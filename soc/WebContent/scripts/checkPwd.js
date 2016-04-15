function checkPass(){
	var password = document.getElementById("userPwd").value;
	if(password.length<8){
		alert("密码长度不能小于8位！！")
	}
	var hasNum = false;
	var hasAlphabet =false;
	var hasChar = false;
	var reg =  /\d+/;
	if(password.match(reg)){
		hasNum=true;
	}
	reg = /[A-Za-z]+/;
	if(password.match(reg)){
		hasAlphabet = true;
	}
	reg=/[^A-Za-z0-9]+/;
	if(password.match(reg)){
		hasChar = true;
	}
	if((hasNum&&hasAlphabet)||(hasNum&&hasChar)||(hasChar&&hasChar)||(hasNum&&hasAlphabet&&hasChar)){
		
	}else{
		alert("密码复杂度太底请重新设置");	
	}
}