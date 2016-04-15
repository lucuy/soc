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
		src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
	<script type='text/javascript'
		src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>	<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function() {
			jQuery("#workfrm").validationEngine();
			
		});
		//点击发布时间自动获得当前时间
		function getToDate(){
			//获得当前时间
			var s;
			var d = new Date();
			s=d.getYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
			
			var pro =  $('#produceDate').val();
			if(pro==null || pro==""){
				$('#produceDate').val(s);
			}
			
		}
		
		
		function sub(){
			
			$("#workfrm").submit();
		}
		
	</script>
  </head>
  
  <body>
  	<s:form action="addWorkOrder.action" namespace="/workOrder" method="post" id="workfrm" name="workfrm">
    		<!-- 主table -->
    		<s:token></s:token>
    		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
    			<tr>
    				<td  width="50%" valign="top">
    					<!-- 左侧table -->
    					<div class="framDiv" style="height: 478px">
    						<table width="100%" border="0" cellspacing="1" cellpadding="0">
				    			<tr>
									<td class='r2title' colspan='3'><b>工单详情</b></td>
								</tr>
								
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20"  align="right"  width="18%">
										工单名称：
									</td>
									<td width="70%"  colspan="2" >
										<input  type="text" class="validate[required,custom[validateLength],custom[spechars]] text-input "   
										 id="workOrderName" name="workOrder.workOrderName"  readonly="readonly" style="width: 270px" value="${ workOrder.workOrderName}"/>
										<input type="hidden" name="workOrder.workOrderId" value="${workOrder.workOrderId}"/>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										工单描述：
									</td>
									<td width="70%" class="msg" colspan="2" >
										<textarea rows="6" name="workOrder.workOrderDescribe" id="workOrderDescribe" cols="35" 
											class="validate[required]" readonly="readonly">${workOrder.workOrderDescribe}</textarea>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										紧急程度：
									</td>
									<td width="70%"  colspan="2" >	
										<input type="radio" disabled="disabled" name="workOrder.exigencyLevel" value="3" <c:if test="${workOrder.exigencyLevel ==3  }">checked="checked"</c:if> disabled="disabled"/>一般 &nbsp;&nbsp;	
										<input type="radio" disabled="disabled" name="workOrder.exigencyLevel" value="2" <c:if test="${workOrder.exigencyLevel ==2  }">checked="checked"</c:if> disabled="disabled"/>次要 &nbsp;&nbsp;
										<input type="radio" disabled="disabled" name="workOrder.exigencyLevel" value="1" <c:if test="${workOrder.exigencyLevel ==1  }">checked="checked"</c:if> disabled="disabled"/>重要 &nbsp;&nbsp;
										<input type="radio" disabled="disabled" name="workOrder.exigencyLevel" value="0" <c:if test="${workOrder.exigencyLevel ==0  }">checked="checked"</c:if> disabled="disabled"/>紧急 &nbsp;&nbsp;
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20" align="right" >
										状态：
									</td>
									<td width="70%" class="msg" colspan="2" >
										<input type="radio" name="workOrder.status" value="0" <c:if test="${workOrder.status ==0  }">checked="checked"</c:if> disabled="disabled"/>未处理 &nbsp;
										<input type="radio" name="workOrder.status" value="1" <c:if test="${workOrder.status ==1  }">checked="checked"</c:if> disabled="disabled" />已处理 &nbsp;
										<input type="radio" name="workOrder.status" value="2" <c:if test="${workOrder.status ==2  }">checked="checked"</c:if> disabled="disabled"/>已关闭 &nbsp;
									</td>
								</tr>
								
								
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20"  align="right"  width="18%">
										工单指派者：
									</td>
									<td width="70%"  colspan="2" >
										<input  type="text" class="validate[required,custom[validateLength],custom[spechars]] text-input "   
										 id="workOrderStart" name="workOrder.workOrderStart" value="${workOrder.workOrderStart}" readonly="readonly" style="width: 270px"/>
									</td>
								</tr>
								<!-- 空行 -->
								<tr>
											<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td height="20"  align="right"  width="18%">
										工单操作者：
									</td>
									<td width="70%"  colspan="2" >
										<input  type="text"    
										 id="workOrderHandle" name="workOrder.workOrderHandle"  value="${workOrder.workOrderHandle}" readonly="readonly" style="width: 270px"/>
									</td>
								</tr>
								<tr>
								<td height="20"  align="right"  width="18%">发送邮件：</td>
								<td> <input type="checkbox" name="workOrder.sendMail" <c:if test="${workOrder.sendMail == 1}"> checked="checked"</c:if>  disabled="disabled"/> </td>
								</tr>
								<!-- 空行 -->
								
    						</table>
    					</div>
    				</td>
					
					<!-- 左右中间间隔 -->
					<td width="5px"></td>
					
 
    				<td valign="top">
    					<!-- 右侧的table -->
    					<div class="framDiv" style="height: 478px">
    						<table width="100%" border="0" cellspacing="1" cellpadding="0">
    							<tr>
									<td class='r2title' colspan='3'></td>
								</tr>
    							<!-- 空行 -->
								<tr>
											<td class="td_detail_separator">&nbsp;</td>
								</tr>
								<tr>
									<td height="20" align="right"  width="15%" >
										处理结果：
									</td>
									<td width="70%"  colspan="2" >
										<textarea rows="5" name="workOrder.remark" id="remark" cols="50" >${workOrder.remark}</textarea>
									</td>
								</tr>
    						</table>
    					</div>
    				</td>
    			</tr>
    		</table>
    		<table align="right">
				<tr align="right">
					<td height="30" colspan="4">
						<input type="button" 	id="btnSave"  value="保  存" class="btnyh" onclick="sub()" width="80"  <c:if test="${workOrder.alarmID > 0 }">style="display: none"</c:if> >
						&nbsp;&nbsp;<input type="button" name="back" value="取  消"  class="btnyh" onClick="window.history.go(-1);" width="80">
					</td>
				</tr>
			</table>
    	</s:form>
  </body>
</html>
