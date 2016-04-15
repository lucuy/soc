<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
	<head>
	 <meta http-equiv="Cache-Control" content="private" />
	<style type="type=text/css">
	{
	FONT-SIZE: 12px
}
#menuTree A {
	COLOR: #566984; TEXT-DECORATION: none
}
	</style>

	<link rel="stylesheet" href="${ctx}/css/left.css" type="text/css"></link>
	<link rel="stylesheet" href="${ctx}/css/tree.css" type="text/css"></link>
	 <script type="text/javascript" src="${ctx}/treeview/lib/jquery.js"></script>
<script type="text/javascript" src="${ctx}/treeview/jquery.treeview.js"></script>
<script type="text/javascript" src="${ctx}/treeview/screen.js"></script>
 <script type="text/javascript" src="${ctx}/js/Left.files/TreeNode.js"></script> 
 <script type="text/javascript" src="${ctx}/js/Left.files/Tree.js"></script>
         <script language="javascript">
			function policyTo(url){
				parent.mainFrame.location.href=url;
			}
		</script>
		
	</head>

	<body class="titleTop" >
	
	<input type="hidden" id="ShouDingId" value='<%=session.getAttribute("SSI_LOGIN_Status")%>'/>
<table height="100%" cellSpacing="0" cellPadding="0" width="100%">
  <tbody>
    <tr>
      <td width=10 height=29><img src="${ctx}/images/bg_left_tl.gif"/></td>
      <td   style="FONT-SIZE: 12px; BACKGROUND-IMAGE: url(${ctx}/images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system">
      合规管理</td>
      <td width=10><img src="${ctx}/images/bg_left_tr.gif"/></td>
    </tr>
    <tr>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_ls.gif)"></td>
      <td id="menuTree"   style="PADDING-RIGHT: 10px;   PADDING-LEFT: 0px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    valign="top"></td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_rs.gif)"></td>
    </tr>
    <tr>
      <td width=10><img src="${ctx}/images/bg_left_bl.gif"/></td>
      <td style="BACKGROUND-IMAGE: url(${ctx}/images/bg_left_bc.gif)"></td>
      <td width=10><img src="${ctx}/images/bg_left_br.gif"/></td>
    </tr>
  </tbody>
</table>
<script type="text/javascript">
  var ShouDingvalue = document.getElementById("ShouDingId").value;
