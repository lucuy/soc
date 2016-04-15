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
		    <link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/template.css" rel="stylesheet"
			type="text/css">
		<link href="${ctx}/styles/validationEngine.jquery.css"
			rel="stylesheet" type="text/css">
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery.validationEngine.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/jquery.select.js"></script>

		<script language="javascript">
	jQuery(document).ready( function() {
		jQuery("#userForm").validationEngine();
	});
	function subData() {
		if($("#message").html()!=""||$("#message").html()!=""){
			return;
		}
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword");
		var confirmPassword = $("#confirmPassword");
		if ($.trim(newPassword.val()) != "" || $.trim(confirmPassword.val()) != "") {
			if($.trim(oldPassword)==""){
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
		$("#userForm").submit();
	}
	
	//检验用户密码是否正确
	function check(){
	
	    $("#showMessage").empty();
	    
		var oldPassword = $("#oldPassword").val();
	
		if($.trim(oldPassword)!=""&& oldPassword!=" "){
			$.ajax({
				type : "post",
				url : "${ctx}/user/checkPassword.action?oldPassword="
					+ oldPassword,
				dataType : "text",
			
				success : function(result){
			
								$("#showMessage").empty();
								if (result != "") {
									var fontNode = $("<font color='red'>"
											+ result + "</font>");
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
				}
							});
		}else{
		    
		    var fontNode = $("<font color='red'>密码不为空  </font>");
											 
			$("#showMessage").append(fontNode);
			
			$("#newPassword").attr("disabled",true);
			$("#confirmPassword").attr("disabled",true);
			return;
		}
	}
	//密码策略进行检查
	function checkPolicy(){
	
		var newPassword = $("#newPassword").val();
		
		if($.trim(newPassword)!=""){
		
			/* $.get("checkPwdPolicy.action?employee.empId=${sessionScope.SOC_LOGON_USER.userId}",{"newPassword":newPassword},function(data){
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
			});
		}else{
			$("#message").empty();
			$("#icon").empty();
			return;
		} */
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
			
			/* $("#confirmPassword").attr("disabled",true); */
			return;
		}
	}
</script>

	</head>

	<body style="margin-top: 2px">
		<s:form action="userInfoSeting.action" namespace="/user"
			method="post" theme="simple" id="userForm" name="userForm">
			<input type="hidden" name="user.userId" id="empId"
				value="${sessionScope.SOC_LOGON_USER.userId}" />
			<!--  主table-->
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
				<tr>
					<!-- left area -->
					<td width="100%" valign="top">
						<!--  左侧table-->
						<div class="framDiv">
						<table width="100%"  border="0" cellspacing="1" cellpadding="0">
							<!-- 用户信息 -->
							<tr>
								<td  class='r2titler' colspan='2'>
										<b>用户信息</b>
									</td>
								</tr>
								<!-- 分隔符黑线 -->
								<!-- <tr>
									<td height="3px">
									</td>
								</tr>
								<tr style="display:none;">
									<td align="right" style="margin-right: 3px">
										<a href="#">证书下载</a>
									</td>
								</tr> -->
								<tr><td align="center">
											<span class="news-success">
												${requestScope.setSuccess}
											</span>
								</td></tr>
								<tr>
									<td>
										<!-- 用户信息内容 -->
										<table width="99%" border="0" cellspacing="0" cellpadding="0">
											<%-- <c:if test="${sessionScope.FORT_LOGON_USER.empTemp==1}">
											<tr>
												<td  align="right" class="td_employee_detail">
													临时用户有效日期
												</td>
												<td style="padding-left: 20px">
													<input type="text" disabled="disabled" style="width: 270px"
														value="<s:date name="#session.FORT_LOGON_USER.empLimitTime" format="yyyy-MM-dd HH:mm:ss"/>" size="40" />
													
												</td>

											</tr>
											</c:if> --%>
											<!-- 空行 -->
											<tr>
												<td width="20%" class="td_detail_separator">
												</td>
											</tr>

											<!-- 姓名 -->
											<tr>
												<td  align="right" class="td_employee_detail">
													真实姓名：
												</td>
												<td style="padding-left: 20px">
													<input type="text" name="user.userRealName"
														style="width: 270px"
														value="${sessionScope.SOC_LOGON_USER.userRealName}" size="40" />
												</td>

											</tr>
											<!-- <tr>
												<td height="2px">
												</td>
											</tr>
											临时用户
											<tr>
												<td class="td_employee_detail">

												</td>

												<td>
													<span align="right"  style="display: none"><input type="checkbox"
															value="1" />临时用户:</span>
												</td>
											</tr> -->

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>


											<!-- 公司/组织 -->
											<%-- <tr>
												<td align="right"  class="td_employee_detail">
													公司/组织：
												</td>

												<td style="padding-left: 20px">
													<input name="employee.empOrganization"
														value="${sessionScope.SOC_LOGON_USER.empOrganization}"
														type="text" size="40" id="emporg"
														class="validate[custom[onlyLetterNumber]] text-input "
														maxlength="255" style="width: 270px" />
												</td>
											</tr> --%>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>

											<!-- 邮件地址 -->
											<tr>
												<td align="right"  class="td_employee_detail">
													<span class="spanred">*</span>邮件地址：
												</td>

												<td style="padding-left: 20px">
													<input name="user.userEmail"
														value="${sessionScope.SOC_LOGON_USER.userEmail}"
														type="text" size="40" id="empemail"
														class="validate[required,custom[email]] text-input "
														maxlength="255" style="width: 270px" />
												</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>


											<!-- 手机号码 -->
											<tr >
												<td align="right" class="td_employee_detail">
													<span class="spanred">*</span>手机号码：
												</td>

												<td  style="padding-left: 20px">
													<input name="user.userMobile"
														value="${sessionScope.SOC_LOGON_USER.userMobile}"
														type="text" size="40" id="empMobile"
														class="validate[required,custom[phone]] text-input "
														maxlength="255" style="width: 270px" />
												</td>
											</tr>

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>


											<!-- 固定电话 -->
											<tr>
												<td align="right" class="td_employee_detail">
													固定电话：
												</td>

												<td style="padding-left: 20px">
													<input type="text" name="user.userTelephone"
														value="${sessionScope.SOC_LOGON_USER.userTelephone}" size="40"
														class="validate[custom[Talphone]] text-input "
														id="tel" style="width: 270px" />
												</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>


											<!-- 通信地址 -->
											<tr>
												<td align="right" class="td_employee_detail">
													通信地址：
												</td>

												<td style="padding-left: 20px">
													<input name="user.userAddress"
														value="${sessionScope.SOC_LOGON_USER.userAddress}"
														type="text" size="40" id="empAddress"
														class="validate[custom[onlyLetterNumber]] text-input "
														maxlength="255" style="width: 270px" />
												</td>
											</tr>


											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
												</td>
											</tr>



											<!-- 备注 -->
											<tr>
												<td align="right" class="td_employee_detail">
													备注：
												</td>

												<td style="padding-left: 20px">
													<textarea name="user.userMemo" id="project.proDesc"
														cols="31" rows="4" style="width:270px">${sessionScope.SOC_LOGON_USER.userMemo}</textarea>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>

								<!-- 空行 -->
								<tr>
									<td class="td_detail_separator">
									</td>
								</tr>
								<!-- 登录信息 -->
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<!-- 登录信息 -->
											<tr>
												<td height="5px">
												</td>
											</tr>
											<tr>
												<td class="td_detail_title">
													<b>登录信息</b>
												</td>
											</tr>



											<!-- 分隔符 -->
											<tr>
												<td height="3px">
												</td>
											</tr>
											<!-- 虚线分割线 -->
											<tr>
												<td colspan="2">
													<div class="xuxian"></div>
												</td>
											</tr>



											<tr>
												<td>
													<!-- 用户信息内容 -->
													<table width="99%" border="0" cellspacing="0"
														cellpadding="0">

														<!-- 空行 -->
														<tr>
															<td width="20%">
															</td>
														</tr>

														<!-- 用户名 -->
														<tr>
															<td align="right" class="td_employee_detail">
																用户名：
															</td>
															<td style="padding-left: 20px">
																${sessionScope.SOC_LOGON_USER.userLoginName}
															</td>
														</tr>


														<!-- 空行 -->
														<tr>
															<td class="td_detail_separator">
															</td>
														</tr>


														<!-- 密码 -->
														<tr >
															<td align="right"  class="td_employee_detail">
																旧密码：
															</td>
															<td style="padding-left: 20px">
																<input type="password" name="user.userPassword" maxlength="30"
																	id="oldPassword" size="40" style="width: 270px"
																	onblur="check();" />
																<span id="showMessage"></span>
															</td>
														</tr>


														<!-- 空行 -->
														<tr>
															<td class="td_detail_separator">
															</td>
														</tr>
														<!--新 密码 -->
														<tr>
															<td align="right" class="td_employee_detail">
																新密码：
															</td>
															<td style="padding-left: 20px">
																<input type="password" disabled="disabled" maxlength="30"
																	name="newPassword" id="newPassword" size="40"
																	class="validate[custom[passwordLength]] text-input "
																	style="width: 270px" onblur="checkPolicy()" />
																<span id="icon"></span>
																
																<!-- <br /> -->
															</td>
														</tr>
														<tr>
														<td class="td_employee_detail">
															</td>
														   <td>
														   <div id="message"></div>
														   </td>
														</tr>
														<!-- <tr>
															<td class="td_employee_detail">
															</td>
															<td>
																<div style="width: 270px;padding-left: 15px">
																	<font color="gray" size="2px"><span id="pwdInfo"></span></font>
																</div>
															</td>
														</tr> -->


														<!-- 空行 -->
														<tr>
															<td class="td_detail_separator">
															</td>
														</tr>
														<!-- 确认密码 -->
														<tr>
															<td align="right" class="td_employee_detail">
																确认密码：
															</td>

															<td style="padding-left: 20px">
																<input type="password" disabled="disabled" maxlength="30"
																	name="password2" id="confirmPassword" size="40"
																	style="width: 270px"
																	class="validate[equals[newPassword]]/>" />
															</td>

															<tr>
																<td height="2px">
																</td>
															</tr>
													</table>
												</td>
											</tr>

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator">
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
					<td width="100%">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>

							<tr>
								
								<td  align="right">
									<input type="button" class="btnyh" id="btnSave" value="保  存" onclick="subData();" />
									<input type="button" class="btnyh" id="btnCancel" value="取  消" onclick="window.history.back();" />	
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
