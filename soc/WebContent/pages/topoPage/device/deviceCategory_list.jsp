<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.soc.model.user.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
<script type="text/javascript" src="${ctx }/scripts/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" >
	$(document).ready(function() {
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// 高级-Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 280,
			buttons : {
				"查询[Enter]" : function() {
					
					extQuery();
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
			//同步账户
		$('#dialog-account').dialog({
			autoOpen : false,
			width : 400,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
					accountAjax();
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		$("#myTable").tablesorter();

	});

	//更新状态
	function updateStatus() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个用户信息...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}

		if (confirm("确认执行操作吗？")) {

			location.href = "${ctx}/user/updateUserStatus.action?ids=" + ids
					+ "&userStatus=" + $("#status").val();
		}
	}
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
		var keyword = $("#keyword").val();
		if(keyword.length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		if(!rege.test(keyword)){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		location.href = "${ctx}/device/queryAllDevice.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword));
	}

	//全选及反选
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});

	$(document).click(function(event) {
		//shiftForCheckBoxFun(event);
		destroyOverDlg();
	});

	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}

	function extQueryDlg() {
		$("#realName").val('');
		$("#loginName").val('');
		$("#creatorIP").val('');
		$("#userEmail").val('');
		$("#roleid").val('');
		$("#userState").val('');
		$("#check_creatorIP_msg").empty();
		$('#dialog-extQuery').dialog('open');
	}

	function accountShow() {
		
		$('#dialog-account').dialog('open');
		$('#d4321').blur();
	}
	function extQuery() {
		var regex = /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/;
		var regex1=/^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/;
		if ($('#creatorIP').val() != '') {
			if (!regex.test($('#creatorIP').val())) {
				$('#creatorIP').val('');
				$('#creatorIP').focus();
				alert("请输入正确的ip地址");
				return;
			}
		}
		if($('#realName').val().length>50){
			alert("姓名长度不能大于50,请重新输入");
			$('#realName').val('');
			$('#realName').focus();
			return;
		}
		if(!rege.test($('#realName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#realName').val('');
			$('#realName').focus();
			return;
		}
		if($('#loginName').val().length>50){
			alert("登陆账号长度不能大于50,请重新输入");
			$('#loginName').val('');
			$('#loginName').focus();
			return;
		}
		if(!rege.test($('#loginName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#loginName').val('');
			$('#loginName').focus();
			return;
		}
		if(!regex1.test($("#userEmail").val())){
			alert("请输入正确的邮箱地址");
			$('#userEmail').val('');
			$('#userEmail').focus();
			return;
		}
		$('#selUserRealName').val($.trim($('#realName').val()));
		$('#selUserLoginName').val($.trim($('#loginName').val()));
		$('#selUserCreatorIp').val($('#creatorIP').val());
		$('#reluserEmail').val($('#userEmail').val());
		$('#relroleid').val($('#roleid').val());
		$('#reluserState').val($('#userState').val());
		
		$('#queryForm').submit();
		$(this).dialog("close");
	}

	function deleteDevice() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个设备类型信息...");
			return;
		}
		
			$.ajax({
			      type : "post",
				   url : "${ctx}/deviceCategory/deviceCategoryStatus.action",
				   dataType : "json",
				   data : "&ids="+ids,
				   success : function(result) {
					  if(1==result.resultByAjax){
					  alert("您要删除的设备类型尚有设备，禁止删除！");
					  }else if(2==result.resultByAjax){
					  	alert("您要删除的设备类型有些尚有设备，将删除不含有设备的设备类型");
					  	location.href = "${ctx}/deviceCategory/deleteDeviceCategory.action?ids=" + ids;
					  } else if(-1==result.resultByAjax){
					  		if (confirm("确认执行操作吗？")) {
							location.href = "${ctx}/deviceCategory/deleteDeviceCategory.action?ids=" + ids;
							}
					  }
				   }
			 }); 
		
	}
	/*function checkmail(){
		var mail=$("#userEmail").val();
		var regex=/^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/;
		if(mail!=null&&mail!=""){
			if(regex.test($("#userEmail").val())){
				
				extQuery();
			}else{
				
				$("#check_creatorEmail_msg").html(
				"<font color='red'>请输入正确的邮箱...</font>");
			}
		}else{
			extQuery();
		}
	}*/
	
	function accountAjax(){
		if(confirm('确认同步账号吗？')){
			var appId=$('#accoutIp').val();
			var syncId=$('#syncIp').val();
			var appUrl="";
			/*var startDate="";
			var endDate = "";
			if($('#d4321').val()!="" && $('#d4322').val()!=""){
				startDate = (new Date($('#d4321').val())).getTime();
				endDate =  (new Date($('#d4322').val())).getTime();
				appUrl = syncId+"/portal/account/pullAccount.action?appId="+appId+"&startDate="+startDate+"&endDate="+endDate;
				alert(appUrl);
				$('#dialog-account').dialog("close");
			}else{
				$('#dialog-account').dialog("close");
			}
			*/
			appUrl = syncId+"/portal/account/pullAccount.action?appId="+appId;
			$.ajax({
	                type:"get",
	                url:appUrl,
	                async : true,
	                dataType : "jsonp",
	                jsonp: "callback",
	                jsonpCallback:"success_jsonpCallback",
	                success:function(data)
	                {
	                	 $('#ccc').html(data.xmlData); 	
		                 $('#data').val(data.xmlData);
		                 $('#userForm1').submit();
	                }
	        });
		
		}
	}

	function add(){
		location.href="${ctx}/deviceCategory/editDeviceCategory.action";
	}
	
	function openwin() { 
/* window.open ('${ctx}/pages/topoPage/device/telnetPage.jsp', 'newwindow', 'height=500, width=600, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no') ; */
location.href="${ctx}/deviceCategory/telnetPage.action"; 
//写成一行 
} 

