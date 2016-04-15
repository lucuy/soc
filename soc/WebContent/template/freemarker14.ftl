<!DOCTYPE html>
<html>
  <head>
    <title>陕西台安管平台事件周报</title>
	
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
				<td colspan=7 style = "text-align:left"><font size=+2>陕西台安管平台事件周报</font></td>
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
					<td height=20 style = "text-align:left;">&nbsp;&nbsp;本报表显在设备日志中，事件发生次数的明细情况。下表是以列表形式列出设备明细情况。</td>
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
																	<td width=5% class=t2>发生时间</td>
																	<td width=19% class=t2 colspan = "3">高级事件</td>
												
																	<td width=19% class=t2 colspan = "3">中级、中高级事件</td>

																</tr>
																<tr height=15>
																	<td width=5% class=t2>事件统计</td>
																	<td width=19% class=t2 rowspan="2">上周</td>
																	<td width=11% class=t2  rowspan="2">本周</td>
																	<td width=11%  class=t2>环比增减</td>
																	<td width=15% class=t2 rowspan="2">上周</td>
																	<td width=19% class=t2  rowspan="2">本周</td>
																	<td width=11%  class=t2 >环比增减</td>
																	
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2>事件类型</td>
																	
																	<td width=8%  class=t2>（%）</td>
																	
																	<td width=8%  class=t2>（%）</td>
																
																	
																</tr>
<#list report14Table1 as table1>
																	<tr height=15>
																	<td width=17%>${table1.category}&nbsp;</td>
																	 <td width=11%>${table1.seriouseventslastweek}&nbsp;</td>  
																	<td width=11% >${table1.seriouseventsthisweek}&nbsp;</td>
																	<td width=8% >${table1.QoQChangeSerious}&nbsp;</td>
																	 <td width=11%>${table1.intermediateeventslastweek}&nbsp;</td>  
																	<td width=11% >${table1.intermediateeventsthisweek}&nbsp;</td>
																	<td width=8% >${table1.QoQChangeIntermediate}&nbsp;</td>
																</tr>
																</#list> 
																
															</table></td>
												
											</tr>
								
							
							
							
							
							
							
							<tr height=15></tr>
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
								<tr>
									<td>
										<table border=0 cellpadding=0 cellspacing=0 width="100%">
											
									
											
										<tr>
												
												<td colspan="2">
													<table width="90%" border=1 cellpadding=0 cellspacing=0 style = "margin-left:auto;margin-right:auto;">
										
													
																
																
													
																
									<#if category10??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">病毒木马</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category10Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category10 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">该类事件可以明确归为病毒木马的范畴内，防病毒产品及其它安全产品有时候会发现一些新的病毒木马样本或行为，这类事件表明了可能有病毒木马存在，也可能是误报。  建议一：升级安全管理平台的知识库，增强病毒木马的识别判断能力。建议二：查看事件的长描述，获取更多信息。建议三：加强病毒防范力度，进行全面病毒扫描。</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


															
											
											</#if>
											
											<#if category11??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">未知类型</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category11Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category11 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">暂无，请留意最新的知识更新  如果经过调查判断，发现其中某些事件对与安全管理没有实际意义的话，比如一些已知的噪声事件。可以在事件采集的时候进行过滤。</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


															
											
											</#if>
											
											
											
											<#if category12??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">系统状态</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category12Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category12 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																	<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">暂无</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


														
											
											</#if>
											
											
											
											
											
											<#if category13??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">扫描探测</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category13Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category13 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">暂无</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


															
											
											</#if>
											
											
											
											
											
											<#if category14??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">拒绝服务</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category14Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category14 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">暂无</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


															
											
											</#if>
											
											
										<#if category15??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">规避</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category15Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category15 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">暂无</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


															
											
											</#if>	
											
											
											
											<#if category16??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">认证授权</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category16Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category16 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">暂无</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


														
											
											</#if>
											
											
											
											
										<#if category17??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">应用漏洞</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category17Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category17 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list> 
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">暂无</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3"> </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


															
											
											</#if>	
											
											
											<#if category18??>
															<tr height=15>
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width=19%  class=t2  colspan = "3">非授权访问</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																	<td width=5% class=t2 rowspan =${category18Size} >最受影响的IP地址</td>
																	<td width=19%  >IP地址</td>
																	<td width=19%   >资产名称</td>
																	<td width=19%  >所属业务系统</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<#list category18 as table2>
																<tr height=15>
																
															
																	<td width=19%  >${table2.assetip}</td>
																	<td width=19%   >${table2.assetname}</td>
																		<td width=19%  >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	<!--<td width=8% >事件级别</td>-->
																	</#list>  
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件危害分析</td>
																	
																	<td width=19%   colspan = "3">这些事件可能是以前没有遇到过的策略违反事件，也可能是遇到过但没有被识别归纳的事件。  建议：在加强监管的同时，也要组织成员加强安全意识和安全制度方面的教育。</td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>
																<tr height=15>
																<td width=5% class=t2>事件处理方式</td>
																	
																	<td width=19%   colspan = "3">    </td>
																	
																	<!--<td width=8% >事件级别</td>-->
																	
																</tr>

																


															</table></td>
											</tr>
											
											</#if>
											
											
											
											
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
