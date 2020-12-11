package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CoinFlipCMD extends Command {
    public CoinFlipCMD() {
        super.commandName = "coinflip";
        super.help = "Flip a coin and test your luck";
        super.cooldown = 2;
        super.countdown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Timer t = new Timer();
        final TextChannel channel = commandContext.getEvent().getChannel();
        final Member m = commandContext.getEvent().getMember();
        final Random r = new Random();
        int flip = r.nextInt(2);
        String result;
        Long time = System.currentTimeMillis() + 3000;
        channel.sendMessage("Flipping coin in `3s`").queue((message) -> {
            Long messageID = message.getIdLong();
            t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Double timeLeft = Math.ceil((time-System.currentTimeMillis())/(double)1000);
                    if (timeLeft > 0) {
                        channel.retrieveMessageById(messageID).queue((editMessage) -> {
                            editMessage.editMessage("Flipping coin in `" + Long.toString(timeLeft.longValue()) + "s`").queue();
                        }, (error) -> {
                            t.purge();
                            t.cancel();
                            return;
                        });
                    }
                }
            }, 1000, 1000);
            channel.deleteMessageById(messageID).queueAfter(3, SECONDS);
        });
        if (flip == 0)
            result = "heads";
        else
            result = "tails";
        channel.sendMessage(EmbedUtils.getInstance()
                .successEmbedText(":dollar: Coin Flip", "The result was: `" + result + "`", m).build())
                .queueAfter(3, SECONDS);
    }
}
