package com.soc.service.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.soc.model.user.User;
import com.soc.model.warn.Warn;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 
 * <预警发布服务接口>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface WarnService
{
    /**
     * 
     * 记录符合条件的记录条数
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
     * <查询所有的预警>
     * <功能详细描述>
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public List<Warn> findAllWarn();
    
    /**
     * <新增预警>
     * <功能详细描述>
     * @param warn
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    
    public int insertWarn(Warn warn);
    
    /**
     * <通过warnId查询预警>
     * <功能详细描述>
     * @param warn
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public Warn queryById(Warn warn);
    
    /**
     * <删除预警>
     * <功能详细描述>
     * @param result
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public int deleteWarn(String result);
    
    /**
     * <修改预警>
     * <功能详细描述>
     * @param warn
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public void updateWarn(Warn warn);
    
    /**
     * <排序列表>
     * @param map
     * @param page
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public SearchResult sort(Map map,Page page);
    
    /**
     * <发送邮件>
     * @param map
     * @param page
     * @see [类、类#方法、类#成员]
     */
    public void sendEmail(String message, String toEmialAdds);
    
    /**
     * <处理邮件发送的内容和资产负责人的Email地址信息>
     * @param 资产负责人
     */
    public void emailHandling(List<User> user,Warn warn);
}
