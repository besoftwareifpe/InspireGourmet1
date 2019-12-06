package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Restaurante;

public interface RestauranteDAO extends JpaRepository<Restaurante, Integer> {

}
