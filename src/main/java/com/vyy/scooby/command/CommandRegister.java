package com.vyy.scooby.command;

import com.vyy.scooby.Scooby;
import com.vyy.scooby.command.commands.admin.*;
import com.vyy.scooby.command.commands.fun.*;
import com.vyy.scooby.command.commands.general.*;
import com.vyy.scooby.command.commands.moderation.*;

public class CommandRegister {
    public CommandRegister(Scooby scooby) {
        addCommand(new HelpCMD());
        addCommand(new PrefixCMD());
        addCommand(new MemeCMD());
        addCommand(new JokeCMD());
        addCommand(new CommandsCMD());
        addCommand(new PingCMD());
        addCommand(new EmbedCMD());
        addCommand(new InfoCMD());
        addCommand(new SuggestCMD());
        addCommand(new InviteCMD());
        addCommand(new GoodMorningCMD());
        addCommand(new GoodNightCMD());
        addCommand(new SlowModeCMD());
        addCommand(new AwwCMD());
        addCommand(new WinkCMD());
        addCommand(new HugCMD());
        addCommand(new SongCMD());
        addCommand(new QuoteCMD());
        addCommand(new EvalCMD(scooby));
        addCommand(new KickCMD());
        addCommand(new BanCMD());
        addCommand(new EightBallCMD());
        addCommand(new CoinFlipCMD());
        addCommand(new CatFactCMD());
        addCommand(new DogFactCMD());
        addCommand(new PikachuCMD());
        addCommand(new PandaCMD());
        addCommand(new PatCMD());
        addCommand(new CovidCMD());
        addCommand(new StreamCMD());
    }
    public void addCommand(Command commandObject) {
        if (CommandHandler.getInstance().getCommands().containsKey(commandObject.getCommandName())) {
            return;
        }
        CommandHandler.getInstance().addCommand(commandObject.getCommandName(), commandObject);
    }
}
