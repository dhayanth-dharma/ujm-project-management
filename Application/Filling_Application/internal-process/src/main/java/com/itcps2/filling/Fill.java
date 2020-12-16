package com.itcps2.filling;

import java.io.IOException;
import java.util.Random;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcps2.filling.model.Output_Prop;
import com.itcps2.filling.webscoket.config.MessageModel;
import com.itcps2.filling.webscoket.config.Message_Handler_Singleton;
import com.itcps2.filling.webscoket.config.ResponseMessageModel;
import com.itcps2.service.UserInteractionSingleton;

public class Fill {

	public int process_id;
	public String status;
	public String message;
	public String error;
	public boolean on_duty;
	public byte stoped;
	public byte started;
	ResponseMessageModel msg;
	Message_Handler_Singleton instance;
	
	public Fill() {
		msg=new ResponseMessageModel();
	    instance=Message_Handler_Singleton.getInstance();
	}
	
	
	public Fill interupt(int command)
	{
		try {
			
//		ObjectMapper objectMapper = new ObjectMapper();
//		Output_Prop out=new Output_Prop();
//		out.setAcquired_fault(true);
//		out.setMagnet(true);
//		
		
//		msg.purpose="something";
//		msg.function="dskjfhksdjfh";
//        //Converting the Object to JSONString
//        String jsonString = objectMapper.writeValueAsString(out);
//         
        Fill fill=new  Fill();
        
//        WebSocketSession session=	getSession();
//		session.sendMessage(new TextMessage(jsonString));
//        
		//use this two lines to send message
		//Message_Handler_Singleton instance=Message_Handler_Singleton.getInstance();
        //instance.sendMsh(msg);
        
        
        msg.message="System Interuption Occured";
        msg.page_id=3;
        msg.func_id=111;
        msg.message_type="message";
        Message_Handler_Singleton instance=Message_Handler_Singleton.getInstance();
        UserInteractionSingleton userInt=UserInteractionSingleton.getInstance();
        
        instance.sendMsh(msg);		
	    Thread.sleep(3000);
		if (command==1)
		{
			
			userInt.setStop(true);
			fill.process_id=genRand(34,3423);
			fill.message="System Offline";
			fill.error="interupted";
			fill.on_duty=false;
			fill.stoped=1;
			fill.started=0;
			
			//messaging service
			msg.message="System Offline";
			msg.page_id=3;
			msg.func_id=112;
			msg.message_type="message";
	        instance.sendMsh(msg);		
		
		}
		else if( command==0)
		{
			
			userInt.setStop(false);
			
			fill.process_id=genRand(34,3423);
			fill.process_id=genRand(34,3423);
			fill.message="starting";
			fill.error="";
			fill.on_duty=true;
			fill.stoped=0;
			fill.started=1;
			//sending message
			//messaging service
			msg.message="System Starting..";
			msg.page_id=3;
			msg.func_id=113;
			msg.message_type="message";
	        instance.sendMsh(msg);		
			Thread.sleep(3000);
			msg.message="Socket connected... Application on live update";
			msg.page_id=3;
			msg.func_id=113;
			msg.message_type="message";
	        instance.sendMsh(msg);		
			
		}
		
		return fill;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return null;
		}
		
	}
	public int genRand(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	private WebSocketSession getSession()
	{
		Message_Handler_Singleton instance=Message_Handler_Singleton.getInstance();
		return instance.getSession();
	}
}
