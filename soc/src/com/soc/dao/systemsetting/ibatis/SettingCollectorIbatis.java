package com.soc.dao.systemsetting.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.SettingCollectorDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.model.systemsetting.Collector;

/**
 * <采集器的配置>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingCollectorIbatis extends BaseDaoIbatis implements SettingCollectorDao
{
    
    @Override
    public void setCollector(Collector collectorData)
    {
        getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"settingcollector.insert", collectorData);
    }
    
    @Override
    public List<Collector> queryCollector()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCollector.query");
    }
    
    @Override
    public List<Collector> queryCollectorTree()
    {
        return getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCollectorTree.query");
    }
    
    //树
    
    @Override
    public List<Collector> queryCollectorTree(Map<String, Integer> treeDataId)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCollector.queryTree", treeDataId);
    }
    
    @Override
    public void updateId(Collector updateData)
    {
        /*int length = updateData.size();
        // 注意使用同一个SqlMapClient会话  
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            // 开始事务  
            sqlMapClient.startTransaction();
            
            // 开始批处理  
            sqlMapClient.startBatch();
            
            while(!updateData.isEmpty())
            {
                // 插入操作  
                getSqlMapClientTemplate().update("updateCollector.update", updateData);
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
        }*/
        getSqlMapClientTemplate().update(GlobalConfig.sqlId+"updateCollector.update", updateData);
    }
    
    @Override
    public void delId(int collectorId)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("collectorId", collectorId);
        super.update(GlobalConfig.sqlId+"delcollector.del", map);
    }
    
    /**
     * {分页}
     */
    @Override
    public int countPage(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"countPage.query", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }
    
    @Override
    public List<Collector> queryPage(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCollector.query", map, startRow, pageSize);
    }
    
    public Collector queryById(long collectorId)
    {
        return (Collector)super.queryForObject(GlobalConfig.sqlId+"collector.queryById", collectorId);
    }
    
    @Override
    public List<Collector> selectMac(String addressName)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.mac", addressName);
    }
    
    /**
     * {@更新}
     */
    @Override
    public void updateById(ConcurrentLinkedQueue<Collector> updateData)
    {
        int length = updateData.size();
        // 注意使用同一个SqlMapClient会话  
        SqlMapClient sqlMapClient = this.getSqlMapClient();
        try
        {
            // 开始事务  
            sqlMapClient.startTransaction();
            
            // 开始批处理  
            sqlMapClient.startBatch();
            
          /*  while (!eventsQueue.isEmpty())
            {
                // 插入操作  
                sqlMapClient.insert("insert.events", eventsQueue.poll());
            }
            */
            while(!updateData.isEmpty())
            {
                // 插入操作  
                getSqlMapClientTemplate().update(GlobalConfig.sqlId+"updateCollector.update", updateData.poll());
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
    }

    @Override
    public void updateCollectorCount(String sql)
    {
        // 插入操作  
       this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"updateCollectorCount.update", sql);
    }

    @Override
    public void updateLoggerStatus(Collector collectorList)
    {
        this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"updateLoggerStatus.update", collectorList);
    }

    /** {@inheritDoc} */
     
    @Override
    public void updateCollectorIsOnLine(String sql)
    {
        // TODO Auto-generated method stub
        this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"updateCollectorIsOnline.update", sql);
    }

	@Override
	public List<Collector> queryCollectors(Map map) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryCollector.query", map);
	}
}