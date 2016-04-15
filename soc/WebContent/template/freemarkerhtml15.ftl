<!DOCTYPE html>
<html>
  <head>
    
    <title>趋势报表</title>
    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="this is my page" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<style type="text/css">
.sbox {
	text-align:center;
    clear: both;
    margin-bottom: 10px;
    margin-top: 4px;
    overflow: hidden;
    
}
.tab2 {
    background: none repeat scroll 0 0 #D2E8FA;
}
.tab2 td {
    background: none repeat scroll 0 0 #FFFFFF;
    line-height: 28px;
    text-align: center;
}
.tab2 .td_t {
    background: none repeat scroll 0 0 #ffffff;
    line-height: 28px;
    text-align: left;
    padding-left: 20px;
}

.tab2 .biaoti {
    background: url("/scan/images/tdBg.jpg") repeat-x scroll 0 0 transparent;
    height:28px;
    color: black;
    font-weight: bold;
    text-align: center;
    
    
}
.cont{ clear: both;overflow:hidden; border-top:none; zoom:1;}
.btnstyle{
	 background:url(/scan/images/btnbg.jpg) no-repeat; 
	 border:none; 
	 width:66px; 
	 height:21px;
	 cursor:pointer;
	 color:#265D86;
}
</style>
  </head>
  
  <body >
  <table width="100%">
  <tr height="28" class="biaoti">
  <th width="100%" align="center" style="font-size: 25px;font-weight:bold;" class="biaoti">趋势报表</th>
  
  </tr>
  <tr height="28" class="biaoti">
 <th width="100%" align="center" style="margin-left: 200px;">生成时间：${time}</th>
  
  </tr>
  
   <tr>
  <td><img src="${image}"/></td>
  </tr>
 
    <tr>
  <td align="center" width="100%">
  <div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id ="dataList">
  <table   width="90%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="listTable">
  <tr height="28" class="biaoti">
  <th width="33%" class="biaoti">时间</th>
  <th  width="33%" class="biaoti">地址</th>
   <th  width="33%" class="biaoti">数量</th>
  </tr>
  <#list voList as vo>
  <tr>
  <td   valign="middle">${vo.trendTime}
  
  </td>
   <td  valign="middle">${vo.trendIp}
  
  </td>
  <td  valign="middle">${vo.trendCount}
  
  </td>
  </tr>
  </#list>
  
  </table>
  </div></div></div>
  
  </td>
  
  </tr>
  
  </table>
  
  </body>
</html>