package com.sweetopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sweetopia.entity.Order;
import com.sweetopia.exception.OrderNotFoundException;
import com.sweetopia.service.OrderService;

import jakarta.validation.Valid;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderservice;
	
	
	
	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) throws OrderNotFoundException{
		Order ord=orderservice.addSweetOrder(order);
		return new ResponseEntity<>(ord, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/orders")
	public ResponseEntity<Order> UpdateSweetOrder(@Valid @RequestBody Order order) throws OrderNotFoundException {
		
		Order ord=orderservice.updateSweetOrder(order);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<Order> cancelSweetOrder(@PathVariable Long orderId) throws OrderNotFoundException{
		Order ord= orderservice.cancelSweetOrder(orderId);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> showAllSweetOrder() throws OrderNotFoundException{
		List<Order> ord = orderservice.showAllSweetOrder();
		return new ResponseEntity<>(ord, HttpStatus.OK);
		
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<List<Order>>  showAllSweetOrderById(@PathVariable Long orderId) throws OrderNotFoundException{
		List<Order> ord = orderservice.showAllSweetOrderById(orderId);
		return new ResponseEntity<>(ord, HttpStatus.OK);
		
	}
	
//	@GetMapping("/orders/{orderId}")
	public ResponseEntity<String> calculateTotalOrdercost(Long orderId) throws OrderNotFoundException{
		double total=orderservice.calculateTotalOrdercost(orderId);
		return new ResponseEntity<>("total order amount is "+total, HttpStatus.OK);
	}
	
	
	

}
