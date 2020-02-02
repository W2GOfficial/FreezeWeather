package io.github.wesauis.freezeweather;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Config {
    private final File file;
    private final FileConfiguration config;

    public Config(JavaPlugin plugin, String name) {
        String filename = name + ".yml";

        file = new File(plugin.getDataFolder(), filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(filename, false);
        }

        config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public void setString(String path, String value) {
        config.set(path, value);
    }

    public void setBoolean(String path, boolean value) {
        config.set(path, value);
    }

    public void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}
