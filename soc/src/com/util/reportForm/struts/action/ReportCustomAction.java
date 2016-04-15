package com.util.reportForm.struts.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.soc.model.conf.GlobalConfig;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.ExtendedActionServlet;
import com.util.reportForm.datadeal.model.ReportCustom;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ReportExportModel;
import com.util.reportForm.struts.form.ReportCustomForm;
import com.util.reportForm.util.JDBC;
import com.util.reportForm.util.export.Realisezip;
import com.util.reportForm.util.export.ReportExportDoc;
import com.util.reportForm.util.export.ReportExportHTML;
import com.util.reportForm.util.export.ReportExportPdf;
import com.util.reportForm.util.export.ReportExportTxt;
import com.util.reportForm.util.export.reportCustom.ExportCustom;
import com.util.reportForm.util.export.reportCustom.ImportCustom;
import com.util.reportForm.util.hibernate.hibernateUtil.HibernateUtil;
import com.util.reportForm.util.page.Pager;


import java.io.UnsupportedEncodingException;
import java.util.Collection;

import jxl.Workbook;
import jxl.write.Label;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.model.ColConversion;
import com.util.reportForm.struts.form.DataSource;

/**
 * 自定义报表action
 * @author zsa
 *
 */
public class ReportCustomAction extends DispatchAction {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private BaseDao dao = new BaseDao();
	private Pager pager = new Pager();
	public InputStream is;
	public BufferedInputStream bis;
	public OutputStream os;
	public BufferedOutputStream bos;
	byte[] data = new byte[1024];
	int i = 0;
	
