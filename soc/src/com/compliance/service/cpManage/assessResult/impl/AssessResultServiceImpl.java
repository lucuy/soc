package com.compliance.service.cpManage.assessResult.impl;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import com.compliance.dao.cpManage.assessResult.AssessResultDao;
import com.compliance.model.cpManage.assessResult.AssessResult;
import com.compliance.model.cpManage.demand.DemandCollet;
import com.compliance.model.cpManage.technology.Technology;
import com.compliance.service.cpManage.assessResult.AssessResultService;
import com.compliance.service.impl.BaseServiceImpl;
import com.util.MySpiderWebPlot;
import com.util.StringUtil;

public class AssessResultServiceImpl extends BaseServiceImpl implements AssessResultService {

	private AssessResultDao assessResultDao;

	/**
	 * 查询评估项存在数量
	 * 
	 * @param Map
	 * @return List<Technology>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCount(Map map) {
		return assessResultDao.queryAssessCount(map);
	}

	/**
	 * 查询评估项已评估数量
	 * 
	 * @param String
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int queryAssessOverCount(String acId) {
		return assessResultDao.queryAssessOverCount(acId);
	}

	/**
	 * 模糊查询差距分布表
	 * 
	 * @param Map
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCountTable(Map map) {
		return assessResultDao.queryAssessCountTable(map);
	}

	/**
	 * 添加数据
	 * 
	 * @param AssessResult
	 * @return void
	 */
	public void insert(AssessResult a) {
		assessResultDao.insert(a);
	}

	/**
	 * 修改数据
	 * 
	 * @param AssessResult
	 * @return void
	 */
	public void update(AssessResult a) {
		assessResultDao.update(a);
	}

