package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import org.json.JSONObject;

public class QuoteCMD extends Command {
    public QuoteCMD() {
        super.commandName = "quote";
        super.help = "Gives you a random quote you might find amusing";
        super.cooldown = 2;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject = WebUtils.parseJson("https://get-me-a-quote.herokuapp.com/?accept=json");
        String quote = (String) jsonObject.get("text");

        commandContext.getEvent().getChannel().sendMessage(EmbedUtils.getInstance().simpleEmbedTextNoTitle(quote, commandContext.getEvent().getMember()).build()).queue();
    }
}
