package com.util.reportForm.chart;


import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart {
	// 线形图的数据及相关属性

	private List<ChartModel> modelList;

	// 数据集

	private DefaultCategoryDataset dataSet;

	// 生成图表的标题

	private String title = " 图 表 统 计 ";

	// 生成Y轴显示的标题
	private String YLable = "-- Y --";

	// X轴显示的标题
	private String XLable = "-- X --";

	// 生成图片的宽度

	private int chartWidth = 800;

	// 生成图片的高度

	private int chartHeight = 600;

	public LineChart() {

	}

	public LineChart(List<ChartModel> modelList) {
		this.modelList = modelList;
	}

	public LineChart(List<ChartModel> modelList, String title, String YLable,
			String XLable, int chartWidth, int chartHeight) {
		this.modelList = modelList;
		this.title = title;
		this.YLable = YLable;
		this.XLable = XLable;
		this.chartWidth = chartWidth;
		this.chartHeight = chartHeight;
	}

	/**
	 * 生成图片
	 * 
	 * @param path
	 *            保存文件的路径
	 * 
	 * @param fileName
	 *            文件名称
	 */
	public void getChart(String path, String fileName) {
		// 参数校验
		if (null == path || "".equals(path)) {
			return;
		}
		File file = new File(path + fileName);
		if (file.exists()) {
			file.delete();
		}
		File dir = new File(path);
		// 如果目录不存在，生成目录
		if (!dir.exists() || !dir.isDirectory()) {
			dir.mkdirs();
		}
		// 生成数据集

		getDataset();

		// 获得生成图片的对象

		JFreeChart chart = createChart(this.dataSet);
		
		// 生成图片
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path + File.separator + fileName);
			ChartUtilities
					.writeChartAsJPEG(fos, chart, chartWidth, chartHeight);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 关闭流

			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获得生成图表需要的数据
	 * 
	 * @return 生成图表的数据
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void getDataset() {
		if (null == this.dataSet) {
			this.dataSet = new DefaultCategoryDataset();
		}
		// 参数检验

		if (null == modelList) {
			return;
		}

		for (ChartModel m : modelList) {
			// 曲线轴标记对应的数据
			double data = m.getData();

			// 获得曲线的名称

			String lineName = m.getLineName();

			// 曲线轴标记

			String axisName = m.getAxisName();

			// 设置dataSet的值

			dataSet.addValue(data, lineName, axisName);

		}
	}

	/**
	 * 创建chart对象
	 * 
	 * @param categorydataset
	 * @return
	 */
	private JFreeChart createChart(CategoryDataset categorydataset) {

		Font xfont = new Font("宋体", Font.PLAIN, 15);// X轴

		Font yfont = new Font("宋体", Font.PLAIN, 15);// Y轴

		Font kfont = new Font("宋体", Font.PLAIN, 15);// 底部
		Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题

		// 生成图表对象
		JFreeChart jfreechart = ChartFactory.createLineChart(this.title,
				this.XLable, YLable, categorydataset, PlotOrientation.VERTICAL,
				true, true, false);
		jfreechart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		jfreechart.setBackgroundPaint(Color.white);
		CategoryPlot plot = jfreechart.getCategoryPlot();// 图形的绘制结构对象
		
		// 设置背景色

//		plot.setBackgroundPaint(new Color(237, 243, 255));
		
		plot.setBackgroundPaint(Color.white);

		// 设置网格线

		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.black);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);

		// 图片标题
		jfreechart.setTitle(new TextTitle(jfreechart.getTitle().getText(),
				titleFont));

		// 底部
		jfreechart.getLegend().setItemFont(kfont);

		// X 轴

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(xfont);// 轴标题

		domainAxis.setTickLabelFont(xfont);// 轴数值

//		domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的label斜显示
		// 设置距离图片左端距离

        domainAxis.setLowerMargin(0.0);
        // 设置距离图片右端距离
        domainAxis.setUpperMargin(0.0);

        NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setAutoRangeIncludesZero(true);
		// Y 轴

		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(yfont);
//		rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色
		rangeAxis.setTickLabelFont(yfont);

		// 获得渲染器

		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) plot
				.getRenderer();
		lineandshaperenderer.setShapesVisible(true);
		lineandshaperenderer.setDrawOutlines(true);
		lineandshaperenderer.setUseFillPaint(true);

		return jfreechart;
	}

	public List<ChartModel> getModelList() {
		return modelList;
	}

	public void setModelList(List<ChartModel> modelList) {
		this.modelList = modelList;
	}

	public DefaultCategoryDataset getDataSet() {
		return dataSet;
	}

	public void setDataSet(DefaultCategoryDataset dataSet) {
		this.dataSet = dataSet;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYLable() {
		return YLable;
	}

	public void setYLable(String lable) {
		YLable = lable;
	}

	public String getXLable() {
		return XLable;
	}

	public void setXLable(String lable) {
		XLable = lable;
	}

	public int getChartWidth() {
		return chartWidth;
	}

	public void setChartWidth(int chartWidth) {
		this.chartWidth = chartWidth;
	}

	public int getChartHeight() {
		return chartHeight;
	}

	public void setChartHeight(int chartHeight) {
		this.chartHeight = chartHeight;
	}

	public static void main(String[] args) {

		// 生成折线图

		ChartModel chartModel = new ChartModel();
		chartModel.addDataToModelList("Java程序员", "第一年", 3000D);
		chartModel.addDataToModelList("Java程序员", "第二年", 4000D);
		
		chartModel.addDataToModelList("C程序员", "第一年", 1000D);
		chartModel.addDataToModelList("C程序员", "第二年", 3000D);
		
		chartModel.addDataToModelList("Java程序员", "第三年", 7000D);
		chartModel.addDataToModelList("Java程序员", "三年后", 9000D);

		
		chartModel.addDataToModelList("C程序员", "第三年", 6000D);
		chartModel.addDataToModelList("C程序员", "三年后", 4000D);
		
		chartModel.addDataToModelList("D程序员", "第一年", 1000D);
		chartModel.addDataToModelList("D程序员", "第二年", 2000D);
		chartModel.addDataToModelList("D程序员", "第三年", 5000D);
		chartModel.addDataToModelList("D程序员", "三年后", 9000D);

		List<ChartModel> modelList = chartModel.getChartModelList();

		LineChart linechart = new LineChart(modelList, "程序员收入调查", "收 入",
				"工作年限", 600, 400);
		linechart.getChart("f:\\chart", "lineChart.jpeg");
	}
}