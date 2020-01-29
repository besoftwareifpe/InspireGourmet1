package com.example.demo.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.daos.DocumentDAO;
import com.example.demo.models.Voucher;


@Service
@Transactional
public class DocumentService {
	
	@Autowired
	private DocumentDAO Dao;
	
public void save(Voucher voucher) {
		
	
		//set a hash of user
		String hashCode = UUID.randomUUID().toString();
		voucher.setHashCode(hashCode);
		Dao.save(voucher);
	}
}
