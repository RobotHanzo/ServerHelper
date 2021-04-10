package dev.robothanzo.serverhelper.commands;

import dev.robothanzo.serverhelper.ServerHelper;

import javax.annotation.Nonnull;

public class CommandsRegistrationService {
    public static void register(@Nonnull ServerHelper plugin) {
        plugin.getCommand("installresourcepack").setExecutor(new InstallResourcePack());
    }
}
