<!DOCTYPE html>
<html>
<head>
<title>陕西台安管平台事件周报</title>

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


<body bgcolor="#ffffff" leftmargin="10" topmargin="15" marginwidth="8"
	marginheight="8" style = "width:98%;">
	
		

		<table  border="0" cellpadding="0" cellspacing="0" style = "width:100%;">
			<tr>
				<td colspan="9" style = "text-align:left"><font size="5pt">陕西台安管平台事件周报</font></td>
				
			</tr>
		</table>
		<!-- 主table-->
			<table  border="1" style="width:100%" bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				<tr>
					<td height="20" style = "text-align:right;">生成日期:${timeDetail}   </td>
				</tr>
				<tr>
					<td height="20" style = "text-align:left;">   本报表显在设备日志中，事件发生次数的明细情况。下表是以列表形式列出设备明细情况。</td>
				</tr>
				<tr id="cd_0" >
					<td align="center" valign="top">
						<table  border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
						
								<td valign="top">
							<!-- 资产列表 -->
							<table id="cd_10" width="100%"  border="0" cellpadding="0" cellspacing="0">
							
							
									<tr>
									<td>
										<table  border="0" cellpadding="0" cellspacing="0" width="100%">
											
									
											
										<tr>
																		<td colspan="2" >
													<table width="100%" border="1" cellpadding="0" cellspacing="0" style = "margin-left:auto;margin-right:auto;">
										
													
																
																
																<tr height="15">
																	<td width="5%" class="t2">发生时间</td>
																<td width="19%" class="t2" colspan = "3">中高级事件</td>
												
																	<td width="19%" class="t2" colspan = "3">中级中高级事件</td>

																</tr>
																<tr height="15">
																	<td width="5%" class="t2">事件统计</td>
																	<td width="19%" class="t2" rowspan="2">上周</td>
																	<td width="11%" class="t2"  rowspan="2">本周</td>
																	<td width="11%"  class="t2">环比增减</td>
																	<td width="15%" class="t2" rowspan="2">上周</td>
																	<td width="19%" class="t2"  rowspan="2">本周</td>
																	<td width="11%"  class="t2" >环比增减</td>
																	
																	
																</tr>
																<tr height="15">
																	<td width="5%" class="t2">事件类型</td>
																	
																	<td width="8%"  class="t2">（%）</td>
																	
																	<td width="8%"  class="t2">（%）</td>
																
																	
																</tr>
<#list report14Table1 as table1>
																	<tr height="15">
																	<td width="17%">${table1.category}</td>
																	 <td width="11%">${table1.seriouseventslastweek}</td>  
																	<td width="11%" >${table1.seriouseventsthisweek}</td>
																	<td width="8%" >${table1.QoQChangeSerious}</td>
																	 <td width="11%">${table1.intermediateeventslastweek}</td>  
																	<td width="11%" >${table1.intermediateeventsthisweek}</td>
																	<td width="8%" >${table1.QoQChangeIntermediate}</td>
																</tr>
																</#list> 
																
															</table></td>
												
											</tr>
								
							
							
							
							
							
							
							<tr height="15"><td colspan = "4"></td></tr>
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
								<tr>
									<td>
										<table  border="0" cellpadding="0" cellspacing="0" width="100%">
											
									
											
										<tr>
												
												<td colspan="2">
													<table width="100%" border="1" cellpadding="0"  cellspacing="0" style = "margin-left:auto;margin-right:auto;">
										
													
																
																
													
																
								<#if category10??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">病毒木马</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category10Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category10 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">该类事件可以明确归为病毒木马的范畴内，防病毒产品及其它安全产品有时候会发现一些新的病毒木马样本或行为，这类事件表明了可能有病毒木马存在，也可能是误报。  建议一：升级安全管理平台的知识库，增强病毒木马的识别判断能力。建议二：查看事件的长描述，获取更多信息。建议三：加强病毒防范力度，进行全面病毒扫描。</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>

																


															
											
											</#if>
											
											<#if category11??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">未知类型</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category11Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category11 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">暂无，请留意最新的知识更新  如果经过调查判断，发现其中某些事件对与安全管理没有实际意义的话，比如一些已知的噪声事件。可以在事件采集的时候进行过滤。</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>

																


															
											
											</#if>
											
											
											
											<#if category12??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">系统状态</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category12Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category12 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																	<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">暂无</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>

																


														
											
											</#if>
											
											
											
											
											
											<#if category13??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">扫描探测</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category13Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category13 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">暂无</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>

																


															
											
											</#if>
											
											
											
											
											
											<#if category14??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">拒绝服务</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category14Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category14 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">暂无</td>
																	
																	
																	
																</tr>

																


															
											
											</#if>
											
											
										<#if category15??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">规避</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category15Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category15 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">暂无</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>

																


															
											
											</#if>	
											
											
											
											<#if category16??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">认证授权</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category16Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category16 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">暂无</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>

																


														
											
											</#if>
											
											
											
											
										<#if category17??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">应用漏洞</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category17Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category17 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list> 
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">暂无</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3"> </td>
																	
																	
																	
																</tr>

																


															
											
											</#if>	
											
											
											<#if category18??>
															<tr height="15">
																
																	<td width="7%" class="t2">事件类型</td>
																	
																	<td width="19%" class="t2"  colspan = "3">非授权访问</td>
																	
																	
																	
																</tr>
																	
																	
																	
																<tr height="15">
																	<td width="5%" class="t2" rowspan ="${category18Size}" >最受影响的IP地址</td>
																	<td width="19%" >IP地址</td>
																	<td width="19%"  >资产名称</td>
																	<td width="19%" >所属业务系统</td>
																	
																	
																	
																</tr>
																<#list category18 as table2>
																<tr height="15">
																
															
																	<td width="19%" >${table2.assetip}</td>
																	<td width="19%"  >${table2.assetname}</td>
																		<td width="19%" >	<#if table2.groupname??>${table2.groupname} <#else>已删除资产</#if></td>
																	
																	</#list>  
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件危害分析</td>
																	
																	<td width="19%"  colspan = "3">这些事件可能是以前没有遇到过的策略违反事件，也可能是遇到过但没有被识别归纳的事件。  建议：在加强监管的同时，也要组织成员加强安全意识和安全制度方面的教育。</td>
																	
																	
																	
																</tr>
																<tr height="15">
																<td width="5%" class="t2">事件处理方式</td>
																	
																	<td width="19%"  colspan = "3">    </td>
																	
																	
																	
																</tr>

																


															</table></td>
											</tr>
											
											</#if>
											
											
											
											
										</table></td>
								</tr>
							</table>
								</td>
								</tr>
							</table>
							</td>
							</tr>
						</table></td>
				</tr>
			</table>

	
	
		</td>
					</tr>
					
	</table>
	
	
	
	
	
</body>
</html>