package com.soc.model.role;

import java.io.Serializable;
import java.util.List;

import com.soc.model.BaseObject;

public class Permission implements Serializable
{
    // 权限ID
    private long permissionId;
    
    // 功能名称
    private String permissionModuleName;
    
    // 功能类型（0：一级模块 | 1：二级模块 | 2：功能）
    private int permissionType;
    
    // 上级模块ID,顶级模块此值为0
    private long permissionSuperiorId;
    
    // URL地址
    private String permissionUrl;
    
    // 描述
    private String permissionMemo;
    
    // 子模块列表
    private List<Permission> permissionModuleList;

    public long getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(long permissionId)
    {
        this.permissionId = permissionId;
    }

    public String getPermissionModuleName()
    {
        return permissionModuleName;
    }

    public void setPermissionModuleName(String permissionModuleName)
    {
        this.permissionModuleName = permissionModuleName;
    }

    public int getPermissionType()
    {
        return permissionType;
    }

    public void setPermissionType(int permissionType)
    {
        this.permissionType = permissionType;
    }

    public long getPermissionSuperiorId()
    {
        return permissionSuperiorId;
    }

    public void setPermissionSuperiorId(long permissionSuperiorId)
    {
        this.permissionSuperiorId = permissionSuperiorId;
    }

    public String getPermissionUrl()
    {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl)
    {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionMemo()
    {
        return permissionMemo;
    }

    public void setPermissionMemo(String permissionMemo)
    {
        this.permissionMemo = permissionMemo;
    }

    public List<Permission> getPermissionModuleList()
    {
        return permissionModuleList;
    }

    public void setPermissionModuleList(List<Permission> permissionModuleList)
    {
        this.permissionModuleList = permissionModuleList;
    }

}
