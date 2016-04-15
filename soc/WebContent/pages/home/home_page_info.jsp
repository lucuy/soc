<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="refresh" content="300">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">



<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">


<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<!--<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
 加载图表需要的js 
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>-->
<!--[if IE]><script type="text/javascript" src="/soc/scripts/jqchart/excanvas.js"></script><![endif]-->
<!-- jsDate 格式化时间的函数 -->
<script type='text/javascript' src="${ctx}/scripts/jsDate.js"></script>
<script type="text/javascript" src="${ctx}/scripts/highcharts.js"></script>

<!-- portlet组要的东西 -->
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="${ctx}/styles/jquery.portlet.css?v=1.3.1" />
<%--<script src="${ctx}/scripts/jquery-1.7.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/scripts/jquery-ui-1.10.2.custom.min.js" type="text/javascript"></script>
	--%>
<script src="${ctx}/scripts/jquery.portlet.js"></script>
<!-- portlet组要的东西 -->
<style type="text/css">
.evnTitleTop {
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4;
}

.ui-dialog-titlebar-close {
	display: none;
}

.eventstrees {
	margin: 10px 5px 5px 25px;
	padding: 0px 0px 0px 0px;
	overflow-x: auto;
	overflow-y: auto;
	white-space: nowrap;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}

.back {
	background: #FFFFFF;
}

.hand {
	cursor: hand;
	background: #ccccff;
}

div#tip {
	position: absolute;
	z-index: 5555;
	width: 180px;
	height: auto;
	border: 1px solid #83B3D8;
	background-color: #FFFFFF;
	font-family: sans-serif;
	font-style: italic;
}
</style>
<script language="javascript">
	var fillStyle1 = {
		type : 'linearGradient',
		x0 : 0,
		y0 : 0,
		x1 : 0,
		y1 : 1,
		colorStops : [ {
			offset : 0,
			color : '#C1E4FE'
		}, {
			offset : 1,
			color : '#27A1FD'
		} ]
	};
	$(document).ready(function() {
		Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

		
		initPage();
		 $('#TenAlarmMessage').hover(
				   function(){
				    $('#tip').show();
				   },
				   function(){
					$('#tip').hide();	 
				 }
		);
		
		$("input [type=checkbox]" ).attr("checked", false);
		$.post("/soc/home/queryHomePageDiv.action", {}, function(data) {
			
			$.each(data, function(i, div) {
				$("#c" + div.id.substr(1) ).attr("checked", true);
			});
			////$('#portletdemo').portlet('option', 'remove', '#test1');
			//$('#portletdemo').portlet('option', 'remove', '#test2');
		//	var pageDivArr = data.split(",");
			
			//for ( var i = 0; i < pageDivArr.length; i++) {
			//		var a = i+1;
			//	if (pageDivArr[i] == "1") {
					//$('#portletdemo').portlet('option', 'remove', '#feeds'+i);//销毁
			//		$("#d" + a ).show();
					
			//		$("#c" + a ).attr("checked", true);
			//	} else {
				//	$("#d" + a ).hide();
				//	$("#c" + a ).attr("checked", false);
				//}
			//}
			

		});
	});
	//初始化界面的时候调用的方法 因为改变浏览器大小的时候要重新加载 所以在这边提出来
