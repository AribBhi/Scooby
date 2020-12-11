package com.vyy.scooby;

import com.vyy.scooby.command.CommandHandler;
import com.vyy.scooby.mysql.managers.PrefixManager;
import com.vyy.scooby.mysql.managers.StreamManager;
import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Listeners extends ListenerAdapter {
    private boolean ready = false;
    @Override
    public void onReady(@Nonnull final ReadyEvent event) {
        for (Guild g : event.getJDA().getGuilds()) {
            if (PrefixManager.getInstance().getPrefix(g.getId()) == null) {
                PrefixManager.getInstance().addNewGuild(g.getId());
            }
            if (StreamManager.getInstance().getStreamToggle(g.getId()) == null) {
                StreamManager.getInstance().addNewGuild(g.getId());
            }
        }
        ready = true;
    }
    @Override
    public void onGuildMessageReceived(@Nonnull final GuildMessageReceivedEvent event) {
        final String[] args = event.getMessage().getContentRaw().split(" ");
        final User u = event.getAuthor();
        final String prefix = PrefixManager.getInstance().getPrefix(event.getGuild().getId());
        if (u.isBot()
                || !args[0].startsWith(prefix)
                || args[0].length() == 1
                || event.isWebhookMessage()
                || !event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_WRITE)
                || !event.getGuild().getSelfMember().hasPermission(event.getChannel(), Permission.MESSAGE_EMBED_LINKS)) return;

        CommandHandler.getInstance().handleCommand(event, args[0].toLowerCase().substring(prefix.length()));
    }

    /*
    The following code is commented out as you may not run the stream command without
    the GUILD_PRESENCES intent enabled which you have to be approved for!

    @Override
    public void onUserActivityStart(@Nonnull final UserActivityStartEvent event) {
        if (!ready) return;
        final String guildId = event.getGuild().getId();
        final Member m = event.getMember();

        if (StreamManager.getInstance().getStreamToggle(guildId).equalsIgnoreCase("false")
            || m.getUser().isBot()
            || event.getNewActivity().getUrl() == null
            || !Activity.isValidStreamingUrl(event.getNewActivity().getUrl())
            || event.getNewActivity().getType() != Activity.ActivityType.STREAMING) return;

        final String channelId = StreamManager.getInstance().getStreamChannel(guildId);
        if (event.getGuild().getTextChannelById(channelId) == null) return;
        if (event.getGuild().getSelfMember().hasPermission(event.getGuild().getTextChannelById(channelId), Permission.MESSAGE_WRITE)
                && event.getGuild().getSelfMember().hasPermission(event.getGuild().getTextChannelById(channelId), Permission.MESSAGE_EMBED_LINKS)) {
            event.getGuild().getTextChannelById(channelId)
                    .sendMessage(EmbedUtils.getInstance()
                            .simpleEmbedText(m.getEffectiveName() + " has started streaming!",
                                    m.getEffectiveName() + " is now live on " + event.getNewActivity().getUrl(), m).build()).queue();
        }
    }
    */

    @Override
    public void onGuildJoin(@Nonnull final GuildJoinEvent event) {
        final Guild g = event.getGuild();
        PrefixManager.getInstance().addNewGuild(g.getId());
        StreamManager.getInstance().addNewGuild(g.getId());
    }
    @Override
    public void onGuildLeave(@Nonnull final GuildLeaveEvent event) {
        final Guild g = event.getGuild();
        PrefixManager.getInstance().removeGuild(g.getId());
        StreamManager.getInstance().removeGuild(g.getId());
    }
    @Override
    public void onShutdown(@Nonnull final ShutdownEvent event) {
        event.getJDA().shutdown();
    }
}
