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
	 Ϊ����getOutputStream() has already been called for this response����
	 ԭ���ǣ�servlet���Զ��ͷ���JSP��ʹ�õĶ��󣬻����response.getWriter()
	 ����������Ǻ�response.getOutputStream()���ͻ��
	*/
    out.clear();
    out = pageContext.pushBody();
%>
