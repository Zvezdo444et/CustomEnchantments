package zvezdo4et.customenchantments.Enchantments;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import zvezdo4et.customenchantments.CustomEnchantments;
import zvezdo4et.customenchantments.ProcreatorEnchantments;

import java.util.HashSet;
import java.util.Set;

public class LightningEnchantment extends ProcreatorEnchantments {

    public static final LightningEnchantment INSTANCE = new LightningEnchantment();

    private LightningEnchantment() {
        super(
                new NamespacedKey(CustomEnchantments.getInstance(), "lightning"),
                "Громовержец",
                5,
                10,
                10,
                10,
                4);
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item){
        Material type = item.getType();
        String typeName = type.toString();

        return typeName.endsWith("_AXE");
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
        return EnchantmentRarity.RARE;
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
        return Component.text("Громовержец " + toRoman(level))
                .color(TextColor.color(0xAAAAAA))
                .decoration(TextDecoration.ITALIC, false);
    }



    public void onHit(EntityDamageByEntityEvent e, int level) {

        Entity damaged = e.getEntity();
        Entity damager = e.getDamager();

        double chance = level * 0.037;

        if (Math.random() >= chance) return;

        World world = damaged.getWorld();

        world.strikeLightningEffect(damaged.getLocation());

        if (damaged instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) damaged;
            target.damage(8.2);
            target.setLastDamageCause(new EntityDamageByEntityEvent(
                    damager,
                    target,
                    EntityDamageEvent.DamageCause.LIGHTNING,
                    8.2
            ));
        }

    }
}

