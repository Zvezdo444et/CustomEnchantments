package zvezdo4et.customenchantments.Listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zvezdo4et.customenchantments.CustomEnchantments;
import zvezdo4et.customenchantments.Enchantments.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EnchantmentTableListener implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onEnchantItem(EnchantItemEvent e) {
        ItemStack item = e.getItem();

        Player player = e.getEnchanter();

        int levelCost = e.getExpLevelCost();
        double chance;

        if (LightningEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelLightning(levelCost);
            chance = calculateChance(levelCost, enchantLevel);

            if (random.nextDouble() < chance) {
                player.sendMessage("§eВы получили зачарование §aГромовержец " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(LightningEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, LightningEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }

        }

        if (ArcheologyEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelArcheology(levelCost);
            chance = calculateChance(levelCost, enchantLevel);

            if (random.nextDouble() < chance) {
                player.sendMessage("§eВы получили зачарование §aАрхеология " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(ArcheologyEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, ArcheologyEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }

        }

        if (FireSpikesEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelFireSpikes(levelCost);
            chance = calculateChance(levelCost, enchantLevel);


            if (random.nextDouble() < chance) {
                player.sendMessage("§eВы получили зачарование §aОгненные шипы " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(FireSpikesEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, FireSpikesEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }

        }

        if (CurseIfritEnchantment.INSTANCE.canEnchantItem(item)) {
            if (random.nextInt(100) + 1 < 12) {
                player.sendMessage("§eВы получили зачарование §cПроклятие ифрита§e!");
                e.getEnchantsToAdd().put(CurseIfritEnchantment.INSTANCE, 1);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, CurseIfritEnchantment.INSTANCE.displayName(1));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }

        }

        if (WitheringEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelWithering(levelCost);

            if (random.nextInt(100) + 1 < 13) {
                player.sendMessage("§eВы получили зачарование §aИссушение " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(WitheringEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, WitheringEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }


    }

        if (FreezingEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelFreezing(levelCost);
            if (enchantLevel == 0) return;
            if (random.nextInt(100) + 1 < 6) {
                player.sendMessage("§eВы получили зачарование §aЗаморозка " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(FreezingEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, FreezingEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }


        }

        if (PoisoningEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelPoisoning(levelCost);
            if (enchantLevel == 0) return;
            if (random.nextInt(100) + 1 < 22) {
                player.sendMessage("§eВы получили зачарование §aОтравление " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(PoisoningEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, PoisoningEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }


        }

        if (HastyEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelHasty(levelCost);

            if (random.nextInt(100) + 1 < 3) {
                player.sendMessage("§eВы получили зачарование §6Спешный " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(HastyEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, HastyEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }

        }

        if (LightTrailEnchantment.INSTANCE.canEnchantItem(item)) {
            if (random.nextInt(100) + 1 < 20) {
                player.sendMessage("§eВы получили зачарование §aСветлый след§e!");
                e.getEnchantsToAdd().put(LightTrailEnchantment.INSTANCE, 1);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, LightTrailEnchantment.INSTANCE.displayName(1));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }

        }

        if (AutoMeltingEnchantment.INSTANCE.canEnchantItem(item)){
            if (random.nextInt(100) + 1 < 6) {
            Map<Enchantment, Integer> enchantsToAdd = e.getEnchantsToAdd();
            boolean hasConflict = false;
            for (Map.Entry<Enchantment, Integer> entry : enchantsToAdd.entrySet()) {
                if (AutoMeltingEnchantment.INSTANCE.conflictsWith(entry.getKey())) {
                    hasConflict = true;
                    break;
                }
            }
            if (hasConflict) {
                return;
            }

                player.sendMessage("§eВы получили зачарование §6Обжиг§e!");
                e.getEnchantsToAdd().put(AutoMeltingEnchantment.INSTANCE, 1);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, AutoMeltingEnchantment.INSTANCE.displayName(1));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }
        }

        if (BloodlustEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelBloodlust(levelCost);
            if (random.nextInt(100) + 1 < 11) {
                player.sendMessage("§eВы получили зачарование §aЖажда крови " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(BloodlustEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, BloodlustEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }
        }

        if (TillingEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelTilling(levelCost);
            if (random.nextInt(100) + 1 < 20) {
                player.sendMessage("§eВы получили зачарование §aЭффективная пахота " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(TillingEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, TillingEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }
        }

        if (GardeningEnchantment.INSTANCE.canEnchantItem(item)) {
            int enchantLevel = calculateEnchantmentLevelTilling(levelCost);
            if (random.nextInt(100) + 1 < 12) {
                player.sendMessage("§eВы получили зачарование §aСадоводство " + enchantLevel + "§e!");
                e.getEnchantsToAdd().put(GardeningEnchantment.INSTANCE, enchantLevel);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, GardeningEnchantment.INSTANCE.displayName(enchantLevel));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }
        }

        if (TransferEnchantment.INSTANCE.canEnchantItem(item)) {
            if (random.nextInt(100) + 1 < 5) {
                player.sendMessage("§eВы получили зачарование §6Пересадка§e!");
                e.getEnchantsToAdd().put(TransferEnchantment.INSTANCE, 1);
                Bukkit.getScheduler().runTask(CustomEnchantments.getInstance(), () -> {
                    if (item == null || !item.hasItemMeta()) return;

                    ItemMeta meta = item.getItemMeta();
                    List<Component> lore = meta.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }

                    lore.add(0, TransferEnchantment.INSTANCE.displayName(1));

                    meta.lore(lore);
                    item.setItemMeta(meta);
                });
            }

        }

    }


    private int calculateEnchantmentLevelLightning(int levelCost) {
        if (levelCost >= 30) {
            return random.nextInt(2) + 3;
        } else if (levelCost >= 20) {
            return random.nextInt(3) + 1;
        } else {
            return 1;
        }
    }
    private int calculateEnchantmentLevelArcheology(int levelCost) {
        if (levelCost >= 30) {
            return random.nextInt(2) + 2;
        } else if (levelCost >= 20) {
            return random.nextInt(2) + 1;
        } else {
            return 1;
        }
    }
    private int calculateEnchantmentLevelFireSpikes(int levelCost) {
        if (levelCost >= 30) {
            int chance = random.nextInt(100) + 1;
            if (chance <= 40) {
                return 2;
            } else if (chance <= 80) {
                return 3;
            } else
                return 4;
        } else if (levelCost >= 20) {
            return random.nextInt(2) + 1;
        } else {
            return 1;
        }
    }
    private int calculateEnchantmentLevelWithering(int levelCost) {
        if (levelCost >= 30) {
            int chance = random.nextInt(100) + 1;
            if (chance <= 50) {
                return 2;
            } else if (chance <= 80) {
                return 3;
            } else
                return 4;
        } else if (levelCost >= 20) {
            return random.nextInt(2) + 2;
        } else {
            return 1;
        }
    }
    private int calculateEnchantmentLevelFreezing(int levelCost) {
        int chance = random.nextInt(100) + 1;
        if (levelCost >= 30) {
            if (chance <= 75) {
                return 1;
            } else {
                return 2;
            }
        }
        else if (levelCost >= 20) {
            return 1;
        }
        return 0;
    }
    private int calculateEnchantmentLevelPoisoning(int levelCost) {
        int chance = random.nextInt(100) + 1;
        if (levelCost >= 30) {
            if (chance <= 55) {
                return 1;
            } else {
                return 2;
            }
        }
        else if (levelCost >= 20) {
            return 1;
        }
        return 0;
    }
    private int calculateEnchantmentLevelHasty(int levelCost) {
        int chance = random.nextInt(100) + 1;
        if (levelCost >= 30) {
            if (chance <= 80) {
                return 1;
            } else {
                return 2;
            }
        }
        return 0;
    }
    private int calculateEnchantmentLevelBloodlust(int levelCost) {
        if (levelCost >= 30) {
            int chance = random.nextInt(100) + 1;
            if (chance <= 50) {
                return 2;
            } else if (chance <= 90) {
                return 3;
            } else
                return 4;
        } else if (levelCost >= 20) {
            return random.nextInt(2) + 2;
        } else {
            return 1;
        }
    }
    private int calculateEnchantmentLevelTilling(int levelCost) {
        if (levelCost >= 30) {
            int chance = random.nextInt(100) + 1;
            if (chance <= 80) {
                return 2;
            } else {
                return 3;
            }
        } else if (levelCost >= 20) {
            return random.nextInt(2) + 1;
        } else {
            return 1;
        }
    }

    private double calculateChance(int levelCost, int enchantLevel)  {
        double baseChance;
        if (enchantLevel == 1){
            baseChance = 0.25 + levelCost * 0.005;
        } else if (enchantLevel == 2) {
            baseChance = 0.21 + levelCost * 0.003;
        } else if (enchantLevel == 3) {
            baseChance = 0.13 + levelCost * 0.002;
        } else if (enchantLevel == 4) {
            baseChance = 0.10 + levelCost * 0.002;
        } else
            baseChance = 0.06;

        return baseChance;

    }

}
