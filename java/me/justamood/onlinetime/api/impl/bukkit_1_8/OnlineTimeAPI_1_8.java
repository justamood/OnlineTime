package me.justamood.onlinetime.api.impl.bukkit_1_8;

import me.justamood.onlinetime.api.OnlineTimeAPI;
import me.justamood.onlinetime.api.OnlineTimeWorld;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.stream.Collectors;

public class OnlineTimeAPI_1_8 extends OnlineTimeAPI {

    @Override
    public int getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().size();
    }

    @Override
    public Collection<OnlineTimeWorld> getWorlds() {
        return Bukkit.getWorlds().stream().map(OnlineTimeWorld_1_8::new).collect(Collectors.toList());
    }

    @Override
    public OnlineTimeWorld getWorld(String name) {
        return new OnlineTimeWorld_1_8(Bukkit.getWorld(name));
    }

}
