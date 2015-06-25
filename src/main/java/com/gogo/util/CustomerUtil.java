package com.gogo.util;

import java.util.HashSet;
import java.util.Set;

import com.gogo.bean.Customer;
import com.gogo.exception.CustomerExists;

public class CustomerUtil {
private static Set<Customer> custSet = new HashSet<Customer>();
	
	public static void addCustomer(Customer customer) throws CustomerExists{
		if (!custSet.add(customer)){
			 throw new CustomerExists("Custmer Name Available");
		}
		
	}
	public static Set<Customer> getCustomers() {
		
		return custSet;
	}
}
