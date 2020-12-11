package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.mysql.managers.PrefixManager;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class PrefixCMD extends Command {
    public PrefixCMD() {
        super.commandName = "setprefix";
        super.help = "If you wish to change ScoobyBot's prefix, please enter the new prefix as an argument";
        super.cooldown = 2;
        super.userPermissions.add(Permission.MANAGE_SERVER);
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String[] args = commandContext.getArgs();
        final TextChannel channel = commandContext.getEvent().getChannel();
        final String guildId = commandContext.getEvent().getGuild().getId();

        if (!(args.length > 1)) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Mising arguments! Please refer to the help command.", m).build()).queue();
            return;
        }
        if (args[1].length() > 3) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Desired prefix is too long! Please refer to the help command.", m).build()).queue();
            return;
        }
        PrefixManager.getInstance().setPrefix(guildId, args[1]);
        channel.sendMessage(EmbedUtils.getInstance().
                successEmbedText(":white_check_mark: Success", "Prefix has been changed to: `" + args[1] + "`", m).build()).queue();
    }
}