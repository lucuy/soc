package com.util;

import java.util.*;
import java.io.*;

import com.util.IpConvert;

public class IpFilterUtil
{
    public static Map<String, ArrayList<String>> isOnlineIp(String ip)
    {
        
        // 在线、离线IP过滤
        Map<String, ArrayList<String>> ipStatus = new HashMap<String, ArrayList<String>>();
        
        ArrayList<String> onlineList = new ArrayList<String>();
        
        ArrayList<String> notOnlineList = new ArrayList<String>();
        
        Process process = null;
        
        BufferedReader bufReader = null;
        
        String bufReadLineString = null;
        
        try
        {
            process = Runtime.getRuntime().exec("nmap -sP " + ip);
            
            bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            while ((bufReadLineString = bufReader.readLine()) != null)
            {
                
                if (bufReadLineString.indexOf("is up") > 0)
                {
                    
                    String onlineIp =
                        bufReadLineString.substring(bufReadLineString.lastIndexOf("(") + 1,
                            bufReadLineString.lastIndexOf(")"));
                    
                    onlineList.add(onlineIp);
                }
                
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        String[] ipw = ip.split("/");
        
        if (ipw.length == 2)
        {
            for (int i = 1; i < 255; i++)
            {
                String notOnlineIp = ipw[0].substring(0, ipw[0].lastIndexOf(".") + 1) + i;
                
                if (!onlineList.contains(notOnlineIp))
                {
                    
                    notOnlineList.add(notOnlineIp);
                    
                }
            }
        }
        else if (ipw.length == 1)
        {
            
            String notOnlineIp = ipw[0];
            
            if (!onlineList.contains(notOnlineIp))
            {
                
                notOnlineList.add(notOnlineIp);
                
            }
            
        }
        
        ipStatus.put("OnlineIps:", onlineList);
        
        ipStatus.put("NotOnlineIps:", notOnlineList);
        
        process.destroy();
        
        return ipStatus;
    }
    
    /**
    * <验证IP是否在线>
    * <功能详细描述>
    * @0.0.0.0到255.255.255.255
    * @see [类、类#方法、类#成员]
    */
    public static boolean checkIpIsOnline(String ip)
    {
        boolean flag = false;
       /* synchronized (this)
        {*/
            Process process = null;
            BufferedReader bufReader = null;
            String bufReadLineString = null;
            try
            {
                process = Runtime.getRuntime().exec("nmap -sP " + ip);
                bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                
                while ((bufReadLineString = bufReader.readLine()) != null)
                {
                    if (bufReadLineString.indexOf("is up") > 0)
                    {
                        flag = true;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
       // }
        return flag;
    }
    
    /**
    * <指定IP端获取在线IP列表>
    * <功能详细描述>
    * @参数[0.0.0.0.0-255.255.255.255]范围
    * @see [类、类#方法、类#成员]
    */
    public static List<String> onLineIps(String ipArea)
    {
        String[] ips = ipArea.split("-");
        
        String startIp = ips[0];
        String endIp = ips[1];
        
        //在线IP列表
        List<String> onLineIp = new ArrayList<String>();
        
        // 得到起始IP的long值
        long startIpLong = IpConvert.iPTransition(startIp);
        
        // 得到结束IP的long值
        long endIpLong = IpConvert.iPTransition(endIp);
        
/*        // 判断起始IP与结束IP是否在同一个网段内
        if(endIpLong >= map.get("ipSectionStart") && endIpLong <= map.get("ipSectionEnd"))
        {*/
            // 若起始IP与结束IP在同一个网段内
            // 遍历起始IP到结束IP之间的IP
            for(long i = startIpLong; i <= endIpLong; i++)
            {
                String ip = IpConvert.IpString(i);
                
                // 使用nmap扫描确定此IP是否在线
                Process process = null;
                BufferedReader bufReader = null;
                String bufReadLineString = null;
                try
                {
                    process = Runtime.getRuntime().exec("nmap -sP " + ip);
                    bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    
                    while ((bufReadLineString = bufReader.readLine()) != null)
                    {
                        if (bufReadLineString.trim().indexOf("(1 host up)") > 0)
                        {
                            //将IP添加到在线IP列表中
                            onLineIp.add(ip);
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        //}
    
        return onLineIp;
    }
    
    public static void main(String[] args)
    {
        IpFilterUtil ipcheck = new IpFilterUtil();
        /* String ip = "192.168.1.11";
        boolean flag =  ipcheck.checkIpIsOnline(ip);
        System.out.println(flag);*/
        /*String ip = "192.168.1.0/24";     

        Map<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();

        map = ipcheck.isOnlineIp(ip);

        Iterator it = map.keySet().iterator();

        while(it.hasNext()){
            String str = (String)it.next();
            System.out.println(str+":");
            ArrayList<String> list = new ArrayList<String>();
            list = map.get(str);
            Iterator it2 = list.iterator();
            while(it2.hasNext()){
                System.out.println((String)it2.next());
            }

        }*/
        long start = new Date().getTime();
        List<String> onLine = ipcheck.onLineIps("192.168.1.1-192.168.1.254");
        
        for (int i = 0; i < onLine.size(); i++)
        {
            System.out.println(onLine.get(i) + "----------------------------");
        }
        System.out.println("扫描254个IP的时间： " + (new Date().getTime() - start) + "毫秒");
        //acquireIpBelongSection("192.168.1.23");
        
    }
    
    /**
     * <获得IP所属的网段>
     * <功能详细描述>
     * @param ip
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Map<String, Long> acquireIpBelongSection(String ip)
    {
        Map<String, Long> map = new HashMap<String, Long>();
        
        // 获得IP地址前三段
        String net = ip.substring(0, ip.lastIndexOf(".") + 1);
        
        // 获得ip所属IP段的起始IP
        String ipSectionStart = net + "1";
        
        // 获得ip所属IP段的结束IP
        String ipSectionEnd = net + "254";
        
        long startIpLong = IpConvert.iPTransition(ipSectionStart);
        long endIpLong = IpConvert.iPTransition(ipSectionEnd);
        
        map.put("ipSectionStart", startIpLong);
        map.put("ipSectionEnd", endIpLong);
        
        return map;
    }
    
}
