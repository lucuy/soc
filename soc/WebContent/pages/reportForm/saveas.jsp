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
<html>
  <head>  
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
   
    <title>另存为</title>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
   <link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
   <script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
	<style type="text/css">
	body {
    color: #000000;
    font-family: "宋体";
    font-size: 12px;
	}
		table{
	position:absolute;
	top:0px;
	left:100px;
	font-size: 12px;
		}
	</style>
<script type="text/JavaScript" src="../js/jquery-1.2.3.js"></script>
	<script type="text/javascript">
		var exitName = true;
		function confirms(objform){
			var name=objform.asmodelname.value;
			name = name.replace(" ","");
			if(name==""){
				alert("模板名称不能为空!");
				return false;
			}
			if(exitName==true){
				alert("模板名称重复，请重新输入！");
				return false;
			}
			objform.action="newreportform.do?method=saveReportModel&queryResults="+objform.queryResults.value+"&reportinfo="+objform.reportinfo.value+"&levelthree="+objform.levelthree.value+"&saveas=saveas&asmodelname="+objform.asmodelname.value+"&buttons="+objform.buttons.value;
			objform.submit();
		}
		function closewin(){
			onbeforeunload_handler();
 			window.close();
		}
		function checkName(){
			var modelType=$("#asmodelname");
			var name = modelType.val();
			name = name.replace(" ","");
			if(name!=""){
				$.ajax({
					type:"post",
					url:"newreportform.do?method=exitReportFormName",
					data: "name="+encodeURIComponent(name),
					dataType:"text",
					success:function(data){
								var result = $("#result");
								var rstext = "";
								if(data==1){
									rstext = "<font color='red' style='font-size: 12px'>您输入的模板名称已存在，请重新输入 </font>";
									exitName = true;
								}else{
								    exitName = false;
									rstext = "<font color='#0080ff' style='font-size: 12px'>您输入的模板名称不存在，可以使用 </font>";
								}
								$("table").css("position","relative").css("top","0px").css("left","0px");
								result.css("width","250px")
								result.html(rstext);
							}
					  });
			}
			
		}
	</script>
	
  </head>
  
  <body>
  <html:form action="newreportform.do" method="post">
  	${msg }
    <table id = "showTable" >
    	<tr>
    		<td height="20px" width="50%"></td> 
    		<td></td> 
    	<tr>
    	<tr>
    		<td align="right">模板名称：</td>
    		<td><input type="text" name="asmodelname" id="asmodelname" value="" onBlur="checkName();" >
    			<div id="result">&nbsp;</div>
    		</td>
    	</tr>
    	<tr>
    		<td align="center" colspan="2"> <input type="button" name="yes" class="btnstyle" value=" 确 定 " onClick="javascript:confirms(document.ReportForm);">
    		<input type="button" name="no" class="btnstyle" value=" 取 消 " onClick="javascript:window.close();"></td>
    	</tr>
    </table>
	<logic:notEmpty name="msg">
		<div id="succuss">
		<a onClick="javascript:window.close();">三秒钟后窗口自动关闭</a>
		<script type="text/javascript">
			setTimeout("closewin();", 3000);
			    window.onbeforeunload = onbeforeunload_handler;   
			    function onbeforeunload_handler(){   
			      window.opener.location.href="reportFormQuery.do?method=initPage";
			    }   
			// -->   
		</script>
		</div>
	</logic:notEmpty>
	<input type="hidden" name="reportinfo" value="${reportinfo }">
    <input type="hidden" name="leveltwo" value="${leveltwo }">
    <input type="hidden" name="levelthree" value="${levelthree }">
    <input type="hidden" name="arrays" value="${arrays }">
 	<input type="hidden" name="buttons" value="${buttons }">
    <input type="hidden" name="queryResults" value="${queryResults }">
    <input type="hidden" name="saveas" value="${saveas }">
    <input type="hidden" name="coordx" value="${coordx }">
	<input type="hidden" name="coordy" value="${coordy }">
    </html:form>
  </body>
</html>
