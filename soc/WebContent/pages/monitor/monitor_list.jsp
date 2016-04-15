<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>


<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet" type="text/css"> --%>
<!-- 加载图表显示需要的css -->
<link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqChart.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
header,section {
	display: block;
}

body {
	font-family: 'Droid Serif';
}

h1,h2 {
	text-align: center;
	font-weight: normal;
}

#features {
	margin: auto;
	width: 460px;
	font-size: 0.9em;
}

.connected,.sortable,.exclude,.handles {
	margin: 0;
	padding: 0;
	width: 100%;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.sortable.grid {
	overflow: hidden;
}

.connected li,.sortable li,.exclude li,.handles li {
	list-style: none;
	border: 1px solid #CCC;
	font-family: "Tahoma";
	color: #1C94C4;
	margin: 5px;
	padding: 5px;
	height: 22px;
}

.handles span {
	cursor: move;
}

li.disabled {
	opacity: 0.5;
}

.sortable.grid li {
	line-height: 14px;
	float: left;
	width: 31.2%;
	height: 300px;
	text-align: center;
}

li.highlight {
	background: #FEE25F;
}

#connected {
	width: 440px;
	overflow: hidden;
	margin: auto;
}

.connected {
	float: left;
	width: 200px;
}

.connected.no2 {
	float: right;
}

li.sortable-placeholder {
	border: 1px dashed #CCC;
	background: none;
}

.hand{cursor:pointer;margin-bottom: 8px;}

</style>

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.sortable.js"></script>


<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<!-- 加载图表需要的js -->
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>
<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->
<script lang="javascript" type="text/javascript">
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
			color : '#EBF6FC'
		} ]
	};
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
	var temp;
	$(document).ready(function() {
		$('#monitorGroupId').val('${monitorGroupId}');
		var temps = '${jsonArray}';
		temp = eval("(" + temps + ")");
		//changeCard(temp.length - 1);
		//stationArr = ;
		for ( var i = 0; i < temp.length; i++) {
			//alert(temp[i].station);
			var tempdiv = temp[i].station;
			//记录图表的url;
			$('#monitorid_' + tempdiv).val(temp[i].monitorDataUrl);

			//alert($('#monitorid_'+tempdiv).val());
            //alert("第几个图表位置 ? == " + tempdiv + " 监控图表的ID ? == " +temp[i].monitorId ) ; 
            //将图表的位置赋值到li的Name属性中
            $('#li_' + tempdiv).attr("name",temp[i].monitorId);
            
			//记录图表的样式
			$('#chart_' + tempdiv).val(i);

			$('#chanel_' + tempdiv).attr("style", "display:block");

			//alert($('#chart_'+tempdiv).val());
			//alert(temp[i].chart.chartType);

			//if (temp[i].chart.chartType == 'pie') {

			//	initChart(temp[i], temp[i].object, tempdiv);
			//} else {
			initChart1(temp[i], temp[i].object, tempdiv);

			//}
		}

		setTimeout("drawChart(temp)", "100");
		$('#demo').sortable().bind('sortupdate',function(){
			saveSort();
		});
		
	
		
		
	});

	//保存页面排序的位置
	function saveSort()
	{
		var monitorIds = "" ; 
		$('#demo').children('li').each(function(){
			//undefined
			if(undefined == $(this).attr('name')){
				monitorIds = monitorIds + "0" + "," ;
			}else{
				monitorIds = monitorIds + $(this).attr('name')　+  ",";
			}
		});
		//alert({"monitorIds":monitorIds,"groupId":groupId,"saveSortFlag":1});
		$.post("${ctx}/monitor/saveMonitor.action",{"monitorIds":monitorIds,"monitorGroupId":$('#monitorGroupId').val(),"saveSortFlag":1},function(){});
	}
	
	
	var tempdiv;
	function drawChart(temp) {
		for ( var j = 0; j < temp.length; j++) {
			tempdiv = temp[j].station;
			updateMonitor("monitorid_" + tempdiv, tempdiv);
		}

	}

	function initChart(object, data, div) {

		var reExtraSpace = /^\s*(.*?)\s+$/;
		var name = (object.monitorName).replace(reExtraSpace, "$1");
		//var chartType = (object.chart.chartType).replace(reExtraSpace, "$1");
		var legend = (object.chart.chartLegendLocation).replace(reExtraSpace,
				"$1");
		var legendtitle = (object.relChartLegendTitle).replace(reExtraSpace,
				"$1");
		
		$('#monitorDiv_' + div).jqChart({
			background : 'white',
			legend : {
				
				location : legend,
				//title : '图例',
				font : '10px sans-serif'
			},
			border : {

				strokeStyle : 'white'
			},
			title : {
				text : name,
				font : '14px sans-serif'
			},
			/* axes : [ /* {
				location : 'bottom',
				labels : {
					valueType : 'dateValue',
					angle : 30
				}
			}, 
			 {
				//type : 'linear',
				location : 'left',
				extendRangeToOrigin : true
			} 

			], */
			series : [ {
				type : "pie",
				title : legendtitle,
				font : '10px sans-serif',
				data : data,
				fillStyle : fillStyle1,

			/* labels : {
				font : '11px sans-serif',
				fillStyle : '#6600FF',
				valueType : 'dateValue'
			}  */
			} ]
		});
	}
	function initChart1(object, data, div) {
		var reExtraSpace = /^\s*(.*?)\s+$/;
		var name = (object.monitorName).replace(reExtraSpace, "$1");
		//var chartType = (object.chart.chartType).replace(reExtraSpace, "$1");
		var legend = (object.chart.chartLegendLocation).replace(reExtraSpace,
				"$1");
		var legendtitle = (object.relChartLegendTitle).replace(reExtraSpace,
				"$1");

		$('#monitorDiv_' + div).jqChart({
			background : 'white',
			legend : {
				location : legend,
				//title : '图例',
				font : '10px sans-serif'
			},
			border : {
				strokeStyle : 'white'
			},
		    tooltips : {
            	disabled : false,
            	//highlightingStrokeStyle: 'rgba(11, 1, 1, 0.5)'
            	
            },
			title : {
				text : name,
				font : '14px sans-serif'
			},
			axes : [ {
				type : 'linear',
				location : 'left',
				extendRangeToOrigin : true
			} ],
			series : [ {
				type : "column",
				title : legendtitle,
				font : '10px sans-serif',
				data : data,
				fillStyle : fillStyle1,
			} ]
		});

	}
	function updateMonitor(monitorId, station, typeChart) {
		var monitorId1 = $("#" + monitorId).val();
		var chartId = $("#chart_" + station).val();
		//alert(monitorId1);
		//alert(chartId);
		$.ajax({
			type : "POST",
			url : "${ctx}/monitor/queryOneMonitor.action",
			data : monitorId1,
			async : false,
			dataType : 'json',
			success : function(msg) {
				var val = $("#" + typeChart).val();
				if (val != undefined && val != "") {
					if (val == '饼状图') {
						initChart(temp[chartId], msg, station);
					} else {
						initChart1(temp[chartId], msg, station);
					}
				} else {
					initChart1(temp[chartId], msg, station);
				}
			}
		});

	}
	
	
