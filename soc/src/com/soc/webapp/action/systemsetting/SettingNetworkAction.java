package com.soc.webapp.action.systemsetting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.taskdefs.Nice;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ha.client.Client;
import com.soc.model.systemsetting.Setting;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.systemsetting.SystemCenterIpService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FileUtil;
import com.util.OSUtil;
import com.util.StringUtil;

public class SettingNetworkAction extends BaseAction
{
    private static final long serialVersionUID = 553469008843077565L;
    
    private SettingService settingManager;
    
    private Setting setting;
    
    private Integer id;
    
    private String ids;
    
    private String b1Ip;
    private String b1Code;
    private String b1Gate;
    
    private String b2Ip;
    private String b2Code;
    private String b2Gate;
    
    private String b3Ip;
    private String b3Code;
    private String b3Gate;
    
    private String b4Ip;
    private String b4Code;
    private String b4Gate;
    
    private String b5Ip;
    private String b5Code;
    private String b5Gate;
    
    private String b6Ip;
    private String b6Code;
    private String b6Gate;
    
    
    private String b7Ip;
    private String b7Code;
    private String b7Gate;
    
    private String b8Ip;
    private String b8Code;
    private String b8Gate;
    
    private String b9Ip;
    private String b9Code;
    private String b9Gate;
    
    private String b10Ip;
    private String b10Code;
    private String b10Gate;
    
    private String consoleIp;
    
    private List<Setting> settingList;
    
    private Integer cardSize = 4;
    
    private String routeInfo;
    
    private String interfacesName;
    
    private String warning;
    
    private List<Map> routeList;
    
    private List<Map> staticRouteList;
    
    private List<Setting> interfacesNameList;
    
    private String pingIP;
    
    private AuditService auditManager;
    
    //判断是否更改路由的表示0代表未更改,1代表更改
    private String routeflag="0";
    
    
  

	public String getRouteflag() {
		return routeflag;
	}

	public void setRouteflag(String routeflag) {
		this.routeflag = routeflag;
	}

	private SystemCenterIpService systemCenterIpManager;
    
    public SystemCenterIpService getSystemCenterIpManager() {
		return systemCenterIpManager;
	}
    
	public void setSystemCenterIpManager(SystemCenterIpService systemCenterIpManager) {
		this.systemCenterIpManager = systemCenterIpManager;
	}
	
	/**
     * 双机热备
     */
   	private String serverNetJson;
	private String ldapNetJson;
	private int type;
	private int iden;
	private String startStr;
	private String serverStart;
	private String ldapStart;

    
    
    /**
     * 负载均衡转发程序
     * @return
     */
    /* public String loadBalancing(int commandPort, int timeout)
     {
         // 取得所有负载均衡IP
         Map map = new HashMap<String, String>();
         map.put("key", "console_ip");
         List<Setting> settingList = settingManager.queryByConIP(map);
         
         for (Setting setting : settingList)
         {
             //System.out.println(setting.getValue());
             
             try
             {
                 Socket socket = new Socket();
                 SocketAddress socketAddress = new InetSocketAddress(setting.getValue(), commandPort);
                 socket.connect(socketAddress, timeout);
                 
                 return setting.getValue();
             }
             catch (IOException e)
             {
                 ////System.out.println("io exception");
                 //e.printStackTrace();
                 continue;
             }
         }
         
         return "127.0.0.1";
     }*/
    
    /**
     * 查询网卡配置信息
     * @return
     */
    public String query()
    {
        
        b1Ip=settingManager.queryByKey("card1_ip");
        b1Code=settingManager.queryByKey("card1_code");
        b1Gate=settingManager.queryByKey("card1_gate");
        
        b2Ip=settingManager.queryByKey("card2_ip");
        b2Code=settingManager.queryByKey("card2_code");
        b2Gate=settingManager.queryByKey("card2_gate");
        
        b3Ip=settingManager.queryByKey("card3_ip");
        b3Code=settingManager.queryByKey("card3_code");
        b3Gate=settingManager.queryByKey("card3_gate");
        
        b4Ip=settingManager.queryByKey("card4_ip");
        b4Code=settingManager.queryByKey("card4_code");
        b4Gate=settingManager.queryByKey("card4_gate");
        
        b5Ip=settingManager.queryByKey("card5_ip");
        b5Code=settingManager.queryByKey("card5_code");
        b5Gate=settingManager.queryByKey("card5_gate");
        
        b6Ip=settingManager.queryByKey("card6_ip");
        b6Code=settingManager.queryByKey("card6_code");
        b6Gate=settingManager.queryByKey("card6_gate");
        
        b7Ip=settingManager.queryByKey("card7_ip");
        b7Code=settingManager.queryByKey("card7_code");
        b7Gate=settingManager.queryByKey("card7_gate");
        
        b8Ip=settingManager.queryByKey("card8_ip");
        b8Code=settingManager.queryByKey("card8_code");
        b8Gate=settingManager.queryByKey("card8_gate");
        
        b9Ip=settingManager.queryByKey("card9_ip");
        b9Code=settingManager.queryByKey("card9_code");
        b9Gate=settingManager.queryByKey("card9_gate");
        
        b10Ip=settingManager.queryByKey("card10_ip");
        b10Code=settingManager.queryByKey("card10_code");
        b10Gate=settingManager.queryByKey("card10_gate");
        
        //设置页面显示的网卡的个数
        Map<String, String> mapcard = new HashMap<String, String>();
        mapcard.put("key", "card");
        if (settingManager.queryByKey("cardSize") != null)
        {
            cardSize = Integer.parseInt(settingManager.queryByKey("cardSize"));
        }
        if (cardSize == 0)
        {
            cardSize = 4;
        }
        flush_route_list();
        
        //页面显示数据库中存储的静态路由的信息显示到静态路由的表格中
        //String r = settingManager.queryByKey("route_info");
        String r = null;
        Setting se = new Setting();
        se.setKey("route_info");
        List<Setting> list = settingManager.query(se);
        for (Setting s : list)
        {
            id = s.getId();
            r = s.getValue();
        }
        
        if (StringUtil.isNotBlank(r))
        {
            staticRouteList = new ArrayList<Map>();
            String[] tr = StringUtil.split(r, ";");
            
            for (int j = 0; j < tr.length-1; j++)
            {
                String[] td = StringUtil.split(tr[j], ",");
                Map<String, String> m = new HashMap<String, String>();
                for (int k = 0; k < td.length; k++)
                {
                    m.put("td_" + k, td[k]);
                }
                staticRouteList.add(j, m);
            }
        }
        
        Map<String, String> m = new HashMap<String, String>();
        m.put("key", "interfaces_bk");
        interfacesNameList = settingManager.queryByConIP(m);
        
        //得到负载均衡ip列表
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", "console_ip");
        settingList = settingManager.queryByConIP(map);
        return SUCCESS;
    }
    
