package com.example.demo.services;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.models.Usuario;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioDAO repositoryDao;
	
	
	public Usuario buscarConta(String email, String senha) {
		
		return repositoryDao.buscalogin(email, senha);
		
	}
	
	public void save(Usuario usuario) {
		
		//set a hash of user
		String hashId = UUID.randomUUID().toString();
		usuario.setHashId(hashId);
		
		repositoryDao.save(usuario);
	}
}
