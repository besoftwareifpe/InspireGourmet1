package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Categoria;
import com.example.demo.models.Endereco;
import com.example.demo.models.Restaurante;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.RestauranteService;

@Controller
public class RestauranteController {

	@Autowired
	private RestauranteService serviceRestaurante;
	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@GetMapping("/restaurante")
	public String showRes() {
		return "restaurante";
	}
	
	@PostMapping("/restauranteCad")
	public String showLRestaurante(Restaurante restaurante ,Model model,@RequestParam(name = "cnpj")String cnpj,@RequestParam(name = "noCNPJ") String noCNPJ) {
		model.addAttribute("restaurante", restaurante);
		
		if(noCNPJ != "0") {
			model.addAttribute("cnpj", cnpj);
		}
		
		System.out.println(noCNPJ);
		
		List<Categoria> categorias = serviceCategoria.listAll();
		model.addAttribute("categorias", categorias);
		
		return "restauranteCad";
	}
	
	@PostMapping("/savarRestaurante")
	public String saveRestaurante(Restaurante restaurante,@RequestParam(name = "cep") String cep,@RequestParam(name = "bairro") String bairro,
			@RequestParam(name = "cidade") String cidade,@RequestParam(name = "numero") String numero,@RequestParam(name = "uf") String uf) {
		
		System.out.println(cep+bairro+cidade+uf);
		
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setNumero(numero);
		endereco.setUf(uf);
		
		restaurante.setEndereco(endereco);
		
		serviceRestaurante.save(restaurante);
		return "redirect:/restaurante";
	}
}