var tree = null;
var root = new TreeNode('合规管理');
  var root1 = new TreeNode('差距分析');
  		if(ShouDingvalue==1){
  		
  		}
  		else {
  		
  		
  		 var fun1 = new TreeNode('通用差距分析');
       var f1son1 = new TreeNode('通用物理');
       fun1.add(f1son1);
         var f1son2=new TreeNode('10.1物理位置的选择', '${ctx}/psadInfo/psadAction.action?psadFatherSort=10.1.2&PSAD_FatherSort=10.1.1', 'tree_node.gif', null, 'tree_node.gif', null);
            f1son1.add(f1son2);
         var f1son3=new TreeNode('10.2物理访问控制', '${ctx}/psadInfo/psadAction.action?psadFatherSort=10.2.2&PSAD_FatherSort=10.2.1', 'tree_node.gif', null, 'tree_node.gif', null);
           f1son1.add(f1son3);
        var f1son4=new TreeNode('10.3防盗窃和防破坏', '${ctx}/psadInfo/psadAction.action?psadFatherSort=10.3.2&PSAD_FatherSort=10.3.1', 'tree_node.gif', null, 'tree_node.gif', null);
            f1son1.add(f1son4); 
          var f1son5=new TreeNode('10.4机房环境', '${ctx}/psadInfo/psadAction.action?psadFatherSort=10.4.2&PSAD_FatherSort=10.4.1', 'tree_node.gif', null, 'tree_node.gif', null);
            f1son1.add(f1son5); 
         var f1son6=new TreeNode('10.5机房消防设施', '${ctx}/psadInfo/psadAction.action?psadFatherSort=10.5.2&PSAD_FatherSort=10.5.1', 'tree_node.gif', null, 'tree_node.gif', null);
          f1son1.add(f1son6);
        var f1son7=new TreeNode('10.6电力供应', '${ctx}/psadInfo/psadAction.action?psadFatherSort=10.6.2&PSAD_FatherSort=10.6.1', 'tree_node.gif', null, 'tree_node.gif', null);
          f1son1.add(f1son7);     

       var msad = new TreeNode('通用管理');
       fun1.add(msad);
       var msad1 = new TreeNode('11.1安全管理总体要求', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.1.2&MSAD_FatherSort=11.1.1', 'tree_node.gif', null, 'tree_node.gif', null);
       msad.add(msad1);

        var msad2 = new TreeNode('11.2安全管理机构');

       msad.add(msad2);
             var safe = new TreeNode('11.2.1岗位设置', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.1.2&MSAD_FatherSort=11.2.1.1', 'tree_node.gif', null, 'tree_node.gif', null);
              msad2.add(safe);
             var safe1 = new TreeNode('11.2.2授权和审批', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.2.2&MSAD_FatherSort=11.2.2.1', 'tree_node.gif', null, 'tree_node.gif', null);
              msad2.add(safe1);
             var safe2 = new TreeNode('11.2.3沟通和合作', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.3.2&MSAD_FatherSort=11.2.3.1', 'tree_node.gif', null, 'tree_node.gif', null);
              msad2.add(safe2);
              var safe3 = new TreeNode('11.2.4审核和检查', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.4.2&MSAD_FatherSort=11.2.4.1', 'tree_node.gif', null, 'tree_node.gif', null);
              msad2.add(safe3);
              var safe3 = new TreeNode('11.2.5制度管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.2.5.2&MSAD_FatherSort=11.2.5.1', 'tree_node.gif', null, 'tree_node.gif', null);
              msad2.add(safe3);
        var msad3 = new TreeNode('11.3人员安全管理');
         msad.add(msad3);
             var person = new TreeNode('11.3.1人员上岗', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.1.2&MSAD_FatherSort=11.3.1.1', 'tree_node.gif', null, 'tree_node.gif', null);
             msad3.add(person); 
             var person1 = new TreeNode('11.3.2人员离岗', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.2.2&MSAD_FatherSort=11.3.2.1', 'tree_node.gif', null, 'tree_node.gif', null);
             msad3.add(person1);
             var person2 = new TreeNode('11.3.3培训与考核', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.3.2&MSAD_FatherSort=11.3.3.1', 'tree_node.gif', null, 'tree_node.gif', null);
             msad3.add(person2);
             var person3 = new TreeNode('11.3.4外部人员访问管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.3.4.2&MSAD_FatherSort=11.3.4.1', 'tree_node.gif', null, 'tree_node.gif', null);
             msad3.add(person3);
        var msad4 = new TreeNode('11.4系统建设管理');
        msad.add(msad4);
                var build = new TreeNode('11.4.1系统定级', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.1.2&MSAD_FatherSort=11.4.1.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build);
                var build1 = new TreeNode('11.4.2安全方案设计', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.2.2&MSAD_FatherSort=11.4.2.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build1);
                var build2 = new TreeNode('11.4.3产品采购和使用', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.3.2&MSAD_FatherSort=11.4.3.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build2);
                var build3 = new TreeNode('11.4.4自行软件开发', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.4.2&MSAD_FatherSort=11.4.4.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build3);
                var build4 = new TreeNode('11.4.5外包软件开发', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.5.2&MSAD_FatherSort=11.4.5.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build4);
                var build5 = new TreeNode('11.4.6工程实施', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.6.2&MSAD_FatherSort=11.4.6.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build5);
                var build6 = new TreeNode('11.4.7测试验收', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.7.2&MSAD_FatherSort=11.4.7.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build6);
                var build7 = new TreeNode('11.4.8系统交付', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.8.2&MSAD_FatherSort=11.4.8.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build7);
                var build8 = new TreeNode('11.4.9系统备案', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.9.2&MSAD_FatherSort=11.4.9.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build8);
                var build9 = new TreeNode('11.4.10等级测评', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.10.2&MSAD_FatherSort=11.4.10.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build9);
                var build10 = new TreeNode('11.4.11安全服务商选择', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.4.11.2&MSAD_FatherSort=11.4.11.1', 'tree_node.gif', null, 'tree_node.gif', null);
                msad4.add(build10);
        var msad5 = new TreeNode('11.5系统运维管理');
        msad.add(msad5);
               var oper = new TreeNode('11.5.1环境管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.1.2&MSAD_FatherSort=11.5.1.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper);
               var oper1 = new TreeNode('11.5.2资产管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.2.2&MSAD_FatherSort=11.5.2.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper1);
               var oper2 = new TreeNode('11.5.3介质管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.3.2&MSAD_FatherSort=11.5.3.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper2);
               var oper3 = new TreeNode('11.5.4设备管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.4.2&MSAD_FatherSort=11.5.4.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper3);
               var oper4 = new TreeNode('11.5.5网络安全管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.5.2&MSAD_FatherSort=11.5.5.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper4);
               var oper5 = new TreeNode('11.5.6系统安全管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.6.2&MSAD_FatherSort=11.5.6.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper5);
               var oper6 = new TreeNode('11.5.7恶意代码防范管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.7.2&MSAD_FatherSort=11.5.7.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper6);
               var oper7 = new TreeNode('11.5.8密码管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.8.2&MSAD_FatherSort=11.5.8.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper7);
               var oper8 = new TreeNode('11.5.9变更管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.9.2&MSAD_FatherSort=11.5.9.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper8);
               var oper9 = new TreeNode('11.5.10备份与恢复管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.10.2&MSAD_FatherSort=11.5.10.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper9);
               var oper10 = new TreeNode('11.5.11安全事件处置', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.11.2&MSAD_FatherSort=11.5.11.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper10);
               var oper11 = new TreeNode('11.5.12应急预案管理', '${ctx}/msadInfo/msadInfoAction.action?msadFatherSort=11.5.12.2&MSAD_FatherSort=11.5.12.1', 'tree_node.gif', null, 'tree_node.gif', null);
               msad5.add(oper11);
    var fun2 = new TreeNode('技术差距分析','${ctx}/pages/cpManage/technology/skip.jsp', 'tree_node.gif', null, 'tree_node.gif', null);

   root1.add(fun1); 
    root1.add(fun2);
  		}
  
   
    
     var chajufenxin = new TreeNode('差距分析报告' );
      var jindu = new TreeNode('差距分析进度', '${ctx}/gapAnalysisSchedule/projectShowcase.action', 'tree_node.gif', null, 'tree_node.gif', null);
     chajufenxin.add(jindu);
     var jindu2 = new TreeNode('差距分析报告', '${ctx}/pages/ensure/chajubaogaoTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
     chajufenxin.add(jindu2);
     root1.add(chajufenxin);
  root.add(root1);
  var root2 = new TreeNode('整改需求');
     var r2s1 = new TreeNode('整改需求汇总');
     var r2s1s1 = new TreeNode('技术差距分析', '${ctx}/pages/cpManage/demand/demandCollect/pageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
     var r2s1s2 = new TreeNode('通用物理安全', '${ctx}/pages/cpManage/gpaShow/gpaShowPageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
     var r2s1s3 = new TreeNode('通用管理安全', '${ctx}/pages/cpManage/msahShow/msaShowPageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
     r2s1.add(r2s1s1);
     r2s1.add(r2s1s2);
     r2s1.add(r2s1s3);
     root2.add(r2s1);
     
     var r2s2 = new TreeNode('整改建议');
     var r2s2s1 = new TreeNode('技术差距分析', '${ctx}/pages/cpManage/demand/rectificationProposal/pageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
     var r2s2s2 = new TreeNode('通用物理安全', '${ctx}/pages/cpManage/gpaShow/gpaRectPageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
     var r2s2s3 = new TreeNode('通用管理安全', '${ctx}/pages/cpManage/msahShow/msaRectPageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
     r2s2.add(r2s2s1);
     r2s2.add(r2s2s2);
     r2s2.add(r2s2s3);
     root2.add(r2s2);
     
     var root33 = new TreeNode('汇总分析');
     var r2s33 = new TreeNode('安全差距分析报告', '${ctx}/summaryAnalysis/projectShowcase.action', 'tree_node.gif', null, 'tree_node.gif', null);
     root33.add(r2s33);
      var r2s44 = new TreeNode('安全控制分析报告', '${ctx}/securityControl/projectShowcase.action', 'tree_node.gif', null, 'tree_node.gif', null);
    
     root33.add(r2s44);
     root2.add(root33);
     var r2s4 = new TreeNode('历史整改需求');
        var r2s4s1 = new TreeNode('技术历史整改需求', '${ctx}/pages/cpManage/demand/contrastRectification/pageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
        var r2s4s2 = new TreeNode('通用物理历史整改需求', '${ctx}/pages/cpManage/gpaShow/gpaRectHistoryPageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
        var r2s4s3 = new TreeNode('通用管理历史整改需求', '${ctx}/pages/cpManage/msahShow/msaRectHistoryPageTo.jsp', 'tree_node.gif', null, 'tree_node.gif', null);
        r2s4.add(r2s4s1);
        r2s4.add(r2s4s2);
        r2s4.add(r2s4s3);
     root2.add(r2s4);
  root.add(root2);
tree = new Tree(root);
tree.show('menuTree');
 

</script>

</body>

</html>
