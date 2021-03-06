<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>物理位置选择</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/dbStyles/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css" />
<LINK href="${ctx}/css/basic/css.css" type=text/css rel="stylesheet" />
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
 <style type="text/css">
 *{
 	font-size: 12px;
 }
 </style>
<script type="text/javascript">
	var a = null;
	var b = null;
	var c = null;
	var d = null;
	var e = null;
	var f = null;
	var g = null;
	var h = null;
	var i = null;
	var j = null;
	var conLine = 0;
	var hLinr = 0;
	//遍历评估列表
	<c:forEach items="${psadList}" var="obj">
	var numResult ='${obj.result}';
	var numVal = '${obj.psadSort}';
	var num = numVal.substring(numVal.length - 1);
    if(numResult.length!=0){
    	//将redioVal值赋值到对应的全局变量中
			if (num == "a") {
				a = numResult;
			}
			if (num == "b") {
				b = numResult;
			}
			if (num == "c") {
				c = numResult;
			}
			if (num == "d") {
				d = numResult;
			}
			if (num == "e") {
				e = numResult;
			}
			if (num == "f") {
				f = numResult;
			}
			if (num == "g") {
				g = numResult;
			}
			if (num == "h") {
				h = numResult;
			}
			if (num == "i") {
				i = numResult;
			}
			if (num == "j") {
				j = numResult;
			}
    }
	//统计评估列表中不参与评估的项个数
	<c:if test="${obj.ishave==0 && obj.haveIf==0}">
	  hLinr+=1;
	</c:if>
	//统计评估项个数
	  conLine += 1;
	</c:forEach>

	$(document).ready(function() {
		$(".radioClick").click(function(event) {
			//得到被选择的单选按钮个数
			var checkedLength = $(".cRadio :input:checked").length;
			//拿到点击单选按钮的那么属性值
			var val = $(this).attr("name");
			//得到点击按钮所属评估项编号：A\B\C\D\E\F\G\H\I\J
			var num = val.substring(val.length - 1);
			//得到被点击按钮的val值
			var redioVal = $(this).attr("value");
			//将redioVal值赋值到对应的全局变量中
			if (num == "A") {
				a = redioVal;
			}
			if (num == "B") {
				b = redioVal;
			}
			if (num == "C") {
				c = redioVal;
			}
			if (num == "D") {
				d = redioVal;
			}
			if (num == "E") {
				e = redioVal;
			}
			if (num == "F") {
				f = redioVal;
			}
			if (num == "G") {
				g = redioVal;
			}
			if (num == "H") {
				h = redioVal;
			}
			if (num == "I") {
				i = redioVal;
			}
			if (num == "J") {
				j = redioVal;
			}
			if (checkedLength == (conLine-hLinr)) {
				$("#psaAssessResult0").removeClass(); 
				$("#psaAssessResult1").removeClass();
				$("#psaAssessResult2").removeClass();
                //统计评估项结果
				var radioResult="";
                if(a!=null){
                	radioResult+=a;
                }
                if(b!=null){
                	radioResult+=b;
                }if(c!=null){
                	radioResult+=c;
                }if(d!=null){
                	radioResult+=d;
                }if(e!=null){
                	radioResult+=e;
                }if(f!=null){
                	radioResult+=f;
                }if(g!=null){
                	radioResult+=g;
                }if(h!=null){
                	radioResult+=h;
                }if(i!=null){
                	radioResult+=i;
                }if(j!=null){
                	radioResult+=j;
                }
                
				wancheng(radioResult);
			}
			 
		});
		function wancheng(radioResult) {
			 <%=request.getAttribute("resultAlgorithm")%> 
		};
		function setClass(val){
			if(val=="0"){
				$("#psaAssessResult0").addClass("regRight");
				$("#psaAssessResult1").addClass("regError");
				$("#psaAssessResult2").addClass("regError");
				$("#psaAssessResult").val(0);
			}else if(val=="1"){
				$("#psaAssessResult0").addClass("regError");
				$("#psaAssessResult1").addClass("regRight");
				$("#psaAssessResult2").addClass("regError");
				$("#psaAssessResult").val(1);
			}else if(val=="2"){
				$("#psaAssessResult0").addClass("regError");
				$("#psaAssessResult1").addClass("regError");
				$("#psaAssessResult2").addClass("regRight");
				$("#psaAssessResult").val(2);
			}
		};
		$("#submit1").click(function(event) {
		   document.getElementById("submit1").disabled="disabled";
			<%--表单提交判断--%>
			var checkedLength = $(".cRadio :input:checked").length;
             if(checkedLength != (conLine-hLinr)){
            	 //document.forms[0].submit();
            	document.getElementById("submit1").disabled="";
				alert("请完成评估！");
				return false;
			} 
			if(document.getElementById("psaMainProbDes").value.length>1000){
		  		alert("您输入的描述长度超过1000！！！");
		  		document.getElementById("submit1").disabled="";
		  		return;
		    }
			$("#frm").submit();
			//alert(checkedLength+"xxxxxxxx"+(conLine-hLinr));
		});
	});
	function btnSub(){
	  //   var formName = document.getElementById("frm");
	    // alert(formName.getAttribute("name"));
	     //formName.action="";
		// formName.submit();
	 
	}
	
	
  
