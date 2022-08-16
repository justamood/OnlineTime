package me.justamood.onlinetime;

import me.justamood.onlinetime.api.OnlineTimeAPI;
import me.justamood.onlinetime.api.OnlineTimeWorld;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnlineTimeEvents implements Listener {

    private final OnlineTimeAPI api = OnlineTimeAPI.get();

    private void handle(World world, boolean joined) {
        OnlineTimeWorld otw = api.getWorld(world.getName());
        if (!OnlineTimePlugin.getPluginConfig().isBlacklisted(otw)) {
            if (joined) {
                otw.setDoDaylightCycle(true);
            } else {
                otw.setDoDaylightCycle(otw.getPlayerCount() - 1 > 0);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        handle(event.getPlayer().getWorld(), true);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        handle(event.getPlayer().getWorld(), false);
    }

}
