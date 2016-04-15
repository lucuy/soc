package com.soc.service.asset.impl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.system.AssetSystemDao;
import com.soc.model.asset.device.Category;
import com.soc.model.asset.system.AssetSystem;
import com.soc.model.asset.system.AssetSystemBrand;
import com.soc.service.asset.system.AssetSystemService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetSystemServiceImpl extends BaseServiceImpl implements AssetSystemService {

	private AssetSystemDao asDao;
	
	@Override
	public List<AssetSystem> queryAssetSystem(Map map) {
		return asDao.queryAssetSystem(map);
	}

	@Override
	public long insertAssetSystem(Map map) {
		return asDao.insertAssetSystem(map);
	}
	
	public AssetSystemDao getAsDao() {
		return asDao;
	}

	public void setAsDao(AssetSystemDao asDao) {
		this.asDao = asDao;
	}

	@Override
	public void insertAssetSystemBrand(Map map) {
		asDao.insertAssetSystemBrand(map);
	}

	@Override
	public String querySystemNameById(long id) {
		return asDao.querySystemNameById(id);
	}

	@Override
	public long querySystemIdByName(Map map) {
		return asDao.querySystemIdByName(map);
	}

	@Override
	public SearchResult querySystem(Map map, Page page) {
		// 按Map中存储的条件查找用户列表
		int rowsCount;
		List<AssetSystem> list;
		rowsCount = asDao.countAssetSystem(map);
		list = asDao.queryassetSystem(map, page.getStartIndex(),
			page.getPageSize());
		page.setTotalCount(rowsCount);
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public void delAssetSystemById(long id) {
		asDao.delAssetSystemById(id);
		asDao.delAssetSystemByLevel(id);
	}

	@Override
	public void delAssetSystemByLevel(long id) {
		asDao.delAssetSystemByLevel(id);
	}

	@Override
	public AssetSystem queryAssetSystemById(long id) {
		return asDao.queryAssetSystemById(id);
	}

	@Override
	public List<AssetSystem> queryVersionList(long level,long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("level", level);
		return asDao.queryAssetSystem(map);
	}

	@Override
	public List<AssetSystem> queryAssetSystemByString(Map map) {
		// TODO Auto-generated method stub
		return asDao.queryAssetSystemByString(map);
	}

	@Override
	public AssetSystem queryAssetSystemByName(String assetSystemName) {
		
		return asDao.queryAssetSystemByName(assetSystemName);
	}


	 
}
