<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>预算分布</title>
<link href="${ctx}/css/basic/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/scripts/report/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/highcharts.js"></script>
<script type="text/javascript" src="${ctx}/scripts/report/exporting.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(function () {
    var chart;
    $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'column'
            },
            title: {
                text: '任务类型-部门预算分析(万元)'
            },
            xAxis: {
                categories: ['产品购买与集成', '设施建设与改造', '安全咨询与服务', '内部工作改进', '培养与意识教育','信息安全治理与组织']
            },
            yAxis: {
                min: 0,
                title: {
                    text: '',
                    align: 'high'
                }
            },
            legend: {
                layout: 'vertical',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 100,
                y: 70,
                floating: true,
                shadow: true
            },
            tooltip: {
            	formatter: function() {
                    return ''+
                        this.x +': '+ this.y ;
                }
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
             series: [{
                name: '新闻中心',
                data: [1.6,0.8,26.0,3.2,7.3,10.1]
            }, {
                name: '播出部',
                data: [6.2,14.8,16.3,2.2,4.0,6.1]
            }, {
                name: '技术中心',
                data: [3.1,1.9,2.6,10.3,6.4,13.0]
            },  {
                name: '总编室',
                data: [3.4,6.4,8.6,8.3,10.1,3.2]
            },{
                name: '总控系统',
                data: [6.2,8.3,12.6,4.0,6.0,11.2]
            }, {
                name: '媒体资产管理系统',
                data: [8.1,3.9,12.6,3.6,5.4,8.3]
            }]
        });
        
        
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'containerChar',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '任务类型预算分布情况'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
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
                            return '<b>'+ this.point.name +'</b>('+ this.y +'): '+ this.percentage.toFixed(1) +' %';
                        }
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'share',
                data: [
                    ['产品购买与集成',   51.2],
                    ['设施建设与改造',   50.8],
                    ['安全咨询与服务',   98.9],
                    ['内部工作改进',     49.7],
                    ['培养与意识教育',	  54.8],
                    ['信息安全治理与组织', 89.5]
                ]
            }]
        });
    });
    
});
		</script>
</head>
<body bgcolor="#E9F3FE">
	<div id="containerChar" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	<div>
		<table border="0" width="99.5%" align="center" class="tab2" cellpadding="0" cellspacing="1">
		<center><h1>任务类型预算分布(单位:万元)</h1></center>
			<tr align="center">
				<th width="10%" class="biaoti"></th>
				<th class="biaoti">产品购买与集成</th>
				<th class="biaoti">设施建设与改造</th>
				<th class="biaoti">安全咨询与服务</th>
				<th class="biaoti">内部工作改进</th>
				<th class="biaoti">培养与意识教育</th>
				<th class="biaoti">信息安全治理与组织</th>
			</tr>
			<tr  align="center">
				<td>新闻中心</td>
				<td>1.6</td>
				<td>0.8</td>
				<td>26.0</td>
				<td>3.2</td>
				<td>7.3</td>
				<td>10.1</td>
			</tr>
				<tr  align="center">
				<td>播出部</td>
				<td>6.2</td>
				<td>14.8</td>
				<td>16.3</td>
				<td>2.2</td>
				<td>4.0</td>
				<td>6.1</td>
			</tr>
				<tr  align="center">
				<td>技术中心</td>
				<td>3.1</td>
				<td>1.9</td>
				<td>2.6</td>
				<td>10.3</td>
				<td>6.4</td>
				<td>13.0</td>
			</tr>
				<tr  align="center">
				<td>总编室</td>
				<td>3.4</td>
				<td>6.4</td>
				<td>8.6</td>
				<td>8.3</td>
				<td>10.1</td>
				<td>3.2</td>
			</tr>
				<tr  align="center">
				<td>总控系统</td>
				<td>6.2</td>
				<td>8.3</td>
				<td>12.6</td>
				<td>4.0</td>
				<td>6.0</td>
				<td>11.2</td>
			</tr>
				<tr  align="center">
				<td>媒体资产管理系统</td>
				<td>8.1</td>
				<td>3.9</td>
				<td>12.6</td>
				<td>3.6</td>
				<td>5.4</td>
				<td>8.3</td>
			</tr>
			<tr  align="center">
				<td  style="background-color: #F6F9FE;">任务数合计</td>
				<td style="background-color: #F6F9FE;">28.6</td>
				<td style="background-color: #F6F9FE;">36.1</td>
				<td style="background-color: #F6F9FE;">78.7</td>
				<td style="background-color: #F6F9FE;">31.6</td>
				<td style="background-color: #F6F9FE;">39.2</td>
				<td style="background-color: #F6F9FE;">51.9</td>
			</tr>
		</table>
	</div>
	
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>
