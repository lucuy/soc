<!DOCTYPE html>
<html>
  <head>
    <title>安全管理平台月报</title>
	
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
	var timer = setInterval(handler, 100);
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
				<td colspan=7 style = "text-align:left"><font size=+2>安全管理平台月报</font>
				</td>
				<td style = "text-align:right"><input type="button" value="导出报表" class="btnstyle"
								style="margin-right:5px;margin-top:-2px;"
								onclick="extQueryDlg()" /></td>

			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table rules=none border=1
				style='width:100%;filter:progid:dximagetransform.microsoft.shadow(color=#333333,direction=120,strength=5)'
				bordercolor=#ddedfb bgcolor=#ffffff cellpadding=0 cellspacing=0>
				<tr>
					<td height=20 style="text-align:right;">生成时间：${timeDetail}&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>


				<tr>
					<td align=center valign=top colspan="2">
						<table width=100% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;">


																<tr height=15>
																	<td width=17%  rowspan="2">基于事件级别统计</td>
																	<td width=11% >低风险</td>
																	<td width=11% >中低风险</td>
																	<td width=8% >中风险</td>
																	<td width=8% >中高风险</td>
																	<td width=8% >高风险</td>
																	<td width=8% >合计</td>
																</tr>

							<#list report2Table1 as table1>
																		<tr height=15>
																	<td width=17% >${table1.priority1}</td>
																	<td width=11% >${table1.priority2}</td>
																	<td width=11% >${table1.priority3}</td>
																	<td width=8% >${table1.priority4}</td>
																	<td width=8% >${table1.priority5}</td>
																	<td width=8% >${table1.priority1+table1.priority2+table1.priority3+table1.priority4+table1.priority5}</td>
																</tr>
<tr height=15>
																	<td width=17% >与上月发生事件数增长百分比(%)</td>
																	<td width=11% >${(table1.priority6-table1.priority1)/100}%</td>
																	<td width=8% >${(table1.priority7-table1.priority2)/100}%</td>
																	<td width=8% >${(table1.priority8-table1.priority3)/100}%</td>
																	<td width=8% >${(table1.priority9-table1.priority4)/100}%</td>
																	<td width=8% >${(table1.priority10-table1.priority5)/100}%</td>																
																	<td width=11% >${((table1.priority1+table1.priority2+table1.priority3+table1.priority4+table1.priority5)-(table1.priority1+table1.priority2+table1.priority3+table1.priority4+table1.priority5))/100}%</td>
																	
																</tr>
																</#list>





																<tr height=15>
																	<td width=17% class=t2 colspan="6">按安全域统计</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>安全域名称</td>
																	<td width=11%>低风险</td>
																	<td width=11% >中低风险</td>
																	<td width=8% >中风险</td>
																	<td width=8% >中高风险</td>
																	<td width=8% >高风险</td>
																	<td width=8% >合计</td>
																	
																</tr>
																<#list report2Table2 as table2>
																	<tr height=15>
																	<td width=17%>${table2.asset}</td>
																	<td width=8% >${table2.priority1}</td>
																	<td width=11%>${table2.priority2}</td>
																	<td width=11% >${table2.priority3}</td>
																	<td width=8% >${table2.priority4}</td>
																	<td width=8% >${table2.priority5}</td>
																	<td width=8% >${table2.priority1+table2.priority2+table2.priority3+table2.priority4+table2.priority5}</td>
																	
																</tr>
																</#list>
																
																<tr height=15>
																	<td width=17% class=t2 colspan="7">事件按设备统计</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>设备类型</td>
																	<td width=11%>低风险</td>
																	<td width=11% >中低风险</td>
																	<td width=8% >中风险</td>
																	<td width=8% >中高风险</td>
																	<td width=8% >高风险</td>
																	<td width=8% >合计</td>
																
				
	
																<#list report2Table4 as table4>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table4.asset}</td>
																	<td width=8% >${table4.priority1}</td>
																	<td width=11%>${table4.priority2}</td>
																	<td width=11%>${table4.priority3}</td>
																	<td width=8% >${table4.priority4}</td>
																	<td width=8% >${table4.priority5}</td>
																	<td width=8% >${table4.priority1+table4.priority2+table4.priority3+table4.priority4+table4.priority5}</td>
																	
																</tr>
																</#list>




														<tr height=15>
																	<td width=17% class=t2 colspan="7">事件统计TOP10</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>设备名称</td>
																	<td width=11%>低风险</td>
																	<td width=11% >中低风险</td>
																	<td width=8% >中风险</td>
																	<td width=8% >中高风险</td>
																	<td width=8% >高风险</td>
																	<td width=8% >合计</td>
																	
																	
																	<#list report2Table3?sort_by("total")?reverse as table3>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table3.asset}</td>
																	<td width=8% >${table3.priority1}</td>
																	<td width=11%>${table3.priority2}</td>
																	<td width=11%>${table3.priority3}</td>
																	<td width=8% >${table3.priority4}</td>
																	<td width=8% >${table3.priority5}</td>
																	<td width=8% >${table3.total}</td>
																	
																</tr>
																</#list>
																	
																	
																	
																	
																	
																




