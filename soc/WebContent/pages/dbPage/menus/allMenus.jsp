<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>总列表</title>
<link href="${ctx}/styles/left.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/tree.css" rel="stylesheet" type="text/css">

<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<style type="text/css">

</style>

<link type="text/css"
    href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
    rel="stylesheet" />
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script type='text/javascript' src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/scripts/dwrManager.js"></script>

<style type="text/css">
   
   #files{
       font-size: 12px;
       margin-left: 8px;
       margin-top:-10px ; 
   }
   
   #files span {
       
       margin-top: 3px;	
       padding-left: 6px;
      
   }
   ul li {
   	list-style: none;
   }
   ul {
   	list-style: none;
   }
   
   #files span .eventext{
       <%--padding-left :-2px ;--%>
   }
   
   .common_node1{
          padding-left : 25px;
   }
   
   
</style>

	
<script type="text/javascript">
/* $(function(){
	$('#files').tree({
		expanded: 'li:first'
	});
}); */

 function onLoad(){
            var userId = '${userinfo}';
            MessagePush.pageOnLoad(userId);
       }
       
       function showMessage(val){
       		
       		if(val!='-1'){
       			parent.frames[0].LoadTimer();
       			alert(val);
       		}else{
       			parent.frames[0].NewMessage();
       		}
       		
      	 	
       }
       dwr.engine.setErrorHandler(function(){});
       /**function showmessage(val){
  		
      	}*/
/**
    打开与关闭树并改变图片
*/
	function action(elementId,eventsType)
	{
         var temp = eventsType+elementId;
        
        changeIcon($("#"+eventsType+elementId+""));
        
        $("#query_"+elementId+" >ul").toggle("slow");
	}
    function changeIcon(nainNode)
    {
        if(nainNode)
        {
            if(nainNode.attr("src").indexOf("u321_normal.gif")>=0)
            {
               nainNode.attr("src","${ctx}/images/u319_normal.gif");
               
            }else
            {
               nainNode.attr("src","${ctx}/images/u321_normal.gif");
            }
         }
     }
</script>		
 
</head>
<body onload="dwr.engine.setActiveReverseAjax(true);dwr.engine.setNotifyServerOnPageUnload(true);onLoad();">

    <div class="titleTop" style="overflow: auto">
	<div style="background-image:url(${ctx}/images/leftDh.jpg)">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="25px"><img src="${ctx}/images/jtTitle.jpg" alt=""
						width="25" height="28" />
					</td>
					<td class="leftDhxx" style="font-size: 12px; padding-bottom: 3px">合规信息管理</td>
				</tr>
			</table>
		</div>
<div  class="over " >
<table width="99%"  border="0" cellspacing="10" cellpadding="10"
			style="line-height: 15px">
  <tbody>
    
    <tr>
      <td id="menuTree" style="">
    <ul id="files" >
	    <li style="padding-top:10px;"><a href="#" class="common_node2" onclick="parent.mainFrame.location.href='${ctx}/unitInfo/query.action';"    title="单位基本情况"><img src="${ctx}/images/arrow_03.gif" /><span style="vertical-align:20%">单位基本情况</span></a>
		</li>
		<li style="padding-top:10px;"><a href="#" class="common_node2" onclick="parent.mainFrame.location.href='${ctx}/pages/dbPage/basic/manager.jsp';"   title="信息系统管理"><img src="${ctx}/images/arrow_03.gif" /><span style="vertical-align:20%">信息系统管理</span></a>
		</li>
		<li id="query_1" style="padding-top:10px;" class="common_node2">
			<a href="javascript:action('1','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup1"></a>
			<a href="javascript:action('1','img_query_assetGroup')"><span class="eventext">资产管理</span></a>
	   		<ul class="disply">
				<li style="margin-top: 5px;" class="common_node1" ><a href="${ctx}/pages/dbPage/basic/assets/softassets.jsp" target="mainFrame" title="业务应用软件">
					<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">业务应用软件</span></a>
				</li>
				<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/basic/assets/dataassets.jsp" target="mainFrame" title="关键数据类别">
					<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">关键数据类别</span></a>
				</li>
				<li style="margin-top: 5px;"class="common_node1"><a href="${ctx}/pages/dbPage/basic/assets/compassets.jsp" target="mainFrame" title="主机存储设备">
					<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">主机存储设备</span></a>
				</li>
				<li style="margin-top: 5px;"class="common_node1"><a href="${ctx}/pages/dbPage/basic/assets/networkassets.jsp" target="mainFrame" title="网络互连设备">
					<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">网络互连设备</span></a>
				</li>
				<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/basic/assets/devassets.jsp" target="mainFrame" title="安全设备">
					<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">安全设备</span></a>
				</li>
				<li style="margin-top: 5px;"class="common_node1"><a href="${ctx}/pages/dbPage/basic/assets/empassets.jsp" target="mainFrame" title="安全相关人员">
					<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">安全相关人员</span></a>
				</li>
				<li style="margin-top: 5px;"class="common_node1"><a href="${ctx}/pages/dbPage/basic/assets/docassets.jsp" target="mainFrame" title="安全管理文档">
					<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">安全管理文档</span></a>
				</li>
			</ul>
	</li>
	<!-- ------------------------------------------------------------------------------------------------------------------------------------ -->
	<li  style="padding-top:10px;">
	    <a href="#" class="common_node2" onclick="parent.mainFrame.location.href='${ctx}/rank/queryRank.action';" title="系统定级"> 
	    <img src="${ctx}/images/arrow_03.gif" /><span style="vertical-align:20%">系统定级</span></a>
	</li>    
	<!-- ------------------------------------------------------------------------------------------------------------------------------------ -->
	<li id="query_2" style="padding-top:10px;" class="common_node2">
			<a href="javascript:action('2','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup2"></a>
			<a href="javascript:action('2','img_query_assetGroup')"><span class="eventext">系统备案</span></a>
		        <ul class="disply">
		        	<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/score/record.jsp" target="mainFrame" title="系统备案">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">系统备案</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/recordHistory/query.action" target="mainFrame" title="备案历史">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">备案历史</span></a>
					</li>
				</ul>
	</li>
