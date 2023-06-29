import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auto } from '../interfacce/auto';

@Injectable({
  providedIn: 'root',
})
export class AutoService {
  constructor(private http: HttpClient) {}

  public getAllAuto(url: string): Observable<Auto[]> {
    return this.http.get<Auto[]>(url);
  }

  public getAutoById(url: string): Observable<Auto> {
    return this.http.get<Auto>(url);
  }

  public addAuto(url: string, body: {}) {
    return this.http.post(url, body);
  }

  public deleteAuto(url: string,id:number): Observable<any>{
    return this.http.delete(url);
  }

}
