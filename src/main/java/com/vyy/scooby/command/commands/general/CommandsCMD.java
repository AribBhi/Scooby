package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.Constants;
import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.mysql.managers.PrefixManager;
import com.vyy.scooby.util.ColorUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

public class CommandsCMD extends Command {
    public CommandsCMD() {
        super.commandName = "commands";
        super.help = "Provides a list of commands provided by Scooby";
        super.cooldown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String prefix = PrefixManager.getInstance().getPrefix(commandContext.getGuild().getId());
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle(":hammer: Utilities")
                .setDescription("List of commands/features ScoobyBot has to offer!\n \u200B")
                .setColor(ColorUtils.getInstance().getDefaultColor())
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com", m.getUser().getEffectiveAvatarUrl().replaceFirst(".jpg", ".png"))
                .addField("Basic Commands", "> `" + prefix + "help` Showcases the features of ScoobyBot\n" +
                        "> `" + prefix + "info` Gives information about your Discord account\n" +
                        "> `" + prefix + "ping` Retrieves the ping of the bot to your Guild\n" +
                        "> `" + prefix + "suggest` Suggest something to the owner of ScoobyBot\n" +
                        "> `" + prefix + "embed` Make your own embed message and have it sent through ScoobyBot\n" +
                        "> `" + prefix + "setprefix` Sets the guild's prefix. Default is " + "';'\n" +
                        "> `" + prefix + "invite` Generates an invite link for Scooby\n" +
                        "> `" + prefix + "stream` Turn on stream notifications for your guild\n" +
                        "> `" + prefix + "covid` Retrieves the latest information regarding COVID-19" + "\n \u200B", false)
                .addField("Moderation Commands", "> `" + prefix + "slowmode` Sets the desired channel into slowmode\n" +
                        "> `" + prefix + "ban` Bans a member from the guild\n" +
                        "> `" + prefix + "kick` Kicks a member from the guild" + "\n \u200B", false)
                .addField("Fun Commands", "> `" + prefix + "song` Retrieves a song based on the set of lyrics/the title you provide\n" +
                        "> `" + prefix + "joke` Gives a random joke from reddit\n" +
                        "> `" + prefix + "nsfw` Gives a random NSFW :camera: from reddit [Enabled only in NSFW permitted channels!]\n" +
                        "> `" + prefix + "quote` Gives a random quote you might find amusing\n" +
                        "> `" + prefix + "goodmorning` Gives you a refreshing image to wake up to\n" +
                        "> `" + prefix + "goodnight` Gives a picture of the night skies\n" +
                        "> `" + prefix + "aww` Gives a random picture of an animal you will adore\n" +
                        "> `" + prefix + "panda` Gives you a cute picture of a panda\n" +
                        "> `" + prefix + "wink` Gives a random gif of a wink\n" +
                        "> `" + prefix + "hug` Gives a random gif of a hug\n" +
                        "> `" + prefix + "pat` Gives a random gif of someone being patted\n" +
                        "> `" + prefix + "pikachu` Gives a random gif of a pikachu\n" +
                        "> `" + prefix + "8ball` To answer your questions by the o mighty 8-ball\n" +
                        "> `" + prefix + "catfact` Gives you a unique cat fact\n" +
                        "> `" + prefix + "dogfact` Gives you a unique dog fact\n" +
                        "> `" + prefix + "coinflip` Generates a random coinflip\n" +
                        "> `" + prefix + "meme` Gives a random meme from reddit\n \u200B", false);

        commandContext.getEvent().getChannel().sendMessage(eb.build()).queue();
    }
}
