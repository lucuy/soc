/*
 * 文 件 名:  PolicyFilter.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-17
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.webapp.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.dao.user.UserDao;
import com.soc.model.policy.Address;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.policy.PasswordPolicy;
import com.soc.model.policy.TimePolicy;
import com.soc.model.user.User;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.impl.LogWriteServiceImpl;
import com.soc.service.user.LoginService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.Base64;
import com.util.DateUtil;
import com.util.StringUtil;

/**
 * 登录时候策略的验证
 * 根据登录的时候的用户登录名，ip地址验证
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-17]
 * @see  com.soc.service.user.LoginService,com.soc.service.UserService
 * @since 
 */

public class PolicyFilter extends BaseAction implements Filter
{
    
    
    //策略验证不通过页面
    private String policy_LIMIT = null;
    public static  int loginNum=0;
    public PolicyFilter()
    {
        policy_LIMIT = "/pages/commons/503.jsp";
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        // TODO Auto-generated method stub
        HttpServletRequest hRequest = (HttpServletRequest)request;
        //HttpServletResponse hResponse = (HttpServletResponse)response;
        HttpSession session = hRequest.getSession();
        String loginName = hRequest.getParameter("loginName");
        String password = hRequest.getParameter("password");
//        int networkTopologyLogin ;
//        if (hRequest.getParameter("networkTopologyLogin") == null) {
//        	networkTopologyLogin = 0;
//		}else {
//			networkTopologyLogin = Integer.parseInt(hRequest.getParameter("networkTopologyLogin"));
//		}
        		
       /* String imgCode = hRequest.getParameter("imgCode");
        String relCode = null;*/
        String policyMessage = "";
       /* Object ob = session.getAttribute("rand");
        if (ob != null)
        {
            relCode = ob.toString();
        }*/
        WebApplicationContext wac =
            WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        LoginService loginManager = (LoginService)wac.getBean("loginManager");
        LogWriteServiceImpl logManager1=(LogWriteServiceImpl) wac.getBean("logManager");
        //定义时间策略的list
        List<TimePolicy> timePolicy = null;
        //定义地址策略的list
        List<AddressPolicy> addressPolicy = null;
        //定义密码策略
        List<PasswordPolicy> passwordList = null;
        List<String> fieldList = new ArrayList<String>();
        //用户业务处理类
        UserDao userDao = (UserDao)wac.getBean("userDao");
         AuditService auditManager=(AuditService) wac.getBean("auditManager");;
        //用户处理业务类
        UserService userManager = (UserService)wac.getBean("userManager");
        //得到要求登录的用户信息
        List<User> userlist = userManager.queryByUserLoginName(loginName);
        if (!userlist.isEmpty())
        {
            User u = userlist.get(0);
            long userId = u.getUserId();
            int smailLockTime = Integer.MAX_VALUE;
            //获得用户的密码开始锁定时间
            Date lockTime = (Date)u.getUserLockTime();
            //获得用户相关的时间策略
            timePolicy = userManager.queryRelTimePolicy(userId);
            //获得用户关联的地址策略
            addressPolicy = userManager.queryRelAddressPolicy(userId);
            //获得用户关联的密码策略
            passwordList = userManager.queryRelPasswordPolicy(userId);
            
            //将时间策略按照允许，禁止划分
            int count = (int)u.getUserFailNum() + 1;
            //当前时间
            long nowMilliSecond = new Date().getTime();
            //禁止时间策略
            List<TimePolicy> timePolicyList1 = new ArrayList<TimePolicy>();
            //允许时间策略
            List<TimePolicy> timePolicyList2 = new ArrayList<TimePolicy>();
            String logString = "";//syslog外发信息
            //判断当前时间是否为锁定时间,当前为锁定时间
            if (lockTime != null)
            {
                //开始锁定时间
                long startMilliSecond = lockTime.getTime();
                //已锁定时间
                long lockMilliSecon = nowMilliSecond - startMilliSecond;
                
                int lockUsedSecond = (int)(lockMilliSecon / 60000);
                
                if (lockTimeJudge(passwordList, lockUsedSecond))
                {
                    u.setUserLockTime(null);
                    u.setUserFailNum(0);
                    //u.setUserPassword(password);
                    count = 1;
                    lockTime = null;
                    userDao.updateUser(u);
                }
            }
            //让count+1t
           
           
            if (!StringUtil.equals(u.getUserPassword(), Base64.encodeString(password.trim())))
            {
                
                u.setUserFailNum(count);
                userDao.updateUser(u);
                
                int smailFaildNum = Integer.MAX_VALUE;
                if (passwordList != null)
                {
                    for (PasswordPolicy p : passwordList)
                    {
                        if (p.getPasswordPolicyStatus() == 1)
                        {
                            if (p.getPasswordPoicyFailLockNumber() != 0)
                            {
                                int fieldNum = p.getPasswordPoicyFailLockNumber();
                                if (smailFaildNum >= fieldNum)
                                {
                                    smailFaildNum = fieldNum;
                                }
                            }
                        }
                    }
                    if (smailFaildNum != Integer.MAX_VALUE)
                    {
                    	loginNum=smailFaildNum;
                        request.setAttribute("FailNumber", smailFaildNum - u.getUserFailNum());
                        fieldList.add(u.getUserLoginName() + "(" + u.getUserLoginName() + "的密码输入错误，尝试第"+u.getUserFailNum()+"次登录)");
            			String ip = request.getRemoteAddr();
            			auditManager.insertBySystemOperator(u.getUserId(), "账户尝试登录",ip, fieldList);
            			/*logString = "登录名："
            					+ u.getUserLoginName() + "  源IP:"
            					+ ip + "   操作时间："
            					+ DateUtil.curDateTimeStr19() + "   操作类型:用户登录失败";*/
            			//logManager1.writeSystemAuditLog(logString); 
            			logManager1.writeSystemAuditLog(u.getUserLoginName(), ip, DateUtil.curDateTimeStr19(), "用户登录失败");
                    }
                    
                    if (checkAccountPasswordTimes(passwordList, count))
                    {
                        u.setUserLockTime(new Date());
                        lockTime = new Date();
                        userDao.updateUser(u);
                    }
                    
                }
            }
            //用户输入对的数据,判断
            else
            {
                int temp = getMinNum(passwordList);
                //判断用户输入少于次数，且锁定时间为空时侯 
                if (u.getUserFailNum() < temp && lockTime == null)
                {
                    u.setUserLockTime(null);
                    u.setUserFailNum(0);
                    userDao.updateUser(u);
                    count = 0;
                    lockTime = null;
                    
                }
            }
            
            //判断为输入错误次数大于要求次数时
            if (lockTime != null && count != 0)
            {
                if (passwordList != null)
                {
                    for (PasswordPolicy p : passwordList)
                    {
                        if (p.getPasswordPolicyStatus() == 1)
                        {
                            int lockSecond = p.getPasswordPolicyLockRecoveTime();
                            
                            if (lockSecond != 0)
                            {
                                if (smailLockTime >= lockSecond)
                                {
                                    smailLockTime = lockSecond;
                                }
                            }
                            
                        }
                    }
                    if (smailLockTime == Integer.MAX_VALUE)
                    {
                        smailLockTime = 0;
                    }
                    if (smailLockTime != Integer.MAX_VALUE)
                    {
                        int unlockTime;
                        
                        if (smailLockTime != 0)
                        {
                            unlockTime = smailLockTime - (int)((new Date().getTime() - lockTime.getTime()) / 60000);
                        }
                        else
                        {
                            unlockTime = smailLockTime;
                        }
                       policyMessage = "您的密码输入错误超过最大次数限制,，账户已被锁定。将于" + unlockTime + "分钟后解锁";
                       fieldList.removeAll(fieldList);
            			fieldList.add(u.getUserLoginName() + "(" + u.getUserLoginName() + "的密码输入错误超过限制("+loginNum+"次)，账户已被锁定。)");
            			String ip = request.getRemoteAddr();
            			auditManager.insertBySystemOperator(u.getUserId(), "登录被锁定",ip, fieldList);
            			
            			
            			
            			
                      /*  logString="登录名："+u.getUserLoginName()+"  源IP:"+request.getRemoteAddr()+"   操作时间："+DateUtil.curDateTimeStr19()
                        +"   操作类型：锁定用户";*/
                        
                       /* for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
                			for (String key : map.keySet()) {
                				SyslogIF syslogIF = map.get(key);
                				SysLogSender.sender(syslogIF, logString);
                			}
                		}*/
            			logManager1.writeSystemAuditLog(u.getUserLoginName(), request.getRemoteAddr(), DateUtil.curDateTimeStr19(), "锁定用户");
                      // logManager1.writeSystemAuditLog(logString);
                    }
                    else
                    {
                    	policyMessage = "您的密码输入错误超过最大次数限制,，账户已被锁定。请与管理员联系";
                        fieldList.add(u.getUserLoginName() + "(" + u.getUserLoginName() + ")");
                        auditManager.insertBySystemOperator(u.getUserId(), "锁定用户尝试登录",request.getRemoteAddr(), fieldList);
            			
            		
            			
            			
                       /* logString="登录名："+u.getUserLoginName()+"  源IP:"+request.getRemoteAddr()+"   操作时间："+DateUtil.curDateTimeStr19()
                        +"   操作类型：登录被锁定";*/
                        logManager1.writeSystemAuditLog(u.getUserLoginName(), request.getRemoteAddr(), DateUtil.curDateTimeStr19(), "登录被锁定");
                        
                        /* for (Map<String, SyslogIF> map : GlobalConfig.SYSLOG_OBJECT) {
                			for (String key : map.keySet()) {
                				SyslogIF syslogIF = map.get(key);
                				SysLogSender.sender(syslogIF, logString);
                			}
                		}*/
                      //  logManager1.writeSystemAuditLog(logString);
                    }
                    request.setAttribute("message", policyMessage);
                 
                    request.getRequestDispatcher(policy_LIMIT).forward(hRequest, response);
                    return;
                }
            
           }
            //将时间策略按照允许，禁止划分
            if (timePolicy != null)
            {
                for (TimePolicy t : timePolicy)
                {
                    //判断策略的状态(激活或者锁定)
                    if (t.getTimePolicyStates() == 1)
                    {
                        if (t.getTimePolicyEffectWay() == 0)
                        {
                            timePolicyList1.add(t);
                        }
                        else
                        {
                            timePolicyList2.add(t);
                        }
                    }
                }
            }
            
            //先判断禁止时间策略内是否包含时间点
            if (!timePolicyList1.isEmpty())
            {
                if (timePolicyJudge(timePolicyList1, hRequest) == true)
                {
                    
                    policyMessage = "现在的时间不在允许策略的时间段内，不能进行登录";
                    fieldList.add(u.getUserLoginName() + "(" + u.getUserLoginName() + ")");
                    auditManager.insertBySystemOperator(u.getUserId(), "非允许时间内用户尝试登录",request.getRemoteAddr(), fieldList);
                   /* logString = "登录名："
        					+ u.getUserLoginName() + "  源IP:"
        					+ request.getRemoteAddr() + "   操作时间："
        					+ DateUtil.curDateTimeStr19() + "   操作类型:用户登录失败";
        			logManager1.writeSystemAuditLog(logString); */
                    logManager1.writeSystemAuditLog(u.getUserLoginName(), request.getRemoteAddr(), DateUtil.curDateTimeStr19(), "用户登录失败");
                    request.setAttribute("message", policyMessage);
                    request.getRequestDispatcher(policy_LIMIT).forward(hRequest, response);
                    return;
                }
            }
            else if (!timePolicyList2.isEmpty())
            {
                
                if (timePolicyJudge(timePolicyList2, hRequest) == false)
                {
                    policyMessage = "现在的时间不在允许策略的时间段内，不能进行登录";
                    fieldList.add(u.getUserLoginName() + "(" + u.getUserLoginName() + ")");
                    auditManager.insertBySystemOperator(u.getUserId(), "非允许时间内用户尝试登录",request.getRemoteAddr(), fieldList);
                   /* logString = "登录名："
        					+ u.getUserLoginName() + "  源IP:"
        					+ request.getRemoteAddr() + "   操作时间："
        					+ DateUtil.curDateTimeStr19() + "   操作类型:用户登录失败";
        			logManager1.writeSystemAuditLog(logString); */
                    logManager1.writeSystemAuditLog(u.getUserLoginName(), request.getRemoteAddr(), DateUtil.curDateTimeStr19(), "用户登录失败");
                    request.setAttribute("message", policyMessage);
                    request.getRequestDispatcher(policy_LIMIT).forward(hRequest, response);
                    return;
                }
            }
            
            //将地址策略按照允许，禁止划分
            List<AddressPolicy> addressPolicy1 = new ArrayList<AddressPolicy>();
            
            List<AddressPolicy> addressPolicy2 = new ArrayList<AddressPolicy>();
            
            if (addressPolicy != null)
            {
                
                for (AddressPolicy a : addressPolicy)
                {
                    //判断激活或者锁定，激活状态 
                    if (a.getAddressPolicyStatus() == 1)
                    {
                        if (a.getAddressPolicyEffectWay() == 0)
                        {
                            addressPolicy1.add(a);
                        }
                        else
                        {
                            addressPolicy2.add(a);
                        }
                    }
                }
            }
            //判断禁止策略内是否含有该ip地址
            if (!addressPolicy1.isEmpty())
            {
                
                if (addressPolicyJudge(addressPolicy1, hRequest) == true)
                {
                    policyMessage = "你的ip地址不在允许策略地址范围内，不能进行登录";
                    fieldList.add(u.getUserLoginName() + "(" + u.getUserLoginName() + ")");
                    auditManager.insertBySystemOperator(u.getUserId(), "非允许IP地址用户尝试登录",request.getRemoteAddr(), fieldList);
                   /* logString = "登录名："
        					+ u.getUserLoginName() + "  源IP:"
        					+ request.getRemoteAddr() + "   操作时间："
        					+ DateUtil.curDateTimeStr19() + "   操作类型:用户登录失败";
        			logManager1.writeSystemAuditLog(logString); */
                    logManager1.writeSystemAuditLog(u.getUserLoginName(), request.getRemoteAddr(), DateUtil.curDateTimeStr19(), "用户登录失败");
                    request.setAttribute("message", policyMessage);
                    request.getRequestDispatcher(policy_LIMIT).forward(hRequest, response);
                    return;
                }
            }
            //允许策略内是否包含
            else if (!addressPolicy2.isEmpty())
            {
                if (addressPolicyJudge(addressPolicy2, hRequest) == false)
                {
                    policyMessage = "你的ip地址不在允许策略地址范围内，不能进行登录";
                    fieldList.add(u.getUserLoginName() + "(" + u.getUserLoginName() + ")");
                    auditManager.insertBySystemOperator(u.getUserId(), "非允许IP地址用户尝试登录",request.getRemoteAddr(), fieldList);
                    /*logString = "登录名："
        					+ u.getUserLoginName() + "  源IP:"
        					+ request.getRemoteAddr() + "   操作时间："
        					+ DateUtil.curDateTimeStr19() + "   操作类型:用户登录失败";
        			logManager1.writeSystemAuditLog(logString); */
                    logManager1.writeSystemAuditLog(u.getUserLoginName(), request.getRemoteAddr(), DateUtil.curDateTimeStr19(), "用户登录失败");
                    request.setAttribute("message", policyMessage);
                    request.getRequestDispatcher(policy_LIMIT).forward(hRequest, response);
                    return;
                }
                
            }
            
        }
        
        chain.doFilter(request, response);
    }
    
