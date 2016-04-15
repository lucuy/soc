package com.soc.webapp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import com.soc.model.user.OnlineUser;
import com.soc.model.user.User;
import com.util.SessionContext;


public class SingleSessionListener implements HttpSessionListener  { 
    
    private transient Log Log = LogFactory.getLog(getClass());
  
    public static Map userMap = new HashMap();
    private SessionContext myc=SessionContext.getInstance();
    
    //Notification that a session was created
    public void sessionCreated(HttpSessionEvent event) {
    	HttpSession session = event.getSession() ; 
    	//session超时时间为10分钟
    	session.setMaxInactiveInterval(600) ; 
    	myc.AddSession(session);
    }
    	  
    //Notification that a session was invalidated
    public void sessionDestroyed(HttpSessionEvent event) {
    	// 用户离线触发
        Log.info("User Logout...");
        
    	User user = (User)event.getSession().getAttribute("SOC_LOGON_USER");
    	if(user != null) {
	    	List<OnlineUser> userList = (List<OnlineUser>)event.getSession().getServletContext().getAttribute("SOC_ONLINE_USERLIST");
	    	
	    	// 遍历在线用户列表，将该离线用户从列表中移除
	    	List<OnlineUser> singleUserList = new ArrayList<OnlineUser>(); 
	    	for(OnlineUser onlineUser : userList) {
	    		if(onlineUser.getUserId() == user.getUserId() && onlineUser.getSessionId().equals(event.getSession().getId())) {
	    			userList.remove(onlineUser);
	    			break;
	    		}
	    	}
	    	//System.out.println(userList.size());
    	}
    	
    	HttpSession session=event.getSession();
    	myc.DelSession(session);
    	return;  
    }
}
