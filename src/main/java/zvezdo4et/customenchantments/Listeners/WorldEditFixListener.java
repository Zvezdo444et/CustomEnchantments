package zvezdo4et.customenchantments.Listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WorldEditFixListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();

            if (item.getType() == Material.WOODEN_AXE && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    e.setCancelled(false);

            }
        }
    }
}