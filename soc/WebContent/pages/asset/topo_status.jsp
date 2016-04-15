<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<link href="${ctx}/styles/asset/assetinfo.css" rel="stylesheet"
	type="text/css">

 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/fontColor.css" rel="stylesheet"
    type="text/css"> 

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/jquery-latest.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/jquery.tablesorter.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script language="javascript">
	
	function loadAssetDlg()
	{ 
		
		//alert("123123");
		if(confirm("确认执行自动探测吗？这将消耗一定的时间"))
		{
		   parent.frames[0].reload(); 
	       parent.frames[1].reload(); 
	       parent.frames[3].reload();
	       parent.frames[4].reload();
		   
	$.post("${ctx}/asset/updateTopo.action",{},function(data){
	 alert(data);
	 $("#topostatus").html("探测完成");
	 $("#mack").html("");
	     parent.frames[0].cancel();
         parent.frames[1].cancel();
         parent.frames[3].cancel();
         parent.frames[4].cancel();
         window.open("${ctx}/flexhtml/BusinessModel.html")
         //alert(policyMemo);
         //去掉弹出框的多选框的选择状态
		});

		}
		
	}
	
	 function reload() {
		    document.onmousedown=ContextMenu;
	        $("#mack").addClass("ui-widget-overlay");
	        $("#mack").css("height",document.documentElement.clientHeight);
	        $("#topostatus").html("探测中");
	        $("#mack").html("<center><font size='38px' color='red'>拓扑自动发现中，请稍后操作！！</font></center>");
	       // location.href = "${ctx}/asset/updateTopo.action";
	        //$("form").submit();
	        //openSuccess("hide","文件上传中请稍候...");
	       // $("#img").css("display", "block");
	        //$("#prog").text("文件上传中");
	    }
	 
	 function ContextMenu(){
	      if (event.button==2 || event.button==3) {  
	             alert("自动探测中，无法操作");
	             return false;
	        }
	  }
	 
	  function cancel() {
			document.onmousedown = null;
			$("#mack").removeClass("ui-widget-overlay");
			$("#mack").css("height",0);
		}
	$(document).ready(function() {
		   
	});
	
</script>

</head>

<body style="margin-top:2px;">
	
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 6px; margin-top: 0px">
			<!-- 空行 -->
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<div class="box">

						<div class="right">
						    <span style="margin-right:5px;margin-top:-2px"/>拓扑状态:<span id="topostatus">探测</span> &nbsp;&nbsp;
							<input type="button" value="执行探测" class="btnstyle" 
								style="margin-right:5px;margin-top:-2px" onclick="loadAssetDlg();" />
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<center></center>
							</div>
							<!-- information area -->
						</div>
					</div></td>
			</tr>
		</table>
	<!-- over layer -->
	<div id="blk" style="display:none;">
		<div class="blk">
			<div class="head">
				<div class="head-right"></div>
			</div>
			<div class="main">
				<h2 id="blk_header">#header#</h2>
				<div id="blk_content">#content#</div>
			</div>
			<div class="foot">
				<div class="foot-right"></div>
			</div>
		</div>
	</div>

    <div class="ui-overlay">
        <div id="mack"></div>
     
    </div>

</body>
</html>
