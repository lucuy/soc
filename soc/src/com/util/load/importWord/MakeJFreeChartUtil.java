package com.util.load.importWord;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import com.compliance.model.cpManage.gapReportDocument.ExportWord;
import com.compliance.model.cpManage.gapStatisticsUnit.GapStatisticsUnit;
import com.compliance.model.cpManage.generalPhysical.GeneralPhysical;
import com.compliance.model.cpManage.securityTable.SecurityTable;
import com.util.MySpiderWebPlot;

public class MakeJFreeChartUtil {

	// 柱状图
	public static JFreeChart getSystemInfosFitBarChart(
			List<GeneralPhysical> getSystemLastProjects) {
		DefaultCategoryDataset categorydataset = new DefaultCategoryDataset();
		DecimalFormat f = new DecimalFormat("#.##");

		for (GeneralPhysical item : getSystemLastProjects) {

			categorydataset.addValue(Double.parseDouble(item
					.getTotalPercentage() == null ? "0" : item
					.getTotalPercentage()), "总体符合度%", item.getSysname());
			categorydataset.addValue(
					Double.parseDouble(item.getPhysicalPercentage()), "物理符合度%",
					item.getSysname());
			categorydataset.addValue(
					Double.parseDouble(item.getTechnologyPercentage()),
					"技术符合度%", item.getSysname());
			categorydataset.addValue(
					Double.parseDouble(item.getManagementPercentage()),
					"管理符合度%", item.getSysname());
		}

		JFreeChart jfreechart = ChartFactory.createBarChart3D("信息系统符合度柱状图", "",
				"", categorydataset, PlotOrientation.VERTICAL, true, true,
				false);

		Font font = new Font("黑体", 1, 20);
		TextTitle title = new TextTitle("信息系统符合度百分比");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);
		jfreechart.getLegend().setItemFont(new Font("宋体", 0, 12));

		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		CategoryPlot categoryplot = jfreechart.getCategoryPlot();

		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setTickLabelFont(new Font("黑体", 0, 12));
		numberaxis.setTickLabelPaint(new Color(95, 73, 122));

		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		domainAxis.setTickLabelFont(new Font("黑体", 0, 12));
		domainAxis.setTickLabelPaint(new Color(95, 73, 122));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setDomainGridlinePaint(Color.BLUE);
		categoryplot.setForegroundAlpha(0.9F);

		BarRenderer3D barRenderer3D = new BarRenderer3D();
		barRenderer3D
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3D.setBaseItemLabelsVisible(true);
		barRenderer3D.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		barRenderer3D.setItemLabelAnchorOffset(10.0D);

		barRenderer3D.setItemMargin(0.1D);
		barRenderer3D.setItemLabelPaint(new Color(95, 73, 122));
		barRenderer3D.setMinimumBarLength(0.01D);
		barRenderer3D.setMaximumBarWidth(0.05D);

		categoryplot.setRenderer(barRenderer3D);

