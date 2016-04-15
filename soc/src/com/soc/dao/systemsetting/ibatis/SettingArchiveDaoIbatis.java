package com.soc.dao.systemsetting.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.SettingArchiveDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.SettingArchive;

public class SettingArchiveDaoIbatis extends BaseDaoIbatis implements SettingArchiveDao
{
    /**
     * {查询出归档的表分页}
     */
    @Override
    public List<SettingArchive> queryArchiveList(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"queryArchiveList.query", map, startRow, pageSize);
    }
    
    @Override
    public int associationPage(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"countPageArchiveList.query", map);
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
    
    /**
     * {根据id更新状态}
     */
    @Override
    public void updataById(SettingArchive archive)
    {
        getSqlMapClientTemplate().update(GlobalConfig.sqlId+"updateArchiveById.update", archive);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createArchive(SettingArchive archive)
    {
        getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"createArchive.insert", archive);
    }
    
    @Override
    public SettingArchive queryById(long checkid)
    {
        return (SettingArchive)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"queryById.query",checkid);
    }

    @Override
    public List<SettingArchive> selectArchiveAuto(Map<String,Long> archive)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"selectArchiveAuto.query",archive);
    }
}