    /** {@inheritDoc} */
    
    @Override
    public void init(FilterConfig arg0)
        throws ServletException
    {
        // TODO Auto-generated method stub
        
    }
    
    //判断锁定的时间是否大于密码策略内的锁定时间
    boolean lockTimeJudge(List<PasswordPolicy> passwordList, int lockedSec)
    {
        boolean flag = false;
        int smailLockTime = Integer.MAX_VALUE;
        if (passwordList != null && !passwordList.isEmpty())
        {
            for (PasswordPolicy p : passwordList)
            {
                if (p.getPasswordPolicyStatus() == 1)
                {
                    if (p.getPasswordPolicyLockRecoveTime() != 0)
                    {
                        int lockSecond = p.getPasswordPolicyLockRecoveTime();
                        if (smailLockTime >= lockSecond)
                        {
                            smailLockTime = lockSecond;
                        }
                    }
                }
            }
            if (lockedSec > smailLockTime)
            {
                flag = true;
            }
            if (smailLockTime == Integer.MAX_VALUE)
            {
                flag = true;
            }
        }
        return flag;
    }
    
    //判断最小允许输错次数
    boolean checkAccountPasswordTimes(List<PasswordPolicy> passwordList, int count)
    {
        boolean flag = false;
        int smailFieldNum = Integer.MAX_VALUE;
        
        if (passwordList != null && !passwordList.isEmpty())
        {
            for (PasswordPolicy p : passwordList)
            {
                if (p.getPasswordPolicyStatus() == 1)
                {
                    if (p.getPasswordPoicyFailLockNumber() != 0)
                    {
                        int fieldNumber = p.getPasswordPoicyFailLockNumber();
                        
                        if (smailFieldNum >= fieldNumber)
                        {
                            smailFieldNum = fieldNumber;
                        }
                    }
                }
            }
            if (count >= smailFieldNum)
            {
                flag = true;
            }
        }
        return flag;
    }
    
