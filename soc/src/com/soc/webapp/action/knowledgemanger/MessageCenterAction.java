package com.soc.webapp.action.knowledgemanger;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.push.services.SendMessage;
import com.soc.model.knowledge.MessageCenter;
import com.soc.model.user.User;
import com.soc.service.knowledge.MessageCenterService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 消息中心查看消息，处理消息，删除消息，查看已删除消息
 * 
 * @author lichen
 *
 */
public class MessageCenterAction extends BaseAction implements SessionAware{

	//所要查询的消息状态
	private long dualstatus;
	//消息集合
	private List<MessageCenter> messagelist;
	//消息中心管理类
	private MessageCenterService messageManager;
	//获得删除消息的id字符串
	private String ids;
	//消息中心对象
	private MessageCenter message;
	//根据id查询对象
	private int id;
	//消息批复内容
	private String messageDeadIdea;
	//快速查询所用的条件
	private String keyword;
	//高级查询的标题
	private String selmessageTitle;
	//高级查询     处理
	private String selmessageIdea;
	//高级查询消息类型
	private String selmessageType;
	//高级查询阅读状态
	private String selmessageRead;
	
	//数据推送类
	private SendMessage msg;
	
	private Map<String , Object> sessionMap;
	
	private String sendUserloginName;
	
	/**
	 * 根据不同条件查询消息中心的数据
	 * @return
	 */
	public String queryMessage(){
		LOG.info("[MessageAction] enter method queryMessage() ...");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dualstatus", dualstatus);
		
		// 对分页的处理
		Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult sr = null;

		String startIndex = request.getParameter("startIndex");
		try {
			if (StringUtil.isNotBlank(startIndex)) {
				if (keyword==null || keyword.equals("")) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				}else {
					if (Page.getKeyword().equals(keyword)) {
						page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
						Page.setKeyword(keyword);
					}else{
						page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
						Page.setKeyword(keyword);
					}
				}
			} else {
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				Page.setKeyword(keyword);
			}			
		} catch (Exception e) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			Page.setKeyword(keyword);
		}
		
		if (StringUtil.isNotBlank(keyword)) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			map.put("keyword", keyword.trim());
			
		}
		if (StringUtil.isNotBlank(selmessageTitle)) {
			map.put("messageTitle", selmessageTitle.trim());
		}
		if (StringUtil.isNotBlank(selmessageIdea)) {
			map.put("messageIdea", selmessageIdea.trim());
		}
		if (StringUtil.isNotBlank(selmessageType) && Integer.parseInt(selmessageType)!=-1) {
			map.put("messageType", Integer.parseInt(selmessageType.trim()));
		}
		if (StringUtil.isNotBlank(selmessageRead) && Integer.parseInt(selmessageRead)!=-1 ) {
			map.put("messageRead", Integer.parseInt(selmessageRead.trim()));
		}
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("SOC_LOGON_USER");
		if (user!=null) {
			map.put("onlineUserId", user.getUserId());
		}
		
		// 根据前台获得的条件来调用不同的查询方法
		sr = messageManager.queryMessageCenters(map, page);
		
		if (sr != null) {
			messagelist = (List<MessageCenter>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		}
		return "success";
	}

	/**
	 *根据所要查询的消息状态判断是要删除数据（修改状态）还是彻底删除数据 
	 * @return
	 * 
	 */
	public String updateMessageStatus(){
		//如果dualstatus等于0  修改消息的删除状态
		if (dualstatus==0) {
			LOG.info("[MessageAction] enter method updateMessageStatus() ...updateMessageStatus");
			if (StringUtil.isNotBlank(ids)) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (ids.indexOf(",")>0) {
					String [] checked = ids.split(",");
					for (String checkedid : checked) {
						map.put("messageId", Integer.parseInt(checkedid));
						map.put("operateDate", new Date());
						messageManager.updMessageStatus(map);
					}
				}else {
					map.put("messageId", Integer.parseInt(ids));
					map.put("operateDate", new Date());
					messageManager.updMessageStatus(map);
				}
				
			}
			// syslog日志输出
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:删除消息中心数据";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除消息中心数据");
		}else { 
			LOG.info("[MessageAction] enter method updateMessageStatus() ...deleteMessageStatus");
			if (StringUtil.isNotBlank(ids)) {
				if (ids.indexOf(",")>0) {
					String [] checked = ids.split(",");
					for (String checkedid : checked) {
						messageManager.delMessageById(Integer.parseInt(checkedid));
					}
				}else {
					messageManager.delMessageById(Integer.parseInt(ids));
				}
				
			}
			// syslog日志输出
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除消息中西数据");
			
		}
	
		
		return "success";
	}
	/**
	 * 查看单个消息对象，如果此消息为未读消息，修改为已读
	 * @return
	 */
	public String queryMessageById(){
		message = messageManager.queryMessageCenterById(id);
		if (message!=null && message.getMessageRead()==0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("operateDate", new Date());
			map.put("messageId", message.getMessageId());
			messageManager.updateMessageRead(map);
		}
		return "success";
	}
	
	public String updateMessage(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (messageDeadIdea!=null && !messageDeadIdea.equals("")) {
			map.put("messageDeadIdea", messageDeadIdea);
			map.put("messageId", id);
		}
		messageManager.updateMessage(map);
		sessionMap.put("userinfo", sendUserloginName);
		msg.sendMessageAuto(sendUserloginName, "-1");
		return "success";
	}

	public long getDualstatus() {
		return dualstatus;
	}

	public void setDualstatus(long dualstatus) {
		this.dualstatus = dualstatus;
	}

	public List<MessageCenter> getMessagelist() {
		return messagelist;
	}

	public void setMessagelist(List<MessageCenter> messagelist) {
		this.messagelist = messagelist;
	}
	public MessageCenterService getMessageManager() {
		return messageManager;
	}

	public void setMessageManager(MessageCenterService messageManager) {
		this.messageManager = messageManager;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public MessageCenter getMessage() {
		return message;
	}

	public void setMessage(MessageCenter message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessageDeadIdea() {
		return messageDeadIdea;
	}

	public void setMessageDeadIdea(String messageDeadIdea) {
		this.messageDeadIdea = messageDeadIdea;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSelmessageTitle() {
		return selmessageTitle;
	}

	public void setSelmessageTitle(String selmessageTitle) {
		this.selmessageTitle = selmessageTitle;
	}

	public String getSelmessageIdea() {
		return selmessageIdea;
	}

	public void setSelmessageIdea(String selmessageIdea) {
		this.selmessageIdea = selmessageIdea;
	}

	public String getSelmessageType() {
		return selmessageType;
	}

	public void setSelmessageType(String selmessageType) {
		this.selmessageType = selmessageType;
	}

	public String getSelmessageRead() {
		return selmessageRead;
	}

	public void setSelmessageRead(String selmessageRead) {
		this.selmessageRead = selmessageRead;
	}

	public SendMessage getMsg() {
		return msg;
	}

	public void setMsg(SendMessage msg) {
		this.msg = msg;
	}

	@Override
	public void setSession(Map arg0) {
		this.sessionMap = arg0;
	}

	public String getSendUserloginName() {
		return sendUserloginName;
	}

	public void setSendUserloginName(String sendUserloginName) {
		this.sendUserloginName = sendUserloginName;
	}


	
	
	
}
