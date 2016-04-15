<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统设置</title>
<link rel="stylesheet" href="${ctx}/css/leftTree.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>	
<script type="text/javascript" src="${ctx}/scripts/jquery.tree.js"></script>	
<script type="text/javascript">
$(function(){
	$('#files').tree({
		expanded: 'li:first'
	});
});
</script>		
 
</head>
<body>	
<table  cellSpacing="0" cellPadding="0" width="100%">
  <tbody>
    <tr>
      <td width=10 height=29><img src="${ctx}/images/bg_left_tl.gif"/></td>
      <td   style="FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system">
      </td>
      <td width=10><img src="${ctx}/images/bg_left_tr.gif"/></td>
    </tr>
    <tr>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_ls.gif)"></td>
      <td id="menuTree" style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign="top">
    <ul id="files">
	 
					<li><a href="${ctx}/serial/serial.action" target="mainFrame" title="系统注册">系统注册</a></li>
					<li><a href="${ctx}/pages/basic/employee.jsp" target="mainFrame" title="人员管理">人员管理</a></li>
					<li><a href="${ctx}/pages/basic/division.jsp" target="mainFrame" title="组织部门">组织部门</a></li>
      </ul>
    </td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_rs.gif)"></td>
    </tr>
    <tr>
      <td width=10><img src="${ctx}/images/bg_left_bl.gif"/></td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_bc.gif)"></td>
      <td width=10><img src="${ctx}/images/bg_left_br.gif"/></td>
    </tr>
  </tbody>
</table>
 
</body>
</html>