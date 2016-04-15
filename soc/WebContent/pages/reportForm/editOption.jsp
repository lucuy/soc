<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
   <title>友情提示</title>
<link href="/model/img/rigcss.css" rel="stylesheet" type="text/css" />
<script>
function sb()
{
   var tt = window.document.getElementById("T1").value;
   if(tt=="")
   { 
    alert("请添名称");return false;
   }
   else
   {
    window.returnValue=tt;window.close();
   }
}
</script>
</head>
<body onload="T1.focus();" onkeydown="if(event.keyCode==27)sb()"
   bgcolor="#FFFFFF" style="font-size:9.5pt; margin-left:10px;">
   <div style="padding:5px 0px 5px 0px">
    请输入名称

   </div>
    <!-- <input type="text" name="T1" class="input1" style="width: 150px;" maxlength="100" onkeydown="if(event.keyCode==13)sb();">-->
    <textarea name="T1" rows="4" cols="25" onkeydown="if(event.keyCode==13)sb();"></textarea>
    <br>
    <input id="B1" type="button" value="确 定" onclick="sb()"
     class="but02">
    <input id="B2" type="button" value="取 消" onclick="window.close();"
     class="but02">
  
</body>
</html>

