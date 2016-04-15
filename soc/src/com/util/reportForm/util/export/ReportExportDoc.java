package com.util.reportForm.util.export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ReportExportModel;
import com.util.reportForm.util.JDBC;
import com.util.reportForm.util.export.RrfDocument.ReportTable;

public class ReportExportDoc {
	
	private ReportExportModel model = new ReportExportModel();
	
	RrfDocument doc=new RrfDocument();
    ReportTable table=doc.new ReportTable(2);
	
	public ReportExportDoc() {
		
	}
	
	public void exportDoc(){
		File docFile = new File(model.getFilePath());
		
		if(docFile.exists()){
			docFile.delete();
		}
		try {
			docFile.createNewFile();
//			Writer writer = new OutputStreamWriter(new FileOutputStream(docFile),"UTF-8");
//			FileWriter writer = new FileWriter(docFile);
			BufferedOutputStream writer =new BufferedOutputStream(new FileOutputStream(docFile));
			//设置文件头

			writer.write(doc.ReportHead().getBytes("gb2312"));
			for(int i=0;i<9;i++) writer.write(doc.PeportParagraph(" ").getBytes("gb2312"));
			writer.write(doc.PeportParagraph(model.getReportTitle(),36,"BC").getBytes("gb2312"));
		    //画间距

		    for(int i=0;i<20;i++) writer.write(doc.PeportParagraph(" ").getBytes("gb2312"));
		    table.set_width("40,60");
	        table.set_noborder();
	        table.add_cell(doc.new ReportCell(doc.PeportChunk("模板创建人：",16),"R"));
	        table.add_cell(doc.new ReportCell(doc.PeportChunk(model.getCreateUser(),16)));
	        table.add_cell(doc.new ReportCell(doc.PeportChunk("报表生成人：",16),"R"));
	        table.add_cell(doc.new ReportCell(doc.PeportChunk(model.getExportUser(),16)));
	        table.add_cell(doc.new ReportCell(doc.PeportChunk("单位：",16),"R"));
	        table.add_cell(doc.new ReportCell(doc.PeportChunk(model.getCompany(),16)));
	        table.add_cell(doc.new ReportCell(doc.PeportChunk("报表生成日期：",16),"R"));
	        table.add_cell(doc.new ReportCell(doc.PeportChunk(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),16)));
	        table.write_cell(writer);
	        writer.write(doc.NewPage().getBytes("gb2312"));
	        //说明信息
	        
	        writer.write(doc.PeportParagraph(" ",36).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph("说 明",36,"C").getBytes("gb2312"));
	        writer.write(doc.PeportParagraph(" ",36).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph("     本报告包含机密信息，不得通过电子邮件、传真、或其它电子设备进行传播；不得对该文档做任何副本或者备份。除非经过授权，否则不得向任何人共享该文档内的任何信息。",14).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph(" ",24).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph("     "+model.getTitle()+"是注册的商品标志，未经所有人授权许可，任何组织和个人不得使用于任何商业目的。",14).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph(" ",24).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph("     "+model.getSupport(),14).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph(" ",24).getBytes("gb2312"));
//	        writer.write(doc.PeportParagraph("     咨询电话："+SERVICE_NUM+"，没有开通800电话的地区请拨打："+PHONE_NUM,14).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph(" ",36).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph(" ",36).getBytes("gb2312"));
	        writer.write(doc.PeportParagraph(model.getOwner(),14,"R").getBytes("gb2312"));
	        writer.write(doc.NewPage().getBytes("gb2312"));
	        
	        //列表数据
	        writer.write(doc.Chapter("报表列表数据").getBytes("gb2312"));
	        
//	        setTable(writer);
	        
	        writeTable(model.getSql(),writer);
	        
	        String [] picPath = model.getPicPath();
	        
	        /*//图形数据
	       
	        if(picPath != null && !"".equals(picPath)){
	        	writer.write(doc.Chapter("报表图形数据").getBytes());
	        	
	        	writer.write(doc.Section("饼图").getBytes());
	        	table.set_width("100");
	        	table.set_noborder();
        		//table.add_cell(doc.new ReportCell(picPath[0],"Img",70));
        		table.write_cell(writer);
        		
		        writer.write(doc.Section("柱状图").getBytes());
		        table.set_width("100");
	        	table.set_noborder();
        		//table.add_cell(doc.new ReportCell(picPath[1],"Img",70));
        		table.write_cell(writer);
        		
		        writer.write(doc.Section("折线图").getBytes());
		        table.set_width("100");
	        	table.set_noborder();
        		//table.add_cell(doc.new ReportCell(picPath[2],"Img",70));
        		table.write_cell(writer);
		        
        		writer.write(doc.Section("雷达图").getBytes());
		        table.set_width("100");
	        	table.set_noborder();
        		//table.add_cell(doc.new ReportCell(picPath[3],"Img",70));
        		table.write_cell(writer);
		        
	        }*/
	        //文件尾

	        writer.write(doc.ReportEnd().getBytes("gb2312"));
	        writer.flush();
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    
	}
	
