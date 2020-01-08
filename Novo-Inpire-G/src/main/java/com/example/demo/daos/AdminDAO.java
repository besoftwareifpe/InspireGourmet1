package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Admin;

public interface AdminDAO extends JpaRepository<Admin, Integer> {

	@Query(value =  "select * from admin where email = ?1 AND senha = ?2", nativeQuery = true)
	Admin buscarAdmin(String email, String senha);
}
