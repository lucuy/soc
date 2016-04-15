
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>


    <title>子域风险变化趋势</title>
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon"/>
    <link href="${ctx}/images/favicon.ico" rel="Bookmark"/>
     
<link href="${ctx}/styles/comprehensivemonitoring.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css" rel="stylesheet" type="text/css" />--%>  
<link href="${ctx}/styles/user/user.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/scripts/chart/FusionCharts.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/chart/FusionCharts.js"></script>--%>

<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->
    </head>
  <script type="text/javascript">
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
	var background={
		    type:'linearGradient',
			x0:0,
			y0:0,
			x1:0,
			y1:1,
			colorStops:[{offset:0,color:'#FBFBF9'},{offset:1,color:'white'}]
		};
		
		var data = [];
        var i;
        for (i = 0; i < 10; i++) {
            yValue = 1 + Math.random();
            var temp = String(yValue); 
            temp.substring(0,4);
            data.push(["", temp]);
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
        }			
  $(document).ready(function() {
    
     inittendencyjqChart();
     
   });

    function inittendencyjqChart()
	{
	    $('#bigriskjq').jqChart({
			background : background,
			legend: { title: '子域名称' },
			border : {
				strokeStyle : '#FCFCFA'
			},
            tooltips: { type: 'shared' },
			
			axes:[
			  {
			      type:'linear',
			      minimum:0,
			      maximum:3,
			      interval:0.1
			  }
			],
			series : [ {
			    title: '新闻包装',
				type : 'line',
				font : '22 sans-serif',
				data : data,
				fillStyle : fillStyle1,
				strokeStyle : '#3D69C8'
			} ,
			{
			    title: '音频媒资',
			    type : 'line',
				font : '22 sans-serif',
				data : data1
			},
			{
			    title: '企业媒体总线',
			    type : 'line',
				font : '22 sans-serif',
				data : data2
			},
			{
			    title: '广告备播系统',
			    type : 'line',
				font : '22 sans-serif',
				data : data3
			},
			{
			    title: '存储系统',
			    type : 'line',
				font : '22 sans-serif',
				data : data4
			}
			]
		});
		updateChart();
	}
	
function updateChart() {            

            yValue = 1+ Math.random();
             var temp = String(yValue); 
             var temp1 = 1+Math.random();
             var temp2 = 1+Math.random();
             var temp3 = 1+Math.random();
             var temp4 = 1+Math.random();
             temp.substring(0,4);
             data.splice(0,1);
             data1.splice(0,1);
             data2.splice(0,1);
             data3.splice(0,1);
             data4.splice(0,1);
            // add a new element
            data.push(["", temp]);
            data1.push(["",temp1]);
            data2.push(["",temp2]);
            data3.push(["",temp3]);
            data4.push(["",temp4]);
            $('#bigriskjq').jqChart('update');

            setTimeout("updateChart()", 10000);
        }

  </script>
<body>
   <div id="mainsafety">
      <!--标题 -->
      <div id="safetytitle">
      <font color="#000000" style="line-height:50px; margin-left:15px;font-size:20px"><strong>子域风险变化趋势</strong></font>
      </div>
      <!-- 中心内容 --> 
      <div id="maincontent">
        <div id="up_title"><font color="#000000" style="line-height:25px;font-size:12px; margin-left:10px"><strong>子域风险变化趋势</strong></font></div>
        <div id="bigriskjq"></div>
      </div>
      
  </div> 
  </body>
</html>
