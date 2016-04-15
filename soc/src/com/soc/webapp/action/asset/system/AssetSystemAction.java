package com.soc.webapp.action.asset.system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.asset.device.Category;
import com.soc.model.asset.system.AssetSystem;
import com.soc.model.user.User;
import com.soc.service.asset.system.AssetSystemService;
import com.soc.service.audit.AuditService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetSystemAction extends BaseAction {

	private AssetSystemService asManager;
	private long asId;
	private AssetSystem assetSystem;
	private List<AssetSystem> systemlist;
	
	private String assetSystemName;      	//系统、版本、版本号
	private String assetSystemComment;		//系统备注
	private String systemOptions[];			//系统品牌集合
	private String versionOptions[];		//系统版本
	private String versionNoOptions[];		//系统版本号集合
	
	private String ids;					//批量删除资产系统的id
	
	private String keyword; 			//快速查询
	
	private AuditService auditManager;
		
	public List<AssetSystem> getSystemlist() {
		return systemlist;
	}
	public void setSystemlist(List<AssetSystem> systemlist) {
		this.systemlist = systemlist;
	}
	public AssetSystemService getAsManager() {
		return asManager;
	}
	public void setAsManager(AssetSystemService asManager) {
		this.asManager = asManager;
	}
	public long getAsId() {
		return asId;
	}
	public void setAsId(long asId) {
		this.asId = asId;
	}
	public AssetSystem getAssetSystem() {
		return assetSystem;
	}
	public void setAssetSystem(AssetSystem assetSystem) {
		this.assetSystem = assetSystem;
	}
	
	/**
	 * 查询所有的资产系统
	 * @return
	 */
	public String querySystem(){
		SearchResult sr = null;
		Page page = null;
		HttpServletRequest request = super.getRequest();
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接收查询条件，并存储到map中
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		sr = asManager.querySystem(map, page);
		systemlist = sr.getList();
		
		if (sr!=null) {
			request.setAttribute("Page", sr.getPage());
		}
		return SUCCESS;
		
	}
	
	/**
	 * 跳转到新增系统页面
	 * @return
	 */
	public String toEditAssetSystem(){
		if (asId !=0 ) {
			assetSystem = asManager.queryAssetSystemById(asId);
		}
		return SUCCESS;
	}
	
	/**
	 * 新增修改资产系统
	 * @return
	 */
	public String insertAssetSystem(){
		log.info("[AssetSystemAction] Enter method insertAssetSystem()...");
		Map<String, Object> map = new HashMap<String, Object>();
		if (asId == 0) {
			map.put("name", assetSystemName!=null?assetSystemName.trim():assetSystemName);
			map.put("comment", assetSystemComment!=null?assetSystemComment.trim():assetSystemComment);
			map.put("level", 0);
			map.put("nolevel", 0);
			asId = (int) asManager.insertAssetSystem(map);
			List<String> fieldList1 = new ArrayList<String>();
			fieldList1.add(map.get("name") + "(" + map.get("name")
					+ ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
					.getRequest().getRemoteAddr(), fieldList1);
			if (asId>0) {
				if (systemOptions != null && systemOptions.length>0) {
					for (int i = 0; i < systemOptions.length; i++) {
						if (systemOptions[i]!="") {
							map = new HashMap<String, Object>();
							map.put("name", systemOptions[i].trim());
							map.put("level",asId);
							map.put("comment", "");
							map.put("nolevel", 3);
							asManager.insertAssetSystem(map);
							List<String> fieldList2 = new ArrayList<String>();
							fieldList2.add(map.get("name") + "(" + map.get("name")
									+ ")");
							auditManager.insertByInsertOperator(((User) this.getSession()
									.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
									.getRequest().getRemoteAddr(), fieldList2);
						}
					}
				}
				if (versionOptions!=null && versionOptions.length>0) {
					for (int i = 0; i < versionOptions.length; i++) {
						if (versionOptions[i]!="") {
							map = new HashMap<String, Object>();
							map.put("name", versionOptions[i].trim());
							map.put("level",asId);
							map.put("comment", "");
							map.put("nolevel", 1);
							asManager.insertAssetSystem(map);
							List<String> fieldList2 = new ArrayList<String>();
							fieldList2.add(map.get("name") + "(" + map.get("name")
									+ ")");
							auditManager.insertByInsertOperator(((User) this.getSession()
									.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
									.getRequest().getRemoteAddr(), fieldList2);
						}
					}
				}
				if (versionNoOptions != null && versionNoOptions.length>0) {
					for (int i = 0; i < versionNoOptions.length; i++) {
						if (versionNoOptions[i]!="") {
							map = new HashMap<String, Object>();
							map.put("name", versionNoOptions[i].trim());
							map.put("level",asId);
							map.put("comment", "");
							map.put("nolevel", 2);
							asManager.insertAssetSystem(map);
							List<String> fieldList2 = new ArrayList<String>();
							fieldList2.add(map.get("name") + "(" + map.get("name")
									+ ")");
							auditManager.insertByInsertOperator(((User) this.getSession()
									.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
									.getRequest().getRemoteAddr(), fieldList2);
						}
					}
				}
			}
		}else {
			asManager.delAssetSystemById(asId);
			map.put("name", assetSystemName!=null?assetSystemName.trim():assetSystemName);
			map.put("comment", assetSystemComment!=null?assetSystemComment.trim():assetSystemComment);
			map.put("level", 0);
			map.put("nolevel", 0);
			asId = (int) asManager.insertAssetSystem(map);
			List<String> fieldList1 = new ArrayList<String>();
			fieldList1.add(map.get("name") + "(" + map.get("name")
					+ ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
					.getRequest().getRemoteAddr(), fieldList1);
			if (asId>0) {
				if (systemOptions != null && systemOptions.length>0) {
					for (int i = 0; i < systemOptions.length; i++) {
						if (systemOptions[i]!="") {
							map = new HashMap<String, Object>();
							map.put("name", systemOptions[i].trim());
							map.put("level",asId);
							map.put("comment", "");
							map.put("nolevel", 3);
							asManager.insertAssetSystem(map);
							List<String> fieldList2 = new ArrayList<String>();
							fieldList2.add(map.get("name") + "(" + map.get("name")
									+ ")");
							auditManager.insertByInsertOperator(((User) this.getSession()
									.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
									.getRequest().getRemoteAddr(), fieldList2);
						}
					}
				}
				if (versionOptions!=null && versionOptions.length>0) {
					for (int i = 0; i < versionOptions.length; i++) {
						if (versionOptions[i]!="") {
							map = new HashMap<String, Object>();
							map.put("name", versionOptions[i].trim());
							map.put("level",asId);
							map.put("comment", "");
							map.put("nolevel", 1);
							asManager.insertAssetSystem(map);
							List<String> fieldList2 = new ArrayList<String>();
							fieldList2.add(map.get("name") + "(" + map.get("name")
									+ ")");
							auditManager.insertByInsertOperator(((User) this.getSession()
									.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
									.getRequest().getRemoteAddr(), fieldList2);
						}
					}
				}
				if (versionNoOptions != null && versionNoOptions.length>0) {
					for (int i = 0; i < versionNoOptions.length; i++) {
						if (versionNoOptions[i]!="") {
							map = new HashMap<String, Object>();
							map.put("name", versionNoOptions[i].trim());
							map.put("level",asId);
							map.put("comment", "");
							map.put("nolevel", 2);
							asManager.insertAssetSystem(map);
							List<String> fieldList2 = new ArrayList<String>();
							fieldList2.add(map.get("name") + "(" + map.get("name")
									+ ")");
							auditManager.insertByInsertOperator(((User) this.getSession()
									.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
									.getRequest().getRemoteAddr(), fieldList2);
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 根据id删除资产系统
	 * @return
	 */
	public String deleteAssetSystem(){
		log.info("[AssetSystemAction] Enter deleteAssetSystem...");
		if (StringUtil.isNotBlank(ids)) {
			if (ids.indexOf(",")>0) {
				String [] checked = ids.split(",");
				for (int i = 0; i < checked.length; i++) {
					assetSystemName=asManager.querySystemNameById(Long.parseLong(checked[i].trim()));
					asManager.delAssetSystemById(Long.parseLong(checked[i].trim()));
					List<String> fieldList = new ArrayList<String>();
					fieldList.add(assetSystemName + "(" + assetSystemName
							+ ")");
					auditManager.insertByDeleteOperator(((User) this.getSession()
							.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
							.getRequest().getRemoteAddr(), fieldList);
				}
			}else {
				assetSystemName=asManager.querySystemNameById(Long.parseLong(ids));
				asManager.delAssetSystemById(Long.parseLong(ids));
				List<String> fieldList = new ArrayList<String>();
				fieldList.add(assetSystemName + "(" + assetSystemName
						+ ")");
				auditManager.insertByDeleteOperator(((User) this.getSession()
						.getAttribute("SOC_LOGON_USER")).getUserId(), "资产系统", super
						.getRequest().getRemoteAddr(), fieldList);
			}
		}
		return SUCCESS;
	}
	
	//检查操作系统唯一性
	public void checkSystemName(){
		// 标识此此操作系统是否存在
		String flag = "false";
		if (StringUtil.isNotBlank(assetSystemName)) {
			AssetSystem assetSystem = asManager.queryAssetSystemByName(assetSystemName);
			// 查找到操作系统，将标识flag设置为true
			if (assetSystem != null) {
				flag = "true";
			}

			// 将flag返回给页面
			try {
				getResponse().getWriter().write(flag);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		
	}
	
	
	
	
	
	
	public String getAssetSystemName() {
		return assetSystemName;
	}
	public void setAssetSystemName(String assetSystemName) {
		this.assetSystemName = assetSystemName;
	}
	public String getAssetSystemComment() {
		return assetSystemComment;
	}
	public void setAssetSystemComment(String assetSystemComment) {
		this.assetSystemComment = assetSystemComment;
	}
	public String[] getSystemOptions() {
		return systemOptions;
	}
	public void setSystemOptions(String[] systemOptions) {
		this.systemOptions = systemOptions;
	}
	public String[] getVersionOptions() {
		return versionOptions;
	}
	public void setVersionOptions(String[] versionOptions) {
		this.versionOptions = versionOptions;
	}
	public String[] getVersionNoOptions() {
		return versionNoOptions;
	}
	public void setVersionNoOptions(String[] versionNoOptions) {
		this.versionNoOptions = versionNoOptions;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	
}
