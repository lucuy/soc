<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加管理人员</title>
<meta http-equiv="Cache-Control" content="private" />
<link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
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


	   function back(){
	   location.href="${ctx}/employee/queryEmployee.action";
	   }
	   //保存数据
	  function inster(){
	   var btnSub=document.getElementById("btnSub");
       btnSub.disabled="disabled";
	   var userName = $("#userName").val();
	   var trueName =$("#trueName").val();
	   var password = $("#password").val();
	   var passwordAgain = $("#passwordAgain").val();
	   var IDCardNum = $("#IDCardNum").val();
	   var depName = $("#depName").val();
	   var userAuthority = "";   
	  // var systemRule = "";
	     //var depName = $("#depName").val();  
	     $("[name=ids]:checkbox:checked").each(function(){
	           if(userAuthority!=""){
	           //userAuthority+=","+$(this).val();
	           userAuthority=2;
	           }else{
	           userAuthority=$(this).val();
	           }
	      });
	      	     
	      if(userName==""){
	     alert("登录用户名不能为空！");
	     $("#userName").focus();
	     btnSub.disabled="";
	     return false;
	   }else  if(trueName==""){
	     alert("真实姓名不能为空！");
	     btnSub.disabled="";
	     $("#trueName").focus();
	       return false;
	   }else if(password==""){
	     alert("密码不能为空");
	     $("#password").focus();
	     btnSub.disabled="";
	     return false;
	   }else if(password!=passwordAgain){
	    alert("请重新输入密码");
	    btnSub.disabled="";
	     $("#password").val("");
	     $("#password").focus();
	     $("#passwordAgain").val("");
	        return false;
	   }else if(IDCardNum==""){
	     alert("身份证号不能为空");
	     $("#IDCardNum").focus();
	     btnSub.disabled="";
	      return false;
	   }else if(userAuthority==""){
	    alert("请设置用户权限");
	    btnSub.disabled="";
	     return false;
	     }
	     
	     if(depName==""){
	     	alert("请选择所在部门！");
	     	$("#depName").focus();
	     	return;
	     }
	   location.href = "${ctx}/employee/addEmployee.action?userName="+encodeURI(encodeURI(userName,"utf-8"))
			   +"&trueName="+encodeURI(encodeURI(trueName,"utf-8"))
			   +"&password="+password
			   +"&IDCardNum="+IDCardNum
			   +"&userAuthority="+encodeURI(encodeURI(userAuthority,"utf-8"))
			   +"&depName="+encodeURI(encodeURI(depName,"utf-8"));
	  }
	  
	  
	  function setDisab(){
	    var btnSub=document.getElementById("btnSub");
        btnSub.disabled="disabled";
	}
	  
	   
//查询用户登录名
function checkUserName(){
			var userName = $.trim($("#userName").val());
			$.post('${ctx}/employee/queryAjaxEmployeeName.action',{name:userName}, function(data) { 
				if(data == 'ERROR'){
					alert("该用户已存在");
					
					$("#userName").val("");
		    		$("#userName").focus();
		    		return false;
				}else{
				  var btnSub=document.getElementById("btnSub");
                   btnSub.disabled="";
				}
			});
		}   
		
	
	function isCardNo(varStr)  
  	{  
	   if(varStr.value==""){
	   	return;
	   }
	   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	   if(reg.test(varStr.value) === false) {  
	       varStr.value="";
	       alert("身份证输入不合法");  
	       return  false;  
	   }  
	}   
	</script>
</head>
<body style="margin: 0">
	<form  id="frm" action="${ctx}/employee/addEmployee.action" method="post">

		<table width="99.5%" border="0" cellspacing="0" cellpadding="0"  >
			<tr>
				<td>

							<table width="100%" class="framDiv">
							    <tr>
   							       <td colspan="4"  class='r2titler'>用户信息编辑</td> 
  							    </tr>
  							     <tr>
									<td class="td_detail_separator"></td>
								</tr>
								<tr >
									<td width="10%" scope="col" align="right">登录用户名：<font color="#ff0000">*</font></td>
									<td width="40%" scope="col" style="text-align: left"><input type="text" name="userName" id="userName" style="width:200px" maxlength="50" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" onfocus="setDisab()" onblur="checkUserName()"/>
									</td>
									<td width="10%" scope="col" align="right">真实姓名：<font color="#ff0000">*</font></td>
									<td width="40%" scope="col" style="text-align: left"><div>
											<input type="text" name="trueName" id="trueName" onblur="yanzheng1(this)" style="width:200px" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\d]/g,''))" maxlength="50" name="Numbers" />
										</div>
									</td>
								</tr>
								<tr>
									<td scope="col" align="right">登录密码：<font color="#ff0000">*</font></td>
									<td style="text-align: left"><div>
											<input type="password" name="password" id="password" maxlength="50" style="width:200px" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
										</div>
									</td>
									<td scope="col" align="right">确认密码：<font color="#ff0000">*</font></td>
									<td style="text-align: left"><div>
											<input type="password" name="passwordAgain" id="passwordAgain" maxlength="50" style="width:200px" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
										</div>
									</td>
								</tr>
								<tr>
									<td align="right">身份证号：<font color="#ff0000">*</font></td>
									<td colspan="3" style="text-align: left"><div>
											<input type="text" name="IDCardNum" id="IDCardNum" style="width:200px" maxlength="50" onblur="isCardNo(this)" />
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row" align="right">用户权限：<font color="#ff0000">*</font></td>
									<td colspan="3" style="text-align: left;">
									
								   <span style="background-color:#FF0000">
								         <input name="ids" type="checkbox" value="0" /> 系统管理员&nbsp; 
								   </span>&nbsp;&nbsp;
								    <span style=" background-color:#00FF33">
								         <input name="ids" type="checkbox" value="1" /> 安全管理员&nbsp; 
								         
								      </span>
								   </td>
								</tr>
								<tr>
									<td align="right">所在部门：<font color="#ff0000">*</font></td>
									<td colspan="3">
										<input type="text"  name="depName" id="depName" style="width:200px"  readonly="readonly" onclick="$('#pages-basic-addEmployee-dialog').dialog('open');"/>
									</td>
								</tr>
								 <tr>
									<td class="td_detail_separator"></td>
								</tr>
							</table>
             </td>
			</tr>
			<tr>
				<td>
					<div >
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr height="30">
								<td  valign="middle" align="right">
								<input type="button" value="取消"  onclick="back()"  class="btnstyle" />
								<input type="button"  onclick="inster()" value="保存" id="btnSub"   class="btnstyle" />
								</td>
							</tr>
						</table>
					</div> <!-- toolbar area --></td>
			</tr>
		</table>
	</form>
	<div id="pages-basic-addEmployee-dialog" title="所在部门"></div>
</body>
</html>

