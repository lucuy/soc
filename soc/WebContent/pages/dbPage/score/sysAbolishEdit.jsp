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

<title>系统废止信息</title>

<meta http-equiv="Cache-Control" content="private" />
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/basic/basic.css" type=text/css rel=stylesheet>
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js"
	type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<script type="text/javascript">
	function save() {
		var btnSub=document.getElementById("btnSub");
        btnSub.disabled="disabled";
		var sysDescription = document.getElementById("sysDescription");
		//var sysAccess=document.getElementById("fsysAccess");
		var devDescription = document.getElementById("devDescription");
		//var devAccess=document.getElementById("fdevAccess");
		var storDescription = document.getElementById("storDescription");
		//var storAccess=document.getElementById("fstorAccess");
		if ($.trim(sysDescription.value) == "") {
			alert("请填写信息删除证明描述");
			btnSub.disabled="";
			return;
		}
		if(sysDescription.value.length>1000){
		  alert("您输入的信息删除证明描述长度超过1000！！！");
		  btnSub.disabled="";
		  return;
		}
		/*  if( $.trim(sysAccess.value)==""){
		   alert("请选择上传文件！");
		    return ;
		 } */
		if ($.trim(devDescription.value) == "") {
			alert("请填写设备清除证明描述");
			btnSub.disabled="";
			return;
		}
		if(devDescription.length>1000){
		  alert("您输入的设备清除证明描述长度超过1000！！！");
		  btnSub.disabled="";
		  return;
		}
		/* if($.trim(devAccess.value)==""){
		  alert("请选择上传文件！");
		   return ;
		}   */
		if ($.trim(storDescription.value) == "") {
			alert("请填写存储清除证明描述");
			btnSub.disabled="";
			return;
		}
		if(storDescription.value.length>1000){
		  alert("您输入的存储清除证明描述长度超过1000！！！");
		  btnSub.disabled="";
		  return;
		}
		/* if($.trim(storAccess.value)==""){
		   alert("请选择上传文件！");
		    return ;
		 } */
		document.editAbolish.target = "mainFrame";
		document.editAbolish.action = "";
		
		//var formName = document.getElementById("editAbolish");
		//formName.action = "${ctx}/sysAbolish/sysAbolishEdit.action";
		//formName.submit();
		document.editAbolish.action = "${ctx}/sysAbolish/sysAbolishEdit.action";
		document.editAbolish.submit();
	}
	function upsys(val) {
		/* var divornot = document.getElementById("msg");
		if (divornot != null) {
			alert("请先上传您要替换的附件，再进行下一步操作！");
			return false;
		} */
		if(val=="1"){
			if (!confirm('确定替换原文件吗？')) {
				return;
			}
		}
		var filesDiv = document.getElementById("divsys_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fsysAccess";
		fileInput.id = "fsysAccess";
		fileInput.style.width = "300px";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";
		

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fsysAccess");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.editAbolish.target = "hiddenframe";
			document.editAbolish.action = "${ctx}/sysAbolish/upsysabfile.action";
			document.editAbolish.submit();
			var divornot = document.getElementById("msg");
		if (divornot != null) {
			divornot.removeAttribute("id");
		} 
		var span = document.getElementById("msg1");
		var divname = document.createElement("div");
		divname.id = "msg";
		span.appendChild(divname);
		};
		 
		var upbuh = document.getElementById("fsysAccessFileNametext");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton1");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function updev(val) {
		if(val=="1"){
			if (!confirm('确定替换原文件吗？')) {
				return;
			}
		}
		var filesDiv = document.getElementById("divdev_main");
		var fileInput = document.createElement("input");
		fileInput.type = "file";
		fileInput.name = "fdevAccess";
		fileInput.id = "fdevAccess";
		fileInput.style.width="300px";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";
		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fdevAccess");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.editAbolish.target = "hiddenframe";
			document.editAbolish.action = "${ctx}/sysAbolish/updevabfile.action";
			document.editAbolish.submit();
			var divornot = document.getElementById("msg");
		if (divornot != null) {
			divornot.removeAttribute("id");
		}
		var span = document.getElementById("msg2");
		var divname = document.createElement("div");
		divname.id = "msg";
		span.appendChild(divname);
		};
		
		var upbuh = document.getElementById("fdevAccessFileNametext");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton2");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function upstore(val) {
		if(val=="1"){
			if (!confirm('确定替换原文件吗？')) {
				return;
			}
		}
		var filesDiv = document.getElementById("divstore_main");
		var fileInput = document.createElement("input");

		fileInput.type = "file";
		fileInput.name = "fstorAccess";
		fileInput.id = "fstorAccess";
		fileInput.style.width="300px";
		var upButton = document.createElement("input");
		upButton.type = "button";
		upButton.value = "上传文件";

		upButton.onclick = function upfile() {
			var frankAccess = document.getElementById("fstorAccess");
			if ($.trim(frankAccess.value) == "") {
				alert("请选择上传文件！");
				return;
			}
			document.editAbolish.target = "hiddenframe";
			document.editAbolish.action = "${ctx}/sysAbolish/upstorabfile.action";
			document.editAbolish.submit();
			var divornot = document.getElementById("msg");
		if (divornot != null) {
			divornot.removeAttribute("id");
		}
		var span = document.getElementById("msg3");
		var divname = document.createElement("div");
		divname.id = "msg";
		span.appendChild(divname);
		};
		
		var upbuh = document.getElementById("fstorAccessFileNametext");
		filesDiv.removeChild(upbuh);
		var upbu = document.getElementById("updatebutton3");
		filesDiv.removeChild(upbu);
		var divname = document.createElement("div");
		divname.appendChild(fileInput);
		divname.appendChild(upButton);
		filesDiv.appendChild(divname);
	}
	function callback(msg) {
		document.getElementById("msg").innerHTML = "<font color='red'>" + msg
				+ "</font>";
	}
	function callback2(name,msg) {
		document.getElementById("msg").innerHTML +=  "<input type='hidden' name='"+name+"' value='"+msg+"'>";
	}
	
