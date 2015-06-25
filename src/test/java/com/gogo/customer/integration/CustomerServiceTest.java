package com.gogo.customer.integration;


import java.net.URLEncoder;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.gogo.bean.Customer;
import com.gogo.customer.boot.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ 
	"server.port:9000"
	})
public class CustomerServiceTest {

	
	private RestTemplate restTemplate = new TestRestTemplate();
	private Customer customer = null;

	
	@Before
    public void setup() throws Exception {
		customer = new Customer();
		customer.setAddress("Airlingeton hights");
		customer.setEmail("info@gogoair.com");
		customer.setName("GoGo");
    }
	
	@Test
	public void testCreateCustomer() throws Exception  {
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType((MediaType.APPLICATION_JSON));
			ResponseEntity<String> response =  restTemplate.postForEntity("http://video.gogoinflight.com:9000/customer/post",  new HttpEntity(customer, headers), String.class);
			String body = response.getBody();
			System.out.println(response.getBody().toString() );
			Assert.assertNotNull(response);
			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testCreatCustomerFail() throws Exception{
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType((MediaType.APPLICATION_JSON));
			ResponseEntity<String> response =  restTemplate.postForEntity("http://video.gogoinflight.com:9000/customer/post",  new HttpEntity(customer, headers), String.class);
			Assert.assertNotNull(response);
			Assert.assertEquals(response.getBody(),"Custmer Name Available");
	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@Test
	public void testCreatCustomerJSONP() throws Exception{
		
		try {
			StringBuffer url = new StringBuffer("http://video.gogoinflight.com:9000/customer/post/jsonp?callback=myfunction&customer=");
			url.append(URLEncoder.encode(customer.toString()));
			
			System.out.println("URL -->"+url.toString());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType((MediaType.APPLICATION_JSON));
			ResponseEntity<String> response =  restTemplate.getForEntity(url.toString(),String.class);
			Assert.assertNotNull(response);
			Assert.assertEquals(response.getBody(),"myfunction('"+customer.toString()+"')");
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		
	}
	
	@Test
	public void testShowCustmers() throws Exception {
		StringBuffer url = new StringBuffer("http://video.gogoinflight.com:9000/customer/get/");
		ResponseEntity<Set> response =  restTemplate.getForEntity(url.toString(),Set.class);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.getBody().size(),1);
		
		
	}
	
	/*protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }*/
}
