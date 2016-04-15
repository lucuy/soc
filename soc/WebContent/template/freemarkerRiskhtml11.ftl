<!DOCTYPE html>
<html>
  <head>
    
    <title>资产组评估饼状图</title>
    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="this is my page" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<style type="text/css">
.sbox {
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
  <table width="90%">
   <tr>
  <td><img src="${image}"/></td>
  </tr>
 
    <tr>
  <td>
  <div class="sbox">
						<div class="cont">
							<!-- information area -->
							<div id ="dataList">
  <table   width="100%" border="0" cellspacing="1" cellpadding="0"
									class="tab2" id="listTable">
  <tr height="28" class="biaoti"><th width="50%" class="biaoti">名称</th>
  <th  width="50%" class="biaoti">值</th></tr>
  <#list assetmap as table2>
  <tr>
  <td   valign="middle">${table2.key}
  
  </td>
   <td  valign="middle">${table2.value}
  
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