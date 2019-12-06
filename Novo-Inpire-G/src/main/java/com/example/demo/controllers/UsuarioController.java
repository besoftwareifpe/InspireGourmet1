package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.models.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO u1;
	
	
	
	@GetMapping ("/ExibirUsuario")
	public String LisUsu(Model model) {
		model.addAttribute("lista", u1.findAll(Sort.by("nome")));
		return "form-list";
	}
	
	@GetMapping ("/EditarUsuario")
	public String EdiPe(Integer id_usuario , Model model) {
		model.addAttribute("usuario", u1.findById(id_usuario));
		
		return "form-usuario";
	}
	
	@GetMapping("/ExcluirUsuario")
	public String DelUsu(Integer id_usuario) {
		u1.deleteById(id_usuario);
		
		return "redirect:/ExibirUsuario";
	}
	
	@GetMapping("/imagem/{id}")
	public byte[] LisImg(@PathVariable("id") Integer id) {
		Usuario usuario = u1.getOne(id);
		return usuario.getImg();
	}
}