    /**
     * 获得允许输入错误的最小次数
     * <功能详细描述>
     * @param passwordList
     * @return
     * @see [类、类#方法、类#成员]
     */
    int getMinNum(List<PasswordPolicy> passwordList)
    {
        int smailFieldNum = Integer.MAX_VALUE;
        if (passwordList != null && !passwordList.isEmpty())
        {
            for (PasswordPolicy p : passwordList)
            {
                if (p.getPasswordPolicyStatus() == 1)
                {
                    if (p.getPasswordPoicyFailLockNumber() != 0)
                    {
                        int fieldNumber = p.getPasswordPoicyFailLockNumber();
                        
                        if (smailFieldNum >= fieldNumber)
                        {
                            smailFieldNum = fieldNumber;
                        }
                    }
                }
            }
        }
        return smailFieldNum;
    }
    
    /**
     * 判断时间策略
     * 根据用户登录的时间判断是否包含在时间段内
     * @param timePolicyList List<TimePolicy>
     * @param request HttpServletRequest
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean timePolicyJudge(List<TimePolicy> timePolicyList, HttpServletRequest request)
    {
    	 SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", new Locale("ENGLISH", "CHINA"));
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
        boolean flag = true;
        for (TimePolicy timePolicy : timePolicyList)
        {
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
                            continue;
                        }
                    }
                    catch (ParseException e)
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
                        continue;
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
                    continue;
                }
                
            }
            if (flag)
            {
                break;
            }
        }
        return flag;
    }
    
    /**
     * 用户地址策略验证
     * 根据用户的登录的ip地址判断是否包含在地址段内
     * @param addressPolicyList List<AddressPolicy>
     * @param request HttpServletRequest
     * @return
     * @see [类、类#方法、类#成员]
     */
    boolean addressPolicyJudge(List<AddressPolicy> addressPolicyList, HttpServletRequest request)
    {
        
        boolean flag = true;
        //获得当前登录用户的ip地址
        String localhost = null;
        localhost = request.getRemoteAddr();
        
        if (localhost.contains(":"))
        {
            localhost = "127.0.0.1";
        }
        
        String[] ipNow = localhost.split("\\.");
        
        //存储地址
        List<Address> addresslist = new ArrayList<Address>();
        //起始ip地址数组
        String[] ipstart = null;
        
        //终止ip地址数组
        String[] ipsend = null;
        for (AddressPolicy addressPolicy : addressPolicyList)
        {
            //获得地址策略的相关对象
            addresslist = addressPolicy.getAddressList();
            
            if (!addresslist.isEmpty())
            {
                for (Address a : addresslist)
                {
                    ////System.out.println(localhost);
                    ipstart = a.getAddressStartIp().split("\\.");
                    ipsend = a.getAddressEndIp().split("\\.");
                    
                    /*//System.out.println(compareToIpStart(ipstart,ipNow));
                    //System.out.println(compareToIpEnd(ipsend, ipNow));*/
                    
                    if (compareToIpStart(ipstart, ipNow) && compareToIpEnd(ipsend, ipNow))
                    {
                        /*//System.out.println(compareToIpStart(ipstart,ipNow));
                        //System.out.println(compareToIpEnd(ipsend, ipNow));*/
                        flag = true;
                        return flag;
                    }
                    else
                    {
                        flag = false;
                        continue;
                    }
                }
            }
            else
            {
                flag = true;
                continue;
            }
        }
        return flag;
    }
    
