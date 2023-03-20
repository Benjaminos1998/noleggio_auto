import { Injectable } from '@angular/core';
import { environmet } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auto } from '../interfacce/auto';

@Injectable({
  providedIn: 'root',
})
export class AutoService {
  private apiServerUrl = environmet.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getAllAuto(url: string): Observable<Auto[]> {
    return this.http.get<Auto[]>(url);
  }

  public getAuto(url: string): Observable<Auto> {
    return this.http.get<Auto>(url);
  }
  public addAuto(url:string,body:{}){
    return this.http.post(url,body)
  }
}
