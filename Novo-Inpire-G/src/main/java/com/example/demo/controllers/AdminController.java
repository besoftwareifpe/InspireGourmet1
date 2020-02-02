package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Categoria;
import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.RestauranteService;
import com.example.demo.services.UsuarioService;

@Controller
public class AdminController {

	@Autowired
	private UsuarioService serviceUser;
	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@Autowired
	private RestauranteService serviceRestaurante;
	
	@GetMapping("/homeAdm")
	public String exibirPageOptCat(Categoria categoria ,Model model) {
		model.addAttribute("categoria", categoria);
		
		
		List<Categoria> categorias = serviceCategoria.listAll();
		model.addAttribute("categorias", categorias);
		
		List<Usuario> usuario = serviceUser.listAll();
		model.addAttribute("usuarios", usuario);
		
		List<Restaurante> lista = serviceRestaurante.listAll();
		model.addAttribute("restaurantes", lista);
		
		return "/admin/homeAdm";
	}
	
	
}
