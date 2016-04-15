package com.soc.model.workorder;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <工单管理实体类>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WorkOrder implements Serializable
{
    
    //工单Id(工单编号)
    private int workOrderId;
    
    //工单名称
    private String workOrderName;
    
    //紧急程度
    private int exigencyLevel;
    
    //工单描述
    private String workOrderDescribe;
    
    //工单产生时间 
    private Date produceDate;
    
    //工单预计完成时间
    private Date predictDate;
    
    //工单实际完成时间
    private Date realityDate;
    
    //工单发起者
    private String workOrderStart;
    
    //工单操作者
    private String workOrderHandle;
    
    //工单审核者
    private String workOrderAudit;
    
    //工单状态
    private int status;
    
    //备注(如果未通过，写明原因在里面)
    private String remark;
    
    //关联的事件ID
    private Integer alarmID;
    private int sendMail;
    private String closeReason;//关闭告警原因
    

	public String getCloseReason() {
		return closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public int getSendMail() {
		return sendMail;
	}

	public void setSendMail(int sendMail) {
		this.sendMail = sendMail;
	}

	public Integer getAlarmID() {
		return alarmID;
	}

	public void setAlarmID(Integer alarmID) {
		this.alarmID = alarmID;
	}

	public int getWorkOrderId()
    {
        return workOrderId;
    }
    
    public void setWorkOrderId(int workOrderId)
    {
        this.workOrderId = workOrderId;
    }
    
    public String getWorkOrderName()
    {
        return workOrderName;
    }
    
    public void setWorkOrderName(String workOrderName)
    {
        this.workOrderName = workOrderName;
    }
    
    public int getExigencyLevel()
    {
        return exigencyLevel;
    }
    
    public void setExigencyLevel(int exigencyLevel)
    {
        this.exigencyLevel = exigencyLevel;
    }
    
    public String getWorkOrderDescribe()
    {
        return workOrderDescribe;
    }
    
    public void setWorkOrderDescribe(String workOrderDescribe)
    {
        this.workOrderDescribe = workOrderDescribe;
    }
    
    public Date getProduceDate()
    {
        return produceDate;
    }
    
    public void setProduceDate(Date produceDate)
    {
        this.produceDate = produceDate;
    }
    
    public Date getPredictDate()
    {
        return predictDate;
    }
    
    public void setPredictDate(Date predictDate)
    {
        this.predictDate = predictDate;
    }
    
    public Date getRealityDate()
    {
        return realityDate;
    }
    
    public void setRealityDate(Date realityDate)
    {
        this.realityDate = realityDate;
    }
    
    public String getWorkOrderStart()
    {
        return workOrderStart;
    }
    
    public void setWorkOrderStart(String workOrderStart)
    {
        this.workOrderStart = workOrderStart;
    }
    
    public String getWorkOrderHandle()
    {
        return workOrderHandle;
    }
    
    public void setWorkOrderHandle(String workOrderHandle)
    {
        this.workOrderHandle = workOrderHandle;
    }
    
    public String getWorkOrderAudit()
    {
        return workOrderAudit;
    }
    
    public void setWorkOrderAudit(String workOrderAudit)
    {
        this.workOrderAudit = workOrderAudit;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
}
