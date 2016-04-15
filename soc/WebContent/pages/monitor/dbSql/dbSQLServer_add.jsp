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
		$("#workfrm").submit();
	}
	function checkName(){
		var taskId=$("#taskId").val();
			if(null==taskId||""==taskId){
				var taskName=$("#taskName").val();
				if(taskName!=null||taskName!=""){
				
				$
				.ajax({
					type : "post",
					url : "${ctx}/monitorDBT/checkName.action?taskName="+taskName,
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
		var ip = $("#ip").val();
		var type=${dbType};
		var port = $("#dbPort").val();
		var username = $("#username").val();
		var pwd = $("#dbPWD").val();
		
		if(ip==null||ip==''){
			alert("ip地址不能为空！");
			return;
		}
		if(username==null||username==''){
			alert("用户名不能为空！");
			return;
		}
		if(pwd==null||pwd==''){
			alert("密码不能为空！");
			return;
		}
		if(port==null||port==''){
			alert("端口不能为空！");
			return;
		}
		$
		.ajax({
			type : "post",
			url : "${ctx}/monitorDBT/testLink.action?type="+type+"&dbIp="+ip+"&port="+port+"&username="+username+"&pwd="+pwd+"&dbType="+type,
			async : false,
			dataType : "text",
			success : function(result) {
				if (result == 'true') {
					$("#dbOnline").val(1);
					alert("测试通过！");
				} else {
					$("#dbOnline").val(0);
					alert("测试未通过！");
				}
			}
		});
		
	}
	</script>
</head>

<body>
	<s:form action="addOrUpdate.action" namespace="/monitorDBT"
		method="post" id="workfrm" name="workfrm" theme="simple">
		<!-- 主table -->
		<s:token></s:token>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<tr>
				<td width="50%" valign="top"><input type="hidden"
					name="mondbt.taskId" value="${mondbt.taskId}" /> <input
					type="hidden" name="dbType" value="${dbType}" /> <input
					type="hidden" name="mondbt.dbOnline" id="dbOnline"
					value="${mondbt.dbOnline}" /> <!-- 左侧table -->
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
														name="mondbt.taskName" id="taskName" onblur="checkName();"
														class="validate[required,custom[validateLength],custom[spechars]] text-input"
														style="width: 270px"
														<c:if test="${mondbt.taskName !=null}"> readonly="readonly"</c:if>
														value="${mondbt.taskName}" /> <span
														id="check_loginname_msg"></span></td>

												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>IP地址：</td>
													<td width="70%" colspan="2"><input type="text"
														name="mondbt.dbIp" id="ip"
														class="validate[required,custom[ipv4]] text-input "
														value="${mondbt.dbIp}" style="width: 270px" />
													</td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>监控类别：</td>
													<td width="70%" colspan="2"><select
														style="width:270px;" name="mondbt.dbCategory">
															<option value="SQLServer2000"
																<c:if test="${mondbt.dbCategory eq 'SQLServer2000' }"> selected="selected"</c:if>>2000</option>
															<option value="SQLServer2005"
																<c:if test="${mondbt.dbCategory eq 'SQLServer2005' }"> selected="selected"</c:if>>2005</option>
															<option value="SQLServer2008"
																<c:if test="${mondbt.dbCategory eq 'SQLServer2008' }"> selected="selected"</c:if>>2008</option>
													</select></td>
												</tr>
											</table>
										</fieldset>
									</div></td>
							</tr>
							<tr>
								<td>
									<div>
										<fieldset style="width:95%; padding:10px 10px 5px 10px;">
											<legend>JDBC</legend>
											<table width="100%">
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>监控类型：</td>
													<td width="70%" colspan="2"><select
														style="width:270px;" name="mondbt.monType">
															<option value="jdbc">Jdbc</option>
													</select></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>端口：</td>
													<td width="70%" colspan="2"><input type="text"
														name="mondbt.dbPort" id="dbPort"
														class="validate[required,custom[settingPort]] text-input "
														<c:if test="${mondbt.dbPort ==null}" > value="1433" </c:if>
														<c:if test="${mondbt.dbPort !=null}" > value="${mondbt.dbPort}" </c:if>
														 />
														</td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>用户名：</td>
													<td width="70%" colspan="2"><input type="text"
														name="mondbt.dbUserName" id="username"
														class="validate[required,custom[validateLength],custom[spechars],maxSize[50]] text-input"
														style="width: 270px" value="${mondbt.dbUserName}" /></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>

												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>密码：</td>
													<td width="70%" colspan="2"><input type="password"
														name="mondbt.dbPWD" id="dbPWD"
														class="validate[required,maxSize[50]] text-input "
														style="width: 270px" value="${mondbt.dbPWD}" /></td>
												</tr>

												<tr>
													<td colspan="2" align="center"><input type="button"
														value="连接测试" class="btnyh" onclick="testLink()" /></td>
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
													<td height="20" align="right" width="18%">联系人：</td>
													<td width="70%" colspan="2"><select
														name="mondbt.dbContasts" id="dbContasts"
														style="width: 270px;">
															<option value="">--请选择--</option>
															<c:forEach items="${userList}" var="user">
																<option title="${user.userId}"
																	value="${user.userLoginName}"
																	<c:if test="${mondbt.dbContasts == user.userLoginName}"> selected="selected"</c:if>>${user.userLoginName}</option>
															</c:forEach>
													</select></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%">物理地址：</td>
													<td width="70%" colspan="2"><input type="text"
														name="mondbt.dbAddr" id="dbAddr" style="width: 270px" value="${mondbt.dbAddr}" /></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right">任务描述：</td>
													<td width="70%" class="msg" colspan="2"><textarea
															rows="6" name="mondbt.taskDESC" id="taskDESC" cols="35">${mondbt.taskDESC}</textarea>
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
										1.
										对于Oracle,SqlServer,MySql采用JDBC方式进行监控,需要配置JDBC的连接参数，例如用户名,密码,端口等;对Oracle进行监控时不能使用sys用户。<br />
									
										<br /> 2.
										参数配置完毕后可以点击"连接测试"按钮检查协议连接是否成功;如果不成功，请检查相关服务是否启动，参数输入是否正确以及网络是否通畅。<br />
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
