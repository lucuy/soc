package com.soc.service.asset.risk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetRiskValue;
import com.soc.service.asset.AssetService;
import com.util.MyException;
import com.util.StringUtil;
import com.util.checkImport.ValidationData;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author yinhaiping
 * @version [版本号, 2013-1-30]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ImportRisk {
	public static AssetRiskService assetRiskManager;

    
    public static List<AssetRiskValue> readRead(File path,AssetService assetManager) throws FileNotFoundException, IOException, MyException
    {
        String sheetName = null;
        int sheetCount = -1;
        int rowCount = -1;
        int cellCount = -1;
        
       // List<List> list1 = new ArrayList<List>();
        
        AssetRiskValue assetRiskValue = null;
        
        List<AssetRiskValue> list = new ArrayList<AssetRiskValue>();
        
        
            HSSFWorkbook wk = new HSSFWorkbook(new FileInputStream(path));
            
            sheetCount = wk.getNumberOfSheets();
            HSSFSheet sheet = wk.getSheetAt(0);
            
            rowCount = sheet.getLastRowNum();
            for (int i = 2; i <= rowCount; i++)
            {
                
                HSSFRow row = sheet.getRow(i);
                row.getCell(0).setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
                row.getCell(1).setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
                cellCount = row.getLastCellNum();
                
                List<HSSFCell> lists = new ArrayList<HSSFCell>();
                for (int k = 0; k <= cellCount; k++)
                {
                    
                    HSSFCell cell = row.getCell(k);
                    
                    lists.add(cell);
                    
                }
                	 assetRiskValue = new AssetRiskValue();
                	/* Object obj1 = lists.get(0).getStringCellValue();
                	Object obj = lists.get(1).getStringCellValue();
                	if(obj!=null&&obj!=""){
                	try{
                		int num = (int) Double.parseDouble(obj.toString());
                		obj = num;
                		assetRiskValue.setRiskName(obj.toString());
                	}catch (Exception e) {
                		assetRiskValue.setRiskName(lists.get(1).getStringCellValue());
					}
                	 
                	int RiskPossibility= (int)lists.get(2).getNumericCellValue();
           		 int RiskInfluence =(int)lists.get(3).getNumericCellValue();
           		if(RiskInfluence>0&&RiskInfluence<6&&RiskPossibility>0&&RiskPossibility<6){
           		 assetRiskValue.setRiskPossibility(RiskPossibility);
                 assetRiskValue.setRiskInfluence(RiskInfluence);
                 
                 list.add(assetRiskValue);
           		} }*/
                	//李长红优化--20140505
                	 //资产名称
                	 String assetName = lists.get(0).getStringCellValue();
                	
                	 if(StringUtil.isNotEmpty(assetName)){
                		 if(assetName.length()>50){
                     		throw new MyException("资产名长度不能大于50");
                     	}
                		 int assetname=0;
                    	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                    	 try {
                    		 assetname=(int)Double.parseDouble(assetName.trim());
                    		 assetName=""+assetname+"";
    					} catch (Exception e) {
    						// TODO: handle exception
    					}
                		 long assetId =0;
                		 if (ValidationData.regexString(assetName)) {
                		 List<Asset> assets=assetManager.queryByName(assetName.trim());
                		 if(assets.size()>0){
                			 //资产ID
                			 assetId = assets.get(0).getAssetId();
                			 assetRiskValue.setRiskAssetId(assetId);
                			 assetRiskValue.setRiskAssetName(assetName.trim());
                		 }else{
                			 throw new MyException("资产不存在!"); 
                		 }}else {
                			 throw new MyException("资产名不能包含特殊字符");
						}
                	 }else{
                		 throw new MyException("资产名称不能为空!");
                	 }
                	 //威胁名称
                	 /**
                	  * 李长红修改
                	  */
                	 String riskName=lists.get(1).getStringCellValue();
                	 String[] riskNames=new String[]{"病毒和恶意代码攻击","利用系统和软件漏洞取得权限","口令猜测","滥用威胁","嗅探攻击","审计行为失效","信息探测","社会工工程"};
                	 List<String> riskNamesLsit = Arrays.asList(riskNames);
                	 if(StringUtil.isNotEmpty(riskName)){
                		
							if (riskNamesLsit.contains(riskName)) {
								assetRiskValue.setRiskName(riskName);
							}else{
								throw new MyException("威胁名称不存在!"); 	
							}
						
                	 }else{
                		 throw new MyException("威胁名称不能为空!"); 
                	 }
                	 //可能性
                	 String riskPossibility = lists.get(2).getStringCellValue();
                	 int riskPossibilityResult=0;
                	 if (StringUtil.isNotEmpty(riskPossibility)) {
						if("不可能".equals(riskPossibility)){
							riskPossibilityResult=1;
						}else if ("可能性很小".equals(riskPossibility)) {
							riskPossibilityResult=2;
						}else if ("可能".equals(riskPossibility)) {
							riskPossibilityResult=3;
						}else if ("非常可能".equals(riskPossibility)) {
							riskPossibilityResult=4;
						}else if ("不可避免".equals(riskPossibility)) {
							riskPossibilityResult=5;
						}else{
							throw new MyException("威胁可能性值不正确!");
						}
						
					}else{
						throw new MyException("威胁可能性值为空!"); 
					}
                	assetRiskValue.setRiskPossibility(riskPossibilityResult);
                	 //影响
                	 String riskInfluence=lists.get(3).getStringCellValue();
             		int riskInfluenceResult = 0;
             		 if (StringUtil.isNotEmpty(riskInfluence)) {
 						if("可忽略".equals(riskInfluence)){
 							riskInfluenceResult=1;
 						}else if ("可忍受".equals(riskInfluence)) {
 							riskInfluenceResult=2;
 						}else if ("明显损失".equals(riskInfluence)) {
 							riskInfluenceResult=3;
 						}else if ("重大损失".equals(riskInfluence)) {
 							riskInfluenceResult=4;
 						}else if ("全部损失".equals(riskInfluence)) {
 							riskInfluenceResult=5;
 						}else{
 							throw new MyException("威胁影响值不正确!");
 						}
 						
 					}else{
 						throw new MyException("威胁影响值为空!"); 
 					}
             		assetRiskValue.setRiskInfluence(riskInfluenceResult);
             		 //威胁值结果
             		int result = 0;
             		if(riskInfluenceResult==1){
            			result = 1;
            		}else if(riskInfluenceResult==2){
            			if(riskPossibilityResult==1){
            				result = 1;
            			}else if(riskPossibilityResult==2){
            				result = 1;
            			}else if(riskPossibilityResult==3){
            				result = 2;
            			}else if(riskPossibilityResult==4){
            				result = 2;
            			}else if(riskPossibilityResult==5){
            				result = 2;
            			}
            			
            		}else if(riskInfluenceResult==3){
            			if(riskPossibilityResult==1){
            				result = 1;
            			}else if(riskPossibilityResult==2){
            				result = 2;
            			}else if(riskPossibilityResult==3){
            				result = 2;
            			}else if(riskPossibilityResult==4){
            				result = 3;
            			}else if(riskPossibilityResult==5){
            				result = 3;
            			}
            		}else if(riskInfluenceResult==4){
            			if(riskPossibilityResult==1){
            				result = 1;
            			}else if(riskPossibilityResult==2){
            				result = 2;
            			}else if(riskPossibilityResult==3){
            				result = 3;
            			}else if(riskPossibilityResult==4){
            				result = 4;
            			}else if(riskPossibilityResult==5){
            				result = 4;
            			}
            		}else if(riskInfluenceResult==5){
            			if(riskPossibilityResult==1){
            				result = 2;
            			}else if(riskPossibilityResult==2){
            				result = 2;
            			}else if(riskPossibilityResult==3){
            				result = 3;
            			}else if(riskPossibilityResult==4){
            				result = 4;
            			}else if(riskPossibilityResult==5){
            				result = 5;
            			}
            		}
             		assetRiskValue.setRiskResult(result);
                	 list.add(assetRiskValue);
                	 }
       
        return list;
                
    }


		// List<List> list1 = new ArrayList<List>();

	public static AssetRiskService getAssetRiskManager() {
		return assetRiskManager;
	}

	public static void setAssetRiskManager(AssetRiskService assetRiskManager) {
		ImportRisk.assetRiskManager = assetRiskManager;
	}
	 

}