<%@page language="java" contentType="application/msword;charset=GBK"%>
<%
String fileName="[JCSJ]等级保护差距分析总体报告.doc";
String sn = new String(fileName.getBytes("gbk"),"UTF-8");
response.setHeader("Content-Disposition","attachment; filename="+sn);
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:o="urn:schemas-microsoft-com:office:office"
	xmlns:w="urn:schemas-microsoft-com:office:word"
	xmlns="http://www.w3.org/TR/REC-html40">
<head>

<title>[COMPLIANCE]</title>
<!--[if gte mso 9]><xml><w:WordDocument><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><w:DisplayHorizontalDrawingGridEvery>0</w:DisplayHorizontalDrawingGridEvery><w:DisplayVerticalDrawingGridEvery>2</w:DisplayVerticalDrawingGridEvery><w:DocumentKind>DocumentNotSpecified</w:DocumentKind><w:DrawingGridVerticalSpacing>7.8</w:DrawingGridVerticalSpacing><w:View>Web</w:View><w:Compatibility></w:Compatibility><w:Zoom>0</w:Zoom></w:WordDocument></xml><![endif]-->
<style>
@font-face {
	font-family: "Times New Roman";
}

@font-face {
	font-family: "&#23435;&#20307;";
}

@font-face {
	font-family: "Cambria";
}

@font-face {
	font-family: "Symbol";
}

@font-face {
	font-family: "Arial";
}

@font-face {
	font-family: "&#40657;&#20307;";
}

@font-face {
	font-family: "Courier New";
}

@font-face {
	font-family: "Wingdings";
}

@font-face {
	font-family: "Cambria Math";
}

@font-face {
	font-family: "&#24494;&#36719;&#38597;&#40657;";
}

@font-face {
	font-family: "unknown";
}

@font-face {
	font-family: "Calibri";
}

@font-face {
	font-family: "Times New Roman CE";
}

@font-face {
	font-family: "Times New Roman Cyr";
}

@font-face {
	font-family: "Times New Roman Greek";
}

@font-face {
	font-family: "Times New Roman Tur";
}

@font-face {
	font-family: "Times New Roman (Hebrew)";
}

@font-face {
	font-family: "Times New Roman (Arabic)";
}

@font-face {
	font-family: "Times New Roman Baltic";
}

@font-face {
	font-family: "Times New Roman (Vietnamese)";
}

@font-face {
	font-family: "Arial CE";
}

@font-face {
	font-family: "Arial Cyr";
}

@font-face {
	font-family: "Arial Greek";
}

@font-face {
	font-family: "Arial Tur";
}

@font-face {
	font-family: "Arial (Hebrew)";
}

@font-face {
	font-family: "Arial (Arabic)";
}

@font-face {
	font-family: "Arial Baltic";
}

@font-face {
	font-family: "Arial (Vietnamese)";
}

@font-face {
	font-family: "SimSun Western";
}

@font-face {
	font-family: "SimHei Western";
}

@font-face {
	font-family: "Cambria Math CE";
}

@font-face {
	font-family: "Cambria Math Cyr";
}

@font-face {
	font-family: "Cambria Math Greek";
}

@font-face {
	font-family: "Cambria Math Tur";
}

@font-face {
	font-family: "Cambria Math Baltic";
}

@font-face {
	font-family: "Cambria Math (Vietnamese)";
}

@font-face {
	font-family: "Cambria CE";
}

@font-face {
	font-family: "Cambria Cyr";
}

@font-face {
	font-family: "Cambria Greek";
}

@font-face {
	font-family: "Cambria Tur";
}

@font-face {
	font-family: "Cambria Baltic";
}

@font-face {
	font-family: "Cambria (Vietnamese)";
}

@font-face {
	font-family: "Microsoft YaHei Western";
}

@font-face {
	font-family: "Microsoft YaHei CE";
}

@font-face {
	font-family: "Microsoft YaHei Cyr";
}

@font-face {
	font-family: "Microsoft YaHei Greek";
}

@font-face {
	font-family: "Microsoft YaHei Tur";
}

@font-face {
	font-family: "Calibri CE";
}

@font-face {
	font-family: "Calibri Cyr";
}

@font-face {
	font-family: "Calibri Greek";
}

@font-face {
	font-family: "Calibri Tur";
}

@font-face {
	font-family: "Calibri Baltic";
}

