package com.itcps2.filling.logic;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


import java.util.Optional;
import com.itcps2.filling.Fill;
import com.itcps2.filling.model.*;
import com.itcps2.filling.webscoket.config.MessageModel;
import com.itcps2.filling.webscoket.config.Message_Handler_Singleton;
import com.itcps2.filling.webscoket.config.ResponseMessageModel;
import com.itcps2.repository.CupconsumeRepository;
import com.itcps2.repository.PowerRepository;
import com.itcps2.repository.ProductRepository;
import com.itcps2.repository.UserRepository;
import com.itcps2.resttemplate.RestService;
import com.itcps2.service.CupconsumeService;
import com.itcps2.service.ProductService;
import com.itcps2.service.UserInteractionSingleton;

@Service 
public class FillingLogic {


	@Autowired 
	private ProductRepository productRepo;
	

	@Autowired
	private CupconsumeRepository cupConsumeRepo;
	
	@Autowired
	private PowerRepository powerRepo;
	

	public boolean check_internal_;
	public String message;
	ResponseMessageModel msg;
	Message_Handler_Singleton instance;
	UserInteractionSingleton systemStatus;
	public FillingLogic() {
		this.check_internal_=false;
		msg=new ResponseMessageModel();
	    instance=Message_Handler_Singleton.getInstance();
        systemStatus=UserInteractionSingleton.getInstance();
	}
	
	public boolean powerCheck()
	{
		Optional<Power> power=powerRepo.findById(1);
		double leftPower=power.get().getValue();
		return (leftPower<25.0);
	}
	
	public boolean informToWasteManagement(WasteManagement obj)
	{
		
		 RestService rest=new RestService();
		 Object output= rest.informWasteManage(obj);
		 if(output != null)
			 return true;
		 else 
			 return false;
	}
	
	public boolean initiatePotting(Input_Prop obj)
	{
		obj.weight=200;
		obj.speed=5;
		 RestService rest=new RestService();
		 Object output= rest.initiatePotting(obj);
		 if(output != null)
			 return true;
		 else 
			 return false;
	}
	
	
	public int checkIsCupDamange()
	{
		
		return genRand(0,7);
	}
	
