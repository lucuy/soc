package com.soc.webapp.action.knowledgemanger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.soc.model.knowledge.Security;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.knowledge.SecurityService;
import com.soc.service.knowledge.exportsecurity.ExcelSecurity;
import com.soc.service.knowledge.importLeak.ImportSecurityExcel;
import com.soc.service.knowledge.parse.Parse;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FileUtil;
import com.util.FreeMarkerUtil;
import com.util.HTMLToPDF;
import com.util.MyException;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <显示安全公告列表,添加安全公告> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SecurityBulletinAction extends BaseAction {

	// SecurityServer类
	private SecurityService securityManager;

	// 前台传过来的id 用于显示详细信息
	private int id;

	// 传过来的字符串数组 用来批量删除
	private String ids;

	// 传过来的字符串数组 用来导出选中的
	private String checkids;
	
	// 用来显示详细信息实体封装类
	private Security security;

	// 显示列表的list
	private List<Security> list;

	// 用于查询的关键字
	private String keyword;

	//高级查询的ID
	private String messageSecurityId;
	
	//高级查询的标题
	private String messageSecurityTitle;
	
	//高级查询的发布人
	private String messagePublisher;
	
	//高级查询的发布时间
	private String messageSecurityDate;
	
	//高级查询的来源
	private String messageSource;
	
	// 后台验证的结果标签
	private boolean flg;
	
	 //请求的action字符串
    private String actionStr ="querySecurityBulletin.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ;
    
    //文件名
	private String upTarFileName; 

	//上传的文件
	private File upTar;
	
	// 审计业务管理类
	private AuditService auditManager;
	
	private static Map<String, Object> mapStatic = new HashMap<String, Object>();
	// 用来存放导出的报表类型,这里直接用String 为了是生成文件名的时候方便
	private String reportType;
	private boolean exist;// 文件是否存在
	private long securityReportId;
	private String falg;//判断是否包含描述：1代表未包含，0代表为包含
	private String info;
	private ImportSecurityExcel importSecurityExcel;
	/**
	 * <显示列表> <有分页功能>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String querySecurityBulletin() {
		log.info("[querySecurityBulletinAction] enter querySecurityBulletin()");
		
		
		// 对前台获得的等级和关键字中文编码进行处理
		this.trimString(keyword);
		this.trimString(messagePublisher);
		this.trimString(messageSecurityDate);
		this.trimString(messageSecurityId);
		this.trimString(messageSecurityTitle);
		this.trimString(messageSource);
		
		
		// 对分页的处理
		Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult sr = null;

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
		// 用来存放查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(keyword)){
		try {
			keyword = java.net.URLDecoder.decode(keyword,"UTF-8") ;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		map.put("keyword", keyword);
		}
		if(StringUtil.isNotEmpty(messagePublisher)){
			try {
				messagePublisher = java.net.URLDecoder.decode(messagePublisher,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			map.put("messagePublisher", messagePublisher);
		}
		if(StringUtil.isNotEmpty(messageSecurityTitle)){
			try {
				messageSecurityTitle = java.net.URLDecoder.decode(messageSecurityTitle,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			map.put("messageSecurityTitle", messageSecurityTitle);
		}
		if(StringUtil.isNotEmpty(messageSource)){
			try {
				messageSource = java.net.URLDecoder.decode(messageSource,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			map.put("messageSource", messageSource);
		}
		
		map.put("messageSecurityDate", messageSecurityDate);
		map.put("messageSecurityId", messageSecurityId);
		
		sr = securityManager.querySecurity(map, page);
		if (sr != null) {
			list = (List<Security>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		}

		return SUCCESS;
	}
	
	
	/**
	 * <公告的导入> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String importSecurity() {

		log.info("[SecurityBulletinAction] Enter method importSecurity.....");
		log.info(upTar);
		if (upTar != null && upTarFileName != null) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/import");

			File importXML = new File(new File(path), upTarFileName);

			try {
				FileUtil.copyFile(upTar, importXML);
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<Security> list = new ArrayList<Security>();

			list = Parse.parse(importXML);
			Map<String, Object> map=new HashMap<String, Object>();
			
			for (int i = 0; i < list.size(); i++) {
				map.put("keyword", list.get(i).getSecurityTitle());
				List<Security> securityList = securityManager.queryAllSecurity(map);
				if (securityList == null || securityList.size() == 0) {
					// 插入公告信息
					securityManager.insertSecuity(list.get(i));
				}
			}
			List<String> fieldList = new ArrayList<String>();
			fieldList.add("导入公告信息" + "("+ "导入公告信息" +")");
	        // 审计日志
			auditManager.insertBySystemOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "导入公告信息", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "导入安全公告");
		}
		return SUCCESS;
	}
	
	 /**
     * <导出excel表>
     * <把安全公告信息导出excel>
     * @see [ExcelAudit,StringUtil]
     */
    public void export()
    {
        LOG.info("[SEcurityBulletinAction] enter method export() ...");
        
        HttpServletResponse response = super.getResponse();
        ExcelSecurity excelsys = new ExcelSecurity();
        Map<String, String> limit = new HashMap<String, String>();
        if (StringUtil.isNotBlank(ids))
        {
            limit.put("ids", ids);
        }else{
        if (StringUtil.isNotBlank(keyword))
        {
            try
            {
                keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            limit.put("keyword", keyword);
        }
		
		if(StringUtil.isNotEmpty(messagePublisher)){
			try {
				messagePublisher = java.net.URLDecoder.decode(messagePublisher,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			limit.put("messagePublisher", messagePublisher);
		}
		if(StringUtil.isNotEmpty(messageSecurityTitle)){
			try {
				messageSecurityTitle = java.net.URLDecoder.decode(messageSecurityTitle,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			limit.put("messageSecurityTitle", messageSecurityTitle);
		}
		if(StringUtil.isNotEmpty(messageSource)){
			try {
				messageSource = java.net.URLDecoder.decode(messageSource,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			limit.put("messageSource", messageSource);
		}
		
		limit.put("messageSecurityDate", messageSecurityDate);
		limit.put("messageSecurityId", messageSecurityId);
        }
        excelsys.export(securityManager.securityExport(limit), "安全公告Excel报表");
        try
        {
            // 中文文件名需要编码
            String fileName = "securitylog_" + DateUtil.curDateStr8();
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment;Filename=" + fileName + ".xls");
            OutputStream os = response.getOutputStream();
            excelsys.getGwb().write(os);
            os.flush();
            os.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

	/**
	 * <用于批量删除> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String deleteSecurity() {
		log.info("[deleteSecurityAction] enter deleteSecurity()");
		securityManager.deleteSecurity(ids);
		
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("删除公告信息" + "("+ "删除公告信息" +")");
        // 审计日志
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "公告信息", super
				.getRequest().getRemoteAddr(), fieldList);
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除安全公告");
		return SUCCESS;
	}

	/**
	 * <插入新的安全公告> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String insertSecurity() {
		log.info("[insertSecurityAction] enter insertSecurity()");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		// 后代判断插入的的属性是否为空,有一个为空不插入并在前台显示值错误信息

		if (security.getPublisher().trim().equals("")
				|| security.getSecurityDetails().trim().equals("")
				|| security.getSecurityTitle().trim().equals("")
				|| security.getSource().trim().equals("")) {
			falg = "1";
			return "fail";
		} else {
			security.setSecurityCreateDate(Calendar.getInstance().getTime());
			String source =security.getSource();
			source=source.replace("\"", "'");
			security.setSource(source);
			String securityDetails = security.getSecurityDetails();
			securityDetails=securityDetails.replace("\"", "'");
			security.setSecurityDetails(securityDetails);
			securityManager.insertSecuity(security);
			
			List<String> fieldList = new ArrayList<String>();
			fieldList.add("添加公告信息" + "("+ "添加公告信息" +")");
	        // 审计日志
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "公告信息", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增安全公告");
			
			return SUCCESS;
		}

	}

	/**
	 * <更新安全公告> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String updateSecuritById() {

		// 后代判断插入的的属性是否为空,有一个为空不插入并在前台显示值错误信息

		if (security.getPublisher().trim().equals("")
				|| security.getSecurityDetails().trim().equals("")
				|| security.getSecurityTitle().trim().equals("")
				|| security.getSource().trim().equals("")) {
			falg = "1";
			id = (int) security.getSecurityId();
			return "fail";
		} else {
			String source =security.getSource();
			source=source.replace("\"", "'");
			security.setSource(source);
			String securityDetails = security.getSecurityDetails();
			securityDetails=securityDetails.replace("\"", "'");
			security.setSecurityDetails(securityDetails);
			securityManager.updateSecurity(security);
			
			List<String> fieldList = new ArrayList<String>();
			fieldList.add("更新公告信息" + "("+ "更新公告信息" +")");
	        // 审计日志
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "公告信息", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改安全公告");
			return SUCCESS;
		}

	}
	/**
	 * <跳转新增公告> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String toAdd(){
		HttpServletRequest request = super.getRequest();
		falg = request.getParameter("falg");
		if (StringUtil.isEmpty(falg)) {
			falg="1";
		}
		return SUCCESS;
	}
	/**
	 * <查询详细公告> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectSecuritById() {
		security = securityManager.selectSecurityByid(id);
		//System.out.println(security);
		return SUCCESS;
	}
	/**
	 * 导入excel
	 */
	/**
	 * <资产的导入> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String importSecurityExcel() {

		log.info("[AssetAction] Enter method importAsset.....");
		log.info(upTar);
		if (upTar != null && upTarFileName != null) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/import");

			File importExcel = new File(new File(path), upTarFileName);

			try {
				FileUtil.copyFile(upTar, importExcel);
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<Security> list = new ArrayList<Security>();
			String publisher = ((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserLoginName();
			try {

				list = importSecurityExcel.readRead(upTar);
				
				for (int i = 0; i < list.size(); i++) {
				Security sec = list.get(i);
				sec.setPublisher(publisher);
				securityManager.insertSecuity(sec);
				List<String> fieldList = new ArrayList<String>();
				fieldList.add("添加公告信息" + "("+ "添加公告信息" +")");
		        // 审计日志
				auditManager.insertByInsertOperator(((User) this.getSession()
						.getAttribute("SOC_LOGON_USER")).getUserId(), "公告信息", super
						.getRequest().getRemoteAddr(), fieldList);
				}
				logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "导入安全公告");
			} catch (MyException e) {

				info = e.getMessage();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				info = "alert";
			} catch (IOException e) {
				e.printStackTrace();
				info = "alert";
			}

		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 导出模板
	 */
	public void exportTEMP() {
		HttpServletResponse response = super.getResponse();
		String path = super.getSession().getServletContext()
				.getRealPath("/riskfile");
		String fileName = "securitylog_templet.xls";
		File file = new File(path, "/" + fileName);
		try {
			InputStream inputStream = new FileInputStream(file);
			// 中文文件名需要编码
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ fileName);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b)) > 0) {
				os.write(b, 0, len);

			}

			inputStream.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * <查询详细公告，并排序> 
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String sort(){
		LOG.info("[SecurityBulletinAction] enter method sort() ...");
        HttpServletRequest request = super.getRequest();
        
        Page page = null;
        SearchResult sr = null;
        
        HttpSession session = this.getSession() ; 
        int changeNum=0;  
        changeNum = session.getAttribute("CHANGENUM")==null ? 1:(Integer)session.getAttribute("CHANGENUM");
        
     // 处理数据分页的起始条数
        String startIndex = request.getParameter("startIndex");
        
        if (StringUtil.isNotBlank(startIndex))
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
        }
        else
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
        }
        
    	if(field != ""){
    		Map<String,String> map=new HashMap<String, String>();
    		int num = changeNum%2;
    		
    		if(num==0){
    			map.put("sortType", "DESC");
    		}else{
    			map.put("sortType", "ASC") ; 
    		}
    		if(sortType != null){
    			map.put("sortType", sortType);
    		}
    		map.put("field", field);
    		
    		actionStr = "field="+field+"&sortType="+map.get("sortType");
    		
    		sr = securityManager.queryBySort(map, page);
    		if (sr != null)
            {
                list = sr.getList();
                request.setAttribute("Page", sr.getPage());
            }
            else
            {
                request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
            }
    	}
    	changeNum++ ; 
    	session.setAttribute("CHANGENUM", changeNum);
    	return SUCCESS ; 
	}
	
	
	// 对前台获得的等级和关键字中文编码进行处理
	public String trimString(String str){
		if (str != null) {
			try {
				str = java.net.URLDecoder.decode(str, "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	public String ExportReport() {
		String path = getServletContext().getRealPath(File.separator);// 项目绝对路径
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
		InputStream in;// 读取本地文件的输入流
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
	
	private void createReportFile(long securityReportId, String reportType,
			boolean isPdf) {
		Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		FreeMarkerUtil fm = new FreeMarkerUtil();
		if (securityReportId==10) {
			Page page = null;
			HttpServletRequest request = super.getRequest();
			
			// 用来存放查询条件
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtil.isNotEmpty(ids)) {
				map.put("ids", ids);
			}else{
			if(StringUtil.isNotEmpty(keyword)){
			try {
				keyword = java.net.URLDecoder.decode(keyword,"UTF-8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			map.put("keyword", keyword);
			}
			if(StringUtil.isNotEmpty(messagePublisher)){
				try {
					messagePublisher = java.net.URLDecoder.decode(messagePublisher,"UTF-8") ;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				map.put("messagePublisher", messagePublisher);
			}
			if(StringUtil.isNotEmpty(messageSecurityTitle)){
				try {
					messageSecurityTitle = java.net.URLDecoder.decode(messageSecurityTitle,"UTF-8") ;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				map.put("messageSecurityTitle", messageSecurityTitle);
			}
			if(StringUtil.isNotEmpty(messageSource)){
				try {
					messageSource = java.net.URLDecoder.decode(messageSource,"UTF-8") ;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				map.put("messageSource", messageSource);
			}
			
			map.put("messageSecurityDate", messageSecurityDate);
			map.put("messageSecurityId", messageSecurityId);
			}
			list=securityManager.securityForExport(map);
			//对公告的描述做处理，将<p></p>标签去掉，不然导出doc打不开。
			for (Security security : list) {
				String desc = security.getSecurityDetails().replace("<p>", "").replace("</p>", "");
				security.setSecurityDetails(desc);
			}
			mapStatic.put("list",list);
		}
		
		if (isPdf) {// 如果是pdf,需要加载html模板
			reportType = "html";
		}
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(path+"reportFile"+File.separator
							+ getReportNameById(securityReportId, reportType))),
					"utf-8"));
			// map.put("image", new File(path +
			// "/reportFormImages/diag1.jpg"));//把图片加进去
			// 这里根据生成的文件类型加载不同的模板 因为以后report6到9导出doc的时候加载的是wrod模板 其他都是html模板
			// 这里要判断
			if (securityReportId >= 6 && securityReportId <= 10) {
				fm.loadTemplate("freemarkerSecurity" + reportType + securityReportId
						+ ".ftl", mapStatic, "template", out);// 生成文件的代码
			} else {
				fm.loadTemplate("freemarkerhtml" + securityReportId + ".ftl",
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
	
	private String getReportNameById(long securityReportId, String reportType) {
		String fileName = null;
		try {
		switch ((int) securityReportId) {
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
		case 6:
			fileName = java.net.URLDecoder.decode("%25E4%25BA%258B%25E4%25BB%25B6%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E6%259C%2588%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 7:
			fileName =java.net.URLDecoder.decode("%25E5%259F%259F%25E9%25A3%258E%25E9%2599%25A9%25E6%2598%258E%25E7%25BB%2586%25E6%258A%25A5%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		case 8:
			fileName = java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E7%25BB%25BC%25E5%2590%2588%25E5%2588%2586%25E6%259E%2590%25E5%2591%25A8%25E6%258A%25A5", "GBK")+"." + reportType;
			break;
		case 9:
			fileName =java.net.URLDecoder.decode("%25E8%25B5%2584%25E4%25BA%25A7%25E7%25B1%25BB%25E5%259E%258B%25E7%25BB%259F%25E8%25AE%25A1%25E6%258A%25A5%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		case 10:
			fileName =java.net.URLDecoder.decode("%25E5%25AE%2589%25E5%2585%25A8%25E5%2585%25AC%25E5%2591%258A%25E6%2595%25B0%25E6%258D%25AE%25E8%25A1%25A8", "GBK")+ "." + reportType;
			break;
		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}

	public SecurityService getSecurityManager() {
		return securityManager;
	}

	public void setSecurityManager(SecurityService securityManager) {
		this.securityManager = securityManager;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public List<Security> getList() {
		return list;
	}

	public void setList(List<Security> list) {
		this.list = list;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean getFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public String getActionStr() {
		return actionStr;
	}

	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}


	public String getUpTarFileName() {
		return upTarFileName;
	}


	public void setUpTarFileName(String upTarFileName) {
		this.upTarFileName = upTarFileName;
	}


	public File getUpTar() {
		return upTar;
	}


	public void setUpTar(File upTar) {
		this.upTar = upTar;
	}


	public String getMessageSecurityId() {
		return messageSecurityId;
	}


	public void setMessageSecurityId(String messageSecurityId) {
		this.messageSecurityId = messageSecurityId;
	}


	public String getMessageSecurityTitle() {
		return messageSecurityTitle;
	}


	public void setMessageSecurityTitle(String messageSecurityTitle) {
		this.messageSecurityTitle = messageSecurityTitle;
	}


	public String getMessagePublisher() {
		return messagePublisher;
	}


	public void setMessagePublisher(String messagePublisher) {
		this.messagePublisher = messagePublisher;
	}


	public String getMessageSecurityDate() {
		return messageSecurityDate;
	}


	public void setMessageSecurityDate(String messageSecurityDate) {
		this.messageSecurityDate = messageSecurityDate;
	}


	public String getMessageSource() {
		return messageSource;
	}


	public void setMessageSource(String messageSource) {
		this.messageSource = messageSource;
	}


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


	public String getCheckids() {
		return checkids;
	}


	public void setCheckids(String checkids) {
		this.checkids = checkids;
	}


	public AuditService getAuditManager() {
		return auditManager;
	}


	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}


	public String getFalg() {
		return falg;
	}


	public void setFalg(String falg) {
		this.falg = falg;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public ImportSecurityExcel getImportSecurityExcel() {
		return importSecurityExcel;
	}


	public void setImportSecurityExcel(ImportSecurityExcel importSecurityExcel) {
		this.importSecurityExcel = importSecurityExcel;
	}


	
	
	

}
