<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page import="java.io.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ImageUtil.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%
		String ppath2 = request.getParameter("ppath");
		FileInputStream in = new FileInputStream(new File(ppath2));
		OutputStream o = response.getOutputStream();
		int l = 0;
		byte[] buffer = new byte[4096];
		while ((l = in.read(buffer)) != -1) {
			o.write(buffer, 0, l);
		}
		o.flush();
		in.close();
		o.close();
		o = null;
		response.flushBuffer();
		out.clear();
		out = pageContext.pushBody();
	%>
</body>
</html>
