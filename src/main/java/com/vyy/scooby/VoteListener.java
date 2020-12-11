package com.vyy.scooby;

import org.json.JSONObject;

/*

TODO:
Add voting rewards and
add more voting listening features

 */

public class VoteListener {
    private static VoteListener instance;

    public void registerVote(JSONObject jsonObject, String authorization) {
        if (!authorization.equalsIgnoreCase(Constants.TOPGGVOTEAUTHORIZATION)) return;

        String user = (String) jsonObject.get("user");
        Scooby.jda.retrieveUserById(user).queue(u -> {
            Scooby.jda.getTextChannelById("736428431717433404").sendMessage(u.getAsTag() + " has voted!\n**User ID:** " + u.getId()).queue();
        });
    }

    public static VoteListener getInstance() {
        if (instance == null) {
            instance = new VoteListener();
        }
        return instance;
    }
}
