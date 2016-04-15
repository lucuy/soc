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
		$("#myTable").tablesorter();
		setInterval("getFlashDevice()", 120000);  //定时器2分钟刷新设备的状态
	});
	
	function getFlashDevice() {
		parent.mainFrame.location.href = '/soc/device/queryAllDevice.action';
	}
	
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
	   $("#selDeviceName").val('');
		$("#selDeviceCategory").val('');
		$("#selDeviceIp").val('');
		$("#selDeviceMac").val('');
		$("#selDeviceStatus").val('');
		$("#selDeviceLocation").val('');
		$('#selectDeviceName').val($.trim($('#selDeviceName').val()));
		$('#selectDeviceCategory').val($.trim($('#selDeviceCategory').val()));
		$('#selectDeviceIp').val($('#selDeviceIp').val());
		$('#selectDeviceMac').val($('#selDeviceMac').val());
		$('#selectDeviceStatus').val($('#selDeviceStatus').val());
		$('#selectDeviceLocation').val($('#selDeviceLocation').val());
		
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
				+ encodeURIComponent(encodeURIComponent(keyword))+"&date="+new Date();
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
		$("#selDeviceName").val('');
		$("#selDeviceCategory").val('');
		$("#selDeviceIp").val('');
		$("#selDeviceMac").val('');
		$("#selDeviceStatus").val('');
		$("#selDeviceLocation").val('');
		$('#dialog-extQuery').dialog('open');
	}

	
	function extQuery() {
		var regex = /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/;
		var regex1=/^($)|(([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}$)/;
		if ($('#selDeviceIp').val() != '') {
			if (!regex.test($('#selDeviceIp').val())) {
				$('#selDeviceIp').val('');
				$('#selDeviceIp').focus();
				alert("请输入正确的ip地址");
				return;
			}
		}
		if($('#selDeviceName').val().length>50){
			alert("设备名称不能大于50,请重新输入");
			$('#selDeviceName').val('');
			$('#selDeviceName').focus();
			return;
		}
		if(!rege.test($('#selDeviceName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#selDeviceName').val('');
			$('#selDeviceName').focus();
			return;
		}
	
		$('#selectDeviceName').val($.trim($('#selDeviceName').val()));
		$('#selectDeviceCategory').val($.trim($('#selDeviceCategory').val()));
		$('#selectDeviceIp').val($('#selDeviceIp').val());
		$('#selectDeviceMac').val($('#selDeviceMac').val());
		$('#selectDeviceStatus').val($('#selDeviceStatus').val());
		$('#selectDeviceLocation').val($('#selDeviceLocation').val());
		
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
			alert("请至少选择一个要删除的设备...");
			return;
		}
		
		 if (confirm("确认执行操作吗？")) {
			if(ids != ""){
			 $.ajax({
			      type : "post",
				   url : "${ctx}/device/queryAssociationDevice.action",
				   dataType : "json",
				   data : "&deviceIds="+ids,
				   success : function(result) {
					   $.each(result.resultByAjax, function(i, item) {
						   alert("设备"+item.device_name+"有关联设备，禁止删除");
						   ids=  ids.replace(item.device_id,""); 
						});
					   location.href = "${ctx}/device/deleteDevice.action?ids=" + ids;
				   }
			 }); 
		}
			 
	}
}

	function add(){
		location.href="${ctx}/device/editDevice.action";
	}
</script>
</head>
<body style="margin-top:2px;">
	<s:form action="queryAllDevice.action" namespace="/device" method="post"
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

						<!-- <div class="right">
					   
								
							 <input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="add();" /> 
				
							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="deleteDevice();" />
						</div> -->

						   <span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> <a href="#" class="jianju"
							onclick="extQueryDlg();">高级</a>  


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
											<!-- <td width="6%" class="biaoti"><input type="checkbox"
												id="chkAll" name="chkAll" class="check-box not_checked" />
											</td> -->
											<th width="21%" class="biaoti">
											设备名称</th>
											<th width="15%" class="biaoti">
											设备类型</th>
											<th width="15%" class="biaoti">
											设备IP</th>
											
											<th width="10%" class="biaoti">
											设备状态</th>
											<th width="10%" class="biaoti">
											设备位置</th>
											<th width="*" class="biaoti">
											设备描述</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="deviceList" status="stat">
											<input type="hidden" name="deviceId" id="deviceId"
												value="${device_id}" />
											<tr>
												<%-- <td class="biaocm" valign="middle"><input
													type="checkbox" name="ids" id="${device_id}" value="${device_id}"
													class="check-box" />
												</td> --%>
												<%-- <td valign="middle"><a
													href="${ctx}/device/editDevice.action?id=${device_id}">${device_name}</a>
												</td> --%>
												<td valign="middle">${device_name}
												</td>
												<td valign="middle">${deviceCategory.deviceCategory_name }</td>
												<td valign="middle">${device_ip }</td>
												
												<td valign="middle" align="center">
													<c:if test="${device_status==0}">不在线</c:if> 
													<c:if test="${device_status==1}">在线</c:if>
													<c:if test="${device_status==2}">轻微告警</c:if>
													<c:if test="${device_status==3}">一般告警</c:if>
													<c:if test="${device_status==4}">重要告警</c:if>
													<c:if test="${device_status==5}">严重告警</c:if>
												</td>
												<td valign="middle" align="center">
													<c:if test="${device_mark==0}">内网设备</c:if> 
													<c:if test="${device_mark==1}">soc间设备</c:if>
												</td>

												<td valign="middle">${device_describe}</td>
											</tr>
										</s:iterator>
									</tbody>
									<tr>
										<td colspan="6" width="100%"><jsp:include
												page="/pages/commons/page.jsp"></jsp:include><br></td>
									</tr>

								</table>
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
	</s:form>

	<!-- ext query from -->
	<s:form action="queryAllDevice.action" namespace="/device" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="selectDeviceName" id="selectDeviceName" />
		<s:hidden name="selectDeviceCategory" id="selectDeviceCategory" />
		<s:hidden name="selectDeviceIp" id="selectDeviceIp" />
		<s:hidden name="selectDeviceMac" id="selectDeviceMac"/>
		<s:hidden name="selectDeviceStatus" id="selectDeviceStatus"/>
		<s:hidden name="selectDeviceLocation" id="selectDeviceLocation"/>
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
				<td width="25%">设备名称:</td>
				<td><input id="selDeviceName" name="selDeviceName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>设备类型:</td>
				<td>
				<select id="selDeviceCategory" style="width:250px;" name='selDeviceCategory' onkeypress="if(event.keyCode==13)extQuery();" >
				<option value="">--请选择--</option>
				<s:iterator value='deviceCategoryList' status='stat'>
					<option   value='${deviceCategory_id}'> ${deviceCategory_name}
					</option >
			    </s:iterator>
				</select>
			</tr>
			<tr>
				<td>设备IP:</td>
				<td><input id="selDeviceIp" name="selDeviceIp" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>设备状态:</td>
				<td>
				<select id="selDeviceStatus" style="width:250px;" name='selDeviceStatus' onkeypress="if(event.keyCode==13)extQuery();" >
				<option value="">--请选择--</option>
				<option value="1">在线</option>				
				<option value="0">不在线</option>
				<option value="3">告警</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>设备位置:</td>
				<td><select id="selDeviceLocation" style="width:250px;" name='selDeviceLocation' onkeypress="if(event.keyCode==13)extQuery();" >
				<option value="">--请选择--</option>
				<option value="1">外网</option>				
				<option value="0">内网</option>
				</select>
				</td>
			</tr>
			<tr>
			<td><span id="check_creatorIP_msg"></span></td>
			</tr>
		</table>
	</div>
	
</body>
</html>
