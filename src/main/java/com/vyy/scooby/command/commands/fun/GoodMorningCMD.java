package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class GoodMorningCMD extends Command {
    public GoodMorningCMD() {
        super.commandName = "goodmorning";
        super.help = "Rise & Shine! :sun_with_face:";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject;
        String url;
        do {
            jsonObject = WebUtils.parseJson("https://meme-api.herokuapp.com/gimme/morning");
            url = (String) jsonObject.get("url");
        }while ((Boolean) jsonObject.get("nsfw"));

        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance().simpleEmbedImageNoTitle(url, commandContext.getEvent().getMember()).build()).queue();
    }
}
