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


    <title>安全态势综合展现</title>
    <link href="${ctx}/images/favicon.ico" rel="shortcut icon"/>
    <link href="${ctx}/images/favicon.ico" rel="Bookmark"/>
 
 <link href="${ctx}/styles/comprehensivemonitoring.css" rel="stylesheet" type="text/css">
<%--<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css" rel="stylesheet" type="text/css" /> --%> 
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
    refreshsafety();
	//initbranchjqChart();
	//inittendencyjqChart();
    //initstatisticsjqChart();   
    //initchangetendencyjqChart();
  });
  
  function initbranchjqChart(obj)
	{
	       chart = new Highcharts.Chart({
           chart: {
                renderTo: 'branchjq',//要显示在div的id
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
                data: obj
            }]
        });	   
	   
	}
 
    function initstatisticsjqChart(safetyJson)
     {	
     chart = new Highcharts.Chart({
            chart: {
         		renderTo: 'statisticsjq',//要显示在div的id 
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
             series:safetyJson
       });
	}
	
	function refreshfacility(){
	   var url="${ctx}/comprehensive/queryfacility.action";
	   $.ajax({
	      type: "post",
	      dataType: "json",
	      url:url,
	      success: function (data){
	        initbranchjqChart(data); 	       
	      }
	   });
	}
	
	function refreshsafety(){
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
     
    // refreshsafety();
     }   
     var time = setInterval(handler,1000*10*60);
  }); 
  //定时刷新数据结束
  
  function facilitya() {
     $("#facilitya").attr("href",'${ctx}/comprehensive/querybigfacility.action');
  }
  
  function safetya() {
     $("#safetya").attr("href",'${ctx}/comprehensive/querybigsafety.action');
  }
  
  /* function riska() {
     $("#riska").attr("href",'${ctx}/comprehensive/querybigrisk.action');
  }
  
  function sonriska() {
     $("#sonriska").attr("href",'${ctx}/comprehensive/querybigsonrisk.action');
  }   */
    
  </script>
<body>
	<div id="mainsafety" style="width: 99.9%">
		<!--标题 -->
		<div id="safetytitle" style="width: 100%">
			<font color="#000000"
				style="line-height:50px; margin-left:15px;font-size:20px"><strong>安全态势综合展现</strong>
			</font>
		</div>
		<!-- 中心内容 -->
		<div id="maincontent" style="width: 100%">
			<!-- 水平第一层 -->
			<div class="horizontallayer" style="width: 100%">
				<div id="firstpicshow" style="margin-left: 15%">
					<div class="windowtitle" >
						<font class="titleinfo">设备事件分布</font> <a href="" target="_blank"
							id="facilitya" onclick="facilitya();"><img alt="放大"
							src="${ctx}/images/magnify_show_screen.png" id="magnifypagepng" />
						</a>
					</div>
					<div id="branchjq"></div>

				</div>
			<%-- 	<div id="secondpicshow">
					<div class="windowtitle">
						<font class="titleinfo">全局域风险变化趋势</font> <a href=""
							target="_blank" id="riska" onclick="riska();"><img alt="放大"
							src="${ctx}/images/magnify_show_screen.png" id="magnifypagepng" />
						</a>
					</div>
					<div id="tendencyjq"></div>
				</div>--%>
				
			</div>
			<!-- 水平第二层 -->
			 <div class="horizontallayer" style="width: 100%">
				<div id="thirdpicshow" style="margin-left: 15%">
					<div class="windowtitle">
						<font class="titleinfo">安全事件统计</font> <a href="" target="_blank"
							id="safetya" onclick="safetya();"><img alt="放大"
							src="${ctx}/images/magnify_show_screen.png" id="magnifypagepng" />
						</a>
					</div>
					<div id="statisticsjq"></div>
				</div>
				<%--<div id="fourthpicshow">
					<div class="windowtitle">
						<font class="titleinfo">子域风险变化趋势</font> <a href="" target="_blank"
							id="sonriska" onclick="sonriska();"><img alt="放大"
							src="${ctx}/images/magnify_show_screen.png" id="magnifypagepng" />
						</a>
					</div>
					<div id="changetendencyjq"></div>

				</div>--%>

			</div>

		</div>

	</div>
</body>
</html>
