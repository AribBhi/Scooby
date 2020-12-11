package com.vyy.scooby.command;

import com.vyy.scooby.util.Category;
import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Command {
    protected String commandName = null;
    protected String help = null;
    protected ArrayList<Permission> userPermissions = new ArrayList<>();
    protected ArrayList<Permission> botPermissions = new ArrayList<>();
    protected HashMap<Long, Long> cooldowns = new HashMap<>();
    protected ArrayList<Long> sentCooldownError = new ArrayList<>();
    protected int cooldown = 0;
    protected int countdown = 0;
    protected Category category;

    protected abstract void handle(CommandContext commandContext);

    public String getCommandName() {
        return this.commandName;
    }

    public String getHelp() {
        return this.help;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public HashMap<Long, Long> getCooldowns() {
        return this.cooldowns;
    }

    public ArrayList<Long> getSentCooldownError() {
        return this.sentCooldownError;
    }

    public ArrayList<Permission> getUserPermissions() {
        return this.userPermissions;
    }

    public ArrayList<Permission> getBotPermissions() {
        return this.botPermissions;
    }

    public Category getCategory() {
        return this.category;
    }
}
