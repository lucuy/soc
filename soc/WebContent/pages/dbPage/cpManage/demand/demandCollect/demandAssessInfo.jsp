<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>评估项</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/css.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".radioClick").click(function(event){ 
			var sortLength = $(".cRadio :input").length;
			var checkedLength = $(".cRadio :input:checked").length;
			if(2*checkedLength==sortLength){
				$("#assessResultClass0").removeClass(); 
				$("#assessResultClass1").removeClass();
				$("#assessResultClass2").removeClass();
				var abcSort =  $("#abcSort").val();
				var str = new Array();
				str = abcSort.split(",");
				var strs = "";
				for (var i = 1; i < str.length; i++) {
					var a =$("input[name=CIA_"+str[i].toUpperCase()+"]:checked").val();
					if (a && a != "") {
						strs += a;
					}
				}
				//alert(strs);
				var jsArray = new Array();
				jsArray = $("#jsAlg").val().split("-");
				if(jsArray.length==1){
					var jss = new Array();
					jss = jsArray[0].split(",");
					var flg = 0;
					for(var j = 0; j < jss.length; j++){
						if(jss[j]==strs){
							flg++;
							$("#assessResultClass0").addClass("regRight");
							$("#assessResultClass1").addClass("regError");
							$("#assessResultClass2").addClass("regError");
							$("#assessResult").val(0);
						}
					}
					if(flg==0){
						$("#assessResultClass0").addClass("regError");
						$("#assessResultClass1").addClass("regError");
						$("#assessResultClass2").addClass("regRight");
						$("#assessResult").val(2);
					}
				}
				if(jsArray.length==2){
					var jsl = new Array();
					jsl = jsArray[0].split(",");
					var flg = 0;
					for(var l = 0; l < jsl.length; l++){
						if(jsl[l]==strs){
							flg++;
							$("#assessResultClass0").addClass("regRight");
							$("#assessResultClass1").addClass("regError");
							$("#assessResultClass2").addClass("regError");
							$("#assessResult").val(0);
						}
					}
					var jst = new Array();
					jst = jsArray[1].split(",");
					var flgt = 0;
					for(var k = 0; k < jst.length; k++){
						if(jst[k]==strs){
							flgt++;
							$("#assessResultClass0").addClass("regError");
							$("#assessResultClass1").addClass("regRight");
							$("#assessResultClass2").addClass("regError");
							$("#assessResult").val(1);
						}
					}
					if(flg+flgt==0){
							$("#assessResultClass0").addClass("regError");
							$("#assessResultClass1").addClass("regError");
							$("#assessResultClass2").addClass("regRight");
						$("#assessResult").val(2);
					}
				}
				
				
			}
		});
		
		
	});
	function nextSort(sort) {
	    document.getElementById("btnSub").disabled="disabled";
		var sortLength = $(".cRadio :input").length;
		var checkedLength = $(".cRadio :input:checked").length;
		
		if(document.getElementById("description").value.length>1000){
		  		alert("您输入的描述长度超过1000！！！");
		  		document.getElementById("btnSub").disabled="";
		  		return;
		    }
		if (sortLength - 2 * checkedLength > 0) {
			alert("评估项未完全评估，请检查！");
			document.getElementById("btnSub").disabled="";
		} else {
			$("#saveAssessForm").submit();
		}
	}
</script>
<style type="text/css">
	table tr td{
		font-size:12px;
		line-height: 180%
	}
