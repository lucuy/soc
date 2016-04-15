<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@page import="com.soc.model.user.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%-- <script type="text/javascript" src="${ctx}/pushlet/ajax-pushlet-client.js"></script>--%>

<link href="${ctx}/styles/systemsetting/systemsetting.css"
	rel="stylesheet" type="text/css">
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<script type='text/javascript' src="${ctx}/scripts/util.js"></script>
<script type='text/javascript' src="${ctx}/scripts/popuplayer.js"></script>
<script type='text/javascript' src="${ctx}/scripts/ajaxfileupload.js"></script>
<link href="${ctx}/styles/template.css" rel="stylesheet" type="text/css">
<link type="text/css"
    href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css"
    rel="stylesheet" />
<script type="text/javascript"
    src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
<script>
    function openSuccess(toggle,content)
    {
        $("#button").html("");
        var docHe =  ($(document).height()/2)-40;
        var docWi =  ($(document).width()/2)-200;
        $("#openSuccess").css({top:docHe,left:docWi});
        $("#msg").text(content);
        if(toggle == "slow")
        {
            $("#button").html("<input type=\"button\" value=\"确&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定\" onclick=\"sure()\" class=\"btnyh\" />&nbsp;&nbsp;"+
            "<input type=\"button\" value=\"取&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;消\" onclick=\"cancel()\" class=\"btnyh\" />&nbsp;&nbsp;");
        }
        $("#openSuccess").show();
    }

	function subdata() {
		
		var upgradeFile = $("#upTarBag");
		if (upgradeFile.val() == "") {
			alert("文件未选择！");
			return;
		}
		else
		{
			
		  parent.frames[0].reload(); 
          parent.frames[1].reload(); 
          parent.frames[3].reload();
          parent.frames[4].reload();
		}
	}
	function checkValue()
	{
	   var tar = $("#oemTar").val();
	   if(tar != "")
	   {
	       
	       var sb = tar.split(".");
	     
	       if(sb[1]=="tar"){
	    	   if(sb[2]=="gz"){
	    		   $("#tarerror").text("");
		           $("#btnSave").removeAttr("disabled");
	    	   } else
		       {
		           $("#btnSave").attr("disabled","disabled");
		           $("#tarerror").text("请选择正确的升级包");
		       }
	       } else
	       {
	           $("#btnSave").attr("disabled","disabled");
	           $("#tarerror").text("请选择正确的升级包");
	       }
	       
	   }
	}
	
	function ContextMenu(){
      if (event.button==2 || event.button==3) {  
             alert("修改OEM中无法操作");
             return false;
        }
  }
	   function reload() {
		  
	    document.onmousedown=ContextMenu;
        $("#mack").addClass("ui-widget-overlay");
        $("form").submit();
        openSuccess("hide","文件上传中请稍候...");
       // $("#img").css("display", "block");
        //$("#prog").text("文件上传中");
    }
	   function cancel()
       {
           $("#openSuccess").hide();
           $("#mack").removeClass("ui-widget-overlay");
       }
	   function sure()
	    {   
	        openSuccess("hide","修改OEM请稍候...");
	        var oemTarFileName = $("#oemTarFileName").val();
	        $.ajax({
	                type:"POST",
	                url:"${ctx}/settingOEM/updateOEMAction.action?",
	                async : true,
	                data:"oemTarFileName="+oemTarFileName,
	                success:function(result)
	                {
	                    if(result == "tomcat6")
	                    {
	                        openSuccess("hide","请关闭浏览器，清空缓存。重新登录！");
	                    }else
	                    {
	                        cancel();
	                    }
	                },
	                error:function(XMLHttpRequest, textStatus, errorThrown)
	                {
	                    
	                }
	        });
	    }
	   function cancel()
	    {
	         $("#openSuccess").hide();
	         $("#mack").removeClass("ui-widget-overlay");
	         parent.frames[0].cancel();
	         parent.frames[1].cancel();
	         parent.frames[4].cancel();
	    }
	    
	    function checkUpStart(stuts)
	    {
	        if(stuts == "true")
	        {
	           $("#mack").addClass("ui-widget-overlay");
	           openSuccess("slow","确定修改OEM信息吗？");
	        }
	    }
</script>
</head>

<body style="margin-top: 2px" onload="checkUpStart('${upStatr}')">
<input type="hidden" value="${oemTarFileName}" id="oemTarFileName"/>
	<s:form id="upOEMTar" action="upOEMTar.action" namespace="/settingOEM"
		method="post" theme="simple" name="oemTar"
		enctype="multipart/form-data">
		<!--  左侧table-->
		<table width="99%" border="0" cellspacing="0" cellpadding="0"
			style="margin-left: 4px; margin-top: 0px">
			<tr>
				<td width="100%" valign="top">
					<div class="framDiv">
						<!--  左侧table-->
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td class="r2titler"><b>修改OEM</b>&nbsp;&nbsp;&nbsp; <font
									id="message1"></font>
								</td>
							</tr>

							<tr>
								<td align="right"><font color="red"><s:actionmessage />
								</font></td>
							</tr>

							<tr>
								<td>
									<table width="94%" border="0" cellspacing="0" cellpadding="0"
										style="margin-left: 18px;">
										<!-- 空行 -->
										
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

										<tr>
											<td colspan="4"><div class="xuxian"></div>
											</td>
										</tr>

										<tr>
											<td class="td_detail_separator"></td>
										</tr>
										<tr>
											<td width="30%">请上传需要修改的OEM包<font color="red">(上传包的格式例如：xxx.tar.gz)</font></td>

											<td colspan="2"><input style="width:270px;" id="oemTar" name="oemTar"
												type="file" onchange="checkValue()"/>&nbsp;&nbsp;<span><font color="red" id="tarerror"></font></span></td>
										</tr>
										<!-- 空行 -->
										<tr>
											<td class="td_detail_separator"></td>
										</tr>

									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>

			<tr>
				<td class="td_detail_separator"></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="button" class="btnyh" id="btnSave"
					value="修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改" onclick="subdata();" disabled="disabled"  />
				</td>
			</tr>
		</table>
  </s:form>
	<div class="ui-overlay">
        <div id="mack"></div>
        <div id="img" style="width: 130px; height: 130px; position: absolute; left: 380px; top: 180px; display: none;">
            <div style="position:absolute ; top:55px; left:35px; font-size: 12px; width:80px; color:#FFFFFF;" id="prog"></div><img src="${ctx}/images/loading.gif" />
        </div>
    </div>
    <div id="article"  style="border:1px solid #3399cc; height:15px; width:400px; display:none; position: absolute;z-index:20; background: #FFFFFF;"><div id="upSize"  style="background-color:#3399cc;height:13px; margin:1px 0px 1px 0px; text-align: center;"></div></div>
   
        <div class="framDiv"  id="openSuccess" style="width:30%;display: none;position: absolute;z-index:10; background:#FFFFFF;">
            <table width="100%" border="0" cellspacing="1" cellpadding="0">
                <tr>
                    <td class="r2titler"><b>修改OEM信息</b></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td align="center"><font style="font-size:16px;"><b id="msg"></b></font></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td class="td_detail_separator"></td>
                </tr>
                <tr>
                    <td align="right" id="button">
		                    
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            </table>
                   </div>
    
</body>
</html>