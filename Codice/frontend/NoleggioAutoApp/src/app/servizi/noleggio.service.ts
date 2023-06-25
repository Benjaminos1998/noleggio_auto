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

  public getNoleggioById(url:string) :Observable<Noleggio> {
    return this.http.get<Noleggio>(url);
  }

  public addNoleggio(url:string,body:Noleggio) : Observable<any> {
    return this.http.post(url,body);
  }

  public deleteNoleggio(url:string) : Observable<any> {
    return this.http.delete(url);
  }


}
