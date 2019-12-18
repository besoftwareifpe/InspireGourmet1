package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.RestauranteDAO;
import com.example.demo.models.Restaurante;

@Service
@Transactional
public class RestauranteService {

	@Autowired
	private RestauranteDAO repository;
	
	public List<Restaurante> listAll(){
		return repository.findAll();
	}
	
	public void save(Restaurante restaurante) {
		
		//set a hash of user
		String hashId = UUID.randomUUID().toString();
		restaurante.setHashId(hashId);
		
		repository.save(restaurante);
	}
	
	public Restaurante get(Integer idRestaurante) {
		return repository.findById(idRestaurante).get();
	}
	
	public void delete(Integer idRestaurante) {
		repository.deleteById(idRestaurante);
	}
	
	
	public Restaurante buscarConta(String email, String senha) {
		return repository.buscalogin(email, senha);
	}
	
	public Restaurante verificaCPF(String cnpj) {
		return repository.findByCnpj(cnpj);
	}
	
	public Restaurante verificaEmailRest(String email) {
		return repository.findByEmail(email);
	}
}

