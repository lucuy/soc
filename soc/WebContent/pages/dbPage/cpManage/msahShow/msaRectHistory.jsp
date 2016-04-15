<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'msaShow.jsp' starting page</title>
    <LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="${ctx}/css/basic/css.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/basic/basic.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.core.js"></script>
<SCRIPT type=text/javascript   src="${ctx}/scripts/util.js"></SCRIPT>
<SCRIPT type=text/javascript   src="${ctx}/scripts/popuplayer.js"></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js}"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type='text/javascript'>
	

		
	$(document).ready(function() {
	       var msaDate=document.getElementById("msaDate");
	       var type=document.getElementById("type");
		    jsonForAjax("${ctx}/msaRect/queryMsaReHistory.action?msaDate="+ msaDate.value+"&type="+type.value+ "&t=" + new Date());

	    }
	);
	
	function jsonForAjax(url){
		var htmlStr = "";	
		$.getJSON(url ,function(result){
			//$("#table-userManage tr:not(:first)").remove();
			$("#tbody").html("");
			$.each(result.processLog, function(i,item) {
			 	//var rowNum = $("#table-userManage tr").length-1;
			 	var tBodyHtml = $("#tbody").html();
			 	if(item.sonContent!= null){
			 		htmlStr = "";
			 		htmlStr += "<tr>";
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:center\">";
			 			  htmlStr += item.gFatherSort+" "+item.gFatherName;	
			 			htmlStr += "</td>";	
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:center\">";
			 			  htmlStr += item.fatherSort+" "+item.fatherName;
			 			htmlStr += "</td>";	
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:left\">";
			 			  htmlStr += item.sonContent;
			 			htmlStr += "</td>";
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:center\">";
			 			  htmlStr += item.msaRectAdvise;
			 			htmlStr += "</td>";
			 			htmlStr += "<td  style=\"cursor:pointer;text-align:center\">";
			 			  htmlStr += item.msaRectDate;
			 			htmlStr += "</td>";
			 		htmlStr += "</tr>";
			 	}
			 	//$(htmlStr).insertAfter($("#table-userManage tr:eq("+rowNum+")"));
			 	$("#tbody").html(tBodyHtml+htmlStr);
			 });				
		});
		

	}
	</script>

  </head>
  
  <body style="padding-left:2px">
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0"  >
			<tr>
				<td>
				 <div class="caozuobox">
					<form action="${ctx}/msaRect/msaexportexcel.action" method="post" width="0px" height="0px">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30" >
							<td> <button type="submit" class="btnstyle" style="margin-left: 2px">导出列表</button></td>
								 <td width="100" valign="middle">
					                 <input type="hidden"  name="msaDate" id="msaDate"  value="<s:property value="#parameters.msaDate"/>" width="0px" height="0px">
   					 <input type="hidden" name="type" id="type" value="<s:property value="#parameters.type"/>"  width="0px" height="0px">
								</td>
								<td width="*">&nbsp;</td>
							</tr>
						</table>
						 </form>
					</div>
				
				
				
				
					 
				</td>

			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-userManage" style="font-size:12px;">
								<thead>
									<tr >				 
										<th width="15%"  style="font-size: 12px;"  class="biaoti" onclick="sortAble('table-userManage', 0)"  style="cursor:pointer" >控制域</th>
										<th width="15%"  style="font-size: 12px;"  class="biaoti" onclick="sortAble('table-userManage', 1)"  style="cursor:pointer" >控制单元</th>
										<th width="30%"  style="font-size: 12px;"  class="biaoti" onclick="sortAble('table-userManage', 2)"  style="cursor:pointer" >不符合项</th>
										<th width="30%"  style="font-size: 12px;"  class="biaoti" onclick="sortAble('table-userManage', 3)"  style="cursor:pointer" >整改建议</th>
										<th width="10%"  style="font-size: 12px;"  class="biaoti" onclick="sortAble('table-userManage', 4)"  style="cursor:pointer" >整改时间</th>
									</tr>
									</thead>
									<tbody  id="tbody">
									</tbody>
								
								</table>
							</div>

						</div>
					</div>
				</td>
			</tr>
		</table>
   
	
  </body>
</html>
