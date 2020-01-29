$(document).ready(function(){
	
	
	jQuery.validator.addMethod("cpf", function(value, element) {
		   value = jQuery.trim(value);

		    value = value.replace('.','');
		    value = value.replace('.','');
		    cpf = value.replace('-','');
		    while(cpf.length < 11) cpf = "0"+ cpf;
		    var expReg = /^0+$|^1+$|^2+$|^3+$|^4+$|^5+$|^6+$|^7+$|^8+$|^9+$/;
		    var a = [];
		    var b = new Number;
		    var c = 11;
		    for (i=0; i<11; i++){
		        a[i] = cpf.charAt(i);
		        if (i < 9) b += (a[i] * --c);
		    }
		    if ((x = b % 11) < 2) { a[9] = 0 } else { a[9] = 11-x }
		    b = 0;
		    c = 11;
		    for (y=0; y<10; y++) b += (a[y] * c--);
		    if ((x = b % 11) < 2) { a[10] = 0; } else { a[10] = 11-x; }

		    var retorno = true;
		    if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10]) || cpf.match(expReg)) retorno = false;

		    return this.optional(element) || retorno;

		}, "Informe um CPF válido");

	//PRE FORM USUARIO
	$("#formPreUsuario").validate({
		rules:{
			cpf:{
				required: true,
				cpf: true,
				minlength: 11,
				remote:{
					url: '/verificationCPF',
					type: "post",
					data:{
						cpf: function()
	                      {
	                          return $('#cpf').val();
	                      }
					}
				}
			},
			submitHandler: function(form){
				form.submit()
			}
			
		},
		messages:{
			cpf:{
				required: "O campo cpf é obrigatório.",
				remote: "CPF informado já está vinculado a uma conta!",
				cpf: "CPF invalido",
				
			}
		}
				
	});
	//FORM USUARIO
	$("#formUsuario").validate({
		rules:{
			nomeUsuario: {
				required: true,
				maxlength: 50,
				minlength: 4
			},
			email:{
				required: true,
				email:true,
				remote:{
					url: '/verificationEmail',
					type: "post",
					data:{
						email: function()
	                      {
	                          return $('#email').val();
	                      }
	
					}
				}
			},
			senha:{
				required: true,
				minlength: 6
	
			},
			cpf:{
				required: true,
	
			},
			submitHandler: function(form){
				form.submit()
			}
	
		
			
		},
		messages:{
			nomeUsuario:{
				required: "Este campo é obrigatório.",
				maxlength: "No máximo 50 caracteres.",
				minlength: "Numero  minimo de 4 letras.",
				minWords: "Numero minimo de duas palavras."
			},
			email:{
				required: "O campo e-mail é obrigatório.",
				email: "E-mail inválido.",
				remote: "Email já existente"
			},
			senha:{
				required:"O campo senha é obrigatório.",
				minlength: "No mínimo 6 caracteres."
						
			},
			cpf:{
				required: "O campo cpf é obrigatório.",
			}
		}
	
		
	});
	
	//PRE FORM RESTAURANTE
	$("#formPreRestaurante").validate({
		rules:{
			cnpj:{
				remote:{
					url: '/verificationCNPJ',
					type: "post",
					data:{
						cpf: function()
	                      {
	                          return $('#cnpj').val();
	                      }
					}
				}
			},
			submitHandler: function(form){
				form.submit()
			}
			
		},
		messages:{
			cnpj:{
				remote: "CNPJ informado já está vinculado a uma conta!"
			}
		}
				
	});
	//FORM USUARIO
	$("#formRestaurante").validate({
		rules:{
			nomeRestaurante: {
				required: true,
				maxlength: 100,
				minlength: 5,
				minWords: 2,
			},
			telefone:{
				required: true,
				maxlength: 20,
			},
			cep:{
				required: true,
			},
			categoria:{
				required: true,
			},
			bairro:{
				required: true,
			},
			cidade:{
				required: true,
			},
			rua:{
				required: true,
			},
			numero:{
				required: true,
			},
			uf:{
				required: true,
			},
			email:{
				required: true,
				email:true,
				remote:{
					url: '/verificationEmailRest',
					type: "post",
					data:{
						email: function()
	                      {
	                          return $('#email').val();
	                      }
	
					}
				}
			},
			senha:{
				required: true,
				minlength: 6
	
			},
			submitHandler: function(form){
				form.submit()
			}
		
			
		},
		messages:{
			nomeRestaurante:{
				required: "Este campo é obrigatório.",
				maxlength: "No máximo 100 caracteres.",
				minlength: "Numero  minimo de 5 letras.",
				minWords: "Numero minimo de duas palavras."
			},
			telefone:{
				required: "O campo telefone é obrigatório.",
			},
			cep:{
				required: "O campo cep é obrigatório.",
			},
			categoria:{
				required: "O campo categoria é obrigatório.",
			},
			bairro:{
				required: "O campo bairro é obrigatório.",
			},
			cidade:{
				required: "O campo cidade é obrigatório.",
			},
			rua:{			
				required: "O campo cpf é obrigatório.",
			},
			numero:{			
				required: "Nº obrigatório.",
			},
			uf:{																																																																																																																																																											
				required: "obrigatório.",
			},		
			email:{
				required: "O campo e-mail é obrigatório.",
				email: "E-mail inválido.",
				remote: "Email já existente"
			},
			senha:{
				required:"O campo senha é obrigatório.",
				minlength: "No mínimo 6 caracteres."
						
			}
		}
	
		
	});
	
	//FORM CONF USER
	$("#formuserConf").validate({
		rules:{
			nomeUsuario: {
				required: true,
				maxlength: 100,
				minlength: 5,
				minWords: 2,
			},
			submitHandler: function(form){
				form.submit()
			}
		
			
		},
		messages:{
			nomeUsuario:{
				required: "Este campo é obrigatório.",
				maxlength: "No máximo 100 caracteres.",
				minlength: "Numero  minimo de 5 letras.",
				minWords: "Numero minimo de duas palavras."
			}
		}
	
		
	});
	
	//FORM OFERTA RESTAURANTE
	$("#restOferta").validate({
		rules:{
			descricao: {
				required: true
			},
			valor: {
				required: true
			},
			restricao: {
				required: true
			},
			sobre: {
				required: true
			},
			submitHandler: function(form){
				form.submit()
			}
	
		},
		messages:{
			descricao: {
				required: "Este campo é obrigatório.",
			},
			valor: {
				required: "Este campo é obrigatório.",
			},
			restricao: {
				required: "Este campo é obrigatório.",
			},
			sobre: {
				required: "Este campo é obrigatório.",
			}
		}
	
		
	});
	
	
	//FORM OFERTA RESTAURANTE
	$("#confRest").validate({
		rules:{
			nomeRestaurante: {
				required: true
			},
			telefone: {
				required: true
			},
			cnpj: {
				required: true
			},
			email: {
				required: true
			},
			submitHandler: function(form){
				form.submit()
			}
	
		},
		messages:{
			nomeRestaurante: {
				required: "Este campo é obrigatório.",
			},
			telefone: {
				required: "Este campo é obrigatório.",
			},
			cnpj:{
				required: "Este campo é obrigatório.",
			},
			email: {
				required: "Este campo é obrigatório.",
			}
		}
	
		
	});
	
	
	//FORM Redefinir Senha
	$("#redefinir").validate({
		rules:{
			password: {
				required: true,
				minlength: 6
			},
			confSenha: {
				required: true,
				equalTo: "#password"
			},
			submitHandler: function(form){
				form.submit()
			}
	
		},
		messages:{
			password: {
				required: "Este campo é obrigatório.",
				minlength: "A senha tem que ter no minimo 6 caracteres!"
			},
			confSenha: {
				required: "Este campo é obrigatório.",
				equalTo: "O campo confirmar senha tem quer identico ao senha!"
			}
		}
	
		
	});
	
	//FORM RECUPERAR SENHA
	$("#recupera").validate({
		rules:{
			email: {
				required: true,
				email: true
			},
			submitHandler: function(form){
				form.submit()
			}
	
		},
		messages:{
			email: {
				required: "Informe um email.",
				email: "Por favor informe um email valido!"
			}
		}
	
		
	});
	
	//CAD CATEGORIA
	$("#categoria").validate({
		rules:{
			nomeCategoria: {
				required: true,
				maxlength: 30
			},
			submitHandler: function(form){
				form.submit()
			}
	
		},
		messages:{
			nomeCategoria: {
				required: "Informe o nome da Categoria a ser salva.",
				minlength: "O nome da Categoria so pode ter no maximo 30 caractes!"
			}
		}
	
		
	});
	
})