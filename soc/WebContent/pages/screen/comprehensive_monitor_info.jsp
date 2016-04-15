<%@ page contentType="text/html; charset=utf-8" import="java.util.*"
	import="com.scan.model.role.*" import="java.text.*"%>
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


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>安全管理平台综合监控</title>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/images/favicon.ico" rel="shortcut icon" />
<link href="${ctx}/images/favicon.ico" rel="Bookmark" />

<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">

<link href="${ctx}/styles/comprehensivemonitoring.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css" />

<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />

<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet"
	type="text/css" />


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

<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>

<script type='text/javascript' src="${ctx}/scripts/paging1.js"></script>
<!-- jsDate 格式化时间的函数 -->

<script type='text/javascript' src="${ctx}/scripts/jsDate.js"></script>
<script type="text/javascript" src="${ctx }/scripts/highcharts.js"></script>
<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->
<style type="text/css">
.biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
	font-size: 12px;
}

.hand {
	cursor: hand;
	background: #ccccff;
}

.eventslist {
	background: none repeat scroll 0 0 #D2E8FA;
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
}

.eventslist tr td {
	line-height: 28px;
	text-align: center;
}

.back {
	background: #FFFFFF;
	font: 12px;
	font-family: "宋体";
}

.conn {
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	position: absolute;
	z-index: -1;
	width: 99%;
}

.dialog-enentsContent {
	overflow: auto;
	font-size: 12px;
	color: #000000;
	margin: 0 auto;
	font-family: 宋体;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4;
}

.btnstyle {
	background: url(/soc/images/btnbg.jpg) no-repeat;
	border: none;
	width: 66px;
	height: 21px;
	cursor: pointer;
	color: #265D86;
	align: center;
}

.column {
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	border-left: 1px solid #D2E8FA;
	border-top: 1px solid #D2E8FA;
	border-right: 1px solid #D2E8FA;
}

.title {
	margin: 0px 0px 0px 0px;
	padding: 0px 0px 0px 0px;
	color: #000000;
	background: url(/soc/images/tdBg.jpg) repeat-x left;
	height: 31px;
}

.title_t {
	position: relative;
	left: 10px;
	top: 5px;
}

.img_a {
	vertical-align: -5px;
	cursor: hand;
}

.tit {
	margin: 0px 0px 0px 5px;
}

.rowLT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
	font: 12px;
	font-family: "宋体";
}

.rowLV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	border-right: 0px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 250px;
	font: 12px;
	font-family: "宋体"
}

.rowRT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
	font: 12px;
	font-family: "宋体";
}

.rowRV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	font: 12px;
	font-family: "宋体";
}

.screentable {
	background: none repeat scroll 0 0 #D2E8FA;
}

.screentable td {
	background: none repeat scroll 0 0 #FFFFFF;
	line-height: 28px;
	text-align: center;
}

.screentable .td_t {
	background: none repeat scroll 0 0 #ffffff;
	line-height: 28px;
	text-align: left;
	padding-left: 20px;
}

#screentable2 {
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}

.loding {
	font-size: 14px;
	border: 3px solid #D2E8FA;
	position: absolute;
	z-index: 10;
	background: #FFFFFF;
	width: 200px;
	height: 60px;
	padding-top: 25px;
	text-align: center;
	maring: 0px auto;
	display: none;
}

.hideDiv {
	left: 0px;
	top: 0px;
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	background-image: url("${ctx}/images/hide.png");
	background-position: 0% 0%;
	background-repeat: repeat;
	background-attachment: scroll;
	position: absolute;
	z-index: 1;
	filter: progid:DXImageTransform.Microsoft.Alpha(style=1, opacity=20,
		finishOpacity=20 );
}
</style>