function opensshwin() { 
/* window.open ('${ctx}/pages/topoPage/device/sshPage.jsp', 'newwindow', 'height=500, width=600, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=yes, status=no') ; */
 location.href="${ctx}/deviceCategory/sshPage.action"; 
//写成一行 
} 
</script>
</head>

<body style="margin-top:2px;">
	
	<s:form action="queryAllDeviceCategory.action" namespace="/deviceCategory" method="post"
		theme="simple" id="userForm" name="userForm">
		
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right">
					   
								
							 <input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="add();" /> 
				
							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="deleteDevice();" />
						</div>

						<!--<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>-->


					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="myTable">
									<thead>
										<tr height="28" class="biaoti">
											<td width="10%" class="biaoti"><input type="checkbox"
												id="chkAll" name="chkAll" class="check-box not_checked" />
											</td>
											<th width="30%" class="biaoti">
											设备类型名称</th>
											<th width="30%" class="biaoti">
											设备类型图片</th>
											<th width="30%" class="biaoti">
											设备类型描述</th>
											<!-- <th width="15%" class="biaoti">
											设备MAC</th>
											<th width="10%" class="biaoti">
											设备状态</th>
											<th width="15%" class="biaoti">
											设备描述</th> -->
										</tr>
									</thead>
									<tbody>
										<c:set value="1" var="root" />
										<c:set value="" var="resStr" />
										<s:property value="#session.XXX" />
										<s:iterator value="deviceCategoryList" status="stat">
											<input type="hidden" name="deviceCategoryId" id="deviceCategoryId"
												value="${deviceCategory_id}" />
												<input type="hidden" name="count" id="count"
												value="count" />
											<tr>
												<td class="biaocm" valign="middle"><input
													type="checkbox" name="ids" id="${deviceCategory_id}" value="${deviceCategory_id}"
													class="check-box" />
												</td>
												<td valign="middle"><a
													href="${ctx}/deviceCategory/editDeviceCategory.action?id=${deviceCategory_id}">${deviceCategory_name}</a>
												</td>
												<td valign="middle"><img alt="设备类型图片" src="${ctx}/${deviceCategory_photo}" width="30px;" height="30px;"></td>
												<td valign="middle" align="center">
												<xmp>${deviceCategory_describe }</xmp>
												</td>
												<c:set value="0" var="root2" />
												<c:set value="" var="resStr" />
											</tr>
											<c:set value="${root+1}" var="root" />
										</s:iterator>
									</tbody>
									<!-- <tr>
										<td colspan="7" width="100%"><jsp:include
												page="/pages/commons/page.jsp"></jsp:include><br></td>
									</tr> -->

								</table>
						 <!--  <input type="button" onclick="openwin()" value="Telnet" id="TelnetTest"> 
							<input type="button" onclick="opensshwin()" value="SSH" id="TelnetTest">    -->
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
	</s:form>
