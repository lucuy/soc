<!DOCTYPE html>
<html>
  <head>
    <title>安全管理平台月报</title>
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

  <body bgcolor="#ffffff" style = "width:98%;">
	<center>
			

		<table  border="0" cellpadding="0" cellspacing="0" style = "width:100%;">
		
			<tr>
				<td colspan="7" style ="text-align:left;"><font size="5px">安全管理平台月报</font></td>
				<td align="right"><!--<a href="javascript:history.go(-1)">返回主报表</a>--></td>
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table rules="none" border="1" 
				style="width:100%;" bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
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
													
													
													
													
													
													
													
													
													
													
													
																
																
																
																
																<tr height="15">
																	<td width="17%" class = "r4"  rowspan="2">基于事件级别统计</td>
																	<td width="11%" class = "r4">低风险</td>
																	<td width="11%" class = "r4">中低风险</td>
																	<td width="8%" class = "r4">中风险</td>
																	<td width="8%" class = "r4">中高风险</td>
																	<td width="8%" class = "r4">高风险</td>
																	<td width="8%" class = "r4">合计</td>
																</tr>
																
																<#list report2Table1 as table1>
																		<tr height="15">
																	<td width="17%" class = "r4">${table1.priority1}</td>
																	<td width="11%" class = "r4">${table1.priority2}</td>
																	<td width="11%" class = "r4">${table1.priority3}</td>
																	<td width="8%" class = "r4">${table1.priority4}</td>
																	<td width="8%" >${table1.priority5}</td>
																	<td width="8%" class = "r4">${table1.priority1+table1.priority2+table1.priority3+table1.priority4+table1.priority5}</td>
																</tr>
																
																
																<tr height="15">
																	<td width="17%" >与上月发生事件数增长百分比(%)</td>
																	<td width="11%" >${(table1.priority6-table1.priority1)/100}%</td>
																	<td width="8%" >${(table1.priority7-table1.priority2)/100}%</td>
																	<td width="8%" >${(table1.priority8-table1.priority3)/100}%</td>
																	<td width="8%" >${(table1.priority9-table1.priority4)/100}%</td>
																	<td width="8%" >${(table1.priority10-table1.priority5)/100}%</td>																
																	<td width="11%" >${((table1.priority1+table1.priority2+table1.priority3+table1.priority4+table1.priority5)-(table1.priority1+table1.priority2+table1.priority3+table1.priority4+table1.priority5))/100}%</td>
																	
																</tr>
																</#list>
																		
																






																<tr height="15">
																	<td width="17%" class="t2" colspan="7">按安全域统计</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">安全域名称</td>
																	<td width="11%">低风险</td>
																	<td width="11%" >中低风险</td>
																	<td width="8%" >中风险</td>
																	<td width="8%" >中高风险</td>
																	<td width="8%" >高风险</td>
																	<td width="8%" >合计</td>
																	
																</tr>
																<#list report2Table2 as table2>
																	<tr height="15">
																	<td width="17%">${table2.asset}</td>
																	<td width="8%" >${table2.priority1}</td>
																	<td width="11%">${table2.priority2}</td>
																	<td width="11%" >${table2.priority3}</td>
																	<td width="8%" >${table2.priority4}</td>
																	<td width="8%" >${table2.priority5}</td>
																	<td width="8%" >${table2.priority1+table2.priority2+table2.priority3+table2.priority4+table2.priority5}</td>
																	
																</tr>
																</#list>
																
																<tr height="15">
																	<td width="17%" class="t2" colspan="7">事件按设备统计</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">设备类型</td>
																	<td width="11%">低风险</td>
																	<td width="11%" >中低风险</td>
																	<td width="8%" >中风险</td>
																	<td width="8%" >中高风险</td>
																	<td width="8%" >高风险</td>
																	<td width="8%" >合计</td>
																
				
	</tr>
																<#list report2Table4 as table4>	
																
																	<tr height="15">
																	<td width="17%">${table4.asset}</td>
																	<td width="8%" >${table4.priority1}</td>
																	<td width="11%">${table4.priority2}</td>
																	<td width="11%">${table4.priority3}</td>
																	<td width="8%" >${table4.priority4}</td>
																	<td width="8%" >${table4.priority5}</td>
																	<td width="8%" >${table4.priority1+table4.priority2+table4.priority3+table4.priority4+table4.priority5}</td>
																	
																</tr>
																</#list>




														<tr height="15">
																	<td width="17%" class="t2" colspan="7">事件统计TOP10</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">设备名称</td>
																	<td width="11%">低风险</td>
																	<td width="11%" >中低风险</td>
																	<td width="8%" >中风险</td>
																	<td width="8%" >中高风险</td>
																	<td width="8%" >高风险</td>
																	<td width="8%" >合计</td>
																	
																	</tr>
																	<#list report2Table3?sort_by("total")?reverse as table3>	
																
																	<tr height="15">
																	<td width="17%">${table3.asset}</td>
																	<td width="8%" >${table3.priority1}</td>
																	<td width="11%">${table3.priority2}</td>
																	<td width="11%">${table3.priority3}</td>
																	<td width="8%" >${table3.priority4}</td>
																	<td width="8%" >${table3.priority5}</td>
																	<td width="8%" >${table3.total}</td>
																	
																</tr>
																</#list>
																	
																	
																	
																	
																	
																




