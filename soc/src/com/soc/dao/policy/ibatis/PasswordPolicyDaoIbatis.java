package com.soc.dao.policy.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.policy.PasswordPolicyDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.user.User;

/**
 * 
 * <密码策略Dao实现类> <查询密码策略条数,修改或者新增密码策略>
 * 
 * @author 曹理冬
 * @version [V100R001C001]
 * @see [passwordPolicy]
 * @since [soc v3.6.0.1]
 */
public class PasswordPolicyDaoIbatis extends BaseDaoIbatis implements PasswordPolicyDao
{
    
    /**
     * <查询条数> <功能详细描述>
     * @param map Map
     * @return Int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的密码策略的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"passwordPolicy.count", map);
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
     * {@inheritDoc}按照条件查询密码策略条数
     */
    public List<PasswordPolicy> query(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"passwordPolicy.query", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}按照条件查询密码策略条数
     */
    public List<PasswordPolicy> query(Map map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"passwordPolicy.queryEx", map);
    }
    
    /**
     * {@inheritDoc}由Id查询密码策略信息
     */
    public PasswordPolicy queryById(long passwordPolicyId)
    {
        return (PasswordPolicy)super.queryForObject(GlobalConfig.sqlId+"passwordPolicy.queryById", passwordPolicyId);
    }
    
    /**
     * {@inheritDoc}新增密码策略信息
     */
    public long insert(PasswordPolicy passwordPolicy)
    {
        long passwordPolicyId = 0;
        Object obj = super.create(GlobalConfig.sqlId+"passwordPolicy.insert", passwordPolicy);
        if (obj != null)
        {
            passwordPolicyId = Long.parseLong(obj.toString());
        }
        return passwordPolicyId;
    }
    
    /**
     * {@inheritDoc}更新密码策略信息
     */
    public void update(PasswordPolicy passwordPolicy)
    {
        super.update(GlobalConfig.sqlId+"passwordPolicy.update", passwordPolicy);
    }
    
    /**
     * {@inheritDoc}根据用户ID删除用户
     */
    public void deletePasswordPolicy(long passwordPolicyId)
    {
        super.delete(GlobalConfig.sqlId+"passwordPolicy.delete", passwordPolicyId);
    }
    
    /**
     * {@inheritDoc}根据策略名查询
     */
    public List<PasswordPolicy> queryByPasswordPolicyName(String passwordPolicyName)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"passwordPolicy.queryBypasswordPolicyName",passwordPolicyName);
    }
    
    /**
     * {@inheritDoc}更新状态
     */
    public void updatePasswordPolicyStatus(Map map)
    {
        
        super.update(GlobalConfig.sqlId+"passwordPolicy.updatePasswordPolicyStatus", map);
        
    }
    
    /**
     * {@inheritDoc}排序
     */
	public List<PasswordPolicy> queryBysort(String str, int startRow,
			int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"passwordPolicy.sort",str,startRow,pageSize);
	}
	/**
	 * 根据密码策略Id查询所有与之关联的用户
	 */
	public List<User> queryUserByPasswordPolicyId(long id) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"passwordPolicy.queryuser",id);
	}
    
}