${initPage};
	function initcolumnChart() {
		chart = new Highcharts.Chart({
			chart: {
				 renderTo: 'chart_1',//要显示在div的id
             defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: [''] //X轴的坐标值
              
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
               y: 0,
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
                    
                    cursor: 'pointer',
					dataLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold'
						},
						formatter: function() {
							return Highcharts.numberFormat(this.y, 0);
						}
                        
					}                   
                },
        	   series: {
                  // pointPadding: 0.2,  //图表柱形的
                  // borderWidth:  0,     //图表柱形的粗细 
                	   cursor: 'pointer',
                       events: {
                			   click: function(e) {
                				  var name = e.point.series.name;
                				  var type = "" ;  
                				  if(name =="信息"){
                					  type = 1 ;
                				  }else if(name == "低级"){
                					  type = 2 ;
                				  }else if(name == "中级"){
                					  type = 3 ;
                				  }else if(name == "高级"){
                					  type = 4 ; 
                				  }else if(name == "严重"){
                					  type = 5 ; 
                				  }
                				  
                				 window.open("/soc/events/editCustom.action?timeRange=12&threatValue="+type+"&actionType=1&beginTime=&endTime=&sourcePort=&targetPort=&identification=10","_blank","width=1100,height=550,location=no,toolbar=no");
                			   }
                		   }
                	  
               }
           },
           
            series: 
            	  [${eventToday}]
        });

	}

	function initColumnChart1() {
			chart = new Highcharts.Chart({
			chart: {
				 renderTo: 'chart_3',//要显示在div的id
                       defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: [''] //X轴的坐标值
              
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
               y: 0,
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
                    
                    cursor: 'pointer',
					dataLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold'
						},
						formatter: function() {
							return Highcharts.numberFormat(this.y, 0);
						}
                        
					}                   
                },
               bar: {
                   dataLabels: {
                       enabled: false
                   }
               }
           },	
            series: 
            	 [${jsonAssetResult}]
        });

	}

	function initAreaChart() {
		chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart_2',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
				//显示的标题
                text: '<b></b>'
            },
            tooltip: {
			//鼠标放在上面显示的内容
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 2 //精度 小数点后面的0的个数
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true, //是否允许点开
                    cursor: 'pointer',
                    dataLabels: {
					//是否显示每个扇形的横向指示
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                        var result = this.percentage+"";
                        if(result.indexOf(".")!=-1){
                            result = result.substr(0,result.indexOf(".")+3);
                            }
                              return '<b>'+ this.point.name +'</b>: '+ result+' %';}  
                    },
                    showInLegend: true //显示底部的区域解释
                }
            },
			credits: {
						enabled: false //不要显示右下角的连接
					},
			exporting:{ 
                     enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
                },
            series: [{
                type: 'pie',
                name: '所占比例',
                data: 
                	[${eventResult1}]
            }]
        });

	}
	
	
	
	
	
	function initAreaChart6() {
		chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart_6',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
				//显示的标题
                text: '<b></b>'
            },
            tooltip: {
			//鼠标放在上面显示的内容
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 2 //精度 小数点后面的0的个数
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true, //是否允许点开
                    cursor: 'pointer',
                    dataLabels: {
					//是否显示每个扇形的横向指示
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                        var result = this.percentage+"";
                        if(result.indexOf(".")!=-1){
                            result = result.substr(0,result.indexOf(".")+3);
                            }
                              return '<b>'+ this.point.name +'</b>: '+ result+' %';}  
                    },
                    showInLegend: true //显示底部的区域解释
                }
            },
			credits: {
						enabled: false //不要显示右下角的连接
					},
			exporting:{ 
                     enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
                },
            series: [{
                type: 'pie',
                name: '所占比例',
                data: 
                	[${hourEventResut}]
            }]
        });

	}
	
	function initAreaChart7() {
		chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart_7',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
				//显示的标题
                text: '<b></b>'
            },
            tooltip: {
			//鼠标放在上面显示的内容
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 2 //精度 小数点后面的0的个数
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true, //是否允许点开
                    cursor: 'pointer',
                    dataLabels: {
					//是否显示每个扇形的横向指示
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                        var result = this.percentage+"";
                        if(result.indexOf(".")!=-1){
                            result = result.substr(0,result.indexOf(".")+3);
                            }
                              return '<b>'+ this.point.name +'</b>: '+ result+' %';}  
                    },
                    showInLegend: true //显示底部的区域解释
                }
            },
			credits: {
						enabled: false //不要显示右下角的连接
					},
			exporting:{ 
                     enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
                },
            series: [{
                type: 'pie',
                name: '所占比例',
                data: 
                	[${dayEventResut}]
            }]
        });

	}
	function initColumnChart8() {
		chart = new Highcharts.Chart({
		chart: {
			 renderTo: 'chart_8',//要显示在div的id
                   defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
           //zoomType: 'x'
           inverted: false                  //左右显示，默认上下正向
       },
       title: {                            
           text: '',        //图标的标题
           style:{}                        //标题样式
       },
     xAxis: {                            
           categories: [''] //X轴的坐标值
          
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
           y: 0,
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
                
                cursor: 'pointer',
				dataLabels: {
					enabled: true,
					style: {
						fontWeight: 'bold'
					},
					formatter: function() {
						return Highcharts.numberFormat(this.y, 0);
					}
                    
				}                   
            },
           bar: {
               dataLabels: {
                   enabled: false
               }
           }
       },	
        series: 
        	 [${hourAssetResult}]
    });

}
	function initColumnChart9() {
		chart = new Highcharts.Chart({
		chart: {
			 renderTo: 'chart_9',//要显示在div的id
                   defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
           //zoomType: 'x'
           inverted: false                  //左右显示，默认上下正向
       },
       title: {                            
           text: '',        //图标的标题
           style:{}                        //标题样式
       },
     xAxis: {                            
           categories: [''] //X轴的坐标值
          
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
           y: 0,
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
                
                cursor: 'pointer',
				dataLabels: {
					enabled: true,
					style: {
						fontWeight: 'bold'
					},
					formatter: function() {
						return Highcharts.numberFormat(this.y, 0);
					}
                    
				}                   
            },
           bar: {
               dataLabels: {
                   enabled: false
               }
           }
       },	
        series: 
        	 [${dayAssetResult}]
    });

}
	function initColumnChart10() {
		chart = new Highcharts.Chart({
		chart: {
			 renderTo: 'chart_10',//要显示在div的id
                   defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
           //zoomType: 'x'
           inverted: false                  //左右显示，默认上下正向
       },
       title: {                            
           text: '',        //图标的标题
           style:{}                        //标题样式
       },
     xAxis: {                            
           categories: [''] //X轴的坐标值
          
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
           y: 0,
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
                
                cursor: 'pointer',
				dataLabels: {
					enabled: true,
					style: {
						fontWeight: 'bold'
					},
					formatter: function() {
						return Highcharts.numberFormat(this.y, 0);
					}
                    
				}                   
            },
           bar: {
               dataLabels: {
                   enabled: false
               }
           }
       },	
        series: 
        	 [${aepetitionMessage}]
    });

}
	//点击高级弹出框框的开始
	$(document).ready(function() {

		// 高级-Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 500,
			height : 460,
			buttons : {
				"确定[Enter]" : function() {
					judegeDivShow();
					 
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}

			}
		});

	});

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}
	//点击高级弹出框框的开始结束  下面还有两个隐藏的div


	//动态加载采集器列表的方法 ajax 此处定义方法是为了定时器调用方便
	function loadCol() {

		//动态加载采集器开始
	
		$.post("/soc/home/queryCollectorList.action", {}, function(data) {
			var collectionList = data;
			$("#colBody").html("");
			$.each(data, function(i, collection) {
				//采集器名称
				var collectorBasic = collection.collectorBasic;
				//在线状态
				var collectorIsOnline;
				if (collection.collectorIsOnline == 1) {
					collectorIsOnline = "在线";
				} else {
					collectorIsOnline = "不在线";
				}
				//健康状态
				var collectorIsSafe;
				if (collection.collectorIsSafe == 1) {
					collectorIsSafe = "健康";
				} else {
					collectorIsSafe = "不健康";
				}
				//接收事件数
				var collectorReceiveEvents = collection.collectorReceiveEvents;

				var $str = '<tr ><td>' + collectorBasic + '</td></tr>';

				var $str1 = '<tr ><td valign="middle" class="td_list_data" >'
						+ collectorBasic
						+ '</td><td valign="middle" class="td_list_data" >'
						+ collectorIsOnline
						+ '</td><td valign="middle" class="td_list_data" >'
						+ collectorIsSafe
						+ '</td><td valign="middle" class="td_list_data" >'
						+ collectorReceiveEvents + '</td></tr>';

				$("#colBody").append($str1);

			});
		});
		//动态加载采集器结束
		//$("#d2").show();
	}

	//连接到APM查询页面
	function gotoInfo(assetMac,assetId,assetSupportDeviceId)
	{
	   window.open('/soc/monitorGroup/queryInfo.action?assetMac='+assetMac+'&assetId='+assetId+'&assetSupportDeviceId='+assetSupportDeviceId);
	}

	function getScrollTop() {  
        var scrollPos;  
        if (window.pageYOffset) {  
        scrollPos = window.pageYOffset; }  
        else if (document.compatMode && document.compatMode != 'BackCompat')  
        { scrollPos = document.documentElement.scrollTop; }  
        else if (document.body) { scrollPos = document.body.scrollTop; }   
        return scrollPos;   
}  
	
	var backgroundSetting = {"background-color":'#ccccff',"cursor": "pointer"} ; 
	function showPopBox(time,id,e){
		//alert($('#trAlarm_'+id).attr("class"));
		$("#trAlarm_"+id).css(backgroundSetting);
		var top , left ;
		if(e.pageY == undefined || e.pageX == undefined){
			//alert(document.body.scrollLeft+" "+document.body.scrollTop);
			 pageX = e.clientX+document.body.scrollLeft;
	         pageY = e.clientY+getScrollTop();
	         top = pageY + 5 ;
	         left = pageX + 14 ; 
		}else{
			top = e.pageY+5;
			left = e.pageX+14;
		}

	    $('#tip').html("告警时间：  " + time);
	    $('#tip').css({
		    'top' : top + 'px',
		    'left': left+ 'px'
		});
	}
	
	//首页展示图表SQL：最近24小时最新的10条阀值告警
	function initARMAlarm() {

		//动态加载采集器开始
		$("#TenAlarmMessage").html("");
		$.post("/soc/home/queryTenAlarmMessage.action", {}, function(data) {
			$.each(data, function(i, alarm) {
				
				var unit = ""
				if(alarm.alarmIndex == "上行流量" || alarm.alarmIndex == "下行流量"){
					unit = " MB/S" ; 
				}else{
					unit = " %" ;
				}
				var str ="<tr id='trAlarm_"+i+"' class='back' onclick='gotoInfo(\""+alarm.assetMac+"\","+alarm.assetId+","+alarm.assetSupportDeviceId+");' onmousemove=\"showPopBox('"+alarm.alarmTime+"',"+i+" ,event);\" onmouseout=\"\">"
				    + '<td valign="middle">'
					+ alarm.alarmIndex
					+ '</td><td valign="middle">'
					+ alarm.assetName
					+ '</td><td valign="middle">'
					+ alarm.assetMac
					+ '</td><td valign="middle">'
					+ alarm.alarmThreshold
					+ unit
					+ '</td><td valign="middle">'
					+ alarm.alarmCurrentValue 
					+ unit
					+'</td></tr>';
				
					$('#TenAlarmMessage').append(str);

			});
		});
	}
	
	
	//动态加载采集器列表的方法 ajax 此处定义方法是为了定时器调用方便

	function loadAlarm() {
		//动态加载近xxx警告开始
		$("#alertMessageId").html("");
		$.post("/soc/home/queryAlertMessageList.action", {}, function(data) {
			var alertMessageList = data;

			$.each(alertMessageList, function(i, alertMessage) {
				var $strRank = '<div  style = " margin-top:-2px;">';
				//等级
				var alertRank = alertMessage.alertRank;
				if (alertRank == 1) {
					$strRank = $strRank + '<span style = "color:#CCCCCC;">信息</span></div>';
				} else if (alertRank == 2) {
					$strRank = $strRank + '<span style = "color:#ffcc33;">轻微</span></div>';
				} else if (alertRank == 3) {
					$strRank = $strRank + '<span style = "color:#ffcc33;">一般</span></div>';
				} else if (alertRank == 4) {
					$strRank = $strRank + '<span style = "color:red;">重要</span></div>';
				} else if (alertRank == 5) {
					$strRank = $strRank + '<span style = "color:red;">严重</span></div>';

				}

				//时间
				var alertDate = DateFormat.format(new Date(
						alertMessage.eventSendTime),
						'yyyy-MM-dd hh:mm:ss');

				//内容
				var alertTitle = alertMessage.alertEventName;
				//方式
				//var sendMode = '待确认';

				//告警编号
				var alertIdentifaction = alertMessage.alertId;
				var $str1 = '<tr ><td valign="middle" class="td_list_data" >'
						+ alertIdentifaction
						+ '</td><td valign="middle" class="td_list_data" >'
						+ $strRank
						+ '</td><td valign="middle" class="td_list_data" >'
						+ alertDate
						+ '</td><td valign="middle" class="td_list_data" >'
						+ alertTitle + '</td></tr>';

				$("#alertMessageId").append($str1);

			});
		});
		//动态加载近xxx警告开始

		$("#d5").show();

	}
	function loadAlarm11() {

		//动态加载近xxx警告开始
		$("#alertMessageId11").html("");
		$.post("/soc/home/queryAlertMessageList.action?personType=1", {}, function(data) {
			var alertMessageList = data;
			$.each(alertMessageList, function(i, alertMessage) {
				var $strRank = '<div  style = " margin-top:-2px;">';
				//等级
				var alertRank = alertMessage.alertRank;
				if (alertRank == 1) {
					$strRank = $strRank + '<span style = "color:#CCCCCC;">信息</span></div>';
				} else if (alertRank == 2) {
					$strRank = $strRank + '<span style = "color:#ffcc33;">轻微</span></div>';
				} else if (alertRank == 3) {
					$strRank = $strRank + '<span style = "color:#ffcc33;">一般</span></div>';
				} else if (alertRank == 4) {
					$strRank = $strRank + '<span style = "color:red;">重要</span></div>';
				} else if (alertRank == 5) {
					$strRank = $strRank + '<span style = "color:red;">严重</span></div>';

				}

				//时间
				var alertDate = DateFormat.format(new Date(
						alertMessage.alertCreateDatetime),
						'yyyy-MM-dd hh:mm:ss');

				//内容
				var alertTitle = alertMessage.alertEventName;
				//方式
				//var sendMode = '待确认';
				
				//告警编号
				var alertIdentifaction = alertMessage.alertId;
				var $str1 = '<tr ><td  valign="middle" class="td_list_data" >'
						+ alertIdentifaction
						+ '</td><td valign="middle" class="td_list_data" >'
						+ $strRank
						+ '</td><td valign="middle" class="td_list_data" >'
						+ alertDate
						+ '</td><td valign="middle" class="td_list_data" >'
						+ alertTitle + '</td></tr>';
				$("#alertMessageId11").append($str1);

			});
		});
		//动态加载近xxx警告开始

		$("#d11").show();

	}
	
	//跳转10条告警页面
	function checkAlarmPage(){
		//alert(123) ; 
		window.open("/soc/home/queryAlertMessageList.action?personType=2&queryFlag='q'");
	}
	
	function loadAlarm12() {

		//动态加载近xxx警告开始
		$("#alertMessageId12").html("");
		$.post("/soc/home/queryAlertMessageList.action?personType=2", {}, function(data) {
			var alertMessageList = data;

			$.each(alertMessageList, function(i, alertMessage) {
				var $strRank = '<div  style = " margin-top:-2px;">';
				//等级
				var alertRank = alertMessage.alertRank;
				if (alertRank == 1) {
					$strRank = $strRank + '<span style = "color:#CCCCCC;">信息</span></div>';
				} else if (alertRank == 2) {
					$strRank = $strRank + '<span style = "color:#ffcc33;">轻微</span></div>';
				} else if (alertRank == 3) {
					$strRank = $strRank + '<span style = "color:#ffcc33;">一般</span></div>';
				} else if (alertRank == 4) {
					$strRank = $strRank + '<span style = "color:red;">重要</span></div>';
				} else if (alertRank == 5) {
					$strRank = $strRank + '<span style = "color:red;">严重</span></div>';

				}

				//时间
				var alertDate = DateFormat.format(new Date(
						alertMessage.alertCreateDatetime),
						'yyyy-MM-dd hh:mm:ss');

				//内容
				var alertTitle = alertMessage.alertEventName;
				//方式
				//var sendMode = '待确认';
				
				//告警编号
				var alertIdentifaction = alertMessage.alertId;
				var $str1 = "<tr class='bank' onclick='checkAlarmPage();' onmousemove=\"this.className='hand'\"><td valign='middle'>"
						+ alertIdentifaction
						+ '</td><td valign="middle">'
						+ $strRank
						+ '</td><td valign="middle">'
						+ alertDate
						+ '</td><td valign="middle">'
						+ alertTitle + '</td></tr>';

				$("#alertMessageId12").append($str1);

			});
		});
		//动态加载近xxx警告开始

		$("#d12").show();

	}
	
	function initchart_13(){
		var chart = new Highcharts.Chart({
	    //$('#highcharts').highcharts({
	        chart: {
	            renderTo:'chart_13',
	            type: 'areaspline',
	            marginRight: 10,
	            events: {
	                load: function() {
	
	                    // set up the updating of the chart each second
	                    var series = this.series[0];
	                    setInterval(function() {
	                        var x = (new Date()).getTime(), // current time
	                            y = Math.random();
	                        series.addPoint([x, y], true, true);
	                    }, 60*60*100000000000);
	                }
	            }
	        },
	        title: {
	            text: '近期事件趋势'
	        },
	        xAxis: {
	            type: 'datetime',
	            tickPixelInterval: 50
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            formatter: function() {
	                    return '<b>'+ this.series.name +'</b><br/>'+
	                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
	                    Highcharts.numberFormat(this.y, 0);
	            }
	        },
	        legend : {
				enabled : true,
				align: 'right',
	            verticalAlign: 'top'
			},
	        credits: {
	            enabled: false
            },
	        exporting: {
	            enabled: false
	        },
	        series: [{
	            name: '近期事件趋势',
	            data: [${trendAnalysis}]
	            //(function() {
	                // generate an array of random data
	            //   var data = [],
	            //        time = (new Date()).getTime(),
	            //        i;
	            //   for (i = -10; i <= 0; i++) {
	            //       data.push({
	            //            x: time + i * 1000*60*60,
	            //            y: Math.random(),
	            //        });
	            //    }
	            //    return data;
	            //})()
	        }]
	    });
	}
	function initEventsCountForLastWeek(){
		var chart = new Highcharts.Chart({
	    //$('#highcharts').highcharts({
	        chart: {
	            renderTo:'eventsCountForLastWeek',
	            type: 'areaspline',
	            margin:[30, 20, 50, 30]
	        },
	        title: {
	            text: '上周事件趋势'
	        },
	        xAxis: {
	        	 categories: [${chartsBuffx}],
                 labels: {
                     rotation: -60,
                     align: 'right',
                     style: {
                         fontSize: '10px',
                         fontFamily: ''
                     }
                 }
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }],
	            labels: {
	                
	                 style: {
	                     fontSize: '10px',
	                     fontFamily: ''
	                 }
	             }
	        },
	        tooltip: {
	            formatter: function() {
	                    return '<b>'+ this.series.name +'</b><br/>'+
	                    this.x+'<br/>'+
	                    Highcharts.numberFormat(this.y, 0);
	            }
	        },
	        legend : {
				enabled : true,
				align: 'right',
	            verticalAlign: 'top'
			},
	        credits: {
	            enabled: false
            },
	        exporting: {
	            enabled: false
	        },
	        series: [{
	            name: '上周事件趋势',
	            data: [${chartsBuff}]
	        }]
	    });
	}
	//当天事件类型分布
	function initPieChart() {
		chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart_14',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
				//显示的标题
                text: '<b></b>'
            },
            tooltip: {
			//鼠标放在上面显示的内容
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 2 //精度 小数点后面的0的个数
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true, //是否允许点开
                    cursor: 'pointer',
                    dataLabels: {
					//是否显示每个扇形的横向指示
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                        var result = this.percentage+"";
                        if(result.indexOf(".")!=-1){
                            result = result.substr(0,result.indexOf(".")+3);
                            }
                              return '<b>'+ this.point.name +'</b>: '+ result+' %';}  
                    },
                    showInLegend: true //显示底部的区域解释
                }
            },
			credits: {
						enabled: false //不要显示右下角的连接
					},
			exporting:{ 
                     enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
                },
            series: [{
                type: 'pie',
                name: '所占比例',
                data: 
                	[${eventsDate}]
            }]
        });

	}	
	
	
	function initIPBar(){
		chart = new Highcharts.Chart({
            chart: {
                type: 'bar',
                renderTo: 'chart_15',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: [''],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                formatter: function() {                 //当鼠标悬置数据点时的格式化提示
                    return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y, 0);
                }
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            series: 
                 [${ipData}]
        });
    };
		
		
	//定时加载采集器结束
	//把首页的div显示情况放在数据库里保存开始

	function saveDiv() {
		var homePageDiv = "";//保存几个模块的显示情况 1 显示 0 不显示 1,0,1,1,1....
		
		$("#dialog-extQuery input[type='checkbox']").each(function() {

			if ($(this).attr("checked") == 'checked') {
				homePageDiv = homePageDiv + "1" + ",";
			} else {
				homePageDiv = homePageDiv + "0" + ",";
			}
		});
		//选择显示都少列
		var layout = $("#selectLayout").val();
		$.post("/soc/home/saveHomePageDiv.action", {
			"homePageDiv" : homePageDiv,"layout":layout
		}, function() {
			window.location = "${ctx}/home/queryHome.action";
		});
	}
	//把首页的div显示情况放在数据库里保存结束
 function judegeDivShow1(num){
		var i = 0;
		var flg = true;
		$("#dialog-extQuery input[type='checkbox']").each(function() {
			if ($(this).attr("checked") == 'checked') {
				i = i + 1;
			}
		});
			
			if (i == 0) {
					alert("请至少选择一组数据");
						$(":checkbox:disabled");  
						$("#c" + num ).attr("checked", true);
						return;
					
				
			} 
			if(i>6){
				alert("最多选择6组数据");
					$("#c" + num ).attr("checked", false);
					$(":checkbox:disabled");  
					return;
				
			
			}
	}
	function judegeDivShow() {
		var i = 0;
		var flg = true;
		$("#dialog-extQuery input[type='checkbox']").each(function() {
			if ($(this).attr("checked") == 'checked') {
				i = i + 1;
			}
		});
			
			if (i == 0) {
				alert("请至少选择一组数据");
						$(":checkbox:disabled");  
						$("#c" + num ).attr("checked", true);
						return;
				$("#bujv").click();
				
			} 
			if(i>6){
				alert("最多选择6组数据");
					$(":checkbox:disabled");  
					$("#c" + num ).attr("checked", false);
					return;
			
			}
			else {
				saveDiv();
			}
	
	}
	//保存拖动后位置的方法
	function savePosition(){
		
		var arrPostion ="";
		var ids = "";
		 var indexs = $('#portletdemo').portlet('index');
			  $.each(indexs, function(k, v) {
				  arrPostion = arrPostion+v.x+","+v.y+"|";
				  ids = ids+k+"|";
	          });
			  
			  $.post("/soc/home/savePosition.action?homePageDivXY="+arrPostion+"&homePageDiv="+ids);
	}
	
	
	

	
