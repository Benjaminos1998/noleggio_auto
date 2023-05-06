import { Auto } from "./auto";
import { Noleggio } from "./noleggio";

export interface UtenteVip{
  nome:String;
  cognome:String;
  idUtente:number;
  email:String;
  password:String;
  dataDiNascita:Date;
  numeroPatente:number;
  listaNoleggio:Noleggio[];
  listaAuto:Auto[];
  numeroCarta:number;
}


