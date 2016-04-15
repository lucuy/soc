
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
<script type='text/javascript' src="${ctx}/scripts/monitorAlarmPage.js"></script>

<title>apm监控</title>
<link href="${ctx}/images/favicon.ico" rel="shortcut icon" />
<link href="${ctx}/images/favicon.ico" rel="Bookmark" />

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css" /> 

<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />--%>
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
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/highcharts.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<!-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
加载图表需要的js 
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>-->
	<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
	<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->

<style type="text/css">
.biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
}
</style>
<script language="javascript">
	//初始化时候内存的占用率
    var data1
    //更新时候内存的占用率
    var updatememryData;
    //初始化时候的cpu占用率
    var cpuData;
    //更新时候的cpu占用率
    var updateCpuData;
    
    //初始化时候的发送流量
    var sendFlow ;
    //更新时候的 发送流量
    var updateSendFlow;
    //初始化时候的接收流量
    var receiveFlow;
    //更新时候的接收流量
    var updateReceiveFlow;
    
	$(document).ready(
			function() {
				
				// 历史查询-Dialog			
				$('#dialog-extQuery').dialog({
					autoOpen : false,
					width : 450,
					height : 280,
					buttons : {
						"查询[Enter]" : function() {
							
							extQuery();
						},

						"取消[Esc]" : function() {
							$(this).dialog("close");
						}
					}
				});
			
			     Highcharts.setOptions({ 
                 global: { 
                  useUTC: false 
                  } 
                });
			

				var temps = '${jsonArray}';

			    $('#assetMac').val('${agent.ip}');

				temp = eval("(" + temps + ")");
				
				if (temp.length >= 2) {

				 data1 = temp[0];
				 
				// memryData = data1.split(',');
				 //memryData.pop();
				 
				//alert(data1.length);
				 
		        var length = data1.length-1;
				 
				updatememryData = data1[length];
				
				//cpu占用率
				cpuData = temp[1];
				
				var length1 = cpuData.length-1;
				
				updateCpuData = cpuData[length1];
				
				//磁盘使用率
				
				diskData = temp[2][1];
				
				//磁盘名称
				diskInfo = temp[2][0];
				
				//初始化磁盘图表
				initdisk();
				
				sendFlow = temp[3];
				
				var length2 = sendFlow.length-1;
				updateSendFlow =sendFlow[length2]; 
			    receiveFlow = temp[4];
			    var length3 =receiveFlow.length-1;
			    updateReceiveFlow =  receiveFlow[length3];
			    
			    
				} 
            
            //书写内存占用率的程序
		    var memChart;

		    memChart = new Highcharts.Chart({
		    		colors: [
		    	         '#aeeeee'
		    	    ],
					chart : {
						renderTo : 'chart_0',
						type: 'area',
						marginRight : 10,
						height : 180,
						events : {
							load : function() {

								// set up the updating of the chart each second
								var series = this.series[0];
						    	setInterval(function() {
									  var x = (new Date()).getTime(), // current time
									  y = updatememryData;
									  series.addPoint([x, y], true, true);
					    		}, 30000);
							}
						}
					},
					
					title : {
						text : ''
					}, 
					xAxis : {
						type : 'datetime',
						tickPixelInterval : 100
					},
					yAxis : {
						title : {
							text : ''
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ]
					},
					tooltip : {
						formatter : function() {
							return '<b>'
									+ this.series.name
									+ '</b><br/>'
									+ Highcharts.dateFormat(
											'%Y-%m-%d %H:%M:%S', this.x)
									+ '<br/>'
									+ Highcharts.numberFormat(this.y, 2)+'%';
						}
					},
					legend : {
						enabled : false
					},
					credits: {
			            enabled: false
		            },
					exporting : {
						enabled : false
					},
					series : [ {
						name : '内存占用率',
						data : (function() {
							// generate an array of random data
							var time = (new Date()).getTime()-5*6*5000;
							var i;
							var data=[];

							for (i = 0; i <= 5; i++) {
								data.push({
									x : time + i * 5000*6,
									y : data1[i]
								});
							}
							return data;
						})()
					} ]
				});
                
                var cpuChart;
                cpuChart = new Highcharts.Chart({
		    		colors: [
			    		'#aeeeee'
			    	],
					chart : {
						renderTo : 'chart_1',
						type: 'area',
						marginRight : 10,
						height : 180,
						events : {
							load : function() {

								// set up the updating of the chart each second
						var series = this.series[0];		
					     setInterval(function() {
								  var x = (new Date()).getTime(), // current time
								  y = updateCpuData;
								  series.addPoint([x, y], true, true);
								  
				    }, 30000);
							}
						}
					},
					 title : {
						text : ''
					}, 
					xAxis : {
						type : 'datetime',
						tickPixelInterval : 100
					},
					yAxis : {
						title : {
							text : ''
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ]
					},
					plotOptions: {
                  line: {
                      dataLabels: {
                          enabled: true
                      },
                        enableMouseTracking: true
                        }
                     },
					
			  		tooltip : {
						formatter : function() {
							return '<b>'
							+ this.series.name
							+ '</b><br/>'
							+ Highcharts.dateFormat(
									'%Y-%m-%d %H:%M:%S', this.x)
							+ '<br/>'
							+ Highcharts.numberFormat(this.y, 2)+'%';
						}
					},
					
					legend : {
						enabled : false
					},
					credits: {
			            enabled: false
		            },
					exporting : {
						enabled : false
					},
					series : [ {
						name : 'cpu占用率',
						data : (function() {
							// generate an array of random data
							var time = (new Date()).getTime()-5*6*5000;
							var i;
							var data=[];
							for (i = 0; i <= 5; i++) {
								data.push({
									x : time + i * 5000*6,
									y : cpuData[i]
								});
							}
							return data;
						})()
					}
					]
				});
				
                
                
                


				
				
    

			    

                
                //编写网络流量
                var netFlowChart;
                 netFlowChart = new Highcharts.Chart({
					chart : {
						renderTo : 'chart_3',
						defaultSeriesTypetype :'spline',
						marginRight : 10,
						height : 180,
						events : {
							load : function() {
								// set up the updating of the chart each second
								var series = this.series[0];
								var series1 = this.series[1];
					     setInterval(function() {
								  var x = (new Date()).getTime(), // current time
								  y = updateSendFlow,
								  y1 = updateReceiveFlow;
								  series.addPoint([x, y], true, true);
								  series1.addPoint([x, y1], true, true);
				    }, 30000);
							}
						}
					},
					 title : {
						text : ''
					}, 
					xAxis : {
						type : 'datetime',
						tickPixelInterval : 300
					},
					yAxis : {
						title : {
							text : ''
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ]
					},
					tooltip : {
						 formatter : function() {
								return '<b>'
								+ this.series.name
								+ '</b><br/>'
								+ Highcharts.dateFormat(
										'%Y-%m-%d %H:%M:%S', this.x)
								+ '<br/>'
								+ Highcharts.numberFormat(this.y, 5)+'M';
							}
					},
					legend : {
						enabled : true,
						align: 'right',
			            verticalAlign: 'top',
					},
					credits: {
			            enabled: false
		            },
					exporting : {
						enabled : false
					},
					series : [ {
						name : '发送流量',
						data : (function() {
							// generate an array of random data
							var time = (new Date()).getTime()-5*6*5000;
							var i;
							var data=[];

							for (i = 0; i <= 5; i++) {
								data.push({
									x : time + i * 5000*6,
									y : sendFlow[i]
								});
							}
							return data;
						})()
					},
					{
						name : '接收流量',
						data : (function() {
							// generate an array of random data
							var time = (new Date()).getTime()-5*6*5000;
							var i;
							var data1=[];

							for (i = 0; i <= 5; i++) {
								data1.push({
									x : time + i * 5000*6,
									y : receiveFlow[i]
								});
							}
							return data1;
						})()
					} ]
				});
                
				//选项卡
			    $('#tabs-setting').tabs();

				showmonitorAlarm();

				setTimeout("updateChart()", 6*5000);

				setTimeout("updateMonitorAlarm()",1000*6000);
				
				
			});	
				
				

	function updateMonitorAlarm()
	{
	    showmonitorAlarm();
	    setTimeout("updateMonitorAlarm()",1000*6000);
	}

	 function updateChart() {
	 
		var assetMac = $('#assetMavPro').val();
		$.ajax({
			type : "POST",
			url : "${ctx}/monitorGroup/queryDynamic.action",
			async : false,
			data : "&assetMac="+ assetMac,
			dataType : 'text',
			success : function(msg) {

				var temp = eval("(" + msg + ")");

				//alert(temp.length);

				if (temp.length > 2) {

					//updateMemory(temp[0]);
					
					//alert(temp[0]);
					
                    updatememryData =temp[0][0];
                   // alert(updatememryData)
                    updateCpuData = temp[1][0];
					//updateCpu(temp[1]);
					//磁盘使用率
				
					diskData = temp[2][1];
					//if(diskData != ""){
					//	diskchart.series[0].setData(diskData);
					//}
					//磁盘名称
					diskInfo = temp[2][0];
					
					
					// = temp[2];
                    updateSendFlow = temp[3][0];
                    updateReceiveFlow =  temp[4][0];
					//updateFlow(temp[3], temp[4]);
					
					$('#ver').text(temp[6]);
					
					$('#agentRuntime').text(temp[5]);
					
					$('#operatingSystem').text(temp[7]);
					$('#cpuType').text(temp[8]);
					$('#memoryTotal').text(temp[9]);
					$('#agentInstallPath').text(temp[10]);
					$('#ip').text(temp[11]);
					$('#uptime').text(temp[12]);
					
					$('#msg').text("资产："+temp[13]);
					
				}else {
				
					$('#msg').text("资产："+temp[0]);
				}

			}
		});

		setTimeout("updateChart()", 6*5000);
	}
	//更新流量
	/* function updateFlow(up, down) {
		data4.splice(0, 1);
		data4.push(up);

		data5.splice(0, 1);
		data5.push(down);
		$('#chart_3').jqChart('update');

	} */

	/* function initAreaChart(div, title) {
		$('#chart_' + div).jqChart({
			background : background,
			legend : {
				visible : false
			},
			border : {

				strokeStyle : 'white'
			},
			axes : [ {
				location : 'left',
				labels : {
					fillStyle : 'red',
					stringFormat : '%d%'
				/* angle : 30 */
	/*},
	minimum : 0,
	maximum : 100,
	interval : 20
	}

	],
	series : [ {
	type : 'area',
	font : '22 sans-serif',
	data : data1,
	fillStyle : fillStyle1,
	labels : {
		font : '11px sans-serif',
		fillStyle : 'red',
		stringFormat : '%d%',
		valueType : 'dateValue'
	}
	} ]
	});

	} */

	/* function initAreaChart1(div, title) {
		$('#chart_' + div).jqChart({
			background : background,
			legend : {
				visible : false
			},
			border : {

				strokeStyle : 'white'
			},
			axes : [ {
				location : 'left',
				labels : {
					fillStyle : 'red',
					stringFormat : '%d%'
				/* angle : 30 */
	/*},
	minimum : 0,
	maximum : 100,
	interval : 20
	} ],
	series : [ {
	type : 'area',
	font : '22 sans-serif',
	data : data2,
	fillStyle : fillStyle1,
	labels : {
		font : '11px sans-serif',
		fillStyle : 'red',
		stringFormat : '%d%',
		valueType : 'dateValue'
	}
	} ]
	});

	} */

	/* function initlineChart(data1, data2) {
		$('#chart_3').jqChart({
			background : background,
			legend : {
				visible : true
			},
			border : {

				strokeStyle : 'white'
			},
			axes : [ {
				location : 'left',
				labels : {
					fillStyle : 'red',
					stringFormat : '%d M'
				}
			} ],
			series : [ {
				type : 'line',
				title : "发送流量",
				font : '22 sans-serif',
				data : data4,
				fillStyle : fillStyle1,
				labels : {
					font : '11px sans-serif',
					fillStyle : 'red',
					valueType : 'dateValue'
				}
			}, {
				type : 'line',
				title : "接收流量",
				font : '22 sans-serif',
				data : data5,
				fillStyle : fillStyle1,
				labels : {
					font : '11px sans-serif',
					fillStyle : 'red',
					valueType : 'dateValue'

				}

			} ]
		});

	}
	 */
	//更新软件信息的方法
	var updataSoftMsg = function (){
		
		var assetMac = $('#assetMavPro').val();
		//alert(assetMac);  
		$.post("${ctx}/monitorGroup/softMsg.action",{'assetMac':assetMac},function(data){
		//	alert(${assetSupportDeviceId});
			if(${assetSupportDeviceId}==41){
				if(data!=null){
					$("#softBody").html("");
					$.each(data, function(i, service) {		
						//测试数据 最后两个 变成 i 和时间戳
										var $str1 = '<tr ><td valign="middle" class="td_list_data" >'
												+ service.softId
												+ '</td><td style="text-align:left;padding-left:5px;"class="td_list_data" >'
												+ service.softName
												+ '</td><td valign="middle" class="td_list_data" >'
												+ service.installDate
												+ '</td><td valign="middle" class="td_list_data" >'
												+ service.softVerstion
												+ '</td><td style="text-align:left;padding-left:5px;"class="td_list_data" >'
												+ service.softFirm
												+ '</td><td  style="text-align:left;padding-left:5px;" class="td_list_data" >'
												+ service.softPath
												+ '</td></tr>';

										$("#softBody").append($str1);

									}); 	 	
				}
			}
		
		//var service = data;
		//清空之前的数据
		
			
			
			
		});
		
	}
	 
	//更新服务信息的方法
	var updataServiceMsg = function (){
		
		var assetMac = $('#assetMavPro').val();
		var assetSupportDeviceId = "${assetSupportDeviceId }";
		//alert(assetMac);
		$.post("${ctx}/monitorGroup/serviceMsg.action",{'assetMac':assetMac,'assetSupportDeviceId':assetSupportDeviceId},function(data){
			//alert(data.length);
			if(${assetSupportDeviceId}==40){
				//
				$("#softli").hide();//在linux中屏蔽掉软件信息
				if(data!=null){
					$("#serviceBody").html("");
					$.each(data, function(i, service) {	
						
						//测试数据 最后两个 变成 i 和时间戳
										var $str1 = '<tr ><td valign="middle" class="td_list_data" >'
												+ service.content
												+ '</td></tr>';

										$("#serviceBody").append($str1);

									}); 	
				}
			}else{
				if(data!=null){
					$("#serviceBody").html("");
					$.each(data, function(i, service) {		
						//测试数据 最后两个 变成 i 和时间戳
										var $str1 = '<tr ><td valign="middle" class="td_list_data" >'
												+ service.serviceName
												+ '</td><td valign="middle" class="td_list_data" >'
												+ service.installStatus
												+ '</td><td valign="middle" class="td_list_data" >'
												+ service.operatestatus
												+ '</td><td valign="middle" class="td_list_data" >'
												+ service.canBeUninstall 
												+ '</td><td valign="middle" class="td_list_data" >'
												+ service.canBeStop
												+ '</td></tr>';

										$("#serviceBody").append($str1);

									}); 	 	
				}
			}
		
		//var service = data;
		//清空之前的数据
		
			
			
			
		});
		
	}
	
	
	
	//更新进程信息的方法
	var updataProgressMsg = function (){
		
		var assetMac = $('#assetMavPro').val();
		var assetSupportDeviceId = "${assetSupportDeviceId}" ; 
		//alert(assetMac);
		$.post("${ctx}/monitorGroup/progressMsg.action",{'assetMac':assetMac,'assetSupportDeviceId':assetSupportDeviceId},function(data){
			//alert(data);
			
			if(assetSupportDeviceId==40){
				//
				if(data!=null){
					$("#progressBody").html("");
					$.each(data, function(i, progress) {		
						//测试数据 最后两个 变成 i 和时间戳
										var $str1 = '<tr><td valign="middle" class="td_list_data" >'
												+ progress.progressNamePID
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.USER
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.PR
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.NI 
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.VIRT
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.RES
												+'</td><td valign="middle" class="td_list_data" >'
												+ progress.SHR
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.s
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.CPU
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.MEM 
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.TIME
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.COMMAND
												+ '</td></tr>';

										$("#progressBody").append($str1);

									}); 	
				}
			}else{
				if(data!=null){
					$("#progressBody").html("");
					$.each(data, function(i, progress) {		
						//测试数据 最后两个 变成 i 和时间戳
										var $str1 = '<tr ><td valign="middle" class="td_list_data" >'
												+ progress.progressName
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.progressNamePID
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.dialogueName
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.dialogueCount 
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.memoryUsage
												+ '</td><td valign="middle" class="td_list_data" >'
												+ progress.timeCPU
												+'</td></tr>';

										$("#progressBody").append($str1);

									}); 		 	
				}
			}

		//var service = data;
		//清空之前的数据
		});
		
	}
	
	$(function(){
		updataProgressMsg();
		var timer = setInterval(updataProgressMsg, 5000*60);
	});
	
	$(function(){
		updataServiceMsg();
		var timer = setInterval(updataServiceMsg, 5000*60);
	});
	$(function(){
		updataSoftMsg();
		var timer = setInterval(updataSoftMsg, 5000*60);
	});

	//刷新界面
	function reload() {
		location.reload();
	}

	//动态加载告警信息列表
	function showmonitorAlarm() {
		var keyword = $("#keyword").val();
		var assetId = $("#assetId").val();
		var reg=  /^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/ ;
		if(!reg.test(keyword)){
			alert("输入内容中含有非法字符，请重新输入...");
			$("#keyword").val("");
			return ;
		}
		getEventListJson(
				"${ctx}/monitorGroup/queryMonitorAlarm.action?startIndex="
						+ encodeURI(0, "utf-8") + "&keyword="
						+ encodeURI(encodeURI(keyword, "utf-8")) + "&AssetId="
						+ assetId, 0);
	}

	function getEventListJson(url, num) {
		var assetId = $("#assetId").val();
		var htmlStr="";
		$.ajax({
			url : url,
	        type : "post",
	        dataType : "json",
	        async : false ,
	        success : function(result){
	        	$("#bottomtable tr:not(:first)").remove();
				$("#bottomtable tr:first").remove();
				$("#test12").remove();
				$.each(result,function(i,item){
					var rowNum = $("#bottomtable tr").length - 1;
					if (rowNum < 0) {
						rowNum = 0;
					}
					if (item.monitorAlarmId
							&& item.monitorAlarmId != 'undefined') {
						htmlStr += "<tr>";
						htmlStr += "<td valign='middle' align='center' width='10%'>"
								+ item.monitorAlarmRank;
						htmlStr += "</td>";
						htmlStr += "<td valign='middle' align='center' width='10%'>"
								+ item.monitorAlarmIndex;
						htmlStr += "</td>";
						htmlStr += "<td valign='middle' align='center' width='25%'>"
								+ item.monitorAlarmContent;
						htmlStr += "</td>";
						htmlStr += "<td valign='middle' align='center' width='10%'>"
								+ item.monitorAlarmCurrentValue.toFixed(2);
						htmlStr += "</td>";
						htmlStr += "<td valign='middle' align='center' width='10%'>"
								+ item.monitorAlarmThreshold;
						htmlStr += "</td>";
						//alert(item.monitorAlarmTimes);
						//var alertDate = new Date(item.monitorAlarmTime).format("yyyy-MM-dd HH:mm:ss");   
						//alert(alertDate);
						htmlStr += "<td valign='middle' align='center' width=''>"
								+ item.monitorAlarmTimes;
						htmlStr += "</td>";
						htmlStr += "</tr>";
						//alert(htmlStr);
						/* $(htmlStr)
								.insertAfter(
										$("#bottomtable tr:eq("
												+ rowNum
												+ ")")); */

						// $(htmlStr).insertAfter($("#bottomtable"));
					}
					if (item.currentPage) {
						paging_page(
								"footer",
								"${ctx}/monitorGroup/queryMonitorAlarm.action",
								item, "keyword",
								"getEventListJson",
								1, assetId);
					}
					$("#monitorAlarm").html(htmlStr);
				});
	        },
	        error:function(){
	        	alert("服务器问题");
	        }
		});
	}
	
	
    function initdisk(){
	var diskchart = null;
	diskchart = new Highcharts.Chart({
		colors: [
    		'#aeeeee'
    	],
		chart: {
			renderTo : 'chart_2',
            type: 'bar',
        },
        title: {
            text: '',
        },
        subtitle: {
            text: '',
        },
        xAxis: {
            categories: diskInfo,
            title: {
                text: null,
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '',
                align: 'high',
            },
            labels: {
                overflow: 'justify',
            },
        },
        tooltip: {
            valueSuffix: ' %',
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true,
                },
            },
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: 4000,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true,
			enabled : false,
        },
        credits: {
            enabled: false,
        },
       series: [{
           name: ['使用率'],
           data: diskData,
       }],
    });
};

