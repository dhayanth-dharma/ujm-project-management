package com.potting.service;


import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcps2.resttemplate.RestService;
import com.pottin.model.*;
import com.potting.repo.PotRepository;
import com.potting.repo.PowerRepository;
import com.potting.repo.CupconsumeRepository;
import com.potting.repo.ProductRepository;
import com.potting.repo.SpeedRepository;
import com.potting.websocket.Message_Handler_Singleton;
import com.potting.websocket.ResponseMessageModel;
@Service 
public class PottinLogic {
	public String message;
	ResponseMessageModel msg;
	Message_Handler_Singleton instance;
	UserInteractionSingleton userinter;
	@Autowired 
	private PotRepository potRepo;
	@Autowired 
	private SpeedRepository speedRepo;
	@Autowired 
	private CupconsumeRepository cupRepo;
	@Autowired
	private PowerRepository powerRepo;

	public PottinLogic() {
	
		msg=new ResponseMessageModel();
	    instance=Message_Handler_Singleton.getInstance();
        userinter=UserInteractionSingleton.getInstance();
	}
	
	
	public Pot getPotData(Input_Prop obj)
	{
		Pot pot=null;
		
		Iterable<Pot> list=potRepo.findAll();
		
	     
			for(Pot item : list)
			{
//				System.out.println(obj.getCup_size());
				if(item.getSize().equals(obj.getCup_size()))
				{
					pot=item;
				}
			}
		
		if(pot!=null)
			return pot;
		else 
			return null;		
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
	
	public double checkPottingSpeed()
	{
		Speed speed=null;
		Iterable<Speed> speedList=speedRepo.findAll();
		for(Speed item : speedList)
		{
			
			speed=item;
		}
		return speed.getSpeed();
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
		 Object output= rest.pottingWasteInform(obj);
		 if(output != null)
			 return true;
		 else 
			 return false;
	}
	public int initiate(Input_Prop obj)
	{
		try {
				msg.page_id=2;
			    msg.func_id=0;
				msg.message="Potting process initiating...";
				msg.message_type="message";
				instance.sendMsh(msg);		
				Thread.sleep(4000);
				msg.message="processing_ checking presence of pot";
				instance.sendMsh(msg);
			

				if(this.check_presence_product(obj))
				{
					if(userinter.getStop())
					{
						stop();
						return 2;
					}

//you can find this code in pottinLogic class
Thread.sleep(2000);
msg.message="success_ checking presence of Cup";
msg.page_id=2;
msg.func_id=106;
msg.message_type="message";
instance.sendMsh(msg);	
if(userinter.getStop())
{
	stop();
	return 2;
}

Thread.sleep(3000);
msg.message="Checking Pot Size";
msg.page_id=2;
msg.func_id=0;
msg.message_type="message";
instance.sendMsh(msg);		
int potSize= check_pot_size(obj);
System.out.println(potSize);
Thread.sleep(3000);

if(userinter.getStop())
{
	stop();
	return 2;
}
		 		    msg.message="Pot size checked";
				    msg.page_id=2;
				    msg.func_id=102;
				    msg.message_type="message";
				    msg.arg=potSize;
				    instance.sendMsh(msg);		
				    Thread.sleep(2000);


				    //Pottin speed *****************************
				    msg.message="Checking potting speed";
				    msg.page_id=2;
				    msg.func_id=0;
				    msg.message_type="message";
				    msg.arg=0;
				    instance.sendMsh(msg);		
				    Thread.sleep(3000);
				    double potting_speed=checkPottingSpeed();
				    msg.message="Potting Speed is :  "+potting_speed;
				    msg.page_id=2;
				    msg.func_id=118;
				    msg.message_type="message";
				    msg.arg=potting_speed;
				    instance.sendMsh(msg);
				    
				    Thread.sleep(3000);
		 		    msg.message="Checking Pot Availability";
				    msg.page_id=2;
				    msg.func_id=0;
				    msg.message_type="message";
				    instance.sendMsh(msg);		
		 		    
					if(userinter.getStop())
					{
						stop();
						return 2;
					}
		 		   Thread.sleep(3000);
		 		    Pot pot=null;
		 		    pot=getPotData(obj);
	
		 		   if(pot!=null)
		 		   {
		 			   Thread.sleep(2000);
		 	 		    msg.message="Getting Pot Data";
		 			    msg.page_id=2;
		 			    msg.func_id=103;
		 			    msg.message_type="message";
		 			    msg.arg=pot.getName();
		 			    instance.sendMsh(msg);	
		 			    Thread.sleep(4000);
		 			   
		 	 		    
		 			   Pot prod=null;
		 			   prod=checkAvailability(obj);
		 			   if(prod!=null)
		 			   {
		 				  msg.message="Available " +prod.getName()+" :"+prod.getAvailability();
			 			    msg.page_id=2;
			 			    msg.func_id=104;
			 			    msg.message_type="message";
			 			    msg.arg=pot.getAvailability();
			 			    instance.sendMsh(msg);	
		 				   
		 				   if(prod.getAvailability() >0)
		 				   {
		 					  Thread.sleep(2000);
		 					    msg.message="Start Potting";
			 	 			    msg.page_id=2;
			 	 			    msg.func_id=105;
			 	 			    msg.arg=genRand(0,8);
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);
			 	 			   Thread.sleep(1000);
			 	 			   Thread.sleep(2000);

			 	 			    msg.arg=genRand(9,25);
							    msg.message="Potting :"+msg.arg+"%";
			 	 			    msg.page_id=2;
			 	 			    msg.func_id=105;
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);
			 	 			  Thread.sleep(3000);
			 	 			  	msg.arg=genRand(27,49);	
			 	 			  	msg.message="Potting :"+msg.arg+"%";
			 	 			  	msg.page_id=2;
			 	 			    msg.func_id=105;
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);
			 	 			  Thread.sleep(3000);
			 	 			  	msg.arg=genRand(52,75);
			 	 			  	msg.message="Potting :"+msg.arg+"%";
		 	 			    	msg.page_id=2;
			 	 			    msg.func_id=105;
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);
			 	 			  Thread.sleep(3000);
			 	 			  
			 	 			    msg.arg=genRand(83,98);
							    msg.message="Completing..:"+msg.arg+"%";

			 	 			    msg.page_id=2;
			 	 			    msg.func_id=105;
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);
			 	 			  Thread.sleep(4000);
							    msg.message="Done..:"+100+"%";
			 	 			    msg.page_id=2;
			 	 			    msg.func_id=105;
			 	 			    msg.arg=100;
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);
			 	 			  Thread.sleep(4000);
							    msg.message="Updating Pot Count";
							    msg.page_id=2;
							    msg.func_id=0;
			 	 			    msg.arg=0;
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);
			 	 			    if(updateAvailability( prod))
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
			 	 			 
