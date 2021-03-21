package dev.robothanzo.serverhelper.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;



import org.bukkit.event.inventory.InventoryOpenEvent;

import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoining implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (ItemStack item : event.getPlayer().getInventory().getContents()) {

            SlimefunItem toConvert = SlimefunItem.getByItem(item);

            if (toConvert == null) {

                continue;

            }

            ItemMeta im = item.getItemMeta();

            SlimefunPlugin.getItemTextureService().setTexture(im, toConvert.getId());

            item.setItemMeta(im);

        }
        TextComponent welcomeComp = new TextComponent(
                TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes(
                                '&', "&b&l歡迎來到伺服器")));
        event.getPlayer().spigot().sendMessage(welcomeComp);

        TextComponent resourceComp = new TextComponent(
                TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes(
                                '&', "&6&l建議你安裝自訂伺服器材質包，需要OptiFine。點擊此文字以安裝")));
        resourceComp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/installresourcepack"));
        event.getPlayer().spigot().sendMessage(resourceComp);

        TextComponent permanentResourceComp = new TextComponent(
                TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes(
                                '&', "&a&l若欲下載並永久安裝，請點擊本行文字")));
        permanentResourceComp.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://cdn.robothanzo.dev/slimefun.zip"));
        event.getPlayer().spigot().sendMessage(permanentResourceComp);

        TextComponent welcomeBroadcastComp = new TextComponent(
                TextComponent.fromLegacyText(
                        ChatColor.translateAlternateColorCodes(
                                '&', "&6&l" + event.getPlayer().getName() +" &3&l加入了伺服器")));
        Bukkit.broadcast(welcomeBroadcastComp);
        event.setJoinMessage(null);
    }
}
