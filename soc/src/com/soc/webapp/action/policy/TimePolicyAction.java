package com.soc.webapp.action.policy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.SessionAware;

import com.push.services.SendMessage;
import com.soc.model.policy.TimePolicy;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.policy.TimePolicyService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
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

public class TimePolicyAction extends BaseAction implements SessionAware{
	// 日期的格式化对象
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");

	// 时间策略实体类
	private TimePolicy timePolicy;

	// 时间策略管理类
	private TimePolicyService timePolicyManager;

	// 时间策略列表
	private List<TimePolicy> timePolicyList;

	// 关键字
	private String keyword;

	// 高级查询所用字段
	private String timePolicyName;

	// 时间策略状态
	private int timePolicyStatus = 2;

	// 时间策略的生效方式
	private int timePolicyEffectWay = 2;

	// 批量操作时复选框的值
	private String ids;

	// 时间策略Id
	private long timePolicyId;

	// 策略的执行方式
	private int timePolicyexecuteWay;

	// 策略描述
	private String timePolicyMemo;

	// 存放周、时间点的处理
	private String week;

	private String hour;

	private String[] hours;

	private String[] weeks;

	private AuditService auditManager;
	
	//请求的action字符串
    private String actionStr ="query.action"; 
    
    // 排序Type
    private String sortType;
    
    // 要查询的字段
    private String field ;  
    
    //保存用户
    private Map<String, Object> sessionMap;
    
    //消息推送管理类
    private SendMessage msg;
    
    //根据时间策略获得的用户集合
    private List<User> userList;
	/**
	 * 查询时间策略-快速检索，高级检索
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#queryTimePolicy(Map, Page)
	 */
	public String query() {
		log.info("[TimePolicyAction] Enter queryTimePolicy....");

		HttpServletRequest request = super.getRequest();

		Page page = null;

		SearchResult sr = null;

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
		if (StringUtil.isNotBlank("timePolicyName")) {
			map.put("timePolicyName", timePolicyName);
		}

		if (StringUtil.isNotBlank("timePolicyMemo")) {
			map.put("timePolicyMemo", timePolicyMemo);
		}

		// 判断传递过来的timePolicyStatusd的值是否为1，0.为1，0放入map
		if (timePolicyStatus == 0 || timePolicyStatus == 1) {
			map.put("timePolicyStatus", timePolicyStatus);
		}
		// 判断传递过来的timePolicyEffectWay的值是否为1,0为1，0放入map
		if (timePolicyEffectWay == 1 || timePolicyEffectWay == 0) {
			map.put("timePolicyEffectWay", timePolicyEffectWay);
		}
		// 得到查询结果
		sr = timePolicyManager.queryTimePolicy(map, page);

		// 对查找的结果为分页赋值
		if (sr != null) {
			timePolicyList = (List<TimePolicy>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		} else {
			request.setAttribute("Page", new Page(Page.DEFAULT_PAGE_SIZE, 0));
		}
		return SUCCESS;
	}

	/**
	 * 主要对时间策略的修改，包括策略的锁定，激活
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#updateTimePolicyStatus(long,
	 *      int)
	 */
	public String updateStatus() {
		log.info("[TimePolicyAcrion]Enter method timePolicy updateStatus");
		List<String> fieldList = new ArrayList<String>();

		if (StringUtil.isNotBlank(ids)) {

			// 操作多条记录
			if (ids.indexOf(",") > 0) {
				String[] checked = ids.split(",");

				// 循环遍历需要更新状态的时间策略
				for (String checkid : checked) {
					TimePolicy timeObject = timePolicyManager
							.queryTimePolicyById(Long.parseLong(checkid));
					fieldList.add(timeObject.getTimePolicyName());
					if (timeObject.getTimePolicyStates() != timePolicyStatus) {
						timePolicyManager.updateTimePolicyStatus(
								timeObject.getTimePolicyId(), timePolicyStatus);
					}else {
						boolean check = timePolicyJudge(timeObject);
						userList = timePolicyManager.queryUserByTimePolicyId(Integer.parseInt(ids));
						if (timeObject.getTimePolicyEffectWay()==0) {
							if (check) {
								if (userList!=null && userList.size()>0) {
									for (User user : userList) {
										sessionMap.put("userinfo", user.getUserLoginName());
										msg.sendMessageAuto(user.getUserLoginName(), "时间策略已经激活，当前时间不在允许范围内，请及时下线！");
									}
								}
							}
						}
					}
				}

			}
			// 操作一条记录
			else {
				TimePolicy timeObject1 = timePolicyManager
						.queryTimePolicyById(Long.parseLong(ids));
				fieldList.add(timeObject1.getTimePolicyName());
				if (timeObject1.getTimePolicyStates() != timePolicyStatus) {
					timePolicyManager.updateTimePolicyStatus(
							timeObject1.getTimePolicyId(), timePolicyStatus);
				}else {
					boolean check = timePolicyJudge(timeObject1);
					userList = timePolicyManager.queryUserByTimePolicyId(Integer.parseInt(ids));
					if (timeObject1.getTimePolicyEffectWay()==0) {
						if (check) {
							if (userList!=null && userList.size()>0) {
								for (User user : userList) {
									sessionMap.put("userinfo", user.getUserLoginName());
									msg.sendMessageAuto(user.getUserLoginName(), "时间策略已经激活，请及时下线修改密码！");
								}
							}
						}
					}
					
				}
			}

			// 审计日志
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "时间策略", super
					.getRequest().getRemoteAddr(), fieldList);

			// syslog
			/*String logString = "";
			logString = "登录名："
					+ ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
							.getUserLoginName() + "  源IP:"
					+ getRequest().getRemoteAddr() + "   操作时间："
					+ DateUtil.curDateTimeStr19() + "   操作类型:更改时间策略状态";

			logManager.writeSystemAuditLog(logString);*/
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改时间策略状态");
		}
		return SUCCESS;
	}