			 	 			    msg.message="Connected. Waiting for next command";
			 	 			    msg.page_id=2;
				 	 		    msg.func_id=108;
			 	 			    msg.arg=0;
			 	 			    msg.message_type="message";
			 	 			    Thread.sleep(3000);
			 	 			    
			 	 			    instance.sendMsh(msg);
			 	 			    return 1;
			 			    
		 				   }
		 				   else
		 				   {
		 					    msg.message="Not enough Material";
			 	 			    msg.page_id=2;
			 	 			    msg.func_id=0;
			 	 			    msg.message_type="message";
			 	 			    instance.sendMsh(msg);	
			 	 			  msg.message="Connected. Waiting for next command";
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
		 	 			  msg.message="Connected. Waiting for next command";
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
		 			   msg.message="Connected. Waiting for next command";
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
				msg.message="Connected. Waiting for next command";
	 			    msg.page_id=2;
 	 		    msg.func_id=108;
	 			    msg.arg=0;
	 			    msg.message_type="message";
	 			    Thread.sleep(3000);
	 			    
	 			    instance.sendMsh(msg);
				return 0;
			}
				//check is pot damage
				//if damage add to damage pot and again substract 2 in availability
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg.message="Connected. Waiting for next command";
			    msg.page_id=2;
	 		    msg.func_id=108;
			    msg.arg=0;
			    msg.message_type="message";
			    try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {

					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			    
			    instance.sendMsh(msg);
			e.printStackTrace();
			return 0;
		}

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
	
	public boolean isDamage()
	{
		int value=genRand(0, 3);
		if(value==1)
			return true;
		else
			return false;
	}
	public boolean updateAvailability(Pot pot)
	{
		int avail=Integer.valueOf(pot.getAvailability());
		int updatedAvail=avail-1;
		pot.setAvailability(updatedAvail);
		Pot result=potRepo.save(pot);
		
	  if(result!=null)
			return true;
		else
			return false;
				
	}
	
public Pot checkAvailability(Input_Prop obj)
{
	Pot pot=null;
	Iterable<Cupconsume> cupList=cupRepo.findAll();
	Iterable<Pot> potlist=potRepo.findAll();
	Cupconsume cup=null;
	for(Cupconsume item : cupList)
	{	
		if(item.getSize().equals(obj.getCup_size()))
		{
			cup=item;
		}
	} 
	for(Pot item : potlist)
	{
		
		if(item.getName().equals(cup.getName()))
		{
			pot=item;
		}
	}
	if(pot!=null)
		return pot;
	else
		return null;	
}
	
	public int check_pot_size(Input_Prop obj)
	{
			return Integer.valueOf(obj.getCup_size());
		
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
	
	public int genRand(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	
}
