<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>
<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
<script language="javascript">
	jQuery(document).ready(function() {
		jQuery("#userForm").validationEngine();
		$("#userLoginName").focus();
		//初始化角色对话框
		$('#dialog-addRole').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('roleSelect','dialog-addRole');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		//初始化密码策略对话框
		$('#dialog-passwordPolicy').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('passwordPolicySelect','dialog-passwordPolicy');
					modifyPwd();
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		//初始化时间策略对话框
		$('#dialog-timePolicy').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('timePolicySelect','dialog-timePolicy');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		$('#dialog-assets').dialog({
			autoOpen: false,
			width: 400,
			height:400,
			buttons: {
				"确定[Enter]": function() {
					addAssets('assetsSelect','dialog-addAssets');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		//初始化地址策略对话框
		$('#dialog-addressPolicy').dialog({
			autoOpen: false,
			width: 300,
			height:350,
			buttons: {
				"确定[Enter]": function() {
					addOk('addressPolicySelect','dialog-addressPolicy');
				},
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		//初始化dialog
		initCheckBox('roleSelect','roleId');
		initCheckBox('passwordPolicySelect','chk-passwordpolicy');
		initCheckBox('timePolicySelect','chk-timePolicy');
		initCheckBox('addressPolicySelect','chk-addresspolicy');
		 initCheckBox('assetsSelect','ids-assetGroup');
	});
	function assetDlg() {
		$("#dialog-assets").dialog("open");
		
	}
	function checkedAssetGroup(objId) {
		$('#assetGroup-'+objId).attr('checked',!$('#assetGroup-'+objId).attr('checked'));
	}
	function addAllAssetGroups()
	{
	   add_AllAssetGroups('assetsSelect','dialog-addAssets');
	   
	   $("#assetsSelect").append("<option id='assetGroup' value=\"-1\">已选择所有资产组</option>"); 
	   
	   $("#dialog-assets").dialog("close");
	}
	function add_AllAssetGroups(typeSelect, checkBoxDiv) {
		//确定按钮
		$("#"+typeSelect).empty();
		
		$("#dialog-assets input[name='ids-assetGroup'][type='radio']:checked").each(
		   function() {
				 var node = $(this).parent().next().children("a");
		         $("#"+typeSelect).append("<option id='assetGroup' value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
	        }
		);
		$("#dialog-assets").dialog('close');
	}
	function addAssets(typeSelect, checkBoxDiv) {
		// 确定按钮
		$("#"+typeSelect).empty();
	
		
		$("#dialog-assets input[name='ids-assetGroup'][type='radio']:checked").each(
		   function() {
				 var node = $(this).parent().next().children("a");
		         $("#"+typeSelect).append("<option id='assetGroup' value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
	        }
		);
		$("#dialog-assets").dialog('close');
	}
	//修改状态
	function updateUserStatus(userStatus) {
		var userId = $("#userId").val();

		var url = "${ctx}/user/updateUserStatus.action?method=updateUserStatus";

		$.ajax({
			type : "post",
			url : url,
			dataType : "text",
			data : "&userId=" + userId + "&status=" + userStatus,
			success : function(result) {

				var res = String($.trim(result));

				if (res = "success") {
					if (userStatus == 1) {
						$("#status").val("激活");
						$("#unLock").attr('disabled', 'disabled');
						$("#lock").attr('disabled', '');

					} else if (userStatus == 0) {
						$("#status").val("锁定");
						$("#lock").attr('disabled', 'disabled');
						$("#unLock").attr('disabled', '');
					}
				} else {
					alert("更新状态有异常，请您重试一下！");
				}
			}
		});
	}

	//保存
	function subData() {
		
		if($('#userMemo').val().length>255){
			alert("描述长度不能大于255");
			$('#userMemo').val('');
			$('#userMemo').focus();
			return ;
		}
		
		if ($("#password").val() != "") {
			if ($("#password2").val() == "") {
				alert("两次密码不一致");
				return;
			}
		}
		else if($('#userId').val() == 0) {
			alert("密码不能为空");
			return;
		}

		
		if ($("#flag").val()=="true") {
			if (existFlag) {
				alert('用户名已占用，请重新设置！');
				$('#employee.empLoginName').focus();
				return;
			}
			
			//取策略列表
			$('#passwordPolicy').val('');
			$("#passwordPolicySelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$('#passwordPolicy').val(
									$('#passwordPolicy').val() + $(this).val()
											+ ",");
						}
					});
					
			$('#timePolicy').val('');
			$("#timePolicySelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$('#timePolicy').val(
									$('#timePolicy').val() + $(this).val()
											+ ",");
						}
					});
					
			$('#addressPolicy').val('');
			$("#addressPolicySelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {
							$('#addressPolicy').val(
									$('#addressPolicy').val() + $(this).val()
											+ ",");
						}
					});
					
			$("#roleSelect").children("option").each(function() {
				if ($(this).parent("select").size() > 0) {
					$('#roles').val($('#roles').val() + $(this).val() + ",");
				}
			});
			
			$("#assetsSelect").children("option").each(
					function() {
						if ($(this).parent("select").size() > 0) {		
							if($(this).val() != '') {	
								 if($(this).attr('id') == 'assetGroup') {
									$('#assetGroups').val($('#assetGroups').val() + $(this).val() );
								}
							}
						}
					});

			//动态验证码功能
			var sVal = $("#savedSecret").val();
			$("#relUserSavedSecret").val(sVal);
			
			$("#savedSecret").removeAttr("disabled");
			
			
			$("#userForm").submit();
		} else {
			$("#password").focus();
			return;
		}
	}
	// 删除关联资产
	function delAssetOption() {
	   /* $('input[type="checkbox"][name="ids-assetGroup"]:checked').each(function(){
			     $(this).attr("checked",false);
			});
		$("#assetsSelect").empty();*/
		//$("select[name=assetsSelect]").find("option:selected").remove();
		
	if($("#assetsSelect").get(0).selectedIndex == -1) {
		if(confirm('确认删除所有？')) {
			
			$('input[type="radio"][name="ids-assetGroup"]:checked').each(function(){
			     $(this).attr("checked",false);
			});
			$("#assetsSelect").empty();
		}
	} else {
		$("#assetsSelect").children("option").each(
			function() {
					if($(this).attr("selected") == true) {
					
					if($(this).attr("id") == 'assetGroup') {
						$("#assetGroup-" + $(this).val()).attr("checked", false);
					}
					
				}
			});
		
		$("#assetsSelect").find("option:selected").remove();
	}

	}
	//弹出选择角色对话框 
	function addRoleDlg() {
	
		$('#dialog-addRole').dialog('open');
		$('#dialog-addRole').focus();
	}
	
	//弹出选择策略对话框		
	function addPolicyDlg(type) {
	
		//密码策略
		if (type == 0) {
			$('#dialog-passwordPolicy').dialog('open');
			$('#dialog-passwordPolicy').focus();
		}
		
		//时间策略
		if (type == 1) {
			$('#dialog-timePolicy').dialog('open');
			$('#dialog-timePolicy').focus();
		}
		
		//地址策略
		if (type == 2) {
			$('#dialog-addressPolicy').dialog('open');
			$('#dialog-addressPolicy').focus();
		}
	}

	//密码策略进行检查
	function checkPolicy() {
		$("#message").empty();
		$("#icon").empty();
		var pps = document.getElementById("passwordPolicySelect");
	
		var len = pps.options.length;
		var pids = "";

		if(len > 0) {
			for (var i = 0; i < len; i++)
			{
		    	if (i==len-1) 
		    	{
		        	pids = pids + pps.options[i].value;
		    	} 
		    	else
		    	{
		    		pids = pids + pps.options[i].value + ",";
		    	}
			}
			
			var password = $("#password").val();
			$("#message").empty();
			$("#icon").empty();
			
			if($.trim(password)!=""){
				$.ajax({
					type : "post",
					url : "checkPwdPolicy.action",
					dataType : "text",
					data : "&userId=${user.userId}&passwordPolicyIds=" + pids + "&newPassword=" + password,
					success : function(data) {
						$("#message").empty();
						$("#icon").empty();
						if(data!=""){
							//$("#password").val('');
							$("#password2").val('');
							var fontNode=$("<font color='red'>"+data+"</font>");
							$("#message").append(fontNode);
							$("#flag").val("false");
							//$("#password").focus();
							return;
						}else{
							var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
							$("#icon").append(img).show("slow");
							//$("#confirmPassword").focus();
							$("#flag").val("true");
						}
					}
				});
			} else {
				$("#message").empty();
				$("#icon").empty();
				return;
			}
		} else {
			if($("#password").val() == '') {
				
			} else {
				var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
				$("#icon").append(img).show("slow");
				$("#confirmPassword").focus();
				$("#flag").val("true");
			}
			
		}
	}
	
	//查看字符串的长度
	var jmz = {};
	jmz.GetLength = function(str) {
	    ///<summary>获得字符串实际长度，中文2，英文1</summary>
	    ///<param name="str">要获得长度的字符串</param>
	    var realLength = 0, len = str.length, charCode = -1;
	    for (var i = 0; i < len; i++) {
	        charCode = str.charCodeAt(i);
	        if (charCode >= 0 && charCode <= 128) realLength += 1;
	        else realLength += 2;
	    }
	    return realLength;
	};

	//检测用户名是否存在
	var existFlag = false;
	function queryByUserLoginName(userLoginName) {
		//alert(userLoginName) ; 
		var userId = $('#userId').val();
		 if(userLoginName != ""){
		if(userId == 0 && (jmz.GetLength(userLoginName) <= 50)) {
			$.ajax({
				type : "post",
				url : "${ctx}/user/queryByUserLoginName.action",
				dataType : "text",
				data : "&userLoginName=" + userLoginName,
				success : function(result) {
					if (result == 'true') {
						$("#check_loginname_msg").addClass("spanred");
						$("#check_loginname_msg").html("该名称已占用");
						existFlag = true;
					} else {
						var img = $("<img src='${ctx}/images/ok.png' height='20px' width='20px'/>");
					//	$("#check_loginname_msg").append(img).show("slow");李长红修改。
						$("#check_loginname_msg").html(img).show("slow");
						existFlag = false;
					}
				}
			});
		}else{
			$("#check_loginname_msg").html("");
		}
	}
	}
	function hs_authtype(value) {
		if (value == 0) {
			$('#tr_password_1').show();
			$('#tr_password_2').show();
			$('#tr_password_3').show();
			$('#tr_password_4').show();
			$('#tr_password_5').show();
			$('#tr_password_6').show();
			$('#yincang').hide();
		} else if (value == 2 || value == 3) {
			$('#tr_password_1').hide();
			$('#tr_password_2').hide();
			$('#tr_password_3').hide();
			$('#tr_password_4').hide();
			$('#tr_password_5').hide();
			$('#tr_password_6').hide();
			$('#yincang').show();
		}
	}
	
	function limit(check) {
		if ($(check).attr("checked")) {
			$('#islimit1').show();
			$('#islimit2').show();
		} else {
			$('#islimit1').hide();
			$('#islimit2').hide();
		}
	}
	
	function modifyPwd()
	{
		$("#password").val('');
		$("#password2").val('');
		$("#message").empty();
		var data = "请修改密码";
		var fontNode=$("<font color='red'>"+data+"</font>");
		$("#message").append(fontNode);
		$("#password").focus();
		$("#flag").val("false");
	}
	$("#chkAll-assetGroup").live("click",function(event) {
		if($("#chkAll-assetGroup").hasClass('not-checked-assetGroup')) {
			$("#chkAll-assetGroup").removeClass('not-checked-assetGroup');
			$(".check-box-assetGroup").attr('checked',true);
		} else {
			$("#chkAll-assetGroup").addClass('not-checked-assetGroup');
			$(".check-box-assetGroup").attr('checked',false);
		}
	});
	
	//生成动态码
	function genSecretTest(){
		var url = "${ctx}/user/getSecretKey.action";
		$.ajax({
			type : "post",
			url : url,
			success : function(secret) {
				if (secret != "") {
					//alert(secret);
					document.getElementById("savedSecret").value=secret;
				}
			}
		});
		
	}
	function initCheckBox1(typeSelect, checkName) {
	    //alert("123");
		$('#'+typeSelect).children("option").each(function() {
			//var i = $(this).val();
			//alert("123");
		
			  if($(this).attr("id")=="assetGroup")
			 {
			    //alert("12");
			    var i =$(this).val();
			    $('input[type="checkbox"][name="'+checkName+'"]').each(function() {
				if($(this).val()==i) {
					$(this).attr("checked",true);
					return false;
				}
			  });
			}
		});
	}
	
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="updateUser.action" namespace="/user" method="post"
		theme="simple" id="userForm" name="userForm">
		<s:hidden name="user.userId" id="userId" />
		<s:hidden name="time" />
		<s:hidden name="user.userStatus" />
		<s:hidden name="order" id="order" />
		<s:hidden name="flag" id="flag" value="true" />
		<s:hidden name="roles" id="roles"></s:hidden>
		<s:hidden name="passwordPolicy" id="passwordPolicy" />
		<s:hidden name="timePolicy" id="timePolicy" />
		<s:hidden name="addressPolicy" id="addressPolicy" />
		<!-- <s:hidden name="user.userSavedSecret" id="relUserSavedSecret" /> -->
		<s:token></s:token>
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
								<td class='r2titler' colspan='2'><b>登录信息</b>
								</td>
							</tr>

							<tr>
								<td>
									<!-- 用户信息内容 -->
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td width="20%"></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 姓名 -->
										<tr>
											<td align="right"><span class="spanred">*</span>用户名：</td>
											<td style="padding-left:8px;" align="left" width="80%"><input
												name="user.userLoginName" value="${user.userLoginName}"
												type="text" size="30" id="userLoginName"
												class="validate[required,custom[validateLength],custom[spechars]] text-input "
												maxlength="255" style="width: 70%"
												onblur="queryByUserLoginName(this.value);"
												<c:if test="${user != null}">readonly="readonly"</c:if> />
												<span id="check_loginname_msg"></span>
											</td>

										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right"><span class="spanred">*</span>真实姓名：</td>
											<td style="padding-left:8px;" align="left"><input
												class="validate[required,custom[validateLength],custom[spechars]] text-input "
												name="user.userRealName" value="${user.userRealName}"
												type="text" size="30" id="userRealName" maxlength="255"
												style="width: 70%" />
											</td>
										</tr>


										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 动态码生产 -->
										<tr id="tr_password_1">
											<td align="right">动态码：</td>

											<td width="50%" align="left" style="padding-left:8px"><input
												type="text" name="user.userSavedSecret" id="savedSecret" readonly="readOnly" disabled="disabled"
												size="30" style="width:70%" maxlength="22" autocomplete="off"/>
												</td>
											<td align="left"></td>
										</tr>
										<tr></tr>
										<tr><td align="right"></td><td width="50%" align="left" style="padding-left:8px">
												<input type="button" value="生成动态码" onclick="genSecretTest()">
												</td>
											</td>
										</tr>


										<!-- 密码 -->
										<tr id="tr_password_1">
											<td align="right"><span class="spanred">*</span>密码：</td>

											<td width="50%" align="left" style="padding-left:8px"><input
												type="password" name="user.userPassword" id="password"
												size="30" class="validate[required,minSize[6],maxSize[22]]"
												style="width:70%" onblur="checkPolicy()" maxlength="22" autocomplete="off"/> <span id="icon"></span>
												<%--<span class="hand" title="${requestScope.pwdPolicyInfo}"
												onclick="if('${requestScope.pwdPolicyInfo}'!=''){alert('${requestScope.pwdPolicyInfo}');}else{alert('强度无特别限制！');}"><em>密码强度</em>
											</span>
											--%></td>
											<td align="left"></td>
										</tr>

										<tr id="tr_password_2">
											<td></td>
											<td>
												<div id="message"></div>
											</td>
										</tr>
										<!-- 空行 -->
										<tr id="tr_password_3">
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 确认密码 -->
										<tr id="tr_password_4">
											<td align="right"><span class="spanred">*</span>确认密码：</td>

											<td style="padding-left:8px" align="left"><input
												type="password" name="password2" id="password2" size="30"
												style="width:70%" class="validate[equals[password]]/>" maxlength="22" autocomplete="off" />
											</td>
										</tr>
										<tr id="tr_password_5">
											<td height="2px"></td>
										</tr>
										<!-- 下次登录修改密码 -->
										<tr id="tr_password_6">
											<td></td>

											<td style="padding-left:8px"><input type="checkbox"
												name="user.userChangePassword" value="1"
												<c:if test="${user.userChangePassword==1}">checked="checked"</c:if> />
												下次登录修改密码</td>
										</tr>

									</table>
								</td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<!-- 认证方式 -->
							<%-- <tr>
								<td>
									<!-- 认证方式及导入证书 -->
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<!-- 认证方式 -->
										<tr>
											<td width="10%" align="right">认证方式：</td>
											<td width="40%" style="padding-left:10px"><select style="width: 71%;"
												name="user.userAuthType" id="user.userAuthType"
												onchange="hs_authtype(this.value)">
													<option value="0"
														<c:if test="${user.userAuthType == 0}"> selected</c:if>>
														密码认证</option>
													<option value="2"
														<c:if test="${user.userAuthType == 2}"> selected</c:if>>
														Radius认证</option>
													<option value="3"
														<c:if test="${user.userAuthType == 3}"> selected</c:if>>
														AD域认证</option>
											</select></td>
										</tr>
									</table></td>
							</tr>



							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>


							<!-- 导入证书 -->
							<tr>
								<td>
									<!-- 导入证书内容 -->
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<!-- 导入证书 -->
										<tr>
											<td width="18%" align="right">导入证书：</td>

											<td width="40%" style="padding-left:10px" align="left" ><input type="text" size="30" style="width:80%"/></td>
											<td width="32%"  align="left"><input  type="button"  value="选择文件"
												class="btdao" /></td>
										</tr>

									</table></td>
							</tr>--%>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div>
								</td>
							</tr>

							<tr>
								<td height="10px"></td>
							</tr>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<!-- 角色 -->
							<tr>
								<td>
									<!-- 角色内容 -->
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<!-- 角色 -->
										<tr valign="top">
											<td width="20%" align="right"><span class="spanred">*</span>角色：
												<s:hidden name="role" id="role"></s:hidden>
											</td>
											<td width="40%" style="padding-left:10px" align="left"><select
												style="width: 100%;" name='roles'>
													<s:iterator value='allRoleList' status='stat'>
														<c:if test="${roleId != 33 }"><option value='${roleId}'
															<c:if test="${roleId eq 32 }"> selected="selected"</c:if>>
															${roleName}</option>
														
															</c:if>
															

													</s:iterator>
											</select>
											</td>
											<td style="padding-left:5px" align="left" width="60%"></td>
										</tr>
									</table>
								</td>
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
								<td colspan="2">
									<div class="xuxian"></div>
								</td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td height="4px"></td>
							</tr>

							<!-- 灰色分割线 -->
							<tr style="display: none;">
								<td colspan="2">
									<hr class="hr_g" />
								</td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td height="3"></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<tr valign="top">



											<td style="padding-left:17px" align="right"><span
												class="spanred">*</span>资产组：&nbsp;</td>
											<td align="left" style="padding-left:4px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select name="assetsSelect" id="assetsSelect"
															style="width: 275px" size="5" multiple="multiple">



																<s:iterator value="assetGroupList" status="stat">
																	<option value="${relid}" id="assetGroup">
																		${relname}</option>
																</s:iterator>

														</select> <s:hidden name="assetType" id="assetType" /> <s:hidden
																name="assetGroups" id="assetGroups" /></td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="assetDlg();" /> <input
															type="button" value="" class="btndel"
															onclick="delAssetOption();" /></td>
													</tr>
												</table></td>
										</tr>
									</table></td>
							</tr>
							<!-- 状态 -->
							<%--<c:if test="${user.userId==null}">
								<tr>
									<td>
										<!-- 状态内容 -->
										<table width="99%" border="0" align="center" cellspacing="0"
											cellpadding="0">
											<!-- 状态 -->
											<tr>
												<td width="25%" align="right">状态：</td>

												<td width="75%">
													<table width="99%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td><input type="text" id="status" value="激活"
																size="40" style="width: 270px" disabled="disabled">
															</td>
														</tr>
														<tr>
															<td height="8px"></td>
														</tr>
														<tr>
															<td><input id="lock" type="button" class="btnyh2"
																style="padding-top:2px;" value="锁定" disabled='disabled' />
																<input id="unLock" type="button" class="btnyh2"
																style="padding-top:2px;" value="激活" disabled="disabled" />
																<input type="button" class="btn_detail_add_del"
																value="注销" style="display: none;" /></td>
														</tr>
													</table></td>
											</tr>
										</table></td>
								</tr>
							</c:if>
							<c:if test="${user.userId!=null}">
								<tr>
									<td>
										<!-- 状态内容 -->
										<table width="99%" align="center" border="0" cellspacing="0"
											cellpadding="0">
											<!-- 状态 -->
											<tr>
												<td width="25%" align="right">状态：</td>

												<td width="75%">
													<table width="99%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td><input type="text" id="status"
																<c:if test="${user.userStatus==1}">value="激活"</c:if>
																<c:if test="${user.userStatus==0}">value="锁定"</c:if>
																size="40" style="width: 270px" disabled="disabled">
															</td>
														</tr>
														<tr>
															<td height="8px"></td>
														</tr>
														<tr>
															<td><input id="lock" type="button" class="btnyh2"
																style="padding-top:2px;" value="锁定"
																onclick="updateUserStatus(0)"
																<c:if test="${user.userStatus==0}">disabled='disabled'</c:if> />
																<input id="unLock" type="button" class="btnyh2"
																style="padding-top:2px;" value="激活"
																onclick="updateUserStatus(1)"
																<c:if test="${user.userStatus==1}">disabled='disabled'</c:if> />
																<input type="button" class="btn_detail_add_del"
																value="注销" style="display: none;" /></td>
														</tr>
													</table></td>
											</tr>
										</table></td>
								</tr>
							</c:if>
							--%>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td height="83px"></td>
							</tr>
							<!-- 空行 -->
							<tr id="yincang" style="display:none">
								<td height="80px"></td>
							</tr>

						</table>
					</div>
				</td>

				<!-- 左右中间间隔 -->
				<td width="5px"></td>

				<!-- 右侧table -->
				<td valign="top">
					<!--  左侧table-->
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">


							<!-- 登录信息 -->
							<tr>
								<td class='r2titler'><b>用户信息</b>
								</td>
							</tr>
							<tr>
								<td>
									<!-- 用户信息内容 -->
									<table width="99%" border="0" cellspacing="0" cellpadding="0">

										<!-- 空行 -->
										<tr>
											<td width="15%"></td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 邮件地址 -->
										<tr>
											<td align="right"><span class="spanred">*</span>邮箱地址：</td>

											<td width="60%" style="padding-left:8px" align="left"><input
												name="user.userEmail" value="${user.userEmail}" type="text"
												size="30" id="useremail"
												class="validate[required,custom[email]] text-input "
												maxlength="255" style="width:67%" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td align="right" class="td_detail_separator"></td>
										</tr>
										<!-- 手机号码 -->
										<tr>
											<td align="right"><span class="spanred">*</span>手机号码：</td>

											<td style="padding-left:8px" align="left"><input
												name="user.userMobile" value="${user.userMobile}"
												type="text" size="30" id="userMobile"
												class="validate[required,custom[phone]] text-input "
												maxlength="255" style="width:67%" />
											</td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!-- 固定电话 -->
										<tr>
											<td align="right">固定电话：</td>

											<td style="padding-left:8px" align="left"><input
												name="user.userTelephone" size="30"
												class="validate[custom[Talphone]] text-input "
												id="usertelephone" maxlength="255" style="width:67%" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!-- 通信地址 -->
										<tr>
											<td align="right">通信地址：</td>

											<td style="padding-left:8px" align="left"><input
												name="user.userAddress" value="${user.userAddress}"
												type="text" size="30" id="userAddress"
												class="validate[custom[onlyLetterNumber],max[300]] text-input "
												maxlength="255" style="width:67%" />
											</td>
										</tr>


										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>



										<!-- 备注 -->
										<tr valign="top">
											<td align="right">描述：</td>

											<td style="padding-left:8px" align="left"><s:textarea
													id="userMemo" name="user.userMemo" cols="31" rows="3"
													cssStyle="width:67%"></s:textarea>
											</td>
										</tr>

									</table>
								</td>
							</tr>
							<tr>
								<td height="8px"></td>
							</tr>


							<tr>
								<td class='r2titler'><b>策略信息</b>
								</td>
							</tr>

							<tr>
								<td height="8px"></td>
							</tr>

							<!-- 策略 -->
							<tr>
								<td>
									<!-- 策略内容 -->
									<table width="99%" border="0" cellspacing="0" cellpadding="0">

										<tr valign="top">
											<td width="15%" align="right">密码策略：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td valign="top"><select name="policySelect"
															class="policySelect" id="passwordPolicySelect"
															style="width:280px" size="3" multiple="multiple">
																<s:iterator value="passwordPolicyList" status="stat">
																	<option class="passwordPolicy"
																		value="${passwordPolicyId}">
																		${passwordPolicyName}</option>
																</s:iterator>
														</select>
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="addPolicyDlg(0);defualtSeleted('passwordPolicySelect');" />
															<input type="button" value="" class="btndel"
															onclick="delOption('passwordPolicySelect','chk-passwordpolicy');checkPolicy();" />
														</td>
													</tr>
												</table>
											</td>
										</tr>

										<tr>
											<td height="8px"></td>
										</tr>

										<tr valign="top">
											<td width="15%" align="right">时间策略：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select name="policySelect" class="policySelect"
															id="timePolicySelect" style="width:280px" size="3"
															multiple="multiple">
																<s:iterator value="timePolicyList" status="stat">
																	<option class="timePolicy" value="${timePolicyId}">${timePolicyName}</option>
																</s:iterator>
														</select>
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="addPolicyDlg(1);defualtSeleted('timePolicySelect');" />
															<input type="button" value="" class="btndel"
															onclick="delOption('timePolicySelect','chk-timePolicy');" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr valign="top">
											<td width="15%" align="right" valign="top">地址策略：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select name="policySelect" class="policySelect"
															id="addressPolicySelect" style="width:280px" size="3"
															multiple="multiple">
																<s:iterator value="addressPolicyList" status="stat">
																	<option class="addressPolicy"
																		value="${addressPolicyId}">
																		${addressPolicyName}</option>
																</s:iterator>
														</select>
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="addPolicyDlg(2);defualtSeleted('addressPolicySelect');" />
															<input type="button" value="" class="btndel"
															onclick="delOption('addressPolicySelect','chk-addresspolicy');" />
														</td>
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

						</table>

					</div>
				</td>
			</tr>
		</table>

		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="subData();" /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/user/queryUser.action';" />
				</td>
			</tr>
		</table>
	</s:form>

	<!-- 角色选择的dialog -->
	<div id="dialog-addRole" title="角色选择">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<s:iterator value='allRoleList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type='checkbox'
						id='roleId-${roleId}' name='roleId' value='${roleId}' />
					</td>
					<td width="80%"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="addOk_a('${roleName}',${roleId},'roleSelect','dialog-addRole');">${roleName}</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<!-- 密码策略选择的dialog  -->
	<div id="dialog-passwordPolicy" title="密码策略选择">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<s:iterator value='allPasswordPolicyList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-passwordpolicy" id="chk-policy-${passwordPolicyId}"
						value="${passwordPolicyId}" />
					</td>
					<td width="80%"><a style='color:#555555'
						href="javascript:void(0);"
						onclick="addOk_a('${passwordPolicyName}',${passwordPolicyId},'passwordPolicySelect','dialog-passwordPolicy');modifyPwd();"
						style='color:#555555';">${passwordPolicyName} </a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<!-- 时间策略选择的dialog -->
	<div id="dialog-timePolicy" title="时间策略选择">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<s:iterator value='allTimePolicyList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-timePolicy" id="chk-policy-${timePolicyId}"
						value="${timePolicyId}" />
					</td>
					<td width="80%"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="addOk_a('${timePolicyName}',${timePolicyId},'timePolicySelect','dialog-timePolicy')">${timePolicyName}</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<!-- 地址策略选择的dialog -->
	<div id="dialog-addressPolicy" title="地址策略选择">
		<table width='96%' cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<s:iterator value='allAddressPolicyList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-addresspolicy" id="chk-policy-${addressPolicyId}"
						value="${addressPolicyId}" /></td>
					<td width="80%"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="addOk_a('${addressPolicyName}',${addressPolicyId},'addressPolicySelect','dialog-addressPolicy')">${addressPolicyName}</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<!-- 资产组选择的dialog -->
	<div id="dialog-assets" title="资产组选择">
		<table id="dlg-table-assets" width='96%' cellspacing='0'
			cellpadding='0' border='0' align='center'>
			<tr height="10"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="dlg-table-denyPolicy">
						<tr height="28">
							<td width="6%" align="center" class="biaoti"></td>
							<td width="100%" align="center" class="biaoti">资产组名称</td>
						</tr>

						<s:iterator value="allAssetGroupList" status="stat">
							<c:if test="${assetGroupId != 2}">
								<tr onclick="checkedAssetGroup('${assetGroupId}')">
									<td valign="middle" class="biaocm" align="center"><input
										type="radio" name="ids-assetGroup"
										class="check-box-assetGroup" id="assetGroup-${assetGroupId}"
										value="${assetGroupId}" onclick="event.cancelBubble=true;" />
										<input type="hidden" name="type" value="1" />
									</td>
									<td valign="middle" class="td_t"><a style='color: #555555'
										href="javascript:void(0);"
										onclick="addOk_a('${assetGroupName}',${assetGroupId}','assetsSelect','dialog-assets');">
											${assetGroupName}</a> <input type="hidden"
										name="hid-dlgName-assetGroup"
										id="hid-dlgName-assetGroup-${assetGroupId}"
										value="${assetGroupName}" />
									</td>

								</tr>
							</c:if>
						</s:iterator>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
