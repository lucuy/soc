package com.soc.service.screen.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.screen.BigScreenDao;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.screen.BigScreenService;


public class BigScreenServiceImpl  extends BaseServiceImpl implements BigScreenService{

    //注入BigScreenDao层
	private BigScreenDao bigScreenDao;
	private AssetGroupService assetGroupManager;
	//public static List<Map> list;

	@Override
	public Object queryEventNum(Map map) {
		// 事件统计 Top 5 逻辑处理

		int flag = 0;

		Object object = "";

		List<Map> eventList = new ArrayList<Map>();

		eventList = bigScreenDao.queryEventNum(map);

		StringBuffer jsonDateStr = new StringBuffer();
		StringBuffer jsonDateNo = new StringBuffer();
		StringBuffer jsonDate = new StringBuffer();
		String jsonStr = "";
		String jsonNo = "";
		jsonDateStr.append("[");
		jsonDateNo.append("[");
		jsonDate.append("[");

		if (eventList.size() > 0) {

			for (Map eventMap : eventList) {

				String eventName = "";
				String key = eventMap.get("key").toString();
				
				//Map<Long, String> eventMap1 = GlobalConfig.eventTypeTag;
				
				//if (eventMap1.containsKey(Long.parseLong(key))) {
					
					//eventName = eventMap1.get(Long.parseLong(key)).toString();
				//}
				eventName = key;
				try{
					
				if(GlobalConfig.eventCategoryTag.containsKey(key))
				{
					eventName = GlobalConfig.eventCategoryTag.get(key);
				}
				}catch(Exception e)
				{
					//log.info("类型转化失败");
				}
				
				if (key == null || "".equals(key)) {
					
					eventName = "其它";
				}
				
				long eventCount = Integer.parseInt(eventMap.get("value").toString());

				if (flag == 0) {
					jsonDate.append("{name:\'"+eventName+"\',data:["+eventCount+"]"+"}");
 					jsonDateStr.append("'" + eventName + "',");
					jsonDateNo.append(eventCount+",");
				} else {
					jsonDate.append(",{name:\'"+eventName+"\',data:["+eventCount+"]"+"}");
					jsonDateStr.append("'" + eventName + "',");
					jsonDateNo.append(eventCount+",");
				}
				flag++;
			}
			
			jsonStr = jsonDateStr.substring(0,jsonDateStr.length()-1);
			jsonNo = jsonDateNo.substring(0,jsonDateNo.length()-1);
			jsonStr += "]";
			jsonNo += "]";
			jsonDate.append("]");
			object = jsonStr+"---"+jsonNo+"---"+jsonDate;

		} else {

			object = "[]---[]---[]";

		}

		return object;
	}
	


	@Override
	public Object queryAssetsNum(long id) {
		// 资产分布
        int flag = 0;
	    
	    Object object = "";
	    
	    String[] str=assetGroupManager.getAllGroupIdByID(id).split(",");
	    
		StringBuffer jsonDate = new StringBuffer();
		StringBuffer jsonStr = new StringBuffer();
		StringBuffer jsonNo = new StringBuffer();
		StringBuffer json = new StringBuffer();
		int count=0;
		
		////System.out.println(str.length);
		if(str==null)
		{
			object = "[]---[]---[]---[]";
		}
		else
		{
		
		if(str.length>0){
			jsonDate.append("[");
			jsonStr.append("[");
			jsonNo.append("[");
			json.append("[");
			for (int i = 0; i < str.length; i++) {
				int groupid=Integer.parseInt(str[i]);
				Map map = new HashMap();
				if(groupid==1){
					
				}else{
					map.put("assetGroupId", assetGroupManager.getAllGroupIdByID(groupid));
				
				if (flag ==0) {
					json.append("['"+bigScreenDao.AssetGroupNameById(groupid)+"',"+bigScreenDao.countByAssetGroupId(map)+"]");
					jsonDate.append("{name:\'" + bigScreenDao.AssetGroupNameById(groupid) + "\',data:[" + bigScreenDao.countByAssetGroupId(map) + "]}");
					jsonStr.append("\'" + bigScreenDao.AssetGroupNameById(groupid) + "\',");
					jsonNo.append(bigScreenDao.countByAssetGroupId(map)+",");
					int a = bigScreenDao.countByAssetGroupId(map);
					if(a>0){
						count++;
					}
				} else {
					json.append(",['"+bigScreenDao.AssetGroupNameById(groupid)+"',"+bigScreenDao.countByAssetGroupId(map)+"]");
					jsonDate.append(",{name:\'" + bigScreenDao.AssetGroupNameById(groupid) + "\',data:[" + bigScreenDao.countByAssetGroupId(map) + "]}");
					jsonStr.append("\'" + bigScreenDao.AssetGroupNameById(groupid) + "\',");
					jsonNo.append(bigScreenDao.countByAssetGroupId(map) + ",");
					int b = bigScreenDao.countByAssetGroupId(map);
					if(b>0){
						count++;
					}
				}
				flag++;
				}
			}
			
			jsonDate.append("]");
			String  strStr = jsonStr.substring(0,jsonStr.length()-1);
			strStr += "]";
			String strNo = jsonNo.substring(0,jsonNo.length()-1);
			strNo += "]";
			json.append("]");
			object = jsonDate+"---"+strStr+"---"+strNo+"---"+json;
			if (count<=0) {
				object = "[]---[]---[]---[]";
			}
			
		}else{
			object = "[]---[]---[]---[]";
		}
		}
		
		//System.out.println(object+"=======================================");
		
		return object;

	}

	
	public BigScreenDao getBigScreenDao() {
		return bigScreenDao;
	}
	
