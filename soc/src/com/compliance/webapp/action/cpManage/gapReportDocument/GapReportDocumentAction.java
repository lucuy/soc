package com.compliance.webapp.action.cpManage.gapReportDocument;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.compliance.model.cpManage.gapReportDocument.ReportDocument;
import com.compliance.model.cpManage.gapReportDocument.ReportDocumentCount;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnit;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnitAnQuan;
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

//合规管理
//差距分析报告
//导出差距分析报告
public class GapReportDocumentAction extends BaseAction {
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


	public String getGapReportDocument() {

		listproject = securityGapService.getProjectShowcase();
		HttpServletRequest request = super.getRequest();
		request.setAttribute("listproject", listproject);
		return SUCCESS;

	}

	public String getSafeField() {
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
				int sum = list2.get(0) + list2.get(1) + list2.get(2)
						+ list2.get(3) + list2.get(4) + list2.get(5)
						+ list2.get(6);
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
				listSecurityTable.add(securityTable);

				GapStatisticsUnit gapStatisticsUnitjichu = new GapStatisticsUnit();
				int gsnum0 = gapStatisticsUnitService.getGapStatisticsUnitTwo0(
						pkca).get(0);
				int gsnum1 = gapStatisticsUnitService.getGapStatisticsUnitTwo1(
						pkca).get(0);
				int gsnum2 = gapStatisticsUnitService.getGapStatisticsUnitTwo2(
						pkca).get(0);
				int gsnum1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(0);

				gapStatisticsUnitjichu.setBianzhun(3);
				gapStatisticsUnitjichu.setShiyong(3);
				gapStatisticsUnitjichu.setFuhe(gsnum0);
				gapStatisticsUnitjichu.setBufenfuhe(gsnum1);
				gapStatisticsUnitjichu.setBufuhe(gsnum2);
				gapStatisticsUnitjichu.setChaju(gsnum1and2);
				double gsuPercentage0 = ((double) gsnum0 / 3) * 100;
				gapStatisticsUnitjichu.setFuhedu(df1.format(gsuPercentage0));

				listGapStatisticsUnit.add(gapStatisticsUnitjichu);

				GapStatisticsUnitBianJie gapStatisticsUnitbianjie = new GapStatisticsUnitBianJie();
				int gsnumbianjie0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(1);
				int gsnumbianjie1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(1);
				int gsnumbianjie2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(1);
				int gsnumbianjie1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(1);

				gapStatisticsUnitbianjie.setBianzhun(6);
				gapStatisticsUnitbianjie.setShiyong(6);
				gapStatisticsUnitbianjie.setFuhe(gsnumbianjie0);
				gapStatisticsUnitbianjie.setBufenfuhe(gsnumbianjie1);
				gapStatisticsUnitbianjie.setBufuhe(gsnumbianjie2);
				gapStatisticsUnitbianjie.setChaju(gsnumbianjie1and2);
				double gsuPercentage1 = ((double) gsnumbianjie0 / 6) * 100;
				gapStatisticsUnitjichu.setFuhedu(df1.format(gsuPercentage1));

				listGapStatisticsUnitBianJie.add(gapStatisticsUnitbianjie);

				GapStatisticsUnitZhongDuan gapStatisticsUnitzhongduan = new GapStatisticsUnitZhongDuan();
				int gsnumzhongduan0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(2);
				int gsnumzhongduan1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(2);
				int gsnumzhongduan2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(2);
				int gsnumzhongduan1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(2);

				gapStatisticsUnitzhongduan.setBianzhun(4);
				gapStatisticsUnitzhongduan.setShiyong(4);
				gapStatisticsUnitzhongduan.setFuhe(gsnumzhongduan0);
				gapStatisticsUnitzhongduan.setBufenfuhe(gsnumzhongduan1);
				gapStatisticsUnitzhongduan.setBufuhe(gsnumzhongduan2);
				gapStatisticsUnitzhongduan.setChaju(gsnumzhongduan1and2);
				double gsuPercentagezhongduan = ((double) gsnumzhongduan0 / 4) * 100;
				gapStatisticsUnitjichu.setFuhedu(df1
						.format(gsuPercentagezhongduan));

				listGapStatisticsUnitZhongDuan.add(gapStatisticsUnitzhongduan);

				GapStatisticsUnitFuWu gapStatisticsUnitfuwu = new GapStatisticsUnitFuWu();
				int gsnumfuwu0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(3);
				int gsnumfuwu1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(3);
				int gsnumfuwu2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(3);
				int gsnumfuwu1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(3);

				gapStatisticsUnitfuwu.setBianzhun(7);
				gapStatisticsUnitfuwu.setShiyong(7);
				gapStatisticsUnitfuwu.setFuhe(gsnumfuwu0);
				gapStatisticsUnitfuwu.setBufenfuhe(gsnumfuwu1);
				gapStatisticsUnitfuwu.setBufuhe(gsnumfuwu2);
				gapStatisticsUnitfuwu.setChaju(gsnumfuwu1and2);
				double gsuPercentagefuwu = ((double) gsnumfuwu0 / 7) * 100;
				gapStatisticsUnitjichu.setFuhedu(df1.format(gsuPercentagefuwu));

				listGapStatisticsUnitFuWu.add(gapStatisticsUnitfuwu);

				GapStatisticsUnitYingYong gapStatisticsUnityingyong = new GapStatisticsUnitYingYong();
				int gsnumyingyong0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(4);
				int gsnumyingyong1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(4);
				int gsnumyingyong2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(4);
				int gsnumyingyong1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(4);

				gapStatisticsUnityingyong.setBianzhun(7);
				gapStatisticsUnityingyong.setShiyong(7);
				gapStatisticsUnityingyong.setFuhe(gsnumyingyong0);
				gapStatisticsUnityingyong.setBufenfuhe(gsnumyingyong1);
				gapStatisticsUnityingyong.setBufuhe(gsnumyingyong2);
				gapStatisticsUnityingyong.setChaju(gsnumyingyong1and2);
				double gsuPercentageyingyong = ((double) gsnumyingyong0 / 7) * 100;
				gapStatisticsUnitjichu.setFuhedu(df1
						.format(gsuPercentageyingyong));

				listGapStatisticsUnitYingYong.add(gapStatisticsUnityingyong);

				GapStatisticsUnitShuJu gapStatisticsUnitshuju = new GapStatisticsUnitShuJu();
				int gsnumshuju0 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo0(pkca).get(5);
				int gsnumshuju1 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo1(pkca).get(5);
				int gsnumshuju2 = gapStatisticsUnitService
						.getGapStatisticsUnitTwo2(pkca).get(5);
				int gsnumshuju1and2 = gapStatisticsUnitService
						.getGapStatisticsUnitThree1And2(pkca).get(5);

				gapStatisticsUnitshuju.setBianzhun(3);
				gapStatisticsUnitshuju.setShiyong(3);
				gapStatisticsUnitshuju.setFuhe(gsnumshuju0);
				gapStatisticsUnitshuju.setBufenfuhe(gsnumshuju1);
				gapStatisticsUnitshuju.setBufuhe(gsnumshuju2);
				gapStatisticsUnitshuju.setChaju(gsnumshuju1and2);
				double gsuPercentageshuju = ((double) gsnumshuju0 / 3) * 100;
				gapStatisticsUnitjichu
						.setFuhedu(df1.format(gsuPercentageshuju));

				listGapStatisticsUnitShuJu.add(gapStatisticsUnitshuju);
				
				

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
				int sum3 = list3.get(0) + list3.get(1) + list3.get(2)
						+ list3.get(3) + list3.get(4) + list3.get(5)
						+ list3.get(6);
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
				listSecurityTable.add(securityTable3);

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
				int sum4 = list4.get(0) + list4.get(1) + list4.get(2)
						+ list4.get(3) + list4.get(4) + list4.get(5)
						+ list4.get(6);
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
				listSecurityTable.add(securityTable4);
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
				listGeneralPhysical.add(generalPhysical4);
			}

		}
		request.setAttribute("listGapStatisticsUnit", listGapStatisticsUnit);
		request.setAttribute("listGapStatisticsUnitBianJie", listGapStatisticsUnitBianJie);
		request.setAttribute("listGapStatisticsUnitZhongDuan", listGapStatisticsUnitZhongDuan);
		request.setAttribute("listGapStatisticsUnitFuWu", listGapStatisticsUnitFuWu);
		request.setAttribute("listGapStatisticsUnitYingYong", listGapStatisticsUnitYingYong);
		request.setAttribute("listGapStatisticsUnitShuJu", listGapStatisticsUnitShuJu);
		return SUCCESS;

	}

	public GapStatisticsUnitService getGapStatisticsUnitService() {
		return gapStatisticsUnitService;
	}

	public void setGapStatisticsUnitService(
			GapStatisticsUnitService gapStatisticsUnitService) {
		this.gapStatisticsUnitService = gapStatisticsUnitService;
	}

	public List<SecurityTable> getListSecurityTable() {
		return listSecurityTable;
	}

	public void setListSecurityTable(List<SecurityTable> listSecurityTable) {
		this.listSecurityTable = listSecurityTable;
	}

	public SecurityTableService getSecurityTableService() {
		return securityTableService;
	}

	public void setSecurityTableService(
			SecurityTableService securityTableService) {
		this.securityTableService = securityTableService;
	}

	public List<GeneralPhysical> getListGeneralPhysical() {
		return listGeneralPhysical;
	}

	public void setListGeneralPhysical(List<GeneralPhysical> listGeneralPhysical) {
		this.listGeneralPhysical = listGeneralPhysical;
	}

	public SecurityGapService getSecurityGapService() {
		return securityGapService;
	}

	public void setSecurityGapService(SecurityGapService securityGapService) {
		this.securityGapService = securityGapService;
	}

	public List<ProjectShowcase> getListproject() {
		return listproject;
	}

	public void setListproject(List<ProjectShowcase> listproject) {
		this.listproject = listproject;
	}

	public GeneralPhysicalService getGeneralPhysicalService() {
		return generalPhysicalService;
	}

	public void setGeneralPhysicalService(
			GeneralPhysicalService generalPhysicalService) {
		this.generalPhysicalService = generalPhysicalService;
	}

	public List<ReportDocument> getListReportDocument() {
		return listReportDocument;
	}

	public void setListReportDocument(List<ReportDocument> listReportDocument) {
		this.listReportDocument = listReportDocument;
	}

	public GapReportDocumentService getGapReportDocumentService() {
		return gapReportDocumentService;
	}

	public void setGapReportDocumentService(
			GapReportDocumentService gapReportDocumentService) {
		this.gapReportDocumentService = gapReportDocumentService;
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
