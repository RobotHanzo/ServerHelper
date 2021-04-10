package dev.robothanzo.serverhelper.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URL;
import java.util.function.Function;
import java.util.logging.Level;

// Most of the code are copied from TheBusyBiscuit's Updater, thanks!
public class UpdaterTask implements Runnable {
    private final GithubReleasesUpdater updater;
    private final URL url;
    private static Function<Boolean,Boolean> resultCallback;

    UpdaterTask(@Nonnull GithubReleasesUpdater updater, @Nonnull URL url, Function<Boolean, Boolean> callback) {
        this.url = url;
        this.updater = updater;
        resultCallback = callback;
    }

    public void run() {
        try {
            String response = new HTTPGet(url, updater.getTimeout()).get();
            JsonObject json = (JsonObject) (new JsonParser()).parse(response);
            if (json == null) {
                updater.getPlugin().getLogger().log(Level.WARNING, "The Auto-Updater could not connect to github.io, is it down?");
            } else {
                String remoteVersion = json.get("name").getAsString();
                URL downloadURL = new URL(json.getAsJsonArray("assets")
                        .get(0).getAsJsonObject().get("browser_download_url").getAsString());
                if (updater.shouldUpdate(updater.getLocalVersion(), remoteVersion)) {
                    updater.getPlugin().getLogger().log(Level.INFO, ChatColor.GOLD + "Found update (" + updater.getLocalVersion() + " -> " + remoteVersion + "), updating...");
                    new HTTPGet(downloadURL, updater.getTimeout()).getToFile("plugins/" + Bukkit.getUpdateFolder(), updater.getFile().getName());
                    resultCallback.apply(true);
                } else {
                    updater.getPlugin().getLogger().log(Level.INFO, ChatColor.GREEN + "The current version (" + updater.getLocalVersion() + ") is the latest. No updates needed");
                    resultCallback.apply(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
