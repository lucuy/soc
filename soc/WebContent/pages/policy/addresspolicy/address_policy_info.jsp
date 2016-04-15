
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
<%-- <script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>--%>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>
<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
<script language="javascript">
	jQuery(document)
			.ready(
					function() {
						jQuery("#addressForm").validationEngine();

						$('#dialog-addressPolicy').dialog({
							autoOpen : false,
							width : 400,
							height : 200,

							buttons : {
								"确定[Enter]" : function() {
									address();
								},
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});

						if (/msie/i.test(navigator.userAgent)) //ie浏览器 
						{
							document.getElementById('addressPolicyName').onpropertychange = checkaddressPolicyName;
						} else {//非ie浏览器，比如Firefox 
							document.getElementById('addressPolicyName')
									.addEventListener("input",
											checkaddressPolicyName, false);
						}

					});

	//验证IP地址合法性
	function address() {
		var ipStart = $("#ipStart").val();
		var ipend = $("#ipEnd").val();
		var e = $('#ips');
		if ((ipStart == "" && ipend != "") || (ipend == "" && ipStart != "")) {
			document.getElementById("div_ip").innerHTML = "两个地址必须同时输入或者同时不输入";
		} else if (ipStart == "" && ipend == "") {
			$('#dialog-addressPolicy').dialog('close');
		} else {
			var flag2 = checkIpStart();

			var result1 = checkIpEnd();
			

			if (flag2 && result1) {
				//判断首位是否相同
				
				 if (parseInt(ipStart.split('.')[0]) == parseInt(ipend
						.split('.')[0])) {
					//判断第二位是否相同
					 if (parseInt(ipStart.split('.')[1]) == parseInt(ipend
							.split('.')[1])) {
						//判断第三位 是否相同
						 if (parseInt(ipStart.split('.')[2]) == parseInt(ipend
								.split('.')[2])) {
							//判断第四为是否相同
							if (parseInt(ipStart.split('.')[3]) > parseInt(ipend
									.split('.')[3])) {
								document.getElementById("div_ip").innerHTML = "起始地址必须小于终止地址";
							return false;
							} else {
								e.append("<option value=\""
										+ $("#ipStart").val() + "-"
										+ $("#ipEnd").val() + "\">"
										+ $("#ipStart").val() + "-"
										+ $("#ipEnd").val() + "</option>");
								$('#dialog-addressPolicy').dialog('close');
							}

						} else {
							document.getElementById("div_ip").innerHTML = "请输入同一IP段";
							return false;
						}

					} else {
						document.getElementById("div_ip").innerHTML = "请输入同一IP段";
						return false;
					}

				} else {
					document.getElementById("div_ip").innerHTML = "请输入同一IP段";
					return false;
				}

			} else {
				document.getElementById("div_ip").innerHTML = "请输入合法的IP段地址！";
				return false;
			}
		}
	}

	//验证起始IP段合法性
	function checkIpStart() {
		var ipStart = $("#ipStart").val();
		if (ipStart != "") {
			var re = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
			var flag = re.test(ipStart);
			if (flag) {
				if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					$("#div_ipStart").addClass("spanred");
					$("#div_ipStart").html("ip地址不合法");
					return false;
				} else {
					$("#div_ipStart").removeClass("spanred");
					$("#div_ipStart").html(
							"<img border=0 src=\"${ctx}/images/ok.png\" />");
					return true;
				}
			} else {
				$("#div_ipStart").addClass("spanred");
				$("#div_ipStart").html("ip地址不合法");
				return false;
			}
		} else {
			$("#div_ipStart").html("");
			return true;
		}
	}
	//验证终止ip段的终止地址
	function checkIpEnd() {
		var ipend = $("#ipEnd").val();
		if (ipend != "") {
			var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
			var result = re1.test(ipend);
			if (result) {
				if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					$("#div_ipEnd").addClass("spanred");
					$("#div_ipEnd").html("ip地址不合法");
					return false;
				} else {
					$("#div_ipEnd").removeClass("spanred");
					$("#div_ipEnd").html(
							"<img border=0 src=\"${ctx}/images/ok.png\" />");
					return true;
				}
			} else {
				$("#div_ipEnd").addClass("spanred");
				$("#div_ipEnd").html("ip地址不合法");
				return false;
			}
		} else {
			$("#div_ipStart").html("");
			return true;
		}
	}
	//弹出地址策略对话框		
	function addPolicyDlg(type) {
		//地址策略
		$("#ipStart").val("");
		$("#ipEnd").val("");
		document.getElementById("div_ip").innerHTML = "";
		$("#div_ipStart").val("");
		$("#div_ipEnd").html("");
		$('#dialog-addressPolicy').dialog('open');
		$('#dialog-addressPolicy').focus();
	}
	//删除ip地址段
	function del() {
		$("select[name=ips]").find("option:selected").remove();
	}
	//验证地址策略名称的唯一性
	var existflag;
	function checkaddressPolicyName() {
		$("#check_loginname_msg").empty();
		if ('${addressPolicy.addressPolicyId}' == '') {
			if ($("#addressPolicyName").val() != null
					&& $("#addressPolicyName").val() != '') {
				var addressName = $("#addressPolicyName").val();
				$
						.ajax({
							type : "post",
							url : "${ctx}/addressPolicy/checkaddressPolicyName.action",
							dataType : "text",
							data : "&addressPolicyName=" + addressName,
							success : function(result) {
								if (result == 'true') {
									existflag = true;
									$("#check_loginname_msg").addClass(
											"spanred");
									$("#check_loginname_msg").html("该名称已占用");
									/* $("#addressName").focus(); */

								} else {
									existflag = false;
									$("#check_loginname_msg").removeClass(
											'spanred');
									$("#check_loginname_msg")
											.html(
													"<img border=0 src=\"${ctx}/images/ok.png\" />");
								}
							}
						});

			} else {
				existflag = true;
			}
		} else {
			existflag = false;
		}
	}
	//验证描述的长度
	function checkAddressMemo() {
		if ($("#addressPolicyMemo").val().length > 255) {
			$("#checkAddressMemo").addClass("spanred");
			$("#checkAddressMemo").html("描述不应大于255个字符");
			return true;
			/* $("#addressPolicyMemo").focus(); */
		} else {
			$("#checkAddressMemo").removeClass("spanred");
			$("#checkAddressMemo").html(
					"<img border=0 src=\"${ctx}/images/ok.png\" />");
			return false;
		}

	}
	//用户点击保存按钮
	var i = 0;
	function subData() {
		var result = checkAddressMemo();

		if ($("#addressPolicyName").val() == null
				|| $("#addressPolicyName").val() == '') {

			$("#addressForm").submit();
		}
		if ('${addressPolicy.addressPolicyId}' != '') {
			if (result == false) {
				var count = $("#ips").find("option").length;
				for (i; i < count; i++) {
					if ($("#ipAll").val() == "") {
						$("#ipAll").val($("#ips").get(0).options[i].text);
					} else {
						$("#ipAll").val(
								$("#ipAll").val() + ";"
										+ $("#ips").get(0).options[i].text);
					}
				}
				$("#addressForm").submit();
			}
		}
		if ('${addressPolicy.addressPolicyId}' == '') {
			if (existflag == false && result == false) {
				//得到select中的Ip串;
				var count = $("#ips").find("option").length;
				for (i; i < count; i++) {
					if ($("#ipAll").val() == "") {
						$("#ipAll").val($("#ips").get(0).options[i].text);
					} else {
						$("#ipAll").val(
								$("#ipAll").val() + ";"
										+ $("#ips").get(0).options[i].text);
					}
				}
				$("#addressForm").submit();
			}
		}
	}
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="updateAddress.action" namespace="/addressPolicy"
		method="post" theme="simple" id="addressForm" name="userForm">

		<s:hidden name="ipAll" id="ipAll" />
		  <s:token></s:token>
		<!--  总table-->
		<!--  主table-->
		<table width="600px" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top" >
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler" colspan='2'><b>地址策略信息</b></td>
							</tr>

							<!-- 分隔符黑线 -->
							<tr>
								<td height="3px"></td>
							</tr>
							<tr>
								<!-- left area -->
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
											<td class="td_detail_separator"></td>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 姓名 -->
										<tr>

											<td align="right"><span class="spanred">*&nbsp;</span>名称：</td>
											<td align="left" style="padding-left:8px;"><input
												id="addressPolicyName"
												name="addressPolicy.addressPolicyName"
												class="validate[required,custom[spechars],custom[validateLength]]"
												type="text" size="50" style="width: 270px"
												value="${addressPolicy.addressPolicyName }"
											<c:if test="${addressPolicy.addressPolicyName != null}">readonly="readonly"</c:if>/>
												<input type="hidden" name="addressPolicy.addressPolicyId"
												value="${addressPolicy.addressPolicyId}">
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

										<!-- 地址策略描述 -->
										<tr>

											<td align="right" style="vertical-align:10%">地址策略描述：</td>

											<td align="left" style="padding-left:8px;"><s:textarea
													name="addressPolicy.addressPolicyMemo"
													id="addressPolicyMemo" cols="31" rows="3"
													cssStyle="width:272px" onblur="checkAddressMemo();"></s:textarea>
											</td>
											<td><span id="checkAddressMemo"></span></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td align="right" class="td_detail_separator"></td>
										</tr>
										
										<!-- 生效方式 -->
										<tr>

											<td align="right"><span class="spanred">*&nbsp;</span>生效方式：</td>

											<td align="left" style="padding-left:8px"><select
												style="width:274px"
												name="addressPolicy.addressPolicyEffectWay">
													<option value="1"
														<c:if test="${addressPolicy.addressPolicyEffectWay==1}">selected="selected"</c:if>>允许</option>
													<option value="0"
														<c:if test="${addressPolicy.addressPolicyEffectWay==0}">selected="selected"</c:if>>禁止</option>
											</select>
											</td>
										</tr>

										<!-- 空行 -->
										<tr>

											<td class="td_detail_separator"></td>
										</tr>

										<!-- IP地址段列表 -->
										<tr>
											<td align="right" style="vertical-align:10%"><span
												class="spanred"></span>IP地址段列表：</td>
											<td align="left" style="padding-left:8px"><select
												name="ips" id="ips" style="width: 274px" size="5"
												multiple="multiple">
													<c:forEach items="${addressPolicy.addressList}" var="l">
														<option value="${l.addressStartIp}-${l.addressEndIp}">${l.addressStartIp}-${l.addressEndIp}</option>
													</c:forEach>
											</select></td>
											<td><span style="padding-right:30px;color:red;">如不填写，则默认值：任意
													地址</span>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td height="2px"></td>
										</tr>
										<tr>
											<td></td>
											<td>
												<table width="98%" border="0" cellspacing="0"
													cellpadding="0" style="align:right">
													<tr>
														<td align="left" style="padding-left:8px"><input
															type="button" value="" class="btnadd"
															onclick="addPolicyDlg();" /> <input type="button"
															value="" class="btndel" onclick="del();" /> <br /></td>
													</tr>
												</table></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
									</table> <!--  左侧table--></td>
							</tr>
						</table>
			</div>
			</td>
			</tr>
		</table>
		<table width="605px" border="0" cellspacing="0" cellpadding="0"
			style="right">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="subData();" /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/addressPolicy/query.action';" />
				</td>
			</tr>
		</table>
	</s:form>

	<!-- 地址策略选择的dialog  -->
	<div id="dialog-addressPolicy" title="IP地址段输入">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<tr>
				<td width="25%">起始IP地址</td>
				<td><input type="text" id="ipStart" name="ipStart" size="20"
					width="100px" onblur="checkIpStart();" />
				</td>
				<td width="25%"><span id="div_ipStart"></span></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td width="25%">结束IP地址</td>
				<td><input type="text" id="ipEnd" name="ipEnd" size="20"
					width="100px" onblur="checkIpEnd();" />
				</td>
				<td><span id="div_ipEnd"></span></td>
			</tr>
			<tr>
				<td></td>
				<td><span style="color: red;" id="div_ip"></span></td>
			</tr>
		</table>
	</div>

</body>
</html>



