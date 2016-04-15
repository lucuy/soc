package com.soc.dao.knowledgemanger.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.knowledgemanger.LeakDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.knowledge.Leak;

/**
 * 
 * <漏洞库的dao实现> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LeakDaoIbatis extends BaseDaoIbatis implements LeakDao {

	@Override
	public Leak selectLeakById(int id) {
		return (Leak) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"leakSQL.selectLeakById", id);
	}

	@Override
	public List<Leak> queryLeak(Map map, int start, int end) {
		return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"leakSQL.queryLeak", map,
				start, end);
	}

	@Override
	public int count(Map map) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"leakSQL.selectCount", map);
	}

	@Override
	public List<Leak> queryByName(String leakName) {

		return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"leakSQL.queryByName",
				leakName);
	}

	@Override
	public void insertLeak(Leak leak) {
		getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"leakSQL.insertLeak", leak);

	}

	@Override
	public List<Map> export(Map map) {
		return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"leakSQL.export", map);
	}

}
