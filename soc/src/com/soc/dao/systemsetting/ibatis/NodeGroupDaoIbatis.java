package com.soc.dao.systemsetting.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.NodeGroupDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.NodeGroup;

public class NodeGroupDaoIbatis extends BaseDaoIbatis implements NodeGroupDao {

	@Override
	public List<NodeGroup> queryByParentId(Map map) {
		
		 return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"nodeGroup.queryByParentId",map);
	}

	@Override
	public void deleteNode(long nodeGroupId) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"nodeGroup.delete", nodeGroupId);
		
	}

	@Override
	public void insertNodeGroup(NodeGroup nodeGroup) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"nodeGroup.insert", nodeGroup);
		
	}

	@Override
	public void updateNodeGroup(NodeGroup nodeGroup) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"nodeGroup.update",nodeGroup);
		
	}

	@Override
	public NodeGroup queryById(long id) {
		
		return (NodeGroup) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"nodeGroup.queryByid", id);
	}
	@Override
	public List<NodeGroup> queryAll() {
		 return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"nodeGroup.queryAll");
	}

	@Override
	public void updatePositionById(Map<String, Integer> map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"nodeGroup.updatePositionById",map);
	}

}
