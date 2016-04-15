package com.soc.service.risk;

import java.util.List;
import java.util.Map;

import com.util.page.Page;
import com.util.page.SearchResult;

public interface AssetRiskGroupService {
	/**
	 * 
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult queryPage(Map map, Page page);

	public SearchResult queryAssetPage(Map<String, Object> map, Page page);
	 /**
     * <资产导出成excel>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map> export(Map map);
    /**
     * <查询资产圆形图>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	public Object queryAssetsNum(Map map);
	 /**
     * <查询资产柱状图>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	public Map queryAssetsNum1(Map map);
	
	 public List<Map> queryAssets(Map map);
	 
	 /**
	     * <查询资产组圆形图>
	     * <功能详细描述>
	     * @param 
	     * @return map
	     * @see [类、类#方法、类#成员]
	     */
		public Object queryRiskGroupNum(Map map);
		 /**
	     * <查询资产组柱状图>
	     * <功能详细描述>
	     * @param 
	     * @return map
	     * @see [类、类#方法、类#成员]
	     */
		public Map queryRiskGroupNum1(Map map);
		
		 /**
	     * <查询资产圆形图>
	     * <功能详细描述>
	     * @param 
	     * @return map
	     * @see [类、类#方法、类#成员]
	     */
		public Object queryRiskAssetNum(Map map);
		 /**
	     * <查询资产柱状图>
	     * <功能详细描述>
	     * @param 
	     * @return map
	     * @see [类、类#方法、类#成员]
	     */
		public Map queryRiskAssetNum1(Map map);
		public List<Map> queryRiskGroup(Map map);
		
		/**
		 * <获得导出文件的map>
		 * <功能详细描述>
		 * @param auditReportId
		 * @param path
		 * @param reprotType
		 * @return
		 * @see [类、类#方法、类#成员]
		 */
			public Map<String, Object> getFreemakerMapExport(long auditReportId,
					String path, String reprotType);

}





