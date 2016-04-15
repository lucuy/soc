package com.util.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 *字符串处理
 * 
 * @author  jiadongxu
 * @version  [版本号, 2012-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class StringTools
{
    
    private static final Map<String, Pattern> patterns = new HashMap();
    
    public static Pattern getPattern(String regex)
    {
        Pattern workPattern = (Pattern)patterns.get(regex);
        if (workPattern == null)
        {
            workPattern = Pattern.compile(regex);
            patterns.put(regex, workPattern);
        }
        return workPattern;
    }
    
    public static boolean contains(String source, String regex)
    {
        Pattern p = getPattern(regex);
        Matcher m = p.matcher(source);
        if (m.find())
        {
            return true;
        }
        return false;
    }
}