	public int initiate(Input_Prop obj)
	{
	try {
		if(systemStatus.getStop())
		{
			stop();
			return 2;
		}
		//checking power
		if(powerCheck())
		{
			stop();
			return 2;
		}
		
		msg.page_id=2;
	    msg.func_id=0;
		msg.message="Filling process initiating...";
		msg.message_type="message";
		instance.sendMsh(msg);		
		Thread.sleep(4000);
		msg.message="processing_ checking presence of pot";
		instance.sendMsh(msg);		
		
		msg.page_id=2;
	    msg.func_id=0;
		msg.message="Checking cup status ...";
		msg.message_type="message";
		instance.sendMsh(msg);		
		Thread.sleep(4000);
		
		instance.sendMsh(msg);

		
		if(this.check_presence_product(obj))
		{
			if(systemStatus.getStop())
			{
				stop();
				return 2;
			}
			Thread.sleep(2000);
		    msg.message="success_ checking presence of Cup";
		    msg.page_id=2;
		    msg.func_id=106;
		    msg.message_type="message";
 		    instance.sendMsh(msg);		
 		    
 		    Thread.sleep(3000);
 		    msg.message="Checking Cup Size";
		    msg.page_id=2;
		    msg.func_id=0;
		    msg.message_type="message";
		    instance.sendMsh(msg);		
 		    int cupSize= check_pot_size(obj);
 		    Thread.sleep(3000);

 			if(systemStatus.getStop())
 			{
 				stop();
 				return 2;
 			}
 		    msg.message="Cup size checked";
		    msg.page_id=2;
		    msg.func_id=102;
		    msg.message_type="message";
		    msg.arg=cupSize;
		    instance.sendMsh(msg);		
		    
		    Thread.sleep(3000);
 		    msg.message="Checking Product Availability";
		    msg.page_id=2;
		    msg.func_id=0;
		    msg.message_type="message";
		    instance.sendMsh(msg);		
 		    
			if(systemStatus.getStop())
			{
				stop();
				return 2;
			}
 		   Thread.sleep(3000);
 		    Cupconsume cupCon=null;
 		    cupCon=getCupData(obj);

 		   if(cupCon!=null)
 		   {
 			   Thread.sleep(2000);
 	 		    msg.message="Getting product data";
 			    msg.page_id=2;
 			    msg.func_id=103;
 			    msg.message_type="message";
 			    msg.arg=cupCon.getName();
 			    instance.sendMsh(msg);	
 			    Thread.sleep(4000);
 			    
 			   Product prod=null;
 			   prod=getProductData(obj);
 			  Thread.sleep(500);
	 		    msg.message="Available Product: "+prod.getAvailability();
			    msg.page_id=2;
			    msg.func_id=104;
			    msg.message_type="message";
			    msg.arg=prod.getAvailability();
			    instance.sendMsh(msg);	
			    Thread.sleep(2000);
 			   if(prod!=null)
 			   {
 				   int availability=Integer.valueOf(prod.getAvailability());
 				   int requestedWeight=Integer.valueOf(cupCon.getConsume());
 				   if(availability >= requestedWeight)
 				   {
 					  Thread.sleep(2000);
 					    msg.message="Filling";
	 	 			    msg.page_id=2;
	 	 			    msg.func_id=105;
	 	 			    msg.arg=genRand(0,8);
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			   Thread.sleep(2000);
	 	 			   Thread.sleep(2000);
					    msg.message="Filling";
	 	 			    msg.page_id=2;
	 	 			    msg.func_id=105;
	 	 			    msg.arg=genRand(9,25);
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			  Thread.sleep(3000);
					    msg.message="Filling";
	 	 			    msg.page_id=2;
	 	 			    msg.func_id=105;
	 	 			    msg.arg=genRand(27,49);
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			  Thread.sleep(3000);
					    msg.message="Filling";
	 	 			    msg.page_id=2;
	 	 			    msg.func_id=105;
	 	 			    msg.arg=genRand(52,75);
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			  Thread.sleep(3000);
					    msg.message="Completing..";
	 	 			    msg.page_id=2;
	 	 			    msg.func_id=105;
	 	 			    msg.arg=genRand(83,98);
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			  Thread.sleep(4000);
					    msg.message="Done..";
	 	 			    msg.page_id=2;
	 	 			    msg.func_id=105;
	 	 			    msg.arg=100;
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			  Thread.sleep(4000);
					    msg.message="Updating product material availability";
					    msg.page_id=2;
					    msg.func_id=0;
	 	 			    msg.arg=0;
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			    if(updateAvailability(cupCon, prod))
 	 			    	msg.message="Successfully updated.";
	 	 			    msg.page_id=2;
		 	 		    msg.func_id=0;
	 	 			    msg.arg=0;
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			    
	 	 			    Thread.sleep(2000);
	 	 			    msg.message="Reseting Parameters";
	 	 			    msg.page_id=2;
		 	 		    msg.func_id=108;
	 	 			    msg.arg=0;
	 	 			    msg.message_type="message";
	 	 			    Thread.sleep(3000);
	 	 			    
	 	 			    instance.sendMsh(msg);
	 	 			    
	 	 			    //updating power
	 	 			    double powerVal=updatePower();
	 	 			    msg.message="Reseting Parameters";
	 	 			    msg.page_id=2;
		 	 		    msg.func_id=114;
	 	 			    msg.arg=powerVal;
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			    
	 	 			    initiatePotting(obj);
	 	 			    
	 	 			    return 1;
	 			    
 				   }
 				   else
 				   {
 					    msg.message="Not enough Material";
	 	 			    msg.page_id=2;
	 	 			    msg.func_id=0;
	 	 			    msg.message_type="message";
	 	 			    instance.sendMsh(msg);
	 	 			  
	 	 			    msg.message="Reseting Parameters";
	 	 			    msg.page_id=2;
		 	 		    msg.func_id=108;
	 	 			    msg.arg=0;
	 	 			    msg.message_type="message";
	 	 			    Thread.sleep(3000);
	 	 			    
	 	 			    instance.sendMsh(msg);
	 	 			    
	 	 			   
	 	 			    return 0;
 				   }
 			   }
 			   else
 			   {
 				  msg.message="Requested Material not available";
 	 			    msg.page_id=2;
 	 			    msg.func_id=0;
 	 			    msg.message_type="message";
 	 			    instance.sendMsh(msg);		
 	 			  msg.message="Reseting Parameters";
	 			    msg.page_id=2;
	 	 		    msg.func_id=108;
	 			    msg.arg=0;
	 			    msg.message_type="message";
	 			    Thread.sleep(3000);
	 			    
	 			    instance.sendMsh(msg);
 	 			    return 0;
 			   }
 		   }
 		   else
 		   {
 			    msg.message="Unknown Cup Size";
 			    msg.page_id=2;
 			    msg.func_id=0;
 			    msg.message_type="message";
 			    instance.sendMsh(msg);		
 			   msg.message="Reseting Parameters";
 			    msg.page_id=2;
	 		    msg.func_id=108;
 			    msg.arg=0;
 			    msg.message_type="message";
 			    Thread.sleep(3000);
 			    
 			    instance.sendMsh(msg);
 			    return 0;
 	 		       
 		   }
		}
		else
		{
			msg.message="Checking presence of pot failed, System sleep mode initiated";
			msg.message_type="message";
			instance.sendMsh(msg);	
			 msg.message="Reseting Parameters";
			    msg.page_id=2;
	 		    msg.func_id=108;
			    msg.arg=0;
			    msg.message_type="message";
			    Thread.sleep(3000);
			    
			    instance.sendMsh(msg);
			return 0;
		}
			
		
	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 msg.message="Reseting Parameters";
			    msg.page_id=2;
	 		    msg.func_id=108;
			    msg.arg=0;
			    msg.message_type="message";
			    try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    instance.sendMsh(msg);
			return 0;
	}
        
	}
	public void start()
	{
	                                     	
	}
	
