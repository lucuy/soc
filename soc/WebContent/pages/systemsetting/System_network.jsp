<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
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
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css" /> --%>
<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<!-- 加载图表需要的js -->
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>
<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->

<script lang="javascript" type="text/javascript">

	//network
	function networkAddDays(date, value) {
		var newDate = new Date(date.getTime());
		newDate.setDate(date.getDate() + value);
		return newDate;
	}

	function networkRound(d) {
		return Math.round(100 * d) / 100;
	}
	var networkData1 = [];
	//var networkData2 = [];

	var networkValue1 = 50;
	//var networkValue2 = 100;

	var date = new Date(2012, 0, 1);

	for ( var i = 0; i < 200; i++) {

		networkValue1 += Math.random() * 10 - 5;
		networkData1.push([ date, networkRound(networkValue1) ]);

		//networkValue2 += Math.random() * 10 - 5;
		//networkData2.push([date, networkRound(networkValue2)]);

		date = networkAddDays(date, 1);
	}
	//2
	
	//END
	
	var background = {
		type : 'linearGradient',
		x0 : 0,
		y0 : 0,
		x1 : 0,
		y1 : 1,
		colorStops : [ {
			offset : 0,
			color : '#d2e6c9'
		}, {
			offset : 1,
			color : 'white'
		} ]
	};

	var fillStyle = {
		type : 'linearGradient',
		x0 : 0,
		y0 : 0,
		x1 : 1,
		y1 : 0,
		colorStops : [ {
			offset : 0,
			color : '#65c2e8'
		}, {
			offset : 0.49,
			color : '#55b3e1'
		}, {
			offset : 0.5,
			color : '#3ba6dc'
		}, {
			offset : 1,
			color : '#2794d4'
		} ]
	};

	var data = [];
	var eventValue = 20;
	var i;
	for (i = 0; i < 30; i++) {

		eventValue = Math.round(Math.random() * 100);
		data.push(eventValue);
	}
	var netdrid=20;
	var netdrname=1;
	var netdrid1=20;
	var netdrname1=1;
	var networkNu;
	$(document).ready(function() {
	networkNu='${networkSize}';
	while(netdrid1>=0){
		$("#net"+netdrname1).hide();
		netdrid1=netdrid1-1;
		netdrname1=netdrname1+1;
	}
	while(networkNu>0){
		$("#net"+netdrname).show();
		netdrname=netdrname+1;
		$("#net"+netdrname).show();
		netdrname=netdrname+1;
		networkNu= networkNu-1;
	}
	$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 220,
			buttons : {
				"确认[Enter]" : function() {
					var temp=extQuery();
					if(temp==true)
					{
					$(this).dialog("close");
					}	
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
	
	var jsonArrayNetworkDataReceive = '${jsonArrayNetworkDataReceive}';
	networkDataReceivedata = eval("(" + jsonArrayNetworkDataReceive + ")");
	upnetworkReceive = networkDataReceivedata;
	var jsonArrayNetworkDataTransmit = '${jsonArrayNetworkDataTransmit}';
	networkDataTransmitdata = eval("(" + jsonArrayNetworkDataTransmit + ")");
	upnetworkTransmit = networkDataTransmitdata;
	
	var jsonArrayNetworkDataReceive2 = '${jsonArrayNetworkDataReceive2}';
	networkDataReceivedata2 = eval("(" + jsonArrayNetworkDataReceive2 + ")");
	upnetworkReceive2 = networkDataReceivedata2;
	var jsonArrayNetworkDataTransmit2 = '${jsonArrayNetworkDataTransmit2}';
	networkDataTransmitdata2 = eval("(" + jsonArrayNetworkDataTransmit2 + ")");
	upnetworkTransmit2 = networkDataTransmitdata2;
	
	var jsonArrayNetworkDataReceive3 = '${jsonArrayNetworkDataReceive3}';
	networkDataReceivedata3 = eval("(" + jsonArrayNetworkDataReceive3 + ")");
	upnetworkReceive3 = networkDataReceivedata3;
	var jsonArrayNetworkDataTransmit3 = '${jsonArrayNetworkDataTransmit3}';
	networkDataTransmitdata3 = eval("(" + jsonArrayNetworkDataTransmit3 + ")");
	upnetworkTransmit3 = networkDataTransmitdata3;
	
	var jsonArrayNetworkDataReceive4 = '${jsonArrayNetworkDataReceive4}';
	networkDataReceivedata4 = eval("(" + jsonArrayNetworkDataReceive4 + ")");
	upnetworkReceive4 = networkDataReceivedata4;
	var jsonArrayNetworkDataTransmit4 = '${jsonArrayNetworkDataTransmit4}';
	networkDataTransmitdata4 = eval("(" + jsonArrayNetworkDataTransmit4 + ")");
	upnetworkTransmit4 = networkDataTransmitdata4;
	
	var jsonArrayNetworkDataReceive5 = '${jsonArrayNetworkDataReceive5}';
	networkDataReceivedata5 = eval("(" + jsonArrayNetworkDataReceive5 + ")");
	upnetworkReceive5 = networkDataReceivedata5;
	var jsonArrayNetworkDataTransmit5 = '${jsonArrayNetworkDataTransmit5}';
	networkDataTransmitdata5 = eval("(" + jsonArrayNetworkDataTransmit5 + ")");
	upnetworkTransmit5 = networkDataTransmitdata5;
	
	var jsonArrayNetworkDataReceive6 = '${jsonArrayNetworkDataReceive6}';
	networkDataReceivedata6 = eval("(" + jsonArrayNetworkDataReceive6 + ")");
	upnetworkReceive6 = networkDataReceivedata6;
	var jsonArrayNetworkDataTransmit6 = '${jsonArrayNetworkDataTransmit6}';
	networkDataTransmitdata6 = eval("(" + jsonArrayNetworkDataTransmit6 + ")");
	upnetworkTransmit6 = networkDataTransmitdata6;
	
	var jsonArrayNetworkDataReceive7 = '${jsonArrayNetworkDataReceive7}';
	networkDataReceivedata7 = eval("(" + jsonArrayNetworkDataReceive7 + ")");
	upnetworkReceive7 = networkDataReceivedata7;
	var jsonArrayNetworkDataTransmit7 = '${jsonArrayNetworkDataTransmit7}';
	networkDataTransmitdata7 = eval("(" + jsonArrayNetworkDataTransmit7 + ")");
	upnetworkTransmit7 = networkDataTransmitdata7;
	var jsonArrayNetworkDataReceive8 = '${jsonArrayNetworkDataReceive8}';
	networkDataReceivedata8 = eval("(" + jsonArrayNetworkDataReceive8 + ")");
	upnetworkReceive8 = networkDataReceivedata8;
	var jsonArrayNetworkDataTransmit8 = '${jsonArrayNetworkDataTransmit8}';
	networkDataTransmitdata8 = eval("(" + jsonArrayNetworkDataTransmit8 + ")");
	upnetworkTransmit8 = networkDataTransmitdata8;
	
	var jsonArrayNetworkDataReceive9 = '${jsonArrayNetworkDataReceive9}';
	networkDataReceivedata9 = eval("(" + jsonArrayNetworkDataReceive9 + ")");
	upnetworkReceive9 = networkDataReceivedata9;
	var jsonArrayNetworkDataTransmit9 = '${jsonArrayNetworkDataTransmit9}';
	networkDataTransmitdata9 = eval("(" + jsonArrayNetworkDataTransmit9 + ")");
	upnetworkTransmit9 = networkDataTransmitdata9;
	
	var jsonArrayNetworkDataReceive10 = '${jsonArrayNetworkDataReceive10}';
	networkDataReceivedata10 = eval("(" + jsonArrayNetworkDataReceive10 + ")");
	upnetworkReceive10 = networkDataReceivedata10;
	var jsonArrayNetworkDataTransmit10 = '${jsonArrayNetworkDataTransmit10}';
	networkDataTransmitdata10 = eval("(" + jsonArrayNetworkDataTransmit10 + ")");
	upnetworkTransmit10 = networkDataTransmitdata10;
						$('#event').jqChart({
							title : {
								text : ''
							},
							border : {
								strokeStyle : '#6ba851'
							},
							background : background,
							legend : {
								visible : false
							},
							axes : [ {
								type : 'linear',
								location : 'left',
								minimum : 0,
								maximum : 100
							} ],
							series : [ {
								type : 'column',
								data : data,
								fillStyle : fillStyle
							} ]
						});

						/* updateChart(); */
						//network
						var networkbackground = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit,
							     markers: null
							 }
							]
						});

						$('#network')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
										
						//2
						var networkbackground2 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network2').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground2,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive2,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit2,
							     markers: null
							 }
							]
						});

						$('#network2')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//3
						var networkbackground3 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network3').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground3,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive3,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit3,
							     markers: null
							 }
							]
						});

						$('#network3')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//4
						var networkbackground4 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network4').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground4,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive4,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit4,
							     markers: null
							 }
							]
						});

						$('#network4')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//5
						var networkbackground5 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network5').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground5,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive5,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit5,
							     markers: null
							 }
							]
						});

						$('#network5')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//6
						var networkbackground6 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network6').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground6,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive6,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit6,
							     markers: null
							 }
							]
						});

						$('#network6')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//7
						var networkbackground7 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network7').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground7,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive7,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit7,
							     markers: null
							 }
							]
						});

						$('#network7')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//8
						var networkbackground8 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network8').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground8,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive8,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit8,
							     markers: null
							 }
							]
						});

						$('#network8')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//9
						var networkbackground9 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network9').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground9,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive9,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit9,
							     markers: null
							 }
							]
						});

						$('#network9')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//10
						var networkbackground10 = {
							type : 'linearGradient',
							x0 : 0,
							y0 : 0,
							x1 : 0,
							y1 : 1,
							colorStops : [ {
								offset : 0,
								color : '#d2e6c9'
							}, {
								offset : 1,
								color : 'white'
							} ]
						};

						$('#network10').jqChart({
							title : '',
							//legend: { title: 'Legend' },
							border : {
								strokeStyle : '#6ba851'
							},
							background : networkbackground10,
							/* animation : {
								duration : 2
							},会重画图 */
							tooltips : {
								type : 'shared'
							},
							legend : {
								//添加
								visible : false
							},
							crosshairs : {
								enabled : true,
								hLine : false,
								vLine : {
									strokeStyle : '#cc0a0c'
								}
							},
							/* axes : [ {
								type : 'dateTime',
								location : 'bottom',
								zoomEnabled : true
							} ], */
							series : [ {
								//title: '',
								type : 'line',
								data : upnetworkReceive10,
								markers : null
							},
							 {
							     //title: '',
							     type: 'line',
							     data: upnetworkTransmit10,
							     markers: null
							 }
							]
						});

						$('#network10')
								.bind(
										'tooltipFormat',
										function(e, data) {

											/* var date = data.chart
													.stringFormat(data.x,
															"ddd, mmm dS, yyyy"); */
											var tooltip = '<b>'
													/* + "网络流量"+ */
													+ '<span style="color:' + data[0].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[0].y
													+ '</b></br>'
													
													+ '<span style="color:' + data[1].series.fillStyle + '">'
													+ '网络流量(MB)' + ': </span>'
													+ '<b>' + data[1].y
													+ '</b></br>';
											return tooltip;
										});
						//END
					});
