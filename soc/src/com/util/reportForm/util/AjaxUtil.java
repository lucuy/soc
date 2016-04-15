package com.util.reportForm.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AjaxUtil {
	private static Log log = LogFactory.getLog(AjaxUtil.class);
	//返回Ajax请求
	public static void responseWrite(HttpServletResponse response, String content ){
		
		if(null == content || "".equals(content)){
			return;
		}
//		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(content);
			out.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
	}

}
