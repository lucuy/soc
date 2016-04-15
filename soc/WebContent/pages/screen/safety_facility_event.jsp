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

<title>设备事件分布</title>
<link href="${ctx}/images/favicon.ico" rel="shortcut icon"/>
<link href="${ctx}/images/favicon.ico" rel="Bookmark"/>

<link href="${ctx}/styles/comprehensivemonitoring.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css" rel="stylesheet" type="text/css" />  --%>
<link href="${ctx}/styles/user/user.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/scripts/chart/FusionCharts.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/chart/FusionCharts.js"></script>--%>
<script  type="text/javascript" src="${ctx }/scripts/highcharts.js"></script>

<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->
    </head>
  <script type="text/javascript">
  $(document).ready(function() {
   
     refreshfacility();
     
   });
  function initbranchjqChart(data){
  
	 chart = new Highcharts.Chart({
            chart: {
                renderTo: 'bigreportpicturejp',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
				//显示的标题
                text: ''
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
                data: data
                	 
            }]
        });	   
	}
 
    function refreshfacility(){
	   $.ajax({
	      type: "post",
	      dataType: "json",
	      url:"${ctx}/comprehensive/queryfacility.action",
	      success: function (data){
	     
	       initbranchjqChart(data); 
	      	       
	      }
	   });
	}
	
	  //定时刷新数据开始	
  $(function() {
     var handler = function() {
     
     refreshfacility();
     
     }   
     var time = setInterval(handler,1000*60*10);
  });
  //定时刷新数据结束
	
  </script>
<body style="height: 90%">
   <div id="mainsafety" style="width: 99.7%;height: 100%;border: 0">
      <!--标题 -->
      <div id="safetytitle" style="width: 100%">
      <font color="#000000" style="line-height:50px; margin-left:15px;font-size:20px"><strong>设备事件分布</strong></font>
      </div>
      <!-- 中心内容 --> 
      <div id="maincontent" style="width: 100%">
        <div id="up_title"><font color="#000000" style="line-height:25px;font-size:12px; margin-left:10px"><strong>设备事件分布</strong></font></div>
        <div id="bigreportpicturejp" style="width: 99.6%;height: 90%""></div>
      </div>
      

  </div> 
  </body>
</html>
