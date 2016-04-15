package com.soc.webapp.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.role.Permission;
import com.soc.model.role.Role;
import com.soc.model.serial.Serial;
import com.soc.service.role.PermissionService;
import com.soc.service.role.RoleService;
import com.util.StringUtil;

public class RoleFilter implements Filter {

	String ROLE_LIMIT;// 用户无权限页面

	public RoleFilter() {
		ROLE_LIMIT = "/soc/pages/commons/403.jsp";
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String url = httpRequest.getRequestURI();
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// 下面的页面直接通过

		if (url.contains("/soc/pages/login.jsp")
				|| url.contains("/login/logout.action")
				|| url.contains("/login/checkSingle.action")
				/* || url.contains("/login/check.action") */
				|| url.contains("/frame.action")
				|| url.contains("/frameHeader.action")
				|| url.contains("/frameBottom.action")
				|| url.contains("/login/changePassword.action")
				|| url.contains("/user/checkPwdPolicy.action")
				|| (url.contains("/login/checkPwdPolicy.action") || url
						.contains("/user/checkPassword.action"))
				|| url.contains("/user/personSeting.action")
				|| url.contains("user/userInfoSeting.action")
				|| url.contains("home/queryHome.action")
				|| url.contains("/home/queryCollectorList.action")
				|| url.contains("/home/queryAlertMessageList.action")
				|| url.contains("/home/savePosition.action")
				|| url.contains("/vulnerability/showUpdatePercent.action")
				|| url.contains("/home/queryHomePageDiv")
				|| url.contains("/home/saveHomePageDiv")
				|| url.contains("/home/queryTenAlarmMessage.action")
				|| url.contains("/indexscreen/queryAll")
				|| url.contains("/comprehensive/queryAll")
				|| url.contains("/comprehensive/querybigfacility")
				|| url.contains("/comprehensive/querybigsafety")
				|| url.contains("/comprehensive/querybigrisk")
				|| url.contains("/comprehensive/querybigsonrisk")
				|| url.contains("/comprehensive/queryfacility")
				|| url.contains("/comprehensive/querysafety")
				|| url.contains("/events/queryEventScreen.action")
				|| url.contains("/vulnerability/showUpdatePercent.action")
				|| url.contains("/home/test.action")
				|| url.contains("/files/filesshow.action")
				|| url.contains("/files/downloadfile.action")
				|| url.contains("/alertMessage") /*|| url.contains("/device")
                || url.contains("/netBackGroundPhoto")*/
				|| url.contains("/serial") || url.contains("/about")
				/* || url.contains("/login/check.action") */
				|| url.contains("/indexscreen/upCountChart.action")
				|| url.contains("/events/eventsDetails.action")
				|| url.contains("/events/queryKeyByCategoryName.action")
				|| url.contains("/home/queryEventsTrend.action")
				|| url.contains("/events/editCustom.action")
				|| url.contains("/asset/queryAllCollector.action")
				|| url.contains("/workOrder/query.action")
				|| url.contains("/workOrder/queryUnfinshWorkOrderCount.action")
				|| url.contains("/alertMessage/queryNotCloseAlertMessage.action")
				|| url.contains("/alertMessage/closeAlaemMessage.action")
				|| url.contains("/alertSetting/queryAlertTrance.action")
				)
		{

			chain.doFilter(request, response);
			return;
		}

		if (url.contains("/login/check.action")) {
			if (GlobalConfig.keyFlag != 1) {
				Process process;
				BufferedReader br = null;
				String hardwareCode;
				
				process = Runtime.getRuntime().exec(GlobalConfig.keyOrFile);

				br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));

				hardwareCode = br.readLine();

				// 判断是否有注册码存在
				if (Serial.SERIAL_SN == null || hardwareCode == null
						|| (!Serial.SERIAL_SN.equals(hardwareCode))) {
					httpResponse.sendRedirect("/soc/serial/initSerial.action");
					return;
				}

				// 判断注册码是否过期
				if (Serial.SERIAL_RESOURCE_NUM == 0
						|| ((new Date().getTime() / 1000 - Serial.SERIAL_GEN_TIME) > (Serial.SERIAL_AUTH_DAY * 24 * 60 * 60))) {
					// 将处理事件的标示置为0，不处理事件

					GlobalConfig.lienceFlag = 0;

					httpResponse.sendRedirect("/soc/serial/initSerial.action");
					return;
				} else {
					chain.doFilter(request, response);
					return;
				}
			} else {
				chain.doFilter(request, response);
				return;
			}
		}

		if ((Role) session.getAttribute("SOC_LOGON_ROLE") != null) {
			List<Permission> permissionList = ((Role) session
					.getAttribute("SOC_LOGON_ROLE")).getPermissionList();

			if (permissionList.size() > 0) {
				boolean flag = false;
				for (Permission p : permissionList) {
					if (StringUtil.isNotBlank(p.getPermissionUrl())) {
						if (compareUrl(p.getPermissionUrl(), url)) {
							flag = true;
							break;
						}
						flag = false;
					}
				}

				if (!flag) {
					httpResponse.sendRedirect(ROLE_LIMIT);
					return;
				} else {
					chain.doFilter(request, response);
					return;
				}
			}
		}

	}

	/**
	 * <比较url> <功能详细描述>
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public boolean compareUrl(String str1, String str2) {
		boolean flag = false;
		if (str1.indexOf(";") != -1) {
			String[] str = str1.split(";");
			for (int i = 0; i < str.length; i++) {
				if (str2.contains("/soc" + str[i])) {
					flag = true;
					break;
				}
				flag = false;
			}
		} else {
			if (str2.contains("/soc" + str1)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * <得到当前登录的账户的角色> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Role limit(List<Role> roleList, HttpSession session) {
		// 得到注入service类
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(session.getServletContext());

		RoleService roleManager = (RoleService) wac.getBean("roleManager");
		PermissionService permissionManager = (PermissionService) wac
				.getBean("permissionManager");

		// 改动过
		int UserAllflag = 0; // 是否关联所有用户
		int AssetAllflag = 0; // 是否关联所有资产
		int AssetGroupAllflag = 0; // 是否关联所有资产组
		String limitUserIds = "";
		String limitAssetIds = "";
		String limitAssetGroupIds = "";
		Role limitRole = new Role();
		List<Map> userList;
		List<Map> assetList;
		List<Map> assetGroupList;
		limitRole.setRoleName(roleList.get(0).getRoleName());// 应用top页面显示用户的角色信息
		limitRole.setRoleNames(this.getRoleNames(roleList));// 得到登录用户的角色名称串
		List<Permission> permissionList = new ArrayList<Permission>();

		List<Permission> allPermissionList = permissionManager
				.queryPermission();

		for (Role role : roleList) {
			List<Permission> rolePermissionList = role.getPermissionList();
			for (Permission p1 : rolePermissionList) {
				if (permissionList.size() > 0) {
					for (Permission p2 : permissionList) {
						if (p1.getPermissionId() != p2.getPermissionId()) {
							permissionList.add(p1);
							break;
						}
					}
				} else {
					permissionList.add(p1);
				}
			}

			// 角色关联的用户
			userList = roleManager.queryRelUserByRoleId(role.getRoleId());
			// 判断角色是否关联所有用户
			if (userList.size() > 0) {
				limitUserIds = "select \"USER_ID\" from \"tbl_user\"";
			} else {
				// 对用户的关联多个角色处理
				if (StringUtil.isBlank(limitUserIds)) {
					limitUserIds = this.getUserIds(role, roleManager);
				} else {
					if (!limitUserIds
							.equals("select \"USER_ID\" from \"tbl_user\"")) {
						if (!this.getUserIds(role, roleManager).equals("")) {
							limitUserIds = limitUserIds + ","
									+ this.getUserIds(role, roleManager);
						}
					}
				}
			}

			// 角色关联的资产(未完全)
			assetList = roleManager.queryRelAssetByRoleId(role.getRoleId());
			if (assetList.size() > 0) {
				limitAssetIds = "0";
			} else {
				// 对用户的关联多个资产处理
				if (StringUtil.isBlank(limitAssetIds)) {
					limitAssetIds = getAssetIds(role, roleManager);
				} else {
					if (!limitAssetIds.equals("0")) {
						if (!getAssetIds(role, roleManager).equals("")) {
							limitAssetIds = limitAssetIds + ","
									+ getAssetIds(role, roleManager);
						}
					}

				}
			}
			// 角色关联的资产组
			assetGroupList = roleManager.queryRelAssetGroupByRoleId(role
					.getRoleId());
			if (assetGroupList.size() > 0) {
				limitAssetGroupIds = "-1";
			} else {
				if (StringUtil.isBlank(limitAssetGroupIds)) {
					limitAssetGroupIds = getAssetGroupIds(role, roleManager);
				} else {
					if (!limitAssetGroupIds.equals("-1")) {
						if (!getAssetGroupIds(role, roleManager).equals("")) {
							limitAssetGroupIds = limitAssetGroupIds + ","
									+ getAssetGroupIds(role, roleManager);

						}
					}
				}
			}
			// 数据权限
			/*
			 * String userIds = getUserIds(role, roleManager);
			 * 
			 * if(StringUtil.isNotBlank(userIds)) {
			 * limitRole.setUserIds(userIds); }
			 * 
			 * String assetIds = getAssetIds(role, roleManager);
			 * if(StringUtil.isNotBlank(assetIds)) {
			 * limitRole.setAssetIds(assetIds); }
			 * 
			 * String assetGroupIds = getAssetGroupIds(role, roleManager);
			 * if(StringUtil.isNotBlank(assetGroupIds)) {
			 * limitRole.setAssetGroupIds(assetGroupIds); }
			 */
			// userList=
			// userList =
		}

		if (permissionList.size() > 0) {
			for (Permission p : allPermissionList) {
				boolean flag = false;
				for (Permission p1 : p.getPermissionModuleList()) {
					if (compareList(permissionList,
							p1.getPermissionModuleList())) {
						permissionList.add(p1);
						flag = true;
						continue;
					}
				}
				if (flag) {
					permissionList.add(p);
					continue;
				}
			}
		}

		limitRole.setPermissionList(permissionList);

		// 设置角色关联的用户ids;
		// limitRole.setUserIds(limitUserIds);

		// 设置角色关联的资产ids;
		// limitRole.setAssetIds(limitAssetIds);

		// 设置角色关联的资产组
		// limitRole.setAssetGroupIds(limitAssetGroupIds);

		return limitRole;
	}

	public boolean compareList(List<Permission> permissionList1,
			List<Permission> permissionList2) {
		boolean flag = false;
		for (Permission p2 : permissionList2) {
			flag = false;
			for (Permission p1 : permissionList1) {
				if (p1.getPermissionId() == p2.getPermissionId()) {
					flag = true;
					break;
				}
				flag = false;
			}

			if (flag) {
				break;
			}
		}

		return flag;
	}

	/**
	 * <得到角色关联的账户的id串> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getUserIds(Role role, RoleService roleManager) {
		StringBuffer limitUser = new StringBuffer();

		List<Map> userList = roleManager.queryRelUser(role.getRoleId());

		if (userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {

				Map map = userList.get(i);
				// //System.out.println(map.get("relUserId"));
				if (i == userList.size() - 1) {
					limitUser.append(map.get("reluserid"));
				} else {
					limitUser.append(map.get("reluserid") + ",");
				}
			}
		}
		return limitUser.toString();
	}

	/**
	 * <得到角色关联的资产的id串> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getAssetIds(Role role, RoleService roleManager) {
		StringBuffer limitAsset = new StringBuffer();

		List<Map> assetList = roleManager.queryRelAsset(role.getRoleId());

		if (assetList.size() > 0) {
			for (int i = 0; i < assetList.size(); i++) {
				Map map = assetList.get(i);
				if (i == assetList.size() - 1) {
					limitAsset.append(map.get("relid"));
				} else {
					limitAsset.append(map.get("relid") + ",");
				}
			}
		}
		return limitAsset.toString();
	}

	/**
	 * <得到角色关联的资产组的id串> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getAssetGroupIds(Role role, RoleService roleManager) {
		StringBuffer limitAssetGroup = new StringBuffer();

		List<Map> assetGroupList = roleManager.queryRelAssetGroup(role
				.getRoleId());

		if (assetGroupList.size() > 0) {
			for (int i = 0; i < assetGroupList.size(); i++) {
				Map map = assetGroupList.get(i);
				if (i == assetGroupList.size() - 1) {
					limitAssetGroup.append(map.get("relid"));
				} else {
					limitAssetGroup.append(map.get("relid") + ",");
				}
			}
		}
		return limitAssetGroup.toString();
	}

	/**
	 * <得到登录账户的角色名称串> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getRoleNames(List<Role> roleList) {
		StringBuffer roleNames = new StringBuffer();
		for (Role role : roleList) {
			if (roleNames.length() == 0) {
				roleNames.append(role.getRoleName());
			} else {
				roleNames.append("," + role.getRoleName());
			}
		}
		return roleNames.toString();
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
