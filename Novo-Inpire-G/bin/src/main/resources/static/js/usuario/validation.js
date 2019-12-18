$(document).ready(function(){
	
	
	$("#cpf").blur(function(){
		var cpf = $(this).val();
		
		$.ajax({
			 method: "get",
			 dataType: "json",
			 url: "http://localhost:9000/verificationCPF",
			 data: { cpf: cpf },
			 success: function(json){
	              if(json.retorno){
	                  console.log(jason.retorno);
	              }else{
	                  
	              }
	          }
		  });
	});
});