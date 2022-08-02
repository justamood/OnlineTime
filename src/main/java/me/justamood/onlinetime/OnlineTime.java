package me.justamood.onlinetime;

import org.bukkit.GameRule;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.Bukkit;


public final class OnlineTime extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("OnlineTime has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);

        // Once the server is ready, set doDaylightCycle to false.
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable(){
            @Override
            public void run(){
                if(Bukkit.getOnlinePlayers().size() == 0) {
                    Bukkit.getWorlds().get(0).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
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
        Bukkit.getWorlds().get(0).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (Bukkit.getOnlinePlayers().size() - 1 == 0) {
            Bukkit.getWorlds().get(0).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        }
    }
}