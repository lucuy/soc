package com.util.reportForm.util.export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ReportExportModel;
import com.util.reportForm.util.JDBC;

public class ReportExportTxt {
	
	private ReportExportModel model = new ReportExportModel();
	
	public ReportExportTxt() {
		
	}
	
	public ReportExportTxt(ReportExportModel model) {
		this.model = model;
	}
	
	public void exportTxt(){
		File txtFile = new File(model.getFilePath());
		if(txtFile.exists()) txtFile.delete();
		try {
			txtFile.createNewFile();
			BufferedOutputStream writer =new BufferedOutputStream(new FileOutputStream(txtFile));
			writer.write((model.getReportTitle()+"\r\n\r\n").getBytes("gb2312"));
			writer.write(("模板创建人："+model.getCreateUser()+"\r\n").getBytes("gb2312"));
		    writer.write(("报表生成人："+model.getExportUser()+"\r\n").getBytes("gb2312"));
		    writer.write(("单位："+model.getCompany()+"\r\n").getBytes("gb2312"));
		    writer.write(("报表生成日期："+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"\r\n\r\n").getBytes("gb2312"));
		    
	        writer.write(("说 明\r\n").getBytes("gb2312"));
	        writer.write(("本报告包含机密信息，不得通过电子邮件、传真、或其它电子设备进行传播；不得对该文档做任何副本或者备份。除非经过授权，否则不得向任何人共享该文档内的任何信息。\r\n").getBytes("gb2312"));
	        writer.write((model.getTitle()+"是注册的商品标志，未经所有人授权许可，任何组织和个人不得使用于任何商业目的。\r\n").getBytes("gb2312"));
	        writer.write((model.getSupport()+"\r\n").getBytes("gb2312"));
	        writer.write(("\r\n"+model.getOwner()+"\r\n\r\n").getBytes("gb2312"));
	        
	        writer.write(("报表列表数据\r\n\r\n").getBytes("gb2312"));
	        writer.flush();
	        writer.close();
	        
	        List tableList = model.getTableList();
	        //调用原来的方法追加列表数据

	       writeTable(model.getSql());
	        
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeTable(String[] sql) throws IOException{
		JDBC db = new JDBC();
		ResultSet rs = null;
		File txtFile = new File(model.getFilePath());
		for(int j =0;j<sql.length;j++){
			List list = new ArrayList();
			String strSQL = sql[j];
			try {
				//System.out.println("strSQL:"+strSQL);
				rs = db.getResultSet(strSQL);
				//if(j==0){
					List title = new ArrayList();
					for (Reportformsinfo info : (List<Reportformsinfo>) model
							.getReportFormInfo()) {
						title.add(info.getColName());
					}
					list.add(title);
				//}
				while (rs.next()) {
					List<String> li = new ArrayList<String>();
					for (Reportformsinfo info : (List<Reportformsinfo>) model
							.getReportFormInfo()) {
						li.add(rs.getString(info.getColName()));
					}
					list.add(li);
				}
				
			} catch (SQLException e) {
				db.closeAll();
				e.printStackTrace();
			}
			
			/* FileWriter fw = new FileWriter(txtFile,true);
				PrintWriter bw = new PrintWriter(fw);
				int[] leng = this.getLengths(list);
				for (int i = 0; i < list.size(); i++) {
					if(j!=0 && i==0){
						continue;
					}
					List it = (List) list.get(i);
					bw.println(this.getLine(it, leng));
				}
				bw.flush();
				bw.close();*/
			FileOutputStream fos = new FileOutputStream(txtFile, true);
			int[] leng = this.getLengths(list);
			for (int i = 0; i < list.size(); i++) {
				if(j!=0 && i==0){
					continue;
				}
				List it = (List) list.get(i);
				fos.write((this.getLine(it, leng)+"\r\n").getBytes("gb2312"));
			}
			fos.flush();
			fos.close();
		}
	}
	
	public int[] getLengths(List list) {
		int[] lengths = new int[((List) list.get(0)).size()];
		for (int i = 0; i < lengths.length; i++) {
			lengths[i] = 0;
			for (int j = 0; j < list.size(); j++) {
				List it = (List) list.get(j);
				if (it.get(i) != null) {
					if (it.get(i).toString().getBytes().length > lengths[i]) {
						lengths[i] = it.get(i).toString().getBytes().length;
					}
				}
			}
			lengths[i] = lengths[i] + 3;
		}
		return lengths;
	}

	public String getLine(List it, int[] leng) throws UnsupportedEncodingException {
		StringBuffer lines = new StringBuffer();
		for (int i = 0; i < leng.length; i++) {
			if (it.get(i) != null) {
				lines.append(it.get(i));
				for (int j = it.get(i).toString().getBytes().length; j < leng[i]; j++) {
					lines.append(" ");
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

	public ReportExportModel getModel() {
		return model;
	}

	public void setModel(ReportExportModel model) {
		this.model = model;
	}
	
}
