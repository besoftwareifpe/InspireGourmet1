package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Oferta;
import com.example.demo.services.OfertaService;

@Controller
public class OfertaController {
	
	@Autowired
	private OfertaService serviceOferta;
	
	
	@PostMapping("/restaurantee")
	public String saveOferta(Oferta oferta,Model model) {
		
		serviceOferta.save(oferta);
		
		return "/restaurante/homeRest";
	}
}
