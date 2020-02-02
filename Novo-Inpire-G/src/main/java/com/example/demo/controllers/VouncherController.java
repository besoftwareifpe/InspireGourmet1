package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Oferta;
import com.example.demo.models.Usuario;
import com.example.demo.models.Voucher;
import com.example.demo.services.VoucherService;

@Controller
public class VouncherController {

	
	@Autowired
	private VoucherService voucherService;
	
	@GetMapping("/gerar/voucher/{idUser}/{idOferta}")
	public String salvarVoucher(@PathVariable("idUser")Usuario idUser, @PathVariable("idOferta")Oferta idOferta,RedirectAttributes ra) {
		
		Voucher voucher = new Voucher();
		voucher.setIdOferta(idOferta);
		voucher.setIdUsuario(idUser);
		
		voucherService.save(voucher);
		
		ra.addFlashAttribute("mensagemErro", "1");
		return "redirect:/home";
	}
	
	
	
}
