package com.soc.webapp.action.policy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;

import com.push.services.SendMessage;
import com.soc.model.policy.Address;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.user.OnlineUser;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.policy.AddressPolicyService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * 地址策略Action类 对地址策略的查询（普通查询，快速查询，高级查询），地址策略的修改，地址策略状态的修改，地址策略的标记删除
 * 
 * @author liuyanxu
 * @version [V100R001C001, 2012-8-14]
 * @see com.soc.service.policy.AddressPolicyService
 * @since [用户管理/地址策略管理/V100R001C001]
 */
public class AddressPolicyAction extends BaseAction implements SessionAware{
	// ip地址段列表
	private String ipAll;

	// 地址策略业务对象
	private AddressPolicyService addressPolicyManager;

	// 地址策略列表
	private List<AddressPolicy> addressPolicyList;

	// 地址策略对象
	private AddressPolicy addressPolicy;

	// 模糊查询关键字
	private String keyword;

	// 地址状态
	private int addressPolicyStatus = 2;

	// 地址策略ids
	private String ids;

	// 地址策略名称
	private String addressPolicyName;

	// 地址策略描述
	private String addressPolicyMemo;

	// 地址策略生效方式
	private int addressPolicyEffectWay = 2;

	// 地址策略id
	private int addressPolicyId;

	// 审计业务对象
	private AuditService auditManager;
	
	//请求的action字符串
    private String actionStr ="query.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ; 
    
    //保存关联的用户
    private Map<String, Object> sessionMap;
    
    //根据策略Id获得所有用户
    private List<User> userList;
    
    //消息推送类
    private SendMessage msg;
    
    
	

