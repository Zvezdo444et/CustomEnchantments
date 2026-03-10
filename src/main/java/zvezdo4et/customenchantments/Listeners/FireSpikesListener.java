package zvezdo4et.customenchantments.Listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import zvezdo4et.customenchantments.Enchantments.FireSpikesEnchantment;

import java.util.Random;

public class FireSpikesListener implements Listener {
    private final Random random = new Random();
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onHasDamage(EntityDamageByEntityEvent e){
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            ItemStack item = player.getInventory().getChestplate();

            if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) return;

            ItemMeta meta = item.getItemMeta();
            if (meta.hasEnchant(FireSpikesEnchantment.INSTANCE)) {
                int level = meta.getEnchantLevel(FireSpikesEnchantment.INSTANCE);
                String materialName = item.getType().toString();
                if (!(materialName.endsWith("_CHESTPLATE"))) return;

                int chance = random.nextInt(100) + 1;

                switch (level) {
                    case 1:
                        if (chance > 20)
                            return;
                        break;
                    case 2:
                        if (chance > 35)
                            return;
                        break;
                    case 3:
                        if (chance > 45)
                            return;
                        break;
                    case 4:
                        if (chance > 52)
                            return;
                        break;
                }

                LivingEntity attacker = null;
                Entity damager = e.getDamager();
                if (damager instanceof LivingEntity) {
                    attacker = (LivingEntity) damager;
                } else if (damager instanceof Arrow) {
                    Arrow arrow = (Arrow) damager;
                    if (arrow.getShooter() instanceof LivingEntity) {
                        attacker = (LivingEntity) arrow.getShooter();
                    }
                }else if (damager instanceof Projectile) {
                    Projectile projectile = (Projectile) damager;
                    if (projectile.getShooter() instanceof LivingEntity) {
                        attacker = (LivingEntity) projectile.getShooter();
                    }
                }
                if (attacker != null) {
                attacker.setFireTicks(20 * level * 3);
                damageArmor(item, level, player);
                }

            }
        }
    }

    public void damageArmor(ItemStack armor, int spikesLevel, Player player) {
        if (armor == null || !armor.hasItemMeta()) return;
        int chance = random.nextInt(100) + 1;

        switch (spikesLevel){
            case 1:
                if (chance > 20)
                    return;
                break;
            case 2:
                if (chance > 35)
                    return;
                break;
            case 3:
                if (chance > 45)
                    return;
                break;
            case 4:
                if (chance > 52)
                    return;
                break;
        }
        if (armor.getItemMeta() instanceof Damageable) {
            Damageable damageable = (Damageable) armor.getItemMeta();

            int currentDamage = damageable.getDamage();
            int additionalDamage = 0;
            switch (spikesLevel){
                case 1:
                    additionalDamage = random.nextInt(2);
                    break;
                case 2:
                    additionalDamage = random.nextInt(3);
                    break;
                case 3:
                    additionalDamage = random.nextInt(4);
                    break;
                case 4:
                    additionalDamage = random.nextInt(5);
                    break;
            }

            damageable.setDamage(currentDamage + additionalDamage);

            int maxDurability = armor.getType().getMaxDurability();
            if (currentDamage + spikesLevel >= maxDurability) {
                player.playSound(
                        player.getLocation(),
                        Sound.ENTITY_ITEM_BREAK,
                        SoundCategory.PLAYERS,
                        1.0f,
                        1.0f
                );
                armor.setAmount(0);
                return;
            }

            armor.setItemMeta((ItemMeta) damageable);
        }
    }

}
