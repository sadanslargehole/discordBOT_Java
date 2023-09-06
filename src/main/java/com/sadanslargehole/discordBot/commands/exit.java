package com.sadanslargehole.discordBot.commands;

import com.sadanslargehole.discordBot.ownerOnly;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.ArrayList;

@ownerOnly
public class exit extends CommandBase {
    public String[] commandName;

    public exit(ArrayList<String> commandName) {
        super(commandName);
    }

    @Override
    public void run(ShardManager bot, MessageReceivedEvent ctx, ArrayList<String> args) {

    }


}
