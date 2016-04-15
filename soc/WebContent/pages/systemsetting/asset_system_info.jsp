<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
function AddOption()
{

	
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_input.setAttribute("maxlength", "50");
	_input.setAttribute("style", "width:270px; margin-top:8px; ");
	_input.setAttribute("onblur", "check11(this.value)");
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
	      url:"${ctx}/category/deletecategory.action?ids="+obj,
	      success: function (data){
	      		
	      }
	 });
	location.reload(); 
}

function check(){
	if($("#categoryName").val()==""){
		alert("设备类型不能为空！");
		return false;
	}else{
		if(regex($('#categoryName').val(),"设备类型")==null){
			return false;
		}
	}
	var options = document.getElementsByName("options");
	for(var i =0;i<options.length;i++){
		if(options[i].value==null||options[i].value==""){
			alert("设备厂商不能为空！");
			return;
		}
		
	}
	
	
	//if($("").val()==""){
	//	alert("设备厂商不能为空！");
	//	return false;
	//}
	if(!existFlag){
	document.getElementById("from").submit();
	}
}
function check11(value,name){
	
	var regex=value.match(/^([^`~!@#$%^&*()=|{}\\\[\]<>\/?~！@#￥……&*|{}【】。\"，？])*$/); 
	if(value!=null&&value!=""){
	if(regex==null){
		alert("您在设备厂商里的输入包含特殊字符！");
		
	}else{
		
	}
	}else{
		alert("设备厂商不能为空！");
		
	}
	
}
var existFlag ;
function checkDeviceName(){
	 $("#check_loginname_msg").empty();
	var categoryName=$("#categoryName").val();
	if('${category.device_category_id}'==''){
		$.ajax({
			type : "post",
			url : "${ctx}/category/checkDeviceName.action",
			dataType : "text",
			data : "&categoryName=" + categoryName,
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
function regex(str,name){
	var regex=str.match(/^([^`~!@#$%^&*()=|{}\\\[\]<>\/?~！@#￥……&*|{}【】。\"，？])*$/); 
	if(regex==null){
		alert("您在"+name+"里的输入包含特殊字符！");
	}
	return regex;
}
</script>
	

</head>

<body style="margin:8px;background-color : white; text-align:left;">

<s:form action="insertCategory.action"  namespace="/category"
		  method="post" id ="from" >
	<s:token></s:token>
	<div class="framDiv"  style="width:550px;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class='r2titler' colspan='3'><b>设备信息 </b></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" width="20%" style="padding-left:12px;font-size:12px;"><span
								class="spanred">*</span>设备类型:</td>
							<td align="left" class="padding">
								<input class="text-input"   
								id="categoryName" name="categoryName"  maxlength="50" 
								value="<s:property value='category.device_category_name'/>" style="width: 270px" <c:if test="${category.device_category_name != null}">readonly="readonly"</c:if> onblur="checkDeviceName();"/>
								<input name="categoryId" type="hidden" value="<s:property value='category.device_category_id'/>"/>
								<font style="color: red"><span id="check_loginname_msg"></span></font>
							</td>
						</tr>
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td align="right" style="padding-left:12px;font-size:12px;"><span class="spanred">*</span>设备厂商:</td>
							<td align="left" class="padding" id="voteoptions">
								<s:if test="category.devicelist.size>0">
									<s:iterator value="category.devicelist" status="sta" >
										<input style="margin-top:8px; width:270px;" maxlength="50"
										class="text-input" value="<s:property value='device_category_name'/>" id="<s:property value='device_category_id'/>" name="options" onblur="check11(this.value);"  />
										<s:if test="#sta.index>0"><a href="#"  onclick="DelOptionNew('${device_category_id}')">删除</a></s:if>           
									</s:iterator>								
								</s:if>
								<s:if test="category==null">
									<input style="margin-top:8px; width:270px;"  maxlength="50"
										class="text-input" value="<s:property value='device_category_name'/>" id="<s:property value='device_category_id'/>" name="options" onblur="check11(this.value);"/>
								</s:if>
							</td>
							<td><span id="div_ipEnd"></span></td>
							
						</tr>
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td></td>
							<td><a href="javascript:;" onclick="AddOption()">增加选项</a></td>
							<td >
							<s:iterator value="subject.options" status="status">
								<p><input type="text" maxlength="50" class="input-text" name="option_<s:property value='id'/>" value="<s:property value='name'/>" onblur="check11(this.value);"/>
								<s:if test="#status.index>1"><a class="del" href="javascript:;" onclick="DelOption()">删除</a></s:if></p>
							</s:iterator>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
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
					id="btnSave" value="保  存" onclick="check();"/> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" />
				</td>
			</tr>
		</table> 
		
</s:form>

</body>
</html>
