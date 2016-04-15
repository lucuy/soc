<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript"
	src="${ctx}/pushlet/ajax-pushlet-client.js"></script>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
.number {
	float: right;
	margin: 0px 10px 0px 0px;
	padding: 0px 0px;
}

.number_label {
	font-size: 12px;
	color: #FFFFFF;
}

.syslog_div {
	margin: 0px auto;
	width: 400px;
	text-align: justify;
}

.lable_class_gestures {
	cursor: hand;
	cursor: pointer;
}

.list {
	list-style: none;
	width:1000px;
	float:left;
	margin-left:10px;
}

.list li {
	margin: 5px 0px;
	float: left;
}

.list_cen {
	line-height: 24px;
	float: left;
}

.list_cne_width_1 {
	width: 90px;
}

.list_cne_width_2 {
	width: 30px;
}

.list_cne_width_3 {
	width: 41px;
}

.lable_class2 {
	margin: 0px 5px 0px 0px;
	line-height: 25px;
	text-align: right;
	float: left;
	width: 80px;
}
</style>

<script>
	$(document).ready(function() {
		var syslog_json = '${sysLogJson}';
		showSysLogList(syslog_json);

		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 250,
			buttons : {
				"确定[Enter]" : function() {
					$("#error_ip").remove();
					$("#error_portl").remove();
					saveSysLog();
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

	});

	function showSysLogList(syslogJson) {
	
		if (null != syslogJson && "" != syslogJson) {
			var objs = $.parseJSON(syslogJson);
			
			$
					.each(
							objs,
							function(i, n) {
								var json = '{\"syslog_id\":\"' + n.syslog_id
										+ '\",\"host_ip\":\"' + n.host_ip
										+ '\",\"port\":\"' + n.port
										+ '\",\"protocol\":\"' + n.protocol
										+ '\",\"code\":\"' + n.code + '\"}';
								//alert(json);
								
								//alert(n.code);
						/* 		var rowNum = $("#bottomtable tr").length - 1;
								
								$("#bottomtable tr:not(:first)").remove();
								
								var htmlStr = "<tr>";
								htmlStr +="<td valign='middle' align='center' width='20%'>服务器主机:"
															+ n.host_ip;
								htmlStr +="</td>";
								htmlStr +="<td valign='middle' align='center' width='10%'>端口:"+n.port;
								htmlStr +="</td>";
								htmlStr +="<td valign='middle' align='center' width='10%'>编码格式:"+n.code;
								htmlStr +="</td>";
								htmlStr +="<td valign='middle' align='center' width='10%'>协议:"+n.protocol;
								htmlStr +="</td>";
								htmlStr +="<td valign='middle' align='center' width='10%'>级别:INFO";
								htmlStr +="</td>";
								htmlStr +="<td valign='middle' align='center' width='10%'>Facility:USER";
								htmlStr +="</td>";
								htmlStr +="<td valign='middle' align='center' width='30%'>操作:<input type='button'";
								htmlStr +="value='修改' class='select_button' onclick='addSySlog(1,"+n.syslog_id+"')/>";
								htmlStr +="<input type='button' value='删除' class='select_button' onclick='deltetSysLog("+n.syslog_id+")'>";
								htmlStr +="</td>";
								htmlStr +="</tr>"; */
							var list_html = $('<ul class="list">'
										+ '<li><label class="lable_class2">服务器主机：</label>'
										+ '<div class="list_cen list_cne_width_1">'
										+ n.host_ip
										+ '</div>'
										+ '</li>'
										+ '<li><label class="lable_class2">端口：</label>'
										+ '<div class="list_cen list_cne_width_2">'
										+ n.port
										+ '</div>'
										+ '</li>'
										+ '<li><label class="lable_class2">编码格式：</label>'
										+ '<div class="list_cen list_cne_width_3">'
										+ n.code
										+ '</div>'
										+ '</li>'
										+ '<li><label class="lable_class2">协议：</label>'
										+ '<div class="list_cen list_cne_width_2">'
										+ n.protocol
										+ '</div>'
										+ '</li>'
										+ '<li><label class="lable_class2">级别：</label>'
										+ '<div class="list_cen">INFO</div>'
										+ '</li>'
										+ '<li><label class="lable_class2">Facility：</label>'
										+ '<div class="list_cen">USER</div>'
										+ '</li>'
										+ '<li><label class="lable_class2">操作：</label><input type="button"'
										+ 'value="修改" class="btnstyle" onclick="addSySlog(1,\''
										+ n.syslog_id
										+ '\');" />&nbsp;&nbsp;&nbsp;'
										+ '<input type="button" value="删除" class="btnstyle" onclick="deltetSysLog(\''
										+ n.syslog_id
										+ '\');"/>'
										+ '<input type="hidden" id="'+n.syslog_id+'"/>'
										+ '</li>' + '</ul>');
								
								$(list_html).prependTo("#syslog_list");
							/* 	$(htmlStr).insertAfter($("#bottomtable tr:eq("
+ rowNum */
																			/* + ")")); */
								$("#" + n.syslog_id).val(json);
							});
		}
	}

	function deltetSysLog(id) {
		location.href = "${ctx}/settingSysLog/delete.action?sysLogJsonId=" + id;
	}

	function addSySlog(key, id) {
		if (key == '0') {
			var temp = getSerialNumber();
			$("#syslog_id").val(temp);
		} else {
			var syslogJson = $("#" + id).val();
			if (null != syslogJson && "" != syslogJson) {
				var obj = $.parseJSON(syslogJson);
				if (null != obj) {
					$("#syslog_id").val(obj.syslog_id);
					$("#host_ip").val(obj.host_ip);
					$("#port").val(obj.port);
					if ("UDP" == obj.protocol)
						$("#P_UDP").attr("checked", true);
					else
						$("#P_TCP").attr("checked", true);
					$("#code").val(obj.code);
				}
			}

		}
		$('#dialog-extQuery').dialog("open");
		
	}

	function saveSysLog() {
		var syslog_id = $("#syslog_id").val();
		var host_ip = $("#host_ip").val();
		var port = $("#port").val();
		if(!checkIP("#host_ip")){
			var error = $('<font color="red" id="error_ip">&nbsp;请输入正确的IP地址</font>');
			$("#error_ip").remove();
			$("#host_ip").after(error);
			return;
		}else{
			$("#error_ip").remove();
		}
		if(!checkPort("#port")){
			var error = $('<font color="red" id="error_portl">&nbsp;请输入正确的端口</font>');
			$("#error_portl").remove();
			$("#port").after(error);
			return;
		}else{
			$("#error_portl").remove();
		}
		var protocols = $("input[name='protocol']");
		var code = $("#code").val();
		var protocol = null;
		$.each(protocols, function(i, n) {
			if ($(n).attr("checked"))
				protocol = $(n).val();
		});
		var syslog_json = '{"syslog_id":"' + syslog_id + '","host_ip":"'
				+ host_ip + '","port":"' + port + '","protocol":"' + protocol
				+ '","code":"' + code + '"}';
		$("#sysLogJson").val(syslog_json);
		
		$("#sysLogJsonForm").submit();
		$('#dialog-extQuery').dialog("close");
	}

	function getSerialNumber() {
		var date = new Date();
		var year = String(date.getYear());
		var month = String(date.getMonth() + 1);
		var day = String(date.getDate());
		var hours = String(date.getHours());
		var minutes = String(date.getMinutes());
		var seconds = String(date.getSeconds());
		var Milliseconds = String(date.getMilliseconds());
		var randoms = String(Math.random() * 1000).substring(0, 2);
		if (month.length < 2) {
			month = "0" + month;
		}
		if (day.length < 2) {
			day = "0" + day;

		}
		if (hours.length < 2) {
			hours = "0" + hours;
		}
		if (minutes.length < 2) {
			minutes = "0" + minutes;
		}
		if (seconds.length < 2) {
			seconds = "0" + seconds;
		}
		if (Milliseconds.length == 1) {
			Milliseconds = "000" + Milliseconds;
		}
		if (Milliseconds.length == 2) {
			Milliseconds = "00" + Milliseconds;
		}
		if (Milliseconds.length == 3) {
			Milliseconds = "0" + Milliseconds;
		}
		return hours + minutes + seconds + Milliseconds + randoms;
	}

	function checkIP(evn) {
		var val = $(evn).val();
		if ("" != val) {
			var reg = /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/;
			if (!reg.test(val)) {
				var error = $('<font color="red" id="error_ip">&nbsp;请输入正确的IP地址</font>');
				$("#error_ip").remove();
				$(evn).after(error);
				return false;
			} else {
				$("#error_ip").remove();
				return true;
			}
		} else {
			return false;
		}
	}

	function checkPort(evn) {
		var val = $(evn).val();
		if ("" != val) {
			var reg = /^(\d{1,4}|([1-5]\d{4})|([1-6][1-4]\d{3})|([1-6][1-4][1-4]{2})|([1-6][1-4][1-4][1-2]\d)|([1-6][1-5][1-5][1-3][1-5]))$/;
			if (!reg.test(val)) {
				var error = $('<font color="red" id="error_portl">&nbsp;请输入正确的端口</font>');
				$("#error_portl").remove();
				$(evn).after(error);
				return false;
			} else {
				$("#error_portl").remove();
				return true;
			}
		} else {
			return false;
		}
	}
</script>
</head>

<body style="margin-top: 2px">
	<s:form id="setting-checking" action="update.action"
		namespace="/setting-checking" method="post" theme="simple"
		name="setting-checking">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>SYSLOG设置</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font></td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font>
								</td>
							</tr>

							<tr>
								<td align="center" width="100%">
									<div
										id="syslog_list">
										<!-- <table width="100%" class="eventslist" cellspacing="1"
											cellpadding="0" id="bottomtable" style="font-size: 13px">

											<tr>
												<td width="20%">事件空</td>
												<td width="10%">事件空</td>
												<td width="10%">事件空</td>
												<td width="10%">事件空</td>
												<td width="10%">事件空</td>
												<td width="10%">事件空</td>
												<td width="30%">事件空</td>
											</tr>


										</table>
 -->
									</div></td>
							</tr>
						</table>
					</div></td>
			</tr>

			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="添加" onclick="addSySlog(0)" /></td>
			</tr>
		</table>
	</s:form>

	<s:form action="update.action" namespace="/settingSysLog" method="post"
		theme="simple" id="sysLogJsonForm">
		<input type="hidden" name="sysLogJson" id="sysLogJson" />
	</s:form>

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="添加/编辑">
		<table width="99%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:5px;">
			<tr>
				<td width="30%">服务器主机:</td>
				<td><input id="host_ip" name="host_ip" type="text"
					style="width:160px;" onblur="checkIP(this);" /> <span
					id="check_Name"></span> <input type="hidden" name="syslog_id"
					id="syslog_id">
				</td>
			</tr>
			<tr>
				<td>端口:</td>
				<td><input id="port" name="port" type="text"
					style="width:160px;" onblur="checkPort(this)" />
					<span
					id="check_Port"></span>
					</td>
			</tr>
			<tr>
				<td>编码格式:</td>
				<td><select style="width:130px;" id="code">
						<option value="UTF-8">UTF-8</option>
						<option value="GBK">GBK</option>
						<option value="GB2312">GB2312</option>
						<option value="GB18030">GB18030</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>协议:</td>
				<td><input type="radio" name="protocol" value="UDP"
					checked="checked" id="P_UDP" />UDP <input type="radio"
					name="protocol" value="TCP" id="P_TCP" />TCP</td>
			</tr>
			<tr>
				<td>级别:</td>
				<td>INFO</td>
			</tr>
			<tr>
				<td>Facility:</td>
				<td>USER</td>
			</tr>

		</table>
	</div>
</body>
</html>