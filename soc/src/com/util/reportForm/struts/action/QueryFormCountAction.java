package com.util.reportForm.struts.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.soc.model.conf.GlobalConfig;
import com.util.StringUtil;
import com.util.reportForm.chart.BarAndPieChart;
import com.util.reportForm.chart.ChartModel;
import com.util.reportForm.chart.LineChart;
import com.util.reportForm.chart.SpiderChart;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.ExtendedActionServlet;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ColConversion;
import com.util.reportForm.model.ReportExportModel;
import com.util.reportForm.service.ReportFormQueryService;
import com.util.reportForm.util.dataBase.DateSource;
import com.util.reportForm.util.export.ReportExportDoc;
import com.util.reportForm.util.export.ReportExportHTML;
import com.util.reportForm.util.export.ReportExportPdf;
import com.util.reportForm.util.export.ReportExportTxt;
import com.util.reportForm.struts.form.DataSource;
import com.util.reportForm.util.CreateChartServiceImpl;
import com.util.reportForm.util.JDBC;
import com.util.reportForm.util.PathUtil;
import com.util.reportForm.util.page.Pager;

public class QueryFormCountAction extends DispatchAction {
	// 图片保存路径
	String picpath = ExtendedActionServlet.setupPath + "CommonFile/QueryCount/";
	BaseDao dao = new BaseDao();
	// 图标题
	String title = "";
	// x轴
	String coordx = "";
	// y轴
	String coordy = "";
	// 分组
	String groupbycol = "";
	// 排序
	String orderby = "";
	// 统计查询
	String having = "";

	Pager pager = new Pager();

	String pathTXT = ExtendedActionServlet.setupPath + "downloads/report.txt";
	String pathXLS = ExtendedActionServlet.setupPath + "downloads/report.xls";
	String pathCSV = ExtendedActionServlet.setupPath + "downloads/report.csv";
	String pathHTML = ExtendedActionServlet.setupPath + "downloads/report.html";
	String pathDOC = ExtendedActionServlet.setupPath + "downloads/report.doc";
	String pathPDF = ExtendedActionServlet.setupPath + "downloads/report.PDF";
	String pathHtmlZip = ExtendedActionServlet.setupPath + "downloads/report.zip";

	public ActionForward getQueryCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String reportFormId = request.getParameter("reportFormId");
		request.setAttribute("reportFormId", reportFormId);
		String ss=request.getParameter("pageoffset");
		request.setAttribute("pageoffset", request.getParameter("pageoffset"));
		String hqls = this.getSql(request);
		//System.out.println("hqls:"+hqls);
		String strs = this.getCol(hqls);
		String tempSql = strs.split("\\|")[0];

		JDBC db = new JDBC();
		// 图形显示
		if (null != request.getParameter("showChart")) {
			this.showGraphic(hqls, request, tempSql, db);
		} else {
			request.setAttribute("isshow", "0");
			request.setAttribute("max", 1);
			request.setAttribute("step", 1);
			request.setAttribute("jsonPie", "aa");
			request.setAttribute("jsonStr", "ss");
			request.setAttribute("jsonline", "ss");
		}
		String isShow = (String) request.getAttribute("isshow");
		if (!"1".equals(isShow)) {
			request.setAttribute("isshow", "0");
		}
		
		return mapping.findForward("countpage");
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
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String reportFormId = request.getParameter("reportFormId");
		String hqls = this.getSql1(request);

		List<List<String>> list = new ArrayList<List<String>>();
		// 获得列表的名称
		Reportforms reportForm = (Reportforms) dao.get(Reportforms.class,
				Integer.parseInt(reportFormId));
		String reportInfoHql = null;
		if(reportForm ==null){
			reportInfoHql = "from Reportformsinfo where reportFormId='"
					+ reportFormId + "' order by id asc";
		}else {
			reportInfoHql ="from Reportformsinfo where reportFormId='"
					+ reportForm.getReportFormId() + "' order by id asc";
		}
		

		List<Reportformsinfo> reportInfoList = (List<Reportformsinfo>) dao
				.getNamedQuery(reportInfoHql, null);

		Integer titleLength = 820;
		Integer currentLength = 0;
		int k = 0;
		List<Reportformsinfo> reportInfoLists = new ArrayList<Reportformsinfo>();
		List<String> titles = new ArrayList<String>();

