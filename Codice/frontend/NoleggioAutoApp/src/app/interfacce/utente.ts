import { Auto } from "./auto";
import { Noleggio } from "./noleggio";

export interface Utente{
  nome:String;
  cognome:String;
  idUtente:number;
  email:String;
  password:String;
  dataDiNascita:Date;
  numeroPatente:number;
  listaNoleggio:Noleggio[];
  listaAuto:Auto[];
}
