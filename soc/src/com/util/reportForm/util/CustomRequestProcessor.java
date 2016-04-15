package com.util.reportForm.util;

import javax.servlet.http.*;
import java.util.Locale;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.Globals;

/**
 * 请求处理的入口
 * 一个用户的请求处理，检查每次的连接是不是来自。 If a Locale is not in the session or
 * the one in the session doesn't match the request, the Locale in the
 * request is set to the session.
 */
public class CustomRequestProcessor extends RequestProcessor {
	/**
	 * 设置locale属性为true 将读取用户请求中包含的locale信息。然后吧locale实例保存到session范围
	 * */
    protected void processLocale(HttpServletRequest request,
                                 HttpServletResponse response) {
    
      // Are we configured to select the Locale automatically?
      if (!moduleConfig.getControllerConfig().getLocale()) {
        return;
      }

      // Get the Locale (if any) that is stored in the user's session
      HttpSession session = request.getSession();
      Locale sessionLocale = (Locale)session.getAttribute(Globals.LOCALE_KEY);

      // Get the user's preferred Locale from the request
      Locale requestLocale = request.getLocale();

      // If was never a Locale in the session or it has changed, set it
      if (sessionLocale == null ||  (sessionLocale != requestLocale) ){
        if (log.isDebugEnabled()) {
          log.debug(" Setting user locale '" + requestLocale + "'");
        }
        // Set the new Locale into the user's session
        session.setAttribute( Globals.LOCALE_KEY, requestLocale );
      }
      
    }
	/**
	 * 这里可以做一些用户自定义的处理
	 * 如：容许那些机器访问等
	 **/
  protected boolean processPreprocess( HttpServletRequest request,
                                      HttpServletResponse response){
	
    boolean continueProcessing = true;

    // Get the name of the remote host and log it
    String remoteHost = request.getRemoteHost();
    log.info( "Request from host: " + remoteHost );
    /*
    // Make sure the host is from one that you expect
    if ( remoteHost == null || !remoteHost.startsWith( "127.") ){
       // Not the localhost, so don't allow the host to access the site
       continueProcessing = false;

       ForwardConfig config = moduleConfig.findForwardConfig("Unauthorized");
       try{
         response.sendRedirect( config.getPath() );
       }catch( Exception ex ){
         log.error( "Problem sending redirect from processPreprocess()" );
       }
    }
    */
    /**
     * 设置action  访问权限。action安全
     * processRoles();
     * */
    return continueProcessing;
  }
}