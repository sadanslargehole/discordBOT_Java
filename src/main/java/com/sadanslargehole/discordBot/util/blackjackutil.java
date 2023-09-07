package com.sadanslargehole.discordBot.util;

import com.sadanslargehole.discordBot.commands.blackjackinfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static com.sadanslargehole.discordBot.commands.blackjackinfo.*;

/**
 * this is probably some of the worst code ive ever written
 * but it works:tm:
 */
public class blackjackutil {
    public static ArrayList<Card> newDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for(String suit : blackjackinfo.suits){
                for (Integer num: blackjackinfo.nums) {
                    deck.add(new Card(suit, num, false,null,false));
                }
                deck.add(new Card(suit, 11, true, "A", false));
                deck.add(new Card(suit, 10, false, "K", false));
                deck.add(new Card(suit, 10, false, "Q", false));
                deck.add(new Card(suit, 10, false, "J", false));
            }
        }
        return deck;
    }
    public static Card deal(ArrayList<Card> deck){
        return deck.remove(ThreadLocalRandom.current().nextInt(0, deck.size()));
    }
    public static ArrayList<Card> playerHit(Card hitCard, ArrayList<Card> playerHand){
        Integer playerHandValue = value(playerHand);
        if (hitCard.ace && hitCard.value + playerHandValue > 21){
            playerHand.add(new Card(hitCard.suit, 1 ,true, "A", false));
        } else {
            playerHand.add(hitCard);
        }
        value(playerHand);
        return playerHand;
    }
    public static ArrayList<Card> dealerPlay(Card hitCard, ArrayList<Card> dealerHand){
        if (hitCard.ace && hitCard.value+ value(dealerHand) > 21){
            dealerHand.add(new Card(hitCard.suit, 1, true, "A", false));
        }else {
            dealerHand.add(hitCard);
        }
        return dealerHand;
    }

    public static Integer value(ArrayList<Card> playerHand) {
        ArrayList<Card> aces = new ArrayList<>();
        Integer total = 0;
        for (Card card: playerHand){
            if (card.hidden){
                continue;
            }
            if (card.ace){
                aces.add(card);
                continue;
            }
            total += card.value;
        }
        if (aces.size() >= 1){
            if (aces.size() == 1){
                if (total+11>21){
                    //TODO: can you make this faster by removing the ace flag on this card?
                    aces.get(0).value =1;
                    return total + 1;
                }else {
                    return total + 11;
                }
            } else {
                Integer a = aces.get(0).value + aces.size() -1+ total;
                if (a<=21){
                    aces.remove(0);
                    for (Card ace : aces) {
                        ace.value = 1;
                    }
                    if (total !=0){
                        return 11+total+aces.size();
                    }else return 11+aces.size();
                }else {
                    if(aces.size()+total>21){
                        for (Card ace : aces) {
                            ace.value =1;
                        } return total + aces.size();
                    }
                    return a;
                }
                //if all the aces as ones are greater than 21 then they have to be ones

            }
        }
        return total;
    }
    public static Integer valueWithHidden(ArrayList<Card> playerHand) {
        ArrayList<Card> aces = new ArrayList<>();
        Integer total = 0;
        for (Card card: playerHand){
            if (card.ace){
                aces.add(card);
                continue;
            }
            total += card.value;
        }
        if (aces.size() >= 1){
            if (aces.size() == 1){
                if (total+11>21){
                    //TODO: can you make this faster by removing the ace flag on this card?
                    aces.get(0).value =1;
                    return total + 1;
                }else {
                    return total + 11;
                }
            } else {
                Integer a = aces.get(0).value + aces.size() -1+ total;
                if (a<=21){
                    aces.remove(0);
                    for (Card ace : aces) {
                        ace.value = 1;
                    }
                    if (total !=0){
                        return 11+total+aces.size();
                    }else return 11+aces.size();
                }else {
                    if(aces.size()+total>21){
                        for (Card ace : aces) {
                            ace.value =1;
                        } return total + aces.size();
                    }
                    return a;
                }
                //if all the aces as ones are greater than 21 then they have to be ones

            }
        }
        return total;
    }
    public static ArrayList<Card> dealerFinish(ArrayList<Card> dealerHand ,ArrayList<Card> deck){
        Integer aaa = 0;
        while (value(dealerHand)<17&&aaa<20){
            dealerPlay(deal(deck), dealerHand);
            aaa++;
        }
        return dealerHand;
    }
    private static String formatCards(ArrayList<Card> hand){
        //TODO: better way to do this?
        ArrayList<String> tmp = new ArrayList<>();
        for (Card card : hand) {
            tmp.add(card.name);
        }
        return String.join(" | ", tmp);
    }
    private static void unhideHand(ArrayList<Card> hand){
        for (Card card : hand) {
            card.hidden = false;
            card.name = String.format("%s %s", card.name, card.suit);
        }
    }

    public static String formatEntireMessage(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        return formatEntireMessage(playerHand, dealerHand, false);
    }

    public static String formatEntireMessage(ArrayList<Card> playerHand, ArrayList<Card> dealerHand, Boolean unhide){
        if (unhide) unhideHand(dealerHand);
        return String.format("Player's hand: %s (total %o)\nDealer's hand: %s (total %o)", formatCards(playerHand), value(playerHand), formatCards(dealerHand), value(dealerHand));

    }

    /**
     *
     * @param playerHand the players hand
     * @param dealerHand the dealers hand
     * @param state the state of the embed found in embedState.
     * <p>
     * embedState.GAME is used to continue the game
     * </p>
     * @see embedState
     * @return a built discord embed ready to be sent
     */
    public static MessageEmbed formatEmbed(ArrayList<Card> playerHand, ArrayList<Card> dealerHand, embedState state) {
        String title = null;
        Integer color = 10070709;
        String blackjack = "Thanks For Playing! made by <@521819891141967883>";
        if (state != embedState.GAME) unhideHand(dealerHand);
        switch (state){
            case WIN:
                title = "You Win!";
                color = 5763719;
                // TODO: 9/6/2023 add color
                break;
            case GAME:
                title = "Blackjack!";
                blackjack = "🃏 HIT | 🖐️ STAND";
                // TODO: 9/6/2023 add color
                break;
            case LOSS:
                title = "You Lose";
                color =15548997;
                // TODO: 9/6/2023 add color
                break;
            case PUSH:
                title = "Push";
                // TODO: 9/6/2023 add color
                break;
            default:
                throw new RuntimeException("oops :3");

        }
        return new EmbedBuilder().
                setColor(color).
                addField(new MessageEmbed.Field(title, formatEntireMessage(playerHand ,dealerHand), false)).
                setFooter(blackjack).build();
    }
    public static void handleError(MessageReceivedEvent ctx, String error, String desc){
        ctx.getMessage().reply(MessageCreateData.fromEmbeds(new EmbedBuilder().setTitle(error).setDescription(desc).build()));
    }
    public static void clearGame(String userID){
        decks.remove(userID);
        bets.remove(userID);
        activeGame.remove(userID);
        activeGameBot.remove(userID);
        playerHands.remove(userID);
        dealerHands.remove(userID);

    }

}
