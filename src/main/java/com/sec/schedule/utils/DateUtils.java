package com.sec.schedule.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


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
    public static String dateStrAddDate(String dateStr,String type ,int iQuantity) {
        Date _date = stringToDateShort(dateStr);
        return formatDate(addDate(_date, type, iQuantity), "yyyy-MM-dd");
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

    /**
     * 取月初
     * @param date
     * @return
     */
    public static Date getMonthBegin(Date date){
        String newDateStr = formatDate(date, "yyyy-MM")+"-01";
        //FormatDate(date, "yyyy-MM-dd");
        return stringToDateShort(newDateStr);
    }

    /**
     * 取月末时间
     * @param date 日期
     * @return date
     */
    public static Date getMonthEnd(Date date){
        int year = Integer.parseInt( formatDate(date, "yyyy"));
        int month = Integer.parseInt(formatDate(date, "MM"));
        int day = Integer.parseInt(formatDate(date, "dd"));

        GregorianCalendar calendar = new GregorianCalendar(year,month-1,day,0,0,0);
        int monthLength = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        String newDateStr = formatDate(date,"yyyy")+"-"+formatDate(date,"MM")+"-";
        if(monthLength<10)
            newDateStr+="0"+monthLength;
        else
            newDateStr+=""+monthLength;
        return stringToDateShort(newDateStr);
    }

    /**
     * 取季初
     * @param date
     * @return
     */
    public static Date getSeasonBegin(Date date){
        int month = Integer.parseInt(formatDate(date, "MM"));
        String newDateStr = formatDate(date,"yyyy")+"-";
        if( month>=1 && month<=3 ){
            newDateStr+="01-01";
        }
        else if( month>=4 && month<=6 ){
            newDateStr+="04-01";
        }
        else if( month>=7 && month<=9 ){
            newDateStr+="07-01";
        }
        else if( month>=10 && month<=12 ){
            newDateStr+="10-01";
        }
        return stringToDateShort(newDateStr);
    }

    /**
     * 取季度末时间
     * @param date 日期
     * @return date
     */
    public static Date getSeasonEnd(Date date){
        int month = Integer.parseInt(formatDate(date, "MM"));
        String newDateStr = formatDate(date,"yyyy")+"-";
        if( month>=1 && month<=3 ){
            newDateStr+="03-31";
        }
        else if( month>=4 && month<=6 ){
            newDateStr+="06-30";
        }
        else if( month>=7 && month<=9 ){
            newDateStr+="09-30";
        }
        else if( month>=10 && month<=12 ){
            newDateStr+="12-31";
        }
        return stringToDateShort(newDateStr);
    }
    


    /**
     * 取周初
     * @param date
     * @return
     */
    public static Date getWeekBegin(Date date){

        int year = Integer.parseInt(formatDate(date, "yyyy"));
        int month = Integer.parseInt(formatDate(date, "MM"));
        //月份修正
        month=month-1;
        int day = Integer.parseInt(formatDate(date, "dd"));

        GregorianCalendar gc = new GregorianCalendar(year, month, day
        );

        int week =GregorianCalendar.DAY_OF_WEEK-1;

        if(week==0){
            week=7;
        }

        gc.add(GregorianCalendar.DATE, 0-week+1);

        return gc.getTime();
    }


    /**
     * 取周末
     * @param date
     * @return
     */
    public static Date getWeekEnd(Date date){

        int year = Integer.parseInt(formatDate(date, "yyyy"));
        int month = Integer.parseInt(formatDate(date, "MM"));
        //月份修正
        month=month-1;
        int day = Integer.parseInt(formatDate(date, "dd"));

        GregorianCalendar gc = new GregorianCalendar(year, month, day
        );

        int week =GregorianCalendar.DAY_OF_WEEK-1;

        if(week==0){
            week=7;
        }
        gc.add(GregorianCalendar.DATE, 7-week);

        return gc.getTime();
    }

    /**
     * 取半年初
     * @param date
     * @return
     */
    public static Date getHalfYearBegin(Date date){
        int month = Integer.parseInt(formatDate(date, "MM"));
        String newDateStr = formatDate(date,"yyyy")+"-";
        if( month<=6 ){
            newDateStr+="01-01";
        }
        else{
            newDateStr+="07-01";
        }
        return stringToDateShort(newDateStr);
    }
    
    /**
     * 取半年末
     * @param date
     * @return
     */
    public static Date getHalfYearEnd(Date date){
        int month = Integer.parseInt(formatDate(date, "MM"));
        String newDateStr = formatDate(date,"yyyy")+"-";
        if( month<=6 ){
            newDateStr+="06-30";
        }
        else{
            newDateStr+="12-31";
        }
        return stringToDateShort(newDateStr);
    }

    

    /**
     * 取得年初
     * @param date
     * @return
     */
    public static Date getYearBegin(Date date){
        String newDateStr = formatDate(date,"yyyy")+"-01-01";
        return stringToDateShort(newDateStr);
    }

    /**
     * 是否为年末
     * @param date 时间
     * @return
     */
    public static Date getYearEnd(Date date){
        String newDateStr = formatDate(date,"yyyy")+"-12-31";
        return stringToDateShort(newDateStr);
    }

   
}