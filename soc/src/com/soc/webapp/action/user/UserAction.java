package com.soc.webapp.action.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.authenticator.GoogleAuthenticator;
import com.push.services.SendMessage;
import com.soc.model.asset.AssetGroup;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.policy.Address;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.policy.TimePolicy;
import com.soc.model.role.Role;
import com.soc.model.user.OnlineUser;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.audit.AuditService;
import com.soc.service.homepagediv.HomePageDivService;
import com.soc.service.policy.AddressPolicyService;
import com.soc.service.policy.PasswordPolicyService;
import com.soc.service.policy.TimePolicyService;
import com.soc.service.role.RoleService;
import com.soc.service.systemsetting.SystemSettingService;
import com.soc.service.systemsetting.impl.LogWriteServiceImpl;
import com.soc.service.user.UserService;
import com.soc.service.user.impl.UserServiceImpl;
import com.soc.webapp.action.BaseAction;
import com.util.Base64;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <账户Action> <对账户的相关操作：查询、修改、删除>
 * 
 * @author 王亚男
 * @version [V100R001C001, 2012-8-4]
 * @see [BaseAction,UserServiceImpl]
 * @since
 */
public class UserAction extends BaseAction implements SessionAware {
	private static final long serialVersionUID = 1L;

	// 用户服务管理类
	private UserService userManager;
	//资产组服务管理类
	private AssetGroupService assetGroupManager;
	// 角色服务管理类
	private RoleService roleManager;

	// 密码策略服务管理类
	private PasswordPolicyService passwordPolicyManager;

	// 时间策略服务管理类
	private TimePolicyService timePolicyManager;

	// 地址策略服务管理类
	private AddressPolicyService addressPolicyManager;

	// 用户实体类
	private User user;

	// 存储符合条件的用户的List
	private List<User> userList;

	// 用户ID
	private int userId;

	// 模糊查询关键字
	private String keyword;

	// 批量操作时复选框的值
	private String ids;

	// 用户的状态(0:激活|1:锁定|2:注销)
	private int userStatus;
	
	// 新密码
	private String newPassword;

	// 登录名
	private String userLoginName;

	// 高级条件查询-登录名
	private String selUserLoginName;

	private String selUserRealName;
	// 高级条件查询-手机号码
	private String selUserMobile;

	// 高级条件查询-创建者IP
	private String selUserCreatorIp;
	// 高级条件查询-邮箱
    private String reluserEmail;
    //高级查询条件--角色id
    private String relroleid;
  //高级查询条件--用户状态
    private String reluserState;
 // 用来存储div是否的字符串的服务类
 	private HomePageDivService homePageDivManager;
	public String getRelroleid() {
		return relroleid;
	}

	public void setRelroleid(String relroleid) {
		this.relroleid = relroleid;
	}

	// 关联角色
	private List<Role> roleList;
	
	// 编辑用户时页面传递过来的资产组的ID串
	private String assetGroups;

	// 关联密码策略
	private List<PasswordPolicy> passwordPolicyList;

	// 关联时间策略
	private List<TimePolicy> timePolicyList;

	// 关联地址策略
	private List<AddressPolicy> addressPolicyList;
	//用户资产组列表
	private List<AssetGroup> assetGroupList;
	//资产组列表
		private List<AssetGroup> allAssetGroupList;
	// 角色列表
	private List<Role> allRoleList;

	// 密码策略列表
	private List<PasswordPolicy> allPasswordPolicyList;

	// 时间策略列表
	private List<TimePolicy> allTimePolicyList;

	// 地址策略
	private List<AddressPolicy> allAddressPolicyList;

	// 关联角色id串
	private String roles;
	//用户的角色id
	private long userroleid;
	// 关联密码策略id串
	private String passwordPolicy;
	//登陆用户的id，前台来做判断，是否删除本用户
	private long myid;
	// 关联时间策略id串
	private String timePolicy;
	//标识，判断跳转至哪个页面
	private String falg;
	// 关联地址策略id串
	private String addressPolicy;
	//关联资产组id
	private long RelAssetGroupId;
	private AuditService auditManager;
	// 是否选择所有资产组
	private int assetGroupType;
		
	public int getAssetGroupType() {
			return assetGroupType;
		}

		public void setAssetGroupType(int assetGroupType) {
			this.assetGroupType = assetGroupType;
		}

	//请求的action字符串
    private String actionStr ="queryUser.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ;  
    
    //数据推送类
	private SendMessage msg;
	
	private Map<String , Object> sessionMap;
	
	private String data;
	
	private String accoutIp;
	
