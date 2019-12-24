package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BtnController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/page1")
	public String show1() {
		return "page1";
	}
	
	

	@GetMapping("/page3")
	public String show3() {
		return "page3";
	}
	
	@GetMapping("/page4")
	public String show4() {
		return "page4";
	}
	
	@GetMapping("/home")
	public String showHome(Model model) {
				
		return "usuario/home";
	}
	
	@GetMapping("/detalhes")
	public String showDetalhes() {
		return "usuario/detalhesRestaurante";
	}
	
	@GetMapping("/homeRest")
	public String showHomeRest(Model model) {
				
		return "restaurante/homeRest";
	}
	
	@GetMapping("/page5")
	public String show5() {
		return "page5";
	}
	
	@GetMapping("/page6")
	public String show6() {
		return "page6";
	}
	
	@GetMapping("/page7")
	public String show7() {
		return "page7";
	}
	
	@GetMapping("/listUsuarios")
	public String showlistAllUsers() {
		return "/admin/listUsuarios";
	}
	
	@GetMapping("/listRestaurantes")
	public String showlistAllRest() {
		return "/admin/listRestaurantes";
	}

	
}
