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

						/*if (/msie/i.test(navigator.userAgent)) //ie浏览器 
						{
							document.getElementById('assetName').onpropertychange = checkAssetName;
						} else {//非ie浏览器，比如Firefox 
							document.getElementById('assetName')
									.addEventListener("input", checkAssetName,
											false);
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
						setTypeCategory();
						*/
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
		if(""==$("#taskName").val()|| null==$("#taskName").val()){
			alert("任务名称不能为空！");
			return ;
		}
		look();
		if(!checkIPBigSmall()){
			return ; 
		}
		

			$("#assetForm").submit();

		

	}

	

	function look(){

     var se =document.getElementById("collectorMac");   
                 var option=se.getElementsByTagName("option");   
                 var str = "" ;   
                 for(var i=0;i<option.length;i++)   
                 {   
                 if(option[i].selected)   
                 {   
                 document.getElementById("collectorName").value = option[i].text;
     
                 }   
                 }  
              
}
	//检查两个IP的大小
	function checkIPBigSmall(){
             var startIP = $('#assetAliasName').val();
             if(""==startIP || null == startIP){
            	 alert("起始IP不能为空！");
            	 return false;
             }
             var endIP = $('#assetWorkIdent').val();
             if(""==endIP || null == endIP){
            	 alert("结束IP不能为空！");
            	 return false;
             }
             if(startIP != "" && endIP != ""){
	             var  re1 =  /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/ ;
	             var test1 = re1.test(startIP);
	             var test2 = re1.test(endIP);
	             var a1 = new Array();
	             var a2 = new Array();
	             if(test1 && test2){
	            	a1 = startIP.split("\.");
	            	a2 = endIP.split("\.");
	            	$('#ipSpan').html("");
	            	for(var i=0;i<a1.length;i++){
	            	     	
	            		if(a1[i]==a2[i] && i<3){   
	            			continue ;
	            		}else if(a1[i]!=a2[i] && i<3 ){
	            			$('#ipSpan').css("color","red");
	            			$('#ipSpan').html("请核对IP，必须处于同一网段");
	            			return false ; 
	            		}
	            		
	            		if(i==3){
	            			if(parseInt(a1[i]) >= parseInt(a2[i])){
	            				$('#ipSpan').css("color","red");
		            			$('#ipSpan').html("结束IP必须大于起始IP");
		            			return false ; 
	            			}else{
	            				return true ; 
	            			}
	            		}
	            	        
	            	}
	             }
		   	
             } 
	}
	
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="addTask.action" namespace="/assetProbeTask" method="post"
		theme="simple" id="assetForm" name="assetForm">
		<s:hidden name="disAll" id="disAll" />
		<s:hidden name="fireAll" id="fireAll" />
		<s:hidden name="commandAll" id="commandAll" />
		<s:hidden name="asset.assetStatus" id="assetStatus" />
		<!--  总table-->
		<!--  主table-->
		<table width="40%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<!-- left area -->
				<td width="50%" valign="top">
					<!--  左侧table-->
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<!-- 用户信息 -->
							<tr>
								<td class='r2titler' colspan='3'><b>资产探测信息 </b>
								</td>
							</tr>
							<tr>
								<td>
									<!-- 资产探测基本信息 -->
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 任务名称: -->
										<tr>
											<td align="right" width="20%" style="padding-left:10px"><span
												class="spanred">*&nbsp;</span>任务名称：</td>
											<td align="left" class="padding"><input
												class="validate[required,custom[spechars],custom[validateLength]]"
												name="taskName"
												type="text" size="40" id="taskName" maxlength="255"
												style="width: 270px" 
												 />
											</td>
											<td><span id="check_loginname_msg"></span>
											</td>
										</tr>
										<!-- 空行-->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>



										<!--采集器-->
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>采集器：</td>
											<td class="padding"><select style="width:274px"
												name="collectorMac" id="collectorMac">
													<s:iterator value="collectorList" status="stat">
													<option value="${collectorMac}" id="collectorMac" 
														 <c:if test="${asset.assetCollectorMac==collectorMac}">selected="selected"</c:if> onclick="look()">${collectorBasic}</option>
													</s:iterator>

											</select></td>
											<td><input type="hidden" name="collectorName" id="collectorName"/></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!--起始IP-->
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>起始IP：</td>
											<td align="left" class="padding"><input class="validate[required,custom[validateLength],custom[ipv4]]"
												name="beginIp"
												type="text" size="40" id="assetAliasName" maxlength="255"
												style="width: 270px" />
											</td>
										</tr>

										<tr>
											<td align="right" class="td_detail_separator"></td>
										</tr>
										<!--结束IP -->
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>结束IP：</td>
											<td align="left" class="padding"><input class="validate[required,custom[validateLength],custom[ipv4]]"
												name="endIp"
												type="text" size="40" id="assetWorkIdent" maxlength="255"
												style="width: 270px" onblur="checkIPBigSmall();" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
										    <td align="right"></td>
											<td align="left" class="td_detail_separator">
											   &nbsp;<span id="ipSpan"></span>
											</td>
										</tr>


								
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 描述-->
										<tr>

											<td align="right" style="vertical-align:20%">描述：</td>
											<td class="padding"><s:textarea name="medo"
													id="medo" cols="35" rows="4" cssStyle="width:273px"></s:textarea>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td height="5px"></td>
										</tr>
									
							
						</table>
					</div>
				</td>

			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onClick="subData();" /> <input
					type="submit" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/assetProbeTask/queryTask.action';" />
				</td>
			</tr>
</table>
	</s:form>

</body>
</html>
