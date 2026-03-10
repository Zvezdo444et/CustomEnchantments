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

public class LightTrailEnchantment extends ProcreatorEnchantments {
    public static final LightTrailEnchantment INSTANCE = new LightTrailEnchantment();

    public LightTrailEnchantment() {
        super(
                new NamespacedKey(CustomEnchantments.getInstance(), "lighttrail"),
                "Светлый след",
                1,
                8,
                10,
                10,
                6);
    }


    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item){
        Material type = item.getType();
        String typeName = type.toString();

        return typeName.endsWith("_SWORD") || typeName.endsWith("_AXE");
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
        return Component.text("Светлый след")
                .color(TextColor.color(0xAAAAAA))
                .decoration(TextDecoration.ITALIC, false);
    }

}
