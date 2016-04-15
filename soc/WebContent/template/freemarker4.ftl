<!DOCTYPE html>
<html>
<head>
<title>漏洞扫描报表</title>

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
			
			</div>

			<div style="">	
	<input type="hidden"  name="auditReportId" id="auditReportId" value = "${auditReportId}"/>  	
			<input type="hidden"  name="reportType" id="reportType" value = ""/> 
			<input type="hidden" name="exportType" value="html"></input>
			</div>
		</form>
		
		<table border=0 cellpadding=0 cellspacing=0 style="width:100%;">
			<tr>
				<td colspan=7 style = "text-align:left"><font size=+2>漏洞扫描报表</font>
				</td>
				
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
				<tr height=30>
					<td height=20
						style="border:1px solid black;font-size:20px;text-align:left;"
						class=t2>&nbsp;&nbsp;一、综述</td>
				</tr>

				<tr>
					<td height=20 style="text-align:left;" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;本报表重点描述了与资产相关的各种漏洞的统计信息和明细信息。统计信息包括：出现最多的20个高风险漏洞、高风险漏洞数量最多的20个资产、总体漏洞级别分布等统计内容。
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;明细信息包括与资产相关的漏洞的各种信息，如：资产IP、漏洞名称、风险级别、CVE编号、漏洞描述等。</td>

				</tr>
				<tr height=30>
					<td height=20
						style="border:1px solid black;font-size:20px;text-align:left;"
						class=t2>&nbsp;&nbsp;二、漏洞综合统计</td>
				</tr>
				<!--  -->
				<tr>
					<td height=20 style="text-align:left;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>1、出现最多的20个高风险漏洞</b>
					</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;根据导入的漏洞扫描数据，下面以表格方式描述出现最多的20个高风险漏洞。</td>

				</tr>
				<tr style="border:0px solid;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid ;border-top:0px solid;">

							<tr height=15>
								<td width=17% colspan="2"
									style="text-align:center;font-size:13px;"><b>出现最多的20个高风险漏洞</b>
								</td>

							</tr>

							<tr height=15>
								<td width=25% class=t2>高风险漏洞名称</td>
								<td width=19% class=t2>出现次数</td>

							</tr>

							<!--- 遍历数据开始 -->
							<tr height=15>
								<td>事件级别</td>
								<td>数量</td>
							</tr>
							<!--- 遍历数据结束 -->




						</table>
					</td>
				</tr>


				<!--  -->
				<tr>
					<td height=20 style="text-align:left;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>
							2、高风险漏洞数量排名前20名的资产</b>
					</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;根据导入的漏洞扫描数据，列出存在高风险漏洞数量最多的前20个资产。</td>

				</tr>
				<tr style="border:0px solid;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid;border-top:0px solid;">

							<tr height=15>
								<td width=17% colspan="2"
									style="text-align:center;font-size:13px;"><b>高风险漏洞数量排名前20名的资产</b>
								</td>

							</tr>

							<tr height=15>
								<td width=20% class=t2>资产IP</td>
								<td width=20% class=t2>累计出现数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<tr height=15>
								<td>事件级别</td>
								<td>数量</td>
							</tr>
							<!--- 遍历数据结束 -->




						</table>
					</td>
				</tr>

				<!--  -->
				<tr>
					<td height=20 style="text-align:left;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>
							3、总体漏洞风险级别分布</b>
					</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;根据导入的漏洞扫描数据，列出总体漏洞风险级别分布情况。</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;注：对于漏洞风险级别，1.0级表示低风险，2.0级表示中风险，3.0级表示高风险。</td>

				</tr>
				<tr style="border:0px solid;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid;border-top:0px solid;">

							<tr height=15>
								<td width=17% colspan="2"
									style="text-align:center;font-size:13px;"><b>漏洞风险级别分布</b>
								</td>

							</tr>

							<tr height=15>
								<td width=20% class=t2>风险级别</td>
								<td width=20% class=t2>累计出现数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<tr height=15>
								<td>事件级别</td>
								<td>数量</td>
							</tr>
							<!--- 遍历数据结束 -->

							<tr height=15>
								<td>图</td>
								<td>图</td>
							</tr>


						</table>
					</td>
				</tr>
				<!-- - -->


				<tr height=30>
					<td height=20
						style="border:1px solid black;font-size:20px;text-align:left;"
						class=t2>&nbsp;&nbsp;三、漏洞明细</td>
				</tr>

				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;本节列出导入安管平台中的各种漏洞信息。<br />&nbsp;&nbsp;&nbsp;&nbsp;漏洞的信息包括：资产IP、漏洞名称、危险级别、CVE编号、漏洞描述等信息。</td>

				</tr>
				<tr>
					<td height=20 style="font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>
							漏洞明细情况如下所示:</b>
					</td>

				</tr>


				<tr style="border:0px solid;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid;border-top:0px solid;">
							<tr height=15>
								<td width=20% class=t2>资产IP</td>
								<td width=20% class=t2>漏洞名称</td>
								<td width=20% class=t2>简明描述</td>
								<td width=20% class=t2>危险级别</td>
							</tr>

							<!--- 遍历数据开始 -->
							<tr height=15>
								<td width=20% >资产IP</td>
								<td width=20% >漏洞名称</td>
								<td width=20% >简明描述</td>
								<td width=20% >危险级别</td>
							</tr>
							<!--- 遍历数据结束 -->



						</table>
					</td>
				</tr>


<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;总结：已经达到漏洞明细报表末尾，共计有 283 条漏洞记录。</td>

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
