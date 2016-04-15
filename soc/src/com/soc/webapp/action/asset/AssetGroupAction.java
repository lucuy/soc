/*
 * 文 件 名:  AssetGroupAction.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.webapp.action.asset;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.Radio;
import com.util.Span;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;
import com.util.treeview.AssetGroupTree;

/**
 * 资产组Action 资产组信息的查看，编辑，删除，添加同级资产组，下级资产组
 * 
 * @author liuyanxu
 * @version [V100R001C001, 2012-8-27]
 * @see com.soc.service.asset.AssetGroupService
 * @since [任务管理/资产组管理/V100R001C001]
 */
public class AssetGroupAction extends BaseAction {

	private String htmlBuffer;

	// 资产组业务处理类
	private AssetGroupService assetGroupManager;

	private AssetService assetManager;

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	// 采集器业务管理类
	private SettingCollectorService collectorManager;

	// 资产组实体类
	private AssetGroup assetGroup;

	// 资产id
	private String assetGroupId;

	// 添加类型
	private String type;

	// 是否点击资产管理
	private String isOnclick;

	// 父级ID
	private long parentId;

	// 审计业务管理
	private AuditService auditManager;

	private String treeQuery;

	private String treeIpQuery;
	
	private String assetsString ; 
	
	private List<Asset> assetList ;
	 //资产组名称
    private String assetGroupName;
    private long groupId;
    

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	/**
	 * 显示左侧资产组树 <功能详细描述>
	 * 
	 * @return String
	 * @see
	 */
	public String displayLeftManagetree() {
		log.info("[AssetGroupAction] enter displayLeftManagetree");
		/*
		 * try { AssetGroupTree assetGroupTree = new
		 * AssetGroupTree(assetGroupManager,
		 * super.getRequest().getContextPath()); Span span = new Span();
		 * span.setServerVar("assetGroupId");
		 * span.setServerTextVar("assetGroupName");
		 * span.setOnClick("showAsset"); span.setActionParam("assetGroupId");
		 * span.setCss("cursor:pointer;vertical-align:10%;");
		 * 
		 * assetGroupTree.setSpan(span);
		 * 
		 * this.setHtmlBuffer(assetGroupTree.displayTree());
		 * 
		 * } catch (UnsupportedEncodingException e) {
		 * log.error("[EmployeeGroupAction] unsupport encoding exception.", e);
		 * } catch (IOException e) {
		 * log.error("[EmployeeGroupAction] io excepion.", e); } isOnclick =
		 * "true";
		 */
		String objectPath = super.getRequest().getContextPath();
		isOnclick = "true";
		//long userid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getUserId();
		long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid(); 
		
		htmlBuffer = assetGroupManager.getTreeStyle(objectPath,groupId);

		// 监控域分组
        
		//采集器分组
		//treeQuery = collectorManager.queryCollectorTree(objectPath);
		
        //Ip分组
		//treeIpQuery = assetManager.queryIpTree(objectPath);

		return SUCCESS;
	}

	/**
	 * 管理资产树 <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String displayManagetree() {
		try {
			AssetGroupTree assetGroupTree = new AssetGroupTree(
					assetGroupManager, super.getRequest().getContextPath());
			Span span = new Span();
			span.setServerVar("assetGroupId");
			span.setServerTextVar("assetGroupName");
			span.setOnClick("edit");
			span.setCss("cursor:pointer;");
			span.setActionParam("assetGroupId");
			assetGroupTree.setSpan(span);
			groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid(); 

			Radio radio = new Radio();
			radio.setVarName("assetGroupId");
			radio.setServerVar("assetGroupId");
			assetGroupTree.setRadio(radio);
			this.setHtmlBuffer(assetGroupTree.displayTree(groupId));
			//System.out.println(this.getHtmlBuffer());

		} catch (UnsupportedEncodingException e) {
			log.error("[EmployeeGroupAction] unsupport encoding exception.", e);
		} catch (IOException e) {
			log.error("[EmployeeGroupAction] io excepion.", e);
		}
		return SUCCESS;
	}

	/**
	 * 资产组的插入显示页面 根据前台的要求显示不同数据
	 * 
	 * @return String
	 * @see com.soc.service.asset.AssetGroupService#insertAssetGroup(String,
	 *      int)
	 */
	public String insert() {
		log.info("[AssetGroupAction] Enter insert...");
		if (StringUtil.isBlank(assetGroupId)) {
			assetGroupId = "0";
		}
		assetGroup = assetGroupManager.insertAssetGroup(type,
				Integer.parseInt(assetGroupId));
		return SUCCESS;
	}

