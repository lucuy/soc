<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%-- <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> --%>
	<link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css">
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
jQuery(document).ready(function() {
	jQuery("#from").validationEngine();
});	


var isIE = !!document.all;
function AddOption(str)
{
	var voteoptions = document.getElementById(str);
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", str+"s");
	_input.setAttribute("maxlength", "30");
	_input.setAttribute("style", "width:270px; margin-top:8px; ");
	if(str=="systemOption"){
		_input.setAttribute("onblur", "check11(this.value,'系统品牌')");
	}
	if(str=="versionOption"){
		_input.setAttribute("onblur", "check11(this.value,'系统版本')");
	}
	if(str=="versionNoOption"){
		_input.setAttribute("onblur", "check11(this.value,'系统版本号')");
	}
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:");
	if(isIE) {
		_a.attachEvent("onclick", DelOption);
	} else {
		_a.addEventListener("click", DelOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
	
}
function DelOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}

function DelOptionNew(obj){
	$.ajax({
	      type: "post",
	      dataType: "json",
	      url:"${ctx}/assetSystem/deleteAssetSystem.action?ids="+obj,
	      success: function (data){
	      		
	      }
	 });
	location.reload(); 
}

function check(){
	
	
	if($("#assetSystemName").val()==""){
		alert("系统名称不能为空！");
		return ;
	}else{
		if(regex($('#assetSystemName').val(),"系统名称")==null){
			return ;
		}
	}
		if($("#text").val().length>200){
		alert("您输入的描述字数太多了");
		return ;
	}else{
		if(regex($('#text').val(),"系统备注")==null){
			return ;
		}
	}
		var systemOptions = document.getElementsByName("systemOptions");
		for(var i =0;i<systemOptions.length;i++){
		if(systemOptions[i].value==null||systemOptions[i].value==""){
			alert("系统品牌不能为空");
			return;
		}
		}
		var versionOptions = document.getElementsByName("versionOptions");
		for(var i =0;i<versionOptions.length;i++){
		if(versionOptions[i].value==null||versionOptions[i].value==""){
			alert("系统版本不能为空");
			return;
		}
		}
		var versionNoOptions = document.getElementsByName("versionNoOptions");
		for(var i =0;i<versionNoOptions.length;i++){
		if(versionNoOptions[i].value==null||versionNoOptions[i].value==""){
			alert("系统版本号不能为空");
			return;
		}	
		}
		if(!existFlag){
			document.getElementById("from").submit();
			}
}
function check11(value,name){
	
	var regex=value.match(/^([^`~!@#$%^&*()=|{}\\\[\]<>\/?~！@#￥……&*|{}【】。\"，？])*$/); 
	if(value!=null&&value!=""){
	if(regex==null){
		alert("您在"+name+"里的输入包含特殊字符！");
		
	}else{
		
	}
	}else{
		alert(name+"不能为空！");
		
	}
	
}

function regex(str,name){
	var regex=str.match(/^([^`~!@#$%^&*()=|{}\\\[\]<>\/?~！@#￥……&*|{}【】。\"，？])*$/); 
	if(regex==null){
		alert("您在"+name+"里的输入包含特殊字符！");
	}
	return regex;
}
var existFlag ;
function checkSystemName(){
	 $("#check_loginname_msg").empty();
		var assetSystemName=$("#assetSystemName").val();
		if('${assetSystem.assetSystemId}'==''){
			$.ajax({
				type : "post",
				url : "${ctx}/assetSystem/checkSystemName.action",
				dataType : "text",
				data : "&assetSystemName=" + assetSystemName,
				success : function(result) {
					if (result=='true') {
						$("#check_loginname_msg").html("该名称已占用，请更换新的名称！");
						
						existFlag = true;
					} else {
						
						existFlag = false;
					}
					
				}
			});
		}else{
			 existFlag = false;
		}
	
}
</script>
	

</head>

<body style="margin:8px;background-color : white; text-align:left;" >

<s:form action="insertAssetSystem.action" namespace="/assetSystem" method="post"  id ="from" onkeydown="if(event.keyCode==13){return false;}">
<s:token></s:token>
	<div class="framDiv"  style="width:550px;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class='r2titler' colspan='3'><b>系统信息 </b></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" width="25%" style="padding-left:15px;font-size:12px;"><span
								class="spanred">*</span>系统名称:</td>
							<td align="left" class="padding">
								<input id="assetSystemName" name="assetSystemName" maxlength="30"
								class="text-input" 
								value="${assetSystem.assetSystemName}"  style="width: 270px" onblur="checkSystemName();"
									 <c:if test="${assetSystem.assetSystemName != null}">readonly="readonly"</c:if>
								/>
								<input name="asId" type="hidden" value="<s:property value='assetSystem.assetSystemId'/>"/>
							<font style="color: red"><span id="check_loginname_msg"></span></font>
							</td>
							
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					
						<tr>
							<td  align="right" width="25%" style="padding-left:15px;font-size:12px;">
								<span class="spanred" style="padding-right:10px;"></span>系统备注:
							</td>
							<td align="left" class="padding">
								<textarea style="width:270px; height:50px;" id="text"
								name="assetSystemComment" ><s:property value="assetSystem.assetSystemComment"/></textarea>
							</td>
						</tr>
						
					</table>
				</td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" width="25%" style="padding-left:15px;font-size:12px;"><span class="spanred" style="padding-right:5px;">*</span>系统品牌: </td>
							<td  align="left" class="padding" id="systemOption">
							<s:if test="assetSystem.brandlist.size>0">
								<s:iterator value="assetSystem.brandlist" status="sta" >
									<input style="margin-top:8px; width:270px;" maxlength="30"
									class="text-input" value="<s:property value='assetSystemName'/>" id="<s:property value='assetSystemId'/>" name="systemOptions" onblur="check11(this.value,'系统品牌');"  />
									<s:if test="#sta.index>0"><a href="#"  onclick="DelOptionNew('${assetSystemId}')">删除</a></s:if>           
								</s:iterator>								
							</s:if>
							<s:if test="%{assetSystem==null||assetSystem.brandlist.size==0}">
								<input style="margin-top:8px; width:270px;" maxlength="30"
									class="text-input" value="<s:property value='assetSystemName'/>" id="<s:property value='assetSystemId'/>" name="systemOptions" onblur="check11(this.value,'系统品牌');"/>
							</s:if>
							</td>
							
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="javascript:;" onclick="AddOption('systemOption')">增加选项</a></td>
							<td >
							<s:iterator value="subject.options" status="status">
								<p><input type="text" maxlength="30"
								class="text-input"
								class="input-text" name="option_<s:property value='id'/>" value="<s:property value='name'/>" onblur="check11(this.value,'系统品牌');"/>
								<s:if test="#status.index>1"><a class="del" href="javascript:;" onclick="DelOption()">删除</a></s:if></p>
							</s:iterator>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
			
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" width="25%" style="padding-left:15px;font-size:12px;"><span class="spanred" style="padding-right:5px;">*</span>系统版本:</td>
							<td align="left" class="padding" id="versionOption">
								<s:if test="assetSystem.versionlist.size>0">
									<s:iterator value="assetSystem.versionlist" status="sta" >
										<input style="margin-top:8px; width:270px;" maxlength="30"
										class="text-input" value="<s:property value='assetSystemName'/>" id="<s:property value='assetSystemId'/>" name="versionOptions"  onblur="check11(this.value,'系统版本');" />
										<s:if test="#sta.index>0"><a href="#"  onclick="DelOptionNew('${assetSystemId}')">删除</a></s:if>           
									</s:iterator>								
								</s:if>
								<s:if test="%{assetSystem==null||assetSystem.versionlist.size==0}">
									<input style="margin-top:8px; width:270px;" maxlength="30"
										class="text-input" value="<s:property value='assetSystemName'/>" id="<s:property value='assetSystemId'/>" name="versionOptions" onblur="check11(this.value,'系统版本');"/>
								</s:if>
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="javascript:;" onclick="AddOption('versionOption')">增加选项</a></td>
							<td >
							<s:iterator value="subject.options" status="status">
								<p><input type="text" class="input-text" maxlength="30" name="option_<s:property value='id'/>" value="<s:property value='name'/>"onblur="check11(this.value,'系统版本');"/>
								<s:if test="#status.index>1"><a class="del" href="javascript:;" onclick="DelOption()">删除</a></s:if></p>
							</s:iterator>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" width="25%" style="padding-left:15px;font-size:12px;"><span class="spanred" >*</span>系统版本号:</td>
							<td align="left" class="padding" id="versionNoOption">
								<s:if test="assetSystem.versionnolist.size>0">
									<s:iterator value="assetSystem.versionnolist" status="sta" >
										<input style="margin-top:8px; width:270px;" maxlength="30"
										class="text-input" value="<s:property value='assetSystemName'/>" id="<s:property value='assetSystemId'/>" name="versionNoOptions"  onblur="check11(this.value,'系统版本号');" />
										<s:if test="#sta.index>0"><a href="#"  onclick="DelOptionNew('${assetSystemId}')">删除</a></s:if>           
									</s:iterator>								
								</s:if>
								<s:if test="%{assetSystem==null||assetSystem.versionnolist.size==0}">
									<input style="margin-top:8px; width:270px;" maxlength="30"
										class="text-input" value="<s:property value='assetSystemName'/>" id="<s:property value='assetSystemId'/>" name="versionNoOptions" onblur="check11(this.value,'系统版本号');" />
								</s:if>
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="javascript:;" onclick="AddOption('versionNoOption')">增加选项</a></td>
							<td >
							<s:iterator value="subject.options" status="status">
								<p><input type="text" class="input-text" maxlength="30" name="option_<s:property value='id'/>" value="<s:property value='name'/>" onblur="check11(this.value,'系统版本号');"/>
								<s:if test="#status.index>1"><a class="del" href="javascript:;" onclick="DelOption()">删除</a></s:if></p>
							</s:iterator>
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; width:550px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存"  onclick="check();"/> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" />
				</td>
			</tr>
		</table>

</s:form>


</body>
</html>
