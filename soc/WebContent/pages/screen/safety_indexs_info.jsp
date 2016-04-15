<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ page contentType="text/html; charset=utf-8" import="java.util.*"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<title>安全管理平台综合监控123</title>
<link href="${ctx}/images/favicon.ico" rel="shortcut icon" />
<link href="${ctx}/images/favicon.ico" rel="Bookmark" />
    
<link href="${ctx}/styles/comprehensivemonitoring.css" rel="stylesheet"
	type="text/css">    
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">    
<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />
	
<style type="text/css">

#back a:link{ color:#FFFFFF; text-decoration:none;}
#back  a:visited{text-decoration:none; color:#FFFFFF;}
#back   a:hover{ color:#faec06; text-decoration:underline;}
#back  a:active{ color:#FFFFFF;}


</style>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<!-- 加载图表需要的js -->
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>
	
<script type='text/javascript' src="${ctx}/scripts/paging1.js"></script>

<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->
	
</head>
<style type="text/css">

.box {
	margin: 0px 0px 4px 0px;
	border: 1px solid #c1ddf1;
	background: url(/soc/images/rightDh.jpg) repeat-x 0 0;
	height: 33px;
	line-height: 30px;
	text-align: center;
    }
   
.sbox {
	clear: both;
	margin-bottom: 10px;
	overflow: hidden;
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
            

.biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
	font-size: 12px;
       }

.eventslist tr td {
	line-height: 28px;
	text-align: center;
    }
.eventslist .biaoti {
	background: url("/soc/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
	height: 28px;
	color: black;
	font-weight: bold;
	text-align: center;
    }
    
.back {
	background: #FFFFFF;
      }
.conn {
	padding: 0px 0px 0px 0px;
	margin: 2px 0px 0px 0px;
	position: absolute;
	z-index: -1;
	width: 99%;
     }      
.refresh {
	text-align: center;
	line-height: 28px;
        }     
.hideDiv {
	padding: 0px 0px 0px 0px;
	margin: 0px 0px 0px 0px;
	background-image: url("${ctx}/images/hide.png");
	background-position: 0% 0%;
	background-repeat: repeat;
	background-attachment: scroll;
	/*     width:100%;
    height:510px; */
	position: absolute;
	z-index: 1;
	filter: progid:DXImageTransform.Microsoft.Alpha(style=1, opacity=20,
		finishOpacity=20 );
}    

.dialog-enentsContent {
	overflow-y:scroll;
	font-size: 13px;
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
	
}

.rowLV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	border-right: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 250px;

}

.rowRT {
	border-bottom: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
	width: 100px;
	background: #F3F9FF;
}

