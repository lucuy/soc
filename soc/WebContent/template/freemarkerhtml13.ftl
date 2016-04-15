<!DOCTYPE html>
<html>
<head>
<title>陕西台安管平台设备告警日报</title>

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
	text-align:center;
}
.td{
	font-size: 20px;
	text-align:left;
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
				<td colspan="7" align="left" class = "td"><font size="5pt">陕西台安管平台设备告警日报</font></td>
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
					<td height="20" style = "text-align:left;">本报表显示${time}这一天内，安管平台产生的告警情况。</td>
				</tr>
				<tr id="cd_0"  >
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
							<span style = "font-size:15px;padding-left:10px;"><b></b></span>
								<td  valign="top">
							<!-- 资产列表 -->
							<table id="cd_10" width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												
												<td colspan="2" class = "r4">
													<table width="90%" border="1" cellpadding="0" cellspacing="0" style = "margin-left:auto;margin-right:auto;">
													
													
													
													
													
													
													
													
													
													
																<tr height="15">
																			<td width="17%" class="t2" colspan="8">陕西台安管平台设备事件告警</td>

																</tr>
																
																<tr height="15">
																	<td width="6%">序号</td>
																	<td width="15%">设备名称</td>
																	<td width="15%" >所在系统</td>
																	<td width="15%" >日期</td>
																	<td width="25%" >告警类型</td>
																	<td width="12%" >告警分析</td>
																	<td width="12%" >告警处理以及结论</td>
																</tr>
																
<#list report13Table1 as table1>
																	<tr height="15">
																	<td >${table1.alarmid}</td>
																	 <td >${table1.alarmassetname}</td>  
																	<td>${table1.groupname}</td>
																	<td >${table1.createdate}</td>
																	 <td >${table1.sendway}</td>  
																	<td></td>
																	<td  ></td>
																	
																	
																	

																	
																
																	
																</tr>
																</#list> 
				
																
																
															




























															</table></td>
											</tr>
											
											
											
											
											
											
																				<tr>
												
												<td colspan="2">
													<table width="90%" border="1" cellpadding="0" cellspacing="0" style = "margin-left:auto;margin-right:auto;">
										
													
																<tr height="15">
																	<td width="17%" class="t2" colspan="9">陕西台安管平台设备阀值告警</td>

																</tr>
																
																	<tr height="15">
																	<td width="8%">告警内容</td>
																	<td width="15%">设备名称</td>
																	<td width="15%" >所在系统</td>
																	<td width="15%" >告警时间</td>
																	<td width="10%" >监控指标</td>
																	<td width="8%" >阀值</td>
																	<td width="8%" >当前值</td>
																	<td width="8%" >告警分析</td>
																	<td width="13%" >告警处理以及结论</td>
																</tr>
																
<#list report13Table2 as table2>
																	<tr height="15">
																	<td >${table2.alarmcontent}</td>
																	 <td >${table2.assetname}</td>  
																	<td>${table2.groupname}</td>
																	<td >${table2.createdate}</td>
																	 <td >${table2.alarmindex}</td>
																	  <td >${table2.alarmthreshold}%</td>  
																	   <td >${table2.alarmcurrentvalue}%</td>  
																	<td></td>
																	<td  ></td>
																	
																	

																	
																
																	
																</tr>
																</#list> 
																
									




























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
						</table>
		</center>
	</center>
</body>
</html>
