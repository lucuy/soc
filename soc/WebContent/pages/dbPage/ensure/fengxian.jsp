<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>部门风险分布</title>
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
            renderTo: 'containerchar',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '任务风险情况分布'
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
                ['高风险',   77],
                ['中风险',   45],
                ['低风险',	51]
            ]
        }]
    });
	
	
	chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'bar'
        },
        title: {
            text: '风险-部门任务分布情况'
        },
        xAxis: {
            categories: [ 	'新闻中心',
                             '播出部',
                            '技术中心',
                             '总编室',
                        	 '总控系统',
                        	'媒体资产管理系统'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            formatter: function() {
                return ''+
                    this.series.name +': '+ this.y ;
            }
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -100,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '部门任务数',
            data: [13,12,12,29,23,32]
        }, {
        	name: '高风险',
            data: [0,3,8,1,17,22]
        }, {
        	name: '中风险',
            data: [5,7,3,8,3,7]
        }, {
            name: '低风险',
            data: [8,2,1,20,3,3]
        }]
    });
	
	
});
});
</script>
  </head>
  <body bgcolor="#E9F3FE">
  <div id="containerchar" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
  <div>
  	<table width="99.5%" border="0" class="tab2" cellpadding="0" cellspacing="1">
  		<center><h1>部门风险分布</h1></center>
  		<tr align="center"> 
  			<th class="biaoti">部门名称</th>
  			<th class="biaoti">部门任务数</th>
  			<th class="biaoti">高风险</th>
  			<th class="biaoti">中风险</th>
  			<th class="biaoti">低风险</th>
  		</tr>
  			
  		<tr  align="center">
  			<td>新闻中心</td><td>13</td><td>0</td><td>5</td><td>8</td>
  		</tr>
  		<tr  align="center">
  			<td>播出部</td><td>12</td><td>3</td><td>7</td><td>2</td>
  		</tr>
  		<tr  align="center">
  			<td>技术中心</td><td>12</td><td>8</td><td>3</td><td>1</td>
  		</tr>
  		<tr  align="center">
  			<td>总编室</td><td>29</td><td>1</td><td>8</td><td>20</td>
  		</tr>
  		<tr  align="center">
  			<td>总控系统</td><td>23</td><td>17</td><td>3</td><td>3</td>
  		</tr>
  		<tr  align="center">
  			<td>媒体资产管理系统</td><td>32</td><td>22</td><td>7</td><td>3</td>
  		</tr>
  		<tr bgcolor="red"  align="center">
  			<td style="background-color: #F6F9FE;">合计:</td>
  			<td style="background-color: #F6F9FE;">121</td>
  			<td style="background-color: #F6F9FE;">51</td>
  			<td style="background-color: #F6F9FE;">33</td>
  			<td style="background-color: #F6F9FE;">37</td>
  		</tr>
  	
  	</table>
  
  
  </div>
  <div id="container" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
  
  
  </body>
</html>
