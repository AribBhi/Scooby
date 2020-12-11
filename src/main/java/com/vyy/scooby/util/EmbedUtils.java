package com.vyy.scooby.util;

import com.vyy.scooby.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

public class EmbedUtils {
    private static EmbedUtils instance;
    public EmbedBuilder simpleEmbedImage(String title, String image, String url, Member m) {
        return new EmbedBuilder()
                .setTitle(title, url)
                .setImage(image)
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com", getMemberIcon(m))
                .setColor(ColorUtils.getInstance().getDefaultColor());
    }
    public EmbedBuilder simpleEmbedImageNoTitle(String image, Member m) {
        return new EmbedBuilder()
                .setImage(image)
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com", getMemberIcon(m))
                .setColor(ColorUtils.getInstance().getDefaultColor());
    }
    public EmbedBuilder simpleEmbedText(String title, String text, Member m) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(text)
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com", getMemberIcon(m))
                .setColor(ColorUtils.getInstance().getDefaultColor());
    }
    public EmbedBuilder simpleEmbedTextNoMember(String title, String text) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(text)
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setColor(ColorUtils.getInstance().getDefaultColor());
    }
    public EmbedBuilder simpleEmbedTextNoTitle(String text, Member m) {
        return new EmbedBuilder()
                .setDescription(text)
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com", getMemberIcon(m))
                .setColor(ColorUtils.getInstance().getDefaultColor());
    }
    public EmbedBuilder successEmbedText(String title, String text, Member m) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(text)
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com", getMemberIcon(m))
                .setColor(ColorUtils.getInstance().getSuccessColor());
    }
    public EmbedBuilder errorEmbedText(String title, String text, Member m) {
        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(text)
                .setFooter("Scooby v" + Constants.VERSION + " | Prototype", Constants.ICON)
                .setAuthor(m.getEffectiveName() + "#" + m.getUser().getDiscriminator(), "https://discordapp.com", getMemberIcon(m))
                .setColor(ColorUtils.getInstance().getErrorColor());
    }
    public String getMemberIcon(final Member m) {
        return m.getUser().getEffectiveAvatarUrl().replaceFirst(".jpg", ".png");
    }
    public static EmbedUtils getInstance() {
        if (instance == null) {
            instance = new EmbedUtils();
        }
        return instance;
    }
}
