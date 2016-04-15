package com.soc.model.policy;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <密码策略实体类>
 * 
 * @author 曹理冬
 * @version [V100R001C001]
 * @see [相关类/方法]
 * @since [soc v3.6.0.1]
 */
public class PasswordPolicy implements Serializable
{

    private static final long serialVersionUID = 1L;
    
    private long passwordPolicyId;// 密码策略id
    
    private String passwordPolicyName;// 密码策略名称
    
    private int passwordPolicyLowerCaseNumber;// 小写字母个数
    
    private int passwordPolicyUpperCaseNumber;// 大写字母个数
    
    private int passwordPoicyNumericNumber;// 数字个数
    
    private int passwordPolicyPasswordChangeCycle;// 修改密码周天
    
    private int passwordPolicyPasswordLength;// 最小密码长度
    
    private int passwordPoicyFailLockNumber;// 失败锁定次数
    
    private int passwordPolicyLockRecoveTime;// 恢复锁定时间
    
    private int passwordPolicyStatus;// 策略状态
    
    private String passwordPolicyUserLoginName;// 创建登录者登录名
    
    private Date passwordPolicyCreateDateTime;// 创建时间
    
    private Date passwordPolicyUpdateDateTime;// 更新时间
    
    private int passwordPolicyIsDelete;// 标记删除
    
    private String passwordPolicyMemo;// 描述
    
    public long getPasswordPolicyId()
    {
        return passwordPolicyId;
    }
    
    public void setPasswordPolicyId(long passwordPolicyId)
    {
        this.passwordPolicyId = passwordPolicyId;
    }
    
    public String getPasswordPolicyName()
    {
        return passwordPolicyName;
    }
    
    public void setPasswordPolicyName(String passwordPolicyName)
    {
        this.passwordPolicyName = passwordPolicyName;
    }
    
    public int getPasswordPolicyLowerCaseNumber()
    {
        return passwordPolicyLowerCaseNumber;
    }
    
    public void setPasswordPolicyLowerCaseNumber(int passwordPolicyLowerCaseNumber)
    {
        this.passwordPolicyLowerCaseNumber = passwordPolicyLowerCaseNumber;
    }
    
    public int getPasswordPolicyUpperCaseNumber()
    {
        return passwordPolicyUpperCaseNumber;
    }
    
    public void setPasswordPolicyUpperCaseNumber(int passwordPolicyUpperCaseNumber)
    {
        this.passwordPolicyUpperCaseNumber = passwordPolicyUpperCaseNumber;
    }
    
    public int getPasswordPoicyNumericNumber()
    {
        return passwordPoicyNumericNumber;
    }
    
    public void setPasswordPoicyNumericNumber(int passwordPoicyNumericNumber)
    {
        this.passwordPoicyNumericNumber = passwordPoicyNumericNumber;
    }
    
    public int getPasswordPolicyPasswordChangeCycle()
    {
        return passwordPolicyPasswordChangeCycle;
    }
    
    public void setPasswordPolicyPasswordChangeCycle(int passwordPolicyPasswordChangeCycle)
    {
        this.passwordPolicyPasswordChangeCycle = passwordPolicyPasswordChangeCycle;
    }
    
    public int getPasswordPolicyPasswordLength()
    {
        return passwordPolicyPasswordLength;
    }
    
    public void setPasswordPolicyPasswordLength(int passwordPolicyPasswordLength)
    {
        this.passwordPolicyPasswordLength = passwordPolicyPasswordLength;
    }
    
    public int getPasswordPoicyFailLockNumber()
    {
        return passwordPoicyFailLockNumber;
    }
    
    public void setPasswordPoicyFailLockNumber(int passwordPoicyFailLockNumber)
    {
        this.passwordPoicyFailLockNumber = passwordPoicyFailLockNumber;
    }
    
    public int getPasswordPolicyLockRecoveTime()
    {
        return passwordPolicyLockRecoveTime;
    }
    
    public void setPasswordPolicyLockRecoveTime(int passwordPolicyLockRecoveTime)
    {
        this.passwordPolicyLockRecoveTime = passwordPolicyLockRecoveTime;
    }
    
    public int getPasswordPolicyStatus()
    {
        return passwordPolicyStatus;
    }
    
    public void setPasswordPolicyStatus(int passwordPolicyStatus)
    {
        this.passwordPolicyStatus = passwordPolicyStatus;
    }
    
    public String getPasswordPolicyUserLoginName()
    {
        return passwordPolicyUserLoginName;
    }
    
    public void setPasswordPolicyUserLoginName(String passwordPolicyUserLoginName)
    {
        this.passwordPolicyUserLoginName = passwordPolicyUserLoginName;
    }
    
    public Date getPasswordPolicyCreateDateTime()
    {
        return passwordPolicyCreateDateTime;
    }
    
    public void setPasswordPolicyCreateDateTime(Date passwordPolicyCreateDateTime)
    {
        this.passwordPolicyCreateDateTime = passwordPolicyCreateDateTime;
    }
    
    public Date getPasswordPolicyUpdateDateTime()
    {
        return passwordPolicyUpdateDateTime;
    }
    
    public void setPasswordPolicyUpdateDateTime(Date passwordPolicyUpdateDateTime)
    {
        this.passwordPolicyUpdateDateTime = passwordPolicyUpdateDateTime;
    }
    
    public int getPasswordPolicyIsDelete()
    {
        return passwordPolicyIsDelete;
    }
    
    public void setPasswordPolicyIsDelete(int passwordPolicyIsDelete)
    {
        this.passwordPolicyIsDelete = passwordPolicyIsDelete;
    }
    
    public String getPasswordPolicyMemo()
    {
        return passwordPolicyMemo;
    }
    
    public void setPasswordPolicyMemo(String passwordPolicyMemo)
    {
        this.passwordPolicyMemo = passwordPolicyMemo;
    }
    
}