	/**
	 * 标记删除时间策略 根据传入的ids判断是否选中多个值,进行批量操作
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#deleteTimePolicy(long)
	 */
	public String deletetimePolicy() {
		log.info("[TimePolicyAction] enter deletetimePolicyAction....");
		List<String> fieldList = new ArrayList<String>();
		// 判断获得的id为一个或者多个，多个执行if内函数,一个执行else内函数
		if (ids.indexOf(",") > 0) {
			// 将获得的ids按照,拆分成数组
			String[] checked = ids.split(",");
			// 循环遍历标记删除时间策略
			for (String checkid : checked) {
				TimePolicy timeObject = timePolicyManager
						.queryTimePolicyById(Long.parseLong(checkid));
				fieldList.add(timeObject.getTimePolicyName());
				timePolicyManager.deleteTimePolicy(Integer.parseInt(checkid));
			}
		} else {
			TimePolicy timeObject = timePolicyManager.queryTimePolicyById(Long
					.parseLong(ids));
			fieldList.add(timeObject.getTimePolicyName());
			timePolicyManager.deleteTimePolicy(Integer.parseInt(ids));
		}

		// 审计日志
		auditManager.insertByDeleteOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "时间策略", super
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
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除时间策略");

		return SUCCESS;
	}

	/**
	 * 编辑时间策略,跳转到添加页面 根据时间策略id是否有值，有值的话跳转到修改页面，没值的话跳转到添加页面
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#queryTimePolicyById(long)
	 */
	public String editTimePolicy() {
		log.info("[TimePolicyAction] enter editTimePolicy......");

		// 如果ID有值
		if (timePolicyId != 0) {
			// 根据传入的id查询出对象
			timePolicy = timePolicyManager.queryTimePolicyById(timePolicyId);
			// 获得对象的时间点
			hour = timePolicy.getTimePolicyHour();
			// 判断时间点是否为空
			if (StringUtil.isNotBlank(hour)) {
				// 将时间点截取,放入数组
				hours = new String[hour.length()];
				for (int i = 0; i < hour.length(); i++) {
					hours[i] = hour.substring(i, i + 1);
				}
			}
			// 获得天数点
			week = timePolicy.getTimePolicyWeek();
			// 判断周点是否为空
			if (StringUtil.isNotBlank(week)) {
				// 将周点截取,放入数组
				weeks = new String[week.length()];
				for (int j = 0; j < week.length(); j++) {
					weeks[j] = week.substring(j, j + 1);
				}
			}

		}

		return SUCCESS;
	}

