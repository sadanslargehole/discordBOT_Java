package com.sadanslargehole.discordBot.util;

public class Card {
    public String suit;
    public Integer value;
    public Boolean ace;
    public String symbol;
    public String name;
    public Boolean hidden;
    public Card(String suit, Integer value, Boolean ace, String symbol, Boolean hidden) {
        this.suit = suit;
        this.value = value;
        this.ace = ace;
        this.symbol = symbol== null ? String.valueOf(value) : symbol;
        this.name = String.format("%s %s", this.symbol, this.suit);
        this.hidden = hidden;
        if (this.hidden) this.name = "?? of ?";
    }
}
