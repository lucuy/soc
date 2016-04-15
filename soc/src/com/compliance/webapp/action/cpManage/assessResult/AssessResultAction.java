package com.compliance.webapp.action.cpManage.assessResult;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.compliance.model.basicinfo.assets.BusinessAssets;
import com.compliance.model.basicinfo.assets.CompAssets;
import com.compliance.model.basicinfo.assets.DataAssets;
import com.compliance.model.basicinfo.assets.DevAssets;
import com.compliance.model.basicinfo.assets.DocAssets;
import com.compliance.model.basicinfo.assets.EmpAssets;
import com.compliance.model.basicinfo.assets.NetAssets;
import com.compliance.model.basicinfo.system.SystemManager;
import com.compliance.model.basicinfo.unitinfo.UnitInfo;
import com.compliance.model.cpManage.assessResult.AssessResult;
import com.compliance.model.cpManage.demand.DemandCollet;
import com.compliance.model.cpManage.technology.Technology;
import com.compliance.model.rank.Rank;
import com.compliance.service.basicinfo.assets.CompAssetsService;
import com.compliance.service.basicinfo.assets.DataAssetsService;
import com.compliance.service.basicinfo.assets.DevAssetsService;
import com.compliance.service.basicinfo.assets.DocAssetsService;
import com.compliance.service.basicinfo.assets.EmpAssetsService;
import com.compliance.service.basicinfo.assets.NetAssetsService;
import com.compliance.service.basicinfo.assets.SoftUseService;
import com.compliance.service.basicinfo.system.SystemService;
import com.compliance.service.basicinfo.unitinfo.UnitInfoService;
import com.compliance.service.cpManage.assessResult.AssessResultService;
import com.compliance.service.cpManage.demand.DemandColletService;
import com.compliance.service.cpManage.technology.TechnologyService;
import com.compliance.service.rank.RankService;
import com.compliance.webapp.action.BaseAction;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfParagraphStyle;
import com.util.StringUtil;

/**
 * Description: 评估结果 Action
 * 
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-24
 * @Modified by
 */

