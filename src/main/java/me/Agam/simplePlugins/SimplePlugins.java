package me.Agam.simplePlugins;

import org.bukkit.plugin.java.JavaPlugin;

public final class SimplePlugins extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Events(), this);
        System.out.println("הפלאגין הופעל בהצלחה!");
        this.getCommand("msg").setExecutor(new MsgCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("הפלאגין כבוי!");
    }
}
