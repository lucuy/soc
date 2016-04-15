/*
 * 文 件 名:  ExcelAsset.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-11-29
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.risk.export;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import com.util.DateUtil;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-11-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ExcelAssetGroup
{
    private HSSFWorkbook gwb = null;
    
    /**
     * 导出具体实现方法
     * 
     * @return
     */
    
    @SuppressWarnings("unchecked")
    public void export(List<Map> list, String title)
    {
        
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        
        // 定义行
        HSSFRow row0 = null;
        HSSFRow row1 = null;
        HSSFRow row2 = null;
        
        // 定义列
        HSSFCell cell = null;
        // 记录行数
        int iLineNumber = 0;
        // 记录列数
        int iColNumber = 0;
        
        // 样式0：生成顶部样式
        HSSFCellStyle csTop = wb.createCellStyle();
        csTop.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        csTop.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // TOP字体样式
        HSSFFont fTop = wb.createFont();
        fTop.setFontName("微软雅黑");
        fTop.setColor(HSSFColor.BLACK.index);
        fTop.setFontHeightInPoints(Short.valueOf(String.valueOf(16)));
        
        // 应用字体
        csTop.setFont(fTop);
        
        // 样式1：生成标题样式
        HSSFCellStyle csHeader = wb.createCellStyle();
        
        // 自定义标题背景色
        int[] iHeaderColor = {23, 55, 93};
        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.DARK_BLUE.index,
            (byte)iHeaderColor[0],
            (byte)iHeaderColor[1],
            (byte)iHeaderColor[2]);
        
        // 自定义正文背景色
        int[] iBodyColor = {242, 242, 242};
        palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index,
            (byte)iBodyColor[0],
            (byte)iBodyColor[1],
            (byte)iBodyColor[2]);
        
        // 自定义统计背景色
        int[] iSumColor = {146, 208, 80};
        palette.setColorAtIndex(HSSFColor.BRIGHT_GREEN.index,
            (byte)iSumColor[0],
            (byte)iSumColor[1],
            (byte)iSumColor[2]);
        
        // 设置这些样式
        csHeader.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
        csHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        csHeader.setWrapText(true); // 自动换行
        csHeader.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        csHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 边框样式
        csHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
        csHeader.setTopBorderColor(HSSFColor.BLACK.index);
        csHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        csHeader.setLeftBorderColor(HSSFColor.BLACK.index);
        csHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        csHeader.setBottomBorderColor(HSSFColor.BLACK.index);
        csHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
        csHeader.setRightBorderColor(HSSFColor.BLACK.index);
        
        // 标题字体样式
        HSSFFont fHeader = wb.createFont();
        fHeader.setFontName("微软雅黑");
        fHeader.setColor(HSSFColor.WHITE.index);
        fHeader.setFontHeightInPoints(Short.valueOf(String.valueOf(11)));
        fHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        // 应用字体
        csHeader.setFont(fHeader);
        
        // 样式2:生成正文样式
        HSSFCellStyle csBody = wb.createCellStyle();
        csBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        csBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        csBody.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        csBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 边框样式
        csBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
        csBody.setTopBorderColor(HSSFColor.BLACK.index);
        csBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        csBody.setLeftBorderColor(HSSFColor.BLACK.index);
        csBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        csBody.setBottomBorderColor(HSSFColor.BLACK.index);
        csBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
        csBody.setRightBorderColor(HSSFColor.BLACK.index);
        
        // 样式custom:生成正文样式
        HSSFCellStyle csBody2 = wb.createCellStyle();
        csBody2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        csBody2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        csBody2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        csBody2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 边框样式
        csBody2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        csBody2.setTopBorderColor(HSSFColor.BLACK.index);
        csBody2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        csBody2.setLeftBorderColor(HSSFColor.BLACK.index);
        csBody2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        csBody2.setBottomBorderColor(HSSFColor.BLACK.index);
        csBody2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        csBody2.setRightBorderColor(HSSFColor.BLACK.index);
        
        csBody2.setWrapText(true); // 自动换行
        
        // 设置这些样式
        csBody.setWrapText(true); // 自动换行
        
        // 正文字体
        HSSFFont fBody = wb.createFont();
        fBody.setFontName("微软雅黑");
        fBody.setColor(HSSFColor.BLACK.index);
        fBody.setFontHeightInPoints(Short.valueOf(String.valueOf(10)));
        
        // 应用字体
        csBody.setFont(fBody);
        
        // 样式3:合计标题样式
        HSSFCellStyle csSumTitle = wb.createCellStyle();
        csSumTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        csSumTitle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        csSumTitle.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
        csSumTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 边框样式
        csSumTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        csSumTitle.setTopBorderColor(HSSFColor.BLACK.index);
        csSumTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        csSumTitle.setLeftBorderColor(HSSFColor.BLACK.index);
        csSumTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        csSumTitle.setBottomBorderColor(HSSFColor.BLACK.index);
        csSumTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        csSumTitle.setRightBorderColor(HSSFColor.BLACK.index);
        
        // 应用字体
        csSumTitle.setFont(fHeader);
        
        // 样式4:合计内容样式
        HSSFCellStyle csSumBody = wb.createCellStyle();
        csSumBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        csSumBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        csSumBody.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
        csSumBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // 边框样式
        csSumBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
        csSumBody.setTopBorderColor(HSSFColor.BLACK.index);
        csSumBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        csSumBody.setLeftBorderColor(HSSFColor.BLACK.index);
        csSumBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        csSumBody.setBottomBorderColor(HSSFColor.BLACK.index);
        csSumBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
        csSumBody.setRightBorderColor(HSSFColor.BLACK.index);
        
        // 应用字体
        csSumBody.setFont(fBody);
        
        // 生成一个表格
        HSSFSheet sheet = wb.createSheet();
        wb.setSheetName(0, "Default");
        sheet.setForceFormulaRecalculation(true);
        
        // 填充TOP区域
        // --------------------
        CellRangeAddress addr = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(addr);
        
        // 生成一行
        row0 = sheet.createRow(iLineNumber++);
        // 应用样式
        row0.setRowStyle(csTop);
        row0.setHeightInPoints(Short.valueOf(String.valueOf(39)));
        
        // 填充TOP文字
        cell = row0.createCell(0);
        cell.setCellStyle(csTop);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(title + "(" + DateUtil.curDateStr8() + ")");
        
        // 生成一行
        row1 = sheet.createRow(iLineNumber++);
        // 应用样式
        row1.setHeightInPoints(Short.valueOf(String.valueOf(20)));
        
        // 填充基本信息标题
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 16));
        cell.setCellValue("资产组id");
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 16));
        cell.setCellValue("资产组名称");
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 16));
        cell.setCellValue("上级ID");
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 16));
        cell.setCellValue("创建时间");
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 23));
        cell.setCellValue("更新时间");
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 16));
        cell.setCellValue("描述");
        
       
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 16));
        cell.setCellValue("威胁值");
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 23));
        cell.setCellValue("脆弱性值");
        
        
        cell = row1.createCell(iColNumber);
        cell.setCellStyle(csHeader);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        sheet.setColumnWidth(iColNumber++, (256 * 16));
        cell.setCellValue("资产风险");
         
        
        // 2、填充基本信息内容
        for (int i = 0; i < list.size(); i++)
        {
            // 生成一行
            row2 = sheet.createRow(iLineNumber++);
            
            // 填充内容
            HashMap<String, Object> map = (HashMap)list.get(i);
            iColNumber = 0;
            
            //资产组Id
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(map.get("ASSET_GROUP_ID").toString());
            
            //资产组名称
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(map.get("ASSET_GROUP_NAME").toString());
            
            //上级id
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(map.get("ASSET_GROUP_PARENT_ID").toString());
            
            //创建时间
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(map.get("ASSET_GROUP_CREATE_DATE_TIME").toString());
            
            //更新时间
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(map.get("ASSET_GROUP_UPDATE_DATE_TIME").toString());
            
            //描述
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            if(map.get("ASSET_MEMO")!=null){
            	cell.setCellValue(map.get("ASSET_MEMO").toString());
            }else{
            	cell.setCellValue("");
            }
            
            
            
            
           
            
            //威胁值
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
              cell.setCellValue(map.get("RISK_THREATVALUE").toString());
           
            
            
            //脆弱性值
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
           
                cell.setCellValue(map.get("VAVULNERABILITYVALUE").toString());
            
            //资产风险值
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(map.get("ASSET_ASSET_VALUE").toString());
            
            /*cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 时间处理
            int point = map.get("AUDIT_OPERATION_TIME").toString().indexOf(".");
            if (point == -1)
            {
                cell.setCellValue(map.get("AUDIT_OPERATION_TIME").toString());
            }
            else
            {
                cell.setCellValue(map.get("AUDIT_OPERATION_TIME").toString().substring(0, point));
            }
            
            cell = row2.createCell(iColNumber++);
            cell.setCellStyle(csBody2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            
            //解析AUDIT_INFORMATION
            
            StringBuffer buf = new StringBuffer();
            JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(map.get("AUDIT_INFORMATION").toString());
            List output = (List)JSONSerializer.toJava(jsonArray);
            for (Object obj : output)
            {
                StringBuffer bufobj = new StringBuffer((String)obj); 
                bufobj.append("\r\n");
                buf.append(bufobj);
            }
            cell.setCellValue(buf.toString());*/
        }
        
        // 填充完成，返回工作簿对象
        this.gwb = wb;
    }
    
    public HSSFWorkbook getGwb()
    {
        return gwb;
    }
    
    public void setGwb(HSSFWorkbook gwb)
    {
        this.gwb = gwb;
    }
}
