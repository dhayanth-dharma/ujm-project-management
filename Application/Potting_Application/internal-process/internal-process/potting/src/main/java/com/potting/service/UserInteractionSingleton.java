package com.potting.service;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInteractionSingleton {
	private static UserInteractionSingleton obj=null;
	private boolean stop;
	private UserInteractionSingleton()
	{
		stop=false;
		
	}
	
	public static UserInteractionSingleton getInstance() 
    { 
        if (obj == null) 
        	obj = new UserInteractionSingleton(); 
  
        return obj; 
    }
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public boolean getStop()
	{
		return stop;
	}
	
	
}
