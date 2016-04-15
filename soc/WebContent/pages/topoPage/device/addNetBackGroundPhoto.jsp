<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>拓扑背景图片的添加和修改页面</title>
   <link href="${ctx}/styles/dbStyles/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/right.css" rel="stylesheet" type="text/css">
<link href="${ctx}/styles/dbStyles/template.css" rel="stylesheet" type="text/css">
<LINK href="${ctx}/css/style/jquery-ui.custom.css" type=text/css rel=stylesheet>
<LINK href="${ctx}/css/style/jquery.popuplayer.css" type=text/css rel=stylesheet>
<%-- <link href="${ctx}/css/imageselect.css" media="screen" rel="stylesheet" type="text/css" /> --%> 
<%-- <script type='text/javascript' src="${ctx}/scripts/devicecategoryphoto/imageselect.js"></script> --%>
<SCRIPT src="${ctx}/scripts/util.js" type=text/javascript></SCRIPT>
<SCRIPT src="${ctx}/scripts/popuplayer.js" type=text/javascript></SCRIPT>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine-en.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${ctx}/scripts/jquery.ui.dialog.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/jquery-ui.custom.min.js"></script>
<%-- <script type='text/javascript' src="${ctx}/scripts/devicecategoryphoto/imageselect.js"></script> --%>
 
<style type="text/css">
	.tdleft{
 		width: 20%;
 		text-align: right;
 	}
 	td{
 		font-size: 12px;
 	}

</style>
<script type="text/javascript">
	
	
	function save(){
		var aa = document.getElementsByName("photoStatus");
		var flag=0;
        for (var i=0; i<aa.length; i++){
        	//只要数组中有一个没有选中返回。假如所有的都是选中状态就将全选复选框选中;
	        if(!aa[i].checked){ 
	        	flag++;
	         }
        }
        if(flag==2){
        	alert('请选择所属拓扑！');
	        return;
        }
		$("#deviceCategoryForm").submit();
	}
	
	
	$(document).ready(function(){
		$('select[name=netBackGroundPhotophotoName]').ImageSelect({dropdownWidth:425});
	});
	
	
	function check_All(e, itemName)
    {    
        var aa = document.getElementsByName(itemName);    //获取全选复选框
        for (var i=0; i<aa.length; i++){
         aa[i].checked = e.checked;    //改变所有复选框的状态为全选复选框的状态
        }
    }
    function checkItem(e,allName){
    	var all = document.getElementsByName(allName)[0];
    	if(!e.checked){
            //没被选中全选复选框置为false;
            all.checked = false;
        } else {
            //选中，遍历数组
            var aa = document.getElementsByName(e.name);
            for (var i=0; i<aa.length; i++)
                //只要数组中有一个没有选中返回。假如所有的都是选中状态就将全选复选框选中;
             if(!aa[i].checked){ 
             	return;
             }
             all.checked = true;
        }
    }
    
   
    
    var bV=parseInt(navigator.appVersion);
var IE4=((document.all)&&(bV>=4))?true:false;
var NS4=(document.layers)?true:false;
var z=0;
function LayerV(LayerName,V){
    E=eval('document.'+LayerName);
    if(IE4) E=eval('document.all.'+LayerName+'.style');
    E.visibility=(V?'visible':'hidden');
}

//////////////////////////////////////////////////////////
//下面这个函数实现了当点击图片时，把图片选上
function select(image1){
var html,url1
url1=image1.src

html="<img src="+url1+" width=220 height=100 onclick='javascript:select(this)'>"
html=html+"<a href=javascript:LayerV('Zhong',z=1-z)><font style='font-family: Webdings;'>6</font></a>"
pic.innerHTML=html
LayerV('Zhong',z=1-z)

var url2 = url1;

var index = url2.indexOf("bgImage/");
var url3 = url2.substring(index+8);
//alert(url3);
$("#photoName").val(url3);
//var vvv = $("#deviceCategoryPhotoPath").val();
//alert(vvv);
//var a = $('#defultImage').attr("src");

//getPhotoName();

}



