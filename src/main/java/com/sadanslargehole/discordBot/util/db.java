package com.sadanslargehole.discordBot.util;

import java.util.HashMap;

public class db {
    private static final HashMap<String, Double> bank = new HashMap<>();
    public static final Double initValue = 15000D;

    public static Double get(String userID){
        bank.putIfAbsent(userID, initValue);
        return bank.get(userID);
    }
    public static void add(String userID, Double ammount){
        bank.putIfAbsent(userID, initValue);
        bank.put(userID, bank.get(userID)+ ammount);
    }
    public static void take(String userID, Double ammount){
        if(has(userID, ammount)){
            bank.put(userID, get(userID)-ammount);
        }
    }

    private static boolean has(String userID, Double ammount) {
        return get(userID)>ammount;
    }
    public static void set(String userID, Double ammount){
        bank.put(userID, ammount);
    }


}
