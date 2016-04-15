package com.soc.dao.screen.ibatis;

import java.util.List;
import java.util.Map;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.screen.ComprehensiveEmergeDao;
import com.soc.model.conf.GlobalConfig;

/**
 * 
 * <告警信息Dao的实现类> <实现告警信息的查看，列表展示，搜索，快速搜索>
 * 
 * @author zhaokui
 * @version [版本号, 2012-11-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ComprehensiveEmergeIbatis extends BaseDaoIbatis implements ComprehensiveEmergeDao{
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Map> queryFacilityEvents(Map map) {
		// 查询设备事件Top10
		return super.queryForList(GlobalConfig.sqlId+"screen.all", map);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map> querySafetyEvents(Map map) {
		// 查询安全事件Top10
		return super.queryForList(GlobalConfig.sqlId+"screen.safety",map);
	}
	
	

}
