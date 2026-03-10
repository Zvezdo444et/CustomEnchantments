package zvezdo4et.customenchantments.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import zvezdo4et.customenchantments.Enchantments.PremiumEnchantment;

import java.util.Objects;

public class EntityDeathListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player) return;

        Player killer = entity.getKiller();

        if (killer == null) return;
        ItemStack item = Objects.requireNonNull(killer.getEquipment()).getItemInMainHand();
        if (item.getType() == Material.AIR || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta.hasEnchant(PremiumEnchantment.INSTANCE)) {
            int level = meta.getEnchantLevel(PremiumEnchantment.INSTANCE);
            int originalExp = event.getDroppedExp();
            int newExp = 0;
            if (originalExp > 0) {
                newExp = switch (level) {
                    case 1 -> (int) Math.round(originalExp * 1.5);
                    case 2 -> (int) Math.round(originalExp * 2.05);
                    case 3 -> (int) Math.round(originalExp * 2.45);
                    default -> newExp;
                };
                event.setDroppedExp(newExp);
            }

        }
    }
}
