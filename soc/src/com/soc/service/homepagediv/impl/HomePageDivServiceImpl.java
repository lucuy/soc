package com.soc.service.homepagediv.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.homepagediv.HomePageDao;
import com.soc.model.home.HomeDiv;
import com.soc.model.home.HomePageDiv;
import com.soc.service.homepagediv.HomePageDivService;

public class HomePageDivServiceImpl implements HomePageDivService {
	private HomePageDao homePageDao;

	@Override
	public void saveHomePageDiv(String homePageDiv,long userId) {
		Map map = new HashMap();
		map.put("userId", userId);
		String[] statuses = homePageDiv.split(",");
		for (int i = 0; i < statuses.length; i++) {
			map.put("id","d"+(i+1) );
			map.put("status", Integer.parseInt(statuses[i]));
			if ("0".equals(statuses[i])) {
				map.put("y", 0);
				map.put("x", ((int)(Math.random()*100))%2);
				homePageDao.updateHomePageDiv(map);
				map.remove("y");
			}else {
				homePageDao.saveHomePageDiv(map);
			}
		
		}
	
	}

	
	@Override
	public List<HomePageDiv> queryHomePageDiv(long userId) {
		List<HomePageDiv> list = homePageDao.queryHomePageDiv(userId);
		for (HomePageDiv homePageDiv : list) {
			homePageDiv.setDivContext(HomeDiv.map.get(homePageDiv.getId()));
		}
		return 	list;
	}


	@Override
	public void savePosition(Map map) {
		homePageDao.savePosition(map);
	}

	public HomePageDao getHomePageDao() {
		return homePageDao;
	}

	public void setHomePageDao(HomePageDao homePageDao) {
		this.homePageDao = homePageDao;
	}


	@Override
	public void updateHomePageDiv(Map map) {
		homePageDao.updateHomePageDiv(map);
		
	}


	@Override
	public void insertDiv(HomePageDiv homePageDiv) {
		homePageDao.insertDiv(homePageDiv);
		
	}


	@Override
	public void deleteDivByUserId(int userId) {
		homePageDao.deleteDivByUserId(userId);
		
	}

}