public class AssessResultAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private AssessResultService assessResultService;
	private DemandColletService demandColletService;
	private TechnologyService technologyService;
	private RankService rankService;
	private SystemService systemService;
	private UnitInfoService unitInfoService;
	private SoftUseService softUseService;
	private DataAssetsService dataAssetsService;
	private CompAssetsService compAssetsService;
	private DevAssetsService devAssetsService;
	private NetAssetsService netAssetsService;
	private EmpAssetsService empAssetsService;
	private DocAssetsService docAssetsService;

	private String sort;
	private String acId;
	private String sysInfoId;

	/**
	 * 
	 * Description : 添加评估结果
	 * 
	 * */
	public String insert() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();

		String acIds = request.getParameter("acId");
		String sorts = request.getParameter("sort");
		////System.out.println("当前id========================="+acIds);
		int assessOverCount = assessResultService.queryAssessOverCount(acIds);
		////System.out.println("当前进度============="+assessOverCount);
		//页面读取评估进度
		request.setAttribute("processnum", assessOverCount);
		String assessResult = request.getParameter("assessResult");
		String description = request.getParameter("description");
		String a = request.getParameter("CIA_A");
		String b = request.getParameter("CIA_B");
		String c = request.getParameter("CIA_C");
		String d = request.getParameter("CIA_D");
		String e = request.getParameter("CIA_E");
		String f = request.getParameter("CIA_F");
		String g = request.getParameter("CIA_G");
		String h = request.getParameter("CIA_H");
		String i = request.getParameter("CIA_I");
		String j = request.getParameter("CIA_J");
		AssessResult ac = new AssessResult();
		if (acIds != null)
			ac.setFK_CA(Integer.parseInt(acIds));
		if (sorts != null)
			ac.setCIA_Sort(sorts);
		if (assessResult != null)
			ac.setCIA_AssessResult(assessResult);
		if (description != null)
			ac.setCIA_MainProbDes(description);
		if (a != null)
			ac.setCIA_A(a);
		if (b != null)
			ac.setCIA_B(b);
		if (c != null)
			ac.setCIA_C(c);
		if (d != null)
			ac.setCIA_D(d);
		if (e != null)
			ac.setCIA_E(e);
		if (f != null)
			ac.setCIA_F(f);
		if (g != null)
			ac.setCIA_G(g);
		if (h != null)
			ac.setCIA_H(h);
		if (i != null)
			ac.setCIA_I(i);
		if (j != null) {
			ac.setCIA_J(j);
		}
		AssessResult assr = new AssessResult();
		if (acIds != null && sorts != null && acIds != "" && sorts != "") {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("acId", Integer.parseInt(acIds));
			map.put("sort", sorts);
			List<AssessResult> asslist = assessResultService.queryAssessCount(map);
			
			/**
			 * 添加审计
			 * @author hanyang
			 */
		/*	Audit audit = new Audit();
			audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
			audit.setObject("技术差距分析");
			audit.setDetailed(technologyService.queryById(Integer.parseInt(acIds)).getName());
			audit.setTime(DateUtil.curDateTimeStr19());*/
			
			if (asslist.size() == 0) {
				assessResultService.insert(ac);
				//audit.setType("添加评估");
				//auditService.instert(audit);
			} else if (asslist.size() == 1) {
				ac.setPK_CIA(asslist.get(0).getPK_CIA());
				assessResultService.update(ac);
				//audit.setType("修改评估");
				//auditService.instert(audit);
			} else {
				log.info(acIds + " - " + sorts + " -  asslist.size() - " + asslist.size());
			}
		}
		if (sorts != null && sorts != "") {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sort", sorts.substring(0, 1));
			int countSort = demandColletService.queryNextSortInfo(map);
			List<DemandCollet> demandList = demandColletService.queryNextSort(map);
			int index = 0;
			for (DemandCollet demandCollet : demandList) {
				if (demandCollet.getUnitDomainName().equals(sorts)) {
					index = demandList.indexOf(demandCollet);
				}
			}
			Map<String, Object> tmap = new HashMap<String, Object>();
			if (countSort == index + 1) {
				if (acIds != null) {
					Technology t = technologyService.queryById(Integer.parseInt(acIds));
					sysInfoId = t.getRankId();
					assessOverCount = assessResultService.queryAssessOverCount(acIds);
					//System.out.println("当前进度============="+assessOverCount);
					tmap.put("id", Integer.parseInt(acIds));
					if (assessOverCount == demandList.size()) {
						tmap.put("currentState", "2");
					} else {
						tmap.put("currentState", "1");
					}
					technologyService.updateCurrentState(tmap);
					tmap.put("endTime", new Date());
					technologyService.updateEndTime(tmap);
				}
				log.info("完成……");
			} else {
				if (acIds != null) {
					Technology t = new Technology();
					tmap.put("id", Integer.parseInt(acIds));
					tmap.put("currentState", "1");
					technologyService.updateCurrentState(tmap);
					DemandCollet dc = demandList.get(index + 1);
					sort = dc.getUnitDomainName();
					log.info("next......");
				}
			}
		}

		return SUCCESS;
	}

	/**
	 * 
	 * Description : 查询评估中……
	 * 
	 * */
	public String queryAssessInfo() {
		HttpServletRequest request = super.getRequest();

		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String grade = request.getParameter("grade");
			map.put("acId", Integer.parseInt(id.trim()));
			if (id != null && grade != null && id != "" && grade != "") {
				if ("第二级".equals(grade)) {
					map.put("sort", "6");
				} else if ("第三级".equals(grade)) {
					map.put("sort", "7");
				} else if ("第四级".equals(grade)) {
					map.put("sort", "8");
				} else if ("第五级".equals(grade)) {
					map.put("sort", "9");
				} else {
					log.info("第……级");
				}
				List<DemandCollet> deList = demandColletService.queryAssessInfo(map);
				if (deList.size() > 0) {
					DemandCollet dc = deList.get(0);
					sort = dc.getUnitDomainName();
					acId = id;
				} else {
					log.info("查询出错 queryAssessInfo deList.size=0 ...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 生成差距评估报告
	 * */
	public String assessResultReport() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		request.removeAttribute("stream");
		response.setCharacterEncoding("GBK");

		String id = request.getParameter("id");
		if (id != null && id != "") {
			Technology technology = technologyService.queryById(Integer.parseInt(id)); // 评估的系统
			request.setAttribute("systemName", "[" + technology.getName() + "]等级保护差距分析报告.doc");
			
			/**
			 * 添加审计
			 * @author hanyang
			 */
			/*Audit audit = new Audit();
			audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
			audit.setObject("技术差距分析");
			audit.setDetailed(technology.getName());
			audit.setTime(DateUtil.curDateTimeStr19());
			audit.setType("导出报告");
			auditService.instert(audit);*/
			
			SystemManager systemManager = null;
			Rank rank = null;
			if (technology != null) {
				systemManager = systemService.queryBySysId(technology.getRankId());
				rank = rankService.queryBySysId(technology.getRankId());
			}

			UnitInfo unitInfo = unitInfoService.query();

			Document document = new Document(PageSize.A4, 89.0F, 89.0F, 72.0F, 72.0F);

			ByteArrayOutputStream stream = new ByteArrayOutputStream(1024);
			try {
				String songPath="";
				String blackFontPath="";
				if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
					songPath = "c:\\windows\\fonts\\msyh.ttf";
					blackFontPath = "c:\\windows\\fonts\\simhei.ttf";
				}else{
					songPath="/usr/share/fonts/dejavu/DejaVuSansMono.ttf";
					blackFontPath="/usr/share/fonts/dejavu/DejaVuSansMono-Bold.ttf";
				}
//				BaseFont blackBaseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
//				BaseFont songFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
				BaseFont blackBaseFont = BaseFont.createFont(songPath, "Identity-H", false);
				BaseFont songFont = BaseFont.createFont(blackFontPath, "Identity-H", false);

				Font songfont_11 = new Font(songFont, 11.0F, 0);

				RtfWriter2.getInstance(document, stream);
				// document.addAuthor("du");

				Phrase hearerPhrse = new Phrase("[" + technology.getName() + "]等级保护差距评估报告");
				hearerPhrse.setFont(songfont_11);

				HeaderFooter header = new HeaderFooter(hearerPhrse, false);
				header.setAlignment(2);
				document.setHeader(header);

				HeaderFooter footer = new HeaderFooter(new Phrase("-"), new Phrase("-"));
				footer.setAlignment(2);
				footer.setBorderColor(Color.red);
				footer.setBorder(15);
				document.setFooter(footer);

				document.open();

				RtfParagraphStyle docTitle1 = RtfParagraphStyle.STYLE_HEADING_1;
				docTitle1.setFontName("宋体");
				docTitle1.setSize(22.0F);
				docTitle1.setAlignment(0);
				docTitle1.setStyle(1);
				docTitle1.setSpacingBefore(10);
				docTitle1.setSpacingAfter(10);

				RtfParagraphStyle docTitle2 = RtfParagraphStyle.STYLE_HEADING_2;
				docTitle2.setFamily("宋体");
				docTitle2.setSize(16.0F);
				docTitle2.setAlignment(0);
				docTitle2.setStyle(1);
				docTitle2.setSpacingBefore(10);
				docTitle2.setSpacingAfter(10);

				RtfParagraphStyle docTitle3 = RtfParagraphStyle.STYLE_HEADING_3;
				docTitle3.setFamily("宋体");
				docTitle3.setSize(14.0F);
				docTitle3.setAlignment(0);
				docTitle3.setStyle(1);
				docTitle3.setSpacingBefore(10);
				docTitle3.setSpacingAfter(10);

				Float smartFour = Float.valueOf(12.0F);
				Float normalOne = Float.valueOf(28.0F);
				Float normalThree = Float.valueOf(16.0F);
				Float normalFive = Float.valueOf(10.5F);

				Chunk chunk = new Chunk();
				chunk.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1));

				Paragraph p = new Paragraph(chunk);
				p.setAlignment(1);

				document.add(p);
				document.add(p);
				document.add(p);
				document.add(p);
				document.add(p);


				p = new Paragraph("[" + technology.getName() + "]");
				p.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1));
				p.setAlignment(1);
				document.add(p);

				document.add(Chunk.NEWLINE);

				p = new Paragraph("等级保护差距评估报告");
				p.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1));
				p.setAlignment(1);
				document.add(p);

				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);

				p = new Paragraph("[" + new SimpleDateFormat("yyyy年MM月dd日").format(new Date()) + "]");
				p.setFont(new Font(songFont, normalThree.floatValue(), 1));
				p.setAlignment(1);
				document.add(p);

				document.add(Chunk.NEXTPAGE);

				p = new Paragraph("1.[" + technology.getName() + "]情况");
				p.setSpacingBefore(20.0F);
				p.setFont(docTitle1);
				document.add(p);
				
				p = new Paragraph("1.1.基本信息");
				p.setFont(docTitle2);
				p.setSpacingBefore(8.0F);
				document.add(p);

				Table t = new Table(3);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(90, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 40, 40, 200 });

				Cell cell = new Cell();

				chunk = new Chunk("系统名称");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setColspan(2);
				t.addCell(cell);

				chunk = new Chunk(technology.getName());
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setBackgroundColor(new Color(211, 223, 238));
				t.addCell(cell);

				chunk = new Chunk("主管机构");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setColspan(2);
				t.addCell(cell);

				chunk = new Chunk("");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("系统承载业务情况");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setRowspan(2);
				t.addCell(cell);

				chunk = new Chunk("业务类型");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				if (systemManager != null) {
					if ("1".equals(systemManager.getBusType())) {
						chunk = new Chunk("生产作业");
					} else if ("2".equals(systemManager.getBusType())) {
						chunk = new Chunk("指挥调度");
					} else if ("3".equals(systemManager.getBusType())) {
						chunk = new Chunk("管理控制");
					} else if ("4".equals(systemManager.getBusType())) {
						chunk = new Chunk("内部办公");
					} else if ("5".equals(systemManager.getBusType())) {
						chunk = new Chunk("公众服务");
					} else {
						if(systemManager.getOtherBusType()!=null){
							chunk = new Chunk(systemManager.getOtherBusType());
						}else {
							chunk=new Chunk("");
							}
					}
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("业务描述");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				if (systemManager != null) {
					chunk = new Chunk(systemManager.getBusDescription());
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("系统服务情况");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setRowspan(2);
				t.addCell(cell);

				chunk = new Chunk("服务范围");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				if (systemManager != null) {
					if ("10".equals(systemManager.getSerArea())) {
						chunk = new Chunk("全国");
					} else if ("11".equals(systemManager.getSerArea())) {
						chunk = new Chunk("跨省（区、市） 跨" + systemManager.getProTotal() + "个");
					} else if ("20".equals(systemManager.getSerArea())) {
						chunk = new Chunk("全省（区、市）");
					} else if ("21".equals(systemManager.getSerArea())) {
						chunk = new Chunk("跨地（市、区） 跨" + systemManager.getCityTotal() + "个");
					} else if ("30".equals(systemManager.getSerArea())) {
						chunk = new Chunk("地（市、区）内");
					} else {
						if(systemManager.getOtherArea()!=null){
							chunk = new Chunk(systemManager.getOtherArea());
						}else {
							chunk=new Chunk("");
						}
					}
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("服务对象");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				if (systemManager != null) {
					if ("1".equals(systemManager.getSerObj())) {
						chunk = new Chunk("单位内部人员");
					} else if ("2".equals(systemManager.getSerObj())) {
						chunk = new Chunk("社会公众人员");
					} else if ("3".equals(systemManager.getSerObj())) {
						chunk = new Chunk("两者均包括");
					} else {
						if(systemManager.getOtherObj()!=null){
							chunk = new Chunk(systemManager.getOtherObj());
						}else{
							chunk=new Chunk();
						}
					}
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("系统网络平台");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setRowspan(2);
				t.addCell(cell);

				chunk = new Chunk("覆盖范围");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				if (rank != null) {
					if ("1".equals(rank.getRankCoveArea())) {
						chunk = new Chunk("局域网");
					} else if ("2".equals(rank.getRankCoveArea())) {
						chunk = new Chunk("城域网");
					} else if ("3".equals(rank.getRankCoveArea())) {
						chunk = new Chunk("广域网");
					} else {
						chunk = new Chunk(rank.getRankOthArea());
					}
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("网络性质");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				if (rank != null) {
					if ("1".equals(rank.getRankNetworkProp())) {
						chunk = new Chunk("业务专网");
					} else if ("2".equals(rank.getRankNetworkProp())) {
						chunk = new Chunk("互联网");
					} else {
						chunk = new Chunk(rank.getRankOthNetworkProp());
					}
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBackgroundColor(new Color(211, 223, 238));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("系统互联情况");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setColspan(2);
				t.addCell(cell);
			

				if (rank != null) {
					if ("1".equals(rank.getRankSysConn())) {
						chunk = new Chunk("与其他行业系统连接");
					} else if ("2".equals(rank.getRankSysConn())) {
						chunk = new Chunk("与本行业其他单位系统连接");
					} else if ("3".equals(rank.getRankSysConn())) {
						chunk = new Chunk("与本单位其他系统连接");
					} else {
						chunk = new Chunk(rank.getRankOtherSysConn());
					}
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);
				
				chunk = new Chunk("信息系统安全保护等级");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(79, 129, 189));
				cell.setBorderWidthRight(1.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				cell.setColspan(2);
				t.addCell(cell);
			
				if (rank.getRankGrade()!= null) {
						chunk = new Chunk(rank.getRankGrade());
				} else {
					chunk = new Chunk("");
				}
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(79, 129, 189));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(1.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				document.add(t);

				p = new Paragraph("1.2.网络结构");
				p.setFont(docTitle2);
				p.setSpacingBefore(8.0F);
				document.add(p);
				Image img;
				/*
				 * if (picAttachments.size() > 0) { for (int i=0;i<10;i++) { img = Image.getInstance("");
				 * 
				 * img.setAbsolutePosition(0.0F, 0.0F); img.setAlignment(0);
				 * 
				 * if (img.getWidth() > 400.0F) { img.scalePercent(40000.0F / img.getWidth()); } else { img.scalePercent(100.0F); }
				 * 
				 * document.add(img); }
				 * 
				 * }
				 */

				p = new Paragraph("1.3.系统构成");
				p.setFont(docTitle2);
				p.setSpacingBefore(8.0F);
				document.add(p);

				p = new Paragraph("1.3.1.业务应用软件");
				p.setFont(docTitle3);
				document.add(p);

				p = new Paragraph("业务应用软件列表如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(4);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 30, 85, 95, 70 });

				cell = new Cell();

				chunk = new Chunk("序号");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("软件名称");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("主要功能");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("重要程度");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				int i = 0;
				List<BusinessAssets> businessAssetsList = softUseService.queryAllBusinessAssets();
				for (BusinessAssets businessAssets : businessAssetsList) {
					String[] strArray = null;
					strArray = businessAssets.getSysName().split(";");
					int flg = 0;
					for (String string : strArray) {
						if (technology.getName().equals(string))
							flg++;
					}
					if (flg > 0) {
						i++;
						chunk = new Chunk(String.valueOf(i));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(businessAssets.getResName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(businessAssets.getMianFun());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(businessAssets.getImpDegree());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
				}
				document.add(t);

				document.add(Chunk.NEWLINE);

				p = new Paragraph("1.3.2.关键数据类别");
				p.setFont(docTitle3);
				document.add(p);

				p = new Paragraph("关键数据类别列表如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(5);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 30, 85, 45, 50, 70 });

				cell = new Cell();

				chunk = new Chunk("序号");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("数据类别");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("所属业务应用");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("主机/存储设备");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("重要程度");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				int j = 0;
				List<DataAssets> dataAssetsList = dataAssetsService.queryAllDataAssets();
				for (DataAssets dataAssets : dataAssetsList) {
					String[] strArray = null;
					strArray = dataAssets.getSysName().split(";");
					int flg = 0;
					for (String string : strArray) {
						if (technology.getName().equals(string))
							flg++;
					}

					if (flg > 0) {
						j++;
						chunk = new Chunk(String.valueOf(j));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (j % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(dataAssets.getDateType());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (j % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(dataAssets.getResName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (j % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(dataAssets.getDevDescription());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (j % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(dataAssets.getImpDegree());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (j % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
				}
				document.add(t);

				document.add(Chunk.NEWLINE);

				p = new Paragraph("1.3.3.主机/存储设备");
				p.setFont(docTitle3);
				document.add(p);

				p = new Paragraph("主机/存储设备列表如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(4);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 30, 85, 95, 70 });

				cell = new Cell();

				chunk = new Chunk("序号");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("设备名称");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("系统描述");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("业务应用软件");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				int k = 0;
				List<CompAssets> compAssetsList = compAssetsService.queryAllCompAssets();
				for (CompAssets compAssets : compAssetsList) {
					String[] strArray = null;
					strArray = compAssets.getSysName().split(";");
					int flg = 0;
					for (String string : strArray) {
						if (technology.getName().equals(string))
							flg++;
					}

					if (flg > 0) {
						k++;
						chunk = new Chunk(String.valueOf(k));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (k % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(compAssets.getDevName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (k % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(compAssets.getDevDescription());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (k % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(compAssets.getResName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (k % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}

				}
				document.add(t);

				document.add(Chunk.NEWLINE);

				p = new Paragraph("1.3.4.网络互联设备");
				p.setFont(docTitle3);
				document.add(p);

				p = new Paragraph("网络互联设备列表如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(4);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 30, 85, 95, 70 });

				cell = new Cell();

				chunk = new Chunk("序号");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("设备名称");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("用  途");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("重要程度");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				int l = 0;
				List<NetAssets> netAssetsList = netAssetsService.queryAllNetAssets();
				for (NetAssets netAssets : netAssetsList) {
					String[] strArray = null;
					strArray = netAssets.getSysName().split(";");
					int flg = 0;
					for (String string : strArray) {
						if (technology.getName().equals(string))
							flg++;
					}

					if (flg > 0) {
						l++;
						chunk = new Chunk(String.valueOf(l));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (l % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(netAssets.getDevName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (l % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(netAssets.getDevDescriotion());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (l % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(netAssets.getImpDegree());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (l % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
				}
				document.add(t);

				document.add(Chunk.NEWLINE);

				p = new Paragraph("1.3.5.安全设备");
				p.setFont(docTitle3);
				document.add(p);

				p = new Paragraph("安全设备列表如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(4);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 30, 85, 95, 70 });

				cell = new Cell();

				chunk = new Chunk("序号");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("设备名称");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("用  途");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("重要程度");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				int ii = 0;
				List<DevAssets> devAssetsList = devAssetsService.queryAllDevAssets();
				for (DevAssets devAssets : devAssetsList) {
					String[] strArray = null;
					strArray = devAssets.getSysName().split(";");
					int flg = 0;
					for (String string : strArray) {
						if (technology.getName().equals(string))
							flg++;
					}

					if (flg > 0) {
						ii++;
						chunk = new Chunk(String.valueOf(ii));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ii % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(devAssets.getDevName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ii % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(devAssets.getDevDescription());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ii % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(devAssets.getImpDegree());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ii % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
				}
				document.add(t);
				document.add(Chunk.NEWLINE);

				p = new Paragraph("1.3.6.安全相关人员");
				p.setFont(docTitle3);
				document.add(p);

				p = new Paragraph("安全相关人员列表如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(4);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 30, 85, 95, 70 });

				cell = new Cell();

				chunk = new Chunk("序号");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("姓名");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("岗位/角色");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("联系方式");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				int i6 = 0;
				List<EmpAssets> empAssetsList = empAssetsService.queryAllEmpAssets();
				for (EmpAssets empAssets : empAssetsList) {
					String[] strArray = null;
					strArray = empAssets.getSysName().split(";");
					int flg = 0;
					for (String string : strArray) {
						if (technology.getName().equals(string))
							flg++;
					}

					if (flg > 0) {
						i6++;
						chunk = new Chunk(String.valueOf(i6));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i6 % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(empAssets.getEmpName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i6 % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(empAssets.getJobDes());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i6 % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(empAssets.getConInfo());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i6 % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}

				}
				document.add(t);
				document.add(Chunk.NEWLINE);

				p = new Paragraph("1.3.7.安全管理文档");
				p.setFont(docTitle3);
				document.add(p);

				p = new Paragraph("安全管理文档列表如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(3);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 30, 85, 95 });

				cell = new Cell();

				chunk = new Chunk("序号");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("文档名称");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("主要内容");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				int i7 = 0;
				List<DocAssets> docAssetsList = docAssetsService.queryAllDocAssets();
				for (DocAssets docAssets : docAssetsList) {
					String[] strArray = null;
					strArray = docAssets.getSysName().split(";");
					int flg = 0;
					for (String string : strArray) {
						if (technology.getName().equals(string))
							flg++;
					}

					if (flg > 0) {
						i7++;
						chunk = new Chunk(String.valueOf(i7));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i7 % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(docAssets.getDocName());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i7 % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(docAssets.getImpContent());
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (i7 % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
				}
				document.add(t);
				document.add(Chunk.NEWLINE);

				p = new Paragraph("2.差距评估概述");
				p.setSpacingBefore(20.0F);
				p.setFont(docTitle1);
				document.add(p);

				p = new Paragraph("2.1.差距评估目的");
				p.setSpacingBefore(20.0F);
				p.setFont(docTitle2);
				document.add(p);

				p = new Paragraph("进行差距评估的目的是在完成信息系统定级后或每次自查时，依据信息系统所属的安全保护级别要求对相应的信息系统进行评估，以便全面、完整地了解信息系统等级保护要求的基本安全控制在信息系统中的配置情况以及系统的整体安全性，同时发现与《信息系统安全等级保护基本要求》之间的差距并分析其导致原因，明确下一步进行整改的需求与内容。");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				p = new Paragraph("2.2.差距评估依据");
				p.setSpacingBefore(20.0F);
				p.setFont(docTitle2);
				document.add(p);

				p = new Paragraph("本次差距评估工作所依据的文件和标准如下：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(8.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				com.lowagie.text.List itextList1_2 = new com.lowagie.text.List(false, 9.0F);
				chunk = new Chunk("●");
				chunk.setFont(new Font(songFont, smartFour.floatValue(), 0));

				itextList1_2.setListSymbol(chunk);
				itextList1_2.setIndentationLeft(24.0F);
				itextList1_2.setSymbolIndent(12.0F);

				p = new Paragraph("《信息安全等级保护管理办法》（公通字[2007]43号）");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem firstItem1_2 = new ListItem(p);
				itextList1_2.add(firstItem1_2);

				p = new Paragraph("GB/T 22240-2008 信息安全技术 信息系统安全等级保护定级指南");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem secondItem1_2 = new ListItem(p);
				itextList1_2.add(secondItem1_2);

				p = new Paragraph("GB/T 22239-2008 信息安全技术 信息系统安全等级保护基本要求");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem thirdItem1_2 = new ListItem(p);
				itextList1_2.add(thirdItem1_2);

				p = new Paragraph("GB/T 25058-2010 信息安全技术 信息系统安全等级保护实施指南");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem fourItem1_2 = new ListItem(p);
				itextList1_2.add(fourItem1_2);

				p = new Paragraph("[" + technology.getName() + "安全等级保护定级报告]");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem fiveItem1_2 = new ListItem(p);
				itextList1_2.add(fiveItem1_2);

				document.add(itextList1_2);

				p = new Paragraph("2.3.差距评估方法");
				p.setFont(docTitle2);
				document.add(p);

				p = new Paragraph("差距评估的主要方式有：人员访谈、文档检查、技术核查和工具测试。");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				com.lowagie.text.List itextList1_4_1 = new com.lowagie.text.List(false, 9.0F);
				itextList1_4_1.setListSymbol("●");
				itextList1_4_1.setIndentationLeft(24.0F);
				itextList1_4_1.setSymbolIndent(12.0F);

				p = new Paragraph("人员访谈");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem firstItem1_4 = new ListItem(p);
				itextList1_4_1.add(firstItem1_4);
				document.add(itextList1_4_1);

				p = new Paragraph("通过询问、交流的方式，检查系统运行相关工作人员在实际工作中对安全管理规章制度的认知程度和执行情况；");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				com.lowagie.text.List itextList1_4_2 = new com.lowagie.text.List(false, 9.0F);
				itextList1_4_2.setListSymbol("●");
				itextList1_4_2.setIndentationLeft(24.0F);
				itextList1_4_2.setSymbolIndent(12.0F);

				p = new Paragraph("文档检查");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem Item1_4_2 = new ListItem(p);
				itextList1_4_2.add(Item1_4_2);
				document.add(itextList1_4_2);

				p = new Paragraph("通过检查相关文档及信息安全管理制度，验证已有文档与基本要求的符合程度；");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				com.lowagie.text.List itextList1_4_3 = new com.lowagie.text.List(false, 9.0F);
				itextList1_4_3.setListSymbol("●");
				itextList1_4_3.setIndentationLeft(24.0F);
				itextList1_4_3.setSymbolIndent(12.0F);

				p = new Paragraph("技术核查");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem Item1_4_3 = new ListItem(p);
				itextList1_4_3.add(Item1_4_3);
				document.add(itextList1_4_3);

				p = new Paragraph("针对系统的不同层面，分别从物理、主机、网络、应用和数据五个层面，检查系统安全功能的设置和实现情况，验证系统中设置的安全功能是否有效地发挥作用；");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				com.lowagie.text.List itextList1_4_4 = new com.lowagie.text.List(false, 9.0F);
				itextList1_4_4.setListSymbol("●");
				itextList1_4_4.setIndentationLeft(24.0F);
				itextList1_4_4.setSymbolIndent(12.0F);

				p = new Paragraph("工具测试");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setSpacingBefore(10.0F);
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);

				ListItem Item1_4_4 = new ListItem(p);
				itextList1_4_4.add(Item1_4_4);
				document.add(itextList1_4_4);

				p = new Paragraph("通过利用专用的安全工具对系统的安全功能要求以及如何正确有效实施这些功能的保证要求进行测试，以验证系统在技术方面达到的安全程度是否符合被测系统业务需求和安全防护需求。");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				p = new Paragraph("3.差距评估结果综述");
				p.setFont(docTitle1);
				document.add(p);

				p = new Paragraph("3.1.总体符合度");
				p.setFont(docTitle2);
				document.add(p);

				String technologyDomain = "";
				if ("第二级".equals(technology.getSysGrade())) {
					technologyDomain = "六";
				} else if ("第三级".equals(technology.getSysGrade())) {
					technologyDomain = "七";
				} else if ("第四级".equals(technology.getSysGrade())) {
					technologyDomain = "七";
				} else {
					technologyDomain = "";
				}
				p = new Paragraph("通过对[" + technology.getName() + "]当前在等级保护[" + technology.getSysGrade() + "]级的" + technologyDomain + "个领域相应控制项的逐一评价，得到了[" + technology.getName() + "]总体符合度见下图:");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				JFreeChart chart = assessResultService.getAssessResultTotalPieJFreeChart(technology.getId());
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ChartUtilities.writeChartAsPNG(baos, chart, 600, 350);
				Image png = Image.getInstance(baos.toByteArray());
				png.scalePercent(70.0F, 70.0F);
				document.add(png);

				p = new Paragraph("3.2.差距分布图");
				p.setFont(docTitle2);
				document.add(p);

				p = new Paragraph("[" + technology.getName() + "]差距项统计如下表所示：");
				p.setFont(new Font(songFont, smartFour.floatValue(), 0));
				p.setFirstLineIndent(24.0F);
				p.setLeading(22.0F);
				document.add(p);

				t = new Table(8);
				t.setBorderWidth(1.0F);
				t.setBorderColor(new Color(79, 129, 189));
				t.setPadding(5.0F);

				t.setWidth(100.0F);
				t.setWidths(new int[] { 10, 5, 5, 5, 5, 5, 5, 5 });

				chunk = new Chunk("安全领域");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("标准控制项（单元）");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("适用控制项（单元）");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("符合项（单元）");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("部分符合项（单元）");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("不符合项（单元）");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("差距项（单元）");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				chunk = new Chunk("符合度");
				chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
				cell = new Cell(chunk);
				cell.setHorizontalAlignment(1);
				cell.setVerticalAlignment(5);
				cell.setBorderColor(new Color(79, 129, 189));
				cell.setBorderColorLeft(new Color(0, 0, 0));
				cell.setBorderColorRight(new Color(0, 0, 0));
				cell.setBorderWidthRight(0.0F);
				cell.setBorderWidthLeft(0.0F);
				cell.setBorderWidthBottom(1.0F);
				cell.setBorderWidthTop(1.0F);
				t.addCell(cell);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("length", 3);
				if ("第二级".equals(technology.getSysGrade())) {
					map.put("sort", "6");
				} else if ("第三级".equals(technology.getSysGrade())) {
					map.put("sort", "7");
				} else if ("第四级".equals(technology.getSysGrade())) {
					map.put("sort", "8");
				} else {
					map.put("sort", "0");
				}

				List<DemandCollet> demandColletList = demandColletService.queryAssessInfoImage(map);

				int ff = 0;
				int gapanalysisNumber;
				for (DemandCollet demandCollet : demandColletList) {
					ff++;
					chunk = new Chunk(demandCollet.getUnitName());
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					if (ff % 2 == 1)
						cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					Map<String, Object> tableMap = new HashMap<String, Object>();
					tableMap.put("acId", technology.getId());
					tableMap.put("sort", demandCollet.getUnitDomainName());
					List<AssessResult> assessResultList = assessResultService.queryAssessCountTable(tableMap);
					int assessResult0 = 0;// 符合
					int assessResult1 = 0;// 部分符合
					int assessResult2 = 0;// 不符合
					for (AssessResult assessResult : assessResultList) {
						if ("0".equals(assessResult.getCIA_AssessResult()))
							assessResult0++;
						if ("1".equals(assessResult.getCIA_AssessResult()))
							assessResult1++;
						if ("2".equals(assessResult.getCIA_AssessResult()))
							assessResult2++;
					}

					chunk = new Chunk(String.valueOf(assessResultList.size()));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					if (ff % 2 == 1)
						cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResultList.size()));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					if (ff % 2 == 1)
						cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResult0));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					if (ff % 2 == 1)
						cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResult1));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					if (ff % 2 == 1)
						cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResult2));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					if (ff % 2 == 1)
						cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResult1 + assessResult2));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					if (ff % 2 == 1)
						cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					if (assessResult0 + assessResult1 + assessResult2 != 0) {
						chunk = new Chunk(StringUtil.formatNumber(Double.valueOf(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2))), "0.00%"));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ff % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					} else {
						chunk = new Chunk("");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ff % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
				}
				document.add(t);

				chart = assessResultService.getAssessResultRadarJFreeChart(demandColletList, technology.getId());
				baos = new ByteArrayOutputStream();
				ChartUtilities.writeChartAsPNG(baos, chart, 566, 400);
				png = Image.getInstance(baos.toByteArray());
				png.scalePercent(70.0F, 70.0F);
				document.add(png);

				p = new Paragraph("4.差距评估结果详述");
				p.setFont(docTitle1);
				document.add(p);

				int m = 0;
				for (DemandCollet demandCollet : demandColletList) {
					m++;
					p = new Paragraph("4." + m + "." + demandCollet.getUnitName());
					p.setFont(docTitle2);
					document.add(p);

					p = new Paragraph("4." + m + ".1.差距评估统计图表");
					p.setFont(docTitle3);
					document.add(p);

					p = new Paragraph("[" + demandCollet.getUnitName() + "]下各个点符合度列表如下：");
					p.setFont(new Font(songFont, smartFour.floatValue(), 0));
					p.setSpacingBefore(8.0F);
					p.setFirstLineIndent(24.0F);
					p.setLeading(22.0F);
					document.add(p);

					t = new Table(8);
					t.setBorderWidth(1.0F);
					t.setBorderColor(new Color(79, 129, 189));
					t.setPadding(5.0F);

					t.setWidth(100.0F);
					t.setWidths(new int[] { 5, 10, 5, 5, 5, 5, 5, 5 });

					chunk = new Chunk("标准号");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("控制点");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("标准控制项（单元）");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("适用控制项（单元）");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("符合项（单元）");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("部分符合项（单元）");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("不符合项（单元）");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("符合度");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					Map<String, Object> tableMap = new HashMap<String, Object>();
					tableMap.put("acId", technology.getId());
					tableMap.put("sort", demandCollet.getUnitDomainName());
					List<AssessResult> assessResultList = assessResultService.queryAssessCountTable(tableMap);
					int assessResult0 = 0;// 符合
					int assessResult1 = 0;// 部分符合
					int assessResult2 = 0;// 不符合
					for (AssessResult assessResult : assessResultList) {
						if ("0".equals(assessResult.getCIA_AssessResult()))
							assessResult0++;
						if ("1".equals(assessResult.getCIA_AssessResult()))
							assessResult1++;
						if ("2".equals(assessResult.getCIA_AssessResult()))
							assessResult2++;
					}

					chunk = new Chunk(demandCollet.getUnitDomainName().charAt(0));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(demandCollet.getUnitName());
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResultList.size()));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResultList.size()));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResult0));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResult1));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk(String.valueOf(assessResult2));
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBackgroundColor(new Color(211, 223, 238));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					if (assessResult0 + assessResult1 + assessResult2 != 0) {
						chunk = new Chunk(StringUtil.formatNumber(Double.valueOf(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2))), "0.00%"));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					} else {
						chunk = new Chunk("");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
					document.add(t);

					chart = assessResultService.getPointJFreeChart(assessResult0, assessResult1, assessResult2);
					baos = new ByteArrayOutputStream();
					ChartUtilities.writeChartAsPNG(baos, chart, 600, 400);
					png = Image.getInstance(baos.toByteArray());
					png.scalePercent(70.0F, 70.0F);
					document.add(png);

					/*
					 * 雷达图 chart = assessResultService.getPointRadarJFreeChart(assessResult0, assessResult1, assessResult2); baos = new ByteArrayOutputStream(); ChartUtilities.writeChartAsPNG(baos, chart, 600, 400); png = Image.getInstance(baos.toByteArray()); png.scalePercent(70.0F, 70.0F); document.add(png);
					 * 
					 * 柱状图 chart = assessResultService.getPointFitBarChart(assessResult0, assessResult1, assessResult2); baos = new ByteArrayOutputStream(); ChartUtilities.writeChartAsPNG(baos, chart, 600, 400); png = Image.getInstance(baos.toByteArray()); png.scalePercent(70.0F, 70.0F); document.add(png);
					 */

					p = new Paragraph("4." + m + ".2.不符合项列表");
					p.setFont(docTitle3);
					document.add(p);

					if (assessResult2 > 0) {
						p = new Paragraph("[" + demandCollet.getUnitName() + "]不符合项列表：");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						p.setSpacingBefore(8.0F);
						p.setFirstLineIndent(24.0F);
						p.setLeading(22.0F);
						document.add(p);
					} else {
						p = new Paragraph("");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						p.setSpacingBefore(8.0F);
						p.setFirstLineIndent(24.0F);
						p.setLeading(22.0F);
						document.add(p);
					}

					int v3 = 0;
					for (AssessResult assessResult : assessResultList) {
						if ("2".equals(assessResult.getCIA_AssessResult())) {
							v3++;
							t = new Table(4);
							t.setBorderWidth(1.0F);
							t.setBorderColor(new Color(79, 129, 189));
							t.setPadding(5.0F);

							t.setWidth(100.0F);
							t.setWidths(new int[] { 5, 8, 10, 30 });

							chunk = new Chunk("序号");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制单元标准号");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制单元");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制项要求");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(v3));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(assessResult.getCIA_Sort());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(demandColletService.queryUnitDomainNameByNum(assessResult.getCIA_Sort()));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							String sortInfoChunk = "";
							if ("0".equals(assessResult.getCIA_B()))
								sortInfoChunk += "b)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.b").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_C()))
								sortInfoChunk += "c)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.c").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_D()))
								sortInfoChunk += "d)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.d").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_E()))
								sortInfoChunk += "e)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.e").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_F()))
								sortInfoChunk += "f)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.f").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_G()))
								sortInfoChunk += "g)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.g").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_H()))
								sortInfoChunk += "h)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.h").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_I()))
								sortInfoChunk += "i)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.i").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_J()))
								sortInfoChunk += "j)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.j").getUnitCon() + "\n";

							chunk = new Chunk(sortInfoChunk.trim());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(0);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk("主要问题描述与原因分析");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(4.0F);
							cell.setBorderWidthTop(1.0F);
							cell.setColspan(2);
							t.addCell(cell);

							chunk = new Chunk(assessResult.getCIA_MainProbDes());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(0);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(4.0F);
							cell.setBorderWidthTop(1.0F);
							cell.setColspan(2);
							t.addCell(cell);
							document.add(t);
						}
					}

					p = new Paragraph("4." + m + ".3.部分符合项列表");
					p.setFont(docTitle3);
					document.add(p);

					if (assessResult1 > 0) {
						p = new Paragraph("[" + demandCollet.getUnitName() + "]部分符合项列表：");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						p.setSpacingBefore(8.0F);
						p.setFirstLineIndent(24.0F);
						p.setLeading(22.0F);
						document.add(p);
					} else {
						p = new Paragraph("");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						p.setSpacingBefore(8.0F);
						p.setFirstLineIndent(24.0F);
						p.setLeading(22.0F);
						document.add(p);
					}

					int v4 = 0;
					for (AssessResult assessResult : assessResultList) {
						if ("1".equals(assessResult.getCIA_AssessResult())) {
							v4++;
							t = new Table(4);
							t.setBorderWidth(1.0F);
							t.setBorderColor(new Color(79, 129, 189));
							t.setPadding(5.0F);

							t.setWidth(100.0F);
							t.setWidths(new int[] { 5, 8, 10, 30 });

							chunk = new Chunk("序号");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制单元标准号");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制单元");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制项要求");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(v4));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(assessResult.getCIA_Sort());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(demandColletService.queryUnitDomainNameByNum(assessResult.getCIA_Sort()));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							String sortInfoChunk = "";
							if ("0".equals(assessResult.getCIA_B()))
								sortInfoChunk += "b)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.b").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_C()))
								sortInfoChunk += "c)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.c").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_D()))
								sortInfoChunk += "d)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.d").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_E()))
								sortInfoChunk += "e)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.e").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_F()))
								sortInfoChunk += "f)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.f").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_G()))
								sortInfoChunk += "g)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.g").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_H()))
								sortInfoChunk += "h)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.h").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_I()))
								sortInfoChunk += "i)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.i").getUnitCon() + "\n";
							if ("0".equals(assessResult.getCIA_J()))
								sortInfoChunk += "j)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.j").getUnitCon() + "\n";

							chunk = new Chunk(sortInfoChunk.trim());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(0);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk("主要问题描述与原因分析");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(4.0F);
							cell.setBorderWidthTop(1.0F);
							cell.setColspan(2);
							t.addCell(cell);

							chunk = new Chunk(assessResult.getCIA_MainProbDes());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(0);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(4.0F);
							cell.setBorderWidthTop(1.0F);
							cell.setColspan(2);
							t.addCell(cell);
							document.add(t);
						}
					}

					p = new Paragraph("4." + m + ".4.符合项列表");
					p.setFont(docTitle3);
					document.add(p);

					if (assessResult0 > 0) {
						p = new Paragraph("[" + demandCollet.getUnitName() + "]符合项列表：");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						p.setSpacingBefore(8.0F);
						p.setFirstLineIndent(24.0F);
						p.setLeading(22.0F);
						document.add(p);
					} else {
						p = new Paragraph("");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						p.setSpacingBefore(8.0F);
						p.setFirstLineIndent(24.0F);
						p.setLeading(22.0F);
						document.add(p);
					}

					int v5 = 0;
					for (AssessResult assessResult : assessResultList) {
						if ("0".equals(assessResult.getCIA_AssessResult())) {
							v5++;
							t = new Table(4);
							t.setBorderWidth(1.0F);
							t.setBorderColor(new Color(79, 129, 189));
							t.setPadding(5.0F);

							t.setWidth(100.0F);
							t.setWidths(new int[] { 5, 8, 10, 30 });

							chunk = new Chunk("序号");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制单元标准号");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制单元");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk("控制项要求");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(4.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(v5));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(assessResult.getCIA_Sort());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(demandColletService.queryUnitDomainNameByNum(assessResult.getCIA_Sort()));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							String sortInfoChunk = "";
							if ("1".equals(assessResult.getCIA_B()))
								sortInfoChunk += "b)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.b").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_C()))
								sortInfoChunk += "c)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.c").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_D()))
								sortInfoChunk += "d)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.d").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_E()))
								sortInfoChunk += "e)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.e").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_F()))
								sortInfoChunk += "f)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.f").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_G()))
								sortInfoChunk += "g)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.g").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_H()))
								sortInfoChunk += "h)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.h").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_I()))
								sortInfoChunk += "i)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.i").getUnitCon() + "\n";
							if ("1".equals(assessResult.getCIA_J()))
								sortInfoChunk += "j)  " + demandColletService.querydemandColletBySort(assessResult.getCIA_Sort() + ".2.j").getUnitCon() + "\n";

							chunk = new Chunk(sortInfoChunk.trim());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(0);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk("主要问题描述与原因分析");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(79, 129, 189));
							cell.setBorderWidthRight(1.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(4.0F);
							cell.setBorderWidthTop(1.0F);
							cell.setColspan(2);
							t.addCell(cell);

							chunk = new Chunk(assessResult.getCIA_MainProbDes());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(0);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							cell.setBorderColorLeft(new Color(79, 129, 189));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(1.0F);
							cell.setBorderWidthBottom(4.0F);
							cell.setBorderWidthTop(1.0F);
							cell.setColspan(2);
							t.addCell(cell);
							document.add(t);
						}
					}

				}
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("stream", stream);
		}
		return SUCCESS;
	}

	/**
	 * 历次差距评估
	 * 
	 * */
	public String everyTimeAssessResultReport() {
		HttpServletRequest request = super.getRequest();
		HttpServletResponse response = super.getResponse();
		request.removeAttribute("stream");
		response.setCharacterEncoding("GBK");

		String sysInfoId = request.getParameter("sysInfoId");
		if (sysInfoId != null && sysInfoId != "") {
			// Technology technology = technologyService.queryById(Integer.parseInt(id)); // 评估的系统
			List<Technology> technologyList = technologyService.queryByAcIdForCs2(sysInfoId);
			if (technologyList.size() > 0) {

				request.setAttribute("systemName", "[" + technologyList.get(0).getName() + "]历次差距评估结果对比报告.doc");
				
				
				/**
				 * 添加审计
				 * @author hanyang
				 */
			/*	Audit a = new Audit();
				a.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
				a.setObject("技术差距分析");
				a.setDetailed(technologyList.get(0).getName());
				a.setTime(DateUtil.curDateTimeStr19());
				a.setType("生成评估对比");
				auditService.instert(a);*/
				
				
				Document document = new Document(PageSize.A4, 89.0F, 89.0F, 72.0F, 72.0F);

				ByteArrayOutputStream stream = new ByteArrayOutputStream(1024);
				try {
					String songPath="";
					String blackFontPath="";
					if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS")==0){
						songPath = "c:\\windows\\fonts\\msyh.ttf";
						blackFontPath = "c:\\windows\\fonts\\simhei.ttf";
					}else{
						songPath="/usr/share/fonts/dejavu/DejaVuSansMono.ttf";
						blackFontPath="/usr/share/fonts/dejavu/DejaVuSansMono-Bold.ttf";
					}

					BaseFont blackBaseFont = BaseFont.createFont(songPath, "Identity-H", false);
					BaseFont songFont = BaseFont.createFont(blackFontPath, "Identity-H", false);

					Font songfont_11 = new Font(songFont, 11.0F, 0);

					RtfWriter2.getInstance(document, stream);

					Phrase hearerPhrse = new Phrase("[" + technologyList.get(0).getName() + "]历次差距评估结果对比报告");
					hearerPhrse.setFont(songfont_11);

					HeaderFooter header = new HeaderFooter(hearerPhrse, false);
					header.setAlignment(2);
					document.setHeader(header);

					HeaderFooter footer = new HeaderFooter(new Phrase("-"), new Phrase("-"));
					footer.setAlignment(2);
					footer.setBorderColor(Color.red);
					footer.setBorder(15);
					document.setFooter(footer);

					document.open();

					RtfParagraphStyle docTitle1 = RtfParagraphStyle.STYLE_HEADING_1;
					docTitle1.setFontName("宋体");
					docTitle1.setSize(22.0F);
					docTitle1.setAlignment(0);
					docTitle1.setStyle(1);
					docTitle1.setSpacingBefore(10);
					docTitle1.setSpacingAfter(10);

					RtfParagraphStyle docTitle2 = RtfParagraphStyle.STYLE_HEADING_2;
					docTitle2.setFamily("宋体");
					docTitle2.setSize(16.0F);
					docTitle2.setAlignment(0);
					docTitle2.setStyle(1);
					docTitle2.setSpacingBefore(10);
					docTitle2.setSpacingAfter(10);

					RtfParagraphStyle docTitle3 = RtfParagraphStyle.STYLE_HEADING_3;
					docTitle3.setFamily("宋体");
					docTitle3.setSize(14.0F);
					docTitle3.setAlignment(0);
					docTitle3.setStyle(1);
					docTitle3.setSpacingBefore(10);
					docTitle3.setSpacingAfter(10);

					Float smartFour = Float.valueOf(12.0F);
					Float normalOne = Float.valueOf(28.0F);
					Float normalThree = Float.valueOf(16.0F);
					Float normalFive = Float.valueOf(10.5F);

					Chunk chunk = new Chunk();
					chunk.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1));

					Paragraph p = new Paragraph(chunk);
					p.setAlignment(1);

					document.add(p);
					document.add(p);
					document.add(p);
					document.add(p);
					document.add(p);

					/*
					 * p = new Paragraph("[compliance]"); p.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1)); p.setAlignment(1); document.add(p);
					 * 
					 * document.add(Chunk.NEWLINE);
					 * 
					 * p = new Paragraph("[" + technology.getName() + "]"); p.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1)); p.setAlignment(1); document.add(p);
					 * 
					 * document.add(Chunk.NEWLINE);
					 */

					p = new Paragraph("历次差距评估结果对比报告");
					p.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1));
					p.setAlignment(1);
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

					p = new Paragraph("[" + new SimpleDateFormat("yyyy年MM月dd日").format(new Date()) + "]");
					p.setFont(new Font(songFont, normalThree.floatValue(), 1));
					p.setAlignment(1);
					document.add(p);

					document.add(Chunk.NEXTPAGE);

					int i = 0;
					for (Technology tec : technologyList) {
						i++;
						p = new Paragraph(i + " " + tec.getName() + " 系统 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tec.getEndTime()) + " 差距评估结果");
						p.setFont(docTitle2);
						document.add(p);

						p = new Paragraph(i + ".1  各项安全项符合度列表");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						document.add(p);

						Table t = new Table(8);
						t.setBorderWidth(1.0F);
						t.setBorderColor(new Color(79, 129, 189));
						t.setPadding(5.0F);

						t.setWidth(100.0F);
						t.setWidths(new int[] { 10, 5, 5, 5, 5, 5, 5, 5 });

						chunk = new Chunk("安全领域");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						Cell cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk("标准控制项（单元）");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk("适用控制项（单元）");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk("符合项（单元）");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk("部分符合项（单元）");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk("不符合项（单元）");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk("差距项（单元）");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk("符合度");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						Map<String, Object> map = new HashMap<String, Object>();
						map.put("length", 3);
						if ("第二级".equals(tec.getSysGrade())) {
							map.put("sort", "6");
						} else if ("第三级".equals(tec.getSysGrade())) {
							map.put("sort", "7");
						} else if ("第四级".equals(tec.getSysGrade())) {
							map.put("sort", "8");
						} else {
							map.put("sort", "0");
						}

						List<DemandCollet> demandColletList = demandColletService.queryAssessInfoImage(map);

						int ff = 0;
						int totSize = 0;
						int totAR0 = 0;
						int totAR1 = 0;
						int totAR2 = 0;
						List<Double> douList = new ArrayList<Double>();
						for (DemandCollet demandCollet : demandColletList) {
							ff++;
							chunk = new Chunk(demandCollet.getUnitName());
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if (ff % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							Map<String, Object> tableMap = new HashMap<String, Object>();
							tableMap.put("acId", tec.getId());
							tableMap.put("sort", demandCollet.getUnitDomainName());
							List<AssessResult> assessResultList = assessResultService.queryAssessCountTable(tableMap);
							int assessResult0 = 0;// 符合
							int assessResult1 = 0;// 部分符合
							int assessResult2 = 0;// 不符合
							for (AssessResult assessResult : assessResultList) {
								if ("0".equals(assessResult.getCIA_AssessResult()))
									assessResult0++;
								if ("1".equals(assessResult.getCIA_AssessResult()))
									assessResult1++;
								if ("2".equals(assessResult.getCIA_AssessResult()))
									assessResult2++;
							}
							totSize += assessResultList.size();
							totAR0 += assessResult0;
							totAR1 += assessResult1;
							totAR2 += assessResult2;

							chunk = new Chunk(String.valueOf(assessResultList.size()));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if (ff % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(assessResultList.size()));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if (ff % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(assessResult0));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if (ff % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(assessResult1));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if (ff % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(assessResult2));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if (ff % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							chunk = new Chunk(String.valueOf(assessResult1 + assessResult2));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if (ff % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);

							if (assessResult0 + assessResult1 + assessResult2 != 0) {
								douList.add(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)));
								chunk = new Chunk(StringUtil.formatNumber(Double.valueOf(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2))), "0.00%"));
								chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
								cell = new Cell(chunk);
								cell.setHorizontalAlignment(1);
								cell.setVerticalAlignment(5);
								cell.setBorderColor(new Color(79, 129, 189));
								if (ff % 2 == 1)
									cell.setBackgroundColor(new Color(211, 223, 238));
								cell.setBorderColorLeft(new Color(0, 0, 0));
								cell.setBorderColorRight(new Color(0, 0, 0));
								cell.setBorderWidthRight(0.0F);
								cell.setBorderWidthLeft(0.0F);
								cell.setBorderWidthBottom(1.0F);
								cell.setBorderWidthTop(1.0F);
								t.addCell(cell);
							} else {
								chunk = new Chunk("");
								chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
								cell = new Cell(chunk);
								cell.setHorizontalAlignment(1);
								cell.setVerticalAlignment(5);
								cell.setBorderColor(new Color(79, 129, 189));
								if (ff % 2 == 1)
									cell.setBackgroundColor(new Color(211, 223, 238));
								cell.setBorderColorLeft(new Color(0, 0, 0));
								cell.setBorderColorRight(new Color(0, 0, 0));
								cell.setBorderWidthRight(0.0F);
								cell.setBorderWidthLeft(0.0F);
								cell.setBorderWidthBottom(1.0F);
								cell.setBorderWidthTop(1.0F);
								t.addCell(cell);
							}
						}

						chunk = new Chunk("合计");
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if ((ff + 1) % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(String.valueOf(totSize));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if ((ff + 1) % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(String.valueOf(totSize));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if ((ff + 1) % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(String.valueOf(totAR0));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if ((ff + 1) % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(String.valueOf(totAR1));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if ((ff + 1) % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(String.valueOf(totAR2));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if ((ff + 1) % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						chunk = new Chunk(String.valueOf(totAR1 + totAR2));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if ((ff + 1) % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);

						if (totAR0 + totAR1 + totAR2 != 0) {
							tec.setDescribe(StringUtil.formatNumber(Double.valueOf(Double.parseDouble(String.valueOf(totAR0)) / Double.parseDouble(String.valueOf(totAR0 + totAR1 + totAR2))), "0.00"));
							chunk = new Chunk(StringUtil.formatNumber(Double.valueOf(Double.parseDouble(String.valueOf(totAR0)) / Double.parseDouble(String.valueOf(totAR0 + totAR1 + totAR2))), "0.00%"));
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if ((ff + 1) % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);
						} else {
							chunk = new Chunk("");
							chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
							cell = new Cell(chunk);
							cell.setHorizontalAlignment(1);
							cell.setVerticalAlignment(5);
							cell.setBorderColor(new Color(79, 129, 189));
							if ((ff + 1) % 2 == 1)
								cell.setBackgroundColor(new Color(211, 223, 238));
							cell.setBorderColorLeft(new Color(0, 0, 0));
							cell.setBorderColorRight(new Color(0, 0, 0));
							cell.setBorderWidthRight(0.0F);
							cell.setBorderWidthLeft(0.0F);
							cell.setBorderWidthBottom(1.0F);
							cell.setBorderWidthTop(1.0F);
							t.addCell(cell);
						}

						document.add(t);

						document.add(Chunk.NEWLINE);

						p = new Paragraph(i + ".2  各项安全项符合度柱状图");
						p.setFont(new Font(songFont, smartFour.floatValue(), 0));
						document.add(p);
						
						
						//生成柱状图
						JFreeChart chart = assessResultService.getEveryTimePointBarChart(demandColletList,douList,tec.getName());
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ChartUtilities.writeChartAsPNG(baos, chart, 600, 400);
						Image png = Image.getInstance(baos.toByteArray());
						png.scalePercent(70.0F, 70.0F);
						document.add(png);
					}
					
					
					p = new Paragraph((i+1) + " 历次评估符合度对比折线图");
					p.setFont(docTitle2);
					document.add(p);
					
					JFreeChart chart = assessResultService.getEveryTimeLineBarChart(technologyList);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ChartUtilities.writeChartAsPNG(baos, chart, 600, 400);
					Image png = Image.getInstance(baos.toByteArray());
					png.scalePercent(70.0F, 70.0F);
					document.add(png);
					
					Table t = new Table(2);
					t.setBorderWidth(1.0F);
					t.setBorderColor(new Color(79, 129, 189));
					t.setPadding(5.0F);

					t.setWidth(100.0F);
					t.setWidths(new int[] { 30 , 15 });

					chunk = new Chunk("系统名称");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					Cell cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);

					chunk = new Chunk("符合度");
					chunk.setFont(new Font(songFont, normalFive.floatValue(), 1));
					cell = new Cell(chunk);
					cell.setHorizontalAlignment(1);
					cell.setVerticalAlignment(5);
					cell.setBorderColor(new Color(79, 129, 189));
					cell.setBorderColorLeft(new Color(0, 0, 0));
					cell.setBorderColorRight(new Color(0, 0, 0));
					cell.setBorderWidthRight(0.0F);
					cell.setBorderWidthLeft(0.0F);
					cell.setBorderWidthBottom(1.0F);
					cell.setBorderWidthTop(1.0F);
					t.addCell(cell);
					
					int ss = 0;
					for (Technology technology : technologyList) {
						ss++;
						chunk = new Chunk(technology.getName() + new SimpleDateFormat("yyyyMMddHHmmss").format(technology.getEndTime()));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ss % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
						
						DecimalFormat f = new DecimalFormat("#.##");
						chunk = new Chunk(String.valueOf(Double.parseDouble(technology.getDescribe())));
						chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						if (ss % 2 == 1)
							cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						t.addCell(cell);
					}
					
					document.add(t);
					
					document.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				request.setAttribute("stream", stream);
			}
		}
		return SUCCESS;
	}

	public AssessResultService getAssessResultService() {
		return assessResultService;
	}

	public void setAssessResultService(AssessResultService assessResultService) {
		this.assessResultService = assessResultService;
	}

	public DemandColletService getDemandColletService() {
		return demandColletService;
	}

	public void setDemandColletService(DemandColletService demandColletService) {
		this.demandColletService = demandColletService;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAcId() {
		return acId;
	}

	public void setAcId(String acId) {
		this.acId = acId;
	}

	public String getSysInfoId() {
		return sysInfoId;
	}

	public void setSysInfoId(String sysInfoId) {
		this.sysInfoId = sysInfoId;
	}

	public TechnologyService getTechnologyService() {
		return technologyService;
	}

	public void setTechnologyService(TechnologyService technologyService) {
		this.technologyService = technologyService;
	}

	public RankService getRankService() {
		return rankService;
	}

	public void setRankService(RankService rankService) {
		this.rankService = rankService;
	}

	public UnitInfoService getUnitInfoService() {
		return unitInfoService;
	}

	public void setUnitInfoService(UnitInfoService unitInfoService) {
		this.unitInfoService = unitInfoService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public SoftUseService getSoftUseService() {
		return softUseService;
	}

	public void setSoftUseService(SoftUseService softUseService) {
		this.softUseService = softUseService;
	}

	public DataAssetsService getDataAssetsService() {
		return dataAssetsService;
	}

	public void setDataAssetsService(DataAssetsService dataAssetsService) {
		this.dataAssetsService = dataAssetsService;
	}

	public CompAssetsService getCompAssetsService() {
		return compAssetsService;
	}

	public void setCompAssetsService(CompAssetsService compAssetsService) {
		this.compAssetsService = compAssetsService;
	}

	public DevAssetsService getDevAssetsService() {
		return devAssetsService;
	}

	public void setDevAssetsService(DevAssetsService devAssetsService) {
		this.devAssetsService = devAssetsService;
	}

	public NetAssetsService getNetAssetsService() {
		return netAssetsService;
	}

	public void setNetAssetsService(NetAssetsService netAssetsService) {
		this.netAssetsService = netAssetsService;
	}

	public EmpAssetsService getEmpAssetsService() {
		return empAssetsService;
	}

	public void setEmpAssetsService(EmpAssetsService empAssetsService) {
		this.empAssetsService = empAssetsService;
	}

	public DocAssetsService getDocAssetsService() {
		return docAssetsService;
	}

	public void setDocAssetsService(DocAssetsService docAssetsService) {
		this.docAssetsService = docAssetsService;
	}

/*	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}*/
	
	

}
