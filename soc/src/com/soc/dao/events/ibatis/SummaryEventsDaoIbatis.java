package com.soc.dao.events.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.soc.dao.events.SummaryEventsDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.events.SummaryEvents;

/**
 * 概要事件实现类
 * 
 * @author 王纯
 * 
 */
public class SummaryEventsDaoIbatis extends BaseDaoIbatis implements SummaryEventsDao
{
    /**
     * 查询近期事件的方法
     */
    @Override
	public List<Events> queryRecentEvents(Map<String, Object> map,
			int startIndex, int pageSize) {
    	return getSqlMapClientTemplate().queryForList(
    			GlobalConfig.sqlId+	"query.recentEvents",map,startIndex,pageSize);
	}
    /**
     * 查询近期事件的条数
     */
	@Override
	public int getRecentEventsCount(Map<String, Object> map) {
	
		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"query.recentEventsCount",map);
	}

	@Override
	public int existsTable(String tableName) {

		return (Integer)this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"query.existsTable", tableName);
	}
	@Override
    public int count(String sqlKey, Map<String, String> map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+sqlKey, map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
        
    }
    
    @Override
    public int count(String sqlKey, String sql)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+sqlKey, sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
        
    }
    
    @Override
    public int count(Map<String, Long> map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"queryRecent.count", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }
    
    @Override
    public List<SummaryEvents> querySummaryEventsByEventsType(String sqlKey, Map<String, String> data, int startRow,
        int pageSize)
    {
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqlKey, data, startRow, pageSize);
    }
    
    @Override
    public List<SummaryEvents> query(String sqlKey, Map<String, String> map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqlKey, map, startRow, pageSize);
    }
    
    @Override
    /*public long insertSummaryEvents(String sqlKey, ConcurrentLinkedQueue<SummaryEvents> temporarySummaryEventsQueue)*/
    public void insertSummaryEvents(String sqlKey, SummaryEvents summaryEvents)
    {
        
        // 注意使用同一个SqlMapClient会话  
        /*SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            // 开始事务  
            sqlMapClient.startTransaction();
            
            // 开始批处理  
            sqlMapClient.startBatch();
            
            //计数用的
            int count = 0;
            
            while (!temporarySummaryEventsQueue.isEmpty())
            {
                count++;
                // 插入操作  
                sqlMapClient.insert(sqlKey, temporarySummaryEventsQueue.poll());
            }
            // 执行批处理  
            sqlMapClient.executeBatch();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return 0;*/
        super.create(GlobalConfig.sqlId+sqlKey, summaryEvents);
        
    }
    
  
   /* public List<Map> monitorCount(String sqlKey, Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(sqlKey, map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }*/
    
    @Override
    public List<Map> monitorSummary(String sqlKey, Map map)
    {
        // TODO Auto-generated method stub
        return super.queryForList(GlobalConfig.sqlId+sqlKey, map);
    }

    @Override
    public List<Events> queryRelevanceAnalysis(String sqlKey, Map<String, String> data, int startRow, int pageSize)
    {
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqlKey, data, startRow, pageSize);
    }
    
    @Override
    public List<Events> queryRelevanceAnalysis(String sqlKey, Map<String, String> data)
    {
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqlKey, data);
    }
    
    @Override
    public void insertRelevanceAnalysis(Map<String, Object> map)
    {
        this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.relevance_analysis", map);
    }
    
    @Override
    public List<Events> queryRelevanceAnalysis(long relId, int startRow, int pageSize)
    {
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.RelevanceAnalysis_cache", relId, startRow, pageSize);
    }

    @Override
    public void emptyTable()
    {
        this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"emptyTable_cache");
    }

    @Override
    public List<Events> queryCustomEventsRules(String sqkKey, String sql, int startRow, int pageSize)
    {
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqkKey, sql, startRow, pageSize);
    }

	@Override
	public List<Map<String, Object>> queryEventDefinition() {
		return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.event.definition");
	}
	@Override
	public int getUndefinedEventsCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Events> queryUndfinedEvents(Map<String, Object> map,
			int startIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
    @Override
    public int countByassetId(String sqlKey, String sql)
    {
        // TODO Auto-generated method stub
         Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+sqlKey, sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }
    @Override
    public List<Events> queryEventsByassetIds(String sqkKey, String sql, int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
        return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqkKey, sql, startRow, pageSize);
    }
    //查询自定义监控图表
	@Override
	public List<Map> getMonitorCustomCount(Map map) {
		return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"customMonitor.query", map);
	}
	@Override
	public List<Events> queryCustomEventsRules(String sqkKey, String sql) {
		
		return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqkKey, sql);
	}
	@Override
	public List<Events> queryExportEventsByassetIds(String sqkKey, String sql) {
		// TODO Auto-generated method stub
		return super.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+sqkKey, sql);
	}
	@Override
	public List<List<Events>> queryCustomEventsRules111(String sqlKsy,List<String> list) {
		List<List<Events>> ll = new ArrayList<List<Events>>();
		List<Events> eList = new ArrayList<Events>();
        // 注意使用同一个SqlMapClient会话  
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            // 开始事务  
            sqlMapClient.startTransaction();
            
            // 开始批处理  
            sqlMapClient.startBatch();
            
            for (int i = 0; i < list.size(); i++)
            {
            	eList=	sqlMapClient.queryForList(GlobalConfig.sqlId+sqlKsy, list.get(i));
//                // 插入操作  
//                sqlMapClient.insert("vulnerability.insert", list.get(i));
            	// 对list的处理
        	/*	String eventName = "";
            	for (Events events : eList) {
    				
    				String eventNameTemp = events.getName();
    				
    				try {
    					eventName = GlobalConfig.eventTypeTag.get(Long
    							.parseLong(eventNameTemp));
    					// String s = events.getsAdd();
    					if (!eventName.equals(null)) {
    						events.setName(eventName);
    					}
    					
    				} catch (Exception e) {
    					
    					events.setName(eventNameTemp);
    					
    					//log.info("转化失败");
    				}
    				
    				String eventstype = events.getType();
    				
    				try {
    					String typeTemp = GlobalConfig.eventTypeTag.get(Long
    							.parseLong(eventstype));
    					
    					if ((typeTemp != null) && (!typeTemp.equals(""))) {
    						events.setType(typeTemp);
    					}
    					
    				} catch (Exception e) {
    					
    					events.setType(eventstype);
    					//log.info("类型转化失败");
    				}
    				try {
    					
    					events.setCustomd1(Integer.parseInt(events.getType()));
    					
    				} catch (Exception e) {
    					//log.info("类型转换错误");
    					
    					//events.setCustomd1(Long.parseLong(eventstype));
    					
    				}
    			}*/
                ll.add(eList);
            }
            
            // 执行批处理  
            sqlMapClient.executeBatchDetailed();
            //executeBatch();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // 结束事务  
                sqlMapClient.endTransaction();
                
              
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        
        return ll;
	}
    
}