<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript">

	jQuery(document).ready(function() {
		jQuery("#setting-storage").validationEngine();
	});
	function subdata() {
		$('#jdbcCacheValue').val($("input[name='jdbccache']:checked").val());
		$('#jdbcBatchValue').val($("input[name='jdbcbatch']:checked").val());
		$('#setting-storage').submit();
	}
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="setting-storage" action="saveStorage.action"
		namespace="/settingStorage" method="post" theme="simple"
		name="setting-storage">
		<s:hidden name="jdbcCache" id="jdbcCacheValue" value="0" />
		<s:hidden name="jdbcBatch" id="jdbcBatchValue" value="0" />
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>配置数据源</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font></td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font>
								</td>
							</tr>

							<tr>
								<td>
									<table width="98%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- <tr>
											<td align="right">数据库类型：&nbsp;&nbsp;</td>
											<td><label for="select"></label> <select name="select"
												id="select">
													<option value="PostgreSQL" selected="selected">PostgreSQL</option>
													<option value="MySQL">MySQL</option>
													<option value="Oracle">Oracle</option>
													<option value="SQL Server">SQL Server</option>
													<option value="Premium">Premium</option>
													<option value="SQLite">SQLite</option>
													<option value="Data Modeler">Data Modeler</option>
											</select>
											</td>
										</tr>
										 -->
										<tr>
											<td width="30%">数据库类型</td>
											<td align="left"><input name="jdbcType" id="jdbcType"
												value="${jdbcType}" type="text" disabled="disabled" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td>用户名</td>
											<td><input name="jdbcName" id="jdbcName"
												value="${jdbcName}" type="text"
												class="validate[required,custom[validateLength],custom[spechars]] text-input " />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>密码</td>
											<td><input name="jdbcPw" id="jdbcPw" value="${jdbcPw}"
												type="password" /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>超时重连</td>
											<td><input name="jdbcTime" id="jdbcTime"
												value="${jdbcTime}" type="text" class="validate[custom[settingNumber]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>线程数</td>
											<td><input name="jdbcNo" id="jdbcNo" value="${jdbcNo}"
												type="text" class="validate[custom[settingNumber]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>批量数</td>
											<td><input name="jdbcBatchNo" id="jdbcBatchNo"
												value="${jdbcBatchNo}" type="text" class="validate[custom[settingNumber]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>是否开启批量</td>
											<td><label> <input type="radio" name="jdbcbatch"
													value="1" id="jdbcBatch1"
													<c:if test="${jdbcBatch==1}">checked="checked"</c:if> /> 是</label>
												<label> <input type="radio" name="jdbcbatch"
													value="0" id="jdbcBatch2"
													<c:if test="${jdbcBatch==0}">checked="checked"</c:if> /> 否</label>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>批量数</td>
											<td><input name="jdbcBatchNo2" id="jdbcBatchNo2"
												value="${jdbcBatchNo2}" type="text" class="validate[custom[settingNumber]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>是否开启缓存</td>
											<td><label> <input type="radio" name="jdbccache"
													value="1" id="jdbcCache1"
													<c:if test="${jdbcCache==1}">checked="checked"</c:if> /> 是</label>
												<label> <input type="radio" name="jdbccache"
													value="0" id="jdbcCache2"
													<c:if test="${jdbcCache==0}">checked="checked"</c:if> /> 否</label>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>JDBC URL</td>
											<td><input name="jdbcUrl" id="jdbcUrl"
												value="${jdbcUrl}" type="text" size="85" /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td colspan="2" style="color:#F00" align="center">声明！JDBC
												URL的格式应为：jdbc:PostgreSQL://192.168.0.1:3306/soc?useUnicode=true&characterEncoding=UTF-8</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div></td>
			</tr>

			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="保存设置" onclick="subdata()" /></td>
			</tr>
		</table>
	</s:form>
</body>
</html>