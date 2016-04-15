package com.soc.webapp.action.risk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.struts2.ServletActionContext;


import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.risk.AssetRiskGroupService;
import com.soc.service.risk.VulnerabilityAssessmentService;
import com.soc.service.risk.export.ExcelAssetGroup;
import com.soc.webapp.action.BaseAction;
import com.soc.webapp.action.asset.AssetAction;
import com.util.DateUtil;
import com.util.FreeMarkerUtil;
import com.util.HTMLToPDF;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetRiskGroupAction extends BaseAction {

	private AssetRiskGroupService assetRiskGroupManager;
	// 资产的业务处理类

	private AssetService assetManager;
	private AssetGroupService assetGroupManager;
	private List<AssetGroup> assetGroupList;
	private List<Asset> assetList;
	private List<Asset> assetEvaluationList;
private 	List<Map> assetmap;

private static Map<String, Object> mapStatic = new HashMap<String, Object>();
// 用来存放导出的报表类型,这里直接用String 为了是生成文件名的时候方便
private String reportType;
private boolean exist;// 文件是否存在
private long securityReportId;
	public String getReportType() {
	return reportType;
}

public void setReportType(String reportType) {
	this.reportType = reportType;
}

public boolean isExist() {
	return exist;
}

public void setExist(boolean exist) {
	this.exist = exist;
}

public long getSecurityReportId() {
	return securityReportId;
}

public void setSecurityReportId(long securityReportId) {
	this.securityReportId = securityReportId;
}

	public List<Map> getAssetmap() {
	return assetmap;
}

public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

public void setAssetmap(List<Map> assetmap) {
	this.assetmap = assetmap;
}

	private String assetGroupId;

	private VulnerabilityAssessmentService vulnerabilityAssessmentManager;

	private String keyword;
	private String selvAVulnerabilityValue;   //高级查询--脆弱性值
    
    private String selriskThreatValue;        //高级查询--威胁值
    
    private String selassetValue;				//高级查询--资产风险
 // 高级查询资产Ip
 	private String selAssetIps;

 	// 高级查询资产重要性
 	private String selAssetImportance;
 // 高级查询资产名
 	private String selAssetName;
 	// 页面获得的 ids
 	private String ids;
	// 要查询的字段
	private String field = "ASSET_GROUP_UPDATE_DATE_TIME";
	 private JSONArray assetNumJson;
	 //标识符
	 private String falg;
	 //图表名称
	 private String chartName;
	 //X轴
	 private String chartX;
	 //Y轴
	 private String chartY;
	 //topN
	 private String topN;
	 private String  assetsObj;
	 //柱状图数据
	 private String returnValue;
	//柱状图数据 
	 private String returnKey;
	//高级查询资产组id
	 private String assetGroupIds;

	/**
	 * 
	 * @return
	 */
	public String query() {
		/*
		 * Page page = null; SearchResult sr = null; Map<String, Object> map =
		 * new HashMap<String, Object>(); HttpServletRequest request =
		 * super.getRequest(); // 处理数据分页的起始条数 String startIndex =
		 * request.getParameter("startIndex"); if
		 * (StringUtil.isNotBlank(startIndex)) { page = new
		 * Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex)); } else {
		 * page = new Page(Page.DEFAULT_PAGE_SIZE, 0); } //
		 * 根据map中存储的查询条件，查找符合条件的用户列表
		 * 
		 * sr = assetRiskGroupManager.queryPage(map, page);
		 * 
		 * // 对查找的结果为分页赋值 if (sr != null) { assetGroupList =
		 * (List<AssetGroup>)sr.getList(); request.setAttribute("Page",
		 * sr.getPage()); } else { request.setAttribute("Page", new
		 * Page(Page.DEFAULT_PAGE_SIZE, 0)); } return SUCCESS;
		 */
		HttpServletRequest request = super.getRequest();
		
		
		long groupId=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		
		Page page = null;
		SearchResult sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		try {
			if (StringUtil.isNotBlank(startIndex)) {
				if (keyword==null || keyword.equals("")) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				}else {
					if (Page.getKeyword().equals(keyword)) {
						page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
						Page.setKeyword(keyword);
					}else{
						page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
						Page.setKeyword(keyword);
					}
				}
			} else {
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				Page.setKeyword(keyword);
			}			
		} catch (Exception e) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			Page.setKeyword(keyword);
		}
		
		
		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();
		if(groupId!=1){
		 map.put("assetGroupId", groupId);	
		}else{
			
		}
		if (request.getParameter("keyword") != null) {
			try {

				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			map.put("keyword", keyword);
		}
		
		/*
		 * if (StringUtil.isNotBlank(assetGroupName)) {
		 * map.put("assetGroupName", Long.valueOf(assetGroupName)); }
		 */
		if(StringUtil.isNotEmpty(selassetValue)){
			map.put("selassetValue", Integer.parseInt(selassetValue));
		}
		if(StringUtil.isNotEmpty(selriskThreatValue)){
			map.put("selriskThreatValue", Integer.parseInt(selriskThreatValue));
		}
		if(StringUtil.isNotEmpty(selvAVulnerabilityValue)){
			map.put("selvAVulnerabilityValue", Integer.parseInt(selvAVulnerabilityValue));
		}
		// 根据条件查询相应审计数据
		sr = assetRiskGroupManager.queryPage(map, page);
		if (sr != null) {
			assetGroupList = (List<AssetGroup>) sr.getList();
			request.setAttribute("associationList", assetGroupList);
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		
		return SUCCESS;

	}

	public String queryByName() {
		//System.out.println("ASSET_GROUP_UPDATE_DATE_TIME".equals(field));
		
		
		if("ASSET_GROUP_UPDATE_DATE_TIME".equals(field) || field=="" || field ==null){			
			field = "ASSET_CREATE_DATE_TIME" ; 
		}
		
		HttpServletRequest request = super.getRequest();
		
		HttpSession session = this.getSession();

		int changeNum = 0;
		changeNum = session.getAttribute("CHANGENUM") == null ? 1
				: (Integer) session.getAttribute("CHANGENUM");

		Page page = null;
		SearchResult sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();
		//接收快速查询条件
		if (request.getParameter("keyword") != null) {
			try {

				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(StringUtil.isNotEmpty(keyword)){
				map.put("keyword", keyword);
			}
			
		}
		// 高级查询
				if (StringUtil.isNotBlank(selAssetName)) {

					map.put("selAssetName", selAssetName);
				}

				if (StringUtil.isNotBlank(selAssetIps)) {

					map.put("selAssetIps", selAssetIps);
				}
				if(StringUtil.isNotEmpty(selassetValue)){
					map.put("selassetValue", Integer.parseInt(selassetValue));
				}
				if(StringUtil.isNotEmpty(selriskThreatValue)){
					map.put("selriskThreatValue", Integer.parseInt(selriskThreatValue));
				}
				if(StringUtil.isNotEmpty(selvAVulnerabilityValue)){
					map.put("selvAVulnerabilityValue", Integer.parseInt(selvAVulnerabilityValue));
				}
		if (field != "" && field != null) {
			int num = changeNum % 2;

			if (num == 0) {
				map.put("sortType", "DESC");
			} else {
				map.put("sortType", "ASC");
			}
			map.put("field", field);

			String field = (String) map.get("field");
			String sortType = (String) map.get("sortType");
			String str = "ORDER BY \"" + field + "\"" + " " + sortType;

			map.put("str", str);

		}
		if(StringUtil.isNotEmpty(assetGroupIds)){
			if(Long.valueOf(assetGroupIds)==1){
				
			}else{
				String assetGroupIdss =assetGroupManager.getAllGroupIdByID(Long.parseLong(assetGroupIds));	
			map.put("assetGroupId", assetGroupIdss);}
		}
		
		if (request.getAttribute("assetGroupId")!=null) {
			assetGroupId=(String) request.getAttribute("assetGroupId");
			if(Long.valueOf(assetGroupId)==1){
				
			}else{
				String assetGroupIdss =assetGroupManager.getAllGroupIdByID(Long.parseLong(assetGroupId));	
			map.put("assetGroupId", assetGroupIdss);}
		}
		request.setAttribute("assetGroupId", assetGroupId);
		// 根据条件查询相应审计数据
		sr = assetRiskGroupManager.queryAssetPage(map, page);
		if (sr != null) {
			assetList = (List<Asset>) sr.getList();
			request.setAttribute("associationList", assetGroupList);
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		
		changeNum++ ; 
        session.setAttribute("CHANGENUM", changeNum);
		return SUCCESS;
	}

	//
	public String queryEvaluation() {
		HttpServletRequest request = super.getRequest();
		Page page = null;
		SearchResult sr = null;
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		/*
		 * if (StringUtil.isNotBlank(assetGroupName)) {
		 * map.put("assetGroupName", Long.valueOf(assetGroupName)); }
		 */
		// 根据条件查询相应审计数据
		sr = assetRiskGroupManager.queryPage(map, page);
		if (sr != null) {
			assetGroupList = (List<AssetGroup>) sr.getList();
			request.setAttribute("associationList", assetGroupList);
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}

	public String queryEvaluationByName() {
		HttpServletRequest request = super.getRequest();

		Page page = null;
		SearchResult sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}

		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(assetGroupId)) {
			map.put("assetGroupId", Long.valueOf(assetGroupId));
		}
		// 根据条件查询相应审计数据
		sr = assetRiskGroupManager.queryAssetPage(map, page);
		if (sr != null) {
			assetEvaluationList = (List<Asset>) sr.getList();
			request.setAttribute("associationList", assetGroupList);
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}
	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void export(){
		log.info("[AssetRiskGropAction] enter export...");
		HttpServletResponse response = super.getResponse();
		HttpServletRequest request =super.getRequest();
		ExcelAssetGroup excelasset = new ExcelAssetGroup();
		Map<String, Object> limit = new HashMap<String, Object>();
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		if (StringUtil.isNotBlank(ids)) {
			limit.put("ids", ids);
		}else{
			if(groupid!=1){
				limit.put("assetGroupId", groupid);
			}else{
				
			}
		}
		if (StringUtil.isNotEmpty(request.getParameter("keyword")) ) {
			try {

				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			limit.put("keyword", keyword);
		}
		
		
		if(StringUtil.isNotEmpty(selassetValue)){
			limit.put("selassetValue", Integer.parseInt(selassetValue));
		}
		if(StringUtil.isNotEmpty(selriskThreatValue)){
			limit.put("selriskThreatValue", Integer.parseInt(selriskThreatValue));
		}
		if(StringUtil.isNotEmpty(selvAVulnerabilityValue)){
			limit.put("selvAVulnerabilityValue", Integer.parseInt(selvAVulnerabilityValue));
		}
		
		excelasset.export(assetRiskGroupManager.export(limit), "资产组Excel报表");
		try {
			// 中文文件名需要编码
			String fileName = "assetGroup_" + DateUtil.curDateStr8();
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ fileName + ".xls");
			OutputStream os = response.getOutputStream();
			excelasset.getGwb().write(os);

			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		String result="";
		log.info("assets interface method: queryEventStatistics");
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		HttpServletRequest request = super.getRequest();
		Map map =new HashMap();
		HttpSession session =super.getSession();
		map.put("assetGroupId", groupid);
		chartX =request.getParameter("chartX");
		chartY =request.getParameter("chartY");
		switch (Integer.parseInt(chartX)) {
		case 0:
			map.put("key", "ASSET_GROUP_NAME");
			break;
		case 1:
			map.put("key", "ASSET_DEVICE_TYPE");
			break;

			
		default:
			break;
		}
		switch (Integer.parseInt(chartY)) {

		case 2:
			map.put("value", "ASSET_ASSET_VALUE");
			break;
		case 3:
			map.put("value", "RISK_THREATVALUE");
			break;
		case 4:
			map.put("value", "VAVULNERABILITYVALUE");
			break;
		case 5:
			map.put("value", "ASSET_ASSET_VALUE\"*\"RISK_THREATVALUE\"*\"VAVULNERABILITYVALUE");
			break;
			
		default:
			break;
		}
		switch (Integer.parseInt(chartY)) {

		case 2:
			chartName="资产值";
			break;
		case 3:
			chartName="威胁值";
			break;
		case 4:
			chartName="脆弱性值";
			break;
		case 5:
			chartName="风险值";
			break;
			
		default:
			break;
		}
	
		falg=request.getParameter("falg");
		topN=request.getParameter("topN");
		map.put("topN", Integer.parseInt(topN));
		if(Integer.parseInt(chartX)==1){
		
		assetmap =assetRiskGroupManager.queryAssets(map);
		map.put("assetGroupId", groupid);
		if(falg.equals("1")){//柱状图
			Map resultMap = new HashMap();
			
			resultMap=assetRiskGroupManager.queryAssetsNum1(map);
			returnKey =(String) resultMap.get("returnKey");
			returnValue=(String) resultMap.get("returnValue");
			log.info("事件统计数据" + returnKey+returnValue);
			
			result= "histogram";
					}else{
			 assetsObj = (String) assetRiskGroupManager.queryAssetsNum(map);
			 log.info("事件统计数据" + assetsObj);
			 result= SUCCESS;
		}
		}else{
			assetmap=assetRiskGroupManager.queryRiskGroup(map);
			map.put("assetGroupId", groupid);
			if(falg.equals("1")){
				Map resultMap = new HashMap();
				resultMap=assetRiskGroupManager.queryRiskGroupNum1(map);
				returnKey =(String) resultMap.get("returnKey");
				returnValue=(String) resultMap.get("returnValue");
				log.info("事件统计数据" + returnKey+returnValue);
				result= "histogram";
			}else{
				 assetsObj = (String) assetRiskGroupManager.queryRiskGroupNum(map);
				 log.info("事件统计数据" + assetsObj);
				 result= SUCCESS;
			}
			
			
			
		}
		
		return result;
		
	}
	
	/**
     * <查询资产数据图表展示>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public String RiskAssetChart(){
		String result="";
		HttpServletRequest request = super.getRequest();
		Map map =new HashMap();
		
		chartY=request.getParameter("chartY");
		switch (Integer.parseInt(chartY)) {

		case 1:
			chartName="资产值";
			break;
		case 2:
			chartName="威胁值";
			break;
		case 3:
			chartName="脆弱性值";
			break;
		case 4:
			chartName="风险值";
			break;
			
		default:
			break;
		}
		switch (Integer.parseInt(chartY)) {

		case 1:
			map.put("value", "ASSET_ASSET_VALUE");
			break;
		case 2:
			map.put("value", "RISK_THREATVALUE");
			break;
		case 3:
			map.put("value", "VAVULNERABILITYVALUE");
			break;
		case 4:
			map.put("value", "ASSET_ASSET_VALUE\"*\"RISK_THREATVALUE\"*\"VAVULNERABILITYVALUE");
			break;
			
		default:
			break;
		}
		falg=request.getParameter("falg");
		topN=request.getParameter("topN");
		map.put("topN", Integer.parseInt(topN));
		switch (Integer.parseInt(chartY)) {
		case 1:
			chartName="资产值";
			break;
		case 2:
			chartName="威胁值";
			break;
		case 3:
			chartName="脆弱性值";
			break;
		case 4:
			chartName="风险值";
			break;
			
		default:
			break;
		}
		assetGroupId=request.getParameter("assetGroupId");
		
			map.put("assetGroupId", assetGroupId);
		
		if(falg.equals("1")){//柱状图
			Map resultMap = new HashMap();
			resultMap=assetRiskGroupManager.queryRiskAssetNum1(map);
			returnKey =(String) resultMap.get("returnKey");
			returnValue=(String) resultMap.get("returnValue");
			log.info("事件统计数据" + returnKey+returnValue);
			
			result= "AssetChart1";
					}else{
			 assetsObj = (String) assetRiskGroupManager.queryRiskAssetNum(map);
			 log.info("事件统计数据" + assetsObj);
			 result= "AssetChart";
		}
		
		return result;
	}
	public String ExportReport(){
		
		
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		svgConvertJpeg(ServletActionContext.getRequest(), securityReportId);
		HttpServletResponse resp = ServletActionContext.getResponse();
		boolean isPdf = false;// 判断此类型是不是pdf
		if ("pdf".equals(reportType)) {
			isPdf = true;
		}
		createReportFile(securityReportId, reportType, isPdf);
		if (isPdf) {
			String inputPath = path +"reportFile"+File.separator+ getReportNameById(securityReportId, "html");
			String outputPath = path +"reportFile"+File.separator+ getReportNameById(securityReportId, "pdf");
			String imagePath = path+"reportFile"+File.separator;
			String fontsPath = path + File.separator + "styles"
					+ File.separator + "SIMSUN.TTC";
			try {
				HTMLToPDF
						.htmlToPdf(inputPath, outputPath, imagePath, fontsPath);
			} catch (Exception e) {
				// 应该没异常吧
				e.printStackTrace();
			}
		}

		OutputStream out;// 输出响应正文的输出流
		InputStream in ;// 读取本地文件的输入流
		// 获得本地输入流
		String fileName = getReportNameById(securityReportId, reportType);
		File file = new File(path+"reportFile"+File.separator + fileName);
		try {
				in = new FileInputStream(file);
			// 设置响应正文的MIME类型
			resp.setContentType("Content-Disposition;charset=utf-8");
			resp.setHeader("Content-Disposition", "attachment;" + " filename="
					+ new String(file.getName().getBytes(), "ISO-8859-1"));
			// 把本地文件发送给客户端
			out = resp.getOutputStream();
			int byteRead = 0;
			byte[] buffer = new byte[1024];
			while ((byteRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteRead);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	private String svgConvertJpeg(HttpServletRequest request, long auditReportId) {
		try {
			request.setCharacterEncoding("GBK");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer imageSb = new StringBuffer();
		try {
			
			String svg = null;
			String type = "svg";
			String imgPath = getServletContext().getRealPath(File.separator)+File.separator+"reportFile";

			File imageFile = new File(imgPath);
			if (!imageFile.exists()) {
				imageFile.mkdirs();
			}
			imgPath += "/";
			if (request.getParameter("picCount") != null) {// 等于null就是意味着这个报表没有图表,
				int count = Integer.parseInt(request.getParameter("picCount"));
				if (count != 0) {
					for (int i = 1; i <= count; i++) {
						svg = request.getParameter("picture" + i);
						type = "diag";

						if (null != type) {
							String ext = "";
							Transcoder t = null;
							if (type.equals("diag")) {
								ext = svg;
							}
							if (null != ext && null != svg) {
								ext = ext.replaceAll(":rect", "rect");
								t = new JPEGTranscoder();
								if (null != t) {
									TranscoderInput input = new TranscoderInput(
											new StringReader(ext));
									OutputStream output = new FileOutputStream(
											imgPath + type + auditReportId + i
													+ ".jpg");
									TranscoderOutput transOutput = new TranscoderOutput(
											output);
									t.addTranscodingHint(
											JPEGTranscoder.KEY_QUALITY,
											new Float(.9f));
									try {
										t.transcode(input, transOutput);
									} catch (TranscoderException e) {
									}

									File file = new File(imgPath + type + i
											+ ".jpg");
									String filePath = file.getAbsolutePath();
									String realImagePath = filePath.substring(
											filePath.indexOf(""),
											filePath.length());
									if (i == count) {
										imageSb.append(realImagePath);
									} else {
										imageSb.append(realImagePath + ",");
									}
									output.flush();
									output.close();
								}
							}
						}

						if (svg == null) {
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageSb.toString();
	}
	
	
	private String getReportNameById(long auditReportId, String reportType) {
		String fileName = null;
		try {
		switch ((int) auditReportId) {
		case 2:
			fileName = java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25AE%25A1%25E7%2590%2586%25E5%25B9%25B3%25E5%258F%25B0%25E6%259C%2588%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 3:
			fileName = java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E8%25BF%2590%25E8%25A1%258C%25E7%258A%25B6%25E6%2580%2581%25E6%258A%25A5%25E5%25AE%258C%25E6%2588%2590%25E8%25A1%25A8", "GBK")+"." + reportType;
			break;
		case 4:
			fileName = java.net.URLDecoder.decode("%25E6%25BC%258F%25E6%25B4%259E%25E6%2589%25AB%25E6%258F%258F%25E6%258A%25A5%25E8%25A1%25A8", "GBK")+"." + reportType;
			break;
		case 5:
			fileName = java.net.URLDecoder.decode("%25E8%25AE%25BE%25E5%25A4%2587%25E6%2598%258E%25E7%25BB%2586%25E7%25BB%259F%25E8%25AE%25A1%25E6%2597%25A5%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 11:
			fileName = java.net.URLDecoder.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E7%25BB%2584%25E8%25AF%2584%25E4%25BC%25B0%25E6%259F%25B1%25E5%25BD%25A2%25E5%259B%25BE", "GBK")+"." + reportType;
			break;
		case 12:
			fileName =java.net.URLDecoder.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E8%25AF%2584%25E4%25BC%25B0%25E5%259C%2586%25E9%25A5%25BC%25E5%259B%25BE", "GBK")+ "." + reportType;
			break;
		case 13:
			fileName = java.net.URLDecoder.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E8%25AF%2584%25E4%25BC%25B0%25E6%259F%25B1%25E5%25BD%25A2%25E5%259B%25BE", "GBK")+"." + reportType;
			break;
		
		case 6:
			fileName =java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E5%2585%25AC%25E5%2591%258A%25E6%2595%25B0%25E6%258D%25AE%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
	
	
	private void createReportFile(long auditReportId, String reportType,
			boolean isPdf) {
		Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		FreeMarkerUtil fm = new FreeMarkerUtil();
		HttpServletRequest request = super.getRequest();
		
		Map map =new HashMap();
		long groupId=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		map.put("assetGroupId", groupId);
		chartX =request.getParameter("chartX");
		chartY =request.getParameter("chartY");
		if(chartX!=null&&chartX!=""){
			switch (Integer.parseInt(chartX)) {
			case 0:
				map.put("key", "ASSET_GROUP_NAME");
				break;
			case 1:
				map.put("key", "ASSET_DEVICE_TYPE");
				break;

				
			default:
				break;
			}
			switch (Integer.parseInt(chartY)) {

			case 2:
				map.put("value", "ASSET_ASSET_VALUE");
				break;
			case 3:
				map.put("value", "RISK_THREATVALUE");
				break;
			case 4:
				map.put("value", "VAVULNERABILITYVALUE");
				break;
			case 5:
				map.put("value", "ASSET_ASSET_VALUE\"*\"RISK_THREATVALUE\"*\"VAVULNERABILITYVALUE");
				break;
				
			default:
				break;
			}
		}
		
		switch (Integer.parseInt(chartY)) {

		case 2:
			chartName="资产值";
			break;
		case 3:
			chartName="威胁值";
			break;
		case 4:
			chartName="脆弱性值";
			break;
		case 5:
			chartName="风险值";
			break;
			
		default:
			break;
		}
	
		falg=request.getParameter("falg");
		topN=request.getParameter("topN");
		map.put("topN", Integer.parseInt(topN));
		if(chartX!=null&&chartX!=""){
			if(Integer.parseInt(chartX)==1){
				
				assetmap =assetRiskGroupManager.queryAssets(map);
				
				}else{
					assetmap=assetRiskGroupManager.queryRiskGroup(map);
				
				}
			mapStatic.put("assetmap", assetmap);
		}
		
		mapStatic.putAll(assetRiskGroupManager.getFreemakerMapExport(
				auditReportId, path+"reportFile" ,reportType));
		
		if (isPdf) {// 如果是pdf,需要加载html模板
			reportType = "html";
		}
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path+"reportFile"+File.separator
							+ getReportNameById(auditReportId, reportType))),
					"utf-8"));
			// map.put("image", new File(path +
			// "/reportFormImages/diag1.jpg"));//把图片加进去
			// 这里根据生成的文件类型加载不同的模板 因为以后report6到9导出doc的时候加载的是wrod模板 其他都是html模板
			// 这里要判断
			if (auditReportId >= 6 && auditReportId <= 15) {
				fm.loadTemplate("freemarkerRisk" + reportType + auditReportId
						+ ".ftl", mapStatic, "template", out);// 生成文件的代码
			} else {
				fm.loadTemplate("freemarkerRiskhtml" + auditReportId + ".ftl",
						mapStatic, "template", out);// 生成文件的代码
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public String reportFileIsExist() {
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		File file = new File(path+"reportFile"+File.separator
				+ getReportNameById(securityReportId, reportType));
		if (file.exists()) {
			exist = true;
		} else {
			exist = false;
		}
		// //System.out.println("gs");
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public VulnerabilityAssessmentService getVulnerabilityAssessmentManager() {
		return vulnerabilityAssessmentManager;
	}

	public void setVulnerabilityAssessmentManager(
			VulnerabilityAssessmentService vulnerabilityAssessmentManager) {
		this.vulnerabilityAssessmentManager = vulnerabilityAssessmentManager;
	}

	public AssetRiskGroupService getAssetRiskGroupManager() {
		return assetRiskGroupManager;
	}

	public void setAssetRiskGroupManager(
			AssetRiskGroupService assetRiskGroupManager) {
		this.assetRiskGroupManager = assetRiskGroupManager;
	}

	public List<AssetGroup> getAssetGroupList() {
		return assetGroupList;
	}

	public void setAssetGroupList(List<AssetGroup> assetGroupList) {
		this.assetGroupList = assetGroupList;
	}

	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public String getAssetGroupId() {
		return assetGroupId;
	}

	public void setAssetGroupId(String assetGroupId) {
		this.assetGroupId = assetGroupId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Asset> getAssetEvaluationList() {
		return assetEvaluationList;
	}

	public void setAssetEvaluationList(List<Asset> assetEvaluationList) {
		this.assetEvaluationList = assetEvaluationList;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSelvAVulnerabilityValue() {
		return selvAVulnerabilityValue;
	}

	public void setSelvAVulnerabilityValue(String selvAVulnerabilityValue) {
		this.selvAVulnerabilityValue = selvAVulnerabilityValue;
	}

	public String getSelriskThreatValue() {
		return selriskThreatValue;
	}

	public void setSelriskThreatValue(String selriskThreatValue) {
		this.selriskThreatValue = selriskThreatValue;
	}

	public String getSelassetValue() {
		return selassetValue;
	}

	public void setSelassetValue(String selassetValue) {
		this.selassetValue = selassetValue;
	}

	public String getSelAssetIps() {
		return selAssetIps;
	}

	public void setSelAssetIps(String selAssetIps) {
		this.selAssetIps = selAssetIps;
	}

	public String getSelAssetImportance() {
		return selAssetImportance;
	}

	public void setSelAssetImportance(String selAssetImportance) {
		this.selAssetImportance = selAssetImportance;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSelAssetName() {
		return selAssetName;
	}

	public void setSelAssetName(String selAssetName) {
		this.selAssetName = selAssetName;
	}

	public JSONArray getAssetNumJson() {
		return assetNumJson;
	}

	public void setAssetNumJson(JSONArray assetNumJson) {
		this.assetNumJson = assetNumJson;
	}
	
	

	public String getFalg() {
		return falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}
	

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public String getChartX() {
		return chartX;
	}

	public void setChartX(String chartX) {
		this.chartX = chartX;
	}

	public String getChartY() {
		return chartY;
	}

	public void setChartY(String chartY) {
		this.chartY = chartY;
	}

	public String getTopN() {
		return topN;
	}

	public void setTopN(String topN) {
		this.topN = topN;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getReturnKey() {
		return returnKey;
	}

	public void setReturnKey(String returnKey) {
		this.returnKey = returnKey;
	}

	public void setAssetsObj(String assetsObj) {
		this.assetsObj = assetsObj;
	}

	public String getAssetsObj() {
		return assetsObj;
	}

	public String getAssetGroupIds() {
		return assetGroupIds;
	}

	public void setAssetGroupIds(String assetGroupIds) {
		this.assetGroupIds = assetGroupIds;
	}
	
	
}