import { Component, OnInit, ViewChild } from '@angular/core';
import { environmet } from 'src/app/environments/environment';
import { Auto } from 'src/app/interfacce/auto';
import { AutoService } from 'src/app/servizi/auto.service';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-auto',
  templateUrl: './auto.component.html',
  styleUrls: ['./auto.component.css'],
})
export class AutoComponent implements OnInit {
  private apiServerUrl = environmet.apiBaseUrl + 'api/auto';

  auto?: Auto[];
  nuovaAuto = {
    idAuto: 0,
    targa: '',
    modello: '',
    tipoAuto: '',
  };
  TipologiaAuto: any;

  constructor(private autoService: AutoService) {}

  ngOnInit(): void {
    this.getAllAuto();
  }

  getAllAuto(): void {
    this.autoService
      .getAllAuto(this.apiServerUrl + '/parcoAuto')
      .subscribe((data) => {
        (this.auto = data), console.log(this.auto);
      });
  }

  aggiungiAuto(): void {
    this.autoService
      .addAuto(this.apiServerUrl + '/addAuto', this.nuovaAuto)
      .subscribe((data) => {
        this.getAllAuto();
      });
  }

  cancellaAuto(id: number) {
    this.autoService
      .deleteAuto(this.apiServerUrl + '/deleteAuto/' + id, id)
      .subscribe((data) => {
        this.getAllAuto();
      });
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
