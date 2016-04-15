<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
<head>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" href="${ctx}/styles/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script type="text/javascript"
	src="${ctx}/scripts/jquery-ui-1.8.16.custom.min.js"></script>
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<%-- <script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script> --%>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>

<%-- <script type='text/javascript'
    src="${ctx}/scripts/jquery-ui.custom.min.js"></script> 
<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>--%>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>



<style type="text/css">
.row {
	text-align: right;
	line-height: 29px;
	padding: 5px 0px 5px 0px;
	width: 20%;
}

.check_di {
	background: #ffffff;
	height: 45px;
	width: 201px;
	font-size: 12px;
}

.check_zhong {
	background: #ffff66;
	height: 45px;
	width: 165px;
}

.check_gao {
	background: #ff6666;
	height: 120px;
	width: 209px;
}

.qEvents {
	margin: 1px 1px 0px 1px;
}

.qEvents tr td {
	margin: 10px 0px 10px 0px;
	line-height: 29px;
}

.display {
	display: none;
}

.datasourceUl {
	list-style: none;
}

.hand {
	cursor: hand;
	background: #ccccff;
}

.back {
	background: #FFFFFF;
}
</style>
<script language="javascript">
	
	var rege = /^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	var assetFlag=true;
	function checkName() {
		var queryEventsName = $("#customQueryEventsName").val();
				if (!rege.test(queryEventsName)) {
					$("#nameError").html("名称中有非法字符，请重新输入！");
					assetFlag = false;
				} else {
					$.ajax({
						type : "post",
						url : "${ctx}/scanTask/checkName.action",
						async : false,
						dataType : "json",
						data : "&scanName=" + queryEventsName,
						success : function(result) {
							if (result) {
								$("#nameError").html("名称已经被占用，请更换！");
								assetFlag = false;
							} else {
								$("#nameError").html("");
								assetFlag = true;
							}

						}
					});
				}
		
		
	}
	
		//验证起始IP段合法性
		function checkIpStart() {
			var ipStart = $("#addrStart").val();
			if (ipStart != "") {
				var re = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
				var flag = re.test(ipStart);
				if (flag) {
					if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
						alert("IP段开始IP不合法！");
						return false;
					} else {
						return true;
					}
				} else {
					alert("IP段开始IP不合法！");
					return false;
				}
			} else {
				alert("IP段开始IP不能为空！");
				return false;
			}
		}
		//验证终止ip段的终止地址
			
		function checkIpEnd() {
			var ipend  = $("#addrEnd").val();
			if (ipend != "") {
				var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
				var result = re1.test(ipend);
				if (result) {
					if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
						alert("IP段结束IP不合法！");
						return false;
					} else {
						return true;
					}
				} else {
					alert("IP段结束IP不合法！");
						return false;
				}
			} else {
				alert("IP段结束IP不能为空！");
				return false;
			}
		}
		//验证IP地址合法性
	function address() {
		
			var flag2 = checkIpStart();
			var ipend  = $("#addrEnd").val();
			var result1 = checkIpEnd();
			var ipStart = $("#addrStart").val();
			
			if (flag2 && result1) {
				//判断首位是否相同
				
				 if (parseInt(ipStart.split('.')[0]) == parseInt(ipend
						.split('.')[0])) {
					//判断第二位是否相同
					 if (parseInt(ipStart.split('.')[1]) == parseInt(ipend
							.split('.')[1])) {
						//判断第三位 是否相同
						 if (parseInt(ipStart.split('.')[2]) == parseInt(ipend
								.split('.')[2])) {
							//判断第四为是否相同
							if (parseInt(ipStart.split('.')[3]) > parseInt(ipend
									.split('.')[3])) {
								alert( "IP段开始IP地址必须小于结束地址");
							return false;
							} else {
								return true;
							}

						} else {
							alert("请输入同一IP段！");
							return false;
						}

					} else {
						alert("请输入同一IP段！");
						return false;
					}

				} else {
					alert("请输入同一IP段！");
					return false;
				}

			} else {
				alert("请输入合法的IP段地址！");
				return false;
			}

	}
		var reIp = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式;
	//提交数据
	function subDate() {
	
			var ipend  = $("#addrEnd").val();
		
			var ipStart = $("#addrStart").val();
		var queryEventsName = $("#trendName").val();
		var add = $("#addr").val();
		var desc = $("#desc").val();
		var queryEventsName = $("#customQueryEventsName").val();
		if(queryEventsName ==null ||queryEventsName=="" ){
			alert("任务名称不能为空！");
			return;
		}
		if (add == null || add == '') {
		if(!address()){
			return;
			}
		} else {
			var addrs = add.split(',');
			for ( var i = 0; i < addrs.length; i++) {
				var ip = addrs[i];
				var result=reIp.test(ip);
				if (!result) {
					if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					alert("IP输入错误，请检查");
					return;
					}
				}
			}
			if((ipStart == "" && ipend != "") || (ipend == "" && ipStart != "")){
			alert("IP段两个地址必须同时输入或者同时不输入!");
			return;
			}else if((ipStart == "" && ipend == "")||(ipStart==null&&ipStart==null)){
			}else{
			if(!address()){
			return;
			}
			}
		}
		
		if (desc == ""||desc ==null) {
			alert("描述不能为空！");
			return ;
		}
			//checkName();
			if (!assetFlag) {
				return;
			}
		
		$("#queryRuleForm").submit();
	}

	

	function gobalk() {
		parent.mainFrame.location.href = '${ctx}/scanTask/query.action';
	}
