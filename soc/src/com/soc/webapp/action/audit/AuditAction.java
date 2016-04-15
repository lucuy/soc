package com.soc.webapp.action.audit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.soc.model.audit.Audit;

import com.soc.service.audit.AuditService;
import com.soc.service.audit.export.ExcelAudit;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <对审计的相关操作：查询审计，添加审计>
 * <功能详细描述>
 * @author  shenhaiguang
 * @version  [版本号, 2012-8-13]
 * @see  [相关类/方法]
 * @since  [审计管理模块]
 */
public class AuditAction extends BaseAction
{
    
    private static final long serialVersionUID = 1L;
    
    // 用户服务管理类
    private AuditService auditManager;
    
    // 模糊查询关键字
    private String keyword;
    
    // 操作者
    private String auditUserLoginName;
    
    // 操作内容
    private String auditOperationProcedure;
    
    // 操作时间
    private String auditOperationTime;
    
    // 操作IP
    private String auditOperationIp;
    
    // 操作的具体内容
    private String auditInformation;
    
    // 编号
    private String ids;
    
    //请求的action字符串
    private String actionStr ="query.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ; 
    
    /**
     * <查询审计>
     * <根据接收到的搜索条件完成系统审计检索>
     * @return String
     * @see [Page,StringUtil]
     */
    public String query()
    {
        LOG.info("[AuditAction] enter method query() ...");
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
        if (StringUtil.isNotBlank(auditUserLoginName))
        {
             map.put("auditUserLoginName", auditUserLoginName);
        }
        if (StringUtil.isNotBlank(auditOperationProcedure))
        {
            map.put("auditOperationProcedure", auditOperationProcedure);
        }
        if (StringUtil.isNotBlank(auditOperationTime))
        {
            map.put("auditOperationTime", auditOperationTime);
        }
        if (StringUtil.isNotBlank(auditOperationIp))
        {
             map.put("auditOperationIp", auditOperationIp);
        }
        if (StringUtil.isNotBlank(auditInformation))
        {
            map.put("auditInformation", auditInformation);
        }
        //根据条件查询相应审计数据
        sr = auditManager.query(map, page);
        if (sr != null)
        {
            List<Audit> auditList = (List<Audit>)sr.getList();
            request.setAttribute("auditList", auditList);
            request.setAttribute("Page", sr.getPage());
        }
        else
        {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        
        return SUCCESS;
    }
    
    /**
     * <导出excel表>
     * <把系统审计信息导出excel>
     * @see [ExcelAudit,StringUtil]
     */
    public void export()
    {
        LOG.info("[AuditAction] enter method export() ...");
        
        HttpServletResponse response = super.getResponse();
        ExcelAudit excelsys = new ExcelAudit();
        Map<String, String> limit = new HashMap<String, String>();
        if (StringUtil.isNotBlank(ids))
        {
            limit.put("ids", ids);
        }else{
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
        if (StringUtil.isNotBlank(auditUserLoginName))
        {
        	 try
             {
        		 auditUserLoginName = java.net.URLDecoder.decode(auditUserLoginName, "UTF-8");
             }
             catch (UnsupportedEncodingException e)
             {
                 e.printStackTrace();
             }
        	limit.put("auditUserLoginName", auditUserLoginName);
        }
        if (StringUtil.isNotBlank(auditOperationProcedure))
        {
        	 try
             {
        		 auditOperationProcedure = java.net.URLDecoder.decode(auditOperationProcedure, "UTF-8");
             }
             catch (UnsupportedEncodingException e)
             {
                 e.printStackTrace();
             }
        	limit.put("auditOperationProcedure", auditOperationProcedure);
        }
        
        if (StringUtil.isNotBlank(auditOperationIp))
        {
        	 try
             {
        		 auditOperationIp = java.net.URLDecoder.decode(auditOperationIp, "UTF-8");
             }
             catch (UnsupportedEncodingException e)
             {
                 e.printStackTrace();
             }
        	limit.put("auditOperationIp", auditOperationIp);
        }
        if (StringUtil.isNotBlank(auditInformation))
        {
        	 try
             {
        		 auditInformation = java.net.URLDecoder.decode(auditInformation, "UTF-8");
             }
             catch (UnsupportedEncodingException e)
             {
                 e.printStackTrace();
             }
        	limit.put("auditInformation", auditInformation);
        }
        }
        excelsys.export(auditManager.auditExport(limit), "系统审计Excel报表");
        try
        {
            // 中文文件名需要编码
            String fileName = "auditlog_" + DateUtil.curDateStr8();
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
     * <排序>
     * <前天点击各字段上的排序按钮实现功能>
     * @see [ExcelAudit,StringUtil]
     */
    public String sort(){
        LOG.info("[AuditAction] enter method sort() ...");
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
    		
    		sr = auditManager.sortAudit(map, page);
    		if (sr != null)
            {
                List<Audit> auditList = (List<Audit>)sr.getList();
                request.setAttribute("auditList", auditList);
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
    
    /**
     * 测试
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    /*public String insert()
    {
        List<String> list = new ArrayList<String>();
        list.add("测试！！！！！！");
        auditManager.insertByLoginOperator(8, "192.168.1.164", list);
        return SUCCESS;
        
    }*/
    
    public AuditService getAuditManager()
    {
        return auditManager;
    }
    
    public void setAuditManager(AuditService auditManager)
    {
        this.auditManager = auditManager;
    }
    
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
    public String getKeyword()
    {
        return keyword;
    }
    
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
    
    public String getAuditUserLoginName()
    {
        return auditUserLoginName;
    }
    
    public void setAuditUserLoginName(String auditUserLoginName)
    {
        this.auditUserLoginName = auditUserLoginName;
    }
    
    public String getAuditOperationProcedure()
    {
        return auditOperationProcedure;
    }
    
    public void setAuditOperationProcedure(String auditOperationProcedure)
    {
        this.auditOperationProcedure = auditOperationProcedure;
    }
    
    public String getAuditOperationTime()
    {
        return auditOperationTime;
    }
    
    public void setAuditOperationTime(String auditOperationTime)
    {
        this.auditOperationTime = auditOperationTime;
    }
    
    public String getAuditOperationIp()
    {
        return auditOperationIp;
    }
    
    public void setAuditOperationIp(String auditOperationIp)
    {
        this.auditOperationIp = auditOperationIp;
    }
    
    public String getAuditInformation()
    {
        return auditInformation;
    }
    
    public void setAuditInformation(String auditInformation)
    {
        this.auditInformation = auditInformation;
    }
    
    public String getIds()
    {
        return ids;
    }
    
    public void setIds(String ids)
    {
        this.ids = ids;
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