</script>
</head>

<body>

	<s:hidden name="monitorGroupId" id="monitorGroupId"></s:hidden>
<table width="99%" border="0" cellspacing="0" cellpadding="0"
		style="margin-left:4px; margin-top:2px">
		<tr>
			<td>
				<div class="caozuobox">
					<!-- toolbar area -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr height="30">
							<td width="15%" align="left"
								style="padding-top:1px; bgcolor:#bfddf7;padding-left:10px;">监控管理<c:if
									test="${monitorGroup!=null}">>>${monitorGroup.monitorGroupName}</c:if>
							</td>
							<td width="33%"></td>
							<td width="5%" align="center"><input type="button"
								style="padding-top:1px;padding-left:1px;" value="编辑"
								class="btnstyle"
								onclick="location.href='${ctx}/monitor/editMonitor.action?monitorGroupId=${monitorGroupId}'" />
							</td>
						</tr>
					</table>
					<!-- toolbar area -->
				</div></td>
		</tr>
	</table>


	<section>
	<ul id="demo" class="sortable grid">
		<li id="li_0"><s:hidden name="monitorid_0" id="monitorid_0"></s:hidden> 
		<s:hidden name="chart_0" id="chart_0"></s:hidden>
			<div id="chanel_0" style="width:100%; height:100%;display:none;">
				<div id="refresh_0" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_0',0,'monitorid_0_select' );" />
					</span> <span> <select id="monitorid_0_select"
						onchange="updateMonitor('monitorid_0',0,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_0" style="height:280px; width:98%;margin:3px"></div>
			</div></li>
		<li id="li_1">
		    <s:hidden name="monitorid_1" id="monitorid_1"></s:hidden> 
		    <s:hidden name="chart_1" id="chart_1"></s:hidden>
             <div id="chanel_1" style="width:100%; height:100%;display:none;">
				<div id="refresh_1" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_1',1,'monitorid_1_select' );" />
					</span> <span> <select id="monitorid_1_select"
						onchange="updateMonitor('monitorid_1',1,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_1" style="height:280px; width:98%;margin:3px"></div>
        </li>
		<li id="li_2">
		     
		    <s:hidden name="monitorid_2" id="monitorid_2"></s:hidden> 
		    <s:hidden name="chart_2" id="chart_2"></s:hidden>
		      <div id="chanel_2" style="width:100%; height:100%;display:none;">
				<div id="refresh_2" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_2',2,'monitorid_2_select' );" />
					</span> <span> <select id="monitorid_2_select"
						onchange="updateMonitor('monitorid_2',2,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_2" style="height:280px; width:98%;margin:3px"></div> 
		
		
		</li>
		<li id="li_3">
            <s:hidden name="monitorid_3" id="monitorid_3"></s:hidden> 
		    <s:hidden name="chart_3" id="chart_3"></s:hidden>
             <div id="chanel_3" style="width:100%; height:100%;display:none;">
				<div id="refresh_3" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_3',3,'monitorid_3_select' );" />
					</span> <span> <select id="monitorid_3_select"
						onchange="updateMonitor('monitorid_3',3,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_3" style="height:280px; width:98%;margin:3px"></div>
            
        </li>
		<li id="li_4">
		 
		     <s:hidden name="monitorid_4" id="monitorid_4"></s:hidden> 
		    <s:hidden name="chart_4" id="chart_4"></s:hidden>
             <div id="chanel_4" style="width:100%; height:100%;display:none;">
				<div id="refresh_4" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_4',4,'monitorid_4_select' );" />
					</span> <span> <select id="monitorid_4_select"
						onchange="updateMonitor('monitorid_4',4,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_4" style="height:280px; width:98%;margin:3px"></div>
		 
		</li>
		<li id="li_5">
              
              <s:hidden name="monitorid_5" id="monitorid_5"></s:hidden> 
		    <s:hidden name="chart_5" id="chart_5"></s:hidden>
             <div id="chanel_5" style="width:100%; height:100%;display:none;">
				<div id="refresh_5" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_5',5,'monitorid_5_select' );" />
					</span> <span> <select id="monitorid_5_select"
						onchange="updateMonitor('monitorid_5',5,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_5" style="height:280px; width:98%;margin:3px"></div>
              
       </li>
       
		<li id="li_6">
 
                 <s:hidden name="monitorid_6" id="monitorid_6"></s:hidden> 
		    <s:hidden name="chart_6" id="chart_6"></s:hidden>
             <div id="chanel_6" style="width:100%; height:100%;display:none;">
				<div id="refresh_6" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_6',6,'monitorid_6_select' );" />
					</span> <span> <select id="monitorid_6_select"
						onchange="updateMonitor('monitorid_6',6,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_6" style="height:280px; width:98%;margin:3px"></div>

        </li>
		<li id="li_7">
		
		          <s:hidden name="monitorid_7" id="monitorid_7"></s:hidden> 
		    <s:hidden name="chart_7" id="chart_7"></s:hidden>
             <div id="chanel_7" style="width:100%; height:100%;display:none;">
				<div id="refresh_7" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_7',7,'monitorid_7_select' );" />
					</span> <span> <select id="monitorid_7_select"
						onchange="updateMonitor('monitorid_7',7,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_7" style="height:280px; width:98%;margin:3px"></div>
		    
		</li>
		<li id="li_8">
		
		        <s:hidden name="monitorid_8" id="monitorid_8"></s:hidden> 
		    <s:hidden name="chart_8" id="chart_8"></s:hidden>
             <div id="chanel_8" style="width:100%; height:100%;display:none;">
				<div id="refresh_8" style="display:block;height:20px;text-align: left;line-height: 25px;background-color: #A4D3EE;"
					onclick="event.cancelBubble=true;">
					<span style="float:right;margin-right:3px;"><img
						class="hand" title="刷新" src="${ctx}/images/refresh.png"
						onclick="updateMonitor('monitorid_8',8,'monitorid_8_select' );" />
					</span> <span> <select id="monitorid_8_select"
						onchange="updateMonitor('monitorid_8',8,this.id);"  class="hand">
							<option selected="selected" value="柱状图">柱状图</option>
							<option value="饼状图">饼状图</option>
					</select> </span>
				</div>
				<div id="monitorDiv_8" style="height:280px; width:98%;margin:3px"></div>
		 
		</li>
	</ul>
	</section>

	
</body>
</html>
