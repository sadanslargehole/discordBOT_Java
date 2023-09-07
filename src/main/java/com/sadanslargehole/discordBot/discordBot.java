package com.sadanslargehole.discordBot;

import com.google.gson.Gson;
import com.sadanslargehole.discordBot.commands.CommandHandler;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Objects;

public class discordBot {

    public static config config;
    public HashMap<String, Object> commandMap = new HashMap<>();

    private static ShardManager shartManager;
    public static ShardManager getShartManager(){
        return shartManager;
    }
    public static void main(String[] args) {
        Gson g = new Gson();
        config = g.fromJson(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("config.json"))), com.sadanslargehole.discordBot.config.class);
        DefaultShardManagerBuilder b = DefaultShardManagerBuilder.createDefault(config.token ).enableIntents(EnumSet.allOf(GatewayIntent.class));
        b.setStatus(OnlineStatus.ONLINE);
        b.setActivity(Activity.playing("With Nobody"));
        b.addEventListeners(new Events());
        b.addEventListeners(new CommandHandler());

        shartManager = b.build();

    }
}
