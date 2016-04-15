package com.soc.webapp.action.risk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.struts2.ServletActionContext;

import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.risk.AssetRiskEvaluation;
import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.risk.AssetRiskEvaluationService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FreeMarkerUtil;
import com.util.HTMLToPDF;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetRiskEvaluationAction extends BaseAction  {

	private AssetRiskEvaluationService assetRiskEvaluationService;
	
	private AssetRiskEvaluation assetRiskEvaluation;
	
	List<AssetRiskEvaluation> assetRiskEvaluations;
	
	//资产管理的业务类
	private AssetService assetManager;
	
	// 审计业务管理类
	private AuditService auditManager;
	
	//资产的列表
	private List<Asset> assetList;
	private String areid;//接受頁面传过来的id
	private String falg;//頁面传过来要显示的图表
	//快速搜索的关键字
	private String keyword;
	private String strChart;//图表展示需要的字符串
	private String areName;
	private static Map<String, Object> mapStatic = new HashMap<String, Object>();
	// 用来存放导出的报表类型,这里直接用String 为了是生成文件名的时候方便
	private String reportType;
	private boolean exist;// 文件是否存在
	private long securityReportId;
	private int errorMessageflg;//是否有错误信息 因为诸多原因 当errorMessageflg是1的时候errorMessage赋值 在list界面显示
	private String errorMessage;//多人操作时 第一个人到资产赋值界面保存之前 如果第二个人已经删除资产 第一个人保存后报错 这里加一个界面提示
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

	public String getAreName() {
		return areName;
	}

	public void setAreName(String areName) {
		this.areName = areName;
	}

	public String getStrChart() {
		return strChart;
	}

	public void setStrChart(String strChart) {
		this.strChart = strChart;
	}

	public String query() {
	    
		LOG.info("[AssetRiskEvaluationAction] enter method query() ...");
		Page page = null;
		SearchResult sr = null;
		HttpServletRequest request = super.getRequest();
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		
		//高级查询条件
		String assetName = request.getParameter("assetName");
		String assetSecretValue = request.getParameter("assetSecretValue");
		String assetIntegrityValue = request.getParameter("assetIntegrityValue");
		String assetUsabilityValue = request.getParameter("assetUsabilityValue");
		String assetAssetValue = request.getParameter("assetAssetValue");
		String assetRiskEvaluationId = request.getParameter("assetRiskEvaluationId");
		/*String keyword = request.getParameter("keyword");*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("assetGroupId", groupid);
		if (StringUtil.isNotBlank(keyword)) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         map.put("keyword", keyword);
		}
        if (StringUtil.isNotBlank(assetName)){
            map.put("assetName", assetName);
        }
        if (StringUtil.isNotBlank(assetSecretValue)){
            map.put("assetSecretValue", Integer.parseInt(assetSecretValue));
        }
        if (StringUtil.isNotBlank(assetIntegrityValue)){
            map.put("assetIntegrityValue",Integer.parseInt(assetIntegrityValue));
        }
        if (StringUtil.isNotBlank(assetUsabilityValue)){
            map.put("assetUsabilityValue", Integer.parseInt(assetUsabilityValue));
        }
        if (StringUtil.isNotBlank(assetAssetValue)){
            map.put("assetAssetValue", Integer.parseInt(assetAssetValue));
        }
        if (StringUtil.isNotBlank(assetRiskEvaluationId)){
            map.put("assetRiskEvaluationId", Integer.parseInt(assetRiskEvaluationId));
        }
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
		
		sr = assetRiskEvaluationService.queryAssetRiskEvaluation(map, page);
		
		// 对查找的结果为分页赋值
		if (sr != null) {
			assetRiskEvaluations = (List<AssetRiskEvaluation>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		if(this.errorMessageflg == 1){
			 this.errorMessage = "资产已被删除，请重新操作！";
		}
		return SUCCESS;
	}
	
	/**
	 * <添加资产赋值>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String editAssetValue()
	{
	    log.info("[AssetRiskEvaluationAction] enter method editAssetValue()....");
	    long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
	    Map map =new HashMap();
	    
		map.put("assetGroupId", groupid);
	    //获得所有的资产列表
	    assetList =assetManager.query(map);
	    
	    return SUCCESS;
	    
	}
	
	/**
	 * <根据Id查询资产赋值情况>
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryInfoById(){
		HttpServletRequest request = super.getRequest();
		int assetRiskEvaluationId =Integer.parseInt(request.getParameter("assetRiskEvaluationId"));
		assetRiskEvaluation=assetRiskEvaluationService.queryInfoById(assetRiskEvaluationId);
		if (assetRiskEvaluation==null) {
			 this.errorMessageflg = 1;
			 return "errorMessage";
		}else {
			return SUCCESS;
		}
	
	}
	
	public String saveAssetRiskEvaluation(){
		
		return SUCCESS;
	}
	/**
	 * <计算资产的风险值> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public void completeAssetValue() {
		log.info("[AssetRiskEvaluationAction.] Enter method completeAssetValue.....");
		
		HttpServletRequest request = super.getRequest();
		
		int use=Integer.parseInt(request.getParameter("use"));
		int complete=Integer.parseInt(request.getParameter("complete"));
		int secret=Integer.parseInt(request.getParameter("secret"));
	    double s = Math.scalb(3, 2);

	     double temp = ((Math.scalb((GlobalConfig.assetUse), use)
	                + Math.scalb((GlobalConfig.assetComplete), complete) + Math
	                .scalb((GlobalConfig.assetSecret), secret))) / 3;

	     double finalvalue = Math.log(temp) / (Math.log((double) 2));

	      int completevalue = (int) finalvalue;

	      try

	        {
	            getResponse().getWriter().write(String.valueOf(completevalue));
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	    }

	public String updateRiskValue()
	{
	    
	    log.info("[AssetRiskEvaluationAction] Enter method updateRiskValue ..");
	    try {
	    	assetRiskEvaluationService.modifyAssetRiskEvaluation(assetRiskEvaluation);
		    
		    List<String> fieldList = new ArrayList<String>();
			fieldList.add(assetRiskEvaluation.getAssetName()+"("+ assetRiskEvaluation.getAssetName() +")");
	        // 审计日志
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产风险值", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改资产风险值");
		    return SUCCESS;
		} catch (Exception e) {
			 this.errorMessageflg = 1;
				
			 return "errorMessage";
			
		}
	}
	
	/**
	 * <图表展示> <功能详细描述>
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	
	public String chartsReport(){
		String result="";
		HttpServletRequest request = super.getRequest();
		areid= request.getParameter("areid");
		falg=request.getParameter("falg");
		assetRiskEvaluation =assetRiskEvaluationService.queryInfoById(Integer.parseInt(areid));
		try {
			areName=assetRiskEvaluation.getAssetName();
			strChart=assetRiskEvaluationService.charts(Integer.parseInt(areid), falg);
			if(falg.equals("1")){
				result="report";
			}else{
				result="report1";
			}
			log.info(strChart);
			return result;
		} catch (Exception e) {
			 this.errorMessageflg = 1;
				
			 return "errorMessage";
		}
		
	}
	
	
	
	
	
public String ExportReportARE(){
		
		
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		svgConvertJpeg(ServletActionContext.getRequest(), securityReportId);
		HttpServletResponse resp = ServletActionContext.getResponse();
		boolean isPdf = false;// 判断此类型是不是pdf
		if ("pdf".equals(reportType)) {
			isPdf = true;
		}
		try {
			createReportFile(securityReportId, reportType, isPdf);
		} catch (Exception e) {
			 this.errorMessageflg = 1;
				
			 return "errorMessage";
		}
		
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
	private void createReportFile(long auditReportId, String reportType,
			boolean isPdf) {
		Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		FreeMarkerUtil fm = new FreeMarkerUtil();
		HttpServletRequest request = super.getRequest();
		assetRiskEvaluation =assetRiskEvaluationService.queryInfoById(Integer.parseInt(areid));
		areName=assetRiskEvaluation.getAssetName();
	
		
		mapStatic.putAll(assetRiskEvaluationService.getFreemakerMapExport(
				auditReportId, path+"reportFile" ,reportType));
		mapStatic.put("assetRiskEvaluation", assetRiskEvaluation);
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
			fileName = java.net.URLDecoder.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E9%25A3%258E%25E9%2599%25A9%25E8%25AF%2584%25E4%25BC%25B0%25E5%259B%25BE", "GBK")+"." + reportType;
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
	public AssetRiskEvaluationService getAssetRiskEvaluationService() {
		return assetRiskEvaluationService;
	}
	public void setAssetRiskEvaluationService(
			AssetRiskEvaluationService assetRiskEvaluationService) {
		this.assetRiskEvaluationService = assetRiskEvaluationService;
	}
	public List<AssetRiskEvaluation> getAssetRiskEvaluations() {
		return assetRiskEvaluations;
	}
	public void setAssetRiskEvaluations(
			List<AssetRiskEvaluation> assetRiskEvaluations) {
		this.assetRiskEvaluations = assetRiskEvaluations;
	}

	

    public AssetRiskEvaluation getAssetRiskEvaluation() {
		return assetRiskEvaluation;
	}

	public void setAssetRiskEvaluation(AssetRiskEvaluation assetRiskEvaluation) {
		this.assetRiskEvaluation = assetRiskEvaluation;
	}

	public AssetService getAssetManager()
    {
        return assetManager;
    }

    public void setAssetManager(AssetService assetManager)
    {
        this.assetManager = assetManager;
    }

    public List<Asset> getAssetList()
    {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList)
    {
        this.assetList = assetList;
    }

	public String getAreid() {
		return areid;
	}

	public void setAreid(String areid) {
		this.areid = areid;
	}

	public String getFalg() {
		return falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorMessageflg() {
		return errorMessageflg;
	}

	public void setErrorMessageflg(int errorMessageflg) {
		this.errorMessageflg = errorMessageflg;
	}

	
}
