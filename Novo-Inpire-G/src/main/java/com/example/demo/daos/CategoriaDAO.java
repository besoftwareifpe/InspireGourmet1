package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{

	public Categoria findByNomeCategoria(String nomeCategoria);
}
