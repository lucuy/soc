<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>技术差距分析</title>
<!-- 原版 -->
<meta http-equiv="Cache-Control" content="private" /> 
<!-- 解决后退按钮失效 
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0">-->
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>

<script language="javascript">
	function addTechnology() {
 
		if($.trim($("#sysInfoId").val())!=""){
			location.href = "${ctx}/technology/queryBySysInfoId.action?sysInfoId=" + encodeURI(encodeURI($.trim($("#sysInfoId").val()),"utf-8"),"utf-8");
		}else{
			alert("未发现已定级系统，无法添加评估项！");
		}
 
	}

	/*多选框  */
	$("#chkAll").live("click", function(event) {
		if ($("#chkAll").hasClass('not_checked')) {
			$("#chkAll").removeClass('not_checked');
			$(".check-box").attr('checked', true);
		} else {
			$("#chkAll").addClass('not_checked');
			$(".check-box").attr('checked', false);
		}
	});
	//删除
	function deleteTechnology() {
		var ids = "";
		$("[name=ids]:checkbox:checked").each(function() {
			if (ids != "") {
				ids += "," + $(this).val();
			} else {
				ids = $(this).val();
			}

		});
		if ($("[name=ids]:checkbox:checked").size() < 1) {
			alert("请选择要删除的评估项");
			return;
		}

		if (ids != '') {
			if (confirm("确认执行操作吗？")) {
				location.href = "${ctx}/technology/deleteById.action?ids=" + ids + "&sysInfoId=" + $.trim($("#sysInfoId").val());
			}
		}
	}

	//历次评估报告
	function everyTimeAssessResult() {
		var everyTimeSize = $(".everyTimeAR").size();
		if (everyTimeSize > 1) {
			location.href = "${ctx}/assessResult/everyTimeAssessResultReport.action?sysInfoId=" + $.trim($("#sysInfoId").val());
		}else{
			alert("无法进行对比");
		}
	}
</script>

</head>

<body style="margin-left:2px;margin-right: 8px">
	<form action="${ctx}/technology/queryList.action" method="post">
		<input type="hidden" name="sysInfoId" value="${sysInfoId}" id="sysInfoId" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<!-- 空行 -->
			<tr>
				<td height="2px"></td>
			</tr>
			<tr>
				<td>
					<div class="caozuobox">

						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td width="1%"></td>
								<td width="99%" valign="middle" align="left">
								  <input type="button" class="btnstyle1"   onclick="addTechnology()"   value="添加评估项" /> 
								  <input type="button" class="btnstyle1"    onclick="deleteTechnology()"   value="删除评估项" /> 
								  <input type="button" class="btnstyle"   onclick="everyTimeAssessResult()"  value="评估对比" />
							 </tr>
						</table>
					</div> <!-- toolbar area --></td>
			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<table width="100%" border="0" cellspacing="1" id="table-technology" cellpadding="0" class="tab2" style="font-size:12px;">
								<thead>
									<tr height="28">
										<th width="5%" align="center" class="biaoti"><label> <input type="checkbox" id="chkAll" name="checkbox" value="checkbox" class="check-box not_checked" /> </label></th>
										<th width="12%" align="center" class="biaoti" onclick="sortAble('table-technology', 1)" style="cursor:pointer;font-size: 12px;">评估项目名</th>
										<th width="20%" align="center" class="biaoti" style="font-size: 12px;">描述</th>
										<th width="10%" align="center" class="biaoti" onclick="sortAble('table-technology', 2)" style="cursor:pointer;font-size: 12px;">系统等级</th>
										<th width="14%" align="center" class="biaoti" onclick="sortAble('table-technology', 3)" style="cursor:pointer;font-size: 12px;">开始时间</th>
										<th width="14%" align="center" class="biaoti" onclick="sortAble('table-technology', 4)" style="cursor:pointer;font-size: 12px;">结束时间</th>
										<th width="10%" align="center" class="biaoti" style="font-size: 12px;" >当前状态</th>
										<th width="15%" align="center" class="biaoti"  style="font-size: 12px;">评估报告</th>
									</tr>
								</thead>
								<tbody id="tbodyTechnology">
									<s:iterator value="technologyList">
										<tr align="center">
											<td><input name="ids" type="checkbox" class="check-box" value="${id}" />
											</td>
											<td>${name}</td>
											<td><xmp>${describe}</xmp></td>
											<td>${sysGrade}</td>
											<td><fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><c:if test="${currentState=='0'}">
													<a href="${ctx}/pages/dbPage/cpManage/demand/demandCollect/demandAssessTree.jsp?id=${id}" target="leftFrame">开始评估</a>
												</c:if> <c:if test="${currentState=='1'}">
													<a href="${ctx}/assessResult/queryAssessInfo.action?id=${id}&grade=${sysGrade}">评估中……</a>
												</c:if> <c:if test="${currentState=='2'}">
													<input type="hidden" class="everyTimeAR" />已结束</c:if></td>
											<td><s:if test="%{currentState==2}">
													<a href="${ctx}/assessResult/assessResultReport.action?id=${id}">导出报告</a>
												</s:if> <s:else>导出报告</s:else></td>
										</tr>

									</s:iterator>
								</tbody>
								<tr id="tbodyTechnologyTr">
									<td colspan="8" width="100%"><jsp:include page="../../commons/page.jsp"></jsp:include></td>
								</tr>
							</table>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
