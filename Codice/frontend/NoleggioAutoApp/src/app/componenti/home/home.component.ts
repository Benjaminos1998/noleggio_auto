import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  /*
Logica per la gestione del menu
*/

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
