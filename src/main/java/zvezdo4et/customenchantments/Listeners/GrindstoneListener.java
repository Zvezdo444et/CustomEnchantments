package zvezdo4et.customenchantments.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import zvezdo4et.customenchantments.CustomEnchantments;
import zvezdo4et.customenchantments.Enchantments.*;
import zvezdo4et.customenchantments.ProcreatorEnchantments;

import java.util.*;

public class GrindstoneListener implements Listener {

    private final CustomEnchantments plugin;
    private final Map<UUID, BukkitTask> updateTasks = new HashMap<>();

    private final List<ProcreatorEnchantments> customEnchants = Arrays.asList(
            CurseIfritEnchantment.INSTANCE,
            LightningEnchantment.INSTANCE,
            ArcheologyEnchantment.INSTANCE,
            FireSpikesEnchantment.INSTANCE,
            WitheringEnchantment.INSTANCE,
            FreezingEnchantment.INSTANCE,
            PoisoningEnchantment.INSTANCE,
            HastyEnchantment.INSTANCE,
            LightTrailEnchantment.INSTANCE,
            PremiumEnchantment.INSTANCE,
            AutoMeltingEnchantment.INSTANCE,
            BloodlustEnchantment.INSTANCE,
            TillingEnchantment.INSTANCE,
            GardeningEnchantment.INSTANCE,
            TransferEnchantment.INSTANCE
    );

    public GrindstoneListener(CustomEnchantments plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.GRINDSTONE) {
            return;
        }

        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            GrindstoneInventory grindstone = (GrindstoneInventory) event.getInventory();

            startUpdateTask(player, grindstone);
            if (event.getRawSlot() == 2 && !event.isCancelled()) {
                giveExperienceForRemovedEnchants(grindstone, player);
                stopUpdateTask(player.getUniqueId());
            }

