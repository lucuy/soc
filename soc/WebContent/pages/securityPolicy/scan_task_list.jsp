<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@page import="com.soc.model.user.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>--%>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>
<script type="text/javascript" src="${ctx }/scripts/My97DatePicker/WdatePicker.js"></script>--%>
<script language="javascript" >
	$(document).ready(function() {
		$("#keyword").keydown(function(event) {
			if (event.keyCode == 13) {
				query();
			}
		});

	});

	
	var rege =/^([^`~!@#$%^&*()=|{}':;, \\\[\].<>\/?~！@#￥……&*（）—|{}【】‘；：”“'。\"，、？])*$/;
	//快速检索
	function query() {
		//关键字
		var keyword = $("#keyword").val();
		if(keyword.length>50){
			alert("搜索长度不能大于50");
			$('#keyword').val('');
			$('#keyword').focus();
			return ;
		}
		if(!rege.test(keyword)){
			alert("输入的内容包含特殊字符，请重新输入");
			$('#keyword').val('');
			$('#keyword').focus();
			return;
		}
		location.href = "${ctx}/scanTask/query.action?keyword="
				+ encodeURIComponent(encodeURIComponent(keyword));
	}

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
	$(document).click(function(event) {
		//shiftForCheckBoxFun(event);
		destroyOverDlg();
	});
	function destory(){
		destroyOverDlg();
	}

	function overDlg(e, header, content, num) {
		$('#blk_header').html(header);
		$('#blk_content').html(content);

		createOverDiv(e, 10, 10, $('#blk').html());
	}
	
	function delteCustomThend() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				if($("#scan-"+ $(this).val()).val()==0){
				ids += "," + $(this).val();
				}else{
					alert($("#scanName-"+ $(this).val()).val()+"正在扫描/扫描完成不能删除！");
				}
			} else {
				if($("#scan-"+ $(this).val()).val()==0){
					ids = $(this).val();
					}else{
						alert($("#scanName-"+ $(this).val()).val()+"正在扫描/扫描完成不能删除！");
						return;
					}
				
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择要删除的信息...");
			return;
		}
		if(ids!=""){
		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/scanTask/delete.action?ids=" + ids;
		}
		}
		
		
		
	}
	function startTask(id){
		if (confirm("扫描任务可能因为网络原因会有延时。扫描结果需要等待几分钟！")) {
			location.href = "${ctx}/scanTask/issuedMethod.action?id="+id;
		}
	}
	function detail(id,state){
		if(state == 0){
			location.href = "${ctx}/scanTask/edit.action?id=" + id;
		}else{
			alert("正在扫描/扫描完成不能查看！");
		}
	}

</script>
</head>

<body style="margin-top:2px;">
	
	<s:form action="query.action" namespace="/scanTask" method="post"
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

						<div class="right">
					 
								
							<input type="button" value="添加" class="btnstyle"
								style="margin-right:5px;margin-top:-2px"
								onclick="location.href='${ctx}/scanTask/edit.action';" />

							<input type="button" value="删除"
								style="margin-right:12px;margin-top:-2px" class="btnstyle"
								onclick="delteCustomThend();" />
						</div>

						<span class="kuaiju">快速搜索</span> <input type="text" id="keyword"
							value="${keyword}" name="keyword" class="jianju" style="height: 15px;" /> <img
							src="${ctx}/images/search.jpg" onclick="query();"
							style="margin-left:5px"> 

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
									class="tab2" id="myTable">
									<thead>
										<tr height="28" class="biaoti">
											<td width="6%" class="biaoti"><input type="checkbox"
												id="chkAll" name="chkAll" class="check-box not_checked" />
											</td>
											<th width="10%" class="biaoti header">
											任务名称</th>
											<td width="10%" class="biaoti header">
											IP地址</td>
											<th width="10%" class="biaoti header">
											IP段开始IP</th>
											<th width="10%" class="biaoti header">
											IP段结束IP</th>
											<th width="16%" class="biaoti header">
											描述</th>
											<th width="10%" class="biaoti header">
											创建者</th>
											<th width="10%" class="biaoti header">
											创建时间</th>
											<th width="15%" class="biaoti header">
											状态</th>
											
										</tr>
									</thead>
									<tbody>
										<c:set value="1" var="root" />
										<c:set value="" var="resStr" />
										<s:property value="#session.XXX" />
										<s:iterator value="scanTaskList" status="stat">
											<input type="hidden" name="scanId" id="scan-${id}"
												value="${state}" />
												<input type="hidden" name="scanName" id="scanName-${id}"
												value="${taskName}" />
											<tr>
												<td class="biaocm" valign="middle"><input
													type="checkbox" name="ids" id="${id}" value="${id}"
													class="check-box" />
												</td>
												<td valign="middle"><a
													href="#" onclick="detail('${id}','${state}');">${taskName}</a>
												</td>
												<c:set var="ipList" value="" />
												<s:iterator value='ipList' id="item">
												<c:set var="ipList" value="${ipList}${item}<br/>" />
											</s:iterator>
											<td valign="middle" class="td_list_data italic hand" align="center"
												onmouseover="overDlg(this,'地址列表','${ipList}','');" onmouseout="destory();">
												地址列表
												</td>
												<td valign="middle">
												${StartIP}
												</td>
												<td valign="middle" align="center">
												${endIP}
												</td>
												<td valign="middle" align="center">
												<c:out value="${description}"></c:out>
												</td>
												<td valign="middle" align="center">
												${userName}
												</td>
												<td valign="middle" align="center">
												<s:date name="createDate" format="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td valign="middle" align="center">
												
												<c:if test="${state == 0 }"><font color="red">未扫描&nbsp;&nbsp;</font><input type="button" class="btnstyle" value="开始扫描"
												onclick="startTask('${id}');"
												></c:if>
												<c:if test="${state == 1 }"><font color="#FF9900">正在扫描中...</font></c:if>
												<c:if test="${state == 2 }"><font color="#00FF33"></font>扫描已完成</c:if>
												</td>
												
											</tr>
											<c:set value="${root+1}" var="root" />
										</s:iterator>
									</tbody>
									<tr>
										<td colspan="9" width="100%"><jsp:include
												page="../commons/page.jsp"></jsp:include><br></td>
									</tr>

								</table>
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
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

	

	
	
	
</body>
</html>
