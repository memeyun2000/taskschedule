package com.sec.schedule.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

/**
 * DateUtils
 */
public class DateUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateStr() {
        return dateFormat.format(new Date());
    }
    public static String getCurrentDateTimeStr() {
        return dateTimeFormat.format(new Date());
    }
    public static String formatDate(Date date,String format) {
        if(date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String dateStrAddDay(String dateStr,int day) {
        Date _date = stringToDateShort(dateStr);
        return formatDate(addDate(_date, "d", day), "yyyy-MM-dd");
    }
    
    public static Date stringToDate(String dateStr,String format){
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr,pos);
    }
    public static Date stringToDateShort(String dateStr) {
        return stringToDate(dateStr,"yyyy-MM-dd");
    }
    public static Date StringToDateTime(String dateStr){
        return stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static long diffTowDate(Date date1 ,Date date2) {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        return (l1 - l2);
    }
    public static long diffTowDateStr(String dateStr1 , String dateStr2){
        return diffTowDate(stringToDateShort(dateStr1), stringToDateShort(dateStr2));
    }

    public static Date addDate(Date date,String type,int iQuantity){
        int year = Integer.parseInt(formatDate(date, "yyyy"));
        int month = Integer.parseInt(formatDate(date, "MM")) - 1;
        int day = Integer.parseInt(formatDate(date, "dd"));
        int hour = Integer.parseInt(formatDate(date, "HH"));
        int mi = Integer.parseInt(formatDate(date, "mm"));
        int ss = Integer.parseInt(formatDate(date, "ss"));

        GregorianCalendar gc = new GregorianCalendar(year, month, day ,hour ,mi ,ss);
        if(type.equalsIgnoreCase("y")) {
            gc.add(GregorianCalendar.YEAR, iQuantity);
        } 
        else if(type.equalsIgnoreCase("m")) {
            gc.add(GregorianCalendar.MONTH, iQuantity);
        }
        else if(type.equalsIgnoreCase("d")) {
            gc.add(GregorianCalendar.DATE, iQuantity);
        }
        else if(type.equalsIgnoreCase("h")) {
            gc.add(GregorianCalendar.HOUR, iQuantity);
        }
        else if(type.equalsIgnoreCase("mi")) {
            gc.add(GregorianCalendar.MINUTE, iQuantity);
        }
        else if(type.equalsIgnoreCase("s")) {
            gc.add(GregorianCalendar.SECOND, iQuantity);
        }
        return gc.getTime();
    }

}