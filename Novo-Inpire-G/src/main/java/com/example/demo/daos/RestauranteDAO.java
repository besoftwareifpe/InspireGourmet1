package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Restaurante;

public interface RestauranteDAO extends JpaRepository<Restaurante, Integer> {

	//ISSO FUNCIONA
	@Query(value =  "select * from restaurante where email = ?1 AND senha = ?2", nativeQuery = true)
	Restaurante buscalogin(String email, String senha);

	Restaurante findByHashId(String hashId);
	Restaurante findByCnpj(String cnpj);
	Restaurante findByEmail(String email);
	
	
}
