package com.example.demo.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.models.Restaurante;

@Component
public class MailerRestaurante {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Async
	public void enviar(Restaurante restaurante){
	   
		Context context =  new Context();
	    context.setVariable("ag", restaurante);	
	   
	   MimeMessage minMessage = mailSender.createMimeMessage();
	   
	   try {
		   String email = thymeleaf.process("email/EmailConfirmacaoRestaurante", context);
		MimeMessageHelper helper = new MimeMessageHelper(minMessage, true, "UTF-8");
		helper.setFrom("besoftware9@gmail.com");
		helper.setTo(restaurante.getEmail());
		helper.setSubject("besoftware- Confirme seu cadastro.");
		helper.setText(email, true);
		
	
		
		 mailSender.send(minMessage);
	} catch (MessagingException e) {
		 e.printStackTrace();
	}  
		
	  
	}
}
