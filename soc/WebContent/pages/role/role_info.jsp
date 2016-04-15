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
<style type="text/css">
.checked{
}
</style>
<script language="javascript">
	jQuery(document).ready(function() {
		jQuery("#roleForm").validationEngine();
		
		//初始化保存按钮
		var roleId = $("#roleId").val();
		if(roleId==34||roleId==35||roleId==36){
		   document.getElementById("btnSave").disabled = true;
		return;
		}else{
			  document.getElementById("btnSave").disabled = false;
			  }
	
		$("input[name='permissionIds'][type='hidden']").each(
		   function() {
		   		var superId = $(this).val();
		   		var flag = true;
		   		$("input[id='item-" + superId + "'][type='checkbox']").each(
		   			function() {
		   				if($(this).attr("checked") == false) {
		   					flag=false;
		   					return;
		   				}
		   			}
		   		);
		   		if(flag) {
		   			$("#chkAll-module" + $(this).val()).attr('checked',false);
		   			$("#chkAll-module" + superId).removeClass('not_checked');
		   		}
	        }
		);
		
		if(/msie/i.test(navigator.userAgent))    //ie浏览器 
	 	{
		    document.getElementById('roleName').onpropertychange = checkRoleName;
	 	} 
		 else 
		{//非ie浏览器，比如Firefox 
		   document.getElementById('roleName').addEventListener("input",checkRoleName,false); 
		} 
	});

	function limit(check) {
		if ($(check).attr("checked")) {
			$('#islimit1').show();
			$('#islimit2').show();
		} else {
			$('#islimit1').hide();
			$('#islimit2').hide();
		}
	}
	
	//验证角色名称唯一性
	var existFlag;	
	var overflog = false;
	function checkRoleName() {
	    $("#check_rolename_msg").empty();
		var roleName = $("#roleName").val();
		if('${role.roleId}'=='')
		{
			if(roleName!="" || roleName==null)
			{	
				$.ajax({
					type : "post",
					url : "${ctx}/role/checkRoleName.action",
					dataType : "text",
					data : "&roleName=" + roleName,
					success : function(result) {
						if (result == 'true') {
							$("#check_rolename_msg").addClass("spanred");
							$("#check_rolename_msg").html("该名称已占用");
							
							existFlag = true;
						} else {
							$("#check_rolename_msg").removeClass('spanred');
							$("#check_rolename_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
							existFlag = false;
						}
						overflog= true;
					}
				});
			}
		}
		else
		{
		  overflog = true;
		  existFlag = false;
		}
	}
	//保存角色
	function subData() {
	     $("#check_rolename_msg").empty();
	     if('${role.roleId}'=='')
	     {
	      /*  if(overflog==false)
	       {
	         alert("网路不可达");
	         $("#roleName").focus();
	       }
	       else
	       { */
		   if (existFlag) {
		 	 alert('角色名已占用，请重新设置！');
			 $("#roleName").focus();
			 return;
		  // }
		   }
		 }
		
	   
	 	
		if(!existFlag){
			$("#roleForm").submit();
		}
		
	}
	
	
		
	// 全选/反选
	function selectCheckBox(checkBoxId, superId) {
		if(event.srcElement.className=='not_checked') {
			event.srcElement.className=null;
			$("#" + checkBoxId).attr("checked",false);
			$(".checkbox-module" + superId).attr('checked',false);
			
		} else {
			$("#"+checkBoxId).attr("checked",true);
			event.srcElement.className ='not_checked';
			$(".checkbox-module" + superId).attr('checked',true);
			
		}
		
	}
	
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="updateRole.action" namespace="/role" method="post"
		theme="simple" id="roleForm" name="roleForm">
		<s:hidden name="role.roleId" id="roleId" />
		<s:hidden name="checkedAllUser" id="checkAllUser" />
		<s:token></s:token>
		<!--  总table-->
		<!--  主table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="70%" valign="top">
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>角色信息</b></td>
							</tr>

							<!-- 分隔符黑线 -->
							<tr>
								<td height="3px"></td>
							</tr>
							<tr>
								<!-- left area -->
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										align="center">

										<!-- 空行 -->
										<tr>
											<td height="5px"></td>
										</tr>

										<!-- 姓名 -->
										<tr>
											<td align="right"><span class="spanred">*</span>名称：</td>
											<td><input name="role.roleName" value="${role.roleName}"
												type="text" id="roleName"
												class="validate[required,custom[spechars],custom[validateLength]] text-input"
												style="width: 270px"
												<c:if test="${role != null&&errorMessage==null}">readonly="readonly"</c:if> onblur="checkRoleName()"/>
												<span id="check_rolename_msg"></span>
												<font style="color: red"><span id="error">${errorMessage}</span></font>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr valign="top">
											<td align="right">备注：</td>
											<td style="padding-right:180px"><s:textarea
													name="role.roleMemo" id="useremail" cols="31" rows="4"
													cssStyle="width:270px"></s:textarea></td>
										</tr>

										<!-- 角色模版 -->
										<tr style="display: none">
											<td align="right">角色模板：</td>
											<td style="padding-left: 20px"><select
												style="width: 380px;" id="template">
													<option>系统管理员</option>
													<option>角色管理员</option>
													<option>审计员</option>
													<option>操作员</option>
													<option>用户管理员</option>
													<option>资源管理员</option>
													<option>策略管理员</option>
													<option>授权管理员</option>
											</select></td>
										</tr>
									</table>
								</td>
							</tr>

							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td height="3px"></td>
							</tr>

							<!-- 数据权限 -->
						<%-- 	<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<c:set value="0" var="userAllType" />
										<tr valign="top">
											<td align="right" style="padding-left:20px;">用户：</td>
											<td>
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select name="usersSelect" id="usersSelect"
															class="usersSelect" style="width: 275px;" size="5"
															multiple="multiple">

																<c:if test="${userType==0}">
																	<option value="">已选择账户：</option>
																</c:if>

																<c:if test="${userType==-1}">
																	<option class="userList" value="-1">已选择所有的用户</option>
																</c:if>

																<s:iterator value="userList" status="stat">
																	<option class="userList" value="${reluserid}">${relusername}</option>
																</s:iterator>

														</select> <s:hidden name="userType" id="userType" /> <s:hidden
																name="users" id="users" />
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="userDlg();"
															<c:if test="${role.roleDisplay==0}">disabled="true"</c:if> />
															<input type="button" value="" class="btndel"
															onclick="delOption('usersSelect','roleId');"
															<c:if test="${role.roleDisplay==0}">disabled="true"</c:if> />
														</td>
													</tr>
												</table>
											</td>

											<td align="right">资产/组：</td>
											<td align="left">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select name="assetsSelect" id="assetsSelect"
															style="width: 275px" size="5" multiple="multiple">
																<c:if test="${assetType==0}">
																	<option id="asset" value="">已选择资产:</option>
																</c:if>

																<c:if test="${assetType==-1}">
																	<option id="asset" value="-1">已选择所有资产</option>
																</c:if>
																<s:iterator value="assetList" status="stat">
																	<option value="${relid}" id="asset">
																		${relname}</option>
																</s:iterator>
																<!-- <c:if test="${assetList!=null}">
																	<option value="">已选择资产：</option>
																	
																	<s:iterator value="assetList" status="stat">
																		<option value="${relId}" id="asset">
																			${relName}</option>
																	</s:iterator>
																</c:if> -->
																<c:if test="${assetGroupType==0}">
																	<option value="">已选择分组：</option>
																</c:if>
																<c:if test="${assetGroupType==-1}">
																	<option id="assetGroup" value="-1">已选择所有资产组</option>
																</c:if>

																<s:iterator value="assetGroupList" status="stat">
																	<option value="${relid}" id="assetGroup">
																		${relname}</option>
																</s:iterator>

														</select> <s:hidden name="assetType" id="assetType" /> <s:hidden
																name="assets" id="assets" /> <s:hidden
																name="assetGroups" id="assetGroups" />
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="assetDlg();"
															<c:if test="${role.roleDisplay==0}">disabled="true"</c:if> />
															<input type="button" value="" class="btndel"
															onclick="delAssetOption();"
															<c:if test="${role.roleDisplay==0}">disabled="true"</c:if> />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr> --%>

							<tr>
								<td height="7px"></td>
							</tr>

							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="r2titler"><b>权限范围</b></td>
										</tr>
										<tr>
											<td height="3px"></td>
										</tr>
										<tr>
											<td>
											<!-- ------------------------------------------------------- -->
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td valign="top">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																
																	<tr>
																	    <td valign="top">
																				<table width="100%" bgcolor="#FFFFFF"style="padding:10px;">
																					<tr>
																						<td>
																							<table width="100%" border="0" cellspacing="0"
																								cellpadding="0"style="background: url(/soc/images/tdBg.jpg); repeat-x;">
																								<tr>
																									<td align="left">首页模块</td>
																									<td align="right">全选/反选&nbsp;<input
																										type="checkbox" disabled="disabled" checked="checked"/></td>
																								</tr>
																							</table>
																						</td>
																					</tr>
																					<tr><td colspan="2">&nbsp;</td></tr>
																					<tr>
																							<td><input type="checkbox" disabled="disabled" checked="checked"/>
																								<span class="hand" ">首页模块</span>
																							</td>
																						
																					</tr>
																				</table>
																			</td>
																<!-- ------------------------------------------------------- -->
																	    <c:set value="0" var="root1" />
																		<s:iterator value="allPermissionList" status="pmll">
																			<td valign="top">
																				<table width="100%" bgcolor="#FFFFFF" style="padding:10px;">
																					<tr>
																						<td colspan="2">
																							<table width="100%" border="0" cellspacing="0"
																								cellpadding="0"style="background: url(/soc/images/tdBg.jpg); repeat-x;">
																								<tr>
																									<td align="left">${permissionModuleName}</td>
																									<td align="right">全选/反选&nbsp;<input
																										type="checkbox"
																										name="chkAll-module${permissionId}"
																										id="chkAll-module${permissionId}"
																										onclick="selectCheckBox('chkAll-module${permissionId}','${permissionId}');"  > <input
																										type="hidden" name="permissionIds"
																										value="${permissionId}" /></td>
																									<c:set value="${permissionId}" var="superId"></c:set>
																								</tr>
																							</table>
																						</td>
																					</tr>
																					<tr><td colspan="2">&nbsp;</td></tr>
																					<tr>
																						<c:set value="0" var="root" />
																						<s:iterator value="permissionModuleList"
																							status="pml">
																							<td><input type="checkbox" name="item"
																								id="item-${superId}" value="${permissionId}"
																								class="checkbox-module${superId}"
																								<s:iterator value="relPermissionList"
																							status="pl">	
																								<c:if test="${permissionId == relpermssionid}">checked="checked"</c:if></s:iterator> />
																								<span class="hand" onclick="chkCheckbox('');">${permissionModuleName}</span>
																							</td>
																							<c:set value="${root+1}" var="root" />
																							<c:if test="${root%2==0}">
																								<tr>
																							</c:if>
																						</s:iterator>
																					</tr>
																				</table>
																			</td>
																		<c:set value="${root1+1}" var="root1" />
																							<c:if test="${root1%3==0}">
																								<tr>
																							</c:if>
																		</s:iterator>
																	</tr>
																	<tr>
																		<td></td>
																	</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">
					<table width="780px" border="0" cellspacing="0" cellpadding="0">
						<!-- 空行 -->
						<tr>
							<td height="10px"></td>
						</tr>
						<tr>
							<td><span class="spanred">
									注:角色修改完成后，与该角色关联的账户需重新登录方可生效</span>&nbsp;&nbsp; <input type="button"
								class="btnyh" id="btnSave" onclick="subData();" value="保  存" />
								<input type="button" class="btnyh" id="btnCancel"
								onclick="window.location.href='/soc/role/queryRole.action?order=create_date';"
								value="取  消" /></td>
						</tr>
					</table> <!--  左侧table--></td>
			</tr>
		</table>
	</s:form>

	<!-- 用户选择的dialog -->
	<%-- <div id="dialog-user" title="账户选择">
		<table id="dlg-table-user" width='96%' cellspacing='1' cellpadding='0'
			border='0' align='center' class="tab2">
			<s:iterator value='allUserList' status='stat'>
				<tr style="line-height: 25px;">
					<td class="biaocm"><input type="checkbox" name="roleId"
						class="check-box-user" id="roleId-${userId}" value="${userId}"
						onclick="event.cancelBubble=true;" /> <input type="hidden"
						value="0" /></td>
					<!--  <td width="20%" align="center" class="biaocm"><input type='checkbox'
						id='roleId-${userId}' name='roleId' value='${userId}' /></td>-->

					<td width="80%"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="addOk_a('${userName}',${userId},'usersSelect','dialog-addUser');">${userLoginName}</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div> --%>

	<!-- 资产/资产组选择的dialog -->
	<%-- <div id="dialog-assets" title="资产/资产组选择">
		<table id="dlg-table-assets" width='96%' cellspacing='0'
			cellpadding='0' border='0' align='center'>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="dlg-table-conductPolicy">
						<tr height="28">
							<td width="6%" align="center" class="biaoti"><input
								type="checkbox" id="chkAll-asset" name="chkAll-asset"
								class="check-box-asset not-checked-asset" /></td>
							<td width="100%" align="center" class="biaoti">资产名称</td>
						</tr>
						<c:set value="1" var="root" />
						<s:iterator value="allAssetList" status="stat">
							<tr onclick="checkedAsset('${assetId}')">
								<td valign="middle" class="biaocm" align="center"><input
									type="checkbox" name="ids-asset" class="check-box-asset"
									id="asset-${assetId}" value="${assetId}"
									onclick="event.cancelBubble=true;" /> <input type="hidden"
									value="0" /></td>
								<td valign="middle" class="td_t"><a style='color: #555555'
									href="javascript:void(0);"
									onclick="addOk_a('${assetName}',${assetId},'assetsSelect','dialog-assets');">
										${assetName}</a> <input type="hidden" name="hid-dlgName-asset"
									id="hid-dlgName-asset-${assetId}" value="${assetName}" /></td>
								<c:set value="${root+1}" var="root" />
							</tr>
						</s:iterator>
					</table></td>
			</tr>
			<tr height="10"></tr>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="dlg-table-denyPolicy">
						<tr height="28">
							<td width="6%" align="center" class="biaoti"><input
								type="checkbox" id="chkAll-assetGroup" name="chkAll-assetGroup"
								class="check-box-assetGroup not-checked-assetGroup" /></td>
							<td width="100%" align="center" class="biaoti">资产组名称</td>
						</tr>
						<c:set value="1" var="root" />
						<s:iterator value="allAssetGroupList" status="stat">
							<tr onclick="checkedAssetGroup('${assetGroupId}')">
								<td valign="middle" class="biaocm" align="center"><input
									type="checkbox" name="ids-assetGroup"
									class="check-box-assetGroup" id="assetGroup-${assetGroupId}"
									value="${assetGroupId}" onclick="event.cancelBubble=true;" />
									<input type="hidden" name="type" value="1" /></td>
								<td valign="middle" class="td_t"><a style='color: #555555'
									href="javascript:void(0);"
									onclick="addOk_a('${assetGroupName}',${assetGroupId},'assetsSelect','dialog-assets');">
										${assetGroupName}</a> <input type="hidden"
									name="hid-dlgName-assetGroup"
									id="hid-dlgName-assetGroup-${assetGroupId}"
									value="${assetGroupName}" /></td>
								<c:set value="${root+1}" var="root" />
							</tr>
						</s:iterator>
					</table></td>
			</tr>
		</table>
	</div> --%>

</body>
</html>
