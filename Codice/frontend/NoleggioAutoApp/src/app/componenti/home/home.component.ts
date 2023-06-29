import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {

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
