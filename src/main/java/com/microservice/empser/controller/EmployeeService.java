package com.microservice.empser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRep;
	
	public List<Employee> getAll(){
		List<Employee> emplist=empRep.findAll();
		
		if (emplist.size() >0) {
    		return emplist;
    	}
    	else {
    		return new ArrayList<Employee>();
    	}
	}
	
	
	public Employee addEmp(Employee e) {
		return empRep.save(e);
	}
}
