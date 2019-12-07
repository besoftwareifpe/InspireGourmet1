package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.models.Usuario;
import com.example.demo.util.Functions;

@Controller
public class CadastroUsuarioController {

	@Autowired
	private UsuarioDAO u1;
	
	@PostMapping("/continuar")
	public String showCadUser(Model model, Usuario usuario,@RequestParam(name = "cpf") String cpf) {
		model.addAttribute("usuario", usuario);
		model.addAttribute("cpf", cpf);
		
		return "page5";
	}
	
	@PostMapping("/salvarUsuario" )
	public String cadUsu(Usuario usuario) {
		//criptografando a senha
		usuario.setSenha(Functions.getSHA256(usuario.getSenha()));
		
		usuario.setAtivo(0);
		
		Date dataCriacao = new Date();
		usuario.setDataDeCriacao(dataCriacao);
		
		u1.save(usuario);
		return "redirect:/login";
	}
}
