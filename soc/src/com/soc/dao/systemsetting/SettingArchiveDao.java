package com.soc.dao.systemsetting;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.SettingArchive;

/**
 * 
 * <归档>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SettingArchiveDao
{
    /**
     * <查询归档的所有的表>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<SettingArchive> queryArchiveList(Map map, int startRow, int pageSize);
    
    public int associationPage(Map map);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param archive
     * @see [类、类#方法、类#成员]
     */
    public void updataById(SettingArchive archive);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param archive
     * @see [类、类#方法、类#成员]
     */
    public void createArchive(SettingArchive archive);
    
    public SettingArchive queryById(long checkid);
    
    public List<SettingArchive> selectArchiveAuto(Map<String,Long> archive);
}