</script>
<style type="text/css">
	table tr td {
		line-height: 180%
	}
</style>
</head>
<body>
	 
  <form action="${ctx}/psaInfo/psaAction.action" method="post" id="frm" name="frm">
  <input type="hidden" name="psaSort" value="${psadFatherSort}"/>
   <input type="hidden" name="pasdTime" value="${lastTime}"/>
   <input type="hidden" name="countNum" value="${countNum}"/>
   <input type="hidden" name="psaId" value="${MsaId}"> 
 <table width="99.5%" class="framDiv">
 
			<!-- 空行 -->
			<tr class="r2titler" align="center">
				<td height="2px" align="center">通用物理测评实施--<s:property value="#request.psadfatherid" /><s:property value="#request.psadname" /></td>
			</tr>
			<tr>
			   <td align="left">评估进度${countNum}/6  &nbsp; &nbsp;
			
			       <c:if test="${countNum==6}">
			            结束时间：${lastTime} &nbsp; &nbsp;
			     </c:if>
			       <c:if test="${countNum!=6}">
			            开始时间：${lastTime} &nbsp; &nbsp;
			     </c:if>
			     评估状态：
			     <c:if test="${countNum==6}">
			       <script  type="text/javascript">
			      alert("评估已经结束，是否从新评估！");
			     </script>
			         本次评估结束
			     </c:if>
			     
			    <c:if test="${countNum!=6}">
			                   评估进行中……
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
						<s:iterator value="psadList" id="psadListo"> 
						   <tr>
						     <c:set value="${fn:substring(psadListo.psadSort, 7, 8)}" var="psadSortToUpper" ></c:set>
								<td align="left">${fn:substring(psadListo.psadSort, 7, 8)}) ${psadListo.psadContent}
								</td>
								
							   <s:if test="#psadListo.ishave==0 && #psadListo.haveIf==0">
							       <td></td>
							   </s:if>
							
							   <s:elseif test="#psadListo.ishave==1 && #psadListo.haveIf==0">
							       
                                     <td align="center" class="cRadio">
										<input type="radio" class="radioClick" <c:if test="${result=='1'}">checked</c:if> name="psa${fn:toUpperCase(psadSortToUpper)}" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" class="radioClick" <c:if test="${result=='0'}">checked</c:if> name="psa${fn:toUpperCase(psadSortToUpper)}" value="0">&nbsp;否
									    </td>							   
							   </s:elseif>
							   <s:elseif test="#psadListo.ishave==1 && #psadListo.haveIf==1">
							      <td align="center" class="cRadio">
							            <input type="radio" class="radioClick" <c:if test="${result=='2'}">checked</c:if> name="psa${fn:toUpperCase(psadSortToUpper)}" value="2">条件不成立
										<input type="radio" class="radioClick" <c:if test="${result=='1'}">checked</c:if> name="psa${fn:toUpperCase(psadSortToUpper)}" value="1">&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" class="radioClick" <c:if test="${result=='0'}">checked</c:if> name="psa${fn:toUpperCase(psadSortToUpper)}" value="0">&nbsp;否
									    </td>
							   </s:elseif>
								</tr>
								<tr>
									<td colspan="2"><div class="xuxian"></div></td>
								</tr>
						</s:iterator>
						<tr align="center">
							<td  class="fRadio">
							<div style="float: right ;overflow: auto;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
							<input type="hidden" name="psaAssessResult" id="psaAssessResult" value="<s:property value="#request.unitResult"/>"  />
							<span id="psaAssessResult2" <s:if test="%{#request.unitResult==2}">class="regRight"</s:if><s:else>class="regError"</s:else>>不符合</span>
							<span id="psaAssessResult1" <s:if test="%{#request.unitResult==1}">class="regRight"</s:if><s:else>class="regError"</s:else>>部分符合</span>
							<span id="psaAssessResult0" <s:if test="%{#request.unitResult==0}">class="regRight"</s:if><s:else>class="regError"</s:else>>符合 </span>
							</div>
							</td><td></td>
						</tr>
						<tr>
							<td colspan="2"><br></td>
							  
						</tr>
						<tr>
							<td colspan="2">
								<table width="100%">
									<tr>
										<td width="15%" align="right">主要问题描述:</td> 
										<td width="85%" align="left">
										
<textarea id="psaMainProbDes" rows="5" name="psaMainProbDes" style="width:100%;text-align: left;">${psaDescription.psaMainProbDes}</textarea>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						</table>
						<tr>
							<td align="left" colspan="2">
							<table style="width: 100%" class="framDiv">
							<tr class="r2titler"><td height="2px">通用物理测评指标--<s:property value="#request.psadfatherid" /><s:property value="#request.psadname" /></td></tr>
							<c:forEach items="${cpzbList}" var="list">
								<tr><td>		
									   ${list.psadContent}
								</td></tr>
									</c:forEach>
									</table>
								
							</td>
						</tr>
			<tr>
				<td>
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td width="100%" valign="middle" align="right" style="padding-top:1px;padding-left:0px;padding-right:4px;">
								<input type="button" width="6%" class="btnyh" <s:if test="%{psadFatherSort =='10.6.2'}">value="完成"</s:if><s:else>value="下一步"</s:else>  id="submit1"/>
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
		</form>		
  </body>
</html>
