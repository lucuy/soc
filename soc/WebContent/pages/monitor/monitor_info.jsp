<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
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
	width: 31%;
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
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui-1.10.2.custom.min.js"></script>

<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script>
<!-- 加载图表需要的js -->
<script type='text/javascript'
	src="${ctx}/scripts/jqchart/jquery.jqRangeSlider.min.js"></script>
<script type="text/javascript"
	src="${ctx}/scripts/jqchart/jquery.jqChart.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/paging.js"></script>
<!--[if IE]><script type="text/javascript" src="${ctx}/scripts/jqchart/excanvas.js"></script><![endif]-->
<script type='text/javascript' src="${ctx}/scripts/jquery.sortable.js"></script>

<script language="javascript">
    var array=[0,0,0,0,0,0,0,0,0];
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
	jQuery(document).ready(function() {
		$('#dialog-monitor').dialog({
			autoOpen : false,
			width : 600,
			height : 600,
			buttons : {
				"确定[Enter]" : function() {
					addMonitor();
				},
				"取消[Esc]" : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#monitorGroupId').val('${monitorGroup.monitorGroupId}');
		var temps = '${jsonArray}';
	    temp = eval("(" + temps + ")");
		//显示页面内的图表个数
		//changechart(temp.length-1);
		for ( var i = 0; i < temp.length; i++) {
		    var tempdiv=temp[i].station;
		    //记录该位置的图表id
		    $('#monitor'+tempdiv).val(temp[i].monitorDataUrl);
			$('#chart_'+tempdiv).val(i);
			//$('#li_'+tempdiv).val(temp[i].monitorId);
			$('#li_'+tempdiv).attr('name',temp[i].monitorId);
			
			$('#chart_'+tempdiv).attr("style","display:block");
		    array[tempdiv] = 1;

			initChart1(temp[i], temp[i].object, tempdiv);

		}
		
		$('#demo').sortable();
		 setTimeout("drawChart(temp)","100"); 
	});
	
	
	function drawChart(temp)
	{
	   for(var j=0;j<temp.length;j++)
		{ 
		    var tempdiv=temp[j].station;
		    updateMonitor("monitor"+tempdiv,tempdiv);
		}
	   
	}
	
	function initChart(object, data, div) {
       
		var reExtraSpace = /^\s*(.*?)\s+$/;
		var name = (object.monitorName).replace(reExtraSpace, "$1");
		
		var chartType = (object.chart.chartType).replace(reExtraSpace, "$1");
		var legend = (object.chart.chartLegendLocation).replace(reExtraSpace,
				"$1");
		var legendtitle = (object.relChartLegendTitle).replace(reExtraSpace,
				"$1");

		$('#monitorDiv_' + div).jqChart({
			background : 'white',
			legend : {
				location : legend
				//title : '图例'
			},
			border : {
				strokeStyle : 'white'
			},		
			animation : {
				duration : 1
			},
			title : {
				text : name,
				font :'14px sans-serif'
			},
			series : [ {
				type : chartType,
				title : legendtitle,
				font : '22 sans-serif',
				data : data,
				fillStyle : fillStyle1
				/* labels : {
					font : '11px sans-serif',
					fillStyle : '#6600FF',
					valueType : 'dateValue'
				} */
			} ]
		});
	}
	function initChart1(object, data, div)
	{
	    var reExtraSpace = /^\s*(.*?)\s+$/;
		var name = (object.monitorName).replace(reExtraSpace, "$1");
		
	//	var chartType = (object.chart.chartType).replace(reExtraSpace, "$1");
		var legend = (object.chart.chartLegendLocation).replace(reExtraSpace,
				"$1");
		var legendtitle = (object.relChartLegendTitle).replace(reExtraSpace,
				"$1");

		$('#monitorDiv_' + div).jqChart({
			background : 'white',
			legend : {
				location : legend
				//title : '图例'
			},
			border : {
				strokeStyle : 'white'
			},		
			animation : {
				duration : 1
			},
			title : {
				text : name,
				font :'14px sans-serif'
			},
			axes : [ {
				type : 'linear',
				location : 'left',
				extendRangeToOrigin : true
			} ],
			series : [ {
				type : "column",
				title : legendtitle,
				font : '22 sans-serif',
				data : data,
				fillStyle : fillStyle1
			} ]
		});
	  
	
	}
	
	//加载选择图表方法
	function showMonitorDialog() {
	    
	    var keyword="";
	    
	    resourceJson("${ctx}/monitor/queryMonitorAjax.action?startIndex="+encodeURI(encodeURI(0,"utf-8"))+
							"&keyword=" + encodeURI(encodeURI(keyword,"utf-8"))+"&t="+new Date(),0);					
	    
		/* $("input[name='monitorId'][type='radio']").each(function() {
			$(this).attr("checked", false);
		}); */
		
		$("#dialog-monitor").dialog("open");
	}
	
	function resourceJson(url,num)
	{
	     
	     var htmlStr = "";
	     
	     $.getJSON(url,function(result){
	     
	     $("#dlg-table-monitor tr:not(:first)").remove();
	     
	     $.each(result,function (i,item){
	    			var rowNum = $("#dlg-table-monitor tr").length-1;	
	    			if(item.monitorId && item.monitorId!='undefined'){
	    				htmlStr="<tr>";
	    				htmlStr +="<td class='biaocm'><input type='radio' name='monitorId' id='monitor_"+item.monitorId+"' value="+item.monitorId+">";
	    				htmlStr +="</td>";
					    htmlStr +="<td class='td_t'>";
					    htmlStr +=""+item.monitorName+"";
					    htmlStr +="</td>";
					    htmlStr +="</tr>";	
		            	$(htmlStr).insertAfter($("#dlg-table-monitor tr:eq("+rowNum +")"));	
	    			}
	    			if(item.currentPage){
                        paging_page("dlg-table-monitor tr:eq("+rowNum+")","${ctx}/monitor/queryMonitorAjax.action",item,"dlg-keyword-resource-id","resourceJson",2);
                    } 
	    		});
	    		
	     });
	
	}
	//选择完成后做的处理
	function addMonitor() {
		$("input[name='monitorId'][type='radio']").each(function() {
			if ($(this).attr("checked") == "checked") {
			    var monitorId1 =$(this).val();
			    //alert(monitorId1);
				var station =findZero();	
			if(station!=10)
		    {
		   /**
				$.ajax({
			      type : "POST",
			      url : "${ctx}/monitor/addOneMonitor.action",
			      data : "&monitorId=" + monitorId1,
			      dataType : 'java',
			      success : function(msg) {
			    	  alert(msg);
			      var temp = eval("("+msg+")");
                  //alert(temp[0]);
                  $('#chart_'+station).attr("style","display:block");
              
                       
                  initChart1(temp[0],temp[0].object,station);
                  $('#monitor'+station).val(temp[0].monitorDataUrl); 
                  
                  $('#flag'+station).val(temp[0].monitorId);
                  $('#li_'+station).attr('name',temp[0].monitorId);
                  //获得数据
                  //setTimeout("updateMonitor2(temp[0],'monitorid_'+station,station)","100");
                  updateMonitor2(temp[0],"monitor"+station,station) ;           
			    }
		     });  */
		     
		     $.post("${ctx}/monitor/addOneMonitor.action",{"monitorId":monitorId1},function(msg){
		    	 
				      var temp = eval("("+msg+")");
		              //alert(temp[0]);
		              $('#chart_'+station).attr("style","display:block");
		          
		                   
		              initChart1(temp[0],temp[0].object,station);
		              $('#monitor'+station).val(temp[0].monitorDataUrl); 
		              
		              $('#flag'+station).val(temp[0].monitorId);
		              $('#li_'+station).attr('name',temp[0].monitorId);
		              //获得数据
		              //setTimeout("updateMonitor2(temp[0],'monitorid_'+station,station)","100");
		              updateMonitor2(temp[0],"monitor"+station,station) ;
		    	 
		    	 
		     });
		     
		    array[station]  = 1;    	
		 }
		 else
		 {
		   alert("图表已满,请删除别的图表后再添加");
	     }
	    }
		});
	   $("#dialog-monitor").dialog("close");
	}
	function deleteMonitor(js,chart_id)
	{    
	   $('#chart_'+chart_id).attr("style","display:none");
	   array[chart_id] =  0;    
	   $('#li_'+chart_id).removeAttr('name');
	}
	
	function findZero()
	{
		
	    //alert(array.length);
	    for(var i =0;i<array.length;i++)
	    {
	        if(array[i]==0)
	        {
	            return i;
	        }
	    }
	    
	    return 10;
	}
	//保存页面布局
	function save()
	{    
		var monitorIds = "" ; 
	    /**
	    for(var i=0;i<array.length;i++)
	    { 
	      
	       if(array[i]==0)
	       {
	          temp= temp +"0,";
	       }
	       //if(array[i]==1)
	     //  {
	       else
	       {
	           //$('#monitorIds').val( $('#monitorIds').val + $('#monitor'+i).val()+",");
	           temp= temp + $('#flag'+i).val()+",";
	     //  }
	       }
	    }*/
	    $('#demo').children('li').each(function(){
	    	if(undefined == $(this).attr('name')){
				monitorIds = monitorIds + "0" + "," ;
			}else{
				monitorIds = monitorIds + $(this).attr('name')　+  ",";
			}
	    });
	    		
	    $('#monitorIds').val(monitorIds);
	    
	    //alert($('#monitorIds').val());
	    $('#monitorForm').submit();
	}
	//更新数据
	function updateMonitor(monitorId,station) {
	   
		var monitorId1 = $("#" + monitorId).val();
		var chartId=$("#chart_"+station).val();
		//alert(chartId);
		//alert(monitorId1);
		$.ajax({
			type : "POST",
			url : "${ctx}/monitor/queryOneMonitor.action",
			data : monitorId1,
			async : false,
			dataType : 'json',
			success : function(msg) {
			   <%-- if(temp[chartId].chart.chartType=='pie')
			    {
				  initChart(temp[chartId], msg, station);
				}
				else
				{
				  initChart1(temp[chartId], msg, station);
				} --%>
				initChart1(temp[chartId], msg, station);
			}
		});
	
		}
    function updateMonitor2(temp,monitorId,station)
	{
	     var monitorId1 = $("#" + monitorId).val();
		$.ajax({
			type : "POST",
			url : "${ctx}/monitor/queryOneMonitor.action",
			data : monitorId1,
			dataType : 'json',
			success : function(msg) {
			 <%--  if(temp.chart.chartType=='pie')
			   {
				initChart(temp, msg, station);
			   }
			   else
			   {
			      initChart1(temp, msg, station);
			   }  --%>
			   initChart1(temp, msg, station);
			}
		});
	}
    
	function openMakeChart(){
		$('#dialog-makeChart').dialog("open");
	}
</script>
</head>

<body style="margin-top:2px;margin-left: 4px;">
	<div class="framDiv" style="width:99%">
		<s:form action="saveMonitor.action" namespace="/monitor" method="post"
			theme="simple" id="monitorForm" name="monitorForm">
			<s:hidden name="monitorIds" id="monitorIds"></s:hidden>
			<s:hidden name="monitorGroupId" id="monitorGroupId" />
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<td class="r2titler"><b>监控<c:if
								test="${monitorGroup!=null}">>>${monitorGroup.monitorGroupName}>>选择图表</c:if>
					</b> <span style="float: right; padding-right: 10px;"> 
					<input class="btnstyle" type="button" value="选择图表"
							onclick="showMonitorDialog();" /> </span></td>
				</tr>
			</table>
			<section>
	         <ul id="demo" class="sortable grid">
	         <!-- 第0个图表 -->
	         <li id="li_0">
	            <s:hidden name="monitor0" id="monitor0" /> 
				<s:hidden name="flag0" id="flag0" />
	            <div id="chart_0" style="width:100%; height:100%;display:none;">
		               <div id="refresh_0" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">
						
						                        <span style="float:right;margin-right:3px;"><img
												class="hand" title="删除"
												src="${ctx}/images/monitor_delete.png"
												onclick="deleteMonitor('monitorid_0',0);" /> </span>
						</div>
					    <div id="monitorDiv_0" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
			
			 <!-- 第1个图表 -->
	         <li  id="li_1">
	            <s:hidden name="monitor1" id="monitor1" /> 
				<s:hidden name="flag1" id="flag1" />
	            <div id="chart_1" style="width:100%; height:100%;display:none;">
		               <div id="refresh_1" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">

							<span style="float:right;margin-right:3px;"><img
								class="hand" title="删除" src="${ctx}/images/monitor_delete.png"
								onclick="deleteMonitor('monitorid_1',1);" /> </span>
						</div>
					    <div id="monitorDiv_1" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
			
			<!-- 第2个图表 -->
	         <li  id="li_2">
	            <s:hidden name="monitor2" id="monitor2" /> 
				<s:hidden name="flag2" id="flag2" />
	            <div id="chart_2" style="width:100%; height:100%;display:none;">
		               <div id="refresh_2" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">

							<span style="float:right;margin-right:3px;"><img
								class="hand" title="删除" src="${ctx}/images/monitor_delete.png"
								onclick="deleteMonitor('monitorid_2',2);" /> </span>
						</div>
					    <div id="monitorDiv_2" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
			
			  <!-- 第3个图表 -->
	         <li  id="li_3">
	            <s:hidden name="monitor3" id="monitor3" /> 
				<s:hidden name="flag3" id="flag3" />
	            <div id="chart_3" style="width:100%; height:100%;display:none;">
		               <div id="refresh_3" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">
						
						                        <span style="float:right;margin-right:3px;"><img
												class="hand" title="删除"
												src="${ctx}/images/monitor_delete.png"
												onclick="deleteMonitor('monitorid_3',3);" /> </span>
						</div>
					    <div id="monitorDiv_3" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
		    
		     <!-- 第4个图表 -->
	         <li  id="li_4">
	            <s:hidden name="monitor4" id="monitor4" /> 
				<s:hidden name="flag4" id="flag4" />
	            <div id="chart_4" style="width:100%; height:100%;display:none;">
		               <div id="refresh_4" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">
						
						                        <span style="float:right;margin-right:3px;"><img
												class="hand" title="删除"
												src="${ctx}/images/monitor_delete.png"
												onclick="deleteMonitor('monitorid_4',4);" /> </span>
						</div>
					    <div id="monitorDiv_4" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
		     <!-- 第5个图表 -->
	         <li  id="li_5"> 
	            <s:hidden name="monitor5" id="monitor5" /> 
				<s:hidden name="flag5" id="flag5" />
	            <div id="chart_5" style="width:100%; height:100%;display:none;">
		               <div id="refresh_5" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">
						
						                        <span style="float:right;margin-right:3px;"><img
												class="hand" title="删除"
												src="${ctx}/images/monitor_delete.png"
												onclick="deleteMonitor('monitorid_5',5);" /> </span>
						</div>
					    <div id="monitorDiv_5" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
		      <!-- 第6个图表 -->
	         <li  id="li_6">
	            <s:hidden name="monitor6" id="monitor6" /> 
				<s:hidden name="flag6" id="flag6" />
	            <div id="chart_6" style="width:100%; height:100%;display:none;">
		               <div id="refresh_6" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">
						
						                        <span style="float:right;margin-right:3px;"><img
												class="hand" title="删除"
												src="${ctx}/images/monitor_delete.png"
												onclick="deleteMonitor('monitorid_6',6);" /> </span>
						</div>
					    <div id="monitorDiv_6" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
		     <!-- 第7个图表 -->
	         <li  id="li_7">
	            <s:hidden name="monitor7" id="monitor7" /> 
				<s:hidden name="flag7" id="flag7" />
	            <div id="chart_7" style="width:100%; height:100%;display:none;">
		               <div id="refresh_7" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">
						
						                        <span style="float:right;margin-right:3px;"><img
												class="hand" title="删除"
												src="${ctx}/images/monitor_delete.png"
												onclick="deleteMonitor('monitorid_7',7);" /> </span>
						</div>
					    <div id="monitorDiv_7" style="height:280px; width:98%;margin:3px"></div></div>
		    </li>
		     <!-- 第8个图表 -->
	         <li  id="li_8">
	            <s:hidden name="monitor8" id="monitor8" /> 
				<s:hidden name="flag8" id="flag8" />
	            <div id="chart_8" style="width:100%; height:100%;display:none;">
		               <div id="refresh_8" style="display:block;height:20px;text-align: left;line-height: 20px;background-color: #A4D3EE;"
						onclick="event.cancelBubble=true;">
						
						                        <span style="float:right;margin-right:3px;"><img
												class="hand" title="删除"
												src="${ctx}/images/monitor_delete.png"
												onclick="deleteMonitor('monitorid_8',8);" /> </span>
						</div>
					    <div id="monitorDiv_8" style="height:280px; width:98%;margin:3px"></div></div>
		      </li>
	         </ul>
			 </section>
			 </s:form>
			</div>	
			
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		style="margin-right: 4px;">
		<!-- 空行 -->
		<tr>
			<td height="8"></td>
		</tr>

		<tr>
			<td align="right" style="padding-right:4px"><input type="button"
				class="btnyh" style="font-family: 微软雅黑; font-size: 12px;"
				id="btnSaveCon" value="保存设置" onclick="save();" /></td>
		</tr>
	</table>
<!-- 显示监控表的dialog -->
	<div id="dialog-monitor" title="图表选择">
	<s:hidden id="dlg-keyword-resource-id" value=""/>
		<table id="dlg-table-monitor" width='96%' cellspacing='1'
			cellpadding='0' border='0' align='center' class="tab2" style="overflow:auto">		
         <tr>
            <td class="biaocm">&nbsp;</td>
            <td width="80%" class="td_t">图表样式</td>
         </tr>
		</table>
	</div>
</body>
</html>
