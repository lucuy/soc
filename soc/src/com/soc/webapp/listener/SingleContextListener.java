package com.soc.webapp.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.user.OnlineUser;





public class SingleContextListener implements ServletContextListener { 
  
    private transient Log log = LogFactory.getLog(getClass());
     
    public void contextInitialized(ServletContextEvent event) {
    	GlobalConfig.ctx = event.getServletContext().getRealPath("/");
    	ServletContext context = event.getServletContext();
        context.setAttribute("SOC_ONLINE_USERLIST", new ArrayList<OnlineUser>());
    }
    
    //Notification that the servlet context is about to be shut down
    public void contextDestroyed(ServletContextEvent event) {
    	ServletContext context = event.getServletContext();
        context.removeAttribute("SOC_ONLINE_USERLIST");
    }
}
