package com.util;

 
 import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;

import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
 
 public class MySpiderWebPlot extends SpiderWebPlot
 {
   private static final long serialVersionUID = 4005814203754627127L;
   private int ticks = 5;
   private static final int DEFAULT_TICKS = 5;
   private NumberFormat format = NumberFormat.getInstance();
   private static final double PERPENDICULAR = 90.0D;
   private static final double TICK_SCALE = 0.015D;
   private int valueLabelGap = 10;
   private static final int DEFAULT_GAP = 10;
   private static final double THRESHOLD = 15.0D;
 
   public MySpiderWebPlot(CategoryDataset createCategoryDataset)
   {
     super(createCategoryDataset);
   }
 
   protected void drawLabel(Graphics2D g2, Rectangle2D plotArea, double value, int cat, double startAngle, double extent) {
     super.drawLabel(g2, plotArea, value, cat, startAngle, extent);
     FontRenderContext frc = g2.getFontRenderContext();
     double[] transformed = new double[2];
     double[] transformer = new double[2];
     Arc2D arc1 = new Arc2D.Double(plotArea, startAngle, 0.0D, 0);
     for (int i = 1; i <= this.ticks; i++) {
       Point2D point1 = arc1.getEndPoint();
       double deltaX = plotArea.getCenterX();
       double deltaY = plotArea.getCenterY();
       double labelX = point1.getX() - deltaX;
       double labelY = point1.getY() - deltaY;
       double scale = i / this.ticks;
       AffineTransform tx = AffineTransform.getScaleInstance(scale, scale);
       AffineTransform pointTrans = AffineTransform.getScaleInstance(scale + 0.015D, scale + 0.015D);
       transformer[0] = labelX;
       transformer[1] = labelY;
       pointTrans.transform(transformer, 0, transformed, 0, 1);
       double pointX = transformed[0] + deltaX;
       double pointY = transformed[1] + deltaY;
       tx.transform(transformer, 0, transformed, 0, 1);
       labelX = transformed[0] + deltaX;
       labelY = transformed[1] + deltaY;
       double rotated = 90.0D;
       AffineTransform rotateTrans = AffineTransform.getRotateInstance(Math.toRadians(rotated), labelX, labelY);
       transformer[0] = pointX;
       transformer[1] = pointY;
       rotateTrans.transform(transformer, 0, transformed, 0, 1);
       double x1 = transformed[0];
       double y1 = transformed[1];
       rotated = -90.0D;
       rotateTrans = AffineTransform.getRotateInstance(Math.toRadians(rotated), labelX, labelY);
       rotateTrans.transform(transformer, 0, transformed, 0, 1);
       Composite saveComposite = g2.getComposite();
       g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
       g2.draw(new Line2D.Double(transformed[0], transformed[1], x1, y1));
       if (startAngle == getStartAngle()) {
         String label = this.format.format(i / this.ticks * getMaxValue());
         LineMetrics lm = getLabelFont().getLineMetrics(label, frc);
         double ascent = lm.getAscent();
         if (Math.abs(labelX - plotArea.getCenterX()) < 15.0D) {
           labelX += this.valueLabelGap;
           labelY += ascent / 2.0D;
         } else if (Math.abs(labelY - plotArea.getCenterY()) < 15.0D) {
           labelY += this.valueLabelGap;
         } else if (labelX >= plotArea.getCenterX()) {
           if (labelY < plotArea.getCenterY()) {
             labelX += this.valueLabelGap;
             labelY += this.valueLabelGap;
           } else {
             labelX -= this.valueLabelGap;
             labelY += this.valueLabelGap;
           }
         }
         else if (labelY > plotArea.getCenterY()) {
           labelX -= this.valueLabelGap;
           labelY -= this.valueLabelGap;
         } else {
           labelX += this.valueLabelGap;
           labelY -= this.valueLabelGap;
         }
 
         g2.setPaint(getLabelPaint());
         g2.setFont(getLabelFont());
         g2.drawString(label, (float)labelX, (float)labelY);
       }
       g2.setComposite(saveComposite);
     }
   }
 }

