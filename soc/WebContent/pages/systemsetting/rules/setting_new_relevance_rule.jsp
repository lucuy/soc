<%@ page contentType="text/html; charset=utf-8" import="java.util.*"
	import="com.scan.model.role.*" import="java.text.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>安全管理平台综合监控</title>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/images/favicon.ico" rel="shortcut icon" />
<link href="${ctx}/images/favicon.ico" rel="Bookmark" />

<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/systemsetting/systemsetting.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/comprehensivemonitoring.css" rel="stylesheet" type="text/css">

<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet" type="text/css" />

<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css" rel="stylesheet" type="text/css" />

<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet" type="text/css" />

<style type="text/css">
table{
font-size:12px;
}
#back a:link {
	color: #FFFFFF;
	text-decoration: none;
}

#back  a:visited {
	text-decoration: none;
	color: #FFFFFF;
}

#back   a:hover {
	color: #faec06;
	text-decoration: underline;
}

#back  a:active {
	color: #FFFFFF;
}
</style>

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>

<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>

<script type='text/javascript' src="${ctx}/scripts/paging1.js"></script>
<!-- jsDate 格式化时间的函数 -->

<script type='text/javascript' src="${ctx}/scripts/jsDate.js"></script>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
	<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>

<script type='text/javascript' src="${ctx}/scripts/treeview.js"></script>
<script type="text/javascript" src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script><%--

前台分页

--%><script type='text/javascript' src="${ctx}/scripts/filterpage.js"></script>
<style type="text/css">
.biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
	font-size: 12px;
}

.hand {
	cursor: hand;
	background: #ccccff;
}

.eventslist {
	background: none repeat scroll 0 0 #D2E8FA;
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
}

.eventslist tr td {
	line-height: 28px;
	text-align: center;
}

.back {
	background: #FFFFFF;
	font: 12px;
	font-family: "宋体";
}

.conn {
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	position: absolute;
	z-index: -1;
	width: 99%;
}

.dialog-enentsContent {
	overflow: auto;
	font-size: 12px;
	color: #000000;
	margin: 0 auto;
	font-family: 宋体;
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4;
}

.btnstyle {
	background: url(/soc/images/btnbg.jpg) no-repeat;
	border: none;
	width: 66px;
	height: 21px;
	cursor: pointer;
	color: #265D86;
	align: center;
}

.column {
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	border-left: 1px solid #D2E8FA;
	border-top: 1px solid #D2E8FA;
	border-right: 1px solid #D2E8FA;
}

.title {
	margin: 0px 0px 0px 0px;
	padding: 0px 0px 0px 0px;
	color: #000000;
	background: url(/soc/images/tdBg.jpg) repeat-x left;
	height: 31px;
}

.title_t {
	position: relative;
	left: 10px;
	top: 5px;
}

.img_a {
	vertical-align: -5px;
	cursor: hand;
}

.tit {
	margin: 0px 0px 0px 5px;
}

.rowLT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
	font: 12px;
	font-family: "宋体";
}

.rowLV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	border-right: 0px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 250px;
	font: 12px;
	font-family: "宋体"
	
}

.rowRT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
	font: 12px;
	font-family: "宋体";
}

.rowRV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	font: 12px;
	font-family: "宋体";
}

.screentable {
	background: none repeat scroll 0 0 #D2E8FA;
}

.screentable td {
	background: none repeat scroll 0 0 #FFFFFF;
	line-height: 28px;
	text-align: center;
}

.screentable .td_t {
	background: none repeat scroll 0 0 #ffffff;
	line-height: 28px;
	text-align: left;
	padding-left: 20px;
}

#screentable2 {
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #D2E5F4;
	scrollbar-highlight-color: #D2E5F4;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color: #FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #D2E5F4
}

.loding {
	font-size: 14px;
	border: 3px solid #D2E8FA;
	position: absolute;
	z-index: 10;
	background: #FFFFFF;
	width: 200px;
	height: 60px;
	padding-top: 25px;
	text-align: center;
	maring: 0px auto;
	display: none;
}

.hideDiv {
    left: 0px;
    top: 0px;
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	background-image: url("${ctx}/images/hide.png");
	background-position: 0% 0%;
	background-repeat: repeat;
	background-attachment: scroll;
	position: absolute;
	z-index: 1;
	filter: progid:DXImageTransform.Microsoft.Alpha(style=1, opacity=20,
		finishOpacity=20 );
}
</style>

