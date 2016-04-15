package com.soc.dao.role.ibatis;

import java.util.List;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.role.PermissionDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.role.Permission;
import com.soc.model.user.User;

public class PermissionDaoIbatis extends BaseDaoIbatis implements PermissionDao
{
    
    /**
     * {@inheritDoc}
     */
    public List<Permission> queryPermission()
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"permission.queryPermission");
    }
    
    /**
     * {@inheritDoc}
     */
    public Permission queryByPermissionId(long permissionId)
    {
        return (Permission)super.queryForObject(GlobalConfig.sqlId+"permission.queryById", permissionId);
    }
    
}
