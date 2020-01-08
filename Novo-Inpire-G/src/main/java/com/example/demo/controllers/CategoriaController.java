package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.daos.CategoriaDAO;
import com.example.demo.models.Categoria;
import com.example.demo.services.CategoriaService;



@Controller
public class CategoriaController {

	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@Autowired
	private CategoriaDAO categoriaDao;


	@GetMapping("/homeAdm")
	public String exibirPageOptCat(Categoria categoria ,Model model) {
		model.addAttribute("categoria", categoria);
		
		
		List<Categoria> categorias = serviceCategoria.listAll();
		model.addAttribute("categorias", categorias);
		
		return "/admin/homeAdm";
	}
	
	
	@PostMapping("/salvarCategoria")
	public String salvarCategoria(Categoria categoria, RedirectAttributes ra) {
		
		Categoria nomeChecado =  categoriaDao.findByNomeCategoria(categoria.getNomeCategoria());
		
		if(nomeChecado != null) {
			ra.addFlashAttribute("mensagem", "4");
			
			return "redirect:/homeAdm";
			
		}else {
			serviceCategoria.save(categoria);
			
			ra.addFlashAttribute("mensagem","1");
			
			return "redirect:/homeAdm";
		}
		
	}
	
	@GetMapping("/editeCat/{idCat}")
	public String edite(@PathVariable("idCat")Integer idCat,Model model) {
		
		Categoria categoria = serviceCategoria.get(idCat);
		
		model.addAttribute("categoria", categoria);
		List<Categoria> categorias = serviceCategoria.listAll();
		model.addAttribute("categorias", categorias);
		
		
		return "/admin/homeAdm";
	}


	@GetMapping("/deleteCat/{idCat}")
	public String removerCat(@PathVariable("idCat") Integer idCat,RedirectAttributes ra) {
		
		serviceCategoria.delete(idCat);
		ra.addFlashAttribute("mensagem", "3");
		return "redirect:/homeAdm";

	}
}
