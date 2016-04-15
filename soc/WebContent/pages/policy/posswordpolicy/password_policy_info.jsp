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


<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>


<script language="javascript">
	jQuery(document)
			.ready(
					function() {
						jQuery("#passwordPolicyForm").validationEngine();
						if (/msie/i.test(navigator.userAgent)) //ie浏览器 
						{
							document.getElementById('passwordPolicyName').onpropertychange = checkPasswordPolicyName;
						} else {//非ie浏览器，比如Firefox 
							document.getElementById('passwordPolicyName')
									.addEventListener("input",
											checkPasswordPolicyName, false);
						}
					});

	function subData() {
		var tme = checkPasswordPolicyMemo();
		if ($("#passwordPolicyName").val() == ""
				|| $("#passwordPolicyName").val() == null) {
			$("#passwordPolicyForm").submit();
		}
		if ('${passwordPolicy.passwordPolicyId}' != '') {
			if (tme) {
				$("#passwordPolicyForm").submit();
			}
		}
		if (existFlag == false && tme) {
			$("#passwordPolicyForm").submit();
		}
		//$("#userPinyin").val(pinyin($("#userLoginName").val()));
	}

	//判断密码策略名称是否存在
	var existFlag;
	function checkPasswordPolicyName() {
		if ('${passwordPolicy.passwordPolicyId}' == '') {
			if ($("#passwordPolicyName").val() != "") {
				var passwordPolicyName = $("#passwordPolicyName").val();
				$
						.ajax({
							type : "post",
							url : "${ctx}/passwordPolicy/checkpasswordPolicyName.action",
							dataType : "text",
							data : "&passwordPolicyName=" + passwordPolicyName,
							success : function(result) {
								if (result == 'true') {
									existFlag = true;
									$("#check_loginname_msg").addClass(
											"spanred");
									$("#check_loginname_msg").html("该名称已占用");

									//existFlag = true;
								} else {
									existFlag = false;
									$("#check_loginname_msg").removeClass(
											'spanred');
									$("#check_loginname_msg")
											.html(
													"<img border=0 src=\"${ctx}/images/ok.png\" />");

								}
							}
						});

			} else {
				existFlag = true;
			}

		} else {
			existFlag = false;
		}
	}

	function checkPasswordPolicyMemo() {
		if ($("#passwordPolicyMemo").val().length > 255) {
			/* alert("描述输入应该小于25"); */
			$("#check_Memo_msg").addClass("spanred");
			$("#check_Memo_msg").html("描述不应大于255个字符");
			/* $("#timePolicyMemo").focus(); */
			return false;
		} else {
			/*  alert("成功"); */
			$("#check_Memo_msg").removeClass("spanred");
			$("#check_Memo_msg").html(
					"<img border=0 src=\"${ctx}/images/ok.png\" />");
			return true;
		}
	}
</script>
</head>

