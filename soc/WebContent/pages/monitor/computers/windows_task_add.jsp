<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<title>任务添加</title>
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(document).ready(function() {
		jQuery("#workfrm").validationEngine();
							   });
	var falg = true;
	//提交的时候判断时间
	function sub(){
		if(!falg){
			return;
		}
		$("#snmpType").val();
		$("#workfrm").submit();
	}
	function checkName(){
	var winId=$("#winId").val();
		if(null==winId||""==winId){
			var winName=$("#winName").val();
			if(winName!=null||winName!=""){
			
			$
			.ajax({
				type : "post",
				url : "${ctx}/monitorWIN/checkName.action?winName="+winName,
				async : false,
				dataType : "text",
				success : function(result) {
					if (result != "") {
						$("#check_loginname_msg").addClass(
								"spanred");
						$("#check_loginname_msg").html("该名称已占用");
						falg = false;
					} else {
						$("#check_loginname_msg").removeClass(
								"spanred");
						$("#check_loginname_msg")
								.html(
										"<img border=0 src=\"${ctx}/images/ok.png\" />");
						falg = true;
					}
				}
			});
			}
		}
	}
	function testLink(){
		var winIp = $("#winIp").val();
		
		var port = $("#winPort").val();
		var communityName = $("#communityName").val();
		var snmpType=$("#snmpType").val();
		if(winIp==null||winIp==''){
			alert("ip地址不能为空！");
			return;
		}
		
		if(communityName==null||communityName==''){
			alert("端口不能为空！");
			return;
		}
		$
		.ajax({
			type : "post",
			url : "${ctx}/monitorWIN/testLink.action?winIp="+winIp+"&port="+port+"&communityName="+communityName+"&winType="+snmpType,
			//async : false,
			dataType : "text",
			success : function(result) {
				if (result == 'false') {
					$("#winIsOnline").val(1);
					alert("测试通过！");
				} else {
					$("#winIsOnline").val(0);
					alert("测试未通过！");
				}
			}
		});
		
	}
	</script>
</head>

<body>
	<s:form action="addOrUpdate" namespace="/monitorWIN"
		method="post" id="workfrm" name="workfrm" theme="simple">
		<!-- 主table -->
		<s:token></s:token>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<tr>
				<td width="50%" valign="top"><input type="hidden"
					name="monwt.winId" id="winId" value="${monwt.winId}" /> 
					 <input
					type="hidden" name="monwt.winIsOnline" id="winIsOnline"
					value="${monwt.winIsOnline}" /> <!-- 左侧table -->
					<div class="framDiv" style="height: 90%">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class='r2title' colspan='3'><b>对象信息</b>
								</td>
							</tr>
							<tr>
								<td>
									<div>
										<fieldset style="width:95%; padding:10px 10px 5px 10px;">
											<legend>基本信息</legend>
											<table width="100%">
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span> 任务名称：</td>
													<td width="70%" colspan="2"><input type="text"
														name="monwt.winName" id="winName" onblur="checkName();"
														class="validate[required,custom[validateLength],custom[spechars]] text-input"
														style="width: 270px"
														value="${monwt.winName}" /><span
														id="check_loginname_msg"></span>
													</td>

												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>IP地址：</td>
													<td width="70%" colspan="2"><input type="text"
														name="monwt.winIp" id="winIp"
														class="validate[required,custom[ipv4]] text-input "
														value="${monwt.winIp}" style="width: 270px" />
													</td>
												</tr>
												
											</table>
										</fieldset>
									</div></td>
							</tr>
							<tr>
								<td>
									<div>
										<fieldset style="width:95%; padding:10px 10px 5px 10px;">
											<legend>Snmp</legend>
											<table width="100%">
												
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>端口：</td>
													<td width="70%" colspan="2"><input type="text"
														name="monwt.winPort" id="winPort"
														class="validate[required,custom[settingPort]] text-input "
														<c:if test="${monwt.winPort ==null}" > value="161" </c:if>
														<c:if test="${monwt.winPort !=null}" > value="${monwt.winPort}" </c:if> />
													</td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>监控类型：</td>
													<td width="70%" colspan="2"><%-- <input type="text"
														name="monwt.snmpType" id="snmpType"
														class="validate[required,custom[validateLength],custom[spechars],maxSize[50]] text-input"
														style="width: 270px"  value="${monwt.snmpType}"/> --%>
									<select name="monwt.snmpType" id="snmpType" style="width: 270px"  value="${monwt.snmpType}">
									<option value="1">Snmpv1</option>
									<option value="2c">Snmpv2</option>
									<option value="3">Snmpv3</option>
									</select>
														
														</td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>

												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>参数配置：</td>
													<td width="70%" colspan="2"><input type="password"
														name="monwt.communityName" id="communityName"
														class="validate[required,maxSize[50]] text-input "
														style="width: 270px" value="${monwt.communityName}" /></td>
												</tr>

												<tr>
													<td colspan="2" align="center"><input type="button"
														class="btnyh" value="连接测试" onclick="testLink()" /></td>
												</tr>

											</table>
										</fieldset>

									</div></td>
							</tr>
							<tr>
								<td>
									<div>
										<fieldset style="width:95%; padding:10px 10px 5px 10px;">
											<legend>描述信息</legend>
											<table width="100%">
												<tr>
													<td height="20" align="right" width="18%">联系方式：</td>
													<td width="70%" colspan="2"><input type="text"
														name="monwt.winContacts" id="winContacts"
														style="width: 270px"  value="${monwt.winContacts}"/></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%">物理地址：</td>
													<td width="70%" colspan="2"><input type="text"
														name="monwt.winAddress" id="winAddress" style="width: 270px" value="${monwt.winAddress}" /></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right">任务描述：</td>
													<td width="70%" class="msg" colspan="2"><textarea
															rows="6" name="monwt.winTaskDesc" id="winTaskDesc" cols="35">${monwt.winTaskDesc}</textarea>
													</td>
												</tr>
											</table>
										</fieldset>
									</div></td>
							</tr>
						</table>
					</div></td>

				<!-- 左右中间间隔 -->
				<td width="5px"></td>


				<td valign="top">
					<!-- 右侧的table -->
					<div class="framDiv" style="height: 478px">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class='r2title' colspan='3'><b>帮助信息</b>
								</td>
							</tr>
							<!-- 空行 -->
							<tr>
								<td><div style="font-size:14px; color:#999">
										1. Windows采用SNMP监控，请确保主机正确配置了SNMP。<br/>2. 参数配置完毕后可以点击"连接测试"按钮检查协议连接是否成功;如果不成功，请检查相关服务是否启动，参数输入是否正确以及网络是否通畅。

									</div>
								</td>
							</tr>

						</table>
					</div></td>
			</tr>
		</table>
		<table align="right">
			<tr align="right">
				<td height="30" colspan="4"><input type="button" id="btnSave"
					value="保  存" class="btnyh" onClick="sub()" width="80">&nbsp;&nbsp;<input
					type="button" name="back" value="取  消" class="btnyh"
					onClick="window.history.go(-1)" width="80"></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
