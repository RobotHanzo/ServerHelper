package dev.robothanzo.serverhelper.commands;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ConvertHoldingItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack toConvertIS = player.getInventory().getItemInMainHand().clone();
            SlimefunItem toConvert = SlimefunItem.getByItem(toConvertIS);
            if (toConvert == null) {
                return true;
            }
            ItemMeta im = toConvertIS.getItemMeta();
            SlimefunPlugin.getItemTextureService().setTexture(im, toConvert.getId());
            toConvertIS.setItemMeta(im);
            player.getInventory().setItemInMainHand(toConvertIS);
        }
        return true;
    }
}
