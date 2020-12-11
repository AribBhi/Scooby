package com.vyy.scooby.command;

import com.vyy.scooby.util.EmbedUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;

import static java.util.concurrent.TimeUnit.SECONDS;

public class CommandHandler {
    private static CommandHandler instance;
    private HashMap<String, Command> commands = new HashMap<>();
    public void handleCommand(GuildMessageReceivedEvent event, String invoke) {
        final String[] args = event.getMessage().getContentRaw().split(" ");
        final Member m = event.getMember();
        final TextChannel channel = event.getChannel();

        if (!commands.containsKey(invoke)) return;

        if (!checkBotPermissions(invoke, channel, event.getGuild().getSelfMember()) || !checkUserPermissions(invoke, channel, m)) {
            channel.sendMessage(EmbedUtils.getInstance()
                    .errorEmbedText(":no_entry_sign: Permissions Error", "Insufficient permissions to run this command.", m).build())
                    .delay(3, SECONDS)
                    .flatMap(Message::delete).queue();
            return;
        }

        if (checkCooldown(invoke, m.getIdLong())) {
            if (sentCooldownErrorCheck(invoke, m.getIdLong())) return;
            channel.sendMessage(EmbedUtils.getInstance()
                    .errorEmbedText(":no_entry_sign: Cooldown", "You are on cooldown for this command", m).build())
                    .delay(3, SECONDS)
                    .flatMap(Message::delete)
                    .queue();
            commands.get(invoke).getSentCooldownError().add(m.getIdLong());
            return;
        }

        if (args.length > 1 && args[1].equalsIgnoreCase("help")) {
            channel.sendMessage(EmbedUtils.getInstance()
                    .simpleEmbedTextNoTitle(commands.get(invoke).getHelp(), event.getMember()).build()).queue();
            return;
        }

        commands.get(invoke).handle(new CommandContext(event, event.getGuild(), args));
        putCooldown(invoke, m.getIdLong(), commands.get(invoke).getCooldown());
        event.getJDA().getGuildById("729715027493257287")
                .getTextChannelById("736428431717433404")
                .sendMessage("Command used. No connection issues.").queue();
    }
    public HashMap<String, Command> getCommands() {
        return this.commands;
    }
    public void addCommand(String commandName, Command commandObject) {
        commands.put(commandName, commandObject);
    }
    public boolean checkCooldown(String invoke, Long id) {
        if (commands.get(invoke).getCooldowns().containsKey(id)) {
            if (commands.get(invoke).getCooldowns().get(id) > System.currentTimeMillis()) {
                return true;
            }
            if (commands.get(invoke).getSentCooldownError().contains(id))
                commands.get(invoke).getSentCooldownError().remove(id);
            commands.get(invoke).getCooldowns().remove(id);
        }
        return false;
    }
    public void putCooldown(String invoke, Long id, int cooldown) {
        commands.get(invoke).getCooldowns().put(id, System.currentTimeMillis() + (1000*cooldown));
    }
    public boolean sentCooldownErrorCheck(String invoke, Long id) {
        return commands.get(invoke).getSentCooldownError().contains(id);
    }
    public boolean checkBotPermissions(String invoke, TextChannel channel, Member m) {
        for (Permission p : commands.get(invoke).getBotPermissions()) {
            if (!m.hasPermission(channel, p)) {
                return false;
            }
        }
        return true;
    }
    public boolean checkUserPermissions(String invoke, TextChannel channel, Member m) {
        for (Permission p : commands.get(invoke).getUserPermissions()) {
            if (!m.hasPermission(channel, p)) {
                return false;
            }
        }
        return true;
    }
    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }
}