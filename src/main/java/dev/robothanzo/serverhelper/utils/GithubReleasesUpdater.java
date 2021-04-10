package dev.robothanzo.serverhelper.utils;

import dev.robothanzo.serverhelper.ServerHelper;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.Updater;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class GithubReleasesUpdater implements Updater {
    protected static URL RELEASE_API_URL;
    protected static ServerHelper PLUGIN;
    protected static String CURRENT_VERSION;
    protected static File FILE;

    public GithubReleasesUpdater(@Nonnull ServerHelper plugin, @Nonnull File file) {
        try {
            RELEASE_API_URL = new URL("https://api.github.com/repos/RobotHanzo/ServerHelper/releases/latest");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        PLUGIN = plugin;
        CURRENT_VERSION = plugin.getDescription().getVersion();
        FILE = file;
    }

    public boolean shouldUpdate(@Nonnull String currentVersion, @Nonnull String remoteVersion) {
        String[] parsedCurrentVersion;
        String[] parsedRemoteVersion;
        parsedCurrentVersion = currentVersion.split("\\.");
        parsedRemoteVersion = remoteVersion.split("\\.");
        for (int i = 0; i < parsedCurrentVersion.length; i++) {
            if (Integer.parseInt(parsedCurrentVersion[i]) < Integer.parseInt(parsedRemoteVersion[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Plugin getPlugin() {
        return PLUGIN;
    }

    @Override
    public File getFile() {
        return FILE;
    }

    @Override
    public String getLocalVersion() {
        return CURRENT_VERSION;
    }

    @Override
    public int getTimeout() {
        return 10000;
    }

    @Override
    public void start() {
        PLUGIN.getServer().getScheduler().runTask(PLUGIN, () -> {
            Thread thread = new Thread(new UpdaterTask(this, RELEASE_API_URL), "Updater Task");
            thread.start();
        });
    }
}