function extQueryDlg() {
	$('#dialog-extQuery').dialog('open');
}
function extQuery(){
	var ip = $("#assetMavPro").val();
	var falg=$("#checkID").val();
	var biaozhi ='${assetSupportDeviceId}';
	 window.open('${ctx}/monitorGroup/queryMonitorAPM.action?ip='+ip+'&falg='+falg+'&biaozhi='+biaozhi);
		$('#dialog-extQuery').dialog("close");
	
}
//定时刷新数据开始	
$(function() {
   var handler = function() {
	   initdisk();
   }   
   var time = setInterval(handler,30000);
});
//定时刷新数据结束

</script>
<style type="text/css">
.tbl1 {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	color: black;
	height: 28px;
	text-align: center;
}

.level {
	padding: 0px 0px 0px 0px;
	margin: 2px 2px 2px 4px;
	border: 1px solid #CCCCC0;
	height: 12px;
	width: 40px;
}

.levelBa {
	height: 12px;
	margin: 0px 0px 0px 0px;
}
</style>

</head>

<body style="margin-top:2px;margin-left: 2px;">
	<input type="hidden" value="${assetMac}" id="assetMavPro" />
	<input type="hidden" value="${AssetId}" id="assetId" />
	<div style="width: 100%">
		<div id="tabs-setting" style="width: 99%; margin:2px auto">
			<ul style="background: #4184BB">
				<li><a href="#showpic" style="cursor: pointer;"
					onclick="reload();">监控</a></li>

				<li><a href="#showother1" style="cursor: pointer;">进程</a></li>
				<li><a href="#showother2" style="cussor: pointer">服务</a></li>
					<li id = "softli"><a href="#showother3" style="cussor: pointer">软件信息</a></li>
					<%--<div style="position:absolute;right: 20px;top: 10px;"><input type="button" value="历史记录"
								 class="btnstyle"
								onclick="extQueryDlg();" /></div>
					
			--%></ul>
			<div id="showpic" class="framDiv" style="width:97%">
				<s:form action="saveMonitor.action" namespace="/monitor"
					method="post" theme="simple" id="monitorForm" name="monitorForm">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td class="r2titler"><b>监控&nbsp;&nbsp;<span id="msg">资产：</span>
									<c:if test="${assetName!=null}">>>${assetName}</c:if> </b> <span
								id="back" style="float:right;margin-right:20px;"><b>

								</b> </span></td>
						</tr>
						<tr>
							<td>
								<table width="100%" cellSpacing="1" cellpadding="6">
									<tr>
										<!-- <td class="biaoti" width="33%"
									style="border:1px solid #C1DDF1;">
									<div id="chart_0" style="width:100%;height:100%;display:block;">
									</div>
								</td>
								<td class="biaoti" width="33%" height="200px" id="monitor_1"
									style="border:1px solid #C1DDF1;">
									<div id="chart_1" style="width:100%;height:100%;display:block;">

									</div></td>
								<td class="biaoti" width="33%" height="200px" id="monitor_2"
									style="border:1px solid #C1DDF1;"><s:hidden
										name="monitor2" id="monitor2" />
									<div id="chart_2" style="width:100%;height:100%;display:block;">

									</div></td> -->
										<td class="biaoti" width="33%">内存占有率</td>
										<td class="biaoti" width="33%">cpu占有率</td>
										<td class="biaoti" width="33%">磁盘使用率</td>
									</tr>
									<tr>
										<td style="width: 33%">
											<div id="chart_0"
												style="width:100%;height:180px;display:block;"></div></td>
										<td style="width: 33%">
											<div id="chart_1"
												style="width:100%;height:180px;display:block;"></div></td>
										<td style="width: 33%">
											<div id="chart_2"
												style="width:33%;height:180px;display:inline;"></div></td>
									</tr>
									<tr>
										<td class="biaoti" id="monitor_3" colspan="3">网络流量</td>
										<!-- <td width="33%" height="200px" id="monitor_4"
									style="border:1px solid #C1DDF1;"><s:hidden
										name="monitor4" id="monitor4" />
									<div id="chart_4" style="width:100%;height:100%;display:none;">

									</div>
								</td>
								<td width="33%" height="200px" id="monitor_5"
									style="border:1px solid #C1DDF1;"><s:hidden
										name="monitor5" id="monitor5" />
									<div id="chart_5" style="width:100%;height:100%;display:none;">

									</div>
								</td> -->
									</tr>
									<tr>
										<td height="180px" colspan="3">
											<div id="chart_3"
												style="width:100%;height:100%;display:block;"></div>
										</td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- </table>
			  <td>
			 </tr> -->

					</table>

				</s:form>
			</div>

			<!-- 测试 其他-->
			<div id="showother1" style="width: 99%; height: 450px">

				<div id="sbox" style="overflow-y:auto;  height:100%;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="colTable">

						<s:if test="assetSupportDeviceId==40">
							<td width="9%" class="biaoti">PID</td>
							<td width="9%" class="biaoti">USER</td>
							<td width="7%" class="biaoti">PR</td>
							<td width="7%" class="biaoti">NI</td>
							<td width="9%" class="biaoti">VIRT</td>
							<td width="9%" class="biaoti">RES</td>
							<td width="9%" class="biaoti">SHR</td>
							<td width="9%" class="biaoti">S</td>
							<td width="9%" class="biaoti">%CPU</td>
							<td width="9%" class="biaoti">%MEM</td>
							<td width="9%" class="biaoti">TIME+</td>
							<td width="14%" class="biaoti">COMMAND</td>
						</s:if>
						<s:else>
							<tr height="28" id="collectionTR">
								<td width="15%" class="biaoti">进程名</td>
								<td width="15%" class="biaoti">进程pid</td>
								<td width="15%" class="biaoti">会话名</td>
								<td width="15%" class="biaoti">会话数</td>
								<td width="15%" class="biaoti">内存使用 单位KB</td>
								<td width="15%" class="biaoti">运行时间</td>
							</tr>
						</s:else>
						<tbody id="progressBody"></tbody>
					</table>
				</div>
			</div>
			<div id="showother2" style="width: 99%; height: 450px">

				<div id="sbox" style="overflow-y:auto;  height:100%;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="colTable">
						<s:if test="assetSupportDeviceId==40">
							<tr height="28" id="collectionTR">
								<td width="20%" class="biaoti">服务内容</td>
							</tr>
						</s:if>
						<s:else>
							<tr height="28" id="collectionTR">
								<td width="20%" class="biaoti">服务名</td>
								<td width="20%" class="biaoti">安装状态</td>
								<td width="20%" class="biaoti">操作状态</td>
								<td width="20%" class="biaoti">是否可卸载</td>
								<td width="20%" class="biaoti">是否可停止</td>
							</tr>
						</s:else>
						<tbody id="serviceBody"></tbody>
					</table>
				</div>
			</div>
				<div id="showother3" style="width: 99%; height: 450px">

				<div id="sbox" style="overflow-y:auto;  height:100%;">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="colTable">
						<s:if test="assetSupportDeviceId==40">
							<tr height="28" id="collectionTR">
								<td width="20%" class="biaoti">服务内容</td>
							</tr>
						</s:if>
						<s:else> 
							<tr height="28" id="collectionTR"> 
								<td width="5%" class="biaoti">序号</td>
								<td width="23%" class="biaoti">软件名</td>
								<td width="8%" class="biaoti">安装时间</td>
								<td width="10%" class="biaoti">版本</td>
									<td width="12%" class="biaoti">厂商</td>
									<td width="*" class="biaoti">安装路径</td>
							</tr>
						</s:else>
						<tbody id="softBody"></tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- 切换tab结束 -->
		<div class="ui-tabs ui-widget ui-widget-content ui-corner-all"
			style="width: 99%; margin:2px auto">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="r2titler"><b>资产基本信息 </b></td>
				</tr>
				<tr>
					<td>
						<table width="100%">
							<tr>
								<td width="25%" align="center" height="25px" class="tbl1">资产基本信息</td>
								<td width="75%" align="center" height="25px" class="tbl1">告警信息</td>
							</tr>
							<tr>
								<td>
									<div id="mess_box"
										style="height:280px;overflow-y:auto;border:1px solid #C1DDF1"
										class="scrollstyle">
										<s:hidden id="assetMac" name="assetMac" />

										<table style="width:100%;height:100%">
											<tr style="text-align:center">
												<td width="30%" height="20px" style="padding-left:4px;"
													bgcolor="#F3F9FF">版本号</td>
												<td style="padding-left:8px" id="ver">${agent.versionnumber}</td>
											</tr>
											<tr style="text-align:center">
												<td width="30%" height="20px" style="padding-left:4px;"
													bgcolor="#F3F9FF">系统类型</td>
												<td id="operatingSystem" style="padding-left:8px">${agent.operatingSystem}</td>
											</tr>
											<tr height="20px" style="text-align:center">
												<td width="30%" style="padding-left:4px;text-align:center"
													bgcolor="#F3F9FF">CPU型号</td>
												<td id="cpuType" style="padding-left:8px">${agent.cpuType}</td>
											</tr>
											<tr height="20px" style="text-align:center">
												<td width="30%" style="padding-left:4px;text-align:center"
													bgcolor="#F3F9FF">内存</td>
												<td id="memoryTotal" style="padding-left:8px">${agent.memoryTotal}M</td>
											</tr>
											<tr height="20px" style="text-align:center">
												<td width="30%" style="padding-left:4px;text-align:center"
													bgcolor="#F3F9FF">Agent安装</td>
												<td id="agentInstallPath" style="padding-left:8px">${agent.agentInstallPath}</td>
											</tr>
											<tr height="20px" style="text-align:center">
												<td width="30%" style="padding-left:4px;text-align:center"
													bgcolor="#F3F9FF">IP</td>
												<td id="ip" style="padding-left:8px">${agent.ip}</td>
											</tr>
											<tr height="20px" style="text-align:center">
												<td width="30%" style="padding-left:4px;text-align:center"
													bgcolor="#F3F9FF">开机时间</td>
												<td id="uptime" style="padding-left:8px"><s:date
														name="agent.uptime" format="yyyy-MM-dd HH:mm:ss" /></td>
											</tr>
											<tr height="20px" style="text-align:center">
												<td width="30%" style="padding-left:4px;text-align:center"
													bgcolor="#F3F9FF">运行时间</td>
												<td style="padding-left:8px" id="agentRuntime">${agent.runtime}</td>
											</tr>
										</table>

									</div></td>
								<td>
									<div id="mess_box"
										style="height:280px;overflow-y:auto;border:1px solid #C1DDF1"
										class="scrollstyle">
										<table width="99.3%" border="0" cellspacing="0"
											cellpadding="0" style="margin-left: 4px; margin-top: 0px">
											<!-- 空行 -->
											<tr>
												<td></td>
											</tr>
											<tr>
												<td>
													<div class="box">
														<span class="kuaiju">快速搜索</span> <input type="text"
															id="keyword" value="${keyword}" name="keyword"
															class="jianju" /> <img src="${ctx}/images/search.jpg"
															onclick="showmonitorAlarm();" style="margin-left:5px">
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<div class="sbox">
														<div class="cont">
															<!-- information area -->
															<div id="dataList">
																<table width="100%" border="0" cellspacing="1"
																	id="footer" cellpadding="0" class="tab2">
																	<tr>
																		<td>
																			<table width="100%">
																				<tr height="28" id="collectionTR">
																					<td width="10%" class="biaoti">等级</td>
																					<td width="10%" class="biaoti">监控指标</td>
																					<td width="25%" class="biaoti">告警内容</td>
																					<td width="10%" class="biaoti">当前值</td>
																					<td width="10%" class="biaoti">阀值</td>
																					<td width="" class="biaoti">时间</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td align="center" width="100%">
																			<div
																				style="overflow-y:scroll; width:100%; height:400px"
																				id="screentable2">
																				<table width="100%" class="monitorAlarm"
																					cellspacing="1" cellpadding="0" id="bottomtable"
																					style="font-size: 13px">
																					<tbody id="monitorAlarm">

																					</tbody>
																					<!-- <tr>
																						<td width="10%">事件空</td>
																						<td width="10%">事件空</td>
																						<td width="25%">事件空</td>
																						<td width="10%">事件空</td>
																						<td width="10%">事件空</td>
																						<td width="">事件空</td>
																					</tr> -->
																				</table>

																			</div>
																		</td>
																	</tr>
																</table>
															</div>
														</div>
													</div></td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
		<div id="dialog-extQuery" title="历史查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			
			<tr>
				<td>角色:</td>
				<td>
				<select id="checkID" style="width:250px;" name='roleid' onkeypress="if(event.keyCode==13)extQuery();" >
				<option value="1">监控</option>
				<option value="2">进程</option>
				<option value="3">服务</option>
				<c:if test="${assetSupportDeviceId == 41}"><option value="4">软件信息</option></c:if>						
												</select>
			</tr>
			
		</table>
	</div>
</body>
</html>