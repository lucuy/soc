package com.soc.webapp.action.systemsetting.rules;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.soc.model.systemsetting.rules.AssociationRules;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.rules.AssociationRulesService;
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
public class AssociationRulesAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private AssociationRulesService associationManager;
    
    private List<AssociationRules> associationList;
    
    private String filterId;
    
    private int associationStatus;
    
    private Integer filterType;
    
    private String filterName;
    
    // 模糊查询关键字
    private String keyword;
    
    //内部审计
    private AuditService auditManager;
    
    //请求的action字符串
    private String actionStr ="AssociationRules.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ; 
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String AssociationRules(){

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
        if (StringUtil.isNotBlank(filterName))
        {
             map.put("filterName", filterName);
        }
        if (filterType != null)
        {
             map.put("filterType", filterType);
        }
        //根据条件查询相应审计数据
        sr = associationManager.queryPage(map, page);
        if (sr != null)
        {
            associationList = (List<AssociationRules>)sr.getList();
            request.setAttribute("associationList", associationList);
            request.setAttribute("Page", sr.getPage());
        }
        else
        {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        return SUCCESS;
    }
    
    public String updateAssociationRules(){
        
        List<String> fieldList = new ArrayList<String>();
        
        if (StringUtil.isNotBlank(filterId))
        {
            if (filterId.indexOf(",") > 0)
            {
                String[] checked = filterId.split(",");
                
                for (String checkid : checked)
                {   
                    AssociationRules assocationObject = associationManager.queryFilterById(checkid);
                    fieldList.add(assocationObject.getFilterName()+"("+assocationObject.getFilterName()+")");
                    
                    
                    associationManager.updateAssociationStatus(checkid, associationStatus);
                }
                
            }
            
            else
            {
                AssociationRules assocationObject = associationManager.queryFilterById(filterId);
                fieldList.add(assocationObject.getFilterName()+"("+assocationObject.getFilterName()+")");
                
                associationManager.updateAssociationStatus(filterId, associationStatus);
            }
            
            //审计日志
            auditManager.insertByUpdateOperator(((User) this.getSession()
                .getAttribute("SOC_LOGON_USER")).getUserId(), "关联规则", super
                .getRequest().getRemoteAddr(), fieldList);
            
            //syslog
         /*   String logString = "";
            
            logString =
                "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                    + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :更改关联规则状态";
            
            logManager.writeSystemAuditLog(logString);*/
            logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改关联规则状态");
        }
        return SUCCESS;
        
    }
    
    public AssociationRulesService getAssociationManager()
    {
        return associationManager;
    }

    public void setAssociationManager(AssociationRulesService associationManager)
    {
        this.associationManager = associationManager;
    }

    public List<AssociationRules> getAssociationList()
    {
        return associationList;
    }

    public void setAssociationList(List<AssociationRules> associationList)
    {
        this.associationList = associationList;
    }

    public String getFilterId()
    {
        return filterId;
    }

    public void setFilterId(String filterId)
    {
        this.filterId = filterId;
    }

    public int getAssociationStatus()
    {
        return associationStatus;
    }

    public void setAssociationStatus(int associationStatus)
    {
        this.associationStatus = associationStatus;
    }

    public Integer getFilterType()
    {
        return filterType;
    }

    public void setFilterType(Integer filterType)
    {
        this.filterType = filterType;
    }

    public String getFilterName()
    {
        return filterName;
    }

    public void setFilterName(String filterName)
    {
        this.filterName = filterName;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
	
	
	
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
	        
	    	if(field != "" && field != null){
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
	    		
	    		sr = associationManager.sort(map, page);
	    		if (sr != null)
	            {
	    			associationList = sr.getList();
	    			request.setAttribute("associationList", associationList);
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