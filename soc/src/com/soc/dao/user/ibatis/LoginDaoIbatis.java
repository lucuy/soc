package com.soc.dao.user.ibatis;

import java.util.Map;
import com.soc.dao.user.LoginDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  tiny
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since  V100R001C001
 */
public class LoginDaoIbatis extends BaseDaoIbatis implements LoginDao
{
    
    /**
     * {@inheritDoc}
     */
    public User check(Map map)
    {
        return (User)super.queryForObject(GlobalConfig.sqlId+"login.check", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void updatePassword(Map map)
    {
        super.update(GlobalConfig.sqlId+"user.updatePassword", map);
    }

    @Override
    public User checkUserName(Map map)
    {
        // TODO Auto-generated method stub
        return (User)super.queryForObject(GlobalConfig.sqlId+"login.checkUserName", map);
    }
    
}