//setTimeout("updateChart()", 5000);
	var updateChart= function updateChart() {
       $.ajax({
			type : "POST",
			url : "${ctx}/showSysInfo/UpdateNetwork.action",
			async : false,
			/* data:"&assetMac=" + upcpu, */
			dataType : 'java',
			success : function(msg) {
			
			var tempMemory = eval("(" + msg + ")");
			updateNetwork(tempMemory[0],tempMemory[1]);
			updateNetwork2(tempMemory[2],tempMemory[3]);
			updateNetwork3(tempMemory[4],tempMemory[5]);
			updateNetwork4(tempMemory[5],tempMemory[7]);
			updateNetwork5(tempMemory[8],tempMemory[9]);
			updateNetwork6(tempMemory[10],tempMemory[11]);
			updateNetwork7(tempMemory[12],tempMemory[13]);
			updateNetwork8(tempMemory[14],tempMemory[15]);
			updateNetwork9(tempMemory[16],tempMemory[17]);
			updateNetwork10(tempMemory[18],tempMemory[19]);
			//setTimeout("updateChart()", 5000);
			}
			
	});
	}
	$(function(){
		updateChart();
		var timer = setInterval(updateChart, 1000*5);
	});
	function updateNetwork(data,data1) {
		upnetworkReceive.splice(0, 1);
		upnetworkReceive.push(data);
		$('#network').jqChart('update');
		upnetworkTransmit.splice(0, 1);
		upnetworkTransmit.push(data1);
		$('#network').jqChart('update');
	}
	function updateNetwork2(data,data1) {
		upnetworkReceive2.splice(0, 1);
		upnetworkReceive2.push(data);
		$('#network2').jqChart('update');
		upnetworkTransmit2.splice(0, 1);
		upnetworkTransmit2.push(data1);
		$('#network2').jqChart('update');
	}
	function updateNetwork3(data,data1) {
		upnetworkReceive3.splice(0, 1);
		upnetworkReceive3.push(data);
		$('#network3').jqChart('update');
		upnetworkTransmit3.splice(0, 1);
		upnetworkTransmit3.push(data1);
		$('#network3').jqChart('update');
	}
	function updateNetwork4(data,data1) {
		upnetworkReceive4.splice(0, 1);
		upnetworkReceive4.push(data);
		$('#network4').jqChart('update');
		upnetworkTransmit4.splice(0, 1);
		upnetworkTransmit4.push(data1);
		$('#network4').jqChart('update');
	}
	function updateNetwork5(data,data1) {
		upnetworkReceive5.splice(0, 1);
		upnetworkReceive5.push(data);
		$('#network5').jqChart('update');
		upnetworkTransmit5.splice(0, 1);
		upnetworkTransmit5.push(data1);
		$('#network5').jqChart('update');
	}
	function updateNetwork6(data,data1) {
		upnetworkReceive6.splice(0, 1);
		upnetworkReceive6.push(data);
		$('#network6').jqChart('update');
		upnetworkTransmit6.splice(0, 1);
		upnetworkTransmit6.push(data1);
		$('#network6').jqChart('update');
	}
	function updateNetwork7(data,data1) {
		upnetworkReceive7.splice(0, 1);
		upnetworkReceive7.push(data);
		$('#network7').jqChart('update');
		upnetworkTransmit7.splice(0, 1);
		upnetworkTransmit7.push(data1);
		$('#network7').jqChart('update');
	}
	function updateNetwork8(data,data1) {
		upnetworkReceive8.splice(0, 1);
		upnetworkReceive8.push(data);
		$('#network8').jqChart('update');
		upnetworkTransmit8.splice(0, 1);
		upnetworkTransmit8.push(data1);
		$('#network8').jqChart('update');
	}
	function updateNetwork9(data,data1) {
		upnetworkReceive9.splice(0, 1);
		upnetworkReceive9.push(data);
		$('#network9').jqChart('update');
		upnetworkTransmit9.splice(0, 1);
		upnetworkTransmit9.push(data1);
		$('#network9').jqChart('update');
	}
	function updateNetwork10(data,data1) {
		upnetworkReceive10.splice(0, 1);
		upnetworkReceive10.push(data);
		$('#network10').jqChart('update');
		upnetworkTransmit10.splice(0, 1);
		upnetworkTransmit10.push(data1);
		$('#network10').jqChart('update');
	}
