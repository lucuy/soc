package com.soc.model.audit;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <审计实体类>
 * <功能详细描述>
 * 
 * @author  shenhaiguang
 * @version  [版本号, 2012-8-13]
 * @see  [相关类/方法]
 * @since  [socv3.6]
 */
public class Audit implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    // 审计ID
    private long auditId;
    
    // 账户ID
    private long auditUserId;
    
    // 操作者
    private String auditUserLoginName;
    
    // 操作内容
    private String auditOperationProcedure;
    
    // 操作时间
    private Date auditOperationTime;
    
    // 操作IP
    private String auditOperationIp;
    
    // 操作的具体内容
    private String auditInformation;
    
    public long getAuditId()
    {
        return auditId;
    }
    
    public void setAuditId(long auditId)
    {
        this.auditId = auditId;
    }
    
    public long getAuditUserId()
    {
        return auditUserId;
    }
    
    public void setAuditUserId(long auditUserId)
    {
        this.auditUserId = auditUserId;
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
    
    public Date getAuditOperationTime()
    {
        return auditOperationTime;
    }
    
    public void setAuditOperationTime(Date auditOperationTime)
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
    
}