	/**
	 * 生成符合度百分比饼图
	 * 
	 * @param acId
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getAssessResultTotalPieJFreeChart(int acId) {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		JFreeChart jfreechart = ChartFactory.createPieChart3D("符合度百分比饼图", pieDataset, true, true, false);

		Font font = new Font("微软雅黑", 1, 20);
		TextTitle title = new TextTitle("符合度百分比饼图");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);

		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		LegendTitle legendTitle = jfreechart.getLegend();
		legendTitle.setItemFont(new Font("微软雅黑", 0, 14));

		PiePlot3D piePlot = (PiePlot3D) jfreechart.getPlot();

		DecimalFormat f = new DecimalFormat("#.##");

		int assessResultCount0 = 0;
		int assessResultCount1 = 0;
		int assessResultCount2 = 0;

		if (acId != 0 && !"null".equals(acId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("acId", acId);
			List<AssessResult> assessResultsList = assessResultDao.queryAssessCount(map);
			for (AssessResult assessResult : assessResultsList) {
				if ("0".equals(assessResult.getCIA_AssessResult()))
					assessResultCount0++;
				if ("1".equals(assessResult.getCIA_AssessResult()))
					assessResultCount1++;
				if ("2".equals(assessResult.getCIA_AssessResult()))
					assessResultCount2++;
			}
		}
		int totleassessResultCount = assessResultCount0 + assessResultCount1 + assessResultCount2;
		if (totleassessResultCount != 0) {
			String perent = StringUtil.formatNumber(Double.valueOf(Double.parseDouble(((Integer) assessResultCount0).toString()) / Double.parseDouble(((Integer) totleassessResultCount).toString())), "0.00%");
			pieDataset.setValue("符合(" + ((Integer) assessResultCount0).toString() + ")" + perent, Double.parseDouble(f.format(Double.parseDouble(((Integer) assessResultCount0).toString()) / Double.parseDouble(((Integer) totleassessResultCount).toString()) * 100.0D)));
			piePlot.setSectionPaint("符合", Color.decode("#FF0F00"));

			perent = StringUtil.formatNumber(Double.valueOf(Double.parseDouble(((Integer) assessResultCount1).toString()) / Double.parseDouble(((Integer) totleassessResultCount).toString())), "0.00%");
			pieDataset.setValue("部分符合(" + ((Integer) assessResultCount1).toString() + ")" + perent, Double.parseDouble(f.format(Double.parseDouble(((Integer) assessResultCount1).toString()) / Double.parseDouble(((Integer) totleassessResultCount).toString()) * 100.0D)));
			piePlot.setSectionPaint("部分符合", Color.decode("#ff9f00"));

			perent = StringUtil.formatNumber(Double.valueOf(Double.parseDouble(((Integer) assessResultCount2).toString()) / Double.parseDouble(((Integer) totleassessResultCount).toString())), "0.00%");
			pieDataset.setValue("不符合(" + ((Integer) assessResultCount2).toString() + ")" + perent, Double.parseDouble(f.format(Double.parseDouble(((Integer) assessResultCount2).toString()) / Double.parseDouble(((Integer) totleassessResultCount).toString()) * 100.0D)));
			piePlot.setSectionPaint("不符合", Color.decode("#FF0000"));
		}
		piePlot.setOutlineVisible(false);
		piePlot.setSimpleLabels(false);
		piePlot.setDarkerSides(true);
		piePlot.setCircular(false);
		piePlot.setLabelGap(0.02D);

		piePlot.setStartAngle(0.0D);
		piePlot.setIgnoreNullValues(true);
		piePlot.setIgnoreZeroValues(true);

		piePlot.setLabelPaint(new Color(255, 255, 255));
		font = new Font("微软雅黑", 0, 12);
		piePlot.setLabelFont(font);
		piePlot.setLabelOutlinePaint(Color.BLACK);
		piePlot.setLabelBackgroundPaint(new Color(95, 73, 122));
		piePlot.setLabelShadowPaint(new Color(64, 49, 82));

		return jfreechart;
	}

	/**
	 * 生成符合度雷达图
	 * 
	 * @param List
	 *            <DemandCollet>,int
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getAssessResultRadarJFreeChart(List<DemandCollet> demandColletList, int acId) {
		DecimalFormat f = new DecimalFormat("#.##");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (DemandCollet demandCollet : demandColletList) {
			Map<String, Object> tableMap = new HashMap<String, Object>();
			tableMap.put("acId", acId);
			tableMap.put("sort", demandCollet.getUnitDomainName());
			List<AssessResult> assessResultList = assessResultDao.queryAssessCountTable(tableMap);
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
			double persentVale = 0.0D;
			if (assessResult0 + assessResult1 + assessResult2 != 0) {
				persentVale = new Double(f.format(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)))).doubleValue();
				dataset.addValue(persentVale, "符合率", demandCollet.getUnitName() + "：" + persentVale);
			} else {
				dataset.addValue(new Double(f.format(0L)), "符合分率", demandCollet.getUnitName() + "：" + persentVale);
			}
		}

		Font font = new Font("微软雅黑", 1, 20);
		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		JFreeChart jfreechart = new JFreeChart("符合度雷达图", font, spiderwebplot, true);

		font = new Font("微软雅黑", 1, 20);
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
		font = new Font("微软雅黑", 0, 14);
		piePlot.setLabelFont(font);
		piePlot.setLabelPaint(new Color(95, 73, 122));

		return jfreechart;
	}

	/**
	 * 差距评估符合度饼图
	 * 
	 * @param int , int ,int
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getPointJFreeChart(int assessResult0, int assessResult1, int assessResult2) {

		DefaultPieDataset pieDataset = new DefaultPieDataset();
		JFreeChart jfreechart = ChartFactory.createPieChart3D("符合度百分比饼图", pieDataset, true, true, false);

		Font font = new Font("微软雅黑", 1, 20);
		TextTitle title = new TextTitle("符合度百分比饼图");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);

		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		LegendTitle legendTitle = jfreechart.getLegend();
		legendTitle.setItemFont(new Font("微软雅黑", 0, 14));

		PiePlot3D piePlot = (PiePlot3D) jfreechart.getPlot();
		DecimalFormat f = new DecimalFormat("#.##");

		if (assessResult0 + assessResult1 + assessResult2 != 0) {
			double assessResult0persentVale = new Double(f.format(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)))).doubleValue();
			pieDataset.setValue("符合(" + String.valueOf(assessResult0) + ")" + assessResult0persentVale, Double.parseDouble(f.format(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)) * 100.0D)));
			piePlot.setSectionPaint("符合", Color.decode("#FF0F00"));
			double assessResult1persentVale = new Double(f.format(Double.parseDouble(String.valueOf(assessResult1)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)))).doubleValue();
			pieDataset.setValue("部分符合(" + String.valueOf(assessResult1) + ")" + assessResult1persentVale, Double.parseDouble(f.format(Double.parseDouble(String.valueOf(assessResult1)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)) * 100.0D)));
			piePlot.setSectionPaint("部分符合", Color.decode("#ff9f00"));
			double assessResult2persentVale = new Double(f.format(Double.parseDouble(String.valueOf(assessResult2)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)))).doubleValue();
			pieDataset.setValue("不符合(" + String.valueOf(assessResult2) + ")" + assessResult2persentVale, Double.parseDouble(f.format(Double.parseDouble(String.valueOf(assessResult2)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)) * 100.0D)));
			piePlot.setSectionPaint("不符合", Color.decode("#FF0000"));
		}

		piePlot.setOutlineVisible(false);
		piePlot.setSimpleLabels(false);
		piePlot.setDarkerSides(true);
		piePlot.setCircular(false);
		piePlot.setLabelGap(0.02D);

		piePlot.setStartAngle(0.0D);
		piePlot.setIgnoreNullValues(true);
		piePlot.setIgnoreZeroValues(true);

		piePlot.setLabelPaint(new Color(255, 255, 255));
		font = new Font("微软雅黑", 0, 12);
		piePlot.setLabelFont(font);
		piePlot.setLabelOutlinePaint(Color.BLACK);
		piePlot.setLabelBackgroundPaint(new Color(95, 73, 122));
		piePlot.setLabelShadowPaint(new Color(64, 49, 82));

		return jfreechart;
	}

	public JFreeChart getPointRadarJFreeChart(int assessResult0, int assessResult1, int assessResult2) {
		DecimalFormat f = new DecimalFormat("#.##");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (assessResult0 + assessResult1 + assessResult2 != 0) {
			double assessResult0persentVale = new Double(f.format(Double.parseDouble(String.valueOf(assessResult0)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)))).doubleValue();
			dataset.addValue(assessResult0persentVale, "符合率", "符合：" + assessResult0persentVale);
			double assessResult1persentVale = new Double(f.format(Double.parseDouble(String.valueOf(assessResult1)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)))).doubleValue();
			dataset.addValue(assessResult1persentVale, "符合率", "部分符合：" + assessResult1persentVale);
			double assessResult2persentVale = new Double(f.format(Double.parseDouble(String.valueOf(assessResult2)) / Double.parseDouble(String.valueOf(assessResult0 + assessResult1 + assessResult2)))).doubleValue();
			dataset.addValue(assessResult2persentVale, "符合率", "不符合：" + assessResult2persentVale);
		}

		Font font = new Font("微软雅黑", 1, 20);
		MySpiderWebPlot spiderwebplot = new MySpiderWebPlot(dataset);
		JFreeChart jfreechart = new JFreeChart("符合度雷达图", font, spiderwebplot, true);

		font = new Font("微软雅黑", 1, 20);
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
		font = new Font("微软雅黑", 0, 14);
		piePlot.setLabelFont(font);
		piePlot.setLabelPaint(new Color(95, 73, 122));

		return jfreechart;
	}

	public JFreeChart getPointFitBarChart(int assessResult0, int assessResult1, int assessResult2) {
		DefaultCategoryDataset categorydataset = new DefaultCategoryDataset();
		DecimalFormat f = new DecimalFormat("#.##");

		/*
		 * for (Iterator localIterator = pointStandardMap.keySet().iterator(); localIterator.hasNext(); ) { Object item = localIterator.next(); String standardId = item.toString(); String standardName = (String)pointStandardMap.get(standardId);
		 * 
		 * categorydataset.addValue((((Integer)pointStandardUseMap.get(standardId)).toString() != null) && (!((Integer)pointStandardUseMap.get(standardId)).toString().equals("")) ? Integer.parseInt(((Integer)pointStandardUseMap.get(standardId)).toString()) : 0, "适用控制项", standardName); categorydataset.addValue((((Integer)pointStandardFitMap.get(standardId)).toString() != null) && (!((Integer)pointStandardFitMap.get(standardId)).toString().equals("")) ? Integer.parseInt(((Integer)pointStandardFitMap.get(standardId)).toString()) : 0, "符合项", standardName); categorydataset.addValue((((Integer)pointStandardSomeMap.get(standardId)).toString() != null) && (!((Integer)pointStandardSomeMap.get(standardId)).toString().equals("")) ? Integer.parseInt(((Integer)pointStandardSomeMap.get(standardId)).toString()) : 0, "部分符合", standardName); categorydataset.addValue((((Integer)pointStandardNoMap.get(standardId)).toString() != null) && (!((Integer)pointStandardNoMap.get(standardId)).toString().equals("")) ? Integer.parseInt(((Integer)pointStandardNoMap.get(standardId)).toString()) : 0, "不符合", standardName); categorydataset.addValue((((Integer)pointStandardNoUseMap.get(standardId)).toString() != null) && (!((Integer)pointStandardNoUseMap.get(standardId)).toString().equals("")) ? Integer.parseInt(((Integer)pointStandardNoUseMap.get(standardId)).toString()) : 0, "不适用", standardName); }
		 */

