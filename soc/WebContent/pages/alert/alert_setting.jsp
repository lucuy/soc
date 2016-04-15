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

<link href="/soc/styles/style.css" rel="stylesheet" type="text/css">
<link href="/soc/styles/user/user.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
    type="text/css"> 
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script>
	function subdata() {
		$('#mails').val('');
		$("#targetPorts").children("option").each(function() {
			if ($(this).parent("select").size() > 0) {
				$('#mails').val($('#mails').val() + $(this).val() + ",");
				
			}
		}); 
		//alert($('#mails').val());
		 $('#present').val($("#presentSel").val());
		$("form").submit(); 
	}
	jQuery(document).ready(function() {
		//目标端口
		$('#dialog_targetPort').dialog({
			autoOpen : false,
			width : 400,
			height : 150,

			buttons : {
				"确定[Enter]" : function() {
					addMail();
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
	});
	function showIpDlg() {
		//alert(1);
		$("#targetPortStart").val("");
		
		//document.getElementById(div_ip).innerHTML = "";
		$("#div_targetPort").val("");
		//$("#"+div_ipEnd).html("");
		$("#dialog_targetPort").dialog('open');
		//$('#dialog_addressPolicy').focus();
	}
	//验证起始IP段合法性
	//参数意义ipStart:输入ip框框的id;div_ipStart:提示ip不合法的框框的id
	function checkMail(ipStart,div_ipStart) {
		var ipStart = $("#"+ipStart).val();
		if (ipStart != "") {
			
			 var re=/^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/;//匹配邮箱
			  
			if (re.test(ipStart)) {	
				$("#"+div_ipStart).removeClass("spanred");
				$("#"+div_ipStart).html(
						"<img border=0 src=\"${ctx}/images/ok.png\" />");
				return true;
				
			}else{
				$("#"+div_ipStart).addClass("spanred");
				$("#"+div_ipStart).html("邮箱不合法");
				return false;
			}
			
		} else {
			$("#"+div_ipStart).html("");
			return true;
		}
	}
	function addMail(){
		var re=/^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/;//匹配邮箱
		var e = $("#targetPorts");
		var a = $("#targetPortStart").val();
		if(re.test(a)){
			e.append("<option value=\""
					+$("#targetPortStart").val()+"\">" +$("#targetPortStart").val()+"</option>");
			$("#dialog_targetPort").dialog('close');
		}		
	}	
</script>
</head>
<body style="margin-top: 2px">
	<s:form id="alert-setting" action="saveAlert.action"
		namespace="/alertSetting" method="post" theme="simple"
		name="alert-setting">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>告警配置</b>&nbsp;&nbsp;&nbsp;
									<font id="message1"></font></td>
							</tr>

							<tr>
								<td align="center"><font color="red"><s:actionmessage />
								</font>
								</td>
							</tr>

							<tr>
								<td>
									<table width="98%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td><h4>平台提示配置</h4></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="45%" align="right">启用平台提示告警&nbsp;&nbsp;：</td>
											<td align="left" width="60%"><input type="checkbox"
												id="alertTerrace" name="alertTerrace" value="1"
												<c:if test="${alertTerrace==1}">checked="checked"</c:if> /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 空行 -->
										<%--<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="45%" align="right">告警提示间隔&nbsp;&nbsp;：</td>
											<td align="left" width="60%">
											<select style="width: 15%">
											<option>5分钟</option>
											<option>10分钟</option>
											<option>15分钟</option>
											
											</select>
											</td>
										</tr>
										
										--%><!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td><h4>syslog告警配置</h4></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="45%" align="right">启用syslog提示告警&nbsp;&nbsp;：</td>
											<td align="left" width="60%"><input type="checkbox"
												id="alertSys" name="alertSys" value="1"
												<c:if test="${alertSys==1}">checked="checked"</c:if> /></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- <tr>
											<td><h4>磁盘告警配置</h4></td>
										</tr>
										空行
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										水平线
										<tr>
											<td colSpan="4"><div class="xuxian"></div></td>
										</tr>
										空行
											<tr>
											<td class="td_detail_separator"></td>
										</tr> -->
									<%-- <tr valign="top">	
										<td width="15%" align="right">告警临界点&nbsp;&nbsp;：</td>
											<td width="60%" align="left" >
												<select 
												id="presentSel" name = "targetPorts" style="width:100px" >
														<option value = "60" <c:if test="${present==60}">selected="selected"</c:if>>60%</option>
														<option value = "70" <c:if test="${present==70}">selected="selected"</c:if>>70%</option>
														<option value = "80" <c:if test="${present==80}">selected="selected"</c:if>>80%</option>
														<option value = "90" <c:if test="${present==90}">selected="selected"</c:if>>90%</option>
														</select></td>
														<input type = "hidden" id = "present" name = "present">
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
									<%-- 	</tr>
									<tr valign="top">	
										<td width="15%" align="right">告警邮箱&nbsp;&nbsp;：</td>

											<td width="60%" align="left" >
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="targetPorts" name = "targetPorts" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="mailList" status="stat" id = "id">
																		<option value="${id}" id="deviceTypeid">${id}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="mails" id="mails" />
													</tr>
													
												</table></td>
										</tr>
		
													<td height="2px"></td>
										</tr> --%>
										<!-- <tr>
											<td></td>
											<td>
												<table width="98%" border="0" cellspacing="0"
													cellpadding="0" style="align:right">
													<tr>
														<td align="left" ><input
															type="button" value="" class="btnadd"
															onclick="showIpDlg();" /> <input type="button"
															value="" class="btndel" onclick="delOption('targetPorts','');" /> <br /></td>
													</tr>
												</table></td>
										</tr> -->
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									</table></td>
							</tr>
						</table>
					</div></td>
			</tr>

			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="保存设置" onclick="subdata()" /></td>
			</tr>
		</table>
	</s:form>
	<!-- 目标ip的dialog  -->
<div id="dialog_targetPort" title="告警邮箱">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
		<tr>
		<td class="row" align="right" width ="20%"><span>告警邮箱：</span></td>
			<td><input type="text" id="targetPortStart" name="targetPortStart" size="20"
					width="100px" onblur="checkMail('targetPortStart','div_targetPortStart');" />
			</td>
		<td width="25%"><span id="div_targetPortStart"></span></td>
		</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td></td>
				<td><span style="color:#363f46;" id="div_targetPort"></span></td>
			</tr>
		</table>
</div>
	
</body>
</html>