package com.soc.webapp.action.monitor;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.struts2.ServletActionContext;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.monitor.CustomTrend;
import com.soc.model.monitor.TrendVO;
import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.monitor.CustomTrendService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FreeMarkerUtil;
import com.util.HTMLToPDF;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CustomTrendAction extends BaseAction {
	private CustomTrendService customTrendManager;
	 //内部审计
    private AuditService auditManager;
    private CustomTrend customTrend;
    private List<CustomTrend> ctList;
    private AssetService assetManager;// 资产管理类
 // 用于查询的关键字
 	private String keyword;
 	private String ids;
 	private String trendId;
 	private String treeBuff;
 	private String trendName;
 	private String chartsBuffx;//曲线图x轴数据
 	private String chartsBuff;//曲线图数据
 	private List<TrendVO> voList;
 	private static Map<String, Object> mapStatic = new HashMap<String, Object>();
 	private String reportType;
 	private long securityReportId;
 	public String queryCustomTrend(){
 		log.info("[CustomTrendAction] Enter method query()...");
 		HttpServletRequest request = super.getRequest();

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
			Map map = new HashMap();
			// 模糊查询
			if (request.getParameter("keyword") != null) {
				try {
					keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				map.put("keyword", keyword);
			}
			long parentId = 1;
			map.put("parentId", parentId);
			sr = customTrendManager.query(map, page);
			if(sr!=null){
				ctList= sr.getList();
				request.setAttribute("Page", sr.getPage());
			} else {
				request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
				
			}
 		
 		return SUCCESS;
 	}
    public String edit(){
    	if(StringUtil.isNotEmpty(trendId)){
    		customTrend = customTrendManager.queryById(Long.parseLong(trendId));
    	}
    	return SUCCESS;
    }
    public String queryEvents(){//李长红修改，把原来的变量xChartsBuff改成chartsBuffx
    	long groupid = ((User) this.getSession().getAttribute(
				"SOC_LOGON_USER")).getAssetGroupid();
		String assetId = assetManager.queryIDSByUser(groupid);
    	Map map = customTrendManager.queryEvents(trendId,assetId);
    	String[] chartsStr = map.get("actionString").toString().split("_");
    	chartsBuffx = chartsStr[0];
    	chartsBuff = chartsStr[1];
    	voList = (List<TrendVO>) map.get("VO");
    	return SUCCESS;
    }
    public void delete(){
    	if(StringUtil.isNotEmpty(ids)){
    		String [] id = ids.split(",");
    		for (String ctId : id) {
    			customTrend = customTrendManager.queryById(Long.parseLong(ctId));
    			customTrendManager.delete(Long.parseLong(ctId));
    			List<String> fieldList = new ArrayList<String>();
    			fieldList.add(customTrend.getTrendName() + "("
    					+ customTrend.getTrendName() + ")");
    			auditManager.insertByDeleteOperator(((User) this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(), "趋势分析", super
    					.getRequest().getRemoteAddr(), fieldList);

    			// syslog
    			/*String logString = "";

    			logString = "登录名:"
    					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
    							.getUserLoginName() + " 源IP:"
    					+ getRequest().getRemoteAddr() + " 操作时间:"
    					+ DateUtil.curDateTimeStr19() + " 操作类型 :删除趋势分析";
    			logManager.writeSystemAuditLog(logString);*/
    			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
    					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除趋势分析");
			}
    	}
    	try {
    		String script = "<script language=javascript>parent.parent.leftFrame.location.href = "
					+ "'/soc/monitorGroup/showGroupTree.action';"+
    				"parent.parent.mainFrame.location.href = "
					+ "'/soc/customTrend/query.action';"
					 + "</script>";

			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void queryAllEvents(){
    	HttpServletRequest request = super.getRequest();
		Page page = null;
		int Num = 1;
		SearchResult<Events> sr = null;
		long groupid = ((User) this.getSession().getAttribute(
				"SOC_LOGON_USER")).getAssetGroupid();
		String assetId = assetManager.queryIDSByUser(groupid);
		String startIndex = request.getParameter("startIndex");
	
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
			Num += Integer.valueOf(startIndex);
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		sr=customTrendManager.queryAllEvents(Long.parseLong(trendId),page,assetId);
		List<Events> eventList = sr.getList();
		if (eventList != null) {
			eventList = (List<Events>) sr.getList();

			for (int i = 0; i < eventList.size(); i++) {
				String name = eventList.get(i).getName();
				String type = eventList.get(i).getType();

				try {

					long temp = Long.valueOf(name);
					String eventsName=GlobalConfig.eventTypeTag.get(temp);
					if(eventsName.equals(null)&&!eventsName.equals("")){
						eventList.get(i).setName(
								eventsName);
					}
					

					eventList.get(i).setType(
							GlobalConfig.eventTypeTag.get(Long.valueOf(type)));
				} catch (Exception e) {
					log.warn("格式转换异常");
				}
				try {

					eventList.get(i).setType(
							GlobalConfig.eventTypeTag.get(Long.valueOf(type)));
				} catch (Exception e) {
					log.warn("格式转换异常");
				}
			}

		}
		if (StringUtil.isNotBlank(startIndex)) {

			try {
				// 转换为JSON数据结构
				JSONArray jsonArray = JSONArray.fromObject(eventList);

				jsonArray.add(page);

				// Ajax返回
				getResponse().getWriter().write(jsonArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    public void save(){
    	List<String> fieldList = new ArrayList<String>();
		fieldList.add(customTrend.getTrendName() + "("
				+ customTrend.getTrendName() + ")");
		User user= (User) this.getSession().getAttribute("SOC_LOGON_USER");
    	if(customTrend.getTrendId()>0){
    		customTrend.setTrendUpdateDateTime(new Date());
    		customTrend.setTrendUpdateUser(user.getUserLoginName());
    		if(StringUtil.isNotEmpty(customTrend.getStartTime())){
    			customTrend.setDays(0);
    		}
    		customTrendManager.update(customTrend);
    		auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "趋势分析", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ user.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :修改趋势分析";
			logManager.writeSystemAuditLog(logString);*/
    		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改趋势分析");
    	}else{
    		customTrend.setTrendCreateDateTime(new Date());
    		customTrend.setTrendUpdateDateTime(new Date());
    		customTrend.setTrendCreateUser(user.getUserLoginName());
    		customTrend.setTrendUpdateUser(user.getUserLoginName());
    		customTrendManager.insert(customTrend);
    		auditManager.insertByInsertOperator(user.getUserId(), "趋势分析", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ user.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :添加趋势分析";
			logManager.writeSystemAuditLog(logString);*/
    		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增趋势分析");
    	}
    	try {
    		String script = "<script language=javascript>parent.parent.leftFrame.location.href = "
					+ "'/soc/monitorGroup/showGroupTree.action';"+
    				"parent.parent.mainFrame.location.href = "
					+ "'/soc/customTrend/query.action';"
					 + "</script>";
			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void checkName(){
    	

		// 标识此策略名是否存在
		String flag = "false";

		if (StringUtil.isNotBlank(trendName)) {
			customTrend = customTrendManager.queryByName(trendName);
			if (customTrend!=null) {
				flag = "true";
			}
		}
		// 将flag返回给页面
		try {
			getResponse().getWriter().write(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void ExportReport(){
    	String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		svgConvertJpeg(ServletActionContext.getRequest(), securityReportId);
		HttpServletResponse resp = ServletActionContext.getResponse();
		long groupid = ((User) this.getSession().getAttribute(
				"SOC_LOGON_USER")).getAssetGroupid();
		String assetId = assetManager.queryIDSByUser(groupid);
		boolean isPdf = false;// 判断此类型是不是pdf
		if ("pdf".equals(reportType)) {
			isPdf = true;
		}
		createReportFile(securityReportId, reportType, isPdf,assetId);
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
		case 15:
			fileName = java.net.URLDecoder.decode("%25E8%25B6%258B%25E5%258A%25BF%25E5%2588%2586%25E6%259E%2590%25E6%258A%25A5%25E8%25A1%25A8","GBK")+"."+reportType;
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
			boolean isPdf,String assetId) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		FreeMarkerUtil fm = new FreeMarkerUtil();
		HttpServletRequest request = super.getRequest();
		Map map = new HashMap();
		map = customTrendManager.queryEvents(trendId,assetId);
		voList = (List<TrendVO>) map.get("VO");
		mapStatic.put("voList", voList);
		mapStatic.put("time", sdf.format(new Date()));
		mapStatic.putAll(customTrendManager.getFreemakerMapExport(auditReportId,  path+"reportFile", reportType));
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
			if (auditReportId >= 6 && auditReportId <= 20) {
				fm.loadTemplate("freemarker" + reportType + auditReportId
						+ ".ftl", mapStatic, "template", out);// 生成文件的代码
			} else {
				fm.loadTemplate("freemarkerhtml" + auditReportId + ".ftl",
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
    
    
    
	public CustomTrendService getCustomTrendManager() {
		return customTrendManager;
	}
	public void setCustomTrendManager(CustomTrendService customTrendManager) {
		this.customTrendManager = customTrendManager;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	public CustomTrend getCustomTrend() {
		return customTrend;
	}
	public void setCustomTrend(CustomTrend customTrend) {
		this.customTrend = customTrend;
	}
	public List<CustomTrend> getCtList() {
		return ctList;
	}
	public void setCtList(List<CustomTrend> ctList) {
		this.ctList = ctList;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getTrendId() {
		return trendId;
	}
	public void setTrendId(String trendId) {
		this.trendId = trendId;
	}
	public String getTreeBuff() {
		return treeBuff;
	}
	public void setTreeBuff(String treeBuff) {
		this.treeBuff = treeBuff;
	}
	public String getTrendName() {
		return trendName;
	}
	public void setTrendName(String trendName) {
		this.trendName = trendName;
	}
	
	public String getChartsBuffx() {
		return chartsBuffx;
	}
	public void setChartsBuffx(String chartsBuffx) {
		this.chartsBuffx = chartsBuffx;
	}
	public String getChartsBuff() {
		return chartsBuff;
	}
	public void setChartsBuff(String chartsBuff) {
		this.chartsBuff = chartsBuff;
	}
	public List<TrendVO> getVoList() {
		return voList;
	}
	public void setVoList(List<TrendVO> voList) {
		this.voList = voList;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public long getSecurityReportId() {
		return securityReportId;
	}
	public void setSecurityReportId(long securityReportId) {
		this.securityReportId = securityReportId;
	}
	public AssetService getAssetManager() {
		return assetManager;
	}
	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
    
    
    
}
