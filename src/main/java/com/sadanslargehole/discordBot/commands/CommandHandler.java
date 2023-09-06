package com.sadanslargehole.discordBot.commands;

import com.sadanslargehole.discordBot.discordBot;
import com.sadanslargehole.discordBot.ownerOnly;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandHandler extends ListenerAdapter {
    public static CommandList commands = new CommandList();
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.getMessage().getContentRaw().startsWith(discordBot.Prefix)) return;
        //strip the prefix and convert to array of args
        ArrayList<String> command = new ArrayList<>(Arrays.asList(event.getMessage().getContentRaw().replaceFirst("^`", "").split(" ") ));
        for (CommandBase commandToRun:
             commands.commands) {
            System.out.println("checking if we have the command");
            if(commandToRun.commandNames.contains(command.get(0))){
                System.out.println("we are checking if they can run it");
                if(canRunCommand(event.getAuthor(), commandToRun)){
                    System.out.println("we are about to run the command");
                    //FIXME: there has to be a better way to pass the array
                    commandToRun.run(discordBot.getShartManager(), event, String.join(" ", command).split(" "));
                }else {
                    event.getMessage().reply("error").queue();
                }
            }
        }
    }
    private <T extends CommandBase> boolean canRunCommand(User author, T command){
        //TODO: add cooldown
        if(command.getClass().isAnnotationPresent(ownerOnly.class)){
            return author.getId().equals(discordBot.ownerID);
        }
        return true;
    }
}