package com.vyy.scooby.util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.EnumSet;

public class EmoteUtils {
    public static EmoteUtils instance;
    public String getPresenceStatus(OnlineStatus onlineStatus, JDA jda) {
        switch (onlineStatus) {
            case ONLINE:
                return jda.getEmoteById("731239593252487248").getAsMention();
            case DO_NOT_DISTURB:
                return jda.getEmoteById("731239610600128514").getAsMention();
            default:
                return jda.getEmoteById("731239602505121882").getAsMention();
        }
    }
    public String getBadges(Member m, JDA jda) {
        final EnumSet<User.UserFlag> userFlags = m.getUser().getFlags();
        StringBuilder sb = new StringBuilder();
        for (User.UserFlag u : userFlags) {
            switch (u) {
                case HYPESQUAD_BALANCE:
                    sb.append(jda.getEmoteById("731237436876914810").getAsMention() + " ");
                    break;
                case HYPESQUAD_BRAVERY:
                    sb.append(jda.getEmoteById("731239585752809472").getAsMention() + " ");
                    break;
                case HYPESQUAD_BRILLIANCE:
                    sb.append(jda.getEmoteById("731239578383548476").getAsMention() + " ");
                    break;
                case VERIFIED_BOT:
                    sb.append(jda.getEmoteById("731245867763499060").getAsMention() + " ");
                    break;
                default:
                    continue;
            }
        }
        return sb.toString();
    }
    public static EmoteUtils getInstance() {
        if (instance == null) {
            instance = new EmoteUtils();
        }
        return instance;
    }
}
