package com.util.reportForm.chart;


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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.util.reportForm.datadeal.ExtendedActionServlet;
import com.util.reportForm.util.dataBase.DateSource;
    
public class BarAndPieChart {     
  

	public static void main(String[] args)      
    {     
    	BarAndPieChart chartD = new BarAndPieChart();     
        //chartD.createBarDemo("bar1.jpg");     
        //chartD.createPieDemo("pie.jpg"); 
//    	List list = new ArrayList();
//    	DateSource date = null;
//    	for(int i=0;i<3;i++){
//    		date = new DateSource();
//        	date.setDate((i+1)*100.0);
//        	date.setItemX("火箭"+(i+1));
//        	list.add(date);
//    	}
//
//    	chartD.createPieDemo("a","d:\\",list,"统计视图");

//    	List list = new ArrayList();
//    	DateSource date = null;
//    	for(int i=0;i<3;i++){
//    		date = new DateSource();
//    		//统计个数
//        	date.setDate((i+1)*100.0);
//        	//名称
//        	date.setItemX("AA"+(i+1));
//        	//名称图例
//        	date.setItemY("火箭"+(i+1));
//        	list.add(date);
//    	}
//    	chartD.createBarDemo("ping.jpg","c:\\",list,"统计视图样例","X坐标","Y坐标");     
      
    }     
         
    //创建柱状图  
    
    public void createBarDemo(String jpgname,String path,List list,String title,String titlex,String titley,String name)     
    {     
        CategoryDataset dataset = getBarDataset(list);     
        JFreeChart chart = ChartFactory.createBarChart3D(     
                title, titlex,titley, dataset, PlotOrientation.VERTICAL,      
                true,false,false);     
        
        /*
         * VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭,
         * 使用的关闭抗锯齿后，字体尽量选择12到14号的宋体字,这样文字最清晰好看
         */
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        chart.setTextAntiAlias(false);
        chart.setBackgroundPaint(Color.white);
        Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题
        chart.setTitle(new TextTitle(chart.getTitle().getText(),
				titleFont));
        Font font = new Font("宋体", 10, 15); 
//        TextTitle txtTitle = null; 
//        txtTitle = chart.getTitle(); 
//        txtTitle.setFont(font); 
        CategoryPlot plot = (CategoryPlot)chart.getPlot(); 
        plot.getDomainAxis().setLabelFont(font);
        plot.getDomainAxis().setTickLabelFont(font);
        plot.getRangeAxis().setLabelFont(font);
        plot.getRangeAxis().setTickLabelFont(font);
//      plot.setBackgroundPaint(new Color(237, 243, 255));
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        // x轴 // 分类轴网格是否可见
        plot.setDomainGridlinesVisible(true);
        // y轴 //数据轴网格是否可见
        plot.setRangeGridlinesVisible(false);
        
        chart.getLegend().setItemFont(font);
        
        BarRenderer renderer = new BarRenderer();
        // 设置柱子宽度
        renderer.setMaximumBarWidth(0.1);
        // 设置柱子高度
        renderer.setMinimumBarLength(0.2);
        // 设置平行柱的之间距离
        renderer.setItemMargin(0.0);
        // 设置柱子边框可见
        renderer.setDrawBarOutline(true);
        // 显示每个柱的数值，并修改该数值的字体属性
        renderer.setIncludeBaseInRange(true);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        plot.setRenderer(renderer);
        // 设置柱的透明度
        plot.setForegroundAlpha(1.0f);
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(font);// 轴标题

        domainAxis.setTickLabelFont(font);// 轴数值
        // 设置距离图片左端距离
        domainAxis.setLowerMargin(0.1);
        // 设置距离图片右端距离
        domainAxis.setUpperMargin(0.1);
        plot.setDomainAxis(domainAxis);
        
        this.delpic(jpgname);
        FileOutputStream jpg = null; 
        String pathname = "";
        if(jpgname==null||"".equals(jpgname)){
        	jpgname = "bar.jpg";
        }else{
        	if(!jpgname.endsWith(".jpg")&&!jpgname.endsWith(".JPG")){
        		jpgname=jpgname+".jpg";
        	}
        }
        if(path==null||"".equals(path)){
        	pathname = jpgname;
        }else{

        		pathname = path+File.separator+jpgname;
        }
        try {   
        	 File file = new File(pathname);
        	 if(file.exists()){
        		 file.delete();
        	 }
//        	//System.out.println(pathname);
            jpg = new FileOutputStream(pathname);     
            ChartUtilities.writeChartAsJPEG(jpg,0.5f,chart,800,600,null);
            
                 
        } catch (Exception e) {     
            // TODO 自动生成 catch 块     
            e.printStackTrace();     
        }     
        finally    
        {     
            try {     
                jpg.close();     
            } catch (IOException e) {     
                // TODO 自动生成 catch 块     
                e.printStackTrace();     
            }     
        }     
    }     
    
    //获取柱状图数据   
    
