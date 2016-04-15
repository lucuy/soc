<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.soc.model.asset.AssetGroup"%>
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
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>
<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script language="javascript">
	function showDetail(groupId) {
		$('#detailFrame').document.loaction.href = '';
	}
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
	var regeNum=/^(([1-9])|([1-9][0-9]{1,3})|([1-6][0-4][0-9]{3})|([1-6][0-5][0-4][0-9]{2})|([1-6][0-5][0-5][0-2][0-9])|([1-6][0-5][0-5][0-3][0-5]))$/;
	function subdata() {
		var str = "" ;
		if($('#group_name').val()==null||$('#group_name').val()==''){
			alert("节点名称不能为空");
			return;
		}
		if($('#group_name').val().length>50){
			alert("节点名称长度不能大于50");
			$('#group_name').val('');
			$('#group_name').focus();
			return ;
		}
		if(!rege.test($('#group_name').val())){
			alert("节点名称输入的内容包含特殊字符，请重新输入");
			$('#group_name').val('');
			$('#group_name').focus();
			return;
		}
		
		if($('#group_memo').val().length>255){
			alert("描述长度不能大于50");
			$('#group_memo').val('');
			$('#group_memo').focus();
			return ;
		}
		
		 var ip =$('#group_ip').val();
		if(ip==''||ip==null){
			alert("IP地址不能为空");
			
			return;
		}else{
			if(!re1.test(ip)){
				alert("IP地址不合法");
				$('#group_ip').val('')
				return;	
			}
		}
		var port=$('#group_port').val();
		if(port==''||port==null){
			alert("端口不能为空");
			return;
		}else{
			if(!regeNum.test(port)){
				alert("端口书输入错误！请输入1-65535之间的数字");
				return;
			}
		}
			$("form").submit();
		
	}
	function cancel() {
		$("#body").attr("style", "display:none");
		/* $("#body").css("display","none"); */
	}
	$(document).ready(function(){
		lock();
		$('#dialog-assets').dialog({
			autoOpen:false,
		    height:500,
		    weight:100,
		    modal:true,
			buttons:{
				"确定[Enter]":function(){
					ok_button() ; 
				},
				"取消[ESC]":function(){
					$('#dialog-assets').dialog('close');
				}
			}
		});
	
	});
	
	function checkCheckBox(objId){
		$("#asset-"+objId).attr("checked",!$('#asset-'+objId).attr("checked"));
	}
	
	function ok_button(){
		$("#assetsSelect").empty();
		$('#assetsSelect').append("<option name='assetList' value='' >已选择资产:</option>   ");
		$("#denyPolicy input[name='ids-asset'][type='checkbox']:checked").each(function(){
			var node = $(this).parent().next().children("a");
			$('#assetsSelect').append("<option name='asset' id='asset' value='"+$(this).val()+"' >"+node.text()+"</option>   ");
		});
		$('#dialog-assets').dialog("close");
	}
	
	function openDialog(){

		var parentId =  $("#parent_id").val();
		var assetGroupId = $('#group_id').val();
		if(parentId == 0){
                 assetGroupId = 1 ;			   
			}else{
				 assetGroupId = parentId ; 
			}
		$.post("${ctx}/asset/queryAllAsset.action",{'assetGroupId':assetGroupId},function(data){
			$('#denyPolicy').empty();
			$.each(data,function(i,asset){
			 	var str="<tr><td valign='middle' class='biaocm' align='center'>"
				+"<input type='checkbox' name='ids-asset' class='check-box-assetGroup' id='asset-"+asset.assetId+"' value='"+asset.assetId+"' onclick='event.cancelBubble=true;'></td>"
				+"<td valign='middle' class='td_t'><a style='color: #555555' href='javascript:void(0);' onclick='checkCheckBox("+asset.assetId+");'>"+asset.assetName+"</a></td></tr>" ;
				$("#denyPolicy").append(str);
			});
			$('#assetsSelect').children("option").each(function(){
				 var id = $(this).val();
				 if(id != ""){
					 $('#asset-'+id).attr("checked","checked");
				 }
			});
		});
		
		$('#dialog-assets').dialog("open");
	}
	function lock(){
		var groupName = "${assetGroup.assetGroupName }" ; 
		if(groupName == "" || groupName == null){
			return ;
		}else{
			$('#group_name').attr("class","readonly");
		}
	}
	
	function delAssetOption(){
		if( $('#assetsSelect').get(0).selectedIndex == -1 ){
			if(window.confirm("确认删除所有么？")){
				$("#assetsSelect").empty();
			}
		}else{
			$('#assetsSelect').children('option').each(function(){
				if($(this).attr("selected") == true){
					if($(this).attr("name") == "asset"){
						$(this).attr("checked",false);	
					}
				}
			});
			$("#assetsSelect").find("option:selected").remove();
		}
	}

	
