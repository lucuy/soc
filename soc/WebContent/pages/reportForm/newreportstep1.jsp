<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ include file="mainoffer.inc"%>
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


		<title>新建模板</title>
		<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
		<%-- <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
			type="text/css"> --%>
			<link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css">
			<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
		
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
		<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
		<script type='text/javascript'
			src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
		<script type="text/javascript">
		var exitName =false;
		function nexts(){
			var f=document.ReportForm;
			var reportinfo="";
			var temp="";
			var re = /^[_A-Za-z0-9\u4e00-\u9fa5-]+$/;
			//去掉其中的空格

			
			while($('#reportFormName').val().indexOf(" ")!=-1){
				$('#reportFormName').val(f.reportFormName.value.replace(" ",""));
			}
			
			//非法字符验证
			if($('#reportFormName').val()!="" && !re.test($('#reportFormName').val())){
				alert("模板名称：请输入汉字、半角字母或数字");
				return false;
			}
			if($('#reportFormName').val()==""){
				alert("模板名称不能为空！");
				return false;
			}
			if($('#reportFormName').val().length>25){
				alert("模板名称长度不能超过25个字！");
				return false;
			}
			if($('#reportFormDescription').val().length>255){
				alert("描述长度不能大于255");
				return;
			}
			if(exitName==true){
				alert("模板名称已存在，请修改");
				return false;
			}
			// 屏蔽掉模板类别

			
			//if(f.reportFormSort.value==""){
			//	alert("模板类别不能为空！");
			//	return false;
			//}
			if(f.reportFormType[0].checked!=true && f.reportFormType[1].checked!=true){
				alert("模板类型不能为空！");
				return false;
			}
			if(f.reportFormType[0].checked){
				temp=f.reportFormType[0].value;
			}
			if(f.reportFormType[1].checked){
				temp=f.reportFormType[1].value;
			}
			var modelname1='${modelname1}';
			//document.queryForm.action = "newreportform.do?method=creatReportFormStep2&reportinfo="+reportinfo+"&modelname1="+"${modelname1}";
			//document.queryForm.submit();
			reportinfo=reportinfo+f.reportFormName.value+";;"+temp+";"+f.reportFormDescription.value;
			<logic:notEqual name="useraction" value="editReport">
		
				f.action="newreportform.do?method=creatReportFormStep2&reportinfo="+encodeURIComponent(encodeURIComponent(reportinfo))+"&modelname1="+encodeURIComponent(encodeURIComponent(modelname1));
				//f.action="newreportform.do?method=creatReportFormStep2&reportinfo="+encodeURIComponent(reportinfo)+"&modelname1="+"${modelname1}";
				f.submit();
			</logic:notEqual>
			<logic:equal name="useraction" value="editReport">
			//alert(reportinfo);
				f.action="editreportform.do?method=editReportFormStep2&reportinfo="+encodeURIComponent(encodeURIComponent(reportinfo))+"&modelname1="+encodeURIComponent(encodeURIComponent(modelname1));
				//f.action="editreportform.do?method=editReportFormStep2&reportinfo="+encodeURIComponent(reportinfo)+"&modelname1="+"${modelname1}";
				f.submit();
			</logic:equal>
		}
		function pre(){
			window.open("","_self");
		}
		function cancels(){
			window.location = "reportFormQuery.do?method=initPage";
		}
	   function reportFormNameChange(){
			var name = $("#reportFormName").val();
			var oldname="${modelname1}";
			var re = /^[_A-Za-z0-9\u4e00-\u9fa5-]+$/;
			var result = $("#result");

			//去掉其中的空格


			while(name.indexOf(" ")!=-1){
				name = name.replace(" ","");
			}
			
			//获取已存在的模板名称
		
			
			//和服务器端交互


			if(oldname!=""){
			    if(oldname != name){
			    	if(name!="" && re.test(name)){
				    	$.ajax({
						type:"post",
						url:"newreportform.do?method=exitReportFormName",
						data: "name="+encodeURIComponent(name),
						dataType:"text",
						success:callback
					  })
			    	}
			    }//else {
			     //	result.html("<font color='#0080ff'>没有做出修改,可以进行下一步 </font>")
			     // }
			}else if(name!="" && re.test(name)) {
				$.ajax({
				type:"post",
				url:"newreportform.do?method=exitReportFormName",
				data: "name="+encodeURIComponent(name),
				dataType:"text",
				success:callback
			 });
			}
		}
		//回调函数
		function callback(data){
			var result = $("#result");
			var rstext = "";
			if(data==1){
				rstext = "<font color='red'>您输入的模板名称已存在，请重新输入 </font>";
				exitName = true;
			}else{
			    exitName = false;
				rstext = "<font color='#0080ff'>您输入的模板名称不存在，可以使用 </font>";
			}
			result.html(rstext);
		}
	</script>
	</head>

	<body>
		<form name="ReportForm" action="newreportform.do"  method="post" onsubmit="return false;">
			<!--  主table-->
			<table width="60%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px; margin-top: 2px">
				<tr>
					<!-- left area -->
					<td>
						<div class="framDiv">
							<!--  左侧table-->
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								style="padding-left: 1px; padding-top: 1px; padding-right: 1px;">

								<!-- 用户信息 -->
								<tr>
									<td class='r2titler'>
										<b>第一步</b>
									</td>
								</tr>
								<tr>
									<td style="padding-left: 100px;padding-bottom:10px">
										<logic:notEqual name="useraction" value="editReport">
											<table style="margin-top: 5px; line-height: 40px">
												<tr>
													<td style="font-size: 13px; font-family: 宋体" align="right">
														<font color="red">*</font>模板名称：
													</td>
													<td style="padding-left: 15px; font-size: 12px">
														<input type="text" style="width: 230px; height: 25px"
															name="reportFormName" id="reportFormName" class="validate[required,custom[spechars],custom[validateLength]] text-input"
															value="${modelname }" onchange="reportFormNameChange();">
														<span id="result"></span>
													</td>
												</tr>
												<tr>
													<td height="5px" colspan="2"></td>
												</tr>
												<tr>
													<td style="font-size: 13px; font-family: 宋体" align="right">
														<font color="red">*</font>模板类型：
													</td>
													<td colspan="3"
														style="padding-left: 15px; font-size: 13px; font-family: 宋体">
														<input type="radio" name="reportFormType" value="0"${isCheck1 }>
														动态模板
														<input type="radio" name="reportFormType" value="1"
															${isCheck2 } checked>
														静态模板
													</td>
												</tr>
												<tr>
													<td style="font-size: 13px; font-family: 宋体" align="right">
														描&nbsp;&nbsp;&nbsp;&nbsp;述：
													</td>
													<td colspan="3" style="padding-left: 15px">
														<textarea name="reportFormDescription" id="reportFormDescription"
															style="width: 230px;" rows="6">${modeldescription }</textarea>
													</td>
												</tr>
											</table>
										</logic:notEqual>

										<logic:equal name="useraction" value="editReport">
											<table style="margin-top: 5px; line-height: 40px">
												<tr>
													<td style="font-size: 13px; font-family: 宋体" align="right">
														<input type="hidden" name="reportFormId"
															value="${reportFormId }">
														<font color="red">*</font>模板名称：
													</td>
													<td
														style="font-size: 13px; font-family: 宋体; padding-left: 15px;">
														<input type="text" name="reportFormName" style="width: 230px; height: 25px"
															id="reportFormName" value="${modelname }"
															onchange="reportFormNameChange();">
														<span id="result"></span>
													</td>
													<td>
														<!--   <font color="red">*</font>模板类别：-->
													</td>
													<td>
														<!-- 
		<select name="reportFormSort" value="${modelcatogry }" style="width:100px;">
				<option value="">类别</option>
				<logic:notEmpty name="typelist">
					<logic:iterate id="list" name="typelist">
						<option value="${list.id }" <logic:equal name="list" property="id" value="${modelcatogry}">selected</logic:equal>>${list.reportType }</option>
					</logic:iterate>
				</logic:notEmpty>
		</select>
		 -->
													</td>
												</tr>
												<tr>
													<td style="font-size: 13px; font-family: 宋体" align="right">
														<font color="red">*</font>模板类型：
													</td>
													<td colspan="3"
														style="font-size: 13px; font-family: 宋体; padding-left: 15px;">
														<input type="radio" name="reportFormType" value="0"${isCheck1 } >
														动态模板


														<input type="radio" name="reportFormType" value="1"${isCheck2 } >
														静态模板


													</td>
												</tr>
												<tr>
													<td style="font-size: 13px" align="right">
														描&nbsp;&nbsp;&nbsp;&nbsp;述：
													</td>
													<td colspan="3"
														style="font-size: 13px; font-family: 宋体; padding-left: 15px;">
														<textarea name="reportFormDescription" id="reportFormDescription"
															style="width: 230px;" rows="6">${modeldescription }</textarea>
													</td>
												</tr>
											</table>
										</logic:equal>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
			<table width="780px" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px;">
				<!-- 空行 -->
				<tr>
					<td class="td_detail_separator">
					</td>
				</tr>

				<tr>
					<td>
						<input type="button" class="btnyh" id="btnSave" value="下一步"
							onclick="nexts();" />
						<input type="button" class="btnyh" id="btnCancel" value="取  消"
							onclick="cancels();" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
