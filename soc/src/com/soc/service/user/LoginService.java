package com.soc.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.user.User;
import com.util.MD5;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * Description: 登录Service实现类
 * <登录的相关操作：登录验证、修改密码等>
 * 
 * @author  王亚男
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since soc v3.6.0.1
 */
public interface LoginService
{
    
    /**
     * 登录验证
     * 
     * @param String, String
     * @return User
     */
    public User check(String loginName, String password,String loginType,String sourceIP);
    
    /**
     * 修改密码
     * 
     * @param empId
     * @param password
     */
    public User changePassword(User user, String password);
    
    /**
     * 得到错误消息
     * 
     * @param 
     * @param String
     */
    public String getActionMessage();
/**
 * <拓扑过来的登录 不看密码>
 * <功能详细描述>
 * @param loginName
 * @return
 * @see [类、类#方法、类#成员]
 */
	public User networkTopologyLogin(String loginName);
}
