package com.soc.service.events.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.soc.dao.events.NotAnalyticEventsDao;
import com.soc.model.asset.Asset;
import com.soc.model.events.NotAnalyticEvents;
import com.soc.model.user.User;
import com.soc.service.events.NotAnalyticEventsService;
import com.util.GetDateUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class NotAnalyticEventsServiceImpl implements NotAnalyticEventsService
{
    private NotAnalyticEventsDao notAnalyticEventsDao;
    
    /**
     * {@inheritDoc}
     */
    public void insertNotAnalyticEvents(NotAnalyticEvents notAnalyticEvents)
    {
        notAnalyticEventsDao.insertNotAnalyticEvents(notAnalyticEvents);
    }
    
    /**
     * {@inheritDoc}
     */
    public NotAnalyticEvents queryNotAnalyticEvents(Map<String, Object> map)
    {
        return notAnalyticEventsDao.queryNotAnalyticEvents(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public int existsTable(String tableName)
    {
        return notAnalyticEventsDao.existsTable(tableName);
    }
    
    /**
     * {@inheritDoc}
     */
    public String existsSeq(String seqName)
    {
        return notAnalyticEventsDao.existsSeq(seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createNotAnalyticEventsSeq(String seqName)
    {
        notAnalyticEventsDao.createNotAnalyticEventsSeq(seqName);
    }
    
    /**
     * {@inheritDoc}
     */
    public void createNotAnalyticEventsTable(Map<String, String> map)
    {
        notAnalyticEventsDao.createNotAnalyticEventsTable(map);
    }
    
    public NotAnalyticEventsDao getNotAnalyticEventsDao()
    {
        return notAnalyticEventsDao;
    }
    
    public void setNotAnalyticEventsDao(NotAnalyticEventsDao notAnalyticEventsDao)
    {
        this.notAnalyticEventsDao = notAnalyticEventsDao;
    }
    
    /** {@inheritDoc} */
    
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        return notAnalyticEventsDao.count(map);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public SearchResult query(Map map, Page page)
    {
        // TODO Auto-generated method stub
        int rowsCount = notAnalyticEventsDao.count(map);
        
        page.setTotalCount(rowsCount);
        
        List<NotAnalyticEvents> list = notAnalyticEventsDao.query(map, page.getStartIndex(), page.getPageSize());
        
        // 对查找的用户列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
    }


	@Override
	public SearchResult queryAssetList(Map map, Page page) {
        
        String assetId=this.queryListOfAssetID((Integer) map.get("day"));
    	map.put("assetId", assetId);
    	
    	int rowsCount = notAnalyticEventsDao.countOfAsset(map);
        
        page.setTotalCount(rowsCount);
        
        List<Asset> list=notAnalyticEventsDao.queryAssetList(map, page.getStartIndex(), page.getPageSize());
		
                
        // 对查找的用户列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
	}
	
	 /**
     * <查询近几天的未解析信息信息的数目>
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int countOfAsset(Map map){
    	String assetId=this.queryListOfAssetID((Integer) map.get("day"));
    	map.put("assetId", assetId);
    	return notAnalyticEventsDao.countOfAsset(map);
    }

	@Override
	public String queryListOfAssetID(int day) {
		List<Integer> list = new ArrayList<Integer>();
		
		List<String> strlist = GetDateUtil.getDate(day);
		
		Set<Integer> set=new HashSet<Integer>();
		Map tableName =new HashMap(); 
		for (String string : strlist) {
			
			int flag = notAnalyticEventsDao.existsTable(string);
			
			if (flag !=0) {
				
				tableName.put("tableName", string);
				
				set.addAll(notAnalyticEventsDao.queryListOfAssetID(tableName));
			}
		}
		list.addAll(set);
		StringBuffer sb= new StringBuffer();
    	for (int i = 0; i < list.size(); i++) {
			if (i!=0) {
				sb.append(",");
			}
			sb.append(list.get(i));
		}
    	String assetId=sb.toString();
		return assetId;
	}

	@Override
	public SearchResult queryAllNotAnaly(Map map, Page page) {
		  SearchResult sr =new SearchResult() ;
			String tblname=(String) map.get("tableName");
			StringBuffer sbf= new StringBuffer();
//			 String selnotAnalyticEventsAssetName=(String) map.get("selnotAnalyticEventsAssetName");
//			   //高级查询 采集器
//			   String selnotAnalyticEventsCollectorName = (String) map.get("selnotAnalyticEventsCollectorName");
//			   //高级查询 时间详情
//			  String selnotAnalyticEventsContent = (String) map.get("selnotAnalyticEventsContent");
			Map map1 = new  HashMap();
			int rowsCount = this.queryAllNotAnalyCont(map);
			sbf.append("select * from  "+tblname+"  where 1=1 ");
			String [] tblnames =tblname.split(",");
			for (int i = 0; i < tblnames.length; i++) {
				if(map.get("selnotAnalyticEventsContent")!=null){
					sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_CONTENT\" LIKE '%");
					sbf.append((String)map.get("selnotAnalyticEventsContent"));
					sbf.append("%'");
				}
				if(map.get("selnotAnalyticEventsCollectorName")!=null){
					sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_COLLECTOR_NAME\" LIKE '%");
					sbf.append((String)map.get("selnotAnalyticEventsCollectorName"));
					sbf.append("%'");
				}
				if(map.get("selnotAnalyticEventsAssetName")!=null){
					sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_ASSET_NAME\" LIKE '%");
					sbf.append((String)map.get("selnotAnalyticEventsAssetName"));
					sbf.append("%'");
				}
				if(map.get("assetId")!=null){
					sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_ASSET_ID\" in(");
					sbf.append(map.get("assetId").toString()+")");
				}
			}
			map1.put("sql", sbf.toString());
			
			List<NotAnalyticEvents> list= notAnalyticEventsDao.queryAllNotAnaly(map1, page.getLastIndex(),  page.getPageSize());
			 page.setTotalCount(rowsCount);
			sr.setList(list);
		        sr.setPage(page);
			return sr;
				
	}

	@Override
	public int queryAllNotAnalyCont(Map map) {
		String tblname=(String) map.get("tableName");
		StringBuffer sbf= new StringBuffer();
		Map map1 = new  HashMap();
		sbf.append("select count(*) from  "+tblname+"  where 1=1 ");
		String [] tblnames =tblname.split(",");
		for (int i = 0; i < tblnames.length; i++) {
			if(map.get("selnotAnalyticEventsContent")!=null){
				sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_CONTENT\" LIKE '%");
				sbf.append((String)map.get("selnotAnalyticEventsContent"));
				sbf.append("%'");
			}
			if(map.get("selnotAnalyticEventsCollectorName")!=null){
				sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_COLLECTOR_NAME\" LIKE '%");
				sbf.append((String)map.get("selnotAnalyticEventsCollectorName"));
				sbf.append("%'");
			}
			if(map.get("selnotAnalyticEventsAssetName")!=null){
				sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_ASSET_NAME\" LIKE '%");
				sbf.append((String)map.get("selnotAnalyticEventsAssetName"));
				sbf.append("%'");
			}
			if(map.get("assetId")!=null){
				sbf.append("AND "+tblnames[i]+".\"NOT_ANALYTIC_EVENT_ASSET_ID\" in(");
				sbf.append(map.get("assetId").toString()+")");
			}
		}
		map1.put("sql", sbf.toString());
		return notAnalyticEventsDao.queryAllNotAnalyCont(map1);
	}

	
    
}
