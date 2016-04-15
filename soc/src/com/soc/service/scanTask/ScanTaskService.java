package com.soc.service.scanTask;

import java.io.Serializable;
import java.util.Map;

import com.soc.model.ScanTask.ScanTask;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 漏扫联动service类
 * 漏扫联动的查询，修改，添加，删除,漏扫联动状态的改变
 * 
 * @author  lichanghong
 * @version  [V100R001C001, 2012-8-11]
 * @see  com.soc.dao.scanTask.ScanTaskDao
 * @since  [安全策略管理/漏扫联动/V100R001C001]
 */
public interface ScanTaskService extends Serializable{
	
	 /**
	     * 查询符合条件的记录
	     * @param map Map
	     * @param startRow int
	     * @param pageSize int
	     * @return List<TimePolicy>
	     * @see [类、类#方法、类#成员]
	     */
	 @SuppressWarnings("rawtypes")
	public SearchResult queryAllScanTasks(Map map, Page page);
	 /**
	     * 根据Id查询漏扫联动对象
	     * @param ID long
	     * @return  ScanTask
	     * @see [类、类#方法、类#成员]
	     */
	 public ScanTask queryScanTaskById(long id);
	 /**
	     * 根据名称查询漏扫联动对象
	     * @param name String
	     * @return  ScanTask
	     * @see [类、类#方法、类#成员]
	     */
	 public ScanTask queryScanTaskByName(String name);
	 /**
	     * 根据根据文件名查询漏扫联动对象
	     * @param name String
	     * @return  ScanTask
	     * @see [类、类#方法、类#成员]
	     */
	 public String queryScanTaskByFileName(String fileName);
	 /**
	     * 根据id删除
	     * @param id long
	     * @see [类、类#方法、类#成员]
	     */
	 public void deleteById(long id);
	 /**
	     * 修改漏扫联动
	     * @param scanTask ScanTask
	     * @see [类、类#方法、类#成员]
	     */
	 public void updateScanTask(ScanTask scanTask);
	 
	 /**
	     * 根据ID修改漏扫联动
	     * @param id long
	     * @see [类、类#方法、类#成员]
	     */
	 public void updateStateById(long id);
	 /**
	  * 根据文件名修改漏扫联动
	  * @param fielname String
	  * @see [类、类#方法、类#成员]
	  */
	public void updateStateByFileName(String fileName);
	
	public void insertScanTask(ScanTask scanTask);
}
