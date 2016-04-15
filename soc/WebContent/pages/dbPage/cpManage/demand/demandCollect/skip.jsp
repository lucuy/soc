<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="Expires" CONTENT="0">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<title>跳转页</title>
<script type="text/javascript">
$(document).ready(function() {
	var sysInfoId = $("#sysInfoId").val();
	var acId = $("#acId").val();
	var sort = $("#sort").val();
	if(sysInfoId==""){
		parent.leftFrame.location.href="${ctx}/pages/dbPage/cpManage/demand/demandCollect/demandAssessTree.jsp?id="+acId+"&over=over";
		parent.mainFrame.location.href="${ctx}/demandCollet/querySortInfo.action?sort="+sort+"&acId="+acId;
	}else{
		parent.leftFrame.location.href="${ctx}/pages/dbPage/cpManage/technology/technologyTree.jsp";
		parent.mainFrame.location.href="${ctx}/technology/queryList.action?sysInfoId="+sysInfoId;
	}
});	
</script>

</head>

<body>
<input type="hidden" id="acId" value="${acId}" />
<input type="hidden" id="sort" value="${sort}" />
<input type="hidden" id="sysInfoId" value="${sysInfoId}" />
	跳转……<br>
</body>
</html>