	public void writeTable(String[] sql,BufferedOutputStream writer) throws IOException{
		JDBC db = new JDBC();
		ResultSet rs = null;
		Iterator it = model.getReportFormInfo().iterator();
		Integer totalLength = 820;
		Integer currentLength = 0;
		Integer width[] = new Integer[model.getReportFormInfo().size()];
		int i =0;
		while(it.hasNext()){
			Reportformsinfo info = (Reportformsinfo)it.next();
			currentLength += Integer.parseInt(info.getColWidth());
			width[i] = Integer.parseInt(info.getColWidth());
			i++;
		}
		if(currentLength > totalLength){
			totalLength = currentLength;
		}
		String wth = "";
		for(Integer w : width){
			wth += String.valueOf(w*100/totalLength)+",";
		}
		wth = wth.substring(0,wth.length()-1);
		table.set_width(wth);
		
		
		for(int j =0;j<sql.length;j++){
			List list = new ArrayList();
			String strSQL = sql[j];
			try {
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
			
			for(i=0;i<list.size();i++){
				List li = (List)list.get(i);
				for(int k=0;k<li.size();k++){
					if(i==0){
						if(j==0){
							table.add_cell(doc.new ReportCell(li.get(k).toString(),"TC"));
						}
					}else{
						String str = parseString(String.valueOf(li.get(k)));
						table.add_cell(doc.new ReportCell(str,"C"));
					}
				}
			}
			try {
				table.write_cell(writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void setTable(BufferedOutputStream writer){
		Iterator it = model.getReportFormInfo().iterator();
		Integer totalLength = 820;
		Integer currentLength = 0;
		Integer width[] = new Integer[model.getReportFormInfo().size()];
		int i =0;
		while(it.hasNext()){
			Reportformsinfo info = (Reportformsinfo)it.next();
			currentLength += Integer.parseInt(info.getColWidth());
			width[i] = Integer.parseInt(info.getColWidth());
			i++;
		}
		if(currentLength > totalLength){
			totalLength = currentLength;
		}
		String wth = "";
		for(Integer w : width){
			wth += String.valueOf(w*100/totalLength)+",";
		}
		wth = wth.substring(0,wth.length()-1);
		table.set_width(wth);
		
		for(i=0;i<model.getTableList().size();i++){
			List li = (List)model.getTableList().get(i);
			for(int j=0;j<li.size();j++){
				if(i==0){
					table.add_cell(doc.new ReportCell(li.get(j).toString(),"TC"));
				}else{
					String str = parseString(String.valueOf(li.get(j)));
					table.add_cell(doc.new ReportCell(str,"C"));
				}
			}
		}
		try {
			table.write_cell(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//如果字符串中含有 '\' 进行转换
	public String parseString (String str){
		String s = str.replace("\\", "/");
		return s;
	}

	public ReportExportModel getModel() {
		return model;
	}

	public void setModel(ReportExportModel model) {
		this.model = model;
	}
	
	
}
