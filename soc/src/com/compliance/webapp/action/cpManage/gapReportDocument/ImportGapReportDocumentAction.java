package com.compliance.webapp.action.cpManage.gapReportDocument;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.compliance.model.cpManage.gapReportDocument.ReportDocument;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnit;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnitBianJie;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnitFuWu;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnitShuJu;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnitYingYong;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnitZhongDuan;
import com.compliance.model.cpManage.generalPhysical.GeneralPhysical;
import com.compliance.model.cpManage.securityTable.SecurityTable;
import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.compliance.service.cpManage.gapReportDocument.GapReportDocumentService;
import com.compliance.service.cpManage.gapStatisticsUnit.GapStatisticsUnitService;
import com.compliance.service.cpManage.generalPhysical.GeneralPhysicalService;
import com.compliance.service.cpManage.securityTable.SecurityTableService;
import com.compliance.service.cpManage.summaryAnalysis.SecurityGapService;
import com.compliance.webapp.action.BaseAction;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
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
import com.util.DateUtil;
import com.util.load.Load;
import com.util.load.LoadWord;
import com.util.load.importWord.MakeJFreeChartUtil;

public class ImportGapReportDocumentAction extends BaseAction {
	static String urlpathstatic;
	static String urlfilename;
	private SecurityGapService securityGapService;

	private GapReportDocumentService gapReportDocumentService;

	// //表：信息系统总体符合度
	private GeneralPhysicalService generalPhysicalService;

	// 表：安全领域符合度
	private SecurityTableService securityTableService;

	// 表：差距分布图-----差距单元统计
	private GapStatisticsUnitService gapStatisticsUnitService;

	List<ProjectShowcase> listproject;

	// 表：安全领域符合度
	List<SecurityTable> listSecurityTable = new ArrayList<SecurityTable>();
	List<ReportDocument> listReportDocument = new ArrayList<ReportDocument>();
	List<GeneralPhysical> listGeneralPhysical = new ArrayList<GeneralPhysical>();
	// 表：差距分布图-----差距单元统计
	List<GapStatisticsUnit> listGapStatisticsUnit = new ArrayList<GapStatisticsUnit>();
	List<GapStatisticsUnitBianJie> listGapStatisticsUnitBianJie = new ArrayList<GapStatisticsUnitBianJie>();
	List<GapStatisticsUnitZhongDuan> listGapStatisticsUnitZhongDuan = new ArrayList<GapStatisticsUnitZhongDuan>();
	List<GapStatisticsUnitFuWu> listGapStatisticsUnitFuWu = new ArrayList<GapStatisticsUnitFuWu>();
	List<GapStatisticsUnitYingYong> listGapStatisticsUnitYingYong = new ArrayList<GapStatisticsUnitYingYong>();
	List<GapStatisticsUnitShuJu> listGapStatisticsUnitShuJu = new ArrayList<GapStatisticsUnitShuJu>();
	
	/**
	 * 审计业务接口
	 */
	/*public AuditService auditService;
	*/
	
	

//	public AuditService getAuditService() {
//		return auditService;
//	}
//
//	public void setAuditService(AuditService auditService) {
//		this.auditService = auditService;
//	}

	public SecurityGapService getSecurityGapService() {
		return securityGapService;
	}

	public void setSecurityGapService(SecurityGapService securityGapService) {
		this.securityGapService = securityGapService;
	}

	public GapReportDocumentService getGapReportDocumentService() {
		return gapReportDocumentService;
	}

	public void setGapReportDocumentService(
			GapReportDocumentService gapReportDocumentService) {
		this.gapReportDocumentService = gapReportDocumentService;
	}

	public GeneralPhysicalService getGeneralPhysicalService() {
		return generalPhysicalService;
	}

	public void setGeneralPhysicalService(
			GeneralPhysicalService generalPhysicalService) {
		this.generalPhysicalService = generalPhysicalService;
	}

	public SecurityTableService getSecurityTableService() {
		return securityTableService;
	}

	public void setSecurityTableService(
			SecurityTableService securityTableService) {
		this.securityTableService = securityTableService;
	}

	public GapStatisticsUnitService getGapStatisticsUnitService() {
		return gapStatisticsUnitService;
	}

	public void setGapStatisticsUnitService(
			GapStatisticsUnitService gapStatisticsUnitService) {
		this.gapStatisticsUnitService = gapStatisticsUnitService;
	}

	public List<ProjectShowcase> getListproject() {
		return listproject;
	}

	public void setListproject(List<ProjectShowcase> listproject) {
		this.listproject = listproject;
	}

	public List<SecurityTable> getListSecurityTable() {
		return listSecurityTable;
	}

	public void setListSecurityTable(List<SecurityTable> listSecurityTable) {
		this.listSecurityTable = listSecurityTable;
	}

	public List<ReportDocument> getListReportDocument() {
		return listReportDocument;
	}

	public void setListReportDocument(List<ReportDocument> listReportDocument) {
		this.listReportDocument = listReportDocument;
	}

	public List<GeneralPhysical> getListGeneralPhysical() {
		return listGeneralPhysical;
	}

	public void setListGeneralPhysical(List<GeneralPhysical> listGeneralPhysical) {
		this.listGeneralPhysical = listGeneralPhysical;
	}

	public List<GapStatisticsUnit> getListGapStatisticsUnit() {
		return listGapStatisticsUnit;
	}

	public void setListGapStatisticsUnit(
			List<GapStatisticsUnit> listGapStatisticsUnit) {
		this.listGapStatisticsUnit = listGapStatisticsUnit;
	}

	public List<GapStatisticsUnitBianJie> getListGapStatisticsUnitBianJie() {
		return listGapStatisticsUnitBianJie;
	}

	public void setListGapStatisticsUnitBianJie(
			List<GapStatisticsUnitBianJie> listGapStatisticsUnitBianJie) {
		this.listGapStatisticsUnitBianJie = listGapStatisticsUnitBianJie;
	}

	public List<GapStatisticsUnitZhongDuan> getListGapStatisticsUnitZhongDuan() {
		return listGapStatisticsUnitZhongDuan;
	}

	public void setListGapStatisticsUnitZhongDuan(
			List<GapStatisticsUnitZhongDuan> listGapStatisticsUnitZhongDuan) {
		this.listGapStatisticsUnitZhongDuan = listGapStatisticsUnitZhongDuan;
	}

	public List<GapStatisticsUnitFuWu> getListGapStatisticsUnitFuWu() {
		return listGapStatisticsUnitFuWu;
	}

	public void setListGapStatisticsUnitFuWu(
			List<GapStatisticsUnitFuWu> listGapStatisticsUnitFuWu) {
		this.listGapStatisticsUnitFuWu = listGapStatisticsUnitFuWu;
	}

	public List<GapStatisticsUnitYingYong> getListGapStatisticsUnitYingYong() {
		return listGapStatisticsUnitYingYong;
	}

	public void setListGapStatisticsUnitYingYong(
			List<GapStatisticsUnitYingYong> listGapStatisticsUnitYingYong) {
		this.listGapStatisticsUnitYingYong = listGapStatisticsUnitYingYong;
	}

	public List<GapStatisticsUnitShuJu> getListGapStatisticsUnitShuJu() {
		return listGapStatisticsUnitShuJu;
	}

	public void setListGapStatisticsUnitShuJu(
			List<GapStatisticsUnitShuJu> listGapStatisticsUnitShuJu) {
		this.listGapStatisticsUnitShuJu = listGapStatisticsUnitShuJu;
	}

