package com.soc.service.risk.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.filters.StringInputStream;

import com.soc.dao.risk.AssetRiskGroupDao;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.risk.VulnerabilityAssessment;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.risk.AssetRiskGroupService;
import com.util.ImageToBase64;
import com.util.IpConvert;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 
 * @author yinhaiping
 *
 */
public class AssetRiskGroupServiceImpl  extends BaseServiceImpl implements AssetRiskGroupService{
	
	private AssetRiskGroupDao assetRiskGroupDao;
	private AssetGroup assetgroup;
	private AssetGroupService assetGroupManager;
	@SuppressWarnings("rawtypes")
	@Override
	public SearchResult queryPage(Map map, Page page) {
		List<AssetGroup> list = assetRiskGroupDao.queryPage(map, page.getStartIndex(), page.getPageSize());
		for(AssetGroup groupName:list){
			Map map2 = new HashMap();
			if(groupName.getAssetGroupId()==1){
				
			}else{
				String assetGroupId =assetGroupManager.getAllGroupIdByID(groupName.getAssetGroupId());
				map2.put("assetGroupId",assetGroupId);	
			}
			
			String riskThreatValue = assetRiskGroupDao.queryByName(map2);
			String vAVulnerabilityValue = assetRiskGroupDao.queryByName1(map2);
			String assetValue = assetRiskGroupDao.queryByName2(map2);
			
			if(StringUtil.isNotBlank(riskThreatValue)){
				groupName.setRiskThreatValue(this.getStringtoNum(riskThreatValue));
			}else{
				groupName.setRiskThreatValue(0);
			}
			if(StringUtil.isNotBlank(vAVulnerabilityValue)){
				groupName.setvAVulnerabilityValue(this.getStringtoNum(vAVulnerabilityValue));
			}else{
				groupName.setvAVulnerabilityValue(0);
			}
			if(StringUtil.isNotBlank(assetValue)){
				groupName.setAssetValue(this.getStringtoNum(assetValue));
			}else{
				groupName.setAssetValue(0);
			}
			
			groupName.setAssetGroupId(groupName.getAssetGroupId());
			assetRiskGroupDao.updateGrouprisk(groupName);
		}
		
		List<AssetGroup> list1 = assetRiskGroupDao.queryPage(map, page.getStartIndex(), page.getPageSize());
		int rowsCount = assetRiskGroupDao.assetRiskGroupPagePage(map);
        page.setTotalCount(rowsCount);
        
        SearchResult sr = new SearchResult();
        sr.setList(list1);
        sr.setPage(page);
        
        return sr;
	}
	/**
	 * 查子
	 */
	@Override
	public SearchResult queryAssetPage(Map map, Page page) {
		 // 按Map中存储的条件查找用户列表
        String ips = String.valueOf(map.get("selAssetIps"));
		int rowsCount ; 
		 List<Asset> list ;
		 if (ips != "null"){
			 Map<String, Long> mapIps = new HashMap<String, Long>();
	            long selAssetIp = IpConvert.iPTransition(ips);
	            mapIps.put("selAssetIp", selAssetIp);
	            rowsCount=assetRiskGroupDao.assetRiskGroupPagePage1(mapIps);
	         list=   assetRiskGroupDao.queryPage1(mapIps, page.getStartIndex(), page.getPageSize());
		 }else{
			 rowsCount= assetRiskGroupDao.assetRiskGroupPagePage1(map);
			list= assetRiskGroupDao.queryPage1(map, page.getStartIndex(), page.getPageSize());
		 }
		
        page.setTotalCount(rowsCount);
        
        
        for (Asset asset : list)
        {
        	double riskvalue=assetRiskGroupDao.avgRiskValue(asset.getAssetId());
        	double vaValue=assetRiskGroupDao.avgVAValue(IpConvert.IpString(asset.getAssetIp()));
        	double assetValue=assetRiskGroupDao.AssetValue(asset.getAssetId());
        	asset.setRiskThreatValue(this.getNum(riskvalue));
        	asset.setvAVulnerabilityValue(this.getNum(vaValue));
        	asset.setAssetValue(this.getNum(assetValue));
        	asset.setAssetId(asset.getAssetId());
        	assetRiskGroupDao.updateAssetValue(asset);
            asset.setAssetIps(IpConvert.IpString(asset.getAssetIp()));
        }
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
	}
	
	/**
	 * Get and Set
	 * @return
	 */
	public AssetRiskGroupDao getAssetRiskGroupDao() {
		return assetRiskGroupDao;
	}

	public void setAssetRiskGroupDao(AssetRiskGroupDao assetRiskGroupDao) {
		this.assetRiskGroupDao = assetRiskGroupDao;
	}

	public AssetGroup getAssetgroup() {
		return assetgroup;
	}

