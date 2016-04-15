<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="mainoffer.inc"%>
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
	
		<title>自定义报表</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">

		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		</script>
	<script type="text/javascript">
		function sub(){
			document.ReportCustomForm.submit();
		}
		function cancel(){
			window.location = "${ctx}/reportCustom.do?method=initPage";
		}
	</script>
	</head>

	<body>
		<html:form action="reportCustom.do?method=doupdate" method="post">
		<html:hidden property="rc.id" name="ReportCustomForm"/>
				<!--  主table-->
		<table width="45%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
				<tr>
					<td width="500px" valign="top">
						<div class="framDiv">

							<!--  左侧table-->
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td class="r2titler" colspan="2">
										<b>自定义报表</b>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>

								<tr>
									<td align="right" width="25%">
										报表名称：
									</td>
									<td style="padding-left: 10px">
										<html:text property="rc.name" name="ReportCustomForm"
											style="width:225px;"></html:text>
									</td>
								</tr>
								<tr>
									<td class="td_detail_separator">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td align="right" width="25%">
										sql语句：
									</td>
									<td style="padding-left: 10px">
										<html:textarea property="rc.customSql" name="ReportCustomForm"
											rows="4" cols="30"></html:textarea>
									</td>
								</tr>
								<tr>
									<td class="td_detail_separator">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td align="right" width="25%">
										描述：
									</td>
									<td style="padding-left: 10px">
										<html:textarea property="rc.memo" name="ReportCustomForm"
											rows="4" cols="30"></html:textarea>
									</td>
								</tr>
								
								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>
							</table>
						</div>
				
				<tr>
					<td align="left">
						<table width="45%" border="0" cellspacing="0" cellpadding="0">
							<!-- 空行 -->
							<tr>
								<td height="8">
								</td>
							</tr>

							<tr>
								<td align="left">
									<input type="button" class="btnyh" id="btnSave" value="保  存"
										onclick="sub();" />
									<input type="button" class="btnyh" id="btnCancel" value="取  消"
										onclick="cancel()" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
