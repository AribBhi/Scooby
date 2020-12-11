package com.vyy.scooby.util;

import net.dv8tion.jda.api.entities.Member;

import java.util.List;

public class BotUtils {
    private static BotUtils instance;

    public Long parseArgsToId(List<Member> mentionedUsers, String[] args) {
        Long id;
        if (mentionedUsers.isEmpty()) {
            try {
                id = Long.parseLong(args[1]);
            }catch (NumberFormatException ignored) {
                return null;
            }
        }else {
            id = mentionedUsers.get(0).getIdLong();
        }
        return id;
    }

    public static BotUtils getInstance() {
        if (instance == null) {
            instance = new BotUtils();
        }
        return instance;
    }
}
