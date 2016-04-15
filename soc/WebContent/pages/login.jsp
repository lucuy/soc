<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${loginTitle}</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
	<link rel="shortcut icon" href="${favicon}">
	<link rel="Bookmark" href="${favicon}">
	<link href="${ctx}/styles/css.css" rel="stylesheet" type="text/css" />
	<script type='text/javascript'
		src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
	<script type='text/javascript' src="${ctx}/scripts/util.js"></script>

	<script type="text/javascript">
		window.onload = function() {
			$("#loginName").focus();
		};
		
		function checkValue(){
			//var imgCode = document.getElementById('imgCode').value;
			var loginName = document.getElementById('loginName').value;
			var password = document.getElementById('password').value;
			
			/* if(Trim(imgCode)=='') {
				alert('请输入验证码');
				document.getElementById('imgCode').focus();
				return  ;
			} else */ if (Trim(loginName)==''){
				alert("请输入用户名");
				document.getElementById('loginName').focus();
				return false;
			} else if(password ==''){
				alert("请输入密码");
				document.getElementById('password').focus();
				return false;
			}
			return true;
		}
		function checkSaveSecret(){
			var savedSecret = document.getElementById('savedSecret').value;
			if (Trim(savedSecret)==''){
				alert("请输入动态码");
				document.getElementById('savedSecret').focus();
				return false;
				}
			return true;
		}
		
		/**
		 * 换验证码图片
		 */
		function change(img) {
		  img.src = '${ctx}/pages/commons/image.jsp?seed=' + Math.random();
		}
		
		document.onkeydown = function(e){
			e=e?e:(window.event?window.event:null);   
		   	if(e.keyCode == 13){
		   		submitForm();
		   	}
		};
		/*提前验证*/
		function checkPerSubmit(){
			 var mark = $('input:radio:checked').val();
			    $("input:radio").on("click", function() {
			        // 这里需要更新
			        mark = $(this).val();
			        
			    });
			    if(mark==1){
			    	submitForm();
			    }else{
			    	submitForm1();
			    }
		}
		/*一次认证*/
		function submitForm() {
			 if(checkValue()){
				
				var url = "${ctx}/login/checkSingle.action";
				$.ajax( {
					type : "post",
					url : url,
					dataType : 'text',
					data : "&loginName=" + $('#loginName').val(),
					success : function(data) {
						if(data!='') {
							if(confirm( $('#loginName').val()+'已经在'+data+'登录，是否继续？强行登录将导致该用户下线！')) {
								document.forms[0].submit();
							}
						} else {
							document.forms[0].submit();
						}
					}
				});
		   	} 
		   	//document.forms[0].submit();
		}
		/*二次认证*/
		function submitForm1() {
			
			 if(checkValue()&&checkSaveSecret()){
				
				var url = "${ctx}/login/checkSingle.action";
				$.ajax( {
					type : "post",
					url : url,
					dataType : 'text',
					data : "&loginName=" + $('#loginName').val(),
					success : function(data) {
						if(data!='') {
							if(confirm( $('#loginName').val()+'已经在'+data+'登录，是否继续？强行登录将导致该用户下线！')) {
								document.forms[0].submit();
							}
						} else {
							document.forms[0].submit();
						}
					}
				});
		   	} 
		   	//document.forms[0].submit();
		}
		
		var g_secchecktatus = 0;
		function showSecCechkDlg(e) {
			if(g_secchecktatus == 1) {
				destroySecCheckDlg();
				return;
			}
		    var t = e.offsetTop + e.offsetHeight;
		    var l = e.offsetLeft;
		    var w = e.offsetWidth;
		    while (e = e.offsetParent) {
		   		t += e.offsetTop;
		     	l += e.offsetLeft;
		    }
		
		    destroySecCheckDlg();
		
		    var objMsgDiv = document.createElement ( "div" );
		
		    var nowtime = new Date();
		     
		    objMsgDiv.setAttribute ( 'id', 'addSecCheck' );
		    objMsgDiv.style.position = "absolute";
		    objMsgDiv.style.background = "#e6ecf0";
		    //objMsgDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=75,finishOpacity=75)";
		  
		     
		     objMsgDiv.style.left = l + "px";
		     objMsgDiv.style.top = t + "px";
		     objMsgDiv.style.border = 1 + "px  solid #808080";
		     objMsgDiv.style.width = "390px";
		     objMsgDiv.style.zIndex = "10000";
		     
		     objMsgDiv.innerHTML += "" + 
		     "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
		     "<tr background=\"${ctx}/images/secchk_title_bg.jpg\" height=\"21\">" + 
		     	"<td align=\"center\">" +
		     		 "<table width=\"98%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
		     		 "<tr>" +
		     			"<td align=\"left\">安全检查</td>" +
		     			"<td align=\"right\"><img border=\"1\" src=\"${ctx}/images/secchk_title_close.jpg\" class=\"hand\" onclick=\"destroySecCheckDlg();\"/></td>" +
		     		 "</tr>" +
		     		 "</table>" +
		     	"</td>" +
		     "</tr>" +
		     "<tr>" +
		     	"<td>" +
		     		"<table width=\"98%\" border=\"0\" cellspacing=\"5\" cellpadding=\"5\">" +
		     		"<tr>" +
		     			"<td align=\"left\">" + retv_process1 + "</td>" +
		     		"</tr>" +
		     		"<tr>" +
		     			"<td align=\"left\">" + retv_process2 + "</td>" +
		     		"</tr>" +
		     		"<tr>" +
		     			"<td align=\"left\">" + retv_process3 + "</td>" +
		     		"</tr>" +
		     		"<tr>" +
		     			"<td align=\"left\">" + retv_comm1 + "</td>" +
		     		"</tr>" +
		     		"<tr>" +
		     			"<td align=\"left\">" + retv_comm2 + "</td>" +
		     		"</tr>" +
		     		"<tr>" +
		     			"<td align=\"left\">" + retv_anti + "</td>" +
		     		"</tr>" +
		     		"</table>" +
		     	"</td>" +
		     "</tr>" +
		     "</table>";
		     	
		     document.body.appendChild ( objMsgDiv );
		     
		     g_secchecktatus = 1;                
		 }
		 
		 function destroySecCheckDlg() {   
		 	if ( g_secchecktatus == 1 ) {
	        	document.body.removeChild ( document.getElementById ("addSecCheck") );
	            g_secchecktatus = 0;
	        }
	    }
		 $(function() {
			 var checkstyle='${checkstyle}';
			 if(checkstyle=='2'){
				 $("#code").css("display","");
			 }else{
				 $("#code").css("display","none");
			 }
			    $("#oneCheck").on("click", function() {
			    	$("#code").css("display","none");
			    	
			    });
			    $("#twoCheck").on("click", function() {
			    	$("#code").css("display","");
			    });
			});
		
	</script>
