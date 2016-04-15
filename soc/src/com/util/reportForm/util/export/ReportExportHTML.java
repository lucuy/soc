package com.util.reportForm.util.export;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ColConversion;
import com.util.reportForm.model.ReportExportModel;
import com.util.reportForm.util.JDBC;

public class ReportExportHTML {

	private ReportExportModel model = new ReportExportModel();

	public ReportExportHTML() {
	}

	public ReportExportHTML(ReportExportModel model) {
		this.model = model;
	}

	public void exportHTML() {

		File htmlFile = new File(model.getFilePath());
		htmlFile.delete();
		Writer writer = null;
		if (!htmlFile.exists()) {
			try {
				htmlFile.createNewFile();
				// writer = new FileWriter(htmlFile);
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
				writer.write(this.chapter("报表列表数据", "0"));
				writer.write("<table align='center'>");
				
				this.writeTable(model.getSql(), writer);
				
				writer.write("</table></HTML>");
				writer.flush();
				writer.close();
				mkZipFile();
				htmlFile.delete();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void writeTable(String[] sql, Writer writer) throws IOException {
		JDBC db = new JDBC();
		ResultSet rs = null;
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
			String table = this.getHTMLString(list, model.getReportFormInfo(),
					b);
			writer.write(table);
		}
	}

	/**
	 * 列表数据
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List getTableContent(ResultSet rs, List<Reportformsinfo> infos) {
		
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
						tdcontent = "<td width='"
								+ width
								+ "' align='"
								+ align
								+ "' title= '"
								+ conversion.getColValue(content)
								+ "'>"
								+ conversion.getColValue(content)
								+ "</td>";

					} else {
						// 表格内容
						tdcontent = "<td width='"
								+ width
								+ "' align='"
								+ "left"
								+ "' title='"
								+ content
								+ "'>"
								+ content
								+ "</td>";
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

	public String getHTMLString(List list,
			List<Reportformsinfo> reportInfoList, boolean b) {
		StringBuffer html = new StringBuffer();
		
		html.append("<table width=97% border=1 cellpadding=0 cellspacing=0  >");
		html.append("<tr bgcolor='#E5F2F2'>");
		for (Reportformsinfo info : reportInfoList) {
			html
					.append("<td width='" + info.getColWidth()
							+ "' class='t1'");
			String align = "";
			if ("0".equals(info.getAlignType())) {
				align = "left";
			} else if ("1".equals(info.getAlignType())) {
				align = "center";
			} else if ("2".equals(info.getAlignType())) {
				align = "right";
			}
			html.append(" align='" + align + "' title=' "
					+ info.getColName() + " ' >");
			html
					.append("<div style='text-align: "
							+ align
							+ ";width:"
							+ info.getColWidth()
							+ "px;'> " + info.getColName());
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

	public void mkZipFile() throws IOException {

		String filePath = model.getFilePath();
		// 压缩文件
		String path = filePath.substring(0, filePath.lastIndexOf("/"));

		File zipFile = new File(path + "/report");
		if (!zipFile.exists()) {
			zipFile.mkdir();
		}

		
		// 复制文件到指定目录
		copyFile(filePath, zipFile.getAbsolutePath() + "/report.html");
		/*if (picPath != null && !"".equals(picPath)) {
			for (int i = 0; i < picPath.length; i++) {
				String picName = picPath[i].substring(picPath[i]
						.lastIndexOf("/"));
				copyFile(picPath[i], imageFile.getAbsolutePath() + picName);
			}
		}*/
		// 压缩文件
		ZipCompressUtil util = new ZipCompressUtil();
		util.compress(zipFile.getAbsolutePath(), model.getZipFileNameAndPath());

		File f = new File(zipFile.getAbsolutePath() + "/report.html");
		f.delete();
		zipFile.delete();
	}

	public void copyFile(String sourceFile, String targetFile)
			throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				new File(sourceFile)));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(new File(targetFile)));
		byte b[] = new byte[1024];
		int a = 0;
		while ((a = bis.read(b)) != -1) {
			bos.write(b, 0, a);
		}
		bos.close();
		bis.close();
	}

	public String chapter(String item, String grade) {

		StringBuffer sb = new StringBuffer();
		if ("0".equals(grade)) {
			sb
					.append("<BR><TABLE border=0 width=97% ><TR><TD><HR></TD></TR></TABLE>");
		} else {
			sb
					.append("<BR><TABLE border=0 width=97% ><TR><TD></TD></TR></TABLE>");
		}
		sb.append("<A name='aaa'></a>");
		sb
				.append("<br><TABLE border=0 cellPadding=0 cellSpacing=0 width=97% ><TR> ");
		sb.append("<TD class='z" + grade + "'>" + item + "</TD>");
		sb
				.append("<TD align=right><NOBR><A href='#TOC--'><IMG align=right border=0 height=13 hspace=3  vspace=4></A><FONT size=3><B></B></FONT></NOBR></TD>");
		sb.append("</TR></TABLE><BR>");
		return sb.toString();
	}

	public ReportExportModel getModel() {
		return model;
	}

	public void setModel(ReportExportModel model) {
		this.model = model;
	}

}
