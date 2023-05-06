import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Noleggio } from '../interfacce/noleggio';

@Injectable({
  providedIn: 'root',
})
export class NoleggioService {
  constructor(private http: HttpClient) {}

  public getAllNoleggi(url: string): Observable<Noleggio[]> {
    return this.http.get<Noleggio[]>(url);
  }

  public getUtenteVip(url: string): Observable<Noleggio> {
    return this.http.get<Noleggio>(url);
  }
  public addUtenteVip(url: string, body: {}) {
    return this.http.post(url, body);
  }
}