</script>

<style type="text/css">
.biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
}

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

.scrollstyle {
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #cdcdcd;
	scrollbar-highlight-color: #cdcdcd;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #cdcdcd;
}

.white_content {
	display: none;
	position: absolute ;
	top: 25%;
	left: 25%;
	width: 50%;
	height: 50%;
	padding: 16px;
	border: 16px solid red;
	background-color: orange;
	z-index: 1002;
	overflow: auto;
}
</style>
</head>

<body >



	<div class="box">
		<div class="right">
			<input type="button" value="布局编辑"
				style="margin-right:12px;margin-top:-2px" class="btnstyle"
				onclick="extQueryDlg();" />
		</div>
	</div>
	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>

	<div id="tip" style="display:none">提示内容</div>

	<div id="dialog-extQuery" title="布局编辑">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td>选择布局:</td>
				<td><select id="selectLayout">
						<option value="1"
							<c:if test="${layout==1}">selected="selected"</c:if>>一列布局</option>
						<option value="2"
							<c:if test="${layout==2}">selected="selected"</c:if>>两列布局</option>
						<option value="3"
							<c:if test="${layout==3}">selected="selected"</c:if>>三列布局</option>
						<option value="4"
							<c:if test="${layout==4}">selected="selected"</c:if>>四列布局</option>
				</select></td>
			</tr>
			<tr>
				<td>请选择要显示的信息:</td>
				<td><input type="checkbox" name="1" id="c1"
					onclick="judegeDivShow1(1);" /> &nbsp;当天事件统计 <br /> <input
					type="checkbox" name="1" id="c2" onclick="judegeDivShow1(2);" />
					当前采集器统计<br /> <input type="checkbox" name="1" id="c3"
					onclick="judegeDivShow1(3);" /> 最近7天的告警类型分布<br /> <input
					type="checkbox" name="1" id="c4" onclick="judegeDivShow1(4);" />
					最近7天告警最多的10个资产<br /> <input type="checkbox" name="1" id="c5"
					onclick="judegeDivShow1(5);" /> 最近一小时最新的5条告警<br /> <input
					type="checkbox" name="1" id="c6" onclick="judegeDivShow1(6);" />
					最近一小时告警类型分布 <br /> <input type="checkbox" name="1" id="c7"
					onclick="judegeDivShow1(7);" /> 最近24小时告警类型分布<br /> <input
					type="checkbox" name="1" id="c8" onclick="judegeDivShow1(8);" />
					最近一小时告警数量最多的10个资产<br /> <input type="checkbox" name="1" id="c9"
					onclick="judegeDivShow1(9);" /> 最近24小时告警数量最多的10个资产<br /> <input
					type="checkbox" name="1" id="c10" onclick="judegeDivShow1(10);" />
					24小时重复最多的10个告警<br /> <input type="checkbox" name="1" id="c11"
					onclick="judegeDivShow1(11);" /> 最近7天最新的10条告警<br /> <input
					type="checkbox" name="1" id="c12" onclick="judegeDivShow1(12);" />
					最近24小时最新的10条告警<br /> <input type="checkbox" name="1" id="c13"
					onclick="judegeDivShow1(13);" /> 近期事件趋势<br /> <input
					type="checkbox" name="1" id="c14" onclick="judegeDivShow1(14);" />
					当天的事件类型分布<br /> <input type="checkbox" name="1" id="c15"
					onclick="judegeDivShow1(15);" /> 当天的事件按IP分布<br /> <input
					type="checkbox" name="1" id="c16" onclick="judegeDivShow1(16);" />
					最近24小时最新的10条阀值告警<br /> <input
					type="checkbox" name="1" id="c17" onclick="judegeDivShow1(17);" />
					上周事件趋势</td>
			</tr>
		</table>
	</div>
	<div id='portletdemo'></div>
	<!-- 设备名称选择的dialog -->
	<div id="roll" class="white_content" >
		<div class="cont">
			<div id="dataList">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					id="listTable">
					<thead>
					<tr> <th colspan="5" align="right" height="25"> <input type="button" value="关闭告警框" class="btnstyle" onclick="closeAll();"/>  </th> </tr>
						<tr height="28">
							<th width="8%" align="center">告警级别</th>
							<th width="8%" align="center">告警编号</th>
							<th width="10%" align="center">告警名称</th>
							<th width="10%" align="center">设备名称</th>
							<th width="8%" align="center">告警状态</th>

						</tr>
					</thead>

					<tbody id="closeAlertMessage">

					</tbody>

				</table>

			</div>
		</div>
	</div>
	<script type="text/javascript">
	getAlertTrance();
	function getAlertTrance() {
		$.ajax({
			type : "POST",
			url : "${ctx}/alertSetting/queryAlertTrance.action",
			data : "",
			dataType : 'text',
			success : function(msg) {
				var results = msg.split(",");
				if (results[0] == 'true') {
	isCloseAlertMessage();
				} else {
					
				}
			}
		});
	}
