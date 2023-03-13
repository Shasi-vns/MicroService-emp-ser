package com.microservice.empser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/emp")
public class Controller {
	
	@Autowired
	private EmployeeService empser;
	
	@Autowired
	private RestTemplate template;
	
	@GetMapping("/getall")
	public List<Employee> allemps(){
		return empser.getAll();
	}
	
	@PostMapping("/addEmp")
	public Employee addemp(@RequestBody TransactionBody tb) {
		Employee e = tb.getEmp();
		String uname = tb.getUname();
		
		Integer result = template.postForObject("http://User-Ser/user/verify", uname,Integer.class);
		if (result != 0) {
			e.setCreatedUserId(result);
		return empser.addEmp(e);
		}
		else {
			return new Employee();
		}
		
	}
	
}
