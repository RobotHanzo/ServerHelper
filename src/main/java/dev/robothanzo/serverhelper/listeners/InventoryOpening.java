package dev.robothanzo.serverhelper.listeners;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryOpening implements Listener {
    @EventHandler
    public static void onInventoryOpened(InventoryOpenEvent event) {
        for (ItemStack item : event.getInventory().getContents()) {
            SlimefunItem toConvert = SlimefunItem.getByItem(item);
            if (toConvert == null) {
                continue;
            }
            ItemMeta im = item.getItemMeta();
            SlimefunPlugin.getItemTextureService().setTexture(im, toConvert.getId());
            item.setItemMeta(im);
        }
    }
}
