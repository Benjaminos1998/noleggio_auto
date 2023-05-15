import { Component, OnInit } from '@angular/core';
import { environmet } from 'src/app/environments/environment';
import { Utente } from 'src/app/interfacce/utente';
import { UtenteService } from 'src/app/servizi/utente.service';

@Component({
  selector: 'app-utente',
  templateUrl: './utente.component.html',
  styleUrls: ['./utente.component.css'],
})
export class UtenteComponent implements OnInit {
  private apiServerUrl = environmet.apiBaseUrl + 'api/utenti';
  utenti?: Utente[];

  constructor(private utenteService: UtenteService ) {}


  ngOnInit(): void {
    this.utenteService
      .getUtenti(this.apiServerUrl + '')
      .subscribe((data) => {
        (this.utenti = data), console.log(this.utenti);
      });
  }



}
