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
	var tomId=$("#tomId").val();
		if(null==tomId||""==tomId){
			var tomName=$("#tomName").val();
			if(tomName!=null||tomName!=""){
			
			$
			.ajax({
				type : "post",
				url : "${ctx}/monitorTOM/checkName.action?tomName="+tomName,
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
		
		var port = $("#tomPort").val();
		var username = $("#username").val();
		var pwd = $("#tomUserPass").val();
		
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
			url : "${ctx}/monitorTOM/testLink.action?tomIp="+ip+"&port="+port+"&username="+username+"&pwd="+pwd,
			async : false,
			dataType : "text",
			success : function(result) {
				if (result == 'true') {
					$("#tomIsOnline").val(1);
					alert("测试通过！");
				} else {
					$("#tomIsOnline").val(0);
					alert("测试未通过！");
				}
			}
		});
		
	}
	</script>
</head>

<body>
	<s:form action="addOrUpdate" namespace="/monitorTOM"
		method="post" id="workfrm" name="workfrm" theme="simple">
		<!-- 主table -->
		<s:token></s:token>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<tr>
				<td width="50%" valign="top"><input type="hidden"
					name="montt.tomId" id="tomId" value="${montt.tomId}" /> 
					 <input
					type="hidden" name="montt.tomIsOnline" id="tomIsOnline"
					value="${montt.tomIsOnline}" /> <!-- 左侧table -->
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
														name="montt.tomName" id="tomName" onblur="checkName();"
														class="validate[required,custom[validateLength],custom[spechars]] text-input"
														style="width: 270px"
														value="${montt.tomName}" /><span
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
														name="montt.tomIp" id="ip"
														class="validate[required,custom[ipv4]] text-input "
														value="${montt.tomIp}" style="width: 270px" />
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
											<legend>Jmx</legend>
											<table width="100%">
												
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>端口：</td>
													<td width="70%" colspan="2"><input type="text"
														name="montt.tomPort" id="tomPort"
														class="validate[required,custom[settingPort]] text-input "
														<c:if test="${montt.tomPort ==null}" > value="1090" </c:if>
														<c:if test="${montt.tomPort !=null}" > value="${montt.tomPort}" </c:if> />
													</td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>用户名：</td>
													<td width="70%" colspan="2"><input type="text"
														name="montt.tomUserName" id="username"
														class="validate[required,custom[validateLength],custom[spechars],maxSize[50]] text-input"
														style="width: 270px"  value="${montt.tomUserName}"/></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>

												<tr>
													<td height="20" align="right" width="18%"><span
														style="color:red;">*</span>密码：</td>
													<td width="70%" colspan="2"><input type="password"
														name="montt.tomUserPass" id="tomUserPass"
														class="validate[required,maxSize[50]] text-input "
														style="width: 270px" value="${montt.tomUserPass}" /></td>
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
														name="montt.tomContacts" id="tomContacts"
														style="width: 270px"  value="${montt.tomContacts}"/></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right" width="18%">物理地址：</td>
													<td width="70%" colspan="2"><input type="text"
														name="montt.tomAddress" id="tomAddress" style="width: 270px" value="${montt.tomAddress}" /></td>
												</tr>
												<tr>
													<td class="td_detail_separator"></td>
												</tr>
												<tr>
													<td height="20" align="right">任务描述：</td>
													<td width="70%" class="msg" colspan="2"><textarea
															rows="6" name="montt.tomTaskDesc" id="tomTaskDesc" cols="35">${montt.tomTaskDesc}</textarea>
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
										Tomcat监控需要配置JMX服务才能正确创建监控任务。若Tomcat没有安装为服务，配置步骤:<br/>
										在[$tomcat]bincatalina.bat中，在set JAVA_OPTS=...后面加上-Dcom.sun.management.jmxremote<br/>
										-Dcom.sun.management.jmxremote.port=1090<br/>
										-Dcom.sun.management.jmxremote.ssl=false<br/>
										-Dcom.sun.management.jmxremote.authenticate=false<br/>
										若Tomcat已安装为服务，请参见FAQ.最后请用Jconsole工具确认Tomcat的JMX服务启动<br/>
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
