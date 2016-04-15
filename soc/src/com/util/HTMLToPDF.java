package com.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;
/**
 * 
 * <把html变成pdf>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-4-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HTMLToPDF {
	public static void htmlToPdf(String inputPath,String outputPath,String imagePath,String fontsPath) throws Exception {  
        OutputStream os = new FileOutputStream(outputPath);  
        ITextRenderer renderer = new ITextRenderer();  
        ITextFontResolver fontResolver = renderer.getFontResolver();  
        fontResolver.addFont(fontsPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
        StringBuffer html = new StringBuffer();  
        // DOCTYPE 必需写否则类似于 这样的字符解析会出现错误  
       // FileReader reader = new FileReader(inputPath);
        FileInputStream fileInputStream = new FileInputStream(inputPath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = "";
        while((line=bufferedReader.readLine())!=null){
        	html.append(line);
        }
     //   //System.out.println(html);
        renderer.setDocumentFromString(html.toString());  
        // 解决图片的相对路径问题  
         renderer.getSharedContext().setBaseURL("file:"+imagePath);  
        renderer.layout();  
        renderer.createPDF(os);  
        os.close();  
    }  
}
