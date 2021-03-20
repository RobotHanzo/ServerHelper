package dev.robothanzo.serverhelper.listeners;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaving implements Listener {
    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event){
        TextComponent welcomeBroadcastComp = new TextComponent(
                TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes(
                                '&', "&6&l" + event.getPlayer().getName() +" &3&l離開了伺服器")));
        Bukkit.broadcast(welcomeBroadcastComp);
        event.setQuitMessage(null);
    }
}
