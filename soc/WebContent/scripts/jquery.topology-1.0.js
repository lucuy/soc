//获得画板
var mainBoard;
var mbWidth;
var mbHeight;
var mbLeft;
var mbTop;

//显示拓扑图

function showTopology(ajaxurl){
	
	checkBoard();
	//loadSrc(ajaxurl,explainData);
	//alert(eval("("+ajaxurl+")"));
	explainData(ajaxurl);
}

function checkBoard(){
	
//获得画板
mainBoard=document.getElementById("board");
mbWidth=mainBoard.style.width.replace("px","")*1;
mbHeight=mainBoard.style.height.replace("px","")*1;
mbLeft=mainBoard.style.left.replace("px","")*1;
mbTop=mainBoard.style.top.replace("px","")*1;
	
}

//获得远程数据

function loadSrc(ajaxurl){
	
		$.ajax({
 		url: ajaxurl+"?r="+new Date(),
		type: 'GET',
		dataType: 'html',
		timeout: 20000,
		data:null,
		error: function(){alert('error');},
		success: function(html){
  			explainData(html);
 		}
	});
		
}

//显示节点数据

function explainData(data){
	
	var obj=data;
	
	var i,j;
	
	var dragSet="";
	
	//创建所有节点
		
	for(i=0;i<obj.nodeList.length;i++){
		
		var nl=obj.nodeList[i];
		//alert(nl.loginName);
		createNode("node_"+nl.id,nl.name,"node_"+nl.fatherId,nl.url,nl.state,nl.top,nl.left,nl.loginName);
				
		if(obj.nodeList[i].fatherId!=0){
		
			dragSet=dragSet+"$('#node_"+nl.id+"').jqDrag();";
		
		}
	}
	//alert(dragSet);
	eval(dragSet);

	//放置所有根节点
	
	var nodeSet=document.getElementsByTagName("div");
	
	for(j=0;j<nodeSet.length;j++){
		
		if(nodeSet[j].getAttribute("fatherId")=="node_0"){
			
			//将节点设置在顶部中央位置
			nodeSet[j].style.left=(mbLeft+(mbWidth-nodeSet[j].style.width.replace("px",""))/2)+"px";
			nodeSet[j].style.top="20px";
			//将节点设置在顶部中央位置
			//nodeSet[j].style.left=nodeSet[j].getAttribute("l")+"px";
			//nodeSet[j].style.top=nodeSet[j].getAttribute("t")+"px";
			drawChildNode(nodeSet[j],1);
			
		}
		
	}
	
}

//重置节点位置

function drawChildNode(fatherObj,level){
	
	var fObjWidth=fatherObj.style.width.replace("px","")*1;
	var fObjHeight=fatherObj.style.height.replace("px","")*1;
	//getAttribute
	
	var fObjLeft=fatherObj.getAttribute("l");
	//var fObjLeft=400*level;
	
	//var fObjTop=fatherObj.style.top.replace("px","")*1;
	var fObjTop=fatherObj.getAttribute("t");
	//alert(fObjLeft);
	var nodeSet=document.getElementsByTagName("div");
	
	var j,m;
	
	var n=0;
	
	var nlength=0;
	
	for(m=0;m<nodeSet.length;m++){
		
		if(nodeSet[m].getAttribute("fatherId")==fatherObj.id){
			
			nlength++;
			
		}
	}
		
	for(j=0;j<nodeSet.length;j++){
		
		if(nodeSet[j].getAttribute("fatherId")==fatherObj.id){
			
			var nodeWidth=nodeSet[j].style.width.replace("px","");
			
			nodeSet[j].style.left=nodeSet[j].getAttribute("l")+"px";
			nodeSet[j].style.top=nodeSet[j].getAttribute("t")+"px";
						
			drawChildNode(nodeSet[j],level+1);
			
			drawLine(nodeSet[j].id);		
			
			n++;
						
		}
		
	}	
	
}

//添加节点信息

