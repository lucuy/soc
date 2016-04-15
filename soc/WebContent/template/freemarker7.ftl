<!DOCTYPE html>
<html>
  <head>
    <title>域风险明细报表</title>
	
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

-->
</style>

<script type="text/javascript">
    var chart;
function Export(reportType){
$("#reportType").val(reportType);
$("#picture1").val(chart.getSVG());
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
$(function () {
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'column'
            },
            title: {
                text: ''
            },
            
            xAxis: {
                categories: [//这里填写域名称
                 ${reportDataCategories}
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            legend: {
                layout: 'vertical',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 100,
                y: 70,
                floating: true,
                shadow: true
            },
            tooltip: {
                formatter: function() {
                    return ''+
                        this.x +': '+this.series.name+ this.y;
                }
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            credits: {

						enabled: false //不要显示右下角的连接
					},
exporting:{ 
                     enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
                },
                series: [
                {
                name: '总风险',
                data: [${totalValue}]//这里写当前值针对每个域的数据
    
            },
                {
                name: '保密性风险',
                data: [${secretValue}]//这里写当前值针对每个域的数据
    
            }, {
                name: '完整性风险',
                data: [${integrityValue}]
    
            }, {
                name: '可用性风险',
                data: [${usabilityValue}]
    
            }]
        });
    });
    
});
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
			<input type="hidden"  name="reportType" id="reportType" value = ""/> 
			<input type="hidden"  name="picCount" id="picCount" value = "1"/> 
			<input type="hidden"  name="auditReportId" id="auditReportId" value = "${auditReportId}"/>  	
			
			<input type="hidden" name="exportType" value="html"></input>
			</div>
		</form>
		<table  border=0 cellpadding=0 cellspacing=0 style = "width:100%;">
			<tr>
			<tr style='display:none'>
				<td width=15%></td>
				<td width=14%></td>
				<td width=14%></td>
				<td width=14%></td>
				<td width=14%></td>
				<td></td>
				<td width=14%></td>
			</tr>
			<tr>
				<td colspan=7 align="left"><font size=+2>域风险明细报表</font></td>
				<td align="right"><input type="button" value="导出报表" class="btnstyle"
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
				<tr id='cd_0' '>
					<td align=center valign=top>
						<table border=0 cellpadding=0 cellspacing=0 style = "width:90%;">
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
													
													
													
													
													
													
													
													
													
													
																<tr height=15>
																	<td width=17% class=t2 colspan="5"><b>域风险列表</b></td>

																</tr>
																
																<tr height=15>
																	<td width=17% style = "text-align:center;">域名称</td>
																	<td width=11% style = "text-align:center;">总风险</td>
																	<td width=11% style = "text-align:center;">保密性风险</td>
																	<td width=11% style = "text-align:center;">完整性风险</td>
																	<td width=11% style = "text-align:center;">可用性风险</td>
																	
																</tr>
																<#list report7Table1 as table1>
																	<tr height=15>
																	<td width=17% style = "text-align:center;">${table1.name}</td>
																	<td width=11% style = "text-align:center;">${table1.secret+table1.integrity+table1.usability}</td>
																	<td width=11% style = "text-align:center;">${table1.secret}</td>
																	<td width=8% style = "text-align:center;">${table1.integrity}</td>
																	<td width=8% style = "text-align:center;">${table1.usability}</td>
																	
																</tr>
																</#list>
															<tr height=15>
																	<td width=17% class=t2 colspan="5"><b>域风险柱状图</b></td>

																</tr>
																<tr height=15>
																	<td width=17% colspan="5" style = "text-align:left;">&nbsp&nbsp&nbsp;其域风险情况柱状图如下：</td>

																	
																</tr>
																
															
																<tr height=15>
																	<td width=17% colspan="5" style = "text-align:left;">
																	
																	<div id="container"></div>

																	
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
