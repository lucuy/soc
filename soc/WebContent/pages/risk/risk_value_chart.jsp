<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'risk_value_chart.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
<%-- <link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">--%>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>--%>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>
<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
<style type="text/css">
.framdiv {
	border: 1px solid #5AA4D1;
	font-size: 12px;
	height: 400px;
}
</style>
<script type="text/javascript">
<%--$(document).ready(
			function() {

				$("#chartX").change(
						function() {
							var option1 = "<option value=''>--请选择--</option>";
							var char = $("#chartX").val();

							var i = parseInt(char);
							switch (i) {
							case 0:
								option1 += "<option value='1'>设备类型</option>"
										+ "<option value='2'>资产值</option>"
										+ "<option value='3'>威胁值</option>"
										+ "<option value='4'>脆弱性值</option>"
										+ "<option value='5'>风险值</option>";
								break;
							case 1:
								option1 += "<option value='0'>安全域</option>"
										+ "<option value='2'>资产值</option>"
										+ "<option value='3'>威胁值</option>"
										+ "<option value='4'>脆弱性值</option>"
										+ "<option value='5'>风险值</option>";
								break;
							case 2:
								option1 += "<option value='1'>设备类型</option>"
										+ "<option value='0'>安全域</option>"
										+ "<option value='3'>威胁值</option>"
										+ "<option value='4'>脆弱性值</option>"
										+ "<option value='5'>风险值</option>";
								break;
							case 3:
								option1 += "<option value='1'>设备类型</option>"
										+ "<option value='2'>资产值</option>"
										+ "<option value='0'>安全域</option>"
										+ "<option value='4'>脆弱性值</option>"
										+ "<option value='5'>风险值</option>";
								break;
							case 4:
								option1 += "<option value='1'>设备类型</option>"
										+ "<option value='2'>资产值</option>"
										+ "<option value='3'>威胁值</option>"
										+ "<option value='0'>安全域</option>"
										+ "<option value='5'>风险值</option>";
								break;
							case 5:
								option1 += "<option value='1'>设备类型</option>"
										+ "<option value='2'>资产值</option>"
										+ "<option value='3'>威胁值</option>"
										+ "<option value='4'>脆弱性值</option>"
										+ "<option value='0'>安全域</option>";
								break;
							default:
								option1 += "";
								break;
							}
							$("#chartY").html(option1);
						});

			});--%>
	function assetChart() {
		
		var charX = document.getElementById("chartX").value;
		var charY = document.getElementById("chartY").value;
		var falg = document.getElementById("falg").value;
		var topN = document.getElementById("topN").value;
	//	var char = document.getElementById("chartName").value;

		
			if (charX == "") {
				alert("请选择X轴");
			} else {
				
				if (charY == "") {
					alert("请选择Y轴");
				} else {
					
					location.href = "${ctx}/assetRiskGroup/queryAllAssets.action?falg="
							+ falg
							+ "&chartX="
							+ charX
							+ "&chartY=" + charY + "&topN=" + topN;
				}
			}
		
		
	}
</script>
</head>

<body style="margin-top:2px;">

	


		<table width="50%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px; " >
			<tr>
				<!-- left area -->
				<td  valign="top">
					<!--  左侧table-->
					<div class="framdiv" style="height:300px ">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							height="80%">
							<tr>
								<td class='r2titler' colspan='2' style="font-size: 12px" ><b>选择查看图表信息</b>
								</td>
							</tr>
							<tr>
								<td>
								
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td width="20%"></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<%-- <tr>
											<td align="right">请选择查看分组名称：</td>
											<td style="padding-left:3px;" align="left" width="50%">
												<select align="center" id="chartName" style="width:75%"
												><option value="">--请选择--</option>
													<option value="0">安全域</option>
													<option value="1">设备类型</option>
													<option value="2">资产值</option>
													<option value="3">威胁值</option>
													<option value="4">脆弱性值</option>
													<option value="5">风险值</option>
											</select></td>

										</tr>--%>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right" style="font-size: 12px">选择X轴：</td>
											<td style="padding-left:3px;" align="left" width="50%">
												<select align="center" id="chartX" style="width:75%"
												><option value="">--请选择--</option>
													<option value="0">安全域</option>
													<option value="1">设备类型</option>	
											</select></td>

										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>



										<tr>
											<td align="right" style="font-size: 12px">选择Y轴：</td>
											<td style="padding-left:3px;" align="left" width="50%">
												<select align="center" id="chartY" style="width:75%"
												>
												<option value="">--请选择--</option>
													<option value="2">资产值</option>
													<option value="3">威胁值</option>
													<option value="4">脆弱性值</option>
													<option value="5">风险值</option>

											</select></td>

										</tr>

										<tr id="tr_password_2">
											
											<td>
												<div id="message"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr id="tr_password_3">
											<td class="td_detail_separator"></td>
										</tr>

									
										<tr>
											<td align="right" style="font-size: 12px">选择所展示的图形：</td>
											<td style="padding-left:3px;" align="left" width="50%">
												<select align="center" name="falg" id="falg" style="width:75%"
												>
												<option value="1">柱状图</option>
			<option value="2">饼状图</option>
												
											</select></td>

										</tr>
									<!-- 空行 -->
										<tr id="tr_password_3">
											<td class="td_detail_separator"></td>
										</tr>
										
										<tr>
											<td align="right" style="font-size: 12px" >选择TOP值：</td>
											<td style="padding-left:3px;" align="left" width="50%">
												<select align="center"  id="topN" style="width:75%"
												>
													<option value="5">TOP5</option>
			<option value="10">TOP10</option>
													
												
											</select></td>

										</tr>

									</table>
								</td>
							</tr>





						</table>
					</div>
				</td>
			</tr>
				<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
			<font color="red"> <span id="msgs"></span></font>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="查看" onclick="assetChart();" /><input type="button" class="btnyh"
					id="btnSave" value="返回" onclick="parent.mainFrame.location.href='${ctx}/assetRiskGroup/query.action';" style="margin-left: 10px" /></td>
			</tr>

		</table>
		<table width="75%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<!-- 空行 -->
		
		</table>
	

</body>
</html>
