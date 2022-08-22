package me.justamood.onlinetime.api;

import io.papermc.lib.PaperLib;

import java.util.Collection;

public abstract class OnlineTimeAPI {

    private static OnlineTimeAPI INSTANCE = null;
    public static OnlineTimeAPI get() {
        if (INSTANCE == null) {
            if (PaperLib.isVersion(13)) {
                INSTANCE = new me.justamood.onlinetime.api.impl.bukkit_1_13.OnlineTimeAPI_1_13();
            } else {
                INSTANCE = new me.justamood.onlinetime.api.impl.bukkit_1_8.OnlineTimeAPI_1_8();
            }
        }
        return INSTANCE;
    }

    public abstract int getOnlinePlayers();

    public abstract Collection<OnlineTimeWorld> getWorlds();

    public abstract OnlineTimeWorld getWorld(String name);

}
