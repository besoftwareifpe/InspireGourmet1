package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BtnController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/assinatura")
	public String show1() {
		return "assinatura";
	}
	
	

	@GetMapping("/restaurantes")
	public String show3() {
		return "restaurantes";
	}
	
	@GetMapping("/detalhe")
	public String show4() {
		return "detalhes";
	}
	
	
	
	@GetMapping("/detalhes")
	public String showDetalhes() {
		return "usuario/detalhesRestaurante";
	}
	
	@GetMapping("/page5")
	public String show5() {
		return "cadUser";
	}
	
	@GetMapping("/page6")
	public String show6() {
		return "page6";
	}
	
	@GetMapping("/recuperarSenha")
	public String show7() {
		return "recuperarSenha";
	}
	


	
}