		for (Reportformsinfo info : reportInfoList) {
			if (k == reportInfoList.size() - 1) {
				if (titleLength > currentLength) {
					info.setColWidth(String
							.valueOf(titleLength - currentLength));
				}
			} else {
				currentLength += Integer.parseInt(info.getColWidth());
			}
			k++;
			reportInfoLists.add(info);
			titles.add(info.getColName());
		}
		String strSQL = "";
		if (!"".equals(groupbycol) && null != groupbycol) {
			if (!"".equals(reportForm.getCoordx())
					&& !"".equals(reportForm.getCoordy())) {
				String[] tempGroup = groupbycol.split("\\,");
				String group = "";
				for (int j = 2; j < tempGroup.length; j++) {
					group += tempGroup[j] + ",";
				}

				strSQL = "".equals(group) ? hqls : hqls + " group by "
						+ group.substring(0, group.length() - 1);

				if ("".equals(group)) {
					if (hqls.indexOf("count") != -1
							|| hqls.indexOf("sum") != -1) {
						strSQL = hqls + " group by null";
					} else {
						strSQL = hqls;
					}
				} else {
					strSQL = hqls + " group by "
							+ group.substring(0, group.length() - 1);
				}

			} else {
				if(GlobalConfig.sqlId.equals("sqlServer")){
					strSQL = hqls;
				}else{
				strSQL = hqls + "group by " + groupbycol;
				}
			}
		} else {
			//if (hqls.indexOf("count") != -1 || hqls.indexOf("sum") != -1) {
			//	strSQL = hqls + " group by null";
			//} else {
				strSQL = hqls;
			//}
		}
		if(GlobalConfig.sqlId.equals("sqlServer")){
			
		}else{
			strSQL += having + orderby;
		}
		JDBC db = new JDBC();
		ResultSet rs = null;
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
		model.setReportTitle(title);
		model.setZipFileNameAndPath(pathHtmlZip);
		model.setExportUser(exportUser);
		model.setCompany(company);
		model.setCreateUser(createUser);
		model.setFilePath(path);
		model.setTableList(list);
		model.setReportFormInfo(reportInfoLists);

		// 以1000条记录为单位导出数据
		String totalRowCount = "select count(*) "
				+ strSQL.substring(strSQL.indexOf(" from "));
		//数据库适配
		rs = db.getResultSet(totalRowCount);
		int num = 0;
		if (totalRowCount.indexOf(" group by ") != -1) {
			rs.last();
			num = rs.getRow();
		} else {
			rs.next();
			num = rs.getInt(1);
		}
		int offset = 1000;
		int a = num / offset + 1;
		int first = 0;
		int max = 0;
		String[] sql = new String[a];
		for (int i = 0; i < a; i++) {
			first = i * offset;
			if ((i + 1) * offset > num) {
				max = num - i * offset;
			} else {
				max = offset;
			}
			if(GlobalConfig.sqlId.equals("sqlServer")){
				String s = strSQL;
				sql[i] = s;
			}else{
			String s = strSQL + " limit " + max + " OFFSET " + first;
			sql[i] = s;
			}
		}
		model.setSql(sql);
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
		strSQL = strSQL.replace("#", "\"");
		try {
			rs = db.getResultSet(strSQL);
			// 添加标题
			list.add(titles);
			if (geshi.equals("html")) {
				tableList = getTableContent(rs, reportInfoList);
			} else {
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
		}
		
		return null;
	}

	public ActionForward selectTable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String reportFormId = request.getParameter("reportFormId");
		request.setAttribute("reportFormId", reportFormId);
		String hqls = this.getSql(request);

		// 获得列表的名称

		Reportforms reportForm = (Reportforms) dao.get(Reportforms.class,
				Integer.parseInt(reportFormId));
		String reportInfoHql = "from Reportformsinfo where reportFormId='"
				+ reportForm.getReportFormId() + "' order by id asc";
		List<Reportformsinfo> reportInfoList = (List<Reportformsinfo>) dao
				.getNamedQuery(reportInfoHql, null);

		// 列表显示 width: 820px
		Integer titleLength = 820;
		Integer currentLength = 0;
		int i = 0;
		List<Reportformsinfo> reportInfoLists = new ArrayList<Reportformsinfo>();
		for (Reportformsinfo info : reportInfoList) {

			if (i == reportInfoList.size() - 1) {
				if (titleLength > currentLength) {
					info.setColWidth(String
							.valueOf(titleLength - currentLength));
				}
			} else {
				currentLength += Integer.parseInt(info.getColWidth().trim());
			}
			i++;
			reportInfoLists.add(info);
		}

		request.setAttribute("titlelist", reportInfoLists);

		// 列表数据
		String strSQL = "";
		if (!"".equals(groupbycol) && null != groupbycol) {
			if (!"".equals(reportForm.getCoordx())
					&& !"".equals(reportForm.getCoordy())) {
				String[] tempGroup = groupbycol.split("\\,");
				String group = "";
				for (int j = 2; j < tempGroup.length; j++) {
					group += tempGroup[j] + ",";
				}
				if(GlobalConfig.sqlId.equals("sqlServer")){
					strSQL = "".equals(group) ? hqls : hqls + " ORDER BY "
							+ group.substring(0, group.length() - 1)+" desc";
				}else{
				strSQL = "".equals(group) ? hqls : hqls + " group by "
						+ group.substring(0, group.length() - 1);
				}
				if ("".equals(group)) {
					if (hqls.indexOf("count") != -1
							|| hqls.indexOf("sum") != -1) {
						if (!"".equals(groupbycol) && null != groupbycol) {
							
						}else{
							strSQL = hqls 
									+ " group by null";
						}
					} else {
						strSQL = hqls;
					}
				} else {
					if (!"".equals(groupbycol) && null != groupbycol) {
						strSQL = hqls + " ORDER BY "
								+ group.substring(0, group.length() - 1)+" desc";
					}else{
						strSQL = hqls + " group by "
								+ group.substring(0, group.length() - 1);
						
					}
				}

			} else {
				strSQL = hqls + "group by " + groupbycol;
			}
		} else {
			if (hqls.indexOf(" count") != -1 || hqls.indexOf(" sum") != -1) {
				strSQL = hqls + " group by null";
			} else {
				strSQL = hqls;
			}
		}
		strSQL += having + orderby;
		JDBC db = new JDBC();
		ResultSet rs = null;
		String pageoffset = request.getParameter("pageoffset");
		int offset = Integer.parseInt(pageoffset);

