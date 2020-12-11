package com.vyy.scooby.util;

import java.awt.*;

public class ColorUtils {
    private static ColorUtils instance;
    public Color getDefaultColor() { return new Color(160,82,45); }
    public Color getSuccessColor() { return new Color(13, 201, 7); }
    public Color getErrorColor() { return new Color(235, 13, 13); }
    public static ColorUtils getInstance() {
        if (instance == null) {
            instance = new ColorUtils();
        }
        return instance;
    }
}
