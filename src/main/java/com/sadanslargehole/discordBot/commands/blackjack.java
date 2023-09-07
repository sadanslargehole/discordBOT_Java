package com.sadanslargehole.discordBot.commands;

import com.sadanslargehole.discordBot.util.db;
import com.sadanslargehole.discordBot.util.embedState;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.ArrayList;
import java.util.Arrays;

import static com.sadanslargehole.discordBot.commands.CommandHandler.clearCooldown;
import static com.sadanslargehole.discordBot.commands.blackjackinfo.*;
import static com.sadanslargehole.discordBot.util.blackjackutil.*;

//@CommandCooldown(cooldownSeconds = 120)
public class blackjack extends CommandBase {

    public blackjack(ArrayList<String> commandName) {
        super(commandName);
    }

    @Override
    public void run(ShardManager bot, MessageReceivedEvent ctx, ArrayList<String> args) {
        System.out.println(args.size());
        String user = ctx.getAuthor().getId();
        Double bal = db.get(user);
        Double wager = null;
        if (args.size() != 2) {
            handleError(ctx, "too many args", "Usage: ``blackjack <wager>`");
            clearCooldown(user, args.get(1));
            return;
        }
        try {
            wager = Double.valueOf(args.get(1));
        } catch (NumberFormatException e) {
            handleError(ctx, "wrong args stupid", "use a number dipshit");
            clearCooldown(user, args.get(1));
            return;
        }



        if (wager > bal){
            handleError(ctx, "Yourre Broke", "get rich chucklenuts");
             clearCooldown(user, args.get(1));
            return;
        }
        if (wager> 15000){
            handleError(ctx, "invanid wager", "your wager is too much");
            clearCooldown(user, args.get(1));
            return;
        }
        if (wager<15){
            handleError(ctx, "invalid wager", "too little");
            clearCooldown(user, args.get(1));
            return;
        }
        if(activeGame.get(user)!= null){
            handleError(ctx, "Already have a game", "do this later\nstring formatting my beloved");
            clearCooldown(user, args.get(1));
            return;
        }
        db.take(user, wager);
        decks.put(user, newDeck());
        activeGame.put(user, ctx);
        dealerHands.put(user, new ArrayList<>(Arrays.asList(deal(decks.get(user)),deal(decks.get(user)))));
        playerHands.put(user, new ArrayList<>(Arrays.asList(deal(decks.get(user)),deal(decks.get(user)))));
        dealerHands.get(user).get(1).hidden = true;
        dealerHands.get(user).get(1).name = "?? of ?";
        bets.put(user, wager);
        Integer initDealerValue = valueWithHidden(
                dealerHands.get(user)
        );
        Integer initPlayerValue = value(
                playerHands.get(user)
        );
        if (initPlayerValue == 21){
            dealerFinish(dealerHands.get(user), decks.get(user));
            if (value(dealerHands.get(user)) == 21){
                ctx.getMessage().reply(MessageCreateData.fromEmbeds(formatEmbed(playerHands.get(user), dealerHands.get(user), embedState.PUSH))).queue();
                db.add(user, wager);
                clearGame(user);
                return;
            }
            ctx.getMessage().reply(MessageCreateData.fromEmbeds(formatEmbed(playerHands.get(user), dealerHands.get(user), embedState.WIN))).queue();
            db.add(user, wager*2.5);
            clearGame(user);
            return;
        }
        if (initDealerValue == 21){
            ctx.getMessage().reply(MessageCreateData.fromEmbeds(formatEmbed(playerHands.get(user), dealerHands.get(user), embedState.LOSS))).queue();
            clearGame(user);
            return;
        }
        ctx.getMessage().reply(MessageCreateData.fromEmbeds(formatEmbed(playerHands.get(user), dealerHands.get(user), embedState.LOSS))).queue(m -> {
            m.addReaction(Emoji.fromUnicode("\uD83C\uDCCF"));
            m.addReaction(Emoji.fromUnicode("\uD83D\uDD90\uFE0F"));
            activeGameBot.put(user, m);
        });

    }

}
