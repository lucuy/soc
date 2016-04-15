<!DOCTYPE html>
<html>
<head>
<title>资产类型统计报表</title>

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
				<td colspan="7" align="left"><font size="5px">资产类型统计报表</font></td>
				<td align="right"> </td>
				
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table  border="1" style="width:100%" bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				
				<tr>
					<td height="20" style = "text-align:right;">生成时间：${timeDetail}</td>
				</tr>
			
				<tr>
					<td height="20" style="text-align:left;">截至${timeDetail}，安全管理平台共有 ${report9Table1Count} 种资产类型，如下表所示：</td>

				</tr>


				<tr>
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2" style="text-align:center;font-size:13px;"><b>资产类型列表</b></td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">资产类型名称</td>
								<td width="19%" class="t2">资产类型描述</td>

							</tr>

							<!--- 遍历数据开始 -->
							<#list report9Table1 as table1>

							<tr height="15">
								<td class="r4">${table1.name}</td>
								<td class="r4">${table1.desc}</td>

							</tr>
							<!--- 遍历数据结束 -->
							</#list>


</table>
</td>
				</tr>
				



				<tr>
					<td height="20" style="text-align:left;">安全管理平台的
						${report9Table2Count}个资产中，按资产类型、资产数量统计，其统计列表如下：</td>

				</tr>


				<tr>
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0" style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>资产类型统计列表</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">资产类型</td>
								<td width="19%" class="t2">资产数量</td>

							</tr>

							<!--- 遍历数据开始 -->

							<#list report9Table2?sort_by("count")?reverse as table2>
							<tr height="15">
								<td class="r4">${table2.name}</td>
								<td class="r4">${table2.count}</td>

							</tr>
							<!--- 遍历数据结束 -->
							</#list>

							<tr style = "text-align:center">
								<td colspan="2"><img src="${image}" />

								</td>


							</tr>

						</table>
					</td>
				</tr>



			</table>




















		</center>
	</center>
</body>
</html>