<script type="text/javascript">
jQuery(document).ready(function() {

	
	//设备divalert(1);
	
	jQuery("#from").validationEngine();
	$('#dialog-asset').dialog({
		autoOpen : false,
		width : 450,
		height :450,
		buttons : {
			"确定[Enter]" : function() {
				addOk('assetSelect','dialog-asset','chk-asset');
			},

			"取消[Esc]" : function() {
				$(this).dialog("close");
			}
		}
	});
	//事件类别div
	$('#dialog-eventCategory').dialog({
		autoOpen : false,
		width : 450,
		height : 450,
		buttons : {
			"确定[Enter]" : function() {
				addOk('eventCategorySelect','dialog-eventCategory','chk-eventCategory');
			},

			"取消[Esc]" : function() {
				$(this).dialog("close");
			}
		}
	});
	//关联后事件类别div
	$('#dialog-eventCategoryRe').dialog({
		autoOpen : false,
		width : 450,
		height:450,
		buttons : {
			"确定[Enter]" : function() {
				add_radio('eventCategorySelectRe','dialog-eventCategoryRe');
			},

			"取消[Esc]" : function() {
				$(this).dialog("close");
			}
		}
	});
	//事件类型div
	$('#dialog-eventType').dialog({
		autoOpen : false,
		width : 450,
		height : 450,
		buttons : {
			"确定[Enter]" : function() {
				addOk('eventTypeSelect','dialog-eventType','chk-eventsType');;
			},
			"取消[Esc]" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	//关联后事件类型div
	$('#dialog-eventTypeRe').dialog({
		autoOpen : false,
		width : 450,
		height : 450,
		buttons : {
			"确定[Enter]" : function() {
				add_radio('eventTypeSelectRe','dialog-eventTypeRe');
			},
			"取消[Esc]" : function() {
				$(this).dialog("close");
			}
		}
	});
	//源地址
	$('#dialog_sourceIp').dialog({
		autoOpen : false,
		width : 400,
		height : 270,
		buttons : {
			"确定[Enter]" : function() {
				// 获得前一状态的源地址 的这个select的 选择内容 如果不是输入内容按以前的流程走
				var sourceAddVal = $("#sourceAdd").val();
				//alert(sourceAddVal);
				if(sourceAddVal == "输入内容"){
					if(checkIp('sourceAddrStart','div_sourceAddrStart')){
						if(judgeRepeatedSelect('sourceAddr','sourceAddrStart')){
							var sourceAddrStart = $("#sourceAddrStart").val();
							$("#sourceAddr").append("<option value="+$("#sourceAddEql").val()+":"+sourceAddrStart+">"+$("#sourceAddEql").val()+"："+sourceAddrStart+"</option>");
							$("#sourceAddrStart").val("");
							$("#div_sourceAddrStart").html("");
							$(this).dialog("close");
						}else{
							$("#div_sourceAddrStart").html("添加数据有误，请检查！");
						}
					}
				}else{
						$("#sourceAddr").empty();
						var sourceAddrStart = $("#sourceAddrStart").val();
						$("#sourceAddr").append("<option value="+$("#sourceAddEql").val()+":"+sourceAddVal+">"+$("#sourceAddEql").val()+"："+sourceAddVal+"</option>");
						$("#sourceAddrStart").val("");
						$("#div_sourceAddrStart").html("");
						$(this).dialog("close");
				}
				
			},
			"取消[Esc]" : function() {
				$("#sourceAddrStart").val("");
				$("#div_sourceAddrStart").html("");
				$(this).dialog("close");
			}
		}
	});
	
	//目标地址
	$('#dialog_targetIp').dialog({
		autoOpen : false,
		width : 420,
		height : 270,
		buttons : {
			"确定[Enter]" : function() {
				// 获得前一状态的源地址 的这个select的 选择内容 如果不是输入内容按以前的流程走
				var targetAddVal = $("#targetAdd").val();
				//alert(targetAddVal);
				if(targetAddVal == "输入内容"){
					if(checkIp('targetAddrStart','div_targetAddrStart')){
						if(judgeRepeatedSelect('targetAddr','targetAddrStart')){
							var targetAddrStart = $("#targetAddrStart").val();
							$("#targetAddr").append("<option value="+$("#targetAddEql").val()+":"+targetAddrStart+">"+$("#targetAddEql").val()+"："+targetAddrStart+"</option>");
							$("#targetAddrStart").val("");
							$("#div_targetAddrStart").html("");
							$(this).dialog("close");
						}else{
							$("#div_targetAddrStart").html("添加数据有误，请检查！");
						}
					}
				}else{
						$("#targetAddr").empty();
						var targetAddrStart = $("#targetAddrStart").val();
						$("#targetAddr").append("<option value="+$("#targetAddEql").val()+":"+targetAddVal+">"+$("#targetAddEql").val()+"："+targetAddVal+"</option>");
						$("#targetAddrStart").val("");
						$("#div_targetAddrStart").html("");
						$(this).dialog("close");
				}
				
				
			},
			"取消[Esc]" : function() {
				$("#targetAddrStart").val("");
				$("#div_targetAddrStart").html("");
				$(this).dialog("close");
			}
		}
	});
	//源端口
	$('#dialog_sourcePort').dialog({
		autoOpen : false,
		width : 420,
		height : 270,

		buttons : {
			"确定[Enter]" : function() {

				// 获得前一状态的源地址 的这个select的 选择内容 如果不是输入内容按以前的流程走
				var sourcePortVal = $("#sourcePort1").val();
				//alert(sourcePortVal);
				if(sourcePortVal == "输入内容"){
					if(checkPort('sourcePortStart','div_sourcePortStart')){
						if(judgeRepeatedSelect('sourcePort','sourcePortStart')){
							var sourcePortStart = $("#sourcePortStart").val();
							$("#sourcePort").append("<option value="+$("#sourcePortEql").val()+":"+sourcePortStart+">"+$("#sourcePortEql").val()+"："+sourcePortStart+"</option>");
							$("#sourcePortStart").val("");
							$("#div_sourcePortStart").html("");
							$(this).dialog("close");
						}else{
							$("#div_sourcePortStart").html("添加数据有误，请检查！");
						}
					}
				}else{
						$("#sourcePort").empty();
						var sourcePortStart = $("#sourcePortStart").val();
						$("#sourcePort").append("<option value="+$("#sourcePortEql").val()+":"+sourcePortVal+">"+$("#sourcePortEql").val()+"："+sourcePortVal+"</option>");
						$("#sourcePortStart").val("");
						$("#div_sourcePortStart").html("");
						$(this).dialog("close");
				}
			},
			"取消[Esc]" : function() {
				$("#sourcePortStart").val("");
				$("#div_sourcePortStart").html("");
				$(this).dialog("close");
			}
		}
	});
	
	//目标端口
	$('#dialog_targetPort').dialog({
		autoOpen : false,
		width : 420,
		height : 270,
		buttons : {
			"确定[Enter]" : function() {
				// 获得前一状态的源地址 的这个select的 选择内容 如果不是输入内容按以前的流程走
				var targetPortVal = $("#targetPort1").val();
				//alert(targetPortVal);
				if(targetPortVal == "输入内容"){
					if(checkPort('targetPortStart','div_targetPortStart')){
						if(judgeRepeatedSelect('targetPort','targetPortStart')){
							var targetPortStart = $("#targetPortStart").val();
							$("#targetPort").append("<option value="+$("#targetPortEql").val()+":"+targetPortStart+">"+$("#targetPortEql").val()+"："+targetPortStart+"</option>");
							$("#targetPortStart").val("");
							$("#div_targetPortStart").html("");
							$(this).dialog("close");
						}else{
							$("#div_targetPortStart").html("添加数据有误，请检查！");
						}
					}
				}else{
						$("#targetPort").empty();
						var targetPortStart = $("#targetPortStart").val();
						$("#targetPort").append("<option value="+$("#targetPortEql").val()+":"+targetPortVal+">"+$("#targetPortEql").val()+"："+targetPortVal+"</option>");
						$("#targetPortStart").val("");
						$("#div_targetPortStart").html("");
						$(this).dialog("close");
				}
			},
			"取消[Esc]" : function() {
				$("#targetPortStart").val("");
				$("#div_targetPortStart").html("");
				$(this).dialog("close");
			}
		}
	});
	
	
	  //根据从后台传过来的等级信息在对应的地方打勾		  
		 //alert(${sizeThreadValue});
		 var priorityArr = [${priorityArr[0]},${priorityArr[1]},${priorityArr[2]},${priorityArr[3]},${priorityArr[4]}];
		 for(var i=0;i<5;i++){
			
			 $("#priority"+priorityArr[i]).attr("checked", true); 
		 }
			//初始化
	initCheckBox('assetSelect','chk-asset');
	initCheckBox('eventCategorySelect','chk-eventCategory');
	initCheckBox('eventTypeSelect','chk-eventType');
	
});
function addOk(typeSelect, checkBoxDiv,checkBoxName) {
	 // 确定按钮
	$("#"+typeSelect).empty();
	$("#"+checkBoxDiv+" input[type='checkbox'][name="+checkBoxName+"]:checked").each(
	   function() {
		 var node = $(this).parent().next().children("a");
	        $("#"+typeSelect).append("<option value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
	         }
	);
	$("#"+checkBoxDiv).dialog('close');
	}
//点击链接选择他左面的box
function checkLeftBox(id){
//	alert(id);
	if ($("#"+id).attr("checked") == false) {	
	$("#"+id).attr("checked",true);	
	}else{
	$("#"+id).attr("checked",false);	
		}
	};

//加载设备表方法
function showAssetDialog() {
	$("#dialog-asset").dialog("open");
}
//加载事件类别表方法
function showEventCategory() {
	//alert(1);
	$("#dialog-eventCategory").dialog("open");
}
//加载事件类型表方法
function showEventType() {
	//alert(1);
	$("#dialog-eventType").dialog("open");
}
//加载关联后事件类别表方法
function showEventCategoryRe() {
	//alert(1);
	$("#dialog-eventCategoryRe").dialog("open");
}
//加载关联后事件类型表方法
function showEventTypeRe() {
	//alert(1);
	$("#dialog-eventTypeRe").dialog("open");
}
//加载源ip的方法
function showSourceAddrDialog() {
	$("#dialog_sourceIp").dialog("open");
}
//加载目标ip的方法
function showTargetAddrDialog() {
	$("#dialog_targetIp").dialog("open");
}
//加载源端口的方法
function showSourcePortDialog() {
	$("#dialog_sourcePort").dialog("open");
}
//加载目标端口的方法
function showTargetPortDialog() {
	$("#dialog_targetPort").dialog("open");
}
//做设备和事件类型 关联后事件类型的前台分页
$(function(){
goPage(1,10,'dlg-table-asset','barcon');
goPage(1,10,'dlg-table-eventType','eventTypeBarcon');
goPage(1,10,'dlg-table-eventTypeRe','eventTypeBarconRe');
checkAll('eventsCategory-checkAll','chk-eventCategory');
checkAll('eventsType-checkAll','chk-eventsType');
checkAll('asset-checkAll','chk-asset');
initRadio1('eventCategorySelectRe','chk-eventCategoryRe');
initRadio1('eventTypeSelectRe','chk-eventsTypeRe');
});


//验证起始IP段合法性
//参数意义ipStart:输入ip框框的id;div_ipStart:提示ip不合法的框框的id
function checkIp(ipStart,div_ipStart) {
var ipStart = $("#"+ipStart).val();
if (ipStart != "") {
	var re = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
	var flag = re.test(ipStart);
	if (flag) {
		if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
			$("#"+div_ipStart).addClass("spanred");
			$("#"+div_ipStart).html("ip地址不合法");
			return false;
		} else {
			$("#"+div_ipStart).removeClass("spanred");
			$("#"+div_ipStart).html(
					"<img border=0 src=\"${ctx}/images/ok.png\" />");
			return true;
		}
	} else {
		$("#"+div_ipStart).addClass("spanred");
		$("#"+div_ipStart).html("ip地址不合法");
		return false;
	}
} else {
	$("#"+div_ipStart).addClass("spanred");
	$("#"+div_ipStart).html("ip地址不合法");
	return false;
}
}

//验证起始IP段合法性
//参数意义ipStart:输入ip框框的id;div_ipStart:提示ip不合法的框框的id
function checkPort(ipStart,div_ipStart) {
var ipStart = $("#"+ipStart).val();
if (ipStart != "") {
	 var re=/^(\d)+$/g;//匹配数字

	var flag = re.test(ipStart);
	 // alert(ipStart);
	if (flag&&parseInt(ipStart)>0&&parseInt(ipStart)<65536) {	
		
		$("#"+div_ipStart).removeClass("spanred");
		$("#"+div_ipStart).html(
				"<img border=0 src=\"${ctx}/images/ok.png\" />");
		return true;
		
	}else{
		$("#"+div_ipStart).addClass("spanred");
		$("#"+div_ipStart).html("端口不合法");
		return false;
	}
	
} else {
	$("#"+div_ipStart).addClass("spanred");
	$("#"+div_ipStart).html("端口不合法");
	return false;
}
}

	





//保存规则
function subData() {

	$('#assetIds').val('');
	$("#assetSelect").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#assetIds').val($('#assetIds').val() + $(this).val() + ",");
		}
	});



$('#eventsCategoryIds').val('');
$("#eventCategorySelect").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#eventsCategoryIds').val($('#eventsCategoryIds').val() + $(this).val() + ",");
	}
});
//关联后事件类别
$('#eventsCategoryIdsRe').val('');
$("#eventCategorySelectRe").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#eventsCategoryIdsRe').val($(this).val());
	}
});

