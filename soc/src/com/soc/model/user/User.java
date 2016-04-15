package com.soc.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.soc.model.policy.PasswordPolicy;
import com.soc.model.role.Role;

public class User implements Serializable
{
    
    // 账户ID
    private long userId;
    
    //登录名
    private String userLoginName;
    
    // 真实姓名
    private String userRealName;
    
    // 密码
    private String userPassword;
    
    // 状态(0:锁定 | 1:激活  | 2:注销)
    private int userStatus = 1;
    
    // 邮箱
    private String userEmail;
    
    // 手机号码
    private String userMobile;
    
    // 电话号码
    private String userTelephone;
    
    // 通信地址
    private String userAddress;
    
    // 登录方式(0:密码登录|1:证书登录|2:USBKEY登录)
    private int userAuthType;
    
    // 下次登录修改密码(0:否 | 1:是)
    private int userChangePassword;
    
    // 创建者IP
    private String userCreatorIp;
    
    // 创建时间
    private Date userCreateDateTime;
    
    // 修改时间
    private Date userUpdateDateTime;
    
    // 显示(0:否 | 1:是)
    private int userDisplay = 1;
    
    // 标识账户是否已删除(0:未删除|1:已删除)
    private int userIsDelete = 0;
    
    // 描述
    private String userMemo;
    
    //用户锁定开始时间点
    private Date userLockTime;
    
    //用户输入错误次数
    private int userFailNum = 0;
    
    //关联角色
    private List<Role> userRoleList;
    
    
    private long assetGroupid;//关联资产组
    
    private String showEventFiled ; //事件页面自定义显示的字段
    //显示多少列
    private int layout;
    //二次验证的码
    private String  userSavedSecret;
    public void setUserSavedSecret(String userSavedSecret) {
		this.userSavedSecret = userSavedSecret;
	}
    public String getUserSavedSecret() {
		return userSavedSecret;
	}
    public long getAssetGroupid() {
		return assetGroupid;
	}

	public void setAssetGroupid(long assetGroupid) {
		this.assetGroupid = assetGroupid;
	}

	public long getUserId()
    {
        return userId;
    }
    
    public void setUserId(long userId)
    {
        this.userId = userId;
    }
    
    public String getUserLoginName()
    {
        return userLoginName;
    }
    
    public void setUserLoginName(String userLoginName)
    {
        this.userLoginName = userLoginName;
    }
    
    public String getUserRealName()
    {
        return userRealName;
    }
    
    public void setUserRealName(String userRealName)
    {
        this.userRealName = userRealName;
    }
    
    public String getUserPassword()
    {
        return userPassword;
    }
    
    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }
    
    public int getUserStatus()
    {
        return userStatus;
    }
    
    public void setUserStatus(int userStatus)
    {
        this.userStatus = userStatus;
    }
    
    public String getUserEmail()
    {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }
    
    public String getUserMobile()
    {
        return userMobile;
    }
    
    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }
    
    public String getUserTelephone()
    {
        return userTelephone;
    }
    
    public void setUserTelephone(String userTelephone)
    {
        this.userTelephone = userTelephone;
    }
    
    public String getUserAddress()
    {
        return userAddress;
    }
    
    public void setUserAddress(String userAddress)
    {
        this.userAddress = userAddress;
    }
    
    public int getUserAuthType()
    {
        return userAuthType;
    }
    
    public void setUserAuthType(int userAuthType)
    {
        this.userAuthType = userAuthType;
    }
    
    public int getUserChangePassword()
    {
        return userChangePassword;
    }
    
    public void setUserChangePassword(int userChangePassword)
    {
        this.userChangePassword = userChangePassword;
    }
    
    public String getUserCreatorIp()
    {
        return userCreatorIp;
    }
    
    public void setUserCreatorIp(String userCreatorIp)
    {
        this.userCreatorIp = userCreatorIp;
    }
    
    public Date getUserCreateDateTime()
    {
        return userCreateDateTime;
    }
    
    public void setUserCreateDateTime(Date userCreateDateTime)
    {
        this.userCreateDateTime = userCreateDateTime;
    }
    
    public Date getUserUpdateDateTime()
    {
        return userUpdateDateTime;
    }
    
    public void setUserUpdateDateTime(Date userUpdateDateTime)
    {
        this.userUpdateDateTime = userUpdateDateTime;
    }
    
    public int getUserDisplay()
    {
        return userDisplay;
    }
    
    public void setUserDisplay(int userDisplay)
    {
        this.userDisplay = userDisplay;
    }
    
    public int getUserIsDelete()
    {
        return userIsDelete;
    }
    
    public void setUserIsDelete(int userIsDelete)
    {
        this.userIsDelete = userIsDelete;
    }
    
    public String getUserMemo()
    {
        return userMemo;
    }
    
    public void setUserMemo(String userMemo)
    {
        this.userMemo = userMemo;
    }

    public Date getUserLockTime()
    {
        return userLockTime;
    }

    public void setUserLockTime(Date userLockTime)
    {
        this.userLockTime = userLockTime;
    }

    public int getUserFailNum()
    {
        return userFailNum;
    }

    public void setUserFailNum(int userFailNum)
    {
        this.userFailNum = userFailNum;
    }

    public void setUserRoleList(List<Role> userRoleList)
    {
        this.userRoleList = userRoleList;
    }

    public List<Role> getUserRoleList()
    {
        return userRoleList;
    }

	public String getShowEventFiled() {
		return showEventFiled;
	}

	public void setShowEventFiled(String showEventFiled) {
		this.showEventFiled = showEventFiled;
	}

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

    
    

}
