package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.Constants;
import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.mysql.managers.PrefixManager;
import com.vyy.scooby.util.ColorUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

public class HelpCMD extends Command {
    public HelpCMD() {
        super.commandName = "help";
        super.help = "This command will showcase the features of ScoobyBot!\nTo check individual command features, please use `<commandName> help`";
        super.cooldown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String prefix = PrefixManager.getInstance().getPrefix(commandContext.getGuild().getId());
        EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Help Command | Support")
                        .setDescription("Welcome to **Scooby**! For further support, please join our [Discord](https://discord.gg/RU7QkjU) server!\n \u200B")
                        .setColor(ColorUtils.getInstance().getDefaultColor())
                        .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                        .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com",
                                m.getUser().getEffectiveAvatarUrl().replaceFirst(".jpg", ".png"))
                        .addField(":hammer_pick: Commands", "> Use `" + prefix + "<command> help` to learn more about a specific command\n> For a list of commands, use `" +
                                prefix + "commands`\n \u200B", false)
                        .addField(":hammer: Prefix", "> Your current prefix is: `" + prefix + "`\n> To change your prefix, use `" +
                                prefix + "setprefix <prefix>`\n \u200B", false)
                        .addField("", "If you encounter any issues/bugs, please let us know on our [Discord](https://discord.gg/RU7QkjU) server!", false);

        commandContext.getEvent().getChannel().sendMessage(eb.build()).queue();
    }
}
