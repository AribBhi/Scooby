package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONObject;

public class EightBallCMD extends Command {
    public EightBallCMD() {
        super.commandName = "8ball";
        super.help = "To answer your deepest questions with high confidence!";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String[] args = commandContext.getArgs();
        final TextChannel channel = commandContext.getEvent().getChannel();

        if (!(args.length > 1)) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Please enter a question for me to answer", m).build()).queue();
            return;
        }
        JSONObject jsonObject = WebUtils.parseJson("https://8ball.delegator.com/magic/JSON/test");
        JSONObject newObject = jsonObject.getJSONObject("magic");
        String answer = (String) newObject.get("answer");

        channel.sendMessage(EmbedUtils.getInstance().simpleEmbedText(":8ball: 8Ball's Response", answer, m).build()).queue();
    }
}
