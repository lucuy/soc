<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>整改建议</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/user/user.css" rel="stylesheet"
	type="text/css">
 <link href="${ctx}/styles/jquery-ui.custom.css" rel="stylesheet" type="text/css">
<%-- <link href="${ctx}/styles/jquery-ui-1.8.21.css" rel="stylesheet"
	type="text/css"> --%>
<link href="${ctx}/styles/jquery.popuplayer.css" rel="stylesheet"
	type="text/css">
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
 <script type="text/javascript" src="${ctx}/scripts/sorttalbe.js"></script>
 <style type="text/css">
     .biaoti{
           font-size: 12px ;
           font-weight: bold;
          
     }
     table {
     	font-size: 12px;
     }


</style>
<%--添加整改建议对话框--%>
 <script type='text/javascript'>
	
  $(function() {
    $( "#addDialog" ).dialog({
      autoOpen: false,
      width:1000,
      height:300,
      modal:true,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 100
      } 
    });
    $(".addOpener").bind("click",function(event){
     
    	var ControlDomain = $(this).parents("tr").children("td:first").text();
    	$("#HD_ControlDomain").val(ControlDomain);
    	var ControlUnit = $(this).parents("tr").children("td:nth-child(2)").text();
    	$("#HD_ControlUnit").val(ControlUnit);
    	var Content = $(this).parents("tr").children("td:nth-child(3)").text();
    	$("#CORRRECOM_Content").val(Content);
    	 var sysId = $("#FK_CA").val();
        $("#HD_sysId").val(sysId);
          var sysname = $("#sysname").val();
        $("#HD_sysname").val(sysname); 
        var AssessResult = $("#CIA_AssessResult").val();
        $("#HD_AssessResult").val(AssessResult); 
        $( "#addDialog" ).dialog( "open" );
    });
  });
  </script>
<%--修改整改建议对话框--%>
 <script>
  $(function() {
    $( "#changeDialog" ).dialog({
      autoOpen: false,
      width:1000,
      height:300,
      modal:true,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 100
      } 
    });
    
    $(".changeOpener").bind("click",function(event){
    	 var ControlDomain = $(this).parents("tr").children("td:first").text();
    	$("#CHD_ControlDomain").val(ControlDomain);
    	var ControlUnit = $(this).parents("tr").children("td:nth-child(2)").text();
    	$("#CHD_ControlUnit").val(ControlUnit);
    	var Content = $(this).parents("tr").children("td:nth-child(3)").text();
    	$("#CCORRRECOM_Content").val(Content);
    	var Proposal = $(this).parents("tr").children("td:nth-child(4)").text();
    	$("#Cproposal").text($.trim(Proposal));
    	var ProposalData = $(this).parents("tr").children("td:nth-child(5)").text();
    	$("#CHD_data").val(ProposalData);
    	
    	 var sysId = $("#FK_CA").val();
        $("#CHD_sysId").val(sysId);
          var sysname = $("#sysname").val();
        $("#CHD_sysname").val(sysname); 
        var AssessResult = $("#CIA_AssessResult").val();
        $("#CHD_AssessResult").val(AssessResult);  
        $( "#changeDialog" ).dialog( "open" );
    });
    
    $(".proDelete").bind("click",function(event){
    	var ControlUnit = $(this).parents("tr").children("td:nth-child(2)").text();
 		 var Content = $(this).parents("tr").children("td:nth-child(3)").text();
 		 var sysId = $("#FK_CA").val();
 		 var deleteAssessResult= document.getElementById("deleteAssessResult").value;
 		 var deletesysname= document.getElementById("deletesysname").value;
 		 var fkcaDelete= document.getElementById("fkcaDelete").value;
 		 window.location.href="${ctx}/proposal/deleteProposal.action?ControlUnit="+encodeURI(encodeURI(ControlUnit))+"&Content="+encodeURI(encodeURI(Content))+"&sysId="+sysId+"&cia_assessResultDelete="+encodeURI(encodeURI(deleteAssessResult))+"&sysnameDelete="+encodeURI(encodeURI(deletesysname))+"&fkcaDelete="+encodeURI(encodeURI(fkcaDelete));
    });
    
  });
  function gpaRectDocCreate(){
  	var sysId = $("#FK_CA").val();	
  	var sysName=$("#sysname").val();
  	window.location.href="${ctx}/proposal/proposalRectDocCreate.action?FK_CA="+encodeURI(encodeURI(sysId))+"&sysname="+encodeURI(sysName);
  }
  
  function butSub1(doc){
     doc.disabled="disabled";
      if(document.getElementById("proposal").length>1000){
		  		alert("您输入的整改建议长度超过1000！！！");
		  		doc.disabled="";
		  		return;
		    }
 	 $("#frm2").submit();
  }
  
   function butSub2(doc){
     doc.disabled="disabled";
     if(document.getElementById("Cproposal").length>1000){
		  		alert("您输入的整改建议长度超过1000！！！");
		  		doc.disabled="";
		  		return;
		    }
     
 	 $("#frm3").submit();

  }
  </script>  
 </head>