<tr height="15">
																	<td width="17%" class="t2" colspan="7">发生事件次数统计TOP20</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">事件类型</td>
																	<td width="11%">事件级别</td>
																	<td width="11%" >目的地址</td>
																	<td width="8%" >源设备名称</td>
																	<td width="8%" colspan = "3">事件描述</td>
																	</tr>
																<#list report2Table5 as table5>	
																
																	<tr height="15">
																	<td width="17%">${table5.type}</td>
																	<td width="11%">${table5.priority}</td>
																	<td width="11%" >${table5.devaddr}</td>
																	<td width="8%" >${table5.name}</td>
																	<td width="8%" colspan = "3">${table5.c}</td>
																</tr>
													</#list>	


<tr height="15">
																	<td width="17%" class="t2" colspan="7">高风险设备统计TOP20</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">设备名称</td>
																	<td width="11%">高风险事件类型</td>
																	<td width="11%" >高风险事件次数</td>
																	<td width="8%" colspan = "4">事件描述</td>
																		</tr>
																<#list report2Table10 as table10>	
															
																	<tr height="15">
																	<td width="17%">${table10.name}</td>
																	
																	<td width="11%" >${table10.type}</td>
																	<td width="8%" >${table10.sum}</td>
																	<td width="8%" colspan = "4">${table10.c}</td>
																</tr>
													</#list>	



<tr height="15">
																	<td width="17%" class="t2" colspan="7">中高风险设备统计TOP20</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">设备名称</td>
																	<td width="11%">中高风险事件类型</td>
																	<td width="11%" >中高风险事件次数</td>
																	<td width="8%" colspan = "4">事件描述</td>
																		</tr>
																<#list report2Table9 as table9>	
															
																	<tr height="15">
																	<td width="17%">${table9.name}</td>
																	
																	<td width="11%" >${table9.type}</td>
																	<td width="8%" >${table9.sum}</td>
																	<td width="8%" colspan = "4">${table9.c}</td>
																</tr>
													</#list>	



<tr height="15">
																	<td width="17%" class="t2" colspan="7">中风险设备统计TOP20</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">设备名称</td>
																	<td width="11%">中风险事件类型</td>
																	<td width="11%" >中风险事件次数</td>
																	<td width="8%" colspan = "4">事件描述</td>
																	</tr>
																<#list report2Table8 as table8>	
																
																	<tr height="15">
																	<td width="17%">${table8.name}</td>
																	
																	<td width="11%" >${table8.type}</td>
																	<td width="8%" >${table8.sum}</td>
																	<td width="8%" colspan = "4">${table8.c}</td>
																</tr>
													</#list>	


<tr height="15">
																	<td width="17%" class="t2" colspan="7">中低风险设备统计TOP20</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">设备名称</td>
																	<td width="11%">中低风险事件类型</td>
																	<td width="11%" >中低风险事件次数</td>
																	<td width="8%" colspan = "4">事件描述</td>
																	</tr>
																<#list report2Table7 as table7>	
																
																	<tr height="15">
																	<td width="17%">${table7.name}</td>
																	
																	<td width="11%" >${table7.type}</td>
																	<td width="8%" >${table7.sum}</td>
																	<td width="8%" colspan = "4">${table7.c}</td>
																</tr>
													</#list>	


<tr height="15">
																	<td width="17%" class="t2" colspan="7">低风险设备统计TOP20</td>

																</tr>
																
																<tr height="15">
																	<td width="17%">设备名称</td>
																	<td width="11%">低风险事件类型</td>
																	<td width="11%" >低风险事件次数</td>
																	<td width="8%" colspan = "4">事件描述</td>
																	</tr>
																<#list report2Table6 as table6>	
																
																	<tr height="15">
																	<td width="17%">${table6.name}</td>
																	
																	<td width="11%" >${table6.type}</td>
																	<td width="8%" >${table6.sum}</td>
																	<td width="8%" colspan = "4">${table6.c}</td>
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
