<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
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
<link href="${ctx}/styles/library.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script> 
    <script  type="text/javascript" src="${ctx}/scripts/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/exporting.js"></script>

	<link href="${ctx}/styles/user/user.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
 <script type="text/javascript">
var chart;
 function creaChart1(){
	 var assetNums = ${assetsObj};
	 
	 	// Build the chart
	     chart = new Highcharts.Chart({
	         chart: {
	             renderTo: 'container',//要显示在div的id
	             plotBackgroundColor: null,
	             plotBorderWidth: null,
	             plotShadow: false
	         },
	         title: {
					//显示的标题
	             text: '<b>${chartName}统计饼形图</b>'
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
	                     result = result.substr(0,result.indexOf(".")+1);
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
	             data: assetNums
	              
	             
	         }]
	     });
 }
 
 
 
 function creaChart(){
	
	
		 creaChart1(); 
	 
	 
 }
 function assetChart(){
		location.href = "${ctx}/pages/risk/risk_asset_chart.jsp?assetGroupId="+${assetGroupId};
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
  <input type="hidden"  name="securityReportId" id="securityReportId" value = "12"/>  
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
  <td><div id="container" style="font-size:12px;" width = "680px"></div></td>
  </tr>
 
   
  <tr>
  <td align="right"><input type="button" class="btnyh" 
					id="btnSave" value="返回"  onclick="assetChart()"/></td>
  </tr>
  </table>
   <!-- 导出的dialog -->
	<div id="dialog-extQuery" title="选择导出格式" style="font-size: 12px;">
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
