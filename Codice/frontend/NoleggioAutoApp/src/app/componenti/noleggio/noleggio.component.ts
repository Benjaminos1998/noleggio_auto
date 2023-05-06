import { Component } from '@angular/core';
import { Noleggio } from 'src/app/interfacce/noleggio';
import { NoleggioService } from 'src/app/servizi/noleggio.service';

@Component({
  selector: 'app-noleggio',
  templateUrl: './noleggio.component.html',
  styleUrls: ['./noleggio.component.css']
})
export class NoleggioComponent {


  noleggi?: Noleggio[];

  constructor(private noleggioService:NoleggioService){}


  ngOnInit(): void {
    this.noleggioService
      .getAllNoleggi('http://localhost:8080/noleggio/noleggi')
      .subscribe((data) => {
        (this.noleggi = data), console.log(this.noleggi);
      });
  }
}
