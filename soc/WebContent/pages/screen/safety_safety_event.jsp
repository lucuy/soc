<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<title>安全事件统计</title>
<link href="${ctx}/images/favicon.ico" rel="shortcut icon"/>
<link href="${ctx}/images/favicon.ico" rel="Bookmark"/>
    
<link href="${ctx}/styles/comprehensivemonitoring.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css" rel="stylesheet" type="text/css" />  
<link href="${ctx}/styles/user/user.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/chart/FusionCharts.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/chart/FusionCharts.js"></script>
<script  type="text/javascript" src="${ctx }/scripts/highcharts.js"></script>

<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->
    </head>
  <script type="text/javascript">
  $(document).ready(function() {
     refreshfacility(); 
   });

    function initstatisticsjqChart(data)
	   {
		chart = new Highcharts.Chart({
            chart: {
         		renderTo: 'bigstatisticsjq',//要显示在div的id 
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
                    		       var name = e.point.series.name ; 
                				   $.post("${ctx}/events/queryEventIdByCategoryName.action",{"eventTypeName":name},function(obj){
                                   window.open("${ctx}/events/editCustom.action?timeRange=12&protocol="+obj+"|&actionType=1&beginTime=&endTime=&sourcePort=&targetPort=&identification=10","_blank","width=1100,height=550,location=no,toolbar=no");
                				   });   
                				 //window.open("${ctx}/events/editCustom.action?timeRange=12&threatValue="+type+"&actionType=1&beginTime=&endTime=&sourcePort=&targetPort=","_blank","width=1100,height=500,menubar=no,location=no,toolbar=no");
                			   }
                		   }
                	  
               }
           },
             series:data
       });
	}

    function refreshfacility(){
	   $.ajax({
	      type: "post",
	      dataType: "json",
	      url:"${ctx}/comprehensive/querysafety.action",
	      success: function (data){
	      
	       initstatisticsjqChart(data);
	      	       
	      }
	   });
	}
	
	 //定时刷新数据开始	
  $(function() {
     var handler = function() {
        refreshfacility(); 
     }   
     var time = setInterval(handler,10000);
  });
  //定时刷新数据结束
	
  </script>
<body style="height: 90%">
   <div id="mainsafety" style="width: 99.7%;height: 100%;border: 0">
      <!--标题 -->
      <div id="safetytitle">
      <font color="#000000" style="line-height:50px; margin-left:15px;font-size:20px"><strong>安全事件统计</strong></font>
      </div>
      <!-- 中心内容 --> 
      <div id="maincontent">
        <div id="up_title"><font color="#000000" style="line-height:25px;font-size:12px; margin-left:10px"><strong>安全事件统计</strong></font></div>
        <div id="bigstatisticsjq" style="width: 99.7%;height: 90%"></div>
      </div>
      
  </div> 
  </body>
</html>
