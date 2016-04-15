<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>设备信息的添加和修改页面</title>
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet"
	type="text/css">
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css
	rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css
	rel=stylesheet>

<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<%-- <script type='text/javascript' src="${ctx}/scripts/filterpage.js"></script> --%>
<style type="text/css">
.tdleft {
	width: 20%;
	text-align: right;
}

td {
	font-size: 12px;
}
</style>
<script type="text/javascript">

	jQuery(document).ready(function() {
		$('#dialog-device').dialog({
			autoOpen : false,
			width : 420,
			height : 450,
			buttons : {
				"确定[Enter]" : function() {
					addOk('deviceSelect','dialog-device','chk-asset');
				},

				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
	});
	
	function addOk(typeSelect, checkBoxDiv,checkBoxName) {
		 // 点击确定按钮时，先把选择框中的内容清空
		$("#"+typeSelect).empty();
		$("#"+checkBoxDiv+" input[type='checkbox'][name="+checkBoxName+"]:checked").each(
		   function() {
			 var node = $(this).parent().next().children("a");
		        $("#"+typeSelect).append("<option value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
		         }
		);
		$("#"+checkBoxDiv).dialog('close');
	}
	
	

	function back() {
		location.href = "${ctx}/device/queryAllDevice.action";
	}
	function save() {
		$('#deviceIds').val('');
	    $("#deviceSelect").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#deviceIds').val($('#deviceIds').val() + $(this).val() + ",");
		}
		});
		//解决直接点击保存没验证
		var dName=$("#deviceName").val();
		if(dName.length==0){
			alert('请输入设备名称');
			return;
		}
		var dName=$("#deviceIp").val();
		if(dName.length==0){
			alert('请输入设备Ip');
			return;
		}
		var dName=$("#deviceMac").val();
		if(dName.length==0){
			alert('请输入设备Mac');
			return;
		}
		if(nameFlag){
			alert('请检查设备名称');
			return;
		}
		if(ipFlag){
			alert('IP地址不合法');
			return;
		}
		if(existflag){
			alert('请检查MAC地址');
			return;
		}
		$("#deviceForm").submit();
	}
	//验证设备名称
	var nameFlag=false;
	function checkDeviceName(){

		var dName=$("#deviceName").val();
		if(dName !=""){
			if(dName.length >50){
			nameFlag=true;
			$("#div_deviceName").addClass("spanred");
			$("#div_deviceName").html("设备名称长度不能大于50个汉字");
			}else{
				$("#div_deviceName").html("");
				nameFlag=false;
			}
		}else{
			nameFlag=true;
			$("#div_deviceName").addClass("spanred");
			$("#div_deviceName").html("设备名称不能为空！");
		}
	}
	//验证IP的合法性
	var ipFlag;
	function checkDeviceIp(){
		var ip = $("#deviceIp").val();
		if (ip != "") {
				var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
				var result = re1.test(ip);
				if (result) {
					if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
						$("#div_ipEnd").addClass("spanred");
						$("#div_ipEnd").html("ip不合法");
						ipFlag = true;
					} else {
						$("#div_ipEnd").html("");
						ipFlag=false;
					}
				} else {
					$("#div_ipEnd").addClass("spanred");
					$("#div_ipEnd").html("ip不合法");
					ipFlag = true;
				}
			
		} else {
			ipFlag = true;
			$("#div_ipEnd").addClass("spanred");
			$("#div_ipEnd").html("  IP不能为空！");
		}
	}
	
	//验证MAC唯一
	var existflag;
	function checkDeviceMac() {
		var addressName = $("#deviceMac").val();
		if(addressName != ""){
			
			var regex=addressName.match(/^($)|(([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}$)/); 
			if(regex==null){
				$("#check_loginname_msg").addClass("spanred");
				$("#check_loginname_msg").html("  请输入正确的MAC地址");
				existflag = true;
			}else{
				$("#check_loginname_msg").html("");
				existflag = false
			}
		}else{
			existflag = true;
			$("#check_loginname_msg").addClass("spanred");
			$("#check_loginname_msg").html("  MAC不能为空！");
		}
	}
	
	//加载设备列表方法
	function showDeviceDialog() {
		$("#dialog-device").dialog("open");
	}
	
		//点击链接选择他左面的box
	function checkLeftBox(id){
	//	alert(id);
		if ($("#"+id).attr("checked") == false) {	
		$("#"+id).attr("checked",true);	
		}else{
		$("#"+id).attr("checked",false);	
			}
	}
	
		
		

//检测设备状态
function checkIsOnline(){
	var deviceIp=$("#deviceIp").val();
	if(deviceIp==""){
		alert("请输入设备Ip");
		return;
	}
	$.ajax({
			      type : "post",
				   url : "${ctx}/device/queryDeviceStatus.action",
				   dataType : "json",
				   data : "&deviceIp="+deviceIp,
				   success : function(result) {
					  if(1==result.resultByAjax){
					  	$("#check_deviceIsOnline").html("该设备在线");
					  	$("#deviceStatus").val(1);
					  }else{
					  	$("#check_deviceIsOnline").html("该设备不在线");
					  	$("#deviceStatus").val(0);
					  }
				   }
			 }); 
}

//改变设备位置时触发
function changeValue(data){
	$.ajax({
			      type : "post",
				   url : "${ctx}/device/queryAssociationDevices.action?datetime="+new Date(),
				   dataType : "json",
				   data : "&deviceMark="+data.value,
				    success : function(result) {
				    //alert(result.resultByAjax);
				    //$("#assList").val(result.resultByAjax);
				  //  $("#assList").empty().append(result.resultByAjax);
				    /* alert(result.resultByAjax.length);
					  $("#assList").val(result.resultByAjax); */
					  /* var temp="";
					  var relList=result.resultByAjax;
					  alert(relList.length);
					  temp=temp+"<s:iterator value='"+relList+"'>";
					  temp=temp+"<tr style='line-height: 25px;'>";
					  temp=temp+"<td width='20%' align='center'><input type='checkbox' name='chk-asset' id='chk-"+${device_id}+"' value='" +${device_id}+"' /></td>";
					  //temp=temp+"<td width='300px'><a style='color: #555555' onclick='"+checkLeftBox+"(chk-"+${device_id} +")'>"+${device_name}+"</a></td>";
					  temp=temp+"</tr>";
					  temp=temp+"</s:iterator>";
					  alert(temp); */
					  $("#dialog-device").html(result.resultByAjax);
				   }
			 }); 
}

function changeValue(){
	var deviceMark=$("#deviceMarkId").val();
	var table=$("#dlg-table-asset");
	$("#header").nextAll("tr").remove();
	var totalRecord=0;
	$.ajax({
			      type : "post",
				   url : "${ctx}/device/queryAssociationDevices.action?datetime="+new Date(),
				   dataType : "json",
				   data : "&deviceMark="+deviceMark,
				    success : function(result) {
				    var deList=result.resultByAjax;
				  	totalRecord=deList.length;
				  	for(var i=0;i<deList.length;i++){
				  		var deviceId="chk-"+deList[i].device_id;
				  		var tr = $("<tr></tr>").attr("lineHeight","25px");
						var td1 = $("<td></td>").attr("width", "20%").attr("align", "center");
						$("<input type='checkbox' name='chk-asset' class='check-box' />").attr("id", deviceId).val(deList[i].device_id).appendTo(td1);
						var td2 = $("<td></td>").attr("width", "300px");
						$("<a style='color: #555555' href='javascript:void(0);'></a>").html(deList[i].device_name).click(function() {
							checkLeftBox(deviceId);
						}).appendTo(td2);
						td1.appendTo(tr);
						td2.appendTo(tr);
						tr.appendTo(table);
				  	}
				  	goPage(1,4,'dlg-table-asset','barcon',totalRecord);
				  	initCheckBox('deviceSelect','chk-asset');
				   }
			 }); 
	    
}  
//全选及反选
	$("#asset-checkAll").live("click", function(event) {
		if ($("#asset-checkAll").attr('checked')) {
			$(".check-box").attr('checked', true);
		} else {
			$(".check-box").attr('checked', false);
		}
	}); 
//分页
function goPage(pno,psize,tableId,afterTableDivId,totalRecord){
var itable = document.getElementById(tableId);
//var num = $('#'+tableId+' tr').length;//表格行数
var num=totalRecord;
var totalPage = 0;//总页数
var pageSize = psize;//每页显示行数
if((num)%pageSize == 0){   
   totalPage=parseInt((num)/pageSize);   
   }else{   
   totalPage=parseInt((num)/pageSize)+1;   
   }   
var currentPage = pno;//当前页数
var startRow = (currentPage - 1) * pageSize+1;//开始显示的行   
   var endRow = currentPage * pageSize;//结束显示的行   
   endRow = (endRow >= num)? num : endRow;
//前三行始终显示
 //前三行始终显示
   for(var i=0;i<1;i++){
   var irow = itable.rows[i];
   irow.style.display = "block";
   }
	   for(var i=1;i<=num;i++){
	   var irow = itable.rows[i];
	   if(i>=startRow&&i<=endRow){
	   irow.style.display = "block";
	   }else{
	   irow.style.display = "none";
	   }
	   }
//var pageEnd = document.getElementById("pageEnd");
var tempStr = "共"+(num)+"条记录 分"+totalPage+"页 当前第"+currentPage+"页";
if(currentPage>1){
tempStr += "<a href=\"javascript:goPage("+(currentPage-1)+","+psize+",'"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">上一页</a>";
}else{
tempStr += "上一页";
}
if(currentPage<totalPage){
tempStr += "<a href=\"javascript:goPage("+(currentPage+1)+","+psize+",'"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">下一页</a>";
}else{
tempStr += "下一页";
}
if(currentPage>1){
tempStr += "<a href=\"javascript:goPage("+(1)+","+psize+",'"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">首页</a>";
//goPage("+(totalPage)+","+psize+","+tableId+","+afterTableDivId+")
}else{
tempStr += "首页";
}
if(currentPage<totalPage){
tempStr += "<a href=\"javascript:goPage("+(totalPage)+",'"+psize+"','"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">尾页</a>";
}else{
tempStr += "尾页";
}
document.getElementById(afterTableDivId).innerHTML = tempStr;
}
</script>

</head>

<body style="margin: 0">
	<s:form action="updateDevice" method="post" namespace="/device"
		theme="simple" id="deviceForm">
		<input type="hidden" id="id" name="id" value="${device.device_id}">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<!-- information area -->
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td colspan="4" class='r2titler' style="font-size: 12px">设备信息编辑</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="tdleft"><span class="spanred">*&nbsp;</span>设备名称：&nbsp;</td>
								<td style="text-align:left;"><input type="text"
									style="width:250px" id="deviceName" width="99%"
									name="deviceName" maxlength="500" value="${device.device_name}" onblur="checkDeviceName()"/>
									&nbsp;<span id="div_deviceName" ></span>
								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="tdleft"><span class="spanred">*&nbsp;</span>设备类型：&nbsp;</td>
								<td style="text-align:left">
									<%-- <input type="text" style="width:250px"  id="sysName" width="99%" name="sysName"  maxlength="500" value="${dataAssets.sysName}" readonly="readonly" onclick="$('#goToSearch').val('');employeeDlg()"/> --%>
									<select style="width: 250px;" name="deviceCategoryName">
										<s:iterator value="deviceCategoryList">
											<option value="${ deviceCategory_name}"
												<c:if test="${deviceCategory_name==deviceCategoryName }">selected="selected"</c:if>>
												${deviceCategory_name }</option>
										</s:iterator>
								</select></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr>
								<td class="tdleft"><span class="spanred">*&nbsp;</span>设备IP：&nbsp;</td>

								<td style="text-align:left"><input type="text"
									id="deviceIp" name="deviceIp" maxlength="50"
									value="${device.device_ip}" style="width:250px"
									onblur="checkDeviceIp()" />&nbsp;<span id="div_ipEnd" ></span></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr>
								<td class="tdleft"><span class="spanred">*&nbsp;</span>设备MAC：&nbsp;</td>
								<td style="text-align:left"><input type="text"
									style="width:250px" id="deviceMac" maxlength="50"
									name="deviceMac" value="${device.device_mac}" onblur="checkDeviceMac()"/>
									<span id="check_loginname_msg"></span>&nbsp;&nbsp;<font color="red"><span>例如：00-00-00-00-00-00</span></font>
								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="tdleft"><span class="spanred">*&nbsp;</span>设备所属拓扑：&nbsp;</td>
								<td style="text-align:left">
									<%-- <input type="text" style="width:250px"  id="sysName" width="99%" name="sysName"  maxlength="500" value="${dataAssets.sysName}" readonly="readonly" onclick="$('#goToSearch').val('');employeeDlg()"/> --%>
									<select style="width: 250px;" name="deviceMarkId" id="deviceMarkId">
											<option value="0" <c:if test="${0==deviceMarkID }">selected="selected"</c:if>>内网</option>
											<option value="1" <c:if test="${1==deviceMarkID }">selected="selected"</c:if>>外网</option>
								</select></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="tdleft">关联设备：&nbsp;</td>
								<td width="60%" align="left" >
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="deviceSelect" name = "deviceSelect" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="saveDeviceList" status="stat">
																		<option value="${device_id}" id="deviceTypeid">${device_name}</option>
																</s:iterator>
														</select></td><s:hidden name="deviceIds" id="deviceIds" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="changeValue();showDeviceDialog();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('deviceSelect','chk-asset');" />
														</td>
													</tr>
												</table></td>
							</tr>

							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
								<td class="tdleft">设备描述：&nbsp;</td>
								<td colspan="3" style="text-align: left;font-size: 12px;">
									<textarea rows="3" style="width:40%" id="devDescription"
										name="devDescription">${device.device_describe}</textarea></td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr>
									<td class="tdleft">检测设备在线情况：&nbsp;</td>
									<%-- <td colspan="3" style="text-align: left;font-size: 12px"><textarea rows="3"
												style="width:40%" id="dataRemarks" name="dataRemarks" >${dataAssets.dataRemarks}</textarea>
										&nbsp;</td> --%>
									<td colspan="3" style="text-align: left;font-size: 12px">
										<input type="button" value="检测" class="btnyh" onclick="checkIsOnline();" /> 
										<span id="check_deviceIsOnline"></span>
										<input type="hidden" id="deviceStatus" name="deviceStatus"/>
									</td>
							</tr> 
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

						</table>
					</div></td>
			</tr>
			<tr>
				<td>

					<div>

						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td align="right"><input type="button" value="取消"
									class="btnyh" onclick="back()" /> <input type="button"
									value="保存" class="btnyh" id="btnSub" onclick="save()" /></td>
							</tr>
						</table>
					</div></td>
			</tr>

		</table>
	</s:form>
	<div id="dialog-device" title="关联设备" >
	
		 <table id="dlg-table-asset" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="overflow:auto">		

             <tr id="header" height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="asset-checkAll" name="chkAll-user"
					class="check-box-deviceName not-checked-deviceName" />
				</td>
				<td width="100%" align="center" class="biaoti">关联设备</td>
			</tr>
<%--          	<s:iterator value="associationDeviceList">
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-asset" id="chk-${device_id}"
						value="${device_id}" /></td>
					<td width="c"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-${device_id}')">${device_name}</a>
					</td>
				</tr>
			</s:iterator> --%>
		</table>
		
		<table width="80%" align="right">
						<tr>
				<td>
					<div id="barcon" name="barcon"></div>
			</td>
		</tr>
		</table> 

	</div>
	
</body>
</html>
