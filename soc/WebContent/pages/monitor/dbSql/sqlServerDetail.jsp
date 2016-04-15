<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
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

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>

<script type="text/javascript" src="${ctx}/scripts/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/exporting.js"></script>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css" />

<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
#back a:link {
	color: #FFFFFF;
	text-decoration: none;
}

#back  a:visited {
	text-decoration: none;
	color: #FFFFFF;
}

#back   a:hover {
	color: #faec06;
	text-decoration: underline;
}

#back  a:active {
	color: #FFFFFF;
}
</style>
<script type="text/javascript">
	
	$(document).ready(function() {

		$('#tabs-setting').tabs();

	
	});

	function creaChart(){
	reFresh();
		 creaChart1();
	 
	 
 }

 
function creaChart1(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'threadsConnected',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['cntr_value'] //X轴的坐标值
              
           },
           yAxis: {
               min: 0,
               title: ''  //Y轴坐标标题  labels:纵柱标尺
           },
           exporting:{ 
               enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
          },
           legend: {                               //【图例】位置样式
               layout: 'horizontal',               //【图例】显示的样式：水平（horizontal）/垂直（vertical）
               backgroundColor: '#FFFFFF',
               borderColor: '#CCC',
               borderWidth: 1,
               align: 'center',
               verticalAlign: 'top',
               enabled:true,
               y: 30,
               shadow: true
           },
           tooltip: {
               formatter: function() {                 //当鼠标悬置数据点时的格式化提示
                   return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y, 0);
               }
           },
           credits: {
               enabled: false
           },
           plotOptions: {
               column: {
                   pointPadding: 0.2,  //图表柱形的
                   borderWidth: 0      //图表柱形的粗细 
               },bar: {
                   dataLabels: {
                       enabled: false
                   }
               }
           },
             series:[{
					 name:"cntr_value",
					 data:[${sqlServerDetail.cntr_value}]
					 }]
       });
}

var m = 120; //定时更新记时器
	var refresh; //字时更新对象
	//定时更新数据
	function reFresh() {		
			if (m >= 1) {
				$("#mes").html(
						"<b>"
								+ m + "</b> 秒后更新数据 ");

				refresh = setTimeout("reFresh()", 1000);
				m--;
			} else {
				// parent.mainFrame.location.href = '${ctx}/events/queryEvents.action?queryEventsType=0';
				//parent.mainFrame.location.href = "${ctx}/monitorDBT/detailDB.action?dbType=2&taskId="+${mondbt.taskId};
				reFreshDB();
			}
		

	}
	function reFreshDB(){
		reload();
		parent.mainFrame.location.href = "${ctx}/monitorDBT/detailDB.action?dbType=2&taskId="+${mondbt.taskId};
	}
	var mack;
	 function reload() {
	   // document.onmousedown=ContextMenu;
	    mack = document.getElementById("mack");
	    mack.className="ui-widget-overlay";
	    mack.style.height=document.documentElement.clientHeight;
	    openSuccess();
   }
	function openSuccess()
    {
        var docHe =  ($(document).height()/5);
        var docWi =  ($(document).width()/2)-200;
        $("#openSuccess").css({top:docHe,left:docWi});
        $("#openSuccess").show();
    }
	
</script>
</head>
<body style="margin-top:2px;margin-left: 2px" onLoad="creaChart();">
<body style="margin-top:2px;" >
<div class="ui-overlay">
        <div id="mack"></div>
     
    </div>
	<!--tab 页切换-->
	<div id="tabs-setting"
		style="width: 100%;border: none; margin: 2px auto">
		<ul style="background: #EDEEF3; ">
			<li><a href="#wholerisk" id="switch1" style="cursor: pointer;">基本信息</a>
			</li>
			<li><a href="#propertyspread" id="switch2"
				style="cussor: pointer">指标明细</a></li>

<li><span id="mes" style="color: red;margin-left: 200px;"></span>
	<input type="button" value="刷新" class="btnstyle"
								style="margin-left:400px;margin-top:2px" onclick="reFreshDB();"/>
<input type="button" value="返回" class="btnstyle"
								style="margin-top:2px" onclick="location.href='${ctx}/monitorDBT/queryAll.action?dbType=2';"/>
