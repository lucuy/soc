package com.util.load.data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class CreateDataBase {

	public static void main(String[] args) throws Exception {
		// 折线图
		CategoryDataset dataset = CreateJFreeChartLine.createDataset();
		JFreeChart freeChart = CreateJFreeChartLine.createChart(dataset);

		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

		dataset2.addValue(50, "计划", "基础网络安全");

		dataset2.addValue(30, "计划", "边界安全");
		dataset2.addValue(40, "计划", "终端系统安全");
		dataset2.addValue(20, "计划", "服务器端系统安全");
		dataset2.addValue(10, "计划", "应用安全");
		dataset2.addValue(60, "计划", "数据安全与备份恢复");
		dataset2.addValue(10, "计划", "安全管理中心");
		String fileName = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String ss = sdf.format(new Date());
		for (int i = 0; i < 2; i++) {
			ss += String.valueOf((int) (java.lang.Math.random() * 10));
		}
		String uploadPath = "f:/dataTeamCoal/sourceFile/";
		File file = new File(uploadPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}

		String descPath = "f:/dataTeamCoal/descFile/";
		File defile = new File(descPath);
		if (!defile.isDirectory()) {
			defile.mkdirs();
		}

		String fileNameFile = "TeamCoal" + ss + ".png";
		String fileNameTitle = "TeamCoal" + ss + ".png";
		String descNameFile = "TeamCoal" + ss + ".doc";
		String descName = descPath + descNameFile;

		String XiTongName = "TeamCoal" + ss;
		fileName = uploadPath + "TeamCoal" + ss + ".png";

		File filenew = new File(fileName);

		String fileLineName = uploadPath + "TeamCoalLine" + ss + ".png";

		CreateJFreeChartLine.saveAsFile(freeChart, fileLineName, 600, 400);

		KpiCityCompanyChart.setImageRange(500, 300);
		JFreeChart chart = KpiCityCompanyChart.createChart(XiTongName, "", "",
				dataset2);

		ChartUtilities.saveChartAsPNG(filenew, chart, 400, 300);// 把报表保存为文件

		WordUtil wordUtil = new WordUtil();

		wordUtil.CreateWordFile(descName, "TeamCoal", XiTongName, "我爱Java",
				fileName, fileLineName);
	}
}
