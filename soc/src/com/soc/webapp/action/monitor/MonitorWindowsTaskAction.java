package com.soc.webapp.action.monitor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;
import com.soc.model.monitor.MonitorWindowsTask;
import com.soc.model.monitor.computer.windows.WindowsDetail;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.monitor.MonitorWindowsTaskService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

@SuppressWarnings({ "serial", "rawtypes" })
public class MonitorWindowsTaskAction extends BaseAction {
	private AuditService auditManager;//内部审计
	private MonitorWindowsTaskService mwtManager;
	private List<MonitorWindowsTask> mwtList;//监控对象集合
	private String keyword;//快速搜索关键字
	private String communityName;//SNMP团体名称
	private String winIp;//主机ip地址
	private String winName;//监控任务名称
	private long winId;//用来存放查询的监控对象Id
	private String winType;//主机snmp类别
	private MonitorWindowsTask monwt;
	private int port;
	private int stopORstart;//是否开启监控：0代表停止，1代表开始
	private String[] timeOids={"1.3.6.1.2.1.1.3.0"};//系统运行时间
	private String[] hostNameOids={"1.3.6.1.2.1.1.5.0"};//主机名称
	private String[] cpuOids = {"1.3.6.1.2.1.25.3.3.1.2"};       //cpu 
	private String[] memoryOids = {"1.3.6.1.2.1.25.2.3.1.2",     //type //内存 磁盘
		                           "1.3.6.1.2.1.25.2.3.1.3",     //descr
		                           "1.3.6.1.2.1.25.2.3.1.4",     //unit
		                           "1.3.6.1.2.1.25.2.3.1.5",     //size
		                           "1.3.6.1.2.1.25.2.3.1.6"};    //used 
	private String[] processOids = {"1.3.6.1.2.1.25.4.2.1.2"};   //进程
	private String[] serviceOids = {"1.3.6.1.4.1.77.1.2.3.1.1"}; //服务
	private String[] softOids = {"1.3.6.1.2.1.25.6.3.1.2"};      //软件
	private String[] interfaceOids = {"1.3.6.1.2.1.2.2.1.2"};    //网络接口
	private String[] sendByteOids={"1.3.6.1.2.1.2.2.1.16"};      //发送字节数
	private String[] arriveByteOids={"1.3.6.1.2.1.2.2.1.10"};     //接收字节数
	private String[] ifSpeed={"1.3.6.1.2.1.2.2.1.5"};
	private String[] arriveErrorOids={"1.3.6.1.2.1.2.2.1.14"};     //接收错误
	private String[] sendErrorOids={"1.3.6.1.2.1.2.2.1.20"};     //发送错误
	private String[] arriveLoseOids={"1.3.6.1.2.1.2.2.1.13"};     //接收丢包数
	private String[] sendLoseOids={"1.3.6.1.2.1.2.2.1.19"};     //发送丢包数
	private String[] ifOutUcastPkts={"1.3.6.1.2.1.2.2.1.17"};   //高层请求单传发送包总数
	private String[] ifOutNUcastPkts={"1.3.6.1.2.1.2.2.1.18"};   //高层请求非单传发送包总数
	private String[] ifInUcastPkts={"1.3.6.1.2.1.2.2.1.11"};    //向高层传递的单传数据包数
	private String[] ifInNUcastPkts={"1.3.6.1.2.1.2.2.1.12"};  //向高层传递的非单传数据包数
	private String mwtIdStr;//监控对象ID拼接字符串
	private WindowsDetail winDetail;//用于存储Windows信息
	private List<WindowsDetail> infoDetail;//用于存储Windows信息
	
