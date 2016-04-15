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
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css" />

<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>

<script type="text/javascript" src="${ctx}/scripts/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/exporting.js"></script>

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
	 
	 
 }

 function creaChart2(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'Latch',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['Latch_total_Waits'] //X轴的坐标值
              
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
					 name:"Latch_total_Waits",
					 data:[${oracleDetail.latchCount}]
					 }]
       });
}
function creaChart1(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'Latch_time',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['Latch_Time_Waited'] //X轴的坐标值
              
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
					 name:"Latch_Time_Waited",
					 data:[${oracleDetail.latchTime}]
					 }]
       });
}
	function creaChart3(){
	
        var chart = new Highcharts.Chart({
         chart: {
               renderTo: 'open_cursors',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: ['open_cursors'] //X轴的坐标值
              
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
					 name:"open_cursors",
					 data:[${oracleDetail.openCursoors}]
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
				//parent.mainFrame.location.href = "${ctx}/monitorDBT/detailDB.action?dbType=3&taskId="+${mondbt.taskId};
			reFreshDB();
			}
		

	}
	function reFreshDB(){
		reload();
		parent.mainFrame.location.href = "${ctx}/monitorDBT/detailDB.action?dbType=3&taskId="+${mondbt.taskId};
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
			<li><a href="#eventstatistics" id="switch2"
				style="cursor: pointer;">配置参数</a></li>
			<li><a href="#propertyspread" id="switch3"
				style="cussor: pointer">指标明细</a></li>

<li><span id="mes" style="color: red;margin-left: 200px;"></span>
	<input type="button" value="刷新" class="btnstyle"
								style="margin-left:400px;margin-top:2px" onclick="reFreshDB();"/>
<input type="button" value="返回" class="btnstyle"
								style="margin-top:2px" onclick="location.href='${ctx}/monitorDBT/queryAll.action?dbType=3';"/>
</li>
		</ul>


		<div id="wholerisk" style="overflow:auto">

			<table width="100%" border="0" cellpadding="0">
				<tr>
					<td>
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
													<td>oracle</td>
												</tr>
												<tr>
													<td>主机名称</td>
													<td>${oracleDetail.hostName}</td>
												</tr>
												<tr>
													<td>版本</td>
													<td>${oracleDetail.version}</td>
												</tr>
												<tr>
													<td>创建时间</td>
													<td>${oracleDetail.creatTime}</td>
												</tr>
												<tr>
													<td>启动时间</td>
													<td>${oracleDetail.startTime}</td>
												</tr>
											</table>
										</fieldset>
									</div></td>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>Latch争用总等待次数</legend>
											<div id="Latch" style="height:200px"></div>
										</fieldset>
									</div></td>

							</tr>
							<tr>
								<td style="height:20%">
									<div>

										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>Latch争用总等待时间</legend>
											<div id="Latch_time" style="height:200px"></div>
										</fieldset>
									</div></td>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>当前游标数</legend>
											<div id="open_cursors" style="height:200px"></div>
										</fieldset>
									</div></td>
							</tr>
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>当前登录用户</legend>
											<div style="height:200px;overflow:auto;">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<th class="biaoti">SID</th>
														<th class="biaoti">USERNAME</th>
														<th class="biaoti">OSUSER</th>
														<th class="biaoti">MACHINE</th>
													</tr>
													<tbody id="TenAlarmMessage">
														<s:iterator value="oracleDetail.currentUser" status="stat">
															<tr>
																<td valign="middle" nowrap="nowrap">${sid}</td>
																<td valign="middle" nowrap="nowrap">${username}</td>
																<td valign="middle" nowrap="nowrap">${osuser}</td>
																<td valign="middle" nowrap="nowrap">${mschine}</td>
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
											<legend>最消耗系统资源的用户和进程</legend>
											<div style="height:200px;  overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">SID</td>
														<td class="biaoti">USERNAME</td>
														<td class="biaoti">SPID</td>
														<td class="biaoti">STATUS</td>
														<td class="biaoti">PROG</td>
														<td class="biaoti">TERMINAL OSUSER</td>
													</tr>
													<tbody id="TenAlarmMessage">
														<s:iterator value="oracleDetail.highCpuUsedUser"
															status="stat">
															<tr>
																<td valign="middle" nowrap="nowrap">${sid}</td>
																<td valign="middle" nowrap="nowrap">${username}</td>
																<td valign="middle" nowrap="nowrap">${spid}</td>
																<td valign="middle" nowrap="nowrap">${status}</td>
																<td valign="middle" nowrap="nowrap">${prog}</td>
																<td valign="middle" nowrap="nowrap">${termonal}</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>

							</tr>
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>最近执行的sql语句</legend>
											<div style="height:200px;overflow:auto;">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">LAST_LOAD_TIME</td>
														<td class="biaoti">SQL_TEXT</td>
													</tr>
													<tbody id="TenAlarmMessage">
														<s:iterator value="oracleDetail.recentSql" status="stat">
															<tr>
																<td valign="middle" nowrap="nowrap">${lastTime}</td>
																<td valign="middle" nowrap="nowrap">${sqlTest}</td>

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
											<legend>RedoLog使用情况</legend>
											<div style="height:200px; overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">GROUP</td>
														<td class="biaoti">TYPE</td>
														<td class="biaoti">MEMBER</td>
														<td class="biaoti">BYTES</td>
														<td class="biaoti">STATUS</td>
														<td class="biaoti">FIRST_CHANGE</td>

													</tr>
													<tbody id="TenAlarmMessage">
														<s:iterator value="oracleDetail.oracleRedoLog"
															status="stat">
															<tr>
																<td valign="middle" nowrap="nowrap">${group}</td>
																<td valign="middle" nowrap="nowrap">${type}</td>
																<td valign="middle" nowrap="nowrap">${member}</td>
																<td valign="middle" nowrap="nowrap">${bytes}</td>
																<td valign="middle" nowrap="nowrap">${status}</td>
																<td valign="middle" nowrap="nowrap">
																	${first_change}</td>


															</tr>

														</s:iterator>
													</tbody>

												</table>
											</div>
										</fieldset>
									</div></td>

							</tr>
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>最消耗系统资源的sql信息Top10</legend>
											<div style="height:200px; overflow:auto;">
												<table width="530px" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<s:iterator value="oracleDetail.oracleTop10Sq" id="topSql"
														status="stat">
														<tr>
															<td nowrap="nowrap">${topSql}</td>
														</tr>

													</s:iterator>

												</table>
											</div>
										</fieldset>
									</div></td>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>表空间状态</legend>
											<div style="height:200px;  overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td class="biaoti">FILE</td>
														<td class="biaoti">STATUS</td>
														<td class="biaoti">CHANGE</td>
														<!-- <td class="biaoti">SEGMENT_SPACE_MANAGEMENT</td> -->

													</tr>
													<tbody id="TenAlarmMessage">
													<s:iterator value="oracleDetail.oracleTableSpace" 
														status="stat">
														<tr>
															<td nowrap="nowrap">${file}</td>
															<td nowrap="nowrap">${status}</td>
															<td nowrap="nowrap">${change}</td>
															<%-- <td nowrap="nowrap">${time}</td> --%>
														</tr>

													</s:iterator>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>

							</tr>
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>剩余空间</legend>
											<div style="height:200px; overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td width="20%" class="biaoti">表空间</td>
														<td width="28%" class="biaoti">剩余空间M</td>
													</tr>
													<tbody id="TenAlarmMessage">
													<s:iterator value="oracleDetail.oracleFreeSpace" 
														status="stat">
														<tr>
															<td nowrap="nowrap">${costom1}</td>
															<td nowrap="nowrap">${costom2}</td>
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
											<legend>总容量</legend>
											<div style="height:200px; width:auto; overflow:auto">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td width="20%" class="biaoti">名称</td>
														<td width="28%" class="biaoti">总空间M</td>
													</tr>
													<tbody id="TenAlarmMessage">
													<s:iterator value="oracleDetail.oracleTotalSpace" 
														status="stat">
														<tr>
															<td nowrap="nowrap">${costom1}</td>
															<td nowrap="nowrap">${costom2}</td>
														</tr>

													</s:iterator>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>

							</tr>
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>连接数</legend>
											<div style="height:200px">
												<table width="100%" border="0" cellspacing="1"
													cellpadding="0" class="tab2">
													<tr height="28" id="collectionTR">
														<td width="20%" class="biaoti">COUNT(1)</td>
													</tr>
													<tbody id="TenAlarmMessage">
													<tr>
															<td nowrap="nowrap">${oracleDetail.oracleConnect}</td>
														</tr>
													</tbody>
												</table>
											</div>
										</fieldset>
									</div></td>

							</tr>
						</table></td>
				</tr>

			</table>
		</div>

		<!-- 第二个tab页：事件统计 -->
		<div id="eventstatistics">
			<div style="width:100%; ">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="tab2">
					<tr height="28" id="collectionTR">
						<th width="20%" class="biaoti">Name</th>
						<th width="28%" class="biaoti">value</th>
					</tr>
					<s:iterator value="oracleDetail.oracleConfigInfo" 
														status="stat">
														<tr>
															<td nowrap="nowrap">${costom1}</td>
															<td nowrap="nowrap">${costom2}</td>
														</tr>

													</s:iterator>
				</table>

			</div>

		</div>

		<!-- 第三个tab页：资产分布 -->
		<div id="propertyspread">

			<!--  <div style="width:100%; height:280px; border: 1px solid #D2E8FA ">-->
			<!-- 页面太大注释掉 -->

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
						<td valign="middle"><c:if test="${oracleDetail.falg ==1 }">连通</c:if>
						<c:if test="${oracleDetail.falg ==null }">连不通</c:if>
						</td>
						<td valign="middle">数据库连通性</td>
					</tr>
					<tr>
						<td valign="middle">MemUsed</td>
						<td valign="middle">
						<c:if test="${oracleDetail.oracleDBMemUsed !=null }">${oracleDetail.oracleDBMemUsed}</c:if>
						
						</td>
						<td valign="middle">数据库内存使用</td>
					</tr>
					
					<tr>
						<td valign="middle">Library_Cache_Hit</td>
						<td valign="middle">${oracleDetail.lch}</td>
						<td valign="middle">Oracle缓存命中率</td>
					</tr>
					<tr>
						<td valign="middle">Buffer_Cache_Hit</td>
						<td valign="middle">${oracleDetail.bch}</td>
						<td valign="middle">Oracle缓存命中率</td>
					</tr>
					<tr>
						<td valign="middle">Total_Timeouts</td>
						<td valign="middle">${oracleDetail.totalTimeouts}</td>
						<td valign="middle">等待事件</td>
					</tr>
					<tr>
						<td valign="middle">Average_Wait</td>
						<td valign="middle">${oracleDetail.averageWait}</td>
						<td valign="middle">等待事件</td>
					</tr>
					<tr>
						<td valign="middle">InMemorySort</td>
						<td valign="middle">${oracleDetail.oracleInMemorySort}</td>
						<td valign="middle">内存排序占总排序的比例</td>
					</tr>
					<tr>
						<td valign="middle">Obtain_Lock_Course</td>
						<td valign="middle">${oracleDetail.oracleObtainLockCourse}</td>
						<td valign="middle">获得锁的进程数</td>
					</tr>
					<tr>
						<td valign="middle">Wait_Lock_Course</td>
						<td valign="middle">${oracleDetail.oracleWaitLockCourse}</td>
						<td valign="middle">等待锁的进程数</td>
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
