package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class GetDateUtil {
	public static List<String> getDate(int day){
		
		List<String> dateList = new ArrayList<String>();
		
		Date date = new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
		for (int i = 0; i < day; i++) {
			
			String str="tbl_"+sdf.format(date.getTime());
			
			dateList.add(str.replace("-", ""));
			
			//日期天数减1,86400000毫秒为一天
			date = new Date(date.getTime() - 86400000);
				
		}
		
		return dateList;
	}
public static List<String> getDate2(int day){
		
		List<String> dateList = new ArrayList<String>();
		
		Calendar cal=Calendar.getInstance();
		
		cal.setTime(new Date());
		
		//获得当天在这一年中的天数，比如2013-01-01是第一天
		
		int dayOfYear=cal.get(Calendar.DAY_OF_YEAR);
		
		for (int i = 0; i < day; i++) {
			
			//得到cal所代表的那天的昨天
			
			cal.set(Calendar.DAY_OF_YEAR, dayOfYear-i);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
			String str="tbl_"+sdf.format(cal.getTime())+"_not_analytic_events";
			
			dateList.add(str.replace("-", ""));
				
		}
		
		return dateList;
	}
/**
 * 传入开始日期、结束日期得到两个日期之间的每一天格式是yyy-MM-dd
 * <功能详细描述>
 * @param string String
 * @return list
 */
public static List<String> getDate3(String startiem,String endtime) {
	List<String> dateList = new ArrayList<String>();
	
   
	try {
		Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startiem);
		 Date	end = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
		 while(start.getTime() <= end.getTime()){
		      dateList.add("tbl_"+new SimpleDateFormat("yyyy-MM-dd").format(start).toString().replace("-", "")+"_not_analytic_events");
		        start = new Date(start.getTime() + 86400000);
		    }
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
	return dateList;
}

}
