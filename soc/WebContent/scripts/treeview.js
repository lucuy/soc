/**
 * 文件名：treeview.js 
 * 功能说明：与EmployeeTree和ResourceTree配合，实现的动态效果。需要JQUERY
 * 作者：郭煜玺
 * 日期：2011-04-18
 */

/**
 * 隐藏显示结点
 */
var index = 0;
function node(id)
{
	if(index == 0){
		$('#span_node_'+id).hide();
		$('#img_'+id).attr("src","/soc/images/tree/plus.gif");
		index = 1;
	}else{
		$('#span_node_'+id).show();
		$('#img_'+id).attr("src","/soc/images/tree/minus.gif");
		index = 0;
	}
}
function node2(id)
{
	if(index == 0){
		$('#span_node_'+id).hide();
		$('#img_'+id).attr("src","/soc/images/node_left2.png");
		index = 1;
	}else{
		$('#span_node_'+id).show();
		$('#img_'+id).attr("src","/soc/images/node_down.png");
		index = 0;
	}
}
