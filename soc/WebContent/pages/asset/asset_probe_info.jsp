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

<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>

<script language="javascript">
	jQuery(document)
			.ready(
					function() {

						jQuery("#assetForm").validationEngine();

						//资产组dialog
						$('#dialog-assetGroupDialog').dialog({
							autoOpen : false,
							width : 300,
							height : 400,
							buttons : {
								"确定[Enter]" : function() {

									addAssetGroup();

									$(this).dialog("close");
								},

								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
						//初始化资产组
						initRadio('assetGroupId', 'assetGroupId');

						//监控目录
						$('#dialog-addAccount').dialog({
							autoOpen : false,
							width : 400,
							height : 150,
							buttons : {
								"确定[Enter]" : function() {
									directoriseAddAccount();
								},
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
						//监控文件
						$('#dialog-fireAccount').dialog({
							autoOpen : false,
							width : 400,
							height : 150,
							buttons : {
								"确定[Enter]" : function() {
									fireAddAccount();
								},
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
						//特权命令
						$('#dialog-assetPrivilegeCommandAccount').dialog({
							autoOpen : false,
							width : 400,
							height : 150,
							buttons : {
								"确定[Enter]" : function() {
									assetPrivilegeCommandAddAccount();
								},
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});

						if (/msie/i.test(navigator.userAgent)) //ie浏览器 
						{
							//document.getElementById('assetName').onpropertychange = checkAssetName;
						} else {//非ie浏览器，比如Firefox 
							//document.getElementById('assetName')
							//		.addEventListener("input", checkAssetName,
								//			false);
						}

						if ('${asset.assetUnName}' == '0') {

							document.getElementById("hiddenVersion").style.display = "block";
							document.getElementById("hiddens1").style.display = "block";
							document.getElementById("hiddenGroup").style.display = "block";
							document.getElementById("hiddens2").style.display = "block";

							document.getElementById("hiddendirectoriseasd").style.display = "none";
							document.getElementById("hiddendirectorise").style.display = "none";
							document.getElementById("hiddens3").style.display = "none";
							document.getElementById("hiddenfileasd").style.display = "none";
							document.getElementById("hiddenfile").style.display = "none";
							document.getElementById("hiddens4").style.display = "none";
							document.getElementById("hiddenPrivilegeCommand").style.display = "none";
							document
									.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
							document.getElementById("hiddens5").style.display = "none";
							document.getElementById("APM1").style.display = "none";
							document.getElementById("APM2").style.display = "none";
							document.getElementById("APM3").style.display = "none";
							document.getElementById("APM4").style.display = "none";
							document.getElementById("APM5").style.display = "none";
							$('.asset1').css("display", "none");
							$('#apmhidden').css("display", "block");
							$('#hiddensys').hide();
							$('#hiddensnmp').show();
							$('#hiddenAgent').hide();
							$('#hiddenwindows').hide();
						} else if ('${asset.assetUnName}' == '1') {
							document.getElementById("hiddendirectoriseasd").style.display = "block";
							document.getElementById("hiddendirectorise").style.display = "block";
							document.getElementById("hiddens3").style.display = "block";
							document.getElementById("hiddenfileasd").style.display = "block";
							document.getElementById("hiddenfile").style.display = "block";
							document.getElementById("hiddens4").style.display = "block";
							document.getElementById("hiddenVersion").style.display = "none";
							document.getElementById("hiddens1").style.display = "none";
							document.getElementById("hiddenGroup").style.display = "none";
							document.getElementById("hiddens2").style.display = "none";
							document.getElementById("APM1").style.display = "block";
							document.getElementById("APM2").style.display = "block";
							document.getElementById("APM3").style.display = "block";
							document.getElementById("APM4").style.display = "block";
							document.getElementById("APM5").style.display = "block";
							$('.asset1').css("display", "block");
							$('#apmhidden').css("display", "none");
							$('#hiddensys').hide();
							$('#hiddensnmp').hide();

							if ('${asset.assetSupportDeviceId}' == 41) {
								document
										.getElementById("hiddenPrivilegeCommand").style.display = "none";
								document
										.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
								document.getElementById("hiddens5").style.display = "none";
								$('#hiddenwindows').show();
								$('#hiddenAgent').hide();
							} else {
								document
										.getElementById("hiddenPrivilegeCommand").style.display = "block";
								document
										.getElementById("hiddenPrivilegeCommandasd").style.display = "block";
								document.getElementById("hiddens5").style.display = "block";
								$('#hiddenwindows').hide();
								$('#hiddenAgent').show();
							}
						} else {

							document.getElementById("hiddendirectoriseasd").style.display = "none";
							document.getElementById("hiddendirectorise").style.display = "none";
							document.getElementById("hiddens3").style.display = "none";
							document.getElementById("hiddenfileasd").style.display = "none";
							document.getElementById("hiddenfile").style.display = "none";
							document.getElementById("hiddens4").style.display = "none";
							document.getElementById("hiddenPrivilegeCommand").style.display = "none";
							document
									.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
							document.getElementById("hiddens5").style.display = "none";
							document.getElementById("hiddenVersion").style.display = "none";
							document.getElementById("hiddens1").style.display = "none";
							document.getElementById("hiddenGroup").style.display = "none";
							document.getElementById("hiddens2").style.display = "none";
							document.getElementById("APM1").style.display = "none";
							document.getElementById("APM2").style.display = "none";
							document.getElementById("APM3").style.display = "none";
							document.getElementById("APM4").style.display = "none";
							document.getElementById("APM5").style.display = "none";
							$('.asset1').css("display", "none");
							$('#apmhidden').css("display", "block");
							$('#hiddensys').show();
							$('#hiddensnmp').hide();
							$('#hiddenAgent').hide();
							$('#hiddenwindows').hide();
						}
						//根据页面设备类型来加载设备种类
				//setTypeCategory();
					});

	//显示资产组
	function addAssetGroup() {

		var id = $('input:[name=assetGroupId]:radio:checked').val();

		if (id != undefined) {
			$("#assetGroupName").val($("#" + id + "_id").val());

			$("#assetGroupFeature").val($("#" + id + "_idF").val());

			$("#assetGroupId1").val(id);

		}
	}

	//显示添加资产组弹出框
	function extQueryDlg() {
		$('#dialog-assetGroupDialog').dialog('open');
	}

	function subData() {
		checkAssetIp();
		if ($('#assetName').val() != "" && $('#assetIp').val() != ""
				&& assetFlag == true && ipFlag == true) {

			var count = $("#directoriseSelect").find("option").length;
			for ( var i = 0; i < count; i++) {
				if ($("#disAll").val() == "") {
					$("#disAll").val(
							$("#directoriseSelect").get(0).options[i].text);
				} else {
					$("#disAll")
							.val(
									$("#disAll").val()
											+ ","
											+ $("#directoriseSelect").get(0).options[i].text);

				}
			}
			var count = $("#fireSelect").find("option").length;
			for ( var i = 0; i < count; i++) {
				if ($("#fireAll").val() == "") {
					$("#fireAll").val($("#fireSelect").get(0).options[i].text);
				} else {
					$("#fireAll").val(
							$("#fireAll").val() + ","
									+ $("#fireSelect").get(0).options[i].text);

				}
			}

			var count = $("#assetPrivilegeCommandSelect").find("option").length;
			for ( var i = 0; i < count; i++) {
				if ($("#commandAll").val() == "") {
					$("#commandAll")
							.val(
									$("#assetPrivilegeCommandSelect").get(0).options[i].text);
				} else {
					$("#commandAll")
							.val(
									$("#commandAll").val()
											+ ","
											+ $("#assetPrivilegeCommandSelect")
													.get(0).options[i].text);

				}
			}

			var status = $('#status').val();
			if (status == '启用') {
				$('#assetStatus').val("1");
			} else {
				$('#assetStatus').val("0");
			}

			$("#assetForm").submit();

		}

	}

	//判断资产是否存在
	var assetFlag;
	function checkAssetName() {
		if ('${asset.assetId}' == '') {
			if ($("#assetName").val() != "") {
				var assetName = $("#assetName").val();
				$
						.ajax({
							type : "post",
							url : "${ctx}/asset/checkAssetName.action",
							async : false,
							dataType : "text",
							data : "&assetName=" + assetName,
							success : function(result) {
								if (result == 'true') {
									$("#check_loginname_msg").addClass(
											"spanred");
									$("#check_loginname_msg").html("该名称已占用");
									assetFlag = false;
								} else {
									$("#check_loginname_msg").removeClass(
											"spanred");
									$("#check_loginname_msg")
											.html(
													"<img border=0 src=\"${ctx}/images/ok.png\" />");
									assetFlag = true;
								}
							}
						});

			} else {
				assetFlag = false;
			}
		} else {
			assetFlag = true;
		}
	}

	var ipFlag;
	//验证ip地址是否合法
	function checkAssetIp() {

		var ip = $("#assetIp").val();
		if (ip != "") {

			if ('${asset.assetIps}' != ip) {
				var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
				var result = re1.test(ip);
				if (result) {
					if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
						$("#div_ipEnd").addClass("spanred");
						$("#div_ipEnd").html("ip地址不合法");
						//return false; 
						ipFlag = false;
					} else {
						var mac = $("#assetIp").val();
						//采集器id
						var collectorId = $("#collectorIds").val();

						var unType = document
								.getElementById("asset.assetUnName");
						var pindex = unType.selectedIndex;
						//采集类型
						var uName = unType.options[pindex].value;
						//设备种类
						var deviceTypeId = $("#bigCateId").val();
						//支持设备
						var supportDeviceId = $("#smalCateId").val();

						$
								.ajax({
									type : "post",
									url : "${ctx}/asset/checkMac.action",
									dataType : "json",
									data : "&mac=" + mac + "&collectorId="
											+ collectorId + "&uName=" + uName
											+ "&deviceTypeId=" + deviceTypeId
											+ "&supportDeviceId="
											+ supportDeviceId,
									success : function(result) {
										if (result) {
											$("#div_ipEnd").removeClass(
													"spanred");
											$("#div_ipEnd")
													.html(
															"<img border=0 src=\"${ctx}/images/ok.png\" />");

											checkAssetGateWay();
											ipFlag = true;
										} else {
											$("#div_ipEnd").addClass("spanred");
											$("#div_ipEnd").html("ip地址已占用");

											ipFlag = false;
										}
									}
								});

					}
				} else {
					$("#div_ipEnd").addClass("spanred");
					$("#div_ipEnd").html("ip地址不合法");
					ipFlag = false;
				}
			} else {
				ipFlag = true;
			}
		} else {
			ipFlag = false;
		}
	}

	function checkAssetGateWay() {
		var gate = "";
		gate = $('#assetGateWays').val();
		if (gate == "") {
			document.getElementById('assetGateWays').value = "255.255.255.0";
		}
		$("#check_gate_msg").removeClass("spanred");
		$("#check_gate_msg").empty();
		if (gate != null && gate != "") {

			var pattern = "/^(254|252|248|240|224|192|128|0)\.0\.0\.0|255\.(254|252|248|240|224|192|128|0)\.0\.0|255\.255\.(254|252|248|240|224|192|128|0)\.0|255\.255\.255\.(254|252|248|240|224|192|128|0)$/";
			eval("var pattern=" + pattern);
			var add_p1 = pattern.test(gate);
			if (add_p1 == false) {
				$("#check_gate_msg").addClass("spanred");
				$("#check_gate_msg").html("子网掩码不合法");
				return false;
			} else {
				$("#check_gate_msg").removeClass("spanred");
				$("#check_gate_msg").html(
						"<img border=0 src=\"${ctx}/images/ok.png\" />");
				return true;
			}

		} else {

			$("#check_gate_msg").removeClass("spanred");
			$("#check_gate_msg").empty();
			return true;
		}
	}

	/* function checkAssetIp() {
		var ip = $("#assetIp").val();
		if (ip != "") {
			var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
			var result = re1.test(ip);
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
			return false;
		}
	}

	function checkAssetMac() {
		$("#check_mac_msg").removeClass("spanred");
		$("#check_mac_msg").empty();
		var mac = $("#assetMac").val();
		if (mac != null && mac != "") {
			var pattern = "/^([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}/";
			eval("var pattern=" + pattern);
			var add_p1 = pattern.test(mac);
			if (add_p1 == false) {
				$("#check_mac_msg").addClass("spanred");
				$("#check_mac_msg").html("mac地址不合法");
				return false;
			} else {
				
				$("#check_mac_msg").removeClass("spanred");
				$("#check_mac_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
				return true;
			}

		}
		 else {
			$("#check_mac_msg").removeClass("spanred");
			$("#check_mac_msg").empty();
			return true;
		} */
	function setTypeUnName() {

		var unType = document.getElementById("asset.assetUnName");
		var pindex = unType.selectedIndex;
		var pValue = unType.options[pindex].value;
		if (pValue == 0) {
			/*$.ajax({
				type : "post",
				url : "${ctx}/asset/queryCategory.action",
				dataType : "json",
				data : "&snmp=" + 2,
				success : function(json) {
					var temp = "";
					for ( var i = 0; i < json.length; i++) {
						temp = temp + "<option value='"+json[i].deviceid+"'>"
								+ json[i].devicename + "</option>";
					}
					$("#bigCateId").html(temp);
				}
			});*/
			document.getElementById("hiddenVersion").style.display = "block";
			document.getElementById("hiddens1").style.display = "block";
			document.getElementById("hiddenGroup").style.display = "block";
			document.getElementById("hiddens2").style.display = "block";
			document.getElementById("hiddendirectoriseasd").style.display = "none";
			document.getElementById("hiddendirectorise").style.display = "none";
			document.getElementById("hiddens3").style.display = "none";
			document.getElementById("hiddenfileasd").style.display = "none";
			document.getElementById("hiddenfile").style.display = "none";
			document.getElementById("hiddens4").style.display = "none";
			document.getElementById("hiddenPrivilegeCommand").style.display = "none";
			document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
			document.getElementById("hiddens5").style.display = "none";
			document.getElementById("APM1").style.display = "none";
			document.getElementById("APM2").style.display = "none";
			document.getElementById("APM3").style.display = "none";
			document.getElementById("APM4").style.display = "none";
			document.getElementById("APM5").style.display = "none";
			$('.asset1').css("display", "none");
			$('#apmhidden').css("display", "block");
			$('#hiddensys').hide();
			$('#hiddensnmp').show();
			$('#hiddenAgent').hide();
			$('#hiddenwindows').hide();
		} else if (pValue == 1) {
			document.getElementById("hiddendirectoriseasd").style.display = "block";
			document.getElementById("hiddendirectorise").style.display = "block";
			document.getElementById("hiddens3").style.display = "block";
			document.getElementById("hiddenfileasd").style.display = "block";
			document.getElementById("hiddenfile").style.display = "block";
			document.getElementById("hiddens4").style.display = "block";
			document.getElementById("hiddenPrivilegeCommand").style.display = "block";
			document.getElementById("hiddenPrivilegeCommandasd").style.display = "block";
			document.getElementById("hiddens5").style.display = "block";
			document.getElementById("hiddenVersion").style.display = "none";
			document.getElementById("hiddens1").style.display = "none";
			document.getElementById("hiddenGroup").style.display = "none";
			document.getElementById("hiddens2").style.display = "none";
			$('.asset1').css("display", "block");
			$('#apmhidden').css("display", "none");
			$('#hiddensys').hide();
			$('#hiddensnmp').hide();
			$('#hiddenAgent').show();
			document.getElementById("APM1").style.display = "block";
			document.getElementById("APM2").style.display = "block";
			document.getElementById("APM3").style.display = "block";
			document.getElementById("APM4").style.display = "block";
			document.getElementById("APM5").style.display = "block";
			/*$
					.ajax({
						type : "post",
						url : "${ctx}/asset/queryCategory.action",
						dataType : "json",
						data : "&bigCateId=" + pValue,
						success : function(json) {
							var temp = "";
							for ( var i = 0; i < json.length; i++) {
								temp = temp
										+ "<option value='"+json[i].deviceid+"'>"
										+ json[i].devicename + "</option>";
							}
							$("#bigCateId").html(temp);
							$
									.ajax({
										type : "post",
										url : "${ctx}/asset/queryCategory.action",
										dataType : "json",
										data : "&smallCateId=" + 39,
										success : function(json) {
											var temp = "";
											for ( var i = 0; i < json.length; i++) {
												temp = temp
														+ "<option value='"+json[i].deviceid+"'>"
														+ json[i].devicename
														+ "</option>";
											}
											$("#smalCateId").html(temp);
										}
									});
						}
					});*/
		} else {

			/*$.ajax({
				type : "post",
				url : "${ctx}/asset/queryCategory.action",
				dataType : "json",
				data : "&syslog=" + 3,
				success : function(json) {
					var temp = "";
					for ( var i = 0; i < json.length; i++) {
						temp = temp + "<option value='"+json[i].deviceid+"'>"
								+ json[i].devicename + "</option>";
					}
					$("#bigCateId").html(temp);
				}
			});*/
			document.getElementById("hiddenVersion").style.display = "none";
			document.getElementById("hiddens1").style.display = "none";
			document.getElementById("hiddenGroup").style.display = "none";
			document.getElementById("hiddens2").style.display = "none";
			document.getElementById("hiddendirectoriseasd").style.display = "none";
			document.getElementById("hiddendirectorise").style.display = "none";
			document.getElementById("hiddens3").style.display = "none";
			document.getElementById("hiddenfileasd").style.display = "none";
			document.getElementById("hiddenfile").style.display = "none";
			document.getElementById("hiddens4").style.display = "none";
			document.getElementById("hiddenPrivilegeCommand").style.display = "none";
			document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
			document.getElementById("hiddens5").style.display = "none";
			document.getElementById("APM1").style.display = "none";
			document.getElementById("APM2").style.display = "none";
			document.getElementById("APM3").style.display = "none";
			document.getElementById("APM4").style.display = "none";
			document.getElementById("APM5").style.display = "none";
			$('.asset1').css("display", "none");
			$('#apmhidden').css("display", "block");
			$('#hiddensys').show();
			$('#hiddensnmp').hide();
			$('#hiddenAgent').hide();
			$('#hiddenwindows').hide();
		}
	}

	//验证mac地址
	function checkAssetMac() {
		$("#check_mac_msg").removeClass("spanred");
		$("#check_mac_msg").empty();
		var mac = $("#assetMac").val();
		if ('${asset.assetMac}' != mac) {
			if (mac != null && mac != "") {
				var pattern = "/^([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}/";
				eval("var pattern=" + pattern);
				var add_p1 = pattern.test(mac);
				if (add_p1 == false) {
					$("#check_mac_msg").addClass("spanred");
					$("#check_mac_msg").html("mac地址不合法");
					macFlag = false;
					return false;
				} else {
					//Mac地址
					var mac = $("#assetMac").val();
					//采集器id
					var collectorId = $("#collectorIds").val();

					var unType = document.getElementById("asset.assetUnName");
					var pindex = unType.selectedIndex;
					//采集类型
					var uName = unType.options[pindex].value;
					//设备种类
					var deviceTypeId = $("#bigCateId").val();
					//支持设备
					var supportDeviceId = $("#smalCateId").val();
					/* alert(mac);
					alert(collectorId);
					alert(uName);
					alert(deviceTypeId);
					alert(supportDeviceId); */
					$
							.ajax({
								type : "post",
								url : "${ctx}/asset/checkMac.action",
								dataType : "json",
								data : "&mac=" + mac + "&collectorId="
										+ collectorId + "&uName=" + uName
										+ "&deviceTypeId=" + deviceTypeId
										+ "&supportDeviceId=" + supportDeviceId,
								success : function(result) {
									if (result) {
										$("#check_mac_msg").removeClass(
												"spanred");
										$("#check_mac_msg")
												.html(
														"<img border=0 src=\"${ctx}/images/ok.png\" />");
										macFlag = true;
										return true;
									} else {
										$("#check_mac_msg").addClass("spanred");
										$("#check_mac_msg").html("mac地址已占用");
										macFlag = false;
										return false;
									}
								}
							});

				}

			} else {
				$("#check_mac_msg").removeClass("spanred");
				$("#check_mac_msg").empty();
				macFlag = true;
				return true;
			}
		}
		/* if (mac != null && mac != "") {
			var pattern = "/^([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}/";
			eval("var pattern=" + pattern);
			var add_p1 = pattern.test(mac);
			if (add_p1 == false) {
				$("#check_mac_msg").addClass("spanred");
				$("#check_mac_msg").html("mac地址不合法");
				return false;
			} else {
				
				$("#check_mac_msg").removeClass("spanred");
				$("#check_mac_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
				return true;
			}

		}
		 else {
			$("#check_mac_msg").removeClass("spanred");
			$("#check_mac_msg").empty();
			return true;
		}  */
	}
	//根据设备类型来判断是否显示特权命令
	function setTypeDeviced() {
		var uname = document.getElementById("asset.assetUnName");
		var temp = uname.selectedIndex;
		var value1 = uname.options[temp].value;

		var deviceType = document.getElementById("smalCateId");
		var pindex = deviceType.selectedIndex;
		var value = deviceType.options[pindex].value;
		if (value1 == 1) {
			if (value == 41) {
				document.getElementById("hiddens4").style.display = "none";
				document.getElementById("hiddenPrivilegeCommand").style.display = "none";
				document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
				$('#hiddenwindows').show();
				$('#hiddenAgent').hide();
			} else {
				document.getElementById("hiddens4").style.display = "block";
				document.getElementById("hiddenPrivilegeCommand").style.display = "block";
				document.getElementById("hiddenPrivilegeCommandasd").style.display = "block";
				$('#hiddenwindows').hide();
				$('#hiddenAgent').show();
			}
		}
	}
	/*function setTypeCategory() {
		var unType = document.getElementById("bigCateId");
		var pindex = unType.selectedIndex;
		var pValue = unType.options[pindex].value;
		$.ajax({
			type : "post",
			url : "${ctx}/asset/queryCategory.action",
			dataType : "json",
			data : "&upsId=" + pValue,
			success : function(json) {
				var temp = "";
				for ( var i = 0; i < json.length; i++) {
					temp = temp + "<option value='"+json[i].deviceid+"'>"
							+ json[i].devicename + "</option>";
				}
				$("#smalCateId").html(temp);
			}
		});
	}*/
	function directoriseDlg() {
		$("#dialog-addAccount").dialog("open");
		$("#address").val("");
	}
	function directoriseAddAccount() {
		if ($("#address").val() != "") {
			var temp = $("#directoriseSelect");
			temp.append("<option value='" + $('#address').val() + "'>"
					+ $('#address').val() + "</option>");
			$("#dialog-addAccount").dialog("close");
		}
	}

	function directoriseDel() {
		$("select[name=asset.directorise]").find("option:selected").remove();
	}
	监控文件
	function fireDlg() {
		$("#dialog-fireAccount").dialog("open");
		$("#fireAddress").val("");
	}
	function fireAddAccount() {
		if ($("#fireAddress").val() != "") {
			var tempfire = $("#fireSelect");
			tempfire.append("<option value='" + $('#fireAddress').val() + "'>"
					+ $('#fireAddress').val() + "</option>");
			$("#dialog-fireAccount").dialog("close");
		}
	}
	function fireDel() {
		$("select[name=asset.fire]").find("option:selected").remove();
	}
	//特权命令
	function assetPrivilegeCommandDlg() {
		$("#dialog-assetPrivilegeCommandAccount").dialog("open");
		$("#assetPrivilegeCommandAddress").val("");
	}
	function assetPrivilegeCommandAddAccount() {
		if ($("#assetPrivilegeCommandAddress").val() != "") {
			var tempPrivilege = $("#assetPrivilegeCommandSelect");
			tempPrivilege.append("<option value='"
					+ $('#assetPrivilegeCommandAddress').val() + "'>"
					+ $('#assetPrivilegeCommandAddress').val() + "</option>");
			$("#dialog-assetPrivilegeCommandAccount").dialog("close");
		}
	}
	function assetPrivilegeCommandDel() {
		$("select[name=asset.assetPrivilegeCommand]").find("option:selected")
				.remove();
	}

	function updateAssetStatus(temp) {
		if (temp == 0) {
			$('#status').val('停用');
			$("#lock").attr('disabled', 'disabled');
			$("#unLock").attr('disabled', '');
		} else {
			$('#status').val('启用');
			$("#unLock").attr('disabled', 'disabled');
			$("#lock").attr('disabled', '');

		}
	}
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="updateAsset.action" namespace="/assetProbe" method="post"
		theme="simple" id="assetForm" name="assetForm">
		<s:hidden name="disAll" id="disAll" />
		<s:hidden name="fireAll" id="fireAll" />
		<s:hidden name="commandAll" id="commandAll" />
		<s:hidden name="asset.assetStatus" id="assetStatus" />
		<!--  总table-->
		<!--  主table-->

		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="50%" valign="top">
					<!--  左侧table-->
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<!-- 用户信息 -->
							<tr>
								<td class='r2titler' colspan='3'><b>资产信息 </b></td>
							</tr>
							<tr>
								<td>
									<!-- 资产基本信息 -->
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 资产名称: -->
										<tr>
											<td align="right" width="20%" style="padding-left:10px"><span
												class="spanred">*&nbsp;</span>资产：</td>
											<td align="left" class="padding"><input
												class="validate[required,custom[spechars],custom[validateLength]]"
												name="asset.assetName" value="${asset.assetName}"
												type="text" size="40" id="assetName" maxlength="255"
												style="width: 270px" onblur="checkAssetName();"
												<c:if test="${asset != null}">readonly="readonly"</c:if> />
												<input type="hidden" name="asset.assetId"
												value="${asset.assetId}" /></td>
											<td><span id="check_loginname_msg"></span></td>
										</tr>
										<!-- 空行-->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>



										<!--采集器-->
										<tr>
											<td align="right">采集器：</td>
											<td class="padding">
											<select style="width:274px"
												name="collectorIds" id="collectorIds">
												
													<option value="0" id="collectorIds">请选择采集器</option>
													<s:iterator value="collectorList" status="stat">
														<option value="${collectorId}" id="collectorIds"
															<c:if test="${asset.assetCollectorId==collectorId}">selected="selected"</c:if>>${collectorBasic}</option>
													</s:iterator>

											</select>
											</td>
											<td></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!--待定-->
										<tr>
											<td align="right">采集类型：</td>
											<td class="padding"><select style="width:274px"
												name="asset.assetUnName" id="asset.assetUnName"
												onchange="javascript:setTypeUnName();">
													<option value="-1"
														<c:if test="${asset.assetUnName==-1 }">selected="selected"</c:if>>syslog</option>
													<option value="0"
														<c:if test="${asset.assetUnName==0}">selected="selected"</c:if>>snmp</option>
													<option value="1"
														<c:if test="${asset.assetUnName==1}">selected="selected"</c:if>>代理</option>
											</select></td>
											<td></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!--资产责任人-->
										<tr>
											<td align="right">资产责任人：</td>
											<td align="left" class="padding"><input class=""
												name="asset.assetAliasName" value="${asset.assetAliasName}"
												type="text" size="40" id="assetAliasName" maxlength="255"
												style="width: 270px" /></td>
										</tr>

										<tr>
											<td align="right" class="td_detail_separator"></td>
										</tr>
										<!--业务标识 -->
										<tr>
											<td align="right">业务标识：</td>
											<td align="left" class="padding"><input class=""
												name="asset.assetWorkIdent" value="${asset.assetWorkIdent}"
												type="text" size="40" id="assetWorkIdent" maxlength="255"
												style="width: 270px" /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!--重要性-->
										<tr>
											<td align="right">重要性：</td>
											<td class="padding"><select style="width:274px"
												name="asset.assetImportance">
													<option value="-1"
														<c:if test="${asset.assetImportance==-1 }">selected="selected"</c:if>>一般</option>
													<option value="0"
														<c:if test="${asset.assetImportance==0}">selected="selected"</c:if>>重要</option>
													<option value="1"
														<c:if test="${asset.assetImportance==1}">selected="selected"</c:if>>比较重要</option>
													<option value="2"
														<c:if test="${asset.assetImportance==2}">selected="selected"</c:if>>非常重要</option>
											</select></td>
											<td></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 描述-->
										<tr>

											<td align="right" style="vertical-align:20%">描述：</td>
											<td class="padding"><s:textarea name="asset.assetMemo"
													id="assetMemo" cols="35" rows="4" cssStyle="width:273px"></s:textarea>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>
										<c:if test="${asset.assetId==null}">
											<tr>
												<td colspan="3">
													<!-- 状态内容 -->
													<table width="99%" border="0" align="center"
														cellspacing="0" cellpadding="0">
														<!-- 状态 -->
														<tr>
															<td width="18%" align="right" valign="top">状态：</td>
															<td width="75%" style="padding-left:6px;">
																<table width="99%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td><input type="text" id="status" value="启用"
																			size="40" style="width: 270px" disabled="disabled">
																		</td>
																	</tr>
																	<tr>
																		<td height="8px"></td>
																	</tr>
																	<tr>
																		<td><input id="lock" type="button" class="btnyh2"
																			style="padding-top:2px;" value="停用"
																			disabled='disabled' /> <input id="unLock"
																			type="button" class="btnyh2" style="padding-top:2px;"
																			value="启用" disabled="disabled" /> <input
																			type="button" class="btn_detail_add_del" value="注销"
																			style="display: none;" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</c:if>
										<c:if test="${asset.assetId!=null}">
											<tr>
												<td colspan="3">
													<!-- 状态内容 -->
													<table width="99%" align="center" border="0"
														cellspacing="0" cellpadding="0">
														<!-- 状态 -->
														<tr>
															<td width="18%" align="right" valign="top">状态：</td>
															<td width="75%" style="padding-left:6px;">
																<table width="99%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td><input type="text" id="status"
																			<c:if test="${asset.assetStatus==1}">value="启用"</c:if>
																			<c:if test="${asset.assetStatus==0}">value="停用"</c:if>
																			size="40" style="width: 270px" disabled="disabled">
																		</td>
																	</tr>
																	<tr>
																		<td height="8px"></td>
																	</tr>
																	<tr>
																		<td><input id="lock" type="button" class="btnyh2"
																			style="padding-top:2px;" value="停用"
																			onclick="updateAssetStatus(0)"
																			<c:if test="${asset.assetStatus==0}">disabled='disabled'</c:if> />
																			<input id="unLock" type="button" class="btnyh2"
																			style="padding-top:2px;" value="启用"
																			onclick="updateAssetStatus(1)"
																			<c:if test="${asset.assetStatus==1}">disabled='disabled'</c:if> />
																			<input type="button" class="btn_detail_add_del"
																			value="注销" style="display: none;" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</c:if>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>
										<tr>
											<td align="right">资产组：</td>
											<td align="left" class="padding"><input class="readonly"
												name="asset.assetGroupName"
												value="<c:if test="${asset==null}">未分组</c:if><c:if test="${asset!=null}">${asset.assetGroupName}</c:if>"
												type="text" size="40" id="assetGroupName" maxlength="255"
												style="width: 180px" readonly="readonly" /> <input
												type="hidden" name="asset.assetGroupId" id="assetGroupId1"
												value="<c:if test="${asset==null}">0</c:if><c:if test="${asset!=null}">${asset.assetGroupId}</c:if>" />
												<input type="hidden" name="asset.assetGroupFeature"
												id="assetGroupFeature"
												value="<c:if test="${asset==null}">,</c:if><c:if test="${asset!=null}">${asset.assetGroupFeature}</c:if>" />&nbsp;&nbsp;&nbsp;
												<input type="button" value="选择资产组" class="btnstyle"
												style="padding-left:-3px;margin-top:-2px"
												onclick="extQueryDlg();" />
											</td>
											<td><span id="check_loginname_msg"></span>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr class="asset1">
											<td colspan="3">
												<div class="xuxian"></div></td>
										</tr>
										<tr class="asset1">
											<td height="5px"></td>
										</tr>
										<tr class="asset1">
											<td align="right">端口：</td>
											<td align="left" class="padding"><input
												name="asset.assetPort" value="${asset.assetPort}"
												type="text" size="40" id="assetPort" maxlength="255"
												style="width: 270px"
												class="validate[custom[settingPort]] text-input " /></td>
										</tr>

										<!-- 空行 -->
										<tr class="asset1">
											<td class="td_detail_separator"></td>
										</tr>

										<tr class="asset1">
											<td align="right">轮循日志：</td>
											<td align="left" class="padding"><input
												name="asset.assetLog" value="${asset.assetLog}" type="text"
												size="40" id="assetPort" maxlength="255"
												style="width: 270px"
												class="validate[custom[settingNumber]] text-input " />&nbsp;秒
											</td>
										</tr>
										<tr class="asset1">
											<td height="5px"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div></td>
										</tr>

										<tr>
											<td height="5px"></td>
										</tr>
										<!-- 保密性价值 -->
										<tr>
											<td align="right">保密性价值：</td>
											<td class="padding"><select style="width:274px"
												name="asset.secretValue">
													<option value="1"
														<c:if test="${asset.secretValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${asset.secretValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${asset.secretValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${asset.secretValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${asset.secretValue==5}">selected="selected"</c:if>>5</option>
											</select></td>
											<td></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 完整性 价值-->
										<tr>
											<td align="right">完整性价值：</td>
											<td class="padding"><select style="width:274px"
												name="asset.integrityValue">
													<option value="1"
														<c:if test="${asset.integrityValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${asset.integrityValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${asset.integrityValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${asset.integrityValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${asset.integrityValue==5}">selected="selected"</c:if>>5</option>
											</select></td>
											<td></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 可用性价值: -->
										<tr>
											<td align="right">可用性价值：</td>
											<td class="padding"><select style="width:274px"
												name="asset.usabilityValue">
													<option value="1"
														<c:if test="${asset.usabilityValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${asset.usabilityValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${asset.usabilityValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${asset.usabilityValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${asset.usabilityValue==5}">selected="selected"</c:if>>5</option>
											</select></td>
											<td></td>
										</tr>
										<tr>
											<td height="20px"></td>
										</tr>
										<tr id="apmhidden">
											<td height="72px"></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div></td>
				<td width="5px"></td>
				<td valign="top">
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<!-- 用户信息 -->
							<tr>
								<td class='r2titler' colspan='3'><b>采集器设备设置</b>
								</td>
							</tr>
							<tr>
								<td>
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<s:iterator value="#session.assetslist" status="stat"
											id="asset">
											<!--设备种类-->
											<tr>
												<td align="right" width="20%">设备种类：</td>
												<td class="padding"><select style="width:274px"
													name="asset.assetDeviceTypeId" id="bigCateId"
													onchange="javascript:setTypeCategory();">

														<option value="39" selected="selected">Server服务器</option>

												</select>
												</td>
												<td></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td align="right">支持设备：</td>
												<td class="padding"><select style="width:274px"
													name="asset.assetSupportDeviceId" id="smalCateId"
													onchange="javascript:setTypeDeviced();">

														<c:if test="${asset.assetDeviceType=='linux'}">
															<option selected="selected" value="40">${asset.assetDeviceType}</option>
														</c:if>

														<c:if test="${asset.assetDeviceType=='Windows'}">

															<option selected="selected" value="41">${asset.assetDeviceType}</option>
														</c:if>

														<c:if test="${asset.assetDeviceType=='aix'}">
															<option selected="selected" value="95">${asset.assetDeviceType}</option>
														</c:if>
														<c:if test="${asset.assetDeviceType=='solaris'}">

															<option selected="selected" value="96">${asset.assetDeviceType}</option>
														</c:if>




												</select>
												</td>
												<td></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<!-- 虚线分割线 -->
											<tr>
												<td colspan="3">
													<div class="xuxian"></div></td>
											</tr>
											<%-- <tr>
											<td height="5px"></td>
										</tr>
										<!-- mac地址 -->
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>mac地址：</td>
											<td align="left" class="padding"><input
												name="asset.assetMac" value="${asset.assetMac}" type="text"
												size="40" id="assetMac" maxlength="255" style="width: 270px"
												class="validate[required,custom[settingMac]]"
												onblur="javascript:checkAssetMac();" />
											</td>
											<td><span id="check_mac_msg"></span>
											</td>
										</tr> --%>
											<!-- IP地址 -->

											<tr>
												<td align="right"><span class="spanred">*&nbsp;</span>IP地址：</td>
											
													<td align="left" class="padding"><input
														class="validate[required,custom[onlyLetterNumber]] text-input"
														name="asset.assetIps" value="${asset.assetIps}"
														type="text" size="40" id="assetIp" maxlength="255"
														style="width: 270px" onblur="checkAssetIp();" /></td>
											
												<td><span id="div_ipEnd"></span></td>
											</tr>
											</s:iterator>
											<!-- 空行-->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<!--网关 -->
											<tr>
												<td align="right"><span class="spanred"> </span>子网掩码：</td>
												<td align="left" class="padding"><input
													name="asset.assetGateWays" id="assetGateWays"
													value="${asset.assetGateWays}" type="text"
													class="validate[required,custom[settingZifang]] text-input"
													size="40" id="assetGateWay" maxlength="255"
													style="width: 270px"
													onblur="javascript:checkAssetGateWay();" />
												</td>
												<td><span id="check_gate_msg"></span></td>
											</tr>

											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<!-- 虚线分割线 -->
											<tr>
												<td colspan="3">
													<div class="xuxian"></div></td>
											</tr>
											<tr>
												<td height="5px"></td>
											</tr>

											<tr id="APM1">
												<td align="right">APM端口：</td>
												<td align="left" class="padding"><input
													name="asset.assetAPM" value="${asset.assetAPM}" type="text"
													size="40" id="assetAPM" maxlength="255"
													style="width: 270px"
													class="validate[custom[settingPort]] text-input" /></td>
											</tr>
											<!-- 空行-->
											<tr id="APM2">
												<td class="td_detail_separator"></td>
											</tr>
											<tr id="APM3">
												<td align="right">实时监控：</td>
												<td align="left" class="padding"><input
													name="asset.assetAPMMonitor"
													value="${asset.assetAPMMonitor}" type="text" size="40"
													id="assetAPMMonitor" maxlength="255" style="width: 270px"
													class="validate[custom[settingNumber]] text-input " />&nbsp;秒
												</td>
											</tr>
											<tr id="APM5">
												<td class="td_detail_separator"></td>
											</tr>
											<tr id="APM4">
												<td colspan="3">
													<div class="xuxian"></div></td>
											</tr>

											<!-- 空行 -->
											<tr id="hiddens">
												<td class="td_detail_separator"></td>
											</tr>
											<!--版本-->
											<tr id="hiddenVersion">
												<td align="right">版本：</td>
												<td class="padding"><select style="width:274px"
													name="asset.version">
														<option value="1"
															<c:if test="${asset.version=='1'}">selected="selected"</c:if>>1</option>
														<option value="2c"
															<c:if test="${asset.version=='2c'}">selected="selected"</c:if>>2c</option>
														<option value="1"
															<c:if test="${asset.version=='3'}">selected="selected"</c:if>>3</option>
												</select></td>
												<td></td>
											</tr>

											<!-- 空行 -->
											<tr id="hiddens1">
												<td class="td_detail_separator"></td>
											</tr>
											<!--版本-->
											<tr id="hiddenGroup">
												<td align="right">团体名：</td>
												<td class="padding"><input
													name="asset.organizationName"
													value="${asset.organizationName}" type="text" size="40"
													id="organizationName" maxlength="255" style="width: 270px" />
												</td>
												<td></td>
											</tr>
											<!-- 空行 -->
											<tr id="hiddens2">
												<td class="td_detail_separator"></td>
											</tr>
											<!--监控目录-->
											<tr id="hiddendirectorise">
												<td align="right">监控目录：</td>
												<td class="padding"><select id="directoriseSelect"
													name="asset.directorise" style="width:260px" size="3"
													multiple="multiple">
														<c:forEach items="${directoriseList}" var="l">
															<option id="directorise">${l}</option>
														</c:forEach>
												</select>
											</tr>
											<tr>
												<td height="2px"></td>
											</tr>
											<tr id="hiddendirectoriseasd">
												<td align="right"></td>
												<td class="padding"><input type="button" value=""
													class="btnadd" onclick="directoriseDlg();" /> <input
													type="button" value="" class="btndel"
													onclick="directoriseDel();" /></td>
											</tr>
											<!-- 空行 -->
											<tr id="hiddens3">
												<td class="td_detail_separator"></td>
											</tr>
											<tr id="hiddenfile">
												<td align="right">监控文件：</td>
												<td class="padding"><select style="width:260px"
													size="3" id="fireSelect" name="asset.fire"
													multiple="multiple">
														<c:forEach items="${fireList}" var="l">
															<option id="fire">${l}</option>
														</c:forEach>
												</select></td>
											</tr>
											<tr>
												<td height="2px"></td>
											</tr>
											<tr id="hiddenfileasd">
												<td align="right"></td>
												<td class="padding"><input type="button" value=""
													class="btnadd" onclick="fireDlg();" /> <input
													type="button" value="" class="btndel" onclick="fireDel();" />
												</td>
											</tr>
											<!-- 空行 -->
											<tr id="hiddens4">
												<td class="td_detail_separator"></td>
											</tr>
											<tr id="hiddenPrivilegeCommand">
												<td align="right">特权命令：</td>
												<td class="padding"><select
													id="assetPrivilegeCommandSelect"
													name="asset.assetPrivilegeCommand" style="width:260px"
													size="3" multiple="multiple">
														<c:forEach items="${commandList}" var="l">
															<option id="assetPrivilegeCommand">${l}</option>
														</c:forEach>
												</select>
											</tr>
											<tr>
												<td height="2px"></td>
											</tr>
											<tr id="hiddenPrivilegeCommandasd">
												<td align="right"></td>
												<td class="padding"><input type="button" value=""
													class="btnadd" onclick="assetPrivilegeCommandDlg();" /> <input
													type="button" value="" class="btndel"
													onclick="assetPrivilegeCommandDel();" />
												</td>
											</tr>

											<!-- 空行 -->
											<tr id="hiddens5">
												<td class="td_detail_separator"></td>
											</tr>
											<tr id="hiddensys" height="391px">
												<td></td>
											</tr>
											<tr id="hiddensnmp" height="328px">
											</tr>
											<tr id="hiddenAgent" height="85px">
											</tr>
											<tr id="hiddenwindows" height="163px">
											</tr>
										
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="subData();" /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/asset/queryAsset.action';" />
				</td>
			</tr>
		</table>
	</s:form>

	<!-- 资产组dialog -->
	<div id="dialog-assetGroupDialog" title="资产组选择">
		<table align="center">
			<tr>
				<td><input type="radio" value="0" name="assetGroupId" /> <s:hidden
						id="0_id" value="未分组" /> 未分组</td>
			</tr>
			<tr>
				<td>
					<div class="big">
						<div class="left">
							<s:property value="htmlBuffer" escape="false" />
						</div>
					</div></td>
			</tr>
		</table>
	</div>
	<!-- 监控目录dialog -->
	<div id="dialog-addAccount">
		<input type="hidden" name="account_id" id="account_id" />
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td align="left">监控目录:</td>
				<td><input id="address" name="address" type="text"
					style="width:250px;" />
				</td>
			</tr>
		</table>
	</div>
	<div id="dialog-fireAccount">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td align="left">监控文件:</td>
				<td><input id="fireAddress" name="fireAddress" type="text"
					style="width:250px;" />
				</td>
			</tr>
		</table>
	</div>
	<div id="dialog-assetPrivilegeCommandAccount">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td align="left">特权命令:</td>
				<td><input id="assetPrivilegeCommandAddress"
					name="assetPrivilegeCommandAddress" type="text"
					style="width:250px;" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
