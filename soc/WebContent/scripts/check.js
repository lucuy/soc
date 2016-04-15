//验证之能是汉字，数字，字母
function yanzheng1(varStr){
	 if(varStr.value==""){
         return;
       }
       
	 var re =/^[a-zA-Z0-9\u4e00-\u9fa5]+$/g;//汉字，数字，字母
		
	 if (!(re.test(varStr.value))) {
		   alert("请不要输入特殊字符！");
		   varStr.value=""; 
		   return false;
	 }
	 if(varStr.value.length>50){
		 alert("搜索长度不能大于50");
		 varStr.value=""; 
		 return false;
	 }
}

//验证身份证
function isCardNo(varStr)  
	{  
   if(varStr.value==""){
   	return;
   }
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(varStr.value) === false) {  
       varStr.value="";
       alert("身份证输入不合法");  
       return  false;  
   }  
}
//重置表头复选框状态
function resetCheckBox() {
	if (!$("#chkAll").hasClass('not_checked')) {
		$("#chkAll").addClass('not_checked');
		$(".check-box").attr('checked', false);
	}
}
