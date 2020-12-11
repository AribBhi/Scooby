package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.Constants;
import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.ColorUtils;
import com.vyy.scooby.util.DateUtils;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.EmoteUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.time.Instant;
import java.util.List;

public class InfoCMD extends Command {
    public InfoCMD() {
        super.commandName = "info";
        super.help = "Retrieves information about your Discord account";
        super.cooldown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member member = commandContext.getEvent().getMember();
        final List<Member> mentionedUsers = commandContext.getEvent().getMessage().getMentionedMembers();
        Long id;
        if (mentionedUsers.isEmpty()) {
            try {
                if (commandContext.getArgs().length == 1)
                    id = member.getIdLong();
                else
                    id = Long.parseLong(commandContext.getArgs()[1]);
            } catch (NumberFormatException ignored) { return; }
        }else {
            id = mentionedUsers.get(0).getIdLong();
        }

        commandContext.getGuild().retrieveMemberById(id).queue((m) -> {
            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("User Information")
                    .setColor(ColorUtils.getInstance().getDefaultColor())
                    .setThumbnail(m.getUser().getEffectiveAvatarUrl().replaceFirst(".jpg", ".png"))
                    .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                    .setAuthor(member.getUser().getAsTag(), "https://discordapp.com", member.getUser().getEffectiveAvatarUrl().replaceFirst(".jpg", ".png"))
                    .addField("Name", "> " + m.getAsMention() + " " + "(" + m.getEffectiveName() + ")", true)
                    .addField("Discriminator", "> #" + m.getUser().getDiscriminator(), true)
                    .addField("ID", "> " + m.getUser().getId(), true)
                    .addField("Created", "> " + DateUtils.getInstance().getTime(m.getTimeCreated()), true)
                    .addField("Joined", "> " + DateUtils.getInstance().getTime(m.getTimeJoined()), true)
                    .addField("Presence", "> " + EmoteUtils.getInstance().getPresenceStatus(m.getOnlineStatus(), commandContext.getJDA()), true)
                    .addField("Badges", "> " + EmoteUtils.getInstance().getBadges(m, commandContext.getJDA()), false)
                    .setTimestamp(Instant.now());

            commandContext.getEvent().getChannel().sendMessage(eb.build()).queue();
        }, (error) -> {
            commandContext.getEvent().getChannel()
                    .sendMessage(EmbedUtils.getInstance().errorEmbedText(":no_entry_sign: Error", "I was unable to find that user", member)
                            .build()).queue();
        });
    }
}
