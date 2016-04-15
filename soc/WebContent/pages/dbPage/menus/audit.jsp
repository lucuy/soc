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
<script type="text/javascript" src="${ctx}/treeview/jquery.treeview.js"></script>
<script type="text/javascript" src="${ctx}/treeview/screen.js"></script>
<script type="text/javascript" src="${ctx}/js/Left.files/TreeNode.js"></script>
<script type="text/javascript" src="${ctx}/js/Left.files/Tree.js"></script>
         <script language="javascript">
			function policyTo(url){
				parent.mainFrame.location.href=url;
			}
		</script>

  </head>
  
 <body class="titleTop">
<table height="100%" cellSpacing="0" cellPadding="0" width="100%">
  <tbody>
    <tr>
      <td width=10 height=29><img src="${ctx}/images/bg_left_tl.gif"/></td>
      <td   style="FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system">
      审计管理</td>
      <td width=10><img src="${ctx}/images/bg_left_tr.gif"/></td>
    </tr>
    <tr>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_ls.gif)"></td>
      <td id="menuTree" style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign="top">
    
    
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

<script type=text/javascript>
		var tree = null;
		var root = new TreeNode('审计管理', '${ctx}/pages/audit/operation_audit.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
	 
		tree = new Tree(root);
		tree.show('menuTree');
</script >


</body>
</html>
