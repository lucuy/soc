package com.soc.service.risk.importfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.soc.model.risk.VulnerabilityAssessment;
/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2013-1-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ImportVA
{
//   public static AssetRiskService assetRiskManager;
    
    public static List<VulnerabilityAssessment> readRead(File path)
    {
        String sheetName = null;
        int sheetCount = -1;
        int rowCount = -1;
        int cellCount = -1;
        
        List<List> list1 = new ArrayList<List>();
        
        VulnerabilityAssessment aulnerabilityAssessment = null;
        
        List<VulnerabilityAssessment> list = new ArrayList<VulnerabilityAssessment>();
        
        try
        {
            HSSFWorkbook wk = new HSSFWorkbook(new FileInputStream(path));
            
            sheetCount = wk.getNumberOfSheets();
            HSSFSheet sheet = wk.getSheetAt(0);
            
            rowCount = sheet.getLastRowNum();
            for (int i = 2; i <= rowCount; i++)
            {
                
                HSSFRow row = sheet.getRow(i);
                
                cellCount = row.getLastCellNum();
                List<HSSFCell> lists = new ArrayList<HSSFCell>();
                for (int k = 0; k <= cellCount; k++)
                {
                    
                    HSSFCell cell = row.getCell(k);
                    
                    lists.add(cell);
                    
                }
                
                aulnerabilityAssessment = new VulnerabilityAssessment();
                
                //1
                aulnerabilityAssessment.setVulnerabilityAssessmentIp(lists.get(0).getStringCellValue().replaceAll(" ", ""));
                
                //2紧急风险
                aulnerabilityAssessment.setVulnerabilityAssessmentEmergencyRisk((int)lists.get(1).getNumericCellValue());
                
                //3高级风险
                aulnerabilityAssessment.setVulnerabilityAssessmentSeniorRisk((int)lists.get(2).getNumericCellValue());
                
                //4中级风险
                aulnerabilityAssessment.setVulnerabilityAssessmentIntermediateRisk((int)lists.get(2).getNumericCellValue());
                
                //5低级风险
                aulnerabilityAssessment.setVulnerabilityAssessmentLlowerRisk((int)lists.get(2).getNumericCellValue());
                
                //6信息风险
                aulnerabilityAssessment.setVulnerabilityAssessmentIinformationRisk((int)lists.get(2).getNumericCellValue());
                
                list.add(aulnerabilityAssessment);
               
            }
            
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
                
    }

}