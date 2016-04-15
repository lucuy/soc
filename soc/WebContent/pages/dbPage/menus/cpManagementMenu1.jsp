<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>合规管理</title>
<link rel="stylesheet" href="${ctx}/css/leftTree.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.min.js"></script>	
<script type="text/javascript" src="${ctx}/scripts/jquery.tree.js"></script>	
<script type="text/javascript">
$(function(){
	$('#files').tree({
		expanded: 'li:first'
	});
});
</script>		
 
</head>
<body>	
<table width="100%" cellSpacing="0" cellPadding="0">
  <tbody>
    <tr>
      <td width=10 height=29><img src="${ctx}/images/bg_left_tl.gif"/></td>
      <td width="180"  style=" FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system; width:*">
      </td>
      <td width=10><img src="${ctx}/images/bg_left_tr.gif"/></td>
    </tr>
    <tr>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_ls.gif)"></td>
      <td id="menuTree" style="PADDING-RIGHT: 10px; PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign="top">
    
    <div >
    
    <ul id="files">
	<li><a href="javascript:void(0);">差距评估</a>
		<ul>
		     <li><a href="javascript:void(0);">通用物理差距评估</a>
		     <ul>
					<li><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.1.2&PSAD_FatherSort=10.1.1" target="mainFrame" title="10.1物理位置的选择">10.1物理位置的选择</a></li>
					<li><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.2.2&PSAD_FatherSort=10.2.1" target="mainFrame" title="10.2物理访问控制">10.2物理访问控制</a></li>
					<li><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.3.2&PSAD_FatherSort=10.3.1" target="mainFrame" title="10.3防盗窃和防破坏">10.3防盗窃和防破坏</a></li>
					<li><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.4.2&PSAD_FatherSort=10.4.1" target="mainFrame" title="10.4机房环境">10.4机房环境</a></li>
					<li><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.5.2&PSAD_FatherSort=10.5.1" target="mainFrame" title="10.5机房消防设施">10.5机房消防设施</a></li>
					<li><a href="${ctx}/psadInfo/psadAction.action?psadFatherSort=10.6.2&PSAD_FatherSort=10.6.1" target="mainFrame" title="10.6电力供应">10.6电力供应</a></li>
				</ul>
		     </li>
		     <li><a href="javascript:void(0);">通用管理差距评估</a>
		     <ul>
					<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.1.2&MSAD_FatherSort=11.1.1" target="mainFrame" title="11.1安全管理总体要求">11.1安全管理总体要求</a></li>
					<li><a href="javascript:void(0);">11.2安全管理机构</a>
						<ul>
						    <li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.1.2&MSAD_FatherSort=11.2.1.1" target="mainFrame" title="11.2.1岗位设置">11.2.1岗位设置</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.2.2&MSAD_FatherSort=11.2.2.1" target="mainFrame" title="11.2.2授权和审批">11.2.2授权和审批</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.3.2&MSAD_FatherSort=11.2.3.1" target="mainFrame" title="11.2.3沟通和合作">11.2.3沟通和合作</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.4.2&MSAD_FatherSort=11.2.4.1" target="mainFrame" title="11.2.4审核和检查">11.2.4审核和检查</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.5.2&MSAD_FatherSort=11.2.5.1" target="mainFrame" title="11.2.5制度管理">11.2.5制度管理</a></li>
						</ul>
					</li>
					
					<li><a href="javascript:void(0);">11.3人员安全管理</a>
						<ul>
						    <li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.1.2&MSAD_FatherSort=11.3.1.1" target="mainFrame" title="11.3.1人员上岗">11.3.1人员上岗</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.2.2&MSAD_FatherSort=11.3.2.1" target="mainFrame" title="11.3.2人员离岗">11.3.2人员离岗</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.3.2&MSAD_FatherSort=11.3.3.1" target="mainFrame" title="11.3.3培训与考核">11.3.3培训与考核</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.4.2&MSAD_FatherSort=11.3.4.1" target="mainFrame" title="11.3.4外部人员访问管理">11.3.4外部人员访问管理</a></li>
						</ul>
					</li>
					
					<li><a href="javascript:void(0);">11.4系统建设管理</a>
						<ul>
						    <li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.1.2&MSAD_FatherSort=11.4.1.1" target="mainFrame" title="11.4.1系统定级">11.4.1系统定级</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.2.2&MSAD_FatherSort=11.4.2.1" target="mainFrame" title="11.4.2安全方案设计">11.4.2安全方案设计</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.3.2&MSAD_FatherSort=11.4.3.1" target="mainFrame" title="11.4.3产品采购和使用">11.4.3产品采购和使用</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.4.2&MSAD_FatherSort=11.4.4.1" target="mainFrame" title="11.4.4自行软件开发">11.4.4自行软件开发</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.5.2&MSAD_FatherSort=11.4.5.1" target="mainFrame" title="11.4.5外包软件开发">11.4.5外包软件开发</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.6.2&MSAD_FatherSort=11.4.6.1" target="mainFrame" title="11.4.6工程实施">11.4.6工程实施</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.7.2&MSAD_FatherSort=11.4.7.1" target="mainFrame" title="11.4.7测试验收">11.4.7测试验收</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.8.2&MSAD_FatherSort=11.4.8.1" target="mainFrame" title="11.4.8系统交付">11.4.8系统交付</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.9.2&MSAD_FatherSort=11.4.9.1" target="mainFrame" title="11.4.9系统备案">11.4.9系统备案</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.10.2&MSAD_FatherSort=11.4.10.1" target="mainFrame" title="11.4.10等级测评">11.4.10等级测评</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.11.2&MSAD_FatherSort=11.4.11.1" target="mainFrame" title="11.4.11安全服务商选择">11.4.11安全服务商选择</a></li>
						</ul>
					</li>
					
                     <li><a href="javascript:void(0);">11.5系统运维管理</a>
						<ul>
						    <li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.1.2&MSAD_FatherSort=11.5.1.1" target="mainFrame" title="11.5.1环境管理">11.5.1环境管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.2.2&MSAD_FatherSort=11.5.2.1" target="mainFrame" title="11.5.2资产管理">11.5.2资产管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.3.2&MSAD_FatherSort=11.5.3.1" target="mainFrame" title="11.5.3介质管理">11.5.3介质管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.4.2&MSAD_FatherSort=11.5.4.1" target="mainFrame" title="11.5.4设备管理">11.5.4设备管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.5.2&MSAD_FatherSort=11.5.5.1" target="mainFrame" title="11.5.5网络安全管理">11.5.5网络安全管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.6.2&MSAD_FatherSort=11.5.6.1" target="mainFrame" title="11.5.6系统安全管理">11.5.6系统安全管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.7.2&MSAD_FatherSort=11.5.7.1" target="mainFrame" title="11.5.7恶意代码防范管理">11.5.7恶意代码防范管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.8.2&MSAD_FatherSort=11.5.8.1" target="mainFrame" title="11.5.8密码管理">11.5.8密码管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.9.2&MSAD_FatherSort=11.5.9.1" target="mainFrame" title="11.5.9变更管理">11.5.9变更管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.10.2&MSAD_FatherSort=11.5.10.1" target="mainFrame" title="11.5.10备份与恢复管理">11.5.10备份与恢复管理</a></li>
							<li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.11.2&MSAD_FatherSort=11.5.11.1" target="mainFrame" title="11.5.11安全事件处置">11.5.11安全事件处置</a></li>
					        <li><a href="${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.12.2&MSAD_FatherSort=11.5.12.1" target="mainFrame" title="11.5.12应急预案管理">11.5.12应急预案管理</a></li>
						</ul>
					</li>
				</ul>
		     </li>
		     <li><a href="${ctx}/pages/cpManage/technology/skip.jsp" target="mainFrame" title="技术差距评估">技术差距评估</a></li>
			<li><a href="javascript:void(0);">差距评估报告</a>
				<ul>
					<li><a href="${ctx}/gapAnalysisSchedule/projectShowcase.action" target="mainFrame" title="差距评估进度">差距评估进度</a></li>
					<li><a href="${ctx}/pages/ensure/chajubaogaoTo.jsp" target="mainFrame" title="差距评估总体报告">差距评估总体报告</a></li>
				</ul>
			</li>
		</ul>
	</li>
	<li><a href="javascript:void(0);">整改需求</a>
	   <ul>
	      <li><a href="javascript:void(0);">整改需求汇总</a>
	          <ul>
	             <li><a href="${ctx}/pages/cpManage/demand/demandCollect/pageTo.jsp" target="mainFrame" title="技术差距评估">技术差距评估</a></li>
	             <li><a href="${ctx}/pages/cpManage/gpaShow/gpaShowPageTo.jsp" target="mainFrame" title="通用物理差距评估">通用物理差距评估</a></li>
	             <li><a href="${ctx}/pages/cpManage/msahShow/msaShowPageTo.jsp" target="mainFrame" title="通用管理差距评估">通用管理差距评估</a></li>
	          </ul>
	       </li>
	      <li><a href="javascript:void(0);">整改建议</a>
	      	 <ul>
	             <li><a href="${ctx}/pages/cpManage/demand/rectificationProposal/pageTo.jsp" target="mainFrame" title="技术差距评估">技术差距评估</a></li>
	             <li><a href="${ctx}/pages/cpManage/gpaShow/gpaRectPageTo.jsp" target="mainFrame" title="通用物理差距评估">通用物理差距评估</a></li>
	             <li><a href="${ctx}/pages/cpManage/msahShow/msaRectPageTo.jsp" target="mainFrame" title="通用管理差距评估">通用管理差距评估</a></li>
	          </ul>
	      </li>
	      <li><a href="javascript:void(0);">汇总分析</a>
	      	  <ul>
	             <li><a href="${ctx}/summaryAnalysis/projectShowcase.action" target="mainFrame" title="安全差距分析报告">安全差距分析报告</a></li>
	             <li><a href="${ctx}/securityControl/projectShowcase.action" target="mainFrame" title="安全控制分析报告">安全控制分析报告</a></li>
	          </ul>
	      </li>
	      <li><a href="javascript:void(0);">历史整改需求</a>
	      	  <ul>
	             <li><a href="${ctx}/pages/cpManage/demand/contrastRectification/pageTo.jsp" target="mainFrame" title="技术历史整改需求">技术整改需求</a></li>
	             <li><a href="${ctx}/pages/cpManage/gpaShow/gpaRectHistoryPageTo.jsp" target="mainFrame" title="通用物理历史整改需求">通用物理整改需求</a></li>
	             <li><a href="${ctx}/pages/cpManage/msahShow/msaRectHistoryPageTo.jsp" target="mainFrame" title="通用管理历史整改需求">通用管理整改需求</a></li>
	          </ul>
	      </li>
	   </ul>
	</li>
</ul>

</div>
    </td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_rs.gif)"></td>
    </tr>
    <tr>
      <td width=10><img src="${ctx}/images/bg_left_bl.gif"/></td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_bc.gif)"></td>
      <td width=10><img src="${ctx}/images/bg_left_br.gif"/></td>
    </tr>
  </tbody>
</table>
 
</body>
</html>