package com.util.reportForm.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.soc.model.conf.GlobalConfig;

import com.soc.service.systemsetting.SettingService;
import com.util.Base64;
import com.util.DES;
import com.util.StringUtil;
import com.util.email.SendEmail;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.ExtendedActionServlet;
import com.util.reportForm.datadeal.model.ReportCustom;
import com.util.reportForm.datadeal.model.ReportTimer;
import com.util.reportForm.datadeal.model.Reportforms;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ColConversion;
import com.util.reportForm.model.ReportExportModel;
import com.util.reportForm.service.ReportFormQueryService;
import com.util.reportForm.util.JDBC;
import com.util.reportForm.util.export.ReportExportCSV;
import com.util.reportForm.util.export.ReportExportDoc;
import com.util.reportForm.util.export.ReportExportPdf;
import com.util.reportForm.util.export.ReportExportTxt;
import com.util.reportForm.util.export.ZipCompressUtil;

/**
 * 计时报表
 * @author zsa
 *
 */
public class ReportTimerThread {
	private static ReportExportModel model = new ReportExportModel();
	private static SettingService settingManager;
	public static SettingService getSettingManager() {
		return settingManager;
	}
	public static void setSettingManager(SettingService settingManager) {
		ReportTimerThread.settingManager = settingManager;
	}

	private static BaseDao dao = new BaseDao();
	// 分组
	private static String groupbycol = "";
	// 排序
	private static String orderby = "";
	// 统计查询
	private static String having = "";
	private static String pathTXT = "";
	private static String pathXLS = "";
	private static String pathCSV = "";
	private static String pathHTML = "";
	private static String pathDOC = "";
	private static String pathPDF = "";
	private static String pathHtmlZip = GlobalConfig.ctx + "downloads/report.zip";
	