function createNode(nodeId,nodeName,fatherId,imgUrl,stateFlag,top,left,loginName){
	
	//在画板上添加新的节点
	var objHtml="<div onMouseMove=\"drawLine('"+nodeId+"')\" id=\""+nodeId+"\" format=\"0\" t=\""+top+"\" l=\""+left+"\" fatherId=\""+fatherId+"\" ></div>";

	mainBoard.innerHTML=mainBoard.innerHTML+objHtml;
	
	//创建所有的节点
	
	//获得新创建的节点对象
	var nodeObj=document.getElementById(nodeId);
	
	try{
		
		nodeObj.style.position='absolute';
		nodeObj.style.top='0px';
		nodeObj.style.left='0px';
		nodeObj.style.zIndex=2;
		nodeObj.style.width = "64px";
  		nodeObj.style.height ="64px"; 		
  		nodeObj.innerHTML ="<table><tr><td><img class =\"flg\" src=\"/soc/images/topo/"+imgUrl+"\"/ id=\""+nodeId+"\" name="+nodeName+" loginname=\""+loginName+"\"></td></tr><tr><td>"+nodeName+"</td><td class= 'buttons'></td></tr></table>";
  		  	
	}catch(e){
	
		//alert(nodeObj);
		
	}
   	//创建所有节点的连接线
   	var objHtmlX,objHtmlM,objHtmlY;
   	if(stateFlag==0){
   		objHtmlX="<div id=\""+nodeId+"_x_"+fatherId+"\" style=\"position:absolute;z-index:1;background-color:#00FF00;display:none;\"><img height=\"1px\" width=\"1px\"/></div>";
   		objHtmlM="<div id=\""+nodeId+"_m_"+fatherId+"\" style=\"position:absolute;z-index:1;background-color:#00FF00;display:none;\"><img height=\"1px\" width=\"1px\"/></div>";
   		objHtmlY="<div id=\""+nodeId+"_y_"+fatherId+"\" style=\"position:absolute;z-index:1;background-color:#00FF00;display:none;\"><img height=\"1px\" width=\"1px\"/></div>";
   	}else if(stateFlag==1){
   		objHtmlX="<div id=\""+nodeId+"_x_"+fatherId+"\" style=\"position:absolute;z-index:1;background-color:#FF0000;display:none;\"><img height=\"1px\" width=\"1px\"/></div>";
   		objHtmlM="<div id=\""+nodeId+"_m_"+fatherId+"\" style=\"position:absolute;z-index:1;background-color:#FF0000;display:none;\"><img height=\"1px\" width=\"1px\"/></div>";
   		objHtmlY="<div id=\""+nodeId+"_y_"+fatherId+"\" style=\"position:absolute;z-index:1;background-color:#FF0000;display:none;\"><img height=\"1px\" width=\"1px\"/></div>";
   	}
	mainBoard.innerHTML=mainBoard.innerHTML+objHtmlX;
	mainBoard.innerHTML=mainBoard.innerHTML+objHtmlM;
	mainBoard.innerHTML=mainBoard.innerHTML+objHtmlY;
	
}

//画连接节点的层

