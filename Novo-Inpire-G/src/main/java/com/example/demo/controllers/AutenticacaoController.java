package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;
import com.example.demo.services.AuteticationService;

@Controller
@RequestMapping("/usuario")
public class AutenticacaoController {
	
	
	@Autowired
	private AuteticationService loginService;
	
	@GetMapping("/novo/{hash}")
	public String authUsuario(@PathVariable String hash, Usuario usuario,RedirectAttributes ra) {
		usuario.setHashId(hash);
		
		loginService.autenciaUsuario(usuario);
		
		ra.addFlashAttribute("mensagemErro", "5");
		return "email/success";
	}
	
	@GetMapping("/Restaurante/{hash}")
	public String authRestaurante(@PathVariable String hash, Restaurante restaurante,RedirectAttributes ra) {
		restaurante.setHashId(hash);
		
		loginService.autenciaRestaurnte(restaurante);
		
		ra.addFlashAttribute("mensagemErro", "5");
		return "email/success";
	}
	
}
