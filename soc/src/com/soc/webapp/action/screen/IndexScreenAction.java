package com.soc.webapp.action.screen;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.screen.BigScreenService;
import com.soc.webapp.action.BaseAction;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  zhaokui
 * @version  [版本号, 2013-03-03]
 * @see  [相关类/方法]
 * @since  [产品/大屏展示/安全管理平台综合监控]
 */
public class IndexScreenAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private BigScreenService bigScreenServiceManager;
    private AssetService assetManager;
    private JSONArray eventNumJson;
    private JSONArray countNumJson;
    private AssetGroupService assetGroupManager;
    private JSONArray assetNumJson;
    private String assetsObjResult;
    private String assetsObjResult1;
    private String assetResultStr;
    private String assetResultNo;
    private String eventResultStr;
    private String eventResultNo;
    private String eventResult;
    private String countEvents;
	private String chartsX;

	/**
     * <查询所有首页展现数据>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String queryAll() {
		
		log.info("big screen show interface  queryAll");
		
		queryEventStatistics();
		queryAssets();
		quertCountEvents();
    	return SUCCESS;
    	
    }
    
	/**
     * <查询事件统计首页展现数据>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String  queryEventStatistics () {
		log.info("event interface method: queryEventStatistics");
		
		Map map = new HashMap();
		
		
		//得到数据库表名
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//得到数据库表名
		String tableName = "tbl_"+ sdf.format(new Date());
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetId=assetManager.queryIDSByUser(groupid);
		if(groupid==1){
			
		}else{
			
			map.put("assetId", assetId);
		}
		
		map.put("tableName", tableName);
		
		Object eventObj = bigScreenServiceManager.queryEventNum(map);
		
		String jsonDateStr[] = eventObj.toString().split("---");
		eventResultStr = jsonDateStr[0];
		eventResultNo = jsonDateStr[1];
		eventResult = jsonDateStr[2];
		
		eventNumJson = JSONArray.fromObject(eventObj);
		
		log.info("事件统计json数据" + eventNumJson);
		
		return SUCCESS;
	}
	/**
     * <全局风险数据>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String quertCountEvents(){
		//long count = bigScreenServiceManager.countEvents();
		
		StringBuffer sbf = new StringBuffer();
		StringBuffer sb = new StringBuffer();
	//	SimpleDateFormat sdf = new SimpleDateFormat("");
		int flag = 0;
		List<Map> list = bigScreenServiceManager.queryCountEventsList();
		SimpleDateFormat datesdf = new SimpleDateFormat("HH:mm");
		sb.append("[");
		sbf.append("[");
		for (Map map : list) {
			long temp =Long.parseLong(map.get("key").toString());
			if(flag==0){
			sb.append("'"+datesdf.format(temp)+"'");
			sbf.append(map.get("value"));
			}else{
				sb.append(",'"+datesdf.format(temp)+"'");
				sbf.append(","+map.get("value"));
			}
			flag++;
		}
		sb.append("]");
		sbf.append("]");
		countEvents =sbf.toString();
		chartsX = sb.toString();
		/*sbf.append("[");
		List<Map> list = bigScreenServiceManager.queryCountEventsList();
		for (Map map : list) {
			long temp =Long.valueOf(map.get("key").toString());
			SimpleDateFormat datesdf = new SimpleDateFormat("HH:mm");
			
			String dateTemp = datesdf.format(temp);
			
			if(flag==0){
				sbf.append("['"+dateTemp+"',"+map.get("value")+"]");
			}else{
				sbf.append(",['"+dateTemp+"',"+map.get("value")+"]");
			}
			flag++;
		}
		sbf.append("]");*/
		//Object countObj=sbf.toString();
		//countEvents =(String) countObj;
		//countNumJson = JSONArray.fromObject(countObj);
		return SUCCESS;
	}
	/**
     * <更新全局域风险数据>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public void upCountChart(){
		//JSONArray jsonUp = new JSONArray();
		
		/*double count = GlobalConfig.EVENTCOUNTMAP.get(GlobalConfig.EVENTCOUNTMAP.size()-1).get("value");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		String time =sdf.format(new Date());
		
		Object countObj="['"+time+"',0]";
		if(count>0){
			countObj="['"+time+"',"+count+"]";
		}
		 List<Object> objectList = new ArrayList<Object>();
		 objectList.add(countObj);
		 jsonUp = JSONArray.fromObject(objectList);*/
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		StringBuffer sbf = new StringBuffer();
		double count = GlobalConfig.EVENTCOUNTMAP.get(GlobalConfig.EVENTCOUNTMAP.size()-1).get("value");
		long temp =Long.parseLong(GlobalConfig.EVENTCOUNTMAP.get(GlobalConfig.EVENTCOUNTMAP.size()-1).get("key").toString());
		sbf.append("['").append(sdf.format(temp)).append("',").append(count).append("]");
		 try
         {
             getResponse().getWriter().write(sbf.toString());
         }
         catch (IOException e)
         {
             e.printStackTrace();
         }
	}
	/**
     * <查询资产分布首页展现数据>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String queryAssets() {
		log.info("assets interface method: queryEventStatistics");
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		
 		
		Object assetsObj = bigScreenServiceManager.queryAssetsNum(groupid);
		String [] assetsObjArray = assetsObj.toString().split("---");
		assetsObjResult = assetsObjArray[0].toString();
		
		assetResultStr = assetsObjArray[1].toString();
		assetResultNo = assetsObjArray[2].toString();
		assetsObjResult1 = assetsObjArray[3].toString();
		
		//System.out.println(assetsObjResult+"========="+assetResultStr+"========="+assetResultNo);
		
		assetNumJson = JSONArray.fromObject(assetsObj);
		
		log.info("事件统计json数据" + assetNumJson);
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @param 
	 * @return 数据库表名
	 *
	 */

	private String getTableName(){
		//构建查询的数据表
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
	    String month = switchTime(c.get(Calendar.MONTH)+1);
	    String day = switchTime(c.get(Calendar.DAY_OF_MONTH));
				
		//数据库表名
		String tableName =("tbl_" + year + month + day).trim();
	    
		return tableName;
	}
	
	/**
	 * @param time
	 * @return 两位的月、日
	 */
	private String switchTime(int time) {
		
		return time < 10 ? "0" + time : time + ""; 
	}

	
    public JSONArray getEventNumJson() {
		return eventNumJson;
	}

	public void setEventNumJson(JSONArray eventNumJson) {
		this.eventNumJson = eventNumJson;
	}
	public BigScreenService getBigScreenServiceManager() {
		return bigScreenServiceManager;
	}

	public void setBigScreenServiceManager(BigScreenService bigScreenServiceManager) {
		this.bigScreenServiceManager = bigScreenServiceManager;
	}

	public JSONArray getAssetNumJson() {
		return assetNumJson;
	}

	public void setAssetNumJson(JSONArray assetNumJson) {
		this.assetNumJson = assetNumJson;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	public String getAssetsObjResult() {
		return assetsObjResult;
	}

	public void setAssetsObjResult(String assetsObjResult) {
		this.assetsObjResult = assetsObjResult;
	}

	public String getAssetResultStr() {
		return assetResultStr;
	}

	public void setAssetResultStr(String assetResultStr) {
		this.assetResultStr = assetResultStr;
	}

	public String getAssetResultNo() {
		return assetResultNo;
	}

	public void setAssetResultNo(String assetResultNo) {
		this.assetResultNo = assetResultNo;
	}

	public String getEventResultStr() {
		return eventResultStr;
	}

	public void setEventResultStr(String eventResultStr) {
		this.eventResultStr = eventResultStr;
	}

	public String getEventResultNo() {
		return eventResultNo;
	}

	public void setEventResultNo(String eventResultNo) {
		this.eventResultNo = eventResultNo;
	}

	public String getEventResult() {
		return eventResult;
	}

	public void setEventResult(String eventResult) {
		this.eventResult = eventResult;
	}

	public String getAssetsObjResult1() {
		return assetsObjResult1;
	}

	public void setAssetsObjResult1(String assetsObjResult1) {
		this.assetsObjResult1 = assetsObjResult1;
	}

	public String getCountEvents() {
		return countEvents;
	}

	public void setCountEvents(String countEvents) {
		this.countEvents = countEvents;
	}

	public JSONArray getCountNumJson() {
		return countNumJson;
	}

	public void setCountNumJson(JSONArray countNumJson) {
		this.countNumJson = countNumJson;
	}

	public String getChartsX() {
		return chartsX;
	}

	public void setChartsX(String chartsX) {
		this.chartsX = chartsX;
	}
	
	
}
