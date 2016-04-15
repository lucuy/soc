<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>技术差距分析左侧树形列表</title>
<link rel="stylesheet" href="${ctx}/css/left.css" type="text/css"></link>
<link rel="stylesheet" href="${ctx}/css/tree.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/db_js/Left.files/TreeNode.js"></script>
<script type="text/javascript" src="${ctx}/db_js/Left.files/Tree.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
</head>
<body  style="padding-left:7px;padding-bottom: 0px;">
	<input type="hidden" id="acId" value="<s:property value="#parameters.id" />"/>
	<input type="hidden" id="acOver" value="<s:property value="#parameters.over" />"/>
	<div style="border:1px solid #C9E1F3;height: 95%" class="titleTop">
	<table cellSpacing=0 cellPadding=0 width="100%">
		<tbody>
			<tr>
				<td width=10 height=29><img src="${ctx}/images/jtTitle.jpg" /></td>
				<td style="FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/leftDh.jpg); COLOR: black; FONT-FAMILY: system" id="sysGrade"></td>
				<td width=10></td>
			</tr>
			<tr>
				<td></td>
				<td id=menuTree style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" vAlign=top></td>
				<td></td>
			</tr>
			<tr>
				<td width=10><img src="${ctx}/images/bg_left_bl.gif" /></td>
				<td></td>
				<td width=10></td>
			</tr>
		</tbody>
	</table>
	</div>
	<script type=text/javascript>
		var acId = $.trim($("#acId").val());
		var acOver = $.trim($("#acOver").val());
		$.getJSON("${ctx}/demandCollet/queryGradeForTree.action?dataTime="+new Date(),{acId : acId}, function(result) {
			 
			$("#sysGrade").html("&nbsp;"+result.sysGrade+"&nbsp;&nbsp;&nbsp;<a href=\"#\" style=\"FONT-SIZE: 12px;  COLOR: blue; FONT-FAMILY: system\"  onclick=\"parent.mainFrame.location.href='${ctx}/pages/dbPage/cpManage/technology/skip.jsp'\">返回</a> ");
			if(acOver==""){
				parent.mainFrame.location.href ="${ctx}/demandCollet/querySortInfo.action?sort="+result.tree[1].unitDomainName+"&acId="+acId+"&dataTime="+new Date();
			}
			var tree = null;
			var root = new TreeNode(result.sysGrade);
			var info1 = null;
			var info2 = null;
			$.each(result.tree, function(i, item) {
				var sort = $.trim(item.unitDomainName).length;
				if(sort<5 && sort>2){
					info1 = new TreeNode(item.unitDomainName+' '+item.unitName);
					root.add(info1);
				}
				if(sort<7 && sort>4){
					info2 = new TreeNode(item.unitDomainName+' '+item.unitName, '${ctx}/demandCollet/querySortInfo.action?sort='+item.unitDomainName+'&acId='+acId, 'arrow_03.gif', null, 'arrow_03.gif', null);
					info1.add(info2);
				}
			});
			tree = new Tree(root);
			tree.show('menuTree');
		});
		
	</script>
</body>
</html>
