package zvezdo4et.customenchantments.Listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import zvezdo4et.customenchantments.CustomItems;
import zvezdo4et.customenchantments.Enchantments.*;
import zvezdo4et.customenchantments.ProcreatorEnchantments;

import java.util.*;

public class AnvilListener implements Listener {

    private final List<ProcreatorEnchantments> customEnchants = List.of(
            LightningEnchantment.INSTANCE,
            ArcheologyEnchantment.INSTANCE,
            FireSpikesEnchantment.INSTANCE,
            CurseIfritEnchantment.INSTANCE,
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareAnvil(PrepareAnvilEvent e) {
        ItemStack first = e.getInventory().getItem(0);
        ItemStack second = e.getInventory().getItem(1);
        boolean hasCustomItem = false;

        if (isCustomItem(first)) {
            hasCustomItem = true;
        }

        if (isCustomItem(second)) {
            hasCustomItem = true;
        }

        if (hasCustomItem) e.setResult(null);

        if (first == null || first.getType() == Material.AIR) return;

        if (second != null && second.getType() != Material.AIR) {
            boolean secondIsBook = second.getType() == Material.ENCHANTED_BOOK;
            boolean sameType = first.getType() == second.getType();
            if (!secondIsBook && !sameType) return;
        }

        ItemStack result = e.getResult();

        if (result == null) {
            if (second == null || second.getType() == Material.AIR) return;
            result = first.clone();
        } else {
            result = result.clone();
        }

        ItemMeta meta = result.getItemMeta();
        if (meta == null) return;

        List<Component> oldLore = meta.lore();
        List<Component> cleanLore = new ArrayList<>();
        if (oldLore != null) {
            for (Component line : oldLore) {
                String leg = LegacyComponentSerializer.legacySection().serialize(line).toLowerCase();
                boolean isCustom = false;
                for (ProcreatorEnchantments ench : customEnchants) {
                    if (leg.contains(ench.getCustomName().toLowerCase()) || leg.contains("ифрита")) {
                        isCustom = true;
                        break;
                    }
                }
                if (!isCustom) cleanLore.add(line);
            }
        }

        boolean enchantChanged = false;
        Map<ProcreatorEnchantments, Integer> toApply = new LinkedHashMap<>();

        if (second != null) {
            Map<org.bukkit.enchantments.Enchantment, Integer> secondEnchants;
            if (second.getItemMeta() instanceof EnchantmentStorageMeta bm) {
                secondEnchants = bm.getStoredEnchants();
            } else {
                secondEnchants = second.getEnchantments();
            }

            for (ProcreatorEnchantments ench : customEnchants) {
                int lvl1 = getLevel(first, ench);
                int lvl2 = getLevel(second, ench);

                if (lvl1 > 0 || lvl2 > 0) {
                    Map<org.bukkit.enchantments.Enchantment, Integer> allVanilla = new HashMap<>(first.getEnchantments());
                    allVanilla.putAll(secondEnchants);

                    for (org.bukkit.enchantments.Enchantment vanillaEnch : allVanilla.keySet()) {
                        if (ench.conflictsWith(vanillaEnch)) {
                            e.setResult(null);
                            return;
                        }
                    }

                    for (ProcreatorEnchantments otherCustom : customEnchants) {
                        if (ench == otherCustom) continue;
                        if ((getLevel(first, otherCustom) > 0 || getLevel(second, otherCustom) > 0) && ench.conflictsWith(otherCustom)) {
                            e.setResult(null);
                            return;
                        }
                    }
                }
            }
        }

        for (ProcreatorEnchantments ench : customEnchants) {
            int lvl1 = getLevel(first, ench);
            int lvl2 = (second != null) ? getLevel(second, ench) : 0;

            if (lvl1 == 0 && lvl2 == 0) continue;

            int finalLvl = (lvl1 == lvl2 && lvl1 > 0) ? Math.min(lvl1 + 1, ench.getMaxLevel()) : Math.max(lvl1, lvl2);

            if (finalLvl > 0 && (result.getType() == Material.ENCHANTED_BOOK || ench.canEnchantItem(result))) {
                toApply.put(ench, finalLvl);

                if (meta instanceof EnchantmentStorageMeta bm) bm.addStoredEnchant(ench, finalLvl, true);
                else meta.addEnchant(ench, finalLvl, true);

                if (finalLvl > lvl1 || (lvl1 == 0 && lvl2 > 0)) enchantChanged = true;
            }
        }

        List<Component> fullLore = new ArrayList<>();
        for (Map.Entry<ProcreatorEnchantments, Integer> entry : toApply.entrySet()) {
            fullLore.add(entry.getKey().displayName(entry.getValue()).decoration(TextDecoration.ITALIC, false));
        }
        fullLore.addAll(cleanLore);

        List<Component> deduplicatedLore = new ArrayList<>();
        Set<String> processed = new HashSet<>();
        for (Component line : fullLore) {
            String key = LegacyComponentSerializer.legacySection().serialize(line).trim();
            if (!key.isEmpty() && processed.add(key)) {
                deduplicatedLore.add(line);
            }
        }

        meta.lore(deduplicatedLore.isEmpty() ? null : deduplicatedLore);
        result.setItemMeta(meta);

        String oldName = first.hasItemMeta() ? first.getItemMeta().getDisplayName() : "";
        String newName = meta.hasDisplayName() ? meta.getDisplayName() : "";
        boolean nameChanged = !oldName.equals(newName);

        if (!toApply.isEmpty() || nameChanged || enchantChanged) {
            e.setResult(result);

            if (e.getInventory().getRepairCost() <= 0) {
                e.getInventory().setRepairCost(2);
            } else {
                e.getInventory().setRepairCost(e.getInventory().getRepairCost() + 3);
            }
        }
    }

    private int getLevel(ItemStack item, ProcreatorEnchantments enchant) {
        if (item == null || !item.hasItemMeta()) return 0;
        ItemMeta m = item.getItemMeta();
        if (m instanceof EnchantmentStorageMeta bm && bm.hasStoredEnchant(enchant)) {
            return bm.getStoredEnchantLevel(enchant);
        }
        return m.getEnchantLevel(enchant);
    }

    private boolean isCustomItem(ItemStack item) {
        if (item == null) return false;

        return item.isSimilar(CustomItems.StonePickaxe) ||
                item.isSimilar(CustomItems.WeaponAxe) ||
                item.isSimilar(CustomItems.AncientAlgae) ||
                item.isSimilar(CustomItems.BlueGlass) ||
                item.isSimilar(CustomItems.AncientCoin) ||
                item.isSimilar(CustomItems.BoneSword) ||
                item.isSimilar(CustomItems.Chainmail) ||
                item.isSimilar(CustomItems.CookingOven) ||
                item.isSimilar(CustomItems.DestroyedStaircase) ||
                item.isSimilar(CustomItems.DynamicWing) ||
                item.isSimilar(CustomItems.Eggshell) ||
                item.isSimilar(CustomItems.Forcer) ||
                item.isSimilar(CustomItems.IronShield) ||
                item.isSimilar(CustomItems.GlowingStone) ||
                item.isSimilar(CustomItems.SteelSword) ||
                item.isSimilar(CustomItems.Lawbook) ||
                item.isSimilar(CustomItems.Longbow) ||
                item.isSimilar(CustomItems.Prototaxites) ||
                item.isSimilar(CustomItems.PyramidBrick) ||
                item.isSimilar(CustomItems.ScarecrowHead) ||
                item.isSimilar(CustomItems.WoodenBarrel);
    }
}