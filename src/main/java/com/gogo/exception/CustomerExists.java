package com.gogo.exception;

public class CustomerExists extends Exception{
	
	public CustomerExists(){
		
	}
	
	public CustomerExists(String message){
		super(message);
	}
	

}
