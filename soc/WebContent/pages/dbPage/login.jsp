<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>捷成安全管理与综合审计系统</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.ico" />
<link rel="stylesheet" href="${ctx}/css/login.css" type="text/css" />
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				submitForm();
			}
		}
	});

	/**
	 * 换验证码图片
	 */
	function change(img) {
		img.src = '${ctx}/pages/commons/image.jsp?seed=' + Math.random();
	}

	function submitForm() {
		var name = $.trim($("#loginName").val());
		var pass = $.trim($("#password").val());
		if (name == "") {
			$("#codeMessage").html("用户名不能为空");
			$("#loginName").focus();
			return;
		}
		if (pass == "") {
			$("#codeMessage").html("密码不能为空");
			$("#password").focus();
			return;
		}
		if ($("#imgCode").val() == "") {
			$("#codeMessage").html("验证码不能为空");
			$("#imgCode").focus();
			return;
		}
		document.forms[0].submit();
	}
</script>
</head>

<body bgcolor="#3c7fb4">
	<div class="container2">
		<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="100px">&nbsp;</td>
			</tr>
			<!-- 
			<tr>
				<td height="187px" style="font-size: 55px; color: #FFFFFF; font-family:'宋体'">
					<center>
						<h2 style="width:840px">应用合规性探测系统 </h2>
					</center></td>
			</tr>
			 -->
			<tr>
				<td height="187px"><center>
						 <img src="${ctx}/images/login3.png" />
					 
					<%--<span style="font-size: 70px;color: white;">等级保护合规管理</span> 
					--%></center>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="loginbox2">
						<s:form action="check" namespace="/login" method="post" theme="simple">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="lbox2top">
										<div class="lbox2m">
											<table width="100%" border="0" align="center" cellpadding="5px" cellspacing="0">
												<tr>
													<td class="lboxleft">用户名：</td>
													<td class="lboxright"><input type="text" name="loginName" id="loginName" class="inputtext" /></td>
												</tr>
												<tr>
													<td class="lboxleft">密码：</td>
													<td class="lboxright"><input type="password" maxlength="20" name="password" id="password" class="inputtext" size="22" /></td>
												</tr>
												 
												<tr>
													<td class="lboxleft">验证码：</td>
													<td class="lboxright"><input name="imgCode" id="imgCode" maxlength="4" size="4" class="inputtext2" /> <img src="${ctx}/pages/commons/image.jsp" align="middle" title="点击图片刷新" style="cursor:pointer" onclick="change(this);" /> <span class="loginSecurity">点击图片刷新</span> <input type="hidden" id="para" value="1" /></td>
												</tr>
												
												<tr>
													<td class="lboxleft"></td>
													<td class="lboxright">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td width="50%"><input style="height:30px;border:0px;cursor: pointer;background:url(../images/login2btn.png) no-repeat;" type="button" id="btn_login" value="登录系统" onclick="submitForm()" />
																</td>
																<td width="50%"><span id="codeMessage" class="actmsg"><s:actionmessage /> </span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
								<tr>
									<td class="lbox2mid">&nbsp;</td>
								</tr>
								<tr>
									<td class="lbox2foot"></td>
								</tr>
							</table>
						</s:form>
					</div>
				</td>
			</tr>
			<tr>
				<td class="100px"></td>
			</tr>
		</table>
	</div>

</body>
</html>
