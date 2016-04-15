<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
    <script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
    <script type='text/javascript' src="${ctx}/scripts/util.js"></script>
    
    <script type="text/javascript">
	function logout() {
		parent.window.location.href="${ctx}/pages/login.jsp";
	}
	</script>
  </head>
  <body onload="logout();">
  </body>
</html>