$('#eventsTypeIds').val('');
$("#eventTypeSelect").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#eventsTypeIds').val($('#eventsTypeIds').val() + $(this).val() + ",");
	}
});
//关联后事件类型
$('#eventsTypeIdsRe').val('');
$("#eventTypeSelectRe").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#eventsTypeIdsRe').val($(this).val());
	}
});
$('#sourceAddrs').val('');
$("#sourceAddr").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#sourceAddrs').val($('#sourceAddrs').val() + $(this).val() + ",");
	}
});

$('#targetAddrs').val('');
$("#targetAddr").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#targetAddrs').val($('#targetAddrs').val() + $(this).val() + ",");
	}
});

$('#sourcePorts').val('');
$("#sourcePort").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#sourcePorts').val($('#sourcePorts').val() + $(this).val() + ",");
	}
});

$('#targetPorts').val('');
$("#targetPort").children("option").each(function() {
	if ($(this).parent("select").size() > 0) {
		$('#targetPorts').val($('#targetPorts').val() + $(this).val() + ",");
	}
});
//做提交之前的判断
var time = $('#time').val();//时间
var count = $('#count').val();//次数

var eventsName = $('#eventsName').val()+"";//关联后事件名字
var eventsCategoryIdsRe = $('#eventsCategoryIdsRe').val();//关联后事件类别
var eventsTypeIdsRe = $('#eventsTypeIdsRe').val();//关联后事件类型

