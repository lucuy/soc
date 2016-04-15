package com.soc.service.audit;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.BaseObject;
import com.soc.model.audit.Audit;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <审计Service> <对系统审计的相关操作：查询审计，添加审计>
 * @author shenhaiguang
 * @version
 * @see [相关类/方法]
 * @since
 */
public interface AuditService extends Serializable
{
    
    /**
     * 返回系统审计记录数
     * 根据条件返回系统审计记录数
     * @param Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    
    public int count(Map map);
    
    /**
     * <按分页查询审计> 
     * <根据条件查询系统审计，分页展示>
     * @param Map,page
     * @return SearchResult
     * @see [类、类#方法、类#成员]
     */
    public SearchResult query(Map map, Page page);
    
    /**
     * 报表导出
     * 根据条件导出审计信息
     * @param Map
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    
    public List<Map> auditExport(Map map);
    
    /**
     * 添加一条记录
     * 添加一条记录
     * @param Audit
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int insert(Audit audit);
    
    /**
     * 新增操作系统审计
     * 记录用户新增操作
     * @param Integer , String, String, BaseObject
     * @return int
     */
    public int insertByInsertOperator(long userId, String module, String sourceIP, BaseObject object);
    
    /**
     * 修改操作系统审计
     * 记录用户修改操作
     * @param Integer , String, String, BaseObject
     * @return int
     */
    public int insertByUpdateOperator(long userId, String moduleDesc, String sourceIP, BaseObject objectSrc,
        BaseObject objectNew);
    
    /**
     * 新增操作系统审计
     * 记录用户新增操作
     * @param Integer , String, String, List<String>
     * @return int
     */
    public int insertByInsertOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList);
    
    /**
     * 修改操作系统审计
     * 记录用户修改操作
     * @param Integer , String, String, List<String>
     * @return int
     */
    public int insertByUpdateOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList);
    
    /**
     * 修改操作系统审计
     * 记录用户修改操作
     * @param Integer , String, String, List<String>
     * @return int
     */
    public int insertBySystemOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList);
    
    /**
     * 删除操作系统审计
     * 记录用户删除操作
     * @param Integer , String, String, List<String>
     * @return int
     */
    public int insertByDeleteOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList);
    
    /**
     * 登录操作系统审计
     * 记录用户登录信息
     * @param Integer, String, List<String>
     * @return int
     */
    public int insertByLoginOperator(long userId, String moduleDesc, String sourceIP, List<String> fieldList);
    
    /**
     * 对查询结果进行排序
     * 记录用户登录信息
     * @param Map, Page
     * @return List
     */
    public SearchResult sortAudit(Map map,Page page) ; 
}
