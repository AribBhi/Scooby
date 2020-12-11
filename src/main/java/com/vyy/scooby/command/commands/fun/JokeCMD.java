package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class JokeCMD extends Command {
    public JokeCMD() {
        super.commandName = "joke";
        super.help = "Use this command to hear a (hopefully) funny joke";
        super.cooldown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject = WebUtils.parseJson("https://official-joke-api.appspot.com/random_joke");
        String setup = (String) jsonObject.get("setup");
        String punchline = (String) jsonObject.get("punchline");

        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance().simpleEmbedText(setup, punchline, commandContext.getEvent().getMember()).build()).queue();
    }
}
