import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class RegistrazioneService {
  constructor(private http: HttpClient) {}

  registrazioneUtente(url: string, body: {},headers:HttpHeaders) {



    return this.http.post(url, body,{headers});
  }
}