<tr height=15>
																	<td width=17% class=t2 colspan="7">发生事件次数统计TOP20</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>事件类型</td>
																	<td width=11%>事件级别</td>
																	<td width=11% >目的地址</td>
																	<td width=8% >源设备名称</td>
																	<td width=8% colspan = "3">事件描述</td>
																<#list report2Table5 as table5>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table5.type}</td>
																	<td width=11%>${table5.priority}</td>
																	<td width=11% >${table5.devaddr}</td>
																	<td width=8% >${table5.name}</td>
																	<td width=8% colspan = "3">${table5.c}</td>
																</tr>
													</#list>	


<tr height=15>
																	<td width=17% class=t2 colspan="7">高风险设备统计TOP20</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>设备名称</td>
																	<td width=11%>高风险事件类型</td>
																	<td width=11% >高风险事件次数</td>
																	<td width=8% colspan = "4">事件描述</td>
																	
																<#list report2Table10 as table10>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table10.name}</td>
																	
																	<td width=11% >${table10.type}</td>
																	<td width=8% >${table10.sum}</td>
																	<td width=8% colspan = "4">${table10.c}</td>
																</tr>
													</#list>	



<tr height=15>
																	<td width=17% class=t2 colspan="7">中高风险设备统计TOP20</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>设备名称</td>
																	<td width=11%>中高风险事件类型</td>
																	<td width=11% >中高风险事件次数</td>
																	<td width=8% colspan = "4">事件描述</td>
																	
																<#list report2Table9 as table9>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table9.name}</td>
																	
																	<td width=11% >${table9.type}</td>
																	<td width=8% >${table9.sum}</td>
																	<td width=8% colspan = "4">${table9.c}</td>
																</tr>
													</#list>	



<tr height=15>
																	<td width=17% class=t2 colspan="7">中风险设备统计TOP20</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>设备名称</td>
																	<td width=11%>中风险事件类型</td>
																	<td width=11% >中风险事件次数</td>
																	<td width=8% colspan = "4">事件描述</td>
																	
																<#list report2Table8 as table8>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table8.name}</td>
																	
																	<td width=11% >${table8.type}</td>
																	<td width=8% >${table8.sum}</td>
																	<td width=8% colspan = "4">${table8.c}</td>
																</tr>
													</#list>	


<tr height=15>
																	<td width=17% class=t2 colspan="7">中低风险设备统计TOP20</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>设备名称</td>
																	<td width=11%>中低风险事件类型</td>
																	<td width=11% >中低风险事件次数</td>
																	<td width=8% colspan = "4">事件描述</td>
																	
																<#list report2Table7 as table7>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table7.name}</td>
																	
																	<td width=11% >${table7.type}</td>
																	<td width=8% >${table7.sum}</td>
																	<td width=8% colspan = "4">${table7.c}</td>
																</tr>
													</#list>	


<tr height=15>
																	<td width=17% class=t2 colspan="7">低风险设备统计TOP20</td>

																</tr>
																
																<tr height=15>
																	<td width=17%>设备名称</td>
																	<td width=11%>低风险事件类型</td>
																	<td width=11% >低风险事件次数</td>
																	<td width=8% colspan = "4">事件描述</td>
																	
																<#list report2Table6 as table6>	
																</tr>
																	<tr height=15>
																	<td width=17%>${table6.name}</td>
																	
																	<td width=11% >${table6.type}</td>
																	<td width=8% >${table6.sum}</td>
																	<td width=8% colspan = "4">${table6.c}</td>
																</tr>
													</#list>	













						</table>
					</td>
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