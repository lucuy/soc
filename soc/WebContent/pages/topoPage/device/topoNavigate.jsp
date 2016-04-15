<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>网络拓扑导航页面</title>
		<script type=text/javascript src="${ctx}/scripts/swfobject.js"></script>
	</head>
	
	<body style="text-align:center;background-color:#000000">
		<div id=loader align=center>Loading...</div>
		<script type=text/javascript>
		var so = new SWFObject("${ctx}/images/topo/img/viewer.swf", "sotester", "990", "480", "9", "#000000");
		so.addParam("wmode", "opaque");
		so.addParam("flashvars", "&xmlLocation=${ctx}/images/topo/img/switch.xml");
		so.write("loader");
		</script>
	</body>
	
</html>