if(time==''|time==0||count==''||count==0||eventsName==''||eventsCategoryIdsRe==''||eventsTypeIdsRe==''){
	alert("请填写必要属性！");
}else{
	//alert("tijiao");
	$("#from").submit();
}


}

//全选的封装方法  参数意义 checkAllId 全选box的id  checkName 下面小box的name

function checkAll(checkAllId,checkName){
//alert(checkName);
$("#"+checkAllId).live("click", function(event) {
	if ($("#"+checkAllId).hasClass('not-checked-assetType')) {
		$("#"+checkAllId).removeClass('not-checked-assetType');
		$("input[name="+checkName+"]").attr('checked', false);
	} else {
		$("#"+checkAllId).addClass('not-checked-assetType');
		$("input[name="+checkName+"]").attr('checked', true);
	}
});

}
//ipInput 输入ip的input ipSpan  后面的提示 ipSelect select中的数据
function checkRepeatIps(ipInput,ipSpan,ipSelect){

}
//判断select中的东西跟要加入的是否有重复的
function judgeRepeatedSelect(selectId,tex){
	var flg = true;
	var rex =/[前一]{1}/;
	//加入指定ip的时候 判断里面是是否有 前一状态的格式  有就清空它
	$("#"+selectId+" option").each(function() { 
		var a = $(this).text();
        if (rex.test(a)) { 
        	$("#"+selectId).empty();
    		return false;
        }
        }); 
	$("#"+selectId+" option").each(function() { 

		flg = true;
        if ($(this).text().split("：")[1] == $("#"+tex).val()) { 
        	flg = false;
    		return false;
        }
        
        });      
	return flg;
}

function ValidateNumber(e, pnumber){
if (!/^\d+$/.test(pnumber)){

$(e).val(/^\d+/.exec($(e).val()));

}

return false;

}

	//tab页用的js开始
		jQuery(document)
			.ready(
					function() {
					$('#tabs-setting').tabs();
		
					});

	
    function removeDiv(){
    	$("div [class='formErrorContent']").parent().toggle("slow") ; 
    	
    }
    function showDiv(){
    	$("div [class='formErrorContent']").parent().toggle("slow") ; 
    }
  //tab页用的js结束
  //目标ip下拉框事件
function sourceAddfunc(){	
	var val = $("#sourceAdd").val();
	if(val =='输入内容'){
		showOrHidden("#sourceAddrStart",1);
		showOrHidden("#div_sourceAddrStart",1);
	}else{
		$("#sourceAddrStart").val("");
		showOrHidden("#div_sourceAddrStart",0);
		showOrHidden("#sourceAddrStart",0);
	}	
}
//目标ip下拉框事件
function targetAddfunc(){	
	var val = $("#targetAdd").val();
	if(val =='输入内容'){
		showOrHidden("#targetAddrStart",1);
		showOrHidden("#div_targetAddrStart",1);
	}else{
		$("#targetAddrStart").val("");
		showOrHidden("#div_targetAddrStart",0);
		showOrHidden("#targetAddrStart",0);
	}	
}
function targetPortfunc(){	
	var val = $("#targetPort1").val();
	if(val =='输入内容'){
		showOrHidden("#targetPortStart",1);
		showOrHidden("#div_targetPortStart",1);
	}else{
		$("#targetPortStart").val("");
		showOrHidden("#div_targetPortStart",0);
		showOrHidden("#targetPortStart",0);
	}	
}
function sourcePortfunc(){	
	var val = $("#sourcePort1").val();
	if(val =='输入内容'){
		showOrHidden("#sourcePortStart",1);
		showOrHidden("#div_sourcePortStart",1);
	}else{
		$("#sourcePortStart").val("");
		showOrHidden("#div_sourcePortStart",0);
		showOrHidden("#sourcePortStart",0);
	}	
}
//处理input和-show或者hidden的方法 1 = show else =hudden
function showOrHidden(id,status){
	if(status==1){
		$(id).show();
	}else{
		$(id).hide();
	}
}
</script>
</head>

