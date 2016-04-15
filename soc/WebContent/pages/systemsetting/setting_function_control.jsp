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
<h<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>.js"></script>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript' src="${ctx}/scripts/ajaxfileupload.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link type="text/css"
    href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
    rel="stylesheet" />
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
<script>
	function subdata() {
		if (confirm("此操作不可逆，请谨慎操作！")) {
			$("#functionColtrolForm").submit();
			
		}
		alert("请等待系统重启...");
	}
	    
</script>
</head>

<body style="margin-top: 2px" >
	<s:form id="functionColtrolForm" action="functionColtrolAction" namespace="/settingOEM"
		method="post" theme="simple" name="oemTar"	>
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>功能控制</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font>
								</td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font></td>
							</tr>

							<tr>
								<td>
									<table width="94%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<!-- 空行 -->
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr style = "text-align:center">
										
											<td width="50%"><input type = "radio" name = "functionControl" value = "0" <c:if test="${functionControl eq '0' }">checked="checked"</c:if> />JSOC-EM</td>
											<td width="50%"><input type = "radio" name = "functionControl" value = "1" <c:if test="${functionControl eq '1' }">checked="checked"</c:if>/>JSOC-RM</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr style = "text-align:center">
											<td width="50%"><input type = "radio" name = "functionControl" value = "2" <c:if test="${functionControl eq '2' }">checked="checked"</c:if>/>JSOC-NM</td>
											<td width="50%"><input type = "radio" name = "functionControl" value = "3" <c:if test="${functionControl eq '3' }">checked="checked"</c:if>/>JSOC-CM</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr style = "text-align:center">
											<td width="50%">&nbsp;<input type = "radio" name = "functionControl" value = "4" <c:if test="${functionControl eq '4' }">checked="checked"</c:if>/>JSOC-ALL</td>
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
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="确 定" onclick="subdata();"   />
				</td>
			</tr>
		</table>
  </s:form>
   
    
</body>
</html>