<script type="text/javascript">
	var time = 2500;
	var i = "tab1";
	var interval;
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
	var background = {
		type : 'linearGradient',
		x0 : 0,
		y0 : 0,
		x1 : 0,
		y1 : 1,
		colorStops : [ {
			offset : 0,
			color : 'white'
		}, {
			offset : 1,
			color : '#d2e6c9'
		} ]
	};
	
    var data ;
    var jsonArrayCount = ${countEvents};
   <%-- var shuju =jsonArrayCount.split(":")
    alert(shuju);
    var biaozhi =0;
    for(i=0;i<shuju.length;i++){
    	
    	data.push(shuju[i]);;
    }--%>
   // alert(jsonArrayCount);
	//countdata =jsonArrayCount ;
	//alert(countdata);
	data =jsonArrayCount;
	//alert(data);
    var i;
   // for (i = 0; i < 3; i++) {
     //   yValue = 2;
      //  var temp = String(yValue); 
       // temp.substring(0,4);
       // data.push(["", temp]);
    //}
    
    
    
    /* var data0 = [];
    var i;
    for (i = 0; i < 10; i++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data0.push(["", temp]);
    }    
    var data1= [];
    var j;
     for (j = 0; j < 10; j++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data1.push(["", temp]);
    }    
    var data2= [];
    var k;
     for (k = 0; k < 10; k++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data2.push(["", temp]);
    }
     var data3= [];
    var l;
     for (l = 0; l < 10; l++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data3.push(["", temp]);
    }    
    var data4=[];
      var m;
     for (m = 0; m < 10; m++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data4.push(["", temp]);
    }             */

    
    
	$(document).ready(function() {
		
		$('#dialog-enentsContent').dialog({
			autoOpen : false,
			height : 650,
			width : 700,
			beforeclose: function(event,ui) {
		       $("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0);     
		       $("#hideDiv").css({
		            width: 0, 
		            height: 0
		        });
               $("#loding").hide();
               for(var i=1; i < 5; i++)
               {
                   $("#img_"+i).attr("src","${ctx}/images/u319_normal.gif");     
                   $("#column_"+i+" >ul").show();
               }
			}
		});

		$('#tabs-setting').tabs();

		//初始化jqchar数据
	/* 	var eventNums = '${eventNumJson}';
		eventNum = eval("(" + eventNums + ")");
		
		var assetNums = '${assetNumJson}';
	    assetsNum = eval("(" + assetNums + ")");
		 */
	    inittendencyjqChart();
		initeventChart();
		showeventList();
		initbarChart3();
		initcolumnChart4();	
		reFresh();
		showswitch();
		 //reFresh1();
	   /*  setTimeout("updateDynamicEvents()",1000*600); */

	});
    var isStop;
/*function reFresh1() {	
		
		if (m1 >= 1) {
			isStop = setTimeout("reFresh1()", 1000);
			m1--;
		} else {
			m1=5;
			
				showswitch();
		
		}
}*/

		 var chart;

		 var m = 60; //定时更新记时器
	var m1 = 5; //定时更新记时器
	var refresh; //字时更新对象
  //定时更新数据
	function reFresh() {		
			if (m >= 1) {
				refresh = setTimeout("reFresh()", 1000);
				m--;
			} else {
				// parent.mainFrame.location.href = '${ctx}/events/queryEvents.action?queryEventsType=0';
				location.href = '${ctx}/indexscreen/queryAll.action';
			}
		

	}

	
	//加载数据
	//加载页面底部的事件列表
	//选项卡
	function dblclick(identifaction) {
		var docHe =  ($(document).height()/2)-60;
		var docWi =  ($  (document).width()/2)-200;
		$("#hideDiv").addClass("hideDiv");
		$("#hideDiv").css({
		    width: $(document).width(), 
		    height: $(document).height()
		});
		$("#loding").toggle("fast");
		$("#loding").css({top:docHe,left:docWi}); 
		
		var falg = false;
		$.ajax({
			type : "POST",
			url : "${ctx}/events/eventsDetails.action?",
			async : true,
			dataType : "json",
			data : "eventsLogIdentification=" + identifaction + "&tableName=0",
			success : function(eventsResult) {
				var obj = eventsResult;
				if (obj != null) {
					   $("#occurTime").text(obj.receptTimes);
                       $("#sendTime").text(obj.sendTimes);
                       $("#priority").text(obj.priority);
                       $("#type").text(obj.typeCustomd);
                       $("#count").text(obj.aggregatedCount);
                       $("#name").text(obj.nameCustomd);
                       $("#customs4").text(obj.customs4);
                       $("#msg").text(obj.msg);
                       $("#sAdd").text(obj.sAdd);
                       $("#sPort").text(obj.sPort);
                       $("#sUser").text(obj.suserName);
                       $("#tAdd").text(obj.tAdd);
                       $("#digest").text(obj.nameCustomd);
                       $("#tPort").text(obj.dPort);
                       $("#tUser").text(obj.dUserName);
                       $("#category").text(obj.cateGoryCustomd);
                       $("#devName").text(obj.customs5);
                       $("#devCategory").text(obj.devCategory);
                       $("#action").text(obj.action);
                       $("#result").text(obj.result);
                       $("#dUserName").text(obj.dUserName);
                       $("#devAddr").text(obj.devAddT);
                       $("#devVendor").text(obj.devVendor);
                       $("#devType").text(obj.devproduct);
                       $("sensorName").text(obj.sensorName);
				}
				$("#loding").toggle("slow");
				$('#dialog-enentsContent').dialog('open');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (textStatus != "") {
					alert("请求错误刷新后重试");
					 $("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
					 $("#hideDiv").css({
				            width: 0, 
				            height: 0
				        });
					$("#loding").hide();
				}
			}
		});
		
		return falg;
	}
		
	function removeAllSpace(str) { 
		return str.replace(/\s+/g, "");
			
	}

    function inittendencyjqChart()
	{
    	chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart_0', //图表放置的容器，DIV
                defaultSeriesType: 'spline', //图表类型为曲线图
               
                margin:[15,20,25,30]
               
               
            },
            title: {
                text: '' //图表标题
                	
            },
            subtitle: {
                text: ''
               
            },
            xAxis: { //设置X轴
               
                tickPixelInterval: 200 , //X轴标签间隔
                categories:${chartsX}
            },
            yAxis: { //设置Y轴
            	 title: {
                     text: ''
                 },
              
                 plotLines: [{
                     value: 0,
                     width: 1,
                     color: '#808080'
                 }]
            },
    		tooltip: {//当鼠标悬置数据点时的提示框
    			formatter: function() { //格式化提示信息
    				return '<b>全局风险:</b> '+Highcharts.numberFormat(this.y, 4);
    			}
    		},
            legend: {
                enabled: false  //设置图例不可见
            },credits: {
    			enabled: false
    		},
    		exporting:{ 
               enabled:false//用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
     		},
     		 plotOptions:{//绘图线条控制  
                spline:{  
                    allowPointSelect :true,//是否允许选中点  
                    animation:true,//是否在显示图表的时候使用动画  
                    cursor:'pointer',//鼠标移到图表上时鼠标的样式  
                    dataLabels:{  
                        enabled :true,//是否在点的旁边显示数据  
                        rotation:0  
                    },  
                    enableMouseTracking:true,//鼠标移到图表上时是否显示提示框    
                    marker:{  
                       enabled:true,//是否显示点  
                       radius:3,//点的半径  
                       states:{  
                            hover:{  
                                enabled:true//鼠标放上去点是否放大  
                                  },  
                            select:{  
                                enabled:false//控制鼠标选中点时候的状态  
                            }  
                        }  
                    },  
                    states:{  
                        hover:{  
                            enabled:true,//鼠标放上去线的状态控制  
                            lineWidth:3  
                        }  
                    },  
                    visible:true,  
                    lineWidth:1//线条粗细  
                }  
           } ,
            series: [{
                data: data
            }]
        });
          
	}
    $(function(){
    	
    	//setTimeout("updateChart()", 120000);
		
	});
	
     function updateChart() {            
    	
    	$.ajax({
			type : "POST",
			url : "${ctx}/indexscreen/upCountChart.action",
			dataType : 'java',
			success : function(msg) {
			//alert(msg);
			var tempMemory = eval("(" + msg + ")");
			updateCount(tempMemory[0]);
			
			}
	});
    	
      //  temp.substring(0,4);
    
       
    }
    function updateCount(datas) {
    	
    	 if(data.length>11){
        	  data.splice(0, 1);
          }
          
		data.push(datas);
		
		$('#chart_0').jqChart('update');
		
		setTimeout("updateChart()", 120000);
	}

    function updateDynamicEvents()
    {    
        showeventList();
        setTimeout("updateDynamicEvents()",1000*600);
    }
    
    
    //待修改
	function initeventChart() {
		chart = new Highcharts.Chart({
            chart: {
         		renderTo: 'chart_1',//要显示在div的id 
                       defaultSeriesType: 'bar',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
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
           		bar: {
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
                	   cursor: 'pointer',
                       events: {
                			   click: function(e) {
                				 var categoryName = e.point.series.name ;
                				 $.post("${ctx}/events/queryKeyByCategoryName.action",{"categoryName":categoryName},function(obj){
                                     window.open("${ctx}/events/editCustom.action?timeRange=12&eventsType="+obj+"|"+"&actionType=1&beginTime=&endTime=&sourcePort=&targetPort=&identification=10","_blank","width=1100,height=550,location=no,toolbar=no");
                				 });
                			   }
                		   }
                	  
               }
           //,bar: {
           //        dataLabels: {
           //            enabled: false
           //        }
           //    }
           },
            series: 
                 ${eventResult}
   
	});
}

	function initbarChart3() {
		  chart = new Highcharts.Chart({
            chart: {
                renderTo: 'chart_3',//要显示在div的id
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
            	percentageDecimals: 1 //精度 小数点后面的0的个数
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
                        result = result.substr(0,result.indexOf(".")+2);
                        }
                          return '<b>'+ this.point.name +'</b>: '+ result+' %';
                        }
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
                	${assetsObjResult1}
                
            }]
        });
	}
	
	function initcolumnChart4() {
	  	var	xs = [''];
		chart = new Highcharts.Chart({
			chart: {
				 renderTo: 'chart_4',//要显示在div的id
              defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '',        //图标的标题
               style:{}                        //标题样式
           },
         xAxis: {                            
               categories: xs //X轴的坐标值
              
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
                   return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y,0);
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
             series:${assetsObjResult}
       });

    

	}
	function showeventList() {
		var keyword = "";
		getEventListJson("${ctx}/events/queryEventScreen.action?startIndex="
				+ encodeURI(0, "utf-8") + "&keyword="
				+ encodeURI(encodeURI(keyword, "utf-8"))
				+ "&queryEventsType=0&t=" + new Date(), 0);
	}

	function getEventListJson(url, num) {
		var htmlStr="";
		$
				.getJSON(
						url,
						function(result) {
                           
							$("#bottomtable tr:not(:first)").remove();
							 $("#bottomtable tr:first").remove();
							$("#test12").remove();
							$
									.each(
											result,
											function(i, item) {

												//var rowNum = $("#bottomtable tr").length - 1;

												if (item.eventsId
														&& item.eventsId != 'undefined') {
													htmlStr += "<tr class='back' title='双击放大' onmouseover='show(this);' onmouseout='hide(this);' ondblclick='dblclick("
															+ item.identification
															+ ");'>";
													htmlStr += "<td valign='middle' align='center' width='10%'>"
															+ item.priority;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='20%'>"
															+ item.name;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='15%'>"
															+ item.customs5;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='15%'>"
															+ item.devAddT;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='10%'>"
															+ item.aggregatedCount;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='*'>"
															+ item.receptTimes;
													htmlStr += "</td>";
													/*htmlStr += "<td valign='middle' align='center' width='10%'>待确认";
													htmlStr += "</td>"; */
													htmlStr += "</tr>";
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
															"${ctx}/events/queryEventScreen.action",
															item,
															"dlg-keyword-resource-id",
															"getEventListJson",
															1);
												}
											});

							$("#dynamicEcents").html(htmlStr);

						});
						//alert(htmlStr);
						

	}
	//点击高级弹出框框的开始
	/* $(document).ready(function() {

		// 高级-Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 230,
			buttons : {
				"确定[Enter]" : function() {
					extQuery();
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
	} */
	//点击高级弹出框框的开始结束  下面还有两个隐藏的div
	/* function extQuery() {
		clearInterval(interval);
		time = $('#frequencynum').val();
		testOnclick();
	} */
	function show(obj) {
		$(obj).removeClass("back");
		$(obj).addClass("hand");
	}

	function hide(obj) {
		$(obj).removeClass("hand");
		$(obj).addClass("back");
	}

	function action(id) {
		changeIcon($("#img_" + id));
		$("#column_" + id + " >ul").toggle("slow");

	}

	function changeIcon(nainNode) {
		if (nainNode) {
			if (nainNode.attr("src").indexOf("u321_normal.gif") >= 0) {
				nainNode.attr("src", "${ctx}/images/u319_normal.gif");

			} else {
				nainNode.attr("src", "${ctx}/images/u321_normal.gif");
			}
		}
	}
	//关闭事件详情
	function closeContent() {
		$('#dialog-enentsContent').dialog("close");
		if (sourceType == 0) {
			setTimeout("reFresh(" + sourceType + ")", 6 * 60 * 1000);
		}
	}

	function geteventstatistics() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "${ctx}/indexscreen/queryEventStatistics.action",
			success : function(data) {
				initeventChart(data);
			}
		});

	}

	//打开事件统计定时器
	function openeventtimer() {
		switchflag = 2;
		loadAlarm();
	//	setInterval("geteventstatistics()", 1 * 60 * 100);
	}
	
	//动态加载采集器列表的方法 ajax 此处定义方法是为了定时器调用方便

	function loadAlarm() {
		//动态加载近xxx警告开始
		$("#alertMessageId").html("");
		$.post("${ctx}/home/queryAlertMessageList.action", {}, function(data) {
			var alertMessageList = data;
			$.each(data, function(i, alertMessage) {
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
				} else {
					$strRank = $strRank + '<span style = "color:red;">严重</span></div>';

				}

				//时间
				var alertDate = DateFormat.format(new Date(alertMessage.alertCreateDatetime),'yyyy-MM-dd hh:mm:ss');
				//内容
				var alertTitle = alertMessage.alertEventName;
				//方式
				var sendMode = '待确认';
				var $str1 = '<tr ><td valign="middle" class="td_list_data" >'
						+ $strRank
						+ '</td><td valign="middle" class="td_list_data" >'
						+ alertDate
						+ '</td><td valign="middle" class="td_list_data" >'
						+ alertTitle
						+ '</td><td valign="middle" class="td_list_data" >'
						+ sendMode + '</td></tr>';
				$("#alertMessageId").append($str1);

			});
		});
		//动态加载近xxx警告开始
		$("#d5").show();

	}
	
	function openassets() {
		switchflag = 3;
		$.ajax({
			type : "post",
			dataType : "json",
			url : "${ctx}/indexscreen/queryAssets.action",
			success : function(data) {
				initcolumnChart4(data);
			}
		});
		
	}
	
	var timeChange;
	var switchflag = 1;
	
	function showswitch()
	{
		
	//$("#123").trigger("click");
	  if(switchflag==4)
	  {
	     switchflag = 1;
	  }
	   $("#switch"+switchflag).trigger("click");
	   
	   switchflag=switchflag+1;
	   
	   timeChange=setTimeout("showswitch()",10000);
	   //reFresh1();
	}
	
	
	//定时加载采集器开始
    $(function() {
		var handler = function() {
			//loadAlarm();
			//geteventstatistics();
			//openassets();
			//showeventList();
			//alert("123");
			//$("#123").trigger("click");
			location.reload();
			
			
		};
		var timer = setInterval(handler, 60*10 * 1000);

    });	
    function flush_network(){
    	switchflag = 1;
    }
    function stopchange(){
    	var check = $("#stop").attr("checked");
    	//alert(check);
    	if(check=='checked'){
    	///	alert(check);
    		clearTimeout(timeChange);
    	}else{
    		showswitch();
    	}
    }
	
