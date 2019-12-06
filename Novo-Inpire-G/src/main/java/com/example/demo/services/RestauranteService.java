package com.example.demo.services;

import java.util.List;

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
		repository.save(restaurante);
	}
	
	public Restaurante get(Integer idRestaurante) {
		return repository.findById(idRestaurante).get();
	}
	
	public void delete(Integer idRestaurante) {
		repository.deleteById(idRestaurante);
	}
}
