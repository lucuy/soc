package com.util.load.data;


import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.Image;
import com.util.load.Load;
 

public class WordUtil {
 private static Document document;
 private static BaseFont baseFont;

//创建word，并设置纸张文档

 private static void openWordFile(String fileName) throws DocumentException,
 IOException {
document = new Document(PageSize.A4);
RtfWriter2.getInstance(document, new FileOutputStream(fileName));
document.open();
baseFont = BaseFont.createFont(null);
}

//设置标题

 private static boolean setTitle(String title) throws DocumentException {
  Font font = new Font(baseFont, 30, Font.BOLD);
  Paragraph pTitle = new Paragraph(title+"系统历次差距评估结果对比表" + "\n");
  pTitle.setFont(font);
  pTitle.setAlignment(Element.ALIGN_CENTER);
  
  
  Font font2 = new Font(baseFont, 10, Font.BOLD);
  
  SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"); 
 String str= myFmt.format(new Date());
  Paragraph pTitle2 = new Paragraph("["+str+"]");
  pTitle2.setFont(font2);
  pTitle2.setAlignment(Element.ALIGN_BOTTOM);
  
  
  return document.add(pTitle)&&document.add(pTitle2);

 }
 
//设置柱形图片

 private static boolean setZhuChuang(String filepath,int i) throws Exception {
  Font font = new Font(baseFont, 15, Font.BOLD);
  Paragraph pTitle = new Paragraph(i+".1  各项安全项符合度柱状表" + "\n");
  pTitle.setFont(font);
  pTitle.setAlignment(Element.ALIGN_TOP);
//在表格末尾添加图片      
  Image png = Image.getInstance(filepath);   
  png.scaleAbsolute(500, 200);
  
  
  return document.add(pTitle)&& document.add(png);

 }
 
//设置折线图图片

 private static boolean setLine(String filepath,int i) throws Exception {
  Font font = new Font(baseFont, 20, Font.BOLD);
  Paragraph pTitle = new Paragraph(i+"   历次评估符合度比折线表" + "\n");
  pTitle.setFont(font);
  pTitle.setAlignment(Element.ALIGN_TOP);
//在表格末尾添加图片      
  Image png = Image.getInstance(filepath);   
  png.scaleAbsolute(500, 200);
  
  
  return document.add(pTitle)&& document.add(png);

 }


//设置文档内容

