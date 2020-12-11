package com.vyy.scooby.command.commands.moderation;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.BotUtils;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.Instant;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class KickCMD extends Command {
    public KickCMD() {
        super.commandName = "kick";
        super.help = "Use this command to kick someone from your guild. To use, mention a user to kick and provide an (optional) reason";
        super.userPermissions.add(Permission.KICK_MEMBERS);
        super.botPermissions.add(Permission.KICK_MEMBERS);
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member member = commandContext.getEvent().getMember();
        final Member selfUser = commandContext.getGuild().getSelfMember();
        final TextChannel channel = commandContext.getEvent().getChannel();
        final List<Member> mentionedUsers = commandContext.getEvent().getMessage().getMentionedMembers();
        final String[] args = commandContext.getArgs();
        Long id = null;

        StringBuilder sb = new StringBuilder();
        if (mentionedUsers.isEmpty() && args.length == 1) {
            channel.sendMessage(EmbedUtils.getInstance()
                    .errorEmbedText(":no_entry_sign: Error", "Please enter a member to kick", member).build())
                    .delay(3, SECONDS)
                    .flatMap(Message::delete).queue();
            return;
        }
        id = BotUtils.getInstance().parseArgsToId(mentionedUsers, args);
        if (id == null) {
            channel.sendMessage(EmbedUtils.getInstance()
                    .errorEmbedText(":no_entry_sign: Error", "Invalid member", member).build())
                    .delay(3, SECONDS)
                    .flatMap(Message::delete).queue();
            return;
        }
        commandContext.getGuild().retrieveMemberById(id).queue((m) -> {
            if (!member.canInteract(m) || !selfUser.canInteract(m)) {
                channel.sendMessage(EmbedUtils.getInstance()
                        .errorEmbedText(":no_entry_sign: Error", "I am unable to kick this member due to a lack of permissions", m).build())
                        .delay(3, SECONDS)
                        .flatMap(Message::delete).queue();
                return;
            }
            for (int i = 2; i < commandContext.getArgs().length; i++) {
                sb.append(commandContext.getArgs()[i] + " ");
            }
            m.kick(sb.toString()).queue();
            channel.sendMessage(EmbedUtils.getInstance()
                    .successEmbedText(":white_check_mark: Success", "`" + m.getUser().getAsTag() + "` has been kicked", member)
                    .setTimestamp(Instant.now())
                    .build())
                    .queue();
        }, (error) -> {
            channel.sendMessage(EmbedUtils.getInstance()
                    .errorEmbedText(":no_entry_sign: Error", "Invalid member", member).build())
                    .delay(3, SECONDS)
                    .flatMap(Message::delete).queue();
        });
    }
}
