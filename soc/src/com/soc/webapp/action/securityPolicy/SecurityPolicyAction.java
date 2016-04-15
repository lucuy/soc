package com.soc.webapp.action.securityPolicy;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.push.services.SendMessage;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.policy.TimePolicy;
import com.soc.model.securityPolicy.SecurityPolicy;
import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.securityPolicy.SecurityPolicyService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FileUtil;
import com.util.ReadAndWriteFileUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * 时间策略Action类 时间策略的查询，添加，修改，删除，时间策略状态的改变
 * 
 * @author liuyanxu
 * @version [V100R001C001, 2012-8-11]
 * @see com.soc.service.policy.TimePolicyService
 * @since [用户管理/时间策略管理/V100R001C001]
 */

public class SecurityPolicyAction extends BaseAction implements SessionAware {
	// 日期的格式化对象
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private SecurityPolicyService securityPolicyManager;
	// 安全策略实体类
	private SecurityPolicy securityPolicy;
	// 关键字
	private String keyword;

	// 批量操作时复选框的值
	private String ids;

	// 时间策略Id
	private int policyId;
	// 策略实体类
	private List<SecurityPolicy> securityPolicyList;
	// 策略描述   下发策略后的返回结果
	private String policyMemo;

	private AuditService auditManager;

	// 消息推送管理类
	private SendMessage msg;
	// 上传的文件
	private File upTar;

