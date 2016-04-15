<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
  
    <style type="text/css">
      .displaystyle{
      	display:none;
      }
    </style>
    <title>My JSP 'newreport.jsp' starting page</title>
   
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	  function next1(){
	  	var s=document.getElementById("step1");
	  	s.style.cssText="display:none";
	  	var ss=document.getElementById("step2");
	  	ss.style.cssText="display:block";
	  }
	  function next2(){
	  	var s=document.getElementById("step1");
	  	s.style.cssText="display:block";
	  	var ss=document.getElementById("step2");
	  	ss.style.cssText="display:none";
	  }
	</script>
  </head>
  
  <body>
    <div id="step1">第一步 <input type="button" value="  a  " onclick="javascript:next1()"></div>
    <div id="step2" class="displaystyle">第二步 <input type="button" value="  b  " onclick="javascript:next2()"> </div>
    <div id="step3" class="displaystyle">第三步</div>
    <div id="step4" class="displaystyle">第四步</div>
    <div id="step5" class="displaystyle">第五步</div>
  </body>
</html>
