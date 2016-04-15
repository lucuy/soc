<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="true">
  <head>
    <title>组态报表管理</title>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

	
	<link href="${ctx}/styles/currentlystyle.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		#queryDiv,#contentDiv{
			text-align: center;
			width:96%; 
			white-space:nowrap;
		}
	</style>    
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
   <br><br>
	<div class="title_right" id="titleDiv">组态报表>>组态报表管理</div>
	<div class="sbox">   
  				 <div class="cont">
		<form name="queryForm" id="queryForm" action="" method="post">
			<table width="90%" border="0">
				<tr>
					<td>模板名称</td>
					<td><input type="text" name="formName" style="width:130px; height:22px;"></td>
					<td>模板类别</td>
					<td>
					<select name="formType" id="formType" style="width:130px; height:22px;">
					<option value="1">模板类型1</option>
					<option value="2">模板类型2</option>
					<option value="3">模板类型3</option>
					<option value="4">模板类型4</option>
					</select>
					</td>
					<td><input type="submit" value=" 查 询 "></td>
				</tr>
				<tr>
					<td>创建时间</td>
					<td><input name="startTime" class="Wdate" type="text" value="" readonly onFocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}'})" style="width:130px; height:22px;"/></td>
					<td>至</td>
					<td><input name="endTime" class="Wdate" type="text" value="" readonly onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" style="width:130px; height:22px;"/></td>
					<td><input type="reset" value=" 重 置 "></td>
				</tr>
			</table>
		</form>
	</div>
	</div>
	<div id="contentDiv">
		<table>
		</table>
	</div>
  </body>
</html>
