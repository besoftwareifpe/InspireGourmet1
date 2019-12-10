package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Usuario;
import com.example.demo.services.AuteticationService;

@Controller
@RequestMapping("/usuario")
public class AutenticacaoController {
	
	
	@Autowired
	private AuteticationService loginService;
	
	@GetMapping("/novo/{hash}")
	public String auth(@PathVariable String hash, Usuario usuario) {
		usuario.setHashId(hash);
		
		loginService.autenciaUsuario(usuario);
		return "redirect:/login";
	}
	
	
	public ModelAndView logar(@Valid Usuario usuario) {
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}

}