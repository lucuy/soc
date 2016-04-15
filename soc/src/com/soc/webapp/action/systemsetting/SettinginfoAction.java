package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.CpuData;
import com.soc.model.systemsetting.MemoryData;
import com.soc.model.systemsetting.SysInfo;
import com.soc.model.user.User;
import com.soc.service.systemsetting.SettingCpuService;
import com.soc.service.systemsetting.SystemMemoryService;
import com.soc.service.systemsetting.impl.SystemInfoService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.OSUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author yinhaiping
 * @version [版本号, 2012-11-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SettinginfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private SysInfo sysInfo;

	private String sysNowTime;

	private String sysDate;

	private String hour;

	private String minute;

	private String second;

	private String sysTime;
	//饼图json数据
		private StringBuffer pieRomData;
		
		 //总容量json
	    private StringBuffer sumRom ;
	    
	    //以使用空间
	    private StringBuffer useRom ;
	    
	    //未使用的空间
	    private StringBuffer unUseRom ;
	    private String now;
	    private StringBuffer diskXJson ;
	private SettingCpuService settingCpuManager;

	private List<CpuData> cpuList;

	private ArrayList<SettingSetCpuData> cpu;

	private ArrayList<SettingSetCpuData> memory;

	private SystemMemoryService settingMemoryManager;

	private List<MemoryData> memoryList;

	private List<String[]> diskList;

	// private AuditService auditManager;

	private JSONArray jsonArrayMemory; // 定义内存使用JSONArray

	private JSONArray jsonArrayCpu; // 定义内存使用JSONArray

	private JSONArray jsonArrayDisk; // 定义内存使用JSONArray

	private JSONArray jsonArrayDiskData; // 定义内存使用JSONArray

	private JSONArray jsonArrayDiskData8; // 定义内存使用JSONArray

	private JSONArray jsonArrayDiskData9; // 定义内存使用JSONArray

	private JSONArray jsonArrayMemoryUp; // 定义内存使用JSONArray

	private JSONArray jsonArrayCpuUp; // 定义内存使用JSONArray

	private JSONArray jsonArrayDiskUp; // 定义内存使用JSONArray

	private JSONArray jsonArrayNetworkDataReceive; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit;

	private JSONArray jsonArrayNetworkDataReceive2; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit2;

	private JSONArray jsonArrayNetworkDataReceive3; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit3;

	private JSONArray jsonArrayNetworkDataReceive4; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit4;

	private JSONArray jsonArrayNetworkDataReceive5; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit5;

	private JSONArray jsonArrayNetworkDataReceive6; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit6;

	private JSONArray jsonArrayNetworkDataReceive7; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit7;

	private JSONArray jsonArrayNetworkDataReceive8; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit8;

	private JSONArray jsonArrayNetworkDataReceive9; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit9;

	private JSONArray jsonArrayNetworkDataReceive10; // 定义内存使用JSONArray
	private JSONArray jsonArrayNetworkDataTransmit10;

	private Integer networkSize;// 网卡个数

	public String query() {
		Page page = null;
		SearchResult sr = null;
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = super.getRequest();
		// cpuList = (List<CpuData>)settingCpuManager.query(map, page);

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 根据map中存储的查询条件，查找符合条件的用户列表
		sr = settingCpuManager.query(map, page);

		// 对查找的结果为分页赋值
		if (sr != null) {
			
			cpuList = (List<CpuData>) sr.getList();

			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}

		return SUCCESS;
	}

	// memory
	public String memoryQuery() {
		Page page = null;
		SearchResult sr = null;
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = super.getRequest();

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 根据map中存储的查询条件，查找符合条件的用户列表
		sr = settingMemoryManager.memoryQuery(map, page);

		// 对查找的结果为分页赋值
		if (sr != null) {
			
			memoryList = (List<MemoryData>) sr.getList();

			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		// memoryList = settingMemoryManager.memoryQuery();
		return SUCCESS;
	}

	/*
	 * public void teat(){ Cache cache = null; cache =
	 * GlobalConfig.cacheManager.getCache("events_count");
	 * 
	 * List<Long> list = new ArrayList<Long>();
	 * 
	 * long temp= System.currentTimeMillis()/1000;
	 * 
	 * List<Integer> eventcounts = new ArrayList<Integer>();
	 * 
	 * list.add(temp);
	 * 
	 * for(int i=1;i<10;i++) {
	 * 
	 * list.add(temp-i);
	 * 
	 * }
	 * 
	 * Map<Object, Element> map = cache.getAll(list);
	 * 
	 * Collection<Element> l = map.values();
	 * 
	 * Iterator it = l.iterator();
	 * 
	 * while (it.hasNext()) {
	 * 
	 * Element temp2 = (Element) it.next();
	 * 
	 * int temp1 =(Integer)temp2.getObjectValue();
	 * 
	 * eventcounts.add(temp1);
	 * 
	 * }
	 * 
	 * Compare compare = new Compare();
	 * 
	 * // 对cache内的数据进行按照时间先后排序 Collections.sort(eventcounts, compare);
	 * 
	 * 
	 * // List list = cache.getKeys(); }
	 */

	/**
	 * <查询所有内存信息> <功能详细描述>
	 * 
	 * @return
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	public String queryMemory() throws IOException {
		
        StringBuffer memoryBuffer = new StringBuffer(); //定义内存使用StringBuffer
        StringBuffer cpuBuffer = new StringBuffer(); //定义内存使用StringBuffer
        StringBuffer diskBuffer = new StringBuffer(); //定义内存使用StringBuffer
        StringBuffer diskDataBuffer = new StringBuffer(); //定义内存使用StringBuffer
        StringBuffer diskDataBuffer8 = new StringBuffer(); //定义内存使用StringBuffer
        StringBuffer diskDataBuffer9 = new StringBuffer(); //定义内存使用StringBuffer
        
        memoryBuffer.append("[");
        cpuBuffer.append("[");
        diskBuffer.append("[");
        
        diskDataBuffer.append("[");
        diskDataBuffer8.append("[");
        diskDataBuffer9.append("[");
        if (OSUtil.getOSName().toLowerCase().contains("linux"))
        {
            String memoryVal;
            String cpuVal;
            String diskpt;
            
            int diskcount = 0;
            int diskcountdata = 0;
            for (SysInfo info : GlobalConfig.sysInfoList)
            {
                memoryVal = info.getMemoryRatio().replaceAll("%", "");
                memoryBuffer.append((memoryBuffer.length() == 1 ? "" : ",") + "['" + info.getSysNowTime() + "',"
                    + memoryVal + "]");
                
                cpuVal = info.getCpuRatio().replaceAll("%", "");
                cpuBuffer.append((cpuBuffer.length() == 1 ? "" : ",") + "['" + info.getSysNowTime() + "',"
                    + cpuVal + "]");
            }
            
            //获取磁盘信息
            try
            {
                sysInfo = SystemInfoService.getSysInfo();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            //获取完毕
            //        	String memory = sysInfo.getMemoryRatio().replaceAll("%", "");
            
            /*memoryBuffer.append("['" + time + "'," + memory + "],['" + time + "'," + memory + "],['" + time + "',"
                + memory + "],['" + time + "'," + memory + "],['" + time + "'," + memory + "],['" + time + "',"
                + memory + "],['" + time + "'," + memory + "],['" + time + "'," + memory + "],['" + time + "',"
                + memory + "],['" + time + "'," + memory + "]");
            
            //            String cpu = sysInfo.getCpuRatio().replaceAll("%", "");
            
            cpuBuffer.append("['" + time + "'," + cpu + "],['" + time + "'," + cpu + "],['" + time + "'," + cpu
                + "],['" + time + "'," + cpu + "],['" + time + "'," + cpu + "],['" + time + "'," + cpu + "],['" + time
                + "'," + cpu + "],['" + time + "'," + cpu + "],['" + time + "'," + cpu + "],['" + time + "'," + cpu
                + "]");*/
            
            diskList = sysInfo.getDiskList();
            
            for (String[] str : diskList)
            {
                diskBuffer.append((diskBuffer.length() == 1 ? "" : ",") + "'" + str[5] + "'");
            }
            
            for (String[] str : diskList)
            {
                String diskdaStr = str[4].substring(0, str[4].indexOf("%"));
                int diskda = Integer.parseInt(diskdaStr);
                if (diskcountdata == 0)
                {
                    if (diskda < 80)
                    {
                        diskDataBuffer.append(diskda);
                        diskDataBuffer8.append(0);
                        diskDataBuffer9.append(0);
                    }
                    else
                    {
                        if (diskda > 80 && diskda < 90)
                        {
                            diskDataBuffer.append(80);
                            diskDataBuffer8.append(Math.abs(diskda - 90));
                            diskDataBuffer9.append(0);
                        }
                        else
                        {
                            if (diskda > 90)
                            {
                                diskDataBuffer.append(80);
                                diskDataBuffer8.append(90);
                                diskDataBuffer9.append(Math.abs(diskda - 100));
                            }
                        }
                    }
                }
                else
                {
                    if (diskda < 80)
                    {
                        diskDataBuffer.append("," + diskda);
                        diskDataBuffer8.append("," + 0);
                        diskDataBuffer9.append("," + 0);
                    }
                    else
                    {
                        if (diskda > 80 && diskda < 90)
                        {
                            diskDataBuffer.append("," + 80);
                            diskDataBuffer8.append("," + Math.abs(diskda - 90));
                            diskDataBuffer9.append("," + 0);
                        }
                        else
                        {
                            if (diskda > 90)
                            {
                                diskDataBuffer.append("," + 80);
                                diskDataBuffer8.append("," + 90);
                                diskDataBuffer9.append("," + Math.abs(diskda - 100));
                            }
                        }
                    }
                }
                diskcountdata++;
                //判断数据
            }
            //结束
        }
        else
        {
            SettingSetCpuData sasa = new SettingSetCpuData();
            memoryBuffer.append("['" + "Please deploy the system to the Linux system!" + "'," + 0 + "]]");
            Object memorytemp = "" + memoryBuffer + "";
            jsonArrayMemory = JSONArray.fromObject(memorytemp);
            
            cpuBuffer.append("['" + "Please deploy the system to the Linux system!" + "'," + 0 + "]]");
            Object cputemp = "" + cpuBuffer + "";
            jsonArrayCpu = JSONArray.fromObject(cputemp);
            
            diskBuffer.append("'Not Linux']");
            Object disktemp = "" + diskBuffer + "";
            jsonArrayDisk = JSONArray.fromObject(disktemp);
            
            diskDataBuffer.append("0]");
            Object disktempdata = "" + diskDataBuffer + "";
            jsonArrayDiskData = JSONArray.fromObject(disktempdata);
            
            diskDataBuffer8.append("0]");
            Object disktempdata8 = "" + diskDataBuffer8 + "";
            jsonArrayDiskData8 = JSONArray.fromObject(disktempdata8);
            
            diskDataBuffer9.append("0]");
            Object disktempdata9 = "" + diskDataBuffer9 + "";
            jsonArrayDiskData9 = JSONArray.fromObject(disktempdata9);
        }
        
        //        memoryBuffer.append("]");
        //        Object memorytemp = "" + memoryBuffer + "";
        //        jsonArrayMemory = JSONArray.fromObject(memorytemp);
        //        
        //        cpuBuffer.append("]");
        //        Object cputemp = "" + cpuBuffer + "";
        //        jsonArrayCpu = JSONArray.fromObject(cputemp);
        
        if (OSUtil.getOSName().toLowerCase().contains("linux"))
        {
            //  获取
            memoryBuffer.append("]");
            Object memorytemp = "" + memoryBuffer + "";
            jsonArrayMemory = JSONArray.fromObject(memorytemp);
            
            cpuBuffer.append("]");
            Object cputemp = "" + cpuBuffer + "";
            jsonArrayCpu = JSONArray.fromObject(cputemp);
            
            diskBuffer.append("]");
            Object disktemp = "" + diskBuffer + "";
            jsonArrayDisk = JSONArray.fromObject(disktemp);
            
            diskDataBuffer.append("]");
            Object disktempdata = "" + diskDataBuffer + "";
            jsonArrayDiskData = JSONArray.fromObject(disktempdata);
            
            diskDataBuffer8.append("]");
            Object disktempdata8 = "" + diskDataBuffer8 + "";
            jsonArrayDiskData8 = JSONArray.fromObject(disktempdata8);
            
            diskDataBuffer9.append("]");
            Object disktempdata9 = "" + diskDataBuffer9 + "";
            jsonArrayDiskData9 = JSONArray.fromObject(disktempdata9);
            //结束
        }
        
        return SUCCESS;
    }
	/**
	 * <获取内存数据>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void getMemorys(){
	    if(!OSUtil.getOSName().toLowerCase().contains("linux")){
            super.addActionMessage("系统状态功能必须部署在liunx系统上");
        }else{
            try {
                sysInfo = SystemInfoService.getSysInfo();
                String usedMemoryRatio=sysInfo.getUsedMemory();
                String unUsedMemoryRatio=sysInfo.getUnuseMemory();
                usedMemoryRatio=usedMemoryRatio.replaceAll("%", "");
                unUsedMemoryRatio=unUsedMemoryRatio.replaceAll("%", "");
                sysInfo.setUsedMemory(usedMemoryRatio);
                sysInfo.setUnuseMemory(unUsedMemoryRatio);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(sysInfo != null){
                try {
                    // 转换为JSON数据结构
                    JSONObject jsonObject = JSONObject.fromObject(sysInfo);
                    
                    PrintWriter pw = getResponse().getWriter();
                    // Ajax返回
                    pw.write(jsonObject.toString());
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	/**
	 * <获得系统磁盘信息>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void getSysDiskInfo(){
	    if(!OSUtil.getOSName().toLowerCase().contains("linux")){
            super.addActionMessage("系统状态功能必须部署在liunx系统上");
        }else{
            try {
                sysInfo = SystemInfoService.getSysInfo();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(sysInfo != null){
                try {
                    // 转换为JSON数据结构
                    JSONArray jsonArray = JSONArray.fromObject(sysInfo.getDiskList());
                    
                    PrintWriter pw = getResponse().getWriter();
                    // Ajax返回
                    pw.write(jsonArray.toString());
                    pw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	/**
	 * <时时更新CPU峰值数据>
	 * <功能详细描述>
	 * @see [类、类#方法、类#成员]
	 */
	public void showCpuInfo(){
	    if(!OSUtil.getOSName().toLowerCase().contains("linux")){
            super.addActionMessage("系统状态功能必须部署在liunx系统上");
        }else{
    	    try {
                sysInfo = SystemInfoService.getSysInfo();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sysInfo.setSysNowTime(new Date().getTime()+"");
            if(StringUtil.equals(sysInfo.getCpuPeak(), "0%")){
                sysInfo.setCpuPeak(sysInfo.getCpuRatio());
            }
            
            if(sysInfo != null){
                try {
                    // 转换为JSON数据结构
                    JSONArray jsonArray = JSONArray.fromObject(sysInfo);
                    
                    // Ajax返回
                    getResponse().getWriter().write(jsonArray.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}

	// 查询网络流量

/*	public String queryNetwork() {
		StringBuffer networkBufferName = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName2 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive2 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit2 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName3 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive3 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit3 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName4 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive4 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit4 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName5 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive5 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit5 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName6 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive6 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit6 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName7 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive7 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit7 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName8 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive8 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit8 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName9 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive9 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit9 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferName10 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferReceive10 = new StringBuffer(); // 定义内存使用StringBuffer
		StringBuffer networkBufferTransmit10 = new StringBuffer(); // 定义内存使用StringBuffer
		// 判断系统
		if (OSUtil.getOSName().toLowerCase().contains("linux")) {
			// 判断是否有记录
			if (GlobalConfig.networkList.size() < 1) {
				SettingSetCpuData ssc = new SettingSetCpuData();
				try {
					ssc.CpuData();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			networkBufferName.append("[");
			networkBufferReceive.append("[");
			networkBufferTransmit.append("[");
			networkBufferName2.append("[");
			networkBufferReceive2.append("[");
			networkBufferTransmit2.append("[");
			networkBufferName3.append("[");
			networkBufferReceive3.append("[");
			networkBufferTransmit3.append("[");
			networkBufferName4.append("[");
			networkBufferReceive4.append("[");
			networkBufferTransmit4.append("[");
			networkBufferName5.append("[");
			networkBufferReceive5.append("[");
			networkBufferTransmit5.append("[");
			networkBufferName6.append("[");
			networkBufferReceive6.append("[");
			networkBufferTransmit6.append("[");
			networkBufferName7.append("[");
			networkBufferReceive7.append("[");
			networkBufferTransmit7.append("[");
			networkBufferName8.append("[");
			networkBufferReceive8.append("[");
			networkBufferTransmit8.append("[");
			networkBufferName9.append("[");
			networkBufferReceive9.append("[");
			networkBufferTransmit9.append("[");
			networkBufferName10.append("[");
			networkBufferReceive10.append("[");
			networkBufferTransmit10.append("[");

			// 获取网络流量
			DateFormat df = new SimpleDateFormat("mm:ss");
			int networkNumber = SystemInfoHandle.NetworkTraffic().size() / 3;
			int receiveNumber = 1;
			int transmitnumber = 2;
			int receiveNumber2 = 4;
			int transmitnumber2 = 5;
			int receiveNumber3 = 7;
			int transmitnumber3 = 8;
			int receiveNumber4 = 10;
			int transmitnumber4 = 11;
			int receiveNumber5 = 13;
			int transmitnumber5 = 14;
			int receiveNumber6 = 16;
			int transmitnumber6 = 17;
			int receiveNumber7 = 19;
			int transmitnumber7 = 20;
			int receiveNumber8 = 22;
			int transmitnumber8 = 23;
			int receiveNumber9 = 25;
			int transmitnumber9 = 26;
			int receiveNumber10 = 28;
			int transmitnumber10 = 29;
			switch (networkNumber) {
			case 1:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive.append((networkBufferReceive.length() == 1 ? "": ",")+ "['"+ df.format(new Date())+ "',"+ GlobalConfig.networkList.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit.length() == 1 ? "" : ",")+ "['"+ df.format(new Date())+ "',"+ GlobalConfig.networkList.get(transmitnumber)+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
				}
				networkBufferReceive2.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit2.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 2:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
				}
				networkBufferReceive3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 3:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
				}
				networkBufferReceive4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 4:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
					networkBufferReceive4.append((networkBufferReceive4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber4)
							+ "]");
					receiveNumber4 = receiveNumber4 + (networkNumber * 3);
					networkBufferTransmit4.append((networkBufferTransmit4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber4)
							+ "]");
					transmitnumber4 = transmitnumber4 + (networkNumber * 3);
				}
				networkBufferReceive5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 5:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
					networkBufferReceive4.append((networkBufferReceive4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber4)
							+ "]");
					receiveNumber4 = receiveNumber4 + (networkNumber * 3);
					networkBufferTransmit4.append((networkBufferTransmit4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber4)
							+ "]");
					transmitnumber4 = transmitnumber4 + (networkNumber * 3);
					networkBufferReceive5.append((networkBufferReceive5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber5)
							+ "]");
					receiveNumber5 = receiveNumber5 + (networkNumber * 3);
					networkBufferTransmit5.append((networkBufferTransmit5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber5)
							+ "]");
					transmitnumber5 = transmitnumber5 + (networkNumber * 3);
				}
				networkBufferReceive6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 6:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
					networkBufferReceive4.append((networkBufferReceive4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber4)
							+ "]");
					receiveNumber4 = receiveNumber4 + (networkNumber * 3);
					networkBufferTransmit4.append((networkBufferTransmit4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber4)
							+ "]");
					transmitnumber4 = transmitnumber4 + (networkNumber * 3);
					networkBufferReceive5.append((networkBufferReceive5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber5)
							+ "]");
					receiveNumber5 = receiveNumber5 + (networkNumber * 3);
					networkBufferTransmit5.append((networkBufferTransmit5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber5)
							+ "]");
					transmitnumber5 = transmitnumber5 + (networkNumber * 3);
					networkBufferReceive6.append((networkBufferReceive6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber6)
							+ "]");
					receiveNumber6 = receiveNumber6 + (networkNumber * 3);
					networkBufferTransmit6.append((networkBufferTransmit6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber6)
							+ "]");
					transmitnumber6 = transmitnumber6 + (networkNumber * 3);
				}
				networkBufferReceive7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 7:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
					networkBufferReceive4.append((networkBufferReceive4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber4)
							+ "]");
					receiveNumber4 = receiveNumber4 + (networkNumber * 3);
					networkBufferTransmit4.append((networkBufferTransmit4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber4)
							+ "]");
					transmitnumber4 = transmitnumber4 + (networkNumber * 3);
					networkBufferReceive5.append((networkBufferReceive5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber5)
							+ "]");
					receiveNumber5 = receiveNumber5 + (networkNumber * 3);
					networkBufferTransmit5.append((networkBufferTransmit5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber5)
							+ "]");
					transmitnumber5 = transmitnumber5 + (networkNumber * 3);
					networkBufferReceive6.append((networkBufferReceive6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber6)
							+ "]");
					receiveNumber6 = receiveNumber6 + (networkNumber * 3);
					networkBufferTransmit6.append((networkBufferTransmit6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber6)
							+ "]");
					transmitnumber6 = transmitnumber6 + (networkNumber * 3);
					networkBufferReceive7.append((networkBufferReceive7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber7)
							+ "]");
					receiveNumber7 = receiveNumber7 + (networkNumber * 3);
					networkBufferTransmit7.append((networkBufferTransmit7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber7)
							+ "]");
					transmitnumber7 = transmitnumber7 + (networkNumber * 3);
				}
				networkBufferReceive8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 8:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
					networkBufferReceive4.append((networkBufferReceive4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber4)
							+ "]");
					receiveNumber4 = receiveNumber4 + (networkNumber * 3);
					networkBufferTransmit4.append((networkBufferTransmit4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber4)
							+ "]");
					transmitnumber4 = transmitnumber4 + (networkNumber * 3);
					networkBufferReceive5.append((networkBufferReceive5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber5)
							+ "]");
					receiveNumber5 = receiveNumber5 + (networkNumber * 3);
					networkBufferTransmit5.append((networkBufferTransmit5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber5)
							+ "]");
					transmitnumber5 = transmitnumber5 + (networkNumber * 3);
					networkBufferReceive6.append((networkBufferReceive6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber6)
							+ "]");
					receiveNumber6 = receiveNumber6 + (networkNumber * 3);
					networkBufferTransmit6.append((networkBufferTransmit6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber6)
							+ "]");
					transmitnumber6 = transmitnumber6 + (networkNumber * 3);
					networkBufferReceive7.append((networkBufferReceive7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber7)
							+ "]");
					receiveNumber7 = receiveNumber7 + (networkNumber * 3);
					networkBufferTransmit7.append((networkBufferTransmit7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber7)
							+ "]");
					transmitnumber7 = transmitnumber7 + (networkNumber * 3);
					networkBufferReceive8.append((networkBufferReceive8
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber8)
							+ "]");
					receiveNumber8 = receiveNumber8 + (networkNumber * 3);
					networkBufferTransmit8.append((networkBufferTransmit8
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber8)
							+ "]");
					transmitnumber8 = transmitnumber8 + (networkNumber * 3);
				}
				networkBufferReceive9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 9:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
					networkBufferReceive4.append((networkBufferReceive4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber4)
							+ "]");
					receiveNumber4 = receiveNumber4 + (networkNumber * 3);
					networkBufferTransmit4.append((networkBufferTransmit4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber4)
							+ "]");
					transmitnumber4 = transmitnumber4 + (networkNumber * 3);
					networkBufferReceive5.append((networkBufferReceive5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber5)
							+ "]");
					receiveNumber5 = receiveNumber5 + (networkNumber * 3);
					networkBufferTransmit5.append((networkBufferTransmit5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber5)
							+ "]");
					transmitnumber5 = transmitnumber5 + (networkNumber * 3);
					networkBufferReceive6.append((networkBufferReceive6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber6)
							+ "]");
					receiveNumber6 = receiveNumber6 + (networkNumber * 3);
					networkBufferTransmit6.append((networkBufferTransmit6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber6)
							+ "]");
					transmitnumber6 = transmitnumber6 + (networkNumber * 3);
					networkBufferReceive7.append((networkBufferReceive7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber7)
							+ "]");
					receiveNumber7 = receiveNumber7 + (networkNumber * 3);
					networkBufferTransmit7.append((networkBufferTransmit7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber7)
							+ "]");
					transmitnumber7 = transmitnumber7 + (networkNumber * 3);
					networkBufferReceive8.append((networkBufferReceive8
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber8)
							+ "]");
					receiveNumber8 = receiveNumber8 + (networkNumber * 3);
					networkBufferTransmit8.append((networkBufferTransmit8
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber8)
							+ "]");
					transmitnumber8 = transmitnumber8 + (networkNumber * 3);
					networkBufferReceive9.append((networkBufferReceive9
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber9)
							+ "]");
					receiveNumber9 = receiveNumber9 + (networkNumber * 3);
					networkBufferTransmit9.append((networkBufferTransmit9
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber9)
							+ "]");
					transmitnumber9 = transmitnumber9 + (networkNumber * 3);
				}
				networkBufferReceive10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmit10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 10:
				for (int i = 0; i < 20; i++) {
					networkBufferReceive
							.append((networkBufferReceive.length() == 1 ? ""
									: ",")
									+ "['"
									+ df.format(new Date())
									+ "',"
									+ GlobalConfig.networkList
											.get(receiveNumber) + "]");
					receiveNumber = receiveNumber + (networkNumber * 3);
					networkBufferTransmit.append((networkBufferTransmit
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber)
							+ "]");
					transmitnumber = transmitnumber + (networkNumber * 3);
					networkBufferReceive2.append((networkBufferReceive2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber2)
							+ "]");
					receiveNumber2 = receiveNumber2 + (networkNumber * 3);
					networkBufferTransmit2.append((networkBufferTransmit2
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber2)
							+ "]");
					transmitnumber2 = transmitnumber2 + (networkNumber * 3);
					networkBufferReceive3.append((networkBufferReceive3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber3)
							+ "]");
					receiveNumber3 = receiveNumber3 + (networkNumber * 3);
					networkBufferTransmit3.append((networkBufferTransmit3
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber3)
							+ "]");
					transmitnumber3 = transmitnumber3 + (networkNumber * 3);
					networkBufferReceive4.append((networkBufferReceive4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber4)
							+ "]");
					receiveNumber4 = receiveNumber4 + (networkNumber * 3);
					networkBufferTransmit4.append((networkBufferTransmit4
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber4)
							+ "]");
					transmitnumber4 = transmitnumber4 + (networkNumber * 3);
					networkBufferReceive5.append((networkBufferReceive5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber5)
							+ "]");
					receiveNumber5 = receiveNumber5 + (networkNumber * 3);
					networkBufferTransmit5.append((networkBufferTransmit5
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber5)
							+ "]");
					transmitnumber5 = transmitnumber5 + (networkNumber * 3);
					networkBufferReceive6.append((networkBufferReceive6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber6)
							+ "]");
					receiveNumber6 = receiveNumber6 + (networkNumber * 3);
					networkBufferTransmit6.append((networkBufferTransmit6
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber6)
							+ "]");
					transmitnumber6 = transmitnumber6 + (networkNumber * 3);
					networkBufferReceive7.append((networkBufferReceive7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber7)
							+ "]");
					receiveNumber7 = receiveNumber7 + (networkNumber * 3);
					networkBufferTransmit7.append((networkBufferTransmit7
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber7)
							+ "]");
					transmitnumber7 = transmitnumber7 + (networkNumber * 3);
					networkBufferReceive8.append((networkBufferReceive8
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber8)
							+ "]");
					receiveNumber8 = receiveNumber8 + (networkNumber * 3);
					networkBufferTransmit8.append((networkBufferTransmit8
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber8)
							+ "]");
					transmitnumber8 = transmitnumber8 + (networkNumber * 3);
					networkBufferReceive9.append((networkBufferReceive9
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber9)
							+ "]");
					receiveNumber9 = receiveNumber9 + (networkNumber * 3);
					networkBufferTransmit9.append((networkBufferTransmit9
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber9)
							+ "]");
					transmitnumber9 = transmitnumber9 + (networkNumber * 3);
					networkBufferReceive10.append((networkBufferReceive10
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(receiveNumber10)
							+ "]");
					receiveNumber10 = receiveNumber10 + (networkNumber * 3);
					networkBufferTransmit10.append((networkBufferTransmit10
							.length() == 1 ? "" : ",")
							+ "['"
							+ df.format(new Date())
							+ "',"
							+ GlobalConfig.networkList.get(transmitnumber10)
							+ "]");
					transmitnumber10 = transmitnumber10 + (networkNumber * 3);

				}
				break;
			}

			networkBufferReceive.append("]");
			Object networktempdataReceive = "" + networkBufferReceive + "";
			jsonArrayNetworkDataReceive = JSONArray
					.fromObject(networktempdataReceive);
			networkBufferTransmit.append("]");
			Object networktempdataTransmit = "" + networkBufferTransmit + "";
			jsonArrayNetworkDataTransmit = JSONArray
					.fromObject(networktempdataTransmit);

			networkBufferReceive2.append("]");
			Object networktempdataReceive2 = "" + networkBufferReceive2 + "";
			jsonArrayNetworkDataReceive2 = JSONArray
					.fromObject(networktempdataReceive2);
			networkBufferTransmit2.append("]");
			Object networktempdataTransmit2 = "" + networkBufferTransmit2 + "";
			jsonArrayNetworkDataTransmit2 = JSONArray
					.fromObject(networktempdataTransmit2);

			networkBufferReceive3.append("]");
			Object networktempdataReceive3 = "" + networkBufferReceive3 + "";
			jsonArrayNetworkDataReceive3 = JSONArray
					.fromObject(networktempdataReceive3);
			networkBufferTransmit3.append("]");
			Object networktempdataTransmit3 = "" + networkBufferTransmit3 + "";
			jsonArrayNetworkDataTransmit3 = JSONArray
					.fromObject(networktempdataTransmit3);

			networkBufferReceive4.append("]");
			Object networktempdataReceive4 = "" + networkBufferReceive4 + "";
			jsonArrayNetworkDataReceive4 = JSONArray
					.fromObject(networktempdataReceive4);
			networkBufferTransmit4.append("]");
			Object networktempdataTransmit4 = "" + networkBufferTransmit4 + "";
			jsonArrayNetworkDataTransmit4 = JSONArray
					.fromObject(networktempdataTransmit4);

			networkBufferReceive5.append("]");
			Object networktempdataReceive5 = "" + networkBufferReceive5 + "";
			jsonArrayNetworkDataReceive5 = JSONArray
					.fromObject(networktempdataReceive5);
			networkBufferTransmit5.append("]");
			Object networktempdataTransmit5 = "" + networkBufferTransmit5 + "";
			jsonArrayNetworkDataTransmit5 = JSONArray
					.fromObject(networktempdataTransmit5);

			networkBufferReceive6.append("]");
			Object networktempdataReceive6 = "" + networkBufferReceive6 + "";
			jsonArrayNetworkDataReceive6 = JSONArray
					.fromObject(networktempdataReceive6);
			networkBufferTransmit6.append("]");
			Object networktempdataTransmit6 = "" + networkBufferTransmit6 + "";
			jsonArrayNetworkDataTransmit6 = JSONArray
					.fromObject(networktempdataTransmit6);

			networkBufferReceive7.append("]");
			Object networktempdataReceive7 = "" + networkBufferReceive7 + "";
			jsonArrayNetworkDataReceive7 = JSONArray
					.fromObject(networktempdataReceive7);
			networkBufferTransmit7.append("]");
			Object networktempdataTransmit7 = "" + networkBufferTransmit7 + "";
			jsonArrayNetworkDataTransmit7 = JSONArray
					.fromObject(networktempdataTransmit7);

			networkBufferReceive8.append("]");
			Object networktempdataReceive8 = "" + networkBufferReceive8 + "";
			jsonArrayNetworkDataReceive8 = JSONArray
					.fromObject(networktempdataReceive8);
			networkBufferTransmit8.append("]");
			Object networktempdataTransmit8 = "" + networkBufferTransmit8 + "";
			jsonArrayNetworkDataTransmit8 = JSONArray
					.fromObject(networktempdataTransmit8);

			networkBufferReceive9.append("]");
			Object networktempdataReceive9 = "" + networkBufferReceive9 + "";
			jsonArrayNetworkDataReceive9 = JSONArray
					.fromObject(networktempdataReceive9);
			networkBufferTransmit9.append("]");
			Object networktempdataTransmit9 = "" + networkBufferTransmit9 + "";
			jsonArrayNetworkDataTransmit9 = JSONArray
					.fromObject(networktempdataTransmit9);

			networkBufferReceive10.append("]");
			Object networktempdataReceive10 = "" + networkBufferReceive10 + "";
			jsonArrayNetworkDataReceive10 = JSONArray
					.fromObject(networktempdataReceive10);
			networkBufferTransmit10.append("]");
			Object networktempdataTransmit10 = "" + networkBufferTransmit10
					+ "";
			jsonArrayNetworkDataTransmit10 = JSONArray
					.fromObject(networktempdataTransmit10);
			networkSize = SystemInfoHandle.NetworkTraffic().size() / 3;
		} else {

			networkBufferReceive.append("0]");
			Object networktempdataReceive = "" + networkBufferReceive + "";
			jsonArrayNetworkDataReceive = JSONArray
					.fromObject(networktempdataReceive);
			networkBufferTransmit.append("0]");
			Object networktempdataTransmit = "" + networkBufferTransmit + "";
			jsonArrayNetworkDataTransmit = JSONArray
					.fromObject(networktempdataTransmit);

			networkBufferReceive2.append("0]");
			Object networktempdataReceive2 = "" + networkBufferReceive2 + "";
			jsonArrayNetworkDataReceive2 = JSONArray
					.fromObject(networktempdataReceive2);
			networkBufferTransmit2.append("0]");
			Object networktempdataTransmit2 = "" + networkBufferTransmit2 + "";
			jsonArrayNetworkDataTransmit2 = JSONArray
					.fromObject(networktempdataTransmit2);

			networkBufferReceive3.append("0]");
			Object networktempdataReceive3 = "" + networkBufferReceive3 + "";
			jsonArrayNetworkDataReceive3 = JSONArray
					.fromObject(networktempdataReceive3);
			networkBufferTransmit3.append("0]");
			Object networktempdataTransmit3 = "" + networkBufferTransmit3 + "";
			jsonArrayNetworkDataTransmit3 = JSONArray
					.fromObject(networktempdataTransmit3);

			networkBufferReceive4.append("0]");
			Object networktempdataReceive4 = "" + networkBufferReceive4 + "";
			jsonArrayNetworkDataReceive4 = JSONArray
					.fromObject(networktempdataReceive4);
			networkBufferTransmit4.append("0]");
			Object networktempdataTransmit4 = "" + networkBufferTransmit4 + "";
			jsonArrayNetworkDataTransmit4 = JSONArray
					.fromObject(networktempdataTransmit4);

			networkBufferReceive5.append("0]");
			Object networktempdataReceive5 = "" + networkBufferReceive5 + "";
			jsonArrayNetworkDataReceive5 = JSONArray
					.fromObject(networktempdataReceive5);
			networkBufferTransmit5.append("0]");
			Object networktempdataTransmit5 = "" + networkBufferTransmit5 + "";
			jsonArrayNetworkDataTransmit5 = JSONArray
					.fromObject(networktempdataTransmit5);

			networkBufferReceive6.append("0]");
			Object networktempdataReceive6 = "" + networkBufferReceive6 + "";
			jsonArrayNetworkDataReceive6 = JSONArray
					.fromObject(networktempdataReceive6);
			networkBufferTransmit6.append("0]");
			Object networktempdataTransmit6 = "" + networkBufferTransmit6 + "";
			jsonArrayNetworkDataTransmit6 = JSONArray
					.fromObject(networktempdataTransmit6);

			networkBufferReceive7.append("0]");
			Object networktempdataReceive7 = "" + networkBufferReceive7 + "";
			jsonArrayNetworkDataReceive7 = JSONArray
					.fromObject(networktempdataReceive7);
			networkBufferTransmit7.append("0]");
			Object networktempdataTransmit7 = "" + networkBufferTransmit7 + "";
			jsonArrayNetworkDataTransmit7 = JSONArray
					.fromObject(networktempdataTransmit7);

			networkBufferReceive8.append("0]");
			Object networktempdataReceive8 = "" + networkBufferReceive8 + "";
			jsonArrayNetworkDataReceive8 = JSONArray
					.fromObject(networktempdataReceive8);
			networkBufferTransmit8.append("0]");
			Object networktempdataTransmit8 = "" + networkBufferTransmit8 + "";
			jsonArrayNetworkDataTransmit8 = JSONArray
					.fromObject(networktempdataTransmit8);

			networkBufferReceive9.append("0]");
			Object networktempdataReceive9 = "" + networkBufferReceive9 + "";
			jsonArrayNetworkDataReceive9 = JSONArray
					.fromObject(networktempdataReceive9);
			networkBufferTransmit9.append("0]");
			Object networktempdataTransmit9 = "" + networkBufferTransmit9 + "";
			jsonArrayNetworkDataTransmit9 = JSONArray
					.fromObject(networktempdataTransmit9);

			networkBufferReceive10.append("0]");
			Object networktempdataReceive10 = "" + networkBufferReceive10 + "";
			jsonArrayNetworkDataReceive10 = JSONArray
					.fromObject(networktempdataReceive10);
			networkBufferTransmit10.append("0]");
			Object networktempdataTransmit10 = "" + networkBufferTransmit10 + "";
			jsonArrayNetworkDataTransmit10 = JSONArray
					.fromObject(networktempdataTransmit10);
			networkSize=0;
		}

		return SUCCESS;
	}*/

	public void upCpuChart() throws IOException {
        if (OSUtil.getOSName().toLowerCase().contains("linux"))
        {
            
            JSONArray jsonUp = new JSONArray();
            
            StringBuffer cpuBufferUp = new StringBuffer(); //定义内存使用StringBuffer
            StringBuffer memoryBufferUp = new StringBuffer(); //定义内存使用StringBuffer
            cpuList = settingCpuManager.cpuQueryOne();
            
            try
            {
                sysInfo = SystemInfoService.getSysInfo();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            
            String cpu = sysInfo.getCpuRatio().replaceAll("%", "");
            String memory = sysInfo.getMemoryRatio().replaceAll("%", "");
            cpuBufferUp.append("[");
            memoryBufferUp.append("[");
            //        String cpuptUp;
            SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
            String upTime = timeFormat.format(new Date());
            
            cpuBufferUp.append("'" + upTime + "'," + cpu);
            memoryBufferUp.append("'" + upTime + "'," + memory);
            //        int cpucountUp = 0;
            //        for (CpuData cdaUp : cpuList)
            //        {
            //            cpuptUp = cdaUp.getSystemCpuEmploy();
            //            String cpudeUp = timeFormat.format(cdaUp.getSystemCpuDate());
            //            
            //            cpuBufferUp.append("'" + cpudeUp + "'," + cpuptUp);
            //            cpucountUp++;
            //        }
            
            cpuBufferUp.append("]");
            Object cputempUp = "" + cpuBufferUp + "";
            //System.out.println("cputempUp    "+cputempUp );
            memoryBufferUp.append("]");
            Object memorytempUp = "" + memoryBufferUp + "";
            //        jsonArrayCpuUp = JSONArray.fromObject(cputempUp);
            //System.out.println("memorytempUp   " +memorytempUp);
            List<Object> objectList = new ArrayList<Object>();
            Object cpuGo = cputempUp;
            Object memoryGo = memorytempUp;
            objectList.add(cpuGo);
            objectList.add(memoryGo);
            jsonUp = JSONArray.fromObject(objectList);
            try
            {
                getResponse().getWriter().write(jsonUp.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            JSONArray jsonUp = new JSONArray();
            List<Object> objectList = new ArrayList<Object>();
            objectList.add("['Please deploy the system to the Linux system!',0]");
            objectList.add("['Please deploy the system to the Linux system!',0]");
            jsonUp = JSONArray.fromObject(objectList);
            try
            {
                getResponse().getWriter().write(jsonUp.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
	
/*	public void UpdateNetwork(){
		if (OSUtil.getOSName().toLowerCase().contains("linux")) {

			JSONArray jsonUp = new JSONArray();

			StringBuffer networkBufferNameUp = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp2 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp2 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp2 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp3 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp3 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp3 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp4 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp4 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp4 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp5 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp5 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp5 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp6 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp6 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp6 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp7 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp7 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp7 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp8 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp8 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp8 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp9 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp9 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp9 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferNameUp10 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferReceiveUp10 = new StringBuffer(); // 定义内存使用StringBuffer
			StringBuffer networkBufferTransmitUp10 = new StringBuffer(); // 定义内存使用StringBuffer

			networkBufferNameUp.append("[");
			networkBufferReceiveUp.append("[");
			networkBufferTransmitUp.append("[");
			networkBufferNameUp2.append("[");
			networkBufferReceiveUp2.append("[");
			networkBufferTransmitUp2.append("[");
			networkBufferNameUp3.append("[");
			networkBufferReceiveUp3.append("[");
			networkBufferTransmitUp3.append("[");
			networkBufferNameUp4.append("[");
			networkBufferReceiveUp4.append("[");
			networkBufferTransmitUp4.append("[");
			networkBufferNameUp5.append("[");
			networkBufferReceiveUp5.append("[");
			networkBufferTransmitUp5.append("[");
			networkBufferNameUp6.append("[");
			networkBufferReceiveUp6.append("[");
			networkBufferTransmitUp6.append("[");
			networkBufferNameUp7.append("[");
			networkBufferReceiveUp7.append("[");
			networkBufferTransmitUp7.append("[");
			networkBufferNameUp8.append("[");
			networkBufferReceiveUp8.append("[");
			networkBufferTransmitUp8.append("[");
			networkBufferNameUp9.append("[");
			networkBufferReceiveUp9.append("[");
			networkBufferTransmitUp9.append("[");
			networkBufferNameUp10.append("[");
			networkBufferReceiveUp10.append("[");
			networkBufferTransmitUp10.append("[");

			DateFormat df = new SimpleDateFormat("mm:ss");
			int networkNumber = SystemInfoHandle.NetworkTraffic().size() / 3;
			int receiveNumber = 1;
			int transmitnumber = 2;
			int receiveNumber2 = 4;
			int transmitnumber2 = 5;
			int receiveNumber3 = 7;
			int transmitnumber3 = 8;
			int receiveNumber4 = 10;
			int transmitnumber4 = 11;
			int receiveNumber5 = 13;
			int transmitnumber5 = 14;
			int receiveNumber6 = 16;
			int transmitnumber6 = 17;
			int receiveNumber7 = 19;
			int transmitnumber7 = 20;
			int receiveNumber8 = 22;
			int transmitnumber8 = 23;
			int receiveNumber9 = 25;
			int transmitnumber9 = 26;
			int receiveNumber10 = 28;
			int transmitnumber10 = 29;
			switch (networkNumber) {
			case 1:
				networkBufferReceiveUp.append("'" + df.format(new Date())+ "',"+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp.append("'"+ df.format(new Date())+ "',"+ SystemInfoHandle.NetworkTraffic().get(transmitnumber));
				networkBufferReceiveUp2.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp2.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 2:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));

				networkBufferReceiveUp3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp3.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 3:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));

				networkBufferReceiveUp4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp4.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 4:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));
				networkBufferReceiveUp4
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber4));
				networkBufferTransmitUp4.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber4));

				networkBufferReceiveUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp5.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 5:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));
				networkBufferReceiveUp4
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber4));
				networkBufferTransmitUp4.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber4));
				networkBufferReceiveUp5
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber5));
				networkBufferTransmitUp5.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber5));

				networkBufferReceiveUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp6.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 6:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));
				networkBufferReceiveUp4
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber4));
				networkBufferTransmitUp4.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber4));
				networkBufferReceiveUp5
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber5));
				networkBufferTransmitUp5.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber5));
				networkBufferReceiveUp6
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber6));
				networkBufferTransmitUp6.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber6));

				networkBufferReceiveUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp7.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 7:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));
				networkBufferReceiveUp4
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber4));
				networkBufferTransmitUp4.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber4));
				networkBufferReceiveUp5
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber5));
				networkBufferTransmitUp5.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber5));
				networkBufferReceiveUp6
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber6));
				networkBufferTransmitUp6.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber6));
				networkBufferReceiveUp7
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber7));
				networkBufferTransmitUp7.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber7));

				networkBufferReceiveUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp8.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 8:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));
				networkBufferReceiveUp4
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber4));
				networkBufferTransmitUp4.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber4));
				networkBufferReceiveUp5
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber5));
				networkBufferTransmitUp5.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber5));
				networkBufferReceiveUp6
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber6));
				networkBufferTransmitUp6.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber6));
				networkBufferReceiveUp7
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber7));
				networkBufferTransmitUp7.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber7));
				networkBufferReceiveUp8
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber8));
				networkBufferTransmitUp8.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber8));

				networkBufferReceiveUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp9.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 9:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));
				networkBufferReceiveUp4
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber4));
				networkBufferTransmitUp4.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber4));
				networkBufferReceiveUp5
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber5));
				networkBufferTransmitUp5.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber5));
				networkBufferReceiveUp6
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber6));
				networkBufferTransmitUp6.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber6));
				networkBufferReceiveUp7
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber7));
				networkBufferTransmitUp7.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber7));
				networkBufferReceiveUp8
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber8));
				networkBufferTransmitUp8.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber8));
				networkBufferReceiveUp9
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber9));
				networkBufferTransmitUp9.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber9));

				networkBufferReceiveUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				networkBufferTransmitUp10.append("['"
						+ "Without this netWork card " + "'," + 0 + "]");
				break;
			case 10:
				networkBufferReceiveUp.append("'" + df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(receiveNumber));
				networkBufferTransmitUp
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										transmitnumber));
				networkBufferReceiveUp2
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber2));
				networkBufferTransmitUp2.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber2));
				networkBufferReceiveUp3
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber3));
				networkBufferTransmitUp3.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber3));
				networkBufferReceiveUp4
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber4));
				networkBufferTransmitUp4.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber4));
				networkBufferReceiveUp5
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber5));
				networkBufferTransmitUp5.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber5));
				networkBufferReceiveUp6
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber6));
				networkBufferTransmitUp6.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber6));
				networkBufferReceiveUp7
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber7));
				networkBufferTransmitUp7.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber7));
				networkBufferReceiveUp8
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber8));
				networkBufferTransmitUp8.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber8));
				networkBufferReceiveUp9
						.append("'"
								+ df.format(new Date())
								+ "',"
								+ SystemInfoHandle.NetworkTraffic().get(
										receiveNumber9));
				networkBufferTransmitUp9.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(transmitnumber9));
				networkBufferReceiveUp10.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic()
								.get(receiveNumber10));
				networkBufferTransmitUp10.append("'"
						+ df.format(new Date())
						+ "',"
						+ SystemInfoHandle.NetworkTraffic().get(
								transmitnumber10));
				break;
			}
			networkBufferReceiveUp.append("]");
			Object networktempdataReceiveUp = "" + networkBufferReceiveUp + "";
			networkBufferTransmitUp.append("]");
			Object networktempdataTransmitUp = "" + networkBufferTransmitUp+ "";

			networkBufferReceiveUp2.append("]");
			Object networktempdataReceive2 = "" + networkBufferReceiveUp2 + "";
			networkBufferTransmitUp2.append("]");
			Object networktempdataTransmit2 = "" + networkBufferTransmitUp2
					+ "";

			networkBufferReceiveUp3.append("]");
			Object networktempdataReceiveUp3 = "" + networkBufferReceiveUp3
					+ "";
			networkBufferTransmitUp3.append("]");
			Object networktempdataTransmitUp3 = "" + networkBufferTransmitUp3
					+ "";

			networkBufferReceiveUp4.append("]");
			Object networktempdataReceiveUp4 = "" + networkBufferReceiveUp4
					+ "";
			networkBufferTransmitUp4.append("]");
			Object networktempdataTransmitUp4 = "" + networkBufferTransmitUp4
					+ "";

			networkBufferReceiveUp5.append("]");
			Object networktempdataReceiveUp5 = "" + networkBufferReceiveUp5
					+ "";
			networkBufferTransmitUp5.append("]");
			Object networktempdataTransmitUp5 = "" + networkBufferTransmitUp5
					+ "";

			networkBufferReceiveUp6.append("]");
			Object networktempdataReceiveUp6 = "" + networkBufferReceiveUp6
					+ "";
			networkBufferTransmitUp6.append("]");
			Object networktempdataTransmitUp6 = "" + networkBufferTransmitUp6
					+ "";

			networkBufferReceiveUp7.append("]");
			Object networktempdataReceiveUp7 = "" + networkBufferReceiveUp7
					+ "";
			networkBufferTransmitUp7.append("]");
			Object networktempdataTransmitUp7 = "" + networkBufferTransmitUp7
					+ "";

			networkBufferReceiveUp8.append("]");
			Object networktempdataReceiveUp8 = "" + networkBufferReceiveUp8
					+ "";
			networkBufferTransmitUp8.append("]");
			Object networktempdataTransmitUp8 = "" + networkBufferTransmitUp8
					+ "";

			networkBufferReceiveUp9.append("]");
			Object networktempdataReceiveUp9 = "" + networkBufferReceiveUp9
					+ "";
			networkBufferTransmitUp9.append("]");
			Object networktempdataTransmitUp9 = "" + networkBufferTransmitUp9
					+ "";

			networkBufferReceiveUp10.append("]");
			Object networktempdataReceiveUp10 = "" + networkBufferReceiveUp10
					+ "";
			networkBufferTransmitUp10.append("]");
			Object networktempdataTransmitUp10 = "" + networkBufferTransmitUp10
					+ "";

			List<Object> objectList = new ArrayList<Object>();
			Object networkReceiveGo;
			Object networkTransmitGo;

			Object networkReceiveGo2;
			Object networkTransmitGo2;
			Object networkReceiveGo3;
			Object networkTransmitGo3;
			Object networkReceiveGo4;
			Object networkTransmitGo4;
			Object networkReceiveGo5;
			Object networkTransmitGo5;
			Object networkReceiveGo6;
			Object networkTransmitGo6;
			Object networkReceiveGo7;
			Object networkTransmitGo7;
			Object networkReceiveGo8;
			Object networkTransmitGo8;
			Object networkReceiveGo9;
			Object networkTransmitGo9;
			Object networkReceiveGo10;
			Object networkTransmitGo10;
			switch (networkNumber) {
			case 1:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);
				break;
			case 2:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);
				break;
			case 3:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);
				break;
			case 4:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);

				networkReceiveGo4 = networktempdataReceiveUp4;
				networkTransmitGo4 = networktempdataTransmitUp4;
				objectList.add(networkReceiveGo4);
				objectList.add(networkTransmitGo4);
				break;
			case 5:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);

				networkReceiveGo4 = networktempdataReceiveUp4;
				networkTransmitGo4 = networktempdataTransmitUp4;
				objectList.add(networkReceiveGo4);
				objectList.add(networkTransmitGo4);

				networkReceiveGo5 = networktempdataReceiveUp5;
				networkTransmitGo5 = networktempdataTransmitUp5;
				objectList.add(networkReceiveGo5);
				objectList.add(networkTransmitGo5);
				break;
			case 6:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);

				networkReceiveGo4 = networktempdataReceiveUp4;
				networkTransmitGo4 = networktempdataTransmitUp4;
				objectList.add(networkReceiveGo4);
				objectList.add(networkTransmitGo4);

				networkReceiveGo5 = networktempdataReceiveUp5;
				networkTransmitGo5 = networktempdataTransmitUp5;
				objectList.add(networkReceiveGo5);
				objectList.add(networkTransmitGo5);

				networkReceiveGo6 = networktempdataReceiveUp6;
				networkTransmitGo6 = networktempdataTransmitUp6;
				objectList.add(networkReceiveGo6);
				objectList.add(networkTransmitGo6);
				break;
			case 7:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);

				networkReceiveGo4 = networktempdataReceiveUp4;
				networkTransmitGo4 = networktempdataTransmitUp4;
				objectList.add(networkReceiveGo4);
				objectList.add(networkTransmitGo4);

				networkReceiveGo5 = networktempdataReceiveUp5;
				networkTransmitGo5 = networktempdataTransmitUp5;
				objectList.add(networkReceiveGo5);
				objectList.add(networkTransmitGo5);

				networkReceiveGo6 = networktempdataReceiveUp6;
				networkTransmitGo6 = networktempdataTransmitUp6;
				objectList.add(networkReceiveGo6);
				objectList.add(networkTransmitGo6);

				networkReceiveGo7 = networktempdataReceiveUp7;
				networkTransmitGo7 = networktempdataTransmitUp7;
				objectList.add(networkReceiveGo7);
				objectList.add(networkTransmitGo7);
				break;
			case 8:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);

				networkReceiveGo4 = networktempdataReceiveUp4;
				networkTransmitGo4 = networktempdataTransmitUp4;
				objectList.add(networkReceiveGo4);
				objectList.add(networkTransmitGo4);

				networkReceiveGo5 = networktempdataReceiveUp5;
				networkTransmitGo5 = networktempdataTransmitUp5;
				objectList.add(networkReceiveGo5);
				objectList.add(networkTransmitGo5);

				networkReceiveGo6 = networktempdataReceiveUp6;
				networkTransmitGo6 = networktempdataTransmitUp6;
				objectList.add(networkReceiveGo6);
				objectList.add(networkTransmitGo6);

				networkReceiveGo7 = networktempdataReceiveUp7;
				networkTransmitGo7 = networktempdataTransmitUp7;
				objectList.add(networkReceiveGo7);
				objectList.add(networkTransmitGo7);

				networkReceiveGo8 = networktempdataReceiveUp8;
				networkTransmitGo8 = networktempdataTransmitUp8;
				objectList.add(networkReceiveGo8);
				objectList.add(networkTransmitGo8);
				break;
			case 9:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);

				networkReceiveGo4 = networktempdataReceiveUp4;
				networkTransmitGo4 = networktempdataTransmitUp4;
				objectList.add(networkReceiveGo4);
				objectList.add(networkTransmitGo4);

				networkReceiveGo5 = networktempdataReceiveUp5;
				networkTransmitGo5 = networktempdataTransmitUp5;
				objectList.add(networkReceiveGo5);
				objectList.add(networkTransmitGo5);

				networkReceiveGo6 = networktempdataReceiveUp6;
				networkTransmitGo6 = networktempdataTransmitUp6;
				objectList.add(networkReceiveGo6);
				objectList.add(networkTransmitGo6);

				networkReceiveGo7 = networktempdataReceiveUp7;
				networkTransmitGo7 = networktempdataTransmitUp7;
				objectList.add(networkReceiveGo7);
				objectList.add(networkTransmitGo7);

				networkReceiveGo8 = networktempdataReceiveUp8;
				networkTransmitGo8 = networktempdataTransmitUp8;
				objectList.add(networkReceiveGo8);
				objectList.add(networkTransmitGo8);

				networkReceiveGo9 = networktempdataReceiveUp9;
				networkTransmitGo9 = networktempdataTransmitUp9;
				objectList.add(networkReceiveGo9);
				objectList.add(networkTransmitGo9);
				break;
			case 10:
				networkReceiveGo = networktempdataReceiveUp;
				networkTransmitGo = networktempdataTransmitUp;
				objectList.add(networkReceiveGo);
				objectList.add(networkTransmitGo);

				networkReceiveGo2 = networktempdataReceive2;
				networkTransmitGo2 = networktempdataTransmit2;
				objectList.add(networkReceiveGo2);
				objectList.add(networkTransmitGo2);

				networkReceiveGo3 = networktempdataReceiveUp3;
				networkTransmitGo3 = networktempdataTransmitUp3;
				objectList.add(networkReceiveGo3);
				objectList.add(networkTransmitGo3);

				networkReceiveGo4 = networktempdataReceiveUp4;
				networkTransmitGo4 = networktempdataTransmitUp4;
				objectList.add(networkReceiveGo4);
				objectList.add(networkTransmitGo4);

				networkReceiveGo5 = networktempdataReceiveUp5;
				networkTransmitGo5 = networktempdataTransmitUp5;
				objectList.add(networkReceiveGo5);
				objectList.add(networkTransmitGo5);

				networkReceiveGo6 = networktempdataReceiveUp6;
				networkTransmitGo6 = networktempdataTransmitUp6;
				objectList.add(networkReceiveGo6);
				objectList.add(networkTransmitGo6);

				networkReceiveGo7 = networktempdataReceiveUp7;
				networkTransmitGo7 = networktempdataTransmitUp7;
				objectList.add(networkReceiveGo7);
				objectList.add(networkTransmitGo7);

				networkReceiveGo8 = networktempdataReceiveUp8;
				networkTransmitGo8 = networktempdataTransmitUp8;
				objectList.add(networkReceiveGo8);
				objectList.add(networkTransmitGo8);

				networkReceiveGo9 = networktempdataReceiveUp9;
				networkTransmitGo9 = networktempdataTransmitUp9;
				objectList.add(networkReceiveGo9);
				objectList.add(networkTransmitGo9);

				networkReceiveGo10 = networktempdataReceiveUp10;
				networkTransmitGo10 = networktempdataTransmitUp10;
				objectList.add(networkReceiveGo10);
				objectList.add(networkTransmitGo10);
				break;
			}

			jsonUp = JSONArray.fromObject(objectList);
			try {
				getResponse().getWriter().write(jsonUp.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			
		}
	}*/

	/**
	 * <获取系统状态信息> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getSysStatus() {
		  HttpServletRequest request = super.getRequest();
	        if(!OSUtil.getOSName().toLowerCase().contains("linux")){
	            request.setAttribute("showCpuDataPic", "0");
	            super.addActionMessage("系统状态功能必须部署在liunx系统上");
	            return SUCCESS;
	        }else{
	            Map <String,Object> map = new HashMap<String, Object>();
	            request.setAttribute("showCpuDataPic", "1");
	            request.setAttribute("dateTime",new Date().getTime());//获取服务器时间的毫秒数传到前台
	            super.clearErrorsAndMessages();
	            SimpleDateFormat dFormat=null;
	            java.util.TimeZone tz = java.util.TimeZone.getTimeZone("Etc/GMT-8");
	            java.util.TimeZone.setDefault(tz);
	            Date date= new Date();
	            Calendar calendar  = Calendar.getInstance();
	            calendar.setTime(date);
	            dFormat = new SimpleDateFormat("yyyy-MM-dd");
	            sysDate = dFormat.format(date);
	            dFormat = new SimpleDateFormat("HH:mm:ss");
	            sysTime = dFormat.format(date);
	            dFormat = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
	            sysNowTime = dFormat.format(date);
	            try {
	                sysInfo = SystemInfoService.getSysInfo();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            sysInfo.setSysNowTime(sysNowTime);
	            if(StringUtil.equals(sysInfo.getCpuPeak(), "0%")){
	                sysInfo.setCpuPeak(sysInfo.getCpuRatio());
	            }
	            
	            if(sysInfo != null){
	                //内存饼图数据
	                pieRomData = new StringBuffer();
	                String usedMemoryRatio=sysInfo.getUsedMemory();
	                String unUsedMemoryRatio=sysInfo.getUnuseMemory();
	                usedMemoryRatio=usedMemoryRatio.replaceAll("%", "");
	                unUsedMemoryRatio=unUsedMemoryRatio.replaceAll("%", "");
	                pieRomData.append("['已占用',"+usedMemoryRatio.substring(0, usedMemoryRatio.length()-1)+"],");
	                pieRomData.append("['未占用',"+unUsedMemoryRatio.substring(0, unUsedMemoryRatio.length()-1)+"]");
	                //map.put("pieRomData", pieRomData.toString());
	                
	                 //磁盘告警条形图
	                  diskXJson = new StringBuffer();
	                  sumRom = new StringBuffer();
	                  unUseRom = new StringBuffer();
	                  useRom = new StringBuffer();
	                List list = sysInfo.getDiskList();
	                diskXJson.append("[");
	                for (int i = 0; i < list.size(); i++)
	                {
	                    String[] temp = (String[])list.get(i);
	                    
	                    for (int j = 0; j < temp.length; j++)
	                    {
	                        if (j == 0)
	                        {
	                            if (i == list.size() - 1)
	                            {
	                                diskXJson.append("'" + temp[j] + "'");
	                            }
	                            else
	                            {
	                                diskXJson.append("'" + temp[j] + "',");
	                            }
	                            
	                        }
	                        else if (j == 1)
	                        {
	                            if (i == list.size() - 1)
	                            {
	                                sumRom.append(temp[j].equals("0")?temp[j]:temp[j].substring(0, temp[j].length()-1));
	                            }
	                            else{
	                                sumRom.append(temp[j].equals("0")?temp[j]+",":temp[j].substring(0, temp[j].length()-1) + ",");
	                            }
	                        }
	                        else if (j == 2)
	                        {
	                            if (temp[j].contains("K"))
	                            {
	                                java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");       
	                                if (i == list.size() - 1)
	                                {
	                                    useRom.append(df.format(Double.parseDouble(temp[j].substring(0, temp[j].length() - 1))/1024));
	                                }
	                                else
	                                {
	                                    useRom.append(df.format(Double.parseDouble(temp[j].substring(0, temp[j].length() - 1))/1024)
	                                        + ",");
	                                }
	                            }
	                            else
	                            {
	                                if (i == list.size() - 1)
	                                {
	                                    useRom.append(temp[j].equals("0") ? temp[j]
	                                        : temp[j].substring(0, temp[j].length() - 1));
	                                }
	                                else
	                                {
	                                    useRom.append(temp[j].equals("0") ? temp[j] + "," : temp[j].substring(0,
	                                        temp[j].length() - 1)
	                                        + ",");
	                                }
	                            }
	                            
	                         
	                        }
	                        else if (j == 3)
	                        {
	                            if (temp[j].contains("K"))
	                            {
	                                java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");   
	                                    
	                                if (i == list.size() - 1)
	                                {
	                                    unUseRom.append(df.format(Double.parseDouble(temp[j].substring(0, temp[j].length() - 1))/1024));
	                                }else{
	                                    unUseRom.append(df.format(Double.parseDouble(temp[j].substring(0, temp[j].length() - 1))/1024) + ",");
	                                }
	                            }
	                            else
	                            {
	                                if (i == list.size() - 1)
	                                {
	                                    unUseRom.append(temp[j].equals("0")?temp[j]:temp[j].substring(0, temp[j].length()-1));
	                                }else{
	                                    unUseRom.append(temp[j].equals("0")?temp[j]+",":temp[j].substring(0, temp[j].length()-1) + ",");
	                                }
	                            }
	                          
	                        }
	                        
	                    }
	                }
	                
	                //封装json数据结束
	                diskXJson.append("]");
	                map.put("diskXJson", diskXJson.toString());
	                map.put("sumRom", sumRom.toString());
	                map.put("useRom", useRom.toString());
	                map.put("unUseRom", unUseRom.toString());
	            }
	            return SUCCESS;
			}
		/*if (!OSUtil.getOSName().toLowerCase().contains("linux")) {
			super.addActionMessage("系统状态功能必须部署在liunx系统上");
			return SUCCESS;
		} else {
			super.clearErrorsAndMessages();
			SimpleDateFormat dFormat = null;
			java.util.TimeZone tz = java.util.TimeZone.getTimeZone("Etc/GMT-8");
			java.util.TimeZone.setDefault(tz);
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
			minute = String.valueOf(calendar.get(Calendar.MINUTE));
			second = String.valueOf(calendar.get(Calendar.SECOND));
			dFormat = new SimpleDateFormat("yyyy-MM-dd");
			sysDate = dFormat.format(date);
			dFormat = new SimpleDateFormat("HH:mm:ss");
			sysTime = dFormat.format(date);
			dFormat = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
			sysNowTime = dFormat.format(date);
			//System.out.println(dFormat.format(date));
			try {
				sysInfo = SystemInfoService.getSysInfo();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sysInfo.setSysNowTime(sysNowTime);
			if (StringUtil.equals(sysInfo.getCpuPeak(), "0%")) {
				sysInfo.setCpuPeak(sysInfo.getCpuRatio());
			}
			return SUCCESS;
		}*/

	}

	/**
	 * <设置系统时间> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String sysTimeSetting() {
		List<String> fieldList = new ArrayList<String>();
		String date1 = DateUtil.curDateTimeStr19();
		HttpServletRequest request=getRequest();	
		SimpleDateFormat dFormat =null;
		if(sysDate!=null&&!StringUtil.equals(sysDate,"")){
			dFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = dFormat.parse(sysDate);
				dFormat = new SimpleDateFormat("MM/dd/yyyy");
				String dateTemp = dFormat.format(date);
				Runtime.getRuntime().exec("date -s "+dateTemp);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if(sysTime!=null&&!StringUtil.equals(sysTime, "")){
			try {
				Runtime.getRuntime().exec("date -s "+sysTime);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*SimpleDateFormat dFormat = null;
		if (sysDate != null && !StringUtil.equals(sysDate, "")) {
			dFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				//System.out.println(sysDate);
				Date date = dFormat.parse(sysDate);
				dFormat = new SimpleDateFormat("MM/dd/yyyy");
				String dateTemp = dFormat.format(date);
				Runtime.getRuntime().exec("date -s " + dateTemp);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (hour.length() == 1) {
			hour = "0" + hour;
		}
		if (minute.length() == 1) {
			minute = "0" + minute;
		}
		if (second.length() == 1) {
			second = "0" + second;
		}
		sysTime = hour + ":" + minute + ":" + second;
		if (sysTime != null && !StringUtil.equals(sysTime, "")) {
			try {
				Runtime.getRuntime().exec("date -s " + sysTime);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
*/
		fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName());
		/*
		 * auditManager.insertByUpdateOperator(((User)this.getSession().getAttribute
		 * ("SOC_LOGON_USER")).getUserId(), "系统时间",
		 * super.getRequest().getRemoteAddr(), fieldList);
		 */

		// syslog
		/*String logString = "";

		logString = "登录名:"
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + " 源IP:"
				+ getRequest().getRemoteAddr() + " 操作时间:"
				+ DateUtil.curDateTimeStr19() + " 操作类型 :系统时间设置";

		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改系统时间");
		return SUCCESS;
	}

	public SettingCpuService getSettingCpuManager() {
		return settingCpuManager;
	}

	public void setSettingCpuManager(SettingCpuService settingCpuManager) {
		this.settingCpuManager = settingCpuManager;
	}

	public List<CpuData> getCpuList() {
		return cpuList;
	}

	public void setCpuList(List<CpuData> cpuList) {
		this.cpuList = cpuList;
	}

	public String getSysNowTime() {
		return sysNowTime;
	}

	public void setSysNowTime(String sysNowTime) {
		this.sysNowTime = sysNowTime;
	}

	public SysInfo getSysInfo() {
		return sysInfo;
	}

	public void setSysInfo(SysInfo sysInfo) {
		this.sysInfo = sysInfo;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getSysTime() {
		return sysTime;
	}

	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public SystemMemoryService getSettingMemoryManager() {
		return settingMemoryManager;
	}

	public void setSettingMemoryManager(SystemMemoryService settingMemoryManager) {
		this.settingMemoryManager = settingMemoryManager;
	}

	public List<MemoryData> getMemoryList() {
		return memoryList;
	}

	public void setMemoryList(List<MemoryData> memoryList) {
		this.memoryList = memoryList;
	}

	public JSONArray getJsonArrayMemory() {
		return jsonArrayMemory;
	}

	public void setJsonArrayMemory(JSONArray jsonArrayMemory) {
		this.jsonArrayMemory = jsonArrayMemory;
	}

	public JSONArray getJsonArrayCpu() {
		return jsonArrayCpu;
	}

	public void setJsonArrayCpu(JSONArray jsonArrayCpu) {
		this.jsonArrayCpu = jsonArrayCpu;
	}

	public JSONArray getJsonArrayDisk() {
		return jsonArrayDisk;
	}

	public void setJsonArrayDisk(JSONArray jsonArrayDisk) {
		this.jsonArrayDisk = jsonArrayDisk;
	}

	public JSONArray getJsonArrayMemoryUp() {
		return jsonArrayMemoryUp;
	}

	public void setJsonArrayMemoryUp(JSONArray jsonArrayMemoryUp) {
		this.jsonArrayMemoryUp = jsonArrayMemoryUp;
	}

	public JSONArray getJsonArrayCpuUp() {
		return jsonArrayCpuUp;
	}

	public void setJsonArrayCpuUp(JSONArray jsonArrayCpuUp) {
		this.jsonArrayCpuUp = jsonArrayCpuUp;
	}

	public JSONArray getJsonArrayDiskUp() {
		return jsonArrayDiskUp;
	}

	public void setJsonArrayDiskUp(JSONArray jsonArrayDiskUp) {
		this.jsonArrayDiskUp = jsonArrayDiskUp;
	}

	public List<String[]> getDiskList() {
		return diskList;
	}

	public void setDiskList(List<String[]> diskList) {
		this.diskList = diskList;
	}

	public JSONArray getJsonArrayDiskData() {
		return jsonArrayDiskData;
	}

	public void setJsonArrayDiskData(JSONArray jsonArrayDiskData) {
		this.jsonArrayDiskData = jsonArrayDiskData;
	}

	public JSONArray getJsonArrayDiskData8() {
		return jsonArrayDiskData8;
	}

	public void setJsonArrayDiskData8(JSONArray jsonArrayDiskData8) {
		this.jsonArrayDiskData8 = jsonArrayDiskData8;
	}

	public JSONArray getJsonArrayDiskData9() {
		return jsonArrayDiskData9;
	}

	public void setJsonArrayDiskData9(JSONArray jsonArrayDiskData9) {
		this.jsonArrayDiskData9 = jsonArrayDiskData9;
	}

	public JSONArray getJsonArrayNetworkDataReceive() {
		return jsonArrayNetworkDataReceive;
	}

	public void setJsonArrayNetworkDataReceive(
			JSONArray jsonArrayNetworkDataReceive) {
		this.jsonArrayNetworkDataReceive = jsonArrayNetworkDataReceive;
	}

	public JSONArray getJsonArrayNetworkDataTransmit() {
		return jsonArrayNetworkDataTransmit;
	}

	public void setJsonArrayNetworkDataTransmit(
			JSONArray jsonArrayNetworkDataTransmit) {
		this.jsonArrayNetworkDataTransmit = jsonArrayNetworkDataTransmit;
	}

	public JSONArray getJsonArrayNetworkDataReceive2() {
		return jsonArrayNetworkDataReceive2;
	}

	public void setJsonArrayNetworkDataReceive2(
			JSONArray jsonArrayNetworkDataReceive2) {
		this.jsonArrayNetworkDataReceive2 = jsonArrayNetworkDataReceive2;
	}

	public JSONArray getJsonArrayNetworkDataTransmit2() {
		return jsonArrayNetworkDataTransmit2;
	}

	public void setJsonArrayNetworkDataTransmit2(
			JSONArray jsonArrayNetworkDataTransmit2) {
		this.jsonArrayNetworkDataTransmit2 = jsonArrayNetworkDataTransmit2;
	}

	public JSONArray getJsonArrayNetworkDataReceive3() {
		return jsonArrayNetworkDataReceive3;
	}

	public void setJsonArrayNetworkDataReceive3(
			JSONArray jsonArrayNetworkDataReceive3) {
		this.jsonArrayNetworkDataReceive3 = jsonArrayNetworkDataReceive3;
	}

	public JSONArray getJsonArrayNetworkDataTransmit3() {
		return jsonArrayNetworkDataTransmit3;
	}

	public void setJsonArrayNetworkDataTransmit3(
			JSONArray jsonArrayNetworkDataTransmit3) {
		this.jsonArrayNetworkDataTransmit3 = jsonArrayNetworkDataTransmit3;
	}

	public JSONArray getJsonArrayNetworkDataReceive4() {
		return jsonArrayNetworkDataReceive4;
	}

	public void setJsonArrayNetworkDataReceive4(
			JSONArray jsonArrayNetworkDataReceive4) {
		this.jsonArrayNetworkDataReceive4 = jsonArrayNetworkDataReceive4;
	}

	public JSONArray getJsonArrayNetworkDataTransmit4() {
		return jsonArrayNetworkDataTransmit4;
	}

	public void setJsonArrayNetworkDataTransmit4(
			JSONArray jsonArrayNetworkDataTransmit4) {
		this.jsonArrayNetworkDataTransmit4 = jsonArrayNetworkDataTransmit4;
	}

	public JSONArray getJsonArrayNetworkDataReceive5() {
		return jsonArrayNetworkDataReceive5;
	}

	public void setJsonArrayNetworkDataReceive5(
			JSONArray jsonArrayNetworkDataReceive5) {
		this.jsonArrayNetworkDataReceive5 = jsonArrayNetworkDataReceive5;
	}

	public JSONArray getJsonArrayNetworkDataTransmit5() {
		return jsonArrayNetworkDataTransmit5;
	}

	public void setJsonArrayNetworkDataTransmit5(
			JSONArray jsonArrayNetworkDataTransmit5) {
		this.jsonArrayNetworkDataTransmit5 = jsonArrayNetworkDataTransmit5;
	}

	public JSONArray getJsonArrayNetworkDataReceive6() {
		return jsonArrayNetworkDataReceive6;
	}

	public void setJsonArrayNetworkDataReceive6(
			JSONArray jsonArrayNetworkDataReceive6) {
		this.jsonArrayNetworkDataReceive6 = jsonArrayNetworkDataReceive6;
	}

	public JSONArray getJsonArrayNetworkDataTransmit6() {
		return jsonArrayNetworkDataTransmit6;
	}

	public void setJsonArrayNetworkDataTransmit6(
			JSONArray jsonArrayNetworkDataTransmit6) {
		this.jsonArrayNetworkDataTransmit6 = jsonArrayNetworkDataTransmit6;
	}

	public JSONArray getJsonArrayNetworkDataReceive7() {
		return jsonArrayNetworkDataReceive7;
	}

	public void setJsonArrayNetworkDataReceive7(
			JSONArray jsonArrayNetworkDataReceive7) {
		this.jsonArrayNetworkDataReceive7 = jsonArrayNetworkDataReceive7;
	}

	public JSONArray getJsonArrayNetworkDataTransmit7() {
		return jsonArrayNetworkDataTransmit7;
	}

	public void setJsonArrayNetworkDataTransmit7(
			JSONArray jsonArrayNetworkDataTransmit7) {
		this.jsonArrayNetworkDataTransmit7 = jsonArrayNetworkDataTransmit7;
	}

	public JSONArray getJsonArrayNetworkDataReceive8() {
		return jsonArrayNetworkDataReceive8;
	}

	public void setJsonArrayNetworkDataReceive8(
			JSONArray jsonArrayNetworkDataReceive8) {
		this.jsonArrayNetworkDataReceive8 = jsonArrayNetworkDataReceive8;
	}

	public JSONArray getJsonArrayNetworkDataTransmit8() {
		return jsonArrayNetworkDataTransmit8;
	}

	public void setJsonArrayNetworkDataTransmit8(
			JSONArray jsonArrayNetworkDataTransmit8) {
		this.jsonArrayNetworkDataTransmit8 = jsonArrayNetworkDataTransmit8;
	}

	public JSONArray getJsonArrayNetworkDataReceive9() {
		return jsonArrayNetworkDataReceive9;
	}

	public void setJsonArrayNetworkDataReceive9(
			JSONArray jsonArrayNetworkDataReceive9) {
		this.jsonArrayNetworkDataReceive9 = jsonArrayNetworkDataReceive9;
	}

	public JSONArray getJsonArrayNetworkDataTransmit9() {
		return jsonArrayNetworkDataTransmit9;
	}

	public void setJsonArrayNetworkDataTransmit9(
			JSONArray jsonArrayNetworkDataTransmit9) {
		this.jsonArrayNetworkDataTransmit9 = jsonArrayNetworkDataTransmit9;
	}

	public JSONArray getJsonArrayNetworkDataReceive10() {
		return jsonArrayNetworkDataReceive10;
	}

	public void setJsonArrayNetworkDataReceive10(
			JSONArray jsonArrayNetworkDataReceive10) {
		this.jsonArrayNetworkDataReceive10 = jsonArrayNetworkDataReceive10;
	}

	public JSONArray getJsonArrayNetworkDataTransmit10() {
		return jsonArrayNetworkDataTransmit10;
	}

	public void setJsonArrayNetworkDataTransmit10(
			JSONArray jsonArrayNetworkDataTransmit10) {
		this.jsonArrayNetworkDataTransmit10 = jsonArrayNetworkDataTransmit10;
	}

	public Integer getNetworkSize() {
		return networkSize;
	}

	public void setNetworkSize(Integer networkSize) {
		this.networkSize = networkSize;
	}

	public StringBuffer getPieRomData() {
		return pieRomData;
	}

	public void setPieRomData(StringBuffer pieRomData) {
		this.pieRomData = pieRomData;
	}

	public StringBuffer getSumRom() {
		return sumRom;
	}

	public void setSumRom(StringBuffer sumRom) {
		this.sumRom = sumRom;
	}

	public StringBuffer getUseRom() {
		return useRom;
	}

	public void setUseRom(StringBuffer useRom) {
		this.useRom = useRom;
	}

	public StringBuffer getUnUseRom() {
		return unUseRom;
	}

	public void setUnUseRom(StringBuffer unUseRom) {
		this.unUseRom = unUseRom;
	}

	public StringBuffer getDiskXJson() {
		return diskXJson;
	}

	public void setDiskXJson(StringBuffer diskXJson) {
		this.diskXJson = diskXJson;
	}

	public ArrayList<SettingSetCpuData> getCpu() {
		return cpu;
	}

	public void setCpu(ArrayList<SettingSetCpuData> cpu) {
		this.cpu = cpu;
	}

	public ArrayList<SettingSetCpuData> getMemory() {
		return memory;
	}

	public void setMemory(ArrayList<SettingSetCpuData> memory) {
		this.memory = memory;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}
	

}