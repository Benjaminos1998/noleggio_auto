import { Component, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent {


  images = [
    'assets/images/image.jpg',
    'assets/images/image1.jpg',
    'assets/images/image2.jpg',
    'assets/images/image3.jpg',

  ];

  bgImage = '';

  constructor(){
    this.selectRandomImage();
  }

  selectRandomImage(){
    this.bgImage = this.images[Math.floor(Math.random() * this.images.length)];
  }

}
