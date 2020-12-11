package com.vyy.scooby.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
    public static DateUtils instance;

    public String getTime(OffsetDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("KK:mm:ss a", Locale.ENGLISH);
        LocalDateTime localDateTime = date.toLocalDateTime();
        String month = date.getMonth().toString().toLowerCase();
        String day = ordinalIndicator(date.getDayOfMonth());
        int year = date.getYear();
        return month.substring(0, 1).toUpperCase() + month.substring(1) + " " + day + " " + year + ", " + localDateTime.format(formatter).toLowerCase();
    }

    private String ordinalIndicator(int day) {
        int mod100 = day % 100;
        int mod10 = day % 10;
        if(mod10 == 1 && mod100 != 11) {
            return day + "st";
        } else if(mod10 == 2 && mod100 != 12) {
            return day + "nd";
        } else if(mod10 == 3 && mod100 != 13) {
            return day + "rd";
        } else {
            return day + "th";
        }
    }
    public static DateUtils getInstance() {
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }
}
