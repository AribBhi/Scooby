package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class SuggestCMD extends Command {
    public SuggestCMD() {
        super.commandName = "suggest";
        super.help = "Suggest something to the owner of ScoobyBot";
        super.cooldown = 15;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String[] args = commandContext.getArgs();
        final TextChannel channel = commandContext.getEvent().getChannel();

        if (!(args.length > 1)) {
            channel.sendMessage(EmbedUtils.getInstance()
                    .errorEmbedText(":no_entry_sign: Error", "Mising arguments! Please refer to the help command.", m).build()).queue();
            return;
        }

        commandContext.getJDA().retrieveUserById("393242459150483457")
                .flatMap(u -> u.openPrivateChannel())
                    .flatMap(c -> c.sendMessage(EmbedUtils.getInstance()
                            .successEmbedText("Suggestion by: " + m.getEffectiveName() + "#" + m.getUser().getDiscriminator(),
                                    commandContext.getEvent().getMessage().getContentRaw().split("\\s+", 2)[1], m)
                            .build()))
                            .queue();
        channel.sendMessage(EmbedUtils.getInstance()
                .successEmbedText(":white_check_mark: Success", "Suggestion has been forwarded to the ScoobyBot developers.\n" +
                "Thanks for your contribution to ScoobyBot!", m).build()).queue();
    }
}
