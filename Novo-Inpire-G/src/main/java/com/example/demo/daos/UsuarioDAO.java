package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Usuario;


public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

	//ISSO FUNCIONA
	@Query(value =  "select * from usuario where email = ?1 AND senha = ?2", nativeQuery = true)
	Usuario buscalogin(String email, String senha);
	
	Usuario findByHashId(String hashId);
	Usuario findByEmail(String email);
	Usuario findByCpf(String cpf);


}
