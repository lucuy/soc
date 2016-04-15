package com.soc.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.policy.AddressPolicy;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.policy.TimePolicy;
import com.soc.model.role.Role;
import com.soc.model.user.User;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <用户Service>
 * <对用户的相关操作：添加用户、修改用户、删除用户、查询用户、为用户添加关联密码、时间、地址策略、为用户添加相关角色>
 * 
 * @author  王亚男
 * @version  [V100R001C001, 2012-8-1]
 * @see  [相关类/方法]
 * @since 
 */
public interface UserService extends Serializable
{
    
    /**
     * <计算符合条件的用户记录数>
     * <功能详细描述>
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <按条件分页查询用户>
     * <功能详细描述>
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see [类、类#方法、类#成员]
     */
    public SearchResult query(Map map, Page page);
    
    /**
     * 按条件查询用户
     * 
     * @param map Map
     * @return 返回符合条件的用户的列表List<User>
     */
    public List<User> query(Map map);
    
    /**
     * 根据用户ID查询用户
     * 
     * @param userId long
     * @return 返回对应ID的用户User
     */
    public User queryByUserId(long userId);
    
    /**
     * 根据登录名查询用户
     * 
     * @param userLoginName String
     * @return 返回登录名为userLoginName的List<User>
     */
    public List<User> queryByUserLoginName(String userLoginName);
    
    /**
     * 新增或修改用户信息
     * 
     * @param user User
     * @return 返回用户的ID
     */
    public long updateUser(User user);
    
    /**
     * 新增或修改用户信息
     * 
     * @param user User
     * @return 返回用户的ID
     */
    public long updateUserPassWordPolicy(User user);
    
    /**
     * 根据用户ID删除用户
     * 
     * @param userId long
     * @see
     */
    public void deleteUser(long userId,int deleteStatus);
    
    /**
     * 根据用户的ID更新用户的状态
     * 
     * @param id long
     * @param userStatus int
     * @see
     */
    public void updateUserStatus(long id, int userStatus);
    
    /**
     * 根据用户ID查询相关角色
     * 
     * @param long
     * @return List<Map>
     */
    public List<Role> queryRelRole(long userId);
    
    /**
     * 根据用户ID查询相关密码策略
     * 
     * @param userId long
     * @return List<Map>
     */
    public List<PasswordPolicy> queryRelPasswordPolicy(long userId);
    
    /**
     * 根据用户ID查询相关时间策略
     * 
     * @param userId long
     * @return List<Map>
     */
    public List<TimePolicy> queryRelTimePolicy(long userId);
    
    /**
     * 根据用户ID查询相关地址策略
     * 
     * @param userId long
     * @return List<Map>
     */
    public List<AddressPolicy> queryRelAddressPolicy(long userId);
    
    /**
     * 为用户添加密码策略
     * 
     * @param map Map
     */
    public void insertRelPasswordPolicy(Map map);
    
    /**
     * 为用户添加时间策略
     * 
     * @param map Map
     * @see
     */
    public void insertRelTimePolicy(Map map);
    
    /**
     * 为用户添加地址策略
     * 
     * @param map Map
     * @see
     */
    public void insertRelAddressPolicy(Map map);
    
    /**
     * 根据用户ID删除与密码策略的关联关系
     * 
     * @param userId long
     * @see
     */
    public void deleteRelPasswordPolicy(long userId);
    
    /**
     * 根据用户ID删除与时间策略的关联关系
     * 
     * @param userId long
     * @see
     */
    public void deleteRelTimePolicy(long userId);
    
    /**
     * 根据用户ID删除与地址策略的关联关系
     * 
     * @param userId long
     * @see
     */
    public void deleteRelAddressPolicy(long userId);
    
    /**
     * 为用户添加角色
     * 
     * @param map Map
     * @see
     */
    public void insertRelRole(Map map);
    
    /**
     * 根据用户ID删除与角色的关联关系
     * 
     * @param userId long
     * @see
     */
    public void deleteRelRole(long userId);
    
    /**
     * 导出符合条件的用户列表
     * 
     * @param map Map
     * @return List<User>
     */
    public List<User> exportQuery(Map map);
    
    /**
     * 根据用户查询用户关联角色
     * 
     * @param long
     * @return List<Role>
     */
    //public List<Role> queryEmpRoleById(long userId);
    
    /**
     * 排序
     * 
     * @param map Map
     * @param page Page
     * @return List<User>
     */
    public SearchResult sort(Map map,Page page);
    /**
     * 删除关联资产组
     *  
     */
    	public void deleteRelAssetGroup(long userid);
    	
    	/**
         * 新增用户资产组关联
         * 
         * @param map
         * @return void
         */
    public void	insertRelAssetGroup( Map map);

    
    /**
     * 为派发工单查询所有用户
     * 
     * @param 
     * @return 返回用户的结果列表List<User>
     */
    public List<User> queryAll();

    /**
     *  根据用户id 获得用户名
     * @param id
     * @return
     */
    public String queryUserNameById(long id);
    
    /**
     *  根据用户名 获得用户Id
     * @param id
     * @return
     */
    public long queryUserIdByName(String name);
    
    /**
     * 升级用户选择的字段
     *  @param Map map
     * @return void
     */
    public void updateField(Map map) ; 
    
    /**
     * 新增或修改用户信息
     * 不适用MD5加密密码 
     * @param user User
     * @return 返回用户的ID
     */
    public long updateUserAccount(User user);
/**
 * <根据用户的id保存列数>
 * <功能详细描述>
 * @param userId
 * @param layout
 * @see [类、类#方法、类#成员]
 */
    public void updateLayoutByUserId(Map map);
/**
 * <根据uesrId查询列数>
 * <功能详细描述>
 * @param userId
 * @return
 * @see [类、类#方法、类#成员]
 */
public int queryUserLayoutByUserId(long userId);
	


}


