package com.dzieniu2.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateConverterService {

    public static String getCurrentDate() {
        int year = LocalDateTime.now().getYear();
        String month = formatNumber(LocalDateTime.now().getMonthValue());
        String day = formatNumber(LocalDateTime.now().getDayOfMonth());
        String hour = formatNumber(LocalDateTime.now().getHour());
        String minute = formatNumber(LocalDateTime.now().getMinute());
        String second = formatNumber(LocalDateTime.now().getSecond());

        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    public static String formatDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        String month = formatNumber(cal.get(Calendar.MONTH)+1);
        String day = formatNumber(cal.get(Calendar.DAY_OF_MONTH));
        String hour = formatNumber(cal.get(Calendar.HOUR_OF_DAY));
        String minute = formatNumber(cal.get(Calendar.MINUTE));
        String second = formatNumber(cal.get(Calendar.SECOND));

        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    private static String formatNumber(int number) {
        String str = String.valueOf(number);
        return str.length() > 1 ? str : "0"+str;
    }

}
