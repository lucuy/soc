package com.soc.dao.homepagediv.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.homepagediv.HomePageDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.home.HomePageDiv;

public class HomePageDaoIbatis extends BaseDaoIbatis implements HomePageDao {

	@Override
	public void saveHomePageDiv(Map map) {
		 getSqlMapClientTemplate().update(GlobalConfig.sqlId+"homePageDiv.update", map);
	}

	@Override
	public List<HomePageDiv> queryHomePageDiv(long userId) {
		return (List<HomePageDiv>) getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"homePageDiv.query",userId);
	}

	@Override
	public void savePosition(Map map) {
		 getSqlMapClientTemplate().update(GlobalConfig.sqlId+"homePageDiv.savePosition", map);
		
	}

	@Override
	public void updateHomePageDiv(Map map) {
		 getSqlMapClientTemplate().update(GlobalConfig.sqlId+"homePageDiv.updateHomePageDiv", map);
		
	}

	@Override
	public void insertDiv(HomePageDiv homePageDiv) {
		 getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"homePageDiv.insertHomePageDiv", homePageDiv);
		
	}

	@Override
	public void deleteDivByUserId(int userId) {
		 getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"homePageDiv.deleteDivByUserId", userId);
		
	}

}
