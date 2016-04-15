package com.soc.service.asset.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetProbeTaskDao;
import com.soc.model.asset.AssetProbeTask;
import com.soc.service.asset.AssetProbeTaskService;

import com.soc.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 资产service实现类
 * 
 * @author 张浩磊
 * 
 */
public class AssetProbeTaskServiceImpl extends BaseServiceImpl implements
		AssetProbeTaskService {

	// 资产探测管理业务类
	private AssetProbeTaskDao assetProbeTaskDao;

	public AssetProbeTaskDao getAssetProbeTaskDao() {
		return assetProbeTaskDao;
	}

	public void setAssetProbeTaskDao(AssetProbeTaskDao assetProbeTaskDao) {
		this.assetProbeTaskDao = assetProbeTaskDao;
	}
	
	

	@Override
	public long insertTask(AssetProbeTask task) {

		return assetProbeTaskDao.insertTask(task);
	}

	@Override
	public SearchResult queryProbe(Page page) {
		// TODO Auto-generated method stub
		
		if(page != null){
	    int count = count() ;
	    page.setTotalCount(count);
		List list =  assetProbeTaskDao.queryProbe(page.getStartIndex(),page.getPageSize());
		SearchResult result = new SearchResult() ;
		result.setList(list);
		result.setPage(page);
		return result ; 
		}
		
		return null ;
	}

	@Override
	public SearchResult queryName(Page page , String keyword) {
		if(page != null){
			int count = getCount(keyword);
			page.setTotalCount(count);
			List<Map> list = assetProbeTaskDao.queryName(page.getStartIndex(),page.getPageSize(),keyword);
			SearchResult result = new SearchResult() ; 
			result.setList(list) ; 
			result.setPage(page);
			return result ; 
		}
		
		return null ; 

	}

	@Override
	public void updateTask(AssetProbeTask task) {
		// TODO Auto-generated method stub
		assetProbeTaskDao.updateTask(task);
	}

	@Override
	public void deleteTask(int taskId) {
		assetProbeTaskDao.deleteTask(taskId);

	}

	@Override
	public AssetProbeTask queryByIdTask(int taskId) {
		return assetProbeTaskDao.queryByIdTask(taskId);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return assetProbeTaskDao.count(null);
	}

	@Override
	public SearchResult queryAllTask(Page page) {
		// TODO Auto-generated method stub
		List<java.util.Map> list = new ArrayList<java.util.Map>() ; 
		if(page != null){
		    int count = count() ;
		    page.setTotalCount(count);
		    list =  assetProbeTaskDao.queryAllTask(page.getStartIndex(),page.getPageSize());
		    SearchResult result = new SearchResult() ;
			result.setList(list);
			result.setPage(page);
			return result ; 
			}
			
			return null ;
	}

	@Override
	public int getCount(String keyword) {
		// TODO Auto-generated method stub
		return assetProbeTaskDao.queryNumLeftJoin(keyword);
	}

	@Override
	public int getProCount(Map map) {
		// TODO Auto-generated method stub
		return assetProbeTaskDao.getProCount(map);
	}

	@Override
	public SearchResult queryPro(Page page, Map map) {
		// TODO Auto-generated method stub
		List<java.util.Map> list = new ArrayList<java.util.Map>() ; 
		if(page != null){
		   int count = 	getProCount(map);
		   page.setTotalCount(count);
		   list = assetProbeTaskDao.queryPro(map, page.getStartIndex(), page.getPageSize());
		   SearchResult result = new SearchResult() ;
		   result.setList(list);
		   result.setPage(page);
			return result ; 
		}
		return null;
	}
}
