<!DOCTYPE html>
<html>
<head>
<title>事件综合分析报表</title>


<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="this is my page" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<style type="text/css">
body{
font-family: SimSun;
border-collapse :collapse; 
}
td {
	font-size: 12px;
}

.t2 {
	font-size: 10pt;
	font-weight: bold;
	color: #ffffff;
	text-align: center;
	background-color: #0099dd
}

.r4 {
	text-align: center;
}
</style>
</head>


<body leftmargin="10" topmargin="15" marginwidth="8" marginheight="8"
	style="width:98%">
	<center>
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
			
			<tr>
				<td colspan="7" align="left"><font size="5px">事件综合分析报表</font>
				</td>
				<td align="right"><!--<a href="javascript:history.go(-1)">返回主报表</a>--></td>
				
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table rules="none" border="1" 
				style="width:100%;"
				 bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				<tr>
					<td height="20" style = "text-align:right;">生成时间：${timeDetail}</td>
				</tr>

				
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">一、综述</td>
				</tr>

				<tr>
					<td height="20" style="text-align:left;">本报表通过对安全管理平台在${time}内采集到的事件总数有${sumEventsReport6}个，<br/>在对这些事件进行基于事件级别、事件名称、设备名称、发生次数最多的前20个事件名称、上报事件最多的前10个设备名称等<br/>统计分析后，得出这些事件的分布结果。
				</td>

				</tr>
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">二、详细描述</td>
				</tr>
				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>1、基于事件级别的事件</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在${time}内采集到的事件，进行统计分析后，其结果可反映出事件的级别分布情况，<br/>相关统计数据如下表所示：</td>

				</tr>
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>基于事件级别的事件分布列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">事件级别</td>
								<td width="20%" class="t2">出现次数</td>

							</tr>

							<!--- 遍历数据开始 -->
							<#list report6Table1?sort_by("count")?reverse as table1>
							<tr height="15">
								<td class = "r4">${table1.priority}</td>
								<td class = "r4">${table1.count}</td>
							</tr>
							<!--- 遍历数据结束 -->
							</#list>



						</table>
					</td>
				</tr>


				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							 2、基于事件名称的事件</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在${time}内采集到的事件，进行统计分析后，其结果可反映出事件的名称分布情况，<br/>相关统计数据如下表所示：</td>

				</tr>
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>基于事件名称事件数量分布列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">事件名称</td>
								<td width="20%" class="t2">事件数量</td>

							</tr>

						<#list report6Table2?sort_by("count")?reverse as table2>
							<tr height="15">
								<td class = "r4">${table2.type}</td>
								<td class = "r4">${table2.count}</td>
							</tr>
							</#list>




						</table>
					</td>
				</tr>

				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							3、基于设备名称的事件</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在${time}内采集到的事件，进行统计分析后，其结果可反映出设备的名称分布情况，<br/>相关统计数据如下表所示：</td>

				</tr>
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>基于设备名称的事件分布列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">设备名称</td>
								<td width="20%" class="t2">事件数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<#list report6Table3?sort_by("count")?reverse as table3>
							<tr height="15">
								<td class = "r4">${table3.type}</td>
								<td class = "r4">${table3.count}</td>
							</tr>
							</#list>
							<!--- 遍历数据结束 -->

							<tr height="15">
										<td colspan = "2" style = "text-align:center"><div id="container1">
										<img src = "${image}"/></div></td>
							</tr>


						</table>
					</td>
				</tr>
				<!-- - -->









	<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							4、发生次数最多的前20个事件名称</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在${time}内采集到的事件，进行统计分析后，其发生次数最多的前20个事件名称，<br/>相关统计数据如下表所示：</td>

				</tr>
			
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>发生次数最多的前20个事件名称列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">事件名称</td>
								<td width="20%" class="t2">事件数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<#list report6Table4?sort_by("count")?reverse as table4>
							<tr height="15">
								<td class = "r4">${table4.type}</td>
								<td class = "r4">${table4.count}</td>
							</tr>
							</#list>
							<!--- 遍历数据结束 -->

							
						</table>
					</td>
				</tr>







	<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							5、上报事件最多的前10个设备名称</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在${time}内采集到的事件，进行统计分析后，其上报次数最多的前10个设备名称，<br/>相关统计数据如下表所示：</td>

				</tr>
			
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>上报事件最多的前10个设备名称列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">设备名称</td>
								<td width="20%" class="t2">事件数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<#list report6Table5?sort_by("count")?reverse as table5>
							<tr height="15">
								<td class = "r4">${table5.type}</td>
								<td class = "r4">${table5.count}</td>
							</tr>
							</#list>
							<!--- 遍历数据结束 -->

							
						</table>
					</td>
				</tr>






























				<!--
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">三、漏洞明细</td>
				</tr>

				<tr>
					<td height="20" style="text-align:left;">本节列出导入安管平台中的各种漏洞信息。<br />漏洞的信息包括：资产IP、漏洞名称、危险级别、CVE编号、漏洞描述等信息。</td>

				</tr>
				<tr>
					<td height="20" style="font-size:15px;"><b>
							漏洞明细情况如下所示:</b>
					</td>

				</tr>

				
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">
							<tr height="15">
								<td width="20%" class="t2">资产IP</td>
								<td width="20%" class="t2">漏洞名称</td>
								<td width="20%" class="t2">简明描述</td>
								<td width="20%" class="t2">危险级别</td>
							</tr>

							
							<tr height="15">
								<td width="20%" class = "r4">127.0.0.1</td>
								<td width="20%" class = "r4">3Com NBX VoIP NetSet Detection</td>
								<td width="20%" class = "r4">Logs into the remote host</td>
								<td width="20%" class = "r4">Medium</td>
							</tr>
							



						</table>
					</td>
				</tr>
			

<tr>
					<td height="20" style="text-align:left;"><br/>总结：已经达到漏洞明细报表末尾，共计有 1条漏洞记录。</td>

				</tr>-->












			</table>




















		</center>
	</center>
</body>
</html>
