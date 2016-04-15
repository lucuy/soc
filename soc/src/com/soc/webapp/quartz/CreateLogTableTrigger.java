package com.soc.webapp.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.SettingArchive;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.soc.service.events.EventsService;
import com.soc.service.events.NotAnalyticEventsService;
import com.soc.service.events.OriginalLogService;
import com.soc.service.systemsetting.SettingArchiveService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.systemsetting.rules.RelevanceRuleGroupService;
import com.util.StringUtil;

public class CreateLogTableTrigger
{
    public OriginalLogService getOriginalLogManager()
    {
        return originalLogManager;
    }

    public void setOriginalLogManager(OriginalLogService originalLogManager)
    {
        this.originalLogManager = originalLogManager;
    }

    private EventsService eventsManager;
    private SettingArchive archive;
    private SettingArchiveService archiveManager;
    private NotAnalyticEventsService notAnalyticEventsManager;
    private  RelevanceRuleGroupService relevanceRuleGroupManager;
    private OriginalLogService originalLogManager;
    private SettingService settingManager;
    
    /**
     * <创建表>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void createTableTask()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String tableName = sdf.format(new Date());
        String saveTime=settingManager.queryByKey("save_Time");
        String oldTable="";
        int tmp = Integer.parseInt(tableName.substring(0, 4));
        if (StringUtil.isNotEmpty(saveTime)) {
        	oldTable=tmp-Integer.parseInt(saveTime)+tableName.substring(4, 8);
		}
        if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
        {
       if (StringUtil.isNotEmpty(oldTable)) {
		//删除时间表和序列
    	   eventsManager.deleteTable("tbl_" + oldTable);
    	   eventsManager.deleteSequence("tbl_" + oldTable + "_seq");
    	   //删除未解析
    	   eventsManager.deleteTable("tbl_" + oldTable + "_not_analytic_events");
    	   eventsManager.deleteSequence("tbl_" + oldTable + "_not_analytic_events_seq");
    	   //删除原始事件
    	   eventsManager.deleteTable("tbl_"+oldTable+"_original_log");
    	   eventsManager.deleteSequence("tbl_" + oldTable + "_original_log_seq");
	}
        } 
        int logTable = eventsManager.existsTable("tbl_" + tableName);
        
        if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
        {
        if(logTable==0)
        {
            String seqName = "tbl_" + tableName + "_seq";
            if(StringUtil.isEmpty(eventsManager.existsSeq(seqName)))
            {
                eventsManager.createLogSeq(seqName);
            }
            
            Map<String, String> map = new HashMap<String,String>();
            map.put("tableName", "tbl_" + tableName);
            map.put("primaryKey", "PK_" + tableName);
            map.put("seqName", seqName);
            eventsManager.createLogTable(map);
            
            /*archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setArchiveDate(new Date().getTime());
            archiveManager.createArchive(archive);*/
            archiveManager.createArchive(tableName);
        }
        }else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
        {
        	int result = eventsManager.exsitsqlServerTable("tbl_"+tableName);
        	if(result==0)
        	{
        		 Map<String, String> map = new HashMap<String,String>();
                 map.put("tableName", "tbl_" + tableName);
                 eventsManager.createLogTable(map);
        	}
        	else
        	{
        		System.out.println("当天事件表已经存在");
        	}
        }
        
        int notAnalyticEventsTable = notAnalyticEventsManager.existsTable("tbl_" + tableName + "_not_analytic_events");
        if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
        {
        if(notAnalyticEventsTable==0)
        {
            String seqName = "tbl_" + tableName + "_not_analytic_events_seq";
            if(StringUtil.isEmpty(eventsManager.existsSeq(seqName)))
            {
                notAnalyticEventsManager.createNotAnalyticEventsSeq(seqName);
            }
            
            Map<String, String> map = new HashMap<String,String>();
            map.put("tableName", "tbl_" + tableName + "_not_analytic_events");
            map.put("primaryKey", "PK_" + tableName + "_not_analytic_events");
            map.put("seqName", seqName);
            notAnalyticEventsManager.createNotAnalyticEventsTable(map);
            
            archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setArchiveDate(new Date().getTime());
            archiveManager.createArchive(tableName);
        }
        }else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
        {
        	int result = notAnalyticEventsManager.existsTable("tbl_" + tableName + "_not_analytic_events");
        	if(result==0)
        	{
        		 Map<String, String> map = new HashMap<String,String>();
                 map.put("tableName", "tbl_" + tableName + "_not_analytic_events");
                 notAnalyticEventsManager.createNotAnalyticEventsTable(map);
        	}
        	else
        	{
        		System.out.println("当天的未解析事件表已经创建");
        	}
        }
        	
        int originalLogTable = originalLogManager.existsTable("tbl_"+tableName+"_original_log");
        
        if(GlobalConfig.sqlId.equalsIgnoreCase("pgAdmin"))
        {
        if(originalLogTable==0)
        {
            String seqName="tbl_"+tableName +"_original_log_seq";
            
            if(StringUtil.isEmpty(originalLogManager.existsSeq(seqName)))
            {
                originalLogManager.createOriginalLogSeq(seqName);
            }
            
            Map<String,String> map = new HashMap<String,String>();
            map.put("tableName", "tbl_"+tableName+"_original_log");
            map.put("primarKey","PK_"+tableName+"_original_log");
            map.put("seqName",seqName);
            originalLogManager.createOriginalLogTable(map);
            
            archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setArchiveDate(new Date().getTime());
            archiveManager.createArchive(tableName);
            GlobalConfig.drools.closeSesssion();
            List<RelevanceRuleGroup> relevanceRuleGroups = this.relevanceRuleGroupManager.queryEnableRule();
   	        GlobalConfig.drools.relevanceRuleGroups = relevanceRuleGroups;
   	        GlobalConfig.ruleNameList = initRuleNamesList(relevanceRuleGroups);//初始化规则引擎需要用的名字
   	        GlobalConfig.drools.initEngine(GlobalConfig.ruleNameList,relevanceRuleGroupManager);
        }
        }else if(GlobalConfig.sqlId.equalsIgnoreCase("sqlServer"))
        {
        	int result = originalLogManager.existsTable("tbl_"+tableName+"_original_log");
        	if(result==0)
        	{
        		 Map<String,String> map = new HashMap<String,String>();
                 map.put("tableName", "tbl_"+tableName+"_original_log");
                 originalLogManager.createOriginalLogTable(map);
                 
                
                 archiveManager.createArchive(tableName);
                 GlobalConfig.drools.closeSesssion();
                 List<RelevanceRuleGroup> relevanceRuleGroups = this.relevanceRuleGroupManager.queryEnableRule();
        	     GlobalConfig.drools.relevanceRuleGroups = relevanceRuleGroups;
        	     GlobalConfig.ruleNameList = initRuleNamesList(relevanceRuleGroups);//初始化规则引擎需要用的名字
        	     GlobalConfig.drools.initEngine(GlobalConfig.ruleNameList,relevanceRuleGroupManager);
        	}
        	else
        	{
        		System.out.println("当天原始事件表已经存在");
        	}
        }
        
    }
    
    public EventsService getEventsManager()
    {
        return eventsManager;
    }
    
    public void setEventsManager(EventsService eventsManager)
    {
        this.eventsManager = eventsManager;
    }

    public SettingArchive getArchive()
    {
        return archive;
    }

    public void setArchive(SettingArchive archive)
    {
        this.archive = archive;
    }

    public SettingArchiveService getArchiveManager()
    {
        return archiveManager;
    }

    public void setArchiveManager(SettingArchiveService archiveManager)
    {
        this.archiveManager = archiveManager;
    }

    public NotAnalyticEventsService getNotAnalyticEventsManager()
    {
        return notAnalyticEventsManager;
    }

    public void setNotAnalyticEventsManager(NotAnalyticEventsService notAnalyticEventsManager)
    {
        this.notAnalyticEventsManager = notAnalyticEventsManager;
    }
    /**
     * <加载规则的名字group1Rule1>
     * <功能详细描述>
     * @param relevanceRuleGroups
     * @return
     * @see [类、类#方法、类#成员]
     */
        private List<String> initRuleNamesList(
        		List<RelevanceRuleGroup> relevanceRuleGroups) {
        	//遍历规则组 规则组下还有还多规则 双重循环
    		List<String> ruleName = new Vector<String>();
        	for (RelevanceRuleGroup relevanceRuleGroup : relevanceRuleGroups) {
        		List<RelevanceRule> relevanceRules = relevanceRuleGroup.getRelevanceRules();
        		RelevanceRule relevanceRule  = relevanceRules.get(0);
        			ruleName.add("group"+relevanceRuleGroup.getId()+"Rule"+relevanceRule.getRelevanceRuleId()+"");
        	}
        	return ruleName;
        }

	public RelevanceRuleGroupService getRelevanceRuleGroupManager() {
		return relevanceRuleGroupManager;
	}

	public void setRelevanceRuleGroupManager(
			RelevanceRuleGroupService relevanceRuleGroupManager) {
		this.relevanceRuleGroupManager = relevanceRuleGroupManager;
	}

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}
   
}
