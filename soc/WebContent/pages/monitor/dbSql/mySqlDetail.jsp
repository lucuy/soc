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
		 creaChart2();
		 creaChart1();
		 creaChart3();
		 creaChart4();
		 creaChart5();
	 
	 
 }

 function creaChart2(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'abortedConnects',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['MySqlAbortedConnects'] //X轴的坐标值
              
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
					 name:"MySqlAbortedConnects",
					 data:[${mysqlDetail.abortedConnects}]
					 }]
       });
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
               categories: ['MySqlThreadsConnected'] //X轴的坐标值
              
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
					 name:"MySqlThreadsConnected",
					 data:[${mysqlDetail.threadsConnected}]
					 }]
       });
}
	function creaChart3(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'runningThread',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['MySqlRunningThrad'] //X轴的坐标值
              
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
					 name:"MySqlRunningThrad",
					 data:[${mysqlDetail.runningThread}]
					 }]
       });
}
function creaChart4(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'queryTimes',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['MySqlQueryTimes'] //X轴的坐标值
              
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
					 name:"MySqlQueryTimes",
					 data:[${mysqlDetail.queryTimes}]
					 }]
       });
}
function creaChart5(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'openTables',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['MySqlOpenTables'] //X轴的坐标值
              
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
					 name:"MySqlOpenTables",
					 data:[${mysqlDetail.openTables}]
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
				//parent.mainFrame.location.href = "${ctx}/monitorDBT/detailDB.action?dbType=1&taskId="+${mondbt.taskId};
				reFreshDB();
			}
		

	}
	function reFreshDB(){
		reload();
		parent.mainFrame.location.href = "${ctx}/monitorDBT/detailDB.action?dbType=1&taskId="+${mondbt.taskId};
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
								style="margin-top:2px" onclick="location.href='${ctx}/monitorDBT/queryAll.action?dbType=1';"/>
</li>
		</ul>


		<div id="wholerisk" style="overflow:auto">

			
						<table width="100%" cellSpacing="1" cellpadding="6" border="0">
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>进本信息</legend>
											<table width="100%" style="height:200px">
												<tr>
													<td>监控对象名称</td>
													<td>${mondbt.taskName}</td>
												</tr>
												<tr>
													<td>监控对象IP地址</td>
													<td>${mondbt.dbIp}</td>
												</tr>
												<tr>
													<td>监控类别</td>
													<td>MySql</td>
												</tr>
												<tr>
													<td>主机名称</td>
													<td>${mysqlDetail.hostName}</td>
												</tr>
												<tr>
													<td>版本</td>
													<td>${mysqlDetail.version}</td>
												</tr>
												<tr>
													<td>操作系统</td>
													<td>${mysqlDetail.os}</td>
												</tr>
												<tr>
													<td>数据目录</td>
													<td>${mysqlDetail.dataDIR}</td>
												</tr>
												<tr>
													<td>基本目录</td>
													<td>${mysqlDetail.dir}</td>
												</tr>
											</table>
										</fieldset>
									</div></td>
									<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>最后一次警告</legend>
											<div style="height:200px;overflow:auto;">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<th class="biaoti">level</th>
														<th class="biaoti">code</th>
														<th class="biaoti">message</th>
													</tr>
													<tbody id="TenAlarmMessage">
														<s:iterator value="mysqlDetail.lastWarning" status="stat">
															<tr>
																<td valign="middle" nowrap="nowrap">${level}</td>
																<td valign="middle" nowrap="nowrap">${code}</td>
																<td valign="middle" nowrap="nowrap">${message}</td>
															</tr>

														</s:iterator>



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
											<legend>数据库</legend>
											<div style="height:200px;  overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">序号</td>
														<td class="biaoti">数据库</td>
														
													</tr>
													<tbody id="TenAlarmMessage">
														<s:iterator value="mysqlDetail.mysqlDB"
															status="stat">
															<tr>
																<td valign="middle" nowrap="nowrap">${num}</td>
																<td valign="middle" nowrap="nowrap">${dbName}</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>打开连接数</legend>
											<div id="threadsConnected" style="height:200px"></div>
										</fieldset>
									</div></td>
							</tr>
							<tr>
							<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>中止连接数</legend>
											<div id="abortedConnects" style="height:200px"></div>
										</fieldset>
									</div></td>
									<td style="height:20%">
									<div>

										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>当前活动线程数</legend>
											<div id="runningThread" style="height:200px"></div>
										</fieldset>
									</div></td>

							</tr>
							<tr>
							<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>已执行查询总数</legend>
											<div id="queryTimes" style="height:200px"></div>
										</fieldset>
									</div></td>
									<td style="height:20%">
									<div>

										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>当前打开的数据表总数</legend>
											<div id="openTables" style="height:200px"></div>
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
						<td valign="middle"><c:if test="${mysqlDetail.falg ==1 }">连通</c:if>
						<c:if test="${mysqlDetail.falg ==null }">连不通</c:if>
						</td>
						<td valign="middle">数据库连通性</td>
					</tr>
					<tr>
						<td valign="middle">Threads_connected</td>
						<td valign="middle">
						${mysqlDetail.threadsConnected}
						
						</td>
						<td valign="middle">打开连接数</td>
					</tr>
					
					<tr>
						<td valign="middle">Aborted_clients</td>
						<td valign="middle">${mysqlDetail.abortedClients}</td>
						<td valign="middle">中止客户端数</td>
					</tr>
					<tr>
						<td valign="middle">Key_Hit</td>
						<td valign="middle">${mysqlDetail.key_Hit}</td>
						<td valign="middle">键命中率</td>
					</tr>
					<tr>
						<td valign="middle">key_buffer_size</td>
						<td valign="middle">${mysqlDetail.kbs}</td>
						<td valign="middle">使用键缓冲大小</td>
					</tr>
					<tr>
						<td valign="middle"> QCacheHit</td>
						<td valign="middle">${mysqlDetail.qCacheHit}</td>
						<td valign="middle">缓存命中率</td>
					</tr>
					<tr>
						<td valign="middle">Aborted_connects</td>
						<td valign="middle">${mysqlDetail.abortedConnects}</td>
						<td valign="middle">中止连接数</td>
					</tr>
					<tr>
						<td valign="middle">MySqlConnectTime</td>
						<td valign="middle">${connTimes}</td>
						<td valign="middle">连接时间</td>
					</tr>
					<tr>
						<td valign="middle">thread_stack</td>
						<td valign="middle">${mysqlDetail.thread_stack}</td>
						<td valign="middle">线程缓冲大小</td>
					</tr>
					<tr>
						<td valign="middle">Qcache_total_blocks</td>
						<td valign="middle">${mysqlDetail.qctb}</td>
						<td valign="middle">请求缓存大小</td>
					</tr>
					<tr>
						<td valign="middle">Threads_running</td>
						<td valign="middle">${mysqlDetail.runningThread}</td>
						<td valign="middle">当前活动线程数</td>
					</tr>
					<tr>
						<td valign="middle">Com_select</td>
						<td valign="middle">${mysqlDetail.queryTimes}</td>
						<td valign="middle">已执行查询总数</td>
					</tr>
					<tr>
						<td valign="middle">Key_blocks_used</td>
						<td valign="middle">${mysqlDetail.key_blocks_used}</td>
						<td valign="middle">使用键缓冲</td>
					</tr>
					<tr>
						<td valign="middle">Query_cache_size</td>
						<td valign="middle">${mysqlDetail.qcs}</td>
						<td valign="middle">查询缓存大小</td>
					</tr>
					<tr>
						<td valign="middle">InnodbBufferPoolSize</td>
						<td valign="middle">${mysqlDetail.innodbBPS}</td>
						<td valign="middle">InnoDB 数据和索引缓存</td>
					</tr>
					<tr>
						<td valign="middle">InnodbBufferPoolHit</td>
						<td valign="middle">${mysqlDetail.innodbBPH}</td>
						<td valign="middle">InnoDB Buffer Pool 的命中率</td>
					</tr>
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
