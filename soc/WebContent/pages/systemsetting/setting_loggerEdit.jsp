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
<script>
	jQuery(document).ready(function() {
		jQuery("#setting-localCollector").validationEngine();
		$('#dialog-addressPolicy').dialog({
			autoOpen : false,
			width : 400,
			height : 140,

			buttons : {
				"确定[Enter]" : function() {
					address();
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
	});
	var i = 1;
	$(function() {
		$("#add")
				.click(
						function() {
							$("#testTbl")
									.append(
											'<tr id="kdel_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel1_' + i + '"><td height="37">IP地址</td></tr><tr id="kdel2_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel3_' + i + '"><td>团体名</td></td></tr>');
							$("#testTbl1")
									.append(
											'<tr id="kdel11_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel12_' + i + '"><td width="13.5%"><input type="text" id="collectorIp" name="collectorIp" class="validate[custom[settingIp]] text-input"/><td rowspan="3" style="padding-left:6px"><input  type="button" value="&nbsp;删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除&nbsp;" onclick="del('
													+ i
													+ ')" class="btnstyle" /></td></tr><tr id="kdel13_' + i + '"><td class="td_detail_separator"></td></tr><tr id="kdel14_' + i + '"><td>  <input type="test" name="collectorGroup" id="collectorGroup"/></td></tr>');
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
	var existflag ;
	function selectMac() {
		var addressName = $("#collectorMac").val();
		if(addressName != ""){
			var regex=addressName.match(/^($)|(([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}$)/); 
				
			if(regex==null){
				$("#check_loginname_msg").addClass("spanred");
				$("#check_loginname_msg").html("  请输入正确的MAC地址");
				existflag = true;
			}else{
				$("#check_loginname_msg").html("");
			if(addressName==$("#collectorMac1").val()){
				existflag = false;
			}else{
			$.ajax({
				type : "post",
				url : "${ctx}/settingLogger/selectMac.action",
				dataType : "text",
				data : "&collectorMac=" + addressName,
				success : function(result) {
					if (result == 'true') {
						existflag = true;
						$("#check_loginname_msg").addClass("spanred");
						$("#check_loginname_msg").html("该MAC已占用");
						/* $("#addressName").focus(); */
	
					} else {
						existflag = false;
						$("#check_loginname_msg").removeClass('spanred');
						$("#check_loginname_msg").html(
								"<img border=0 src=\"${ctx}/images/ok.png\" />");
					}
				}
			});
			}
			}
		}else{
			existflag = true;
			$("#check_loginname_msg").addClass("spanred");
			$("#check_loginname_msg").html("  MAC不能为空！");
		}
	}
	function del(i) {

		$("#kdel_" + i).remove();

		$("#kdel1_" + i).remove();

		$("#kdel2_" + i).remove();

		$("#kdel3_" + i).remove();

		$("#kdel11_" + i).remove();

		$("#kdel12_" + i).remove();

		$("#kdel13_" + i).remove();

		$("#kdel14_" + i).remove();
	}

	function subdata() {
		var count = $("#ips").find("option").length;
		for (i = 0; i < count; i++) {
			if ($("#collectorOid").val() == "") {
				$("#collectorOid").val($("#ips").get(0).options[i].text);
			} else {
				$("#collectorOid").val(
						$("#collectorOid").val() + ","
								+ $("#ips").get(0).options[i].text);
			}
		}
		if(existflag==true){
			alert("请检查MAC地址！");
			}else{
			$("form").submit();
			}
	}

	//验证IP地址合法性
	function address() {
		var regex=$("#ipStart").val().match(/^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/); 
		if(regex==null){
			alert("请输入合法的IP地址");
			return;
		}
		var e = $('#ips');
		e.append("<option>" + $("#ipStart").val() + "</option>");
		$('#dialog-addressPolicy').dialog('close');

	}

	//弹出地址策略对话框		
	function addPolicyDlg(type) {
		//地址策略
		$("#ipStart").val("");
		document.getElementById("div_ip").innerHTML = "";
		$("#div_ipStart").val("");
		$('#dialog-addressPolicy').dialog('open');
		$('#dialog-addressPolicy').focus();
	}
	//删除ip地址段
	function del() {
		$("select[name=ips]").find("option:selected").remove();
	}
	//用户点击保存按钮
	/* var i = 0;
	function subData() {
	alert();
		var count = $("#ips").find("option").length;
		alert(count);
				for (i; i < count; i++) {
					if ($("#collectorOid").val() == "") {
						$("#collectorOid").val($("#ips").get(0).options[i].text);
					} else {
						$("#collectorOid").val(
								$("#collectorOid").val() + ";"
										+ $("#ips").get(0).options[i].text);
					}
				}
	} */
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="setting-localCollector" name="setting-localCollector" 
		action="updateId" namespace="/settingLogger" method="post"
		theme="simple">
		<s:hidden name="collectorId" id="collectorId" />
		<s:hidden name="collectorOid" id="collectorOid" />
		<s:iterator value="collectorList">
			<!--  左侧table-->
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 0px">
				<tr>
					<td width="100%" valign="top">
						<div class="framDiv">

							<!--  左侧table-->
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td class="r2titler"><b>修改采集器</b><font id="message1"></font>
									</td>
								</tr>

								<tr>
									<td align="right"><font color="red"><s:actionmessage />
									</font>
									</td>
								</tr>

								<tr>
									<td>
										<table width="98%" border="0" cellspacing="0" cellpadding="0"
											style="margin-left: 18px;" id="deltable">
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td colSpan="5"><h4>基本信息</h4>
												</td>
											</tr>
											<!-- 水平线 -->
											<tr>
												<td colSpan="4"><div class="xuxian"></div></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="5"></td>
											</tr>
											<tr>
												<td width="40%" colspan="2"><span class="spanred">*</span>采集器名称</td>
												<td><input name="collectorBasic" id="collectorBasic"
													value="${collectorBasic}" type="text"
													class="validate[required,custom[validateLength],custom[spechars]] text-input " />
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td colspan="2"><span class="spanred">*</span>MAC（唯一标识）</td>
												<td><input name="collectorMac" id="collectorMac" onblur="selectMac()"
													value="${collectorMac}" type="text" class="validate[required,custom[validateLength],custom[validateSpase]] text-input  " />
													<span id="check_loginname_msg"></span>&nbsp;&nbsp;<font color="red"><span>例如：00-00-00-00-00-00</span></font>
													<input type="hidden" id="collectorMac1" value="${collectorMac}"/>
													</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="5"></td>
											</tr>
											<!-- 水平线 -->
											<tr>
												<td colSpan="4"><div class="xuxian"></div>
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="5"></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="5"></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td width="30%" colSpan="5"><h4>snmp walk</h4>
												</td>
											</tr>
											<!-- 水平线 -->
											<tr>
												<td colSpan="4"><div class="xuxian"></div>
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td colspan="2">端口</td>
												<td colspan="2"><input type="text"
													id="collectorWalkPort" name="collectorWalkPort"
													value="${collectorWalkPort}"
													class="validate[custom[settingPort]] text-input " />
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td colspan="2">轮循间隔</td>
												<td colspan="2"><input type="text" id="collectorTime"
													name="collectorTime" value="${collectorTime}" maxlength="255"
													class="validate[custom[settingNumber]] text-input " />&nbsp;秒</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<%-- <tr>
												<td colspan="2">OID</td>
												<td colspan="2"><input type="text" id="collectorOid"
													name="collectorOid" value="${collectorOid}" /></td>
											</tr> --%>
											<tr>
												<td colspan="2">snmp信息库导入</td>
												<td colspan="2"><input type="file" id="collectorEnter"
													name="collectorEnter" value="${collectorEnter}" /></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td align="left" style="vertical-align:10%" colspan="2"><span
													class="spanred"></span>OID</td>
												<td align="left"><select name="ips" id="ips"
													style="width: 193px" size="5" multiple="multiple">
														<%-- <c:forEach items="${collectorOid}" var="l">
														<option value="${collectorOid}">${collectorOid}</option>
													</c:forEach> --%>
														<s:iterator value="ipList">
															<option value="${ip0}">${ip0}</option>
														</s:iterator>
												</select></td>
											</tr>

											<!-- <tr>
												<td height="2px"></td>
												<td>
													<table width="98%" border="0" cellspacing="0"
														cellpadding="0" style="align:right">
														<tr>
															<td align="left"><input type="button" value=""
																class="btnadd" onclick="addPolicyDlg();" /> <input
																type="button" value="" class="btndel" onclick="del();" />
																<br /></td>
														</tr>
													</table></td>
											</tr> -->
											<tr>
												<td colspan="2"></td>
												<td>
													<table width="98%" border="0" cellspacing="0"
														cellpadding="0" style="align:right">
														<tr>
															<td align="left"><input type="button" value=""
																class="btnadd" onclick="addPolicyDlg();" /> <input
																type="button" value="" class="btndel" onclick="del();" />
																<br /></td>
														</tr>
													</table></td>
											</tr>
											<%-- <s:iterator value="ipList" status="stat">
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
											<!-- 空行 -->
										<tr>
											<td class="td_detail_separator" colSpan="4"></td>
										</tr> --%>
											<tr>
												<td colspan="2">
													<table id="testTbl" border="0" cellspacing="0"
														cellpadding="0">
														<tbody></tbody>
													</table></td>
												<td colspan="2">
													<table id="testTbl1" border="0" cellspacing="0"
														cellpadding="0">
														<tbody></tbody>
													</table></td>
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
												<td colspan="4"><h4>snmp trap</h4>
												</td>
											</tr>
											<!-- 水平线 -->
											<tr>
												<td colSpan="4"><div class="xuxian"></div>
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td colSpan="2">端口</td>
												<td colSpan="2"><input type="text" id="collectorTrip"
													name="collectorTrip" value="${collectorTrip}"
													class="validate[custom[settingPort]] text-input " /></td>
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
												<td colspan="4"><h4>syslog</h4>
												</td>
											</tr>
											<!-- 水平线 -->
											<tr>
												<td colSpan="4"><div class="xuxian"></div>
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td colSpan="2"><span class="spanred">*</span>端口</td>
												<td colSpan="2"><input type="text" id="collectorLog"
													name="collectorLog" value="${collectorLog}"
													
													class="validate[required,custom[settingPort]] text-input " /></td>
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
											<tr>
												<td colspan="4"><h4>范式化</h4>
												</td>
											</tr>
											<!-- 水平线 -->
											<tr>
												<td colSpan="4"><div class="xuxian"></div>
												</td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator" colSpan="4"></td>
											</tr>
											<tr>
												<td colSpan="2">轮循间隔</td>
												<td colSpan="2"><input type="text"
													id="collectorPattern" name="collectorPattern"
													value="${collectorPattern}"
													class="validate[custom[settingNumber]] text-input " />&nbsp;秒</td>
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
											<td colSpan="2"><h4>网络环境</h4></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="3"><div class="xuxian"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>网络环境</td>
											<td colSpan="2">
											<select id="collectNetwork" name="collectNetenvironment" style="width:20%;">
											  <option value="0">内网</option>
											  <option value="1">外网</option>
											</select>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td colSpan="2"><h4>外网配置</h4></td>
										</tr>
										<!-- 水平线 -->
										<tr>
											<td colSpan="3"><div class="xuxian"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td>外网IP：</td>
											<td colSpan="2"><input type="text"
												id="collectorIp" name="collectorIp" value="${collectorIp}"
												class="validate[custom[settingIp]] text-input " /></td>
										</tr>
										<tr>
											<td>外网端口：</td>
											<td colSpan="2"><input type="text" id="collectorAgentPort"
												name="collectorAgentPort" value="${collectorAgentPort}"
												class="validate[custom[settingPort]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										</table></td>
								</tr>
							</table>
						</div></td>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="保存设置" onclick="subdata()" /></td>
			</tr>
		</table>
		</s:iterator>
	</s:form>
	
	<!-- 地址策略选择的dialog  -->
	<div id="dialog-addressPolicy" title="OID输入">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<tr>
				<td width="25%">OID</td>
				<td><input type="text" id="ipStart" name="ipStart" size="20" maxlength="255"
					width="100px" onblur="checkIpStart();" />
				</td>
				<td width="25%"><span id="div_ipStart"></span></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr>
				<td></td>
				<td><span style="color: #363f46;" id="div_ip"></span></td>
			</tr>
		</table>
	</div>
</body>
</html>