</li>
		</ul>


		<div id="wholerisk" style="overflow:auto">

			
						<table width="100%" cellSpacing="1" cellpadding="6" border="0">
							<tr>
								<td style="height:20%">
									<div >
										<fieldset style="width:530px; height:200px; padding:10px 10px 5px 10px; overflow: auto;">
											<legend>基本信息</legend>
											<table width="100%" style="height:200px;overflow: auto;" >
											
												<tr>
													<td nowrap="nowrap">监控对象名称</td>
													<td nowrap="nowrap">${mondbt.taskName}</td>
												</tr>
												<tr>
													<td nowrap="nowrap">监控对象IP地址</td>
													<td nowrap="nowrap">${mondbt.dbIp}</td>
												</tr>
												<tr>
													<td nowrap="nowrap">监控类别</td>
													<td nowrap="nowrap">${mondbt.dbCategory}</td>
												</tr>
												<tr>
													<td nowrap="nowrap">主机名称</td>
													<td nowrap="nowrap">${sqlServerDetail.hostName}</td>
												</tr>
												<tr>
													<td nowrap="nowrap">版本</td>
													<td nowrap="nowrap">${sqlServerDetail.version}</td>
												</tr>
												
												
											</table>
										</fieldset>
									</div></td>
									<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>内存使用情况</legend>
											<div style="height:200px;overflow:auto;">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<th class="biaoti">memory</th>
														<th class="biaoti">kB</th>
													</tr>
													<tbody id="TenAlarmMessage">
														<c:forEach items="${sqlServerDetail.memory}" var="i">
														<tr>
															<td nowrap="nowrap">${i.key}</td>
															<td nowrap="nowrap">${i.value}</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>
								

							</tr>
							<tr>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>访问方法明细</legend>
											<div style="height:200px;  overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">name</td>
														<td class="biaoti">value</td>
														
													</tr>
													<tbody id="TenAlarmMessage">
														<c:forEach items="${sqlServerDetail.accessMethods}" var="i">
														<tr>
															<td nowrap="nowrap">${i.key}</td>
															<td nowrap="nowrap">${i.value}</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>数据文件大小</legend>
											<div id="threadsConnected" style="height:200px"></div>
										</fieldset>
									</div></td>
							</tr>
							<tr>
							<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>高速缓存中高速缓存的对象数</legend>
											<div style="height:200px;  overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">name</td>
														<td class="biaoti">value</td>
														
													</tr>
													<tbody id="TenAlarmMessage">
														<c:forEach items="${sqlServerDetail.cacheObjectCounts}" var="i">
														<tr>
															<td nowrap="nowrap">${i.key}</td>
															<td nowrap="nowrap">${i.value}</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>
									<td style="height:20%">
									<div>

										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>高速缓存对象所使用的（KB）页的数目</legend>
											<div style="height:200px;  overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">name</td>
														<td class="biaoti">value</td>
														
													</tr>
													<tbody id="TenAlarmMessage">
														<c:forEach items="${sqlServerDetail.cachePages}" var="i">
														<tr>
															<td nowrap="nowrap">${i.key}</td>
															<td nowrap="nowrap">${i.value}</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>

							</tr>
							<tr>
							<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>SQL Server Static Manager</legend>
											<div style="height:200px;  overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">name</td>
														<td class="biaoti">value</td>
														
													</tr>
													<tbody id="TenAlarmMessage">
														<c:forEach items="${sqlServerDetail.staticManager}" var="i">
														<tr>
															<td nowrap="nowrap">${i.key}</td>
															<td nowrap="nowrap">${i.value}</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>
							</tr>

			</table>
		</div>
<!-- 第二个tab页：事件统计 -->
		
		<!-- 第三个tab页：资产分布 -->
		<div id="propertyspread">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				class="tab2">
				<thead>
					<tr height="28">
						<th width="20%">项目</th>
						<th width="28%">数值</th>
						<th width="28%">描述</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td valign="middle">DBConnexity</td>
						<td valign="middle">
						<c:if test="${sqlServerDetail.falg ==1 }">连通</c:if>
						<c:if test="${sqlServerDetail.falg ==null }">连不通</c:if>
						</td>
						<td valign="middle">数据库连通性</td>
					</tr>
					<tr>
						<td valign="middle">BufferCache_Hit</td>
						<td valign="middle">
						${sqlServerDetail.bch}
						</td>
						<td valign="middle">Buffer缓存命中率</td>
					</tr>
					<c:forEach items="${sqlServerDetail.bufferCacheDetail}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">Buffer缓存明细</td>
					</tr>
					</c:forEach>
					<tr>
						<td valign="middle">ActiveConnect</td>
						<td valign="middle">${sqlServerDetail.activeConnect}</td>
						<td valign="middle">活动连接数</td>
					</tr>
					<tr>
						<td valign="middle">SqlServerConnectTime</td>
						<td valign="middle">${connTimes}</td>
						<td valign="middle">连接时间</td>
					</tr>
					<tr>
						<td valign="middle">SqlServerLogins</td>
						<td valign="middle">${sqlServerDetail.logins}</td>
						<td valign="middle">登录数</td>
					</tr>
					<tr>
						<td valign="middle">SqlServerLogouts</td>
						<td valign="middle">${sqlServerDetail.logouts}</td>
						<td valign="middle">注销数</td>
					</tr>
					<c:forEach items="${sqlServerDetail.memory}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">内存使用情况</td>
					</tr>
					</c:forEach>
					<c:forEach items="${sqlServerDetail.sqlDetail}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">Sql明细</td>
					</tr>
					</c:forEach>
					<c:forEach items="${sqlServerDetail.transactions}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">事务明细</td>
					</tr>
					</c:forEach>
					<c:forEach items="${sqlServerDetail.latchDetail}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">latch明细</td>
					</tr>
					</c:forEach>
					<c:forEach items="${sqlServerDetail.dataFileSize}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">数据文件大小</td>
					</tr>
					</c:forEach>
					<c:forEach items="${sqlServerDetail.accessMethods}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">访问方法明细</td>
					</tr>
					</c:forEach>
					<c:forEach items="${sqlServerDetail.accessMethods}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">锁明细</td>
					</tr>
					</c:forEach>
					<c:forEach items="${sqlServerDetail.logFileSize}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">日志文件大小</td>
					</tr>
					</c:forEach>
					
					<c:forEach items="${sqlServerDetail.logFlush}" var="i">
					<tr>
						<td valign="middle">${i.key}</td>
						<td valign="middle">${i.value}</td>
						<td valign="middle">日志刷新明细</td>
					</tr>
					</c:forEach>
					
					
				</tbody>
			</table>

		</div>

	</div>
<div class="framDiv"  id="openSuccess" style="width:30%;display: none;position: absolute;z-index:10; background:#FFFFFF;">
            <table width="100%" border="0" cellspacing="1" cellpadding="0">
               
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td align="center"><font style="font-size:16px;"><img alt="加载中" src="${ctx}/images/reload.gif">&nbsp;&nbsp;数据加载中...</font></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td align="right" id="button">
		                    
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            </table>
                   </div>


</body>
</html>
