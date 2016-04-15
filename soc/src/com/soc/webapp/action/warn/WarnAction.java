package com.soc.webapp.action.warn;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.soc.model.asset.Asset;
import com.soc.model.asset.system.AssetSystem;
import com.soc.model.user.User;
import com.soc.model.warn.Warn;
import com.soc.service.asset.AssetService;
import com.soc.service.asset.system.AssetSystemService;
import com.soc.service.audit.AuditService;
import com.soc.service.repository.WarnService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <预警发布>
 * <对预警的相关操作：新增、删除、修改、查询>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WarnAction extends BaseAction
{
    private WarnService warnManager;
    
    //预警id
    private int warnId;
    
    //实体类实例
    private Warn warn;
    
    //批量删除的id
    private String result;
    
    //关键字
    private String keyword;
    
    //存储经过模糊查询信息的list
    private List<Warn> listWarn;
    
    //高级查询条件-预警名称
    private String selWarnName;
    
    //高级查询条件-类型
    private String selWarnType;
    
    //高级查询条件-严重等级
    private String selSeriousLevel;
    
    //高级查询条件-状态
    private String selStatus;
    
    //请求的action字符串
    private String actionStr ="query.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ; 
    
    // 预警的状态
    private int status ;
    
    //预警类型
    private int warnType ;
    
    //严重程度
    private int seriousLevel ; 
    
    //审计业务
    private AuditService auditManager;
    /**
     * <查询所有预警>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String findAllWarn()
    {
        log.info("[WarnAction] enter findAllWarn()");
        
        List<Warn> list = warnManager.findAllWarn();
        //  ActionContext.getContext().getSession().put("allList", list);
        ServletActionContext.getRequest().setAttribute("allList", list);
        
        return "success";
    }
    
    /**
     * <新增预警>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String insertWarn()
    {
        
        log.info("[WarnAction] enter insertWarn()");
        if(warn != null){
        	
        	if(warn.getPublicDate() == null){
        		warn.setPublicDate(new Date()) ; 
        	}
        	
        int i = warnManager.insertWarn(warn);
        
        List<String> fieldList = new ArrayList<String>();
        fieldList.add(warn.getWarnName() + "(" + warn.getWarnName() + ")");
		auditManager.insertByInsertOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "预警发布",super
				.getRequest().getRemoteAddr(), fieldList);
		
		/*String logString="";
		
		
        logString="登录名："+((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName()+"  源IP:"+getRequest().getRemoteAddr()+"   操作时间："+DateUtil.curDateTimeStr19()
        +"   操作类型：新增预警发布";*/
        
       /* for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
			for (String key : map.keySet()) {
				SyslogIF syslogIF = map.get(key);
				SysLogSender.sender(syslogIF, logString);
			}
		}*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增预警发布");
       // logManager.writeSystemAuditLog(logString);
        
        }
        return "success";
    }
    
    /**
     * <通过id查询预警>
     * <查看预警详细信息>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryById()
    {
        
        log.info("[WarnAction] enter queryById()");
        
        Warn warn = new Warn();
        warn.setWarnId(warnId);
        
        Warn warn1 = warnManager.queryById(warn);
        // ActionContext.getContext().getSession().put("queryId", warn1);
        ServletActionContext.getRequest().setAttribute("queryId", warn1);
        
        return "success";
    }
    
    /**
     * <删除预警>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String deleteById()
    {
        log.info("[WarnAction] enter deleteById()");
        
        String s = result.substring(0, result.length() - 1);
        
        warnManager.deleteWarn(s);
        
        List<String> fieldList = new ArrayList<String>();
        fieldList.add(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserLoginName() + "(" + ((User) this.getSession()
						.getAttribute("SOC_LOGON_USER")).getUserLoginName() + ")");
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "预警发布",super
				.getRequest().getRemoteAddr(), fieldList);
		
		/*String logString="";
		
		
        logString="登录名："+((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName()+"  源IP:"+getRequest().getRemoteAddr()+"   操作时间："+DateUtil.curDateTimeStr19()
        +"   操作类型：删除预警发布";*/
        
       /* for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
			for (String key : map.keySet()) {
				SyslogIF syslogIF = map.get(key);
				SysLogSender.sender(syslogIF, logString);
			}
		}*/
      //  logManager.writeSystemAuditLog(logString);
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除预警发布");
        
        return "success";
    }
    
    /**
     * <修改预警信息>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateById()
    {
        log.info("[WarnAction] enter updateById()");
        
        warn.setWarnId(warnId);
        warnManager.updateWarn(warn);
        
        List<String> fieldList = new ArrayList<String>();
        fieldList.add(warn.getWarnName() + "(" + warn.getWarnName() + ")");
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "预警发布",super
				.getRequest().getRemoteAddr(), fieldList);
		
		/*String logString="";
		
		
        logString="登录名："+((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName()+"  源IP:"+getRequest().getRemoteAddr()+"   操作时间："+DateUtil.curDateTimeStr19()
        +"   操作类型：更新预警发布";*/
        
       /* for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
			for (String key : map.keySet()) {
				SyslogIF syslogIF = map.get(key);
				SysLogSender.sender(syslogIF, logString);
			}
		}*/
       // logManager.writeSystemAuditLog(logString);
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改预警发布");
        
        return "success";
    }
    
    /**
     * <高级、快速查询>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String query()
    {
        log.info("[WarnAction] enter query()");
        
        HttpServletRequest request = super.getRequest();
        Page page = null;
        SearchResult<Warn> sr = null;
        
        //处理数据分页的其实条数
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
			
			if("已发布".equals(keyword)){
				status=100;
			}
			if("未发布".equals(keyword)){
				status=50;
			}
			if("已过期".equals(keyword)){
				status=20;
			}
			if("已消除".equals(keyword)){
				status=0;
			}
            
		    if("新漏洞".equals(keyword)){
		    	warnType=1;
		    }
		    if("蠕虫".equals(keyword)){
		    	warnType=2;
		    }
            
            if("低级".equals(keyword)){
            	seriousLevel=1  ;
            }
            if("中低级".equals(keyword)){
            	seriousLevel=2 ;
            }
            if("中级".equals(keyword)){
            	seriousLevel=3  ;
            }
            if("中高级".equals(keyword)){
            	seriousLevel=4  ;
            }
            if("高级".equals(keyword)){
            	seriousLevel=5  ;
            }
            
            map.put("keyword", keyword);
        }
        if (StringUtil.isNotBlank(selWarnName))
        {
            map.put("selWarnName", selWarnName);
        }
        if (StringUtil.isNotBlank(selWarnType))
        {
            map.put("selWarnType", selWarnType);
        }
        if (StringUtil.isNotBlank(selSeriousLevel))
        {
            map.put("selSeriousLevel", Integer.parseInt(selSeriousLevel));
        }
        if(StringUtil.isNotBlank(selStatus)){
        	map.put("selStatus", Integer.parseInt(selStatus));
        }
        if(status != 0){
        	map.put("status", status) ; 
        }
        if(warnType != 0){
        	map.put("warnType",warnType);
        }
        if(seriousLevel != 0){
        	map.put("seriousLevel", seriousLevel);
        }
        
        // 根据map中存储的查询条件，查找符合条件的预警信息
        sr = warnManager.query(map, page);
        
        listWarn = sr.getList();
        
        page = sr.getPage();
        
        request.setAttribute("Page", sr.getPage());
        
        ServletActionContext.getRequest().setAttribute("allList", listWarn);
        return "success";
    }
    
    private List<Asset> assetList ; 
    private Asset asset ; 
    private AssetService assetManager ; 
    private AssetSystemService systemManager ; 
    
    
	public String queryAjaxByWarn() {
		log.info("[AssetAction] Enter method queryAjaxByWarn()...");
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取前台传递的操作系统字符串
		if (StringUtil.isNotBlank(asset.getAssetSupportDevice())) {
			String strInfo =  asset.getAssetSupportDevice() ; 
			//String strInfoResult = "('"+strInfo.replaceAll("\\|","','")+"')";
			String arr[] = strInfo.split("\\|") ;
			int size = arr.length ; 
			for(int i=0;i<size ; i++){
				map.put("parameter"+i, arr[i]) ;
			}
			//通过字符串模糊查询到的系统对象
			List<AssetSystem> list =  systemManager.queryAssetSystemByString(map) ; 
			String numStr = "" ; 
			for(int i=0;i<list.size();i++){
				numStr+= list.get(i).getAssetSystemId()+"," ;
			}
			if(StringUtil.isEmpty(numStr)){
				assetList = new ArrayList<Asset>() ; 
				return SUCCESS ; 
			}
			numStr = numStr.substring(0, numStr.length()-1) ;
			map.clear() ; 
			map.put("asset_system_id", numStr);
			assetList = assetManager.queryByAssetSupportDevice(map);
		}
		return SUCCESS ;
	}
    
	
	
    
    public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public WarnService getWarnManager()
    {
        return warnManager;
    }
    
    public void setWarnManager(WarnService warnManager)
    {
        this.warnManager = warnManager;
    }
    
    public int getWarnId()
    {
        return warnId;
    }
    
    public void setWarnId(int warnId)
    {
        this.warnId = warnId;
    }
    
    public Warn getWarn()
    {
        return warn;
    }
    
    public void setWarn(Warn warn)
    {
        this.warn = warn;
    }
    
    public String getResult()
    {
        return result;
    }
    
    public void setResult(String result)
    {
        this.result = result;
    }
    
    public String getKeyword()
    {
        return keyword;
    }
    
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
    
    public List<Warn> getListWarn()
    {
        return listWarn;
    }
    
    public void setListWarn(List<Warn> listWarn)
    {
        this.listWarn = listWarn;
    }
    
    public String getSelWarnName()
    {
        return selWarnName;
    }
    
    public void setSelWarnName(String selWarnName)
    {
        this.selWarnName = selWarnName;
    }
    
    public String getSelWarnType()
    {
        return selWarnType;
    }
    
    public void setSelWarnType(String selWarnType)
    {
        this.selWarnType = selWarnType;
    }
    
    public String getSelSeriousLevel()
    {
        return selSeriousLevel;
    }
    
    public void setSelSeriousLevel(String selSeriousLevel)
    {
        this.selSeriousLevel = selSeriousLevel;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getWarnType() {
		return warnType;
	}

	public void setWarnType(int warnType) {
		this.warnType = warnType;
	}

	public int getSeriousLevel() {
		return seriousLevel;
	}

	public void setSeriousLevel(int seriousLevel) {
		this.seriousLevel = seriousLevel;
	}

	public String getSelStatus() {
		return selStatus;
	}

	public void setSelStatus(String selStatus) {
		this.selStatus = selStatus;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public AssetSystemService getSystemManager() {
		return systemManager;
	}

	public void setSystemManager(AssetSystemService systemManager) {
		this.systemManager = systemManager;
	}


}
