package com.soc.dao.events.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.events.EventLibraryDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.EventLibrary;

/**
 * 事件库Dao实现类 对事件树的表（tbl_event_Library）进行操作
 * 
 * @author 何贝贝
 * 
 */
public class EventLibraryDaoIbatis extends BaseDaoIbatis implements EventLibraryDao {

	/**
     * {@inheritDoc}
     */
	public int count(Map map) {
		
		Object ob = null;
		
		// 根据map中存储的条件查询符合条件的事件列表的记录数
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"eventLibrary.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 总条数
		int totalRows = 0;
		
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}
		return totalRows;
	}

	@Override
	public int insertEventLibrary(EventLibrary eventLibrary) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.eventLibrary", eventLibrary);
		return 0;
	}

	/**
     * {@inheritDoc}
     */
	@Override
    public List<EventLibrary> query(Map map, int startRow, int pageSize)
    {
         return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"eventLibrary.query", map, startRow,	pageSize);
    }

	@Override
	public int updateEventLibrary(EventLibrary eventLibrary){
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"update.eventLibrary", eventLibrary);
		return 0;
	}

	@Override
	public int deleteEventLibraryById(String... ids) {
		
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<ids.length;i++) {
			sb.append( "'"+ids[i] + "',");
		}
		sb.deleteCharAt(sb.length() - 1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("eventLibraryId", sb.toString());
		return this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"delete.eventLibrary", map);
	}

}
