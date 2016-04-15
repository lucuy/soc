<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page contentType="text/html; charset=utf-8"
	import="com.soc.model.systemsetting.SysInfo,java.util.*"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<html>
<head>
<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
	<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine-en.js"></script> --%>
<%-- <script type='text/javascript'
	src="${ctx}/scripts/jquery.validationEngine.js"></script> --%>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/validationEngine.jquery.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/highcharts.js"></script>	
<script type='text/javascript'
	src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
if("${showCpuDataPic}" == '1'){
Highcharts.setOptions({ 
    global: { 
        useUTC: false 
    } 
});} 
var chart;
$(document).ready(function() {
	$('#dialog-extQuery').dialog({
		autoOpen : false,
		width : 450,
		height : 220,
		buttons : {
			"确认[Enter]" : function() {
				var temp=extQuery();
				if(temp==true)
				{
				$(this).dialog("close");
				}	
			},

			"取消[Esc]" : function() {
				$(this).dialog("close");
			}
		}
	});
});
$(document).ready(function() {
	chart = new Highcharts.Chart({
        chart: {
            renderTo: 'ajaxCpu', //图表放置的容器，DIV
            defaultSeriesType: 'spline', //图表类型为曲线图
            height:145,
            margin:[15,20,25,30],
            events: {
                load: function() { 
                    var series = this.series;
					//每隔5秒钟，图表更新一次，y数据值在0-100之间的随机数
                   var url = "${ctx}/showSysInfo/showCpuInfo.action?now=" + new Date().getTime(); // 实时获取最新数据
   				     $.ajax({
   				          url: url,
   				          type: "post",
   				          cache: false,
   				          dataType: "json",
   				          data: {},
   				          ifModified: false,
   				          success: function(result){
   				        	  var time=result[0].sysNowTime;
      				           var x = (new Date(parseInt(time-5000))).getTime();
   				          cpuData = result[0].cpuRatio;
   				          series[0].addPoint([x, parseInt(cpuData)], true, true);
   				         }
   				      }); 
   				   setInterval(function() {
		                   var url = "${ctx}/showSysInfo/showCpuInfo.action?now=" + new Date().getTime(); // 实时获取最新数据
   				    		 $.ajax({
   				          url: url,
   				          type: "post",
   				          cache: false,
   				          dataType: "json",
   				          data: {},
   				          ifModified: false,
   				          success: function(result){
   				        	  var time=result[0].sysNowTime;
      				           var x = (new Date(parseInt(time-5000))).getTime();
   				           cpuData = result[0].cpuRatio;
   				           series[0].addPoint([x, parseInt(cpuData)], true, true);
   				         }
   				      }); 
		              },5000);

                }
            }
           
        },
        title: {
            text: ''  //图表标题
        },
        xAxis: { //设置X轴
            type: 'datetime',  //X轴为日期时间类型
            tickPixelInterval: 200  //X轴标签间隔
        },
        yAxis: { //设置Y轴
        	 title: {
                 text: ''
             },
             startOnTick: true, //为true时，设置min才有效
             min: 0,  

             minrTickInterval: 'auto',// y轴样式 网格的形式
             plotLines: [{
                 value: 0,
                 width: 1,
                 color: '#808080'
             }]
        },
		tooltip: {//当鼠标悬置数据点时的提示框
			formatter: function() { //格式化提示信息
				return '<b>CPU使用率:</b> '+Highcharts.numberFormat(this.y, 2)+'%<br>'+'<b>系统时间:</b>'+
				Highcharts.dateFormat('%H:%M:%S', this.x) ;
			}
		},
        legend: {
            enabled: false  //设置图例不可见
        },credits: {
			enabled: false
		},
		exporting:{ 
           enabled:false//用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
 		},
 		 plotOptions:{//绘图线条控制  
            spline:{  
                allowPointSelect :true,//是否允许选中点  
                animation:true,//是否在显示图表的时候使用动画  
                cursor:'pointer',//鼠标移到图表上时鼠标的样式  
                dataLabels:{  
                    enabled :true,//是否在点的旁边显示数据  
                    rotation:0  
                },  
                enableMouseTracking:true,//鼠标移到图表上时是否显示提示框    
                marker:{  
                   enabled:true,//是否显示点  
                   radius:3,//点的半径  
                   states:{  
                        hover:{  
                            enabled:true//鼠标放上去点是否放大  
                              },  
                        select:{  
                            enabled:false//控制鼠标选中点时候的状态  
                        }  
                    }  
                },  
                states:{  
                    hover:{  
                        enabled:true,//鼠标放上去线的状态控制  
                        lineWidth:3  
                    }  
                },  
                visible:true,  
                lineWidth:1//线条粗细  
            }  
       } ,
        series: [{
            data: (function() { //设置默认数据，
                var data = [],
                time = (new Date($("#dateTime").val()-12000)).getTime(),
                i;
 
                 for (i = -3; i <= 0; i++) {  
                 data.push({  
                  x: time + i * 1000,  
                  y: 0
                });  
              }  
                return data;
            })()
        }]
    });
});
    
	 var chart1;
	    $(document).ready(function() {
	        chart1 = new Highcharts.Chart({
				colors: [
  
   '#AA4643','#89A54E'
],
	            chart: {
	                renderTo: 'ajaxRom',
	                width:460,
	                height:270,
	                plotBackgroundColor: null,
	                plotBorderWidth: null,
	                plotShadow: false,
	                events: {
		               load: function() { 
		                   var series = this.series;
						//每隔5秒钟，图表更新一次，y数据值在0-100之间的随机数
		 
		                   	var url = "${ctx}/showSysInfo/getMemoryInfo.action?now=" + new Date().getTime(); // 实时获取最新数据
		  				     $.ajax({
		  			
		  				          url: url,
		  				          type: "post",
		  				          cache: false,
		  				          dataType: "json",
		  				          data: {},
		  				          ifModified: false,
		  				          success: function(result){
		  				            var usedMemoryRatio = result.memoryRatio;
									
		  				            var unUsedMemoryRatio = 100-usedMemoryRatio;
									
		  				            series[0].setData([['已使用',parseFloat(usedMemoryRatio)],['未使用',parseFloat(unUsedMemoryRatio)]],true);
		  				          $("#use").html(result.usedMemory);
		  				          $("#unuse").html(result.unuseMemory);
		  				         }
		  				      });
	
		                   
		                   setInterval(function(){
		                   	var url = "${ctx}/showSysInfo/getMemoryInfo.action?now=" + new Date().getTime(); // 实时获取最新数据
		                   	 $.ajax({
		  				          url: url,
		  				          type: "post",
		  				          cache: false,
		  				          dataType: "json",
		  				          data: {},
		  				          ifModified: false,
		  				          success: function(result){
		  				            var usedMemoryRatio = result.memoryRatio;
		  				            
		  				            var unUsedMemoryRatio = 100-usedMemoryRatio;
								
		  				                  series[0].setData([['已使用',parseFloat(usedMemoryRatio)],['未使用',parseFloat(unUsedMemoryRatio)]],true);
		  				            $("#use").html(result.usedMemory);
		  				          $("#unuse").html(result.unuseMemory);
		  				         }
		  				      })
		                   },5000);
		                   
		               }
                  }
	            },
	            title: {
	                text: ''
	            },
	            tooltip: {
	        	    formatter: function(){
	        	    	return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2)+' %';
	        	    },
	            	percentageDecimals: 2
	            },
	            plotOptions: {
	                pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								color: '#000000',
								connectorColor: '#000000',
								formatter: function() {
									return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(2)+' %';

								}
							},
							showInLegend: true,
							status:{
								hover:{
									enabled:true
								}
							}
							
							
						}
	            },credits: {
						enabled: false
					},
					exporting:{ 
                    enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
             },
            series: [{
                type: 'pie',
                data: [
                   ['已使用',0],
                   ['未使用',1]
                ]
            }]
	        });
	    });
	    
	    
	    //diskbar
	    var diskchart;
	    $(document).ready(function() {
	        diskchart = new Highcharts.Chart({
	            chart: {
					renderTo: 'diskBar',
	                type: 'column',
	                events: {
		               load: function() { 
		                   var series = this.series;
						//每隔5秒钟，图表更新一次，y数据值在0-100之间的随机数
		                   	var url = "${ctx}/showSysInfo/getSysDiskInfo.action?now=" + new Date().getTime(); // 实时获取最新数据
		  				     $.ajax({
		  				          url: url,
		  				          type: "post",
		  				          cache: false,
		  				          dataType: "json",
		  				          data: {},
		  				          ifModified: false,
		  				          success: function(result){
		  				        	var html="<table  width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"class=\"tab2\" id=\"myTable\">"+
									"<tr height=\"28\" class=\"biaoti\">"+
									"<td width=\"25%\" class=\"biaoti\">磁盘名称</td>"+
									"<td width=\"25%\" class=\"biaoti\">总容量</td>"+
									"<td width=\"25%\" class=\"biaoti\">已使用</td>"+
						  "<td width=\"25%\" class=\"biaoti\">挂载点</td>"+
									"</tr>";
		  				             var category =[];
		  				             var totalMemoryValue =[];
		  				           //  var freeMemoryValue = [];
		  				           //  var usedMemoryValue = [];
		  				            jQuery.each(result,function(m,disk){
		  				               category.push(disk[0]);
		  				               html+="<tr><td valign=\"middle\">"+disk[0]+"</td><td valign=\"middle\">"+disk[1]+"</td>"+
		  				             "<td valign=\"middle\">"+disk[2]+"</td><td valign=\"middle\">"+disk[5]+"</td></tr>";
		  				               if(disk[4].length>1){
		  				                 totalMemoryValue.push(parseFloat(disk[4].substring(0,(disk[1].length-1))));
		  				               }else{
		  				                 totalMemoryValue.push(pareseFloat(disk[4]));
		  				               }
		  				              /*  if(disk[2].length>1){
		  				                 usedMemoryValue.push(parseFloat(disk[2].substring(0,(disk[2].length-1))));
		  				               }else{
		  				               	 usedMemoryValue.push(parseFloat(disk[2]));
		  				               }
		  				               if(disk[3].length>1){
		  				               	 freeMemoryValue.push(parseFloat(disk[3].substring(0,(disk[3].length-1))));
		  				               }else{
		  				               	 freeMemoryValue.push(parseFloat(disk[3]));
		  				               } */
		  				            });
									html+="</table>";
		  				             diskchart.xAxis[0].setCategories(category);
		  				             diskchart.series[0].setData(totalMemoryValue);
		  				             $("#trr").html(html);
		  				            // diskchart.series[1].setData(usedMemoryValue);
		  				             //diskchart.series[2].setData(freeMemoryValue);
		  				         }
		  				      });
		                   
		                    setInterval(function(){
		                    	var url = "${ctx}/showSysInfo/getSysDiskInfo.action?now=" + new Date().getTime(); // 实时获取最新数据
		  				     $.ajax({
		  				          url: url,
		  				          type: "post",
		  				          cache: false,
		  				          dataType: "json",
		  				          data: {},
		  				          ifModified: false,
		  				          success: function(result){
		  				        	 var html="<table  width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\"class=\"tab2\" id=\"myTable\">"+
										"<tr height=\"28\" class=\"biaoti\">"+
										"<td width=\"25%\" class=\"biaoti\">磁盘名称</td>"+
										"<td width=\"25%\" class=\"biaoti\">总容量</td>"+
										"<td width=\"25%\" class=\"biaoti\">已使用</td>"+
										"<td width=\"25%\" class=\"biaoti\">挂载点</td>"+
										"</tr>";
													
											
		  				        	 
		  				             var category =[];
		  				             var totalMemoryValue =[];
		  				             //var freeMemoryValue = [];
		  				           //  var usedMemoryValue = [];
		  				            jQuery.each(result,function(m,disk){
		  				               category.push(disk[0]);
		  				             html+="<tr><td valign=\"middle\">"+disk[0]+"</td><td valign=\"middle\">"+disk[1]+"</td>"+
		  				           "<td valign=\"middle\">"+disk[2]+"</td><td valign=\"middle\">"+disk[5]+"</td></tr>";
		  				               if(disk[4].length>1){
		  				                 totalMemoryValue.push(parseFloat(disk[4].substring(0,(disk[1].length-1))));
		  				               }else{
		  				                 totalMemoryValue.push(pareseFloat(disk[4]));
		  				               }
		  				              /*  if(disk[2].length>1){
		  				                 usedMemoryValue.push(parseFloat(disk[2].substring(0,(disk[2].length-1))));
		  				               }else{
		  				               	 usedMemoryValue.push(parseFloat(disk[2]));
		  				               }
		  				               if(disk[3].length>1){
		  				               	 freeMemoryValue.push(parseFloat(disk[3].substring(0,(disk[3].length-1))));
		  				               }else{
		  				               	 freeMemoryValue.push(parseFloat(disk[3]));
		  				               } */
		  				            });
									html+="</table>";
		  				             diskchart.xAxis[0].setCategories(category);
		  				             diskchart.series[0].setData(totalMemoryValue);
		  				           $("#trr").html(html);
		  				            // diskchart.series[1].setData(usedMemoryValue);
		  				             //diskchart.series[2].setData(freeMemoryValue);
		  				         }
		  				      });
		                    },5000);
  
		               }
                  }
	            },
	            xAxis: {
	                categories:['/dev/sda7','udev','tmpfs','none','none'],
	                title: {
	                    text:'磁盘',
	                    align:'high'
	                }
	            },
	            title:{
	            	text:''
	            },
	            yAxis:{
	                min: 0,
	                title: {
	                    text: '单位 (%)',
	                    align: 'high'
	                },
	                labels: {
	                    overflow: 'justify'
	                }
	            },
	            tooltip: {
	                 formatter:function(){
	                 	 return "已使用："+this.y+"%";
	                 }
	            },
	            plotOptions: {
	                 column: {
	                  pointWidth: 40,     //设置柱形的宽度
                      borderWidth: 0      //设置柱子的边框，默认是1
	                 }
	            },
	            legend: {
	                layout: 'horizontal',
	                align: 'right',
	                verticalAlign: 'top',
	                floating: true,
	                borderWidth: 1,
	                backgroundColor: '#FFFFFF'
	            },
	            credits: {
	                enabled: false
	            },
	            series: [{
	                name: '已用百分比',
	                data: [0,0,0,0,0],
	                dataLabels:{
	                	enabled:true
	                }
	            }]
	        });
	    });

	    <%
	      Calendar rightNow = Calendar.getInstance();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String sysdate = format.format(rightNow.getTime());
	int week = rightNow.get(rightNow.DAY_OF_WEEK);
	String weekary[] ={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	%>
	var dateTime  ;
	var currentDate;
	function getTimes(){
		dateTime=$("#dateTime").val()-3000;
		currentDate= new Date(parseInt(dateTime));
		
	}
	  
function updateDate() {
	currentDate.setSeconds(currentDate.getSeconds()+1); 
        document.getElementById('times').innerHTML =
        	currentDate.toLocaleString();
    }
setInterval("updateDate();", 1000);
    function disc() {
    	 $("#checkdisk").html("");
		$.post("${ctx}/showSysInfo/display.action", function(data) {
			var obj = data.substring(1, data.length - 1);
			obj = eval("(" + obj + ")");
			$("#emailDisk").val(obj.emailDisk);
			if (obj.diskAlertUse == 1) {
				$("#diskAlert_use").attr("checked", "checked");
			}
			$(
					"#diskAlter_criticalPoint option[value="
							+ obj.diskAlterCriticalPoint + "]").attr(
					"selected", "selected");
		});
		$('#dialog-extQuery').dialog('open');
	}
    function extQuery() {
		var emailDisk = $("#emailDisk").val();
		var email = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if (email.test(emailDisk)) {
			var diskAlertUse = 0;
			var diskAlterCriticalPoint = $("#diskAlter_criticalPoint").val();
			if ($("#diskAlert_use").attr("checked")) {
				diskAlertUse = 1;	
			}
			$.post("${ctx}/showSysInfo/update.action", {
					emailDisk : emailDisk,
					diskAlertUse : diskAlertUse,
					diskAlterCriticalPoint : diskAlterCriticalPoint
				}, function() {

				});
			 $("#checkdisk").html("");
		 return true;

		} else {
		    $("#checkdisk").addClass("spanred");
		    $("#checkdisk").html("邮箱地址不合法");
			return false;
			
		}

	}
//}
</script>	
<script language="javascript">
	var g_addgrouptatus = 0;
	function sysTimeSetting(e) {
		if (g_addgrouptatus == 1) {
			destroyTimeSettinDlg();
			return;
		}
		var t = e.offsetTop+22;
		var l = e.offsetLeft + e.offsetWidth -168;
		var w = e.offsetWidth;
		while (e = e.offsetParent) {
			t += e.offsetTop;
			l += e.offsetLeft;
		}
		destroyTimeSettinDlg();

		var objMsgDiv = document.createElement("div");

		var nowtime = new Date();

		objMsgDiv.setAttribute('id', 'timeSetting');
		objMsgDiv.style.position = "absolute";
		objMsgDiv.style.background = "#eeeeee";
		objMsgDiv.style.left = l + "px";
		objMsgDiv.style.top = t + "px";
		objMsgDiv.style.border = 1 + "px  solid #000000";
		objMsgDiv.style.width = "300px";
		objMsgDiv.style.zIndex = "10000";
		objMsgDiv.innerHTML += "<form action='' method='post' name='settingForm' id='settingForm'>"
				+ "<table width=\"100%\" border=\"0\" cellspacing=\"5\" cellpadding=\"5\">"
				+ "<tr><td align=\"left\" colspan=\"2\">系统日期:"
				+ "<input id='sysDate' readonly='readonly' onclick='WdatePicker({startDate:\"%y-%M-01\",dateFmt:\"yyyy-MM-dd\",alwaysUseStartDate:true})' name='sysDate' value='${sysDate}' class='Wdate' style='width:120px; height:18px;'>(格式为:年-月-日)</td></tr>"
				+ "<tr><td align=\"left\" colspan=\"2\">系统时间:"
				+ "<input type='text' id='sysTime' name='sysTime' value='${sysTime}' readonly='readonly' onclick='WdatePicker({startDate:\"%y-%M-01#%H:%m:%s\",dateFmt:\"HH:mm:ss\",alwaysUseStartDate:true})' style='width:120px; height:18px;'/>(格式为:时:分:秒)</td></tr>"
				+ "<tr><td></td><td><table width=\"100%\" border=\"0\">"
				+ "<td align=\"right\" width=\"80%\"><div onmouseover=\"this.style.cursor='pointer'\" onclick='if(saveSysTime())destroyTimeSettinDlg();'/><u>保存</u></div></td>"
				+ "<td align=\"left\" width=\"20%\"><div onclick='destroyTimeSettinDlg()' onmouseover=\"this.style.cursor='pointer'\"/><u>关闭</u></div></td>"
				+ "</tr></table></td>" + "</tr></table>" + "</from>";
		document.body.appendChild(objMsgDiv);
		g_addgrouptatus = 1;
	}
	function destroyTimeSettinDlg() {
		if (g_addgrouptatus == 1) {
			document.body.removeChild(document.getElementById("timeSetting"));
			g_addgrouptatus = 0;
		}
	}
	function saveSysTime(id) {
		
		var time = $("#"+id).val();
		if(time!=""){
		      var str =time.split(" ");
		      $("#sysDate").val(str[0]);
		      $("#sysTime").val(str[1]);
		      $("#"+id).attr("style","display:none");
              $("#time").attr("style","display:block");
              $("#time").attr("value",time);
		      if(confirm("修改时间需要重新登录，您确认修改时间吗！")){
			  	$("#setTimeForm").submit();
			  }
		}else{
		$("#"+id).attr("style","display:none");
        $("#time").attr("style","display:block");
        $("#time").attr("value","设置");
        
	}
	}
	
		
	
	
	
	function cpuInfo(){
		window.location.href='${ctx}/showSysInfo/query.action';
	}
	
	function memoryQuery(){
		window.location.href='${ctx}/showSysInfo/memoryQuery.action';
	}
	function changeTag(id){
	     $("#"+id).attr("style","display:none");
	     $("#set"+id).attr("style","display:block");
	     $("#set"+id).focus();
	}
	function closeDia(){
	if($(".WdateDiv").is(":visible")){
	alert("1");
	}
	}
</script>
</head>

<body onload="getTimes();">
    <form action="${ctx}/showSysInfo/sysTimeSetting.action" id="setTimeForm">
    	<input type="hidden" name="sysDate" id="sysDate">
    	<input type="hidden" name="sysTime" id="sysTime">
    	<input type="hidden" name="dateTime" id="dateTime" value="${dateTime}"/>
    </form>
	<!--  主table-->
	<s:form action="update-e.action" namespace="/setting" method="post"
		theme="simple" id="empForm" name="empForm">
		<!--  主table-->
		<table width="100%">
			<tr>
				<td width="50%">
					<!-- 3 -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style=" margin-top: 0px;margin-left: 2px;" align="left">
						<tr>
							<!-- left area -->
							<td valign="top">
								<div class="settingdivtable1" style="height: 182px; ">
									<!--  左侧table-->
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="padding-top:1px;padding-left:1px;padding-right:1px;">
										<tr>
											<td class="r2titler"><b>CPU</b>
											<input type="hidden" id="dateTime" value="${dateTime}"/>
											</td>
										</tr>

										<!-- 分隔符黑线 -->
										<tr>
											<td height="3px"></td>
										</tr>

										<tr>
											<td align="right"><font color="red">
											</font></td>
										</tr>

										<tr>
											<td>
												<c:if test="${showCpuDataPic eq '1'}">
													<div id="ajaxCpu" style="width: 100%;">
													
													</div>
												</c:if>
												<c:if test="${showCpuDataPic eq '0'}">
													<div style="width:99%;margin: 0 auto; text-align: center; padding-top:50px; ">
														<span style="font-size: 13px;color: #777777;">请部署在linux系统上</span>
													</div>
												</c:if>
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>
									</table>
								</div>
							</td>

						</tr>
					</table>
					
				</td>
				<td rowspan="2" width="50%">
					<!-- 2 -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="margin-right: 1px; margin-top: 0px;; margin-left: 2px; " align="center">
						<tr>
							<!-- left area -->
							<td valign="top">
								<div class="settingdivtable1" style="height: 308px; text-align: center;">
									<!--  左侧table-->
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="padding-top:1px;padding-left:1px;padding-right:1px;" >
										<tr>
											<td class="r2titler"><b>内存&nbsp;&nbsp;<c:if test="${showCpuDataPic eq '1'}">已使用：<span id="use"></span>&nbsp;&nbsp;未使用：<span id="unuse"></span></c:if></b>
											</td>
										</tr>

										<!-- 分隔符黑线 -->
										<tr>
											<td height="3px"></td>
										</tr>

										<tr>
											<td align="right"><font color="red">
											</font></td>
										</tr>

										<tr>
											<td align="center">
												<c:if test="${showCpuDataPic eq '1'}">
													<div id="ajaxRom" style="width: 100%;">
													
													</div>
												</c:if>
												<c:if test="${showCpuDataPic eq '0'}">
													<div style="width:99%;margin: 0 auto; text-align: center; padding-top:120px; ">
														<span style="font-size: 13px; color: #777777;">请部署在linux系统上</span>
													</div>
												</c:if>
											</td>
										</tr>
										<tr height="15">
											<td class="td_detail_separator"></td>
										</tr>

									</table>
								</div>
							</td>

						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="50%">
					
					 <!-- 4 -->
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						style="margin-left: 2px; margin-top: 2px" align="left">
						<tr>
							<!-- left area -->
							<td valign="top">
								<div class="settingdivtable2">
									<!--  左侧table-->
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="padding-top:1px;padding-left:1px;padding-right:1px;">
										<tr>
											<td class="r2titler"><b>系统时间</b>
											</td>
										</tr>

										<!-- 分隔符黑线 -->
										<tr>
											<td height="3px"></td>
										</tr>

										<tr>
											<td align="right"><font color="red">
											</font></td>
										</tr>

										<tr>
											<td>
												<c:if test="${showCpuDataPic eq '1'}">
													<table width="99%" border="0" cellspacing="0"
													cellpadding="0" style="margin-left:-100px">
													<!-- 空行 -->
													
													<tr>
														<td align="right" class="setttinginfotd">运行时间：</td>
														<td>${sysInfo.runTime}</td>
													</tr>
													<!-- 空行 -->
												<tr>
														<td class="td_detail_separator"></td>
													</tr>
													<tr>
														<td align="right" class="setttinginfotd">系统日期：</td>
														<td>${sysDate}<span id="systemTime"></span></td>
													</tr>
													<!-- 空行 -->
													<tr>
														<td class="td_detail_separator"></td>
													</tr>
													<tr>
														<td align="right" class="setttinginfotd">系统时间：</td>
														<!-- <td><a href="#" id="time" onclick="sysTimeSetting(this)">设置系统时间</a></td> -->
														<td>
														<div id="times">
   
</div></td></tr>	
										<!--  </tr>
													<tr><td align="right" class="setttinginfotd">设置系统时间：	
													</td>
													<td>
													<input type="button" id="time" value="设置"
															class="btnstyle" onclick="changeTag('time')" style="display: block"/>
															<input type="text" id="settime"
															onclick="WdatePicker({startDate:'%y-%M-01',isShowWeek:'true',readOnly:'true',dateFmt:'yyyy-MM-dd HH:mm:ss'})" 
															onchange="saveSysTime('settime')" onblur="closeDia()" style="display: none"/>
															</td>

													</tr>-->
													<!-- 空行 -->
													<tr>
														<td class="td_detail_separator"></td>
													</tr>
												</table>
												</c:if>
												<c:if test="${showCpuDataPic eq '0'}">
													<div style="width:99%;margin: 0 auto; text-align: center; padding-top:40px; ">
														<span style="font-size: 13px;color: #777777;">请部署在linux系统上</span>
													</div>
												</c:if>
											</td>
										</tr>
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									</table>
								</div>
							</td>

						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>

	<!--  磁盘主table-->
		<div width="100%">
			<!-- 2 -->
					<table width="99.3%" border="0" cellspacing="0" cellpadding="0"
						style="margin-right: 1px; margin-top: 2px; margin-left: 4px;" align="left">
						<tr>
							<!-- left area -->
							<td valign="top">
								<div class="settingdivtable1" style="height: 390px;">
									<!--  左侧table-->
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										style="padding-top:1px;padding-left:1px;padding-right:1px;">
										<tr>
											<td class="r2titler"><b>磁盘</b>
											<span style="float: right;"><input type="button"
						value="磁盘告警" class="btnstyle" onclick="disc();" />&nbsp;&nbsp;&nbsp;&nbsp;</span>
											</td>
										</tr>

										<!-- 分隔符黑线 -->
										<tr>
											<td height="3px"></td>
										</tr>

										<tr>
											<td align="right"><font color="red">
											</font></td>
										</tr>

										<tr>
											<td width="100%">
												<c:if test="${showCpuDataPic eq '1'}">
													<div id="diskBar" style="min-width: 800px; height: 355px;"></div>
												</c:if>
												<c:if test="${showCpuDataPic eq '0'}">
													<div style="width:99%;margin: 0 auto; text-align: center; padding-top:150px; ">
														<span style="font-size: 13px; color: #777777;">请部署在linux系统上</span>
													</div>
													</c:if>
											</td>
										</tr>
										
										<tr height="15">
											<td class="td_detail_separator"></td>
										</tr>

									</table>
								</div>
							</td>

						</tr>
						<tr>
											<td width="100%">
												<c:if test="${showCpuDataPic eq '1'}">
												
													<div id="trr">
													
													</div>
													
												</c:if>
												<c:if test="${showCpuDataPic eq '0'}">
													<div style="width:99%;margin: 0 auto; text-align: center; padding-top:150px; ">
														<span style="font-size: 13px; color: #777777;">请部署在linux系统上</span>
													</div>
													</c:if>
											</td>
										</tr>
					</table>
		</div>
	<div id="dialog-extQuery" title="磁盘空间告警设置">
		<table width="90%" border="0" cellspacing="5" cellpadding="5"
			style="margin-left:20px;">
			<tr>
				<td width="30%">收件人邮箱:</td>
				<td><input id="emailDisk" name="emailDisk" type="text"
					style="width:250px;" onkeypress="if(event.keyCode==13)extQuery();" />
				</td>
			</tr>
			<tr>
				<td>启用磁盘空间告警:</td>
				<td align="left" width="60%"><input type="checkbox"
					name="diskAlertUse" id="diskAlert_use"
					<c:if test="${diskAlertUse==1}">checked="checked"</c:if>
					value="1" />
				</td>
			</tr>
			<tr>
				<td>告警临界点:</td>
				<td align="left" width="60%"><select
					name="diskAlterCriticalPoint" id="diskAlter_criticalPoint">
						<option value="60">60%</option>
						<option value="70">70%</option>
						<option value="80">80%</option>
						<option value="90">90%</option>
				</select></td>
			</tr>
			<tr>
				<td><span id="checkdisk"></span></td>
			</tr>
		</table>
	</div>
</body>
</html>