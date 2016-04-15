package com.soc.webapp.action.role;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AssetGroup;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.role.Permission;
import com.soc.model.role.Role;
import com.soc.model.user.User;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.role.PermissionService;
import com.soc.service.role.RoleService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <角色Action> <实现对角色的添加、修改、删除、拷贝等功能>
 * 
 * @author 王亚男
 * @version [V100R001C001, 2012-8-6]
 * @see [BaseAction,RoleService]
 * @since
 */
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	// 角色服务管理类
	private RoleService roleManager;

	// 账户服务管理类
	private UserService userManager;

	// 资产服务管理类
	private AssetService assetManager;

	// 资产组服务管理类
	private AssetGroupService assetGroupManager;

	// 权限服务管理类
	private PermissionService permissionManager;

	// 角色对象
	private Role role;

	// 角色列表
	private List<Role> roleList;

	// 角色ID
	private int roleId;

	// 快速搜索关键字
	private String keyword;

	// 批量操作时角色ID串
	private String ids;

	// 角色已关联账户列表
	private List<Map> userList;

	// 角色已关联资产列表
	private List<Map> assetList;

	// 角色已关联资产组列表
	private List<Map> assetGroupList;

	// 角色已关联权限列表
	private List<Map> relPermissionList;

	// 所有账户列表
	private List<User> allUserList;

	// 所有资产列表
	private List<Asset> allAssetList;

	// 所有资产组列表
	private List<AssetGroup> allAssetGroupList;

	// 所有权限
	private List<Permission> allPermissionList;

	// 编辑角色时页面传递过来的账户的ID串
	private String users;

	// 编辑角色时页面传递过来的资产的ID串
	private String assets;

	// 编辑角色时页面传递过来的资产组的ID串
	private String assetGroups;

	// 是否选择全部账户
	private int userType;

	// 是否选择全部资产
	private int assetType;

	// 是否选择所有资产组
	private int assetGroupType;

	// 角色名称
	private String roleName;

	//
	private String roles;

	// 高级检索-角色名
	private String selRoleName;

	// 高级检索-创建者
	private String selRoleUserLoginName;

	// 审计业务类
	private AuditService auditManager;
	
	//请求的action字符串
    private String actionStr ="queryRole.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ; 
    private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * <查询角色信息> <功能详细描述>
	 * 
	 * @return String
	 * @see [RoleServiceImpl#queryRole]
	 */
	public String queryRole() {
		LOG.info("[RoleAction] enter method queryRole() ...");

		HttpServletRequest request = super.getRequest();

		// 存储数据的变量定义
		Page page = null;
		SearchResult sr = null;
		role = new Role();

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
		if (StringUtil.isNotBlank(selRoleName)) {
			map.put("selRoleName", selRoleName);
		}
		if (StringUtil.isNotBlank(selRoleUserLoginName)) {
			map.put("selRoleUserLoginName", selRoleUserLoginName);
		}

		map.put("roleDisplay", GlobalConfig.DISPLAY_SIGN);

		// 根据role存储的信息查询角色
		sr = roleManager.queryRole(map, page);

		if (sr != null) {
			roleList = (List<Role>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}

		return SUCCESS;
	}

	/**
	 * <编辑角色信息> <功能详细描述>
	 * 
	 * @return String
	 * @see [RoleServiceImpl#queryByRoleId]
	 * @see [UserServiceImpl#query]
	 * @see [AssetServiceImpl#query]
	 * @see [AssetGroupServiceImpl#query]
	 */
	public String editRole() {
		LOG.info("[RoleAction] enter method editRole() ...");

		// 取得权限列表
		allPermissionList = permissionManager.queryPermission();

		// 取得基本信息
		role = roleManager.queryByRoleId(roleId);

		// 取得所有账户信息
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		/*List<User> searchUser = userManager.query(map);
		if (searchUser != null) {
			allUserList = searchUser;
		}
*/
		// 取得所有资产信息
		/*SearchResult searchAsset = assetManager.query(map, page);
		if (searchUser != null) {
			allAssetList = (List<Asset>) searchAsset.getList();
		}

		// 取得所有资产信息
		SearchResult searchAssetGroup = assetGroupManager.query(map, page);
		if (searchUser != null) {
			allAssetGroupList = (List<AssetGroup>) searchAssetGroup.getList();
		}*/

		/*// 取得相关账户信息
		userList = roleManager.queryRelUser(roleId);

		// 取得相关资产信息
		assetList = roleManager.queryRelAsset(roleId);

		// 取得相关资产组信息
		assetGroupList = roleManager.queryRelAssetGroup(roleId);
*/
		// 取得相关权限信息
		relPermissionList = roleManager.queryRelPermission(roleId);

		/*List<Map> listUserMap = roleManager.queryRelUserByRoleId(roleId);

		if (listUserMap.size() > 0) {
			userType = -1;
		}

		List<Map> listAssetMap = roleManager.queryRelAssetByRoleId(roleId);
		
		if (listAssetMap.size() > 0) {
			assetType = -1;
		}
		
		List<Map> listAssetGroupMap = roleManager
				.queryRelAssetGroupByRoleId(roleId);
		if (listAssetGroupMap.size() > 0) {
			assetGroupType = -1;
		}*/
		
		return SUCCESS;
	}

	/**
	 * <新增/修改角色> <功能详细描述>
	 * 
	 * @return String
	 * @see [RoleServiceImpl]
	 * @see [GlobalConfig#IS_NOT_ALL]
	 */
	public String updateRole() {
		LOG.info("[RoleAction] enter method updateRole() ...");

		// 获取权限ID
		HttpServletRequest request = super.getRequest();
		String[] permissionIds = request.getParameterValues("item");
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(role.getRoleName() + "(" + role.getRoleName() + ")");

		// 更新角色
		if (role.getRoleId() != 0) {

			if (role.getRoleDisplay() == 0) {
				// 保存基本信息
				Role role1 = roleManager.queryByRoleName(role.getRoleName());
				
					errorMessage="";
				long roleId1 = roleManager.updateRole(role);

				// 删除相关信息
				roleManager.deleteRelUser(roleId1);
				roleManager.deleteRelAsset(roleId1);
				roleManager.deleteRelAssetGroup(roleId1);
				roleManager.deleteRelPermission(roleId1);

				// 保存关联账户信息(改动过)
				if (StringUtil.isNotBlank(users)) {
					String[] employeeArray = users.split(",");
					for (int i = 0; i < employeeArray.length; i++) {
						if (StringUtil.isNotEmpty(employeeArray[i])) {

							Map map = new HashMap<String, String>();
							map.put("roleId", Long.valueOf(roleId1));

							if (employeeArray[i].equals("-1")) {
								map.put("userId", Long.valueOf("0"));
								map.put("isAllUser", GlobalConfig.IS_ALL);
							} else {
								map.put("userId",
										Long.valueOf(employeeArray[i]));
								map.put("isAllUser", GlobalConfig.IS_NOT_ALL);
							}
							roleManager.insertRelUser(map);
						}
					}
				}

				// 保存关联资产信息
				if (StringUtil.isNotBlank(assets)) {
					String[] idStr = assets.split(",");

					for (int i = 0; i < idStr.length; i++) {
						if (StringUtil.isNotBlank(idStr[i])) {
							Map map = new HashMap<String, String>();
							map.put("roleId", Long.valueOf(roleId1));
							if (idStr[i].equals("-1")) {
								map.put("assetId", Long.valueOf(idStr[i]));
								map.put("isAllAsset", GlobalConfig.IS_ALL);
							} else {
								map.put("assetId", Long.valueOf(idStr[i]));
								map.put("isAllAsset", GlobalConfig.IS_NOT_ALL);
							}
							roleManager.insertRelAsset(map);
						}
					}
				}

				// 保存关联资产组信息
				if (StringUtil.isNotBlank(assetGroups)) {
					String[] idStr = assetGroups.split(",");
					for (int i = 0; i < idStr.length; i++) {
						if (StringUtil.isNotEmpty(idStr[i])) {
							Map map = new HashMap<String, String>();
							map.put("roleId", Long.valueOf(roleId1));
							if (idStr[i].equals("-1")) {
								map.put("assetGroupId", Long.valueOf(idStr[i]));
								map.put("relIsAllAssetGroup",
										GlobalConfig.IS_ALL);
							} else {
								map.put("assetGroupId", Long.valueOf(idStr[i]));
								map.put("relIsAllAssetGroup",
										GlobalConfig.IS_NOT_ALL);

							}

							roleManager.insertRelAssetGroup(map);
						}
					}
				}

				// 保存关联权限信息
				if (permissionIds != null) {
					for (int i = 0; i < permissionIds.length; i++) {
						if (StringUtil.isNotEmpty(permissionIds[i])) {
							Map<String, Long> map = new HashMap<String, Long>();
							map.put("roleId", Long.valueOf(roleId1));
							map.put("permissionId",
									Long.valueOf(permissionIds[i]));
							roleManager.insertRelPermission(map);
						}
					}
				}
			

			// 内部审计
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "角色", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog日志
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:修改角色";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改角色");
				}
		
		}

		// 添加角色
		else {
			role.setRoleDisplay(1);
			role.setRoleUserLoginName(((User) request.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserLoginName());
			//检查角色名称是否存在
			Role role1 = roleManager.queryByRoleName(role.getRoleName());
			
			if(role1!=null){//此处已经在前台页面禁用了保存按钮，防止跳过前台验证依然要保存。如果角色名称存在直接跳会新增页面
				errorMessage="用户名已存在！请重新输入";
				allPermissionList = permissionManager.queryPermission();
				return "error";
			}else{
				// 保存基本信息
				errorMessage="";
			long roleId1 = roleManager.updateRole(role);

			// 保存关联账户信息(改动过)
			if (StringUtil.isNotBlank(users)) {
				String[] employeeArray = users.split(",");
				for (int i = 0; i < employeeArray.length; i++) {
					if (StringUtil.isNotEmpty(employeeArray[i])) {

						Map map = new HashMap<String, String>();
						map.put("roleId", Long.valueOf(roleId1));

						if (employeeArray[i].equals("-1")) {
							map.put("userId", Long.valueOf("0"));
							map.put("isAllUser", GlobalConfig.IS_ALL);
						} else {
							map.put("userId", Long.valueOf(employeeArray[i]));
							map.put("isAllUser", GlobalConfig.IS_NOT_ALL);
						}
						roleManager.insertRelUser(map);
					}
				}
			}

			// 保存关联资产信息
			if (StringUtil.isNotBlank(assets)) {
				String[] idStr = assets.split(",");
				for (int i = 0; i < idStr.length; i++) {
					if (StringUtil.isNotBlank(idStr[i])) {
						Map<String, Long> map = new HashMap<String, Long>();
						map.put("roleId", Long.valueOf(roleId1));
						map.put("assetId", Long.valueOf(idStr[i]));
						map.put("isAllAsset",
								Long.valueOf(GlobalConfig.IS_NOT_ALL));
						roleManager.insertRelAsset(map);
					}
				}
			}

			// 保存关联资产组信息
			if (StringUtil.isNotBlank(assetGroups)) {
				String[] idStr = assetGroups.split(",");
				for (int i = 0; i < idStr.length; i++) {
					if (StringUtil.isNotEmpty(idStr[i])) {
						Map map = new HashMap<String, String>();
						map.put("roleId", Long.valueOf(roleId1));
						if (idStr[i].equals("-1")) {
							map.put("assetGroupId", Long.valueOf(idStr[i]));
							map.put("relIsAllAssetGroup", GlobalConfig.IS_ALL);
						} else {
							map.put("assetGroupId", Long.valueOf(idStr[i]));
							map.put("relIsAllAssetGroup",
									GlobalConfig.IS_NOT_ALL);

						}

						roleManager.insertRelAssetGroup(map);
					}
				}
			}

			// 保存关联权限信息
			if (permissionIds != null) {
				for (int i = 0; i < permissionIds.length; i++) {
					if (StringUtil.isNotEmpty(permissionIds[i])) {
						Map<String, Long> map = new HashMap<String, Long>();
						map.put("roleId", Long.valueOf(roleId1));
						map.put("permissionId", Long.valueOf(permissionIds[i]));

						roleManager.insertRelPermission(map);
					}
				}
			}
		
			// 内部审计
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "角色", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog日志
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:添加角色";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "添加角色");
			}
		}
	
		return SUCCESS;
	}

	/**
	 * <删除角色> <功能详细描述>
	 * 
	 * @return String
	 * @see [RoleServiceImpl]
	 */
	public String deleteRole() {
		LOG.info("[RoleAction] enter method deleteRole() ...");
		List<String> fieldList = new ArrayList<String>();
		if (StringUtil.isNotBlank(ids)) {
			// 针对多个账户操作
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");

				// 循环遍历需要执行更新状态的角色ID并执行更新状态操作
				for (String checkid : checked) {
					Role roleObject = roleManager.queryByRoleId(Long
							.parseLong(checkid));
					fieldList.add(roleObject.getRoleName() + "("
							+ roleObject.getRoleName() + ")");
					// 批量注销角色
					if (roleObject.getRoleDisplay() != GlobalConfig.HIDEN_SIGN) {
						roleManager.deleteRole(Long.parseLong(checkid));
					}
				}
			}

			// 针对单个角色操作
			else {
				Role roleObject = roleManager
						.queryByRoleId(Long.parseLong(ids));
				fieldList.add(roleObject.getRoleName() + "("
						+ roleObject.getRoleName() + ")");
				if (roleObject.getRoleDisplay() != GlobalConfig.HIDEN_SIGN) {
					roleManager.deleteRole(Long.parseLong(ids));
				}
			}

			// 内部审计
			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "角色", super
					.getRequest().getRemoteAddr(), fieldList);

			// 删除角色
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:删除角色";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除角色");
		}

		return SUCCESS;
	}

	/**
	 * <检测角色名称> <确定角色名称的唯一性>
	 * 
	 * @see [RoleServiceImpl]
	 */
	public void checkRoleName() {
		LOG.info("[RoleAction] enter method checkRoleName() ...");

		// 标识此角色名是否存在
		String flag = "false";

		// 根据角色名查找角色
		if (StringUtil.isNotBlank(roleName)) {
			Role role1 = roleManager.queryByRoleName(roleName);

			// 查找到角色，将标识flag设置为true
			if (role1 != null) {
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
	 * <角色拷贝> <将已存在的角色拷贝出一个副本>
	 * 
	 * @return String
	 * @see [RoleServiceImpl]
	 */
	public String copyRole() {
		LOG.info("[RoleAction] enter method copyRole() ...");

		HttpServletRequest request = getRequest();

		// 获取页面传递过来的copyRoleName的值
		String roleName=null;
		try {
			roleName = java.net.URLDecoder.decode(
					request.getParameter("copyRoleName"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		role = roleManager.queryByRoleName(roleName);
		if(role==null){
			
		
		Role roleCopy = new Role();
		roleCopy.setRoleName(roleName);
		roleCopy.setRoleDisplay(1);
		roleCopy.setRoleCreateDateTime(new Date());
		roleCopy.setRoleUpdateDateTime(new Date());
		roleCopy.setRoleUserLoginName(((User) request.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserLoginName());

		

		// 得到源角色的id
		long copyRoleId = Long.parseLong(request.getParameter("copyRoleId"));
		Role r =roleManager.queryByRoleId(copyRoleId);
		// 执行新建角色
		roleCopy.setRoleMemo(r.getRoleMemo());
				long newRoleId = roleManager.updateRole(roleCopy);
		// 根据源角色id得到关联信息
		List<Map> copyUserList = roleManager.queryRelUser(copyRoleId);
		List<Map> copyAssetList = roleManager.queryRelAsset(copyRoleId);
		List<Map> copyAssetGroupList = roleManager
				.queryRelAssetGroup(copyRoleId);
		List<Map> copyPermissionList = roleManager
				.queryRelPermission(copyRoleId);

		// 向新角色添加关联账户信息
		if (copyUserList.size() > 0) {
			for (Map copyUser : copyUserList) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("roleId", Long.valueOf(newRoleId));
				map.put("userId",
						Long.valueOf(String.valueOf(copyUser.get("reluserid"))));
				map.put("isAllUser", Long.valueOf(GlobalConfig.IS_NOT_ALL));
				roleManager.insertRelUser(map);
			}
		}

		// 向新角色添加关联资产信息
		if (copyAssetList.size() > 0) {
			for (Map copyAsset : copyAssetList) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("roleId", Long.valueOf(newRoleId));
				map.put("assetId",
						Long.valueOf(String.valueOf(copyAsset.get("relid"))));
				map.put("isAllAsset", Long.valueOf(GlobalConfig.IS_NOT_ALL));

				roleManager.insertRelAsset(map);
			}
		}

		// 向新角色添加关联资产组信息
		if (copyAssetGroupList.size() > 0) {
			for (Map<String, Long> copyAssetGroup : copyAssetGroupList) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("roleId", Long.valueOf(newRoleId));
				map.put("assetGroupId", copyAssetGroup.get("relid"));

				roleManager.insertRelAssetGroup(map);
			}
		}

		// 向新角色添加关联权限信息
		if (copyPermissionList.size() > 0) {
			for (Map<String, Long> copyPermission : copyPermissionList) {
				Map<String, Long> map = new HashMap<String, Long>();
				map.put("roleId", Long.valueOf(newRoleId));
				map.put("permissionId", copyPermission.get("relpermssionid"));

				roleManager.insertRelPermission(map);
			}
		}

		// 内部审计
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(roleCopy.getRoleName() + "("
				+ r.getRoleName() + ")");
		auditManager.insertBySystemOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "copy角色", super
				.getRequest().getRemoteAddr(), fieldList);

		// 删除角色
		/*String logString = "";
		logString = "登录名："
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + "  源IP:"
				+ getRequest().getRemoteAddr() + "   操作时间："
				+ DateUtil.curDateTimeStr19() + "   操作类型:copy角色";

		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "copy角色");
		}
		return SUCCESS;
	}
	
	/**
	 * <排序>
	 * 
	 * @return String
	 * @see [RoleServiceImpl#queryRole]
	 */
	public String sort(){
		    LOG.info("[RoleAction] enter method sort() ...");
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
	    		map.put("roleDisplay", GlobalConfig.DISPLAY_SIGN);
	            
	            actionStr = "field="+field+"&sortType="+map.get("sortType");
	            
	            sr = roleManager.sort(map, page);
	            if (sr != null)
	            {
	            	roleList = sr.getList();
	                request.setAttribute("roleList", roleList);
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

	public RoleService getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleService roleManager) {
		this.roleManager = roleManager;
	}

	public PermissionService getPermissionManager() {
		return permissionManager;
	}

	public void setPermissionManager(PermissionService permissionManager) {
		this.permissionManager = permissionManager;
	}

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	public List<Asset> getAllAssetList() {
		return allAssetList;
	}

	public void setAllAssetList(List<Asset> allAssetList) {
		this.allAssetList = allAssetList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public List<Permission> getAllPermissionList() {
		return allPermissionList;
	}

	public void setAllPermissionList(List<Permission> allPermissionList) {
		this.allPermissionList = allPermissionList;
	}

	public List<AssetGroup> getAllAssetGroupList() {
		return allAssetGroupList;
	}

	public void setAllAssetGroupList(List<AssetGroup> allAssetGroupList) {
		this.allAssetGroupList = allAssetGroupList;
	}

	public List<Map> getRelPermissionList() {
		return relPermissionList;
	}

	public void setRelPermissionList(List<Map> relPermissionList) {
		this.relPermissionList = relPermissionList;
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

	public List<Map> getUserList() {
		return userList;
	}

	public void setUserList(List<Map> userList) {
		this.userList = userList;
	}

	public List<Map> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Map> assetList) {
		this.assetList = assetList;
	}

	public List<Map> getAssetGroupList() {
		return assetGroupList;
	}

	public void setAssetGroupList(List<Map> assetGroupList) {
		this.assetGroupList = assetGroupList;
	}

	public List<User> getAllUserList() {
		return allUserList;
	}

	public void setAllUserList(List<User> allUserList) {
		this.allUserList = allUserList;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getAssets() {
		return assets;
	}

	public void setAssets(String assets) {
		this.assets = assets;
	}

	public String getAssetGroups() {
		return assetGroups;
	}

	public void setAssetGroups(String assetGroups) {
		this.assetGroups = assetGroups;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getAssetType() {
		return assetType;
	}

	public void setAssetType(int assetType) {
		this.assetType = assetType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getSelRoleName() {
		return selRoleName;
	}

	public void setSelRoleName(String selRoleName) {
		this.selRoleName = selRoleName;
	}

	public String getSelRoleUserLoginName() {
		return selRoleUserLoginName;
	}

	public void setSelRoleUserLoginName(String selRoleUserLoginName) {
		this.selRoleUserLoginName = selRoleUserLoginName;
	}

	public int getAssetGroupType() {
		return assetGroupType;
	}

	public void setAssetGroupType(int assetGroupType) {
		this.assetGroupType = assetGroupType;
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
	
	
}
