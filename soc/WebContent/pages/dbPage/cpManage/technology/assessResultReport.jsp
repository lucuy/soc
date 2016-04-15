<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.io.ByteArrayOutputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.ByteArrayOutputStream"%>
<%
	response.setContentType("application/x-msdownload");
	response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(request.getAttribute("systemName").toString(), "UTF-8"));
	
	OutputStream os = null;
	try {
		os = response.getOutputStream();
		
		ByteArrayOutputStream baos =(ByteArrayOutputStream) request.getAttribute("stream");
		baos.writeTo(os);
		
	    os.flush();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
		    os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 为避免getOutputStream() has already been called for this response错误
	 原因是：servlet会自动释放在JSP中使用的对象，会调用response.getWriter()
	 而这个方法是和response.getOutputStream()相冲突的
	*/
    out.clear();
    out = pageContext.pushBody();
%>
