package com.itcps2.filling.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itcps2.filling.Fill;
import com.itcps2.filling.logic.FillingLogic;
import com.itcps2.filling.model.Cupconsume;
import com.itcps2.filling.model.Input_Prop;
import com.itcps2.filling.model.ResponseCustom;
import com.itcps2.filling.model.ResponseStatusDisplay;
import com.itcps2.filling.model.User;
import com.itcps2.repository.CupconsumeRepository;
import com.itcps2.repository.UserRepository;
import com.itcps2.resttemplate.RestService;
import com.itcps2.service.CupconsumeService;
import com.itcps2.service.UserService;

import channel.Channel_1;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api")
public class FillingController {

//	start_fill_test
	@Autowired
	private CupconsumeRepository cupRepo;
	@Autowired
	private  UserRepository userRepositoru;

	@Autowired
	private FillingLogic fillingLogic;


	@RequestMapping(method=RequestMethod.GET, path= "getTest")
	public Optional<User> getTest()
	{

		Optional<User> u=userRepositoru.findById(0);
		return u;
	}
	@RequestMapping(method=RequestMethod.GET, path= "getTest2")
	public Cupconsume getTest2()
	{
		
		Iterable<Cupconsume> list=cupRepo.findAll();
		Cupconsume cpm=null;
	     
		for(Cupconsume item : list)
		{
			
			if(item.getSize().equals("10"))
			{
				cpm=item;
			}
		}
		
		return cpm;
	}
	
