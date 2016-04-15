<!DOCTYPE html>
<html>
  <head>
    <title>陕西台安管平台设备告警日报</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link href="/soc/styles/library.css" rel="stylesheet"
	type="text/css">
<link href="/soc/styles/library.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/soc/scripts/jquery-1.7.2.min.js"></script> 
    <script  type="text/javascript" src="/soc/scripts/highcharts.js"></script>
<script type="text/javascript" src="/soc/scripts/exporting.js"></script>

	<link href="/soc/styles/user/user.css" rel="stylesheet" type="text/css">
		<link href="/soc/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css">
		<script type='text/javascript' src="/soc/scripts/jquery-ui.custom.min.js"></script>

<style type='text/css'>
<!--
body{

border-collapse :collapse; 
}

td {
	font-size: 12px;
}

.m0 {
	font-size: 12pt;
	font-weight: bold;
}

.m1 {
	font-size: 10pt;
	font-weight: bold;
}

.m2 {
	font-size: 10pt;
	font-weight: bold;
	text-align: right;
}

.caption {
	font-size: 14;
	font-weight: bold;
	color: #003399;
}

.caption2 {
	font-size: 12;
	font-weight: bold;
	color: #003399;
}

.t0 {
	font-size: 12pt;
}

.t1 {
	font-size: 10pt;
	font-weight: bold;
	color: #ffffff;
	background-color: #0099dd
}

.t2 {
	font-size: 10pt;
	font-weight: bold;
	color: #ffffff;
	text-align: center;
	background-color: #0099dd
}

.t3 {
	font-size: 10pt;
	font-weight: bold;
	color: #ffffff;
	text-align: left;
	background-color: #0099dd
}

.bg_color1 {
	background-color: #ffffff
}

.bg_color2 {
	background-color: #ddedfb
}

.r0 {
	text-align: center;
	color: #ff0000;
}

.r1 {
	text-align: center;
	color: #ff00ff;
}

.r2 {
	text-align: center;
	color: #ff9900;
}

.r3 {
	text-align: center;
	color: #0000ff;
}

.r4 {
	text-align: center;
	color: #00cc00;
}
td{
	text-align: center;
}
-->
</style>
<script language="javascript">
function Export(reportType){
$("#reportType").val(reportType);
	$("#export").submit();
		  parent.frames[0].reload(); 
          parent.frames[1].reload(); 
          parent.frames[3].reload();
          parent.frames[4].reload();
	$("#dialog-extQuery").dialog("close");
	//提交后激动查询文件是否存在的方法 用ajax 存在后在看
	var handler = function(){//声明一个定时器 
	var url = "/soc/auditReport/reportFileIsExist.action";
	$.post(url,{"auditReportId":$('#auditReportId').val(),"reportType":reportType},function(data){
	//alert(data);
	if(data){//回调函数 判断文件是否已经生成
	parent.frames[0].cancel();
         parent.frames[1].cancel();
           parent.frames[3].cancel();
         parent.frames[4].cancel();
	clearTimeout(timer);
	}
	});
		};
	var timer = setInterval(handler, 200);
	}
			$(document).ready(function() {
		// Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 700,
			height : 200,
			buttons : {
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		
	});
		function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
	}
	function reload() {
	    document.onmousedown=ContextMenu;
        $("#mack").addClass("ui-widget-overlay");
        $("#mack").css("height",document.body.clientHeight);
    }
    	function ContextMenu(){
      if (event.button==2 || event.button==1) {  
             alert("加载数据中...");
             return false;
        }
  }
  function cancel() {
		document.onmousedown = null;
		$("#mack").removeClass("ui-widget-overlay");
		 $("#mack").css("height",0);
	}
	
	</script>

  </head>
  
  <body bgcolor=#ffffff leftmargin=10 topmargin=15 marginwidth=8
	marginheight=8 style = "width:98%;">
	
	<center>
		<div class="ui-overlay">
        <div id="mack"></div>
        </div>
    </div>
	<form action="ExportReport.action" name="export1" method="post" id = "export" >
			<div style="">
			<input type="hidden"  name="picture1" id="picture1"></input>
			
			</div>

			<div style="">	
	<input type="hidden"  name="auditReportId" id="auditReportId" value = "${auditReportId}"/>  	
			<input type="hidden"  name="reportType" id="reportType" value = ""/>
			<input type="hidden" name="exportType" value="html"></input>
			</div>
		</form>
		<table  border=0 cellpadding=0 cellspacing=0 style = "width:100%;">
			<tr>
				<td colspan=7 style = "text-align:left"><font size=+2>陕西台安管平台设备告警日报</font></td>
				<td style = "text-align:right"><input type="button" value="导出报表" class="btnstyle"
								style="margin-right:5px;margin-top:-2px;"
								onclick="extQueryDlg()" /> </td>
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table rules=none border=1 
				style='width:100%;filter:progid:dximagetransform.microsoft.shadow(color=#333333,direction=120,strength=5)'
				bordercolor=#ddedfb bgcolor=#ffffff cellpadding=0 cellspacing=0>
				<tr>
					<td height=20 style = "text-align:right;">生成日期:${timeDetail}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td height=20 style = "text-align:left;">&nbsp;&nbsp;本报表显示${time}这一天内，安管平台产生的告警情况。</td>
				</tr>
				<tr id='cd_0' style='display:'>
					<td align=center valign=top>
						<table border=0 cellpadding=0 cellspacing=0 width="100%">
							<tr>
							<span style = "font-size:15px;padding-left:10px;"><b>&nbsp;&nbsp;</b></span>
								<td valign=top>
							<!-- 资产列表 -->
							<table id='cd_10' width="100%" border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td>
										<table border=0 cellpadding=0 cellspacing=0 width="100%">
											<tr>
												
												<td colspan="2">
													<table width="90%" border=1 cellpadding=0 cellspacing=0 style = "margin-left:auto;margin-right:auto;">
										
													
																<tr height=15>
																	<td width="17%" class=t2 colspan="8">陕西台安管平台设备事件告警</td>

																</tr>
																
																<tr height="15">
																	<td width="6%">序号</td>
																	<td width="15%">设备名称</td>
																	<td width="15%" >所在系统</td>
																	<td width="13%" >日期</td>
																	<td width="25%" >告警类型</td>
																	<td width="12%" >告警分析</td>
																	<td width="14%" >告警处理以及结论</td>
																</tr>
																
