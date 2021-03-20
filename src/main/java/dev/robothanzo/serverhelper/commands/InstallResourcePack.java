package dev.robothanzo.serverhelper.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InstallResourcePack implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setResourcePack("https://cdn.robothanzo.dev/slimefun.zip", "AFBFB561BF71561D7764786FDA0B5A41571AA3B5".toLowerCase());
        }
        return true;
    }
}
