<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息导入</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/main.css" type="text/css" />

  </head>
  
  <body>
   <script type="text/javascript">
function validateForm() {
	return true
		&& validateString(cpUnitBaseinfoForm.mainAttach, 'XML文件路径', 1, 500)
}

function checkFile() {
	var fileName = document.getElementById("filedata").value;
	var oForm = document.getElementById("uploadForm");
		
	var suffix = fileName.slice(fileName.lastIndexOf("."));

	if(fileName == "" || fileName == null) {
		alert('请先选择要导入的文件！');
		return;
	}
	
	if (".xml" == suffix) {
		oForm.action = oForm.action + suffix;
		oForm.submit();
	} else
		alert("请使用 .xml 格式文件!");
}

</script>

<form id="uploadForm" ENCTYPE="multipart/form-data" method="post" action="/servlet/UploadFile.do?method=importUnitBaseInfo&category=">
<div id="divAction">
	<div id="divCaption">导入【单位信息】</div>
	<input class="button" type="button" value="开始导入" onclick="checkFile()" class="btn">
	<input class="button" type="button" name="btnReturn" value="返回" onclick="goBack()" class="btn">
</div>
	
	
<div id="divDetail">
	<table border="0" width="100%" class="basetable">
		<col width="100">
		<col>
		<col width="80">
		<col>
		
	
		<tr>
			<td class="col">选择导入文件：</td>
			<td colspan="5">
				<input type="file" enctype="multipart/form-data" name="filedata" class="txt">
			</td>
		</tr>
		<tr>
			<td class="col">操作提示：</td>
			<td colspan="5">
				<font size="2" color=darkgreen>从等级保护备案端软件XML文件导入，文件名：orgdata.xml</font>
			</td>
		</tr>
		
	</TABLE>
</div>

</form>
</body>
</html>
