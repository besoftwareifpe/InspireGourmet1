package com.example.demo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Restaurante;

public interface RestauranteDAO extends JpaRepository<Restaurante, Integer> {

	//ISSO FUNCIONA
	@Query(value =  "select * from restaurante where email = ?1 AND senha = ?2", nativeQuery = true)
	Restaurante buscalogin(String email, String senha);

	@Query(value = "select * from restaurante where nome_restaurante like %?1%", nativeQuery = true)
	List<Restaurante> buscarRestaurantes(String pesquisa);
	
	@Query(value = "select * from restaurante where id_categoria = ?1", nativeQuery = true)
	List<Restaurante> buscarRestauranteCate(Integer idCat);

	Restaurante findByHashId(String hashId);
	Restaurante findByCnpj(String cnpj);
	Restaurante findByEmail(String email);
	
	
}
