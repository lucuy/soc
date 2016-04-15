<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ include file="mainoffer.inc"%>
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
	
		<title>计时报表</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css"/>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		</script>
		<SCRIPT type="text/javascript">
			function subDate(){
				var count=$("#sel").find("option").length;
				if(count>0){
					var v="";
					for(var i=0;i<count;i++){
						if($("#reportStr").val()==""){
							$("#reportStr").val($("#sel").get(0).options[i].value);
						}else{
							$("#reportStr").val($("#reportStr").val()+";"+$("#sel").get(0).options[i].value);
						}
					}
					var ra= $('input:radio[name="type"]:checked').val();
					if(ra=="week"){
						$('#useweek').val(1);
						var weeks=$("input[type='checkbox'][name='week']");
						weeks.each(function(){
							if($(this).attr("checked")==true){
								if($('#weekSet').val()=="")
									$('#weekSet').val($(this).val());
								else
									$('#weekSet').val($('#weekSet').val()+":"+$(this).val());
								}
							}
						);
						$('#timerForm').submit();
					}
					if(ra=="day"){
						$('#useday').val(1);
						var days=$("input[type='checkbox'][name='day']");
						days.each(function (){
							if($(this).attr('checked')==true){
								if($('#daySet').val()==""){
									$('#daySet').val($(this).val());
								}else{
									$('#daySet').val($('#daySet').val()+":"+$(this).val());
								}
							}
						});
						$('#timerForm').submit();
					}
					if(ra==undefined){
						alert("请设定时间...");
					}
					
				}else{
					alert("请选择需要定时导出报表...");
				}
			}
			function cancel(){
				$('#useweek').val('');
				$('#useday').val('');
				$('#weekSet').val('');
				$('#daySet').val('');
				$('#reportStr').val('');
				$('#timerForm').submit();
			}
			function change(value){
				if(value=="custom"){
					$('#'+value).show();
					$('#sys').hide();
				}else{
					$('#'+value).show();
					$('#custom').hide();
				}
			}
			function add(){
				var text="";
				var val="";
				var ops=document.getElementById("sel").options;
				if(document.getElementById("custom").style.display=='none'){
					text=$("select[name=sys]").find("option:selected").text();
					val=$("select[name=sys]").find("option:selected").val();
				}else{
					text=$("select[name=custom]").find("option:selected").text();
					val=$("select[name=custom]").find("option:selected").val();
				}
				if(ops.length>0){
					var j=0;
					for(var i=0;i<ops.length;i++){
						if(val==ops[i].value){
							j=0;
							break;
						}else{
							j=1;
							continue;
						}
					}
					if(j==1){
						if(val!=undefined){
							$("#sel").append('<option value="'+val+'">'+text+'</option>');
						}else{
							alert("请选择一项...");	
						}	
					}else{
						alert("该选项已存在，请选择其他的选项..");
					}
				}else{
					if(val!=undefined){
						$("#sel").append('<option value="'+val+'">'+text+'</option>');
					}else{
						alert("请选择一项...");	
					}	
				}
			}
			function delsel(){
				var selectId=document.getElementById("sel").selectedIndex;
				if(selectId<0){
					alert("请选择一项删除...");
				}else{
					if(confirm('您确定要删除此项吗?')){
			     		$("select[name=sel]").find("option:selected").remove();
					}
				}
			}
		</SCRIPT>
	</head>

	<body>
	<form action="reportTime.do?method=save" method="post" id="timerForm" name="timerForm">
	<input type="hidden" id="useweek" name="useweek" value="${ReportTimerForm.useweek }"/>
	<input type="hidden" id="useday" name="useday" value="${ReportTimerForm.useday }"/>
	<input type="hidden" id="weekSet" name="weekSet" value="${ReportTimerForm.weekSet }"/>
	<input type="hidden" id="daySet" name="daySet" value="${ReportTimerForm.daySet }"/>
	<html:hidden property="reportTimer.id" name="ReportTimerForm"/>
	<input type="hidden" id="reportStr" name="reportStr"/>
	<!--  主table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 2px">
			<tr>
				<td width="99%" valign="top">
					<div class="framDiv">

						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:1px;padding-left:1px;padding-right:1px;">
						
							<tr>
								<td class="r2titler">
									<b>计时报表设置</b>
								</td>
							</tr>

							<!-- 分隔符黑线 -->
							<tr>
								<td height="5px">
								</td>
							</tr>
							<tr>
							<td>
							<table width="90%" border="0" cellspacing="0" cellpadding="0" style="margin-left: 36px;margin-bottom:10px;margin-top:5px;">
							<tr>
								<td class="td_detail_title">
									<b>报表模板设置</b>
								</td>
							</tr>
							
							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										valign="top" style="padding-left: 20px;padding-top:6px;">
										<tr>
											<td width="25%">模板类型选择
												<select onchange="change(this.value);">
													<option value="sys">系统报表</option>
													<option value="custom">自定义报表</option>
												</select>
											</td>
											<td width="15%" >模板选择<br/>
												<select id="sys" name="sys" size="8" style="width:100%; ">
												<logic:iterate id="e" name="formsList">
													<option value="${e.id},${e.reportFormId}" >${e.reportFormName }</option>
												</logic:iterate>
												</select>
												<select id="custom" name="custom" size="8" style="width:100%;display:none;">
												<logic:iterate id="c" name="customList">
													<option value="${c.id}">${c.name}</option>
												</logic:iterate>
												</select>
											</td>
											<td align="left" valign="bottom">
											<input type="button" value="添加" class="btnstyle1" onclick="add();"/>
											</td>
											<td width="15%">选定模板<br/>
												<select id="sel" name="sel" size="8" style="width:100%">
												<logic:notEmpty name="selSys">
													<logic:iterate id="sys" name="selSys">
														<option value="${sys.id},${sys.reportFormId}">${sys.reportFormName }</option>
													</logic:iterate>
												</logic:notEmpty>
												<logic:notEmpty name="selCustom">
													<logic:iterate id="cus" name="selCustom">
														<option value="${cus.id}">${cus.name}</option>
													</logic:iterate>
												</logic:notEmpty>
												</select>
											</td>
											<td align="left" valign="bottom">
											<input type="button" value="删除" class="btnstyle1" onclick="delsel();"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="td_detail_separator">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_detail_title">
									<b>时间设置</b>
								</td>
							</tr>

							<!-- 虚线分割线 -->
							<tr>
								<td colspan="2">
									<div class="xuxian"></div>
								</td>
							</tr>
							<tr>
								<td style="padding-left: 20px">
									<input type="radio" name="type" value="week" <c:if test="${ReportTimerForm.reportTimer.useWeek==1}">checked="checked"</c:if>>
									按周 &nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_detail_separator">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<table width="90%" border="0" cellspacing="0" cellpadding="0"
										id="week" name="week" valign="top" style="padding-left: 40px">
										<tr>
										<%int  i=0; %>
										<logic:iterate id="w" name="ReportTimerForm" property="weekList">
										<% i++; %>
										<td>
											<input type="checkbox" name="week" id="week<%=i%>" value="<%=i %>" <c:if test="${w==1}">checked="checked"</c:if>/>周
											<%if(i==1){%>一<%} %>
											<%if(i==2){%>二<%} %>
											<%if(i==3){%>三<%} %>
											<%if(i==4){%>四<%} %>
											<%if(i==5){%>五<%} %>
											<%if(i==6){%>六<%} %>
											<%if(i==7){%>日<%} %>
										</td>
										</logic:iterate>
              							</tr>
										
									</table>
								</td>
							</tr>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr><!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>
							<tr>
								<td style="padding-left: 20px">
								<input type="radio" name="type" value="day" <c:if test="${ReportTimerForm.reportTimer.useDay==1}">checked="checked"</c:if>>
									按日
								</td>
							</tr>
							<!-- 空行 -->
							<tr>
								<td class="td_detail_separator">
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
											id="day" name="day" style="padding-left: 40px;" valign="top">
										<tr>
										<%int  j=0; %>
										<logic:iterate id="d" name="ReportTimerForm" property="dayList">
										<% j++; %>
											<td>
												<input type="checkbox"  name="day" id="day<%=j%>" value="<%=j%>" <c:if test="${d==1}">checked="checked"</c:if>/>
												<%if(j<10){%>0<%=j%>
												<%}else{ %><%=j%><%} %>
											</td>
										 <%if ((j%10)==0){%>
              							</tr>
             							 <%} %>
             							 </logic:iterate>
									</table>
								</td>
							</tr>
							</table>
							</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!-- 空行 -->
				<tr>
					<td class="td_detail_separator">
					</td>
				</tr>

				<tr>
					<td align="right">
						<input type="button" value="保    存" class="btnyh" id="btnSave"  onclick="subDate();"/>
						<input type="button" class="btnyh" value="取   消" id="btnCancel" onclick="cancel();"/>
					</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		</form>
	</body>
</html>
