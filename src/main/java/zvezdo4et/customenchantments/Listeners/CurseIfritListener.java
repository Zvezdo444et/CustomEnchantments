package zvezdo4et.customenchantments.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import zvezdo4et.customenchantments.CustomEnchantments;
import zvezdo4et.customenchantments.Enchantments.CurseIfritEnchantment;


public class CurseIfritListener implements Listener {

    private final CustomEnchantments plugin;

    public CurseIfritListener(CustomEnchantments plugin) {
        this.plugin = plugin;
        startWaterDamageTimer();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTakeFireDmg(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player player)) {
            return;
        }

        if (!hasCurseIfrit(player)) {
            return;
        }

        EntityDamageEvent.DamageCause cause = e.getCause();
        if (cause == EntityDamageEvent.DamageCause.FIRE ||
                cause == EntityDamageEvent.DamageCause.FIRE_TICK ||
                cause == EntityDamageEvent.DamageCause.LAVA ||
                cause == EntityDamageEvent.DamageCause.HOT_FLOOR) {

            e.setCancelled(true);
        }
    }


    private void startWaterDamageTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!hasCurseIfrit(player)) {
                        continue;
                    }

                    if (player.isInWaterOrRain() ||
                            player.getLocation().getBlock().getType() == Material.WATER) {

                        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR || player.isInvulnerable()) {
                            continue;
                        }
                        player.damage(1.5);


                        player.playSound(player.getLocation(),
                                Sound.ENTITY_PLAYER_HURT_ON_FIRE,
                                1.0f,
                                1.0f
                        );
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private boolean hasCurseIfrit(Player player) {
        ItemStack chestplate = player.getInventory().getChestplate();
        if (chestplate == null || chestplate.getType() == Material.AIR ||
                !chestplate.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = chestplate.getItemMeta();
        String materialName = chestplate.getType().toString();

        return materialName.endsWith("_CHESTPLATE") &&
                meta.hasEnchant(CurseIfritEnchantment.INSTANCE);
    }



}