@font-face {
	font-family: "Calibri (Vietnamese)";
}

p.p0 {
	margin: 0pt;
	margin-bottom: 0.0001pt;
	margin-bottom: 0pt;
	margin-top: 0pt;
	color: rgb(0, 0, 0);
	font-size: 12.0000pt;
	font-family: '&#23435;&#20307;';
}

h1 {
	margin-bottom: 10.0000pt;
	margin-top: 10.0000pt;
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 22.0000pt;
	font-family: 'Arial';
}

h2 {
	margin-bottom: 10.0000pt;
	margin-top: 10.0000pt;
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 16.0000pt;
	font-family: 'Cambria';
}

h3 {
	margin-bottom: 10.0000pt;
	margin-top: 10.0000pt;
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 16.0000pt;
	font-family: 'Arial';
}

span.10 {
	font-size: 10.0000pt;
	font-family: 'Times New Roman';
}

span.15 {
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 22.0000pt;
	font-family: 'Arial';
}

span.16 {
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 16.0000pt;
	font-family: 'Cambria';
}

span.17 {
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 16.0000pt;
	font-family: 'Arial';
}

span.18 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.19 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: '&#23435;&#20307;';
}

span.20 {
	color: rgb(0, 0, 0);
	font-size: 10.0000pt;
	font-family: 'Arial';
}

span.21 {
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 10.0000pt;
	font-family: 'Arial';
}

span.22 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.23 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.24 {
	font-size: 10.5000pt;
	font-family: 'Times New Roman';
}

span.25 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: '&#23435;&#20307;';
}

span.26 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.27 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.28 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.29 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: '&#23435;&#20307;';
}

span.30 {
	color: rgb(0, 0, 0);
	font-size: 12.0000pt;
	font-family: 'Arial';
}

