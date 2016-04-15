<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>设备类型信息的添加和修改页面</title>
   <link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<link href="${ctx}/css/imageselect.css" media="screen" rel="stylesheet" type="text/css" /> 

<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/devicecategoryphoto/imageselect.js"></script>
 
<style type="text/css">
	.tdleft{
 		width: 20%;
 		text-align: right;
 	}
 	td{
 		font-size: 12px;
 	}
 	body{
 		background-color: #000000;
 		
 	}
 	input{
 		background-color: #000000;
 		color: #ffffff;
 		font-family: "黑体";
 	}
 	textarea{
 		background-color: #000000;
 		color: #ffffff;
 	}

</style>
<script type="text/javascript">
	
	
		var nameFlag = false;
		function checkDeviceCategoryName(){

		var dName=$("#deviceCategoryName").val();
		if(dName !=""){
			if(dName.length >20){
			nameFlag=true;
			$("#div_deviceCategoryName").addClass("spanred");
			$("#div_deviceCategoryName").html("设备类型名称长度不能大于20个汉字");
			}else{
				$("#div_deviceCategoryName").html("");
				nameFlag=false;
			}
		}else{
			nameFlag=true;
			$("#div_deviceName").addClass("spanred");
			$("#div_deviceName").html("设备名称不能为空！");
		}
	}
	
	function save(){
	//	var Name=$("#deviceCategoryName").val();
		//if(Name.length==0){
		//	alert('请输入设备名称');
			//return;
	//	}
		if(nameFlag){
			alert('请检查设备类型名称');
			return;
		}
		
		$("#deviceCategoryForm").submit();
	}
	
	function back(){
		location.href="${ctx}/deviceCategory/queryAllDeviceCategory.action";
	}
	
	$(document).ready(function(){
		$('select[name=deviceCategoryPhotoPath]').ImageSelect({dropdownWidth:425});
	});
	
	function pingIP() {
		//var ids = "";
		var deviceIp=$("#deviceIp").val();
		//alert("123");
			var url = "${ctx}/deviceCategory/pingDevice.action";
/*			$.post(url, function(data) {
			$("#returnMsg").val(data);
			});*/
			$.ajax({
			      type : "post",
				   url: url,
				   data:{deviceIp:deviceIp},
				   success : function(result) {
				//		alert(result.resultByAjax);
						$("#returnMsg").val(result);
				   }
			 }); 
		
	}
</script>

  </head>
  
  <body style="margin: 0">
<input type="text" name="deviceIp" id="deviceIp"  value="${deviceIp }"/>
		<input type="button" value="PING" onclick="pingIP();" />
		<hr/>
		 <textarea rows="25" style="width:100%" id="returnMsg" name="returnMsg"  readonly="readonly"></textarea>
  </body>
</html>
