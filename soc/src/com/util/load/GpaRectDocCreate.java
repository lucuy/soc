package com.util.load;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.compliance.model.cpManage.gpaShow.GpaRect;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.util.DateUtil;

/**
 * 通用物理整改建议报告文档创建
 * @author quyongkun
 *
 */
public class GpaRectDocCreate {
	
	/**
	 * cell设置居中
	 * @param cell
	 */
	public static void setLocal(Cell cell){
		cell.setVerticalAlignment(1);
		cell.setHorizontalAlignment(1);
	}
	
	/**
	 * 创建通用物理整改建议报告文档
	 * @param response 
	 * @param partcount 部分符合项个数
	 * @param inconfCount 不符合项个数
	 * @param gpaRect1 部分符合项集合
	 * @param gpaRect2 不符合项集合
	 */
	public static void createGpaRectDoc(HttpServletResponse response,int partcount,int inconfCount,List<GpaRect> gpaRect1,List<GpaRect> gpaRect2){
		
		String dirPath =Thread.currentThread().getContextClassLoader().getResource("").getPath()+"gpaRectDoc";
		File gpaFile = new File(dirPath);
		boolean filePath = gpaFile.exists();
		if(!filePath){
			gpaFile.mkdirs();
		}
		String fileName="通用物理整改建议"+DateUtil.curDateTimeStr14()+".doc";
	
  	        Document document = new Document(PageSize.A4, 90.0F, 90.0F, 50.0F,40.0F);
			try {
				RtfWriter2.getInstance(document, new FileOutputStream(dirPath+"\\"+fileName));
				document.open();
				String blackFontPath="";
				int sdf = System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS");
				if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
					blackFontPath = "c:\\windows\\fonts\\simhei.ttf";
				}else{
					blackFontPath="/usr/share/fonts/dejavu/DejaVuSansMono-Bold.ttf";
				}
				BaseFont songFont = BaseFont.createFont(blackFontPath,"Identity-H", false);
				Font songfont_11 = new Font(songFont, 11.0F, 0);
				
				Font oneFont=new Font(songFont, 20.0F, 1);
				Font twoFont=new Font(songFont, 15.0F, 1);
				Font threeFont=new Font(songFont, 13.0F, 1);

				Cell cell=null;
				
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				/**
				 * @author liujingjing
				 * 去掉文档中的compliance
				 * 2013-7-31
				 */
				/*Paragraph p = new Paragraph("[COMPLIANCE]");
				p.setFont(new Font(songFont, 28.0F, 1));//1表示加粗
				p.setAlignment(1);
				document.add(p);*/
				
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
			
				Paragraph p = new Paragraph("通用物理整改建议报告");
				p.setFont(new Font(songFont, 30.0F, 1));
				p.setAlignment(1);
				document.add(p);
				
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
                
				p = new Paragraph("["+DateUtil.curDate21()+"]");
				p.setFont(new Font(songFont, 15.0F, 1));
				p.setAlignment(1);
				document.add(p);
				
				document.add(Chunk.NEXTPAGE);
				
				p = new Paragraph("1.整改建议");
				p.setFont(oneFont);
				document.add(p);
				
				document.add(Chunk.NEWLINE);
				
				p = new Paragraph("1.1.需求分析");
				p.setFont(twoFont);
				document.add(p);
				
                document.add(Chunk.NEWLINE);
				
				p = new Paragraph("1.1.1.部分符合项：("+partcount+")个");
				p.setFont(threeFont);
				document.add(p);
				
				document.add(Chunk.NEWLINE);
				p = new Paragraph("　　　将确定的部分符合项归并后如下表所示：");
				document.add(p);
				
				
				
				Table table1 = new Table(5);
				table1.setWidth(110.0F);
				table1.setWidths(new int[] { 5, 5, 5, 5,5 });
				
				//表头开始
				cell=new Cell(new Phrase(new Chunk("序号", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("控制域", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("控制点", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("差距项", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("整改建议", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				//表头结束
				
				//部分符合数据开始
				int i=1;
				for(GpaRect gpaRect:gpaRect1){
					cell=new Cell(new Phrase(new Chunk(""+i, songfont_11)));
					setLocal(cell);
					table1.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getgFatherSort()+gpaRect.getgFatherName(), songfont_11)));
					setLocal(cell);
					table1.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getFatherSort()+gpaRect.getFatherName(), songfont_11)));
					setLocal(cell);
					table1.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getSonContent(), songfont_11)));
					setLocal(cell);
					table1.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getGpaRectAdvise()==null?"":gpaRect.getGpaRectAdvise(), songfont_11)));
					setLocal(cell);
					table1.addCell(cell);
					i++;
				}
				document.add(table1);
				//数据结束
				
				
				document.add(Chunk.NEWLINE);
				p = new Paragraph("1.1.2.不符合项：("+inconfCount+")个");
				p.setFont(threeFont);
				document.add(p);
				
				document.add(Chunk.NEWLINE);
				p = new Paragraph("　　　将确定的不符合项归并后如下表所示：");
				document.add(p);
				
				Table table2 = new Table(5);
				table2.setWidth(110.0F);
				table2.setWidths(new int[] { 5, 5, 5, 5,5 });
				
				//表头开始
				cell=new Cell(new Phrase(new Chunk("序号", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("控制域", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("控制点", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("差距项", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell=new Cell(new Phrase(new Chunk("整改建议", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				//表头结束
				
				//数据开始
				int j=1;
				for(GpaRect gpaRect:gpaRect2){
					cell=new Cell(new Phrase(new Chunk(""+j, songfont_11)));
					setLocal(cell);
					table2.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getgFatherSort()+gpaRect.getgFatherName(), songfont_11)));
					setLocal(cell);
					table2.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getFatherSort()+gpaRect.getFatherName(), songfont_11)));
					setLocal(cell);
					table2.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getSonContent(), songfont_11)));
					setLocal(cell);
					table2.addCell(cell);
					cell=new Cell(new Phrase(new Chunk(gpaRect.getGpaRectAdvise()==null?"":gpaRect.getGpaRectAdvise(), songfont_11)));
					setLocal(cell);
					table2.addCell(cell);	
					j++;
				}
				
				document.add(table2);
				//数据结束
				
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				document.close();
			}
			
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;charset=UTF-8");		
			try {
				File resFile=new File(dirPath+"\\"+fileName);

				if (resFile!=null && resFile.exists()) {
					
					response.addHeader("Content-Disposition","attachment;filename="+java.net.URLEncoder.encode(fileName, "UTF-8"));
					
					FileInputStream fileInputStream = null;
					byte[] buf = new byte[1024];
					int readLength;
					try {
						fileInputStream = new FileInputStream(dirPath+"\\"+fileName);
						while ((readLength = fileInputStream.read(buf)) != -1) {
							response.getOutputStream().write(buf, 0, readLength);
						}
						response.getOutputStream().flush();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						
						
						try {
							fileInputStream.close();
							response.getOutputStream().close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}finally{
							resFile.delete();
						}
					}
				} else {
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		
		
	}
	





}
