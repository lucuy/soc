package com.soc.webapp.action.alert;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.alert.AlertInteractionTable;
import com.soc.model.alert.AlertRule;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.securityPolicy.SecurityPolicy;
import com.soc.model.user.User;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.securityPolicy.SecurityPolicyService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * <对警告规则的相关操作：查询规则，添加规则，删除规则> <功能详细描述>
 * 
 * @author jiadongxu
 * @version V100R001C001
 * @see [相关类/方法]
 * @since [事件告警管理模块]
 */
public class AlertRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 规则服务管理类
	private AlertRuleService alertRuleManager;

	// 规则实体类
	private AlertRule alertRule;

	// 规则名称
	private String alertRuleName;

	// 模糊查询关键字
	private String keyword;

	// 编辑角色时页面传递过来的账户的ID串
	private String users;

	// 账户服务管理类
	private UserService userManager;

	// 资产服务管理类
	private AssetService assetManager;

	// 编辑角色时页面传递过来的资产的ID串
	private String assets;

	// 规则ID
	private long ruleId;

	// 获得讲要删除的ID串
	private String ids;

	// 规则名称
	private String ruleName;

	// 关联用户
	private List<Map<String ,Object>> userList;

	// 关联用户
	private List<Map<String, Object>> deviceTypeAllList;
	
	private List<Map<String, Object>> deviceTypeList;
	
	private AlertInteractionTable alertInteractionTableManager;

	// 鍏宠仈鐢ㄦ埛
	private List<Map<String, Object>> assetTypeAllList;
	private List<Map<String, Object>> assetTypeList;
	//前台显示的设备列表
	private List<Asset> assetListDiv;
	
	private List<Map<String, Object>> deviceByNameList;
	
	private List<Map<String, Object>> eventTypeList;
    private List<Map<String, Object>> eventTypeAllList;

	private List<User> allUserList;

	// 关联资产
	private String assetIp;

	private List<Map> assetList;

	private List<Asset> allAssetList;

	// 关联等级
	private List<Map> rankListMail;

	private String[] rankListMails;

	private List<Map> rankListMsm;

	private String[] rankListMsms;

	private List<Map> rankListPlat;

	private String[] rankListPlats;

	private List<Map> rankListSys;

	private String[] rankListSyss;

	// 鏄惁閫夋嫨鍏ㄩ儴璐︽埛
	private int userType;

	// 鏄惁閫夋嫨鍏ㄩ儴璧勪骇
	private int assetType;

	// 鍏宠仈鐢ㄦ埛鍚�
	private String userLoginName;

	// 瑙勫垯鏇存柊鏃ユ湡
	private String ruleUpdateTime;

	// 瑙勫垯鏇存柊鏃ユ湡
	private String ruleCreateTime;

	// 閭欢绛夊瓨鏀剧瓑绾�
	private String rankMail;

	// 鐭俊绛夊瓨鏀剧瓑绾�
	private String rankMsm;

	// 骞冲彴绛夌骇瀛樻斁
	private String rankPlat;

	// syslog绛夌骇瀛樻斁
	private String rankSys;

	private static final String LEVEL_DEFULT = "00000000000";
	// 鑾峰彇鏁版嵁搴撲腑鐨勭瓑绾т覆
	private String defult = "00000000000";
	private String falg;

	// 瀹¤涓氬姟绠＄悊绫�
	private AuditService auditManager;
	
	//鍏宠仈鍏朵粬琛�
	private List<AlertInteractionTable> interactionTableList;
	
	private String deviceByTypeValue;
	private String assetTypeValue;
	private String deviceByNameValue;
	private List<Long> ruleUserId = new ArrayList<Long>();
	// 资产组业务处理类
	private AssetGroupService assetGroupManager;
	
	//安全策略管理业务处理类
	private SecurityPolicyService securityPolicyManager;
	//安全策略list
	private List<SecurityPolicy> policyList;
	
	/**
	 * <鏌ヨ鍛婅瑙勫垯> <鏍规嵁鎺ユ敹鍒扮殑鎼滅储鏉′欢瀹屾垚鍛婅瑙勫垯妫�储>
	 * 
	 * @return String
	 * @see [Page,StringUtil]
	 */
	public String ruleQuery() {
		LOG.info("[AuditAction] enter method query() ...");
		HttpServletRequest request = super.getRequest();

		Page page = null;
		SearchResult sr = null;

		// 澶勭悊鏁版嵁鍒嗛〉鐨勮捣濮嬫潯鏁�
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

		// 鎺ユ敹鏌ヨ鏉′欢锛屽苟瀛樺偍鍒癿ap涓�
		Map<String, Object> map = new HashMap<String, Object>();
		if (request.getParameter("keyword") != null) {
			try {

				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			map.put("keyword", keyword);
		}
		if (StringUtil.isNotBlank(ruleName)) {
			map.put("ruleName", ruleName);
		}
		if (StringUtil.isNotBlank(ruleUpdateTime)) {
			map.put("ruleUpdateTime", ruleUpdateTime);
		}
		if (StringUtil.isNotBlank(ruleCreateTime)) {
			map.put("ruleCreateTime", ruleCreateTime);
		}

		map.put("ruleIdIsDelete", GlobalConfig.NOT_DELETE);
		ruleUserId.add(((User) this.getSession().getAttribute("SOC_LOGON_USER")).getUserId());
		map.put("thisUserId",ruleUserId.get(0));
		sr = alertRuleManager.ruleQuery(map, page);
		if (sr != null) {
			List<AlertRule> ruleList = (List<AlertRule>) sr.getList();
			request.setAttribute("ruleList", ruleList);
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}

		return SUCCESS;
	}

	/**yin
	 * 缂栬緫瑙勫垯 鏌ョ湅瑙勫垯璇︾粏淇℃伅锛屽寘鎷瑙勫垯褰撳墠鍏宠仈鏌ヨ
	 * 
	 * @return String
	 */
	public String ruleEdit() {
	    LOG.info("[AlertRuleAction] enter method ruleEdit() ...");
	    policyList = securityPolicyManager.queryAllSecurityPolicy();
        if(ruleId!=0){
        
            alertRule = alertRuleManager.queryByRuleId(ruleId);

        Map<String, Object> map = new HashMap<String, Object>();
        List<User> searchUser = userManager.query(map);
        if (searchUser != null) {
            allUserList = searchUser;
        }
        /*
         * SearchResult searchAsset = assetManager.query(map, page); if
         * (searchUser != null) { allAssetList =
         * (List<Asset>)searchAsset.getList(); }
         */

        // 取得相关账户信息
//      userList = alertRuleManager.queryRelUser(ruleId);//取用户
        String[] userIdSt = alertRule.getUserId().split(",");
        String[] definitionIdSt = alertRule.getDefinitionId().split(",");
        String[] deviceCategoryIdSt=alertRule.getDeviceCategoryId().split(",");
        String[] assetIdSt=alertRule.getAssetId().split(",");
//      String[] eventType=alertRule.getEventType().split(",");//事件类型
        assetTypeList = new ArrayList<Map<String, Object>>();
        deviceByNameList = new ArrayList<Map<String, Object>>();
        deviceTypeList = new ArrayList<Map<String, Object>>();
        userList = new ArrayList<Map<String, Object>>();
        eventTypeList = new ArrayList<Map<String, Object>>();
        
        for(int i = 0;i<assetIdSt.length;i++){
            
            assetTypeList.addAll(alertRuleManager.queryAssetTypeById(assetIdSt[i]));//事件类型
            
        }
        for(int i = 0 ; i<deviceCategoryIdSt.length;i++){
            
            deviceByNameList.addAll(alertRuleManager.queryDeviceByNameById(deviceCategoryIdSt[i]));//设备名称
        }
        for(int i = 0 ; i<definitionIdSt.length;i++){
            
            deviceTypeList.addAll(alertRuleManager.queryEventType(definitionIdSt[i]));//事件类别alertRuleManager.queryDeviceTypeById(definitionIdSt[i])
        }
        for(int i=0 ; i<userIdSt.length;i++){
            userList.addAll(alertRuleManager.queryUserById(userIdSt[i]));//取用户
        }
        /*for(int i = 0 ;i<eventType.length;i++){
            eventTypeList.addAll(alertRuleManager.queryEventType(eventType[i]));
        }*/
        
        
        //事件类别
        deviceTypeAllList = alertRuleManager.queryAllEventType();//设备类型alertRuleManager.queryDeviceType();
        
        //
        assetTypeAllList = alertRuleManager.queryAssetType();//事件类型
      //获得当前登录用户所管辖的组的id
      		long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid(); 
            assetListDiv = this.assetManager.queryAllAssetByUserId(groupId);
//      eventTypeAllList = alertRuleManager.queryAllEventType();
        // 取得相关资产信息
        // assetList = alertRuleManager.queryRelAsset(ruleId);

        // 取得相关等级的信息
        if (alertRule != null) {
            // 邮件等级
            falg = alertRule.getRuleLevelValue();
            rankListMsms = alertRule.getRuleLevelValue().split(",");

     /*       rankListMails = new String[levelS[0].length()];
            for (int i = 0; i < levelS[0].length(); i++) {
                rankListMails[i] = levelS[0].substring(i, i + 1);
            }

            // 信息等级
            rankListMsms = new String[levelS[1].length()];
            for (int i = 0; i < levelS[1].length(); i++) {
                rankListMsms[i] = levelS[1].substring(i, i + 1);
            }

            // 平台
            rankListPlats = new String[levelS[2].length()];
            for (int i = 0; i < levelS[2].length(); i++) {
                rankListPlats[i] = levelS[2].substring(i, i + 1);
            }

            // syslog
            rankListSyss = new String[levelS[3].length()];
            for (int i = 0; i < levelS[3].length(); i++) {
                rankListSyss[i] = levelS[3].substring(i, i + 1);
            }*/
        }
        List<Map> listUserMap = alertRuleManager.queryRelUserByRuleId(ruleId);
        if (listUserMap.size() > 0) {
            userType = -1;
        }

        /*
         * List<Map> listAssetMap =
         * alertRuleManager.queryRelAssetByRuleId(ruleId); if
         * (listAssetMap.size() > 0) { assetType = -1; }
         */
        }else{
            Map<String, Object> map = new HashMap<String, Object>();
            List<User> searchUser = userManager.query(map);
            if (searchUser != null) {
                allUserList = searchUser;
            }
            
            deviceTypeAllList = alertRuleManager.queryAllEventType();//alertRuleManager.queryDeviceType();
            
            assetTypeAllList = alertRuleManager.queryAssetType();
          //获得当前登录用户所管辖的组的id
      		long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid(); 
      		map.put("assetGroupId", groupId);
            assetListDiv = this.assetManager.query(map);
//          eventTypeAllList = alertRuleManager.queryAllEventType();
            return SUCCESS;
        }
        return SUCCESS;
	}

	/**
	 * <新增/修改规则><功能详细描述>
	 * 
	 * @return String
	 * @see [ruleServiceImpl]
	 * @see [GlobalConfig#IS_NOT_ALL]
	 */
	public String ruleUpdate() {
		LOG.info("[RuleAction] enter method ruleUpdate() ...");
		List<String> fieldList = new ArrayList<String>();
		// 鏇存柊瑙勫垯
		if (alertRule.getRuleId() != 0) {
			if (alertRule.getRuleIdIsDelete() == 0) {
				alertRule.setUserId(users);
				alertRule.setDeviceCategoryId(deviceByNameValue);
				alertRule.setAssetId(assetTypeValue);
				alertRule.setDefinitionId(deviceByTypeValue);
				alertRule.setRuleLevelValue(rankSave());

				// 淇濆瓨鍩烘湰淇℃伅
				long ruleId1 = alertRuleManager.updateRule(alertRule);

				// 鍒犻櫎鐩稿叧淇℃伅
				alertRuleManager.deleteRelUser(ruleId1);
				// alertRuleManager.deleteRelAsset(ruleId1);
				alertRuleManager.deleteRank(ruleId1);
				// 淇濆瓨鍏宠仈璐︽埛淇℃伅
				if (StringUtil.isNotBlank(users)) {
					String[] employeeArray = users.split(",");
					for (int i = 0; i < employeeArray.length; i++) {
						if (StringUtil.isNotEmpty(employeeArray[i])) {

							Map map = new HashMap<String, String>();
							map.put("ruleId", Long.valueOf(ruleId1));

							if (employeeArray[i].equals("-1")) {
								map.put("userId", Long.valueOf("0"));
							} else {
								map.put("userId",
										Long.valueOf(employeeArray[i]));
							}
							alertRuleManager.insertRelUser(map);
						}
					}
				}

				// 鑾峰彇鏇存柊鎿嶄綔鐨勮鍒欏悕绉�
				fieldList.add(alertRule.getRuleName() + "("
						+ alertRule.getRuleName() + ")");

				auditManager.insertByUpdateOperator(((User) this.getSession()
						.getAttribute("SOC_LOGON_USER")).getUserId(), "更改告警规则",
						super.getRequest().getRemoteAddr(), fieldList);

				// syslog
				/*String logString = "";

				logString = "登录名:"
						+ ((User) this.getSession().getAttribute(
								"SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
						+ getRequest().getRemoteAddr() + " 操作时间:"
						+ DateUtil.curDateTimeStr19() + " 操作类型 :更改告警规则";

				logManager.writeSystemAuditLog(logString);*/
				logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改告警规则");
			}

		}
		// 娣诲姞瑙勫垯
		else {
			// 淇濆瓨鍩烘湰淇℃伅
			alertRule.setRuleIdIsDelete(0);
			alertRule.setRuleLevelValue(rankSave());
			alertRule.setUserId(users);
			alertRule.setDeviceCategoryId(deviceByNameValue);
			alertRule.setAssetId(assetTypeValue);
			alertRule.setDefinitionId(deviceByTypeValue);

			
			ruleUserId.add(((User) this.getSession().getAttribute("SOC_LOGON_USER")).getUserId());
    		alertRule.setThisUserId(ruleUserId.get(0));
			
			long ruleId1 = alertRuleManager.updateRule(alertRule);
			
			//鑾悕鍏跺
			// 鍒犻櫎鐩稿叧淇℃伅
			alertRuleManager.deleteRelUser(ruleId1);
			// alertRuleManager.deleteRelAsset(ruleId1);
			alertRuleManager.deleteRank(ruleId1);
			// 淇濆瓨鍏宠仈璐︽埛淇℃伅
			if (StringUtil.isNotBlank(users)) {
				String[] employeeArray = users.split(",");
				for (int i = 0; i < employeeArray.length; i++) {
					if (StringUtil.isNotEmpty(employeeArray[i])) {

						Map map1 = new HashMap<String, String>();
						map1.put("ruleId", Long.valueOf(ruleId1));

						if (employeeArray[i].equals("-1")) {
							map1.put("userId", Long.valueOf("0"));
						} else {
							map1.put("userId",Long.valueOf(employeeArray[i]));
						}
						alertRuleManager.insertRelUser(map1);
					}
				}
			}
			
			// 淇濆瓨鍏宠仈璐︽埛淇℃伅
			/*if (StringUtil.isNotBlank(users)) {
				String[] employeeArray = users.split(",");
				String[] deviceByType = deviceByTypeValue.split(",");
				String[] assetType=assetTypeValue.split(",");
				String[] deviceByName=deviceByNameValue.split(",");
				for (int i = 0; i < employeeArray.length; i++) {
					if (StringUtil.isNotEmpty(employeeArray[i])) {

						Map map = new HashMap<String, String>();
						map.put("ruleId", Long.valueOf(ruleId1));

						if (employeeArray[i].equals("-1")) {
							map.put("userId", Long.valueOf("0"));
						} else {
							map.put("userId", Long.valueOf(employeeArray[i]));
							map.put("deviceByType", Long.valueOf(deviceByType[i]));
							map.put("assetType", Long.valueOf(assetType[i]));
							map.put("deviceByName", Long.valueOf(deviceByName[i]));
						}
						alertRuleManager.insertRelUser(map);
					}
				}
			}*/

			// 淇濆瓨鍏宠仈璧勪骇淇℃伅
			/*
			 * if (StringUtil.isNotBlank(assets)) { String[] idStr =
			 * assets.split(","); for (int i = 0; i < idStr.length; i++) { if
			 * (StringUtil.isNotBlank(idStr[i])) { Map<String, Long> map = new
			 * HashMap<String, Long>(); map.put("ruleId",
			 * Long.valueOf(ruleId1)); map.put("assetId",
			 * Long.valueOf(idStr[i])); alertRuleManager.insertRelAsset(map); }
			 * } }
			 */
			// 鑾峰彇鏇存柊鎿嶄綔鐨勮鍒欏悕绉�
			fieldList.add(alertRule.getRuleName() + "("+ alertRule.getRuleName() + ")");

			// 瀹¤鍏ュ簱
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "告警规则", super
					.getRequest().getRemoteAddr(), fieldList);
			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :添加告警规则";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增告警规则");
		}

		return SUCCESS;
	}

	/**
	 * 鎷兼帴鍛婅绾у埆涓�<鍔熻兘璇︾粏鎻忚堪>
	 * 
	 * @param ruleIdKey
	 *            long
	 * @see [绫汇�绫�鏂规硶銆佺被#鎴愬憳]
	 */
	public String rankSave() {
		StringBuffer levelStr = new StringBuffer();

		// 淇濆瓨閭欢璁剧疆绛夌骇
		if (rankMail != null) {
			levelStr.append(alertRuleManager.setRank(rankMail));
		} else {
			levelStr.append(LEVEL_DEFULT);
		}

/*
 *      // 淇濆瓨鐭俊璁剧疆绛夌骇
		if (rankMsm != null) {
			levelStr.append("," + alertRuleManager.setRank(rankMsm));
		} else {
			levelStr.append("," + LEVEL_DEFULT);
		}

		// 淇濆瓨淇℃伅骞冲彴璁剧疆绛夌骇
		if (rankPlat != null) {
			levelStr.append("," + alertRuleManager.setRank(rankPlat));
		} else {
			levelStr.append("," + LEVEL_DEFULT);
		}

		// 淇濆瓨Syslog璁剧疆绛夌骇
		if (rankSys != null) {
			levelStr.append("," + alertRuleManager.setRank(rankSys));
		} else {
			levelStr.append("," + LEVEL_DEFULT);
		}
*/
		if (rankMail == null) {
			String rankDefult = "0,0,0,0,0";
			levelStr = new StringBuffer();
			levelStr.append(rankDefult);
		}
		return levelStr.toString();
	}

	// 瑙勫垯鍒犻櫎
	/**
	 * 鍒犻櫎瑙勫垯 鍒犻櫎瑙勫垯
	 * 
	 * @return String
	 * @see [绫汇�绫�鏂规硶銆佺被#鎴愬憳]
	 */
	public String deleteRule() {
		List<String> fieldList = new ArrayList<String>();
		// 鑾峰彇瑕佸垹闄ょ殑ruleId
		if (StringUtil.isNotBlank(ids)) {
			// 閽堝澶氫釜瑙勫垯鎿嶄綔
			if (ids.indexOf(",") > 0) {

				String[] checked = ids.split(",");

				// 寰幆閬嶅巻闇�鎵ц鏇存柊鐘舵�鐨勮鍒橧D骞舵墽琛屾洿鏂扮姸鎬佹搷浣�
				for (String checkid : checked) {
					AlertRule alertRuleObject = alertRuleManager
							.queryByRuleId(Long.parseLong(checkid));
					fieldList.add(alertRuleObject.getRuleName() + "("
							+ alertRuleObject.getRuleName() + ")");
					// 鎵归噺娉ㄩ攢瑙勫垯
					alertRuleManager.deleteRule(Long.parseLong(checkid));
					alertRuleManager.deleteRelUser(Long.parseLong(checkid));
					// alertRuleManager.deleteRelAsset(Long.parseLong(checkid));
					alertRuleManager.deleteRank(Long.parseLong(checkid));

				}
			}

			// 鍗曚釜娉ㄩ攢瑙勫垯
			else {
				AlertRule alertRuleObject = alertRuleManager.queryByRuleId(Long
						.parseLong(ids));
				fieldList.add(alertRuleObject.getRuleName() + "("
						+ alertRuleObject.getRuleName() + ")");
				alertRuleManager.deleteRule(Long.parseLong(ids));
				alertRuleManager.deleteRelUser(Long.parseLong(ids));
				// alertRuleManager.deleteRelAsset(Long.parseLong(ids));
				alertRuleManager.deleteRank(Long.parseLong(ids));
			}
			// 瀹¤鍏ュ簱
			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "告警规则", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除告警规则");
		}
		return SUCCESS;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAlertRuleName() {
		return alertRuleName;
	}

	public void setAlertRuleName(String alertRuleName) {
		this.alertRuleName = alertRuleName;
	}

	public AlertRuleService getAlertRuleManager() {
		return alertRuleManager;
	}

	public void setAlertRuleManager(AlertRuleService alertRuleManager) {
		this.alertRuleManager = alertRuleManager;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getAssetIp() {
		return assetIp;
	}

	public void setAssetIp(String assetIp) {
		this.assetIp = assetIp;
	}

	public String getRuleUpdateTime() {
		return ruleUpdateTime;
	}

	public void setRuleUpdateTime(String ruleUpdateTime) {
		this.ruleUpdateTime = ruleUpdateTime;
	}

	public AlertRule getAlertRule() {
		return alertRule;
	}

	public void setAlertRule(AlertRule alertRule) {
		this.alertRule = alertRule;
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

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	public List<User> getAllUserList() {
		return allUserList;
	}

	public void setAllUserList(List<User> allUserList) {
		this.allUserList = allUserList;
	}

	public List<Asset> getAllAssetList() {
		return allAssetList;
	}

	public void setAllAssetList(List<Asset> allAssetList) {
		this.allAssetList = allAssetList;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	public List<Map> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Map> assetList) {
		this.assetList = assetList;
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

	public String getRankMail() {
		return rankMail;
	}

	public void setRankMail(String rankMail) {
		this.rankMail = rankMail;
	}

	public String getRankMsm() {
		return rankMsm;
	}

	public void setRankMsm(String rankMsm) {
		this.rankMsm = rankMsm;
	}

	public String getRankPlat() {
		return rankPlat;
	}

	public void setRankPlat(String rankPlat) {
		this.rankPlat = rankPlat;
	}

	public String getRankSys() {
		return rankSys;
	}

	public void setRankSys(String rankSys) {
		this.rankSys = rankSys;
	}

	public long getRuleId() {
		return ruleId;
	}

	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}

	public List<Map> getRankListMail() {
		return rankListMail;
	}

	public void setRankListMail(List<Map> rankListMail) {
		this.rankListMail = rankListMail;
	}

	public List<Map> getRankListMsm() {
		return rankListMsm;
	}

	public void setRankListMsm(List<Map> rankListMsm) {
		this.rankListMsm = rankListMsm;
	}

	public List<Map> getRankListPlat() {
		return rankListPlat;
	}

	public void setRankListPlat(List<Map> rankListPlat) {
		this.rankListPlat = rankListPlat;
	}

	public List<Map> getRankListSys() {
		return rankListSys;
	}

	public void setRankListSys(List<Map> rankListSys) {
		this.rankListSys = rankListSys;
	}

	public String[] getRankListMsms() {
		return rankListMsms;
	}

	public void setRankListMsms(String[] rankListMsms) {
		this.rankListMsms = rankListMsms;
	}

	public String[] getRankListPlats() {
		return rankListPlats;
	}

	public void setRankListPlats(String[] rankListPlats) {
		this.rankListPlats = rankListPlats;
	}

	public String[] getRankListSyss() {
		return rankListSyss;
	}

	public void setRankListSyss(String[] rankListSyss) {
		this.rankListSyss = rankListSyss;
	}

	public String[] getRankListMails() {
		return rankListMails;
	}

	public void setRankListMails(String[] rankListMails) {
		this.rankListMails = rankListMails;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getRuleCreateTime() {
		return ruleCreateTime;
	}

	public void setRuleCreateTime(String ruleCreateTime) {
		this.ruleCreateTime = ruleCreateTime;
	}



	public String getFalg() {
		return falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}
	public List<AlertInteractionTable> getInteractionTableList() {
		return interactionTableList;
	}

	public void setInteractionTableList(
			List<AlertInteractionTable> interactionTableList) {
		this.interactionTableList = interactionTableList;
	}

	public List<Map<String, Object>> getDeviceTypeAllList() {
		return deviceTypeAllList;
	}

	public void setDeviceTypeAllList(List<Map<String, Object>> deviceTypeAllList) {
		this.deviceTypeAllList = deviceTypeAllList;
	}

	public List<Map<String, Object>> getAssetTypeAllList() {
		return assetTypeAllList;
	}

	public void setAssetTypeAllList(List<Map<String, Object>> assetTypeAllList) {
		this.assetTypeAllList = assetTypeAllList;
	}




	public List<Asset> getAssetListDiv() {
		return assetListDiv;
	}

	public void setAssetListDiv(List<Asset> assetListDiv) {
		this.assetListDiv = assetListDiv;
	}

	public String getDeviceByTypeValue() {
		return deviceByTypeValue;
	}

	public void setDeviceByTypeValue(String deviceByTypeValue) {
		this.deviceByTypeValue = deviceByTypeValue;
	}

	public String getAssetTypeValue() {
		return assetTypeValue;
	}

	public void setAssetTypeValue(String assetTypeValue) {
		this.assetTypeValue = assetTypeValue;
	}

	public String getDeviceByNameValue() {
		return deviceByNameValue;
	}

	public void setDeviceByNameValue(String deviceByNameValue) {
		this.deviceByNameValue = deviceByNameValue;
	}

	public List<Map<String, Object>> getUserList() {
		return userList;
	}

	public void setUserList(List<Map<String, Object>> userList) {
		this.userList = userList;
	}

	public List<Map<String, Object>> getDeviceTypeList() {
		return deviceTypeList;
	}

	public void setDeviceTypeList(List<Map<String, Object>> deviceTypeList) {
		this.deviceTypeList = deviceTypeList;
	}

	public AlertInteractionTable getAlertInteractionTableManager() {
		return alertInteractionTableManager;
	}

	public void setAlertInteractionTableManager(
			AlertInteractionTable alertInteractionTableManager) {
		this.alertInteractionTableManager = alertInteractionTableManager;
	}

	public List<Map<String, Object>> getAssetTypeList() {
		return assetTypeList;
	}

	public void setAssetTypeList(List<Map<String, Object>> assetTypeList) {
		this.assetTypeList = assetTypeList;
	}

	public List<Map<String, Object>> getDeviceByNameList() {
		return deviceByNameList;
	}

	public void setDeviceByNameList(List<Map<String, Object>> deviceByNameList) {
		this.deviceByNameList = deviceByNameList;
	}

	public String getDefult() {
		return defult;
	}

	public void setDefult(String defult) {
		this.defult = defult;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

    public List<Map<String, Object>> getEventTypeList()
    {
        return eventTypeList;
    }

    public void setEventTypeList(List<Map<String, Object>> eventTypeList)
    {
        this.eventTypeList = eventTypeList;
    }

    public List<Map<String, Object>> getEventTypeAllList()
    {
        return eventTypeAllList;
    }

    public void setEventTypeAllList(List<Map<String, Object>> eventTypeAllList)
    {
        this.eventTypeAllList = eventTypeAllList;
    }

	public SecurityPolicyService getSecurityPolicyManager() {
		return securityPolicyManager;
	}

	public void setSecurityPolicyManager(SecurityPolicyService securityPolicyManager) {
		this.securityPolicyManager = securityPolicyManager;
	}

	public List<SecurityPolicy> getPolicyList() {
		return policyList;
	}

	public void setPolicyList(List<SecurityPolicy> policyList) {
		this.policyList = policyList;
	}
    
    

}