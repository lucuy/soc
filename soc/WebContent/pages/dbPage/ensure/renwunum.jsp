<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>部门任务类数量分布</title>
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
            text: '任务数量部门分布'
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
                ['新闻中心',   13],
                ['播出部',   12],
                ['技术中心',   12],
                ['总编室',   29],
                ['总控系统',   23],
                ['媒体资产管理系统',   32]
            ]
        }]
    });
	
	
	chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'bar'
        },
        title: {
            text: '部门任务-差距项分布情况'
        },
        xAxis: {
            categories: ['新闻中心',
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
            name: '差距项数量',
            data: [22,24,17,29,7,16]
        }, {
            name: '部门任务数',
            data: [13,12,12,29,23,32]
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
  	<center><h1>任务数量部门分布</h1></center>
  		<tr align="center">
  			<th class="biaoti">部门名称</th>
  			<th class="biaoti">任务数量</th>
  			<th class="biaoti">差距项数量</th>
  		</tr>	
  		<tr  align="center">
  			<td>新闻中心</td><td>13</td><td>22</td>
  		</tr>
  		<tr  align="center">
  			<td>播出部</td><td>12</td><td>24</td>
  		</tr>
  		<tr  align="center">
  			<td>技术中心</td><td>12</td><td>17</td>
  		</tr>
  		<tr  align="center">
  			<td>总编室</td><td>29</td><td>29</td>
  		</tr>
  		<tr  align="center">
  			<td>总控系统</td><td>23</td><td>7</td>
  		</tr>
  		<tr  align="center">
  			<td>媒体资产管理系统</td><td>32</td><td>16</td>
  		</tr>
  	
  	</table>
  
  
  </div>
  
  <div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
  </body>
</html>