	@RequestMapping(method=RequestMethod.GET, path= "getCupBySize")
	public Cupconsume getTest3()
	{
		Iterable<Cupconsume> list=cupRepo.findAll();
		
	      Cupconsume c=new Cupconsume();
			for(Cupconsume item : list)
			{
				
				if(item.getSize().equals("20"))
				{
					c=item;
				}
			}
			return c;
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "pwc")
	public ResponseCustom getPowerConsumption()
	{
		ResponseCustom res=new ResponseCustom();
		res.message= "power_cons";
		res.status	= "sleep";
		res.humidity= 44.1f;
		res.powerConsum= 26;
		res.processID= 1;
		res.progressPer= 0;
		
		return res ;
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/{filling_id}/{type}")
	public ResponseCustom initProc(@PathVariable int filling_id, @PathVariable String type)
	{
		ResponseCustom res=new ResponseCustom();
		res.message= "process initiated";
		res.status	= "on queue "+type;
		res.humidity= 41.1f;
		res.powerConsum= 26;
		res.processID= filling_id;
		res.progressPer= 12.5f;
		
		return res ;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/cSerSt/{channel_id}/status")
	
	public ResponseStatusDisplay  getChannelnfor(@PathVariable int channel_id)
	{ 
		ResponseStatusDisplay res2=new ResponseStatusDisplay(channel_id);
		res2=get_staus(channel_id);
//		return res;
		return res2;
	}
	
	public  ResponseStatusDisplay get_staus( int chanelId)
	{
		
		ResponseStatusDisplay res=new ResponseStatusDisplay(chanelId);
		res.chan_1_dx10_rack=(byte) genRand(0,1);
		res.chan_1_dx10_slot =(byte) genRand(0,1);
		res.chan_1_dx10_computer_horaire = genRand(1200,9000);
		res.chan_1_dx10_computer_pots = genRand(1,12000);
		res.chan_1_dx10_consigne_vitesse_conv= genRand(124420,12114442);
		res.chan_1_dx10_duree_arret_en_cours= genRand(245,12000); 
		res.chan_1_dx10_duree_arret_cumules = genRand(1,1412);
		res.chan_1_dx10_KM1 =getBool();
		res.chan_1_dx10_memp_accumulation =getBool();
		res.chan_1_dx10_nombre_darret=genRand(1,14);
		res.chan_1_dx10_num_message_puptre=genRand(1,9) ;
		res.chan_1_dx10_produit =getBool();
		res.chan_1_dx10_tempsRemplissage=genRand(99875,10045781) ;
		res.chan_1_dx10_verrtine_verte=getBool();
	
		return res;
	}
	
	public int genRand(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	public boolean getBool()
	{
		 int a=genRand(0,1);
		 if(a==0)
			 return false;
		 else 
			 return true;
	}
	
@RequestMapping(method = RequestMethod.GET, path = "/chnl_in_st/{channel_id}/status")
	
	public Channel_1 getChannel_info(@PathVariable int channel_id)
	{ 
	 Channel_1 chn=new Channel_1();
     return chn;
	}

@CrossOrigin
@RequestMapping(method = RequestMethod.GET, path = "/interupt/{command}")
public Fill interuption(@PathVariable int command)
{ 
	Fill fill=new Fill();
	fill=fill.interupt(command);
   return fill;
}
	
@CrossOrigin
@RequestMapping(method = RequestMethod.POST, path = "/start_fill")
//@ResponseStatus(HttpStatus.OK)
public ResponseEntity start_fill(@RequestBody Input_Prop obj)
{ 

	int result=fillingLogic.initiate(obj);
	ResponseCustom res=new ResponseCustom();
	if(result==1)
	{
		res.status="success";
		res.processID=10124;
		obj.weight=200;
		obj.speed=5;
		 RestService rest=new RestService();
    	rest.initiatePotting(obj);
	}
	else if(result==2)
	{
		res.status="System offline initiated";
		res.processID=12245;
		
	}
	else if(result==0)
	{
		res.status="failed";
		res.processID=12145;
		
	}
	
	return ResponseEntity.ok(res);
//	return ResponseEntity.ok().build();//juse to send http ok as result without content
	
}




@CrossOrigin
@RequestMapping(method = RequestMethod.GET, path = "/start_fill_test")
//@ResponseStatus(HttpStatus.OK)
public ResponseEntity start_fill_2()
{ 
	Input_Prop obj=new Input_Prop();
	
	obj.km1=true;
	obj.axot_under_power=true;
	obj. v90_alarm=true;
	obj.presence_pot=true;
	obj.homing_cam=true;
	obj.presence_product=true;
	obj.cup_size="10";
	obj.downstream_b5_accumulation=true;
	obj.product_name="salt";
	
//	FillingLogic logic=new FillingLogic();
	int result=fillingLogic.initiate(obj);
//	logic.initiate(obj);
	ResponseCustom res=new ResponseCustom();
	if(result==1)
	{
		obj.weight=200;
		obj.speed=5;
//		obj=sendPostRequest(obj);
		if(obj.potting_status_bool)
		{
			obj.filling_status="success";
			obj.filling_status_boolean=true;
			res.status="success";
			res.processID=14572;
		}
		else
		{
			res.status="failed";
			res.processID=14572;
			obj.filling_status="success";
			obj.filling_status_boolean=true;
		}
	}
	else if(result==2)
	{
		res.status="System offline initiated";
		res.processID=12245;
		
	}
	else if(result==0)
	{
		res.status="failed";
		res.processID=14572;
		
	}
	
//	return ResponseEntity.ok().build();

	return ResponseEntity.ok(res);
}



@CrossOrigin
@RequestMapping(method = RequestMethod.GET, path = "/set_stop")
public ResponseEntity setStop()
{ 
	Input_Prop obj=new Input_Prop();
	
	obj.km1=true;
	obj.axot_under_power=true;
	obj. v90_alarm=true;
	obj.presence_pot=true;
	obj.homing_cam=true;
	obj.presence_product=true;
	obj.cup_size="10";
	obj.downstream_b5_accumulation=true;
	obj.product_name="salt";
	
//	FillingLogic logic=new FillingLogic();
	fillingLogic.initiate(obj);
//	logic.initiate(obj);
	ResponseCustom res=new ResponseCustom();
	res.status="success";
	res.processID=10124;
	
//	return ResponseEntity.ok().build();

	return ResponseEntity.ok(res);
}

	
}
