package com.soc.webapp.action.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.f;

import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.MonitorAlarmHistory;
import com.soc.model.progressmsg.WinProgressMsg;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;

import com.soc.service.monitor.MonitorHistoryService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

@SuppressWarnings("serial")
public class MonitorAlarmHistoryAction extends BaseAction {
	private MonitorHistoryService monitorHistoryManager;//历史信息server
	private String ip;//资产ip
	private String startTime;//高级查询开始时间
	private String endTime;//高级查询结束时间
	private String falg;//标识符：1代表的是监控，2代表的是进程，3代表的是服务，4代表的是软件信息
	private List<MonitorAlarmHistory> monitorAlarmHistories;
	private List<LinuxProgressMsg> linuxProgressMsgs;
	private List<LinuxServiceMsg> linuxServiceMsgs;
	private List<WinProgressMsg> winProgressMsgs;
	private List<WinSoftMsg> winSoftMsgs;
	private List<WinServiceMsg> winServiceMsgs;
	private String keyword;
	private String biaozhi;//标识是windows还是linux：40代表linux，41代表windows
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String query(){
		HttpServletRequest request = super.getRequest();

		Page page = null;
		
		Map map = new HashMap();
		//存放查询条件
		map.put("ip", ip);
		// 处理数据分页的起始条数
				String startIndex = request.getParameter("startIndex");
				try {
					if (StringUtil.isNotBlank(startIndex)) {
						if (keyword==null || keyword.equals("")) {
							page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
						}else {
							if (Page.getKeyword().equals(keyword)) {
								page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
								Page.setKeyword(keyword);
							}else{
								page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
								Page.setKeyword(keyword);
							}
						}
						
					} else {
						page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
						Page.setKeyword(keyword);
					}			
				} catch (Exception e) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
					Page.setKeyword(keyword);
				}
			
