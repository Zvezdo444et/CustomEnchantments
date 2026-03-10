package zvezdo4et.customenchantments;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import zvezdo4et.customenchantments.Commands.CustomEnchCommand;
import zvezdo4et.customenchantments.Commands.GetItemsCommand;
import zvezdo4et.customenchantments.Enchantments.*;
import zvezdo4et.customenchantments.Listeners.*;

import java.lang.reflect.Field;

public final class CustomEnchantments extends JavaPlugin {

    private static CustomEnchantments instanse;

    public static CustomEnchantments getInstance() {
        return instanse;
    }

    @Override
    public void onEnable() {
        instanse = this;
        registerCustomEnchantments();

        getCommand("customench").setExecutor(new CustomEnchCommand());
        getCommand("getitems").setExecutor(new GetItemsCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EnchantmentTableListener(), this);
        pm.registerEvents(new AnvilListener(), this);
        pm.registerEvents(new FireSpikesListener(), this);
        pm.registerEvents(new CurseIfritListener(this), this);
        pm.registerEvents(new GrindstoneListener(this), this);
        pm.registerEvents(new EntityByEntityDamageListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new WorldEditFixListener(), this);
        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        CustomItems.Init();
    }

    @Override
    public void onDisable() {
    }

    private void registerCustomEnchantments() {
        try {
            Field acceptingField = Enchantment.class.getDeclaredField("acceptingNew");
            acceptingField.setAccessible(true);
            acceptingField.set(null, true);


            if (Enchantment.getByKey(FireSpikesEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(FireSpikesEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(LightningEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(LightningEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(ArcheologyEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(ArcheologyEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(CurseIfritEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(CurseIfritEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(WitheringEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(WitheringEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(FreezingEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(FreezingEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(PoisoningEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(PoisoningEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(HastyEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(HastyEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(LightTrailEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(LightTrailEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(PremiumEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(PremiumEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(AutoMeltingEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(AutoMeltingEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(BloodlustEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(BloodlustEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(TillingEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(TillingEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(GardeningEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(GardeningEnchantment.INSTANCE);
            }
            if (Enchantment.getByKey(TransferEnchantment.INSTANCE.getKey()) == null) {
                Enchantment.registerEnchantment(TransferEnchantment.INSTANCE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
