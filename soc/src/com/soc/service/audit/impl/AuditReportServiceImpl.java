package com.soc.service.audit.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.net.aso.s;

import com.soc.dao.audit.AuditReportDao;
import com.soc.model.audit.AuditReport;
import com.soc.model.conf.GlobalConfig;
import com.soc.service.audit.AuditReportService;
import com.util.DateUtil;
import com.util.ImageToBase64;
import com.util.StringUtil;

public class AuditReportServiceImpl implements AuditReportService {
	private StringBuffer treeBuff;
	private AuditReportDao auditReportDao;
	private static final String PICTURE_NAME_SEED = "arrow_03.gif"; // 表示页子节点的图片
	private static final String PICTURE_NAME_CLOSE = "u321_normal.gif"; // 表示关闭的图片
	private AuditReport auditReport;
	// 基于事件级别统计这一个月发生的次数
	private List<Integer> table1ThisMonth;
	// 基于事件级别统计上一个月发生的次数
	private List<Integer> table1LastMonth;

	// 安全月报 设备明细列表freemarker2.ftl用到
	// 基于事件级别统计
	private List<Map<String, Object>> report2Table1;
	private List<Map<String, Object>> report2Table12;
	// 事件按设备统计
	private List<Map<String, Object>> report2Table3;
	// 按安全域统计
	private List<Map<String, Object>> report2Table2;
	// 按安全域统计
	private List<Map<String, Object>> report2Table4;
	// 发生事件次数统计TOP20
	private List<Map<String, Object>> report2Table5;
	private List<Map<String, Object>> report2Table6;
	private List<Map<String, Object>> report2Table7;
	private List<Map<String, Object>> report2Table8;
	private List<Map<String, Object>> report2Table9;
	private List<Map<String, Object>> report2Table10;
	// 按安全域统计
	// private List<Integer> table1LastMonth;
	// 设备明细列表freemarker5.ftl用到
	private List<Map<String, Object>> equipmentDetailList;
	// 设备明细列表freemarker5.ftl用到

	// freemarker6.ftl用到
	// 基于事件级别的事件
	private List<Map<String, Object>> report6Table1;
	// 基于事件类别的事件
	private List<Map<String, Object>> report6Table2;
	// 基于设备类别的事件
	private List<Map<String, Object>> report6Table3;
	// 发生次数最多的前20个事件类型
	private List<Map<String, Object>> report6Table4;
	// 上报事件最多的前10个设备类型
	private List<Map<String, Object>> report6Table5;
	private List<Map<String, Object>> report7Table1;
	// 安全月报 设备明细列表freemarker8.ftl用到
	// 基于事件级别的事件分布列表
	private List<Map<String, Object>> report8Table1;
	// 基于事件类别报警数量分布列表
	private List<Map<String, Object>> report8Table2;
	// 基于事件类别的中高级、高级事件数量统计表
	private List<Map<String, Object>> report8Table3;
	// 危害性高的前20个事件类型列表
	private List<Map<String, Object>> report8Table4;
	// 危害性高的前20个事件类型列表
	private List<Map<String, Object>> report8Table5;

	// 安全月报 设备明细列表freemarker9.ftl用到
	// 资产类型列表
	private List<Map<String, Object>> report9Table1;
	private List<Map<String, Object>> report9Table2;
	// 做防火墙事件的变量
	private List<Map<String, Object>> report10Table1;
	private List<Map<String, Object>> report11Table1;
	// 告警报表
	private List<Map<String, Object>> report12Table1;// 告警类型统计
	private List<Map<String, Object>> report12Table2;// 告警等级统计
	private List<Map<String, Object>> report12Table3;// 严重告警次数最多的10个设备
	private List<Map<String, Object>> report12Table4;// 总告警数最多的10个设备
	private List<Map<String, Object>> report13Table1;// 陕西台事件告警
	private List<Map<String, Object>> report13Table2;// 陕西台阀值告警
	private List<Map<String, Object>> report14Table1;// 陕西台告警周报按照事件类别分类
	private List<Map<String, Object>> report14Table2;// 病毒木马
	private List<Map<String, Object>> report14Table3;// 未知类型
	private List<Map<String, Object>> report14Table4;// 系统状态
	private List<Map<String, Object>> report14Table5;// 扫描探测
	private List<Map<String, Object>> report14Table6;// 拒绝服务
	private List<Map<String, Object>> report14Table7;// 规避
	private List<Map<String, Object>> report14Table8;// 认证授权
	private List<Map<String, Object>> report14Table9;// 应用漏洞
	private List<Map<String, Object>> report14Table10;// 非授权访问
	private int report9Table1Count;
	private int report9Table2Count;
	// 用来显示当前时间 显示格式yyyy-MM-DD hh:mm:ss
	private String timeDetail;
	// 显示年月日
	private String time;
	// 用来存放表名的字符串list
	private List<String> date;
	// 用来获得这个星期的表明的字符串格式tbl_20130303
	private List<String> dateWeek;

