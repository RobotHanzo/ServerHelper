package dev.robothanzo.serverhelper;

import dev.robothanzo.serverhelper.commands.ConvertHoldingItem;
import dev.robothanzo.serverhelper.commands.InstallResourcePack;
import dev.robothanzo.serverhelper.items.RegistrationService;
import dev.robothanzo.serverhelper.listeners.InventoryOpening;
import dev.robothanzo.serverhelper.listeners.PlayerJoining;
import dev.robothanzo.serverhelper.listeners.PlayerLeaving;
import dev.robothanzo.serverhelper.listeners.ResourcePackStatus;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ServerHelper extends JavaPlugin implements SlimefunAddon {
    private static ServerHelper instance;

    public static ServerHelper inst() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerJoining(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaving(), this);
        getServer().getPluginManager().registerEvents(new ResourcePackStatus(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpening(), this);
        this.getCommand("installresourcepack").setExecutor(new InstallResourcePack());
        this.getCommand("convertholdingitem").setExecutor(new ConvertHoldingItem());
        this.getCommand("chi").setExecutor(new ConvertHoldingItem());
        RegistrationService.register(this);
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
}
