package com.soc.webapp.action.systemsetting.rules;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SystemCenterIpService;
import com.soc.service.systemsetting.rules.AnalysisRulesService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AnalysisRulesAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private AnalysisRulesService analysisManager;
    
    private List<AnalysisRules> analysisList;
    

    private String analysisId;
    
    // 过滤规则名称
    private String analysisName;
    
    //类型
    private Integer analysisType;
    
    private int analysisStatus;
    
    private String keyword;
    
    //内部审计
    private AuditService auditManager;
    
    //从新写入规则配置文件
    private SystemCenterIpService systemCenterIpManager;
    
    // 请求的action字符串
 	private String actionStr = "AnalysisRules.action";

 	// 排序Type
 	private String sortType;

 	// 要查询的字段
 	private String field;
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String AnalysisRules(){

        HttpServletRequest request = super.getRequest();
        
        Page page = null;
        SearchResult sr = null;
        
        // 处理数据分页的起始条数
        String startIndex = request.getParameter("startIndex");
    	try {
			if (StringUtil.isNotBlank(startIndex)) {
				if (keyword==null || keyword.equals("")) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				}else {
					if (Page.getKeyword().equals(keyword)) {
						page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
						Page.setKeyword(keyword);
					}else{
						page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
						Page.setKeyword(keyword);
					}
				}
			} else {
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				Page.setKeyword(keyword);
			}			
		} catch (Exception e) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			Page.setKeyword(keyword);
		}
        
        // 接收查询条件，并存储到map中
        Map<String, Object> map = new HashMap<String, Object>();
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
            
            map.put("keyword", keyword);
        }
        if (StringUtil.isNotBlank(analysisName))
        {
             map.put("analysisName", analysisName);
        }
        if (analysisType != null)
        {
             map.put("analysisType", analysisType);
        }
        
        //根据条件查询相应审计数据
        sr = analysisManager.queryPage(map, page);
        
        if (sr != null)
        {
            analysisList = (List<AnalysisRules>)sr.getList();
            request.setAttribute("associationList", analysisList);
            request.setAttribute("Page", sr.getPage());
        }
        else
        {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        return SUCCESS;
    
    }
    
    /**
     * <更改规则的状态>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateAnalysisRules(){
        
        List<String> fieldList = new ArrayList<String>();
        
        if (StringUtil.isNotBlank(analysisId))
        {
            if (analysisId.indexOf(",") > 0)
            {
                String[] checked = analysisId.split(",");
                
                for (String checkid : checked)
                {
                    AnalysisRules analysisObject =analysisManager.queryAnalysisById(checkid);
                    fieldList.add(analysisObject.getAnalysisName()+"("+analysisObject.getAnalysisName()+")");
                    
                    analysisManager.updateAnalysisStatus(checkid, analysisStatus);
                }
                
            }
            
            else
            {
                AnalysisRules analysisObject =analysisManager.queryAnalysisById(analysisId);
                fieldList.add(analysisObject.getAnalysisName()+"("+analysisObject.getAnalysisName()+")");
                analysisManager.updateAnalysisStatus(analysisId, analysisStatus);
            }
            
            
            //从新写入规则文件
            systemCenterIpManager.updateConf();
            
            //写入审计日志
            auditManager.insertByUpdateOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "解析规则", super
                .getRequest().getRemoteAddr(), fieldList);
            
            //syslog
          /*  String logString = "";
            
            logString =
                "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                    + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :更改解析规则状态";
            
            logManager.writeSystemAuditLog(logString);*/
            logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改解析规则");
        }
        
        
        return SUCCESS;
        
    }
    
    /**
	 * <排序>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String sort(){
		LOG.info("[VulnerabilityAction] enter method sort() ...");
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
            
            sr = analysisManager.sort(map, page);
            if (sr != null)
            {
            	analysisList = sr.getList();
                request.setAttribute("associationList", analysisList);
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

    public AnalysisRulesService getAnalysisManager()
    {
        return analysisManager;
    }

    public void setAnalysisManager(AnalysisRulesService analysisManager)
    {
        this.analysisManager = analysisManager;
    }

    public List<AnalysisRules> getAnalysisList()
    {
        return analysisList;
    }

    public void setAnalysisList(List<AnalysisRules> analysisList)
    {
        this.analysisList = analysisList;
    }

    public String getAnalysisId()
    {
        return analysisId;
    }

    public void setAnalysisId(String analysisId)
    {
        this.analysisId = analysisId;
    }

    public int getAnalysisStatus()
    {
        return analysisStatus;
    }

    public void setAnalysisStatus(int analysisStatus)
    {
        this.analysisStatus = analysisStatus;
    }

    public String getAnalysisName()
    {
        return analysisName;
    }

    public void setAnalysisName(String analysisName)
    {
        this.analysisName = analysisName;
    }


    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }



    public SystemCenterIpService getSystemCenterIpManager()
    {
        return systemCenterIpManager;
    }

    public void setSystemCenterIpManager(SystemCenterIpService systemCenterIpManager)
    {
        this.systemCenterIpManager = systemCenterIpManager;
    }
    public Integer getAnalysisType()
    {
        return analysisType;
    }

    public void setAnalysisType(Integer analysisType)
    {
        this.analysisType = analysisType;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
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
	
	
}