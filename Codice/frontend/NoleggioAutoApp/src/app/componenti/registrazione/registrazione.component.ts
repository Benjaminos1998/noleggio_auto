import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { environmet } from 'src/app/environments/environment';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css'],
})
export class RegistrazioneComponent {
  private apiServerUrl = environmet.apiBaseUrl + 'api/auth/registrazione';

  constructor(private http: HttpClient) {}

  registrazioneUtente(registrazioneForm: NgForm) : void{
    const Utente = {
      nome: registrazioneForm.value.nome,
      cognome: registrazioneForm.value.cognome,
      dataDiNascita: registrazioneForm.value.dataDiNascita,
      numeroPatente: registrazioneForm.value.numeroPatente,
      email: registrazioneForm.value.email,
      password: registrazioneForm.value.password,
    };

    this.http.post(this.apiServerUrl,Utente)
      .subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.error("Impossibile registrarsi. Riprova!")
        }
      )
  }


  @ViewChild('sidenav') sidenav?: MatSidenav;
  isExpanded = true;
  showSubmenu: boolean = false;
  isShowing = false;
  showSubSubMenu: boolean = false;

  mouseenter() {
    if (!this.isExpanded) {
      this.isShowing = true;
    }
  }

  mouseleave() {
    if (!this.isExpanded) {
      this.isShowing = false;
    }
  }
}
