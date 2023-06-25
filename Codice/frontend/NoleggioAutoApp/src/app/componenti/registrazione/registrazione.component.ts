import { Component } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { environmet } from 'src/app/environments/environment';
import { Router } from '@angular/router';
import { UtenteService } from 'src/app/servizi/utente.service';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css'],
})
export class RegistrazioneComponent {



  private apiServerUrl = environmet.apiBaseUrl + 'api/auth';


  utente ={
    nome:'',
    cognome:'',
    dataDiNascita:Date,
    numeroPatente:'',
    email:'',
    password:''
  }


  constructor(private utenteService:UtenteService, private http:HttpClient, private router:Router){}

  nuovoUtente(){
    this.utenteService
      .addUtente(this.apiServerUrl +'/registrazione',this.utente)
      .subscribe(
        result => {
          if(result!=null){
            this.router.navigateByUrl('/dashboard')
          }else{
            alert("username già utilizzato")
          }
        }
      )
  }

}
