package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class EmbedCMD extends Command {
    public EmbedCMD() {
        super.commandName = "embed";
        super.help = "Make your own embed message and have it sent through ScoobyBot";
        super.cooldown = 2;
        super.botPermissions.add(Permission.MESSAGE_MANAGE);
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String[] args = commandContext.getArgs();
        final TextChannel channel = commandContext.getEvent().getChannel();
        StringBuilder sb = new StringBuilder();

        if (!(args.length > 1)) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Mising arguments! Please refer to the help command.", m).build()).queue();
            return;
        }
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i] + " ");
        }
        commandContext.getEvent().getMessage().delete().queue();
        channel.sendMessage(EmbedUtils.getInstance().simpleEmbedTextNoTitle(sb.toString(), m).build()).queue();
    }
}
