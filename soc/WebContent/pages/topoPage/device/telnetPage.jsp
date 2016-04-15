<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>telnet页面</title>
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
	
	function telnetConnect() {
		//var ids = "";
		 $("#returnMsg").val("正在连接服务器......");
		var deviceIp=$("#deviceIp").val();
		var devicePort = $("#devicePort").val();
		//var pwd1 = $("#pwd1").val();
		//var pwd2 = $("#pwd2").val();
		//alert("123");
			var url = "${ctx}/deviceCategory/telnetConnect.action";
/*			$.post(url, function(data) {
			$("#returnMsg").val(data);
			});*/
			$.ajax({
			      type : "post",
				   url: url,
				   data:{deviceIp:deviceIp,devicePort:devicePort},
				   success : function(result) {
				//		alert(result.resultByAjax);
						$("#returnMsg").val(result);
				   }
			 }); 
		
	}
	function disconnect(){
		var url = "${ctx}/deviceCategory/telnetDisConnect.action";
		$.ajax({
			type:"post",
			url:url,
			success:function(result){
				$("#returnMsg").val(result);
			}
		});
	}
	
	
	function doCmd(){
		var deviceCmd = $("#deviceCmd").val();
		var url = "${ctx}/deviceCategory/doCmd.action";
		$("#returnMsg").val("正在执行命令......");
		$.ajax({
			type:"post",
			url:url,
			data:{deviceCmd:deviceCmd},
			success:function(result){
				$("#returnMsg").val(result);
			}
		});
	}	
	
	function run_dll(appName,methodName){
		
	}
	
</script>

  </head>
  
  <body style="margin: 0">
<%-- <input type="text" name="deviceIp" id="deviceIp"  value="${deviceIp }"/>
		<input type="button" value="PING" onclick="pingIP();" />
		<hr/>
		 <textarea rows="25" style="width:100%" id="returnMsg" name="returnMsg"  readonly="readonly"></textarea> --%>
		  <div style="color: #fff ;margin-top: 5px;" >&nbsp;IP:&nbsp;
				 <input type="text" name="deviceIp" id="deviceIp"  style="height: 30px;"/>&nbsp;&nbsp;
		 端口:
		 			 <input type="text" name="devicePort" id="devicePort" style="width: 45px;height: 30px;"/>
		 	&nbsp;&nbsp;
		 			 <input type="button"  name="connectPort" id="connectPort" value="连 接"  style="width: 100px;height: 30px " onclick="telnetConnect();"/>
		 			 &nbsp;&nbsp;
		 			 <input type="button"  name="disconnectPort" id="disconnectPort" value="断开连接"  style="width: 100px;height: 30px" onclick="disconnect();"/>
		 	</div>
		 	<div style="color: #fff ;margin-top: 8px;" >命令:
		 			 <input type="text" name="deviceCmd" id="deviceCmd"  style="width: 300px; height: 30px;"/>
		 			 &nbsp;&nbsp;
		 			 <input type="button"  name="doCmd" id="doCmd" value="执 行" style="width: 100px;height: 30px " onclick="doCmd();"/>
		 	</div>
		 <table>
		 	<!-- <tr>
		 		<td align="left">
		 			 <div style="color: #fff">IP:
				 <input type="text" name="deviceIp" id="deviceIp"  style="height: 25px;"/></div>
				</td>
				<td align="left">
					<div style="color: #fff"> Port:
		 			 <input type="text" name="devicePort" id="devicePort" style="width: 45px;height: 25px;"/></div>
				</td>
				<td align="left">
					 <input type="button"  name="connectPort" id="connectPort" value="连接" onclick="telnetConnect();"/>
				</td>
				<td align="left">
					<input type="button"  name="disconnectPort" id="disconnectPort" value="断开连接" onclick="disconnect();"/>
				</td>
		 	</tr> -->
		 	<!-- <tr>
		 		<td align="left">
		 			<div style="color: #fff">命令:
		 			 <input type="text" name="deviceCmd" id="deviceCmd"  style="width: 200px; height: 25px;"/></div>
		 		</td>
		 		<td align="left">
		 			 <input type="button"  name="doCmd" id="doCmd" value="执行" onclick="doCmd();"/>
		 		</td>
		 	</tr> -->
		 	
		 </table>
		 <hr/>
		 <textarea rows="20" style="width:100%" id="returnMsg" name="returnMsg"  readonly="readonly"></textarea>
		
		 
		 
		 
  </body>
</html>