    /**
     * 保存网卡个数
     */
    public void saveCardSize()
    {
        List<String> fieldList = new ArrayList<String>();
        Setting setting = new Setting();
        setting.setKey("cardSize");
        setting.setValue(String.valueOf(cardSize));
        settingManager.updateByKey("cardSize", setting);
        
        fieldList.add(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserLoginName());
       auditManager.insertByUpdateOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "网络设置", super
            .getRequest().getRemoteAddr(), fieldList);
      //syslog
       /* String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :网络设置";
        
        logManager.writeSystemAuditLog(logString);*/
       logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改网络设置");
        
    }
    
    /**
     * 更新网卡信息
     * 2014年2月19日加入判断eth0网卡的地址是否更改，更改的话，先下发配置文件，然后在进行网卡的更改。
     * 
     * @return
     */
    public void insertCard()
    {
       
    	HttpServletRequest request = super.getRequest();
        HttpServletResponse response = super.getResponse();
        
        boolean routeFlag = true; // 标识路由是否已在interfaces文件中配置过（true:未配置 | false:已配置）
        int cardFlag = 1;
       
        //判断不是更改路由的时候走的方法
        if(routeflag.equals("0"))
        {
        //判断网卡1是否更改！
        
           testEth0Changed();
        
        }
        // 将路由信息存入数据库
        if (StringUtil.isNotBlank(routeInfo))
        {
            List<String> fieldList = new ArrayList<String>();
            for (; cardFlag <= 10; cardFlag++)
            {
                if (StringUtil.isNotBlank(settingManager.queryByKey("card" + cardFlag + "_ip")))
                {
                    break;
                }
            }
            if (cardFlag <= 10)
            {
                String rstr = settingManager.queryByKey("route_info");
                routeInfo= routeInfo+";";
                if (StringUtil.isNotBlank(rstr))
                {
                    routeInfo = rstr + routeInfo;
                }
                Setting s1 = new Setting();
                s1.setKey("route_info");
                s1.setValue(routeInfo);
                settingManager.updateByKey("route_info", s1);
                
                fieldList.add(((User) this.getSession()
                    .getAttribute("SOC_LOGON_USER")).getUserLoginName());
                  auditManager.insertByInsertOperator(((User) this.getSession()
                    .getAttribute("SOC_LOGON_USER")).getUserId(), "路由表", super
                    .getRequest().getRemoteAddr(), fieldList);
               
                //syslog
               /* String logString = "";
                
                logString =
                    "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                        + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :添加网卡";
                
                logManager.writeSystemAuditLog(logString);*/
                  logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
      					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增网卡");
            }
        }
        else if (StringUtil.isBlank(ids))
        {
            // 将网卡信息存入数据库
            for (int i = 1; i <= cardSize; i++)
            {
                String key = request.getParameter("b" + i + "Ip");
                String code = request.getParameter("b" + i + "Code");
                String gate = request.getParameter("b" + i + "Gate");
                Setting setting = new Setting();
                setting.setKey("card" + i + "_ip");
                setting.setValue(key);
                settingManager.updateByKey("card" + i + "_ip", setting);
                setting.setKey("card" + i + "_code");
                setting.setValue(code);
                settingManager.updateByKey("card" + i + "_code", setting);
                setting.setKey("card" + i + "_gate");
                setting.setValue(gate);
                settingManager.updateByKey("card" + i + "_gate" , setting);
            }
        }
        else
        {
            if (StringUtil.isNotBlank(ids))
            {
            	//删除路由表信息
                List<String> fieldList = new ArrayList<String>();
                String route = "";
               // String route
                String[] rid = ids.split(",");
                String aStr = settingManager.queryByKey("route_info");
                
                if (StringUtil.isNotBlank(aStr))
                {
                    String[] tr = aStr.split(";");
                    for (String id : rid)
                    {
                        for (String t : tr)
                        {
                            if (t.contains(id))
                            {
                                route=route+t+";";
                            }
                           /* else
                            {
                            if ("".equals(route))
                            {
                                route = t;
                            }
                            else
                            {
                                route += ";" + t;
                            }
                            }*/
                        }
                    }
                }
                String routeDlete[] = route.split(";");
                System.out.println(routeDlete.length);
                for(int i=0;i<routeDlete.length;i++)
                {
                	String routeTemp = routeDlete[i]+";";
                	if(aStr.contains(routeTemp))
                	{
                		aStr=aStr.replace(routeTemp, "");
                	}
                }
                route=aStr;
               // String 
                Setting setting = new Setting();
                setting.setKey("route_info");
                setting.setValue(route);
                settingManager.updateByKey("route_info", setting);
                
                fieldList.add(((User) this.getSession()
                    .getAttribute("SOC_LOGON_USER")).getUserLoginName());
                auditManager.insertByDeleteOperator(((User) this.getSession()
                    .getAttribute("SOC_LOGON_USER")).getUserId(), "路由表", super
                    .getRequest().getRemoteAddr(), fieldList);
                
                
            }
        }
        if (cardFlag <= 10)
        {
            if (StringUtil.equals(OSUtil.getOSName().trim().toLowerCase(), "linux"))
            {
                StringBuffer cfgStrBuf = new StringBuffer();
                
                cfgStrBuf.append("# This file describes the network interfaces available on your system\n");
                cfgStrBuf.append("# and how to activate them. For more information, see interfaces(5).\n");
                cfgStrBuf.append("\n");
                cfgStrBuf.append("# The loopback network interface\n");
                cfgStrBuf.append("\n");
                
                cfgStrBuf.append("auto lo\n");
                cfgStrBuf.append("iface lo inet loopback\n");
                cfgStrBuf.append("\n");
                cfgStrBuf.append("# The primary network interface\n");
                
                cfgStrBuf.append("\n");
                
                for (int i = 1; i <= cardSize; i++)
                {
                    String nicIp = settingManager.queryByKey("card" + i + "_ip");
                    String nicNetmask = settingManager.queryByKey("card" + i + "_code");
                    String nicGate = settingManager.queryByKey("card" + i + "_gate");
                    if (StringUtil.isNotBlank(nicIp) && StringUtil.isNotBlank(nicNetmask))
                    {
                        // 修改的网卡
                        log.info("id:" + i + "; address:" + nicIp);
                        cfgStrBuf.append("allow-hotplug eth" + (i - 1) + "\n");
                        cfgStrBuf.append("iface eth" + (i - 1) + " inet static\n");
                        cfgStrBuf.append("address " + nicIp + "\n");
                        cfgStrBuf.append("netmask " + nicNetmask + "\n");
                       //允许gateWay为空功能  增加网关判断
                        if(StringUtil.isNotBlank(nicGate))cfgStrBuf.append("gateway " + nicGate + "\n");
                        
                        if (routeFlag)
                        {
                            // 添加路由配置
                            String rstr = settingManager.queryByKey("route_info");
                            if (StringUtil.isNotBlank(rstr))
                            {
                                String[] tr = StringUtil.split(rstr, ";");
                                for (int j = 0; j < tr.length; j++)
                                {
                                    String[] td = StringUtil.split(tr[j], ",");
                                    String route = "";
                                    for (int k = 0; k < td.length; k++)
                                    {
                                        if (StringUtil.isNotBlank(td[k]) || td[k] != "")
                                        {
                                            if (k == 0)
                                            {
                                                route = route + "-net " + td[k];
                                            }
                                            if (k == 1)
                                            {
                                                route = route + " netmask " + td[k];
                                            }
                                            if (k == 2)
                                            {
                                                route = route + " gw " + td[k];
                                            }
                                        }
                                    }
                                    if (route != "")
                                    {
                                        cfgStrBuf.append("up route add " + route + "\n");
                                    }
                                }
                            }
                            routeFlag = false;
                        }
                        cfgStrBuf.append("\n");
                        cfgStrBuf.append("auto eth" + (i - 1) + "\n");
                        cfgStrBuf.append("\n");
                    }
                }
                
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                Date date = new Date();
                
                File fDir = new File("/home/interfaces_backup");
                
                if(!fDir.isDirectory())
                {
                	fDir.mkdir();
                }
                
                File f2 = new File("/home/interfaces_backup/interfaces.bk_" + df.format(date));
                
                File f1 = new File("/etc/network/interfaces");
                
                try
                {
                    // 判断interfaces文件是否存在，不存在则创建
                    if (!f1.exists())
                    {
                        FileUtil.makeFile("/etc/network/interfaces");    
                    }
                    
                    // 备份原有的interfaces文件
                    FileUtil.copyFile(f1, f2);
                    
                    System.out.println("netWorkinterface copy successed");
                }
                catch (IOException e1)
                {
                    System.err.println(df.format(date) + "时复制文件interfaces失败...");
                    e1.printStackTrace();
                }
                // 删除最早存入/home/interfaces_backup/的interfaces备份文件
                if (StringUtil.isNotBlank(settingManager.queryByKey("interfaces_bk1")))
                {
                    try
                    {
                        FileUtil.delFileByName("/home/interfaces_backup/" + settingManager.queryByKey("interfaces_bk1"));
                    }
                    catch (Exception e)
                    {
                        System.err.println(df.format(date) + "删除文件" + settingManager.queryByKey("interfaces_bk1")
                            + "时失败...");
                        e.printStackTrace();
                    }
                }
                
                // 将数据库中原有的interfaces备份文件的文件名循环赋给前一个interfaces_bk
                for (int i = 2; i <= 10; i++)
                {
                    Setting s = new Setting();
                    s.setKey("interfaces_bk" + (i - 1));
                    s.setValue(settingManager.queryByKey("interfaces_bk" + i));
                    settingManager.updateByKey("interfaces_bk" + (i - 1), s);
                }
                
                // 将新的备份文件的文件名存入数据库
                Setting s10 = new Setting();
                s10.setKey("interfaces_bk10");
                s10.setValue("interfaces.bk_" + df.format(date));
                settingManager.updateByKey("interfaces_bk10", s10);
                
                try
                {
                    // 将新的配置注入interfaces文件
                    FileUtil.WriteFile("/etc/network/interfaces", cfgStrBuf.toString(), false);
                    Runtime.getRuntime().exec("/etc/init.d/networking restart");
                    // 刷新路由表
                    Runtime.getRuntime().exec("ip ro flush cache");
                    if (StringUtil.isBlank(ids))
                    {
                        response.getWriter().write("配置保存成功！请重新登录系统！");
                    }
                    else
                    {
                        response.getWriter().write("路由表删除成功，请重新登录！");
                    }
                    
                }
                catch (Exception e)
                {
                    //System.out.println("注入interfaces文件失败...");
                    e.printStackTrace();
                }
            }
            else
            {
                // OS不符
                try
                {
                    response.getWriter().write("系统不符");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            // wangka不符
            try
            {
                response.getWriter().write("请配置网卡信息");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
   
    /**
     * 判断eth0口的地址是否更改，更改的话，进行更改系统设置的网络配置，以及下发配置文件。
     */
    public void testEth0Changed()
    {   
    	if(StringUtil.isBlank(ids))
    	{
    		
    	HttpServletRequest request = super.getRequest();
    	String ip1 = settingManager.queryByKey("card1_ip");
    	String ipChanged =request.getParameter("b1Ip");
    	if(!ip1.equalsIgnoreCase(ipChanged))
    	{
    		Setting setting = new Setting();
    		setting.setKey("centerNatIp");
    		setting.setValue(ipChanged);
    		settingManager.updateByKey("centerNatIp", setting);
    		setting.setKey("centerNatUpIp");
    		setting.setValue(ipChanged);
    		settingManager.updateByKey("centerNatUpIp", setting);
    		
    		//重新下发配置文件：
    		systemCenterIpManager.updateConf();
    		
    		/*long temp1 = System.currentTimeMillis();
    		try {
    			
				Thread.sleep(120000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("设置失败");
				e.printStackTrace();
			}
    		long temp2 =System.currentTimeMillis();
    		*/
    		/*System.out.println("等待的时间为:"+(temp2-temp1));*/
    	}
    	else
    	{
    		log.info("IP1未进行修改");
    	}
    	}
    	
    }
    /**
     * 执行ping
     * @return
     */
    public String executePing()
    {
        
        if (StringUtil.isNotBlank(pingIP))
        {
            
        }
        
        return SUCCESS;
    }
    
    public void flush_route_list()
    {
        HttpServletResponse response = super.getResponse();
        Process p = null;
        routeList = new ArrayList<Map>();
        Map<String, String> map = null;
        if (StringUtil.equals(OSUtil.getOSName().trim().toLowerCase(), "linux"))
        {
            // 路由列表显示
            try
            {
                p = Runtime.getRuntime().exec("route -n");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            try
            {
                br.readLine();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            String str = null;
            StringTokenizer token = null;
            try
            {
                while ((str = br.readLine()) != null)
                {
                    map = new HashMap<String, String>();
                    token = new StringTokenizer(str);
                    if (token.hasMoreTokens())
                    {
                        map.put("col1", token.nextToken());
                        map.put("col2", token.nextToken());
                        map.put("col3", token.nextToken());
                        map.put("col4", token.nextToken());
                        map.put("col5", token.nextToken());
                        map.put("col6", token.nextToken());
                        map.put("col7", token.nextToken());
                        map.put("col8", token.nextToken());
                    }
                    routeList.add(map);
                    map = null;
                }
                JSONArray jsonArray = JSONArray.fromObject(routeList);
                response.getWriter().write(jsonArray.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (br != null)
                {
                    try
                    {
                        br.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            warning = "路由列表功能必须部署在liunx系统上";
        }
    }
    
    public void flush()
    {
        HttpServletResponse response = super.getResponse();
        List<Map> list = new ArrayList<Map>();
        Map<String, String> m = new HashMap<String, String>();
        m.put("key", "interfaces_bk");
        interfacesNameList = settingManager.queryByConIP(m);
        m = new HashMap<String, String>();
        for (Setting s : interfacesNameList)
        {
            m.put("id", String.valueOf(s.getId()));
            m.put("key", s.getKey());
            m.put("value", s.getValue());
            m.put("type", String.valueOf(s.getType()));
            list.add(m);
        }
        
        JSONArray jsonArray = JSONArray.fromObject(list);
        //System.out.println("jsonArray: " + jsonArray.toString());
        try
        {
            response.getWriter().write(jsonArray.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /*
     * 查看interfaces备份文件的方法
     */
    public void lookInterfaces()
    {
        HttpServletRequest request = super.getRequest();
        HttpServletResponse response = super.getResponse();
        String name = (String)request.getAttribute("interfacesName");
        BufferedInputStream bis = null;
        if (StringUtil.isNotBlank(name))
        {
            File file = new File("/home/interfaces_backup/" + name);
            if (file.exists())
            {
                try
                {
                    bis = new BufferedInputStream(new FileInputStream(file));
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                byte[] b = new byte[4096];
                try
                {
                    int i = bis.read(b);
                    while (i != -1)
                    {
                        i = bis.read(b);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                try
                {
                    response.getWriter().write(new String(b));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (bis != null)
                {
                    try
                    {
                        bis.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                System.out.println("文件" + name + "不存在");
            }
        }
    }
    

    
    
    //获取网卡文件中的配置内容 并返回Mac地址
    public String str(String networkNo){
	    String path="/etc/sysconfig/network-scripts/"+networkNo;
	    String content="";      //content保存文件内容，
	    String HWADDR = "";
	    String UUID = "";
	    BufferedReader reader=null; //定义BufferedReader
	  
	    try{
	      reader=new BufferedReader(new FileReader(path));
	      //按行读取文件并加入到content中。
	      //当readLine方法返回null时表示文件读取完毕。
	      String line;
	      while((line=reader.readLine())!=null){
	        content+=line+",";
	      }
	    }catch(IOException e){
	      e.printStackTrace();
	    }finally{
	  
	      //最后要在finally中将reader对象关闭
	      if(reader!=null){
	        try{
	          reader.close();
	        }catch(IOException e){
	          e.printStackTrace();
	        }
	      }
	   }
	    String [] centOsIP = content.split(",");
	    for (int i = 0; i < centOsIP.length; i++) {
			String[] strAddress = centOsIP[i].split("=");
			String str = strAddress[0];
			if (str.equals("HWADDR")) {
				HWADDR = strAddress[1];
			}
			if (str.equals("UUID")) {
				UUID = strAddress[1];
			}
		}
	    System.out.println(HWADDR+"--"+UUID);
		return HWADDR+"--"+UUID;
}
    
    //获得系统是主机还是备机
    public int queryStatus(){
		File hostFile = new File("/opt/heartbeat/etc/ha.d/haresources");
		BufferedReader hostNameBuff = null;
		BufferedReader localNameBuff = null;
		if(hostFile.exists()){
			
			int status = 0;			 // 1 为 主机， 0 为备机
			try {
				hostNameBuff = new BufferedReader(new InputStreamReader(new FileInputStream(hostFile)), 1024);
				String command = "hostname";
				String hostName = null;  // 本机计算机名称
				String localName = null; // 双机配置文件中表示主机的计算机名称
				try {
					// 执行命令
					Process proc = Runtime.getRuntime().exec(command);
					localNameBuff = new BufferedReader(new InputStreamReader(proc.getInputStream()), 1024);
					while ((localName = localNameBuff.readLine()) != null && (hostName = hostNameBuff.readLine()) != null) {
							String[] temp = hostName.split(" ");
						if (temp[0].equals(localName)) {
							status = 1;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} finally {
				if (hostNameBuff != null) {
					try {
						hostNameBuff.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (localNameBuff != null) {
					try {
						localNameBuff.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return status;
		}else{
			return 1;
		}
	}
    
    
    /*
     * 恢复网络设置
     */
    public void recoverNetwork()
    {
        HttpServletRequest request = super.getRequest();
        HttpServletResponse response = super.getResponse();
        String name = (String)request.getAttribute("interfacesName");
        if (StringUtil.equals(OSUtil.getOSName().trim().toLowerCase(), "linux"))
        {
            if (StringUtil.isNotBlank(name))
            {
                File file1 = new File("/home/interfaces_backup/" + name);
                if (file1.exists())
                {
                    File file2 = new File("/etc/network/interfaces");
                    if (file2.exists())
                    {
                        try
                        {
                            FileUtil.delFileByName("/etc/network/interfaces");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    try
                    {
                        FileUtil.copyFile(file1, file2);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        Runtime.getRuntime().exec("/etc/init.d/networking restart");
                        // 刷新路由表
                        Runtime.getRuntime().exec("ip ro flush cache");
                        response.getWriter().write("success");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    // response.getWriter().write("success");
                }
                else
                {
                    //System.out.println("文件" + interfacesName + "不存在");
                }
            }
        }
        else
        {
            // OS不符
            try
            {
                response.getWriter().write("error_os");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
    public String updateNet() {
		if(null != serverNetJson && !serverNetJson.isEmpty()){
			String data = settingManager.queryByKey("SERVER_NET");
			
			if(data == null || data.isEmpty()){
				Setting setting = new Setting();
				setting.setKey("SERVER_NET") ; 
				setting.setValue(serverNetJson);
				settingManager.updateByKey("SERVER_NET", setting);
				return SUCCESS;
			}
			
			JSONObject object = settingManager.getHaMessage(data);
			String address = object.getString("mainIp")+","+object.getString("stanIp");
			Client client = new Client("localhost");
			System.out.println("localhost--IP:"+client.toString());
			Socket so = client.connect();
			try {
				
				BufferedReader bReader = new BufferedReader(
						new InputStreamReader(so.getInputStream()));
				PrintWriter out = new PrintWriter(so.getOutputStream(),
						true);
				String tempStr = bReader.readLine();
				if ("OK".equals(tempStr.trim())) {
					
					
					out.println(3+"\r\n"+address+"\r\n"+serverNetJson+"\r\n"+"END");
					out.flush();
					
					Setting setting = new Setting();
					setting.setKey("SERVER_NET") ; 
					setting.setValue(serverNetJson);
					settingManager.updateByKey("SERVER_NET", setting);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		//屏蔽ldap
	/*	if(null != ldapNetJson && !ldapNetJson.isEmpty()){
			
			String data = settingManager.queryByKey("LDAP_NET");
			if(data == null || data.isEmpty()){
				Setting setting = new Setting();
				setting.setKey("LDAP_NET") ; 
				setting.setValue(ldapNetJson);
				settingManager.updateByKey("LDAP_NET", setting);
				return SUCCESS;
			}
			List<String> haIps = settingManager.getLDAPHaIp(data);
			JSONObject object = settingManager.getHaMessage(data);
			String address = object.getString("mainIp")+","+object.getString("stanIp");
			Socket so = null;
			for(String haIp : haIps){
				Client client = new Client(haIp);
				so = client.connect();
				if(null != so){
					try {
						BufferedReader bReader = new BufferedReader(
								new InputStreamReader(so.getInputStream()));
						PrintWriter out = new PrintWriter(so.getOutputStream(),
								true);
						String tempStr = bReader.readLine();
						if ("OK".equals(tempStr.trim())) {
							out.println(3);
							out.println(address);
							out.println(ldapNetJson);
							out.println("END");
							out.flush();
							
								Setting setting = new Setting();
								setting.setValue(ldapNetJson);
								settingManager.updateByKey("LDAP_NET", setting);
							
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}*/
		return SUCCESS;
	}

	public String queryNetSetting() {
		serverNetJson = settingManager.queryByKey("SERVER_NET");
		//ldapNetJson = settingManager.queryByKey("LDAP_NET");
		return SUCCESS;
	}

	public String queryHAStart(){
		List<String> localhost = new ArrayList<String>();
		//添加的是本机
		localhost.add("localhost");
		//获取本机ip和磁盘状态
		serverStart = settingManager.getHAStart(type, localhost);
		//获取数据库中双机网卡配置
		serverNetJson = settingManager.queryByKey("SERVER_NET");
		//将网卡配置转化成JSONArray
		JSONArray jsonArray = JSONArray.fromObject("["+serverNetJson+"]");
		//用来记录启用心跳线的是哪个网卡
		int eth=0;
		//循环来获取启用心跳线的网卡信息
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jObject2 = (JSONObject) jsonArray.get(i);
			//判断是否启用心跳线
			if (jObject2.get("mainLink").toString().equals("true")&&jObject2.get("stanLink").toString().equals("true")) {
				//取到网卡的名称
				String netName = (String) jObject2.get("netName");
				//获取是第几块网卡
				 eth= Integer.valueOf(netName.replace("eth", ""))+1;
			}
		}
		//将本机ip和磁盘状态转化为JSONObject，由于取到的本机ip是127.0.0.1，此处修改成真正的ip
		JSONObject jObject = JSONObject.fromObject(serverStart);
		//本机名称和IP
		 String host = (String) jObject.get("host");
		 //得到本机的ip是127.0.0.1
		String ip = host.split("/")[1];
		//获取本机所有网卡ip
		List<String> ipList = getLocalIP();
		//把JSONObject里的本机ip和名称去掉
		 jObject.remove("host");
		//把启用心跳线的真实ip替换调127.0.0.1
		 host = host.split("/")[0]+"/"+ipList.get(ipList.size()-eth);
		 jObject.accumulate("host", host);
		 serverStart = jObject.toString();
		System.out.println("serverStart value"+serverStart+ "  " + serverStart);
		if(null == serverStart || serverStart.isEmpty()){
			
			JSONObject obj = new JSONObject();
			obj.accumulate("host", "");
			obj.accumulate("disk_syn", "");
			serverStart = obj.toString();
		}
		/*String data = settingManager.queryByKey("LDAP_NET");
	//	data = data==null?new String():data ; 
		System.out.println("data is ---"+data);
		List<String> haIps = settingManager.getLDAPHaIp(data);
		ldapStart = settingManager.getHAStart(type, haIps);
		System.out.println("ldapStart is ---"+ldapStart);
		if(null == ldapStart || ldapStart.isEmpty()){
			JSONObject obj = new JSONObject();
			obj.accumulate("ldap_host", "");
			obj.accumulate("ldap_disk_syn", "");
			ldapStart = obj.toString();
		}*/
		return SUCCESS;
	}
	
	public void queryHAProfessional(){
		List<String> haIps = null;
		switch (iden) {
		case 0:
			haIps = new ArrayList<String>();
			haIps.add("localhost");
			break;
		case 1:
			String data = settingManager.queryByKey("LDAP_NET");
			haIps = settingManager.getLDAPHaIp(data);
			break;
		}
		startStr =  settingManager.getHAStart(type, haIps);
		 // Ajax返回
		
        try {
            getResponse().getWriter().write(startStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    
    
    public SettingService getSettingManager()
    {
        return settingManager;
    }
    
    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }
    
    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }
    
    public Setting getSetting()
    {
        return setting;
        
    }
    
    public void setSetting(Setting setting)
    {
        this.setting = setting;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getB1Ip()
    {
        return b1Ip;
    }
    
    public void setB1Ip(String ip)
    {
        b1Ip = ip;
    }
    
    public String getB1Code()
    {
        return b1Code;
    }
    
    public void setB1Code(String code)
    {
        b1Code = code;
    }
    
    public String getB2Ip()
    {
        return b2Ip;
    }
    
    public void setB2Ip(String ip)
    {
        b2Ip = ip;
    }
    
    public String getB2Code()
    {
        return b2Code;
    }
    
    public void setB2Code(String code)
    {
        b2Code = code;
    }
    
    public String getB3Ip()
    {
        return b3Ip;
    }
    
    public void setB3Ip(String ip)
    {
        b3Ip = ip;
    }
    
    public String getB3Code()
    {
        return b3Code;
    }
    
    public void setB3Code(String code)
    {
        b3Code = code;
    }
    
    public String getB4Ip()
    {
        return b4Ip;
    }
    
    public void setB4Ip(String ip)
    {
        b4Ip = ip;
    }
    
    public String getB4Code()
    {
        return b4Code;
    }
    
    public void setB4Code(String code)
    {
        b4Code = code;
    }
    
    public String getB5Ip()
    {
        return b5Ip;
    }
    
    public void setB5Ip(String ip)
    {
        b5Ip = ip;
    }
    
    public String getB5Code()
    {
        return b5Code;
    }
    
    public void setB5Code(String code)
    {
        b5Code = code;
    }
    
    public String getB6Ip()
    {
        return b6Ip;
    }
    
    public void setB6Ip(String ip)
    {
        b6Ip = ip;
    }
    
    public String getB6Code()
    {
        return b6Code;
    }
    
    public void setB6Code(String code)
    {
        b6Code = code;
    }
    
    public String getB6Gate()
    {
        return b6Gate;
    }
    
    public void setB6Gate(String gate)
    {
        b6Gate = gate;
    }
    
    public String getB7Ip()
    {
        return b7Ip;
    }
    
    public void setB7Ip(String ip)
    {
        b7Ip = ip;
    }
    
    public String getB7Code()
    {
        return b7Code;
    }
    
    public void setB7Code(String code)
    {
        b7Code = code;
    }
    
    public String getB8Ip()
    {
        return b8Ip;
    }
    
    public void setB8Ip(String ip)
    {
        b8Ip = ip;
    }
    
    public String getB8Code()
    {
        return b8Code;
    }
    
    public void setB8Code(String code)
    {
        b8Code = code;
    }
    
    public String getB9Ip()
    {
        return b9Ip;
    }
    
    public void setB9Ip(String ip)
    {
        b9Ip = ip;
    }
    
    public String getB9Code()
    {
        return b9Code;
    }
    
    public void setB9Code(String code)
    {
        b9Code = code;
    }
    
    public String getB10Ip()
    {
        return b10Ip;
    }
    
    public void setB10Ip(String ip)
    {
        b10Ip = ip;
    }
    
    public String getB10Code()
    {
        return b10Code;
    }
    
    public void setB10Code(String code)
    {
        b10Code = code;
    }
    
    public String getConsoleIp()
    {
        return consoleIp;
    }
    
    public void setConsoleIp(String consoleIp)
    {
        this.consoleIp = consoleIp;
    }
    
    public List<Setting> getSettingList()
    {
        return settingList;
    }
    
    public void setSettingList(List<Setting> settingList)
    {
        this.settingList = settingList;
    }
    
    public Integer getCardSize()
    {
        return cardSize;
    }
    
    public void setCardSize(Integer cardSize)
    {
        this.cardSize = cardSize;
    }
    
    public String getRouteInfo()
    {
        return routeInfo;
    }
    
    public void setRouteInfo(String routeInfo)
    {
        this.routeInfo = routeInfo;
    }
    
    public String getInterfacesName()
    {
        return interfacesName;
    }
    
    public void setInterfacesName(String interfacesName)
    {
        this.interfacesName = interfacesName;
    }
    
    public String getWarning()
    {
        return warning;
    }
    
    public void setWarning(String warning)
    {
        this.warning = warning;
    }
    
    public List<Map> getRouteList()
    {
        return routeList;
    }
    
    public void setRouteList(List<Map> routeList)
    {
        this.routeList = routeList;
    }
    
    public List<Map> getStaticRouteList()
    {
        return staticRouteList;
    }
    
    public void setStaticRouteList(List<Map> staticRouteList)
    {
        this.staticRouteList = staticRouteList;
    }
    
    public List<Setting> getInterfacesNameList()
    {
        return interfacesNameList;
    }
    
    public void setInterfacesNameList(List<Setting> interfacesNameList)
    {
        this.interfacesNameList = interfacesNameList;
    }
    
    public String getPingIP()
    {
        return pingIP;
    }
    
    public void setPingIP(String pingIP)
    {
        this.pingIP = pingIP;
    }
    
    public String getIds()
    {
        return ids;
    }
    
    public void setIds(String ids)
    {
        this.ids = ids;
    }

   

    public String getB1Gate()
    {
        return b1Gate;
    }

    public void setB1Gate(String b1Gate)
    {
        this.b1Gate = b1Gate;
    }

    public String getB2Gate()
    {
        return b2Gate;
    }

    public void setB2Gate(String b2Gate)
    {
        this.b2Gate = b2Gate;
    }

    public String getB3Gate()
    {
        return b3Gate;
    }

    public void setB3Gate(String b3Gate)
    {
        this.b3Gate = b3Gate;
    }

    public String getB4Gate()
    {
        return b4Gate;
    }

    public void setB4Gate(String b4Gate)
    {
        this.b4Gate = b4Gate;
    }

    public String getB5Gate()
    {
        return b5Gate;
    }

    public void setB5Gate(String b5Gate)
    {
        this.b5Gate = b5Gate;
    }

    public String getB7Gate()
    {
        return b7Gate;
    }

    public void setB7Gate(String b7Gate)
    {
        this.b7Gate = b7Gate;
    }

    public String getB8Gate()
    {
        return b8Gate;
    }

    public void setB8Gate(String b8Gate)
    {
        this.b8Gate = b8Gate;
    }

    public String getB9Gate()
    {
        return b9Gate;
    }

    public void setB9Gate(String b9Gate)
    {
        this.b9Gate = b9Gate;
    }

    public String getB10Gate()
    {
        return b10Gate;
    }

    public void setB10Gate(String b10Gate)
    {
        this.b10Gate = b10Gate;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getServerNetJson() {
		return serverNetJson;
	}

	public void setServerNetJson(String serverNetJson) {
		this.serverNetJson = serverNetJson;
	}

	public String getLdapNetJson() {
		return ldapNetJson;
	}

	public void setLdapNetJson(String ldapNetJson) {
		this.ldapNetJson = ldapNetJson;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

	public String getStartStr() {
		return startStr;
	}

	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}

	public String getServerStart() {
		return serverStart;
	}

	public void setServerStart(String serverStart) {
		this.serverStart = serverStart;
	}

	public String getLdapStart() {
		return ldapStart;
	}

	public void setLdapStart(String ldapStart) {
		this.ldapStart = ldapStart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static void main(String[] args) {
//		 String message ="{\"disk_syn\":\"UpToDate/UpToDate\",\"host\":\"soc/127.0.1.1\"}";
//		 JSONObject jObject = JSONObject.fromObject(message);
//		 String host = (String) jObject.get("host");
//		String ip = host.split("/")[1];
//		 System.out.println(ip);
//		 host = host.split("/")[0]+"/"+"192.168.170.130";
//		 jObject.remove("host");
//		 jObject.accumulate("host", host);
//		 System.out.println(jObject.get("host")+"\t"+jObject.get("disk_syn"));
		System.out.println(Integer.valueOf("eth0".replace("eth", ""))+1);
		
	}
	 public static boolean isWindowsOS(){
		  boolean isWindowsOS = false;
		  String osName = System.getProperty("os.name");
		  if(osName.toLowerCase().indexOf("windows")>-1){
		   isWindowsOS = true;
		  }
		  return isWindowsOS;
		 }
	 public static List<String> getLocalIP(){
		 List<String> ipList = new ArrayList<String>();
		  InetAddress ip = null; 
		  try {
		   //如果是Windows操作系统
		   if(isWindowsOS()){
		    ip = InetAddress.getLocalHost();
		    ipList.add(ip.getHostAddress());
		   }else{
		    boolean bFindIP = false;
		    Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
		      .getNetworkInterfaces();
		    while (netInterfaces.hasMoreElements()) {
		    
		     NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
		     //----------特定情况，可以考虑用ni.getName判断
		     //遍历所有ip
		     Enumeration<InetAddress> ips = ni.getInetAddresses();
		     while (ips.hasMoreElements()) {
		      ip = (InetAddress) ips.nextElement();
		      if( ip!=null&&ip instanceof Inet4Address&&!ip.isLoopbackAddress()){
		    	  ipList.add(ip.getHostAddress());
		           
		         }
		     }

		    }
		   }
		  }
		  catch (Exception e) {
		   e.printStackTrace();
		  } 

		  return ipList;
		 }
	
}