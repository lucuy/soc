package com.compliance.webapp.action.cpManage.summaryAnalysis;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.compliance.model.cpManage.summaryAnalysis.ProjectShowcase;
import com.compliance.service.cpManage.summaryAnalysis.SecurityGapService;
import com.compliance.webapp.action.BaseAction;

public class SummaryAnalysisAction extends BaseAction {

	private SecurityGapService securityGapService;

	public SecurityGapService getSecurityGapService() {
		return securityGapService;
	}

	public void setSecurityGapService(SecurityGapService securityGapService) {
		this.securityGapService = securityGapService;
	}

	List<ProjectShowcase> listproject;

	public List<ProjectShowcase> getListproject() {
		return listproject;
	}

	public void setListproject(List<ProjectShowcase> listproject) {
		this.listproject = listproject;
	}

	public String getSecurityGapList() {
		int sum0;
		int sum1;
		int sum2;
		int sum3;
		int sum4;
		int sum5;
		int sum6;
		int n0;
		int n1;
		int n2;
		int n3;
		HttpServletRequest request = super.getRequest();
		String pkca1 = request.getParameter("pkca");
		int pkca = Integer.parseInt(pkca1);

		List<ProjectShowcase> listproject2 = securityGapService.getProjectShowcase();
		for (ProjectShowcase projectShowcase : listproject2) {
			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list = securityGapService.getJiZhuWangLou2(pkca);

				sum0 = list.get(0) + list.get(1) + list.get(2) + list.get(3);
				sum1 = list.get(4) + list.get(5) + list.get(6) + list.get(7);
				sum2 = list.get(8) + list.get(9) + list.get(10) + list.get(11);
				sum3 = list.get(12) + list.get(13) + list.get(14)
						+ list.get(15);
				sum4 = list.get(16) + list.get(17) + list.get(18)
						+ list.get(19);
				sum5 = list.get(20) + list.get(21) + list.get(22)
						+ list.get(23);
				sum6 = list.get(24) + list.get(25) + list.get(26)
						+ list.get(27);

				request.setAttribute("num0", list.get(0));
				request.setAttribute("num1", list.get(1));
				request.setAttribute("num2", list.get(2));
				request.setAttribute("num3", list.get(3));
				request.setAttribute("sum0", sum0);

				request.setAttribute("num4", list.get(4));
				request.setAttribute("num5", list.get(5));
				request.setAttribute("num6", list.get(6));
				request.setAttribute("num7", list.get(7));
				request.setAttribute("sum1", sum1);

				request.setAttribute("num8", list.get(8));
				request.setAttribute("num9", list.get(9));
				request.setAttribute("num10", list.get(10));
				request.setAttribute("num11", list.get(11));
				request.setAttribute("sum2", sum2);

				request.setAttribute("num12", list.get(12));
				request.setAttribute("num13", list.get(13));
				request.setAttribute("num14", list.get(14));
				request.setAttribute("num15", list.get(15));
				request.setAttribute("sum3", sum3);

				request.setAttribute("num16", list.get(16));
				request.setAttribute("num17", list.get(17));
				request.setAttribute("num18", list.get(18));
				request.setAttribute("num19", list.get(19));
				request.setAttribute("sum4", sum4);

				request.setAttribute("num20", list.get(20));
				request.setAttribute("num21", list.get(21));
				request.setAttribute("num22", list.get(22));
				request.setAttribute("num23", list.get(23));
				request.setAttribute("sum5", sum5);

				request.setAttribute("num24", list.get(24));
				request.setAttribute("num25", list.get(25));
				request.setAttribute("num26", list.get(26));
				request.setAttribute("num27", list.get(27));
				request.setAttribute("sum6", sum6);
				n0 = list.get(0);
				n1 = list.get(1);
				n2 = list.get(2);
				n3 = list.get(3);
				if (sum0 != 0) {
					double percentage0 = ((double) n0 / sum0) * 100;
					double percentage1 = ((double) n1 / sum0) * 100;
					double percentage2 = ((double) n2 / sum0) * 100;
					double percentage3 = ((double) n3 / sum0) * 100;

					request.setAttribute("percentage0", percentage0);
					request.setAttribute("percentage1", percentage1);
					request.setAttribute("percentage2", percentage2);
					request.setAttribute("percentage3", percentage3);

				}
				if (sum1 != 0) {
					double percentage4 = ((double) list.get(4) / sum1) * 100;
					double percentage5 = ((double) list.get(5) / sum1) * 100;
					double percentage6 = ((double) list.get(6) / sum1) * 100;
					double percentage7 = ((double) list.get(7) / sum1) * 100;

					request.setAttribute("percentage4", percentage4);
					request.setAttribute("percentage5", percentage5);
					request.setAttribute("percentage6", percentage6);
					request.setAttribute("percentage7", percentage7);
				}

				if (sum2 != 0) {

					double percentage8 = ((double) list.get(8) / sum2) * 100;
					double percentage9 = ((double) list.get(9) / sum2) * 100;
					double percentage10 = ((double) list.get(10) / sum2) * 100;
					double percentage11 = ((double) list.get(11) / sum2) * 100;

					request.setAttribute("percentage8", percentage8);
					request.setAttribute("percentage9", percentage9);
					request.setAttribute("percentage10", percentage10);
					request.setAttribute("percentage11", percentage11);

				}

				if (sum3 != 0) {
					double percentage12 = ((double) list.get(12) / sum3) * 100;
					double percentage13 = ((double) list.get(13) / sum3) * 100;
					double percentage14 = ((double) list.get(14) / sum3) * 100;
					double percentage15 = ((double) list.get(15) / sum3) * 100;

					request.setAttribute("percentage12", percentage12);
					request.setAttribute("percentage13", percentage13);
					request.setAttribute("percentage14", percentage14);
					request.setAttribute("percentage15", percentage15);

				}

				if (sum4 != 0) {
					double percentage16 = ((double) list.get(16) / sum4) * 100;
					double percentage17 = ((double) list.get(17) / sum4) * 100;
					double percentage18 = ((double) list.get(18) / sum4) * 100;
					double percentage19 = ((double) list.get(19) / sum4) * 100;

					request.setAttribute("percentage16", percentage16);
					request.setAttribute("percentage17", percentage17);
					request.setAttribute("percentage18", percentage18);
					request.setAttribute("percentage19", percentage19);

				}

				if (sum5 != 0) {
					double percentage20 = ((double) list.get(20) / sum5) * 100;
					double percentage21 = ((double) list.get(21) / sum5) * 100;
					double percentage22 = ((double) list.get(22) / sum5) * 100;
					double percentage23 = ((double) list.get(23) / sum5) * 100;

					request.setAttribute("percentage20", percentage20);
					request.setAttribute("percentage21", percentage21);
					request.setAttribute("percentage22", percentage22);
					request.setAttribute("percentage23", percentage23);

				}

				if (sum6 != 0) {
					double percentage24 = ((double) list.get(24) / sum6) * 100;
					double percentage25 = ((double) list.get(25) / sum6) * 100;
					double percentage26 = ((double) list.get(26) / sum6) * 100;
					double percentage27 = ((double) list.get(27) / sum6) * 100;

					request.setAttribute("percentage24", percentage24);
					request.setAttribute("percentage25", percentage25);
					request.setAttribute("percentage26", percentage26);
					request.setAttribute("percentage27", percentage27);

				}

			}

			// 三级
			if (projectShowcase.getCasysGrade().equals("第三级")) {
				List<Integer> list = securityGapService.getJiZhuWangLou3(pkca);

				sum0 = list.get(0) + list.get(1) + list.get(2) + list.get(3);
				sum1 = list.get(4) + list.get(5) + list.get(6) + list.get(7);
				sum2 = list.get(8) + list.get(9) + list.get(10) + list.get(11);
				sum3 = list.get(12) + list.get(13) + list.get(14)
						+ list.get(15);
				sum4 = list.get(16) + list.get(17) + list.get(18)
						+ list.get(19);
				sum5 = list.get(20) + list.get(21) + list.get(22)
						+ list.get(23);
				sum6 = list.get(24) + list.get(25) + list.get(26)
						+ list.get(27);

				request.setAttribute("num0", list.get(0));
				request.setAttribute("num1", list.get(1));
				request.setAttribute("num2", list.get(2));
				request.setAttribute("num3", list.get(3));
				request.setAttribute("sum0", sum0);

				request.setAttribute("num4", list.get(4));
				request.setAttribute("num5", list.get(5));
				request.setAttribute("num6", list.get(6));
				request.setAttribute("num7", list.get(7));
				request.setAttribute("sum1", sum1);

				request.setAttribute("num8", list.get(8));
				request.setAttribute("num9", list.get(9));
				request.setAttribute("num10", list.get(10));
				request.setAttribute("num11", list.get(11));
				request.setAttribute("sum2", sum2);

				request.setAttribute("num12", list.get(12));
				request.setAttribute("num13", list.get(13));
				request.setAttribute("num14", list.get(14));
				request.setAttribute("num15", list.get(15));
				request.setAttribute("sum3", sum3);

				request.setAttribute("num16", list.get(16));
				request.setAttribute("num17", list.get(17));
				request.setAttribute("num18", list.get(18));
				request.setAttribute("num19", list.get(19));
				request.setAttribute("sum4", sum4);

				request.setAttribute("num20", list.get(20));
				request.setAttribute("num21", list.get(21));
				request.setAttribute("num22", list.get(22));
				request.setAttribute("num23", list.get(23));
				request.setAttribute("sum5", sum5);

				request.setAttribute("num24", list.get(24));
				request.setAttribute("num25", list.get(25));
				request.setAttribute("num26", list.get(26));
				request.setAttribute("num27", list.get(27));
				request.setAttribute("sum6", sum6);
				n0 = list.get(0);
				n1 = list.get(1);
				n2 = list.get(2);
				n3 = list.get(3);
				if (sum0 != 0) {
					double percentage0 = ((double) n0 / sum0) * 100;
					double percentage1 = ((double) n1 / sum0) * 100;
					double percentage2 = ((double) n2 / sum0) * 100;
					double percentage3 = ((double) n3 / sum0) * 100;

					request.setAttribute("percentage0", percentage0);
					request.setAttribute("percentage1", percentage1);
					request.setAttribute("percentage2", percentage2);
					request.setAttribute("percentage3", percentage3);

				}
				if (sum1 != 0) {
					double percentage4 = ((double) list.get(4) / sum1) * 100;
					double percentage5 = ((double) list.get(5) / sum1) * 100;
					double percentage6 = ((double) list.get(6) / sum1) * 100;
					double percentage7 = ((double) list.get(7) / sum1) * 100;

					request.setAttribute("percentage4", percentage4);
					request.setAttribute("percentage5", percentage5);
					request.setAttribute("percentage6", percentage6);
					request.setAttribute("percentage7", percentage7);
				}

				if (sum2 != 0) {

					double percentage8 = ((double) list.get(8) / sum2) * 100;
					double percentage9 = ((double) list.get(9) / sum2) * 100;
					double percentage10 = ((double) list.get(10) / sum2) * 100;
					double percentage11 = ((double) list.get(11) / sum2) * 100;

					request.setAttribute("percentage8", percentage8);
					request.setAttribute("percentage9", percentage9);
					request.setAttribute("percentage10", percentage10);
					request.setAttribute("percentage11", percentage11);

				}

				if (sum3 != 0) {
					double percentage12 = ((double) list.get(12) / sum3) * 100;
					double percentage13 = ((double) list.get(13) / sum3) * 100;
					double percentage14 = ((double) list.get(14) / sum3) * 100;
					double percentage15 = ((double) list.get(15) / sum3) * 100;

					request.setAttribute("percentage12", percentage12);
					request.setAttribute("percentage13", percentage13);
					request.setAttribute("percentage14", percentage14);
					request.setAttribute("percentage15", percentage15);

				}

				if (sum4 != 0) {
					double percentage16 = ((double) list.get(16) / sum4) * 100;
					double percentage17 = ((double) list.get(17) / sum4) * 100;
					double percentage18 = ((double) list.get(18) / sum4) * 100;
					double percentage19 = ((double) list.get(19) / sum4) * 100;

					request.setAttribute("percentage16", percentage16);
					request.setAttribute("percentage17", percentage17);
					request.setAttribute("percentage18", percentage18);
					request.setAttribute("percentage19", percentage19);

				}

				if (sum5 != 0) {
					double percentage20 = ((double) list.get(20) / sum5) * 100;
					double percentage21 = ((double) list.get(21) / sum5) * 100;
					double percentage22 = ((double) list.get(22) / sum5) * 100;
					double percentage23 = ((double) list.get(23) / sum5) * 100;

					request.setAttribute("percentage20", percentage20);
					request.setAttribute("percentage21", percentage21);
					request.setAttribute("percentage22", percentage22);
					request.setAttribute("percentage23", percentage23);

				}

				if (sum6 != 0) {
					double percentage24 = ((double) list.get(24) / sum6) * 100;
					double percentage25 = ((double) list.get(25) / sum6) * 100;
					double percentage26 = ((double) list.get(26) / sum6) * 100;
					double percentage27 = ((double) list.get(27) / sum6) * 100;

					request.setAttribute("percentage24", percentage24);
					request.setAttribute("percentage25", percentage25);
					request.setAttribute("percentage26", percentage26);
					request.setAttribute("percentage27", percentage27);

				}

			}

			// 四级
			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list = securityGapService.getJiZhuWangLou4(pkca);

				sum0 = list.get(0) + list.get(1) + list.get(2) + list.get(3);
				sum1 = list.get(4) + list.get(5) + list.get(6) + list.get(7);
				sum2 = list.get(8) + list.get(9) + list.get(10) + list.get(11);
				sum3 = list.get(12) + list.get(13) + list.get(14)
						+ list.get(15);
				sum4 = list.get(16) + list.get(17) + list.get(18)
						+ list.get(19);
				sum5 = list.get(20) + list.get(21) + list.get(22)
						+ list.get(23);
				sum6 = list.get(24) + list.get(25) + list.get(26)
						+ list.get(27);

				request.setAttribute("num0", list.get(0));
				request.setAttribute("num1", list.get(1));
				request.setAttribute("num2", list.get(2));
				request.setAttribute("num3", list.get(3));
				request.setAttribute("sum0", sum0);

				request.setAttribute("num4", list.get(4));
				request.setAttribute("num5", list.get(5));
				request.setAttribute("num6", list.get(6));
				request.setAttribute("num7", list.get(7));
				request.setAttribute("sum1", sum1);

				request.setAttribute("num8", list.get(8));
				request.setAttribute("num9", list.get(9));
				request.setAttribute("num10", list.get(10));
				request.setAttribute("num11", list.get(11));
				request.setAttribute("sum2", sum2);

				request.setAttribute("num12", list.get(12));
				request.setAttribute("num13", list.get(13));
				request.setAttribute("num14", list.get(14));
				request.setAttribute("num15", list.get(15));
				request.setAttribute("sum3", sum3);

				request.setAttribute("num16", list.get(16));
				request.setAttribute("num17", list.get(17));
				request.setAttribute("num18", list.get(18));
				request.setAttribute("num19", list.get(19));
				request.setAttribute("sum4", sum4);

				request.setAttribute("num20", list.get(20));
				request.setAttribute("num21", list.get(21));
				request.setAttribute("num22", list.get(22));
				request.setAttribute("num23", list.get(23));
				request.setAttribute("sum5", sum5);

				request.setAttribute("num24", list.get(24));
				request.setAttribute("num25", list.get(25));
				request.setAttribute("num26", list.get(26));
				request.setAttribute("num27", list.get(27));
				request.setAttribute("sum6", sum6);
				n0 = list.get(0);
				n1 = list.get(1);
				n2 = list.get(2);
				n3 = list.get(3);
				if (sum0 != 0) {
					double percentage0 = ((double) n0 / sum0) * 100;
					double percentage1 = ((double) n1 / sum0) * 100;
					double percentage2 = ((double) n2 / sum0) * 100;
					double percentage3 = ((double) n3 / sum0) * 100;

					request.setAttribute("percentage0", percentage0);
					request.setAttribute("percentage1", percentage1);
					request.setAttribute("percentage2", percentage2);
					request.setAttribute("percentage3", percentage3);

				}
				if (sum1 != 0) {
					double percentage4 = ((double) list.get(4) / sum1) * 100;
					double percentage5 = ((double) list.get(5) / sum1) * 100;
					double percentage6 = ((double) list.get(6) / sum1) * 100;
					double percentage7 = ((double) list.get(7) / sum1) * 100;

					request.setAttribute("percentage4", percentage4);
					request.setAttribute("percentage5", percentage5);
					request.setAttribute("percentage6", percentage6);
					request.setAttribute("percentage7", percentage7);
				}

				if (sum2 != 0) {

					double percentage8 = ((double) list.get(8) / sum2) * 100;
					double percentage9 = ((double) list.get(9) / sum2) * 100;
					double percentage10 = ((double) list.get(10) / sum2) * 100;
					double percentage11 = ((double) list.get(11) / sum2) * 100;

					request.setAttribute("percentage8", percentage8);
					request.setAttribute("percentage9", percentage9);
					request.setAttribute("percentage10", percentage10);
					request.setAttribute("percentage11", percentage11);

				}

				if (sum3 != 0) {
					double percentage12 = ((double) list.get(12) / sum3) * 100;
					double percentage13 = ((double) list.get(13) / sum3) * 100;
					double percentage14 = ((double) list.get(14) / sum3) * 100;
					double percentage15 = ((double) list.get(15) / sum3) * 100;

					request.setAttribute("percentage12", percentage12);
					request.setAttribute("percentage13", percentage13);
					request.setAttribute("percentage14", percentage14);
					request.setAttribute("percentage15", percentage15);

				}

				if (sum4 != 0) {
					double percentage16 = ((double) list.get(16) / sum4) * 100;
					double percentage17 = ((double) list.get(17) / sum4) * 100;
					double percentage18 = ((double) list.get(18) / sum4) * 100;
					double percentage19 = ((double) list.get(19) / sum4) * 100;

					request.setAttribute("percentage16", percentage16);
					request.setAttribute("percentage17", percentage17);
					request.setAttribute("percentage18", percentage18);
					request.setAttribute("percentage19", percentage19);

				}

				if (sum5 != 0) {
					double percentage20 = ((double) list.get(20) / sum5) * 100;
					double percentage21 = ((double) list.get(21) / sum5) * 100;
					double percentage22 = ((double) list.get(22) / sum5) * 100;
					double percentage23 = ((double) list.get(23) / sum5) * 100;

					request.setAttribute("percentage20", percentage20);
					request.setAttribute("percentage21", percentage21);
					request.setAttribute("percentage22", percentage22);
					request.setAttribute("percentage23", percentage23);

				}

				if (sum6 != 0) {
					double percentage24 = ((double) list.get(24) / sum6) * 100;
					double percentage25 = ((double) list.get(25) / sum6) * 100;
					double percentage26 = ((double) list.get(26) / sum6) * 100;
					double percentage27 = ((double) list.get(27) / sum6) * 100;

					request.setAttribute("percentage24", percentage24);
					request.setAttribute("percentage25", percentage25);
					request.setAttribute("percentage26", percentage26);
					request.setAttribute("percentage27", percentage27);

				}

			}

		}

		listproject = securityGapService.getProjectShowcase();
		request.setAttribute("listss", listproject);

		return SUCCESS;
	}

	// 下拉框选项变化时显示的值

	public String getSecurityGapListC() throws Exception {
		HttpServletRequest request = super.getRequest();
		
		DecimalFormat df1 = new DecimalFormat("00.00");
		// request.setCharacterEncoding("GBK");
		String pkca1 = request.getParameter("pkca");
		int pkca = Integer.parseInt(pkca1);
		// String caName = new String(request.getParameter("caName").getBytes(
		// "ISO-8859-1"), "UTF-8");
		List<ProjectShowcase> listproject2 = securityGapService
				.getProjectShowcaseByName(pkca);
		for (ProjectShowcase projectShowcase : listproject2) {

			// 三级
			if (projectShowcase.getCasysGrade().equals("第三级")) {
				List<Integer> list3 = securityGapService.getJiZhuWangLou3(pkca);

				int sum00 = list3.get(0) + list3.get(1) + list3.get(2)
						+ list3.get(3);
				int sum11 = list3.get(4) + list3.get(5) + list3.get(6)
						+ list3.get(7);
				int sum22 = list3.get(8) + list3.get(9) + list3.get(10)
						+ list3.get(11);
				int sum33 = list3.get(12) + list3.get(13) + list3.get(14)
						+ list3.get(15);
				int sum44 = list3.get(16) + list3.get(17) + list3.get(18)
						+ list3.get(19);
				int sum55 = list3.get(20) + list3.get(21) + list3.get(22)
						+ list3.get(23);
				int sum66 = list3.get(24) + list3.get(25) + list3.get(26)
						+ list3.get(27);

				request.setAttribute("num0", list3.get(0));
				request.setAttribute("num1", list3.get(1));
				request.setAttribute("num2", list3.get(2));
				request.setAttribute("num3", list3.get(3));
				request.setAttribute("sum0", sum00);

				request.setAttribute("num4", list3.get(4));
				request.setAttribute("num5", list3.get(5));
				request.setAttribute("num6", list3.get(6));
				request.setAttribute("num7", list3.get(7));
				request.setAttribute("sum1", sum11);

				request.setAttribute("num8", list3.get(8));
				request.setAttribute("num9", list3.get(9));
				request.setAttribute("num10", list3.get(10));
				request.setAttribute("num11", list3.get(11));
				request.setAttribute("sum2", sum22);

				request.setAttribute("num12", list3.get(12));
				request.setAttribute("num13", list3.get(13));
				request.setAttribute("num14", list3.get(14));
				request.setAttribute("num15", list3.get(15));
				request.setAttribute("sum3", sum33);

				request.setAttribute("num16", list3.get(16));
				request.setAttribute("num17", list3.get(17));
				request.setAttribute("num18", list3.get(18));
				request.setAttribute("num19", list3.get(19));
				request.setAttribute("sum4", sum44);

				request.setAttribute("num20", list3.get(20));
				request.setAttribute("num21", list3.get(21));
				request.setAttribute("num22", list3.get(22));
				request.setAttribute("num23", list3.get(23));
				request.setAttribute("sum5", sum55);

				request.setAttribute("num24", list3.get(24));
				request.setAttribute("num25", list3.get(25));
				request.setAttribute("num26", list3.get(26));
				request.setAttribute("num27", list3.get(27));
				request.setAttribute("sum6", sum66);
				if (sum00 != 0) {
					double percentage00 = ((double) list3.get(0) / sum00) * 100;
					double percentage11 = ((double) list3.get(1) / sum00) * 100;
					double percentage22 = ((double) list3.get(2) / sum00) * 100;
					double percentage33 = ((double) list3.get(3) / sum00) * 100;

					request.setAttribute("percentage0", df1.format(percentage00));
					request.setAttribute("percentage1", df1.format(percentage11));
					request.setAttribute("percentage2", df1.format(percentage22));
					request.setAttribute("percentage3", df1.format(percentage33));

				}
				if (sum11 != 0) {
					double percentage44 = ((double) list3.get(4) / sum11) * 100;
					double percentage55 = ((double) list3.get(5) / sum11) * 100;
					double percentage66 = ((double) list3.get(6) / sum11) * 100;
					double percentage77 = ((double) list3.get(7) / sum11) * 100;

					request.setAttribute("percentage4", df1.format(percentage44));
					request.setAttribute("percentage5", df1.format(percentage55));
					request.setAttribute("percentage6", df1.format(percentage66));
					request.setAttribute("percentage7", df1.format(percentage77));
				}

				if (sum22 != 0) {

					double percentage88 = ((double) list3.get(8) / sum22) * 100;
					double percentage99 = ((double) list3.get(9) / sum22) * 100;
					double percentage1010 = ((double) list3.get(10) / sum22) * 100;
					double percentage1111 = ((double) list3.get(11) / sum22) * 100;

					request.setAttribute("percentage8", df1.format(percentage88));
					request.setAttribute("percentage9", df1.format(percentage99));
					request.setAttribute("percentage10", df1.format(percentage1010));
					request.setAttribute("percentage11", df1.format(percentage1111));

				}

				if (sum33 != 0) {
					double percentage123 = ((double) list3.get(12) / sum33) * 100;
					double percentage133 = ((double) list3.get(13) / sum33) * 100;
					double percentage143 = ((double) list3.get(14) / sum33) * 100;
					double percentage153 = ((double) list3.get(15) / sum33) * 100;

					request.setAttribute("percentage12", df1.format(percentage123));
					request.setAttribute("percentage13", df1.format(percentage133));
					request.setAttribute("percentage14", df1.format(percentage143));
					request.setAttribute("percentage15", df1.format(percentage153));

				}

				if (sum44 != 0) {
					double percentage163 = ((double) list3.get(16) / sum44) * 100;
					double percentage173 = ((double) list3.get(17) / sum44) * 100;
					double percentage183 = ((double) list3.get(18) / sum44) * 100;
					double percentage193 = ((double) list3.get(19) / sum44) * 100;

					request.setAttribute("percentage16", df1.format(percentage163));
					request.setAttribute("percentage17", df1.format(percentage173));
					request.setAttribute("percentage18", df1.format(percentage183));
					request.setAttribute("percentage19", df1.format(percentage193));

				}

				if (sum55 != 0) {
					double percentage203 = ((double) list3.get(20) / sum55) * 100;
					double percentage213 = ((double) list3.get(21) / sum55) * 100;
					double percentage223 = ((double) list3.get(22) / sum55) * 100;
					double percentage233 = ((double) list3.get(23) / sum55) * 100;

					request.setAttribute("percentage20", df1.format(percentage203));
					request.setAttribute("percentage21", df1.format(percentage213));
					request.setAttribute("percentage22", df1.format(percentage223));
					request.setAttribute("percentage23", df1.format(percentage233));

				}

				if (sum66 != 0) {
					double percentage243 = ((double) list3.get(24) / sum66) * 100;
					double percentage253 = ((double) list3.get(25) / sum66) * 100;
					double percentage263 = ((double) list3.get(26) / sum66) * 100;
					double percentage273 = ((double) list3.get(27) / sum66) * 100;

					request.setAttribute("percentage24", df1.format(percentage243));
					request.setAttribute("percentage25", df1.format(percentage253));
					request.setAttribute("percentage26",df1.format(percentage263));
					request.setAttribute("percentage27", df1.format(percentage273));

				}

			}

			// 四级
			if (projectShowcase.getCasysGrade().equals("第四级")) {
				List<Integer> list = securityGapService.getJiZhuWangLou4(pkca);

				int sum044 = list.get(0) + list.get(1) + list.get(2)
						+ list.get(3);
				int sum144 = list.get(4) + list.get(5) + list.get(6)
						+ list.get(7);
				int sum244 = list.get(8) + list.get(9) + list.get(10)
						+ list.get(11);
				int sum344 = list.get(12) + list.get(13) + list.get(14)
						+ list.get(15);
				int sum444 = list.get(16) + list.get(17) + list.get(18)
						+ list.get(19);
				int sum544 = list.get(20) + list.get(21) + list.get(22)
						+ list.get(23);
				int sum644 = list.get(24) + list.get(25) + list.get(26)
						+ list.get(27);

				request.setAttribute("num0", list.get(0));
				request.setAttribute("num1", list.get(1));
				request.setAttribute("num2", list.get(2));
				request.setAttribute("num3", list.get(3));
				request.setAttribute("sum0", sum044);

				request.setAttribute("num4", list.get(4));
				request.setAttribute("num5", list.get(5));
				request.setAttribute("num6", list.get(6));
				request.setAttribute("num7", list.get(7));
				request.setAttribute("sum1", sum144);

				request.setAttribute("num8", list.get(8));
				request.setAttribute("num9", list.get(9));
				request.setAttribute("num10", list.get(10));
				request.setAttribute("num11", list.get(11));
				request.setAttribute("sum2", sum244);

				request.setAttribute("num12", list.get(12));
				request.setAttribute("num13", list.get(13));
				request.setAttribute("num14", list.get(14));
				request.setAttribute("num15", list.get(15));
				request.setAttribute("sum3", sum344);

				request.setAttribute("num16", list.get(16));
				request.setAttribute("num17", list.get(17));
				request.setAttribute("num18", list.get(18));
				request.setAttribute("num19", list.get(19));
				request.setAttribute("sum4", sum444);

				request.setAttribute("num20", list.get(20));
				request.setAttribute("num21", list.get(21));
				request.setAttribute("num22", list.get(22));
				request.setAttribute("num23", list.get(23));
				request.setAttribute("sum5", sum544);

				request.setAttribute("num24", list.get(24));
				request.setAttribute("num25", list.get(25));
				request.setAttribute("num26", list.get(26));
				request.setAttribute("num27", list.get(27));
				request.setAttribute("sum6", sum644);
				if (sum044 != 0) {
					double percentage044 = ((double) list.get(0) / sum044) * 100;
					double percentage144 = ((double) list.get(1) / sum044) * 100;
					double percentage244 = ((double) list.get(2) / sum044) * 100;
					double percentage344 = ((double) list.get(3) / sum044) * 100;

					request.setAttribute("percentage0", df1.format(percentage044));
					request.setAttribute("percentage1", df1.format(percentage144));
					request.setAttribute("percentage2", df1.format(percentage244));
					request.setAttribute("percentage3", df1.format(percentage344));

				}
				if (sum144 != 0) {
					double percentage444 = ((double) list.get(4) / sum144) * 100;
					double percentage544 = ((double) list.get(5) / sum144) * 100;
					double percentage644 = ((double) list.get(6) / sum144) * 100;
					double percentage744 = ((double) list.get(7) / sum144) * 100;

					request.setAttribute("percentage4", df1.format(percentage444));
					request.setAttribute("percentage5", df1.format(percentage544));
					request.setAttribute("percentage6", df1.format(percentage644));
					request.setAttribute("percentage7", df1.format(percentage744));
				}

				if (sum244 != 0) {

					double percentage844 = ((double) list.get(8) / sum244) * 100;
					double percentage944 = ((double) list.get(9) / sum244) * 100;
					double percentage1044 = ((double) list.get(10) / sum244) * 100;
					double percentage1144 = ((double) list.get(11) / sum244) * 100;

					request.setAttribute("percentage8", df1.format(percentage844));
					request.setAttribute("percentage9", df1.format(percentage944));
					request.setAttribute("percentage10", df1.format(percentage1044));
					request.setAttribute("percentage11", df1.format(percentage1144));

				}

				if (sum344 != 0) {
					double percentage1244 = ((double) list.get(12) / sum344) * 100;
					double percentage1344 = ((double) list.get(13) / sum344) * 100;
					double percentage1444 = ((double) list.get(14) / sum344) * 100;
					double percentage1544 = ((double) list.get(15) / sum344) * 100;

					request.setAttribute("percentage12", df1.format(percentage1244));
					request.setAttribute("percentage13", df1.format(percentage1344));
					request.setAttribute("percentage14", df1.format(percentage1444));
					request.setAttribute("percentage15", df1.format(percentage1544));

				}

				if (sum444 != 0) {
					double percentage1644 = ((double) list.get(16) / sum444) * 100;
					double percentage1744 = ((double) list.get(17) / sum444) * 100;
					double percentage1844 = ((double) list.get(18) / sum444) * 100;
					double percentage1944 = ((double) list.get(19) / sum444) * 100;

					request.setAttribute("percentage16", df1.format(percentage1644));
					request.setAttribute("percentage17", df1.format(percentage1744));
					request.setAttribute("percentage18", df1.format(percentage1844));
					request.setAttribute("percentage19", df1.format(percentage1944));

				}

				if (sum544 != 0) {
					double percentage2044 = ((double) list.get(20) / sum544) * 100;
					double percentage2144 = ((double) list.get(21) / sum544) * 100;
					double percentage2244 = ((double) list.get(22) / sum544) * 100;
					double percentage2344 = ((double) list.get(23) / sum544) * 100;

					request.setAttribute("percentage20", df1.format(percentage2044));
					request.setAttribute("percentage21", df1.format(percentage2144));
					request.setAttribute("percentage22", df1.format(percentage2244));
					request.setAttribute("percentage23", df1.format(percentage2344));

				}

				if (sum644 != 0) {
					double percentage2444 = ((double) list.get(24) / sum644) * 100;
					double percentage2544 = ((double) list.get(25) / sum644) * 100;
					double percentage2644 = ((double) list.get(26) / sum644) * 100;
					double percentage2744 = ((double) list.get(27) / sum644) * 100;

					request.setAttribute("percentage24", df1.format(percentage2444));
					request.setAttribute("percentage25", df1.format(percentage2544));
					request.setAttribute("percentage26", df1.format(percentage2644));
					request.setAttribute("percentage27", df1.format(percentage2744));

				}

			}

			if (projectShowcase.getCasysGrade().equals("第二级")) {
				List<Integer> list2 = securityGapService.getJiZhuWangLou2(pkca);

				int sum0 = list2.get(0) + list2.get(1) + list2.get(2)
						+ list2.get(3);
				int sum1 = list2.get(4) + list2.get(5) + list2.get(6)
						+ list2.get(7);
				int sum2 = list2.get(8) + list2.get(9) + list2.get(10)
						+ list2.get(11);
				int sum3 = list2.get(12) + list2.get(13) + list2.get(14)
						+ list2.get(15);
				int sum4 = list2.get(16) + list2.get(17) + list2.get(18)
						+ list2.get(19);
				int sum5 = list2.get(20) + list2.get(21) + list2.get(22)
						+ list2.get(23);
				int sum6 = list2.get(24) + list2.get(25) + list2.get(26)
						+ list2.get(27);

				request.setAttribute("num0", list2.get(0));
				request.setAttribute("num1", list2.get(1));
				request.setAttribute("num2", list2.get(2));
				request.setAttribute("num3", list2.get(3));
				request.setAttribute("sum0", sum0);

				request.setAttribute("num4", list2.get(4));
				request.setAttribute("num5", list2.get(5));
				request.setAttribute("num6", list2.get(6));
				request.setAttribute("num7", list2.get(7));
				request.setAttribute("sum1", sum1);

				request.setAttribute("num8", list2.get(8));
				request.setAttribute("num9", list2.get(9));
				request.setAttribute("num10", list2.get(10));
				request.setAttribute("num11", list2.get(11));
				request.setAttribute("sum2", sum2);

				request.setAttribute("num12", list2.get(12));
				request.setAttribute("num13", list2.get(13));
				request.setAttribute("num14", list2.get(14));
				request.setAttribute("num15", list2.get(15));
				request.setAttribute("sum3", sum3);

				request.setAttribute("num16", list2.get(16));
				request.setAttribute("num17", list2.get(17));
				request.setAttribute("num18", list2.get(18));
				request.setAttribute("num19", list2.get(19));
				request.setAttribute("sum4", sum4);

				request.setAttribute("num20", list2.get(20));
				request.setAttribute("num21", list2.get(21));
				request.setAttribute("num22", list2.get(22));
				request.setAttribute("num23", list2.get(23));
				request.setAttribute("sum5", sum5);

				request.setAttribute("num24", list2.get(24));
				request.setAttribute("num25", list2.get(25));
				request.setAttribute("num26", list2.get(26));
				request.setAttribute("num27", list2.get(27));
				request.setAttribute("sum6", sum6);
				if (sum0 != 0) {
					double percentage0 = ((double) list2.get(0) / sum0) * 100;
					double percentage1 = ((double) list2.get(1) / sum0) * 100;
					double percentage2 = ((double) list2.get(2) / sum0) * 100;
					double percentage3 = ((double) list2.get(3) / sum0) * 100;

					request.setAttribute("percentage0", df1.format(percentage0));
					request.setAttribute("percentage1", df1.format(percentage1));
					request.setAttribute("percentage2", df1.format(percentage2));
					request.setAttribute("percentage3", df1.format(percentage3));

				}
				if (sum1 != 0) {
					double percentage4 = ((double) list2.get(4) / sum1) * 100;
					double percentage5 = ((double) list2.get(5) / sum1) * 100;
					double percentage6 = ((double) list2.get(6) / sum1) * 100;
					double percentage7 = ((double) list2.get(7) / sum1) * 100;

					request.setAttribute("percentage4", df1.format(percentage4));
					request.setAttribute("percentage5", df1.format(percentage5));
					request.setAttribute("percentage6", df1.format(percentage6));
					request.setAttribute("percentage7", df1.format(percentage7));
				}

				if (sum2 != 0) {

					double percentage8 = ((double) list2.get(8) / sum2) * 100;
					double percentage9 = ((double) list2.get(9) / sum2) * 100;
					double percentage10 = ((double) list2.get(10) / sum2) * 100;
					double percentage11 = ((double) list2.get(11) / sum2) * 100;

					request.setAttribute("percentage8", df1.format(percentage8));
					request.setAttribute("percentage9", df1.format(percentage9));
					request.setAttribute("percentage10", df1.format(percentage10));
					request.setAttribute("percentage11", df1.format(percentage11));

				}

				if (sum3 != 0) {
					double percentage12 = ((double) list2.get(12) / sum3) * 100;
					double percentage13 = ((double) list2.get(13) / sum3) * 100;
					double percentage14 = ((double) list2.get(14) / sum3) * 100;
					double percentage15 = ((double) list2.get(15) / sum3) * 100;

					request.setAttribute("percentage12", df1.format(percentage12));
					request.setAttribute("percentage13", df1.format(percentage13));
					request.setAttribute("percentage14", df1.format(percentage14));
					request.setAttribute("percentage15", df1.format(percentage15));

				}

				if (sum4 != 0) {
					double percentage16 = ((double) list2.get(16) / sum4) * 100;
					double percentage17 = ((double) list2.get(17) / sum4) * 100;
					double percentage18 = ((double) list2.get(18) / sum4) * 100;
					double percentage19 = ((double) list2.get(19) / sum4) * 100;

					request.setAttribute("percentage16", df1.format(percentage16));
					request.setAttribute("percentage17", df1.format(percentage17));
					request.setAttribute("percentage18", df1.format(percentage18));
					request.setAttribute("percentage19", df1.format(percentage19));

				}

				if (sum5 != 0) {
					double percentage20 = ((double) list2.get(20) / sum5) * 100;
					double percentage21 = ((double) list2.get(21) / sum5) * 100;
					double percentage22 = ((double) list2.get(22) / sum5) * 100;
					double percentage23 = ((double) list2.get(23) / sum5) * 100;

					request.setAttribute("percentage20", df1.format(percentage20));
					request.setAttribute("percentage21", df1.format(percentage21));
					request.setAttribute("percentage22", df1.format(percentage22));
					request.setAttribute("percentage23", df1.format(percentage23));

				}

				if (sum6 != 0) {
					double percentage24 = ((double) list2.get(24) / sum6) * 100;
					double percentage25 = ((double) list2.get(25) / sum6) * 100;
					double percentage26 = ((double) list2.get(26) / sum6) * 100;
					double percentage27 = ((double) list2.get(27) / sum6) * 100;

					request.setAttribute("percentage24", df1.format(percentage24));
					request.setAttribute("percentage25", df1.format(percentage25));
					request.setAttribute("percentage26", df1.format(percentage26));
					request.setAttribute("percentage27", df1.format(percentage27));

				}

			}

		}
		listproject = securityGapService.getProjectShowcase();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (ProjectShowcase projectShowcase : listproject) {
			map.put(projectShowcase.getPkca(), projectShowcase.getCaName());
		}

		request.setAttribute("map", map);
		request.setAttribute("pkca", pkca);

		return SUCCESS;
	}

	public String getProjectShowcase() {

		String result ="";
		listproject = securityGapService.getProjectShowcase();
		HttpServletRequest request = super.getRequest();
		request.setAttribute("listss", listproject);
		if(listproject.size()==0){
			result = NONE;
		}else{
			result=SUCCESS;
		}
		return result;

	}

}
