package com.sadanslargehole.discordBot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class CommandBase {
    public ArrayList<String> commandNames;
    //cooldown map for cooldowns
    public HashMap<String, Long> commandCooldown = new HashMap<>();

    public CommandBase(ArrayList<String> commandName) {
        this.commandNames = commandName;
    }

    public abstract void run(ShardManager bot, MessageReceivedEvent ctx, ArrayList<String> args);

}
