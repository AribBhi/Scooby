package com.vyy.scooby;

import com.vyy.scooby.command.CommandRegister;
import com.vyy.scooby.webhook.HttpServerHook;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.*;
import java.util.stream.Collectors;

import static net.dv8tion.jda.api.requests.GatewayIntent.*;
import static net.dv8tion.jda.api.utils.MemberCachePolicy.*;
import static net.dv8tion.jda.api.utils.cache.CacheFlag.*;

public class Scooby {
    public static JDA jda;
    private final List<GatewayIntent> gatewayIntents = Arrays.asList(GUILD_BANS, GUILD_EMOJIS, GUILD_MESSAGES);
    private final List<CacheFlag> disableCacheFlags = Arrays.asList(ACTIVITY, CLIENT_STATUS, MEMBER_OVERRIDES, VOICE_STATE);
    private Scooby() throws Exception{
        jda = new JDABuilder().create(Constants.TOKEN, gatewayIntents)
                .setActivity(Activity.playing("loading..."))
                .setStatus(OnlineStatus.ONLINE)
                .setChunkingFilter(ChunkingFilter.NONE)
                .setMemberCachePolicy(MemberCachePolicy.ONLINE.or(OWNER))
                .disableCache(disableCacheFlags)
                .addEventListeners(new Listeners())
                .build()
                .awaitReady();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                jda.getPresence().setActivity(Activity.watching(jda.getGuilds().size() + " guilds | ;help"));
            }
        }, 0, 20000);
        new CommandRegister(this);
        new HttpServerHook();
    }

    public static void main(String[] args) throws Exception{
        System.setProperty("http.agent", "Mozilla/4.76");
        new Scooby();
    }
}
