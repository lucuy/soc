package com.soc.service.role;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.role.Role;
import com.soc.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <角色Service>
 * <对角色的相关操作：添加角色、修改角色、删除角色、查询角色、拷贝角色>
 * 
 * @author  王亚男
 * @version  [V100R001C001, 2012-8-12]
 * @see  [相关类/方法]
 * @since 
 */
public interface RoleService extends Serializable
{
    /**
     * <计算有多少条记录>
     * <功能详细描述>
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see [类、类#方法、类#成员]
     */
    public SearchResult queryRole(Map map, Page page);
    
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map Map
     * @return List<Role>
     * @see [类、类#方法、类#成员]
     */
    public List<Role> queryRole(Map map);

    /**
     * <查询角色>
     * <根据ID查询角色>
     * @param roleId long
     * @return Role
     * @see [类、类#方法、类#成员]
     */
    public Role queryByRoleId(long roleId);

    /**
     * <查询角色>
     * <根据角色名称查询>
     * @param roleName String
     * @return List<Role>
     * @see [类、类#方法、类#成员]
     */
    public Role queryByRoleName(String roleName);

    /**
     * <添加/更新角色>
     * <判断角色的ID是否为空，判断本次操作为添加还是更新操作>
     * @param role Role
     * @return long
     * @see [类、类#方法、类#成员]
     */
    public long updateRole(Role role);

    /**
     * <删除角色>
     * <根据角色ID删除角色>
     * @param roleId long
     * @see [类、类#方法、类#成员]
     */
    public void deleteRole(long roleId);
    
    /**
     * <添加角色关联账户>
     * <为角色添加账户数据权限>
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelUser(Map map);

    /**
     * <查询角色相关账户>
     * <根据角色ID查询角色相关账户>
     * @param roleId long
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelUser(long roleId);

    /**
     * <删除角色相关账户>
     * <根据角色ID删除角色相关账户>
     * @param roleId long
     * @see [类、类#方法、类#成员]
     */
    public void deleteRelUser(long roleId);

    /**
     * <添加角色相关资产>
     * <功能详细描述>
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelAsset(Map map);

    /**
     * <查询角色相关资产>
     * <根据角色ID查询角色相关资产>
     * @param roleId long
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAsset(long roleId);
    
    /**
     * <删除角色相关资产>
     * <功能详细描述>
     * @param roleId long
     * @see [类、类#方法、类#成员]
     */
    public void deleteRelAsset(long roleId);
    
    /**
     * <添加角色相关资产>
     * <功能详细描述>
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelAssetGroup(Map map);
    
    /**
     * <添加角色相关权限>
     * <功能详细描述>
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void insertRelPermission(Map map);

    /**
     * <查询角色相关资产组>
     * <根据角色ID查询角色相关资产>
     * @param roleId long
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAssetGroup(long roleId);
    
    /**
     * <删除角色相关资产组>
     * <功能详细描述>
     * @param roleId long
     * @see [类、类#方法、类#成员]
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
     * <删除角色相关权限>
     * <功能详细描述>
     * @param roleId long
     * @see [类、类#方法、类#成员]
     */
    public void deleteRelPermission(long roleId);

    /**
     * <角色关联全部用户处理方法>
     * <功能详细描述>
     * @param roleId long
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelUserByRoleId(long roleId);
    
    /**
     * <角色关联全部资源处理方法>
     * <功能详细描述>
     * @param roleId long
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAssetByRoleId(long roleId);
    
    /**
     * <角色关联的全部资产组处理方法>
     * <功能详细描述>
     * @param roleId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> queryRelAssetGroupByRoleId(long roleId);
    
    /**
     * <排序>
     * @param map
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult sort(Map map,Page page) ; 
    
}
