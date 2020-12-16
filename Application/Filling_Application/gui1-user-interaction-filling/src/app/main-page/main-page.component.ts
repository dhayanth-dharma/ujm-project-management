import { Component, OnInit } from '@angular/core';
import { response_ } from '../api-socket/response-model';
import { FillingSocketAPI } from '../api-socket/filling-api.socket';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  system_status:any="init";
  cup_size:any="init";
  is_connected:boolean=false;
  _message:string="";
  is_cup_presence:boolean=false;
  is_started=false;
  cup_size_letr:string="unknown";
  process_percent:any=0;
  prod_avail:string="Unknown";
  process_status="Not Initiated";
  _power_value=0.0;
  constructor(private  webSocketAPI: FillingSocketAPI) { }

  ngOnInit() {
    this.webSocketAPI.toggle.subscribe(res=>{
      this.handleMessage(res);
    });
  }
  handleMessage(message:response_){
    
    if(message.code==201 &&
    message.page_id==1   )
    {
      if(message.status=="connected")
      this.is_connected=true;
    }
    if(message.page_id==2 &&message.func_id==101)
    {
       this.is_connected=true;
    }
    if(message.page_id==2 &&message.func_id==102)
    {
      this.process_status="Started";
       this.cup_size=message.arg;
    }
    if(message.page_id==2 &&message.func_id==103)
    {
      this.process_status="in-progress";
       this.cup_size_letr=message.arg;
       this.is_started=true;
    }
    if(message.page_id==2 &&message.func_id==105)
    {
       this.process_percent=message.arg;
    }  
    if(message.page_id==2 &&message.func_id==114)
    {
       this._power_value=message.arg;
    }  
    if(message.page_id==2 && message.func_id==106)
    {
       this.is_cup_presence=true;
    }  
    if(message.page_id==3 && message.func_id==111)
    {
       this.is_cup_presence=false;
       this.is_connected=false;
       this.process_percent=0;
       this.is_started=false;
       this.system_status="init";
       this.cup_size="init";
       
       this.is_cup_presence=false;
       this.is_started=false;
       this.cup_size_letr="unknown";
       this.prod_avail="Unknown";
       this.process_status="Not Initiated";

    }  
    if(message.page_id==3 && message.func_id==112)
    {
      this.setOffline();

    }  
    if(message.page_id==3 && message.func_id==113)
    {
      this.setOnline();
    }  
    if(message.page_id==2 && message.func_id==108)
    {
      this.reset();
      
    }
    this._message=message.message;
    
  }
  reset()
  {
    this.is_cup_presence=false;
    this.is_connected=true;
    this.process_percent=0;
    this.is_started=true;
    this.system_status="init";
    this.cup_size="init";
    this.is_cup_presence=false;
    this.is_started=false;
    this.cup_size_letr="unknown";
    this.prod_avail="Unknown";
    this.process_status="Not Initiated";
  }
  setOffline()
  {

    this.is_cup_presence=false;
    this.is_connected=false;
    this.process_percent=0;
    this.is_started=false;
    this.system_status="offline";
    this.cup_size="init";
    
    this.is_cup_presence=false;
    this.is_started=false;
    this.cup_size_letr="unknown";
    this.prod_avail="Unknown";
    this.process_status="Not Initiated";
  }
  setOnline()
  {
    this.is_cup_presence=false;
    this.is_connected=true;
    this.process_percent=0;
    this.is_started=true;
    this.system_status="init";
    this.cup_size="init";
    
    this.is_cup_presence=false;
    this.is_started=false;
    this.cup_size_letr="unknown";
    this.prod_avail="Unknown";
    this.process_status="Not Initiated";
  }
}
