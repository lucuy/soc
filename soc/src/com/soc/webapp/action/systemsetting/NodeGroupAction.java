package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.soc.model.monitor.Node;
import com.soc.model.monitor.NodeTree;
import com.soc.model.systemsetting.NodeGroup;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.NodeGroupService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.IpFilterUtil;
import com.util.Radio;
import com.util.Span;
import com.util.StringUtil;
import com.util.treeview.AssetGroupTree;

/**
 * 节点组Action 节点组信息的查看，编辑，删除，添加同级节点组，下级节点组
 * 
 * @author lichanghong
 * @version [V100R001C001, 2013-12-20]
 * @see com.soc.service.systemsetting.NodeGroupService
 * @since [任务管理/节点组管理/V100R001C001]
 */
public class NodeGroupAction extends BaseAction {
	private String htmlBuffer;
	private NodeGroupService nodeGroupManager;
	private NodeGroup nodeGroup;
	private long parentId;
	private String type;
	private String nodeGroupId;
	// 审计业务管理
	private AuditService auditManager;
	//node树的类
	private NodeTree nt;
	//前台需要的jetsen字符串
	private String JsonSrc;
	//前台传过来的id
	private int id;
	//位置 top
	private int top;
	 //位子 left
	 private int left;
	/**
	 * 管理级联节点 <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String displayNodetree() {
		try {
			AssetGroupTree nodeGroupTree = new AssetGroupTree(nodeGroupManager,
					super.getRequest().getContextPath());
			Span span = new Span();
			span.setServerVar("nodeGroupId");
			span.setServerTextVar("nodeGroupName");
			span.setOnClick("edit");
			span.setCss("cursor:pointer;");
			span.setActionParam("nodeGroupId");
			nodeGroupTree.setSpan(span);

			Radio radio = new Radio();
			radio.setVarName("nodeGroupId");
			radio.setServerVar("nodeGroupId");
			nodeGroupTree.setRadio(radio);
			this.setHtmlBuffer(nodeGroupTree.displayNodeTree());
		} catch (UnsupportedEncodingException e) {
			log.error("[EmployeeGroupAction] unsupport encoding exception.", e);
		} catch (IOException e) {
			log.error("[EmployeeGroupAction] io excepion.", e);
		}
		return SUCCESS;
	}
	/**
	 * 节点新增
	 */
	public String toinsert(){
		if(StringUtil.isBlank(nodeGroupId)){
			nodeGroupId="0";
		}
		nodeGroup = nodeGroupManager.insert(type, Long.parseLong(nodeGroupId));
		return SUCCESS;
	}
	/**
	 * 进入到编辑页面
	 * 
	 */
	public String edit() {
		
		log.info("[AssetGroupAction] Enter edit.....");

		nodeGroup = nodeGroupManager
				.queryById(Integer.parseInt(nodeGroupId));
		
		return SUCCESS;
	}
	/**
	 *  更改节点组信息，节点组的编辑添加
	 */
	public void updateNodeGroup(){
		log.info("[AssetGroupAction] enter updateAssetGroup...");


		List<String> fieldList = new ArrayList<String>();
		fieldList.add(nodeGroup.getNodeGroupName() + "("
				+ nodeGroup.getNodeGroupName() + ")");
		
		if(nodeGroup.getNodeGroupId()==0){
			nodeGroup.setNodeGroupCreateDateTime(new Date());
			nodeGroup.setNodeGroupUpdateDateTime(new Date());
			//默认的图片
			nodeGroup.setUrl("available/router.png");
			nodeGroupManager.insertNodeGroup(nodeGroup);
			// 审计日志
						auditManager.insertByInsertOperator(((User) this.getSession()
								.getAttribute("SOC_LOGON_USER")).getUserId(), "级联节点", super
								.getRequest().getRemoteAddr(), fieldList);

						// syslog
						/*String logString = "";

						logString = "登录名:"
								+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
										.getUserLoginName() + " 源IP:"
								+ getRequest().getRemoteAddr() + " 操作时间:"
								+ DateUtil.curDateTimeStr19() + " 操作类型 :添加级联节点";
						logManager.writeSystemAuditLog(logString);*/
						logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
								.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增级联节点");
		}else{
			
			
			nodeGroup.setNodeGroupUpdateDateTime(new Date());
			nodeGroupManager.updateNodeGroup(nodeGroup);
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "级联节点", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :修改级联节点";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改级联节点");
			
		}
		
		
		
		
		
		
		try {
			String script = "<script language=javascript>parent.parent.mainFrame.location.href = "
					+ "'/soc/nodeGroup/editnodeGroup.action';"
					+ "parent.location.reload();" + "</script>";

			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 删除节点
 * @return
 */
public void deleteNodeGroup(){
	log.info("[NodeGroupAction] enter deleteNodeGroup..");
	List<String> fieldList = new ArrayList<String>();

	if(StringUtil.isNotEmpty(nodeGroupId)){
		NodeGroup ng = nodeGroupManager.queryById(Long.parseLong(nodeGroupId));
		fieldList.add(ng.getNodeGroupName()+"("+ng.getNodeGroupName() + ")");
		nodeGroupManager.deleteNode(Long.parseLong(nodeGroupId));
	}
	
	// 审计日志
			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "级联节点", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :删除节点";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除级联节点");
			
			try {
				String script = "<script language=javascript>parent.parent.mainFrame.location.href = "
						+ "'/soc/nodeGroup/editnodeGroup.action';"
						+ "parent.location.reload();" + "</script>";

				super.getResponse().getWriter().print(script);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}	
	/**
	 * <级联管理中的一级拓扑图action>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String nodeGroupNetworkTopolog(){
		Map map = new HashMap();
		  List<NodeGroup> nodeGroups = this.nodeGroupManager.queryAll();
		  //把nodegroup类变成拓扑的node类 因为nodeGroup类中有null的对象 变成json的时候报错
		  List<Node>  nodes = new ArrayList<Node>();
		for (NodeGroup nodeGroup : nodeGroups) {
			Node node = new Node();
			node.setAssetIp(nodeGroup.getNodeIp());
			node.setFatherId((int) nodeGroup.getNodeGroupParentId());
			node.setId((int) nodeGroup.getNodeGroupId());
			node.setLeft(nodeGroup.getLeft());
			node.setLoginName(nodeGroup.getLoginName());
			node.setName(nodeGroup.getNodeGroupName());
			node.setTop(nodeGroup.getTop());
			node.setUrl(nodeGroup.getUrl());
			nodes.add(node);
		}
		
	 	nt=new NodeTree();
			for (Node node : nodes) {
				String ip = node.getAssetIp();//获得ip
				if (StringUtil.isNotEmpty(ip)) {
					//nmap探测是否通畅
					if (IpFilterUtil.checkIpIsOnline(ip)) {
						node.setState(0);
						node.setUrl(node.getUrl().replaceAll("^unavailable", "available"));
					}else {
						node.setState(1);
						node.setUrl(node.getUrl().replaceAll("^available", "unavailable"));
					}
			}
				nt.getNodeList().add(node);
			}
			this.JsonSrc = JSONObject.fromObject(nt).toString();
		return SUCCESS;
	}
	   /**
     * <从拓扑界面点详情调到的系统信息界面>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public  void savePosition(){
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("id", id);
    	map.put("top", top);
    	map.put("left", left);
    	nodeGroupManager.updatePositionById(map);
    }
	
	
	public String getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(String htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}

	public NodeGroupService getNodeGroupManager() {
		return nodeGroupManager;
	}

	public void setNodeGroupManager(NodeGroupService nodeGroupManager) {
		this.nodeGroupManager = nodeGroupManager;
	}

	public NodeGroup getNodeGroup() {
		return nodeGroup;
	}

	public void setNodeGroup(NodeGroup nodeGroup) {
		this.nodeGroup = nodeGroup;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getNodeGroupId() {
		return nodeGroupId;
	}
	public void setNodeGroupId(String nodeGroupId) {
		this.nodeGroupId = nodeGroupId;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	public String getJsonSrc() {
		return JsonSrc;
	}
	public void setJsonSrc(String jsonSrc) {
		JsonSrc = jsonSrc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}

}