	//同步用户服务器IP
	private String syncIp;
	private SystemSettingService ssManger;
	private String secret;
	/**
	 * 查询账户-快速检索、高级检索 根据接收到的搜索条件完成用户检索，
	 * 
	 * @return String
	 * @see Page,StringUtil
	 */
	public String queryUser() {
		LOG.info("[UserAction] enter method queryUser() ...");

		HttpServletRequest request = super.getRequest();

		Page page = null;
		SearchResult sr = null;
		user = new User();
		Map map1 =new HashMap();
		allRoleList =roleManager.queryRole(map1);
		myid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getUserId();
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
		if (StringUtil.isNotBlank(selUserLoginName)) {
			map.put("selUserLoginName", selUserLoginName);
		}
		if (StringUtil.isNotBlank(selUserCreatorIp)) {
			map.put("selUserCreatorIp", selUserCreatorIp);
		}
		if (StringUtil.isNotBlank(selUserRealName)) {
			map.put("selUserRealName", selUserRealName);
		}
	//	userState=request.getParameter("userState") ;
		if(StringUtil.isNotBlank(reluserState)){
			map.put("userState", Integer.parseInt(reluserState));
		}
		//roleid=request.getParameter("roleid");
		if(StringUtil.isNotBlank(relroleid)){
			map.put("roleid", Integer.parseInt(relroleid));
		}
		//userEmail=request.getParameter("userEmail");
		if(StringUtil.isNotBlank(reluserEmail)){
			map.put("userEmail", reluserEmail);
		}
		// 默认内置用户不显示,0:隐藏|1:显示
		map.put("display", GlobalConfig.DISPLAY_SIGN);
		allAssetGroupList= assetGroupManager.query();
		// 改动过(2012-10-21)
		// 判断用户管理角色的关联用户
		/*Role role = (Role) request.getSession().getAttribute("SOC_LOGON_ROLE");

		if (role != null) {
			if (StringUtil.isNotBlank(role.getUserIds())) {
				map.put("relUsers", role.getUserIds());
			}
			if (StringUtil.isNotBlank(role.getUserIds())) {*/

				// 根据map中存储的查询条件，查找符合条件的用户列表
				sr = userManager.query(map, page);
	/*		}
		}
*/
		// 对查找的结果为分页赋值
		if (sr != null) {
			userList = (List<User>) sr.getList();
			for (User u : userList) {

				List<Role> r = u.getUserRoleList();
			}
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}

		return SUCCESS;

	}

	

	/**
	 * 编辑账户 查看账户详细信息，包括此账户当前关联的策略的查询
	 * 
	 * @return String
	 * @see UserServiceImpl#queryByUserId
	 * @see UserServiceImpl#queryRelPasswordPolicy
	 * @see UserServiceImpl#queryRelTimePolicy
	 * @see UserServiceImpl#queryRelAddress
	 */
	public String editUser() {
		LOG.info("[UserAction] enter method editUser() ...");

		// 查询账户基本信息
		user = userManager.queryByUserId(userId);

		// 查询账户关联角色信息
		roleList = userManager.queryRelRole(userId);
		for (Role role : roleList) {
			userroleid =role.getRoleId();
		}
		// 查询账户关联密码策略信息
		passwordPolicyList = userManager.queryRelPasswordPolicy(userId);

		// 查询账户关联时间策略信息
		timePolicyList = userManager.queryRelTimePolicy(userId);

		// 查询账户关联地址策略信息
		addressPolicyList = userManager.queryRelAddressPolicy(userId);

		Map<String, Object> map = new HashMap<String, Object>();

		// 角色列表
		allRoleList = roleManager.queryRole(map);
		//屏蔽掉oem角色
		List<Role> tmpList = new ArrayList<Role>();
		for (Role role : allRoleList) {
			if (role.getRoleName().equals("OEM")) {
				tmpList.add(role);
				break;
			}
		}
		allRoleList.removeAll(tmpList);
		// 密码策略列表
		allPasswordPolicyList = passwordPolicyManager.query(map);

		// 时间策略
		allTimePolicyList = timePolicyManager.queryTimePolicy(map);
		//所有资产组
		allAssetGroupList= assetGroupManager.query();
		// 取得相关资产组信息
		Map map1 = new HashMap();
		if(user!=null){
			map1.put("assetGroupId", user.getAssetGroupid());
			assetGroupList= assetGroupManager.queryByuserid(map1);
		}
		
		// 地址策略
		allAddressPolicyList = addressPolicyManager.queryAddressPolicy(map);
		if(falg.equals("1")){
			return "adduser";
		}else{
		return SUCCESS;
		}
	}

