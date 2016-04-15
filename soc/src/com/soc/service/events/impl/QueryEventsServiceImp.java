package com.soc.service.events.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.events.EventsDao;
import com.soc.dao.events.OriginalLogDao;
import com.soc.dao.events.QueryEventsDao;
import com.soc.dao.events.SummaryEventsDao;
import com.soc.dao.systemsetting.SettingDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.events.OriginalEvents;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.model.events.SummaryEvents;
import com.soc.model.systemsetting.Setting;
import com.soc.service.events.QueryEventsService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class QueryEventsServiceImp extends BaseServiceImpl implements
		QueryEventsService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private QueryEventsDao queryEvevntsDao; // 查询事件Dao

	private SummaryEventsDao summaryEventsDao; // 概要事件Dao
	 private OriginalLogDao originalLogDao;//原始事件dao
	/**
	 * 处理近期事件
	 */
	@Override
	public SearchResult queryRecentEvents(Map<String, Object> map, Page page) {

		//如果判断出map的长度大于1，说明有多个条件在同时进行查询。则：不限制查询只能返回1000条数据
		if(map.size()>1){
			map.put("flag", "NOLIMIT");
		}
		
		int rowsCount = summaryEventsDao.getRecentEventsCount(map);

		page.setTotalCount(rowsCount);
		List<Events> list = summaryEventsDao.queryRecentEvents(map,
				page.getStartIndex(), page.getPageSize());
		// 对list的处理
		String eventName = "";
		/*for (Events events : list) {

			//String eventNameTemp = events.getName();

			try {
				eventName = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventNameTemp));
				// String s = events.getsAdd();
				if (eventName.equals(null)) {

				} else {
					events.setName(eventName);
				}
			} catch (Exception e) {
				////log.info("转化失败");
			}

			String eventstype = events.getType();
			try {
				events.setCustomd1(Integer.parseInt(events.getType()));
			} catch (Exception e) {
				////log.info("事件类型转化失败 原因:事件类型原本是汉字无需转化");

			}

			try {
				String typeTemp = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventstype));

				if ((typeTemp != null) && (!typeTemp.equals(""))) {
					events.setType(typeTemp);
				}

			} catch (Exception e) {
				////log.info("类型转化失败");
			}

		}*/
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}

	/**
	 * 判断表是否存在
	 */
	@Override
	public int existsTable(String tableName) {

		return summaryEventsDao.existsTable(tableName);
	}

	// 原始事件
	private EventsDao eventsDao;

	public EventsDao getEventsDao() {
		return eventsDao;
	}

	public void setEventsDao(EventsDao eventsDao) {
		this.eventsDao = eventsDao;
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

	// 用于存储设置监控表内存储的时间
	private SettingDao settingDao;

	public SummaryEventsDao getSummaryEventsDao() {
		return summaryEventsDao;
	}

	public void setSummaryEventsDao(SummaryEventsDao summaryEventsDao) {
		this.summaryEventsDao = summaryEventsDao;
	}

	public QueryEventsDao getQueryEvevntsDao() {
		return queryEvevntsDao;
	}

	public void setQueryEvevntsDao(QueryEventsDao queryEvevntsDao) {
		this.queryEvevntsDao = queryEvevntsDao;
	}

	@Override
	public SearchResult<SummaryEvents> querySummaryEventsById(
			long queryEventsGroupId, Page page, String ids, String assetId,
			Map maps) {
		String eventsConditionsStr = queryEvevntsDao
				.querySummaryEventsConditions(queryEventsGroupId);

		String[] eventsConditionsStts = eventsConditionsStr.split(",");

		Map data = new HashMap();

		data.put("ids", ids);
		if (assetId == null) {

		} else {
			data.put("assetId", assetId);
		}
		String eventsType = (String) maps.get("eventsType");
		long deviceIp = 0;
		if (maps.get("deviceIp") != null) {
			deviceIp = Long.parseLong(maps.get("deviceIp").toString());

		}

		String sourceAdd = (String) maps.get("sourceAdd");
		String targetAdd = (String) maps.get("targetAdd");
		if (StringUtil.isNotEmpty(eventsType)) {
			data.put("eventsType", Integer.parseInt(eventsType));
		}
		if (deviceIp != 0) {
			data.put("deviceIp", deviceIp);
		}
		if (StringUtil.isNotEmpty(sourceAdd)) {
			data.put("sourceAdd", sourceAdd);
		}
		if (StringUtil.isNotEmpty(targetAdd)) {
			data.put("targetAdd", targetAdd);
		}

		String type = eventsConditionsStts[2].replaceAll("'", "").trim();

		String devproduct = eventsConditionsStts[3].replaceAll("'", "").trim();

		String program = eventsConditionsStts[4].replaceAll("'", "").trim();

		String devvendor = eventsConditionsStts[5].replaceAll("'", "").trim();

		String devtype = eventsConditionsStts[6].replaceAll("'", "").trim();

		// 关联分析时候用到的
		String devName = "";
		String programtype = "";
		String eventsCustomd1 = "";
		String rawid1 = "";
		String rawid2 = "";
		String rawid3 = "";
		String rawid4 = "";
		String rawid5 = "";
		String rawid6 = "";
		String rawid7 = "";
		String rawid8 = "";
		String rawid9 = "";
		String rawid10 = "";

		if (eventsConditionsStts.length > 7) {
			devName = eventsConditionsStts[7].replaceAll("'", "").trim();

			programtype = eventsConditionsStts[8].replaceAll("'", "").trim();

			eventsCustomd1 = eventsConditionsStts[9].replaceAll("'", "").trim();
		}
		if (eventsConditionsStts.length > 10) {
			rawid1 = eventsConditionsStts[10].replaceAll("'", "").trim()
					.replaceAll("\\|", ",");
			rawid2 = eventsConditionsStts[11].replaceAll("'", "").trim();
			rawid3 = eventsConditionsStts[12].replaceAll("'", "").trim();
			rawid4 = eventsConditionsStts[13].replaceAll("'", "").trim();
			rawid5 = eventsConditionsStts[14].replaceAll("'", "").trim();
			rawid6 = eventsConditionsStts[15].replaceAll("'", "").trim();
			rawid7 = eventsConditionsStts[16].replaceAll("'", "").trim();
			rawid8 = eventsConditionsStts[17].replaceAll("'", "").trim();
			// rawid9 = eventsConditionsStts[18].replaceAll("'", "").trim();
			// rawid10 = eventsConditionsStts[19].replaceAll("'", "").trim();

		}
		if (StringUtil.isNotEmpty(type)) {
			data.put("type", type);
		}
		if (StringUtil.isNotEmpty(devproduct)) {
			data.put("devproduct", devproduct);
		}
		if (StringUtil.isNotEmpty(program)) {
			data.put("program", program);
		}
		if (StringUtil.isNotEmpty(devvendor)) {
			data.put("devvendor", devvendor);
		}
		if (StringUtil.isNotEmpty(devtype)) {
			data.put("devtype", devtype);
		}
		if (StringUtil.isNotEmpty(devName)) {
			data.put("devName", devName);
		}
		if (StringUtil.isNotEmpty(programtype)) {
			data.put("programtype", programtype);
		}
		if (StringUtil.isNotEmpty(eventsCustomd1)) {
			data.put("customd1", eventsCustomd1);
		}
		if (StringUtil.isNotEmpty(rawid1)) {
			data.put("rawid1", rawid1);
		}
		if (StringUtil.isNotEmpty(rawid2)) {
			data.put("rawid2", rawid2);
		}
		if (StringUtil.isNotEmpty(rawid3)) {
			data.put("rawid3", rawid3);
		}
		if (StringUtil.isNotEmpty(rawid4)) {
			data.put("rawid4", rawid4);
		}
		if (StringUtil.isNotEmpty(rawid5)) {
			data.put("rawid5", rawid5);
		}
		if (StringUtil.isNotEmpty(rawid6)) {
			data.put("rawid6", rawid6);
		}
		if (StringUtil.isNotEmpty(rawid7)) {
			data.put("rawid7", rawid7);
		}
		if (StringUtil.isNotEmpty(rawid8)) {
			data.put("rawid8", rawid8);
		}
		if (StringUtil.isNotEmpty(rawid9)) {
			data.put("rawid9", rawid9);
		}
		if (StringUtil.isNotEmpty(rawid10)) {
			data.put("rawid10", rawid10);
		}

		int rowsCount = summaryEventsDao.count(eventsConditionsStts[0]
				.replaceAll("'", "").trim(), data);

		page.setTotalCount(rowsCount);

		List<SummaryEvents> attestationEventsList = summaryEventsDao
				.querySummaryEventsByEventsType(eventsConditionsStts[1]
						.replaceAll("'", "").trim(), data,
						page.getStartIndex(), page.getPageSize());
		// 对查找的列表做分页处理
		SearchResult<SummaryEvents> sr = new SearchResult<SummaryEvents>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult<SummaryEvents> queryAttestationEventsById(
			long queryEventsId, Page page) {
		String eventsConditionsStr = queryEvevntsDao
				.queryAttestationEvents(queryEventsId);
		String[] eventsConditionsStts = eventsConditionsStr.split(",");
		String value = eventsConditionsStts[2].replaceAll("'", "").trim();
		Map<String, String> data = new HashMap<String, String>();
		if (StringUtil.isNotEmpty(value)) {
			data.put("value", value);
		}
		int rowsCount = summaryEventsDao.count(eventsConditionsStts[0], data);
		page.setTotalCount(rowsCount);
		List<SummaryEvents> attestationEventsList = summaryEventsDao
				.querySummaryEventsByEventsType(eventsConditionsStts[1], data,
						page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult<SummaryEvents> queryConfigEvents(long queryEventsId,
			Page page) {
		String eventsConditionsStr = queryEvevntsDao
				.queryConfigEvents(queryEventsId);
		String[] eventsConditionsStts = eventsConditionsStr.split(",");
		Map<String, String> data = new HashMap<String, String>();
		data.put("value", eventsConditionsStts[2]);
		data.put("value", eventsConditionsStts[3]);
		data.put("value", eventsConditionsStts[4]);
		int rowsCount = summaryEventsDao.count(eventsConditionsStts[0], data);
		page.setTotalCount(rowsCount);
		List<SummaryEvents> attestationEventsList = summaryEventsDao
				.querySummaryEventsByEventsType(eventsConditionsStts[1], data,
						page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult<SummaryEvents> sr = new SearchResult<SummaryEvents>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult<SummaryEvents> queryOperationEvents(long queryEventsId,
			Page page) {
		String eventsConditionsStr = queryEvevntsDao
				.queryOperationEvents(queryEventsId);
		String[] eventsConditionsStts = eventsConditionsStr.split(",");
		Map<String, String> data = new HashMap<String, String>();
		data.put("value", eventsConditionsStts[2]);
		data.put("value", eventsConditionsStts[3]);
		data.put("value", eventsConditionsStts[4]);
		int rowsCount = summaryEventsDao.count(eventsConditionsStts[0], data);
		page.setTotalCount(rowsCount);
		List<SummaryEvents> attestationEventsList = summaryEventsDao
				.querySummaryEventsByEventsType(eventsConditionsStts[1], data,
						page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult<SummaryEvents> sr = new SearchResult<SummaryEvents>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult<SummaryEvents> queryResourcesEvents(long queryEventsId,
			Page page) {
		String eventsConditionsStr = queryEvevntsDao
				.queryResourcesEvents(queryEventsId);
		String[] eventsConditionsStts = eventsConditionsStr.split(",");
		Map<String, String> data = new HashMap<String, String>();
		data.put("value", eventsConditionsStts[2]);
		data.put("value", eventsConditionsStts[3]);
		data.put("value", eventsConditionsStts[4]);
		int rowsCount = summaryEventsDao.count(eventsConditionsStts[0], data);
		page.setTotalCount(rowsCount);
		List<SummaryEvents> attestationEventsList = summaryEventsDao
				.querySummaryEventsByEventsType(eventsConditionsStts[1], data,
						page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult<SummaryEvents> sr = new SearchResult<SummaryEvents>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult<SummaryEvents> queryAnomalyEvents(long queryEventsId,
			Page page) {
		String eventsConditionsStr = queryEvevntsDao
				.queryResourcesEvents(queryEventsId);
		String[] eventsConditionsStts = eventsConditionsStr.split(",");
		Map<String, String> data = new HashMap<String, String>();
		data.put("value", eventsConditionsStts[2]);
		data.put("value", eventsConditionsStts[3]);
		data.put("value", eventsConditionsStts[4]);
		int rowsCount = summaryEventsDao.count(eventsConditionsStts[0], data);
		page.setTotalCount(rowsCount);
		List<SummaryEvents> attestationEventsList = summaryEventsDao
				.querySummaryEventsByEventsType(eventsConditionsStts[1], data,
						page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult<SummaryEvents> sr = new SearchResult<SummaryEvents>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult<SummaryEvents> queryRecentEvents(Page page) {
		Date currentTime = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String, Long> data = new HashMap<String, Long>();
		data.put("value", (currentTime.getTime() - 60000));

		int rowsCount = summaryEventsDao.count(data);

		page.setTotalCount(rowsCount);

		List<SummaryEvents> attestationEventsList = queryEvevntsDao
				.queryRecentEvents(data, page.getStartIndex(),
						page.getPageSize());

		// 对查找的用户列表做分页处理
		SearchResult<SummaryEvents> sr = new SearchResult<SummaryEvents>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;

	}

	@Override
	public SearchResult<Events> queryCustomEventsRules(long queryEventsGroupId,
			Page page, String id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String eventsConditionsStr = queryEvevntsDao
				.queryCustomEventsRules(queryEventsGroupId);
		String[] eventsConditionsStrs = eventsConditionsStr.split(",");
		String threatValue = eventsConditionsStrs[2];
		String sourceAdd = eventsConditionsStrs[3];
		String sourcePort = eventsConditionsStrs[4];
		String targetAdd = eventsConditionsStrs[5];
		String targetPort = eventsConditionsStrs[6];
		String eventsType = eventsConditionsStrs[7];
		String beginTime = eventsConditionsStrs[8];
		String endTime = eventsConditionsStrs[9];
		String protocol = eventsConditionsStrs[10];
		String timeRange = eventsConditionsStrs[11];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;
			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:

				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					tableName.add("tbl_" + sdf.format(cal.getTime()));
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					tableName.add("tbl_" + sdf.format(cal.getTime()));
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();
					cal1.add(Calendar.DATE, -i);
					tableName.add("tbl_" + sdf.format(cal1.getTime()));
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);
					String tableNameTemp = sdf.format(calendar.getTime());
					if (endTime.equals(tableNameTemp)
							|| dayTime.equals(tableNameTemp)) {
						tableName.add("tbl_" + tableNameTemp);
						break;
					}
					tableName.add("tbl_" + tableNameTemp);
					num++;
				}
			} catch (ParseException e) {
				////log.info("转化失败");
			}
		}

		// 拼接SQL语句
		StringBuffer sqlStrCount = new StringBuffer();
		StringBuffer sqlStr = new StringBuffer();
		// 这里开始去掉没有的表 里面写的太乱 不知道谁是谁啊
		List<String> tableList = new ArrayList<String>();
		for (String string : tableName) {
			if (existsTable(string) == 0) {
				tableList.add(string);
			}
		}
		// 去掉没有的表
		tableName.removeAll(tableList);
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				if (sqlStr.length() <= 0) {
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");
				} else {
					sqlStr.append("  UNION ALL   SELECT * FROM "
							+ tableName.get(i) + "  WHERE 1=1");
				}

				if (StringUtil.isNotEmpty(threatValue)) {
					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");

				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(sourcePort)) {
					sqlStr.append("AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(targetPort)) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
					/*String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN  ('" + protocol.replaceAll("\\|","','")
							+ "')");
				}
				if (StringUtil.isNotEmpty(id)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" in(" + id + ")");
				}
			}
			sqlStr.append(" order by \"LOG_RECEPT_TIME\" DESC");
		}
		sqlStrCount
				.append("SELECT COUNT(*) FROM (" + sqlStr.toString() + ") C");
		int rowsCount = summaryEventsDao.count(eventsConditionsStrs[0],
				sqlStrCount.toString());
		page.setTotalCount(rowsCount);
		List<Events> attestationEventsList = summaryEventsDao
				.queryCustomEventsRules(eventsConditionsStrs[1],
						sqlStr.toString(), page.getStartIndex(),
						page.getPageSize());

		// 对list的处理
	/*	String eventName = "";
		for (Events events : attestationEventsList) {
			String eventNameTemp = events.getName();
			try {
				eventName = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventNameTemp));
				// String s = events.getsAdd();
				if (!eventName.equals(null)) {
					events.setName(eventName);
				}
			} catch (Exception e) {
				////log.info("转化失败");
			}

			String eventstype = events.getType();
			try {
				String typeTemp = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventstype));

				if ((typeTemp != null) && (!typeTemp.equals(""))) {
					events.setType(typeTemp);
				}

			} catch (Exception e) {
				//log.info("类型转化失败");
			}

		}*/

		// 对查找的用户列表做分页处理
		SearchResult<Events> sr = new SearchResult<Events>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public long insertQueryEventsRule(QueryEvents customQueryEvents) {
		long queryEventsId = queryEvevntsDao
				.insertQueryEventsRule(customQueryEvents);
		return queryEventsId;
	}

	@Override
	public long updateQueryEventsRule(QueryEvents customQueryEvents) {
		long queryEventsId = queryEvevntsDao
				.updateinsertQueryEventsRule(customQueryEvents);
		return queryEventsId;
	}

	@Override
	public void delCustom(long queryEventsId) {
		queryEvevntsDao.delCustom(queryEventsId);
	}

	@Override
	public Object getMonitorCount(String sqlKey, Map map) {
		// TODO Auto-generated method stub
		// 证明是查询认证数量的
		/*
		 * if (count.equals("true")) { List<Map> maplist =
		 * summaryEventsDao.monitorSummary(sqlKey, map);
		 * 
		 * Object temp = "[['数量'," + result + "]]";
		 * 
		 * return temp; }
		 */
		/*
		 * else {
		 */
		List<Map> maplist = summaryEventsDao.monitorSummary(sqlKey, map);

		StringBuffer temp = new StringBuffer();
		temp.append("[");

		for (int i = 0; i < maplist.size(); i++) {
			if (maplist.get(i).get("key") != null) {
				String key = maplist.get(i).get("key").toString();
				int count1 = Integer.parseInt(maplist.get(i).get("count")
						.toString());
				if (i == 0) {
					temp.append("['" + key + "'," + count1 + "]");
				} else {
					temp.append(",['" + key + "'," + count1 + "]");
				}
			}
		}
		temp.append("]");

		Object source = "" + temp + "";

		return source;
		/* } */

	}

	@Override
	public Object getMonitorServerCount(String sqlKey, Map map) {
		// TODO Auto-generated method stub
		/*
		 * if (count.equals("true")) { int result =
		 * summaryEventsDao.monitorCount(sqlKey, map);
		 * 
		 * Object temp = "[['数量'," + result + "]]";
		 * 
		 * return temp; } else {
		 */
		List<Map> maplist = summaryEventsDao.monitorSummary(sqlKey, map);

		StringBuffer temp = new StringBuffer();

		temp.append("[");

		for (int i = 0; i < maplist.size(); i++) {
			String key = maplist.get(i).get("key").toString();

			int count1 = Integer.parseInt(maplist.get(i).get("count")
					.toString());

			if (i == 0) {

				temp.append("['" + key + "'," + count1 + "]");

			} else {
				temp.append(",['" + key + "'," + count1 + "]");
			}
		}
		temp.append("]");

		Object source = "" + temp + "";

		return source;
		/* } */
	}

	@Override
	public Object getMonitorCustomCount(Map map) {
		// 要获取两天的表名 还要判断是不是存在...麻烦
		Calendar c = Calendar.getInstance();// 创建一个日历
		String today = "tbl_"
				+ new SimpleDateFormat("yyyyMMdd").format(c.getTime());// 今天
		c.add(Calendar.DAY_OF_YEAR, -1);
		String yesterday = "tbl_"
				+ new SimpleDateFormat("yyyyMMdd").format(c.getTime());// 昨天
		String sql="";
		// 判断表是不是存在 不存在不拼接 今天的肯定有 昨天在说 先把今天的拼上
		// 先判断是那种数据库，然后拼接出相应的一个sql字符串
		System.out.println(GlobalConfig.sqlId);
		System.out.println(GlobalConfig.sqlId.equals("pgAdmin"));
		if(GlobalConfig.sqlId.equals("pgAdmin")){
			//拼接postgreSQL的语句
			sql = "select count(\"" + map.get("chatX") + "\") as count,\""
					+ map.get("chatX") + "\" as key from " + today
					+ " group by key ";
	    
			int tempFlag = existsTable(yesterday);
			//yesterday = 
			// 如果yesterday是空就不拼接了
			if (tempFlag == 1) {
				sql = sql + "union all select count(\"" + map.get("chatX")
						+ "\")  as count,\"" + map.get("chatX") + "\" as \'key\' from "
						+ yesterday + " group by key ";
			}
		}else if(GlobalConfig.sqlId.equals("sqlServer")){
			//拼接sqlServer的语句
			sql = "select count(\"" + map.get("chatX") + "\") as count,\""
					+ map.get("chatX") + "\" as \'key\' from " + today
					+ " group by \"" + map.get("chatX") + "\" ";
	    
			int tempFlag = existsTable(yesterday);
			//yesterday = 
			// 如果yesterday是空就不拼接了
			if (tempFlag == 1) {
				sql = sql + "union all select count(\"" + map.get("chatX")
						+ "\")  as count,\"" + map.get("chatX") + "\" as \'key\' from " 
						+ yesterday + " group by \"" + map.get("chatX") + "\" ";
			}
		}
		
		map.put("sql", sql);
		List<Map> maplist = summaryEventsDao.getMonitorCustomCount(map);

		StringBuffer temp = new StringBuffer();

		temp.append("[");
		String chatX = (String) map.get("chatX");
		for (int i = 0; i < maplist.size(); i++) {
			// 判断key的类型 因为可能是ip(Long表示)或者事件类型(数字表示) 等级(级别表示) 这里要解析一下
			String key = maplist.get(i).get("key").toString();
			if ("LOG_PRIORITY".equals(chatX)) {// 等级
				if ("5".equals(key)) {
					key = "高级";
				} else if ("4".equals(key)) {
					key = "中高级";
				} else if ("3".equals(key)) {
					key = "中级";
				} else if ("2".equals(key)) {
					key = "中低级";
				} else {
					key = "低级";
				}

			} else if ("LOG_CATEGORY".equals(chatX)) {
				key = GlobalConfig.eventCategoryTag.get(key);
			} else if ("LOG_TYPE".equals(chatX)) {
				key = GlobalConfig.eventTypeTag.get(Long.parseLong(key));
			} else if ("LOG_SADDR".equals(chatX) || "LOG_DADDR".equals(chatX)) {
				key = lIpTransitionStrIp(Long.parseLong(key));
			}

			int count1 = Integer.parseInt(maplist.get(i).get("count")
					.toString());

			if (i == 0) {

				temp.append("['" + key + "'," + count1 + "]");

			} else {
				temp.append(",['" + key + "'," + count1 + "]");
			}
		}
		temp.append("]");

		Object source = "" + temp + "";

		return source;
	}

	@Override
	public SearchResult<SummaryEvents> queryServerEvents(long queryEventsId,
			Page page) {
		String eventsConditionsStr = queryEvevntsDao
				.queryServerEvents(queryEventsId);
		String[] eventsConditionsStts = eventsConditionsStr.split(",");
		Map<String, String> data = new HashMap<String, String>();
		String type = eventsConditionsStts[2].replaceAll("'", "").trim();
		String devproduct = eventsConditionsStts[3].replaceAll("'", "").trim();
		String program = eventsConditionsStts[4].replaceAll("'", "").trim();
		String devvendor = eventsConditionsStts[5].replaceAll("'", "").trim();
		String devtype = eventsConditionsStts[6].replaceAll("'", "").trim();
		if (StringUtil.isNotEmpty(type)) {
			data.put("type", type);
		}
		if (StringUtil.isNotEmpty(devproduct)) {
			data.put("devproduct", devproduct);
		}
		if (StringUtil.isNotEmpty(program)) {
			data.put("program", program);
		}
		if (StringUtil.isNotEmpty(devvendor)) {
			data.put("devvendor", devvendor);
		}
		if (StringUtil.isNotEmpty(devtype)) {
			data.put("devtype", devtype);
		}
		int rowsCount = summaryEventsDao.count(eventsConditionsStts[0], data);
		page.setTotalCount(rowsCount);
		List<SummaryEvents> attestationEventsList = summaryEventsDao
				.querySummaryEventsByEventsType(eventsConditionsStts[1], data,
						page.getStartIndex(), page.getPageSize());
		// 对查找的列表做分页处理
		SearchResult<SummaryEvents> sr = new SearchResult<SummaryEvents>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult<Events> queryRelevanceAnalysis(long relId, Page page) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("relId", String.valueOf(relId));
		int rowsCount = summaryEventsDao.count("RelevanceAnalysis_cache.count",
				map);
		page.setTotalCount(rowsCount);
		List relevanceAnalysisList = summaryEventsDao.queryRelevanceAnalysis(
				relId, page.getStartIndex(), page.getPageSize());
		// 对查找的列表做分页处理
		SearchResult<Events> sr = new SearchResult<Events>();
		sr.setList(relevanceAnalysisList);
		sr.setPage(page);
		return sr;

	}

	@Override
	public void insertRelevanceAnalysis() {
		// 清空临时表
		summaryEventsDao.emptyTable();

		List<QueryEventsGroup> queryEventsGroupList = queryEvevntsDao
				.queryRelevanceAnalysisConditions();

		for (QueryEventsGroup queryEventsGroup : queryEventsGroupList) {
			String[] eventsConditionsStts = queryEventsGroup
					.getQueryEventsConditions().split(",");
			String sqlKey = eventsConditionsStts[1];
			Map<String, String> data = new HashMap<String, String>();
			String type = eventsConditionsStts[2].replaceAll("'", "").trim();
			String name = eventsConditionsStts[3].replaceAll("'", "").trim();
			String programtype = eventsConditionsStts[4].replaceAll("'", "")
					.trim();
			String devtype = eventsConditionsStts[5].replaceAll("'", "").trim();
			String action = eventsConditionsStts[6].replaceAll("'", "").trim();
			String customs2 = eventsConditionsStts[7].replaceAll("'", "")
					.trim();
			String msg = eventsConditionsStts[8].replaceAll("'", "").trim();
			String rawid = eventsConditionsStts[9].replaceAll("'", "").trim();
			String devvendor = eventsConditionsStts[10].replaceAll("'", "")
					.trim();
			String resource = eventsConditionsStts[11].replaceAll("'", "")
					.trim();
			String devproduct = eventsConditionsStts[12].replaceAll("'", "")
					.trim();
			String result = eventsConditionsStts[13].replaceAll("'", "").trim();
			String systype = eventsConditionsStts[14].replaceAll("'", "")
					.trim();
			String program = eventsConditionsStts[15].replaceAll("'", "")
					.trim();
			if (StringUtil.isNotEmpty(type)) {
				data.put("type", type);
			}
			if (StringUtil.isNotEmpty(name)) {
				data.put("name", name);
			}
			if (StringUtil.isNotEmpty(programtype)) {
				data.put("programtype", programtype);
			}
			if (StringUtil.isNotEmpty(devtype)) {
				data.put("devtype", devtype);
			}
			if (StringUtil.isNotEmpty(action)) {
				data.put("action", action);
			}
			if (StringUtil.isNotEmpty(customs2)) {
				data.put("customs2", customs2);
			}
			if (StringUtil.isNotEmpty(msg)) {
				data.put("msg", msg);
			}
			if (StringUtil.isNotEmpty(rawid)) {
				data.put("rawid", rawid);
			}
			if (StringUtil.isNotEmpty(devvendor)) {
				data.put("devvendor", devvendor);
			}
			if (StringUtil.isNotEmpty(resource)) {
				data.put("resource", resource);
			}
			if (StringUtil.isNotEmpty(devproduct)) {
				data.put("devproduct", devproduct);
			}
			if (StringUtil.isNotEmpty(result)) {
				data.put("result", result);
			}
			if (StringUtil.isNotEmpty(systype)) {
				data.put("systype", systype);
			}
			if (StringUtil.isNotEmpty(program)) {
				data.put("program", program);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			data.put("tableName", "tbl_" + sdf.format(new Date()));

			List<Events> eventsList = summaryEventsDao.queryRelevanceAnalysis(
					sqlKey, data);

			Map<String, Object> map = null;

			for (Events events : eventsList) {
				map = new HashMap<String, Object>();
				map.put("identification", events.getIdentification());
				map.put("type", events.getType());
				map.put("name", events.getName());
				map.put("rawId", events.getRawId());
				map.put("programType", events.getProgramType());
				map.put("devType", events.getDevType());
				map.put("action", events.getAction());
				map.put("customs2", events.getCustomd2());
				map.put("msg", events.getMsg());
				map.put("devVendor", events.getDevVendor());
				map.put("resource", events.getResource());
				map.put("devproduct", events.getDevproduct());
				map.put("result", events.getResult());
				map.put("systype", events.getSystype());
				map.put("program", events.getProgram());
				map.put("priority", events.getPriority());
				map.put("sAddr", events.getsAdd());
				map.put("sPort", events.getsPort());
				map.put("dAddr", events.gettAdd());
				map.put("dPort", events.getdPort());
				map.put("aggregatedCount", events.getAggregatedCount());
				map.put("sendTime", events.getSendTimes());
				map.put("relId", queryEventsGroup.getQueryEventsGroupId());
				map.put("tableName", sdf.format(new Date()));
				summaryEventsDao.insertRelevanceAnalysis(map);
			}
		}
	}

	/**
	 * 字符串IP转换为Long型IP
	 * 
	 * @param strIP
	 * @return
	 */
	public Long StrIpTransitionLip(String strIP) {
		String[] strIps = strIP.split("\\.");
		long lIp0 = Long.parseLong(strIps[0]);
		long lIp1 = Long.parseLong(strIps[1]);
		long lIp2 = Long.parseLong(strIps[2]);
		long lIp3 = Long.parseLong(strIps[3]);
		long lip = (lIp0 << 24) + (lIp1 << 16) + (lIp2 << 8) + lIp3;
		return lip;
	}

	/**
	 * 字符串日期转换为Long型日期
	 * 
	 * @param StrTime
	 * @return
	 */
	public Long StrTimeTransitionLtime(String StrTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long time = null;
		try {
			Date date = sdf.parse(StrTime);
			time = date.getTime();
		} catch (ParseException e) {
			//log.info("转化失败");
		}
		return time;
	}

	@Override
	public void insertMonitorFtpEvents() {
		// 获得上次的查询时间
		String setting = settingDao.queryByKey("ftpDate");

		Map<String, Object> map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 获得当前的时间
		String date = sdf.format(new Date());

		map.put("appprotocol", 21);

		// 如果查询到的时间是空值或者没值
		if (setting.equals("0") || setting == null) {
			map.put("event_id", 0);

			map.put("tableName", "tbl_" + sdf.format(new Date()));
		} else {
			// 如果跟当前时间相同
			if (setting.equals(date)) {
				map.put("tableName", "tbl_" + sdf.format(new Date()));

				// 获得上次的最大Id
				String lastId = settingDao.queryByKey("ftpId");

				map.put("event_id", Long.valueOf(lastId));

			} else {
				map.put("tableName", "tbl_" + setting);

				String lastId = settingDao.queryByKey("ftpId");

				map.put("event_id", Long.valueOf(lastId));

				Setting setting2 = new Setting();

				setting2.setKey("ftpId");

				setting2.setValue("0");

				// 更新上次查询出来的最大的id
				settingDao.update(setting2);

			}
		}

		List<Map> eventList = eventsDao.queryMointorEvents(map);

		if (!eventList.isEmpty() && eventList != null && setting.equals(date)) {

			// //System.out.println(eventList.size());

			// //System.out.println(eventList.get(0).get("LOG_ID").toString());

			Map mapTemp = new HashMap();

			mapTemp = eventList.get(eventList.size() - 1);

			String temp = mapTemp.get("LOG_ID").toString();
			// System.out.println(mapTemp.get("LOG_ID"));

			// String temp = eventList.get(eventList.size() -
			// 1).get("LOG_ID").toString();

			Setting setting2 = new Setting();

			setting2.setKey("ftpId");

			setting2.setValue(temp);

			// 更新上次查询出来的最大的id
			settingDao.update(setting2);

			List<SummaryEvents> summaryEventsList = new ArrayList<SummaryEvents>();

			for (Map map1 : eventList) {

				map1.put("events_puttime", System.currentTimeMillis());

			}

		}

		Setting setting1 = new Setting();

		setting1.setKey("ftpDate");

		setting1.setValue(date);

		settingDao.update(setting1);

		// 插入ftp事件
		eventsDao.insertMonitorFtpEvents(eventList);

	}

	@Override
	public void insertMonitorTelnetEvents() {
		// 获得上次的查询时间
		String setting = settingDao.queryByKey("telnetDate");

		Map<String, Object> map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 获得当前的时间
		String date = sdf.format(new Date());

		map.put("appprotocol", 23);

		// 如果查询到的时间是空值或者没值
		if (setting.equals("0") || setting == null) {
			map.put("event_id", 0);

			map.put("tableName", "tbl_" + sdf.format(new Date()));
		} else {
			// 如果跟当前时间相同
			if (setting.equals(date)) {
				map.put("tableName", "tbl_" + sdf.format(new Date()));

				// 获得上次的最大Id
				String lastId = settingDao.queryByKey("telnetId");

				map.put("event_id", Long.valueOf(lastId));

			} else {
				map.put("tableName", "tbl_" + setting);

				String lastId = settingDao.queryByKey("telnetId");

				map.put("event_id", Long.valueOf(lastId));

				Setting setting2 = new Setting();

				setting2.setKey("telnetId");

				setting2.setValue("0");

				// 更新上次查询出来的最大的id
				settingDao.update(setting2);

			}
		}

		List<Map> eventList = eventsDao.queryMointorEvents(map);

		if (!eventList.isEmpty() && eventList != null && setting.equals(date)) {

			// //System.out.println(eventList.size());

			// //System.out.println(eventList.get(0).get("LOG_ID").toString());

			Map mapTemp = new HashMap();

			mapTemp = eventList.get(eventList.size() - 1);

			String temp = mapTemp.get("LOG_ID").toString();

			// String temp = eventList.get(eventList.size() -
			// 1).get("LOG_ID").toString();

			Setting setting2 = new Setting();

			setting2.setKey("telnetId");

			setting2.setValue(temp);

			// 更新上次查询出来的最大的id
			settingDao.update(setting2);

			List<SummaryEvents> summaryEventsList = new ArrayList<SummaryEvents>();

			for (Map map1 : eventList) {

				map1.put("events_puttime", System.currentTimeMillis());

			}

		}

		Setting setting1 = new Setting();

		setting1.setKey("telnetDate");

		setting1.setValue(date);

		settingDao.update(setting1);

		// 插入telnet事件
		eventsDao.insertMonitorTelnetEvents(eventList);

	}

	@Override
	public void insertMonitorconnectRefuseEvents() {
		// 获得上次的查询时间
		String setting = settingDao.queryByKey("connectDate");

		Map<String, Object> map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 获得当前的时间
		String date = sdf.format(new Date());

		map.put("policy", "/policy/breach");

		// 如果查询到的时间是空值或者没值
		if (setting.equals("0") || setting == null) {
			map.put("event_id", 0);

			map.put("tableName", "tbl_" + sdf.format(new Date()));
		} else {
			// 如果跟当前时间相同
			if (setting.equals(date)) {
				map.put("tableName", "tbl_" + sdf.format(new Date()));

				// 获得上次的最大Id
				String lastId = settingDao.queryByKey("connectId");

				map.put("event_id", Long.valueOf(lastId));

			} else {
				map.put("tableName", "tbl_" + setting);

				String lastId = settingDao.queryByKey("connectId");

				map.put("event_id", Long.valueOf(lastId));

				Setting setting2 = new Setting();

				setting2.setKey("connectId");

				setting2.setValue("0");

				// 更新上次查询出来的最大的id
				settingDao.update(setting2);

			}
		}

		List<Map> eventList = eventsDao.queryMointorEvents(map);

		if (!eventList.isEmpty() && eventList != null && setting.equals(date)) {

			Map mapTemp = new HashMap();

			mapTemp = eventList.get(eventList.size() - 1);

			String temp = mapTemp.get("LOG_ID").toString();

			Setting setting2 = new Setting();

			setting2.setKey("connectId");

			setting2.setValue(temp);

			// 更新上次查询出来的最大的id
			settingDao.update(setting2);

			List<SummaryEvents> summaryEventsList = new ArrayList<SummaryEvents>();

			for (Map map1 : eventList) {

				map1.put("events_puttime", System.currentTimeMillis());

			}

		}

		Setting setting1 = new Setting();

		setting1.setKey("connectDate");

		setting1.setValue(date);

		settingDao.update(setting1);

		// 插入连接被阻击事件
		eventsDao.insertMonitorConnectRefuse(eventList);

	}

	@Override
	public void insertMonitorAccountLockedEvents() {
		// TODO Auto-generated method stub
		// 获得上次的查询时间
		String setting = settingDao.queryByKey("accountLockDate");

		Map<String, Object> map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 获得当前的时间
		String date = sdf.format(new Date());

		map.put("action", "/lock");

		// 如果查询到的时间是空值或者没值
		if (setting.equals("0") || setting == null) {
			map.put("event_id", 0);

			map.put("tableName", "tbl_" + sdf.format(new Date()));
		} else {
			// 如果跟当前时间相同
			if (setting.equals(date)) {
				map.put("tableName", "tbl_" + sdf.format(new Date()));

				// 获得上次的最大Id
				String lastId = settingDao.queryByKey("accountLockId");

				map.put("event_id", Long.valueOf(lastId));

			} else {
				map.put("tableName", "tbl_" + setting);

				String lastId = settingDao.queryByKey("accountLockId");

				map.put("event_id", Long.valueOf(lastId));

				Setting setting2 = new Setting();

				setting2.setKey("accountLockId");

				setting2.setValue("0");

				// 更新上次查询出来的最大的id
				settingDao.update(setting2);

			}
		}

		List<Map> eventList = eventsDao.queryMointorEvents(map);

		if (!eventList.isEmpty() && eventList != null && setting.equals(date)) {

			Map mapTemp = new HashMap();

			mapTemp = eventList.get(eventList.size() - 1);

			String temp = mapTemp.get("LOG_ID").toString();

			Setting setting2 = new Setting();

			setting2.setKey("accountLockId");

			setting2.setValue(temp);

			// 更新上次查询出来的最大的id
			settingDao.update(setting2);

			List<SummaryEvents> summaryEventsList = new ArrayList<SummaryEvents>();

			for (Map map1 : eventList) {

				map1.put("events_puttime", System.currentTimeMillis());

			}

		}

		Setting setting1 = new Setting();

		setting1.setKey("accountLockDate");

		setting1.setValue(date);

		settingDao.update(setting1);

		// 插入账户被锁定事件
		eventsDao.insertMonitorAccountLocked(eventList);

	}

	@Override
	public void insertMonitorAccountLoginEvents() {
		// TODO Auto-generated method stub

		// 获得上次的查询时间
		String setting = settingDao.queryByKey("accountLoginDate");

		Map<String, Object> map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 获得当前的时间
		String date = sdf.format(new Date());

		StringBuffer rawidList = new StringBuffer();

		rawidList.append("672");

		rawidList.append(",673");

		rawidList.append(",674");

		rawidList.append(",675");

		rawidList.append(",676");

		rawidList.append(",677");

		rawidList.append(",678");

		rawidList.append(",681");

		rawidList.append(",682");

		rawidList.append(",683");

		map.put("rawidList", rawidList);

		map.put("devvendor", "microsoft");

		// 如果查询到的时间是空值或者没值
		if (setting.equals("0") || setting == null) {
			map.put("event_id", 0);

			map.put("tableName", "tbl_" + sdf.format(new Date()));
		} else {
			// 如果跟当前时间相同
			if (setting.equals(date)) {
				map.put("tableName", "tbl_" + sdf.format(new Date()));

				// 获得上次的最大Id
				String lastId = settingDao.queryByKey("accountLoginId");

				map.put("event_id", Long.valueOf(lastId));

			} else {
				map.put("tableName", "tbl_" + setting);

				String lastId = settingDao.queryByKey("accountLoginId");

				map.put("event_id", Long.valueOf(lastId));

				Setting setting2 = new Setting();

				setting2.setKey("accountLoginId");

				setting2.setValue("0");

				// 更新上次查询出来的最大的id
				settingDao.update(setting2);

			}
		}

		List<Map> eventList = eventsDao.queryMointorEvents(map);

		if (!eventList.isEmpty() && eventList != null && setting.equals(date)) {

			Map mapTemp = new HashMap();

			mapTemp = eventList.get(eventList.size() - 1);

			String temp = mapTemp.get("LOG_ID").toString();

			Setting setting2 = new Setting();

			setting2.setKey("accountLoginId");

			setting2.setValue(temp);

			// 更新上次查询出来的最大的id
			settingDao.update(setting2);

			List<SummaryEvents> summaryEventsList = new ArrayList<SummaryEvents>();

			for (Map map1 : eventList) {

				map1.put("events_puttime", System.currentTimeMillis());

			}

		}

		Setting setting1 = new Setting();

		setting1.setKey("accountLoginDate");

		setting1.setValue(date);

		settingDao.update(setting1);

		// 插入账户被锁定事件
		eventsDao.insertMonitorAccountLogin(eventList);

	}

	@Override
	public void insertMonitorPrivligeEvents() {
		// TODO Auto-generated method stub
		// 获得上次的查询时间
		String setting = settingDao.queryByKey("privligeDate");

		Map<String, Object> map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 获得当前的时间
		String date = sdf.format(new Date());

		map.put("rawid1", 576);

		map.put("rawid2", 578);

		map.put("devvendor", "microsoft");

		// 如果查询到的时间是空值或者没值
		if (setting.equals("0") || setting == null) {
			map.put("event_id", 0);

			map.put("tableName", "tbl_" + sdf.format(new Date()));
		} else {
			// 如果跟当前时间相同
			if (setting.equals(date)) {
				map.put("tableName", "tbl_" + sdf.format(new Date()));

				// 获得上次的最大Id
				String lastId = settingDao.queryByKey("privligeId");

				map.put("event_id", Long.valueOf(lastId));

			} else {
				map.put("tableName", "tbl_" + setting);

				String lastId = settingDao.queryByKey("privligeId");

				map.put("event_id", Long.valueOf(lastId));

				Setting setting2 = new Setting();

				setting2.setKey("privligeId");

				setting2.setValue("0");

				// 更新上次查询出来的最大的id
				settingDao.update(setting2);

			}
		}

		List<Map> eventList = eventsDao.queryMointorEvents(map);

		if (!eventList.isEmpty() && eventList != null && setting.equals(date)) {

			Map mapTemp = new HashMap();

			mapTemp = eventList.get(eventList.size() - 1);

			String temp = mapTemp.get("LOG_ID").toString();

			Setting setting2 = new Setting();

			setting2.setKey("privligeId");

			setting2.setValue(temp);

			// 更新上次查询出来的最大的id
			settingDao.update(setting2);

			List<SummaryEvents> summaryEventsList = new ArrayList<SummaryEvents>();

			for (Map map1 : eventList) {

				map1.put("events_puttime", System.currentTimeMillis());

			}

		}

		Setting setting1 = new Setting();

		setting1.setKey("privligeDate");

		setting1.setValue(date);

		settingDao.update(setting1);

		// 插入连接被阻击事件
		eventsDao.insertMonitorPrivilge(eventList);

	}

	/** {@inheritDoc} */

	@Override
	public void insertMonitorAccountManageEvents() {
		// TODO Auto-generated method stub
		String setting = settingDao.queryByKey("accountManageDate");

		Map<String, Object> map = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// 获得当前的时间
		String date = sdf.format(new Date());
		StringBuffer rawidList = new StringBuffer();

		rawidList.append("624");
		rawidList.append(",627");
		rawidList.append(",628");
		rawidList.append(",684");
		rawidList.append(",685");

		map.put("rawidList", rawidList);

		map.put("rawid1", 630);

		map.put("rawid2", 639);

		map.put("rawid3", 641);

		map.put("rawid4", 668);

		map.put("devvendor", "microsoft");

		// 如果查询到的时间是空值或者没值
		if (setting.equals("0") || setting == null) {
			map.put("event_id", 0);

			map.put("tableName", "tbl_" + sdf.format(new Date()));
		} else {
			// 如果跟当前时间相同
			if (setting.equals(date)) {
				map.put("tableName", "tbl_" + sdf.format(new Date()));

				// 获得上次的最大Id
				String lastId = settingDao.queryByKey("accountManageId");

				map.put("event_id", Long.valueOf(lastId));

			} else {
				map.put("tableName", "tbl_" + setting);

				String lastId = settingDao.queryByKey("accountManageId");

				map.put("event_id", Long.valueOf(lastId));

				Setting setting2 = new Setting();

				setting2.setKey("accountManageId");

				setting2.setValue("0");

				// 更新上次查询出来的最大的id
				settingDao.update(setting2);

			}
		}

		List<Map> eventList = eventsDao.queryMointorEvents(map);

		if (!eventList.isEmpty() && eventList != null && setting.equals(date)) {

			Map mapTemp = new HashMap();

			mapTemp = eventList.get(eventList.size() - 1);

			String temp = mapTemp.get("LOG_ID").toString();

			Setting setting2 = new Setting();

			setting2.setKey("accountManageId");

			setting2.setValue(temp);

			// 更新上次查询出来的最大的id
			settingDao.update(setting2);

			List<SummaryEvents> summaryEventsList = new ArrayList<SummaryEvents>();

			for (Map map1 : eventList) {

				map1.put("events_puttime", System.currentTimeMillis());

			}

		}

		Setting setting1 = new Setting();

		setting1.setKey("accountManageDate");

		setting1.setValue(date);

		settingDao.update(setting1);

		// 插入账户管理事件
		eventsDao.insertMonitorAccountManage(eventList);
	}

	@Override
	public SearchResult<Events> queryCustomEventsRules(String str, Page page,
			String id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] eventsConditionsStrs = str.split(",");
		String threatValue = eventsConditionsStrs[2];
		String sourceAdd = eventsConditionsStrs[3];
		String sourcePort = eventsConditionsStrs[4];
		String targetAdd = eventsConditionsStrs[5];
		String targetPort = eventsConditionsStrs[6];
		String eventsType = eventsConditionsStrs[7];
		String beginTime = eventsConditionsStrs[8];
		String endTime = eventsConditionsStrs[9];
		String protocol = eventsConditionsStrs[10];
		String timeRange = eventsConditionsStrs[11];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;
			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:
				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();

					cal1.add(Calendar.DATE, -i);

					// //System.out.println(sdf.format(cal1.getTime()));

					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal1.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal1.getTime()));
					}
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);

					String tableNameTemp = sdf.format(calendar.getTime());

					// System.out.println(tableNameTemp);

					int flag = eventsDao.existsTable("tbl_" + tableNameTemp);
					if (flag!=0) {
						if (endTime.equals(tableNameTemp)
								|| dayTime.equals(tableNameTemp)) {
							tableName.add("tbl_" + tableNameTemp);
							break;
						}
						tableName.add("tbl_" + tableNameTemp);
					}
					num++;
				}
			} catch (ParseException e) {
				//log.info("转化失败");
			}
		}

		// 拼接SQL语句
		StringBuffer sqlStrCount = new StringBuffer();
		StringBuffer sqlStr = new StringBuffer();
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				if (sqlStr.length() <= 0) {
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");
				} else {
					sqlStr.append("  UNION ALL   SELECT * FROM "
							+ tableName.get(i) + "  WHERE 1=1");
				}

				if (StringUtil.isNotEmpty(threatValue)) {

					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");
				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(sourcePort)) {
					sqlStr.append(" AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(targetPort)) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
					/*String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}
					sqlStr.append(" AND \"LOG_CATEGORY\"  IN  ("
							+ sb.toString() + ")");*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN  ('" + protocol.replaceAll("\\|", "','")
							+ "')");
				}
				if (StringUtil.isNotEmpty(id)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" in(" + id + ")");
				}
			}
		}
		sqlStrCount
				.append("SELECT COUNT(*) FROM (" + sqlStr.toString() + ") C");

		int rowsCount = summaryEventsDao.count(eventsConditionsStrs[0],
				sqlStrCount.toString());
		page.setTotalCount(rowsCount);
		List<Events> attestationEventsList = summaryEventsDao
				.queryCustomEventsRules(eventsConditionsStrs[1],
						sqlStr.toString(), page.getStartIndex(),
						page.getPageSize());

		// 对list的处理
		/*String eventName = "";
		
		for (Events events : attestationEventsList) {

			String eventNameTemp = events.getName();
			
			try {
				eventName = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventNameTemp));
				// String s = events.getsAdd();
				if (!eventName.equals(null)) {
					events.setName(eventName);
				}

			} catch (Exception e) {
				
				events.setName(eventNameTemp);
				
				//log.info("转化失败");
			}

			String eventstype = events.getType();
			
			try {
				String typeTemp = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventstype));

				if ((typeTemp != null) && (!typeTemp.equals(""))) {
					events.setType(typeTemp);
				}

			} catch (Exception e) {
				
				events.setType(eventstype);
				//log.info("类型转化失败");
			}
			try {
				
				events.setCustomd1(Integer.parseInt(events.getType()));
				
			} catch (Exception e) {
				//log.info("类型转换错误");
				
				//events.setCustomd1(Long.parseLong(eventstype));

			}
		}*/
		// 对查找的用户列表做分页处理
		SearchResult<Events> sr = new SearchResult<Events>();
		sr.setList(attestationEventsList);
		sr.setPage(page);
		return sr;
	}

	@Override
	public SearchResult queryUndefinedEvents(Map<String, Object> map, Page page) {
		// TODO Auto-generated method stub
		int rowsCount = summaryEventsDao.getUndefinedEventsCount(map);
		page.setTotalCount(page.getTotalCount() + rowsCount);
		List<Events> list = summaryEventsDao.queryUndfinedEvents(map,
				page.getStartIndex(), page.getPageSize());
		// 对list的处理
		/*String eventName = "";
		for (Events events : list) {
			String eventNameTemp = events.getName();
			try {
				eventName = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventNameTemp));
				// String s = events.getsAdd();
				events.setName(eventName);

			} catch (Exception e) {
				//log.info("转化失败");
			}

			String eventstype = events.getType();
			try {
				String typeTemp = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventstype));

				if ((typeTemp != null) && (!typeTemp.equals(""))) {
					events.setType(typeTemp);
				}

			} catch (Exception e) {
				//log.info("类型转化失败");
			}

		}*/
		// 对查找的用户列表做分页处理
		SearchResult<Events> sr = new SearchResult();
		List eventTemp = sr.getList();
		eventTemp.addAll(list);
		sr.setList(eventTemp);

		sr.setPage(page);

		return sr;
	}

	@Override
	public String queryEventsConditionsStr(long queryEventsGroupId) {

		return queryEvevntsDao.queryCustomEventsRules(queryEventsGroupId);
	}

	@Override
	public List<SummaryEvents> queryEventForExport(long queryEventsGroupId,
			Map map) {
		String eventsConditionsStr = queryEvevntsDao
				.querySummaryEventsConditions(queryEventsGroupId);

		String[] eventsConditionsStts = eventsConditionsStr.split(",");

		Map data = new HashMap();

		data.put("ids", map.get("ids"));
		
			 data.put("deviceIp", map.get("deviceIp"));
			 data.put("sourceAdd", map.get("sourceAdd"));
			 data.put("targetAdd", map.get("targetAdd"));
			 data.put("eventsType", map.get("eventsType"));
		String type = eventsConditionsStts[2].replaceAll("'", "").trim();

		String devproduct = eventsConditionsStts[3].replaceAll("'", "").trim();

		String program = eventsConditionsStts[4].replaceAll("'", "").trim();

		String devvendor = eventsConditionsStts[5].replaceAll("'", "").trim();

		String devtype = eventsConditionsStts[6].replaceAll("'", "").trim();

		// 关联分析时候用到的
		String devName = "";
		String programtype = "";
		String eventsCustomd1 = "";
		String rawid1 = "";
		String rawid2 = "";
		String rawid3 = "";
		String rawid4 = "";
		String rawid5 = "";
		String rawid6 = "";
		String rawid7 = "";
		String rawid8 = "";
		String rawid9 = "";
		String rawid10 = "";

		if (eventsConditionsStts.length > 7) {
			devName = eventsConditionsStts[7].replaceAll("'", "").trim();

			programtype = eventsConditionsStts[8].replaceAll("'", "").trim();

			eventsCustomd1 = eventsConditionsStts[9].replaceAll("'", "").trim();
		}
		if (eventsConditionsStts.length > 10) {
			rawid1 = eventsConditionsStts[10].replaceAll("'", "").trim()
					.replaceAll("\\|", ",");
			rawid2 = eventsConditionsStts[11].replaceAll("'", "").trim();
			rawid3 = eventsConditionsStts[12].replaceAll("'", "").trim();
			rawid4 = eventsConditionsStts[13].replaceAll("'", "").trim();
			rawid5 = eventsConditionsStts[14].replaceAll("'", "").trim();
			rawid6 = eventsConditionsStts[15].replaceAll("'", "").trim();
			rawid7 = eventsConditionsStts[16].replaceAll("'", "").trim();
			rawid8 = eventsConditionsStts[17].replaceAll("'", "").trim();
			// rawid9 = eventsConditionsStts[18].replaceAll("'", "").trim();
			// rawid10 = eventsConditionsStts[19].replaceAll("'", "").trim();

		}
		if (StringUtil.isNotEmpty(type)) {
			data.put("type", type);
		}
		if (StringUtil.isNotEmpty(devproduct)) {
			data.put("devproduct", devproduct);
		}
		if (StringUtil.isNotEmpty(program)) {
			data.put("program", program);
		}
		if (StringUtil.isNotEmpty(devvendor)) {
			data.put("devvendor", devvendor);
		}
		if (StringUtil.isNotEmpty(devtype)) {
			data.put("devtype", devtype);
		}
		if (StringUtil.isNotEmpty(devName)) {
			data.put("devName", devName);
		}
		if (StringUtil.isNotEmpty(programtype)) {
			data.put("programtype", programtype);
		}
		if (StringUtil.isNotEmpty(eventsCustomd1)) {
			data.put("customd1", eventsCustomd1);
		}
		if (StringUtil.isNotEmpty(rawid1)) {
			data.put("rawid1", rawid1);
		}
		if (StringUtil.isNotEmpty(rawid2)) {
			data.put("rawid2", rawid2);
		}
		if (StringUtil.isNotEmpty(rawid3)) {
			data.put("rawid3", rawid3);
		}
		if (StringUtil.isNotEmpty(rawid4)) {
			data.put("rawid4", rawid4);
		}
		if (StringUtil.isNotEmpty(rawid5)) {
			data.put("rawid5", rawid5);
		}
		if (StringUtil.isNotEmpty(rawid6)) {
			data.put("rawid6", rawid6);
		}
		if (StringUtil.isNotEmpty(rawid7)) {
			data.put("rawid7", rawid7);
		}
		if (StringUtil.isNotEmpty(rawid8)) {
			data.put("rawid8", rawid8);
		}
		if (StringUtil.isNotEmpty(rawid9)) {
			data.put("rawid9", rawid9);
		}
		if (StringUtil.isNotEmpty(rawid10)) {
			data.put("rawid10", rawid10);
		}
		data.put("assetId", map.get("assetId"));
		List<SummaryEvents> attestationEventsList = null;
		try {
			

			
			attestationEventsList = queryEvevntsDao
					.queryEventForExport(eventsConditionsStts[1]
							.replaceAll("'", "").trim(), data);
		} catch (Exception e) {
			attestationEventsList= new ArrayList<SummaryEvents>();
			e.printStackTrace();
		}
		
		// 对查找的列表做分页处理
		return attestationEventsList;
	}

	@Override
	public SearchResult<Events> queryRelAssetEvents(String condition, Page page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] eventsConditionsStrs = condition.split(",");
		String threatValue = eventsConditionsStrs[2];
		String assetId = eventsConditionsStrs[3];
		String sourceAdd = eventsConditionsStrs[4];
		String sourcePort = eventsConditionsStrs[5];
		String targetAdd = eventsConditionsStrs[6];
		String targetPort = eventsConditionsStrs[7];
		String eventsType = eventsConditionsStrs[8];
		String beginTime = eventsConditionsStrs[9];
		String endTime = eventsConditionsStrs[10];
		String protocol = eventsConditionsStrs[11];
		String timeRange = eventsConditionsStrs[12];
		String deviceIp = eventsConditionsStrs[13];

		String operateOrder = eventsConditionsStrs[15];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;

			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:
				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();

					cal1.add(Calendar.DATE, -i);

					// //System.out.println(sdf.format(cal1.getTime()));

					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal1.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal1.getTime()));
					}
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);

					String tableNameTemp = sdf.format(calendar.getTime());

					// System.out.println(tableNameTemp);

					int flag = eventsDao.existsTable("tbl_" + tableNameTemp);

					if (flag!=0) {
						if (endTime.equals(tableNameTemp)
								|| dayTime.equals(tableNameTemp)) {
							tableName.add("tbl_" + tableNameTemp);
							break;
						}
						tableName.add("tbl_" + tableNameTemp);
					}
					num++;
				}
			} catch (ParseException e) {
				//log.info("转化失败");
			}
		}

		// 拼接SQL语句
		StringBuffer sqlStrCount = new StringBuffer();
		StringBuffer sqlStr = new StringBuffer();
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				if (sqlStr.length() <= 0) {
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");
				} else {
					sqlStr.append("  UNION ALL   SELECT * FROM "
							+ tableName.get(i) + "  WHERE 1=1");
				}

				if (StringUtil.isNotEmpty(threatValue)) {

					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");
				}
				if (StringUtil.isNotEmpty(assetId)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" = CAST("
							+ assetId.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				// System.out.println(sourcePort);
				if ((!sourcePort.trim().equals("0"))
						&& (!sourcePort.equals("null")) && (sourcePort != null)
						&& (!sourcePort.equals(""))) {
					sqlStr.append(" AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				// System.out.println(targetPort);
				if ((!targetPort.trim().equals("0")) && (targetPort != null)
						&& (!targetPort.equals("null"))
						&& (!targetPort.equals(""))) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
				/*	String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}
					sqlStr.append(" AND \"LOG_CATEGORY\"  IN  ("
							+ sb.toString() + ")");*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN  ('" + protocol.replaceAll("\\|", ",")
							+ "')");
				}
				if (StringUtil.isNotEmpty(deviceIp)) {
					sqlStr.append(" AND \"LOG_DEVADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(deviceIp))
							+ "  AS bigint)");
				}
				if (StringUtil.isNotEmpty(operateOrder)) {
					sqlStr.append(" AND \"LOG_CUSTOMS4\"  LIKE  '%"
							+ operateOrder + "%'");
				}
			}
		}

		if (timeRange.equals("12")) {
			if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
			{
			sqlStrCount
					.append("SELECT COUNT(*) FROM (SELECT * FROM("
							+ sqlStr.toString()
							+ ") C order by \"LOG_SENDTIME\" desc limit 1000 offset 0) as temp");
			}else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
			{
				sqlStrCount
				.append("SELECT COUNT(*) FROM (SELECT TOP 1000 * FROM("
						+ sqlStr.toString()
						+ ") C order by \"LOG_SENDTIME\" desc)temp");
			}
		} else {
			sqlStrCount.append("SELECT COUNT(*) FROM (" + sqlStr.toString()
					+ ") C");
		}

		int rowsCount = summaryEventsDao.countByassetId(
				eventsConditionsStrs[0], sqlStrCount.toString());

		page.setTotalCount(rowsCount);

		StringBuffer sqlList = new StringBuffer();

		if (timeRange.equals("12")) {
           if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
           {
			sqlList.append("SELECT * FROM (SELECT * FROM("
					+ sqlStr.toString()
					+ ") C order by \"LOG_SENDTIME\" desc limit 1000 offset 0) as temp");
           }
           else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
           {
        	   sqlList.append("SELECT * FROM (SELECT TOP 1000 * FROM("
   					+ sqlStr.toString()
   					+ ") C order by \"LOG_SENDTIME\" desc )temp");
           }
		} else {
			sqlList.append("SELECT * FROM(" + sqlStr.toString()
					+ ") C order by \"LOG_SENDTIME\" desc");
		}

		List<Events> attestationEventsList = summaryEventsDao
				.queryEventsByassetIds(eventsConditionsStrs[1],
						sqlList.toString(), page.getStartIndex(),
						page.getPageSize());

		// 对list的处理
		String eventName = "";

		/*for (Events events : attestationEventsList) {
			String eventNameTemp = events.getName();// 查出来的 这里是数字

			String eventType = events.getType();
			// 修改---2013-10-31
			try {
				events.setCustomd1(Integer.parseInt(events.getType()));
			} catch (Exception e) {

			}

			try {
				eventName = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventNameTemp));
				String s = events.getsAdd();
				if (!eventName.equals(null)) {
					events.setName(eventName);
				}
			} catch (Exception e) {
				//log.info("类型转化错误");
			}

			try {
				String typeTemp = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventType));
				if ((typeTemp != null) && (!typeTemp.equals(""))) {
					events.setType(typeTemp);
				}
			} catch (Exception e) {
				//log.info("转化失败");
			}

		}*/

		// 对查找的用户列表做分页处理
		SearchResult<Events> sr = new SearchResult<Events>();

		sr.setList(attestationEventsList);

		sr.setPage(page);

		return sr;
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
	public QueryEventsGroup queryCustomByID(long queryEventsId) {
		return queryEvevntsDao.queryCustomByID(queryEventsId);
	}

	@Override
	public List<Events> queryExportEventsRules(long queryEventsGroupId,
			String id,String ids) {
		String eventsConditionsStr = queryEvevntsDao
				.queryCustomEventsRules(queryEventsGroupId);
		return this.queryAllExportEventsRules(eventsConditionsStr, id, ids);
	}

	@Override
	public List<Events> queryExportEventsByRules(String conditions, String id,
			String ids) {
		
		return this.queryAllExportEventsRules(conditions, id, ids);
	}
	public  List<Events> queryAllExportEventsRules( String eventsConditionsStr, String id,
			String ids){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String[] eventsConditionsStrs = eventsConditionsStr.split(",");
		String threatValue = eventsConditionsStrs[2];
		String sourceAdd = eventsConditionsStrs[3];
		String sourcePort = eventsConditionsStrs[4];
		String targetAdd = eventsConditionsStrs[5];
		String targetPort = eventsConditionsStrs[6];
		String eventsType = eventsConditionsStrs[7];
		String beginTime = eventsConditionsStrs[8];
		String endTime = eventsConditionsStrs[9];
		String protocol = eventsConditionsStrs[10];
		String timeRange = eventsConditionsStrs[11];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;
			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:

				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					tableName.add("tbl_" + sdf.format(cal.getTime()));
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					tableName.add("tbl_" + sdf.format(cal.getTime()));
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();
					cal1.add(Calendar.DATE, -i);
					tableName.add("tbl_" + sdf.format(cal1.getTime()));
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);
					String tableNameTemp = sdf.format(calendar.getTime());
					if (endTime.equals(tableNameTemp)
							|| dayTime.equals(tableNameTemp)) {
						tableName.add("tbl_" + tableNameTemp);
						break;
					}
					tableName.add("tbl_" + tableNameTemp);
					num++;
				}
			} catch (ParseException e) {
				//log.info("转化失败");
			}
		}

		// 拼接SQL语句
		StringBuffer sqlStrCount = new StringBuffer();
		StringBuffer sqlStr = new StringBuffer();
		// 这里开始去掉没有的表 里面写的太乱 不知道谁是谁啊
		List<String> tableList = new ArrayList<String>();
		for (String string : tableName) {
			if (existsTable(string) == 0) {
				tableList.add(string);
			}
		}
		// 去掉没有的表
		tableName.removeAll(tableList);
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				if (sqlStr.length() <= 0) {
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");
				} else {
					sqlStr.append("  UNION ALL   SELECT * FROM "
							+ tableName.get(i) + "  WHERE 1=1");
				}

				if (StringUtil.isNotEmpty(threatValue)) {
					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");

				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(sourcePort)) {
					sqlStr.append(" AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(targetPort)) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
					/*String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}
					sqlStr.append(" AND \"LOG_CATEGORY\"  IN  ("
							+ sb.toString() + ")");;*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN  ('" + protocol.replaceAll("\\|","','")
							+ "')");
				}
				if (StringUtil.isNotEmpty(id)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" in(" + id + ")");
				}
				if(StringUtil.isNotEmpty(ids)){
					sqlStr.append(" AND \"LOG_IDENTIFICATION\" IN("+ids+")");
				}
			}
		}
		
		
		List<Events> attestationEventsList = summaryEventsDao
				.queryCustomEventsRules(eventsConditionsStrs[1],
						sqlStr.toString());

		// 对list的处理
		/*String eventName = "";
		for (Events events : attestationEventsList) {
			String eventNameTemp = events.getName();
			try {
				eventName = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventNameTemp));
				// String s = events.getsAdd();
				if (!eventName.equals(null)) {
					events.setName(eventName);
				}
			} catch (Exception e) {
				//log.info("转化失败");
			}

			String eventstype = events.getType();
			try {
				String typeTemp = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventstype));

				if ((typeTemp != null) && (!typeTemp.equals(""))) {
					events.setType(typeTemp);
				}

			} catch (Exception e) {
				//log.info("类型转化失败");
			}

		}*/

		// 对查找的用户列表做分页处理
		return attestationEventsList;
	}

	@Override
	public List<Events> queryExportRelAssetEvents(String condition,String ids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] eventsConditionsStrs = condition.split(",");
		String threatValue = eventsConditionsStrs[2];
		String assetId = eventsConditionsStrs[3];
		String sourceAdd = eventsConditionsStrs[4];
		String sourcePort = eventsConditionsStrs[5];
		String targetAdd = eventsConditionsStrs[6];
		String targetPort = eventsConditionsStrs[7];
		String eventsType = eventsConditionsStrs[8];
		String beginTime = eventsConditionsStrs[9];
		String endTime = eventsConditionsStrs[10];
		String protocol = eventsConditionsStrs[11];
		String timeRange = eventsConditionsStrs[12];
		String deviceIp = eventsConditionsStrs[13];

		String operateOrder = eventsConditionsStrs[15];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;

			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:
				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();

					cal1.add(Calendar.DATE, -i);

					// //System.out.println(sdf.format(cal1.getTime()));

					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal1.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal1.getTime()));
					}
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);

					String tableNameTemp = sdf.format(calendar.getTime());

					// System.out.println(tableNameTemp);

					int flag = eventsDao.existsTable("tbl_" + tableNameTemp);

					if (flag!=0) {
						if (endTime.equals(tableNameTemp)
								|| dayTime.equals(tableNameTemp)) {
							tableName.add("tbl_" + tableNameTemp);
							break;
						}
						tableName.add("tbl_" + tableNameTemp);
					}
					num++;
				}
			} catch (ParseException e) {
				//log.info("转化失败");
			}
		}

		// 拼接SQL语句
		StringBuffer sqlStrCount = new StringBuffer();
		StringBuffer sqlStr = new StringBuffer();
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				if (sqlStr.length() <= 0) {
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");
				} else {
					sqlStr.append("  UNION ALL   SELECT * FROM "
							+ tableName.get(i) + "  WHERE 1=1");
				}

				if (StringUtil.isNotEmpty(threatValue)) {

					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");
				}
				if (StringUtil.isNotEmpty(assetId)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" = CAST("
							+ assetId.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				// System.out.println(sourcePort);
				if ((!sourcePort.trim().equals("0"))
						&& (!sourcePort.equals("null")) && (sourcePort != null)
						&& (!sourcePort.equals(""))) {
					sqlStr.append(" AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				// System.out.println(targetPort);
				if ((!targetPort.trim().equals("0")) && (targetPort != null)
						&& (!targetPort.equals("null"))
						&& (!targetPort.equals(""))) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
					/*String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}
					sqlStr.append(" AND \"LOG_CATEGORY\"  IN  ("
							+ sb.toString() + ")");*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN ('" + protocol.replaceAll("\\|", "','")
							+ "')");
				}
				if (StringUtil.isNotEmpty(deviceIp)) {
					sqlStr.append(" AND \"LOG_DEVADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(deviceIp))
							+ "  AS bigint)");
				}
				if (StringUtil.isNotEmpty(operateOrder)) {
					sqlStr.append(" AND \"LOG_CUSTOMS4\"  LIKE  '%"
							+ operateOrder + "%'");
				}
				if(StringUtil.isNotEmpty(ids)){
					sqlStr.append(" AND \"LOG_IDENTIFICATION\" IN("+ids+")");
				}
			}
		}
      
		//刘延旭修改未修改完全。
		if (timeRange.equals("12")) {
			if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
			{
			sqlStrCount
					.append("SELECT COUNT(*) FROM (SELECT * FROM("
							+ sqlStr.toString()
							+ ") C order by \"LOG_SENDTIME\" desc limit 1000 offset 0) as temp");
			}
			else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
 			{ 
				sqlStrCount
				.append("SELECT COUNT(*) FROM (SELECT TOP 1000 * FROM("
						+ sqlStr.toString()
						+ ") C order by \"LOG_SENDTIME\" desc)temp");
			}
		} else {
			sqlStrCount.append("SELECT COUNT(*) FROM (" + sqlStr.toString()
					+ ") C");
		}

		int rowsCount = summaryEventsDao.countByassetId(
				eventsConditionsStrs[0], sqlStrCount.toString());

		

		StringBuffer sqlList = new StringBuffer();

		if (timeRange.equals("12")) {
            if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
            {
			sqlList.append("SELECT * FROM (SELECT * FROM("
					+ sqlStr.toString()
					+ ") C order by \"LOG_SENDTIME\" desc limit 1000 offset 0) as temp");
            }else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
            {
            	sqlList.append("SELECT * FROM (SELECT TOP 1000 * FROM("
    					+ sqlStr.toString()
    					+ ") C order by \"LOG_SENDTIME\" desc)temp");
            }
            	
		} else {
			sqlList.append("SELECT * FROM(" + sqlStr.toString()
					+ ") C order by \"LOG_SENDTIME\" desc");
		}

		List<Events> attestationEventsList = summaryEventsDao.queryExportEventsByassetIds(eventsConditionsStrs[1], sqlList.toString());
				

		// 对list的处理
		String eventName = "";

		/*for (Events events : attestationEventsList) {
			String eventNameTemp = events.getName();// 查出来的 这里是数字

			String eventType = events.getType();
			// 修改---2013-10-31
			try {
				events.setCustomd1(Integer.parseInt(events.getType()));
			} catch (Exception e) {

			}

			try {
				eventName = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventNameTemp));
				String s = events.getsAdd();
				if (!eventName.equals(null)) {
					events.setName(eventName);
				}
			} catch (Exception e) {
				//log.info("类型转化错误");
			}

			try {
				String typeTemp = GlobalConfig.eventTypeTag.get(Long
						.parseLong(eventType));
				if ((typeTemp != null) && (!typeTemp.equals(""))) {
					events.setType(typeTemp);
				}
			} catch (Exception e) {
				//log.info("转化失败");
			}

		}*/

		
		return attestationEventsList;
	}

	@Override
	public int queryCountCustomEventsRules(String str, String id,String ids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] eventsConditionsStrs = str.split(",");
		String threatValue = eventsConditionsStrs[2];
		String sourceAdd = eventsConditionsStrs[3];
		String sourcePort = eventsConditionsStrs[4];
		String targetAdd = eventsConditionsStrs[5];
		String targetPort = eventsConditionsStrs[6];
		String eventsType = eventsConditionsStrs[7];
		String beginTime = eventsConditionsStrs[8];
		String endTime = eventsConditionsStrs[9];
		String protocol = eventsConditionsStrs[10];
		String timeRange = eventsConditionsStrs[11];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;
			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:
				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();

					cal1.add(Calendar.DATE, -i);

					// //System.out.println(sdf.format(cal1.getTime()));

					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal1.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal1.getTime()));
					}
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);

					String tableNameTemp = sdf.format(calendar.getTime());

					// System.out.println(tableNameTemp);

					int flag = eventsDao.existsTable("tbl_" + tableNameTemp);
					if (flag!=0) {
						if (endTime.equals(tableNameTemp)
								|| dayTime.equals(tableNameTemp)) {
							tableName.add("tbl_" + tableNameTemp);
							break;
						}
						tableName.add("tbl_" + tableNameTemp);
					}
					num++;
				}
			} catch (ParseException e) {
				//log.info("转化失败");
			}
		}

		// 拼接SQL语句
		StringBuffer sqlStrCount = new StringBuffer();
		StringBuffer sqlStr = new StringBuffer();
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				if (sqlStr.length() <= 0) {
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");
				} else {
					sqlStr.append("  UNION ALL   SELECT * FROM "
							+ tableName.get(i) + "  WHERE 1=1");
				}

				if (StringUtil.isNotEmpty(threatValue)) {

					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");
				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(sourcePort)) {
					sqlStr.append(" AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(targetPort)) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
					/*String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}
					sqlStr.append(" AND \"LOG_CATEGORY\"  IN  ("
							+ sb.toString() + ")");*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN  ('" + protocol.replaceAll("\\|", "','")
							+ "')");
				}
				if (StringUtil.isNotEmpty(id)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" in(" + id + ")");
				}
				if(StringUtil.isNotEmpty(ids)){
					sqlStr.append(" AND \"LOG_IDENTIFICATION\" IN("+ids+")");
				}
			}
		}
		sqlStrCount
				.append("SELECT COUNT(*) FROM (" + sqlStr.toString() + ") C");

		int rowsCount = summaryEventsDao.count(eventsConditionsStrs[0],
				sqlStrCount.toString());
			return rowsCount;
	}

	@Override
	public List<List<Events>> queryCustomEventsRules1(String str,
			String id, String ids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] eventsConditionsStrs = str.split(",");
		String threatValue = eventsConditionsStrs[2];
		String sourceAdd = eventsConditionsStrs[3];
		String sourcePort = eventsConditionsStrs[4];
		String targetAdd = eventsConditionsStrs[5];
		String targetPort = eventsConditionsStrs[6];
		String eventsType = eventsConditionsStrs[7];
		String beginTime = eventsConditionsStrs[8];
		String endTime = eventsConditionsStrs[9];
		String protocol = eventsConditionsStrs[10];
		String timeRange = eventsConditionsStrs[11];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;
			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:
				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();

					cal1.add(Calendar.DATE, -i);

					// //System.out.println(sdf.format(cal1.getTime()));

					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal1.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal1.getTime()));
					}
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);

					String tableNameTemp = sdf.format(calendar.getTime());

					// System.out.println(tableNameTemp);

					int flag = eventsDao.existsTable("tbl_" + tableNameTemp);
					if (flag!=0) {
						if (endTime.equals(tableNameTemp)
								|| dayTime.equals(tableNameTemp)) {
							tableName.add("tbl_" + tableNameTemp);
							break;
						}
						tableName.add("tbl_" + tableNameTemp);
					}
					num++;
				}
			} catch (ParseException e) {
				//log.info("转化失败");
			}
		}

		// 拼接SQL语句
		StringBuffer sqlStrCount = new StringBuffer();
		
		List<String> sqlList= new ArrayList<String>();
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				StringBuffer sqlStr = new StringBuffer();
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");

				if (StringUtil.isNotEmpty(threatValue)) {

					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");
				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(sourcePort)) {
					sqlStr.append(" AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				if (StringUtil.isNotEmpty(targetPort)) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
/*
					String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}
					sqlStr.append(" AND \"LOG_CATEGORY\"  IN  ("
							+ sb.toString() + ")");*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN  ('" + protocol.replaceAll("\\|", "','")
							+ "')");
				}
				if (StringUtil.isNotEmpty(id)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" in(" + id + ")");
				}
				if(StringUtil.isNotEmpty(ids)){
					sqlStr.append(" AND \"LOG_IDENTIFICATION\" IN("+ids+")");
				}
				sqlList.add(sqlStr.toString());
			}
		}
//		sqlStrCount
//				.append("SELECT COUNT(*) FROM (" + sqlStr.toString() + ") C");

//		int rowsCount = summaryEventsDao.count(eventsConditionsStrs[0],
//				sqlStrCount.toString());
//		page.setTotalCount(rowsCount);
		List<List<Events>> attestationEventsList1 = summaryEventsDao
				.queryCustomEventsRules111(eventsConditionsStrs[1],sqlList
						);

		
		
		// 对查找的用户列表做分页处理
//		SearchResult<Events> sr = new SearchResult<Events>();
//		sr.setList(attestationEventsList);
//		sr.setPage(page);
		return attestationEventsList1;
	}

	@Override
	public List<List<Events>> excelEventsByAsset(String str, String ids) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] eventsConditionsStrs = str.split(",");
		String threatValue = eventsConditionsStrs[2];
		String assetId = eventsConditionsStrs[3];
		String sourceAdd = eventsConditionsStrs[4];
		String sourcePort = eventsConditionsStrs[5];
		String targetAdd = eventsConditionsStrs[6];
		String targetPort = eventsConditionsStrs[7];
		String eventsType = eventsConditionsStrs[8];
		String beginTime = eventsConditionsStrs[9];
		String endTime = eventsConditionsStrs[10];
		String protocol = eventsConditionsStrs[11];
		String timeRange = eventsConditionsStrs[12];
		String deviceIp = eventsConditionsStrs[13];

		String operateOrder = eventsConditionsStrs[15];

		List<String> tableName = new ArrayList<String>();

		if (StringUtil.isEmpty(beginTime) && StringUtil.isEmpty(endTime)) {
			int timeRangeI = 12;

			if (StringUtil.isNotEmpty(timeRange)) {
				timeRangeI = Integer.valueOf(timeRange);
			}
			switch (timeRangeI) {
			case 12:
				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 30:
				for (int i = 0; i < 30; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			case 0:
				SimpleDateFormat dd = new SimpleDateFormat("dd");
				Calendar cal = Calendar.getInstance();
				int day = Integer.valueOf(dd.format(cal.getTime()));
				for (int i = 0; i < day; i++) {
					Calendar cal1 = Calendar.getInstance();

					cal1.add(Calendar.DATE, -i);

					// //System.out.println(sdf.format(cal1.getTime()));

					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal1.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal1.getTime()));
					}
				}
				break;
			}
		} else {
			Calendar calendarDay = Calendar.getInstance();
			String dayTime = sdf.format(calendarDay.getTime());
			Calendar calendar = new GregorianCalendar();
			calendar.clear();
			try {
				int num = 0;
				while (true) {
					calendar.setTime(sdf.parse(beginTime));
					calendar.add(calendar.DATE, +num);

					String tableNameTemp = sdf.format(calendar.getTime());

					// System.out.println(tableNameTemp);

					int flag = eventsDao.existsTable("tbl_" + tableNameTemp);

					if (flag!=0) {
						if (endTime.equals(tableNameTemp)
								|| dayTime.equals(tableNameTemp)) {
							tableName.add("tbl_" + tableNameTemp);
							break;
						}
						tableName.add("tbl_" + tableNameTemp);
					}
					num++;
				}
			} catch (ParseException e) {
				//log.info("转化失败");
			}
		}
		List<String> sqlList= new ArrayList<String>();
		
		if (tableName.size() > 0) {
			for (int i = 0; i < tableName.size(); i++) {
				StringBuffer sqlStr = new StringBuffer();
				if (sqlStr.length() <= 0) {
					sqlStr.append(" SELECT * FROM " + tableName.get(i)
							+ "  WHERE 1=1");
				}

				if (StringUtil.isNotEmpty(threatValue)) {

					sqlStr.append(" AND \"LOG_PRIORITY\" IN ("
							+ threatValue.replaceAll("\\|", ",") + ")");
				}
				if (StringUtil.isNotEmpty(assetId)) {
					sqlStr.append(" AND \"LOG_ASSET_ID\" = CAST("
							+ assetId.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(sourceAdd)) {
					sqlStr.append(" AND \"LOG_SADDR\"  = CAST("
							+ String.valueOf(StrIpTransitionLip(sourceAdd))
							+ "  AS bigint) ");
				}
				// System.out.println(sourcePort);
				if ((!sourcePort.trim().equals("0"))
						&& (!sourcePort.equals("null")) && (sourcePort != null)
						&& (!sourcePort.equals(""))) {
					sqlStr.append(" AND \"LOG_SPORT\" = CAST("
							+ sourcePort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(targetAdd)) {
					sqlStr.append(" AND \"LOG_DADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(targetAdd))
							+ "  AS bigint) ");
				}
				// System.out.println(targetPort);
				if ((!targetPort.trim().equals("0")) && (targetPort != null)
						&& (!targetPort.equals("null"))
						&& (!targetPort.equals(""))) {
					sqlStr.append(" AND \"LOG_DPORT\" = CAST("
							+ targetPort.trim() + " AS bigint)");
				}
				if (StringUtil.isNotEmpty(eventsType)) {
					/*String [] types = eventsType.replaceAll("\\|", ",").split(",");
					int falg = 0;
					StringBuffer sb = new StringBuffer();
					for (String string : types) {
						if(falg == 0){
							try{
							sb.append("'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
							}catch (Exception e) {
								sb.append("'").append(string).append("'");
							}
						}else{
							try{
								sb.append(",'").append(GlobalConfig.eventCategoryTag.get(string)!=null?GlobalConfig.eventCategoryTag.get(string):string).append("'");
								}catch (Exception e) {
									sb.append(",'").append(string).append("'");
								}
						}
					}
					sqlStr.append(" AND \"LOG_CATEGORY\"  IN  ("
							+ sb.toString() + ")");*/
					sqlStr.append(" AND \"LOG_CATEGORY\" IN ('"
							+ eventsType.replaceAll("\\|", "','") + "')");
				}
				if (StringUtil.isNotEmpty(protocol)) {
					sqlStr.append(" AND \"LOG_TYPE\" IN  ('" + protocol.replaceAll("\\|", "','")
							+ "')");
				}
				if (StringUtil.isNotEmpty(deviceIp)) {
					sqlStr.append(" AND \"LOG_DEVADDR\" = CAST("
							+ String.valueOf(StrIpTransitionLip(deviceIp))
							+ "  AS bigint)");
				}
				if (StringUtil.isNotEmpty(operateOrder)) {
					sqlStr.append(" AND \"LOG_CUSTOMS4\"  LIKE  '%"
							+ operateOrder + "%'");
				}
				if(StringUtil.isNotEmpty(ids)){
					sqlStr.append(" AND \"LOG_IDENTIFICATION\" IN("+ids+")");
				}
				sqlList.add(sqlStr.toString());
			}
		}
		List<List<Events>> attestationEventsList1 = summaryEventsDao
				.queryCustomEventsRules111(eventsConditionsStrs[1],sqlList
						);
		return attestationEventsList1;
	}

	public OriginalLogDao getOriginalLogDao() {
		return originalLogDao;
	}

	public void setOriginalLogDao(OriginalLogDao originalLogDao) {
		this.originalLogDao = originalLogDao;
	}

	@Override
	public List<OriginalEvents> exportOriginalEvents(Map map) {
		return originalLogDao.exportOriginalEvents(map);
	}
	
}
