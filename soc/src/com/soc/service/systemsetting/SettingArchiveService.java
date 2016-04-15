package com.soc.service.systemsetting;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.SettingArchive;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SettingArchiveService
{
    /**
     * <查询归档的所有的表>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public SearchResult queryPage(Map map, Page page);
    /**
     * <更新>
     * <功能详细描述>
     * @param archive
     * @see [类、类#方法、类#成员]
     */
    public String updataById(SettingArchive archive,long archiveStatus,long checkid);
    /**
     * <插入数据>
     * <功能详细描述>
     * @param archive
     * @see [类、类#方法、类#成员]
     */
    public void createArchive(String tableName);
    
    public List<SettingArchive> selectArchiveAuto(Map<String,Long> archive);
    
    public void archiveAuto(List<SettingArchive> selectArchive);
    public SettingArchive queryById(long checkid);
}