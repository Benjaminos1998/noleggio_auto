import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { environmet } from 'src/app/environments/environment';
import { RegistrazioneService } from 'src/app/servizi/registrazione.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-registrazione',
  templateUrl: './registrazione.component.html',
  styleUrls: ['./registrazione.component.css'],
})
export class RegistrazioneComponent {
  // private apiServerUrl = environmet.apiBaseUrl + 'api/auth/registrazione';

  // nuovoUtente = {
  //   nome: '',
  //   cognome: '',
  //   dataDiNascita: '',
  //   numeroPatente: '',
  //   email: '',
  //   password: '',
  // };

  // constructor(
  //   private http: HttpClient,
  //   private registrazioneService: RegistrazioneService,
  //   private router: Router,
  //   private modalService: NgbModal
  // ) {}
  // ngOnInit(): void {}

  // registraUtente(registrazioneForm: NgForm): void {
  //   let headers: HttpHeaders = new HttpHeaders({
  //     'Content-Type': 'application/json',
  //   });

  //   this.registrazioneService
  //     .registrazioneUtente(this.apiServerUrl, this.nuovoUtente, headers)
  //     .subscribe((result) => {
  //       if (result != null) {
  //         this.modalService.dismissAll();
  //         this.router.navigateByUrl('/home');
  //       } else {
  //         alert('username già utilizzato');
  //       }
  //     });
  // }

  // @ViewChild('sidenav') sidenav?: MatSidenav;
  // isExpanded = true;
  // showSubmenu: boolean = false;
  // isShowing = false;
  // showSubSubMenu: boolean = false;

  // mouseenter() {
  //   if (!this.isExpanded) {
  //     this.isShowing = true;
  //   }
  // }

  // mouseleave() {
  //   if (!this.isExpanded) {
  //     this.isShowing = false;
  //   }
  // }

  // open(content: any) {
  //   this.modalService.open(content);
  // }
}
