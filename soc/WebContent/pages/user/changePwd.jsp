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

<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.select.js"></script>

<script language="javascript">
	jQuery(document).ready(function() {
		jQuery("#userForm").validationEngine();
	});
	function subData() {
		checkPolicy();
		if ($.trim($("#message").html()) == "" || $("#message").html() == null) {
			var oldPassword = $("#oldPassword").val();
			var newPassword = $("#newPassword");
			var confirmPassword = $("#confirmPassword");
			if ($.trim(newPassword.val()) != ""
					|| $.trim(confirmPassword.val()) != "") {
				if ($.trim(oldPassword) == "") {
					$("#showMessage").empty();
					var fontNode = $("<font color='red'>*请输入密码</font>");
					$("#showMessage").append(fontNode);
					return;
				}
				if (newPassword.val() != confirmPassword.val()) {
					alert("两次密码不一致请重新输入");
					confirmPassword.focus();
					return;
				}
			}
				$("#userForm").attr("action",
						"${ctx}/login/changePassword.action");
				$("#userForm").submit();
		} else {
			$("#newPassword").focus();
			return;
		}
	}
	/*function check() {
		var oldPassword = $("#oldPassword").val();
		oldPassword=escape(oldPassword);
		if ($.trim(oldPassword) != "" && oldPassword != " ") {
			$
					.get(
							"${ctx}/user/checkPassword.action?user.userPassword="
									+ oldPassword,
							null,
							function(data) {
								$("#showMessage").empty();
								if (data != "") {
									var fontNode = $("<font color='red'>"
											+ data + "</font>");
									$("#showMessage").append(fontNode);
									$("#newPassword").attr("disabled", true);
									$("#confirmPassword")
											.attr("disabled", true);
									return;
								} else {
									var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
									$("#showMessage").append(img).show("slow");
									$("#newPassword").attr("disabled", false);
									$("#confirmPassword").attr("disabled",
											false);
									$("#newPassword").focus();
								}
							});
		} else {
			$("#showMessage").empty();
			$("#newPassword").attr("disabled", true);
			$("#confirmPassword").attr("disabled", true);
			return;
		}
	}*/
	
	
	function check() {
		var oldPassword = $("#oldPassword").val();
		//oldPassword=escape(oldPassword);
		if ($.trim(oldPassword) != "" && oldPassword != " ") {
			var pass={oldPassword:oldPassword}
			$
					.post(
							"${ctx}/user/checkPassword.action",
							pass,
							function(data) {
								$("#showMessage").empty();
								if (data != "") {
									var fontNode = $("<font color='red'>"
											+ data + "</font>");
									$("#showMessage").append(fontNode);
									$("#newPassword").attr("disabled", true);
									$("#confirmPassword")
											.attr("disabled", true);
									return;
								} else {
									var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
									$("#showMessage").append(img).show("slow");
									$("#newPassword").attr("disabled", false);
									$("#confirmPassword").attr("disabled",
											false);
									$("#newPassword").focus();
								}
							});
		} else {
			$("#showMessage").empty();
			$("#newPassword").attr("disabled", true);
			$("#confirmPassword").attr("disabled", true);
			return;
		}
	}

	//密码策略进行检查
	function checkPolicy() {
		var newPassword = $("#newPassword").val();
		/* var empId = $("#empId").val(); */

		if ($.trim(newPassword) != "") {
			/* $.get("${ctx}/user/checkPwdPolicy.action?newPassword="+newPassword,null,function(data){
				$("#message").empty();
				$("#icon").empty();
				if(data!=""){
					var fontNode=$("<font color='red'>"+data+"</font>");
					$("#message").append(fontNode);
					return;
				}else{
					var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
					$("#icon").append(img).show("slow");
					$("#confirmPassword").focus();
				}
			}); */

			$
					.ajax({
						type : "post",
						url : "checkPwdPolicy.action",
						dataType : "text",
						data : "&newPassword=" + newPassword,
						success : function(data) {
							$("#message").empty();
							$("#icon").empty();
							if (data != "") {
								var fontNode = $("<font color='red'>" + data
										+ "</font>");
								$("#message").append(fontNode);
								return;
							} else {
								var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
								$("#icon").append(img).show("slow");
								$("#confirmPassword").focus();
							}
						}
					});
		} else {
			$("#message").empty();
			$("#icon").empty();
			return;
		}
	}
</script>

</head>

<body style="margin-top: 2px">
	<center>
		<input type="hidden" name="user.userId" id="empId"
			value="${sessionScope.SOC_LOGON_USER.userId}" />
		<form action="" method="post" theme="simple" id="userForm"
			name="userForm">
			<!--  主table-->
			<table width="600px" align="center" border="0" cellspacing="0"
				cellpadding="0" style="margin-top: 100px">
				<tr>
					<td style="width:600px" valign="top">
						<div class="framDiv">

							<!--  左侧table-->
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td class="r2titler"><b>修改密码</b></td>
								</tr>

								<!-- 分隔符 -->
								<tr>
									<td height="3px"></td>
								</tr>

								<tr>
									<td>
										<!-- 用户信息内容 -->
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											style="margin-left: 80px;margin-top: 20px">
											<tr>
												<td colspan="3">
													<div class="news-success"
														style="font-size: 12px;padding-left: 40px" align="left">
														${requestScope.setSuccess}</div></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>

											<!-- 用户名 -->
											<tr>
												<td align="right">用户名：</td>
												<td width="20px"></td>
												<td align="left">
													${sessionScope.SOC_LOGON_USER.userLoginName}</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>


											<!-- 密码 -->
											<tr>
												<td align="right"><span class="spanred">*</span>旧密码：</td>
												<td width="20px"></td>
												<td align="left"><input type="password"
													name="user.userPassword" id="oldPassword" size="40"
													style="width:270px" onblur="check()" autocomplete="off"/><span
													id="showMessage"></span></td>
											</tr>

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>

											<!--新 密码 -->
											<tr>
												<td align="right" valign="top">新密码：</td>
												<td width="20px"></td>
												<td align="left"><input type="password"
													disabled="disabled" name="newPassword" id="newPassword"
													size="40" style="width:270px" onblur="checkPolicy()" autocomplete="off"
													class="validate[custom[validateSpase]] text-input " /><span
													id="icon"></span>
													<div id="message"></div> <br /></td>
											</tr>
											<tr>
												<td></td>
												<td>
													<div>
														<font color="gray" size="2px">${requestScope.pwdPolicyInfo}</font>
													</div></td>
											</tr>

											<!-- 确认密码 -->
											<tr>
												<td align="right" valign="middle">确认密码：</td>
												<td width="20px"></td>
												<td align="left"><input type="password" autocomplete="off"
													disabled="disabled" name="password2" id="confirmPassword"
													size="40" style="width: 270px"
													class="validate[equals[newPassword]]/>" /></td>
											</tr>
											<tr>
												<td height="2px"></td>
											</tr>


										</table></td>
								</tr>

								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
							</table>
						</div></td>
				</tr>
				<tr>
					<td>
						<table width="40%" border="0" cellspacing="0" align="center"
							cellpadding="0">
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr>
								<td align="center" style="padding-right:25px"><input
									type="button" class="btnyh" id="btnSave" value="保  存"
									onclick="subData();" /> <input type="button" class="btnyh"
									id="btnCancel" value="取  消"
									onclick="window.history.go(-1)" />
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
