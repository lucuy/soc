package com.soc.service.monitor.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.events.EventsDao;
import com.soc.dao.monitor.CustomTrendDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.monitor.CustomTrend;
import com.soc.model.monitor.TrendVO;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.CustomTrendService;
import com.util.ImageToBase64;
import com.util.IpConvert;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CustomTrendServiceImpl extends BaseServiceImpl implements CustomTrendService {
	private CustomTrendDao customTrendDao;
	 private static final String PICTURE_NAME_SEED = "arrow_03.gif"; // 表示页子节点的图片
	    
	    private static final String PICTURE_NAME_CLOSE = "u321_normal.gif"; // 表示关闭的图片
	    StringBuffer treeBuff;
	 // 原始事件
		private EventsDao eventsDao;
	    @Override
	public List<CustomTrend> queryAllCustomTrend(Map map) {
		
		return customTrendDao.queryAllCustomTrend(map);
	}

	@Override
	public Map queryEvents(String trendId,String assetId) {
		CustomTrend ct = customTrendDao.queryById(Long.parseLong(trendId));
		String [] ips =ct.getTrendIp().split(",");
		Map actionMap = new HashMap();
		 
		StringBuffer tableStr = new StringBuffer();
		StringBuffer sb = new StringBuffer();//拼接图表数据
		List<TrendVO> voList = new ArrayList<TrendVO>();
		List<String> sqlList=new ArrayList<String>();
		if(ct.getDays()==1){
			int timeCount=0;
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			 SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
				String startTime = sdf.format(new Date());
				String table = "tbl_"+startTime;
				Date start;
				try {
					int indexMap=0;
					start = new SimpleDateFormat("yyyyMMdd").parse(startTime);
					long starttime  = start.getTime();
					long nowTime = System.currentTimeMillis();
					timeCount = (int) ((nowTime-starttime)/3600000);
					sqlList = this.getDaySql(ct,table, starttime, timeCount+1, assetId);
					Map ipMap = this.getIpMap(ips,timeCount+1);
					for (String sql : sqlList) {
						List<Map> mapList = customTrendDao.queryEvents(sql);
						indexMap++;
						for (Map map : mapList) {
							String ip = IpConvert.IpString(Long.parseLong(map.get("key").toString()));
							if(ipMap.containsKey(ip)){
								long [] nums =(long[]) ipMap.get(ip);
								//int num = (Integer) map.get("value");--修复类型转换异常，李长红20140410
								long num = (Long) map.get("value");
								nums[indexMap-1]=num;
								ipMap.put(ip, nums);
							}
							
						}
					}
					int chartNum = 0;
					for (int j = 0; j < ips.length; j++) {
						StringBuffer nsf = new StringBuffer();
						
						if(chartNum==0){
							int nsfNum = 0;
							sb.append("{name:'").append(ips[j]).append("',data:[");
							long [] nums = (long[]) ipMap.get(ips[j]);
							for (int i = 0; i < nums.length; i++) {
								String time = sd.format((starttime+(3600000*i)));
								TrendVO vo = new TrendVO();
								vo.setTrendCount(nums[i]);
								vo.setTrendIp(ips[j]);
								vo.setTrendTime(time);
								voList.add(vo);
								if(nsfNum==0){
									nsf.append(nums[i]);
									tableStr.append("'"+time+"'");
								}else{
									nsf.append(",").append(nums[i]);
									tableStr.append(",").append("'"+time+"'");
								}
								nsfNum++;
							}
							sb.append(nsf.toString()).append("]}");
						}else{
							int nsfNum = 0;
							sb.append(",{name:'").append(ips[j]).append("',data:[");
							long [] nums = (long[]) ipMap.get(ips[j]);
							for (int i = 0; i < nums.length; i++) {
								String time = sd.format((starttime+(3600000*i)));
								TrendVO vo = new TrendVO();
								vo.setTrendCount(nums[i]);
								vo.setTrendIp(ips[j]);
								vo.setTrendTime(time);
								voList.add(vo);
								if(nsfNum==0){
									nsf.append(nums[i]);
								}else{
									nsf.append(",").append(nums[i]);
								}
								nsfNum++;
								
							}
							sb.append(nsf.toString()).append("]}");
						}
						chartNum++;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
					
		}else{
			//获取表名
			try {
		List<String> tableNameList = this.getTable(ct);
		//获取sql
		 sqlList = this.getSql(ct,tableNameList, assetId);
		int tableNum = 0;
		//拼接趋势图x轴需要的数据
		
		if(ct.getDays()>1){
			for (int i = tableNameList.size()-1; i >= 0; i--) {
				String table = tableNameList.get(i).substring(4, 12);
				if(tableNum==0){
					tableStr.append("'").append(table).append("'");
				}else{
					tableStr.append(",'").append(table).append("'");
				}
				tableNum++;
			}
		}else{
		for (String tableName : tableNameList) {
			String table = tableName.substring(4, 12);
			if(tableNum==0){
				tableStr.append("'").append(table).append("'");
			}else{
				tableStr.append(",'").append(table).append("'");
			}
			tableNum++;
		}
		}
		
		Map ipMap = this.getIpMap(ips,tableNameList.size());
		int indexMap=0;
		//查询数据
		if(ct.getDays()>1){
			for (int i=sqlList.size()-1;i>=0;i--) {
				List<Map> mapList = customTrendDao.queryEvents(sqlList.get(i));
				for (Map map : mapList) {
					String ip = IpConvert.IpString(Long.parseLong(map.get("key").toString()));
					if(ipMap.containsKey(ip)){
						long [] nums =(long[]) ipMap.get(ip);
						//int num = (Integer) map.get("value");--修复类型转换异常，李长红20140410
						long num = (Long) map.get("value");
						nums[indexMap]=num;
						ipMap.put(ip, nums);
					}
				}
				indexMap++;
			}
		}else{
		for (int i=0;i<sqlList.size();i++) {
			List<Map> mapList = customTrendDao.queryEvents(sqlList.get(i));
			for (Map map : mapList) {
				String ip = IpConvert.IpString(Long.parseLong(map.get("key").toString()));
				if(ipMap.containsKey(ip)){
					long [] nums =(long[]) ipMap.get(ip);
					//int num = Integer.parseInt(map.get("value").toString());--修复类型转换异常，李长红20140410
					long num = (Long) map.get("value");
					nums[indexMap]=num;
					ipMap.put(ip, nums);
				}
			}
			indexMap++;
		}
		}
		
		int chartNum = 0;
	
		for (String ip : ips) {
			StringBuffer nsf = new StringBuffer();
			
			if(chartNum==0){
				int nsfNum = 0;
				sb.append("{name:'").append(ip).append("',data:[");
				long [] nums = (long[]) ipMap.get(ip);
				for (int i = 0; i < nums.length; i++) {
					TrendVO vo = new TrendVO();
					vo.setTrendCount(nums[i]);
					vo.setTrendIp(ip);
					vo.setTrendTime(tableNameList.get(i).substring(4, 12));
					voList.add(vo);
					if(nsfNum==0){
						nsf.append(nums[i]);
					}else{
						nsf.append(",").append(nums[i]);
					}
					nsfNum++;
				}
				sb.append(nsf.toString()).append("]}");
			}else{
				int nsfNum = 0;
				sb.append(",{name:'").append(ip).append("',data:[");
				long [] nums = (long[]) ipMap.get(ip);
				for (int i = 0; i < nums.length; i++) {
					TrendVO vo = new TrendVO();
					vo.setTrendCount(nums[i]);
					vo.setTrendIp(ip);
					vo.setTrendTime(tableNameList.get(i).substring(4, 12));
					voList.add(vo);
					if(nsfNum==0){
						nsf.append(nums[i]);
					}else{
						nsf.append(",").append(nums[i]);
					}
					nsfNum++;
				}
				sb.append(nsf.toString()).append("]}");
			}
			chartNum++;
		}
	
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}
		StringBuffer actionString = new StringBuffer();//往action传数据
		actionString.append(tableStr.toString());
		actionString.append("_");
		actionString.append(sb.toString());
		actionMap.put("VO", voList);
		actionMap.put("actionString", actionString.toString());
		return actionMap;
	}

	@Override
	public int countAllCustomTrend(Map map) {
		
		return customTrendDao.countAllCustomTrend(map);
	}

	@Override
	public CustomTrend queryById(long id) {
		
		return customTrendDao.queryById(id);
	}

	@Override
	public void update(CustomTrend customTrend) {
		customTrendDao.update(customTrend);

	}

	@Override
	public void delete(long id) {
		customTrendDao.delete(id);

	}

	@Override
	public void insert(CustomTrend customTrend) {
		customTrendDao.insert(customTrend);

	}

	@Override
	public SearchResult query(Map map, Page page) {
		int rowsCount = customTrendDao.countAllCustomTrend(map);
		page.setTotalCount(rowsCount);
		
		List<CustomTrend> list = customTrendDao.queryCustomTrend(map, page.getStartIndex(), page.getPageSize());
		//对查到的list进行处理
		if(list.size()>0){
			for (CustomTrend ct : list) {
				List<String> ipList= new ArrayList<String>();
				String[] ips = ct.getTrendIp().split(",");
				for (String ip : ips) {
					ipList.add(ip);
				}
				ct.setIpList(ipList);
			}
		}
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	public CustomTrendDao getCustomTrendDao() {
		return customTrendDao;
	}

	public void setCustomTrendDao(CustomTrendDao customTrendDao) {
		this.customTrendDao = customTrendDao;
	}

	@Override
	public String createCustomTree(String objectpath) {
		treeBuff = new StringBuffer();
		Map map = new HashMap();
		map.put("parentId", Long.valueOf("0"));
		List<CustomTrend> ctList = customTrendDao.queryAllCustomTrend(map);
		for (CustomTrend ct : ctList) {
			Map map1 = new HashMap();
			map1.put("parentId", ct.getTrendId());
			List<CustomTrend> cList = customTrendDao.queryAllCustomTrend(map1);
			if(cList.size()<=0){
				 treeBuff.append("<ul>");
                 treeBuff.append("<li id=\"query_" + ct.getTrendId()
                     + "\"><a href=\"javascript:action1('" + ct.getTrendId()
                     + "','img');\"><img src=\"" + objectpath + "/images/" + PICTURE_NAME_SEED
                     + "\" id=\"img_query_" + ct.getTrendId()
                     + "\"></a>&nbsp;<a href=\"javascript:showGroup1('" + ct.getTrendId()
                     + "');\" ondblclick=\"action1('" + ct.getTrendId()
                     + "','img')\"><span class=\"eventext\">&nbsp;" + ct.getTrendName() + "</span></a>");
                 treeBuff.append("</li>");
                 treeBuff.append("</ul>");
			}else{
				 // 画出下级组
                treeBuff.append("<ul>");
                treeBuff.append("<li id=\"query_img_query_custom" + ct.getTrendId()
                    + "\"><a href=\"javascript:action1('" + ct.getTrendId()
                    + "','img_query_custom');\"><img src=\"" + objectpath + "/images/" + PICTURE_NAME_CLOSE
                    + "\" id=\"img_query_custom" + ct.getTrendId()
                    + "\"></a>&nbsp;<a href=\"javascript:showGroup1('" + ct.getTrendId()
                    + "');\" ondblclick=\"action1('" + ct.getTrendId()
                    + "','img_query_custom')\"><span class=\"eventext\">&nbsp;" + ct.getTrendName() + "</span></a>");
                drawSon(cList, objectpath);
                treeBuff.append("</li>");
                treeBuff.append("</ul>");
			}
		}
		return treeBuff.toString();
	}
	 public void drawSon(List<CustomTrend> customTrendList, String basePath){
		 
		 for (CustomTrend ct : customTrendList) {
			 treeBuff.append("<ul  class=\"disply\">");
	            treeBuff.append("<li class=\"eventleft\"><a href=\"javascript:linkTo1('" + ct.getTrendId()
	                + "');\"><img src=\"" + basePath + "/images/" + PICTURE_NAME_SEED
	                + "\"></a>&nbsp;<a href=\"javascript:linkTo1('" + ct.getTrendId()
	                + "');\"><span class=\"eventext\">&nbsp;" + ct.getTrendName() + "</span></a>");
	            treeBuff.append("</li>");
	            treeBuff.append("</ul>");
		}
	 }

	@Override
	public CustomTrend queryByName(String name) {
		
		return customTrendDao.queryByName(name);
	}
 private List<String> getSql(CustomTrend ct, List<String> tableName,String assetId){ 
	 
	 int addType = Integer.parseInt(ct.getTrendCustom1());//确定地址类型
	 String address=null;
	 switch (addType) {
	 case 1:
		 address = "LOG_SADDR";//源地址
		 break;
	 case 2:
		 address = "LOG_DADDR";//目标地址
		 break;
	 case 3:
		 address = "LOG_DEVADDR";//设备地址
		 break;
	 }
	 String [] ips = ct.getTrendIp().split(",");
	 int ipNum=0;
	 StringBuffer ipStr = new StringBuffer();
	 for (int i = 0; i < ips.length; i++) {
		if(ipNum==0){
			ipStr.append(IpConvert.iPTransition(ips[i]));
		}else{
			ipStr.append(","+IpConvert.iPTransition(ips[i]));
		}
		ipNum++;
	}
	 List<String> sqlList = new ArrayList<String>();
	 if(tableName.size()>0){
		 if(ct.getDays()>0){
			 if(GlobalConfig.sqlId.equals("pgAdmin"))
			 {
				 for (int i = tableName.size()-1; i >=0 ; i--) {	
					 StringBuffer sqlStr = new StringBuffer();
						 sqlStr.append("SELECT \"").append(address).append("\" AS key, count(\"").append(address).append("\") AS value");
						 sqlStr.append(" FROM "+tableName.get(i));
						 sqlStr.append(" where \"").append(address).append("\" in(").append(ipStr.toString()).append(")");
						 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(") ");
						 sqlStr.append("   GROUP BY \""+address+"\"");
						 sqlList.add(sqlStr.toString());
					 } 
			 }
			 else if(GlobalConfig.sqlId.equals("sqlServer")){
			 for (int i = tableName.size()-1; i >=0 ; i--) {	
				 StringBuffer sqlStr = new StringBuffer();
					 sqlStr.append("SELECT \"").append(address).append("\" AS 'key', count(\"").append(address).append("\") AS 'value'");
					 sqlStr.append(" FROM "+tableName.get(i));
					 sqlStr.append(" where \"").append(address).append("\" in(").append(ipStr.toString()).append(")");
					 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(") ");
					 sqlStr.append("   GROUP BY \""+address+"\"");
					 sqlList.add(sqlStr.toString());
				 }
			 }
		 }else{
		 for (int i = 0; i <tableName.size() ; i++) {
			
		 
		 StringBuffer sqlStr = new StringBuffer();
		     if(GlobalConfig.sqlId.equals("pgAdmin"))
		     {
			 sqlStr.append("SELECT \"").append(address).append("\" AS key, count(\"").append(address).append("\") AS value");
			 sqlStr.append(" FROM "+tableName.get(i));
			 sqlStr.append(" where \"").append(address).append("\" in(").append(ipStr.toString()).append(")");
			 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(") ");
			 sqlStr.append("   GROUP BY \""+address+"\"");
		     }else if(GlobalConfig.sqlId.equals("sqlServer"))
		     {
		    	 sqlStr.append("SELECT \"").append(address).append("\" AS 'key', count(\"").append(address).append("\") AS 'value'");
				 sqlStr.append(" FROM "+tableName.get(i));
				 sqlStr.append(" where \"").append(address).append("\" in(").append(ipStr.toString()).append(")");
				 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(") ");
				 sqlStr.append("   GROUP BY \""+address+"\"");
		     }
			 sqlList.add(sqlStr.toString());
		 }
		 }
	 }
	 return sqlList;
 }
private List<String> getTable(CustomTrend ct){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	List<String> tableName = new ArrayList<String>();
	 if (StringUtil.isEmpty(ct.getStartTime()) && StringUtil.isEmpty(ct.getEndTime())) {
			int timeRangeI = 1;
			if (ct.getDays()>0) {
				timeRangeI = ct.getDays();
			}
			switch (timeRangeI) {
			case 1:
				tableName.add("tbl_" + sdf.format(new Date()));
				break;

			case 7:
				for (int i = 0; i < 7; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i+1);
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
					cal.add(Calendar.DATE, -i+1);
					int flag = eventsDao.existsTable("tbl_"
							+ sdf.format(cal.getTime()));
					if (flag!=0) {
						tableName.add("tbl_" + sdf.format(cal.getTime()));
					}
				}
				break;

			
			}
		}else{
			
			tableName= this.getDate3(ct.getStartTime(), ct.getEndTime());
		}
	 return tableName;
	 
}
private Map getIpMap(String [] ips,int num){
	Map map  = new HashMap();
	for (int i = 0; i < ips.length; i++) {
		long [] nums = new long[num];
		map.put(ips[i], nums);
	}
	return map;
}
public EventsDao getEventsDao() {
	return eventsDao;
}

public void setEventsDao(EventsDao eventsDao) {
	this.eventsDao = eventsDao;
}
// public static void main(String[] args) {
//	Map map = new HashMap();
//	map.put("122", 123);
//	map.put("121", 123);
//	map.put("123", 123);
//	
//	Iterator it = map.keySet().iterator();
//	while(it.hasNext()){
//		String key = (String) it.next();
//		System.out.println(key);
//	}
//}
 private  List<String> getDate3(String startiem,String endtime) {
		List<String> dateList = new ArrayList<String>();
		
	   
		try {
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startiem);
			 Date	end = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
			 while(start.getTime() <= end.getTime()){
				 String tableName = "tbl_"+new SimpleDateFormat("yyyy-MM-dd").format(start).toString().replace("-", "");
				 if(eventsDao.existsTable(tableName)!=0){
					 dateList.add(tableName);
				 }
			        start = new Date(start.getTime() + 86400000);
			    }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   
		return dateList;
	}



@Override
public SearchResult queryAllEvents(long trendId,Page page,String assetId) {
	CustomTrend ct = customTrendDao.queryById(trendId);
	//获取表名
	List<String> tableNameList = this.getTable(ct);
	String sql = this.getEventsSql(ct, tableNameList,assetId);
	SearchResult<Events> sr = new SearchResult<Events>();
	int count = customTrendDao.countEvents("SELECT COUNT(*) FROM ("+sql+")C");
	page.setTotalCount(count);
	List<Events> attestationEventsList = customTrendDao.queryAllEvents(sql, page.getStartIndex(), page.getPageSize());
	// 对查找的用户列表做分页处理
			System.err.println(page.getCurrentPage());
			sr.setList(attestationEventsList);
			sr.setPage(page);
			return sr;
}

private String getEventsSql(CustomTrend ct,List<String> tableName,String assetId){
	StringBuffer sqlStr = new StringBuffer();
	int addType = Integer.parseInt(ct.getTrendCustom1());//确定地址类型
	 String address=null;
	 switch (addType) {
	 case 1:
		 address = "LOG_SADDR";//源地址
		 break;
	 case 2:
		 address = "LOG_DADDR";//目标地址
		 break;
	 case 3:
		 address = "LOG_DEVADDR";//设备地址
		 break;
	 }
	 String [] ips = ct.getTrendIp().split(",");
	 int ipNum=0;
	 StringBuffer ipStr = new StringBuffer();
	 for (int i = 0; i < ips.length; i++) {
		if(ipNum==0){
			ipStr.append(IpConvert.iPTransition(ips[i]));
		}else{
			ipStr.append(","+IpConvert.iPTransition(ips[i]));
		}
		ipNum++;
	}
	 if(tableName.size()>0){
		 if(ct.getDays()>0){
			 for (int i = 0; i < tableName.size(); i++) {
				 if (sqlStr.length() <= 0) {
						sqlStr.append(" SELECT * FROM " + tableName.get(i)
								+ "  WHERE 1=1");
					} else {
						sqlStr.append("  UNION ALL   SELECT * FROM "
								+ tableName.get(i) + "  WHERE 1=1");
					}
				 sqlStr.append(" AND ").append("\""+address+"").append("\" in(").append(ipStr.toString()).append(")");
				 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(")");
			} 
		 }else{
			 for (int i = tableName.size()-1; i >=0 ; i--) {
				 if (sqlStr.length() <= 0) {
						sqlStr.append(" SELECT * FROM " + tableName.get(i)
								+ "  WHERE 1=1");
					} else {
						sqlStr.append("  UNION ALL   SELECT * FROM "
								+ tableName.get(i) + "  WHERE 1=1");
					}
				 sqlStr.append(" AND ").append("\""+address+"").append("\" in(").append(ipStr.toString()).append(")");
				 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(")");
			}
		 }
		 
	 }
	
	return sqlStr.toString();
}

@Override
public Map<String, Object> getFreemakerMapExport(long auditReportId,
		String path, String reprotType) {
	Map<String, Object> map = new HashMap<String, Object>();
	switch ((int) auditReportId) {// 根据报表id把相应的图放在word中
	case 15:
		String base64Report15;
		if (reprotType.equals("pdf")) {// pdf格式需要相对路径的图片 doc需要base64类型的图片
										// html
										// 需要"data:image/jpeg;base64,"+base64类型的图片
										// 所以这里分开写
			base64Report15 = "diag" + auditReportId + "1.jpg";
		} else if (reprotType.equals("doc")) {
			base64Report15 = ImageToBase64.imgToBase64(path + "/diag" + auditReportId
					+ "1.jpg");
		} else {
			base64Report15 = "data:image/jpeg;base64,"
					+ ImageToBase64.imgToBase64(path + "/diag"
							+ auditReportId + "1.jpg");
		}
		map.put("image", base64Report15);// 把base64图片加进去;
		break;
	
	}

	return map;
}

private List<String> getDaySql(CustomTrend ct,String tableName,long times,int count,String assetId){
	List<String> sqlList = new ArrayList<String>();
	
	int addType = Integer.parseInt(ct.getTrendCustom1());//确定地址类型
	 String address=null;
	 switch (addType) {
	 case 1:
		 address = "LOG_SADDR";//源地址
		 break;
	 case 2:
		 address = "LOG_DADDR";//目标地址
		 break;
	 case 3:
		 address = "LOG_DEVADDR";//设备地址
		 break;
	 }
	 String [] ips = ct.getTrendIp().split(",");
	 int ipNum=0;
	 StringBuffer ipStr = new StringBuffer();
	 for (int i = 0; i < ips.length; i++) {
		if(ipNum==0){
			ipStr.append(IpConvert.iPTransition(ips[i]));
		}else{
			ipStr.append(","+IpConvert.iPTransition(ips[i]));
		}
		ipNum++;
	}
	long time = times;
	if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
	{
	for (int i = 1; i <=count; i++) {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT \"").append(address).append("\" AS key, count(\"").append(address).append("\") AS value");
		 sqlStr.append(" FROM "+tableName);
		 sqlStr.append(" where \"").append(address).append("\" in(").append(ipStr.toString()).append(")");
		 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(") ");
		 sqlStr.append(" AND \"LOG_RECEPT_TIME\" between ").append(time).append(" AND ").append(time+3600000);
		 sqlStr.append("   GROUP BY \""+address+"\"");
		 sqlList.add(sqlStr.toString());
		 time +=3600000;
	}
	}
	else if(GlobalConfig.sqlId.equals("sqlServer"))
	{
		for (int i = 1; i <=count; i++) {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("SELECT \"").append(address).append("\" AS 'key', count(\"").append(address).append("\") AS 'value'");
		 sqlStr.append(" FROM "+tableName);
		 sqlStr.append(" where \"").append(address).append("\" in(").append(ipStr.toString()).append(")");
		 sqlStr.append(" AND \"LOG_ASSET_ID\" in (").append(assetId).append(") ");
		 sqlStr.append(" AND \"LOG_RECEPT_TIME\" between ").append(time).append(" AND ").append(time+3600000);
		 sqlStr.append("   GROUP BY "+address+"");
		 sqlList.add(sqlStr.toString());
		 time +=3600000;
		}
	}
	
	return sqlList;
}
/*public static void main(String[] args) throws Exception {
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String startTime = sdf.format(new Date());
	Date start = new SimpleDateFormat("yyyyMMdd").parse(startTime);
		long starttime  = start.getTime();
		long s = System.currentTimeMillis();
		System.out.println(starttime+"\t"+s);
		long i = s-starttime;
		long c = i/3600000;
		System.out.println(i+"\t"+c);
}*/

@Override
public String queryLastWeekcountEvents(String assetId) {
	List<String> lastWeekSql = getLastWeekEventsCountsSql(assetId);
	StringBuilder sbEventsCount = new StringBuilder();
	String [] week ={"'星期一'","'星期二'","'星期三'","'星期四'","'星期五'","'星期六'","'星期日'"};
	for (int i = 0; i < lastWeekSql.size(); i++) {
			if(sbEventsCount.length()>0){
				sbEventsCount.append(",");
			}
			sbEventsCount.append("[").append(week[i]).append(","+customTrendDao.countEvents(lastWeekSql.get(i))).append("]");
	}
	return "'星期一','星期二','星期三','星期四','星期五','星期六','星期日'"+"_"+sbEventsCount.toString();
}
/**
 * <获取上周事件sql语句>
 * <根据用户关联的资产，去查询上周的每天事件数量的sql>
 * @param assetId
 * @returnList<String>
 */
public  List<String> getLastWeekEventsCountsSql(String assetId){
	List<String> lastWeekSql = new ArrayList<String>();
	List<String> lastWeekEventTable = getLastWeekEventsTable();
	for (String tableName : lastWeekEventTable) {
		lastWeekSql.add(" SELECT COUNT(\"LOG_ID\") FROM "+tableName+" WHERE \"LOG_ASSET_ID\" in ("+assetId+") "); 
	}
	return lastWeekSql;
}
/**
 * <获取上周事件表名称>
 * @return List<String>
 */
public  List<String> getLastWeekEventsTable(){
	List<String> lastWeek = new ArrayList<String>();
	 SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
     Calendar cal = Calendar.getInstance();
     cal.add(Calendar.WEEK_OF_MONTH, -1);
     for (int i = 0; i < 7; i++) {
         cal.add(Calendar.DATE, -1 * cal.get(Calendar.DAY_OF_WEEK) + 2 + i);
         lastWeek.add("tbl_"+sf.format(cal.getTime()));
     }
     return lastWeek;
}
/*public static void main(String[] args) {
	for (String str : getLastWeekEventsCountsSql("1,2")) {
		System.out.println(str);
	}
}*/
}
