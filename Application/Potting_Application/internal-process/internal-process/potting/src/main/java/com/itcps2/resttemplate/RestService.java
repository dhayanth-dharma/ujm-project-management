package com.itcps2.resttemplate;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.pottin.model.Input_Prop;
import com.pottin.model.WasteManagement;


//@Service
public class RestService {


	private RestTemplate restTemplate;
	public RestService() {

		   restTemplate = new RestTemplate();
		     
		    
	}
	
	public void test()
	{
		final String uri = "https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22";
	    
		 restTemplate.getForObject(uri,String.class);
	     ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);
	     Object objects = responseEntity.getBody();
	     System.out.println(objects);
	     MediaType contentType = responseEntity.getHeaders().getContentType();
	}
	
	public void initPackage(String param)
	{

		final String uri = "https://192.168.1.12/api/initiate_packaging?params="+param;
	    
		 restTemplate.getForObject(uri,String.class);
	     ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);
	     System.out.println( responseEntity);
	}
	
	public Object pottingWasteInform(WasteManagement obj)
	{
		final String uri = "https://192.168.1.12/api/pottingWasteInform?status=";
	    
		 restTemplate.getForObject(uri,String.class);
	     ResponseEntity<Object> responseEntity = restTemplate.postForEntity(uri,obj, Object.class);
	     Object objects = responseEntity.getBody();
	     System.out.println(objects);
	     MediaType contentType = responseEntity.getHeaders().getContentType();
	     return objects;
	}
	public Object initiatePotting(Input_Prop obj)
	{
		final String uri = "https://localhost:8082/api/start_potting";
	    
		 restTemplate.getForObject(uri,String.class);
	     ResponseEntity<Object> responseEntity = restTemplate.postForEntity(uri,obj, Object.class);
	     Object objects = responseEntity.getBody();
	     System.out.println(objects);
	     MediaType contentType = responseEntity.getHeaders().getContentType();
	     return objects;
	}
	
	//this code can be found in restService class
	public Object getPowerAllocation()
	{
		final String uri = "https://192.168.1.12/api/opc/powerManagement?workshop=potting";
	    
		 restTemplate.getForObject(uri,String.class);
	     ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);
	     Object objects = responseEntity.getBody();
	     MediaType contentType = responseEntity.getHeaders().getContentType();
	     return objects;
	}
	
	public Object getPostsPlainJSON(String url, Input_Prop obj) {
	    	 HttpHeaders headers = new HttpHeaders();
	    	 
	        return this.restTemplate.postForObject(url, obj, Object.class);
	    }
	    public Object getPlainJSON(String url) {
	    	 HttpHeaders headers = new HttpHeaders();
	    	 
	        return this.restTemplate.getForObject(url,  Object.class);
	    }
	    
	    public Object[] getPostsPlainJSONList(String url, Input_Prop obj) {
	    	 HttpHeaders headers = new HttpHeaders();
	    	 
	        return this.restTemplate.postForObject(url, obj, Object[].class);
	    }
	

}
