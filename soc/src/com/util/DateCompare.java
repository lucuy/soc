/*
 * 文 件 名:  DateCompare.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2013-2-22
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util;



import java.util.Calendar;

/**
 * <两个时间比较相差多少年月日>
 * <两个String类型的时间比较>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2013-2-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DateCompare
{
   public static Calendar getCal(String date)
    {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Integer.parseInt(date.substring(0, 4)),
            Integer.parseInt(date.substring(4, 6)) - 1,
            Integer.parseInt(date.substring(6, 8)));
        return cal;
    }
    
   /**
    * <前面的日期小于后边的日期>
    * <功能详细描述>
    * @param src
    * @param dst
    * @return
    * @see [类、类#方法、类#成员]
    */
    
   public static String compareYMD(String src, String dst)
    {
        //Calendar srcCal = Calendar.getInstance();
        Calendar srcCal = getCal(src);
        // srcCal.setTime(src);
        // Calendar dstCal = Calendar.getInstance();
        //  dstCal.setTime(dst);
        Calendar dstCal = getCal(dst);
        
        // 比较年月日
        int year = dstCal.get(Calendar.YEAR) - srcCal.get(Calendar.YEAR);
        int month = dstCal.get(Calendar.MONTH) - srcCal.get(Calendar.MONTH);
        int day = dstCal.get(Calendar.DAY_OF_MONTH) - srcCal.get(Calendar.DAY_OF_MONTH);
        // 实际年份差：
        year = year - ((month > 0) ? 0 : ((month < 0) ? 1 : ((day >= 0 ? 0 : 1))));
        // 实际月份差：
        
        if (month != 0)
        {
            month = (month < 0) ? (day > 0 ? 12 + month : 12 + month - 1) : (day >= 0 ? month : month - 1);
        }
        else
        {
            month =( day < 0 )?(11):month;
        }
        // 去除年月之后的剩余的实际天数差：
        dstCal.add(Calendar.MONTH, -1);
        
        day = (day < 0) ? (perMonthDays(dstCal)) + day : day;
        String ages = year + "年" + month + "月" + day + "天";
        return ages;
    }
    
    /**
     * 判断一个时间所在月有多少天
     *
     * @param Calendar
     *            具体时间的日历对象
     * @throws ParseException
     */
   public static int perMonthDays(Calendar cal)   
    {
        int maxDays = 0;
        int month = cal.get(Calendar.MONTH);
        switch (month)
        {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                maxDays = 31;
                break;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                maxDays = 30;
                break;
            case Calendar.FEBRUARY:
                if (isLeap(cal.get(Calendar.YEAR)))
                {
                    maxDays = 29;
                }
                else
                {
                    maxDays = 28;
                }
                break;
        }
        return maxDays;
    }
    
    /**
     * 判断某年是否是闰年
     *
     * @param year
     *            年份
     * @throws ParseException
     */
   public static boolean isLeap(int year)
    {
        boolean leap = false;
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        {
            leap = true;
        }
        return leap;
    }
}