	String pathTXT = ExtendedActionServlet.setupPath + "downloads/report.txt";
	String pathXLS = ExtendedActionServlet.setupPath + "downloads/report.xls";
	String pathCSV = ExtendedActionServlet.setupPath + "downloads/report.csv";
	String pathHTML = ExtendedActionServlet.setupPath + "downloads/report.html";
	String pathDOC = ExtendedActionServlet.setupPath + "downloads/report.doc";
	String pathPDF = ExtendedActionServlet.setupPath + "downloads/report.PDF";
	String pathHtmlZip = ExtendedActionServlet.setupPath + "downloads/report.zip";
	/**
	 * 列表分页显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward initPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportCustomForm rcf=(ReportCustomForm)form;
		List<ReportCustom> list=null;
		Map mapParam = new HashMap();
		String hql="from ReportCustom r where";
		
		// 模板名称
		String name = request.getParameter("name");
		
		// sql
		String customSql = request.getParameter("customSql");
		
		// 开始时间
		String startTime = request.getParameter("startTime");
		
		// 结束时间
		String endTime = request.getParameter("endTime");
		
		//快速搜索
		String keyword = request.getParameter("keyword");
		
		// 构造hql语句
		StringBuilder queryBuilder = new StringBuilder();
		
		// 模板名称
		if (null != name && !name.trim().equals("")) {
			queryBuilder.append(" r.name like :name and ");
			mapParam.put("name", "%" + name + "%");
		}

		// 模板类型
		if (null != customSql && !customSql.trim().equals("")) {
			queryBuilder.append(" r.customSql like :customSql and ");
			mapParam.put("customSql", "%" +customSql+ "%");
		}
		
//		
//		// 开始时间
//		if (null != startTime && !startTime.trim().equals("")) {
//			queryBuilder.append(" r.createDate>=:startTime and ");
//			mapParam.put("startTime", startTime);
//		}
//		
//		//结束时间
//		if (null != endTime && !endTime.trim().equals("")) {
//			queryBuilder.append(" r.createDate<=:endTime and ");
//			mapParam.put("endTime", endTime);
//		}
//		
		//快速搜索
		if((null != keyword && !keyword.equals(""))) {
			/*queryBuilder.append(" (r.name like :name or r.customSql like :customSql or r.createDate>=:startTime or r.createDate<=:endTime) and ");
			mapParam.put("name", "%" + keyword + "%");
			mapParam.put("customSql", "%" +keyword+ "%");
			mapParam.put("startTime", keyword);
			mapParam.put("endTime", keyword);*/
			queryBuilder.append(" (r.name like :name or r.customSql like :customSql) and ");
			mapParam.put("name", "%" + keyword + "%");
			mapParam.put("customSql", "%" +keyword+ "%");
		}
		queryBuilder.append(" 1=1 ");
		queryBuilder.append(" order by r.createDate desc");
		hql=hql+queryBuilder.toString();
		
		int startRow = 0;
		int endRow = 0;
		int totalRows = 0;
		int pageSize = 0;

		String action = request.getParameter("action");
		if (action == null || action.equals("null")) {
			pager = new Pager();
		}
		String pageS = request.getParameter("page");
		Integer page = 0;
		if (pageS != null && !pageS.equals("null")) {
			page = Integer.parseInt(pageS);
		}
		pager.setPageSize(15);
		Map mapParam1 = new HashMap();
		int count = dao.getQueryCount("from ReportCustom", mapParam1);
		list = (List<ReportCustom>) dao.getQueryResult(hql, mapParam,0,count);
		totalRows = list.size();

		pager.start(totalRows);
		pageSize = pager.getPageSize();

		if (action == null || action.equals("null")) {

			startRow = pager.getStartRow();
			if (totalRows > pageSize) {
				pager.setHasNextPage(true);
				pager.setEndRow(startRow + pageSize - 1);
			} else {
				pager.setEndRow(totalRows);
			}
			endRow = pager.getEndtRow();

			list = (List<ReportCustom>) dao.getQueryResult(hql, mapParam, startRow, pageSize);
		} else {
			if (action == "previousPage" || action.equals("previousPage")) {

				pager.previous();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				list = (List<ReportCustom>) dao.getQueryResult(hql, mapParam, startRow, pageSize);

			}
			if (action == "nextPage" || action.equals("nextPage")) {

				pager.next();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				list = (List<ReportCustom>) dao.getQueryResult(hql, mapParam, startRow, pageSize);

			}
			if (action == "first" || action.equals("first")) {

				pager.first();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				list = (List<ReportCustom>) dao.getQueryResult(hql, mapParam, startRow, pageSize);

			}
			if (action == "last" || action.equals("last")) {

				pager.last();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				list = (List<ReportCustom>) dao.getQueryResult(hql, mapParam, startRow, pageSize);
			}
			if (action == "findPage" || action.equals("findPage")) {

				pager.find(page);
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				list = (List<ReportCustom>) dao.getQueryResult(hql, mapParam, startRow, pageSize);
			}
		}
		request.setAttribute("page", pager);
		//将集合封装到form
		rcf.setList(list);
		return mapping.findForward("initPage");
	}
	
	/**
	 * 跳转到添加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		saveToken(request);// 防止重复提交
		return mapping.findForward("reportCustomAdd");
	}
	/**
	 * 添加一条数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportCustomForm rcf=(ReportCustomForm)form;
		ReportCustom rc=rcf.getRc();
		rc.setCreateDate(new Date());
		rc.setUpdateDate(new Date());
		if (isTokenValid(request, false)) {
			dao.save(rc);
		}
		return mapping.findForward("toList");
	}
	/**
	 * 跳转到更新页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportCustomForm rcf=(ReportCustomForm)form;
		ReportCustom rc=(ReportCustom)dao.get(ReportCustom.class, rcf.getRc().getId());
		rcf.setRc(rc);
		return mapping.findForward("reportCustomUpdate");
	}
	/**
	 * 执行更新操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportCustomForm rcf=(ReportCustomForm)form;
		ReportCustom rcold=(ReportCustom)dao.get(ReportCustom.class, rcf.getRc().getId());//创建时间
		ReportCustom rc=rcf.getRc();
		rc.setCreateDate(rcold.getCreateDate());
		rc.setUpdateDate(new Date());
		dao.saveOrUpdate(rc);
		return mapping.findForward("toList");
	}
	/**
	 * 执行删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String ids=request.getParameter("ids");
		if(ids!=null){
			String[] checked = ids.split(",");
			for (String checkid : checked) {
				ReportCustom rc=(ReportCustom)dao.get(ReportCustom.class, Integer.parseInt(checkid));
				dao.delete(rc);
			}
		}
		return mapping.findForward("toList");
	}
	/**
	 * 删除所有
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Session session = HibernateUtil.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			// 删除记录
			Query query = session.createQuery("delete from ReportCustom");
			query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}
		return mapping.findForward("toList");
	}
	/**
	 * 执行sql操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward excute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		ReportCustomForm rcf=(ReportCustomForm)form;
		int rcid =  rcf.getRc().getId();
		request.setAttribute("rcid", rcid);
		ReportCustom rc=(ReportCustom)dao.get(ReportCustom.class, rcf.getRc().getId());
		JDBC db = new JDBC();
		ResultSet rs;
		try {
			rs = db.getResultSet(rc.getCustomSql());
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
		    List<String> titles=new ArrayList<String>();
		    List<Map> val=new ArrayList<Map>();
		    Map rowData = new HashMap(columnCount); 
			while(rs.next()){
				 Map m=new HashMap();
				 for (int i=1; i<=columnCount; i++){
					 rowData.put(md.getColumnName(i),rs.getObject(i)); 
					 m.put(md.getColumnName(i), rs.getObject(i));
				 }
				 m.remove("EVENTS_DEVTYPE");
				 try{
				 long temp = Long.parseLong(m.get("EVENTS_TYPE").toString());
				 m.put("EVENTS_TYPE",GlobalConfig.eventTypeTag.get(temp));
				 }catch (Exception e) {
					
				}
				 val.add(m);//得到所有的数据
			}
			rowData.remove("EVENTS_DEVTYPE");
			Iterator it=rowData.keySet().iterator();
			while(it.hasNext()){
				titles.add(it.next().toString());//得到title
			}
			rcf.setTitles(titles);
			rcf.setVal(val);
			if(titles.size()==0){
				request.setAttribute("message", "无数据显示...");
			}
		} catch (Exception e1) {
			request.setAttribute("message", "sql语句拼写错误...请重新修改...");
		}finally{
			db.close();
		}
		return mapping.findForward("report");
	}
	
	/**
	 * 导出自定义组态报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward exportReportCustomForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ExportCustom exportCustom=new ExportCustom();
		// 生成zip文件
		exportCustom.export("customReport.xml","customReport.zip");
		File file = new File(ExtendedActionServlet.setupPath+"customReport.zip");
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			response.reset();
			// 设置为下载application/force-download
			response.setContentType("application/force-download");
			response.setHeader("Location",ExtendedActionServlet.setupPath+"customReport.zip");
			response.setHeader("Content-Disposition", "attachment; filename="+"customReport.zip");
			outputStream = response.getOutputStream();
			inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
		} catch (Exception e) {

		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
					inputStream.close();
					file.delete();
				}
			} catch (Exception e) {

			}
		}
		return null;
	}
	
	/**
	 * 跳转到上传页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toinport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("upload");
	}
	
	/**
	 * 导入自定义组态报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward inportReportForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String rootPath = ExtendedActionServlet.setupPath.substring(0,
				ExtendedActionServlet.setupPath.indexOf("webapps"));
	
		String path1 = rootPath + "webapps\\soc\\tmp\\customReport.zip";
		try {
			Realisezip ie = new Realisezip();
			ie.realiseZipFile(path1, rootPath);
			File zip = new File(path1);
			if (zip.exists()) {
				zip.delete();
			}
			ImportCustom importCustom=new ImportCustom();
			//System.out.println(rootPath + "customReport.xml");
			importCustom.inport(rootPath + "customReport.xml");
			
			File file = new File(rootPath + "customReport.xml");
			if(file.exists()){
				file.delete();
			}
			request.setAttribute("message", "ok");
		} catch (Exception e) {
			request.setAttribute("mes", "文件导入失败！");
			e.printStackTrace();
			return mapping.findForward("upload");
		}
		return mapping.findForward("upload");
	}

	
	
	/**
	 * 报表导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<List<String>> list = new ArrayList<List<String>>();
		List<Reportformsinfo> reportInfoList = new ArrayList<Reportformsinfo>();
		
		ReportCustomForm rcf=(ReportCustomForm)form;
		int asdf =  rcf.getRc().getId();
		ReportCustom rc=(ReportCustom)dao.get(ReportCustom.class, rcf.getRc().getId());
		JDBC db = new JDBC();
		ResultSet rs = null;
		List<String> titles = null;
		List<Map> val = null;
		try {
			String sd = rc.getCustomSql();
			rs = db.getResultSet(rc.getCustomSql());
			log.debug("custom sql:" + rc.getCustomSql());
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
		    titles=new ArrayList<String>();
		    val=new ArrayList<Map>();
		    Map rowData = new HashMap(columnCount); 
		    
		    Integer titleLength = 820;
			Integer currentLength = 0;
			int k = 0;
			
			for (int i=1; i<=columnCount; i++){
				Reportformsinfo e = new Reportformsinfo(0L, md.getColumnName(i), "120", 1, 0, 0, "0");
				reportInfoList.add(e);
			}
			
			while(rs.next()){
				 Map m=new HashMap();
				 for (int i=1; i<=columnCount; i++){
					 rowData.put(md.getColumnName(i),rs.getObject(i)); 
					 m.put(md.getColumnName(i), rs.getObject(i));
				 }
				 val.add(m);//得到所有的数据
			}
			Iterator it=rowData.keySet().iterator();
			while(it.hasNext()){
				titles.add(it.next().toString());//得到title
			}
			rcf.setTitles(titles);
			rcf.setVal(val);
			if(titles.size()==0){
				request.setAttribute("message", "无数据显示...");
			}
		} catch (Exception e1) {
			request.setAttribute("message", "sql语句拼写错误...请重新修改...");
		}
		String geshi = request.getParameter("geshi");
		List<List<List<String>>> tableList = new ArrayList<List<List<String>>>();

		
		// 模板生成人

		String createUser = request.getParameter("createUser");
		// 报表导出人

		String exportUser = request.getParameter("exportUser");
		// 单位
		String company = request.getParameter("company");

		// 文件路径
		String path = "";
		// 文件下载格式
		String type = "";

		// 报表导出内容
		ReportExportModel model = new ReportExportModel();
		model.setReportTitle(rc.getName());
		model.setZipFileNameAndPath(pathHtmlZip);
		model.setExportUser(exportUser);
		model.setCompany(company);
		model.setCreateUser(createUser);
		model.setFilePath(path);
		model.setTableList(titles);
		model.setReportFormInfo(reportInfoList);
		
		String[] sqlList = new String[1];
		sqlList[0] = rc.getCustomSql();
		model.setSql(sqlList);

		int num = val.size();
		
		File f=new File(ExtendedActionServlet.setupPath + "downloads");
		if(!f.isDirectory()){
			f.mkdir();
		}else{
			if(f.listFiles().length>0){
				File[] files = f.listFiles();
		    	for (int i = 0; i < files.length; i++) {
					File file2 = files[i];
					file2.delete();
				}
	    	}
		}

		try {
			
			// 添加标题
			list.add(titles);
			if (geshi.equals("html")) {
				/*for(String title : titles) {
					Reportformsinfo e = new Reportformsinfo(0L, title, "120", 1, 0, 0, "0");
					reportInfoList.add(e);
				}*/
				tableList = getTableContent(rs, reportInfoList);
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					List<String> li = new ArrayList<String>();
					for (String title : titles) {
						li.add(rs.getString(title));
					}
					list.add(li);
				}
			}
		} catch (SQLException e) {
			db.closeAll();
			e.printStackTrace();
		}
		if (geshi.equals("xls")) {
			this.ExportExcel(list);
			path = pathXLS;
			type = "application/force-download";
		} else if (geshi.equals("csv")) {
			this.ExportTXT(list, pathCSV);
			path = pathCSV;
			type = "application/force-download";
		} else {
			// 设置报表信息
			if (geshi.equals("html")) {
				path = pathHTML;
				model.setFilePath(path);
				ReportExportHTML html = new ReportExportHTML();
				html.setModel(model);
				html.exportHTML();
				path = pathHtmlZip;
				geshi = "zip";
				type = "application/force-download";
			} else if (geshi.equals("doc")) {
				path = pathDOC;
				model.setFilePath(path);
				// 得到用户信息
				ReportExportDoc doc = new ReportExportDoc();
				// 设置导出doc参数
				doc.setModel(model);
				doc.exportDoc(); 
				geshi = "doc";
				type = "application/ms-word";
			} else if (geshi.equals("pdf")) {
				path = pathPDF;
				model.setFilePath(path);
				ReportExportPdf pdf = new ReportExportPdf();
				pdf.setModel(model);
				pdf.exportPdf();
				geshi = "pdf";
				type = "application/force-download";
			} else if (geshi.equals("txt")) {
				path = pathTXT;
				model.setFilePath(path);
				ReportExportTxt txt = new ReportExportTxt();
				txt.setModel(model);
				txt.exportTxt();
				type = "text/txt";
			}
		}
		
		// 导出文件
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			response.reset();
			response.setContentType(type);
			response.setHeader("Location", "report." + geshi);
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("report." + geshi, "UTF-8"));
			outputStream = response.getOutputStream();
			inputStream = new FileInputStream(path);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println(e.getMessage());
		} finally {

			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();

			}
			File file1 = new File(path);
			file1.delete();
			
			db.close();
			
		}
		
		return null;
	}

	/**
	 * excel导出
	 * @param list
	 */
	public void ExportExcel(List list) {
		try {
			OutputStream os = new FileOutputStream(pathXLS);
			WritableWorkbook workbook = Workbook.createWorkbook(os);// 创建工作薄

			WritableSheet worksheet = workbook.createSheet("报表1", 0);// 创建第一个工作表，name:工作表名称

			Label label = null;// 用于写入文本内容到工作表中去
			jxl.write.Number nmb = null;// 用于写入数值到工作表中去

			for (int i = 0; i < list.size(); i++) {
				List it = (List) list.get(i);
				for (int j = 0; j < it.size(); j++) {
					if (it.get(j) != null) {
						if (it.get(j).getClass().toString().equals(
								"class java.lang.String")
								|| it.get(j).getClass().toString().equals(
										"class java.util.Date")) {
							label = new Label(j, i, it.get(j).toString());// 参数依次代表列数、行数、内容
							worksheet.addCell(label);// 写入单元格
						}
						if (it.get(j).getClass().toString().equals(
								"class java.lang.Integer")) {
							Integer num_ = (Integer) it.get(j);
							int num = num_.intValue();
							nmb = new jxl.write.Number(j, i, num);
							worksheet.addCell(nmb);
						}
						if (it.get(j).getClass().toString().equals(
								"class java.lang.Long")) {
							Long num_ = (Long) it.get(j);
							int num = num_.intValue();
							nmb = new jxl.write.Number(j, i, num);
							worksheet.addCell(nmb);
						}
					} else {
						label = new Label(j, i, "");// 参数依次代表列数、行数、内容
						worksheet.addCell(label);// 写入单元格
					}
				}
			}
			workbook.write();
			workbook.close();
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * txt导出
	 * @param list
	 * @param path
	 */
	public void ExportTXT(List list, String path) {
		try {
			File file = new File(path);
			/*FileWriter fw = new FileWriter(file);
			PrintWriter bw = new PrintWriter(fw);
			int[] leng = this.getLengths(list);
			for (int i = 0; i < list.size(); i++) {
				List it = (List) list.get(i);
				bw.println(this.getLine(it, leng));
			}
			bw.flush();
			bw.close();*/
			FileOutputStream fos = new FileOutputStream(file, true);
			int[] leng = this.getLengths(list);
			for (int i = 0; i < list.size(); i++) {
				List it = (List) list.get(i);
				fos.write(this.getLine(it, leng).getBytes("gb2312"));
			}
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] getLengths(List list) {
		int[] lengths = new int[((List) list.get(0)).size()];
		for (int i = 0; i < lengths.length; i++) {
			lengths[i] = 0;
			for (int j = 0; j < list.size(); j++) {
				List it = (List) list.get(j);
				if (it.get(i) != null) {
					try {
						if (it.get(i).toString().getBytes("gb2312").length > lengths[i]) {
							lengths[i] = it.get(i).toString().getBytes("gb2312").length;
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			lengths[i] = lengths[i] + 3;
		}
		return lengths;
	}
	
	// dgf update 得到每一行的内容,修改原因，原来的增加$,没有必要，浪费内存和性能
		public String getLine(List it, int[] leng) {
			StringBuffer lines = new StringBuffer();
			for (int i = 0; i < leng.length; i++) {
				if (it.get(i) != null) {
					lines.append(it.get(i));
					try {
						for (int j = it.get(i).toString().getBytes("gb2312").length; j < leng[i]; j++) {
							lines.append(" ");
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					for (int j = 0; j < leng[i]; j++) {
						lines.append(" ");
					}
				}
			}
			lines.append("\n");
			return lines.toString();
		}

		public String getHTMLString(List list, List<Reportformsinfo> reportInfoList) {
			StringBuffer html = new StringBuffer();
			html
					.append("<table width=97% border=1 cellpadding=0 cellspacing=0  bordercolordark=#A6E9FF bordercolorlight=#000099 style='TABLE-LAYOUT:fixed'>");
			html.append("<tr bgcolor='#E5F2F2'>");
			for (Reportformsinfo info : reportInfoList) {
				html.append("<td width='" + info.getColWidth() + "' class='t1'");
				String align = "";
				if ("0".equals(info.getAlignType())) {
					align = "left";
				} else if ("1".equals(info.getAlignType())) {
					align = "center";
				} else if ("2".equals(info.getAlignType())) {
					align = "right";
				}
				html.append(" align='" + align + "' title=' " + info.getColName()
						+ " ' >");
				html
						.append("<div style='overflow: hidden;text-overflow:ellipsis;white-space:nowrap;text-align: "
								+ align
								+ ";width:"
								+ info.getColWidth()
								+ "px;'> "
								+ info.getColName());
				html.append("</div></td>");
			}
			html.append("</tr>");
			for (int i = 0; i < list.size(); i++) {
				List li = (List) list.get(i);
				html.append("<tr>");
				for (int j = 0; j < li.size(); j++) {
					List l = (List) li.get(j);
					for (int k = 0; k < l.size(); k++) {
						html.append((String) l.get(k));
					}
				}
				html.append("</tr>");
			}
			return html.toString();
		}

		/**
		 * 列表数据
		 * 
		 * @return list
		 */
		public List<List<List<String>>> getTableContent(ResultSet rs,
				List<Reportformsinfo> infos) {
			List<List<List<String>>> tableList = new ArrayList<List<List<String>>>();
			try {
				while (rs.next()) {
					List<List<String>> trList = new ArrayList<List<String>>();
					for (Reportformsinfo info : infos) {
						List<String> tdList = new ArrayList<String>();
						// 表格内内容
						String content = rs.getString(info.getColName());
						// 表格的宽度
						String width = info.getColWidth();
						// 表格的对齐方式
						String align = "";
						if (info.getAlignType() == 0) {
							align = "left";
						}
						if (info.getAlignType() == 1) {
							align = "center";
						}
						if (info.getAlignType() == 2) {
							align = "right";
						}
						String tdcontent = "";

						// 拦截服务器日志，进行日志类型替换
						if (info.getCorrespondingField() == 48) {
							ColConversion conversion = new ColConversion();
							conversion.setColName(ColConversion.eventId);
							content = conversion.getColValue(content);
						}
						// 联想版内容
						// 拦截事件变更信息，替换主类型名
						if (info.getCorrespondingField() == 338) {
							Map<String, String> map = new DataSource()
									.getMajorType();
							content = map.get(content);
						}
						// 拦截事件变更信息，替换次类型名
						if (info.getCorrespondingField() == 339) {
							Map<String, Map<String, String>> map = new DataSource()
									.getMap();
							Collection<Map<String, String>> list = map.values();
							for (Map<String, String> m : list) {
								if (m.containsKey(content.toLowerCase())) {
									content = m.get(content.toLowerCase());
								}
							}
						}
						// 拦截事件变更信息，替换变化类型
						if (info.getCorrespondingField() == 342) {
							if (content.equals("0")) {
								content = "增加";
							} else if (content.equals("1")) {
								content = "删除";
							} else {
								content = "修改";
							}
						}

						// 表格内容
						tdcontent = "<td width='"
								+ width
								+ "' align='"
								+ align
								+ "' title='"
								+ content
								+ "'>"
								+ "<div style='overflow: hidden;text-overflow:ellipsis;width:"
								+ (Integer.valueOf(width) * 0.9)
								+ "px;white-space:nowrap;'>" + content
								+ "</div></td>";
						tdList.add(tdcontent);
						trList.add(tdList);
					}
					tableList.add(trList);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return tableList;
		}
}