</script>
</head>
<body style="margin-top:2px;margin-left: 2px">


	<div style="width: 100%; border:1px solid #dcdcdc;">
		<!--标题 -->
		<div id="safetytitle">
			<font color="#000000"
				style="line-height:50px; margin-left:30px;font-size:20px"><strong>安全管理平台综合监控</strong>
			</font>
		</div>

		<!--tab 页切换-->
		<div id="tabs-setting"
			style="width: 100%;border: none; margin: 2px auto">
			<ul style="background: #EDEEF3">
				<li><a href="#wholerisk" id="switch1" style="cursor: pointer;"
					onclick="flush_network();">全局风险</a>
				</li>
				<li><a href="#eventstatistics" id="switch2" style="cursor: pointer;"
					onclick="openeventtimer();">事件统计</a>
				</li>
				<li><a href="#propertyspread" id="switch3" style="cussor: pointer"
					onclick="openassets();">资产分布</a>
				</li>
				<li>
					&nbsp; &nbsp;<input type="checkbox" id="stop" style="cussor:pointer" onclick="stopchange();" value=""/>&nbsp; &nbsp;<font color="red" style="font-size: 16px;position: absolute;top: 7%">停止播放</font>
				</li>

			</ul>

			<!-- 第一个tab页：全局风险 -->
			<div id="wholerisk">

				<table width="98%" height="450px" border="0" cellspacing="1"
					cellpadding="0" style="border: 1px solid #D2E8FA">
					<tr>
						<td>
							<table width="100%" cellSpacing="1" cellpadding="6" border="0">
								<tr>
									<td class="biaoti" width="70%">全局域风险</td>
									<%-- <td class="biaoti" width="50%">子域风险</td>--%>
								</tr>
								<tr>
									<td>
										<div id="chart_0"
											style="width:100%; height:420px;display:block"></div>
									</td>
									<%--<td>
										<div id="chart_2"
											style="width:100%;height:420px;display:block;"></div></td>--%>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

			<!-- 第二个tab页：事件统计 -->
			<div id="eventstatistics">
				<div style="width:100%;  border: 1px solid #D2E8FA ">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td>
								<table width="100%" cellSpacing="1" cellpadding="6">
									<tr>
										<td class="biaoti" width="46%">当天事件按照事件类别统计 Top 5</td>
										<td class="biaoti" width="54%">最近7天最新的10条告警</td>
									</tr>
									<tr>
										<td>
											<div id="chart_1"
												style="width:50%;height:420px;display:block; "></div></td>
										<td>
											<div id="d5" class="screentable3"
												style="overflow:auto;width:100%;height:420px;float:left">
												<table style="width:100%;">
													<tr>
														<td style="width:100%;" valign="top">


															<div class="sbox">
																<div class="cont">
																	<!-- information area -->
																	<div id="dataList">
																		<table width="100%" border="0" cellspacing="1"
																			cellpadding="0" class="tab2" style="font-size: 12px">
																			<tr height="28">
																				<td width="15%" class="biaoti">等级</td>
																				<td width="37%" class="biaoti">发生时间</td>
																				<td width="38%" class="biaoti">事件名称</td>
																				<td width="" class="biaoti">确认方式</td>
																			</tr>
																			<tbody id="alertMessageId"></tbody>


																		</table>
																	</div>
																</div>
															</div>
														</td>
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

			<!-- 第三个tab页：资产分布 -->
			<div id="propertyspread">

				<!--  <div style="width:100%; height:280px; border: 1px solid #D2E8FA ">-->
				<!-- 页面太大注释掉 -->

				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td>
							<table width="100%" cellSpacing="1" cellpadding="6">
								<tr>
									<td class="biaoti" width="46%">资产分布</td>
									<td class="biaoti" width="54%">资产分布</td>
								</tr>
								<tr>
									<td>
										<div id="chart_3" style="width:50%;height:420px;display:block"></div>
									</td>
									<td>
										<div id="chart_4" style="width:45%;height:420px;display:block"></div>

									</td>

								</tr>
							</table></td>
					</tr>
				</table>

			</div>
			<!--  </div>-->
			<!-- 页面太大注释掉 -->
		</div>


		<div id="dialog-enentsContent" title="详细信息"
			class="dialog-enentsContent" style="height: 200px">
			<input type="button" value="返回列表" class="btnstyle"
				style="margin-right:5px;margin-top:-2px" onclick="closeContent();" />
			<div style="margin:5px 0px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_1" class="img_a"
						onclick="action(1)">&nbsp;<span class="title_t_t">设备信息</span>
					</span>
				</div>
				<div id="column_1">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">设备名称</td>
									<td class="rowLV" id="devName">&nbsp;</td>
									<td class="rowRT">ip地址</td>
									<td class="rowLV" id="devAddr">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">设备厂商</td>
									<td class="rowLV" id="devVendor">&nbsp;</td>
									<td class="rowRT">设备型号</td>
									<td class="rowLV" id="devType">&nbsp;</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
			</div>

			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_2" class="img_a"
						onclick="action(2)">&nbsp;<span class="title_t_t">时间信息</span>
					</span>
				</div>
				<div id="column_2">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">产生时间</td>
									<td class="rowLV" id="sendTime">&nbsp;</td>
									<td class="rowRT">接收时间</td>
									<td class="rowLV" id="occurTime">&nbsp;</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
			</div>
			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_3" class="img_a"
						onclick="action(3)">&nbsp;<span class="title_t_t">基本信息</span>
					</span>
				</div>
				<div id="column_3">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">等&nbsp;&nbsp;级</td>
									<td class="rowLV" id="priority">&nbsp;</td>
									<td class="rowRT">数量</td>
									<td class="rowLV" id="count">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件名称</td>
									<td colspan="3" class="rowRV" id="name">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件类别</td>
									<td colspan="3" class="rowRV" id="category">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件类型</td>
									<td colspan="3" class="rowRV" id="type">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件描述</td>
									<td colspan="3" class="rowRV" id="customs4">&nbsp;</td>
								</tr>
								<!-- 	<tr>
									<td class="rowLT">摘要</td>
									<td colspan="3" class="rowRV" id="digest">&nbsp;</td>
								</tr> -->
							</table>
						</li>
					</ul>
				</div>
			</div>

			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_4" class="img_a"
						onclick="action(4)">&nbsp;<span class="title_t_t">来源目标信息</span>
					</span>
				</div>
				<div id="column_4">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">源地址</td>
									<td class="rowLV" id="sAdd">&nbsp;</td>
									<td class="rowRT">源端口</td>
									<td class="rowLV" id="sPort">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">目标地址</td>
									<td class="rowLV" id="tAdd">&nbsp;</td>
									<td class="rowRT">目标端口</td>
									<td class="rowLV" id="tPort">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件来源用户名</td>
									<td colspan="3" class="rowRV" id="sUser"></td>
								</tr>
							</table>
						</li>
					</ul>
				</div>

			</div>
			<div style="height: 10px;"></div>

			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_6" class="img_a"
						onclick="action(6)">&nbsp;<span class="title_t_t">事件分类信息</span>
					</span>
				</div>

				<div id="column_6">
					<ul class="display">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<!-- <tr>
								<td class="rowLT">事件对象分类</td>
								<td class="rowLV" id="category"></td>
								<td class="rowRT">事件设备分类</td>
								<td class="rowRV" id="devCategory"></td>
							</tr> -->
								<tr>
									<td class="rowLT">事件动作</td>
									<td class="rowLV" id="action"></td>
									<td class="rowRT">动作结果</td>
									<td class="rowRV" id="result"></td>
								</tr>
								<!-- <tr>
								<td class="rowLT">事件采集技术</td>
								<td colspan="3" class="rowRV" id="sensorName"></td>
							</tr>
							<tr>
								<td class="rowLT">事件特征</td>
								<td colspan="3" class="rowRV"></td>
							</tr> -->
							</table></li>
					</ul>
				</div>
			</div>


		</div>

		<!-- 底部不变区域 -->
		<div id="bottom_date">
			<div
				style="margin-left: 1%; width: 99.4%; margin-right: 0.5%;border: 1px solid #D2E8FA">
				<table width="100%" align="center" id="footer">
					<tr>
						<td colspan="8">
							<table width="99%" border="0" cellspacing="1" cellpadding="0">
								<tr height="28" id="collectionTR">
									<td width="10%" class="biaoti">事件级别</td>
									<td width="20%" class="biaoti">事件名称</td>
									<td width="15%" class="biaoti">设备名称</td>
									<td width="15%" class="biaoti">设备IP</td>
									<td width="10%" class="biaoti">数量</td>
									<td width="*" class="biaoti">发生时间</td>
									<!-- <td width="10%" class="biaoti">处理情况</td> -->
								</tr>

							</table>
						</td>
					</tr>
					<tr>
						<td align="center" width="100%">
							<div style="overflow-y:scroll; width:100%; height: 320px"
								id="screentable2">
								<table width="100%" class="eventslist" cellspacing="1"
									cellpadding="0" id="bottomtable" style="font-size: 13px">
									<tbody id="dynamicEcents">

									</tbody>
									<!-- <tr>
									<td width="10%">事件空</td>
									<td width="20%">事件空</td>
									<td width="15%">事件空</td>
									<td width="15%">事件空</td>
									<td width="10%">事件空</td>
									<td width="20%">事件空</td>
									<td width="10%">事件空</td>
								</tr> -->


								</table>

							</div>
						</td>

					</tr>

				</table>

			</div>
		</div>

		<div id="loding" class="loding">
			<font color='#69C3FF'>数据加载中...</font>
		</div>
		<div id="hideDiv"></div>
		<!-- 事件详情弹出框 -->
</body>
</html>
