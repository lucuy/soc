<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.lowagie.text.Document"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
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
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>
<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
<script type='text/javascript' src="${ctx}/scripts/filterpage.js"></script>
<script language="javascript">
	jQuery(document).ready(function() {
		jQuery("#alertRuleForm").validationEngine();
		var changeScript ='${alertRule.changeScript}';
		if(''==changeScript){
			$("#changeScript").val(0);
		}
		$('#dialog-user').dialog({
			autoOpen : false,
			width : 450,
			height : 450,
			buttons : {
				"确定[Enter]" : function() {
					addUsers('userSelect', 'dialog-addUsers');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		//设备类型
		$('#deviceTypeDlg-open').dialog({
			autoOpen : false,
			width : 450,
			height : 450,
			buttons : {
				"确定[Enter]" : function() {
					addDeviceType('deviceTypeSelect', 'deviceTypeDlg-open');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#assetTypeDlg-open').dialog({
			autoOpen : false,
			width : 450,
			height : 450,
			buttons : {
				"确定[Enter]" : function() {
					addAssetType('assetTypeSelect', 'assetTypeDlg-open');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#deviceByNameDlg-open').dialog({
			autoOpen : false,
			width : 450,
			height : 450,
			buttons : {
				"确定[Enter]" : function() {
					addDeviceName('deviceNameSelect', 'deviceByNameDlg-open');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#scriptDlg-open').dialog({
			autoOpen : false,
			width : 450,
			height : 450,
			buttons : {
				"确定[Enter]" : function() {
					addScriptName('scriptFile','scriptName', 'scriptDlg-open');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		//初始化
		initCheckBox('assetTypeSelect','definition_NAME');
		initCheckBox('userSelect','relUserId');
		//
		initCheckBox('deviceNameSelect','ASSET_NAME1');
		initCheckBox('deviceTypeSelect','ID1');
		initCheckBox('scriptName','SCRIPTS');
		
		/* $('#dialog-assets').dialog({
			autoOpen : false,
			width : 400,
			height : 400,
			buttons : {
				"确定[Enter]" : function() {
					addAssets('assetSelect', 'dialog-addAssets');
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		}); */
		
		/* 设置邮件全选 */
		var falg = '${falg}';
		var tempmail = falg.substring(0, 11);
		if(tempmail != null && tempmail != ""){
		var flagMail=1;
		for(var i= 0 ;i < tempmail.length;i++){
		
		    if((tempmail[i] ==0)){
		    	flagMail = 0;
		    	break;
		    }
		}
			if(flagMail == 1){
				$('#chkAll-mail').attr("checked", true);
			}if(flagMail == 0){
				$('#chkAll-mail').attr("checked", false);
			}
		}
		
		/*设置短信全选*/
		var tempmsm = falg.substring(12, 23);
		if(tempmsm !=null && tempmsm !=""){
		var flagMsm = 1;
		for(var i= 0 ;i < tempmsm.length;i++){
		    if((tempmsm[i] ==0)){
		    	flagMsm = 0;
		    	break;
		    }
		}
			if(flagMsm==1){
			$('#chkAll-msm').attr("checked", true);
			}
			if(flagMsm == 0){
				$('#chkAll-msm').attr("checked", false);
			}
		}
		
		/*设置平台全选*/
		var tempplat = falg.substring(24, 35);
		if(tempplat != null && tempplat != ""){
		var flagPlat = 1;
		for(var i= 0 ;i < tempplat.length;i++){
		
		    if((tempplat[i] ==0)){
		    	flagPlat = 0;
		    	break;
		    }
		}
			if(flagPlat ==1){
			$('#chkAll-plat').attr("checked", true);
			}
			if(flagPlat == 0){
				$('#chkAll-plat').attr("checked", false);
			}
		}
		/*设置syslog全选*/
		var tempsys = falg.substring(36, 47);
		if(tempsys != null && tempsys != ""){
		var flagSys = 1;
		for(var i= 0 ;i < tempsys.length;i++){
		
		    if((tempsys[i] ==0)){
		    	flagSys = 0;
		    	break;
		    }
		}
			if(flagSys ==1){
			$('#chkAll-sys').attr("checked", true);
			}
			if(flagSys == 0){
				$('#chkAll-sys').attr("checked", false);
			}
		}
		//alert("");
		
		//alert("${empty alertRule.ruleId}") ;
		
	});

	function check_All(e, itemName)
    {    
        var aa = document.getElementsByName(itemName);    //获取全选复选框
        for (var i=0; i<aa.length; i++){
         aa[i].checked = e.checked;    //改变所有复选框的状态为全选复选框的状态
        }
    }
    function checkItem(e,allName){
    	var all = document.getElementsByName(allName)[0];
    	if(!e.checked){
            //没被选中全选复选框置为false;
            all.checked = false;
        } else {
            //选中，遍历数组
            var aa = document.getElementsByName(e.name);
            for (var i=0; i<aa.length; i++)
                //只要数组中有一个没有选中返回。假如所有的都是选中状态就将全选复选框选中;
             if(!aa[i].checked){ 
             	return;
             }
             all.checked = true;
        }
    }
	// 打开选择账户对话框
	function userDlg() {
		$("#dialog-user").dialog("open");
	}
	
	// 设备类型
	function deviceTypeDlg() {
		$("#deviceTypeDlg-open").dialog("open");
	}
	// 打开选择账户对话框
	function assetTypeDlg() {
		$("#assetTypeDlg-open").dialog("open");
	}
	// 打开选择账户对话框
	function deviceByNameDlg() {
		$("#deviceByNameDlg-open").dialog("open");
	}

	// 打开选择资产对话框
	/* function assetDlg() {
		$("#dialog-assets").dialog("open");

	} */
	//保存规则
	function subData() {
	
		$('#users').val('');
		if (document.getElementById("userSelect").options.length == 0) {
			alert("关联账户不能为空！");
			return;
		}
		$("#userSelect").children("option").each(function() {
			if ($(this).parent("select").size() > 0) {
				$('#users').val($('#users').val() + $(this).val() + ",");
			}
		});
		
		$('#assetTypeValue').val('');
		if (document.getElementById("assetTypeSelect").options.length == 0) {
			alert("事件类型不能为空！");
			return;
		}
		$("#assetTypeSelect").children("option").each(function() {
			if ($(this).parent("select").size() > 0) {
				$('#assetTypeValue').val($('#assetTypeValue').val() + $(this).val() + ",");
			}
		});
		
		$('#deviceByNameValue').val('');
		if (document.getElementById("deviceNameSelect").options.length == 0) {
			alert("设备名称不能为空！");
			return;
		}
		$("#deviceNameSelect").children("option").each(function() {
			if ($(this).parent("select").size() > 0) {
				$('#deviceByNameValue').val($('#deviceByNameValue').val() + $(this).val() + ",");
			}
		});
		
		$('#deviceByTypeValue').val('');
		if (document.getElementById("deviceTypeSelect").options.length == 0) {
			alert("事件类别不能为空！");
			return;
		}
		$("#deviceTypeSelect").children("option").each(function() {
			if ($(this).parent("select").size() > 0) {
				$('#deviceByTypeValue').val($('#deviceByTypeValue').val() + $(this).val() + ",");
			}
		});
		
		/* $('#assets').val('');
		if (document.getElementById("assetSelect").options.length == 0) {
			alert("关联资产不能为空！");
			return;
		}
		$("#assetSelect").children("option").each(function() {
			if ($(this).parent("select").size() > 0) {
				$('#assets').val($('#assets').val() + $(this).val() + ",");
			}
		}); */
		/* $("alertRuleForm").submit(); */

		if(${empty alertRule.ruleId}){
			var arr = new Array();
			arr = ['sendMail','sendMessage','sendSyslog','sendPlatformAlert'] ;
			for(var i=0;i<arr.length;i++){
				var h = $("input[type='checkbox'][class='check-box-mail'][name='"+arr[i]+"']" ) ;
				//alert(h.is(":checked"));
				if(!h.is(":checked")){
					$("#"+arr[i]).val(1);
				}
			}
		}

		$('#alertRuleForm').submit();
	}

	// Dialog 全选 - ConductPolicy
	//邮件等级全选
	function checkOr(ids)
	{   
	    var avalue = ids.value;
	
	     if(ids.checked==true)
	     {
	       $("input[type='checkbox'][value='"+avalue+"']").each(function() {
	       		$(this).attr("checked", true);
	       });
	     }
	     if(ids.checked == false)
	     {
	     	$("input[type='checkbox'][value='"+avalue+"']").each(function() {
	       		$(this).attr("checked", false);
	       });
	     }
	}
	
	//信息等级全选
	$("#chkAll-msm").live("click", function(event) {
		if ($("#chkAll-msm").hasClass('not-checked-msm')) {
			$("#chkAll-msm").removeClass('not-checked-msm');
			$(".check-box-msm").attr('checked', true);
		} else {
			$("#chkAll-msm").addClass('not-checked-msm');
			$(".check-box-msm").attr('checked', false);
		}
	});
	//平台等级
	$("#chkAll-plat").live("click", function(event) {
		if ($("#chkAll-plat").hasClass('not-checked-plat')) {
			$("#chkAll-plat").removeClass('not-checked-plat');
			$(".check-box-plat").attr('checked', true);
		} else {
			$("#chkAll-plat").addClass('not-checked-plat');
			$(".check-box-plat").attr('checked', false);
		}
	});
	//Syslog
	$("#chkAll-sys").live("click", function(event) {
		if ($("#chkAll-sys").hasClass('not-checked-sys')) {
			$("#chkAll-sys").removeClass('not-checked-sys');
			$(".check-box-sys").attr('checked', true);
		} else {
			$("#chkAll-sys").addClass('not-checked-sys');
			$(".check-box-sys").attr('checked', false);
		}
	});
	
	//关联用户全选
	$("#chkAll-user").live("click", function(event) {
		if ($("#chkAll-user").hasClass('not-checked-user')) {
			$("#chkAll-user").removeClass('not-checked-user');
			$(".check-box-user").attr('checked', true);
		} else {
			$("#chkAll-user").addClass('not-checked-user');
			$(".check-box-user").attr('checked', false);
		}
	});
	
	//设备类型
	$("#shebei-type").live("click", function(event) {
		if ($("#shebei-type").hasClass('not-checked-deviceType')) {
			$("#shebei-type").removeClass('not-checked-deviceType');
			$(".check-box-deviceType").attr('checked', true);
		} else {
			$("#shebei-type").addClass('not-checked-deviceType');
			$(".check-box-deviceType").attr('checked', false);
		}
	});
	
	//关联用户全选
	$("#shebei-name").live("click", function(event) {
		if ($("#shebei-name").hasClass('not-checked-deviceName')) {
			$("#shebei-name").removeClass('not-checked-deviceName');
			$(".check-box-deviceName").attr('checked', true);
		} else {
			$("#shebei-name").addClass('not-checked-deviceName');
			$(".check-box-deviceName").attr('checked', false);
		}
	});
	
	//关联用户全选
	$("#shijian-type").live("click", function(event) {
		if ($("#shijian-type").hasClass('not-checked-assetType')) {
			$("#shijian-type").removeClass('not-checked-assetType');
			$(".check-box-assetType").attr('checked', true);
		} else {
			$("#shijian-type").addClass('not-checked-assetType');
			$(".check-box-assetType").attr('checked', false);
		}
	});

	//关联资产全选
	/* $("#chkAll-asset").live("click", function(event) {
		if ($("#chkAll-asset").hasClass('not-checked-asset')) {
			$("#chkAll-asset").removeClass('not-checked-asset');
			$(".check-box-asset").attr('checked', true);
		} else {
			$("#chkAll-asset").addClass('not-checked-asset');
			$(".check-box-asset").attr('checked', false);
		}
	}); */
	
	

	//添加用户活资产确定按钮
	function addUsers(typeSelect, checkBoxDiv) {
		// 确定按钮
		$("#" + typeSelect).empty();
		$("#dialog-user input[name='relUserId'][type='checkbox']:checked").each(
				function() {
					var node = $(this).parent().next().children("a");
					$("#" + typeSelect).append(
							"<option id='user' value=\"" + $(this).val() + "\">" + node.text() + "</option>");
				});
		$("#dialog-user").dialog('close');
	}
	//告警动作确定按钮
	function addScriptName(scriptFile,typeSelect, checkBoxDiv) {
		// 确定按钮
		$("#" + typeSelect).empty();
		$("#scriptDlg-open input[name='SCRIPTS'][type='radio']:checked").each(
				function() {
					var node = $(this).parent().next().children("a");
					$("#"+scriptFile).val($(this).val());
					$("#" + typeSelect).val(node.text() );
				});
		$("#scriptDlg-open").dialog('close');
	}
    
   //设备类型
	function addDeviceType(typeSelect, checkBoxDiv) {
		// 确定按钮
		$("#" + typeSelect).empty();
		$("#deviceTypeDlg-open input[name='ID1'][type='checkbox']:checked").each(
		   
				function() {
					var node = $(this).parent().next().children("a");
					$("#" + typeSelect).append("<option id='deciceType' value=\"" + $(this).val() + "\">"+ node.text() + "</option>");
				});
		$("#deviceTypeDlg-open").dialog('close');
	}
	
	//设备mingcheng
	function addDeviceName(typeSelect, checkBoxDiv) {
		// 确定按钮
		$("#" + typeSelect).empty();
		$("#deviceByNameDlg-open input[name='ASSET_NAME1'][type='checkbox']:checked").each(
		   
				function() {
					var node = $(this).parent().next().children("a");
					$("#" + typeSelect).append("<option id='deciceNAme' value=\"" + $(this).val() + "\">"+ node.text() + "</option>");
				});
		$("#deviceByNameDlg-open").dialog('close');
	}
	
	//shijian类型
	function addAssetType(typeSelect, checkBoxDiv) {
		// 确定按钮
		$("#" + typeSelect).empty();
		$("#assetTypeDlg-open input[name='definition_NAME'][type='checkbox']:checked").each(
		   
				function() {
					var node = $(this).parent().next().children("a");
					$("#" + typeSelect).append("<option id='assetType' value=\"" + $(this).val() + "\">"+ node.text() + "</option>");
				});
		$("#assetTypeDlg-open").dialog('close');
	}

	$(function(){
		//前台分页
		goPage(1,10,'dlg-table-user','dlg-table-user-page');
		goPage(1,10,'dlg-table-eventsType','dlg-table-eventsType-page');
		goPage(1,10,'dlg-table-asset','dlg-table-asset-page');
		goPage(1,10,'dlg-table-script','dlg-table-script-page');
	});
	
	//告警方式 -- 设置发送标识
	function changeVal(obj){
		var inputObj = $("input[name="+obj+"]") ;
		var hiddenInput = $("#"+obj) ; 
		if(inputObj.is(":checked")){
			hiddenInput.val(0);
		}else{
			hiddenInput.val(1);
			
		}
		
	}
	//
	function changeScripts(){
		var check = document.getElementsByName("alertRule.changeScript");
		         if(check[0].checked){
		        	 $("#changeScript").val(1);
		        	 document.getElementById("delScript").disabled='';
		        	 document.getElementById("addScript").disabled='';
		        	// $("#delScript").attr('disabled', '');
				   	//$("#addScript").attr('disabled', '');
		         }else{
		        	 document.getElementById("delScript").disabled='none';
		        	 document.getElementById("addScript").disabled='none';
		        	// $("#delScript").attr('disabled', 'disabled');
				   		//$("#addScript").attr('disabled', 'disabled');
				   	 $("#changeScript").val(0);
				   	delScripts();
		         }
	}
	//删除告警动作
	function delScripts(){
		$("#scriptName").val("");
	}
	//新增告警动作
	function addScripts(){
		$("#scriptDlg-open").dialog('open');
	}

</script>

</head>

<body style="margin-top: 2px;margin-left:5px">
	<div class="framDiv" style="width:99.2%">
		<s:form action="updateRule.action" namespace="/alertRule"
			method="post" theme="simple" id="alertRuleForm" name="alertRuleForm">
			<s:hidden name="alertRule.ruleId" id="ruleId" />

			<s:hidden name="alertRule.sendMail" id="sendMail" />
			<s:hidden name="alertRule.sendMessage" id="sendMessage" />
			<s:hidden name="alertRule.sendSyslog" id="sendSyslog"></s:hidden>
			<s:hidden name="alertRule.sendPlatformAlert" id="sendPlatformAlert"></s:hidden>

			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="r2titler" colspan="2"><b>告警规则信息</b></td>
				</tr>

				<tr>
					<td width="40%" valign="top">

						<table width="90%" border="0" cellspacing="0" cellpadding="0"
							style="margin-left: 20px;" id="card1">

							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td class="td_detail_title"><b>基本信息</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font></td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td class="td_employee_detail">规则名称：</td>
											<td style="padding-left: 30px"><input type="text"
												id="ruleName" name="alertRule.ruleName"
												class="validate[required,custom[spechars],custom[validateLength]] text-input"
												maxlength="255"
												<c:if test="${alertRule.ruleName ne null}">disabled="disabled"</c:if>
												value="${alertRule.ruleName}" size="49" /> <span
												id="check_rolename_msg"></span></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td class="td_employee_detail">源地址：</td>
											<td style="padding-left: 30px"><input
												name="alertRule.sourceAddress" id="sourceAddress"
												type="text" size="49" value="${alertRule.sourceAddress}"
												class="validate[custom[settingIp]] text-input " /></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
										<tr>
											<td class="td_employee_detail">目的地址：</td>
											<td style="padding-left: 30px"><input
												name="alertRule.targetAddress" id="targetAddress"
												type="text" size="49" value="${alertRule.targetAddress}"
												class="validate[custom[settingIp]] text-input " /></td>
										</tr>
										<%-- <tr>
											<td class="td_employee_detail">时间范围：</td>
											<td style="padding-left: 30px"><input id="beginTime"
												onclick="WdatePicker()"
												name="alertRule.startTime"
												value="${alertRule.startTime}"
												class="Wdate" readonly="readonly"
												style="width:125px; height:19px;">&nbsp;至 <input
												id="endTime" onclick="WdatePicker()"
												name="alertRule.endTime"
												value="${alertRule.endTime}"
												class="Wdate" readonly="readonly"
												style="width:125px; height:19px;">
											</td>
										</tr> --%>
									</table></td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table></td>
					<td width="40%" valign="top">
						<!--  左侧table-->
						<table width="90%" border="0" cellspacing="0" cellpadding="0"
							style="margin-left: 20px;" id="card2">

							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td class="td_detail_title"><b>告警级别</b>&nbsp;&nbsp;&nbsp; <font
									id="message2"></font></td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td>
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="100%">事件等级：</td>
														<td></td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMsms[0] == '1' }">checked="checked"</c:if>
															name="rankMail" value="0" /> &nbsp;&nbsp;信息</td>
														<td  style="width: 100px;"><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMsms[1] == '1' }">checked="checked"</c:if>
															name="rankMail" value="1" /> &nbsp;&nbsp;轻微</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMsms[2] == '1' }">checked="checked"</c:if>
															name="rankMail" value="2" /> &nbsp;&nbsp;一般</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMsms[3] == '1' }">checked="checked"</c:if>
															name="rankMail" value="3" /> &nbsp;&nbsp;重要</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMsms[4] == '1' }">checked="checked"</c:if>
															name="rankMail" value="4"
															onclick="checkItem(this,'rankmails')" /> &nbsp;&nbsp;严重
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<!-- 告警方式 -->
													<tr>
														<td height="100%">告警方式：</td>
														<td></td>
														<td><input type="checkbox" class="check-box-mail"
															<c:choose>
													           <c:when test="${alertRule.sendMail==0}">
													              checked="checked"
													           </c:when>
													        </c:choose>
															name="sendMail" onclick="changeVal(this.name);" />

															&nbsp;发送邮件</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:choose>
													           <c:when test="${alertRule.sendSyslog==0}">
													              checked="checked"
													           </c:when>
													        </c:choose>
															name="sendSyslog" onclick="changeVal(this.name);" />
															&nbsp;发送syslog</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:choose>
													           <c:when test="${alertRule.sendMessage==0}">
													              checked="checked"
													           </c:when>
													        </c:choose>
															name="sendMessage" onclick="changeVal(this.name);" />
															&nbsp;发送短信</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:choose>
													           <c:when test="${alertRule.sendPlatformAlert==0}">
													              checked="checked"
													           </c:when>
													        </c:choose>
															name="sendPlatformAlert" onclick="changeVal(this.name);" />
															&nbsp;平台提示</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<!-- 告警方式 -->
													<tr>
														<td height="100%">告警动作：</td>
														<td></td>
														<td><input type="checkbox" class="check-box-mail"
															name="alertRule.changeScript" id="changeScript"
															value="${alertRule.changeScript}"
															<c:choose>
													           <c:when test="${alertRule.changeScript==1}">
													              checked="checked"
													           </c:when>
													        </c:choose>
															onclick="changeScripts();" /> &nbsp;开启/关闭</td>
														<td colspan="1"><input name="alertRule.scriptName"
															id="scriptName" style="width: 90%;height: 12px;" type="text" readonly="readonly"
															value="${alertRule.scriptName}" /> <input
															name="alertRule.scriptFile" id="scriptFile" value="${alertRule.scriptFile}" type="hidden" /></td>

														<td ><input type="button" class="btnadd"
															id="addScript" style="width: 90%" onclick="addScripts();"
																<c:choose>
													           <c:when test="${alertRule.changeScript!=1}">
													              disabled="disabled"
													           </c:when>
													        </c:choose>
															 /> </td>
															 <td >
															 <input type="button"
															class="btndel" id="delScript" style="width: 90%"
															onclick="delScripts();" <c:choose>
													           <c:when test="${alertRule.changeScript!=1}">
													              disabled="disabled"
													           </c:when>
													        </c:choose> /></td>
													</tr>

												</table></td>
										</tr>





										<%--<tr>
											<td>
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<!-- 邮件提示等级 -->
													<tr>
														<td height="100%">邮件提示：</td>
														 <td><input type="checkbox" class="check-box-mail"
												<c:if test="${rankListMails[0]=='1'}">checked="checked"</c:if>
												id="malis" name="rankMail" value="0"
												onclick="checkItem(this,'rankmails')" /> 0</td> 
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMails[1]=='1'}">checked="checked"</c:if>
															name="rankMail" value="1"
															onclick="checkItem(this,'rankmails')" /> 信息</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMails[2]=='1'}">checked="checked"</c:if>
															name="rankMail" value="2"
															onclick="checkItem(this,'rankmails')" /> 轻微</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMails[3]=='1'}">checked="checked"</c:if>
															name="rankMail" value="3"
															onclick="checkItem(this,'rankmails')" /> 一般</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMails[4]=='1'}">checked="checked"</c:if>
															name="rankMail" value="4"
															onclick="checkItem(this,'rankmails')" /> 重要</td>
														<td><input type="checkbox" class="check-box-mail"
															<c:if test="${rankListMails[5]=='1'}">checked="checked"</c:if>
															name="rankMail" value="5"
															onclick="checkItem(this,'rankmails')" /> 严重</td>
														 <td><input type="checkbox" class="check-box-mail"
												<c:if test="${rankListMails[6]=='1'}">checked="checked"</c:if>
												name="rankMail" value="6"
												onclick="checkItem(this,'rankmails')" /> 6</td>
											<td><input type="checkbox" class="check-box-mail"
												<c:if test="${rankListMails[7]=='1'}">checked="checked"</c:if>
												name="rankMail" value="7"
												onclick="checkItem(this,'rankmails')" /> 7</td>
											<td><input type="checkbox" class="check-box-mail"
												<c:if test="${rankListMails[8]=='1'}">checked="checked"</c:if>
												name="rankMail" value="8"
												onclick="checkItem(this,'rankmails')" /> 8</td>
											<td><input type="checkbox" class="check-box-mail"
												<c:if test="${rankListMails[9]=='1'}">checked="checked"</c:if>
												name="rankMail" value="9"
												onclick="checkItem(this,'rankmails')" /> 9</td>
											<td><input type="checkbox" class="check-box-mail"
												<c:if test="${rankListMails[10]=='1'}">checked="checked"</c:if>
												name="rankMail" value="10"
												onclick="checkItem(this,'rankmails')" /> 10</td> 
														<td><input type="checkbox" id="chkAll-mail"
															class="check-box-mail not-checked-mail" name="rankmails"
															onclick="check_All(this,'rankMail')" /> 全选</td>
													</tr>
													<tr>
														<td height="100%">短信提示：</td>
														 <td><input type="checkbox" class="check-box-msm"
												<c:if test="${rankListMsms[0]=='1'}">checked="checked"</c:if>
												name="rankMsm" value="0" /> 0</td> 
														<td><input type="checkbox" class="check-box-msm"
															<c:if test="${rankListMsms[1]=='1'}">checked="checked"</c:if>
															name="rankMsm" value="1" /> 信息</td>
														<td><input type="checkbox" class="check-box-msm"
															<c:if test="${rankListMsms[2]=='1'}">checked="checked"</c:if>
															name="rankMsm" value="2" /> 轻微</td>
														<td><input type="checkbox" class="check-box-msm"
															<c:if test="${rankListMsms[3]=='1'}">checked="checked"</c:if>
															name="rankMsm" value="3" /> 一般</td>
														<td><input type="checkbox" class="check-box-msm"
															<c:if test="${rankListMsms[4]=='1'}">checked="checked"</c:if>
															name="rankMsm" value="4" /> 重要</td>
														<td><input type="checkbox" class="check-box-msm"
															<c:if test="${rankListMsms[5]=='1'}">checked="checked"</c:if>
															name="rankMsm" value="5" /> 严重</td>
														 <td><input type="checkbox" class="check-box-msm"
												<c:if test="${rankListMsms[6]=='1'}">checked="checked"</c:if>
												name="rankMsm" value="6" /> 6</td>
											<td><input type="checkbox" class="check-box-msm"
												<c:if test="${rankListMsms[7]=='1'}">checked="checked"</c:if>
												name="rankMsm" value="7" /> 7</td>
											<td><input type="checkbox" class="check-box-msm"
												<c:if test="${rankListMsms[8]=='1'}">checked="checked"</c:if>
												name="rankMsm" value="8" /> 8</td>
											<td><input type="checkbox" class="check-box-msm"
												<c:if test="${rankListMsms[9]=='1'}">checked="checked"</c:if>
												name="rankMsm" value="9" /> 9</td>
											<td><input type="checkbox" class="check-box-msm"
												<c:if test="${rankListMsms[10]=='1'}">checked="checked"</c:if>
												name="rankMsm" value="10" /> 10</td> 
														<td><input type="checkbox" id="chkAll-msm"
															class="check-box-msm not-checked-msm"
															onclick="check_All(this,'rankMsm')" name="rank" /> 全选</td>
													</tr>
													<tr>
														<td height="100%">平台提示：</td>
														 <td><input type="checkbox" class="check-box-plat"
												<c:if test="${rankListPlats[0]=='1'}">checked="checked"</c:if>
												name="rankPlat" value="0" /> 0</td> 
														<td><input type="checkbox" class="check-box-plat"
															<c:if test="${rankListPlats[1]=='1'}">checked="checked"</c:if>
															name="rankPlat" value="1" /> 信息</td>
														<td><input type="checkbox" class="check-box-plat"
															<c:if test="${rankListPlats[2]=='1'}">checked="checked"</c:if>
															name="rankPlat" value="2" /> 轻微</td>
														<td><input type="checkbox" class="check-box-plat"
															<c:if test="${rankListPlats[3]=='1'}">checked="checked"</c:if>
															name="rankPlat" value="3" /> 一般</td>
														<td><input type="checkbox" class="check-box-plat"
															<c:if test="${rankListPlats[4]=='1'}">checked="checked"</c:if>
															name="rankPlat" value="4" /> 重要</td>
														<td><input type="checkbox" class="check-box-plat"
															<c:if test="${rankListPlats[5]=='1'}">checked="checked"</c:if>
															name="rankPlat" value="5" /> 严重</td>
														 <td><input type="checkbox" class="check-box-plat"
												<c:if test="${rankListPlats[6]=='1'}">checked="checked"</c:if>
												name="rankPlat" value="6" /> 6</td>
											<td><input type="checkbox" class="check-box-plat"
												<c:if test="${rankListPlats[7]=='1'}">checked="checked"</c:if>
												name="rankPlat" value="7" /> 7</td>
											<td><input type="checkbox" class="check-box-plat"
												<c:if test="${rankListPlats[8]=='1'}">checked="checked"</c:if>
												name="rankPlat" value="8" /> 8</td>
											<td><input type="checkbox" class="check-box-plat"
												<c:if test="${rankListPlats[9]=='1'}">checked="checked"</c:if>
												name="rankPlat" value="9" /> 9</td>
											<td><input type="checkbox" class="check-box-plat"
												<c:if test="${rankListPlats[10]=='1'}">checked="checked"</c:if>
												name="rankPlat" value="10" /> 10</td> 
														<td><input type="checkbox" id="chkAll-plat"
															class="check-box-plat not-checked-plat" name="rank"
															onclick="check_All(this,'rankPlat')" /> 全选</td>
													</tr>
													<tr>
														<td height="100%">snmp/syslog：</td>
														 <td><input type="checkbox" class="check-box-sys"
												<c:if test="${rankListSyss[0]=='1'}">checked="checked"</c:if>
												name="rankSys" value="0" /> 0</td> 
														<td><input type="checkbox" class="check-box-sys"
															<c:if test="${rankListSyss[1]=='1'}">checked="checked"</c:if>
															name="rankSys" value="1" /> 信息</td>
														<td><input type="checkbox" class="check-box-sys"
															<c:if test="${rankListSyss[2]=='1'}">checked="checked"</c:if>
															name="rankSys" value="2" /> 轻微</td>
														<td><input type="checkbox" class="check-box-sys"
															<c:if test="${rankListSyss[3]=='1'}">checked="checked"</c:if>
															name="rankSys" value="3" /> 一般</td>
														<td><input type="checkbox" class="check-box-sys"
															<c:if test="${rankListSyss[4]=='1'}">checked="checked"</c:if>
															name="rankSys" value="4" /> 重要</td>
														<td><input type="checkbox" class="check-box-sys"
															<c:if test="${rankListSyss[5]=='1'}">checked="checked"</c:if>
															name="rankSys" value="5" /> 严重</td>
														 <td><input type="checkbox" class="check-box-sys"
												<c:if test="${rankListSyss[6]=='1'}">checked="checked"</c:if>
												name="rankSys" value="6" /> 6</td>
											<td><input type="checkbox" class="check-box-sys"
												<c:if test="${rankListSyss[7]=='1'}">checked="checked"</c:if>
												name="rankSys" value="7" /> 7</td>
											<td><input type="checkbox" class="check-box-sys"
												<c:if test="${rankListSyss[8]=='1'}">checked="checked"</c:if>
												name="rankSys" value="8" /> 8</td>
											<td><input type="checkbox" class="check-box-sys"
												<c:if test="${rankListSyss[9]=='1'}">checked="checked"</c:if>
												name="rankSys" value="9" /> 9</td>
											<td><input type="checkbox" class="check-box-sys"
												<c:if test="${rankListSyss[10]=='1'}">checked="checked"</c:if>
												name="rankSys" value="10" /> 10</td> 
														<td><input type="checkbox" id="chkAll-sys"
															class="check-box-sys not-checked-sys" name="rank"
															onclick="check_All(this,'rankSys')" /> 全选</td>
													</tr>
												</table>
											</td>
										</tr>

									--%>
									</table></td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table></td>
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
								<td class="td_detail_title"><b>关联用户</b>&nbsp;&nbsp;&nbsp; <font
									id="message3"></font></td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td class="td_employee_detail">关联用户</td>
											<s:hidden name="rule" id="rule"></s:hidden>
											<td style="padding-left: 30px">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select id="userSelect" name="userSelect"
															style="width:275px" size="5" multiple="multiple">
																<s:iterator value="UserList" status="stat">
																	<option value="${USER_ID}" id="user">${USER_LOGIN_NAME}</option>
																</s:iterator>
														</select> <s:hidden name="users" id="users" /></td>
													</tr>

													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="userDlg();" /> <input type="button" value=""
															class="btndel"
															onclick="delOption('userSelect','relUserId');" /></td>
													</tr>
												</table></td>
										</tr>

									</table></td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table></td>
					<td width="40%" valign="top">
						<!--  左侧table-->
						<table width="90%" border="0" cellspacing="0" cellpadding="0"
							style="margin-left: 20px;" id="card4">

							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td class="td_detail_title"><b>事件类型</b>&nbsp;&nbsp;&nbsp; <font
									id="message4"></font></td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td class="td_employee_detail">事件类型</td>
											<td style="padding-left: 30px">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select id="assetTypeSelect"
															name="assetTypeSelect" style="width:275px" size="5"
															multiple="multiple">
																<s:iterator value="assetTypeList" status="stat">
																	<option value="${ID}" id="assetTypeId">${NAME}</option>
																</s:iterator>
														</select> <s:hidden name="assetTypeValue" id="assetTypeValue" /></td>
													</tr>

													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="assetTypeDlg();" /> <input type="button"
															value="" class="btndel"
															onclick="delOption('assetTypeSelect','definition_NAME');" />
														</td>
													</tr>
												</table></td>
										</tr>

									</table></td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td width="40%" valign="top">

						<table width="90%" border="0" cellspacing="0" cellpadding="0"
							style="margin-left: 20px;" id="card5">

							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td class="td_detail_title"><b>设备名称</b>&nbsp;&nbsp;&nbsp; <font
									id="message5"></font></td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td class="td_employee_detail">设备名称</td>
											<td style="padding-left: 30px"><table width="99%"
													border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td><select id="deviceNameSelect"
															name="deviceNameSelect" style="width:275px" size="5"
															multiple="multiple">
																<s:iterator value="deviceByNameList" status="stat">
																	<option value="${ASSET_ID}" id="deviceByNameid">${ASSET_NAME}</option>
																</s:iterator>
														</select> <s:hidden name="deviceByNameValue" id="deviceByNameValue" />
														</td>
													</tr>

													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="deviceByNameDlg();" /> <input type="button"
															value="" class="btndel"
															onclick="delOption('deviceNameSelect','ASSET_NAME1');" />
														</td>
													</tr>
												</table></td>
										</tr>

									</table></td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table></td>
					<td width="40%" valign="top">
						<!--  左侧table-->
						<table width="90%" border="0" cellspacing="0" cellpadding="0"
							style="margin-left: 20px;" id="card6">

							<tr>
								<td height="5px"></td>
							</tr>
							<tr>
								<td class="td_detail_title"><b>事件类别</b>&nbsp;&nbsp;&nbsp; <font
									id="message6"></font></td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td class="td_employee_detail">事件类别</td>
											<td style="padding-left: 30px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select id="deviceTypeSelect"
															name="deviceTypeSelect" style="width:275px" size="5"
															multiple="multiple">
																<s:iterator value="deviceTypeList" status="stat">
																	<option value="${ID}" id="deviceTypeid">${NAME}</option>
																</s:iterator>
																<s:hidden name="deviceByTypeValue"
																	id="deviceByTypeValue" />
														</select></td>
													</tr>

													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="deviceTypeDlg();" /> <input type="button"
															value="" class="btndel"
															onclick="delOption('deviceTypeSelect','ID1');" /></td>
													</tr>
												</table></td>
										</tr>

									</table></td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table></td>
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
			<td align="right" style="padding-right:6px"><input type="button"
				class="btnyh" style="font-family: 微软雅黑; font-size: 12px;"
				id="btnSaveCon" value="保存设置" onclick="subData();" />
			</td>
		</tr>
	</table>
	<!-- 用户选择的dialog -->
	<div id="dialog-user" title="账户选择">
		<table id="dlg-table-user" width='96%' cellspacing='1' cellpadding='0'
			border='0' align='center' class="tab2">
			<tr height="28" width="100%">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="chkAll-user" name="chkAll-user"
					class="check-box-user not-checked-user" /></td>
				<td align="center" class="biaoti" width="400px">账户名称</td>
			</tr>
			<c:set value="1" var="root" />
			<s:iterator value='allUserList' status='stat'>
				<tr>
					<td valign="middle" align="center" width="6%" class="biaocm"><input
						type="checkbox" name="relUserId" class="check-box-user"
						id="userId-${userId}" value="${userId}"
						onclick="event.cancelBubble=true;" /> <input type="hidden"
						value="0" /></td>

					<td valign="middle" class="td_t" width="400px"><a
						style='color: #555555' href="javascript:void(0);"
						onclick="addOk_a('${userLoginName}',${userId},'userSelect','dialog-user');">${userLoginName}</a>
						<input type="hidden" name="hid-dlgName-user"
						id="hid-dlgName-user-${userId}" value="${userLoginName}" /></td>

					<c:set value="${root+1}" var="root" />
				</tr>
			</s:iterator>
		</table>
		<table width="80%" align="right">
			<tr>
				<td>
					<div id="dlg-table-user-page" name="barcon"></div></td>
			</tr>
		</table>
	</div>

	<!-- 事件类别选择的dialog -->
	<div id="deviceTypeDlg-open" title="事件类别选择">
		<table id="dlg-table-eventsCategory" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2">
			<tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="shebei-type" name="shebei-type"
					class="check-box-deviceType not-checked-deviceType" /></td>
				<td align="center" class="biaoti" width="400px">事件类别</td>
			</tr>
			<c:set value="1" var="root" />
			<s:iterator value='deviceTypeAllList' status='stat'>
				<tr>
					<td valign="middle" align="center" class="biaocm"><input
						type="checkbox" name="ID1" class="check-box-deviceType"
						id="deshebeitype-${ID}" value="${ID}"
						onclick="event.cancelBubble=true;" /> <input type="hidden"
						value="0" /></td>

					<td valign="middle" class="td_t" width="400px"><a
						style='color: #555555' href="javascript:void(0);"
						onclick="addOk_a('${NAME}','${ID}','deviceTypeSelect','deviceTypeDlg-open');">${NAME}</a>
						<input type="hidden" name="hid-dlgName-deviceType"
						id="hid-dlgName-deviceType-${ID}" value="${NAME}" /></td>

					<c:set value="${root+1}" var="root" />
				</tr>
			</s:iterator>
		</table>
	</div>
	<!-- 事件类型的dialog -->
	<div id="assetTypeDlg-open" title="事件类型选择">
		<table id="dlg-table-eventsType" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2">
			<tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="shijian-type" name="chkAll-user"
					class="check-box-assetType not-checked-assetType" /></td>
				<td align="center" class="biaoti" width="400px">事件类型</td>
			</tr>
			<c:set value="1" var="root" />
			<s:iterator value='assetTypeAllList' status='stat'>
				<tr>
					<td valign="middle" align="center" class="biaocm" width="6%"><input
						type="checkbox" name="definition_NAME" class="check-box-assetType"
						id="deviceType-${ID}" value="${ID}"
						onclick="event.cancelBubble=true;" /> <input type="hidden"
						value="0" /></td>

					<td valign="middle" class="td_t" width="400px"><a
						style='color: #555555' href="javascript:void(0);"
						onclick="addOk_a('${NAME}',${ID},'assetTypeSelect','assetTypeDlg-open');">${NAME}</a>
						<input type="hidden" name="hid-dlgName-user"
						id="hid-dlgName-user-${ID}" value="${NAME}" /></td>

					<c:set value="${root+1}" var="root" />
				</tr>
			</s:iterator>
		</table>
		<table width="80%" align="right">
			<tr>
				<td>
					<div id="dlg-table-eventsType-page" name="barcon"></div></td>
			</tr>
		</table>
	</div>
	<!-- 设备名称选择的dialog -->
	<div id="deviceByNameDlg-open" title="设备名称选择">
		<table id="dlg-table-asset" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2">
			<tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="shebei-name" name="chkAll-user"
					class="check-box-deviceName not-checked-deviceName" /></td>
				<td width="400px" align="center" class="biaoti">设备名称</td>
			</tr>
			<c:set value="1" var="root" />

			<s:iterator value='assetListDiv' status='stat'>
				<tr>
					<td valign="middle" align="center" class="biaocm" width="6%"><input
						type="checkbox" name="ASSET_NAME1" class="check-box-deviceName"
						id="deshebeiName-${assetId}" value="${assetId}"
						onclick="event.cancelBubble=true;" /> <input type="hidden"
						value="0" /></td>

					<td valign="middle" class="td_t" width="400px"><a
						style='color: #555555' href="javascript:void(0);"
						onclick="addOk_a('${assetName}',${assetId},'deviceNameSelect','deviceByNameDlg-open');">${assetName}</a>
						<input type="hidden" name="hid-dlgName-user"
						id="hid-dlgName-user-${assetId}" value="${assetName}" /></td>

					<c:set value="${root+1}" var="root" />
				</tr>
			</s:iterator>
		</table>
		<table width="80%" align="right">
			<tr>
				<td>
					<div id="dlg-table-asset-page" name="barcon"></div></td>
			</tr>
		</table>
	</div>

	<!-- 告警动作选择的dialog -->
	<div id="scriptDlg-open" title="告警动作选择">
		<table id="dlg-table-script" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2">
			<tr height="28">

				<td width="600px;" align="center" class="biaoti" colspan="2">告警动作</td>
			</tr>
			<c:set value="1" var="root" />

			<s:iterator value='policyList' status='stat'>
				<tr>
					<td valign="middle" align="center" class="biaocm" width="6%"><input
						type="radio" name="SCRIPTS" class="check-box-deviceName"
						id="scriptName-${id}" value="${relPolicyName}"
						onclick="event.cancelBubble=true;" /> <input type="hidden"
						value="0" /></td>

					<td valign="middle" class="td_t" width="400px"><a
						style='color: #555555' href="javascript:void(0);"
						onclick="addRadio_b('${policyName}','${relPolicyName}','scriptName','scriptDlg-open','scriptFile');">${policyName}</a>
						<input type="hidden" name="hid-dlgName-user"
						id="hid-dlgName-user-${relPolicyName}" value="${policyName}" /></td>

					<c:set value="${root+1}" var="root" />
				</tr>
			</s:iterator>
		</table>
		<table width="80%" align="right">
			<tr>
				<td>
					<div id="dlg-table-script-page" name="barcon"></div></td>
			</tr>
		</table>
	</div>

</body>
</html>