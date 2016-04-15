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

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script>
	jQuery(document).ready(function() {
		jQuery("#setting-localCollector").validationEngine();

	});
	var i = 1;
	$(function() {
		$("#add")
				.click(
						function() {
							$("#testTbl").append('<tr id="kdel_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel1_' + i + '"><td height="37">IP地址</td></tr><tr id="kdel2_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel3_' + i + '"><td>团体名</td></td></tr>');
							$("#testTbl1").append('<tr id="kdel11_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel12_' + i + '"><td><input type="text" id="collectorIp" name="collectorIp" class="validate[custom[settingIp]] text-input"/><td rowspan="3" style="padding-left:6px"><input  type="button" value="&nbsp;删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除&nbsp;" onclick="del('+i+')" class="btnstyle" /></td></tr><tr id="kdel13_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel14_' + i + '"><td>  <input type="test" name="collectorGroup" id="collectorGroup"/></td></tr>');
							i++;
						});
		$("#del").click(function() {
			var $checked = $("input:checkbox:checked");
			for ( var k = 0; k < $checked.length; k++) {
				var $tr = $checked[k].id;
				console.log($tr);
				$("#k" + $tr).remove();
			}
		});

		$("#delAll").click(function() {
			if ($(this).is(":checked")) {
				$("#testTbl input:checkbox").attr("checked", "true");
			} else {
				$("#testTbl input:checkbox").removeAttr("checked");
			}

		});

	});

	function add() {
		alert("hee");
	}

	function del(i) {
	
		 $("#kdel_"+i).remove();
		
		 $("#kdel1_"+i).remove();
		 
		 $("#kdel2_"+i).remove();
		 
		 $("#kdel3_"+i).remove();
		
		 $("#kdel11_"+i).remove();
		 
		 $("#kdel12_"+i).remove();
		 
		 $("#kdel13_"+i).remove();
		 
		 $("#kdel14_"+i).remove();
	}
	function subdata() {
		$("form").submit();
	}
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="setting-localCollector" name="setting-localCollector"
		action="savelocalCollector" namespace="/settingLocalCollector"
		method="post" theme="simple">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">

						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>本机采集器配置</b><font id="message1"></font>
								</td>
							</tr>
							<tr>
								<td align="center">
								</td>
							</tr>

							<tr>
								<td>
									<table width="98%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<tr><td><font color="red"><s:actionmessage /></font></td></tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td width="30%" colSpan="5"><h4>SNMP WALK</h4></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colspan="2">端口 </td>
											<td colspan="2"><input type="text"
												id="collectorWalkPort" name="collectorWalkPort"
												value="${collectorWalkPort}"
												class="validate[custom[settingPort]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colspan="2">轮循间隔 </td>
											<td colspan="2"><input type="text" id="collectorTime"
												name="collectorTime" value="${collectorTime}"
												class="validate[custom[settingNumber]] text-input " />秒</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colspan="2">snmp信息库导入 </td>
											<td colspan="2"><input type="file" id="collectorEnter"
												name="collectorEnter" value="${collectorEnter}" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<s:iterator value="ipList" status="stat">
											<s:if test="#stat.First">
											<tr>
												<td width="40%" colspan="2">IP地址 </td>
												<td width="13.5%">
													<input type="text" id="collectorIp" name="collectorIp" value="${ip0}"
													class="validate[custom[settingIp]] text-input " />
												</td>
												<td rowspan="3"><input id="add" type="button" value=" 新      增  " class="btnstyle" /></td>
											</tr>
											<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
											<tr>
												<td colspan="2">团体名 </td>
												<td>
													<input type="text" id="collectorGroup"
													name="collectorGroup" value="${group0}"
													class="validate[required,custom[validateLength],custom[spechars]] text-input " />
												</td>
												</tr>
											</s:if>
											<s:else>
											<tr>
											<td width="40%" colspan="2">IP地址 
											<td>
													<input type="text" id="collectorIp" name="collectorIp"
													value="${ip0}"
													class="validate[custom[settingIp]] text-input " />
											</td>
											<td rowspan="2">
													<input type="button" value=" 删      除  " onclick="del(this)" class="btnstyle" />
												</td>
											</tr>
											<tr>
												<td colspan="2">团体名 
												<td>
													<input type="text" id="collectorGroup"
													name="collectorGroup" value="${group0}"
													class="validate[required,custom[validateLength],custom[spechars]] text-input " />
												</td>
												</tr>
												</s:else>
										</s:iterator>
										<tr>
											<td colspan="2">
												<table id="testTbl" border="0" cellspacing="0" cellpadding="0">
													<tbody></tbody>
												</table>
											</td>
											<td colspan="2">
												<table id="testTbl1" border="0" cellspacing="0" cellpadding="0">
													<tbody></tbody>
												</table>
											</td>
										</tr>
											<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
											<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colspan="4"><h4>SNMP TRAP</h4></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colSpan="2">端口 </td>
											<td colSpan="2"><input type="text" id="collectorTrip"
												name="collectorTrip" value="${collectorTrip}"
												class="validate[custom[settingPort]] text-input " />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
											<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colspan="4"><h4>SYSLOG</h4></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colSpan="2">端口 </td>
											<td colSpan="2"><input type="text" id="collectorLog"
												name="collectorLog" value="${collectorLog}"
												class="validate[custom[settingPort]] text-input " />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
											<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colspan="4"><h4>AGENT</h4></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colSpan="2">端口 </td>
											<td colSpan="2"><input type="text"
												id="collectorAgentPort" name="collectorAgentPort"
												value="${collectorAgentPort}"
												class="validate[custom[settingPort]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colSpan="2">实时监控</td>
											<td colSpan="2"><input type="text" id="collectorUpTime"
												name="collectorUpTime" value="${collectorUpTime}"
												class="validate[custom[settingNumber]] text-input " />秒</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colSpan="2">轮循日志</td>
											<td colSpan="2"><input type="text"
												id="collectorIntervalTime" name="collectorIntervalTime"
												value="${collectorIntervalTime}"
												class="validate[custom[settingNumber]] text-input " />秒</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
											<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colspan="4"><h4>范式化</h4></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<tr>
											<td colSpan="2">轮循间隔 </td>
											<td colSpan="2"><input type="text" id="collectorPattern"
												name="collectorPattern" value="${collectorPattern}"
												class="validate[custom[settingNumber]] text-input " />秒</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
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
					value="保存设置" onclick="subdata()" />
				</td>
			</tr>
		</table>
		<!-- <div id="setting-addcorrespond" title="新添加IP及团体名">
		<table width='96%' border="0" cellspacing="5" cellpadding="5"
			align='center'>
			<tr>
				<td align="center" style="width: 30%">IP地址：&nbsp;&nbsp;</td>
				<td align="left" width="60%"><input type="text"
					id="newcollectorIp" name="newcollectorIp" />
				</td>
			</tr>
			<tr>
				<td align="center">团体名：&nbsp;&nbsp;</td>
				<td><input type="text" id="newcollectorGroup"
					name="newcollectorGroup" />
				</td>
			</tr>
		</table>
	</div> -->
	</s:form>
</body>
</html>