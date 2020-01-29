package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Voucher;


public interface DocumentDAO extends JpaRepository<Voucher, Integer> {
	

}
