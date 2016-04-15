package com.soc.dao.monitor.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorGroupDao;
import com.soc.dao.monitor.MonitorNetworkTopologyDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorGroup;
import com.soc.model.monitor.Node;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-11-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonitorNetworkTopologyDaoIbatis extends BaseDaoIbatis implements MonitorNetworkTopologyDao
{

	@Override
	public List<Node> queryAllNode() {
		return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"monitorNetworkTopologyDao.queryAllNode");
	}

	@Override
	public Node queryById(int id) {
		return (Node) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"monitorNetworkTopologyDao.queryById",id);
	}

	@Override
	public void updateStatus(Node node) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"monitorNetworkTopologyDao.updateStatus", node);
	}


	@Override
	public void updatePositionById(Map<String, Integer> map) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"monitorNetworkTopologyDao.updatePositionById", map);
		
	}
    
 
    
}
