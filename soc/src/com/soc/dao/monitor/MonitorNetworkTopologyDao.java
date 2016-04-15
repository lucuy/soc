package com.soc.dao.monitor;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.monitor.MonitorGroup;
import com.soc.model.monitor.Node;

/**
 * 
 * <监控组Dao>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MonitorNetworkTopologyDao extends BaseDao
{
    
    /**
     * <查询出所有的节点>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Node> queryAllNode();
    /**
     * <按照id查节点>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]s
     */
	public Node queryById(int id);
	public void updateStatus(Node node);
	public void updatePositionById(Map<String, Integer> map);
}
