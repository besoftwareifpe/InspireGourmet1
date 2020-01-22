package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.email.Mailer;
import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;
import com.example.demo.util.Functions;

@Controller
public class CadastroUsuarioController {
	
	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private Mailer mail;
	
	@RequestMapping("/continuar")
	public String showCadUser(@RequestParam(name = "cpf")String cpf,Model model, Usuario usuario) {
		model.addAttribute("usuario", usuario);		
		model.addAttribute("cpf", cpf);
		
		return "cadastro";
	}
	
	@PostMapping("/salvarUsuario" )
	public String cadUsu(Usuario usuario , Model model,RedirectAttributes ra) {

		//criptografando a senha
		usuario.setSenha(Functions.getSHA256(usuario.getSenha()));
		
		usuario.setAtivo(0);
		
		Date dataCriacao = new Date();
		usuario.setDataDeCriacao(dataCriacao);
		
		usuario.setPrioridade(1);
		usuario.setSaltera(0);
		
		serviceUser.save(usuario);
		mail.enviar(usuario);
		
		ra.addFlashAttribute("mensagemErro", "1");
		
		return "redirect:/login";		
		
	}
	
	@PostMapping("/verificationCPF")
	@ResponseBody
	public String valideCpf(@RequestParam(name = "cpf") String cpf) {

		Boolean cpfChecado = serviceUser.verificaCPF(cpf) == null;
		
		return cpfChecado.toString();
		
	}
	
	@PostMapping("/verificationEmail")
	@ResponseBody
	public String valideEmail(@RequestParam(name = "email") String email) {
		
		Boolean emailChecado = serviceUser.verificaEmail(email) == null; 
		
		return emailChecado.toString();
		
		
	}
}
