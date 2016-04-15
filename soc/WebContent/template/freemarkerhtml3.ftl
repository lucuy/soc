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
		<table  border="0" cellpadding="0" cellspacing="0" style = "width:100%;">
			<tr>
				<td colspan="7" style = "text-align:left"><font size="5px">安全运行状态报表</font></td>
				<td style = "text-align:right"><!--<a href="javascript:history.go(-1)">返回主报表</a>--></td>
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table rules="none" border="1" style="width:100%;" bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				<tr>
					<td height="20" style = "text-align:right;">生成时间：${timeDetail}</td>
				</tr>
				<tr id='cd_0' >
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td valign="top">
							<!-- 资产列表 -->
							<table id='cd_10' width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												
												<td colspan="2">
													<table width="100%" border="1" cellpadding="0" cellspacing="0">
													

																<tr height="27">
																	<td width="17%" class="t2" colspan="7" > 截至 ${timeDetail}，安管平台总计运行 1 天，当前连续运行 1 天，最大无故障运行时间为 1 天。</td>

																</tr>
																
															<tr height="27">
															<td width="17%" colspan="7"  style = "text-align: center;" >   历史上，高危 0 天、风险 0 天、安全 1 天，其分布情况如下图所示：。</td>
															</tr>


																
															<tr height="27">
															<td width="17%" colspan="7"  style = "text-align: center;" >   

																<!--- z这里写一个-->
															<div id = "chart_2"></div></td>
															</tr>
																
															<tr height="27">
															<td width="17%" colspan="7"  style = "text-align: center;"> 最近一周的安全运行状态变化趋势如下图所示：</td>
															</tr>																
															<tr height="27">
															<td width="17%" colspan="7"  style = "text-align: center;">   

																<!--- z这里写一个-->
															<div id = "chart_3"></div></td>
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
						</table>
		</center>
	</center>
</body>
</html>
