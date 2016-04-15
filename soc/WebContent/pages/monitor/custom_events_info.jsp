<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
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
    
    <title>My JSP 'audit_report.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/soc/styles/library.css" rel="stylesheet"
	type="text/css">
	<link href="/soc/styles/user/user.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css"> 
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
    <script  type="text/javascript" src="/soc/scripts/highcharts.js"></script>
<script type="text/javascript" src="/soc/scripts/exporting.js"></script>

<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
	
	<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
		<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>

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
.biaoti1 {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
}

.tbl1 {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	color: black;
	height: 28px;
	text-align: center;
}

.level {
	padding: 0px 0px 0px 0px;
	margin: 2px 2px 2px 4px;
	border: 1px solid #CCCCC0;
	height: 12px;
	width: 40px;
}

.levelBa {
	height: 12px;
	margin: 0px 0px 0px 0px;
}

.scrollstyle {
	scrollbar-face-color: #FFFFFF;
	scrollbar-shadow-color: #cdcdcd;
	scrollbar-highlight-color: #cdcdcd;
	scrollbar-3dlight-color: #FFFFFF;
	scrollbar-darkshadow-color:#FFFFFF;
	scrollbar-track-color: #FFFFFF;
	scrollbar-arrow-color: #cdcdcd;
}

#risingTab td{word-break: keep-all;white-space:nowrap;}

.haveBackground{
    background: none repeat scroll 0 0 #FFFFFF;
    line-height: 28px;
    text-align: center;
    }
font {
	size: 12;
}
</style>
 <script type="text/javascript">
 function paging_changeRecord(url,num,event,keywordId){
		showeventList(num);
		
	}
	function paging_gopage(lastnum,keywordId,url,event)
	{
		
		var page=document.getElementById('paging_Page').value;
		
		
		var keyword="";
		
		var num=parseInt(page*15)-parseInt(15);
		
		if(num > lastnum || num < 0)
		{
			alert("错误页数");
			return false;
		}
		
		if(num<0)
		{
			num=0;
		}
		
		document.getElementById('startIndex').value=num;
		
		showeventList(num);
		
	}
	function paging_page(tableId,url,item,keywordId,event,column){
		if(item.currentPage != null){
			var htmlPage = "";
			//htmlPage ="<table width='100%'>";
			//htmlPage += "<tr>";
				//htmlPage +="<td width='100%'>";
					htmlPage ="<table id='test12' width='100%' border='0' cellspacing='1' cellpadding='0' >";
						htmlPage +="<tr>";
							htmlPage +="<td>";
								htmlPage +="<table align='right' style='font-size: 12px'>";
									htmlPage +="<tr>";
										htmlPage +="<td>";
										htmlPage +="共"+ item.totalCount +"条记录";
										htmlPage +="<input type='hidden' name='startIndex' id='startIndex' value='0'>";
										htmlPage +="<input type='hidden' name='lastIndex' id='lastIndex' value="+ item.lastIndex + ">";
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.startIndex != 0){
											htmlPage +="<a href=" +"javascript:paging_changeRecord(\""+url+"\",'0',\""+event+"\",\""+keywordId+"\")"+" >首页</a>";
										}else{
											htmlPage +="首页";
										}																							
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.startIndex != 0){
											htmlPage +="<a href=" + "javascript:paging_changeRecord(\""+url+"\"," + item.previousIndex + ",\""+event+"\",\""+keywordId+"\") "+ " >上一页</a>";
										}else{
											htmlPage +="上一页";
										}
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.nextIndex > item.startIndex){
											htmlPage +="<a href=" + "javascript:paging_changeRecord(\""+url+"\"," + item.nextIndex + ",\""+event+"\",\""+keywordId+"\")" + " >下一页</a>";
										}else{
											htmlPage +="下一页";
										}					
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.lastIndex == item.startIndex){
											htmlPage +="末页";
										}else{
											htmlPage +="<a href=" + "javascript:paging_changeRecord(\""+url+"\"," + item.lastIndex + ",\""+event+"\",\""+keywordId+"\")" + " >末页</a>";
										}
										htmlPage +="</td>";
										htmlPage +="<td>";
										htmlPage +=" <input type='text' style='width: 30px' name='paging_Page' id='paging_Page'  size='3' >";
										htmlPage +="<input type='button' value='GO' class='btn1' onclick='javascript:paging_gopage("+item.lastIndex+",\""+keywordId+"\",\""+url+"\",\""+event+"\")' >";
										htmlPage +="当前第" + item.currentPage + "/" + item.pageCount + "页";													
										htmlPage +="</td>";
									htmlPage +="</tr>";
								htmlPage +="</table>";
							htmlPage +="</td>";
						htmlPage +="</tr>";
					htmlPage +="</table>";
				//htmlPage +="</td>";
			//htmlPage +="</tr>";
		  // alert(htmlPage);
		$(htmlPage).insertAfter($("#"+tableId+""));
		}
	}
