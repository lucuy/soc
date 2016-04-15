package com.soc.webapp.action.asset;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetProbe;
import com.soc.service.asset.AssetProbeService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.webapp.action.BaseAction;
import com.util.OSUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 资产探测任务执行Action
 * 
 * @author 张浩磊
 * 
 */
public class AssetProbeAction extends BaseAction {
	HttpServletResponse response = super.getResponse();

	// 审计业务管理类
	private AuditService auditManager;
	private AssetProbeService assetProbeServiceManager ;  //资产探测表
	private String collectorMac;//采集器MAC
	private String deviceType;//资产设备类型
	private String deviceMemo;//资产设备描述
	private String deviceIp;//资产Ip地址
	private String deviceMac;//资产MAC地址
    private List<AssetProbe> assetProbeList ; 
	private int taskId ;     //探测任务Id
	
	public String queryProbeAssetByMac(){
		
		//System.out.println(collectorMac);
		assetProbeList =  assetProbeServiceManager.queryByMac(collectorMac);
		//System.out.println(assetProbeList);
		return SUCCESS ; 
	}
	
	public String queryProbeAssetById(){
		if(taskId != 0){
			assetProbeList = assetProbeServiceManager.queryByTaskId(taskId) ; 
		}
		return SUCCESS ; 
	}
	
	
	
	
//	/**
//	 * 解析资产探测结果XML文件
//	 * 
//	 * @return
//	 */
//	public String resolveXml() {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		List<Asset> aslist = new ArrayList<Asset>();
//		Page page = null;
//		SearchResult sr = null;
//		// 处理数据分页的起始条数
//		String startIndex = request.getParameter("startIndex");
//
//		if (StringUtil.isNotBlank(startIndex)) {
//			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
//		} else {
//			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
//		}
//		
//		//验证资产探测异常begin
//		
//		File files = new File("D://exited");
//		// 判断是否出现探测失败
//		if (files.exists()) {
//			// 探测失败提示用户并
//			JOptionPane.showMessageDialog(null, "探测设备失败请重新开始", "系统信息",
//					JOptionPane.ERROR_MESSAGE);
//			return INPUT;
//		} else {
//			File file = new File("D://collectTask.xml");
//			// 判断资产探测是否已经完毕
//			if (file.exists()) {
//
//				try {
//					SAXReader reader = new SAXReader();
//					Document document = reader.read(new File(
//							"D://collectTask.xml"));
//					Element reElement = document.getRootElement();
//					Element rootElement = reElement.element("content");
//					Element rootElement1 = rootElement.element("result");
//					List nodes = rootElement1.elements("asset");
//
//					for (Iterator i = nodes.iterator(); i.hasNext();) {
//						Asset asset = new Asset();
//						Element elm = (Element) i.next();
//						asset.setAssetIps(elm.attributeValue("ip"));
//						asset.setAssetMac(elm.attributeValue("mac"));
//						for (Iterator j = elm.elementIterator(); j.hasNext();) {
//							Element elm1 = (Element) j.next();
//							if (elm1.elementText("osmatch") != null) {
//								asset.setAssetMemo(elm1.elementText("osmatch"));
//							}
//						}
//						for (Iterator k = elm.elementIterator(); k.hasNext();) {
//							Element elm2 = (Element) k.next();
//							if (elm2.elementText("osfamily") != null) {
//								asset.setAssetDeviceType(elm2
//										.elementText("osfamily"));
//							}
//
//						}
//
//						aslist.add(asset);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			} else {
//				return NONE;
//			}
//		}
//		if (aslist.size() > 0) {
//			page.setTotalCount(aslist.size());
//
//			request.setAttribute("Page", page);
//		}
//		request.getSession().setAttribute("probelist", aslist);
//		return SUCCESS;
//	}

	

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getCollectorMac() {
		return collectorMac;
	}

	public void setCollectorMac(String collectorMac) {
		this.collectorMac = collectorMac;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceMemo() {
		return deviceMemo;
	}

	public void setDeviceMemo(String deviceMemo) {
		this.deviceMemo = deviceMemo;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public AssetProbeService getAssetProbeServiceManager() {
		return assetProbeServiceManager;
	}

	public void setAssetProbeServiceManager(
			AssetProbeService assetProbeServiceManager) {
		this.assetProbeServiceManager = assetProbeServiceManager;
	}






	public List<AssetProbe> getAssetProbeList() {
		return assetProbeList;
	}






	public void setAssetProbeList(List<AssetProbe> assetProbeList) {
		this.assetProbeList = assetProbeList;
	}






	public int getTaskId() {
		return taskId;
	}






	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

    




	
}
