package io.github.staudlol.inventories.command.load;

import io.github.staudlol.inventories.InventorySpigotPlugin;
import io.github.staudlol.inventories.inventory.InventoryPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class LoadInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.RED + "You can't execute this command from the console.");
            return true;
        }

        final Player player = (Player) sender;

        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "Your inventory is full.");
            return true;
        }

        final InventoryPlayer inventoryPlayer = InventorySpigotPlugin.getInstance().getInventoryHandler().findPlayer(player.getUniqueId());

        if (inventoryPlayer == null) {
            player.sendMessage(ChatColor.RED + "You don't have a saved inventory.");
            return true;
        }

        inventoryPlayer.applyInventory(player);
        return true;
    }
}
