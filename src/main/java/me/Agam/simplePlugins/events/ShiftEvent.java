package me.Agam.simplePlugins.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ShiftEvent implements Listener {

    @EventHandler
    public void onShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (event.isSneaking()) {
            Bukkit.broadcastMessage(player.getName() + " עשה פלוץ, תצחקו עליו!!!");

        }
    }

    @EventHandler
    public void onServerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage("ברוך הבא לשרת!");
        Bukkit.broadcastMessage("השחקן " + player.getName() + " נכנס לשרת!");
    }

    @EventHandler
    public void onServerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("השחקן " + player.getName() + " יצא מהשרת!");
    }

}