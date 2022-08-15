package me.justamood.onlinetime.api.impl.bukkit_1_13;

import me.justamood.onlinetime.api.OnlineTimeWorld;
import org.bukkit.GameRule;
import org.bukkit.World;

public class OnlineTimeWorld_1_13 implements OnlineTimeWorld {

    private final World bukkitWorld;
    public OnlineTimeWorld_1_13(World world) {
        bukkitWorld = world;
    }

    public World getBukkitWorld() {
        return bukkitWorld;
    }

    @Override
    public String getName() {
        return bukkitWorld.getName();
    }

    @Override
    public void setDoDaylightCycle(boolean doDaylightCycle) {
        bukkitWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, doDaylightCycle);
    }

    @Override
    public int getPlayerCount() {
        return bukkitWorld.getPlayers().size();
    }

}
