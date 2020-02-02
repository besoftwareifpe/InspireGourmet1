package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.OfertaDAO;
import com.example.demo.models.Oferta;

@Service
public class OfertaService {

	@Autowired
	private OfertaDAO repository;
	
	public void save(Oferta oferta) {
		
		repository.save(oferta);
	}
	
	public Oferta get(Integer idRestaurante) {
		return repository.buscaOferta(idRestaurante);
	}
	
	public Oferta buscarPeloId(Integer idOferta) {
		return repository.findById(idOferta).get();
	}
	
	public void delete(Integer idRestaurante) {
		repository.deleteById(idRestaurante);
	}

	public void buscarPorIdRest(Integer idRest) {
		repository.deletePorIdRest(idRest);
	}

}
