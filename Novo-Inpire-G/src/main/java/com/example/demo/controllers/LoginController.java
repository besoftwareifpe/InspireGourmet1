package com.example.demo.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;
import com.example.demo.services.RestauranteService;
import com.example.demo.services.UsuarioService;
import com.example.demo.util.Functions;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private RestauranteService serviceRest;
	
	
	@GetMapping("/login")
	public String showLogin(Usuario usuario,Model model,HttpSession session) {
		model.addAttribute("login", usuario);
		
		return "page2";
	}
	
	@PostMapping("realizarLogin")
	public String login(Usuario usuario, Model model, HttpSession session) throws NoSuchAlgorithmException {
		//criptografando a senha
		usuario.setSenha(Functions.getSHA256(usuario.getSenha()));
		
		Usuario usuarioConsultado = serviceUser.buscarConta(usuario.getEmail(), usuario.getSenha());
		
		if(usuarioConsultado == null) {
			model.addAttribute("mensagemErro", "Usuario não encontrado");
			return "redirect:/login";
		}else {
			session.setAttribute("usuarioLogado", usuarioConsultado);
			return "redirect:/home";
		}
		
	}
	
	
	//Login Restaurante
	@GetMapping("/restaurante")
	public String showRestaurante(Restaurante restaurante,Model model,HttpSession session) {
		model.addAttribute("loginRestaurante", restaurante);
		
		return "restaurante";
	}
	
	@PostMapping("/realizarLoginRest")
	public String loginRestaurante(Restaurante restaurante, Model model, HttpSession session) throws NoSuchAlgorithmException{
		//criptografando a senha
		restaurante.setSenha(Functions.getSHA256(restaurante.getSenha()));
		
		Restaurante restauranteConsultado = serviceRest.buscarConta(restaurante.getEmail(), restaurante.getSenha());
		
		if(restauranteConsultado == null) {
			model.addAttribute("mensagemErro", "Restaurante não encontrado");
			return "redirect:/restaurante";
		}else {
			session.setAttribute("restauranteLogado", restauranteConsultado);
			return "redirect:/homeRest";
		}
		
	}
}
