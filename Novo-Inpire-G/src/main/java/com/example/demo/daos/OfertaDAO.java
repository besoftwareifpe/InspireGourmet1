package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Oferta;

public interface OfertaDAO extends JpaRepository<Oferta, Integer> {

	//ISSO FUNCIONA
	@Query(value =  "select * from oferta where id_restaurante = ?1", nativeQuery = true)
	Oferta buscaOferta(Integer idRestaurante);

	@Query(value =  "delete from oferta where id_restaurante = ?1", nativeQuery = true)
	void deletePorIdRest(Integer idRest);
}
