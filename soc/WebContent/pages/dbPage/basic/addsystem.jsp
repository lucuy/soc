<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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


<html>
<head>
<base href="<%=basePath%>">

<title>添加信息系统</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<script type="text/javascript">
	$(function() {
	    $("form").keypress(function(e){  
	        if(e.keyCode == 13) {  
	            e.preventDefault();  
	        }  
	    });
	});
	 function saveUnitBaseinfo() {
		    var btnSub=document.getElementById("btnSub");
	        btnSub.disabled="disabled";
		if (!checkName) {
			return false;
		};
		if ("" == $.trim($("#sysName").val())) {
			alert("系统名称不能为空");
			$("#sysName").focus();
			return false;
		} else if ($.trim($("#sysName").val()).length > 20) {
			alert("系统名称不能大于20个字符");
			$("#sysName").focus();
			return false;
		} 	else if ("" == $.trim($("#sysId").val())) {
			alert("系统编号不能为空");
			$("#sysId").focus();
			return false;
		} else if (document.getElementById("ywlxrdo").checked
				&& "" == $("#ywlxval").val()) {
			//业务类型选择了其他
			alert("请输入该系统的业务类型");
			$("#ywlxval").focus();
			return false;
		} else if (document.getElementById("ywlxrdo").checked
				&& $("#ywlxval").val().length > 20) {
			//业务类型选择了其他
			alert("该系统的业务类型名称不能大于20个字符");
			$("#ywlxval").focus();
			return false;
		} else if (document.getElementById("ksrdo").checked
				&& "" == $("#ksrval").val()) {
			//跨省

			alert("请输入该系统服务范围跨省个数！");
			$("#ksrval").focus();
			return false;
		} else if ($("#ksrval").val() >2862) {
			alert("全国有省级34个，地级333个，县级2862个,请重新输入！");
			$("#ksrval").focus();
			return false;
		}else if (document.getElementById("kdrdo").checked
				&& "" == $("#kdval").val()) {
			//跨地

			alert("请输入该系统服务范围跨地区个数！");
			$("#kdval").focus();
			return false;
		} else if (document.getElementById("fwfwdo").checked
				&& "" == $("#fwfwval").val()) {
			//其他

			alert("请输入该系统服务范围");
			$("#fwfwval").focus();
			return false;
		} else if (document.getElementById("fwfwdo").checked
				&& $("#fwfwval").val().length > 20) {
			//其他

			alert("该系统服务范围名称不能大于20个字符");
			$("#fwfwval").focus();
			return false;
		}else if (document.getElementById("dxrdo").checked
				&& "" == $("#dxrval").val()) {
			//服务范围
			alert("请输入该系统服务对象");
			$("#dxrval").focus();
			return false;
		} else if (document.getElementById("dxrdo").checked
				&& $("#dxrval").val().length > 4) {
			//服务范围
			alert("该系统服务对象不能大于4个字符");
			$("#dxrval").focus();
			return false;
		}else if(document.getElementById("busDescription").value.length>1000){
		      alert("您输入的业务描述长度超过1000!!!");
		      return false;
	    } else {
	    	btnSub.disabled="";
	    	return true;
	    }

	}

	function setEnable() {
		//业务类型其他输入框 
		if (document.getElementById("ywlxrdo").checked) {
			document.getElementById("ywlxval").disabled = false;
		} else {
			document.getElementById("ywlxval").disabled = true;
			document.getElementById("ywlxval").value = "";
		}
	}
	//服务范围
	function setfwfw() {

		//服务范围 跨省 
		if (document.getElementById("ksrdo").checked) {
			document.getElementById("ksrval").disabled = false;
		} else {
			document.getElementById("ksrval").disabled = true;
			document.getElementById("ksrval").value = "";
		}

		//服务范围 跨地
		if (document.getElementById("kdrdo").checked) {
			document.getElementById("kdval").disabled = false;
		} else {
			document.getElementById("kdval").disabled = true;
			document.getElementById("kdval").value = "";
		}

		//服务范围 其他
		if (document.getElementById("fwfwdo").checked) {
			document.getElementById("fwfwval").disabled = false;
		} else {
			document.getElementById("fwfwval").disabled = true;
			document.getElementById("fwfwval").value = "";
		}

	}
	//服务对象
	function setdx() {

		if (document.getElementById("dxrdo").checked) {
			document.getElementById("dxrval").disabled = false;
		} else {
			document.getElementById("dxrval").disabled = true;
			document.getElementById("dxrval").value = "";
		}

	}
	
	
	function setDisab(){
	var btnSub=document.getElementById("btnSub");
        btnSub.disabled="disabled";
	}
	
	function checkName() {
		var sysName = $.trim($("#sysName").val());
		if (sysName == "") {
			alert("系统名称不能为空！");
			return false;
		} else {
			$.post('${ctx}/system/queryAjaxSysName.action', {
				sysName : sysName
			}, function(data) {
				if (data > 0) {
				    var btnSub=document.getElementById("btnSub");
			        btnSub.disabled="disabled";
					$("#sysName").val("");
					alert("系统名称已存在！");
					return false;
				} else {
				    var btnSub=document.getElementById("btnSub");
			        btnSub.disabled="";
					return true;
				};
			});
		}
	}
	//查询系统编号
	function checkSysId() {
	var sysId = $.trim($("#sysId").val());
	 if($("#sysId").val()==""){
	       return ;
	    }
	    //alert();
		/* var re =/^[a-zA-Z0-9]+$/g;
		if (!(re.test(sysId))) {
		   document.getElementById("sysId").value="";
		    //varStr.focus(); 
			alert("请不要输入特殊字符！");
			return false;
		}  */
		
		$.post('${ctx}/system/queryAjaxSysId.action', {
			sysId : sysId
		}, function(data) {
			if (data == 'ERROR') {
				alert("系统编号已存在");
				$("#sysId").val("");
				$("#sysId").focus();
				return false;
			}else{
			var btnSub=document.getElementById("btnSub");
			btnSub.disabled="";
			}
		});
	}
	
	function checkNum(varStr) {
	var msgNum = /^\d+$/;
		if (!msgNum.test(varStr.value)) {
			alert("请输入数字");
			varStr.value="";
			return false;
			
			
		}
	}