 private static boolean setContent(String content0,String content) throws  Exception {
	 
	 document.add(Chunk.NEXTPAGE);
  Font font = new Font(baseFont, 10, Font.NORMAL);
  Paragraph pContent = new Paragraph(content);
  pContent = new Paragraph();
  pContent.setAlignment(0);
  pContent.add(new Chunk("1  "+content0+"差距评估结果", new Font(baseFont, 18, Font.BOLD)));
  Table table1 = new Table(8);
	table1.setWidth(110.0F);
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	Cell cell = new Cell("");
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.setBorderColorTop(Color.BLUE);
	table1.addCell(new Phrase(new Chunk("安全领域" , new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(new Phrase(new Chunk("标准控制单元", new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(new Phrase(new Chunk("适用控制单元", new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(new Phrase(new Chunk("符合单元", new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(new Phrase(new Chunk("部分符合单元", new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(new Phrase(new Chunk("不符合单元", new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(new Phrase(new Chunk("差距单元", new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(new Phrase(new Chunk("符合度", new Font(baseFont, 10, Font.BOLD))));
	table1.addCell(cell);
	
	///
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.setBorderColor(new Color(0, 125, 255));
	table1.addCell(new Phrase(new Chunk("基础网络安全")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("100%")));
	table1.addCell(cell);
	
	
	
	
	///
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.setBorderColor(Color.GREEN);
	table1.addCell(new Phrase(new Chunk("边界安全")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("100%")));
	table1.addCell(cell);
	
	
	/////
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.addCell(new Phrase(new Chunk("终端系统安全")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("100%")));
	table1.addCell(cell);

	
	/////
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.addCell(new Phrase(new Chunk("服务端系统安全")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("100%")));
	table1.addCell(cell);
	

	
	/////
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.addCell(new Phrase(new Chunk("应用安全")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("100%")));
	table1.addCell(cell);


	
	/////
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.addCell(new Phrase(new Chunk("数据安全域备份复")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("100%")));
	table1.addCell(cell);


	
	/////
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
	table1.setBackgroundColor(new Color(0, 125, 255));
	table1.addCell(new Phrase(new Chunk("安全管理中心")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("3")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("0")));
	table1.addCell(new Phrase(new Chunk("100%")));
	table1.addCell(cell);


	
	/////
	table1.setWidths(new int[] { 5, 5,5,5,5,5,5,5});
	cell.setColspan(9);
	cell.setRowspan(8);
table1.setBackgroundColor(Color.RED);
	table1.addCell(new Phrase(new Chunk("合计")));
	table1.addCell(new Phrase(new Chunk("32")));
	table1.addCell(new Phrase(new Chunk("12")));
	table1.addCell(new Phrase(new Chunk("34")));
	table1.addCell(new Phrase(new Chunk("40")));
	table1.addCell(new Phrase(new Chunk("25")));
	table1.addCell(new Phrase(new Chunk("27")));
	table1.addCell(new Phrase(new Chunk("60%")));
	table1.addCell(cell);
	
	pContent.add(table1);
	
	
	
	
	
  return document.add(pContent);
 }

//对外使用的接口

 public static boolean CreateWordFile(String url, String title,String content0,
   String contents,String filePath,String filePath2) throws Exception {
  boolean returnValue = false;
  try {
   openWordFile(url);
   returnValue = setTitle(title);
   for (int i = 1; i < 3; i++) {
	   CreateTables(content0, contents,i);
	   setZhuChuang(filePath,i);
	   
	   
}
   
   setLine(filePath2, 3);
   
   CreateTablesCount("", "");
   
 //  setContent(content0, contents);
  
   
//   for (int i = 0; i < contents.size(); i++) {
//    returnValue = returnValue && setContent(contents.get(i));
//   }
   document.close();
  } catch (DocumentException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }

  return returnValue;

 }
// //对外使用的接口
// public static boolean CreateWordFile(String url, String title,
//   String content) {
//  boolean returnValue = false;
//  try {
//   openWordFile(url);
//   returnValue = setTitle(title);
//   returnValue = returnValue && setContent(content);
//   document.close();
//  } catch (DocumentException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  } catch (IOException e) {
//   // TODO Auto-generated catch block
//   e.printStackTrace();
//  }
//
//  return returnValue;
//
// }

 
 public static void CreateTables(String content0,String content,int i) throws Exception{
	 document.add(Chunk.NEXTPAGE);
	 
	  Font font = new Font(baseFont, 10, Font.NORMAL);
	  Paragraph pContent = new Paragraph(content);
	  pContent = new Paragraph();
	  pContent.setAlignment(0);
	  pContent.add(new Chunk(i+"  "+content0+"差距评估结果", new Font(baseFont, 18, Font.BOLD)));
	  
	  document.add(pContent);
	  
	 // 设置 Table 表格
	 Table aTable = new Table(8);
	 int width[] = new int[] { 5, 5,5,5,5,5,5,5};
	 aTable.setWidths(width);//设置每列所占比例
	 aTable.setWidth(100); // 占页面宽度 100%

	 aTable.setAlignment(Element.ALIGN_CENTER);//居中显示
	 aTable.setAlignment(Element.ALIGN_MIDDLE);//纵向居中显示
	 aTable.setAutoFillEmptyCells(true); //自动填满
	 aTable.setBorderWidth(1); //边框宽度
	 aTable.setBorderColor(new Color(0, 125, 255)); //边框颜色
	 aTable.setPadding(0);//衬距，看效果就知道什么意思了
	 aTable.setSpacing(0);//即单元格之间的间距
	 aTable.setBorder(2);//边框

	 //设置表头
	 /**
	 * cell.setHeader(true);是将该单元格作为表头信息显示；
	 * cell.setColspan(3);指定了该单元格占3列；
	 * 为表格添加表头信息时，要注意的是一旦表头信息添加完了之后， \
	 * 必须调用 endHeaders()方法，否则当表格跨页后，表头信息不会再显示
	 */
	 Cell haderCell1 = new Cell("安全领域");
	 Cell haderCell2 = new Cell("标准控制单元");
	 Cell haderCell3 = new Cell("适用控制单元");
	 Cell haderCell4 = new Cell("符合单元");
	 Cell haderCell5 = new Cell("部分符合单元");
	 Cell haderCell6 = new Cell("不符合单元");
	 Cell haderCell7 = new Cell("差距单元");
	 Cell haderCell8 = new Cell("符合度");
	 haderCell1.setBackgroundColor(Color.YELLOW);
	 haderCell2.setBackgroundColor(Color.YELLOW);
	 haderCell3.setBackgroundColor(Color.YELLOW);
	 haderCell4.setBackgroundColor(Color.YELLOW);
	 haderCell5.setBackgroundColor(Color.YELLOW);
	 haderCell6.setBackgroundColor(Color.YELLOW);
	 haderCell7.setBackgroundColor(Color.YELLOW);
	 haderCell8.setBackgroundColor(Color.YELLOW);
	 aTable.addCell(haderCell1);
	 aTable.addCell(haderCell2);
	 aTable.addCell(haderCell3);
	 aTable.addCell(haderCell4);
	 aTable.addCell(haderCell5);
	 aTable.addCell(haderCell6);
	 aTable.addCell(haderCell7);
	 aTable.addCell(haderCell8);
	 aTable.endHeaders();
	 
	 
	 Cell haderCell11 = new Cell("基础网络安全");
	 Cell haderCell22 = new Cell("22");
	 Cell haderCell33 = new Cell("33");
	 Cell haderCell44 = new Cell("44");
	 Cell haderCell55 = new Cell("55");
	 Cell haderCell66 = new Cell("66");
	 Cell haderCell77 = new Cell("22");
	 Cell haderCell88 = new Cell("87%");
	 aTable.addCell(haderCell11);
	 aTable.addCell(haderCell22);
	 aTable.addCell(haderCell33);
	 aTable.addCell(haderCell44);
	 aTable.addCell(haderCell55);
	 aTable.addCell(haderCell66);
	 aTable.addCell(haderCell77);
	 aTable.addCell(haderCell88);
	 
	 
	 Cell haderCellq1 = new Cell("边界安全");
	 Cell haderCellq2 = new Cell("21");
	 Cell haderCellq3 = new Cell("42");
	 Cell haderCellq4 = new Cell("76");
	 Cell haderCellq5 = new Cell("53");
	 Cell haderCellq6 = new Cell("92");
	 Cell haderCellq7 = new Cell("42");
	 Cell haderCellq8 = new Cell("36%");
	 haderCellq1.setBackgroundColor(Color.YELLOW);
	 haderCellq2.setBackgroundColor(Color.YELLOW);
	 haderCellq3.setBackgroundColor(Color.YELLOW);
	 haderCellq4.setBackgroundColor(Color.YELLOW);
	 haderCellq5.setBackgroundColor(Color.YELLOW);
	 haderCellq6.setBackgroundColor(Color.YELLOW);
	 haderCellq7.setBackgroundColor(Color.YELLOW);
	 haderCellq8.setBackgroundColor(Color.YELLOW);
	 aTable.addCell(haderCellq1);
	 aTable.addCell(haderCellq2);
	 aTable.addCell(haderCellq3);
	 aTable.addCell(haderCellq4);
	 aTable.addCell(haderCellq5);
	 aTable.addCell(haderCellq6);
	 aTable.addCell(haderCellq7);
	 aTable.addCell(haderCellq8);
	 
	 
	 Cell haderCell111 = new Cell("终端系统安全");
	 Cell haderCell222 = new Cell("21");
	 Cell haderCell333 = new Cell("42");
	 Cell haderCell444 = new Cell("76");
	 Cell haderCell555 = new Cell("53");
	 Cell haderCell666 = new Cell("92");
	 Cell haderCell777 = new Cell("42");
	 Cell haderCell888 = new Cell("36%");
	 aTable.addCell(haderCell111);
	 aTable.addCell(haderCell222);
	 aTable.addCell(haderCell333);
	 aTable.addCell(haderCell444);
	 aTable.addCell(haderCell555);
	 aTable.addCell(haderCell666);
	 aTable.addCell(haderCell777);
	 aTable.addCell(haderCell888);
	 
	 
	 Cell haderCellqq1 = new Cell("服务端系统安全");
	 Cell haderCellqq2 = new Cell("21");
	 Cell haderCellqq3 = new Cell("42");
	 Cell haderCellqq4 = new Cell("76");
	 Cell haderCellqq5 = new Cell("53");
	 Cell haderCellqq6 = new Cell("92");
	 Cell haderCellqq7 = new Cell("42");
	 Cell haderCellqq8 = new Cell("36%");
	 haderCellqq1.setBackgroundColor(Color.YELLOW);
	 haderCellqq2.setBackgroundColor(Color.YELLOW);
	 haderCellqq3.setBackgroundColor(Color.YELLOW);
	 haderCellqq4.setBackgroundColor(Color.YELLOW);
	 haderCellqq5.setBackgroundColor(Color.YELLOW);
	 haderCellqq6.setBackgroundColor(Color.YELLOW);
	 haderCellqq7.setBackgroundColor(Color.YELLOW);
	 haderCellqq8.setBackgroundColor(Color.YELLOW);
	 aTable.addCell(haderCellqq1);
	 aTable.addCell(haderCellqq2);
	 aTable.addCell(haderCellqq3);
	 aTable.addCell(haderCellqq4);
	 aTable.addCell(haderCellqq5);
	 aTable.addCell(haderCellqq6);
	 aTable.addCell(haderCellqq7);
	 aTable.addCell(haderCellqq8);
	 
	 Cell haderCellqqq1 = new Cell("应用安全");
	 Cell haderCellqqq2 = new Cell("21");
	 Cell haderCellqqq3 = new Cell("42");
	 Cell haderCellqqq4 = new Cell("76");
	 Cell haderCellqqq5 = new Cell("53");
	 Cell haderCellqqq6 = new Cell("92");
	 Cell haderCellqqq7 = new Cell("42");
	 Cell haderCellqqq8 = new Cell("36%");
	 aTable.addCell(haderCellqqq1);
	 aTable.addCell(haderCellqqq2);
	 aTable.addCell(haderCellqqq3);
	 aTable.addCell(haderCellqqq4);
	 aTable.addCell(haderCellqqq5);
	 aTable.addCell(haderCellqqq6);
	 aTable.addCell(haderCellqqq7);
	 aTable.addCell(haderCellqqq8);
	 
	 
	 Cell haderCellqqw1 = new Cell("数据安全域备份复");
	 Cell haderCellqqw2 = new Cell("21");
	 Cell haderCellqqw3 = new Cell("42");
	 Cell haderCellqqw4 = new Cell("76");
	 Cell haderCellqqw5 = new Cell("53");
	 Cell haderCellqqw6 = new Cell("92");
	 Cell haderCellqqw7 = new Cell("42");
	 Cell haderCellqqw8 = new Cell("36%");
	 haderCellqqw1.setBackgroundColor(Color.YELLOW);
	 haderCellqqw2.setBackgroundColor(Color.YELLOW);
	 haderCellqqw3.setBackgroundColor(Color.YELLOW);
	 haderCellqqw4.setBackgroundColor(Color.YELLOW);
	 haderCellqqw5.setBackgroundColor(Color.YELLOW);
	 haderCellqqw6.setBackgroundColor(Color.YELLOW);
	 haderCellqqw7.setBackgroundColor(Color.YELLOW);
	 haderCellqqw8.setBackgroundColor(Color.YELLOW);
	 aTable.addCell(haderCellqq1);
	 aTable.addCell(haderCellqq2);
	 aTable.addCell(haderCellqq3);
	 aTable.addCell(haderCellqq4);
	 aTable.addCell(haderCellqq5);
	 aTable.addCell(haderCellqq6);
	 aTable.addCell(haderCellqq7);
	 aTable.addCell(haderCellqq8);
	 
	 
	 
	 Cell haderCellwqqq1 = new Cell("安全管理中心");
	 Cell haderCellwqqq2 = new Cell("21");
	 Cell haderCellwqqq3 = new Cell("42");
	 Cell haderCellwqqq4 = new Cell("76");
	 Cell haderCellwqqq5 = new Cell("53");
	 Cell haderCellwqqq6 = new Cell("92");
	 Cell haderCellwqqq7 = new Cell("42");
	 Cell haderCellwqqq8 = new Cell("36%");
	 aTable.addCell(haderCellwqqq1);
	 aTable.addCell(haderCellwqqq2);
	 aTable.addCell(haderCellwqqq3);
	 aTable.addCell(haderCellwqqq4);
	 aTable.addCell(haderCellwqqq5);
	 aTable.addCell(haderCellwqqq6);
	 aTable.addCell(haderCellwqqq7);
	 aTable.addCell(haderCellwqqq8);
	 
	 
	 
	 
	 Cell haderCellaqqw1 = new Cell("合计");
	 Cell haderCellaqqw2 = new Cell("21");
	 Cell haderCellaqqw3 = new Cell("42");
	 Cell haderCellaqqw4 = new Cell("76");
	 Cell haderCellaqqw5 = new Cell("53");
	 Cell haderCellaqqw6 = new Cell("92");
	 Cell haderCellaqqw7 = new Cell("42");
	 Cell haderCellaqqw8 = new Cell("36%");
	 haderCellaqqw1.setBackgroundColor(Color.YELLOW);
	 haderCellaqqw2.setBackgroundColor(Color.YELLOW);
	 haderCellaqqw3.setBackgroundColor(Color.YELLOW);
	 haderCellaqqw4.setBackgroundColor(Color.YELLOW);
	 haderCellaqqw5.setBackgroundColor(Color.YELLOW);
	 haderCellaqqw6.setBackgroundColor(Color.YELLOW);
	 haderCellaqqw7.setBackgroundColor(Color.YELLOW);
	 haderCellaqqw8.setBackgroundColor(Color.YELLOW);
	 aTable.addCell(haderCellaqqw1);
	 aTable.addCell(haderCellaqqw2);
	 aTable.addCell(haderCellaqqw3);
	 aTable.addCell(haderCellaqqw4);
	 aTable.addCell(haderCellaqqw5);
	 aTable.addCell(haderCellaqqw6);
	 aTable.addCell(haderCellaqqw7);
	 aTable.addCell(haderCellaqqw8);
	 
//	 
//	// 设置中文字体
//	 BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
//	 "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//	 Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.GREEN);
//	 Cell cell = new Cell(new Phrase("这是一个测试的 3*3 Table 数据", fontChinese ));
//	 cell.setVerticalAlignment(Element.ALIGN_TOP);
//	 cell.setBorderColor(new Color(255, 0, 0));
//	 cell.setRowspan(2);
//	 aTable.addCell(cell);
//
//	 aTable.addCell(new Cell("#1"));
//	 aTable.addCell(new Cell("#2"));
//	 aTable.addCell(new Cell("#3"));
//	 aTable.addCell(new Cell("#4"));
//	 Cell cell3 = new Cell(new Phrase("一行三列数据", fontChinese ));
//	 cell3.setColspan(3);
//	 cell3.setVerticalAlignment(Element.ALIGN_CENTER);
//	 aTable.addCell(cell3);

	 document.add(aTable);
	 document.add(new Paragraph("\n"));
	 
 }
 
 
 
 

 public static void CreateTablesCount(String content0,String content) throws Exception{
	 
	 
	  
	 // 设置 Table 表格
	 Table aTable = new Table(4);
	 int width[] = new int[] { 5, 5,5,5};
	 aTable.setWidths(width);//设置每列所占比例
	 aTable.setWidth(100); // 占页面宽度 100%

	 aTable.setAlignment(Element.ALIGN_CENTER);//居中显示
	 aTable.setAlignment(Element.ALIGN_MIDDLE);//纵向居中显示
	 aTable.setAutoFillEmptyCells(true); //自动填满
	 aTable.setBorderWidth(1); //边框宽度
	 aTable.setBorderColor(new Color(0, 125, 255)); //边框颜色
	 aTable.setPadding(0);//衬距，看效果就知道什么意思了
	 aTable.setSpacing(0);//即单元格之间的间距
	 aTable.setBorder(2);//边框

	 //设置表头
	 /**
	 * cell.setHeader(true);是将该单元格作为表头信息显示；
	 * cell.setColspan(3);指定了该单元格占3列；
	 * 为表格添加表头信息时，要注意的是一旦表头信息添加完了之后， \
	 * 必须调用 endHeaders()方法，否则当表格跨页后，表头信息不会再显示
	 */
	 Cell haderCell1 = new Cell("系统名称");
	 Cell haderCell2 = new Cell("TeamCoal系统20120302");
	 Cell haderCell3 = new Cell("TeamCoal系统20120909");
	 Cell haderCell4 = new Cell("TeamCoal系统20130405");
	 haderCell1.setBackgroundColor(Color.YELLOW);
	 haderCell2.setBackgroundColor(Color.YELLOW);
	 haderCell3.setBackgroundColor(Color.YELLOW);
	 haderCell4.setBackgroundColor(Color.YELLOW);
	 aTable.addCell(haderCell1);
	 aTable.addCell(haderCell2);
	 aTable.addCell(haderCell3);
	 aTable.addCell(haderCell4);
	 aTable.endHeaders();
	 
	 
	 Cell haderCell11 = new Cell("符合度");
	 Cell haderCell22 = new Cell("9.23%");
	 Cell haderCell33 = new Cell("20.00%");
	 Cell haderCell44 = new Cell("55.38%");
	 aTable.addCell(haderCell11);
	 aTable.addCell(haderCell22);
	 aTable.addCell(haderCell33);
	 aTable.addCell(haderCell44);

	 document.add(aTable);
	 document.add(new Paragraph("\n"));
	 
 }
public static void main(String[] args ) throws Exception {
  WordUtil wordUtil = new WordUtil();

  
  
  
//传入内容为字符串
  //wordUtil.CreateWordFile("e:\\word.doc", "TeamCoal","TeamCoal系统20120302", "我爱Java","d:/TeamCoal20120909.png");

//传入内容为字符串List
  //wordUtil.CreateWordFile("e:\\word.doc", "标题居中", strList);

 }
}