	public void setAssetgroup(AssetGroup assetgroup) {
		this.assetgroup = assetgroup;
	}
	
	
	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}
	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}
	@Override
	public List<Map> export(Map map) {
		 List<Map> maplist = new ArrayList<Map>();
		 try {
			 maplist =assetRiskGroupDao.export(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for (Map map2 : maplist) {
//			Map map3 = new HashMap();
//			if(Integer.parseInt(map2.get("ASSET_GROUP_ID").toString())==1){
//				
//			}else{
//				String assetGroupId =assetGroupManager.getAllGroupIdByID(Integer.parseInt(map2.get("ASSET_GROUP_ID").toString()));
//				
//				map3.put("assetGroupId",assetGroupId);	
//			}
//			
//			String riskThreatValue = assetRiskGroupDao.queryByName(map3);
//			String vAVulnerabilityValue = assetRiskGroupDao.queryByName1(map3);
//			String assetValue = assetRiskGroupDao.queryByName2(map3);
//			
//			if(StringUtil.isNotBlank(riskThreatValue)){
//				map2.put("RISK_THREATVALUE", this.getStringtoNum(riskThreatValue));
//			}else{
//				map2.put("RISK_THREATVALUE", 0);
//			}
//			if(StringUtil.isNotBlank(vAVulnerabilityValue)){
//				map2.put("VAVULNERABILITYVALUE", this.getStringtoNum(vAVulnerabilityValue));
//			}else{
//				map2.put("VAVULNERABILITYVALUE", 0);
//			}
//			if(StringUtil.isNotBlank(assetValue)){
//				map2.put("ASSET_ASSET_VALUE", this.getStringtoNum(assetValue));
//			}else{
//				map2.put("ASSET_ASSET_VALUE", 0);
//			}
//		}
		
		return maplist;
	}
	
	@Override
	public Object queryAssetsNum(Map map) {
		
	   
	    List<Map> AssetsList = new ArrayList<Map>();
	    String assetGroupId= map.get("assetGroupId").toString();
		long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
	    AssetsList=assetRiskGroupDao.queryAssetsNum(map);
	    
	   
		return this.resultOBJ(AssetsList);
	}
	
	@Override
	public Map queryAssetsNum1(Map map) {
		
		  List<Map> AssetsList = new ArrayList<Map>();
		  String assetGroupId= map.get("assetGroupId").toString();
			long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
		    AssetsList=assetRiskGroupDao.queryAssetsNum(map);
		  
		return this.result(AssetsList);
	}
	
	@Override
	public List<Map> queryAssets(Map map) {
		 String assetGroupId= map.get("assetGroupId").toString();
			long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
		List<Map> list=assetRiskGroupDao.queryAssetsNum(map);
		List<Map> mapList = new ArrayList<Map>();
		for (Map map2 : list) {
			map2.put("value", this.getStringtoNum(map2.get("value").toString()));
			mapList.add(map2);
		}
		return mapList;
	}
	
	@Override
	public Object queryRiskGroupNum(Map map) {
		    List<Map> AssetsList = new ArrayList<Map>();
		    String assetGroupId= map.get("assetGroupId").toString();
			long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
		    AssetsList=assetRiskGroupDao.queryRiskAssetGroup(map);
			return this.resultOBJ(AssetsList);
	}
	@Override
	public Map queryRiskGroupNum1(Map map) {
		  List<Map> AssetsList = new ArrayList<Map>();
		  String assetGroupId= map.get("assetGroupId").toString();
			long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
		    AssetsList=assetRiskGroupDao.queryRiskAssetGroup(map);
		   
		return this.result(AssetsList);
	}
	
	@Override
	public List<Map> queryRiskGroup(Map map) {
		 String assetGroupId= map.get("assetGroupId").toString();
			long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
		List<Map> list=assetRiskGroupDao.queryRiskAssetGroup(map);
		List<Map> mapList = new ArrayList<Map>();
		for (Map map2 : list) {
			map2.put("value", this.getStringtoNum(map2.get("value").toString()));
			mapList.add(map2);
		}
		return mapList;
	}
	/**
     * 拼接页面显示柱状图所需要的字符串
     * <功能详细描述>
     * @param list List<Map>
     * @return Map map
     */
	private Map result(List<Map> list){
		 int flag = 0;
		Map resultMap = new HashMap();
		 StringBuffer returnValue = new StringBuffer();
		    StringBuffer returnKey=new StringBuffer();
		 
		    if(list.size()>0){
		    	   returnValue.append("[");
				    returnKey.append("[");
		    	for (Map AssetsMap : list) {
				    String acceatName = AssetsMap.get("key").toString();
				    if(acceatName.equals("")||acceatName==null)
				    {
				    	acceatName = "其它";
				    }
					double acceatCount = Double.parseDouble(AssetsMap.get("value").toString());
					if (flag ==0){
						returnKey.append("'"+acceatName+"'");
						returnValue.append("{name:'"+acceatName+"',data:["+acceatCount+"]}");
					}else{
						returnKey.append(",'"+acceatName+"'");
						returnValue.append(",{name:'"+acceatName+"',data:["+acceatCount+"]}");
					}
					flag++;
				}
		    	returnKey.append("]");
		    	returnValue.append("]");
		    	
		    }else{
		    	returnKey.append("['没有数据']");
		    	returnValue.append("[{name:'没有数据',data:[0]}]");
		    }
		   
		    resultMap.put("returnKey", returnKey.toString());
		    resultMap.put("returnValue", returnValue.toString());
		
		return resultMap;
	}
	/**
     * 拼接页面显示饼状图所需要的字符串
     * <功能详细描述>
     * @param list List<Map>
     * @return Object
     */
	private Object resultOBJ( List<Map> list){
		 int flag = 0;
	        StringBuffer jsonDate = new StringBuffer();
		    Object object = "";
		   
		   
		    if (list.size() > 0) {
		    	 jsonDate.append("[");
				for (Map AssetsMap : list) {
				   
			    String acceatName = AssetsMap.get("key").toString();
			    if(acceatName.equals("")||acceatName==null)
			    {
			    	acceatName = "其它";
			    }
				double acceatCount = Double.parseDouble(AssetsMap.get("value").toString());
			      if(acceatCount==0.0){
			    	  
			      }else{
			    	  if (flag ==0) {
							
							jsonDate.append("[\'" + acceatName + "\'," + acceatCount + "]");
						} else {
							
						    
							jsonDate.append(",[\'" + acceatName + "\'," + acceatCount + "]");
						}
						    flag ++;
						}		
						
			      }
				jsonDate.append("]");
				
					object = "" + jsonDate + "";
			
				
			} else {
				
				object = "" + "[['未搜索到数据','0']]" + "";
			}
		    return object;
		    
	}
	
	@Override
	public Object queryRiskAssetNum(Map map) {
		 List<Map> AssetsList = new ArrayList<Map>();
		 String assetGroupId= map.get("assetGroupId").toString();
		long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
		    AssetsList=assetRiskGroupDao.queryRiskAssets(map);
		return this.resultOBJ(AssetsList);
	}
	@Override
	public Map queryRiskAssetNum1(Map map) {
		List<Map> AssetsList = new ArrayList<Map>();
		 String assetGroupId= map.get("assetGroupId").toString();
			long	assetGroupIds = Integer.parseInt(assetGroupId);
		if(assetGroupIds==1){
			map.remove("assetGroupId");
		}else{
			String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
			map.put("assetGroupId", assetGroupIdss);
		}
	    AssetsList=assetRiskGroupDao.queryRiskAssets(map);
		return this.result(AssetsList);
	}

	
	@Override
	public Map<String, Object> getFreemakerMapExport(long auditReportId,
			String path, String reprotType) {
		// 如果是pdf,图片需要相对路径,如果不是需要base64类型的图片 在这里要判断
				Map<String, Object> map = new HashMap<String, Object>();
				switch ((int) auditReportId) {// 根据报表id把相应的图放在word中
				case 14:
					String base64Report14;
					if (reprotType.equals("pdf")) {// pdf格式需要相对路径的图片 doc需要base64类型的图片
													// html
													// 需要"data:image/jpeg;base64,"+base64类型的图片
													// 所以这里分开写
						base64Report14 = "diag" + auditReportId + "1.jpg";
					} else if (reprotType.equals("doc")) {
						base64Report14 = ImageToBase64.imgToBase64(path + "/diag" + auditReportId
								+ "1.jpg");
					} else {
						base64Report14 = "data:image/jpeg;base64,"
								+ ImageToBase64.imgToBase64(path + "/diag"
										+ auditReportId + "1.jpg");
					}
					map.put("image", base64Report14);// 把base64图片加进去;
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
					String base64Report12;
					if (reprotType.equals("pdf")) {// pdf
						base64Report12 = "diag" + auditReportId + "1.jpg";
					} else if (reprotType.equals("html")) {
						base64Report12 = "data:image/jpeg;base64,"
								+ ImageToBase64.imgToBase64(path + "/diag"
										+ auditReportId + "1.jpg");
					} else {
						base64Report12 = ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
					}
					map.put("image", base64Report12);// 把图片加进去;
					break;
				case 13:
					String base64Report13;
					if (reprotType.equals("pdf")) {
						base64Report13 = "diag" + auditReportId + "1.jpg";
					} else if (reprotType.equals("doc")) {
						base64Report13 = ImageToBase64.imgToBase64(path + "/diag"
								+ auditReportId + "1.jpg");
					} else {
						base64Report13 = "data:image/jpeg;base64,"
								+ ImageToBase64.imgToBase64(path + "/diag"
										+ auditReportId + "1.jpg");
					}

					map.put("image", base64Report13);// 把图片加进去;
					break;
				}

				return map;
		
	}
	/**
     * 取double类型小数点后后两位，第三位四舍五入
     * <功能详细描述>
     * @param double double
     * @return double
     */
	private double getNum(double num){
		return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
     * 取double类型小数点后后两位，第三位四舍五入
     * <功能详细描述>
     * @param String string
     * @return double
     */
	private double getStringtoNum(String str){
		return new BigDecimal(Double.parseDouble(str)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}