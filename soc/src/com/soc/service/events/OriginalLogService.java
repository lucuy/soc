package com.soc.service.events;

import java.util.Map;

import com.util.page.Page;
import com.util.page.SearchResult;

public interface OriginalLogService
{
    /**
     * 插入原始日志
     * @param map Map<String, Object>
     */
    public void insertOrginalLog(Map<String, Object> map);
    
    /**
     * 查询原始日志
     * @param identification long
     * @return String
     */
    public String queryOriginalLog(Map<String, Object> map);
    
    /**
     * <判断表是否存在>
     * <功能详细描述>
     * @param tableName String
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public int existsTable(String tableName);
    
    /**
     * <判断表是否存在>
     * <功能详细描述>
     * @param seqName String
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String existsSeq(String seqName);
    
    /**
     * <创建表序列>
     * <功能详细描述>
     * @param seqName
     * @see [类、类#方法、类#成员]
     */
    public void createOriginalLogSeq(String seqName);
    
    /**
     * <按日期建表>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void createOriginalLogTable(Map<String, String> map);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @param page
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult queryOriginalEvents(Map map, Page page);
    
    
}
