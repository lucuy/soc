/*
 * 文 件 名:  ScreenAction.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2013-2-26
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.webapp.action.screen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.plot.dial.DialPointer.Pointer;

import net.sf.json.JSONArray;

import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.screen.ComprehensiveEmerService;
import com.soc.webapp.action.BaseAction;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2013-2-26]
 * @see  [相关类/方法]
 * @since  [产品/大屏展示/安全态势综合显示]
 */
public class ScreenAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
   
	private ComprehensiveEmerService comprehensiveEmergeManager;
	private AssetService assetManager;
	//设备事件jsonDate
	private JSONArray facilityJson;
	
	//安全事件jsonDate
	private JSONArray safetyJson;
	
	private String facilityObjResult;
	
	private String safetyObjResult;
	private String safetyStrResult;
	private String safetyNoResult;
	
	

	/**
     * <查询所有展现>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    
	public String query() {
		
		log.info("调用查询所有综合展现");
		
		/*Map map = new HashMap();
		
		//得到需要查询的数据库表名,已存放到Map集合中
		String tableName = getTableName();
		map.put("tableName", tableName);
		
		//查询设备事件Top 10
		Object facilityObj = comprehensiveEmergeManager.queryFacilityEvents(map);
		//查询安全事件Top 10
		Object safetyObj = comprehensiveEmergeManager.querySafetyEvents(map);
		
		//把查询出来的数据装换成json数据
		facilityJson = JSONArray.fromObject(facilityObj);
	    safetyJson = JSONArray.fromObject(safetyObj);*/
	    //queryfacility();
		//querysafety();
		
	    //log.info("设备事件分布json数据"+facilityJson);
	    
	   // log.info("安全事件统计json数据"+safetyJson);
	    
		return SUCCESS;
	}
	
	
	/**
     * <刷新查询设备事件统计Top 10>
     * <定数刷新访问接口>
     * @return
     * @see [类、类#方法、类#成员]
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String queryfacility(){
		
		log.info("定时刷新调用查询设备事件top 10 接口");
		
        Map map = new HashMap();
		
		//得到数据库表名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//得到数据库表名
		String tableName = "tbl_"+ sdf.format(new Date());
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		
		String assetId = null;
		
 		if(groupid==1){
 			
 		
 		  }else{
 				
 			   assetId= assetManager.queryIDSByUser(groupid);
 			
 			}
		map.put("assetId", assetId);
		
		map.put("tableName", tableName);
		
		Object facilityObj = comprehensiveEmergeManager.queryFacilityEvents(map);
		
		if (facilityObj != null && ! "".equals(facilityObj)) {
			//把查询出来的数据装换成json数据
			facilityJson = JSONArray.fromObject(facilityObj);
			facilityObjResult = facilityObj.toString();
			
		} else {
			
			facilityObjResult = "[]";
			facilityJson = JSONArray.fromObject(facilityObjResult);
		}
		
		/*try{
			
			System.out.println("index");
			
			getResponse().getWriter().write(facilityJson.toString());
			
		}catch(Exception e)
		{
			
		}*/
		
		
		log.info("设备事件统计json数据"+facilityObjResult);
		
		return SUCCESS;
		
	}
	
	/**
     * <刷新查询安全事件统计Top 10>
     * <定数刷新访问接口>
     * @return
     * @see [类、类#方法、类#成员]
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String querysafety(){
		
		log.info("定时刷新调用查询安全事件top 10 接口");
		
		Map map = new HashMap();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//得到数据库表名
		String tableName = "tbl_"+ sdf.format(new Date());
		
		
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetId = null;
 		if(groupid==1){
 			}else{
 				assetId= assetManager.queryIDSByUser(groupid);
 			
 			}
		map.put("assetId", assetId);
		map.put("tableName", tableName);
		Object safetyObj = comprehensiveEmergeManager.querySafetyEvents(map);
		
        if (safetyObj != null && !"".equals(safetyObj)) {
        	String [] assetsObjArray = safetyObj.toString().split("---");
        	safetyStrResult = assetsObjArray[0].toString();
    		
    		safetyNoResult = assetsObjArray[1].toString();
    		safetyObjResult = assetsObjArray[2].toString();
    		
    		
    		
		} else {
			
			safetyStrResult = "[]";
			safetyNoResult = "[]";
			safetyObjResult = "[{name:'',data:[]}]";
			//facilityJson = JSONArray.fromObject(obj);
			
			//safetyJson = JSONArray.fromObject(obj);
		}
        
        safetyJson = JSONArray.fromObject(safetyObjResult);
        /*try{
			getResponse().getWriter().write(safetyObjResult);
		}catch(Exception e)
		{
			
		}*/
        
         log.info("安全事件统计json数据"+safetyJson);
		
		return SUCCESS;
	}
	
	
	/**
     * <大屏查询设备事件统计Top 10>
     * <访问接口>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String querybigfacility () {
		
		return SUCCESS;
	}
	
	/**
     * <大屏查询安全事件统计Top 10>
     * <访问接口>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String querybigsafety() {
		
		return SUCCESS;
	}
	
	public String querybigrisk() {
		
		return SUCCESS;
	}
	
	public String querybigsonrisk() {
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @param time
	 * @return 两位的月、日
	 */
	private String switchTime(int time) {
		
		return time < 10 ? "0" + time : time + ""; 
	}
	

	/**
	 * 
	 * @param 
	 * @return 数据库表名
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	
	
	public ComprehensiveEmerService getComprehensiveEmergeManager() {
		return comprehensiveEmergeManager;
	}

	public void setComprehensiveEmergeManager(
			ComprehensiveEmerService comprehensiveEmergeManager) {
		this.comprehensiveEmergeManager = comprehensiveEmergeManager;
	}
	
	public JSONArray getFacilityJson() {
		return facilityJson;
	}

	public void setFacilityJson(JSONArray facilityJson) {
		this.facilityJson = facilityJson;
	}

	public JSONArray getSafetyJson() {
		return safetyJson;
	}
	public void setSafetyJson(JSONArray safetyJson) {
		this.safetyJson = safetyJson;
	}
	public AssetService getAssetManager() {
		return assetManager;
	}
	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	public String getFacilityObjResult() {
		return facilityObjResult;
	}
	public void setFacilityObjResult(String facilityObjResult) {
		this.facilityObjResult = facilityObjResult;
	}


	public String getSafetyObjResult() {
		return safetyObjResult;
	}


	public void setSafetyObjResult(String safetyObjResult) {
		this.safetyObjResult = safetyObjResult;
	}
	public String getSafetyStrResult() {
		return safetyStrResult;
	}
	public void setSafetyStrResult(String safetyStrResult) {
		this.safetyStrResult = safetyStrResult;
	}
	public String getSafetyNoResult() {
		return safetyNoResult;
	}
	public void setSafetyNoResult(String safetyNoResult) {
		this.safetyNoResult = safetyNoResult;
	}

	
}
