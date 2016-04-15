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
<script language="javascript">
	jQuery(document).ready(function() {
	
	    $("#chkAll").click(function(){
			var chkFlag = $("#chkFlag").val();
			if(chkFlag == "false"){
				$("input[name='ids']").attr("checked",$(this).attr("checked"));
				chkFlag = "true";
			}else{
				$("input[name='ids']").attr("checked",$(this).attr("checked"));
				chkFlag = "false";
			}
		});
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
				alert("ip地址填写错误，最后一位必须为0")
				return false;
			}
		} else {
			$('#relsubnet').focus();
			return false;
		}
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
		save(vsubnet + "," + vmask + "," + vgateway);
	}
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
		$.post("${ctx}/settingRoute/saveCardSize.action", {
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

	/* function ping() {
		if ($('#ping_ip').val() == '') {
			alert("IP地址不能为空...");
			$('#ping_ip').focus();
			return;
		}
		var url = "${ctx}/settingRoute/executePing.action?method=executePing";

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
	} */
	
          /*  刷新路由表 */
	function flushroutelist() {
		$('#btn_flush1').attr('disabled', 'disabled');
		var opanel = document.getElementById("route_list");
		var pchildren = opanel.childNodes;
		//清空表中的行和列
		for ( var a = 0; a < pchildren.length; a++) {
			opanel.removeChild(pchildren[a]);
		}
		$.getJSON(
				"${ctx}/settingRoute/flush_route_list.action?t=" + new Date(),
				function(result) {
					var htmlStr = "";
					$.each(result, function(i) {
					 if(i==0)
					 {
						htmlStr += "<tr style='line-height: 15px;'><td class='biaoti'>"
								+ result[i].col1 + "</td><td class='biaoti'>" + result[i].col2
								+ "</td><td class='biaoti'>" + result[i].col3 + "</td><td class='biaoti'>"
								+ result[i].col4 + "</td><td class='biaoti'>" + result[i].col5
								+ "</td><td class='biaoti'>" + result[i].col6 + "</td><td class='biaoti'>"
								+ result[i].col7 + "</td><td class='biaoti'>" + result[i].col8
								+ "</td></tr>";
					}
					else
					{
					   htmlStr += "<tr style='line-height: 15px;'><td>"
								+ result[i].col1 + "</td><td>" + result[i].col2
								+ "</td><td>" + result[i].col3 + "</td><td>"
								+ result[i].col4 + "</td><td>" + result[i].col5
								+ "</td><td>" + result[i].col6 + "</td><td>"
								+ result[i].col7 + "</td><td>" + result[i].col8
								+ "</td></tr>";
					}
					});
					$('#route_list').html(htmlStr);
					$('#btn_flush1').attr('disabled', '');
				});

	}

	function save(routeInfo) {
		
		var data = "&routeInfo=" + routeInfo+"&routeflag=1";
		var url = "${ctx}/settingRoute/insertCard.action?method=insertCard";
		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			data : data,
			success : function(result) {
				alert(result);
				location.href = '${ctx}/login/logout.action';
			}
		});
	}

	function lookDlg(name) {
		var url = "${ctx}/settingRoute/lookInterfaces.action?method=lookInterfaces";
		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			data : "&interfacesName=" + name,
			success : function(result) {
				$('#interfaces_content').val(String($.trim(result)));
			}
		});
		$('#dialog-look').dialog('option', 'title', name).dialog("open");
	}
	
    /* 
	function recover(name) {
		if (confirm("恢复网络设置可能造成网络不可达，您确认要恢复吗？")) {
			if (confirm("确认恢复？")) {
				var url = "${ctx}/settingRoute/recoverNetwork.action?method=recoverNetwork";
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
	}*/

	//刷新恢复网络设置列表

	function flushrecover() {
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
						"${ctx}/settingRoute/flush.action?a=" + new Date(),
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
		location.href = "${ctx}/settingRoute/query.action";
	}

	//delete data
	function del() {
		//alert("123");
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
			   {
				ids += "," + $(this).val();
			   }
			else
				ids = $(this).val();
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个信息...");
			return;
		}
		//alert(ids);
		var data = "ids=" + ids;
		var url = "${ctx}/settingRoute/insertCard.action?method=insertCard";
		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			data : data,
			success : function(result) {
				alert(result);
				document.location.reload();
			}
		});
	}
