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
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css" /> --%>
<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<!-- 加载图表需要的js -->
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>

<script language="javascript">

	jQuery(document).ready(function() {
		jQuery("#ipForm").validationEngine();
		jQuery("#addForm").validationEngine();
		//选项卡
		$('#tabs-setting').tabs();

		changeCard('${cardSize}');//设置网卡的显示个数
		//changeCard(4);//设置网卡的显示个数
		// Dialog			
		$('#dialog-addStaticRoute').dialog({
			autoOpen : false,
			width : 350,
			height : 200,
			buttons : {
				"添加[Enter]" : function() {
					addStaticRoute();
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		$('#dialog-look').dialog({
			autoOpen : false,
			width : 450,
			height : 500,
			buttons : {
				/*"下载[Download]" : function() {
					//download();
				},*/
				"关闭[Esc]" : function() {
					$('#interfaces_content').val('');
					$(this).dialog("close");
				}
			}
		});
	});

	function addStaticRouteDlg() {
		$('#relsubnet').val('');
		$('#relmask').val('');
		$('#relgateway').val('');
		$('#relsubnet').focus();
		$('#dialog-addStaticRoute').dialog('open');
	}

	function addStaticRoute() {
		var vsubnet = $('#relsubnet').val();
		var vmask = $('#relmask').val();
		var vgateway = $('#relgateway').val();
		var re = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
		var ipbool = re.test(vsubnet);
		if (ipbool) {
			if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 == 0)) {
				$('#relsubnet').focus();
				return false;
			}
		} else {
			$('#relsubnet').focus();
			return false;
		}
		/*var gatebool = re.test(vgateway);
		if(gatebool){
		 	if(!( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256)){
		 		$('#relgateway').focus();
		 		return false;
			}
		}else{
		 	$('#relgateway').focus();
		 	return false;
		}*/
		if ($.trim(vgateway) == '') {
			alert("网关不能为空...");
			$('#relgateway').focus();
			return;
		}
		var str = "<tr style='line-height: 25px;' valign='middle' onclick='checkTr(this);'><td width='30%' align='center' class='ThinInput'>"
				+ vsubnet
				+ "</td><td align='center' class='ThinInput'>"
				+ vmask
				+ "</td><td align='center' class='ThinInput'>"
				+ vgateway + "</td></tr>";
		$('#staticTable').append(str);
		$('#dialog-addStaticRoute').dialog('close');
	}
	//验证子网掩码
	/*function checkMask(mask) 
	{ 
	    obj=mask; 
	    var exp=/^(254|252|248|240|224|192|128|0)/.0/.0/.0|255/.(254|252|248|240|224|192|128|0)/.0/.0|255/.255/.(254|252|248|240|224|192|128|0)/.0|255/.255/.255/.(254|252|248|240|224|192|128|0)$/; 
	var reg = obj.match(exp); 
	    if(reg==null) 
	    { 
	       return false; 
	    } 
	    else 
	    { 
	       return true; 
	    } 
	
	}*/

	function delStaticRoute() {
		var table = document.getElementById('staticTable');
		if ($('#hid-checked-tr').val() != -1) {
			table.deleteRow($('#hid-checked-tr').val());
			$('#hid-checked-tr').val(-1);
		}
	}

	function ipChange(ip_id) {
		var ip = $("#" + ip_id + "Ip").val();
		var iparray = ip.split('.');

		if (iparray.length == 4) {
			var ip;
			if ($("#" + ip_id + "Code").val() == '') {
				$("#" + ip_id + "Code").val('255.255.255.0');
			}
		}
	}

	function staticIpChange() {
		var ip = $("#relsubnet").val();
		var iparray = ip.split('.');

		if (iparray.length == 4) {
			var ip;
			if ($("#relmask").val() == '') {
				$("#relmask").val('255.255.255.0');
			}
			if ($("#relgateway").val() == '') {
				$("#relgateway")
						.val(
								iparray[0] + '.' + iparray[1] + '.'
										+ iparray[2] + '.1');
			}
		}
	}

	function changeCard(checkId) {
		for ( var i = 1; i <= 10; i++) {
			if (i > checkId) {
				document.getElementById("card" + i).style.display = 'none';
			} else {
				document.getElementById("card" + i).style.display = 'block';
			}
		}
		$.post("${ctx}/settingNetwork/saveCardSize.action", {
			cardSize : checkId
		}, function() {

		});
	}

	function checkTr(obj) {
		var table = $(obj).parent("tbody");
		var trNodes = table.children("tr");
		trNodes.css("background-color", "#ffffff");
		$(obj).css("background-color", "#dddddd");
		$('#hid-checked-tr').val($(obj).index());
	}

	function ping() {
		if ($('#ping_ip').val() == '') {
			alert("IP地址不能为空...");
			$('#ping_ip').focus();
			return;
		}
		var url = "${ctx}/settingNetwork/executePing.action?method=executePing";

		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			data : "&pingIP=" + $('#ping_ip').val(),
			success : function(result) {

				var res = String($.trim(result));

				if (res = "success") {
				} else {
					alert("");
				}
			}
		});
	}

	function flushroutelist() {
		//var url = "${ctx}/setting/flush_route_list.action?method=flush_route_list";
		/*$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			success : function(result) {
				//$('#routeList').val(List(result));
			}
		});*/
		$('#btn_flush1').attr('disabled', 'disabled');
		var opanel = document.getElementById("route_list");
		var pchildren = opanel.childNodes;
		//清空表中的行和列
		for ( var a = 0; a < pchildren.length; a++) {
			opanel.removeChild(pchildren[a]);
		}
		$.getJSON("${ctx}/settingNetwork/flush_route_list.action?t=" + new Date(),
				function(result) {
					var htmlStr = "";
					$.each(result, function(i) {
						htmlStr += "<tr style='line-height: 15px;'><td>"
								+ result[i].col1 + "</td><td>" + result[i].col2
								+ "</td><td>" + result[i].col3 + "</td><td>"
								+ result[i].col4 + "</td><td>" + result[i].col5
								+ "</td><td>" + result[i].col6 + "</td><td>"
								+ result[i].col7 + "</td><td>" + result[i].col8
								+ "</td></tr>";
					});
					$('#route_list').append(htmlStr);
					$('#btn_flush1').attr('disabled', '');
				});

	}

	function save() {
		var size = $("#size").val();
		/*var rows = $('#staticTable').getElementsByTagName('tr');
		var bIp = $("#b" + id + "Ip").val();
		var bCode = $("#b" + id + "Code").val();*/
		/*if ($.trim(bIp) == '') {
			alert("网卡IP不能为空...");
			$("#b" + id + "Ip").focus();
			return false;
		}
		if ($.trim(bCode) == '') {
			alert("掩码不能为空...");
			$("#b" + id + "Code").focus();
			return false;
		}*/
		
		var data = '';
		var falg1=false;
		var falg2=false;
		var falg3=false;
		for ( var i = 1; i <= size; i++) {
			
			var bIp = $("#b" + i + "Ip").val();
			var bCode = $("#b" + i + "Code").val();
			var bGate = $("#b" + i + "Gate").val();
			if (bIp == ''|| bIp==null ) {
				alert("请输入网卡" + i - 1 + "的IP地址...");
				//$("#b" + i + "Ip").focus();
				falg1=false;
				break;
			}else if(regex(bIp)==null){
				alert("您输入网卡" + (i - 1).toString() + "的IP地址有误...");
				//$("#b" + i + "Ip").focus();
				falg1=false;
				break;
			}else {
				falg1=true;
			}
			if ( bCode == ''||bCode==null) {
				alert("请输入网卡" + i - 1 + "的掩码...");
				//$("#b" + i + "Code").focus();
				falg2=false;
				break;
			}else if(regex(bCode)==null){
				alert("您输入网卡" + (i - 1).toString() + "的掩码有误...");
				//$("#b" + i + "Ip").focus();
				falg1=false;
				break;
			}else{
				falg2=true;
			}
			/*  --- 屏蔽添加网卡时，一定要添加网关的限制 ---
			if ( bGate == '' ||bGate==null ) {
				alert("请输入网卡" + i - 1 + "的网关...");
				//$("#b" + i + "Gate").focus();
				falg3=false;
				break;
			}else 
			*/	
			
			if(bGate==''){				
				falg3 = true;
			}else if(regex(bGate)==null){
				alert("您输入网卡" + (i - 1).toString() + "的网关有误...");
				//$("#b" + i + "Ip").focus();
				falg1=false;
				break;
			}else{
				falg3=true;
			}
			data = data + "&b" + i + "Ip=" + bIp + "&b" + i + "Code=" + bCode + "&b" + i + "Gate=" + bGate ;
			/**if(bGate!='' || bGate!=null){
				alert("空hi");
				data = data + "&b" + i + "Gate=" + bGate;
			}*/
		}
		data = data + "&cardSize=" + size;
		
		if(falg3==true && falg2 == true && falg1 ==true){
			
			var url = "${ctx}/settingNetwork/insertCard.action?method=insertCard";
			$.ajax({
				type : "post",
				url : url,
				dataType : "text",
				data : data,
				success : function(result) {
					var res = String($.trim(result));
					if (res = "success") {
						alert("配置保存成功！请重新登录系统！");
					} else {
					}

				}
			});
		
		}
		
	}
	//正则的验证IP
	function regex(str){
		var regex=str.match(/^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/); 
		return regex;
	}
	
	function lookDlg(name) {
		var url = "${ctx}/settingNetwork/lookInterfaces.action?method=lookInterfaces";
		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			data : "&interfacesName=" + name,
			success : function(result) {
				$('#interfaces_content').val(String($.trim(result)));
			}
		});
		//document.getElementById("dialog-look").attributes.title.innerHTML=name;
		//document.getElementById("dialog-look").setAttribute("title", name);
		//$('#dialog-look').title=name;
		$('#dialog-look').dialog('option', 'title', name).dialog("open");
		//$('#dialog-look').dialog("open");
	}

	function recover(name) {
		if (confirm("恢复网络设置可能造成网络不可达，您确认要恢复吗？")) {
			if (confirm("确认恢复？")) {
				var url = "${ctx}/settingNetwork/recoverNetwork.action?method=recoverNetwork";
				$.ajax({
					type : "post",
					url : url,
					dataType : "text",
					data : "&interfacesName=" + name,
					success : function(result) {
						if (res = "success") {
							alert("恢复" + name + "成功！请重新登录系统！");
						}
					}
				});
			}
		}
	}

	//刷新恢复网络设置列表

	function flushrecover() {
		/*var op = document.getElementById("interfaces_list");
		var pc = op.childNodes;*/
		//清空表中的行和列
		/*for ( var a = 0; a < pc.length; a++) {
			//alert(pc.length);
			if(a==0) {
				//return false;
			} else {
				opanel.removeChild(pchildren[a]);
			}
		}*/
		$('#btn_flush2').attr('disabled', 'disabled');
		var table = document.getElementById("interfaces_list");
		var rows = document.getElementById("interfaces_list")
				.getElementsByTagName('tr');
		/*for ( var i = rows.length-1; i>0; i--) {
			table.deleteRow(i);
		}*/
		$("#interfaces_list tr:not(:first)").remove();

		$
				.getJSON(
						"${ctx}/settingNetwork/flush.action?a=" + new Date(),
						function(result) {
							var htmlStr = "";
							$
									.each(
											result,
											function(i) {
												htmlStr += "<tr><td><a href='javascript:void(0);' onclick=\"lookDlg('"
														+ result[i].value
														+ "');\" style='cursor: pointer;'>"
														+ result[i].value
														+ "</a></td><td><a href='javascript:void(0);' onclick=\"lookDlg('"
														+ result[i].value
														+ "');\" style='cursor: pointer;'>查看</a>&nbsp;<a href='javascript:void(0);' onclick=\"recover('"
														+ result[i].value
														+ "');\" style='cursor: pointer;'>恢复</a></td></tr>";
											});
							htmlStr += "<tr><td colspan='2'><input type='button' style='padding-top: 3px; padding-left: 1px; font-size: 12px;' class='btnstyle1' value='刷新' id='flush' name='flush' onclick='flushrecover();' /></td></tr>";
							$("#interfaces_list").append(htmlStr);
							$('#btn_flush2').attr('disabled', '');
						});
	}
	function flush_network() {
		location.href = "${ctx}/settingNetwork/query.action";
	}
