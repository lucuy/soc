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

	<body  class="titleTop">
<table height="100%" cellSpacing=0 cellPadding=0 width="100%">
  <tbody >
    <tr style="width: 100%">
      <td width=10 height=29><img src="${ctx}/images/bg_left_tl.gif"/></td>
      <td  style="FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system;background-repeat: repeat-x">
      基础数据</td>
      <td width=10><img src="${ctx}/images/bg_left_tr.gif"/></td>
    </tr>
    <tr>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_ls.gif)"></td>
      <td id=menuTree   style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" vAlign=top>
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
var root = new TreeNode('基础数据');
 
var root1 = new TreeNode('单位基本情况', '${ctx}/pages/menus/testTree.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root.add(root1);
<c:if test="${sessionScope.SSI_LOGIN_AuthorityID==1||sessionScope.SSI_LOGIN_AuthorityID==2}">
var root3 = new TreeNode('信息系统管理', '${ctx}/pages/basic/manager.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root.add(root3);
var root4 = new TreeNode('资产管理' );
root.add(root4);
var fun1 = new TreeNode('业务应用软件', '${ctx}/pages/basic/assets/softassets.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root4.add(fun1);
var fun2 = new TreeNode('关键数据类别', '${ctx}/pages/basic/assets/dataassets.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root4.add(fun2);
var fun3 = new TreeNode('主机存储设备', '${ctx}/pages/basic/assets/compassets.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root4.add(fun3);
var fun4 = new TreeNode('网络互连设备', '${ctx}/pages/basic/assets/networkassets.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root4.add(fun4);
var fun5 = new TreeNode('安全设备', '${ctx}/pages/basic/assets/devassets.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root4.add(fun5);
var fun6 = new TreeNode('安全相关人员', '${ctx}/pages/basic/assets/empassets.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root4.add(fun6);
var fun7 = new TreeNode('安全管理文档', '${ctx}/pages/basic/assets/docassets.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
root4.add(fun7);
</c:if>
tree = new Tree(root);

tree.show('menuTree');
 
</script>
</body>
</html>