<%-- <a href="${ctx}/deviceCategory/pingPage.action?deviceip=123" target="_blank">PING</a> --%> 

	<!-- ext query from -->
	<s:form action="queryUser.action" namespace="/user" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selUserLoginName" id="selUserLoginName" />
		<s:hidden name="selUserRealName" id="selUserRealName" />
		<s:hidden name="selUserCreatorIp" id="selUserCreatorIp" />
		<s:hidden name="reluserState" id="reluserState"/>
		<s:hidden name="relroleid" id="relroleid"/>
		<s:hidden name="reluserEmail" id="reluserEmail"/>
			
	</s:form>

	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">姓名:</td>
				<td><input id="realName" name="realName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>登录帐号:</td>
				<td><input id="loginName" name="loginName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>创建人IP:</td>
				<td><input id="creatorIP" name="creatorIP" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>角色:</td>
				<td>
				<select id="roleid" style="width:250px;" name='roleid' onkeypress="if(event.keyCode==13)extQuery();" >
				<option value="">--请选择--</option>
												<s:iterator value='allRoleList' status='stat'>
					<option   value='${roleId}'> ${roleName}
					</option >
			</s:iterator>
												</select>
			</tr>
			<tr>
				<td>状态:</td>
				<td><select id="userState" style="width:250px;" name='userState' onkeypress="if(event.keyCode==13)extQuery();" >
				<option value="">--请选择--</option>
								<option value="1">激活</option>				
				<option value="0">锁定</option>
												</select>
				</td>
			</tr>
			<tr>
				<td>邮件:</td>
				<td><input id="userEmail" name="userEmail" type="text" 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
					<br/> <span id="check_creatorEmail_msg"></span>
				</td>
			</tr>
			<tr>
			<td><span id="check_creatorIP_msg"></span></td>
			</tr>
		</table>
	</div>
	
	<div id="dialog-account" title="账户同步">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="25%">开始时间:</td>                        
				<td><input  class="Wdate" type="text" onClick="WdatePicker()" id="d4321" onFocus="WdatePicker({dateFmt:'yyyy/M/d HH:mm:ss',maxDate:'#F{$dp.$D(\'d4322\')}',maxDate:'%y-%M-%d-1'})"
					style="width:250px;" onkeypress="if(event.keyCode==13)accountAjax();" />
				</td>
			</tr>
			<tr>
				<td>结束时间:</td>
				<td><input class="Wdate" type="text" onClick="WdatePicker()" id="d4322" onFocus="WdatePicker({dateFmt:'yyyy/M/d HH:mm:ss',minDate:'#F{$dp.$D(\'d4321\')}',maxDate:'%y-%M-%d'})"
					style="width:250px;" onkeypress="if(event.keyCode==13)accountAjax();"  />
				</td>
			</tr>
			
		</table>
	</div>
	
	<input type="hidden" id="actionStr" value="${actionStr}" />
	<s:form action="accountData" namespace="/user" method="post"
		theme="simple" id="userForm1">
		<input id="data" name="data" type="hidden"/>

		<input id="accoutIp" value="${accoutIp}" type="hidden" />
		<input id="syncIp" value="${syncIp}" type="hidden" />

	</s:form>
</body>
</html>
