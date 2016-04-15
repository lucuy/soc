package com.soc.model.knowledge;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <安全公告模块实体类> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Security implements Serializable {

	// id
	private long securityId;

	// 标题
	private String securityTitle;

	// 内容
	private String securityDetails;

	// 来源
	private String source;

	// 日期
	private Date securityDate;

	// 创建日期
	private Date securityCreateDate;
	
	// 发布人
	private String publisher;

	public Security() {

	}

	public long getSecurityId() {
		return securityId;
	}

	public void setSecurityId(long securityId) {
		this.securityId = securityId;
	}

	public String getSecurityTitle() {
		return securityTitle;
	}

	public void setSecurityTitle(String securityTitle) {
		this.securityTitle = securityTitle;
	}

	public String getSecurityDetails() {
		return securityDetails;
	}

	public void setSecurityDetails(String securityDetails) {
		this.securityDetails = securityDetails;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getSecurityDate() {

		return securityDate;

	}

	public void setSecurityDate(Date securityDate) {
		this.securityDate = securityDate;
	}

	@Override
	public String toString() {
		return "Security [securityId=" + securityId + ", securityTitle="
				+ securityTitle + ", securityDetails=" + securityDetails
				+ ", source=" + source + ", securityDate=" + securityDate
				+ ", publisher=" + publisher + "]";
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getSecurityCreateDate() {
		return securityCreateDate;
	}

	public void setSecurityCreateDate(Date securityCreateDate) {
		this.securityCreateDate = securityCreateDate;
	}
	
	

}
