package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.email.Mailer;
import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;
import com.example.demo.util.Functions;

@Controller
public class CadastroUsuarioController {
	
	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private UsuarioDAO userDao;
	
	@Autowired
	private Mailer mail;
	
	@RequestMapping("/continuar")
	public String showCadUser(Model model, Usuario usuario,@RequestParam(name = "cpf") String cpf) {
		model.addAttribute("usuario", usuario);		
		model.addAttribute("cpf", cpf);
		
		return "page5";
	}
	
	@PostMapping("/salvarUsuario" )
	public String cadUsu(Usuario usuario , Model model,RedirectAttributes ra) {
	
		
		//criptografando a senha
		usuario.setSenha(Functions.getSHA256(usuario.getSenha()));
		
		usuario.setAtivo(0);
		
		Date dataCriacao = new Date();
		usuario.setDataDeCriacao(dataCriacao);
		
		
		serviceUser.save(usuario);
		mail.enviar(usuario);
		
		ra.addFlashAttribute("mensagemErro", "1");
		
		return "redirect:/login";

		
	}
	
	@GetMapping("/verificationCPF")
	public String valideCpf(Model model, @RequestParam(name = "cpf") String cpf) {
		
		System.out.println(cpf);
		
		Usuario cpfChecado = userDao.findByCpf(cpf);
		
		if(cpfChecado.isPresent()) {
			model.addAttribute("mensagemErro", "CPF Já esta cadastrado em nosso banco");
			return "redirect:/index";
		}else {
			return "redrect:/";
		}
		
	}
}
