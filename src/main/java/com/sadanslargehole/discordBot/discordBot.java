package com.sadanslargehole.discordBot;

import com.sadanslargehole.discordBot.commands.CommandHandler;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;
import java.util.HashMap;

public class discordBot {
    public static final String Prefix = "`";
    public static final String ownerID = "521819891141967883";
    public HashMap<String, Object> commandMap = new HashMap();

    private static ShardManager shartManager;
    public static ShardManager getShartManager(){
        return shartManager;
    }
    public static void main(String[] args) throws  LoginException{

        DefaultShardManagerBuilder b = DefaultShardManagerBuilder.createDefault("REPLACE WITHG TOKEN" ).enableIntents(EnumSet.allOf(GatewayIntent.class));
        b.setStatus(OnlineStatus.ONLINE);
        b.setActivity(Activity.playing("With Nobody"));
        b.addEventListeners(new Events());
        b.addEventListeners(new CommandHandler());

        shartManager = b.build();

    }
}
