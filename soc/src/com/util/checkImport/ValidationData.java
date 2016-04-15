package com.util.checkImport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证导入的字符串是否包含特殊字符
 * @author 李长红
 *
 */
public class ValidationData {
	/**
	 * <判断字符串中是否包含特殊字符>
	 * <如果包含就返回true>
	 * @param value
	 * @return boolean
	 */
	 public static boolean regexString(String value){
    	 String regex="^[^`~!@#$%^&*()=|{}':;, \\[\\]<>\\/?~！@#￥……&*（）—|{}【】‘；：”“'\"，、？]+$";
         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
	 /**
	  * <验证IP地址是否合法>
	  * @param ip
	  * @return boolean
	  */
	    public static boolean regexIP(String ip){
	    	  String regex="([0-9]|[1-9]\\d|1\\d{1,2}|2\\d|2[0-3]\\d|24[0-7])\\.(\\d|[1-9]\\d|1\\d{1,2}|2\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{1,2}|2\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{1,2}|2\\d|2[0-4]\\d|25[0-5])";
	          Pattern pattern = Pattern.compile(regex);
	          Matcher matcher = pattern.matcher(ip);
	         return matcher.matches();
	    }
	    public static void main(String[] args) {
			String s = "中_文";
			System.out.println(regexString(s));
			//System.out.println(regexString(s));
		}
}
