package com.hgits.hotc.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static Date parse(String dateStr){
        LocalDateTime localDateTime = parseDate(dateStr);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime parseDate(String dateStr){
        if(dateStr == null || dateStr.length() == 0) return null;

        LocalDateTime localDateTime = null;
        try {
            localDateTime = LocalDateTime.parse(dateStr, FORMATTER);
        } catch (Exception e1) {
            try {
                localDateTime = LocalDateTime.parse(dateStr, FORMATTER1);
            } catch (Exception e2) {
                try {
                    localDateTime = LocalDateTime.parse(dateStr, FORMATTER2);
                } catch (Exception e3){
                    throw new RuntimeException(e3);
                }
            }

        }

        return localDateTime;
    }

    public static String format(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return "";
        return localDateTime.format(FORMATTER);
    }
}
