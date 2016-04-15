package com.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class SystemInfoHandle {

	//获得系统运行时间
	public static String getSysRuntime(){
		String str=null;
		String runTime="";
		BufferedReader br = null;
		try {
			
			////System.out.println("get systemTime........");
			Runtime.getRuntime().exec("uptime");
			File file1 = new File("/proc/uptime");
			BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
			if((str=br1.readLine())!=null){
				runTime = str.substring(0,str.indexOf(" ")).trim();
				long runTimes = (long)(Float.parseFloat(runTime)*1000);
				/*java.util.TimeZone tz = java.util.TimeZone.getTimeZone("ETC/GMT-8");
				java.util.TimeZone.setDefault(tz);
				SimpleDateFormat format = new  SimpleDateFormat("HH小时mm分");
				runTime = format.format(new Date(runTimes));
				if(runTime.charAt(0)=='0'){
					runTime=runTime.substring(1);
				}*/
				
				float f = Float.parseFloat(str.substring(0,str.indexOf(" ")).trim());
				//System.out.println(f);
				int day = (int) (f/86400);
				int hour = (int) ((f%86400)/3600);
				int minute =(int) ((f%3600)/60);
				int se =(int) (f%60);
				runTime = day+"天"+hour+"小时"+minute+"分"+se+"秒";
			}
			java.util.TimeZone tz = java.util.TimeZone.getTimeZone("Etc/GMT-8");
			java.util.TimeZone.setDefault(tz);
			
			////System.out.println("get systemTime........end");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return runTime;
	}
	
	/**
	   * get memory by used info
	   * 
	   * @return int[] result
	   * result.length==4;int[0]=MemTotal;int[1]=MemFree;int[2]=SwapTotal;int[3]=SwapFree;
	   * @throws IOException
	   * @throws InterruptedException
	   */
	   public static int[] getMemInfo() throws IOException, InterruptedException
	   {
		  ////System.out.println("get meminfo ..........");
	      File file = new File("/proc/meminfo");
	      BufferedReader br = new BufferedReader(new InputStreamReader(
	      new FileInputStream(file)));
	      int[] result = new int[6];
	      String str = null;
	      StringTokenizer token = null;
	      while((str = br.readLine()) != null)
	      {
	         token = new StringTokenizer(str);
	         if(!token.hasMoreTokens())
	            continue;
	   
	         str = token.nextToken();
	         if(!token.hasMoreTokens())
	            continue;
	         if(str.equalsIgnoreCase("MemTotal:"))
		            result[0] = Integer.parseInt(token.nextToken());
		         else if(str.equalsIgnoreCase("MemFree:"))
		            result[1] = Integer.parseInt(token.nextToken());
		         else if(str.equalsIgnoreCase("SwapTotal:"))
		            result[2] = Integer.parseInt(token.nextToken());
		         else if(str.equalsIgnoreCase("SwapFree:"))
		            result[3] = Integer.parseInt(token.nextToken());
		         else if(str.equalsIgnoreCase("Buffers:"))
		            result[4] = Integer.parseInt(token.nextToken());
		         else if(str.equalsIgnoreCase("Cached:"))
		            result[5] = Integer.parseInt(token.nextToken());
		      
	      }
	      
	      br.close();
	      
	     // //System.out.println("get meminfo .......... end");
	      
	      return result;
	   }

	   /**
	   * get memory by used info
	   * 
	   * @return float efficiency
	   * @throws IOException
	   * @throws InterruptedException
	   */
	public static float getCpuInfo() throws IOException, InterruptedException
	   {
		
		 // //System.out.println("get cupInfo.....");
	      File file = new File("/proc/stat");
	      BufferedReader br = new BufferedReader(new InputStreamReader(
	      new FileInputStream(file)));
	      StringTokenizer token = new StringTokenizer(br.readLine());
	      token.nextToken();
	      long user1 = Long.parseLong(token.nextToken());
	      long nice1 = Long.parseLong(token.nextToken());
	      long sys1 = Long.parseLong(token.nextToken());
	      long idle1 = Long.parseLong(token.nextToken());
	      br.close();
	      
	      Thread.sleep(1000);

	      br = new BufferedReader(
	      new InputStreamReader(new FileInputStream(file)));
	      token = new StringTokenizer(br.readLine());
	      token.nextToken();
	      long user2 = Long.parseLong(token.nextToken());
	      long nice2 = Long.parseLong(token.nextToken());
	      long sys2 = Long.parseLong(token.nextToken());
	      long idle2 = Long.parseLong(token.nextToken());
	      
	      if(br != null)
	    	  br.close();
	      
	     // //System.out.println("get cupInfo ..... end!");
	      
	      return (float)((user2 + sys2 + nice2) - (user1 + sys1 + nice1)) / (float)((user2 + nice2 + sys2 + idle2) - (user1 + nice1 + sys1 + idle1));
	     
	   }
	
	
	/**
	 * 或得系统的磁盘信息。
	 * 
	 * @return
	 */
	public static List getDiskInfo() {
		
		////System.out.println("get diskinfo .........");
		
		List<String[]> diskList = new ArrayList<String[]>();
		String[] result = null;
		Process p;
		BufferedReader br = null;
		try {
			p = Runtime.getRuntime().exec("df -h");
			br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			br.readLine();
			String str = null;
			StringTokenizer token = null;
			while ((str = br.readLine()) != null) {
				result = new String[6];
				token = new StringTokenizer(str);
				//System.out.println("get diskinfo size="+token.countTokens());
				//System.out.println("memsize"+str);
				
				if (token.countTokens()>1) {
					try{
						
					result[0] = token.nextToken();
					result[1] = token.nextToken();
					result[2] = token.nextToken();
					result[3] = token.nextToken();
					result[4] = token.nextToken();
					result[5] = token.nextToken();
					
					diskList.add(result);
					}
					catch(Exception e)
					{
						//System.out.println("read diskinfo seat failed!");
					}
				}
				else
				{
					try{
					
					result[0] = str.trim();
					
					//System.out.println("memtoal"+str);
					
					str = br.readLine();
					
					token = new StringTokenizer(str);
					
					result[1] = token.nextToken();
					result[2] = token.nextToken();
					result[3] = token.nextToken();
					result[4] = token.nextToken();
					result[5] = token.nextToken();
					
					diskList.add(result);
					
					}catch(Exception e)
					{
						//System.out.println("transfer failed......!");
					}
				}
				
				result = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		////System.out.println("get diskinfo .........end");
		return diskList;
	}
	
	
	/**
	 * 获取网络流量信息 尹海平 return List<String> [0]+3网络名称[1]+3是接收流量[2]+3是发送流量 返回单位 MB
	 * 
	 * @throws IOException
	 */
	/*public static List<String> NetworkTraffic(){
		File file = new File("/proc/net/dev");
		List<String> netWorkList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			List<String[]> devListData = new ArrayList<String[]>();
			List<String> devList = new ArrayList<String>();
			String[] result = null;
			String str = null;
			int line = 1;
			StringTokenizer token = null;
			try {
				while ((str = br.readLine()) != null) {
					if (line > 2) {
						result = new String[17];
						token = new StringTokenizer(str);
						if (token.hasMoreTokens()) {
							result[0] = token.nextToken();
							result[1] = token.nextToken();
							result[2] = token.nextToken();
							result[3] = token.nextToken();
							result[4] = token.nextToken();
							result[5] = token.nextToken();
							result[6] = token.nextToken();
							result[7] = token.nextToken();
							result[8] = token.nextToken();
							result[9] = token.nextToken();
							result[10] = token.nextToken();
							result[11] = token.nextToken();
							result[12] = token.nextToken();
							result[13] = token.nextToken();
							result[14] = token.nextToken();
							result[15] = token.nextToken();
							result[16] = token.nextToken();
						}
						if(result[0].toString().contains("eth")){
							devListData.add(result);
						}
						result = null;
					}++line;
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				if(br != null)
				{
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			int nameNu =0;
			int receiveNu=1;
			int transmitNu=2;
			for (String[] data : devListData) {
				String face = data[0];
				float receive = Long.valueOf((String) data[1]);
				float transmit = Long.valueOf((String) data[9]);
				devList.add(face);
				devList.add(String.valueOf(((receive / 8) / 1024) / 1024));
				devList.add(String.valueOf(((transmit / 8) / 1024) / 1024));
				netWorkList.add(devList.get(nameNu));
				netWorkList.add(devList.get(receiveNu));
				netWorkList.add(devList.get(transmitNu));
				nameNu=nameNu+3;receiveNu=receiveNu+3;transmitNu=transmitNu+3;
				}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
				return netWorkList;
	}*/
}