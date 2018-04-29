package com.zxpublic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author penggl
 *
 */
public class GetDateUtil {
	
	//字符串转日期  年月日
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//字符串转日期  年月日时分秒
	public static SimpleDateFormat sdf2 =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取过去  任意天内的日期数组
	 * @param intervals intervals天内
	 * @return
	 */
	public static ArrayList<String> getPastDateByDay(int intervals ) {  
       ArrayList<String> pastDaysList = new ArrayList<String>();
       for (int i = intervals-1; i >=0 ; i--) {  
           pastDaysList.add(getPastDate(i));  
       }  
       return pastDaysList;  
    }
	/**
	 * 获取未来  任意天内的日期数组
	 * @param intervals intervals天内
	 * @return
	 */
	public static ArrayList<String> getFetureDateByDay(int intervals ) {  
       ArrayList<String> fetureDaysList = new ArrayList<String>();  
       for (int i = intervals-1; i >=0 ; i--) {  
           fetureDaysList.add(getFetureDate(i));  
       }  
       return fetureDaysList;  
    }
	/**
	 * 获取过去第几天的日期 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");  
       String result = format.format(today);  
       return result;  
	}
	/**
	 * 获取未来 第 past 天的日期
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       String result = format.format(today);  
       return result;  
	} 
	 
    public static void main(String args[]) {
    	
    	/*Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, -1);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);*/
    	try {
			System.out.println(sdf.parse("2017-08-04"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
    }
    /**
     * 传入日期，获取日期对应的月份的所有日期的  字符串 集合
     * @param d
     * @return
     */
    public static ArrayList<String> getOneMonthDays(Date d){
    	ArrayList<String> dayList = new ArrayList<String>();
    	Date date = getMonthStart(d);
        Date monthEnd = getMonthEnd(d);
        while (!date.after(monthEnd)) {
        	dayList.add(sdf.format(date));
            date = getNext(date);
        }
        return dayList;
    }
    /**
     * 获取月初
     * @param date
     * @return
     */
    private static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }
	/**
	 * 获取月末
	 * @param date
	 * @return
	 */
    private static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }
 
    private static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }
}
