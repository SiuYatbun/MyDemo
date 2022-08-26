package com.example.mydemo.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtil {
    private static final Calendar calendar = Calendar.getInstance();

    public static Date offsetDay(Date date, int amount){
        return offset(date, Calendar.DATE, amount);
    }

    public static Date offsetMonth(Date date, int amount){
        return offset(date, Calendar.MONTH, amount);
    }

    public static Date offsetYear(Date date, int amount){
        return offset(date, Calendar.YEAR, amount);
    }

    public static Date offset(Date date, int field, int amount){
        Objects.requireNonNull(date);
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static String format(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parse(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