var checkIds;
function isCloseAlertMessage(){
	$("#closeAlertMessage").html("");
	 $.ajax({
      		type : "POST",
 			url : "${ctx}/alertMessage/queryNotCloseAlertMessage.action",
 			async : true,
 			dataType : "text",
 			success : function(eventsResult) {
 				if(eventsResult!="$$"){
 				$("#closeAlertMessage").append(eventsResult.split("$$")[0]);
 					//$("#deviceByNameDlg-open").dialog("open");
 					checkIds=eventsResult.split("$$")[1];
 				document.getElementById('roll').style.display='block';
 				}
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 				if (textStatus != "") {
 					alert("请求错误刷新后重试");
 				}
 			}
      	});
	
}
function closeAll(){
	 $.ajax({
	   		type : "POST",
				url : "${ctx}/alertMessage/closeAlaemMessage.action",
				async : true,
				dataType : "text",
				data : "checkids="+checkIds,
				success : function(eventsResult) {
					parent.topFrame.updateNum(0);
					 parent.stopBlinkNewMsg();
					document.getElementById('roll').style.display='none';
				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if (textStatus != "") {
						alert("请求错误刷新后重试");
					}
				}
	   	});
}
function isClose(va){
	var doc =document.getElementById("tr_"+va);
	var par = doc.parentNode;
	doc.parentNode.removeChild(doc);
	 $.ajax({
   		type : "POST",
			url : "${ctx}/alertMessage/closeAlaemMessage.action",
			async : true,
			dataType : "text",
			data : "alertID="+va,
			success : function(eventsResult) {
				parent.topFrame.updateNum(par.childNodes.length);
			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (textStatus != "") {
					alert("请求错误刷新后重试");
				}
			}
   	});
	 if(!par.childNodes.length>0){
		 parent.stopBlinkNewMsg();
		 document.getElementById('roll').style.display='none';
	 }
	//alert(par.childNodes.length);
	//var list=doc.parentNode.childNodes;
	//alert(list.length);
}

</script>
</body>
</html>