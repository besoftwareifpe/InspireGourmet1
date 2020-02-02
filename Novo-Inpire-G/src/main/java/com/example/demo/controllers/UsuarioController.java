package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

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
import com.example.demo.models.Categoria;
import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.RestauranteService;
import com.example.demo.services.UsuarioService;
import com.example.demo.util.Functions;
import com.example.demo.util.PasswordGenerate;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private RestauranteService serviceRestaurante;
	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@Autowired
	private MailerPassword mail;
	
	@Autowired
	private UsuarioDAO userDao;
	
	@GetMapping("/home")
	public String showHome(Model model) {
		Restaurante restaurante = new Restaurante();
		model.addAttribute("restaurante", restaurante);
		
		List<Restaurante> lista = serviceRestaurante.listAll();
		model.addAttribute("restaurantes", lista);
		
		List<Categoria> listaCat = serviceCategoria.listAll();
		model.addAttribute("comidas", listaCat);
		
		return "usuario/home";
	}
	
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
				return "redirect:/recuperarSenha";
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
				usuarioChecado.setSaltera(1);
				
				serviceUser.save(usuarioChecado);
				ra.addFlashAttribute("mensagemErro", "4");
				return "redirect:/login";
			}
			
		}else {
			ra.addFlashAttribute("mensagemErro", "1");
			return "redirect:/recuperarSenha";
		}
	
	}
	
	@GetMapping("/deleteUser/{idRest}")
	public String delete(@PathVariable("idRest")Integer idRest,Model model) {
		
		serviceUser.delete(idRest);
		
		return "redirect:/homeAdm";
	}
	
	@GetMapping("/redefinir/{hash}")
	public String showRedefinir(@PathVariable("hash")String hash,Model model) {
		
		Usuario user = serviceUser.buscarPeloHash(hash);
		model.addAttribute("redefinir", user);
		
		return "/usuario/redefinir";
	}
	
	@PostMapping("/redefinirSenha")
	public String redefinir(@RequestParam(name = "hashId")String hash,@RequestParam(name = "confSenha")String senha,RedirectAttributes ra) {

		serviceUser.redefinirSenha(hash,senha);
     	ra.addFlashAttribute("mensagemErro", "5");
		return "redirect:/home";
	}
	
	@GetMapping("/senha/{hash}")
	public String showRede(@PathVariable("hash")String hash,Model model) {
		
		Usuario user = serviceUser.buscarPeloHash(hash);
		model.addAttribute("redefinir", user);
		
		return "/usuario/senha";
	}
	
}