    private CategoryDataset getBarDataset(List list) {     
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();     
        Iterator it = null;
        DateSource datesource = null;
		String itemX = "";
		String itemY = "";
        if(list!=null&&list.size()>0){
        	it = list.iterator();
        	while(it.hasNext()){
        		datesource = (DateSource)it.next();
        		itemX = datesource.getItemX();
        		itemY = datesource.getItemY();
        		if(itemX==null){
        			itemX="";
				}
        		if(itemY==null){
        			itemY="";
				}
        		dataset.setValue(datesource.getDate(),itemY,itemX);
        	}
        }
        return dataset;     
    }    
         
    //创建饼图     
    public void createPieDemo(String jpgname,String path,List list,String title,String name)     
    {     
        DefaultPieDataset dataset = getPieDataset(list);     
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);  
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        Font font = new Font("宋体", 10, 15); 
        Font titleFont = new Font("黑体", Font.PLAIN, 20); // 图片标题
        chart.setTitle(new TextTitle(chart.getTitle().getText(),
				titleFont));
        chart.setBackgroundPaint(Color.white);
        PiePlot pieplot = (PiePlot)chart.getPlot(); 
        pieplot.setBackgroundPaint(Color.white);
        pieplot.setLabelFont(font);
        pieplot.setCircular(true);
        pieplot.setSectionPaint(0, new Color(0xF7, 0x79, 0xED)); 
        //设定背景透明度（0-1.0之间） 
        pieplot.setBackgroundAlpha(0.6f); 
        //设定前景透明度（0-1.0之间） 
        pieplot.setForegroundAlpha(0.8f); 
        pieplot.setLabelGap(0.02D);
        pieplot.setCircular(false);
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} {2}", NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
        // 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
        pieplot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
        "{0}={1}"));
        
        pieplot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
       
        PiePlot3D plot3D = (PiePlot3D)pieplot;
        plot3D.setDepthFactor(0);
        
        chart.getLegend().setItemFont(font); 
        
        this.delpic();
        
        FileOutputStream jpg = null;  
        String pathname = "";
        if(jpgname==null||"".equals(jpgname)){
        	jpgname = "pie.jpg";
        }else{
        	if(!jpgname.endsWith(".jpg")&&!jpgname.endsWith(".JPG")){
        		jpgname=jpgname+".jpg";
        	}
        }
        if(path==null||"".equals(path)){
        	pathname = jpgname;
        }else{

        		pathname = path+File.separator+jpgname;
        }
        try {     
        	 File file = new File(pathname);
        	 if(file.exists()){
        		 file.delete();
        	 }
//        	//System.out.println(pathname);
            jpg = new FileOutputStream(pathname);     
            ChartUtilities.writeChartAsJPEG(jpg,0.5f,chart,800,600,null);     
                 
                 
        } catch (Exception e) {     
            // TODO 自动生成 catch 块     
            e.printStackTrace();     
        }     
        finally    
        {     
            try {     
                jpg.close();     
            } catch (IOException e) {     
                // TODO 自动生成 catch 块     
                e.printStackTrace();     
            }     
        }     
    }     
    
    //获取饼图数据     
    /*
    public DefaultPieDataset getPieDataset(Map map) {     
        DefaultPieDataset dataset = new DefaultPieDataset();     
        //dataset.setValue("苹果", 100);  
        Set set = null;
        Iterator it = null;
		Map.Entry entry = null;
		String key = "";
		Double value = null;
        if(map!=null&&map.size()>0){
        	
        		set = map.entrySet();
        		if(set!=null){
        			it = set.iterator();
        			while(it.hasNext()){
        				entry = (Map.Entry)it.next();
        				key = (String)entry.getKey();
        				if(key==null){
        					key="";
        				}
        				value =  (Double)entry.getValue();
        				dataset.setValue(key,value);
        				////System.out.println("key:"+key+"==="+"value:"+value);
        			}
        		}
        	
        }
        
   
        return dataset;     
    }  */   
    
    public DefaultPieDataset getPieDataset(List list) {     
        DefaultPieDataset dataset = new DefaultPieDataset();   
        Iterator it = null;
        DateSource datesource = null;
		String key = "";
        if(list!=null&&list.size()>0){
        	it = list.iterator();
        	while(it.hasNext()){
        		datesource = (DateSource)it.next();
        		key = datesource.getItemX();
        		if(key==null){
					key="";
				}
        		dataset.setValue(key, datesource.getDate());
        	}
        }
        
   
        return dataset;     
    }   
         
    public void delpic(){
    	File file = new File(ExtendedActionServlet.setupPath+"CommonFile/QueryCount");
    	if(file.isDirectory()){
	    	if(file.listFiles().length>0){
				File[] files = file.listFiles();
		    	for (int i = 0; i < files.length; i++) {
					File file2 = files[i];
					file2.delete();
				}
	    	}
    	}
    }
    public void delpic(String picName){
    	File file = new File(ExtendedActionServlet.setupPath+"CommonFile/QueryCount"+picName);
    	if(file.exists()){
    		file.delete();
    	}
    }
}     

