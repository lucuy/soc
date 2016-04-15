package com.soc.model.systemsetting;

import java.util.Date;

/**
 * 
 * <归档>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-12-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettingArchive
{
    private long archiveId;             //归档ID
    
    private String archiveName;           //归档的名字
    
    private long archiveStatus;         //归档的状态
    
    private long archiveDate;    //日期
    
    private String archivePath;//归档目录
    private long capacity; //本次的容量 
    private long totalCapacity;//总共的容量
    private float percent;//使用的百分比
    private long thresholds;//告警的临界值
    private long alarmMode;//告警方式 0 邮件 1 短信 2 电话
    
    public long getArchiveId()
    {
        return archiveId;
    }

    public void setArchiveId(long archiveId)
    {
        this.archiveId = archiveId;
    }

    public String getArchiveName()
    {
        return archiveName;
    }

    public void setArchiveName(String archiveName)
    {
        this.archiveName = archiveName;
    }

    public long getArchiveStatus()
    {
        return archiveStatus;
    }

    public void setArchiveStatus(long archiveStatus)
    {
        this.archiveStatus = archiveStatus;
    }

    public long getArchiveDate()
    {
        return archiveDate;
    }

    public void setArchiveDate(long archiveDate)
    {
        this.archiveDate = archiveDate;
    }

	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String archivePath) {
		this.archivePath = archivePath;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public long getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(long totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public long getThresholds() {
		return thresholds;
	}

	public void setThresholds(long thresholds) {
		this.thresholds = thresholds;
	}

	public long getAlarmMode() {
		return alarmMode;
	}

	public void setAlarmMode(long alarmMode) {
		this.alarmMode = alarmMode;
	}

}