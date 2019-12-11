package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.models.Usuario;

@Service
public class AuteticationService {

	@Autowired
	private  UsuarioDAO repository;
	
	@Autowired
	private  UsuarioService serviceUser;
		
	
	
	public void autenciaUsuario(Usuario usuario) {
		Usuario usuarioGetHash = repository.findByHashId(usuario.getHashId());
		
		if(!usuarioGetHash.isPresent()) {	
			
			usuarioGetHash.setAtivo(1);
						
			serviceUser.save(usuarioGetHash);
			
		}			
		
		
	}
	
	
}
