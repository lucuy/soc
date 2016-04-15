package com.util.reportForm.struts.form;


import java.util.HashMap;
import java.util.Map;

public class DataSource {
	/** 存储主要类型 */
	private Map<String, String> majorType = new HashMap<String, String>();
	/** 存储次要类型 */
	private Map<String, String> minorType;
	/** 存储主要次要类型集合 */
	private Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

	public DataSource() {
		initMajorType();
		initMinorType();
	}

	public Map<String, String> getMajorType() {
		return majorType;
	}

	public Map<String, Map<String, String>> getMap() {
		return map;
	}

	/***************************************************************************
	 * 初始化主要类型
	 */
	private void initMajorType() {
		majorType.put("Apache", "Apache信息");
		majorType.put("IIS", "IIS信息");
		majorType.put("Tomcat", "Tomcat信息");
		majorType.put("WebLogic", "WebLogic信息");
		majorType.put("Oracle", "Oracle信息");
		majorType.put("SQLServer", "SQLServer信息");
		majorType.put("MySQL", "MySQL信息");
		majorType.put("ShareInfo", "共享信息");
		majorType.put("OsUserInfo", "系统帐户信息");
		//majorType.put("OSEvent", "OS事件");
		majorType.put("SystemEvent", "OS系统事件");
		majorType.put("SafeEvent", "OS安全事件");
		majorType.put("AppEvent", "OS应用程序事件");
		majorType.put("OSPolicy", "OS策略信息");
		majorType.put("OSpatch", "补丁信息");
		majorType.put("Netstat", "网络连接信息");
		majorType.put("Process", "进程信息");
		majorType.put("AutoRun", "启动项信息");
		majorType.put("SoftWare", "软件信息");
		majorType.put("OSver", "OS版本信息");
		majorType.put("Netcard", "网卡信息");
		majorType.put("DeviceInfo", "设备信息");
		majorType.put("Service", "系统服务");
		majorType.put("SecureOption", "安全选项");
		majorType.put("SecureOptionTemp", "安全选项");
	}