	public static Runnable createThread(final SettingService settingManager,final List<ReportTimer> allReportTimer){
		return new Runnable() { 
			public void run() {
				setSettingManager(settingManager);
				//发送邮件
				sendEmail(export(allReportTimer));
			}
		};
	}
	/**
	 * 报表table串生成
	 * @param allReportTimer
	 * @return
	 */
	private static String export(List<ReportTimer> allReportTimer){
		StringBuffer html = new StringBuffer();
		List<List<String>> list = new ArrayList<List<String>>();
		int reportId;
		long reportFormId;
		ReportTimer timer=null;
		for(int i=0;i<allReportTimer.size();i++){
			timer=allReportTimer.get(i);
			if(timer.getReportType()!=null){
				if(timer.getReportType()==0){//系统报表串
					reportId=Integer.parseInt(timer.getReportId().substring(0,timer.getReportId().indexOf(",")));
					reportFormId=Long.parseLong(timer.getReportId().substring(timer.getReportId().indexOf(",")+1));
					list=exportSys(reportFormId, reportId);
					//报表以邮件正文方式发送
					html.append(chapter(model.getReportTitle(), String.valueOf(i)));
					html.append("<table align='center'>");
					html.append(writeTable(model.getSql()));
					html.append("</table>");
					//报表以html、doc、txt、pdf、csv、xls等格式以邮件附件方式发送
					pathHTML= GlobalConfig.ctx + "downloads/report/"+reportFormId+".html";
					model.setFilePath(pathHTML);
					model.setZipFileNameAndPath(pathHtmlZip);
					exportHTML(reportFormId);
					pathDOC= GlobalConfig.ctx + "downloads/report/"+reportFormId+".doc";
					model.setFilePath(pathDOC);
					ReportExportDoc doc=new ReportExportDoc();
					doc.setModel(model);
					doc.exportDoc();
					pathTXT=GlobalConfig.ctx + "downloads/report/"+reportFormId+".txt";
					model.setFilePath(pathTXT);
					ReportExportTxt txt=new ReportExportTxt();
					txt.setModel(model);
					txt.exportTxt();
					pathPDF=GlobalConfig.ctx + "downloads/report/"+reportFormId+".pdf";
					model.setFilePath(pathPDF);
					ReportExportPdf pdf=new ReportExportPdf();
					pdf.setModel(model);
					pdf.exportPdf();
					pathCSV=GlobalConfig.ctx + "downloads/report/"+reportFormId+".csv";
					ReportExportCSV csv=new ReportExportCSV();
					csv.ExportCSV(list, pathCSV);
					pathXLS=GlobalConfig.ctx + "downloads/report/"+reportFormId+".xls";
					ExportExcel(list);
				}else{//自定义报表串
					html.append(exportCustom(Integer.parseInt(timer.getReportId()),String.valueOf(i)));
				}
			}
		}
		//压缩文件
		mkZipFile();
		return html.toString();
	}
	/**
	 * 系统报表导出得到所有的数据list
	 * @param reportFormId
	 * @param reportId
	 */
	private static List exportSys(Long reportFormId,int reportId){
		String reportInfoHql = "from Reportformsinfo where reportFormId="+reportFormId;
		List<List<String>> list = new ArrayList<List<String>>();
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
					info.setColWidth(String.valueOf(titleLength - currentLength));
				}
			} else {
				currentLength += Integer.parseInt(info.getColWidth());
			}
			k++;
			reportInfoLists.add(info);
			titles.add(info.getColName());
		}
		String strSQL = "";
		String hqls = getSql(reportId);
		if (!"".equals(groupbycol) && null != groupbycol) {
			strSQL = hqls + "group by " + groupbycol;
		} else {
			strSQL = hqls;
		}
		strSQL += having + orderby;
		JDBC db = new JDBC();
		ResultSet rs = null;
		// 报表导出内容
		model.setTableList(list);
		model.setReportFormInfo(reportInfoLists);

		// 以1000条记录为单位导出数据
		String totalRowCount = "select count(*) "
				+ strSQL.substring(strSQL.indexOf("from"));
		//数据库适配
		int num = 0;
		try {
			rs = db.getResultSet(totalRowCount);
			if (totalRowCount.indexOf(" group by ") != -1) {
				rs.last();
				num = rs.getRow();
			} else {
				rs.next();
				num = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
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

			String s = strSQL + " limit " + first + "," + max;
			sql[i] = s;
		}
		model.setSql(sql);
		try {
			rs = db.getResultSet(strSQL);
			// 添加标题
			list.add(titles);
			while (rs.next()) {
				List<String> li = new ArrayList<String>();
				for (String title : titles) {
					li.add(rs.getString(title));
				}
				list.add(li);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 拼接sql语句
	 * @return
	 */
	private static String getSql(int reportId) {
		String hqls = "";
		String hql = "from Reportforms where id="+reportId;
		List<Reportforms> list1 = new ArrayList<Reportforms>();
		list1 = (List<Reportforms>) dao.getNamedQuery(hql, null);
		if (list1.size() != 0) {
			Iterator<Reportforms> ite = list1.iterator();
			Reportforms rf = ite.next();
			
			model.setReportTitle(rf.getReportFormName());
			
			groupbycol = rf.getGroupby();
			if (!"".equals(rf.getOrderby()) && null != rf.getOrderby()) {
				orderby = " order by " + rf.getOrderby();
			} else {
				orderby = "";
			}
			ReportFormQueryService queryService = new ReportFormQueryService();
			String cond = "";

			if (rf.getReportFormType() == 0) {
				// 从页面重新接收到参数
				cond = rf.getSelTerm();
			} else {
				cond = rf.getSelTerm();
			}
			String arr[] = queryService.getCond(cond);

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
				hqls = formhql + " where 1=1 " + tablelink + value;
			} else {
				tablelink = queryService.tableLink(rf.getTables());
				formhql = rf.getReportFormSql();
				hqls = formhql + " where 1=1 " + tablelink;
			}
		}
		return hqls;
	}
	
	/**
	 * table 写入
	 * @param sql
	 * @param writer
	 * @throws IOException
	 */
	private static String writeTable(String[] sql) {
		JDBC db = new JDBC();
		ResultSet rs = null;
		String table="";
		for (int j = 0; j < sql.length; j++) {
			List list = new ArrayList();
			String strSQL = sql[j];
			try {
				rs = db.getResultSet(strSQL);
				list = getTableContent(rs, model.getReportFormInfo());
			} catch (Exception e) {
				db.closeAll();
				e.printStackTrace();
			}
			boolean b = true;
			if (j == sql.length - 1) {
				b = false;
			}
			table =table+getHTMLString(list, model.getReportFormInfo(),b);
		}
		return table;
	}

	/**
	 * 得到所有的table显示数据
	 * @param rs
	 * @param infos
	 * @return
	 */
	private static List getTableContent(ResultSet rs, List<Reportformsinfo> infos) {
		List tableList = new ArrayList();
		ColConversion conversion = new ColConversion();
		try {
			while (rs.next()) {
				List trList = new ArrayList();

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

					if (info.getCorrespondingField() == Integer
							.parseInt(ColConversion.eventId)) {
						conversion.setColName(ColConversion.eventId);
						tdcontent = "<td width='" + width + "' align='" + align
								+ "' title= '"
								+ conversion.getColValue(content) + "'>"
								+ conversion.getColValue(content) + "</td>";

					} else {
						// 表格内容
						tdcontent = "<td width='" + width + "' align='"
								+ "left" + "' title='" + content + "'>"
								+ content + "</td>";
					}
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
	 * 报表数据显示
	 * @param list
	 * @param reportInfoList
	 * @param b
	 * @return
	 */
	private static String getHTMLString(List list,
			List<Reportformsinfo> reportInfoList, boolean b) {
		StringBuffer html = new StringBuffer();
		html.append("<table width=97% border=1 cellpadding=0 cellspacing=0  >");
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
			html.append("<div style='text-align: " + align + ";width:"
					+ info.getColWidth() + "px;'> " + info.getColName());
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
		if (!b) {
			html.append("</table>");
		}
		return html.toString();
	}
	
	 /**
	  * 自定义报表导出-以邮件正文行式发送
	  * @param id
	  * @param grade
	  * @return
	  */
	private static String exportCustom(int id,String grade){
		String table="";
		List<List<String>> list = new ArrayList<List<String>>();
		List<String> titles=new ArrayList<String>();
		ReportCustom rc=(ReportCustom)dao.get(ReportCustom.class,id);
		String title=rc.getName();
		JDBC db = new JDBC();
		ResultSet rs;
		try {
			rs = db.getResultSet(rc.getCustomSql());
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
		    List<Map> val=new ArrayList<Map>();
		    Map rowData = new HashMap(columnCount);
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
			for(Map m:val){
				List<String> li = new ArrayList<String>();
				for(String s:titles){
					li.add(m.get(s).toString());
				}
				list.add(li);
			}
			//得到自定义报表的html串
			table= getHtmlCustom(titles,list,title,grade);
		} catch (Exception e1) {
			table="customsql is wrong,you can't export it!";
		}finally{
			db.close();
		}
		return table;
	}
	
	/**
	 * 自定义报表html串
	 * @param titles表头列表
	 * @param contents列表内容
	 * @param tabtitle报表名称
	 * @param grade表格编号
	 * @return
	 */
	private static String getHtmlCustom(List<String>titles,List<List<String>>contents,String tabtitle,String grade){
		StringBuffer html = new StringBuffer();
		html.append(chapter(tabtitle, grade));
		html.append("<table width=97% border=1 cellpadding=0 cellspacing=0  >");
		html.append("<tr bgcolor='#E5F2F2'>");
		for(String title:titles){
			html.append("<td width='120' class='t1'");
			html.append(" align='left' title=' " +title+ " ' >");
			html.append("<div style='text-align: left;width:"
					+ title + "px;'> " + title);
			html.append("</div></td>");
		}
		html.append("</tr>");
		for(int i=0;i<contents.size();i++){
			List li = (List) contents.get(i);
			html.append("<tr>");
			for (int j = 0; j < li.size(); j++) {
				html.append("<td>");
				html.append((String) li.get(j));
				html.append("</td>");
			}
			html.append("</tr>");
		}
		html.append("</table>");
		return html.toString();
	}
	
	

	/**
	 * 报表title样式
	 * @param item报表名称
	 * @param grade表格编号
	 * @return
	 */
	private static String chapter(String item, String grade) {

		StringBuffer sb = new StringBuffer();
		if ("0".equals(grade)) {
			sb.append("<BR><TABLE border=0 width=97% ><TR><TD><HR></TD></TR></TABLE>");
		} else {
			sb.append("<BR><TABLE border=0 width=97% ><TR><TD></TD></TR></TABLE>");
		}
		sb.append("<A name='aaa'></a>");
		sb.append("<br><TABLE border=0 cellPadding=0 cellSpacing=0 width=97% ><TR> ");
		sb.append("<TD class='z" + grade + "'>" + item + "</TD>");
		sb.append("<TD align=right><NOBR><FONT size=3><B></B></FONT></NOBR></TD>");
		sb.append("</TR></TABLE><BR>");
		return sb.toString();
	}
	
	/**
	 * 导出html-以附件形式
	 * @param reportFormId
	 */
	private static void exportHTML(Long reportFormId) {
		String path = model.getFilePath().substring(0, model.getFilePath().lastIndexOf("/"));
		File zipFile = new File(path);
		if(!zipFile.exists()){
			zipFile.mkdirs();
		}
		File htmlFile = new File(model.getFilePath());
		htmlFile.delete();
		Writer writer = null;
		if (!htmlFile.exists()) {
			try {
				htmlFile.createNewFile();
				writer = new OutputStreamWriter(new FileOutputStream(htmlFile),"UTF-8");
				writer.write("<HTML><HEAD><TITLE>" + model.getReportTitle()
						+ " HTML报表</TITLE>");
				writer
						.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /></HEAD>");
				writer.write("<style type='text/css'><!-- \n");
				writer.write(" body{font-family:Arial;}\n");
				writer.write(" td{FONT-SIZE:10pt}\n");
				writer.write(" .m0{font-size:12pt;font-weight:bold;}\n");
				writer.write(" .m1{font-size:10pt;font-weight:bold;}\n");
				writer
						.write(" .m2{font-size:10pt;font-weight:bold;text-align:right;}\n");
				writer
						.write(" .z0{font-size:12pt;font-weight:bold;color:#003399;}\n");
				writer
						.write(" .z1{font-size:11pt;font-weight:bold;color:#003399;}\n");
				writer.write(" .t0{font-size:12pt;}\n");
				writer
						.write(" .t1{font-size:10pt;font-weight:bold;color:#FFFFFF;background-color:#0099DD}\n");
				writer
						.write(" .t2{font-size:10pt;font-weight:bold;color:#FFFFFF;text-align:center;background-color:#0099DD}\n");
				writer.write("--></style>");
				writer
						.write("<BODY bgColor=#DDEDFB leftMargin=10 topMargin=15 marginwidth=8 marginheight=8>");
				writer
						.write("<CENTER><table border=1 width=97% frame='vsides' bordercolor=#FFFFFF bgcolor=#DDEDFB cellPadding=0 cellspacing=1>");

				// 报表模板名称
				writer
						.write("<TABLE border=0 width='97%'><TR><TD><HR></TD></TR></TABLE>");
				writer.write("<TABLE border=0 width='97%'><TR align=center>");
				writer.write("<TD><FONT size=7><B>" + model.getReportTitle()
						+ "</B></FONT></TD>");
				writer.write("</TR></TABLE>");
				writer.write("<BR/>");
				// 产生换行
				for (int i = 0; i < 20; i++)
					
				// 报表属性

				writer.write("<TABLE border=0 width='97%' alert='center'><TR>");
				writer
						.write("<TD align=right width='50%'><B><FONT size=4>模板创建人：<BR><BR></FONT></B></TD>");
				writer.write("<TD width='50%'><B><FONT size=4>"
						+ model.getCreateUser() + "<BR><BR></FONT></B></TD>");
				writer.write("</TR><TR>");
				writer
						.write("<TD align=right><B><FONT size=4>报表生成人：<BR><BR></FONT></B></TD>");
				writer.write("<TD><B><FONT size=4>" + model.getExportUser()
						+ "<BR><BR></FONT></B></TD>");
				writer.write("</TR><TR>");
				writer
						.write("<TD align=right><B><FONT size=4>单位：<BR><BR></FONT></B></TD>");
				writer.write("<TD><B><FONT size=4>" + model.getCompany()
						+ "<BR><BR></FONT></B></TD>");
				writer.write("</TR><TR>");
				writer
						.write("<TD align=right><B><FONT size=4>报表生成日期：<BR><BR></FONT></B></TD>");
				writer.write("<TD><B><FONT size=4>"
						+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())
						+ "<BR><BR></FONT></B></TD>");
				writer.write("</TR></TABLE>");
				writer
						.write("<P id=Paragraph style='PAGE-BREAK-AFTER: always'>&nbsp;</P>");
				writer
						.write("<TABLE border=0 width='97%'><TR><TD><HR></TD></TR></TABLE>");
				// 说明信息
				writer.write("<TABLE border=0 width=97% >");
				writer
						.write("<TR><TD align=center><B><FONT size=+7>说 明<BR><BR></FONT></B></TD></TR>");
				writer
						.write("<TR><TD class=t0 >　　本报告包含机密信息，不得通过电子邮件、传真、或其它电子设备进行传播；不得对该文档做任何副本或者备份。除非经过授权，否则不得向任何人共享该文档内的任何信息。<BR><BR><BR></TD></TR>");
				writer
						.write("<TR><TD class=t0 >　　"
								+ model.getTitle()
								+ "是注册的商品标志，未经所有人授权许可，任何组织和个人不得使用于任何商业目的。<BR><BR><BR></TD></TR>");
				writer.write("<TR><TD class=t0 >　　" + model.getSupport()
						+ "<BR><BR><BR></TD></TR>");
				writer
						.write("<TR><TD align=right><BR><BR><BR><BR><BR><BR><FONT size=3>"
								+ model.getOwner() + "</font></TD></TR>");
				writer.write("</TABLE><P>&nbsp;</P>");
				writer
						.write("<P id=Paragraph style='PAGE-BREAK-AFTER: always'>&nbsp;</P>");
				writer.write("</td></tr></table>");

				// 列表数据输出
				writer.write(chapter("报表列表数据", "0"));
				writer.write("<table align='center'>");
				writeTable(model.getSql(), writer);
				writer.write("</table></HTML>");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写入table
	 * @param sql
	 * @param writer
	 * @throws IOException
	 */
	private static void writeTable(String[] sql, Writer writer) throws IOException {
		JDBC db = new JDBC();
		ResultSet rs = null;
		
		for (int j = 0; j < sql.length; j++) {
			List list = new ArrayList();
			String strSQL = sql[j];
			try {
				//System.out.println(strSQL);
				rs = db.getResultSet(strSQL);
				list = getTableContent(rs, model.getReportFormInfo());
				//System.out.println(list.size());
			} catch (Exception e) {
				db.closeAll();
				e.printStackTrace();
			}
			boolean b = true;
			if (j == sql.length - 1) {
				b = false;
			}
			String table =getHTMLString(list, model.getReportFormInfo(),
					b);
			writer.write(table);
		}
	}
	
	/**
	 * excel导出
	 * @param list
	 */
	private static void ExportExcel(List list) {
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
	 * 压缩文件
	 */
	private static void mkZipFile(){
		String filePath = model.getFilePath();
		// 压缩文件
		String path = filePath.substring(0, filePath.lastIndexOf("/"));
		File zipFile = new File(path);
		if(zipFile.exists()){
			// 压缩文件
			ZipCompressUtil util = new ZipCompressUtil();
			util.compress(zipFile.getAbsolutePath(), model.getZipFileNameAndPath());
			for(File f:zipFile.listFiles()){
				f.delete();
			}
			zipFile.delete();
		}
	}
	
	/**
	 * 发送邮件
	 * @param message
	 */
	private static void sendEmail(String message){
		
		String mailAlarm=settingManager.queryByKey("mailAlarm");
		if(StringUtil.isNotBlank(mailAlarm)){
			int mailAlarmInt=Integer.parseInt(mailAlarm);
			if(mailAlarmInt==1){
				//获得邮箱参数
				String smtpServer=settingManager.queryByKey("smtp_server");
				String emailNicky=settingManager.queryByKey("email_nicky");
				String smtpPort=settingManager.queryByKey("smtp_port");
				String emailStr=settingManager.queryByKey("email");
				String smtpIsneedauth=settingManager.queryByKey("smtp_isneedauth");
				String smtpIsneedssl=settingManager.queryByKey("smtp_isneedssl");
				String emailUsername=settingManager.queryByKey("email_username");
				DES des=new DES();
				String emailPass=settingManager.queryByKey("email_password");
				String emailAddress=settingManager.queryByKey("email_address");
				//
				if(StringUtil.isNotBlank(smtpServer)&&StringUtil.isNotBlank(emailNicky)&&StringUtil.isNotBlank(smtpPort)
						&&StringUtil.isNotBlank(smtpIsneedauth)&&StringUtil.isNotBlank(emailUsername)&&StringUtil.isNotBlank(emailPass)
						&&StringUtil.isNotBlank(emailAddress)){
					String emailPassword=DES.getEncrypt(Base64.decodeString(emailPass));
					if(emailStr.indexOf(",")==-1){
						SendEmail sendEmail=new SendEmail(smtpServer);
						//sendEmail.setBody(emailNicky+"您好：<br/>"+message);
						sendEmail.setBody(emailNicky+"您好：<br/>"
								+ "<br/><SPAN STYLE='PADDING-LEFT:10%;'>附件是导出的报表信息，请查收！！！</SPAN>"
								+"<br/>"+message);
						
						String title="计时报表";
						sendEmail.setSubject(title);
						sendEmail.setTo(emailStr);
						if(StringUtil.isNotBlank(smtpIsneedssl)){
							int isssl=Integer.parseInt(smtpIsneedssl);
							if(isssl==1){
								sendEmail.setNeedSsl(true);
								sendEmail.setSSLSecurity(smtpPort);
							}else{
								//sendEmail.setNeedSsl(false);
								sendEmail.setSmtpPort(smtpPort);
							}
						}else{
							sendEmail.setNeedSsl(false);
							sendEmail.setSmtpPort(smtpPort);
						}
						if(Integer.parseInt(smtpIsneedauth)==1){
							sendEmail.setNeedAuth(true);
						}
						else{
							sendEmail.setNeedAuth(false);
						}
						sendEmail.setNamePass(emailUsername,emailPassword);
						sendEmail.setFrom(emailAddress);
						File f=new File(pathHtmlZip);
						if(f.isFile()){
							sendEmail.addFileAffix(pathHtmlZip);
						}
						sendEmail.sendout();
					}else{
						String []str=emailStr.split(",");
						for(String email:str){
							SendEmail sendEmail=new SendEmail(smtpServer);
							//sendEmail.setBody(emailNicky+"您好：<br/>"+message);
							sendEmail.setBody(emailNicky+"您好：<br/>"
									+ "<br/><SPAN STYLE='PADDING-LEFT:10%;'>附件是导出的报表信息，请查收！！！</SPAN>"
									+"<br/>"+message);
							String title="计时报表";
							sendEmail.setSubject(title);
							sendEmail.setTo(email);
							if(StringUtil.isNotBlank(smtpIsneedssl)){
								int isssl=Integer.parseInt(smtpIsneedssl);
								if(isssl==1){
									sendEmail.setNeedSsl(true);
									sendEmail.setSSLSecurity(smtpPort);
								}else{
									sendEmail.setNeedSsl(false);
									sendEmail.setSmtpPort(smtpPort);
								}
							}else{
								sendEmail.setNeedSsl(false);
								sendEmail.setSmtpPort(smtpPort);
							}
							if(Integer.parseInt(smtpIsneedauth)==1){
								sendEmail.setNeedAuth(true);
							}
							else{
								sendEmail.setNeedAuth(false);
							}
							sendEmail.setNamePass(emailUsername,emailPassword);
							sendEmail.setFrom(emailAddress);
							File f=new File(pathHtmlZip);
							if(f.exists()){
								sendEmail.addFileAffix(pathHtmlZip);
							}
							sendEmail.sendout();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	

}
