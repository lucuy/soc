package com.soc.service.audit;

import java.util.List;
import java.util.Map;

import com.soc.model.events.Events;

/**
 * 
 * <审计报表的serice接口> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AuditReportService {
	/**
	 * <获得左侧的报表熟> <功能详细描述>
	 * 
	 * @param path
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getTreeStyle(String path);

	/**
	 * <是否存在表> <功能详细描述>
	 * 
	 * @param tableName
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int existsTable(String tableName);

	/**
	 * <查询设备明细> <功能详细描述>
	 * 
	 * @param string
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> queryEquipmentDetailList(Map map);

	/**
	 * <基于事件类别的事件> <功能详细描述>
	 * 
	 * @return 设备类别:出现次数
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> report6Table1(List<String> date,String assertIds,String categoryIds);

	/**
	 * <基于事件类别的事件 > <功能详细描述>
	 * 
	 * @return 事件类别:出现次数
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> report6Table2(List<String> date,String assertIds,String categoryIds);

	/**
	 * <基于设备类别的事件> <功能详细描述>
	 * 
	 * @return 设备类别:出现次数
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> report6Table3(List<String> date,String assertIds,String categoryIds);

	/**
	 * <发生次数最多的前20个事件类型 > <功能详细描述>
	 * 
	 * @return 设备类别:出现次数
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> report6Table4(List<String> date,String assertIds,String categoryIds);

	/**
	 * <上报事件最多的前10个设备类型 > <功能详细描述>
	 * 
	 * @return 设备类别:出现次数
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> report6Table5(List<String> date,String assertIds,String categoryIds);

	/**
	 * <判断表命是否存在存在的返回表名名字是用日期命名的要> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<String> getMonthReportTableName(int month);

	public List<Map<String, Object>> report2Table1(List<String> date);

	public List<Map<String, Object>> report2Table12(List<String> date);

	public List<Map<String, Object>> report2Table2(List<String> date);

	public List<Map<String, Object>> report2Table3(List<String> date);

	public List<Map<String, Object>> report2Table4(List<String> date);

	public List<Map<String, Object>> report2Table5(List<String> date);

	public List<Map<String, Object>> report2Table6(List<String> date);

	public List<Map<String, Object>> report2Table7(List<String> date);

	public List<Map<String, Object>> report2Table8(List<String> date);

	public List<Map<String, Object>> report2Table9(List<String> date);

	public List<Map<String, Object>> report2Table10(List<String> date);

	public List<Map<String, Object>> report8Table1(List<String> dateWeek,String assetId);


	public List<Map<String, Object>> report8Table2(List<String> dateWeek,String assetId);

	public List<Map<String, Object>> report8Table3(List<String> dateWeek,String assetId);

	public List<Map<String, Object>> report8Table4(List<String> dateWeek,String assetId);

	public List<Map<String, Object>> report8Table5(List<String> date,String assetId);

	public List<Map<String, Object>> report9Table1(long groupid);

	public int report9Table1Count(long groupid);

	public List<Map<String, Object>> report9Table2(long groupid);

	public int report9Table2Count(Map map);

	public List<Map<String, Object>> report7Table1();


/**
 * <获得导出文件的map>
 * <功能详细描述>
 * @param auditReportId
 * @param path
 * @param reprotType
 * @return
 * @see [类、类#方法、类#成员]
 */
	public Map<String, Object> getFreemakerMapExport(long auditReportId,
			String path, String reprotType);

List<Map<String, Object>> report10Table1(String assetId);

List<Map<String, Object>> report11Table1(String assetId);
List<Map<String, Object>> report12Table1(String assetId);
List<Map<String, Object>> report12Table2(String assetId);

List<Map<String, Object>> report12Table3(Map map);
/**
 * 陕西台日报
 * @return
 */
List<Map<String, Object>> report13Table1(String assetId);
/**
 * 陕西台日报
 * @return
 */
List<Map<String, Object>> report13Table2(String assetId);
/**
 *  获取以周为单位的表名
 * @param week
 * @return
 */
List<String> getWeekReportTableName(int week);
/**
 * 陕西周报按照事件类别分类
 * @return
 */
List<Map<String, Object>> report14Table1(String assetId);
/**
 *  陕西周报 关联后时间
 * @return
 */
List<Map<String, Object>> report14Table1Relance(String assetId);
/**
 * 陕西周报
 * @param timeInMillis
 * @return
 */
List<Map<String, Object>> report14Table2(String categroy,String assetId);
/**
 * <外部审计功能>
 * <功能详细描述>
 * @param long auditReportId 通过此参数来判断查询的是什么报表
 * @param String aasetId 通过用户关联的资产组来获取资产组下关联的资产id串，如果是关联全部资产参数为null，如果关联的是资产组没有资产则为0
 * @param long groupid 用户关联的资产组id
 * @return Map
 * @see [类、类#方法、类#成员]
 */
public Map<String, Object> getFreemakerMapPageCustoms(long auditReportId,
		String assetId, long groupid, String assetIds, String customs,
		String beginTime, String endTime,String categIds);

}