</script>

</head>
<body>
	<s:form action="UpdateNetwork" namespace="/showSysInfo" method="post"
		theme="simple" id="empForm" name="empForm">
		<table style="margin-left:5px;margin-top:2px" width="99.5%" border="0"
			cellspacing="1" cellpadding="0" class="tab2">
			<!-- 空行 -->
			<tr id="net1">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net2">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti">系统网络状态  网卡  1</div>
						<div id="network" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net3">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net4">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti"  id="net2">系统网络状态  网卡  2</div>
						<div id="network2" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net5">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net6">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti"  id="net3">系统网络状态  网卡  3</div>
						<div id="network3" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net7">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net8">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti"  id="net4">系统网络状态  网卡  4</div>
						<div id="network4" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net9">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net10">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti"  id="net5">系统网络状态  网卡  5</div>
						<div id="network5" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net11">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net12">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti"  id="net6">系统网络状态  网卡  6</div>
						<div id="network6" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net13">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net14">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti7" id="net7">系统网络状态  网卡  7</div>
						<div id="network7" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net15">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net16">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti" id="net8">系统网络状态  网卡  8</div>
						<div id="network8" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net17">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net18">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti" id="net9">系统网络状态  网卡  9</div>
						<div id="network9" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
			<!-- 空行 -->
			<tr id="net19">
				<td style="padding-top:5px" colspan="2"></td>
			</tr>
			<tr id="net20">
				<td colspan="2"
					style="padding-left:3px; padding-right:3px; padding-top:3px; padding-bottom:3px"><div>
					<div class="biaoti" id="net10">系统网络状态  网卡  10</div>
						<div id="network10" style="width: 100%; height: 220px;"></div>
					</div></td>
			</tr>
		</table>
	</s:form>
</body>
</html>