</script>
</head>

<body>
	<iframe name="hiddenframe" id="hiddenframe" style="display:none"></iframe>
	<form name="editAbolish" action="" method="post" id="frm"
		enctype="multipart/form-data">
		<input type="hidden" name="pkSysInfo"
			value="<s:property value="sysAbolish.pkSysInfo"/>" /> <input
			type="hidden" name="sysAbolishId"
			value="<s:property value="sysAbolish.sysAbolishId"/>" />

		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px;">
			<tr>
				<td>
					<div class="caozuobox" >

						<input type="button" value="保存" style="margin-top: 5px;margin-left: 10px;" onclick="save()" id="btnSub"  class="btnstyle"  />
						<input type="button" value="返回" onclick="history.back()" style="margin-top: 5px;"
							 class="btnstyle"  />

					</div></td>
			</tr>
			<tr>
			<td height="5px;"></td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<!-- information area -->
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="table-userManage">
									<thead>
										<tr >
											<th width="10%" class="biaoti" style="font-size: 12px;" align="center">相关证明</th>
											<th width="50%" class="biaoti" style="font-size: 12px;" align="center">相关描述</th>
											<th width="40%" class="biaoti" style="font-size: 12px;" align="center">相关附件</th>
										</tr>
									</thead>
									<tr>
										<td class="tdhead"
											style="text-align:center; background-color:#E1DEE4">信息删除证明：</td>
										<td><textarea name="sysDescription" id="sysDescription"
												style="width:100%" cols="5" onblur="yanzheng1(this)">${sysAbolish.sysDescription}</textarea>
										</td>
										<td>
											<%-- <input type="file" name="fsysAccess" id="sysAccess" value="${sysAbolish.sysAccess}" style="width:65%">
									<input type="button" value="上传" onclick="upsys();"/><span id="msg1"></span> --%>
											<div id="divsys_main">
												<input type="text" name="fsysAccessFileName" maxlength="200" id="fsysAccessFileNametext" value="<s:property value="sysAbolish.sysAccess"/>" readonly="readonly" style="width: 300px; "> 
												
												<c:if test="${sysAbolish.sysAccess!=null}">
												  <input type="button" value="替换原附件" class="btnstyle" onclick="upsys(1);" name="updatebutton1" id="updatebutton1" >
												</c:if>
												<c:if test="${sysAbolish.sysAccess==null}">
												  <input type="button" value="上传文件" class="btnstyle" onclick="upsys(0);" name="updatebutton1" id="updatebutton1">
												</c:if>
											</div> 
											<span id="msg1"></span>
										</td>
									</tr>
									<tr>
										<td class="tdhead"
											style="text-align:center; background-color:#E1DEE4">设备清除证明：</td>
										<td><textarea name="devDescription" id="devDescription"
												style="width:100%" cols="5" onblur="yanzheng1(this)">${sysAbolish.devDescription}</textarea>
										</td>
										<td>
											<%-- <input type="file" name="fdevAccess" id="devAccess" value="${sysAbolish.devAccess}" style="width:65%">
									<input type="button" value="上传" onclick="updev();"/><span id="msg2"></span> --%>
											<div id="divdev_main">
												<input type="text" name="fdevAccessFileName" maxlength="200"  id="fdevAccessFileNametext" value="<s:property value="sysAbolish.devAccess"/>" readonly="readonly" style="width:300px">
											    <c:if test="${sysAbolish.devAccess!=null}"> 
												 <input type="button" value="替换原附件" onclick="updev(1);" name="updatebutton2" class="btnstyle" id="updatebutton2">
											    </c:if>
											    <c:if test="${sysAbolish.devAccess==null}">
											    <input type="button" value="上传附件" onclick="updev(0);" name="updatebutton2" class="btnstyle" id="updatebutton2">
											    </c:if>
											</div> <span id="msg2"></span>
										</td>
									</tr>
									<tr>
										<td class="tdhead"
											style="text-align:center; background-color:#E1DEE4">存储清除证明：</td>
										<td><textarea name="storDescription" id="storDescription"
												style="width:100%" cols="5" onblur="yanzheng1(this)">${sysAbolish.storDescription}</textarea>
										</td>
										<td>
											<%-- <input type="file" name="fstorAccess" id="storAccess" value="${sysAbolish.storAccess}"style="width:65%">
									<input type="button" value="上传" onclick="upstore();"/> --%>
											<div id="divstore_main">
												<input type="text" name="fstorAccessFileName" maxlength="200"  id="fstorAccessFileNametext" value="<s:property value="sysAbolish.storAccess"/>" readonly="readonly" style="width:300px"> 
												 <c:if test="${sysAbolish.storAccess!=null}">
												 <input type="button" value="替换原附件" class="btnstyle" onclick="upstore(1);" name="updatebutton3" id="updatebutton3">
												 </c:if>
												 <c:if test="${sysAbolish.storAccess==null}"> 
													<input type="button" value="上传附件" class="btnstyle" onclick="upstore(0);" name="updatebutton3" id="updatebutton3">
											      </c:if>
											</div> <span id="msg3"></span></td>
									</tr>
								</table>
							</div>
						</div>
					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
