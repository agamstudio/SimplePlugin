package me.Agam.simplePlugins;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {

    String msg = "";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) /* בודק אם מי שהזין את הפקודה הוא שחקן */ {
            sender.sendMessage("הפקודה יכולה להיות משומשת על ידי שחקן בלבד!");
            return false;
        }

        if (args.length <= 0) {

            sender.sendMessage("אתה צריך להזין שם של שחקן");

            return false;

        }

        if (Bukkit.getPlayer(args[0]) == sender) {
            sender.sendMessage("אינך יכול לשלוח הודעה לעצמך.");
            return false;
        }

        msg = "";
        if (Bukkit.getPlayer(args[0]) == null) {
            sender.sendMessage("השחקן אותו הזנת לא נמצא בשרת!");
            return false;
        }

        else {
            for (int i = 1; i < args.length; i++){
                msg = msg + args[i] + " ";
            }
            Player target = Bukkit.getPlayer(args[0]);
            target.sendMessage(msg);
            sender.sendMessage("ל-" + target.getName() + ": " + msg);

        }

        return true;
    }
}