<?xml version="1.0" encoding="UTF-8"?>
<xml-body>
	<securityId>序号</securityId>
	<securityTitle>标题</securityTitle>
	<publisher>发布人</publisher>
	<securityDate>发布时间</securityDate>
	<source>来源</source>
	<#list list as security>
		<security>
			<securityTitle><![CDATA[${security.securityTitle}]]></securityTitle>
			<publisher><![CDATA[${security.publisher}]]></publisher>
			<securityDate><![CDATA[${security.securityDate?string("yyyy-MM-dd")}]]></securityDate>
			<securityDetails><![CDATA[${security.securityDetails}]]></securityDetails>
			<source><![CDATA[${security.source}]]></source>
			<securityCreateDate><![CDATA[${security.securityCreateDate?string("yyyy-MM-dd")}]]></securityCreateDate>
		</security>
	</#list>
</xml-body>