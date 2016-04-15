package com.soc.model.knowledge;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.soc.model.user.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.util.DateUtil;

/**
 * 消息中心实体类
 * @author lichen
 *
 */
public class MessageCenter {
	
	//消息Id
	private long messageId;
	//消息标题
	private String messageTitle;
	//消息类型
	private long messageType;
	//消息内容
	private String messageContent;
	//消息创建时间
	private Date messageCreateDate;
	//消息最后操作时间
	private Date messageOperateDate;
	//消息是否阅读
	private long messageRead;
	//消息创建人Id
	private long messageSendUserId;
	//消息处理人Id
	private long messageDealUserId;
	//消息处理意见
	private String messageDealIdea;
	//消息删除状态
	private long messageDelStatus;
	//发送人对象
	private User sendUser;
	//处理人对象
	private User dealUser;
	
	private String createDate;
	private String operateDate;
	
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public long getMessageType() {
		return messageType;
	}
	public void setMessageType(long messageType) {
		this.messageType = messageType;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Date getMessageCreateDate() {
		return messageCreateDate;
	}
	public void setMessageCreateDate(Date messageCreateDate) {
		this.messageCreateDate = messageCreateDate;
	}
	public Date getMessageOperateDate() {
		return messageOperateDate;
	}
	public void setMessageOperateDate(Date messageOperateDate) {
		this.messageOperateDate = messageOperateDate;
	}
	public long getMessageRead() {
		return messageRead;
	}
	public void setMessageRead(long messageRead) {
		this.messageRead = messageRead;
	}
	public long getMessageSendUserId() {
		return messageSendUserId;
	}
	public void setMessageSendUserId(long messageSendUserId) {
		this.messageSendUserId = messageSendUserId;
	}
	public long getMessageDealUserId() {
		return messageDealUserId;
	}
	public void setMessageDealUserId(long messageDealUserId) {
		this.messageDealUserId = messageDealUserId;
	}
	public String getMessageDealIdea() {
		return messageDealIdea;
	}
	public void setMessageDealIdea(String messageDealIdea) {
		this.messageDealIdea = messageDealIdea;
	}
	public long getMessageDelStatus() {
		return messageDelStatus;
	}
	public void setMessageDelStatus(long messageDelStatus) {
		this.messageDelStatus = messageDelStatus;
	}
	public User getSendUser() {
		return sendUser;
	}
	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}
	public User getDealUser() {
		return dealUser;
	}
	public void setDealUser(User dealUser) {
		this.dealUser = dealUser;
	}
	public String getCreateDate() {
		return DateUtil.formatDate(messageCreateDate, "yyyy-MM-dd hh:mm:ss");
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getOperateDate() {
		return DateUtil.formatDate(messageOperateDate, "yyyy-MM-dd hh:mm:ss");
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	
	
	
}
