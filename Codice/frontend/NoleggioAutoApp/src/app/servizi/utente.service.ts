import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Utente } from '../interfacce/utente';

@Injectable({
  providedIn: 'root',
})
export class UtenteService {
  constructor(private http: HttpClient) {}

  public getAllUtente(url: string): Observable<Utente[]> {
    return this.http.get<Utente[]>(url);
  }

  public getUtente(url: string): Observable<Utente> {
    return this.http.get<Utente>(url);
  }
  public addUtente(url: string, body: {}) {
    return this.http.post(url, body);
  }
}
