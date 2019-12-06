package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Usuario;


public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

}