.rowRV {
	border-bottom: 1px solid #D2E8FA;
	border-left: 1px solid #D2E8FA;
	padding: 5px 5px 5px 5px;
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

.loding {
	font-size: 14px;
	border: 3px solid #D2E8FA;
	position: absolute;
	z-index: 10;
	background: #FFFFFF;
	width: 200px;
	height: 40px;
	padding-top: 25px;
	text-align: center;
	maring: 0px auto;
	display: none;
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
	$(document).ready(function() {

        $('#dialog-enentsContent').dialog ({
			autoOpen : false,
		    height : 700,
			width : 700,
			modal:true
		});

		//加载数据
		 var eventNums = '${eventNumJson}';
		 eventNum = eval("(" + eventNums + ")");
		 initeventChart(eventNum);
		 
		
		initcolumnChart();
		initbarChart();
		//加载页面底部的事件列表
	    showeventList();
	    
	    initbarChart3();
		
			//选项卡
		$('#tabs-setting').tabs();
	});

	function initcolumnChart() {
		$('#chart_0').jqChart({
			background : background,
			legend : {
				visible : false
			},
			border : {
				strokeStyle : 'white'
			},
			axes : [ {
				location : 'bottom',
				type : 'category',
				categories : [ '严重', '一般', '不重要']
			} ],
			series : [ {
				type : 'column',
				font : '22 sans-serif',
				data : [ 20, 25, 14 ],
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

	function initeventChart(eventnum) {
		$('#chart_1').jqChart({
			background : background,
			legend : {
				visible : false
			},
			border : {
				strokeStyle : 'white'
			},
			series : [ {
				type : 'bar',
				font : '22 sans-serif',
				data :  eventnum,
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
	function initbarChart() {
		$('#chart_2').jqChart(
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
						data : [ [ 'United States', 65 ],
								[ 'United Kingdom', 58 ], [ 'Germany', 30 ],
								[ '中国', 40 ] ],
						fillStyle : fillStyle1,
						labels : {
							font : '11px sans-serif',
							valueType : 'dateValue'
						}
					} ]
				});
	}
	
		function initbarChart3() {
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
						data : [ [ 'United States', 65 ],
								[ 'United Kingdom', 58 ], [ 'Germany', 30 ],
								[ '中国', 40 ] ],
						fillStyle : fillStyle1,
						labels : {
							font : '11px sans-serif',
							valueType : 'dateValue'
						}
					} ]
				});
				
	    }
	
	
	function showeventList() {
		//alert("23w");
		var keyword = "";
		getEventListJson("${ctx}/events/queryEventScreen.action?startIndex="
				+ encodeURI(0, "utf-8") + "&keyword="
				+ encodeURI(encodeURI(keyword, "utf-8"))
				+ "&queryEventsType=0&t=" + new Date(), 0);
	}

	function getEventListJson(url, num) {
		var htmlStr;
		$
				.getJSON(
						url,
						function(result) {

							$("#bottomtable tr:not(:first)").remove();

							$("#test12").remove();
							$
									.each(
											result,
											function(i, item) {

												var rowNum = $("#bottomtable tr").length - 1;

												if (item.eventsId
														&& item.eventsId != 'undefined') {
													htmlStr = "<tr class='back' onmouseover='show(this);' onmouseout='hide(this);' ondblclick='dblclick("
															+ item.identification
															+ ");'>";
													htmlStr += "<td valign='middle' align='center' width='10%'>"
															+ item.priority;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='20%'>"
															+ item.name;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='15%'>"
															+ item.devName;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='15%'>"
															+ item.devproduct;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='10%'>"
															+ item.aggregatedCount;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='20%'>"
															+ item.receptTimes;
													htmlStr += "</td>";
													htmlStr += "<td valign='middle' align='center' width='10%'>待确认";
													htmlStr += "</td>";
													htmlStr += "</tr>";
													$(htmlStr)
															.insertAfter(
																	$("#bottomtable tr:eq("
																			+ rowNum
																			+ ")"));

													// $(htmlStr).insertAfter($("#bottomtable"));

												}
												if (item.currentPage) {
													paging_page(
															"footer",
															"${ctx}/events/queryEventScreen.action",
															item,
															"dlg-keyword-resource-id",
															"getEventListJson",
															1);
												}
											});

							$("#bottomtable tr:first").remove();

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
	
	function geteventstatistics() {
		 $.ajax({
	      type: "post",
	      dataType: "json",
	      url:"${ctx}/indexscreen/queryEventStatistics.action",
	      success: function (data){      
	        initeventChart(data);      	       
	      }
	   });
		
 	}
 	

 	//打开事件统计定时器
	function openeventtimer() {
	   setInterval("geteventstatistics();",1 * 60 * 100);
	}
	
	
	function dblclick(obj){
	
	
/* 	    var docHe =  ($(document).height()/2)-40;
        var docWi =  ($(document).width()/2)-200;
        alert(docHe+":"+docWi);
        $("#hideDiv").addClass("hideDiv");
        $("#hideDiv").css({
            width: $(document).width(), 
            height: $(document).height()
        });
        $("#loding").toggle("slow");
        $("#loding").css({top:docHe,left:docWi}); 
        
         */
        
	
	
	 $('#dialog-enentsContent').dialog('open');
	
	}
	
</script>
<body style="margin-top: 2px">
	<div style="width: 100%; border:1px solid #dcdcdc;" >
		<!--标题 -->
		<div id="safetytitle">
			<font color="#000000"
				style="line-height:50px; margin-left:15px;font-size:20px"><strong>安全管理平台综合监控</strong>
			</font>
		</div>

		<!--tab 页切换-->
		<div id="tabs-setting" style="width: 100%; margin: 2px auto">
			<ul style="background: #D6E8CE">
				<li><a href="#wholerisk" style="cursor: pointer;"
					onclick="flush_network();">全局风险</a>
				</li>
				<li><a href="#eventstatistics" style="cursor: pointer;"
					onclick="openeventtimer();">事件统计</a>
				</li>
				<li><a href="#propertyspread" style="cussor: pointer"
					onclick="flushrecover;">资产分布</a>
				</li>

			</ul>

			<!-- 第一个tab页-->
			<div id="wholerisk">
				<div style="width:100%; height:280px; border: 1px solid #D2E8FA ">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td>
								<table width="100%" cellSpacing="1" cellpadding="6">
									<tr>
										<td class="biaoti" width="46%">内存占有率</td>
										<td class="biaoti" width="20%">事件类型|事件数量</td>
										<td class="biaoti" width="33%">磁盘使用率</td>
										
									</tr>
									<tr>
										<td>
											<div id="chart_0"
												style="width:100%;height:250px;display:block"></div>
										</td>
										<td>
											<div style="width:100%;height:250px;display:block;">
												<!-- table左侧区域 开始 -->

												<!-- table左侧区域结束 -->
											</div>
										</td>
										<td>
											<div id="chart_2"
												style="width:100%;height:250px;display:block;"></div></td>

									</tr>
								</table></td>
						</tr>
					</table>

				</div>


			</div>


			<!-- 第二个tab页：事件统计 -->
			<div id="eventstatistics" >

				<div style="width:100%; height:280px; border: 1px solid #D2E8FA ">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td>
								<table width="100%" cellSpacing="1" cellpadding="6">
									<tr>
										<td class="biaoti" width="46%">当天事件统计 Top 5</td>
										<td class="biaoti" width="54%">事件统计说明</td>
									</tr>
									<tr>
										<td>
											<div id="chart_1"
												style="width:100%;height:250px;display:block"></div></td>
										<td>
											<div style="width:100%;height:250px;display:block;">
												<!-- table左侧区域 开始 -->

												<table style="width:100%">
													<tr>
														<td style="width:100%;" valign="top">
															<div class="sbox">
																<div class="cont">
																	<!-- information area -->
																	<div id="dataList">
																		<table width="100%" border="0" cellspacing="1"
																			cellpadding="0" class="tab2">
																			<tr height="28">
																				<td width="25%" class="biaoti">业务系统</td>
																				<td width="25%" class="biaoti">资产数量</td>
																				<td width="25%" class="biaoti">业务系统</td>
																				<td width="25%" class="biaoti">资产数量</td>
																			</tr>

																		</table>
																	</div>
																</div>
															</div>
														</td>
													</tr>

												</table>

												<!-- table左侧区域结束 -->
											</div></td>

									</tr>
								</table>
							</td>
						</tr>
					</table>

				</div>

			</div>
			<!-- 第二个tab页：事件统计 结束-->

			<!-- 第三个tab页：资产分布 -->
			<div id="propertyspread">

				<div style="width:100%; height:280px; border: 1px solid #D2E8FA ">

					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td>
								<table width="100%" cellSpacing="1" cellpadding="6">
									<tr>
										<td class="biaoti" width="46%">资产统计 Top 5</td>
										<td class="biaoti" width="54%">资产统计说明</td>
									</tr>
									<tr>
										<td>
											<div id="chart_3"
												style="width:100%;height:250px;display:block"></div></td>
										<td>
											<div style="width:100%;height:250px;display:block;">

												<!-- table左侧区域 开始 -->

												<table style="width:100%">
													<tr>
														<td style="width:100%;" valign="top">
															<div class="sbox">
																<div class="cont">
																	<!-- information area -->
																	<div id="dataList">
																		<table width="100%" border="0" cellspacing="1"
																			cellpadding="0" class="tab2">
																			<tr height="28">
																				<td width="25%" class="biaoti">业务系统</td>
																				<td width="25%" class="biaoti">资产数量
																				<td>
																				<td width="25%" class="biaoti">业务系统</td>
																				<td width="25%" class="biaoti">资产数量</td>
																			</tr>

																		</table>
																	</div>
																</div>
															</div></td>
													</tr>

												</table>

												<!-- table左侧区域结束 -->

											</div></td>

									</tr>
								</table>
							</td>
						</tr>
					</table>


				</div>


			</div>


			<!--set-tab结束 -->
		</div>

            <div style="width: 100%; height: 100px ">

			<!-- 底部不变区域 -->
			<div id="bottom_date">
				<div style="margin-left: 1.5%; width: 100%; margin-right: 1%">
					<table width="100%" align="center" id="footer">
						<tr>
							<td colspan="8">
								<table width="98.6%" border="0" cellspacing="1" cellpadding="0">
									<tr height="28" id="collectionTR">
										<td width="10%" class="biaoti">事件级别</td>
										<td width="20%" class="biaoti">事件名称</td>
										<td width="15%" class="biaoti">设备名称</td>
										<td width="15%" class="biaoti">设备IP</td>
										<td width="10%" class="biaoti">数量</td>
										<td width="20%" class="biaoti">发生时间</td>
										<td width="10%" class="biaoti">处理情况</td>
									</tr>

								</table>
							</td>
						</tr>
						<tr>
							<td align="center" width="100%">
								<div style="overflow-y:scroll; width:100%; height: 180px"
									id="screentable2">
									<table width="100%" class="eventslist" cellspacing="1"
										cellpadding="0" id="bottomtable" style="font-size: 12px">
										<tr>
											<td width="10%">事件空</td>
											<td width="20%">事件空</td>
											<td width="15%">事件空</td>
											<td width="15%">事件空</td>
											<td width="10%">事件空</td>
											<td width="20%">事件空</td>
											<td width="10%">事件空</td>
										</tr>
									</table>

								</div>
							</td>

						</tr>

					</table>


				</div>
			</div>
			<!--底部区域数据结束  -->

		</div>

	</div>
	
     <div id="dialog-enentsContent" title="详细信息" class="dialog-enentsContent">
			<div style="margin:5px 0px;"></div>
			<div class="column">
			  <div class="title">
			      <span class="title_t"><img
						src="${ctx}/images/arrow_03.gif" id="img_1" class="img_a"
						onclick="action(1)">&nbsp;<span class="title_t_t">设备信息</span>
				  </span>
			  </div>
			  <div id="column_1">
					<ul class="display" style="list-style-type:none">
						<li style="font-size: 8px">
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">设备名称</td>
									<td class="rowLV" id="facilityName">&nbsp;</td>
									<td class="rowRT">ip地址</td>
									<td class="rowLV" id="ipAddress">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">设备厂商</td>
									<td class="rowLV" id="facilityFirm">&nbsp;</td>
									<td class="rowRT">设备型号</td>
									<td class="rowLV" id="facilityModel">&nbsp;</td>
								</tr>
							</table></li>
					</ul>
				</div>
			</div>
			
			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/arrow_03.gif" id="img_2" class="img_a"
						onclick="action(2)">&nbsp;<span class="title_t_t">时间信息</span>
					</span>
				</div>
				<div id="column_2">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">产生时间</td>
									<td class="rowLV" id="sendTime">&nbsp;</td>
									<td class="rowRT">接收时间</td>
									<td class="rowLV" id="occurTime">&nbsp;</td>
								</tr>
							</table></li>
					</ul>
				</div>
			</div>
			<div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/arrow_03.gif" id="img_3" class="img_a"
						onclick="action(3)">&nbsp;<span class="title_t_t">基本信息</span>
					</span>
				</div>
				<div id="column_3">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">等&nbsp;&nbsp;级</td>
									<td class="rowLV" id="priority">&nbsp;</td>
									<td class="rowRT">数量</td>
									<td class="rowLV" id="type">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件类型</td>
									<td colspan="3" class="rowLV" id="count">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件名称</td>
									<td colspan="3" class="rowLV" id="name">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件描述</td>
									<td colspan="3" class="rowLV" id="customs4">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">摘要</td>
									<td colspan="3" class="rowLV" id="digest">&nbsp;</td>
								</tr>
							</table></li>
					</ul>
				</div>
			</div>

			<div style="height: 10px;"></div>
                <div class="column">
                <div class="title">
					<span class="title_t"><img
						src="${ctx}/images/arrow_03.gif" id="img_4" class="img_a"
						onclick="action(4)">&nbsp;<span class="title_t_t">来源目标信息</span>
					</span>
				</div>
                <div id="column_4">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">源地址</td>
									<td class="rowLV" id="sourceAddress">&nbsp;</td>
									<td class="rowRT">源端口</td>
									<td class="rowLV" id="sourcePort">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">目标地址</td>
									<td class="rowLV" id="goalAddress">&nbsp;</td>
									<td class="rowRT">目标端口</td>
									<td class="rowLV" id="goalPort">&nbsp;</td>
								</tr>
							</table></li>
					</ul>
				</div>
                
                </div>
		    <div style="height: 10px;"></div>
			<div class="column">
				<div class="title">
					<span class="title_t"><img
						src="${ctx}/images/arrow_03.gif" id="img_5" class="img_a"
						onclick="action(5)">&nbsp;<span class="title_t_t">事件分类信息</span>
					</span>
				</div>
				<div id="column_6">
					<ul class="display" style="list-style-type:none">
						<li>
							<table cellpadding="0px" cellspacing="0px" width="100%">
								<tr>
									<td class="rowLT">事件对象分类</td>
									<td class="rowLV" id="category">&nbsp;</td>
									<td class="rowRT">事件设备分类</td>
									<td class="rowLV" id="devCategory">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件动作</td>
									<td class="rowLV" id="action">&nbsp;</td>
									<td class="rowRT">动作结果</td>
									<td class="rowLV" id="result">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件采集技术</td>
									<td colspan="3" class="rowLV" id="sensorName">&nbsp;</td>
								</tr>
								<tr>
									<td class="rowLT">事件特征</td>
									<td colspan="3" class="rowLV">&nbsp;</td>
								</tr>
							</table></li>
					</ul>
				</div>
			</div>
			</div>
	 
   <!-- 黑屏 -->
   <div id="loding" class="loding">
		<font color='#69C3FF'>数据加载中...</font>
	</div>
	
	<div id="hideDiv"></div>
	
</body>
</html>
