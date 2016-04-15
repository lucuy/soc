<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<link href="${ctx}/styles/doubleMachine/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/doubleMachine/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/doubleMachine/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<style type="text/css">
.ha_mes {
	font-size: 12px;
}

.ha_mes ul {
	margin: 8px 5px;
	padding: 0px 0px;
	list-style: none;
	float: left;
}

.ha_mes ul li {
	margin: 9px 5px;
	padding: 0px 0px;
}

.label_st {
	margin: 0px 0px;
	padding: 0px 0px;
	width: 100px;
	text-align: right;
	display: block;
	float: left;
	font-size: 13px;
	font-weight: 500;
}
.select_button{
	margin:0px 0px;
	padding:0px 0px;
	background: url(../images/btnbg_sv.png) 0 0 no-repeat;
	border: none;
	width: 56px;
	height: 22px;
	text-align: center;
	line-height: 26px;
	color: #FFFFFF;
	cursor: hand ;
	cursor: pointer  ;
	font-weight: bolder;
}
</style>
<script>
	$(document).ready(function() {
		init();
	});
	function init() {
		var serverStart = '${serverStart}';
		var obj = $.parseJSON(serverStart);
		$("#host").text(obj.host);
		$("#disk_syn").text(obj.disk_syn);
		<%--var ldapStart = '${ldapStart}'; 
		var obj = $.parseJSON(ldapStart);
		$("#ldap_host").text(obj.host);
		$("#ldap_disk_syn").text(obj.disk_syn);--%>
	}

	function queryHAProfessional(iden) {
		$.post("${ctx}/settingNetwork/queryHAProfessional.action", {
			"type" : 5,
			"iden" : iden
		}, function(result) {
			switch (parseInt(iden)) {
			case 0:
				$("#text_drbd").val(result);
				break;
			case 1:
				$("#ldap_text_drbd").val(result);
				break;
			}
		});
	}
	function fulsh(iden) {
		switch (parseInt(iden)) {
		case 0:
			queryHAProfessional(iden);
		setInterval(function() {
			queryHAProfessional(iden);
		}, 5000);
			break;
		case 1:
			queryHAProfessional(iden);
		setInterval(function() {
			queryHAProfessional(iden);
		}, 5000);
			break;
		}
		
	}
	function standby(iden) {
		$.post("${ctx}/settingNetwork/queryHAProfessional.action", {
			"type" : 0,
			"iden" : iden
		}, function(result) {
			switch (parseInt(iden)) {
			case 0:
				$("#text_drbd").val(result);
				break;
			case 1:
				$("#ldap_text_drbd").val(result);
				break;
			}
		});
	}
</script>
</head>

<body>
	<div class="win_div_bor">
		<div class="win_div">
			<ul class="ul">
				<li class="li"><span>Server状态</span></li>
			</ul>
			<div class="ha_mes">
				<ul style="width:40%;">
					<li><label class="label_st">服务主机：</label><span id="host"></span>
					</li>
					<li><label class="label_st">磁盘状态：</label><span id="disk_syn"></span>
					</li>
					<li><label class="label_st">操作：</label>
					<input type="button"
						value="切换" class="select_button" style="margin-top:-3px;"
						onclick="standby('0');" /></li>
				</ul>
				<ul style="width:55%;">
					<li><label>专业模式：</label>&nbsp;&nbsp;<a
						href="javascript:;" onclick="queryHAProfessional('0');">刷新</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="radio" style="margin-top:-2px;" id="fulsh"
						onclick="fulsh('0');" />&nbsp;<label for="fulsh"  style="cursor: hand ;cursor:pointer ;">自动刷新<span
							style="font-size: 11px;">(5秒)</span> </label></li>
					<li><textarea rows="10" cols="75" id="text_drbd"></textarea></li>
				</ul>
			</div>
		</div>

	</div>
	<br />
	<br />
	<br />
	<%--<div class="win_div_bor">
		<div class="win_div">
			<ul class="ul">
				<li class="li"><span>LDAP状态</span></li>
			</ul>
			<div class="ha_mes">
				<ul style="width:40%;">
					<li><label class="label_st">服务主机：</label><span id="ldap_host"></span>
					</li>
					<li><label class="label_st">磁盘状态：</label><span
						id="ldap_disk_syn"></span></li>
					<li><label class="label_st">操作：</label><input type="button"
						value="切换" class="select_button" style="margin-top:-3px;"
						onclick="standby('1');" /></li>
				</ul>
				<ul style="width:55%;">
					<li><label style="font-size: 13px;font-weight:500;">专业模式：</label>&nbsp;&nbsp;<a
						href="javascript:queryHAProfessional('1');">刷新</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="radio" style="margin-top:-2px;" id="ldap_fulsh"
						onclick="fulsh('1');" />&nbsp;<label for="ldap_fulsh" style="cursor: hand ;cursor:pointer ;">自动刷新<span
							style="font-size: 11px;">(5秒)</span> </label></li>
					<li><textarea rows="10" cols="75" id="ldap_text_drbd"></textarea>
					</li>
				</ul>
			</div>
		</div>
	</div>
--%></body>
</html>