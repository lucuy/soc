package com.util.reportForm.datadeal;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.struts.action.ActionServlet;
/**
 * Extend the Struts ActionServlet to perform your own special
 * initialization.
 */
public class ExtendedActionServlet extends ActionServlet {
	public static String setupPath=null;

  public void init() throws ServletException {
    // Make sure to always call the super's init() first
    super.init();
    ServletContext ctx = this.getServletContext();
    setupPath=ctx.getRealPath("/");
    //do some custom operation
    //......
  }
}