package com.sadanslargehole.discordBot.commands;

import com.sadanslargehole.discordBot.CommandCooldown;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.ArrayList;

@CommandCooldown(cooldownSeconds = 5)
public class test extends CommandBase {
    public test(ArrayList<String> commandName) {
        super(commandName);
    }

    @Override

    public void run(ShardManager bot, MessageReceivedEvent ctx, ArrayList<String> args) {
        System.out.println("we are here");
        ctx.getMessage().reply("the test works").queue();
    }
}
