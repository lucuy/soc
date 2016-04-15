package com.soc.webapp.action.audit;

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

import com.soc.model.eventtypetag.eventcategorytag;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditReportService;
import com.soc.service.eventtypetag.EventCategoryTagService;
import com.soc.webapp.action.BaseAction;
import com.util.FreeMarkerUtil;
import com.util.HTMLToPDF;
import com.util.treeview.AssetGroupTree;

/**
 * 
 * <显示报表树> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AuditReportAction extends BaseAction {

	// 外部审计报表id
	private long auditReportId;
	// 拼接出树的html代码  用在事件综合月报自定义条件中 展示资产组树
	private String htmlBuffer;
	// 外部审计服务类
	private AuditReportService auditReportManager;
	private static Map<String, Object> mapStatic = new HashMap<String, Object>();
	// 用来存放导出的报表类型,这里直接用String 为了是生成文件名的时候方便
	private String reportType;
	private boolean exist;// 文件是否存在
	private String keyword;
	private AssetService assetManager;
	// 资产组业务处理类
	private AssetGroupService assetGroupManager;
	private long groupId;
	private String assetIds = "0";//自定义综合事件查询条件的id串
	private String customs;//自定义综合事件查询自定义标识 1代表带着查询查询条件
	private String beginTime;//开始时间
	private String endTime;//结束时间
	//事件类别列表
    private List<eventcategorytag> eventCategoryTagList;
    //事件类别service
    private EventCategoryTagService eventcategoryTagManager;
    //自定义报表传过来的事件类型的id串1,2,3
	private String categoryIds;
	/**
	 * <显示左侧树> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String displayLeftManagetree() {
		log.info("[AuditReportAction] enter displayLeftManagetree");
		String objectPath = super.getRequest().getContextPath();
		// isOnclick = "true";

		htmlBuffer = auditReportManager.getTreeStyle(objectPath);

		return SUCCESS;

	}

	/**
	 * <根据id查询报表> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryReportByAuditReportId() {
		log.info("[AuditReportAction] enter queryReportByAuditReportId");
		mapStatic.clear();// 把全局的清空
		long groupid = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getAssetGroupid();
		String assetId = null;
		if (groupid == 1) {

		//} else {
			assetId = assetManager.queryIDSByUser(groupid);
			if (assetId.equals("")) {
				assetId = "0";
			}

		}
		// 获的项目根目录，传输路径(在模板中得到路径)
		String path = getServletContext().getRealPath(File.separator);
		// System.out.println(auditReportId);
	/*	private String assetIds;//自定义综合事件查询条件的id串
		private String customs;//自定义综合事件查询自定义标识 1代表带着查询查询条件
		private String beginTime;//开始时间
		private String endTime;//结束时间
*/		mapStatic = auditReportManager.getFreemakerMapPageCustoms(auditReportId,
				assetId, groupid,assetIds+"0",customs,beginTime,endTime,categoryIds);
		mapStatic.put("path", path);
		mapStatic.put("titleBgcolor", "#0099dd");
		mapStatic.put("textColor", "");

		HttpServletResponse response = this.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		Writer out;
		try {
			FreeMarkerUtil fm = new FreeMarkerUtil();
			out = response.getWriter();
			fm.loadTemplate("freemarker" + auditReportId + ".ftl", mapStatic,
					"template", out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String ExportReport() {
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		svgConvertJpeg(ServletActionContext.getRequest(), auditReportId);
		HttpServletResponse resp = ServletActionContext.getResponse();
		boolean isPdf = false;// 判断此类型是不是pdf
		if ("pdf".equals(reportType)) {
			isPdf = true;
		}
		createReportFile(auditReportId, reportType, isPdf);
		if (isPdf) {
			String inputPath = path + "reportFile" + File.separator
					+ getReportNameById(auditReportId, "html");
			String outputPath = path + "reportFile" + File.separator
					+ getReportNameById(auditReportId, "pdf");
			String imagePath = path + "reportFile" + File.separator;
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
		InputStream in;// 读取本地文件的输入流
		// 获得本地输入流
		String fileName = getReportNameById(auditReportId, reportType);
		File file = new File(path + "reportFile" + File.separator + fileName);
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

	public String reportFileIsExist() {
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
		File file = new File(path + "reportFile" + File.separator
				+ getReportNameById(auditReportId, reportType));
		if (file.exists()) {
			exist = true;
		} else {
			exist = false;
		}
		return SUCCESS;
	}

	private void createReportFile(long auditReportId, String reportType,
			boolean isPdf) {
		Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		FreeMarkerUtil fm = new FreeMarkerUtil();
		mapStatic.putAll(auditReportManager.getFreemakerMapExport(
				auditReportId, path + "reportFile", reportType));

		if (isPdf) {// 如果是pdf,需要加载html模板
			reportType = "html";
		}
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path + "reportFile"
							+ File.separator
							+ getReportNameById(auditReportId, reportType))),
					"utf-8"));
			// map.put("image", new File(path +
			// "/reportFormImages/diag1.jpg"));//把图片加进去
			// 这里根据生成的文件类型加载不同的模板 因为以后report6到9导出doc的时候加载的是wrod模板 其他都是html模板
			// 这里要判断
			if (reportType.equals("xls")) {
				fm.loadTemplate("freemarker" + reportType + auditReportId
						+ ".ftl", mapStatic, "template", out);// 生成文件的代码
			} else if (auditReportId >= 6 && auditReportId <= 12) {
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

	/**
	 * 显示资产组树 <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String customs() {
		try {
			AssetGroupTree assetGroupTree = new AssetGroupTree(
					assetGroupManager, super.getRequest().getContextPath());
			groupId = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getAssetGroupid();
			this.setHtmlBuffer(assetGroupTree.displayZTree(groupId,assetManager));
			// System.out.println(this.getHtmlBuffer());
			//查询事件类型
			eventCategoryTagList = eventcategoryTagManager.query();

		} catch (UnsupportedEncodingException e) {
			log.error("[EmployeeGroupAction] unsupport encoding exception.", e);
		} catch (IOException e) {
			log.error("[EmployeeGroupAction] io excepion.", e);
		}
		return SUCCESS;
	}

	public String getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(String htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}

	public AuditReportService getAuditReportManager() {
		return auditReportManager;
	}

	public void setAuditReportManager(AuditReportService auditReportManager) {
		this.auditReportManager = auditReportManager;
	}

	public long getAuditReportId() {
		return auditReportId;
	}

	public void setAuditReportId(long auditReportId) {
		this.auditReportId = auditReportId;
	}

	/**
	 * <SVG转换JPEG> <功能详细描述>
	 * 
	 * @param request
	 * @param realUrl
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
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
			String imgPath = getServletContext().getRealPath(File.separator)
					+ File.separator + "reportFile";

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
				fileName = java.net.URLDecoder
						.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25AE%25A1%25E7%2590%2586%25E5%25B9%25B3%25E5%258F%25B0%25E6%259C%2588%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 3:
				fileName = java.net.URLDecoder
						.decode("%25E5%25AE%2589%25E5%2585%25A8%25E8%25BF%2590%25E8%25A1%258C%25E7%258A%25B6%25E6%2580%2581%25E6%258A%25A5%25E5%25AE%258C%25E6%2588%2590%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 4:
				fileName = java.net.URLDecoder
						.decode("%25E6%25BC%258F%25E6%25B4%259E%25E6%2589%25AB%25E6%258F%258F%25E6%258A%25A5%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 5:
				fileName = java.net.URLDecoder
						.decode("%25E8%25AE%25BE%25E5%25A4%2587%25E6%2598%258E%25E7%25BB%2586%25E7%25BB%259F%25E8%25AE%25A1%25E6%2597%25A5%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 6:
				fileName = java.net.URLDecoder
						.decode("%25E4%25BA%258B%25E4%25BB%25B6%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E6%259C%2588%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 7:
				fileName = java.net.URLDecoder
						.decode("%25E5%259F%259F%25E9%25A3%258E%25E9%2599%25A9%25E6%2598%258E%25E7%25BB%2586%25E6%258A%25A5%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 8:
				fileName = java.net.URLDecoder
						.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E5%2591%25A8%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 9:
				fileName = java.net.URLDecoder
						.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E7%25B1%25BB%25E5%259E%258B%25E7%25BB%259F%25E8%25AE%25A1%25E6%258A%25A5%25E8%25A1%25A8",
								"GBK")
						+ "." + reportType;
				break;
			case 10:
				fileName = java.net.URLDecoder
						.decode("%25E9%2598%25B2%25E6%258A%25A4%25E5%25A2%2599%25E4%25BA%258B%25E4%25BB%25B6%25E5%2591%25A8%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 11:
				fileName = java.net.URLDecoder
						.decode("%25E9%2598%25B2%25E6%258A%25A4%25E5%25A2%2599%25E4%25BA%258B%25E4%25BB%25B6%25E6%259C%2588%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 12:
				fileName = java.net.URLDecoder
						.decode("%25E5%2591%258A%25E8%25AD%25A6%25E7%25BB%259F%25E8%25AE%25A1%25E5%2591%25A8%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break;
			case 13:
				fileName = java.net.URLDecoder
						.decode("%25E9%2599%2595%25E8%25A5%25BF%25E5%258F%25B0%25E5%25AE%2589%25E7%25AE%25A1%25E5%25B9%25B3%25E5%258F%25B0%25E8%25AE%25BE%25E5%25A4%2587%25E5%2591%258A%25E8%25AD%25A6%25E6%2597%25A5%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break; // %25E9%2599%2595%25E8%25A5%25BF%25E5%258F%25B0%25E5%25AE%2589%25E5%25AE%2598%25E5%25B9%25B3%25E5%258F%25B0%25E8%25AE%25BE%25E5%25A4%2587%25E5%2591%258A%25E8%25AD%25A6%25E5%2591%25A8%25E6%258A%25A
						// 周报
			case 14:
				fileName = java.net.URLDecoder
						.decode("%25E9%2599%2595%25E8%25A5%25BF%25E5%258F%25B0%25E5%25AE%2589%25E7%25AE%25A1%25E5%25B9%25B3%25E5%258F%25B0%25E4%25BA%258B%25E4%25BB%25B6%25E5%2591%25A8%25E6%258A%25A5",
								"GBK")
						+ "." + reportType;
				break; // 周报

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 * <把long形式ip变成标准形式> <功能详细描述>
	 * 
	 * @param lipAdd
	 * @return
	 * @see [类、类#方法、类#成员]
	 */

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getAssetIds() {
		return assetIds;
	}

	public void setAssetIds(String assetIds) {
		this.assetIds = assetIds;
	}

	public String getCustoms() {
		return customs;
	}

	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<eventcategorytag> getEventCategoryTagList() {
		return eventCategoryTagList;
	}

	public void setEventCategoryTagList(List<eventcategorytag> eventCategoryTagList) {
		this.eventCategoryTagList = eventCategoryTagList;
	}

	public EventCategoryTagService getEventcategoryTagManager() {
		return eventcategoryTagManager;
	}

	public void setEventcategoryTagManager(
			EventCategoryTagService eventcategoryTagManager) {
		this.eventcategoryTagManager = eventcategoryTagManager;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}


}
