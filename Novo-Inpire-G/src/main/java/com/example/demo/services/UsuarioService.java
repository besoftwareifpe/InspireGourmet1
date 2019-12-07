package com.example.demo.services;

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
}