	/**
	 * 进入到编辑页面
	 * 
	 * @return String
	 * @see com.soc.service.asset.AssetGroupService#queryById(long)
	 */
	public String edit() {
		
		log.info("[AssetGroupAction] Enter edit.....");

		assetGroup = assetGroupManager
				.queryById(Integer.parseInt(assetGroupId));
		Map map = new HashMap<String, Object>(); 
		if(StringUtil.isNotEmpty(assetGroupId)){
			map.put("assetGroupId", Integer.parseInt(assetGroupId));
			assetList =  assetManager.queryAssetByGroupId(map) ; 
		}
		return SUCCESS;
	}


	/**
	 * 更改资产组信息 资产组的编辑添加
	 * 
	 * @see com.soc.service.asset.AssetGroupService#updateAssetGroup(AssetGroup)
	 */
	public void updateAssetGroup() {		
		log.info("[AssetGroupAction] enter updateAssetGroup...");


		List<String> fieldList = new ArrayList<String>();
		fieldList.add(assetGroup.getAssetGroupName() + "("
				+ assetGroup.getAssetGroupName() + ")");
		//获得修改的资产组的ID
		int groupId =  assetGroupManager.updateAssetGroup(assetGroup);

		
		/**
		 * 当删除父级组中的资产时同时删除子组中的资产
		 */
		
		//获得修改之前的资产组对应的资产信息
    	Map assetMap = new HashMap();
    	assetMap.put("assetGroupId", groupId);
    	//查询资产组下先前对应的资产信息
    	List<Asset> assetList = assetManager.queryAssetByGroupId(assetMap) ; 
    	//String[] str = assetsString.split(",");
        for(Asset asset : assetList){
                 	String assetId = String.valueOf(asset.getAssetId());
                 	//当前台选定的资产中没有之前选定的资产时
        	        if(assetsString.indexOf(assetId) < 0){
        	        	  List<Map> mapList = assetGroupManager.querySon(String.valueOf(groupId));
        	        	  for(Map map:mapList){
        	        		     map.put("assetId", asset.getAssetId());
        	        		     assetGroupManager.delAssetLinkedPro(map);
        	        	  }
        	        }
        }
    	
    	
    	
    	
		
	    if(StringUtil.isNotEmpty(assetsString) && groupId != 0 ){
        	String[] str = assetsString.split(",");
        	Map map = new HashMap<String, Object>();
        	assetGroupManager.delLikedAssetGroup(Long.parseLong(String.valueOf(groupId)));

        	for(int i=0; i< str.length;i++){
        		   map.put("assetGroupId", assetGroup.getAssetGroupId());
        		if(str[i] != ""){
        		   map.put("assetId",Integer.parseInt(str[i]));
        		}
        		assetGroupManager.linkedAssetAssetGroup(map);
        	}
        }else if(!StringUtil.isNotEmpty(assetsString)){
        	assetGroupManager.delLikedAssetGroup(Long.parseLong(String.valueOf(groupId)));
        }
		
		if (assetGroup.getAssetGroupId() == 0) {

			// 审计日志
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产组", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :添加资产组";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增资产组");
		} else {
			// 审计日志
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产组", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :更新资产组";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改资产组");
		}

		// 刷新父页面
		try {
			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/assetGroup/showGroupTree.action';"
					+ "parent.location.reload();" + "</script>";
			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除资产组信息 <功能详细描述>
	 * 
	 * @see com.soc.service.asset.AssetGroupService#deleteAssetGroup(long)
	 */
	public void deleteAssetGroup() {
		log.info("[AssetGroupAction] enter deleteAssetGroup..");
		List<String> fieldList = new ArrayList<String>();

		if (StringUtil.isNotBlank(assetGroupId)) {
			AssetGroup assetGroupObject = assetGroupManager.queryById(Long
					.parseLong(assetGroupId));
			fieldList.add(assetGroupObject.getAssetGroupName() + "("
					+ assetGroupObject.getAssetGroupName() + ")");
			assetGroupManager.deleteAssetGroup(Long.parseLong(assetGroupId));
		}

		// 审计日志
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "资产组", super
				.getRequest().getRemoteAddr(), fieldList);

		// syslog
		/*String logString = "";

		logString = "登录名:"
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + " 源IP:"
				+ getRequest().getRemoteAddr() + " 操作时间:"
				+ DateUtil.curDateTimeStr19() + " 操作类型 :删除资产组";
		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除资产组");
		// 刷新父页面
		try {
			String script = "<script language=javascript>parent.parent.leftFrame.location.href = "
					+ "'/soc/assetGroup/showGroupTree.action';"
					+ "parent.location.reload();" + "</script>";

			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void querySon() {
		LOG.info("[AssetGroupAction] enter method queryByParentId() ...");

		if (parentId != 0) {
			StringBuffer assetGroupIds = new StringBuffer();
			assetGroupIds.append(parentId + ",");
			List<Map> lm = assetGroupManager.querySon("," + parentId + ",");
			for (Map m : lm) {
				assetGroupIds.append(m.get("assetGroupId") + ",");
			}

			// Ajax返回
			try {
				getResponse().getWriter().write(assetGroupIds.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <验证该组下是否存在资产> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void checkAssets() {
		log.info("[AssetGroupAction] enter method checkAssets....");

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("assetGroupId", Integer.parseInt(assetGroupId));

		// 查询改组下的资产数目
		int count = assetManager.selectByGroupId(map);

		String flag = "false";

		if (count > 0) {
			flag = "true";
		}

		try {
			getResponse().getWriter().write(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * <根据当前用户查询所有组下的资产> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public String queryAllAssetByUser(){
		
		log.info("[AssetGroupAction] Enter method queryAllAssetByUser()...");
		HttpServletRequest request = super.getRequest();

		Page page = null;

		SearchResult sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");

		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		long groupId=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		
		
		
		//assetList = assetGroupManager.getAllAsset(groupId,page);
		return SUCCESS ; 
	}
	
	//检查资产组名称是不是唯一的
	public void checkAssetGroupName(){
		// 标识此此名称的资产组是否存在
		String flag = "false";
		if(StringUtil.isNotBlank(assetGroupName)){
			AssetGroup aGroup =assetGroupManager.queryByAssetGroupName(assetGroupName);
			//查找到此名称已经被占用，将标识flag赋值为“true”
			if(aGroup!=null){
				flag="true";
			}
			//将flag返回给页面
			try {
				getResponse().getWriter().write(flag);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public String getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(String htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}

	public AssetGroup getAssetGroup() {
		return assetGroup;
	}

	public void setAssetGroup(AssetGroup assetGroup) {
		this.assetGroup = assetGroup;
	}

	public String getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(String assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsOnclick() {
		return isOnclick;
	}

	public void setIsOnclick(String isOnclick) {
		this.isOnclick = isOnclick;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public SettingCollectorService getCollectorManager() {
		return collectorManager;
	}

	public void setCollectorManager(SettingCollectorService collectorManager) {
		this.collectorManager = collectorManager;
	}

	public String getTreeQuery() {
		return treeQuery;
	}

	public void setTreeQuery(String treeQuery) {
		this.treeQuery = treeQuery;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public String getTreeIpQuery() {
		return treeIpQuery;
	}

	public void setTreeIpQuery(String treeIpQuery) {
		this.treeIpQuery = treeIpQuery;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getAssetsString() {
		return assetsString;
	}

	public void setAssetsString(String assetsString) {
		this.assetsString = assetsString;
	}

	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public String getAssetGroupName() {
		return assetGroupName;
	}

	public void setAssetGroupName(String assetGroupName) {
		this.assetGroupName = assetGroupName;
	}
	
	

    

}
