package com.soc.service.systemsetting.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.NodeGroupDao;
import com.soc.model.systemsetting.NodeGroup;
import com.soc.service.systemsetting.NodeGroupService;

public class NodeGroupServiceImpl implements NodeGroupService {
	private NodeGroupDao nodeGroupDao;
	@Override
	public List<NodeGroup> queryByParentId(Map map) {
		return nodeGroupDao.queryByParentId(map);
	}

	@Override
	public void deleteNode(long nodeGroupId) {
		nodeGroupDao.deleteNode(nodeGroupId);

	}

	@Override
	public void insertNodeGroup(NodeGroup nodeGroup) {
		nodeGroupDao.insertNodeGroup(nodeGroup);

	}

	@Override
	public void updateNodeGroup(NodeGroup nodeGroup) {
		nodeGroupDao.updateNodeGroup(nodeGroup);

	}

	public NodeGroupDao getNodeGroupDao() {
		return nodeGroupDao;
	}

	public void setNodeGroupDao(NodeGroupDao nodeGroupDao) {
		this.nodeGroupDao = nodeGroupDao;
	}

	@Override
	public NodeGroup queryById(long id) {
		
		return nodeGroupDao.queryById(id);
	}

	@Override
	public NodeGroup insert(String type, long id) {
		NodeGroup thisGroup;
		NodeGroup nodeGroup = new NodeGroup();
		if(type.equals("b")){
			// 增加同级相同的上级
			thisGroup=this.queryById(id);
			nodeGroup.setNodeGroupParentId(thisGroup.getNodeGroupParentId());
			nodeGroup.setParentGroup(thisGroup.getParentGroup());
		}else if(type.equals("s")){
			thisGroup=this.queryById(id);
			nodeGroup.setNodeGroupParentId(id);
			nodeGroup.setParentGroup(thisGroup);
		}else if(type.equals("r")){
			nodeGroup.setNodeGroupParentId(0);
		}
		return nodeGroup;
	}

	@Override
	public List<NodeGroup> queryAll() {
		return nodeGroupDao.queryAll();
	}

	@Override
	public void updatePositionById(Map<String, Integer> map) {
		nodeGroupDao.updatePositionById(map);
		
	}

}
