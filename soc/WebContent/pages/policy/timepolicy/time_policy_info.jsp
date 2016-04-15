<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.lowagie.text.Document"%>
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


<!-- <link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css"> -->
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
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
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/vbscript' src="${ctx}/scripts/ascii.vbs"></script>
<script type='text/javascript' src="${ctx}/scripts/pinyin.js"></script>
<script language="javascript">
	jQuery(document).ready(function() {
		jQuery("#timeForm").validationEngine();
        
        
		$("#ableTime1").click(function() {
			$("#policyExecuteWay").val(1);
			$("#time1").attr("style", "display:none");
			$("#time2").attr("style", "display:block");
		});

		$("#ableTime").click(function() {
			$("#policyExecuteWay").val(0);
			$("#time1").attr("style", "display:block");
			$("#time2").attr("style", "display:none");

		});

		//判断用户传递过来的执行方式
		if ('${timePolicy.timePolicyexecuteWay}' == '1') {
			$("#ableTime1").attr("checked", true);
			$("#time2").attr("style", "display:block");
			$("#time1").attr("style", "display:none");
		} else {
			$("#ableTime").attr("checked", true);
			$("#time1").attr("style", "display:block");
			$("#time2").attr("style", "display:none");

		}
		if(/msie/i.test(navigator.userAgent))    //ie浏览器 
	 	{
		    document.getElementById('timePolicyName').onpropertychange = checkTimePolicyName;
	 	} 
		 else 
		{//非ie浏览器，比如Firefox 
		   document.getElementById('timePolicyName').addEventListener("input",checkTimePolicyName,false); 
		} 

	});
	var flag;

	//判断时间策略名称是否存在
	function checkTimePolicyName() {
		$("#check_loginname_msg").empty();
		if ('${timePolicy.timePolicyId}' == '') {
			if (($("#timePolicyName").val() != null && $("#timePolicyName")
					.val() != '')) {
				timePolicyName = $("#timePolicyName").val();
				$
						.ajax({
							type : "post",
							url : "${ctx}/timePolicy/checktimePolicyName.action",
							dataType : "text",
							data : "&timePolicyName=" + timePolicyName,
							success : function(result) {
								if (result == 'true') {
									//alert(existFlag);
									flag = true;
									//alert("asdas");
									$("#check_loginname_msg").addClass(
											"spanred");
									$("#check_loginname_msg").html("该名称已占用");

								} else {
									flag = false;
									
								}
							}
						});
			} else {
				flag = true;

			}

		} else {
			flag = false;
		}
	}

	//验证描述
	function checkTimePolicyMemo() {
		if ($("#timePolicyMemo").val().length > 255) {
			/* alert("描述输入应该小于25"); */
			$("#check_Memo_msg").addClass("spanred");
			$("#check_Memo_msg").html("描述不应大于255个字符");
			/* $("#timePolicyMemo").focus(); */
			return false;
		} else {
			/*  alert("成功"); */
			$("#check_Memo_msg").removeClass("spanred");
			$("#check_Memo_msg").html(
					"<img border=0 src=\"${ctx}/images/ok.png\" />");
			return true;
		}
	}
	//提交按钮
	function subData() {
		//checkTimePolicyName();
		
		if($('#timePolicyMemo').val().length>50){
			alert("描述长度不能大于50");
			$('#timePolicyMemo').val('');
			$('#timePolicyMemo').focus();
			return ;
		}
		var tme1 = checkTimePolicyMemo();
		if(($("#timePolicyName").val() == null || $("#timePolicyName")
					.val() == ''))
	    {
	        $("#timeForm").submit();
	    }
		if ('${timePolicy.timePolicyId}' != '') {
			if (tme1) {
				if ($("#policyExecuteWay").val() == '0') {
					/* alert("进入函数"); */
					if ($("#beginTime").val() > $("#endTime").val()) {
						document.getElementById("div_Date").innerHTML = "请选择合法的时间段...";
						/* alert("输入合法的时间段...."); */
						return false;
					} else {
						document.getElementById("div_Date").innerHTML = "";
						$("#timeForm").submit();
						return true;
					}
				} else {
					$("#timeForm").submit();
					return true;
				}
			}
		}
		
		if (flag == false && tme1) {
			//alert("1");
			if ($("#policyExecuteWay").val() == '0') {
				/* alert("进入函数"); */
				if ($("#beginTime").val() > $("#endTime").val()) {
					document.getElementById("div_Date").innerHTML = "请选择合法的时间段...";
					/* alert("输入合法的时间段...."); */
					return false;
				} else {
					document.getElementById("div_Date").innerHTML = "";
					$("#timeForm").submit();
					return true;
				}
			} else {
				$("#timeForm").submit();
				return true;
			}
		}

	}