<body style="margin: 0;position: 0">
    <input type="hidden" id="deleteAssessResult" value="${CIA_AssessResult}"> 
    <input type="hidden" id="deletesysname" value="${sysname}"> 
    <input type="hidden" id="fkcaDelete" value="${FK_CA}"> 
    
	   <form   method="post" action="${ctx}/proposal/queryProposal.action" id="frm1">
		<table width="99.5%" border="0" cellspacing="0" cellpadding="0">
			<!-- 空行 -->
			<tr>
				<td height="2px">
				<input type="hidden" id="FK_CA" name="FK_CA" value="${FK_CA}">
				<input type="hidden" id="CIA_AssessResult" name="CIA_AssessResult" value="${CIA_AssessResult}">
				<input type="hidden" id="sysname" name="sysname" value="${sysname}">
				</td>
 
			</tr> 
 
		 
 
			<tr>
				<td>
 
					<div class="caozuobox">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td width="1%"></td>
								
								<td>
								    <input type="button"  class="btnstyle" value="生成报表" onclick="gpaRectDocCreate();"/>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td>
 
					<div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id="dataList">
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-manageList">
									<thead>
										 <tr height="28">
											<th width="15%" class="biaoti" onclick="sortAble('table-manageList', 0)" style="cursor:pointer">控制域</th>
											<th width="15%" class="biaoti" onclick="sortAble('table-manageList', 1)" style="cursor:pointer">控制单元</th>
											<th width="30%" class="biaoti" onclick="sortAble('table-manageList', 2)" style="cursor:pointer">不符合项</th>
											<th width="15%" class="biaoti" onclick="sortAble('table-manageList', 3)" style="cursor:pointer">整改建议</th>
											<th width="10%" class="biaoti" onclick="sortAble('table-manageList', 4)" style="cursor:pointer">整改时间</th>
											<th width="*" class="biaoti">整改建议操作</th>
										</tr>
									 </thead>
									<tbody id="tbodyuse" style="font-size:12px;">
									<s:iterator value="proposals" id="proposal" status="demdcol">
									 <tr style="font-size:12px;"> 
									 <td align="center">${proposal.CORRRECOM_ControlDomainSort} :${CORRRECOM_ControlDomainName}</td>
									 <td align="center">${CORRRECOM_ControlUnitSort}:${CORRRECOM_ControlUnitName}</td>
									 <td><span id="Content_TD" style="font-size:12px;">${CORRRECOM_Content}</span></td>
									 <td align="center">
										 <c:if test="${CORRRECOM_Advise==null}">
										  <input  type="button"    class="addOpener" style=" background:url(${ctx}/images/btn1.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer;">
										 </c:if>
										   <c:if test="${CORRRECOM_Advise!=null}">
										  ${CORRRECOM_Advise}
										 </c:if>
									 </td>
									 <td align="center">${CORRRECOM_Date}</td>
									  <td align="center">
										   <c:if test="${CORRRECOM_Advise!=null}">
										 <input  type="button"   class="changeOpener"    style=" background:url(${ctx}/images/btnEdit.jpg) no-repeat; border:none; width:58px; height:21px; cursor:pointer;">
										&nbsp;&nbsp;
										 <input  type="button"   class="proDelete"  style=" background:url(${ctx}/images/btnDel.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;" >
										 </c:if>
										   <c:if test="${CORRRECOM_Advise==null}">
										   <!--   <span style="color: gray;">修改整改建议&nbsp;&nbsp;删除整改建议</span>-->
										    <input  type="button" style=" background:url(${ctx}/images/btnEdit_gray.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;">
										    &nbsp;&nbsp;
										     <input  type="button" style=" background:url(${ctx}/images/btnDel_gray.jpg) no-repeat; border:none;  width:58px;  height:21px; cursor:pointer;" >
										 </c:if>
									  </td>
									 </tr>
									</s:iterator>
									</tbody>
									 <tr id="tbodyuseTr">
									<td colspan="6">
									<jsp:include page="../../../commons/pageForList.jsp"></jsp:include>
									</td>
									</tr>
								 </table>
							</div>
							<!-- information area -->
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form> 
	
	 <div id="addDialog" title="添加整改建议" >
	<form id="frm2" action="${ctx}/proposal/insterProposal.action?cia_assessResultparam=${CIA_AssessResult}&sysnameparam=${sysname}&fkcaparam=${FK_CA}" method="post" >
	<input type="hidden" name="ControlDomain" id="HD_ControlDomain" value="">
	<input type="hidden" name="ControlUnit" id="HD_ControlUnit" value="">
	<input type="hidden" name="sysId" id="HD_sysId" value="">
	<input type="hidden" name="sysname" id="HD_sysname" value="">
	<input type="hidden" name="AssessResult" id="HD_AssessResult" value=""> 
    <table  width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-manageList">
  <tr>
    <td width="10%" align="right">差距项：</td>
    <td width="90%"><input type="text" style="width: 100%" readonly="readonly" id="CORRRECOM_Content" name="Content" value=""> </td>
  </tr>
  <tr>
    <td  align="right">整改建议：</td>
    <td><textarea cols="20" id="proposal" name="proposal" style="width: 100%"></textarea></td>
  </tr>
 <TR>
   <TD colspan="2" align="right" style="text-align: right;">  
   <input type="button" value="确定"  onclick="butSub1(this)" class="btnyh">
   </TD>
 </TR>