	/***************************************************************************
	 * 初始化次要类型
	 * 
	 * 次要类型所有字段全用小写表示
	 */
	private void initMinorType() {
		// Apache
		minorType = new HashMap<String, String>();
		minorType.put("serverip", "服务器绑定的IP");
		minorType.put("hostdomain", "服务器所在主机的域名");
		minorType.put("installpath", "服务器的安装路径");
		minorType.put("logtype", "日志类型");
		minorType.put("logpath", "日志路径");
		minorType.put("configfilepath", "配置文件路径");
		minorType.put("isdirbrow", "是否开启目录浏览");
		minorType.put("dirpriv", "目录权限");
		minorType.put("robots", "搜索引擎配置");
		map.put("Apache", minorType);

		// IIS
		minorType = new HashMap<String, String>();
		minorType.put("serverip", "服务器绑定的IP");
		minorType.put("hostdomain", "服务器所在主机的域名");
		minorType.put("installpath", "服务器的安装路径");
		minorType.put("isdirbrow", "是否开启目录浏览功能");
		minorType.put("isrecordlog", "是否启用日志记录功能");
		minorType.put("filetype", "IIS解释的文件类型");
		minorType.put("dirpriv", "目录权限");
		minorType.put("subassembly", "子组件");
		minorType.put("robots", "搜索引擎配置");
		map.put("IIS", minorType);

		// Tomcat
		minorType = new HashMap<String, String>();
		minorType.put("serverip", "服务器绑定的IP");
		minorType.put("hostdomain", "服务器所在主机的域名");
		minorType.put("installpath", "服务器的安装路径");
		minorType.put("logtype", "日志类型");
		minorType.put("loglevel", "日志等级");
		minorType.put("logpath", "日志路径");
		minorType.put("configfilepath", "配置文件路径");
		minorType.put("isdirbrow", "是否开启目录浏览功能");
		minorType.put("dirpriv", "目录权限");
		minorType.put("robots", "搜索引擎配置");
		map.put("Tomcat", minorType);

		// webLogic
		minorType = new HashMap<String, String>();
		minorType.put("serverip", "服务器绑定的IP");
		minorType.put("hostdomain", "服务器所在主机的域名");
		minorType.put("installpath", "服务器的安装路径");
		minorType.put("accesslogpath", "访问日志路径");
		minorType.put("logpath", "日志路径");
		minorType.put("configfilepath", "配置文件路径");
		minorType.put("isdirbrow", "是否开启目录浏览");
		minorType.put("dirpriv", "目录权限");
		minorType.put("robots", "搜索引擎配置");
		map.put("WebLogic", minorType);

		// Oracle
		minorType = new HashMap<String, String>();
		minorType.put("userlist", "当前数据库用户列表");
		minorType.put("userpriv", "当前数据库权限列表");
		minorType.put("rolelist", "当前数据库角色列表");
		minorType.put("logpath", "日志路径");
		map.put("Oracle", minorType);

		// SQLServer
		minorType = new HashMap<String, String>();
		minorType.put("userlist", "用户列表");
		minorType.put("userpriv", "用户权限列表");
		minorType.put("rolelist", "角色列表");
		minorType.put("dbname", "数据库");
		minorType.put("datapath", "数据库路径");
		minorType.put("logpath", "数据库日志路径");
		minorType.put("backpath", "备份路径");
		map.put("SQLServer", minorType);

		// MySQL
		minorType = new HashMap<String, String>();
		minorType.put("userlist", "用户列表");
		minorType.put("userpriv", "用户权限列表");
		minorType.put("binlog", "二进制日志");
		minorType.put("slowquerylog", "慢查询日志");
		minorType.put("errorlog", "错误日志");
		minorType.put("commonquerypath", "一般查询日志");
		map.put("MySQL", minorType);

		// ShareInfo
		minorType = new HashMap<String, String>();
		minorType.put("sharedir", "共享信息");
		minorType.put("sharepath", "路径");
		minorType.put("type", "类型");
		minorType.put("cilentlink", "客户端连接");
		minorType.put("description", "说明");
		map.put("ShareInfo", minorType);

		// OsUserInfo
		minorType = new HashMap<String, String>();
		minorType.put("username", "用户名");
		minorType.put("fullname", "全名");
		minorType.put("description", "描述");
		map.put("OsUserInfo", minorType);

		// OSEvent
		/*minorType = new HashMap<String, String>();
		minorType.put("clientid", "客户端GUID");
		minorType.put("eventtype", "事件类型");
		minorType.put("createtime", "发生时间");
		minorType.put("source", "来源");
		minorType.put("catalog", "分类");
		minorType.put("eventid", "事件");
		minorType.put("user", "用户");
		minorType.put("computername", "计算机名");
		minorType.put("infotype", "信息类型");
		minorType.put("changedate", "变更时间");
		map.put("OSEvent", minorType);*/
		
		// SystemEvent
		minorType = new HashMap<String, String>();
		minorType.put("displayname", "显示名称");
		minorType.put("filename", "日志名称");
		//minorType.put("filesize", "日志大小");
		//minorType.put("filecreatetime", "创建时间");
		//minorType.put("fileaccesstime", "访问时间");
		//minorType.put("filemodifytime", "修改时间");
		minorType.put("maxfilesize", "最大日志文件大小");
		minorType.put("maxaction", "处理方式");
		map.put("SystemEvent", minorType);
		
		// AppEvent
		minorType = new HashMap<String, String>();
		minorType.put("displayname", "显示名称");
		minorType.put("filename", "日志名称");
		//minorType.put("filesize", "日志大小");
		//minorType.put("filecreatetime", "创建时间");
		//minorType.put("fileaccesstime", "访问时间");
		//minorType.put("filemodifytime", "修改时间");
		minorType.put("maxfilesize", "最大日志文件大小");
		minorType.put("maxaction", "处理方式");
		map.put("AppEvent", minorType);
		
		// SafeEvent
		minorType = new HashMap<String, String>();
		minorType.put("displayname", "显示名称");
		minorType.put("filename", "日志名称");
		//minorType.put("filesize", "日志大小");
		//minorType.put("filecreatetime", "创建时间");
		//minorType.put("fileaccesstime", "访问时间");
		//minorType.put("filemodifytime", "修改时间");
		minorType.put("maxfilesize", "最大日志文件大小");
		minorType.put("maxaction", "处理方式");
		map.put("SafeEvent", minorType);

		// DeviceInfo
		minorType = new HashMap<String, String>();
		minorType.put("devicetype", "设备类型");
		minorType.put("classname", "设备显示名");
		minorType.put("description", "设备");
		minorType.put("devicetitle", "设备标题");

		map.put("DeviceInfo", minorType);

		// Netcard
		minorType = new HashMap<String, String>();
		minorType.put("adaptername", "网卡");
		minorType.put("macaddress", "mac地址");
		minorType.put("iplist", "Ip列表");

		map.put("Netcard", minorType);

		// OSver
		minorType = new HashMap<String, String>();
		minorType.put("osname", "操作系统名称");
		minorType.put("osmajorver", "主版本号");
		minorType.put("osminorver", "次版本号");
		minorType.put("osspver", "SP版本号");

		map.put("OSver", minorType);

		// SoftWare
		minorType = new HashMap<String, String>();
		minorType.put("softwarename", "软件");
		minorType.put("softwarever", "版本号");
		minorType.put("patchversion", "修补版本");
		minorType.put("installdate", "安装日期");
		minorType.put("installpath", "安装路径");
		map.put("SoftWare", minorType);
		
		// AutoRun
		minorType = new HashMap<String, String>();
		minorType.put("name", "启动项");
		minorType.put("path", "启动项路径");

		map.put("AutoRun", minorType);
		// Process
		minorType = new HashMap<String, String>();
		minorType.put("name", "进程");
		minorType.put("path", "进程路径");
		minorType.put("memsize", "占用内存");
		minorType.put("username", "用户名");
		minorType.put("processid", "进程");

		map.put("Process", minorType);
		// Netstat
		minorType = new HashMap<String, String>();
		minorType.put("protocol", "协议 (TCP/UDP)");
		minorType.put("localaddress", "本地地址端口");
		minorType.put("foreignaddress", "外部地址端口");
		minorType.put("state", "连接状态");

		map.put("Netstat", minorType);
		// OSpatch
		minorType = new HashMap<String, String>();
		minorType.put("patchname", "补丁");
		minorType.put("patchver", "补丁版本");
		minorType.put("installdate", "安装日期");
		
		map.put("OSpatch", minorType);
		
		// Service
		minorType = new HashMap<String, String>();
		minorType.put("servicename", "服务名");
		minorType.put("displayname", "服务显示名");
		minorType.put("description", "描述");
		minorType.put("exepath", "可执行文件路径");
		minorType.put("starttype", "启动类型");
		minorType.put("state", "状态");
		minorType.put("logintype", "登录类型");
		map.put("Service", minorType);
		
		// OSPolicy
		minorType = new HashMap<String, String>();
		minorType.put("passwordcomplexity", "密码复杂性");
		minorType.put("minimumpasswordlength", "最短密码长度");
		minorType.put("maximumpasswordage", "密码最长存留期");
		minorType.put("minimumpasswordage", "密码最短存留期");
		minorType.put("passwordhistorysize", "强制密码历史");
		minorType.put("resetlockoutcount", "复位帐户锁定计数器");
		minorType.put("lockoutduration", "帐户锁定时间");
		minorType.put("lockoutbadcount", "帐户锁定阈值");
		minorType.put("auditpolicychange", "审核策略更改");
		minorType.put("auditlogonevents", "审核登录事件");
		minorType.put("auditobjectaccess", "审核对象访问");
		minorType.put("auditprocesstracking", "审核过程追踪");
		minorType.put("auditdsaccess", "审核目录服务访问");
		minorType.put("auditprivilegeuse", "审核特权使用");
		minorType.put("auditsystemevents", "审核系统事件");
		minorType.put("auditaccountlogon", "审核账户登录事件");
		minorType.put("auditaccountmanage", "审核账户管理");
		minorType.put("screenprotectflag", "是否开启屏幕保护");
		minorType.put("screenprotecttimeout", "屏幕保护设置时间");
		minorType.put("filesystemformat", "文件系统格式");
		minorType.put("antivirusname", "防病毒软件名字");
		minorType.put("antivirusver", "防病毒软件版本");
		minorType.put("antivirusisnew", "防病毒软件是否最新");
		minorType.put("antivirusisactive", "防病毒软件是否激活");

		map.put("OSPolicy", minorType);
		
		// SecureOption
		minorType = new HashMap<String, String>();
		minorType.put("machineaccessrestriction", "DCOM:安全描述符定义语言(SDDL)语法中的计算机访问限制");
		minorType.put("machinelaunchrestriction", "DCOM:安全描述符定义语言(SDDL)语法中的计算机启动限制");
		minorType.put("enableforcedlogoff", "Microsoft网络服务器:当登录时间用完时自动注销用户");
		minorType.put("clientenablesecuritysignature", "Microsoft网络客户端:数字签名的通信(若服务器同意)");
		minorType.put("serverrequiresecuritysignature", "Microsoft网络服务器:数字签名的通信(总是)");
		minorType.put("autodisconnect", "Microsoft网络服务器:在挂起会话之前所需的空闲时间(分钟)");
		minorType.put("enableplaintextpassword", "Microsoft网络客户:发送未加密的密码到第三方SMB服务器");
		minorType.put("serverenablesecuritysignature", "Microsoft网络服务器:数字签名的通信(若客户端同意)");
		minorType.put("clientrequiresecuritysignature", "Microsoft网络客户:数字签名的通信(总是)");
		minorType.put("setcommand", "故障恢复控制台:允许对所有驱动器和文件夹进行软盘复制和访问");
		minorType.put("securitylevel", "故障恢复控制台:允许自动系统管理级登录");
		minorType.put("clearpagefileatshutdown", "关机:清理虚拟内存页面文件");
		minorType.put("shutdownwithoutlogon", "关机:允许在未登录前关机");
		minorType.put("dontdisplaylastusername", "交互式登录:不显示上次的用户名");
		minorType.put("disablecad", "交互式登录:不需要按 CTRL+ALT+DEL");
		minorType.put("cachedlogonscount", "交互式登录:可被缓冲保存的前次登录个数(在域控制器不可用的情况下)(次)");
		minorType.put("forceunlocklogon", "交互式登录:要求域控制器身份验证以脱离工作站");
		minorType.put("scforceoption", "交互式登录:要求智能卡");
		minorType.put("legalnoticecaption", "交互式登录:用户试图登录时消息标题");
		minorType.put("legalnoticetext", "交互式登录:用户试图登录时消息文字");
		minorType.put("passwordexpirywarning", "交互式登录:在密码到期前提示用户更改密码(天)");
		minorType.put("scremoveoption", "交互式登录:智能卡移除操作");
		minorType.put("addprinterdrivers", "设备:防止用户安装打印机驱动程序");
		minorType.put("policy", "设备:未签名驱动程序的安装操作");
		minorType.put("undockwithoutlogon", "设备:允许不登录脱离");
		minorType.put("allocatedasd", "设备:允许格式化和弹出可移动媒体");
		minorType.put("allocatecdroms", "设备:只有本地登录的用户才能访问CD-ROM");
		minorType.put("allocatefloppies", "设备:只有本地登录的用户才能访问软盘");
		minorType.put("fullprivilegeauditing", "审计:对备份和还原权限的使用进行审计");
		minorType.put("auditbaseobjects", "审计:对全局系统对象的访问进行审计");
		minorType.put("crashonauditfail", "审计:如果无法纪录安全审计则立即关闭系统");
		minorType.put("lmcompatibilitylevel", "网络安全:LAN Manager身份验证级别");
		minorType.put("ldapclientintegrity", "网络安全:LDAP客户签名要求");
		minorType.put("nolmhash", "网络安全:不要在下次更改密码时存储LAN Manager的Hash值");	
		minorType.put("forcelogoffwhenhourexpire", "网络安全:在超过登录时间后强制注销");
		minorType.put("ntlmminserversec", "网络安全设置:基于 NTLM SSP(包括安全 RPC)服务器的最小会话安全");
		minorType.put("ntlmminclientsec", "网络安全设置:基于 NTLM SSP(包括安全 RPC)客户的最小会话安全");
		minorType.put("forceguest", "网络访问:本地帐户的共享和安全模式");
		minorType.put("restrictanonymoussam", "网络访问:不允许SAM帐户的匿名枚举");
		minorType.put("restrictanonymous", "网络访问:不允许SAM帐户和共享的匿名枚举");
		minorType.put("disabledomaincreds", "网络访问:不允许为网络身份验证储存凭据或 .NET Passports");
		minorType.put("nullsessionshares", "网络访问:可匿名访问的共享");
		minorType.put("nullsessionpipes", "网络访问:可匿名访问的命名管道");
		minorType.put("machine", "网络访问:可远程访问的注册表路径");
		minorType.put("everyoneincludesanonymous", "网络访问:让“每个人”权限应用于匿名用户");
		minorType.put("lsaanonymousnamelookup", "网络访问:允许匿名SID/名称转换");
		minorType.put("obcaseinsensitive", "系统对象:对非Windows子系统不要求区分大小写");
		minorType.put("nodefaultadminowner", "系统对象:由Administrators组成员所创建的对象默认所有者");
		minorType.put("protectionmode", "系统对象:增强内部系统对象的默认权限");
		minorType.put("fipsalgorithmpolicy", "系统加密:使用FIPS兼容的算法来加密，散列和签名");
		minorType.put("sealsecurechannel", "域成员:对安全通道数据进行数字加密(如果可能)");
		minorType.put("requiresignorseal", "域成员:对安全通道数据进行数字加密或签名(总是)");
		minorType.put("signsecurechannel", "域成员:对安全通道数据进行数字签名(如果可能)");
		minorType.put("requirestrongkey", "域成员:需要强(Windows 2000 或以上版本)会话密钥");
		minorType.put("ldapserverintegrity", "域控制器:LDAP服务器签名要求");
		minorType.put("disablepasswordchange", "域控制器:禁用更改机器帐户密码");
		minorType.put("refusepasswordchange", "域控制器:拒绝更改机器帐户密码");
		minorType.put("submitcontrol", "域控制器:允许服务器操作员计划任务");
		minorType.put("maximumpasswordage", "域控制器:最长机器帐户密码寿命(天)");
		minorType.put("enableadminaccount", "帐户:管理员帐户状态");
		minorType.put("enableguestaccount", "帐户:来宾帐户状态");
		minorType.put("limitblankpassworduse", "帐户:使用空白密码的本地帐户只允许进行控制台登录");
		minorType.put("newguestname", "帐户:重命名来宾帐户");
		minorType.put("newadministratorname", "帐户:重命名系统管理员帐户");

		map.put("SecureOption", minorType);
		
		// SecureOption临时存取，为标准库方便开发而建
		minorType = new HashMap<String, String>();
		minorType.put("machineAccessRestriction", "DCOM:安全描述符定义语言(SDDL)语法中的计算机访问限制");
		minorType.put("machineLaunchRestriction", "DCOM:安全描述符定义语言(SDDL)语法中的计算机启动限制");
		minorType.put("enableForcedLogOff", "Microsoft网络服务器:当登录时间用完时自动注销用户");
		minorType.put("clientEnableSecuritySignature", "Microsoft网络客户端:数字签名的通信(若服务器同意)");
		minorType.put("serverRequireSecuritySignature", "Microsoft网络服务器:数字签名的通信(总是)");
		minorType.put("autoDisconnect", "Microsoft网络服务器:在挂起会话之前所需的空闲时间(分钟)");
		minorType.put("enablePlainTextPassword", "Microsoft网络客户:发送未加密的密码到第三方SMB服务器");
		minorType.put("serverEnableSecuritySignature", "Microsoft网络服务器:数字签名的通信(若客户端同意)");
		minorType.put("clientRequireSecuritySignature", "Microsoft网络客户:数字签名的通信(总是)");
		minorType.put("setCommand", "故障恢复控制台:允许对所有驱动器和文件夹进行软盘复制和访问");
		minorType.put("securityLevel", "故障恢复控制台:允许自动系统管理级登录");
		minorType.put("clearPageFileAtShutdown", "关机:清理虚拟内存页面文件");
		minorType.put("shutdownWithoutLogon", "关机:允许在未登录前关机");
		minorType.put("dontDisplayLastUserName", "交互式登录:不显示上次的用户名");
		minorType.put("disableCAD", "交互式登录:不需要按 CTRL+ALT+DEL");
		minorType.put("cachedLogonsCount", "交互式登录:可被缓冲保存的前次登录个数(在域控制器不可用的情况下)(次)");
		minorType.put("forceUnlockLogon", "交互式登录:要求域控制器身份验证以脱离工作站");
		minorType.put("scForceOption", "交互式登录:要求智能卡");
		minorType.put("legalNoticeCaption", "交互式登录:用户试图登录时消息标题");
		minorType.put("legalNoticeText", "交互式登录:用户试图登录时消息文字");
		minorType.put("passwordExpiryWarning", "交互式登录:在密码到期前提示用户更改密码(天)");
		minorType.put("scRemoveOption", "交互式登录:智能卡移除操作");
		minorType.put("addPrinterDrivers", "设备:防止用户安装打印机驱动程序");
		minorType.put("policy", "设备:未签名驱动程序的安装操作");
		minorType.put("undockWithoutLogon", "设备:允许不登录脱离");
		minorType.put("allocateDASD", "设备:允许格式化和弹出可移动媒体");
		minorType.put("allocateCDRoms", "设备:只有本地登录的用户才能访问 CD-ROM");
		minorType.put("allocateFloppies", "设备:只有本地登录的用户才能访问软盘");
		minorType.put("fullPrivilegeAuditing", "审计:对备份和还原权限的使用进行审计");
		minorType.put("auditBaseObjects", "审计:对全局系统对象的访问进行审计");
		minorType.put("crashOnAuditFail", "审计:如果无法纪录安全审计则立即关闭系统");
		minorType.put("lmCompatibilityLevel", "网络安全:LAN Manager身份验证级别");
		minorType.put("LDAPClientIntegrity", "网络安全:LDAP客户签名要求");
		minorType.put("noLMHash", "网络安全:不要在下次更改密码时存储LAN Manager的Hash值");	
		minorType.put("forceLogoffWhenHourExpire", "网络安全:在超过登录时间后强制注销");
		minorType.put("NTLMMinServerSec", "网络安全设置:基于 NTLM SSP(包括安全 RPC)服务器的最小会话安全");
		minorType.put("NTLMMinClientSec", "网络安全设置:基于 NTLM SSP(包括安全 RPC)客户的最小会话安全");
		minorType.put("forceGuest", "网络访问:本地帐户的共享和安全模式");
		minorType.put("restrictAnonymousSAM", "网络访问:不允许SAM帐户的匿名枚举");
		minorType.put("restrictAnonymous", "网络访问:不允许SAM帐户和共享的匿名枚举");
		minorType.put("disableDomainCreds", "网络访问:不允许为网络身份验证储存凭据或 .NET Passports");
		minorType.put("nullSessionShares", "网络访问:可匿名访问的共享");
		minorType.put("nullSessionPipes", "网络访问:可匿名访问的命名管道");
		minorType.put("machine", "网络访问:可远程访问的注册表路径");
		minorType.put("everyoneIncludesAnonymous", "网络访问:让“每个人”权限应用于匿名用户");
		minorType.put("LSAAnonymousNameLookup", "网络访问:允许匿名SID/名称转换");
		minorType.put("obCaseInsensitive", "系统对象:对非Windows子系统不要求区分大小写");
		minorType.put("noDefaultAdminOwner", "系统对象:由Administrators组成员所创建的对象默认所有者");
		minorType.put("protectionMode", "系统对象:增强内部系统对象的默认权限");
		minorType.put("FIPSAlgorithmPolicy", "系统加密:使用FIPS兼容的算法来加密，散列和签名");
		minorType.put("sealSecureChannel", "域成员:对安全通道数据进行数字加密(如果可能)");
		minorType.put("requireSignOrSeal", "域成员:对安全通道数据进行数字加密或签名(总是)");
		minorType.put("signSecureChannel", "域成员:对安全通道数据进行数字签名(如果可能)");
		minorType.put("requireStrongKey", "域成员:需要强(Windows 2000 或以上版本)会话密钥");
		minorType.put("LDAPServerIntegrity", "域控制器:LDAP服务器签名要求");
		minorType.put("disablePasswordChange", "域控制器:禁用更改机器帐户密码");
		minorType.put("refusePasswordChange", "域控制器:拒绝更改机器帐户密码");
		minorType.put("submitControl", "域控制器:允许服务器操作员计划任务");
		minorType.put("maximumPasswordAge", "域控制器:最长机器帐户密码寿命(天)");
		minorType.put("enableAdminAccount", "帐户:管理员帐户状态");
		minorType.put("enableGuestAccount", "帐户:来宾帐户状态");
		minorType.put("limitBlankPasswordUse", "帐户:使用空白密码的本地帐户只允许进行控制台登录");
		minorType.put("newGuestName", "帐户:重命名来宾帐户");
		minorType.put("newAdministratorName", "帐户:重命名系统管理员帐户");

		map.put("SecureOptionTemp", minorType);

	}

}
