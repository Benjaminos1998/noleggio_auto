import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './componenti/dashboard/dashboard.component';
import { LoginComponent } from './componenti/login/login.component';
import { RegistrazioneComponent } from './componenti/registrazione/registrazione.component';
import { UtenteComponent } from './componenti/utente/utente.component';
import { AutoComponent } from './componenti/auto/auto.component';
import { HomeComponent } from './componenti/home/home.component';
import { NoleggioComponent } from './componenti/noleggio/noleggio.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'registrazione',component:RegistrazioneComponent},
  {path:'dashboard',component: DashboardComponent},
  {path:'auto',component:AutoComponent},
  {path:'home',component:HomeComponent},
  {path:'noleggio',component:NoleggioComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
