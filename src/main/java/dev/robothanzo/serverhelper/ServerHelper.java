package dev.robothanzo.serverhelper;

import dev.robothanzo.serverhelper.commands.ConvertHoldingItem;
import dev.robothanzo.serverhelper.commands.InstallResourcePack;
import dev.robothanzo.serverhelper.listeners.InventoryOpening;
import dev.robothanzo.serverhelper.listeners.PlayerJoining;
import dev.robothanzo.serverhelper.listeners.PlayerLeaving;
import dev.robothanzo.serverhelper.listeners.ResourcePackStatus;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ServerHelper extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerJoining(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaving(), this);
        getServer().getPluginManager().registerEvents(new ResourcePackStatus(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpening(), this);
        this.getCommand("installresourcepack").setExecutor(new InstallResourcePack());
        this.getCommand("convertholdingitem").setExecutor(new ConvertHoldingItem());
        this.getCommand("chi").setExecutor(new ConvertHoldingItem());
    }

    @Override
    public void onDisable() {
    }
}
