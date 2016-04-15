package com.soc.dao.events;

import java.util.List;
import java.util.Map;

import com.soc.model.events.OriginalEvents;

public interface OriginalLogDao
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
     * <查询原始事件count>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    
    /**
     * <查询原始事件列表>
     * <功能详细描述>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<OriginalEvents> queryForList(Map map,int startRow, int pageSize);
    /**
     * <导出原始事件>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<OriginalEvents> exportOriginalEvents(Map map);
    
    
}
