package dev.robothanzo.serverhelper;

import dev.robothanzo.serverhelper.commands.CommandsRegistrationService;
import dev.robothanzo.serverhelper.items.ItemsRegistrationService;
import dev.robothanzo.serverhelper.listeners.ListenersRegistrationService;
import dev.robothanzo.serverhelper.utils.GithubReleasesUpdater;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public final class ServerHelper extends JavaPlugin implements SlimefunAddon {
    private static ServerHelper instance;

    public static ServerHelper inst() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Config cfg = new Config(this);
        if (cfg.getBoolean("options.auto-update")) {
            new GithubReleasesUpdater(this, getFile()).start();
        }
        CommandsRegistrationService.register(this);
        ItemsRegistrationService.register(this);
        ListenersRegistrationService.register(this);
    }

    @Override
    public void onDisable() {
    }

    @NotNull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return null;
    }

    public File getPluginFile() {
        return getFile();
    }
}
