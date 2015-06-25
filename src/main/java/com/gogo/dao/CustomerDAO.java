package com.gogo.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gogo.bean.Customer;
import com.gogo.exception.CustomerExists;
@Component
public class CustomerDAO {
	private static Set<Customer> custSet = new HashSet<Customer>();
	public void addCustomer(Customer customer) throws CustomerExists {
		
		if (!custSet.add(customer)){
			 throw new CustomerExists("Custmer Name Available");
		}
		
	}
	
	public   Set<Customer> getCustomers() {
		
		return custSet;
	}

}