	/**
	 * 批量更新账户状态 将账户的状态批量修改为锁定、激活、注销
	 * 
	 * @return String
	 * @see UserServiceImpl#queryByUserId
	 * @see UserServiceImpl#updateUserStatus
	 * @see LogWriteServiceImpl#writeSystemAuditLog
	 * @see UserServiceImpl#deleteUser
	 */
	public String updateUserStatus() {
		LOG.info("[UserAction] enter method updateUserStatus() ...");
		List<String> fieldList = new ArrayList<String>();
		if (StringUtil.isNotBlank(ids)) {
			// 初始化审计描述
			String statusStr = "";

			if (userStatus == GlobalConfig.STATUS_ACTIVATE) {
				statusStr = "账户激活";
			} else if (userStatus == GlobalConfig.STATUS_LOCKED) {
				statusStr = "账户锁定";
				
				//获得要锁定的用户，如果被锁定的用户在线给予提示
				String[] checked = ids.split(",");
				for (int i = 0; i < checked.length; i++) {
					user = userManager.queryByUserId(Integer.parseInt(checked[i]));
					sessionMap.put("userinfo", user.getUserLoginName());
					msg.sendMessageAuto(user.getUserLoginName(), "您已经被锁定了，请及时下线");
				}
			}
			// 针对多个账户操作
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				// 循环遍历需要执行更新状态的账户ID并执行更新状态操作
				for (String checkid : checked) {
					User userObject = userManager.queryByUserId(Long.parseLong(checkid));
					fieldList.add(userObject.getUserLoginName());
					userManager.updateUserStatus(Long.parseLong(checkid),userStatus);
				}

			}

			// 针对单个账户操作
			else {
				
				User userObject = userManager.queryByUserId(Long.parseLong(ids));
				fieldList.add(userObject.getUserLoginName());
				userManager.updateUserStatus(Long.parseLong(ids), userStatus);
			}

			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), statusStr,
					super.getRequest().getRemoteAddr(), fieldList);

			// syslog日志输出
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:更新账户状态";*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改用户状态");
			//logManager.writeSystemAuditLog(logString);
		}

		return SUCCESS;
	}

	/**
	 * 检测账户的登录名是否存在 根据账户登录名查找账户，如果存在向页面返回"flag=true"，如果不存在向页面返回"flag=false"
	 * 
	 * @see UserServiceImpl#queryByUserLoginName
	 */
	public void queryByUserLoginName() {
		LOG.info("[UserAction] enter method queryByUserLoginName() ...");

		// 标识此登录名是否存在
		String flag = "false";  

		// 根据登录名查找账户
		if (StringUtil.isNotBlank(userLoginName)) {
			List<User> userList1 = userManager
					.queryByUserLoginName(userLoginName);

			// 查找到账户，将标识flag设置为true
			if (userList1.size() > 0) {
				flag = "true";
			}

			// 将flag返回给页面
			try {
				getResponse().getWriter().write(flag);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return;
	}
	/**
	 * 谷歌二次认证得到谷歌key
	 * 
	 */
	public void getSecretKey(){
		 secret = GoogleAuthenticator.generateSecretKey();
		 String url = GoogleAuthenticator.getQRBarcodeURL("testuser", "testhost", secret);
		 HttpServletResponse response=ServletActionContext.getResponse();
		 response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().println(secret);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        //String url = GoogleAuthenticator.getQRBarcodeURL("testuser", "testhost", secret);
        return;
        
	}
	/**
	 * 谷歌key生成二维码
	 * 
	 
	public void getErWeiMa(){
		 secret = GoogleAuthenticator.generateSecretKey();
		 HttpServletResponse response=ServletActionContext.getResponse();
		 response.setCharacterEncoding("UTF-8");
		 try {
		     EwPath = user.getUserEwPath();
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(secret, BarcodeFormat.QR_CODE, 400, 400,hints);
		     response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().println(bitMatrix);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     File file = new File(EwPath,userLoginName+".jpg");
		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
		     
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
       //String url = GoogleAuthenticator.getQRBarcodeURL("testuser", "testhost", secret);
       return;
	}*/
	

	/**
	 * 添加/修改账户 根据账户的ID是否为0判断当前需要执行的是创建账户还是修改账户
	 * 
	 * @return String
	 * @see UserServiceImpl#queryByUserId,UserServiceImpl#updateUser,
	 */
	public String updateUser() {
		LOG.info("[UserAction] enter method updateUser() ...");

		HttpServletRequest request = super.getRequest();

		// 创建/修改账户的标识(true:创建|false:修改)
		boolean createFlag = false;

		// 更新时备份原有对象，以作审计对比
		User userSrc = null;

		// 创建账户操作
		if (user.getUserId() == GlobalConfig.CREATE_SIGN) {
			// 设置创建者IP地址
			createFlag = true;
			user.setUserCreatorIp(request.getRemoteAddr());
		} else {
			// 更新时备份原有对象，以作审计对比
			userSrc = userManager.queryByUserId(user.getUserId());
			user.setUserStatus(userSrc.getUserStatus());
		}
		if (StringUtil.isNotEmpty(assetGroups)) {
			user.setAssetGroupid(Integer.parseInt(assetGroups));
		}else{
		
			user.setAssetGroupid(1);

		}
		
		// 更新人员基本信息
		long userId = userManager.updateUser(user);
		//这里不写13  写div的数量 这样拓展性好 以后如果加div 直接在HomeDiv.map中加就ok了
	
			/* int divCount = HomeDiv.map.size();
		        for (int i = 1; i <= divCount; i++) {
		        	//新建用户的时候为用户去创建这12个div
		        	if (i<7) {
		        		//设置默认显示前6个
		        		 homePageDivManager.insertDiv(new HomePageDiv("d"+i, 1, i%2,userId));
					}else{
						 homePageDivManager.insertDiv(new HomePageDiv("d"+i, 0, i%2, userId));
					}
		           
				}*/
		
       
        
		// 如果更新的是当前登录用户更新session
		if (user.getUserPassword() == null) {
			
			user.setUserPassword(userSrc.getUserPassword());
		}
		User user1 = (User) request.getSession().getAttribute("SOC_LOGON_USER");
		if (user1.getUserId() == user.getUserId()) {
			request.getSession().setAttribute("SOC_LOGON_USER", user);
		}
		
		int u1= -1, u2 = -1, u3 = -1 ,no = -1;
		//查询修改的密码策略之前知否存在
		if (StringUtil.isNotBlank(passwordPolicy)) {
			String[] passwordPolicyArray = passwordPolicy.split(",");
			for (String passwordPolicyId : passwordPolicyArray) {
				List<User> u = passwordPolicyManager.queryUserByPasswordPolicyId(Integer.parseInt(passwordPolicyId));
					if (u==null || u.size()==0) {
						u1 = 1;
						break;
					}else {
						for (User useru : u) {
							if (useru.getUserId()!=user.getUserId()) {
								u1 = 1;
								break;
							}
						}
					}
					
				}
			}
		// 查询时间策略之前是否存在
		if (StringUtil.isNotBlank(timePolicy)) {
			String[] timePolicyArray = timePolicy.split(",");
			for (String timePolicyId : timePolicyArray) {
				List<User> u = timePolicyManager.queryUserByTimePolicyId(Integer.parseInt(timePolicyId));
				if (u == null || u.size()==0) {
					u2 = 1;
					break;
				}else {
					for (User useru : u) {
						if (useru.getUserId()!=user.getUserId()) {
							u2 = 1;
							break;
						}
					}
				}
			}
		}	
		// 查询关联地址策略之前是否存在
		if (StringUtil.isNotBlank(addressPolicy)) {
			String[] addressPolicyArray = addressPolicy.split(",");
			for (String addressPolicyId : addressPolicyArray) {
				List<User> u = addressPolicyManager.queryUserByAddressPolicyId(Integer.parseInt(addressPolicyId));
				if (u == null || u.size()==0) {
					u3 = 1;
					break;
				}else {
					for (User useru : u) {
						if (useru.getUserId()!=user.getUserId()) {
							u3 = 1;
							break;
						}
					}
				}
			}	
		}
		// 删除关联信息
		userManager.deleteRelRole(user.getUserId());
		userManager.deleteRelPasswordPolicy(user.getUserId());
		userManager.deleteRelTimePolicy(user.getUserId());
		userManager.deleteRelAddressPolicy(user.getUserId());
	
//
		
		// 添加角色
		if (StringUtil.isNotBlank(roles)) {
			String[] roleArray = roles.split(",");
			for (String roleId : roleArray) {
				if(StringUtil.isNotBlank(roleId)){
					Map<String, Long> map = new HashMap<String, Long>();
					
					map.put("roleId", Long.valueOf(roleId.trim()));
					map.put("userId", user.getUserId());

					userManager.insertRelRole(map);
				}
				
			}
		}

		// 添加关联密码策略
		if (StringUtil.isNotBlank(passwordPolicy)) {
			String[] passwordPolicyArray = passwordPolicy.split(",");
			for (String passwordPolicyId : passwordPolicyArray) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("passwordPolicyId", Long.valueOf(passwordPolicyId));
				map.put("userId", user.getUserId());
				userManager.insertRelPasswordPolicy(map);
			}
			if (u1==1) {
				if (no == -1) {
					no = 1;
					sessionMap.put("userinfo", user.getUserLoginName());
					msg.sendMessageAuto(user.getUserLoginName(), "您的账户新增密码策略，请及时联系管理员修改密码！");
				}
			}
				
		}

		// 添加关联时间策略
		if (StringUtil.isNotBlank(timePolicy)) {
			String[] timePolicyArray = timePolicy.split(",");
			boolean check = false;
			for (String timePolicyId : timePolicyArray) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("timePolicyId", Long.valueOf(timePolicyId));
				map.put("userId", user.getUserId());
				userManager.insertRelTimePolicy(map);
				TimePolicy tp = timePolicyManager.queryTimePolicyById(Integer.parseInt(timePolicyId));
				if (u2==1) {
					if (tp.getTimePolicyEffectWay()==0) {
						if (timePolicyJudge(tp)) {
							check = true;
						}	
					}
				}
			}
			if (check) {
				if (no == -1) {
					no = 1;
					sessionMap.put("userinfo", user.getUserLoginName());
					msg.sendMessageAuto(user.getUserLoginName(), "您的账户新增时间策略，当前时间不在允许范围内，请及时下线！");
				}
				
			}
		}

		// 添加关联地址策略
		if (StringUtil.isNotBlank(addressPolicy)) {
			String[] addressPolicyArray = addressPolicy.split(",");
			boolean check = false;
			for (String addressPolicyId : addressPolicyArray) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("addressPolicyId", Long.valueOf(addressPolicyId));
				map.put("userId", user.getUserId());
				userManager.insertRelAddressPolicy(map);
				if (u3 == 1) {
					if (!checkPolicyIp(addressPolicyId)) {
						check = true;
					}
				}
			}
			if (check) {
				if (no == -1) {
					no = 1;
					sessionMap.put("userinfo", user.getUserLoginName());
					msg.sendMessageAuto(user.getUserLoginName(), "您的账户新增地址策略，当前IP不在允许范围内，请及时下线！");
				}
			}
		}

		// 写入审计数据库
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(user.getUserLoginName() + "(" + user.getUserLoginName()
				+ ")");
		//String logString = "";
		if (createFlag == true) {
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "账号", super
					.getRequest().getRemoteAddr(), fieldList);

			/*String logString1 = "";
			logString1 = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:添加账户";

			logManager.writeSystemAuditLog(logString1);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "添加用户户");

		} else {
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "帐号", super
					.getRequest().getRemoteAddr(), fieldList);
			// syslog日志
			/*String logString1 = "";
			logString1 = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:修改账户";

			logManager.writeSystemAuditLog(logString1);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改用户");
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String deleteUser() {
		LOG.info("[UserAction] enter method deleteUser() ...");

		if (StringUtil.isNotBlank(ids)) {
			// 初始化审计描述
			String statusStr = "";
			List<String> fieldList = new ArrayList<String>();

			statusStr = "删除账户";

			// 针对多个账户操作
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");

				// 循环遍历需要执行更新状态的账户ID并执行更新状态操作
				for (String checkid : checked) {
					User userObject = userManager.queryByUserId(Long
							.parseLong(checkid));
					fieldList.add(userObject.getUserRealName());
					//删除这个账户对应的div
					homePageDivManager.deleteDivByUserId(Integer.parseInt(checkid));
					// 批量注销账户
					if (userObject.getUserDisplay() != GlobalConfig.HIDEN_SIGN) {
						userManager.deleteUser(Long.parseLong(checkid),1);
					}
				}
			}

			// 针对单个账户操作
			else {
				User userObject = userManager
						.queryByUserId(Long.parseLong(ids));
				fieldList.add(userObject.getUserLoginName());
				if (userObject.getUserDisplay() != GlobalConfig.HIDEN_SIGN) {
					userManager.deleteUser(Long.parseLong(ids),1);
				}
				//删除这个账户对应的div
				homePageDivManager.deleteDivByUserId(Integer.parseInt(ids));
			}

			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "账号", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog日志
			/*String logString = "";

			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间:"
					+ DateUtil.curDateTimeStr19() + "   操作类型:删除账户";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除用户");
		}

		return SUCCESS;
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String personSeting() {
		log.info("display employee personal setting page...");

		HttpServletRequest request = getRequest();

		User user1 = (User) request.getSession().getAttribute("SOC_LOGON_USER");

		if (!StringUtil.equals(newPassword, "")) {
			// 列出密码策略
			/*
			 * List<Map> policyList = employeeManager.queryRelPolicy(emp
			 * .getEmpId()); if (policyList.size() > 0) { for (Map map :
			 * policyList) { if (map.size() > 0) { if
			 * (StringUtil.equals(map.get("relType").toString(), "3")) {
			 * passwordList.add(map); } } } if
			 * (getPwdPolicyMessage(passwordList) != null) {
			 * request.getSession().setAttribute("pwdPolicyInfo",
			 * getPwdPolicyMessage(passwordList).toString()); }else{
			 * request.getSession().removeAttribute("pwdPolicyInfo"); } } }
			 */
			// 查询账户关联密码策略信息
			// passwordPolicyList = userManager.queryRelPasswordPolicy(userId);

		}
		if (request.getAttribute("changPwd") != null) {
			return "changePwd";
		}
		return SUCCESS;
	}

	/**
	 * <保存用户修改的信息> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String userInfoSeting() {
		log.info("[UserAction] enter method userInfoSeting() ...");
		HttpServletRequest request = getRequest();

		User userTemp = userManager.queryByUserId(user.getUserId());
		userTemp.setUserRealName(user.getUserRealName());
		userTemp.setUserEmail(user.getUserEmail());
		userTemp.setUserMobile(user.getUserMobile());
		userTemp.setUserTelephone(user.getUserTelephone());
		userTemp.setUserAddress(user.getUserAddress());
		userTemp.setUserMemo(user.getUserMemo());
		user.setAssetGroupid(userTemp.getAssetGroupid());
		if (!StringUtil.equals(newPassword, "") && newPassword != null) {
			// employee.setEmpPassword(newPassword);
			userTemp.setUserPassword(newPassword);
			// employee.setEmpChangePassword(1);
			// employee.setEmpChangePwdTime(new Date());
			userTemp.setUserUpdateDateTime(new Date());
		}else{
			userTemp.setUserPassword("");
		}
		userManager.updateUser(userTemp);
		if (StringUtil.equals(newPassword, "") || newPassword == null) {
			user.setUserPassword(userTemp.getUserPassword());
		}

		request.setAttribute("setSuccess", "设置成功");
		getSession().setAttribute("SOC_LOGON_USER", userTemp);

		List<String> fieldList = new ArrayList<String>();

		fieldList.add(user.getUserLoginName() + "(" + user.getUserLoginName()
				+ ")");

		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "个人设置", super
				.getRequest().getRemoteAddr(), fieldList);

		String logString = "登录名："
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + "  源IP:"
				+ getRequest().getRemoteAddr() + "   操作时间："
				+ DateUtil.curDateTimeStr19() + "   操作类型:个人设置";

		logManager.writeSystemAuditLog(logString);
		return SUCCESS;
	}

	/**
	 * 核对用户密码 <功能详细描述>
	 * 
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public String checkPassword() {
		User u = (User) getSession().getAttribute("SOC_LOGON_USER");
		String oldPassword = userManager.queryByUserId(u.getUserId()).getUserPassword();
		//String fullPassword = user.getUserPassword();
		HttpServletRequest request=super.getRequest();
		String fullPassword=request.getParameter("oldPassword");
		//String password = MD5.getMD5Password(fullPassword);
		String password = Base64.encodeString(fullPassword);//修改
		// 检验密码是否正确
		try {
			if (!StringUtil.equals(oldPassword, password)) {
				/*
				 * logManager.writeWarninglog(u.getUserLoginName()+"在个人设置密码输入错误")
				 * ;
				 */
				getResponse().getWriter().write("*密码错误请确认后再输");
				return null;
			} else {
				getResponse().getWriter().write("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <获得用户关联的密码策略信息> <功能详细描述>
	 * 
	 * @param passwordList
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	
	/**
	 * 验证时间策略与当前时间
	 * @param timePolicy
	 * @return
	 */
	public boolean timePolicyJudge(TimePolicy timePolicy)
    {
	 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //当前日期
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        
        //获得今天星期几
        int dayofWeek = c.get(Calendar.DAY_OF_WEEK);
        
        if (dayofWeek == 0)
        {
            dayofWeek = 7;
        }
        String beginTime = "";
        String endTime = "";
        String weekSet = "";
        String hourSet = "";
        boolean flag = false;
            //得到时间策略是按照周还是时间段执行
            if (timePolicy.getTimePolicyexecuteWay() == 0)
            {
                if (timePolicy.getTimePolicyDateStart() != null)
                {
                    beginTime = timePolicy.getTimePolicyDateStart().toString();
                }
                if (timePolicy.getTimePolicyDateEnd() != null)
                {
                    endTime = timePolicy.getTimePolicyDateEnd().toString();
                }
                //判断当前时间是否在时间时间段内
                if (StringUtil.isNotBlank(beginTime) && StringUtil.isNotBlank(endTime))
                {
                    try
                    {
                        if (sdf.parse(beginTime).before(now) && sdf.parse(endTime).after(now))
                        {
                            flag = true;
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            //判断在周内
            else if (timePolicy.getTimePolicyexecuteWay() == 1)
            {
                if (timePolicy.getTimePolicyWeek() != null)
                {
                    weekSet = timePolicy.getTimePolicyWeek().toString();
                }
                //需要判断星期几且今天是时间对象规定的星期几
                if (!weekSet.equals("0000000"))
                {
                    if (weekSet.substring(dayofWeek - 1, dayofWeek).equals("1"))
                    {
                        flag = true;
                    }
                    else
                    {
                        flag = false;
                    }
                }
            }
            
            //获得允许执行的时间点
            if (timePolicy.getTimePolicyHour() != null)
            {
                hourSet = timePolicy.getTimePolicyHour().toString();
            }
            
            //判断时间点是否包含在时间对象规定的时间点
            if (!hourSet.equals("000000000000000000000000"))
            {
                if (hourSet.substring(Integer.parseInt(DateUtil.curDateHour()),
                    Integer.parseInt(DateUtil.curDateHour()) + 1).equals("1"))
                {
                    flag = true;
                }
                else
                {
                    flag = false;
                }
            }
            
        return flag;
    }

	/**
	 * 验证地址策略
	 * @param checkid
	 */
	public boolean checkPolicyIp(String checkid){
		boolean check = false;
		//判断当前用户所在Ip是否在允许范围内
		List<Address> addressList = addressPolicyManager.getAddressList(Integer.parseInt(checkid));
		AddressPolicy ap = addressPolicyManager.queryById(Integer.parseInt(checkid));
		if (addressList!=null && addressList.size()>0) {
				for (int i = 0; i < addressList.size(); i++) {
						Address address = addressList.get(i);
						userList = addressPolicyManager.queryUserByAddressPolicyId((Integer.parseInt(checkid)));
						if (userList!=null && userList.size()>0) {
							 List<OnlineUser> OnUserList = (List<OnlineUser>)super.getServletContext().getAttribute("SOC_ONLINE_USERLIST");
							 String ipnowIp = "";
							 int no = 0;
							for (User user : userList) {
								for (OnlineUser onlineUser : OnUserList) {
									if (user.getUserId()==onlineUser.getUserId()) {
										ipnowIp = onlineUser.getUserIp();
										no = 0;
										break;
									}else {
										no = 1;
									}
								}
								if (no==1) {
									continue;
								}
								if (ipnowIp.contains(":")) {
									break;
								}
								String [] ipnow = ipnowIp.split("\\.");
								String [] ipStart = address.getAddressStartIp().split("\\.");
								String [] ipEnd = address.getAddressEndIp().split("\\.");
								
								if (Integer.parseInt(ipStart[0])<=Integer.parseInt(ipnow[0]) && Integer.parseInt(ipEnd[0])>=Integer.parseInt(ipnow[0])) {
									if (Integer.parseInt(ipStart[1])<=Integer.parseInt(ipnow[1]) && Integer.parseInt(ipEnd[1])>=Integer.parseInt(ipnow[1])) {
										if (Integer.parseInt(ipStart[2])<=Integer.parseInt(ipnow[2]) && Integer.parseInt(ipEnd[2])>=Integer.parseInt(ipnow[2])) {
											if (Integer.parseInt(ipStart[3])<=Integer.parseInt(ipnow[3]) && Integer.parseInt(ipEnd[3])>Integer.parseInt(ipnow[3])) {
												if (ap.getAddressPolicyEffectWay()==1) {
													check = true;	
												}
											} 
										} 
									} 
								} 
							}
						}
						break;
				}
		}
		return check;
	}
	
	
	
	
	//同步4A账户
	public String accountData(){	
		Document doc = null;
		try {
			  if (data!=null) {
				  //System.out.println(System.getProperty("file.encoding"));
				  //System.out.println(data);
				  data = Base64.decodeString(data);
				  //System.out.println(data+"=========================================================================================");
				  // 下面的是通过解析xml字符串的
				  //data =java.net.URLDecoder.decode(data, "GBK") ;
				  //System.out.println(data+"=========================================================================================");
				  doc = DocumentHelper.parseText(data); // 将字符串转为XML
				 
				  Element rootElt = doc.getRootElement(); // 获取根节点
				  if(rootElt.getName()!=""||rootElt.getName()!=null){
					  //System.out.println("根节点：" + rootElt.getName()); // 输出根节点的名称
					  Iterator iter = rootElt.elementIterator("account"); // 获取根节点下的子节点account
					  // 遍历account节点
					  while (iter.hasNext()) {
						  	User user = new User();
						  	Map map = new HashMap<String, Object>();
						  	Element recordEle = (Element) iter.next();
						  	String loginName = recordEle.elementTextTrim("loginName");
						  	user.setUserRealName(recordEle.elementTextTrim("userName"));
	            	  		user.setUserEmail(recordEle.elementTextTrim("userMail"));
	            	  		user.setUserMobile(recordEle.elementTextTrim("userPhone"));
	            	  		user.setUserTelephone(recordEle.elementTextTrim("userTele"));
	            	  		user.setUserAddress(recordEle.elementTextTrim("userAddress"));
	                  		user.setUserLoginName(recordEle.elementTextTrim("loginName"));
	                  		user.setUserPassword(recordEle.elementTextTrim("loginPassword"));
	                  		user.setAssetGroupid(1);
	                  		user.setUserMemo(recordEle.elementTextTrim("userNote"));
	                  		
	                  		//获取isDel（0代表添加|1代表添加）是用户添加还是删除用户
	                  		int isDel = Integer.parseInt(recordEle.elementText("isDel")) ; 
	                  		
	                  		//添加用户
	                  		if(isDel == 1){
	                  		
	                  		//判断账户是否存在
	                  		long id = userManager.queryUserIdByName(loginName.trim());
	                  		
	                  	 	if (id ==0) {
	                  			
	                  			long userId = userManager.updateUserAccount(user);
	                  			map.put("userId", userId);
	                  			map.put("roleId", 32);
	                  			userManager.insertRelRole(map);
	                  	
							}
	                  		else { 
	                  			
	                  			//账户存在，则执行账户升级功能
								user.setUserId(id);
								userManager.updateUserAccount(user);
								//userManager.deleteUser(user.getUserId(), 0);
							}
	                  		
	                  		 //删除用户
	                  		} else if(isDel == 0){
	                  			//获取要删除的用户ID
	                  			long id = userManager.queryUserIdByName(loginName.trim());
	                  			userManager.deleteUser(id, 1);
	                  			//删除用户关联的 密码策略
	                  			userManager.deleteRelPasswordPolicy(id);
	                  			//删除用户关联的 资产组信息
	                  			userManager.deleteRelAssetGroup(id);
	                  			//删除用户关联的 地址策略
	                  			userManager.deleteRelAddressPolicy(id);
	                  			//删除用户关联的 时间策略
	                  			userManager.deleteRelTimePolicy(id);
	                  			//删除用户关联的角色信息
	                  			userManager.deleteRelRole(id);
	                  		}
					  }
				  }
				
			  }	
		    } catch (DocumentException e) {
		              e.printStackTrace();
		  
		 }catch (Exception e) {
             e.printStackTrace();
          }

		return SUCCESS;
	}
	
	
	
	

    
    
	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public RoleService getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleService roleManager) {
		this.roleManager = roleManager;
	}

	public PasswordPolicyService getPasswordPolicyManager() {
		return passwordPolicyManager;
	}

	public void setPasswordPolicyManager(
			PasswordPolicyService passwordPolicyManager) {
		this.passwordPolicyManager = passwordPolicyManager;
	}

	public TimePolicyService getTimePolicyManager() {
		return timePolicyManager;
	}

	public void setTimePolicyManager(TimePolicyService timePolicyManager) {
		this.timePolicyManager = timePolicyManager;
	}

	public AddressPolicyService getAddressPolicyManager() {
		return addressPolicyManager;
	}

	public void setAddressPolicyManager(
			AddressPolicyService addressPolicyManager) {
		this.addressPolicyManager = addressPolicyManager;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<PasswordPolicy> getPasswordPolicyList() {
		return passwordPolicyList;
	}

	public void setPasswordPolicyList(List<PasswordPolicy> passwordPolicyList) {
		this.passwordPolicyList = passwordPolicyList;
	}

	public List<TimePolicy> getTimePolicyList() {
		return timePolicyList;
	}

	public void setTimePolicyList(List<TimePolicy> timePolicyList) {
		this.timePolicyList = timePolicyList;
	}

	public List<AddressPolicy> getAddressPolicyList() {
		return addressPolicyList;
	}

	public void setAddressPolicyList(List<AddressPolicy> addressPolicyList) {
		this.addressPolicyList = addressPolicyList;
	}

	public List<AddressPolicy> getAllAddressPolicyList() {
		return allAddressPolicyList;
	}

	public void setAllAddressPolicyList(List<AddressPolicy> allAddressPolicyList) {
		this.allAddressPolicyList = allAddressPolicyList;
	}

	public List<Role> getAllRoleList() {
		return allRoleList;
	}

	public void setAllRoleList(List<Role> allRoleList) {
		this.allRoleList = allRoleList;
	}

	public List<PasswordPolicy> getAllPasswordPolicyList() {
		return allPasswordPolicyList;
	}

	public void setAllPasswordPolicyList(
			List<PasswordPolicy> allPasswordPolicyList) {
		this.allPasswordPolicyList = allPasswordPolicyList;
	}

	public List<TimePolicy> getAllTimePolicyList() {
		return allTimePolicyList;
	}

	public void setAllTimePolicyList(List<TimePolicy> allTimePolicyList) {
		this.allTimePolicyList = allTimePolicyList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getSelUserLoginName() {
		return selUserLoginName;
	}

	public void setSelUserLoginName(String selUserLoginName) {
		this.selUserLoginName = selUserLoginName;
	}

	public String getSelUserRealName() {
		return selUserRealName;
	}

	public void setSelUserRealName(String selUserRealName) {
		this.selUserRealName = selUserRealName;
	}

	public String getSelUserCreatorIp() {
		return selUserCreatorIp;
	}

	public void setSelUserCreatorIp(String selUserCreatorIp) {
		this.selUserCreatorIp = selUserCreatorIp;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPasswordPolicy() {
		return passwordPolicy;
	}

	public void setPasswordPolicy(String passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}

	public String getTimePolicy() {
		return timePolicy;
	}

	public void setTimePolicy(String timePolicy) {
		this.timePolicy = timePolicy;
	}

	public String getAddressPolicy() {
		return addressPolicy;
	}

	public void setAddressPolicy(String addressPolicy) {
		this.addressPolicy = addressPolicy;
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
	
	
	public long getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(long userroleid) {
		this.userroleid = userroleid;
	}
	

	public String getFalg() {
		return falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}

	public String sort(){
		 LOG.info("[UserAction] enter method sort() ...");
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
	            Map<String,Object> map=new HashMap<String, Object>();
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
	            map.put("display", GlobalConfig.DISPLAY_SIGN);
	            actionStr = "field="+field+"&sortType="+map.get("sortType");
	            
	            sr = userManager.sort(map, page);
	            if (sr != null)
	            {
	                userList = sr.getList();
	                for (User u : userList) {

	    				List<Role> r = u.getUserRoleList();
	    			}
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

	@Override
	public void setSession(Map arg0) {
		this.sessionMap = arg0;
	}

	public SendMessage getMsg() {
		return msg;
	}

	public void setMsg(SendMessage msg) {
		this.msg = msg;
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	
	public long getRelAssetGroupId() {
		return RelAssetGroupId;
	}

	public void setRelAssetGroupId(long relAssetGroupId) {
		RelAssetGroupId = relAssetGroupId;
	}

	public List<AssetGroup> getAssetGroupList() {
		return assetGroupList;
	}

	public void setAssetGroupList(List<AssetGroup> assetGroupList) {
		this.assetGroupList = assetGroupList;
	}

	public List<AssetGroup> getAllAssetGroupList() {
		return allAssetGroupList;
	}

	public void setAllAssetGroupList(List<AssetGroup> allAssetGroupList) {
		this.allAssetGroupList = allAssetGroupList;
	}

	public String getAssetGroups() {
		return assetGroups;
	}

	public void setAssetGroups(String assetGroups) {
		this.assetGroups = assetGroups;
	}

	public String getReluserEmail() {
		return reluserEmail;
	}

	public void setReluserEmail(String reluserEmail) {
		this.reluserEmail = reluserEmail;
	}

	public String getReluserState() {
		return reluserState;
	}

	public void setReluserState(String reluserState) {
		this.reluserState = reluserState;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getSecret() {
		return secret;
	}
	  
	
	public String getSyncIp() {
		syncIp = ssManger.query4AIpByKey();
		return syncIp;
	}

	public void setSyncIp(String syncIp) {
		this.syncIp = syncIp;
	}

	public SystemSettingService getSsManger() {
		return ssManger;
	}

	public void setSsManger(SystemSettingService ssManger) {
		this.ssManger = ssManger;
	}

	public String getAccoutIp() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostAddress().toString();
	}

	public void setAccoutIp(String accoutIp) {
	
		this.accoutIp = accoutIp;//获得本机IP
	}

	public long getMyid() {
		return myid;
	}

	public void setMyid(long myid) {
		this.myid = myid;
	}

	public HomePageDivService getHomePageDivManager() {
		return homePageDivManager;
	}

	public void setHomePageDivManager(HomePageDivService homePageDivManager) {
		this.homePageDivManager = homePageDivManager;
	}
	

}
