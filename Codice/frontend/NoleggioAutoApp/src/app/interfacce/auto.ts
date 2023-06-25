import { TipologiaAuto } from "../enums/TipologiaAuto";

export interface Auto{
  idAuto:number;
  targa:String;
  modello:String;
  tipoAuto:TipologiaAuto;
  inUso:boolean;
}
