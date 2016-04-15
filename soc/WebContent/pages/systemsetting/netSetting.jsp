<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/netSetting.css" rel="stylesheet"
	type="text/css">
<style type="text/css">

   .lable{
		margin:0px 2px 0px 0px;
		padding:0px 0px 0px 0px;
		width:55px;
		text-align: lift;
		height: 30px;
		font-size:12px;
		line-height: 30px;
    }
     
 .input{
	margin:0px 0px 0px auto;
	padding:0px 0px;
	height:25px;
	line-height: 20px;
	width:270px;
}

.inputYanMa{
    margin:0px 0px 0px 0px;
	padding:0px 0px;
	height:25px;
	font-size:12px;
	line-height: 20px;
	width:270px;
}


.row{
	margin:0px 0px;
	padding:0px 0px;
	height:25px;
	list-style-position: outsied;
	list-style-type: none ; 
	
}
</style>

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script language="javascript">
	var ldapNetJson;
	var serverNetJson;
	$(document).ready(function() {
		//选项卡
		$('#tabs-setting').tabs();
		serverNetJson = '${serverNetJson}';
		ldapNetJson = '${ldapNetJson}';
		initServerNet();
		$("#net_work").validationEngine('attach', {
			onValidationComplete : function(form, status) {
				if (status) {
					getJson("serv_net_","serverNetJson");
				}
			}
		});
		
		$("#ldap_work").validationEngine('attach', {
			onValidationComplete : function(form, status) {
				if (status) {
					getJson("ldap_net_","ldapNetJson");
				}
			}
		});
		
	});

	function initLdapNet() {
		initNet(10, "ldap_net_");
		var netSize = $.parseJSON('[' + ldapNetJson + ']').length == 0 ? 1 : $
				.parseJSON('[' + ldapNetJson + ']').length;
		displyNet(netSize,"ldap_net_");
		$("#opt_size").val(netSize);
		setVal(ldapNetJson,"ldap_net_");
		$("#opt_size").unbind("change");
		$("#opt_size").bind("change",function(){
			displyNet($(this).val(),"ldap_net_");
		});
		
		$("#btnSaveCon").unbind("click");
		$("#btnSaveCon").bind("click",function(){
			save("ldap_work");
		});
	}

	function initServerNet() {
		initNet(10, "serv_net_");
		var netSize = $.parseJSON('[' + serverNetJson + ']').length == 0 ? 1
				: $.parseJSON('[' + serverNetJson + ']').length;
		$("#opt_size").val(netSize);
		displyNet(netSize,"serv_net_");
		setVal(serverNetJson,"serv_net_");
		$("#opt_size").unbind("change");
		$("#opt_size").bind("change",function(){
			displyNet($(this).val(),"serv_net_");
		});
		
		$("#btnSaveCon").unbind("click");
		$("#btnSaveCon").bind("click",function(){
			save("net_work");
		});
	}

	function displyNet(num,ulId) {
		for ( var i = 0; i < num; i++) {
			$("#" + ulId + i).css("display", "block");
		}
		for ( var j = num; j < 10; j++) {
			$("#" + ulId + j).css("display", "none");
		}
	}

	function initNet(num, divId) {
		$("#"+divId).children().remove();
		for ( var i = 0; i < num; i++) {
			var ul = $('<ul></ul>').attr("id", divId + i);
			$("#" + divId).append($(ul));
			
<%--
			$("#"+divId).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			$("#"+divId).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	--%>		
			var ul_li = $('<li></li>');
			$(ul_li).appendTo(ul);

			var div = $('<div></div>').addClass("row");
			$(div).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable_head").text(
					"主机LAN" + (Number(i) + 1));
			$(label_head).appendTo(div);

			var label_head_nu = $('<label></label>').addClass("lable_head_un")
					.text("eth" + i);
			$(label_head_nu).appendTo(div);

			var link = $('<label class="link" for="'+divId+'link_main_'+i+'">启用心跳直接</label><input type="checkbox" id="'+divId+'link_main_'+i+'"/>');
			$(link).appendTo(div);
			$(link).bind(
					'click',
					function() {
						var j = $(this).attr("id").substring(19,
								$(this).attr("id").length);
						$("input[id!='"+divId+"link_stan_" + j + "']").attr("checked",
								false);
						$("input[id!='"+divId+"link_main_" + j + "']").attr("checked",
								false);
						$("#"+divId+"link_stan_" + j).attr("checked", "checked");
						$("#"+divId+"link_main_" + j).attr("checked", "checked");
					});

			var div1 = $('<div></div>').addClass("row rowleft");
			$(div1).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable_head").text(
					"备机LAN" + (Number(i) + 1));
			$(label_head).appendTo(div1);

			var label_head_nu = $('<label></label>').addClass("lable_head_un")
					.text("eth" + i);
			$(label_head_nu).appendTo(div1);

			var link = $('<label class="link" for="'+divId+'link_main_'+i+'">启用心跳直接</label><input type="checkbox" id="'+divId+'link_stan_'+i+'" />');
			$(link).appendTo(div1);

			//IP地址
			var ul_li = $('<li></li>');
			$(ul_li).appendTo(ul);

			var div = $('<div></div>').addClass("row");
			$(div).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"IP地址：");
			$(label_head).appendTo(div);

			var label_head_nu = $('<input type="text"/>').addClass(
					"validate[required,custom[ipv4]] text-input input").attr(
					"id", divId+"mainIp_" + i);
			$(label_head_nu).appendTo(div);

			var div1 = $('<div></div>').addClass("row rowleft");
			$(div1).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"IP地址：");
			$(label_head).appendTo(div1);

			var label_head_nu = $('<input type="text"/>').addClass(
					"validate[required,custom[ipv4]] text-input input").attr(
					"id", divId+"stanIp_" + i);
			$(label_head_nu).appendTo(div1);

			//IP掩码
			var ul_li = $('<li></li>');
			$(ul_li).appendTo(ul);

			var div = $('<div></div>').addClass("row");
			$(div).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"掩 码：");
			$(label_head).appendTo(div);

			var label_head_nu = $('&nbpsb;<input type="text"/>').addClass("inputYanMa")
					.attr("id", divId+"mainMask_" + i);
			
			$(label_head_nu).appendTo(div);

			var div1 = $('<div></div>').addClass("row rowleft");
			$(div1).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"掩 码：");
			$(label_head).appendTo(div1);

			var label_head_nu = $('&nbpsb;<input type="text"/>').addClass("inputYanMa")
					.attr("id", divId+"stanMask_" + i);
			$(label_head_nu).appendTo(div1);

			//IP网关
			var ul_li = $('<li></li>');
			$(ul_li).appendTo(ul);

			var div = $('<div></div>').addClass("row");
			$(div).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"网 关：");
			$(label_head).appendTo(div);

			var label_head_nu = $('&nbpsb;<input type="text" />').addClass(
					"validate[required,custom[ipv4]] text-input inputYanMa").attr(
					"id", divId+"mainGate_" + i);
			$(label_head_nu).appendTo(div);

			var div1 = $('<div></div>').addClass("row rowleft");
			$(div1).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"网 关：");
			$(label_head).appendTo(div1);

			var label_head_nu = $('&nbpsb;<input type="text" />').addClass(
					"validate[required,custom[ipv4]] text-input inputYanMa").attr(
					"id", divId+"stanGate_" + i);
			$(label_head_nu).appendTo(div1);

			//IP浮动IP
			var ul_li = $('<li></li>');
			$(ul_li).appendTo(ul);

			var div = $('<div></div>').addClass("row");
			$(div).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"浮动IP：");
			$(label_head).appendTo(div);

			var label_head_nu = $('<input type="text"/>').addClass(
					"validate[custom[ipv4]] text-input input").attr("id",
					divId+"mainHaIp_" + i);

			$(label_head_nu).appendTo(div);

			$(label_head_nu).bind(
					'change',
					function() {
						var j = $(this).attr("id").substring(18,
								$(this).attr("id").length);
						$("#"+divId+"stanHaIp_" + j).val($(this).val());
					});

			var div1 = $('<div></div>').addClass("row rowleft");
			$(div1).appendTo(ul_li);

			var label_head = $('<label></label>').addClass("lable").text(
					"浮动IP：");
			$(label_head).appendTo(div1);

			var label_head_nu1 = $('<input type="text"/>').addClass(
					"validate[custom[ipv4]] text-input input").attr({
				"id" : divId+"stanHaIp_" + i,
				"disabled" : "disabled"
			});
			$(label_head_nu1).appendTo(div1);
		}
	}

	function setVal(serverNetJson,divId) {
		var obj = $.parseJSON('[' + serverNetJson + ']');
		var size = $.parseJSON('[' + serverNetJson + ']').length;
	
		for ( var i = 0; i < size; i++) {
			$("#"+divId+"mainIp_" + i).val(obj[i].mainIp);
			$("#"+divId+"stanIp_" + i).val(obj[i].stanIp);
			$("#"+divId+"mainMask_" + i).val(obj[i].mainMask);
			$("#"+divId+"stanMask_" + i).val(obj[i].stanMask);
			$("#"+divId+"mainGate_" + i).val(obj[i].mainGateIp);
			$("#"+divId+"stanGate_" + i).val(obj[i].stanGateIp);
			$("#"+divId+"mainHaIp_" + i).val(obj[i].mainHaIp);
			$("#"+divId+"stanHaIp_" + i).val(obj[i].stanHaIp);
			$("#"+divId+"link_stan_" + i).attr("checked",
					obj[i].stanLink == "true" ? true : false);
			$("#"+divId+"link_main_" + i).attr("checked",
					obj[i].mainLink == "true" ? true : false);
		}
	}

	function save(id) {
		if (confirm("修改网络设置需要重启服务器，修改后请使用新的IP地址访问。确定要修改网络设置吗？")) {
			$("#"+id).submit();
		} 

		
	}

	function getJson(divId,inputId) {
		var netCardCount = $("#opt_size").val(); // 提交的网卡数量
		var netJson = "";
		for ( var i = 0; i < netCardCount; i++) {
			var netName = "eth" + i;
			var mainIp = $("#"+divId+"mainIp_" + i).val();
			var stanIP = $("#"+divId+"stanIp_" + i).val();
			var mainMask = $("#"+divId+"mainMask_" + i).val();
			var stanMask = $("#"+divId+"stanMask_" + i).val();
			var mainGate = $("#"+divId+"mainGate_" + i).val();
			var stanGate = $("#"+divId+"stanGate_" + i).val();
			var mainHaIp = $("#"+divId+"mainHaIp_" + i).val();
			var stanHaIp = $("#"+divId+"stanHaIp_" + i).val();
			var mainLink = $("#"+divId+"link_stan_" + i).attr("checked");
			var stanLink = $("#"+divId+"link_main_" + i).attr("checked");
			if ("" == netJson)
				netJson = '{"netName":"' + netName + '","mainIp":"' + mainIp
						+ '","stanIp":"' + stanIP + '","mainMask":"' + mainMask
						+ '","stanMask":"' + stanMask + '","mainGateIp":"'
						+ mainGate + '","stanGateIp":"' + stanGate
						+ '","mainHaIp":"' + mainHaIp + '","stanHaIp":"'
						+ stanHaIp + '","mainLink":"' + mainLink
						+ '","stanLink":"' + stanLink + '"}';
			else
				netJson += ',{"netName":"' + netName + '","mainIp":"' + mainIp
						+ '","stanIp":"' + stanIP + '","mainMask":"' + mainMask
						+ '","stanMask":"' + stanMask + '","mainGateIp":"'
						+ mainGate + '","stanGateIp":"' + stanGate
						+ '","mainHaIp":"' + mainHaIp + '","stanHaIp":"'
						+ stanHaIp + '","mainLink":"' + mainLink
						+ '","stanLink":"' + stanLink + '"}';
		}
		$("#"+inputId).val(netJson);
		$("#net_temp_work").attr("action",
				"${ctx}/settingNetwork/updateNet.action");
		$("#net_temp_work").submit();
	}
