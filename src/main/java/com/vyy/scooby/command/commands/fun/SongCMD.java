package com.vyy.scooby.command.commands.fun;

import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.EmbedUtils;
import com.vyy.scooby.util.WebUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONObject;

public class SongCMD extends Command {
    public SongCMD() {
        super.commandName = "song";
        super.help = "Find a song based on a set of lyrics/title";
        super.cooldown = 5;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final String[] args = commandContext.getArgs();
        final TextChannel channel = commandContext.getEvent().getChannel();
        StringBuilder sb = new StringBuilder();
        String title;
        String author;
        String lyrics;
        JSONObject jsonObject;

        if (!(args.length > 1)) {
            channel.sendMessage(EmbedUtils.getInstance().
                    errorEmbedText(":no_entry_sign: Error", "Mising arguments! Please refer to the help command.", m).build()).queue();
            return;
        }
        for (int i = 1; i < args.length; i++) {
            if ((i+1) == args.length) {
                sb.append(args[i]);
                break;
            }
            sb.append(args[i] + " ");
        }
        String url = "https://some-random-api.ml/lyrics?title=" + sb.toString();
        jsonObject = WebUtils.parseJson(url.replaceAll(" ", "%20"));
        if (jsonObject == null || jsonObject.keySet().contains("error")) {
            channel.sendMessage(EmbedUtils.getInstance()
                    .errorEmbedText(":no_entry_sign: No song found", "I couldn't find a song with those lyrics.", m).build()).queue();
            return;
        }
        title = (String) jsonObject.get("title");
        author = (String) jsonObject.get("author");
        lyrics = (String) jsonObject.get("lyrics");

        if (lyrics.length() > Message.MAX_CONTENT_LENGTH) {
            lyrics = lyrics.substring(0, Message.MAX_CONTENT_LENGTH);
        }

        channel.sendMessage(EmbedUtils.getInstance()
                .simpleEmbedText("\"" + title + "\" by " + author, lyrics, m).build()).queue();
    }
}
