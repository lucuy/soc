<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<style type="type=text/css">
	{
	FONT-SIZE: 12px
}
#menuTree A {
	COLOR: #566984; TEXT-DECORATION: none
}
	</style>

<link rel="stylesheet" href="${ctx}/css/left.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/css/tree.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/treeview/lib/jquery.js"></script>
<script type="text/javascript" src="${ctx}/treeview/screen.js"></script>
<script type="text/javascript" src="${ctx}/js/Left.files/TreeNode.js"></script>
<script type="text/javascript" src="${ctx}/js/Left.files/Tree.js"></script>
</head>

<body class="titleTop">
	<table height="100%" cellSpacing=0 cellPadding=0 width="100%">
		<tbody>
			<tr style="width: 100%">
				<td width=10 height=29><img src="${ctx}/images/bg_left_tl.gif" /></td>
				<td style="FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system;background-repeat: repeat-x">系统设置</td>
				<td width=10><img src="${ctx}/images/bg_left_tr.gif" /></td>
			</tr>
			<tr>
				<td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_ls.gif)"></td>
				<td id=menuTree style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" vAlign=top></td>
				<td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_rs.gif)"></td>
			</tr>
			<tr>
				<td width=10><img src="${ctx}/images/bg_left_bl.gif" /></td>
				<td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_bc.gif)"></td>
				<td width=10><img src="${ctx}/images/bg_left_br.gif" /></td>
			</tr>
		</tbody>
	</table>
	<script type=text/javascript>
 
var tree = null;
var root = new TreeNode('系统设置');
 
var root1 = new TreeNode('注册页', '${ctx}/serial/serial.action', 'tree_node.gif', null, 'tree_node.gif', null);
root.add(root1);
<c:if test="${sessionScope.SSI_LOGIN_AuthorityID==0||sessionScope.SSI_LOGIN_AuthorityID==2}">
var root2 = new TreeNode('人员管理', '${ctx}/pages/basic/employee.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root.add(root2);
</c:if>
var root3 = new TreeNode('组织部门', '${ctx}/pages/basic/division.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root.add(root3); 
tree = new Tree(root);
tree.show('menuTree');
 
</script>
</body>
</html>
