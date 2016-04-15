package com.util.reportForm.util.export;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.lowagie.text.Anchor;
import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.util.reportForm.datadeal.model.Reportformsinfo;
import com.util.reportForm.model.ReportExportModel;
import com.util.reportForm.util.JDBC;

public class ReportExportPdf {

	private ReportExportModel model = new ReportExportModel();
	
	Document document ; //报表文件
	Anchor anchor;
	Chapter chapter;
	Table table;
	Cell cell;
	Paragraph p;
	int firstinfo=0,lastinfo=0;
	BaseFont bfChinese;
	Font bfChinese12, bfChinese14, bfChinese16, bfChinese20;
	Font bfChinese32, bfChinese36;
	PdfPTable ptable = null;
    PdfPCell pcell = null;
	
	public ReportExportPdf(ReportExportModel model) {
		this.model = model;
	}
	public ReportExportPdf() {
		
	}
	
	public void exportPdf(){
		
		// 默认字体
		
		File pdfFile = new File(model.getFilePath());
		if(pdfFile.exists()) pdfFile.delete();
		try {
			//设置默认字体
			bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		    bfChinese12=new Font(bfChinese, 12, Font.NORMAL);
		    bfChinese14=new Font(bfChinese, 14, Font.NORMAL);
		    bfChinese16=new Font(bfChinese, 16, Font.NORMAL);
		    bfChinese20=new Font(bfChinese, 20, Font.NORMAL);
		    bfChinese32=new Font(bfChinese, 32, Font.NORMAL);
		    bfChinese36=new Font(bfChinese, 36, Font.NORMAL);
			pdfFile.createNewFile();
			document = new Document(PageSize.A4);
		    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
		    document.open();
		    p=new Paragraph("");
		    
		    //document.add(new Paragraph("            "+COMPANY_NAME+"            服务热线："+SERVICE_NUM+"           公司电话："+PHONE_NUM,bfChinese12));

	        //画红线

//	        Graphic g = new Graphic();
//	        g.drawHorizontalLine(1.5f,new Color(0xFF, 0x00, 0x00),40,560,775f);
//	        document.add(g);
	        //封面标题
	        p=new Paragraph(model.getReportTitle(),bfChinese36);
	        p.setAlignment("CENTER");
	        document.add(p);

	        table=new Table(2);
//	        table.setDefaultCellBorderColor(new Color(255,255,255));
	        table.setBorderColor(new Color(255,255,255));
//	        table.setBorderColor(new Color(0,0,0));
	        String[] tempStr=new String[]{"模板创建人:","报表生成人：","单位：","报表生成日期："};
	        for(int i=0;i<tempStr.length;i++){
	            cell=new Cell(new Paragraph(tempStr[i],bfChinese16));
	            cell.setHorizontalAlignment("RIGHT");
	            cell.setBorderColor(new Color(255,255,255));
	            table.addCell(cell);
	            if(i==0){
	            	cell=new Cell(new Paragraph(model.getCreateUser(),bfChinese16));
	            }else if(i==1){
	            	cell=new Cell(new Paragraph(model.getExportUser(),bfChinese16));
	            }else if(i==2){
	            	cell=new Cell(new Paragraph(model.getCompany(),bfChinese16));
	            }else if(i==3){
	            	cell=new Cell(new Paragraph(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),bfChinese16));
	            }
	            
	            cell.setBorderColor(new Color(255,255,255));
	            table.addCell(cell);
	          }
	         table.setOffset(450);
	         document.add(table);
	         document.newPage();//new page
	         
	         
	         //说明信息
	         document.add(new Paragraph(" ",bfChinese32));
	         p=new Paragraph("说 明",bfChinese32);
	         p.setAlignment("CENTER");
	         document.add(p);
	         document.add(new Paragraph(" ",bfChinese32));
	         document.add(new Paragraph("         本报告包含机密信息，不得通过电子邮件、传真、或其它电子设备进行传播；不得对该文档做任何副本或者备份。除非经过授权，否则不得向任何人共享该文档内的任何信息。",bfChinese14));
	         document.add(new Paragraph(" ",bfChinese20));
	         document.add(new Paragraph("         "+model.getTitle()+"是注册的商品标志，未经所有人授权许可，任何组织和个人不得使用于任何商业目的。",bfChinese14));
	         document.add(new Paragraph(" ",bfChinese20));
	         document.add(new Paragraph("         "+model.getSupport(),bfChinese14));
	         document.add(new Paragraph(" ",bfChinese20));
//	         document.add(new Paragraph("         咨询电话："+SERVICE_NUM+"，没有开通800电话的地区请拨打："+PHONE_NUM,bfChinese14));
	         document.add(new Paragraph(" ",bfChinese32));
	         p=new Paragraph(model.getOwner(),bfChinese14);
	         p.setAlignment("RIGHT");
	         document.add(p);
	         document.newPage();//new page
	         set_chapter("报表列表数据");
	         this.writeTable(model.getSql());
	         
	         document.close();
		    
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void setGraphic(String path) throws DocumentException, MalformedURLException, IOException{
		table = new Table(1);
        cell=new Cell();
//        //System.out.println("path--->"+path);
        Image jpeg = Image.getInstance(path);
        jpeg.setAlignment(2);
        cell.add(jpeg);
        table.addCell(cell);
        table.setWidth(70);
        document.add(table);
	}
	
	public void writeTable(String[] sql) throws IOException, DocumentException{
		JDBC db = new JDBC();
		ResultSet rs = null;
		
		List reportFormInfo = model.getReportFormInfo();
		
		Iterator it = reportFormInfo.iterator();
		Integer totalLength = 820;
		Integer currentLength = 0;
		float width[] = new float[reportFormInfo.size()];
		int i =0;
		while(it.hasNext()){
			Reportformsinfo info = (Reportformsinfo)it.next();
			currentLength += Integer.parseInt(info.getColWidth());
			width[i] = Float.parseFloat(info.getColWidth());
			i++;
		}
		if(currentLength > totalLength){
			totalLength = currentLength;
		}
		String wth = "";
		for(Float w : width){
			wth += String.valueOf(w*100/totalLength)+",";
		}
		wth = wth.substring(0,wth.length()-1);
		
		ptable = new PdfPTable(reportFormInfo.size());
		ptable.setWidths(width);
		
		for(int j =0;j<sql.length;j++){
			List list = new ArrayList();
			String strSQL = sql[j];
			try {
				rs = db.getResultSet(strSQL);
				if(j==0){
					List title = new ArrayList();
					for (Reportformsinfo info : (List<Reportformsinfo>) model
							.getReportFormInfo()) {
						title.add(info.getColName());
					}
					list.add(title);
				}
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
					pcell=new PdfPCell(new Paragraph(String.valueOf(li.get(k)),bfChinese12));
					pcell.setHorizontalAlignment(1);
		            ptable.addCell(pcell);
				}
			}
			
		}
		document.add(ptable);
	}
	
	
	public void setTable() throws DocumentException{
		List reportFormInfo = model.getReportFormInfo();
		List tableList = model.getTableList();
		Iterator it = reportFormInfo.iterator();
		Integer totalLength = 820;
		Integer currentLength = 0;
		float width[] = new float[reportFormInfo.size()];
		int i =0;
		while(it.hasNext()){
			Reportformsinfo info = (Reportformsinfo)it.next();
			currentLength += Integer.parseInt(info.getColWidth());
			width[i] = Float.parseFloat(info.getColWidth());
			i++;
		}
		if(currentLength > totalLength){
			totalLength = currentLength;
		}
		String wth = "";
		for(Float w : width){
			wth += String.valueOf(w*100/totalLength)+",";
		}
		wth = wth.substring(0,wth.length()-1);
		
		ptable = new PdfPTable(reportFormInfo.size());
		ptable.setWidths(width);
		
		
//		
		for(i=0;i<tableList.size();i++){
			List li = (List)tableList.get(i);
			for(int j=0;j<li.size();j++){
//				if(i==0){
//					table.add_cell(doc.new ReportCell(li.get(j).toString(),"TC"));
//				}else{
//					table.add_cell(doc.new ReportCell(li.get(j).toString(),"C"));
//				}
				pcell=new PdfPCell(new Paragraph(String.valueOf(li.get(j)),bfChinese12));
				pcell.setHorizontalAlignment(1);
	            ptable.addCell(pcell);
			}
		}
		document.add(ptable);
		
	}
	
