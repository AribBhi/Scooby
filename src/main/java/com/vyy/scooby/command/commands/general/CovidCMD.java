package com.vyy.scooby.command.commands.general;

import com.vyy.scooby.Constants;
import com.vyy.scooby.command.Command;
import com.vyy.scooby.command.CommandContext;
import com.vyy.scooby.util.ColorUtils;
import com.vyy.scooby.util.WebUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

public class CovidCMD extends Command {
    public CovidCMD() {
        super.commandName = "covid";
        super.help = "Retrieves the latest live information regarding COVID-19";
        super.cooldown = 1;
    }

    @Override
    protected void handle(CommandContext commandContext) {
        JSONObject jsonObject = WebUtils.parseJson("https://coronavirus-19-api.herokuapp.com/all");
        int cases = (int) jsonObject.get("cases");
        int deaths = (int) jsonObject.get("deaths");
        int recovered = (int) jsonObject.get("recovered");

        EmbedBuilder eb = new EmbedBuilder()
                .setColor(ColorUtils.getInstance().getDefaultColor())
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setTitle("Worldwide Statistics", "https://www.cdc.gov/")
                .setThumbnail("https://feen.us/uc38db.png")
                .addField("Total Cases", NumberFormat.getNumberInstance(Locale.US).format(cases), true)
                .addField("Total Deaths", NumberFormat.getNumberInstance(Locale.US).format(deaths), true)
                .addField("Total Recovered", NumberFormat.getNumberInstance(Locale.US).format(recovered), true)
                .addField("To help prevent the spread of COVID-19, everyone should:",
                        "• Clean your hands often, either with soap and water for 20 seconds or a hand sanitizer that contains at least 60% alcohol." +
                        "\n\n• Avoid close contact with people who are sick." +
                        "\n\n• Put distance between yourself and other people (at least 6 feet)." +
                        "\n\n• Cover your mouth and nose with a cloth face cover when around others." +
                        "\n\n• Cover your cough or sneeze with a tissue, then throw the tissue in the trash." +
                        "\n\n• Clean and disinfect frequently touched objects and surfaces daily." +
                        "\n\n• CDC recommends that people wear cloth face coverings in public settings and when around people outside of their household, especially when other social distancing measures are difficult to maintain." +
                        "\n\n• Cloth face coverings may help prevent people who have COVID-19 from spreading the virus to others.\n \u200B", false);
        commandContext.getEvent().getChannel()
                .sendMessage(eb.build()).queue();
    }
}
