package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Oferta;
import com.example.demo.models.Restaurante;
import com.example.demo.services.OfertaService;
import com.example.demo.services.RestauranteService;

@Controller
public class BtnController {
	
	@Autowired
	private RestauranteService serviceRestaurante;
	
	@Autowired
	private OfertaService serviceOferta;

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/assinatura")
	public String show1() {
		return "assinatura";
	}
	
	

	@GetMapping("/restaurantes")
	public String show3(Model model) {
		
		List<Restaurante> restaurantes = serviceRestaurante.listAll();
		model.addAttribute("restaurantes",restaurantes);
		
 		return "restaurantes";
	}
	
	@GetMapping("/detalhe")
	public String show4() {
		return "detalhes";
	}
	
	
	
	@GetMapping("/detalhes/{id}")
	public String showDetalhes(@PathVariable("id")Integer idRest,Model model) {
		
		Restaurante  restaurante = serviceRestaurante.get(idRest);
		model.addAttribute("restaurante", restaurante);
		
		Oferta oferta = serviceOferta.get(idRest);
		model.addAttribute("oferta", oferta);
		
		return "usuario/detalhesRestaurante";
	}
	
	@GetMapping("/page5")
	public String show5() {
		return "cadUser";
	}
	
	@GetMapping("/page6")
	public String show6() {
		return "continuar";
	}
	
	@GetMapping("/recuperarSenha")
	public String show7() {
		return "recuperarSenha";
	}
	


	
}
