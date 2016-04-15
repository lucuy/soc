<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
	<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css">
		
	<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>		
  </head>
  
  <body>
    <div class="aboutContainer">
    	<div class="aboutBg">
    		<div class="aboutInfo">
    			<table width="100%" border="0" cellpadding="15" cellspacing="0">
    			<tr>
    				<td>版本类型：${verTypeDesc}</td>
    			</tr>
    			<tr>
    				<td>版本号：${ver}</td>
    			</tr>
    			
    			<c:if test="${verType eq '0'}">
    			<tr>
    				<td>剩余天数：${overDay}天</td>
    			</tr>
    			</c:if>
    			
    			<tr>
    				<td>资源总数：${resourceNum}个</td>
    			</tr>
    			<tr>
    				<td>剩余数量：${overResourceNum}个</td>
    			</tr>
    			</table>
    		</div>
    	</div>
    </div>
  </body>
</html>
