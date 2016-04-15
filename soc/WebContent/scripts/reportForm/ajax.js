function All(e)
{
 var f=window.clientForm;
for(i=0;i<f.elements.length;i++){
		if(f.elements[i].name=="clientid"){
      		f.elements[i].checked=e.checked;}
			}
}