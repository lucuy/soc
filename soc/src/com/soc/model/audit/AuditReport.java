package com.soc.model.audit;

/**
 * 
 * <外部审计报表的实体类> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-5]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AuditReport {
	
	// 外部审计报表id
	private long auditReportId;
	
	// 外部审计报表name
	private String auditReportName;
	
	// 外部审计报表parengId
	private long auditReportParentId;

	public long getAuditReportId() {
		return auditReportId;
	}

	public void setAuditReportId(long auditReportId) {
		this.auditReportId = auditReportId;
	}

	public String getAuditReportName() {
		return auditReportName;
	}

	public void setAuditReportName(String auditReportName) {
		this.auditReportName = auditReportName;
	}

	public long getAuditReportParentId() {
		return auditReportParentId;
	}

	public void setAuditReportParentId(long auditReportParentId) {
		this.auditReportParentId = auditReportParentId;
	}

	@Override
	public String toString() {
		return "AuditReport [auditReportId=" + auditReportId
				+ ", auditReportName=" + auditReportName
				+ ", auditReportParentId=" + auditReportParentId + "]";
	}

	public AuditReport() {
		super();
		// TODO Auto-generated constructor stub
	}

}
