import { Injectable } from "@angular/core";

import { HttpErrorResponse } from '@angular/common/http';
// import { Http, Response, Headers, RequestOptions } from '@angular/common/http';

import { Observable } from 'rxjs';
// import 'rxjs/add/observable/throw';
// import 'rxjs/add/operator/catch';
// import 'rxjs/add/operator/do';
// import { API_URL } from '../_shared/API_URLS.const';
// import { RCMasterDataResponse } from "../_shared/rcm-master-data-response";
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable()
export class UserInteractionService {
 
    
     localUrl:any="http://localhost:8080/api/interupt/1";
     localUrl2:any="http://localhost:8080/api/interupt/0";
  
  constructor(  private http: HttpClient ) { }



  getStatus(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.localUrl, { observe: 'response' });
  }
  stopCommand(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.localUrl, { observe: 'response' });
  }
  startCommand(): Observable<HttpResponse<any>> {
    return this.http.get<any>(
      this.localUrl2, { observe: 'response' });
  }

    private handleError(err: HttpErrorResponse) {
        console.error(err.message);
        return Observable.throw(err.message);
    }


}
