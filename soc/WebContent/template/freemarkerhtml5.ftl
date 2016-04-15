<!DOCTYPE html>
<html>
<head>
<title>设备明细统计日报</title>

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
				<td colspan="7" align="left"><font size="5px">设备明细统计日报</font></td>
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
					<td height="20" style = "text-align:left;">本报表显示${time}这一天内，在设备日志中，事件发生次数的明细情况。下表是以列表形式列出设备明细情况。</td>
				</tr>
				<tr id='cd_0' style='display:'>
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
							<span style = "font-size:15px;padding-left:10px;"><b></b></span>
								<td  valign="top">
							<!-- 资产列表 -->
							<table id='cd_10' width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												
												<td colspan="2" class = "r4">
													<table width="90%" border="1" cellpadding="0" cellspacing="0" style = "margin-left:auto;margin-right:auto;">
													
													
													
													
													
													
													
													
													
													
																<tr height="15">
																	<td width="17%" class="t2" colspan="4">设备明细列表（限500条）</td>

																</tr>
																
																<tr height="15">
																	<td width="5%">发生时间</td>
																	<td width="19%">设备名称</td>
																	<td width="11%" >目的IP</td>
																	<td width="8%" >事件级别</td>
																	
																	
																</tr>
<#list equipmentDetailList as equipmentDetail>
																	<tr height="15">
																	<td width="17%">${equipmentDetail.receptTime}</td>
																	 <td width="11%">${equipmentDetail.devName}</td>  
																	<td width="11%" >${equipmentDetail.dAddr}</td>
																	<td width="8%" >${equipmentDetail.priority}</td>
																	
																	

																	
																
																	
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
