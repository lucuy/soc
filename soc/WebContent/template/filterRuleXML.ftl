<?xml version="1.0" encoding="UTF-8"?>
<xml-body>
	<#list list as filterRule>
		<filterRule>
	<priority><![CDATA[${filterRule.priorityIds}]]></priority>
	<asset><![CDATA[${filterRule.assetIds}]]></asset>
	<category><![CDATA[${filterRule.eventsCategoryIds}]]></category>
	<type><![CDATA[${filterRule.eventsTypeIds}]]></type>
	<sourceIP><![CDATA[equal=${filterRule.sourceAddrEqual}|between=${filterRule.sourceAddrBetween}|notEqual=${filterRule.sourceAddrNotEqual}]]></sourceIP>
	<targetIP><![CDATA[equal=${filterRule.targetAddrEqual}|between=${filterRule.targetAddrBetween}|notEqual=${filterRule.targetAddrNotEqual}]]></targetIP>
	<sourcePort><![CDATA[equal=${filterRule.sourcePortEqual}|between=${filterRule.sourcePortBetween}|notEqual=${filterRule.sourcePortNotEqual}]]></sourcePort>
	<targetPort><![CDATA[equal=${filterRule.targetPortEqual}|between=${filterRule.targetPortBetween}|notEqual=${filterRule.targetPortNotEqual}]]></targetPort>
		</filterRule>
	</#list>
</xml-body>