package com.soc.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.user.UserDao;
import com.soc.model.home.HomeDiv;
import com.soc.model.home.HomePageDiv;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.policy.TimePolicy;
import com.soc.model.role.Role;
import com.soc.model.user.User;
import com.soc.service.homepagediv.HomePageDivService;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.user.UserService;
import com.util.Base64;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * Description: 用户Service
 * <对用户的相关操作：添加用户、修改用户、删除用户、查询用户、为用户添加关联密码、时间、地址策略、为用户添加相关角色>
 * 
 * @author  王亚男
 * @version  V100R001C001
 * @see  [相关类/方法]
 * @since soc v3.6.0.1
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService
{
    // 用户管理业务类
    private UserDao userDao;
    // 用来存储div是否的字符串的服务类
 	private HomePageDivService homePageDivManager;
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        return userDao.count(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public SearchResult query(Map map, Page page)
    {
        // 按Map中存储的条件查找用户列表
        int rowsCount = userDao.count(map);
        page.setTotalCount(rowsCount);
        List<User> list = userDao.query(map, page.getStartIndex(), page.getPageSize());
        
        // 对查找的用户列表做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<User> query(Map map)
    {
        return userDao.query(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public User queryByUserId(long userId)
    {
        return userDao.queryByUserId(userId);
    }
    /**
     * {@inheritDoc}
     */
    public List<User> queryByUserLoginName(String userLoginName)
    {
        return userDao.queryByUserLoginName(userLoginName);
    }
    
    /**
     * {@inheritDoc}
     */
    public long updateUser(User user)
    {
        // 用户ID
        long userId;
        
        // 更新用户信息
        if (user.getUserId() != 0)
        {
            
            // 设置更新日期
            user.setUserUpdateDateTime(new Date());
            //更新用户密码
            if (!StringUtil.equals(user.getUserPassword(), ""))
            {
            		//user.setUserPassword(MD5.getMD5Password(user.getUserPassword()));	
            	user.setUserPassword(Base64.encodeString(user.getUserPassword()));
            }
            else
            {
                
            }
            
            // 更新数据
            userDao.updateUser(user);
            
            // 取得ID
            userId = user.getUserId();
            
            // 更新审计中记录的名称
            Map map = new HashMap<String, String>();
            map.put("userLoginName", user.getUserLoginName());
            map.put("userId", user.getUserId());
            //conductAuditDao.updateEmployeeNameByEmployeeId(map);
        }
        //创建用户
        else
        {
            // 设置创建日期和更新日期
            user.setUserCreateDateTime(new Date());
            user.setUserUpdateDateTime(new Date());
            
            // 设置用户密码
            //user.setUserPassword(MD5.getMD5Password(user.getUserPassword()));
            user.setUserPassword(Base64.encodeString(user.getUserPassword()));
   
            // 添加数据，取得用户ID
            userId = userDao.insert(user);
       	 int divCount = HomeDiv.map.size();
	        for (int i = 1; i <= divCount; i++) {
	        	//新建用户的时候为用户去创建这12个div
	        	if (i<7) {
	        		//设置默认显示前6个
	        		 homePageDivManager.insertDiv(new HomePageDiv("d"+i, 1, i%2,userId));
				}else{
					 homePageDivManager.insertDiv(new HomePageDiv("d"+i, 0, i%2, userId));
				}
	           
			}
        }
        
        return userId;
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteUser(long userId,int deleteStatus)
    {
        //删除用户
        userDao.deleteUser(userId,deleteStatus);
        
        //删除与角色的关联关系
        //userDao.deleteRelRole(userId);
        
        //删除与密码策略的关联关系
        //userDao.deleteRelPasswordPolicy(userId);
        
        //删除与时间策略的关联关系
        //userDao.deleteRelTimePolicy(userId);
        
        //删除与地址策略的关联关系
        //userDao.deleteRelAddressPolicy(userId);
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateUserStatus(long id, int userStatus)
    {
        // 该注释是人员注销
        /*else if (status == -1) {
            // 执行删除操作
            employeeDao.delRuleResAcc(id);// 关联授权帐号
            employeeDao.delRuleEmpRes(id);// 关联授权资源
            employeeDao.deleteRelRole(id);// 关联角色
            employeeDao.deleteRelPolicy(id);// 关联策略
            employeeDao.delete(id);// 人员
        }*/
        if (userStatus != -1)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            
            // 设置用户状态(锁定|激活)
            map.put("userId", Long.valueOf(id));
            map.put("userStatus", Long.valueOf(userStatus));
            map.put("userUpdateDateTime", new Date());
            
            userDao.updateUserStatus(map);
        }
        
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Role> queryRelRole(long userId)
    {
        return userDao.queryRelRole(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<PasswordPolicy> queryRelPasswordPolicy(long userId)
    {
        return userDao.queryRelPasswordPolicy(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<TimePolicy> queryRelTimePolicy(long userId)
    {
        return userDao.queryRelTimePolicy(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<AddressPolicy> queryRelAddressPolicy(long userId)
    {
        return userDao.queryRelAddressPolicy(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelPasswordPolicy(Map map)
    {
        userDao.insertRelPasswordPolicy(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelTimePolicy(Map map)
    {
        userDao.insertRelTimePolicy(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelAddressPolicy(Map map)
    {
        userDao.insertRelAddressPolicy(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelPasswordPolicy(long userId)
    {
        userDao.deleteRelPasswordPolicy(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelTimePolicy(long userId)
    {
        userDao.deleteRelTimePolicy(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelAddressPolicy(long userId)
    {
        userDao.deleteRelAddressPolicy(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertRelRole(Map map)
    {
        userDao.insertRelRole(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteRelRole(long userId)
    {
        userDao.deleteRelRole(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<User> exportQuery(Map map)
    {
        return userDao.exportQuery(map);
    }
    
    public UserDao getUserDao()
    {
        return userDao;
    }
    
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }
    
    /**
     * {@inheritDoc}
     */
	public SearchResult sort(Map map, Page page) {
		    int rowsCount = userDao.count(map);
	        page.setTotalCount(rowsCount);
	        String field=(String)map.get("field");
	        String sortType=(String)map.get("sortType");
	        String str=" \""+field+"\""+" "+sortType;
	        map.put("str", str) ; 
	        List<User> list = userDao.queryBySort(map, page.getStartIndex(), page.getPageSize());
	        SearchResult sr = new SearchResult() ; 
	        sr.setList(list);
	        sr.setPage(page);
	        return sr;

	}

	@Override
	public void deleteRelAssetGroup(long userid) {
		userDao.deleteRelAssetGroup(userid);
		
	}

	@Override
	public void insertRelAssetGroup(Map map) {
		userDao.insertRelAssetGroup(map);
		
	}

	@Override
	public List<User> queryAll() {
		return userDao.queryAll();
	}

	@Override
	public String queryUserNameById(long id) {
		return userDao.queryUserNameById(id);
	}

	@Override
	public long queryUserIdByName(String name) {
		return userDao.queryUserIdByName(name);
	}
	

	@Override
	public void updateField(Map map) {
		// TODO Auto-generated method stub
		userDao.updateField(map);
	}

	@Override
	public long updateUserAccount(User user) {
        
		long userId = 0;
        if (user.getUserId()==0) {
        	// 设置创建日期和更新日期
            user.setUserCreateDateTime(new Date());
            user.setUserUpdateDateTime(new Date());
            
            // 添加数据，取得用户ID
            userId = userDao.insert(user);
        
		}else {
			 // 设置更新日期
	        user.setUserUpdateDateTime(new Date());
	        
	        user.setUserPassword(user.getUserPassword());	
	        // 更新数据
	        userDao.updateUser(user);
	        
	        // 取得ID
	        userId = user.getUserId();
	        
	        // 更新审计中记录的名称
	        Map map = new HashMap<String, String>();
	        map.put("userLoginName", user.getUserLoginName());
	        map.put("userId", user.getUserId());
	        //conductAuditDao.updateEmployeeNameByEmployeeId(map);
		}
        
        return userId;
	}

	@Override
	public void updateLayoutByUserId(Map map) {
		this.userDao.updateLayoutByUserId(map);
		
	}

	@Override
	public int queryUserLayoutByUserId(long userId) {
	return  userDao.queryUserLayoutByUserId(userId);
	}

	@Override
	public long updateUserPassWordPolicy(User user) {
		  userDao.updateUser(user);
		return 0;
	}

	public HomePageDivService getHomePageDivManager() {
		return homePageDivManager;
	}

	public void setHomePageDivManager(HomePageDivService homePageDivManager) {
		this.homePageDivManager = homePageDivManager;
	}


    
}