	//查询所有监控对象列表
	public String queryAll(){
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult<MonitorWindowsTask> sr = null;
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
				// 接收查询条件，并存储到map中
				Map<String, Object> map = new HashMap<String, Object>();
				if (request.getParameter("keyword") != null) {
					try {
						keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					map.put("keyword", keyword);
				}
				if(StringUtil.isNotEmpty(winIp)){
					map.put("winIp", winIp);
				}
				if(StringUtil.isNotEmpty(winName)){
					map.put("winName", winName);
				}
				map.put("winType", winType);
				sr=mwtManager.queryMonitorWindowsTaskS(map, page);
				if (sr != null) {
					mwtList = sr.getList();
					StringBuffer sbf = new StringBuffer();
					//看监控对象是否可用
					for(MonitorWindowsTask mwt:mwtList){
						if(mwt.getWinTaskStatus()==1){
							mwt.setWinIsOnline(1);
						}else{
							mwt.setWinIsOnline(2);//未知状态，不显示任何信息
						}
						sbf.append(mwt.getWinId()+",");
					}
					mwtIdStr=sbf.toString();
					request.setAttribute("Page", sr.getPage());
				} else {
					request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
				}
		return SUCCESS;
	}
	//删除
	public String deleteWinTask(){
		if(winId>0){
			mwtManager.deleteMonitorWindowsTask(winId);
			List<String> fieldList = new ArrayList<String>();
			fieldList.add(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName()
					+ "("
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + ")");
			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "主机监控", super
					.getRequest().getRemoteAddr(), fieldList);
		
		}
		return SUCCESS;
	}
	//修改
	public String detailWinTask(){
		String result="";
		if(winId>0){
			monwt = mwtManager.detailMonitorWindowsTask(winId);
			mwtManager.updateMonitorWindowsTask(monwt);
				result="success";
			}
		return result;
	}
	//添加监控对象
	public String addOrUpdate(){
		List<String> fieldList = new ArrayList<String>();
		monwt.setRegTime(new Date());
		if(monwt.getWinId()>0){
			mwtManager.updateMonitorWindowsTask(monwt);
			fieldList.add(monwt.getWinName() + "("
					+ monwt.getWinName() + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "主机监控", super
					.getRequest().getRemoteAddr(), fieldList);

		}else{
			mwtManager.insertMonitorWindowsTask(monwt);
			fieldList.add(monwt.getWinName() + "("
					+ monwt.getWinName() + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "主机监控", super
					.getRequest().getRemoteAddr(), fieldList);
	}	
		return SUCCESS;
	}
	
