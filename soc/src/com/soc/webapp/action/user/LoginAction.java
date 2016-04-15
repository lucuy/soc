package com.soc.webapp.action.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;



import com.google.authenticator.GoogleAuthenticator;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.role.Permission;
import com.soc.model.role.Role;
import com.soc.model.user.OnlineUser;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.user.LoginService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.soc.webapp.filter.RoleFilter;
import com.util.DateUtil;
import com.util.SessionContext;
import com.util.StringUtil;

/**
 * 
 * Description: 登录Action 登录系统
 * 
 * @author 王亚男
 * @version V100R001C001
 * @see [相关类/方法]
 * @since soc v3.6.0.1
 */
/**
 * @author Administrator
 *
 */
public class LoginAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;

	// 登录的服务类
	private LoginService loginManager;

	// 用户服务类
	private UserService userManager;

	// 系统设置业务类
	private SettingService settingManger;

	// 用户实体类
	private User user;

	// 登录名
	private String loginName;
	// 密码
	private String savedSecret;

	// 密码
	private String password;

	// 验证码
	private String imgCode;

	// 新密码
	private String newPassword;

	// 显示 /隐藏
	private Integer display = 0;
	
	// 显示 /隐藏
	private Integer checkstyle = 0;
	
	// 管理员登录用户名
	private String loginName2;

	// 管理员登录密码
	private String password2;

	// 管理员登录验证码
	private String imgCode2;

	private AuditService auditManager;

	private Map<String, Object> sessionMap;

	private String loginType = "false";
	// 拓扑过来的登录标示 1 是 其他 不是
	private int networkTopologyLogin;
	// 测评中心需要 admin用户输入密码错误 锁定一分钟
	private static long adminLockStartTime;
	
	/**
	 * 单一用户在线判断
	 * 
	 * @param
	 * @return void
	 */
	public void checkSingle() {
		log.info("[LoginAction] enter checkSingle()");

		long userId = 0;

		String userIp = "";

		// 根据登录名查询当前执行登录操作的用户
		List<User> userList1 = userManager.queryByUserLoginName(loginName);

		// 判断查询到的用户是否在线
		if (userList1.size() > 0) {
			List<OnlineUser> userList = (List<OnlineUser>) super
					.getServletContext().getAttribute("SOC_ONLINE_USERLIST");

			if (userList != null) {
				for (OnlineUser onlineUser : userList) {
					if (onlineUser.getUserId() == userList1.get(0).getUserId()
							&& onlineUser.getFlag() == 1) {
						userId = onlineUser.getUserId();
						userIp = onlineUser.getUserIp();

						break;
					}
				}
			}
		}
		// 返回IP地址代表此用户在线，返回空字符串代表不在线
		try {
			if (userId != 0) {

				getResponse().getWriter().write(userIp);
			} else {
				getResponse().getWriter().write("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户登入
	 * 
	 * @param
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String check() {
		log.info("[LoginAction] enter check.....");

		List<String> fieldList = new ArrayList<String>();

		HttpServletRequest request = super.getRequest();

		HttpSession session = super.getSession();

		// 得到session中保存的验证码
		/*
		 * String relCode = "";
		 * 
		 * Object ob = session.getAttribute("rand");
		 * 
		 * if (ob != null) { relCode = ob.toString(); }
		 */

		/**
		 * 先判断时间 时间不符合直接返回
		 */
		/*
		 * if ("admin".equals(this.loginName)&&System.currentTimeMillis()-
		 * adminLockStartTime<60000) {
		 * super.addActionMessage("用户锁定,剩余时间"+(60-(System
		 * .currentTimeMillis()-adminLockStartTime)/1000)+"秒");
		 * fieldList.add("admin( admin )");
		 * 
		 * auditManager.insertByLoginOperator(38, "admin锁定",super
		 * .getRequest().getRemoteAddr(), fieldList); return INPUT; } else {
		 * adminLockStartTime = 0; }
		 */

		// 说明是从拓扑过来的登录 就不判断密码了
		/* if(networkTopologyLogin != 1){ */
		/*
		 * if(loginName==null || password==null||loginName=="" || password=="")
		 * { super.addActionMessage("用户名或者密码错误!");
		 * 
		 * return INPUT; }
		 */
		
		//清除actionMessage中无用的信息
		super.clearMessages();
		

		if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
			return INPUT;
		}
		if (loginName.length() > 50 || password.length() > 22) {
			super.addActionMessage("用户名或者密码错误!");
			return INPUT;
		}
		if (checkName(loginName) || checkName(password)) {
			super.addActionMessage("用户名或者密码错误!");
			return INPUT;
		}
		

		/* } */
		// 校验验证码
		/*
		 * if (StringUtil.isEmpty(imgCode) ||
		 * !imgCode.equalsIgnoreCase(relCode)) {
		 * super.addActionMessage("验证码不正确!");
		 * 
		 * return INPUT; }
		 */
		// System.out.println(loginType);

		/*
		 * if(!loginType.equals("false")) { session.invalidate(); }
		 */

		/*
		 * if (loginType!=null || !loginType.equals(" ")) {
		 * session.invalidate(); }
		 */

		// 将中文转吗成UTF-8
		try {

			loginName = java.net.URLDecoder.decode(loginName, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 说明是从拓扑过来的登录 就不判断密码了
		/*
		 * if(networkTopologyLogin == 1){ // 校验用户名密码 user =
		 * loginManager.networkTopologyLogin(loginName); }else {
		 */
		// 校验用户名密码

		user = loginManager.check(loginName, password,loginType,
				super.getRequest().getRemoteAddr()); /* } */
	
		/**
		 * 如果用户名是admin 密码错误锁定一分钟
		 */
		if ("admin".equals(this.loginName) && user == null) {
			adminLockStartTime = System.currentTimeMillis();// 获取当前时间
		} else {
			adminLockStartTime = 0;
		}

		// 用户不存在，返回登录页面
		if (user == null) {
			super.addActionMessage(loginManager.getActionMessage());
			return INPUT;

			// google验证
		} else {
			if(checkstyle==2){
			String bk_secrtKey = user.getUserSavedSecret();
			//前台输入动态码 未双重验证
			if (StringUtil.isNotBlank(savedSecret)) {
				// 传递的有动态码 -- 需要动态码验证 双验证方式
				if (StringUtil.isNotBlank(bk_secrtKey)) {

					long t = System.currentTimeMillis();
					GoogleAuthenticator ga = new GoogleAuthenticator();
					ga.setWindowSize(5);
					// 前台必须为long类型传递到后台
					boolean r = ga.check_code(bk_secrtKey,
							Long.parseLong(savedSecret), t);
					// 验证失败
					if (!r) {
						super.addActionMessage("动态码验证失败");
						return INPUT;
					}
					// 未传递动态码到后台 -- 单验证方式
				}else{			
					super.addActionMessage("动态码验证失败");
					return INPUT;
				}

			}
			}

		}

		// 用户状态为1，返回登陆页，并提示用户此用户已锁定
		if (user.getUserStatus() == 0) {
			super.addActionMessage("该用户已被锁定");

			fieldList.add(user.getUserLoginName() + "("
					+ user.getUserLoginName() + ")");

			auditManager.insertByLoginOperator(user.getUserId(), "失败", super
					.getRequest().getRemoteAddr(), fieldList);

			return INPUT;
		}

		// 用户状态为2，返回登录页，并提示用户此用户已注销
		/*
		 * else if (user.getUserStatus() == 2) {
		 * super.addActionMessage("该用户已被注销"); return INPUT; }
		 */

		// 其他值为此用户可正常登录并初始化用户信息
		else {
			super.getSession().setAttribute("SOC_LOGON_USER", user);

			super.getSession()
					.setAttribute("userinfo", user.getUserLoginName());

			SessionContext myc = SessionContext.getInstance();

			myc.AddSession(session);
			/*
			 * List<OnlineUser> userList1 =
			 * (List<OnlineUser>)super.getServletContext
			 * ().getAttribute("SOC_ONLINE_USERLIST");
			 */

			// userList1.remove(user);

			// super.getSession().removeAttribute("SOC_LOGON_USER");
			// super.getSession().removeAttribute("userinfo");

			// 登录后初始化工作

			// super.getSession().removeValue(name)
			// 单一登录判断
			synchronized (this) {
				List<OnlineUser> userList = (List<OnlineUser>) super
						.getServletContext()
						.getAttribute("SOC_ONLINE_USERLIST");
				// //System.out.println(userList.size());
				boolean flag = false;
				for (OnlineUser onlineUser : userList) {
					if (onlineUser.getUserId() == user.getUserId()) {
						if (onlineUser.getFlag() == 1)
							onlineUser.setFlag(0);
					}
				}
				OnlineUser newUser = new OnlineUser();
				newUser.setFlag(1);
				newUser.setUserId(user.getUserId());
				newUser.setLoginTime(new Date());
				newUser.setSessionId(session.getId());
				// newUser.setUserIp(user.getUserCreatorIp());
				if (request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")) {
					newUser.setUserIp("127.0.0.1");
				} else {
					newUser.setUserIp(request.getRemoteAddr());
				}
				newUser.setUserLoginName(user.getUserLoginName());

				// 将新登录的用户存入Onlineuserlist内
				userList.add(newUser);

			}

			List<Role> roleList = userManager.queryRelRole(user.getUserId());

			if (roleList.size() > 0) {
				RoleFilter rf = new RoleFilter();

				Role role = rf.limit(roleList, super.getSession());

				super.getSession().setAttribute("SOC_LOGON_ROLE", role);

				// 封装权限id串到session中
				List<Permission> permissionList = role.getPermissionList();

				StringBuffer PermissionBuffer = new StringBuffer();

				for (Permission p : permissionList) {
					PermissionBuffer.append(",");
					PermissionBuffer.append(p.getPermissionId());
					PermissionBuffer.append(",");
				}
				super.getSession().setAttribute("SOC_LOGON_PERMISSIONS",
						PermissionBuffer.toString());

			} else {
				super.addActionMessage("角色未分配，请联系管理员！");

				return INPUT;
			}

			// 下次登录修改密码
			if (user.getUserChangePassword() == 1) {
				getRequest().setAttribute("setSuccess", "请修改密码,再登录");
				request.setAttribute("changPwd", "change");
				return "personalSetting";
			}

			// 验证密码是否过期:
			List<PasswordPolicy> passwordPolicyList = userManager
					.queryRelPasswordPolicy(user.getUserId());

			// 获得上次的策略更新时间
			Date updateTime = user.getUserUpdateDateTime();

			// 最小天数
			int limitDay = Integer.MAX_VALUE;

			if (passwordPolicyList != null && !passwordPolicyList.isEmpty()) {

				for (PasswordPolicy pwdPolicy : passwordPolicyList) {
					// 判断策略是否为激活状态
					if (pwdPolicy.getPasswordPolicyStatus() == 1) {
						// 判断密码的修改周期是否为0
						if (pwdPolicy.getPasswordPolicyPasswordChangeCycle() != 0) {
							int temp = pwdPolicy
									.getPasswordPolicyPasswordChangeCycle();
							if (limitDay > temp) {
								limitDay = temp;
								// updateTime = user.getUserUpdateDateTime();
							}
						}
					}
				}
				if (limitDay != Integer.MAX_VALUE) {
					if ((new Date().getTime() - updateTime.getTime()) > (limitDay * 60000 * 60 * 24)) {
						getRequest().setAttribute("setSuccess", "你的密码已过期请修改密码");
						request.setAttribute("changPwd", "change");
						return "personalSetting";
					}
				}
			}

			// 升级处的代码
			String upgrade = settingManger.queryByKey("upgrade");
			if (upgrade.equals("1")) {
				return "upgrade";
			}

			fieldList.add(user.getUserLoginName() + "("
					+ user.getUserLoginName() + ")");
			auditManager.insertByLoginOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "成功", super
					.getRequest().getRemoteAddr(), fieldList);

			/*
			 * String logString="";
			 * 
			 * 
			 * logString="登录名："+user.getUserLoginName()+"  源IP:"+getRequest().
			 * getRemoteAddr()+"   操作时间："+DateUtil.curDateTimeStr19()
			 * +"   操作类型：登录系统"
			 */;

			/*
			 * for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
			 * for (String key : map.keySet()) { SyslogIF syslogIF =
			 * map.get(key); SysLogSender.sender(syslogIF, logString); } }
			 */
			logManager.writeSystemAuditLog(user.getUserLoginName(),
					getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(),
					"登录系统");
			// logManager.writeSystemAuditLog(logString);

			return SUCCESS;
		}

	}

	/**
	 * 修改用户密码 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String changePassword() {
		if (newPassword == null || StringUtil.equals(newPassword, "")) {
			getRequest().setAttribute("pwdPolicyInfo", "*新密码不能为空");
			return INPUT;
		}
		user = (User) getSession().getAttribute("SOC_LOGON_USER");

		user = loginManager.changePassword(user, newPassword);

		this.getRequest().getSession().setAttribute("SOC_LOGON_USER", user);

		List<String> fieldList = new ArrayList<String>();
		fieldList.add(user.getUserLoginName() + "(" + user.getUserLoginName()
				+ ")");
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "密码成功", super
				.getRequest().getRemoteAddr(), fieldList);

		/*
		 * String logString="";
		 * 
		 * 
		 * logString="登录名："+user.getUserLoginName()+"  源IP:"+getRequest().
		 * getRemoteAddr()+"   操作时间："+DateUtil.curDateTimeStr19()
		 * +"   操作类型：更新密码";
		 */
		/*
		 * for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) { for
		 * (String key : map.keySet()) { SyslogIF syslogIF = map.get(key);
		 * SysLogSender.sender(syslogIF, logString); } }
		 */
		// logManager.writeSystemAuditLog(logString);
		logManager.writeSystemAuditLog(user.getUserLoginName(), getRequest()
				.getRemoteAddr(), DateUtil.curDateTimeStr19(), "更新密码");
		return SUCCESS;
	}

	/**
	 * 用户登出
	 * 
	 * @param
	 * @return String
	 */
	public String logout() {
		HttpServletRequest request = getRequest();
		String date = DateUtil.curDateTimeStr19();
		HttpServletRequest request1 = ServletActionContext.getRequest();
		User user3 = (User) request.getSession().getAttribute("SOC_LOGON_USER");

		List<String> fieldList = new ArrayList<String>();
		fieldList.add(user3.getUserLoginName() + "(" + user3.getUserLoginName()
				+ ")");
		auditManager.insertBySystemOperator(user3.getUserId(), "用户登出成功",
				request1.getRemoteHost(), fieldList);

		// String logString="";

		/*
		 * logString="登录名："+user3.getUserLoginName()+"  源IP:"+request1.getRemoteHost
		 * ()+"   操作时间："+DateUtil.curDateTimeStr19() +"   操作类型：用户登出成功";
		 */

		/*
		 * for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) { for
		 * (String key : map.keySet()) { SyslogIF syslogIF = map.get(key);
		 * SysLogSender.sender(syslogIF, logString); } }
		 */
		// logManager.writeSystemAuditLog(logString);
		logManager.writeSystemAuditLog(user3.getUserLoginName(), getRequest()
				.getRemoteAddr(), DateUtil.curDateTimeStr19(), "用户登出成功");

		HttpSession session = this.getRequest().getSession();

		session.invalidate();

		return SUCCESS;
	}

	public LoginService getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginService loginManager) {
		this.loginManager = loginManager;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public User getUser() {
		return user;
	}

	public void setEmployee(User user) {
		this.user = user;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public String getLoginName2() {
		return loginName2;
	}

	public String getPassword2() {
		return password2;
	}

	public String getImgCode2() {
		return imgCode2;
	}

	public void setLoginName2(String loginName2) {
		this.loginName2 = loginName2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public void setImgCode2(String imgCode2) {
		this.imgCode2 = imgCode2;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SettingService getSettingManger() {
		return settingManger;
	}

	public void setSettingManger(SettingService settingManger) {
		this.settingManger = settingManger;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public void setSavedSecret(String savedSecret) {
		this.savedSecret = savedSecret;
	}

	public String getSavedSecret() {
		return savedSecret;
	}
	
	public Integer getCheckstyle() {
		return checkstyle;
	}
	public void setCheckstyle(Integer checkstyle) {
		this.checkstyle = checkstyle;
	}
	@Override
	public void setSession(Map arg0) {
		this.sessionMap = arg0;
	}

	public int getNetworkTopologyLogin() {
		return networkTopologyLogin;
	}

	public void setNetworkTopologyLogin(int networkTopologyLogin) {
		this.networkTopologyLogin = networkTopologyLogin;
	}
	
	
	public static boolean checkName(String name) {
		String regex = "([`~!@#$%^&*()=|{}':;, \\[\\]<>\\/?~！@#￥……&*（）—|{}【】‘；：”“\'\"，、？]+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public static void main(String[] args) {
		System.err.println(checkName("!#"));
	}

}
