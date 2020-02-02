package io.github.wesauis.freezeweather;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WeatherManager implements Listener, CommandExecutor {
    public Config config;
    private Weather weather;
    private boolean locked;

    public WeatherManager(JavaPlugin plugin) {
        config = new Config(plugin, "cache");

        weather = Weather.valueOf(config.getString("WEATHER_STATE"));
        locked = config.getBoolean("LOCKED");
    }

    private Weather getWeather() {
        if (Bukkit.getWorlds().get(0).isThundering()) {
            return Weather.THUNDER;
        } else if (Bukkit.getWorlds().get(0).hasStorm()) {
            return Weather.RAIN;
        } else {
            return Weather.CLEAR;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        locked = !locked;
        config.saveFile();

        sender.sendMessage("weather mode -> " + (locked ? weather.toString().toLowerCase() : "default"));
        return true;
    }

    public void disable() {
        config.setString("WEATHER_STATE", weather.toString());
        config.setBoolean("LOCKED", locked);
        config.saveFile();
    }

    @EventHandler
    public void onThunder(ThunderChangeEvent event) {
        if (locked) {
            event.setCancelled(true);
        } else {
            weather = getWeather();
        }
    }

    @EventHandler
    public void onRain(WeatherChangeEvent event) {
        if (locked) {
            event.setCancelled(true);
        } else {
            weather = getWeather();
        }
    }


    private enum Weather {
        CLEAR, RAIN, THUNDER
    }
}