</style>
</head>
<body class="titleTop" >
	<input type="hidden" id="abcSort"  value='<s:property value="#request.abcSort" />' />
	<input type="hidden" id="jsAlg"  value='<s:property value="#request.JsAlg" />' />
	<form id="saveAssessForm" action="${ctx}/assessResult/insert.action" method="post" >
	<input type="hidden" name="sort"  value='<s:property value="#request.sort" />' />
	<input type="hidden" name="acId"  value='<s:property value="#request.acId" />' />
	
		<table width="99.5%" class="framDiv">
			<!-- 空行 -->
			<tr class="r2titler" align="center">
				<td height="2px" align="center">技术差距分析测评实施--<s:property value="#request.sort" /><s:property value="#request.cadname" /></td>
			</tr>
			<tr>
			
			   <td align="left">评估进度<s:property value="#request.processnum" />/34  &nbsp; &nbsp;
			
			      <c:if test="${processnum!=34}">
			                   评估进行中……
			     </c:if>
			     <c:if test="${processnum==34}">
			         本次评估结束
			     </c:if>
			   </td>
			</tr> 
			<tr>
				<td>
					<table width="100%"  border="0" cellspacing="10" cellpadding="10">
						<tr align="center">
							<td width="65%">评估项</td>
							<td width="35%">选项</td>
						</tr>
						<tr>
							<td colspan="2"><div class="xuxian"></div>
							</td>
						</tr>
						<c:forEach items="${demandList}" var="list">
							<c:if test="${fn:substring(list.unitDomainName, 6, 7)=='2'}">
								<tr >
									<c:set var="unitDomainName" value="${fn:substring(list.unitDomainName, 8, 9)}"/> 
									<td align="left">${unitDomainName})&nbsp;${list.unitCon}</td>
									<td align="center" class="cRadio">
										<c:if test="${unitDomainName!='a'}">
											<input class="radioClick" type="radio" <c:if test="${list.CAD_ListGrade=='1'}">checked</c:if> id="CIA_${fn:toUpperCase(unitDomainName)}1"  name="CIA_${fn:toUpperCase(unitDomainName)}" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
											<input class="radioClick" type="radio" <c:if test="${list.CAD_ListGrade=='0'}">checked</c:if> id="CIA_${fn:toUpperCase(unitDomainName)}0"  name="CIA_${fn:toUpperCase(unitDomainName)}" value="0">&nbsp;否
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="2"><div class="xuxian"></div></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr align="center">
							<td  class="fRadio">
							<div  style=" float: right ;overflow: auto;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
								<input type="hidden" name="assessResult" id="assessResult" value="<s:property value="#request.assessResult.CIA_AssessResult"/>"  />
								<span id="assessResultClass2" <s:if test="%{#request.assessResult.CIA_AssessResult==2}">class="regRight"</s:if><s:else>class="regError"</s:else>>不符合</span>
								<span id="assessResultClass1" <s:if test="%{#request.assessResult.CIA_AssessResult==1}">class="regRight"</s:if><s:else>class="regError"</s:else>>部分符合</span>
								<span id="assessResultClass0" <s:if test="%{#request.assessResult.CIA_AssessResult==0}">class="regRight"</s:if><s:else>class="regError"</s:else> >符合 </span>
							</div>
							</td><td>
							</td>
						</tr> 
						<tr>
							<td colspan="2"><div class="xuxian"></div></td>
						</tr>
						<tr>
							<td colspan="2">
								<table width="100%">
									<tr>
										<td width="15%" align="right">主要问题描述:</td>
										<td width="85%" align="left">
										<textarea id="description" rows="5" name="description" style="width:95%;">${assessResult.CIA_MainProbDes}</textarea></td>
									</tr>
								</table>
							</td>
						</tr>
						</table>
						<tr>
							<td align="left" colspan="2">
							<table style="width: 100%" class="framDiv">
							<tr class="r2titler"><td height="2px">技术差距分析测评指标--<s:property value="#request.sort" /><s:property value="#request.cadname" /></td></tr>
							<c:forEach items="${demandList}" var="list">
							<tr><td>
										<c:if test="${fn:substring(list.unitDomainName, 6, 7)=='1'}">
												${fn:substring(list.unitDomainName, 8, 9)})&nbsp;${list.unitCon}
										</c:if>
										</td>
										</tr>
									</c:forEach>
									</table>
								
							</td>
						</tr>
				<%--</td>
			</tr>
			--%><tr>
				<td>
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td width="100%" valign="middle" align="right" style="padding-top:1px;padding-left:0px;padding-right:4px;">
								<s:if test="%{#request.nextSort=='over'}">
									<input type="button" width="6%" class="btnyh" value="完成" id="btnSub" onclick="nextSort('over');" />
								</s:if>
								<s:else>
									<input type="button" width="6%" class="btnyh" value="下一步"  id="btnSub" onclick="nextSort('next');" /> 
								</s:else>
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	</form>
</body>
</html>
