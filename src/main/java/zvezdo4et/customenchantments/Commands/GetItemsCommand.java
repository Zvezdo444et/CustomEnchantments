package zvezdo4et.customenchantments.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import zvezdo4et.customenchantments.CustomItems;

public class GetItemsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;

        player.getInventory().addItem(CustomItems.SteelSword);
        player.getInventory().addItem(CustomItems.BlueGlass);
        player.getInventory().addItem(CustomItems.DynamicWing);
        player.getInventory().addItem(CustomItems.Forcer);
        player.getInventory().addItem(CustomItems.GlowingStone);
        player.getInventory().addItem(CustomItems.CookingOven);
        player.getInventory().addItem(CustomItems.Chainmail);
        player.getInventory().addItem(CustomItems.IronShield);
        player.getInventory().addItem(CustomItems.Lawbook);
        player.getInventory().addItem(CustomItems.WoodenBarrel);
        player.getInventory().addItem(CustomItems.AncientCoin);
        player.getInventory().addItem(CustomItems.DestroyedStaircase);
        player.getInventory().addItem(CustomItems.Longbow);
        player.getInventory().addItem(CustomItems.StonePickaxe);
        player.getInventory().addItem(CustomItems.WeaponAxe);
        player.getInventory().addItem(CustomItems.AncientAlgae);
        player.getInventory().addItem(CustomItems.BoneSword);
        player.getInventory().addItem(CustomItems.Eggshell);
        player.getInventory().addItem(CustomItems.Prototaxites);
        player.getInventory().addItem(CustomItems.ScarecrowHead);
        player.getInventory().addItem(CustomItems.PyramidBrick);
        return false;
    }
}
