package dev.robothanzo.serverhelper.listeners;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

public class ResourcePackStatus implements Listener {
    @EventHandler
    public static void onResourcePackStatusChanged(PlayerResourcePackStatusEvent event) {
        if (Status.SUCCESSFULLY_LOADED == event.getStatus()) {
            TextComponent resultMsg = new TextComponent(
                    TextComponent.fromLegacyText(
                            ChatColor.translateAlternateColorCodes(
                                    '&', "&a&l材質包安裝成功")));
            event.getPlayer().spigot().sendMessage(resultMsg);
        }
        if (Status.DECLINED == event.getStatus()) {
            TextComponent resultMsg = new TextComponent(
                    TextComponent.fromLegacyText(
                            ChatColor.translateAlternateColorCodes(
                                    '&', "&c&l材質包安裝失敗，您已拒絕材質包安裝要求")));
            event.getPlayer().spigot().sendMessage(resultMsg);
        }
        if (Status.FAILED_DOWNLOAD == event.getStatus()) {
            TextComponent resultMsg = new TextComponent(
                    TextComponent.fromLegacyText(
                            ChatColor.translateAlternateColorCodes(
                                    '&', "&e&l材質包安裝失敗，下載失敗")));
            event.getPlayer().spigot().sendMessage(resultMsg);
        }
    }
}