	//测试连接
	public void testLink(){
		monwt = new MonitorWindowsTask();
		monwt.setWinIp(winIp);
		monwt.setWinPort(port);
		monwt.setCommunityName(communityName);
		monwt.setSnmpType(winType);
		try {
			boolean flag=checkOnline(monwt);
		getResponse().getWriter().write(flag+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//测试是否在线
	public  boolean checkOnline (MonitorWindowsTask mwt) {
		boolean flag =true;
		try { 
			List<TableEvent> list=connect(mwt,cpuOids);
        	flag=(list.size()==1 && list.get(0).getColumns()==null);
		} catch (Exception e) {
		//	e.printStackTrace();
			System.out.println("无法连接");
		}
        return flag;
	}
	
	public void online(){
		monwt=mwtManager.detailMonitorWindowsTask(winId);
		try{
		if(monwt.getWinTaskStatus()==1){
		if(!checkOnline(monwt)){
		getResponse().getWriter().write("1");
		}
		else{
			getResponse().getWriter().write("0");
			}
		}else{
		getResponse().getWriter().write("2");
		}
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
	
	/*
	 * 查看图表信息详情
	 * */
	public String detailWin(){
		winDetail=new WindowsDetail();
		infoDetail=new ArrayList<WindowsDetail>();
		String result="";
		if(winId>0){
				monwt = mwtManager.detailMonitorWindowsTask(winId);
				if(monwt.getWinTaskStatus()==1){				
						if(checkOnline(monwt)==false){
							//getInfo(monwt);	
						getWinMemory(monwt, memoryOids);
						winDetail.setTimeSpan(winDetail.getTime(monwt,timeOids));
						winDetail.setHostName(winDetail.getHostName(monwt,hostNameOids));
						getWinDisk(monwt, memoryOids);
						getIntface(monwt, interfaceOids);
						getRate(monwt);
						getSoft(monwt);
						getWinCpu(monwt, cpuOids);}
				}else{
				}
				result="success";
				return result;
		}
		return result;
	}
	
	/*连接是否成功，返回参数
	 * */
	public List<TableEvent> connect(MonitorWindowsTask mwt,String[] oids){
		Snmp snmp = null;
		TransportMapping transport = null;
		CommunityTarget target = null;
		//String[] oids = {"1.3.6.1.2.1.25.4.2.1.2"};
			try {
				transport = new DefaultUdpTransportMapping();
				snmp = new Snmp(transport);
				snmp.listen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			target = new CommunityTarget();
			target.setCommunity(new OctetString(mwt.getCommunityName()));
			target.setRetries(2);
			target.setAddress(GenericAddress.parse("udp:"+mwt.getWinIp()+"/"+mwt.getWinPort()));
			target.setTimeout(3000);
			if(mwt.getSnmpType()=="1"){
			target.setVersion(SnmpConstants.version1);
			}else if(mwt.getSnmpType()=="2c"){
				target.setVersion(SnmpConstants.version2c);
			}else if(mwt.getSnmpType()=="3"){
				target.setVersion(SnmpConstants.version3);
			}
			
			TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
				@Override
				public PDU createPDU(Target arg0) {
					PDU request = new PDU();
					request.setType(PDU.GET);
					return request;
				}
			});
			OID[] columns = new OID[oids.length];
			for (int i = 0; i < oids.length; i++)
				columns[i] = new OID(oids[i]);
			
			//CPU可能是多核的
			List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
			return list;
	}
	
	/*
	 * 获取cpu信息*/
	public void getWinCpu(MonitorWindowsTask mwt,String[] oids){
		List<TableEvent> list=connect(mwt, oids);
		int percentage = 0;
		String cpu="";
		String ucpu="";
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			if(values != null) {
				 percentage += getIntValue(values[0]);
			}
		}
		cpu= percentage/list.size()+"";
		ucpu=100-percentage/list.size()+"";
		winDetail.setUsedCpu("{value:"+cpu+",name:''"+"}");
		//winDetail.setUsedCpu("{name:'已使用',y:"+cpu+"}");
		//winDetail.setUnUsedCpu("{name:'未使用',y:"+ucpu+"}");
	}
	
	/*
	 *获取内存信息*/
	public void getWinMemory(MonitorWindowsTask mwt,String[] oids){
		List<TableEvent> list=connect(mwt, oids);
		String memory="";
		String unMemory="";
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			if(values!=null){
				String oid = getValue(values[0]);
				if ("1.3.6.1.2.1.25.2.1.2".equals(oid)){
					double[] temp = compute(values);
					memory+=Math.round(temp[1]*100)/100.0+"";
					unMemory+=Math.round((100.00-temp[1])*100)/100.0+"";
					
				}
			}
		}
		winDetail.setUsedMemory("{name:'已使用',y:"+memory+"}");
		winDetail.setUnUsedMemory("{name:'未使用',y:"+unMemory+"}");
		
	}
	/*
	 *获取磁盘信息*/
	public void getWinDisk(MonitorWindowsTask mwt,String[] oids){
		List<TableEvent> list=connect(mwt, oids);
		List<String> diskList = new ArrayList<String>();
		List<Double> diskUsed=new ArrayList<Double>();
		String diskName="";
		String diskuse="";
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			if(values!=null){
				String oid = getValue(values[0]);//判断是否磁盘的OID
				String oid1=getOid(values[1]);//判断磁盘名称（c,d,e）
				double[] temp = compute(values);
				if ("1.3.6.1.2.1.25.2.1.4".equals(oid)&&"1.3.6.1.2.1.25.2.3.1.3.1".equals(oid1)){
					diskList.add("C盘");
					diskUsed.add(temp[1]);
				}else if("1.3.6.1.2.1.25.2.1.4".equals(oid)&&"1.3.6.1.2.1.25.2.3.1.3.2".equals(oid1)){
					diskList.add("D盘");
					diskUsed.add(temp[1]);
				}else if("1.3.6.1.2.1.25.2.1.4".equals(oid)&&"1.3.6.1.2.1.25.2.3.1.3.3".equals(oid1)){
					diskList.add("E盘");
					diskUsed.add(temp[1]);
				}else if("1.3.6.1.2.1.25.2.1.4".equals(oid)&&"1.3.6.1.2.1.25.2.3.1.3.4".equals(oid1)){
					diskList.add("F盘");
					diskUsed.add(temp[1]);
				}else if("1.3.6.1.2.1.25.2.1.4".equals(oid)&&"1.3.6.1.2.1.25.2.3.1.3.5".equals(oid1)){
					diskList.add("G盘");
					diskUsed.add(temp[1]);
				}else if("1.3.6.1.2.1.25.2.1.4".equals(oid)&&"1.3.6.1.2.1.25.2.3.1.3.6".equals(oid1)){
					diskList.add("H盘");
					diskUsed.add(temp[1]);
				}
			}
		}
		for(int i=0;i<diskList.size();i++){
			if(0==i){//第一个
				diskName+="'"+diskList.get(i)+"'";
			}else{
				diskName+=",'"+diskList.get(i)+"'";
			}
		}
		for(int i=0;i<diskUsed.size();i++){
			if(0==i){//第一个
				diskuse+=Math.round(diskUsed.get(i)*100)/100.0;
			}else{
				diskuse+=","+Math.round(diskUsed.get(i)*100)/100.0;
			}
		}
		winDetail.setDiskName(diskName);
		winDetail.setUsedDisk(diskuse);
	}
	
	/*
	 * 流量*/
	public List<Long> getWinFlow(MonitorWindowsTask mwt,String[] oids){
		List<TableEvent> list=connect(mwt, oids);
		List<Long> recFlow=new ArrayList<Long>();
		if(list.size()>1){
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			recFlow.add(Long.parseLong(getStringValue(values[0])));
		}
		}
		return recFlow;
	}
	
