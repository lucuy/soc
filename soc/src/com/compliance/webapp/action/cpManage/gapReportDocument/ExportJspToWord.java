package com.compliance.webapp.action.cpManage.gapReportDocument;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.compliance.model.cpManage.gapReportDocument.ExportWord;
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
import com.util.load.importWord.MakeJFreeChartUtil;

public class ExportJspToWord extends BaseAction {
	private SecurityGapService securityGapService;

	private GapReportDocumentService gapReportDocumentService;

	// //表：信息系统总体符合度
	private GeneralPhysicalService generalPhysicalService;

	// 表：安全领域符合度
	private SecurityTableService securityTableService;

	// 表：差距分布图-----差距单元统计
	private GapStatisticsUnitService gapStatisticsUnitService;

	List<ProjectShowcase> listproject;

	List<ExportWord> listExportWord = new ArrayList<ExportWord>();

	public List<ExportWord> getListExportWord() {
		return listExportWord;
	}

	public void setListExportWord(List<ExportWord> listExportWord) {
		this.listExportWord = listExportWord;
	}

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

	public String getExportJsp() throws Exception {
		DecimalFormat df1 = new DecimalFormat("00.00");
		HttpServletRequest request = super.getRequest();
		listproject = securityGapService.getProjectShowcase();
		request.setAttribute("listproject", listproject);

		JFreeChart chart;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String ss = sdf.format(new Date());
		for (int i = 0; i < 2; i++) {
			ss += String.valueOf((int) (java.lang.Math.random() * 10));
		}

		String tomcatpath = System.getProperty("catalina.home");
		String uploadPath = tomcatpath + "/dataword/File/sourceFile/";
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
		
		
		if (!file.isDirectory()) {
			file.mkdirs();
		}

		int m = 0;

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
				ExportWord exportWord11 = new ExportWord();
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
				double gsuPercentagettyguan = ((double) tyguanli0 / 33) * 100;
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
				exportWord11.setGapStatisticsUnit(gapStatisticsUnit);

				m++;
				String title = "3." + m + ".[" + gapStatisticsUnit.getSysname()
						+ "]";

				String titlett = "3." + m + ".1.总体符合度";
				String subtitle = "通过对[" + gapStatisticsUnit.getSysname()
						+ "]当前在等级保护[" + gapStatisticsUnit.getCasysGrade()
						+ "]的十个领域相应控制项的逐一评价，得到了["
						+ gapStatisticsUnit.getSysname() + "]总体符合度见下图：";
				String fileName3 = uploadPath + "3." + m + ".png";
				File filenew3 = new File(fileName3);
				chart = MakeJFreeChartUtil
						.getGapanalysisTotalPieJFreeChart(gapStatisticsUnit);
				ChartUtilities.saveChartAsPNG(filenew3, chart, 600, 350);

				String pingtuimg = fileName3;
				String subsubtitle = "3." + m + ".2.差距分布图";

				String sbtitle = "[" + gapStatisticsUnit.getSysname()
						+ "]差距项统计如下表所示：";

				String fileName4 = uploadPath + "4." + m + ".png";
				File filenew4 = new File(fileName4);
				chart = MakeJFreeChartUtil
						.getSystemInfoRadarJFreeChart(gapStatisticsUnit);
				ChartUtilities.saveChartAsPNG(filenew4, chart, 600, 350);

				String leidaimg = fileName4;

				exportWord11.setTitle(title);
				exportWord11.setTitlett(titlett);
				exportWord11.setSubtitle(subtitle);
				exportWord11.setSubsubtitle(subsubtitle);
				exportWord11.setLeidaimg(leidaimg);
				exportWord11.setPingtuimg(pingtuimg);
				exportWord11.setSysname(gapStatisticsUnit.getSysname());
				exportWord11.setSbtitle(sbtitle);

				listExportWord.add(exportWord11);

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
				ExportWord exportWord22 = new ExportWord();
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
				double gsuPercentagettyguan = ((double) tyguanli0 / 33) * 100;
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
				exportWord22.setGapStatisticsUnit(gapStatisticsUnit);

				m++;
				String title = "3." + m + ".[" + gapStatisticsUnit.getSysname()
						+ "]";

				String titlett = "3." + m + ".1.总体符合度";
				String subtitle = "通过对[" + gapStatisticsUnit.getSysname()
						+ "]当前在等级保护[" + gapStatisticsUnit.getCasysGrade()
						+ "]的十个领域相应控制项的逐一评价，得到了["
						+ gapStatisticsUnit.getSysname() + "]总体符合度见下图：";
				String fileName3 = uploadPath + "3." + m + ".png";
				File filenew3 = new File(fileName3);
				chart = MakeJFreeChartUtil
						.getGapanalysisTotalPieJFreeChart(gapStatisticsUnit);
				ChartUtilities.saveChartAsPNG(filenew3, chart, 600, 350);

				String pingtuimg = fileName3;
				String subsubtitle = "3." + m + ".2.差距分布图";

				String sbtitle = "[" + gapStatisticsUnit.getSysname()
						+ "]差距项统计如下表所示：";

				String fileName4 = uploadPath + "4." + m + ".png";
				File filenew4 = new File(fileName4);
				chart = MakeJFreeChartUtil
						.getSystemInfoRadarJFreeChart(gapStatisticsUnit);
				ChartUtilities.saveChartAsPNG(filenew4, chart, 600, 350);

				String leidaimg = fileName4;

				ExportWord exportWord = new ExportWord();

				exportWord22.setTitle(title);
				exportWord22.setTitlett(titlett);
				exportWord22.setSubtitle(subtitle);
				exportWord22.setSubsubtitle(subsubtitle);
				exportWord22.setLeidaimg(leidaimg);
				exportWord22.setPingtuimg(pingtuimg);
				exportWord22.setSysname(gapStatisticsUnit.getSysname());
				exportWord22.setSbtitle(sbtitle);
				listExportWord.add(exportWord22);

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
				ExportWord exportWord33 = new ExportWord();
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
				double gsuPercentagettyguan = ((double) tyguanli0 / 33) * 100;
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
				exportWord33.setGapStatisticsUnit(gapStatisticsUnit);

				m++;
				String title = "3." + m + ".[" + gapStatisticsUnit.getSysname()
						+ "]";

				String titlett = "3." + m + ".1.总体符合度";
				String subtitle = "通过对[" + gapStatisticsUnit.getSysname()
						+ "]当前在等级保护[" + gapStatisticsUnit.getCasysGrade()
						+ "]的十个领域相应控制项的逐一评价，得到了["
						+ gapStatisticsUnit.getSysname() + "]总体符合度见下图：";
				String fileName3 = uploadPath + "3." + m + ".png";
				File filenew3 = new File(fileName3);
				chart = MakeJFreeChartUtil
						.getGapanalysisTotalPieJFreeChart(gapStatisticsUnit);
				ChartUtilities.saveChartAsPNG(filenew3, chart, 600, 350);

				String pingtuimg = fileName3;
				String subsubtitle = "3." + m + ".2.差距分布图";

				String sbtitle = "[" + gapStatisticsUnit.getSysname()
						+ "]差距项统计如下表所示：";

				String fileName4 = uploadPath + "4." + m + ".png";
				File filenew4 = new File(fileName4);
				chart = MakeJFreeChartUtil
						.getSystemInfoRadarJFreeChart(gapStatisticsUnit);
				ChartUtilities.saveChartAsPNG(filenew4, chart, 600, 350);

				String leidaimg = fileName4;

				ExportWord exportWord = new ExportWord();

				exportWord33.setTitle(title);
				exportWord33.setTitlett(titlett);
				exportWord33.setSubtitle(subtitle);
				exportWord33.setSubsubtitle(subsubtitle);
				exportWord33.setLeidaimg(leidaimg);
				exportWord33.setPingtuimg(pingtuimg);
				exportWord33.setSysname(gapStatisticsUnit.getSysname());
				exportWord33.setSbtitle(sbtitle);
				listExportWord.add(exportWord33);

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
			int summ77 = 0;
			int summ777 = 0;
			for (int i = 0; i < listReportDocument.size(); i++) {
				summ1 += listReportDocument.get(i).getJichu();
				summ2 += listReportDocument.get(i).getBianjie();
				summ3 += listReportDocument.get(i).getZhongduan();
				summ4 += listReportDocument.get(i).getFuwu();
				summ5 += listReportDocument.get(i).getYingyong();
				summ6 += listReportDocument.get(i).getShuju();
				summ7 += listReportDocument.get(i).getAnquan();
				summ77 += listReportDocument.get(i).getTywuli();
				summ777 += listReportDocument.get(i).getTyguanli();
			}
			int summSum = summ1 + summ2 + summ3 + summ4 + summ5 + summ6 + summ7
					+ summ77 + summ777;
			request.setAttribute("summ1", summ1);
			request.setAttribute("summ2", summ2);
			request.setAttribute("summ3", summ3);
			request.setAttribute("summ4", summ4);
			request.setAttribute("summ5", summ5);
			request.setAttribute("summ6", summ6);
			request.setAttribute("summ7", summ7);
			request.setAttribute("summ77", summ77);
			request.setAttribute("summ777", summ777);
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

		String timenew = "["
				+ new SimpleDateFormat("yyyy年MM月dd日").format(new Date()) + "]";
		request.setAttribute("timenew", timenew);

		int countnum = listproject.size();
		request.setAttribute("countnum", countnum);

		for (GeneralPhysical generalPhysicalPic : listGeneralPhysical) {
			if (null != generalPhysicalPic) {

				chart = MakeJFreeChartUtil
						.getSystemInfosFitBarChart(listGeneralPhysical);

				String fileName = uploadPath + "zhuxing.png";
				File filenew = new File(fileName);
				ChartUtilities.saveChartAsPNG(filenew, chart, 600, 400);// 把报表保存为文件

				request.setAttribute("zhuzhuangpath", fileName);
			}
		}
		for (SecurityTable securityTablePic : listSecurityTable) {
			if (null != securityTablePic) {
				chart = MakeJFreeChartUtil
						.getSystemLineJFreeChart(listSecurityTable);
				String fileName2 = uploadPath + "zhexian.png";
				File filenew2 = new File(fileName2);
				ChartUtilities.saveChartAsPNG(filenew2, chart, 600, 400);// 把报表保存为文件

				request.setAttribute("zhexianpath", fileName2);
			}

		}

		// for (GapStatisticsUnit gapStatisticsUnit : listGapStatisticsUnit) {
		// m++;
		// String title = "3." + m + ".[" + gapStatisticsUnit.getSysname()
		// + "]";
		//
		// String titlett = "3." + m + ".1.总体符合度";
		// String subtitle = "通过对[" + gapStatisticsUnit.getSysname()
		// + "]当前在等级保护[" + gapStatisticsUnit.getCasysGrade()
		// + "]级的十个领域相应控制项的逐一评价，得到了[" + gapStatisticsUnit.getSysname()
		// + "]总体符合度见下图：";
		// String fileName3 = uploadPath + "3." + m
		// + ".png";
		// File filenew3 = new File(fileName3);
		// chart = MakeJFreeChartUtil
		// .getGapanalysisTotalPieJFreeChart(gapStatisticsUnit);
		// ChartUtilities.saveChartAsPNG(filenew3, chart, 600, 350);
		//
		// String pingtuimg = fileName3;
		// String subsubtitle = "3." + m + ".2.差距分布图";
		//
		// String sbtitle = "[" + gapStatisticsUnit.getSysname()
		// + "]差距项统计如下表所示：";
		//
		// String fileName4 = uploadPath + "4." + m
		// + ".png";
		// File filenew4 = new File(fileName4);
		// chart = MakeJFreeChartUtil
		// .getSystemInfoRadarJFreeChart(gapStatisticsUnit);
		// ChartUtilities.saveChartAsPNG(filenew4, chart, 600, 350);
		//
		// String leidaimg = fileName4;
		//
		// ExportWord exportWord = new ExportWord();
		//
		// exportWord.setTitle(title);
		// exportWord.setTitlett(titlett);
		// exportWord.setSubtitle(subtitle);
		// exportWord.setSubsubtitle(subsubtitle);
		// exportWord.setLeidaimg(leidaimg);
		// exportWord.setPingtuimg(pingtuimg);
		// exportWord.setSysname(gapStatisticsUnit.getSysname());
		// exportWord.setSbtitle(sbtitle);
		// listExportWord.add(exportWord);
		// }

		request.setAttribute("listExportWord", listExportWord);

		return SUCCESS;
	}

	/**
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * 
	 * @param sPath
	 *            要删除的目录或文件
	 * @return 删除成功返回 true，否则返回 false。
	 */
	public boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(sPath);
			}
		}
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

}