	public void setBigScreenDao(BigScreenDao bigScreenDao) {
		this.bigScreenDao = bigScreenDao;
	}



	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}



	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}



	@Override
	public double countEvents(long percent) {
		
		Map map = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		String tableName = "tbl_"+sdf.format(new Date());
		
		map.put("tableName", tableName);
		
		long startime =new Date().getTime()-300000;
		
		long endtime=new Date().getTime();
		
		map.put("startime", startime);
		
		map.put("endtime", endtime);
		
		List<Map> eventList = new ArrayList<Map>();
		eventList=bigScreenDao.countEvents(map);
		
		double nums = 0;
		
		for(Map temp :eventList)
		{
			 String keytemp = temp.get("key").toString();
			 try{
				 
				 int tempkey = Integer.parseInt(keytemp);
				
				 int countTemp =Integer.valueOf(map.get(keytemp).toString());
				
				 nums += (countTemp*tempkey*Math.pow(percent, tempkey));
				 //如果nums>0，就进行格式化
				 if (nums>0) {
					 DecimalFormat df = new DecimalFormat(".0000");
					 nums = Double.parseDouble(df.format(nums));
				}
			 }catch(Exception e)
			 {
				  //log.info("等级转化失败");
			 }
		}
		
		return nums;
		
	}



	@Override
	public void queryCountEvents() {
		Map map = new HashMap();
		Map contMap = new HashMap();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		long key =new Date().getTime();
		String tableName = "tbl_"+sdf.format(new Date());
		map.put("tableName", tableName);
		long startime =new Date().getTime()-120000;
		long endtime=new Date().getTime();
		map.put("startime", startime);
		map.put("endtime", endtime);
		
		//计算收到事件的值，这里面需要传递一个参数来进行运算。
		
		List<Map> eventList = new ArrayList<Map>();
		eventList=bigScreenDao.countEvents(map);
		double assetNums=0;
		
		double nums = 0.0;
		
		for(Map temp :eventList)
		{
			
			 String keytemp = temp.get("key").toString();
			 
			 try{
				 
				 int tempkey = Integer.parseInt(keytemp);
				
				 int countTemp =Integer.valueOf(temp.get("value").toString());
				
				 double numtemp = (countTemp*tempkey*0.2*Math.pow(0.2, (5-tempkey)));
				 
				 nums +=numtemp;
				 
//				 String  temp1 = sdf1.format(key);
//				 
//		         String fileName = "d:/1.txt";
//		         
//		         String fileContent = "等级为"+tempkey+"的事件数量为:"+countTemp+"计算出来的值为:"+numtemp+"时间为:"+temp1+"\r\n";
//		         	 
//		         FileWriter writer = new FileWriter(fileName, true);
//		         
//		         writer.write(fileContent);
//		         
//		         writer.close();

				 //String  temp1 = sdf1.format(key);
				 
		        /* String fileName = "d:/1.txt";
>>>>>>> .r5582
		         
<<<<<<< .mine
=======
		         String fileContent = "等级为"+tempkey+"的事件数量为:"+countTemp+"计算出来的值为:"+numtemp+"时间为:"+temp1+"\r\n";
		         	 
		         FileWriter writer = new FileWriter(fileName, true);
		         
		         writer.write(fileContent);
		         
		         writer.close();*/
		         

				 
			 }catch(Exception e)
			 {
				  //log.info("等级转化失败");
			 }
		}
		
		//修改过
		List<Asset> assetList = bigScreenDao.queryAllAsset();
		for (Asset asset : assetList) {
			assetNums+=(asset.getAssetValue()*asset.getRiskThreatValue()*asset.getvAVulnerabilityValue());
		} 
        if (assetList.size()>0) {
        	nums =(nums+assetNums)/assetList.size();
		}else{
			nums=0.0;
		}
		
		/*String  temp1 = sdf1.format(key);
		
//		String fileName = "d:/1.txt";
//		
//	    String fileContent = "时间为:"+temp1+"计算值为:"+nums+"\r\n";
         
		 
<<<<<<< .mine
       // FileWriter writer;
//		try {
////			writer = new FileWriter(fileName, true);
////			
////			writer.write(fileContent);
////	         
////	         writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
=======
        FileWriter writer;
		try {
			writer = new FileWriter(fileName, true);
			
			writer.write(fileContent);
	         
	         writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        //如果nums>0，就进行格式化
		if (nums>0) {
			 DecimalFormat df = new DecimalFormat(".0000");
			 nums = Double.parseDouble(df.format(nums));
		}

		contMap.put("key", key);
		
		try{
			contMap.put("value", nums);
		}catch (Exception e) {
			//log.info("failed.......");
		}
		
		
		if(GlobalConfig.EVENTCOUNTMAP.size()==0||GlobalConfig.EVENTCOUNTMAP==null){
			//list = new ArrayList<Map>();
			
			GlobalConfig.EVENTCOUNTMAP.add(contMap);
			
		}else{
			
		if(GlobalConfig.EVENTCOUNTMAP.size()>11){
			
			GlobalConfig.EVENTCOUNTMAP.remove(0);
			GlobalConfig.EVENTCOUNTMAP.add(contMap);
			
		}else{
			GlobalConfig.EVENTCOUNTMAP.add(contMap);
		}
		}
	}



	@Override
	public List queryCountEventsList() {
		return GlobalConfig.EVENTCOUNTMAP;
	}
	
}
