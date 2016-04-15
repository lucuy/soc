package com.util.reportForm.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import com.util.reportForm.chart.TickMarkSpiderWebPlot;
import com.util.reportForm.struts.form.DataSource;
import com.util.reportForm.util.dataBase.DateSource;


/**
 * 实际取色的时候一定要16位的，这样比较准确
 * 
 * @author new
 */
public class CreateChartServiceImpl {

	private static final String CHART_PATH = "f:/";

	public static void main(String[] args) {
		CreateChartServiceImpl pm = new CreateChartServiceImpl();
		// 生成饼状图
		// pm.makePieChart();
		// 生成单组柱状图
		// pm.makeBarChart();
		// 生成多组柱状图
		// pm.makeBarGroupChart();
		// 生成堆积柱状图
		// pm.makeStackedBarChart();
		// 生成折线图
		// pm.makeLineAndShapeChart();
	}

	// 柱状图数据集
	public CategoryDataset getBarData(List list) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Iterator it = null;
		DateSource datesource = null;
		String itemX = "";
		String itemY = "";
		if (list != null && list.size() > 0) {
			it = list.iterator();
			while (it.hasNext()) {
				datesource = (DateSource) it.next();
				itemX = datesource.getItemX();
				itemY = datesource.getItemY();
				if (itemX == null) {
					itemX = "";
				}
				if (itemY == null) {
					itemY = "";
				}
				dataset.setValue(datesource.getDate(), itemY, itemX);
			}
		}
		return dataset;

	}

	// 饼状图 数据集
	public PieDataset getDataPieSetByUtil(List list) {

		DefaultPieDataset dataset = new DefaultPieDataset();
		Iterator it = null;
		DateSource datesource = null;
		String key = "";
		if (list != null && list.size() > 0) {
			it = list.iterator();
			while (it.hasNext()) {
				datesource = (DateSource) it.next();
				key = datesource.getItemX();
				if (key == null) {
					key = "";
				}
				dataset.setValue(key, datesource.getDate());
			}
		}
		return dataset;
	}

	/**
	 * 柱状图
	 * 
	 *@param dataset
	 *            数据集
	 * @param xName
	 *            x轴的说明（如种类，时间等）
	 * @param yName
	 *            y轴的说明（如速度，时间等）
	 * @param chartTitle
	 *            图标题
	 * @param charName
	 *            生成图片的名字
	 * @return
	 */
	public String createBarChart(CategoryDataset dataset, String xName,
			String yName, String chartTitle, String path, String picName) {
		JFreeChart chart = ChartFactory.createBarChart(chartTitle, // 图表标题
				xName, // 目录轴的显示标签
				yName, // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);
		/*
		 * VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭,
		 * 使用的关闭抗锯齿后，字体尽量选择12到14号的宋体字,这样文字最清晰好看
		 */
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setTextAntiAlias(false);
		chart.setBackgroundPaint(Color.white);
		Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
		Font font = new Font("宋体", 10, 15);
		Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
		// create plot
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.getDomainAxis().setLabelFont(font);
		plot.getDomainAxis().setTickLabelFont(font);
		plot.getRangeAxis().setLabelFont(font);
		plot.getRangeAxis().setTickLabelFont(font);
		// 设置横虚线可见
		plot.setRangeGridlinesVisible(true);
		// 虚线色彩
		plot.setRangeGridlinePaint(Color.gray);

		// 数据轴精度
		NumberAxis vn = (NumberAxis) plot.getRangeAxis();
		// vn.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0.00");
		vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式
		// x轴设置
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值

		// Lable（Math.PI/3.0）度倾斜
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions
		// .createUpRotationLabelPositions(Math.PI / 3.0));

		domainAxis.setMaximumCategoryLabelWidthRatio(1.5f);// 横轴上的 Lable 是否完整显示

		// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.1);
		// 设置距离图片右端距离
		domainAxis.setUpperMargin(0.1);
		// 设置 columnKey 是否间隔显示
		// domainAxis.setSkipCategoryLabelsToFit(true);

		plot.setDomainAxis(domainAxis);
		// 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
		plot.setBackgroundPaint(new Color(255, 255, 204));

		// y轴设置
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(labelFont);
		rangeAxis.setTickLabelFont(labelFont);
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);

		BarRenderer renderer = new BarRenderer();
		// 设置柱子宽度
		renderer.setMaximumBarWidth(0.04);
		// 设置柱子高度
		renderer.setMinimumBarLength(0.2);
		// 设置柱子边框颜色
		renderer.setBaseOutlinePaint(Color.BLACK);
		// 设置柱子边框可见
		renderer.setDrawBarOutline(true);

		// 设置柱的颜色
		renderer.setSeriesPaint(0, new Color(204, 255, 255));
		renderer.setSeriesPaint(1, new Color(153, 204, 255));
		renderer.setSeriesPaint(2, new Color(51, 204, 204));

		// 设置每个地区所包含的平行柱的之间距离
		renderer.setItemMargin(0.0);

		// 显示每个柱的数值，并修改该数值的字体属性
		renderer.setIncludeBaseInRange(true);
		renderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		plot.setRenderer(renderer);
		// 设置柱的透明度
		plot.setForegroundAlpha(1.0f);

		// 设置图片中item部分中文乱码
		chart.getLegend().setItemFont(font);

		FileOutputStream jpg = null;
		String pathname = "";
		if (picName == null || "".equals(picName)) {
			picName = "bar.jpg";
		} else {
			if (!picName.endsWith(".jpg") && !picName.endsWith(".JPG")) {
				picName = picName + ".jpg";
			}
		}
		if (path == null || "".equals(path)) {
			pathname = picName;
		} else {
			pathname = path + File.separator + picName;
		}
		try {
			File file = new File(pathname);
			if (file.exists()) {
				file.delete();
			}
			jpg = new FileOutputStream(pathname);
			ChartUtilities.writeChartAsPNG(jpg, chart, 800, 600, true, 10);
			return pathname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				jpg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 横向图
	 * 
	 * @param dataset
	 *            数据集
	 * @param xName
	 *            x轴的说明（如种类，时间等）
	 * @param yName
	 *            y轴的说明（如速度，时间等）
	 * @param chartTitle
	 *            图标题
	 * @param charName
	 *            生成图片的名字
	 * @return
	 */
	public String createHorizontalBarChart(CategoryDataset dataset,
			String xName, String yName, String chartTitle, String path) {
		JFreeChart chart = ChartFactory.createBarChart(chartTitle, // 图表标题
				xName, // 目录轴的显示标签
				yName, // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);

		CategoryPlot plot = chart.getCategoryPlot();
		// 数据轴精度
		NumberAxis vn = (NumberAxis) plot.getRangeAxis();
		// 设置刻度必须从0开始
		// vn.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0.00");
		vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式

		CategoryAxis domainAxis = plot.getDomainAxis();

		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的
		// Lable
		Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);

		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值

		domainAxis.setMaximumCategoryLabelWidthRatio(0.8f);// 横轴上的 Lable 是否完整显示
		// domainAxis.setVerticalCategoryLabels(false);
		plot.setDomainAxis(domainAxis);

		ValueAxis rangeAxis = plot.getRangeAxis();
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);
		BarRenderer renderer = new BarRenderer();
		// 设置柱子宽度
		renderer.setMaximumBarWidth(0.03);
		// 设置柱子高度
		renderer.setMinimumBarLength(30);

		renderer.setBaseOutlinePaint(Color.BLACK);

		// 设置柱的颜色
		renderer.setSeriesPaint(0, Color.GREEN);
		renderer.setSeriesPaint(1, new Color(0, 0, 255));
		// 设置每个地区所包含的平行柱的之间距离
		renderer.setItemMargin(0.5);
		// 显示每个柱的数值，并修改该数值的字体属性
		renderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// 设置柱的数值可见
		renderer.setBaseItemLabelsVisible(true);

		plot.setRenderer(renderer);
		// 设置柱的透明度
		plot.setForegroundAlpha(0.6f);

		FileOutputStream fos_jpg = null;
		try {
			String chartName = path + "HorizontalBar.png";
			fos_jpg = new FileOutputStream(chartName);
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 500, 500, true, 10);
			return chartName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 饼状图
	 * 
	 * @param dataset
	 *            数据集
	 * @param chartTitle
	 *            图标题
	 * @param path
	 *            生成图的路径
	 * @return
	 */
	public String createValidityComparePimChar(PieDataset dataset,
			String chartTitle, String path, String jpgname) {
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, // chart
				// title
				dataset,// data
				true,// include legend
				true, false);

		// 使下说明标签字体清晰,去锯齿类似于
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setTextAntiAlias(false);
		// 图片背景色
		chart.setBackgroundPaint(Color.white);
		// 设置图标题的字体重新设置title
		Font font = new Font("宋体", 10, 15);
		Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 图片中显示百分比:默认方式

		plot.setBackgroundPaint(Color.white);
		plot.setLabelFont(font);
		plot.setCircular(true);

		// 指定饼图轮廓线的颜色
		plot.setBaseSectionOutlinePaint(Color.BLACK);
		plot.setBaseSectionPaint(Color.BLACK);

		// 设置无数据时的信息
		plot.setNoDataMessage("无对应的数据，请重新查询。");

		// 设置无数据时的信息显示颜色
		plot.setNoDataMessagePaint(Color.red);

		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2})",
				NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}"));

		// 设置指示线风格
		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);

		// 指定图片的透明度(0.0-1.0)
		plot.setForegroundAlpha(0.65f);
		// 指定显示的饼图上圆形(false)还椭圆形(true)
		plot.setCircular(false, true);

		// 设置第一个 饼块section 的开始位置，默认是12点钟方向
		plot.setStartAngle(90);

		// 转换成2D图
		// PiePlot3D plot3D = (PiePlot3D)plot;
		// plot3D.setDepthFactor(0);

		// 设置图片中item部分中文乱码
		chart.getLegend().setItemFont(font);
		// 删除已有的图片
		this.delpic();

		FileOutputStream jpg = null;
		String pathname = "";
		if (jpgname == null || "".equals(jpgname)) {
			jpgname = "pie.jpg";
		} else {
			if (!jpgname.endsWith(".jpg") && !jpgname.endsWith(".JPG")) {
				jpgname = jpgname + ".jpg";
			}
		}
		if (path == null || "".equals(path)) {
			pathname = jpgname;
		} else {

			pathname = path + File.separator + jpgname;
		}
		try {
			File file = new File(pathname);
			if (file.exists()) {
				file.delete();
			}
			// //System.out.println(pathname);
			jpg = new FileOutputStream(pathname);
			ChartUtilities.writeChartAsPNG(jpg, chart, 800, 600);
			//ChartUtilities.writeChartAsPNG(jpg, chart, 800, 600, true, 10);
			return pathname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				jpg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 删除已有图片
	public void delpic() {
		File file = null;
		try {
			PathUtil pu=new PathUtil();
			file = new File(pu.getWebRoot() + "CommonFile/QueryCount");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file2 = files[i];
			file2.delete();

		}
	}

	/**
	 * 折线图
	 * 
	 * @param chartTitle
	 * @param x
	 * @param y
	 * @param xyDataset
	 * @param charName
	 * @return
	 */
	public String createTimeXYChar(String chartTitle, String x, String y,
			CategoryDataset xyDataset, String path) {

		JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y,
				xyDataset, PlotOrientation.VERTICAL, true, true, false);

		chart.setTextAntiAlias(false);
		chart.setBackgroundPaint(Color.WHITE);
		// 设置图标题的字体重新设置title
		Font font = new Font("宋体", 10, 15);
		Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
		// 设置面板字体
		Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);

		chart.setBackgroundPaint(Color.WHITE);

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		// x轴 // 分类轴网格是否可见
		categoryplot.setDomainGridlinesVisible(true);
		// y轴 //数据轴网格是否可见
		categoryplot.setRangeGridlinesVisible(true);

		categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩

		categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩

		categoryplot.setBackgroundPaint(Color.lightGray);

		// 设置轴和面板之间的距离
		// categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));

		CategoryAxis domainAxis = categoryplot.getDomainAxis();

		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值

		ValueAxis rangeAxis = categoryplot.getRangeAxis();
		rangeAxis.setLabelFont(font);
		// rangeAxis.setLabelPaint(Color.BLUE); // 字体颜色
		rangeAxis.setTickLabelFont(font);

		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // 横轴上的
		// Lable
		// 45度倾斜
		// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.0);
		// 设置距离图片右端距离
		domainAxis.setUpperMargin(0.0);

		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(true);

		// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
				.getRenderer();

		lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
		lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见

		// 显示折点数据
		// lineandshaperenderer.setBaseItemLabelGenerator(new
		// StandardCategoryItemLabelGenerator());
		// lineandshaperenderer.setBaseItemLabelsVisible(true);

		// 设置图片中item部分中文乱码
		chart.getLegend().setItemFont(font);

		FileOutputStream fos_jpg = null;
		try {
			String chartName = path + "LineAndShape.png";
			fos_jpg = new FileOutputStream(chartName);

			// 将报表保存为png文件
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 800, 600);

			return chartName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 雷达图
	 * 
	 * @param chartTitle
	 * @param x
	 * @param y
	 * @param xyDataset
	 * @param charName
	 * @return
	 */
	public String createPolarChart(String chartTitle,
			CategoryDataset categorydataset, String path) {
		SpiderWebPlot dataset = new TickMarkSpiderWebPlot(categorydataset);

		JFreeChart chart = new JFreeChart(chartTitle, new Font("宋体",
				Font.PLAIN, 15), dataset, false);

		chart.setTextAntiAlias(false);
		chart.setBackgroundPaint(Color.WHITE);
		// 设置图标题的字体重新设置title
		Font font = new Font("宋体", 10, 15);
		Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));

		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		chart.setBackgroundPaint(Color.white);
		TickMarkSpiderWebPlot pieplot = (TickMarkSpiderWebPlot) chart.getPlot();
		pieplot.setBackgroundPaint(Color.white);

		// 设定背景透明度（0-1.0之间）
		pieplot.setBackgroundAlpha(0.6f);

		// 设定前景透明度（0-1.0之间）
		pieplot.setForegroundAlpha(0.8f);
		LegendTitle legendtitle = new LegendTitle(dataset);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		chart.addSubtitle(legendtitle);

		// 设置图片中item部分中文乱码
		chart.getLegend().setItemFont(font);

		FileOutputStream fos_jpg = null;
		try {
			String chartName = path + "Polar.png";
			fos_jpg = new FileOutputStream(chartName);

			// 将报表保存为png文件
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 800, 600);

			return chartName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 堆栈柱状图
	 * 
	 * @param dataset
	 * @param xName
	 * @param yName
	 * @param chartTitle
	 * @param charName
	 * @return
	 */
	public String createStackedBarChart(CategoryDataset dataset, String xName,
			String yName, String chartTitle, String path) {
		// 1:得到 CategoryDataset

		// 2:JFreeChart对象
		JFreeChart chart = ChartFactory.createStackedBarChart(chartTitle, // 图表标题
				xName, // 目录轴的显示标签
				yName, // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);
		// 图例字体清晰
		chart.setTextAntiAlias(false);

		chart.setBackgroundPaint(Color.WHITE);

		// 2 ．2 主标题对象 主标题对象是 TextTitle 类型
		chart
				.setTitle(new TextTitle(chartTitle, new Font("黑体", Font.PLAIN,
						20)));
		// 2 ．2.1:设置中文
		// x,y轴坐标字体
		Font labelFont = new Font("宋体", 10, 15);

		// 2 ．3 Plot 对象 Plot 对象是图形的绘制结构对象
		CategoryPlot plot = chart.getCategoryPlot();

		// 设置横虚线可见
		plot.setRangeGridlinesVisible(true);
		// 虚线色彩
		plot.setRangeGridlinePaint(Color.gray);

		// 数据轴精度
		NumberAxis vn = (NumberAxis) plot.getRangeAxis();
		// // 设置最大值是1
		// vn.setUpperBound(2000);
		// 设置数据轴坐标从0开始
		// vn.setAutoRangeIncludesZero(true);
		// 数据显示格式是百分比
		DecimalFormat df = new DecimalFormat("#0.00");
		vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式
		// DomainAxis （区域轴，相当于 x 轴）， RangeAxis （范围轴，相当于 y 轴）
		CategoryAxis domainAxis = plot.getDomainAxis();

		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值

		// x轴坐标太长，建议设置倾斜，如下两种方式选其一，两种效果相同
		// 倾斜（1）横轴上的 Lable 45度倾斜
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// 倾斜（2）Lable（Math.PI 3.0）度倾斜
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions
		// .createUpRotationLabelPositions(Math.PI / 3.0));

		domainAxis.setMaximumCategoryLabelWidthRatio(0.6f);// 横轴上的 Lable 是否完整显示

		plot.setDomainAxis(domainAxis);

		// y轴设置
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(labelFont);
		rangeAxis.setTickLabelFont(labelFont);
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);

		// Renderer 对象是图形的绘制单元
		StackedBarRenderer renderer = new StackedBarRenderer();
		// 设置柱子宽度
		renderer.setMaximumBarWidth(0.05);
		// 设置柱子高度
		renderer.setMinimumBarLength(0.1);
		// 设置柱的边框颜色
		renderer.setBaseOutlinePaint(Color.BLACK);
		// 设置柱的边框可见
		renderer.setDrawBarOutline(true);

		// 设置柱的颜色(可设定也可默认)
		renderer.setSeriesPaint(0, new Color(204, 255, 204));
		renderer.setSeriesPaint(1, new Color(255, 204, 153));

		// 设置每个地区所包含的平行柱的之间距离
		renderer.setItemMargin(0.4);

		plot.setRenderer(renderer);
		// 设置柱的透明度(如果是3D的必须设置才能达到立体效果，如果是2D的设置则使颜色变淡)
		// plot.setForegroundAlpha(0.65f);

		// 设置图片中item部分中文乱码
		chart.getLegend().setItemFont(labelFont);

		FileOutputStream fos_jpg = null;
		try {
			String chartName = path + "StackedBar.png";
			fos_jpg = new FileOutputStream(chartName);
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 500, 500, true, 10);
			return chartName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}