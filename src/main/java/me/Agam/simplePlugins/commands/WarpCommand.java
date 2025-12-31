package me.Agam.simplePlugins.commands;

import me.Agam.simplePlugins.SimplePlugins;
import me.Agam.simplePlugins.warps.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        SimplePlugins plugin = JavaPlugin.getPlugin(SimplePlugins.class);

        if (!(sender instanceof Player)) {
            sender.sendMessage("הפקודה יכולה להיות משומשת על ידי שחקן בלבד!");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("אתה צריך להזין מזהה לווארפ כדי להשתגר אליו");
            return false;
        }

        if (args.length >= 2){
            sender.sendMessage("אינך יכול להזין יותר ממזהה אחת");
            return false;
        }
        String id = args[0];

        if (!plugin.warps.containsKey(id)) {
            sender.sendMessage("אין ווארפ עם המזהה הזה!");
            return false;
        }

        Player player = (Player) sender;
        Warp warp = plugin.warps.get(id);
        Location loc = warp.getLocation();

        World world = loc.getWorld();
        if (world == null) {
            sender.sendMessage("העולם של הווארפ לא קיים!");
            return false;
        }

        player.teleport(loc);
        sender.sendMessage("השתגרת לווארפ " + id + " בהצלחה!");
        return true;
    }
}