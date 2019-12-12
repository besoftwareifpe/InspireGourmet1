package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.RestauranteDAO;
import com.example.demo.daos.UsuarioDAO;
import com.example.demo.models.Restaurante;
import com.example.demo.models.Usuario;

@Service
public class AuteticationService {

	@Autowired
	private  UsuarioDAO repository1;
	
	@Autowired
	private  RestauranteDAO repository2;
	
	@Autowired
	private  UsuarioService serviceUser;
	
	@Autowired
	private RestauranteService serviceRest;
		
	
	
	public void autenciaUsuario(Usuario usuario) {
		Usuario usuarioGetHash = repository1.findByHashId(usuario.getHashId());
		
		if(!usuarioGetHash.isPresent()) {	
			
			usuarioGetHash.setAtivo(1);
						
			serviceUser.save(usuarioGetHash);
			
		}			
		
		
	}
	
	public void autenciaRestaurnte(Restaurante restaurante) {
		Restaurante restauranteGetHash = repository2.findByHashId(restaurante.getHashId());
		
		if(!restauranteGetHash.isPresent()) {	
			
			restauranteGetHash.setAtivo(1);
						
			serviceRest.save(restauranteGetHash);
			
		}			
		
		
	}
	
}
