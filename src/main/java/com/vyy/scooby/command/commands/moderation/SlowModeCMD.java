package com.vyy.scooby.command.commands.moderation;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;

public class SlowModeCMD extends Command {
    public SlowModeCMD() {
        super.commandName = "slowmode";
        super.help = "Sets the channel where the command is ran in slow mode. [Set to 0 to disable slow mode";
        super.botPermissions.add(Permission.MESSAGE_MANAGE);
        super.botPermissions.add(Permission.MANAGE_CHANNEL);
        super.userPermissions.add(Permission.MANAGE_CHANNEL);
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String[] args = commandContext.getArgs();
        final TextChannel channel = commandContext.getEvent().getChannel();
        int time = 0;

        if (!(args.length > 1)) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Mising arguments! Please refer to the help command.", m).build()).queue();
            return;
        }
        try {
            time = Integer.parseInt(args[1]);
        }catch (NumberFormatException e) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Invalid slowmode time argument! Please refer to the help command.", m).build()).queue();
            return;
        }
        if (time > TextChannel.MAX_SLOWMODE) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Desired slowmode time is too long! Please refer to the help command.", m).build()).queue();
            return;
        }
        commandContext.getEvent().getMessage().delete().queue();
        channel.getManager().setSlowmode(time).queue();
        channel.sendMessage(EmbedUtils.getInstance()
                .successEmbedText(":white_check_mark: Success", "Slowmode time changed to: `" + time + "s` for channel: `" + channel.getName() + "`", m)
                .setTimestamp(Instant.now())
                .build())
                .queue();
    }
}