<body style="margin-top: 2px">
	<s:form action="update.action" namespace="/passwordPolicy"
		method="post" theme="simple" id="passwordPolicyForm"
		name="passwordPolicyForm">
		<s:hidden name="passwordPolicy.passwordPolicyId" id="userId" />
		<!-- <s:hidden name="user.userPinyin" id="userPinyin" /> -->
		<s:hidden name="time" />
		<s:hidden name="userStatus" />
		<s:hidden name="order" id="order" />
		<s:hidden name="flag" id="flag" />
		  <s:token></s:token>
		<!--  总table-->
		<!--  主table-->

		<table width="600px" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px" id="table1">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top">
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>密码策略信息</b>
								</td>
							</tr>

							<!-- 分隔符黑线 -->
							<tr>
								<td height="3px"></td>
							</tr>
							<tr>
								<!-- left area -->
								<td>
									<table border="0" cellspacing="0" cellpadding="0" align="left">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
											<td class="td_detail_separator"></td>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 姓名 -->
										<tr>

											<td align="right"><span class="spanred">*&nbsp;</span>名称：</td>
											<td align="left" class="padding"><input
												class="validate[required,custom[spechars],custom[validateLength]] text-input"
												name="passwordPolicy.passwordPolicyName"
												<c:if test="${passwordPolicy.passwordPolicyName!=null}">readonly="readonly"</c:if>
												value="${passwordPolicy.passwordPolicyName}" type="text"
												size="40" id="passwordPolicyName" style="width: 270px" />
											</td>
											<td><span id="check_loginname_msg"></span>
											</td>
										</tr>
										<tr>
											<td height="2px"></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 密码策略描述 -->
										<tr>

											<td align="right" style="vertical-align:10%">密码策略描述：</td>

											<td class="padding"><s:textarea
													name="passwordPolicy.passwordPolicyMemo"
													id="passwordPolicyMemo" cols="35" rows="3"
													cssStyle="width:272px" onblur="checkPasswordPolicyMemo();"></s:textarea>
											</td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td align="right" class="td_detail_separator"></td>
										</tr>


										<!-- 密码长度 -->
										<tr>
											<td align="right">最小密码长度：</td>

											<td class="padding"><input
												name="passwordPolicy.passwordPolicyPasswordLength"
												value="${passwordPolicy.passwordPolicyPasswordLength}"
												type="text" size="40" id="passwordPolicyPasswordLength"
												class="validate[min[6],custom[onlyNumberSp],max[20],custom[validateSpase]]" maxlength="255"
												style="width: 270px" /></td>
											<td align="left"><span
												style="margin-left:10px;color:red;">如不填写，则默认值：6</span>
											</td>
										</tr>

										<!-- 空行 -->
										<tr>

											<td class="td_detail_separator"></td>
										</tr>


										<!-- 密码更新周期 -->
										<tr>

											<td align="right">密码更新周期(天)：</td>

											<td class="padding"><input
												name="passwordPolicy.passwordPolicyPasswordChangeCycle"
												size="40" id="passwordPolicyPasswordChangeCycle"
												value="${passwordPolicy.passwordPolicyPasswordChangeCycle}"

												Style="width:270px" class="validate[custom[onlyNumberSp],custom[validateSpase],maxSize[10]]" size="10"/>

											</td>
											<td align="center"><span
												style="margin-left:10px;color:red;">如不填写，则默认值：永不过期</span>
											</td>
										</tr>


										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!-- 小写字母个数 -->
										<tr>

											<td align="right">小写字母个数：</td>

											<td class="padding"><input
												name="passwordPolicy.passwordPolicyLowerCaseNumber"
												value="${passwordPolicy.passwordPolicyLowerCaseNumber}"
												type="text" size="40" id="passwordPolicyLowerCaseNumber"
												class="validate[max[20],custom[onlyNumberSp],custom[validateSpase]] " maxlength="255"
												style="width: 270px" /></td>
											<td align="left"><span
												style="margin-left:10px;color:red;">如不填写，则默认值：0</span>
											</td>
										</tr>


										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>



										<!-- 大写字母个数 -->
										<tr>

											<td align="right">大写字母个数：</td>

											<td class="padding"><input
												name="passwordPolicy.passwordPolicyUpperCaseNumber"
												value="${passwordPolicy.passwordPolicyUpperCaseNumber}"
												type="text" size="40" id="passwordPolicyUpperCaseNumber"
												class="validate[max[20],custom[onlyNumberSp],custom[validateSpase]] " maxlength="255"
												style="width: 270px" /></td>
											<td align="left"><span
												style="margin-left:10px;color:red;">如不填写，则默认值：0</span>
											</td>
										</tr>




										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 数字个数 -->
										<tr>

											<td align="right">数字最少个数：</td>

											<td class="padding"><input
												name="passwordPolicy.passwordPoicyNumericNumber"
												value="${passwordPolicy.passwordPoicyNumericNumber}"
												type="text" size="40" id="passwordPoicyNumericNumber"
												class="validate[max[20],custom[onlyNumberSp],custom[validateSpase]] " maxlength="255"
												style="width: 270px" /></td>
											<td align="left"><span
												style="margin-left:10px;color:red;">如不填写，则默认值：0</span>
											</td>
										</tr>




										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 锁定次数 -->
										<tr>

											<td align="right">锁定次数：</td>
											<td class="padding"><input
												name="passwordPolicy.passwordPoicyFailLockNumber"
												value="${passwordPolicy.passwordPoicyFailLockNumber}"
												type="text" size="40" id="passwordPoicyFailLockNumber"

												class="validate[custom[onlyNumberSp],custom[validateSpase],maxSize[10]]" maxlength="255"
												style="width: 270px" maxlength="10"/></td>

											<td align="left"><span
												style="margin-left:10px;color:red;">如不填写，则默认值：从不锁定</span>
											</td>
										</tr>


										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 锁定时间 -->
										<tr>

											<td align="right">锁定时间(分钟)：</td>
											<td class="padding"><input
												name="passwordPolicy.passwordPolicyLockRecoveTime"
												value="${passwordPolicy.passwordPolicyLockRecoveTime}"
												type="text" size="40" id="passwordPolicyLockRecoveTime"

												class="validate[custom[onlyNumberSp],custom[validateSpase],maxSize[10]] " maxlength="255"
												style="width: 270px" maxlength="10"/></td>

											<td align="left"><span
												style="margin-left:10px;color:red;">如不填写，则默认值：0</span>
											</td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
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
		</table>

		<table width="605px" border="0" cellspacing="0" cellpadding="0"
			style="align:right" id="table2">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="subData();" /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/passwordPolicy/query.action';" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>
