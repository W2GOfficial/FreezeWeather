package io.github.wesauis.freezeweather;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FreezeWeather extends JavaPlugin {
    private WeatherManager weatherManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        weatherManager = new WeatherManager(this);
        getCommand("freezeweather").setExecutor(weatherManager);
        Bukkit.getServer().getPluginManager().registerEvents(weatherManager, this);

        getLogger().info("Successfully enabled!");
    }

    @Override
    public void onDisable() {
        weatherManager.disable();

        getLogger().info("Successfully disabled!");
    }
}
