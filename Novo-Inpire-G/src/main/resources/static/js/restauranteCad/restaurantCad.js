 $(document).ready(function(){

    //Buscar endereço por cep
	$('#cep').blur(function(){
		
		var cep = $('#cep').val();
		
		console.log(cep);
		
		$.getJSON("https://viacep.com.br/ws/"+cep+"/json", function(result) {
	        if (("erro" in result)) {
	          self.error = "CEP não encontrado";
	          self.city = "";
	        } else {
	          	self.city = result.logradouro +", "+result.bairro+ " - "+ result.localidade + "/" + result.uf;
	       		console.log(self.city);
	  			
	       		$('#bairro').val(result.bairro);
	      		$('#cidade').val(result.localidade);
	      		$('#rua').val(result.logradouro);
				$('#uf').val(result.uf);
		   	        
		   	        };
	   	        	console.log(result);
			
	   	        });
   	        
	});
	
	
	//Buscar por cnpj
	var cnpj = $('#cnpj').val();
	
	if(cnpj != ""){
		
		consultaCNPJ(cnpj).then((json) => {
		    console.log(json);
		    
		    var dados = json;
		    
		    $('#nome').val(json.nome)
		    $('#telefone').val(json.telefone);
		    $('#email').val(json.email);
		    $('#cep').val(json.cep);
       		$('#bairro').val(json.bairro);
      		$('#cidade').val(json.municipio);
      		$('#rua').val(json.logradouro);
      		$('#numero').val(json.numero);
			$('#uf').val(json.uf);
			
			console.log(dados);
		}, (erro) => {
		    console.log('ERRO:', erro);
		});
		
	}
	

        
});