		JFreeChart jfreechart = ChartFactory.createBarChart3D("控制点评估结果柱状图", "", "", categorydataset, PlotOrientation.VERTICAL, true, true, false);

		Font font = new Font("微软雅黑", 1, 20);
		TextTitle title = new TextTitle("控制点评估结果柱状图");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);
		jfreechart.getLegend().setItemFont(new Font("宋体", 0, 12));

		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		CategoryPlot categoryplot = jfreechart.getCategoryPlot();

		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
		numberaxis.setTickLabelPaint(new Color(95, 73, 122));

		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		domainAxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
		domainAxis.setTickLabelPaint(new Color(95, 73, 122));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions(0.8D));
		domainAxis.setMaximumCategoryLabelWidthRatio(3.0F);

		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setDomainGridlinePaint(Color.BLUE);
		categoryplot.setForegroundAlpha(0.9F);

		BarRenderer3D barRenderer3D = new BarRenderer3D();
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3D.setBaseItemLabelsVisible(true);
		barRenderer3D.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		barRenderer3D.setItemLabelAnchorOffset(10.0D);

		barRenderer3D.setItemMargin(0.1D);
		barRenderer3D.setItemLabelPaint(new Color(95, 73, 122));
		barRenderer3D.setMinimumBarLength(0.01D);
		barRenderer3D.setMaximumBarWidth(0.05D);

		categoryplot.setRenderer(barRenderer3D);

		return jfreechart;
	}

	// 历次评估对比柱状图
	public JFreeChart getEveryTimePointBarChart(List<DemandCollet> demandColletList, List<Double> douList, String technologyName) {
		DefaultCategoryDataset categorydataset = new DefaultCategoryDataset();
		DecimalFormat f = new DecimalFormat("#.##");
		if (demandColletList.size() > 0) {
			int i = 0;
			for (DemandCollet demandCollet : demandColletList) {
				categorydataset.addValue(douList.get(i), technologyName, demandCollet.getUnitName());
				i++;
			}
		}
		JFreeChart jfreechart = ChartFactory.createBarChart3D(demandColletList.get(0).getUnitName(), "", "", categorydataset, PlotOrientation.VERTICAL, true, true, false);

		Font font = new Font("微软雅黑", 1, 20);
		TextTitle title = new TextTitle("各项安全项符合度柱状图");
		title.setFont(font);
		title.setPaint(new Color(95, 73, 122));
		jfreechart.setTitle(title);
		jfreechart.getLegend().setItemFont(new Font("宋体", 0, 12));

		jfreechart.setBackgroundPaint(new Color(220, 220, 220));
		jfreechart.setBorderVisible(true);
		jfreechart.setBorderPaint(new Color(211, 223, 238));

		CategoryPlot categoryplot = jfreechart.getCategoryPlot();

		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
		numberaxis.setTickLabelPaint(new Color(95, 73, 122));

		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		domainAxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
		domainAxis.setTickLabelPaint(new Color(95, 73, 122));
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createDownRotationLabelPositions(0.8D));
		domainAxis.setMaximumCategoryLabelWidthRatio(3.0F);

		categoryplot.setDomainGridlinePaint(Color.white);
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setDomainGridlinePaint(Color.BLUE);
		categoryplot.setForegroundAlpha(0.9F);

		BarRenderer3D barRenderer3D = new BarRenderer3D();
		barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3D.setBaseItemLabelsVisible(true);
		barRenderer3D.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		barRenderer3D.setItemLabelAnchorOffset(10.0D);

		barRenderer3D.setItemMargin(0.1D);
		barRenderer3D.setItemLabelPaint(new Color(95, 73, 122));
		barRenderer3D.setMinimumBarLength(0.01D);
		barRenderer3D.setMaximumBarWidth(0.05D);

		categoryplot.setRenderer(barRenderer3D);

		return jfreechart;
	}

	/**
	 * 历次评估对比折线图
	 * 
	 * @param
	 * @return JFreeChart
	 * 
	 * */
	public JFreeChart getEveryTimeLineBarChart(List<Technology> technologyList) {

		DefaultKeyedValues keyedValues = new DefaultKeyedValues();
		JFreeChart jfreechart = null;
		if (technologyList.size() > 0) {
			for (Technology technology : technologyList) {
				keyedValues.addValue(technology.getName() + new SimpleDateFormat("yyyyMMddHHmmss").format(technology.getEndTime()), Double.parseDouble(technology.getDescribe()));
			}
			CategoryDataset dataset = DatasetUtilities.createCategoryDataset(technologyList.get(0).getName(), keyedValues);
			jfreechart = ChartFactory.createLineChart(technologyList.get(0).getName(), "", "", dataset, PlotOrientation.VERTICAL, true, false, false);

			Font font = new Font("微软雅黑", 1, 20);
			TextTitle title = new TextTitle("历次评估符合度对比折线图");
			title.setFont(font);
			title.setPaint(new Color(95, 73, 122));
			jfreechart.setTitle(title);
			jfreechart.getLegend().setItemFont(new Font("宋体", 0, 12));

			jfreechart.setBackgroundPaint(new Color(220, 220, 220));
			jfreechart.setBorderVisible(true);
			jfreechart.setBorderPaint(new Color(211, 223, 238));

			CategoryPlot categoryplot = jfreechart.getCategoryPlot();

			CategoryAxis categoryAxis = categoryplot.getDomainAxis();
			categoryAxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
			categoryAxis.setTickLabelPaint(new Color(95, 73, 122));

			ValueAxis valueAxis = categoryplot.getRangeAxis();
			valueAxis.setTickLabelFont(new Font("微软雅黑", 0, 12));
			valueAxis.setTickLabelPaint(new Color(95, 73, 122));
		}
		return jfreechart;
	}

	public AssessResultDao getAssessResultDao() {
		return assessResultDao;
	}

	public void setAssessResultDao(AssessResultDao assessResultDao) {
		this.assessResultDao = assessResultDao;
	}
}
