package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Orders;
import com.example.repository.OrdersRepository;

@Service
public class OrderService {

	@Autowired
	OrdersRepository ordersRepository;
	
	public Orders getOrderById(int id) {
		return ordersRepository.findById(id).orElse(null);
	}
}
