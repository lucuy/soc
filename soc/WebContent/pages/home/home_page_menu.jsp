<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"
	import="com.scan.model.role.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<head>

<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/chart/FusionCharts.js"></script>
	<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">


<script language="javascript">
   
     function onLoad(){
            var userId = '${userinfo}';
            MessagePush.pageOnLoad(userId);
       }
    
       function showMessage(val){
       		if(val!='-1'){
       			parent.frames[0].LoadTimer();
       			alert(val);
       		}else{
       			alert(val);
       			parent.frames[0].NewMessage();
       		}
       		
      	 	
       }
       dwr.engine.setErrorHandler(function(){});
       /**function showmessage(val){
  		
      	}*/
	function action(elementId, eventsType) {
		if (eventsType != "img") {
			if (eventsType == -1) {
				linkTo(elementId, eventsType);
			}
		} else {
			changeIcon($("#img_query_" + elementId + ""));
			$("#query_" + elementId + " >ul").toggle("slow");
		}
	}
	function changeIcon(nainNode) {
		if (nainNode) {
			if (nainNode.attr("src").indexOf("u321_normal.png") >= 0) {
				nainNode.attr("src", "${ctx}/images/u319_normal.png");

			} else {
				nainNode.attr("src", "${ctx}/images/u321_normal.png");
			}
		}
	}
	
	function linkTo(id)
	{
	    //parent.mainFrame.location.href = '${ctx}/asset/queryAsset.action?assetSegMent='
	    //alert("123");
	    parent.mainFrame.location.href='${ctx}/home/queryHome.action?personType='+id;
	}
	function showcomprehensive()
	{

	 $("#showcomprehensive").attr("href",'${ctx}/indexscreen/queryAll.action');
	}
	
//请求安全态势综合展现	
	function facility()
	{
	 $("#facility").attr("href",'${ctx}/comprehensive/queryAll.action');
	}
	
//设备事件分布
    function spread()
    {
     $("#spread").attr("href",'${ctx}/comprehensive/querybigfacility.action');
     
    }

//安全事件统计
     function statistics()
     {
      $("#statistics").attr("href",'${ctx}/comprehensive/querybigsafety.action');
     }
     
// 全局域 风险变化趋势     
     function risk()
     {
      $("#risk").attr("href",'${ctx}/comprehensive/querybigrisk.action');
     }
// 子域风险变化趋势
     function sonrisk() {
      $("#sonrisk").attr("href",'${ctx}/comprehensive/querybigsonrisk.action');
     }     
</script>
</head>

<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">
	<div class="titleTop">
		<%-- <div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /> 
					</td>
					<td class="leftDhxx">健康信息</td>
				</tr>
			</table>
		</div>
		<div style="width:180px;height:100px" id="chartdiv"
			align="center">
			FusionGadgets
			<img alt="" src="${ctx}/images/121.png">
		</div> --%>
		<!-- <script type="text/javascript">
			var myChart = new FusionCharts("${ctx}/swf/AngularGauge.swf",
					"myChartId", "150", "100", "0", "0");

			myChart.setDataURL("${ctx}/swf/angular1.xml");

			myChart.render("chartdiv");
		</script> -->

		<div
			style="margin-top:2px;background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" /></td>
					<td class="leftDhxx">大屏展示</td>
				</tr>
			</table>
		</div>

		<%-- <div class="eventstree">
			<ul>
				<li id="query_1"><a href="javascript:action('1','img');"> <img
						id="img_query_1" src="/soc/images/u321_normal.png"></img> </a> <a
					href="javascript:void(0);"> <span class="eventext">人员 </span> </a>
					<ul class="disply">
						<li class="eventleft"><a href="javascript:linkTo('1');">
								<img src="/soc/images/u305_normal.png" /> </a> <a
							href="javascript:linkTo('1');"> <span class="eventext">
									 运维人员</span> </a>
						</li>
					</ul>
					<ul class="disply">
						<li class="eventleft"><a href="javascript:linkTo('2');">
								<img src="/soc/images/u305_normal.png" /> </a> <a
							href="javascript:linkTo('2');"> <span class="eventext">
									管理员 </span> </a>
						</li>
					</ul>
					<ul class="disply">
						<li class="eventleft"><a href="javascript:linkTo('3');">
								<img src="/soc/images/u305_normal.png" /> </a> <a
							href="javascript:linkTo('3');"> <span class="eventext">
									 主管</span> </a>
						</li>
					</ul>
				</li>

			</ul>

		</div>
		<table width="99%" border="0" cellspacing="10" cellpadding="10"
			style="margin-left:6px">
			<tr>
				<td align="left"><a href="#" class="hand"
					onclick="parent.mainFrame.location.href='${ctx}/pages/home/home_page_info.jsp';">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" /> &nbsp;首页</a></td>
			</tr>
		</table> --%>
		<table width="99%" border="0" cellspacing="10" cellpadding="10"
			style="margin-left:6px">
			
			<tr>

				<td align="left">
				<a id="showcomprehensive" href="" class="hand" target="_blank" onclick="showcomprehensive();">

						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">综合监控 </span></a>
				</td>
			</tr>
			
			<tr align="left">
				<td><a id="facility" href="" class="hand" target="_blank" onclick="facility();">
				&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">安全态势综合展现</span> </a>
				</td>
			</tr>
			
			<tr align="left">
				<td><a id="spread" href="" class="hand" target="_blank" onclick="spread();">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">设备事件分布</span></a>
				</td>
			</tr>
			
			<tr align="left">
				<td><a id="statistics" href="" class="hand" target="_blank" onclick="statistics();">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">安全事件统计</span></a>
				</td>
			</tr>
			
			
			<%-- <tr align="left">
				<td><a id="risk" href="" class="hand" target="_blank" onclick="risk();">
						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">全局域风险变化趋势</span></a>
				</td>
			</tr>
			
			<tr align="left">

				<td><a id="sonrisk" href="" class="hand" target="_blank" onclick="sonrisk();">

						&nbsp; <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span style="vertical-align:20%">子域风险变化趋势 </span></a>
				</td>
			</tr> --%>
			
		</table>
	</div>
</body>
</html>
