package com.soc.service.role.impl;

import java.util.List;

import com.soc.dao.role.PermissionDao;
import com.soc.model.role.Permission;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.role.PermissionService;

/**
 * 
 * <权限Service实现类>
 * <对权限的相关操作：查询权限>
 * 
 * @author  王亚男
 * @version  [V100R001C001, 2012-8-15]
 * @see  [相关类/方法]
 * @since 
 */
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService
{
    
    //权限Dao对象
    private PermissionDao permissionDao;
    
    /**
     * {@inheritDoc}
     */
    public List<Permission> queryPermission()
    {
        List<Permission> list = permissionDao.queryPermission();
        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public Permission queryByPermissionId(long permissionId)
    {
        return permissionDao.queryByPermissionId(permissionId);
    }

    public PermissionDao getPermissionDao()
    {
        return permissionDao;
    }

    public void setPermissionDao(PermissionDao permissionDao)
    {
        this.permissionDao = permissionDao;
    }
    
}