</script>

</head>

<body>
	<div id="body">
		<s:form id="fmInput" action="save.action"
			namespace="/nodeGroup" method="post" theme="simple">
			<table width="99%" height="796px" border="0" cellspacing="0" cellpadding="0"
				style="
	margin-left: 4px">
				<s:hidden name="nodeGroup.nodeGroupId" id="group_id" />
				
				<tr>
					<td width="45%" valign="top">
						<div class="framDiv">
							<!--  左侧table-->
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td class="r2titler">节点信息</td>
								</tr>
								<tr>
									<td>
										<table width="99%" border="0" cellspacing="6" cellpadding="10">
						                <tr>

						    <td>节点名称(<span class="spanred">*</span>)：</td>
							   <td><input type="text" name="nodeGroup.nodeGroupName"
									id="group_name" value="${nodeGroup.nodeGroupName }">
							   </td>
							
						                    </tr>
						                       <tr>
						                    <td>节点IP(<span class="spanred">*</span>)：</td>
							   <td><input type="text" name="nodeGroup.nodeIp"
									id="group_ip" value="${nodeGroup.nodeIp }">
							   </td>
							   </tr>
							   <tr>
							   <td>端口(<span class="spanred">*</span>)：</td>
							   <td><input type="text" name="nodeGroup.nodePort" class="validate[custom[settingPort]] text-input "
									id="group_port" value="${nodeGroup.nodePort}">
							   </td>
						                    </tr>
						        <tr>
							   <td>登录名(<span class="spanred">*</span>)：</td>
							   <td><input type="text" name="nodeGroup.loginName" class="validate[required,custom[onlyLetterNumber]] text-input"
									id="group_port" value="${nodeGroup.loginName}">
							   </td>
						                    </tr>
						                   
											<tr>
												<td>上级节点：</td>
												<td><s:textfield
														name="nodeGroup.parentGroup.nodeGroupName"
														cssClass="readonly" id="parent_group_name" readonly="true" />
													<s:hidden name="nodeGroup.nodeGroupParentId"
														id="parent_id" /></td>
											</tr>
											<tr>
												<td>上级节点IP：</td>
												<td><s:textfield
														name="nodeGroup.parentGroup.nodeIp"
														cssClass="readonly" id="parent_group_name" readonly="true" />
													
											</tr>
											<tr>
												<td>上级节点端口：</td>
												<td><s:textfield
														name="nodeGroup.parentGroup.nodePort"
														cssClass="readonly" id="parent_group_name" readonly="true" />
											</tr>
											<tr>
												<td>备注：</td>
												<td><s:textarea name="nodeGroup.nodeGroupMemo" 
														id="group_memo" cols="40" rows="3" />
												</td>
											</tr>
											
											</tr>
											
											

										</table></td>
								</tr>

								<tr height="10" align="center">
									<td colspan="2"><div class="xuxian"></div>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="10"
											cellpadding="10">
											<tr>
												<td><input type="button" class="btnyh" id="btnSave"
													value="保  存" onclick="subdata();" /> <input type="button"
													class="btnyh" id="btnCancel" value="取  消"
													onclick="cancel();" /></td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div></td>
				</tr>
			</table>
		</s:form>
	</div>
				
			
			<!-- 资产组选择的dialog -->
	<div id="dialog-assets" title="资产选择">
		<table id="dlg-table-assets" width='96%' cellspacing='0'
			cellpadding='0' border='0' align='center'>
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="1" cellpadding="0"
						class="tab2" id="denyPolicy">
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
