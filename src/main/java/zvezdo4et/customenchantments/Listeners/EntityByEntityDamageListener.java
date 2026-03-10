package zvezdo4et.customenchantments.Listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import zvezdo4et.customenchantments.Enchantments.*;

import java.util.Objects;
import java.util.Random;

public class EntityByEntityDamageListener implements Listener {
    private final Random random = new Random();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDmg(EntityDamageByEntityEvent e) {
        int level;
        int chance;
        Entity damaged;
        String materialName;
        if (!(e.getDamager() instanceof LivingEntity attacker)) return;

        ItemStack item = Objects.requireNonNull(attacker.getEquipment()).getItemInMainHand();

        if (item.getType() == Material.AIR || !item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();

        if (meta.hasEnchant(FreezingEnchantment.INSTANCE)) {

            level = meta.getEnchantLevel(FreezingEnchantment.INSTANCE);

            materialName = item.getType().toString();

            if (!materialName.endsWith("_SWORD")) return;


            damaged = e.getEntity();
            if (damaged instanceof LivingEntity livingDamaged) {
                chance = random.nextInt(100) + 1;

                switch (level) {
                    case 1:
                        if (chance <= 20) {
                            spawnFreezingEffects(livingDamaged.getLocation());
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.SLOW,
                                    20 * 3,
                                    2,
                                    true,
                                    false
                            ));
                        }
                        break;
                    case 2:
                        if (chance <= 27) {
                            spawnFreezingEffects(livingDamaged.getLocation());
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.BLINDNESS,
                                    30,
                                    2,
                                    true,
                                    false
                            ));
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.SLOW,
                                    20 * 4,
                                    2,
                                    true,
                                    false
                            ));
                        }
                        break;
                }
            }
        }
        if (meta.hasEnchant(LightningEnchantment.INSTANCE)) {
            level = meta.getEnchantLevel(LightningEnchantment.INSTANCE);
            materialName = item.getType().toString();
            if (!materialName.endsWith("_AXE")) return;
            LightningEnchantment.INSTANCE.onHit(e, level);
        }
        if (meta.hasEnchant(PoisoningEnchantment.INSTANCE)) {

            level = meta.getEnchantLevel(PoisoningEnchantment.INSTANCE);

            materialName = item.getType().toString();

            if (!(materialName.endsWith("_SWORD") || materialName.endsWith("_AXE"))) return;


            damaged = e.getEntity();
            if (damaged instanceof LivingEntity livingDamaged) {
                chance = random.nextInt(100) + 1;

                switch (level) {
                    case 1:
                        if (chance <= 25) {
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.POISON,
                                    20 * 3,
                                    0,
                                    true,
                                    false
                            ));
                        }
                        break;
                    case 2:
                        if (chance <= 35) {
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.POISON,
                                    20 * 5,
                                    1,
                                    true,
                                    false
                            ));
                        }
                        break;
                }
            }
        }
        if (meta.hasEnchant(WitheringEnchantment.INSTANCE)) {

            level = meta.getEnchantLevel(WitheringEnchantment.INSTANCE);

            materialName = item.getType().toString();

            if (!materialName.endsWith("_SWORD")) return;


            damaged = e.getEntity();
            if (damaged instanceof LivingEntity livingDamaged) {
                chance = random.nextInt(100) + 1;

                switch (level) {
                    case 1:
                        if (chance <= 20) {
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.WITHER,
                                    20 * 3,
                                    2,
                                    true,
                                    false
                            ));
                        }
                        break;
                    case 2:
                        if (chance <= 25) {
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.WITHER,
                                    20 * 3,
                                    3,
                                    true,
                                    false
                            ));
                        }
                        break;
                    case 3:
                        if (chance <= 30) {
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.WITHER,
                                    20 * 3,
                                    4,
                                    true,
                                    false
                            ));
                        }
                        break;
                    case 4:
                        if (chance <= 38) {
                            livingDamaged.addPotionEffect(new PotionEffect(
                                    PotionEffectType.WITHER,
                                    20 * 3,
                                    5,
                                    true,
                                    false
                            ));
                        }
                        break;

                }
            }

        }
        if (meta.hasEnchant(LightTrailEnchantment.INSTANCE)) {

            materialName = item.getType().toString();

            if (!(materialName.endsWith("_SWORD") || materialName.endsWith("_AXE"))) return;

            damaged = e.getEntity();
            if (damaged instanceof LivingEntity livingDamaged) {
                chance = random.nextInt(100) + 1;

                if (chance <= 5) {
                    livingDamaged.addPotionEffect(new PotionEffect(
                            PotionEffectType.GLOWING,
                            20 * 15,
                            1,
                            true,
                            false
                    ));

                }
            }

        }
        if (meta.hasEnchant(HastyEnchantment.INSTANCE)) {

            materialName = item.getType().toString();
            level = meta.getEnchantLevel(HastyEnchantment.INSTANCE);
            if (!materialName.endsWith("_AXE")) return;

            Player player = (Player) e.getDamager();
            chance = random.nextInt(100) + 1;
            switch (level) {
                case 1:
                    if (chance <= 20) {
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.FAST_DIGGING,
                                20 * 15,
                                0,
                                true,
                                true
                        ));

                    }
                    break;
                case 2:
                    if (chance <= 25) {
                        player.addPotionEffect(new PotionEffect(
                                PotionEffectType.FAST_DIGGING,
                                20 * 15,
                                1,
                                true,
                                true
                        ));

                    }
                    break;
            }

        }

        if (meta.hasEnchant(BloodlustEnchantment.INSTANCE)) {
            materialName = item.getType().toString();
            level = meta.getEnchantLevel(BloodlustEnchantment.INSTANCE);
            if (!(materialName.endsWith("_AXE") || materialName.endsWith("_SWORD"))) return;
            Player damager = (Player) e.getDamager();
            damaged = e.getEntity();

            if (damaged instanceof LivingEntity livingDamaged) {
                chance = random.nextInt(100) + 1;
                switch (level) {
                    case 1:
                        if (chance <= 6) {
                            applyBloodlust(livingDamaged, damager, 1); // 1 сердце
                        }
                        break;
                    case 2:
                        if (chance <= 9) {
                            applyBloodlust(livingDamaged, damager, 1.5); // 1.5 сердца
                        }
                        break;
                    case 3:
                        if (chance <= 10) {
                            applyBloodlust(livingDamaged, damager, 2); // 2 сердца
                        }
                        break;
                    case 4:
                        if (chance <= 10) {
                            applyBloodlust(livingDamaged, damager, 2.5); // 2.5 сердца
                        }
                        break;
                }
            }

        }
    }
    private void spawnFreezingEffects(Location location) {
        World world = location.getWorld();
        if (world == null) return;
        Location center = location.clone().add(0.5, 1.0, 0.5);

        world.spawnParticle(Particle.SNOW_SHOVEL, center, 30, 0.8, 0.5, 0.8, 0.05);

        world.spawnParticle(Particle.CLOUD, center, 15, 0.4, 0.2, 0.4, 0.01);

        world.spawnParticle(Particle.ASH, center, 10, 0.3, 0.1, 0.3, 0);

        world.playSound(center, Sound.BLOCK_GLASS_BREAK, 0.3f, 1.5f);
        world.playSound(center, Sound.BLOCK_SNOW_BREAK, 0.4f, 0.8f);
    }

    private void applyBloodlust(LivingEntity damaged, Player damager, double hearts) {
        double damage = hearts * 2;
        double newDamagedHelth = damaged.getHealth() - damage;
        if (newDamagedHelth < 0) newDamagedHelth = 0;
        damaged.setHealth(newDamagedHelth);

        double newAttackerHealth = damager.getHealth() + damage;
        double maxHealth = damager.getMaxHealth();
        if (newAttackerHealth > maxHealth) newAttackerHealth = maxHealth;
        damager.setHealth(newAttackerHealth);

    }
}
