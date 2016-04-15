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
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jsDate.js"></script>
<script type="text/javascript" src="${ctx}/scripts/highcharts.js"></script>
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
legend{
font-weight:bold;
}
</style>
<script type="text/javascript" >
/* function testOnline(){
	if(${montt.tomIsOnline}!=1){
		reFresh();
		$("#useable").html("b");
		$("#memoryUse").html("b");
		$("#sessionUse").html("b");
		$("#currentThread").html("b");
		$("#busyThread").html("b");
	}else{
		creaChart();
	}
} */

function creaChart(){
	reFresh();
	creaChart1();
	creaChart2();
	creaChart3();
	creaChart4();
	creaChart5(); 	
 }
 function creaChart1(){
	 var chart = new Highcharts.Chart({
		    //$('#highcharts').highcharts({
		        chart: {
		            renderTo:'useable',
		            //backgroundColor: '#CFCFCF',
		             //borderColor:'red',  
		            type: 'column'
		            //marginRight: 10,
		        },
		        title: {
		            text: '',
		            style:{
		            	
		            }
		            	
		        },
		        xAxis: {
		        	categories: [${tomDetail.projectName}]
		        },
		        yAxis: {
		        	labels:{
		        		formatter:function(){
		        		      if(this.value <=0) { 
		        		        return "不可用";
		        		      }else { 
		        		        return "可用";
		        		      }
		        		    }
		        	},
		            title:'',
		            allowDecimals:false,//是否允许刻度有小数
		            max:1,
		            min:0
		        },
		        legend: {
		        	enabled:false
	            },
		        tooltip: {
		        	formatter: function() {                 //当鼠标悬置数据点时的格式化提示
		        		if(this.y <=0) { 
	        		        return "不可用";
	        		      }else { 
	        		        return "可用";
	        		      }
		               }
		        },
		        credits: {
		            enabled: false
	            },
		        exporting: {
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
		        series: [{
		            name: '1-可用性,0-不可用',
		            data:[${tomDetail.useAble} ]
		        }]
		    });
 }
	 
 function creaChart2(){
	 var chart = new Highcharts.Chart({  
         chart: {  
             renderTo: 'memoryUse',  
             plotBackgroundColor: null,  
             plotBorderWidth: null,  
             plotShadow: false  
         },  
         title: {  
             text: ''  
         },  
         credits: {  
             enabled: false  
         },  
         exporting: {  
             enabled: false  
         },  
         tooltip: {  
             formatter: function () {  
                 return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';  
             }  
         },  
         plotOptions: {  
             pie: {  
                 allowPointSelect: true,  
                 cursor: 'pointer',  
                 showInLegend: false,  
                 dataLabels: {  
                     enabled: true,  
                     
                     formatter: function () {  
                         return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';  
                     }  
                 }  
             }  
         },  
         series: [{  
             type: 'pie',  
             name: '堆使用率',  
             data: [${tomDetail.usedMemory},${tomDetail.unUsedMemory}] //这个序列之，从后台数据库读取并动态拼凑该JSON序列串  
         }]   
 });}

   function creaChart3(){
		 var chart = new Highcharts.Chart({
			        chart: {
			            renderTo:'sessionUse',
			            //backgroundColor: '#CFCFCF',
			             //borderColor:'red',  
			            type: 'column'
			            //marginRight: 10,
			        },
			        title: {
			            text: '',
			            style:{	
			            } 	
			        },
			        xAxis: {
			        	categories: [${tomDetail.projectName}]  
			        },
			        yAxis: {
			            title:'',
			            allowDecimals:false, //是否允许刻度有小数
			            min:0
			        },
			        legend: {
			        	enabled:false
		            },
			        tooltip: {
			        	formatter: function() {                 //当鼠标悬置数据点时的格式化提示
			                   return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y, 0);
			               }
			        },
			        credits: {
			            enabled: false
		            },
			        exporting: {
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
			        series: [{
			            name: '会话数',
			            data:[${tomDetail.sessionUse} ]
			        }]
			    });
	 }
 function creaChart4(){
	 var chart = new Highcharts.Chart({
		        chart: {
		            renderTo:'currentThread',
		            //backgroundColor: '#CFCFCF',
		             //borderColor:'red',  
		            type: 'column'
		           // marginRight: 10,
		        },
		        title: {
		            text: '',
		            style:{
		            	
		            }
		            	
		        },
		        xAxis: {
		        	categories: [${tomDetail.portName}]
		            
		        },
		        yAxis: {
		            title:'',
		            allowDecimals:false, //是否允许刻度有小数
		            min:0
		        },
		        legend: {
		        	enabled:false
	            },
		        tooltip: {
		        	formatter: function() {                 //当鼠标悬置数据点时的格式化提示
		                   return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y, 0);
		               }
		        },
		        credits: {
		            enabled: false
	            },
		        exporting: {
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
		        series: [{
		            name: '当前线程数',
		            data:[${tomDetail.currentThread}] 
		        }]
		    });
 } 
function creaChart5(){
	 var chart = new Highcharts.Chart({
		        chart: {
		            renderTo:'busyThread',
		            //backgroundColor: '#CFCFCF',
		             //borderColor:'red',  
		            type: 'column'
		           // marginRight: 10,
		        },
		        title: {
		            text: '',
		            style:{
		            	
		            }
		            	
		        },
		        xAxis: {
		        	categories: [${tomDetail.portName}]
		            
		        },
		        yAxis: {
		            title:'',
		            allowDecimals:false, //是否允许刻度有小数
		            min:0
		        },
		        legend: {
		        	enabled:false
	            },
		        tooltip: {
		        	formatter: function() {                 //当鼠标悬置数据点时的格式化提示
		                   return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y, 0);
		               }
		        },
		        credits: {
		            enabled: false
	            },
		        exporting: {
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
		        series: [{
		            name: '当前活跃线程数',
		            data:[${tomDetail.busyThread} ]
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
				parent.mainFrame.location.href = "${ctx}/monitorTOM/detailTOM.action?tomId="+${montt.tomId};
			}
		

	}  
</script>
</head>
<body style="margin-top:2px;margin-left: 2px" onLoad="creaChart()" >
	<div id="tabs-setting"
		style="width: 100%;border: none; margin: 2px auto">
		<ul style="background:url('/soc/images/rightDh.jpg') repeat-x; ">

<li>
<span id="mes" style="margin-left:400px" width="150px"></span><span style="margin-left:500px">
	<input type="button" value="刷新" class="btnstyle"
								 onclick="location.href='${ctx}/monitorTOM/detailTOM.action?tomId=${montt.tomId}';"/>
<input type="button" value="返回" class="btnstyle"
							style="margin-top:2px" onclick="location.href='${ctx}/monitorTOM/queryAll.action';"/></span>
</li>
		</ul>


		<div id="wholerisk" style="overflow:auto">

			
						<table width="100%" cellSpacing="1" cellpadding="6" border="0">
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>基本信息</legend>
											<table width="100%" style="height:200px">
												<tr>
													<td>监控对象名称</td>
													<td>${montt.tomName}</td>
												</tr>
												<tr>
													<td>监控对象IP地址</td>
													<td>${montt.tomIp}</td>
												</tr>
												<tr>
													<td>服务器启动时间</td>
													<td>${tomDetail.startTime }</td>
												</tr>
												<tr>
													<td>连续工作时间</td>
													<td>${tomDetail.timeSpan }</td>
												</tr>
												<tr>
													<td>最大堆内存(M)</td>
													<td>${tomDetail.maxMemory}</td>
												</tr>
												 <!-- <tr>
													<td>操作系统</td>
													<td></td>
												</tr>  -->
											</table>
										</fieldset>
									</div></td>
									<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend><b>可用性</b></legend>
											<div id="useable" style="height:200px;overflow:auto;">
											</div>
										</fieldset>
									</div></td>
								

							</tr>
							<tr>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend><b>内存使用情况</b></legend>
											<div id="memoryUse" style="height:200px;  overflow:auto">
												
											</div>
										</fieldset>
									</div></td>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend><b>应用活动会话数</b></legend>
											<div id="sessionUse" style="height:200px"></div>
										</fieldset>
									</div></td>
							</tr>
							<tr>
							<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend><b>当前线程数</b></legend>
											<div id="currentThread" style="height:200px"></div>
										</fieldset>
									</div></td>
									<td style="height:20%">
									<div>

										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend><b>当前活跃线程数</b></legend>
											<div id="busyThread" style="height:200px"></div>
										</fieldset>
									</div></td>

							</tr>

			</table>
		</div>


	</div>



</body>
</html>
