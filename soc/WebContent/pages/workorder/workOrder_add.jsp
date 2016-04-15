<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    
    <title>工单添加</title>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
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
		src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
	<script type='text/javascript'
		src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>	
	<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function() {
			jQuery("#workfrm").validationEngine();
			//紧急程度判断
			var s =document.getElementsByName("workOrder.exigencyLevel");
			for(var i=0;i<s.length;i++){
				if(s[i].value=='${workOrder.exigencyLevel}'){
					s[i].checked=true;
				}
			}
			//状态判断
			var h =document.getElementsByName("workOrder.status");
			for(var i=0;i<h.length;i++){
				if(h[i].value=='${workOrder.status}'){
					h[i].checked=true;
				}
			}
			
			$("#predict").blur(function() {
				var pro = $('#produceDate').val();
				var pre = $(this).val();
				html = "预计完成时间小于当前时间";
				pro1 = pro.replace("-","").replace("-","");
				pre1 = pre.replace("-","").replace("-","");
				if (parseInt(pro1) > parseInt(pre1)){
					$("#btnSave").attr("disabled", "disabled");
					$("#checkpre").html(html).show();
				}else{
					$("#btnSave").removeAttr("disabled");
					$("#checkpre").html(html).hide();
				}
			});

			$("#realityDate").blur(function() {
				var rea = $(this).val();
				var pro = $('#produceDate').val();
				html = "实际完成时间不能小于工单生成时间";
				pro1 = pro.replace("-","").replace("-","");
				pre1 = rea.replace("-","").replace("-","");
				if (parseInt(pro1) > parseInt(pre1)){
					$("#btnSave").attr("disabled", "disabled");
					$("#checkrea").html(html).show();
				}else{
					$("#btnSave").removeAttr("disabled");
					$("#checkrea").html(html).hide();
				}
				
			});
			//判断是否为查询
			var type = $("#type").val();
			if(type == "detail"){
				$("form input").attr("readonly", true);
				$(".radio").attr("disabled", "disabled");
				$("#workOrderDescribe").attr("readonly","readonly");
				$("#remark").attr("readonly","readonly");
				$("#produceDate").attr("onclick",""); 
				$("#predict").attr("onclick",""); 
				$("#realityDate").attr("onclick",""); 
				$("#btnSave").hide();
				$("#back").val("返回");
				$("#b").html("工单详情");
				$(".input").show();
				$(".select").hide();
			}else{
				$(".input").hide();
				$(".select").show();
			}
			
			var queryFlag = "${queryFlag}" ;
				if(queryFlag == "'q'"){
					$('#back').click(function(){
						window.close();
					});
					$('#btnSave').click(function(){
						sub() ;
						setTimeout(closePageMethod(),2000);
					});
			}
			
		});
		
		function closePageMethod(){
			window.opener.location.reload();
			window.close();
		}
		
		//点击发布时间自动获得当前时间
		function getToDate(){
			//获得当前时间
			var s;
			var d = new Date();
			if((d.getMonth()+1)<10){
				s=d.getFullYear()+"-0"+(d.getMonth()+1)+"-";
			}else{
				s=d.getFullYear()+"-"+(d.getMonth()+1)+"-";
			}
			if(d.getDate()<10){
				s+="0"+d.getDate();
			}else{
				s+=d.getDate();
			}
			var pro =  $('#produceDate').val();
			if(pro==null || pro==""){
				$('#produceDate').val(s);
			}
			
		}
		
		//点击后，把userId付值给隐藏域
		function checkID(obj,id){
			if(id == "workOrderAuditUserId"){
				document.getElementById("workOrderAuditUserId").value=obj.options[obj.selectedIndex].title;
			}else if(id == "workOrderHandleUserId"){
				document.getElementById("workOrderHandleUserId").value=obj.options[obj.selectedIndex].title;
			}
			
		}
		
		//提交的时候判断时间
		function sub(){
			
			if(""==$("#workOrderDescribe").val()||null==$("#workOrderDescribe").val()){
				alert("工单描述不能为空!");
				return;
			}
			if(""==$("#workOrderHandles").val()){
				alert("请选择工单操作者!");
				return;
			}
			
	        $("#workfrm").submit();
		}
		
	</script>
  </head>
  
  <body>
  <s:form action="addWorkOrder.action" namespace="/workOrder" method="post" id="workfrm" name="workfrm" theme="simple">
    		<!-- 主table -->
    		<s:token></s:token>
    		<table width="60%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
    			<tr>
    				<td width="100%" valign="top">
    					<!-- 左侧table -->
    					<div class="framDiv" style="height: 478px">
    						<table width="100%" border="0" cellspacing="1" cellpadding="0">
				    			<tr>
									<td class='r2title' colspan='4'><b id="b">新增工单</b></td>
								</tr>
								
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20"  align="right"  width="18%">
										<span class="spanred">*</span>工单名称：
									</td>
									<td width="70%"  colspan="2" >
										<input  type="text" class="validate[required,custom[validateLength],custom[spechars]] text-input "   
										 id="workOrderName" name="workOrder.workOrderName"   style="width: 270px" value="${workOrder.workOrderName}"/>
										
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										<span class="spanred">*</span>工单描述：
									</td>
									<td width="70%" class="msg" colspan="2" >
											<textarea rows="6" name="workOrder.workOrderDescribe" id="workOrderDescribe" cols="35" 
											class="validate[required]">${workOrder.workOrderDescribe}</textarea>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										<span class="spanred">*</span>紧急程度：
									</td>
									<td width="70%"  colspan="2" >	
										<input type="radio" name="workOrder.exigencyLevel" value="3" class="radio"
										<c:if test="${workOrder.exigencyLevel eq '3'}">checked="checked"</c:if>
										<c:if test="${workOrder.exigencyLevel==null}">checked="checked"</c:if>
										/>一般 &nbsp;&nbsp;	
										
										<input type="radio" name="workOrder.exigencyLevel" value="2" class="radio"
										<c:if test="${workOrder.exigencyLevel eq '2'}"> checked="checked"</c:if>/>次要 &nbsp;&nbsp;
										
										<input type="radio" name="workOrder.exigencyLevel" value="1" class="radio"
										<c:if test="${workOrder.exigencyLevel eq '1'}"> checked="checked"</c:if>/>重要 &nbsp;&nbsp;
										
										<input type="radio" name="workOrder.exigencyLevel" value="0" class="radio"
										<c:if test="${workOrder.exigencyLevel eq '0'}"> checked="checked"</c:if>/>紧急 &nbsp;&nbsp;
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
							</tr>
								
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20"  align="right"  width="18%">
										<span class="spanred">*</span>工单操作者：
									</td>
									<td width="70%"  colspan="3" >
										<div class="input">
											<input  type="text"    
											id="workOrderHandle" name="workOrderHandle" 
											readonly="readonly" value="${workOrder.workOrderHandle}" style="width: 270px"/>
										</div>
										
										
										<div class="select">
											<select name="workOrder.workOrderHandle" id="workOrderHandles" class="validate[required]"
											 onchange="checkID(this,'workOrderHandleUserId')" style="width: 20%">
												<option value="">--请选择--</option>
												<c:forEach items="${userList}" var="user">
													<option title="${user.userId}" value="${user.userLoginName}">${user.userLoginName}</option>
												</c:forEach>
											</select>
										</div>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
								<td height="20"  align="right"  width="18%">发送邮件：</td>
								<td> <input type="checkbox" name="workOrder.sendMail" value="1"/></td>
								</tr>
							
    						</table>
    					</div>
    				</td>
					
					</tr>
    				<tr align="right">
					<td height="30" colspan="4">
						<input type="button" 	id="btnSave"  value="保  存" class="btnyh" onclick="sub()" width="80">
						<input type="button" name="back" id="back" value="取  消"  class="btnyh" onClick="window.history.go(-1)" width="80">
					</td>
				</tr>
    		</table>
    		</s:form>
  </body>
</html>
