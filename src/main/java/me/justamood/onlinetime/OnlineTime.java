package me.justamood.onlinetime;

import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.Bukkit;


public final class OnlineTime extends JavaPlugin implements Listener {

    FileConfiguration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        System.out.println("OnlineTime has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
        config = getConfig();
        Bukkit.getPluginCommand("otreload").setExecutor(new reloadCommand());
        // Once the server is ready, set doDaylightCycle to false.
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
            @Override
            public void run(){
                if(Bukkit.getOnlinePlayers().size() == 0) {
                    for (org.bukkit.World world : Bukkit.getWorlds()) {
                        if(worldIsNotBlacklisted(world.getName())) {
                            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("OnlineTime has been disabled!");
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(worldIsNotBlacklisted(event.getPlayer().getWorld().getName())) {
            event.getPlayer().getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if(worldIsNotBlacklisted(event.getPlayer().getWorld().getName())) {
            if(event.getPlayer().getWorld().getPlayers().size() - 1 == 0) {
                event.getPlayer().getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            }
        }
    }

    public boolean worldIsNotBlacklisted(String worldname){
        if(config.getList("blacklisted-worlds") == null) {
            return true;
        }
        return !(config.getList("blacklisted-worlds").contains(worldname));
    }


    public class reloadCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if(sender.hasPermission("onlinetime.reload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.GREEN+ "[Online Time] Reloaded config!");
                return true;
            }
            else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
                return false;
            }
        }
    }

}