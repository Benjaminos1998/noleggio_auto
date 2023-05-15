import { Ruolo } from "../enums/Ruolo";


export interface Utente{
  idUtente:number;
  nome:String;
  cognome:String;
  email:String;
  password:String;
  numeroPunti:number;
  dataDiNascita:Date;
  numeroPatente:number;
  eta:number;
  ruolo:Ruolo;
  noleggioInCorso:boolean;
}
