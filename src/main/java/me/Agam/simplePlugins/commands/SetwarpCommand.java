package me.Agam.simplePlugins.commands;

import me.Agam.simplePlugins.SimplePlugins;
import me.Agam.simplePlugins.warps.Warp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SetwarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        SimplePlugins plugin = JavaPlugin.getPlugin(SimplePlugins.class);
        if (!(sender instanceof Player)) {
            sender.sendMessage("הפקודה יכולה להיות משומשת על ידי שחקן בלבד!");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("אתה צריך להזין מזהה לווארפ");
            return false;
        }

        if (args.length >= 2){
            sender.sendMessage("אינך יכול להזין יותר ממזהה אחת");
            return false;
        }
        Player player = ((Player) sender);
        if (args.length == 1) {
            player = (Player) sender;
            Location loc = player.getLocation();
            String id = args[0];
            Warp warp = new Warp(id, loc);
            plugin.warps.put(id, warp);
            sender.sendMessage("הווארפ " + id + " נשמר בהצלחה!");
            return true;
        }
        return false;
    }
}