</script>
</head>

<body style="font-size: 12px;">

	<div id="tabs-setting" class="framDiv" style="width:99.2%">
	<table style="width:99.2%">
	<tr><td>
		<ul>
			<li><a href="#serv_net_" style="cursor: pointer;Font-size: 12px;"
				onclick="initServerNet();">Server网络配置</a></li>
			<%--<li><a href="#ldap_net_" style="cursor: pointer;Font-size: 12px;"
				onclick="initLdapNet();">LDAP网络配置</a>
			</li>
		    --%><li style="float: right;">
		    <span style="float: right; padding: 4px 5px 0px 0px; font-size:12px;">
					网卡个数设置: <select style="width: 50px;" id="opt_size" name="opt_size">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
					
			</span>
			</li>
		</ul>
		</td></tr>
		<tr><td>
		<form id="net_work">
			<div id="serv_net_" class="network"></div>
		</form>
		<form id="ldap_work">
			<div id="ldap_net_" class="network"></div>
		</form>
		</td></tr>
		</table>
	</div>
	<div style="margin:5px 0px 0px 0px;">
		<input type="button" class="btnyh"
			style="float:right; font-family: 微软雅黑; font-size: 12px;" id="btnSaveCon"
			value="保存并生效" />
	</div>
	<form id="net_temp_work" method="post">
		<input type="hidden" name="serverNetJson" id="serverNetJson">
		<input type="hidden" name="ldapNetJson" id="ldapNetJson">
	</form>
</body>
</html>