function drawLine(node){
	try{
	
	//变更与父结点之间的关系
	
	var nodeObj_1=document.getElementById(node);
	var nodeObj_2=document.getElementById(nodeObj_1.getAttribute("fatherId"));
		
	var obj1Width=nodeObj_1.style.width.replace("px","")*1;
	var obj1Height=nodeObj_1.style.height.replace("px","")*1;
	var obj1Left=nodeObj_1.style.left.replace("px","")*1;
	var obj1Top=nodeObj_1.style.top.replace("px","")*1;
	
	var obj2Width=nodeObj_2.style.width.replace("px","")*1;
	var obj2Height=nodeObj_2.style.height.replace("px","")*1;
	var obj2Left=nodeObj_2.style.left.replace("px","")*1;
	var obj2Top=nodeObj_2.style.top.replace("px","")*1;
	
	//本节点至中间点
	var line_x=document.getElementById(nodeObj_1.id+"_x_"+nodeObj_2.id);
	//两点连接
	var line_m=document.getElementById(nodeObj_1.id+"_m_"+nodeObj_2.id);
	//服务器置中间点
	var line_y=document.getElementById(nodeObj_1.id+"_y_"+nodeObj_2.id);
	
	var line_x_piont_x=obj1Left+obj1Width/2;
	var line_x_piont_y=obj1Top+obj1Height/2;
	
	var line_y_piont_x=obj2Left+obj2Width/2;
	var line_y_piont_y=obj2Top+obj2Height/2;

	var line_m_piont_x=(line_x_piont_x-line_y_piont_x)/2;
	var line_m_piont_y=(line_x_piont_y-line_y_piont_y)/2;
	
	//本节点在父节点右下角
	if(line_m_piont_x>0 && line_m_piont_y>0){
		
		//-----------------------------------
		
		line_x.style.left=(line_x_piont_x-line_m_piont_x)+"px";
		
		line_x.style.top=line_x_piont_y+"px";
		
		line_x.style.width=(line_m_piont_x)+"px";
		
		line_x.style.height="2px";
		
		//-----------------------------------
		
		line_y.style.left=line_y_piont_x+"px";
		
		line_y.style.top=line_y_piont_y+"px";
		
		line_y.style.width=line_m_piont_x+"px";
		
		line_y.style.height="2px";
		
		//-----------------------------------
		
		line_m.style.left=(line_y_piont_x+line_m_piont_x)+"px";
		
		line_m.style.top=line_y_piont_y+"px";
		
		line_m.style.width="2px";
		
		line_m.style.height=line_m_piont_y*2+"px";
		
		//本节点在父节点右上角
	}else if(line_m_piont_x>0 && line_m_piont_y<0){
		
		//-----------------------------------
		
		line_x.style.left=(line_x_piont_x-line_m_piont_x)+"px";
		
		line_x.style.top=line_x_piont_y+"px";
		
		line_x.style.width=(line_m_piont_x)+"px";
		
		line_x.style.height="2px";
		
		//-----------------------------------
		
		line_y.style.left=line_y_piont_x+"px";
		
		line_y.style.top=line_y_piont_y+"px";
		
		line_y.style.width=line_m_piont_x+"px";
		
		line_y.style.height="2px";
		
		//-----------------------------------
		
		line_m.style.left=(line_y_piont_x+line_m_piont_x)+"px";
		
		line_m.style.top=line_x_piont_y+"px";
		
		line_m.style.width="2px";
		
		line_m.style.height=(-line_m_piont_y)*2+"px";
		
		
	//本节点在父节点左下角
	}else if(line_m_piont_x<0 && line_m_piont_y>0){
		
		//-----------------------------------
		
		line_x.style.left=line_x_piont_x+"px";
		
		line_x.style.top=line_x_piont_y+"px";
		
		line_x.style.width=(-line_m_piont_x)+"px";
		
		line_x.style.height="2px";
		
		//-----------------------------------
		
		line_y.style.left=(line_y_piont_x+line_m_piont_x)+"px";
		
		line_y.style.top=line_y_piont_y+"px";
		
		line_y.style.width=(-line_m_piont_x)+"px";
		
		line_y.style.height="2px";
		
		//-----------------------------------
		
		line_m.style.left=(line_y_piont_x+line_m_piont_x)+"px";
		
		line_m.style.top=line_y_piont_y+"px";
		
		line_m.style.width="2px";
		
		line_m.style.height=line_m_piont_y*2+"px";
		
	//本节点在父节点左上角
	}else if(line_m_piont_x<0 && line_m_piont_y<0){
		
		//-----------------------------------
		
		line_x.style.left=line_x_piont_x+"px";
		
		line_x.style.top=line_x_piont_y+"px";
		
		line_x.style.width=(-line_m_piont_x)+"px";
		
		line_x.style.height="2px";
		
		//-----------------------------------
		
		line_y.style.left=(line_y_piont_x+line_m_piont_x)+"px";
		
		line_y.style.top=line_y_piont_y+"px";
		
		line_y.style.width=(-line_m_piont_x)+"px";
		
		line_y.style.height="2px";
		
		//-----------------------------------
		
		line_m.style.left=(line_y_piont_x+line_m_piont_x)+"px";
		
		line_m.style.top=line_x_piont_y+"px";
		
		line_m.style.width="2px";
		
		line_m.style.height=(-line_m_piont_y)*2+"px";
		
	//其他
	}else{
		
		
	}
	
	line_x.style.display="";
	line_m.style.display="";
	line_y.style.display="";
	
	//变更与字节点之间的关系
	
	var i;
	
	var nodeChildSet=document.getElementsByTagName("div");
	
	for(i=0;i<nodeChildSet.length;i++){
		
		if(nodeChildSet[i].getAttribute("fatherId")==node){
			
			drawLine(nodeChildSet[i].id);
			
		}
		
	}
	
	}catch(e){}

}
