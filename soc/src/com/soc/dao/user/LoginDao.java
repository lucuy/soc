package com.soc.dao.user;

import java.util.List;
import java.util.Map;

import com.soc.model.user.User;


/**
 * Description: 登录Dao			
 * @author 王亚男                       
 * @Version 1.0                            
 * @Created at 2011-01-10                  
 * @Modified by         
 */

/**
 * 
 * 登录Dao
 * 登錄
 * 
 * @author  tiny
 * @version  [版本号, 2012-8-3]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface LoginDao {
    
    /**
     * 用户登录验证方法
     * 
     * @param Map
     * @return User
     */
    public User check(Map map);
    
    /**
     * 检测用户存在方法
     * 
     * @param Map
     * @return User
     */
    public User checkUserName(Map map);
    
    /**
     * 修改密码
     * 
     * @param Map
     * @return void
     */
    public void updatePassword(Map map);
}
