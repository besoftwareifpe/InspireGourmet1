package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.UsuarioDAO;
import com.example.demo.models.Usuario;
import com.example.demo.util.Functions;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	private UsuarioDAO repositoryDao;
	
	
	public List<Usuario> listAll(){
		return repositoryDao.findAll();
	}
	
	public Usuario buscarConta(String email, String senha) {
		
		return repositoryDao.buscalogin(email, senha);
		
	}
	
	public void save(Usuario usuario) {
		
		//set a hash of user
		String hashId = UUID.randomUUID().toString();
		usuario.setHashId(hashId);
		
		repositoryDao.save(usuario);
	}
	
	
	public Usuario get(Integer idUser) {
		
		return repositoryDao.findById(idUser).get();
	}
	
	public void delete(Integer idUser) {
		repositoryDao.deleteById(idUser);
	}
	
	public Usuario verificaCPF(String cpf) {
		return repositoryDao.findByCpf(cpf);
	}
	
	public Usuario verificaEmail(String email) {
		return repositoryDao.findByEmail(email);
	}

	public Usuario buscarPeloHash(String hash) {
		return repositoryDao.findByHashId(hash);
	}

	public void redefinirSenha(String hash, String senha) {
		
		Usuario usuario = repositoryDao.findByHashId(hash);
		
		String novaSenha = Functions.getSHA256(senha);
		usuario.setSenha(novaSenha);
		usuario.setSaltera(0);
		
		repositoryDao.save(usuario);
	}
	
}
