package com.soc.webapp.action.startup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.xml.sax.InputSource;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.eventtypetag.EventTypeTag;
import com.soc.model.eventtypetag.eventcategorytag;
import com.soc.model.home.HomeDiv;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.SettingArchive;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.asset.AssetService;
import com.soc.service.events.EventsService;
import com.soc.service.events.NotAnalyticEventsService;
import com.soc.service.events.OriginalLogService;
import com.soc.service.eventtypetag.EventCategoryTagService;
import com.soc.service.eventtypetag.EventTypeTagService;
import com.soc.service.systemsetting.SettingArchiveService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.systemsetting.rules.RelevanceRuleGroupService;
import com.soc.webapp.action.systemsetting.SettingSetCpuData;
import com.util.Env;
import com.util.SysLogSender;
import com.util.drools.Drools;

public class InitServlet extends HttpServlet
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6595304017326236125L;
    
    private transient Log Log = LogFactory.getLog(getClass());
    
    private SettingService settingManager;
    
    private SettingCollectorService settingLoggerManager;
    private AlertMessageSerive alertMessageManager;
    @SuppressWarnings("unused")
	private EventsService eventsManager;
    
    private SettingArchive archive;
    @SuppressWarnings("unused")
	private static int count=0;
    private EventTypeTagService eventTypeTagManager;
    //事件类别业务类
    private EventCategoryTagService eventCategroyTagManger;
    private RelevanceRuleGroupService relevanceRuleGroupManager;
    public SettingArchive getArchive()
    {
        return archive;
    }
    
    public void setArchive(SettingArchive archive)
    {
        this.archive = archive;
    }
    
    @SuppressWarnings("unused")
	private SettingArchiveService archiveManager;
    
    @SuppressWarnings("unused")
	private NotAnalyticEventsService notAnalyticEventsManager;
    
    @SuppressWarnings("unused")
	private OriginalLogService originalLogManager;
    private AssetService assetManager ;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(ServletConfig config)
    {
    	HomeDiv.map.put("d1", "{"+
                "attrs: {"+
                    "id: 'd1'"+
                "},"+
"title:'<span >当天事件统计</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout('initcolumnChart();', 300 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initcolumnChart();\", 380 );}},"+
                "content: {"+
                    "style: {"+
                        "height: '300px'"+
                    "},"+
                    "type: 'text',"+
                    "text: '<div id=\"chart_1\" style = \"height:300px\"></div>',"+
                    "beforeShow: function(aa) {"+
                    "},"+
                    "afterShow: function() {"+
                    	"initcolumnChart();"+
                    "}"+
                "}}");   




HomeDiv.map.put("d2", "{"+
        "attrs: {"+
            "id: 'd2'"+
        "},"+
"title:'<span >当前采集器统计</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout('loadCol();', 350 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"loadCol()\", 350 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"dataList\"><table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"class=\"tab2\" id=\"colTable\"><tr height=\"28\" id=\"collectionTR\"><td width=\"20%\" class=\"biaoti\">采集器名称</td><td width=\"20%\" class=\"biaoti\">在线状态</td><td width=\"25%\" class=\"biaoti\">健康状态</td><td  class=\"biaoti\">接收事件数</td></tr><tbody id=\"colBody\"></tbody></table></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"loadCol();"+
            "}"+
        "}}");  



HomeDiv.map.put("d3", "{"+
        "attrs: {"+
            "id: 'd3'"+
        "},"+
"title:'<span >最近7天的告警类型分布</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initAreaChart();\", 300 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initAreaChart();\", 380 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "   text: '<div id=\"chart_2\" style = \" height: 300px\"></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initAreaChart();"+
            "}"+
        "}}");  

HomeDiv.map.put("d4", "{"+
        "attrs: {"+
            "id: 'd4'"+
        "},"+
"title:'<span >最近7天告警最多的10个资产</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initColumnChart1();\", 300 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initColumnChart1();\", 380 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"chart_3\"  style = \" height: 300px\" ></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initColumnChart1();;"+
            "}"+
        "}}"); 

HomeDiv.map.put("d5", "{"+
        "attrs: {"+
            "id: 'd5'"+
        "},"+
"title:'<span >最近一小时最新的5条告警</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"loadAlarm11();\", 300 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"loadAlarm11();\", 480 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"dataList\"><table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"class=\"tab2\"><tr height=\"28\"> <td width=\"15%\" class=\"biaoti\">告警编号</td><td width=\"10%\" class=\"biaoti\">等级</td><td width=\"30%\" class=\"biaoti\">发生时间</td><td width=\"*\" class=\"biaoti\">事件名称</td></tr><tbody id=\"alertMessageId11\"></tbody></table></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"loadAlarm11();"+
            "}"+
        "}}"); 


HomeDiv.map.put("d6", "{"+
        "attrs: {"+
            "id: 'd6'"+
        "},"+
"title:'<span >最近一小时内的告警类型分布</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initAreaChart6();\", 400 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initAreaChart6();\", 480 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "   text: '<div id=\"chart_6\" style = \" height: 300px\"></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initAreaChart6();"+
            "}"+
        "}}");  

HomeDiv.map.put("d7", "{"+
        "attrs: {"+
            "id: 'd7'"+
        "},"+
"title:'<span >最近24小时内的告警类型分布</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initAreaChart7();\", 200 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initAreaChart7();\", 280 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "   text: '<div id=\"chart_7\" style = \" height: 300px\"></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initAreaChart7();"+
            "}"+
        "}}");  


HomeDiv.map.put("d8", "{"+
        "attrs: {"+
            "id: 'd8'"+
        "},"+
"title:'<span >最近一小时告警数量最多的10个资产</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initColumnChart8();\",400 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initColumnChart8();\", 480 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"chart_8\"  style = \" height: 300px\" ></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initColumnChart8();"+
            "}"+
        "}}"); 

HomeDiv.map.put("d9", "{"+
        "attrs: {"+
            "id: 'd9'"+
        "},"+
"title:'<span >最近24小时告警数量最多的10个资产</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initColumnChart8();initColumnChart9();\",500 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initColumnChart9();\", 580 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"chart_9\"  style = \" height: 300px\" ></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initColumnChart9();"+
            "}"+
        "}}"); 

HomeDiv.map.put("d10", "{"+
        "attrs: {"+
            "id: 'd10'"+
        "},"+
"title:'<span >24小时内重复最多的10个告警</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initColumnChart10();\",550 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initColumnChart10();\", 600 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"chart_10\"  style = \" height: 300px\" ></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initColumnChart10();"+
            "}"+
        "}}"); 
HomeDiv.map.put("d11", "{"+
        "attrs: {"+
        "id: 'd11'"+
    "},"+
"title:'<span >最近7天最新的10条告警</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"loadAlarm();\", 240 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"loadAlarm();\", 290 );}},"+
    "content: {"+
        "style: {"+
            "height: '300px'"+
        "},"+
        "type: 'text',"+
        "text:'<div id=\"dataList\"><table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"class=\"tab2\"><tr height=\"28\"><td width=\"15%\" class=\"biaoti\">告警编号</td><td width=\"10%\" class=\"biaoti\">等级</td><td width=\"30%\" class=\"biaoti\">发生时间</td><td width=\"*\" class=\"biaoti\">事件名称</td></tr><tbody id=\"alertMessageId\"></tbody></table></div>',"+
        "beforeShow: function(aa) {"+
        "},"+
        "afterShow: function() {"+
        "loadAlarm();"+
        "}"+
    "}}");  

HomeDiv.map.put("d12", "{"+
        "attrs: {"+
            "id: 'd12'"+
        "},"+
"title:'<span >最近24小时最新的10条告警</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"loadAlarm12();\", 280 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"loadAlarm12();\", 320 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"dataList\"><table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"class=\"tab2\"><tr height=\"28\"> <td width=\"15%\" class=\"biaoti\">告警编号</td><td width=\"10%\" class=\"biaoti\">等级</td><td width=\"30%\" class=\"biaoti\">发生时间</td><td width=\"*\" class=\"biaoti\">事件名称</td></tr><tbody id=\"alertMessageId12\"></tbody></table></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"loadAlarm12();"+
            "}"+
        "}}"); 
        
HomeDiv.map.put("d13", "{"+
        "attrs: {"+
            "id: 'd13'"+
        "},"+
"title:'<span >近期事件趋势</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initchart_13();\",500 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initchart_13();\", 580 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"chart_13\"  style = \" height: 300px\" ></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initchart_13();"+
            "}"+
        "}}"); 




HomeDiv.map.put("d14", "{"+
        "attrs: {"+
            "id: 'd14'"+
        "},"+
"title:'<span >当天的事件类型分布</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initPieChart();\", 300 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initPieChart();\", 380 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "   text: '<div id=\"chart_14\" style = \" height: 300px\"></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initPieChart();"+
            "}"+
        "}}"); 

HomeDiv.map.put("d15", "{"+
        "attrs: {"+
            "id: 'd15'"+
        "},"+
"title:'<span >当天事件IP分布</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initIPBar();\", 300 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initIPBar();\", 380 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"chart_15\"  style = \" height: 300px\" ></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initIPBar();"+
            "}"+
        "}}"); 


HomeDiv.map.put("d16", "{"+
        "attrs: {"+
            "id: 'd16'"+
        "},"+
"title:'<span >最近24小时内最新的10条阀值告警</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout('initARMAlarm();', 350 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initARMAlarm()\", 350 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'	<div id=\"dataList\"><table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"class=\"tab2\" id=\"TenAlarmMessageTable\"><tr height=\"28\" id=\"collectionTR\"><td width=\"20%\" class=\"biaoti\">告警内容</td><td width=\"28%\" class=\"biaoti\">资产名称</td><td class=\"biaoti\">资产IP</td><td width=\"17%\" class=\"biaoti\">阀值</td><td width=\"17%\" class=\"biaoti\">当前值</td></tr><tbody id=\"TenAlarmMessage\"></tbody></table></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initARMAlarm();"+
            "}"+
        "}}");  

HomeDiv.map.put("d17", "{"+
        "attrs: {"+
            "id: 'd17'"+
        "},"+
"title:'<span >上周事件趋势</span>',"+
"singleView: {"+
"width:'99.8%',"+
"enable: function() {"+

"setTimeout(\"initEventsCountForLastWeek();\",400 );"+
"},"+
"recovery: function() {"+


"setTimeout(\"initEventsCountForLastWeek();\", 480 );}},"+
        "content: {"+
            "style: {"+
                "height: '300px'"+
            "},"+
            "type: 'text',"+
            "text:'<div id=\"eventsCountForLastWeek\"  style = \" height: 300px\" ></div>',"+
            "beforeShow: function(aa) {"+
            "},"+
            "afterShow: function() {"+
            	"initEventsCountForLastWeek();"+
            "}"+
        "}}"); 


//在全局homedivmap放入对应的js方法 homeaction拼接js代码用
//这么做的目的是 ie9以下js纠错不知能,移动div后hightchars报错,错误的原因是找不到对应的div
HomeDiv.mapJs.put("d1", "initcolumnChart();");
HomeDiv.mapJs.put("d2", "loadCol();");
HomeDiv.mapJs.put("d3", "initAreaChart();");
HomeDiv.mapJs.put("d4", "initColumnChart1();");
HomeDiv.mapJs.put("d5", "loadAlarm11();");
HomeDiv.mapJs.put("d6", "initAreaChart6();");
HomeDiv.mapJs.put("d7", "initAreaChart7();");
HomeDiv.mapJs.put("d8", "initColumnChart8();");
HomeDiv.mapJs.put("d9", "initColumnChart9();");
HomeDiv.mapJs.put("d10", "initColumnChart10();");
HomeDiv.mapJs.put("d11", "loadAlarm();");
HomeDiv.mapJs.put("d12", "loadAlarm12();");
HomeDiv.mapJs.put("d13", "initchart_13();");
HomeDiv.mapJs.put("d14", "initPieChart();");
HomeDiv.mapJs.put("d15", "initIPBar();");
HomeDiv.mapJs.put("d16", "initARMAlarm();");
HomeDiv.mapJs.put("d17", "initEventsCountForLastWeek();");
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone("Etc/GMT-8");
        java.util.TimeZone.setDefault(tz);
        
        // 得到注入service类
        WebApplicationContext wac =
            WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        
        settingManager = (SettingService)wac.getBean("settingManager");
        
        settingLoggerManager = (SettingCollectorService)wac.getBean("settingLoggerManager");
        
        eventsManager = (EventsService)wac.getBean("eventsManager");
        
        archiveManager = (SettingArchiveService)wac.getBean("archiveManager");
        
        notAnalyticEventsManager = (NotAnalyticEventsService)wac.getBean("notAnalyticEventsManager");
        
        originalLogManager = (OriginalLogService)wac.getBean("originalLogManager");
        
        eventTypeTagManager = (EventTypeTagService)wac.getBean("eventTypeTagManager");
        alertMessageManager = (AlertMessageSerive) wac.getBean("alertMessageManager");
        //获取事件类别的对象
        eventCategroyTagManger = (EventCategoryTagService)wac.getBean("eventCategoryTagManager");
        relevanceRuleGroupManager =  (RelevanceRuleGroupService)wac.getBean("relevanceRuleGroupManager");
        //全部资产
       assetManager = (AssetService) wac
    			.getBean("assetManager");
       Map assetMap = new HashMap();
       assetMap.put("assetGroupId", 1);
       List<Asset> assets = assetManager.query(assetMap);
       for (Asset asset : assets) {
		GlobalConfig.assetGlobleMap.put(asset.getAssetId(), asset);
	}
        // ctx
        GlobalConfig.ctx = config.getServletContext().getRealPath("/");
        //安全策略文件存放路径
        GlobalConfig.securityPolicyFilePath=config.getServletContext().getRealPath("/securityPolicyFile");
      //漏扫联动扫描返回结果存放目录
        GlobalConfig.scanTaskPath=config.getServletContext().getRealPath("/scanData");
        // 初始化系统已有采集器
        List<Collector> collectorList = settingLoggerManager.queryCollector();
        
        for (Collector collector : collectorList)
        {
            GlobalConfig.collector.put(collector.getCollectorMac(), collector.getCollectorReceiveEvents());
            
            //预定义刚启动项目时候采集器不在线
            GlobalConfig.collectorIsOnline.put(collector.getCollectorMac(), 0);
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        
        String tableName = sdf.format(new Date());
        
        //String logTable = eventsManager.existsTable("tbl_" + tableName);
      /*  
        if (StringUtil.isEmpty(logTable))
        {
            String seqName = "tbl_" + tableName + "_seq";
            if (StringUtil.isEmpty(eventsManager.existsSeq(seqName)))
            {
                eventsManager.createLogSeq(seqName);
            }
            
            Map<String, String> map = new HashMap<String, String>();
            map.put("tableName", "tbl_" + tableName);
            map.put("primaryKey", "PK_" + tableName);
            map.put("seqName", seqName);
            eventsManager.createLogTable(map);
            
            archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setArchiveDate(new Date().getTime());
            archiveManager.createArchive(archive);
        }*/
        
       /* String notAnalyticEventsTable =
            notAnalyticEventsManager.existsTable("tbl_" + tableName + "_not_analytic_events");
        if (StringUtil.isEmpty(notAnalyticEventsTable))
        {
            String seqName = "tbl_" + tableName + "_not_analytic_events_seq";
            if (StringUtil.isEmpty(eventsManager.existsSeq(seqName)))
            {
                notAnalyticEventsManager.createNotAnalyticEventsSeq(seqName);
            }
            
            Map<String, String> map = new HashMap<String, String>();
            map.put("tableName", "tbl_" + tableName + "_not_analytic_events");
            map.put("primaryKey", "PK_" + tableName + "_not_analytic_events");
            map.put("seqName", seqName);
            notAnalyticEventsManager.createNotAnalyticEventsTable(map);
            
            archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setArchiveDate(new Date().getTime());
            archiveManager.createArchive(archive);
        }*/
        
     /*   String originalLogTable = originalLogManager.existsTable("tbl_" + tableName + "_original_log");
        
        if (StringUtil.isEmpty(originalLogTable))
        {
            String seqName = "tbl_" + tableName + "_original_log_seq";
            
            if (StringUtil.isEmpty(originalLogManager.existsSeq(seqName)))
            {
                originalLogManager.createOriginalLogSeq(seqName);
            }
            
            Map<String, String> map = new HashMap<String, String>();
            map.put("tableName", "tbl_" + tableName + "_original_log");
            map.put("primarKey", "PK_" + tableName + "_original_log");
            map.put("seqName", seqName);
            originalLogManager.createOriginalLogTable(map);
            
            archive = new SettingArchive();
            archive.setArchiveName(tableName);
            archive.setArchiveStatus(1);
            archive.setArchiveDate(new Date().getTime());
            archiveManager.createArchive(archive);
        }*/
        
        //获得资产计算的可用性值
        GlobalConfig.assetUse = Double.valueOf(Env.getString("use"));
        
        //获得资产计算的完整性价值
        GlobalConfig.assetComplete = Double.valueOf(Env.getString("complete"));
        
        //获得资产计算的保密性价值
        GlobalConfig.assetSecret = Double.valueOf(Env.getString("secret"));
        
       /* Setting setting = new Setting();
        
        setting.setId(191);
        
        setting.setKey("install_time");
        
        setting.setValue(tableName);
        
        
        //设置安装时间
        settingManager.update(setting);*/
        
        try{
        //启动项目的时候加载Syslog
        String temp = settingManager.queryByKey("SETTING_SYSLOG_JSON");
        JSONArray array = JSONArray.fromObject(temp);
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            SysLogSender.updateSysLogConfig(object);
        }
        }
        catch(Exception e)
        {
        	System.out.println("SySlog设置失败！");
        }
        
        GlobalConfig.installTime = tableName;
        // 设置与采集器通信的端口
        //GlobalConfig.MONITOR_LISTENER_PORT = Integer.parseInt(settingManager.queryByKey("monitor_listener_port"));
        
        //new CreateLogTableTrigger().createTableTask();
        GlobalConfig.assetManager  =(AssetService)wac.getBean("assetManager");
        GlobalConfig.alertRuleManager = (AlertRuleService) wac.getBean("alertRuleManager");
        //new CreateLogTableTrigger().createTableTask();
        GlobalConfig.drools = new Drools();
        //加载规则文件
        //这边还是传入规则类的list比较好
        //这里需要改 改成查询所有的条件型规则
        List<RelevanceRuleGroup> relevanceRuleGroups = this.relevanceRuleGroupManager.queryEnableRule();
        GlobalConfig.drools.relevanceRuleGroups = relevanceRuleGroups;
        GlobalConfig.ruleNameList = initRuleNamesList(relevanceRuleGroups);//初始化规则引擎需要用的名字
        GlobalConfig.drools.initEngine( GlobalConfig.ruleNameList,relevanceRuleGroupManager);
        List<EventTypeTag> eventTypeTagList = eventTypeTagManager.query();
        for (EventTypeTag tag : eventTypeTagList)
        {
            
            GlobalConfig.eventTypeTag.put(tag.getEventtypetagId(), tag.getEventtypetagName());
        }
        
        //获得所有的事件类别的列表
        List<eventcategorytag> eventCategoryTagList = eventCategroyTagManger.query();
        
        for (eventcategorytag tag : eventCategoryTagList)
        {
            GlobalConfig.eventCategoryTag.put(tag.getEventcategorykey(), tag.getEventcategoryValue());
        }
        
        //系统信息
        SettingSetCpuData sscd = new SettingSetCpuData();
        try
        {
            sscd.CpuData();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        new Thread(createEventsPool(alertMessageManager,settingLoggerManager)).start();
    	
    }
    
    public EventCategoryTagService getEventCategroyTagManger()
    {
        return eventCategroyTagManger;
    }
    
    public void setEventCategroyTagManger(EventCategoryTagService eventCategroyTagManger)
    {
        this.eventCategroyTagManger = eventCategroyTagManger;
    }
    
    public Runnable createEventsPool(final AlertMessageSerive amm,final SettingCollectorService stm){
    	return new Runnable() {
			
			@SuppressWarnings("resource")
			@Override
			public void run() {
				try {
					ServerSocket serverSocket = new ServerSocket(GlobalConfig.SOCKET_PORT);
					Socket socket =null;
					//System.out.println("启动Socket服务器--Socket-startUP");
					 ExecutorService pool = Executors.newFixedThreadPool(20);
					 while(true){
						 if (GlobalConfig.diskFalg) {
						 socket = serverSocket.accept();
						 pool.execute(createEventsThread(socket,amm,stm));
						 }
					 }
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		};
    }
    public Runnable createEventsThread(final Socket socket,final AlertMessageSerive amm,final SettingCollectorService stm){
    	return new Runnable(){
    		public void run(){
    			socketEvents(socket,amm,stm);
    		}
    	};
    }
    
    /**
     * socket，处理超长日志，最大为4096
     */
    public void socketEvents(Socket socket,final AlertMessageSerive amm,final SettingCollectorService stm){
    //	System.out.println("开始接收事件，IP地址为："+socket.getInetAddress()+"\t count :"+count++);
    	String ip = socket.getInetAddress().toString();
    	InputStream in =null;
    	OutputStream out=null;
    	try {
    		 in=socket.getInputStream();
			 out=socket.getOutputStream();
			 byte[] buf = new byte[4096];
			 int len = in.read(buf);
			 //接收事件
			 String event = new String(buf, 0, len);
			 System.out.println("接收事件地址："+ip+"\t事件："+event);
			 if(event.startsWith("<msg>")){
				 insertCollectorAlert(event,amm,stm); 
			 }else{
			 List<String> list = new ArrayList<String>();
		            	list.add(event);
		                GlobalConfig.logQueue.add(list.subList(0, list.size()));
		                GlobalConfig.logDroolsQueue.addAll(GlobalConfig.logQueue) ;
    	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    	
    	
    	
    	
    }
  private static void insertCollectorAlert(String xmlDoc,final AlertMessageSerive amm,final SettingCollectorService stm){
	  //创建一个新的字符串
      StringReader read = new StringReader(xmlDoc);
      //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
      InputSource source = new InputSource(read);
      //创建一个新的SAXBuilder
      SAXBuilder sb = new SAXBuilder();
      AlertMessage alertMessage = new AlertMessage();
      try {
          //通过输入源构造一个Document
          Document doc = sb.build(source);
          //取的根元素
          Element root = doc.getRootElement();
          String priority = root.getChildText("Priority");
          String name = root.getChildText("name");
          String time = root.getChildText("occurTime");
          String mac = root.getChildText("mac");
          String ip = root.getChildText("IP");
          String macName = stm.selectMac(mac).get(0).getCollectorBasic();
          long ips = alertMessage.lIpTransitionStrIpRevert(ip);
          if("0".equals(priority)){
          alertMessage.setAlertRank(5);
          alertMessage.setAlertEventName(name+"停止运行");
          }else{
          alertMessage.setAlertEventName(name+"超负载运行");
          }
          if("1".equals(priority)){
          	alertMessage.setAlertRank(4);
          }
          if("2".equals(priority)){
          	alertMessage.setAlertRank(3);
          }
			alertMessage.setSendMode("邮件提示,平台提示,短信提示,snmp,trap/syslog提示");
			 alertMessage.setAlertEventType("120024");
			 alertMessage.setAlertCreateDatetime(Long.parseLong(time));
			 alertMessage.setEventsSourceAdd(ips);
			 alertMessage.setEventsTargetAdd(ips);
			 alertMessage.setAlertAssetName(macName);
			 alertMessage.setAlertDeviceName("采集器："+macName);
			 amm.insertAlertMessage(alertMessage);
         
         
      } catch (JDOMException e) {
          e.printStackTrace();
      }
  }  
    
    
    
    //   GlobalConfig.socRuleEngine.initEngine(relevanceRuleGroups);
      
       //设置规则引擎需要的全局变量
    //   GlobalConfig.socRuleEngine.setGlobalParamList(GlobalConfig.ruleNameList);
     //  GlobalConfig.test.setGlobalParamList(GlobalConfig.ruleNameList);
       
       
   
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
  
       
}