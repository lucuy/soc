package com.soc.service.policy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.policy.PasswordPolicy;
import com.soc.model.user.User;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <密码策略Service类> <对密码策略的相关操作：查询密码策略、修改密码策略、增加密码策略、删除密码策略>
 * 
 * @author 曹理冬
 * @version [V100R001C001]
 * @see [相关类/方法]
 * @since [soc v3.6.0.1]
 */
public interface PasswordPolicyService extends Serializable
{
    
    /**
     * 
     * 记录符合条件的时间策略记录条数
     * 
     * @param map Map
     * @return int
     * 
     */
    public int count(Map map);
    
    /**
     * 按条件分页查询密码策略
     * 
     * @param map Map
     * @param page Page
     * @return 返回符合查询条件的密码策略结果列表
     */
    public SearchResult query(Map map, Page page);
    
    /**
     * 按条件分页查询密码策略
     * 
     * @param map Map
     * @return 返回符合查询条件的密码策略结果列表
     */
    public List<PasswordPolicy> query(Map map);
    
    /**
     * 根据ID查询密码策略
     * 
     * @param passwordPolicyId long
     * @return PasswordPolicy
     * 
     */
    public PasswordPolicy queryById(long passwordPolicyId);
    
    /**
     * 更新密码策略的状态
     * 
     * @param id long
     * @param passwordPolicyStatus int
     * @return 
     */
    public void updatePasswordPolicyStatus(long id, int passwordPolicyStatus);
    
    /**
     * 新增或修改密码策略信息
     * 
     * @param passwordPolicy String
     * @return 返回用户的ID
     */
    public long update(PasswordPolicy passwordPolicy);
    
    /**
     * 根据ID删除密码策略
     * 
     * @param passwordPolicyId long
     * @return 
     */
    public void deletePasswordPolicy(long passwordPolicyId);
    
    /**
     * 根据策略名查询策略
     * 
     * @param passwordPolicyName String
     * @return List<PasswordPolicy>
     * 
     */
    public List<PasswordPolicy> queryByPasswordPolicyName(String passwordPolicyName);
    
    /**
     * 排序
     * 
     * @param map Map
     * @param page Page
     * @return SearchResult
     * 
     */
    public SearchResult sort(Map map,Page page);
    
    
    /**
     * 根据密码策略Id查询所有与之关联的用户
     * @param id
     * @return
     */
    public List<User> queryUserByPasswordPolicyId(long id);
}
