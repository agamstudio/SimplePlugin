package me.Agam.simplePlugins;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SimplePlugins extends JavaPlugin {

    private File warpsFile;
    private FileConfiguration warpsConfig;

    public void saveWarps() {
        try {
            warpsConfig.save(warpsFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getWarpsConfig() { return warpsConfig; }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Events(), this);
        System.out.println("הפלאגין הופעל בהצלחה!");
        this.getCommand("msg").setExecutor(new MsgCommand());
        this.getCommand("warp").setExecutor(new CMD_warp());
        this.getCommand("setwarp").setExecutor(new CMD_setwarp());

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        warpsFile = new File(getDataFolder(), "warps.yml");

        if (!warpsFile.exists()) {
            saveResource("warps.yml", false);
        }

        warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("הפלאגין כבוי!");
    }
}
