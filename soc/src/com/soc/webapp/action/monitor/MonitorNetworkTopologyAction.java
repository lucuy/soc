package com.soc.webapp.action.monitor;
/**

 * 网络拓扑的action
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.soc.model.asset.Asset;
import com.soc.model.monitor.Node;
import com.soc.model.monitor.NodeTree;
import com.soc.service.asset.AssetService;
import com.soc.service.monitor.MonitorNetworkTopologyService;
import com.soc.webapp.action.BaseAction;
import com.util.IpFilterUtil;
import com.util.StringUtil;

public class MonitorNetworkTopologyAction extends BaseAction
{
	//前台需要的jetsen字符串
	private String JsonSrc;
	//node树的类
	private NodeTree nt;
	//节点list 后台查出来的
	private List<Node> nodes;
	//拓扑的Manager类
	private MonitorNetworkTopologyService monitorNetworkTopologyManager;
	//前台传过来的id
	private int id;
    //位置 top
    private int top;
    //位子 left
    private int left;
 // 资产的业务处理类
 	private AssetService assetManager;
 // 资产实体类
 	private Asset asset;
	/**
	 * <显示网络拓扑图action>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    public String networkTopology(){
    	nodes = monitorNetworkTopologyManager.queryAllNode();
    	nt=new NodeTree();
		for (Node node : nodes) {
			String ip = node.getAssetIp();//获得ip
			if (StringUtil.isNotEmpty(ip)) {
				//nmap探测是否通畅
				if (IpFilterUtil.checkIpIsOnline(ip)) {
					//判断联通状态修改库
					node.setState(0);
					String s = node.getUrl().replaceAll("^unavailable", "available");
					node.setUrl(s);
				}else {
					node.setState(1);
					node.setUrl(node.getUrl().replaceAll("^available", "unavailable"));
				}
				//更新更新状态
				monitorNetworkTopologyManager.updateStatus(node);
		}
			/*//获得设备的通畅状态
			if (node.getState()==1) {
				node.setUrl(node.getUrl().replace("available", "unavailable"));
			}*/
			nt.getNodeList().add(node);
		}
		this.JsonSrc = JSONObject.fromObject(nt).toString();
        return SUCCESS;
    }
    /**
     * <保存位置的方法>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public  void savePosition(){
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("id", id);
    	map.put("top", top);
    	map.put("left", left);
    	monitorNetworkTopologyManager.updatePositionById(map);
    }
    /**
     * <根据id获得资产的详细信息然后跳到apm界面>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public  void getAssetById(){
    	asset = assetManager.searchById(id);
    }

	public String getJsonSrc() {
		return JsonSrc;
	}
	public void setJsonSrc(String jsonSrc) {
		JsonSrc = jsonSrc;
	}
	public NodeTree getNt() {
		return nt;
	}
	public void setNt(NodeTree nt) {
		this.nt = nt;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public MonitorNetworkTopologyService getMonitorNetworkTopologyManager() {
		return monitorNetworkTopologyManager;
	}
	public void setMonitorNetworkTopologyManager(
			MonitorNetworkTopologyService monitorNetworkTopologyManager) {
		this.monitorNetworkTopologyManager = monitorNetworkTopologyManager;
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
	public AssetService getAssetManager() {
		return assetManager;
	}
	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public Asset getAsset() {
		return asset;
	}

	
}