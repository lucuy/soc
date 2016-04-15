<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>拓扑图</title>
<link href="${ctx}/images/favicon.ico" rel="shortcut icon" />
<link href="${ctx}/images/favicon.ico" rel="Bookmark" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<script src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script src="${ctx}/scripts/jqDnR.js"></script>
<script src="${ctx}/scripts/jquery.topology-1.0.js"></script>
<style>
	td{
			text-align:center;
			}
</style>
</head>
<body>

	<div id="board" style="width:1240px;height:480px;">
	
	<input type="button" value="保存位置" class="btnstyle" style = "margin-top:10px;float:right;"
							id = "d"	 onclick="savePosition()"  />
	</div>
		<table  id="d1" style = "display:none">
			<tr>
				<td><input type="button" value="查看详情" class="btnstyle"
							id = "detail"	 /></td>
			
				<td><input type="button" value="登录" class="btnstyle"
							id = "login"	 /></td>
			</tr>
		</table>
	<script>
	var t;
	var id;
	var name;
jQuery(document).ready(function() {	$('#d1').hide();
	showTopology(${JsonSrc});
	

	$("img[class='flg'").click(function(event){
		 id =$(this)[0].id;
		 //alert($(this).attr("loginname"));
		 name =  $(this).attr("loginname");
	if(t!=null){
		clearTimeout(t) ;
		$('#detail').unbind("click");
		$('#events').unbind("click");
		$('#alert').unbind("click");
		$('#login').unbind("click");
	} 
			$("#d1").show();
			$("#d1").css({position: "absolute",'left':$(this).offset().left+$(this).width()/2-$('#d1').width()/2,'top':$(this).offset().top+90});
			
			$("#detail").click(function(event){
			window.open("${ctx}/settingNetworkTopology/networkTopology.action","_blank","location=yes,resizable=yes,width=1100,height=550,toolbar=no");
			});
			
			
			$("#login").click(function(event){
				window.open("${ctx}/login/check.action?loginName="+name+"&networkTopologyLogin=1","_blank","location=yes,resizable=yes,width=1100,height=550,toolbar=no");
			});
			t = setTimeout("$('#d1').hide();", 3000 );
      });
	//获得是否开启告警设置
	//setInterval("getAlertTrance()", 10000);
	//保存位置并且刷新界面
	//setInterval("savePositionAndReFresh()", 60000);
	//getAlertTrance();
});

//保存 
function savePosition(){
	$("img[class='flg']").each(
			   function() {
				   var node_id = $(this)[0].id.substr(5);
				  // alert(node_id);
				   var top = $('#node_'+node_id).offset().top; 
					var left = $('#node_'+node_id).offset().left; 
					
					   $.post("${ctx}/nodeGroup/nodeGroupAction/savePosition.action",{"id":node_id,"top":top,"left":left});
	 });
	
}
//刷新界面
function reFresh(){
	location.reload();
}
function savePositionAndReFresh(){
	savePosition();
	reFresh();
}
</script>

</body>
</html>