	/**
	 * 地址策略记录的查询 地址策略的高级查询，分页显示，快速查询
	 * 
	 * @return
	 * @see com.soc.service.policy.AddressPolicyService#queryAddreesPolicy(Map,
	 *      Page)
	 */
	public String query() {

		log.info("[AddressPolicyAction] Enter query...");

		HttpServletRequest request = super.getRequest();

		Page page = null;

		SearchResult sr = null;

		// 接收查询条件，并存储到map中
		Map<String, Object> map = new HashMap<String, Object>();

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
		// 模糊查询
		if (request.getParameter("keyword") != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		// 高级查询
		if (StringUtil.isNotBlank("addressPolicyName")) {
			map.put("addressPolicyName", addressPolicyName);
		}
		if (StringUtil.isNotBlank("addressPolicyMemo")) {
			map.put("addressPolicyMemo", addressPolicyMemo);
		}
		if (addressPolicyStatus == 0 || addressPolicyStatus == 1) {
			map.put("addressPolicyStatus", addressPolicyStatus);
		}
		if (addressPolicyEffectWay == 0 || addressPolicyEffectWay == 1) {
			map.put("addressPolicyEffectWay", addressPolicyEffectWay);
		}
		// 得到查询结果
		sr = addressPolicyManager.queryAddressPolicy(map, page);
		if (sr != null) {
			// 将查询结果放到list内
			addressPolicyList = (List<AddressPolicy>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}

	/**
	 * 更改地址策略的状态
	 * 
	 * @return
	 * @see com.soc.service.policy.AddressPolicyService#updateAddressPolicyStatus(long,
	 *      int)
	 */
	public String updateStatus() {
		log.info("[AddressPolicyAction] enter updateStatus....");
		List<String> fieldList = new ArrayList<String>();

		if (StringUtil.isNotBlank(ids)) {
			// 操作多条记录
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");

				// 循环遍历需要更新状态的地址策略
				for (String checkid : checked) {
					AddressPolicy addressObject = addressPolicyManager
							.queryById(Long.parseLong(checkid));
					fieldList.add(addressObject.getAddressPolicyName() + "("
							+ addressObject.getAddressPolicyName() + ")");
					if (addressObject.getAddressPolicyStatus() != addressPolicyStatus) {
						addressPolicyManager.updateAddressPolicyStatus(
								addressObject.getAddressPolicyId(),
								addressPolicyStatus);
					}else {
						checkPolicyIp(checkid);
					}
				}

			}
			// 操作一条记录
			else {
				AddressPolicy addressObject = addressPolicyManager
						.queryById(Long.parseLong(ids));
				fieldList.add(addressObject.getAddressPolicyName() + "("
						+ addressObject.getAddressPolicyName() + ")");
				addressPolicyManager.updateAddressPolicyStatus(
						Integer.parseInt(ids), addressPolicyStatus);
				checkPolicyIp(ids);
			}

			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "地址策略", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :更改地址策略状态";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改地址策略状态");
		}
		return SUCCESS;
	}

	/**
	 * 标记删除 根据获得的Ids标记删除地址策略
	 * 
	 * @return
	 * @see com.soc.service.policy.AddressPolicyService#deleteAddressPolicy(long)
	 */
	public String deleteaddressPolicy() {
		log.info("[AddressPolicyAction] enter deleteaddressPolicy...");

		List<String> fieldList = new ArrayList<String>();
		if (StringUtil.isNotBlank(ids)) {
			// 操作多条记录
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");
				// 循环遍历需要删除的地址策略
				for (String checkid : checked) {
					AddressPolicy addressObject = addressPolicyManager
							.queryById(Long.parseLong(checkid));
					fieldList.add(addressObject.getAddressPolicyName() + "("
							+ addressObject.getAddressPolicyName() + ")");
					addressPolicyManager.deleteAddressPolicy(Integer
							.parseInt(checkid));
				}
			}
			// 操作一条记录
			else {
				AddressPolicy addressObject = addressPolicyManager
						.queryById(Long.parseLong(ids));
				fieldList.add(addressObject.getAddressPolicyName() + "("
						+ addressObject.getAddressPolicyName() + ")");
				addressPolicyManager.deleteAddressPolicy(Integer.parseInt(ids));
			}

			// 审计日志
			auditManager.insertByDeleteOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "地址策略", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :删除地址策略";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除地址策略");
		}
		return SUCCESS;
	}

	/**
	 * 跳转页面 根据是否接收到addressPolicyId,判断跳转到不同的页面
	 * 
	 * @return
	 * @see com.soc.service.policy.AddressPolicyService#queryById(long)
	 */
	public String editAddressPolicy() {
		if (addressPolicyId != 0) {
			addressPolicy = addressPolicyManager.queryById(addressPolicyId);
		}
		return SUCCESS;
	}

	/**
	 * 更新数据 更新或者添加数据
	 * 
	 * @return
	 * @see com.soc.service.policy.AddressPolicyService#updateAddressPolicy(AddressPolicy,
	 *      String)
	 */
	public String updateAddressPolicy() {

		log.info("[AddressPolicyAction] enter updateAddressPolicy");
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(addressPolicy.getAddressPolicyName() + "("
				+ addressPolicy.getAddressPolicyName() + ")");

		// 判断是否为增加操作
		if (addressPolicy.getAddressPolicyId() == 0) {
			// 获得session内存储的user对象
			User u = (User) super.getSession().getAttribute("SOC_LOGON_USER");
			// 将用户的登录名赋值到addressPolicy内
			addressPolicy.setAddressPolicyCreateUserLoginName(u
					.getUserLoginName());
		}

		if (addressPolicy.getAddressPolicyId() == 0) {

			//审计日志
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "地址策略", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";

			logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :添加地址策略";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增地址策略");
		} else {
			//审计日志
			  auditManager.insertByUpdateOperator(((User) this.getSession()
			  .getAttribute("SOC_LOGON_USER")).getUserId(), "地址策略", super
			  .getRequest().getRemoteAddr(), fieldList);
			 
			// syslog
			String logString = "";

			/*logString = "登录名:"
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + " 源IP:"
					+ getRequest().getRemoteAddr() + " 操作时间:"
					+ DateUtil.curDateTimeStr19() + " 操作类型 :更改地址策略";
			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改地址策略");
		}

		addressPolicyManager.updateAddressPolicy(addressPolicy, ipAll);
		
		String [] ipAlls = ipAll.split(";");
		//判断当前用户所在Ip是否在允许范围内
		if (ipAlls.length>0&&ipAlls.equals(null)) {
			for (int i = 0; i < ipAlls.length; i++) {
					userList = addressPolicyManager.queryUserByAddressPolicyId((addressPolicy.getAddressPolicyId()));
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
							
							String [] ipnow = ipnowIp.split("\\.");
							String [] ipStart = ipAlls[i].split("-")[0].split("\\.");
							String [] ipEnd = ipAlls[i].split("-")[1].split("\\.");
							boolean check = false;
							if (Integer.parseInt(ipStart[0])<=Integer.parseInt(ipnow[0]) && Integer.parseInt(ipEnd[0])>=Integer.parseInt(ipnow[0])) {
								if (Integer.parseInt(ipStart[1])<=Integer.parseInt(ipnow[1]) && Integer.parseInt(ipEnd[1])>=Integer.parseInt(ipnow[1])) {
									if (Integer.parseInt(ipStart[2])<=Integer.parseInt(ipnow[2]) && Integer.parseInt(ipEnd[2])>=Integer.parseInt(ipnow[2])) {
										if (Integer.parseInt(ipStart[3])<=Integer.parseInt(ipnow[3]) && Integer.parseInt(ipEnd[3])>Integer.parseInt(ipnow[3])) {
											if (addressPolicy.getAddressPolicyEffectWay()==1) {
												check = true;
											}
										} 
									} 
								} 
							} 
							if (!check) {
								sessionMap.put("userinfo", user.getUserLoginName());
								msg.sendMessageAuto(user.getUserLoginName(), "地址策略已经修改，你所在IP不在允许范围内,请及时下线！");
							}
						}
					}
					break;
			}
		}
		//System.out.println(ipAll);
		return SUCCESS;
	}

	/**
	 * 查询 根据策略名称查询策略是否存在,如果存在向页面返回"flag=true",不存在向页面返回"flag=false"
	 * 
	 * @return
	 * @see com.soc.service.policy.AddressPolicyService#queryByAddressName(String)
	 */
	public void checkaddressPolicyName() {
		log.info("[AddressPolicyAction] enter checkaddressPolicyName..");

		// 标识此策略名是否存在
		String flag = "false";

		if (StringUtil.isNotBlank(addressPolicyName)) {
			List<AddressPolicy> list = addressPolicyManager
					.queryByAddressName(addressPolicyName);
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
	
	public String sort(){
		 LOG.info("[VulnerabilityAction] enter method sort() ...");
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
	            
	            sr = addressPolicyManager.sort(map, page);
	            if (sr != null)
	            {
	            	addressPolicyList = sr.getList();
	                request.setAttribute("addressPolicyList", addressPolicyList);
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

	
	public void checkPolicyIp(String checkid){
		
		//判断当前用户所在Ip是否在允许范围内
		List<Address> addressList = addressPolicyManager.getAddressList(Integer.parseInt(checkid));
		AddressPolicy ap = addressPolicyManager.queryById(Integer.parseInt(checkid));
		if (addressList!=null && addressList.size()>0) {
				for (int i = 0; i < addressList.size(); i++) {
						Address address = addressList.get(i);
						userList = addressPolicyManager.queryUserByAddressPolicyId((Integer.parseInt(ids)));
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
								
								String [] ipnow = ipnowIp.split("\\.");
								String [] ipStart = address.getAddressStartIp().split("\\.");
								String [] ipEnd = address.getAddressEndIp().split("\\.");
								boolean check = false;
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
								if (!check) {
									sessionMap.put("userinfo", user.getUserLoginName());
									msg.sendMessageAuto(user.getUserLoginName(), "地址策略已经激活，你所在IP不在允许范围内,请及时下线！");
								}
							}
						}
						break;
				}
		}
	}

	public String displayTree() {
		return SUCCESS;
	}

	public String getIpAll() {
		return ipAll;
	}

	public void setIpAll(String ipAll) {
		this.ipAll = ipAll;
	}

	public AddressPolicyService getAddressPolicyManager() {
		return addressPolicyManager;
	}

	public void setAddressPolicyManager(
			AddressPolicyService addressPolicyManager) {
		this.addressPolicyManager = addressPolicyManager;
	}

	public List<AddressPolicy> getAddressPolicyList() {
		return addressPolicyList;
	}

	public void setAddressPolicyList(List<AddressPolicy> addressPolicyList) {
		this.addressPolicyList = addressPolicyList;
	}

	public AddressPolicy getAddressPolicy() {
		return addressPolicy;
	}

	public void setAddressPolicy(AddressPolicy addressPolicy) {
		this.addressPolicy = addressPolicy;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getAddressPolicyStatus() {
		return addressPolicyStatus;
	}

	public void setAddressPolicyStatus(int addressPolicyStatus) {
		this.addressPolicyStatus = addressPolicyStatus;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getAddressPolicyName() {
		return addressPolicyName;
	}

	public void setAddressPolicyName(String addressPolicyName) {
		this.addressPolicyName = addressPolicyName;
	}

	public String getAddressPolicyMemo() {
		return addressPolicyMemo;
	}

	public void setAddressPolicyMemo(String addressPolicyMemo) {
		this.addressPolicyMemo = addressPolicyMemo;
	}

	public int getAddressPolicyEffectWay() {
		return addressPolicyEffectWay;
	}

	public void setAddressPolicyEffectWay(int addressPolicyEffectWay) {
		this.addressPolicyEffectWay = addressPolicyEffectWay;
	}

	public int getAddressPolicyId() {
		return addressPolicyId;
	}

	public void setAddressPolicyId(int addressPolicyId) {
		this.addressPolicyId = addressPolicyId;
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

	public SendMessage getMsg() {
		return msg;
	}

	public void setMsg(SendMessage msg) {
		this.msg = msg;
	}

	
	
	
	

}