</script>

 

  </head>
  
  <body style="margin: 0">
 <s:form action="updatePhotoName" method="post" namespace="/netBackGroundPhoto" theme="simple" id="deviceCategoryForm"  name="deviceCategoryForm" enctype="multipart/form-data">
 
 		<div id="Zhong" style=" background-color:#fff; visibility:hidden ;width:220px ;height:300px ;overflow:scroll; position:absolute; z-index:4; left:240px; top:165px;"> 
        <s:iterator value="nbgpList" >
			 <img src="/soc/flexhtml/assets/bgImage/${photoName }" width=100%  onclick="javascript:select(this)"><br>
		</s:iterator>
      </div>
 
 
      <table  width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px"  >
	  	<tr>
				<td width="100%" valign="top">
							<!-- information area -->
							<div class="framDiv">
							<table width="100%" border="0" cellspacing="1" cellpadding="0"   >
                                 <tr>
									<td colspan="4" class='r2titler' style="font-size: 12px">网络拓扑背景编辑
									<div style="font-size: 17px; font-weight:bold;   color:red ;display: inline;">
									${requestScope.setSuccess}
		                        </div>
									</td>
									
								</tr>
								<!-- <tr>
									<td class="td_detail_separator">
									</td>
								</tr> -->
								<tr>
								<td></td>
								
		                        
								</tr> 
								
								<tr>
									<td class="tdleft">所属拓扑：&nbsp;</td>
									<td style="text-align:left;">
										<%-- <select style="width: 250px;" name="netBackGroundPhoto.photoStatus"  id="photoStatus"  >
											  <option value="0" <c:if test="${0==netBackGroundPhoto.photoStatus}" >selected="selected"</c:if> >内网拓扑</option>
											  <option value="1" <c:if test="${1==netBackGroundPhoto.photoStatus}" >selected="selected"</c:if> >外网拓扑</option>
										</select> --%>
										<input type="checkbox" class="check-box-mail"
															<c:if test="${0==netBackGroundPhoto.photoStatus}">checked="checked"</c:if>
															name="photoStatus" value="0"
															onclick="checkItem(this,'photoStatuss')" /> 内网拓扑
									   <input type="checkbox" class="check-box-mail"
															<c:if test="${1==netBackGroundPhoto.photoStatus}">checked="checked"</c:if>
															name="photoStatus" value="1"
															onclick="checkItem(this,'photoStatuss')" /> 外网拓扑
										<input type="checkbox" id="chkAll-mail"
															class="check-box-mail not-checked-mail" name="photoStatuss"
															<%-- <c:if test="${2==netBackGroundPhoto.photoStatus}">checked="checked"</c:if> --%>
															onclick="check_All(this,'photoStatus')" value="2"/> 全选
									</td>
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								<tr>
									<td class="tdleft" >背景图片：&nbsp;</td>
									<td style="text-align:left ;" >
										<%-- <select style="width: 350px;" name="netBackGroundPhotophotoName"  id="photoName"  >
											<s:iterator value="nbgpList" >
												  <option value="${photoName }" selected="selected">/soc/flexhtml/assets/bgImage/${photoName }</option>
											</s:iterator>
										</select> --%>
										
										
										<div id=pic style="border: 1">
                          <font style="font-size:12px">
                          <img src="/soc/flexhtml/assets/bgImage/bg1.png"  id="bgImage" name="bgImage" width=220 height=100 onclick="javascript:LayerV('Zhong',z=1-z)" >
                          
                          <a href="javascript:LayerV('Zhong',z=1-z)">
                          <font style="font-family: Webdings;">6</font>
                          </a>
                          </div>
                          
                         <input type="hidden" name="netBackGroundPhotophotoName"  id="photoName"  value="bg1.png"  />
										
										
										
									</td>
									
								 	<!-- <td style="text-align:left"><span id="div_photoName" ></span></td> -->
								</tr>
								<tr>
										<td class="td_detail_separator"></td>
								</tr>
								
							</table>
							</div>
					</td>
			</tr>
		<tr>
		  		<td>
		
	               <div >
					 
	                   <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                    <tr height="30">							 
										<td align="right"  >
											<!-- <input type="button" value="取消"   class="btnyh"  onclick="back()" /> -->
											<input type="button"  value="保存"   class="btnyh" id="btnSub" onclick="save()" />
										</td>
									</tr>
									
	                   </table>
		         </div>
		  	</td>
		 </tr>

</table>
</s:form>
	
  </body>
</html>
