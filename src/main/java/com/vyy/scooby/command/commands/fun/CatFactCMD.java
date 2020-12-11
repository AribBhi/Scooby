package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class CatFactCMD extends Command {
    public CatFactCMD() {
        super.commandName = "catfact";
        super.help = "Get a unique fact about a cat";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject = WebUtils.parseJson("https://some-random-api.ml/facts/cat");
        String fact = (String) jsonObject.get("fact");

        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance()
                .successEmbedText("Cat Fact", fact, commandContext.getEvent().getMember()).build()).queue();
    }
}
