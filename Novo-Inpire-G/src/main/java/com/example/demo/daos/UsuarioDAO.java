package com.example.demo.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Usuario;


public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

	//ISSO FUNCIONA
	@Query(value =  "select * from usuario where email = ?1 AND senha = ?2", nativeQuery = true)
	Usuario buscalogin(String email, String senha);

	Optional<Usuario> findByHashId(String hashId);
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByCpf(String cpf);
}
