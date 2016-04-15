package com.soc.service.role;

import java.io.Serializable;
import java.util.List;

import com.soc.model.role.Permission;

/**
 * 
 * <权限Service>
 * <对权限的相关操作：查询权限>
 * 
 * @author  王亚男
 * @version  [V100R001C001, 2012-8-15]
 * @see  [相关类/方法]
 * @since  
 */
public interface PermissionService extends Serializable
{
    /**
     * <查询所有权限>
     * <功能详细描述>
     * @return List<Permission>
     * @see [类、类#方法、类#成员]
     */
    public List<Permission> queryPermission();
    
    /**
     * <查询单个权限>
     * <功能详细描述>
     * @param permissionId long
     * @return Permission
     * @see [类、类#方法、类#成员]
     */
    public Permission queryByPermissionId(long permissionId);
    
}
