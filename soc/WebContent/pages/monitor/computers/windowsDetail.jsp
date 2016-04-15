<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
 <script type="text/javascript" src="${ctx}/scripts/esl.js"></script>
<script type='text/javascript'
	src="${ctx}/scripts/jquery-ui.custom.min.js"></script>

<script type="text/javascript" src="${ctx}/scripts/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/exporting.js"></script>
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet"
	type="text/css">

<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css" />

<link href="${ctx}/styles/jqchart/jquery.jqRangeSlider.css"
	rel="stylesheet" type="text/css" />
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
<script type="text/javascript">
	
	$(document).ready(function() {

		$('#tabs-setting').tabs();

	
	});

	function creaChart(){
		reFresh();
		creaChart1();
		creaChart2();
		creaChart3();
		creaChart4();
		creaChart5(); 
		creaChart6(); 
	 }
	 /* function creaChart1(){
		 var chart = new Highcharts.Chart({  
	         chart: {  
	             renderTo: 'cpuUse',  
	             plotBackgroundColor: null,  
	             plotBorderWidth: null,  
	             plotShadow: false  
	         },  
	         title: {  
	             text: ''  
	         },  
	         credits: {  
	             enabled: false  
	         },  
	         exporting: {  
	             enabled: false  
	         },  
	         tooltip: {  
	             formatter: function () {  
	                 return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';  
	             }  
	         },  
	         plotOptions: {  
	             pie: {  
	                 allowPointSelect: true,  
	                 cursor: 'pointer',  
	                 showInLegend: false,  
	                 dataLabels: {  
	                     enabled: true,  
	                     
	                     formatter: function () {  
	                         return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';  
	                     }  
	                 }  
	             }  
	         },  
	         series: [{  
	             type: 'pie',  
	             name: '堆使用率',  
	             data: [${winDetail.usedCpu},${winDetail.unUsedCpu}] //从后台数据库读取并动态拼凑序列串  
	         }]   
	 });
	 }  */
	 
	 function creaChart1(){
		 require.config({
		        paths:{
		            echarts:'${ctx}/scripts/echarts',
		            'echarts/chart/bar' : '${ctx}/scripts/echarts',
		            'echarts/chart/line': '${ctx}/scripts/echarts'
		        }
		    });
			require(
		        [
		            'echarts',
		            'echarts/chart/bar',
		            'echarts/chart/line'
		        ],
		        function(ec) {
		            var myChart = ec.init(document.getElementById('cpuUse'));
		            var option = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		    	  show : false,
		          feature : {
		              mark : {show: false},
		              restore : {show: false},
		              saveAsImage : {show: false}
		          }
		    },
		    series : [
		        {
		            name:'cpu利用率',
		            type:'gauge',
		            detail : {formatter:'{value}%'},
		            data:[${winDetail.usedCpu}]
		        }
		    ]
		};

		            myChart.setOption(option);  
		  
		        }  
		  
		    );  
	 }
		 
	  function creaChart2(){
		 var chart = new Highcharts.Chart({  
	         chart: {  
	             renderTo: 'memoryUse',  
	             plotBackgroundColor: null,  
	             plotBorderWidth: null,  
	             plotShadow: false  
	         },  
	         title: {  
	             text: ''  
	         },  
	         credits: {  
	             enabled: false  
	         },  
	         exporting: {  
	             enabled: false  
	         },  
	         tooltip: {  
	             formatter: function () {  
	                 return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';  
	             }  
	         },  
	         plotOptions: {  
	             pie: {  
	                 allowPointSelect: true,  
	                 cursor: 'pointer',  
	                 showInLegend: false,  
	                 dataLabels: {  
	                     enabled: true,  
	                     
	                     formatter: function () {  
	                         return '<b>' + this.point.name + '</b>: ' + Highcharts.numberFormat(this.percentage, 2) + ' %';  
	                     }  
	                 }  
	             }  
	         },  
	         series: [{  
	             type: 'pie',  
	             name: '内存使用率',  
	             data: [${winDetail.usedMemory},${winDetail.unUsedMemory}] //从后台数据库读取并动态拼凑序列串  
	         }]   
	 });} 

	   function creaChart3(){
			 var chart = new Highcharts.Chart({
				        chart: {
				            renderTo:'diskUse',
				            //backgroundColor: '#CFCFCF',
				             //borderColor:'red',  
				            type: 'column'
				            //marginRight: 10,
				        },
				        title: {
				            text: '',
				            style:{	
				            } 	
				        },
				        xAxis: {
				        	categories: [${winDetail.diskName}]  
				        },
				        yAxis: {
				            title:'',
				            allowDecimals:false, //是否允许刻度有小数
				            min:0
				        },
				        legend: {
				        	enabled:false
			            },
				        tooltip: {
				        	formatter: function() {                 //当鼠标悬置数据点时的格式化提示
				                   return ''+ this.series.name + ': '+ Highcharts.numberFormat(this.y, 0)+'%';
				               }
				        },
				        credits: {
				            enabled: false
			            },
				        exporting: {
				            enabled: false
				        },
				        plotOptions: {
				        	column: {
				                   pointPadding: 0.2,  //图表柱形的
				                   borderWidth: 0      //图表柱形的粗细 
				               },bar: {
				                   dataLabels: {
				                       enabled: false
				                   }
				               }
				        },
				        series: [{
				            name: '使用率',
				            data:[${winDetail.usedDisk}]
				        }]
				    });
		 }
	   
	   function creaChart4(){
		   new HighchartsPager('flow',10,{
			   chart: {
		            //renderTo:'flow',
		            //backgroundColor: '#CFCFCF',
		             //borderColor:'red',  
		            type: 'column'
		           // marginRight: 10,
		        },
		        title: {
		            text: ''
		        },
		        xAxis: {
		            categories:[${winDetail.interfaceName}],
		            labels: {
		                style: {
		                    fontStyle : 'italic'//这个是控制斜放的
		                }  
		            	//enabled:true
		            }
		      },
		        yAxis: {
		            min: 0,
		            title: {
		                text: ''
		            },
		            stackLabels: {
		                enabled:false,
		                style: {
		                    fontWeight: 'bold',
		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		                }
		            }
		        },
		        legend: {
		            align: 'right',
		            x: -70,
		            verticalAlign: 'top',
		            y: 20,
		            floating: true,
		            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
		            borderColor: '#CCC',
		            borderWidth: 1,
					 enabled:false,
		            shadow: false
		        },
		        tooltip: {
		            formatter: function() {
		                return '<b>'+ this.x +'</b><br>'+
		                    this.series.name +': '+ this.y +'<br>'+
		                    'Total: '+ this.point.stackTotal;
		            }
		        },
		        credits: {
		            enabled: false
	            },
		        plotOptions: {
		            column: {
		                stacking: 'normal',
		                dataLabels: {
		                    enabled: false,
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
		                }
		            }
		        },
		        series: [{
		            name: '发送流量',
		            data: [${winDetail.sendFlow}]
		        }, {
		            name: '接收流量',
		            data: [${winDetail.arriveFlow}]
		        }
			   ]
		   });
		   
	   }
	   
      function creaChart5(){
    	  new HighchartsPager('error',10,{
    		  chart: {
  	           // renderTo:'error',
  	            //backgroundColor: '#CFCFCF',
  	             //borderColor:'red',  
  	            type: 'column'
  	           // marginRight: 10,
  	        },
  	        title: {
  	            text: ''
  	        },
  	        xAxis: {
  	            categories: [${winDetail.interfaceName}],
  	            labels: { 
                      //rotation:90
  	            	enabled: false
                  } 
  	      },
  	        yAxis: {
  	            min: 0,
  	            title: {
  	                text: ''
  	            },
  	            stackLabels: {
  	                enabled:false,
  	                style: {
  	                    fontWeight: 'bold',
  	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
  	                }
  	            }
  	        },
  	        legend: {
  	            align: 'right',
  	            x: -70,
  	            verticalAlign: 'top',
  	            y: 20,
  	            floating: true,
  	            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
  	            borderColor: '#CCC',
  	            borderWidth: 1,
  				 enabled:false,
  	            shadow: false
  	        },
  	        tooltip: {
  	            formatter: function() {
  	                return '<b>'+ this.x +'</b><br>'+
  	                    this.series.name +': '+ this.y +'<br>'+
  	                    'Total: '+ this.point.stackTotal;
  	            }
  	        },
  	        credits: {
  	            enabled: false
              },
  	        plotOptions: {
  	            column: {
  	                stacking: 'normal',
  	                dataLabels: {
  	                    enabled: false,
  	                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
  	                }
  	            }
  	        },
  	        series: [{
  	            name: '发送错误率',
  	            data:  [${winDetail.sendError}] 
  	        }, {
  	            name: '接收错误率',
  	            data:[${winDetail.arriveError}]
  	        }
  		   ]
    	  });
	   }
	   
     function creaChart6(){
    	 new HighchartsPager('lose',10,{
    		 chart: {
		            //renderTo:'lose',
		            //backgroundColor: '#CFCFCF',
		             //borderColor:'red',  
		            type: 'column'
		           // marginRight: 10,
		        },
		        title: {
		            text: ''
		        },
		        xAxis: {
		            categories: [${winDetail.interfaceName}],
		            labels: { 
	                    rotation: 90 
	                } 
		      },
		        yAxis: {
		            min: 0,
		            title: {
		                text: ''
		            },
		            stackLabels: {
		                enabled:false,
		                style: {
		                    fontWeight: 'bold',
		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		                }
		            }
		        },
		        legend: {
		            align: 'right',
		            x: -70,
		            verticalAlign: 'top',
		            y: 20,
		            floating: true,
		            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
		            borderColor: '#CCC',
		            borderWidth: 1,
					 enabled:false,
		            shadow: false
		        },
		        tooltip: {
		            formatter: function() {
		                return '<b>'+ this.x +'</b><br>'+
		                    this.series.name +': '+ this.y +'<br>'+
		                    'Total: '+ this.point.stackTotal;
		            }
		        },
		        credits: {
		            enabled: false
	            },
		        plotOptions: {
		            column: {
		                stacking: 'normal',
		                dataLabels: {
		                    enabled: false,
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
		                }
		            }
		        },
		        series: [{
		            name: '发送d丢包率',
		            data:  [${winDetail.sendLose}] 
		        }, {
		            name: '接收丢包率',
		            data:[${winDetail.arriveLose}]
		        }
			   ]
    	 });
      }
	   
	  
     function HighchartsPager(id, pageSize, options) {
    	    this.id = id;
    	    options.chart = options.chart || {};
    	    options.chart.renderTo = id;
    	    this._options = $.extend({}, options);
    	    this._xAxis = options.xAxis;
    	    this._series = options.series;
    	     
    	    this._total = 0;
    	    if(this._xAxis.categories){
    	        this._total = this._xAxis.categories.length
    	    }else{
    	        this._total = this._series[0].data.length;
    	    }
    	    this.toPage(this._total, pageSize);
    	    this.chart = null;
    	    //this.chart = new Highcharts.Chart(options);
    	     
    	    this.showPage(1);   
    	     
    	    return this.chart;
    	}
    	 
    	HighchartsPager.prototype.showPageBar = function(pageTotal) {
    	    var the = this;
    	    var arr = [];
    	    var suffixStr = '-pagebar-div';
    	    for ( var i = 0; i < pageTotal; i++) {
    	        arr.push('<a style="margin-right: 10px;text-decoration : underline; cursor: pointer; font-size: 17px;">'
    	                        + (i + 1) + '</a>');
    	    }
    	    $('#' + this.id).append(
    	            '<center><div style="border:0px red solid; height: 30px; width: 300px;" id="'
    	                    + this.id + suffixStr + '">' + arr.join('')
    	                    + '</div></center>');
    	 
    	    the._current_pageNum = -1;
    	 
    	    $('#' + this.id + suffixStr).children().each(function(index) {
    	        $(this).click(function() {
    	            the.showPage(index + 1);
    	        });
    	    });
    	}
    	HighchartsPager.prototype.showPage = function(pageNum) {
    	    var the = this;
    	    var suffixStr = '-pagebar-div';
    	    if (pageNum == the._current_pageNum) {
    	        return;
    	    }
    	    var data = the.pageData(pageNum);
    	    if (the.chart == null) {
    	        var options = $.extend({}, the._options);
    	        options.xAxis = data.xAxis;
    	        options.series = data.series;
    	        the.chart = new Highcharts.Chart(options);
    	        the.showPageBar(the._page.pageTotal);
    	 
    	        the._current_pageNum = 1;
    	        $($('#' + the.id + suffixStr).children()[0]).css('text-decoration',
    	                'none').css(
    	                        'font-size', '17px').css(
    	                                'font-weight', 'bold');
    	 
    	    } else {
    	        the.removeData();
    	        the.chart.addAxis(data.xAxis, true, true);
    	        for ( var i = 0; i < data.series.length; i++) {
    	            the.chart.addSeries(data.series[i], true);
    	        }
    	        if (the._current_pageNum != -1) {
    	            $($('#' + the.id + suffixStr).children()[the._current_pageNum - 1])
    	                    .css('text-decoration', 'underline').css(
    	                            'font-size', '17px').css(
    	                                    'font-weight', 'normal');
    	        }
    	        the._current_pageNum = pageNum;
    	        $($('#' + the.id + suffixStr).children()[pageNum - 1]).css(
    	                'text-decoration', 'none').css(
    	                        'font-size', '17px').css(
    	                                'font-weight', 'bold');
    	    }
    	 
    	}
    	HighchartsPager.prototype.toPage = function(total, pageSize) {
    	    this._page = {
    	        pageSize : pageSize,
    	        pageTotal : (total - total % pageSize) / pageSize
    	                + (total % pageSize != 0 ? 1 : 0),
    	        total : total
    	    };
    	}
    	 
    	HighchartsPager.prototype.pageData = function(pageNum) {
    	    var xAxis = $.extend({}, this._xAxis);
    	    if(xAxis.categories){
    	        xAxis.categories = [];
    	        for ( var i = (pageNum - 1) * this._page.pageSize; i < Math.min(this._total, pageNum * this._page.pageSize); i++) {
    	            xAxis.categories.push(this._xAxis.categories[i]);
    	        }       
    	    }   
    	 
    	    var series = [];
    	    var series_child = null;
    	    for ( var j = 0; j < this._series.length; j++) {
    	        series_child = $.extend({}, this._series[j]);
    	        series_child.data = [];
    	 
    	        for ( var i = (pageNum - 1) * this._page.pageSize; i < Math.min(
    	                this._series[j].data.length, pageNum * this._page.pageSize); i++) {
    	            series_child.data.push(this._series[j].data[i]);
    	        }
    	 
    	        series.push(series_child);
    	    }
    	    return {
    	        xAxis : xAxis,
    	        series : series
    	    };
    	}
    	 
    	HighchartsPager.prototype.removeData = function() {
    	    if (this.chart == null) {
    	        return;
    	    }
    	    for ( var i = 0; i < this.chart.xAxis.length; i++) {
    	        //this.chart.xAxis[i].remove();
    	    }
    	 
    	    //for(var i=0; i<this.chart.series.length; i++){
    	    //this.chart.series[i].remove(true);
    	    //}
    	 
    	    this.chart.xAxis[0].remove(true);
    	 
    	}

     
     
	   
	   /* function creaChart4(){
			 var chart = new Highcharts.Chart({
				        chart: {
				            renderTo:'flow',
				            //backgroundColor: '#CFCFCF',
				             //borderColor:'red',  
				            type: 'column'
				           // marginRight: 10,
				        },
				        title: {
				            text: ''
				        },
				        xAxis: {
				            categories:[${winDetail.interfaceName}],
				            labels: {
				                style: {
				                    fontStyle : 'italic'//这个是控制斜放的
				                }  
				            	//enabled:true
				            }
				      },
				        yAxis: {
				            min: 0,
				            title: {
				                text: ''
				            },
				            stackLabels: {
				                enabled:false,
				                style: {
				                    fontWeight: 'bold',
				                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
				                }
				            }
				        },
				        legend: {
				            align: 'right',
				            x: -70,
				            verticalAlign: 'top',
				            y: 20,
				            floating: true,
				            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
				            borderColor: '#CCC',
				            borderWidth: 1,
							 enabled:false,
				            shadow: false
				        },
				        tooltip: {
				            formatter: function() {
				                return '<b>'+ this.x +'</b><br>'+
				                    this.series.name +': '+ this.y +'<br>'+
				                    'Total: '+ this.point.stackTotal;
				            }
				        },
				        credits: {
				            enabled: false
			            },
				        plotOptions: {
				            column: {
				                stacking: 'normal',
				                dataLabels: {
				                    enabled: false,
				                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
				                }
				            }
				        },
				        series: [{
				            name: '发送流量',
				            data: [${winDetail.sendFlow}]
				        }, {
				            name: '接收流量',
				            data: [${winDetail.arriveFlow}]
				        }
					   ]
				    });
		 } 
	function creaChart5(){
		 //alert(${winDetail.interfaceName});
	 var chart = new Highcharts.Chart({
	        chart: {
	            renderTo:'error',
	            //backgroundColor: '#CFCFCF',
	             //borderColor:'red',  
	            type: 'column'
	           // marginRight: 10,
	        },
	        title: {
	            text: ''
	        },
	        xAxis: {
	            categories: [${winDetail.interfaceName}],
	            labels: { 
                    //rotation:90
	            	enabled: false
                } 
	      },
	        yAxis: {
	            min: 0,
	            title: {
	                text: ''
	            },
	            stackLabels: {
	                enabled:false,
	                style: {
	                    fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                }
	            }
	        },
	        legend: {
	            align: 'right',
	            x: -70,
	            verticalAlign: 'top',
	            y: 20,
	            floating: true,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
	            borderColor: '#CCC',
	            borderWidth: 1,
				 enabled:false,
	            shadow: false
	        },
	        tooltip: {
	            formatter: function() {
	                return '<b>'+ this.x +'</b><br>'+
	                    this.series.name +': '+ this.y +'<br>'+
	                    'Total: '+ this.point.stackTotal;
	            }
	        },
	        credits: {
	            enabled: false
            },
	        plotOptions: {
	            column: {
	                stacking: 'normal',
	                dataLabels: {
	                    enabled: false,
	                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
	                }
	            }
	        },
	        series: [{
	            name: '发送错误率',
	            data:  [${winDetail.sendError}] 
	        }, {
	            name: '接收错误率',
	            data:[${winDetail.arriveError}]
	        }
		   ]
	    });  
	} 
	 function creaChart6(){
		 var chart = new Highcharts.Chart({
		        chart: {
		            renderTo:'lose',
		            //backgroundColor: '#CFCFCF',
		             //borderColor:'red',  
		            type: 'column'
		           // marginRight: 10,
		        },
		        title: {
		            text: ''
		        },
		        xAxis: {
		            categories: [${winDetail.interfaceName}],
		            labels: { 
	                    rotation: 90 
	                } 
		      },
		        yAxis: {
		            min: 0,
		            title: {
		                text: ''
		            },
		            stackLabels: {
		                enabled:false,
		                style: {
		                    fontWeight: 'bold',
		                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		                }
		            }
		        },
		        legend: {
		            align: 'right',
		            x: -70,
		            verticalAlign: 'top',
		            y: 20,
		            floating: true,
		            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
		            borderColor: '#CCC',
		            borderWidth: 1,
					 enabled:false,
		            shadow: false
		        },
		        tooltip: {
		            formatter: function() {
		                return '<b>'+ this.x +'</b><br>'+
		                    this.series.name +': '+ this.y +'<br>'+
		                    'Total: '+ this.point.stackTotal;
		            }
		        },
		        credits: {
		            enabled: false
	            },
		        plotOptions: {
		            column: {
		                stacking: 'normal',
		                dataLabels: {
		                    enabled: false,
		                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
		                }
		            }
		        },
		        series: [{
		            name: '发送d丢包率',
		            data:  [${winDetail.sendLose}] 
		        }, {
		            name: '接收丢包率',
		            data:[${winDetail.arriveLose}]
		        }
			   ]
		    });  
		}  */
	 
	 
	 function AddRunningDiv() {
         $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(document).height() }).appendTo("body");
         $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候...").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(document).height() - 45) / 2 });
     }
	 
	 function MoveRunningDiv() {
         $("div[class='datagrid-mask']").remove();
         $("div[class='datagrid-mask-msg']").remove();
     }
	 
