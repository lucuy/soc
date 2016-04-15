package com.soc.dao.knowledgemanger.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.knowledgemanger.SecurityDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.knowledge.Security;

/**
 * 
 * <SecurityDao的ibatis实现> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SecurityDaoIbatis extends BaseDaoIbatis implements SecurityDao {

	@Override
	public List<Security> querySecurity(Map map, int start, int end) {

		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"securitySQL.querySecurity",map,start,end);
	}

	@Override
	public void deleteSecurity(int id) {

		getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"securitySQL.deleteSecurity", id);
	}

	@Override
	public void insertSecuity(Security securty) {
		getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"securitySQL.insert", securty);

	}

	@Override
	public int count(Map map) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+	"securitySQL.count",map);
	}

	@Override
	public void updateSecurity(Security security) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"securitySQL.update", security);

	}

	@Override
	public Security selectSecurityByid(int id) {

		return (Security) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"securitySQL.selectSecurityByid",id);
	}
	
	@Override
	public List<Security> queryBySort(String str, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"securitySQL.sort",str,startRow,pageSize);
	}

	@Override
	public List<Map> securityExport(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"securitySQL.export",map);
	}

	@Override
	public List<Security> queryAllSecurity(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"securitySQL.queryAllSecurity",map);
	}

	@Override
	public List<Security> securityForExport(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"securitySQL.forExport",map);
	}
	
	

}
