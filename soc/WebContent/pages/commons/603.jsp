<%@ include file="/pages/commons/meta.jsp"%>
<%@ include file="/pages/commons/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type='text/javascript' src="${ctx}/scripts/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>您的操作被策略限制</title>

<link rel="stylesheet" type="text/css" href="${ctx}/styles/error.css"
	media="screen" />
<style>
#center {
	margin: 50px auto;
	width: 800px;
}

#loading {
	width: 797px;
	height: 49px;
	border: 1px solid #4297d7;
	/* background: url(${ctx}/images/bak.png) no-repeat; */
}

#loading div {
	width: 0px;
	float: left;
	height: 47px;
	background: #4297d7;
	color: #fff;
	text-align: center;
	font-family: Tahoma;
	font-size: 18px;
	line-height: 48px;
}
#message {
	width: 200px;
	height: 35px;
	font-family: Tahoma;
	font-size: 12px;
	background-color: #d8e7f0;
	border: 1px solid #187CBE;
	display: none;
	line-height: 35px;
	text-align: center;
	margin-bottom: 10px;
	margin-left: 50px;
}
</style>
<script type="text/javascript">
	var xmlHttp;
	function createXMLHttpRequest()//创建XMLHttpRequest对象 
	{
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}
	function go() {
		createXMLHttpRequest();//创建XMLHttpRequest对象 
		xmlHttp.onreadystatechange = callBack;//设置回调函数 
		var url = "${ctx}/vulnerability/showUpdatePercent.action";//请求的地址 
		xmlHttp.open("post", url, true);//打开对服务器的连接 
		xmlHttp.send();//发送请求 
	}
	function callBack() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				setTimeout("pollServer()", 1000);//定时调用 
			}
		}
	}
	function pollServer() {
		createXMLHttpRequest();
		var url = "${ctx}/vulnerability/showUpdatePercent.action";
		xmlHttp.onreadystatechange = pollCallBack;
		xmlHttp.open("post", url, true);
		xmlHttp.send();
	}
	function pollCallBack() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				
				var progress_id = "loading";
				
				var temps = xmlHttp.responseText;
				
				$("#showPercent").html("当前进度" + temps + "%");
				$("#" + progress_id + " > div").css("width",
						String(temps) + "%"); //控制#loading div宽度 
				$("#" + progress_id + " > div").html(String(temps) + "%"); //显示百分比 
				
				if (temps == '100') {
					$("#message").html("升级完成！").fadeIn("slow");//加载完毕提示 
					$(".news-error").html("恭喜您，升级完成");
					$(".text-error").html(
							"请<a href='${ctx}'  target='_top'>点击这里</a>，回到首页。");
				}
				if (temps != '100') {
					setTimeout("pollServer()", 1000);
				}
			}
		}
	}
</script>
</head>

<body class="lgbody" onload="go();">
	<c:if test="${requestScope.type eq 'alert'}">
		<script language="javascript">
			parent.window.alert('您受到禁止/允许策略限制，不能访问此设备！');
		</script>
	</c:if>

	<center>
		<div id="container">
			<!-- content star-->
			<div class="news-error">系统升级中</div>
			<div class="m-content">
				<div class="cp-content1">
					<div class="error-div"></div>
					<div class="clear"></div>
					<div class="clear"></div>
					<table width="100%" border="0" cellpadding="5" cellspacing="1">
						<tr>
							<td>&nbsp;</td>
							<td width="632">&nbsp;</td>
						</tr>
						<tr>
							<td width="213" rowspan="3" valign="top"><table width="100%"
									border="0" cellspacing="1" cellpadding="3">
									<tr>
										<td width="59%" align="right"><img
											src="${ctx}/images/403_pic.jpg" width="78" height="75" /></td>
										<td width="41%" align="center"><span class="h_1">提示</span>
										</td>
									</tr>
								</table></td>
							<td height="7"></td>
						</tr>
						<tr>
							<td rowspan="2" class="text-error">系统升级中请稍后登录</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- content end-->

		</div>
		<div id="center">
			<div id="message"></div>
			<div id="loading">
				<div></div>
			</div>
			<span id="showPercent" style="align:center"></span>
		</div>
	</center>

</body>
</html>