var m = 120; //定时更新记时器
	var refresh; //字时更新对象
	//定时更新数据
	function reFresh() {		
			if (m >= 1) {
				$("#mes").html(
						"<b>"
								+ m + "</b> 秒后更新数据 ");

				refresh = setTimeout("reFresh()", 1000);
				m--;
			} else {
				parent.mainFrame.location.href='${ctx}/monitorWIN/detailWIN.action?winId=${monwt.winId}';
			}
		

	}
</script>
</head>
<body style="margin-top:2px;margin-left: 2px" onLoad="creaChart();">
	<!--tab 页切换-->
	<div id="tabs-setting"
		style="width: 100%;border: none; margin: 2px auto">
		<ul style="background: #EDEEF3; ">
			<li><a href="#wholerisk" id="switch1" style="cursor: pointer;">基本信息</a>
			</li>
			<li><a href="#propertyspread" id="switch2"
				style="cussor: pointer">指标明细</a></li>

<li><span id="mes" style="color: red;margin-left: 200px;"></span>
	<input type="button" value="刷新" class="btnstyle"
								style="margin-left:400px;margin-top:2px" onclick="location.href='${ctx}/monitorWIN/detailWIN.action?winId=${monwt.winId}';"/>
<input type="button" value="返回" class="btnstyle"
								style="margin-top:2px" onclick="location.href='${ctx}/monitorWIN/queryAll.action';"/>