	public String getSafeField() throws Exception {
		DecimalFormat df1 = new DecimalFormat("00.00");
		HttpServletRequest request = super.getRequest();
		listproject = securityGapService.getProjectShowcase();
		request.setAttribute("listproject", listproject);

		for (ProjectShowcase projectShowcase : listproject) {
			int pkca = projectShowcase.getPkca();

			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list2 = gapReportDocumentService
						.getGaplineAndSortTwo(pkca);
				ReportDocument reportDocument = new ReportDocument();
				reportDocument.setSysname(projectShowcase.getCaName());
				reportDocument.setJichu(list2.get(0));
				reportDocument.setBianjie(list2.get(1));
				reportDocument.setZhongduan(list2.get(2));
				reportDocument.setFuwu(list2.get(3));
				reportDocument.setYingyong(list2.get(4));
				reportDocument.setShuju(list2.get(5));
				reportDocument.setAnquan(list2.get(6));
				reportDocument.setTywuli(generalPhysicalService
						.getTongYongWuLi());
				reportDocument.setTyguanli(generalPhysicalService
						.getTongYongGuanLi());
				int tywuli = generalPhysicalService.getTongYongWuLi();
				int tyguanli = generalPhysicalService.getTongYongGuanLi();
				int sum = list2.get(0) + list2.get(1) + list2.get(2)
						+ list2.get(3) + list2.get(4) + list2.get(5)
						+ list2.get(6) + tywuli + tyguanli;
				reportDocument.setSum(sum);
				if (reportDocument != null) {
					listReportDocument.add(reportDocument);
				}

				SecurityTable securityTable = new SecurityTable();
				List<Integer> stSum = securityTableService
						.getSecurityTableServiceTwo(pkca);
				securityTable.setSysname(projectShowcase.getCaName());
				double stPercentage0 = ((double) stSum.get(0) / 30) * 100;
				securityTable.setJichuPertage(df1.format(stPercentage0));
				double stPercentage1 = ((double) stSum.get(1) / 30) * 100;
				securityTable.setBianjiePertage(df1.format(stPercentage1));
				double stPercentage2 = ((double) stSum.get(2) / 30) * 100;
				securityTable.setZhongduanPertage(df1.format(stPercentage2));
				double stPercentage3 = ((double) stSum.get(3) / 30) * 100;
				securityTable.setFuwuduanPertage(df1.format(stPercentage3));
				double stPercentage4 = ((double) stSum.get(4) / 30) * 100;
				securityTable.setYingyongPertage(df1.format(stPercentage4));
				double stPercentage5 = ((double) stSum.get(5) / 30) * 100;
				securityTable.setShujuPertage(df1.format(stPercentage5));
				double stPercentage6 = ((double) stSum.get(6) / 30) * 100;
				securityTable.setAnquanPertage(df1.format(stPercentage6));

				int gpsum = generalPhysicalService.getGeneralPhysicalCount();
				double gpPercentage = ((double) gpsum / 6) * 100;
				securityTable.setTwuliPertage(df1.format(gpPercentage));

				int mcsum = generalPhysicalService.getManagementCount();
				double mcPercentage = ((double) mcsum / 33) * 100;
				securityTable.setTguanliPertage(df1.format(mcPercentage));

				listSecurityTable.add(securityTable);

				GapStatisticsUnit gapStatisticsUnit = new GapStatisticsUnit();
				int gsnum0 = gapStatisticsUnitService.getGapStatisticsUnitTwo0(
						pkca).get(0);
				int gsnum1 = gapStatisticsUnitService.getGapStatisticsUnitTwo1(
						pkca).get(0);
				int gsnum2 = gapStatisticsUnitService.getGapStatisticsUnitTwo2(
						pkca).get(0);
				int gsnum1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1And2(pkca).get(0);
				gapStatisticsUnit.setJichu("基础网络安全");
				gapStatisticsUnit.setSysname(projectShowcase.getCaName());
				gapStatisticsUnit
						.setCasysGrade(projectShowcase.getCasysGrade());
				gapStatisticsUnit.setBianzhun(3);
				gapStatisticsUnit.setShiyong(3);
				gapStatisticsUnit.setFuhe(gsnum0);
				gapStatisticsUnit.setBufenfuhe(gsnum1);
				gapStatisticsUnit.setBufuhe(gsnum2);
				gapStatisticsUnit.setChaju(gsnum1and2);
				double gsuPercentage0 = ((double) gsnum0 / 3) * 100;
				gapStatisticsUnit.setFuhedu(df1.format(gsuPercentage0));

				int gsnumbianjie0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(1);
				int gsnumbianjie1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(1);
				int gsnumbianjie2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(1);
				int gsnumbianjie1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1And2(pkca).get(1);
				gapStatisticsUnit.setBianji("边界安全");
				gapStatisticsUnit.setBianzhunbianji(6);
				gapStatisticsUnit.setShiyongbianji(6);
				gapStatisticsUnit.setFuhebianji(gsnumbianjie0);
				gapStatisticsUnit.setBufenfuhebianji(gsnumbianjie1);
				gapStatisticsUnit.setBufuhebianji(gsnumbianjie2);
				gapStatisticsUnit.setChajubianji(gsnumbianjie1and2);
				double gsuPercentage1 = ((double) gsnumbianjie0 / 6) * 100;
				gapStatisticsUnit.setFuhedubianji(df1.format(gsuPercentage1));

				int gsnumzhongduan0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(2);
				int gsnumzhongduan1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(2);
				int gsnumzhongduan2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(2);
				int gsnumzhongduan1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1And2(pkca).get(2);
				gapStatisticsUnit.setZhongduan("终端系统安全");
				gapStatisticsUnit.setBianzhunzhongduan(4);
				gapStatisticsUnit.setShiyongzhongduan(4);
				gapStatisticsUnit.setFuhezhongduan(gsnumzhongduan0);
				gapStatisticsUnit.setBufenfuhezhongduan(gsnumzhongduan1);
				gapStatisticsUnit.setBufuhezhongduan(gsnumzhongduan2);
				gapStatisticsUnit.setChajuzhongduan(gsnumzhongduan1and2);
				double gsuPercentagezhongduan = ((double) gsnumzhongduan0 / 4) * 100;
				gapStatisticsUnit.setFuheduzhongduan(df1
						.format(gsuPercentagezhongduan));

				int gsnumfuwu0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(3);
				int gsnumfuwu1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(3);
				int gsnumfuwu2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(3);
				int gsnumfuwu1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1And2(pkca).get(3);
				gapStatisticsUnit.setFuwu("服务端系统安全");
				gapStatisticsUnit.setBianzhunfuwu(7);
				gapStatisticsUnit.setShiyongfuwu(7);
				gapStatisticsUnit.setFuhefuwu(gsnumfuwu0);
				gapStatisticsUnit.setBufenfuhefuwu(gsnumfuwu1);
				gapStatisticsUnit.setBufuhefuwu(gsnumfuwu2);
				gapStatisticsUnit.setChajufuwu(gsnumfuwu1and2);
				double gsuPercentagefuwu = ((double) gsnumfuwu0 / 7) * 100;
				gapStatisticsUnit.setFuhedufuwu(df1.format(gsuPercentagefuwu));

				int gsnumyingyong0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(4);
				int gsnumyingyong1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(4);
				int gsnumyingyong2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(4);
				int gsnumyingyong1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1And2(pkca).get(4);
				gapStatisticsUnit.setYingyong("应用安全");
				gapStatisticsUnit.setBianzhunyingyong(7);
				gapStatisticsUnit.setShiyongyingyong(7);
				gapStatisticsUnit.setFuheyingyong(gsnumyingyong0);
				gapStatisticsUnit.setBufenfuheyingyong(gsnumyingyong1);
				gapStatisticsUnit.setBufuheyingyong(gsnumyingyong2);
				gapStatisticsUnit.setChajuyingyong(gsnumyingyong1and2);
				double gsuPercentageyingyong = ((double) gsnumyingyong0 / 7) * 100;
				gapStatisticsUnit.setFuheduyingyong(df1
						.format(gsuPercentageyingyong));

				int gsnumshuju0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(5);
				int gsnumshuju1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(5);
				int gsnumshuju2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(5);
				int gsnumshuju1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1And2(pkca).get(5);
				gapStatisticsUnit.setShuju("数据安全域备份恢复");
				gapStatisticsUnit.setBianzhunshuju(3);
				gapStatisticsUnit.setShiyongshuju(3);
				gapStatisticsUnit.setFuheshuju(gsnumshuju0);
				gapStatisticsUnit.setBufenfuheshuju(gsnumshuju1);
				gapStatisticsUnit.setBufuheshuju(gsnumshuju2);
				gapStatisticsUnit.setChajushuju(gsnumshuju1and2);
				double gsuPercentageshuju = ((double) gsnumshuju0 / 3) * 100;
				gapStatisticsUnit
						.setFuhedushuju(df1.format(gsuPercentageshuju));

				gapStatisticsUnit.setAnquan("安全管理中心");
				gapStatisticsUnit.setBianzhunanquan(0);
				gapStatisticsUnit.setShiyonganquan(0);
				gapStatisticsUnit.setFuheanquan(0);
				gapStatisticsUnit.setBufenfuheanquan(0);
				gapStatisticsUnit.setBufuheanquan(0);
				gapStatisticsUnit.setChajuanquan(0);
				double gsuPercentageanquan = ((double) 0 / 3) * 100;
				gapStatisticsUnit.setFuheduanquan(df1
						.format(gsuPercentageanquan));

				int wulifuhenum = generalPhysicalService.getTyWuliFuHeNum();
				int wulibufenfuhenum = generalPhysicalService
						.getTyWuliBufenFuHeNum();
				int wulibufuhenum = generalPhysicalService.getTyWuliBuFuHeNum();
				int wulichajunum = generalPhysicalService.getTyWuliChaJuNum();
				double gsuPercentagetywuli = ((double) wulifuhenum / 6) * 100;

				gapStatisticsUnit.setTywuli("通用物理安全");
				gapStatisticsUnit.setBianzhuntywuli(6);
				gapStatisticsUnit.setShiyongtywuli(6);
				gapStatisticsUnit.setFuhetywuli(wulifuhenum);
				gapStatisticsUnit.setBufenfuhetywuli(wulibufenfuhenum);
				gapStatisticsUnit.setBufuhetywuli(wulibufuhenum);
				gapStatisticsUnit.setChajutywuli(wulichajunum);
				gapStatisticsUnit.setFuhedutywuli(df1
						.format(gsuPercentagetywuli));

				int tyguanli0 = generalPhysicalService.getTyGuanLiFuHeNum();
				int tyguanli1 = generalPhysicalService.getTyGuanLiFuFenHeNum();
				int tyguanli2 = generalPhysicalService.getTyGuanLiBuFuHeNum();
				int tyguanli0and1 = generalPhysicalService
						.getTyGuanLiChaJuNum();
				double gsuPercentagettyguan = ((double) tyguanli0 / 5) * 100;
				gapStatisticsUnit.setTyguanli("通用管理安全");
				gapStatisticsUnit.setBianzhuntyguanli(5);
				gapStatisticsUnit.setShiyongtyguanli(5);
				gapStatisticsUnit.setFuhetyguanli(tyguanli0);
				gapStatisticsUnit.setBufenfuhetyguanli(tyguanli1);
				gapStatisticsUnit.setBufuhetyguanli(tyguanli2);
				gapStatisticsUnit.setChajutyguanli(tyguanli0and1);
				gapStatisticsUnit.setFuhedutyguanli(df1
						.format(gsuPercentagettyguan));

				int fuhesum = gapStatisticsUnit.getFuhe()
						+ gapStatisticsUnit.getFuhebianji()
						+ gapStatisticsUnit.getFuhezhongduan()
						+ gapStatisticsUnit.getFuhefuwu()
						+ gapStatisticsUnit.getFuheshuju()
						+ gapStatisticsUnit.getFuheanquan()
						+ gapStatisticsUnit.getFuheyingyong()
						+ gapStatisticsUnit.getFuhetywuli()
						+ gapStatisticsUnit.getFuhetyguanli();
				gapStatisticsUnit.setFuhesum(fuhesum);

				int bufenfuhesum = gapStatisticsUnit.getBufenfuhe()
						+ gapStatisticsUnit.getBufenfuhebianji()
						+ gapStatisticsUnit.getBufenfuhezhongduan()
						+ gapStatisticsUnit.getBufenfuhefuwu()
						+ gapStatisticsUnit.getBufenfuheshuju()
						+ gapStatisticsUnit.getBufenfuheanquan()
						+ gapStatisticsUnit.getBufenfuheyingyong()
						+ gapStatisticsUnit.getBufenfuhetyguanli()
						+ gapStatisticsUnit.getBufenfuhetywuli();
				gapStatisticsUnit.setBufenfuhesum(bufenfuhesum);

				int bufuhesum = gapStatisticsUnit.getBufuhe()
						+ gapStatisticsUnit.getBufuhebianji()
						+ gapStatisticsUnit.getBufuhezhongduan()
						+ gapStatisticsUnit.getBufuhefuwu()
						+ gapStatisticsUnit.getBufuheshuju()
						+ gapStatisticsUnit.getBufuheanquan()
						+ gapStatisticsUnit.getBufuheyingyong()
						+ gapStatisticsUnit.getBufuhetyguanli()
						+ gapStatisticsUnit.getBufuhetywuli();
				gapStatisticsUnit.setBufuhesum(bufuhesum);
				listGapStatisticsUnit.add(gapStatisticsUnit);

			}

			if (projectShowcase.getCasysGrade().equals("第三级")) {
				List<Integer> list3 = gapReportDocumentService
						.getGaplineAndSortThree(pkca);

				ReportDocument reportDocument3 = new ReportDocument();
				reportDocument3.setSysname(projectShowcase.getCaName());
				reportDocument3.setJichu(list3.get(0));
				reportDocument3.setBianjie(list3.get(1));
				reportDocument3.setZhongduan(list3.get(2));
				reportDocument3.setFuwu(list3.get(3));
				reportDocument3.setYingyong(list3.get(4));
				reportDocument3.setShuju(list3.get(5));
				reportDocument3.setAnquan(list3.get(6));

				reportDocument3.setTywuli(generalPhysicalService
						.getTongYongWuLi());
				reportDocument3.setTyguanli(generalPhysicalService
						.getTongYongGuanLi());
				int tywuli3 = generalPhysicalService.getTongYongWuLi();
				int tyguanli3 = generalPhysicalService.getTongYongGuanLi();

				int sum3 = list3.get(0) + list3.get(1) + list3.get(2)
						+ list3.get(3) + list3.get(4) + list3.get(5)
						+ list3.get(6) + tywuli3 + tyguanli3;
				reportDocument3.setSum(sum3);
				if (reportDocument3 != null) {
					listReportDocument.add(reportDocument3);
				}

				SecurityTable securityTable3 = new SecurityTable();
				List<Integer> stSum3 = securityTableService
						.getSecurityTableServiceThree(pkca);
				securityTable3.setSysname(projectShowcase.getCaName());
				double stPercentage0 = ((double) stSum3.get(0) / 34) * 100;
				securityTable3.setJichuPertage(df1.format(stPercentage0));
				double stPercentage1 = ((double) stSum3.get(1) / 34) * 100;
				securityTable3.setBianjiePertage(df1.format(stPercentage1));
				double stPercentage2 = ((double) stSum3.get(2) / 34) * 100;
				securityTable3.setZhongduanPertage(df1.format(stPercentage2));
				double stPercentage3 = ((double) stSum3.get(3) / 34) * 100;
				securityTable3.setFuwuduanPertage(df1.format(stPercentage3));
				double stPercentage4 = ((double) stSum3.get(4) / 34) * 100;
				securityTable3.setYingyongPertage(df1.format(stPercentage4));
				double stPercentage5 = ((double) stSum3.get(5) / 34) * 100;
				securityTable3.setShujuPertage(df1.format(stPercentage5));
				double stPercentage6 = ((double) stSum3.get(6) / 34) * 100;
				securityTable3.setAnquanPertage(df1.format(stPercentage6));

				int gpsum = generalPhysicalService.getGeneralPhysicalCount();
				double gpPercentage = ((double) gpsum / 6) * 100;
				securityTable3.setTwuliPertage(df1.format(gpPercentage));

				int mcsum = generalPhysicalService.getManagementCount();
				double mcPercentage = ((double) mcsum / 33) * 100;
				securityTable3.setTguanliPertage(df1.format(mcPercentage));
				listSecurityTable.add(securityTable3);

				// ////////////////////- 增加了 安全管理中心 通用物理安全
				// 通用管理安全------------------------------------

				GapStatisticsUnit gapStatisticsUnit = new GapStatisticsUnit();
				int gsnum0 = gapStatisticsUnitService
						.getGapStatisticsUnitThree0(pkca).get(0);
				int gsnum1 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1(pkca).get(0);
				int gsnum2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree2(pkca).get(0);
				int gsnum1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(0);
				gapStatisticsUnit.setJichu("基础网络安全");
				gapStatisticsUnit.setSysname(projectShowcase.getCaName());
				gapStatisticsUnit
						.setCasysGrade(projectShowcase.getCasysGrade());
				gapStatisticsUnit.setBianzhun(3);
				gapStatisticsUnit.setShiyong(3);
				gapStatisticsUnit.setFuhe(gsnum0);
				gapStatisticsUnit.setBufenfuhe(gsnum1);
				gapStatisticsUnit.setBufuhe(gsnum2);
				gapStatisticsUnit.setChaju(gsnum1and2);
				double gsuPercentage0 = ((double) gsnum0 / 3) * 100;
				gapStatisticsUnit.setFuhedu(df1.format(gsuPercentage0));

				int gsnumbianjie0 = gapStatisticsUnitService
						.getGapStatisticsUnitThree0(pkca).get(1);
				int gsnumbianjie1 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1(pkca).get(1);
				int gsnumbianjie2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree2(pkca).get(1);
				int gsnumbianjie1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(1);
				gapStatisticsUnit.setBianji("边界安全");
				gapStatisticsUnit.setBianzhunbianji(6);
				gapStatisticsUnit.setShiyongbianji(6);
				gapStatisticsUnit.setFuhebianji(gsnumbianjie0);
				gapStatisticsUnit.setBufenfuhebianji(gsnumbianjie1);
				gapStatisticsUnit.setBufuhebianji(gsnumbianjie2);
				gapStatisticsUnit.setChajubianji(gsnumbianjie1and2);
				double gsuPercentage1 = ((double) gsnumbianjie0 / 6) * 100;
				gapStatisticsUnit.setFuhedubianji(df1.format(gsuPercentage1));

				int gsnumzhongduan0 = gapStatisticsUnitService
						.getGapStatisticsUnitThree0(pkca).get(2);
				int gsnumzhongduan1 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1(pkca).get(2);
				int gsnumzhongduan2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree2(pkca).get(2);
				int gsnumzhongduan1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(2);
				gapStatisticsUnit.setZhongduan("终端系统安全");
				gapStatisticsUnit.setBianzhunzhongduan(5);
				gapStatisticsUnit.setShiyongzhongduan(5);
				gapStatisticsUnit.setFuhezhongduan(gsnumzhongduan0);
				gapStatisticsUnit.setBufenfuhezhongduan(gsnumzhongduan1);
				gapStatisticsUnit.setBufuhezhongduan(gsnumzhongduan2);
				gapStatisticsUnit.setChajuzhongduan(gsnumzhongduan1and2);
				double gsuPercentagezhongduan = ((double) gsnumzhongduan0 / 5) * 100;
				gapStatisticsUnit.setFuheduzhongduan(df1
						.format(gsuPercentagezhongduan));

				int gsnumfuwu0 = gapStatisticsUnitService
						.getGapStatisticsUnitThree0(pkca).get(3);
				int gsnumfuwu1 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1(pkca).get(3);
				int gsnumfuwu2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree2(pkca).get(3);
				int gsnumfuwu1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(3);
				gapStatisticsUnit.setFuwu("服务端系统安全");
				gapStatisticsUnit.setBianzhunfuwu(7);
				gapStatisticsUnit.setShiyongfuwu(7);
				gapStatisticsUnit.setFuhefuwu(gsnumfuwu0);
				gapStatisticsUnit.setBufenfuhefuwu(gsnumfuwu1);
				gapStatisticsUnit.setBufuhefuwu(gsnumfuwu2);
				gapStatisticsUnit.setChajufuwu(gsnumfuwu1and2);
				double gsuPercentagefuwu = ((double) gsnumfuwu0 / 7) * 100;
				gapStatisticsUnit.setFuhedufuwu(df1.format(gsuPercentagefuwu));

				int gsnumyingyong0 = gapStatisticsUnitService
						.getGapStatisticsUnitThree0(pkca).get(4);
				int gsnumyingyong1 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1(pkca).get(4);
				int gsnumyingyong2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree2(pkca).get(4);
				int gsnumyingyong1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(4);
				gapStatisticsUnit.setYingyong("应用安全");
				gapStatisticsUnit.setBianzhunyingyong(7);
				gapStatisticsUnit.setShiyongyingyong(7);
				gapStatisticsUnit.setFuheyingyong(gsnumyingyong0);
				gapStatisticsUnit.setBufenfuheyingyong(gsnumyingyong1);
				gapStatisticsUnit.setBufuheyingyong(gsnumyingyong2);
				gapStatisticsUnit.setChajuyingyong(gsnumyingyong1and2);
				double gsuPercentageyingyong = ((double) gsnumyingyong0 / 7) * 100;
				gapStatisticsUnit.setFuheduyingyong(df1
						.format(gsuPercentageyingyong));

				int gsnumshuju0 = gapStatisticsUnitService
						.getGapStatisticsUnitThree0(pkca).get(5);
				int gsnumshuju1 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1(pkca).get(5);
				int gsnumshuju2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree2(pkca).get(5);
				int gsnumshuju1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(5);
				gapStatisticsUnit.setShuju("数据安全域备份恢复");
				gapStatisticsUnit.setBianzhunshuju(3);
				gapStatisticsUnit.setShiyongshuju(3);
				gapStatisticsUnit.setFuheshuju(gsnumshuju0);
				gapStatisticsUnit.setBufenfuheshuju(gsnumshuju1);
				gapStatisticsUnit.setBufuheshuju(gsnumshuju2);
				gapStatisticsUnit.setChajushuju(gsnumshuju1and2);
				double gsuPercentageshuju = ((double) gsnumshuju0 / 3) * 100;
				gapStatisticsUnit
						.setFuhedushuju(df1.format(gsuPercentageshuju));

				int gsnumanquan0 = gapStatisticsUnitService
						.getGapStatisticsUnitThree0(pkca).get(6);
				int gsnumanquan1 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1(pkca).get(6);
				int gsnumanquan2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree2(pkca).get(6);
				int gsnumanquan1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(6);
				gapStatisticsUnit.setAnquan("安全管理中心");
				gapStatisticsUnit.setBianzhunanquan(3);
				gapStatisticsUnit.setShiyonganquan(3);
				gapStatisticsUnit.setFuheanquan(gsnumanquan0);
				gapStatisticsUnit.setBufenfuheanquan(gsnumanquan1);
				gapStatisticsUnit.setBufuheanquan(gsnumanquan2);
				gapStatisticsUnit.setChajuanquan(gsnumanquan1and2);
				double gsuPercentageanquan = ((double) gsnumanquan0 / 3) * 100;
				gapStatisticsUnit.setFuheduanquan(df1
						.format(gsuPercentageanquan));

				int wulifuhenum = generalPhysicalService.getTyWuliFuHeNum();
				int wulibufenfuhenum = generalPhysicalService
						.getTyWuliBufenFuHeNum();
				int wulibufuhenum = generalPhysicalService.getTyWuliBuFuHeNum();
				int wulichajunum = generalPhysicalService.getTyWuliChaJuNum();
				double gsuPercentagetywuli = ((double) wulifuhenum / 6) * 100;

				gapStatisticsUnit.setTywuli("通用物理安全");
				gapStatisticsUnit.setBianzhuntywuli(6);
				gapStatisticsUnit.setShiyongtywuli(6);
				gapStatisticsUnit.setFuhetywuli(wulifuhenum);
				gapStatisticsUnit.setBufenfuhetywuli(wulibufenfuhenum);
				gapStatisticsUnit.setBufuhetywuli(wulibufuhenum);
				gapStatisticsUnit.setChajutywuli(wulichajunum);
				gapStatisticsUnit.setFuhedutywuli(df1
						.format(gsuPercentagetywuli));

				int tyguanli0 = generalPhysicalService.getTyGuanLiFuHeNum();
				int tyguanli1 = generalPhysicalService.getTyGuanLiFuFenHeNum();
				int tyguanli2 = generalPhysicalService.getTyGuanLiBuFuHeNum();
				int tyguanli0and1 = generalPhysicalService
						.getTyGuanLiChaJuNum();
				double gsuPercentagettyguan = ((double) tyguanli0 / 5) * 100;
				gapStatisticsUnit.setTyguanli("通用管理安全");
				gapStatisticsUnit.setBianzhuntyguanli(5);
				gapStatisticsUnit.setShiyongtyguanli(5);
				gapStatisticsUnit.setFuhetyguanli(tyguanli0);
				gapStatisticsUnit.setBufenfuhetyguanli(tyguanli1);
				gapStatisticsUnit.setBufuhetyguanli(tyguanli2);
				gapStatisticsUnit.setChajutyguanli(tyguanli0and1);
				gapStatisticsUnit.setFuhedutyguanli(df1
						.format(gsuPercentagettyguan));

				int fuhesum = gapStatisticsUnit.getFuhe()
						+ gapStatisticsUnit.getFuhebianji()
						+ gapStatisticsUnit.getFuhezhongduan()
						+ gapStatisticsUnit.getFuhefuwu()
						+ gapStatisticsUnit.getFuheshuju()
						+ gapStatisticsUnit.getFuheanquan()
						+ gapStatisticsUnit.getFuheyingyong()
						+ gapStatisticsUnit.getFuhetywuli()
						+ gapStatisticsUnit.getFuhetyguanli();
				gapStatisticsUnit.setFuhesum(fuhesum);

				int bufenfuhesum = gapStatisticsUnit.getBufenfuhe()
						+ gapStatisticsUnit.getBufenfuhebianji()
						+ gapStatisticsUnit.getBufenfuhezhongduan()
						+ gapStatisticsUnit.getBufenfuhefuwu()
						+ gapStatisticsUnit.getBufenfuheshuju()
						+ gapStatisticsUnit.getBufenfuheanquan()
						+ gapStatisticsUnit.getBufenfuheyingyong()
						+ gapStatisticsUnit.getBufenfuhetyguanli()
						+ gapStatisticsUnit.getBufenfuhetywuli();
				gapStatisticsUnit.setBufenfuhesum(bufenfuhesum);

				int bufuhesum = gapStatisticsUnit.getBufuhe()
						+ gapStatisticsUnit.getBufuhebianji()
						+ gapStatisticsUnit.getBufuhezhongduan()
						+ gapStatisticsUnit.getBufuhefuwu()
						+ gapStatisticsUnit.getBufuheshuju()
						+ gapStatisticsUnit.getBufuheanquan()
						+ gapStatisticsUnit.getBufuheyingyong()
						+ gapStatisticsUnit.getBufuhetyguanli()
						+ gapStatisticsUnit.getBufuhetywuli();
				gapStatisticsUnit.setBufuhesum(bufuhesum);

				listGapStatisticsUnit.add(gapStatisticsUnit);

				// //

			}
			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list4 = gapReportDocumentService
						.getGaplineAndSortFour(pkca);
				ReportDocument reportDocument4 = new ReportDocument();
				reportDocument4.setSysname(projectShowcase.getCaName());
				reportDocument4.setJichu(list4.get(0));
				reportDocument4.setBianjie(list4.get(1));
				reportDocument4.setZhongduan(list4.get(2));
				reportDocument4.setFuwu(list4.get(3));
				reportDocument4.setYingyong(list4.get(4));
				reportDocument4.setShuju(list4.get(5));
				reportDocument4.setAnquan(list4.get(6));

				reportDocument4.setTywuli(generalPhysicalService
						.getTongYongWuLi());
				reportDocument4.setTyguanli(generalPhysicalService
						.getTongYongGuanLi());
				int tywuli4 = generalPhysicalService.getTongYongWuLi();
				int tyguanli4 = generalPhysicalService.getTongYongGuanLi();
				int sum4 = list4.get(0) + list4.get(1) + list4.get(2)
						+ list4.get(3) + list4.get(4) + list4.get(5)
						+ list4.get(6) + tywuli4 + tyguanli4;
				reportDocument4.setSum(sum4);
				if (reportDocument4 != null) {
					listReportDocument.add(reportDocument4);
				}

				SecurityTable securityTable4 = new SecurityTable();
				List<Integer> stSum4 = securityTableService
						.getSecurityTableServiceFour(pkca);
				securityTable4.setSysname(projectShowcase.getCaName());
				double stPercentage0 = ((double) stSum4.get(0) / 34) * 100;
				securityTable4.setJichuPertage(df1.format(stPercentage0));
				double stPercentage1 = ((double) stSum4.get(1) / 34) * 100;
				securityTable4.setBianjiePertage(df1.format(stPercentage1));
				double stPercentage2 = ((double) stSum4.get(2) / 34) * 100;
				securityTable4.setZhongduanPertage(df1.format(stPercentage2));
				double stPercentage3 = ((double) stSum4.get(3) / 34) * 100;
				securityTable4.setFuwuduanPertage(df1.format(stPercentage3));
				double stPercentage4 = ((double) stSum4.get(4) / 34) * 100;
				securityTable4.setYingyongPertage(df1.format(stPercentage4));
				double stPercentage5 = ((double) stSum4.get(5) / 34) * 100;
				securityTable4.setShujuPertage(df1.format(stPercentage5));
				double stPercentage6 = ((double) stSum4.get(6) / 34) * 100;
				securityTable4.setAnquanPertage(df1.format(stPercentage6));

				int gpsum = generalPhysicalService.getGeneralPhysicalCount();
				double gpPercentage = ((double) gpsum / 6) * 100;
				securityTable4.setTwuliPertage(df1.format(gpPercentage));

				int mcsum = generalPhysicalService.getManagementCount();
				double mcPercentage = ((double) mcsum / 33) * 100;
				securityTable4.setTguanliPertage(df1.format(mcPercentage));
				listSecurityTable.add(securityTable4);

				// ////////////////////- 四级 增加了 安全管理中心 通用物理安全
				// 通用管理安全------------------------------------

				GapStatisticsUnit gapStatisticsUnit = new GapStatisticsUnit();
				int gsnum0 = gapStatisticsUnitService
						.getGapStatisticsUnitFour0(pkca).get(0);
				int gsnum1 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1(pkca).get(0);
				int gsnum2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour2(pkca).get(0);
				int gsnum1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1And2(pkca).get(0);
				gapStatisticsUnit.setJichu("基础网络安全");
				gapStatisticsUnit.setSysname(projectShowcase.getCaName());
				gapStatisticsUnit
						.setCasysGrade(projectShowcase.getCasysGrade());
				gapStatisticsUnit.setBianzhun(3);
				gapStatisticsUnit.setShiyong(3);
				gapStatisticsUnit.setFuhe(gsnum0);
				gapStatisticsUnit.setBufenfuhe(gsnum1);
				gapStatisticsUnit.setBufuhe(gsnum2);
				gapStatisticsUnit.setChaju(gsnum1and2);
				double gsuPercentage0 = ((double) gsnum0 / 3) * 100;
				gapStatisticsUnit.setFuhedu(df1.format(gsuPercentage0));

				int gsnumbianjie0 = gapStatisticsUnitService
						.getGapStatisticsUnitFour0(pkca).get(1);
				int gsnumbianjie1 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1(pkca).get(1);
				int gsnumbianjie2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour2(pkca).get(1);
				int gsnumbianjie1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1And2(pkca).get(1);
				gapStatisticsUnit.setBianji("边界安全");
				gapStatisticsUnit.setBianzhunbianji(5);
				gapStatisticsUnit.setShiyongbianji(5);
				gapStatisticsUnit.setFuhebianji(gsnumbianjie0);
				gapStatisticsUnit.setBufenfuhebianji(gsnumbianjie1);
				gapStatisticsUnit.setBufuhebianji(gsnumbianjie2);
				gapStatisticsUnit.setChajubianji(gsnumbianjie1and2);
				double gsuPercentage1 = ((double) gsnumbianjie0 / 5) * 100;
				gapStatisticsUnit.setFuhedubianji(df1.format(gsuPercentage1));

				int gsnumzhongduan0 = gapStatisticsUnitService
						.getGapStatisticsUnitFour0(pkca).get(2);
				int gsnumzhongduan1 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1(pkca).get(2);
				int gsnumzhongduan2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour2(pkca).get(2);
				int gsnumzhongduan1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1And2(pkca).get(2);
				gapStatisticsUnit.setZhongduan("终端系统安全");
				gapStatisticsUnit.setBianzhunzhongduan(5);
				gapStatisticsUnit.setShiyongzhongduan(5);
				gapStatisticsUnit.setFuhezhongduan(gsnumzhongduan0);
				gapStatisticsUnit.setBufenfuhezhongduan(gsnumzhongduan1);
				gapStatisticsUnit.setBufuhezhongduan(gsnumzhongduan2);
				gapStatisticsUnit.setChajuzhongduan(gsnumzhongduan1and2);
				double gsuPercentagezhongduan = ((double) gsnumzhongduan0 / 5) * 100;
				gapStatisticsUnit.setFuheduzhongduan(df1
						.format(gsuPercentagezhongduan));

				int gsnumfuwu0 = gapStatisticsUnitService
						.getGapStatisticsUnitFour0(pkca).get(3);
				int gsnumfuwu1 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1(pkca).get(3);
				int gsnumfuwu2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour2(pkca).get(3);
				int gsnumfuwu1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1And2(pkca).get(3);
				gapStatisticsUnit.setFuwu("服务端系统安全");
				gapStatisticsUnit.setBianzhunfuwu(8);
				gapStatisticsUnit.setShiyongfuwu(8);
				gapStatisticsUnit.setFuhefuwu(gsnumfuwu0);
				gapStatisticsUnit.setBufenfuhefuwu(gsnumfuwu1);
				gapStatisticsUnit.setBufuhefuwu(gsnumfuwu2);
				gapStatisticsUnit.setChajufuwu(gsnumfuwu1and2);
				double gsuPercentagefuwu = ((double) gsnumfuwu0 / 8) * 100;
				gapStatisticsUnit.setFuhedufuwu(df1.format(gsuPercentagefuwu));

				int gsnumyingyong0 = gapStatisticsUnitService
						.getGapStatisticsUnitFour0(pkca).get(4);
				int gsnumyingyong1 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1(pkca).get(4);
				int gsnumyingyong2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour2(pkca).get(4);
				int gsnumyingyong1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1And2(pkca).get(4);
				gapStatisticsUnit.setYingyong("应用安全");
				gapStatisticsUnit.setBianzhunyingyong(7);
				gapStatisticsUnit.setShiyongyingyong(7);
				gapStatisticsUnit.setFuheyingyong(gsnumyingyong0);
				gapStatisticsUnit.setBufenfuheyingyong(gsnumyingyong1);
				gapStatisticsUnit.setBufuheyingyong(gsnumyingyong2);
				gapStatisticsUnit.setChajuyingyong(gsnumyingyong1and2);
				double gsuPercentageyingyong = ((double) gsnumyingyong0 / 7) * 100;
				gapStatisticsUnit.setFuheduyingyong(df1
						.format(gsuPercentageyingyong));

				int gsnumshuju0 = gapStatisticsUnitService
						.getGapStatisticsUnitFour0(pkca).get(5);
				int gsnumshuju1 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1(pkca).get(5);
				int gsnumshuju2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour2(pkca).get(5);
				int gsnumshuju1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1And2(pkca).get(5);
				gapStatisticsUnit.setShuju("数据安全域备份恢复");
				gapStatisticsUnit.setBianzhunshuju(3);
				gapStatisticsUnit.setShiyongshuju(3);
				gapStatisticsUnit.setFuheshuju(gsnumshuju0);
				gapStatisticsUnit.setBufenfuheshuju(gsnumshuju1);
				gapStatisticsUnit.setBufuheshuju(gsnumshuju2);
				gapStatisticsUnit.setChajushuju(gsnumshuju1and2);
				double gsuPercentageshuju = ((double) gsnumshuju0 / 3) * 100;
				gapStatisticsUnit
						.setFuhedushuju(df1.format(gsuPercentageshuju));

				int gsnumanquan0 = gapStatisticsUnitService
						.getGapStatisticsUnitFour0(pkca).get(6);
				int gsnumanquan1 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1(pkca).get(6);
				int gsnumanquan2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour2(pkca).get(6);
				int gsnumanquan1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitFour1And2(pkca).get(6);
				gapStatisticsUnit.setAnquan("安全管理中心");
				gapStatisticsUnit.setBianzhunanquan(3);
				gapStatisticsUnit.setShiyonganquan(3);
				gapStatisticsUnit.setFuheanquan(gsnumanquan0);
				gapStatisticsUnit.setBufenfuheanquan(gsnumanquan1);
				gapStatisticsUnit.setBufuheanquan(gsnumanquan2);
				gapStatisticsUnit.setChajuanquan(gsnumanquan1and2);
				double gsuPercentageanquan = ((double) gsnumanquan0 / 3) * 100;
				gapStatisticsUnit.setFuheduanquan(df1
						.format(gsuPercentageanquan));

				int wulifuhenum = generalPhysicalService.getTyWuliFuHeNum();
				int wulibufenfuhenum = generalPhysicalService
						.getTyWuliBufenFuHeNum();
				int wulibufuhenum = generalPhysicalService.getTyWuliBuFuHeNum();
				int wulichajunum = generalPhysicalService.getTyWuliChaJuNum();
				double gsuPercentagetywuli = ((double) wulifuhenum / 6) * 100;

				gapStatisticsUnit.setTywuli("通用物理安全");
				gapStatisticsUnit.setBianzhuntywuli(6);
				gapStatisticsUnit.setShiyongtywuli(6);
				gapStatisticsUnit.setFuhetywuli(wulifuhenum);
				gapStatisticsUnit.setBufenfuhetywuli(wulibufenfuhenum);
				gapStatisticsUnit.setBufuhetywuli(wulibufuhenum);
				gapStatisticsUnit.setChajutywuli(wulichajunum);
				gapStatisticsUnit.setFuhedutywuli(df1
						.format(gsuPercentagetywuli));

				int tyguanli0 = generalPhysicalService.getTyGuanLiFuHeNum();
				int tyguanli1 = generalPhysicalService.getTyGuanLiFuFenHeNum();
				int tyguanli2 = generalPhysicalService.getTyGuanLiBuFuHeNum();
				int tyguanli0and1 = generalPhysicalService
						.getTyGuanLiChaJuNum();
				double gsuPercentagettyguan = ((double) tyguanli0 / 5) * 100;
				gapStatisticsUnit.setTyguanli("通用管理安全");
				gapStatisticsUnit.setBianzhuntyguanli(5);
				gapStatisticsUnit.setShiyongtyguanli(5);
				gapStatisticsUnit.setFuhetyguanli(tyguanli0);
				gapStatisticsUnit.setBufenfuhetyguanli(tyguanli1);
				gapStatisticsUnit.setBufuhetyguanli(tyguanli2);
				gapStatisticsUnit.setChajutyguanli(tyguanli0and1);
				gapStatisticsUnit.setFuhedutyguanli(df1
						.format(gsuPercentagettyguan));

				int fuhesum = gapStatisticsUnit.getFuhe()
						+ gapStatisticsUnit.getFuhebianji()
						+ gapStatisticsUnit.getFuhezhongduan()
						+ gapStatisticsUnit.getFuhefuwu()
						+ gapStatisticsUnit.getFuheshuju()
						+ gapStatisticsUnit.getFuheanquan()
						+ gapStatisticsUnit.getFuheyingyong()
						+ gapStatisticsUnit.getFuhetywuli()
						+ gapStatisticsUnit.getFuhetyguanli();
				gapStatisticsUnit.setFuhesum(fuhesum);

				int bufenfuhesum = gapStatisticsUnit.getBufenfuhe()
						+ gapStatisticsUnit.getBufenfuhebianji()
						+ gapStatisticsUnit.getBufenfuhezhongduan()
						+ gapStatisticsUnit.getBufenfuhefuwu()
						+ gapStatisticsUnit.getBufenfuheshuju()
						+ gapStatisticsUnit.getBufenfuheanquan()
						+ gapStatisticsUnit.getBufenfuheyingyong()
						+ gapStatisticsUnit.getBufenfuhetyguanli()
						+ gapStatisticsUnit.getBufenfuhetywuli();
				gapStatisticsUnit.setBufenfuhesum(bufenfuhesum);

				int bufuhesum = gapStatisticsUnit.getBufuhe()
						+ gapStatisticsUnit.getBufuhebianji()
						+ gapStatisticsUnit.getBufuhezhongduan()
						+ gapStatisticsUnit.getBufuhefuwu()
						+ gapStatisticsUnit.getBufuheshuju()
						+ gapStatisticsUnit.getBufuheanquan()
						+ gapStatisticsUnit.getBufuheyingyong()
						+ gapStatisticsUnit.getBufuhetyguanli()
						+ gapStatisticsUnit.getBufuhetywuli();
				gapStatisticsUnit.setBufuhesum(bufuhesum);

				listGapStatisticsUnit.add(gapStatisticsUnit);

				// //

			}
			request.setAttribute("listSecurityTable", listSecurityTable);
			int summ1 = 0;
			int summ2 = 0;
			int summ3 = 0;
			int summ4 = 0;
			int summ5 = 0;
			int summ6 = 0;
			int summ7 = 0;
			int summ8 = 0;
			for (int i = 0; i < listReportDocument.size(); i++) {
				summ1 += listReportDocument.get(i).getJichu();
				summ2 += listReportDocument.get(i).getBianjie();
				summ3 += listReportDocument.get(i).getZhongduan();
				summ4 += listReportDocument.get(i).getFuwu();
				summ5 += listReportDocument.get(i).getYingyong();
				summ6 += listReportDocument.get(i).getShuju();
				summ7 += listReportDocument.get(i).getAnquan();
				summ8 += listReportDocument.get(i).getSum();
			}
			int summSum = summ1 + summ2 + summ3 + summ4 + summ5 + summ6 + summ7
					+ summ8;
			request.setAttribute("summ1", summ1);
			request.setAttribute("summ2", summ2);
			request.setAttribute("summ3", summ3);
			request.setAttribute("summ4", summ4);
			request.setAttribute("summ5", summ5);
			request.setAttribute("summ6", summ6);
			request.setAttribute("summ7", summ7);
			request.setAttribute("summSum", summSum);
			request.setAttribute("listReportDocument", listReportDocument);

		}

		// 表：信息系统总体符合度

		for (ProjectShowcase JiShuprojectShowcase : listproject) {
			GeneralPhysical generalPhysical2 = new GeneralPhysical();
			if (JiShuprojectShowcase.getCasysGrade().equals("第二级")) {
				int twoJiShu = generalPhysicalService
						.getTechnologyCount(JiShuprojectShowcase.getPkca());
				double twoJiShuPercentage = ((double) twoJiShu / 30) * 100;
				generalPhysical2.setTechnologyPercentage(df1
						.format(twoJiShuPercentage));

				generalPhysical2.setSysgrade(JiShuprojectShowcase
						.getCasysGrade());
				generalPhysical2.setSysname(JiShuprojectShowcase.getCaName());

				int gpsum = generalPhysicalService.getGeneralPhysicalCount();
				double gpPercentage = ((double) gpsum / 6) * 100;
				generalPhysical2
						.setPhysicalPercentage(df1.format(gpPercentage));

				int mcsum = generalPhysicalService.getManagementCount();
				double mcPercentage = ((double) mcsum / 33) * 100;
				generalPhysical2.setManagementPercentage(df1
						.format(mcPercentage));

				int zongtiSum = twoJiShu + gpsum + mcsum;
				double zongtiPercentage = ((double) zongtiSum / 41) * 100;
				generalPhysical2.setTotalPercentage(df1
						.format(zongtiPercentage));
				listGeneralPhysical.add(generalPhysical2);

			}

			if (JiShuprojectShowcase.getCasysGrade().equals("第三级")) {

				GeneralPhysical generalPhysical3 = new GeneralPhysical();
				int threeJiShu = generalPhysicalService
						.getTechnologyCount(JiShuprojectShowcase.getPkca());
				double threeJiShuPercentage = ((double) threeJiShu / 34) * 100;
				generalPhysical3.setTechnologyPercentage(df1
						.format(threeJiShuPercentage));

				generalPhysical3.setSysgrade(JiShuprojectShowcase
						.getCasysGrade());
				generalPhysical3.setSysname(JiShuprojectShowcase.getCaName());

				int gpsum = generalPhysicalService.getGeneralPhysicalCount();
				double gpPercentage = ((double) gpsum / 6) * 100;
				generalPhysical3
						.setPhysicalPercentage(df1.format(gpPercentage));

				int mcsum = generalPhysicalService.getManagementCount();
				double mcPercentage = ((double) mcsum / 33) * 100;
				generalPhysical3.setManagementPercentage(df1
						.format(mcPercentage));

				int zongtisum3 = threeJiShu + gpsum + mcsum;
				double zongtisum3Percentage = ((double) zongtisum3 / 45) * 100;
				generalPhysical3.setTotalPercentage(df1
						.format(zongtisum3Percentage));
				listGeneralPhysical.add(generalPhysical3);
			}

			if (JiShuprojectShowcase.getCasysGrade().equals("第四级")) {
				GeneralPhysical generalPhysical4 = new GeneralPhysical();
				int fourJiShu = generalPhysicalService
						.getTechnologyCount(JiShuprojectShowcase.getPkca());
				double fourJiShuPercentage = ((double) fourJiShu / 34) * 100;
				generalPhysical4.setTechnologyPercentage(df1
						.format(fourJiShuPercentage));

				generalPhysical4.setSysgrade(JiShuprojectShowcase
						.getCasysGrade());
				generalPhysical4.setSysname(JiShuprojectShowcase.getCaName());
				int gpsum = generalPhysicalService.getGeneralPhysicalCount();
				double gpPercentage = ((double) gpsum / 6) * 100;
				generalPhysical4
						.setPhysicalPercentage(df1.format(gpPercentage));

				int mcsum = generalPhysicalService.getManagementCount();
				double mcPercentage = ((double) mcsum / 33) * 100;
				generalPhysical4.setManagementPercentage(df1
						.format(mcPercentage));

				int zongtisum4 = fourJiShu + gpsum + mcsum;
				double zongtisum4Percentage = ((double) zongtisum4 / 45) * 100;
				generalPhysical4.setTotalPercentage(df1
						.format(zongtisum4Percentage));
				listGeneralPhysical.add(generalPhysical4);
			}

		}
		request.setAttribute("listGapStatisticsUnit", listGapStatisticsUnit);
		request.setAttribute("listGapStatisticsUnitBianJie",
				listGapStatisticsUnitBianJie);
		request.setAttribute("listGapStatisticsUnitZhongDuan",
				listGapStatisticsUnitZhongDuan);
		request.setAttribute("listGapStatisticsUnitFuWu",
				listGapStatisticsUnitFuWu);
		request.setAttribute("listGapStatisticsUnitYingYong",
				listGapStatisticsUnitYingYong);
		request.setAttribute("listGapStatisticsUnitShuJu",
				listGapStatisticsUnitShuJu);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String ss = sdf.format(new Date());
		for (int i = 0; i < 2; i++) {
			ss += String.valueOf((int) (java.lang.Math.random() * 10));
		}

		String tomcatpath = System.getProperty("catalina.home");
		String uploadPath = tomcatpath + "/word/";
		File file = new File(uploadPath);

		if (file.isDirectory()) {
			boolean flag = true;
			// 删除文件夹下的所有文件(包括子目录)
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				// 删除子文件
				if (files[i].isFile()) {
					flag = deleteFile(files[i].getAbsolutePath());
					if (!flag)
						break;
				} // 删除子目录
				else {
					flag = deleteDirectory(files[i].getAbsolutePath());
					if (!flag)
						break;
				}
			}
		}
	/*	Audit audit=new Audit();
		audit.setDetailed("技术差距分析");
		audit.setName(super.getRequest().getSession().getAttribute("SSI_LOGIN_USER").toString());
		audit.setObject("差距分析报告");
		audit.setTime(DateUtil.curDateTimeStr19());
		audit.setType("导出文档");
		auditService.instert(audit);*/

		if (!file.isDirectory()) {
			file.mkdirs();
		}

		String str = "等级保护差距评估总体报告" + ss + ".doc";
		String strs = uploadPath + str;
		urlfilename = str;

		urlpathstatic = strs;
		openWordFile(strs);

		return SUCCESS;

	}

	public void openWordFile(String fileName) throws DocumentException,
			IOException {

		int xitongjichusum = 0;
		int xitongbianjisum = 0;
		int xitongzhongduansum = 0;
		int xitongfuwusum = 0;
		int xitongyingyongsum = 0;
		int xitongshujusum = 0;
		int xitonganquansum = 0;
		int xitongtywuli = 0;
		int xitongtyguanli = 0;
		for (int i = 0; i < listReportDocument.size(); i++) {
			xitongjichusum += listReportDocument.get(i).getJichu();
			xitongbianjisum += listReportDocument.get(i).getBianjie();
			xitongzhongduansum += listReportDocument.get(i).getZhongduan();
			xitongfuwusum += listReportDocument.get(i).getFuwu();
			xitongyingyongsum += listReportDocument.get(i).getYingyong();
			xitongshujusum += listReportDocument.get(i).getShuju();
			xitonganquansum += listReportDocument.get(i).getAnquan();
			xitongtywuli += listReportDocument.get(i).getTywuli();
			xitongtyguanli += listReportDocument.get(i).getTyguanli();
		}

		int totosum = xitongjichusum + xitongbianjisum + xitongzhongduansum
				+ xitongfuwusum + xitongyingyongsum + xitongshujusum
				+ xitonganquansum + xitongtywuli + xitongtyguanli;

		Document document;
		BaseFont baseFont;
		document = new Document(PageSize.A4);
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
			songPath="/usr/share/fonts/dejavu/DejaVuSansMono.ttf";
			blackFontPath="/usr/share/fonts/dejavu/DejaVuSansMono-Bold.ttf";
			fangsongPath="/usr/share/fonts/dejavu/DejaVuSerif.ttf";
			wingdings2FontPath="/usr/share/fonts/dejavu/unifont.ttf";
		}

		BaseFont blackBaseFont = BaseFont.createFont(songPath, "Identity-H",
				false);
		BaseFont songFont = BaseFont.createFont(blackFontPath, "Identity-H",
				false);
		BaseFont fangsongFont = BaseFont.createFont(fangsongPath, "Identity-H",
				false);
		BaseFont wingdings2Font = BaseFont.createFont(wingdings2FontPath,
				"Identity-H", false);

		Font songfont_11 = new Font(songFont, 11.0F, 0);
		Font songfontUnderLine_11 = new Font(songFont, 11.0F, 4);
		Font blackfont_15 = new Font(blackBaseFont, 15.0F, 1);
		Font blackfont_13 = new Font(blackBaseFont, 13.0F, 1);
		Font blackfont_11 = new Font(blackBaseFont, 11.0F, 1);

		Float firstSpacing = Float.valueOf(10.0F);
		Float indent = Float.valueOf(20.0F);

		RtfWriter2.getInstance(document, new FileOutputStream(fileName));
		document.addAuthor("http:www.reachway.com.cn");

		Phrase hearerPhrse = new Phrase("等级保护差距评估总体报告");
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

		p = new Paragraph("");
		p.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1));
		p.setAlignment(1);
		document.add(p);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		p = new Paragraph("等级保护差距评估总体报告");
		p.setFont(new Font(blackBaseFont, normalOne.floatValue(), 1));
		p.setAlignment(1);
		document.add(p);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);

		p = new Paragraph("["
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()) + "]");
		p.setFont(new Font(songFont, normalThree.floatValue(), 1));
		p.setAlignment(1);
		document.add(p);

		document.add(Chunk.NEXTPAGE);

		p = new Paragraph("1.差距评估概述");
		p.setSpacingBefore(20.0F);
		p.setFont(docTitle1);
		document.add(p);

		p = new Paragraph("1.1.差距评估目的");
		p.setSpacingBefore(20.0F);
		p.setFont(docTitle2);
		document.add(p);

		p = new Paragraph("2007年四部委正式发布《信息安全等级保护管理办法》（公通字[2007] 43号），要求：");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		com.lowagie.text.List itextList = new com.lowagie.text.List(false, 9.0F);
		itextList.setListSymbol("●");
		itextList.setIndentationLeft(24.0F);
		itextList.setSymbolIndent(12.0F);

		p = new Paragraph("信息系统运营、使用单位应当依据本办法和《信息系统安全等级保护定级指南》确定信息系统的安全保护等级；");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);

		ListItem firstItem = new ListItem(p);
		firstItem.setAlignment(0);
		itextList.add(firstItem);

		p = new Paragraph("信息系统运营、使用单位应当按照《信息系统安全等级保护实施指南》具体实施等级保护工作；");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);

		ListItem secondItem = new ListItem(p);
		secondItem.setAlignment(0);
		itextList.add(secondItem);

		p = new Paragraph(
				"信息系统运营、使用单位及其主管部门应当定期对信息系统安全状况、安全保护制度及措施的落实情况进行自查（第三级信息系统应当每年至少进行一次自查，第四级信息系统应当每半年至少进行一次自查，第五级信息系统应当依据特殊安全需求进行自查）。");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);

		ListItem thirdItem = new ListItem(p);
		thirdItem.setAlignment(0);
		itextList.add(thirdItem);

		document.add(itextList);

		p = new Paragraph(
				"进行差距评估的目的是在完成信息系统定级后或每次自查时，依据信息系统所属的安全保护级别要求对相应的信息系统进行评估，以便全面、完整地了解信息系统等级保护要求的基本安全控制在信息系统中的配置情况以及系统的整体安全性，同时发现与《信息系统安全等级保护基本要求》之间的差距并分析其导致原因，明确下一步进行整改的需求与内容。");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setSpacingAfter(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		p = new Paragraph("1.2.差距评估依据");
		p.setFont(docTitle2);
		p.setSpacingBefore(8.0F);
		document.add(p);

		p = new Paragraph("本次差距评估工作所依据的文件和标准如下：");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(8.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		com.lowagie.text.List itextList1_2 = new com.lowagie.text.List(false,
				9.0F);
		chunk = new Chunk("●");
		chunk.setFont(new Font(songFont, smartFour.floatValue(), 0));

		itextList1_2.setListSymbol(chunk);
		itextList1_2.setIndentationLeft(24.0F);
		itextList1_2.setSymbolIndent(12.0F);

		p = new Paragraph("《广播电视安全播出管理规定》（总局62号令）");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);

		ListItem firstItem1_2 = new ListItem(p);
		itextList1_2.add(firstItem1_2);

		p = new Paragraph("《广播电视相关信息系统安全等级保护测评要求》");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);

		ListItem secondItem1_2 = new ListItem(p);
		itextList1_2.add(secondItem1_2);

		p = new Paragraph("《广播电视相关信息系统安全等级保护定级指南-发布版》");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);

		ListItem thirdItem1_2 = new ListItem(p);
		itextList1_2.add(thirdItem1_2);

		p = new Paragraph("《广播电视相关信息系统安全等级保护基本要求-发布版》");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setSpacingBefore(10.0F);
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);

		ListItem fourItem1_2 = new ListItem(p);
		itextList1_2.add(fourItem1_2);

		List<ProjectShowcase> listproject = securityGapService
				.getProjectShowcase();
		for (ProjectShowcase projectShowcase : listproject) {
			p = new Paragraph(" [" + projectShowcase.getCaName() + "安全等级保护定级报告]");
			p.setFont(new Font(songFont, smartFour.floatValue(), 0));
			p.setSpacingBefore(10.0F);
			p.setFirstLineIndent(24.0F);
			p.setLeading(22.0F);

			ListItem fiveItem1_2 = new ListItem(p);
			itextList1_2.add(fiveItem1_2);
		}
		document.add(itextList1_2);

		p = new Paragraph("1.3.差距评估范围");
		p.setFont(docTitle2);
		document.add(p);

		p = new Paragraph("此次差距评估所包括的信息系统及其安全保护等级如下：");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		Table t = new Table(5);
		t.setBorderWidth(1.0F);
		t.setBorderColor(new Color(79, 129, 189));
		t.setPadding(5.0F);

		t.setWidth(100.0F);
		t.setWidths(new int[] { 30, 85, 40, 55, 70 });

		Cell cell = new Cell();

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

		chunk = new Chunk("信息系统名称");
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

		chunk = new Chunk("定级情况");
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

		chunk = new Chunk(" ");
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
		chunk = new Chunk("是否完成差距评估");
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
		for (ProjectShowcase projectShowcase : listproject) {

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

			chunk = new Chunk(projectShowcase.getCaName());
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

			chunk = new Chunk(projectShowcase.getCasysGrade());
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

			chunk = new Chunk();
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

			chunk = new Chunk("已完成");
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

		document.add(t);

		p = new Paragraph("说明：对于未完成差距评估的信息系统，不在此报告汇总之内。");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		p = new Paragraph("1.4.差距评估方法");
		p.setFont(docTitle2);
		document.add(p);

		p = new Paragraph("差距评估的主要方式有：人员访谈、文档检查、技术核查和工具测试。");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		com.lowagie.text.List itextList1_4_1 = new com.lowagie.text.List(false,
				9.0F);
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
		com.lowagie.text.List itextList1_4_2 = new com.lowagie.text.List(false,
				9.0F);
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
		com.lowagie.text.List itextList1_4_3 = new com.lowagie.text.List(false,
				9.0F);
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

		p = new Paragraph(
				"针对系统的不同层面，分别从物理、主机、网络、应用和数据五个层面，检查系统安全功能的设置和实现情况，验证系统中设置的安全功能是否有效地发挥作用；");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);
		com.lowagie.text.List itextList1_4_4 = new com.lowagie.text.List(false,
				9.0F);
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

		p = new Paragraph(
				"通过利用专用的安全工具对系统的安全功能要求以及如何正确有效实施这些功能的保证要求进行测试，以验证系统在技术方面达到的安全程度是否符合被测系统业务需求和安全防护需求。");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		p = new Paragraph("2.差距评估结果综述");
		p.setFont(docTitle1);
		document.add(p);

		p = new Paragraph("2.1.内容与工作量统计");
		p.setFont(docTitle2);
		document.add(p);

		StringBuffer note_2_1 = new StringBuffer();

		for (int j = 0; j < listproject.size(); j++) {
			note_2_1.append("本次差距评估针对[" + listproject.get(j).getCaName() + "]的");

		}

		note_2_1.append("共计["
				+ listproject.size()
				+ "]个信息系统分别从管理和技术两个层面对相关信息系统进行等级保护符合度分析，涉及到物理安全、网络安全、主机安全、应用安全、数据安全及备份恢复、安全管理制度、安全管理机构、人员安全管理、系统建设管理、系统运维管理等十大领域共计["
				+ "508" + "]个控制项。");

		p = new Paragraph(note_2_1.toString());
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		t = new Table(11);
		t.setBorderWidth(1.0F);
		t.setBorderColor(new Color(79, 129, 189));
		t.setPadding(5.0F);
		t.setWidth(100.0F);

		int[] widths = new int[11];
		widths[0] = 3;

		for (int j = 0; j < 11; j++) {
			widths[j] = 3;
		}

		t.setWidths(widths);
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

		chunk = new Chunk("基础网络安全");
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

		chunk = new Chunk("边界安全");
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

		chunk = new Chunk("终端系统安全");
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

		chunk = new Chunk("服务端系统安全");
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

		chunk = new Chunk("应用安全");
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

		chunk = new Chunk("数据安全域备份恢复");
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

		chunk = new Chunk("安全管理中心");
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

		chunk = new Chunk("通用物理安全");
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

		chunk = new Chunk("通用管理安全");
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

		chunk = new Chunk("合计");
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
		for (ReportDocument reportDocument22 : listReportDocument) {

			chunk = new Chunk(reportDocument22.getSysname() + "[适用项]");
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

			// 基础网络安全
			String jichu = String.valueOf(reportDocument22.getJichu());
			chunk = new Chunk(jichu);
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

			// 边界安全
			String bianji = String.valueOf(reportDocument22.getBianjie());
			chunk = new Chunk(bianji);
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

			// 终端系统安全
			String zhongduan = String.valueOf(reportDocument22.getZhongduan());
			chunk = new Chunk(zhongduan);
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

			// 服务端系统安全
			String fuwu = String.valueOf(reportDocument22.getFuwu());
			chunk = new Chunk(fuwu);
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

			// 应用安全
			String yingyong = String.valueOf(reportDocument22.getYingyong());
			chunk = new Chunk(yingyong);
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

			// 数据安全域备份恢复
			String shuju = String.valueOf(reportDocument22.getShuju());
			chunk = new Chunk(shuju);
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

			// 安全管理中心
			String anquan = String.valueOf(reportDocument22.getAnquan());
			chunk = new Chunk(anquan);
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

			// 通用物理安全
			String twuli = String.valueOf(reportDocument22.getTywuli());
			chunk = new Chunk(twuli);
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
			// 通用管理安全
			String tanquan = String.valueOf(reportDocument22.getTyguanli());
			chunk = new Chunk(tanquan);
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

			// 合计
			String danhangheji = String.valueOf(reportDocument22.getSum());
			chunk = new Chunk(danhangheji);
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
			ii++;
		}

		chunk = new Chunk("合计");
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

		//
		String jichu = String.valueOf(xitongjichusum);
		chunk = new Chunk(jichu);
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

		// 边界安全
		String bianji = String.valueOf(xitongzhongduansum);
		chunk = new Chunk(bianji);
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

		// 终端系统安全
		String zhongduan = String.valueOf(xitongzhongduansum);
		chunk = new Chunk(zhongduan);
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

		// 服务端系统安全
		String fuwu = String.valueOf(xitongfuwusum);
		chunk = new Chunk(fuwu);
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

		// 应用安全
		String yingyong = String.valueOf(xitongyingyongsum);
		chunk = new Chunk(yingyong);
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

		// 数据安全域备份恢复
		String shuju = String.valueOf(xitongshujusum);
		chunk = new Chunk(shuju);
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

		// 安全管理中心
		String anquan = String.valueOf(xitonganquansum);
		chunk = new Chunk(anquan);
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

		// 通用物理安全
		String twuli = String.valueOf(xitongtywuli);
		chunk = new Chunk(twuli);
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
		// 通用管理安全
		String tanquan = String.valueOf(xitongtyguanli);
		chunk = new Chunk(tanquan);
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

		// 合计
		String danhangheji = String.valueOf(totosum);
		chunk = new Chunk(danhangheji);
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

		document.add(t);

		p = new Paragraph("2.2.信息系统总体符合度");
		p.setFont(docTitle2);
		document.add(p);

		p = new Paragraph("各信息系统与之相对应的等级保护要求相比较的总体符合度见下表：");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		t = new Table(7);
		t.setBorderWidth(1.0F);
		t.setBorderColor(new Color(79, 129, 189));
		t.setPadding(5.0F);

		t.setWidth(100.0F);
		t.setWidths(new int[] { 3, 13, 5, 6, 6, 6, 6 });

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
		cell.setRowspan(2);
		t.addCell(cell);

		chunk = new Chunk("信息系统名称");
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
		cell.setRowspan(2);
		t.addCell(cell);

		chunk = new Chunk("定级情况");
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
		cell.setRowspan(2);
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
		cell.setColspan(4);
		t.addCell(cell);

		chunk = new Chunk("总体符合度");
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
		chunk = new Chunk("物理符合度");
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
		chunk = new Chunk("技术符合度");
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

		chunk = new Chunk("管理符合度");
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
		for (GeneralPhysical generalPhysical : listGeneralPhysical) {
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

			chunk = new Chunk("[" + generalPhysical.getSysname() + "]");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(0);
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

			chunk = new Chunk(generalPhysical.getSysgrade());
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

			chunk = new Chunk(
					generalPhysical.getTotalPercentage() == null ? "0"
							: generalPhysical.getTotalPercentage() + "%");
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

			chunk = new Chunk(
					generalPhysical.getPhysicalPercentage() == null ? "0"
							: generalPhysical.getPhysicalPercentage() + "%");
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

			chunk = new Chunk(
					generalPhysical.getTechnologyPercentage() == null ? "0"
							: generalPhysical.getTechnologyPercentage() + "%");
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

			chunk = new Chunk(
					generalPhysical.getManagementPercentage() == null ? "0"
							: generalPhysical.getManagementPercentage() + "%");
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

		document.add(t);

		chunk = new Chunk("");
		chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
		p = new Paragraph(chunk);
		document.add(p);

		JFreeChart chart = MakeJFreeChartUtil
				.getSystemInfosFitBarChart(listGeneralPhysical);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsPNG(baos, chart, 600, 400);
		Image png = Image.getInstance(baos.toByteArray());
		png.scalePercent(70.0F, 70.0F);
		document.add(png);

		p = new Paragraph("2.3.安全领域符合度");
		p.setFont(docTitle2);
		document.add(p);

		p = new Paragraph("各信息系统与等级保护要求的各安全领域相比较的符合度见下表：");
		p.setFont(new Font(songFont, smartFour.floatValue(), 0));
		p.setFirstLineIndent(24.0F);
		p.setLeading(22.0F);
		document.add(p);

		t = new Table(10);
		t.setBorderWidth(1.0F);
		t.setBorderColor(new Color(79, 129, 189));
		t.setPadding(5.0F);
		t.setWidth(100.0F);
		t.setWidths(new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 });
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

		chunk = new Chunk("基础网络安全");
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

		chunk = new Chunk("边界安全");
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

		chunk = new Chunk("终端系统安全");
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

		chunk = new Chunk("服务端系统安全");
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

		chunk = new Chunk("应用安全");
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

		chunk = new Chunk("数据安全域备份恢复");
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

		chunk = new Chunk("安全管理中心");
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

		chunk = new Chunk("通用物理安全");
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

		chunk = new Chunk("通用管理安全");
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

		int aa = 0;
		for (SecurityTable securityTable22 : listSecurityTable) {
			aa++;
			chunk = new Chunk(securityTable22.getSysname());
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 基础网络安全
			chunk = new Chunk(securityTable22.getJichuPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 边界安全
			chunk = new Chunk(securityTable22.getBianjiePertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 终端系统安全
			chunk = new Chunk(securityTable22.getZhongduanPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 服务端系统安全
			chunk = new Chunk(securityTable22.getFuwuduanPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 应用安全
			chunk = new Chunk(securityTable22.getYingyongPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 数据安全域备份恢复
			chunk = new Chunk(securityTable22.getShujuPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 安全管理中心
			chunk = new Chunk(securityTable22.getAnquanPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

			// 通用物理安全
			chunk = new Chunk(securityTable22.getTwuliPertage() == null ? "0"
					: securityTable22.getTwuliPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);
			// 通用管理安全
			chunk = new Chunk(securityTable22.getTguanliPertage() == null ? "0"
					: securityTable22.getTguanliPertage() + "%");
			chunk.setFont(new Font(songFont, normalFive.floatValue(), 0));
			cell = new Cell(chunk);
			cell.setHorizontalAlignment(1);
			cell.setVerticalAlignment(5);
			cell.setBorderColor(new Color(79, 129, 189));
			if (aa % 2 == 1)
				cell.setBackgroundColor(new Color(211, 223, 238));
			cell.setBorderColorLeft(new Color(0, 0, 0));
			cell.setBorderColorRight(new Color(0, 0, 0));
			cell.setBorderWidthRight(0.0F);
			cell.setBorderWidthLeft(0.0F);
			cell.setBorderWidthBottom(1.0F);
			cell.setBorderWidthTop(1.0F);
			t.addCell(cell);

		}

		chart = MakeJFreeChartUtil.getSystemLineJFreeChart(listSecurityTable);
		baos = new ByteArrayOutputStream();
		ChartUtilities.writeChartAsPNG(baos, chart, 600, 400);
		png = Image.getInstance(baos.toByteArray());
		png.scalePercent(70.0F, 70.0F);
		document.add(png);

		document.add(t);

		p = new Paragraph("3.信息系统差距评估结果汇总");
		p.setFont(docTitle1);
		document.add(p);

		int m = 0;
		for (GapStatisticsUnit gapStatisticsUnit : listGapStatisticsUnit) {
			m++;
			p = new Paragraph("3." + m + ".[" + gapStatisticsUnit.getSysname()
					+ "]");
			p.setFont(docTitle2);
			document.add(p);

			p = new Paragraph("3." + m + ".1.总体符合度");
			p.setFont(docTitle3);
			document.add(p);

			p = new Paragraph("通过对[" + gapStatisticsUnit.getSysname()
					+ "]当前在等级保护[" + gapStatisticsUnit.getCasysGrade()
					+ "]的十个领域相应控制项的逐一评价，得到了[" + gapStatisticsUnit.getSysname()
					+ "]总体符合度见下图：");
			p.setFont(new Font(songFont, smartFour.floatValue(), 0));
			p.setFirstLineIndent(24.0F);
			p.setLeading(22.0F);
			document.add(p);

			chart = MakeJFreeChartUtil
					.getGapanalysisTotalPieJFreeChart(gapStatisticsUnit);
			baos = new ByteArrayOutputStream();
			ChartUtilities.writeChartAsPNG(baos, chart, 600, 350);
			png = Image.getInstance(baos.toByteArray());
			png.scalePercent(70.0F, 70.0F);
			document.add(png);
			p = new Paragraph("3." + m + ".2.差距分布图");
			p.setFont(docTitle3);
			document.add(p);
			p = new Paragraph("[" + gapStatisticsUnit.getSysname()
					+ "]差距项统计如下表所示：");
			p.setFont(new Font(songFont, smartFour.floatValue(), 0));
			p.setFirstLineIndent(24.0F);
			p.setLeading(22.0F);
			document.add(p);
			if (gapStatisticsUnit.getCasysGrade().endsWith("第二级")) {
				for (int j = 0; j < listGapStatisticsUnit.size(); j++) {
					if (listGapStatisticsUnit.get(j).getSysname()
							.equals(gapStatisticsUnit.getSysname())) {

						Table ta = new Table(8);
						ta.setBorderWidth(1.0F);
						ta.setBorderColor(new Color(79, 129, 189));
						ta.setPadding(5.0F);

						ta.setWidth(100.0F);
						ta.setWidths(new int[] { 10, 5, 5, 5, 5, 5, 5, 5 });

						chunk = new Chunk("安全领域");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("标准控制项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("适用控制项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						chunk = new Chunk("部分符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("不符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("差距项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						chunk = new Chunk("符合度");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						// 基础
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getJichu());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhun = String.valueOf(listGapStatisticsUnit
								.get(j).getBianzhun());
						chunk = new Chunk(bianzhun);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyong = String.valueOf(listGapStatisticsUnit
								.get(j).getShiyong());
						chunk = new Chunk(shiyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhe = String.valueOf(listGapStatisticsUnit.get(
								j).getFuhe());
						chunk = new Chunk(fuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhe = String.valueOf(listGapStatisticsUnit
								.get(j).getBufenfuhe());
						chunk = new Chunk(bufenfuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhe = String.valueOf(listGapStatisticsUnit
								.get(j).getBufuhe());

						chunk = new Chunk(bufuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chaju = String.valueOf(listGapStatisticsUnit
								.get(j).getChaju());

						chunk = new Chunk(chaju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedu = String.valueOf(listGapStatisticsUnit
								.get(j).getFuhedu() + "%");
						chunk = new Chunk(fuhedu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 边界

						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getBianji());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						String bianzhunbianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunbianji());
						chunk = new Chunk(bianzhunbianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongbianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongbianji());
						chunk = new Chunk(shiyongbianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhebianji());
						chunk = new Chunk(fuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhebianji());
						chunk = new Chunk(bufenfuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhebianji());

						chunk = new Chunk(bufuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajubianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajubianji());

						chunk = new Chunk(chajubianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedubianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedubianji() + "%");
						chunk = new Chunk(fuhedubianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 终端
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getZhongduan());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunzhongduan());
						chunk = new Chunk(bianzhunzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongzhongduan());
						chunk = new Chunk(shiyongzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhezhongduan());
						chunk = new Chunk(fuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhezhongduan());
						chunk = new Chunk(bufenfuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhezhongduan());

						chunk = new Chunk(bufuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuzhongduan());

						chunk = new Chunk(chajuzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduzhongduan() + "%");
						chunk = new Chunk(fuheduzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 服务
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getFuwu());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunfuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunfuwu());
						chunk = new Chunk(bianzhunfuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongfuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongfuwu());
						chunk = new Chunk(shiyongfuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhefuwu = String.valueOf(listGapStatisticsUnit
								.get(j).getFuhefuwu());
						chunk = new Chunk(fuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhefuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhefuwu());
						chunk = new Chunk(bufenfuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhefuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhefuwu());

						chunk = new Chunk(bufuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajufuwu = String.valueOf(listGapStatisticsUnit
								.get(j).getChajufuwu());

						chunk = new Chunk(chajufuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedufuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedufuwu() + "%");
						chunk = new Chunk(fuhedufuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 应用
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getYingyong());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunyingyong());
						chunk = new Chunk(bianzhunyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongyingyong());
						chunk = new Chunk(shiyongyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheyingyong());
						chunk = new Chunk(fuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheyingyong());
						chunk = new Chunk(bufenfuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheyingyong());

						chunk = new Chunk(bufuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuyingyong());

						chunk = new Chunk(chajuyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduyingyong() + "%");
						chunk = new Chunk(fuheduyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 数据
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getShuju());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunshuju());
						chunk = new Chunk(bianzhunshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongshuju());
						chunk = new Chunk(shiyongshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheshuju = String.valueOf(listGapStatisticsUnit
								.get(j).getFuheshuju());
						chunk = new Chunk(fuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheshuju());
						chunk = new Chunk(bufenfuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheshuju());

						chunk = new Chunk(bufuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajushuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajushuju());

						chunk = new Chunk(chajushuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedushuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedushuju() + "%");
						chunk = new Chunk(fuhedushuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 安全管理中心
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getAnquan());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						ta.addCell(cell);
						String bianzhunanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunanquan());
						chunk = new Chunk(bianzhunanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						ta.addCell(cell);

						String shiyonganquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyonganquan());
						chunk = new Chunk(shiyonganquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheanquan());
						chunk = new Chunk(fuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheanquan());
						chunk = new Chunk(bufenfuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheanquan());

						chunk = new Chunk(bufuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuanquan());

						chunk = new Chunk(chajuanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduanquan() + "%");
						chunk = new Chunk(fuheduanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 通用物理安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getTywuli());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhuntywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhuntywuli());
						chunk = new Chunk(bianzhuntywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongtywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongtywuli());
						chunk = new Chunk(shiyongtywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhetywuli());
						chunk = new Chunk(fuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhetywuli());
						chunk = new Chunk(bufenfuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhetywuli());

						chunk = new Chunk(bufuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajutywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajutywuli());

						chunk = new Chunk(chajutywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedutywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedutywuli() + "%");
						chunk = new Chunk(fuhedutywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 通用管理安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getTyguanli());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						String bianzhuntyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhuntyguanli());
						chunk = new Chunk(bianzhuntyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongtyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongtyguanli());
						chunk = new Chunk(shiyongtyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhetyguanli());
						chunk = new Chunk(fuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhetyguanli());
						chunk = new Chunk(bufenfuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhetyguanli());

						chunk = new Chunk(bufuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajutyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajutyguanli());

						chunk = new Chunk(chajutyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedutyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedutyguanli() + "%");
						chunk = new Chunk(fuhedutyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						document.add(ta);

						chart = MakeJFreeChartUtil
								.getSystemInfoRadarJFreeChart(gapStatisticsUnit);
						baos = new ByteArrayOutputStream();
						ChartUtilities.writeChartAsPNG(baos, chart, 566, 400);
						png = Image.getInstance(baos.toByteArray());
						png.scalePercent(70.0F, 70.0F);
						document.add(png);
					}
				}
			}

			// ///-------------三级----------------
			if (gapStatisticsUnit.getCasysGrade().endsWith("第三级")) {
				for (int j = 0; j < listGapStatisticsUnit.size(); j++) {
					if (listGapStatisticsUnit.get(j).getSysname()
							.equals(gapStatisticsUnit.getSysname())) {

						Table ta = new Table(8);
						ta.setBorderWidth(1.0F);
						ta.setBorderColor(new Color(79, 129, 189));
						ta.setPadding(5.0F);

						ta.setWidth(100.0F);
						ta.setWidths(new int[] { 10, 5, 5, 5, 5, 5, 5, 5 });

						chunk = new Chunk("安全领域");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("标准控制项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("适用控制项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						chunk = new Chunk("部分符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("不符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("差距项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						chunk = new Chunk("符合度");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getJichu());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhun = String.valueOf(listGapStatisticsUnit
								.get(j).getBianzhun());
						chunk = new Chunk(bianzhun);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyong = String.valueOf(listGapStatisticsUnit
								.get(j).getShiyong());
						chunk = new Chunk(shiyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhe = String.valueOf(listGapStatisticsUnit.get(
								j).getFuhe());
						chunk = new Chunk(fuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhe = String.valueOf(listGapStatisticsUnit
								.get(j).getBufenfuhe());
						chunk = new Chunk(bufenfuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhe = String.valueOf(listGapStatisticsUnit
								.get(j).getBufuhe());

						chunk = new Chunk(bufuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chaju = String.valueOf(listGapStatisticsUnit
								.get(j).getChaju());

						chunk = new Chunk(chaju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedu = String.valueOf(listGapStatisticsUnit
								.get(j).getFuhedu() + "%");
						chunk = new Chunk(fuhedu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 边界

						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getBianji());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						String bianzhunbianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunbianji());
						chunk = new Chunk(bianzhunbianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongbianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongbianji());
						chunk = new Chunk(shiyongbianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhebianji());
						chunk = new Chunk(fuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhebianji());
						chunk = new Chunk(bufenfuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhebianji());

						chunk = new Chunk(bufuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajubianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajubianji());

						chunk = new Chunk(chajubianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedubianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedubianji() + "%");
						chunk = new Chunk(fuhedubianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 终端
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getZhongduan());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunzhongduan());
						chunk = new Chunk(bianzhunzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongzhongduan());
						chunk = new Chunk(shiyongzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhezhongduan());
						chunk = new Chunk(fuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhezhongduan());
						chunk = new Chunk(bufenfuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhezhongduan());

						chunk = new Chunk(bufuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuzhongduan());

						chunk = new Chunk(chajuzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduzhongduan() + "%");
						chunk = new Chunk(fuheduzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 服务
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getFuwu());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunfuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunfuwu());
						chunk = new Chunk(bianzhunfuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongfuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongfuwu());
						chunk = new Chunk(shiyongfuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhefuwu = String.valueOf(listGapStatisticsUnit
								.get(j).getFuhefuwu());
						chunk = new Chunk(fuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhefuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhefuwu());
						chunk = new Chunk(bufenfuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhefuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhefuwu());

						chunk = new Chunk(bufuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajufuwu = String.valueOf(listGapStatisticsUnit
								.get(j).getChajufuwu());

						chunk = new Chunk(chajufuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedufuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedufuwu() + "%");
						chunk = new Chunk(fuhedufuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 应用
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getYingyong());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunyingyong());
						chunk = new Chunk(bianzhunyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongyingyong());
						chunk = new Chunk(shiyongyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheyingyong());
						chunk = new Chunk(fuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheyingyong());
						chunk = new Chunk(bufenfuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheyingyong());

						chunk = new Chunk(bufuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuyingyong());

						chunk = new Chunk(chajuyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduyingyong() + "%");
						chunk = new Chunk(fuheduyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 数据
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getShuju());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunshuju());
						chunk = new Chunk(bianzhunshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongshuju());
						chunk = new Chunk(shiyongshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheshuju = String.valueOf(listGapStatisticsUnit
								.get(j).getFuheshuju());
						chunk = new Chunk(fuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheshuju());
						chunk = new Chunk(bufenfuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheshuju());

						chunk = new Chunk(bufuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajushuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajushuju());

						chunk = new Chunk(chajushuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedushuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedushuju() + "%");
						chunk = new Chunk(fuhedushuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getAnquan());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunanquan());
						chunk = new Chunk(bianzhunanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyonganquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyonganquan());
						chunk = new Chunk(shiyonganquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheanquan());
						chunk = new Chunk(fuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheanquan());
						chunk = new Chunk(bufenfuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheanquan());

						chunk = new Chunk(bufuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuanquan());

						chunk = new Chunk(chajuanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduanquan() + "%");
						chunk = new Chunk(fuheduanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 通用物理安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getTywuli());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhuntywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhuntywuli());
						chunk = new Chunk(bianzhuntywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongtywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongtywuli());
						chunk = new Chunk(shiyongtywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhetywuli());
						chunk = new Chunk(fuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhetywuli());
						chunk = new Chunk(bufenfuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhetywuli());

						chunk = new Chunk(bufuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajutywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajutywuli());

						chunk = new Chunk(chajutywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedutywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedutywuli() + "%");
						chunk = new Chunk(fuhedutywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 通用管理安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getTyguanli());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						String bianzhuntyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhuntyguanli());
						chunk = new Chunk(bianzhuntyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongtyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongtyguanli());
						chunk = new Chunk(shiyongtyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhetyguanli());
						chunk = new Chunk(fuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhetyguanli());
						chunk = new Chunk(bufenfuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhetyguanli());

						chunk = new Chunk(bufuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajutyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajutyguanli());

						chunk = new Chunk(chajutyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedutyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedutyguanli() + "%");
						chunk = new Chunk(fuhedutyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
						cell = new Cell(chunk);
						cell.setHorizontalAlignment(1);
						cell.setVerticalAlignment(5);
						cell.setBackgroundColor(new Color(211, 223, 238));
						cell.setBorderColor(new Color(79, 129, 189));
						cell.setBorderColorLeft(new Color(0, 0, 0));
						cell.setBorderColorRight(new Color(0, 0, 0));
						cell.setBorderWidthRight(0.0F);
						cell.setBorderWidthLeft(0.0F);
						cell.setBorderWidthBottom(1.0F);
						cell.setBorderWidthTop(1.0F);
						ta.addCell(cell);

						document.add(ta);

						chart = MakeJFreeChartUtil
								.getSystemInfoRadarJFreeChart(gapStatisticsUnit);
						baos = new ByteArrayOutputStream();
						ChartUtilities.writeChartAsPNG(baos, chart, 566, 400);
						png = Image.getInstance(baos.toByteArray());
						png.scalePercent(70.0F, 70.0F);
						document.add(png);
					}
				}
			}

			// ///-------------四级----------------
			if (gapStatisticsUnit.getCasysGrade().endsWith("第四级")) {
				for (int j = 0; j < listGapStatisticsUnit.size(); j++) {
					if (listGapStatisticsUnit.get(j).getSysname()
							.equals(gapStatisticsUnit.getSysname())) {

						Table ta = new Table(8);
						ta.setBorderWidth(1.0F);
						ta.setBorderColor(new Color(79, 129, 189));
						ta.setPadding(5.0F);

						ta.setWidth(100.0F);
						ta.setWidths(new int[] { 10, 5, 5, 5, 5, 5, 5, 5 });

						chunk = new Chunk("安全领域");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("标准控制项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("适用控制项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						chunk = new Chunk("部分符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("不符合项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk("差距项");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						chunk = new Chunk("符合度");
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getJichu());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhun = String.valueOf(listGapStatisticsUnit
								.get(j).getBianzhun());
						chunk = new Chunk(bianzhun);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyong = String.valueOf(listGapStatisticsUnit
								.get(j).getShiyong());
						chunk = new Chunk(shiyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhe = String.valueOf(listGapStatisticsUnit.get(
								j).getFuhe());
						chunk = new Chunk(fuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhe = String.valueOf(listGapStatisticsUnit
								.get(j).getBufenfuhe());
						chunk = new Chunk(bufenfuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhe = String.valueOf(listGapStatisticsUnit
								.get(j).getBufuhe());

						chunk = new Chunk(bufuhe);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chaju = String.valueOf(listGapStatisticsUnit
								.get(j).getChaju());

						chunk = new Chunk(chaju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedu = String.valueOf(listGapStatisticsUnit
								.get(j).getFuhedu() + "%");
						chunk = new Chunk(fuhedu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 边界

						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getBianji());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						String bianzhunbianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunbianji());
						chunk = new Chunk(bianzhunbianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongbianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongbianji());
						chunk = new Chunk(shiyongbianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhebianji());
						chunk = new Chunk(fuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhebianji());
						chunk = new Chunk(bufenfuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhebianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhebianji());

						chunk = new Chunk(bufuhebianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajubianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajubianji());

						chunk = new Chunk(chajubianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedubianji = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedubianji() + "%");
						chunk = new Chunk(fuhedubianji);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 终端
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getZhongduan());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunzhongduan());
						chunk = new Chunk(bianzhunzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongzhongduan());
						chunk = new Chunk(shiyongzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhezhongduan());
						chunk = new Chunk(fuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhezhongduan());
						chunk = new Chunk(bufenfuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhezhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhezhongduan());

						chunk = new Chunk(bufuhezhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuzhongduan());

						chunk = new Chunk(chajuzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduzhongduan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduzhongduan() + "%");
						chunk = new Chunk(fuheduzhongduan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 服务
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getFuwu());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunfuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunfuwu());
						chunk = new Chunk(bianzhunfuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongfuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongfuwu());
						chunk = new Chunk(shiyongfuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhefuwu = String.valueOf(listGapStatisticsUnit
								.get(j).getFuhefuwu());
						chunk = new Chunk(fuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhefuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhefuwu());
						chunk = new Chunk(bufenfuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhefuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhefuwu());

						chunk = new Chunk(bufuhefuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajufuwu = String.valueOf(listGapStatisticsUnit
								.get(j).getChajufuwu());

						chunk = new Chunk(chajufuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedufuwu = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedufuwu() + "%");
						chunk = new Chunk(fuhedufuwu);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 应用
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getYingyong());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunyingyong());
						chunk = new Chunk(bianzhunyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongyingyong());
						chunk = new Chunk(shiyongyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheyingyong());
						chunk = new Chunk(fuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheyingyong());
						chunk = new Chunk(bufenfuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheyingyong());

						chunk = new Chunk(bufuheyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuyingyong());

						chunk = new Chunk(chajuyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduyingyong = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduyingyong() + "%");
						chunk = new Chunk(fuheduyingyong);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 数据
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getShuju());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunshuju());
						chunk = new Chunk(bianzhunshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongshuju());
						chunk = new Chunk(shiyongshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheshuju = String.valueOf(listGapStatisticsUnit
								.get(j).getFuheshuju());
						chunk = new Chunk(fuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheshuju());
						chunk = new Chunk(bufenfuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheshuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheshuju());

						chunk = new Chunk(bufuheshuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajushuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajushuju());

						chunk = new Chunk(chajushuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedushuju = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedushuju() + "%");
						chunk = new Chunk(fuhedushuju);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getAnquan());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhunanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhunanquan());
						chunk = new Chunk(bianzhunanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyonganquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyonganquan());
						chunk = new Chunk(shiyonganquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheanquan());
						chunk = new Chunk(fuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuheanquan());
						chunk = new Chunk(bufenfuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuheanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuheanquan());

						chunk = new Chunk(bufuheanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajuanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajuanquan());

						chunk = new Chunk(chajuanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuheduanquan = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuheduanquan() + "%");
						chunk = new Chunk(fuheduanquan);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 通用物理安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getTywuli());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);
						String bianzhuntywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhuntywuli());
						chunk = new Chunk(bianzhuntywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongtywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongtywuli());
						chunk = new Chunk(shiyongtywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhetywuli());
						chunk = new Chunk(fuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhetywuli());
						chunk = new Chunk(bufenfuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhetywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhetywuli());

						chunk = new Chunk(bufuhetywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajutywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajutywuli());

						chunk = new Chunk(chajutywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedutywuli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedutywuli() + "%");
						chunk = new Chunk(fuhedutywuli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						// 通用管理安全
						chunk = new Chunk(listGapStatisticsUnit.get(j)
								.getTyguanli());
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 1));
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
						ta.addCell(cell);

						String bianzhuntyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBianzhuntyguanli());
						chunk = new Chunk(bianzhuntyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String shiyongtyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getShiyongtyguanli());
						chunk = new Chunk(shiyongtyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhetyguanli());
						chunk = new Chunk(fuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufenfuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufenfuhetyguanli());
						chunk = new Chunk(bufenfuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String bufuhetyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getBufuhetyguanli());

						chunk = new Chunk(bufuhetyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String chajutyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getChajutyguanli());

						chunk = new Chunk(chajutyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						String fuhedutyguanli = String
								.valueOf(listGapStatisticsUnit.get(j)
										.getFuhedutyguanli() + "%");
						chunk = new Chunk(fuhedutyguanli);
						chunk.setFont(new Font(songFont, normalFive
								.floatValue(), 0));
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
						ta.addCell(cell);

						document.add(ta);

						chart = MakeJFreeChartUtil
								.getSystemInfoRadarJFreeChart(gapStatisticsUnit);
						baos = new ByteArrayOutputStream();
						ChartUtilities.writeChartAsPNG(baos, chart, 566, 400);
						png = Image.getInstance(baos.toByteArray());
						png.scalePercent(70.0F, 70.0F);
						document.add(png);
					}
				}
			}

		}

		document.close();

	}

	public void Download() {
		HttpServletResponse response = super.getResponse();
		LoadWord load = new LoadWord();
		String fileName = urlpathstatic;
		String urlfilenamepass = urlfilename;
		load.AttachmentDownload(response, fileName, urlfilenamepass);
		return;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
}