<#list report13Table1 as table1>
																	<tr height=15>
																	<td >${table1.alarmid}&nbsp;</td>
																	 <td >${table1.alarmassetname}&nbsp;</td>  
																	<td>${table1.groupname}&nbsp;</td>
																	<td >${table1.createdate}&nbsp;</td>
																	 <td >${table1.sendway}&nbsp;</td>  
																	<td>&nbsp;</td>
																	<td  >&nbsp;</td>
																	
																	

																	
																
																	
																</tr>
																</#list> 
																
																
															




























															</table></td>
											</tr>
											
											
											
											
											
											<tr>
												
												<td colspan="2">
													<table width="90%" border=1 cellpadding=0 cellspacing=0 style = "margin-left:auto;margin-right:auto;">
										
													
																<tr height=15>
																	<td width="17%" class=t2 colspan="9">陕西台安管平台设备阀值告警</td>

																</tr>
																
																<tr height="15">
																	<td width="8%">告警内容</td>
																	<td width="15%">设备名称</td>
																	<td width="15%" >所在系统</td>
																	<td width="14%" >告警时间</td>
																	<td width="10%" >监控指标</td>
																	<td width="8%" >阀值</td>
																	<td width="8%" >当前值</td>
																	<td width="8%" >告警分析</td>
																	<td width="14%" >告警处理以及结论</td>
																</tr>
																
<#list report13Table2 as table2>
																	<tr height=15>
																	<td >${table2.alarmcontent}&nbsp;</td>
																	 <td >${table2.assetname}&nbsp;</td>  
																	<td>${table2.groupname}&nbsp;</td>
																	<td >${table2.createdate}&nbsp;</td>
																	 <td >${table2.alarmindex}&nbsp;</td>
																	  <td >${table2.alarmthreshold}%&nbsp;</td>  
																	   <td >${table2.alarmcurrentvalue}%&nbsp;</td>  
																	<td>&nbsp;</td>
																	<td  >&nbsp;</td>
																	
																	

																	
																
																	
																</tr>
																</#list> 
																
									




























															</table></td>
											</tr>
											
											
											
											
											
											
										</table></td>
								</tr>
							</table>
								</td>
								</tr>
							</table>
							</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</center>
	</center>
	<div id="dialog-extQuery" title="选择导出格式">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top">
					<!--  左侧table-->
					<div class="framDiv" style="border:none;">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td>
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
												<td align="center" width="25%">
												<img src="/soc/images/u19.png" >
													<input type="button" class="btnyh"
													id="btnSave" value="导出为HTML格式" onclick="Export('html');"  style = "font-size:12px;" />
												</td>
												<td align="center" width="25%">
												<img src="/soc/images/u21.png">
													<input type="button" class="btnyh"
													id="btnSave" value="导出为DOC格式" onclick="Export('doc');"  style = "font-size:12px;"/>
												</td>
												<td align="center" width="25%">
												<img src="/soc/images/u23.png">
													<input type="button" class="btnyh"
													id="btnSave" value="导出为PDF格式" onclick="Export('pdf');"   style = "font-size:12px;"/>
												</td>
																		<td align="center" width="25%">
												<img src="/soc/images/u25.png">
													<input type="button" class="btnyh"
													id="btnSave" value="导出为XLS格式" onclick="Export('xls');"   style = "font-size:12px;"/>
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
								</td>
							</tr>

						</table>
						</div>
				</td>
			</tr>
			
		</table>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
