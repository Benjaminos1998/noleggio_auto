import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UtenteVip } from '../interfacce/utenteVip';

@Injectable({
  providedIn: 'root'
})
export class UtenteVipService {

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
