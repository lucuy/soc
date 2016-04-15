package com.soc.service.role.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.role.RoleDao;
import com.soc.model.role.Role;
import com.soc.model.user.User;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.role.RoleService;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <角色Service实现类>
 * <对角色的相关操作：添加角色、修改角色、删除角色、查询角色、拷贝角色>
 * 
 * @author  王亚男
 * @version  [V100R001C001, 2012-8-12]
 * @see  [RoleService]
 * @since
 */
public class RoleServiceImpl extends BaseServiceImpl implements Serializable, RoleService
{
    private RoleDao roleDao;
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        return roleDao.count(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public SearchResult queryRole(Map map, Page page)
    {
        // 按Map中存储的条件查找用户列表
        int rowsCount = roleDao.count(map);
        page.setTotalCount(rowsCount);
        List<Role> list = roleDao.queryRole(map, page.getStartIndex(), page.getPageSize());
        
        // 对查找的用户列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Role> queryRole(Map map)
    {
        return roleDao.queryRole(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public Role queryByRoleId(long roleId)
    {
        return roleDao.queryByRoleId(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public Role queryByRoleName(String roleName)
    {
        return roleDao.queryByRoleName(roleName);
    }
    
    /**
     * {@inheritDoc}
     */
    public long updateRole(Role role)
    {
        long roleId;
        
          // 更新角色
        if (role.getRoleId() != 0)
        {
            role.setRoleUpdateDateTime(new Date());
            roleDao.updateRole(role);
            roleId = role.getRoleId();
        }
        
     // 添加角色
        else
        {
            role.setRoleCreateDateTime(new Date());
            role.setRoleUpdateDateTime(new Date());
            roleId = roleDao.insertRole(role);
        }
        return roleId;
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRole(long roleId)
    {
        roleDao.deleteRole(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelUser(Map map)
    {
        roleDao.insertRelUser(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelUser(long roleId)
    {
        List<Map> list;
        
        // 取得相关人员
        list = roleDao.queryRelUser(roleId);
        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelUser(long roleId)
    {
        roleDao.deleteRelUser(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelAsset(Map map)
    {
        roleDao.insertRelAsset(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelAsset(long roleId)
    {
        List<Map> list;
        
        // 取得相关资产
        list = roleDao.queryRelAsset(roleId);
        
        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelAsset(long roleId)
    {
        roleDao.deleteRelAsset(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelAssetGroup(Map map)
    {
        roleDao.insertRelAssetGroup(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelAssetGroup(long roleId)
    {
        List<Map> list;
        
        // 取得相关资产
        list = roleDao.queryRelAssetGroup(roleId);
        
        return list;
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelAssetGroup(long roleId)
    {
        roleDao.deleteRelAssetGroup(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelPermission(long roleId)
    {
        roleDao.deleteRelPermission(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelPermission(Map map)
    {
        roleDao.insertRelPermission(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelPermission(long roleId)
    {
        return roleDao.queryRelPermission(roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelUserByRoleId(long roleId)
    {
        return roleDao.queryRelUserByRoleId(roleId);
    }
    
    
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelAssetByRoleId(long roleId)
    {
        return roleDao.queryRelAssetByRoleId(roleId);
    }
    
    @Override
   	public List<Map> queryRelAssetGroupByRoleId(long roleId) {
   		// TODO Auto-generated method stub
   		return roleDao.queryRelAssetGroupByRoleId(roleId);
   	}
    public RoleDao getRoleDao()
    {
        return roleDao;
    }
    
    public void setRoleDao(RoleDao roleDao)
    {
        this.roleDao = roleDao;
    }
    
    @Override
	public SearchResult sort(Map map, Page page) {
		int rowsCount = roleDao.count(map);
        page.setTotalCount(rowsCount);
        String field=(String)map.get("field");
        String sortType=(String)map.get("sortType");
        String str=" \""+field+"\""+" "+sortType;
        map.put("str", str);
        
        List<Role> list = roleDao.queryBySort(map, page.getStartIndex(), page.getPageSize());
        SearchResult sr = new SearchResult() ; 
        sr.setList(list);
        sr.setPage(page);
        return sr;
	}

}
