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
		location.href = "${ctx}/customTrend/query.action?keyword="
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
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}
		});
		if ($(":checkbox[checked = true]").size() < 1) {
			alert("请至少选择要删除的信息...");
			return;
		}
		
		
		
		if (confirm("确认执行操作吗？")) {
			location.href = "${ctx}/customTrend/delteCustomThend.action?ids=" + ids;
		}
	}
	
	

</script>
</head>

<body style="margin-top:2px;">
	
	<s:form action="query.action" namespace="/customTrend" method="post"
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
								onclick="location.href='${ctx}/customTrend/editCustom.action';" />

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
											名称</th>
											<th width="8%" class="biaoti header">
											地址类型</th>
											<td width="10%" class="biaoti header">
											地址</td>
											<th width="8%" class="biaoti header">
											时间类型</th>
											<th width="8%" class="biaoti header">
											相对时间</th>
											<th width="15%" class="biaoti header">
											开始时间</th>
											<th width="15%" class="biaoti header">
											结束时间</th>
											<th width="8%" class="biaoti header">
											创建者</th>
											
										</tr>
									</thead>
									<tbody>
										<c:set value="1" var="root" />
										<c:set value="" var="resStr" />
										<s:property value="#session.XXX" />
										<s:iterator value="ctList" status="stat">
											<input type="hidden" name="trendId" id="trendId"
												value="${trendId}" />
											<tr>
												<td class="biaocm" valign="middle"><input
													type="checkbox" name="ids" id="${trendId}" value="${trendId}"
													class="check-box" />
												</td>
												<td valign="middle"><a
													href="${ctx}/customTrend/editCustom.action?trendId=${trendId}">${trendName}</a>
												</td>
												<td valign="middle"><c:if test="${trendCustom1 eq '1' }">源地址</c:if>
												<c:if test="${trendCustom1 eq '2' }">目标地址</c:if>
												<c:if test="${trendCustom1 eq '3' }">设备地址</c:if>
												</td>
												<c:set var="customIps" value="" />
												<s:iterator value='ipList' id="item">
												<c:set var="customIps" value="${customIps}${item}<br/>" />
											</s:iterator>
											<td valign="middle" class="td_list_data italic hand" align="center"
												onmouseover="overDlg(this,'地址列表','${customIps}','');" onmouseout="destory();">
												地址列表
												</td>
												<td valign="middle"><c:if test="${days == 0}">绝对时间</c:if>
												<c:if test="${days != 0}">相对时间</c:if>
												</td>
												<td valign="middle" align="center">
												<c:if test="${days > 1}">最近${days}天</c:if>
												<c:if test="${days eq 1}">本日</c:if>
												
												</td>
												<td valign="middle" align="center">
												${startTime}
												</td>
												<td valign="middle" align="center">
												${endTime}
												</td>
												<td valign="middle" align="center">
												${trendCreateUser}
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
