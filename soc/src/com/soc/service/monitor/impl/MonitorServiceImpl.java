package com.soc.service.monitor.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorDao;
import com.soc.model.monitor.Monitor;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.MonitorService;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService
{
    
    private MonitorDao monitorDao;
    
    public MonitorDao getMonitorDao()
    {
        return monitorDao;
    }
    
    public void setMonitorDao(MonitorDao monitorDao)
    {
        this.monitorDao = monitorDao;
    }
    
    @Override
    public List<Monitor> queryAll()
    {
        // TODO Auto-generated method stub
        return monitorDao.queryAll();
    }
    
    @Override
    public Monitor queryById(long id)
    {
        // TODO Auto-generated method stub
        return monitorDao.queryById(id);
    }

    /** {@inheritDoc} */
     
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        return monitorDao.queryMonitorCount(map) ;
    }

    /** {@inheritDoc} */
     
    @Override
    public SearchResult queryMonitor(Map map, Page page)
    {
        // TODO Auto-generated method stub
        int rowsCount = monitorDao.queryMonitorCount(map);
        page.setTotalCount(rowsCount);
        List<Monitor> list = monitorDao.queryMonitorAjax(map, page.getStartIndex(), page.getPageSize());
        //对查找结果进行处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }

	@Override
	public void insertChar(Monitor monitor) {
		monitorDao.insertChar(monitor);
		
	}

	@Override
	public SearchResult queryMonitorCustom(Map<String, Object> map, Page page) {
		int rowsCount = monitorDao.countMonitorCustom(map);
		page.setTotalCount(rowsCount);
		List<Monitor> list = monitorDao.queryMonitorCustom(map,
				page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}

	@Override
	public void deleteCustom(String ids) {
		//拆分字符串
		if(!ids.equals("")){
			String [] idTemp = ids.split(",");		
			for (String id : idTemp) {
				monitorDao.deleteCustom(Long.parseLong(id));
			}
			
		}
		
		
		
	}

	@Override
	public Monitor selectMonitorById(Map map) {
		return monitorDao.selectMonitorById(map);
	} 

	@Override
	public void updateChar(Monitor monitor) {
		 monitorDao.updateChar(monitor);
		
	}

	
		
	
    
}
