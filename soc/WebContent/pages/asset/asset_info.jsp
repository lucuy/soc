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
<!-- jsDate 格式化时间的函数 

<script type='text/javascript' src="${ctx}/scripts/jsDate.js"></script>-->
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
<%-- <script type="text/javascript" src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>--%>
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
	var time = 2500;
	var i = "tab1";
	var interval;
	var fillStyle1 = {
		type : 'linearGradient',
		x0 : 0,
		y0 : 0,
		x1 : 0,
		y1 : 1,
		colorStops : [ {
			offset : 0,
			color : '#C1E4FE'
		}, {
			offset : 1,
			color : '#27A1FD'
		} ]
	};
	var background = {
		type : 'linearGradient',
		x0 : 0,
		y0 : 0,
		x1 : 0,
		y1 : 1,
		colorStops : [ {
			offset : 0,
			color : 'white'
		}, {
			offset : 1,
			color : '#d2e6c9'
		} ]
	};
	
    var data = [];
    var i;
    for (i = 0; i < 10; i++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data.push(["", temp]);
    }
    
    
    
    var data0 = [];
    var i;
    for (i = 0; i < 10; i++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data0.push(["", temp]);
    }    
    var data1= [];
    var j;
     for (j = 0; j < 10; j++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data1.push(["", temp]);
    }    
    var data2= [];
    var k;
     for (k = 0; k < 10; k++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data2.push(["", temp]);
    }
     var data3= [];
    var l;
     for (l = 0; l < 10; l++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data3.push(["", temp]);
    }    
    var data4=[];
      var m;
     for (m = 0; m < 10; m++) {
        yValue = 1 + Math.random();
        var temp = String(yValue); 
        temp.substring(0,4);
        data4.push(["", temp]);
    }            

    

	function removeAllSpace(str) { 
		return str.replace(/\s+/g, "");
			
	}

    function inittendencyjqChart()
	{
    	  $('#chart_0').jqChart({
              background : background,
              legend : {
                  visible : false
              },
              border : {
                  strokeStyle : '#FCFCFA'
              },
              axes:[
                {
                    type:'linear',
                    minimum:0,
                    maximum:3,
                    interval:0.2
                }
              ],
              series : [ {
                  type : 'line',
                  font : '22 sans-serif',
                  data : data,
                  fillStyle : fillStyle1,
                  strokeStyle : '#3D69C8'
              } ]
          });
          updateChart();
	}
    
    function updateChart() {            

        yValue = 1+ Math.random();
         var temp = String(yValue); 
        
        temp.substring(0,4);
        data.splice(0, 1);
        data.push(["", temp]);
         
        $('#chart_0').jqChart('update');

        setTimeout("updateChart()", 10000);
    }


      function updateChart2() {     
    
         yValue = 1+ Math.random();
         var temp = String(yValue); 
         var temp1 = 1+Math.random();
         var temp2 = 1+Math.random();
         var temp3 = 1+Math.random();
         var temp4 = 1+Math.random();
         temp.substring(0,4);
         data0.splice(0,1);
         data1.splice(0,1);
         data2.splice(0,1);
         data3.splice(0,1);
         data4.splice(0,1);
        // add a new element
        data0.push(["", temp]);
        data1.push(["",temp1]);
        data2.push(["",temp2]);
        data3.push(["",temp3]);
        data4.push(["",temp4]);
        
        $('#chart_2').jqChart('update');

        setTimeout("updateChart2()", 10000);
    }

	function initeventChart(data) {
		$('#chart_1').jqChart({
			background : background,
			legend : {
				visible : false
			},
			border : {
				strokeStyle : 'white'
			},
			axes : [ {
				location : 'left',
				type : 'category'

			} ],
			series : [ {
				type : 'bar',
				font : '22 sans-serif',
				data : data,
				fillStyle : fillStyle1,
				strokeStyle : 'white',
				labels : {
					font : '11px sans-serif',
					fillStyle : 'red',
					valueType : 'dateValue'
				}
			} ]
		});
	}


	function initbarChart3(db) {
		$('#chart_3').jqChart(
				{
					background : background,
					legend : {
						visible : true,
						location : 'right'
					},
					border : {
						strokeStyle : 'white'
					},
					series : [ {
						type : 'pie',
						font : '22 sans-serif',
						data : db,
						fillStyle : fillStyle1,
						labels : {
							font : '11px sans-serif',
							valueType : 'dateValue'
						}
					} ]
				});

	}
	function initcolumnChart4(db) {
		$('#chart_4').jqChart({
			background : background,
			legend : {
				visible : false
			},
			border : {
				strokeStyle : 'white'
			},
			series : [ {
				type : 'column',
				font : '22 sans-serif',
				data : db,
				fillStyle : fillStyle1,
				strokeStyle : 'white',
				labels : {
					font : '11px sans-serif',
					fillStyle : 'red',
					valueType : 'dateValue'
				}
			} ]
		});

	}

	function show(obj) {
		$(obj).removeClass("back");
		$(obj).addClass("hand");
	}

	function hide(obj) {
		$(obj).removeClass("hand");
		$(obj).addClass("back");
	}

	function action(id) {
		changeIcon($("#img_" + id));
		$("#column_" + id + " >ul").toggle("slow");

	}

	function changeIcon(nainNode) {
		if (nainNode) {
			if (nainNode.attr("src").indexOf("u321_normal.png") >= 0) {
				nainNode.attr("src", "${ctx}/images/arrow_03.gif");
			} else {
				nainNode.attr("src", "${ctx}/images/u321_normal.png");
			}
		}
	}

	
		jQuery(document)
			.ready(
					function() {
						jQuery("#assetForm").validationEngine();
						$("#assetName").focus();
					$('#tabs-setting').tabs();
						

						//资产组dialog
						$('#dialog-assetGroupDialog').dialog({
							autoOpen : false,
							width : 400,
							height : 450,
							buttons : {
								"确定[Enter]" : function() {

								//	addAssetGroup();
									addAssets('assetsSelect','dialog-addAssets');
									$(this).dialog("close");
								},

								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
						//初始化资产组
						initRadio('assetGroupId', 'assetGroupId');
                    
						//监控目录
						$('#dialog-addAccount').dialog({
							autoOpen : false,
							width : 400,
							height : 150,
							buttons : {
								"确定[Enter]" : function() {
									directoriseAddAccount();
								},
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
						
						//监控文件
						$('#dialog-fireAccount').dialog({
							autoOpen : false,
							width : 400,
							height : 150,
							buttons : {
								"确定[Enter]" : function() {
									fireAddAccount();
								},
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
						//特权命令
						$('#dialog-assetPrivilegeCommandAccount').dialog({
							autoOpen : false,
							width : 400,
							height : 150,
							buttons : {
								"确定[Enter]" : function() {
									assetPrivilegeCommandAddAccount();
								},
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});

						//屏蔽页面加载时会加载checkAssetName（监测资产名的方法）
						/**
						if (/msie/i.test(navigator.userAgent)) //ie浏览器 
						{
							document.getElementById('assetName').onpropertychange = checkAssetName;
						} else {//非ie浏览器，比如Firefox 
							document.getElementById('assetName')
									.addEventListener("input", checkAssetName,
											false);
						}
						*/

						document.getElementById("hiddenNetOutLoginName").style.display = "none";
						document.getElementById("hiddensLoginName").style.display = "none";
						if ('${asset.assetUnName}' == '0') {

							document.getElementById("hiddenVersion").style.display = "block";
							document.getElementById("hiddens1").style.display = "block";
							document.getElementById("hiddenGroup").style.display = "block";
							document.getElementById("hiddens2").style.display = "block";
							document.getElementById("hiddendirectoriseasd").style.display = "none";
							document.getElementById("hiddendirectorise").style.display = "none";
							document.getElementById("hiddens3").style.display = "none";
							document.getElementById("hiddenfileasd").style.display = "none";
							document.getElementById("hiddenfile").style.display = "none";
							document.getElementById("hiddens4").style.display = "none";
							document.getElementById("hiddenPrivilegeCommand").style.display = "none";
							document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
							document.getElementById("hiddens5").style.display = "none";
							document.getElementById("APM1").style.display = "none";
							document.getElementById("APM2").style.display = "none";
							document.getElementById("APM3").style.display = "none";
							document.getElementById("APM4").style.display = "none";
							document.getElementById("APM5").style.display = "none";
							$('.asset1').css("display", "none");
							$('#apmhidden').css("display", "block");
							$('#hiddensys').hide();
							$('#hiddensnmp').show();
							$('#hiddenAgent').hide();
							$('#hiddenwindows').hide();
						} else if ('${asset.assetUnName}' == '1') {
							
							document.getElementById("hiddendirectoriseasd").style.display = "block";
							document.getElementById("hiddendirectorise").style.display = "block";
							document.getElementById("hiddens3").style.display = "block";
							document.getElementById("hiddenfileasd").style.display = "block";
							document.getElementById("hiddenfile").style.display = "block";
							document.getElementById("hiddens4").style.display = "block";
							document.getElementById("hiddenVersion").style.display = "none";
							document.getElementById("hiddens1").style.display = "none";
							document.getElementById("hiddenGroup").style.display = "none";
							document.getElementById("hiddens2").style.display = "none";
							
							document.getElementById("APM1").style.display = "none";
							document.getElementById("APM2").style.display = "none";
							document.getElementById("APM3").style.display = "none";
							document.getElementById("APM4").style.display = "none";
							document.getElementById("APM5").style.display = "none";
							//document.getElementById("APM1").style.display = "block";
							//document.getElementById("APM3").style.display = "block";
							//document.getElementById("APM4").style.display = "block";
							//document.getElementById("APM5").style.display = "block";
							//$('.asset1').css("display", "block");
						    $('.asset1').css("display", "none");
							$('#apmhidden').css("display", "none");
							$('#hiddensys').hide();
							$('#hiddensnmp').hide();

							if ('${asset.assetSupportDeviceId}' == 41) {
								document
										.getElementById("hiddenPrivilegeCommand").style.display = "none";
								document
										.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
								document.getElementById("hiddens5").style.display = "none";
								$('#hiddenwindows').show();
								$('#hiddenAgent').hide();
							} else {
								document
										.getElementById("hiddenPrivilegeCommand").style.display = "block";
								document
										.getElementById("hiddenPrivilegeCommandasd").style.display = "block";
								document.getElementById("hiddens5").style.display = "block";
								$('#hiddenwindows').hide();
								$('#hiddenAgent').show();
							}
						} else {
							document.getElementById("hiddendirectoriseasd").style.display = "none";
							document.getElementById("hiddendirectorise").style.display = "none";
							document.getElementById("hiddens3").style.display = "none";
							document.getElementById("hiddenfileasd").style.display = "none";
							document.getElementById("hiddenfile").style.display = "none";
							document.getElementById("hiddens4").style.display = "none";
							document.getElementById("hiddenPrivilegeCommand").style.display = "none";
							document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
							document.getElementById("hiddens5").style.display = "none";
							document.getElementById("hiddenVersion").style.display = "none";
							document.getElementById("hiddens1").style.display = "none";
							document.getElementById("hiddenGroup").style.display = "none";
							document.getElementById("hiddens2").style.display = "none";
							document.getElementById("APM1").style.display = "none";
							document.getElementById("APM2").style.display = "none";
							document.getElementById("APM3").style.display = "none";
							document.getElementById("APM4").style.display = "none";
							document.getElementById("APM5").style.display = "none";
							$('.asset1').css("display", "none");
							$('#apmhidden').css("display", "block");
							$('#hiddensys').show();
							$('#hiddensnmp').hide();
							$('#hiddenAgent').hide();
							$('#hiddenwindows').hide();
						}
						//根据页面设备类型来加载设备种类
						setTypeCategory();
						$('#dialog-device').dialog({
							autoOpen : false,
							width : 420,
							height : 450,
							buttons : {
								"确定[Enter]" : function() {
									addOk('deviceSelect','dialog-device','chk-asset');
								},
				
								"取消[Esc]" : function() {
									$(this).dialog("close");
								}
							}
						});
					});

	function addOk(typeSelect, checkBoxDiv,checkBoxName) {
		 // 点击确定按钮时，先把选择框中的内容清空
		$("#"+typeSelect).empty();
		$("#"+checkBoxDiv+" input[type='checkbox'][name="+checkBoxName+"]:checked").each(
		   function() {
			 var node = $(this).parent().next().children("a");
		        $("#"+typeSelect).append("<option value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
		         }
		);
		$("#"+checkBoxDiv).dialog('close');
	}
	//显示资产组
	function addAssetGroup() {

		var id = $('input:[name=assetGroupId]:checkbox:checked').val();

		if (id != undefined) {
			$("#assetGroupName").val($("#" + id + "_id").val());

			$("#assetGroupFeature").val($("#" + id + "_idF").val());

			$("#assetGroupName").val(id);

		}
	}

	//显示添加资产组弹出框
	function extQueryDlg() {
		checkAllReadyInput();
		$('#dialog-assetGroupDialog').dialog('open');
	}
	
	//选中已有的资产组
	function checkAllReadyInput(){
		$("#assetsSelect").children("option").each(function(){
			var val = $(this).val();
			if(val != ""){  
              //$("#"+val+"_id").siblings("input[]").attr("checked","checked");
				$("input[name='assetGroupId'][value='"+val+"']").attr("checked","checked");
			}
		});
		
	}
	

	function subData() {
	var smalDevice=$("#smalCateId").val();
		if(smalDevice==null){
			$("#smal_div").addClass("spanred");
			$("#smal_div").html("该项不能为空");
			return;
		}else{
			$("#smal_div").html("");
		}
		$('#deviceIds').val('');
	    $("#deviceSelect").children("option").each(function() {
		if ($(this).parent("select").size() > 0) {
			$('#deviceIds').val($('#deviceIds').val() + $(this).val() + ",");
		}
		});
		checkAssetIp();
		addValueToAssetGroup() ; 
		checkAssetName();
		if(""==$("#assetName").val()){
			alert("资产名称不能为空！");
			return;
		}
		var memo = checkDescription( $('#assetMemo').val() ) ; 
		if(!memo){
		    return ; 
		}
		
		var agi=$('#assetGroupId1').val();
		if(agi==""){
			alert("请选择至少一个资产组");
			return ; 
		}
		if(""==$('#smalCateId').val()||null==$('#smalCateId').val()){
			alert("请选择一个设备厂商！");
			return;
		} 
		if(0==$('#collectorIds').val()){
			alert("请选择一个采集器！");
			return;
		}
		if(""==$("#assetIp").val()||null==$("#assetIp").val()){
			alert("请输入IP地址！");
			return;
		}
		if(""==$("#assetGateWays").val()||null==$("#assetGateWays").val()){
			alert("请输入子网掩码！");
			return;
		}
		var securityLinkeThod =Trim($("#securityLinkeThod").val()); 
		var securityUserName =Trim($("#securityUserName").val()); 
		var securityPWD =$("#securityPWD").val(); 
		var securityPort =Trim($("#securityPort").val()); 
		if(securityUserName==''){
			alert("用户名不能为空！");
			return;
		}else{
			if(securityUserName.indexOf(" ")!=-1){
				alert("用户名不能包含空格！");
				return;
			}
		}
		if(securityPWD==''){
			alert("密码不能为空！");
			return;
		}else{
		if(securityPWD.indexOf(" ")!=-1){
			alert("密码不能包含空格！");
			return;
		}}
		if(!regexPort.test(securityPort)){
			alert("请填写正确的端口号！");
			return;
		}
		if(securityLinkeThod==''){
			alert("请选择连接方式！");
			return;
		}
		if(""==$("#snmpCommunityName").val()||null==$("#snmpCommunityName").val()){
			alert("请输入SNMP社区名称！");
			return;
		}
		
		if ($('#assetName').val() != "" && $('#assetIp').val() != ""
				&& assetFlag == true && ipFlag == true) {

			var count = $("#directoriseSelect").find("option").length;
			for ( var i = 0; i < count; i++) {
				if ($("#disAll").val() == "") {
					$("#disAll").val(
							$("#directoriseSelect").get(0).options[i].text);
				} else {
					$("#disAll")
							.val(
									$("#disAll").val()
											+ ","
											+ $("#directoriseSelect").get(0).options[i].text);

				}
			}
			var count = $("#fireSelect").find("option").length;
			for ( var i = 0; i < count; i++) {
				if ($("#fireAll").val() == "") {
					$("#fireAll").val($("#fireSelect").get(0).options[i].text);
				} else {
					$("#fireAll").val(
							$("#fireAll").val() + ","
									+ $("#fireSelect").get(0).options[i].text);

				}
			}

			var count = $("#assetPrivilegeCommandSelect").find("option").length;
			for ( var i = 0; i < count; i++) {
				if ($("#commandAll").val() == "") {
					$("#commandAll")
							.val(
									$("#assetPrivilegeCommandSelect").get(0).options[i].text);
				} else {
					$("#commandAll")
							.val(
									$("#commandAll").val()
											+ ","
											+ $("#assetPrivilegeCommandSelect")
													.get(0).options[i].text);
				}
			}

			var status = $('#status').val();
			if (status == '启用') {
				$('#assetStatus').val("1");
			} else {
				$('#assetStatus').val("0");
			}
			$("#assetCollectorId").val($("#collectorIds").val());
			$("#assetForm").submit();

		}

	}
	
	function addValueToAssetGroup(){
		var str="" ; 
		$('#assetsSelect').children("option").each(function(){
			var val = $(this).val() ; 
			if(val != ""){
				str = str + val +"," ; 
			}
		});
		$('#assetGroupId1').val(str);
	}
	

	//判断资产是否存在
	var assetFlag;
	function checkAssetName() {
		if ('${asset.assetId}' == '' || '${asset.assetName}' != $("#assetName").val()) {
			if ($("#assetName").val() != "") {
				var assetName = $("#assetName").val();
				$
						.ajax({
							type : "post",
							url : "${ctx}/asset/checkAssetName.action",
							async : false,
							dataType : "text",
							data : "&assetName=" + assetName,
							success : function(result) {
								if (result == 'true') {
									$("#check_loginname_msg").addClass(
											"spanred");
									$("#check_loginname_msg").html("该名称已占用");
									assetFlag = false;
								} else {
									$("#check_loginname_msg").removeClass(
											"spanred");
									$("#check_loginname_msg")
											.html(
													"<img border=0 src=\"${ctx}/images/ok.png\" />");
									assetFlag = true;
								}
							}
						});

			} else {
				assetFlag = false;
			}
		} else {
			$("#check_loginname_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
			assetFlag = true;
		}
	}
	var re1 = /^([0-9]|[1-9]\d|1\d{1,2}|2\d|2[0-3]\d|24[0-7])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d{1,2}|2\d|2[0-4]\d|25[0-5])$/; // 匹配IP地址的正则表达式
	var ipFlag;
	//验证ip地址是否合法
	function checkAssetIp() {

		var ip = $("#assetIp").val();
		if (ip != "") {

			if ('${asset.assetIps}' != ip) {
				
				var result = re1.test(ip);
				if (result) {
					if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
						$("#div_ipEnd").addClass("spanred");
						$("#div_ipEnd").html("ip不合法");
						//return false; 
						ipFlag = false;
					} else {
						var mac = $("#assetIp").val();
						
						//采集器id
						var collectorId = $("#collectorIds").val();

						/* var unType = document
								.getElementById("asset.assetUnName"); */
						//var pindex = unType.selectedIndex;
						//采集类型
						//var uName = unType.options[pindex].value;
						//设备种类
						//var deviceTypeId = $("#bigCateId").val();
						//支持设备
						//var supportDeviceId = $("#smalCateId").val();
						$
								.ajax({
									type : "post",
									url : "${ctx}/asset/checkMac.action",
									dataType : "json",
									data : "&mac=" + mac,
									success : function(result) {
										if (result) {
											$("#div_ipEnd").removeClass("spanred");
											$("#div_ipEnd")
													.html(
															"<img border=0 src=\"${ctx}/images/ok.png\" />");

											checkAssetGateWay();
											ipFlag = true;
										} else {
											$("#div_ipEnd").addClass("spanred");
											$("#div_ipEnd").html("ip地址占用");

											ipFlag = false;
										}
									}
								});

					}
				} else {
					$("#div_ipEnd").addClass("spanred");
					$("#div_ipEnd").html("ip不合法");
					ipFlag = false;
				}
			} else {
				ipFlag = true;
			}
		} else {
			ipFlag = false;
		}
	}

	function checkAssetGateWay() {
		var gate = "";
		gate = $('#assetGateWays').val();
		if (gate == "") {
			document.getElementById('assetGateWays').value = "255.255.255.0";
		}
		$("#check_gate_msg").removeClass("spanred");
		$("#check_gate_msg").empty();
		if (gate != null && gate != "") {

			var pattern = "/^(254|252|248|240|224|192|128|0)\.0\.0\.0|255\.(254|252|248|240|224|192|128|0)\.0\.0|255\.255\.(254|252|248|240|224|192|128|0)\.0|255\.255\.255\.(254|252|248|240|224|192|128|0)$/";
			eval("var pattern=" + pattern);
			var add_p1 = pattern.test(gate);
			if (add_p1 == false) {
				$("#check_gate_msg").addClass("spanred");
				$("#check_gate_msg").html("子网掩码不合法");
				return false;
			} else {
				$("#check_gate_msg").removeClass("spanred");
				$("#check_gate_msg").html(
						"<img border=0 src=\"${ctx}/images/ok.png\" />");
				return true;
			}

		} else {

			$("#check_gate_msg").removeClass("spanred");
			$("#check_gate_msg").empty();
			return true;
		}
	}

	//资产风险值计算
	function setAssetValue() {

		var use = $("#usabilityValue").val();

		var secret = $("#secretValue").val();

		var complete = $("#integrityValue").val();
		
		$.ajax({
			type : "post",
			url : "${ctx}/asset/completeAssetValue.action",
			dataType : "text",
			data : "use=" + use + "&secret=" + secret + "&complete="
					+ complete,
			success : function(result) {
			   
				$("#assetValue").val(result);
			}
		});
	}

	function setTypeUnName() {

		var unType = document.getElementById("asset.assetUnName");
		var pindex = unType.selectedIndex;
		var pValue = unType.options[pindex].value;
		//alert(pValue)
		if (pValue == 0) {
			$.ajax({
				type : "post",
				url : "${ctx}/asset/queryCategory.action",
				dataType : "json",
				data : "&snmp=" + 2,
				success : function(json) {
					var temp = "";
					var ftemp=json[0].deviceid;
					for ( var i = 0; i < json.length; i++) {
						temp = temp + "<option value='"+json[i].deviceid+"'>"
								+ json[i].devicename + "</option>";
					}
					$("#bigCateId").html(temp);
					$
					.ajax({
						type : "post",
						url : "${ctx}/asset/queryCategory.action",
						dataType : "json",
						data : "&smallCateId=" + ftemp,
						success : function(json) {
							var temp = "";
							for ( var i = 0; i < json.length; i++) {
								temp = temp
										+ "<option value='"+json[i].deviceid+"'>"
										+ json[i].devicename
										+ "</option>";
							}
							
							$("#smalCateId").html(temp);
						}
					});
				}
			});
			document.getElementById("hiddenVersion").style.display = "";
			document.getElementById("hiddens1").style.display = "";
			document.getElementById("hiddenGroup").style.display = "";
			document.getElementById("hiddens2").style.display = "";
			document.getElementById("hiddendirectoriseasd").style.display = "none";
			document.getElementById("hiddendirectorise").style.display = "none";
			document.getElementById("hiddens3").style.display = "none";
			document.getElementById("hiddenfileasd").style.display = "none";
			document.getElementById("hiddenfile").style.display = "none";
			document.getElementById("hiddens4").style.display = "none";
			document.getElementById("hiddenPrivilegeCommand").style.display = "none";
			document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
			document.getElementById("hiddens5").style.display = "none";
			document.getElementById("APM1").style.display = "none";
			document.getElementById("APM2").style.display = "none";
			document.getElementById("APM3").style.display = "none";
			document.getElementById("APM4").style.display = "none";
			document.getElementById("APM5").style.display = "none";
			$('.asset1').css("display", "none");
			$('#apmhidden').css("display", "");
			$('#hiddensys').hide();
			$('#hiddensnmp').show();
			$('#hiddenAgent').hide();
			$('#hiddenwindows').hide();
		} else if (pValue == 1) {
			document.getElementById("hiddendirectoriseasd").style.display = "";
			document.getElementById("hiddendirectorise").style.display = "";
			document.getElementById("hiddens3").style.display = "";
			document.getElementById("hiddenfileasd").style.display = "";
			document.getElementById("hiddenfile").style.display = "";
			document.getElementById("hiddens4").style.display = "";
			document.getElementById("hiddenPrivilegeCommand").style.display = "";
			document.getElementById("hiddenPrivilegeCommandasd").style.display = "";
			document.getElementById("hiddens5").style.display = "";
			document.getElementById("hiddenVersion").style.display = "none";
			document.getElementById("hiddens1").style.display = "none";
			document.getElementById("hiddenGroup").style.display = "none";
			document.getElementById("hiddens2").style.display = "none";
			$('.asset1').css("display", "none");
			$('#apmhidden').css("display", "none");
			$('#hiddensys').hide();
			$('#hiddensnmp').hide();
			$('#hiddenAgent').show();
			document.getElementById("APM1").style.display = "none";
			document.getElementById("APM2").style.display = "none";
			document.getElementById("APM3").style.display = "none";
			document.getElementById("APM4").style.display = "none";
			document.getElementById("APM5").style.display = "none";
			////document.getElementById("APM1").style.display = "";
			//document.getElementById("APM2").style.display = "";
			//document.getElementById("APM3").style.display = "";
			//document.getElementById("APM4").style.display = "";
			//document.getElementById("APM5").style.display = "";
			$
					.ajax({
						type : "post",
						url : "${ctx}/asset/queryCategory.action",
						dataType : "json",
						data : "&bigCateId=" + pValue,
						success : function(json) {
							var temp = "";
							//alert(json[0].devicename)
							for ( var i = 0; i < json.length; i++) {
								temp = temp
										+ "<option value='"+json[i].deviceid+"'>"
										+ json[i].devicename + "</option>";
							}
							$("#bigCateId").html(temp);
							$
									.ajax({
										type : "post",
										url : "${ctx}/asset/queryCategory.action",
										dataType : "json",
										data : "&smallCateId=" + 39,
										success : function(json) {
											var temp = "";
											//alert(json[0].devicename)
											for ( var i = 0; i < json.length; i++) {
												
												temp = temp
														+ "<option value='"+json[i].deviceid+"'>"
														+ json[i].devicename
														+ "</option>";
											}
											
											$("#smalCateId").html(temp);
										}
									});
						}
					});
		} else {

			$.ajax({
				type : "post",
				url : "${ctx}/asset/queryCategory.action",
				dataType : "json",
				data : "&syslog=" + 3,
				success : function(json) {
					var temp = "";
					var ftemp=json[0].deviceid;
					for ( var i = 0; i < json.length; i++) {
						temp = temp + "<option value='"+json[i].deviceid+"'>"
								+ json[i].devicename + "</option>";
					}
					$("#bigCateId").html(temp);
					$
					.ajax({
						type : "post",
						url : "${ctx}/asset/queryCategory.action",
						dataType : "json",
						data : "&smallCateId=" + ftemp,
						success : function(json) {
							var temp = "";
							for ( var i = 0; i < json.length; i++) {
								temp = temp
										+ "<option value='"+json[i].deviceid+"'>"
										+ json[i].devicename
										+ "</option>";
							}
							
							$("#smalCateId").html(temp);
						}
					});
				}
			});
			document.getElementById("hiddenVersion").style.display = "none";
			document.getElementById("hiddens1").style.display = "none";
			document.getElementById("hiddenGroup").style.display = "none";
			document.getElementById("hiddens2").style.display = "none";
			document.getElementById("hiddendirectoriseasd").style.display = "none";
			document.getElementById("hiddendirectorise").style.display = "none";
			document.getElementById("hiddens3").style.display = "none";
			document.getElementById("hiddenfileasd").style.display = "none";
			document.getElementById("hiddenfile").style.display = "none";
			document.getElementById("hiddens4").style.display = "none";
			document.getElementById("hiddenPrivilegeCommand").style.display = "none";
			document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
			document.getElementById("hiddens5").style.display = "none";
			document.getElementById("APM1").style.display = "none";
			document.getElementById("APM2").style.display = "none";
			document.getElementById("APM3").style.display = "none";
			document.getElementById("APM4").style.display = "none";
			document.getElementById("APM5").style.display = "none";
			$('.asset1').css("display", "none");
			$('#apmhidden').css("display", "");
			$('#hiddensys').show();
			$('#hiddensnmp').hide();
			$('#hiddenAgent').hide();
			$('#hiddenwindows').hide();
		}
	}

	//验证mac地址
	function checkAssetMac() {
		$("#check_mac_msg").removeClass("spanred");
		$("#check_mac_msg").empty();
		var mac = $("#assetMac").val();
		if ('${asset.assetMac}' != mac) {
			if (mac != null && mac != "") {
				var pattern = "/^([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}/";
				eval("var pattern=" + pattern);
				var add_p1 = pattern.test(mac);
				if (add_p1 == false) {
					$("#check_mac_msg").addClass("spanred");
					$("#check_mac_msg").html("mac地址不合法");
					macFlag = false;
					return false;
				} else {
					//Mac地址
					var mac = $("#assetMac").val();
					//采集器id
					var collectorId = $("#collectorIds").val();

					var unType = document.getElementById("asset.assetUnName");
					var pindex = unType.selectedIndex;
					//采集类型
					var uName = unType.options[pindex].value;
					//设备种类
					var deviceTypeId = $("#bigCateId").val();
					//支持设备
					var supportDeviceId = $("#smalCateId").val();
					/* alert(mac);
					alert(collectorId);
					alert(uName);
					alert(deviceTypeId);
					alert(supportDeviceId); */
					$
							.ajax({
								type : "post",
								url : "${ctx}/asset/checkMac.action",
								dataType : "json",
								data : "&mac=" + mac + "&collectorId="
										+ collectorId + "&uName=" + uName
										+ "&deviceTypeId=" + deviceTypeId
										+ "&supportDeviceId=" + supportDeviceId,
								success : function(result) {
									if (result) {
										$("#check_mac_msg").removeClass(
												"spanred");
										$("#check_mac_msg")
												.html(
														"<img border=0 src=\"${ctx}/images/ok.png\" />");
										macFlag = true;
										return true;
									} else {
										$("#check_mac_msg").addClass("spanred");
										$("#check_mac_msg").html("mac地址已占用");
										macFlag = false;
										return false;
									}
								}
							});

				}

			} else {
				$("#check_mac_msg").removeClass("spanred");
				$("#check_mac_msg").empty();
				macFlag = true;
				return true;
			}
		}
		/* if (mac != null && mac != "") {
			var pattern = "/^([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})(:[0-9A-Fa-f]{2}){5}/";
			eval("var pattern=" + pattern);
			var add_p1 = pattern.test(mac);
			if (add_p1 == false) {
				$("#check_mac_msg").addClass("spanred");
				$("#check_mac_msg").html("mac地址不合法");
				return false;
			} else {
				
				$("#check_mac_msg").removeClass("spanred");
				$("#check_mac_msg").html("<img border=0 src=\"${ctx}/images/ok.png\" />");
				return true;
			}

		}
		 else {
			$("#check_mac_msg").removeClass("spanred");
			$("#check_mac_msg").empty();
			return true;
		}  */
	}
	//根据设备类型来判断是否显示特权命令
	function setTypeDeviced() {
var smalDevice=$("#smalCateId").val();
		if(smalDevice==null){
			$("#smal_div").addClass("spanred");
			$("#smal_div").html("该项不能为空");
			return;
		}else{
			$("#smal_div").html("");
		}
		var uname = document.getElementById("asset.assetUnName");
		var temp = uname.selectedIndex;
		var value1 = uname.options[temp].value;

		var deviceType = document.getElementById("smalCateId");
		var pindex = deviceType.selectedIndex;
		var value = deviceType.options[pindex].value;
		if (value1 == 1) {
			if (value == 41) {
				document.getElementById("hiddens4").style.display = "none";
				document.getElementById("hiddenPrivilegeCommand").style.display = "none";
				document.getElementById("hiddenPrivilegeCommandasd").style.display = "none";
				$('#hiddenwindows').show();
				$('#hiddenAgent').hide();
			} else {
				document.getElementById("hiddens4").style.display = "block";
				document.getElementById("hiddenPrivilegeCommand").style.display = "block";
				document.getElementById("hiddenPrivilegeCommandasd").style.display = "block";
				$('#hiddenwindows').hide();
				$('#hiddenAgent').show();
			}
		}
	}
	function setTypeCategory() {
		var unType = document.getElementById("bigCateId");
		var pindex = unType.selectedIndex;
		var pValue = unType.options[pindex].value;
	/* 	if(unType.value>0){
			$('#smalCate').attr('disabled','');
		}  */
		$.ajax({
			type : "post",
			url : "${ctx}/asset/queryCategory.action",
			dataType : "json",
			data : "&upsId=" + pValue,
			success : function(json) {

			var assetSupportDeviceId = $("#assetSupportDeviceId").val();
				var temp = "";
				for ( var i = 0; i < json.length; i++) {
					
					if(json[i].deviceid==assetSupportDeviceId){
						temp = temp + "<option selected= "+"selected"+" value='"+json[i].deviceid+"'>"
						+ json[i].devicename + "</option>";
					}else{
						temp = temp + "<option value='"+json[i].deviceid+"'>"
						+ json[i].devicename + "</option>";
					}
					
					
				
					
				}
				$("#smalCateId").html(temp);
			}
		});
	}
	function directoriseDlg() {
		$("#dialog-addAccount").dialog("open");
		$("#address").val("");
	}
	function directoriseAddAccount() {
		if ($("#address").val() != "") {
			var temp = $("#directoriseSelect");
			temp.append("<option value='" + $('#address').val() + "'>"
					+ $('#address').val() + "</option>");
			$("#dialog-addAccount").dialog("close");
		}
	}

	function directoriseDel() {
		$("select[name=asset.directorise]").find("option:selected").remove();
	}
	//监控文件
	function fireDlg() {
		$("#dialog-fireAccount").dialog("open");
		$("#fireAddress").val("");
	}
	function fireAddAccount() {
		if ($("#fireAddress").val() != "") {
			var tempfire = $("#fireSelect");
			tempfire.append("<option value='" + $('#fireAddress').val() + "'>"
					+ $('#fireAddress').val() + "</option>");
			$("#dialog-fireAccount").dialog("close");
		}
	}
	function fireDel() {
		$("select[name=asset.fire]").find("option:selected").remove();
	}
	//特权命令
	function assetPrivilegeCommandDlg() {
		$("#dialog-assetPrivilegeCommandAccount").dialog("open");
		$("#assetPrivilegeCommandAddress").val("");
	}
	function assetPrivilegeCommandAddAccount() {
		if ($("#assetPrivilegeCommandAddress").val() != "") {
			var tempPrivilege = $("#assetPrivilegeCommandSelect");
			tempPrivilege.append("<option value='"
					+ $('#assetPrivilegeCommandAddress').val() + "'>"
					+ $('#assetPrivilegeCommandAddress').val() + "</option>");
			$("#dialog-assetPrivilegeCommandAccount").dialog("close");
		}
	}
	function assetPrivilegeCommandDel() {
		$("select[name=asset.assetPrivilegeCommand]").find("option:selected")
				.remove();
	}

	function updateAssetStatus(temp) {
		if (temp == 0) {
			$('#status').val('停用');
			document.getElementById('lock').disabled='disabled';
			document.getElementById('unLock').disabled='';
			//$("#lock").attr('disabled', 'disabled');
			//$("#unLock").attr('disabled', 'block');
		} else {
			$('#status').val('启用');
			document.getElementById('lock').disabled='';
			document.getElementById('unLock').disabled='disabled';
			//$("#unLock").attr('disabled', 'disabled');
			//$("#lock").attr('disabled', 'block');

		}
	}
	function initCheckBox1(typeSelect, checkName) {
	    //alert("123");
		$('#'+typeSelect).children("option").each(function() {
			//var i = $(this).val();
			//alert("123");
		
			  if($(this).attr("id")=="assetGroup")
			 {
			    //alert("12");
			    var i =$(this).val();
			    $('input[type="checkbox"][name="'+checkName+'"]').each(function() {
				if($(this).val()==i) {
					$(this).attr("checked",true);
					return false;
				}
				
			  });
			}
			
			
		});
	}
	function addAllAssetGroups()
	{
	   add_AllAssetGroups('assetsSelect','dialog-addAssets');
	   
	   $("#assetsSelect").append("<option id='assetGroup' value=\"-1\">已选择所有资产组</option>"); 
	   
	   $("#dialog-assets").dialog("close");
	}

	function addAssets(typeSelect, checkBoxDiv) {
		// 确定按钮
		$("#"+typeSelect).empty();
	
		$("#"+typeSelect).append("<option value=\"\">已选择资产组：</option>"); 
		$("#dialog-assetGroupDialog input[name='assetGroupId'][type='checkbox']:checked").each(
		   function() {
			     var v = $(this).val();
				 //var node = $(this).siblings("span[value='"+$(this).val()+"']");
				 var node = $("span[value='"+v+"']") ; 
				// alert($(this).val() +"　　adfasdf　"+node.text());
		         $("#"+typeSelect).append("<option id='assetGroup' value=\"" + $(this).val() + "\">" + node.text() + "</option>"); 
	        }
		);
		$("#dialog-assets").dialog('close');
	}
	
	function systemChange(){
		var assetSystemId = $('#systemId').val();
		var temp = "";
		var temp1 = "";
		var temp2 = "";
		if(assetSystemId == 0){
$("#div_system").addClass("spanred");
			$('#div_system').html('该项不能为空');
			temp = "<option value=0>===请选择===</option>" ;
			temp1 = "<option value=0>===请选择===</option>" ;
			temp2 = "<option value=0>===请选择===</option>" ;
			$("#BrandId").html(temp);
			$("#EditionId").html(temp1);
			$("#NumId").html(temp2);
			$("#edition").attr('disabled', 'disabled');
			$("#num").attr('disabled', 'disabled');
			$("#brand").attr('disabled', 'disabled');
			return;
		}else{
			$("#edition").attr('disabled', '');
			$("#num").attr('disabled', '');
			$("#brand").attr('disabled', '');
			$('#div_system').html('');
		}
		$.ajax({
				type : "post",
				url : "${ctx}/asset/systemChange1.action",
				dataType : "json",
				data : "&assetSystemId=" + assetSystemId,
				success : function(json) {
					if(json!=""){
						for ( var i = 0; i < json.length; i++) {
							temp = temp + "<option value='"+json[i].assetSystemId+"'>"
							+ json[i].assetSystemName + "</option>";
						}
						$("#BrandId").html(temp);
					}else{
						temp = "<option value=0>===请选择===</option>" ; 
						$("#BrandId").html(temp);
					}
				}
		});
		
		$.ajax({
				type : "post",
				url : "${ctx}/asset/systemChange2.action",
				dataType : "json",
				data : "&assetSystemId=" + assetSystemId,
				success : function(json) {
					if(json!=""){
						for ( var i = 0; i < json.length; i++) {
							temp1 = temp1 + "<option value='"+json[i].assetSystemId+"'>"
							+ json[i].assetSystemName + "</option>";
						}
						$("#EditionId").html(temp1);
					}else{
						temp = "<option value=0>===请选择===</option>" ; 
						$("#EditionId").html(temp1);
					}
					
				}
		});
		$.ajax({
				type : "post",
				url : "${ctx}/asset/systemChange3.action",
				dataType : "json",
				data : "&assetSystemId=" + assetSystemId,
				success : function(json) {
					
					if(json!=""){
						for ( var i = 0; i < json.length; i++) {
							temp2 = temp2 + "<option value='"+json[i].assetSystemId+"'>"
							+ json[i].assetSystemName + "</option>";
						}
						$("#NumId").html(temp2);
					}else{
						temp = "<option value=0>===请选择===</option>" ; 
						$("#NumId").html(temp2);
					}
				}
		});
		
		
		
		
	}
	function btnAddSystem(strno){
		var obj = document.getElementById("systemId");
		var systemNo = obj.value;
		var systemText = obj.options(obj.selectedIndex).text;
	location.href="${ctx }/assetSystem/toEditAssetSystem.action";
		
	}
	
	
	function checkFocusData(str,strv){ 
		if(document.getElementById(str).value=="" || document.getElementById(str).value==null){
			document.getElementById(strv).setAttribute("readOnly","true");		
		}else{
			//document.getElementById(strv).value="";
			document.getElementById(strv).removeAttribute("readonly");
			$("#"+strv).attr("readOnly",false) ;
		}
	}
	
	function checkClearData(str,strv){
		var reg = /^([+-]?)\d*\.?\d+$/;
		if(strv == "apv3" ){
			if(document.getElementById(str).value!="" && document.getElementById(strv).value!=null){
				if(!reg.test(document.getElementById(strv).value)){
					alert("该值只能填写数字");
					document.getElementById(strv).value="";
				}
			}
		} 
	}
	

	function checkFocus(str,strv){
		if(document.getElementById(str).value=="" || document.getElementById(str).value==null){
			document.getElementById(strv).value="";
		}
	}

		
	function subDevice(strno){
		var obj = document.getElementById("bigCateId");
		var deviceNo = obj.value;
		var deviceText = obj.options(obj.selectedIndex).text;
		location.href='${ctx}/category/toEditCategory.action';
	}	
	
	
	function checkDescription(obj){
		var len = obj.length  ; 
		$('#spanMemo').html("");
		if( len > 200 ){
			
			$('#spanMemo').addClass("spanred");
			$('#spanMemo').html(" 文本超长");
			
			return false ;
		}else{
			
			return true ; 
		}
		
		
		
	}
	
    function removeDiv(){
    	$("div [class='formErrorContent']").parent().toggle("slow") ; 
    	
    }
    function showDiv(){
    	$("div [class='formErrorContent']").parent().toggle("slow") ; 
    }
//网络拓扑添加
    function goPage(pno,psize,tableId,afterTableDivId,totalRecord){
		var itable = document.getElementById(tableId);
		//var num = $('#'+tableId+' tr').length;//表格行数
		var num=totalRecord;
		var totalPage = 0;//总页数
		var pageSize = psize;//每页显示行数
		if((num)%pageSize == 0){   
		   totalPage=parseInt((num)/pageSize);   
		   }else{   
		   totalPage=parseInt((num)/pageSize)+1;   
		   }   
		var currentPage = pno;//当前页数
		var startRow = (currentPage - 1) * pageSize+1;//开始显示的行   
		   var endRow = currentPage * pageSize;//结束显示的行   
		   endRow = (endRow >= num)? num : endRow;
		//前三行始终显示
		 //前三行始终显示
		   for(var i=0;i<1;i++){
		   var irow = itable.rows[i];
		   irow.style.display = "block";
		   }
			   for(var i=1;i<=num;i++){
			   var irow = itable.rows[i];
			   if(i>=startRow&&i<=endRow){
			   irow.style.display = "block";
			   }else{
			   irow.style.display = "none";
			   }
			   }
		//var pageEnd = document.getElementById("pageEnd");
		var tempStr = "共"+(num)+"条记录 分"+totalPage+"页 当前第"+currentPage+"页";
		if(currentPage>1){
		tempStr += "<a href=\"javascript:goPage("+(currentPage-1)+","+psize+",'"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">上一页</a>";
		}else{
		tempStr += "上一页";
		}
		if(currentPage<totalPage){
		tempStr += "<a href=\"javascript:goPage("+(currentPage+1)+","+psize+",'"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">下一页</a>";
		}else{
		tempStr += "下一页";
		}
		if(currentPage>1){
		tempStr += "<a href=\"javascript:goPage("+(1)+","+psize+",'"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">首页</a>";
		//goPage("+(totalPage)+","+psize+","+tableId+","+afterTableDivId+")
		}else{
		tempStr += "首页";
		}
		if(currentPage<totalPage){
		tempStr += "<a href=\"javascript:goPage("+(totalPage)+",'"+psize+"','"+tableId+"','"+afterTableDivId+"','"+totalRecord+"')\">尾页</a>";
		}else{
		tempStr += "尾页";
		}
		document.getElementById(afterTableDivId).innerHTML = tempStr;
}

	//加载设备列表方法
	function showDeviceDialog() {
		$("#dialog-device").dialog("open");
		
	}
	
    function changeValue(){
	var deviceMark=$("#deviceMarkId").val();
	var table=$("#dlg-table-asset");
	$("#header").nextAll("tr").remove();
	var totalRecord=0;
	$.ajax({
			      type : "post",
				   url : "${ctx}/device/queryAssociationDevices.action?datetime="+new Date(),
				   dataType : "json",
				   data : "&deviceMark="+deviceMark,
				    success : function(result) {
				    var deList=result.resultByAjax;
				  	totalRecord=deList.length;
				  	for(var i=0;i<deList.length;i++){
				  		var deviceId="chk-"+deList[i].device_id;
				  		var tr = $("<tr></tr>").attr("lineHeight","25px");
						var td1 = $("<td></td>").attr("width", "20%").attr("align", "center");
						$("<input type='checkbox' name='chk-asset' class='check-box'/>").attr("id", deviceId).val(deList[i].device_id).appendTo(td1);
						var td2 = $("<td></td>").attr("width", "300px");
						$("<a style='color: #555555' href='javascript:void(0);'></a>").html(deList[i].device_name).click(function() {
							checkLeftBox(deviceId);
						}).appendTo(td2);
						td1.appendTo(tr);
						td2.appendTo(tr);
						tr.appendTo(table);
				  	}
				  	goPage(1,8,'dlg-table-asset','barcon',totalRecord);
				  	initCheckBox('deviceSelect','chk-asset');
				   }
			 }); 
}  

 //全选及反选
	$("#asset-checkAll").live("click", function(event) {
		if ($("#asset-checkAll").attr('checked')) {
			$(".check-box").attr('checked', true);
		} else {
			$(".check-box").attr('checked', false);
		}
	}); 

//当设备属于外网设备时，让用户输入登陆名称
function changeOption(item){
	var itemValue=item.value;
	if(itemValue==1){
		document.getElementById("hiddenNetOutLoginName").style.display = "block";
		document.getElementById("hiddensLoginName").style.display = "block";
	}else{
		document.getElementById("hiddenNetOutLoginName").style.display = "none";
		document.getElementById("hiddensLoginName").style.display = "none";
	}
}
var regexPort=/^(([1-9])|([1-9][0-9]{1,3})|([1-6][0-4][0-9]{3})|([1-6][0-5][0-4][0-9]{2})|([1-6][0-5][0-5][0-2][0-9])|([1-6][0-5][0-5][0-3][0-5]))$/;
function testLinke(){
	var securityLinkeThod =Trim($("#securityLinkeThod").val()); 
	var securityUserName =Trim($("#securityUserName").val()); 
	var securityPWD =$("#securityPWD").val(); 
	var securityPort =Trim($("#securityPort").val()); 
	var ip = $("#assetIp").val();
	if (ip != "") {

			var result = re1.test(ip);
			if (result) {
				if (!(RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)) {
					
					alert("ip地址不合法！");
					//return false; 
					ipFlag = false;
				}
			}
	}else{
		alert("ip地址不能为空！");
	}
	if(securityUserName==''){
		alert("用户名不能为空！");
		return;
	}else{
		if(securityUserName.indexOf(" ")!=-1){
			alert("用户名不能包含空格！");
			return;
		}
	}
	if(securityPWD==''){
		alert("密码不能为空！");
		return;
	}else{
	if(securityPWD.indexOf(" ")!=-1){
		alert("密码不能包含空格！");
		return;
	}}
	if(!regexPort.test(securityPort)){
		alert("请填写正确的端口号！");
		return;
	}
	if(securityLinkeThod==''){
		alert("请选择连接方式！");
		return;
	}
	
	$
	.ajax({
		type : "post",
		url : "${ctx}/asset/testLinked.action",
		dataType : "text",
		data : "&mac=" + ip + "&securityUserName="
				+ encodeURI(encodeURI(securityUserName, "utf-8")) + "&securityPWD=" + securityPWD
				+ "&securityPort=" + securityPort
				+ "&securityLinkeThod=" + securityLinkeThod,
		success : function(result) {
			alert(result);
		}
	});
}
</script>
</head>

<body style="margin-top:2px;margin-left: 2px; font-size:12px;" >
<s:form action="updateAsset.action" namespace="/asset" method="post" theme="simple" id="assetForm" name="assetForm" >
	  <s:token></s:token>
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
					onclick="showDiv();">资产信息</a>
				</li>
				<li><a href="#propertyspread" style="cussor: pointer"
					onclick="removeDiv();">自定义属性</a>
				</li>

			</ul>

			

			<!-- 第一个tab页：事件统计 -->
			<div id="eventstatistics">
		
		<s:hidden name="disAll" id="disAll" />
		<s:hidden name="fireAll" id="fireAll" />
		<s:hidden name="commandAll" id="commandAll" />
		<s:hidden name="asset.assetStatus" id="assetStatus" />
		<input type="hidden" name="asset.assetIssued" value="${asset.assetIssued}" />
		<!--  总table-->

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			style=" font-size:12px;" >
			<tr>
				<!-- left area -->
				<td  width="49.5%" valign="top">
					<!--  左侧table-->
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0" style=" font-size:12px;">
							<!-- 用户信息 -->
							<tr>
								<td class='r2titler' colspan='3'><b>资产信息 </b>
								</td>
							</tr>
							<tr>
								<td>
									<!-- 资产基本信息 -->
									<table width="99%" border="0" align="center" cellspacing="0"
										cellpadding="0" style=" font-size:12px;">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 资产名称: -->
										
										<tr>
															<td width="18%" align="right" valign="top"><span
												class="spanred">*&nbsp;</span>资产：</td>
															<td width="75%" style="padding-left:6px;">
																<input
												class="validate[required,custom[spechars],custom[validateLength]] text-input "
												name="asset.assetName" value="${asset.assetName}"
												type="text" size="40" id="assetName" maxlength="255"
												style="width: 270px" onblur="checkAssetName();"/>
												<%-- <c:if test="${asset.assetName != null}">readonly="readonly"</c:if>  --%>
												<input type="hidden" name="asset.assetId"
												value="${asset.assetId}" />&nbsp;<span id="check_loginname_msg"></span></td>
														</tr>
										<!-- 空行-->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>



										<!--采集器-->
										<tr>
											<td align="right"><span
												class="spanred">*&nbsp;</span>采集器：</td>

											<td class="padding"><select style="width:270px"
												name="asset.assetCollectorId" id="collectorIds">

													<option value="0" id="collectorIds">请选择采集器</option>
													<s:iterator value="collectorList" status="stat">
														<option value="${collectorId}" id="collectorIds"
															<c:if test="${asset.assetCollectorId==collectorId}">selected="selected"</c:if>>${collectorBasic}</option>
													</s:iterator>


											

											</select></td>

											
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!--待定-->
										<tr>
											<td align="right"><span
												class="spanred">*&nbsp;</span>采集类型：</td>
											<td class="padding"><select style="width:270px"
												name="asset.assetUnName" id="asset.assetUnName"
												onchange="javascript:setTypeUnName();">
													<option value="-1"
														<c:if test="${asset.assetUnName==-1 }">selected="selected"</c:if>>syslog</option>
													<option value="0"
														<c:if test="${asset.assetUnName==0}">selected="selected"</c:if>>snmp</option>
													<option value="1"
														<c:if test="${asset.assetUnName==1}">selected="selected"</c:if>>代理</option>
											</select>
											</td>
											
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!--资产责任人-->
										<tr>
											<td align="right"><span
												class="spanred">*&nbsp;</span>资产响应人：</td>
											<td align="left" class="padding">
												<select name="asset.asset_answer_user_id"  style="width:270px">
													<s:iterator value="userlist" >
														<option value="<s:property value="userId"/>"  <c:if test="${userId==asset.asset_answer_user_id }">selected="selected"</c:if>><s:property value="userRealName"/></option>
													</s:iterator>
												</select>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!--资产责任人-->
										<tr>
											<td align="right"><span
												class="spanred">*&nbsp;</span>资产责任人：</td>
											<td align="left" class="padding">
												<select name="asset.asset_pessponsibility_user_id"  style="width:270px">
													<s:iterator value="userlist">
														<option value="<s:property value="userId"/>"  <c:if test="${userId==asset.asset_answer_user_id }">selected="selected"</c:if>><s:property value="userRealName"/></option>
													</s:iterator>
												</select>
											</td>
										</tr>
										
											
										<tr>
											<td align="right" class="td_detail_separator"></td>
										</tr>
										<!--业务标识 -->
										<tr>
											<td align="right">业务标识：</td>
											<td align="left" class="padding"><input class="validate[maxSize[50]]"
												name="asset.assetWorkIdent" value="${asset.assetWorkIdent}"
												type="text" size="40" id="assetWorkIdent" maxlength="255"
												style="width: 270px" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>


										<!--重要性-->
										<tr>
											<td align="right"><span
												class="spanred">*&nbsp;</span>重要性：</td>
											<td class="padding"><select style="width:270px"
												name="asset.assetImportance">
													<option value="-1"
														<c:if test="${asset.assetImportance==-1 }">selected="selected"</c:if>>一般</option>
													<option value="0"
														<c:if test="${asset.assetImportance==0}">selected="selected"</c:if>>重要</option>
													<option value="1"
														<c:if test="${asset.assetImportance==1}">selected="selected"</c:if>>比较重要</option>
													<option value="2"
														<c:if test="${asset.assetImportance==2}">selected="selected"</c:if>>非常重要</option>
											</select>
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
	<!-- 设备类型 -->
										<tr>
										<td align="right"><span class="spanred">*&nbsp;</span>拓扑设备类型：&nbsp;</td>
										<td class="padding">
											<select style="width: 270px;" name="deviceCategoryId">
												<s:iterator value="deviceCategoryList">
													<option value="${ deviceCategory_id}"
														<c:if test="${deviceCategory_id==deviceCategoryId }">selected="selected"</c:if>>
														${deviceCategory_name }</option>
												</s:iterator>
										</select></td>
									   </tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>所属拓扑：</td>
											<td class="padding">
												<select style="width: 270px;" name="deviceMarkId" id="deviceMarkId" onchange="changeOption(this);">
														<option value="0" <c:if test="${0==deviceMarkID }">selected="selected"</c:if>>内网</option>
														<option value="1" <c:if test="${1==deviceMarkID }">selected="selected"</c:if>>外网</option>
											</select></td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!--外网登录名-->
										<tr id="hiddenNetOutLoginName">
											<td align="right"><span class="spanred">*&nbsp;</span>登陆名称：</td>
											<td class="padding"><input name="netOutLoginName" class="validate[required,custom[spechars],custom[validateLength]]"
												value="${netOutLoginName}" type="text" size="40"
												id="netOutLoginName" maxlength="255" style="width: 270px" />
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr id="hiddensLoginName">
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right">关联设备：&nbsp;</td>
											<td width="60%" class="padding" >
															<table width="99%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td><select 
																		id="deviceSelect" name = "deviceSelect" style="font-size:12px;width:270px" size="5"
																		multiple="multiple">
																			<s:iterator value="saveDeviceList" status="stat">
																					<option value="${device_id}" id="deviceTypeid">${device_name}</option>
																			</s:iterator>
																	</select></td><s:hidden name="deviceIds" id="deviceIds" />
																</tr>
																<tr>
																	<td height="2px"></td>
																</tr>
																<tr>
																	<td><input type="button" value="" class="btnadd"
																		onclick="changeValue();showDeviceDialog();" />
																		<input type="button" value="" class="btndel"
																		onclick="delOption('deviceSelect','chk-asset');" />
																	</td>
																</tr>
															</table></td>
										</tr>
<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 描述-->
										<tr>

											<td align="right" style="vertical-align:20%">描述：</td>
											<td class="padding"><s:textarea name="asset.assetMemo"
													id="assetMemo" cols="35" rows="4" cssStyle="width:270px;" onblur="checkDescription(this.value);"></s:textarea>
													<span id="spanMemo"></span>
											</td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>
										<c:if test="${asset.assetId==null}">
											<tr>
												<td colspan="3">
													<!-- 状态内容 -->
													<table width="99%" border="0" align="center"
														cellspacing="0" cellpadding="0" style="padding-left:12px; font-size:12px">
														<!-- 状态 -->
														<tr>
															<td width="18%" align="right" valign="top">状态：</td>
															<td width="75%" style="padding-left:6px;">
																<table width="99%" border="0" cellspacing="0"
																	cellpadding="0" style="font-size:12px">
																	<tr>
																		<td><input type="text" id="status" value="启用"
																			size="40" style="width: 270px" disabled="disabled">
																		</td>
																	</tr>
																	<tr>
																		<td height="8px"></td>
																	</tr>
																	<tr>
																		<td><input id="lock" type="button" class="btnyh2"
																			style="padding-top:2px;" value="停用"
																			disabled='disabled' /> <input id="unLock"
																			type="button" class="btnyh2" style="padding-top:2px;"
																			value="启用" disabled="disabled" /> <input
																			type="button" class="btn_detail_add_del" value="注销"
																			style="display: none;" /></td>
																	</tr>
																</table></td>
														</tr>
													</table></td>
											</tr>
										</c:if>
										<c:if test="${asset.assetId!=null}">
											<tr>
												<td colspan="3">
													<!-- 状态内容 -->
													<table width="99%" align="center" border="0"
														cellspacing="0" cellpadding="0" style=" font-size:12px; padding-left:36px;">
														<!-- 状态 -->
														<tr>
															<td width="18%" align="right" valign="top" style=" font-size:12px; padding-left:36px;">状态：</td>
															<td width="75%" style="padding-left:6px; font-size:12px;" >
																<table width="99%" border="0" cellspacing="0"
																	cellpadding="0" style=" font-size:12px;">
																	<tr>
																		<td style=" font-size:12px;"><input type="text" id="status"
																			<c:if test="${asset.assetStatus==1}">value="启用"</c:if>
																			<c:if test="${asset.assetStatus==0}">value="停用"</c:if>
																			size="40" style="width: 270px" disabled="disabled">
																		</td>
																	</tr>
																	<tr>
																		<td height="8px"></td>
																	</tr>
																	<tr>
																		<td style=" font-size:12px;"><input id="lock" type="button" class="btnyh2"
																			style="padding-top:2px; " value="停用"
																			onclick="updateAssetStatus(0)"
																			<c:if test="${asset.assetStatus==0}">disabled='disabled'</c:if> />
																			<input id="unLock" type="button" class="btnyh2"
																			style="padding-top:2px;" value="启用"
																			onclick="updateAssetStatus(1)"
																			<c:if test="${asset.assetStatus==1}">disabled='disabled'</c:if> />
																			<input type="button" class="btn_detail_add_del"
																			value="注销" style="display: none;" /></td>
																	</tr>
																</table>
																
																</td>
														</tr>
													</table>
													
													</td>
											</tr>
										</c:if>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>
									
										<tr >
											<td align="right">资产组：</td>
											<td align="left" class="padding">
												 <%-- <input class="readonly" name="asset.assetGroupName" value="<c:if test="${asset==null}">未分组</c:if><c:if test="${asset!=null}">${asset.assetGroupName}</c:if>"
												type="text" size="40" id="assetGroupName" maxlength="255"
												style="width: 180px" readonly="readonly" />  --%>
												
												
												<select name="assetsSelect" id="assetsSelect" class="policySelect"
															id="timePolicySelect" style="width: 270px "  size="5"
															multiple="multiple">
															    <option value="">已选择资产组：</option>
																<s:iterator value="assetGroupList" status="stat">
																	<option id="assetGroup" value="${assetGroupId}">${assetGroupName}</option>
																</s:iterator> 
												</select>

											<input type="hidden" name="assetGroupIds" id="assetGroupId1" /> 
											
												<input type="button" value="选择资产组" class="btnstyle"
												style="padding-left:-3px;margin-top:-2px"
												onclick="extQueryDlg();" /></td>
											<td><span id="check_loginname_msg"></span></td>
										</tr>
										
								
										<!-- 空行 -->
								<tr>
									<td class="td_detail_separator"></td>
								</tr>
										<!-- 虚线分割线 -->
										<tr class="asset1">
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>
										<tr class="asset1">
											<td height="5px"></td>
										</tr>
										<tr class="asset1">
											<td align="right">端口：</td>
											<td align="left" class="padding"><input
												name="asset.assetPort" value="${asset.assetPort}"
												type="text" size="40" id="assetPort" maxlength="255"
												style="width: 270px"
												class="validate[custom[settingPort]] text-input " />
											</td>
										</tr>

										<!-- 空行 -->
										<tr class="asset1">
											<td class="td_detail_separator"></td>
										</tr>

										<tr class="asset1">
											<td align="right">轮循日志：</td>
											<td align="left" class="padding"><input
												name="asset.assetLog" value="${asset.assetLog}" type="text"
												size="40" id="assetPort" maxlength="255"
												style="width: 270px"
												class="validate[custom[settingNumber]] text-input " />&nbsp;秒
											</td>
										</tr>
										<tr class="asset1">
											<td height="5px"></td>
										</tr>
										<!-- 虚线分割线 --><%--
										<tr>
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>

										<tr>
											<td height="5px"></td>
										</tr>
										<!-- 保密性价值 -->
										<tr>
											<td align="right">保密性价值：</td>
											<td class="padding">
										    <select style="width:274px"
												id="secretValue" name="asset.secretValue"
												onchange="javascript:setAssetValue();">
													<option value="1"
														<c:if test="${asset.secretValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${asset.secretValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${asset.secretValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${asset.secretValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${asset.secretValue==5}">selected="selected"</c:if>>5</option>
											</select> 
											 <input class="readonly"
												type="text" size="40" id="secretValue"
												name="asset.secretValue"
												value="<c:if test="${asset==null}">1</c:if>
												<c:if test="${asset!=null}">${asset.secretValue}</c:if>"
												 style="width:270px" disabled="disabled"/> 
												<input type="text" value="${asset.secretValue}" style="width:270px" class="readonly" disabled="disabled"/>
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<!-- 完整性 价值-->
										<tr>
											<td align="right">完整性价值：</td>
											<td class="padding"> <select style="width:274px"
												id="integrityValue" name="asset.integrityValue"
												onchange="javascript:setAssetValue();">
													<option value="1"
														<c:if test="${asset.integrityValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${asset.integrityValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${asset.integrityValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${asset.integrityValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${asset.integrityValue==5}">selected="selected"</c:if>>5</option>
											</select> 
											<input class="readonly"
												type="text" size="40" id="integrityValue" maxlength="255"
												name="asset.integrityValue"
												value="<c:if test="${asset==null}">1</c:if><c:if test="${asset!=null}">${asset.integrityValue}</c:if>"
												style="width: 270px" disabled="disabled" />
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 可用性价值: -->
										<tr>
											<td align="right">可用性价值：</td>
											<td class="padding"> <select style="width:274px"
												id="usabilityValue" name="asset.usabilityValue"
												onchange="javascript:setAssetValue();">
													<option value="1"
														<c:if test="${asset.usabilityValue==1}">selected="selected"</c:if>>1</option>
													<option value="2"
														<c:if test="${asset.usabilityValue==2}">selected="selected"</c:if>>2</option>
													<option value="3"
														<c:if test="${asset.usabilityValue==3}">selected="selected"</c:if>>3</option>
													<option value="4"
														<c:if test="${asset.usabilityValue==4}">selected="selected"</c:if>>4</option>
													<option value="5"
														<c:if test="${asset.usabilityValue==5}">selected="selected"</c:if>>5</option>
											</select> 
											
											<input class="readonly"
												type="text" size="40" id="usabilityValue" maxlength="255"
												name="asset.usabilityValue"
												value="<c:if test="${asset==null}">1</c:if><c:if test="${asset!=null}">${asset.usabilityValue}</c:if>"
												style="width: 270px" disabled="disabled" />
											</td>
											
										</tr>

										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 可用性价值: -->
										<tr>
											<td align="right">资产值：</td>
											<td align="left" class="padding"><input class="readonly"
												type="text" size="40" id="assetValue" maxlength="255"
												name="asset.assetValue"
												value="<c:if test="${asset==null}">1</c:if><c:if test="${asset!=null}">${asset.assetValue}</c:if>"
												style="width: 270px" disabled="disabled" />
											</td>
											
										</tr>
										--%><tr>
											<td height="15px"></td>
										</tr>
										<tr id="apmhidden">
											<td height="72px"></td>
										</tr>
									</table></td>
							</tr>
						</table>
					</div>
				</td>
				<td width="20px"></td>
				<td width="49.5%" valign="top">
					<div class="framDiv">
						<table width="100%" border="0" cellspacing="1" cellpadding="0" style=" font-size:12px;">
							<tr>
								<td class='r2titler' colspan='3'><b>设备设置</b></td>
							</tr>
							<tr>
								<td>
									<table width="100%" border="0" align="center" cellspacing="0"
										cellpadding="0" style=" font-size:12px;">
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!--设备种类-->
										<tr>
											<td align="right" ><span
												class="spanred">*&nbsp;</span>设备类型：</td>
											<td class="padding"><select style="width:274px"
												name="asset.assetDeviceTypeId" id="bigCateId"
												onchange="javascript:setTypeCategory();">
													<s:iterator value="categoryName" status="stat">
														<option value="${deviceid}"
															<c:if test="${deviceid==asset.assetDeviceTypeId}">selected="selected"</c:if>>${devicename
															}</option>
													</s:iterator>
											</select>
											<input type="button" value="新增设备" class="btnstyle"
												style="padding-left:-3px;margin-top:-2px" onclick="subDevice(0); "
												 />
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right"><span
												class="spanred">*&nbsp;</span>厂商设备：</td>
											<input type = "hidden" value = "${asset.assetSupportDeviceId}" id = "assetSupportDeviceId"/>
											
											<td class="padding">
											<select style="width:274px" name="asset.assetSupportDeviceId" id="smalCateId"
												onchange="javascript:setTypeDeviced();">
											</select><span id="smal_div"></span>
										<input type="button" value="新增厂商" class="btnstyle"  id="smalCate"
												style="padding-left:-3px;margin-top:-2px" onclick="subDevice(1)" 
												 />
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>
										<%-- <tr>
											<td height="5px"></td>
										</tr>
										<!-- mac地址 -->
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>mac地址：</td>
											<td align="left" class="padding"><input
												name="asset.assetMac" value="${asset.assetMac}" type="text"
												size="40" id="assetMac" maxlength="255" style="width: 270px"
												class="validate[required,custom[settingMac]]"
												onblur="javascript:checkAssetMac();" />
											</td>
											<td><span id="check_mac_msg"></span>
											</td>
										</tr> --%>
										<!-- IP地址 -->
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>IP地址：</td>
											<td align="left" class="padding"><input
												class="validate[required,custom[onlyLetterNumber]] text-input"
												name="asset.assetIps" value="${asset.assetIps}" type="text"
												size="40" id="assetIp" maxlength="255" style="width: 270px"
												onblur="checkAssetIp();" /> &nbsp;<span id="div_ipEnd" ></span></td>
											
										</tr>
										<!-- 空行-->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!--网关 -->
										<tr>
											<td align="right"><span
												class="spanred">*&nbsp;</span>子网掩码：</td>
											<td align="left" class="padding"><input
												name="asset.assetGateWays" id="assetGateWays"
												value="${asset.assetGateWays}" type="text"
												class="validate[required,custom[settingZifang]] text-input"
												size="40" id="assetGateWay" maxlength="255"
												style="width: 270px"
												onblur="javascript:checkAssetGateWay();" /> &nbsp;<span id="check_gate_msg"></span></td>
												
											
											
											
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>用户名：</td>
											<td align="left" class="padding"><input
												class="validate[required,maxSize[50]] text-input"
												name="asset.securityUserName" value="${asset.securityUserName}" type="text"
												size="40" id="securityUserName" maxlength="50" style="width: 270px"
												 /> &nbsp;</td>
											
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>密 码：</td>
											<td align="left" class="padding"><input
												class="validate[required,maxSize[255]] text-input"
												name="asset.securityPWD" value="${asset.securityPWD}" type="password"
												size="40" id="securityPWD" maxlength="255" style="width: 270px"
												 /> &nbsp;</td>
											
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
											<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>端 口：</td>
											<td align="left" class="padding"><input
												class="validate[required,custom[settingPort]] text-input"
												name="asset.securityPort" value="${asset.securityPort}" type="text"
												size="40" id="securityPort" maxlength="50" style="width: 270px"
												 /> &nbsp;</td>
											
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
											<tr>
											<td align="right"><span class="spanred">*&nbsp;</span>连接方式：</td>
											<td align="left" class="padding">
											<select
												style="width: 270px;" name='asset.securityLinkeThod' id="securityLinkeThod">
												<option value=" ">===请选择===</option>
												<option value="SSH" <c:if test="${asset.securityLinkeThod eq 'SSH' }"> selected="selected"</c:if>>SSH</option>
												<option value="Telnet" <c:if test="${asset.securityLinkeThod eq 'Telnet' }"> selected="selected"</c:if>>Telnet</option>
												</select>
											<input type="button" value="测试连接" class="btnstyle"
												style="padding-left:-3px;margin-top:-2px" onclick="testLinke();" />
											</td>
											
										</tr>
										<!-- 空行-->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>
										
										<tr >
											<td align="right">操作系统：</td>
		    								<td align="left" class="padding">
		    									<select id="systemId" name="asset.asset_system_id" style="width:270px; padding-left:-3px;margin-top:2px" onchange="systemChange()" class="validate[required]">
													<OPTION value="0">===请选择===</OPTION>
													<s:iterator value="assetsystemlist">
														<c:if test="${assetSystemNoLevel==0 }"><option value="<s:property value='assetSystemId'/>" <c:if test="${assetSystemId==asset.asset_system_id }">selected="selected"</c:if>><s:property value="assetSystemName"/></option></c:if>
													</s:iterator>
												</select>
												<input type="button" value="新增系统" class="btnstyle"
												style="padding-left:-3px;margin-top:-2px" onclick="btnAddSystem(0)" />
		    								</td>
											
										</tr>	
											<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
		    								<td align="right">系统版本：</td>
		    								<td align="left" class="padding">
		    									<select name="asset.asset_system_version" id="EditionId" style="width:270px">
													<OPTION value="0">===请选择===</OPTION>
													<s:iterator value="SystemVersionList">
														<option value="<s:property value='assetSystemId'/>" <c:if test="${assetSystemId==asset.asset_system_version }">selected="selected"</c:if>><s:property value="assetSystemName"/></option>
													</s:iterator>
												</select>
												<input type="button" value="新增版本" class="btnstyle" id="edition"
												style="padding-left:-3px;margin-top:-2px" onclick="btnAddSystem(1)" disabled="disabled"/>
		    								</td>
		    							</tr>
		    							<!-- 空行 -->
										<tr>
												<td class="td_detail_separator"></td>
										</tr>
		    							<tr>
		    								<td align="right">系统版本号：</td>
		    								<td align="left" class="padding">
		    									<select name="asset.asset_system_versionno" id="NumId" style="width:270px">
													<OPTION value="0">===请选择===</OPTION>
													<s:iterator value="SystemVersionNoList">
														<option value="<s:property value='assetSystemId'/>" <c:if test="${assetSystemId==asset.asset_system_versionno }">selected="selected"</c:if>><s:property value="assetSystemName"/></option>
													</s:iterator>
												</select>
												<input type="button" value="新增版本号" class="btnstyle" id="num"
												style="padding-left:-3px;margin-top:-2px" onclick="btnAddSystem(2)" disabled="disabled"/>
		    								</td>
		    							</tr>
		    							<!-- 空行 -->
										<tr>
												<td class="td_detail_separator"></td>
										</tr>
		    							<tr>
		    								<td align="right">系统品牌：</td>
		    								<td align="left" class="padding">
		    									<select name="asset.asset_system_brand_name" id="BrandId" style="width:270px">
													<OPTION value="0">===请选择===</OPTION>
													<s:iterator value="SystemBrandList">
														<option value="<s:property value='assetSystemId'/>" <c:if test="${assetSystemId==asset.asset_system_brand }">selected="selected"</c:if>><s:property value="assetSystemName"/></option>
													</s:iterator>
												</select>
												<input type="button" value="新增品牌" class="btnstyle" id="brand"
												style="padding-left:-3px;margin-top:-2px" onclick="btnAddSystem(3)" disabled="disabled" />
		    								</td>
		    							</tr>
										
									
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>
										<tr>
	<td align="right"><span class="spanred">*&nbsp;</span>SNMP社区名称：</td>
											<td class="padding"><input name="snmpCommunityName" class="validate[required,custom[spechars],custom[validateLength]]"
												value="${snmpCommunityName}" type="text" size="40"
												id="snmpCommunityName" maxlength="255" style="width: 270px" />
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr >
											<td class="td_detail_separator"></td>
										</tr>
										<!-- 虚线分割线 -->
										<tr>
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>
										<tr>
											<td height="5px"></td>
										</tr>

										<tr id="APM1">
											<td align="right">APM端口：</td>
											<td align="left" class="padding"><input
												name="asset.assetAPM" value="${asset.assetAPM}" type="text"
												size="40" id="assetAPM" maxlength="255" style="width: 270px"
												class="validate[custom[settingPort]] text-input" />
											</td>
										</tr>
										<!-- 空行-->
										<tr id="APM2">
											<td class="td_detail_separator"></td>
										</tr>
										<tr id="APM3">
											<td align="right">实时监控：</td>
											<td align="left" class="padding"><input
												name="asset.assetAPMMonitor"
												value="${asset.assetAPMMonitor}" type="text" size="40"
												id="assetAPMMonitor" maxlength="255" style="width: 270px"
												class="validate[custom[settingNumber]] text-input " />&nbsp;秒
											</td>
										</tr>
										<tr id="APM5">
											<td class="td_detail_separator"></td>
										</tr>
										<tr id="APM4">
											<td colspan="3">
												<div class="xuxian"></div>
											</td>
										</tr>

										<!-- 空行 -->
										<tr id="hiddens">
											<td class="td_detail_separator"></td>
										</tr>
										<!--版本-->
										<tr id="hiddenVersion">
											<td align="right">版本：</td>
											<td class="padding"><select style="width:274px"
												name="asset.version">
													<option value="1"
														<c:if test="${asset.version=='1'}">selected="selected"</c:if>>1</option>
													<option value="2c"
														<c:if test="${asset.version=='2c'}">selected="selected"</c:if>>2c</option>
													<option value="1"
														<c:if test="${asset.version=='3'}">selected="selected"</c:if>>3</option>
											</select>
											</td>
											
										</tr>

										<!-- 空行 -->
										<tr id="hiddens1">
											<td class="td_detail_separator"></td>
										</tr>
										<!--版本-->
										<tr id="hiddenGroup">
											<td align="right">团体名：</td>
											<td class="padding"><input name="asset.organizationName"
												value="${asset.organizationName}" type="text" size="40"
												id="organizationName" maxlength="255" style="width: 270px" />
											</td>
											
										</tr>
										<!-- 空行 -->
										<tr id="hiddens2">
											<td class="td_detail_separator"></td>
										</tr>
										<!--监控目录-->
										<tr id="hiddendirectorise">
											<td align="right">监控目录：</td>
											<td class="padding"><select id="directoriseSelect"
												name="asset.directorise" style="width:260px" size="3"
												multiple="multiple">
													<c:forEach items="${directoriseList}" var="l">
														<option id="directorise">${l}</option>
													</c:forEach>
											</select>
										</tr>
										<tr>
											<td height="3px"></td>
										</tr>
										<tr id="hiddendirectoriseasd">
											<td align="right"></td>
											<td class="padding"><input type="button" value=""
												class="btnadd" onclick="directoriseDlg();" /> <input
												type="button" value="" class="btndel"
												onclick="directoriseDel();" />
											</td>
										</tr>
										<!-- 空行 -->
										<tr id="hiddens3">
											<td class="td_detail_separator"></td>
										</tr>
										<tr id="hiddenfile">
											<td align="right">监控文件：</td>
											<td class="padding"><select style="width:260px" size="3"
												id="fireSelect" name="asset.fire" multiple="multiple">
													<c:forEach items="${fireList}" var="l">
														<option id="fire">${l}</option>
													</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<td height="2px"></td>
										</tr>
										<tr id="hiddenfileasd">
											<td align="right"></td>
											<td class="padding"><input type="button" value=""
												class="btnadd" onclick="fireDlg();" /> <input type="button"
												value="" class="btndel" onclick="fireDel();" /></td>
										</tr>
										<!-- 空行 -->
										<tr id="hiddens4">
											<td class="td_detail_separator"></td>
										</tr>
										<tr id="hiddenPrivilegeCommand">
											<td align="right">特权命令：</td>
											<td class="padding"><select
												id="assetPrivilegeCommandSelect"
												name="asset.assetPrivilegeCommand" style="width:260px"
												size="3" multiple="multiple">
													<c:forEach items="${commandList}" var="l">
														<option id="assetPrivilegeCommand">${l}</option>
													</c:forEach>
											</select>
										</tr>
										<tr>
											<td height="2px"></td>
										</tr>
										<tr id="hiddenPrivilegeCommandasd">
											<td align="right"></td>
											<td class="padding"><input type="button" value=""
												class="btnadd" onclick="assetPrivilegeCommandDlg();" /> <input
												type="button" value="" class="btndel"
												onclick="assetPrivilegeCommandDel();" /></td>
										</tr>

										<!-- 空行 -->
										<tr id="hiddens5">
											<td class="td_detail_separator"></td>
										</tr>
										<tr id="hiddensys" height="274px">
											<td></td>
										</tr>
										<tr id="hiddensnmp" height="210px">
										<td></td>
										</tr>
										<tr id="hiddenAgent" height="0px">
										<td></td>
										</tr>
										<tr id="hiddenwindows" height="44px">
										<td></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div></td>
			</tr>
		</table>
	
		
		
		<!-- 资产组dialog -->
	<div id="dialog-assetGroupDialog" title="资产组选择">
		<table align="center">

			<tr>
				<td>
					<div class="big">
						<div class="left">
							  <s:property value="htmlBuffer" escape="false"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- 监控目录dialog -->
	<div id="dialog-addAccount">
		<input type="hidden" name="account_id" id="account_id" />
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px; font-size:12px; height:100px" >
			<tr>
				<td align="left">监控目录:</td>
				<td><input id="address" name="address" type="text"
					style="width:240px;" /></td>
			</tr>
		</table>
	</div>
	<div id="dialog-fireAccount">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px; font-size:12px;  height:100px" >
			<tr>
				<td align="left">监控文件:</td>
				<td><input id="fireAddress" name="fireAddress" type="text"
					style="width:240px;" /></td>
			</tr>
		</table>
	</div>
	<div id="dialog-assetPrivilegeCommandAccount">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px; font-size:12px;  height:100px">
			<tr>
				<td align="left">特权命令:</td>
				<td><input id="assetPrivilegeCommandAddress"
					name="assetPrivilegeCommandAddress" type="text"
					style="width:240px;" /></td>
			</tr>
		</table>
	</div>
	

	<s:iterator value="assetsystemlist">
		<input type="hidden" id="<s:property value='assetSystemId'/>" value="<s:property value='assetSystemName'/>"/>
	</s:iterator>


			</div>

			<!-- 第二个tab页：用户自定义属性 -->
			<div id="propertyspread">
			<div class="framDiv"  style="width:600px;" style="margin-left:-18px">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" style=" font-size:12px;">
			<tr>
				<td class='r2titler' colspan='3' style="font-size:12px;"><b>用户自定义属性</b></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" align="center" cellspacing="0"
						cellpadding="0">
						
						<tr>
							<td width="20%" style="padding-left:12px;font-size:12px;"><span
								class="spanred"></span>自定义属性:
							</td>
							<td width="20%" style="padding-left:12px;font-size:12px;">
								<span class="spanred"></span>自定义属性值:
							</td>
							<td width="20%" style="padding-left:12px;font-size:12px;">
								<span class="spanred"></span>属性类型:
							</td>
							
						</tr>
						<tr >
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td  class="padding">
								<input id="ap1" class="validate[maxSize[50],custom[spechars]]" name="asset.assetProperty1" value="<s:property value='asset.assetProperty1'/>"  style="width: 170px" onblur="checkFocusData('ap1','apv1')" onkeypress="if(event.keyCode==9)checkFocusData('ap1','apv1');"/>
							</td>
							<td  class="padding">
								<input id="apv1" name="asset.assetPropertyValue1" class="validate[maxSize[50],custom[spechars]]" value="<s:property value='asset.assetPropertyValue1'/>" style="width: 170px"  onfocus="checkFocus('ap1','apv1')"/>
							</td>
							<td width="20%" style="padding-left:12px;font-size:12px;">
								<span id="check_loginname_msg"></span>
								字符串类型
							</td>
						</tr>
						<tr >
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td  class="padding">
								<input id="ap2" name="asset.assetProperty2" class="validate[maxSize[50],custom[spechars]]" value="${asset.assetProperty2}"  style="width: 170px" onblur="checkFocusData('ap2','apv2')" onkeypress=" if(event.keyCode == 9)checkFocusData('ap2','apv2'); "/>
							</td>
							<td  class="padding">
								<input id="apv2" name="asset.assetPropertyValue2" class="validate[maxSize[50],custom[spechars]]" value="${asset.assetPropertyValue2}" style="width: 170px"  onfocus="checkFocus('ap2','apv2')"/>
							</td>
							<td width="20%" style="padding-left:12px;font-size:12px;">
								<span id="check_loginname_msg"></span>
								字符串类型
							</td>
						</tr>
						<tr >
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td  class="padding">
								<input id="ap3" name="asset.assetProperty3" value="${asset.assetProperty3 }"  style="width: 170px" onblur="checkFocusData('ap3','apv3')" onkeypress="if(event.keyCode == 9)checkFocusData('ap3','apv3');"/>
							</td>
							<td  class="padding">
								<input id="apv3" name="asset.assetPropertyValue3" <c:if test="${asset.assetPropertyValue3!=0 }"> value="${asset.assetPropertyValue3 }"</c:if> style="width: 170px"  onblur="checkClearData('ap3','apv3')" onfocus="checkFocus('ap3','apv3')"/>
							</td>
							<td width="20%" style="padding-left:12px;font-size:12px;">
								<span id="check_loginname_msg"></span>
								数字类型
							</td>
						</tr>
						<tr >
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td  class="padding">
								<input id="ap4" name="asset.assetProperty4" value="${asset.assetProperty4 }"   style="width: 170px" onblur="checkFocusData('ap4','apv4')" />
							</td>
							<td  class="padding">
								<input id="apv4" name="asset.assetPropertyValue4" value='<s:date name="asset.assetPropertyValue4" format='yyyy-MM-dd' />' style="width: 170px ;font-size:12px;" class="Wdate" type="text" onClick="WdatePicker()"  onfocus="checkFocus('ap4','apv4')"/>
							</td>
							<td width="20%" style="padding-left:12px;font-size:12px;">
								<span id="check_loginname_msg"></span>
								时间类型
							</td>
						</tr>
						<tr >
							<td class="td_detail_separator"></td>
						</tr>
						<tr>
							<td  class="padding">
								<input id="ap5" name="asset.assetProperty5" value="${asset.assetProperty5 }"  style="width: 170px" onblur="checkFocusData('ap5','apv5');" />
							</td>
							<td  class="padding">
								
								<input id="apv5" name="asset.assetPropertyValue5" value='<s:date name="asset.assetPropertyValue5" format='yyyy-MM-dd' />' style="width: 170px ;font-size:12px;" class="Wdate" type="text" onClick="WdatePicker()" onfocus="checkFocus('ap5','apv5');"/>
							</td>
							<td width="20%" style="padding-left:12px;font-size:12px;" >
								<span id="check_loginname_msg"></span>
								时间类型
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>
		</table>
	</div>
				
			</div>
		</div>

	</div>
	<c:if test="${isMonitorNetworkTopology==0}">
			<table width="97%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px;">
			<tr>
				<td class="td_detail_separator"></td>
			</tr>

			<tr>
				<td align="right"><input type="button" class="btnyh"
					id="btnSave" value="保  存" onclick="subData();" /> 
				
					<!--  
					<input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.location.href='${ctx}/asset/queryAsset.action';" />
					-->
					
					<input
					type="button" class="btnyh" id="btnCancel" value="取  消"
					onclick="window.history.back();" />
					
				</td>
			</tr>
		</table>
		</c:if>
	<div id="hideDiv"></div>
	<!-- 事件详情弹出框 -->
	</s:form>
	<div id="dialog-device" title="关联设备" >
	
		 <table id="dlg-table-asset" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="font-size:12px; overflow:auto" >		

             <tr id="header" height="28">
				<td width="6%" align="center" class="biaoti"><input
					type="checkbox" id="asset-checkAll" name="asset-checkAll" />
				</td>
				<td width="100%" align="center" class="biaoti">关联设备</td>
			</tr>
<%--          	<s:iterator value="associationDeviceList">
				<tr style="line-height: 25px;">
					<td width="20%" align="center"><input type="checkbox"
						name="chk-asset" id="chk-${device_id}"
						value="${device_id}" /></td>
					<td width="c"><a style='color: #555555'
						href="javascript:void(0);"
						onclick="checkLeftBox('chk-${device_id}')">${device_name}</a>
					</td>
				</tr>
			</s:iterator> --%>
		</table>
		
		<table width="80%" align="right" style="font-size:12px;">
						<tr>
				<td>
					<div id="barcon" name="barcon"></div>
			</td>
		</tr>
		</table> 

	</div>
</body>
</html>
