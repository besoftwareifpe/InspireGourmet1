package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.AdminDAO;
import com.example.demo.models.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminDAO repository;
	
	public Admin verificaAdmin(String email, String senha) {
		return repository.buscarAdmin(email, senha);
	}
	
	public Admin get(Integer idAdmin) {
		
		return repository.findById(idAdmin).get();
	}
}