</table>
</form>
    </div>
     <%--修改整改建议--%> 
    <div id="changeDialog" title="修改整改建议" >
	<form id="frm3" action="${ctx}/proposal/updataProposal.action?cia_assessResultUpdata=${CIA_AssessResult}+&sysnameUpdata=${sysname}+&fkcaUpdata=${FK_CA}"  method="post" >
	<input type="hidden" name="ControlDomain" id="CHD_ControlDomain" value="">
	<input type="hidden" name="ControlUnit" id="CHD_ControlUnit" value="">
	<input type="hidden" name="sysId" id="CHD_sysId" value="">
	<input type="hidden" name="sysname" id="CHD_sysname" value="">
	<input type="hidden" name="AssessResult" id="CHD_AssessResult" value=""> 
	<input type="hidden" name="ProposalData" id="CHD_data" value="">
	
    <table  width="100%" border="0" cellspacing="1" cellpadding="0" class="tab2" id="table-manageList">
  <tr>
    <td width="10%" align="right">差距项：</td>
    <td width="90%"><input type="text" style="width: 100%" readonly="readonly" id="CCORRRECOM_Content" name="Content" value=""> </td>
  </tr>
  <tr>
    <td  align="right">整改建议：</td>
    <td><textarea cols="20" id="Cproposal" name="proposal" style="width: 100%" ></textarea></td>
  </tr>
 <TR>
   <TD colspan="2" align="right" style="text-align: right;"> 
   <input type="button" class="btnyh" value="确定" id="but2" onclick="butSub2(this)">
   </TD>
 </TR>
</table>
</form>
    </div>
     </body>
</html>
