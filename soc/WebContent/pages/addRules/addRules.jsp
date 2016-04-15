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
<%-- <%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>--%>

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
		var rulesFile = $("#rules");
		if (rulesFile.val() == "") {
			alert("文件未选择！");
			return;
		}
		else
		{
		  $("form").submit();

		}
	}
	function checkValue()
	{
	   var tar = $("#rules").val();
	   if(tar != "")
	   {
	       var suffix = tar.substring(tar.lastIndexOf(".")+1,tar.length);
	       if(suffix == "xml")
	       {
	           $("#tarerror").text("");
	           $("#btnSave").removeAttr("disabled");
	       }
	       else
	       {
	           $("#btnSave").attr("disabled","disabled");
	           $("#tarerror").text("请选择正确的xml");
	       }
	   }
	}
	
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="addRules" action="addRules" namespace="/addRules"
		method="post" theme="simple" name="rules"
		enctype="multipart/form-data">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>添加解析规则</b>&nbsp;&nbsp;&nbsp; <font
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
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<tr>
											<td width="30%">请选择需要添加的解析规则文件</td>

											<td colspan="2"><input style="width:270px;" id="rules" name="rules"
												type="file" onchange="checkValue()"/>&nbsp;&nbsp;<span><font color="red" id="tarerror">${msg}</font></span></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
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
					value="添&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;加" onclick="subdata();" disabled="disabled"  />
				</td>
			</tr>
		</table>
  </s:form>
</body>
</html>