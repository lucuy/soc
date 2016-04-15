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
<title>添加评估项</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
<script type="text/javascript">
	function beginAssess() {
	$.post("${ctx}/technology/saveAssess.action", $("#saveAssessForm").serialize(), function(data) {
			if(data != "error"){
				parent.leftFrame.location.href = "${ctx}/pages/dbPage/cpManage/demand/demandCollect/demandAssessTree.jsp?id="+data;
				//location.href = "${ctx}/pages/cpManage/demand/demandCollect/demandAssessTree.jsp?id="+data;
			}
		}, "text");
	}
</script>
</head>

<body style="margin: 0">
	<form id="saveAssessForm" action="${ctx}/technology/insert.action" method="post">
		<input type="hidden" id="sysGrade" name="sysGrade" value="${rank.rankGrade}" /> <input type="hidden" id="rankId" name="rankId" value="${rank.sysInfoId}" />

		<table width="100%" class="framDiv">
			<!-- 空行 -->
			<tr class="r2titler" align="center">
				<td height="2px" align="center">添加差距评估项</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tab3" border="0" cellspacing="1" cellpadding="0" style="font-size:12px;">
						<tr align="left">
							<td width="11%" height="18" style="text-align:right">差距评估依据：</td>
							<td colspan="3">
								<ul style="margin-left: 20px;">
									<li>《广播电视安全播出管理规定》（总局62号令）</li>
									<li>《广播电视相关信息系统安全等级保护测评要求》</li>
									<li>《广播电视相关信息系统安全等级保护定级指南-发布版》</li>
									<li>《广播电视相关信息系统安全等级保护基本要求-发布版》</li>
								</ul></td>
						</tr>
						<tr>
							<td style="text-align:right">被评估系统：</td>
							<td colspan="3" style="text-align:left"><input id="name" readonly="readonly" style="width:200px" name="name" value="${rank.sysInfoName}" /></td>
						</tr>
						<tr>
							<td style="text-align:right">描述：</td>
							<td colspan="3" style="text-align:left"><textarea id="description" rows="5" name="description" style="width:100%; "></textarea></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td width="100%" valign="middle" align="right" style="padding-top:1px;padding-left:0px;padding-right:4px;"><input type="submit" width="6%" class="btnstyle" value="保存" /> <input type="button" width="6%" class="btnstyle" value="开始评估" onclick="beginAssess()" /></td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