<body style="margin-top:2px;margin-left: 2px; font-size:12px;" >
<s:form action="insertOrUpdateRelevanceRule" namespace="/relevanceRules"
		method="post" theme="simple" id="from">
		<s:hidden name="relevanceRule.initState"  value = "0"/>
	<div style="width: 99%; border:1px solid #dcdcdc;">
		<!--标题 -->
		<!-- <div id="safetytitle">
			<font color="#000000"
				style="line-height:50px; margin-left:30px;font-size:20px"><strong>资产添加</strong>
			</font>
		</div> -->

		<!--tab 页切换-->
		<div id="tabs-setting"
			style="width: 98%;border: none; margin: 2px auto">
			<ul style="background: #EDEEF3">

				<li><a href="#eventstatistics" style="cursor: pointer;"
					onclick="showDiv();">规则属性</a>
				</li>
				<li><a href="#propertyspread" style="cussor: pointer"
					onclick="removeDiv();">关联后事件属性</a>
				</li>

			</ul>

			

			<!-- 第一个tab页：事件统计 -->
			<div id="eventstatistics" >
		<div class="framDiv"  style="margin-left:-18px">
	<input type="text"
	style="display:none;" name="relevanceRule.relevanceRuleId"
			value="${relevanceRule.relevanceRuleId }" />	
			<input type="text"
	style="display:none;" name="relevanceRule.groupId"
			value="${groupId}" />
			
			
													
		<!-- 公司table-->
		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<!-- 用户信息 -->
				<tr>
					<td class='r2titler' colspan='2' ><b>关联规则</b></td>
				</tr>
				<tr>
					<td width=52%>

						<table width="99%" border="0" align="top" cellspacing="0"
							cellpadding="1">							
						<!-- 空行-->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<!-- 创建者 -->
							<tr>
								<td align="right" style="padding-left:10px;font-size:12px;">创建者：</td>
								<td align="left" class="padding"><input
									value="${relevanceRule.relevanceRuleCreater }"
									name="relevanceRule.relevanceRuleCreater"
									value="" type="text"  disabled ="disabled"
									size="40" id="devicename" maxlength="255" style="width: 253px" />
								</td>

								</td>
							</tr>
							<!-- 空行-->
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<!--来源 -->
							<tr >
								<td align="right" style="padding-left:10px;font-size:12px;">创建时间：</td>
								<td align="left" class="padding"><input
									name="relevanceRule.relevanceRuleCreateDate" id="assetGateWays"
									value='<s:date
										name="relevanceRule.relevanceRuleCreateDate" format="yyyy-MM-dd HH:mm:ss" />' disabled ="disabled"
									type="text"
									
									size="40" id="assetGateWay" maxlength="255"
									style="width: 253px" 
									readonly="readonly" />
								</td>

								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr >
								<td align="right" style="padding-left:10px;font-size:12px;">修改者：</td>
								<td align="left" class="padding">
								
								<input
									disabled ="disabled"
									name="relevanceRule.relevanceRuleMender"
									value="${relevanceRule.relevanceRuleMender}" type="text"
									size="40" id="devicename" maxlength="255" style="width: 253px" /> 
									
								</td>

								</td>
							</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>


							<tr >
								<td align="right" style="padding-left:10px;font-size:12px;">修改时间：</td>
								<td align="left" class="padding">
								
								
								<input
									name="" id="assetGateWays"
									value='<s:date
										name="relevanceRule.relevanceRuleMenderDate" format="yyyy-MM-dd HH:mm:ss" />'
									type="text"
									disabled ="disabled"
									size="40" id="assetGateWay" maxlength="255"
									style="width: 253px" 
									>
								</td>

								</td>
							</tr>
						
	
				<tr>
								<td class="td_detail_separator"></td>
							</tr>
