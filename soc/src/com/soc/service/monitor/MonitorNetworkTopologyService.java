package com.soc.service.monitor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.monitor.Node;

/**
 * 
 * <监控业务处理类>
 * <监控的查询>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MonitorNetworkTopologyService extends Serializable
{
    
    /**
     * <查询所有的节点>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Node> queryAllNode();
    /**
     * <按照id查节点>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Node queryById(int id);
	public void updateStatus(Node node);
	public void updatePositionById(Map<String, Integer> map);
}
