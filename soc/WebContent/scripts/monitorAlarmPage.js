	function paging_changeRecord(url,num,event,keywordId,assetId){
		
		//alert(assetId);
		document.getElementById('startIndex').value=num;
		var keyword = $.trim($("#"+keywordId).val());
		//alert(event);
		if(keyword){
			eval(event+"(url+'?startIndex='+encodeURI(encodeURI(num,'utf-8'))+'&keyword=' +encodeURI(encodeURI(keyword,'utf-8'))+'&AssetId=' +assetId,num)");
		}else{
			$("#"+keywordId).val("");
			eval(event+"(url+'?startIndex='+encodeURI(encodeURI(num,'utf-8'))+'&AssetId=' +encodeURI(encodeURI(assetId,'utf-8')),num)");
			//eval(event+"(url+'?startIndex='+encodeURI(encodeURI(num,'utf-8'))+'&queryEventsType=0&t='+new Date(),num)");
		
			
		}
	}
	function paging_gopage(lastnum,keywordId,url,event,assetId)
	{
		
		//alert(assetId);
		var page=document.getElementById('paging_Page').value;
		
		var keyword = $.trim($("#"+keywordId).val()+"");
		
		var num=parseInt(page*15)-parseInt(15);
		
		if(num > lastnum || num < 0)
		{
			alert("错误页数");
			return false;
		}
		
		if(num<0)num=0;
			
		document.getElementById('startIndex').value=num;
		
		if(keyword){
			eval(event+"(url+'?startIndex='+encodeURI(encodeURI(num,'utf-8'))" +
					"'&keyword=' + encodeURI(encodeURI(keyword,'utf-8'))+'&AssetId=' +encodeURI(encodeURI(assetId,'utf-8'))+',num)");
		}else{
			$("#"+keywordId).val("");
			eval(event+"(url+'?startIndex='+encodeURI(encodeURI(num,'utf-8'))+'&AssetId=' +encodeURI(encodeURI(assetId,'utf-8')),num)");
		}
	}
	function paging_page(tableId,url,item,keywordId,event,column,assetId){
		if(item.currentPage != null){
			var htmlPage = "";
			htmlPage = "<tr>";
				htmlPage +="<td width='100%' colspan='"+column+"'"+">";
					htmlPage +="<table id='test12'  width='100%' border='0' cellspacing='1' cellpadding='0'>";
						htmlPage +="<tr>";
							htmlPage +="<td>";
								htmlPage +="<table align='right' >";
									htmlPage +="<tr>";
										htmlPage +="<td>";
										htmlPage +="共"+ item.totalCount +"条记录";
										htmlPage +="<input type='hidden' name='startIndex' id='startIndex' value='0'>";
										htmlPage +="<input type='hidden' name='lastIndex' id='lastIndex' value="+ item.lastIndex + ">";
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.startIndex != 0){
											htmlPage +="<a href=" +"javascript:paging_changeRecord(\""+url+"\",'0',\""+event+"\",\""+keywordId+"\",\""+assetId+"\")"+" >首页</a>";
										}else{
											htmlPage +="首页";
										}																							
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.startIndex != 0){
											htmlPage +="<a href=" + "javascript:paging_changeRecord(\""+url+"\"," + item.previousIndex + ",\""+event+"\",\""+keywordId+"\",\""+assetId+"\") "+ " >上一页</a>";
										}else{
											htmlPage +="上一页";
										}
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.nextIndex > item.startIndex){
											htmlPage +="<a href=" + "javascript:paging_changeRecord(\""+url+"\"," + item.nextIndex + ",\""+event+"\",\""+keywordId+"\",\""+assetId+"\")" + " >下一页</a>";
										}else{
											htmlPage +="下一页";
										}					
										htmlPage +="</td>";
										htmlPage +="<td>";
										if(item.lastIndex == item.startIndex){
											htmlPage +="末页";
										}else{
											htmlPage +="<a href=" + "javascript:paging_changeRecord(\""+url+"\"," + item.lastIndex + ",\""+event+"\",\""+keywordId+"\",\""+assetId+"\")" + " >末页</a>";
										}
										htmlPage +="</td>";
										htmlPage +="<td>";
										htmlPage +=" <input type='text' style='width: 30px' name='paging_Page' id='paging_Page'  size='3' >";
										htmlPage +="<input type='button' value='GO' class='btn1' onclick='javascript:paging_gopage("+item.lastIndex+",\""+keywordId+"\",\""+url+"\",\""+event+"\",\""+assetId+"\")' >";
										htmlPage +="当前第" + item.currentPage + "/" + item.pageCount + "页";													
										htmlPage +="</td>";
									htmlPage +="</tr>";
								htmlPage +="</table>";
							htmlPage +="</td>";
						htmlPage +="</tr>";
					htmlPage +="</table>";
				htmlPage +="</td>";
			htmlPage +="</tr>";
		$(htmlPage).insertAfter($("#"+tableId+""));
		}
	}