				if (StringUtil.isNotEmpty(startTime)) {
					map.put("startTime", this.formatTime(startTime, "开始"));
				}
				if (StringUtil.isNotEmpty(endTime)) {
					map.put("endTime", this.formatTime(endTime, ""));
				}
				//查询监控
				if ("1".equals(falg)) {
					this.queryAlarmHistories(map,request,page);
					return "mah";
				}
				if ("40".equals(biaozhi)) {
					if ("2".equals(falg)) {
						this.queryLinuxProgressMsgs(map, request, page);
						return "linuxPro";
					}
					if ("3".equals(falg)) {
						this.queryLinuxServiceMsgs(map, request, page);
						return "linuxSer";
					}
				}
				if ("41".equals(biaozhi)) {
					if ("2".equals(falg)) {
						this.queryWinProgressMsgs(map, request, page);
						return "winPro";
					}
					if ("3".equals(falg)) {
						this.queryWinServiceMsgs(map, request, page);
						return "winSer";
					}
					if ("4".equals(falg)) {
						this.queryWinSoftMsgs(map, request, page);
						return "winSotf";
					}
				}
		return SUCCESS;
	}
	//查询监控
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<MonitorAlarmHistory> queryAlarmHistories(Map map,HttpServletRequest request,Page page) {
		SearchResult sr = null;
		sr = monitorHistoryManager.queryMonitorAlarmHistories(map,page);
		if (sr!=null) {
			monitorAlarmHistories = sr.getList();
			request.setAttribute("Page", sr.getPage());
		}else{
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return monitorAlarmHistories;
	}
	//查询linux进程
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LinuxProgressMsg> queryLinuxProgressMsgs(Map map,HttpServletRequest request,Page page){
		SearchResult sr = null;
		sr = monitorHistoryManager.queryLinuxProgressMsgs(map, page);
		if (sr!=null) {
			linuxProgressMsgs = sr.getList();
			request.setAttribute("Page", sr.getPage());
		}else{
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return linuxProgressMsgs;
	}
	//查询linux服务
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<LinuxServiceMsg> queryLinuxServiceMsgs(Map map,HttpServletRequest request,Page page){
		SearchResult sr = null;
		sr = monitorHistoryManager.queryLinuxServiceMsgs(map, page);
		if (sr!=null) {
			linuxServiceMsgs = sr.getList();
			request.setAttribute("Page", sr.getPage());
		}else{
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return linuxServiceMsgs;
	}
	//查询windows进程
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<WinProgressMsg> queryWinProgressMsgs(Map map,HttpServletRequest request,Page page){
		SearchResult sr = null;
		sr = monitorHistoryManager.queryWinProgressMsgs(map, page);
		if (sr!=null) {
			winProgressMsgs = sr.getList();
			request.setAttribute("Page", sr.getPage());
		}else{
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return winProgressMsgs;
	}
	//查询windows服务
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<WinServiceMsg> queryWinServiceMsgs(Map map,HttpServletRequest request,Page page){
		SearchResult sr = null;
		sr = monitorHistoryManager.queryWinServiceMsgs(map, page);
		if (sr!=null) {
			winServiceMsgs = sr.getList();
			request.setAttribute("Page", sr.getPage());
		}else{
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return winServiceMsgs;
	}
	//查询windows软件信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<WinSoftMsg> queryWinSoftMsgs(Map map,HttpServletRequest request,Page page){
		SearchResult sr = null;
		sr = monitorHistoryManager.queryWinSoftMsgs(map, page);
		if (sr!=null) {
			winSoftMsgs = sr.getList();
			request.setAttribute("Page", sr.getPage());
		}else{
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return winSoftMsgs;
	}
	//格式化时间
	public Date formatTime(String time,String end){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );		
		SimpleDateFormat sdf1 =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Date date = null;
		String formatTime = "";
		try {
			date = sdf.parse(time);	
			//判断传入时间，如果是开始时间就格式化到0点，如果是结束时间就+1天
			if (StringUtil.isNotEmpty(end)) {
				formatTime=  sdf1.format(new Date(date.getTime()));
			}else{
				formatTime=  sdf1.format(new Date(date.getTime()+86400000));
				}
			date= sdf1.parse(formatTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	
	public String getKeyword() {
		return keyword;
	}




	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}




	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFalg() {
		return falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}

	

	public MonitorHistoryService getMonitorHistoryManager() {
		return monitorHistoryManager;
	}
	public void setMonitorHistoryManager(MonitorHistoryService monitorHistoryManager) {
		this.monitorHistoryManager = monitorHistoryManager;
	}
	public List<MonitorAlarmHistory> getMonitorAlarmHistories() {
		return monitorAlarmHistories;
	}

	public void setMonitorAlarmHistories(
			List<MonitorAlarmHistory> monitorAlarmHistories) {
		this.monitorAlarmHistories = monitorAlarmHistories;
	}

	public List<LinuxProgressMsg> getLinuxProgressMsgs() {
		return linuxProgressMsgs;
	}

	public void setLinuxProgressMsgs(List<LinuxProgressMsg> linuxProgressMsgs) {
		this.linuxProgressMsgs = linuxProgressMsgs;
	}

	public List<LinuxServiceMsg> getLinuxServiceMsgs() {
		return linuxServiceMsgs;
	}

	public void setLinuxServiceMsgs(List<LinuxServiceMsg> linuxServiceMsgs) {
		this.linuxServiceMsgs = linuxServiceMsgs;
	}

	public List<WinProgressMsg> getWinProgressMsgs() {
		return winProgressMsgs;
	}

	public void setWinProgressMsgs(List<WinProgressMsg> winProgressMsgs) {
		this.winProgressMsgs = winProgressMsgs;
	}

	public List<WinSoftMsg> getWinSoftMsgs() {
		return winSoftMsgs;
	}

	public void setWinSoftMsgs(List<WinSoftMsg> winSoftMsgs) {
		this.winSoftMsgs = winSoftMsgs;
	}

	public List<WinServiceMsg> getWinServiceMsgs() {
		return winServiceMsgs;
	}

	public void setWinServiceMsgs(List<WinServiceMsg> winServiceMsgs) {
		this.winServiceMsgs = winServiceMsgs;
	}
	public String getBiaozhi() {
		return biaozhi;
	}
	public void setBiaozhi(String biaozhi) {
		this.biaozhi = biaozhi;
	}
	
	
	
}
