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
    <title>跳转</title>
  <script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
  <style type="text/css">
 *{
 	font-size: 12px;
 }
 </style>
  <script type="text/javascript">
  $(document).ready(function(){
  		//var psadFatherSort = $("#psadFatherSort").val();
  		//var PSAD_FatherSort = $("#PSAD_FatherSort").val();
  		var psadFatherSort ="${psadFatherSort}";
  		var PSAD_FatherSort ="${PSAD_FatherSort}";
  		//alert(psadFatherSort+"ssssssssssssss"+PSAD_FatherSort);
  		parent.mainFrame.location.href = "${ctx}/psadInfo/psadAction.action?psadFatherSort="+psadFatherSort+"&PSAD_FatherSort="+PSAD_FatherSort;
   		//parent.mainFrame.location.href = "${ctx}/psadInfo/psadAction.action?psadFatherSort="+psadFatherSort+"&PSAD_FatherSort="+PSAD_FatherSort;
  	});
  	<%--	function tiaozhuan(){
  		var psadFatherSort ="${psadFatherSort}";
  		var PSAD_FatherSort ="${PSAD_FatherSort}";
  		alert(psadFatherSort);
  		alert(PSAD_FatherSort);
  		//alert(psadFatherSort+"ssssssssssssss"+PSAD_FatherSort);
  		parent.mainFrame.location.href = "${ctx}/psadInfo/psadAction.action?psadFatherSort="+psadFatherSort+"&PSAD_FatherSort="+PSAD_FatherSort;
  	}
  --%></script>
  </head>
 <body  <%--onload="tiaozhuan();"--%>>
 
     <input type="hidden" id="psadFatherSort" value="${psadFatherSort}"/>
     <input type="hidden" id="PSAD_FatherSort" value="${PSAD_FatherSort}"/>
  	跳转页……
  </body>
</html>
