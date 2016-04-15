package com.soc.webapp.action.asset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetRiskValue;
import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.asset.export.ExcelRisk;
import com.soc.service.asset.risk.AssetRiskService;
import com.soc.service.asset.risk.ImportRisk;
import com.soc.service.audit.AuditService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FileUtil;
import com.util.MyException;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AssetRisk extends BaseAction
{
    private File upTar; //上传文件
    
    private String upTarFileName; //文件名称
    
    private String ids;
    
    private AssetRiskService assetRiskManager;
    
    private List<Map<String, Object>> riskValueList;
    private List<Asset> assetList;
    private AssetService assetManager;
    
    private String keyword;
    
    private String riskName;
    
    private String riskResult;             //威胁值结果
    
    private String riskAssetId;
    
    private long id;
    
    private AssetRiskValue riskValue;
    //请求的action字符串
    private String actionStr ="queryRisk.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ; 
    
  //返回的结果字符串
  	private String info ; 
  	
  	// 审计业务管理类
 	private AuditService auditManager;
 	
 	private int errorMessageflg;//是否有错误信息 因为诸多原因 当errorMessageflg是1的时候errorMessage赋值 在list界面显示
	private String errorMessage;//多人操作时 第一个人到资产赋值界面保存之前 如果第二个人已经删除资产 第一个人保存后报错 这里加一个界面提示
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryRisk()
    {
    	HttpServletRequest request = super.getRequest();
        Page page = null;
        SearchResult sr = null;
        // 处理数据分页的起始条数
        String startIndex = request.getParameter("startIndex");
        if (StringUtil.isNotBlank(startIndex))
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
        }
        else
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
        }
        
        // 接收查询条件，并存储到map中
        Map<String, Object> map = new HashMap<String, Object>();
        long groupId=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		
		 map.put("assetGroupId", groupId);	
		
        assetList = assetManager.query(map);
        if (request.getParameter("keyword") != null)
        {
            try
            {
                
                keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
                
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            if(StringUtil.isNotEmpty(keyword)){
            	map.put("keyword", keyword);
            }
            
        }
        if(StringUtil.isNotEmpty(riskAssetId)){
        	map.put("riskAssetId", Integer.parseInt(riskAssetId));
        }
        if (StringUtil.isNotBlank(riskName))
        {
             map.put("riskName", riskName.trim());
        }
        if (StringUtil.isNotBlank(riskResult))
        {
             map.put("riskResult", Long.valueOf(riskResult.trim()));
        }
        if (StringUtil.isNotBlank(riskAssetId)) {
			map.put("riskAssetId", Integer.parseInt(riskAssetId));
		}
        map.put("assetGroupId", groupId);	
        //根据条件查询相应审计数据
        sr = assetRiskManager.queryPage(map, page);
        if (sr != null)
        {
        	riskValueList = (List<Map<String, Object>>)sr.getList();
            request.setAttribute("associationList", riskValueList);
            request.setAttribute("Page", sr.getPage());
        }
        else
        {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
       errorMessageflg=errorMessageflg;
        return SUCCESS;
    
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String importRisk()
    {
    	try{
        if (upTar != null && upTarFileName != null)
        {
            String path = ServletActionContext.getServletContext().getRealPath("/riskfile");
            
            File importExcel = new File(new File(path), upTarFileName);
            
            try
            {
                FileUtil.copyFile(upTar, importExcel);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            List<AssetRiskValue> riskValueList = new ArrayList<AssetRiskValue>();
            
            
                riskValueList = ImportRisk.readRead(upTar,assetManager);
                //李长红优化--20140505
                for (AssetRiskValue riskValue1:riskValueList)
                {
//                    List<AssetRiskValue> list1 = null;
                	//Asset asset= assetRiskManager.RiskQueryAssetByName(riskValueList.get(i).getRiskAssetName());
//                    
                	//if(asset!=null&&riskValueList.get(i).getRiskName()!=null){
                		/*AssetRiskValue riskValue1= new AssetRiskValue();
//                		riskValue1.setRiskAssetId(asset.getAssetId());
//                		riskValue1.setRiskName(riskValueList.get(i).getRiskName());
                		int riskPossibilityResult=riskValueList.get(i).getRiskPossibility();
                		int riskInfluenceResult = riskValueList.get(i).getRiskInfluence();
                		int result = 0;
                		riskValue1.setRiskPossibility(riskPossibilityResult);
                		riskValue1.setRiskInfluence(riskInfluenceResult);
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
                		
                		riskValue1.setRiskResult(result);
                		*/
                		assetRiskManager.insertRiskValue(riskValue1);
                		List<String> fieldList = new ArrayList<String>();
             			fieldList.add(riskValue1.getRiskAssetName() + "(" + riskValue1.getRiskAssetName()
             					+ ")");
             			auditManager.insertByUpdateOperator(((User) this.getSession()
             					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产风险", super
             					.getRequest().getRemoteAddr(), fieldList);
                		
                	//}
                	
                	
                	
//                    if (list1 == null || list1.isEmpty())
//                    {
                        //插入资产
                       
//                    }
                }

            }

                }catch(MyException e){
                	e.printStackTrace(); 
                	info = e.getMessage() ; 
                } catch (FileNotFoundException e) {
                	e.printStackTrace(); 
                	info = "alert" ;
				} catch (IOException e) {
					e.printStackTrace(); 
                	info = "alert" ;
				}
                

            
        return SUCCESS;
    }
    
    public String deleteRisk(){
        if (StringUtil.isNotBlank(String.valueOf(ids)))
        {
            // 针对多个规则操作
            if (String.valueOf(ids).indexOf(",") > 0)
            {
                
                String[] checked = String.valueOf(ids).split(",");
                
                // 循环遍历需要执行更新状态的规则ID并执行更新状态操作
                for (String checkid : checked)
                {
                	AssetRiskValue assetriskvalue=
                			assetRiskManager.queryAssetRiskValue(Integer.parseInt(checkid));
                    if(assetriskvalue!=null){
                    List<String> fieldList = new ArrayList<String>();
         			fieldList.add(assetriskvalue.getRiskAssetName() + "(" + 
                    assetriskvalue.getRiskAssetName() + ")");
         			auditManager.insertByDeleteOperator(((User) this.getSession()
         					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产风险", super
         					.getRequest().getRemoteAddr(), fieldList);
         			assetRiskManager.deleteRisk(Integer.parseInt(checkid));
                    }else{
                    	return"errorMessage";
                    }
                    
                }
            }else{
            	AssetRiskValue assetriskvalue=
            			assetRiskManager.queryAssetRiskValue(Integer.parseInt(ids));
            	 if(assetriskvalue!=null){
                assetRiskManager.deleteRisk(Integer.parseInt(ids));
                List<String> fieldList = new ArrayList<String>();
     			fieldList.add(assetriskvalue.getRiskAssetName() + "(" + 
                assetriskvalue.getRiskAssetName() + ")");
     			auditManager.insertByDeleteOperator(((User) this.getSession()
     					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产风险", super
     					.getRequest().getRemoteAddr(), fieldList);
            	 }else{
                 	return"errorMessage";
                 }
            }
        }
        return SUCCESS;
    }
    
    public String sort(){
    	LOG.info("[AssetRiskAction] enter method sort() ...");
        HttpServletRequest request = super.getRequest();
        
        Page page = null;
        SearchResult sr = null;
        
        HttpSession session = this.getSession() ; 
        int changeNum=0;  
        changeNum = session.getAttribute("CHANGENUM")==null ? 1:(Integer)session.getAttribute("CHANGENUM");
        
     // 处理数据分页的起始条数
        String startIndex = request.getParameter("startIndex");
        
        if (StringUtil.isNotBlank(startIndex))
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
        }
        else
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
        }
        
        if(field != ""){
            Map<String,String> map=new HashMap<String, String>();
            int num = changeNum%2;
            
            if(num==0){
                map.put("sortType", "DESC");
            }else{
                map.put("sortType", "ASC") ; 
            }
            if(sortType != null){
                map.put("sortType", sortType);
            }
            map.put("field", field);
            
            actionStr = "field="+field+"&sortType="+map.get("sortType");
            
            sr = assetRiskManager.sort(map, page);
            if (sr != null)
            {
            	riskValueList = sr.getList();
                request.setAttribute("riskValueList", riskValueList);
                //request.setAttribute("auditList", vulList);
                request.setAttribute("Page", sr.getPage());
            }
            else
            {
                request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
            }
        }
        changeNum++ ; 
        session.setAttribute("CHANGENUM", changeNum);
        return SUCCESS ; 
    }
    
    public String queryRiskValueId(){
    	riskValue = assetRiskManager.queryAssetRiskValue(id);
    	if(riskValue==null&&id!=0){
    			return"errorMessage";
    		
    	}
    	Map map = new HashMap();
    	 long groupId=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
         
    	 map.put("assetGroupId", groupId);
    	assetList =assetManager.query(map);
    	return SUCCESS;
    }
    
    
    public String updateRiskValue(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	Asset asset = assetManager.searchById(riskValue.getRiskAssetId());
    	if(asset!=null){
    	if(riskValue.getRiskId()==0){
    		assetRiskManager.insertRiskValue(riskValue);
    	}else{
		map.put("riskPossibility", riskValue.getRiskPossibility());
		map.put("riskInfluence", riskValue.getRiskInfluence());
		map.put("riskResult", riskValue.getRiskResult());
		map.put("riskAssetId", riskValue.getRiskAssetId());
		map.put("riskId", riskValue.getRiskId());
		map.put("riskName", riskValue.getRiskName());
    	assetRiskManager.updateAssetRiskValue(map);
    	List<String> fieldList = new ArrayList<String>();
			fieldList.add(riskValue.getRiskAssetName() + "(" + 
        riskValue.getRiskAssetName() + ")");
			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "资产风险", super
					.getRequest().getRemoteAddr(), fieldList);
    	}
    	return SUCCESS;
    	}else{
    		this.errorMessageflg=1;
    		return"errorMessage";
    	}
    	
    }
    
    /**
     * 把威胁信息导出Excel格式
     */
    public void  exportExcel(){
    	
    	LOG.info("[AssetRisk] enter method exportExcel() ...");
        
        HttpServletResponse response = super.getResponse();
        ExcelRisk excelsys = new ExcelRisk();
        Map<String, Object> limit = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(ids))
        {
            limit.put("ids", ids);
            limit.put("assetGroupId", 1);
        }else{
        	 long groupId=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
             
         	limit.put("assetGroupId", groupId);
        }
        if (StringUtil.isNotBlank(keyword))
        {
            try
            {
                keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            limit.put("keyword", keyword);
        }
       
        
            if (StringUtil.isNotBlank(riskName))
            {
            	try
                {
            		riskName = java.net.URLDecoder.decode(riskName, "UTF-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
               
            	limit.put("riskName", riskName.trim());
            }
            if (StringUtil.isNotBlank(riskResult))
            {
            	limit.put("riskResult", Long.valueOf(riskResult.trim()));
            }
            if (StringUtil.isNotBlank(riskAssetId)) {
            	limit.put("riskAssetId", Integer.parseInt(riskAssetId));
    		}
        
        excelsys.export(assetRiskManager.ReportExcel(limit), "威胁信息Excel报表");
        try
        {
            // 中文文件名需要编码
            String fileName = "AssetRisk_" + DateUtil.curDateStr8();
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment;Filename=" + fileName + ".xls");
            OutputStream os = response.getOutputStream();
            excelsys.getGwb().write(os);
            os.flush();
            os.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    	
    }
    /**
     * 威胁信息模板
     */
    public void exportMould(){
    	//李长红修改--20140505
    	HttpServletResponse response = super.getResponse();
		String path = super.getSession().getServletContext().getRealPath("/riskfile");
		String fileName ="AssetRisk_Mould.xls";
		File file= new File(path,"/"+fileName);
		try {
			InputStream inputStream = new FileInputStream(file);
			// 中文文件名需要编码
			response.setContentType("application/ms-excel");
			response.setHeader("Content-Disposition", "attachment;Filename="
					+ fileName );
			OutputStream os = response.getOutputStream();
			byte [] b = new byte[1024];
			int len;
			while ((len = inputStream.read(b))>0) {
				os.write(b, 0, len);
			}
			inputStream.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    
    
    public File getUpTar()
    {
        return upTar;
    }
    
    public void setUpTar(File upTar)
    {
        this.upTar = upTar;
    }
    
    public String getUpTarFileName()
    {
        return upTarFileName;
    }
    
    public void setUpTarFileName(String upTarFileName)
    {
        this.upTarFileName = upTarFileName;
    }

    public AssetRiskService getAssetRiskManager()
    {
        return assetRiskManager;
    }

    public void setAssetRiskManager(AssetRiskService assetRiskManager)
    {
        this.assetRiskManager = assetRiskManager;
    }

  
    public List<Map<String, Object>> getRiskValueList() {
		return riskValueList;
	}
	public void setRiskValueList(List<Map<String, Object>> riskValueList) {
		this.riskValueList = riskValueList;
	}
	public String getIds()
    {
        return ids;
    }
    public void setIds(String ids)
    {
        this.ids = ids;
    }
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public String getRiskResult() {
		return riskResult;
	}
	public void setRiskResult(String riskResult) {
		this.riskResult = riskResult;
	}
	public String getActionStr() {
		return actionStr;
	}
	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public String getRiskAssetId() {
		return riskAssetId;
	}
	public void setRiskAssetId(String riskAssetId) {
		this.riskAssetId = riskAssetId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public AssetRiskValue getRiskValue() {
		return riskValue;
	}
	public void setRiskValue(AssetRiskValue riskValue) {
		this.riskValue = riskValue;
	}
	public List<Asset> getAssetList() {
		return assetList;
	}
	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}
	public AssetService getAssetManager() {
		return assetManager;
	}
	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public AuditService getAuditManager() {
		return auditManager;
	}
	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	public int getErrorMessageflg() {
		return errorMessageflg;
	}
	public void setErrorMessageflg(int errorMessageflg) {
		this.errorMessageflg = errorMessageflg;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
    
}