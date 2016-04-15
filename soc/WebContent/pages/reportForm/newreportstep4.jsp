<%@ include file="mainoffer.inc"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%

String path = request.getContextPath();

	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>
  
    <link href="${ctx}/styles/currentlystyle.css" rel="stylesheet" type="text/css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function nexts(){
			var f=document.ReportForm;
			var array="";
			f=document.ReportForm;
			var k=0;
			for(var i=0;i<f.elements.length;i++){
				if(f.elements[i].type=="checkbox" && f.elements[i].name=="rowvalues"){
					var tempv=f.elements[i].value;
					var tempArr=tempv.split(";");
					for(var j=0;j<tempArr.length;j++){
						array=array+tempArr[j]+",";
					}
					array=array+"|";
					k++;	
				}
			}
			<logic:notEqual name="useraction" value="editReport">
				f.action="newreportform.do?method=creatReportFormStep5&arrays="+f.arrays.value+"&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value+"&buttons="+array;
				f.submit();
			</logic:notEqual>
			<logic:equal name="useraction" value="editReport">
				f.action="editreportform.do?method=editReportFormStep5&arrays="+f.arrays.value+"&leveltwo="+f.leveltwo.value+"&levelthree="+f.levelthree.value+"&buttons="+array;
				f.submit();
			</logic:equal>
		}
		function pre(){
			var f=document.ReportForm;
			var reportinfo=encodeURIComponent(f.reportinfo.value);
			<logic:notEqual name="useraction" value="editReport">
				f.action="newreportform.do?method=creatReportFormStep3&reportinfo="+encodeURIComponent(encodeURIComponent(reportinfo))+"&levelthree="+f.levelthree.value+"&leveltwo="+f.leveltwo.value+"&arrays="+f.arrays.value;
				f.submit();
			</logic:notEqual>
			<logic:equal name="useraction" value="editReport">
				f.action="editreportform.do?method=editReportFormStep3";
				f.submit();
			</logic:equal>
		}
		
		function additem(objform){
			var temp="";
			if(objform.roworcoll.value=="0"){
				temp="列";
			}if(objform.roworcoll.value=="1"){
				temp="行";
			}
			if(objform.roworcoll.value=="0"){
				if(objform.hrefname.value==""){
					alert("链接/按钮 名称 不能为空!");
					return false;
				}if(objform.onclickthing.value==""){
					alert("点击事件不能为空！");
					return false;
				}if(objform.hrefvalue.value==""){
					alert("链接跳转到不能为空！");
					return false;
				}if(objform.sign.value==""){
					alert("显示标志不能为空！");
					return false;	
				}
			}if(objform.roworcoll.value=="1"){
				if(objform.hrefname.value==""){
					alert("链接/按钮 名称 不能为空!");
					return false;
				}if(objform.onclickthing.value==""){
					alert("点击事件不能为空！");
					return false;
				}if(objform.sign.value==""){
					alert("显示标志不能为空！");
					return false;	
				}
			}
			contentTable.insertRow();
			
			var i=contentTable.rows.length-1;
			
			contentTable.rows(i).insertCell().innerHTML=i;
			
			contentTable.rows(i).insertCell().innerHTML="&nbsp;"+temp;
			
			var al="居左";
			if(objform.aligntype.value=="0"){
				al="居左";
			}if(objform.aligntype.value=="1"){
				al="居中";
			}if(objform.aligntype.value=="2"){
				al="居右";
			}
			contentTable.rows(i).insertCell().innerHTML="&nbsp;"+al;
			contentTable.rows(i).insertCell().innerHTML=objform.hrefname.value+"&nbsp;";
			contentTable.rows(i).insertCell().innerHTML=objform.onclickthing.value+"&nbsp;";
			contentTable.rows(i).insertCell().innerHTML=objform.hrefvalue.value+"&nbsp;";
			contentTable.rows(i).insertCell().innerHTML=objform.sign.value+"&nbsp;";
			var values=objform.roworcoll.value+";"+objform.aligntype.value+";"+objform.hrefname.value+";"+objform.onclickthing.value+";"+objform.hrefvalue.value+";"+objform.sign.value;
			contentTable.rows(i).insertCell().innerHTML="<input type='checkbox' name='rowvalues' value='"+values+"' style='display:none'>";
			for(var j=0;j<8;j++){
				contentTable.rows(i).cells(j).align="center";
			}
		}
		
		function changes(objform){
			if(objform.roworcoll.value=="1"){
				objform.aligntype.disabled=true;
				objform.hrefvalue.disabled=true;
			}else if(objform.roworcoll.value=="0"){
				objform.aligntype.disabled=false;
				objform.hrefvalue.disabled=false;
			}
		}
		function kpress(){
			var f=document.ReportForm;
			var oldcontent=document.getElementById("jstext").value;
			window.open("jstext.jsp?oldval="+oldcontent,"mydialog","Top=80,Left=150,Width=400,Height=400,help=no,scroll=yes,status=yes");
		}
	</script>
  </head>
  
  <body>
    <div class="title_right" style="margin-top: 10px;padding-left: 8px"><b>组态报表&gt;&gt;${title }</b></div>
    <div align="center" style="padding-top:20px;">
    	<html:form action="newreportform.do" method="post">
    	<logic:notEqual name="useraction" value="editReport">
    		<table border="0" cellpadding="0" cellspacing="0"   width="800">
    			<tr>
    				<input type="hidden" name="reportinfo" value="${reportinfo }">
		    		<input type="hidden" name="leveltwo" value="${leveltwo }">
		    		<input type="hidden" name="levelthree" value="${levelthree }">
		    		<input type="hidden" name="arrays" value="${arrays }">
		    		<input type="hidden" name="coordx" value="${coordx }">
		    		<input type="hidden" name="coordy" value="${coordy }">
    		
    				<td align="right">显示位置：</td>
    				<td><select name="roworcoll" onchange="javascript:changes(document.ReportForm);" style="width:155px;">
    						<option value="0">列</option>
    						<option value="1">行</option>
    					</select></td>
    				<td align="right">对齐方式：</td>
    				<td><select name="aligntype" style="width:155px;">
    						<option value="0">------居左------</option>
    						<option value="1">------居中------</option>
    						<option value="2">------居右------</option>
    					</select></td>
    				<td>JS内容：<input type="text" name="jstext" id="jstext" onclick="kpress();"></td>
    			</tr>
    			<tr>
    				<td align="right">链接/按钮 名称：</td>
    				<td><input type="text" name="hrefname" value=""></td>
    				<td align="right">点击事件：</td>
    				<td><input type="text" name="onclickthing" value=""></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td align="right">链接跳转到：</td>
    				<td><input type="text" name="hrefvalue" value=""></td>
    				<td align="right">显示标志：</td>
    				<td><input type="text" name="sign" value=""></td>
    				<td><input type="button" name="add" value=" 增 加 " onclick="additem(document.ReportForm)"></td>
    			</tr>
    			<tr>
    				<td colspan="5" align="center" colspan="5" bordercolorlight="#666666" bordercolordark="#FFFFFF" bgcolor="#FFFFFF">
    					<div align="center" style="width:100%; height:230px; overflow-x:hidden; overflow-y:scroll;">
    						<table width="100%" id="contentTable" border="1" cellpadding="0" cellspacing="0">
    							<tr>
		    						<td width="5%" height="20" align='center' background="../images/title_bg.gif">编号</td>
		    						<td width="15%" height="20" align='center' background="../images/title_bg.gif">显示位置</td>
		    						<td width="10%" height="20" align='center' background="../images/title_bg.gif">对齐方式</td>
		    						<td width="20%" height="20" align='center' background="../images/title_bg.gif">链接/按钮 名称</td>
		    						<td width="20%" height="20" align='center' background="../images/title_bg.gif">点击事件</td>
		    						<td width="20%" height="20" align='center' background="../images/title_bg.gif">链接跳转到</td>
		    						<td width="10%" height="20" align='center' background="../images/title_bg.gif">显示标志</td>
		    						<td width="1%"></td>
		    					</tr>
		    				<logic:notEmpty name="buttonlist">
    						<%int i=1; %>
    						<logic:iterate id="list" name="buttonlist">
    							<tr>
    								<td align="center"><%=i %>&nbsp;</td>
    								<td align="center"><logic:equal name="list" property="rowOrColl" value="0">列</logic:equal>
    												   <logic:equal name="list" property="rowOrColl" value="1">行</logic:equal>
    												   <logic:equal name="list" property="rowOrColl" value="">&nbsp;</logic:equal></td>
    								<td align="center"><logic:equal name="list" property="alignType" value="0">居左</logic:equal>
    												   <logic:equal name="list" property="alignType" value="1">居中</logic:equal>
    												   <logic:equal name="list" property="alignType" value="2">居右</logic:equal>
    												   <logic:equal name="list" property="alignType" value="">&nbsp;</logic:equal></td>
    								<td align="center">${list.hrefName }&nbsp;</td>
    								<td align="center">${list.onClickThing }&nbsp;</td>
    								<td align="center">${list.hrefValue }&nbsp;</td>
    								<td align="center">${list.sign }&nbsp;</td>
    								<td align="center"><input type='checkbox' name='rowvalues' value="${list.rowOrColl };${list.alignType };${list.hrefName };${list.onClickThing };${list.hrefValue };${list.sign }" style='display:none'></td>
    							</tr>
    							<%i++; %>
    						</logic:iterate>
    					</logic:notEmpty>
    						</table>
    					</div>
    				</td>
    			</tr>
    			<tr>
    				<td align="center" colspan="5">
		  				<input type="button" name="previous" value="上一步" onclick="javascript:pre();">
		    			<input type="button" name="next" value="下一步" onclick="javascript:nexts();">
		    			<input type="button" name="cancel" value=" 取 消 " onclick="">
  					</td>
    			</tr>
    		</table>
    		</logic:notEqual>
    		
    	<logic:equal name="useraction" value="editReport">
    		<table border="0" cellpadding="0" cellspacing="0"   width="800">
    			<tr>
    				<input type="hidden" name="reportinfo" value="${reportinfo }">
		    		<input type="hidden" name="leveltwo" value="${leveltwo }">
		    		<input type="hidden" name="levelthree" value="${levelthree }">
		    		<input type="hidden" name="arrays" value="${arrays }">
    				<input type="hidden" name="reportFormId" value="${reportFormId }">
    				<input type="hidden" name="coordx" value="${coordx }">
		    		<input type="hidden" name="coordy" value="${coordy }">
		    		
    				<td align="right">显示位置：</td>
    				<td ><select name="roworcoll" onchange="javascript:changes(document.ReportForm);" style="width:155px;">
    						<option value="0">列</option>
    						<option value="1">行</option>
    					</select></td>
    				<td align="right">对齐方式：</td>
    				<td><select name="aligntype" style="width:155px;">
    						<option value="0">------居左------</option>
    						<option value="1">------居中------</option>
    						<option value="2">------居右------</option>
    					</select></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td align="right">链接/按钮 名称：</td>
    				<td><input type="text" name="hrefname" value=""></td>
    				<td align="right">点击事件：</td>
    				<td><input type="text" name="onclickthing" value=""></td>
    				<td></td>
    			</tr>
    			<tr>
    				<td align="right">链接跳转到：</td>
    				<td><input type="text" name="hrefvalue" value=""></td>
    				<td align="right">显示标志：</td>
    				<td><input type="text" name="sign" value=""></td>
    				<td><input type="button" name="add" value=" 增 加 " onclick="additem(document.ReportForm)"></td>
    			</tr>
    			<tr>
    				<td colspan="5" align="center" colspan="5" bordercolorlight="#666666" bordercolordark="#FFFFFF" bgcolor="#FFFFFF">
    					<div align="center" style="width:100%; height:230px; overflow-x:hidden; overflow-y:scroll;">
    						<table width="100%" id="contentTable" border="1" cellpadding="0" cellspacing="0">
    							<tr>
		    						<td width="5%" height="20" align='center' background="../images/title_bg.gif">编号</td>
		    						<td width="15%" height="20" align='center' background="../images/title_bg.gif">显示位置</td>
		    						<td width="10%" height="20" align='center' background="../images/title_bg.gif">对齐方式</td>
		    						<td width="20%" height="20" align='center' background="../images/title_bg.gif">链接/按钮 名称</td>
		    						<td width="20%" height="20" align='center' background="../images/title_bg.gif">点击事件</td>
		    						<td width="20%" height="20" align='center' background="../images/title_bg.gif">链接跳转到</td>
		    						<td width="10%" height="20" align='center' background="../images/title_bg.gif">显示标志</td>
		    						<td width="1%"></td>
		    					</tr>
		    				<logic:notEmpty name="buttonlist">
    						<%int j=1; %>
    						<logic:iterate id="list" name="buttonlist">
    							<tr>
    								<td align="center"><%=j %>&nbsp;</td>
    								<td align="center"><logic:equal name="list" property="rowOrColl" value="0">列</logic:equal>
    												   <logic:equal name="list" property="rowOrColl" value="1">行</logic:equal>
													   <logic:equal name="list" property="rowOrColl" value="">&nbsp;</logic:equal></td>
    								<td align="center"><logic:equal name="list" property="alignType" value="0">居左</logic:equal>
    												   <logic:equal name="list" property="alignType" value="1">居中</logic:equal>
    												   <logic:equal name="list" property="alignType" value="2">居右</logic:equal>
    												   <logic:equal name="list" property="alignType" value="">&nbsp;</logic:equal></td>
    								<td align="center">${list.hrefName }&nbsp;</td>
    								<td align="center">${list.onClickThing }&nbsp;</td>
    								<td align="center">${list.hrefValue }&nbsp;</td>
    								<td align="center">${list.sign }&nbsp;</td>
    								<td align="center"><input type='checkbox' name='rowvalues' value="${list.rowOrColl };${list.alignType };${list.hrefName };${list.onClickThing };${list.hrefValue };${list.sign }" style='display:none'></td>
    							</tr>
    							<%j++; %>
    						</logic:iterate>
    					</logic:notEmpty>
    						</table>
    					</div>
    				</td>
    			</tr>
    			<tr>
    				<td align="center" colspan="5">
		  				<input type="button" name="previous" value="上一步" onclick="javascript:pre();">
		    			<input type="button" name="next" value="下一步" onclick="javascript:nexts();">
		    			<input type="button" name="cancel" value=" 取 消 " onclick="">
  					</td>
    			</tr>
    		</table>
    		</logic:equal>	
    	</html:form>
    </div>
  </body>
</html>