</script>
</head>

<body style="margin-top: 2px;margin-left:4px">
	<div class="framDiv" style="width:99.2%">
	<s:form name="ipForm" id="ipForm">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="r2titler" colspan="2"><b>网卡设置</b> <span
					style="float: right; padding-right: 10px;"> 网卡个数设置: <select
						style="width: 50px;" id="size" name="size"
						onchange="changeCard(this.value)">
							<option value="1"
								<c:if test="${cardSize==1}">selected="selected"</c:if>>
								1</option>
							<option value="2"
								<c:if test="${cardSize==2}">selected="selected"</c:if>>
								2</option>
							<option value="3"
								<c:if test="${cardSize==3}">selected="selected"</c:if>>
								3</option>
							<option value="4"
								<c:if test="${cardSize==4}">selected="selected"</c:if>>
								4</option>
							<option value="5"
								<c:if test="${cardSize==5}">selected="selected"</c:if>>
								5</option>
							<option value="6"
								<c:if test="${cardSize==6}">selected="selected"</c:if>>
								6</option>
							<option value="7"
								<c:if test="${cardSize==7}">selected="selected"</c:if>>
								7</option>
							<option value="8"
								<c:if test="${cardSize==8}">selected="selected"</c:if>>
								8</option>
							<option value="9"
								<c:if test="${cardSize==9}">selected="selected"</c:if>>
								9</option>
							<option value="10"
								<c:if test="${cardSize==10}">selected="selected"</c:if>>
								10</option>
					</select> </span>
				</td>
			</tr>

			<tr>
				<td width="40%" valign="top">

					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card1">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡1(eth0)</b>&nbsp;&nbsp;
								<font id="message1" color="red">修改网卡1后将会消耗一段时间进行重新通信</font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input name="b1Ip" id="b1Ip" style="width: 100%;"
											type="text" value="${b1Ip}"
											class="validate[required,custom[ipv4]] text-input"
											onchange="ipChange('b1');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b1Code" name="b1Code"
											style="width: 100%;" type="text" value="${b1Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b1Gate" name="b1Gate" style="width: 100%;"
																			type="text" value="${b1Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
				<td width="40%" valign="top">
					<!--  左侧table-->
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card2">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡2(eth1)</b>&nbsp;&nbsp;&nbsp;
								<font id="message2"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input id="b2Ip" name="b2Ip" style="width:100%;"
											class="validate[required,custom[ipv4]] text-input" type="text"
											value="${b2Ip}" onchange="ipChange('b2');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b2Code" name="b2Code"
											style="width:100%;" type="text" value="${b2Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b2Gate" name="b2Gate" style="width: 100%;"
																			type="text" value="${b2Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td width="40%" valign="top">
					<!--  左侧table-->
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card3">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡3(eth2)</b>&nbsp;&nbsp;&nbsp;
								<font id="message3"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input id="b3Ip" name="b3Ip" style="width:100%;"
											class="validate[required,custom[ipv4]] text-input" type="text"
											value="${b3Ip}" onchange="ipChange('b3');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b3Code" name="b3Code"
											style="width:100%;" type="text" value="${b3Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b3Gate" name="b3Gate" style="width: 100%;"
																			type="text" value="${b3Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
				<td width="40%" valign="top">
					<!--  左侧table-->
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card4">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡4(eth3)</b>&nbsp;&nbsp;&nbsp;
								<font id="message4"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input id="b4Ip" name="b4Ip" style="width:100%;"
											class="validate[required,custom[ipv4]] text-input" type="text"
											value="${b4Ip}" onchange="ipChange('b4');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b4Code" name="b4Code"
											style="width:100%;" type="text" value="${b4Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b4Gate" name="b4Gate" style="width: 100%;"
																			type="text" value="${b4Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="40%" valign="top">

					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card5">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡5(eth4)</b>&nbsp;&nbsp;&nbsp;
								<font id="message5"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input name="b5Ip" id="b5Ip" style="width:100%;"
											type="text" value="${b5Ip}"
											class="validate[required,custom[ipv4]] text-input" 
											onchange="ipChange('b5');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b5Code" name="b5Code"
											style="width:100%;" type="text" value="${b5Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b5Gate" name="b5Gate" style="width: 100%;"
																			type="text" value="${b5Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
				<td width="40%" valign="top">
					<!--  左侧table-->
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card6">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡6(eth5)</b>&nbsp;&nbsp;&nbsp;
								<font id="message6"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input id="b6Ip" name="b6Ip" style="width:100%;"
										class="validate[required,custom[ipv4]] text-input" type="text"
											value="${b6Ip}" onchange="ipChange('b6');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b6Code" name="b6Code"
											style="width:100%;" type="text" value="${b6Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b6Gate" name="b6Gate" style="width: 100%;"
																			type="text" value="${b6Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="40%" valign="top">

					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card7">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡7(eth6)</b>&nbsp;&nbsp;&nbsp;
								<font id="message7"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input name="b7Ip" id="b7Ip" style="width:100%;"
											type="text" value="${b7Ip}"
											class="validate[required,custom[ipv4]] text-input"
											onchange="ipChange('b7');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b7Code" name="b7Code"
											style="width:100%;" type="text" value="${b7Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b7Gate" name="b7Gate" style="width: 100%;"
																			type="text" value="${b7Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
				<td width="40%" valign="top">
					<!--  左侧table-->
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card8">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡8(eth7)</b>&nbsp;&nbsp;&nbsp;
								<font id="message8"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input id="b8Ip" name="b8Ip" style="width:100%;"
											class="validate[required,custom[ipv4]] text-input" type="text"
											value="${b8Ip}" onchange="ipChange('b8');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b8Code" name="b8Code"
											style="width:100%;" type="text" value="${b8Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b8Gate" name="b8Gate" style="width: 100%;"
																			type="text" value="${b8Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="40%" valign="top">

					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card9">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡9(eth8)</b>&nbsp;&nbsp;&nbsp;
								<font id="message9"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input name="b9Ip" id="b9Ip" style="width:100%;"
											type="text" value="${b9Ip}"
											class="validate[required,custom[ipv4]] text-input"
											onchange="ipChange('b9');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b9Code" name="b9Code"
											style="width:100%;" type="text" value="${b9Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b9Gate" name="b9Gate" style="width: 100%;"
																			type="text" value="${b9Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
								</table>
							</td>
						</tr>

						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
				<td width="40%" valign="top">
					<!--  左侧table-->
					<table width="90%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 20px;" id="card10">

						<tr>
							<td height="5px"></td>
						</tr>
						<tr>
							<td class="td_detail_title"><b>网卡10(eth9)</b>&nbsp;&nbsp;&nbsp;
								<font id="message10"></font>
							</td>
						</tr>

						<!-- 虚线分割线 -->
						<tr>
							<td colspan="2">
								<div class="xuxian"></div>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">

									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>

									<tr>
										<td class="td_employee_detail">IP地址</td>
										<td><input id="b10Ip" name="b10Ip" style="width:100%;"
											class="validate[required,custom[ipv4]] text-input" type="text"
											value="${b10Ip}" onchange="ipChange('b10');" />
										</td>
									</tr>
									<!-- 空行 -->
									<tr>
										<td class="td_detail_separator"></td>
									</tr>
									<tr>
										<td class="td_employee_detail">掩码</td>
										<td><input id="b10Code" name="b10Code"
											style="width:100%;" type="text" value="${b10Code}" />
										</td>
									</tr>
									<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
																<tr>
																	<td class="td_employee_detail">
																		网关
																	</td>
																	<td>
																		<input id="b10Gate" name="b10Gate" style="width: 100%;"
																			type="text" value="${b10Gate}" />
																	</td>
																</tr>
																<!-- 空行 -->
																<tr>
																	<td class="td_detail_separator">
																	</td>
																</tr>
									<!-- 虚线分割线 -->
									<tr>
										<td colspan="2">
											<div class="xuxian"></div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>

		</table>
		</s:form>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		style="margin-right: 4px;">
		<!-- 空行 -->
		<tr>
			<td height="8"></td>
		</tr>

		<tr>
			<td align="right" style="padding-right:6px"><input type="button" class="btnyh"
				style="font-family: 微软雅黑; font-size: 12px;" id="btnSaveCon"
				value="保存设置" onclick="save();" /></td>
		</tr>
	</table>
	<!-- 显示interfaces备份文件内容的dialog -->
	<div id="dialog-look">
		<table width='96%' border="0" cellspacing="5" cellpadding="5"
			align='center'>
			<tr>
				<td><s:textarea id="interfaces_content" rows="25" cols="62"></s:textarea>
				</td>
			</tr>
		</table>

	</div>
</body>
</html>
