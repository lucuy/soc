package com.soc.service.user.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils.Null;

import com.soc.dao.user.UserDao;
import com.soc.dao.user.LoginDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;
//import com.soc.service.user.ADAuth;
import com.soc.service.user.ADAuth;
import com.soc.service.user.LoginService;
import com.soc.service.user.RadiusAuth;
//import com.soc.service.useradiusAuth;
import com.soc.service.audit.AuditService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.Base64;
import com.util.DES;
import com.util.MD5;
import com.util.StringUtil;

/**
 * 
 * Description: 登录Service的实现类
 * 登录的相关操作
 * 
 * @author  王亚男
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since soc v3.6.0.1
 */
public class LoginServiceImpl extends BaseServiceImpl implements LoginService
{
    
    /*
     * 登录的dao对象
     */
    private LoginDao loginDao;
    
    /*
     * 用户的dao对象
     */
    private UserDao userDao;
    
    /*
     * 记录返回给页面的信息
     */
    private String actionMessage;
    
    private AuditService auditManager;
    
    /**
     * 登录验证
     * 
     * @param String, String
     * @return User
     */
    public User check(String loginName, String password,String loginType , String sourceIP)
    {
        User user = null;
        User userName=null;
        actionMessage = "";
        
        //去除字符创前后空格
        loginName = loginName.trim();
       // password = password.trim(); 李长红修改。密码允许有空格
        
        // 判断认证方式为密码认证或者Radius认证
        List<User> userList = userDao.queryByUserLoginName(loginName);
        if (userList.size() > 0)
        {
            List<String> fieldList = new ArrayList<String>();
           if (userList.get(0).getUserAuthType() == 0)
            {
                // 密码认证
                Map map = new HashMap<String, String>();
                map.put("loginName", loginName);
                if (!loginType.equals("false")) {
					map.put("password", password);
					//map.put("password", Base64.encodeString(password));
				}else {
					//map.put("password", MD5.getMD5Password(password));
					//map.put("password", password);
					map.put("password", Base64.encodeString(password));
				}
                
                user = loginDao.check(map);
                
                userName = loginDao.checkUserName(map);
                
                if (user == null)
                {
                    actionMessage = "用户名密码错误！";
                    
                    if(userName !=null){
                        fieldList.add(userName.getUserLoginName() + "(" + userName.getUserLoginName() + ")");
                        try{
                        //InetAddress address = InetAddress.getLocalHost();
                        auditManager.insertByLoginOperator(userName.getUserId(), "失败",sourceIP, fieldList);
                        }catch (Exception e) {
                        }
                    }
                }
            }
        }
        else{
        	 actionMessage = "用户名密码错误！";
        }
        return user;
    }
    /**
     * {@inheritDoc}
     */
    @Override
	public User networkTopologyLogin(String loginName) {
    	//去除字符创前后空格
        loginName = loginName.trim();
        Map map = new HashMap();
        map.put("loginName", loginName);
        return loginDao.checkUserName(map);
	}

	/**
     * 修改密码
     * 
     * @param User,String
     * @return User
     */
    public User changePassword(User user, String password)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtil.equals(password, "") && password != null)
        {
            map.put("userId", user.getUserId());
            map.put("userPassword", Base64.encodeString(password));
            map.put("userChangePassword", 0);
           /* if (user.getUserChangePassword() != 1)
            {
                map.put("empTimeout", null);
                //user.setEmpTimeout(null);
            }
            else
            {
                //map.put("empTimeout", user.getEmpTimeout());
            }*/
            
            Date date = new Date();
            map.put("userUpdateDateTime", date);
            userDao.updatePassword(map);
            
            user.setUserPassword(password);
            user.setUserChangePassword(0);
        }
        return user;
    }
    
    /**
     * 得到错误消息
     * @param 
     * @param String
     */
    public String getActionMessage()
    {
        return this.actionMessage;
    }
    
    public LoginDao getLoginDao()
    {
        return loginDao;
    }
    
    public void setLoginDao(LoginDao loginDao)
    {
        this.loginDao = loginDao;
    }
    
    public UserDao getUserDao()
    {
        return userDao;
    }
    
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public AuditService getAuditManager()
    {
        return auditManager;
    }

    public void setAuditManager(AuditService auditManager)
    {
        this.auditManager = auditManager;
    }
    
}