</script>

</head>

<body style="margin-top: 2px">
	<s:form action="updateTimePolicy.action" namespace="/timePolicy"
		method="post" theme="simple" id="timeForm" name="timeForm">

		<s:hidden name="timePolicyexecuteWay" id="policyExecuteWay" value="0" />
  <s:token></s:token>
		<!-- <s:hidden name="user.userPinyin" id="userPinyin" /> -->
		<!-- <s:hidden name="time" />
		<s:hidden name="userStatus" />
		<s:hidden name="order" id="order" />-->
		<s:hidden name="existflag" id="existflag" />
		<s:hidden name="flag" id="flag" value="true" />

		<!--  主table-->
		<table width="600px" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="70%" valign="top">
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>时间策略信息</b></td>
							</tr>

							<!-- 分隔符黑线 -->
							<tr>
								<td height="3px"></td>
							</tr>
							<tr>
								<!-- left area -->
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
											<td class="td_detail_separator"></td>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 姓名 -->
										<tr>

											<td align="left" style="padding-left:48px"><span
												class="spanred">*&nbsp;</span>名称：</td>
											<td align="left" width="50%"><input
												class="validate[required,custom[spechars],custom[validateLength]] text-input"
												name="timePolicy.timePolicyName"
												value="${timePolicy.timePolicyName}"
												<c:if test="${timePolicy.timePolicyName!=null}">readonly="readonly"</c:if>
												type="text" size="40" id="timePolicyName"
												style="width: 270px" /><input
												type="hidden" name="timePolicy.timePolicyId"
												value="${timePolicy.timePolicyId}">
											</td>
											<td><span id="check_loginname_msg"></span></td>
										</tr>
										<tr>
											<td height="2px"></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 时间策略描述 -->
										<tr>


											<td align="left" style="padding-left:62px;vertical-align:10%">描述：</td>
											<td><s:textarea name="timePolicy.timePolicyMemo"
													id="timePolicyMemo" cols="31" rows="3"
													cssStyle="width:272px" ></s:textarea>
											</td>
											<td><span id="check_Memo_msg"></span></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td align="right" class="td_detail_separator"></td>
										</tr>


										<!-- 生效方式 -->
										<tr>

											<td align="left" style="padding-left:37px">生效方式：</td>
											<td><select name="timePolicy.timePolicyEffectWay">
													<option value="1"
														<c:if test="${timePolicy.timePolicyEffectWay==1}">selected="selected"</c:if>>允许登录</option>
													<option value="0"
														<c:if test="${timePolicy.timePolicyEffectWay==0}">selected="selected"</c:if>>禁止登录</option>
											</select></td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!-- 时间点列表 -->
										<tr>

											<td align="left" style="padding-left:48px;vertical-align:10%;">时间点：</td>
											<td valign="top">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><input type="checkbox"
															<c:if test="${hours[0]=='1'}">checked="checked"</c:if>
															name="hour" value="24" />00</td>
														<td><input type="checkbox"
															<c:if test="${hours[1]=='1'}">checked="checked"</c:if>
															name="hour" value="1" />01</td>
														<td><input type="checkbox"
															<c:if test="${hours[2]=='1'}">checked="checked"</c:if>
															name="hour" value="2" />02</td>
														<td><input type="checkbox"
															<c:if test="${hours[3]=='1'}">checked="checked"</c:if>
															name="hour" value="3" />03</td>
													</tr>
													<tr>
														<td><input type="checkbox"
															<c:if test="${hours[4]=='1'}">checked="checked"</c:if>
															name="hour" value="4" />04</td>
														<td><input type="checkbox"
															<c:if test="${hours[5]=='1'}">checked="checked"</c:if>
															name="hour" value="5" />05</td>
														<td><input type="checkbox"
															<c:if test="${hours[6]=='1'}">checked="checked"</c:if>
															name="hour" value="6" />06</td>
														<td><input type="checkbox"
															<c:if test="${hours[7]=='1'}">checked="checked"</c:if>
															name="hour" value="7" />07</td>
													</tr>
													<tr>

														<td><input type="checkbox"
															<c:if test="${hours[8]=='1'}">checked="checked"</c:if>
															name="hour" value="8" />08</td>
														<td><input type="checkbox"
															<c:if test="${hours[9]=='1'}">checked="checked"</c:if>
															name="hour" value="9" />09</td>
														<td><input type="checkbox"
															<c:if test="${hours[10]=='1'}">checked="checked"</c:if>
															name="hour" value="10" />10</td>
														<td><input type="checkbox"
															<c:if test="${hours[11]=='1'}">checked="checked"</c:if>
															name="hour" value="11" />11</td>
													</tr>
													<tr>
														<td><input type="checkbox"
															<c:if test="${hours[12]=='1'}">checked="checked"</c:if>
															name="hour" value="12" />12</td>
														<td><input type="checkbox"
															<c:if test="${hours[13]=='1'}">checked="checked"</c:if>
															name="hour" value="13" />13</td>
														<td><input type="checkbox"
															<c:if test="${hours[14]=='1'}">checked="checked"</c:if>
															name="hour" value="14" />14</td>
														<td><input type="checkbox"
															<c:if test="${hours[15]=='1'}">checked="checked"</c:if>
															name="hour" value="15" />15</td>
													</tr>
													<tr>
														<td><input type="checkbox"
															<c:if test="${hours[16]=='1'}">checked="checked"</c:if>
															name="hour" value="16" />16</td>
														<td><input type="checkbox"
															<c:if test="${hours[17]=='1'}">checked="checked"</c:if>
															name="hour" value="17" />17</td>
														<td><input type="checkbox"
															<c:if test="${hours[18]=='1'}">checked="checked"</c:if>
															name="hour" value="18" />18</td>
														<td><input type="checkbox"
															<c:if test="${hours[19]=='1'}">checked="checked"</c:if>
															name="hour" value="19" />19</td>
													</tr>
													<tr>
														<td><input type="checkbox"
															<c:if test="${hours[20]=='1'}">checked="checked"</c:if>
															name="hour" value="20" />20</td>
														<td><input type="checkbox"
															<c:if test="${hours[21]=='1'}">checked="checked"</c:if>
															name="hour" value="21" />21</td>
														<td><input type="checkbox"
															<c:if test="${hours[22]=='1'}">checked="checked"</c:if>
															name="hour" value="22" />22</td>
														<td><input type="checkbox"
															<c:if test="${hours[23]=='1'}">checked="checked"</c:if>
															name="hour" value="23" />23</td>
													</tr>
													<tr>
														<td colspan="2"><div id="div_hour"
																style="color: red;"></div></td>
													</tr>
												</table>
											</td>
											<td><span style="padding-right:8px;color:red;">如不填写，则默认值：任意时间</span>
											</td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!-- 生效日期 -->
										<tr>

											<td align="left" style="padding-left:38px">生效日期：</td>
											<td><input type="radio" id="ableTime" name="ableTime"
												value="0" checked="checked">指定时间段 <input
												type="radio" id="ableTime1" name="ableTime" value="1">每周重复
											</td>
										</tr>


										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!-- 时间段列表 -->

										<tr id="time1">

											<td align="left" style="padding-left:25px">生效时间段：</td>

											<td><input id="beginTime" onclick="WdatePicker()"
												name="timePolicy.timePolicyDateStart"
												value='<s:date name="timePolicy.timePolicyDateStart" format="yyyy-MM-dd"/>'
												class="Wdate" readonly="readonly"
												style="width:113px; height:19px;">&nbsp;至
												<input id="endTime" onclick="WdatePicker()"
												name="timePolicy.timePolicyDateEnd"
												value='<s:date name="timePolicy.timePolicyDateEnd" format="yyyy-MM-dd"/>'
												class="Wdate" readonly="readonly"
												style="width:113px; height:19px;">
											</td>

										</tr>
										<tr>
											<td></td>
											<td></td>
											<td><div id="div_Date" style="color: red;"></div></td>
										</tr>
										<tr id="time2" style="display:none;">

											<td align="left" style="padding-left:60px">重复：</td>
											<td><input type="checkbox" name="week"
												<c:if test="${weeks[0]=='1'}">checked="checked"</c:if>
												value="1">周日 <input type="checkbox" name="week"
												<c:if test="${weeks[1]=='1'}">checked="checked"</c:if>
												value="2">周一 <input type="checkbox" name="week"
												<c:if test="${weeks[2]=='1'}">checked="checked"</c:if>
												value="3">周二 <input type="checkbox" name="week"
												<c:if test="${weeks[3]=='1'}">checked="checked"</c:if>
												value="4">周三 <input type="checkbox" name="week"
												<c:if test="${weeks[4]=='1'}">checked="checked"</c:if>
												value="5">周四 <input type="checkbox" name="week"
												<c:if test="${weeks[5]=='1'}">checked="checked"</c:if>
												value="6">周五 <input type="checkbox" name="week"
												<c:if test="${weeks[6]=='1'}">checked="checked"</c:if>
												value="7">周六</td>
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									</table> <!--  左侧table--></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>

		<table width="605px" border="0" cellspacing="0" cellpadding="0"
			style="align:right">
			<!-- 空行 -->
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input
					type="button" class="btnyh" id="btnSave" value="保  存"
					onclick="subData();" /> <input type="button" class="btnyh"
					id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/timePolicy/query.action';" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>
