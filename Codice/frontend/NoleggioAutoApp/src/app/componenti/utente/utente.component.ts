import { Component, OnInit } from '@angular/core';
import { environmet } from 'src/app/environments/environment';
import { Utente } from 'src/app/interfacce/utente';
import { UtenteService } from 'src/app/servizi/utente.service';

@Component({
  selector: 'app-utente',
  templateUrl: './utente.component.html',
  styleUrls: ['./utente.component.css'],
})
export class UtenteComponent  {
  private apiServerUrl = environmet.apiBaseUrl + 'utente/';
  utenti?: Utente[];

  constructor(private utenteService: UtenteService ) {}


  ngOnInit(): void {
    this.utenteService
      .getAllUtente(this.apiServerUrl + 'utenti')
      .subscribe((data) => {
        (this.utenti = data), console.log(this.utenti);
      });
  }



}