		int startRow = 0;
		int endRow = 0;
		int totalRows = 0;
		int pageSize = 0;

		String action = request.getParameter("action");
		if (action == null || action.equals("null")) {
			pager = new Pager(offset);
		}
		String pageS = request.getParameter("page");
		Integer page = 0;
		if (pageS != null && !pageS.equals("null") && !pageS.equals("")) {
			page = Integer.parseInt(pageS);
		}
		// 设置总数
		String totalRowCount = "select count(1) "
				+ strSQL.substring(strSQL.indexOf(" from "));
		//数据库适配
		rs = db.getResultSet(totalRowCount);
		if (totalRowCount.indexOf(" group by ") != -1) {
			rs.last();
			totalRows = rs.getRow();
		} else {
			rs.next();
			totalRows = rs.getInt(1);
		}

		pager.start(totalRows);

		pager.setPageSize(offset);

		pageSize = pager.getPageSize();
		if(GlobalConfig.sqlId.equals("sqlServer")){
			strSQL=strSQL.replace("group by","ORDER BY");
			
			if (action == null || action.equals("null")) {
				startRow = pager.getStartRow();
				if (totalRows > pageSize) {
					pager.setHasNextPage(true);
					pager.setEndRow(startRow + pageSize - 1);
				} else {
					pager.setEndRow(totalRows);
				}
				endRow = pager.getEndtRow();
				strSQL = strSQL.replace("fenyeshu", pager.getPageSize()+"");
				
			} else {
				strSQL.replace("group by","ORDER BY");
				if (action == "previousPage" || action.equals("previousPage")) {

					pager.previous();
					startRow = pager.getStartRow();
					endRow = pager.getEndtRow();
					strSQL = strSQL.replace("fenyeshu", endRow-startRow+"");
					strSQL = strSQL.replace("0", startRow+"");
					strSQL = strSQL.replace("having 1=1", "");
				}
				if (action == "nextPage" || action.equals("nextPage")) {

					pager.next();
					startRow = pager.getStartRow();
					endRow = pager.getEndtRow();
					strSQL = strSQL.replace("fenyeshu", endRow-startRow+"");
					strSQL = strSQL.replace("0", startRow+"");
					strSQL = strSQL.replace("having 1=1", "");
				}
				if (action == "first" || action.equals("first")) {

					pager.first();
					startRow = pager.getStartRow();
					endRow = pager.getEndtRow();
					strSQL = strSQL.replace("fenyeshu", endRow-startRow+"");
					strSQL = strSQL.replace("0", startRow+"");
					strSQL = strSQL.replace("having 1=1", "");
				}
				if (action == "last" || action.equals("last")) {

					pager.last();
					startRow = pager.getStartRow();
					endRow = pager.getEndtRow();
					strSQL = strSQL.replace("fenyeshu", endRow-startRow+"");
					strSQL = strSQL.replace("0", startRow+"");
					strSQL = strSQL.replace("having 1=1", "");
				}
				if (action == "findPage" || action.equals("findPage")) {

					pager.find(page);
					startRow = pager.getStartRow();
					endRow = pager.getEndtRow();
					strSQL = strSQL.replace("fenyeshu", endRow-startRow+"");
					strSQL = strSQL.replace("0", startRow+"");
					strSQL = strSQL.replace("having 1=1", "");
				}
			}
		}else{
		if (action == null || action.equals("null")) {

			startRow = pager.getStartRow();
			if (totalRows > pageSize) {
				pager.setHasNextPage(true);
				pager.setEndRow(startRow + pageSize - 1);
			} else {
				pager.setEndRow(totalRows);
			}
			endRow = pager.getEndtRow();
			strSQL = strSQL + " limit " + pager.getPageSize() + " OFFSET " + startRow;

		} else {
			if (action == "previousPage" || action.equals("previousPage")) {

				pager.previous();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				strSQL = strSQL + " limit " + pager.getPageSize() + " OFFSET " + startRow;
			}
			if (action == "nextPage" || action.equals("nextPage")) {

				pager.next();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				strSQL = strSQL + " limit " + pager.getPageSize() + " OFFSET " + startRow;
			}
			if (action == "first" || action.equals("first")) {

				pager.first();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				strSQL = strSQL + " limit " + pager.getPageSize() + " OFFSET " + startRow;
			}
			if (action == "last" || action.equals("last")) {

				pager.last();
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				strSQL = strSQL + " limit " + pager.getPageSize() + " OFFSET " + startRow;
			}
			if (action == "findPage" || action.equals("findPage")) {

				pager.find(page);
				startRow = pager.getStartRow();
				endRow = pager.getEndtRow();
				strSQL = strSQL + " limit " + pager.getPageSize() + " OFFSET " + startRow;
			}
		}
		}
		List<List<List<String>>> datalist = new ArrayList<List<List<String>>>();
		try {
			rs = db.getResultSet(strSQL);
		} catch (RuntimeException e) {
			db.closeAll();
			e.printStackTrace();
		}

