<!DOCTYPE html>
<html>
<head>
<title>漏洞扫描报表</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="this is my page"/>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
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

<body leftmargin="10" topmargin="15" marginwidth="8" marginheight="8"
	style="width:98%">
	<center>
	
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
			
			<tr>
				<td colspan="7" align="left"><font size="5">漏洞扫描报表</font>
				</td>
				<td align="right"></td>
			</tr>
		</table>
		<center>
			<!-- 主table-->
			<table rules="none" border="1"
				style="width:100%;"
				bordercolor="#ddedfb" bgcolor="#ffffff" cellpadding="0" cellspacing="0">
				<tr>
					<td height="20" style = "text-align:right;">生成时间：${timeDetail}</td>
				</tr>
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">一、综述</td>
				</tr>

				<tr>
					<td height="20"  style="text-align:left;">本报表重点描述了与资产相关的各种漏洞的统计信息和明细信息。<br/>统计信息包括：出现最多的20个高风险漏洞、高风险漏洞数量最多的20个资产、总体漏洞级别分布等统计内容。
						<br/>
						明细信息包括与资产相关的漏洞的各种信息，如：资产IP、漏洞名称、风险级别、CVE编号、漏洞描述等。</td>

				</tr>
				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">二、漏洞综合统计</td>
				</tr>
				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>1、出现最多的20个高风险漏洞</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据导入的漏洞扫描数据，下面以表格方式描述出现最多的20个高风险漏洞。</td>

				</tr>
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>出现最多的20个高风险漏洞</b>
								</td>

							</tr>

							<tr height="15">
								<td width="25%" class="t2">高风险漏洞名称</td>
								<td width="19%" class="t2">出现次数</td>

							</tr>

							<!--- 遍历数据开始 -->
							<tr height="15">
								<td class = "r4">事件级别</td>
								<td class = "r4">数量</td>
							</tr>
							<!--- 遍历数据结束 -->




						</table>
					</td>
				</tr>


				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							2、高风险漏洞数量排名前20名的资产</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据导入的漏洞扫描数据，列出存在高风险漏洞数量最多的前20个资产。</td>

				</tr>
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>高风险漏洞数量排名前20名的资产</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">资产IP</td>
								<td width="20%" class="t2">累计出现数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<tr height="15">
								<td class = "r4">事件级别</td>
								<td class = "r4">数量</td>
							</tr>
							<!--- 遍历数据结束 -->




						</table>
					</td>
				</tr>

				<!--  -->
				<tr>
					<td height="20" style="text-align:left;font-size:15px;"><b>
							3、总体漏洞风险级别分布</b>
					</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">根据导入的漏洞扫描数据，列出总体漏洞风险级别分布情况。</td>

				</tr>
				<tr>
					<td height="20" style="text-align:left;">注：对于漏洞风险级别，1.0级表示低风险，2.0级表示中风险，3.0级表示高风险。</td>

				</tr>
				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">

							<tr height="15">
								<td width="17%" colspan="2"
									style="text-align:center;font-size:13px;"><b>漏洞风险级别分布</b>
								</td>

							</tr>

							<tr height="15">
								<td width="20%" class="t2">风险级别</td>
								<td width="20%" class="t2">累计出现数量</td>

							</tr>

							<!--- 遍历数据开始 -->
							<tr height="15">
								<td class = "r4">事件级别</td>
								<td class = "r4">数量</td>
							</tr>
							<!--- 遍历数据结束 -->

							<tr height="15">
								<td class = "r4">图</td>
								<td class = "r4">图</td>
							</tr>


						</table>
					</td>
				</tr>
				<!-- - -->


				<tr height="30">
					<td height="20"
						style="border:1px solid black;font-size:20px;text-align:left;"
						class="t2">三、漏洞明细</td>
				</tr>

				<tr>
					<td height="20" style="text-align:left;">本节列出导入安管平台中的各种漏洞信息。<br />漏洞的信息包括：资产IP、漏洞名称、危险级别、CVE编号、漏洞描述等信息。</td>

				</tr>
				<tr>
					<td height="20" style="font-size:15px;"><b>
							漏洞明细情况如下所示:</b>
					</td>

				</tr>


				<tr >
					<td align="center" valign="top" colspan="2">
						<table width="60%" border="1" cellpadding="0" cellspacing="0"
							style="margin-left:auto;margin-right:auto;">
							<tr height="15">
								<td width="20%" class="t2">资产IP</td>
								<td width="20%" class="t2">漏洞名称</td>
								<td width="20%" class="t2">简明描述</td>
								<td width="20%" class="t2">危险级别</td>
							</tr>

							<!--- 遍历数据开始 -->
							<tr height="15">
								<td width="20%" class = "r4">资产IP</td>
								<td width="20%" class = "r4">漏洞名称</td>
								<td width="20%" class = "r4">简明描述</td>
								<td width="20%" class = "r4">危险级别</td>
							</tr>
							<!--- 遍历数据结束 -->



						</table>
					</td>
				</tr>


<tr>
					<td height="20" style="text-align:left;">总结：已经达到漏洞明细报表末尾，共计有 283 条漏洞记录。</td>

				</tr>












			</table>




















		</center>
	</center>
</body>
</html>
