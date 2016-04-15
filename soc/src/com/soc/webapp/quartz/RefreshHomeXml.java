package com.soc.webapp.quartz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class RefreshHomeXml
{
  public void writeXml()
  {
    //System.out.println("start refreshing xml.......");
    try
    {
      File file = new File("/usr/local/tomcat6/webapps/soc/swf/angular1.xml");
      
      //File file1 = new File("/usr/local/tomcat6/webapps/soc/swf/test1.xml");
      
      if (!file.exists())
      {
        try
        {
          file.createNewFile();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
      
     /* if(!file1.exists())
      {
          try
          {
              file1.createNewFile();
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
      }*/
      
      /*FileOutputStream fos1 = new FileOutputStream(file1);*/
      
      FileOutputStream fos = new FileOutputStream(file);

      StringBuffer content = new StringBuffer();
      
      content.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
      content.append("<chart showPivotBorder=\"1\" pivotBorderThickness=\"2\"pivotBorderColor=\"FFFFFF\" pivotBgColor=\"333333\" pivotRadius=\"15\" origH=\"200\"origW=\"350\" autoScale=\"1\" showTickMarks=\"0\" showTickValues=\"0\"gaugeBorderThickness=\"1\" gaugeBorderColor=\"666666\" showGaugeBorder=\"1\"gaugeInnerRadius=\"100\" gaugeOuterRadius=\"160\" basefontColor=\"000000\"showBorder=\"0\" upperLimit=\"4\" lowerLimit=\"0\" bgColor=\"FFFFFF\" bgAlpha=\"0\" >\n");

      content.append("<colorRange>\n");
      content.append("<color code=\"00CD00\" maxValue=\"1\" minValue=\"0\" />\n");
      content.append("<color code=\"00B2EE\" maxValue=\"2\" minValue=\"1\" />\n");
      content.append("<color code=\"FFFF00\" maxValue=\"3\" minValue=\"2\" />\n");
      content.append("<color code=\"FF0000\" maxValue=\"4\" minValue=\"3\" />\n");
      content.append("</colorRange>\n");
      content.append("<dials>\n");
      content.append("<dial bgColor=\"333333\" borderThickness=\"2\" topWidth=\"1\"baseWidth=\"30\" borderAlpha=\"100\" borderColor=\"FFFFFF\" radius=\"95\"value=\"1\" />\n");

      content.append("</dials>\n");
      content.append("<trendpoints>\n");
      content.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"00CD00\"innerRadius=\"40\" endValue=\"1\" startValue=\"0\" displayValue=\"safe\" />\n");

      content.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"00B2EE\"innerRadius=\"40\" endValue=\"2\" startValue=\"1\" displayValue=\"low\" />\n");

      content.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"FFFF00\"innerRadius=\"40\" endValue=\"3\" startValue=\"2\" displayValue=\"medium\" />\n");

      content.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"FF0000\"innerRadius=\"40\" endValue=\"4\" startValue=\"3\" displayValue=\"high\" />\n");

      content.append("</trendpoints>\n");
      content.append("<styles>\n");
      content.append("<definition>\n");
      content.append("<style type=\"font\" color=\"FFFFFF\" isHTML=\"1\" bold=\"1\" size=\"12\"name=\"labelFont\" />\n");

      content.append("<style type=\"glow\" alpha=\"45\" color=\"000000\" name=\"labelGlow\" />\n");
      content.append("</definition>\n");
      content.append("<application>\n");
      content.append("<apply styles=\"labelFont,labelGlow\" toObject=\"TRENDVALUES\" />\n");
      content.append("</application>\n");
      content.append("</styles>\n");
      content.append("</chart>\n");

      fos.write(new String(content.toString().getBytes(), "GBK").getBytes());

      fos.close();
      
    /*  content1.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
      content1.append("<chart showPivotBorder=\"1\" pivotBorderThickness=\"2\"pivotBorderColor=\"FFFFFF\" pivotBgColor=\"333333\" pivotRadius=\"15\" origH=\"200\"origW=\"350\" autoScale=\"1\" showTickMarks=\"0\" showTickValues=\"0\"gaugeBorderThickness=\"1\" gaugeBorderColor=\"666666\" showGaugeBorder=\"1\"gaugeInnerRadius=\"100\" gaugeOuterRadius=\"160\" basefontColor=\"000000\"showBorder=\"0\" upperLimit=\"4\" lowerLimit=\"0\" bgColor=\"FFFFFF\" bgAlpha=\"0\" >\n");

      content1.append("<colorRange>\n");
      content1.append("<color code=\"00CD00\" maxValue=\"1\" minValue=\"0\" />\n");
      content1.append("<color code=\"00B2EE\" maxValue=\"2\" minValue=\"1\" />\n");
      content1.append("<color code=\"FFFF00\" maxValue=\"3\" minValue=\"2\" />\n");
      content1.append("<color code=\"FF0000\" maxValue=\"4\" minValue=\"3\" />\n");
      content1.append("</colorRange>\n");
      content1.append("<dials>\n");
      content1.append("<dial bgColor=\"333333\" borderThickness=\"2\" topWidth=\"1\"baseWidth=\"30\" borderAlpha=\"100\" borderColor=\"FFFFFF\" radius=\"95\"value=\"1\" />\n");

      content1.append("</dials>\n");
      content1.append("<trendpoints>\n");
      content1.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"00CD00\"innerRadius=\"40\" endValue=\"1\" startValue=\"0\" displayValue=\"正常\" />\n");

      content1.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"00B2EE\"innerRadius=\"40\" endValue=\"2\" startValue=\"1\" displayValue=\"低风险\" />\n");

      content1.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"FFFF00\"innerRadius=\"40\" endValue=\"3\" startValue=\"2\" displayValue=\"中风险\" />\n");

      content1.append("<point borderColor=\"FFFFFF\" radius=\"100\" alpha=\"30\" color=\"FF0000\"innerRadius=\"40\" endValue=\"4\" startValue=\"3\" displayValue=\"高风险\" />\n");

      content.append("</trendpoints>\n");
      content.append("<styles>\n");
      content.append("<definition>\n");
      content.append("<style type=\"font\" color=\"FFFFFF\" isHTML=\"1\" bold=\"1\" size=\"12\"name=\"labelFont\" />\n");

      content.append("<style type=\"glow\" alpha=\"45\" color=\"000000\" name=\"labelGlow\" />\n");
      content.append("</definition>\n");
      content.append("<application>\n");
      content.append("<apply styles=\"labelFont,labelGlow\" toObject=\"TRENDVALUES\" />\n");
      content.append("</application>\n");
      content.append("</styles>\n");
      content.append("</chart>\n");*/

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}