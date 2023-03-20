import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environmet } from '../environments/environment';
import { UtenteVip } from '../interfacce/utenteVip';

@Injectable({
  providedIn: 'root'
})
export class UtenteVipService {

  private apiServerUrl = environmet.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getAllUtenteVip(url: string): Observable<UtenteVip[]> {
    return this.http.get<UtenteVip[]>(url);
  }

  public getUtenteVip(url: string): Observable<UtenteVip> {
    return this.http.get<UtenteVip>(url);
  }
  public addUtenteVip(url:string,body:{}){
    return this.http.post(url,body)
  }
}
