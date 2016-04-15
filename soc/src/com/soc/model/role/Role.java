package com.soc.model.role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.soc.model.user.User;

public class Role implements Serializable
{
    // 角色ID
    private long roleId;
    
    // 角色名称
    private String roleName;
    
    // 创建时间
    private Date roleCreateDateTime;
    
    // 更新时间
    private Date roleUpdateDateTime;
    
    // 创建者
    private String roleUserLoginName;
    
    // 标记删 （0：未删除 | 1：已删除）
    private int roleIsDelete = 0;
    
    // 角色是否显示（0：隐藏 | 1：显示）
    private int roleDisplay;
    
    // 描述
    private String roleMemo;
    
    // 关联账户
    private List<User> userList;
    
    // 关联资源
    //private List<Asset> assetList;
    
    // 关联资源组
    //private List<AssetGroup> assetGroup;
    
    // 权限
    private List<Permission> permissionList;
    
    private String userIds;
    
    private String assetIds;
    
    private String assetGroupIds;
    
    private String roleNames;
    
    public long getRoleId()
    {
        return roleId;
    }
    
    public void setRoleId(long roleId)
    {
        this.roleId = roleId;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
    public Date getRoleCreateDateTime()
    {
        return roleCreateDateTime;
    }
    
    public void setRoleCreateDateTime(Date roleCreateDateTime)
    {
        this.roleCreateDateTime = roleCreateDateTime;
    }
    
    public Date getRoleUpdateDateTime()
    {
        return roleUpdateDateTime;
    }
    
    public void setRoleUpdateDateTime(Date roleUpdateDateTime)
    {
        this.roleUpdateDateTime = roleUpdateDateTime;
    }
    
    public String getRoleUserLoginName()
    {
        return roleUserLoginName;
    }
    
    public void setRoleUserLoginName(String roleUserLoginName)
    {
        this.roleUserLoginName = roleUserLoginName;
    }
    
    public String getRoleMemo()
    {
        return roleMemo;
    }
    
    public void setRoleMemo(String roleMemo)
    {
        this.roleMemo = roleMemo;
    }
    
    public int getRoleIsDelete()
    {
        return roleIsDelete;
    }

    public void setRoleIsDelete(int roleIsDelete)
    {
        this.roleIsDelete = roleIsDelete;
    }

    public int getRoleDisplay()
    {
        return roleDisplay;
    }

    public void setRoleDisplay(int roleDisplay)
    {
        this.roleDisplay = roleDisplay;
    }

    public List<User> getUserList()
    {
        return userList;
    }
    
    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }

    public List<Permission> getPermissionList()
    {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList)
    {
        this.permissionList = permissionList;
    }

    public String getUserIds()
    {
        return userIds;
    }

    public void setUserIds(String userIds)
    {
        this.userIds = userIds;
    }

    public String getAssetIds()
    {
        return assetIds;
    }

    public void setAssetIds(String assetIds)
    {
        this.assetIds = assetIds;
    }

    public String getAssetGroupIds()
    {
        return assetGroupIds;
    }

    public void setAssetGroupIds(String assetGroupIds)
    {
        this.assetGroupIds = assetGroupIds;
    }

    public String getRoleNames()
    {
        return roleNames;
    }

    public void setRoleNames(String roleNames)
    {
        this.roleNames = roleNames;
    }
    
}