	/**
	 * 添加或者修改时间策略
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#updateTimePolicy(TimePolicy)
	 */
	public String updateTimePolicy() {
		log.info("[TimeTimePolicyAction] enter updateTimePolicy...");
		List<String> fieldList = new ArrayList<String>();
		fieldList.add(timePolicy.getTimePolicyName() + "("
				+ timePolicy.getTimePolicyName() + ")");
		// 获得session内存储的用户对象
		User u = (User) super.getSession().getAttribute("SOC_LOGON_USER");
		// 判断时间对象Id是否存在,不存在将用户的登录名赋值给操作用户名
		if (timePolicy.getTimePolicyId() == 0) {
			timePolicy.setTimePolicyUserLoginName(u.getUserLoginName());

			// 审计日志
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "时间策略", super
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
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增时间策略");

		} else {

			// 审计日志
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "时间策略", super
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
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改时间策略");
		}
		// 将时间点赋值到对象内
		timePolicy.setTimePolicyHour(hour);
		timePolicy.setTimePolicyWeek(week);
		timePolicy.setTimePolicyexecuteWay(timePolicyexecuteWay);
		// 执行更新函数
		timePolicyManager.updateTimePolicy(timePolicy);
		boolean check = timePolicyJudge(timePolicy);
		userList = timePolicyManager.queryUserByTimePolicyId(timePolicy.getTimePolicyId());
		if (timePolicy.getTimePolicyEffectWay()==0) {
			if (check) {
				if (userList!=null && userList.size()>0) {
					for (User user : userList) {
						sessionMap.put("userinfo", user.getUserLoginName());
						msg.sendMessageAuto(user.getUserLoginName(), "时间策略已经修改，当前时间不在允许范围内,请及时下线！");
					}
					
				}
			}
		}
		

		return SUCCESS;
	}

	/**
	 * 检测时间策略名称是否被使用 根据策略名称查找策略,如果存在向页面返回"flag=true",不存在向页面返回"flag=false"
	 * 
	 * @return
	 * @see com.soc.service.policy.TimePolicyService#queryByTimePolicyName(String)
	 */
	public void checktimePolicyName() {
		LOG.info("[TimePolicyAction] enter method checktimePolicyName() ...");

		// 标识此策略名是否存在
		String flag = "false";

		if (StringUtil.isNotBlank("timePolicyName")) {
			List<TimePolicy> list = timePolicyManager
					.queryByTimePolicyName(timePolicyName);
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
	 * @return String
	 */
	public String sort(){
		LOG.info("[TimePolicyAction] enter method sort() ...");
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
            
            sr = timePolicyManager.sort(map, page);
            if (sr != null)
            {
            	timePolicyList = sr.getList();
                request.setAttribute("timePolicyList", timePolicyList);
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

	public String redirect() {
		return SUCCESS;
	}

	public TimePolicy getTimePolicy() {
		return timePolicy;
	}

	public void setTimePolicy(TimePolicy timePolicy) {
		this.timePolicy = timePolicy;
	}

	public TimePolicyService getTimePolicyManager() {
		return timePolicyManager;
	}

	public void setTimePolicyManager(TimePolicyService timePolicyManager) {
		this.timePolicyManager = timePolicyManager;
	}

	public List<TimePolicy> getTimePolicyList() {
		return timePolicyList;
	}

	public void setTimePolicyList(List<TimePolicy> timePolicyList) {
		this.timePolicyList = timePolicyList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTimePolicyName() {
		return timePolicyName;
	}

	public void setTimePolicyName(String timePolicyName) {
		this.timePolicyName = timePolicyName;
	}

	public int getTimePolicyStatus() {
		return timePolicyStatus;
	}

	public void setTimePolicyStatus(int timePolicyStatus) {
		this.timePolicyStatus = timePolicyStatus;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public long getTimePolicyId() {
		return timePolicyId;
	}

	public void setTimePolicyId(long timePolicyId) {
		this.timePolicyId = timePolicyId;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String[] getHours() {
		return hours;
	}

	public void setHours(String[] hours) {
		this.hours = hours;
	}

	public String[] getWeeks() {
		return weeks;
	}

	public void setWeeks(String[] weeks) {
		this.weeks = weeks;
	}

	public int getTimePolicyexecuteWay() {
		return timePolicyexecuteWay;
	}

	public void setTimePolicyexecuteWay(int timePolicyexecuteWay) {
		this.timePolicyexecuteWay = timePolicyexecuteWay;
	}

	public String getTimePolicyMemo() {
		return timePolicyMemo;
	}

	public void setTimePolicyMemo(String timePolicyMemo) {
		this.timePolicyMemo = timePolicyMemo;
	}

	public int getTimePolicyEffectWay() {
		return timePolicyEffectWay;
	}

	public void setTimePolicyEffectWay(int timePolicyEffectWay) {
		this.timePolicyEffectWay = timePolicyEffectWay;
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
	
	

}
