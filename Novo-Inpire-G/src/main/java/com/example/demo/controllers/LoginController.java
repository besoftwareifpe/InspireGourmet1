package com.example.demo.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Admin;
import com.example.demo.models.Oferta;
import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;
import com.example.demo.services.AdminService;
import com.example.demo.services.OfertaService;
import com.example.demo.services.RestauranteService;
import com.example.demo.services.UsuarioService;
import com.example.demo.util.Functions;

@Controller
public class LoginController {

	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private RestauranteService serviceRest;
	
	@Autowired
	private OfertaService serviceOferta;
	
	@Autowired
	private AdminService serviceAdmin;
	
	
	@GetMapping("/login")
	public String showLogin(Usuario usuario,Model model,HttpSession session) {
		model.addAttribute("login", usuario);
		
		return "page2";
	}
	
	@PostMapping("realizarLogin")
	public String login(Usuario usuario, RedirectAttributes ra, HttpSession session) throws NoSuchAlgorithmException {
		//criptografando a senha
		usuario.setSenha(Functions.getSHA256(usuario.getSenha()));
		
		Usuario usuarioConsultado = serviceUser.buscarConta(usuario.getEmail(), usuario.getSenha());
		
		if(usuarioConsultado == null) {
			ra.addFlashAttribute("mensagemErro", "3");
			return "redirect:/login";
		}else {
			
			if(usuarioConsultado.getAtivo() != 1) {
				ra.addFlashAttribute("mensagemErro", "2");
				return "redirect:/login";
			}else {
				if(usuarioConsultado.getPrioridade() != 1) {
					ra.addFlashAttribute("mensagemErro", "6");
					return "redirect:/login";
				}else {
					session.setAttribute("usuarioLogado", usuarioConsultado);
					return "redirect:/home";
				}
			}			
		}
		
	}
	
	
	//Login Restaurante
	@GetMapping("/restaurante")
	public String showRestaurante(Restaurante restaurante,Model model,HttpSession session) {
		model.addAttribute("loginRestaurante", restaurante);
		
		return "restaurante";
	}
	
	@PostMapping("/restaurante")
	public String loginRestaurante(Restaurante restaurante, RedirectAttributes ra, HttpSession session, Model model) throws NoSuchAlgorithmException{
		
		String senhaAnterior = restaurante.getSenha();
		//criptografando a senha
		restaurante.setSenha(Functions.getSHA256(restaurante.getSenha()));
		
		Restaurante restauranteConsultado = serviceRest.buscarConta(restaurante.getEmail(), restaurante.getSenha());
		
		if(restauranteConsultado == null){
			Admin adminChecado = serviceAdmin.verificaAdmin(restaurante.getEmail(), senhaAnterior);
			
			if(adminChecado == null) {
				ra.addFlashAttribute("mensagemErro", "3");
				return "redirect:/restaurante";
			}else {
				if(adminChecado.getPrioridade() != 3) {
					ra.addFlashAttribute("mensagemErro", "6");
					return "redirect:/restaurante";
				}else{
					session.setAttribute("adminLogado", adminChecado);
					return "redirect:/homeAdm";
				}
			}
		}else {
			
			if(restauranteConsultado.getAtivo() != 1) {
				ra.addFlashAttribute("mensagemErro", "2");
				return "redirect:/restaurante";
			}else {
				if(restauranteConsultado.getPrioridade() != 2) {
					ra.addFlashAttribute("mensagemErro", "6");
					return "redirect:/restaurante";
				}else {
					session.setAttribute("restauranteLogado", restauranteConsultado);
					
					Oferta oferta = serviceOferta.get(restauranteConsultado.getIdRestaurante());
					model.addAttribute("oferta", oferta);
					return "/restaurante/homeRest";
				}
			}
		}
		
	}
	
	
	@GetMapping("/logout")
	public String logUot(HttpSession session) {
		session.invalidate();
		
		return "redirect:/index";
	}
}
