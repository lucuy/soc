<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.01 Transitional//EN">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>

<html>
  <head>
    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css">
    <link type="text/css" href="${ctx}/styles/css/ui-lightness/jquery-ui-1.7.3.custom.css" rel="stylesheet" />    
    <script type="text/javascript" src="${ctx}/scripts/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/js/jquery-ui-1.7.3.custom.min.js"></script>
    <script type="text/javascript">
    var mack;
        function reload(){
        	 mack = document.getElementById("mack");
     	    mack.className="ui-widget-overlay";
            //$("#mack").addClass("ui-widget-overlay");
        }
        
        function cancel()
        {
        	 mack.className="";
           //// $("#mack").removeClass("ui-widget-overlay");
        }
          function ContextMenu(){
            if (event.button==2 || event.button==3) {  
                  alert("升级中无法操作");
                  return false;
        }
  }

</script>
  </head>
  
  <body>
  	<center valign="middle">
  		<table>
  			<tr align="center">
  				<%--<td align="center" rowspan="2">
  					<img src="${footer}" width="24px" height="30px"> 
  				</td>
  				--%><td align="center">
  					版权所有 ${banquan}
  				</td>
  			</tr>
  			<tr>
  				<td align="center"> 
  					${ver}
  				</td>
  			</tr>
  		</table>
  	</center>
  	<div class="ui-overlay">
<div  id="mack"></div>
</div>  
  </body>
</html>
