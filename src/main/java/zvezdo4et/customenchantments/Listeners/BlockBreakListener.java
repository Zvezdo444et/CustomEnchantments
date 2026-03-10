package zvezdo4et.customenchantments.Listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import zvezdo4et.customenchantments.CustomEnchantments;
import zvezdo4et.customenchantments.CustomItems;
import zvezdo4et.customenchantments.Enchantments.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BlockBreakListener implements Listener {
    private static final Map<Material, Material> PickaxeMaterial = new HashMap<>();
    private static final Map<Material, Material> AxeMaterial = new HashMap<>();
    private static final Map<Material, Material> ShovelMaterial = new HashMap<>();
    private static final Map<Material, Integer> Exp = new HashMap<>();

    static {
        PickaxeMaterial.put(Material.COBBLESTONE, Material.STONE);
        PickaxeMaterial.put(Material.IRON_ORE, Material.IRON_INGOT);
        PickaxeMaterial.put(Material.NETHER_GOLD_ORE, Material.GOLD_INGOT);
        PickaxeMaterial.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        PickaxeMaterial.put(Material.NETHERRACK, Material.NETHER_BRICK);
        PickaxeMaterial.put(Material.CLAY, Material.TERRACOTTA);
        PickaxeMaterial.put(Material.STONE, Material.SMOOTH_STONE);
        PickaxeMaterial.put(Material.ANCIENT_DEBRIS, Material.NETHERITE_SCRAP);
        PickaxeMaterial.put(Material.SANDSTONE, Material.SMOOTH_SANDSTONE);
        PickaxeMaterial.put(Material.NETHER_BRICKS, Material.CRACKED_NETHER_BRICKS);
        PickaxeMaterial.put(Material.STONE_BRICKS, Material.CRACKED_STONE_BRICKS);
        PickaxeMaterial.put(Material.WET_SPONGE, Material.SPONGE);
        PickaxeMaterial.put(Material.RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE);
        PickaxeMaterial.put(Material.QUARTZ_BLOCK, Material.SMOOTH_QUARTZ);
        PickaxeMaterial.put(Material.POLISHED_BLACKSTONE_BRICKS, Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        PickaxeMaterial.put(Material.WHITE_TERRACOTTA, Material.WHITE_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.ORANGE_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.MAGENTA_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.PINK_TERRACOTTA, Material.PINK_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.CYAN_TERRACOTTA, Material.CYAN_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.BROWN_TERRACOTTA, Material.BROWN_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.LIGHT_GRAY_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.YELLOW_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.LIME_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.GREEN_TERRACOTTA, Material.GREEN_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.PURPLE_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.RED_TERRACOTTA, Material.RED_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.GRAY_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.BLUE_TERRACOTTA, Material.BLUE_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.BLACK_TERRACOTTA, Material.BLACK_GLAZED_TERRACOTTA);
        PickaxeMaterial.put(Material.SEA_PICKLE, Material.LIME_DYE);
        PickaxeMaterial.put(Material.CACTUS, Material.GREEN_DYE);
        PickaxeMaterial.put(Material.KELP, Material.DRIED_KELP);
    }

    static {
        AxeMaterial.put(Material.ACACIA_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.JUNGLE_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.DARK_OAK_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.BIRCH_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.OAK_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.SPRUCE_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_JUNGLE_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_ACACIA_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_DARK_OAK_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_BIRCH_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_OAK_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_SPRUCE_LOG, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_JUNGLE_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_ACACIA_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_DARK_OAK_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_BIRCH_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_OAK_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.STRIPPED_SPRUCE_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.JUNGLE_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.ACACIA_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.DARK_OAK_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.BIRCH_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.OAK_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.SPRUCE_WOOD, Material.CHARCOAL);
        AxeMaterial.put(Material.WET_SPONGE, Material.SPONGE);
        AxeMaterial.put(Material.SEA_PICKLE, Material.LIME_DYE);
        AxeMaterial.put(Material.CACTUS, Material.GREEN_DYE);
        AxeMaterial.put(Material.KELP, Material.DRIED_KELP);
    }

    static {
        ShovelMaterial.put(Material.SAND, Material.GLASS);
        ShovelMaterial.put(Material.RED_SAND, Material.GLASS);
        ShovelMaterial.put(Material.CLAY, Material.BRICK);
        ShovelMaterial.put(Material.WET_SPONGE, Material.SPONGE);
        ShovelMaterial.put(Material.CACTUS, Material.GREEN_DYE);
        ShovelMaterial.put(Material.SEA_PICKLE, Material.LIME_DYE);
        ShovelMaterial.put(Material.KELP, Material.DRIED_KELP);
    }

    static {
        Exp.put(Material.SAND, 1);
        Exp.put(Material.RED_SAND, 1);
        Exp.put(Material.CLAY, 1);
        Exp.put(Material.IRON_ORE, 2);
        Exp.put(Material.NETHER_GOLD_ORE, 2);
        Exp.put(Material.GOLD_ORE, 3);
        Exp.put(Material.ANCIENT_DEBRIS, 7);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Material[] ores = {
                Material.COAL_ORE,
                Material.DIAMOND_ORE,
                Material.EMERALD_ORE,
                Material.LAPIS_ORE,
                Material.REDSTONE_ORE,
                Material.NETHER_QUARTZ_ORE,
                Material.NETHER_GOLD_ORE
        };
        int level;
        String toolName;
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().isSimilar(CustomItems.WeaponAxe) || player.getInventory().getItemInMainHand().isSimilar(CustomItems.StonePickaxe)) {
            e.setCancelled(true);
        }
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        Material blockType = e.getBlock().getType();
        ItemStack item = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();

        if (item.getType() == Material.AIR || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta.hasEnchant(ArcheologyEnchantment.INSTANCE)) {
            level = meta.getEnchantLevel(ArcheologyEnchantment.INSTANCE);
            toolName = item.getType().toString();

            if (toolName.endsWith("_SHOVEL")) {

                if (!(blockType == Material.SAND ||
                        blockType == Material.GRASS_BLOCK ||
                        blockType == Material.DIRT ||
                        blockType == Material.CLAY ||
                        blockType == Material.GRAVEL ||
                        blockType == Material.SOUL_SAND ||
                        blockType == Material.SOUL_SOIL ||
                        blockType == Material.RED_SAND ||
                        blockType == Material.COARSE_DIRT ||
                        blockType == Material.PODZOL ||
                        blockType == Material.MYCELIUM)) return;


                ArcheologyEnchantment.INSTANCE.onBreak(player, e, level);

            } else if (toolName.endsWith("_PICKAXE")) {

                if (!(blockType == Material.CRACKED_NETHER_BRICKS ||
                        blockType == Material.END_STONE ||
                        blockType == Material.NETHER_QUARTZ_ORE ||
                        blockType == Material.NETHER_GOLD_ORE ||
                        blockType == Material.GOLD_ORE ||
                        blockType == Material.IRON_ORE ||
                        blockType == Material.COAL_ORE ||
                        blockType == Material.EMERALD_ORE ||
                        blockType == Material.LAPIS_ORE ||
                        blockType == Material.DIAMOND_ORE ||
                        blockType == Material.MOSSY_COBBLESTONE ||
                        blockType == Material.CHISELED_NETHER_BRICKS ||
                        blockType == Material.COBBLESTONE ||
                        blockType == Material.ANDESITE ||
                        blockType == Material.DIORITE ||
                        blockType == Material.GRANITE ||
                        blockType == Material.STONE ||
                        blockType == Material.BASALT ||
                        blockType == Material.POLISHED_BASALT ||
                        blockType == Material.NETHERRACK ||
                        blockType == Material.SMOOTH_SANDSTONE ||
                        blockType == Material.SMOOTH_RED_SANDSTONE ||
                        blockType == Material.NETHER_BRICKS ||
                        blockType == Material.WHITE_TERRACOTTA ||
                        blockType == Material.ORANGE_TERRACOTTA ||
                        blockType == Material.MAGENTA_TERRACOTTA ||
                        blockType == Material.LIGHT_BLUE_TERRACOTTA ||
                        blockType == Material.YELLOW_TERRACOTTA ||
                        blockType == Material.LIME_TERRACOTTA ||
                        blockType == Material.PINK_TERRACOTTA ||
                        blockType == Material.GRAY_TERRACOTTA ||
                        blockType == Material.LIGHT_GRAY_TERRACOTTA ||
                        blockType == Material.CYAN_TERRACOTTA ||
                        blockType == Material.PURPLE_TERRACOTTA ||
                        blockType == Material.BROWN_TERRACOTTA ||
                        blockType == Material.BLUE_TERRACOTTA ||
                        blockType == Material.GREEN_TERRACOTTA ||
                        blockType == Material.RED_TERRACOTTA ||
                        blockType == Material.BLACK_TERRACOTTA ||
                        blockType == Material.RED_SANDSTONE ||
                        blockType == Material.CHISELED_RED_SANDSTONE ||
                        blockType == Material.CUT_RED_SANDSTONE ||
                        blockType == Material.PRISMARINE_BRICKS ||
                        blockType == Material.DARK_PRISMARINE ||
                        blockType == Material.RED_NETHER_BRICKS ||
                        blockType == Material.MAGMA_BLOCK ||
                        blockType == Material.ANCIENT_DEBRIS ||
                        blockType == Material.GILDED_BLACKSTONE ||
                        blockType == Material.POLISHED_BLACKSTONE ||
                        blockType == Material.BLACKSTONE ||
                        blockType == Material.CHISELED_POLISHED_BLACKSTONE ||
                        blockType == Material.POLISHED_BLACKSTONE_BRICKS ||
                        blockType == Material.CRACKED_POLISHED_BLACKSTONE_BRICKS ||
                        blockType == Material.END_STONE_BRICKS)) return;


                ArcheologyEnchantment.INSTANCE.onBreak(player, e, level);

            } else
                return;
        }
        if (meta.hasEnchant(HastyEnchantment.INSTANCE)) {
            level = meta.getEnchantLevel(HastyEnchantment.INSTANCE);
            toolName = item.getType().toString();
            if (toolName.endsWith("_PICKAXE")) {
                switch (level) {
                    case 1:
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.FAST_DIGGING,
                                20 * 3,
                                0,
                                true,
                                false
                        ));
                        break;
                    case 2:
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.FAST_DIGGING,
                                20 * 3,
                                1,
                                true,
                                false
                        ));
                        break;
                }
            } else if (toolName.endsWith("_AXE")) {
                switch (level) {
                    case 1:
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.FAST_DIGGING,
                                20 * 3,
                                0,
                                true,
                                false
                        ));
                        break;
                    case 2:
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.FAST_DIGGING,
                                20 * 3,
                                1,
                                true,
                                false
                        ));
                        break;
                }
            }
        }

        if (meta.hasEnchant(PremiumEnchantment.INSTANCE)) {
            level = meta.getEnchantLevel(PremiumEnchantment.INSTANCE);

            toolName = item.getType().toString();
            boolean isOre = false;
            for (Material ore : ores) {
                if (blockType == ore) {
                    isOre = true;
                    break;
                }
            }
            if (!isOre) return;

            int newExp = 0;
            int originalExp = e.getExpToDrop();

            if (originalExp > 0 && toolName.endsWith("_PICKAXE")) {
                newExp = switch (level) {
                    case 1 -> (int) Math.round(originalExp * 1.5);
                    case 2 -> (int) Math.round(originalExp * 2.1);
                    case 3 -> (int) Math.round(originalExp * 2.7);
                    default -> newExp;
                };

                e.setExpToDrop(newExp);
            }
        }

        if (meta.hasEnchant(AutoMeltingEnchantment.INSTANCE)) {
            toolName = item.getType().toString();
            Block brokenBlock = e.getBlock();
            Material converted = null;
            Collection<ItemStack> drops = brokenBlock.getDrops();

            int totalItems = 0;
            int expToDrop = e.getExpToDrop();
            if (toolName.endsWith("_PICKAXE")) {
                if (PickaxeMaterial.containsKey(e.getBlock().getType())) {
                    converted = PickaxeMaterial.get(e.getBlock().getType());
                }
                totalItems = 1;
            } else if (toolName.endsWith("_SHOVEL")) {
                for (ItemStack drop : drops) {
                    totalItems += drop.getAmount();
                }
                if (ShovelMaterial.containsKey(e.getBlock().getType())) {
                    converted = ShovelMaterial.get(e.getBlock().getType());
                }
            } else if (toolName.endsWith("_AXE")) {
                if (AxeMaterial.containsKey(e.getBlock().getType())) {
                    converted = AxeMaterial.get(e.getBlock().getType());
                }
                totalItems = 1;
            }
            if (converted != null) {
                e.setDropItems(false);
                Location dropLocation = brokenBlock.getLocation().add(0.1, 0.1, 0.1);
                ItemStack dropItem = new ItemStack(converted, totalItems);
                player.getWorld().dropItemNaturally(dropLocation, dropItem);
                spawnMeltingEffects(brokenBlock.getLocation().add(0.1, 0.1, 0.1));
                if (Exp.containsKey(e.getBlock().getType())) {
                    expToDrop = Exp.get(e.getBlock().getType());
                }
                e.setExpToDrop(expToDrop);
            }

        }

        if (meta.hasEnchant(TransferEnchantment.INSTANCE)) {

            Block block = e.getBlock();


            if (block.getBlockData() instanceof Ageable ageable) {

                if (ageable.getAge() == ageable.getMaximumAge()) {
                    final Material finalBlockType = blockType;
                    Bukkit.getScheduler().runTask(CustomEnchantments.getPlugin(CustomEnchantments.class), () -> {

                        PlayerInteractEvent fakeInteract = new PlayerInteractEvent(
                                player,
                                Action.RIGHT_CLICK_BLOCK,
                                player.getInventory().getItemInMainHand(),
                                block,
                                org.bukkit.block.BlockFace.UP
                        );

                        Bukkit.getPluginManager().callEvent(fakeInteract);

                        if (!fakeInteract.isCancelled()) {
                            block.setType(finalBlockType);
                            if (block.getBlockData() instanceof Ageable newAge) {
                                newAge.setAge(0);
                                block.setBlockData(newAge);
                            }
                        }
                    });
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    private void spawnMeltingEffects(Location location) {
        World world = location.getWorld();
        if (world == null) return;
        world.spawnParticle(Particle.FLAME, location.clone().add(0.1, 0.3, 0.3), 10, 0.2, 0.2, 0.2, 0.05);

        world.spawnParticle(Particle.SMOKE_NORMAL, location.clone().add(0.1, 0.3, 0.3), 15, 0.2, 0.2, 0.2, 0.02);
        world.playSound(location.clone().add(0.1, 0.3, 0.3), Sound.BLOCK_LAVA_EXTINGUISH, 0.1f, 2f);
    }

}
