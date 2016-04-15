<!DOCTYPE html>
<html>
  <head>
    <title>域风险明细报表</title>
	
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
				<td colspan="7" align="left"><font size="5px">域风险明细报表</font></td>
				<td align="right"><!--<a href="javascript:history.go(-1)">返回主报表</a>--></td>
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table rules="none" border="1"	style="width:100%;" bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				
				<tr>
					<td height="20" style = "text-align:right;">生成时间：${timeDetail}</td>
				</tr>
				<tr >
					<td align="center" valign="top">
						<table border="0" cellpadding="0" cellspacing="0" style = "width:80%;">
							<tr>
								<td valign="top">
							<!-- 资产列表 -->
							<table  width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<tr>
												
												<td colspan="2">
													<table width="100%" border="1" cellpadding="0" cellspacing="0">
													
													
													
													
													
													
													
													
													
													
																<tr height="15">
																	<td width="17%" class="t2" colspan="5"><b>域风险列表</b></td>

																</tr>
																
																<tr height="15">
																	<td width="17%" class = "r4">域名称</td>
																	<td width="11%" class = "r4">总风险</td>
																	<td width="11%" class = "r4">保密性风险</td>
																	<td width="11%" class = "r4">完整性风险</td>
																	<td width="11%" class = "r4">可用性风险</td>
																	
																</tr>
																<#list report7Table1 as table1>
																	<tr height="15">
																	<td width="17%" class = "r4">${table1.name}</td>
																	<td width="11%" class = "r4">${table1.secret+table1.integrity+table1.usability}</td>
																	<td width="11%" class = "r4">${table1.secret}</td>
																	<td width="8%" class = "r4">${table1.integrity}</td>
																	<td width="8%" class = "r4">${table1.usability}</td>
																	
																</tr>
																</#list>
															<tr height="15">
																	<td width="17%" class="t2" colspan="5"><b>域风险柱状图</b></td>

																</tr>
																<tr height="15">
																	<td width="17%" colspan="5" style = "text-align:left;">其域风险情况柱状图如下：</td>

																	
																</tr>
																
															
																<tr height="15">
																	<td width="17%" colspan="5" style = "text-align:left;">
																	
																	<div id="chart_1">
																	<img src = "${image}"/></div>
</td>
																	
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
