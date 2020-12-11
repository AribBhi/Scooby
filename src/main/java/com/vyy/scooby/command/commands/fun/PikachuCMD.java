package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class PikachuCMD extends Command {
    public PikachuCMD() {
        super.commandName = "pikachu";
        super.help = "Retrieves a cute picture of a pikachu";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject = WebUtils.parseJson("https://some-random-api.ml/img/pikachu");
        String url = (String) jsonObject.get("link");

        commandContext.getEvent().getChannel()
                .sendMessage(EmbedUtils.getInstance().simpleEmbedImageNoTitle(url, commandContext.getEvent().getMember()).build()).queue();
    }
}
