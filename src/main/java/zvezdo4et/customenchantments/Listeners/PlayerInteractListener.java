package zvezdo4et.customenchantments.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import zvezdo4et.customenchantments.CustomItems;
import zvezdo4et.customenchantments.Enchantments.GardeningEnchantment;
import zvezdo4et.customenchantments.Enchantments.TillingEnchantment;

import java.util.*;

public class PlayerInteractListener implements Listener {

    private boolean isProcessing = false;
    private static final Map<Material, Material> SEED_TO_CROP = new HashMap<>();
    private static final List<Material> SEED_PRIORITY = new ArrayList<>();

    static {
        SEED_TO_CROP.put(Material.WHEAT_SEEDS, Material.WHEAT);
        SEED_TO_CROP.put(Material.POTATO, Material.POTATOES);
        SEED_TO_CROP.put(Material.CARROT, Material.CARROTS);
        SEED_TO_CROP.put(Material.BEETROOT_SEEDS, Material.BEETROOTS);
        SEED_TO_CROP.put(Material.MELON_SEEDS, Material.MELON_STEM);
        SEED_TO_CROP.put(Material.PUMPKIN_SEEDS, Material.PUMPKIN_STEM);
        SEED_TO_CROP.put(Material.NETHER_WART, Material.NETHER_WART);
        SEED_PRIORITY.addAll(Arrays.asList(
                Material.NETHER_WART,
                Material.POTATO,
                Material.CARROT,
                Material.WHEAT_SEEDS,
                Material.BEETROOT_SEEDS,
                Material.MELON_SEEDS,
                Material.PUMPKIN_SEEDS
        ));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onPlayerInteract(PlayerInteractEvent e) {
        if (isProcessing) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        if (item == null) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        Block clickedBlock = e.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }
        if (isCustomItem(item)) {
            e.setCancelled(true);
            return;
        }

        if (meta == null) {
            return;
        }

        isProcessing = true;
        try {
            if (meta.hasEnchant(TillingEnchantment.INSTANCE)) {
                handleTilling(clickedBlock, e, meta, player);
            }

            if (meta.hasEnchant(GardeningEnchantment.INSTANCE)) {
                handleGardening(e, player, clickedBlock, meta);
            }
        } finally {
            isProcessing = false;
        }
    }

    private void handleGardening(PlayerInteractEvent e, Player player, Block clickedBlock, ItemMeta meta) {
        boolean isFarmland = clickedBlock.getType() == Material.FARMLAND;
        boolean isSoulSand = clickedBlock.getType() == Material.SOUL_SAND;

        if (!isFarmland && !isSoulSand) return;

        e.setCancelled(true);

        World world = clickedBlock.getWorld();
        int level = meta.getEnchantLevel(GardeningEnchantment.INSTANCE);
        int radius = (level - 1);

        List<Block> plantableBlocks = new ArrayList<>();

        for (int x = -1; x <= radius; x++) {
            for (int z = -1; z <= radius; z++) {
                Block soil = world.getBlockAt(
                        clickedBlock.getX() + x,
                        clickedBlock.getY(),
                        clickedBlock.getZ() + z
                );

                Block cropBlock = soil.getRelative(BlockFace.UP);

                if (canPlantOn(soil, cropBlock)) {
                    plantableBlocks.add(cropBlock);
                }
            }
        }

        if (plantableBlocks.isEmpty()) return;

        int planted = plantSeeds(player, plantableBlocks);

        if (planted > 0) {
            world.playSound(clickedBlock.getLocation(),
                    Sound.ITEM_CROP_PLANT, 0.8f, 1.2f);
        } else {
            player.sendActionBar("§cСемена не найдены в инвентаре");
        }
    }

    private void handleTilling(Block clickedBlock, PlayerInteractEvent e, ItemMeta meta, Player player) {
        if (clickedBlock.getType() != Material.FARMLAND && canBeTilled(clickedBlock.getType())) {
            e.setCancelled(true);
            int tilled = 0;
            World world = clickedBlock.getWorld();
            int level = meta.getEnchantLevel(TillingEnchantment.INSTANCE);
            boolean hasWater = player.getInventory().contains(Material.WATER_BUCKET);


            for (int x = -1; x <= level - 1; x++) {
                for (int z = -1; z <= level - 1; z++) {
                    Block target = world.getBlockAt(
                            clickedBlock.getX() + x,
                            clickedBlock.getY(),
                            clickedBlock.getZ() + z
                    );

                    PlayerInteractEvent fakeEvent = new PlayerInteractEvent(
                            player, Action.RIGHT_CLICK_BLOCK, player.getInventory().getItemInMainHand(),
                            target, BlockFace.UP
                    );

                    Bukkit.getPluginManager().callEvent(fakeEvent);
                    if (!fakeEvent.isCancelled()) {
                        if (target.getRelative(BlockFace.UP).getType() == Material.AIR && canBeTilled(target.getType())) {

                            target.setType(Material.FARMLAND);

                            if (hasWater && target.getBlockData() instanceof Farmland farmlandData) {
                                farmlandData.setMoisture(farmlandData.getMaximumMoisture());
                                target.setBlockData(farmlandData);
                            }
                            tilled++;

                        }
                    }
                }
            }
            if (tilled > 0) {
                world.playSound(clickedBlock.getLocation(), Sound.ITEM_HOE_TILL, 1.0f, 1.0f);
                applyDamageWithUnbreaking(player, e.getItem(), tilled);
            }
        }
    }

    private boolean canPlantOn(Block soil, Block cropBlock) {
        if (cropBlock.getType() != Material.AIR) {
            return false;
        }

        Material soilType = soil.getType();

        if (soilType == Material.SOUL_SAND) {
            return true;
        }

        return soilType == Material.FARMLAND;
    }

