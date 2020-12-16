package com.potting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itcps2.resttemplate.RestService;
import com.pottin.model.Input_Prop;
import com.pottin.model.ResponseCustom;
import com.potting.service.PottinLogic;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value="/api")
public class PottingController {

	@Autowired
	private PottinLogic pottingLogic;
//	@Autowired
//	private RestService restService;
	
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, path= "/start_potting_test")
	public Input_Prop start_potting_test()
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
		obj.weight=200;
		obj.speed=5;
		int result=pottingLogic.initiate(obj);
//		logic.initiate(obj);
		ResponseCustom res=new ResponseCustom();
		if(result==1)
		{
			res.status="success";
			res.processID=10124;
			obj._pottingstatus="success";
			obj.potting_status_bool=true;
			RestService restService =new RestService();
			restService.initPackage();
		}
		else if(result==2)
		{
			obj._pottingstatus="failure";
			obj.potting_status_bool=false;
			res.status="System offline initiated";
			res.processID=12245;
			
		}
		else if(result==1)
		{
			obj._pottingstatus="failure";
			obj.potting_status_bool=false;
			res.status="Success";
			res.processID=112402;
			
		}
		

		return obj;
	}
	
	@RequestMapping(method=RequestMethod.POST, path= "/start_potting")
	public ResponseEntity start_potting(@RequestBody Input_Prop obj)
	{	
		int result=pottingLogic.initiate(obj);
//		logic.initiate(obj);
		ResponseCustom res=new ResponseCustom();
		if(result==1)
		{
			res.status="success";
			res.processID=10124;
			obj._pottingstatus="success";
			obj.potting_status_bool=true;
			RestService restService =new RestService();
			restService.initPackage();
		}
		else if(result==2)
		{
			res.status="System offline initiated";
			res.processID=12245;
			
		}
		else if(result==1)
		{
			res.status="Success";
			res.processID=112402;
			
		}
		
//		return ResponseEntity.ok().build();

		return ResponseEntity.ok(res);
	}
	
	public void sendPostRequest()
	{
		
		
	}
	
	
}
