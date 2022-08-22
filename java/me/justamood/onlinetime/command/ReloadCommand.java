package me.justamood.onlinetime.command;

import me.justamood.onlinetime.OnlineTimePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("onlinetime.reload")) {
            OnlineTimePlugin.getPluginConfig().reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "[Online Time] Reloaded config!");
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED + "* You do not have permission to do that!");
            return false;
        }
    }

}
