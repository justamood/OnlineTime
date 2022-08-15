package me.justamood.onlinetime;

import me.justamood.onlinetime.api.OnlineTimeWorld;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class OnlineTimeConfig {

    private final FileConfiguration cfg;
    private final OnlineTimePlugin plugin;
    OnlineTimeConfig(OnlineTimePlugin plugin) {
        plugin.saveDefaultConfig();
        this.cfg = plugin.getConfig();
        this.plugin = plugin;
        load();
    }

    private List<String> blacklistedWorlds;

    public void load() {
        blacklistedWorlds = cfg.getStringList("blacklisted-worlds");
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        load();
    }

    public void saveConfig() {
        cfg.set("blacklisted-worlds", blacklistedWorlds);
        plugin.saveConfig();
    }

    public List<String> getBlacklistedWorlds() {
        return blacklistedWorlds;
    }

    public boolean isBlacklisted(String name) {
        for (String s : blacklistedWorlds) {
            if (name.equals(s)) return true;
        }
        return false;
    }

    public boolean isBlacklisted(OnlineTimeWorld world) {
        return isBlacklisted(world.getName());
    }

}
