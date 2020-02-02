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
import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;
import com.example.demo.services.AdminService;
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
	private AdminService serviceAdmin;
	
	
	@GetMapping("/login")
	public String showLogin(Usuario usuario,Model model,HttpSession session) {
		model.addAttribute("login", usuario);
		
		return "login";
	}
	
	@PostMapping("realizarLogin")
	public String login(Usuario usuario, RedirectAttributes ra, HttpSession session	) throws NoSuchAlgorithmException {
		String senhaAnterior = usuario.getSenha();
		//criptografando a senha
		usuario.setSenha(Functions.getSHA256(usuario.getSenha()));
		
		Usuario usuarioConsultado = serviceUser.buscarConta(usuario.getEmail(), usuario.getSenha());
		
		if(usuarioConsultado == null) {
			Restaurante restauranteConsultado = serviceRest.buscarConta(usuario.getEmail(), usuario.getSenha());
			
			if(restauranteConsultado == null){
				Admin adminChecado = serviceAdmin.verificaAdmin(usuario.getEmail(), senhaAnterior);
				
				if(adminChecado == null) {
					ra.addFlashAttribute("mensagemErro", "3");
					return "redirect:/login";
				}else {
					if(adminChecado.getPrioridade() != 3) {
						ra.addFlashAttribute("mensagemErro", "6");
						return "redirect:/login";
					}else{
						session.setAttribute("adminLogado", adminChecado);
						return "redirect:/homeAdm";
					}
				}
			}else {
				
				if(restauranteConsultado.getAtivo() != 1) {
					ra.addFlashAttribute("mensagemErro", "2");
					return "redirect:/login";
				}else {
					if(restauranteConsultado.getPrioridade() != 2) {
						ra.addFlashAttribute("mensagemErro", "6");
						return "redirect:/login";
					}else {
						if (restauranteConsultado.getSaltera() != 1) {
							session.setAttribute("restauranteLogado", restauranteConsultado);

							return "redirect:/restaurante/homeRest/"+restauranteConsultado.getHashId();
						}else {
							session.setAttribute("restauranteLogado", restauranteConsultado);
							
							return "redirect:/redefini/"+restauranteConsultado.getHashId()	;
						}
						
					}
				}
			}
		}else {
			
			if(usuarioConsultado.getAtivo() != 1) {
				ra.addFlashAttribute("mensagemErro", "2");
				return "redirect:/login";
			}else {
				if(usuarioConsultado.getPrioridade() != 1) {
					ra.addFlashAttribute("mensagemErro", "6");
					return "redirect:/login";
				}else {
					if (usuarioConsultado.getSaltera() != 1) {
						session.setAttribute("usuarioLogado", usuarioConsultado);
						return "redirect:/home";
					}else {
						session.setAttribute("usuarioLogado", usuarioConsultado);
						
						return "redirect:/redefinir/"+usuarioConsultado.getHashId()	;
					}
					
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