</li>
		</ul>


		<div id="wholerisk" style="overflow:auto">

			
						<table width="100%" cellSpacing="1" cellpadding="6" border="0">
							<tr>
								<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend>进本信息</legend>
											<table width="100%" style="height:200px">
												<tr>
													<td>监控对象名称</td>
													<td> ${monwt.winName}</td>
												</tr>
												<tr>
													<td>监控对象IP地址</td>
													<td>${monwt.winIp} </td>
												</tr>
												<tr>
													<td>监控类别</td>
													<td>Windows</td>
												</tr>
												 <tr>
													<td>主机名称</td>
													<td>${winDetail.hostName}</td>
												</tr> 
												<tr>
													<td>运行时间</td>
													<td>${winDetail.timeSpan}</td>
												</tr> 
											</table>
										</fieldset>
									</div></td>
									
									<td style="height:20%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend><b>cpu利用率</b></legend>
											<div id="cpuUse" style="height:200px;overflow:auto;">
											</div>
										</fieldset>
									</div></td>
								

							</tr>
							<tr>
								<td width="50%">
									<div>
										<fieldset style="width:530px;padding:10px 10px 5px 10px;">
											<legend><b>内存利用率</b></legend>
											<div id="memoryUse" style="height:200px;  overflow:auto">
												
											</div>
										</fieldset>
									</div></td>
								<td width="50%">
									<div>
										<fieldset style="width:530px; padding:10px 10px 5px 10px;">
											<legend><b>存储空间利用率</b></legend>
											<div id="diskUse" style="height:200px"></div>
										</fieldset>
									</div></td>
							</tr>
			</table>
			<table width="100%" cellSpacing="1" cellpadding="6" border="0">
			<tr>
							<td width="100%">
									<div>
										<fieldset style="width:530; padding:10px 10px 5px 10px;">
											<legend><b>发送/接收流量</b></legend>
											<div id="flow" style="height:200px"></div>
										</fieldset>
									</div></td>
							</tr>
							<tr>
							<td width="100%">
									<div>
										<fieldset style="width:530; padding:10px 10px 5px 10px;">
											<legend><b>发送/接收错误率</b></legend>
											<div id="error" style=""></div>
										</fieldset>
									</div></td>
							</tr>
							<tr>
							<td width="100%">
									<div>
										<fieldset style="width:530; padding:10px 10px 5px 10px;">
											<legend><b>发送/接收丢包率</b></legend>
											<div id="lose" style="" ></div>
										</fieldset>
									</div></td>
							</tr>
			</table>
		</div>
<!-- 第二个tab页：事件统计 -->
		
		<!-- 第三个tab页：资产分布 -->
		<div id="propertyspread">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				class="tab2">
				<thead>
					<tr height="28">
						<th width="20%">安装软件</th>
						<th width="28%">安装服务</th>
						<th width="28%">运行进程</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="infoDetail" status="stat">
						<tr>
						<td valign="middle">
						${allSofts}	
								</td>
								<td valign="middle">
									${allServices}
								</td>
								<td valign="middle">
								${allProcess}
								</td>
							</tr>
						</s:iterator>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>

