<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>跳转页</title>
<script type="text/javascript">
 
	parent.leftFrame.location.href = "${ctx}/pages/dbPage/cpManage/technology/technologyTree.jsp";
</script>

</head>

<body style="font-size: 12px;">
	跳转……
	<br>
</body>
</html>