    private int plantSeeds(Player player, List<Block> plantableBlocks) {
        PlayerInventory inv = player.getInventory();
        int planted = 0;

        ItemStack offHand = inv.getItemInOffHand();
        if (offHand != null && SEED_TO_CROP.containsKey(offHand.getType())) {
            planted += plantWithSeedType(player, plantableBlocks, offHand.getType());
            if (planted >= plantableBlocks.size()) return planted;
        }

        for (Material seedType : SEED_PRIORITY) {
            if (planted >= plantableBlocks.size()) break;

            for (int i = 0; i < inv.getSize(); i++) {
                if (planted >= plantableBlocks.size()) break;

                ItemStack item = inv.getItem(i);
                if (item != null && item.getType() == seedType) {
                    planted += plantWithSeedStack(player, plantableBlocks, item, i);
                }
            }
        }

        return planted;
    }

    private int plantWithSeedType(Player player, List<Block> plantableBlocks, Material seedType) {
        Material cropType = SEED_TO_CROP.get(seedType);
        if (cropType == null) return 0;

        PlayerInventory inv = player.getInventory();
        int planted = 0;

        for (int i = 0; i < inv.getSize(); i++) {
            if (planted >= plantableBlocks.size()) break;

            ItemStack item = inv.getItem(i);
            if (item != null && item.getType() == seedType) {
                planted += plantWithSeedStack(player, plantableBlocks, item, i);
            }
        }

        return planted;
    }

    private int plantWithSeedStack(Player player, List<Block> plantableBlocks,
                                   ItemStack seedStack, int slot) {
        Material seedType = seedStack.getType();
        Material cropType = SEED_TO_CROP.get(seedType);
        if (cropType == null) return 0;

        int planted = 0;
        PlayerInventory inv = player.getInventory();

        for (Block block : plantableBlocks) {
            if (seedStack.getAmount() <= 0) break;

            Block soil = block.getRelative(BlockFace.DOWN);
            if (!canPlantCropOnSoil(cropType, soil)) continue;

            PlayerInteractEvent fakeEvent = new PlayerInteractEvent(
                    player, Action.RIGHT_CLICK_BLOCK, seedStack,
                    soil, BlockFace.UP
            );
            Bukkit.getPluginManager().callEvent(fakeEvent);

            if (!fakeEvent.isCancelled()) {
                block.setType(cropType);

                if (block.getBlockData() instanceof Ageable ageable) {
                    ageable.setAge(0);
                    block.setBlockData(ageable);
                }

                seedStack.setAmount(seedStack.getAmount() - 1);
                planted++;

                if (seedStack.getAmount() <= 0) {
                    inv.setItem(slot, null);
                    break;
                } else {
                    inv.setItem(slot, seedStack);
                }
            }
        }
        return planted;
    }

    private boolean canPlantCropOnSoil(Material cropType, Block soil) {
        Material soilType = soil.getType();

        if (cropType == Material.NETHER_WART) {
            return soilType == Material.SOUL_SAND;
        } else {
            return soilType == Material.FARMLAND;
        }
    }

    private boolean canBeTilled(Material material) {
        return material == Material.GRASS_BLOCK ||
                material == Material.DIRT ||
                material == Material.COARSE_DIRT ||
                material == Material.PODZOL ||
                material == Material.MYCELIUM;
    }

    private void applyDamageWithUnbreaking(Player player, ItemStack tool, int baseDamage) {
        if (tool == null || !tool.hasItemMeta()) return;

        ItemMeta meta = tool.getItemMeta();
        if (!(meta instanceof Damageable)) return;

        Damageable damageable = (Damageable) meta;

        int unbreakingLevel = meta.getEnchantLevel(org.bukkit.enchantments.Enchantment.DURABILITY);

        int actualDamage = calculateActualDamage(baseDamage, unbreakingLevel);

        if (actualDamage <= 0) {
            return;
        }

        int currentDamage = damageable.getDamage();
        int maxDurability = tool.getType().getMaxDurability();
        int newDamage = currentDamage + actualDamage;

        if (newDamage < maxDurability) {
            damageable.setDamage(newDamage);
            tool.setItemMeta(meta);
        } else {
            player.getInventory().setItemInMainHand(null);
            player.playSound(player.getLocation(),
                    Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
        }
    }

    private int calculateActualDamage(int baseDamage, int unbreakingLevel) {
        if (unbreakingLevel <= 0) {
            return baseDamage;
        }

        int actualDamage = 0;
        double avoidChance = (double) unbreakingLevel / (unbreakingLevel + 1);

        for (int i = 0; i < baseDamage; i++) {
            if (Math.random() > avoidChance) {
                actualDamage++;
            }
        }

        return actualDamage;
    }

    private boolean isCustomItem(ItemStack item) {
        ItemStack[] customItems = {
                CustomItems.StonePickaxe, CustomItems.WeaponAxe, CustomItems.AncientAlgae,
                CustomItems.BlueGlass, CustomItems.AncientCoin, CustomItems.BoneSword,
                CustomItems.Chainmail, CustomItems.CookingOven, CustomItems.DestroyedStaircase,
                CustomItems.DynamicWing, CustomItems.Eggshell, CustomItems.Forcer,
                CustomItems.IronShield, CustomItems.GlowingStone, CustomItems.SteelSword,
                CustomItems.Lawbook, CustomItems.Longbow, CustomItems.Prototaxites,
                CustomItems.PyramidBrick, CustomItems.ScarecrowHead, CustomItems.WoodenBarrel
        };

        for (ItemStack customItem : customItems) {
            if (item.isSimilar(customItem)) {
                return true;
            }
        }
        return false;
    }

}
