package com.soc.dao.role.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.role.RoleDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.role.Role;

/**
 * <角色Dao实现类>
 * <对角色的相关操作：添加角色、修改角色、删除角色、查询角色、拷贝角色>
 * 
 * @author  王亚男
 * @version  [V100R001C001, 2012-8-4]
 * @see  [相关类/方法]
 * @since  
 */
public class RoleDaoIbatis extends BaseDaoIbatis implements RoleDao
{
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        Object ob = null;
        try{
            ob = super.queryForObject(GlobalConfig.sqlId+"role.count", map);
        }catch(Exception e){
            e.printStackTrace();
        }
        int totalRows = 0;
        if(ob != null){
            totalRows = ((Integer)ob).intValue();
        }
        return totalRows;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Role> queryRole(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"role.query", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Role> queryRole(Map map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"role.queryEx", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public Role queryByRoleId(long roleId)
    {
        return (Role)super.queryForObject(GlobalConfig.sqlId+"role.queryById", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public Role queryByRoleName(String roleName)
    {
        return (Role)super.queryForObject(GlobalConfig.sqlId+"role.queryRoleByRoleName", roleName);
    }
    
    /**
     * {@inheritDoc}
     */
    public long insertRole(Role role)
    {
        long roleId = 0L;
        Object roleObject = super.create(GlobalConfig.sqlId+"role.insert", role);
        if (roleObject != null)
        {
            roleId = Long.parseLong(roleObject.toString());
        }
        return roleId;
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateRole(Role role)
    {
        super.update(GlobalConfig.sqlId+"role.update", role);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRole(long roleId)
    {
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("roleId", Long.valueOf(roleId));
        map.put("roleIsDelete", Long.valueOf(GlobalConfig.IS_DELETE));
        super.update(GlobalConfig.sqlId+"role.updateRoleIsDelete", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelUser(Map map)
    {
        super.create(GlobalConfig.sqlId+"role.insert-relUser", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelUser(long roleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"role.query-relUser", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelUser(long roleId)
    {
        super.delete(GlobalConfig.sqlId+"role.delete-relUser", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelAsset(Map map)
    {
        super.create(GlobalConfig.sqlId+"role.insert-relAsset", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelAssetGroup(Map map)
    {
        super.create(GlobalConfig.sqlId+"role.insert-relAssetGroup", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelAsset(long roleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"role.query-relAsset", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelAssetGroup(long roleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"role.query-relAssetGroup", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelAsset(long roleId)
    {
        super.delete(GlobalConfig.sqlId+"role.delete-relAsset", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelAssetGroup(long roleId)
    {
        super.delete(GlobalConfig.sqlId+"role.delete-relAssetGroup", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelPermission(long roleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"role.query-relPermission", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelPermission(Map map)
    {
        super.create(GlobalConfig.sqlId+"role.insert-relPermission", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelPermission(long roleId)
    {
        super.delete(GlobalConfig.sqlId+"role.delete-relPermission", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelUserByRoleId(long roleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"role.queryRelUserByRoleId", roleId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> queryRelAssetByRoleId(long roleId)
    {
        return super.queryForList(GlobalConfig.sqlId+"role.queryRelAssetByRoleId", roleId);
    }
    
    @Override
	public List<Map> queryRelAssetGroupByRoleId(long roleId) {
		// TODO Auto-generated method stub
		return super.queryForList(GlobalConfig.sqlId+"role.queryRelAssetGroupByRoleId", roleId);
	}
    
    @Override
	public List<Role> queryBySort(Map map, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"role.sort",map,startRow,pageSize);
	}
}
