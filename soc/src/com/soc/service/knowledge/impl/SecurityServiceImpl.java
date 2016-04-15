package com.soc.service.knowledge.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.soc.dao.knowledgemanger.SecurityDao;
import com.soc.model.knowledge.Security;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.knowledge.SecurityService;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <SecurityServer的实现> <能处理分页调用dao的一些方法>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SecurityServiceImpl extends BaseServiceImpl implements
		SecurityService {

	// SecurityDao类
	private SecurityDao securityDao;

	@Override
	public SearchResult querySecurity(Map map, Page page) {

		int rowsCount = securityDao.count(map);
		page.setTotalCount(rowsCount);
		List<Security> list = securityDao.querySecurity(map,
				page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}

	@Override
	public void deleteSecurity(String ids) {
	//拆分字符串
		if(!ids.equals("")){
			String [] idTemp = ids.split(",");		
			for (String id : idTemp) {
				securityDao.deleteSecurity(Integer.parseInt(id));
			}
			
		}
		

	}

	@Override
	public void insertSecuity(Security securty) {
		//把插入的日期变成lang型
		securityDao.insertSecuity(securty);

	}

	@Override
	public void updateSecurity(Security security) {
		securityDao.updateSecurity(security);

	}

	@Override
	public Security selectSecurityByid(int id) {

		return securityDao.selectSecurityByid(id);
	}

	public SecurityDao getSecurityDao() {
		return securityDao;
	}

	public void setSecurityDao(SecurityDao securityDao) {
		this.securityDao = securityDao;
	}

	@Override
	public SearchResult queryBySort(Map map, Page page) {
		int rowsCount = securityDao.count(map);
		page.setTotalCount(rowsCount);
		String field=(String)map.get("field");
		String sortType=(String)map.get("sortType");
		String str=" \""+field+"\""+" "+sortType;
		List<Security> list = securityDao.queryBySort(str,
				page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	 /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Map> securityExport(Map map) {
        return securityDao.securityExport(map);
    }

	@Override
	public List<Security> queryAllSecurity(Map map) {
		return securityDao.queryAllSecurity(map);
	}

	@Override
	public List<Security> securityForExport(Map map) {
		return securityDao.securityForExport(map);
	}

}
