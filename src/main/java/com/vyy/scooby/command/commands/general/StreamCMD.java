package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.mysql.managers.StreamManager;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class StreamCMD extends Command {
    public StreamCMD() {
        super.commandName = "stream";
        super.help = "To toggle stream notifications on/off. Usage: stream <on/off> <text channel>";
        super.cooldown = 2;
        super.userPermissions.add(Permission.MANAGE_SERVER);
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String[] args = commandContext.getArgs();
        final TextChannel channel = commandContext.getEvent().getChannel();
        final String guildId = commandContext.getGuild().getId();
        final List<TextChannel> mentionedChannels = commandContext.getEvent().getMessage().getMentionedChannels();
        String toggle;

        if (!(args.length > 1)) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Mising arguments! Usage: stream <on/off> <text channel>", m).build()).queue();
            return;
        }
        if (!(args[1].equalsIgnoreCase("on")) && !(args[1].equalsIgnoreCase("off"))) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Wrong usage! Please refer to the help command. ", m).build()).queue();
            return;
        }
        if (args[1].equalsIgnoreCase("on") && mentionedChannels.isEmpty()) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Mising arguments! Please refer to the help command.", m).build()).queue();
            return;
        }
        toggle = args[1].equalsIgnoreCase("on") ? "true" : "false";
        StreamManager.getInstance().setStreamToggle(guildId, toggle);
        if (toggle.equalsIgnoreCase("true")) {
            StreamManager.getInstance().setStreamChannel(guildId, mentionedChannels.get(0).getId());
            channel.sendMessage(EmbedUtils.getInstance().
                    successEmbedText(":white_check_mark: Success", "Stream notification toggle has been set to `" + toggle +
                            "`\nNotifications will be sent to " + mentionedChannels.get(0).getAsMention(), m).build()).queue();
        }else {
            channel.sendMessage(EmbedUtils.getInstance().
                    successEmbedText(":white_check_mark: Success", "Stream notification toggle has been set to `" + toggle +
                            "`", m).build()).queue();
        }
    }
}
