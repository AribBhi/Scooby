package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class DogFactCMD extends Command {
    public DogFactCMD() {
        super.commandName = "dogfact";
        super.help = "Get a unique fact about a dog";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject = WebUtils.parseJson("https://some-random-api.ml/facts/dog");
        String fact = (String) jsonObject.get("fact");

        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance()
                .successEmbedText("Dog Fact", fact, commandContext.getEvent().getMember()).build()).queue();
    }
}
