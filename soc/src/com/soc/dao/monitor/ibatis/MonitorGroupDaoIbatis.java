package com.soc.dao.monitor.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.monitor.MonitorGroupDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorGroup;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-11-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonitorGroupDaoIbatis extends BaseDaoIbatis implements MonitorGroupDao
{
    
    @Override
    public List<MonitorGroup> queryByParentId(Map map)
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"monitorGroup.queryByParentId", map);
    }
    
    @Override
    public MonitorGroup queryById(long id)
    {
        // TODO Auto-generated method stub
        return (MonitorGroup)super.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"monitorGroup.queryById", id);
    }
    
    @Override
    public void updateRelMonitors(Map map)
    {
        // TODO Auto-generated method stub
        super.update(GlobalConfig.sqlId+"monitorGroup.updateMonitor", map);
    }
    
    @Override
    public List<MonitorGroup> queryall(Map map, int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"monitorGroup.queryAll", map, startRow, pageSize);
    }
    
    @Override
    public int countGroups(Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"monitorGroup.count", map);
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
    public void insertGroup(MonitorGroup monitorGroup)
    {
        // TODO Auto-generated method stub
        this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"monitorGroup.insert", monitorGroup);
    }
    
    @Override
    public void deleteGroup(Long id)
    {
        // TODO Auto-generated method stub
        super.update(GlobalConfig.sqlId+"monitorGroup.update", id);
    }
    
}
