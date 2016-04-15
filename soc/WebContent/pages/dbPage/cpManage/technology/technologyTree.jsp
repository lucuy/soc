<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
<link rel="stylesheet" href="${ctx}/css/leftTree.css" type="text/css"></link>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>	
<script type="text/javascript" src="${ctx}/scripts/jquery.tree.js"></script>	

<script type=text/javascript>
$(document).ready(function() {
	 
$.getJSON("${ctx}/technology/queryTree.action?dataTime="+new Date(), function(result) {
	var contentStr = "";
	 
	if(result.length == 0){
		parent.mainFrame.location.href="${ctx}/technology/queryList.action?sysInfoId=0";
	}else{
		parent.mainFrame.location.href="${ctx}/technology/queryList.action?sysInfoId="+ encodeURI(encodeURI(result[0].sysInfoId,"utf-8"),"utf-8");
	}
	$.each(result, function(i, item) {
		contentStr+=" <li><a href=\"${ctx}/technology/queryList.action?sysInfoId="+encodeURI(encodeURI(item.sysInfoId,"utf-8"),"utf-8")+"\" target=\"mainFrame\"  title=\""+item.sysInfoName+"\">"+item.sysInfoName+"</a></li>";
	});
	$("#files").html(contentStr).tree({
		expanded: 'li:first'
	});
});


});
function linkTo(linkType) {
if(linkType=='rank'){
			//等级保护和规管理
			parent.leftFrame.location.href='${ctx}/sysAbolish/queryForJson.action';
			parent.mainFrame.location.href='${ctx}/unitInfo/query.action';
		}
}
</script>
	</head>

<body>
<div class="titleTop">
	<table   cellSpacing=0 cellPadding=0  >
   <tbody>
    <tr style="background-image:url(${ctx}/images/leftDh.jpg)">
  
	<td width="25px" ><img src="${ctx}/images/jtTitle.jpg" alt=""
		width="25" height="28" />
	</td>
	<td class="leftDhxx">技术差距评估&nbsp;&nbsp;<a href="javascript:linkTo('rank');">返回</a></td>
    </tr>
    <tr>
      <td></td>
      <td id="menuTree" style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign="top">
    <%-- 树形导航 --%>
    <ul id="files">
	</ul>
    </td>
      <td></td>
    </tr>
   
  </tbody>
	</table>
 </div>
</body>
</html>
