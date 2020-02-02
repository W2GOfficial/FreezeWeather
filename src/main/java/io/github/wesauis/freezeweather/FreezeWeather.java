package io.github.wesauis.freezeweather;

import org.bukkit.plugin.java.JavaPlugin;

public final class FreezeWeather extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Successfully disabled!");
    }
}
