package com.push.util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;

import com.util.StringUtil;

public class DwrScriptSessionManagerUtil extends DwrServlet{
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	 
	  Container container = ServerContextFactory.get().getContainer();
     
	  ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
     
      ScriptSessionListener listener = new ScriptSessionListener() {
    	  	public void sessionCreated(ScriptSessionEvent ev) {
                    HttpSession session = WebContextFactory.get().getSession();
                    String userId = (String)session.getAttribute("userinfo");
                    String userId1 = (String)session.getAttribute("userId1");
                    if(StringUtil.isNotEmpty(userId1)){
                    	//实时监控
                    	ev.getSession().setAttribute("userId1", "admin");
                    }
                    if(StringUtil.isNotEmpty(userId)){
                    	//修改用户的时候提示用户
                    	ev.getSession().setAttribute("userId", userId);
                    }
                    
             }
             public void sessionDestroyed(ScriptSessionEvent ev) {
             }
      };
      
      manager.addScriptSessionListener(listener);
  }

}