<!-- -------------------------------------------------------------------------------------------------------------------------------------->			
			<li  style="padding-top:10px;" class="common_node2">
			    <a href="#" onclick="parent.mainFrame.location.href='${ctx}/rankReport/query.action';"> 
			    <img src="${ctx}/images/arrow_03.gif" /><span style="vertical-align:20%">统计报告</span></a>
			</li>
<!-- -------------------------------------------------------------------------------------------------------------------------------------->
	
    		<li id="query_4" style="padding-top:10px;" class="common_node2">
				<a href="javascript:action('4','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup4"></a>
				<a href="javascript:action('4','img_query_assetGroup')"><span class="eventext">差距评估</span></a>
					<ul class="disply">
					     <li id="query_41" style="margin-top: 5px; width:160px;" class="common_node1" >
					    	 <a href="javascript:action('41','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup41"></a>
							 <a href="javascript:action('41','img_query_assetGroup')"><span class="eventext">通用物理差距评估</span></a>
					   		  <ul class="disply" >
					   			<li style="margin-top: 5px;width:200px;" class="common_node1" ><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.1.2&PSAD_FatherSort=10.1.1" target="mainFrame">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">10.1物理位置的选择</span></a>
								</li>
								<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.2.2&PSAD_FatherSort=10.2.1" target="mainFrame">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">10.2物理访问控制</span></a>
								</li>	
								<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.3.2&PSAD_FatherSort=10.3.1" target="mainFrame">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">10.3防盗窃和防破坏</span></a>
								</li>	
								<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.4.2&PSAD_FatherSort=10.4.1" target="mainFrame">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">10.4机房环境</span></a>
								</li>	
								<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.5.2&PSAD_FatherSort=10.5.1" target="mainFrame">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">10.5机房消防设施</span></a>
								</li>	
								<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.6.2&PSAD_FatherSort=10.6.1" target="mainFrame">
									<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">10.6电力供应</span></a>
								</li>		
							 </ul>
		    			 </li>
		     			<li id="query_5" style="margin-top: 5px; width:200px;" class="common_node1" >
		     				<a href="javascript:action('5','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup5"></a>
							<a href="javascript:action('5','img_query_assetGroup')"><span class="eventext">通用管理差距评估</span></a>
			     			<ul class="disply" >
								<li class="common_node1" style="margin-top: 5px; width:200px;" class="common_node1">
								    <a href="#" onclick="parent.mainFrame.location.href='${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.1.2&MSAD_FatherSort=11.1.1';"> 
								    <img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.1安全管理总体要求</span></a>
								</li>
								<li id="query_51" class="common_node1" style="margin-top: 5px;">
									<a href="javascript:action('51','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup51"></a>
									<a href="javascript:action('51','img_query_assetGroup')"><span class="eventext">11.2安全管理机构</span></a>
									<ul class="disply">
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.1.2&MSAD_FatherSort=11.2.1.1" target="mainFrame" title="11.2.1岗位设置">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.2.1岗位设置</span></a>
										</li>
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.2.2&MSAD_FatherSort=11.2.2.1" target="mainFrame" title="11.2.2授权和审批">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.2.2授权和审批</span></a>
										</li>
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.3.2&MSAD_FatherSort=11.2.3.1" target="mainFrame" title="11.2.3沟通和合作">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.2.3沟通和合作</span></a>
										</li>
										<li style="margin-top: 5px;" class="common_node1" ><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.4.2&MSAD_FatherSort=11.2.4.1" target="mainFrame" title="11.2.4审核和检查">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.2.4审核和检查</span></a>
										</li>
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.5.2&MSAD_FatherSort=11.2.5.1" target="mainFrame" title="11.2.5制度管理">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.2.5制度管理</span></a>
										</li>
									</ul>
								</li>
					
								<li id="query_6" style="margin-top: 5px;"  class="common_node1">
									<a href="javascript:action('6','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup6"></a>
									<a href="javascript:action('6','img_query_assetGroup')"><span class="eventext">11.3人员安全管理</span></a>	
									<ul class="disply">
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.1.2&MSAD_FatherSort=11.3.1.1" target="mainFrame" >
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.3.1人员上岗</span></a>
										</li>
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.2.2&MSAD_FatherSort=11.3.2.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.3.2人员离岗</span></a>
										</li>
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.3.2&MSAD_FatherSort=11.3.3.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.3.3培训与考核</span></a>
										</li>
										<li style="margin-top: 5px; width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.4.2&MSAD_FatherSort=11.3.4.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.3.4外部人员访问管理</span></a>
										</li>
									</ul>
								</li>
								<li id="query_7" style="margin-top: 5px;" class="common_node1">
									<a href="javascript:action('7','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup7"></a>
									<a href="javascript:action('7','img_query_assetGroup')"><span class="eventext">11.4系统建设管理</span></a>	
									<ul class="disply">
										<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.1.2&MSAD_FatherSort=11.4.1.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.1系统定级</span></a>
										</li>
										<li style="margin-top: 5px; width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.2.2&MSAD_FatherSort=11.4.2.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.2安全方案设计</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.3.2&MSAD_FatherSort=11.4.3.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.3产品采购和使用</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.4.2&MSAD_FatherSort=11.4.4.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.4自行软件开发</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.5.2&MSAD_FatherSort=11.4.5.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.5外包软件开发</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.6.2&MSAD_FatherSort=11.4.6.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.6工程实施</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.7.2&MSAD_FatherSort=11.4.7.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.7测试验收</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.8.2&MSAD_FatherSort=11.4.8.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.8系统交付</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.9.2&MSAD_FatherSort=11.4.9.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.9系统备案</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.10.2&MSAD_FatherSort=11.4.10.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.10等级测评</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.11.2&MSAD_FatherSort=11.4.11.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.4.11安全服务商选择</span></a>
										</li>
									</ul>
								</li>
								
			                     <li id="query_8" style="margin-top: 5px;" class="common_node1">
			                     	 <a href="javascript:action('8','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup8"></a>
									 <a href="javascript:action('8','img_query_assetGroup')"><span class="eventext">11.5系统运维管理</span></a>	
									<ul class="disply">
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.1.2&MSAD_FatherSort=11.5.1.1"  target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext" style="vertical-align:top">11.5.1环境管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.2.2&MSAD_FatherSort=11.5.2.1"  target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext" style="vertical-align:top">11.5.2资产管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.3.2&MSAD_FatherSort=11.5.3.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext" style="vertical-align:top">11.5.3介质管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.4.2&MSAD_FatherSort=11.5.4.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext" style="vertical-align:top">11.5.4设备管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.5.2&MSAD_FatherSort=11.5.5.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.5网络安全管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.6.2&MSAD_FatherSort=11.5.6.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.6系统安全管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.7.2&MSAD_FatherSort=11.5.7.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.7恶意代码防范管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.8.2&MSAD_FatherSort=11.5.8.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.8密码管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.9.2&MSAD_FatherSort=11.5.9.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.9变更管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.10.2&MSAD_FatherSort=11.5.10.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.10备份与恢复管理</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.11.2&MSAD_FatherSort=11.5.11.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.11安全事件处置</span></a>
										</li>
										<li style="margin-top: 5px;width:200px;" class="common_node1"><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.12.2&MSAD_FatherSort=11.5.12.1" target="mainFrame">
											<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">11.5.12应急预案管理</span></a>
										</li>
									</ul>
								</li>
							</ul>
					     </li>
		     <li class="common_node1" style="margin-top: 5px;"> 
			    <a href="#"  onclick="parent.mainFrame.location.href='${ctx}/pages/dbPage/cpManage/technology/skip.jsp'"> 
			    <img src="${ctx}/images/arrow_03.gif" /><span style="vertical-align:20%" >技术差距评估</span></a>
			</li>
			<li id="query_9" style="margin-top: 5px;" class="common_node1">
				<a href="javascript:action('9','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup9"></a>
				<a href="javascript:action('9','img_query_assetGroup')"><span class="eventext">差距评估报告</span></a>
				<ul class="disply">
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/gapAnalysisSchedule/projectShowcase.action"  target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">差距评估进度</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/ensure/chajubaogaoTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">差距评估总体报告</span></a>
					</li>
				</ul>
			</li>
		</ul>
	</li>
	<!-- -------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- ---------- -->
	<li id="query_10" style="padding-top:10px;" class="common_node2">
			<a href="javascript:action('10','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup10"></a>
			<a href="javascript:action('10','img_query_assetGroup')"><span class="eventext">整改需求</span></a>
	   <ul  class="disply">
	      <li id="query_101" style="margin-top: 5px; " class="common_node1">
	      	  <a href="javascript:action('101','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup101"></a>
			  <a href="javascript:action('101','img_query_assetGroup')"><span class="eventext">整改需求汇总</span></a>
	          <ul  class="disply">
	         		<li style="margin-top: 5px; width:180px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/demand/demandCollect/pageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">技术差距评估</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/gpaShow/gpaShowPageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">通用物理差距评估</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/msahShow/msaShowPageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">通用管理差距评估</span></a>
					</li>
	          </ul>
	       </li>
	      <li id="query_102" style="margin-top: 5px;" class="common_node1">
	      	  <a href="javascript:action('102','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup102"></a>
			  <a href="javascript:action('102','img_query_assetGroup')"><span class="eventext">整改建议</span></a>
	      	 <ul class="disply">
	      	 		<li style="margin-top: 5px;width:180px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/demand/rectificationProposal/pageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">技术差距评估</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/gpaShow/gpaRectPageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">通用物理差距评估</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/msahShow/msaRectPageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">通用管理差距评估</span></a>
					</li>
	          </ul>
	      </li>
	      <li id="query_103" style="margin-top: 5px;" class="common_node1">
	          <a href="javascript:action('103','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup103"></a>
			  <a href="javascript:action('103','img_query_assetGroup')"><span class="eventext">汇总分析</span></a>
	      	  <ul  class="disply">
	      	  		<li style="margin-top: 5px;width:180px;" class="common_node1"><a href="${ctx}/summaryAnalysis/projectShowcase.action" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">安全差距分析报告</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/securityControl/projectShowcase.action" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">安全控制分析报告</span></a>
					</li>
	          </ul>
	      </li>
	      <li id="query_104" style="margin-top: 5px;" class="common_node1" >
	      	  <a href="javascript:action('104','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup104"></a>
			  <a href="javascript:action('104','img_query_assetGroup')"><span class="eventext">历史整改需求</span></a>
	      	  <ul class="disply">
	      	 		<li style="margin-top: 5px;width:180px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/demand/contrastRectification/pageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">技术历史整改需求</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/gpaShow/gpaRectHistoryPageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">通用物理历史整改需求</span></a>
					</li>
					<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/pages/dbPage/cpManage/msahShow/msaRectHistoryPageTo.jsp" target="mainFrame">
						<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">通用管理历史整改需求</span></a>
					</li>
	          </ul>
	      </li>
	   </ul>
	</li>
    <!-- ------------------------------------------------------------------------------------------------------------------------------------ -->			
			<li id="query_3" style="padding-top:10px;" class="common_node2">
				<a href="javascript:action('3','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup3"></a>
				<a href="javascript:action('3','img_query_assetGroup')"><span class="eventext">系统废止</span></a>
				<ul class="disply">
				   <c:forEach items="${systemManagers}" var="obj">
				      <li style="margin-top: 5px;" class="common_node1"><img src="${ctx}/images/arrow_03.gif" />&nbsp;<a href="${ctx}/sysAbolish/querySysInFo.action?pkSysInfo=${obj.id}" target="mainFrame" title="${obj.sysName}">${obj.sysName}</a></li>
				   </c:forEach>  
				</ul>
			</li>
		<!-- ---------------------------------------->
			<li id="query_105" style="padding-top:10px;" class="common_node2">
				<a href="javascript:action('105','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup105"></a>
				<a href="javascript:action('105','img_query_assetGroup')"><span class="eventext">文件管理</span></a>
	   			<ul class="disply">
					<li id="query_106" style="margin-top: 5px;" class="common_node1" >
	      	  			<a href="javascript:action('106','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup106"></a>
			  			<a href="javascript:action('106','img_query_assetGroup')"><span class="eventext">系统定级文档</span></a>
	   					<ul class="disply">
	   						<li style="margin-top: 5px;width:180px;" class="common_node1">
	   							<a href="${ctx}/files/filesshow.action?filetype=2" target="mainFrame" title="frankfiles">
	   							<img src="${ctx}/images/arrow_03.gif" />&nbsp;等级测评单位信息文档</a>
	   						</li>
	   						<li style="margin-top: 5px;width:180px;" class="common_node1">
	   							<a href="${ctx}/files/filesshow.action?filetype=3" target="mainFrame" title="rankfiles">
	   							<img src="${ctx}/images/arrow_03.gif" />&nbsp;系统定级报告</a>
	   						</li>
							<li style="margin-top: 5px;width:180px;" class="common_node1">
								<a href="${ctx}/files/filesshow.action?filetype=4" target="mainFrame" title="topfiles">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;系统拓扑结构说明文档</a>
							</li>
							<li style="margin-top: 5px;width:180px;" class="common_node1">
								<a href="${ctx}/files/filesshow.action?filetype=5" target="mainFrame" title="rulesfiles">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;系统安全组织机构及管理制度文档</a>
							</li>
							<li style="margin-top: 5px;width:180px;" class="common_node1">
								<a href="${ctx}/files/filesshow.action?filetype=6" target="mainFrame" title="planfiles">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;系统安全保护实施设计实施方案或改建实施方案</a>
							</li>
							<li style="margin-top: 5px;width:180px;" class="common_node1">
								<a href="${ctx}/files/filesshow.action?filetype=7" target="mainFrame" title="licencefiles">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;系统使用的安全产品清单及认证、销售许可证明</a>
							</li>
							<li style="margin-top: 5px;width:180px;" class="common_node1">
								<a href="${ctx}/files/filesshow.action?filetype=8" target="mainFrame" title="rankfiles">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;系统等级测评报告</a>
							</li>
							<li style="margin-top: 5px;width:180px;" class="common_node1">
								<a href="${ctx}/files/filesshow.action?filetype=9" target="mainFrame" title="professionfiles">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;专家评审情况</a>
							</li>
							<li style="margin-top: 5px;width:180px;" class="common_node1">
								<a href="${ctx}/files/filesshow.action?filetype=10" target="mainFrame" title="supriorfiles">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;上级主管部门审批意见</a>
							</li>
						</ul>
				</li>
			</ul>
				<ul class="disply">
				    <li id="query_107" style="margin-top: 5px;" class="common_node1" >
	      	  			<a href="javascript:action('107','img_query_assetGroup');"><img src="/soc/images/u321_normal.gif" id="img_query_assetGroup107"></a>
			  			<a href="javascript:action('107','img_query_assetGroup')"><span class="eventext">系统废止文档</span></a>
	      	  			<ul class="disply">
	      	 				<li style="margin-top: 5px;width:180px;" class="common_node1"><a href="${ctx}/files/filesshow.action?filetype=11" target="mainFrame" title="infodelete">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">信息删除证明</span></a>
							</li>
							<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/files/filesshow.action?filetype=12" target="mainFrame" title="devclean">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">设备清除证明</span></a>
							</li>
							<li style="margin-top: 5px;" class="common_node1"><a href="${ctx}/files/filesshow.action?filetype=13" target="mainFrame">
								<img src="${ctx}/images/arrow_03.gif" />&nbsp;<span class="eventext">存储清除证明</span></a>
							</li>
	          		</ul>
	      </li>
				</ul>
			</li>
			<!--------------------------------------------------------------->
     </ul>
    </td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_rs.gif)"></td>
    </tr>
    
  </tbody>
</table>
</div>
 </div>
</body>
</html>