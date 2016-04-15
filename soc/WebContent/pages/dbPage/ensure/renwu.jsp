<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html>
<head>
<title>任务类型分析</title>
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
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '任务类型分布情况'
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
                ['产品购买与集成',   6],
                ['设施建设与改造',   14],
                ['安全咨询与服务',  	16],
                ['内部工作改进',   2],
                ['培训与意识教育',	4],
                ['信息安全治理与组织',   6]
            ]
        }]
    });
	
});
});
</script>
  </head>
  <body style="background-color: #FFFFFF">
  	<div id="container" style="min-width: 400px; height: 600px; margin: 0 auto"></div>
  <div>
  	<table width="99.5%" border="0" class="tab2" cellpadding="0" cellspacing="1">
  	<center><h1>任务类型分布情况</h1></center>
  		<tr align="center">
  			<th class="biaoti">部门名称</th>
  			<th class="biaoti">产品购买与集成</th>
  			<th class="biaoti">设施建设与改造</th>
  			<th class="biaoti">安全咨询与服务</th>
  			<th class="biaoti">内部工作改进</th>
  			<th class="biaoti">培训与意识教育</th>
  			<th class="biaoti">信息安全治理与组织</th>
  			<th class="biaoti">任务数合计</th>
  		</tr>
  		<tr  align="center">
  			<td>新闻中心</td><td>2</td><td>0</td><td>26</td><td>3</td><td>7</td><td>10</td><td style="background-color: #F6F9FE;">48</td>
  			</tr><tr align="center">
  			<td>播出部</td><td>6</td><td>14</td><td>16</td><td>2</td><td>4</td><td>6</td><td style="background-color: #F6F9FE;">48</td>
  			</tr><tr align="center">
  			<td>技术中心</td><td>3</td><td>1</td><td>2</td><td>10</td><td>6</td><td>13</td><td style="background-color: #F6F9FE;">35</td>
  			</tr><tr align="center">
  			<td>总编室</td><td>3</td><td>6</td><td>8</td><td>8</td><td>10</td><td>3</td><td style="background-color: #F6F9FE;">38</td>
  			</tr><tr align="center">
  			<td>总控系统</td><td>6</td><td>8</td><td>12</td><td>4</td><td>6</td><td>11</td><td style="background-color: #F6F9FE;">47</td>
  			</tr><tr align="center">
  			<td>媒体资产管理系统</td><td>8</td><td>3</td><td>12</td><td>3</td><td>5</td><td>8</td><td style="background-color: #F6F9FE;">39</td>
  		</tr>
  	
  	</table>
  
  
  </div>
  
  </body>
</html>
