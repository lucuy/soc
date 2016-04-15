<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
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
<h<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>ent.js"></script>

<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script language="javascript">
	$(document).ready(function() {
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

		// 高级-Dialog			
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 450,
			height : 260,
			buttons : {
				"查询[Enter]" : function() {
					extQuery();
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

		$('#dialog-loadAsset').dialog({
			autoOpen : false,
			width : 380,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
					importExcel();
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});

     	$('#listTable').tablesorter();
     	//界面加载的时候判断errorMessage字段是不是空 空的话就不是出现了多人操作的情况
		if(""!="${errorMessage}"){
			alert("${errorMessage}");
		}
	});
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		if($('#keyword').val()!=""){
			if(parseInt($('#keyword').val().length)>50){
				alert("长度不能大于50");
				$('#keyword').val('');
				$('#keyword').focus();
				return;
			}
		}
		
		if(!rege.test($('#keyword').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		//关键字
		$('#keyword').val($.trim($('#keyword').val()));
		location.href = "${ctx}/assetRiskGroup/queryAssetRiskEvaluation.action?keyword="
				+ encodeURI(encodeURI($('#keyword').val(), "utf-8"));
	}

	$(document).click(function(event) {
		//shiftForCheckBoxFun(event);
		
		destroyOverDlg();
	});

	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}

	function extQueryDlg() {
		$('#dialog-extQuery').dialog('open');
		$('#qassetName').val("");
	}

	function extQuery() {
		$('#assetName').val($('#qassetName').val());
		$('#assetSecretValue').val($('#qassetSecretValue').val());
		$('#assetIntegrityValue').val($('#qassetIntegrityValue').val());
		$('#assetUsabilityValue').val($('#qassetUsabilityValue').val());
		$('#assetAssetValue').val($('#qassetAssetValue').val());
		if($('#qassetName').val()!=""){
			if(parseInt($('#qassetName').val().length)>50){
				alert("长度不能大于50");
				return;
			}
		}
		
		if(!rege.test($('#qassetName').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			return;
		}
		$('#queryForm').submit();
	}


	//更新资产状态
	function updateStatus() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});

		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择一个资产信息...");
			return;
		}

		if ($("#status").val() == 100) {
			alert("请选择需要执行的操作...");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/asset/updateAssetStatus.action?ids=" + ids
					+ "&status=" + $("#status").val();
		}
	}
	
	function gotoInfo(assetRiskEvaluationId) {
		location.href = "${ctx}/assetRiskGroup/queryAssetRiskEvaluationInfo.action?assetRiskEvaluationId=" + assetRiskEvaluationId;
	}


	function loadAssetDlg() {
		clearFile();
		$('#dialog-loadAsset').dialog('open');
	}
	function chart(falg,areid){
		location.href="${ctx}/assetRiskGroup/chartsReport.action?falg="+falg+"&areid="+areid;
	}
</script>

</head>
<body style="margin-top:2px;">
	
<s:form action="queryAssetRiskEvaluation.action" namespace="/assetRiskGroup" method="post"
	theme="simple" id="userForm" name="userForm">
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		style="margin-left: 4px; margin-top: 0px">
		<!-- 空行 -->
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
				<div class="box">


				    <%--<div class="right">


							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="location.href='${ctx}/assetRiskGroup/insert.action';" />

						</div> --%>
					<span class="kuaiju">快速搜索</span> <input type="text" id="keyword" style="height: 15px;"
						value="${keyword}" name="keyword" class="jianju" /> <img
						src="${ctx}/images/search.jpg" onclick="query();"
						style="margin-left:5px"> <a href="#" class="jianju"
						onclick="extQueryDlg();">高级</a>
				</div>
			</td>

		</tr>
		<tr>
			<td>
				<div class="sbox">
					<div class="cont">
						<!-- information area -->
						<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="listTable">
									<thead>
									<tr height="28" class="biaoti">
										<th width="20%" class="biaoti">资产名称</th>
										<th width="10%" class="biaoti">保密性价值</th>
										<th width="10%" class="biaoti">完整性价值</th>
										<th width="10%" class="biaoti">可用性价值</th>
										<th width="10%" class="biaoti">资产值</th>
										<th width="10%" class="biaoti">赋值</th>
										<th width="15%" class="biaoti" colspan="2">图表展示</th>
										
									</tr>
									</thead>
									<tbody>
									<s:iterator value="assetRiskEvaluations" status="stat">
									
										<tr>
										
											<td valign="middle"><a href="javascript:gotoInfo(${assetRiskEvaluationId})">${assetName}</a></td>
											<td valign="middle">${assetSecretValue}
											<input type="hidden" name="areid" id="areid" value="${assetRiskEvaluationId}"/>
											</td>
											<td valign="middle" align="center">${assetIntegrityValue}</td>
											<td valign="middle" align="center">${assetUsabilityValue}</td>
											<td valign="middle" align="center">${assetAssetValue}</td>
											<td valign="middle" align="center"><a href="javascript:gotoInfo(${assetRiskEvaluationId})">赋值</a></td>
											<td valign="middle" align="center"> <a href="javascript:chart(1,${assetRiskEvaluationId})">柱状图</a></td>
										<td valign="middle" align="center">	<a href="javascript:chart(2,${assetRiskEvaluationId})">饼形图</a>
											</td>
										</tr>
									</s:iterator>
									</tbody>
									<tr>
										<td colspan="8" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include></td>
									</tr>
								</table>
							</div>
							<!-- information area -->
						</div>
					</div>
				</td>
			</tr>
		</table>
</s:form>
	<!-- ext query from -->
	<s:form action="queryAssetRiskEvaluation.action" namespace="/assetRiskGroup" method="post"
		theme="simple" id="queryForm" name="queryForm">
		<s:hidden name="assetRiskEvaluationId" id="assetRiskEvaluationId" />
		<s:hidden name="assetName" id="assetName" />
		<s:hidden name="assetSecretValue" id="assetSecretValue" />
		<s:hidden name="assetIntegrityValue" id="assetIntegrityValue" />
		<s:hidden name="assetUsabilityValue" id="assetUsabilityValue" />
		<s:hidden name="assetAssetValue" id="assetAssetValue" />
	</s:form>

	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>

	<!-- ui-dialog -->
	<div id="dialog-extQuery" title="高级查询">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="30%">资产名称:</td>
				<td><input id="qassetName" name="qassetName" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>保密性:</td>

				<td><select id="qassetSecretValue" name="qassetSecretValue" 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();">
					<option value="">请选择值</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select>

				<!-- <td><input id="qassetSecretValue" name="qassetSecretValue" type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />

				</td> -->
			</tr>
			<tr>
				<td>完整性:</td>

				<td><select id="qassetIntegrityValue" name="qassetIntegrityValue"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();">
					<option value="">请选择值</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select>

				<!-- <td><input id="qassetIntegrityValue" name="qassetIntegrityValue" type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />

				</td> -->
			</tr>
			<tr>
				<td>可用性:</td>

				<td><select id="qassetUsabilityValue" name="qassetUsabilityValue" 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();">
					<option value="">请选择值</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select>

				<!-- <td><input id="qassetUsabilityValue" name="qassetUsabilityValue" type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />

				</td> -->
			</tr>
			<tr>
				<td>资产值:</td>

				<td><select id="qassetAssetValue" name="qassetAssetValue" 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();">
					<option value="">请选择值</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select>

				<!-- <td><input id="qassetAssetValue" name="qassetAssetValue" type="text" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " 
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />

				</td> -->
			</tr>
		</table>
	</div>

</body>
</html>