package com.util;

public class IpConvert
{
    /**
     * 将字符串IP地址转换为long型
     * @param strIp
     * @return
     */
    public static long iPTransition(String strIp)
    {
        long longIP = 0;
        if (StringUtil.isNotEmpty(strIp))
        {
            String[] strIps = strIp.split("\\.");
            long lIp0 = Long.parseLong(strIps[0]);
            long lIp1 = Long.parseLong(strIps[1]);
            long lIp2 = Long.parseLong(strIps[2]);
            long lIp3 = Long.parseLong(strIps[3]);
            longIP = (lIp0 << 24) + (lIp1 << 16) + (lIp2 << 8) + lIp3;
        }
        
        return longIP;
    }
    
    /**
     * <将long型IP地址转换为字符串>
     * <功能详细描述>
     * @param lip
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String IpString(long lip)
    {
        StringBuffer sBuffer = new StringBuffer();
        // 直接右移24位  
        sBuffer.append(String.valueOf((lip >>> 24)));
        sBuffer.append(".");
        
        // 将高8位置0，然后右移16位  
        sBuffer.append(String.valueOf((lip & 0x00FFFFFF) >>> 16));
        sBuffer.append(".");
        
        // 将高16位置0，然后右移8位  
        sBuffer.append(String.valueOf((lip & 0x0000FFFF) >>> 8));
        sBuffer.append(".");
        
        // 将高24位置0  
        sBuffer.append(String.valueOf((lip & 0x000000FF)));
        return sBuffer.toString();
    }
    
  
}
