import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class LoginService {


  logged? : boolean
  error?: boolean


  constructor(private http:HttpClient) {}


  loginUtente(url:string,body:{}, headers:HttpHeaders) {
    return this.http.post(url,body, {headers});
  }

  setFalse(){
    this.logged = false
  }

  setTrue(){
    this.logged=true
  }

  getLogged(){
    return this.logged
  }

  credenzialiErrate(){
    this.error = true
  }

  getError(){
    return this.error
  }


}
