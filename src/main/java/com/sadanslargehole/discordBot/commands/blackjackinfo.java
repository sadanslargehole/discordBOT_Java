package com.sadanslargehole.discordBot.commands;

import com.sadanslargehole.discordBot.util.Card;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class blackjackinfo {
    public static final ArrayList<String> suits = new ArrayList<>(Arrays.asList("<:spades:936447656463597568>", "<:hearts:936447656480350249>",
            "<:diamonds:936447656589398066>", "<:clubs:936447656199348256>"));
    public static final ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    public static HashMap<String, MessageReceivedEvent> activeGame = new HashMap<>();
    public static HashMap<String, Message> activeGameBot = new HashMap<String, net.dv8tion.jda.api.entities.Message>();
    public static HashMap<String, ArrayList<Card>> playerHands = new HashMap<>();
    public static HashMap<String, ArrayList<Card>> dealerHands = new HashMap<>();
    public static HashMap<String, Double> bets = new HashMap<>();
    public static HashMap<String, ArrayList<Card>> decks = new HashMap<>();
}