<tr>
		<td class="row" align= "right" width = "20%"><span>事件级别：</span></td>
		<td align= "left">
		&nbsp;<input type="checkbox" name="priorityArr" value="1"  id="priority1"/>&nbsp;1&nbsp;
		<input type="checkbox" name="priorityArr" value="2" id="priority2"/>&nbsp;2&nbsp;
			<input type="checkbox" name="priorityArr" value="3" id="priority3"/>&nbsp;3&nbsp;
			<input type="checkbox" name="priorityArr" value="4" id="priority4"/>&nbsp;4&nbsp;
		<input type="checkbox" name="priorityArr" value="5" id="priority5"/>&nbsp;5&nbsp;
									
								</td>
							</tr>
				<tr>
								<td class="td_detail_separator"></td>
							</tr>
													<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;"><span
									class="spanred">*&nbsp;</span>时间：</td>
								<td align="left" class="padding"><input id = "time"
									class="validate[required,min[1],custom[onlyNumberSp],custom[validateSpase]] text-input"
									name="relevanceRule.time"
									value="${relevanceRule.time}" type="text"
									size="40" id="devicename" maxlength="9" style="width: 253px" />&nbsp;秒
								</td>

							</tr>				<tr>
								<td class="td_detail_separator"></td>
							</tr>
					<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;"><span
									class="spanred">*&nbsp;</span>次数：</td>
								<td align="left" class="padding"><input id = "count"
									class="validate[required,min[1],custom[onlyNumberSp],custom[validateSpase]] text-input"
									name="relevanceRule.count"
									value="${relevanceRule.count}" type="text"
									size="40" id="devicename" maxlength="9" style="width: 253px" />&nbsp;次
								</td>

							</tr>				<tr>
								<td class="td_detail_separator"></td>
							</tr>
					<tr valign="top">		
					<td width="15%" align="right">源地址：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="sourceAddr" name = "sourceAddr" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="srouceAddrSel" status="stat" id = "srouceAddrOpt">
																		<option value="${srouceAddrOpt}" id="deviceTypeid">${srouceAddrOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.sourceAddr" id="sourceAddrs" />
													</tr>
													<tr>
													
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showSourceAddrDialog();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('sourceAddr','');" />
														</td>
													</tr>
												</table></td>
										</tr>
										<tr>
		<td class="td_detail_separator"></td>
	</tr>
				
							
							
							
							
						<%--<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;">源IP：</td>
								<td align="left" class="padding"><input
									
									name="relevanceRule.sourceAddr" class="validate[custom[ipv4]] text-input"
									value="${relevanceRule.sourceAddr}" type="text"
									size="40" id="sourceAddr" maxlength="255" style="width: 253px" />
									<span id="div_sourceAddr"></span>
								</td>

							</tr>
				--%><%--<tr>
								<td class="td_detail_separator"></td>
							</tr>
														<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;">目标IP：</td>
								<td align="left" class="padding"><input
									class="validate[custom[ipv4]] text-input"
									   name="relevanceRule.targetAddr"
									value="${relevanceRule.targetAddr}" type="text"
									size="40" id="targetAddr" maxlength="255" style="width: 253px" />
									<span id="div_targetAddr"></span>
								</td>
								

							</tr>
				--%><%--<tr>
								<td class="td_detail_separator"></td>
							</tr>
						<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;">源端口：</td>
								<td align="left" class="padding"><input
								
									class="validate[custom[settingPort]] text-input"
									
									name="relevanceRule.sourcePort"
									id = "sourcePort"
									value="${relevanceRule.sourcePort }" type="text"
									size="40" id="devicename" maxlength="255" style="width: 253px" />
									<span id="div_sourcePort"></span>
								</td>

							</tr>
				--%><%--<tr>
								<td class="td_detail_separator"></td>
							</tr>
														<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;">目标端口：</td>
								<td align="left" class="padding"><input
											class="validate[custom[settingPort]] text-input"
									name="relevanceRule.targetPort" id = "targetPort"
									value="${relevanceRule.targetPort }" type="text"
									size="40" id="devicename" maxlength="255" style="width: 253px" />
									<span id="div_targetPort"></span>
								</td>

							</tr>
				--%>
								<tr valign="top">		<td width="15%" align="right">目标地址：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="targetAddr" name = "targetAddr" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="targetAddrSel" status="stat" id = "targetAddrOpt">
																		<option value="${targetAddrOpt}" id="deviceTypeid">${targetAddrOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.targetAddr" id="targetAddrs" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showTargetAddrDialog();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('targetAddr','');" />
														</td>
													</tr>
												</table></td>
										</tr>
										<tr>
		<td class="td_detail_separator"></td>
	</tr>
<tr valign="top">		<td width="15%" align="right">源端口：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="sourcePort" name = "sourcePort" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="sourcePortSel" status="stat" id = "sourcePortOpt">
																		<option value="${sourcePortOpt}" id="deviceTypeid">${sourcePortOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.sourcePort" id="sourcePorts" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showSourcePortDialog();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('sourcePort','');" />
														</td>
													</tr>
												</table></td>
										</tr>
													<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table>
					</td>




					<td>

						<table width="99%" border="0" align="center" cellspacing="0"
							cellpadding="1">
								<tr>
		<td class="td_detail_separator"></td>
	</tr>
	
<tr valign="top">		<td width="15%" align="right">目标端口：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="targetPort" name = "targetPort" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="targetPortSel" status="stat" id = "targetPPortOpt">
																		<option value="${targetPPortOpt}" id="deviceTypeid">${targetPPortOpt}</option>
																				
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.targetPort" id="targetPorts" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showTargetPortDialog();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('targetPort','');" />
														</td>
													</tr>
												</table></td>
										</tr>	
										<tr>
		<td class="td_detail_separator"></td>
	</tr>
	
					<tr valign="top">		<td width="15%" align="right">关联设备：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="assetSelect" name = "assetSelect" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="assetListSel" status="stat">
																		<option value="${DEVICE_CATEGORY_ID}" id="deviceTypeid">${DEVICE_CATEGORY_NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.assetIds" id="assetIds" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showAssetDialog();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('assetSelect','chk-asset');" />
														</td>
													</tr>
												</table></td>
										</tr>
<tr>
		<td class="td_detail_separator"></td>
	</tr>
										<tr valign="top">		<td width="15%" align="right">事件类别：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="eventCategorySelect" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="eventCategoryListSel" status="stat">
																		<option value="${ID}" id="deviceTypeid">${NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.eventsCategoryIds" id="eventsCategoryIds" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showEventCategory();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('eventCategorySelect','chk-eventCategory');" />
														</td>
													</tr>
												</table></td>
										</tr>
	<tr>
		<td class="td_detail_separator"></td>
	</tr>
											<tr valign="top">		<td width="15%" align="right">事件类型：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="eventTypeSelect" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="eventTypeTagListSel" status="stat">
																		<option value="${ID}" id="deviceTypeid">${NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.eventsTypeIds" id="eventsTypeIds" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showEventType();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('eventTypeSelect','chk-eventType');" />
														</td>
													</tr>
												</table></td>
										</tr>

							</td>
							</tr>


										</tr>
					<tr>
		<td class="td_detail_separator"></td>
	</tr>
										
				<tr valign="top">	
					<td width="15%" align="right">备注：</td>

											<td width="60%" align="left" style="padding-left:8px">
											
												<textarea  style="width:255px;height:80px;" name = "relevanceRule.relevanceRuleDes">${relevanceRule.relevanceRuleDes }</textarea>
											
											</td>
										</tr>		
										

															<!-- 空行 -->
<tr>
		<td class="td_detail_separator"></td>
	</tr>			
							<!-- 空行 -->
<tr>
		<td class="td_detail_separator"></td>
	</tr>
						</table>
					</td>


























				</tr>
			</table>



		
	

	<%--


	<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px;">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>

				<tr>
					<td align="right"><input type="button" class="btnyh"
						id="btnCancel" value="保   存" onclick="subData();" /> <input type="button" class="btnyh"
						id="btnCancel" value="取  消" onclick="window.history.back();" />
					</td>
				</tr>
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
			</table>









	--%><%--</s:form>


	--%><!-- ext query from -->

	<div id="dialog-asset" title="关联设备">
	<s:hidden id="dlg-keyword-resource-id" value=""/>
		<table id="dlg-table-asset" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="overflow:auto">		

             <tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="asset-checkAll" name="chkAll-user"
					class="check-box-devicename not-checked-devicename" />
				</td>
				<td  width = "400px" align="center" class="biaoti">关联设备</td>
			</tr>
         
         	<s:iterator value='assetList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="6%" align="center"><input type="checkbox"
						name="chk-asset" id="chk-${deviceid}"
						value="${deviceid}" /></td>
					<td width = "400px" ><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-${deviceid}')">${devicename}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>
<table width="80%" align="right">
				<tr>
		<td>
			<div id="barcon" name="barcon"></div>
	</td>
</tr>
</table>

	</div>


	<div id="dialog-eventCategory" title="事件类别">
	<s:hidden id="dlg-keyword-resource-id" value=""/>
		<table id="dlg-table-eventsCategory" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="overflow:auto">		
         <tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="eventsCategory-checkAll" name="chkAll-user"
					class="check-box-devicename not-checked-devicename" />
				</td>
				<td width = "400px" align="center" class="biaoti">事件类别</td>
			</tr>
         	<s:iterator value='eventCategoryList' status='stat' >
         	   
				<tr style="line-height: 25px;">
					<td width="6%" align="center"><input type="checkbox"
						name="chk-eventCategory" id="chk-eventCategory-${eventcategorykey}"
						value="${eventcategorykey}" /></td>
					<td width = "400px" ><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-eventCategory-${eventcategorykey}')">${eventcategoryValue}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>


	</div><%-- 弹出框
关联后事件类别
--%>
	<div id="dialog-eventCategoryRe" title="事件类别">
	<s:hidden id="dlg-keyword-resource-id" value=""/>
		<table id="dlg-table-eventsCategory" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="overflow:auto">		
         <tr height="28">
				<td width="6%" align="center" class="biaoti">
				</td>
				<td width = "400px" align="center" class="biaoti">事件类别</td>
			</tr>
         	<s:iterator value='eventCategoryListRe' status='stat' >
         	   
				<tr style="line-height: 25px;">
					<td width="6%" align="center"><input type="radio"
						name="chk-eventCategoryRe" id="chk-eventCategoryRe-${eventcategorykey}"
						value="${eventcategorykey}" /></td>
					<td width = "400px" ><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-eventCategoryRe-${eventcategorykey}')">${eventcategoryValue}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>


	</div>





<div id="dialog-eventType" title="事件类型">
	
		<table id="dlg-table-eventType" width='96%' cellspacing='1'
			cellpadding='0' border='0'  class="tab2" style="overflow:auto">		

            <tr height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="eventsType-checkAll" name="chkAll-user"
					class="check-box-devicename not-checked-devicename" />
				</td>
				<td width = "400px" align="center" class="biaoti">事件类型</td>
			</tr>
         	<s:iterator value='eventTypeTagList' status='stat'>
				<tr style="line-height: 25px;">
					<td width="6%" align="center"><input type="checkbox"
							name="chk-eventsType" id="chk-eventType-${eventtypetagId}"
						value="${eventtypetagId}" /></td>
					<td width = "400px"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-eventType-${eventtypetagId}')">${eventtypetagName}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>
<table width="80%" >
	<tr>
		<td colspan="2" style ="text-align:right;">
			<div id="eventTypeBarcon" name="eventTypeBarcon"></div>
	</td>
</tr>
</table>

	</div><%--
	
	关联后事件类别的弹出框
	
	
	--%>
	<div id="dialog-eventTypeRe" title="事件类型">
	
		<table id="dlg-table-eventTypeRe" width='96%' cellspacing='1'
			cellpadding='0' border='0'  class="tab2" style="overflow:auto">		

            <tr height="28">
				<td width="6%" align="center" class="biaoti">
				</td>
				<td width = "400px" align="center" class="biaoti">事件类型</td>
			</tr>
         	<s:iterator value='eventTypeTagListRe' status='stat'>
				<tr style="line-height: 25px;">
					<td width="6%" align="center"><input type="radio"
							name="chk-eventsTypeRe" id="chk-eventTypeRe-${eventtypetagId}"
						value="${eventtypetagId}" /></td>
					<td width = "400px"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-eventTypeRe-${eventtypetagId}')">${eventtypetagName}</a>
					</td>
				</tr>
			</s:iterator>
         
         
		</table>
<table width="80%" >
	<tr>
		<td colspan="2" style ="text-align:right;">
			<div id="eventTypeBarconRe" name="eventTypeBarconRe"></div>
	</td>
</tr>
</table>

	</div>
	
	<%--
	
	源地址弹出框
--%><div id="dialog_sourceIp" title="源地址">
	
	<table  cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
			<tr valign="top">
				<td class="row" align= "top" width = "20%"><span>源地址：</span></td>
				<td>
				<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
													<td>
														<select id = "sourceAddEql" >
														<option>等于</option>
														<option>不等于</option>
														</select>
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr><td>
													<select onchange="sourceAddfunc();" id = "sourceAdd">
														<option>前一状态的源地址 </option>
														<option>前一状态的目的地址 </option>
														<option>输入内容</option>
														</select>
														
														</td>
													</tr>
														<tr>
														<td height="2px"></td>
													</tr>	
													<tr  >
													<td><input style = "display:none;"  type="text" id="sourceAddrStart" name="sourceAddrStart" size="20"
													width="100px" />
																</td>
																
													</tr>
													<tr>
													
											<td width="30%"><span style = "color: red;" id="div_sourceAddrStart"></span></td>
													</tr>		
													
													
												</table>
				</td>
				</tr>
		</table>

	</div>
	
	<%--
	
	目标地址弹出框
--%><div id="dialog_targetIp" title="目标地址">
	
	<table  cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
			<tr valign="top">
				<td class="row" align= "top" width = "20%"><span>目标地址：</span></td>
				<td>
				<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
													<td>
														<select id = "targetAddEql" >
														<option>等于</option>
														<option>不等于</option>
														</select>
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr><td>
													<select onchange="targetAddfunc();" id = "targetAdd">
														<option>前一状态的源地址 </option>
														<option>前一状态的目的地址 </option>
														<option>输入内容</option>
														</select>
														
														</td>
													</tr>
														<tr>
														<td height="2px"></td>
													</tr>	
													<tr  >
													<td><input style = "display:none;"  type="text" id="targetAddrStart" name="targetAddrStart" size="20"
													width="100px" />
																</td>
																
													</tr>
													<tr>
													
											<td width="30%"><span style = "color: red;" id="div_targetAddrStart"></span></td>
													</tr>		
													
													
												</table>
				</td>
				</tr>
		</table>

	</div>
	<%--
	
	源地址弹出框
--%><div id="dialog_sourcePort" title="源端口">
	
	<table  cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
			<tr valign="top">
				<td class="row" align= "top" width = "20%"><span>源端口：</span></td>
				<td>
				<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
													<td>
														<select id = "sourcePortEql" >
														<option>等于</option>
														<option>不等于</option>
														</select>
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr><td>
													<select onchange="sourcePortfunc();" id = "sourcePort1">
														<option>前一状态的源端口 </option>
														<option>前一状态的目的端口 </option>
														<option>输入内容</option>
														</select>
														
														</td>
													</tr>
														<tr>
														<td height="2px"></td>
													</tr>	
													<tr  >
													<td><input style = "display:none;"  type="text" id="sourcePortStart" name="sourcePortStart" size="20"
													width="100px" />
																</td>
																
													</tr>
													<tr>
													
											<td width="30%"><span style = "color: red;" id="div_sourcePortStart"></span></td>
													</tr>		
													
													
												</table>
				</td>
				</tr>
		</table>

	</div>
	
	<div id="dialog_targetPort" title="目标端口">
	
	<table  cellspacing='0' cellpadding='0' border='0'
			align='center'>
			<tr>
				<td class="td_detail_separator"></td>
			</tr>	
			<tr valign="top">
				<td class="row" align= "top" width = "20%"><span>目标端口：</span></td>
				<td>
				<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
													<td>
														<select id = "targetPortEql" >
														<option>等于</option>
														<option>不等于</option>
														</select>
														</td>
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr><td>
													<select onchange="targetPortfunc();" id = "targetPort1">
														<option>前一状态的源端口 </option>
														<option>前一状态的目的端口 </option>
														<option>输入内容</option>
														</select>
														
														</td>
													</tr>
														<tr>
														<td height="2px"></td>
													</tr>	
													<tr  >
													<td><input style = "display:none;"  type="text" id="targetPortStart" name="targetPortStart" size="20"
													width="100px" />
																</td>
																
													</tr>
													<tr>
													
											<td width="30%"><span style = "color: red;" id="div_targetPortStart"></span></td>
													</tr>		
													
													
												</table>
				</td>
				</tr>
		</table>

	</div>
<!--tab 页切换-->
	
</div>
			</div>

			<!-- 第二个tab页：用户自定义属性 -->
			<div id="propertyspread">
						<!-- 第一个tab页：事件统计 -->
			
		<div class="framDiv"  style="margin-left:-18px">
	
													
		<!-- 公司table-->
		
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<!-- 用户信息 -->
				<tr>
					<td class='r2titler' colspan='2' ><b>关联后事件属性</b></td>
				</tr>
				<tr>
					<td width=52%>

						<table width="99%" border="0" align="top" cellspacing="0"
							cellpadding="1">							
						<!-- 空行-->
						
						

						
		
														
				<tr>
								<td class="td_detail_separator"></td>
							</tr>

							<tr>
								<td align="right" width="30%"
									style="padding-left:10px;font-size:12px;"><span
									class="spanred">*&nbsp;</span>事件名称：</td>
								<td align="left" class="padding"><input id = "eventsName"
									class="validate[required,custom[spechars],custom[validateLength]]"
									name="relevanceRule.eventsName"
									value="${relevanceRule.eventsName }" type="text"
									size="40" id="devicename" maxlength="100" style="width: 253px" />
								</td>

							</tr>
				<tr>
								<td class="td_detail_separator"></td>
							</tr>



	<tr valign="top">		<td width="15%" align="right"><span
									class="spanred">*&nbsp;</span>事件类别：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="eventCategorySelectRe" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="eventCategoryListSelRe" status="stat">
																		<option value="${ID}" id="deviceTypeid">${NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.eventsCategoryIdsRe" id="eventsCategoryIdsRe" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showEventCategoryRe();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('eventCategorySelectRe','chk-eventCategoryRe');" />
														</td>
													</tr>
												</table></td>
										</tr>


						<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table>
					</td>




					<td>

						<table width="99%" border="0" align="center" cellspacing="0"
							cellpadding="1">

	

									
					<tr>
								<td align="right" style="padding-left:10px;font-size:12px;"><span
									class="spanred">*&nbsp;</span>事件级别：</td>
								<td align="left" class="padding" >
									<input type="radio" class="check-box-sys"
								<c:if test="${relevanceRule.level=='1'}">checked="checked"</c:if>
														checked="checked"name="relevanceRule.level" value="1" /> 1&nbsp;
														<input type="radio" class="check-box-sys" 
															<c:if test="${relevanceRule.level=='2'}">checked="checked"</c:if>
															name="relevanceRule.level" value="2" /> 2&nbsp;
														<input type="radio" class="check-box-sys"
															<c:if test="${relevanceRule.level=='3'}">checked="checked"</c:if>
															name="relevanceRule.level" value="3" /> 3&nbsp;
														<input type="radio" class="check-box-sys"
															<c:if test="${relevanceRule.level=='4'}">checked="checked"</c:if>
															name="relevanceRule.level" value="4" /> 4&nbsp;
														<input type="radio" class="check-box-sys"
															<c:if test="${relevanceRule.level=='5'}">checked="checked"</c:if>
															name="relevanceRule.level" value="5" /> 5&nbsp;
					
								</td></tr>
								<tr>
		<td class="td_detail_separator"></td>
	</tr>
			
			
											<tr valign="top">		<td width="15%" align="right"><span
									class="spanred">*&nbsp;</span>事件类型：</td>

											<td width="60%" align="left" style="padding-left:8px">
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td><select 
															id="eventTypeSelectRe" style="width:253px" size="5"
															multiple="multiple">
																<s:iterator value="eventTypeTagListSelRe" status="stat">
																		<option value="${ID}" id="deviceTypeid">${NAME}</option>
																</s:iterator>
														</select></td><s:hidden name="relevanceRule.eventsTypeIdsRe" id="eventsTypeIdsRe" />
													</tr>
													<tr>
														<td height="2px"></td>
													</tr>
													<tr>
														<td><input type="button" value="" class="btnadd"
															onclick="showEventTypeRe();" />
															<input type="button" value="" class="btndel"
															onclick="delOption('eventTypeSelectRe','chk-eventTypeRe');" />
														</td>
													</tr>
												</table></td>
										</tr>

							</td>
							</tr>


										</tr>
					<tr>
		

						</table>
					</td>



				</tr>
			</table>


	
<!--tab 页切换-->
	
</div>
		
				
			</div>
		</div>

	</div>
		
	<div id="hideDiv"></div>
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 4px;">
				<tr>
					<td class="td_detail_separator"></td>
				</tr>

				<tr>
					<td align="right"><input type="button" class="btnyh"
						id="btnCancel" value="保   存" onclick="subData();" /> <input type="button" class="btnyh"
						id="btnCancel" value="取  消" onclick="window.history.back();" />
					</td>
				</tr>
				<tr>
					<td class="td_detail_separator"></td>
				</tr>
			</table>
	</s:form>
</body>
</html>
