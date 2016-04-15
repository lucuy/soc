package com.util.load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.basicinfo.unitinfo.UnitInfo;
import com.compliance.model.rank.Rank;
import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * 备案文档创建
 * @author quyongkun
 *
 */
public class RecordDocCreate {
	
	/**
	 * cell设置居中
	 * @param cell
	 */
	public static void setLocal(Cell cell){
		cell.setVerticalAlignment(1);
		cell.setHorizontalAlignment(1);
	}
	
	/**
	 * 创建备案文档
	 * @param unitInfo 单位信息
	 * @param systemManager 信息系统
	 * @param rank 定级
	 * @param countTol 信息系统总数
	 * @param countSec 第二级信息系统数
	 * @param countThr 第三级信息系统数
	 * @param countThir第四级信息系统数
	 * @param countMay 第五级信息系统数
	 * @param fileName 备案文档名称
	 * @return 是否创建   1：表示创建成功
	 */
	public static int createRecordDoc(UnitInfo unitInfo,SystemManager systemManager,Rank rank,int countTol,int countSec,int countThr,int countThir,int countMay,File fileName){

  	        Document document = new Document(PageSize.A4, 90.0F, 90.0F, 50.0F,40.0F);
			try {
				RtfWriter2.getInstance(document, new FileOutputStream(fileName));
				document.open();

				String songPath="";
				String blackFontPath="";
				String fangsongPath="";
				String wingdings2FontPath="";
				if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
					songPath = "c:\\windows\\fonts\\msyh.ttf";
					blackFontPath = "c:\\windows\\fonts\\simhei.ttf";
					fangsongPath = "C:\\Windows\\Fonts\\simfang.ttf";
					wingdings2FontPath = "c:\\windows\\fonts\\WINGDNG2.TTF";
				}else{
					   /*songPath="/usr/share/fonts/truetype/ttf-dejavu/DejaVuSansMono.ttf";
					blackFontPath="/usr/share/fonts/truetype/ttf-dejavu/DejaVuSansMono-Bold.ttf";
					 fangsongPath="/usr/share/fonts/truetype/ttf-dejavu/DejaVuSerif.ttf";
			   wingdings2FontPath="/usr/share/fonts/truetype/unifont/unifont.ttf";*/
					songPath="/usr/share/fonts/dejavu/DejaVuSansMono.ttf";
					blackFontPath="/usr/share/fonts/dejavu/DejaVuSansMono-Bold.ttf";
					 fangsongPath="/usr/share/fonts/dejavu/DejaVuSerif.ttf";
			   wingdings2FontPath="/usr/share/fonts/dejavu/unifont.ttf";
				}


				BaseFont blackBaseFont = BaseFont.createFont(songPath,"Identity-H", false);
				BaseFont songFont = BaseFont.createFont(blackFontPath,"Identity-H", false);
				BaseFont fangsongFont = BaseFont.createFont(fangsongPath,"Identity-H", false);
				BaseFont wingdings2Font = BaseFont.createFont(wingdings2FontPath,"Identity-H", false);

				Font songfont_11 = new Font(songFont, 11.0F, 0);
				Font songfontUnderLine_11 = new Font(songFont, 11.0F, 4);

				Chunk rightSign = new Chunk("R", new Font(wingdings2Font, 16.0F, 0));
				Chunk blankSign = new Chunk("*", new Font(wingdings2Font, 20.0F, 0));

				Paragraph p = new Paragraph();
				  //p.setFont(new Font(songFont, 14.0F, 0));
				  p.add("附 件 2：");
				  document.add(p);
				
				  Paragraph p1 = new Paragraph();
				p1.add(new Chunk("备案表编号："));
				Table numTable = new Table(12, 1);
				numTable.setLeft(0);
				 numTable.setWidth(50.0F);
				 numTable.addCell("");
				p1.add(numTable);
				document.add(p1);
                /*String docName="";
				if("1".equals(rank.getRankOrganType())){
					docName="各级广播中心播出相关信息系统安全";
				}
				if("2".equals(rank.getRankOrganType())){
					docName="各级电视中心播出相关信息系统安全";
				}
				if("3".equals(rank.getRankOrganType())){
					docName="付费频道播出相关信息系统安全";
				}
				if("4".equals(rank.getRankOrganType())){
					docName="集成播控平台相关信息系统安全";
				}
				if("5".equals(rank.getRankOrganType())){
					docName="有线电视网络相关信息系统安全";
				}
				if("6".equals(rank.getRankOrganType())){
					docName="无线覆盖等其它类相关信息系统安全";
				}*/				
				p = new Paragraph(rank.getSysInfoName()+"保护备案表");
				p.setFont(new Font(songFont, 36.0F, 1));
				p.setAlignment(1);
				document.add(p);

				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("备 案 单 位：", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("__________", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("(盖章)", new Font(fangsongFont, 16.0F, 4)));
				p.add(new Chunk("__________", new Font(fangsongFont, 16.0F, 0)));
				document.add(p);

				document.add(Chunk.NEWLINE);

				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("备 案 日 期：", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("__________________________", new Font(fangsongFont, 16.0F, 0)));
				document.add(p);

				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("主 管 部 门：", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("__________", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("(盖章)", new Font(fangsongFont, 16.0F, 4)));
				p.add(new Chunk("__________", new Font(fangsongFont, 16.0F, 0)));
				document.add(p);

				document.add(Chunk.NEWLINE);

				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("备 案 日 期：", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("__________________________", new Font(fangsongFont, 16.0F, 0)));
				document.add(p);

				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("受理备案单位：", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("__________", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("(盖章)", new Font(fangsongFont, 16.0F, 4)));
				p.add(new Chunk("__________", new Font(fangsongFont, 16.0F, 0)));
				document.add(p);

				document.add(Chunk.NEWLINE);

				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("受 理 日 期：", new Font(fangsongFont, 16.0F, 0)));
				p.add(new Chunk("__________________________", new Font(fangsongFont, 16.0F, 0)));
				document.add(p);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("中华人民共和国公安部监制", new Font(songFont, 20.0F, 1)));
				document.add(p);
				
				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("填　表　说　明", new Font(songFont, 16.0F, 1)));
				document.add(p);

				Chapter chapter = new Chapter(1);
				com.lowagie.text.List list = new com.lowagie.text.List(true, false, 20.0F);

				//1
				ListItem listItem = new ListItem();
				Chunk itemChunk = new Chunk("制表依据。", new Font(blackBaseFont, 11.0F,0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("根据《信息安全等级保护管理办法》（公通字[2007]43号）之规定，制作本表；");
				listItem.add(itemChunk);
				list.add(listItem);

				//2
				listItem = new ListItem();
				itemChunk = new Chunk("填表范围。", new Font(blackBaseFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("本表由第二级以上信息系统运营使用单位或主管部门（以下简称“备案单位”）填写；本表由四张表单构成，表一为单位信息，每个填表单位填写一张；表二为信息系统基本信息，表三为信息系统定级信息，表二、表三每个信息系统填写一张；表四为第三级以上信息系统需要同时提交的内容，由每个第三级以上信息系统填写一张，并在完成系统建设、整改、测评等工作，投入运行后三十日内向受理备案公安机关提交；表二、表三、表四可以复印使用；");
				listItem.add(itemChunk);
				list.add(listItem);

				//3
				listItem = new ListItem();
				itemChunk = new Chunk("保存方式。", new Font(blackBaseFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("本表一式二份，一份由备案单位保存，一份由受理备案公安机关存档；");
				listItem.add(itemChunk);
				list.add(listItem);

				//4
				listItem = new ListItem("本表中有选择的地方请在选项左侧“", new Font(songFont,11.0F, 0));
				listItem.add(blankSign);
				listItem.add(new Chunk("”划“", songfont_11));
				listItem.add(rightSign);
				listItem.add(new Chunk("”，如选择“其他”，请在其后的横线中注明详细内容；", songfont_11));
				list.add(listItem);

				//5
				listItem = new ListItem();
				itemChunk = new Chunk("封面中备案表编号",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("（由受理备案的公安机关填写并校验）：分两部分共11位，第一部分6位，为受理备案公安机关代码前六位（可参照行标GA380-2002）。第二部分5位，为受理备案的公安机关给出的备案单位的顺序编号；");
				listItem.add(itemChunk);
				list.add(listItem);

				//6
				listItem = new ListItem();
				itemChunk = new Chunk("封面中备案单位：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指负责运营使用信息系统的法人单位全称；");
				listItem.add(itemChunk);
				list.add(listItem);

				//7
				listItem = new ListItem();
				itemChunk = new Chunk("封面中主管部门：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指所属辖区广播影视行政主管部门的名称；");
				listItem.add(itemChunk);
				list.add(listItem);
				//8
				listItem = new ListItem();
				itemChunk = new Chunk("封面中受理备案单位：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指受理备案的公安机关公共信息网络安全监察部门名称。此项由受理备案的公安机关负责填写并盖章；");
				listItem.add(itemChunk);
				list.add(listItem);

				//9
				listItem = new ListItem();
				itemChunk = new Chunk("表一04行政区划代码：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指备案单位所在的地(区、市、州、盟)行政区划代码；");
				listItem.add(itemChunk);
				list.add(listItem);

				//10
				listItem = new ListItem();
				itemChunk = new Chunk("表一05单位负责人：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指主管本单位信息安全工作的领导；");
				listItem.add(itemChunk);
				list.add(listItem);

				//11
				listItem = new ListItem();
				itemChunk = new Chunk("表一06责任部门：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指单位内负责信息系统安全工作的部门；");
				listItem.add(itemChunk);
				list.add(listItem);

				//12
				listItem = new ListItem();
				itemChunk = new Chunk("表一08隶属关系：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指信息系统运营使用单位与上级行政机构的从属关系，须按照单位隶属关系代码（GB/T12404―1997）填写；");
				listItem.add(itemChunk);
				list.add(listItem);

				//13
				listItem = new ListItem();
				itemChunk = new Chunk("表二02系统编号：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是由运营使用单位给出的本单位备案信息系统的编号；");
				listItem.add(itemChunk);
				list.add(listItem);

				//14
				listItem = new ListItem();
				itemChunk = new Chunk("表二05系统网络平台：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("是指系统所处的网络环境和网络构架情况；");
				listItem.add(itemChunk);
				list.add(listItem);

				//15
				listItem = new ListItem();
				itemChunk = new Chunk("表二07关键产品使用情况：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("国产品是指系统中该类产品的研制、生产单位是由中国公民、法人投资或者国家投资或者控股，在中华人民共和国境内具有独立的法人资格，产品的核心技术、关键部件具有我国自主知识产权；");
				listItem.add(itemChunk);
				list.add(listItem);

				//16
				listItem = new ListItem();
				itemChunk = new Chunk("表二08系统采用服务情况：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("国内服务商是指服务机构在中华人民共和国境内注册成立（港澳台地区除外），由中国公民、法人或国家投资的企事业单位；");
				listItem.add(itemChunk);
				list.add(listItem);

				//17
				listItem = new ListItem();
				itemChunk = new Chunk("解释：",new Font(songFont, 11.0F, 0));
				listItem.add(itemChunk);
				itemChunk = new Chunk("本表由公安部公共信息网络安全监察局监制并负责解释，未经允许，任何单位和个人不得对本表进行改动。");
				listItem.add(itemChunk);
				list.add(listItem);
				document.add(list);

				document.add(Chunk.NEXTPAGE);

				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("表一  单位基本情况", new Font(songFont, 16.0F, 0)));
				document.add(p);

				Table table1 = new Table(16);
				table1.setWidth(110.0F);
				table1.setWidths(new int[] { 20, 5, 5, 5, 5, 5, 5, 10, 25, 5, 5, 5,5, 5, 5, 10 });
				Cell cell=new Cell(new Phrase(new Chunk("01单位名称", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				cell = new Cell(unitInfo.getUnitName()==null ? " ":unitInfo.getUnitName());
				setLocal(cell);
				cell.setColspan(15);
				table1.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("02单位地址", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				cell = new Cell();
				Font underLineFont = new Font(songFont, 11.0F, 4);
				cell.add(new Chunk(unitInfo.getProvince()==null ? "":unitInfo.getProvince(), underLineFont));
				cell.add(new Chunk("省(自治区、直辖市) ", songfont_11));
				cell.add(new Chunk(unitInfo.getCity()==null ? "":unitInfo.getCity(), underLineFont));
				cell.add(new Chunk(" 地(区、市、州、盟) ", songfont_11));
				cell.add(new Chunk(unitInfo.getCounty()==null ? "":unitInfo.getCounty(), underLineFont));
				cell.add(new Chunk("县(区、市、旗)", songfont_11));
				cell.setColspan(15);
				table1.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("03邮政编码", songfont_11)));
				setLocal(cell);
				table1.addCell(cell);
				
				table1.addCell(new Phrase(new Chunk(unitInfo.getPostcode()==null || "".equals(unitInfo.getPostcode())? "":unitInfo.getPostcode().charAt(0)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getPostcode()==null || "".equals(unitInfo.getPostcode())? "":unitInfo.getPostcode().charAt(1)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getPostcode()==null || "".equals(unitInfo.getPostcode())? "":unitInfo.getPostcode().charAt(2)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getPostcode()==null || "".equals(unitInfo.getPostcode())? "":unitInfo.getPostcode().charAt(3)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getPostcode()==null || "".equals(unitInfo.getPostcode())? "":unitInfo.getPostcode().charAt(4)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getPostcode()==null || "".equals(unitInfo.getPostcode())? "":unitInfo.getPostcode().charAt(5)+"",songfont_11)));
				table1.addCell("");
				table1.addCell(new Phrase(new Chunk("04行政区划代码", songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getDivisionCode()==null || "".equals(unitInfo.getDivisionCode())? "":unitInfo.getDivisionCode().charAt(0)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getDivisionCode()==null || "".equals(unitInfo.getDivisionCode())? "":unitInfo.getDivisionCode().charAt(1)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getDivisionCode()==null || "".equals(unitInfo.getDivisionCode())? "":unitInfo.getDivisionCode().charAt(2)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getDivisionCode()==null || "".equals(unitInfo.getDivisionCode())? "":unitInfo.getDivisionCode().charAt(3)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getDivisionCode()==null || "".equals(unitInfo.getDivisionCode())? "":unitInfo.getDivisionCode().charAt(4)+"",songfont_11)));
				table1.addCell(new Phrase(new Chunk(unitInfo.getDivisionCode()==null || "".equals(unitInfo.getDivisionCode())? "":unitInfo.getDivisionCode().charAt(5)+"",songfont_11)));
				table1.addCell("");

				cell = new Cell(new Chunk("05单位负责人", new Font(songFont, 11.0F, 0)));
				setLocal(cell);
				cell.setRowspan(2);
				table1.addCell(cell);

				cell = new Cell(new Chunk("姓   名", new Font(songFont, 11.0F, 0)));
				cell.setColspan(3);
				table1.addCell(cell);
				cell = new Cell(new Chunk(unitInfo.getUnitLeader()==null ? "":unitInfo.getUnitLeader(), new Font(songFont, 11.0F, 0)));
				cell.setColspan(4);
				table1.addCell(cell);

				table1.addCell(new Phrase(new Chunk("职务/职称", new Font(songFont,11.0F, 0))));
				cell = new Cell(new Phrase(new Chunk(unitInfo.getDuty()==null ? "":unitInfo.getDuty(),new Font(songFont, 11.0F, 0))));
				cell.setColspan(7);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("办公电话", new Font(songFont,11.0F, 0))));
				cell.setColspan(3);
				table1.addCell(cell);
				cell = new Cell(new Chunk(unitInfo.getUnitTel()==null ? "":unitInfo.getUnitTel(), new Font(songFont, 11.0F, 0)));
				cell.setColspan(4);
				table1.addCell(cell);

				table1.addCell(new Phrase(new Chunk("电子邮件", new Font(songFont,	11.0F, 0))));
				cell = new Cell(new Phrase(new Chunk(unitInfo.getUnitEmail()==null ? "":unitInfo.getUnitEmail(),new Font(songFont, 11.0F, 0))));
				cell.setColspan(7);
				table1.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("06责任部门", new Font(songFont,11.0F, 0))));
				setLocal(cell);
				table1.addCell(cell);
				cell = new Cell(new Chunk(unitInfo.getUnitDep()==null ? "":unitInfo.getUnitDep(), new Font(songFont,11.0F, 0)));
				cell.setColspan(15);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("07责任部门联系人", new Font(songFont, 11.0F, 0))));
				setLocal(cell);
				cell.setRowspan(3);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("姓   名", new Font(songFont,11.0F, 0))));
				cell.setColspan(3);
				table1.addCell(cell);
				cell = new Cell(new Chunk(unitInfo.getDepContact()==null ? "":unitInfo.getDepContact(), new Font(songFont, 11.0F, 0)));
				cell.setColspan(4);
				table1.addCell(cell);

				table1.addCell(new Phrase(new Chunk("职务/职称", new Font(songFont,11.0F, 0))));
				cell = new Cell(new Chunk(unitInfo.getDepDuty()==null ? "":unitInfo.getDepDuty(), new Font(songFont,11.0F, 0)));
				cell.setColspan(7);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("办公电话", new Font(songFont,11.0F, 0))));
				cell.setColspan(3);
				table1.addCell(cell);
				cell = new Cell(new Chunk(unitInfo.getDepTel()==null ? "":unitInfo.getDepTel(), new Font(songFont,11.0F, 0)));
				cell.setColspan(4);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("电子邮件", new Font(songFont,11.0F, 0))));
				cell.setRowspan(2);
				table1.addCell(cell);
				cell = new Cell(new Chunk(unitInfo.getDepEmail()==null ? "":unitInfo.getDepEmail(), new Font(songFont, 11.0F, 0)));
				cell.setColspan(7);
				cell.setRowspan(2);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("移动电话", new Font(songFont,11.0F, 0))));
				cell.setColspan(3);
				table1.addCell(cell);
				cell = new Cell(new Chunk(unitInfo.getDepMobile()==null ? "":unitInfo.getDepMobile(), new Font(songFont, 11.0F, 0)));
				cell.setColspan(4);
				table1.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("08隶属关系", new Font(songFont,11.0F, 0))));
				setLocal(cell);
				table1.addCell(cell);
				cell = new Cell("中央".equals(unitInfo.getSubordinate()) ? rightSign : blankSign);
				cell.add(new Chunk("1中央\t", new Font(songFont, 11.0F, 0)));
				cell.add("省".equals(unitInfo.getSubordinate()) ? rightSign : blankSign);
				cell.add(new Chunk("2省(自治区、直辖市)\t", new Font(songFont, 11.0F, 0)));
				cell.add("地".equals(unitInfo.getSubordinate()) ? rightSign : blankSign);
				cell.add(new Chunk("3地(区、市、州、盟)\t", new Font(songFont, 11.0F, 0)));
				cell.add(Chunk.NEWLINE);
				cell.add("县".equals(unitInfo.getSubordinate()) ? rightSign : blankSign);
				cell.add(new Chunk("4县(区、市、旗)\t", new Font(songFont, 11.0F, 0)));
				cell.add("其他".equals(unitInfo.getSubordinate()) ? rightSign : blankSign);
				cell.add(new Chunk("9其他", new Font(songFont, 11.0F, 0)));
				cell.add(new Chunk(unitInfo.getOtherSub()!=null? unitInfo.getOtherSub() : "________", new Font(songFont, 11.0F, 4)));
				cell.setColspan(15);
				table1.addCell(cell);
				
                cell=new Cell(new Phrase(new Chunk("09单位类型", new Font(songFont,11.0F, 0))));
                setLocal(cell);
				table1.addCell(cell);
				cell = new Cell("党委机关".equals(unitInfo.getUnitType()) ? rightSign	: blankSign);
				cell.add(new Chunk("1党委机关\t\t", songfont_11));
				cell.add("政府机关".equals(unitInfo.getUnitType()) ? rightSign : blankSign);
				cell.add(new Chunk("2政府机关\t\t", songfont_11));
				cell.add("事业单位".equals(unitInfo.getUnitType()) ? rightSign : blankSign);
				cell.add(new Chunk("3事业单位\t\t", songfont_11));
				cell.add("企业".equals(unitInfo.getUnitType()) ? rightSign : blankSign);
				cell.add(new Chunk("4企业", songfont_11));
				cell.add(Chunk.NEWLINE);
				cell.add("其他".equals(unitInfo.getUnitType()) ? rightSign : blankSign);
				cell.add(new Chunk("9其他", songfont_11));
				cell.add(new Chunk(unitInfo.getOtherUnitType()!=null ?unitInfo.getOtherUnitType() : "________", new Font(songFont, 11.0F, 4)));
				cell.setColspan(15);
				table1.addCell(cell);
				
				cell=new Cell(new Phrase(new Chunk("10行业类别", songfont_11)));
	            setLocal(cell);
				table1.addCell(cell);
				cell = new Cell("电信".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("11电信\t\t", songfont_11));
				
				cell.add("广电".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("12广电\t", songfont_11));
				
				cell.add("经营性公众互联网".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("13经营性公众互联网", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("铁路".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("21铁路\t\t", songfont_11));
				
				cell.add("银行".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("22银行\t", songfont_11));
				
				cell.add("海关".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("23海关\t\t\t", songfont_11));
				
				cell.add("税务".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("24税务", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("民航".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("25民航\t\t", songfont_11));
				
				cell.add("电力".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("26电力\t", songfont_11));
				
				cell.add("证券".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("27证券\t\t\t", songfont_11));
				
				cell.add("保险".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("28保险", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("国防科技工业".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("31国防科技工业\t", songfont_11));
				
				cell.add("公安".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("32公安\t", songfont_11));
				
				cell.add("人事劳动和社会保障".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("33人事劳动和社会保障\t", songfont_11));
				
				cell.add("财政".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("34财政", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("审计".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("35审计\t\t", songfont_11));
				
				cell.add("商业贸易".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("36商业贸易\t", songfont_11));
				
				cell.add("国土资源".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("37国土资源\t\t\t", songfont_11));
				
				cell.add("能源".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("38能源", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("交通".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("39交通\t\t", songfont_11));
				
				cell.add("统计".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("40统计\t", songfont_11));
				
				cell.add("工商行政管理".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("41工商行政管理\t\t", songfont_11));
				
				cell.add("邮政".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("42邮政", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("教育".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("43教育\t\t", songfont_11));
				
				cell.add("文化".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("44文化\t", songfont_11));
				
				cell.add("卫生".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("45卫生\t\t\t", songfont_11));
				
				cell.add("农业".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("46农业", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("水利".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("47水利\t\t", songfont_11));
				
				cell.add("外交".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("48外交\t", songfont_11));
				
				cell.add("发展改革".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("49发展改革\t\t\t", songfont_11));
				
				cell.add("科技".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("50科技", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("宣传".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("51宣传\t\t", songfont_11));
				
				cell.add("质量监督检验检疫".equals(unitInfo.getEmployment()) ? rightSign : blankSign);
				cell.add(new Chunk("52质量监督检验检疫\t", songfont_11));
				cell.add(Chunk.NEWLINE);
				
				cell.add("其他".equals(unitInfo.getOtherEmp()) ? rightSign : blankSign);
				cell.add(new Chunk("99其他", songfont_11));
				cell.add(new Chunk("其他".equals(unitInfo.getOtherEmp()) ? unitInfo.getOtherEmp() : "________"));
				cell.setColspan(15);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("11信息系统总数", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				table1.addCell(cell);
				cell = new Cell(new Phrase(new Chunk(countTol+"",songfont_11)));
				setLocal(cell);
				cell.setColspan(2);
				cell.setRowspan(2);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("12第二级信息系统数", songfont_11)));
				cell.setColspan(5);
				table1.addCell(cell);
				cell = new Cell(new Phrase(new Chunk(countSec+"",songfont_11)));
				setLocal(cell);
				cell.setColspan(1);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("13第三级信息系统数", songfont_11)));
				cell.setColspan(6);
				table1.addCell(cell);
				cell = new Cell(new Phrase(new Chunk(countThr+"",songfont_11)));
				cell.setColspan(1);
				setLocal(cell);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("14第四级信息系统数", songfont_11)));
				cell.setColspan(5);
				table1.addCell(cell);
				cell = new Cell(new Phrase(new Chunk(countThir+"",songfont_11)));
				cell.setColspan(1);
				setLocal(cell);
				table1.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("15第五级信息系统数", songfont_11)));
				cell.setColspan(6);
				table1.addCell(cell);
				cell = new Cell(new Phrase(new Chunk("0",songfont_11)));
				cell.setColspan(1);
				setLocal(cell);
				table1.addCell(cell);

				document.add(table1);

				document.add(Chunk.NEXTPAGE);

				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("表二 (" + rank.getSysInfoName()+")信息系统情况",	new Font(songFont, 16.0F, 0)));
				document.add(p);

				Table table2 = new Table(14);
				table2.setWidth(120.0F);
				table2.setWidths(new int[] { 8, 13, 5, 12, 3, 8, 5, 5, 5, 13, 5, 5,	5, 5 });
				
				cell = new Cell(new Phrase(new Chunk("01系统名称", songfont_11)));
				setLocal(cell);
				cell.setColspan(2);
				table2.addCell(cell);
				cell = new Cell(new Chunk(rank.getSysInfoName()==null ? "" :rank.getSysInfoName(), songfont_11));
				cell.setColspan(7);
				table2.addCell(cell);

				table2.addCell(new Phrase(new Chunk("02系统编号", songfont_11)));
				cell = new Cell(new Chunk(rank.getSysInfoId()==null ? "" :rank.getSysInfoId(), songfont_11));
				cell.setColspan(4);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("03系统承载业务情况", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				table2.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("业务类型", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell = new Cell("1".equals(systemManager.getBusType()) ? rightSign	: blankSign);
				cell.add(new Chunk("1生产作业\t", songfont_11));
				cell.add("2".equals(systemManager.getBusType()) ? rightSign : blankSign);
				cell.add(new Chunk("2指挥调度\t", songfont_11));
				cell.add("3".equals(systemManager.getBusType()) ? rightSign : blankSign);
				cell.add(new Chunk("3管理控制\t", songfont_11));
				cell.add("4".equals(systemManager.getBusType()) ? rightSign : blankSign);
				cell.add(new Chunk("4内部办公\t\n", songfont_11));
				cell.add("5".equals(systemManager.getBusType()) ? rightSign : blankSign);
				cell.add(new Chunk("5公众服务\t", songfont_11));
				cell.add("9".equals(systemManager.getBusType()) ? rightSign : blankSign);
				cell.add(new Chunk("9其他", songfont_11));
				cell.add(new Chunk(systemManager.getOtherBusType()!=null ? systemManager.getOtherBusType() : "________", songfontUnderLine_11));
				cell.setColspan(12);
				table2.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("业务描述", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell = new Cell(new Chunk(systemManager.getBusDescription(), songfont_11));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("04系统服务情况", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				table2.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("服务范围", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell = new Cell("10".equals(systemManager.getSerArea()) ? rightSign	: blankSign);
				cell.add(new Chunk("10全国\t", songfont_11));
				
				cell.add(new Chunk("11".equals(systemManager.getSerArea()) ? rightSign	: blankSign));
				cell.add(new Chunk("11跨省（区、市）跨", songfont_11));
				cell.add(new Chunk(systemManager.getProTotal()!=null ? systemManager.getProTotal()+"": "________", songfontUnderLine_11));
				cell.add(new Chunk("个\t", songfont_11));
				
				cell.add("20".equals(systemManager.getSerArea()) ? rightSign : blankSign);
				cell.add(new Chunk("20全省（区、市）\t\n", songfont_11));
				
				cell.add("21".equals(systemManager.getSerArea()) ? rightSign : blankSign);
				cell.add(new Chunk("21跨地（市、区） 跨", songfont_11));
				cell.add(new Chunk(systemManager.getCityTotal()!=null ? systemManager.getCityTotal()+"" : "________", songfontUnderLine_11));
				cell.add(new Chunk("个\t", songfont_11));
				
				cell.add("30".equals(systemManager.getSerArea()) ? rightSign : blankSign);
				cell.add(new Chunk("30地（市、区）内\t", songfont_11));
				
				cell.add(new Chunk("99".equals(systemManager.getSerArea()) ? rightSign	: blankSign));
				cell.add(new Chunk("99其它", songfont_11));
				cell.add(new Chunk(systemManager.getOtherArea()!=null ? systemManager.getOtherArea() : "________", songfontUnderLine_11));
				cell.setColspan(12);
				table2.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("服务对象", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell = new Cell("1".equals(systemManager.getSerObj()) ? rightSign	: blankSign);
				cell.add(new Chunk("1单位内部人员   ", songfont_11));
				cell.add("2".equals(systemManager.getSerObj()) ? rightSign : blankSign);
				cell.add(new Chunk("2社会公众人员   ", songfont_11));
				cell.add("3".equals(systemManager.getSerObj()) ? rightSign : blankSign);
				cell.add(new Chunk("3两者均包括   ", songfont_11));
				cell.add("9".equals(systemManager.getSerObj()) ? rightSign : blankSign);
				cell.add(new Chunk("9其他", songfont_11));
				cell.add(new Chunk(systemManager.getOtherObj()!=null ? systemManager.getOtherObj() : "________", songfontUnderLine_11));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("05系统网络平台", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				table2.addCell(cell);

				cell=new Cell(new Phrase(new Chunk("覆盖范围", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell = new Cell("1".equals(rank.getRankCoveArea()) ? rightSign : blankSign);
				cell.add(new Chunk("1局域网\t", songfont_11));
				cell.add("2".equals(rank.getRankCoveArea()) ? rightSign : blankSign);
				cell.add(new Chunk("2城域网\t", songfont_11));
				cell.add("3".equals(rank.getRankCoveArea()) ? rightSign : blankSign);
				cell.add(new Chunk("3广域网\t", songfont_11));
				cell.add("4".equals(rank.getRankCoveArea()) ? rightSign : blankSign);
				cell.add(new Chunk("9其他", songfont_11));
				cell.add(new Chunk(rank.getRankOthArea()!=null ? rank.getRankOthArea() : "________", songfontUnderLine_11));
				cell.setColspan(12);
				table2.addCell(cell);
				
				cell=new Cell(new Phrase(new Chunk("网络性质", songfont_11)));
				setLocal(cell);
				table2.addCell(cell);
				cell = new Cell("1".equals(rank.getRankNetworkProp()) ? rightSign : blankSign);
				cell.add(new Chunk("1业务专网\t", songfont_11));
				cell.add("2".equals(rank.getRankNetworkProp()) ? rightSign : blankSign);
				cell.add(new Chunk("2互联网\t", songfont_11));
				cell.add("3".equals(rank.getRankNetworkProp()) ? rightSign : blankSign);
				cell.add(new Chunk("9其它", songfont_11));
				cell.add(new Chunk( rank.getRankOthNetworkProp()!=null? rank.getRankOthNetworkProp() : "________", songfontUnderLine_11));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("06系统互联情况", songfont_11)));
				setLocal(cell);
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell("1".equals(rank.getRankSysConn()) ? rightSign	: blankSign);
				cell.add(new Chunk("1与其他行业系统连接\t", songfont_11));
				cell.add("2".equals(rank.getRankSysConn()) ? rightSign	: blankSign);
				cell.add(new Chunk("2与本行业其他单位系统连接\t\n", songfont_11));
				cell.add("3".equals(rank.getRankSysConn()) ? rightSign	: blankSign);
				cell.add(new Chunk("3与本单位其他系统连接\t", songfont_11));
				cell.add("4".equals(rank.getRankSysConn()) ? rightSign	: blankSign);
				cell.add(new Chunk("9其它", songfont_11));
				cell.add(new Chunk(rank.getRankOtherSysConn()!=null ? rank.getRankOtherSysConn() : "________", songfontUnderLine_11));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("07关键产品使用情况", songfont_11)));
				cell.setColspan(2);
				cell.setRowspan(8);
				setLocal(cell);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("序号", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("产品类型", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("数量", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("使用国产品率", songfont_11)));
				setLocal(cell);
				cell.setColspan(8);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("全部使用 ", songfont_11)));
				setLocal(cell);
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("全部未使用 ", songfont_11)));
				setLocal(cell);
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("部分使用及使用率  ", songfont_11)));
				setLocal(cell);
				cell.setColspan(4);
				table2.addCell(cell);

				
                 for(int i=1;i<=6;i++){
                	 
                	 
     				table2.addCell(new Phrase(new Chunk(i+"",songfont_11)));
                    switch(i){
                    
                    case 1:cell = new Cell(new Chunk("安全专用产品", songfont_11));
							   cell.setColspan(2);
							   table2.addCell(cell);
							   table2.addCell(new Phrase(new Chunk(rank.getRankSecCount(), songfont_11)));
							   cell = new Cell(("100".equals(rank.getRankSecUse())) ? rightSign	: blankSign);
							   setLocal(cell);
							   cell.setColspan(2);
							   table2.addCell(cell);
							   cell = new Cell(("0".equals(rank.getRankSecUse())) ? rightSign : blankSign);
							   setLocal(cell);
							   cell.setColspan(2);
							   table2.addCell(cell);
							   cell = new Cell(("3".equals(rank.getRankSecUse())) ? rightSign	: blankSign);
							   setLocal(cell);
							   cell.add(new Chunk((rank.getPartRankSecUse()!=null ? rank.getPartRankSecUse() : "________") + "%", songfontUnderLine_11));
							   cell.setColspan(4);
							   table2.addCell(cell);
                   	break;
                   	
                    case 2:cell = new Cell(new Chunk("网络产品", songfont_11));
						   cell.setColspan(2);
						   table2.addCell(cell);
						   table2.addCell(new Phrase(new Chunk(rank.getRankNetCount(), songfont_11)));
						   cell = new Cell(("100".equals(rank.getRankNetUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("0".equals(rank.getRankNetUse())) ? rightSign : blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("3".equals(rank.getRankNetUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.add(new Chunk((rank.getPartRankNetUse()!=null ? rank.getPartRankNetUse() : "________") + "%", songfontUnderLine_11));
						   cell.setColspan(4);
						   table2.addCell(cell);                
               	 	break;
                    case 3:cell = new Cell(new Chunk("操作系统", songfont_11));
						   cell.setColspan(2);
						   table2.addCell(cell);
						   table2.addCell(new Phrase(new Chunk(rank.getRankSysCount(), songfont_11)));
						   cell = new Cell(("100".equals(rank.getRankSysUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("0".equals(rank.getRankSysUse())) ? rightSign : blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("3".equals(rank.getRankSysUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.add(new Chunk((rank.getPartRankSysUse()!=null ? rank.getPartRankSysUse() : "________") + "%", songfontUnderLine_11));
						   cell.setColspan(4);
						   table2.addCell(cell);                
               	 	break;
                    case 4:cell = new Cell(new Chunk("数据库", songfont_11));
						   cell.setColspan(2);
						   table2.addCell(cell);
						   table2.addCell(new Phrase(new Chunk(rank.getRankSqlCount(), songfont_11)));
						   cell = new Cell(("100".equals(rank.getRankSqlUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("0".equals(rank.getRankSqlUse())) ? rightSign : blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("3".equals(rank.getRankSqlUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.add(new Chunk((rank.getPartRankSqlUse()!=null ? rank.getPartRankSqlUse() : "________") + "%", songfontUnderLine_11));
						   cell.setColspan(4);
						   table2.addCell(cell);                
               	 	break;
                    case 5:cell = new Cell(new Chunk("服务器", songfont_11));
						   cell.setColspan(2);
						   table2.addCell(cell);
						   table2.addCell(new Phrase(new Chunk(rank.getRankSerCount(), songfont_11)));
						   cell = new Cell(("100".equals(rank.getRankSerUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("0".equals(rank.getRankSerUse())) ? rightSign : blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("3".equals(rank.getRankSerUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.add(new Chunk((rank.getPartRankSerUse()!=null ? rank.getPartRankSerUse() : "________") + "%", songfontUnderLine_11));
						   cell.setColspan(4);
						   table2.addCell(cell);                
               	 	break;
                    case 6:cell = new Cell(new Chunk("其他", songfont_11));
                           cell.add(new Chunk((rank.getRankOthProd()!=null ? rank.getRankOthProd() : "________"), songfontUnderLine_11));
                    	   cell.setColspan(2);
						   table2.addCell(cell);
						   table2.addCell(new Phrase(new Chunk(rank.getRankOthProdCount(), songfont_11)));
						   cell = new Cell(("100".equals(rank.getRankOthProdUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("0".equals(rank.getRankOthProdUse())) ? rightSign : blankSign);
						   setLocal(cell);
						   cell.setColspan(2);
						   table2.addCell(cell);
						   cell = new Cell(("3".equals(rank.getRankOthProdUse())) ? rightSign	: blankSign);
						   setLocal(cell);
						   cell.add(new Chunk((rank.getPartRankOthProdUse()!=null ? rank.getPartRankOthProdUse() : "________") + "%", songfontUnderLine_11));
						   cell.setColspan(4);
						   table2.addCell(cell);                
               	 	break;
                    
                    }

                 }

				cell = new Cell(new Phrase(new Chunk("08系统采用服务情况", songfont_11)));
				cell.setColspan(2);
				cell.setRowspan(10);
				setLocal(cell);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("序号", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("服务类型", songfont_11)));
				setLocal(cell);
				cell.setRowspan(2);
				cell.setColspan(3);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("服务责任方类型", songfont_11)));
				setLocal(cell);
				cell.setColspan(8);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("本行业（单位）", songfont_11)));
				setLocal(cell);
				cell.setColspan(3);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("国内其他服务商 ", songfont_11)));
				setLocal(cell);
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("国外服务商", songfont_11)));
				setLocal(cell);
				cell.setColspan(3);
				table2.addCell(cell);
			
					table2.addCell(new Phrase(new Chunk("1",songfont_11)));
					table2.addCell(new Phrase(new Chunk("等级测评", songfont_11)));
					cell = new Cell("1".equals(rank.getRankIfGradeEval()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfGradeEval())) ? 	rightSign: blankSign);
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankSerGradeType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankSerGradeType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankSerGradeType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					
					
					table2.addCell(new Phrase(new Chunk("2",songfont_11)));
					table2.addCell(new Phrase(new Chunk("风险评估", songfont_11)));
					cell = new Cell("1".equals(rank.getRankIfRiskEval()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfRiskEval())) ? 	rightSign:blankSign );
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankSerRiskType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankSerRiskType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankSerRiskType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					
					table2.addCell(new Phrase(new Chunk("3",songfont_11)));
					table2.addCell(new Phrase(new Chunk("灾难恢复", songfont_11)));
					cell = new Cell("1".equals(rank.getRankIfSuffReco()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfSuffReco())) ? rightSign	:  blankSign);
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankIfSuffRecoType()) ? rightSign : blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankIfSuffRecoType()) ? rightSign : blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankIfSuffRecoType()) ? rightSign : blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					
					table2.addCell(new Phrase(new Chunk("4",songfont_11)));
					table2.addCell(new Phrase(new Chunk("应急响应", songfont_11)));
					cell = new Cell("1".equals(rank.getRankIfResponse()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfResponse())) ? 	rightSign:blankSign );
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankResponseType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankResponseType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankResponseType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					
					table2.addCell(new Phrase(new Chunk("5",songfont_11)));
					table2.addCell(new Phrase(new Chunk("系统集成", songfont_11)));
					cell = new Cell("1".equals(rank.getRankIfSysInte()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfSysInte())) ? rightSign :blankSign );
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankSysInteType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankSysInteType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankSysInteType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					
					table2.addCell(new Phrase(new Chunk("6",songfont_11)));
					table2.addCell(new Phrase(new Chunk("安全咨询", songfont_11)));
					cell = new Cell("1".equals(rank.getRankIfSecCon()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfSecCon())) ? rightSign:blankSign );
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankSecConypeType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankSecConypeType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankSecConypeType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					
					table2.addCell(new Phrase(new Chunk("7",songfont_11)));
					table2.addCell(new Phrase(new Chunk("安全培训", songfont_11)));
					cell = new Cell("1".equals(rank.getRankIfSecTrain()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfSecTrain())) ? rightSign	:blankSign );
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankSecTrainType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankSecTrainType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankSecTrainType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					
					
					table2.addCell(new Phrase(new Chunk("8",songfont_11)));
					cell = new Cell(new Chunk("其他", songfont_11));
                    cell.add(new Chunk((rank.getRankOthSerName()!=null?rank.getRankOthSerName():"________"), songfontUnderLine_11));
                    table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankIfOthSer()) ? rightSign : blankSign);
					setLocal(cell);
					cell.add(new Chunk("有", new Font(songFont, 9.0F, 0)));
					cell.add(("0".equals(rank.getRankIfOthSer())) ? rightSign :blankSign );
					setLocal(cell);
					cell.add(new Chunk("无", new Font(songFont, 9.0F, 0)));
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("1".equals(rank.getRankOthUseType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					cell = new Cell("2".equals(rank.getRankOthUseType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(2);
					table2.addCell(cell);
					cell = new Cell("3".equals(rank.getRankOthUseType()) ? rightSign	: blankSign);
					setLocal(cell);
					cell.setColspan(3);
					table2.addCell(cell);
					

				cell = new Cell(new Phrase(new Chunk("09等级测评单位名称", songfont_11)));
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk(rank.getRankEvalUnitName(),songfont_11)));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("10何时投入运行使用", songfont_11)));
				cell.setColspan(2);
				table2.addCell(cell);

				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				cell = new Cell(new Phrase(new Chunk(format.format(rank.getRankUseDate()), songfont_11)));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("11系统是否是分系统", songfont_11)));
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell("1".equals(rank.getRankFlag()) ? rightSign : blankSign);
				cell.add(new Chunk("是", songfont_11));
				cell.add("0".equals(rank.getRankFlag())? rightSign :  blankSign );
				cell.add(new Chunk("否（如选择是请填下两项）", songfont_11));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("12上级系统名称", songfont_11)));
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk(rank.getRankParentSysName(),songfont_11)));
				cell.setColspan(12);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("13上级系统所属单位名称", songfont_11)));
				cell.setColspan(2);
				table2.addCell(cell);

				cell = new Cell(new Phrase(new Chunk(rank.getRankParentUnitName(),songfont_11)));
				cell.setColspan(12);
				table2.addCell(cell);

				document.add(table2);
				document.add(Chunk.NEXTPAGE);

				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("表三(" + systemManager.getSysName() + ")信息系统定级情况", new Font(songFont,16.0F, 0)));
				document.add(p);

				Table table3 = new Table(2);
				table3.setWidth(110.0F);
				table3.setWidths(new int[] { 40,70 });
				
				cell = new Cell(new Phrase(new Chunk("01信息系统安全保护等级", songfont_11)));
				table3.addCell(cell);
				
				cell = new Cell("第一级".equals(rank.getRankGrade()) ? rightSign	: blankSign);
				cell.add(new Chunk("第一级", songfont_11));
				cell.add("第二级".equals(rank.getRankGrade()) ? rightSign	: blankSign);
				cell.add(new Chunk("第二级", songfont_11));
				cell.add("第三级".equals(rank.getRankGrade()) ? rightSign	: blankSign);
				cell.add(new Chunk("第三级", songfont_11));
				cell.add("第四级".equals(rank.getRankGrade()) ? rightSign	: blankSign);
				cell.add(new Chunk("第四级", songfont_11));
				cell.add("第五级".equals(rank.getRankGrade()) ? rightSign	: blankSign);
				cell.add(new Chunk("第五级", songfont_11));
				table3.addCell(cell);
				
				cell = new Cell(new Phrase(new Chunk("02定级时间", songfont_11)));
				table3.addCell(cell);
				cell = new Cell(new Chunk(format.format(rank.getRankTime()), songfont_11));
				table3.addCell(cell);
				
				cell = new Cell(new Phrase(new Chunk("03专家评审情况", songfont_11)));
				table3.addCell(cell);
				cell = new Cell(new Phrase(new Chunk("1".equals(rank.getRankJudge())? "已评审":"未评审", songfont_11)));
				table3.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("04是否有主管部门", songfont_11)));
				table3.addCell(cell);
				cell = new Cell("1".equals(rank.getRankIsDep()) ? rightSign	: blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankIsDep())? rightSign :blankSign );
				cell.add(new Chunk("无（如选择有请填下两项）", songfont_11));
				table3.addCell(cell);
				
				
				cell = new Cell(new Phrase(new Chunk("05主管部门名称", songfont_11)));
				table3.addCell(cell);
				cell = new Cell(new Phrase(new Chunk(rank.getRankDepName()==null?"":rank.getRankDepName(), songfont_11)));
				table3.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("06主管部门审批定级情况", songfont_11)));
				table3.addCell(cell);
				cell = new Cell("1".equals(rank.getRankDepJudge()) ? rightSign	: blankSign);
				cell.add(new Chunk("已评审", songfont_11));
				cell.add("0".equals(rank.getRankDepJudge()) ? rightSign :blankSign );
				cell.add(new Chunk("未评审", songfont_11));
				table3.addCell(cell);

				cell = new Cell(new Phrase(new Chunk("07系统定级报告", songfont_11)));
				table3.addCell(cell);
				cell = new Cell(new Phrase(new Chunk("1".equals(rank.getRankDoc()) ? rightSign	: blankSign)));
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankDoc()) ? rightSign :blankSign );
				cell.add(new Chunk("无\t", songfont_11));
				cell.add(new Chunk("附件名称"+(rank.getRankAccess()==null?"":rank.getRankAccess()), songfont_11));
				table3.addCell(cell);
				
				cell = new Cell(new Phrase(new Chunk("填表人：", songfont_11)));
				cell.add(new Chunk(rank.getRankInformant()));
				table3.addCell(cell);
				cell = new Cell(new Phrase(new Chunk("填表日期：", songfont_11)));
				cell.add(new Chunk(format.format(rank.getRankDate())));
				table3.addCell(cell);


				document.add(table3);
				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("备案审核民警：                              审核日期：       年   月   日",songfont_11));
				document.add(p);
				document.add(Chunk.NEXTPAGE);

				p = new Paragraph();
				p.setAlignment(1);
				p.add(new Chunk("表四（ "+systemManager.getSysName()+" ）第三级（含）以上信息系统提交材料情况",songfont_11));
				document.add(p);
				if (null== null){
				Table table4 = new Table(2);
				table4.setWidth(110.0F);
				table4.setWidths(new int[] { 40, 70 });

				table4.addCell(new Phrase(new Chunk("01系统拓扑结构及说明", songfont_11)));
				cell = new Cell("1".equals(rank.getRankTopStruct())? rightSign : blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankTopStruct())? rightSign :blankSign );
				cell.add(new Chunk("无\t附件名称", songfont_11));
				cell.add(new Chunk(rank.getRankTopRelAcc()!=null?rank.getRankTopRelAcc():"________", songfont_11));
				table4.addCell(cell);

				table4.addCell(new Phrase(new Chunk("02系统安全组织机构及管理制度", songfont_11)));

				cell = new Cell("1".equals(rank.getRankSysManage()) ? rightSign : blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankSysManage())? rightSign :blankSign);
				cell.add(new Chunk("无\t附件名称", songfont_11));
				cell.add(new Chunk(rank.getRankSysManRel()!=null?rank.getRankSysManRel():"________", songfont_11));
				table4.addCell(cell);

				table4.addCell(new Phrase(new Chunk("03系统安全保护设施设计实施方案或改建实施方案",songfont_11)));
				cell = new Cell("1".equals(rank.getRankSysPlan()) ? rightSign : blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankSysPlan())? rightSign :blankSign);
				cell.add(new Chunk("无\t附件名称", songfont_11));
				cell.add(new Chunk(rank.getRankSysPlanRel()!=null?rank.getRankSysPlanRel():"________", songfont_11));
				
				table4.addCell(cell);
				
				table4.addCell(new Phrase(new Chunk("04系统使用的安全产品清单及认证、销售许可证明",songfont_11)));
				cell = new Cell("1".equals(rank.getRankSysLicense()) ? rightSign : blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankSysLicense())  ? rightSign :blankSign);
				cell.add(new Chunk("无\t附件名称", songfont_11));
				cell.add(new Chunk(rank.getRankSysLiceRel()!=null?rank.getRankSysLiceRel():"________", songfont_11));
				table4.addCell(cell);

				table4.addCell(new Phrase(new Chunk("05系统等级测评报告", songfont_11)));
				cell = new Cell("1".equals(rank.getRankSysReport()) ? rightSign : blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankSysReport()) ? rightSign :blankSign);
				cell.add(new Chunk("无\t附件名称", songfont_11));
				cell.add(new Chunk(rank.getRankSysReportRel()!=null?rank.getRankSysReportRel():"________", songfont_11));
				table4.addCell(cell);

				table4.addCell(new Phrase(new Chunk("06专家评审情况", songfont_11)));
				cell = new Cell("1".equals(rank.getRankPeerRev()) ? rightSign : blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankPeerRev()) ? rightSign :blankSign);
				cell.add(new Chunk("无\t附件名称", songfont_11));
				cell.add(new Chunk(rank.getRankPeerRevRel()!=null?rank.getRankPeerRevRel():"________", songfont_11));
				table4.addCell(cell);
				
				table4.addCell(new Phrase(new Chunk("07上级主管部门审批意见", songfont_11)));
				cell = new Cell("1".equals(rank.getRankSuperOpin()) ? rightSign : blankSign);
				cell.add(new Chunk("有", songfont_11));
				cell.add("0".equals(rank.getRankSuperOpin())? rightSign :blankSign);
				cell.add(new Chunk("无\t附件名称", songfont_11));
				cell.add(new Chunk(rank.getRankSuperOpinRel()!=null?rank.getRankSuperOpinRel():"________", songfont_11));
				table4.addCell(cell);

				document.add(table4);
				document.close();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    
		return 1;
		
	}
	





}
