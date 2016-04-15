package com.soc.service.policy.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import com.soc.model.policy.PasswordPolicy;
import com.soc.model.user.User;
import com.soc.service.policy.PasswordPolicyService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;
import com.soc.dao.policy.PasswordPolicyDao;
import com.soc.dao.user.UserDao;

/**
 * 
 * <密码策略Service实现类> <实现对密码策略的相关操作：查询密码策略、修改密码策略、增加密码策略、删除密码策略>
 * 
 * @author 曹理冬
 * @version [V100R001C001]
 * @see [相关类/方法]
 * @since [soc v3.6.0.1]
 */
public class PasswordPolicyServiceImpl extends BaseServiceImpl implements PasswordPolicyService
{
    private static final long serialVersionUID = 1L;
    
    private PasswordPolicyDao passwordPolicyDao;
    
    private UserDao userdao;
    
    private long zero = 0L;
    
    /**
     * {@inheritDoc}根据条件查找密码策略信息
     */
    public SearchResult<PasswordPolicy> query(Map map, Page page)
    {
        
        // 按Map中存储的条件查找用户列表
        int rowsCount = passwordPolicyDao.count(map);
        page.setTotalCount(rowsCount);
        List<PasswordPolicy> list = passwordPolicyDao.query(map, page.getStartIndex(), page.getPageSize());
        
        // 对查找的用户列表做分页处理
        SearchResult<PasswordPolicy> sr = new SearchResult<PasswordPolicy>();
        sr.setList(list);
        sr.setPage(page);
        
        return sr;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<PasswordPolicy> query(Map map)
    {
        List<PasswordPolicy> list = passwordPolicyDao.query(map);
        
        return list;
    }

    /**
     * <根据用户Id判断执行插入或者更新操作> <功能详细描述>
     * @param passwordPolicy long
     * @return long
     * @see [类、类#方法、类#成员]
     */
    public long update(PasswordPolicy passwordPolicy)
    {
        // 密码策略ID
        long passwordPolicyId;
        
        // 用户信息存在更新密码策略信息
        if (passwordPolicy.getPasswordPolicyId() != zero)
        {
            
            passwordPolicy.setPasswordPolicyUpdateDateTime(new Date());
            
            // 设置密码策略可见
            passwordPolicy.setPasswordPolicyIsDelete(0);
            
            // 设置密码策略激活状态
            passwordPolicy.setPasswordPolicyStatus(1);
            
            // 执行更新操作
            passwordPolicyDao.update(passwordPolicy);
            
            // 取得密码策略Id
            passwordPolicyId = passwordPolicy.getPasswordPolicyId();
        }
        else
        {
            passwordPolicy.setPasswordPolicyUpdateDateTime(new Date());
            passwordPolicy.setPasswordPolicyCreateDateTime(new Date());
            // 设置密码策略可见
            passwordPolicy.setPasswordPolicyIsDelete(0);
            // 设置密码策略激活状态
            passwordPolicy.setPasswordPolicyStatus(1);
            
            // 执行插入操作
            passwordPolicyId = passwordPolicyDao.insert(passwordPolicy);
        }
        return passwordPolicyId;
    }
    
    /**
     * 根据ID删除密码策略
     * 
     * @param passwordPolicyId long
     * @return
     */
    
    public void deletePasswordPolicy(long passwordPolicyId)
    {
        passwordPolicyDao.deletePasswordPolicy(passwordPolicyId);
        
    }
    
    /**
     * {@inheritDoc}查詢條數
     */
    public int count(Map map)
    {
        return passwordPolicyDao.count(map);
    }
    
    /**
     * {@inheritDoc}根據ID查詢
     */
    public PasswordPolicy queryById(long passwordPolicyId)
    {
        
        return passwordPolicyDao.queryById(passwordPolicyId);
    }
    
    /**
     * {@inheritDoc}更新狀態
     */
    public void updatePasswordPolicyStatus(long id, int passwordPolicyStatus)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        // 将传入的放到Map内
        map.put("passwordPolicyId", id);
        // 将要更新为的状态，放到Map内
        map.put("passwordPolicyStatus", passwordPolicyStatus);
        
        passwordPolicyDao.updatePasswordPolicyStatus(map);
        
    }
    
    /**
     * {@inheritDoc}根據姓名查詢
     */
    public List<PasswordPolicy> queryByPasswordPolicyName(String passwordPolicyName)
    {
        return passwordPolicyDao.queryByPasswordPolicyName(passwordPolicyName);
    }
    
    public PasswordPolicyDao getPasswordPolicyDao()
    {
        return passwordPolicyDao;
    }
    
    public void setPasswordPolicyDao(PasswordPolicyDao passwordPolicyDao)
    {
        this.passwordPolicyDao = passwordPolicyDao;
    }
    
    public long getZero()
    {
        return zero;
    }
    
    public void setZero(long zero)
    {
        this.zero = zero;
    }
    
    @Override
	public SearchResult sort(Map map, Page page) {

		int rowsCount = passwordPolicyDao.count(map);
		page.setTotalCount(rowsCount);
		String field = (String) map.get("field");
		String sortType = (String) map.get("sortType");
		String str = " \"" + field + "\"" + " " + sortType;
		List<PasswordPolicy> list = passwordPolicyDao.queryBysort(str,
				page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;

	}

	@Override
	public List<User> queryUserByPasswordPolicyId(long id) {
		
		return passwordPolicyDao.queryUserByPasswordPolicyId(id);
	}
}
