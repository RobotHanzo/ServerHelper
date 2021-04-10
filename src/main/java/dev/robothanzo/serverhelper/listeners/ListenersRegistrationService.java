package dev.robothanzo.serverhelper.listeners;

import dev.robothanzo.serverhelper.ServerHelper;

import javax.annotation.Nonnull;

public class ListenersRegistrationService {
    public static void register(@Nonnull ServerHelper plugin) {
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoining(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerLeaving(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ResourcePackStatus(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new InventoryOpening(), plugin);
    }
}
