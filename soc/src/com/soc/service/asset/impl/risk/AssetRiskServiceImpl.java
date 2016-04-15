package com.soc.service.asset.impl.risk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.asset.AssetGroupDao;
import com.soc.dao.asset.risk.AssetRiskDao;
import com.soc.dao.risk.AssetRiskGroupDao;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.asset.AssetRiskGroup;
import com.soc.model.asset.AssetRiskValue;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.risk.AssetRiskService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetRiskServiceImpl extends BaseServiceImpl implements AssetRiskService
{
    private AssetRiskDao assetRiskDao;
    private AssetGroupService assetGroupManager;
    private AssetRiskGroupDao assetRiskGroupDao;
    private AssetDao assetDao;
    private AssetGroupDao assetGroupDao;
    @Override
    public AssetRiskGroup queryAssetRiskGroup(String groupName)
    {
        return assetRiskDao.queryAssetRiskGroup(groupName);
    }
    
    @Override
    public List<AssetRiskValue> queryAssetRiskValuebyname(String groupName)
    {
        
        return assetRiskDao.queryAssetRiskValuebyname(groupName);
    }

    /**
     * 导入
     */
    @Override
    public void insertRiskValue(AssetRiskValue assetRiskValue)
    {
        assetRiskDao.addAssetRiskValue(assetRiskValue);
        long riskAssetId =assetRiskValue.getRiskAssetId();
        this.updateRiskValueforAssetAndAssetGroup(riskAssetId);
    }
    //get and set

    public AssetRiskDao getAssetRiskDao()
    {
        return assetRiskDao;
    }

    public void setAssetRiskDao(AssetRiskDao assetRiskDao)
    {
        this.assetRiskDao = assetRiskDao;
    }

   @Override
   public List<AssetRiskValue> queryAssetRiskValue()
   {
       return assetRiskDao.queryAssetRiskValue();
   }

    @Override
    public SearchResult queryPage(Map<String, Object> map, Page page)
    {
       
        
//        String assetGroupId= map.get("assetGroupId").toString();
//		long	assetGroupIds = Integer.parseInt(assetGroupId);
    	long	assetGroupIds = (Long) map.get("assetGroupId");
			if(assetGroupIds==1){
				map.remove("assetGroupId");
			}else{
				String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
				map.put("assetGroupId", assetGroupIdss);
			}
			 int rowsCount = assetRiskDao.associationPage(map);
			 page.setTotalCount(rowsCount);
        List<Map<String, Object>> list = assetRiskDao.queryPage(map, page.getStartIndex(), page.getPageSize());
        
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRisk(int parseInt)
    {
    	  long riskAssetId = assetRiskDao.queryAssetRiskValue(parseInt).getRiskAssetId();
          //通过关联的资产id找到资产
            assetRiskDao.deleteRisk(parseInt);
            this.updateRiskValueforAssetAndAssetGroup(riskAssetId);
    }

	@Override
	public SearchResult sort(Map map, Page page) {
		
		 int rowsCount = assetRiskDao.associationPage(map);
	        page.setTotalCount(rowsCount);
	        String field=(String)map.get("field");
	        String sortType=(String)map.get("sortType");
	        String str=" \""+field+"\""+" "+sortType;
	        List<AssetRiskValue> list = assetRiskDao.sort(str, page.getStartIndex(), page.getPageSize());
	        SearchResult sr = new SearchResult() ; 
	        sr.setList(list);
	        sr.setPage(page);
	        return sr;

		
	}

	@Override
	public AssetRiskValue queryAssetRiskValue(long id) {
		return assetRiskDao.queryAssetRiskValue(id);
	}

	@Override
	public void updateAssetRiskValue(Map map) {
		assetRiskDao.updateAssetRiskValue(map);
		//获得关联的资产id
				long riskAssetId = Long.parseLong(map.get("riskAssetId").toString());
				 this.updateRiskValueforAssetAndAssetGroup(riskAssetId);
	}

	@Override
	public List<Map> ReportExcel(Map map) {
		List<Map> list = new ArrayList<Map>();
	//	String assetGroupId= map.get("assetGroupId").toString();
		
			try {
				long	assetGroupIds = Long.valueOf(map.get("assetGroupId").toString());
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
				list=assetRiskDao.ReportExcel(map);
			} catch (Exception e) {
				log.info("导出威胁信息出错了");
			}
			
		return list;
	}

	@Override
	public List<Map> ReportExcelMould(Map map) {
		long assetGroupId= (Long) map.get("assetGroupId");
	//	long	assetGroupIds = Integer.parseInt(assetGroupId);
			if(assetGroupId==1){
				map.remove("assetGroupId");
			}else{
				String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupId);
				map.put("assetGroupId", assetGroupIdss);
			}
		return assetRiskDao.ReportExcelMould(map);
	}

	@Override
	public Asset RiskQueryAssetByName(String str) {
		
		return assetRiskDao.RiskQueryAssetByName(str);
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	public AssetRiskGroupDao getAssetRiskGroupDao() {
		return assetRiskGroupDao;
	}

	public void setAssetRiskGroupDao(AssetRiskGroupDao assetRiskGroupDao) {
		this.assetRiskGroupDao = assetRiskGroupDao;
	}

	public AssetDao getAssetDao() {
		return assetDao;
	}

	public void setAssetDao(AssetDao assetDao) {
		this.assetDao = assetDao;
	}

	public AssetGroupDao getAssetGroupDao() {
		return assetGroupDao;
	}

	public void setAssetGroupDao(AssetGroupDao assetGroupDao) {
		this.assetGroupDao = assetGroupDao;
	}
	/*
	 * 根据威胁值的变化修改关联资产的威胁值，根据资产修改关联资产组的威胁值
	 */
	private  void updateRiskValueforAssetAndAssetGroup(long riskAssetId){
		//通过关联的资产id找到资产
				Asset asset = assetDao.queryByid(riskAssetId);
				//创建一个新的资产对象，做修改威胁值
				Asset asset1 = new Asset();
				double riskvalue=assetRiskGroupDao.avgRiskValue(riskAssetId);
				asset1.setRiskThreatValue(this.getNum(riskvalue));
		    	asset1.setvAVulnerabilityValue(asset.getvAVulnerabilityValue());
		    	asset1.setAssetValue(asset.getAssetValue());
		    	asset1.setAssetId(riskAssetId);
		    	//修改资产的，风险值、威胁值、脆弱性值
		    	assetRiskGroupDao.updateAssetValue(asset1);
		    	
		    	Map map =new HashMap();
		    	map.put("assetId", riskAssetId);
		    	//根据资产id查询所管理的资产组
		    	List<AssetGroup> assetGroup = assetGroupDao.queryGroupByAssetId(map);
		    	//迭代,得到每一个资产组
		    	if(assetGroup.size()>0){
		    		for (AssetGroup ag : assetGroup) {
		    			AssetGroup ag1 = new AssetGroup();
		    			Map map1 =new HashMap();
		    			//如果是全部资产则不拼查询条件
		    			if(ag.getAssetGroupId()!=1){
		    				map1.put("assetGroupId",ag.getAssetGroupId());
		    			}
		    			//得到资产组所管理资产下所有资产的威胁值平均值
		    			String riskThreatValue = assetRiskGroupDao.queryByName(map1);
		    			//如果威胁值为null就手动赋值0.0
		    			if(StringUtil.isNotEmpty(riskThreatValue)){
		    				ag1.setRiskThreatValue(this.getStringtoNum(riskThreatValue));
		    			}else{
		    				ag1.setRiskThreatValue(0.0);
		    			}
		    			ag1.setvAVulnerabilityValue(ag.getvAVulnerabilityValue());
		    			ag1.setAssetValue(ag.getAssetValue());
		    			
		    			ag1.setAssetGroupId(ag.getAssetGroupId());
		    			//修改资产组的威胁值、脆弱性值、资产值
		    			assetRiskGroupDao.updateGrouprisk(ag1);
					}
		    	}
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