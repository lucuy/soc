<!DOCTYPE html>
<html>
<head>
<title>资产类型统计报表</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="this is my page" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
 

</head>
<body>
		<table width="99%" border="1" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td colspan="5" align="left"><font size="5px">安全公告数据表</font></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="1" cellspacing="1" cellpadding="0"
						class="tab2">

						<tr hight="10" >
							<td width="10%">
								序号
							</td>
							<td width="25%">
								标题
							</td>
							<td width="16%">
								发布人
							</td>		
							<td width="15%">
								发布日期
							</td>
							<td width="">
								来源
							</td>	
						</tr>
						
						
						
						<!-- 开始遍历 -->
						<#list list as being>
							<being>
								<tr hight = "10" >
									<td width="10%">
										${being.securityId}
									</td>
									<td width="25%">
										${being.securityTitle}
									</td>
									<td width="16%">
										${being.publisher}
									</td>
									<td width="15%">
										${being.securityDate?string("yyyy-MM-dd")}
									</td>
									<td width="">
										${being.source}
									</td>
								</tr>
							</being>
						</#list>
						<!-- 结束遍历 -->
						
						
						
					</table>
				</td>
			</tr>
		</table>
</body>
</html>
