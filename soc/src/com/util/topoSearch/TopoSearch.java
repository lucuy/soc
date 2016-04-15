package com.util.topoSearch;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class TopoSearch {

	
	public static List<String> nmapIpList = new ArrayList<String>();
	
	public static List<String> switchList = new ArrayList<String>();
	
	 public static List<String>  getNmapIP(String fileName)
	 {
		 List<String> tempNmapIp = new ArrayList<String>();
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	     try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			File temp = new File(fileName);
			int count =0;
			while(count<4)
			{
				if(temp.exists())
				{
					break;
				}
				else
				{
					Thread.sleep(5000);
					count ++;
				}
			}
		    Document doc = db.parse(temp);
		    // ��ȡ��Ԫ�ؽڵ�
	        Element root = doc.getDocumentElement();
	        tempNmapIp= parseElement(root);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return tempNmapIp;
	 }
	 
	 public static List<String> parseElement(Element element)
	 {   
		 List<String> tempResult = new ArrayList<String>();
		 String tagName = element.getNodeName();

	       // System.out.print("<" + tagName);

	        // elementԪ�ص��������Թ��ɵ�NamedNodeMap������Ҫ��������ж�
	        NamedNodeMap map = element.getAttributes();

	        // ���������ԣ����ӡ����
	        if (null != map && map.getLength()<3)
	        {   
	            for (int i = 0; i < map.getLength(); i++)
	            {
	                // ��ø�Ԫ�ص�ÿһ������
	                Attr attr = (Attr) map.item(i);

	                // �����������ֵ
	                String attrName = attr.getName();
	                String attrValue = attr.getValue();
	               if(Pattern.matches("\\d+\\.\\d+\\.\\d+\\.\\d+", attrValue))
	               {  
	                // ע������ֵ��Ҫ������ţ�������Ҫ\ת��
	            	   nmapIpList.add(attrValue);
	            	   tempResult.add(attrValue);
	               }
	            }
	        }

	        // �رձ�ǩ��
	       // System.out.print(">");

	        // �����Ѿ���ӡ����Ԫ�����������
	        // ���濪ʼ���������Ԫ��
	        NodeList children = element.getChildNodes();

	        for (int i = 0; i < children.getLength(); i++)
	        {

	            // ��ȡÿһ��child
	            Node node = children.item(i);
	            // ��ȡ�ڵ�����
	            short nodeType = node.getNodeType();

	            if (nodeType == Node.ELEMENT_NODE)
	            {
	                // �����Ԫ�����ͣ���ݹ����
	            	if(node.getNodeName().equals("host")||node.getNodeName().equals("address"))
	            	{
	                    parseElement((Element) node);
	            	}
	            }
	            
	            else if (nodeType == Node.TEXT_NODE)
	            {
	                // ������ı����ͣ�������ڵ�ֵ�����ı�����
	                //System.out.print(node.getNodeValue());
	            }
	            else if (nodeType == Node.COMMENT_NODE)
	            {
	                // �����ע�ͣ������ע��
	               // System.out.print("<!--");

	                Comment comment = (Comment) node;

	                // ע������
	                String data = comment.getData();

	               // System.out.print(data);

	              //  System.out.print("-->");
	            }
	        }
	        
	        return tempResult;

	 }
	 
	 /**
	 * �õ������ip��ַ
	 * @return
	 */
	public static List<String> getHostIp()
	{
		List<String> ipList = new ArrayList<String>();
		Enumeration allNetInterfaces = null;  
	    try {  
	        allNetInterfaces = NetworkInterface.getNetworkInterfaces();  
	    } catch (SocketException e) {  
	  
	        e.printStackTrace();  
	    }  
	    InetAddress ip = null;  
	    while (allNetInterfaces.hasMoreElements()) {  
	        NetworkInterface netInterface = (NetworkInterface) allNetInterfaces  
	                .nextElement();  
	        //System.out.println(netInterface.getName());  
	        Enumeration addresses = netInterface.getInetAddresses();  
	        while (addresses.hasMoreElements()) {  
	            ip = (InetAddress) addresses.nextElement();  
	            if (ip != null && ip instanceof Inet4Address  
	                    && ip.getHostAddress().indexOf(".") != -1) { 
	            	if(!ip.getHostAddress().equals("127.0.0.1"))
	            	{
	                  System.out.println("本机IP = " + ip.getHostAddress());  
	                  ipList.add(ip.getHostAddress());
	            	}
	                //ipFinal= ip.getHostAddress();
	                //logger.debug("------------------�����IP : "+ip.getHostAddress()+"---------------------");  
	            }  
	        }  
	    }  
	    return  ipList;
	}
	
	/*
	 * ִ��nmap������ļ�����/home��
	 * 
	 */
	public static void nampSearch(String cmd)
	{
		try {
			Process ps = Runtime.getRuntime().exec(cmd);
			
			//等待命令执行完毕
			ps.waitFor();
			
			//睡5秒
			//Thread.sleep(50000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("执行失败");
			e.printStackTrace();
		}
	}
	
	
	public static void pingHost(String IP)
	{   
	     	IP=IP.substring(0, IP.lastIndexOf("."));
	     	String tempIP="";
	    	/*for(int i = 1 ;i<255;i++ )
	    	{
	    		tempIP =IP+"."+i;
	    		String result = Ping.pingIP(tempIP);
	    		System.out.println(result);
	    		if(result!=null)
	    		{
	    			nmapIpList.add(tempIP);
	    		}
	    	}*/
	     	tempIP=IP+".1";
	     	String result = Ping.pingIP(tempIP);
	     	System.out.println(result);
	     	if(result!=null)
    		{
    			nmapIpList.add(tempIP);
    		}
	     	tempIP=IP+".254";
	     	result = Ping.pingIP(tempIP);
	     	System.out.println(result);
	     	if(result!=null)
    		{
    			nmapIpList.add(tempIP);
    		}
	}
	/**osts
	 * 判断某个ip是否为路由设备
	 * @param IP
	 * @return
	 */
	public static boolean testIfSwitch(String IP)
	{
		
		//System.out.println(IP);
		String snmpTestDeviceType="snmpwalk -v2c -c public123  "+IP+"  1.3.6.1.2.1.4.1";
		boolean temp = false;
		
		try {
			
			
			Process ps = Runtime.getRuntime().exec(snmpTestDeviceType);
		
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
			sb.append(line).append("\n");
			}
			
			System.out.println(IP);
			
			String result = sb.toString();
			
			if(result.contains("INTEGER: 1"))
			{   
				temp = true;
				System.out.println(IP+"是交换机");
				switchList.add(IP);
			}
			
			//System.out.println(result);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;
	}
	public static List<String> getArpList(String ip)
	{
		List<String> routeList = new ArrayList<String>();
		
		routeList.add(ip);
		
		String snmpArpList="snmpwalk -v2c -c public123  "+ip+"  1.3.6.1.2.1.4.22.1.2";
		
     try {
			Process ps = Runtime.getRuntime().exec(snmpArpList);
		
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
			line = line.split("=")[0];
			if(line.contains("iso"))
			{
			   /* line = line.replace("iso.3.6.1.2.1.4.22.1.2.", "");
			    
			    if(line.indexOf(".")==4)
			    {
			    	line = line.replace(line.split(".")[0]+".", "");
			    }*/
			    
			   // int length = line.split(".").length;
			    
				System.out.println(line);
			    String lineresult="";
			    
			    System.out.println( line.split("\\.").length);
			    
			    String   tempArp[] = line.split("\\.");
			    
			    int length=tempArp.length;
			    lineresult=tempArp[length-4]+"."+tempArp[length-3]+"."+tempArp[length-2]+"."+tempArp[length-1];
			    routeList.add(lineresult);
		    	sb.append(lineresult).append("\n");
			}
			
			}
			
			String result = sb.toString();
			
			System.out.println("得到的arp表为"+result);
			
			String routeTableCmd="snmpwalk -v2c -c public123  "+ip+"  1.3.6.1.2.1.4.20.1.3";
			
			Process ps1 = Runtime.getRuntime().exec(routeTableCmd);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(ps1.getInputStream()));
			StringBuffer sb1 = new StringBuffer();
			String line1;
			while ((line1 = br1.readLine()) != null) {
			line1 = line1.split("=")[0];
			if(line1.contains("iso"))
			{
			line1 = line1.replace("iso.3.6.1.2.1.4.20.1.3.", "");
			//System.out.println(line1);
			sb1.append(line1).append("\n");
			routeList.remove(line1);
			}
		  	
			
			}
			System.out.println("得到的路由表为:"+sb1.toString());
			
			for(int p=0;p<routeList.size();p++)
			{
				System.out.println("zichan"+routeList.get(p));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return routeList;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> finalIP = new ArrayList<String>();
		
		
		finalIP=getHostIp();
		
		for(String temIp:finalIP)
		{
			//System.out.println(temIp.lastIndexOf("."));
		/*	temIp=temIp.substring(0, temIp.lastIndexOf("."));*/
		/*	temIp +=".1";
			System.out.println(temIp);
			
			String nmapCmd=" nmap -T4 -sP -oX - "+temIp+"/24 > /home/"+temIp+".xml"; 
		 
			System.out.println(nmapCmd);
			
			nampSearch(nmapCmd);*/
			
			
			
	/*		String fileName="/home/"+temIp+".xml";
			
			getNmapIP(fileName);
			*/
			pingHost(temIp);
		}
		
		//循环判断得出交换机
		for(String tempSearchIp:nmapIpList)
		{
			//得到探测到的段内有多少台机器不是主机，是交换机路由器
			testIfSwitch(tempSearchIp);
		}
		
		
		if(switchList.size()>0)
		{
			for(int i=0;i<switchList.size();i++)
			{
				getArpList(switchList.get(i));
			}
		}
		
		//finalIP = getHostIp();
       //System.out.println(getHostIp());
	}

}
