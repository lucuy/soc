<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
<link rel="stylesheet" href="${ctx}/css/leftTree.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>	
<script type="text/javascript" src="${ctx}/scripts/jquery.tree.js"></script>	

<script type=text/javascript>
$(document).ready(function() {
$.getJSON("${ctx}/demandCollet/queryTree.action?dataTime="+new Date(), function(result) {
	var contentStr = "";
	if(result.length == 0){
		parent.mainFrame.location.href="${ctx}/pages/dbPage/cpManage/demand/demandCollect/demandCollectList.jsp?data=nodata";
	}else{
		parent.mainFrame.location.href="${ctx}/demandCollet/queryDemand.action?CIA_AssessResult=1&FK_CA="+ result[0].id+"&dataTime="+new Date();
	}
	$.each(result, function(i, item) {
		
		contentStr+="<li><a href=\"javascript:void(0);\">"+item.name+"</a>";
		contentStr+="<ul>";
		contentStr+=" <li><a href=\"${ctx}/demandCollet/queryDemand.action?CIA_AssessResult=1&FK_CA="+item.id+"\" target=\"mainFrame\"  title=\"部分符合项\">部分符合项</a></li>";
		contentStr+=" <li><a href=\"${ctx}/demandCollet/queryDemand.action?CIA_AssessResult=2&FK_CA="+item.id+"\" target=\"mainFrame\" title=\"不符合项\">不符合项</a></li>";
		contentStr+="</ul>";
		contentStr+="</li>";
		 
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
<table width="100%"  cellSpacing=0 cellPadding=0  >
  <tbody>
    <tr style="background-image:url(${ctx}/images/leftDh.jpg)">
  
	<td width="25px" ><img src="${ctx}/images/jtTitle.jpg" alt=""
		width="25" height="28" />
	</td>
	<td class="leftDhxx">需求汇总信息&nbsp;&nbsp;<a href="javascript:linkTo('rank');">返回</a></td>
    </tr>
    <tr>
    <tr>
      <td ></td>
      <td id=menuTree   style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign=top>
      <%-- 树形导航 --%>
    <ul id="files">
    
	
    </ul>
    
    </td>
      <td ></td>
    </tr>
 
  </tbody>
</table>
</div>
</body>
</html>
