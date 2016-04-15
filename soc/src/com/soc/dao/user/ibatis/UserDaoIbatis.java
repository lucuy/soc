package com.soc.dao.user.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.user.UserDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.policy.TimePolicy;
import com.soc.model.role.Role;
import com.soc.model.user.User;

/**
 * 
 * Description: 用户Dao的实现类
 * <对用户的相关操作：添加用户、修改用户、删除用户、查询用户、为用户添加关联密码、时间、地址策略、为用户添加相关角色>
 * 
 * @author  王亚男
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since V100R001C001
 */
public class UserDaoIbatis extends BaseDaoIbatis implements UserDao
{
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"user.count", map);
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
     * {@inheritDoc}
     */
    public List<User> query(Map map, int startRow, int pageSize)
    {
       return super.queryForList(GlobalConfig.sqlId+"user.query", map, startRow, pageSize);
    	//this.getSqlMapClientTemplate().q
        
    }
    
    /**
     * {@inheritDoc}
     */
    public List<User> query(Map map)
    {
        return super.queryForList(GlobalConfig.sqlId+"user.queryEx", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public User queryByUserId(long userId)
    {
        return (User)super.queryForObject(GlobalConfig.sqlId+"user.queryById", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<User> queryByUserLoginName(String userLoginName)
    {
        return super.queryForList(GlobalConfig.sqlId+"user.queryByUserLoginName", userLoginName);
    }
    
    /**
     * {@inheritDoc}
     */
    public long insert(User user)
    {
        long userId = 0;
        userId = (Long)super.create(GlobalConfig.sqlId+"user.insert", user);
        return userId;
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateUser(User user)
    {
        super.update(GlobalConfig.sqlId+"user.update", user);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteUser(long userId,int deleteStatus)
    {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("userId", Long.valueOf(userId));
        map.put("userIsDelete", Long.valueOf(deleteStatus));
        super.update(GlobalConfig.sqlId+"user.updateUserIsDelete", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateUserStatus(Map map)
    {
        super.update(GlobalConfig.sqlId+"user.updateUserStatus", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Role> queryRelRole(long userId)
    {
        return super.queryForList(GlobalConfig.sqlId+"user.queryRoleByUserId", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<PasswordPolicy> queryRelPasswordPolicy(long userId)
    {
        return super.queryForList(GlobalConfig.sqlId+"user.queryPasswordPolicyByUserId", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<TimePolicy> queryRelTimePolicy(long userId)
    {
        return super.queryForList(GlobalConfig.sqlId+"user.queryTimePolicyByUserId", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<AddressPolicy> queryRelAddressPolicy(long userId)
    {
        return super.queryForList(GlobalConfig.sqlId+"user.queryAddressPolicyByUserId", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelPasswordPolicy(Map map)
    {
        super.create(GlobalConfig.sqlId+"user.insert-relPasswordPolicy", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelTimePolicy(Map map)
    {
        super.create(GlobalConfig.sqlId+"user.insert-relTimePolicy", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelAddressPolicy(Map map)
    {
        super.create(GlobalConfig.sqlId+"user.insert-relAddressPolicy", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelPasswordPolicy(long userId)
    {
        super.delete(GlobalConfig.sqlId+"user.delete-relPasswordPolicy", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelTimePolicy(long userId)
    {
        super.delete(GlobalConfig.sqlId+"user.delete-relTimePolicy", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelAddressPolicy(long userId)
    {
        super.delete(GlobalConfig.sqlId+"user.delete-relAddressPolicy", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelRole(Map map)
    {
        super.create(GlobalConfig.sqlId+"user.insert-relRole", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelRole(long userId)
    {
        super.delete(GlobalConfig.sqlId+"user.delete-relRole", userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<User> exportQuery(Map map)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
     
    @Override
    public void updatePassword(Map map)
    {
        // TODO Auto-generated method stub
         super.update(GlobalConfig.sqlId+"user.updatePassword", map);
    }

    @Override
    public List<User> queryByIds(String userIds)
    {
        return super.queryForList(GlobalConfig.sqlId+"query.userIds", userIds);
    }
    
    @Override
	public List<User> queryBySort(Map map, int startRow, int pageSize) {
		return super.queryForList(GlobalConfig.sqlId+"user.sort",map,startRow,pageSize);
	}

	@Override
	public void deleteRelAssetGroup(long userid) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"user.delete-asset_group", userid);
		
	}

	@Override
	public void insertRelAssetGroup(Map map) {
		super.create(GlobalConfig.sqlId+"user.insert-relAssetGroup", map);
	}

	@Override
	public List<User> queryAll() {
		return super.queryForList(GlobalConfig.sqlId+"user.queryAll");
	}

	@Override
	public String queryUserNameById(long id) {
		Object obj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"user.queryUserNameById",id);
		if (obj!=null) {
			return obj.toString();
		}else {
			return null;
		}
		
	}

	@Override
	public long queryUserIdByName(String name) {
		//System.out.print(name);
		//name = "admin";
		Object obj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"user.queryUserIdByName",name.trim());
		if (obj!=null) {
			return Long.parseLong(obj.toString());
		}else {
			return 0;
		}
		
	}
	
	@Override
	public void updateField(Map map) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"user.updateEventFaild",map);
	}

	@Override
	public List<User> queryAssetResponsibilityUserByPrincipalSys(Map map) {
	
		return super.queryForList(GlobalConfig.sqlId+"user.queryAssetResponsibilityUserByPrincipalSys" , map) ;
	}

	@Override
	public void updateLayoutByUserId(Map map) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"user.updateLayoutByUserId", map);
		
	}

	@Override
	public int queryUserLayoutByUserId(long userId) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"user.queryUserLayoutByUserId", userId);
	}
	
}
