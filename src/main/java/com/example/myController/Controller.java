package com.example.myController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Customers;
import com.example.entity.Orders;
import com.example.service.CustomerService;
import com.example.service.JedisService;
import com.example.service.OrderService;
import com.example.userAuth.CustomerAuth;

@RestController
public class Controller {
	
	private static String custRedisKey;
	private static String orderRedisKey;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	JedisService jedisService;

	@GetMapping("/")
	public String home() {
		String userName = jedisService.getCust(custRedisKey, "name");
		if (userName.isEmpty()) {
			return "Please Login First";
		}
		return "<h1>Hello " + userName + "</h1>";
	}
	
	@GetMapping("/logout")
	public String logout() {
		long del = jedisService.delCust(custRedisKey);
		if(del != 1) {
			return "<h1>No user found</h1>";
		}
		return "<h1>Thank you! Please vist again</h1>";
	}

	@PostMapping("/auth")
	public Customers authenticate(@RequestBody CustomerAuth data) {

		Customers authCust = customerService.getCustomerByEmailAndPassword(data.getEmail(), data.getPassword());

		if (authCust != null) {

//			String redisKey = "Customer:" + authCust.getCustomerId();
			prepareCustHset(authCust);
			prepareCustHset(authCust.getCustomerId());
//			System.out.println(result);
		}

		return authCust;
	}

	public String prepareCustHset(Customers cust) {
		Map<String, String> hashMap = new HashMap<>();
		String id = cust.getCustomerId()+"";
        hashMap.put("id",id);
        hashMap.put("name", cust.getName());
        hashMap.put("email", cust.getEmail());
        hashMap.put("password", cust.getPassword());
        hashMap.put("shippingAddress", cust.getShippingAddress());
        hashMap.put("purchaseHist", cust.getPurchaseHistory() != null?cust.getPurchaseHistory():"" );

        custRedisKey = "Customer:" + id;
        String result = jedisService.setValue(custRedisKey, hashMap);
        System.out.println("HMSET Result: " + result);
		return result;
	}
	
	@PostMapping("/cust/{id}")
	public Customers getCache(@PathVariable Integer id) {
		if(custRedisKey == null) return null;
        String name = jedisService.getCust(custRedisKey, "name");
        String email = jedisService.getCust(custRedisKey, "email");
        String password = jedisService.getCust(custRedisKey, "password");
        String shippingAddress = jedisService.getCust(custRedisKey, "shippingAddress");
        String purchase = jedisService.getCust(custRedisKey, "purchaseHist");
        
		return new Customers(id,name,email,password,shippingAddress,purchase);
	}
	
	public String prepareCustHset(int id) {
		Orders order = orderService.getOrderById(id);
		
		if(order != null) {
			prepareOrderHset(order);
		}
		return "No order Found";
	}
	
	@PostMapping("/order/{id}")
	public Orders getOrder(@PathVariable int id) {
		
        int orderId = Integer.parseInt(jedisService.getCust(orderRedisKey, "orderId")); 
        Date orderDate = new Date(jedisService.getCust(orderRedisKey, "orderDate"));
        String status = jedisService.getCust(orderRedisKey, "status");
        
		return new Orders(orderId,id,orderDate,status);
		
	}
	
	public String prepareOrderHset(Orders order) {
		Map<String, String> hashMap = new HashMap<>();
		String id = order.getCustomerId()+"";
        hashMap.put("orderId",id);
        hashMap.put("orderDate", order.getOrderDate().toLocaleString());
        hashMap.put("status", order.getStatus());

        orderRedisKey = "Order:" + id;
        String result = jedisService.setValue(orderRedisKey, hashMap);
        System.out.println("HMSET Result: " + result);
		return result;
	}

}
