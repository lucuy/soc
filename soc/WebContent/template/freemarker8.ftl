<!DOCTYPE html>
<html>
<head>
<title>安全综合分析周报</title>

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
body{
border-collapse :collapse; 
}
<!--
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
<script type="text/javascript">
    var chart1;
    var chart2;
function Export(reportType){
$("#reportType").val(reportType);
$("#picture1").val(chart1.getSVG());
$("#picture2").val(chart2.getSVG());
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
    
    $(document).ready(function () {
    	
    	// Build the chart
        chart1 = new Highcharts.Chart({
            chart: {
                renderTo: 'container1',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
				//显示的标题
                text: '<b>基于事件级别的事件分布饼形图</b>'
            },
            tooltip: {
			//鼠标放在上面显示的内容
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1 //精度小数点后面的0的个数
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
                data: [
                   ${report8Data1}
                ]
            }]
        });
    });
    
    
});

function reload() {
	    document.onmousedown=ContextMenu;
        $("#mack").addClass("ui-widget-overlay");
       // alert(document.body.clientWidth);
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
	

$(function () {
    
    $(document).ready(function () {
    	
    	// Build the chart
        chart2 = new Highcharts.Chart({
            chart: {
                renderTo: 'container2',//要显示在div的id
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
				//显示的标题
                text: '<b>基于事件类别的事件分布饼形图</b>'
            },
            tooltip: {
			//鼠标放在上面显示的内容
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1 //精度小数点后面的0的个数
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
                data: [
                   ${report8Data2}
                ]
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
	 //打印用的js
 	function pagesetup_null(){
 		try{
 		var RegWsh = new ActiveXObject("WScript.Shell")
 		hkey_key="header" 
 		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
 		hkey_key="footer"
 		RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"")
 		}catch(e){
 		}
 		} 
 	
 	function preview(){
 		try{
 		//pagesetup_null();
 		var mes = "<input type=\"button\" value=\"导出报表\" class=\"btnstyle\""+
								"style=\"margin-right:5px;margin-top:-2px;\""+
								"onclick=\"extQueryDlg()\" />"+ 
								"<input type=\"button\" style=\"margin-right:5px;margin-top:-2px;\" value=\"打印\"  class=\"btnstyle\" onclick=\"preview();\">";
 		$("#mes").html("");
 		newwin=window.open("", "newwin", "height="+window.screen.height+",width="+window.screen.width+",toolbar=no,scrollbars=auto,menubar=no");
 		newwin.document.body.innerHTML=$("#dataList").html();
 		
 		newwin.window.print();
 		newwin.window.close();
 		$("#mes").html(mes);
 		pagesetup_default();
 		}catch(e){}
 		}
 	//打印用的js结束
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
			<input type="hidden"   name="picture2" id="picture2"></input>
			</div>

			<div style="">	
			<input type="hidden"  name="reportType" id="reportType" value = ""/> 
			<input type="hidden"  name="picCount" id="picCount" value = "2"/> 
			<input type="hidden"  name="auditReportId" id="auditReportId" value = "${auditReportId}"/>  	
			
			<input type="hidden" name="exportType" value="html"></input>
			</div>
		</form>
		<table border=0 cellpadding=0 cellspacing=0 style="width:100%;">
			
			<tr>
				<td colspan=7 style = "text-align:left"><font size=+2>安全综合分析周报</font>
				</td>
				<td style = "text-align:right"  id="mes"><input type="button" value="导出报表" class="btnstyle"
								style="margin-right:5px;margin-top:-2px;"
								onclick="extQueryDlg()" /> 
								<input type="button" style="margin-right:5px;margin-top:-2px;" value="打印"  class="btnstyle" onclick="preview();">
								</td>
				</td>
			</tr>
		</table>
		<center>
		
			<!-- 主table-->
			<div id="dataList">
			<table rules=none border=1
				style='width:100%;filter:progid:dximagetransform.microsoft.shadow(color=#333333,direction=120,strength=5)'
				bordercolor=#ddedfb bgcolor=#ffffff cellpadding=0 cellspacing=0>
				
				<tr>
					<td height=20 style = "text-align:right;">生成时间：${timeDetail}&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr height=30>
					<td height=20
						style="border:1px solid black;font-size:25px;text-align:left;"
						class=t2>&nbsp;&nbsp;一、事件综合分析报表（周报）</td>
				</tr>
				<tr height=30>
					<td height=20
						style="border:1px solid black;font-size:20px;text-align:left;"
						class=t2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一）、综述</td>
				</tr>

				<tr>
					<td height=20 style="text-align:left;" style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 本报表通过对安全管理平台从${startOfWeek}到${endOfWeek}一周时间内采集到的事件总数有 ${sumEventsReport8}个，在对这些事件进行基于事件级别、事件类别、危害性高的前20个设备名称、上报事件最多的前10个设备名称等统计分析后，得出这些事件的分布结果。
				</td>

				</tr>
				<tr height=30>
					<td height=20
						style="border:1px solid black;font-size:20px;text-align:left;"
						class=t2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二)、详细描述</td>
				</tr>
				<!--  -->
				<tr>
					<td height=20 style="text-align:left;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>1、基于事件级别的事件 </b>
					</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其结果可反映出事件的级别分布情况，相关统计数据如下表所示：</td>

				</tr>
				<tr style="border:0px solid;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid ;border-top:0px solid;">

							<tr height=15>
								<td width=17% colspan="2"
									style="text-align:center;font-size:13px;"><b>基于事件级别的事件分布列表</b>
								</td>

							</tr>

							<tr height=15>
								<td width=20% class=t2>事件级别</td>
								<td width=20% class=t2>出现次数</td>

							</tr>

							<#list report8Table1?sort_by("sum")?reverse as table1>
							<!--- 遍历数据开始 -->
							<tr height=15>
								<td>${table1.priority}</td>
								<td>${table1.sum}</td>
							</tr>
							<!--- 遍历数据结束 -->
					</#list>

<tr>
								<td colspan = "2"><div id="container1" width = "680"></div></td>
								
							</tr>


						</table>
					</td>
				</tr>


				<!--  -->
				<tr>
					<td height=20 style="text-align:left;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>
							 2、基于事件类别的事件</b>
					</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其结果可反映出事件的类别分布情况，相关统计数据如下表所示：</td>

				</tr>
				<tr style="border:0px solid;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid ;border-top:0px solid ;">

							<tr height=15>
								<td width=17% colspan="2"
									style="text-align:center;font-size:13px;"><b>基于事件类别的事件数量分布列表</b>
								</td>

							</tr>

							<tr height=15>
								<td width=20% class=t2>事件类别</td>
								<td width=20% class=t2>事件数量</td>

							</tr>
					<#list report8Table2?sort_by("count")?reverse as table2>
							<tr height=15>
								<td>${table2.type}</td>
								<td>${table2.count}</td>
							</tr>
							</#list>
<tr>
								<td colspan = "2"><div id="container2" width = "680"></div></td>
								
							</tr>


						</table>
					</td>
				</tr>
				
				<tr style="border:0px solid ;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid ;border-top:0px solid ;">

							<tr height=15>
								<td width=17% colspan="3"
									style="text-align:center;font-size:13px;"><b>基于事件类别的中高级、高级事件数量统计表</b>
								</td>

							</tr>

							<tr height=15>
								<td width=23% class=t2>事件类别</td>
								<td width=20% class=t2>中高级</td>
								<td width=20% class=t2>高级</td>

							</tr>

							<#list report8Table3 as table3>
							<tr height=15>
								<td>${table3.asset}</td>
								<td>${table3.priority4}</td>
								<td>${table3.priority5}</td>
							</tr>
							</#list>




						</table>
					</td>
				</tr>

				<!--  -->
				<tr>
					<td height=20 style="text-align:left;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>
						3、危害性高的前20个设备名称</b>
					</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其危害性高的前20个设备名称，相关统计数据如下表所示：</td>

				</tr>
				<tr style="border:0px solid ;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid ;border-top:0px solid ;">

							<tr height=15>
								<td width=17% colspan="2"
									style="text-align:center;font-size:13px;"><b>危害性高的前20个设备名称列表</b>
								</td>

							</tr>

							<tr height=15>
								<td width=30% class=t2>设备名称</td>
								<td width=20% class=t2>事件数量</td>

							</tr>

							<#list report8Table4?sort_by("count")?reverse as table4>
							<tr height=15>
								<td>${table4.type}</td>
								<td>${table4.count}</td>
							</tr>
							</#list>



						</table>
					</td>
				</tr>
				<!-- - -->









	<tr>
					<td height=20 style="text-align:left;font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>
							4、上报事件最多的前10个设备名称 </b>
					</td>

				</tr>
				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其上报次数最多的前10个设备名称，相关统计数据如下表所示：</td>

				</tr>
			
				<tr style="border:0px solid ;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid ;border-top:0px solid ;">

							<tr height=15>
								<td width=17% colspan="2"
									style="text-align:center;font-size:13px;"><b>上报事件最多的前10个设备名称</b>
								</td>

							</tr>

							<tr height=15>
								<td width=30% class=t2>设备名称</td>
								<td width=20% class=t2>事件数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<#list report8Table5 as table5>
							<tr height=15>
								<td>${table5.type}&nbsp;</td>
								<td>${table5.count}&nbsp;</td>
							</tr>
							</#list>
							<!--- 遍历数据结束 -->

							
						</table>
					</td>
				</tr>







	


























				<!--
				<tr height=30>
					<td height=20
						style="border:1px solid black;font-size:20px;text-align:left;"
						class=t2>&nbsp;&nbsp;二、资产风险排名前10位报表</td>
				</tr>

				<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp; 截至2013年02月19日16时27分19秒，安全管理平台通过实时风险评估，得出资产风险排名情况。</td>

				</tr>
				<tr>
					<td height=20 style="font-size:15px;">&nbsp;&nbsp;&nbsp;&nbsp;<b>
							资产风险排名前10位</b>
					</td>

				</tr>


				<tr style="border:0px solid ;">
					<td align=center valign=top colspan="2">
						<table width=60% border=1 cellpadding=0 cellspacing=0
							style="margin-left:auto;margin-right:auto;border-bottom:0px solid ;border-top:0px solid ;">
							<tr height=15>
								<td width=30% class=t2>资产名称</td>
								<td width=25% class=t2>所属域</td>
								<td width=20% class=t2>资产IP</td>
								<td width=20% class=t2>资产风险值</td>
							</tr>

							
							<tr height=15>
								<td width=20% >资产IP</td>
								<td width=20% >漏洞名称</td>
								<td width=20% >简明描述</td>
								<td width=20% >危险级别</td>
							</tr>
							



						</table>
					</td>
				</tr>
				-->

<tr>
					<td height=20 style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;<br/>根据上面的资产风险排名情况，管理人员可以清楚的掌握全网中的高风险资产状况，对风险性值较大的资产，应引起管理人员的关注。</td>

				</tr>












			</table>

</div>


















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
