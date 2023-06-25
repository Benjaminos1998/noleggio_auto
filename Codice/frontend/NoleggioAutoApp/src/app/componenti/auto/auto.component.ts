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

  public filteredCars?: Auto[];

  constructor(private autoService: AutoService) {
  }

  ngOnInit(): void {
    this.getAllAuto();
  }

  getAllAuto(): void {
    this.autoService
      .getAllAuto(this.apiServerUrl + '/parcoAuto')
      .subscribe((data) => {
        (this.auto = data), console.log(this.auto);
        this.filteredCars = this.auto;
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

  //Logica per la gestione del filtro

  onFilterChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    let filterValue = selectElement.value;
    if (filterValue == '1') {
      this.filteredCars = this.auto;
    } else if (filterValue == '2') {
      this.filteredCars = this.auto?.filter(automobile => automobile.tipoAuto.startsWith('U'));
    } else if (filterValue == '3') {
      this.filteredCars = this.auto?.filter(automobile => automobile.tipoAuto.startsWith('B'));
    } else if (filterValue == '4') {
      this.filteredCars = this.auto?.filter(automobile => automobile.tipoAuto.startsWith('L'));
    }
    else if (filterValue == '0') {
      this.filteredCars = this.auto;
    }
  }

  // Logica per la gestione dell'immagine

  getCarImage(modello: String): string {
    let imagePath = '';

    if (modello == 'Ferrari') {
      imagePath = 'assets/images/ferrari.jpg';
    } else if (modello == 'Bmw' || modello == 'BMW') {
      imagePath =
        'https://www.motornet.it/img/modelli/auto/BMW/Serie%208%20Gran%20Coupe_1.jpg';
    } else if (modello == 'Tesla') {
      imagePath = 'assets/images/tesla.png';
    } else if (modello == 'Fiat') {
      imagePath =
        'https://www.garageromasnc.com/wp-content/uploads/2021/05/Panda.jpg';
    } else if (modello == 'Alfa Romeo' || modello == 'AlfaRomeo') {
      imagePath = 'assets/images/alfaromeo.png';
    }

    return imagePath;
  }

  //Logica per la gestione del menu

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
