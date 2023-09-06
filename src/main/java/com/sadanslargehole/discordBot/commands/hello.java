package com.sadanslargehole.discordBot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.ArrayList;

public class hello extends CommandBase {
    public hello(ArrayList<String> commandName) {
        super(commandName);
    }

    @Override
    public void run(ShardManager bot, MessageReceivedEvent ctx, ArrayList<String> args) {

    }
}
