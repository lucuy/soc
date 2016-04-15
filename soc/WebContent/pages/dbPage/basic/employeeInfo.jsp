<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人设置</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link href="${ctx}/css/dtree.css" rel="stylesheet" type=text/css  /> 
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script src="${ctx}/scripts/jquery-1.7.2.min.js" type='text/javascript'></script>
<SCRIPT src="${ctx}/scripts/jquery-1.7.2.min.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/jquery-ui.custom.min.js" type=text/javascript></SCRIPT>
<script src="${ctx}/scripts/dtree.js" type="text/javascript"  ></script>
<script type="text/javascript" src="${ctx}/scripts/check.js"></script>
<script language="javascript">
jQuery(document).ready(function(){
	 queryTree();
	$("#pages-basic-addEmployee-dialog").dialog({
		autoOpen: false,
		width: 300,
		height: 400,
		buttons: {
			"确定[Enter]": function() {
				$(this).dialog("close");  
			},
			"取消[Esc]": function() { 
				$("#depName").val("");
				$(this).dialog("close"); 
			} 
		}
	});	
});
	
function queryTree(){
	d = new dTree('d');
	$.getJSON("${ctx}/division/query.action" ,function(json){
		if(json!=0){
			$.each(json, function(i,item) {
				var depName = item.depName;
				if(item.parentId == 0){
					d.add(0,-1,depName,'','','','','',true);
				}
				if(item.parentId != 0){
					d.add(i,XNodeTree(json,item.parentId),"<input type='radio' name='dep' value="+depName+" onclick='checkedRadio()'/>"+depName,'','','','','',true);//,"javascript:queryInfo("+item.id+")"
				}
			});
			$("#pages-basic-addEmployee-dialog").html("");
			$("#pages-basic-addEmployee-dialog").html(d.toString());
		}else{
			$("#pages-basic-addEmployee-dialog").html("");
		}
	});
}

function XNodeTree(json,  parentId){
	var index = 0;
	var flg = false;
	$.each(json, function(j,itemj) {
		if(parentId == itemj.id){
			flg = true;
			index = j;
		}
	});
	if(flg){
		return index;
	}
}
function checkedRadio(checkedValue){
	var checkedValue = $("input[name='dep']:checked").val();
	$("#depName").val(checkedValue);
}
//保存数据
function updataUser(){
	var trueName =$("#trueName").val();
	var password = $("#password").val();
	var passwordAgain = $("#passwordAgain").val();
	var IDCardNum = $("#IDCardNum").val(); 
	if(trueName==""){
	   alert("真实姓名不能为空！");
	   $("#trueName").focus();
	   return false;
	}else if(password==""){
	   alert("密码不能为空");
	   $("#password").focus();
	   return false;
	}else if(password!=passwordAgain){
	   alert("请重新输入密码");
	   $("#password").val("");
	   $("#password").focus();
	   $("#passwordAgain").val("");
	   return false;
	}else if(IDCardNum==""){
	   alert("身份证号不能为空");
	   $("#IDCardNum").focus();
	   return false;
	} 
	
			document.upemployee.submit();
}
	</script>
</head>
<body>
<form name="upemployee" id="upemployee" action="${ctx}/employee/personalset.action" method="post">
	<input type="hidden" name="userID" id="userID" value="${employee.userID}"> 
	<input type="hidden" name="userAuthority" id="userAuthority" value="${employee.userAuthority}"> 
	<input type="hidden" name="status" id="status" value="${employee.status}"> 
					<div class="cont">
						<!-- information area -->
						<table width="100%" class="framDiv">
						  	<tr>
   							   <td colspan="4"  class='r2titler'>个人设置</td> 
  							</tr>
  							<tr>
								<td class="td_detail_separator"></td>
							</tr>
							<tr >
								<td width="12%" scope="col" align="right">登陆用户名：<font color="#ff0000">*</font></td>
								<td width="40%" scope="col" style="text-align: left">
									<input type="hidden" name="userID" id="userID" value="${employee.userID}"> 
									
									<input type="text" name="userName" id="userName" value="${employee.userName}" style="width:200px"  disabled="disabled"/>
								</td>
								<td width="11%" scope="col" align="right">真实姓名：<font color="#ff0000">*</font></td>
								<td width="40%" scope="col" style="text-align: left">
									<div>
										<input type="text" name="trueName" id="trueName" value="${employee.trueName}" style="width:200px" />
									</div>
								</td>
							</tr>
							<tr>
								<td scope="col" style="text-align:right">登陆密码：<font color="#ff0000">*</font></td>
								<td style="text-align: left">
									<div>
										<input type="password" name="password" maxlength="50" id="password" style="width:200px" />
									</div>
								</td>
								<td scope="col" style="text-align: right">确认密码：<font color="#ff0000">*</font></td>
								<td style="text-align: left">
									<div>
										<input type="password" name="passwordAgain" maxlength="50" id="passwordAgain" style="width:200px" />
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align:right">身份证号：<font color="#ff0000">*</font></td>
								<td colspan="3" style="text-align: left">
									<div>
										<input type="text" onblur="isCardNo(this)" id="IDCardNum" name="IDCardNum" maxlength="50" value="${employee.IDCardNum}" style="width:200px" />
									</div>
								</td>
							</tr>
							<tr>
									<td align="right">所在部门：<font color="#ff0000">*</font></td>
									<td >
										<input type="text"  name="depName" id="depName" value="${employee.depName}" style="width:200px"  readonly="readonly" onclick="$('#pages-basic-addEmployee-dialog').dialog('open');"/>
									</td>
								</tr>
							<tr>
								<td class="td_detail_separator"></td>
							</tr>
						</table>
					</div>
				<div >
					<table width="100%">
						<tr >
							<td  align="right"><input type="button" width="6%" onclick="updataUser()" value="提交修改"  class="btnstyle"/>
							</td>
						</tr>
					</table>
				</div> 
</form>	
<div id="pages-basic-addEmployee-dialog" title="所在部门"></div>
</body>
</html>

