package dev.robothanzo.serverhelper.commands.subcommands;

import dev.robothanzo.serverhelper.ServerHelper;
import dev.robothanzo.serverhelper.commands.MainCommand;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class Subcommands {

    private Subcommands() {
    }

    public static Collection<SubCommandBase> getAllCommands(MainCommand cmd) {
        ServerHelper plugin = cmd.getPlugin();
        List<SubCommandBase> commands = new LinkedList<>();

        commands.add(new Update(plugin, cmd));

        return commands;
    }
}