</head>
<body bgcolor="#3c7fb4">
	<div class="container2">
		<table width="60%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="100px">&nbsp;</td>
			</tr>
			<tr>
				<td height="187px"><center>
						<img src="${login}" />
					</center>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="loginbox2" id="tablereload">
						<s:form action="check" namespace="/login" method="post"
							theme="simple">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="lbox2top">
										<div class="lbox2m">
											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr></tr>
												<tr>
													<td class="lboxleft">用户名：</td>
													<td class="lboxright"><input type="text"
														name="loginName" id="loginName" class="inputtext" />
													</td>
												</tr>
												<tr>
													<td class="lboxleft" id="password">密&nbsp;&nbsp;码：</td>
													<td class="lboxright"><input type="password"
														maxlength="22" name="password" id="password" 
														class="inputtext" size="22" autocomplete="off"/>
													</td>
												</tr>
											  	<tr id="code" style="display:none;">
													<td class="lboxleft">动态码：</td>
													<td class="lboxright"><input type="text"
														maxlength="22" name="savedSecret" id="savedSecret" 
														class="inputtext" size="22" autocomplete="off" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " 
														onafterpaste="this.value=this.value.replace(/[^\d]/g,'') " />
													</td>
												</tr>
												<tr>
													<td class="lboxleft">登录方式：</td>
													<td class="lboxright"> 
													<input type ="radio" id="oneCheck" name="checkstyle"  value="1" <c:if test="${checkstyle !=2 }">checked="checked"</c:if> />密码验证  
													<input type ="radio" id="twoCheck" name ="checkstyle" value="2" <c:if test="${checkstyle ==2 }">checked="checked"</c:if>/>双重验证
														
													</td>
												</tr>
												<tr>
													<td class="lboxleft">&nbsp;</td>
													<td class="lboxright">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td>
																		<!--<input align="right" type="button" id="btn_login" value="登录系统"
																			class="inputsubmit" onclick="submitForm()" /> -->
																		 <input type="button" id="btn_login" value="登录系统"
																			class="inputsubmit" onclick="checkPerSubmit();" />
																			
																	</td>
																<td><span id="codeMessage" class="actmsg"><s:actionmessage />
																		<c:if test="${requestScope.FailNumber!= null}">错误<c:out
																				value="${requestScope.FailNumber}"></c:out>次后账户将被锁定</c:if>
																</span> </td>
															</tr>
														</table></td>
												</tr>
											</table>
										</div></td>
								</tr>
								<tr>
									<td class="lbox2mid">&nbsp;</td>
								</tr>
								<tr>
									<td class="lbox2foot"></td>
								</tr>
								
							</table>
						</s:form>
					</div></td>
			</tr>
			<tr>
				<td class="100px"></td>
			</tr>
		</table>
		
	</div>
	<div style="position: fixed;width: 100%;bottom: 3px">
		<center valign="middle" >
	  		<table>
	  			<tr align="center">
	  				<td align="center" rowspan="2">
	  					
	  				</td>
	  				<td align="center">
	  					版权所有 ${banquan}
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					${ver}
	  				</td>
	  			</tr>
	  		</table>
	  	</center>
	</div>
</body>
</html>