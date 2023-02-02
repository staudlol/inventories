package io.github.staudlol.inventories.command.save;

import io.github.staudlol.inventories.InventorySpigotPlugin;
import io.github.staudlol.inventories.profile.PlayerProfile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SaveInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.RED + "You can't execute this command from the console.");
            return true;
        }

        final Player player = (Player) sender;
        final PlayerProfile playerProfile = InventorySpigotPlugin.getInstance().getProfileHandler().findOrCreateProfile(player.getUniqueId(), null);

        if (playerProfile == null) {
            playerProfile.getPlayer().sendMessage(ChatColor.RED + "Please re-login as your profile was not found.");
        }

        playerProfile.setContents(player.getInventory().getContents());
        playerProfile.setArmorContents(player.getInventory().getArmorContents());

        InventorySpigotPlugin.getInstance().getProfileHandler().saveInventory(playerProfile);

        player.sendMessage(ChatColor.GREEN + "Your inventory has been saved.");
        return true;
    }
}
