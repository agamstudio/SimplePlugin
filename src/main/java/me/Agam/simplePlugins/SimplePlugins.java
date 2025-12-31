package me.Agam.simplePlugins;

import me.Agam.simplePlugins.commands.DeletewarpCommand;
import me.Agam.simplePlugins.commands.SetwarpCommand;
import me.Agam.simplePlugins.commands.WarpCommand;
import me.Agam.simplePlugins.commands.MsgCommand;
import me.Agam.simplePlugins.events.ShiftEvent;
import me.Agam.simplePlugins.warps.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class SimplePlugins extends JavaPlugin {

    public HashMap<String, Warp> warps = new HashMap<>();

    private File warpsFile;
    private FileConfiguration warpsConfig;

    public void saveWarps() {
        try {
            warpsConfig.save(warpsFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getWarpsConfig() {
        return warpsConfig;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ShiftEvent(), this);
        System.out.println("הפלאגין הופעל בהצלחה!");
        this.getCommand("msg").setExecutor(new MsgCommand());
        this.getCommand("warp").setExecutor(new WarpCommand());
        this.getCommand("setwarp").setExecutor(new SetwarpCommand());
        this.getCommand("deletewarp").setExecutor(new DeletewarpCommand());

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        warpsFile = new File(getDataFolder(), "warps.yml");

        if (!warpsFile.exists()) {
            saveResource("warps.yml", false);
        }

        warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);

        if (warpsConfig.contains("warps")) {
            for (String id : warpsConfig.getConfigurationSection("warps").getKeys(false)) {
                String worldName = warpsConfig.getString("warps." + id + ".world");
                double x = warpsConfig.getDouble("warps." + id + ".x");
                double y = warpsConfig.getDouble("warps." + id + ".y");
                double z = warpsConfig.getDouble("warps." + id + ".z");
                float yaw = (float) warpsConfig.getDouble("warps." + id + ".yaw");
                float pitch = (float) warpsConfig.getDouble("warps." + id + ".pitch");

                World world = Bukkit.getWorld(worldName);
                if (world != null) {
                    Location loc = new Location(world, x, y, z, yaw, pitch);
                    warps.put(id, new Warp(id, loc));
                }
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        warpsConfig.set("warps", null);

        for (String id : warps.keySet()) {
            Warp warp = warps.get(id);
            Location loc = warp.getLocation();
            warpsConfig.set("warps." + id + ".world", loc.getWorld().getName());
            warpsConfig.set("warps." + id + ".x", loc.getX());
            warpsConfig.set("warps." + id + ".y", loc.getY());
            warpsConfig.set("warps." + id + ".z", loc.getZ());
            warpsConfig.set("warps." + id + ".yaw", loc.getYaw());
            warpsConfig.set("warps." + id + ".pitch", loc.getPitch());
        }

        saveWarps();
        System.out.println("הפלאגין כבוי!");
    }

}