</script>
</head>

<body style="margin-top: 2px;font-size: 12px">
	
	<form id="basicform" name="cpUnitBaseinfoForm" method="post" onsubmit="return saveUnitBaseinfo();"
		action="${ctx}/system/insert.action" enctype="multipart/form-data">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 7px; margin-top: 0px">
			<tr>
				<td width="70%" valign="top">
							<!-- information area -->
							<div class="framDiv">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"   >
                                 <tr>
									<td colspan="4" class='r2titler' style="font-size: 12px">信息系统编辑</td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td  align="right" style="font-size: 12px" >系统名称：<font color="#ff0000">*</font></td>
									<td  style="text-align:left"><input type="text" maxlength="50" id="sysName"
										name="system.sysName" style="width:200px" onblur="yanzheng1(this);checkName()"/>
									</td>
									<td  align="right" style="font-size: 12px; text-align: right;">系统编号：<font color="#ff0000">*</font></td>
 
									<td style="text-align:left"><input type="text" maxlength="50" id="sysId"
										name="system.sysId" style="width:200px" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" onfocus="setDisab()" onblur="checkSysId()" />
									</td>
									</tr>
								
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
								<td  align="right" style="font-size: 12px;">业务类型：&nbsp;</td>
								<td  colspan="3" style="text-align:left;font-size: 12px">
									<input type="radio" name="system.busType" value="1" onclick="setEnable()" />生产作业
									<input type="radio" name="system.busType" value="2" onclick="setEnable()" />指挥调度 
									<input type="radio" name="system.busType" value="3" onclick="setEnable()" />管理控制 
									<input type="radio" name="system.busType" value="4" onclick="setEnable()" />内部办公
									<input type="radio" name="system.busType" value="5" onclick="setEnable()" />公众服务 
									<input type="radio" name="system.busType" id="ywlxrdo" value="9" onclick="setEnable()" />其它 
									<input type="text" class="showBorder" maxlength="50" id="ywlxval" name="system.otherBusType" style="width:100px;" disabled="disabled" onblur="yanzheng1(this)"/>
								</td>
									
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td align="right" style="font-size: 12px">业务描述：&nbsp;</td>
									<td  style="text-align:left;" colspan="3">
										<textarea  cols="31" rows="10" style="width:99%" name="system.busDescription" id="busDescription" maxlength="50"></textarea>
									</td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td align="right" style="font-size: 12px">服务范围：&nbsp;</td>
									<td colspan="3" style="text-align: left;font-size: 12px;"><input
										type="radio" name="system.serArea" value="10"
										onclick="setfwfw()" />全国 <input type="radio"
										name="system.serArea" value="11" onclick="setfwfw()"
										id="ksrdo" />跨省（区、市） 跨 <input type="text" class="showBorder"
										name="system.proTotal"  maxlength="4" id="ksrval"
										disabled="disabled" onblur="checkNum(this)" style="width:50px"  />个&nbsp;&nbsp;
										<input type="radio" name="system.serArea" value="20"
										onclick="setfwfw()" />全省（区、市） <input type="radio"
										name="system.serArea" value="21" onclick="setfwfw()"
										id="kdrdo" />跨地（市、区） 跨 <input type="text" class="showBorder"
										name="system.cityTotal"  class="txt" maxlength="4"
										disabled="disabled" id="kdval" onblur="checkNum(this)" style="width:50px"/>个&nbsp;&nbsp;
										<input type="radio" name="system.serArea" value="30"
										onclick="setfwfw()" />地（市、区）内  <input type="radio"
										name="system.serArea" value="99" onclick="setfwfw()"
										id="fwfwdo" />其它 <input type="text" class="showBorder"
										name="system.otherArea" maxlength="50" id="fwfwval"
										disabled="disabled" style="width:100px" onblur="yanzheng1(this)"/></td>
								</tr>
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td align="right" style="font-size: 12px">服务对象：&nbsp;</td>
									<td colspan="3" style="text-align: left;font-size: 12px">
									<input type="radio" name="system.serObj" value="1" onclick="setdx()" />单位内部人员
									<input type="radio" name="system.serObj" value="2"onclick="setdx()" />社会公众人员 
									<input type="radio"name="system.serObj" value="3" onclick="setdx()" />两者均包括  <input
										type="radio" name="system.serObj" value="9" onclick="setdx()"
										id="dxrdo" /> 其他 <input type="text" name="system.otherObj"
										class="showBorder" maxlength="50" style="width:100px"
										id="dxrval" disabled="disabled"  onblur="yanzheng1(this)"/></td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								
							</table>
							</div>
					</td>
			</tr>
			<tr>
				<td>
						<table width="100%">
							<tr >
								<td width="*" align="right">
								   <input  type="button" class="btnyh" value="取消"onClick="window.location.href='${ctx}/pages/dbPage/basic/manager.jsp';" />
								   <input  type="submit" class="btnyh" id="btnSub" value="保存" />
								 </td>
							</tr>
						</table>

					</td>
			</tr>
		</table>
	</form>
</body>
</html>
