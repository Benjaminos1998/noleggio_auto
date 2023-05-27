import { HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { environmet } from 'src/app/environments/environment';
import { LoginService } from 'src/app/servizi/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  authRequest = {
    email: '',
    passwrod: '',
  };
  logged = this.loginService.getLogged()
  error = this.loginService.getError()

  private apiServerUrl = environmet.apiBaseUrl +"/api/auth/login";

  constructor(private loginService: LoginService, private router: Router) {}

  autenticazioneUtente(loginForm: NgForm) :void {
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });


    this.loginService
      .loginUtente(this.apiServerUrl,this.authRequest,headers)
      .subscribe((result) => {
        if(result!=null){
          this.error=false;
          this.logged=true;
          localStorage.setItem('token');
        }
      }
      )





  }
}
