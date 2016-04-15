package com.util.load.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class CreateJFreeChartLine {

/**
*/
public static void main(String[] args) {
   CategoryDataset dataset = createDataset();
   JFreeChart freeChart = createChart(dataset);
   saveAsFile(freeChart, "http://localhost:8080/", 600, 400);
}

public static void saveAsFile(JFreeChart chart, String outputPath,
    int weight, int height) {
   FileOutputStream out = null;
   try {
    File outFile = new File(outputPath);
    if (!outFile.getParentFile().exists()) {
     outFile.getParentFile().mkdirs();
    }
    out = new FileOutputStream(outputPath);
    //ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
    ChartUtilities.writeChartAsJPEG(out, chart, 600, 400);
    out.flush();
   } catch (FileNotFoundException e) {
    e.printStackTrace();
   } catch (IOException e) {
    e.printStackTrace();
   } finally {
    if (out != null) {
     try {
      out.close();
     } catch (IOException e) {
      // do nothing
     }
    }
   }
}

public static JFreeChart createChart(CategoryDataset categoryDataset) {
   JFreeChart jfreechart = ChartFactory.createLineChart("TeamCoal", 
     "",
     "(Unit/%)", 
     categoryDataset, // dataset
     PlotOrientation.VERTICAL, true, // legend
     false, // tooltips
     false); // URLs
  
   CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
   plot.setBackgroundAlpha(0.5f);
   plot.setForegroundAlpha(0.5f);
  
   return jfreechart;
}

/**
*
*/
public static CategoryDataset createDataset() {
  
   String[] rowKeys = { "TeamCoal" };
   String[] colKeys = { "TeamCoal20130506", "TeamCoal20130509", "TeamCoal20130513" };
  
   double[][] data = { { 4, 3, 1 }, };
  
   // DefaultCategoryDataset categoryDataset = new
   // DefaultCategoryDataset();
   // categoryDataset.addValue(10, "rowKey", "colKey");

   return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
}
}