var chart;
 
 function assetChart(){
	 var x=$("#xChartsBuff").val();
	 var y =$("#chartsBuff").val();
	 chart = new Highcharts.Chart({
		 chart: {
	            renderTo: 'container', //图表放置的容器，DIV
	            defaultSeriesType: 'spline', //图表类型为曲线图
	            height:300,
	            margin:[30, 20, 50, 30]
	           
	           
	        },
         title: {
             text: ''
            
         },
         subtitle: {
             text: ''
             
         },
         xAxis: {
             categories: [${chartsBuffx}],
                 labels: {
                     rotation: -60,
                     align: 'right',
                     style: {
                         fontSize: '10px',
                         fontFamily: ''
                     }
                 }
         },
         yAxis: {
             title: {
                 text: ''
             },
             min: 0,  

             minrTickInterval: 'auto',// y轴样式 网格的形式
             plotLines: [{
                 value: 0,
                 width: 1,
                 color: '#808080'
             }],
             labels: {
                
                 style: {
                     fontSize: '10px',
                     fontFamily: ''
                 }
             }
         },
         tooltip: {
             valueSuffix: ''
         },
         legend: {
        	 layout: 'horizontal',
             align: 'right',
             verticalAlign: 'top',
             borderWidth: 0
             
         },
         credits: {
				enabled: false
			},
			exporting:{ 
		           enabled:false//用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
		 		},
         series: [${chartsBuff}]
     });
	}
 function getEventListJson(url, num) {
	 var trendId=$("#trendId").val();
		var htmlStr="";
		$
				.getJSON(
						url,
						function(result) {
                       
							$("#bottomtable tr:not(:first)").remove();
							 $("#bottomtable tr:first").remove();
							$("#test12").remove();
							$
									.each(
											result,
											function(i, item) {
												//var rowNum = $("#bottomtable tr").length - 1;

												if (item.eventsId
													) {
													htmlStr += "<tr class='back' title='双击放大' onmouseover='show(this);' onmouseout='hide(this);' ondblclick='dblclick("
															+ item.identification+","+item.customs1
															+ ");'>";
													htmlStr += "<td valign='middle' align='center' width='10%'>"
															+ item.priority;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='20%'>"
															+ item.name;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='15%'>"
															+ item.customs5;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='15%'>"
															+ item.devAddT;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='10%'>"
															+ item.aggregatedCount;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='*'>"
															+ item.receptTimes;
													htmlStr += "</td>";
													
													htmlStr += "</tr>";
													

												}
												if (item.currentPage) {
													paging_page(
															"footer",
															"${ctx}/customTrend/queryAllEvents.action?trendId="+trendId,
															item,
															"dlg-keyword-resource-id",
															"getEventListJson",
															1);
												}
											});
							
							$("#dynamicEcents").html(htmlStr);

						});
						
						

	}
 function dblclick(identifaction,tableName) {
		var docHe =  ($(document).height()/2)-60;
		var docWi =  ($  (document).width()/2)-200;
		$("#hideDiv").addClass("hideDiv");
		$("#hideDiv").css({
		    width: $(document).width(), 
		    height: $(document).height()
		});
		$("#loding").toggle("fast");
		$("#loding").css({top:docHe,left:docWi}); 
		
		var falg = false;
		$.ajax({
			type : "POST",
			url : "${ctx}/events/eventsDetails.action?",
			async : true,
			dataType : "json",
			data : "eventsLogIdentification=" + identifaction + "&tableName="+tableName,
			success : function(eventsResult) {
				var obj = eventsResult;
				
				if (obj != null) {
					   $("#occurTime").text(obj.receptTimes);
                    $("#sendTime").text(obj.sendTimes);
                    $("#priority").text(obj.priority);
                    $("#type").text(obj.typeCustomd);
                    $("#count").text(obj.aggregatedCount);
                    $("#name").text(obj.name);
                    $("#customs4").text(obj.customs4);
                    $("#msg").text(obj.msg);
                    $("#sAdd").text(obj.sAdd);
                    $("#sPort").text(obj.sPort);
                    $("#sUser").text(obj.suserName);
                    $("#tAdd").text(obj.tAdd);
                    $("#digest").text(obj.nameCustomd);
                    $("#tPort").text(obj.dPort);
                    $("#tUser").text(obj.dUserName);
                    $("#category").text(obj.cateGoryCustomd);
                    $("#devName").text(obj.customs5);
                    $("#devCategory").text(obj.devCategory);
                    $("#action").text(obj.action);
                    $("#result").text(obj.result);
                    $("#dUserName").text(obj.dUserName);
                    $("#devAddr").text(obj.devAddT);
                    $("#devVendor").text(obj.devVendor);
                    $("#devType").text(obj.devproduct);
                    $("#sensorName").text(obj.sensorName);
				}
				$("#loding").toggle("slow");
				$('#dialog-enentsContent').dialog('open');
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if (textStatus != "") {
					alert("请求错误刷新后重试");
					 $("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0); 
					 $("#hideDiv").css({
				            width: 0, 
				            height: 0
				        });
					$("#loding").hide();
				}
			}
		});
		
		return falg;
	}
 function showeventList(num) {
		var trendId = $("#trendId").val();
		getEventListJson("${ctx}/customTrend/queryAllEvents.action?startIndex="
				+ encodeURI(num, "utf-8") + "&trendId="+trendId)
				;
	}
 function closeContent() {
		$('#dialog-enentsContent').dialog("close");
		if (sourceType == 0) {
			setTimeout("reFresh(" + sourceType + ")", 6 * 60 * 1000);
		}
	}
 function show(obj) {
		$(obj).removeClass("back");
		$(obj).addClass("hand");
	}

	function hide(obj) {
		$(obj).removeClass("hand");
		$(obj).addClass("back");
	}
	function geteventstatistics() {
		var trendId = $("#trendId").val();
		$.ajax({
			type : "post",
			dataType : "json",
			url : "${ctx}/customTrend/queryAllEvents.action?trendId="+trendId,
			success : function(data) {
				initeventChart(data);
			}
		});

	}
	function action(id)
    {
            changeIcon($("#img_"+id));
            $("#column_"+id+" >ul").toggle("slow");
    
    }
    
     function changeIcon(nainNode)
    {
        if(nainNode)
        {
            if(nainNode.attr("src").indexOf("u321_normal.gif")>=0)
            {
               nainNode.attr("src","${ctx}/images/u319_normal.gif");
               
            }else
            {
               nainNode.attr("src","${ctx}/images/u321_normal.gif");
            }
         }
     }
 
 
 
 jQuery(document).ready(function() {
	 $('#dialog-enentsContent').dialog({
			autoOpen : false,
			height : 600,
			width : 700,
			beforeclose: function(event,ui) {
		       $("#hideDiv").removeClass("hideDiv");$("#hideDiv").css("width",0);$("#hideDiv").css("height",0);     
		       $("#hideDiv").css({
		            width: 0, 
		            height: 0
		        });
            $("#loding").hide();
            for(var i=1; i < 5; i++)
            {
                $("#img_"+i).attr("src","${ctx}/images/u319_normal.gif");     
                $("#column_"+i+" >ul").show();
            }
			}
		});
	 assetChart();
		$('#tabs-setting').tabs();
		//初始化格式对话框
		
		showeventList(0);
		$('#dialog-extQuery').dialog({
			autoOpen : false,
			width : 700,
			height : 300,
			buttons: {
				"取消[Esc]": function() {
					$(this).dialog("close");
				}
			}
		});
		
		
			
	});
	
function Export(reportType){
	$("#reportType").val(reportType);
	 $("#picture1").val(chart.getSVG());
	 $("#export").submit();
	 $("#dialog-extQuery").dialog("close");
	
	 	
	 	}
	 	function fush(){
	 		var trendId = $("#trendId").val();
	 		parent.mainFrame.location.href = '/soc/customTrend/queryEvents.action?trendId='+trendId;

	 	}
 </script>
  </head>
 
  <body >
  
  <s:form action="exportReport" namespace="/customTrend" method="post" id = "export">
  <input type="hidden"  name="securityReportId" id="securityReportId" value = "15"/>  
  <input type="hidden"  name="picture1" id="picture1"></input>
  <input type="hidden"  name="reportType" id="reportType"></input>
  <input type="hidden" name="picCount" id="picCount" value="1"/>
  <input type="hidden" id="trendId" name="trendId" value="${trendId}">
  </s:form>
  <input type="hidden" id="xChartsBuff" value="${chartsBuffx}">
  <input type="hidden" id="chartsBuff" value="${chartsBuff}">
  
  <table width="100%">
   <tr>
				<td>
					<div class="box">

						<div class="right">
							<input type="button" value="刷 新" class="btnstyle"
								style="margin-right:5px;margin-top:2px"
								onclick="fush();" />
							<input type="button" value="导 出" class="btnstyle"
								style="margin-right:5px;margin-top:2px"
								onclick="$('#dialog-extQuery').dialog('open');" />
								
							
						</div>

						</div>
				</td>

			</tr>
  <tr>
  <td>
  <div class="evnTitleTop"
			style=" overflow-x:hidden;width:49.6%;height:350px;overflow-y:auto;float:left; border:1px solid #D0D9E1"
			class="scrollstyle" id="d2" >
			<table style="width:100%;font-size: 12px">
				<tr>
					<td class="biaoti" width="100%" >趋势分析折线图</td>
				</tr>
				<tr>	
					<td style="width:100%;" valign="top">
						<div id="container">
							
							
						</div></td>
				</tr>

			</table>
		</div>
		<div class="evnTitleTop"
			style=" overflow-x:hidden;width:49.6%;height:350px;overflow-y:auto;float:left; border:1px solid #D0D9E1"
			class="scrollstyle" id="d2" >
			<table style="width:100%;font-size: 12px">
				<tr>
					<td class="biaoti" width="100%">趋势分析数据</td>
				</tr>
				<tr>	
					<td style="width:100%;" valign="top">
						<div class="sbox">
							<div class="cont">
								<!-- information area -->
								<div id="dataList">
									<table width="100%" border="0" cellspacing="1" cellpadding="0"
										class="tab2" id="colTable" style="font-size: 12px">
										<tr height="28" id="collectionTR">
										<td width="33%" class="biaoti">时间</td>
											<td width="33%"  class="biaoti">地址</td>
											<td width="33%" class="biaoti">数量</td>
											
										</tr>
										<tbody id="colBody">
											<s:iterator value="voList" status="state">
                                        
                                        <tr>
                                         <td valign="middle" class="td_list_data" >${trendTime}</td>
										    <td valign="middle" class="td_list_data" >${trendIp}</td>
										    
										    <td valign="middle" class="td_list_data" >${trendCount}</td>
										     
										    </tr>
										  </s:iterator>  
										
										</tbody>
									</table>
								</div>
							</div>
						</div></td>
				</tr>

			</table>
		</div>
  </td>
  </tr>
 
  <tr>
  <td><!-- 底部不变区域 -->
		
			<div
				style=" width: 99.4%; margin-right: 0.5%;border: 1px solid #D2E8FA">
				<table width="100%" align="center" id="footer" style="font-size: 12px;">
					<tr>
						<td colspan="8">
							<table width="99%" border="0" cellspacing="1" cellpadding="0" style="font-size: 12px;">
								<tr height="28" id="collectionTR">
									<td width="10%" class="biaoti">事件级别</td>
									<td width="20%" class="biaoti">事件名称</td>
									<td width="15%" class="biaoti">设备名称</td>
									<td width="15%" class="biaoti">设备IP</td>
									<td width="10%" class="biaoti">数量</td>
									<td width="*" class="biaoti">发生时间</td>
									<!-- <td width="10%" class="biaoti">处理情况</td> -->
								</tr>

							</table>
						</td>
					</tr>
					<tr>
						<td align="center" width="100%">
							<div style="overflow-y:scroll; width:100%; height: 320px"
								id="screentable2">
								<table width="100%" class="eventslist" cellspacing="1"
									cellpadding="0" id="bottomtable" style="font-size: 13px">
									<tbody id="dynamicEcents">

									</tbody>
									


								</table>

							</div>
						</td>

					</tr>

				</table>

		
		</div>
  </td>
  
  </tr>
  <tr>
  
  </tr>
  </table>
   <!-- 导出的dialog -->
	<div id="dialog-extQuery" title="选择导出格式" style="font-size: 12px;" >
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<!-- left area -->
				<td width="100%" valign="top">
					<!--  左侧table-->
					<div class="framDiv" style="border:none;">
									<!-- 报表模板内容 -->
									<table width="70%" border="0" cellspacing="0" cellpadding="0"
											style="margin-left: 4px;" align="center">
											<!-- 空行 -->
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
								
											<tr>
												<td align="center" width="30%">
												<img src="/soc/images/u19.png" >
													<input type="button" class="btnyh" width="50%" style="font-size: 12px;"
													id="btnSave" value="导出为html" onclick="Export('html');" />
												</td>
												<td align="center" width="30%">
												<img src="/soc/images/u21.png">
													<input type="button" class="btnyh" width="50%" style="font-size: 12px;"
													id="btnSave" value="导出为DOC" onclick="Export('doc');" />
												</td>
												<td align="center" width="30%">
												<img src="/soc/images/u23.png" >
													<input type="button" class="btnyh" width="50%" style="font-size: 12px;"
													id="btnSave" value="导出为PDF" onclick="Export('pdf');" />
												</td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
											<tr>
												<td class="td_detail_separator"></td>
											</tr>
										</table>
						</div>
				</td>
			</tr>
			
		</table>
	</div>
   <div id="dialog-enentsContent" title="详细信息"
			class="dialog-enentsContent" style="height: 200px">
			<input type="button" value="返回列表" class="btnstyle"
				style="margin-right:5px;margin-top:-2px" onclick="closeContent();" />
			<div style="margin:5px 0px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_1" class="img_a"
						onclick="action(1)">&nbsp;<span class="title_t_t">设备信息</span>
					</span>
				</div>
				<div id="column_1">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%" style="font-size: 12px;">
								<tr>
									<td class="rowLT">设备名称</td>
									<td class="rowLV" id="devName">&nbsp;</td>
									<td class="rowRT">ip地址</td>
									<td class="rowLV" id="devAddr">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">设备厂商</td>
									<td class="rowLV" id="devVendor">&nbsp;</td>
									<td class="rowRT">设备型号</td>
									<td class="rowLV" id="devType">&nbsp;</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
			</div>

			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_2" class="img_a"
						onclick="action(2)">&nbsp;<span class="title_t_t">时间信息</span>
					</span>
				</div>
				<div id="column_2">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%" style="font-size: 12px;">
								<tr>
									<td class="rowLT">产生时间</td>
									<td class="rowLV" id="sendTime">&nbsp;</td>
									<td class="rowRT">接收时间</td>
									<td class="rowLV" id="occurTime">&nbsp;</td>
								</tr>
							</table>
						</li>
					</ul>
				</div>
			</div>
			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_3" class="img_a"
						onclick="action(3)">&nbsp;<span class="title_t_t">基本信息</span>
					</span>
				</div>
				<div id="column_3">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%" style="font-size: 12px;">
								<tr>
									<td class="rowLT">等&nbsp;&nbsp;级</td>
									<td class="rowLV" id="priority">&nbsp;</td>
									<td class="rowRT">数量</td>
									<td class="rowLV" id="count">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件名称</td>
									<td colspan="3" class="rowRV" id="name">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件类别</td>
									<td colspan="3" class="rowRV" id="category">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件类型</td>
									<td colspan="3" class="rowRV" id="type">&nbsp;</td>
								</tr>

								<tr>
									<td class="rowLT">事件描述</td>
									<td colspan="3" class="rowRV" id="customs4">&nbsp;</td>
								</tr>
								<!-- 	<tr>
									<td class="rowLT">摘要</td>
									<td colspan="3" class="rowRV" id="digest">&nbsp;</td>
								</tr> -->
							</table>
						</li>
					</ul>
				</div>
			</div>

			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_4" class="img_a"
						onclick="action(4)">&nbsp;<span class="title_t_t">来源目标信息</span>
					</span>
				</div>
				<div id="column_4">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%" style="font-size: 12px;">
								<tr>
									<td class="rowLT">源地址</td>
									<td class="rowLV" id="sAdd">&nbsp;</td>
									<td class="rowRT">源端口</td>
									<td class="rowLV" id="sPort">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">目标地址</td>
									<td class="rowLV" id="tAdd">&nbsp;</td>
									<td class="rowRT">目标端口</td>
									<td class="rowLV" id="tPort">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件来源用户名</td>
									<td colspan="3" class="rowRV" id="sUser"></td>
								</tr>
							</table>
						</li>
					</ul>
				</div>

			</div>
			<div style="height: 10px;"></div>

			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/u319_normal.gif" id="img_6" class="img_a"
						onclick="action(6)">&nbsp;<span class="title_t_t">事件分类信息</span>
					</span>
				</div>

				<div id="column_6">
					<ul class="display">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%" style="font-size: 12px;">
								<!-- <tr>
								<td class="rowLT">事件对象分类</td>
								<td class="rowLV" id="category"></td>
								<td class="rowRT">事件设备分类</td>
								<td class="rowRV" id="devCategory"></td>
							</tr> -->
								<tr>
									<td class="rowLT">事件动作</td>
									<td class="rowLV" id="action"></td>
									<td class="rowRT">动作结果</td>
									<td class="rowRV" id="result"></td>
								</tr>
								<!-- <tr>
								<td class="rowLT">事件采集技术</td>
								<td colspan="3" class="rowRV" id="sensorName"></td>
							</tr>
							<tr>
								<td class="rowLT">事件特征</td>
								<td colspan="3" class="rowRV"></td>
							</tr> -->
							</table></li>
					</ul>
				</div>
			</div>


		</div>
		
		<div id="loding" class="loding">
			<font color='#69C3FF'>数据加载中...</font>
		</div>
		<div id="hideDiv"></div>
  </body>
</html>
