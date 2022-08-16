package me.justamood.onlinetime.api.impl.bukkit_1_8;

import me.justamood.onlinetime.api.OnlineTimeWorld;
import org.bukkit.World;

public class OnlineTimeWorld_1_8 implements OnlineTimeWorld {

    private final World bukkitWorld;
    public OnlineTimeWorld_1_8(World world) {
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
        bukkitWorld.setGameRuleValue("doDaylightCycle", doDaylightCycle ? "true" : "false");
    }

    @Override
    public int getPlayerCount() {
        return bukkitWorld.getPlayers().size();
    }

}