span.31 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.32 {
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

span.33 {
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 12.0000pt;
	font-family: 'Arial';
}

p.p34 {
	margin-bottom: 0pt;
	margin-top: 0pt;
	border-bottom: 0.7500pt solid rgb(0, 0, 0);
	mso-border-bottom-alt: 0.7500pt solid rgb(0, 0, 0);
	padding: 0pt 0pt 1pt 0pt;
	text-align: center;
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

p.p35 {
	margin-bottom: 0pt;
	margin-top: 0pt;
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: '&#23435;&#20307;';
}

p.p36 {
	margin-bottom: 0pt;
	margin-top: 0pt;
	color: rgb(0, 0, 0);
	font-size: 12.0000pt;
	font-family: 'Arial';
}

p.p37 {
	margin-bottom: 0pt;
	margin-top: 0pt;
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

p.p38 {
	margin-bottom: 0pt;
	margin-top: 0pt;
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: '&#23435;&#20307;';
}

p.p39 {
	margin-bottom: 0pt;
	margin-top: 0pt;
	color: rgb(0, 0, 0);
	font-size: 9.0000pt;
	font-family: 'Arial';
}

p.p40 {
	margin-bottom: 0pt;
	margin-top: 0pt;
	color: rgb(0, 0, 0);
	font-weight: bold;
	font-size: 12.0000pt;
	font-family: 'Arial';
}

@page {
	mso-page-border-surround-header: no;
	mso-page-border-surround-footer: no;
}

@page Section0 {
	margin-top: 72.0000pt;
	margin-bottom: 72.0000pt;
	margin-left: 89.0000pt;
	margin-right: 89.0000pt;
	size: 595.3500pt 842.0000pt;
}

div.Section0 {
	page: Section0;
}
</style>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function onclickAction() {

		location.href = "${ctx}/gapReportDocuments/getGapReportDocumentsC.action";
	}
</script>
</head>
<body style="tab-interval: 36pt;">
	<input type="button" onclick="onclickAction();" value="导出成word文档">
	<!--StartFragment-->
	<div class="Section0">
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';">[COMPLIANCE]</span><span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 28.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#31561;&#32423;&#20445;&#25252;&#24046;&#36317;&#35780;&#20272;&#24635;&#20307;&#25253;&#21578;</span><span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: '&amp;#24494;&amp;#36719;&amp;#38597;&amp;#40657;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-size: 28.0000pt; font-family: 'unknown';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-bottom: 0pt; margin-top: 0pt; text-align: center;">
			<span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[2012</span><span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24180;</span><span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#40657;&amp;#20307;';">11</span><span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#26376;</span><span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#40657;&amp;#20307;';">30</span><span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#26085;</span><span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#40657;&amp;#20307;';">]</span><span
				style="mso-spacerun: 'yes'; font-size: 16.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h1 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#40657;&amp;#20307;';"></span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 22.0000pt; font-family: 'Arial';">1.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 22.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#35780;&#20272;&#27010;&#36848;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 22.0000pt; font-family: 'Arial';"><o:p></o:p>
			</span>
		</h1>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">1.1.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#35780;&#20272;&#30446;&#30340;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2007</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24180;&#22235;&#37096;&#22996;&#27491;&#24335;&#21457;&#24067;&#12298;&#20449;&#24687;&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#31649;&#29702;&#21150;&#27861;&#12299;&#65288;&#20844;&#36890;&#23383;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[2007]&nbsp;43</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#21495;&#65289;&#65292;&#35201;&#27714;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20449;&#24687;&#31995;&#32479;&#36816;&#33829;&#12289;&#20351;&#29992;&#21333;&#20301;&#24212;&#24403;&#20381;&#25454;&#26412;&#21150;&#27861;&#21644;&#12298;&#20449;&#24687;&#31995;&#32479;&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#23450;&#32423;&#25351;&#21335;&#12299;&#30830;&#23450;&#20449;&#24687;&#31995;&#32479;&#30340;&#23433;&#20840;&#20445;&#25252;&#31561;&#32423;&#65307;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20449;&#24687;&#31995;&#32479;&#36816;&#33829;&#12289;&#20351;&#29992;&#21333;&#20301;&#24212;&#24403;&#25353;&#29031;&#12298;&#20449;&#24687;&#31995;&#32479;&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#23454;&#26045;&#25351;&#21335;&#12299;&#20855;&#20307;&#23454;&#26045;&#31561;&#32423;&#20445;&#25252;&#24037;&#20316;&#65307;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20449;&#24687;&#31995;&#32479;&#36816;&#33829;&#12289;&#20351;&#29992;&#21333;&#20301;&#21450;&#20854;&#20027;&#31649;&#37096;&#38376;&#24212;&#24403;&#23450;&#26399;&#23545;&#20449;&#24687;&#31995;&#32479;&#23433;&#20840;&#29366;&#20917;&#12289;&#23433;&#20840;&#20445;&#25252;&#21046;&#24230;&#21450;&#25514;&#26045;&#30340;&#33853;&#23454;&#24773;&#20917;&#36827;&#34892;&#33258;&#26597;&#65288;&#31532;&#19977;&#32423;&#20449;&#24687;&#31995;&#32479;&#24212;&#24403;&#27599;&#24180;&#33267;&#23569;&#36827;&#34892;&#19968;&#27425;&#33258;&#26597;&#65292;&#31532;&#22235;&#32423;&#20449;&#24687;&#31995;&#32479;&#24212;&#24403;&#27599;&#21322;&#24180;&#33267;&#23569;&#36827;&#34892;&#19968;&#27425;&#33258;&#26597;&#65292;&#31532;&#20116;&#32423;&#20449;&#24687;&#31995;&#32479;&#24212;&#24403;&#20381;&#25454;&#29305;&#27530;&#23433;&#20840;&#38656;&#27714;&#36827;&#34892;&#33258;&#26597;&#65289;&#12290;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 10.0000pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36827;&#34892;&#24046;&#36317;&#35780;&#20272;&#30340;&#30446;&#30340;&#26159;&#22312;&#23436;&#25104;&#20449;&#24687;&#31995;&#32479;&#23450;&#32423;&#21518;&#25110;&#27599;&#27425;&#33258;&#26597;&#26102;&#65292;&#20381;&#25454;&#20449;&#24687;&#31995;&#32479;&#25152;&#23646;&#30340;&#23433;&#20840;&#20445;&#25252;&#32423;&#21035;&#35201;&#27714;&#23545;&#30456;&#24212;&#30340;&#20449;&#24687;&#31995;&#32479;&#36827;&#34892;&#35780;&#20272;&#65292;&#20197;&#20415;&#20840;&#38754;&#12289;&#23436;&#25972;&#22320;&#20102;&#35299;&#20449;&#24687;&#31995;&#32479;&#31561;&#32423;&#20445;&#25252;&#35201;&#27714;&#30340;&#22522;&#26412;&#23433;&#20840;&#25511;&#21046;&#22312;&#20449;&#24687;&#31995;&#32479;&#20013;&#30340;&#37197;&#32622;&#24773;&#20917;&#20197;&#21450;&#31995;&#32479;&#30340;&#25972;&#20307;&#23433;&#20840;&#24615;&#65292;&#21516;&#26102;&#21457;&#29616;&#19982;&#12298;&#20449;&#24687;&#31995;&#32479;&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#22522;&#26412;&#35201;&#27714;&#12299;&#20043;&#38388;&#30340;&#24046;&#36317;&#24182;&#20998;&#26512;&#20854;&#23548;&#33268;&#21407;&#22240;&#65292;&#26126;&#30830;&#19979;&#19968;&#27493;&#36827;&#34892;&#25972;&#25913;&#30340;&#38656;&#27714;&#19982;&#20869;&#23481;&#12290;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">1.2.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#35780;&#20272;&#20381;&#25454;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 8.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#26412;&#27425;&#24046;&#36317;&#35780;&#20272;&#24037;&#20316;&#25152;&#20381;&#25454;&#30340;&#25991;&#20214;&#21644;&#26631;&#20934;&#22914;&#19979;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#12298;&#24191;&#25773;&#30005;&#35270;&#23433;&#20840;&#25773;&#20986;&#31649;&#29702;&#35268;&#23450;&#12299;&#65288;&#24635;&#23616;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">62</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#21495;&#20196;&#65289;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#12298;&#24191;&#25773;&#30005;&#35270;&#30456;&#20851;&#20449;&#24687;&#31995;&#32479;&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#27979;&#35780;&#35201;&#27714;&#12299;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#12298;&#24191;&#25773;&#30005;&#35270;&#30456;&#20851;&#20449;&#24687;&#31995;&#32479;&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#23450;&#32423;&#25351;&#21335;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">-</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#21457;&#24067;&#29256;&#12299;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#12298;&#24191;&#25773;&#30005;&#35270;&#30456;&#20851;&#20449;&#24687;&#31995;&#32479;&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#22522;&#26412;&#35201;&#27714;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">-</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#21457;&#24067;&#29256;&#12299;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[TeamCola</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#23450;&#32423;&#25253;&#21578;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[windows8</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#23433;&#20840;&#31561;&#32423;&#20445;&#25252;&#23450;&#32423;&#25253;&#21578;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">1.3.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#35780;&#20272;&#33539;&#22260;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#27492;&#27425;&#24046;&#36317;&#35780;&#20272;&#25152;&#21253;&#25324;&#30340;&#20449;&#24687;&#31995;&#32479;&#21450;&#20854;&#23433;&#20840;&#20445;&#25252;&#31561;&#32423;&#22914;&#19979;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<table border="1">
			<tr>
				<td>序号</td>
				<td>信息系统名称</td>
				<td>定级情况</td>
				<td>是否完成差距评估</td>
			</tr>
			<c:choose>
				<c:when test="${empty listproject}">
					<tr>
						<td colspan="4">没有符合条件的数据</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${listproject}" var="project"
						varStatus="projects">
						<tr>
							<td>${projects.index+1}</td>
							<td>${project.caName}</td>
							<td>${project.casysGrade}</td>
							<td>已完成</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#35828;&#26126;&#65306;&#23545;&#20110;&#26410;&#23436;&#25104;&#24046;&#36317;&#35780;&#20272;&#30340;&#20449;&#24687;&#31995;&#32479;&#65292;&#19981;&#22312;&#27492;&#25253;&#21578;&#27719;&#24635;&#20043;&#20869;&#12290;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">1.4.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#35780;&#20272;&#26041;&#27861;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#35780;&#20272;&#30340;&#20027;&#35201;&#26041;&#24335;&#26377;&#65306;&#20154;&#21592;&#35775;&#35848;&#12289;&#25991;&#26723;&#26816;&#26597;&#12289;&#25216;&#26415;&#26680;&#26597;&#21644;&#24037;&#20855;&#27979;&#35797;&#12290;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20154;&#21592;&#35775;&#35848;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36890;&#36807;&#35810;&#38382;&#12289;&#20132;&#27969;&#30340;&#26041;&#24335;&#65292;&#26816;&#26597;&#31995;&#32479;&#36816;&#34892;&#30456;&#20851;&#24037;&#20316;&#20154;&#21592;&#22312;&#23454;&#38469;&#24037;&#20316;&#20013;&#23545;&#23433;&#20840;&#31649;&#29702;&#35268;&#31456;&#21046;&#24230;&#30340;&#35748;&#30693;&#31243;&#24230;&#21644;&#25191;&#34892;&#24773;&#20917;&#65307;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#25991;&#26723;&#26816;&#26597;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36890;&#36807;&#26816;&#26597;&#30456;&#20851;&#25991;&#26723;&#21450;&#20449;&#24687;&#23433;&#20840;&#31649;&#29702;&#21046;&#24230;&#65292;&#39564;&#35777;&#24050;&#26377;&#25991;&#26723;&#19982;&#22522;&#26412;&#35201;&#27714;&#30340;&#31526;&#21512;&#31243;&#24230;&#65307;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#25216;&#26415;&#26680;&#26597;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#38024;&#23545;&#31995;&#32479;&#30340;&#19981;&#21516;&#23618;&#38754;&#65292;&#20998;&#21035;&#20174;&#29289;&#29702;&#12289;&#20027;&#26426;&#12289;&#32593;&#32476;&#12289;&#24212;&#29992;&#21644;&#25968;&#25454;&#20116;&#20010;&#23618;&#38754;&#65292;&#26816;&#26597;&#31995;&#32479;&#23433;&#20840;&#21151;&#33021;&#30340;&#35774;&#32622;&#21644;&#23454;&#29616;&#24773;&#20917;&#65292;&#39564;&#35777;&#31995;&#32479;&#20013;&#35774;&#32622;&#30340;&#23433;&#20840;&#21151;&#33021;&#26159;&#21542;&#26377;&#25928;&#22320;&#21457;&#25381;&#20316;&#29992;&#65307;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="margin-left: 36.0000pt; text-indent: -12.0000pt; margin-bottom: 0pt; margin-top: 10.0000pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&#9679;&nbsp;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24037;&#20855;&#27979;&#35797;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36890;&#36807;&#21033;&#29992;&#19987;&#29992;&#30340;&#23433;&#20840;&#24037;&#20855;&#23545;&#31995;&#32479;&#30340;&#23433;&#20840;&#21151;&#33021;&#35201;&#27714;&#20197;&#21450;&#22914;&#20309;&#27491;&#30830;&#26377;&#25928;&#23454;&#26045;&#36825;&#20123;&#21151;&#33021;&#30340;&#20445;&#35777;&#35201;&#27714;&#36827;&#34892;&#27979;&#35797;&#65292;&#20197;&#39564;&#35777;&#31995;&#32479;&#22312;&#25216;&#26415;&#26041;&#38754;&#36798;&#21040;&#30340;&#23433;&#20840;&#31243;&#24230;&#26159;&#21542;&#31526;&#21512;&#34987;&#27979;&#31995;&#32479;&#19994;&#21153;&#38656;&#27714;&#21644;&#23433;&#20840;&#38450;&#25252;&#38656;&#27714;&#12290;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h1 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 22.0000pt; font-family: 'Arial';">2.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 22.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#35780;&#20272;&#32467;&#26524;&#32508;&#36848;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 22.0000pt; font-family: 'Arial';"><o:p></o:p>
			</span>
		</h1>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">2.1.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20869;&#23481;&#19982;&#24037;&#20316;&#37327;&#32479;&#35745;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#26412;&#27425;&#24046;&#36317;&#35780;&#20272;&#38024;&#23545;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[JCSJ]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#30340;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[TeamCola][windows8]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20849;&#35745;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[2]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20010;&#20449;&#24687;&#31995;&#32479;&#20998;&#21035;&#20174;&#29289;&#29702;&#23433;&#20840;&#12289;&#25216;&#26415;&#23433;&#20840;&#12289;&#31649;&#29702;&#23433;&#20840;&#19977;&#37096;&#20998;&#23545;&#30456;&#20851;&#20449;&#24687;&#31995;&#32479;&#36827;&#34892;&#31561;&#32423;&#20445;&#25252;&#31526;&#21512;&#24230;&#20998;&#26512;&#65292;&#28041;&#21450;&#21040;&#22522;&#30784;&#32593;&#32476;&#23433;&#20840;&#12289;&#36793;&#30028;&#23433;&#20840;&#12289;&#32456;&#31471;&#31995;&#32479;&#23433;&#20840;&#12289;&#26381;&#21153;&#31471;&#31995;&#32479;&#23433;&#20840;&#12289;&#24212;&#29992;&#23433;&#20840;&#12289;&#25968;&#25454;&#23433;&#20840;&#19982;&#22791;&#20221;&#24674;&#22797;&#12289;&#23433;&#20840;&#31649;&#29702;&#20013;&#24515;&#31561;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&nbsp;[508]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20010;&#25511;&#21046;&#39033;&#12290;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<table border="1">

			<tr>
				<td>安全领域</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.sysname}</td>
				</c:forEach>
				<td>合计</td>
			</tr>


			<tr>
				<td>基础网络安全</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.jichu}</td>
				</c:forEach>
				<td>${summ1}</td>
			</tr>

			<tr>
				<td>边界安全</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.bianjie}</td>
				</c:forEach>
				<td>${summ2}</td>
			</tr>

			<tr>
				<td>终端系统安全</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.zhongduan}</td>
				</c:forEach>
				<td>${summ3}</td>
			</tr>

			<tr>
				<td>服务端系统安全</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.fuwu}</td>
				</c:forEach>
				<td>${summ4}</td>
			</tr>

			<tr>
				<td>应用安全</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.yingyong}</td>
				</c:forEach>
				<td>${summ5}</td>
			</tr>

			<tr>
				<td>数据安全域备份恢复</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.shuju}</td>
				</c:forEach>
				<td>${summ6}</td>
			</tr>

			<tr>
				<td>安全管理中心</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>${project.anquan}</td>
				</c:forEach>
				<td>${summ7}</td>
			</tr>

			<tr>
				<td>通用物理安全</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>0</td>
				</c:forEach>
				<td>0</td>
			</tr>
			<tr>
				<td>通用管理安全</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">

					<td>0</td>
				</c:forEach>
				<td>0</td>
			</tr>
			<tr>
				<td>合计</td>
				<c:forEach items="${listReportDocument}" var="project"
					varStatus="projects">
					<td>${project.sum}</td>
				</c:forEach>
				<td>${summSum}</td>
			</tr>
		</table>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">2.2.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20449;&#24687;&#31995;&#32479;&#24635;&#20307;&#31526;&#21512;&#24230;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#21508;&#20449;&#24687;&#31995;&#32479;&#19982;&#20043;&#30456;&#23545;&#24212;&#30340;&#31561;&#32423;&#20445;&#25252;&#35201;&#27714;&#30456;&#27604;&#36739;&#30340;&#24635;&#20307;&#31526;&#21512;&#24230;&#35265;&#19979;&#34920;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<table border="1">
			<tr>
				<th rowspan="2">序号</th>
				<th rowspan="2">信息系统名称</th>
				<th rowspan="2">定级情况</th>
				<th colspan="4">符合度</th>
			</tr>
			<tr>


				<td>总体符合度</td>
				<td>物理符合度</td>
				<td>技术符合度</td>
				<td>管理符合度</td>
			</tr>
			<c:forEach items="${listGeneralPhysical}" var="project"
				varStatus="projects">
				<tr>

					<td>${projects.index+1}</td>
					<td>${project.sysname}</td>
					<td>${project.sysgrade}</td>
					<td>10</td>
					<td>${project.physicalPercentage}%</td>
					<td>${project.technologyPercentage}%</td>
					<td>${project.managementPercentage}%</td>
				</tr>
			</c:forEach>
		</table>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<img width="560" height="373"
				src="chajubaogao.files/chajubaogao-1652.png"> <span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">2.3.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#23433;&#20840;&#39046;&#22495;&#31526;&#21512;&#24230;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#21508;&#20449;&#24687;&#31995;&#32479;&#19982;&#31561;&#32423;&#20445;&#25252;&#35201;&#27714;&#30340;&#21508;&#23433;&#20840;&#39046;&#22495;&#30456;&#27604;&#36739;&#30340;&#31526;&#21512;&#24230;&#35265;&#19979;&#34920;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<table border="1">
			<tr>
				<th>安全领域</th>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<th>${project.sysname}</th>
				</c:forEach>

			</tr>
			<tr>
				<td>基础网络安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.jichuPertage}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>边界安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.bianjiePertage}%</td>
				</c:forEach>
			</tr>


			<tr>
				<td>基础网络安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.jichuPertage}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>终端系统安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.zhongduanPertage}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>服务端系统安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.fuwuduanPertage}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>应用安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.yingyongPertage}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>数据安全域备份恢复</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.shujuPertage}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>安全管理中心</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>${project.anquanPertage}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>通用物理安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>0</td>
				</c:forEach>
			</tr>

			<tr>
				<td>通用管理安全</td>
				<c:forEach items="${listSecurityTable}" var="project"
					varStatus="projects">
					<td>0</td>
				</c:forEach>
			</tr>
		</table>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<img width="560" height="373"
				src="chajubaogao.files/chajubaogao-1939.png"> <span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h1 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 22.0000pt; font-family: 'Arial';">3.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 22.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#20449;&#24687;&#31995;&#32479;&#24046;&#36317;&#35780;&#20272;&#32467;&#26524;&#27719;&#24635;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 22.0000pt; font-family: 'Arial';"><o:p></o:p>
			</span>
		</h1>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">3.1.[TeamCola]</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<h3 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Arial';">3.1.1.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24635;&#20307;&#31526;&#21512;&#24230;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Arial';"><o:p></o:p>
			</span>
		</h3>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36890;&#36807;&#23545;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[TeamCola]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24403;&#21069;&#22312;&#31561;&#32423;&#20445;&#25252;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[3]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#32423;&#30340;&#20061;&#20010;&#39046;&#22495;&#30456;&#24212;&#25511;&#21046;&#39033;&#30340;&#36880;&#19968;&#35780;&#20215;&#65292;&#24471;&#21040;&#20102;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[TeamCola]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24635;&#20307;&#31526;&#21512;&#24230;&#35265;&#19979;&#22270;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<img width="560" height="327"
				src="chajubaogao.files/chajubaogao-2047.png"> <span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h3 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Arial';">3.1.2.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#20998;&#24067;&#22270;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Arial';"><o:p></o:p>
			</span>
		</h3>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[TeamCola]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#21333;&#20803;&#32479;&#35745;&#22914;&#19979;&#34920;&#25152;&#31034;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<table border="1">
			<tr>
				<th>安全领域</th>
				<th>标准控制单元</th>
				<th>适用控制单元</th>
				<th>符合单元</th>
				<th>部分符合单元</th>
				<th>不符合单元</th>
				<th>差距单元</th>
				<th>符合度</th>

			</tr>
			<tr>
				<td>基础网络安全</td>
				<c:forEach items="${listGapStatisticsUnit}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>边界安全</td>
				<c:forEach items="${listGapStatisticsUnitBianJie}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>


			<tr>
				<td>终端系统安全</td>
				<c:forEach items="${listGapStatisticsUnitZhongDuan}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>服务端系统安全</td>
				<c:forEach items="${listGapStatisticsUnitFuWu}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>应用安全</td>
				<c:forEach items="${listGapStatisticsUnitYingYong}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>
			
			
			
			<tr>
				<td>数据安全域备份恢复</td>
				<c:forEach items="${listGapStatisticsUnitShuJu}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>安全管理中心</td>
				<c:forEach items="${listGapStatisticsUnitShuJu}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>
			<tr>
				<td>通用物理安全</td>
				<c:forEach items="${listGapStatisticsUnitShuJu}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>

			<tr>
				<td>通用管理安全</td>
				<c:forEach items="${listGapStatisticsUnitShuJu}" var="project"
					varStatus="projects">
					<td>${project.bianzhun}</td>
					<td>${project.shiyong}</td>
					<td>${project.fuhe}</td>
					<td>${project.bufenfuhe}</td>
					<td>${project.bufuhe}</td>
					<td>${project.chaju}</td>
					<td>${project.fuhedu}%</td>
				</c:forEach>
			</tr>
		</table>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<img width="528" height="373"
				src="chajubaogao.files/chajubaogao-2381.png"> <span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h2 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Cambria';">3.2.[windows8]</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Cambria';"><o:p></o:p>
			</span>
		</h2>
		<h3 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Arial';">3.2.1.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24635;&#20307;&#31526;&#21512;&#24230;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Arial';"><o:p></o:p>
			</span>
		</h3>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36890;&#36807;&#23545;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[windows8]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24403;&#21069;&#22312;&#31561;&#32423;&#20445;&#25252;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[3]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#32423;&#30340;&#20061;&#20010;&#39046;&#22495;&#30456;&#24212;&#25511;&#21046;&#39033;&#30340;&#36880;&#19968;&#35780;&#20215;&#65292;&#24471;&#21040;&#20102;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[windows8]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24635;&#20307;&#31526;&#21512;&#24230;&#35265;&#19979;&#22270;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<img width="560" height="327"
				src="chajubaogao.files/chajubaogao-2474.png"> <span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<h3 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: 'Arial';">3.2.2.</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: bold; font-size: 16.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#20998;&#24067;&#22270;</span><span
				style="mso-spacerun: 'yes'; color: rgb(0, 0, 0); font-weight: normal; font-size: 16.0000pt; font-family: 'Arial';"><o:p></o:p>
			</span>
		</h3>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';">[windows8]</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#21333;&#20803;&#32479;&#35745;&#22914;&#19979;&#34920;&#25152;&#31034;&#65306;</span><span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0
			style="text-indent: 24.0000pt; margin-bottom: 0pt; margin-top: 0pt; line-height: 22.0000pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 12.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<table align=center
			style="border-collapse: collapse; margin-left: 2.9000pt; mso-table-layout-alt: fixed; padding: 0.0000pt 2.5000pt 0.0000pt 2.5000pt;">
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#23433;&#20840;&#39046;&#22495;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#26631;&#20934;&#25511;&#21046;&#21333;&#20803;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36866;&#29992;&#25511;&#21046;&#21333;&#20803;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&nbsp;</span><span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#31526;&#21512;&#21333;&#20803;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#37096;&#20998;&#31526;&#21512;&#21333;&#20803;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&nbsp;</span><span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#19981;&#31526;&#21512;&#21333;&#20803;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&nbsp;</span><span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24046;&#36317;&#21333;&#20803;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: 0.5000pt solid rgb(128, 128, 128); mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-weight: bold; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#31526;&#21512;&#24230;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#22522;&#30784;&#32593;&#32476;&#23433;&#20840;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">100%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36793;&#30028;&#23433;&#20840;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">6</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">6</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">50.00%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#32456;&#31471;&#31995;&#32479;&#23433;&#20840;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">&nbsp;5</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">5</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">60.00%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#26381;&#21153;&#31471;&#31995;&#32479;&#23433;&#20840;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">7</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">7</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">5</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">71.42%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#24212;&#29992;&#23433;&#20840;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">7</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">6</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">4</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">5</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">16.66%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#25968;&#25454;&#23433;&#20840;&#22495;&#22791;&#20221;&#24674;&#22797;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0.00%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#23433;&#20840;&#31649;&#29702;&#20013;&#24515;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">3</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">2</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">66.66%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36890;&#29992;&#29289;&#29702;&#23433;&#20840;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">6</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">6</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">5</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">0</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">1</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(255, 255, 255);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">83.33%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td width=123 valign=center
					style="width: 92.8000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: 31.8750pt none rgb(255, 255, 255); mso-border-left-alt: 31.8750pt none rgb(255, 255, 255); border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#23435;&amp;#20307;';">&#36890;&#29992;&#31649;&#29702;&#23433;&#20840;</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: center; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">32</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.4000pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">30</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">10</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">5</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">15</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">20</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
				<td width=61 valign=center
					style="width: 46.3500pt; padding: 2.5000pt 2.5000pt 2.5000pt 2.5000pt; border-left: none;; mso-border-left-alt: none;; border-right: 31.8750pt none rgb(255, 255, 255); mso-border-right-alt: 31.8750pt none rgb(255, 255, 255); border-top: none;; mso-border-top-alt: 0.5000pt solid rgb(128, 128, 128); border-bottom: 0.5000pt solid rgb(128, 128, 128); mso-border-bottom-alt: 0.5000pt solid rgb(128, 128, 128); background: rgb(211, 223, 238);">
					<p class=p0
						style="margin-bottom: 0pt; margin-top: 0pt; text-align: right; line-height: 16.0000pt;">
						<span
							style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';">33.33%</span><span
							style="font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
						</span>
					</p>
				</td>
			</tr>
		</table>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
		<!--  
			<img width="528" height="373"	src="chajubaogao.files/chajubaogao-2810.png">
			-->
		<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
		<p class=p0 style="margin-bottom: 0pt; margin-top: 0pt;">
			<span
				style="mso-spacerun: 'yes'; font-size: 10.0000pt; font-family: '&amp;#40657;&amp;#20307;';"><o:p></o:p>
			</span>
		</p>
	</div>
	<!--EndFragment-->
</body>
</html>