<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 
<script type="text/javascript" src="/soc/pushlet/ajax-pushlet-client.js"></script>


<link href="/soc/styles/style.css" rel="stylesheet" type="text/css">
<link href="/soc/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="/soc/styles/template.css" rel="stylesheet" type="text/css">

	<!-- <link href="/soc/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> -->
<link href="/soc/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>


<script type='text/javascript'
	src="/soc/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="/soc/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="/soc/scripts/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript'
	src="/soc/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="/soc/scripts/util.js"></script>
<script type='text/javascript' src="/soc/scripts/popuplayer.js"></script>
<script type='text/vbscript' src="/soc/scripts/ascii.vbs"></script>
<script type='text/javascript' src="/soc/scripts/pinyin.js"></script>
<script type='text/javascript' src="${ctx}/scripts/filterpage.js"></script>
<!-- ------- -->
<style type="text/css">

table{
font-size:12px;
}

</style>


<!-- ------- -->
<script type='text/javascript'>
	jQuery(document).ready(function() {

		
		//设备divalert(1);
		
		jQuery("#from").validationEngine();
		$('#dialog-asset').dialog({
			autoOpen : false,
			width : 420,
			height : 400,
			buttons : {
				"确定[Enter]" : function() {
					addOk('assetSelect','dialog-asset','chk-asset');
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		//事件类别div
		$('#dialog-eventCategory').dialog({
			autoOpen : false,
			width : 400,
			height : 400,
			buttons : {
				"确定[Enter]" : function() {
					addOk('eventCategorySelect','dialog-eventCategory','chk-eventCategory');
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		//事件类型div
		$('#dialog-eventType').dialog({
			autoOpen : false,
			width : 600,
			height : 400,
			buttons : {
				"确定[Enter]" : function() {
					addOk('eventTypeSelect','dialog-eventType','chk-eventsType');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		//源地址
		$('#dialog_sourceIp').dialog({
			autoOpen : false,
			width : 440,
			height : 300,

			buttons : {
				"确定[Enter]" : function() {
					//address();
//参数意义ipStart:弹出div的开始ip;ipEnd:弹出div的结束ip;ips:select的id;div_ip:两个ip做验证的提示div的id

  					address('sourceIpStart','sourceIpEnd','sourceIps','div_sourceIp','dialog_sourceIp','sourceAdd');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		//目标地址
		$('#dialog_targetIp').dialog({
			autoOpen : false,
			width : 440,
			height : 300,

			buttons : {
				"确定[Enter]" : function() {
					//address();
//参数意义ipStart:弹出div的开始ip;ipEnd:弹出div的结束ip;ips:select的id;div_ip:两个ip做验证的提示div的id

  					address('targetIpStart','targetIpEnd','targetIps','div_targetIp','dialog_targetIp','targetAdd');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		//源端口
		$('#dialog_sourcePort').dialog({
			autoOpen : false,
			width : 440,
			height : 300,

			buttons : {
				"确定[Enter]" : function() {
					//address();
//参数意义ipStart:弹出div的开始ip;ipEnd:弹出div的结束ip;ips:select的id;div_ip:两个ip做验证的提示div的id

  					addPort('sourcePortStart','sourcePortEnd','sourcePorts','div_sourcePort','dialog_sourcePort','sourceP');
					
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		//目标端口
		$('#dialog_targetPort').dialog({
			autoOpen : false,
			width : 440,
			height : 300,

			buttons : {
				"确定[Enter]" : function() {
					//address();
//参数意义ipStart:弹出div的开始ip;ipEnd:弹出div的结束ip;ips:select的id;div_ip:两个ip做验证的提示div的id

  					addPort('targetPortStart','targetPortEnd','targetPorts','div_targetPort','dialog_targetPort','targetP');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		
		  //根据从后台传过来的等级信息在对应的地方打勾		  
			 //alert(${sizeThreadValue});
			 var priorityArr = [${priorityArr[0]},${priorityArr[1]},${priorityArr[2]},${priorityArr[3]},${priorityArr[4]}];
			 for(var i=0;i<5;i++){
				
				 $("#priority"+priorityArr[i]).attr("checked", true); 
			 }
				//初始化
		initCheckBox('assetSelect','chk-asset');
		initCheckBox('eventCategorySelect','chk-eventCategory');
		initCheckBox('eventTypeSelect','chk-eventType');
		
	});
	function addOk(typeSelect, checkBoxDiv,checkBoxName) {
		 // 确定按钮
		$("#"+typeSelect).empty();
		$("#"+checkBoxDiv+" input[type='checkbox'][name="+checkBoxName+"]:checked").each(
		   function() {
			 var node = $(this).parent().next().children("a");
		        $("#"+typeSelect).append("<option value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
		         }
		);
		$("#"+checkBoxDiv).dialog('close');
		}
	//点击链接选择他左面的box
	function checkLeftBox(id){
	//	alert(id);
		if ($("#"+id).attr("checked") == false) {	
		$("#"+id).attr("checked",true);	
		}else{
		$("#"+id).attr("checked",false);	
			}
		};

	//加载设备表方法
	function showAssetDialog() {
		$("#dialog-asset").dialog("open");
	}
	//加载事件类别表方法
	function showEventCategory() {
		//alert(1);
		$("#dialog-eventCategory").dialog("open");
	}
	//加载事件类型表方法
	function showEventType() {
		//alert(1);
		$("#dialog-eventType").dialog("open");
	}
	//做设备和事件类型的前台分页
$(function(){
	goPage(1,10,'dlg-table-asset','barcon');
	goPage(1,10,'dlg-table-eventType','eventTypeBarcon');

	checkAll('eventsCategory-checkAll','chk-eventCategory');
	checkAll('eventsType-checkAll','chk-eventsType');
	checkAll('asset-checkAll','chk-asset');
});


//验证IP地址合法性
//参数意义ipStart:弹出div的开始ip;ipEnd:弹出div的结束ip;ips:select的id;div_ip:两个ip做验证的提示div的id
function address(ipStartHtml,ipEndHtml,ips,div_ip,dialog_div,condition_sel) {
	//alert(ipStart);
	//条件框框的index
	var condition = document.getElementById(condition_sel).selectedIndex;
	var conditionText = $("#"+condition_sel).val();
	var ipStart = $("#"+ipStartHtml).val();
	//alert(ipStart);
	var ipend = $("#"+ipEndHtml).val();
	//alert(ipend);
	var e = $("#"+ips);
	//一个条件的情况
	if(condition!=2){
		//alert(conditionText);
		//先处理格式问题
		var flag2 =
			checkIp(ipStartHtml,'div_sourceIpStart');
		if(flag2&&ipStart!=""){
		//如果格式对了
		//判断是否有相同的数据
			if(!judgeRepeatedSelect(ips,conditionText+":"+ ipStart)){
				alert("请不要加入相同数据");
			}
			else{
				e.append("<option value=\""
						+conditionText+":"
						+ $("#"+ipStartHtml).val() +"\">" +conditionText+":"
						+ $("#"+ipStartHtml).val() 
						+  "</option>");
				$('#'+dialog_div).dialog('close');
			}

		}else{
			if(ipStart==""){
				document.getElementById(div_ip).innerHTML = "请输入地址...";	
			}else{
				document.getElementById(div_ip).innerHTML = "";
			}
			
		}
		
		
	}
else if ((ipStart == "" && ipend != "") || (ipend == "" && ipStart != "")) {
		document.getElementById(div_ip).innerHTML = "两个地址必须同时输入或者同时不输入";
	} else if (ipStart == "" && ipend == "") {
		document.getElementById(div_ip).innerHTML = "请输入合法地址...";
	} else {
		var flag2 = checkIp(ipStartHtml,'div_sourceIpStart');
		var flag3 =$("#sourceIpStart").val();
		var result1 = checkIp(ipEndHtml,'div_sourceIpEnd');
		var result2=$("#sourceIpEnd").val();
		//alert(flag2);
		if (flag2 && result1) {
			//判断首位是否相同
			if (parseInt(ipStart.split('.')[0]) != parseInt(ipend.split('.')[0])) {
				document.getElementById(div_ip).innerHTML = "起始地址与终止地址必须在相同段";
			} else if (parseInt(ipStart.split('.')[0]) == parseInt(ipend
					.split('.')[0])) {
				//判断第二位是否相同
				if (parseInt(ipStart.split('.')[1]) != parseInt(ipend
						.split('.')[1])) {
					document.getElementById(div_ip).innerHTML = "起始地址与终止地址必须在相同段";
				} else if (parseInt(ipStart.split('.')[1]) == parseInt(ipend
						.split('.')[1])) {
					//判断第三位 是否相同
					if (parseInt(ipStart.split('.')[2]) != parseInt(ipend
							.split('.')[2])) {
						document.getElementById(div_ip).innerHTML = "起始地址与终止地址必须在相同段";
					} else if (parseInt(ipStart.split('.')[2]) == parseInt(ipend
							.split('.')[2])) {
						//判断第四为是否相同
						if (parseInt(ipStart.split('.')[3]) > parseInt(ipend.split('.')[3])||parseInt(ipStart.split('.')[3]) == parseInt(ipend.split('.')[3])) {
							//alert(1);
							document.getElementById(div_ip).innerHTML = "起始地址必须小于终止地址";
						} else {
							
							if(!judgeRepeatedSelect(ips,conditionText+":"+ ipStart+"-"+ipend)){
								alert("请不要加入相同数据");
							}else{
								e.append("<option value=\""
										+conditionText+":"
										+ $("#"+ipStartHtml).val() + "-"
										+ $("#"+ipEndHtml).val() + "\">" +conditionText+":"
										+ $("#"+ipStartHtml).val() + "-"
										+ $("#"+ipEndHtml).val() + "</option>");
								$('#'+dialog_div).dialog('close');
							}
							

						}

					} else {
						
						if(!judgeRepeatedSelect(ips,conditionText+":"+ ipStart+"-"+ipend)){
							alert("请不要加入相同数据");
						}else{
							e.append("<option value=\""
									+conditionText+":"
									+ $("#"+ipStartHtml).val() + "-"
									+ $("#"+ipEndHtml).val() + "\">" +conditionText+":"
									+ $("#"+ipStartHtml).val() + "-"
									+ $("#"+ipEndHtml).val() + "</option>");
							$('#'+dialog_div).dialog('close');
						}
					}

				} else if (parseInt(ipStart.split('.')[1]) < parseInt(ipend
						.split('.')[1])) {
					if(!judgeRepeatedSelect(ips,conditionText+":"+ ipStart+"-"+ipend)){
						alert("请不要加入相同数据");
					}else{
						e.append("<option value=\""
								+conditionText+":"
								+ $("#"+ipStartHtml).val() + "-"
								+ $("#"+ipEndHtml).val() + "\">" +conditionText+":"
								+ $("#"+ipStartHtml).val() + "-"
								+ $("#"+ipEndHtml).val() + "</option>");
						$('#'+dialog_div).dialog('close');
					}
				}

			} else if (parseInt(ipStart.split('.')[0]) < parseInt(ipend
					.split('.')[0])) {
				if(!judgeRepeatedSelect(ips,conditionText+":"+ ipStart+"-"+ipend)){
					alert("请不要加入相同数据");
				}else{
					e.append("<option value=\""
							+conditionText+":"
							+ $("#"+ipStartHtml).val() + "-"
							+ $("#"+ipEndHtml).val() + "\">" +conditionText+":"
							+ $("#"+ipStartHtml).val() + "-"
							+ $("#"+ipEndHtml).val() + "</option>");
					$('#'+dialog_div).dialog('close');
				}
			}

		} else {
			if(ipStart==""||ipend==""){
				document.getElementById(div_ip).innerHTML = "请输入合法的IP段...";
			}
			
		}

	}
}

	
//验证端口合法性
//参数意义ipStart:弹出div的开始ip;ipEnd:弹出div的结束ip;ips:select的id;div_ip:两个ip做验证的提示div的id
function addPort(ipStartHtml,ipEndHtml,ips,div_ip,dialog_div,condition_sel) {
	//alert(ipStart);
	//条件框框的index
	var condition = document.getElementById(condition_sel).selectedIndex;
	var conditionText = $("#"+condition_sel).val();
	var ipStart = $("#"+ipStartHtml).val();
	//alert(ipStart);
	var ipend = $("#"+ipEndHtml).val();
	var e = $("#"+ips);

	//一个条件的情况
	if(condition!=2){
		//alert(conditionText);
		//先处理格式问题
		var flag2 = checkPort(ipStartHtml,'div_sourcePortStart');
		if(flag2&&ipStart!=""){
		//如果格式对了
		//如果有相同的
		//alert(judgeRepeatedSelect(ips,conditionText+":"+ ipStart));
		if(!judgeRepeatedSelect(ips,conditionText+":"+ ipStart)){
			alert("请不要加入相同数据");
			
		}else{
			e.append("<option value=\""
					+conditionText+":"
					+ $("#"+ipStartHtml).val() +"\">" +conditionText+":"
					+ $("#"+ipStartHtml).val() 
					+  "</option>");
			$('#'+dialog_div).dialog('close');
		}
				
		}else{
			if(ipStart==""){
				document.getElementById(div_ip).innerHTML = "请输入合法端口...";
			}
		
			
		}
		
		
	}
else if ((ipStart == "" && ipend != "") || (ipend == "" && ipStart != "")) {
		document.getElementById(div_ip).innerHTML = "两个端口必须同时输入或者同时不输入";
	} else if (ipStart == "" && ipend == "") {
		document.getElementById(div_ip).innerHTML = "请输入端口...";
	} else {
		var flag2 = checkPort(ipStartHtml,'div_sourcePortStart');

		var result1 = checkPort(ipEndHtml,'div_sourcePortEnd');
		//alert(flag2);
		if (flag2 && result1) {
			//判断首位是否相同
			if (parseInt(ipStart) > parseInt(ipend)) {
				document.getElementById(div_ip).innerHTML = "起始端口必须小于终止端口";
			} else{
				if(!judgeRepeatedSelect(ips,conditionText+":"+ ipStart+"-"+ipend)){
					alert("请不要加入相同数据");
					
				}else{
					e.append("<option value=\""
							+conditionText+":"
							+ $("#"+ipStartHtml).val() + "-"
							+ $("#"+ipEndHtml).val() + "\">" +conditionText+":"
							+ $("#"+ipStartHtml).val() + "-"
							+ $("#"+ipEndHtml).val() + "</option>");
					$('#'+dialog_div).dialog('close');
					$('#'+dialog_div).dialog('close');
				}
				
				
			}

		} else {
			if(ipStart==""||ipend==""){
				document.getElementById(div_ip).innerHTML = "请输入合法端口...";
			}
		
		}

	}
}

	
	
	
	
	
//验证起始IP段合法性
//参数意义ipStart:输入ip框框的id;div_ipStart:提示ip不合法的框框的id
function checkIp(ipStart,div_ipStart) {
	var ipStart = $("#"+ipStart).val();
	if (ipStart != "") {
		var re = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
		var flag = re.test(ipStart);
		if (flag) {
			if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
				$("#"+div_ipStart).addClass("spanred");
				$("#"+div_ipStart).html("ip地址不合法");
				return false;
			} else {
				$("#"+div_ipStart).removeClass("spanred");
				$("#"+div_ipStart).html(
						"<img border=0 src=\"${ctx}/images/ok.png\" />");
				return true;
			}
		} else {
			$("#"+div_ipStart).addClass("spanred");
			$("#"+div_ipStart).html("ip地址不合法");
			return false;
		}
	} else {
		$("#"+div_ipStart).html("");
		return true;
	}
}

//验证起始IP段合法性
//参数意义ipStart:输入ip框框的id;div_ipStart:提示ip不合法的框框的id
function checkPort(ipStart,div_ipStart) {
	var ipStart = $("#"+ipStart).val();
	if (ipStart != "") {
		 var re=/^(\d)+$/g;//匹配数字

		var flag = re.test(ipStart);
		  
		if (flag&&parseInt(ipStart)>0&&parseInt(ipStart)<65536) {	
			$("#"+div_ipStart).removeClass("spanred");
			$("#"+div_ipStart).html(
					"<img border=0 src=\"${ctx}/images/ok.png\" />");
			return true;
			
		}else{
			$("#"+div_ipStart).addClass("spanred");
			$("#"+div_ipStart).html("端口不合法");
			return false;
		}
		
	} else {
		$("#"+div_ipStart).html("");
		return true;
	}
}


//弹出地址策略对话框		
//参数意义:ipStart输入框id,ipEnd输入框id;div_ip验证两个ip的提示框框的id;div_ipStart,,div_ipStart:ip不合法提示框框id;dialog_addressPolicy:要show的div的id
function showIpDlg(ipStart,ipEnd,div_ip,div_ipStart,div_ipEnd,dialog_addressPolicy) {
	//alert(1);
	$("#"+ipStart).val("");
	$("#"+ipEnd).val("");
	document.getElementById(div_ip).innerHTML = "";
	$("#"+div_ipStart).html("");
	$("#"+div_ipEnd).html("");
	$("#"+dialog_addressPolicy).dialog('open');
	//$('#dialog_addressPolicy').focus();
}

//处理input和-show或者hidden的方法 1 = show else =hudden
function showOrHidden(id,status){
	//alert($(id).html());
	if(status==1){
		$(id).show();
	}else{
		$(id).hide();
	}
	
	
}
//源ip下拉框事件
function targetAddfunc(){	
	var val = $("#targetAdd").val();
	if(val =='两者之间'){
		showOrHidden("#targetAddBetween",1);
	}else{
		showOrHidden("#targetAddBetween",0);
	}	
}
//源端口下拉框事件
function targetPortfunc(){	
	var val = $("#targetP").val();
	
	if(val =='两者之间'){
		showOrHidden("#targetPortBetween",1);
	}else{
		showOrHidden("#targetPortBetween",0);
	}	
}
//目标ip下拉框事件
function sourceAddfunc(){	
	var val = $("#sourceAdd").val();
	if(val =='两者之间'){
		showOrHidden("#sourceAddBetween",1);
	}else{
		showOrHidden("#sourceAddBetween",0);
	}	
}
//目标端口下拉框事件
function sourcePortfunc(){	
	var val = $("#sourceP").val();

	if(val =='两者之间'){
		showOrHidden("#sourcePortBetween",1);
	}else{
		showOrHidden("#sourcePortBetween",0);
	}	
}
//判断select中的东西跟要加入的是否有重复的
function judgeRepeatedSelect(selectId,tex){
	var flg = true;

	$("#"+selectId+" option").each(function() { 

		flg = true;
        if ($(this).text() == tex) { 
        	flg = false;
    		return false;
        }
        
        });      
	return flg;
}
//保存规则
function subData() {
	
	$('#assetIds').val('');
	$("#assetSelect").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#assetIds').val($('#assetIds').val() + $(this).val() + ",");
		}
	});



	$('#eventsCategoryIds').val('');
	$("#eventCategorySelect").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#eventsCategoryIds').val($('#eventsCategoryIds').val() + $(this).val() + ",");
		}
	});


	$('#eventsTypeIds').val('');
	$("#eventTypeSelect").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#eventsTypeIds').val($('#eventsTypeIds').val() + $(this).val() + ",");
		}
	});


	$('#targetAddr').val('');
	$("#targetIps").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#targetAddr').val($('#targetAddr').val() + $(this).val() + ",");
		}
	});

	$('#sourceAddr').val('');
	$("#sourceIps").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#sourceAddr').val($('#sourceAddr').val() + $(this).val() + ",");
		}
	});
	$('#sourcePort').val('');
	$("#sourcePorts").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#sourcePort').val($('#sourcePort').val() + $(this).val() + ",");
		}
	});
	$('#targetPort').val('');
	$("#targetPorts").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#targetPort').val($('#targetPort').val() + $(this).val() + ",");
			
		}
	});
	$("#from").submit();
	//alert($('#targetPort').val());
}

//全选的封装方法  参数意义 checkAllId 全选box的id  checkName 下面小box的name

function checkAll(checkAllId,checkName){
	//alert(checkName);
	$("#"+checkAllId).live("click", function(event) {
		if ($("#"+checkAllId).hasClass('not-checked-assetType')) {
			$("#"+checkAllId).removeClass('not-checked-assetType');
			$("input[name="+checkName+"]").attr('checked', false);
		} else {
			$("#"+checkAllId).addClass('not-checked-assetType');
			$("input[name="+checkName+"]").attr('checked', true);
		}
	});
	
}


</script>

</head>

<body style="margin:5px;" >



	<s:form action="insertOrUpdateFilterRule" namespace="/filteringRules"
		method="post" theme="simple" id="from">
	
<input type="text"
	style="display:none;" name="filteringRule.filteringRuleId"
			value="${filteringRule.filteringRuleId }" />										
		<!-- 公司table-->
		<div class="framDiv" style="width:100%;">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<!-- 用户信息 -->
				<tr>
					<td class='r2titler' colspan='3' ><b>过滤规则</b></td>
				</tr>
				<tr>
					<td>

						<table width="99%" border="0" align="center" cellspacing="0"
							cellpadding="1">
							
							<!-- 公告标题 -->
							<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px; padding-top: 10px"><span
									class="spanred">*&nbsp;</span>过滤器名称:</td>
								<td align="left" class="padding" style="padding-top: 10px"><input 
									class="validate[required,custom[onlyLetterNumber]] text-input"
									name="filteringRule.filteringRuleName"
									value="${filteringRule.filteringRuleName }" type="text"
									size="40" id="assetName" maxlength="255" style="width: 270px" />
								</td>

							</tr>
							
							<!-- 空行-->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<!-- 创建者 -->
							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;">创建者:</td>
								<td align="left" class="padding"><input
									value="${filteringRule.filteringRuleCreater }"
									name="filteringRule.filteringRuleCreater"
									value="" type="text"  disabled ="disabled"
									size="40" id="assetName" maxlength="255" style="width: 270px" />
								</td>

								</td>
							</tr>
							<!-- 空行-->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<!--来源 -->
							<tr >
								<td align="right" style="padding-left:10px;font-size:12px;">创建时间:</td>
								<td align="left" class="padding"><input
									name="filteringRule.filteringRuleCreateDate" id="assetGateWays"
									value='<s:date
										name="filteringRule.filteringRuleCreateDate" format="yyyy-MM-dd HH:mm:ss" />' disabled ="disabled"
									type="text"
									
									size="40" id="assetGateWay" maxlength="255"
									style="width: 270px" 
									readonly="readonly" />
								</td>

								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr >
								<td align="right" style="padding-left:10px;font-size:12px;">修改者:</td>
								<td align="left" class="padding">
								
								<input
									disabled ="disabled"
									name="filteringRule.filteringRuleMender"
									value="${filteringRule.filteringRuleMender}" type="text"
									size="40" id="assetName" maxlength="255" style="width: 270px" /> 
									
								</td>

								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>


							<tr >
								<td align="right" style="padding-left:10px;font-size:12px;">修改时间:</td>
								<td align="left" class="padding">
								
								
								<input
									name="" id="assetGateWays"
									value='<s:date
										name="filteringRule.filteringRuleMenderDate" format="yyyy-MM-dd HH:mm:ss" />'
									type="text"
									disabled ="disabled"
									size="40" id="assetGateWay" maxlength="255"
									style="width: 270px" 
									>
								</td>

								
							</tr>
						
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;">是否启用:</td>
								<td align="left" class="padding"><input type="radio"
									name="filteringRule.filteringRuleType" value="0"<c:if test="${filteringRule.filteringRuleType eq 0}">checked="checked"</c:if> title="启用"
									checked="checked" /><span
									style="font-size:12px;" >启用</span> <input type="radio"
									name="filteringRule.filteringRuleType" value="1" <c:if test="${filteringRule.filteringRuleType eq 1}">checked="checked"</c:if> title="停用"/><span
									style="font-size:12px;">停用</span>
								</td>

							
							</tr>


							<%--<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 虚线分割线 -->
						<tr>
							<td colspan="3">
								<div class="xuxian"></div></td>
						</tr>
						--%>
							<tr>
								<td height="5px"></td>
							</tr>

							<tr>
								<td colspan="3"></td>
							</tr>




						</table>
					</td>




					<td>

						<table width="99%" border="0" align="center" cellspacing="0"
							cellpadding="1">
							<!-- 空行 -->

						<tr >

								<td align="right" style="vertical-align:20%" align = "top"
									style="padding-left:10px;font-size:12px;">过滤器描述:</td>
								<td class="padding"><textarea
										name="filteringRule.filteringRuleDes" id="assetMemo" cols="37"
										rows="8" style="width:273px;resize:none;">${filteringRule.filteringRuleDes }</textarea>
								</td>
							</tr>

							</td>
							</tr>




						</table>
					</td>


























				</tr>
			</table>



		
		</div>

		<div class="td_detail_separator"></div>








		<div class="framDiv" style="width:100%;">
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<!-- 用户信息 -->
				<tr>
					<td class='r2titler' colspan='3' "><b>过滤条件</b></td>
				</tr>

			</table>
			<!-- ----------------------------------------------------------------------------------------------- -->
<div style = "float:left;width:49%;">
<table width="99%" border="0"  cellspacing="0"
				cellpadding="1" style = "text-align:center">
				<!-- 空行 -->
<tr>
		<td class="td_detail_separator"></td>
	</tr>
				
<tr>
		<td class="row" align= "right" width = "20%"><span>级&nbsp;&nbsp;&nbsp;&nbsp;别：</span></td>
		<td align= "left">
		&nbsp;<input type="checkbox" name="priorityArr" value="1"  id="priority1"/>&nbsp;1&nbsp;
		<input type="checkbox" name="priorityArr" value="2" id="priority2"/>&nbsp;2&nbsp;
			<input type="checkbox" name="priorityArr" value="3" id="priority3"/>&nbsp;3&nbsp;
			<input type="checkbox" name="priorityArr" value="4" id="priority4"/>&nbsp;4&nbsp;
		<input type="checkbox" name="priorityArr" value="5" id="priority5"/>&nbsp;5&nbsp;
									
								</td>
							</tr>
<tr>
		<td class="td_detail_separator"></td>
	</tr>
	
	<%--<tr valign="top">
											--%>
									<tr valign="top">		<td width="15%" align="right">关联设备：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="assetSelect" name = "assetSelect" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="assetListSel" status="stat">
																		<option value="${ASSET_ID}" id="deviceTypeid">${ASSET_NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="filteringRule.assetIds" id="assetIds" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showAssetDialog();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('assetSelect','chk-asset');" />
														</td>
													</tr>
												</table></td>
										</tr>
<tr>
		<td class="td_detail_separator"></td>
	</tr>
										<tr valign="top">		<td width="15%" align="right">事件类别：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="eventCategorySelect" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="eventCategoryListSel" status="stat">
																		<option value="${ID}" id="deviceTypeid">${NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="filteringRule.eventsCategoryIds" id="eventsCategoryIds" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showEventCategory();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('eventCategorySelect','chk-eventCategory');" />
														</td>
													</tr>
												</table></td>
										</tr>
	<tr>
		<td class="td_detail_separator"></td>
	</tr>
											<tr valign="top">		<td width="15%" align="right">事件类型：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="eventTypeSelect" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="eventTypeTagListSel" status="stat">
																		<option value="${ID}" id="deviceTypeid">${NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="filteringRule.eventsTypeIds" id="eventsTypeIds" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showEventType();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('eventTypeSelect','chk-eventType');" />
														</td>
													</tr>
												</table></td>
										</tr>
	</table>
	
	
	
	
	
	
	
<table width="99%" border="0"  cellspacing="0"
				cellpadding="1" style = "text-align:center">


<tr>
								<td class="td_detail_separator"></td>
							</tr>





	</table>



</div>




<div style = "float:left;width:49%;">
<table width="99%" border="0"  cellspacing="0"
				cellpadding="1" style = "text-align:center">
				<!-- 空行 -->
		

<tr>
		<td class="td_detail_separator"></td>
	</tr>
	</table>
<table width="99%" border="0"  cellspacing="0"
				cellpadding="1" style = "text-align:center">

<tr>
	<td class="td_detail_separator"></td>
</tr>
<tr>
	<td class="td_detail_separator"></td>
</tr>
<tr>
	<td class="td_detail_separator"></td>
</tr>
	<tr valign="top">		<td width="15%" align="right">源IP：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="sourceIps" name = "sourceIps" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="srouceAddrSel" status="stat" id = "srouceAddrOpt">
																		<option value="${srouceAddrOpt}" id="deviceTypeid">${srouceAddrOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="filteringRule.sourceAddr" id="sourceAddr" />
													</tr>
													
												</table></td>
										</tr>
		
													<td height="2px"></td>
										</tr>
										<tr>
											<td></td>
											<td>
												<table width="98%" border="0" cellspacing="0"
													cellpadding="0" style="align:right">
													<tr>
														<td align="left" style="padding-left:4px"><input
															type="button" value="" class="btnadd"
															onclick="showIpDlg('sourceIpStart','sourceIpEnd','div_sourceIp','div_sourceIpStart','div_sourceIpEnd','dialog_sourceIp');" /> <input type="button"
															value="" class="btndel" onclick="delOption('sourceIps','');" /> <br /></td>
													</tr>
												</table></td>
										</tr>

<tr>
		<td class="td_detail_separator"></td>
	</tr>
	
	
	
		<tr valign="top">		<td width="15%" align="right">目标IP：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="targetIps" name = "targetIps" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="targetAddrSel" status="stat" id = "targetAddrOpt">
																		<option value="${targetAddrOpt}" id="deviceTypeid">${targetAddrOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="filteringRule.targetAddr" id="targetAddr" />
													</tr>
													
												</table></td>
										</tr>
		
													<td height="2px"></td>
										</tr>
										<tr>
											<td></td>
											<td>
												<table width="98%" border="0" cellspacing="0"
													cellpadding="0" style="align:right">
													<tr>
														<td align="left" style="padding-left:4px"><input
															type="button" value="" class="btnadd"
															onclick="showIpDlg('targetIpStart','targetIpEnd','div_targetIp','div_targetIpStart','div_targetIpEnd','dialog_targetIp');" /> <input type="button"
															value="" class="btndel" onclick="delOption('targetIps','');" /> <br /></td>
													</tr>
												</table></td>
										</tr>

<tr>
		<td class="td_detail_separator"></td>
	</tr>
	

	<tr valign="top">		<td width="15%" align="right">源端口：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="sourcePorts" name = "sourcePorts" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="sourcePortSel" status="stat" id = "sourcePortOpt">
																		<option value="${sourcePortOpt}" id="deviceTypeid">${sourcePortOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="filteringRule.sourcePort" id="sourcePort" />
													</tr>
													
												</table></td>
										</tr>
		
													<td height="2px"></td>
										</tr>
										<tr>
											<td></td>
											<td>
												<table width="98%" border="0" cellspacing="0"
													cellpadding="0" style="align:right">
													<tr>
														<td align="left" style="padding-left:4px"><input
															type="button" value="" class="btnadd"
															onclick="showIpDlg('sourcePortStart','sourcePortEnd','div_sourcePort','div_sourceIpStart','div_sourcePortEnd','dialog_sourcePort');" /> <input type="button"
															value="" class="btndel" onclick="delOption('sourcePorts','');" /> <br /></td>
													</tr>
												</table></td>
										</tr>
	<tr>
		<td class="td_detail_separator"></td>
	</tr>
	

	<tr valign="top">		<td width="15%" align="right">目标端口：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="targetPorts" name = "targetPorts" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="targetPortSel" status="stat" id = "targetPPortOpt">
																		<option value="${targetPPortOpt}" id="deviceTypeid">${targetPPortOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="filteringRule.targetPort" id="targetPort" />
													</tr>
													
												</table></td>
										</tr>
		
													<td height="2px"></td>
										</tr>
										<tr>
											<td></td>
											<td>
												<table width="98%" border="0" cellspacing="0"
													cellpadding="0" style="align:right">
													<tr>
														<td align="left" style="padding-left:4px"><input
															type="button" value="" class="btnadd"
															onclick="showIpDlg('targetPortStart','targetPortEnd','div_targetPort','div_targetPortStart','div_targetPortEnd','dialog_targetPort');" /> <input type="button"
															value="" class="btndel" onclick="delOption('targetPorts','');" /> <br /></td>
													</tr>
												</table></td>
										</tr>
	
	<tr>
		<td class="td_detail_separator"></td>
	</tr>
	
	</table>




</div>










			
















			<!-- -------------------------------------------- -->
		










		</div>



	<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px;">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>

				<tr>
					<td align="right"><input type="button" class="btnyh"
						id="btnCancel" value="保   存" onclick="subData();" /> <input type="button" class="btnyh"
						id="btnCancel" value="取  消" onclick="window.history.back();" />
					</td>
				</tr>
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
			</table>









	</s:form>


	<!-- ext query from -->

	<div id="dialog-asset" title="关联设备">
	<s:hidden id="dlg-keyword-resource-id" value=""/>
		<table id="dlg-table-asset" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="overflow:auto">		

             <tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="asset-checkAll" name="chkAll-user"
					class="check-box-deviceName not-checked-deviceName" />
				</td>
				<td width="100%" align="center" class="biaoti">关联设备</td>
			</tr>
         
         	<s:iterator value='assetList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-asset" id="chk-${assetId}"
						value="${assetId}" /></td>
					<td width="300px"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-${assetId}')">${assetName}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>
<table width="80%" align="right">
				<tr>
		<td>
			<div id="barcon" name="barcon"></div>
	</td>
</tr>
</table>

	</div>


	<div id="dialog-eventCategory" title="事件类别">
	<s:hidden id="dlg-keyword-resource-id" value=""/>
		<table id="dlg-table-eventsCategory" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="overflow:auto">		
         <tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="eventsCategory-checkAll" name="chkAll-user"
					class="check-box-deviceName not-checked-deviceName" />
				</td>
				<td width="100%" align="center" class="biaoti">事件类别</td>
			</tr>
         	<s:iterator value='eventCategoryList' status='stat' >
         	   
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-eventCategory" id="chk-eventCategory-${eventcategorykey}"
						value="${eventcategorykey}" /></td>
					<td width="300px"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-eventCategory-${eventcategorykey}')">${eventcategoryValue}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>


	</div>




<div id="dialog-eventType" title="事件类型">
	
		<table id="dlg-table-eventType" width='96%' cellspacing='1'
			cellpadding='0' border='0'  class="tab2" style="overflow:auto">		

            <tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="eventsType-checkAll" name="chkAll-user"
					class="check-box-deviceName not-checked-deviceName" />
				</td>
				<td width="100%" align="center" class="biaoti">事件类别</td>
			</tr>
         	<s:iterator value='eventTypeTagList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
							name="chk-eventsType" id="chk-eventType-${eventtypetagId}"
						value="${eventtypetagId}" /></td>
					<td ><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-eventType-${eventtypetagId}')">${eventtypetagName}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>
<table width="80%" >
	<tr>
		<td colspan="2" style ="text-align:right;">
			<div id="eventTypeBarcon" name="eventTypeBarcon"></div>
	</td>
</tr>
</table>

	</div>
<!-- 源ip的dialog  -->
<div id="dialog_sourceIp" title="源IP">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			
		
		
		<tr>
		<td class="row" align= "right" width = "20%"><span>源IP：</span></td>
		<td align= "left" width = "15%">
		<select onchange="sourceAddfunc();" id = "sourceAdd">
		
		<option>等于</option>
		<option>不等于</option>
		<option>两者之间</option>
		</select>
	
		</td>
		
		<td >	<%--<input /> --%></td>
		<td width = "32%" align="left" >	<%--<span style = "display:none;" id = "sourceAddBetween"> -&nbsp;&nbsp;<input /></span></td>--%>
		</tr>
		
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
		
			
			<tr>
				<td width="25%"></td>
				<td><input type="text" id="sourceIpStart" name="sourceIpStart" size="20"
					width="100px" onblur="checkIp('sourceIpStart','div_sourceIpStart');" />
				</td>
				<td width="25%"><span id="div_sourceIpStart"></span></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<%--<span style = "display:none;" id = "sourceAddBetween">
			--%><tr style = "display:none;" id = "sourceAddBetween">
				<td width="25%"></td>
				<td><input type="text" id="sourceIpEnd" name="sourceIpEnd" size="20"
					width="100px" onblur="checkIp('sourceIpEnd','div_sourceIpEnd');" />
				</td>
				<td><span id="div_sourceIpEnd"></span></td>
			</tr><%--
			</span>
			--%><tr>
				<td></td>
				<td><span style="color:#363f46;" id="div_sourceIp"></span></td>
			</tr>
		</table>
	</div>

<!-- 目标ip的dialog  -->
<div id="dialog_targetIp" title="目标IP">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			
		
		
		<tr>
		<td class="row" align= "right" width = "20%"><span>目标IP：</span></td>
		<td align= "left" width = "15%">
		<select onchange="targetAddfunc();" id = "targetAdd">
		
		<option>等于</option>
		<option>不等于</option>
		<option>两者之间</option>
		</select>
	
		</td>
		
		<td >	<%--<input /> --%></td>
		<td width = "32%" align="left" >	<%--<span style = "display:none;" id = "targetAddBetween"> -&nbsp;&nbsp;<input /></span></td>--%>
		</tr>
		
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
		
			
			<tr>
				<td width="25%"></td>
				<td><input type="text" id="targetIpStart" name="targetIpStart" size="20"
					width="100px" onblur="checkIp('targetIpStart','div_targetIpStart');" />
				</td>
				<td width="25%"><span id="div_targetIpStart"></span></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<%--<span style = "display:none;" id = "targetAddBetween">
			--%><tr style = "display:none;" id = "targetAddBetween">
				<td width="25%"></td>
				<td><input type="text" id="targetIpEnd" name="targetIpEnd" size="20"
					width="100px" onblur="checkIp('targetIpEnd','div_targetIpEnd');" />
				</td>
				<td><span id="div_targetIpEnd"></span></td>
			</tr><%--
			</span>
			--%><tr>
				<td></td>
				<td><span style="color:#363f46;" id="div_targetIp"></span></td>
			</tr>
		</table>
	</div>

<!-- 源端口的dialog  -->
<div id="dialog_sourcePort" title="源端口">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
		
		<tr>
		<td class="row" align= "right" width = "20%"><span>源端口：</span></td>
		<td align= "left" width = "15%">
		<select onchange="sourcePortfunc();" id = "sourceP">
		
		<option>等于</option>
		<option>不等于</option>
		<option>两者之间</option>
		</select>
	
		</td>
		
		<td >	<%--<input /> --%></td>
		<td width = "32%" align="left" >	<%--<span style = "display:none;" id = "sourceAddBetween"> -&nbsp;&nbsp;<input /></span></td>--%>
		</tr>
		
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
		
			
			<tr>
				<td width="25%"></td>
				<td><input type="text" id="sourcePortStart" name="sourcePortStart" size="20"
					width="100px" onblur="checkPort('sourcePortStart','div_sourcePortStart');" />
				</td>
				<td width="25%"><span id="div_sourcePortStart"></span></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<%--<span style = "display:none;" id = "sourceAddBetween">
			--%><tr style = "display:none;" id = "sourcePortBetween">
				<td width="25%"></td>
				<td><input type="text" id="sourcePortEnd" name="sourcePortEnd" size="20"
					width="100px" onblur="checkPort('sourcePortEnd','div_sourcePortEnd');" />
				</td>
				<td><span id="div_sourcePortEnd"></span></td>
			</tr><%--
			</span>
			--%><tr>
				<td></td>
				<td><span style="color:#363f46;" id="div_sourcePort"></span></td>
			</tr>
		</table>
	</div>
<!-- 目标ip的dialog  -->
<div id="dialog_targetPort" title="目标IP">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			
		
		
		<tr>
		<td class="row" align= "right" width = "20%"><span>目标端口：</span></td>
		<td align= "left" width = "15%">
		<select onchange="targetPortfunc();" id = "targetP">
		
		<option>等于</option>
		<option>不等于</option>
		<option>两者之间</option>
		</select>
	
		</td>
		
		<td >	<%--<input /> --%></td>
		<td width = "32%" align="left" >	<%--<span style = "display:none;" id = "targetAddBetween"> -&nbsp;&nbsp;<input /></span></td>--%>
		</tr>
		
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
		
			
			<tr>
				<td width="25%"></td>
				<td><input type="text" id="targetPortStart" name="targetPortStart" size="20"
					width="100px" onblur="checkPort('targetPortStart','div_targetPortStart');" />
				</td>
				<td width="25%"><span id="div_targetPortStart"></span></td>
			</tr>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<%--<span style = "display:none;" id = "targetAddBetween">
			--%><tr style = "display:none;" id = "targetPortBetween">
				<td width="25%"></td>
				<td><input type="text" id="targetPortEnd" name="targetPortEnd" size="20"
					width="100px" onblur="checkPort('targetPortEnd','div_targetPortEnd');" />
				</td>
				<td><span id="div_targetPortEnd"></span></td>
			</tr><%--
			</span>
			--%><tr>
				<td></td>
				<td><span style="color:#363f46;" id="div_targetPort"></span></td>
			</tr>
		</table>
	</div>

</body>
</html>