</script>
</head>

<body>
	<div>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 1px">
			<c:if test="${warning==null}">
				<tr>
					<td>
						<div class="caozuobox">
							<div style=" margin-left: 8px;float:left">路由表</div>
							<div style=" margin-right: 4px;float:right">
								<input id="btn_flush1" type="button" value="刷新" class="btnstyle"
									onclick="flushroutelist();" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="sbox">
							<div class="cont">
								<table id="route_list" width="100%" border="0" cellspacing="1"
									cellpadding="0" class="tab2">
									<c:set var="temp" value="1"/>
									<s:iterator id="routeList" value="routeList" status="stat">
										<tr height="28">
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="13%" align="center">${col1}</td>
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="13%" align="center">${col2}</td>
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="13%" align="center">${col3}</td>
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="9%"  align="center">${col4}</td>
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="11%" align="center">${col5}</td>
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="11%" align="center">${col6}</td>
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="11%" align="center">${col7}</td>
											<td <c:if test="${temp==1}">class="biaoti"</c:if> width="13%" align="center">${col8}</td>
										</tr>
										<c:set var="temp" value="${temp+1}"/>
									</s:iterator>
								</table>
							</div>
						</div></td>
				</tr>
			</c:if>
		</table>
	</div>
	<!--  -->
	<form action="/systemAudit/query.action" method="post"
		 id="sysForm" name="sysForm">
		<div style="margin-top:2px;">
			<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 2px">
				<tr>
					<td>
						<div class="caozuobox">
							<div style=" margin-left: 8px;float:left">静态路由配置</div>
							<div style=" margin-right: 4px;float:right">
								<input type="button" value="增加路由" class="btnstyle" onclick="addStaticRouteDlg();" />
								<!-- <input type="button" value="增加默认" class="btnstyle" onclick="addStaticRouteDlg();" /> -->
								<input type="button" value="删除" class="btnstyle" onclick="del();" />
							</div>
						</div></td>
				</tr>
				<tr>
					<td>
						<div class="sbox">
							<div class="cont">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2">
									<tr height="28">
										<td width="6%" align="center" class="biaoti"><input
											type="checkbox" id="chkAll" name="chkAll"
											class="check-box not_checked" />
											<input type="hidden" id="chkFlag" value="false" />
										</td>
										<td width="15%" align="center" class="biaoti">子网</td>
										<td width="15%" align="center" class="biaoti">掩码</td>
										<td width="15%" align="center" class="biaoti">网关</td>
									</tr>
									
									<s:iterator value="staticRouteList" status="stat">
										<tr>
											<td valign="middle" class="biaocm" align="center"><input
												type="checkbox" name="ids" id="${td_0}" value="${td_0}"
												class="check-box" /></td>
											<td align="center">${td_0}</td>
											<td align="center">${td_1}</td>
											<td align="center">${td_2}</td>
										</tr>
									</s:iterator>
								</table>
							</div>
						</div></td>
				</tr>
			</table>
		</div>
		<div id="dialog-addStaticRoute" title="添加静态路由">
			<table width='96%' border="0" cellspacing="5" cellpadding="5"
				align='center'>
				<tr>
					<td align="center" style="width: 30%">子网</td>
					<td><input type="text" id="relsubnet" name="relsubnet"
						class="validate[required,custom[ipv4]] text-input"
						onchange="staticIpChange();" />
					</td>
				</tr>
				<tr>
					<td align="center">掩码</td>
					<td><input type="text" id="relmask" name="relmask" />
					</td>
				</tr>
				<tr>
					<td align="center">网关</td>
					<td><input type="text" id="relgateway" name="relgateway" /></td>
				</tr>
				<tr>
					<td></td>
					<td><span id="mes"></span>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
