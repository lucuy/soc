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
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<%-- <script type='text/javascript'
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
jQuery(document).ready(function() {
	
	jQuery("#from").validationEngine();
});

//保存数据
function subData() {
	
	$('#chartXInput').val($("#chartX").val());//统计数据
	$('#topNInput').val($("#topN").val());//统计的top
	$("#from").submit();

}
</script>

</head>

<body style="margin-top:2px;">
	<s:form action="insertChar" namespace="/monitor"
		method="post" theme="simple" id="from">
		<s:token></s:token>
	<table width="50%" border="0" cellspacing="0" cellpadding="0"
		style="margin-left: 4px; margin-top: 0px; ">
		<tr>
			<!-- left area -->
			<td valign="top">
				<!--  左侧table-->
				<div class="framdiv" style="height:270px ">
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						height="80%">
						<tr>
							<td class='r2titler' colspan='2' style="font-size: 12px"><b>自定义监控图表信息</b>
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

<input type = "hidden" name = "monitor.monitorId" value = "${monitor.monitorId }" />
									<tr>
										<td align="right" style="font-size: 12px">图表标题：</td>
										<td style="padding-left:3px;" align="left" width="50%"><input
											class="validate[required,custom[onlyLetterNumber],custom[spechars],maxSize[50]] text-input" type="text" style="width:60%" id="chartTitle"
											name="monitor.monitorName" value = "${monitor.monitorName }" /> <span id="chartTitleSpan"
											style="color: red ;font-size: 12px; ">*</span></td>
									</tr>
					
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td align="right" style="font-size: 12px">统计信息：</td>
										<td style="padding-left:3px;" align="left"  width="50%"><select
											 id="chartX" style="width:60%">
											<option value = "LOG_CUSTOMS5"<c:if test="${chatX=='LOG_CUSTOMS5'}">selected="selected"</c:if>
											>设备</option>
											<option value = "LOG_PRIORITY"<c:if test="${chatX=='LOG_PRIORITY'}">selected="selected"</c:if>
											>等级</option>
											<option value = "LOG_CATEGORY"<c:if test="${chatX=='LOG_CATEGORY'}">selected="selected"</c:if>
											>事件类别</option>
											<option value = "LOG_TYPE" <c:if test="${chatX=='LOG_TYPE'}">selected="selected"</c:if>
											>事件类型</option>
											<option value = "LOG_SADDR"<c:if test="${chatX=='LOG_SADDR'}">selected="selected"</c:if>
											>源IP</option>
											<option value = "LOG_DADDR"<c:if test="${chatX=='LOG_DADDR'}">selected="selected"</c:if>
											>目标IP</option>
											<option value = "LOG_SPORT"<c:if test="${chatX=='LOG_SPORT'}">selected="selected"</c:if>
											>源端口</option>
											<option value = "LOG_DPORT"<c:if test="${chatX=='LOG_DPORT'}">selected="selected"</c:if>>目标端口</option>
										</select> 
										</td>
										<input type = "hidden" id = "chartXInput" name = "chatX"/>
									</tr>

									<tr id="tr_password_2">

										<td>
											<div id="message"></div></td>
									</tr>
									<!-- 空行 -->
									<tr id="tr_password_3">
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td align="right" style="font-size: 12px">TOP值：</td>
										<td style="padding-left:3px;" align="left" width="50%"><select
											 id="topN" style="width:60%">
												<option value="5"<c:if test="${topN==5}">selected="selected"</c:if> >TOP5</option>
												<option value="10"<c:if test="${topN==10}">selected="selected"</c:if> >TOP10</option>


										</select>
										</td>
				<input type = "hidden" id = "topNInput" name = "topN"/>
									</tr>
										<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								<!-- 描述-->
						<tr>
									<tr>
										<td align="right" style="font-size: 12px">描述：</td>
										<td style="padding-left:3px;" align="left" width="50%">
										<textarea name="monitor.monitorMemo"
									id="assetMemo" cols="10" rows="7"
									style="width:230px;resize:none;"
									class="validate[max[255]] text-input "  
									>${monitor.monitorMemo}</textarea>										</td>
			
									</tr>
							
								</table></td>
						</tr>





					</table>
				</div></td>
		</tr>
		<tr>
			<td class="td_detail_separator"></td>
		</tr>

		<tr>
			<font color="red"> <span id="msgs"></span>
			</font>
			<td align="right"><input type="button" class="btnyh"
				id="btnSave" value="确  定" onclick="subData();" /> 
			<input type="button" class="btnyh"
				id="btnSave" value="取  消" onclick="window.history.back();" />
			</td>
		</tr>

	</table>
	</s:form>
	<table width="75%" border="0" cellspacing="0" cellpadding="0"
		style="margin-left: 4px;">
		<!-- 空行 -->

	</table>


</body>
</html>
