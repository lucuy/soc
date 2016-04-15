/*
 * 文 件 名:  ImportAsset.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-12-19
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.asset.importAsset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.soc.model.asset.Asset;
import com.soc.model.asset.device.Category;
import com.soc.model.systemsetting.Collector;
import com.soc.service.asset.AssetService;
import com.soc.service.asset.device.CategoryService;
import com.soc.service.asset.system.AssetSystemService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.service.user.UserService;
import com.util.IpConvert;
import com.util.MyException;
import com.util.StringUtil;
import com.util.checkImport.ValidationData;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-12-19]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ImportAsset
{
	private UserService uService;
	private AssetSystemService asService;
	private AssetService assetManager;
	private CategoryService categoryService;
	private SettingCollectorService settingLoggerManager;
    public List<Asset> readRead(File path) throws MyException, FileNotFoundException, IOException
    {
        String sheetName = null;
        int sheetCount = -1;
        int rowCount = -1;
        int cellCount = -1;
        
        List<List> list1 = new ArrayList<List>();
        
        Asset asset = null;
        
        List<Asset> list = new ArrayList<Asset>();
        
        
            HSSFWorkbook wk = new HSSFWorkbook(new FileInputStream(path));
            
            sheetCount = wk.getNumberOfSheets();
            HSSFSheet sheet = wk.getSheetAt(0);
            
            rowCount = sheet.getLastRowNum();
        
				
			
            for (int i = 2; i <= rowCount; i++)
            {
                
                HSSFRow row = sheet.getRow(i);
                
                cellCount = row.getLastCellNum();
                if(cellCount<16)
                	throw new MyException("请填写完整的资产信息！");
                List<HSSFCell> lists = new ArrayList<HSSFCell>();
                for (int k = 0; k < cellCount; k++)
                {
                    
                    HSSFCell cell = row.getCell(k);
                  
                    lists.add(cell);
                  
                    
                }
               
                /* list1.add(lists);*/
               /* vulnerability = new Vulnerability();
                * 
                * 
                
                vulnerability.setVulnerabilityUniqueIdentification(lists.get(0).getStringCellValue());
                vulnerability.setVulnerabilityName(lists.get(1).getStringCellValue());
                vulnerability.setVulnerabilitySynopsis(lists.get(2).getStringCellValue());
                vulnerability.setVulnerabilitySolution(lists.get(3).getStringCellValue());
                vulnerability.setVulnerabilitySeeAlso(lists.get(4).getStringCellValue());
                vulnerability.setVulnerabilityType(lists.get(5).getStringCellValue());
                vulnerability.setVulnerabilityRiskFactor(lists.get(6).getStringCellValue());
                vulnerability.setVulnerabilityUpdateDateTime(lists.get(7).getStringCellValue());
                vulnerability.setVulnerabilityCvssMath(lists.get(8).getStringCellValue());
                vulnerability.setVulnerabilityCveID(lists.get(9).getStringCellValue());
                vulnerability.setVulnerabilityPluginPublicationDateTime(lists.get(10).getStringCellValue());
                vulnerability.setVulnerabilityNvtiSrc(lists.get(11).getStringCellValue());
                vulnerability.setVulnerabilityAttack(lists.get(12).getStringCellValue());
                vulnerability.setVulnerabilityPort(lists.get(13).getStringCellValue());
                vulnerability.setVulnerabilityService(lists.get(14).getStringCellValue());
                vulnerability.setVulnerabilityMemo(lists.get(15).getStringCellValue());
                vulnerability.setVulnerabilityOid(lists.get(16).getStringCellValue());
                vulnerability.setVulnerabilityVersion(lists.get(17).getStringCellValue());
                vulnerability.setVulnerabilityCopyright(lists.get(18).getStringCellValue());
                vulnerability.setVulnerabilityTag(lists.get(19).getStringCellValue());
                vulnerability.setVulnerabilityDependencies(lists.get(20).getStringCellValue());
                vulnerability.setVulnerabilityRequiredKeys(lists.get(21).getStringCellValue());
                vulnerability.setVulnerabilityMandatorgKeys(lists.get(22).getStringCellValue());
                vulnerability.setVulnerabilityExcluedKeys(lists.get(23).getStringCellValue());
                vulnerability.setVulnerabilitySignKeyIds(lists.get(24).getStringCellValue());
                vulnerability.setVulnerabilityAscFileName(lists.get(25).getStringCellValue());
                vulnerability.setVulnerabilityAsc(lists.get(26).getStringCellValue());
                vulnerability.setVulnerabilityCheckType(lists.get(27).getStringCellValue());
                vulnerability.setVulnerabilityCvssBase(lists.get(28).getStringCellValue());
                vulnerability.setVulnerabilityPrefs(lists.get(29).getStringCellValue());
                vulnerability.setVulnerabilityTimeout(lists.get(30).getStringCellValue());
                
                list.add(vulnerability);*/
                
                asset = new Asset();
                
                //资产id
                /*lists.get(0).setCellType(Cell.CELL_TYPE_NUMERIC);
                //System.out.println(lists.get(0).getStringCellValue());
                //System.out.println(lists.get(0).getStringCellValue().getClass().getName());
             
                asset.setAssetId(Long.valueOf(lists.get(0).getStringCellValue()));*/
                
            //    //System.out.println("导入资产的名称            "+lists.get(1).getStringCellValue());
                //资产名称
               
                if ( lists.get(0)!=null) {
					
               lists.get(0).setCellType(HSSFCell.CELL_TYPE_STRING);
              
               
               String assetName= (String)lists.get(0).getStringCellValue();
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
                	 if (ValidationData.regexString(assetName)) {
						
					
                	if(assetManager.queryByName(assetName.trim()).size()>0){
                    	throw new MyException("资产名已经被占用");
                    	
                    }else{
                    asset.setAssetName(assetName.trim());
                    }
                	 }else{
                		 throw new MyException("资产名不能包含特殊字符");
                	 }
                }else{
                	throw new MyException("资产名不能为空");
                }
                }else{
                	throw new MyException("资产名不能为空");
                }
                
                //System.out.println("导入资产的名称            "+asset.getAssetName());
                //资产ip
                if ( lists.get(1)!=null) {
                lists.get(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                String ip = lists.get(1).getStringCellValue();
               if (ValidationData.regexIP(ip)) {
            	   Map map = new HashMap();
            	   map.put("mac", ip);
            	  if(assetManager.checkMac(map).size()>0){
            		  throw new MyException("IP地址被占用");
            	  }else{ 
            	   asset.setAssetIp(StrIpTransitionLip(ip));
            	  }
			}else{
				throw new MyException("IP地址不合法");
			}
               
               
                //资产网关
               // String netWork = lists.get(3).getStringCellValue();
                asset.setAssetGateWay(StrIpTransitionLip("255.255.255.0"));
                
                //资产ip段
              //  String ips = lists.get(4).getStringCellValue();
                asset.setAssetSegMent(Long.parseLong(Long.toBinaryString(IpConvert
    					.iPTransition(ip)
    					& IpConvert.iPTransition("255.255.255.0")), 2));
                //资产mac地址
                asset.setAssetMac(ip);
                }else{
                	throw new MyException("IP地址不能为空！");
                }
                

                //资产重要性
                if ( lists.get(2)!=null) { 
                lists.get(2).setCellType(HSSFCell.CELL_TYPE_STRING);
                String important = lists.get(2).getStringCellValue();
                
                int flag = 0;
                if(important.equals("一般"))
                {
                    flag= -1;
                }
                if(important.equals("重要"))
                {
                    flag = 0;
                }
                if(important.equals("比较重要"))
                {
                    flag = 1;
                }
                if(important.equals("非常重要"))
                {
                    flag = 2;
                }
                asset.setAssetImportance(flag);
                }else{
                	throw new MyException("资产重要性不能为空！");
                }
                
                //资产主机名称
              //  asset.setAssetHostName(lists.get(6).getStringCellValue());
                
                if ( lists.get(3)!=null) { 
                //创建者登录名
                lists.get(3).setCellType(HSSFCell.CELL_TYPE_STRING);
                String loginName = lists.get(3).getStringCellValue();
                if(StringUtil.isNotEmpty(loginName)){
                	if(loginName.length()>50)
                		throw new MyException("创建者登录名长度不能大于50");
                	int loginname=0;
               	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
               	 try {
               		loginname=(int)Double.parseDouble(loginName.trim());
               		loginName=""+loginname+"";
					} catch (Exception e) {
						// TODO: handle exception
					}
               	 if (ValidationData.regexString(loginName)) {
                	if(uService.queryByUserLoginName(loginName.trim()).size()>0){
                	 asset.setAssetUserLoginName(loginName.trim());
                	}else{
                		throw new MyException("创建者登录不存在！");
                	}}else {
                		throw new MyException("创建者登录名不能包含特殊字符！");
					}
                }else{
                	throw new MyException("创建者登录名为空！");
                }
                }else{
                	throw new MyException("创建者不能为空！");
                }
               
                
                //资产节点id
               // asset.setAssetNodeId(Integer.parseInt(lists.get(8).getStringCellValue()));
                
                //资产节点别名
               // asset.setAssetAliasName(lists.get(9).getStringCellValue());
                
                //责任人
                if ( lists.get(4)!=null) { 
                lists.get(4).setCellType(HSSFCell.CELL_TYPE_STRING);
                String pUserName = lists.get(4).getStringCellValue();
                if (StringUtil.isNotEmpty(pUserName)) {
                	if(pUserName.length()>50)
                		throw new MyException("责任人名称长度不能大于50");
                	int pUsername=0;
                  	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                  	 try {
                  		pUsername=(int)Double.parseDouble(pUserName.trim());
                  		pUserName=""+pUsername+"";
   					} catch (Exception e) {
   						// TODO: handle exception
   					}
                  	 if (ValidationData.regexString(pUserName)) {
					if(uService.queryUserIdByName(pUserName.trim())>0){
						 asset.setAsset_pessonsibility_userName(pUserName.trim());
			                //责任人id
			                asset.setAsset_pessponsibility_user_id((int)uService.queryUserIdByName(pUserName.trim()));
					}else{
						throw new MyException("责任人不存在！");
					}}else {
						throw new MyException("责任人名称不能包含特殊字符！");
					}
				}else{
					throw new MyException("责任人为空！");
				}
                }else{
                	throw new MyException("责任人不能为空！");
                }
               
                
                //响应人
                if ( lists.get(5)!=null) { 
                lists.get(5).setCellType(HSSFCell.CELL_TYPE_STRING);
                String aUserName = lists.get(5).getStringCellValue();
                if(aUserName!=null&& aUserName!=""){
                	if(aUserName.length()>50)
                		throw new MyException("响应人名称长度不能大于50");
                	int pUsername=0;
                 	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                 	 try {
                 		pUsername=(int)Double.parseDouble(aUserName.trim());
                 		aUserName=""+pUsername+"";
  					} catch (Exception e) {
  						// TODO: handle exception
  					}
                 	 if (ValidationData.regexString(aUserName)) {
                	if (uService.queryUserIdByName(aUserName.trim())>0) {
                		 asset.setAsset_answer_userName(aUserName.trim());
                         //响应人id
                         asset.setAsset_answer_user_id((int)uService.queryUserIdByName(aUserName.trim()));
					}else{
						throw new MyException("响应人不存在！");
					}
                 	 }else {
                 		throw new MyException("响应人不能包含特殊字符！");
					}
                }else{
                	throw new MyException("响应人为空！");
                }
                }else{
                	throw new MyException("响应人不能为空");
                }
               
                
                
                //采集类型
                if ( lists.get(6)!=null) { 
                int flag2=1;
                lists.get(6).setCellType(HSSFCell.CELL_TYPE_STRING);
                String collectType = lists.get(6).getStringCellValue();
                if(collectType.equals("Syslog"))
                {
                    flag2 = -1;
                }
                if(collectType.equals("snmp"))
                {
                    flag2 = 0;
                }
                if(collectType.equals("代理"))
                {
                    flag2 = 1;
                }
                asset.setAssetUnName(flag2);
                }else{
                	throw new MyException("采集类型不能为空");
                }
                
                //团体名
                String organizationName="";
                if ( lists.get(7)!=null) { 
                lists.get(7).setCellType(HSSFCell.CELL_TYPE_STRING);
               organizationName=lists.get(7).getStringCellValue();
            		   
                if(organizationName!=null&& organizationName!=""){
                	int organization=0;
                 	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                 	 try {
                 		organization=(int)Double.parseDouble(organizationName.trim());
                 		organizationName=""+organization+"";
  					} catch (Exception e) {
  						// TODO: handle exception
  					}
                }
                }
                
                asset.setOrganizationName(organizationName);
                
                //版本号
                asset.setVersion("1");
                String dir ="";
                //监控目录
                if ( lists.get(8)!=null) { 
				
               lists.get(8).setCellType(HSSFCell.CELL_TYPE_STRING);
                 dir = lists.get(8).getStringCellValue();
                if(dir!=null&& dir!=""){
                	int dirNum=0;
                 	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                 	 try {
                 		dirNum=(int)Double.parseDouble(dir.trim());
                 		dir=""+dirNum+"";
  					} catch (Exception e) {
  						// TODO: handle exception
  					}
                }
                }
                asset.setDirectorise(dir);
                
                //监控文件
                String fileName="";
                if ( lists.get(9)!=null) { 
                lists.get(9).setCellType(HSSFCell.CELL_TYPE_STRING);
                fileName = lists.get(9).getStringCellValue();
                if(fileName!=null&& fileName!=""){
                	int fileNum=0;
                 	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                 	 try {
                 		fileNum=(int)Double.parseDouble(fileName.trim());
                 		fileName=""+fileNum+"";
  					} catch (Exception e) {
  						e.printStackTrace();
  					}
                }
                }
                
				
                asset.setFile(fileName);
                //资产标识
             //   asset.setAssetWorkIdent(lists.get(17).getStringCellValue());
                
                //资产描述
                String meno ="";
                if ( lists.get(10)!=null) { 
				 
               lists.get(10).setCellType(HSSFCell.CELL_TYPE_STRING);
                 meno = lists.get(10).getStringCellValue();
                if(meno!=null&& meno!=""){
                	int mNum=0;
                 	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                 	 try {
                 		mNum=(int)Double.parseDouble(meno.trim());
                 		meno=""+mNum+"";
  					} catch (Exception e1) {
  						e1.printStackTrace();
  					}
                }
                }
               
                asset.setAssetMemo(meno);
                
//                //所属资产组名称
             //  asset.setAssetGroupName(lists.get(19).getStringCellValue());
              //采集器MAC
                if ( lists.get(12)!=null) { 
                lists.get(12).setCellType(HSSFCell.CELL_TYPE_STRING);
                String MAC=lists.get(12).getStringCellValue();
                if(MAC!=null&&MAC!=""){
                	String regex="($)|(([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}$)";
                	 Pattern pattern = Pattern.compile(regex);
                     Matcher matcher = pattern.matcher(MAC);
                    if( matcher.matches()){
                	List<Collector> listCollector =settingLoggerManager.selectMac(MAC);
                	 if ( lists.get(11)!=null) { 
                		 lists.get(11).setCellType(HSSFCell.CELL_TYPE_STRING);
                		 String collectorName=lists.get(11).getStringCellValue();
                	if (listCollector.size()>0) {
                		 //采集器Mac
                        asset.setAssetCollectorMac(MAC);
                        Collector collector = listCollector.get(0);
                        //采集器id
                        asset.setAssetCollectorId(collector.getCollectorId());
                       if(collectorName!=null&& collectorName!=""){
                       	int mNum=0;
                        	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                        	 try {
                        		mNum=(int)Double.parseDouble(collectorName.trim());
                        		collectorName=""+mNum+"";
         					} catch (Exception e3) {
         						e3.printStackTrace();
         					}
                       }
                       
                       
                       if(collector.getCollectorBasic().equals(collectorName)){
                    	   asset.setAssetCollectorName(collectorName); 
                       }else{
                    	   throw new MyException("采集器名称不存在！");
                       }
                	}else{
                		throw new MyException("采集器MAC不存在！");
                	}
                        
					}else{
						throw new MyException("采集器名不能为空！");
					}
                    }else{
                    	throw new MyException("采集器MAC不正确！");
                    }
                }else{
                	throw new MyException("采集器MAC不能为空！");
                }
                }else{
                	throw new MyException("采集器MAC不能为空");
                }
                     
                //设备类型名称
                if ( lists.get(13)!=null) { 
                lists.get(13).setCellType(HSSFCell.CELL_TYPE_STRING);
                String categoryName = lists.get(13).getStringCellValue();
                if(StringUtil.isNotEmpty(categoryName)){
                	if(categoryName.length()>50)
                		throw new MyException("设备类型名称长度不能大于50");
                	int categoryname=0;
                	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
                	 try {
                		 categoryname=(int)Double.parseDouble(categoryName.trim());
                		 categoryName=""+categoryname+"";
 					} catch (Exception e4) {
 						// TODO: handle exception
 					}
                	 if (ValidationData.regexString(categoryName)) {
						
					
                	Category category=categoryService.queryCategoryByCategoryName(categoryName);
                	if(category!=null){
                		asset.setAssetDeviceType(categoryName);
                   	 //设备类型id
                       asset.setAssetDeviceTypeId(category.getDevice_category_id());
                       //支持设备类型名称
                       if ( lists.get(14)!=null) { 
                       lists.get(14).setCellType(HSSFCell.CELL_TYPE_STRING);
                       String assetSupportDevice = lists.get(14).getStringCellValue();
                       if(assetSupportDevice!=null&&assetSupportDevice!=""){
                    	   if(assetSupportDevice.length()>50)
                    		   throw new MyException("支持设备类型名称长度不能大于50");
                    	   List<Map> maps= assetManager.queryCategory(category.getDevice_category_id());
                    	   boolean falg=false;
                    	   for (Map map1 : maps) {
							if(map1.get("devicename").equals(assetSupportDevice)){
								asset.setAssetSupportDevice(assetSupportDevice);
								 //支持设备id
		                    	   asset.setAssetSupportDeviceId(Long.parseLong(map1.get("deviceid").toString()));
		                    	   asset.setAssetDeviceRules(map1.get("eventsdevname").toString());
		                    	   falg=true;
		                    	   break;
							}
						}
                    	   if(!falg){
                    		   throw new MyException("支持设备不存在");   
                    	   }
                    	
                       }else{
                    	   throw new MyException("支持设备名称为空"); 
                       }
                       }else{
                    	   throw new MyException("支持设备名不能为空");
                       }
                	}else{
                		throw new MyException("设备类型不存在！");
                	}
                	 }else {
                		 throw new MyException("设备类型名称包含特殊字符！");
					}
                	
                }else{
                	throw new MyException("设备类型名称为空！");
                }
                }else{
                	throw new MyException("设备类型不能为空");
                }
                
                
                //特权命令
                if ( lists.get(15)!=null) { 
                lists.get(15).setCellType(HSSFCell.CELL_TYPE_STRING);
                asset.setAssetPrivilegeCommand(lists.get(15).getStringCellValue());
                }
                
                //资产状态
                if ( lists.get(16)!=null) { 
                lists.get(16).setCellType(HSSFCell.CELL_TYPE_STRING);
                String assetStatus = lists.get(16).getStringCellValue();
                
                int flag3 =1;
                if(assetStatus.equals("启用"))
                {
                    flag3 = 1;
                }
                else
                {
                    flag3 = 0;
                }
                asset.setAssetStatus(flag3);
                }else{
                	throw new MyException("资产状态不能为空");
                }
                
                //设置为资产未删除
                asset.setAssetIsDelete(0);
                //资产关联的规则
               // asset.setAssetDeviceRules(lists.get(29).getStringCellValue());
                //保密性价值
                asset.setSecretValue(0);
                //完整性价值 
                asset.setIntegrityValue(0);
                //可用性价值
                asset.setUsabilityValue(0);
                //资产风险值
                asset.setAssetValue(0);
                
                
                
//                String sName = lists.get(34).getStringCellValue();
//                //资源系统
//                Map<String, Object> SystemMap = new HashMap<String, Object>();
//                asset.setAsset_system_name(sName);
//                //资源系统id
//                SystemMap.put("name", sName.trim());
//                SystemMap.put("level", 0);
//                int systemId = (int)asService.querySystemIdByName(SystemMap);
//                asset.setAsset_system_id(systemId);
//                
//                //系统版本
//                Map<String, Object> verSiconMap = new HashMap<String, Object>();
//                String verSion = lists.get(35).getStringCellValue();
//                asset.setAsset_system_version_name(verSion);
//                verSiconMap.put("name", verSion);
//                verSiconMap.put("level", systemId);
//                long b = asService.querySystemIdByName(verSiconMap);;
//                asset.setAsset_system_version((int)b);
//                
//                //系统版本号
//                Map<String, Object> versionNameMap = new HashMap<String, Object>();
//                String versionName = lists.get(36).getStringCellValue();
//                asset.setAsset_system_versionno_name(versionName);
//                versionNameMap.put("name", versionName);
//                versionNameMap.put("level", systemId);
//                asset.setAsset_system_versionno((int)asService.querySystemIdByName(versionNameMap));
//                
//                //系统品牌
//                Map<String, Object> brandMap = new HashMap<String, Object>();
//                String brand = lists.get(37).getStringCellValue();
//                asset.setAsset_system_brand_name(brand);
//                brandMap.put("name", brand);
//                brandMap.put("level", systemId);
//                asset.setAsset_system_brand((int)asService.querySystemIdByName(versionNameMap));
                
                
                list.add(asset);
      //         //System.out.println(list);
            }
            
           
           
        return list;
                
    }
    
    public List<Asset> readRead1(File path)
    	    throws MyException, FileNotFoundException, IOException
    	  {
    	    String sheetName = null;
    	    int sheetCount = -1;
    	    int rowCount = -1;
    	    int cellCount = -1;

    	    List list1 = new ArrayList();

    	    Asset asset = null;

    	    List<Asset> list = new ArrayList<Asset>();

    	    HSSFWorkbook wk = new HSSFWorkbook(new FileInputStream(path));

    	    sheetCount = wk.getNumberOfSheets();
    	    HSSFSheet sheet = wk.getSheetAt(0);

    	    rowCount = sheet.getLastRowNum();
    	    for (int i = 2; i <= rowCount; ++i)
    	    {
    	      HSSFRow row = sheet.getRow(i);

    	      cellCount = row.getLastCellNum();
    	      List<HSSFCell> lists = new ArrayList<HSSFCell>();
    	      for (int k = 0; k <= cellCount; ++k)
    	      {
    	        HSSFCell cell = row.getCell(k);

    	        lists.add(cell);
    	      }

    	      asset = new Asset();

    	      String assetName= (String)lists.get(0).getStringCellValue();
              
              if(StringUtil.isNotEmpty(assetName)){
              	 int assetname=0;
              	 //由于从Excel导入整数会自动转换成Double类型。此处做一次类型转换，保证数据的正确性。
              	 try {
              		 assetname=(int)Double.parseDouble(assetName.trim());
              		 assetName=""+assetname+"";
					} catch (Exception e) {
						// TODO: handle exception
					}
              	if(assetManager.queryByName(assetName.trim()).size()>0){
                  	throw new MyException("资产名已经被占用");
                  	
                  }else{
                  asset.setAssetName(assetName.trim());
                  }
              }else{
              	throw new MyException("资产名不能为空");
              }
    	      String ip = ((HSSFCell)lists.get(1)).getStringCellValue();
    	      if (ValidationData.regexIP(ip)) {
    	        Map map = new HashMap();
    	        map.put("mac", ip);
    	        if (this.assetManager.checkMac(map).size() > 0) {
    	          throw new MyException("IP地址被占用");
    	        }
    	        asset.setAssetIp(StrIpTransitionLip(ip).longValue());
    	      }
    	      else {
    	        throw new MyException("IP地址不合法");
    	      }

    	      asset.setAssetGateWay(StrIpTransitionLip("255.255.255.0").longValue());

    	      asset.setAssetSegMent(Long.parseLong(Long.toBinaryString(
    	        IpConvert.iPTransition(ip) & 
    	        IpConvert.iPTransition("255.255.255.0")), 2));

    	      String important = ((HSSFCell)lists.get(2)).getStringCellValue();

    	      int flag = 0;
    	      if (important.equals("不重要"))
    	      {
    	        flag = -1;
    	      }
    	      if (important.equals("一般"))
    	      {
    	        flag = -1;
    	      }
    	      if (important.equals("重要"))
    	      {
    	        flag = 0;
    	      }
    	      if (important.equals("比较重要"))
    	      {
    	        flag = 1;
    	      }
    	      if (important.equals("非常重要"))
    	      {
    	        flag = 2;
    	      }

    	      asset.setAssetImportance(flag);

    	      asset.setAssetMac(ip);

    	      asset.setAssetUserLoginName("admin");

    	      asset.setAsset_pessonsibility_userName("admin");

    	      asset.setAsset_answer_userName("admin");

    	      int flag2 = 1;

    	      asset.setAssetUnName(flag2);

    	      asset.setAssetCollectType("代理");

    	      asset.setOrganizationName("public");

    	      asset.setAssetPort("");
    	      asset.setAssetLog("");
    	      asset.setAssetAPM("");
    	      asset.setAssetAPMMonitor("");

    	      asset.setVersion("1");

    	      asset.setDirectorise("");

    	      asset.setFile("");

    	      asset.setAssetMemo(((HSSFCell)lists.get(7)).getStringCellValue());

    	      Collector tempCollector = this.settingLoggerManager.queryById(1L);
    	      if (tempCollector != null)
    	      {
    	        asset.setAssetCollectorMac(tempCollector.getCollectorMac());

    	        asset.setAssetCollectorId(1L);

    	        asset.setAssetCollectorName(tempCollector.getCollectorBasic());
    	      }
    	      else
    	      {
    	        throw new MyException("采集器不存在！");
    	      }

    	      String categroyName = "";
    	      if (((HSSFCell)lists.get(9)).getStringCellValue() == null)
    	      {
    	        categroyName = "";
    	      }
    	      else
    	      {
    	        categroyName = ((HSSFCell)lists.get(9)).getStringCellValue().toLowerCase();
    	      }

    	      asset.setAssetDeviceType("Server服务器");

    	      asset.setAssetDeviceTypeId(39L);

    	      if (categroyName.contains("windows"))
    	      {
    	        asset.setAssetSupportDeviceId(41L);
    	        asset.setAssetDeviceRules("Server_Windows");
    	        asset.setAssetSupportDevice("Windows");
    	      }
    	      else
    	      {
    	        asset.setAssetSupportDeviceId(40L);
    	        asset.setAssetDeviceRules("Server_Linux_agent-Server_Linux_log");
    	        asset.setAssetSupportDevice("Linux");
    	      }

    	      asset.setAssetPrivilegeCommand("");

    	      int flag3 = 1;

    	      asset.setAssetStatus(flag3);

    	      asset.setAssetIsDelete(0);

    	      asset.setSecretValue(0);

    	      asset.setIntegrityValue(0);

    	      asset.setUsabilityValue(0);

    	      asset.setAssetValue(0.0D);

    	      list.add(asset);
    	    }

    	    return list;
    	  }

    	 

    
    /**
     * 字符串IP转换为Long型IP
     * @param strIP
     * @return
     */
    public static Long StrIpTransitionLip(String strIP)
    {
        String[] strIps = strIP.split("\\.");
        long lIp0 = Long.parseLong(strIps[0]);
        long lIp1 = Long.parseLong(strIps[1]);
        long lIp2 = Long.parseLong(strIps[2]);
        long lIp3 = Long.parseLong(strIps[3]);
        long lip = (lIp0 << 24) + (lIp1 << 16) + (lIp2 << 8) + lIp3;
        return lip;
    }
   
    
	public UserService getuService() {
		return uService;
	}

	public void setuService(UserService uService) {
		this.uService = uService;
	}

	public AssetSystemService getAsService() {
		return asService;
	}

	public void setAsService(AssetSystemService asService) {
		this.asService = asService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public SettingCollectorService getSettingLoggerManager() {
		return settingLoggerManager;
	}

	public void setSettingLoggerManager(SettingCollectorService settingLoggerManager) {
		this.settingLoggerManager = settingLoggerManager;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	
    
}
