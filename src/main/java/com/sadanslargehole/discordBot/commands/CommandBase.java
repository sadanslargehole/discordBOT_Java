package com.sadanslargehole.discordBot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.ArrayList;

public abstract class CommandBase {
    public ArrayList<String> commandNames;

    public CommandBase(ArrayList<String> commandName) {
        this.commandNames = commandName;
    }

    public abstract void run(ShardManager bot, MessageReceivedEvent ctx, String[] args);


}
