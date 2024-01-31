package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Customers;
import com.example.repository.CustomersRepository;

@Service
public class CustomerService {

	@Autowired
	CustomersRepository customersRepository;
	
	public List<Customers> getAllCustomers(){
		return customersRepository.findAll();
	}
	
	public Customers getCustomerById(int id) {
		return customersRepository.findById(id).orElse(null);
	}
	
	public Customers getCustomerByEmailAndPassword(String email, String pass) {
		return customersRepository.findByEmailAndPassword(email, pass);
//		return null;
	}
}
