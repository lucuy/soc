package com.soc.dao.role;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.role.Role;

/**
 * <角色Dao>
 * <对角色的相关操作：添加角色、修改角色、删除角色、查询角色、拷贝角色>
 * 
 * @author  王亚男
 * @version  [V100R001C001, 2012-8-4]
 * @see  [相关类/方法]
 * @since
 */
public interface RoleDao extends BaseDao
{
    /**
     * <计算符合条件的用户记录数>
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <按条件分页查询用户>
     * <功能详细描述>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Role> queryRole(Map map, int startRow, int pageSize);
    
    
    /**
     * <按条件分页查询用户>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Role> queryRole(Map map);
    
    /**
     * <查询角色>
     * <根据ID查询角色>
     * @param roleId
     * @return Role
     * @see [类、类#方法、类#成员]
     */
    public Role queryByRoleId(long roleId);
    
    /**
     * <查询角色>
     * <根据角色名称查询>
     * @param roleName
     * @return List<Role>
     * @see [类、类#方法、类#成员]
     */
    public Role queryByRoleName(String roleName);
    
    /**
     * <添加角色>
     * <创建角色>
     * @param role
     * @return long
     * @see [类、类#方法、类#成员]
     */
    public long insertRole(Role role);
    
    /**
     * <更新角色>
     * <修改角色信息>
     * @param role
     * @return long
     * @see [类、类#方法、类#成员]
     */
    public void updateRole(Role role);
    
    /**
     * <删除角色>
     * <根据角色ID删除角色>
     * @param roleId
     * @see [类、类#方法、类#成员]
     */
    public void deleteRole(long roleId);
    
    /**
     * <添加角色关联账户>
     * <为角色添加账户数据权限>
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelUser(Map map);
    
    /**
     * <查询角色相关账户>
     * <根据角色ID查询角色相关账户>
     * @param roleId
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelUser(long roleId);
    
    /**
     * <删除角色相关账户>
     * <根据角色ID删除角色相关账户>
     * @param roleId
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public void deleteRelUser(long roleId);
    
    /**
     * <添加角色相关资产>
     * <功能详细描述>
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelAsset(Map map);
    
    /**
     * <添加角色相关资产组>
     * <功能详细描述>
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelAssetGroup(Map map);
    
    /**
     * <查询角色相关资产>
     * <根据角色ID查询角色相关资产>
     * @param roleId
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAsset(long roleId);
    
    /**
     * <查询角色相关资产组>
     * <根据角色ID查询角色相关资产>
     * @param roleId
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAssetGroup(long roleId);
    
    /**
     * 删除相关角色资产数据
     * @param int
     */
    public void deleteRelAsset(long roleId);
    
    /**
     * 删除相关角色资产组数据
     * @param int
     */
    public void deleteRelAssetGroup(long roleId);
    
    /**
     * <查询角色相关权限>
     * <根据角色ID查询角色相关资产>
     * @param roleId long
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelPermission(long roleId);
    
    /**
     * <添加角色相关权限>
     * <功能详细描述>
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelPermission(Map map);
    
    /**
     * <删除角色相关权限>
     * <功能详细描述>
     * @param roleId long
     * @see [类、类#方法、类#成员]
     */
    public void deleteRelPermission(long roleId);
    
    /**
     * <角色关联全部用户处理方法>
     * <功能详细描述>
     * @param roleId
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelUserByRoleId(long roleId);
    
    /**
     * <角色关联全部资源处理方法>
     * <功能详细描述>
     * @param roleId
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAssetByRoleId(long roleId);
    
    /**
     * <角色关联全部资产组处理方法>
     * <功能详细描述>
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAssetGroupByRoleId(long roleId);
    
    /**
     * <排序>
     * @param map
     * @param startRow
     * @param pageSize
     * @return 
     * @see [类、类#方法、类#成员]
     */
    public List<Role> queryBySort(Map map,int startRow,int pageSize);
}