	/*
	 * 发送错误与丢包数
	 * */
	public List<Long> getLoseAndError(MonitorWindowsTask mwt,String[] oids){
		List<TableEvent> list=connect(mwt, oids);
		List<Long> losErr=new ArrayList<Long>();
		if(list.size()>1){
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			losErr.add(Long.parseLong(getStringValue(values[0])));
		}
		}
		return losErr;
	}

	/*
	 * 发送错误与丢包数
	 * */
	public void getRate(MonitorWindowsTask mwt){
		List<Long> sendError=getLoseAndError(mwt, sendErrorOids);
		List<Long> sendLose=getLoseAndError(mwt, sendLoseOids);
		List<Long> sendLone=getLoseAndError(mwt, ifOutUcastPkts);
		List<Long> sendULone=getLoseAndError(mwt, ifOutNUcastPkts);
		List<Long> reciveError=getLoseAndError(mwt, arriveErrorOids);
		List<Long> reciveLone=getLoseAndError(mwt, ifInUcastPkts);
		List<Long> reciveULone=getLoseAndError(mwt, ifInNUcastPkts);
		List<Long> reciveLose=getLoseAndError(mwt, arriveLoseOids);
		List<Long> reciveFlow=getWinFlow(mwt, arriveByteOids);
		List<Long> sendFlow=getWinFlow(mwt, sendByteOids);
		List<Long> speed=getWinFlow(mwt, ifSpeed);
		String ErrorRate="";
		String LoseRate="";
		String reErrRate="";
		String reLosRate="";
		String sendFw="";
		String reciveFw="";
		
		for(int i=0;i<sendLone.size();i++){
			if(sendLone.get(i)+sendULone.get(i)==0){
				if(0==i){
					ErrorRate+=0.0;
					LoseRate+=0.0;
				}else{
					ErrorRate+=","+0.0;
					LoseRate+=","+0.0;
				}
			}else{
				if(0==i){//第一个
				ErrorRate+=sendError.get(i)/(sendLone.get(i)+sendULone.get(i));
				LoseRate+=Math.ceil(sendLose.get(i)/(sendLone.get(i)+sendULone.get(i)));
			}else{
				ErrorRate+=","+Math.ceil(sendError.get(i)/(sendLone.get(i)+sendULone.get(i)));
				LoseRate+=","+Math.ceil(sendLose.get(i)/(sendLone.get(i)+sendULone.get(i)));
			}
			}
			if(reciveLone.get(i)+reciveULone.get(i)==0){
				if(0==i){
					reErrRate+=0;
					reLosRate+=0;
				}else{
					reErrRate+=","+0;
					reLosRate+=","+0;
				}
			}else{
				if(0==i){//第一个
				reErrRate+=Math.ceil(reciveError.get(i)/(reciveLone.get(i)+reciveULone.get(i)));
				reLosRate+=Math.ceil(reciveLose.get(i)/(reciveLone.get(i)+reciveULone.get(i)));
			}else{
				reErrRate+=","+Math.ceil(reciveError.get(i)/(reciveLone.get(i)+reciveULone.get(i)));
				reLosRate+=","+Math.ceil(reciveLose.get(i)/(reciveLone.get(i)+reciveULone.get(i)));
			}
			}
			if(speed.get(i)==0){
				if(0==i){
					sendFw+=0;
					reciveFw+=0;
				}else{
					sendFw+=","+0;
					reciveFw+=","+0;
				}
			}else{
				if(0==i){//第一个
				sendFw+=Math.ceil(sendFlow.get(i)*100*8/speed.get(i)/1024);
				reciveFw+=Math.ceil(reciveFlow.get(i)*100*8/speed.get(i)/1024);
			}else{
				sendFw+=","+Math.ceil(sendFlow.get(i)*100*8/speed.get(i)/1024);
				reciveFw+=","+Math.ceil(reciveFlow.get(i)*100*8/speed.get(i)/1024);
			}
			}
		}
		winDetail.setSendError(ErrorRate);
		winDetail.setArriveError(reErrRate);
		winDetail.setSendLose(LoseRate);
		winDetail.setArriveLose(reLosRate);
		winDetail.setSendFlow(sendFw);
		winDetail.setArriveFlow(reciveFw);
		
	}
	
	/*
	 * 获取系统运行时间
	 * */
	public void getRunTime(){
		
	}
	//获取软件 服务 进程信息
	public void getSoft(MonitorWindowsTask mwt){
		List<TableEvent> listSoft=connect(mwt, softOids);
		List<TableEvent> listService=connect(mwt, serviceOids);
		List<TableEvent> listProcess=connect(mwt, processOids);
		List<String> soft=new ArrayList<String>();
		List<String> service=new ArrayList<String>();
		List<String> process=new ArrayList<String>();
		int maxSize=Math.max(Math.max(listProcess.size(), listService.size()), listSoft.size());
		if(listSoft.size()>1){
		for(TableEvent event: listSoft){
			VariableBinding[] values = event.getColumns();
			soft.add(getChinese(getStringValue(values[0])));
		}
		}
		if(listService.size()>1){
		for(TableEvent event: listService){
			VariableBinding[] values = event.getColumns();
			service.add(getChineseUtf(getStringValue(values[0])));
		}}
		if(listProcess.size()>1){
		for(TableEvent event: listProcess){
			VariableBinding[] values = event.getColumns();
			process.add(getChinese(getStringValue(values[0])));
		}}
		
		for(int i=0;i<maxSize;i++){
			WindowsDetail wd=new WindowsDetail();
			if(process.size()<=i){
				wd.setAllProcess("..");
			}else{
				wd.setAllProcess(process.get(i));
			}
			if(service.size()<=i){
				wd.setAllServices("..");
			}else{
				wd.setAllServices(service.get(i));
			}
			if(soft.size()<=i){
				wd.setAllSofts("..");
			}else{
			wd.setAllSofts(soft.get(i));
			}
			infoDetail.add(wd);
			//System.out.println(infoDetail.get(i).getAllProcess());
			} 
	}
	/*public void getService(MonitorWindowsTask mwt){
		List<TableEvent> list=connect(mwt, serviceOids);
		List<String> info=new ArrayList<String>();
		infoDetail=new ArrayList<WindowsDetail>();
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			
			getChinese(getStringValue(values[0]));
		}
	}
	public void getProcess(MonitorWindowsTask mwt){
		List<TableEvent> list=connect(mwt, processOids);
		List<String> info=new ArrayList<String>();
		infoDetail=new ArrayList<WindowsDetail>();
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			
			getChinese(getStringValue(values[0]));
		}
	}
	*/
	//所有网络接口
	public void getIntface(MonitorWindowsTask mwt,String[] oids){
		List<TableEvent> list=connect(mwt, oids);
		List<String> intfList=new ArrayList<String>();
		String inter= "";
		if(list.size()>1){
		for(TableEvent event: list){
			VariableBinding[] values = event.getColumns();
			/*if(getChinese(getStringValue(values[0])).length()>20){
			intfList.add(getChinese(getStringValue(values[0])).substring(0, 20));
			}else{
				intfList.add(getChinese(getStringValue(values[0])));
			}*/
			if(values.length!=0){
			intfList.add(getChinese(getStringValue(values[0])).substring(0,getChinese(getStringValue(values[0])).length()-1));
			}else{
				intfList.add("");
			}
		}
		for(int i=0;i<intfList.size();i++){
			if(0==i){//第一个
				inter+="'"+intfList.get(i)+"'";
			}else{
				inter+=",'"+intfList.get(i)+"'";
			}
		}
		winDetail.setInterfaceName(inter);
		}else{}
	}
	//检查是否重名
	public void checkName(){
		String tmp ="";
		if(StringUtil.isNotEmpty(winName)){
			tmp = mwtManager.checkWinTaskName(winName);
		}
		if(tmp==null){
			tmp="";
		}
		try {
			getResponse().getWriter().write(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//启动/停止
	@SuppressWarnings({ })
	public void stopORstart(){
		Map<String, Number> map = new HashMap<String, Number>();
		map.put("winTaskStatus", stopORstart);
		map.put("winId", winId);
		mwtManager.stopMonitorWindowsTask(map);
	}

	public static String getValue(VariableBinding vb){
		if(vb == null || vb.getVariable() == null)
			return null;		
		return getRidQuote(vb.getVariable().toString());
	}
	public static String getOid(VariableBinding vb){
		if(vb == null || vb.getOid() == null)
			return null;		
		return vb.getOid().toString();
	}

	/**
	 *对VariableBinding 及 OcetString类型数据进行处理
	 */
	public static String getChinese(String octetString){
        try{
        	if(!octetString.contains(":")){
        		return octetString;
        	}
            String[] temps = octetString.split(":");
            byte[] bs = new byte[temps.length];
            for(int i=0;i<temps.length;i++)
                bs[i] = (byte)Integer.parseInt(temps[i],16);
        
            return new String(bs,"GB2312");
        }catch(Exception e){
            return null;
        }
    }
	
	public static String getChineseUtf(String octetString){
        try{
        	if(!octetString.contains(":")){
        		return octetString;
        	}
            String[] temps = octetString.split(":");
            byte[] bs = new byte[temps.length];
            for(int i=0;i<temps.length;i++)
                bs[i] = (byte)Integer.parseInt(temps[i],16);
        
            return new String(bs,"utf-8");
        }catch(Exception e){
            return null;
        }
    }
	public static String getStringValue(VariableBinding vb){
		if(vb == null || vb.getVariable().toString() instanceof String == false)
			return "";
		return vb.getVariable().toString();
	}
	public static String getRidQuote(String value){
		if(value != null && value.indexOf("'") > 0)
			return value.replace("'","_");
		return value;		
	}
	
	public static int getIntValue(VariableBinding vb){
		if(vb == null || vb.getVariable() instanceof Integer32 == false)
			return 0;
		return ((Integer32)vb.getVariable()).getValue();
	}
	
	private static double[] compute(VariableBinding[] values){
		int unit = getIntValue(values[2]);
		int totalSize = getIntValue(values[3]);
		int usedSize = getIntValue(values[4]);		
		double value = formatFloat((long)usedSize * unit, 1024 * 1024);
		double percentage = formatFloat((long)usedSize * 100, totalSize);				

		return new double[]{value,percentage};
    }
	
	public static final DecimalFormat floatFormatter = new DecimalFormat("#.00");
	
	public static float formatFloat(double val1, double val2) {
		return val2 == 0 ? 0 : Float.parseFloat(floatFormatter.format(val1 / val2));
	}
	
	
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	public MonitorWindowsTaskService getMwtManager() {
		return mwtManager;
	}
	public void setMwtManager(MonitorWindowsTaskService mwtManager) {
		this.mwtManager = mwtManager;
	}
	public List<MonitorWindowsTask> getMwtList() {
		return mwtList;
	}
	public void setMwtList(List<MonitorWindowsTask> mwtList) {
		this.mwtList = mwtList;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getWinIp() {
		return winIp;
	}
	public void setWinIp(String winIp) {
		this.winIp = winIp;
	}
	public String getWinName() {
		return winName;
	}
	public void setWinName(String winName) {
		this.winName = winName;
	}
	public long getWinId() {
		return winId;
	}
	public void setWinId(long winId) {
		this.winId = winId;
	}
	public String getWinType() {
		return winType;
	}
	public void setWinType(String winType) {
		this.winType = winType;
	}
	public MonitorWindowsTask getMonwt() {
		return monwt;
	}
	public void setMonwt(MonitorWindowsTask monwt) {
		this.monwt = monwt;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getStopORstart() {
		return stopORstart;
	}
	public void setStopORstart(int stopORstart) {
		this.stopORstart = stopORstart;
	}
	public String getMwtIdStr() {
		return mwtIdStr;
	}
	public void setMwtIdStr(String mwtIdStr) {
		this.mwtIdStr = mwtIdStr;
	}
	public WindowsDetail getWinDetail() {
		return winDetail;
	}
	public void setWinDetail(WindowsDetail winDetail) {
		this.winDetail = winDetail;
	}

	public List<WindowsDetail> getInfoDetail() {
		return infoDetail;
	}
	public void setInfoDetail(List<WindowsDetail> infoDetail) {
		this.infoDetail = infoDetail;
	}
}