	/**
	 * 李长红修改，根据用户关联的资产组来做权限控制
	 */
	@Override
	public Map<String, Object> getFreemakerMapPageCustoms(long auditReportId,
			String aasetId, long groupid, String assetIds, String customs,
			String beginTime, String endTime, String categoryIds) {
		if ("1".equals(customs)) {// 说明是自定义的条件6
			date = getReportTableNameCostoms(beginTime, endTime);
		} else {
			date = getMonthReportTableName(-1);
		}

		dateWeek = getWeekReportTableName(0);
		Map<String, Object> assetMap = new HashMap<String, Object>();
		assetMap.put("assetId", aasetId);
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar c8 = Calendar.getInstance();
		String endOfWeek = new SimpleDateFormat("yyyy年MM月dd日").format(c8
				.getTime());
		c8.add(Calendar.DATE, -7);
		String startOfWeek = new SimpleDateFormat("yyyy年MM月dd日").format(c8
				.getTime());
		switch ((int) auditReportId) {
		case 1:
		case 2:
			// long s = System.currentTimeMillis();
			report2Table1 = report2Table1(date);
			report2Table12 = report2Table12(getMonthReportTableName(-2));
			if (report2Table12.isEmpty()) {
				// BigDecimal zero = new BigDecimal(0);
				int zero = 0;
				Map<String, Object> mapReport2Table12 = new HashMap<String, Object>();
				mapReport2Table12.put("priority6", zero);
				mapReport2Table12.put("priority7", zero);
				mapReport2Table12.put("priority8", zero);
				mapReport2Table12.put("priority9", zero);
				mapReport2Table12.put("priority10", zero);
				report2Table12.add(mapReport2Table12);
			}

			if (!report2Table1.isEmpty()) {

				// System.out.println(report2Table1.get(0));
				report2Table1.get(0).put("priority6",
						report2Table12.get(0).get("priority6"));
				report2Table1.get(0).put("priority7",
						report2Table12.get(0).get("priority7"));
				report2Table1.get(0).put("priority8",
						report2Table12.get(0).get("priority8"));
				report2Table1.get(0).put("priority9",
						report2Table12.get(0).get("priority9"));
				report2Table1.get(0).put("priority10",
						report2Table12.get(0).get("priority10"));
			}
			map.put("report2Table1", report2Table1);
			report2Table2 = report2Table2(date);
			map.put("report2Table2", report2Table2);
			report2Table3 = report2Table3(date);
			if (report2Table3 != null) {
				for (Map<String, Object> table3 : report2Table3) {
					// 这里查出的数据没有查出总计,在界面的不能排序,在这里加入一个总计的key value
					table3.put(
							"total",
							Long.parseLong(table3.get("priority1").toString())
									+ Long.parseLong(table3.get("priority2")
											.toString())
									+ Long.parseLong(table3.get("priority3")
											.toString())
									+ Long.parseLong(table3.get("priority4")
											.toString())
									+ Long.parseLong(table3.get("priority5")
											.toString()));
				}
			}
			map.put("report2Table3", report2Table3);
			report2Table4 = report2Table4(date);
			map.put("report2Table4", report2Table4);
			report2Table5 = report2Table5(date);
			if (report2Table5 != null) {

				for (Map<String, Object> table5 : report2Table5) {
					table5.put(
							"devaddr",
							this.lIpTransitionStrIp(Long.parseLong(table5.get(
									"devaddr").toString())));
				}
				// 把用数字表示的级别变成汉子形式
				for (Map<String, Object> map1 : report2Table5) {
					if (map1.get("priority").toString().equals("1")) {
						map1.put("priority", "信息");
					} else if (map1.get("priority").toString().equals("2")) {
						map1.put("priority", "低级");
					} else if (map1.get("priority").toString().equals("3")) {
						map1.put("priority", "中级");
					} else if (map1.get("priority").toString().equals("4")) {
						map1.put("priority", "高级");
					} else {
						map1.put("priority", "严重");
					}

				}

				map.put("report2Table5", report2Table5);
				report2Table6 = report2Table6(date);
				// 转换 事件类型和ip
				for (Map<String, Object> table6 : report2Table6) {
					String str = GlobalConfig.eventTypeTag.get(Long
							.parseLong(table6.get("type").toString()));
					table6.put("type", str != null ? str : " ");
				}
			}
			// 转换 事件类型和ip

			map.put("report2Table6", report2Table6);

			report2Table7 = report2Table7(date);
			if (report2Table7 != null) {
				// 转换 事件类型和ip
				for (Map<String, Object> table7 : report2Table7) {
					String str = GlobalConfig.eventTypeTag.get(Long
							.parseLong(table7.get("type").toString()));
					table7.put("type", str != null ? str : " ");
				}
			}
			map.put("report2Table7", report2Table7);
			report2Table8 = report2Table8(date);
			if (report2Table8 != null) {
				// 转换 事件类型和ip
				for (Map<String, Object> table8 : report2Table8) {
					String str = GlobalConfig.eventTypeTag.get(Long
							.parseLong(table8.get("type").toString()));
					table8.put("type", str != null ? str : " ");
				}
			}
			map.put("report2Table8", report2Table8);
			report2Table9 = report2Table9(date);
			if (report2Table9 != null) {
				// 转换 事件类型和ip
				for (Map<String, Object> table9 : report2Table9) {
					String str = GlobalConfig.eventTypeTag.get(Long
							.parseLong(table9.get("type").toString()));
					table9.put("type", str != null ? str : " ");
				}
			}
			map.put("report2Table9", report2Table9);
			report2Table10 = report2Table10(date);
			if (report2Table10 != null) {
				// 转换 事件类型和ip
				for (Map<String, Object> table10 : report2Table10) {
					String str = GlobalConfig.eventTypeTag.get(Long
							.parseLong(table10.get("type").toString()));
					table10.put("type", str != null ? str : " ");
				}
			}

			map.put("report2Table10", report2Table10);
			break;

		case 3:

			break;
		case 4:

			break;

		case 5:
			equipmentDetailList = queryEquipmentDetailList(assetMap);
			// 把里面的lang类型的time变成 date 显示方便
			time = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
			// 把long类型的ip转换成常规形式
			for (Map<String, Object> equipmentDetailListMap : equipmentDetailList) {
				equipmentDetailListMap.put("receptTime", this
						.formartTime((Long) equipmentDetailListMap
								.get("receptTime")));
				equipmentDetailListMap.put("dAddr", this
						.lIpTransitionStrIp((Long) equipmentDetailListMap
								.get("dAddr")));
			}
			for (Map<String, Object> map1 : equipmentDetailList) {
				if (map1.get("priority").toString().equals("1")) {
					map1.put("priority", "信息");
				} else if (map1.get("priority").toString().equals("2")) {
					map1.put("priority", "低级");
				} else if (map1.get("priority").toString().equals("3")) {
					map1.put("priority", "中级");
				} else if (map1.get("priority").toString().equals("4")) {
					map1.put("priority", "高级");
				} else {
					map1.put("priority", "严重");
				}
			}

			int i = equipmentDetailList.size();
			map.put("time", time);
			map.put("equipmentDetailList", equipmentDetailList);
			break;
		case 6:
			//将参数assetIds换成aasetId解决事件综合分析报表（月报）无数据的问题
			if (StringUtil.isEmpty(categoryIds)) {
				categoryIds = "'0'";
			} else {
				categoryIds = categoryIds + "'0'";// 加一个0就不用去掉最后的 ""
			}
			StringBuffer report6Data1 = new StringBuffer();// 图表要用的数据
			List<Map<String, Object>> report6 = new ArrayList<Map<String, Object>>();// 去掉空数据的临时变量;
			// 基于事件级别的事件
			report6Table1 = report6Table1(date, aasetId, categoryIds);
			int sumEventsReport6 = 0;
			List<Map<String, Object>> report6Table1Temp = new ArrayList<Map<String, Object>>();// 去掉等级是0的事件
			if (report6Table1.size() != 0) {
				for (Map<String, Object> map1 : report6Table1) {
					if (map1.get("priority").toString().equals("1")) {
						map1.put("priority", "信息");
					} else if (map1.get("priority").toString().equals("2")) {
						map1.put("priority", "低级");
					} else if (map1.get("priority").toString().equals("3")) {
						map1.put("priority", "中级");
					} else if (map1.get("priority").toString().equals("4")) {
						map1.put("priority", "高级");
					} else if (map1.get("priority").toString().equals("5")) {
						map1.put("priority", "严重");
					} else {
						report6Table1Temp.add(map1);// 等级是0就放进临时list
					}
				}
				report6Table1.removeAll(report6Table1Temp);
				for (Map<String, Object> reportTable1 : report6Table1) {// 算事件总数
					sumEventsReport6 = sumEventsReport6
							+ Integer.parseInt(reportTable1.get("count")
									.toString());
				}
			}
			// 基于事件类别的事件
			report6Table2 = report6Table2(date, aasetId, categoryIds);
			for (Map<String, Object> table2 : report6Table2) {
				String s = (String) table2.get("type");
				if (table2.get("type").equals("")) {
					report6.add(table2);
				}
			}
			report6Table2.removeAll(report6);
			// 基于设备类别的事件
			report6Table3 = report6Table3(date, aasetId, categoryIds);
			// 发生次数最多的前20个事件类型
			report6Table4 = report6Table4(date, aasetId, categoryIds);
			for (Map<String, Object> table4 : report6Table4) {
				String s = (String) table4.get("type");
				if (table4.get("type").equals("")) {
					report6.add(table4);
				}
			}
			report6Table4.removeAll(report6);
			// 上报事件最多的前10个设备类型
			report6Table5 = report6Table5(date, aasetId, categoryIds);
			map.put("sumEventsReport6", sumEventsReport6);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			if ("1".equals(customs)) {// 说明是自定义的事件报表，时间处理一下
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				Date start;
				try {
					start = sd.parse(beginTime);
					Date end = sd.parse(endTime);
					sd.applyPattern("yyyy年M月dd日");
					time = sd.format(start) + "到" + sd.format(end);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				time = new SimpleDateFormat("yyyy年M月").format(c.getTime());
			}
			map.put("time", time);
			map.put("report6Table1", report6Table1);
			if (report6Table2.size() != 0) {
				String str = null;
				for (Map<String, Object> table2 : report6Table2) {
					try {
						str = GlobalConfig.eventTypeTag.get(Long
								.parseLong(table2.get("type").toString()));
						if (str != null) {// 如果str==null说明在eventTypeTag找不到对应的字段
											// 放原来的东西
							table2.put("type", str != null ? str : " ");
						}

					} catch (Exception e) {
						// TODO: handle exception
					}
					// table2.put("type", str != null ? str :
					// table2.get("type").toString());
				}
			}
			map.put("report6Table2", report6Table2);
			List listTempReport6Table3 = new ArrayList();// 用来放要删除的元素的因为type可能是null
			for (Map<String, Object> table3 : report6Table3) {

				try {
					table3.get("type").toString();
				} catch (Exception e) {
					listTempReport6Table3.add(table3);
				}

			}
			report6Table3.removeAll(listTempReport6Table3);// 清除元素
			map.put("report6Table3", report6Table3);
			map.put("report6Table4", report6Table4);
			if (report6Table4.size() != 0) {
				String str = null;
				for (Map<String, Object> table4 : report6Table4) {
					try {
						str = GlobalConfig.eventTypeTag.get(Long
								.parseLong(table4.get("type").toString()));
						if (str != null) {// 如果str==null说明在eventTypeTag找不到对应的字段
											// 放原来的东西
							table4.put("type", str != null ? str : " ");
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					// table2.put("type", str != null ? str :
					// table2.get("type").toString());
				}
			}
			List listTempReport6Table5 = new ArrayList();// 用来放要删除的元素的因为type可能是null
			for (Map<String, Object> table5 : report6Table5) {

				try {
					table5.get("type").toString();
				} catch (Exception e) {
					listTempReport6Table5.add(table5);
				}

			}
			report6Table5.removeAll(listTempReport6Table5);// 清除元素
			map.put("report6Table5", report6Table5);
			if (report6Table3.size() != 0) {
				for (Map<String, Object> mapCategories : report6Table3) {// 处理数据,把数据变成highchart需要的形式
					report6Data1.append("['" + mapCategories.get("type") + "',"
							+ mapCategories.get("count") + "],");
				}
				report6Data1.delete(report6Data1.length() - 1,
						report6Data1.length());
			}
			map.put("report6Data1", report6Data1);
			break;
		case 7:
			report7Table1 = report7Table1();
			StringBuffer report7DataCategories = new StringBuffer();// 图表要用的数据
			StringBuffer totalValue = new StringBuffer();// 保密性价值
			StringBuffer secretValue = new StringBuffer();// 保密性价值
			StringBuffer integrityValue = new StringBuffer();// 完整性价值
			StringBuffer usabilityValue = new StringBuffer();// 可用性价值
			if (!report7Table1.isEmpty()) {
				for (Map<String, Object> mapCategories : report7Table1) {
					report7DataCategories.append("'"
							+ mapCategories.get("name") + "',");
					totalValue.append((Integer) mapCategories.get("secret")
							+ (Integer) mapCategories.get("secret")
							+ (Integer) mapCategories.get("usability") + ",");
					secretValue.append(mapCategories.get("secret") + ",");
					integrityValue.append(mapCategories.get("integrity") + ",");
					usabilityValue.append(mapCategories.get("usability") + ",");
				}
				report7DataCategories.delete(
						report7DataCategories.length() - 1,
						report7DataCategories.length());
				totalValue.delete(totalValue.length() - 1, totalValue.length());
				secretValue.delete(secretValue.length() - 1,
						secretValue.length());
				integrityValue.delete(integrityValue.length() - 1,
						integrityValue.length());
				usabilityValue.delete(usabilityValue.length() - 1,
						usabilityValue.length());
			}

			map.put("report7Table1", report7Table1);
			map.put("reportDataCategories", report7DataCategories);
			map.put("totalValue", totalValue);
			map.put("secretValue", secretValue);
			map.put("integrityValue", integrityValue);
			map.put("usabilityValue", usabilityValue);
			break;
		case 8:

			report8Table1 = report8Table1(dateWeek, aasetId);
			// 把用数字表示的级别变成汉子形式 这里也可以不转换,直接在ftl中if标签判断也行
			for (Map<String, Object> map1 : report8Table1) {
				if (map1.get("priority").toString().equals("1")) {
					map1.put("priority", "信息");
				} else if (map1.get("priority").toString().equals("2")) {
					map1.put("priority", "低级");
				} else if (map1.get("priority").toString().equals("3")) {
					map1.put("priority", "中级");
				} else if (map1.get("priority").toString().equals("4")) {
					map1.put("priority", "高级");
				} else {
					map1.put("priority", "严重");
				}
			}

			map.put("report8Table1", report8Table1);
			report8Table2 = report8Table2(dateWeek, aasetId);
			List<Map<String, Object>> report8Table2Tmp = new ArrayList<Map<String, Object>>();// 去掉空数据的临时变量;
			for (Map<String, Object> table2 : report8Table2) {
				String str = null;
				try {
					// String s = table2.get("type").toString();
					str = GlobalConfig.eventCategoryTag.get(table2.get("type"));
					if (str == null) {
						report8Table2Tmp.add(table2);
					}
				} catch (Exception e) {

				}
				table2.put("type", str != null ? str : table2.get("type")
						.toString());
			}

			report8Table2.removeAll(report8Table2Tmp);

			map.put("report8Table2", report8Table2);
			report8Table3 = report8Table3(dateWeek, aasetId);

			for (Map<String, Object> table3 : report8Table3) {
				String str = null;
				try {
					// String s = table2.get("type").toString();
					str = GlobalConfig.eventCategoryTag
							.get(table3.get("asset"));
					if (str == null) {
						report8Table2Tmp.add(table3);
					}
				} catch (Exception e) {
					// table3.put("asset", " ");
				}
				table3.put("asset", str != null ? str : table3.get("asset")
						.toString());

			}
			report8Table3.removeAll(report8Table2Tmp);

			map.put("report8Table3", report8Table3);
			report8Table4 = report8Table4(dateWeek, aasetId);
			List listTempReport8Table4 = new ArrayList();// 用来放要删除的元素的因为type可能是null
			for (Map<String, Object> table4 : report8Table4) {

				try {
					table4.get("type").toString();
				} catch (Exception e) {
					listTempReport8Table4.add(table4);
				}

			}
			report8Table4.removeAll(listTempReport8Table4);// 清除元素

			map.put("report8Table4", report8Table4);
			report8Table5 = report8Table5(dateWeek, aasetId);
			List listTempReport8Table5 = new ArrayList();// 用来放要删除的元素的因为type可能是null
			for (Map<String, Object> table5 : report8Table5) {

				try {
					table5.get("type").toString();

				} catch (Exception e) {
					listTempReport8Table5.add(table5);
				}

			}
			report8Table5.removeAll(listTempReport8Table5);// 清除元素
			map.put("report8Table5", report8Table5);

			map.put("endOfWeek", endOfWeek);
			map.put("startOfWeek", startOfWeek);
			int sumEventsReport8 = 0;// 这一个月事件的总条数
			// Map<String,Object> reportTable1 = report6Table1.get(0);
			for (Map<String, Object> reportTable1 : report8Table1) {
				sumEventsReport8 = sumEventsReport8
						+ Integer.parseInt(reportTable1.get("sum").toString());
			}
			map.put("sumEventsReport8", sumEventsReport8);
			StringBuffer report8Data1 = new StringBuffer();
			// 处理图表需要的数据
			for (Map<String, Object> mapCategories : report8Table1) {
				report8Data1.append("['" + mapCategories.get("priority") + "',"
						+ mapCategories.get("sum") + "],");
			}
			if (report8Data1.length() != 0) {// 如果没有数据,这里会数组越界,但是实际情况下会没有数据吗
				report8Data1.delete(report8Data1.length() - 1,
						report8Data1.length());
			}
			map.put("report8Data1", report8Data1);
			StringBuffer report8Data2 = new StringBuffer();
			// 处理图表需要的数据
			for (Map<String, Object> mapCategories : report8Table2) {
				report8Data2.append("['" + mapCategories.get("type") + "',"
						+ mapCategories.get("count") + "],");
			}
			if (report8Data2.length() != 0) {// 如果没有数据,这里会数组越界,但是实际情况下会没有数据吗
				report8Data2.delete(report8Data2.length() - 1,
						report8Data2.length());
			}
			map.put("report8Data2", report8Data2);
			break;
		case 9:
			report9Table1 = report9Table1(groupid);
			for (Map<String, Object> table1 : report9Table1) {
				table1.put("desc", table1.get("desc").equals("") ? "未添加描述"
						: table1.get("desc"));
			}
			report9Table2 = report9Table2(groupid);
			report9Table1Count = report9Table1Count(groupid);
			Map map2 = new HashMap();
			map2.put("aasetId", aasetId);
			report9Table2Count = report9Table2Count(map2);
			map.put("report9Table1Count", report9Table1Count);
			map.put("report9Table2Count", report9Table2Count);
			map.put("report9Table1", report9Table1);
			map.put("report9Table2", report9Table2);
			// 处理图表需要的数据
			StringBuffer report9Data = new StringBuffer();// 图表要用的数据
			if (report9Table2.size() != 0) {
				for (Map<String, Object> mapCategories : report9Table2) {
					if (Integer.parseInt(mapCategories.get("count").toString()) != 0) {
						report9Data.append("['" + mapCategories.get("name")
								+ "'," + mapCategories.get("count") + "],");
					}
				}
				if (report9Data.length() != 0) {
					report9Data.delete(report9Data.length() - 1,
							report9Data.length());
				}
			}

			map.put("report9Data", report9Data);
			break;
		case 10:
			map.put("endOfWeek", endOfWeek);
			map.put("startOfWeek", startOfWeek);
			report10Table1 = report10Table1(aasetId);
			map.put("report10Table1", report10Table1);
			// 处理图表需要的数据
			StringBuffer report10Data = new StringBuffer();// 图表要用的数据
			if (report10Table1.size() != 0) {
				for (Map<String, Object> mapCategories : report10Table1) {
					report10Data.append("['" + mapCategories.get("name") + "',"
							+ mapCategories.get("count") + "],");
				}
				report10Data.delete(report10Data.length() - 1,
						report10Data.length());
			}

			map.put("report10Data", report10Data);

			break;
		case 11:
			map.put("endOfWeek", endOfWeek);
			map.put("startOfWeek", startOfWeek);
			report11Table1 = report11Table1(aasetId);
			map.put("report11Table1", report11Table1);
			// 处理图表需要的数据
			StringBuffer report11Data = new StringBuffer();// 图表要用的数据
			if (report11Table1.size() != 0) {
				for (Map<String, Object> mapCategories : report11Table1) {
					report11Data.append("['" + mapCategories.get("name") + "',"
							+ mapCategories.get("count") + "],");
				}
				report11Data.delete(report11Data.length() - 1,
						report11Data.length());
			}
			Calendar c11 = Calendar.getInstance();
			c11.add(Calendar.MONTH, -1);
			time = new SimpleDateFormat("yyyy年M月").format(c11.getTime());
			map.put("time", time);
			map.put("report11Data", report11Data);
			break;
		case 12:
			map.put("endOfWeek", endOfWeek);
			map.put("startOfWeek", startOfWeek);
			report12Table1 = report12Table1(aasetId);
			List listTempReport12Table1 = new ArrayList();// 用来放要删除的元素的因为type可能是null
			for (Map<String, Object> table1 : report12Table1) {
				try {
					table1.get("type").toString();
				} catch (Exception e) {
					listTempReport12Table1.add(table1);
				}
			}
			// 转化type
			for (Map<String, Object> table1 : report12Table1) {
				try {
					table1.put("type", GlobalConfig.eventTypeTag.get(Long
							.parseLong(table1.get("type").toString())));
				} catch (Exception e) {
					table1.put("type", table1.get("type").toString());
				}

			}
			// 处理图表需要的数据
			StringBuffer report12Data1 = new StringBuffer();// 图表要用的数据
			if (report12Table1.size() != 0) {
				for (Map<String, Object> mapCategories : report12Table1) {
					report12Data1.append("['" + mapCategories.get("type")
							+ "'," + mapCategories.get("count") + "],");
				}
				report12Data1.delete(report12Data1.length() - 1,
						report12Data1.length());
			}
			report12Table1.removeAll(listTempReport12Table1);// 清除元素
			map.put("report12Table1", report12Table1);
			map.put("report12Data1", report12Data1);

			report12Table2 = report12Table2(aasetId);
			// 把用数字表示的级别变成汉子形式 这里也可以不转换,直接在ftl中if标签判断也行
			for (Map<String, Object> map1 : report12Table2) {
				if (map1.get("rank").toString().equals("1")) {
					map1.put("rank", "信息");
				} else if (map1.get("rank").toString().equals("2")) {
					map1.put("rank", "低级");
				} else if (map1.get("rank").toString().equals("3")) {
					map1.put("rank", "中级");
				} else if (map1.get("rank").toString().equals("4")) {
					map1.put("rank", "高级");
				} else {
					map1.put("rank", "严重");
				}
			}
			// 处理图表需要的数据
			StringBuffer report12Data2 = new StringBuffer();// 图表要用的数据
			if (report12Table2.size() != 0) {
				for (Map<String, Object> mapCategories : report12Table2) {
					report12Data2.append("['" + mapCategories.get("rank")
							+ "'," + mapCategories.get("count") + "],");
				}
				report12Data2.delete(report12Data2.length() - 1,
						report12Data2.length());
			}
			map.put("report12Table2", report12Table2);
			map.put("report12Data2", report12Data2);
			// table3 table4公用的是 report12Table3() table3需要传一个等级这个参数 alarmRank
			Map mapAlarmRank = new HashMap();
			mapAlarmRank.put("alarmRank", 5);
			mapAlarmRank.put("alertAssetId", aasetId);
			report12Table3 = report12Table3(mapAlarmRank);
			// 处理图表需要的数据
			StringBuffer report12Data3 = new StringBuffer();// 图表要用的数据
			if (report12Table3.size() != 0) {
				for (Map<String, Object> mapCategories : report12Table3) {
					report12Data3.append("['" + mapCategories.get("name")
							+ "'," + mapCategories.get("count") + "],");
				}
				report12Data3.delete(report12Data3.length() - 1,
						report12Data3.length());
			}
			map.put("report12Table3", report12Table3);
			map.put("report12Data3", report12Data3);

			mapAlarmRank.remove("alarmRank");
			report12Table4 = report12Table3(mapAlarmRank);
			// 处理图表需要的数据
			StringBuffer report12Data4 = new StringBuffer();// 图表要用的数据
			if (report12Table4.size() != 0) {
				for (Map<String, Object> mapCategories : report12Table4) {
					report12Data4.append("['" + mapCategories.get("name")
							+ "'," + mapCategories.get("count") + "],");
				}
				report12Data4.delete(report12Data4.length() - 1,
						report12Data4.length());
			}
			map.put("report12Table4", report12Table4);
			map.put("report12Data4", report12Data4);

			break;
		case 13: // 陕西台日报
			this.report13Table1 = report13Table1(aasetId);// 获取事件告警
			// 把里面的lang类型的time变成 date 显示方便
			time = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
			// 把long类型的ip转换成常规形式
			DateUtil dateUtil = new DateUtil();// 项目中的时间工具类
			for (Map<String, Object> report13Table1Map : report13Table1) {
				report13Table1Map.put("createdate", dateUtil.formatDate(
						new Date(Long.parseLong(report13Table1Map.get(
								"createdate").toString())), "yyyy年MM月dd日"));
			}

			// (new Date(Long.parseLong((String) )), )
			map.put("time", time);
			map.put("report13Table1", this.report13Table1);

			this.report13Table2 = report13Table2(aasetId);// 获取阀值
			// 把里面的lang类型的time变成 date 显示方便
			time = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
			// 把long类型的ip转换成常规形式
			// DateUtil dateUtil = new DateUtil();//项目中的时间工具类
			// for (Map<String, Object> report13Table1Map : report13Table1) {
			// report13Table1Map.put("createdate",dateUtil.formatDate(new
			// Date(Long.parseLong(report13Table1Map.get("createdate").toString())
			// ), "yyyy年MM月dd日"));
			// }
			map.put("report13Table2", this.report13Table2);
			// (new Date(Long.parseLong((String) )), )
			map.put("time", time);
			map.put("report13Table1", this.report13Table1);

			break;
		case 14: // 陕西台周报
			this.report14Table1 = report14Table1(aasetId);// 获取事件告警
			report14Table1.addAll(report14Table1Relance(aasetId));
			for (Map<String, Object> table3 : report14Table1) {
				String str = null;
				// try {
				// String s = table2.get("type").toString();
				str = GlobalConfig.eventCategoryTag.get(table3.get("category"));
				if (table3.get("seriouseventslastweek") == null) { // 如果null 变成0
					table3.put("seriouseventslastweek", new BigDecimal(0));
				}
				if (table3.get("seriouseventsthisweek") == null) {
					table3.put("seriouseventsthisweek", new BigDecimal(0));
				}
				if (table3.get("intermediateeventslastweek") == null) {
					table3.put("intermediateeventslastweek", new BigDecimal(0));
				}
				if (table3.get("intermediateeventsthisweek") == null) {
					table3.put("intermediateeventsthisweek", new BigDecimal(0));
				}
				// 算环比增长
				// double QoQChangeSerious ;
				// double QoQChangeIntermediate;
				BigDecimal seriouseventslastweek = (BigDecimal) table3
						.get("seriouseventslastweek"); // 上周严重
				BigDecimal seriouseventsthisweek = (BigDecimal) table3
						.get("seriouseventsthisweek");// 本周严重
				BigDecimal intermediateeventslastweek = (BigDecimal) table3
						.get("intermediateeventslastweek");// 上周中级
				BigDecimal intermediateeventsthisweek = (BigDecimal) table3
						.get("intermediateeventsthisweek");// 本周中级
				if (seriouseventslastweek.intValue() != 0) {// 判断除数是0的情况
					double QoQChangeSerious = seriouseventsthisweek
							.subtract(seriouseventslastweek)
							.divide(seriouseventslastweek, 2,
									BigDecimal.ROUND_HALF_UP).doubleValue();
					;
					table3.put("QoQChangeSerious", QoQChangeSerious * 100 + "%");
				} else {
					table3.put("QoQChangeSerious", "——");
				}
				if (intermediateeventslastweek.intValue() != 0) {
					double QoQChangeIntermediate = intermediateeventsthisweek
							.subtract(intermediateeventslastweek)
							.divide(intermediateeventslastweek, 2,
									BigDecimal.ROUND_HALF_UP).doubleValue();
					;
					table3.put("QoQChangeIntermediate", QoQChangeIntermediate
							* 100 + "%");
				} else {
					table3.put("QoQChangeIntermediate", "——");
				}
				try {
					table3.put("category",
							str != null ? str : table3.get("category")
									.toString());
				} catch (Exception e) {
					table3.put("category", table3.get("category"));
				}

			}
			// 把里面的lang类型的time变成 date 显示方便
			time = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
			map.put("time", time);
			map.put("report14Table1", this.report14Table1);

			report14Table2 = this.report14Table2("10", aasetId);// 病毒木马
			report14Table3 = this.report14Table2("11", aasetId);// 未知类型
			report14Table4 = this.report14Table2("12", aasetId);// 系统状态
			report14Table5 = this.report14Table2("13", aasetId);// 扫描探测
			report14Table6 = this.report14Table2("14", aasetId);// 拒绝服务
			report14Table7 = this.report14Table2("15", aasetId);// 规避
			report14Table8 = this.report14Table2("16", aasetId);// 认证授权
			report14Table9 = this.report14Table2("17", aasetId);// 应用漏洞
			report14Table10 = this.report14Table2("18", aasetId);// 非授权访问
			// 判断是不是list 如果是空的话就不在map里面放了
			if (!report14Table2.isEmpty()) {
				map.put("category10", report14Table2);
				map.put("category10Size", report14Table2.size() + 1);
			}
			if (!report14Table3.isEmpty()) {
				map.put("category11", report14Table3);
				map.put("category11Size", report14Table3.size() + 1);
			}
			if (!report14Table4.isEmpty()) {
				map.put("category12", report14Table4);
				map.put("category12Size", report14Table4.size() + 1);
			}
			if (!report14Table5.isEmpty()) {
				map.put("category13", report14Table5);
				map.put("category13Size", report14Table5.size() + 1);
			}
			if (!report14Table6.isEmpty()) {
				map.put("category14", report14Table6);
				map.put("category14Size", report14Table6.size() + 1);
			}
			if (!report14Table7.isEmpty()) {
				map.put("category15", report14Table7);
				map.put("category15Size", report14Table7.size() + 1);
			}
			if (!report14Table8.isEmpty()) {
				map.put("category16", report14Table8);
				map.put("category16Size", report14Table8.size() + 1);
			}
			if (!report14Table9.isEmpty()) {
				map.put("category17", report14Table9);
				map.put("category17Size", report14Table9.size() + 1);
			}
			if (!report14Table10.isEmpty()) {
				map.put("category18", report14Table10);
				map.put("category18Size", report14Table10.size() + 1);
			}
			break;

		}
		timeDetail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		map.put("timeDetail", timeDetail);
		// map.put("time", time);
		map.put("auditReportId", auditReportId);
		return map;

	}

	private List<String> getReportTableNameCostoms(String beginTime,
			String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(beginTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * //设置日历类， String[] beginArr = beginTime.split("-"); String[] endArr =
		 * endTime.split("-");
		 * 
		 * Calendar start = Calendar.getInstance();// 开始时间
		 * start.set(Integer.parseInt(beginArr[0]),
		 * Integer.parseInt(beginArr[0])-1, Integer.parseInt(beginArr[0]));
		 * Calendar end = Calendar.getInstance();// 结束时间
		 * end.set(Integer.parseInt(endArr[0]), Integer.parseInt(endArr[0])-1,
		 * Integer.parseInt(endArr[0]));
		 */
		// 就算两个时间相隔多少天
		Calendar startCalendar = Calendar.getInstance();// 开始时间
		startCalendar.setTimeInMillis(start.getTime());
		// startCalendar.set(start.getYear(), start.getMonth()-1,
		// start.getDay());
		int day = (int) ((end.getTime() - start.getTime()) / (1000 * 3600 * 24));
		List<String> date = new ArrayList<String>();
		for (int i = 0; i <= day; i++) {
			date.add("tbl_"
					+ new SimpleDateFormat("yyyyMMdd").format(startCalendar
							.getTimeInMillis()));
			startCalendar.add(Calendar.DAY_OF_MONTH, 1);

		}
		// 去除没有的表
		List<String> dateTemp = new ArrayList<String>();
		for (String string : date) {
			if (existsTable(string) == 0) {
				dateTemp.add(string);
			}
		}
		date.removeAll(dateTemp);
		return date;
	}

	@Override
	public List<Map<String, Object>> report14Table1(String assetId) {
		List<String> tablesNameLastWeek = this.getWeekReportTableName(-1);// 上周的表名字
																			// 14天之前
		List<String> tablesNameThisWeek = this.getWeekReportTableName(0);// 本周的表名字
																			// 7天之前
		StringBuffer seriousEventsLastWeek = new StringBuffer();// 上周严重事件
		if (!tablesNameLastWeek.isEmpty()) { // 拼接上周高级事件
			for (int i = 0; i < tablesNameLastWeek.size() - 1; i++) {
				seriousEventsLastWeek
						.append("select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
								+ tablesNameLastWeek.get(i)
								+ "   where 	\"LOG_PRIORITY\" = 5");
				if (StringUtil.isNotEmpty(assetId)) {
					seriousEventsLastWeek.append(" and \"LOG_ASSET_ID\" in ")
							.append("(" + assetId + ")");
				}
				seriousEventsLastWeek
						.append(" group by \"LOG_CATEGORY\" union ");

			}
			seriousEventsLastWeek
					.append(" select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
							+ tablesNameLastWeek.get(tablesNameLastWeek.size() - 1)
							+ " where 	\"LOG_PRIORITY\" = 5 ");
			if (StringUtil.isNotEmpty(assetId)) {
				seriousEventsLastWeek.append(" and \"LOG_ASSET_ID\" in ")
						.append("(" + assetId + ")");
			}
			seriousEventsLastWeek.append(" group by \"LOG_CATEGORY\" ");
		}

		StringBuffer seriousEventsThisWeek = new StringBuffer();// 本周严重事件
		if (!tablesNameThisWeek.isEmpty()) { // 拼接上周高级事件
			for (int i = 0; i < tablesNameThisWeek.size() - 1; i++) {
				seriousEventsThisWeek
						.append("select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
								+ tablesNameThisWeek.get(i)
								+ "  where 	\"LOG_PRIORITY\" = 5 ");
				if (StringUtil.isNotEmpty(assetId)) {
					seriousEventsThisWeek.append(" and \"LOG_ASSET_ID\" in ")
							.append("(" + assetId + ")");
				}
				seriousEventsThisWeek
						.append(" group by \"LOG_CATEGORY\" union ");

			}
			seriousEventsThisWeek
					.append(" select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
							+ tablesNameThisWeek.get(tablesNameThisWeek.size() - 1)
							+ " where 	\"LOG_PRIORITY\" = 5  ");
			if (StringUtil.isNotEmpty(assetId)) {
				seriousEventsThisWeek.append(" and \"LOG_ASSET_ID\" in ")
						.append("(" + assetId + ")");
			}
			seriousEventsThisWeek.append(" group by \"LOG_CATEGORY\" ");
		}

		StringBuffer IntermediateEventsLastWeek = new StringBuffer();// 上周中级事件
		if (!tablesNameLastWeek.isEmpty()) { // 拼接上周高级事件
			for (int i = 0; i < tablesNameLastWeek.size() - 1; i++) {
				IntermediateEventsLastWeek
						.append("select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
								+ tablesNameLastWeek.get(i)
								+ "  where 	\"LOG_PRIORITY\"  in (3,4) ");
				if (StringUtil.isNotEmpty(assetId)) {
					IntermediateEventsLastWeek.append(
							" and \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				IntermediateEventsLastWeek
						.append(" group by \"LOG_CATEGORY\" union ");

			}
			IntermediateEventsLastWeek
					.append(" select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
							+ tablesNameLastWeek.get(tablesNameLastWeek.size() - 1)
							+ " where 	\"LOG_PRIORITY\"  in (3,4) ");
			if (StringUtil.isNotEmpty(assetId)) {
				IntermediateEventsLastWeek.append(" and \"LOG_ASSET_ID\" in ")
						.append("(" + assetId + ")");
			}
			IntermediateEventsLastWeek.append(" group by \"LOG_CATEGORY\" ");
		}

		StringBuffer IntermediateEventsThisWeek = new StringBuffer();// 本周中级事件
		if (!tablesNameThisWeek.isEmpty()) { // 拼接上周高级事件
			for (int i = 0; i < tablesNameThisWeek.size() - 1; i++) {
				IntermediateEventsThisWeek
						.append("select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
								+ tablesNameThisWeek.get(i)
								+ "  where 	\"LOG_PRIORITY\" in (3,4) ");
				if (StringUtil.isNotEmpty(assetId)) {
					IntermediateEventsThisWeek.append(
							" and \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				IntermediateEventsThisWeek
						.append(" group by \"LOG_CATEGORY\" union ");

			}
			IntermediateEventsThisWeek
					.append(" select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
							+ tablesNameThisWeek.get(tablesNameThisWeek.size() - 1)
							+ " where 	\"LOG_PRIORITY\" in (3,4)  ");
			if (StringUtil.isNotEmpty(assetId)) {
				IntermediateEventsThisWeek.append(" and \"LOG_ASSET_ID\" in ")
						.append("(" + assetId + ")");
			}
			IntermediateEventsThisWeek.append(" group by \"LOG_CATEGORY\" ");
		}
		Map map = new HashMap();// 放条件的map
		map.put("seriousEventsLastWeek", seriousEventsLastWeek);
		map.put("IntermediateEventsLastWeek", IntermediateEventsLastWeek);
		map.put("seriousEventsThisWeek", seriousEventsThisWeek);
		map.put("IntermediateEventsThisWeek", IntermediateEventsThisWeek);
		return this.auditReportDao.report14Table1(map);
	}

	@Override
	public List<Map<String, Object>> report13Table1(String assetId) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Map map = new HashMap();// 放查询条件的map
		map.put("start", cal.getTimeInMillis());// 获取当天0点的时间戳
		map.put("end", new Date().getTime());// 获取查询的时间戳
		map.put("alertAssetId", assetId);
		return this.auditReportDao.report13Table1(map);
	}

	@Override
	public List<Map<String, Object>> report13Table2(String assetId) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Map map = new HashMap();// 放查询条件的map
		map.put("start", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal
				.getTimeInMillis()));// 获取当天0点的时间戳
		map.put("end",
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));// 获取查询的时间戳
		map.put("alertAssetId", assetId);
		return this.auditReportDao.report13Table2(map);
	}

	@Override
	public List<Map<String, Object>> report14Table1Relance(String assetId) {
		List<Map<String, Object>> report14Table2 = new ArrayList<Map<String, Object>>();
		// 放关联后事件的时间条件
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);// 7天前的时间
		map.put("thisWeekStart", cal.getTimeInMillis());
		map.put("thisWeekend", System.currentTimeMillis());
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DAY_OF_YEAR, -14);// 14天前的时间
		map.put("lastWeekstart", cal1.getTimeInMillis());
		map.put("lastWeekend", cal.getTimeInMillis());
		map.put("assetId", assetId);
		Map mapCagegory = new HashMap();
		mapCagegory.put("category", "关联分析后的事件");
		mapCagegory.putAll(auditReportDao.report14Table2Col1(map));// 上周高级
		mapCagegory.putAll(auditReportDao.report14Table2Col2(map));// 本周高级
		mapCagegory.putAll(auditReportDao.report14Table2Col3(map));// 上周中高
		mapCagegory.putAll(auditReportDao.report14Table2Col4(map));// 本周中高
		report14Table2.add(mapCagegory);
		/*
		 * report14Table2.add(this.);
		 * report14Table2.add(this.auditReportDao.report14Table2Col3(map));//本周中
		 * 中高级
		 * report14Table2.add(this.auditReportDao.report14Table2Col2(map));//
		 * 本周高级
		 * report14Table2.add(this.auditReportDao.report14Table2Col4(map));/
		 * /本周高级
		 */return report14Table2;
	}

	@Override
	public Map<String, Object> getFreemakerMapExport(long auditReportId,
			String path, String reprotType) {
		// 如果是pdf,图片需要相对路径,如果不是需要base64类型的图片 在这里要判断
		Map<String, Object> map = new HashMap<String, Object>();
		switch ((int) auditReportId) {// 根据报表id把相应的图放在word中
		case 6:
			String s;
			if (reprotType.equals("pdf")) {// pdf格式需要相对路径的图片 doc需要base64类型的图片
											// html
											// 需要"data:image/jpeg;base64,"+base64类型的图片
											// 所以这里分开写
				s = "diag" + auditReportId + "1.jpg";
			} else if (reprotType.equals("doc")) {
				s = ImageToBase64.imgToBase64(path + "/diag" + auditReportId
						+ "1.jpg");
			} else {
				s = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
			}
			map.put("image", s);// 把base64图片加进去;
			break;
		case 7:
			String base64Report7;
			if (reprotType.equals("pdf")) {
				base64Report7 = "diag" + auditReportId + "1.jpg";
			} else if (reprotType.equals("doc")) {
				base64Report7 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "1.jpg");
			} else {
				base64Report7 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
			}
			map.put("image", base64Report7);// 把图片加进去;
			break;
		case 8:
			String base64Report81;
			String base64Report82;
			if (reprotType.equals("pdf")) {// pdf
				base64Report81 = "diag" + auditReportId + "1.jpg";
				base64Report82 = "diag" + auditReportId + "2.jpg";
			} else if (reprotType.equals("html")) {
				base64Report81 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
				base64Report82 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "2.jpg");
			} else {
				base64Report81 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "1.jpg");
				base64Report82 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "2.jpg");
			}
			map.put("image1", base64Report81);// 把图片加进去;
			map.put("image2", base64Report82);// 把图片加进去;
			break;
		case 9:
			String base64Report9;
			if (reprotType.equals("pdf")) {
				base64Report9 = "diag" + auditReportId + "1.jpg";
			} else if (reprotType.equals("doc")) {
				base64Report9 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "1.jpg");
			} else {
				base64Report9 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
			}

			map.put("image", base64Report9);// 把图片加进去;
			break;
		case 10:
			String base64Report10;
			if (reprotType.equals("pdf")) {
				base64Report10 = "diag" + auditReportId + "1.jpg";
			} else if (reprotType.equals("doc")) {
				base64Report10 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "1.jpg");
			} else {
				base64Report10 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
			}

			map.put("image", base64Report10);// 把图片加进去;
			break;
		case 11:
			String base64Report11;
			if (reprotType.equals("pdf")) {
				base64Report11 = "diag" + auditReportId + "1.jpg";
			} else if (reprotType.equals("doc")) {
				base64Report11 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "1.jpg");
			} else {
				base64Report11 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
			}

			map.put("image", base64Report11);// 把图片加进去;
			break;
		case 12:
			String base64Report121;
			String base64Report122;
			String base64Report123;
			String base64Report124;
			if (reprotType.equals("pdf")) {// pdf
				base64Report121 = "diag" + auditReportId + "1.jpg";
				base64Report122 = "diag" + auditReportId + "2.jpg";
				base64Report123 = "diag" + auditReportId + "3.jpg";
				base64Report124 = "diag" + auditReportId + "4.jpg";
			} else if (reprotType.equals("html")) {
				base64Report121 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
				base64Report122 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "2.jpg");
				base64Report123 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "3.jpg");
				base64Report124 = "data:image/jpeg;base64,"
						+ ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "4.jpg");
			} else {
				base64Report121 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "1.jpg");
				base64Report122 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "2.jpg");
				base64Report123 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "3.jpg");
				base64Report124 = ImageToBase64.imgToBase64(path + "/diag"
						+ auditReportId + "4.jpg");
			}
			map.put("image1", base64Report121);// 把图片加进去;
			map.put("image2", base64Report122);// 把图片加进去;
			map.put("image3", base64Report123);// 把图片加进去;
			map.put("image4", base64Report124);// 把图片加进去;
			break;
		}

		return map;
	}

	@Override
	public String getTreeStyle(String path) {

		treeBuff = new StringBuffer();
		Map<String, Long> map = new HashMap<String, Long>();

		map.put("auditReportParentId", Long.valueOf(0));

		List<AuditReport> auditReportList = auditReportDao.queryByParentId(map);

		for (AuditReport auditReport : auditReportList) {
			Map<String, Long> map1 = new HashMap<String, Long>();

			map1.put("auditReportParentId", auditReport.getAuditReportId());

			List<AuditReport> ReportList1 = auditReportDao
					.queryByParentId(map1);
			if (ReportList1.size() <= 0) {
				treeBuff.append("<ul>");
				treeBuff.append("<li><a href=\"javascript:showReport('"
						+ auditReport.getAuditReportId() + "');\"><img src=\""
						+ path + "/images/" + PICTURE_NAME_SEED
						+ "\"></a>&nbsp;<a href=\"javascript:showReport('"
						+ auditReport.getAuditReportId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ auditReport.getAuditReportName() + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul>");
				treeBuff.append("<li id=\"query_"
						+ auditReport.getAuditReportId()
						+ "\"><a href=\"javascript:action('"
						+ auditReport.getAuditReportId()
						+ "','img');\"><img src=\"" + path + "/images/"
						+ PICTURE_NAME_CLOSE + "\" id=\"img_query_"
						+ auditReport.getAuditReportId()
						+ "\"></a>&nbsp;<a href=\"javascript:showReport('"
						+ auditReport.getAuditReportId()
						+ "');\" ondblclick=\"action('"
						+ auditReport.getAuditReportId()
						+ "','img')\"><span class=\"eventext\">&nbsp;"
						+ auditReport.getAuditReportName() + "</span></a>");
				drawSon(ReportList1, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			}

		}

		return treeBuff.toString();
	}

	public void drawSon(List<AuditReport> auditReportList, String path) {
		for (AuditReport auditReport : auditReportList) {
			Map<String, Long> map = new HashMap<String, Long>();

			map.put("auditReportParentId", auditReport.getAuditReportId());

			List<AuditReport> groupList = auditReportDao.queryByParentId(map);
			if (groupList.size() <= 0) {
				// 写出页子节点
				treeBuff.append("<ul  class=\"disply\">");
				treeBuff.append("<li class=\"eventleft\"><a href=\"javascript:showReport('"
						+ auditReport.getAuditReportId()
						+ "');\"><img src=\""
						+ path
						+ "/images/"
						+ PICTURE_NAME_SEED
						+ "\"></a>&nbsp;<a href=\"javascript:showReport('"
						+ auditReport.getAuditReportId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ auditReport.getAuditReportName() + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul  class=\"disply\">");
				treeBuff.append("<li id=\"query_"
						+ auditReport.getAuditReportId()
						+ "\" class=\"eventleft\"><a href=\"javascript:action('"
						+ auditReport.getAuditReportId()
						+ "','img');\"><img src=\"" + path + "/images/"
						+ PICTURE_NAME_CLOSE + "\" id=\"img_query_"
						+ auditReport.getAuditReportId()
						+ "\"></a>&nbsp;<a href=\"javascript:showReport('"
						+ auditReport.getAuditReportId()
						+ "')\" ondblclick=\"action('"
						+ auditReport.getAuditReportId() + "','"
						+ auditReport.getAuditReportId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ auditReport.getAuditReportName() + "</span></a>");
				drawSon(groupList, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");

			}
		}
	}

	public StringBuffer getTreeBuff() {
		return treeBuff;
	}

	public void setTreeBuff(StringBuffer treeBuff) {
		this.treeBuff = treeBuff;
	}

	public AuditReportDao getAuditReportDao() {
		return auditReportDao;
	}

	public void setAuditReportDao(AuditReportDao auditReportDao) {
		this.auditReportDao = auditReportDao;
	}

	public AuditReport getAuditReport() {
		return auditReport;
	}

	public void setAuditReport(AuditReport auditReport) {
		this.auditReport = auditReport;
	}

	@Override
	public List<Map<String, Object>> queryEquipmentDetailList(Map map) {
		String table = "tbl_"
				+ new SimpleDateFormat("yyyyMMdd").format(new Date())
						.toString();
		if (existsTable(table) != 0) {
			map.put("table", table);
			return auditReportDao.queryEquipmentDetailList(map);
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report6Table1(List<String> date,
			String assertIds, String categoryIds) {
		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append(
						"select \"LOG_PRIORITY\" AS priority  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
						.append(date.get(i));
				if (!StringUtil.isEmpty(assertIds)) {
					sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
							.append(assertIds).append(")");
				}
				if (!"'0'".equals(categoryIds)) {
					sbf.append(" and \"LOG_CATEGORY\" in ").append(
							"(" + categoryIds + ")");
				}
				sbf.append(" group by \"LOG_PRIORITY\" union ");

			}
			sbf.append(
					"select \"LOG_PRIORITY\" AS priority  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
					.append(date.get(date.size() - 1));
			if (!StringUtil.isEmpty(assertIds)) {
				sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
						.append(assertIds).append(")");
			}
			if (!"'0'".equals(categoryIds)) {
				sbf.append(" and \"LOG_CATEGORY\" in ").append(
						"(" + categoryIds + ")");
			}
			sbf.append(" group by \"LOG_PRIORITY\"  ");

			return auditReportDao.report6Table1(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report6Table2(List<String> date,
			String assertIds, String categoryIds) {
		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append(
						"select \"LOG_NAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
						.append(date.get(i));
				if (!StringUtil.isEmpty(assertIds)) {
					sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
							.append(assertIds).append(")");
				}
				if (!"'0'".equals(categoryIds)) {
					sbf.append(" and \"LOG_CATEGORY\" in ").append(
							"(" + categoryIds + ")");
				}
				sbf.append(" group by \"LOG_NAME\" union ");

			}
			sbf.append(
					"select \"LOG_NAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
					.append(date.get(date.size() - 1));

			if (!StringUtil.isEmpty(assertIds)) {
				sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
						.append(assertIds).append(")");
			}
			if (!"'0'".equals(categoryIds)) {
				sbf.append(" and \"LOG_CATEGORY\" in ").append(
						"(" + categoryIds + ")");
			}
			sbf.append(" group by \"LOG_NAME\"  ");

			return auditReportDao.report6Table2(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report6Table3(List<String> date,
			String assertIds, String categoryIds) {
		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append(
						"select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
						.append(date.get(i));
				if (!StringUtil.isEmpty(assertIds)) {
					sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
							.append(assertIds).append(")");
				}
				if (!"'0'".equals(categoryIds)) {
					sbf.append(" and \"LOG_CATEGORY\" in ").append(
							"(" + categoryIds + ")");
				}
				sbf.append(" group by \"LOG_CUSTOMS5\" union ");

			}
			sbf.append(
					"select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
					.append(date.get(date.size() - 1));

			if (!StringUtil.isEmpty(assertIds)) {
				sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
						.append(assertIds).append(")");
			}
			if (!"'0'".equals(categoryIds)) {
				sbf.append(" and \"LOG_CATEGORY\" in ").append(
						"(" + categoryIds + ")");
			}
			sbf.append(" group by \"LOG_CUSTOMS5\"  ");

			return auditReportDao.report6Table3(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report6Table4(List<String> date,
			String assertIds, String categoryIds) {

		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append(
						"select \"LOG_NAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
						.append(date.get(i));
				if (!StringUtil.isEmpty(assertIds)) {
					sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
							.append(assertIds).append(")");
				}
				if (!"'0'".equals(categoryIds)) {
					sbf.append(" and \"LOG_CATEGORY\" in ").append(
							"(" + categoryIds + ")");
				}
				sbf.append(" group by \"LOG_NAME\" union ");

			}
			sbf.append(
					"select \"LOG_NAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
					.append(date.get(date.size() - 1));

			if (!StringUtil.isEmpty(assertIds)) {
				sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
						.append(assertIds).append(")");
			}
			if (!"'0'".equals(categoryIds)) {
				sbf.append(" and \"LOG_CATEGORY\" in ").append(
						"(" + categoryIds + ")");
			}
			sbf.append(" group by \"LOG_NAME\"  ");
			return auditReportDao.report6Table4(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report6Table5(List<String> date,
			String assertIds, String categoryIds) {
		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append(
						"select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
						.append(date.get(i));
				if (!StringUtil.isEmpty(assertIds)) {
					sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
							.append(assertIds).append(")");
				}
				if (!"'0'".equals(categoryIds)) {
					sbf.append(" and \"LOG_CATEGORY\" in ").append(
							"(" + categoryIds + ")");
				}
				sbf.append(" group by \"LOG_CUSTOMS5\" union ");

			}
			sbf.append(
					"select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from ")
					.append(date.get(date.size() - 1));

			if (!StringUtil.isEmpty(assertIds)) {
				sbf.append(" where \"LOG_ASSET_ID\" in").append("(")
						.append(assertIds).append(")");
			}
			if (!"'0'".equals(categoryIds)) {
				sbf.append(" and \"LOG_CATEGORY\" in ").append(
						"(" + categoryIds + ")");
			}
			sbf.append(" group by \"LOG_CUSTOMS5\"  ");

			return auditReportDao.report6Table5(sbf.toString());

		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	public List<String> getMonthReportTableName(int month) {
		Calendar start = Calendar.getInstance();// 上个月的第一天
		start.add(Calendar.MARCH, month);// 月减去1
		start.set(Calendar.DAY_OF_MONTH, 1);// 日设设置为1
		int dayOfThisMonth = start.getActualMaximum(Calendar.DAY_OF_MONTH);// 这个月共有多少天
		List<String> date = new ArrayList<String>();
		for (int i = 0; i < dayOfThisMonth; i++) {
			date.add("tbl_"
					+ new SimpleDateFormat("yyyyMMdd").format(start.getTime()));
			start.add(Calendar.DAY_OF_MONTH, 1);

		}
		// 去除没有的表
		List<String> dateTemp = new ArrayList<String>();
		for (String string : date) {
			if (existsTable(string) == 0) {
				dateTemp.add(string);
			}
		}
		date.removeAll(dateTemp);
		return date;
	}

	@Override
	public int existsTable(String tableName) {
		return auditReportDao.existsTable(tableName);
	}

	@Override
	public List<Map<String, Object>> report2Table1(List<String> date) {
		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (date.size() != 0) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ date.get(i)
						+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\"   union ");
			}
			sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ date.get(date.size() - 1)
					+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\"   ");
			// sbf.append(" select \"LOG_DEVNAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
			// + date.get(date.size() - 1) + " group by \"LOG_DEVNAME\" ");

			return auditReportDao.report2Table1(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report2Table12(List<String> date) {
		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ date.get(i)
						+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\"   union ");
			}
			sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ date.get(date.size() - 1)
					+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\"   ");

			return auditReportDao.report2Table12(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report2Table2(List<String> date) {

		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ date.get(i)
						+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\"   union ");
			}
			sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ date.get(date.size() - 1)
					+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\"   ");
			return auditReportDao.report2Table2(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report2Table3(List<String> date) {

		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ date.get(i)
						+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\" union  ");
			}
			sbf.append("select \"LOG_CUSTOMS5\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ date.get(date.size() - 1)
					+ " group by \"LOG_PRIORITY\" ,\"LOG_CUSTOMS5\"   ");
			// sbf.append(" select \"LOG_DEVNAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
			// + date.get(date.size() - 1) + " group by \"LOG_DEVNAME\" ");

			return auditReportDao.report2Table3(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report2Table4(List<String> date) {
		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {

			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_DEVNAME\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ date.get(i)
						+ " group by \"LOG_PRIORITY\" ,\"LOG_DEVNAME\" union  ");
			}
			sbf.append("select \"LOG_DEVNAME\" as asset ,\"LOG_PRIORITY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ date.get(date.size() - 1)
					+ " group by \"LOG_PRIORITY\" ,\"LOG_DEVNAME\"   ");
			// sbf.append(" select \"LOG_DEVNAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
			// + date.get(date.size() - 1) + " group by \"LOG_DEVNAME\" ");

			return auditReportDao.report2Table4(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report2Table5(List<String> date) {

		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {

			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_DEVADDR\" as devaddr,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from "
						+ date.get(i)
						+ "  group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\"  union ");
			}
			sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_DEVADDR\" as devaddr,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from "
					+ date.get(date.size() - 1)
					+ "  group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\" ,\"LOG_DEVNAME\"  ");
			// sbf.append(" select \"LOG_DEVNAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
			// + date.get(date.size() - 1) + " group by \"LOG_DEVNAME\" ");

			return auditReportDao.report2Table5(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report2Table6(List<String> date) {// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {

			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
						+ date.get(i)
						+ " group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\" union  ");
			}
			sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
					+ date.get(date.size() - 1)
					+ "  group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\"  ");
			// sbf.append(" select \"LOG_DEVNAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
			// + date.get(date.size() - 1) + " group by \"LOG_DEVNAME\" ");

			return auditReportDao.report2Table6(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report2Table7(List<String> date) {// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
						+ date.get(i)
						+ " group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\" union  ");
			}
			sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
					+ date.get(date.size() - 1)
					+ "  group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\"  ");
			// sbf.append(" select \"LOG_DEVNAME\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
			// + date.get(date.size() - 1) + " group by \"LOG_DEVNAME\" ");

			return auditReportDao.report2Table7(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report2Table8(List<String> date) {// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
						+ date.get(i)
						+ " group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\" union  ");
			}
			sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
					+ date.get(date.size() - 1)
					+ "  group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\"  ");

			return auditReportDao.report2Table8(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report2Table9(List<String> date) {// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
						+ date.get(i)
						+ " group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\" union  ");
			}
			sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
					+ date.get(date.size() - 1)
					+ "  group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\"  ");

			return auditReportDao.report2Table9(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report2Table10(List<String> date) {// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {

			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
						+ date.get(i)
						+ " group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\" union  ");
			}
			sbf.append("select \"LOG_TYPE\" as type,\"LOG_PRIORITY\" as priority,\"LOG_CUSTOMS4\" as c ,\"LOG_DEVNAME\" as name,  sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
					+ date.get(date.size() - 1)
					+ "  group by \"LOG_TYPE\" ,\"LOG_PRIORITY\",\"LOG_DEVADDR\" ,\"LOG_CUSTOMS4\", \"LOG_DEVNAME\"  ");
			return auditReportDao.report2Table10(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<Map<String, Object>> report8Table1(List<String> dateWeek,
			String assetId) {
		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!dateWeek.isEmpty()) {
			for (int i = 0; i < dateWeek.size() - 1; i++) {
				sbf.append("select \"LOG_PRIORITY\" as priority, sum(\"LOG_AGGREGATED_COUNT\") as sum from "
						+ dateWeek.get(i));
				if (StringUtil.isNotEmpty(assetId)) {
					sbf.append(" where \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				sbf.append("  group by \"LOG_PRIORITY\"  union ");
			}
			sbf.append("select \"LOG_PRIORITY\" as priority, sum(\"LOG_AGGREGATED_COUNT\") as sum from "
					+ dateWeek.get(dateWeek.size() - 1));
			if (StringUtil.isNotEmpty(assetId)) {
				sbf.append(" where \"LOG_ASSET_ID\" in ").append(
						"(" + assetId + ")");
			}
			sbf.append("  group by \"LOG_PRIORITY\"");

			return auditReportDao.report8Table1(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<String> getWeekReportTableName(int week) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 7 * (week - 1));
		c.add(Calendar.DAY_OF_YEAR, 1);
		List<String> dateWeek = new ArrayList<String>();
		for (int i = 7 * (-week); i < 7 * (-week + 1); i++) {
			dateWeek.add("tbl_"
					+ new SimpleDateFormat("yyyyMMdd").format(c.getTime()));
			c.add(Calendar.DAY_OF_YEAR, 1);
		}
		// 去除没有的表
		List<String> dateTemp = new ArrayList<String>();
		for (String string : dateWeek) {
			if (existsTable(string) == 0l) {
				dateTemp.add(string);
			}
		}
		dateWeek.removeAll(dateTemp);
		return dateWeek;
	}

	@Override
	public List<Map<String, Object>> report8Table2(List<String> dateWeek,
			String assetId) {
		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!dateWeek.isEmpty()) {
			for (int i = 0; i < dateWeek.size() - 1; i++) {
				sbf.append("select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ dateWeek.get(i));
				if (StringUtil.isNotEmpty(assetId)) {
					sbf.append(" where \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				sbf.append(" group by \"LOG_CATEGORY\" union ");

			}
			/*if (StringUtil.isNotEmpty(assetId)) {
				sbf.append(" where \"LOG_ASSET_ID\" in ").append(
						"(" + assetId + ")");
			}*/
			sbf.append(" select \"LOG_CATEGORY\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ dateWeek.get(dateWeek.size() - 1));
			if (StringUtil.isNotEmpty(assetId)) {
				sbf.append(" where \"LOG_ASSET_ID\" in ").append(
						"(" + assetId + ")");
			}
			sbf.append(" group by \"LOG_CATEGORY\" ");

			return auditReportDao.report6Table2(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report8Table3(List<String> dateWeek,
			String assetId) {
		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!dateWeek.isEmpty()) {
			for (int i = 0; i < dateWeek.size() - 1; i++) {
				sbf.append("select \"LOG_CATEGORY\" as asset,\"LOG_PRIORITY\" as type, sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
						+ dateWeek.get(i));
				if (StringUtil.isNotEmpty(assetId)) {
					sbf.append(" where \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				sbf.append(" group by \"LOG_CATEGORY\" ,\"LOG_PRIORITY\"	union ");

			}
			sbf.append("select \"LOG_CATEGORY\" as asset,\"LOG_PRIORITY\" as type , sum(\"LOG_AGGREGATED_COUNT\") as sum from  "
					+ dateWeek.get(dateWeek.size() - 1));
			if (StringUtil.isNotEmpty(assetId)) {
				sbf.append(" where \"LOG_ASSET_ID\" in ").append(
						"(" + assetId + ")");
			}
			sbf.append(" group by \"LOG_CATEGORY\" ,\"LOG_PRIORITY\" ");

			return auditReportDao.report8Table3(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report8Table4(List<String> dateWeek,
			String assetId) {

		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!dateWeek.isEmpty()) {
			for (int i = 0; i < dateWeek.size() - 1; i++) {
				sbf.append("select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ dateWeek.get(i));
				if (StringUtil.isNotEmpty(assetId)) {
					sbf.append(" where \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				sbf.append(" group by \"LOG_CUSTOMS5\" union ");

			}
			sbf.append(" select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ dateWeek.get(dateWeek.size() - 1));
			if (StringUtil.isNotEmpty(assetId)) {
				sbf.append(" where \"LOG_ASSET_ID\" in ").append(
						"(" + assetId + ")");
			}
			sbf.append(" group by \"LOG_CUSTOMS5\" ");

			return auditReportDao.report8Table4(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report8Table5(List<String> date,
			String assetId) {

		// 拼接字符串
		StringBuffer sbf = new StringBuffer();
		if (!date.isEmpty()) {
			for (int i = 0; i < date.size() - 1; i++) {
				sbf.append("select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
						+ date.get(i));
				if (StringUtil.isNotEmpty(assetId)) {
					sbf.append(" where \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				sbf.append(" group by \"LOG_CUSTOMS5\" union ");

			}
			sbf.append(" select \"LOG_CUSTOMS5\" AS type  ,sum(\"LOG_AGGREGATED_COUNT\") AS sum from "
					+ date.get(date.size() - 1));
			if (StringUtil.isNotEmpty(assetId)) {
				sbf.append(" where \"LOG_ASSET_ID\" in ").append(
						"(" + assetId + ")");
			}
			sbf.append(" group by \"LOG_CUSTOMS5\" ");

			return auditReportDao.report8Table5(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public List<Map<String, Object>> report12Table1(String assetId) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -7);
		Map map = new HashMap();
		map.put("start", new Date(c.getTime().getTime()));
		map.put("end", new Date());
		map.put("alertAssetId", assetId);
		return auditReportDao.report12Table1(map);
	}

	@Override
	public List<Map<String, Object>> report12Table2(String assetId) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -7);
		Map map = new HashMap();
		map.put("start", new Date(c.getTime().getTime()));
		map.put("end", new Date());
		map.put("alertAssetId", assetId);
		return auditReportDao.report12Table2(map);
	}

	@Override
	public List<Map<String, Object>> report12Table3(Map map) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -7);
		map.put("start", new Date(c.getTime().getTime()));
		map.put("end", new Date());
		return auditReportDao.report12Table3(map);
	}

	private String lIpTransitionStrIp(long lipAdd) {
		String ipStr = "";
		StringBuffer sBuffer = new StringBuffer();
		if (lipAdd > 0) {
			sBuffer.append(String.valueOf((lipAdd >>> 24)));
			sBuffer.append(".");
			// 将高8位置0，然后右移16位
			sBuffer.append(String.valueOf((lipAdd & 0x00FFFFFF) >>> 16));
			sBuffer.append(".");
			// 将高16位置0，然后右移8位
			sBuffer.append(String.valueOf((lipAdd & 0x0000FFFF) >>> 8));
			sBuffer.append(".");
			// 将高24位置0
			sBuffer.append(String.valueOf((lipAdd & 0x000000FF)));
			ipStr = sBuffer.toString();
		}
		return ipStr;
	}

	@Override
	public List<Map<String, Object>> report9Table1(long groupid) {

		return auditReportDao.report9Table1(groupid);
	}

	@Override
	public int report9Table1Count(long groupid) {

		return auditReportDao.report9Table1Count(groupid);
	}

	@Override
	public List<Map<String, Object>> report9Table2(long groupid) {
		return auditReportDao.report9Table2(groupid);
	}

	@Override
	public List<Map<String, Object>> report10Table1(String assetId) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -7);
		Map map = new HashMap();
		map.put("start", c.getTime().getTime());
		map.put("end", System.currentTimeMillis());
		map.put("assetId", assetId);
		return auditReportDao.report10Table2(map);
	}

	@Override
	public List<Map<String, Object>> report11Table1(String assetId) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -30);
		Map map = new HashMap();
		map.put("assetId", assetId);
		map.put("start", c.getTime().getTime());
		map.put("end", System.currentTimeMillis());
		return auditReportDao.report10Table2(map);
	}

	@Override
	public int report9Table2Count(Map map) {
		return auditReportDao.report9Table2Count(map);
	}

	@Override
	public List<Map<String, Object>> report7Table1() {
		return auditReportDao.report7Table1();
	}

	public List<Integer> getTable1ThisMonth() {
		return table1ThisMonth;
	}

	public void setTable1ThisMonth(List<Integer> table1ThisMonth) {
		this.table1ThisMonth = table1ThisMonth;
	}

	public List<Integer> getTable1LastMonth() {
		return table1LastMonth;
	}

	public void setTable1LastMonth(List<Integer> table1LastMonth) {
		this.table1LastMonth = table1LastMonth;
	}

	public List<Map<String, Object>> getReport2Table1() {
		return report2Table1;
	}

	public void setReport2Table1(List<Map<String, Object>> report2Table1) {
		this.report2Table1 = report2Table1;
	}

	public List<Map<String, Object>> getReport2Table12() {
		return report2Table12;
	}

	public void setReport2Table12(List<Map<String, Object>> report2Table12) {
		this.report2Table12 = report2Table12;
	}

	public List<Map<String, Object>> getReport2Table3() {
		return report2Table3;
	}

	public void setReport2Table3(List<Map<String, Object>> report2Table3) {
		this.report2Table3 = report2Table3;
	}

	public List<Map<String, Object>> getReport2Table2() {
		return report2Table2;
	}

	public void setReport2Table2(List<Map<String, Object>> report2Table2) {
		this.report2Table2 = report2Table2;
	}

	public List<Map<String, Object>> getReport2Table4() {
		return report2Table4;
	}

	public void setReport2Table4(List<Map<String, Object>> report2Table4) {
		this.report2Table4 = report2Table4;
	}

	public List<Map<String, Object>> getReport2Table5() {
		return report2Table5;
	}

	public void setReport2Table5(List<Map<String, Object>> report2Table5) {
		this.report2Table5 = report2Table5;
	}

	public List<Map<String, Object>> getReport2Table6() {
		return report2Table6;
	}

	public void setReport2Table6(List<Map<String, Object>> report2Table6) {
		this.report2Table6 = report2Table6;
	}

	public List<Map<String, Object>> getReport2Table7() {
		return report2Table7;
	}

	public void setReport2Table7(List<Map<String, Object>> report2Table7) {
		this.report2Table7 = report2Table7;
	}

	public List<Map<String, Object>> getReport2Table8() {
		return report2Table8;
	}

	public void setReport2Table8(List<Map<String, Object>> report2Table8) {
		this.report2Table8 = report2Table8;
	}

	public List<Map<String, Object>> getReport2Table9() {
		return report2Table9;
	}

	public void setReport2Table9(List<Map<String, Object>> report2Table9) {
		this.report2Table9 = report2Table9;
	}

	public List<Map<String, Object>> getReport2Table10() {
		return report2Table10;
	}

	public void setReport2Table10(List<Map<String, Object>> report2Table10) {
		this.report2Table10 = report2Table10;
	}

	public List<Map<String, Object>> getEquipmentDetailList() {
		return equipmentDetailList;
	}

	public void setEquipmentDetailList(
			List<Map<String, Object>> equipmentDetailList) {
		this.equipmentDetailList = equipmentDetailList;
	}

	public List<Map<String, Object>> getReport6Table1() {
		return report6Table1;
	}

	public void setReport6Table1(List<Map<String, Object>> report6Table1) {
		this.report6Table1 = report6Table1;
	}

	public List<Map<String, Object>> getReport6Table2() {
		return report6Table2;
	}

	public void setReport6Table2(List<Map<String, Object>> report6Table2) {
		this.report6Table2 = report6Table2;
	}

	public List<Map<String, Object>> getReport6Table3() {
		return report6Table3;
	}

	public void setReport6Table3(List<Map<String, Object>> report6Table3) {
		this.report6Table3 = report6Table3;
	}

	public List<Map<String, Object>> getReport6Table4() {
		return report6Table4;
	}

	public void setReport6Table4(List<Map<String, Object>> report6Table4) {
		this.report6Table4 = report6Table4;
	}

	public List<Map<String, Object>> getReport6Table5() {
		return report6Table5;
	}

	public void setReport6Table5(List<Map<String, Object>> report6Table5) {
		this.report6Table5 = report6Table5;
	}

	public List<Map<String, Object>> getReport7Table1() {
		return report7Table1;
	}

	public void setReport7Table1(List<Map<String, Object>> report7Table1) {
		this.report7Table1 = report7Table1;
	}

	public List<Map<String, Object>> getReport8Table1() {
		return report8Table1;
	}

	public void setReport8Table1(List<Map<String, Object>> report8Table1) {
		this.report8Table1 = report8Table1;
	}

	public List<Map<String, Object>> getReport8Table2() {
		return report8Table2;
	}

	public void setReport8Table2(List<Map<String, Object>> report8Table2) {
		this.report8Table2 = report8Table2;
	}

	public List<Map<String, Object>> getReport8Table3() {
		return report8Table3;
	}

	public void setReport8Table3(List<Map<String, Object>> report8Table3) {
		this.report8Table3 = report8Table3;
	}

	public List<Map<String, Object>> getReport8Table4() {
		return report8Table4;
	}

	public void setReport8Table4(List<Map<String, Object>> report8Table4) {
		this.report8Table4 = report8Table4;
	}

	public List<Map<String, Object>> getReport8Table5() {
		return report8Table5;
	}

	public void setReport8Table5(List<Map<String, Object>> report8Table5) {
		this.report8Table5 = report8Table5;
	}

	public List<Map<String, Object>> getReport9Table1() {
		return report9Table1;
	}

	public void setReport9Table1(List<Map<String, Object>> report9Table1) {
		this.report9Table1 = report9Table1;
	}

	public List<Map<String, Object>> getReport9Table2() {
		return report9Table2;
	}

	public void setReport9Table2(List<Map<String, Object>> report9Table2) {
		this.report9Table2 = report9Table2;
	}

	public int getReport9Table1Count() {
		return report9Table1Count;
	}

	public void setReport9Table1Count(int report9Table1Count) {
		this.report9Table1Count = report9Table1Count;
	}

	public int getReport9Table2Count() {
		return report9Table2Count;
	}

	public void setReport9Table2Count(int report9Table2Count) {
		this.report9Table2Count = report9Table2Count;
	}

	public String getTimeDetail() {
		return timeDetail;
	}

	public void setTimeDetail(String timeDetail) {
		this.timeDetail = timeDetail;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public List<String> getDateWeek() {
		return dateWeek;
	}

	public void setDateWeek(List<String> dateWeek) {
		this.dateWeek = dateWeek;
	}

	private String formartTime(long time) {
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date(time));
	}

	@Override
	public List<Map<String, Object>> report14Table2(String category,
			String assetId) {
		// 拼接字符串

		StringBuffer sbf = new StringBuffer();
		if (!dateWeek.isEmpty()) {
			for (int i = 0; i < dateWeek.size() - 1; i++) {
				sbf.append("select \"LOG_ASSET_ID\" as assetId  from   soc.\""
						+ dateWeek.get(i)
						+ "\" where	 \"LOG_PRIORITY\" = 3 and  \"LOG_CATEGORY\" = '"
						+ category + "' ");
				if (StringUtil.isNotEmpty(assetId)) {
					sbf.append(" and \"LOG_ASSET_ID\" in ").append(
							"(" + assetId + ")");
				}
				sbf.append(" group by \"LOG_ASSET_ID\" union ");
			}
			sbf.append(" select \"LOG_ASSET_ID\" as assetId  from  soc.\""
					+ dateWeek.get(dateWeek.size() - 1)
					+ "\" where	 \"LOG_PRIORITY\" = 3 and  \"LOG_CATEGORY\" =   '"
					+ category + "'  ");
			if (StringUtil.isNotEmpty(assetId)) {
				sbf.append(" and \"LOG_ASSET_ID\" in ").append(
						"(" + assetId + ")");
			}
			sbf.append(" group by \"LOG_ASSET_ID\" ");
			return auditReportDao.report14Table2(sbf.toString());
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

}