	public void stop()
	{
		try {
			Fill fill=new Fill();
			msg.message="Process Terminated";
		    msg.page_id=2;
		    msg.func_id=0;
		    msg.message_type="message";
		    instance.sendMsh(msg);
		    
			Thread.sleep(3000);
			fill.interupt(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
//	internal check
	public Product checkAvailability(Input_Prop obj)
	{
		Product prod=null;
		
		
		Iterable<Product> list=productRepo.findAll();
		
	     
			for(Product item : list)
			{
				
				if(item.getName().equals(obj.getProduct_name()))
				{
					prod=item;
				}
			}
		if(prod!=null)
			return prod;
		else
			return null;
				
	}
//this code can be found in fillingLogic	
public boolean updateAvailability(Cupconsume cup, Product prod)
{
	int avail=Integer.valueOf(prod.getAvailability());
	int updatedAvail=avail-Integer.valueOf(cup.getConsume());
	prod.setAvailability(String.valueOf(updatedAvail));
	Product result=productRepo.save(prod);
	
  if(result!=null)
		return true;
	else
		return false;
			
}
	
public double updatePower()
{
	
		Power power=powerRepo.findById(1).get();
		double value=power.getValue();
		power.setValue(value-25);
		Power result=powerRepo.save(power);
		double leftPer=(power.getValue()/500)*100;
		double roundOff = Math.round(leftPer * 100.0) / 100.0;
		return roundOff;
}
	
	public Cupconsume getCupData(Input_Prop obj)
	{
		Cupconsume con=null;
		
		Iterable<Cupconsume> list=cupConsumeRepo.findAll();
		
	     
			for(Cupconsume item : list)
			{
//				System.out.println(obj.getCup_size());
				if(item.getSize().equals(obj.getCup_size()))
				{
					con=item;
				}
			}
		
		if(con!=null)
			return con;
		else 
			return null;
		
		
				
	}
	
	public void interupted()
	{
		
	}
	
//	external check
	public void check_pre()
	{
		
	}
	public boolean check_presence_product(Input_Prop obj)
	{
		
		System.out.print("checking presence of pot");
		if(obj.getPresence_pot())
		{
			return true;
		}
		else
			return false;
	}
	//this code can be found in fillingLogic class
public Product getProductData(Input_Prop obj)
{
	Product con=null;
	Iterable<Product> list=productRepo.findAll();
		for(Product item : list)
		{
			if(item.getName().equals(obj.getProduct_name()))
			{
				con=item;
			}
		}
	if(con!=null)
		return con;
	else
		return null;	
}
	public int check_pot_size(Input_Prop obj)
	{
			return Integer.valueOf(obj.getCup_size());
		
	}

	public int genRand(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	
	
}
