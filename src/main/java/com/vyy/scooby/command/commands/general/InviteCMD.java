package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.entities.Member;

public class InviteCMD extends Command {
    public InviteCMD() {
        super.commandName = "invite";
        super.help = "Generates an invite link for Scooby";
        super.cooldown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance()
                .successEmbedText(":white_check_mark: Success",
                        "[Invite Link](https://discord.com/oauth2/authorize?client_id=729547900400566373&permissions=2147483639&scope=bot)",
                        m).build()).queue();
    }
}
