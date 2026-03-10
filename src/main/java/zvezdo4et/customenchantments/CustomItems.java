package zvezdo4et.customenchantments;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class CustomItems {

    public static ItemStack SteelSword;
    public static ItemStack DynamicWing;
    public static ItemStack Forcer;
    public static ItemStack GlowingStone;
    public static ItemStack BlueGlass;
    public static ItemStack IronShield;
    public static ItemStack CookingOven;
    public static ItemStack Chainmail;
    public static ItemStack WoodenBarrel;
    public static ItemStack Lawbook;
    public static ItemStack StonePickaxe;
    public static ItemStack WeaponAxe;
    public static ItemStack AncientCoin;
    public static ItemStack DestroyedStaircase;
    public static ItemStack Longbow;
    public static ItemStack Prototaxites;
    public static ItemStack AncientAlgae;
    public static ItemStack BoneSword;
    public static ItemStack Eggshell;
    public static ItemStack PyramidBrick;
    public static ItemStack ScarecrowHead;

    public static void Init() {
        SteelSword = SteelSword();
        DynamicWing = DynamicWing();
        Forcer = Forcer();
        GlowingStone = GlowingStone();
        BlueGlass = BlueGlass();
        IronShield = IronShield();
        CookingOven = CookingOven();
        Chainmail = Chainmail();
        WoodenBarrel = WoodenBarrel();
        Lawbook = Lawbook();
        StonePickaxe = StonePickaxe();
        WeaponAxe = WeaponAxe();
        AncientCoin = AncientCoin();
        DestroyedStaircase = DestroyedStaircase();
        Longbow = Longbow();
        Prototaxites = Prototaxites();
        AncientAlgae = AncientAlgae();
        BoneSword = BoneSword();
        Eggshell = Eggshell();
        PyramidBrick = PyramidBrick();
        ScarecrowHead = ScarecrowHead();
    }

    private static ItemStack SteelSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§aСтальной меч");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из современности",
                "§6Редкость: §a◎§f◎◎◎◎",
                "§6Стоимость: §f600",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));
        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.setDurability((short) 250);
        return item;
    }

    private static ItemStack DynamicWing() {
        ItemStack item = new ItemStack(Material.PHANTOM_MEMBRANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§aДинамическое крыло");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из современности",
                "§6Редкость: §a◎§f◎◎◎◎",
                "§6Стоимость: §f600",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack Forcer() {
        ItemStack item = new ItemStack(Material.PISTON);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§aПоршень");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из современности",
                "§6Редкость: §a◎§f◎◎◎◎",
                "§6Стоимость: §f600",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack GlowingStone() {
        ItemStack item = new ItemStack(Material.GLOWSTONE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§aСветящийся камень");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из современности",
                "§6Редкость: §a◎§f◎◎◎◎",
                "§6Стоимость: §f600",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack BlueGlass() {
        ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§aСинее стекло");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из современности",
                "§6Редкость: §a◎§f◎◎◎◎",
                "§6Стоимость: §f600",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack IronShield() {
        ItemStack item = new ItemStack(Material.IRON_BARS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6Железный щит");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из средневековья",
                "§6Редкость: §a◎◎§f◎◎◎",
                "§6Стоимость: §f1200",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack CookingOven() {
        ItemStack item = new ItemStack(Material.SMOKER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6Кулинарная печь");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из средневековья",
                "§6Редкость: §a◎◎§f◎◎◎",
                "§6Стоимость: §f1200",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack Chainmail() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6Кольчуга");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из средневековья",
                "§6Редкость: §a◎◎§f◎◎◎",
                "§6Стоимость: §f1200",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));
        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.setDurability((short) 240);
        return item;
    }

    private static ItemStack WoodenBarrel() {
        ItemStack item = new ItemStack(Material.BARREL);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6Деревянная бочка");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из средневековья",
                "§6Редкость: §a◎◎§f◎◎◎",
                "§6Стоимость: §f1200",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack Lawbook() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6Кодекс");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из средневековья",
                "§6Редкость: §a◎◎§f◎◎◎",
                "§6Стоимость: §f1200",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack StonePickaxe() {
        ItemStack item = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7Сломанная каменная кирка");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из античности",
                "§6Редкость: §a◎◎◎§f◎◎",
                "§6Стоимость: §f2400",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));
        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.setDurability((short) 131);
        return item;
    }

    private static ItemStack WeaponAxe() {
        ItemStack item = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7Оружейный топор");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из античности",
                "§6Редкость: §a◎◎◎§f◎◎",
                "§6Стоимость: §f2400",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.setDurability((short) 250);
        return item;
    }

    private static ItemStack AncientCoin() {
        ItemStack item = new ItemStack(Material.SUNFLOWER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7Старинная монета");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из античности",
                "§6Редкость: §a◎◎◎§f◎◎",
                "§6Стоимость: §f2400",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack DestroyedStaircase() {
        ItemStack item = new ItemStack(Material.CRACKED_STONE_BRICKS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7Обломок лестницы");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из античности",
                "§6Редкость: §a◎◎◎§f◎◎",
                "§6Стоимость: §f2400",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack Longbow() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§7Длинный лук");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет из античности",
                "§6Редкость: §a◎◎◎§f◎◎",
                "§6Стоимость: §f2400",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));
        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        item.setDurability((short) 384);
        return item;
    }

    private static ItemStack Prototaxites() {
        ItemStack item = new ItemStack(Material.RED_MUSHROOM);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§cПрототаксит");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет доисторический",
                "§6Редкость: §a◎◎◎◎§f◎",
                "§6Стоимость: §f3800",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack AncientAlgae() {
        ItemStack item = new ItemStack(Material.VINE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§cДревние водоросли");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет доисторический",
                "§6Редкость: §a◎◎◎◎§f◎",
                "§6Стоимость: §f3800",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack BoneSword() {
        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§cКостный меч");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет доисторический",
                "§6Редкость: §a◎◎◎◎§f◎",
                "§6Стоимость: §f3800",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell",
                "",
                "§7Когда в ведущей руке:",
                "§2 3.5 Урон",
                "§2 4 Скорость атаки"
        ));

        UUID damageUUID = UUID.randomUUID();

        AttributeModifier damageModifier = new AttributeModifier(
                damageUUID,
                "generic.attack_damage",
                2.5,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlot.HAND
        );

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack Eggshell() {
        ItemStack item = new ItemStack(Material.EGG);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§cЯичная скорлупа");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет доисторический",
                "§6Редкость: §a◎◎◎◎§f◎",
                "§6Стоимость: §f3800",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack ScarecrowHead() {
        ItemStack item = new ItemStack(Material.END_STONE_BRICKS);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§eКирпич пирамиды");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет §kдоисторический",
                "§6Редкость: §c◎◎◎◎◎",
                "§6Стоимость: §f10000",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack PyramidBrick() {
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§eГолова пугала");

        meta.setLore(Arrays.asList(
                "§dЭтот предмет §kдоисторический",
                "§6Редкость: §c◎◎◎◎◎",
                "§6Стоимость: §f10000",
                "§dЧтобы выставить на аукцион введите: " + "§6/ah sell"
        ));

        item.setItemMeta(meta);

        return item;
    }
}
