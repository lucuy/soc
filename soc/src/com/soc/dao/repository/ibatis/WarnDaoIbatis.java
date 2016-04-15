package com.soc.dao.repository.ibatis;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.repository.WarnDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;
import com.soc.model.warn.Warn;
/**
 * 
 * <预警发布实现类>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WarnDaoIbatis extends BaseDaoIbatis implements WarnDao
{
    /**
     * {@inheritDoc}查询所有预警
     */
    @Override
    public List<Warn> findAllWarn()
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"warn.query");
    }
    
    /**
     * {@inheritDoc}新增预警
     */
    @Override
    public int insertWarn(Warn warn)
    {
        // TODO Auto-generated method stub
        int i = 0;
        try
        {
            this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"warn.insert", warn);
            i = 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }
    
    /**
     * {@inheritDoc}通过warnId详细查询预警
     */
    @Override
    public Warn queryById(Warn warn)
    {
        // TODO Auto-generated method stub
        return (Warn)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"warn.queryById", warn);
    }
    
    /**
     * {@inheritDoc}批量删除预警
     */
    @Override
    public int deleteWarn(String result)
    {
        // TODO Auto-generated method stub
        int i = 0;
        try
        {
            this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"warn.deleteWarn", result);
            i = 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return i;
    }
    
    /**
     * {@inheritDoc}修改预警
     */
    @Override
    public void updateWarn(Warn warn)
    {
        // TODO Auto-generated method stub
        this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"warn.updateWarn", warn);
    }
    
    /**
     * {@inheritDoc}查询预警总条数
     */
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        Object ob = null;
        // 根据map中存储的条件查询符合条件的密码策略的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"warn.count", map);
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
    
    /**
     * {@inheritDoc}按照条件查询预警条数
     */
    @Override
    public List<Warn> query(Map map, int startRow, int pageSize)
    {
        // TODO Auto-generated method stub
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"warn.higQuery", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}排序数据库信息
     */
	@Override
	public List<Warn> sort(String str, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"warn.sort",str,startRow,pageSize);
	}
    
}
