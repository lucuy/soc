package com.soc.service.risk.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.asset.AssetGroupDao;
import com.soc.dao.risk.AssetRiskEvaluationDao;
import com.soc.dao.risk.AssetRiskGroupDao;
import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.risk.AssetRiskEvaluation;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.risk.AssetRiskEvaluationService;
import com.util.ImageToBase64;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 事件库业务层实现类
 * 
 * @author 何贝贝
 */
public class AssetRiskEvaluationServiceImpl implements
		AssetRiskEvaluationService {
	private AssetGroupService assetGroupManager;
	private static final long serialVersionUID = 1L;
	private AssetRiskEvaluationDao assetRiskEvaluationDao;
	private AssetRiskGroupDao assetRiskGroupDao;
    private AssetDao assetDao;
    private AssetGroupDao assetGroupDao;
	@Override
	public int count(Map map) {
		return assetRiskEvaluationDao.count(map);
	}

	@Override
	public int addAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation) {
		return assetRiskEvaluationDao
				.insertAssetRiskEvaluation(assetRiskEvaluation);
	}

	@Override
	public SearchResult queryAssetRiskEvaluation(Map map, Page page) {
		 String assetGroupId= map.get("assetGroupId").toString();
			long	assetGroupIds = Integer.parseInt(assetGroupId);
				if(assetGroupIds==1){
					map.remove("assetGroupId");
				}else{
					String assetGroupIdss=assetGroupManager.getAllGroupIdByID(assetGroupIds);
					map.put("assetGroupId", assetGroupIdss);
				}
		List<AssetRiskEvaluation> list = assetRiskEvaluationDao.query(map,
				page.getStartIndex(), page.getPageSize());
		// 对查找的事件库做分页处理
		int rowsCount = assetRiskEvaluationDao.count(map);
		page.setTotalCount(rowsCount);
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);

		return sr;
	}

	@Override
	public AssetRiskEvaluation queryInfoById(int id) {
		return assetRiskEvaluationDao.queryById(id);
	}

	@Override
	public int modifyAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation) {
		assetRiskEvaluation.setAssetValueUpdateTime(new Date());
		int value=assetRiskEvaluationDao
				.updateAssetRiskEvaluation(assetRiskEvaluation);
		
		
		 AssetRiskEvaluation as =assetRiskEvaluationDao.queryById(assetRiskEvaluation.getAssetRiskEvaluationId());
		 long riskAssetId = as.getRelAssetId();
		//通过关联的资产id找到资产
			Asset asset = assetDao.queryByid(riskAssetId);
			//创建一个新的资产对象，做修改威胁值
			Asset asset1 = new Asset();
			double riskvalue=assetRiskGroupDao.AssetValue(riskAssetId);
			asset1.setAssetValue(this.getNum(riskvalue));
	    	asset1.setvAVulnerabilityValue(asset.getvAVulnerabilityValue());
	    	asset1.setRiskThreatValue(asset.getRiskThreatValue());
	    	asset1.setAssetId(riskAssetId);
	    	asset1.setIntegrityValue(assetRiskEvaluation.getAssetIntegrityValue());
	    	asset1.setUsabilityValue(assetRiskEvaluation.getAssetUsabilityValue());
	    	asset1.setSecretValue(assetRiskEvaluation.getAssetSecretValue());
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
	    			String assetValue = assetRiskGroupDao.queryByName2(map1);
	    			//如果威胁值为null就手动赋值0.0
	    			if(StringUtil.isNotEmpty(assetValue)){
	    				ag1.setAssetValue(this.getStringtoNum(assetValue));
	    			}else{
	    				ag1.setRiskThreatValue(0.0);
	    			}
	    			ag1.setvAVulnerabilityValue(ag.getvAVulnerabilityValue());
	    			ag1.setRiskThreatValue(ag.getRiskThreatValue());
	    			
	    			ag1.setAssetGroupId(ag.getAssetGroupId());
	    			//修改资产组的威胁值、脆弱性值、资产值
	    			assetRiskGroupDao.updateGrouprisk(ag1);
				}
	    	}
		return value;
	}

	@Override
	public int deleteAssetRiskEvaluationById(int... ids) {
		return assetRiskEvaluationDao.deleteAssetRiskEvaluationById(ids);
	}

	@Override
	public AssetRiskEvaluation queryAssetRiskEvaluationById(Map map) {
		return assetRiskEvaluationDao.query(map, 0, 1).get(0);
	}

	public AssetRiskEvaluationDao getAssetRiskEvaluationDao() {
		return assetRiskEvaluationDao;
	}

	public void setAssetRiskEvaluationDao(
			AssetRiskEvaluationDao assetRiskEvaluationDao) {
		this.assetRiskEvaluationDao = assetRiskEvaluationDao;
	}

	@Override
	public String charts(int id, String falg) {
		AssetRiskEvaluation assetRel = assetRiskEvaluationDao.queryById(id);
		StringBuffer sbf = new StringBuffer();
		if (falg.equals("1")) {
			if (assetRel != null) {
				if(assetRel.getAssetUsabilityValue()>0&&assetRel.getAssetIntegrityValue()>0&&assetRel.getAssetSecretValue()>0){
					sbf.append("[{name:'可用性价值',data:["
							+ assetRel.getAssetUsabilityValue()
							+ "]},{name:'完整性价值',data:["
							+ assetRel.getAssetIntegrityValue()
							+ "]},{name:'保密性价值',data:["
							+ assetRel.getAssetSecretValue() + "]}]");
				}else{
					sbf.append("[]");
				}
			} else {
				sbf.append("[{name:'没有数据',data:[0]}]");
			}
		} else {
			if (assetRel != null) {
				if(assetRel.getAssetUsabilityValue()>0&&assetRel.getAssetIntegrityValue()>0&&assetRel.getAssetSecretValue()>0){
					sbf.append("[['可用性价值',"+assetRel.getAssetUsabilityValue()+"],['完整性价值',"+assetRel.getAssetIntegrityValue()+"],['保密性价值',"+assetRel.getAssetSecretValue()+"]]");
				}else{
					sbf.append("[]");
				}
			} else {
				sbf.append("" + "[['未搜索到数据',0]]" + "");
			}
		}
		return sbf.toString();
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

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
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

	
}
