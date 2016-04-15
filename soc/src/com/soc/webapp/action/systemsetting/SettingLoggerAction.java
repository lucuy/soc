package com.soc.webapp.action.systemsetting;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.systemsetting.Collector;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <采集器配置>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [V100R001C001, 2012-11-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingLoggerAction extends BaseAction
{
    private static final long serialVersionUID = 1L;
    
    private SettingCollectorService settingLoggerManager;
    
    private List<Collector> collectorList;
    
    private Collector collector;
    
    private int collectorId;
    
    private String delIdVal;
    
    private String upIdVal;
    
    private String collectorIp;
    
    private String collectorWalkPort;
    
    private String collectorGroup;
    
    private String collectorRelation;
    
    private String collectorTime;
    
    private String collectorEnter;
    
    private String collectorTrip;
    
    private String collectorLog;
    
    private String collectorAgentPort;
    
    private String collectorUpTime;
    
    private String collectorIntervalTime;
    
    private String collectorPattern;
    
    private String collectorBasic;
    
    private String collectorMac;
    
    private String collectorOid;
    
    private String treeQuery;
    
    private List<Map> ipList;
    
    private List<Map> groupList;
    
    private int collectNetwork = 2;
    
    private int queryId; //根据id查询数资源
    
    private int collectorStatus = 2;
    
    public void setCollectNetwork(int collectNetwork) {
		this.collectNetwork = collectNetwork;
	}

	public void setCollectorStatus(int collectorStatus) {
		this.collectorStatus = collectorStatus;
	}

	private String collectorNo;
    
    private String keyword;
    
    private String delErr;
    
    // 审计业务管理类
    private AuditService auditManager;
    
    Collector setting = new Collector();
    
    /** <查询采集器的配置>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String logger()
    {
        Page page = null;
        SearchResult sr = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("display", 1);
        HttpServletRequest request = super.getRequest();
        
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
        // 根据map中存储的查询条件，查找符合条件的用户列表
        sr = settingLoggerManager.queryPage(map, page);
        
        // 对查找的结果为分页赋值
        if (sr != null)
        {
            collectorList = (List<Collector>)sr.getList();
          
            request.setAttribute("Page", sr.getPage());
        }
        else
        {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        
        /*collectorList = settingLoggerManager.queryCollector();*/
        
        return SUCCESS;
    }
    
    /** <保存采集器的配置>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String saveLogger()
    {
        List<String> fieldList = new ArrayList<String>();
        
        setting.setCollectorId(collectorId);
        setting.setCollectorIp(collectorIp.trim());
        setting.setCollectorWalkPort(collectorWalkPort);
//        setting.setCollectorGroup(collectorGroup);
        setting.setCollectorTime(collectorTime);
        setting.setCollectorEnter(collectorEnter);
        setting.setCollectorTrip(collectorTrip);
        setting.setCollectorLog(collectorLog);
        setting.setCollectorAgentPort(collectorAgentPort);
        setting.setCollectorUpTime(collectorUpTime);
        setting.setCollectorIntervalTime(collectorIntervalTime);
        setting.setCollectorPattern(collectorPattern);
        setting.setCollectorBasic(collectorBasic);
        setting.setCollectorMac(collectorMac.toUpperCase());
        setting.setCollectorOid(collectorOid);
        setting.setCollectorStatus(1);
        setting.setCollectNetwork(Integer.valueOf(collectNetwork));
        settingLoggerManager.setCollector(setting);
        //fieldList.add()
        fieldList.add(collectorBasic+"("+collectorBasic+")");
        
        auditManager.insertByInsertOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
            "采集器",
            super.getRequest().getRemoteAddr(),
            fieldList);
        
       /* String logString1 = "";
        logString1 =
            "登录名：" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + "  源IP:"
                + getRequest().getRemoteAddr() + "   操作时间：" + DateUtil.curDateTimeStr19() + "   操作类型:添加采集器";
        
        logManager.writeSystemAuditLog(logString1);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增采集器");
        return SUCCESS;
    }
    
    /** <查询树目录>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    /*public String queryMenu()
    {
        String objectPath = super.getRequest().getContextPath();
        treeQuery = settingLoggerManager.queryTree(objectPath);
        return SUCCESS;
    }*/
    
    /** <根据ID查询>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String queryId()
    {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("querytreeeid", collectorId);
        
        settingLoggerManager.queryCollectorTree(map);
        //collector = settingLoggerManager.queryById(collectorId);
        
        collectorList = settingLoggerManager.queryCollectorTree(map);
        String oid = collectorList.get(0).getCollectorOid();
//        String group = collectorList.get(0).getCollectorGroup();
        if (StringUtil.isNotBlank(oid))
        {
            ipList = new ArrayList<Map>();
            String[] ips = StringUtil.split(oid, ",");
            
//            groupList = new ArrayList<Map>();
//            String[] groups = StringUtil.split(group, ",");
            
            for (int p = 0; p < ips.length; p++)
            {
                String[] ipf = StringUtil.split(ips[p], ",");
//                String[] groupf = StringUtil.split(groups[p], ",");
                Map<String, String> ipm = new HashMap<String, String>();
                for (int k = 0; k < ipf.length; k++)
                {
                    ipm.put("ip" + k, ipf[k]);
//                    ipm.put("group" + k, groupf[k]);
                    
                }
                ipList.add(p, ipm);
                
            }
            
        }
        
        return SUCCESS;
    }
    
    /** <根据ID更新数据>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateId()
    {
        List<String> fieldList = new ArrayList<String>();
        
        setting.setCollectorId(collectorId);
        setting.setCollectorIp(collectorIp.trim());
        setting.setCollectorWalkPort(collectorWalkPort);
        setting.setCollectorGroup(collectorGroup);
        setting.setCollectorTime(collectorTime);
        setting.setCollectorEnter(collectorEnter);
        setting.setCollectorTrip(collectorTrip);
        setting.setCollectorLog(collectorLog);
        setting.setCollectorAgentPort(collectorAgentPort);
        setting.setCollectorUpTime(collectorUpTime);
        setting.setCollectorIntervalTime(collectorIntervalTime);
        setting.setCollectorPattern(collectorPattern);
        setting.setCollectorBasic(collectorBasic);
        setting.setCollectorMac(collectorMac.toUpperCase());
        setting.setCollectorOid(collectorOid);
        setting.setCollectorStatus(1);
        settingLoggerManager.updateId(setting);
        
        fieldList.add(collectorBasic+"("+collectorBasic+")");
        auditManager.insertByUpdateOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
            "采集器",
            super.getRequest().getRemoteAddr(),
            fieldList);
        //syslog日志
       /* String logString1 = "";
        logString1 =
            "登录名：" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + "  源IP:"
                + getRequest().getRemoteAddr() + "   操作时间：" + DateUtil.curDateTimeStr19() + "   操作类型:修改采集器";
        
        logManager.writeSystemAuditLog(logString1);*/
        logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改关采集器");
        
        
        return SUCCESS;
    }
    
    public String updateById(){
        ConcurrentLinkedQueue<Collector> settingClq = new ConcurrentLinkedQueue<Collector>();
        
        setting.setCollectorId(collectorId);
        setting.setCollectorIp(collectorIp);
        setting.setCollectorWalkPort(collectorWalkPort);
        setting.setCollectorGroup(collectorGroup);
        setting.setCollectorTime(collectorTime);
        setting.setCollectorEnter(collectorEnter);
        setting.setCollectorTrip(collectorTrip);
        setting.setCollectorLog(collectorLog);
        setting.setCollectorAgentPort(collectorAgentPort);
        setting.setCollectorUpTime(collectorUpTime);
        setting.setCollectorIntervalTime(collectorIntervalTime);
        setting.setCollectorPattern(collectorPattern);
        setting.setCollectorBasic(collectorBasic);
        setting.setCollectorMac(collectorMac);
        setting.setCollectorOid(collectorOid);
        settingClq.add(collector);
        settingLoggerManager.updateById(settingClq);
        return SUCCESS;
    }
    
    public String delId(){
        
        if (StringUtil.isNotBlank(String.valueOf(delIdVal)))
        {
            List<String> fieldList = new ArrayList<String>();
            // 针对多个规则操作
            if (String.valueOf(delIdVal).indexOf(",") > 0)
            {
                
                String[] checked = String.valueOf(delIdVal).split(",");
                
                // 循环遍历需要执行更新状态的规则ID并执行更新状态操作
                for (String checkid : checked)
                {
                    // 批量注销规则Integer.parseInt(checkid)
                    
                    Collector collectorObject = settingLoggerManager.queryById(Integer.parseInt(checkid));
                    fieldList.add(collectorObject.getCollectorBasic()+"("+collectorObject.getCollectorBasic()+")");
                    settingLoggerManager.delId(Integer.parseInt(checkid));
                }
            }
            
            // 单个注销规则
            else
            {
                Collector collectorObject = settingLoggerManager.queryById(Integer.parseInt(delIdVal));
                fieldList.add(collectorObject.getCollectorBasic()+"("+collectorObject.getCollectorBasic()+")");
                settingLoggerManager.delId(Integer.parseInt(delIdVal));
            }
            
            auditManager.insertByDeleteOperator(((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId(),
                "采集器",
                super.getRequest().getRemoteAddr(),
                fieldList);
            
            //syslog日志
           /* String logString = "";
            
            logString =
                "登录名：" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + "  源IP:"
                    + getRequest().getRemoteAddr() + "   操作时间：" + DateUtil.curDateTimeStr19() + "   操作类型:删除采集器";
            logManager.writeSystemAuditLog(logString);*/
            logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除采集器");
        }
        return SUCCESS;
    }
    
    public void selectAsset(){
        List<String> fieldList = new ArrayList<String>();
        String flag = "true";
        if (String.valueOf(delErr).indexOf(",") > 0)
        {
            String[] checked = String.valueOf(delErr).split(",");
            
            // 循环遍历需要执行更新状态的规则ID并执行更新状态操作
            for (String checkid : checked)
            {
                // 批量注销规则Integer.parseInt(checkid)
                
                Collector collectorObject = settingLoggerManager.queryById(Integer.parseInt(checkid));
                fieldList.add(collectorObject.getCollectorBasic()+"("+collectorObject.getCollectorBasic()+")");
                int err = settingLoggerManager.selectAsset(Integer.parseInt(checkid));
                   if(err!=0){
                       flag = "false";
                       break;
                   }else{
                   }
            }
        }
        
        // 单个注销规则
        else
        {
            Collector collectorObject = settingLoggerManager.queryById(Integer.parseInt(delErr));
            fieldList.add(collectorObject.getCollectorBasic()+"("+collectorObject.getCollectorBasic()+")");
            int err = settingLoggerManager.selectAsset(Integer.parseInt(delErr));
            if(err!=0){
                flag = "false";
            }else{
            }
        }
      //将flag返回给页面
        try
        {
            getResponse().getWriter().write(flag);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void selectMac(){
     // 标识此策略名是否存在
        String flag = "false";
        
        if (StringUtil.isNotBlank(collectorMac))
        {
            List<Collector> list = settingLoggerManager.selectMac(collectorMac);
            if (list.size() > 0)
            {
                flag = "true";
            }
        }
        //将flag返回给页面
        try
        {
            getResponse().getWriter().write(flag);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public String register(){
        return SUCCESS;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateLoggerStatus(){
        if(collectorNo!=null){
            String[] collectorValue = collectorNo.split(",");
            for(String temp:collectorValue){
                setting.setCollectorId(Integer.valueOf(temp));
                setting.setCollectorStatus(Integer.valueOf(collectorStatus));
                settingLoggerManager.updateLoggerStatus(setting);
            }
            logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
            		.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改采集器状态");
        }
        return SUCCESS;
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String search(){
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
        if (StringUtil.isNotBlank(collectorBasic))
        {
             map.put("collectorBasic", collectorBasic);
        }
       /* if (StringUtil.isNotBlank(collectorStatus))
        {
             map.put("collectorStatus", collectorStatus);
        }
        if (StringUtil.isNotBlank(collectNetwork))
        {
             map.put("collectNetwork", collectNetwork);
        }*/
        if(collectorStatus == 0 || collectorStatus==1)
        {
        	map.put("collectorStatus", collectorStatus);
        }
        if(collectNetwork==0 ||collectNetwork==1)
        {
        	map.put("collectNetwork", collectNetwork);
        }
        //根据条件查询相应审计数据
        sr = settingLoggerManager.queryPage(map, page);
        
        if (sr != null)
        {
            collectorList = (List<Collector>)sr.getList();
            //request.setAttribute("associationList", collectorList);
            
            request.setAttribute("Page", sr.getPage());
        }
        else
        {
            request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
        }
        return SUCCESS;
    }
    
    public String getCollectorIp()
    {
        return collectorIp;
    }
    
    public void setCollectorIp(String collectorIp)
    {
        this.collectorIp = collectorIp;
    }
    
    public String getCollectorWalkPort()
    {
        return collectorWalkPort;
    }
    
    public void setCollectorWalkPort(String collectorWalkPort)
    {
        this.collectorWalkPort = collectorWalkPort;
    }
    
    public String getCollectorGroup()
    {
        return collectorGroup;
    }
    
    public void setCollectorGroup(String collectorGroup)
    {
        this.collectorGroup = collectorGroup;
    }
    
    public String getCollectorRelation()
    {
        return collectorRelation;
    }
    
    public void setCollectorRelation(String collectorRelation)
    {
        this.collectorRelation = collectorRelation;
    }
    
    public String getCollectorTime()
    {
        return collectorTime;
    }
    
    public void setCollectorTime(String collectorTime)
    {
        this.collectorTime = collectorTime;
    }
    
    public String getCollectorEnter()
    {
        return collectorEnter;
    }
    
    public void setCollectorEnter(String collectorEnter)
    {
        this.collectorEnter = collectorEnter;
    }
    
    public String getCollectorTrip()
    {
        return collectorTrip;
    }
    
    public void setCollectorTrip(String collectorTrip)
    {
        this.collectorTrip = collectorTrip;
    }
    
    public String getCollectorLog()
    {
        return collectorLog;
    }
    
    public void setCollectorLog(String collectorLog)
    {
        this.collectorLog = collectorLog;
    }
    
    public String getCollectorAgentPort()
    {
        return collectorAgentPort;
    }
    
    public void setCollectorAgentPort(String collectorAgentPort)
    {
        this.collectorAgentPort = collectorAgentPort;
    }
    
    public String getCollectorUpTime()
    {
        return collectorUpTime;
    }
    
    public void setCollectorUpTime(String collectorUpTime)
    {
        this.collectorUpTime = collectorUpTime;
    }
    
    public String getCollectorIntervalTime()
    {
        return collectorIntervalTime;
    }
    
    public void setCollectorIntervalTime(String collectorIntervalTime)
    {
        this.collectorIntervalTime = collectorIntervalTime;
    }
    
    public String getCollectorPattern()
    {
        return collectorPattern;
    }
    
    public void setCollectorPattern(String collectorPattern)
    {
        this.collectorPattern = collectorPattern;
    }
    
    public String getCollectorBasic()
    {
        return collectorBasic;
    }
    
    public void setCollectorBasic(String collectorBasic)
    {
        this.collectorBasic = collectorBasic;
    }
    
    public String getCollectorMac()
    {
        return collectorMac;
    }
    
    public void setCollectorMac(String collectorMac)
    {
        this.collectorMac = collectorMac;
    }
    
    public List<Collector> getCollectorList()
    {
        return collectorList;
    }
    
    public void setCollectorList(List<Collector> collectorList)
    {
        this.collectorList = collectorList;
    }
    
    public SettingCollectorService getSettingLoggerManager()
    {
        return settingLoggerManager;
    }
    
    public void setSettingLoggerManager(SettingCollectorService settingLoggerManager)
    {
        this.settingLoggerManager = settingLoggerManager;
    }
    
    public String getTreeQuery()
    {
        return treeQuery;
    }
    
    public void setTreeQuery(String treeQuery)
    {
        this.treeQuery = treeQuery;
    }
    
    public int getQueryId()
    {
        return queryId;
    }
    
    public void setQueryId(int queryId)
    {
        this.queryId = queryId;
    }
    
    public int getCollectorId()
    {
        return collectorId;
    }
    
    public void setCollectorId(int collectorId)
    {
        this.collectorId = collectorId;
    }

    public List<Map> getIpList()
    {
        return ipList;
    }

    public void setIpList(List<Map> ipList)
    {
        this.ipList = ipList;
    }

    public List<Map> getGroupList()
    {
        return groupList;
    }

    public void setGroupList(List<Map> groupList)
    {
        this.groupList = groupList;
    }

    public String getDelIdVal()
    {
        return delIdVal;
    }

    public void setDelIdVal(String delIdVal)
    {
        this.delIdVal = delIdVal;
    }

    public String getUpIdVal()
    {
        return upIdVal;
    }

    public void setUpIdVal(String upIdVal)
    {
        this.upIdVal = upIdVal;
    }

    public String getCollectorOid()
    {
        return collectorOid;
    }

    public void setCollectorOid(String collectorOid)
    {
        this.collectorOid = collectorOid;
    }

  /*  public AuditService getAuditManager()
    {
        return auditManager;
    }

    public void setAuditManager(AuditService auditManager)
    {
        this.auditManager = auditManager;
    }*/

  
    public String getCollectorNo()
    {
        return collectorNo;
    }

    public void setCollectorNo(String collectorNo)
    {
        this.collectorNo = collectorNo;
    }

  

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getDelErr()
    {
        return delErr;
    }

    public void setDelErr(String delErr)
    {
        this.delErr = delErr;
    }

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}
}