package com.soc.service.monitor.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorNetworkTopologyDao;
import com.soc.model.monitor.Node;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.MonitorNetworkTopologyService;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonitorNetworkTopologyServiceImpl extends BaseServiceImpl implements MonitorNetworkTopologyService
{
    
    private MonitorNetworkTopologyDao monitorNetworkTopologyDao;

	@Override
	public List<Node> queryAllNode() {
		return monitorNetworkTopologyDao.queryAllNode();
	}
	
	@Override
	public Node queryById(int id) {
		return monitorNetworkTopologyDao.queryById(id);
	}

	public MonitorNetworkTopologyDao getMonitorNetworkTopologyDao() {
		return monitorNetworkTopologyDao;
	}

	public void setMonitorNetworkTopologyDao(
			MonitorNetworkTopologyDao monitorNetworkTopologyDao) {
		this.monitorNetworkTopologyDao = monitorNetworkTopologyDao;
	}

	@Override
	public void updateStatus(Node node) {
		monitorNetworkTopologyDao.updateStatus(node);
	}

	@Override
	public void updatePositionById(Map<String, Integer> map) {
		monitorNetworkTopologyDao.updatePositionById(map) ;
		
	}

	
    
   
    
}
