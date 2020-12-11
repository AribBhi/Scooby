package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class AwwCMD extends Command {
    public AwwCMD() {
        super.commandName = "aww";
        super.help = "Retrieves a cute picture from Reddit";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject;
        String postLink;
        String title;
        String url;
        do {
            jsonObject = WebUtils.parseJson("https://meme-api.herokuapp.com/gimme/aww");
            postLink = (String) jsonObject.get("postLink");
            title = (String) jsonObject.get("title");
            url = (String) jsonObject.get("url");
        }while ((Boolean) jsonObject.get("nsfw"));

        if (title.length() > 255)
            title = title.substring(0 , 255);

        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance().simpleEmbedImage(title, url, postLink, commandContext.getEvent().getMember()).build()).queue();
    }
}
