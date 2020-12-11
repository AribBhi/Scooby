package com.vyy.scooby.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandContext {
    private final GuildMessageReceivedEvent event;
    private final Guild guild;
    private final String[] args;
    private final JDA jda;
    public CommandContext(GuildMessageReceivedEvent event, Guild guild, String[] args) {
        this.event = event;
        this.guild = guild;
        this.args = args;
        this.jda = event.getJDA();
    }
    public GuildMessageReceivedEvent getEvent() {
        return this.event;
    }
    public Guild getGuild() {
        return this.guild;
    }
    public String[] getArgs() {
        return this.args;
    }
    public JDA getJDA() {
        return this.jda;
    }
}
