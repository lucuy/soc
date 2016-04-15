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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'audit_report.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/soc/styles/library.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/soc/scripts/jquery-1.7.2.min.js"></script> 
    <script  type="text/javascript" src="/soc/scripts/highcharts.js"></script>
<script type="text/javascript" src="/soc/scripts/exporting.js"></script>

	<link href="/soc/styles/user/user.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
 <script type="text/javascript">
var chart;
 
 function assetChart(){
		location.href = "${ctx}/pages/risk/risk_value_chart.jsp";
	}

 function creaChart2(){
	  var	xs = [''];
   var	returnValue=${returnValue};
        chart = new Highcharts.Chart({
         chart: {
               renderTo: 'container',          //放置图表的容器
               defaultSeriesType: 'column',    //图表类型line, spline, area, areaspline, column, bar, pie , scatter
               //zoomType: 'x'
               inverted: false                  //左右显示，默认上下正向
           },
           title: {                            
               text: '${chartName}'+'统计柱形图',        //图标的标题
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
               y: 30,
               shadow: true
           },
           tooltip: {
               formatter: function() {                 //当鼠标悬置数据点时的格式化提示
                   return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y, 2);
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
             series:returnValue
       });
}
 
 
 function creaChart(){
	
		 creaChart2();
	 
	 
 }
 jQuery(document).ready(function() {
		
		//初始化格式对话框
		
		
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 700,
			height : 300,
			buttons: {
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		
			
	});
	
function Export(reportType){
	$("#reportType").val(reportType);
	 $("#picture1").val(chart.getSVG());
	 $("#export").submit();
	 $("#dialog-extQuery").dialog("close");
	
	 	
	 	}
 </script>
  </head>
 
  <body onload="creaChart();">
  
  <s:form action="exportReport" namespace="/assetRiskGroup" method="post" id = "export">
  <input id="asset" type="hidden" value="${assetsObj}" /> 
  <input type="hidden"  name="reportType" id="reportType" value = ""/> 
  <input type="hidden"  name="picCount" id="picCount" value = "1"/> 
  <input type="hidden" name="chartX" id="chartX" value="${chartX}"/>
   <input type="hidden" name="chartY" id="chartX" value="${chartY}"/>
   <input type="hidden" name="topN" id="topN" value="${topN}"/>
   <input type="hidden" name="falg" id="falg" value="${falg}"/>
  <input type="hidden"  name="securityReportId" id="securityReportId" value = "11"/>  
  <input type="hidden" id="chartName" name="chartName" value="${chartName}"/>
  <input type="hidden"  name="picture1" id="picture1"></input></s:form>
  <table width="70%">
   <tr>
				<td>
					<div class="box">

						<div class="right">
						
							<input type="button" value="导出" class="btnstyle"
								style="margin-right:5px;margin-top:2px"
								onclick="$('#dialog-extQuery').dialog('open');" />
								
							
						</div>

						</div>
				</td>

			</tr>
  <tr>
  <td>
  
  <div id="container" width = "680px"></div></td>
  </tr>
 
  <tr>
  <td><div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
  <table   width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="listTable" style="font-size:12px;">
  <tr height="28" class="biaoti"><th width="50%" class="biaoti">名称</th>
  <th  width="50%" class="biaoti">值</th></tr>
  <s:iterator value="assetmap" status="stat">
  <tr>
  <td   valign="middle">${key}
  
  </td>
   <td  valign="middle">${value}
  
  </td>
  </tr>
  
  </s:iterator>
  </table>
  </div></div></div>
  </td>
  
  </tr>
  <tr>
  <td align="right"><input type="button" class="btnyh" 
					id="btnSave" value="返回"  onclick="assetChart()"/></td>
  </tr>
  </table>
   <!-- 导出的dialog -->
	<div id="dialog-extQuery" title="选择导出格式" style="font-size: 12px;" >
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top">
					<!--  左侧table-->
					<div class="framDiv" style="border:none;">
									<!-- 报表模板内容 -->
									<table width="70%" border="0" cellspacing="0" cellpadding="0"
											style="margin-left: 4px;" align="center">
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
								
											<tr>
												<td align="center" width="30%">
												<img src="/soc/images/u19.png" >
													<input type="button" class="btnyh" width="50%" style="font-size: 12px;"
													id="btnSave" value="导出为html" onclick="Export('html');" />
												</td>
												<td align="center" width="30%">
												<img src="/soc/images/u21.png">
													<input type="button" class="btnyh" width="50%" style="font-size: 12px;"
													id="btnSave" value="导出为DOC" onclick="Export('doc');" />
												</td>
												<td align="center" width="30%">
												<img src="/soc/images/u23.png" >
													<input type="button" class="btnyh" width="50%" style="font-size: 12px;"
													id="btnSave" value="导出为PDF" onclick="Export('pdf');" />
												</td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
										</table>
						</div>
				</td>
			</tr>
			
		</table>
	</div>
   
  </body>
</html>