</script>
</head>

<body>
	<s:form action="saveScanTask.action" method="post"
		namespace="/sacnTask" id="queryRuleForm" name="queryRuleForm">
		<input name="scanTask.id" type="hidden" value="${scanTask.id}"/>
		<s:token></s:token>
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 5px; margin-top: 2px">
			<tr>
				<!-- rigth area -->
				<td width="100%" valign="top">
					<div class="framDiv" style="heigh:1000px">
						<table width="100%" cellspacing="1" cellpadding="0" border="0">
							<tr>
								<td class="r2titler" colspan="3"><b>编辑漏扫联动信息</b></td>
							</tr>

							<tr>

								<td class="row"><font
									color="red" >*</font><span>任务名称：</span></td>
								<td><input type="text" name="scanTask.taskName"
									class="validate[required,custom[spechars],custom[validateLength]]"
									id="customQueryEventsName" style="width:270px;"
									value="${scanTask.taskName}" maxlength="50"
									<c:if test="${scanTask !=null }"> readonly="readonly"</c:if>
									<c:if test="${scanTask ==null }">onblur="checkName();"</c:if> />&nbsp;<font
									color="red" id="nameError"></font></td>
							</tr>

							<tr>
								<td class="row"><span>创建时间：</span></td>
								<td><input type="text" readonly="readonly"
									style="width:270px;"
									value="<s:date
										name='scanTask.createDate' format='yyyy-MM-dd HH:mm:ss' />" />
								</td>
							</tr>
							<tr>
								<td class="row"><span>创建者：</span></td>
								<td><input type="text" readonly="readonly"
									style="width:270px;" value="${scanTask.userName}" />
								</td>
							</tr>
							<tr>
								<td class="row">IP地址：</td>
								<td><input type="text" style="width:270px;"
									name="scanTask.ipBunch" id="addr" maxlength="500"
									value="${scanTask.ipBunch}" /> <span style="color: red;">(输入IP地址时，可以输入多个IP，请用“,”分隔，例如：10.70.18.35,10.50.10.0)
								</span></td>

							</tr>
							<tr>
								<td class="row">IP段开始IP：</td>
								<td><input type="text" style="width:270px;"
									name="scanTask.StartIP" id="addrStart" maxlength="20"
									value="${scanTask.startIP}" /> </td>

							</tr>
							<tr>
								<td class="row">IP段结束IP：</td>
								<td><input type="text" style="width:270px;"
									name="scanTask.endIP" id="addrEnd" maxlength="20"
									value="${scanTask.endIP}" /> </td>

							</tr>

							<tr>
								<td align="right" style="padding-left:62px;vertical-align:10%"><span
												class="spanred">*&nbsp;</span>描述：</td>
											<td>
													<textarea cols="35" id ="desc"
													class="validate[required]"
													
														name="scanTask.description"
													
													rows="5">${scanTask.description}</textarea>
											</td>
							</tr>

						</table>
					</div>
				</td>
			</tr>

			<tr>
				<td style="padding:5px 0px 5px 0px; text-align: right;"
					align="right"><input type="button" class="btnyh" id="btnSave"
					value="保   存" onclick="subDate();" /> <input type="button"
					class="btnyh" id="btnCancel" value="取  消" onclick="gobalk();" /></td>
			</tr>
		</table>
	</s:form>

</body>
</html>
