<!DOCTYPE html>
<html>
<head>
<title>告警统计报表</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="this is my page" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<style type='text/css'>

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
	background-color: ${titleBgcolor};
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
*{
color:${textColor};
}


</style>

</head>


<body leftmargin="10" topmargin="15" marginwidth="8" marginheight="8"
	style="width:98%">
	<center>
	
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td colspan="7" align="left"><font size="5px">告警统计周报</font></td>
				<td align="right"> </td>
				
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table  border="1" style="width:95%" bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				
				<tr>
					<td height="20" style = "text-align:left;">生成时间：${timeDetail}</td>
				</tr>
			
				<tr>
					<td height="20" style="text-align:left;">本报表通过对安全管理平台从${startOfWeek}到${endOfWeek}一周时间内产生的告警。</td>

				</tr>



				<tr align="center">
					<td  align="center" colspan="3" style = "text-align:center">
						<table width="60%" border="1" cellpadding="0" cellspacing="0" style="margin-left:auto;margin-right:auto;">

							<tr height="15">
																	<td width="17%" class="t2" colspan="2">告警类型统计周报</td>

																</tr>
																
																<tr height="15">
																	<td width="20%"><b>告警类型</b></td>
																	<td width="20%"><b>告警数量</b></td>
																	
																	
																</tr>
<#list report12Table1?sort_by("count")?reverse as table1>	
																	<tr height="15">
																	<td width="17%">${table1.type}</td>
																	 <td width="11%">${table1.count}</td>  
		
																</tr>
																</#list> 
							<tr>
								<td colspan="2" align="center"><img src="${image1}" />

								</td>


							</tr>

						</table></td></tr>
<tr align="center">
					<td  align="center" colspan="3" style = "text-align:center">
<table width="60%" border="1" cellpadding="0" cellspacing="0" style="margin-left:auto;margin-right:auto;">

							<tr height="15">
																	<td width="17%" class="t2" colspan="2">告警等级统计周报</td>

																</tr>
																
																<tr height="15">
																	<td width="20%"><b>告警等级</b></td>
																	<td width="20%"><b>告警数量</b></td>
																	
																	
																</tr>
<#list report12Table2?sort_by("count")?reverse as table1>	
																	<tr height="15">
																	<td width="17%">${table1.rank}</td>
																	 <td width="11%">${table1.count}</td>  
		
																</tr>
																</#list> 
							<tr>
								<td colspan="2" align="center"><img src="${image2}" />

								</td>


							</tr>

						</table></td></tr>

<tr align="center">
					<td  align="center" colspan="3" style = "text-align:center">
						<table width="60%" border="1" cellpadding="0" cellspacing="0" style="margin-left:auto;margin-right:auto;">

							<tr height="15">
																	<td width="17%" class="t2" colspan="2">严重警告次数最多的10个设备</td>

																</tr>
																
																<tr height="15">
																	<td width="20%"><b>设备名称</b></td>
																	<td width="20%"><b>告警数量</b></td>
																	
																	
																</tr>
<#list report12Table3?sort_by("count")?reverse as table1>	
																	<tr height="15">
																	<td width="17%">${table1.name}</td>
																	 <td width="11%">${table1.count}</td>  
		
																</tr>
																</#list> 
							<tr>
								<td colspan="2" align="center"><img src="${image3}" />

								</td>


							</tr>

						</table></td></tr>
<tr align="center">
					<td  align="center" colspan="3" style = "text-align:center">
						<table width="60%" border="1" cellpadding="0" cellspacing="0" style="margin-left:auto;margin-right:auto;">

							<tr height="15">
																	<td width="17%" class="t2" colspan="2">总告警次数最多的10个设备</td>

																</tr>
																
																<tr height="15">
																	<td width="20%"><b>设备名称</b></td>
																	<td width="20%"><b>告警数量</b></td>
																	
																	
																</tr>
<#list report12Table4?sort_by("count")?reverse as table1>	
																	<tr height="15">
																	<td width="17%">${table1.name}</td>
																	 <td width="11%">${table1.count}</td>  
		
																</tr>
																</#list> 
							<tr>
								<td colspan="2" align="center"><img src="${image4}" />

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
