package com.soc.webapp.action.policy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;

import com.push.services.SendMessage;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.policy.PasswordPolicyService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <密码策略Action> <对密码策略的相关操作：查询密码策略、修改密码策略、增加密码策略、删除密码策略>
 * 
 * @author 曹理冬
 * @version [V100R001C001]
 * @see [相关类/方法]
 * @since [soc v3.6.0.1]
 */
public class PasswordPolicyAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;

	// 存储密码策略信息
	private PasswordPolicy passwordPolicy;

	// 密码策略名称 ( 去重)

	private String passwordPolicyName;

	// 存储密码ID
	private long passwordPolicyId;

	// 存储密码策略信息List
	private List<PasswordPolicy> passwordPolicyList;

	// 存储密码策略信息状态(0:锁定 | 1:激活 )
	private int passwordPolicyStatus;

	// 关键字
	private String keyword;

	// 批量操作时复选框的值
	private String ids;

	// 高级查询密码策略名
	private String selPasswordPolicyName;

	// 高级查询密码长度
	private String selPasswordPolicyPasswordLength;

	// 密码策略服务管理类
	private PasswordPolicyService passwordPolicyManager;

	// 内部审计服务类
	private AuditService auditManager;
	
	 //请求的action字符串
    private String actionStr ="query.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ; 
    
    //用来保存与修改策略相关联的用户
    private Map<String, Object> sessionMap;
    
    //推送消息类
    private SendMessage msg;

    //用户集合
    private List<User> userList;
    
    //用户业务类
    private UserService userManager;
	/**
	 * <查询所有密码策略> <功能详细描述>
	 * 
	 * @return String
	 * @see [Page,UserService]
	 */
	public String query() {

		// 打印日志信息
		log.info("query passwordPolicy list...");
		HttpServletRequest request = super.getRequest();

		Page page = null;
		SearchResult<PasswordPolicy> sr = null;

		// 处理数据分页的起始条数
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

		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		if (StringUtil.isNotBlank(selPasswordPolicyName)) {
			map.put("selpasswordPolicyName", selPasswordPolicyName);
		}
		if (StringUtil.isNotBlank(selPasswordPolicyPasswordLength)) {
			map.put("selpasswordPolicyPasswordLength",
					Long.valueOf(selPasswordPolicyPasswordLength));
		}

		// 设置密码策略显示
		map.put("display", 0);

		// 根据map中存储的查询条件，查找符合条件的密码策略列表
		sr = passwordPolicyManager.query(map, page);
		passwordPolicyList = (List<PasswordPolicy>) sr.getList();

		request.setAttribute("Page", sr.getPage());

		return SUCCESS;
	}

	/**
	 * <编辑密码策略>
	 * 
	 * @return String
	 * @see []
	 */
	public String edit() {
		log.info("[PasswordPolicyAction] enter editPasswordPolicy...");

		passwordPolicy = passwordPolicyManager.queryById(passwordPolicyId);
		return SUCCESS;
	}

	/**
	 * <新增或者修改密码策略> <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String update() {
		log.info("[PasswordPolicyAction] enter updatePasswordPolicy...");
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(passwordPolicy.getPasswordPolicyName() + "("
				+ passwordPolicy.getPasswordPolicyName() + ")");
		// 判断时间对象Id是否存在,不存在将用户的登录名赋值给操作用户名

		if (passwordPolicy.getPasswordPolicyId() != 0) {
			// 日志审计
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "密码策略", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :更新密码策略";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改密码策略");

		} else {

			// 审计日志
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "密码策略", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :添加密码策略";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增地址策略");
		}
		//如果前台页面的最小密码长度是默认值，在此为其赋值为6
		if(passwordPolicy.getPasswordPolicyPasswordLength()==0){
			passwordPolicy.setPasswordPolicyPasswordLength(6);
		}
		// 判断时间对象Id是否存在,不存在将用户的登录名赋值给操作用户名
		passwordPolicyManager.update(passwordPolicy);
		userList = passwordPolicyManager.queryUserByPasswordPolicyId(passwordPolicy.getPasswordPolicyId());
		if (userList!=null && userList.size()>0) {
			for (User user : userList) {
				sessionMap.put("userinfo", user.getUserLoginName());
				msg.sendMessageAuto(user.getUserLoginName(), "密码策略已经修改，请及时下线修改密码！");
				user.setUserChangePassword(1);
				user.setUserUpdateDateTime(new Date());
				userManager.updateUserPassWordPolicy(user);
			}
		}
		return SUCCESS;
	}

	/**
	 * <删除密码策略> <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String deletepasswordPolicy() {
		LOG.info("[PasswordPolicyAction] enter method deletePasswordPolicy() ...");
		List<String> fieldList = new ArrayList<String>();
		// 针对多个策略操作
		if (ids.indexOf(",") > 0) {
			String[] checked = ids.split(",");

			// 循环遍历需要执行更新状态的策略ID并执行更新状态操作
			for (String checkid : checked) {
				PasswordPolicy passwordObject = passwordPolicyManager
						.queryById(Long.parseLong(checkid));
				fieldList.add(passwordObject.getPasswordPolicyName());
				passwordPolicyManager.deletePasswordPolicy(Long
						.parseLong(checkid));
			}
		}
		// 针对单个策略操作
		else {
			PasswordPolicy passwordObject = passwordPolicyManager
					.queryById(Long.parseLong(ids));
			fieldList.add(passwordObject.getPasswordPolicyName());
			passwordPolicyManager.deletePasswordPolicy(Long.parseLong(ids));
		}

		// 审计日志
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "密码策略", super
				.getRequest().getRemoteAddr(), fieldList);

		/*String logString = "";
		logString = "登录名:"
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + " 源IP:"
				+ getRequest().getRemoteAddr() + " 操作时间:"
				+ DateUtil.curDateTimeStr19() + " 操作类型 :删除密码策略";

		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除地址策略");
		return SUCCESS;
	}

	/**
	 * <更新密码策略状态> <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String updateStatus() {
		log.info("[PasswordPolicyAcrion]Enter method timePolicy updateStatus");
		List<String> fieldList = new ArrayList<String>();
		if (StringUtil.isNotBlank(ids)) {

			// 操作多条记录
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				
				// 循环遍历需要更新状态的时间策略
				for (String checkid : checked) {
					PasswordPolicy passwordObject = passwordPolicyManager.queryById(Long.parseLong(checkid));
					fieldList.add(passwordObject.getPasswordPolicyName());
					if (passwordObject.getPasswordPolicyStatus() != passwordPolicyStatus) {
						passwordPolicyManager.updatePasswordPolicyStatus(passwordObject.getPasswordPolicyId(),passwordPolicyStatus);
					}else {
						userList = passwordPolicyManager.queryUserByPasswordPolicyId(Integer.parseInt(checkid));
						if (userList!=null && userList.size()>0) {
							for (User user : userList) {
								sessionMap.put("userinfo", user.getUserLoginName());
								msg.sendMessageAuto(user.getUserLoginName(), "密码策略已经激活，请及时下线修改密码！");
							}
						}
					}
					
				}
			}
			// 操作一条记录
			else {
				PasswordPolicy passwordObject1 = passwordPolicyManager
						.queryById(Long.parseLong(ids));
				fieldList.add(passwordObject1.getPasswordPolicyName());
				if (passwordObject1.getPasswordPolicyStatus() != passwordPolicyStatus) {
					passwordPolicyManager.updatePasswordPolicyStatus(
							passwordObject1.getPasswordPolicyId(),
							passwordPolicyStatus);
				}else {
					userList = passwordPolicyManager.queryUserByPasswordPolicyId(Integer.parseInt(ids));
					if (userList!=null && userList.size()>0) {
						for (User user : userList) {
							sessionMap.put("userinfo", user.getUserLoginName());
							msg.sendMessageAuto(user.getUserLoginName(), "密码策略已经激活，请及时下线修改密码！");
						}
					}
				}
				
				
			}
			
			// 审计日志
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "密码策略", super
					.getRequest().getRemoteAddr(), fieldList);

			/*String logString = "";
			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :更改密码策略状态";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改密码策略状态");
		}
		return SUCCESS;
	}

	/**
	 * 检测密码策略名称是否被使用 根据策略名称查找策略,如果存在向页面返回"flag=true",不存在向页面返回"flag=false"
	 * 
	 * @return
	 * @see
	 */
	public void checkpasswordPolicyName() {
		LOG.info("[PasswordPolicyAction] enter method checkpasswordPolicyName() ...");

		// 标识此策略名是否存在
		String flag = "false";

		if (StringUtil.isNotBlank("passwordPolicyName")) {
			List<PasswordPolicy> list = passwordPolicyManager
					.queryByPasswordPolicyName(passwordPolicyName);
			// 查找到策略，将flag赋值为true
			if (list.size() > 0) {
				flag = "true";
			}
		}
		// 将flag返回给页面
		try {
			getResponse().getWriter().write(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 排序
	 * @return
	 * @see
	 */
	public String sort(){
		LOG.info("[PasswordPolicyAcrion] enter method sort() ...");
        HttpServletRequest request = super.getRequest();
        
        Page page = null;
        SearchResult sr = null;
        
        HttpSession session = this.getSession() ; 
        int changeNum=0;  
        changeNum = session.getAttribute("CHANGENUM")==null ? 1:(Integer)session.getAttribute("CHANGENUM");
        
     // 处理数据分页的起始条数
        String startIndex = request.getParameter("startIndex");
        
        if (StringUtil.isNotBlank(startIndex))
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
        }
        else
        {
            page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
        }
        
        if(field != ""){
            Map<String,String> map=new HashMap<String, String>();
            int num = changeNum%2;
            
            if(num==0){
                map.put("sortType", "DESC");
            }else{
                map.put("sortType", "ASC") ; 
            }
            if(sortType != null){
                map.put("sortType", sortType);
            }
            map.put("field", field);
            
            actionStr = "field="+field+"&sortType="+map.get("sortType");
            
            sr = passwordPolicyManager.sort(map, page);
            if (sr != null)
            {
            	passwordPolicyList = sr.getList();
                //request.setAttribute("associationList", associationList);
                //request.setAttribute("auditList", vulList);
                request.setAttribute("Page", sr.getPage());
            }
            else
            {
                request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
            }
        }
        changeNum++ ; 
        session.setAttribute("CHANGENUM", changeNum);
        return SUCCESS ; 
	}
	

	public PasswordPolicy getPasswordPolicy() {
		return passwordPolicy;
	}

	public void setPasswordPolicy(PasswordPolicy passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}

	public long getPasswordPolicyId() {
		return passwordPolicyId;
	}

	public void setPasswordPolicyId(long passwordPolicyId) {
		this.passwordPolicyId = passwordPolicyId;
	}

	public List<PasswordPolicy> getPasswordPolicyList() {
		return passwordPolicyList;
	}

	public void setPasswordPolicyList(List<PasswordPolicy> passwordPolicyList) {
		this.passwordPolicyList = passwordPolicyList;
	}

	public int getPasswordPolicyStatus() {
		return passwordPolicyStatus;
	}

	public void setPasswordPolicyStatus(int passwordPolicyStatus) {
		this.passwordPolicyStatus = passwordPolicyStatus;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public PasswordPolicyService getPasswordPolicyManager() {
		return passwordPolicyManager;
	}

	public void setPasswordPolicyManager(
			PasswordPolicyService passwordPolicyManager) {
		this.passwordPolicyManager = passwordPolicyManager;
	}

	public String getSelPasswordPolicyName() {
		return selPasswordPolicyName;
	}

	public void setSelPasswordPolicyName(String selPasswordPolicyName) {
		this.selPasswordPolicyName = selPasswordPolicyName;
	}

	public String getSelPasswordPolicyPasswordLength() {
		return selPasswordPolicyPasswordLength;
	}

	public void setSelPasswordPolicyPasswordLength(
			String selPasswordPolicyPasswordLength) {
		this.selPasswordPolicyPasswordLength = selPasswordPolicyPasswordLength;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPasswordPolicyName() {
		return passwordPolicyName;
	}

	public void setPasswordPolicyName(String passwordPolicyName) {
		this.passwordPolicyName = passwordPolicyName;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getActionStr() {
		return actionStr;
	}

	public void setActionStr(String actionStr) {
		this.actionStr = actionStr;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}
	
	

}
