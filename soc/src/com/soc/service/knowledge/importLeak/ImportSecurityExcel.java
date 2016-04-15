package com.soc.service.knowledge.importLeak;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.soc.model.knowledge.Security;
import com.util.MyException;
import com.util.StringUtil;
import com.util.checkImport.ValidationData;

public class ImportSecurityExcel {
	
	public List<Security> readRead(File upTar) throws MyException, FileNotFoundException, IOException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String sheetName = null;
	        int sheetCount = -1;
	        int rowCount = -1;
	        int cellCount = -1;
	        List<Security> list= new ArrayList<Security>();
	        Security security =null;
	        HSSFWorkbook wk = new HSSFWorkbook(new FileInputStream(upTar));
            
            sheetCount = wk.getNumberOfSheets();
            HSSFSheet sheet = wk.getSheetAt(0);
            
            rowCount = sheet.getLastRowNum();
            for (int i = 2; i <= rowCount; i++)
            {
            
				
			  
                HSSFRow row = sheet.getRow(i);
                
                cellCount = row.getLastCellNum();
                List<HSSFCell> lists = new ArrayList<HSSFCell>();
                for (int k = 0; k < cellCount; k++)
                {
                    
                    HSSFCell cell = row.getCell(k);
                    lists.add(cell);
                  
                    
                }
                security = new Security();
                if (lists.get(0)!=null) {
					
                lists.get(0).setCellType(HSSFCell.CELL_TYPE_STRING);//设置Excel读取类型。
                //标题
                String securityTitle =lists.get(0).getStringCellValue();
                if(securityTitle.length()>50)
                	throw new MyException("标题长度不能大于50");
                if (StringUtil.isNotEmpty(securityTitle)) {
					int num = 0;
					 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
					try {
						num = (int) Double.parseDouble(securityTitle.trim());
						securityTitle = num+"";
					} catch (Exception e) {
						
					}
				}else{
					throw new MyException("标题不能为空");
				}
                if(ValidationData.regexString(securityTitle))
                	throw new MyException("标题不能包含特殊字符");
                	
                security.setSecurityTitle(securityTitle);
                }else{
                	throw new MyException("标题不能为空");
                }
                
                //发布时间
                String securityDate = "";
                if (lists.get(1)!=null) {
					
                switch (lists.get(1).getCellType()) {
        		case HSSFCell.CELL_TYPE_NUMERIC:
        			if(HSSFDateUtil.isCellDateFormatted(lists.get(1))){
        				
        				securityDate= sdf.format(HSSFDateUtil.getJavaDate(lists.get(1).getNumericCellValue())).toString();
        			}
        			break;
        		case HSSFCell.CELL_TYPE_STRING:
        			System.out.println(lists.get(1).getStringCellValue());
        			securityDate= lists.get(1).getStringCellValue();
        			break;
        		
        		}
               
                if (StringUtil.isNotEmpty(securityDate)) {
                	
                	try {
						if (sdf.parse(securityDate).getTime()+86400000>new Date().getTime()+86400000) {
							throw new MyException("发布时间不能大于今天");
						}else{
							security.setSecurityDate(sdf.parse(securityDate));
							security.setSecurityCreateDate(Calendar.getInstance().getTime());
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					throw new MyException("发布时间不能为空");
				}
                }else{
                	throw new MyException("发布时间不能为空");
                }
                //来源
                if (lists.get(2)!=null) {
					
                lists.get(2).setCellType(HSSFCell.CELL_TYPE_STRING);//设置Excel读取类型。
                String source =lists.get(2).getStringCellValue();
                if(source.length()>50)
                	throw new MyException("来源长度不能大于50");
                if (StringUtil.isNotEmpty(source)) {
                	int num = 0;
					 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
					try {
						num = (int) Double.parseDouble(source.trim());
						source = num+"";
					} catch (Exception e) {
						
					}
					security.setSource(source.replaceAll("\"", "'"));
				}else{
					throw new MyException("来源不能为空");
				}
                }else{
                	throw new MyException("来源不能为空");
                }
                //描述
                if (lists.get(3)!=null) {
					
                lists.get(3).setCellType(HSSFCell.CELL_TYPE_STRING);//设置Excel读取类型。 
                String details = lists.get(3).getStringCellValue();
                if (StringUtil.isNotEmpty(details)) {
                	int num = 0;
					 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
					try {
						num = (int) Double.parseDouble(details.trim());
						details = num+"";
					} catch (Exception e) {
						
					}
					security.setSecurityDetails("<p>"+details+"</p>");
				}else{
					throw new MyException("描述不能为空");
				}
                }else{
                	throw new MyException("描述不能为空");
                }
            
                list.add(security);
            }
           
		return list;
	}
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  String securityDate ="2014/06/19";
		  securityDate = securityDate.replaceAll("/", "-");
		  System.out.println(sdf.parse(securityDate).getTime());
		  System.err.println(Calendar.getInstance().getTime());
	}
	
}
