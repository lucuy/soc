package com.soc.dao.events.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.events.EventTreeDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.EventTree;
/**
 * 事件树Dao实现类
 * 对事件树的表（tbl_event_tree）进行操作
 * @author 何贝贝
 *
 */
public class EventTreeDaoIbatis extends BaseDaoIbatis implements EventTreeDao {

	@Override
	public int insertEventTree(List<EventTree> eventTrees) {
		for (EventTree eventTree : eventTrees)
			this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.eventTree",eventTree);
		return 0;
	}

	@Override
	public List<EventTree> selectEventTree() {
		return (List<EventTree>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.eventTree");
	}

	@Override
	public int updateEventTree(List<EventTree> eventTrees) {
		for (EventTree eventTree : eventTrees) 
			this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"update.eventTree",eventTree);
		return 0;
	}
	@Override
	public int deleteEventTreeById(int...ids){
		StringBuffer sb=new StringBuffer();
		for (int i : ids) {
			sb.append(i+",");
		}
		sb.deleteCharAt(sb.length()-1);
		//System.out.println(sb);
		Map<String,String> map=new HashMap<String, String>();
		map.put("id", sb.toString());
		return this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"delete.eventTree", map);
	}
	
	public EventTree selectEventTreeById(int id){
		Map<String,Integer> map=new HashMap<String, Integer>();
		
		map.put("id", id);
		return (EventTree)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.eventTree",map);
	}
	
}
