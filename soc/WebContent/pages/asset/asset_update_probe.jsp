<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript' src="${ctx}/scripts/ajaxfileupload.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<%-- <link type="text/css"
    href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
    rel="stylesheet" /> --%>
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>

</head>
<script type="text/javascript">
    function sure()
    {   		
				var str = window.location.href;//当前URL地址
				var es = /ids=/;
				es.exec(str);
				var right = RegExp.rightContext;
				
                location.href="${ctx}/assetProbeTask/testResult.action?ids="+right;

    }
</script>
<body style="margin-top: 2px">


            	<div style="position:absolute ; top:10px; left:400px; font-size: 12px; width:500px; height:500px; color:#FFFFFF;" id="prog"><img src="${ctx}/images/loading.gif"  height="500"  width="500" /></div>
<script language="JavaScript">
	setInterval("sure()",10000); 
	</script>
</body>
</html>