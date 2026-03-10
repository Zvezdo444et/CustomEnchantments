package zvezdo4et.customenchantments.Commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zvezdo4et.customenchantments.Enchantments.*;
import zvezdo4et.customenchantments.ProcreatorEnchantments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomEnchCommand implements CommandExecutor, TabCompleter {


    private static final Map<String, ProcreatorEnchantments> ENCHANT_MAP = new HashMap<>();

    static {

        ENCHANT_MAP.put("lightning", LightningEnchantment.INSTANCE);
        ENCHANT_MAP.put("archeology", ArcheologyEnchantment.INSTANCE);
        ENCHANT_MAP.put("firespikes", FireSpikesEnchantment.INSTANCE);
        ENCHANT_MAP.put("withering", WitheringEnchantment.INSTANCE);
        ENCHANT_MAP.put("freezing", FreezingEnchantment.INSTANCE);
        ENCHANT_MAP.put("poisoning", PoisoningEnchantment.INSTANCE);
        ENCHANT_MAP.put("hasty", HastyEnchantment.INSTANCE);
        ENCHANT_MAP.put("lighttrail", LightTrailEnchantment.INSTANCE);
        ENCHANT_MAP.put("transfer", TransferEnchantment.INSTANCE);
        ENCHANT_MAP.put("curseifrit", CurseIfritEnchantment.INSTANCE);
        ENCHANT_MAP.put("premium", PremiumEnchantment.INSTANCE);
        ENCHANT_MAP.put("automelting", AutoMeltingEnchantment.INSTANCE);
        ENCHANT_MAP.put("bloodlust", BloodlustEnchantment.INSTANCE);
        ENCHANT_MAP.put("tilling", TillingEnchantment.INSTANCE);
        ENCHANT_MAP.put("gardening", GardeningEnchantment.INSTANCE);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }


        if (args.length < 1) {
            player.sendMessage("§6Использование: /customench <зачарование> [уровень]");
            return false;
        }

        String enchantName = args[0].toLowerCase();


        int level = 1;
        boolean b = !(enchantName.equals("curseifrit") || enchantName.equals("lighttrail") || enchantName.equals("automelting") || enchantName.equals("transfer"));
        if (args.length < 2 && b) {
            player.sendMessage("§6Использование: /customench <зачарование> [уровень]");
            return false;
        }
        if (args.length >= 2) {
            try {
                level = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("§4Вы неверно указали уровень!");
                return false;
            }
        }
        ProcreatorEnchantments enchant = ENCHANT_MAP.get(enchantName);

        if (enchant != null && level > enchant.getMaxLevel() && b) {
            player.sendMessage("§4Максимальный уровень зачарования " + enchant.getMaxLevel() + "!");
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) {
            player.sendMessage("§4Держите предмет в руке!");
            return false;
        }


        ItemMeta meta = item.getItemMeta();

        switch (enchantName) {
            case "curseifrit":
                if (level == 0) {
                    removeEnch(meta, CurseIfritEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!CurseIfritEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на проклятие ифрита!");
                    return false;
                }

                meta.addEnchant(CurseIfritEnchantment.INSTANCE, 1, true);
                updateItemLore(meta, CurseIfritEnchantment.INSTANCE, 1, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на §cПроклятие ифрита");
                return true;
            case "transfer":
                if (level == 0) {
                    removeEnch(meta, TransferEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!TransferEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на пересадку!");
                    return false;
                }

                meta.addEnchant(TransferEnchantment.INSTANCE, 1, true);
                updateItemLore(meta, TransferEnchantment.INSTANCE, 1, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Пересадку");
                return true;
            case "lightning":
                if (level == 0) {
                    removeEnch(meta, LightningEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!LightningEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на Громовержец!");
                    return false;
                }
                meta.addEnchant(LightningEnchantment.INSTANCE, level, true);
                updateItemLore(meta, LightningEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Громовержец " + level);
                return true;
            case "archeology":
                if (level == 0) {
                    removeEnch(meta, ArcheologyEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!ArcheologyEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на Археологию!");
                    return false;
                }
                meta.addEnchant(ArcheologyEnchantment.INSTANCE, level, true);
                updateItemLore(meta, ArcheologyEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Археологию " + level);
                return true;
            case "firespikes":
                if (level == 0) {
                    removeEnch(meta, FireSpikesEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!FireSpikesEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на огненные шипы!");
                    return false;
                }
                meta.addEnchant(FireSpikesEnchantment.INSTANCE, level, true);
                updateItemLore(meta, FireSpikesEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Огненные шипы " + level);
                return true;
            case "withering":

                if (level == 0) {
                    removeEnch(meta, WitheringEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!WitheringEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на иссушение!");
                    return false;
                }
                System.out.println("ДО: Иссушения=" + meta.hasEnchant(PoisoningEnchantment.INSTANCE));
                meta.addEnchant(WitheringEnchantment.INSTANCE, level, true);
                updateItemLore(meta, WitheringEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Иссушение " + level);
                return true;
            case "freezing":
                if (level == 0) {
                    removeEnch(meta, FreezingEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!FreezingEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на заморозку!");
                    return false;
                }
                meta.addEnchant(FreezingEnchantment.INSTANCE, level, true);
                updateItemLore(meta, FreezingEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Заморозку " + level);
                return true;
            case "poisoning":
                if (level == 0) {
                    removeEnch(meta, PoisoningEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!PoisoningEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на отравление!");
                    return false;
                }
                meta.addEnchant(PoisoningEnchantment.INSTANCE, level, true);
                updateItemLore(meta, PoisoningEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Отравление " + level);
                return true;
            case "hasty":
                if (level == 0) {
                    removeEnch(meta, HastyEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!HastyEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на спешный!");
                    return false;
                }
                meta.addEnchant(HastyEnchantment.INSTANCE, level, true);
                updateItemLore(meta, HastyEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Спешный " + level);
                return true;
            case "lighttrail":
                if (level == 0) {
                    removeEnch(meta, LightTrailEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!LightTrailEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на светлый след!");
                    return false;
                }
                meta.addEnchant(LightTrailEnchantment.INSTANCE, 1, true);
                updateItemLore(meta, LightTrailEnchantment.INSTANCE, 1, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Светлый след");
                return true;
            case "premium":
                if (level == 0) {
                    player.sendMessage("§4Данное зачарование нельзя снять с предмета!");
                    return true;
                }
                if (!PremiumEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на премиум!");
                    return false;
                }
                meta.addEnchant(PremiumEnchantment.INSTANCE, level, true);
                updateItemLore(meta, PremiumEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Премиум " + level);
                return true;
            case "automelting":
                if (level == 0) {
                    removeEnch(meta, AutoMeltingEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!AutoMeltingEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на обжиг!");
                    return false;
                }
                meta.addEnchant(AutoMeltingEnchantment.INSTANCE, 1, true);
                updateItemLore(meta, AutoMeltingEnchantment.INSTANCE, 1, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Обжиг");
                return true;
            case "bloodlust":
                if (level == 0) {
                    removeEnch(meta, BloodlustEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!BloodlustEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на жажду крови!");
                    return false;
                }
                meta.addEnchant(BloodlustEnchantment.INSTANCE, level, true);
                updateItemLore(meta, BloodlustEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Жажда крови " + level);
                return true;
            case "tilling":
                if (level == 0) {
                    removeEnch(meta, TillingEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!TillingEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на эффективную пахоту!");
                    return false;
                }
                meta.addEnchant(TillingEnchantment.INSTANCE, level, true);
                updateItemLore(meta, TillingEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Эффективная пахота " + level);
                return true;
            case "gardening":
                if (level == 0) {
                    removeEnch(meta, GardeningEnchantment.INSTANCE, item, enchantName, player);
                    return true;
                }
                if (!GardeningEnchantment.INSTANCE.canEnchantItem(item)) {
                    player.sendMessage("§4Этот предмет нельзя зачаровать на садоводство!");
                    return false;
                }
                meta.addEnchant(GardeningEnchantment.INSTANCE, level, true);
                updateItemLore(meta, GardeningEnchantment.INSTANCE, level, enchantName);
                item.setItemMeta(meta);

                player.sendMessage("§aПредмет зачарован на Садоводство " + level);
                return true;
        }


        return false;
    }

    private void removeEnch(ItemMeta meta, ProcreatorEnchantments enchantment, ItemStack item, String enchantName, Player player) {
        if (!meta.hasEnchant(enchantment)) {
            player.sendMessage("§4На данном предмете нет зачарования для снятия!");
            return;
        }
        meta.removeEnchant(enchantment);
        updateItemLore(meta, enchantment, 0, enchantName);
        item.setItemMeta(meta);
        player.sendMessage("§aВы успешно сняли зачарование §e" + enchantment.getCustomName() + " §aс предмета!");
    }

    private void updateItemLore(ItemMeta meta, ProcreatorEnchantments enchantment, int level, String enchantName) {
        List<Component> lore = meta.lore();
        if (lore == null) {
            lore = new ArrayList<>();
        }



        lore.removeIf(component ->
                component.toString().contains(enchantment.getCustomName()) ||
                        component.toString().contains(enchantName)

        );
        if (level == 0) {
            meta.lore(lore.isEmpty() ? null : lore);
            return;
        }
        Component displayName = enchantment.displayName(level);
        lore.add(0, displayName);

        meta.lore(lore);
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        List<String> tab = new ArrayList<>();

        if (strings.length == 1) {
            if (commandSender instanceof Player player) {
                ItemStack itemInHand = player.getInventory().getItemInMainHand();

                String typeName = itemInHand.getType().toString();

                if (typeName.endsWith("_CHESTPLATE")) {
                    tab.add("firespikes");
                    tab.add("curseifrit");
                } else if (typeName.endsWith("_AXE")) {
                    tab.add("lightning");
                    tab.add("poisoning");
                    tab.add("hasty");
                    tab.add("lighttrail");
                    tab.add("automelting");
                    tab.add("bloodlust");
                    tab.add("transfer");
                } else if (typeName.endsWith("_SHOVEL")) {
                    tab.add("archeology");
                    tab.add("automelting");
                } else if (typeName.endsWith("_PICKAXE")) {
                    tab.add("archeology");
                    tab.add("hasty");
                    tab.add("premium");
                    tab.add("automelting");
                } else if (typeName.endsWith("_SWORD")) {
                    tab.add("freezing");
                    tab.add("withering");
                    tab.add("poisoning");
                    tab.add("lighttrail");
                    tab.add("premium");
                    tab.add("bloodlust");
                } else if (typeName.endsWith("_HOE")) {
                    tab.add("tilling");
                    tab.add("gardening");
                    tab.add("transfer");
                }

            }
        } else if (strings.length == 2) {
            String enchantName = strings[0].toLowerCase();
            switch (enchantName) {
                case "freezing", "poisoning", "hasty":
                    for (int i = 1; i <= 2; i++) {
                        tab.add(String.valueOf(i));
                    }
                    break;
                case "archeology", "premium", "tilling", "gardening":
                    for (int i = 1; i <= 3; i++) {
                        tab.add(String.valueOf(i));
                    }
                    break;
                case "firespikes", "withering", "bloodlust":
                    for (int i = 1; i <= 4; i++) {
                        tab.add(String.valueOf(i));
                    }
                    break;
                case "lightning":
                    for (int i = 1; i <= 5; i++) {
                        tab.add(String.valueOf(i));
                    }
                    break;
            }
        }

        return tab;
    }
}
