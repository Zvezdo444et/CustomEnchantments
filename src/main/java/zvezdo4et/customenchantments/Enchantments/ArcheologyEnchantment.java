package zvezdo4et.customenchantments.Enchantments;
import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zvezdo4et.customenchantments.CustomEnchantments;
import zvezdo4et.customenchantments.CustomItems;
import zvezdo4et.customenchantments.ProcreatorEnchantments;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ArcheologyEnchantment extends ProcreatorEnchantments {
    private final Random random = new Random();
    public static final ArcheologyEnchantment INSTANCE = new ArcheologyEnchantment();

    private ArcheologyEnchantment() {
        super(
                new NamespacedKey(CustomEnchantments.getInstance(), "archeology"),
                "Археология",
                3,
                8,
                10,
                10,
                5);
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item){
        Material type = item.getType();
        String typeName = type.toString();

        return typeName.endsWith("_SHOVEL") ||
               typeName.endsWith("_PICKAXE");
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return EnchantmentRarity.UNCOMMON;
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        Set<EquipmentSlot> slots = new HashSet<>();
        slots.add(EquipmentSlot.HAND);
        return slots;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return false;
    }

    @Override
    public @NotNull Component displayName(int level) {
        return Component.text("Археология " + toRoman(level))
                .color(TextColor.color(0xAAAAAA))
                .decoration(TextDecoration.ITALIC, false);
    }


    public void onBreak(Player player, BlockBreakEvent e, int level) {
        int chance;
        switch (level){
            case 1:
                chance = random.nextInt(100) + 1;
                if (chance <= 9) {
                        if (random.nextDouble() * 3.0 > 2.8) {

                            if (random.nextInt(1000000) == 25786) {
                                giveItem(player, e, 5);
                            }

                            int a = random.nextInt(100) + 1;
                            if (a > 0 && a <= 27) {
                                giveItem(player, e, 1);
                            } else if (a > 28 && a <= 31) {
                                giveItem(player, e, 2);
                            } else if (a == 38) {
                                giveItem(player, e, 3);
                            } else if (a == 50) {
                                giveItem(player, e, 4);
                            }
                        }
                }
                break;
            case 2:
                chance = random.nextInt(100) + 1;
                if (chance <= 14) {
                        if (random.nextDouble() * 3.0 > 2.8) {
                            if (random.nextInt(1000000) == 678898) {
                                giveItem(player, e, 5);
                            }

                            int a = random.nextInt(100)+1;
                            if (a > 0 && a <= 18) {
                                giveItem(player, e, 1);
                            } else if (a > 25 && a <= 34) {
                                giveItem(player, e, 2);
                            } else if (a > 38 && a <= 40) {
                                giveItem(player, e, 3);
                            } else if (a == 50 || a == 98 || a == 44 || a == 52) {
                                giveItem(player, e, 4);
                            }
                        }
                }
                break;
            case 3:
                chance = random.nextInt(100) + 1;
                if (chance <= 20) {
                        if (random.nextDouble() * 3.0 > 2.8) {
                            if (random.nextInt(1000000) == 235786) {
                                giveItem(player, e, 5);
                            }
                            if (random.nextInt(1000000) == 125786) {
                                giveItem(player, e, 5);
                            }
                            int a = random.nextInt(100)+1;
                            if (a > 0 && a <= 23) {
                                giveItem(player, e, 1);
                            } else if (a > 25 && a <= 32) {
                                giveItem(player, e, 2);
                            } else if (a > 38 && a <= 43) {
                                giveItem(player, e, 3);
                            } else if (a == 50 || a == 98 || a == 46 || a == 71 || a == 88) {
                                giveItem(player, e, 4);
                            }
                        }
                }
                break;
        }
    }

    public void giveItem(Player player, BlockBreakEvent e, int rare){
        Block block = e.getBlock();
        boolean hasSlot;
        if (player.getInventory().firstEmpty() == -1) {
            hasSlot = false;

        } else {
            hasSlot = true;
        }
        int b = random.nextInt(5);
        switch (rare){
            case 1:
                if (hasSlot) {
                    switch (b) {
                        case 0:
                            player.getInventory().addItem(CustomItems.SteelSword);
                            break;
                        case 1:
                            player.getInventory().addItem(CustomItems.BlueGlass);
                            break;
                        case 2:
                            player.getInventory().addItem(CustomItems.BlueGlass);
                            break;
                        case 3:
                            player.getInventory().addItem(CustomItems.Forcer);
                            break;
                        case 4:
                            player.getInventory().addItem(CustomItems.GlowingStone);
                            break;
                    }
                } else {
                    Location dropLocation = block.getLocation().add(0.5, 0.5, 0.5);
                    player.sendActionBar("§cВ вашем инвентаре нет места! Предмет выброшен на землю.");
                    switch (b) {
                        case 0:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.SteelSword);
                            break;
                        case 1:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.BlueGlass);
                            break;
                        case 2:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.BlueGlass);
                            break;
                        case 3:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.Forcer);
                            break;
                        case 4:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.GlowingStone);
                            break;
                    }
                }
                break;
            case 2:
                if (hasSlot) {
                    switch (b) {
                        case 0:
                            player.getInventory().addItem(CustomItems.CookingOven);
                            break;
                        case 1:
                            player.getInventory().addItem(CustomItems.Chainmail);
                            break;
                        case 2:
                            player.getInventory().addItem(CustomItems.IronShield);
                            break;
                        case 3:
                            player.getInventory().addItem(CustomItems.Lawbook);
                            break;
                        case 4:
                            player.getInventory().addItem(CustomItems.WoodenBarrel);
                            break;
                    }
                } else {
                    Location dropLocation = block.getLocation().add(0.5, 0.5, 0.5);
                    player.sendActionBar("§cВ вашем инвентаре нет места! Предмет выброшен на землю.");
                    switch (b) {
                        case 0:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.CookingOven);
                            break;
                        case 1:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.Chainmail);
                            break;
                        case 2:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.IronShield);
                            break;
                        case 3:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.Lawbook);
                            break;
                        case 4:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.WoodenBarrel);
                            break;
                    }
                }
                break;
            case 3:
                if (hasSlot) {
                    switch (b) {
                        case 0:
                            player.getInventory().addItem(CustomItems.AncientCoin);
                            break;
                        case 1:
                            player.getInventory().addItem(CustomItems.DestroyedStaircase);
                            break;
                        case 2:
                            player.getInventory().addItem(CustomItems.Longbow);
                            break;
                        case 3:
                            player.getInventory().addItem(CustomItems.StonePickaxe);
                            break;
                        case 4:
                            player.getInventory().addItem(CustomItems.WeaponAxe);
                            break;
                    }
                } else {
                    Location dropLocation = block.getLocation().add(0.5, 0.5, 0.5);
                    player.sendActionBar("§cВ вашем инвентаре нет места! Предмет выброшен на землю.");
                    switch (b) {
                        case 0:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.AncientCoin);
                            break;
                        case 1:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.DestroyedStaircase);
                            break;
                        case 2:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.Longbow);
                            break;
                        case 3:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.StonePickaxe);
                            break;
                        case 4:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.WeaponAxe);
                            break;
                    }
                }
                break;
            case 4:
                if (hasSlot) {
                    switch (b) {
                        case 0:
                            player.getInventory().addItem(CustomItems.AncientAlgae);
                            break;
                        case 1:
                            player.getInventory().addItem(CustomItems.BoneSword);
                            break;
                        case 2:
                            player.getInventory().addItem(CustomItems.Eggshell);
                            break;
                        case 3:
                            player.getInventory().addItem(CustomItems.Prototaxites);
                            break;
                        case 4:
                            break;
                    }
                } else {
                    Location dropLocation = block.getLocation().add(0.5, 0.5, 0.5);
                    player.sendActionBar("§cВ вашем инвентаре нет места! Предмет выброшен на землю.");
                    switch (b) {
                        case 0:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.AncientAlgae);
                            break;
                        case 1:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.BoneSword);
                            break;
                        case 2:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.Eggshell);
                            break;
                        case 3:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.Prototaxites);
                            break;
                        case 4:
                            break;
                    }
                }
                break;
            case 5:
                int c = random.nextInt(2);
                if (hasSlot) {
                    switch (c) {
                        case 0:
                            player.getInventory().addItem(CustomItems.PyramidBrick);
                            break;
                        case 1:
                            player.getInventory().addItem(CustomItems.ScarecrowHead);
                            break;
                    }
                } else {
                    Location dropLocation = block.getLocation().add(0.5, 0.5, 0.5);
                    player.sendActionBar("§cВ вашем инвентаре нет места! Предмет выброшен на землю.");
                    switch (c) {
                        case 0:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.ScarecrowHead);
                            break;
                        case 1:
                            player.getWorld().dropItemNaturally(dropLocation, CustomItems.PyramidBrick);
                            break;
                    }
                }
                break;
        }
    }
}