		return jfreechart;
	}

	// /饼图
	public static JFreeChart getGapanalysisTotalPieJFreeChart(
			ExportWord listGapStatisticsUnit) {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		JFreeChart jfreechart = ChartFactory.createPieChart3D("符合度百分比饼图",
				pieDataset, true, true, false);

		Font font = new Font("黑体", 1, 20);
		TextTitle title = new TextTitle("符合度百分比饼图");
		title.setFont(font);

		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);

		LegendTitle legendTitle = jfreechart.getLegend();
		legendTitle.setItemFont(new Font("微软雅黑", 0, 14));
		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		PiePlot3D piePlot = (PiePlot3D) jfreechart.getPlot();

		DecimalFormat f = new DecimalFormat("#.##");
		pieDataset.setValue("符合", listGapStatisticsUnit.getFuhesum());
		piePlot.setSectionPaint("符合", Color.decode("#00ff00"));

		pieDataset.setValue("部分符合", listGapStatisticsUnit.getBufenfuhesum());
		piePlot.setSectionPaint("部分符合", Color.decode("#0000ff"));

		pieDataset.setValue("不符合", listGapStatisticsUnit.getBufuhesum());
		piePlot.setSectionPaint("不符合", Color.decode("#FF0000"));
		piePlot.setOutlineVisible(false);
		piePlot.setSimpleLabels(false);
		piePlot.setDarkerSides(true);
		piePlot.setCircular(false);
		piePlot.setLabelGap(0.02D);

		piePlot.setStartAngle(0.0D);
		piePlot.setIgnoreNullValues(true);
		piePlot.setIgnoreZeroValues(true);

		piePlot.setLabelPaint(new Color(255, 255, 255));
		font = new Font("黑体", 0, 12);
		piePlot.setLabelFont(font);
		piePlot.setLabelOutlinePaint(Color.BLACK);
		piePlot.setLabelBackgroundPaint(new Color(95, 73, 122));
		piePlot.setLabelShadowPaint(new Color(64, 49, 82));

		return jfreechart;
	}

	// /饼图
	public static JFreeChart getGapanalysisTotalPieJFreeChart(
			GapStatisticsUnit listGapStatisticsUnit) {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		JFreeChart jfreechart = ChartFactory.createPieChart3D("符合度百分比饼图",
				pieDataset, true, true, false);

		Font font = new Font("黑体", 1, 20);
		TextTitle title = new TextTitle("符合度百分比饼图");
		title.setFont(font);

		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);

		LegendTitle legendTitle = jfreechart.getLegend();
		legendTitle.setItemFont(new Font("微软雅黑", 0, 14));
		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		PiePlot3D piePlot = (PiePlot3D) jfreechart.getPlot();

		DecimalFormat f = new DecimalFormat("#.##");
		pieDataset.setValue("符合", listGapStatisticsUnit.getFuhesum());
		piePlot.setSectionPaint("符合", Color.decode("#00ff00"));

		pieDataset.setValue("部分符合", listGapStatisticsUnit.getBufenfuhesum());
		piePlot.setSectionPaint("部分符合", Color.decode("#0000ff"));

		pieDataset.setValue("不符合", listGapStatisticsUnit.getBufuhesum());
		piePlot.setSectionPaint("不符合", Color.decode("#FF0000"));
		piePlot.setOutlineVisible(false);
		piePlot.setSimpleLabels(false);
		piePlot.setDarkerSides(true);
		piePlot.setCircular(false);
		piePlot.setLabelGap(0.02D);

		piePlot.setStartAngle(0.0D);
		piePlot.setIgnoreNullValues(true);
		piePlot.setIgnoreZeroValues(true);

		piePlot.setLabelPaint(new Color(255, 255, 255));
		font = new Font("黑体", 0, 12);
		piePlot.setLabelFont(font);
		piePlot.setLabelOutlinePaint(Color.BLACK);
		piePlot.setLabelBackgroundPaint(new Color(95, 73, 122));
		piePlot.setLabelShadowPaint(new Color(64, 49, 82));

		return jfreechart;
	}

	// 雷纳图
	public static JFreeChart getSystemInfoRadarJFreeChart(
			GapStatisticsUnit listGapStatisticsUnit) {
		DecimalFormat f = new DecimalFormat("#.##");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		double persentVale = new Double(listGapStatisticsUnit.getFuhedu())
				.doubleValue();
		dataset.addValue(persentVale, " ", listGapStatisticsUnit.getJichu()
				+ "：" + persentVale);

		double persentVale1 = new Double(
				listGapStatisticsUnit.getFuhedubianji()).doubleValue();
		dataset.addValue(persentVale1, " ", listGapStatisticsUnit.getBianji()
				+ "：" + persentVale1);

		double persentVale2 = new Double(
				listGapStatisticsUnit.getFuheduzhongduan()).doubleValue();
		dataset.addValue(persentVale2, " ",
				listGapStatisticsUnit.getZhongduan() + "：" + persentVale2);

		double persentVale3 = new Double(listGapStatisticsUnit.getFuhedufuwu())
				.doubleValue();
		dataset.addValue(persentVale3, " ", listGapStatisticsUnit.getFuwu()
				+ "：" + persentVale3);

		double persentVale4 = new Double(
				listGapStatisticsUnit.getFuheduyingyong()).doubleValue();
		dataset.addValue(persentVale4, " ",
				listGapStatisticsUnit.getYingyong() + "：" + persentVale4);

		double persentVale5 = new Double(listGapStatisticsUnit.getFuhedushuju())
				.doubleValue();
		dataset.addValue(persentVale5, " ", listGapStatisticsUnit.getShuju()
				+ "：" + persentVale5);

		double persentVale6 = new Double(
				listGapStatisticsUnit.getFuheduanquan()).doubleValue();
		dataset.addValue(persentVale6, " ", listGapStatisticsUnit.getAnquan()
				+ "：" + persentVale6);

		double persentVale7 = new Double(
				listGapStatisticsUnit.getFuhedutywuli()).doubleValue();
		dataset.addValue(persentVale7, " ", listGapStatisticsUnit.getTywuli()
				+ "：" + persentVale7);

		double persentVale8 = new Double(
				listGapStatisticsUnit.getFuhedutyguanli()).doubleValue();
		dataset.addValue(persentVale8, " ",
				listGapStatisticsUnit.getTyguanli() + "：" + persentVale8);

		Font font = new Font("黑体", 1, 20);
		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		JFreeChart jfreechart = new JFreeChart("符合度雷达图", font, spiderwebplot,
				true);

		font = new Font("黑体", 1, 20);
		TextTitle title = new TextTitle("符合度雷达图");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);
		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		SpiderWebPlot piePlot = (SpiderWebPlot) jfreechart.getPlot();
		piePlot.setSeriesPaint(new Color(255, 192, 0));

		piePlot.setStartAngle(90.0D);
		font = new Font("黑体", 0, 14);
		piePlot.setLabelFont(font);
		piePlot.setLabelPaint(new Color(95, 73, 122));

		return jfreechart;
	}

	// 雷纳图
	public static JFreeChart getSystemInfoRadarJFreeChart(
			ExportWord listGapStatisticsUnit) {
		DecimalFormat f = new DecimalFormat("#.##");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		double persentVale = new Double(listGapStatisticsUnit.getFuhedu())
				.doubleValue();
		dataset.addValue(persentVale, "符合率", listGapStatisticsUnit.getJichu()
				+ "：" + persentVale);

		double persentVale1 = new Double(
				listGapStatisticsUnit.getFuhedubianji()).doubleValue();
		dataset.addValue(persentVale1, "符合率", listGapStatisticsUnit.getBianji()
				+ "：" + persentVale1);

		double persentVale2 = new Double(
				listGapStatisticsUnit.getFuheduzhongduan()).doubleValue();
		dataset.addValue(persentVale2, "符合率",
				listGapStatisticsUnit.getZhongduan() + "：" + persentVale2);

		double persentVale3 = new Double(listGapStatisticsUnit.getFuhedufuwu())
				.doubleValue();
		dataset.addValue(persentVale3, "符合率", listGapStatisticsUnit.getFuwu()
				+ "：" + persentVale3);

		double persentVale4 = new Double(
				listGapStatisticsUnit.getFuheduyingyong()).doubleValue();
		dataset.addValue(persentVale4, "符合率",
				listGapStatisticsUnit.getYingyong() + "：" + persentVale4);

		double persentVale5 = new Double(listGapStatisticsUnit.getFuhedushuju())
				.doubleValue();
		dataset.addValue(persentVale5, "符合率", listGapStatisticsUnit.getShuju()
				+ "：" + persentVale5);

		// double persentVale6 = new Double(listGapStatisticsUnit.get(i)
		// .getFuheduanquan()).doubleValue();
		// dataset.addValue(persentVale6, "符合率", listGapStatisticsUnit.get(i)
		// .getAnquan() + "：" + persentVale6);

		Font font = new Font("黑体", 1, 20);
		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		JFreeChart jfreechart = new JFreeChart("符合度雷达图", font, spiderwebplot,
				true);

		font = new Font("黑体", 1, 20);
		TextTitle title = new TextTitle("符合度雷达图");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);
		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		SpiderWebPlot piePlot = (SpiderWebPlot) jfreechart.getPlot();
		piePlot.setSeriesPaint(new Color(255, 192, 0));

		piePlot.setStartAngle(90.0D);
		font = new Font("黑体", 0, 14);
		piePlot.setLabelFont(font);
		piePlot.setLabelPaint(new Color(95, 73, 122));

		return jfreechart;
	}

	// 折线图
	public static JFreeChart getSystemLineJFreeChart(
			List<SecurityTable> listSecurityTable) {
		DecimalFormat f = new DecimalFormat("#.##");

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (SecurityTable securityTable : listSecurityTable) {

			String useNumber0 = "基础网络安全";

			dataset.addValue(
					Double.parseDouble(securityTable.getJichuPertage()),
					securityTable.getSysname(), useNumber0);
			String useNumber1 = "边界安全";

			dataset.addValue(
					Double.parseDouble(securityTable.getBianjiePertage()),
					securityTable.getSysname(), useNumber1);

			String useNumber2 = "终端系统安全";

			dataset.addValue(
					Double.parseDouble(securityTable.getZhongduanPertage()),
					securityTable.getSysname(), useNumber2);

			String useNumber3 = "服务端系统安全";
			dataset.addValue(
					Double.parseDouble(securityTable.getFuwuduanPertage()),
					securityTable.getSysname(), useNumber3);

			String useNumber4 = "应用安全";
			dataset.addValue(
					Double.parseDouble(securityTable.getYingyongPertage()),
					securityTable.getSysname(), useNumber4);
			String useNumber5 = "数据安全域备份恢复";
			dataset.addValue(
					Double.parseDouble(securityTable.getShujuPertage()),
					securityTable.getSysname(), useNumber5);

			String useNumber6 = "安全管理中心";
			dataset.addValue(
					Double.parseDouble(securityTable.getAnquanPertage()),
					securityTable.getSysname(), useNumber6);
			String useNumber7 = "通用物理安全";
			String yy = (securityTable.getTwuliPertage() == null) ? "0"
					: (securityTable.getTwuliPertage());
			dataset.addValue(Double.parseDouble(yy),
					securityTable.getSysname(), useNumber7);
			String useNumber8 = "通用管理安全";
			String uu = securityTable.getTguanliPertage() == null ? "0"
					: securityTable.getTguanliPertage();
			dataset.addValue(Double.parseDouble(uu),
					securityTable.getSysname(), useNumber8);
		}
		JFreeChart chart = ChartFactory.createLineChart3D("各信息系统安全域符合度百分比", "",
				"", dataset, PlotOrientation.VERTICAL, true, true, false);

		Font font = new Font("黑体", 1, 20);
		TextTitle title = new TextTitle("各信息系统安全域符合度百分比");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		chart.setTitle(title);

		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("微软雅黑", 0, 14));

		CategoryPlot plot = chart.getCategoryPlot();

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.2D);
		rangeAxis.setLabelAngle(1.570796326794897D);
		rangeAxis.setTickLabelPaint(new Color(95, 73, 122));

		CategoryAxis categoryaxis = plot.getDomainAxis();
		categoryaxis.setTickLabelFont(new Font("黑体", 0, 11));
		categoryaxis.setTickLabelPaint(new Color(95, 73, 122));

		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions
				.createDownRotationLabelPositions(0.8D));

		categoryaxis.setMaximumCategoryLabelWidthRatio(3.0F);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelPaint(new Color(95, 73, 122));

		renderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F,
				new float[] { 10.0F, 6.0F }, 0.0F));
		renderer.setSeriesStroke(1, new BasicStroke(2.0F, 1, 1, 1.0F,
				new float[] { 6.0F, 6.0F }, 0.0F));
		renderer.setSeriesStroke(2, new BasicStroke(2.0F, 1, 1, 1.0F,
				new float[] { 2.0F, 6.0F }, 0.0F));

		return chart;
	}

}
