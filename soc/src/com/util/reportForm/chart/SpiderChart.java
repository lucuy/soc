package com.util.reportForm.chart;


import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class SpiderChart {
	// 线形图的数据及相关属性
	private List<ChartModel> modelList;

	// 数据集
	private DefaultCategoryDataset dataSet;

	// 生成图表的标题
	private String title = " 图 表 统 计 ";
	
	//生成图片的宽度
	private int chartWidth = 500;
	
	//生成图片的高度
	private int chartHeight = 300;

	public SpiderChart(List<ChartModel> modelList) {
		super();
		this.modelList = modelList;
	}

	public SpiderChart(List<ChartModel> modelList, String title,
			int chartWidth, int chartHeight) {
		super();
		this.modelList = modelList;
		this.title = title;
		this.chartWidth = chartWidth;
		this.chartHeight = chartHeight;
	}
	
	/**
	 * 生成图片
	 * 
	 * @param path
	 *            保存文件的路径
	 * @param fileName 文件名称
	 */
	public void getChart(String path, String fileName) {
		// 参数校验
		if (null == path || "".equals(path)) {
			return;
		}
		File dir = new File(path);
		//如果目录不存在，生成目录
		if(!dir.exists() || !dir.isDirectory()){
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
			ChartUtilities.writeChartAsJPEG(fos, chart, chartWidth, chartHeight);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {//关闭流
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
	 */
	@SuppressWarnings("unchecked")
	private void getDataset() {
		if(null == this.dataSet){
			this.dataSet = new DefaultCategoryDataset();
		}
		// 参数检验
		if (null == modelList) {
			return;
		}

		for (ChartModel m : modelList) {
			// 曲线轴对应的数据
			double data = m.getData();

			// 获得曲线的名称
			String lineName = m.getLineName();

			// 曲线轴标记
			String axisName = m.getAxisName();

			// 设置dataSet的值
			dataSet.addValue(data, lineName, axisName);

		}
	}
	
	private JFreeChart createChart(CategoryDataset categorydataset) {
		 SpiderWebPlot spiderwebplot = new TickMarkSpiderWebPlot(categorydataset);
		 
		 //spiderwebplot.setBackgroundPaint(new Color(69,171,254));
//		 spiderwebplot.setBackgroundPaint(Color.white);
		 Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题
		 JFreeChart jfreechart = new JFreeChart(this.title, new Font("宋体", Font.PLAIN,15), spiderwebplot, false);
		 jfreechart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
	                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		 jfreechart.setBackgroundPaint(Color.white);
		 TickMarkSpiderWebPlot pieplot = (TickMarkSpiderWebPlot)jfreechart.getPlot(); 
	     pieplot.setBackgroundPaint(Color.white);
	     
		 // 设定背景透明度（0-1.0之间）
		 pieplot.setBackgroundAlpha(0.6f);
		 // 设定前景透明度（0-1.0之间）
		 pieplot.setForegroundAlpha(0.8f); 
		 LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		 legendtitle.setPosition(RectangleEdge.BOTTOM);
		 jfreechart.addSubtitle(legendtitle);
		// 图片标题
		jfreechart.setTitle(new TextTitle(jfreechart.getTitle().getText(),
					titleFont));
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
	
	public static void main(String[] args){
		//生成折线图
		ChartModel chartModel = new ChartModel();
		chartModel.addDataToModelList("Java程序员", "第一年", 3000D);
		chartModel.addDataToModelList("Java程序员", "第二年", 4000D);
		chartModel.addDataToModelList("Java程序员", "第三年", 7000D);
		chartModel.addDataToModelList("Java程序员", "第四年", 8000D);
		chartModel.addDataToModelList("Java程序员", "第五年", 9000D);
		chartModel.addDataToModelList("Java程序员", "第六年", 12000D);
		
		chartModel.addDataToModelList("C程序员", "第一年", 1000D);
		chartModel.addDataToModelList("C程序员", "第二年", 3000D);
		chartModel.addDataToModelList("C程序员", "第三年", 6000D);
		chartModel.addDataToModelList("C程序员", "第四年", 4000D);
		chartModel.addDataToModelList("C程序员", "第五年", 8000D);
		chartModel.addDataToModelList("C程序员", "第六年", 13000D);
		
		List<ChartModel> modelList = chartModel.getChartModelList();
		SpiderChart schart = new SpiderChart(modelList, "程序员收入调查", 600, 400);
		schart.getChart("E:/", "spiderChart.jpeg");
	}
	
	
}
