package com.sadanslargehole.discordBot.commands;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandList {
    public ArrayList<CommandBase> commands = new ArrayList<>();

    public CommandList() {
        // TODO: make this a hash map with entries for each
        commands.add(new exit(new ArrayList<>(Arrays.asList("quit", "exit"))));
        commands.add(new hello(new ArrayList<>(Arrays.asList("hello", "hi"))));
        commands.add(new test(new ArrayList<>(Arrays.asList("test"))));
    }
}
