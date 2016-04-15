package com.soc.service.screen.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.soc.dao.screen.ComprehensiveEmergeDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.screen.ComprehensiveEmerService;

/**
 * 
 * <安全态势展现Service实现类> <实现告警信息的查看，搜索，快速搜索>
 * 
 * @author zhaokui
 * @version [版本号, 2012-11-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ComprehensiveEmerServiceImpl extends BaseServiceImpl implements
		ComprehensiveEmerService {

	private ComprehensiveEmergeDao comprehensiveEmergeDao;

	public ComprehensiveEmergeDao getComprehensiveEmergeDao() {
		return comprehensiveEmergeDao;
	}

	public void setComprehensiveEmergeDao(
			ComprehensiveEmergeDao comprehensiveEmergeDao) {
		this.comprehensiveEmergeDao = comprehensiveEmergeDao;
	}

	@Override
	public Object queryFacilityEvents(Map map) {
		// 查询最新设备事件Top10,并封装成jqchar识别的、类似于json的数据
		int flag = 0;

		Object object = "";

		List<Map> facilityList = new ArrayList<Map>();

		facilityList = comprehensiveEmergeDao.queryFacilityEvents(map);

		StringBuffer jsonDate = new StringBuffer();

		if (facilityList.size() > 0) {

			for (Map facilityMap : facilityList) {

				String facilityName = facilityMap.get("key").toString();

				if (facilityName.equals("") || facilityName == null) {
					facilityName = "其它";
				}

				long facilityCount = Integer.parseInt(facilityMap.get("value")
						.toString());

				if (flag == 0) {

					jsonDate.append("['" + facilityName + "'," + facilityCount
							+ "]");
				} else {

					jsonDate.append(",['" + facilityName + "'," + facilityCount
							+ "]");
				}
				flag++;
			}

			object = "[" + jsonDate + "]";
		}

		return object;
	}

	@Override
	public Object querySafetyEvents(Map map) {
		// 查询安全事件Top 10
		int flag = 0;

		Object object = "";

		List<Map> safetyList = new ArrayList<Map>();

		safetyList = comprehensiveEmergeDao.querySafetyEvents(map);

		StringBuffer jsonDateStr = new StringBuffer();
		StringBuffer jsonDateNo = new StringBuffer();
		StringBuffer jsonDate = new StringBuffer();
		String jsonStr = "";
		String jsonNo = "";
		jsonDateStr.append("[");
		jsonDateNo.append("[");
		jsonDate.append("[");

		if (safetyList.size() > 0) {

			for (Map safetyMap : safetyList) {

				String safetyName = "";

				String key = safetyMap.get("key").toString();

				safetyName = key;

				try {
					if (GlobalConfig.eventTypeTag.containsKey(Long
							.parseLong(key))) {

						safetyName = GlobalConfig.eventTypeTag.get(
								Long.parseLong(key)).toString();
					}

				} catch (Exception e) {
					//log.info("转化失败");
				}
				/*
				 * Map<Long, String> eventMap = GlobalConfig.eventTypeTag;
				 * 
				 * if (eventMap.containsKey(Long.parseLong(key))) {
				 * 
				 * safetyName = eventMap.get(Long.parseLong(key)).toString(); }
				 */

				if (safetyName == null || "".equals(safetyName)) {

					safetyName = "其它";
				}
				
				long safetyConut = Integer.parseInt(safetyMap.get("value")
						.toString());

				if (flag == 0) {
					jsonDate.append("{name:\'" + safetyName + "\',data:["
							+ safetyConut + "]}");
					jsonDateStr.append("'" + safetyName + "',");
					jsonDateNo.append(safetyConut + ",");
				} else {
					jsonDate.append(",{name:\'" + safetyName + "\',data:["
							+ safetyConut + "]}");
					jsonDateStr.append("'" + safetyName + "',");
					jsonDateNo.append(safetyConut + ",");
				}
				flag++;
			}
			jsonDate.append("]");
			jsonStr = jsonDateStr.substring(0, jsonDateStr.length() - 1);
			jsonNo = jsonDateNo.substring(0, jsonDateNo.length() - 1);
			jsonStr += "]";
			jsonNo += "]";
			object = jsonStr + "---" + jsonNo + "---" + jsonDate;

		} else {
			object = "[]---[]---[]";
		}
		return object;
	}

}
