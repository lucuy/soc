package com.soc.dao.policy;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.user.User;

/**
 * 
 * <Description: 密码策略Dao> <对相关密码策略操作：查询密码策略条数、添加密码策略、修改密码策略、删除密码策略、查询密码策略>
 * 
 * @author 曹理冬
 * @version [V100R001C001, 2012-8-5]
 * @see [相关类/方法]
 * @since [soc v3.6.0.1]
 */
public interface PasswordPolicyDao extends BaseDao
{
    /**
     * <查密码策略条数>
     * <功能详细描述>
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map int
     * @param startRow int
     * @param pageSize int
     * @return List<PasswordPolicy> 
     * @see [类、类#方法、类#成员]
     */
    public List<PasswordPolicy> query(Map map, int startRow, int pageSize);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map int
     * @return List<PasswordPolicy> 
     * @see [类、类#方法、类#成员]
     */
    public List<PasswordPolicy> query(Map map);
    
    /**
     * <根据Id查询密码策略>
     * 
     * @param passwordPolicyId long
     * @return PasswordPolicy的详细信息
     */
    public PasswordPolicy queryById(long passwordPolicyId);
    
    /**
     * <新增一条密码策略>
     * 
     * @param passwordPolicy String
     * @return 新增密码策略的Id
     */
    public long insert(PasswordPolicy passwordPolicy);
    
    /**
     * 根据策略名称查询策略
     * 
     * @param passwordPolicyName String
     * @return List<PasswordPolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<PasswordPolicy> queryByPasswordPolicyName(String passwordPolicyName);
    
    /**
     * <修改一条密码策略>
     * 
     * @param passwordPolicy  String
     * @return
     */
    public void update(PasswordPolicy passwordPolicy);
    
    /**
     * <更新密码策略状态>
     * 
     * @param map Map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updatePasswordPolicyStatus(Map map);
    
    /**
     * 根据ID删除密码策略
     * 
     * @param passwordPolicyId long
     * @return
     */
    public void deletePasswordPolicy(long passwordPolicyId);
    
    /**
     * 排序
     * 
     * @param str String 
     * @param startRow int
     * @param pageSize int
     * @return
     */
    public List<PasswordPolicy> queryBysort(String str,int startRow,int pageSize);
    
    
    /**
     * 通过密码策略Id获得与之关联的用户
     * 
     * @param id long
     * @return 
     */
    public List<User> queryUserByPasswordPolicyId(long id);
}
 