    /**
     * 判断传入地址与起始地址大小
     * <功能详细描述>
     * @param ipStart
     * @param ipNow
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean compareToIpStart(String[] ipStart, String[] ipNow)
    {
        Boolean flag = false;
        if (Integer.parseInt(ipStart[0]) > Integer.parseInt(ipNow[0]))
        {
            flag = false;
            return flag;
        }
        else if (Integer.parseInt(ipStart[0]) == Integer.parseInt(ipNow[0]))
        {
            //判断第二位
            if (Integer.parseInt(ipStart[1]) > Integer.parseInt(ipNow[1]))
            {
                flag = false;
                return flag;
            }
            else if (Integer.parseInt(ipStart[1]) == Integer.parseInt(ipNow[1]))
            {
                //判断第三位
                if (Integer.parseInt(ipStart[2]) > Integer.parseInt(ipNow[2]))
                {
                    flag = false;
                    return flag;
                }
                else if (Integer.parseInt(ipStart[2]) == Integer.parseInt(ipNow[2]))
                {
                    //判断第四位
                    if (Integer.parseInt(ipStart[3]) > Integer.parseInt(ipNow[3]))
                    {
                        flag = false;
                        return flag;
                    }
                    else
                    {
                        flag = true;
                        return flag;
                    }
                }
                else
                {
                    flag = true;
                    return flag;
                }
            }
            else
            {
                flag = true;
                return flag;
            }
        }
        else
        {
            flag = true;
            return flag;
        }
        
    }
    
    /**
     * 判断当前地址是否小于终止地址
     * 根据传入的当前地址的数组，跟终止地址数组判断当前地址是否小于终止地址
     * @param ipEnd
     * @param ipNow
     * @return
     * @see [类、类#方法、类#成员]
     */
    Boolean compareToIpEnd(String[] ipEnd, String[] ipNow)
    {
        if (Integer.parseInt(ipEnd[0]) < Integer.parseInt(ipNow[0]))
        {
            return false;
        }
        else if (Integer.parseInt(ipEnd[0]) == Integer.parseInt(ipNow[0]))
        {
            if (Integer.parseInt(ipEnd[1]) < Integer.parseInt(ipNow[1]))
            {
                return false;
            }
            else if (Integer.parseInt(ipEnd[1]) == Integer.parseInt(ipNow[1]))
            {
                if (Integer.parseInt(ipEnd[2]) < Integer.parseInt(ipNow[2]))
                {
                    return false;
                }
                else if (Integer.parseInt(ipEnd[2]) == Integer.parseInt(ipNow[2]))
                {
                    if (Integer.parseInt(ipEnd[3]) < Integer.parseInt(ipNow[3]))
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                else
                {
                    return true;
                }
                
            }
            else
            {
                return true;
            }
        }
        else
        {
            return true;
        }
    }
    public static void main(String[] args) throws ParseException {
		 
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", new Locale("ENGLISH", "CHINA"));
		System.err.println(sdf.parse(date+"").getTime());
	}
}
/*class SendMailThread extends Thread {
    private HttpSession session;
    private String htmlStr;
    
    public SendMailThread(HttpSession session, String htmlStr) {
        this.session = session;
        this.htmlStr = htmlStr;
    }
    
    public void run() {
        SendEmail.sendEmail(session,htmlStr);
    }
}*/
