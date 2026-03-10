package zvezdo4et.customenchantments.Enchantments;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zvezdo4et.customenchantments.CustomEnchantments;
import zvezdo4et.customenchantments.ProcreatorEnchantments;

import java.util.HashSet;
import java.util.Set;

public class HastyEnchantment extends ProcreatorEnchantments {
    public static final HastyEnchantment INSTANCE = new HastyEnchantment();

    public HastyEnchantment() {
        super(
                new NamespacedKey(CustomEnchantments.getInstance(), "hasty"),
                "Спешный",
                2,
                21,
                7,
                8,
                2);
    }


    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item){
        Material type = item.getType();
        String typeName = type.toString();

        return typeName.endsWith("_AXE") || typeName.endsWith("_PICKAXE");
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
        return EnchantmentRarity.VERY_RARE;
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
        return Component.text("Спешный " + toRoman(level))
                .color(TextColor.color(0xAAAAAA))
                .decoration(TextDecoration.ITALIC, false);
    }

}
