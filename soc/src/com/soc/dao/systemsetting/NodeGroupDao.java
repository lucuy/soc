package com.soc.dao.systemsetting;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.systemsetting.NodeGroup;



public interface NodeGroupDao extends BaseDao {
	/**
     * 按照父节点组Id查询
     * 根据传入的父节点组Id查询该节点组的
     * @param map Map
     * @return List<NodeGroup>
     * @see [类、类#方法、类#成员]
     */
    public List<NodeGroup> queryByParentId(Map map);
    
    /**
     * 删除节点组
     * 根据传入的节点组Id删除该节点组及下级组
     * @param nodeGroupId Long
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public void deleteNode(long nodeGroupId);
    /**
     * 新增
     * 
     * @param NodeGroup NodeGroup
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public void insertNodeGroup(NodeGroup nodeGroup);
    /**
     * 修改
     * 
     * @param NodeGroup NodeGroup
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public void updateNodeGroup(NodeGroup nodeGroup);
    
    /**
     * 查询本组
     * 
     * @param id long
     * @return NodeGroup
     * @see [类、类#方法、类#成员]
     */
    public NodeGroup queryById(long id);

	public List<NodeGroup> queryAll();

	public void updatePositionById(Map<String, Integer> map);
    
}