		// 列表显示表格数据
		datalist = getTableContent(rs, reportInfoLists);
		request.setAttribute("datalist", datalist);
		request.setAttribute("title", reportInfoLists.size());
		request.setAttribute("page", pager);
		return mapping.findForward("selectTable");
	}

	public ActionForward reportinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String reportFormId = request.getParameter("reportFormId");
		String geshi = request.getParameter("geshi");
		request.setAttribute("geshi", geshi);
		request.setAttribute("reportFormId", reportFormId);
		return null;
	}
	

	// 拼接SQL语句
	private String getSql(HttpServletRequest request) {
		String hqls = "";
		String reportFormId = request.getParameter("reportFormId");
		String hql = "from Reportforms where id='" + reportFormId + "'";
		List<Reportforms> list1 = new ArrayList<Reportforms>();
		list1 = (List<Reportforms>) dao.getNamedQuery(hql, null);
		if (list1.size() != 0) {
			Iterator<Reportforms> ite = list1.iterator();
			Reportforms rf = ite.next();
			title = rf.getReportFormName();
			coordx = rf.getCoordx();
			coordy = rf.getCoordy();
			groupbycol = rf.getGroupby();
			if(groupbycol!=null&&!groupbycol.equals("")){
				groupbycol = rf.getGroupby().replace("#", "\"");
			}
					
			if (!"".equals(rf.getOrderby()) && null != rf.getOrderby()) {
				orderby = " order by " + rf.getOrderby().replace("#", "\"");
			} else {
				orderby = "";
			}

			ReportFormQueryService queryService = new ReportFormQueryService();
			String cond = "";
			/*if (rf.getReportFormType() == 0) {
				// 从页面重新接收到参数
				if (request.getParameter("arrays") != null
						&& !"".equals(request.getParameter("arrays"))) {
					cond = request.getParameter("arrays").replaceAll("％", "%");
					request.setAttribute("arrays", cond);
				} else {
					cond = rf.getSelTerm();
				}
			} else {
				cond = rf.getSelTerm();
			}*/
			cond = rf.getSelTerm();
			// 联想版本内容
			// 拦截事件变更信息，替换查询条件中的主类型名
			if (cond.indexOf("client_change_info.MajorType") > -1) {
				String[] temp = cond.split("client_change_info.MajorType");
				cond = temp[0];
				for (int i = 0; i < temp.length; i++) {
					if (i > 0) {
						String old = temp[i].substring(
								temp[i].indexOf(" '") + 2, temp[i]
										.indexOf("'$"));
						String news = "";
						if (!old.equals("？")) {
							Iterator<Entry<String, String>> set = new DataSource()
									.getMajorType().entrySet().iterator();
							while (set.hasNext()) {
								Entry<String, String> entry = set.next();
								if (entry.getValue().equals(old)) {
									news = entry.getKey();
								}
							}
						}
						temp[i] = temp[i].replaceFirst(old, news);
						cond += ("client_change_info.MajorType" + temp[i]);
					}
				}
			}
			// 拦截事件变更信息，替换查询条件中的次类型名
			if (cond.indexOf("client_change_info.MinorType") > -1) {
				String[] temp = cond.split("client_change_info.MinorType");
				cond = temp[0];
				for (int i = 0; i < temp.length; i++) {
					if (i > 0) {
						String old = temp[i].substring(
								temp[i].indexOf(" '") + 2, temp[i]
										.indexOf("'$"));
						String news = "";
						if (!old.equals("？")) {
							Collection<Map<String, String>> list = new DataSource()
									.getMap().values();
							for (Map<String, String> m : list) {
								Iterator<Entry<String, String>> set = m
										.entrySet().iterator();
								while (set.hasNext()) {
									Entry<String, String> entry = set.next();
									if (entry.getValue().equals(old)) {
										news = entry.getKey();
									}
								}
							}
						}
						temp[i] = temp[i].replaceFirst(old, news);
						cond += ("client_change_info.MinorType" + temp[i]);
					}
				}
			}

			// 拦截事件变更信息，替换查询条件中的变化类型
			if (cond.indexOf("client_change_info.ChangeType") > -1) {
				String[] temp = cond.split("client_change_info.ChangeType");
				cond = temp[0];
				for (int i = 0; i < temp.length; i++) {
					if (i > 0) {
						String old = temp[i].substring(
								temp[i].indexOf(" '") + 2, temp[i]
										.indexOf("'$"));
						String news = "";
						if (old.equals("增加")) {
							news = "0";
						} else if (old.equals("删除")) {
							news = "1";
						} else if(old.equals("修改")){
							news = "2";
						}else{
							news = old;
						}
						temp[i] = temp[i].replaceFirst(old, news);
						cond += ("client_change_info.ChangeType" + temp[i]);
					}
				}
			}

			// 统计求和查询条件
			String[] havingStr = request.getParameterValues("hav");
			String[] havingArr = request.getParameterValues("having");
			String tempHaving = "";
			if (!"".equals(havingStr) && null != havingStr) {
				for (int i = 0; i < havingStr.length; i++) {
					String str = havingStr[i];
					if (!"".equals(str)) {
						tempHaving += " and " + havingArr[i];
					}
				}
			}

			String t = request.getParameter("tempHaving");

			if ("".equals(tempHaving)) {
				if (!"".equals(t) && null != t) {
					tempHaving = t;
				}
			}

			if (!"".equals(tempHaving)) {
				having = " having 1=1" + tempHaving;
			} else {
				having = "";
			}
			request.setAttribute("tempHaving", tempHaving);

			String arr[] = queryService.getCond(cond);

			if (null == havingStr && t == null) {
				String s = arr[3];
				if (s.indexOf("and") > -1) {
					s = s.replaceAll("and", ") and (");
					s = s.substring(s.indexOf(")") + 1, s.length()) + ")";
				}

				if (s.indexOf("？") > -1) {
					s = s.replaceAll("？", "'' or 1=1 ");
				}
				having = " having 1=1" + s;
			}

			String formhql = "";
			// 表与表之间的关联
			String tablelink = "";
			cond = arr[0];
			if (cond != null && cond.length() > 0) {
				String value = "";
				String[] conds = cond.split("\\|\\|");
				for (int i = 0; i < conds.length; i++) {
					String[] condss = conds[i].split(";");
					String _value = condss[1].split("\\$")[0];
					// 如果条件值为空，去除条件
					if (queryService.isEmpty(_value.toCharArray())) {
						continue;
					}
					value = value + (condss[1].split("\\$"))[0] + " ";
				}
				formhql = rf.getReportFormSql();
				tablelink = queryService.tableLink(rf.getTables());
				if(formhql.indexOf("where")==-1){
					if(GlobalConfig.sqlId.equals("sqlServer")){
						hqls = formhql + " where 1=1 " + tablelink + value;
					}else{
					hqls = formhql.replaceAll("select", "select top fenyeshu") + " where 1=1 " + tablelink + value;
					}
				}else {
					hqls = formhql + tablelink + value;
				}
			} else {
				tablelink = queryService.tableLink(rf.getTables());
				formhql = rf.getReportFormSql();
				if(formhql.indexOf("where")==-1){
					if(GlobalConfig.sqlId.equals("sqlServer")){
						
						hqls = formhql.replaceAll("select", "select top fenyeshu") + " where 1=1  AND "+groupbycol +" NOT IN (select top 0 "+groupbycol.split("\\.")[1]+" FROM "+
								rf.getTables().replace(",", "") +" ORDER BY "+groupbycol.split("\\.")[1]+" desc)"+ tablelink;
								;
					}else{
					hqls = formhql + " where 1=1 " + tablelink;
					}
				}else {
					hqls = formhql + tablelink;
				}
				
			}
		}
		hqls = hqls.replace("#", "\"");
		return hqls;
	}
	// 拼接SQL语句
		private String getSql1(HttpServletRequest request) {
			String hqls = "";
			String reportFormId = request.getParameter("reportFormId");
			String hql = "from Reportforms where id='" + reportFormId + "'";
			List<Reportforms> list1 = new ArrayList<Reportforms>();
			list1 = (List<Reportforms>) dao.getNamedQuery(hql, null);
			if (list1.size() != 0) {
				Iterator<Reportforms> ite = list1.iterator();
				Reportforms rf = ite.next();
				title = rf.getReportFormName();
				coordx = rf.getCoordx();
				coordy = rf.getCoordy();
				groupbycol = rf.getGroupby();
				if(groupbycol!=null&&!groupbycol.equals("")){
					groupbycol = rf.getGroupby().replace("#", "\"");
				}
						
				if (!"".equals(rf.getOrderby()) && null != rf.getOrderby()) {
					orderby = " order by " + rf.getOrderby().replace("#", "\"");
				} else {
					orderby = "";
				}

				ReportFormQueryService queryService = new ReportFormQueryService();
				String cond = "";
				/*if (rf.getReportFormType() == 0) {
					// 从页面重新接收到参数
					if (request.getParameter("arrays") != null
							&& !"".equals(request.getParameter("arrays"))) {
						cond = request.getParameter("arrays").replaceAll("％", "%");
						request.setAttribute("arrays", cond);
					} else {
						cond = rf.getSelTerm();
					}
				} else {
					cond = rf.getSelTerm();
				}*/
				cond = rf.getSelTerm();
				// 联想版本内容
				// 拦截事件变更信息，替换查询条件中的主类型名
				if (cond.indexOf("client_change_info.MajorType") > -1) {
					String[] temp = cond.split("client_change_info.MajorType");
					cond = temp[0];
					for (int i = 0; i < temp.length; i++) {
						if (i > 0) {
							String old = temp[i].substring(
									temp[i].indexOf(" '") + 2, temp[i]
											.indexOf("'$"));
							String news = "";
							if (!old.equals("？")) {
								Iterator<Entry<String, String>> set = new DataSource()
										.getMajorType().entrySet().iterator();
								while (set.hasNext()) {
									Entry<String, String> entry = set.next();
									if (entry.getValue().equals(old)) {
										news = entry.getKey();
									}
								}
							}
							temp[i] = temp[i].replaceFirst(old, news);
							cond += ("client_change_info.MajorType" + temp[i]);
						}
					}
				}
				// 拦截事件变更信息，替换查询条件中的次类型名
				if (cond.indexOf("client_change_info.MinorType") > -1) {
					String[] temp = cond.split("client_change_info.MinorType");
					cond = temp[0];
					for (int i = 0; i < temp.length; i++) {
						if (i > 0) {
							String old = temp[i].substring(
									temp[i].indexOf(" '") + 2, temp[i]
											.indexOf("'$"));
							String news = "";
							if (!old.equals("？")) {
								Collection<Map<String, String>> list = new DataSource()
										.getMap().values();
								for (Map<String, String> m : list) {
									Iterator<Entry<String, String>> set = m
											.entrySet().iterator();
									while (set.hasNext()) {
										Entry<String, String> entry = set.next();
										if (entry.getValue().equals(old)) {
											news = entry.getKey();
										}
									}
								}
							}
							temp[i] = temp[i].replaceFirst(old, news);
							cond += ("client_change_info.MinorType" + temp[i]);
						}
					}
				}

				// 拦截事件变更信息，替换查询条件中的变化类型
				if (cond.indexOf("client_change_info.ChangeType") > -1) {
					String[] temp = cond.split("client_change_info.ChangeType");
					cond = temp[0];
					for (int i = 0; i < temp.length; i++) {
						if (i > 0) {
							String old = temp[i].substring(
									temp[i].indexOf(" '") + 2, temp[i]
											.indexOf("'$"));
							String news = "";
							if (old.equals("增加")) {
								news = "0";
							} else if (old.equals("删除")) {
								news = "1";
							} else if(old.equals("修改")){
								news = "2";
							}else{
								news = old;
							}
							temp[i] = temp[i].replaceFirst(old, news);
							cond += ("client_change_info.ChangeType" + temp[i]);
						}
					}
				}

				// 统计求和查询条件
				String[] havingStr = request.getParameterValues("hav");
				String[] havingArr = request.getParameterValues("having");
				String tempHaving = "";
				if (!"".equals(havingStr) && null != havingStr) {
					for (int i = 0; i < havingStr.length; i++) {
						String str = havingStr[i];
						if (!"".equals(str)) {
							tempHaving += " and " + havingArr[i];
						}
					}
				}

				String t = request.getParameter("tempHaving");

				if ("".equals(tempHaving)) {
					if (!"".equals(t) && null != t) {
						tempHaving = t;
					}
				}

				if (!"".equals(tempHaving)) {
					having = " having 1=1" + tempHaving;
				} else {
					having = "";
				}
				request.setAttribute("tempHaving", tempHaving);

				String arr[] = queryService.getCond(cond);

				if (null == havingStr && t == null) {
					String s = arr[3];
					if (s.indexOf("and") > -1) {
						s = s.replaceAll("and", ") and (");
						s = s.substring(s.indexOf(")") + 1, s.length()) + ")";
					}

					if (s.indexOf("？") > -1) {
						s = s.replaceAll("？", "'' or 1=1 ");
					}
					having = " having 1=1" + s;
				}

				String formhql = "";
				// 表与表之间的关联
				String tablelink = "";
				cond = arr[0];
				if (cond != null && cond.length() > 0) {
					String value = "";
					String[] conds = cond.split("\\|\\|");
					for (int i = 0; i < conds.length; i++) {
						String[] condss = conds[i].split(";");
						String _value = condss[1].split("\\$")[0];
						// 如果条件值为空，去除条件
						if (queryService.isEmpty(_value.toCharArray())) {
							continue;
						}
						value = value + (condss[1].split("\\$"))[0] + " ";
					}
					formhql = rf.getReportFormSql();
					tablelink = queryService.tableLink(rf.getTables());
					if(formhql.indexOf("where")==-1){
						
							hqls = formhql + " where 1=1 " + tablelink + value;
						
					}else {
						hqls = formhql + tablelink + value;
					}
				} else {
					tablelink = queryService.tableLink(rf.getTables());
					formhql = rf.getReportFormSql();
					if(formhql.indexOf("where")==-1){
						
						hqls = formhql + " where 1=1 " + tablelink;
						
					}else {
						hqls = formhql + tablelink;
					}
					
				}
			}
			hqls = hqls.replace("#", "\"");
			return hqls;
		}
	// 统计显示的列
	private String getCol(String hqls) {
		String tempSql = "";
		if (hqls.indexOf("count") != -1 || hqls.indexOf("sum") != -1) {
			String s = hqls.substring(0, hqls.indexOf("from")).substring(
					"select".length() + 1);
			String[] str = s.split("\\,");
			String col_dispalys = "";
			for (String col_display : str) {
				if (col_display.indexOf("count") != -1) {
					tempSql += col_display + " ,";
					col_dispalys += col_display.substring("count".length())
							+ " ,";
				} else if (col_display.indexOf("sum") != -1) {
					tempSql += col_display + " ,";
					col_dispalys += col_display.substring("sum".length())
							+ " ,";
				} else {
					col_dispalys += col_display + " ,";
				}
			}
			hqls = "select "
					+ col_dispalys.substring(0, col_dispalys.length() - 1)
					+ hqls.substring(hqls.indexOf("from"));
		}
		if(hqls.indexOf("1=1")==-1){
			hqls.replace("where", "where 1=1");
		}
		return tempSql + "|" + hqls;
	}

	/**
	 * 图形显示
	 */

	private void showGraphic(String hqls, HttpServletRequest request,
			String tempSql, JDBC db) {
		// 图形显示
		ArrayList<DateSource> chartModels = new ArrayList<DateSource>();
		ChartModel chartModel = new ChartModel();
		DateSource date = null;
		// top 值
		String top = request.getParameter("top");
		// 横轴显示条数
		String topX = request.getParameter("topX");
		// 纵轴显示条数
		String topY = request.getParameter("topY");
		// 自定义top值
		String selfTop = request.getParameter("selfTop");
		if (!"".equals(selfTop) && null != selfTop) {
			top = selfTop;
		}
		double t = 0;
		// 对数据的赋值
		if (!"".equals(tempSql) && null != groupbycol) {
			String xSql = hqls + " group by " + groupbycol.split("\\,")[0]+ having;
//			String xSql = hqls + " group by " + groupbycol + having;
			//数据库适配
			try {
				
				ResultSet rs = db.getResultSet(xSql);
				int tx = 0;
				while (rs.next()) {
					if (!"0".equals(topX) && tx == Integer.parseInt(topX)) {
						break;
					}
					int ty = 0;
					String dataSql = "";
					if (!"".equals(tempSql)) {
						request.setAttribute("isshow", "1");
						String[] dis_cols = tempSql.split("\\,");
						for (String col : dis_cols) {
							if (!"0".equals(topY)
									&& ty == Integer.parseInt(topY)) {
								break;
							}
							if (!"".equals(col)) {
								dataSql = "select " + col
										+ hqls.substring(hqls.indexOf("from"));
							}
							date = new DateSource();
							String xName = "";
							if(hqls.substring(hqls.indexOf("from")).contains("policy")){
								if(Integer.parseInt(rs.getString(coordx))==1){
									xName="允许";
								}else{
									xName="禁止";
								}
							}else{
								xName = rs.getString(coordx);
							}
							String num = "0";
							String str = dataSql + " and "
									+ groupbycol.split("\\,")[0] + "='" + xName
									+ "' group by "
									+ groupbycol.split("\\,")[0];
							// 每个X轴上对应的Y轴数据
							
							String tempsql = str + having + orderby;
							//数据库适配
							ResultSet r = db.getResultSet(tempsql);
							while (r.next()) {
								String s = col.split("\\ ")[1].substring(1, col
										.split("\\ ")[1].length() - 1);
								num = r.getString(s);
							}
							if (null == num || "".equals(num)) {
								num = "0";
							}
							if (!"0".equals(top) && Double.parseDouble(num) > t) {
								t = Double.parseDouble(num);
							}
							r.close();

							// 饼图柱状图数据源
							date.setItemX(xName);
							date.setItemY(col.split("\\ ")[1]);
							date.setDate(Double.parseDouble(num));
							chartModels.add(date);
							// 拆线图雷达图数据源
							chartModel.addDataToModelList(col.split("\\ ")[1],
									xName, Double.parseDouble(num));
							ty++;
						}
					}
					tx++;
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				db.close();
			}

		} else {
			request.setAttribute("isshow", "0");
		}
		List<ChartModel> list = new ArrayList<ChartModel>();
		ArrayList<DateSource> ds = new ArrayList<DateSource>();
		if (!"0".equals(top)) {
			for (ChartModel model : chartModel.getChartModelList()) {
				model.setData(model.getData() * Integer.parseInt(top) / t);
				list.add(model);
			}
			chartModel.setChartModelList(list);

			for (DateSource d : chartModels) {
				d.setDate(d.getDate() * Integer.parseInt(top) / t);
				ds.add(d);
			}
			this.graphic(request, ds, chartModel);
		} else {
			this.graphic(request, chartModels, chartModel);
		}

	}

	// 创建图形
	public void graphic(HttpServletRequest request,
			ArrayList<DateSource> chartModels, ChartModel chartModel) {
		//创建文件
		File f=new File(ExtendedActionServlet.setupPath+ "CommonFile/QueryCount");
		if(f.isDirectory()==false){
			f.mkdirs();
		}
		String picRealPath=request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf("/")+1)+ "CommonFile/QueryCount/";
		String picName = String.valueOf(System.currentTimeMillis());
		BarAndPieChart chartD = new BarAndPieChart();
		CreateChartServiceImpl createChart=new CreateChartServiceImpl();
		
		// 饼图
		//chartD.createPieDemo(picName + ".jpg", picpath, chartModels, title, "访问统计饼图");//原始饼图
		
		//第一次修改饼图
		//createChart.createValidityComparePimChar(createChart.getDataPieSetByUtil(chartModels), "访问统计饼图", picpath,picName + ".jpg");
		returnPieJSON(chartModels,request);
		String pie = picRealPath + picName + ".jpg";
		request.setAttribute("pie", pie);
		// 柱形图

		picName = String.valueOf(System.currentTimeMillis());
		//chartD.createBarDemo(picName + "bar.jpg", picpath, chartModels, title, "X坐标", "Y坐标","访问统计柱形图");//原始柱形图
		
		//第一次修改柱形图
		//createChart.createBarChart(createChart.getBarData(chartModels),"X坐标", "Y坐标", "访问统计柱形图", picpath, picName+"bar.jpg");
		String bar =picRealPath + picName + "bar.jpg";
		request.setAttribute("bar", bar);
		
		//第二次修改柱形图
		String json=returnJSON(chartModels,request);
		request.setAttribute("jsonStr", json);
		
		//折线图
		request.setAttribute("jsonline", json);
	
		
		if (chartModel != null) {
			// 折线图

			picName = String.valueOf(System.currentTimeMillis());
			List<ChartModel> modelList = chartModel.getChartModelList();
			/*LineChart linechart = new LineChart(modelList, title, coordy,coordx, 800, 600);
			linechart.getChart(picpath, picName + "line.jpg");
			request.setAttribute("lineChart", picRealPath + picName + "line.jpg");*/
			// 雷达图
			chartD.delpic();
			picName = String.valueOf(System.currentTimeMillis());
			SpiderChart schart = new SpiderChart(modelList, title, 800, 600);
			schart.getChart(picpath, picName + "spider.jpg");
			request.setAttribute("spiderChart", picRealPath + picName + "spider.jpg");
		}

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
	 * 柱形图返回json串()
	 * @param chartModels
	 * @return
	 */
	public String returnJSON(ArrayList<DateSource> chartModels,HttpServletRequest request){
		DateSource ds=null;
		StringBuffer buffer=new StringBuffer();
		double d=0;
		buffer.append("[");
		for(int i=0;i<chartModels.size();i++){
			ds=chartModels.get(i);
			if(ds.getDate()>d){
				d=ds.getDate();
			}
			if(i==chartModels.size()-1){
				buffer.append("{y:'"+ds.getDate()+"',x:'"+ds.getItemX()+"'}");
			}else{
				buffer.append("{y:'"+ds.getDate()+"',x:'"+ds.getItemX()+"'},");
			}
		}
		buffer.append("]");
		String dd=String.valueOf((int)d);
		int l=dd.length();
		int max=10;
		int step=1;
		for(int i=1;i<l;i++){
			max=max*10;//得到最大值
			step=step*10;//步调
		}
		request.setAttribute("max", max);
		request.setAttribute("step", step);
		request.setAttribute("coordx", coordx);
		request.setAttribute("coordy", coordy);
		return buffer.toString();
	}
	/**
	 * 饼状图返回json串
	 * @param chartModels
	 * @param request
	 */
	public void returnPieJSON(ArrayList<DateSource> chartModels,HttpServletRequest request){
		DateSource ds=null;
		StringBuffer bufferPie=new StringBuffer();
		double d=0;
		String color="";
		int red,green,blue;
		bufferPie.append("[");
		for(int i=0;i<chartModels.size();i++){
			red=(int)Math.floor((Math.random()+8)/10*69);
		    green=(int)Math.floor((Math.random()+8)/10*171);
		    blue=(int)Math.floor((Math.random()+8)/10*254);
		    color="#"+Integer.toHexString(red)+Integer.toHexString(green)+Integer.toHexString(blue);
			ds=chartModels.get(i);
			if(ds.getDate()>d){
				d=ds.getDate();
			}
			if(i==chartModels.size()-1){
				bufferPie.append("{y:'"+ds.getDate()+"',x:'"+ds.getItemX()+"',color:'"+color+"'}");
			}else{
				bufferPie.append("{y:'"+ds.getDate()+"',x:'"+ds.getItemX()+"',color:'"+color+"'},");
			}
		}
		bufferPie.append("]");
		request.setAttribute("jsonPie", bufferPie.toString());
		request.setAttribute("coordx", coordx);
		request.setAttribute("coordy", coordy);
	}
	public static void main(String[] args) {
		String s ="tbl_address_policy.ADDRESS_POLICY_ID HAVING 1=1";
		s=s.replace("HAVING 1=1", "");
		System.out.println(s);
		System.out.println(s);
		String [] ss = s.split("\\.");
		System.out.println(ss.length);
	}

}
