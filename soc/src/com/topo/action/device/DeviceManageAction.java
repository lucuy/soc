package com.topo.action.device;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.rank.Rank;
import com.opensymphony.xwork2.ModelDriven;
import com.soc.model.user.User;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.asset.AssetService;
import com.topo.action.BaseAction;
import com.topo.model.device.Device;
import com.topo.model.device.NetBackGround;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.service.device.DeviceManageService;
import com.topo.service.deviceCategory.DeviceCategoryManageService;
import com.topo.util.CpuSnmpUtil;
import com.topo.util.DiskSnmpUtil;
import com.topo.util.MacSnmpUtil;
import com.topo.util.MemorySnmpUtil;
import com.topo.util.Ping;
import com.topo.util.ProcessSnmpUtil;
import com.topo.util.WebUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;



public class DeviceManageAction extends BaseAction implements ModelDriven<Device>{

	private static final long serialVersionUID = -4146818016498898294L;
	private String did;
	private Device device=new Device();
	//WriteXml writeXml = new WriteXml();
	
	
	private List<Device> deviceList=new ArrayList<Device>();
	
	private DeviceManageService deviceManageService;
	// 告警信息管理类
	private AlertMessageSerive alertMessageManager;
	private String keyword;
	private List<DeviceCategory> deviceCategoryList;
	private DeviceCategoryManageService deviceCategoryService;
	long deviceMarkID;
	String deviceCategoryName;
	//添加关联设备是用到的
	private List<Device> associationDeviceList;
	//用来保存用户选择的关联设备
	private List<Device> saveDeviceList;
	//高级查询条件-设备名称
	private String selectDeviceName;
	//高级查询条件-设备类型
	private String selectDeviceCategory;
	//高级查询条件-设备Ip
	private String selectDeviceIp;
	//高级查询条件-设备Mac
	private String selectDeviceMac;
	//高级查询条件-设备状态
	private String selectDeviceStatus;
	//高级查询条件-设备位置
	private String selectDeviceLocation;
	private AssetService assetManager;//资产管理类
	
	//snmp信息采集
	
