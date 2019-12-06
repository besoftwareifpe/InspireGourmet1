package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.CategoriaDAO;
import com.example.demo.models.Categoria;

@Service
@Transactional
public class CategoriaService {

	@Autowired
	private CategoriaDAO repository;
	
	
	public List<Categoria> listAll(){
		return repository.findAll();
	}
	
	
	public void delete(Integer idCategpria) {
		repository.deleteById(idCategpria);
	}
}