	 //写标题

	  public void set_chapter(String content) throws DocumentException {
	    firstinfo++;
	    lastinfo=1;
	    chapter = new Chapter(new Paragraph(content,bfChinese16),firstinfo);
	    document.add(chapter);
	    anchor=new Anchor(firstinfo+"-"+lastinfo);
	    anchor.setReference("#TOC-"+firstinfo+"-"+lastinfo);
	    anchor.setName(firstinfo+"-"+lastinfo);
	    p=new Paragraph();
	    p.add(anchor);
	    p.setAlignment("RIGHT");
	    document.add(p);
	  }
	  public void set_section(String content) throws DocumentException{
	    lastinfo++;
	    table=new Table(2);
	   //table.setDefaultCellBorderColor(new Color(255,255,255));
	    table.setBorderColor(new Color(255,255,255));
	    cell=new Cell(new Paragraph(content,bfChinese14));
	    cell.setBorderColor(new Color(255,255,255));
	    table.addCell(cell);
	    anchor=new Anchor(firstinfo+"-"+lastinfo);
	    anchor.setReference("#TOC-"+firstinfo+"-"+lastinfo);
	    anchor.setName(firstinfo+"-"+lastinfo);
	    cell=new Cell();
	    cell.setHorizontalAlignment("RIGHT");
	    cell.add(anchor);
	    cell.setBorderColor(new Color(255,255,255));
	    table.addCell(cell);
	    table.setWidth(100);
	    document.add(table);
	  }
	public ReportExportModel getModel() {
		return model;
	}
	public void setModel(ReportExportModel model) {
		this.model = model;
	}
}
