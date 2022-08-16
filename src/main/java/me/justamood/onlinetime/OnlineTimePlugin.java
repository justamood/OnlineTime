package me.justamood.onlinetime;

import me.justamood.onlinetime.api.OnlineTimeAPI;
import me.justamood.onlinetime.api.OnlineTimeWorld;
import me.justamood.onlinetime.command.ReloadCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;


public final class OnlineTimePlugin extends JavaPlugin {

    private static Logger logger;
    private static OnlineTimeConfig config;
    private static OnlineTimeEvents events;

    public static OnlineTimeConfig getPluginConfig() {
        return config;
    }

    @Override
    public void onEnable() {
        config = new OnlineTimeConfig(this);
        logger = getLogger();

        logger.log(Level.INFO, "OnlineTime has been enabled");

        registerEvents();
        registerCommands();
        initialize();
    }

    @Override
    public void onDisable() {
        logger.log(Level.INFO, "OnlineTime has been disabled");
        HandlerList.unregisterAll(events);
    }

    private void registerEvents() {
        logger.log(Level.INFO, "Registering events");
        events = new OnlineTimeEvents();
        Bukkit.getPluginManager().registerEvents(events, this);
    }

    private void registerCommands() {
        logger.log(Level.INFO, "Registering commands");
        PluginCommand cmd = Bukkit.getPluginCommand("otreload");
        if (cmd != null) cmd.setExecutor(new ReloadCommand());
    }

    private void initialize() {
        OnlineTimeAPI api = OnlineTimeAPI.get();
        for (OnlineTimeWorld otw : api.getWorlds()) {
            if (!config.isBlacklisted(otw)) {
                otw.setDoDaylightCycle(otw.getPlayerCount() > 0);
            }
        }
    }

}