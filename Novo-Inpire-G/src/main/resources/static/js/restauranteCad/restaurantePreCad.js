$(document).ready(function(){

	//Buscar por cnpj
	$("#cnpj").blur(function(){
		var cnpj = $('#cnpj').val();
		
		if(cnpj != ""){
			
			consultaCNPJ(cnpj).then((json) => {
			    console.log(json);
			    $("#btn").prop("disabled", true);
			    $('#erro').html("");
			}, (erro) => {
				$("#btn").prop("disabled", false);
				$('#erro').html(erro);
			    console.log('ERRO:', erro);
			});
			
		}
	});
		
	//Checando se o campo CNPJ esta preenchido
	$('#cnpj').change(function(){
		var valor = $('#cnpj').val();
		
		if(valor.length > 0){
			$("#noCNPJ").prop("disabled", true);
		}else {
			$("#noCNPJ").prop("disabled", false);
		}
	});
	
	
	//Mudando o valor do checkbox
	$("#noCNPJ").click(function(){		
		var t = $("#inv").val();
		
		if(t == '0'){
			 $('#inv').val('1');
			 $("#cnpj").prop("disabled", true);
		}else{		
			 $('#inv').val('0');
			 $("#cnpj").prop("disabled", false	);
		}
			 
			 
	})
	
	//Habilitanto o campo cnpj para não dar erro no controller
	$("#btn").click(function(){	
		$("#cnpj").prop("disabled", false);
	});
	
	
	 
	 

		 

	 
});