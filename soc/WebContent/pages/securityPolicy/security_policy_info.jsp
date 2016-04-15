<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>

<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 

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
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>

<script language="javascript">
	jQuery(document).ready(function() {
		jQuery("#timeForm").validationEngine();
        
        
		$("#ableTime1").click(function() {
			$("#policyExecuteWay").val(1);
			$("#time1").attr("style", "display:none");
			$("#time2").attr("style", "display:block");
		});

		$("#ableTime").click(function() {
			$("#policyExecuteWay").val(0);
			$("#time1").attr("style", "display:block");
			$("#time2").attr("style", "display:none");

		});

		//判断用户传递过来的执行方式
		if ('${timePolicy.timePolicyexecuteWay}' == '1') {
			$("#ableTime1").attr("checked", true);
			$("#time2").attr("style", "display:block");
			$("#time1").attr("style", "display:none");
		} else {
			$("#ableTime").attr("checked", true);
			$("#time1").attr("style", "display:block");
			$("#time2").attr("style", "display:none");

		}
		
$('#dialog-loadAsset').dialog({
			autoOpen : false,
			width : 380,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
				$("#policyFile").val($("#upTar").val());
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
 $('#123').click(function(){
             $("#upTar").click();
              });
	});

	
	//提交按钮
	function subData() {
	//checkName();
	if(""=='${securityPolicy.policyName}'){
	
	if(""==$("#timePolicyName").val()||null==$("#timePolicyName").val()){
		alert("策略名不能为空！");
		return;
	}
	if(assetFlag==false){
		alert("策略已被占用请更换！");
		return;
	}
	}
	var upgradeFile = $("#aa").val();
		//var upgradeFile = $("#aa").val();
		var start = upgradeFile.lastIndexOf(".");
		//alert(upgradeFile.substr(start));
			if (upgradeFile.substr(start) != ".bat"&&upgradeFile.substr(start) != ".sh") {
			alert("请上传脚本文件！");
			return ;
			}
			//alert(flag);
			if ( assetFlag == true ) {
				
				$("#timeForm").submit();
				}
			
			
				
	}
	function loadAssetDlg() {
		clearFile();
		$('#dialog-loadAsset').dialog('open');

	}
	function clearFile() {
		var oldFile = document.getElementById("upTar");
		var newFile = document.createElement("input");
		newFile.id = oldFile.id;
		newFile.type = "file";
		newFile.name = "upTar";
		oldFile.parentNode.replaceChild(newFile, oldFile);
	}
	function tt(){
	$("#aa").val($("#upTar").val());
	}
		//判断资产是否存在
	var assetFlag=true;
	function checkName() {
		if ('${securityPolicy.id}' == '' || '${securityPolicy.policyName}' != $("#timePolicyName").val()) {
			if ($("#timePolicyName").val() != "") {
				var assetName = $("#timePolicyName").val();
				$
						.ajax({
							type : "post",
							url : "${ctx}/securityPolicy/checkName.action",
							async : false,
							dataType : "text",
							data : "&timePolicyName=" + assetName,
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
			$("#check_loginname_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
			assetFlag = true;
		}
	}
	
	
	
	
	
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="updateSecurityPolicy" namespace="/securityPolicy"
		method="post" theme="simple" id="timeForm" name="timeForm" enctype="multipart/form-data">

  <s:token/>
		<!--  主table-->
		
					<div class="framDiv"  style = "width:60%"  >
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler" colspan="2"><b>安全策略信息</b></td>
							</tr>

							<!-- 分隔符黑线 -->
							<tr>
								<td height="3px"></td>
							</tr>
							<tr>
								<!-- left area -->
								<td colspan="20"> 
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
											<td class="td_detail_separator"></td>
										
										</tr>

										<!-- 姓名 -->
										<tr>

											<td align="right" style="padding-left:48px" width="20%"><span
												class="spanred">*&nbsp;</span>策略名称：</td>
											<td align="left" width="50%"><input
												class="validate[required,custom[spechars],custom[validateLength]] text-input"
												name="securityPolicy.policyName"
												value="${securityPolicy.policyName}"  <c:if test="${securityPolicy != null}">readonly="readonly"</c:if>
												<c:if test="${securityPolicy == null}">onblur="checkName();"</c:if>
												type="text" size="40" id="timePolicyName" maxlength="255"
												style="width: 270px" /><input   
												type="hidden" name="securityPolicy.id"
												value="${securityPolicy.id}">&nbsp;<span id="check_loginname_msg"></span></td>
										</tr>
										<tr>
											<td height="2px"></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
								<tr>

											<td align="right" style="padding-left:48px">创建者：</td>
											<td align="left" width="50%"><input
												value="${securityPolicy.createUsername}"
												type="text" size="40" id="createUsername"
												style="width: 270px"  readonly="readonly"/>
											</td>
											</tr>
											<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<tr>

											<td align="right" style="padding-left:48px">修改者：</td>
											<td align="left" width="50%"><input
												value="${securityPolicy.modifyUsername}"
												type="text" size="40" id="modifyUsername"
												style="width: 270px" readonly="readonly"/>
											</td>
											</tr>
											<tr>
											<td class="td_detail_separator"></td>
										</tr>
										
										<tr>

											<td align="right" style="padding-left:48px">创建时间：</td>
											<td align="left" width="50%"><input
												value="${securityPolicy.createTime}"
												type="text" size="40" id="createTime"
												style="width: 270px" readonly="readonly"/>
												<input name="securityPolicy.createTime" type="hidden" value="${securityPolicy.createTime}"/>
											</td>
											</tr>
											<tr>
											<td class="td_detail_separator"></td>
										</tr>
											<tr>

											<td align="right" style="padding-left:48px">修改时间：</td>
											<td align="left" width="50%"><input
												value="${securityPolicy.modifyTime}"
												type="text" size="40" id="modifyTime"
												style="width: 270px" readonly="readonly"/>
											</td>
											</tr>
											<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 时间策略描述 -->
											<tr>

											<td align="right" style="padding-left:48px"><span
												class="spanred">*&nbsp;</span>策略文件：</td>
											<td align="left" width="50%">
											<input type="hidden" name="tmpPolicyFileName" value="${tmpPolicyFileName}"/>
						<input id="aa" name = "timePolicyMemo" class="validate[required]" style="width: 200px;height:19px;position:absolute;" type="text"  value ="${securityPolicy.relPolicyName}"  readonly="readonly">
										 <input  style="width:270px;height:20px;" type="file"  id="upTar" name="upTar"  onchange="tt()" />    	 
						<%--<input  style="position:absolute;margin-left: 271px;" type="button" value="浏览" class="btnstyle"id = "123">  
--%><%--<input onchange="tt()" style="filter:alpha(opacity=0);width:335px;height:20px;" type="file"  id="upTar" name="upTar"  />  
						
					
						--%></td>
											</tr>
											<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>


											<td align="right" style="padding-left:62px;vertical-align:10%"><span
												class="spanred">*&nbsp;</span>描述：</td>
											<td>
													<textarea cols="35" id ="desc"
													class="validate[required]"
													
														name="securityPolicy.desc"
													
													rows="5">${securityPolicy.desc}</textarea>
											</td>
										</tr>

								
										



										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									</table> <!--  左侧table--></td>
							</tr>
						</table>
					</div>
			
		<table style = "width:60%"  border="0" cellspacing="0" cellpadding="0"
			style="align:right">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input
					type="button" class="btnyh" id="btnSave" value="保  存"
					onclick="subData();" /> <input type="button" class="btnyh"
					id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/securityPolicy/query.action';" />
				</td>
			</tr>
		</table>
		<!-- 选择文件对话框 -->
	<div id="dialog-loadAsset" title="策略对话框">

			<table width="90%" border="0" cellspacing="5" cellpadding="5"
				align="center">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
				<tr>
					<td valign="30%" width="80px">请选择文件:</td>
					<td valign="middle">
				</tr>
				<tr>
					<td></td>
					<td><span id="span_ip"></span></td>
				</tr>
			</table>




	</div>
	</s:form>
	
	
</body>
</html>
