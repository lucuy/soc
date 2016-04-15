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
  	jQuery(document).ready(function(){
  		var msadFatherSort = $("#msadFatherSort").val();
  		var Msad_FatherSort = $("#Msad_FatherSort").val();
   		parent.mainFrame.location.href = "${ctx}/msadInfo/msadInfoAction.action?msadFatherSort="+msadFatherSort+"&MSAD_FatherSort="+Msad_FatherSort;
  	});
  </script>
  </head>
  <body>
     <input type="hidden" id="msadFatherSort" value="${msadFatherSort}"/>
     <input type="hidden" id="Msad_FatherSort" value="${Msad_FatherSort}"/>
  	跳转页……
  </body>
</html>
