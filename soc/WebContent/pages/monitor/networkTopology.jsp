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
				<td><input type="button" value="查看事件" class="btnstyle"
							id = "events"	 /></td>
				<td><input type="button" value="查看告警" class="btnstyle"
							id = "alert"	 /></td>
				<%--<td><input type="button" value="登录" class="btnstyle"
							id = "login"	 /></td>
			--%></tr>
		</table>
	<script>
	//查看资产的apm信息
	function gotoInfo(assetMac,assetId,assetSupportDeviceId)
	{
	   window.open('${ctx}/monitorGroup/queryInfo.action?assetMac='+assetMac+'&assetId='+assetId+'&assetSupportDeviceId='+assetSupportDeviceId,'_blank','location=yes,resizable=yes,width=1100,height=550,toolbar=no');
	}
	var t;
	var id;
	var name;
jQuery(document).ready(function() {	
	$('#d1').hide();
	showTopology(${JsonSrc});
	
	//点一下图片产生以下事件
	$("img[class='flg']").click(function(event){
		//获得做点的图片的id
		 id =$(this)[0].id;//这个id从数据库中取出来  这里是资产的id
		 name =  $(this).attr("loginnname");//获得loginname的值
	if(t!=null){ //取消绑定在#detail #events #alert  的click事件 如果不清理就会出现多个单击事件
		clearTimeout(t) ;//清理掉定时器
		$('#detail').unbind("click");//清理掉单击事件
		$('#events').unbind("click");
		$('#alert').unbind("click");
	}
			$("#d1").show();//d1显示
			//改变d1的位置 使其在图片的下方
			$("#d1").css({position: "absolute",'left':$(this).offset().left+$(this).width()/2-$('#d1').width()/2,'top':$(this).offset().top+90});
			//单击详情产生的事件
			$("#detail").click(function(event){
				//查看资产的apm信息
		$.post("${ctx}/settingNetworkTopology/getAssetById.action?id="+ parseInt(id.substr(5)),{},function(data){
			gotoInfo('${asset.assetMac}','${asset.assetId}','${asset.assetSupportDeviceId}')
		});
				
			});
			//根据id 查出这个资产发生的事件
			$("#events").click(function(event){
				window.open("${ctx}/events/queryByAssetEvents.action?assetId="+parseInt(id.substr(5)),"_blank");
			});
			//根据id 查出这个资产发生的告警
			$("#alert").click(function(event){
				window.open("${ctx}/alertMessage/alertMessageQuery.action?currentCount="+parseInt(id.substr(5))+"&isMonitorNetworkTopology=1","_blank");
			});
			$("#login").click(function(event){
				window.open("${ctx}/login/check.action?loginName="+name+"&networkTopologyLogin=1","_blank","location=yes,resizable=yes,width=1100,height=550,toolbar=no");
			});
			t = setTimeout("$('#d1').hide();", 3000 );//一个定时器 3秒后id为di的div隐藏 
      });
	//获得是否开启告警设置
	setInterval("getAlertTrance()", 10000);  //定时器  刷新界面看是否有告警
	//保存位置并且刷新界面
	setInterval("savePositionAndReFresh()", 60000);
getAlertTrance();
});
//获得是否开启告警设置
function getAlertTrance() {
	// _imgId ;
	$("img[class='flg']").each(
			   function() {
				   var  _imgId = $(this)[0].id;
				   //alert( _imgId );
				   $.post("${ctx}/alertMessage/queryAlarmByAssetIdWhereWorkorderEqual01.action?id="+ _imgId.substr(5),{},function(data){
					  // alert(_imgId);
							$("img[id='"+_imgId+"']").attr('src','/soc/images/topo/'+data);
					});
	 });
	
	
	
	
}
//保存 
function savePosition(){
	$("img[class='flg']").each(
			   function() {
				   var node_id = $(this)[0].id.substr(5);
				 //  alert(node_id);
				   var top = $('#node_'+node_id).offset().top; 
					var left = $('#node_'+node_id).offset().left; 
					
					   $.post("${ctx}/settingNetworkTopology/savePosition.action",{"id":node_id,"top":top,"left":left});
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