package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class MemeCMD extends Command {
    public MemeCMD() {
        super.commandName = "meme";
        super.help = "Use this command to see a (hopefully) funny meme";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject;
        String postLink;
        String title;
        String url;
        do {
            jsonObject = WebUtils.parseJson("https://meme-api.herokuapp.com/gimme/dankmemes");
            postLink = (String) jsonObject.get("postLink");
            title = (String) jsonObject.get("title");
            url = (String) jsonObject.get("url");
        }while ((Boolean) jsonObject.get("nsfw"));

        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance().simpleEmbedImage(title, url, postLink, commandContext.getEvent().getMember()).build()).queue();
    }
}
