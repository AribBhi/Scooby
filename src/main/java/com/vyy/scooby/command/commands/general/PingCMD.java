package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.entities.Member;

public class PingCMD extends Command {
    public PingCMD() {
        super.commandName = "ping";
        super.help = "Retrieves the ping of the bot to your Guild";
        super.cooldown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance().simpleEmbedText(":ping_pong: Pong!", "Bot Latency: `" +
                commandContext.getJDA().getGatewayPing() + "ms`", m).build()).queue();
    }
}
