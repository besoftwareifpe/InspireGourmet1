package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.models.Restaurante;


@Controller
public class DocumentController {
	
	@Autowired
	private Restaurante restaurante;
	


}
