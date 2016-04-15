package com.soc.webapp.action.systemsetting;

import global.GlobalThreadPool;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.SettingArchive;
import com.soc.model.user.User;
import com.soc.service.systemsetting.SettingArchiveService;
import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;
import com.soc.webapp.listener.CommunicationContextListener;
import com.util.Base64;
import com.util.DES;
import com.util.SendEmail;
import com.util.StringUtil;

/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingArchiveAutoAction extends BaseAction
{

    private static final long serialVersionUID = 1L;
    private SettingArchiveService archiveManager;
    private SettingArchive archive;
    private String archiveAutoTime;//归档时间
    
    private SettingService settingManager;
    
    public void archiveAuto(){
        archiveAutoTime = settingManager.queryByKey("archiveAutoTime");
        if(archiveAutoTime!=null){
        if(Integer.valueOf(archiveAutoTime)<1){
            
        }else{
            long archiveNo = Integer.valueOf(archiveAutoTime)*30*24*60*60*1000l;
            Map<String,Long> archiveMap = new HashMap<String,Long>();
            archiveMap.put("start", new Date().getTime()-archiveNo);
            archiveMap.put("end", new Date().getTime()-24*60*60*1000l);
            List<SettingArchive> selectArchive = archiveManager.selectArchiveAuto(archiveMap);
            archiveManager.archiveAuto(selectArchive);
        }
        }
    }

    public SettingArchiveService getArchiveManager()
    {
        return archiveManager;
    }

    public void setArchiveManager(SettingArchiveService archiveManager)
    {
        this.archiveManager = archiveManager;
    }

    public SettingArchive getArchive()
    {
        return archive;
    }

    public void setArchive(SettingArchive archive)
    {
        this.archive = archive;
    }

    public SettingService getSettingManager()
    {
        return settingManager;
    }

    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }
    
}