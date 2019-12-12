package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.email.MailerRestaurante;
import com.example.demo.models.Categoria;
import com.example.demo.models.Endereco;
import com.example.demo.models.Restaurante;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.RestauranteService;
import com.example.demo.util.Functions;

@Controller
public class RestauranteController {

	@Autowired
	private RestauranteService serviceRestaurante;
	
	@Autowired
	private CategoriaService serviceCategoria;
	
	@Autowired
	private MailerRestaurante mail;
	
	
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
			@RequestParam(name = "cidade") String cidade,@RequestParam(name = "numero") String numero,@RequestParam(name = "uf") String uf,RedirectAttributes ra) {
		
		//criptografando a senha
		restaurante.setSenha(Functions.getSHA256(restaurante.getSenha()));
		
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setNumero(numero);
		endereco.setUf(uf);
		
		restaurante.setEndereco(endereco);
		restaurante.setAtivo(0);
		
		serviceRestaurante.save(restaurante);
		mail.enviar(restaurante);
		
		ra.addFlashAttribute("mensagemErro", "1");
		return "redirect:/restaurante";
		
		
	}
}
