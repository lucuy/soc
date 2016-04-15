<!DOCTYPE html>
<html>
<head>
<title>安全综合分析周报</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="this is my page" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

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
	
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td colspan="7" align="left"><font size="5px">安全综合分析周报</font></td>
				<td align="right"> </td>
				
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table  border="1" style="width:100%" bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				
				<tr>
					<td height="20" style = "text-align:right;">生成时间：${timeDetail}</td>
				</tr>
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:25px;text-align:left;"
						class="t2">一、事件综合分析报表（周报）</td>
				</tr>
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">一）、综述</td>
				</tr>

				<tr>
					<td height="20" style="text-align:left;"> 本报表通过对安全管理平台从${startOfWeek}到${endOfWeek}一周时间内采集到的事件总数有 ${sumEventsReport8}个，<br/>在对这些事件进行基于事件级别、事件类别、危害性高的前20个设备名称、上报事件最多的前10个设备名称等统计分析后，<br/>得出这些事件的分布结果。
				</td>

				</tr>
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">二)、详细描述</td>
				</tr>
				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>1、基于事件级别的事件 </b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其结果可反映出事件的级别分布情况，<br/>相关统计数据如下表所示：</td>

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

							<#list report8Table1?sort_by("sum")?reverse as table1>
							<!--- 遍历数据开始 -->
							<tr height="15">
								<td style = "text-align:center;">${table1.priority}</td>
								<td style = "text-align:center;">${table1.sum}</td>
							</tr>
							<!--- 遍历数据结束 -->
					</#list>




						</table>
					</td>
				</tr>
<tr style = "text-align:center">
								<td colspan = "2" style = ""><img src = "${image1}"/></td>
								
							</tr>

				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							 2、基于事件类别的事件</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其结果可反映出事件的类别分布情况，<br/>相关统计数据如下表所示：</td>

				</tr>
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>基于事件类别事件数量分布列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">事件类别</td>
								<td width="20%" class="t2">事件数量</td>

							</tr>
					<#list report8Table2?sort_by("count")?reverse as table2>
							<tr height="15">
								<td style = "text-align:center;" >${table2.type}</td>
								<td style = "text-align:center;">${table2.count}</td>
							</tr>
							</#list>



						</table>
					</td>
				</tr>
				<tr style = "text-align:center">
								<td colspan = "2"><img src = "${image2}"/></td>
								
							</tr>
				<tr>
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="3"
									style="text-align:center;font-size:13px;"><b>基于事件类别的中高级、高级事件数量统计表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="23%" class="t2">事件类别</td>
								<td width="20%" class="t2">中高级</td>
								<td width="20%" class="t2">高级</td>

							</tr>

							<#list report8Table3 as table3>
							<tr height="15">
								<td style = "text-align:center;">${table3.asset}</td>
								<td style = "text-align:center;">${table3.priority4}</td>
								<td style = "text-align:center;">${table3.priority5}</td>
							</tr>
							</#list>




						</table>
					</td>
				</tr>

				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
						3、危害性高的前20个设备名称 </b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其危害性高的前20个设备名称，<br/>相关统计数据如下表所示：</td>

				</tr>
				<tr>
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>危害性高的前20个设备名称列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="30%" class="t2">设备名称</td>
								<td width="20%" class="t2">事件数量</td>

							</tr>

							<#list report8Table4?sort_by("count")?reverse as table4>
							<tr height="15">
								<td style = "text-align:center;">${table4.type}</td>
								<td style = "text-align:center;">${table4.count}</td>
							</tr>
							</#list>



						</table>
					</td>
				</tr>
				<!-- - -->









	<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							4、上报事件最多的前10个设备名称 </b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据安全管理平台在一周时间内采集到的事件，进行统计分析后，其上报次数最多的前10个设备名称，<br/>相关统计数据如下表所示：</td>

				</tr>
			
				<tr>
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>上报事件最多的前10个设备名称</b>
								</td>

							</tr>

							<tr height="15">
								<td width="30%" class="t2">事件名称</td>
								<td width="20%" class="t2">事件数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<#list report8Table5 as table5>
							<tr height="15">
								<td style = "text-align:center;">${table5.type}</td>
								<td style = "text-align:center;">${table5.count}</td>
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
						class="t2">二、资产风险排名前10位报表</td>
				</tr>

				<tr>
					<td height="20" style="text-align:left;"> 截至${timeDetail}，安全管理平台通过实时风险评估，得出资产风险排名情况。</td>

				</tr>
				<tr>
					<td height="20" style="font-size:15px;"><b>
							资产风险排名前10位</b>
					</td>

				</tr>


				<tr>
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">
							<tr height="15">
								<td width="30%" class="t2">资产名称</td>
								<td width="25%" class="t2">所属域</td>
								<td width="20%" class="t2">资产IP</td>
								<td width="20%" class="t2">资产风险值</td>
							</tr>

							<tr height="15">
								<td width="20%" class = "r4">资产IP</td>
								<td width="20%" class = "r4">漏洞名称</td>
								<td width="20%" class = "r4">简明描述</td>
								<td width="20%" class = "r4">危险级别</td>
							</tr>



						</table>
					</td>
				</tr>
				-->

<tr>
					<td height="20" style="text-align:left;">根据上面的资产风险排名情况，管理人员可以清楚的掌握全网中的高风险资产状况，<br/>对风险性值较大的资产，应引起管理人员的关注。</td>

				</tr>












			</table>




















		</center>
	</center>
</body>
</html>
