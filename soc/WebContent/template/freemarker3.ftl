<!DOCTYPE html>
<html>
  <head>
    <title>安全运行状态报表</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
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
*{
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
	font-size: 12px;
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


-->
</style>

<script type="text/javascript">
    var chart;
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
	var timer = setInterval(handler, 2000);
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
	marginheight=8 style="width:98%;">
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
			<input type="hidden"  name="reportType" id="reportType" value = ""/> 
			<input type="hidden"  name="picCount" id="picCount" value = "1"/> 
			<input type="hidden"  name="auditReportId" id="auditReportId" value = "${auditReportId}"/>  	
			
			<input type="hidden" name="exportType" value="html"></input>
			</div>
		</form>
		<table border=0 cellpadding=0 cellspacing=0 style="width:100%;">
			<tr>
				<td colspan=7 style = "text-align:left"><font size=5>安全运行状态报表</font></td>
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
					<td height=20 style = "text-align:right;">生成时间：${timeDetail}&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr id='cd_0' style='display:'>
					<td align=center valign=top>
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
							<tr>
								<td valign=top>
							<!-- 资产列表 -->
							<table id='cd_10' width=100% border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td>
										<table border=0 cellpadding=0 cellspacing=0 width=100%>
											<tr>
												
												<td colspan="2">
													<table width=100% border=1 cellpadding=0 cellspacing=0>
													

																<tr height=27>
																	<td width=17% class=t2 colspan="7"> 截至 ${timeDetail}，安管平台总计运行 1 天，当前连续运行 1 天，最大无故障运行时间为 1 天。</td>

																</tr>
																
															<tr height=27>
															<td width=17% colspan="7" style = "text-align: center;">   历史上，高危 0 天、风险 0 天、安全 1 天，其分布情况如下图所示：。</td>
															</tr>


																
															<tr height=27>
															<td width=17% colspan="7"  style = "text-align: center;">   

																<!--- z这里写一个-->
															<div id="container" width = "680"></div></td>
															</tr>
																
															<tr height=27>
															<td width=17% colspan="7"  style = "text-align: center;"> 最近一周的安全运行状态变化趋势如下图所示：</td>
															</tr>																
															<tr height=27>
															<td width=17% colspan="7"  style = "text-align: center;">   

																<!--- z这里写一个-->
															<div id = "chart_3"></div></td>
															</tr>






													
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
													id="btnSave" value="导出为HTML格式" onclick="Export('html');" />
												</td>
												<td align="center" width="25%">
												<img src="/soc/images/u21.png">
													<input type="button" class="btnyh"
													id="btnSave" value="导出为DOC格式" onclick="Export('doc');" />
												</td>
												<td align="center" width="25%">
												<img src="/soc/images/u23.png">
													<input type="button" class="btnyh"
													id="btnSave" value="导出为PDF格式" onclick="Export('pdf');" />
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