	// 文件名称
	private String upTarFileName;
	// 前台显示的设备列表
	private List<Asset> assetListDiv;
	// 资产服务管理类
	private AssetService assetManager;
	// 高级查询所用字段
	private String timePolicyName;
	//临时安全策略文件名称
	private String tmpPolicyFileName;
	// 策略描述
	private String timePolicyMemo;
	private String  relPolicyName;
	//下发结果
	private JSONArray policyResultArray;
	//资产的名字
	private String assetName;
	/**
	 * 查询快速检索，高级检索
	 * 
	 * @return
	 */
	public String query() {
		log.info("[SecurityPolicyAction] Enter query....");

		HttpServletRequest request = super.getRequest();

		Page page = null;

		SearchResult sr = null;

		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		try {
			if (StringUtil.isNotBlank(startIndex)) {
				if (keyword == null || keyword.equals("")) {
					page = new Page(Page.DEFAULT_PAGE_SIZE,
							Integer.valueOf(startIndex));
				} else {
					if (Page.getKeyword().equals(keyword)) {
						page = new Page(Page.DEFAULT_PAGE_SIZE,
								Integer.valueOf(startIndex));
						Page.setKeyword(keyword);
					} else {
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

		// 接收快速查询传递来的参数
		if (request.getAttribute("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		// 高级查询判断数据是否为空
		if (StringUtil.isNotBlank(timePolicyName)) {
			map.put("policyName", timePolicyName);
		}

		if (StringUtil.isNotBlank(timePolicyMemo)) {
			map.put("policyMemo", timePolicyMemo);
		}
		// 得到查询结果
		sr = securityPolicyManager.queryPolicy(map, page);

		// 对查找的结果为分页赋值
		if (sr != null) {
			securityPolicyList = (List<SecurityPolicy>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		  //获得当前登录用户所管辖的组的id
  		long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid(); 
        assetListDiv = this.assetManager.queryAllAssetByUserId(groupId);
		return SUCCESS;
	}

	/**
	 *下发策略脚本
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#updateTimePolicyStatus(long,
	 *      int)
	 */
	
	  public String issuedPolicy() {
		  String path = ServletActionContext.getServletContext().getRealPath(
					"/securityPolicyFile");
		  policyMemo = "";
		  for (String id : ids.split(",")) {
			  Asset asset = assetManager.searchById(Integer.parseInt(id));		  
			  policyMemo = policyMemo+ asset.getAssetName()+":"+securityPolicyManager.issuedPolicy(asset,policyId,path)+"  ";
			
			
		}
		  //policyMemo = policyMemo.
		return SUCCESS;
		  
	  }
		/**
		 * 查看脚本执行结果对话框
		 * 
		 * @return
		 * @see com.soc.service.policy.TimePolicyService#updateTimePolicyStatus(long,
		 *      int)
		 */
		
		  public String policyResult() {
			 
			  //传过来资产的名字 和策略的名字 根据从这个去找文件夹个文件 读取内容在界面显示
			 // this.securityPolicy = this.securityPolicyManager.queryPolicyById(policyId);
			  	  String path = ServletActionContext.getServletContext().getRealPath(
						"/securityPolicyFile/"+policyId);
			  	  //获得一个文件夹中的所有文件名
			  	File file=new File(path);
			String assetNameFileContext ; //结果文件中的内容
			    String test[];
			    test=file.list();
			    if (test !=null&&test.length!=0) {//等于空说明没有文件
			    	 policyResultArray = new JSONArray();
			    	 JSONObject  policyResult = new JSONObject();//json对象
			    	 Asset asset;
					    for(int i=0;i<test.length;i++)
					    {
					    	assetNameFileContext = 	ReadAndWriteFileUtil.readFileContentUTF8(new File(path+"/"+test[i]));
					    	//文件夹的名字是资产id 在查一遍
					    	asset = this.assetManager.searchById(Integer.parseInt(test[i]));
					    	//policyResult.put("assetName", asset.getAssetName());
					    	//policyResult.put("assetNameFileContext", assetNameFileContext);
					    
					    	assetNameFileContext = 	ReadAndWriteFileUtil.readFileContentUTF8(new File(path+"/"+test[i]));

					    	//修改下样式
					    	assetNameFileContext  = assetNameFileContext.replaceAll(" ", "&nbsp").replaceAll("\n", "<br/>");

					    	policyResult.put("assetName", asset.getAssetName());
					    	policyResult.put("assetNameFileContext", assetNameFileContext);
					    	policyResult.put("assetIp", asset.getAssetMac());
					    this.policyResultArray.add(policyResult);
					    }
				}
		
		
			return SUCCESS;
			  
		  }
			
	/**
	 * 标记删除时间策略 根据传入的ids判断是否选中多个值,进行批量操作
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#deleteTimePolicy(long)
	 */
	public String deleteSecurityPolicy() {
		log.info("[SecurityPolicyAction] enter deleteSecurityPolicy....");
		List<String> fieldList = new ArrayList<String>();
		// 判断获得的id为一个或者多个，多个执行if内函数,一个执行else内函数
		if (ids.indexOf(",") > 0) {
			// 将获得的ids按照,拆分成数组
			String[] checked = ids.split(",");
			for (String checkid : checked) {
				SecurityPolicy policyObject = securityPolicyManager
						.queryPolicyById(Integer.parseInt(checkid));
				File file = new File(GlobalConfig.securityPolicyFilePath+File.separator+policyObject.getRelPolicyName());
				file.delete();
				//删掉脚本执行结果文件夹
				 file = new File(GlobalConfig.securityPolicyFilePath+File.separator+policyObject.getId());
				 file.delete();
				fieldList.add(policyObject.getPolicyName());
				this.securityPolicyManager.deletePolicy(Integer
						.parseInt(checkid));
			}
		} else {
			SecurityPolicy policyObject = securityPolicyManager
					.queryPolicyById(Integer.parseInt(ids));
			File file = new File(GlobalConfig.securityPolicyFilePath+File.separator+policyObject.getRelPolicyName());
			file.delete();
			//删掉脚本执行结果文件夹
			FileUtil.deleteFile(GlobalConfig.securityPolicyFilePath+File.separator+policyObject.getId());
			// file = new File(GlobalConfig.securityPolicyFilePath+File.separator+policyObject.getPolicyName());
		//	 file.delete();
			fieldList.add(policyObject.getPolicyName());
			securityPolicyManager.deletePolicy(Integer.parseInt(ids));
		}

		// 审计日志
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "安全策略策略", super
				.getRequest().getRemoteAddr(), fieldList);

		// syslog
		/*String logString = "";
		logString = "登录名："
				+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
						.getUserLoginName() + "  源IP:"
				+ getRequest().getRemoteAddr() + "   操作时间："
				+ DateUtil.curDateTimeStr19() + "   操作类型:删除时间策略";

		logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除安全策略");

		return SUCCESS;
	}

	/**
	 * 编辑时间策略,跳转到添加页面 根据时间策略id是否有值，有值的话跳转到修改页面，没值的话跳转到添加页面
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#queryTimePolicyById(long)
	 */
	public String edit() {
		
		log.info("[SecurityolicyAction] enter edit......");
		
		this.securityPolicy = this.securityPolicyManager
				.queryPolicyById(policyId);
		if (policyId!=0) {
			tmpPolicyFileName = securityPolicy.getRelPolicyName();
			securityPolicy.setRelPolicyName(securityPolicy.getRelPolicyName().split("-")[1]);
		}
		return SUCCESS;
	}
	/**
	 * 判断名字是不是存在
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#queryTimePolicyById(long)
	 */
	public void checkName() {
		
			log.info("[AssetAction] enter checkAssetName...");

			// 标识此策略名是否存在
			String flag = "false";

			if (StringUtil.isNotBlank(timePolicyName)) {
				int count = this.securityPolicyManager.countOfName(timePolicyName);
				if (count > 0) {
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
	 * 添加或者修改时间策略
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#updateTimePolicy(TimePolicy)
	 */
	public String updateSecurityPolicy() {
		log.info("[TimeTimePolicyAction] enter updateTimePolicy...");
		String path = ServletActionContext.getServletContext().getRealPath(
				"/securityPolicyFile");
		if(securityPolicy.getId() == 0){
			securityPolicy.setCreateTime(this.simpleDateFormat
					.format(new Date()));	
		}
		String fileNameString ="";
		if (upTar != null && upTarFileName != null) {
		
			File policyFile = new File(path+File.separator+System.currentTimeMillis()+"-"+upTarFileName);
			fileNameString = policyFile.getName();
			try {
				FileUtil.copyFile(upTar, policyFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(securityPolicy.getPolicyName() + "("
				+ securityPolicy.getPolicyName() + ")");
		// 获得session内存储的用户对象
		User u = (User) super.getSession().getAttribute("SOC_LOGON_USER");
		// 判断时间对象Id是否存在,不存在将用户的登录名赋值给操作用户名
		if (securityPolicy.getId() == 0) {
			// 添加
			
			securityPolicy.setCreateUsername(u.getUserLoginName());
			//String [] fileName = timePolicyMemo.split("\\\\");//这里把文件名提取出来
			//timePolicyMemo = fileName[fileName.length-1];
			securityPolicy.setRelPolicyName(fileNameString);
			//FileUtil.renamefile(path + File.separator +upTarFileName,securityPolicy.getCreateTime()+timePolicyMemo);
			//System.out.println(timePolicyMemo);
			// 审计日志
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "安全策略", super
					.getRequest().getRemoteAddr(), fieldList);
			// syslog
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:添加时间策略";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增安全策略");

		} else {
			// 修改
			securityPolicy.setModifyTime(this.simpleDateFormat
					.format(new Date()));
			securityPolicy.setModifyUsername(u.getUserLoginName());
			String [] fileName = timePolicyMemo.split("\\\\");//这里把文件名提取出来
			timePolicyMemo = fileName[fileName.length-1];
			if (fileName.length!=1) {
				File file =new File(path+File.separator+tmpPolicyFileName);
			boolean b=	file.delete();
				securityPolicy.setRelPolicyName(fileNameString);
				//FileUtil.renamefile(path + File.separator +upTarFileName, securityPolicy.getCreateTime()+timePolicyMemo);
				// file.delete();
			// 
				file = new File(GlobalConfig.securityPolicyFilePath+File.separator+securityPolicy.getId());
				FileUtil.emptyDirectory(file);
				//file.delete();
			}
			////删掉脚本执行结果文件夹
			// file = new File(GlobalConfig.securityPolicyFilePath+File.separator+policyObject.getPolicyName());
			// file.delete();
			//File file = new File(GlobalConfig.securityPolicyFilePath+File.separator+securityPolicy.getPolicyName());
		//	file.delete();
			// 审计日志
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "安全策略", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog日志
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:更新时间策略";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改安全策略");
		}
		// 执行更新函数
		this.securityPolicyManager.updatePolicy(securityPolicy);
		return SUCCESS;
	}
	 
	public String redirect() {
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}

	public SecurityPolicyService getSecurityPolicyManager() {
		return securityPolicyManager;
	}

	public void setSecurityPolicyManager(
			SecurityPolicyService securityPolicyManager) {
		this.securityPolicyManager = securityPolicyManager;
	}

	public SecurityPolicy getSecurityPolicy() {
		return securityPolicy;
	}

	public void setSecurityPolicy(SecurityPolicy securityPolicy) {
		this.securityPolicy = securityPolicy;
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

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public List<SecurityPolicy> getSecurityPolicyList() {
		return securityPolicyList;
	}

	public void setSecurityPolicyList(List<SecurityPolicy> securityPolicyList) {
		this.securityPolicyList = securityPolicyList;
	}

	public String getPolicyMemo() {
		return policyMemo;
	}

	public void setPolicyMemo(String policyMemo) {
		this.policyMemo = policyMemo;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public SendMessage getMsg() {
		return msg;
	}

	public void setMsg(SendMessage msg) {
		this.msg = msg;
	}

	public File getUpTar() {
		return upTar;
	}

	public void setUpTar(File upTar) {
		this.upTar = upTar;
	}

	public String getUpTarFileName() {
		return upTarFileName;
	}

	public void setUpTarFileName(String upTarFileName) {
		this.upTarFileName = upTarFileName;
	}

	public List<Asset> getAssetListDiv() {
		return assetListDiv;
	}

	public void setAssetListDiv(List<Asset> assetListDiv) {
		this.assetListDiv = assetListDiv;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public String getTimePolicyName() {
		return timePolicyName;
	}

	public void setTimePolicyName(String timePolicyName) {
		this.timePolicyName = timePolicyName;
	}

	public String getTimePolicyMemo() {
		return timePolicyMemo;
	}

	public void setTimePolicyMemo(String timePolicyMemo) {
		this.timePolicyMemo = timePolicyMemo;
	}

	public String getRelPolicyName() {
		return relPolicyName;
	}

	public void setRelPolicyName(String relPolicyName) {
		this.relPolicyName = relPolicyName;
	}

	public String getTmpPolicyFileName() {
		return tmpPolicyFileName;
	}

	public void setTmpPolicyFileName(String tmpPolicyFileName) {
		this.tmpPolicyFileName = tmpPolicyFileName;
	}

	public JSONArray getPolicyResultArray() {
		return policyResultArray;
	}

	public void setPolicyResultArray(JSONArray policyResultArray) {
		this.policyResultArray = policyResultArray;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

}
