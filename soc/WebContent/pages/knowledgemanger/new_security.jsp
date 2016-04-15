<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<link href="${ctx}/styles/css.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/library.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/stylesalidationEngine.jquery.css" rel="stylesheet"
	type="text/css">
		<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
		type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
		src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript' src="${ctx}/ckeditor/ckeditor.js"></script>


<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/user/user.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">

<script type='text/javascript' >

jQuery(document).ready(function() {
		jQuery("#from").validationEngine();
		var falg=$("#falg").val();
		if("0" !=falg){
			alert("请检查输入内容，输入项不能为空！");
		}
		
		$("#securityDate").blur(function() {
			checkDate();
		});
		
		$("#securityDate").focus(function() {
			checkDate();
		});
	});
	//字符串转换成时间
	function toDate(str){
		var sd=str.split("-");
		return new Date(sd[0],sd[1],sd[2]);
	}
		
	//判断发布时间是否在当前时间之前
	function checkDate(){
		var html="发布时间不能大于当前时间";
		if($("#securityDate").val() != ""){
			//获得当前时间组成的数
			var today = new Date();
			var	today1=today.getFullYear();
			today1=(today1*100)+today.getMonth()+1;
			today1=(today1*100)+today.getDate();
			
			//获得发布日期组成的数字
			var date =toDate($("#securityDate").val());
			var date1=date.getFullYear();
			date1=(date1*100)+date.getMonth();
			date1=(date1*100)+date.getDate();
			
			//比较
			if(today1>=date1){
				$("#btnSave").removeAttr("disabled");
				$("#div_ipEnd").html(html).hide();
			}else{
				$("#btnSave").attr("disabled", "disabled");
				$("#div_ipEnd").html(html).show();
			}
		}else{
			$("#btnSave").attr("disabled", "disabled");
			$("#div_ipEnd").html(html).hide();
		}
		
	}
	
	//去除首部和尾部的空格
	function trim(str){   
		str = str.replace(/^(\s|\u00A0)+/,'');   
    	for(var i=str.length-1; i>=0; i--){   
        	if(/\S/.test(str.charAt(i))){   
				str = str.substring(0, i+1);   
				break;   
			}   
		}   
		return str;   
	}
	
	function trimTable(){
		checkDate();
		if(""==$("#securityDate").val()||null==$("#securityDate").val()){
			alert("请选择发布时间！");
			return;
		}
		$("#from").submit();
	}
</script>




</head>

<body style="margin:2px;background-color : white; text-align:left;">

<s:form action="insertSecurity" namespace="/securityBulletin"
		method="post" theme="simple"  cssStyle="width:800px;" id ="from" onkeydown="if(event.keyCode==13){return false;}">
<s:token></s:token>
	<!-- 公司table-->
	<div class="framDiv" style="width:800px;">
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<!-- 用户信息 -->
			<tr>
				<td class='r2titler' colspan='3' style= "font-size:12px;"><b>新增安全公告 </b></td>
			</tr>
			<tr>
				<td><input type="hidden" id="falg" value="${falg}"/>
					<!-- 资产基本信息 -->
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						<!-- 空行 -->
						<tr>
							<td class="td_detail_separator"></td>
						</tr>
						<!-- 公告标题 -->
						<tr>
							<td align="right" width="12%" style="padding-left:10px;font-size:12px;"><span
								class="spanred">*&nbsp;</span>公告标题：</td>
							<td align="left" class="padding"><input
								class="validate[required,maxSize[50],custom[spechars]] text-input " 
								name="security.securityTitle" value="" type="text"
								size="40" id="title" maxlength="255" style="width: 270px"
								 /> 
							</td>
							<td><span id="check_loginname_msg"></span></td>
						</tr>
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator">
								<!-- 发布人 -->
								<input name="security.publisher" value="${SOC_LOGON_USER.userLoginName}" type="hidden"/>
							</td>
						</tr>

						<!--  
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred">*&nbsp;</span>发布人:</td>
							<td align="left" class="padding"><input
								class="validate[required,custom[onlyLetterNumber]] text-input"
								name="security.publisher" value="" type="text"
								size="40" id="publisher" maxlength="255" style="width: 270px"
								onblur="checkAssetIp();" />
							</td>
							<td><span id="div_ipEnd"></span></td>
						</tr>
						-->
						
						<!-- 发布时间 -->
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred">*&nbsp;</span>发布时间：</td>
							<td align="left" class="padding"><input
								
								name="security.securityDate" value="" type="text"
								size="40" id="securityDate" maxlength="255" style="width: 270px" onclick="WdatePicker()" readonly
								 />
							</td>
							<td><font color="red" size="2px"><span id="div_ipEnd"></span></font></td>
						</tr>
						<!-- 空行-->
						<tr>
							<td class="td_detail_separator">
							</td>
						</tr>
						<!--来源 -->
						<tr>
							<td align="right" style="padding-left:10px;font-size:12px;"><span class="spanred"> </span><span
								class="spanred">*&nbsp;</span>来源：</td>
							<td align="left" class="padding"><input
								name="security.source"
								value="" type="text"
								class="validate[required,custom[settingZifang],maxSize[50]] text-input"
								size="40" id="assetGateWay" maxlength="255" style="width: 270px"
								 />
							</td>
							<td><span id="check_gate_msg"></span></td>
						</tr>

                         <tr>
							<td class="td_detail_separator"></td>
						</tr>





						
						<!-- 虚线分割线 -->
						<tr>
							<td colspan="3">
								<div class="xuxian"></div></td>
						</tr>
						<tr>
							<td height="5px"></td>
						</tr>

						<tr>
							<td colspan="3">
								<!-- 描述-->
						<tr>

							<td align="right" style="vertical-align:top;padding-left:10px;font-size:12px;"><span
								class="spanred">*&nbsp;</span>描述：</td>
							<td class="padding"><textarea name="security.securityDetails"
									id="assetMemo"
									style="width:273px;resize:none;"
									class="validate[required,max[255]] text-input ckeditor" 
									></textarea></td>
						</tr>

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


<!-- <input type="submit" class="btnyh"
					id="btnSave" value="保  存"  /> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" /> -->





<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存"  onclick="trimTable()"/> <input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" />
				</td>
			</tr>
		</table>








	
		</s:form>
	

	<!-- ext query from -->




</body>
</html>
