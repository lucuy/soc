package com.soc.dao.audit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.asset.AssetGroup;
import com.soc.model.audit.AuditReport;
import com.soc.model.events.Events;

/**
 * 
 * <显示报表树的dao类> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AuditReportDao {
	public List<AuditReport> queryByParentId(Map map);

	/**
	 * <查询当天的设备明细列表> <功能详细描述>
	 * 
	 * @param date
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> queryEquipmentDetailList(Map map);

	public  List<Map<String, Object>> report6Table1(String tableName);

	public List<Map<String, Object>> report6Table2(String tableName);

	public List<Map<String, Object>> report6Table3(String tableName);

	public List<Map<String, Object>> report6Table4(String tableName);

	public List<Map<String, Object>> report6Table5(String tableName);

	public int existsTable(String tableName);

	public List<Map<String, Object>> report2Table1(String string);

	public List<Map<String, Object>> report2Table12(String string);

	public List<Map<String, Object>> report2Table2(String string);

	public List<Map<String, Object>> report2Table3(String string);

	public List<Map<String, Object>> report2Table4(String string);

	public List<Map<String, Object>> report2Table5(String string);

	public List<Map<String, Object>> report2Table6(String string);

	public List<Map<String, Object>> report2Table7(String string);

	public List<Map<String, Object>> report2Table8(String string);

	public List<Map<String, Object>> report2Table9(String string);

	public List<Map<String, Object>> report2Table10(String string);

	public List<Map<String, Object>> report8Table1(String string);

	public List<Map<String, Object>> report8Table3(String string);

	public List<Map<String, Object>> report8Table2(String string);

	public List<Map<String, Object>> report8Table4(String string);

	public List<Map<String, Object>> report8Table5(String string);

	public List<Map<String, Object>> report9Table1(long groupid);

	public int report9Table1Count(long groupid);

	public List<Map<String, Object>> report9Table2(long groupid);

	public int report9Table2Count(Map map);

	public List<Map<String, Object>> report7Table1();
	public List<AssetGroup> queryAllAssetGroup();
	/**
     * 根据资产组id查询关联的资产总数
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public int countByAssetGroupId(Map map);
	
	/**
     * 根据资产组id查询资产组名称
     * <功能详细描述>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
	public String AssetGroupNameById(long id);

	public List<Map<String, Object>> report10Table2(Map map);

	public List<Map<String, Object>> report12Table1(Map map);

	List<Map<String, Object>> report12Table2(Map map);

	public List<Map<String, Object>> report12Table3(Map map);
/**
 * 陕西台事件日报
 * @param map
 * @return
 */
	public List<Map<String, Object>> report13Table1(Map map);
	/**
	 * 陕西台阀值日报
	 * @param map
	 * @return
	 */
public List<Map<String, Object>> report13Table2(Map map);
/**
 * 陕西台周报
 * @param map
 * @return
 */
	public List<Map<String, Object>> report14Table1(Map map);

public HashMap<String, Object> report14Table2Col1(Map<String, Object> map);//list的一个元素
public HashMap<String, Object> report14Table2Col2(Map<String, Object> map);//list的2个元素
public HashMap<String, Object> report14Table2Col3(Map<String, Object> map);//list的3个元素
public HashMap<String, Object> report14Table2Col4(Map<String, Object> map);//list的4个元素
/**
 * 陕西台周报 table2
 * @param map
 * @return
 */
public List<Map<String, Object>> report14Table2(String string);
	}
