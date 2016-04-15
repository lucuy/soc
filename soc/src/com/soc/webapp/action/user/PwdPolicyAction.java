/*
 * 文 件 名:  PwdPolicyAction.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-16
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.webapp.action.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.soc.model.policy.PasswordPolicy;
import com.soc.model.user.User;
import com.soc.service.policy.PasswordPolicyService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;

/**
 * 密码策略Action 
 * 根据密码策略提示用户修改密码
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-16]
 * @see  
 * @since  [soc v3.6.0.1]
 */
public class PwdPolicyAction extends BaseAction
{
    // 用户管理类
    private UserService userManage;
    
    // 密码策略管理类
    private PasswordPolicyService passwordPolicyManager;
    
    // 修改后的密码
    private String newPassword;
    
    //策略id串
    private String passwordPolicyIds;
    
    /**
     * <密码策略检查>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void checkPwdPolicy()
    {
        LOG.info("[PwdPolicyAction] enter method checkPwdPolicy()...");
        
        List<PasswordPolicy> passwordPolicyList = new ArrayList<PasswordPolicy>();
        
        // 账户信息页面提交的请求在这里做处理
        if (StringUtil.isNotBlank(passwordPolicyIds))
        {
            if (passwordPolicyIds.indexOf(",") != -1)
            {
                String[] ids = passwordPolicyIds.split(",");
                for (String id : ids)
                {
                    PasswordPolicy passwordPolicy = passwordPolicyManager.queryById(Long.parseLong(id));
                    if (passwordPolicy != null)
                    {
                        passwordPolicyList.add(passwordPolicy);
                    }
                }
            }
            else
            {
                PasswordPolicy passwordPolicy = passwordPolicyManager.queryById(Long.parseLong(passwordPolicyIds));
                if (passwordPolicy != null)
                {
                    passwordPolicyList.add(passwordPolicy);
                }
            }
            
        }
        
        // 登录修改密码提交的请求在这里做处理
        else
        {
            User u = (User)getSession().getAttribute("SOC_LOGON_USER");
            passwordPolicyList = userManage.queryRelPasswordPolicy(u.getUserId());
        }
        
        // 需要验证的密码不能为空
        if (!StringUtil.equals(newPassword, "") && newPassword != null)
        {
            String message = "";
            String pwdPolicyInfo = "";
            StringBuffer lowerCaseNum = new StringBuffer();
            StringBuffer upperCaseNum = new StringBuffer();
            int numberLength = 0;
            int passwordLength = 0;
            boolean flagStatus = false;
            if (passwordPolicyList != null)
            {
                for (PasswordPolicy passwordPolicy : passwordPolicyList)
                {
                    
                    if (passwordPolicy != null)
                    {
                        if (passwordPolicy.getPasswordPolicyStatus() == 1)
                        {
                            //保证密码策略未被锁定
                            //验证策略的小写字母个数
                            if (passwordPolicy.getPasswordPolicyLowerCaseNumber() != 0)
                            {
                                lowerCaseNum.append(passwordPolicy.getPasswordPolicyLowerCaseNumber() + ",");
                            }
                            
                            //策略的大写字母个数
                            if (passwordPolicy.getPasswordPolicyUpperCaseNumber() != 0)
                            {
                                upperCaseNum.append(passwordPolicy.getPasswordPolicyUpperCaseNumber() + ",");
                            }
                            
                            //策略的数字个数
                            if (passwordPolicy.getPasswordPoicyNumericNumber() != 0)
                            {
                                int figureNum = passwordPolicy.getPasswordPoicyNumericNumber();
                                if (numberLength < figureNum)
                                {
                                    numberLength = figureNum;
                                }
                            }
                            
                            //策略密码长度,已经修改
                            if (passwordPolicy.getPasswordPolicyPasswordLength() != 0)
                            {
                                int pwdtemplength=passwordPolicy.getPasswordPolicyPasswordLength();
                                if(passwordLength < pwdtemplength)
                                {
                                	passwordLength= pwdtemplength;
                                }
                            	//passwordLength = passwordPolicy.getPasswordPolicyPasswordLength();
                            }
                            
                            flagStatus = true;
                            
                        }
                    }
                }
                
                if (flagStatus)
                {
                    String pwdLowerCaseNum = String.valueOf(StringUtil.getLowerCaseNum(newPassword));
                    String pwdUpperCaseNum = String.valueOf(StringUtil.getUpperCaseNum(newPassword));
                    
                    int pwdLength = newPassword.length();
                    int pwdFigureNum = StringUtil.getFigureNum(newPassword);
                    
                    if (pwdLength < passwordLength)
                    {
                        message = "*密码长度至少为:" + passwordLength;
                        pwdPolicyInfo += message;
                    }
                    if (!lowerCaseNum.toString().contains(pwdLowerCaseNum) && lowerCaseNum.length() > 0)
                    {
                        message = "*小写字母个数应该为:" + StringUtil.removeLastChar(lowerCaseNum.toString());
                        pwdPolicyInfo += message;
                    }
                    
                    if (!upperCaseNum.toString().contains(pwdUpperCaseNum) && upperCaseNum.length() > 0)
                    {
                        
                        message = "*大写字母个数应该为：" + StringUtil.removeLastChar(upperCaseNum.toString());
                        pwdPolicyInfo += message;
                    }
                    
                    if (pwdFigureNum < numberLength && numberLength != Integer.MAX_VALUE)
                    {
                        message = "*数字至少为：" + numberLength;
                        pwdPolicyInfo += message;
                    }
                    
                    try
                    {
                        getResponse().getWriter().write(pwdPolicyInfo);
                    }
                    catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
            }
        }
    }
    
    public UserService getUserManage()
    {
        return userManage;
    }
    
    public void setUserManage(UserService userManage)
    {
        this.userManage = userManage;
    }
    
    public PasswordPolicyService getPasswordPolicyManager()
    {
        return passwordPolicyManager;
    }
    
    public void setPasswordPolicyManager(PasswordPolicyService passwordPolicyManager)
    {
        this.passwordPolicyManager = passwordPolicyManager;
    }
    
    public String getNewPassword()
    {
        return newPassword;
    }
    
    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }
    
    public String getPasswordPolicyIds()
    {
        return passwordPolicyIds;
    }
    
    public void setPasswordPolicyIds(String passwordPolicyIds)
    {
        this.passwordPolicyIds = passwordPolicyIds;
    }
    
}