	private String deviceIp;
	private String returnMsg="";
	private String memoryUsed="";
	private String memoryTotal="";
	private String hardDisk="";
	private String courseSum="";
	
	
	//查看所有的设备信息
	public String queryAllDevice(){
		HttpServletRequest request=super.getRequest();
		Page page=null;
		SearchResult sr=null;
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		keyword=request.getParameter("keyword");
		try {
		//	deviceCategoryList=deviceCategoryService.queryAllDeviceCategory();
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
		//--------------开始（不加的话，高级条件查询完后，点击搜索按钮还是根据高级条件查询的）--------------
		selectDeviceName=request.getParameter("selectDeviceName");
		selectDeviceCategory=request.getParameter("selectDeviceCategory");
		selectDeviceIp=request.getParameter("selectDeviceIp");
		selectDeviceMac=request.getParameter("selectDeviceMac");
		selectDeviceStatus=request.getParameter("selectDeviceStatus");
		selectDeviceLocation=request.getParameter("selectDeviceLocation");
		//-----------------结束--------
		if(StringUtil.isNotBlank(selectDeviceName)){
			map.put("deviceName", selectDeviceName);
		}
		if(StringUtil.isNotBlank(selectDeviceCategory)){
			map.put("deviceCategoryID",Long.parseLong(selectDeviceCategory));
		}
		if(StringUtil.isNotBlank(selectDeviceIp)){
			map.put("deviceIp", selectDeviceIp);
		}
		if(StringUtil.isNotBlank(selectDeviceMac)){
			map.put("deviceMac", selectDeviceMac);
		}
		if(StringUtil.isNotBlank(selectDeviceStatus)){
			map.put("deviceStatus", Integer.parseInt(selectDeviceStatus));
		}
		if(StringUtil.isNotBlank(selectDeviceLocation)){
			map.put("deviceMark", Integer.parseInt(selectDeviceLocation));
		}
		//根据用户关联的资产组去查询资产
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
 		String assetId=assetManager.queryIDSByUser(groupid);
    if(groupid==1){
	}else{
		map.put("alertAssetId", assetId);
	}
		
		try {
			sr = deviceManageService.query(map, page);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (sr != null) {
			deviceList = (List<Device>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}
	
	
	
	
	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public void setDeviceManageService(DeviceManageService deviceManageService) {
		this.deviceManageService = deviceManageService;
	}
	
	/**
	 * 添加(添加的时候，不执行该方法中的内容，直接跳转；
	 * 但是修改的时候，要执行该方法中的内容)
	 * @return
	 */
	public  String addDevice() {
		HttpServletRequest request=super.getRequest();
		try {
			deviceCategoryList=deviceCategoryService.queryAllDeviceCategory();
			//associationDeviceList=deviceManageService.queryAllDevice();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String strId=request.getParameter("id");
		if(StringUtil.isNotBlank(strId)){
			long id=Long.parseLong(strId);
			try {
				this.device=deviceManageService.queryDeviceById(id);
				deviceCategoryName=this.device.getDeviceCategory().getDeviceCategory_name();
				//deviceCategoryName=this.device.getDeviceCategory().getDeviceCategory_name();
				deviceMarkID=this.device.getDevice_mark();
				if(StringUtil.isNotBlank(device.getDevice_association_id())){
					saveDeviceList=new ArrayList<Device>();
					String[] strIds=device.getDevice_association_id().split(",");
					if(strIds.length!=0){
						for(String strDeviceId:strIds){
							this.saveDeviceList.add(deviceManageService.queryDeviceById(Long.parseLong(strDeviceId)));
						}
					}
				}else{
					saveDeviceList=new ArrayList<Device>();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			device=new Device();
			deviceCategoryName="";
			deviceMarkID=0;
			saveDeviceList=new ArrayList<Device>();
		}
		return SUCCESS;
	}
	
	/**
	 * 选择设备的位置加载不同的关联设备
	 */
	public void queryAssociationDevices(){
		HttpServletRequest request=super.getRequest();
		String deviceMark=super.getRequest().getParameter("deviceMark");
		StringBuffer sb=new StringBuffer();
		//根据用户关联的资产组去查询资产
		Map map =new HashMap();
				long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		 		String assetId=assetManager.queryIDSByUser(groupid);
		    if(groupid==1){
			}else{
				map.put("alertAssetId", assetId);
			}
		if("0".equals(deviceMark)){
			try {
				this.associationDeviceList=deviceManageService.queryAllDevice(map);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			this.associationDeviceList=deviceManageService.queryAllOutDevice();
		}
		JSONArray jsonArray = JSONArray.fromObject(associationDeviceList);
		try {
			getResponse().getWriter().write(
					"{\"resultByAjax\":" + jsonArray.toString() + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*sb.append("<table id=\"dlg-table-asset\" width='96%' cellspacing='1' cellpadding='0' border='0' align='center' class=\"tab2\" style=\"overflow:auto\">");
		sb.append("<tr height=\"28\">");
		sb.append("<td width=\"6%\" align=\"center\" class=\"biaoti\">" +
				"<input type=\"checkbox\" id=\"asset-checkAll\" name=\"chkAll-user\" class=\"check-box-deviceName not-checked-deviceName\" />" +
				"</td>");
		sb.append("<td width=\"100%\" align=\"center\" class=\"biaoti\">关联设备</td>");
		sb.append("</tr>");
		sb.append("<s:iterator value=\""+associationDeviceList+"\">");
		sb.append("<tr style=\"line-height: 25px;\">");
		sb.append("<td width=\"20%\" align=\"center\">" +
				"<input type=\"checkbox\" name=\"chk-asset\" id=\"chk-${device_id}\" value=\"${device_id}\" />" +
				"</td>");
		sb.append("<td width=\"300px\">" +
				"<a style='color: #555555' onclick=\"checkLeftBox('chk-${device_id}')\">${device_name}</a>" +
				"</td>");
		sb.append("</tr>");
		sb.append("</s:iterator>");
		sb.append("</table>");
		sb.append("<table width=\"80%\" align=\"right\">");
		sb.append("<tr><td><div id=\"barcon\" name=\"barcon\"></div>");
		sb.append("</td></tr></table>");
		System.out.println(sb.toString());
		JSONArray jsonArray = JSONArray.fromObject(associationDeviceList);
		try {
			getResponse().getWriter().write(
					"{\"resultByAjax\":" + sb.toString() + "}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return ;
	}
	
	/**
	 * 将拓扑图写入xml
	 * @author 王帅
	 */
	/*public void writeXml(){
		List<Device> deviceList = null;
		try {
			deviceList = deviceManageService.queryAllDevice();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//return "error";
		}
		try {
			writeXml.writeTopoXml(deviceList);
			//return SUCCESS;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			//return "error";
		}
	}*/
	
	
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String updateDevice() throws Exception {
		HttpServletRequest request=super.getRequest();
		//String strDeviceId=request.getParameter("id");
		String strDeviceCategoryName=request.getParameter("deviceCategoryName");
		String deviceName=request.getParameter("deviceName");
		String deviceIp=request.getParameter("deviceIp");
		String deviceMac=request.getParameter("deviceMac");
		String devDescription=request.getParameter("devDescription");
		String deviceStatus=request.getParameter("deviceStatus");
		String deviceAssociation=request.getParameter("deviceIds");
		String deviceMarkid=request.getParameter("deviceMarkId");
		if(StringUtil.isNotBlank(deviceAssociation)){
			String deviceIds=deviceAssociation.substring(0, deviceAssociation.length()-1);
			device.setDevice_association_id(deviceIds);
		}
		device.setDevice_name(deviceName);
		device.setDevice_ip(deviceIp);
		device.setDevice_mac(deviceMac);
		device.setDevice_describe(devDescription);
		//device.setDevice_deviceCategory_name(strDeviceCategoryName);
		DeviceCategory deviceCategory=new DeviceCategory();
		deviceCategory.setDeviceCategory_name(strDeviceCategoryName);
		device.setDeviceCategory(deviceCategory);
		
		if(StringUtil.isNotBlank(deviceStatus)){
			device.setDevice_status(Integer.parseInt(deviceStatus));
		}
		if(StringUtil.isNotBlank(deviceMarkid)){
			device.setDevice_mark(Long.parseLong(deviceMarkid));
		}
		long id=this.device.getDevice_id();
		if(id!=0){
			deviceManageService.editDevice(this.device);
		}else{
			device.setDevice_locationX(String.valueOf((int)Math.random()*100 + 1));
			device.setDevice_locationY(String.valueOf((int)Math.random()*100 + 1));
			deviceManageService.addDevice(device);
		}
		//writeXml();
		return SUCCESS;
	}
	/**
	 * 删除设备信息
	 */
	public String deleteDevice(){
		HttpServletRequest request=super.getRequest();
		String ids=request.getParameter("ids");
		String[] strs=ids.split(",");
        int [] num=new int[strs.length];
        for(int i=0;i<num.length;i++){
        	if(StringUtil.isNotBlank(strs[i])){
        		num[i]=Integer.parseInt(strs[i]);
        	}
        }
		try {
			deviceManageService.delDevice(num);
			//writeXml();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断设备是否有关联设备，有则不允许删除
	 */
	
	public void queryAssociationDevice(){
		String[] strDeviceIds = super.getRequest().getParameter("deviceIds")
				.split(",");
		 long [] num=new long[strDeviceIds.length];
	        for(int i=0;i<num.length;i++){
	            num[i]=Integer.parseInt(strDeviceIds[i]);
	        }
		List<Device> exitList=new ArrayList<Device>();
		for (long deviceId : num) {
			List<Device> dList=new ArrayList<Device>();
			try {
				dList=deviceManageService.queryAssociationDeviceByID(deviceId);
				if(!dList.isEmpty()){
					Device d=deviceManageService.queryDeviceById(deviceId);
					exitList.add(d);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONArray jsonArray = JSONArray.fromObject(exitList);
		try {
			getResponse().getWriter().write(
					"{\"resultByAjax\":" + jsonArray.toString() + "}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	/**
	 * 检测设备的状态
	 */
	public void queryDeviceStatus(){
		HttpServletRequest request=super.getRequest();
		String strIp=request.getParameter("deviceIp");
		Ping ping=new Ping(strIp);
		String result=ping.pingIP();
		if(StringUtil.isNotBlank(result)){
			try {
				getResponse().getWriter().write("{\"resultByAjax\":1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				getResponse().getWriter().write("{\"resultByAjax\":-1}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void loadBirdeyeData() throws Exception {
		HttpServletRequest request=super.getRequest();
		HttpServletResponse response=super.getResponse();
		Map map =new HashMap();
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
 		String assetId=assetManager.queryIDSByUser(groupid);
    if(groupid==1){
	}else{
		map.put("alertAssetId", assetId);
	}
		List<Device> deviceList=deviceManageService.queryAllDevice(map);
		StringBuffer s = new StringBuffer();
		StringBuffer edgeStringBuffer = new StringBuffer();
		//创建XML文件所需的各种对象并序列化
		s.append("<Graph>");
		   for(int i=0;i<deviceList.size();i++){
			   if(i==0){
				   
				  
				   s.append("<Node id=\"" + String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
				   s.append("ip=\"" + deviceList.get(i).getDevice_ip()+ "\" ");
				   s.append("mac=\"" + deviceList.get(i).getDevice_mac()+ "\" ");
				   s.append("assetId=\"" + deviceList.get(i).getDevice_asset_id()+ "\" ");
				   s.append("loginName=\"" + deviceList.get(i).getDevice_loginName()+ "\" ");
				   s.append("displayName=\"" + deviceList.get(i).getDevice_name()+ "\" ");
				   s.append("snmpCommunityName=\"" + deviceList.get(i).getDevice_community_name()+ "\" ");
				   s.append("memo=\"" + deviceList.get(i).getDevice_describe()+ "\" ");
				   s.append("scaling=\"" +"0.9"+ "\" ");
				   s.append("type=\""+"business"+ "\" ");
				   s.append("level=\""+"business"+ "\" ");
				   //level和type的值相同
				   /*s.append("positionX=\"" +"646.0"+ "\" ");
				   s.append("positionY=\"" +"39.75"+ "\" ");*/
				   s.append("positionX=\"" +deviceList.get(i).getDevice_locationX()+ "\" ");
				   s.append("positionY=\"" +deviceList.get(i).getDevice_locationY()+ "\" ");
				   s.append("severity=\"" +String.valueOf(deviceList.get(i).getDevice_status())+ "\" ");
				   //s.append("icon=\"" +deviceCategoryService.queryDeviceCategoryByName(deviceList.get(i).getDevice_deviceCategory_name()).getDeviceCategory_photo()+ "\" ");
				   s.append("icon=\"" +deviceList.get(i).getDeviceCategory().getDeviceCategory_photo()+ "\" ");
				   
				   s.append(" />");
				   //如果设备有关联ID，则写入线
				   String assoIds=deviceList.get(i).getDevice_association_id();
				   if(assoIds!=null){
					   String[] strIds=assoIds.split(",");
					   if(strIds.length==1){
						   if(StringUtil.isNotBlank(strIds[0])){
							   edgeStringBuffer.append("<Edge ");
							   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
							   edgeStringBuffer.append("toID=\"" + strIds[0]+ "\" ");
							   edgeStringBuffer.append(" />");
						   }
					   }else{
						   for(String assoId:strIds){
							   if(assoId!=null){
								   edgeStringBuffer.append("<Edge ");
								   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
								   edgeStringBuffer.append("toID=\"" + assoId+ "\" ");
								   edgeStringBuffer.append(" />");
							   } 
						   }
					   }
					   s.append(edgeStringBuffer);
				   }
			   }else{
				   s.append("<Node id=\"" + String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
				   s.append("ip=\"" + deviceList.get(i).getDevice_ip()+ "\" ");
				   s.append("mac=\"" + deviceList.get(i).getDevice_mac()+ "\" ");
				   s.append("assetId=\"" + deviceList.get(i).getDevice_asset_id()+ "\" ");
				   s.append("loginName=\"" + deviceList.get(i).getDevice_loginName()+ "\" ");
				   s.append("displayName=\"" + deviceList.get(i).getDevice_name()+ "\" ");
				   s.append("snmpCommunityName=\"" + deviceList.get(i).getDevice_community_name()+ "\" ");
				   s.append("memo=\"" + deviceList.get(i).getDevice_describe()+ "\" ");
				   s.append("scaling=\"" +"null"+ "\" ");
				   s.append("type=\""+deviceList.get(i).getDeviceCategory().getDeviceCategory_name()+ "\" ");
				   s.append("level=\""+deviceList.get(i).getDeviceCategory().getDeviceCategory_name()+ "\" ");
				   //level和type的值相同
				   s.append("positionX=\"" +deviceList.get(i).getDevice_locationX()+ "\" ");
				   s.append("positionY=\"" +deviceList.get(i).getDevice_locationY()+ "\" ");
				   s.append("severity=\"" +String.valueOf(deviceList.get(i).getDevice_status())+ "\" ");
				   s.append("icon=\"" +deviceList.get(i).getDeviceCategory().getDeviceCategory_photo()+ "\" ");
				   s.append(" />");
				   //如果设备有关联ID，则写入线
				   String assoIds=deviceList.get(i).getDevice_association_id();
				   if(assoIds!=null){
					   String[] strIds=assoIds.split(",");
					   if(strIds.length==1){
						   if(StringUtil.isNotBlank(strIds[0])){
							   edgeStringBuffer.append("<Edge ");
							   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
							   edgeStringBuffer.append("toID=\"" + strIds[0]+ "\" ");
							   edgeStringBuffer.append(" />");
						   }
					   }else{
						   for(String assoId:strIds){
							   if(assoId!=null){
								   edgeStringBuffer.append("<Edge ");
								   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
								   edgeStringBuffer.append("toID=\"" + assoId+ "\" ");
								   edgeStringBuffer.append(" />");
							   } 
						   }
					   }
					   s.append(edgeStringBuffer);
				   }
			   }
		   }
		   s.append("</Graph>");
			outPutXML(request,response,s);
		}
	
	//soc间拓扑
	public void loadOutData()throws Exception{
		HttpServletRequest request=super.getRequest();
		HttpServletResponse response=super.getResponse();
		List<Device> deviceList=deviceManageService.queryAllOutDevice();
		StringBuffer s = new StringBuffer();
		StringBuffer edgeStringBuffer = new StringBuffer();
		//创建XML文件所需的各种对象并序列化
		s.append("<Graph>");
		   for(int i=0;i<deviceList.size();i++){
			   if(i==0){
				   
				  
				   s.append("<Node id=\"" + String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
				   s.append("ip=\"" + deviceList.get(i).getDevice_ip()+ "\" ");
				   s.append("mac=\"" + deviceList.get(i).getDevice_mac()+ "\" ");
				   s.append("assetId=\"" + deviceList.get(i).getDevice_asset_id()+ "\" ");
				   s.append("loginName=\"" + deviceList.get(i).getDevice_loginName()+ "\" ");
				   s.append("displayName=\"" + deviceList.get(i).getDevice_name()+ "\" ");
				   s.append("snmpCommunityName=\"" + deviceList.get(i).getDevice_community_name()+ "\" ");
				   s.append("memo=\"" + deviceList.get(i).getDevice_describe()+ "\" ");
				   s.append("scaling=\"" +"0.9"+ "\" ");
				   s.append("type=\""+"business"+ "\" ");
				   s.append("level=\""+"business"+ "\" ");
				   //level和type的值相同
				  /* s.append("positionX=\"" +"646.0"+ "\" ");
				   s.append("positionY=\"" +"39.75"+ "\" ");*/
				   s.append("positionX=\"" +deviceList.get(i).getDevice_locationX()+ "\" ");
				   s.append("positionY=\"" +deviceList.get(i).getDevice_locationY()+ "\" ");
				   s.append("severity=\"" +String.valueOf(deviceList.get(i).getDevice_status())+ "\" ");
				   s.append("icon=\"" +deviceList.get(i).getDeviceCategory().getDeviceCategory_photo()+ "\" ");
				   s.append("alertCount=\"" +String.valueOf(deviceList.get(i).getAlertAcount()==null?0:deviceList.get(i).getAlertAcount())+ "\" ");
				   s.append(" />");
				   //如果设备有关联ID，则写入线
				   String assoIds=deviceList.get(i).getDevice_association_id();
				   if(assoIds!=null){
					   String[] strIds=assoIds.split(",");
					   if(strIds.length==1){
						   if(StringUtil.isNotBlank(strIds[0])){
							   edgeStringBuffer.append("<Edge ");
							   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
							   edgeStringBuffer.append("toID=\"" + strIds[0]+ "\" ");
							   edgeStringBuffer.append(" />");
						   }
					   }else{
						   for(String assoId:strIds){
							   if(assoId!=null){
								   edgeStringBuffer.append("<Edge ");
								   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
								   edgeStringBuffer.append("toID=\"" + assoId+ "\" ");
								   edgeStringBuffer.append(" />");
							   } 
						   }
					   }
					   s.append(edgeStringBuffer);
				   }
			   }else{
				   s.append("<Node id=\"" + String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
				   s.append("ip=\"" + deviceList.get(i).getDevice_ip()+ "\" ");
				   s.append("mac=\"" + deviceList.get(i).getDevice_mac()+ "\" ");
				   s.append("assetId=\"" + deviceList.get(i).getDevice_asset_id()+ "\" ");
				   s.append("loginName=\"" + deviceList.get(i).getDevice_loginName()+ "\" ");
				   s.append("displayName=\"" + deviceList.get(i).getDevice_name()+ "\" ");
				   s.append("snmpCommunityName=\"" + deviceList.get(i).getDevice_community_name()+ "\" ");
				   s.append("memo=\"" + deviceList.get(i).getDevice_describe()+ "\" ");
				   s.append("scaling=\"" +"null"+ "\" ");
				   s.append("type=\""+deviceList.get(i).getDeviceCategory().getDeviceCategory_name()+ "\" ");
				   s.append("level=\""+deviceList.get(i).getDeviceCategory().getDeviceCategory_name()+ "\" ");
				   //level和type的值相同
				   s.append("positionX=\"" +deviceList.get(i).getDevice_locationX()+ "\" ");
				   s.append("positionY=\"" +deviceList.get(i).getDevice_locationY()+ "\" ");
				   s.append("severity=\"" +String.valueOf(deviceList.get(i).getDevice_status())+ "\" ");
				   s.append("icon=\"" +deviceList.get(i).getDeviceCategory().getDeviceCategory_photo()+ "\" ");
				   s.append("alertCount=\"" +String.valueOf(deviceList.get(i).getAlertAcount()==null?0:deviceList.get(i).getAlertAcount())+ "\" ");
				   s.append(" />");
				   //如果设备有关联ID，则写入线
				   String assoIds=deviceList.get(i).getDevice_association_id();
				   if(assoIds!=null){
					   String[] strIds=assoIds.split(",");
					   if(strIds.length==1){
						   if(StringUtil.isNotBlank(strIds[0])){
							   edgeStringBuffer.append("<Edge ");
							   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
							   edgeStringBuffer.append("toID=\"" + strIds[0]+ "\" ");
							   edgeStringBuffer.append(" />");
						   }
					   }else{
						   for(String assoId:strIds){
							   if(assoId!=null){
								   edgeStringBuffer.append("<Edge ");
								   edgeStringBuffer.append("fromID=\"" +String.valueOf(deviceList.get(i).getDevice_id())+ "\" ");
								   edgeStringBuffer.append("toID=\"" + assoId+ "\" ");
								   edgeStringBuffer.append(" />");
							   } 
						   }
					   }
					   s.append(edgeStringBuffer);
				   }
				  
			   }
		   }
		   s.append("</Graph>");
			outPutXML(request,response,s);
		
	}
	public void outPutXML(HttpServletRequest request,
			HttpServletResponse response,StringBuffer xmlStr) throws IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/xml; charset=utf-8");
		
		String header="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		
		PrintWriter out = response.getWriter();
		xmlStr.insert(0, header);
		System.out.println("=======--"+xmlStr);
		out.print(xmlStr.toString());
		out.flush();
	}
	
	
	///添加背景Panle
		public void saveBackGround() throws IOException {
			HttpServletRequest request=super.getRequest();
			
			String panelType = request.getParameter("panelType");
			String title = request.getParameter("title");
			String color = request.getParameter("color");
			
			
			if( panelType != null && !"".equals(panelType) && 
					title != null && !"".equals(title) && color != null && !"".equals(color) ){
				
				title = URLDecoder.decode(title,"UTF-8");
				
				NetBackGround netBackGround = new NetBackGround();
				netBackGround.setTreeId(1);
				netBackGround.setTitle(title);
				netBackGround.setColor(WebUtil.disposeChineseParam(color));
				netBackGround.setHeight(200f);
				netBackGround.setWidth(200f);
				netBackGround.setPanelType(Integer.parseInt(panelType));
				netBackGround.setPositionX(100f);
				netBackGround.setPositionY(100f);
				netBackGround.setType(0);
				deviceManageService.saveBackGround(netBackGround);
			}
		}
		
		
		
		/**
		 * 加载网络大屏图层信息
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		public void loadRoundrectInfo() throws IOException {
			
			HttpServletRequest request=super.getRequest();
			HttpServletResponse response=super.getResponse();
			
			
			request.setCharacterEncoding("utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("text/xml; charset=utf-8");

			StringBuffer s = new StringBuffer();
			PrintWriter out = response.getWriter();
			s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

//			String isNetworkString = request.getParameter("isNetwork");
//			int isNetwork;
//			try {
//				isNetwork = Integer.parseInt(isNetworkString);
//			} catch (Exception e) {
//				isNetwork = 0;
//				System.out.println("整数转化错误：" + isNetworkString);
//			}

			List<NetBackGround> deviceRoundrectList = deviceManageService.loadRoundrectInfo();
					
			s.append("<deviceList>");
			for (NetBackGround deviceRoundrect : deviceRoundrectList) {
				s.append("<device id=\"" + deviceRoundrect.getId() + "\"");
				s.append(" pointX=\"" + deviceRoundrect.getPositionX() + "\"");
				s.append(" pointY=\"" + deviceRoundrect.getPositionY() + "\"");
				s.append(" width=\"" + deviceRoundrect.getWidth() + "\"");
				s.append(" height=\"" + deviceRoundrect.getHeight() + "\"");
				s.append(" title=\"" + deviceRoundrect.getTitle() + "\"");
				s.append(" panelType=\"" + deviceRoundrect.getPanelType() + "\"");
				s.append(" bgcolor=\"" + deviceRoundrect.getColor() + "\"");
				s.append("/>");
			}
			s.append("</deviceList>");

			out.print(s.toString());
			out.flush();
		}
		
		
		/**
		 * 更新网络图层信息
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		public void updateRoundrectInfoById() throws IOException {
			
			HttpServletRequest request=super.getRequest();
			String id = request.getParameter("id");
			String positionX = request.getParameter("positionX");
			String positionY = request.getParameter("positionY");
			String width = request.getParameter("width");
			String height = request.getParameter("height");
			
			Map map=new HashMap();
			
			map.put("id", Integer.parseInt(id));
			map.put("positionX", Float.parseFloat(positionX));
			map.put("positionY", Float.parseFloat(positionY));
			map.put("width", Float.parseFloat(width));
			map.put("height", Float.parseFloat(height));
			
			deviceManageService.updateRoundrectInfoById(map);
		}
		
		
		
		/**
		 * 删除网络图层信息
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		public void delBackGroundById() throws IOException {
			
			HttpServletRequest request=super.getRequest();
			String idV = request.getParameter("id");
			int id=Integer.parseInt(idV);		
			deviceManageService.delBackGroundById(id);
		}
	
	//内网开始
		///添加背景Panle
		public void saveBackGroundBM() throws IOException {
					HttpServletRequest request=super.getRequest();
					
					String panelType = request.getParameter("panelType");
					String title = request.getParameter("title");
					String color = request.getParameter("color");
					
					
					if( panelType != null && !"".equals(panelType) && 
							title != null && !"".equals(title) && color != null && !"".equals(color) ){
						
						title = URLDecoder.decode(title,"UTF-8");
						
						NetBackGround netBackGround = new NetBackGround();
						netBackGround.setTreeId(1);
						netBackGround.setTitle(title);
						netBackGround.setColor(WebUtil.disposeChineseParam(color));
						netBackGround.setHeight(200f);
						netBackGround.setWidth(200f);
						netBackGround.setPanelType(Integer.parseInt(panelType));
						netBackGround.setPositionX(0f);
						netBackGround.setPositionY(0f);
						netBackGround.setType(1);
						deviceManageService.saveBackGround(netBackGround);
					}
				}
				
				
				
				/**
				 * 加载网络大屏图层信息
				 * @param request
				 * @param response
				 * @throws IOException
				 */
		public void loadRoundrectInfoBM() throws IOException {
					
					HttpServletRequest request=super.getRequest();
					HttpServletResponse response=super.getResponse();
					
					
					request.setCharacterEncoding("utf-8");
					response.setHeader("Pragma", "No-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);
					response.setContentType("text/xml; charset=utf-8");

					StringBuffer s = new StringBuffer();
					PrintWriter out = response.getWriter();
					s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					List<NetBackGround> deviceRoundrectList = deviceManageService.loadRoundrectInfoBM();
							
					s.append("<deviceList>");
					for (NetBackGround deviceRoundrect : deviceRoundrectList) {
						s.append("<device id=\"" + deviceRoundrect.getId() + "\"");
						s.append(" pointX=\"" + deviceRoundrect.getPositionX() + "\"");
						s.append(" pointY=\"" + deviceRoundrect.getPositionY() + "\"");
						s.append(" width=\"" + deviceRoundrect.getWidth() + "\"");
						s.append(" height=\"" + deviceRoundrect.getHeight() + "\"");
						s.append(" title=\"" + deviceRoundrect.getTitle() + "\"");
						s.append(" panelType=\"" + deviceRoundrect.getPanelType() + "\"");
						s.append(" bgcolor=\"" + deviceRoundrect.getColor() + "\"");
						s.append("/>");
					}
					s.append("</deviceList>");

					out.print(s.toString());
					out.flush();
				}
	//释放图片，更新设备的位置
	public void editDeviceMoveOut(){
		HttpServletRequest request=super.getRequest();
		String strdeviceId=request.getParameter("id");
		String strLocationX=request.getParameter("positionX");
		String strLocationY=request.getParameter("positionY");
		if(StringUtil.isNotBlank(strdeviceId)){
			Device device=new Device();
			device.setDevice_id(Long.parseLong(strdeviceId));
			device.setDevice_locationX(strLocationX);
			device.setDevice_locationY(strLocationY);
			deviceManageService.updateDeviceByTempDevice(device);
		}
	}
	
	public void selectBackgroundPicture() throws Exception{
		HttpServletRequest request=super.getRequest();
		HttpServletResponse response=super.getResponse();
		StringBuffer s = new StringBuffer();
		PrintWriter out = response.getWriter();
		s.append("bg3.jpg");
		out.print(s.toString());
		out.flush();
	}
	
	@Override
	public Device getModel() {
		// TODO Auto-generated mesthod stub
		return this.device;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<DeviceCategory> getDeviceCategoryList() {
		return deviceCategoryList;
	}
	public void setDeviceCategoryList(List<DeviceCategory> deviceCategoryList) {
		this.deviceCategoryList = deviceCategoryList;
	}
	public void setDeviceCategoryService(
			DeviceCategoryManageService deviceCategoryService) {
		this.deviceCategoryService = deviceCategoryService;
	}
	
	public long getDeviceMarkID() {
		return deviceMarkID;
	}

	public void setDeviceMarkID(long deviceMarkID) {
		this.deviceMarkID = deviceMarkID;
	}

	public String getDeviceCategoryName() {
		return deviceCategoryName;
	}
	public void setDeviceCategoryName(String deviceCategoryName) {
		this.deviceCategoryName = deviceCategoryName;
	}

	public List<Device> getAssociationDeviceList() {
		return this.associationDeviceList;
	}

	public void setAssociationDeviceList(List<Device> associationDeviceList) {
		this.associationDeviceList = associationDeviceList;
	}

	public List<Device> getSaveDeviceList() {
		return saveDeviceList;
	}

	public void setSaveDeviceList(List<Device> saveDeviceList) {
		this.saveDeviceList = saveDeviceList;
	}

	public void setSelectDeviceName(String selectDeviceName) {
		this.selectDeviceName = selectDeviceName;
	}

	public void setSelectDeviceCategory(String selectDeviceCategory) {
		this.selectDeviceCategory = selectDeviceCategory;
	}

	public void setSelectDeviceIp(String selectDeviceIp) {
		this.selectDeviceIp = selectDeviceIp;
	}

	public void setSelectDeviceMac(String selectDeviceMac) {
		this.selectDeviceMac = selectDeviceMac;
	}

	public void setSelectDeviceStatus(String selectDeviceStatus) {
		this.selectDeviceStatus = selectDeviceStatus;
	}

	public void setSelectDeviceLocation(String selectDeviceLocation) {
		this.selectDeviceLocation = selectDeviceLocation;
	}

	
	
//	 public void execSnmpCommand() throws Exception{
//		 HttpServletRequest request=super.getRequest();
//			HttpServletResponse response=super.getResponse();
//			
//			
//			request.setCharacterEncoding("utf-8");
//			response.setHeader("Pragma", "No-cache");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setDateHeader("Expires", 0);
//			response.setContentType("text/xml; charset=utf-8");
//
//			StringBuffer sTotol = new StringBuffer();
//			PrintWriter out = response.getWriter();
//			sTotol.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//			sTotol.append("<informationSum>");
//			
//			
//		  boolean networkUseable = false;
//		  boolean networkUseablememoryUsed = false;
//		  boolean networkUseablememoryTotal = false;
//		  boolean networkUseablehardDisk = false;
//		  boolean networkUseablecourseSum = false;
//		  
//		  this.deviceIp = request.getParameter("deviceIp");
//		 // this.deviceIp = "192.168.20.14";
//		    
//					  ///cpu---------------
//					        Process process = null;
//					  try {
//					   process = Runtime.getRuntime().exec("snmpwalk -v 2c -c public "+deviceIp+" .1.3.6.1.2.1.25.3.3.1.2");
//					  } catch (IOException e1) {
//					   // TODO Auto-generated catch block
//					  } 
//					        InputStreamReader r = new InputStreamReader(process.getInputStream());  
//					        LineNumberReader returnData = new LineNumberReader(r);  
//					        String line = ""; 
//					        try {
//					   while ((line = returnData.readLine()) != null) {  
//					        //System.out.println(line);  
//					        this.returnMsg += line; 
//					        this.returnMsg+="\n";
//					   }
//					//   session.setAttribute("msg", this.returnMsg);
//					   
//					   //.HttpSession session = super.getSession();
//					   //session.setAttribute("msg", this.returnMsg);
//					   if(this.returnMsg.indexOf("Unreachable") != -1 || this.returnMsg.indexOf("100% packet loss") != -1){  
//					          networkUseable = false;
//					         }  
//					         else{  
//					          networkUseable = true; 
//					         }  
//					   if(!networkUseable){
//				    	   this.returnMsg = "连接超时！";
//				       }
//					   System.out.println("returnMsg===="+returnMsg);
//					   sTotol.append("<information cpu=\"" + returnMsg + "\"");
//					   
//					  }catch (IOException e) {
//					   // TODO Auto-generated catch block
//					  } finally{
//					   
//					   if(returnData != null){
//					    try {
//					     
//					     returnData.close();
//					     
//					    } catch (IOException e) {
//					     // TODO Auto-generated catch block
//					    }
//					   }
//					   
//					   if(r != null){
//					    try {
//					     r.close();
//					     
//					    } catch (IOException e) {
//					     // TODO Auto-generated catch block
//					    }
//					   }
//					   
//					  } 
//		        
//		  //===========================================================================================
//		        
//		        ///已用内存---------------
//						        Process processmemoryUsed = null;
//						  try {
//							  processmemoryUsed = Runtime.getRuntime().exec("snmpwalk -v 2c -c public "+deviceIp+" .1.3.6.1.2.1.25.2.3.1.6.1");
//						  } catch (IOException e1) {
//						   // TODO Auto-generated catch block
//						  } 
//						        InputStreamReader rmemoryUsed = new InputStreamReader(processmemoryUsed.getInputStream());  
//						        LineNumberReader returnDatamemoryUsed = new LineNumberReader(rmemoryUsed);  
//						        String linememoryUsed = ""; 
//						        try {
//						   while ((linememoryUsed = returnDatamemoryUsed.readLine()) != null) {  
//						        this.memoryUsed += linememoryUsed; 
//						        this.memoryUsed+="\n";
//						   }
//						//   session.setAttribute("msg", this.returnMsg);
//						   
//						   //.HttpSession session = super.getSession();
//						   //session.setAttribute("msg", this.returnMsg);
//						   if(this.memoryUsed.indexOf("Unreachable") != -1 || this.memoryUsed.indexOf("100% packet loss") != -1){  
//							   networkUseablememoryUsed = false;
//						         }  
//						         else{  
//						        	 networkUseablememoryUsed = true; 
//						         }  
//						   if(!networkUseablememoryUsed){
//					    	   this.memoryUsed = "连接超时！";
//					       }
//						   System.out.println("memoryUsed===="+memoryUsed);
//						   sTotol.append("  memoryUsed=\"" + memoryUsed + "\"");
//						   
//						  }catch (IOException e) {
//						   // TODO Auto-generated catch block
//						  } finally{
//						   
//						   if(returnDatamemoryUsed!= null){
//						    try {
//						     
//						    	returnDatamemoryUsed.close();
//						     
//						    } catch (IOException e) {
//						     // TODO Auto-generated catch block
//						    }
//						   }
//						   
//						   if(rmemoryUsed != null){
//						    try {
//						    	rmemoryUsed.close();
//						     
//						    } catch (IOException e) {
//						     // TODO Auto-generated catch block
//						    }
//						   }
//						   
//						  }   
//		        //=======================================================
//						        
//						        
//						        ///总共 内存---------------
//						        Process processmemoryTotal = null;
//						  try {
//							  processmemoryTotal = Runtime.getRuntime().exec("snmpwalk -v 2c -c public "+deviceIp+" .1.3.6.1.2.1.25.2.2.0");
//						  } catch (IOException e1) {
//						   // TODO Auto-generated catch block
//						  } 
//						        InputStreamReader rmemoryTotal = new InputStreamReader(processmemoryTotal.getInputStream());  
//						        LineNumberReader returnDatamemoryTotal = new LineNumberReader(rmemoryTotal);  
//						        String linememoryTotal = ""; 
//						        try {
//						   while ((linememoryTotal = returnDatamemoryTotal.readLine()) != null) {  
//						        this.memoryTotal += linememoryTotal; 
//						        this.memoryTotal+="\n";
//						   }
//						//   session.setAttribute("msg", this.returnMsg);
//						   
//						   //.HttpSession session = super.getSession();
//						   //session.setAttribute("msg", this.returnMsg);
//						   if(this.memoryTotal.indexOf("Unreachable") != -1 || this.memoryTotal.indexOf("100% packet loss") != -1){  
//							   networkUseablememoryTotal = false;
//						         }  
//						         else{  
//						        	 networkUseablememoryTotal = true; 
//						         }  
//						   if(!networkUseablememoryTotal){
//					    	   this.memoryTotal = "连接超时！";
//					       }
//						   System.out.println("memoryTotal===="+memoryTotal);
//						   sTotol.append("  memoryTotal=\"" + memoryTotal + "\"");
//						   
//						  }catch (IOException e) {
//						   // TODO Auto-generated catch block
//						  } finally{
//						   
//						   if(returnDatamemoryTotal!= null){
//						    try {
//						     
//						    	returnDatamemoryTotal.close();
//						     
//						    } catch (IOException e) {
//						     // TODO Auto-generated catch block
//						    }
//						   }
//						   
//						   if(rmemoryTotal != null){
//						    try {
//						    	rmemoryTotal.close();
//						     
//						    } catch (IOException e) {
//						     // TODO Auto-generated catch block
//						    }
//						   }
//						   
//						  }   
//		        //=======================================================
//						        
//						        
//						        ///总共硬盘Hard disk---------------
////						        Process processhardDisk = null;
////						  try {
////							  processhardDisk = Runtime.getRuntime().exec("snmpwalk -v 2c -c public "+deviceIp+" .1.3.6.1.2.1.25.2.0");
////						  } catch (IOException e1) {
////						   // TODO Auto-generated catch block
////						  } 
////						        InputStreamReader rhardDisk = new InputStreamReader(processhardDisk.getInputStream());  
////						        LineNumberReader returnDatahardDisk = new LineNumberReader(rhardDisk);  
////						        String linehardDisk = ""; 
////						        try {
////						   while ((linehardDisk = returnDatahardDisk.readLine()) != null) {  
////						        this.hardDisk += linehardDisk; 
////						        this.hardDisk+="\n";
////						   }
////						//   session.setAttribute("msg", this.returnMsg);
////						   
////						   //.HttpSession session = super.getSession();
////						   //session.setAttribute("msg", this.returnMsg);
////						   if(this.hardDisk.indexOf("Unreachable") != -1 || this.hardDisk.indexOf("100% packet loss") != -1){  
////							   networkUseablehardDisk = false;
////						         }  
////						         else{  
////						        	 networkUseablehardDisk = true; 
////						         }  
////						   if(!networkUseablehardDisk){
////					    	   this.hardDisk = "连接超时！";
////					       }
////						   if("".endsWith(hardDisk.trim())){
////							   sTotol.append("  hardDisk=\"" + "无信息" + "\"");
////						   }else{
////							   sTotol.append("  hardDisk=\"" + hardDisk + "\"");
////						   }
////						   
////						   
////						  }catch (IOException e) {
////						   // TODO Auto-generated catch block
////						  } finally{
////						   
////						   if(returnDatahardDisk!= null){
////						    try {
////						     
////						    	returnDatahardDisk.close();
////						     
////						    } catch (IOException e) {
////						     // TODO Auto-generated catch block
////						    }
////						   }
////						   
////						   if(rhardDisk != null){
////						    try {
////						    	rhardDisk.close();
////						     
////						    } catch (IOException e) {
////						     // TODO Auto-generated catch block
////						    }
////						   }
////						   
////						  }   
//						        
//						String  hardDiskMessage=      LinuxDiskUtil.LinuxDiskMethod(deviceIp);
//						 sTotol.append("  hardDisk=\"" + hardDiskMessage + "\"");
//						 System.out.println("hardDiskMessage=="+hardDiskMessage);
//		        //=======================================================
//						        
//						        
//				//=======================================================
//						        
//						        
//						        ///得到取得windows端的系统进程用户数---------------
//						        Process processcourseSum = null;
//						  try {
//							  processcourseSum = Runtime.getRuntime().exec("snmpwalk -v 2c -c public "+deviceIp+" .1.3.6.1.2.1.25.1");
//						  } catch (IOException e1) {
//						   // TODO Auto-generated catch block
//						  } 
//						        InputStreamReader rcourseSum = new InputStreamReader(processcourseSum.getInputStream());  
//						        LineNumberReader returnDatacourseSum = new LineNumberReader(rcourseSum);  
//						        String linecourseSum = ""; 
//						        try {
//						   while ((linecourseSum = returnDatacourseSum.readLine()) != null) {  
//						        this.courseSum += linecourseSum; 
//						        this.courseSum+="\n";
//						   }
//						//   session.setAttribute("msg", this.returnMsg);
//						   
//						   //.HttpSession session = super.getSession();
//						   //session.setAttribute("msg", this.returnMsg);
//						   if(this.courseSum.indexOf("Unreachable") != -1 || this.courseSum.indexOf("100% packet loss") != -1){  
//							   networkUseablecourseSum = false;
//						         }  
//						         else{  
//						        	 networkUseablecourseSum = true; 
//						         }  
//						   if(!networkUseablecourseSum){
//					    	   this.courseSum = "连接超时！";
//					       }
//						   courseSum=   StringUtil.replace(courseSum, "\"", "|");
//						   System.out.println("courseSum===="+courseSum);
//						   sTotol.append("  courseSum=\"" + courseSum + "\"");
//						   
//						  }catch (IOException e) {
//						   // TODO Auto-generated catch block
//						  } finally{
//						   
//						   if(returnDatacourseSum!= null){
//						    try {
//						     
//						    	returnDatacourseSum.close();
//						     
//						    } catch (IOException e) {
//						     // TODO Auto-generated catch block
//						    }
//						   }
//						   
//						   if(rcourseSum != null){
//						    try {
//						    	rcourseSum.close();
//						     
//						    } catch (IOException e) {
//						     // TODO Auto-generated catch block
//						    }
//						   }
//						   
//						  }   
//		        //=======================================================
//						        
//						        
//		        sTotol.append("/>");
//		        sTotol.append("</informationSum>");
//
//		        System.out.println("sTotol--------------"+sTotol);
//				out.print(sTotol.toString());
//				out.flush();        
//		 }

	 public void execSnmpCommand() throws Exception{
		 HttpServletRequest request=super.getRequest();
			HttpServletResponse response=super.getResponse();
			
			String ip = request.getParameter("deviceIp");
			String snmpCommunityName = request.getParameter("snmpCommunityName");
			
			if(null==snmpCommunityName||"".equals(snmpCommunityName)){
				snmpCommunityName="public";
			}
			StringBuffer sTotol = new StringBuffer();
			PrintWriter out = response.getWriter();
			
			///-------------先判断是否在线
			
			
			
			///方法二
//			 boolean networkUseable = false;
//			  this.deviceIp = ip;
//			        Process process = null;
//			  try {
//			   process = Runtime.getRuntime().exec("ping -c 3 "+deviceIp);
//			  } catch (IOException e1) {
//			   // TODO Auto-generated catch block
//			  } 
//			        InputStreamReader r = new InputStreamReader(process.getInputStream());  
//			        LineNumberReader returnData = new LineNumberReader(r);  
//			        String line = ""; 
//			        try {
//			   while ((line = returnData.readLine()) != null) {  
//			        //System.out.println(line);  
//			        this.returnMsg += line; 
//			        this.returnMsg+="\n";
//			   }
//			//   session.setAttribute("msg", this.returnMsg);
//			   
//			   //.HttpSession session = super.getSession();
//			   //session.setAttribute("msg", this.returnMsg);
//			   if(this.returnMsg.indexOf("Unreachable") != -1 || this.returnMsg.indexOf("100% packet loss") != -1){  
//			          networkUseable = false;
//			         }  
//			         else{  
//			          networkUseable = true; 
//			         }  
//			   if(!networkUseable){
//		    	   this.returnMsg = "FFfalse";
//		       }
//			   
//			  }catch (IOException e) {
//			   // TODO Auto-generated catch block
//			  } finally{
//			   
//			   if(returnData != null){
//			    try {
//			     
//			     returnData.close();
//			     
//			    } catch (IOException e) {
//			     // TODO Auto-generated catch block
//			    }
//			   }
//			   
//			   if(r != null){
//			    try {
//			     r.close();
//			     
//			    } catch (IOException e) {
//			     // TODO Auto-generated catch block
//			    }
//			   }
//			   
//			  } 
			
			////////----------------------
			
			
			
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("text/xml; charset=utf-8");
			
			sTotol.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sTotol.append("<informationSum>");
			//方法一
			Ping ping=new Ping(ip);
			String result=ping.pingIP();
			//
			if(StringUtil.isBlank(result)){
				
				 sTotol.append("<information cpu=\"" + "设备不在线" + "\"");
				 
				  sTotol.append("  memoryUsed=\"" + "设备不在线" + "\"");
				  
				  sTotol.append("  hardDisk=\"" + "设备不在线" + "\"");
				
				  sTotol.append("  macMessage=\"" + "设备不在线" + "\"");
				  
				  sTotol.append("  processMessage=\"" + "设备不在线" + "\"");
				  sTotol.append("/>");
				
			}else{
			
				String cpuMessage=CpuSnmpUtil.getCpu(ip,snmpCommunityName);
			 if(cpuMessage.trim().contains("连接暂时中断")){
				 sTotol.append("<information cpu=\"" + "设备没有开启snmp服务" + "\"");
				 
				  sTotol.append("  memoryUsed=\"" + "设备没有开启snmp服务" + "\"");
				  
				  sTotol.append("  hardDisk=\"" + "设备没有开启snmp服务" + "\"");
				
				  sTotol.append("  macMessage=\"" + "设备没有开启snmp服务" + "\"");
				  
				  sTotol.append("  processMessage=\"" + "设备没有开启snmp服务" + "\"");
				  sTotol.append("/>");
				}else{
						sTotol.append("<information cpu=\"" + cpuMessage + "\"");
						String memoryMessage=MemorySnmpUtil.getMemory(ip,snmpCommunityName);
					  sTotol.append("  memoryUsed=\"" + memoryMessage + "\"");
					  
					  String diskMessage=DiskSnmpUtil.getDisk(ip,snmpCommunityName);
					  sTotol.append("  hardDisk=\"" + diskMessage + "\"");
					  
					  String macMessage=MacSnmpUtil.getMac(ip,snmpCommunityName);
					  sTotol.append("  macMessage=\"" + macMessage + "\"");
					  
					  String processMessage=ProcessSnmpUtil.getProcess(ip,snmpCommunityName);
					  sTotol.append("  processMessage=\"" + processMessage + "\"");
					  sTotol.append("/>");
				}
			 
			  
			}  
			
		    sTotol.append("</informationSum>");

			out.print(sTotol.toString());
			out.flush(); 
	 }


	public String getDeviceIp() {
		return deviceIp;
	}




	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}




	public String getReturnMsg() {
		return returnMsg;
	}




	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}




	public String getMemoryUsed() {
		return memoryUsed;
	}




	public void setMemoryUsed(String memoryUsed) {
		this.memoryUsed = memoryUsed;
	}




	public String getMemoryTotal() {
		return memoryTotal;
	}




	public void setMemoryTotal(String memoryTotal) {
		this.memoryTotal = memoryTotal;
	}




	public String getHardDisk() {
		return hardDisk;
	}




	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}




	public String getCourseSum() {
		return courseSum;
	}




	public void setCourseSum(String courseSum) {
		this.courseSum = courseSum;
	}




	public AlertMessageSerive getAlertMessageManager() {
		return alertMessageManager;
	}




	public void setAlertMessageManager(AlertMessageSerive alertMessageManager) {
		this.alertMessageManager = alertMessageManager;
	}




	public AssetService getAssetManager() {
		return assetManager;
	}




	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	
	
	
}
