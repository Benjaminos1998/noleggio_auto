import { Component, OnInit } from '@angular/core';
import { environmet } from 'src/app/environments/environment';
import { Auto } from 'src/app/interfacce/auto';
import { AutoService } from 'src/app/servizi/auto.service';

@Component({
  selector: 'app-auto',
  templateUrl: './auto.component.html',
  styleUrls: ['./auto.component.css']
})
export class AutoComponent  implements OnInit {

  private apiServerUrl = environmet.apiBaseUrl + 'api/auto';

  auto?: Auto[];

  constructor(private autoService: AutoService){}

  ngOnInit(): void {
    this.autoService
    .getAllAuto(this.apiServerUrl+'/parcoAuto')
    .subscribe((data) => {
      (this.auto = data), console.log(this.auto);
    });
  }

}
