package com.vyy.scooby.command.commands.admin;

import com.vyy.scooby.Scooby;
import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.mysql.managers.PrefixManager;
import groovy.lang.GroovyShell;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

public class EvalCMD extends Command {
    private Scooby scooby;
    private final GroovyShell engine;
    private final String imports;
    public EvalCMD(Scooby scooby) {
        super.commandName = "eval";
        super.help = "Owner use only";
        super.cooldown = 0;
        this.scooby = scooby;
        this.engine = new GroovyShell();
        this.imports = "import java.io.*\n" +
                "import java.lang.*\n" +
                "import java.util.*\n" +
                "import java.util.concurrent.*\n" +
                "import net.dv8tion.jda.core.*\n" +
                "import net.dv8tion.jda.core.entities.*\n" +
                "import net.dv8tion.jda.core.entities.impl.*\n" +
                "import net.dv8tion.jda.core.managers.*\n" +
                "import net.dv8tion.jda.core.managers.impl.*\n" +
                "import net.dv8tion.jda.core.utils.*\n";
    }

    @Override
    protected void handle(CommandContext commandContext) {
        final Member m = commandContext.getEvent().getMember();
        final TextChannel channel = commandContext.getEvent().getChannel();
        if (!m.getId().equalsIgnoreCase("393242459150483457")) {
            return;
        }
        try {
            engine.setProperty("bot", scooby);
            engine.setProperty("args", commandContext.getArgs());
            engine.setProperty("event", commandContext.getEvent());
            engine.setProperty("message", commandContext.getEvent().getMessage());
            engine.setProperty("channel", commandContext.getEvent().getChannel());
            engine.setProperty("jda", commandContext.getJDA());
            engine.setProperty("guild", commandContext.getGuild());
            engine.setProperty("member", commandContext.getEvent().getMember());

            String script = imports + commandContext.getEvent().getMessage().getContentRaw().split("\\s+", 2)[1];
            Object out = engine.evaluate(script);

            channel.sendMessage(out == null ? "Executed without error" : out.toString()).queue();
        }
        catch (Exception e) {
            channel.sendMessage(e.getMessage()).queue();
        }
    }
}
