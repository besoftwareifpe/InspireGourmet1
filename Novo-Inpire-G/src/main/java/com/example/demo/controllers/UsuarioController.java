package com.example.demo.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.email.MailerPassword;
import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;
import com.example.demo.util.Functions;
import com.example.demo.util.PasswordGenerate;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private MailerPassword mail;
	
	@Autowired
	private UsuarioDAO userDao;
	
	@GetMapping("/edite/{idUser}")
	public String edite(@PathVariable("idUser") Integer idUser, Model model) {
		
		Usuario usuario = serviceUser.get(idUser);
		
		model.addAttribute("usuario",usuario);
		
		return "/usuario/configuracoes";
	}
	
	@PostMapping("/updateUsuario" )
	public String cadUsu(Usuario usuario,HttpSession session) {
	
		Usuario usuario1 = userDao.findByHashId(usuario.getHashId());
			
		usuario.setSenha(usuario1.getSenha());
		usuario.setAtivo(usuario1.getAtivo());
		usuario.setDataDeCriacao(usuario1.getDataDeCriacao());
		usuario.setUltimoUpdate(new Date());
		
		serviceUser.save(usuario);
		
		
		session.setAttribute("usuarioLogado", usuario);
		return "redirect:/home";
		
	}
	
	
	@PostMapping("/recuperarSenha")
	public String recupera(@RequestParam(name = "email") String email,RedirectAttributes ra) {
		
		Usuario usuarioChecado = userDao.findByEmail(email);
		
		if(usuarioChecado != null) {
			
			if(usuarioChecado.getAtivo() != 1) {
				ra.addFlashAttribute("mensagemErro", "2");
				return "redirect:/page7";
			}else {
				//variaveis para geração de senha
				String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			    String ALPHA = "abcdefghijklmnopqrstuvwxyz";
			    String NUMERIC = "0123456789";
				Integer len = 10;
	
				String newSenha = PasswordGenerate.generatePassword(len, ALPHA_CAPS + ALPHA + NUMERIC);
				
				usuarioChecado.setSenha(newSenha);
				
				mail.enviar(usuarioChecado);
				
				System.out.println(newSenha);
				newSenha = Functions.getSHA256(newSenha);
				usuarioChecado.setSenha(newSenha);
				
				serviceUser.save(usuarioChecado);
				ra.addFlashAttribute("mensagemErro", "4");
				return "redirect:/login";
			}
			
		}else {
			ra.addFlashAttribute("mensagemErro", "1");
			return "redirect:/page7";
		}
	
	}
	
	
}