            if (event.getRawSlot() == 0 || event.getRawSlot() == 1) {
                Bukkit.getScheduler().runTask(plugin, () -> updateGrindstoneResult(grindstone));
            }
        }
    }

    private void startUpdateTask(Player player, GrindstoneInventory grindstone) {
        UUID playerId = player.getUniqueId();

        if (updateTasks.containsKey(playerId)) {
            return;
        }

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (player.isOnline() && player.getOpenInventory().getTopInventory() instanceof GrindstoneInventory) {
                updateGrindstoneResult((GrindstoneInventory) player.getOpenInventory().getTopInventory());
            } else {
                stopUpdateTask(playerId);
            }
        }, 0L, 2L);

        updateTasks.put(playerId, task);
    }

    private void stopUpdateTask(UUID playerId) {
        if (updateTasks.containsKey(playerId)) {
            updateTasks.get(playerId).cancel();
            updateTasks.remove(playerId);
        }
    }

    private void updateGrindstoneResult(GrindstoneInventory grindstone) {
        ItemStack upperItem = grindstone.getItem(0);
        ItemStack lowerItem = grindstone.getItem(1);

        if (isEmpty(upperItem) && isEmpty(lowerItem)) {
            grindstone.setItem(2, null);
            return;
        }

        boolean hasCustomEnchants = hasAnyCustomEnchant(upperItem) || hasAnyCustomEnchant(lowerItem);

        if (!hasCustomEnchants) {
            return;
        }

        ItemStack result = grindstone.getItem(2);

        if (isEmpty(result)) {
            return;
        }

        boolean hasCurseIfrit = hasCustomEnchant(upperItem, CurseIfritEnchantment.INSTANCE) ||
                hasCustomEnchant(lowerItem, CurseIfritEnchantment.INSTANCE);

        ItemMeta meta = result.getItemMeta();
        if (meta == null) return;

        if (hasCurseIfrit) {
            handleCurseIfrit(meta, result, grindstone, upperItem, lowerItem);
        } else {
            handleOtherCustomEnchants(meta, result, grindstone);
        }
    }

    private boolean isEmpty(ItemStack item) {
        return item == null || item.getType() == Material.AIR;
    }

    private boolean hasAnyCustomEnchant(ItemStack item) {
        if (isEmpty(item) || !item.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        for (ProcreatorEnchantments enchant : customEnchants) {
            if (meta.hasEnchant(enchant)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCustomEnchant(ItemStack item, ProcreatorEnchantments enchantment) {
        if (isEmpty(item) || !item.hasItemMeta()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        return meta.hasEnchant(enchantment);
    }

    private void handleCurseIfrit(ItemMeta meta, ItemStack result, GrindstoneInventory grindstone,
                                  ItemStack upperItem, ItemStack lowerItem) {
        boolean needsUpdate = false;

        if (!meta.hasEnchant(CurseIfritEnchantment.INSTANCE)) {
            meta.addEnchant(CurseIfritEnchantment.INSTANCE, 1, true);
            needsUpdate = true;
        }

        for (ProcreatorEnchantments enchant : customEnchants) {
            if (enchant != CurseIfritEnchantment.INSTANCE && meta.hasEnchant(enchant)) {
                meta.removeEnchant(enchant);
                needsUpdate = true;
            }
        }

        if (needsUpdate) {
            updateLoreForCurse(meta, upperItem, lowerItem);
            result.setItemMeta(meta);
            grindstone.setItem(2, result);
        }
    }

    private void handleOtherCustomEnchants(ItemMeta meta, ItemStack result, GrindstoneInventory grindstone) {
        boolean needsUpdate = false;

        for (ProcreatorEnchantments enchant : customEnchants) {
            if (meta.hasEnchant(enchant)) {
                meta.removeEnchant(enchant);
                needsUpdate = true;
            }
        }

        if (needsUpdate || hasCustomEnchantLore(meta)) {
            clearCustomEnchantLore(meta);
            result.setItemMeta(meta);
            grindstone.setItem(2, result);
        }
    }

    private void updateLoreForCurse(ItemMeta meta, ItemStack upperItem, ItemStack lowerItem) {
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        List<String> newLore = new ArrayList<>();

        for (String line : lore) {
            if (!isCustomEnchantLoreLine(line)) {
                newLore.add(line);
            }
        }
        if (hasCustomEnchant(upperItem, CurseIfritEnchantment.INSTANCE) ||
                hasCustomEnchant(lowerItem, CurseIfritEnchantment.INSTANCE)) {
            newLore.add(0, "§cПроклятие ифрита");
        }

        meta.setLore(newLore);
    }

    private void clearCustomEnchantLore(ItemMeta meta) {
        if (!meta.hasLore()) {
            return;
        }

        List<String> lore = meta.getLore();
        List<String> newLore = new ArrayList<>();

        for (String line : lore) {
            if (!isCustomEnchantLoreLine(line)) {
                newLore.add(line);
            }
        }

        meta.setLore(newLore.isEmpty() ? null : newLore);
    }

    private boolean hasCustomEnchantLore(ItemMeta meta) {
        if (!meta.hasLore()) {
            return false;
        }

        for (String line : meta.getLore()) {
            if (isCustomEnchantLoreLine(line)) {
                return true;
            }
        }

        return false;
    }

    private boolean isCustomEnchantLoreLine(String line) {
        if (line == null) return false;

        String lowerLine = line.toLowerCase();
        return lowerLine.contains("проклятие ифрита") ||
                lowerLine.contains("громовержец") ||
                lowerLine.contains("археология") ||
                lowerLine.contains("огненные шипы") ||
                lowerLine.contains("иссушение") ||
                lowerLine.contains("заморозка") ||
                lowerLine.contains("отравление") ||
                lowerLine.contains("спешный") ||
                lowerLine.contains("светлый след") ||
                lowerLine.contains("премиум") ||
                lowerLine.contains("обжиг") ||
                lowerLine.contains("жажда крови") ||
                lowerLine.contains("эффективная пахота") ||
                lowerLine.contains("садоводство") ||
                lowerLine.contains("пересадка")
                ;
    }

    private void giveExperienceForRemovedEnchants(GrindstoneInventory grindstone, Player player) {
        ItemStack upperItem = grindstone.getItem(0);
        ItemStack lowerItem = grindstone.getItem(1);

        int totalXp = 0;

        if (upperItem != null && upperItem.hasItemMeta()) {
            totalXp += calculateXpForItem(upperItem);
        }
        if (lowerItem != null && lowerItem.hasItemMeta()) {
            totalXp += calculateXpForItem(lowerItem);
        }

        if (totalXp > 0) {
            int minXp = Math.max(1, totalXp / 3);
            player.giveExp(minXp);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.0f);
        }
    }

    private int calculateXpForItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return 0;

        int xp = 0;

        if (meta.hasEnchant(LightningEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(LightningEnchantment.INSTANCE) * 3;
        }
        if (meta.hasEnchant(ArcheologyEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(ArcheologyEnchantment.INSTANCE) * 2;
        }
        if (meta.hasEnchant(FireSpikesEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(FireSpikesEnchantment.INSTANCE) * 3;
        }
        if (meta.hasEnchant(WitheringEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(WitheringEnchantment.INSTANCE) * 2;
        }
        if (meta.hasEnchant(FreezingEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(FreezingEnchantment.INSTANCE) * 4;
        }
        if (meta.hasEnchant(PoisoningEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(PoisoningEnchantment.INSTANCE) * 2;
        }
        if (meta.hasEnchant(HastyEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(HastyEnchantment.INSTANCE) * 4;
        }
        if (meta.hasEnchant(LightTrailEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(LightTrailEnchantment.INSTANCE) * 2;
        }
        if (meta.hasEnchant(PremiumEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(PremiumEnchantment.INSTANCE) * 10000;
        }
        if (meta.hasEnchant(AutoMeltingEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(AutoMeltingEnchantment.INSTANCE) * 5;
        }
        if (meta.hasEnchant(BloodlustEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(BloodlustEnchantment.INSTANCE) * 2;
        }
        if (meta.hasEnchant(TillingEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(TillingEnchantment.INSTANCE) * 3;
        }
        if (meta.hasEnchant(GardeningEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(GardeningEnchantment.INSTANCE) * 3;
        }
        if (meta.hasEnchant(TransferEnchantment.INSTANCE)) {
            xp += meta.getEnchantLevel(TransferEnchantment.INSTANCE) * 7;
        }
        return xp;
    }

    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        stopUpdateTask(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInventoryClose(org.bukkit.event.inventory.InventoryCloseEvent event) {
        if (event.getInventory().getType() == InventoryType.GRINDSTONE &&
                event.getPlayer() instanceof Player) {
            stopUpdateTask(event.getPlayer().getUniqueId());
        }
    }
}