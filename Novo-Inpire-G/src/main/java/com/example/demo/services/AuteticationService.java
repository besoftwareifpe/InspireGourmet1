package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.models.Usuario;

@Service
public class AuteticationService {

	@Autowired
	private  UsuarioDAO repository;
		
	
	
	public void autenciaUsuario(Usuario usuario) {
		Optional<Usuario> usuarioGetHash = repository.findByHashId(usuario.getHashId());
		     
		if(usuarioGetHash.isPresent()) {
			
			usuario.setAtivo(1);
			
		}			
		
		
	}
	
	
}
