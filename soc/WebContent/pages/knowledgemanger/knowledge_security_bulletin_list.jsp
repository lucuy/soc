<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
	<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>



<script type="text/javascript">
	//全选及反选
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});
	//excel
	function exportExcel() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
			   {
				ids += "," + $(this).val();
				
				}
			else
				{
				
				ids = $(this).val();
				
				}

		});
		var messageSecurityTitle = $('#messageSecurityTitle').val();
		var messageSecurityId = $('#messageSecurityId').val();
		var messagePublisher = $('#messagePublisher').val();
		var messageSecurityDate = $('#messageSecurityDate').val();
		var messageSource = $('#messageSource').val();
	if(ids!=""){
		if (confirm("你是要导出选中的安全公告...")) {
			location.href = "${ctx}/securityBulletin/ExportExcel.action?ids="+ids;
		}
	}else{
		if (confirm("你是要导出全部的安全公告...")) {
			location.href = "${ctx}/securityBulletin/ExportExcel.action?keyword="
				+ encodeURI(encodeURI($("#keyword").val(), "utf-8"))+"&messageSecurityTitle="
				+ encodeURI(encodeURI(messageSecurityTitle, "utf-8"))+"&messagePublisher="
				+ encodeURI(encodeURI(messagePublisher, "utf-8"))+"&messageSecurityDate="
				+ messageSecurityDate+"&messageSource="
				+ encodeURI(encodeURI(messageSource, "utf-8"))+"&messageSecurityId" +messageSecurityId;
		}
	}
		
		 $("#dialog-extQuery").dialog("close");
	}
	
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	var reg =/^\d{1,2}$/;
	//导出的方法
	function Export(reportType){
		$("#reportType").val(reportType);
		$("#key").val($("#keyword").val());
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "")
			   {
				ids += "," + $(this).val();
				
				}
			else
				{
				
				ids = $(this).val();
				
				}

		});
		//$("#checkids").val(ids);
		//document.getElementById("keyword").value=encodeURI(encodeURI("安全公告数据表", "utf-8"));
		//$("#export").submit();
		var messageSecurityTitle = $('#messageSecurityTitle').val();
		var messageSecurityId = $('#messageSecurityId').val();
		var messagePublisher = $('#messagePublisher').val();
		var messageSecurityDate = $('#messageSecurityDate').val();
		var messageSource = $('#messageSource').val();
	if(ids!=""){
		if (confirm("你是要导出选中的安全公告...")) {
			location.href = "${ctx}/securityBulletin/ExportReport.action?reportType="
				+reportType+"&ids="+ids+"&securityReportId=10";
		}
	}else{
		if (confirm("你是要导出全部的安全公告...")) {
			location.href = "${ctx}/securityBulletin/ExportReport.action?keyword="
				+ encodeURI(encodeURI($("#keyword").val(), "utf-8"))+"&reportType="
				+reportType+"&securityReportId=10"+"&messageSecurityTitle="
				+ encodeURI(encodeURI(messageSecurityTitle, "utf-8"))+"&messagePublisher="
				+ encodeURI(encodeURI(messagePublisher, "utf-8"))+"&messageSecurityDate="
				+ messageSecurityDate+"&messageSource="
				+ encodeURI(encodeURI(messageSource, "utf-8"))+"&messageSecurityId="+messageSecurityId;
		}
	}
	
	
        $("#dialog-extQuery").dialog("close");
        //提交后激动查询文件是否存在的方法 用ajax 存在后在看
		/*
		var handler = function(){//声明一个定时器 
			var url = "/soc/auditReport/reportFileIsExist.action";
			$.post(url,{"securityReportId":$('#securityReportId').val(),"reportType":reportType},function(data){
			//alert(data);
				if(data){//回调函数 判断文件是否已经生成
					parent.frames[0].cancel();
				    parent.frames[1].cancel();
				    parent.frames[3].cancel();
				    parent.frames[4].cancel();
					clearTimeout(timer);
				}
			});
		};
		var timer = setInterval(handler, 2000);
		*/
		
        
	}
		//高级查询
		function extQueryDlg1() {
			$("#messageSecurityId").val('');
			$("#messageSecurityTitle").val('');
			$("#messagePublisher").val('');
			$("#messageSecurityDate").val('');
			$("#messageSource").val('');
			$('#dialog-extQuery1').dialog('open');
		}
	
	jQuery(document).ready(function() {
		
		//初始化格式对话框
		
		
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 500,
			height : 230,
			buttons: {
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		$('#dialog-extQuery2').dialog({
			autoOpen : false,
			width : 380,
			height : 200,
			buttons : {
				"确定[Enter]" : function() {
					importXML();
					$(this).dialog("close");
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		
		
		// 高级-Dialog			
			$('#dialog-extQuery1').dialog({
				autoOpen : false,
				width : 450,
				height : 300,
				buttons : {
					"查询[Enter]" : function() {
						extquery();
					},
	
					"取消[Esc]" : function() {
						$(this).dialog("close");
					}
				}
			});
			var str = $('#actionStr').val();
	        if (str != "query.action") {
	            var url = "sort.action?" + str;
	            $('#messageForm').attr('action', url);
	        }
		$('#listTable').tablesorter();
	});
	
	function importXML() {

		var upgradeFile = $("#upTar").val();
		if (upgradeFile == "") {
			alert("文件未选择！");
			return;
		} else {
			var start = upgradeFile.lastIndexOf(".");
			if (upgradeFile.substr(start) != ".xls") {
				alert("文件不是xls格式！");
			}

			else {
				//$("#upgradeFile").val(upgradeFile);

				///alert($("#upgradeFile").val());

				// $("#userForm").attr("action","${ctx}/asset/importAsset.action");

				$("#importForm").submit();
				//$("#empForm").attr("action","${ctx}/vulnerability/importVulnerability.action?");

				//$("#empForm").submit();
			}
		}

	}
	
	function extquery(){
		//序号
		var regNum =/^\d{1,9}$/;
		if($('#rel_messageSecurityId').val()!=''&&$('#rel_messageSecurityId').val()!=null){
			if(!regNum.test($('#rel_messageSecurityId').val())){
				alert("请输入数字");
				$('#rel_messageSecurityId').val('');
				$('#rel_messageSecurityId').focus();
				return;
			}
		}
		var messageSecurityId=$('#rel_messageSecurityId').val();
		$('#messageSecurityId').val(messageSecurityId);
		//标题
		if($('#rel_messageSecurityTitle').val().length>50){
			alert("标题长度不能大于50");
			$('#rel_messageSecurityTitle').val('');
			$('#rel_messageSecurityTitle').focus();
			return ;
		}
		if(!rege.test($('#rel_messageSecurityTitle').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#rel_messageSecurityTitle').val('');
			$('#rel_messageSecurityTitle').focus();
			return;
		}
		var messageSecurityTitle = $('#rel_messageSecurityTitle').val();
		$('#messageSecurityTitle').val(messageSecurityTitle);
		//发布人
		if($('#rel_messagePublisher').val().length>50){
			alert("发布人长度不能大于50");
			$('#rel_messagePublisher').val('');
			$('#rel_messagePublisher').focus();
			return ;
		}
		if(!rege.test($('#rel_messagePublisher').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#rel_messagePublisher').val('');
			$('#rel_messagePublisher').focus();
			return;
		}
		var messagePublisher = $('#rel_messagePublisher').val();
		$('#messagePublisher').val(messagePublisher);
		//来源
		if($('#rel_messageSource').val().length>50){
			alert("来源长度不能大于50");
			$('#rel_messageSource').val('');
			$('#rel_messageSource').focus();
			return ;
		}
		var messageSource = $('#rel_messageSource').val();
		$('#messageSource').val(messageSource);
		var messageSecurityDate = $('#rel_messageSecurityDate').val();
		$('#messageSecurityDate').val(messageSecurityDate);
		//$("#queryForm").submit();
		location.href = "${ctx}/securityBulletin/querySecurityBulletin.action?messageSecurityId="+messageSecurityId+"&messageSecurityTitle="
			+ encodeURI(encodeURI(messageSecurityTitle, "utf-8"))+"&messagePublisher="
			+ encodeURI(encodeURI(messagePublisher, "utf-8"))+"&messageSecurityDate="
			+ messageSecurityDate+"&messageSource="
			+ encodeURI(encodeURI(messageSource, "utf-8"));
		$(this).dialog("close");
	}
	

	//更新资产状态
	function bathDelete() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if (ids == "") {
			alert("没有删除对象,请检查");
			return;
		}

		if (confirm("确认执行操作吗？")) {
			// alert(ids);

			//alert($("#status").val());
			location.href = "${ctx}/securityBulletin/deleteSecurity.action?ids="
					+ ids;
		}
	}

	function openwin(hre) {
		location.href = hre;

	}

	//快速检索
	function query() {
		if($('#keyword').val().length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		if(!rege.test($('#keyword').val())){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		//关键字
		var keyword = $("#keyword").val();

		location.href = "${ctx}/securityBulletin/querySecurityBulletin.action?keyword="
				+ encodeURI(encodeURI(keyword, "utf-8"));
	}
	function exportTEMP(){
		location.href = "${ctx}/securityBulletin/exportTEMP.action" ;
	}
	//导入
	function loadAssetDlg(){
		
		if(confirm("请先下载模板，在模板内填写导入数据。（如已下载请继续）")){
			$('#dialog-extQuery2').dialog('open');
			}
	}
	window.onload = function(){
		var info = "${info}" ; 
		if(info != ""){
			
		if(info=="alert"){
			alert("导入的xls格式不正确，请点击模版下载");
		}else{
			alert(info+",请检查导入内容!");
		}
		}
	}
</script>


</head>

<body style="margin-top:2px;">
			<div style="">
			
			</div>

			<div style="">	
			</div>
		
		
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<!-- 空行 -->
			<input type="text" style="display:none;" value="${keyword }"
				name="keyword" id="hiddenLeak" />
			<input type="text" style="display:none;" value="${leakLevel }"
				name="leakLevel" id="hiddenLeak" />
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right">
						
							<input type="button" value="导出" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="$('#dialog-extQuery').dialog('open');" />
								
							<input type="button" value="导入" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="loadAssetDlg()" />
						
							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick='openwin("${ctx}/securityBulletin/toAdd.action?falg=0")' />

							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="bathDelete();" />
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword" maxlength="50"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;" /><a   onclick="query();"><img src="/soc/images/search.jpg"  style="margin-left:5px"></a>
				  			<a href="#" class="jianju" onclick="extQueryDlg1();">高级</a>
				  			<font color="red"><a href="#" style="color: red" onclick="exportTEMP();">模板下载</a></font>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
						
						<s:form action="querySecurityBulletin" method="post"
		namespace="/securityBulletin">
		<s:hidden id="messageSecurityId" name="messageSecurityId"/>
		<s:hidden id="messageSecurityTitle" name="messageSecurityTitle"/>
		<s:hidden id="messagePublisher" name="messagePublisher"/>
		<s:hidden id="messageSecurityDate" name="messageSecurityDate"/>
		<s:hidden id="messageSource" name="messageSource"/>
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="listTable">
                                    <thead>
									<tr align="center" class="biaoti">
										<td width="6%" class="biaoti"><input type="checkbox"
											id="chkAll" name="chkAll" class="check-box not_checked" /></td>
										<th width="10%" class="biaoti">
											序号</th>
										<th width="25%" class="biaoti">
											标题</th>
										<th width="10%" class="biaoti">
											发布人</th>		
							            <th width="15%" class="biaoti">
								                              发布日期</th>
										<th width="" class="biaoti">
											来源</th>	
							</tr>
							   </thead>
							   <tbody>
							 <s:iterator value="list" >
										
								<tr align="center" hight = "10">
								<td class="biaocm" valign="middle"><input
												type="checkbox" name="ids" id="${securityId}"
												value="${securityId}" class="check-box" /></td>
						<td align="left" >${securityId}</td>
									<td align="left" >&nbsp;<a href="selectSecuritById.action?id=${securityId}&falg=0"   title="${securityTitle }"> <c:out value="${securityTitle }"></c:out></a></td>
									<td>${publisher}</td>
									<td align="left" title=""> <s:date format="yyyy-MM-dd" name = "securityDate"/>
									 </td>
									<td>
									<c:out value="${source}"></c:out> 
									 </td>
								</tr>
								
								</s:iterator>
								</tbody>
									<tr>
										<td colspan="7" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include></td>
									</tr>
								</table>
							</div>
							</s:form>
							<!-- information area -->
						</div>
					</div>
				</td>
			</tr>
		</table>
	
  <input type="hidden" id="actionStr" value="${actionStr }" /> 	
	
	
	<!-- 导出的dialog -->
	<div id="dialog-extQuery" title="选择导出格式">
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top">
					<!--  左侧table-->
					<div class="framDiv" style="border:none;">
									<!-- 报表模板内容 -->
									<table width="50%" border="0" cellspacing="0" cellpadding="0"
											style="margin-left: 4px;" align="center">
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
								
											<tr>
												
												<td align="center" width="10%">
												<img src="/soc/images/u21.png">
													<input type="button" class="btnyh"
													id="btnSave" value="导出为DOC格式" onclick="Export('doc');" />
												</td>
												
												<td align="center" width="10%">
												<img src="/soc/images/u23.png">
													<input type="button" class="btnyh"
													id="btnSave" value="导出为excel格式" onclick="exportExcel();" />
												</td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
										</table>
						</div>
				</td>
			</tr>
			
		</table>
	</div>
	
	
	
	<!-- 导入xml -->
	<div id="dialog-extQuery2" title="导入公告对话框">
		<s:form action="importSecurityExcel" namespace="/securityBulletin" method="post"
			theme="simple" id="importForm" name="importForm"
			enctype="multipart/form-data">
			<table width="90%" border="0" cellspacing="5" cellpadding="5"
				align="center">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
				<tr>
					<td valign="30%" width="80px">请选择文件:</td>
					<td valign="middle"><input type="file" id="upTar" name="upTar"
						style="width:170px;" />&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td><span id="span_ip"></span></td>
				</tr>
			</table>
		</s:form>
	</div>
	
	<!-- 高级搜索 -->
	<div id="dialog-extQuery1" title="高级查询">
		
			<table width="90%" border="0" cellspacing="5" cellpadding="5"
				style="margin-left:20px;">
				<tr>
					<td width="25%">序号:</td>
					<td><input id="rel_messageSecurityId"  type="text"
						style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
					</td>
				</tr>
				<tr>
					<td width="25%">标题:</td>
					<td><input id="rel_messageSecurityTitle"  type="text"
						style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
					</td>
				</tr>
				<tr>
					<td width="25%">发布人:</td>
					<td><input id="rel_messagePublisher"  type="text"
						style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
					</td>
				</tr>
				<tr>
					<td width="25%">发布日期:</td>
					<td><input id="rel_messageSecurityDate"  type="text"
						style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" onclick="WdatePicker()" />
					</td>
				</tr>
				<tr>
					<td width="25%">来源:</td>
					<td><input id="rel_messageSource"  type="text"
						style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
					</td>
				</tr>
			</table>
	
	</div>
	
	<%-- <form action="ExportReport.action" name="export1" method="post" id = "export" >
			<input type="hidden"  name="reportType" id="reportType" value = ""/> 
			<input type="hidden"  name="securityReportId" id="securityReportId" value = "10"/>  
			<input type="hidden"  name="keyword" id="key" value=""/>
			<input type="hidden"  name="checkids" id="checkids" value=""/>
		</form>--%>
</body>
</html>
