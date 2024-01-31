package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Customers;


public interface CustomersRepository extends JpaRepository<Customers, Integer>{

	@Query(value = "SELECT * FROM customers WHERE email = ?1 and password = ?2", nativeQuery = true)
	Customers findByEmailAndPassword(String email, String pass);

}
