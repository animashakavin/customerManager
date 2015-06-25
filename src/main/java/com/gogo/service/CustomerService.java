package com.gogo.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gogo.bean.Customer;
import com.gogo.dao.CustomerDAO;
import com.gogo.exception.CustomerExists;

@RestController
@RequestMapping("/customer")
public class CustomerService {
	
	
	@Autowired
	private CustomerDAO custDAO;
	
	
	
	@RequestMapping(value="/post", method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String createCustomer(@RequestBody(required = true) Customer customer)  {
		String result = addCustomer(customer);
		return new String(result);
	} 
	
	
	
	@RequestMapping(value="/post/jsonp", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String createCustomer(@RequestParam("callback") String callBack, @RequestParam("customer") String customer)  {
		
		Customer cust = new Customer();
		JsonParser parser =JsonParserFactory.getJsonParser();
		System.out.println("String that we got "+URLDecoder.decode(customer));
		Map jsonMap=parser.parseMap(URLDecoder.decode(customer));	
		cust.setAddress((String)jsonMap.get("address")); 
		cust.setEmail((String) jsonMap.get("email"));
		cust.setName((String) jsonMap.get("name"));
		
		StringBuffer result = new StringBuffer();
		result.append(callBack);
		result.append("(");
		result.append("'"+addCustomer(cust)+"'");
		result.append(")");
		return result.toString();
	} 
	
	
	
	@RequestMapping( value="/get", method = RequestMethod.GET )
	public Set<Customer> getCustomer()  {
		System.out.println("inside get method");
		return custDAO.getCustomers();
	} 

	
	private  String addCustomer(Customer customer) {
		StringBuffer result = new StringBuffer();
		try {
			custDAO.addCustomer(customer);
			result = result.append(customer);
		} catch (CustomerExists e) {
			result = result.append(e.getMessage());
		}
		
		return result.toString();
		
	}
	
}
