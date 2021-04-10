package dev.robothanzo.serverhelper.commands.subcommands;

import dev.robothanzo.serverhelper.ServerHelper;
import dev.robothanzo.serverhelper.commands.MainCommand;
import dev.robothanzo.serverhelper.utils.GithubReleasesUpdater;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public class Update extends SubCommandBase {
    protected Update(ServerHelper plugin, MainCommand cmd) {
        super(plugin, cmd, "update", false);
    }

    @Override
    public void onExecute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        GithubReleasesUpdater gru = new GithubReleasesUpdater(plugin, plugin.getPluginFile());
        gru.start();
        if (gru.isUpdated()) {
            sender.sendMessage(ChatColor.YELLOW + "An update has been found, downloaded and queued for update, please restart the server to apply the update.");
        } else {
            sender.sendMessage(ChatColor.GREEN + "No updates found, this should be the latest version!");
        }
    }

    @Nonnull
    @Override
    public String getDescription() {
        return "Checks for plugin update and updates if there is a newer version";
    }
}
